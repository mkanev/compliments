package com.yanka.goodcauses.transfer;

import com.owlike.genson.annotation.JsonIgnore;
import com.yanka.goodcauses.model.GenericEntity;

import org.springframework.beans.support.PagedListHolder;

import java.util.List;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
public class PagedResponseTransfer<T extends GenericEntity> {

    @JsonIgnore
    private PagedListHolder<T> pagedListHolder;

    public PagedResponseTransfer(PagedListHolder<T> pagedListHolder) {
        this.pagedListHolder = pagedListHolder;
    }

    public int getPageCount() {
        return pagedListHolder.getPageCount();
    }

    public int getTotalCount() {
        return pagedListHolder.getNrOfElements();
    }

    public boolean isFirstPage() {
        return pagedListHolder.isFirstPage();
    }

    public boolean isLastPage() {
        return pagedListHolder.isLastPage();
    }

    public List<T> getEntries() {
        return pagedListHolder.getPageList();
    }

    public int getPageSize() {
        return pagedListHolder.getPageSize();
    }
}
