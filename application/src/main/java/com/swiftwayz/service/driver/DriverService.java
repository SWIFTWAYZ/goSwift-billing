package com.swiftwayz.service.driver;

import com.swiftwayz.repository.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by sydney on 2017/04/17.
 */
@Service
@Transactional
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;

}
