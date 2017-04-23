package com.swiftwayz.domain.user;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by sydney on 2017/04/23.
 */
@Data
public class Driver extends User {

    private DriverDetail driverDetail;

}
