package com.coviam.dto;

import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Objects;

public class FlightDetailRequestDTO implements Serializable{
    private String flightId;
    private String origin;
    private String destination;
    private String departDate;
    private String returnDate;
    private String flightType;
    private int adult;
    private int child;
    private int infant;
    private boolean doGenerate;

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

    public boolean isDoGenerate() {
        return doGenerate;
    }

    public void setDoGenerate(boolean doGenerate) {
        this.doGenerate = doGenerate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FlightDetailRequestDTO)) return false;
        FlightDetailRequestDTO that = (FlightDetailRequestDTO) o;
        return adult == that.adult &&
                child == that.child &&
                infant == that.infant &&
                doGenerate == that.doGenerate &&
                Objects.equals(flightId, that.flightId) &&
                Objects.equals(origin, that.origin) &&
                Objects.equals(destination, that.destination) &&
                Objects.equals(departDate, that.departDate) &&
                Objects.equals(returnDate, that.returnDate) &&
                Objects.equals(flightType, that.flightType);
    }

    @Override
    public int hashCode() {

        return Objects.hash(flightId, origin, destination, departDate, returnDate, flightType, adult, child, infant, doGenerate);
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
                ", adult=" + adult +
                ", child=" + child +
                ", infant=" + infant +
                ", doGenerate=" + doGenerate +
                '}';
    }
}



