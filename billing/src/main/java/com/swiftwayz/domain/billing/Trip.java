package com.swiftwayz.domain.billing;

import com.swiftwayz.domain.util.BaseEntity;

import javax.persistence.*;
import java.time.ZonedDateTime;

/**
 * Created by sydney on 2017/05/29.
 */
@Entity
@Table(name = "trip")
public class Trip extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="seq-gen",sequenceName="trip_id_seq", initialValue=205, allocationSize=12)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq-gen")
    @Column(name = "id")
    private Long id;

    @Column(name = "trip_time")
    private ZonedDateTime tripTime;

    @Column(name = "driver_time")
    private ZonedDateTime driverTime;

    @Column(name = "distance")
    private Double distance;

    @Column(name = "km")
    private Double km;

    @Column(name = "destination_lat")
    private Double destinationLat;

    @Column(name = "destination_long")
    private Double destinationLong;

    @Column(name = "destination")
    private String destination;

    @Column(name = "departure_long")
    private Double departureLong;

    @Column(name = "departure_lat")
    private Double departureLat;

    @Column(name = "departure")
    private Double departure;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getTripTime() {
        return tripTime;
    }

    public void setTripTime(ZonedDateTime tripTime) {
        this.tripTime = tripTime;
    }

    public ZonedDateTime getDriverTime() {
        return driverTime;
    }

    public void setDriverTime(ZonedDateTime driverTime) {
        this.driverTime = driverTime;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getKm() {
        return km;
    }

    public void setKm(Double km) {
        this.km = km;
    }

    public Double getDestinationLat() {
        return destinationLat;
    }

    public void setDestinationLat(Double destinationLat) {
        this.destinationLat = destinationLat;
    }

    public Double getDestinationLong() {
        return destinationLong;
    }

    public void setDestinationLong(Double destinationLong) {
        this.destinationLong = destinationLong;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Double getDepartureLong() {
        return departureLong;
    }

    public void setDepartureLong(Double departureLong) {
        this.departureLong = departureLong;
    }

    public Double getDepartureLat() {
        return departureLat;
    }

    public void setDepartureLat(Double departureLat) {
        this.departureLat = departureLat;
    }

    public Double getDeparture() {
        return departure;
    }

    public void setDeparture(Double departure) {
        this.departure = departure;
    }
}
