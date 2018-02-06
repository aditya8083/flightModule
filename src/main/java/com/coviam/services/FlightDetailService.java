package com.coviam.services;

import com.coviam.dto.FlightDetailRequestDTO;


public interface FlightDetailService {

    public String getFlightDetail(FlightDetailRequestDTO flightDetailRequestDTO);

    public String getFlightItinerary(String superPnr);

    FlightDetailRequestDTO mapAllParamsToDTOObject(String flightId, String origin, String destination, String originDepartDate, String destinationArrivalDate, int adults, int infants, int children, String flightType);
}

