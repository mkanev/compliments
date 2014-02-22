package com.yanka.goodcauses.model;

import com.google.common.base.Objects;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
@MappedSuperclass
public class NamedEntity extends GenericEntity {

    @Column(length = 1024, nullable = false)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
            .add("name", name)
            .toString() + super.toString();
    }

    @Override
    public void deleteFull() {

    }

    @Override
    public void loadFull() {

    }
}
