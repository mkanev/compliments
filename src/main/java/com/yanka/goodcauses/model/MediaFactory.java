package com.yanka.goodcauses.model;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
public class MediaFactory {

    private static final MediaFactory instance = new MediaFactory();

    private MediaFactory() {
    }

    public static MediaFactory getInstance() {
        return instance;
    }

    public Media buildPlaceholderImage(int width, int height) {
        Media media = new Media();
        media.setName("Sample image");
        media.setMediaType(MediaType.LINKED);
        media.setMediaDataType(MediaDataType.IMAGE);
        media.setUrl("http://placehold.it/" + width + "x" + height);
        return media;
    }
}
