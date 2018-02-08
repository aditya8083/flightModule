package com.coviam.services;

import com.coviam.dto.BaseResponseDTO;
import com.coviam.dto.FlightDetailRequestDTO;


public interface FlightDetailService {

    public BaseResponseDTO getFlightDetail(FlightDetailRequestDTO flightDetailRequestDTO, String interactionId);


    FlightDetailRequestDTO mapAllParamsToDTOObject(String flightId, String origin, String destination, String originDepartDate, String destinationArrivalDate, int adults, int infants, int children, String flightType);
}

