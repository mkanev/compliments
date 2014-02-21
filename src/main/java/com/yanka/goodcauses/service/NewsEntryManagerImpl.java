package com.yanka.goodcauses.service;

import com.yanka.goodcauses.model.NewsEntry;
import com.yanka.goodcauses.repository.NewsEntryDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
@Service("newsEntryManager")
public class NewsEntryManagerImpl extends GenericEntityManagerImpl<NewsEntry> implements NewsEntryManager {

    private NewsEntryDAO newsEntryDao;

    @Autowired
    public NewsEntryManagerImpl(NewsEntryDAO newsEntryDao) {
        super(newsEntryDao);
        this.newsEntryDao = newsEntryDao;
    }
}
