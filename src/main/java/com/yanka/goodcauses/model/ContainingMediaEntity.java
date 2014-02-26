package com.yanka.goodcauses.model;

import com.yanka.goodcauses.JsonViews;

import org.codehaus.jackson.map.annotate.JsonView;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
@MappedSuperclass
public class ContainingMediaEntity extends NamedEntity {

    @OneToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
    private Set<Media> mediaSet = new HashSet<>();

    @JsonView(JsonViews.Extended.class)
    public Set<Media> getMediaSet() {
        return mediaSet;
    }

    public void setMediaSet(Set<Media> mediaSet) {
        this.mediaSet = mediaSet;
    }

    public void addMedia(Media media) {
        mediaSet.add(media);
    }

    @JsonView(JsonViews.Preview.class)
    public Media getMediaPreview() {
        for (Media media : mediaSet) {
            if (MediaDataType.IMAGE.equals(media.getMediaDataType())) {
                return media;
            }
        }
        return MediaFactory.getInstance().buildPlaceholderImage(1500, 900);
    }

    @Override
    public void deleteFull() {

    }

    @Override
    public void loadFull() {

    }
}
