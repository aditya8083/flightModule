package com.coviam.services.Impl;

import com.coviam.cache.CachingWrapper;
import com.coviam.dto.BaseResponseDTO;
import com.coviam.dto.FlightSearchRequestDTO;
import com.coviam.dto.FlightSearchResponseDTO;
import com.coviam.dto.FlightSearchResultDTO;
import com.coviam.entity.FlightInfo;
import com.coviam.entity.FlightSearchResponse;
import com.coviam.repository.FlightInfoRepository;
import com.coviam.repository.FlightSearchRepository;
import com.coviam.services.FlightSearchService;
import com.coviam.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.Logger;


@Service
public class FlightSearchServiceImpl extends BaseResponseDTO<FlightSearchResultDTO> implements FlightSearchService{

    @Autowired
    CachingWrapper cachingWrapper;
    @Autowired
    UUIDUtil UUIDUtil;
    @Autowired
    FlightConstants flightConstants;
    @Autowired
    EscapeCharacter escapeCharacter;
    @Autowired
    FlightSearchRepository flightSearchRepository;
    @Autowired
    FlightInfoRepository flightInfoRepository;
    @Autowired
    Logger log;


    @Override
    public FlightInfo save(FlightInfo flightInfo) {
        return flightInfoRepository.save(flightInfo);
    }

    public BaseResponseDTO getAllFlights(FlightSearchRequestDTO flightSearchRequestDTO, String interactionId) {
        log.debug(flightSearchRequestDTO.toString());
        if (flightSearchResponsePresentInCache(flightSearchRequestDTO)) {
            return cachingWrapper.readValue(flightConstants.FLIGHT_CACHE_SET, flightSearchRequestDTO.toString(), flightConstants.FLIGHT_SEARCH_COL);
        }
        FlightSearchResultDTO flightSearchResultDTO = new FlightSearchResultDTO();
        BaseResponseDTO response;
        try {
            flightSearchResultDTO.setOneWay(getAllOneWayFlights(flightSearchRequestDTO));
            if (flightSearchRequestDTO.getFlightType().equalsIgnoreCase("ROUNDTRIP")) {
                flightSearchResultDTO.setRoundWay(getAllReturnTripFlights(flightSearchRequestDTO));
            }
            if (flightSearchResultDTO.getOneWay().size() > 0 && (
                    flightSearchRequestDTO.getFlightType().equalsIgnoreCase("ROUNDTRIP")) && flightSearchResultDTO.getRoundWay().size() > 0) {
                log.debug("Flight Search response got successfully");
                response = new BaseResponseDTO<FlightSearchResultDTO>(flightConstants.SUCCESS_CODE, flightConstants.SUCCESS,interactionId, flightConstants.FLIGHT_SEARCH, flightSearchResultDTO);
                saveSearchResponseInCache(flightSearchRequestDTO, response);
                log.info(response);
            } else {
                response = new BaseResponseDTO<FlightSearchResultDTO>(flightConstants.FAILURE_CODE, flightConstants.NO_FLIGHT_FOUND_MSG, interactionId, flightConstants.FLIGHT_SEARCH, flightSearchResultDTO);
                log.debug("Error in Getting Flight Search results");
            }
        } catch (Exception e) {
            log.debug("Exception in Getting Flight Search Details");
            return new BaseResponseDTO<FlightSearchResultDTO>(flightConstants.EXCEPTION_CODE, flightConstants.FAILURE, interactionId, flightConstants.FLIGHT_SEARCH, flightSearchResultDTO);
        }
        return response;
    }

    @Override
    public FlightSearchRequestDTO mapAllParamsToDTOObject(String origin, String destination, String originDepartDate, String destinationArrivalDate, int adults, int infants, int children, String flightType) {
        FlightSearchRequestDTO flightSearchRequestDTO = new FlightSearchRequestDTO();
        flightSearchRequestDTO.setOrigin(origin);
        flightSearchRequestDTO.setDestination(destination);
        flightSearchRequestDTO.setOriginDepartDate(originDepartDate);
        flightSearchRequestDTO.setDestinationArrivalDate(destinationArrivalDate);
        flightSearchRequestDTO.setAdults(adults);
        flightSearchRequestDTO.setChildren(children);
        flightSearchRequestDTO.setInfants(infants);
        flightSearchRequestDTO.setFlightType(flightType);
        return  flightSearchRequestDTO;
    }


    private void saveSearchResponseInCache(FlightSearchRequestDTO flightSearchRequestDTO, BaseResponseDTO response) {
        cachingWrapper.writeWithoutCompression(flightConstants.FLIGHT_CACHE_SET, flightSearchRequestDTO.toString(), flightConstants.FLIGHT_SEARCH_COL, response);
    }


    public boolean flightSearchResponsePresentInCache(FlightSearchRequestDTO flightSearchRequestDTO) {
        if (StringUtils.isBlank((cachingWrapper.readValue(flightConstants.FLIGHT_CACHE_SET, flightSearchRequestDTO.toString(), flightConstants.FLIGHT_SEARCH_COL)).toString())) {
            return true;
        }
        return false;
    }

    public List<FlightSearchResponseDTO> getAllReturnTripFlights(FlightSearchRequestDTO flightSearchRequestDTO) {
        List<FlightInfo> returnWayFlightInfoResponses = flightInfoRepository.flightSearch(flightSearchRequestDTO.getDestination(),
                flightSearchRequestDTO.getOrigin(),
                flightSearchRequestDTO.getDestinationArrivalDate());
        log.debug("No of Flights in return Trip : " + returnWayFlightInfoResponses.size());
        List<FlightSearchResponse> returnWayFlightSearchResponses = getFlightSearchResp(returnWayFlightInfoResponses);
        return getFlightSearchResponseDTOs(returnWayFlightSearchResponses);
    }

    public List<FlightSearchResponseDTO> getAllOneWayFlights(FlightSearchRequestDTO flightSearchRequestDTO) {
        List<FlightInfo> oneWayResponseList = flightInfoRepository.flightSearch(flightSearchRequestDTO.getOrigin(),
                flightSearchRequestDTO.getDestination(),
                flightSearchRequestDTO.getOriginDepartDate());
        log.debug("No of Flights in OneWay : " + oneWayResponseList.size());
        List<FlightSearchResponse> oneWayFlightSearchResponses = getFlightSearchResp(oneWayResponseList);
        return getFlightSearchResponseDTOs(oneWayFlightSearchResponses);
    }

    private List<FlightSearchResponse> getFlightSearchResp(List<FlightInfo> returnWayFlightSearchResponse) {
        List<FlightSearchResponse> flightSearchResponses = new ArrayList<>();
        for(FlightInfo flightInfo : returnWayFlightSearchResponse){
            FlightSearchResponse flightSearchResponse = new FlightSearchResponse();
            BeanUtils.copyProperties(flightInfo,flightSearchResponse);
            flightSearchResponses.add(flightSearchResponse);
        }
        return flightSearchResponses;
    }

    private List<FlightSearchResponseDTO> getFlightSearchResponseDTOs(List<FlightSearchResponse> flightSearchResponseList) {
        List<FlightSearchResponseDTO> flightSearchResponseDTOS = new ArrayList<>();
        for (FlightSearchResponse flightSearchResponse : flightSearchResponseList) {
            FlightSearchResponseDTO flightSearchResponseDTO = new FlightSearchResponseDTO();
            BeanUtils.copyProperties(flightSearchResponse, flightSearchResponseDTO);
            flightSearchResponseDTOS.add(flightSearchResponseDTO);
        }
        return flightSearchResponseDTOS;
    }

}
