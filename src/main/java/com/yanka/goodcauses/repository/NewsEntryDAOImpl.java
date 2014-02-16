package com.yanka.goodcauses.repository;

import com.yanka.goodcauses.model.NewsEntry;

import org.springframework.stereotype.Repository;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
@Repository
public class NewsEntryDAOImpl extends GenericEntityDAOImpl<NewsEntry, Long> implements NewsEntryDAO {

    public NewsEntryDAOImpl() {
        super(NewsEntry.class);
    }
}
