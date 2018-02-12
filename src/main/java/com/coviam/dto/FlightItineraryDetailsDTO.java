package com.coviam.dto;


import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class FlightItineraryDetailsDTO implements Serializable{
    private List<FlightDetailResponseDTO> flightDetailResponseDTOList;

    public List<FlightDetailResponseDTO> getFlightDetailResponseDTOList() {
        return flightDetailResponseDTOList;
    }

    public void setFlightDetailResponseDTOList(List<FlightDetailResponseDTO> flightDetailResponseDTOList) {
        this.flightDetailResponseDTOList = flightDetailResponseDTOList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FlightItineraryDetailsDTO)) return false;
        FlightItineraryDetailsDTO that = (FlightItineraryDetailsDTO) o;
        return Objects.equals(flightDetailResponseDTOList, that.flightDetailResponseDTOList);
    }

    @Override
    public int hashCode() {

        return Objects.hash(flightDetailResponseDTOList);
    }

    @Override
    public String toString() {
        return "FlightItineraryDetailsDTO{" +
                "flightDetailResponseDTOList=" + flightDetailResponseDTOList +
                '}';
    }
}
