package com.swiftwayz.domain.util;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.ZonedDateTime;

/**
 * Created by sydney on 2017/04/09.
 */


@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public abstract class BaseEntity {

    @Column(name = "created_date", nullable = false)
    @CreatedDate
    private ZonedDateTime createdDate;

    @Column(name = "modified_date")
    @LastModifiedDate
    private ZonedDateTime modifiedDate;

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    public ZonedDateTime getModifiedDate() {
        return modifiedDate;
    }
}