package com.yanka.goodcauses.model;

import com.yanka.goodcauses.common.DateFormat;
import com.yanka.goodcauses.common.LoggedClass;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;


/**
 * @author <a href="mailto:maksim.kanev@gmail.com">Maksim Kanev</a>
 */
public class GenericEntityListener extends LoggedClass {

    @PreUpdate
    public void preUpdate(GenericEntity s) {
        s.setUpdateDate(DateFormat.getInstance().now());
    }

    @PrePersist
    public void prePersist(GenericEntity s) {
        Date now = DateFormat.getInstance().now();
        s.setInsertDate(now);
        s.setUpdateDate(now);
    }

}
