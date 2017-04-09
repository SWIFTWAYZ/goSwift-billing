package com.swiftwayz.domain.vehicle;

import com.swiftwayz.domain.util.BaseEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by sydney on 2017/04/09.
 */
@Entity
@Table(name = "product")
public class Product extends BaseEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="seq-gen",sequenceName="product_id_seq", initialValue=205, allocationSize=12)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq-gen")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
