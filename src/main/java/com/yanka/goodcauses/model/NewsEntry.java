package com.yanka.goodcauses.model;

import com.google.common.base.Objects;

import com.yanka.goodcauses.JsonViews;

import org.codehaus.jackson.map.annotate.JsonView;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;


/**
 * JPA Annotated Pojo that represents a news entry.
 *
 * @author Philip W. Sorst <philip@sorst.net>
 */
@Entity
public class NewsEntry extends GenericEntity {

    @Column(length = 1024, nullable = false)
    private String title;

    @Lob
    @Column(length = Integer.MAX_VALUE, nullable = false)
    private String content;


    public NewsEntry() {
    }

    @JsonView(JsonViews.User.class)
    public String getTitle() {
        return title;
    }

    public void setTitle(String caption) {
        this.title = caption;
    }

    @JsonView(JsonViews.User.class)
    public String getContent() {

        return this.content;
    }


    public void setContent(String content) {

        this.content = content;
    }


    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("title", title).add("content", content) + super.toString();
    }

    @Override
    public void deleteFull() {

    }

    @Override
    public void loadFull() {

    }

}
