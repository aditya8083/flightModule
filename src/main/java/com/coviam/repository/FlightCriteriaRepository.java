package com.coviam.repository;

import com.coviam.entity.FlightSearchCriteria;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightCriteriaRepository extends CrudRepository<FlightSearchCriteria,String>{

    @Query("select fsc from FlightSearchCriteria fsc where fsc.id = :superPnr")
    FlightSearchCriteria getFlightIdBySuperPnr(@Param("superPnr") String superPnr);

}
