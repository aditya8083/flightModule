package com.coviam.services.Impl;

import com.coviam.dto.BaseResponseDTO;
import com.coviam.dto.FlightDetailResponseDTO;
import com.coviam.dto.FlightItineraryDetailsDTO;
import com.coviam.entity.FlightInfo;
import com.coviam.entity.FlightSearchCriteria;
import com.coviam.repository.FlightCriteriaRepository;
import com.coviam.repository.FlightInfoRepository;
import com.coviam.services.FlightItineraryService;
import com.coviam.util.*;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlightItineraryServiceImpl extends BaseResponseDTO<FlightItineraryDetailsDTO> implements FlightItineraryService {
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


    @Override
    public BaseResponseDTO getFlightItinerary(String superPnr, String interactionId) {
        BaseResponseDTO response;
        FlightItineraryDetailsDTO flightItineraryDetailsDTO = new FlightItineraryDetailsDTO();
        try {
            String itineraryFlightId = getFlightIDsForItinerary(superPnr);
            List<FlightDetailResponseDTO> flightItineraryDetails = getFlightItineraryDetails(itineraryFlightId);
            flightItineraryDetailsDTO.setFlightDetailResponseDTOList(flightItineraryDetails);
            if(flightItineraryDetailsDTO.getFlightDetailResponseDTOList().size() > 0) {
                log.debug("Flight Itinerary Detail response got successfully");
                response = new BaseResponseDTO<FlightItineraryDetailsDTO>(flightConstants.SUCCESS_CODE, flightConstants.SUCCESS, interactionId, flightConstants.FLIGHT_ITINERARY_RESPONSE, flightItineraryDetailsDTO);
            } else {
                response = new BaseResponseDTO<FlightItineraryDetailsDTO>(flightConstants.FAILURE_CODE, flightConstants.FAILURE, interactionId, flightConstants.FLIGHT_ITINERARY_RESPONSE, flightItineraryDetailsDTO);
            }
        }catch(Exception e){
                log.debug("Getting Exception in getting the flight Itinerary Details from Database");
                return new BaseResponseDTO<FlightItineraryDetailsDTO>(flightConstants.EXCEPTION_CODE, flightConstants.FAILURE, interactionId, flightConstants.FLIGHT_ITINERARY_RESPONSE, flightItineraryDetailsDTO);
            }
            return response;
        }

        private List<FlightDetailResponseDTO> getFlightItineraryDetails (String itineraryFlightId){
            List<FlightDetailResponseDTO> flightDetailResponseDTOList = new ArrayList<>();
            String flightPricingIds[] = itineraryFlightId.split(",");
            String onGoingTripFlightId = flightPricingIds[0];
            FlightInfo OnGoingFlightInfoRes = flightInfoRepository.getFlightInfo(onGoingTripFlightId);
            FlightDetailResponseDTO OnGoingFlightDetailDTORes = new FlightDetailResponseDTO();
            BeanUtils.copyProperties(OnGoingFlightInfoRes, OnGoingFlightDetailDTORes);
            flightDetailResponseDTOList.add( OnGoingFlightDetailDTORes);

            if (flightPricingIds.length > 1) {
                String returnTripFlightId = flightPricingIds[1];
                FlightInfo returnFlightInfoRes = flightInfoRepository.getFlightInfo(returnTripFlightId);
                FlightDetailResponseDTO returnFlightDetailDTORes = new FlightDetailResponseDTO();
                BeanUtils.copyProperties(returnFlightInfoRes, returnFlightDetailDTORes);
                flightDetailResponseDTOList.add(returnFlightDetailDTORes);
            }
            return flightDetailResponseDTOList;
        }

        public String getFlightIDsForItinerary (String superPnr){
            FlightSearchCriteria flightSearchCriteria = flightCriteriaRepository.getFlightIdBySuperPnr(superPnr);
            return flightSearchCriteria.getFlightId();
        }
    }
