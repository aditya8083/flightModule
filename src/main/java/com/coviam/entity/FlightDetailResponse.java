package com.coviam.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = FlightDetailResponse.TABLE_NAME)
public class FlightDetailResponse {
    public static final String TABLE_NAME = "FLIGHT_DETAIL_RESPONSE";
    private static final String ID_COLUMN = "ID";

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = FlightDetailResponse.ID_COLUMN)
    private String id;
    private String flightId;
    private String origin;
    private String destination;
    private boolean isRefundable;
    private String originDepartDate;
    private String originDepartTime;
    private String destinationArrivalDate;
    private String destinationArrivalTime;
    private String flightName;
    private String flightNumber;
    private String seatRemain;
    private String pricePerAdult;
    private String pricePerChild;
    private String pricePerInfant;

    public static String getTableName() {
        return TABLE_NAME;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FlightDetailResponse)) return false;
        FlightDetailResponse that = (FlightDetailResponse) o;
        return isRefundable == that.isRefundable &&
                Objects.equals(flightId, that.flightId) &&
                Objects.equals(origin, that.origin) &&
                Objects.equals(destination, that.destination) &&
                Objects.equals(originDepartDate, that.originDepartDate) &&
                Objects.equals(originDepartTime, that.originDepartTime) &&
                Objects.equals(destinationArrivalDate, that.destinationArrivalDate) &&
                Objects.equals(destinationArrivalTime, that.destinationArrivalTime) &&
                Objects.equals(flightName, that.flightName) &&
                Objects.equals(flightNumber, that.flightNumber) &&
                Objects.equals(seatRemain, that.seatRemain) &&
                Objects.equals(pricePerAdult, that.pricePerAdult) &&
                Objects.equals(pricePerChild, that.pricePerChild) &&
                Objects.equals(pricePerInfant, that.pricePerInfant);
    }

    @Override
    public int hashCode() {

        return Objects.hash(flightId, origin, destination, isRefundable, originDepartDate, originDepartTime, destinationArrivalDate, destinationArrivalTime, flightName, flightNumber, seatRemain, pricePerAdult, pricePerChild, pricePerInfant);
    }


    @Override
    public String toString() {
        return "FlightDetailResponse{" +
                "flightId='" + flightId + '\'' +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", isRefundable=" + isRefundable +
                ", originDepartDate='" + originDepartDate + '\'' +
                ", originDepartTime='" + originDepartTime + '\'' +
                ", destinationArrivalDate='" + destinationArrivalDate + '\'' +
                ", destinationArrivalTime='" + destinationArrivalTime + '\'' +
                ", flightName='" + flightName + '\'' +
                ", flightNumber='" + flightNumber + '\'' +
                ", seatRemain='" + seatRemain + '\'' +
                ", pricePerAdult='" + pricePerAdult + '\'' +
                ", pricePerChild='" + pricePerChild + '\'' +
                ", pricePerInfant='" + pricePerInfant + '\'' +
                '}';
    }
}
