package com.coviam.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class FlightSearchResultDTO implements Serializable{
    private List<List<FlightSearchResponseDTO>> flightResult;

    public List<List<FlightSearchResponseDTO>> getFlightResult() {
        return flightResult;
    }

    public void setFlightResult(List<List<FlightSearchResponseDTO>> flightResult) {
        this.flightResult = flightResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FlightSearchResultDTO)) return false;
        FlightSearchResultDTO that = (FlightSearchResultDTO) o;
        return Objects.equals(flightResult, that.flightResult);
    }

    @Override
    public int hashCode() {

        return Objects.hash(flightResult);
    }

    @Override
    public String toString() {
        return "FlightSearchResultDTO{" +
                "flightResult=" + flightResult +
                '}';
    }


    /*private List<FlightSearchResponseDTO>  roundWay;

    public List<FlightSearchResponseDTO> getOneWay() {
        return oneWay;
    }

    public void setOneWay(List<FlightSearchResponseDTO> oneWay) {
        this.oneWay = oneWay;
    }

    public List<FlightSearchResponseDTO> getRoundWay() {
        return roundWay;
    }

    public void setRoundWay(List<FlightSearchResponseDTO> roundWay) {
        this.roundWay = roundWay;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FlightSearchResultDTO)) return false;
        FlightSearchResultDTO that = (FlightSearchResultDTO) o;
        return Objects.equals(oneWay, that.oneWay) &&
                Objects.equals(roundWay, that.roundWay);
    }

    @Override
    public int hashCode() {

        return Objects.hash(oneWay, roundWay);
    }

    @Override
    public String toString() {
        return "FlightSearchResultDTO{" +
                "oneWay=" + oneWay +
                ", roundWay=" + roundWay +
                '}';
    }*/
}
