package com.coviam.dto;

import java.io.Serializable;
import java.util.Objects;

public class FlightSearchResponseDTO implements Serializable{

    private String flightId;
    private String origin;
    private String destination;
    private boolean isRefundable;
    private String originDepartDate;
    private String originDepartTime;
    private String destinationArrivalDate;
    private String destinationArrivalTime;
    private String flightCode;
    private String flightName;
    private String flightNumber;
    private String pricePerAdult;
    private boolean isHandBaggageFlight;
    private String baggageWeight;
    private boolean transitVisaRequired;

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

    public boolean isRefundable() {
        return isRefundable;
    }

    public void setRefundable(boolean refundable) {
        isRefundable = refundable;
    }

    public String getOriginDepartDate() {
        return originDepartDate;
    }

    public void setOriginDepartDate(String originDepartDate) {
        this.originDepartDate = originDepartDate;
    }

    public String getOriginDepartTime() {
        return originDepartTime;
    }

    public void setOriginDepartTime(String originDepartTime) {
        this.originDepartTime = originDepartTime;
    }

    public String getDestinationArrivalDate() {
        return destinationArrivalDate;
    }

    public void setDestinationArrivalDate(String destinationArrivalDate) {
        this.destinationArrivalDate = destinationArrivalDate;
    }

    public String getDestinationArrivalTime() {
        return destinationArrivalTime;
    }

    public void setDestinationArrivalTime(String destinationArrivalTime) {
        this.destinationArrivalTime = destinationArrivalTime;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getPricePerAdult() {
        return pricePerAdult;
    }

    public void setPricePerAdult(String pricePerAdult) {
        this.pricePerAdult = pricePerAdult;
    }

    public boolean isHandBaggageFlight() {
        return isHandBaggageFlight;
    }

    public void setHandBaggageFlight(boolean handBaggageFlight) {
        isHandBaggageFlight = handBaggageFlight;
    }

    public String getBaggageWeight() {
        return baggageWeight;
    }

    public void setBaggageWeight(String baggageWeight) {
        this.baggageWeight = baggageWeight;
    }

    public boolean isTransitVisaRequired() {
        return transitVisaRequired;
    }

    public void setTransitVisaRequired(boolean transitVisaRequired) {
        this.transitVisaRequired = transitVisaRequired;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FlightSearchResponseDTO)) return false;
        FlightSearchResponseDTO that = (FlightSearchResponseDTO) o;
        return isRefundable == that.isRefundable &&
                isHandBaggageFlight == that.isHandBaggageFlight &&
                transitVisaRequired == that.transitVisaRequired &&
                Objects.equals(flightId, that.flightId) &&
                Objects.equals(origin, that.origin) &&
                Objects.equals(destination, that.destination) &&
                Objects.equals(originDepartDate, that.originDepartDate) &&
                Objects.equals(originDepartTime, that.originDepartTime) &&
                Objects.equals(destinationArrivalDate, that.destinationArrivalDate) &&
                Objects.equals(destinationArrivalTime, that.destinationArrivalTime) &&
                Objects.equals(flightCode, that.flightCode) &&
                Objects.equals(flightName, that.flightName) &&
                Objects.equals(flightNumber, that.flightNumber) &&
                Objects.equals(pricePerAdult, that.pricePerAdult) &&
                Objects.equals(baggageWeight, that.baggageWeight);
    }

    @Override
    public int hashCode() {

        return Objects.hash(flightId, origin, destination, isRefundable, originDepartDate, originDepartTime, destinationArrivalDate, destinationArrivalTime, flightCode, flightName, flightNumber, pricePerAdult, isHandBaggageFlight, baggageWeight, transitVisaRequired);
    }

    @Override
    public String toString() {
        return "FlightSearchResponseDTO{" +
                "flightId='" + flightId + '\'' +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", isRefundable=" + isRefundable +
                ", originDepartDate='" + originDepartDate + '\'' +
                ", originDepartTime='" + originDepartTime + '\'' +
                ", destinationArrivalDate='" + destinationArrivalDate + '\'' +
                ", destinationArrivalTime='" + destinationArrivalTime + '\'' +
                ", flightCode='" + flightCode + '\'' +
                ", flightName='" + flightName + '\'' +
                ", flightNumber='" + flightNumber + '\'' +
                ", pricePerAdult='" + pricePerAdult + '\'' +
                ", isHandBaggageFlight=" + isHandBaggageFlight +
                ", baggageWeight='" + baggageWeight + '\'' +
                ", transitVisaRequired=" + transitVisaRequired +
                '}';
    }
}
