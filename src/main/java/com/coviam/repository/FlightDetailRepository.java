package com.coviam.repository;


import com.coviam.entity.FlightDetailResponse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightDetailRepository extends CrudRepository<FlightDetailResponse, String> {

}
