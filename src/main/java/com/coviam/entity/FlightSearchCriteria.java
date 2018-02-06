package com.coviam.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = FlightSearchCriteria.TABLE_NAME)
public class FlightSearchCriteria {
    public static final String TABLE_NAME = "FLIGHT_SEARCH_CRITERIA";
    private static final String ID_COLUMN = "ID";

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = FlightSearchCriteria.ID_COLUMN)
    private String superPnr;
    private String flightId;
    private String origin;
    private String destination;
    private String departDate;
    private String returnDate;
    private String flightType;
    private String adult;
    private String child;
    private String infant;

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

    public String getSuperPnr() {
        return superPnr;
    }

    public void setSuperPnr(String superPnr) {
        this.superPnr = superPnr;
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

    public String getAdult() {
        return adult;
    }

    public void setAdult(String adult) {
        this.adult = adult;
    }

    public String getChild() {
        return child;
    }

    public void setChild(String child) {
        this.child = child;
    }

    public String getInfant() {
        return infant;
    }

    public void setInfant(String infant) {
        this.infant = infant;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FlightSearchCriteria)) return false;
        FlightSearchCriteria that = (FlightSearchCriteria) o;
        return Objects.equals(flightId, that.flightId) &&
                Objects.equals(superPnr, that.superPnr) &&
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

        return Objects.hash(flightId, superPnr, origin, destination, departDate, returnDate, flightType, adult, child, infant);
    }

    @Override
    public String toString() {
        return "FlightSearchCriteria{" +
                "flightId='" + flightId + '\'' +
                ", superPnr='" + superPnr + '\'' +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", departDate='" + departDate + '\'' +
                ", returnDate='" + returnDate + '\'' +
                ", flightType='" + flightType + '\'' +
                ", adult='" + adult + '\'' +
                ", child='" + child + '\'' +
                ", infant='" + infant + '\'' +
                '}';
    }
}
