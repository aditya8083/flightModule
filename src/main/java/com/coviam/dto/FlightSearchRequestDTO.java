package com.coviam.dto;

import java.io.Serializable;
import java.util.Objects;

public class FlightSearchRequestDTO implements Serializable{

        private String origin;
        private String destination;
        private String originDepartDate;
        private String returnDepartDate;
        private int adults;
        private int infants;
        private int children;
        private String flightType;

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

        public String getOriginDepartDate() {
                return originDepartDate;
        }

        public void setOriginDepartDate(String originDepartDate) {
                this.originDepartDate = originDepartDate;
        }

        public String getReturnDepartDate() {
                return returnDepartDate;
        }

        public void setReturnDepartDate(String returnDepartDate) {
                this.returnDepartDate = returnDepartDate;
        }

        public int getAdults() {
                return adults;
        }

        public void setAdults(int adults) {
                this.adults = adults;
        }

        public int getInfants() {
                return infants;
        }

        public void setInfants(int infants) {
                this.infants = infants;
        }

        public int getChildren() {
                return children;
        }

        public void setChildren(int children) {
                this.children = children;
        }

        public String getFlightType() {
                return flightType;
        }

        public void setFlightType(String flightType) {
                this.flightType = flightType;
        }


        @Override
        public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                FlightSearchRequestDTO that = (FlightSearchRequestDTO) o;
                return adults == that.adults &&
                        infants == that.infants &&
                        children == that.children &&
                        Objects.equals(origin, that.origin) &&
                        Objects.equals(destination, that.destination) &&
                        Objects.equals(originDepartDate, that.originDepartDate) &&
                        Objects.equals(returnDepartDate, that.returnDepartDate) &&
                        Objects.equals(flightType, that.flightType);
        }

        @Override
        public int hashCode() {

                return Objects.hash(origin, destination, originDepartDate, returnDepartDate, adults, infants, children, flightType);
        }

        @Override
        public String toString() {
                return "FlightSearchRequestDTO{" +
                        "origin='" + origin + '\'' +
                        ", destination='" + destination + '\'' +
                        ", originDepartDate='" + originDepartDate + '\'' +
                        ", returnDepartDate='" + returnDepartDate + '\'' +
                        ", adults=" + adults +
                        ", infants=" + infants +
                        ", children=" + children +
                        ", flightType='" + flightType + '\'' +
                        '}';
        }
}
