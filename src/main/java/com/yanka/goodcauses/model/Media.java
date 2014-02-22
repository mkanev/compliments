package com.yanka.goodcauses.model;

import com.google.common.base.Objects;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
@Entity
public class Media extends NamedEntity {

    @Enumerated(EnumType.STRING)
    private MediaType mediaType;
    @Enumerated(EnumType.STRING)
    private MediaDataType mediaDataType;
    private String url;
    private byte[] data;

    public MediaType getMediaType() {
        return mediaType;
    }

    public void setMediaType(MediaType mediaType) {
        this.mediaType = mediaType;
    }

    public MediaDataType getMediaDataType() {
        return mediaDataType;
    }

    public void setMediaDataType(MediaDataType mediaDataType) {
        this.mediaDataType = mediaDataType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                   .add("mediaType", mediaType)
                   .add("mediaDataType", mediaDataType)
                   .toString() + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Media that = (Media) o;
        return Objects.equal(getUuid(), that.getUuid());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getUuid());
    }

    @Override
    public void deleteFull() {

    }

    @Override
    public void loadFull() {

    }
}
