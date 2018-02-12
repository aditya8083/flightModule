package com.coviam.dto;

import java.io.Serializable;
import java.util.Objects;

public class FlightDetailResponseDTO implements Serializable{
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
    private String seatRemain;
    private String pricePerAdult;
    private String pricePerChild;
    private String pricePerInfant;
    private String originAirportName;
    private String originTerminal;
    private String destinationAirportName;
    private String destinationTerminal;
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

    public String getSeatRemain() {
        return seatRemain;
    }

    public void setSeatRemain(String seatRemain) {
        this.seatRemain = seatRemain;
    }

    public String getPricePerAdult() {
        return pricePerAdult;
    }

    public void setPricePerAdult(String pricePerAdult) {
        this.pricePerAdult = pricePerAdult;
    }

    public String getPricePerChild() {
        return pricePerChild;
    }

    public void setPricePerChild(String pricePerChild) {
        this.pricePerChild = pricePerChild;
    }

    public String getPricePerInfant() {
        return pricePerInfant;
    }

    public void setPricePerInfant(String pricePerInfant) {
        this.pricePerInfant = pricePerInfant;
    }

    public String getOriginAirportName() {
        return originAirportName;
    }

    public void setOriginAirportName(String originAirportName) {
        this.originAirportName = originAirportName;
    }

    public String getDestinationAirportName() {
        return destinationAirportName;
    }

    public void setDestinationAirportName(String destinationAirportName) {
        this.destinationAirportName = destinationAirportName;
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

    public String getOriginTerminal() {
        return originTerminal;
    }

    public void setOriginTerminal(String originTerminal) {
        this.originTerminal = originTerminal;
    }

    public String getDestinationTerminal() {
        return destinationTerminal;
    }

    public void setDestinationTerminal(String destinationTerminal) {
        this.destinationTerminal = destinationTerminal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FlightDetailResponseDTO)) return false;
        FlightDetailResponseDTO that = (FlightDetailResponseDTO) o;
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
                Objects.equals(seatRemain, that.seatRemain) &&
                Objects.equals(pricePerAdult, that.pricePerAdult) &&
                Objects.equals(pricePerChild, that.pricePerChild) &&
                Objects.equals(pricePerInfant, that.pricePerInfant) &&
                Objects.equals(originAirportName, that.originAirportName) &&
                Objects.equals(originTerminal, that.originTerminal) &&
                Objects.equals(destinationAirportName, that.destinationAirportName) &&
                Objects.equals(destinationTerminal, that.destinationTerminal) &&
                Objects.equals(baggageWeight, that.baggageWeight);
    }

    @Override
    public int hashCode() {

        return Objects.hash(flightId, origin, destination, isRefundable, originDepartDate, originDepartTime, destinationArrivalDate, destinationArrivalTime, flightCode, flightName, flightNumber, seatRemain, pricePerAdult, pricePerChild, pricePerInfant, originAirportName, originTerminal, destinationAirportName, destinationTerminal, isHandBaggageFlight, baggageWeight, transitVisaRequired);
    }


    @Override
    public String toString() {
        return "FlightDetailResponseDTO{" +
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
                ", seatRemain='" + seatRemain + '\'' +
                ", pricePerAdult='" + pricePerAdult + '\'' +
                ", pricePerChild='" + pricePerChild + '\'' +
                ", pricePerInfant='" + pricePerInfant + '\'' +
                ", originAirportName='" + originAirportName + '\'' +
                ", originTerminal='" + originTerminal + '\'' +
                ", destinationAirportName='" + destinationAirportName + '\'' +
                ", destinationTerminal='" + destinationTerminal + '\'' +
                ", isHandBaggageFlight=" + isHandBaggageFlight +
                ", baggageWeight='" + baggageWeight + '\'' +
                ", transitVisaRequired=" + transitVisaRequired +
                '}';
    }
}
