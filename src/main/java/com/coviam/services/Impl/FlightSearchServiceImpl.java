package com.coviam.services.Impl;

import com.coviam.cache.CachingWrapper;
import com.coviam.dto.FlightSearchRequestDTO;
import com.coviam.dto.FlightSearchResponseDTO;
import com.coviam.entity.FlightInfo;
import com.coviam.entity.FlightSearchResponse;
import com.coviam.repository.FlightInfoRepository;
import com.coviam.repository.FlightSearchRepository;
import com.coviam.services.FlightSearchService;
import com.coviam.util.*;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FlightSearchServiceImpl implements FlightSearchService {

    @Autowired
    CachingWrapper cachingWrapper;
    @Autowired
    RandomGenerator randomGenerator;
    @Autowired
    FlightConstants flightConstants;
    @Autowired
    EscapeCharacter escapeCharacter;
    @Autowired
    FlightSearchRepository flightSearchRepository;
    @Autowired
    FlightInfoRepository flightInfoRepository;
    @Autowired
    CommonUtil commonUtil;


    @Override
    public FlightInfo save(FlightInfo flightInfo) {
        return flightInfoRepository.save(flightInfo);
    }

    public String getAllFlights(FlightSearchRequestDTO flightSearchRequestDTO) {
        System.out.println(flightSearchRequestDTO.toString());
        if (flightSearchResponsePresentInCache(flightSearchRequestDTO)) {
            return cachingWrapper.readValue(flightConstants.FLIGHT_CACHE_SET, flightSearchRequestDTO.toString(), flightConstants.FLIGHT_SEARCH_COL);
        }
        JSONObject flightSearchRespObj = new JSONObject();
        JSONArray flightResArr = new JSONArray();
        List<FlightSearchResponseDTO> oneWayFlightSearchResponse = new ArrayList<FlightSearchResponseDTO>();
        String response;
        try {
            oneWayFlightSearchResponse = getAllOneWayFlights(flightSearchRequestDTO);
            flightResArr.put(0, oneWayFlightSearchResponse);

            if (flightSearchRequestDTO.getFlightType().equalsIgnoreCase("ROUNDTRIP")) {
                List<FlightSearchResponseDTO> returnWayFlightSearchResponse = new ArrayList<FlightSearchResponseDTO>();
                returnWayFlightSearchResponse = getAllReturnTripFlights(flightSearchRequestDTO);
                flightResArr.put(1, returnWayFlightSearchResponse);
            }
            flightSearchRespObj.put("search_results", flightResArr);
            if (flightSearchRespObj.getJSONArray("search_results").getJSONArray(0).length() != 0) {
                System.out.println("Flight Search response got successfully");
                response = commonUtil.successResponse(flightSearchRespObj, flightConstants.FLIGHT_SEARCH, randomGenerator.generateRandomString());
                saveSearchResponseInCache(flightSearchRequestDTO, response);
            } else {
                response = commonUtil.errorResponse(flightConstants.FAILURE_CODE, flightConstants.NO_FLIGHT_FOUND_MSG, randomGenerator.generateRandomString(), flightConstants.FLIGHT_SEARCH);
                System.out.println("Error in Getting Flight Search results");
            }
        } catch (Exception e) {
            System.out.println("Exception in Getting Flight Search Details");
            return escapeCharacter.escapeCharacter(new JSONObject(new ResponseDeco(flightConstants.EXCEPTION_CODE, flightConstants.FAILURE, randomGenerator.generateRandomString(),
                    flightConstants.FLIGHT_SEARCH, new JSONObject())).toString());
        }
        return escapeCharacter.escapeCharacter(response);
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


    private void saveSearchResponseInCache(FlightSearchRequestDTO flightSearchRequestDTO, String response) {
        cachingWrapper.writeWithoutCompression(flightConstants.FLIGHT_CACHE_SET, flightSearchRequestDTO.toString(), flightConstants.FLIGHT_SEARCH_COL, escapeCharacter.escapeCharacter(response));
    }


    public boolean flightSearchResponsePresentInCache(FlightSearchRequestDTO flightSearchRequestDTO) {
        if (!StringUtils.isBlank(cachingWrapper.readValue(flightConstants.FLIGHT_CACHE_SET, flightSearchRequestDTO.toString(), flightConstants.FLIGHT_SEARCH_COL))) {
            return true;
        }
        return false;
    }

    public List<FlightSearchResponseDTO> getAllReturnTripFlights(FlightSearchRequestDTO flightSearchRequestDTO) {
        List<FlightInfo> returnWayFlightInfoResponses = flightInfoRepository.flightSearch(flightSearchRequestDTO.getDestination(),
                flightSearchRequestDTO.getOrigin(),
                flightSearchRequestDTO.getDestinationArrivalDate());
        System.out.println("No of Flights in return Trip : " + returnWayFlightInfoResponses.size());
        List<FlightSearchResponse> returnWayFlightSearchResponses = getFlightSearchResp(returnWayFlightInfoResponses);
        return getFlightSearchResponseDTOs(returnWayFlightSearchResponses);
    }

    public List<FlightSearchResponseDTO> getAllOneWayFlights(FlightSearchRequestDTO flightSearchRequestDTO) {
        List<FlightInfo> oneWayResponseList = flightInfoRepository.flightSearch(flightSearchRequestDTO.getOrigin(),
                flightSearchRequestDTO.getDestination(),
                flightSearchRequestDTO.getOriginDepartDate());
        System.out.println("No of Flights in OneWay : " + oneWayResponseList.size());
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

    public int getPaxCount(FlightSearchRequestDTO flightSearchRequest) {
        return flightSearchRequest.getAdults() + flightSearchRequest.getChildren() + flightSearchRequest.getInfants();

    }


}
