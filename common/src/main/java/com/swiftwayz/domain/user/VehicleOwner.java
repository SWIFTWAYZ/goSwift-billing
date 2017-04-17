package com.swiftwayz.domain.user;

import com.swiftwayz.domain.util.BaseEntity;
import com.swiftwayz.domain.vehicle.Vehicle;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by sydney on 2017/04/16.
 */
@Entity
@Table(name = "vehicle_owner")
public class VehicleOwner extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="seq-gen",sequenceName="vehicle_owner_id_seq", initialValue=205, allocationSize=12)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq-gen")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "is_driver")
    private boolean isDriver;

    @OneToMany(mappedBy = "vehicleOwner",cascade = CascadeType.ALL)
    private Set<DriverDetail> driverDetails;

    public boolean isDriver() {
        return isDriver;
    }

    public void setDriver(boolean driver) {
        isDriver = driver;
    }

    public Set<DriverDetail> getDriverDetails() {
        return driverDetails;
    }

    public void setDriverDetails(Set<DriverDetail> driverDetails) {
        this.driverDetails = driverDetails;
    }
}
