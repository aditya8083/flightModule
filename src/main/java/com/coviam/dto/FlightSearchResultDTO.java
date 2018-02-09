package com.coviam.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class FlightSearchResultDTO implements Serializable{
    private List<FlightSearchResponseDTO> oneWay;
    private List<FlightSearchResponseDTO>  roundWay;

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
    }
}
