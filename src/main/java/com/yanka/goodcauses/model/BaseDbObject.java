package com.yanka.goodcauses.model;

import com.google.common.base.Objects;

import com.yanka.goodcauses.JsonViews;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonView;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author <a href="mailto:maksim.kanev@gmail.com">Maksim Kanev</a>
 */
@MappedSuperclass
public abstract class BaseDBObject extends BaseObject implements Serializable {

    public static final String FIELD_ID = "id";
    public static final String FIELD_DELETED = "deleted";
    private static final long serialVersionUID = -7254989743482726486L;
    @Id
    @GeneratedValue
    private Long id;
    private boolean deleted;

    @JsonIgnore
    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean disabled) {
        this.deleted = disabled;
    }

    @JsonIgnore
    public boolean isBlank() {
        return id == null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BaseDBObject that = (BaseDBObject) o;
        return Objects.equal(this.id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
            .add("id", id)
            .add("class", getClass().getCanonicalName())
            .toString();
    }

    public abstract void deleteFull();

    public abstract void loadFull();
}
