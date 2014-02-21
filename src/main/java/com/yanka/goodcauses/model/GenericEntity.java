package com.yanka.goodcauses.model;

import com.google.common.base.Objects;

import com.yanka.goodcauses.JsonViews;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonView;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * @author <a href="mailto:maksim.kanev@gmail.com">Maksim Kanev</a>
 */
@MappedSuperclass
@EntityListeners({GenericEntityListener.class})
public abstract class GenericEntity extends BaseDBObject {

    public static final String FIELD_UPDATE_DATE = "updateDate";
    public static final String FIELD_INSERT_DATE = "insertDate";
    public static final String FIELD_UUID = "uuid";
    private static final long serialVersionUID = -974110180786730119L;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "insert_date")
    private Date insertDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "update_date")
    private Date updateDate;
    @Column(name = "uuid", nullable = false, unique = true, length = 64)
    private String uuid = UUID.randomUUID().toString();

    @JsonView(JsonViews.Admin.class)
    public Date getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(Date insertDate) {
        this.insertDate = insertDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    @JsonIgnore
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                   .add("insertDate", insertDate)
                   .add("updateDate", updateDate)
                   .add("uuid", uuid)
                   .toString() + super.toString();
    }
}
