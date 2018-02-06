package com.coviam.controller;


import com.coviam.dto.FlightDetailRequestDTO;
import com.coviam.dto.FlightSearchRequestDTO;
import com.coviam.services.FlightDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flight")
public class FlightDetailController {

    @Autowired
    FlightDetailService flightDetailService;

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public ResponseEntity<String> flightDetail(@RequestParam String flightId, @RequestParam String origin, @RequestParam String destination,
                                               @RequestParam String originDepartDate, @RequestParam String destinationArrivalDate,
                                               @RequestParam int adults, @RequestParam int infants, @RequestParam int children,
                                               @RequestParam String flightType) {
        FlightDetailRequestDTO flightDetailRequestDTO = flightDetailService.mapAllParamsToDTOObject( flightId, origin, destination, originDepartDate, destinationArrivalDate,
                                                        adults, infants, children, flightType);
        System.out.println("Getting Flight Detail");
        String detailResponse = flightDetailService.getFlightDetail(flightDetailRequestDTO);
        return new ResponseEntity<String>(detailResponse, HttpStatus.OK);
    }

    @RequestMapping(value = "/getFlightItinerary", method = RequestMethod.GET)
    public ResponseEntity<String> getFlightItinerary(@RequestParam(value = "superPnr", required = true) String superPnr) {
        System.out.println("Getting Flight Itinerary details");
        String flightItineraryResponse = flightDetailService.getFlightItinerary(superPnr);
        return new ResponseEntity<String>(flightItineraryResponse, HttpStatus.OK);
    }


}
