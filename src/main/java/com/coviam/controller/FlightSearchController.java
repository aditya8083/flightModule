package com.coviam.controller;

import com.coviam.dto.BaseResponseDTO;
import com.coviam.dto.FlightInfoDTO;
import com.coviam.dto.FlightSearchRequestDTO;
import com.coviam.entity.FlightInfo;
import com.coviam.services.FlightSearchService;
import com.coviam.util.RandomDateGenerator;
import com.coviam.util.RandomGenerator;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin("*")
@RequestMapping("/flight")
public class FlightSearchController {

    @Autowired
    FlightSearchService flightSearchService;
    @Autowired
    Logger log;
    @Autowired
    RandomGenerator randomGenerator;
    @Autowired
    RandomDateGenerator randomDateGenerator;

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    public ResponseEntity<BaseResponseDTO> flightSearch(@RequestParam String origin, @RequestParam String destination,
                                                        @RequestParam String originDepartDate, @RequestParam String returnDate,
                                                        @RequestParam int adults, @RequestParam int infants, @RequestParam int children,
                                                        @RequestParam String flightType, HttpServletRequest request){
        log.debug("Getting all Flights");
        FlightSearchRequestDTO flightSearchRequestDTO = flightSearchService.mapAllParamsToDTOObject(origin.toUpperCase(), destination.toUpperCase(), originDepartDate, returnDate, adults, infants, children, flightType);
        log.debug(flightSearchRequestDTO);
        String interactionId = (String)request.getSession().getAttribute("interactionId");
        BaseResponseDTO baseResponseDTO = flightSearchService.getAllFlights(flightSearchRequestDTO, interactionId);
        return new ResponseEntity<BaseResponseDTO>(baseResponseDTO, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveFlightSearchResponse")
    public ResponseEntity<String> saveFlightSearchResponse(@RequestBody FlightInfoDTO flightInfoDTO, HttpServletRequest request) {

        log.debug(flightInfoDTO);
        for(int i =0 ;i < 100000 ; i++){
            String origin = randomGenerator.generateRandomString();
            String destination = randomGenerator.generateRandomString();
            String originDepartDate = randomDateGenerator.getRandomDate();
            String destinationArrivalDate = randomDateGenerator.getRandomDate();
            FlightInfo flightInfo = new FlightInfo();
            BeanUtils.copyProperties(flightInfoDTO, flightInfo);
            flightInfo.setOrigin(origin);
            flightInfo.setDestination(destination);
            flightInfo.setOriginDepartDate(originDepartDate);
            flightInfo.setDestinationArrivalDate(destinationArrivalDate);
            FlightInfo flightInfoSaved = flightSearchService.save(flightInfo);
            FlightSearchRequestDTO flightSearchRequestDTO = flightSearchService.mapAllParamsToDTOObject(origin.toUpperCase(), destination.toUpperCase(), originDepartDate, destinationArrivalDate, 1, 1, 1, "ONEWAY");
            log.debug(flightSearchRequestDTO);
            String interactionId = (String)request.getSession().getAttribute("interactionId");
            BaseResponseDTO baseResponseDTO = flightSearchService.getAllFlights(flightSearchRequestDTO, interactionId);

        }

        return new ResponseEntity<String>("Success",HttpStatus.CREATED);
    }

}