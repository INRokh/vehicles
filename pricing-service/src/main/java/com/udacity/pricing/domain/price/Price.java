package com.udacity.pricing.domain.price;

import javax.persistence.Entity;
import java.math.BigDecimal;
import javax.persistence.Id;

/**
 * Represents the price of a given vehicle, including currency.
 */
@Entity
public class Price {
    @Id
    private Long id;
    private Long vehicleId;

    private String currency;
    private BigDecimal price;


    public Price() {
    }

    public Price(Long id, String currency, BigDecimal price, Long vehicleId) {
        this.id = id;
        this.currency = currency;
        this.price = price;
        this.vehicleId = vehicleId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }
}
