package com.coviam.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = FlightSearchResponse.TABLE_NAME)
public class FlightSearchResponse  implements Serializable{
    public static final String TABLE_NAME = "FLIGHT_SEARCH_RESPONSE";
    private static final String ID_COLUMN = "ID";

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = FlightSearchResponse.ID_COLUMN)
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
    private String pricePerAdult;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public static String getTableName() {
        return TABLE_NAME;
    }

    public static String getIdColumn() {
        return ID_COLUMN;
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

    public String getpricePerAdult() {
        return pricePerAdult;
    }

    public void setpricePerAdult(String pricePerAdult) {
        this.pricePerAdult = pricePerAdult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FlightSearchResponse)) return false;
        FlightSearchResponse that = (FlightSearchResponse) o;
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
                Objects.equals(pricePerAdult, that.pricePerAdult);
    }

    @Override
    public int hashCode() {

        return Objects.hash(flightId, origin, destination, isRefundable, originDepartDate, originDepartTime, destinationArrivalDate, destinationArrivalTime, flightName, flightNumber, pricePerAdult);
    }

    @Override
    public String toString() {
        return "FlightSearchResponse{" +
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
                ", pricePerAdult='" + pricePerAdult + '\'' +
                '}';
    }
}
