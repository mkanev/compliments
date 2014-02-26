package com.yanka.goodcauses.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
@Entity
public class Comment extends GenericEntity {

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH})
    private Contact author;
    @Lob
    @Column(length = Integer.MAX_VALUE, nullable = false)
    private String content;

    public Contact getAuthor() {
        return author;
    }

    public void setAuthor(Contact author) {
        this.author = author;
    }

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
