package com.coviam.services.Impl;

import com.coviam.dto.*;
import com.coviam.entity.FlightInfo;
import com.coviam.entity.FlightSearchResponse;
import com.coviam.repository.BaseResponseRepository;
import com.coviam.repository.FlightInfoRepository;
import com.coviam.repository.FlightSearchRepository;
import com.coviam.services.FlightSearchService;
import com.coviam.util.*;
import org.apache.commons.codec.binary.Base64;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.Logger;
import org.springframework.web.client.RestTemplate;


@Service
public class FlightSearchServiceImpl extends BaseResponseDTO<FlightSearchResultDTO> implements FlightSearchService{

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
    @Autowired
    BaseResponseRepository baseResponseRepository;
    @Autowired
    RestTemplate restTemplate;





    @Override
    public FlightInfo save(FlightInfo flightInfo) {
        return flightInfoRepository.save(flightInfo);
    }

    @Cacheable(value="flightSearch")
    public BaseResponseDTO  getAllFlights(FlightSearchRequestDTO flightSearchRequestDTO, String interactionId) {

        getDateInFormat(flightSearchRequestDTO);
        log.debug(flightSearchRequestDTO.toString());

        if (flightSearchResponsePresentInCache(flightSearchRequestDTO)) {
            return baseResponseRepository.get(flightConstants.FLIGHT_CACHE_SET, flightSearchRequestDTO.toString());
        }
        FlightSearchResultDTO flightSearchResultDTO = new FlightSearchResultDTO();
        BaseResponseDTO response;
        try {
            List<List<FlightSearchResponseDTO>> FlightSearchResponseDTOs = new ArrayList<>();
            FlightSearchResponseDTOs.add(getAllOneWayFlights(flightSearchRequestDTO));
            if (flightSearchRequestDTO.getFlightType().equalsIgnoreCase("ROUNDTRIP")) {
                FlightSearchResponseDTOs.add(getAllReturnTripFlights(flightSearchRequestDTO));
            }
            flightSearchResultDTO.setFlightResult(FlightSearchResponseDTOs);
            if (flightSearchRequestDTO.getFlightType().equalsIgnoreCase("ONEWAY") && flightSearchResultDTO.getFlightResult().get(0).size() > 0){
                log.debug("One Way flight Search response got successfully");
                response = new BaseResponseDTO<FlightSearchResultDTO>(flightConstants.SUCCESS_CODE, flightConstants.SUCCESS, interactionId, flightConstants.FLIGHT_SEARCH, flightSearchResultDTO);
                saveSearchResponseInCache(flightSearchRequestDTO, response);
            }else if(flightSearchRequestDTO.getFlightType().equalsIgnoreCase("ROUNDTRIP") && flightSearchResultDTO.getFlightResult().get(1).size() > 0 && flightSearchResultDTO.getFlightResult().get(0).size()  > 0) {
                log.debug("Round Trip flight Search response got successfully");
                response = new BaseResponseDTO<FlightSearchResultDTO>(flightConstants.SUCCESS_CODE, flightConstants.SUCCESS,interactionId, flightConstants.FLIGHT_SEARCH, flightSearchResultDTO);
                saveSearchResponseInCache(flightSearchRequestDTO, response);
            } else {
                response = new BaseResponseDTO<FlightSearchResultDTO>(flightConstants.FAILURE_CODE, flightConstants.NO_FLIGHT_FOUND_MSG, interactionId, flightConstants.FLIGHT_SEARCH, flightSearchResultDTO);
                log.debug("Error in Getting Flight Search results");
            }
        } catch (Exception e) {
            log.debug("Exception in Getting Flight Search Details");
            return new BaseResponseDTO<FlightSearchResultDTO>(flightConstants.EXCEPTION_CODE, flightConstants.FAILURE, interactionId, flightConstants.FLIGHT_SEARCH, flightSearchResultDTO);
        }
        log.info(response);
        return response;
    }

