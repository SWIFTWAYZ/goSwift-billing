package com.swiftwayz.service.audit;

import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

/**
 * Created by sydney on 2017/04/09.
 */
@Component
public class CurrentTimeDateTimeService implements  DateTimeService {

    @Override
    public ZonedDateTime getCurrentDateAndTime() {
        return ZonedDateTime.now();
    }
}
