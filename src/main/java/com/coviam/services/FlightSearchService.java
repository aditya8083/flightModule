package com.coviam.services;

import com.coviam.dto.BaseResponseDTO;
import com.coviam.dto.FlightSearchRequestDTO;
import com.coviam.entity.FlightInfo;

public interface FlightSearchService {

        public FlightInfo save(FlightInfo flightInfo);

        public BaseResponseDTO getAllFlights(FlightSearchRequestDTO flightSearchRequestDTO, String interactionId) ;

        FlightSearchRequestDTO mapAllParamsToDTOObject(String origin, String destination, String originDepartDate, String returnDate, int adults, int infants, int children, String flightType);
}
