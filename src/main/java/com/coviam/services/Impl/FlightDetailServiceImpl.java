package com.coviam.services.Impl;


import com.coviam.dto.FlightDetailRequestDTO;
import com.coviam.dto.FlightDetailResponseDTO;
import com.coviam.entity.FlightInfo;
import com.coviam.entity.FlightSearchCriteria;
import com.coviam.repository.FlightCriteriaRepository;
import com.coviam.repository.FlightDetailRepository;
import com.coviam.repository.FlightInfoRepository;
import com.coviam.services.FlightDetailService;
import com.coviam.util.*;
import com.google.gson.Gson;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightDetailServiceImpl implements FlightDetailService{

    @Autowired
    RandomGenerator randomGenerator;
    @Autowired
    FlightConstants flightConstants;
    @Autowired
    EscapeCharacter escapeCharacter;
    @Autowired
    FlightDetailRepository flightDetailRepository;
    @Autowired
    FlightCriteriaRepository flightCriteriaRepository;
    @Autowired
    FlightInfoRepository flightInfoRepository;
    @Autowired
    CommonUtil commonUtil;



    public String getFlightDetail(FlightDetailRequestDTO flightDetailRequestDTO ) {
        String flightPricingIds = flightDetailRequestDTO.getFlightId();
        JSONObject flightPricingRespObj = new JSONObject();
        String response = "";
        try {
            String superPnr = randomGenerator.generateRandomString();
            saveFlightSearchCriteria(flightDetailRequestDTO, superPnr);
            JSONArray flightResArr = getFlightItineraryDetails(flightPricingIds);
            System.out.println(flightResArr);

            flightPricingRespObj.put("details_result", flightResArr);
            flightPricingRespObj.put("superPnr", superPnr);
            flightPricingRespObj.put("totalPrice", getTotalPrice(flightResArr, flightDetailRequestDTO));

            if (flightPricingRespObj.getJSONArray("details_result").length() != 0 && (flightPricingRespObj.getInt("totalPrice")) > 0) {
                System.out.println("Flight Detail response got successfully");
                response =  commonUtil.successResponse(flightPricingRespObj,flightConstants.FLIGHT_DETAIL, randomGenerator.generateRandomString() );
            } else {
                response =  commonUtil.errorResponse(flightConstants.FAILURE_CODE, flightConstants.FAILURE, randomGenerator.generateRandomString(), flightConstants.FLIGHT_DETAIL);
                System.out.println("Error in Getting Flight Details");
            }
        } catch (Exception e) {
            System.out.println("Exception in Getting Flight Details");
            return escapeCharacter.escapeCharacter(new JSONObject(new ResponseDeco(flightConstants.EXCEPTION_CODE, flightConstants.FAILURE, randomGenerator.generateRandomString(),
                    flightConstants.FLIGHT_DETAIL, new JSONObject())).toString());
        }
        return escapeCharacter.escapeCharacter(response);
    }

    private void saveFlightSearchCriteria(FlightDetailRequestDTO flightDetailRequestDTO, String superPnr) {
        FlightSearchCriteria flightSearchCriteria = new FlightSearchCriteria();
        BeanUtils.copyProperties(flightDetailRequestDTO, flightSearchCriteria);
        flightCriteriaRepository.save(flightSearchCriteria);
    }

    private int getTotalPrice(JSONArray flightPriceResArr, FlightDetailRequestDTO flightDetailRequestDTO) {
        int totalAmount = 0;
        int adultCount = flightDetailRequestDTO.getAdult();
        int childCount = flightDetailRequestDTO.getChild();
        int infantCount = flightDetailRequestDTO.getInfant();
        try {
            if (flightPriceResArr.length() > 0) {
                JSONObject onGoingFlightObj = flightPriceResArr.getJSONObject(0);
                totalAmount = getTotalAmount(onGoingFlightObj, adultCount, childCount,infantCount);
                if (flightPriceResArr.length() > 1) {
                    JSONObject returnFlightObj = flightPriceResArr.getJSONObject(1);
                    totalAmount += getTotalAmount(returnFlightObj, adultCount, childCount, infantCount);
                }
            }
        } catch (Exception e) {
            System.out.println("Getting Exception in calculating the total Amount");
            return 0;
        }
        return totalAmount;
    }

    private int getTotalAmount(JSONObject flightResponseObj, int adultCount, int childCount, int infantCount) {
        return (int) ((adultCount > 0 ? adultCount * Double.parseDouble(flightResponseObj.getString("pricePerAdult")) : 0.0)
                + (childCount > 0 ? childCount * Double.parseDouble(flightResponseObj.getString("pricePerChild")) : 0.0)
                + (infantCount > 0 ? infantCount * Double.parseDouble(flightResponseObj.getString("pricePerInfant")) : 0.0));

    }

    private FlightDetailRequestDTO getFlightPricingRequestParams(HttpServletRequest request) {
        FlightDetailRequestDTO flightDetailRequestDTO = new FlightDetailRequestDTO();
        flightDetailRequestDTO.setFlightId((request.getParameter("flightId")));
        return flightDetailRequestDTO;

    }

    public String getFlightItinerary(String superPnr) {
        String response;
        JSONObject flightItineraryRespObj = new JSONObject();
        try {
            String itineraryFlightId = getFlightIDsForItinerary(superPnr);
            JSONArray flightItineraryDetails = getFlightItineraryDetails(itineraryFlightId);
            flightItineraryRespObj.put("itineraryDetails", flightItineraryDetails);
            if (flightItineraryRespObj.getJSONArray("itineraryDetails").length() != 0) {
                System.out.println("Flight Itinerary Detail response got successfully");
                response = commonUtil.successResponse(flightItineraryRespObj,flightConstants.FLIGHT_ITINERARY_RESPONSE, randomGenerator.generateRandomString());
            } else {
                response = commonUtil.errorResponse(flightConstants.FAILURE_CODE, flightConstants.FAILURE, randomGenerator.generateRandomString(), flightConstants.FLIGHT_ITINERARY_RESPONSE);
            }
        } catch (Exception e) {
            System.out.println("Getting Exception in getting the flight Itinerary Details from Database");
            return escapeCharacter.escapeCharacter(new JSONObject(new ResponseDeco(flightConstants.EXCEPTION_CODE, flightConstants.FAILURE, randomGenerator.generateRandomString(),
                    flightConstants.FLIGHT_ITINERARY_RESPONSE, new JSONObject())).toString());
        }
        return escapeCharacter.escapeCharacter(response);
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

    private JSONArray getFlightItineraryDetails(String itineraryFlightId) {
        Gson gsonObj = new Gson();
        JSONArray flightItineraryDetails = new JSONArray();
        String flightPricingIds[] = itineraryFlightId.split(",");
        String onGoingTripFlightId = flightPricingIds[0];
        FlightInfo OnGoingFlightInfoRes = flightInfoRepository.getFlightInfo(onGoingTripFlightId);
        FlightDetailResponseDTO OnGoingFlightDetailDTORes = new FlightDetailResponseDTO();
        BeanUtils.copyProperties(OnGoingFlightInfoRes, OnGoingFlightDetailDTORes);
        flightItineraryDetails.put(new JSONObject(gsonObj.toJson(OnGoingFlightDetailDTORes)));

        if (flightPricingIds.length > 1) {
            String returnTripFlightId = flightPricingIds[1];
            FlightInfo returnFlightInfoRes = flightInfoRepository.getFlightInfo(returnTripFlightId);
            FlightDetailResponseDTO returnFlightDetailDTORes = new FlightDetailResponseDTO();
            BeanUtils.copyProperties(returnFlightInfoRes, returnFlightDetailDTORes);
            flightItineraryDetails.put(new JSONObject(gsonObj.toJson(returnFlightDetailDTORes)));
        }
        return flightItineraryDetails;
    }

    public String getFlightIDsForItinerary(String superPnr) {
        FlightSearchCriteria flightSearchCriteria = flightCriteriaRepository.getFlightIdBySuperPnr(superPnr);
        return flightSearchCriteria.getFlightId();
    }

}
