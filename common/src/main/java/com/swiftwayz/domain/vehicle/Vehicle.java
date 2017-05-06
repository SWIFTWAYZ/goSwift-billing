package com.swiftwayz.domain.vehicle;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by sydney on 2017/04/08.
 */
@Entity
@Table(name = "vehicle")
public class Vehicle implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="seq-gen",sequenceName="product_id_seq", initialValue=205, allocationSize=12)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq-gen")
    private Long id;

    @Column(name = "registration_number")
    private String registrationNumber;

    @Column(name = "year_registered")
    private int yearRegistered;

    @Column(name = "vin_number")
    private String vinNumber;

    @Column(name = "make")
    private String make;

    @Column(name = "model")
    private String model;

    @Column(name = "color")
    private String color;

    @Column(name = "seat_capacity")
    private int seatCapacity;

    @Column(name = "clock_mileage")
    private int clockMileage;

    @Column(name = "date_approved")
    private Date dateApproved;

    @OneToOne
    @JoinColumn(name = "product_type_id")
    private Product product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String  registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public int getYearRegistered() {
        return yearRegistered;
    }

    public void setYearRegistered(int yearRegistered) {
        this.yearRegistered = yearRegistered;
    }

    public String getVinNumber() {
        return vinNumber;
    }

    public void setVinNumber(String vinNumber) {
        this.vinNumber = vinNumber;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getSeatCapacity() {
        return seatCapacity;
    }

    public void setSeatCapacity(int seatCapacity) {
        this.seatCapacity = seatCapacity;
    }

    public int getClockMileage() {
        return clockMileage;
    }

    public void setClockMileage(int clockMileage) {
        this.clockMileage = clockMileage;
    }

    public Date getDateApproved() {
        return dateApproved;
    }

    public void setDateApproved(Date dateApproved) {
        this.dateApproved = dateApproved;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
