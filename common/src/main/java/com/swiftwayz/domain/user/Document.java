package com.swiftwayz.domain.user;

import com.swiftwayz.domain.util.BaseEntity;

import javax.persistence.*;

/**
 * Created by sydney on 2017/04/17.
 */
@Entity
@Table(name = "document")
public class Document extends BaseEntity{
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name="seq-gen",sequenceName="document_id_seq", initialValue=205, allocationSize=12)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq-gen")
    private Long id;

    private String name;

    private String type;

    private String path;

}
