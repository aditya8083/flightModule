package com.coviam.services;

import com.coviam.dto.FlightSearchRequestDTO;
import com.coviam.entity.FlightInfo;
import com.coviam.entity.FlightSearchResponse;

public interface FlightSearchService {

        public FlightInfo save(FlightInfo flightInfo);

        public String getAllFlights(FlightSearchRequestDTO flightSearchRequestDTO) ;

        FlightSearchRequestDTO mapAllParamsToDTOObject(String origin, String destination, String originDepartDate, String destinationArrivalDate, int adults, int infants, int children, String flightType);
}
