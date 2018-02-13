package com.coviam.services.Impl;


import com.coviam.dto.*;
import com.coviam.entity.FlightInfo;
import com.coviam.entity.FlightSearchCriteria;
import com.coviam.repository.FlightCriteriaRepository;
import com.coviam.repository.FlightInfoRepository;
import com.coviam.services.FlightDetailService;
import com.coviam.util.*;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlightDetailServiceImpl extends  BaseResponseDTO<FlightDetailResultDTO>implements FlightDetailService{

    @Autowired
    UUIDUtil UUIDUtil;
    @Autowired
    FlightConstants flightConstants;
    @Autowired
    EscapeCharacter escapeCharacter;
    @Autowired
    FlightCriteriaRepository flightCriteriaRepository;
    @Autowired
    FlightInfoRepository flightInfoRepository;
    @Autowired
    Logger log;




    public BaseResponseDTO getFlightDetail(FlightDetailRequestDTO flightDetailRequestDTO , String interactionId) {
        String flightPricingIds = flightDetailRequestDTO.getFlightId();
        FlightDetailResultDTO flightDetailResultDTO = new FlightDetailResultDTO();
        BaseResponseDTO response;
        try {
            flightDetailResultDTO.setDetailResult(getFlightItineraryDetails(flightPricingIds));
            if(flightDetailRequestDTO.isDoGenerate()) {
                String superPnr = UUIDUtil.getUniqueId();
                flightDetailResultDTO.setSuperPnr(superPnr);
                saveFlightSearchCriteria(flightDetailRequestDTO, superPnr);
            }
            flightDetailResultDTO.setTotalPrice(getTotalPrice(flightDetailResultDTO, flightDetailRequestDTO));
            if (flightDetailResultDTO.getDetailResult().size() > 0) {
                log.debug("Flight Detail response got successfully");
                response =  new BaseResponseDTO<FlightDetailResultDTO>(flightConstants.SUCCESS_CODE, flightConstants.SUCCESS, interactionId, flightConstants.FLIGHT_DETAIL, flightDetailResultDTO);
            } else {
                response =  new BaseResponseDTO<FlightDetailResultDTO>(flightConstants.FAILURE_CODE, flightConstants.FAILURE, interactionId, flightConstants.FLIGHT_DETAIL, flightDetailResultDTO);
                log.debug("Error in Getting Flight Details");
            }
        } catch (Exception e) {
            log.debug("Exception in Getting Flight Details");
            return new BaseResponseDTO<FlightDetailResultDTO>(flightConstants.EXCEPTION_CODE, flightConstants.FAILURE, interactionId, flightConstants.FLIGHT_DETAIL, flightDetailResultDTO);
        }
        return response;
    }

    private void saveFlightSearchCriteria(FlightDetailRequestDTO flightDetailRequestDTO, String superPnr) {
        FlightSearchCriteria flightSearchCriteria = new FlightSearchCriteria();
        BeanUtils.copyProperties(flightDetailRequestDTO, flightSearchCriteria);
        flightCriteriaRepository.save(flightSearchCriteria);
    }

    private int getTotalPrice(FlightDetailResultDTO flightDetailResultDTO, FlightDetailRequestDTO flightDetailRequestDTO) {
        int totalAmount = 0;
        int adultCount = flightDetailRequestDTO.getAdult();
        int childCount = flightDetailRequestDTO.getChild();
        int infantCount = flightDetailRequestDTO.getInfant();
        try {
            if (flightDetailResultDTO.getDetailResult().size() > 0) {
                FlightDetailResponseDTO onGoingSectorDetails= flightDetailResultDTO.getDetailResult().get(0);
                totalAmount = getTotalAmount(onGoingSectorDetails, adultCount, childCount,infantCount);
                if (flightDetailResultDTO.getDetailResult().size() > 1) {
                    FlightDetailResponseDTO returnSectorDetails= flightDetailResultDTO.getDetailResult().get(0);
                    totalAmount += getTotalAmount(returnSectorDetails, adultCount, childCount, infantCount);
                }
            }
        } catch (Exception e) {
            log.debug("Getting Exception in calculating the total Amount");
            return 0;
        }
        return totalAmount;
    }

    private int getTotalAmount(FlightDetailResponseDTO sectorDetails, int adultCount, int childCount, int infantCount) {
        return (int) ((adultCount > 0 ? adultCount * Double.parseDouble(sectorDetails.getPricePerAdult()) : 0.0)
                + (childCount > 0 ? childCount * Double.parseDouble(sectorDetails.getPricePerChild()) : 0.0)
                + (infantCount > 0 ? infantCount * Double.parseDouble(sectorDetails.getPricePerInfant()) : 0.0));

    }

    private FlightDetailRequestDTO getFlightPricingRequestParams(HttpServletRequest request) {
        FlightDetailRequestDTO flightDetailRequestDTO = new FlightDetailRequestDTO();
        flightDetailRequestDTO.setFlightId((request.getParameter("flightId")));
        return flightDetailRequestDTO;

    }

    @Override
    public FlightDetailRequestDTO mapAllParamsToDTOObject(String flightId, String origin, String destination, String originDepartDate, String destinationArrivalDate, int adults, int infants, int children, String flightType) {
        FlightDetailRequestDTO flightDetailRequestDTO = new FlightDetailRequestDTO();
        flightDetailRequestDTO.setFlightId(flightId);
        flightDetailRequestDTO.setOrigin(origin);
        flightDetailRequestDTO.setDestination(destination);
        flightDetailRequestDTO.setDepartDate(originDepartDate);
        flightDetailRequestDTO.setReturnDate(destinationArrivalDate);
        flightDetailRequestDTO.setAdult(adults);
        flightDetailRequestDTO.setChild(children);
        flightDetailRequestDTO.setInfant(infants);
        flightDetailRequestDTO.setFlightType(flightType);
        return  flightDetailRequestDTO;
    }

    private List<FlightDetailResponseDTO> getFlightItineraryDetails(String itineraryFlightId) {
        List<FlightDetailResponseDTO> flightDetailResponseDTOList = new ArrayList<>();
        String flightPricingIds[] = itineraryFlightId.split(",");
        String onGoingTripFlightId = flightPricingIds[0];
        FlightInfo OnGoingFlightInfoRes = flightInfoRepository.getFlightInfo(onGoingTripFlightId);
        FlightDetailResponseDTO OnGoingFlightDetailDTORes = new FlightDetailResponseDTO();
        BeanUtils.copyProperties(OnGoingFlightInfoRes, OnGoingFlightDetailDTORes);
        flightDetailResponseDTOList.add(OnGoingFlightDetailDTORes);

        if (flightPricingIds.length > 1) {
            String returnTripFlightId = flightPricingIds[1];
            FlightInfo returnFlightInfoRes = flightInfoRepository.getFlightInfo(returnTripFlightId);
            FlightDetailResponseDTO returnFlightDetailDTORes = new FlightDetailResponseDTO();
            BeanUtils.copyProperties(returnFlightInfoRes, returnFlightDetailDTORes);
            flightDetailResponseDTOList.add(returnFlightDetailDTORes);
        }
        return flightDetailResponseDTOList;
    }


}
