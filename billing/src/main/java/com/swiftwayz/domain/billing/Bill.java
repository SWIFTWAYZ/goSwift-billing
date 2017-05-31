package com.swiftwayz.domain.billing;

import com.swiftwayz.domain.util.BaseEntity;
import com.swiftwayz.domain.vehicle.Product;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

/**
 * Created by sydney on 2017/05/29.
 */
@Entity
@Table(name = "bill")
public class Bill extends BaseEntity{
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="seq-gen",sequenceName="bill_id_seq", initialValue=205, allocationSize=12)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq-gen")
    @Column(name = "id")
    private Long id;

    @Column(name = "description")
    private String description;

    @Column(name = "base_fare")
    private BigDecimal baseFare;

    @Column(name = "normal_fare")
    private BigDecimal normalFare;

    @Column(name = "subtotal_fare")
    private BigDecimal subTotalFare;

    @Column(name = "total_fare")
    private BigDecimal totalFare;

    @Column(name = "send_date")
    private ZonedDateTime sendDate;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "trip_id")
    private Trip trip;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "driver_id", nullable = false)
    private Long driverId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getBaseFare() {
        return baseFare;
    }

    public void setBaseFare(BigDecimal baseFare) {
        this.baseFare = baseFare;
    }

    public BigDecimal getNormalFare() {
        return normalFare;
    }

    public void setNormalFare(BigDecimal normalFare) {
        this.normalFare = normalFare;
    }

    public BigDecimal getSubTotalFare() {
        return subTotalFare;
    }

    public void setSubTotalFare(BigDecimal subTotalFare) {
        this.subTotalFare = subTotalFare;
    }

    public BigDecimal getTotalFare() {
        return totalFare;
    }

    public void setTotalFare(BigDecimal totalFare) {
        this.totalFare = totalFare;
    }

    public ZonedDateTime getSendDate() {
        return sendDate;
    }

    public void setSendDate(ZonedDateTime sendDate) {
        this.sendDate = sendDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }
}
