package com.swiftwayz.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.swiftwayz.domain.util.BaseEntity;
import com.swiftwayz.domain.vehicle.Vehicle;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by sydney on 2017/04/16.
 */
@Entity
@Table(name = "driver_detail")
public class DriverDetail extends BaseEntity{
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="seq-gen",sequenceName="driver_detail_id_seq", initialValue=205, allocationSize=12)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq-gen")
    private Long id;

    @Column(name = "license_number")
    private Long licenseNumber;

    @Column(name = "license_date_obtained")
    private Date dateLicenseObtained;

    @Column(name = "public_permit_number")
    private String permitNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "vehicle_owner_id", referencedColumnName = "ID")
    private VehicleOwner vehicleOwner;

    @Column(name = "crime_check_status")
    private String crimeCheck;

    @Column
    private String comments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(Long licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public Date getDateLicenseObtained() {
        return dateLicenseObtained;
    }

    public void setDateLicenseObtained(Date dateLicenseObtained) {
        this.dateLicenseObtained = dateLicenseObtained;
    }

    public String getPermitNumber() {
        return permitNumber;
    }

    public void setPermitNumber(String permitNumber) {
        this.permitNumber = permitNumber;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public VehicleOwner getVehicleOwner() {
        return vehicleOwner;
    }

    public void setVehicleOwner(VehicleOwner vehicleOwner) {
        this.vehicleOwner = vehicleOwner;
    }

    public String getCrimeCheck() {
        return crimeCheck;
    }

    public void setCrimeCheck(String crimeCheck) {
        this.crimeCheck = crimeCheck;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
