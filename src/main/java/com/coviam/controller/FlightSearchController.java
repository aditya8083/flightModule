package com.coviam.controller;

import com.coviam.dto.FlightInfoDTO;
import com.coviam.dto.FlightSearchRequestDTO;
import com.coviam.dto.FlightSearchResponseDTO;
import com.coviam.entity.FlightInfo;
import com.coviam.entity.FlightSearchResponse;
import com.coviam.services.FlightSearchService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flight")
public class FlightSearchController {

    @Autowired
    FlightSearchService flightSearchService;


    @RequestMapping(method = RequestMethod.GET, value = "/search")
    public ResponseEntity<String> flightSearch(@RequestParam String origin, @RequestParam String destination,
                                               @RequestParam String originDepartDate, @RequestParam String destinationArrivalDate,
                                               @RequestParam int adults, @RequestParam int infants, @RequestParam int children,
                                               @RequestParam String flightType){
        System.out.println("Getting all Flights");
        FlightSearchRequestDTO flightSearchRequestDTO = flightSearchService.mapAllParamsToDTOObject(origin, destination, originDepartDate, destinationArrivalDate,
                                                          adults, infants, children, flightType);
        System.out.println(flightSearchRequestDTO);
        String searchResponse = flightSearchService.getAllFlights(flightSearchRequestDTO);
        return new ResponseEntity<String>(searchResponse, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveFlightSearchResponse")
    public ResponseEntity<String> saveFlightSearchResponse(@RequestBody FlightInfoDTO flightInfoDTO) {
        System.out.println(flightInfoDTO);
        FlightInfo flightInfo = new FlightInfo();
        BeanUtils.copyProperties(flightInfoDTO, flightInfo);
        FlightInfo flightInfoSaved = flightSearchService.save(flightInfo);
        return new ResponseEntity<String>(flightInfoSaved.getFlightId(),HttpStatus.CREATED);
    }

}