    private void getDateInFormat(FlightSearchRequestDTO flightSearchRequestDTO) {
        DateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat inputFormat = new SimpleDateFormat("yyyy-M-dd");
        Date date = null;
        try {
            date = inputFormat.parse(flightSearchRequestDTO.getOriginDepartDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        flightSearchRequestDTO.setOriginDepartDate(outputFormat.format(date));
    }

    @Override
    public FlightSearchRequestDTO mapAllParamsToDTOObject(String origin, String destination, String originDepartDate, String returnDate, int adults, int infants, int children, String flightType) {
        FlightSearchRequestDTO flightSearchRequestDTO = new FlightSearchRequestDTO();
        flightSearchRequestDTO.setOrigin(origin);
        flightSearchRequestDTO.setDestination(destination);
        flightSearchRequestDTO.setOriginDepartDate(originDepartDate);
        flightSearchRequestDTO.setReturnDepartDate(returnDate);
        flightSearchRequestDTO.setAdults(adults);
        flightSearchRequestDTO.setChildren(children);
        flightSearchRequestDTO.setInfants(infants);
        flightSearchRequestDTO.setFlightType(flightType);
        return  flightSearchRequestDTO;
    }


    private void saveSearchResponseInCache(FlightSearchRequestDTO flightSearchRequestDTO, BaseResponseDTO response) {
        baseResponseRepository.save(flightConstants.FLIGHT_CACHE_SET, flightSearchRequestDTO.toString(),  response);
    }


    public boolean flightSearchResponsePresentInCache(FlightSearchRequestDTO flightSearchRequestDTO) {
        BaseResponseDTO baseResponseDTO = (BaseResponseDTO)(baseResponseRepository.get(flightConstants.FLIGHT_CACHE_SET, flightSearchRequestDTO.toString()));
        if (baseResponseDTO!= null && baseResponseDTO.getResponse() != null) {
            return true;
        }
        return false;
    }

    public List<FlightSearchResponseDTO> getAllReturnTripFlights(FlightSearchRequestDTO flightSearchRequestDTO) {
        List<FlightInfo> returnWayFlightInfoResponses = flightInfoRepository.flightSearch(flightSearchRequestDTO.getDestination(),
                flightSearchRequestDTO.getOrigin(),
                flightSearchRequestDTO.getReturnDepartDate());
        log.debug("No of Flights in return Trip : " + returnWayFlightInfoResponses.size());
        List<FlightSearchResponse> returnWayFlightSearchResponses = getFlightSearchResp(returnWayFlightInfoResponses, flightSearchRequestDTO);
        return getFlightSearchResponseDTOs(returnWayFlightSearchResponses);
    }
/*
    public List<FlightSearchResponseDTO> getAllOneWayFlights(FlightSearchRequestDTO flightSearchRequestDTO) {
        List<FlightInfo> oneWayResponseList = getFlightFromVendor(flightSearchRequestDTO);
        log.debug("No of Flights in OneWay : " + oneWayResponseList.size());
        List<FlightSearchResponse> oneWayFlightSearchResponses = getFlightSearchResp(oneWayResponseList, flightSearchRequestDTO);
        return getFlightSearchResponseDTOs(oneWayFlightSearchResponses);
    }*/

   /* private List<FlightInfo> getFlightFromVendor(FlightSearchRequestDTO flightSearchRequestDTO) {

        https://api.sandbox.amadeus.com/v1.2/flights/low-fare-search?apikey=f4h35P7an10yOp639dGeKAdqUHy9cAVC&
        // origin=BOS&destination=LON&departure_date=2018-06-25&return_date=2018-08-04&children=1&infants=1&currency=INR

        List<FlightInfo> flightInfoList = new ArrayList<>();
        FlightSearchVendorRequestDTO flightSearchVendorRequestDTO = mapAllRequestParamsToVendorObj(flightSearchRequestDTO);
        HttpHeaders headers = new HttpHeaders();
        String uriString = "https://api.sandbox.amadeus.com/v1.2/flights/low-fare-search";
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.add("apiKey", flightSearchVendorRequestDTO.getApikey());
        headers.add("Accept-Encoding", "gzip");
        headers.add("Accept-Language", "en-IN");
        HttpEntity<FlightSearchVendorRequestDTO> entity = new HttpEntity<FlightSearchVendorRequestDTO>(flightSearchVendorRequestDTO, headers);
        JSONObject bookingResponse = restTemplate.exchange(uriString, HttpMethod.GET, entity, JSONObject.class).getBody();
        log.debug(bookingResponse);
        return flightInfoList;

    }

    private FlightSearchVendorRequestDTO mapAllRequestParamsToVendorObj(FlightSearchRequestDTO flightSearchRequestDTO) {
        FlightSearchVendorRequestDTO flightSearchVendorRequestDTO = new FlightSearchVendorRequestDTO();
        flightSearchVendorRequestDTO.setDeparture_date(flightSearchRequestDTO.getOriginDepartDate());
        flightSearchVendorRequestDTO.setReturn_date(flightSearchRequestDTO.getReturnDepartDate());
        flightSearchVendorRequestDTO.setOrigin(flightSearchRequestDTO.getOrigin());
        flightSearchVendorRequestDTO.setDestination(flightSearchRequestDTO.getDestination());
        flightSearchVendorRequestDTO.setAdults(flightSearchRequestDTO.getAdults());
        flightSearchVendorRequestDTO.setChildren(flightSearchRequestDTO.getChildren());
        flightSearchVendorRequestDTO.setInfants(flightSearchRequestDTO.getInfants());
        return flightSearchVendorRequestDTO;

    }*/
    public List<FlightSearchResponseDTO> getAllOneWayFlights(FlightSearchRequestDTO flightSearchRequestDTO) {
        List<FlightInfo> oneWayResponseList = flightInfoRepository.flightSearch(flightSearchRequestDTO.getOrigin(),
                flightSearchRequestDTO.getDestination(),
                flightSearchRequestDTO.getOriginDepartDate());
        log.debug("No of Flights in OneWay : " + oneWayResponseList.size());
        List<FlightSearchResponse> oneWayFlightSearchResponses = getFlightSearchResp(oneWayResponseList, flightSearchRequestDTO);
        return getFlightSearchResponseDTOs(oneWayFlightSearchResponses);
    }

    private List<FlightSearchResponse> getFlightSearchResp(List<FlightInfo> returnWayFlightSearchResponse, FlightSearchRequestDTO flightSearchRequestDTO) {
        List<FlightSearchResponse> flightSearchResponses = new ArrayList<>();
        for(FlightInfo flightInfo : returnWayFlightSearchResponse){
            FlightSearchResponse flightSearchResponse = new FlightSearchResponse();
            BeanUtils.copyProperties(flightInfo,flightSearchResponse);
            flightSearchResponse.setTotalPrice(getTotalCost(flightInfo, flightSearchRequestDTO));
            flightSearchResponses.add(flightSearchResponse);
        }
        return flightSearchResponses;
    }

    private String getTotalCost(FlightInfo flightInfo, FlightSearchRequestDTO flightSearchRequestDTO) {
         int adultCount =flightSearchRequestDTO.getAdults();
         int childCount = flightSearchRequestDTO.getChildren();
         int infantCount = flightSearchRequestDTO.getInfants();

        return String.valueOf((adultCount > 0 ? adultCount * Double.parseDouble(flightInfo.getPricePerAdult()) : 0.0)
                + (childCount > 0 ? childCount * Double.parseDouble(flightInfo.getPricePerChild()) : 0.0)
                + (infantCount > 0 ? infantCount * Double.parseDouble(flightInfo.getPricePerInfant()) : 0.0));
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
