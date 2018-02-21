package com.coviam.dto;

import java.io.Serializable;
import java.util.Objects;

public class FlightSearchVendorRequestDTO implements Serializable {

    private String apikey = "f4h35P7an10yOp639dGeKAdqUHy9cAVC";
    private String origin;
    private String destination;
    private String departure_date;
    private String return_date;
    private int adults;
    private int children;
    private int infants;
    private String currency = "INR";

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
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

    public String getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(String departure_date) {
        this.departure_date = departure_date;
    }

    public String getReturn_date() {
        return return_date;
    }

    public void setReturn_date(String return_date) {
        this.return_date = return_date;
    }

    public int getAdults() {
        return adults;
    }

    public void setAdults(int adults) {
        this.adults = adults;
    }

    public int getChildren() {
        return children;
    }

    public void setChildren(int children) {
        this.children = children;
    }

    public int getInfants() {
        return infants;
    }

    public void setInfants(int infants) {
        this.infants = infants;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FlightSearchVendorRequestDTO)) return false;
        FlightSearchVendorRequestDTO that = (FlightSearchVendorRequestDTO) o;
        return children == that.children &&
                infants == that.infants &&
                Objects.equals(apikey, that.apikey) &&
                Objects.equals(origin, that.origin) &&
                Objects.equals(destination, that.destination) &&
                Objects.equals(departure_date, that.departure_date) &&
                Objects.equals(return_date, that.return_date) &&
                Objects.equals(adults, that.adults) &&
                Objects.equals(currency, that.currency);
    }

    @Override
    public int hashCode() {

        return Objects.hash(apikey, origin, destination, departure_date, return_date, adults, children, infants, currency);
    }

    @Override
    public String toString() {
        return "FlightSearchVendorRequestDTO{" +
                "apikey='" + apikey + '\'' +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", departure_date='" + departure_date + '\'' +
                ", return_date='" + return_date + '\'' +
                ", adults='" + adults + '\'' +
                ", children=" + children +
                ", infants=" + infants +
                ", currency='" + currency + '\'' +
                '}';
    }
}
