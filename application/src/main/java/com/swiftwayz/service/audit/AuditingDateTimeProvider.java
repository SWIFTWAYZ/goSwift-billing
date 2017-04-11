package com.swiftwayz.service.audit;

import org.springframework.data.auditing.DateTimeProvider;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by sydney on 2017/04/09.
 */
public class AuditingDateTimeProvider implements DateTimeProvider {

    private final DateTimeService dateTimeService;

    public AuditingDateTimeProvider(DateTimeService dateTimeService) {
        this.dateTimeService = dateTimeService;
    }

    public Calendar getNow() {
        return GregorianCalendar.from(dateTimeService.getCurrentDateAndTime());
    }
}
