package com.swiftwayz.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by sydney on 2017/07/17.
 */
/**
 * An authority (a security role) used by Spring Security.
 */
@Entity
@Table(name = "authority")
public class Authority implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(min = 0, max = 10)
    @Id
    @Column(length = 10)
    private String code;

    @NotNull
    @Size(min = 0, max = 50)
    @Column(length = 50)
    private String name;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Authority authority = (Authority) o;

        if (code != null ? !code.equals(authority.code) : authority.code != null) return false;
        return name != null ? name.equals(authority.name) : authority.name == null;

    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Authority{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
