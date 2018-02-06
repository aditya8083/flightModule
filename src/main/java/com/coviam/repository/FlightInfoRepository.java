package com.coviam.repository;

import com.coviam.entity.FlightInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightInfoRepository  extends CrudRepository<FlightInfo, String>{

    @Query("select fi from FlightInfo fi where fi.origin = :origin AND fi.destination = :destination AND fi.originDepartDate = :originDepartDate")
    List<FlightInfo> flightSearch(@Param("origin") String origin, @Param("destination") String destination, @Param("originDepartDate") String originDepartDate);


    @Query("select fi from FlightInfo fi where fi.flightId = :flightId")
    FlightInfo getFlightInfo(@Param("flightId") String flightId);
}
