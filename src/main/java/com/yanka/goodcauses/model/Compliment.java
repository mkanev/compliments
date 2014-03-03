package com.yanka.goodcauses.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
@Entity
public class Compliment extends GenericEntity {

    @Column(length = 1024, nullable = false)
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public void deleteFull() {

    }

    @Override
    public void loadFull() {

    }
}
