package com.coviam.dto;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class FlightDetailRequestDTO {
    private String flightId;
    private String origin;
    private String destination;
    private String departDate;
    private String returnDate;
    private String flightType;
    private int adult;
    private int child;
    private int infant;

    public String getFlightId() {
        return flightId;
    }

    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDepartDate() {
        return departDate;
    }

    public void setDepartDate(String departDate) {
        this.departDate = departDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getFlightType() {
        return flightType;
    }

    public void setFlightType(String flightType) {
        this.flightType = flightType;
    }

    public int getAdult() {
        return adult;
    }

    public void setAdult(int adult) {
        this.adult = adult;
    }

    public int getChild() {
        return child;
    }

    public void setChild(int child) {
        this.child = child;
    }

    public int getInfant() {
        return infant;
    }

    public void setInfant(int infant) {
        this.infant = infant;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FlightDetailRequestDTO)) return false;
        FlightDetailRequestDTO that = (FlightDetailRequestDTO) o;
        return Objects.equals(flightId, that.flightId) &&
                Objects.equals(origin, that.origin) &&
                Objects.equals(destination, that.destination) &&
                Objects.equals(departDate, that.departDate) &&
                Objects.equals(returnDate, that.returnDate) &&
                Objects.equals(flightType, that.flightType) &&
                Objects.equals(adult, that.adult) &&
                Objects.equals(child, that.child) &&
                Objects.equals(infant, that.infant);
    }

    @Override
    public int hashCode() {

        return Objects.hash(flightId, origin, destination, departDate, returnDate, flightType, adult, child, infant);
    }

    @Override
    public String toString() {
        return "FlightDetailRequestDTO{" +
                "flightId='" + flightId + '\'' +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", departDate='" + departDate + '\'' +
                ", returnDate='" + returnDate + '\'' +
                ", flightType='" + flightType + '\'' +
                ", adult='" + adult +
                ", child='" + child +
                ", infant='" + infant +
                '}';
    }
}



