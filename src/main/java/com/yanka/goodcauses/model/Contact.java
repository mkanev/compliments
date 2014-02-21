package com.yanka.goodcauses.model;

import com.yanka.goodcauses.JsonViews;

import org.codehaus.jackson.map.annotate.JsonView;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
@Entity
public class Contact extends Person {

    @Enumerated(EnumType.STRING)
    private Position position;

    @JsonView(JsonViews.User.class)
    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
