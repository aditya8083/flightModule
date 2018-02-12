package com.coviam.controller;

import com.coviam.dto.BaseResponseDTO;
import com.coviam.dto.FlightInfoDTO;
import com.coviam.dto.FlightSearchRequestDTO;
import com.coviam.entity.FlightInfo;
import com.coviam.services.FlightSearchService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@CrossOrigin("*")
@RequestMapping("/flight")
public class FlightSearchController {

    @Autowired
    FlightSearchService flightSearchService;
    @Autowired
    Logger log;

    @RequestMapping(method = RequestMethod.GET, value = "/search")
    public ResponseEntity<BaseResponseDTO> flightSearch(@RequestParam String origin, @RequestParam String destination,
                                                        @RequestParam String originDepartDate, @RequestParam String returnDate,
                                                        @RequestParam int adults, @RequestParam int infants, @RequestParam int children,
                                                        @RequestParam String flightType, HttpServletRequest request){
        log.debug("Getting all Flights");
        FlightSearchRequestDTO flightSearchRequestDTO = flightSearchService.mapAllParamsToDTOObject(origin, destination, originDepartDate, returnDate, adults, infants, children, flightType);
        log.debug(flightSearchRequestDTO);
        String interactionId = (String)request.getSession().getAttribute("interactionId");
        BaseResponseDTO baseResponseDTO = flightSearchService.getAllFlights(flightSearchRequestDTO, interactionId);
        return new ResponseEntity<BaseResponseDTO>(baseResponseDTO, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/saveFlightSearchResponse")
    public ResponseEntity<FlightInfo> saveFlightSearchResponse(@RequestBody FlightInfoDTO flightInfoDTO) {
        log.debug(flightInfoDTO);
        FlightInfo flightInfo = new FlightInfo();
        BeanUtils.copyProperties(flightInfoDTO, flightInfo);
        FlightInfo flightInfoSaved = flightSearchService.save(flightInfo);
        return new ResponseEntity<FlightInfo>(flightInfoSaved,HttpStatus.CREATED);
    }

}