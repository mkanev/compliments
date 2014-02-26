package com.yanka.goodcauses.model;

import com.google.common.base.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;


/**
 * JPA Annotated Pojo that represents a news entry.
 *
 * @author Philip W. Sorst <philip@sorst.net>
 */
@Entity
public class NewsEntry extends ContainingMediaEntity {

    @Lob
    @Column(length = Integer.MAX_VALUE, nullable = false)
    private String content;
    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.REFRESH})
    @JoinColumn(name = "author_id")
    private User author;

    public NewsEntry() {
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                   .add("content", content) + super.toString();
    }

    @Override
    public void deleteFull() {

    }

    @Override
    public void loadFull() {

    }

}
