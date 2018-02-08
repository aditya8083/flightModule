package com.coviam.services;

import com.coviam.dto.BaseResponseDTO;

public interface FlightItineraryService {

    public BaseResponseDTO getFlightItinerary(String superPnr, String interactionId);
}
