package com.coviam.repository;


import com.coviam.entity.FlightInfo;
import com.coviam.entity.FlightSearchResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightSearchRepository extends CrudRepository<FlightSearchResponse, String> {


}
