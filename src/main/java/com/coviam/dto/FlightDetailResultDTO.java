package com.coviam.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class FlightDetailResultDTO implements Serializable{
    private String superPnr;
    private int totalPrice;
    private List<FlightDetailResponseDTO> detailResult;

    public String getSuperPnr() {
        return superPnr;
    }

    public void setSuperPnr(String superPnr) {
        this.superPnr = superPnr;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<FlightDetailResponseDTO> getDetailResult() {
        return detailResult;
    }

    public void setDetailResult(List<FlightDetailResponseDTO> detailResult) {
        this.detailResult = detailResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FlightDetailResultDTO)) return false;
        FlightDetailResultDTO that = (FlightDetailResultDTO) o;
        return Objects.equals(superPnr, that.superPnr) &&
                Objects.equals(totalPrice, that.totalPrice) &&
                Objects.equals(detailResult, that.detailResult);
    }

    @Override
    public int hashCode() {

        return Objects.hash(superPnr, totalPrice, detailResult);
    }

    @Override
    public String toString() {
        return "FlightDetailResultDTO{" +
                "superPnr='" + superPnr + '\'' +
                ", totalPrice='" + totalPrice + '\'' +
                ", detailResult=" + detailResult +
                '}';
    }
}
