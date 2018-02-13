package com.coviam.controller;


import com.coviam.dto.BaseResponseDTO;
import com.coviam.dto.FlightDetailRequestDTO;
import com.coviam.services.FlightDetailService;
import com.coviam.services.FlightItineraryService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin("*")
@RequestMapping("/flight")
public class FlightDetailController {

    @Autowired
    FlightDetailService flightDetailService;
    @Autowired
    FlightItineraryService flightItineraryService;
    @Autowired
    Logger log;

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public ResponseEntity<BaseResponseDTO> flightDetail(@RequestParam String flightId, @RequestParam String origin, @RequestParam String destination,
                                                        @RequestParam String originDepartDate, @RequestParam String destinationArrivalDate,
                                                        @RequestParam int adults, @RequestParam int infants, @RequestParam int children,
                                                        @RequestParam String flightType,@RequestParam boolean doGenerate,  HttpServletRequest request) {
        FlightDetailRequestDTO flightDetailRequestDTO = flightDetailService.mapAllParamsToDTOObject( flightId, origin, destination, originDepartDate, destinationArrivalDate, adults, infants, children, flightType, doGenerate);
        log.debug("Getting Flight Detail");
        String interactionId = (String)request.getSession().getAttribute("interactionId");
        BaseResponseDTO flightDetailResult = flightDetailService.getFlightDetail(flightDetailRequestDTO, interactionId);
        return new ResponseEntity<BaseResponseDTO>(flightDetailResult, HttpStatus.OK);
    }

    @RequestMapping(value = "/getFlightItinerary", method = RequestMethod.GET)
    public ResponseEntity<BaseResponseDTO> getFlightItinerary(@RequestParam(value = "superPnr", required = true) String superPnr, HttpServletRequest request) {
        log.debug("Getting Flight Itinerary details");
        String interactionId = (String)request.getSession().getAttribute("interactionId");
        BaseResponseDTO flightItineraryResponse = flightItineraryService.getFlightItinerary(superPnr, interactionId);
        return new ResponseEntity<BaseResponseDTO>(flightItineraryResponse, HttpStatus.OK);
    }


}
