package com.yanka.goodcauses.rest.resources;

import com.yanka.goodcauses.model.GenericEntity;
import com.yanka.goodcauses.service.GenericEntityManager;
import com.yanka.goodcauses.transfer.PagedResponseTransfer;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PagedListHolder;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
public abstract class GenericEntityResource<T extends GenericEntity> extends GenericResource<T, Long> {

    private final String NODE_LIST_MODEL_PAGE = "pageItems",
        NAVIGATION_NEXT = "next",
        NAVIGATION_PREV = "prev";
    private final int DEFAULT_PAGE_SIZE = 6;
    @Context
    private HttpServletRequest servletRequest;
    private GenericEntityManager<T> genericEntityManager;

    public GenericEntityResource(GenericEntityManager<T> genericEntityManager) {
        super(genericEntityManager);
        this.genericEntityManager = genericEntityManager;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String list(
        @QueryParam("page") Integer page,
        @QueryParam("navigate") String nav,
        @QueryParam("limit") Integer pageSize
    ) throws IOException {
        logDebug("list(%d,%s,%d)", page, nav, pageSize);
        PagedListHolder<T> pagedListHolder = configurePagedListHolder(page, nav, pageSize, new ResourceCallback<T>() {

            @Override
            public PagedListHolder<T> invokeCreate() {
                List<T> result = getEntityList();
                return new PagedListHolder<>(CollectionUtils.isEmpty(result) ? Collections.<T>emptyList() : result);
            }
        });
        return getPreviewWriter().writeValueAsString(/*new PagedResponseTransfer<>(*/pagedListHolder.getPageList()/*)*/);
    }

    protected PagedListHolder<T> configurePagedListHolder(Integer page, String nav, Integer limit,
                                                     ResourceCallback<T> createCall) {

        @SuppressWarnings("unchecked")
        PagedListHolder<T> pagedListHolder = (PagedListHolder<T>) servletRequest.getSession().getAttribute(getClass().getName() + "_" + NODE_LIST_MODEL_PAGE);

        if (pagedListHolder == null) {
            pagedListHolder = createCall.invokeCreate();

            MutableSortDefinition sort = (MutableSortDefinition) pagedListHolder.getSort();
            sort.setProperty(T.FIELD_UPDATE_DATE);
            sort.setIgnoreCase(false);
            sort.setAscending(false);
            pagedListHolder.resort();

            logDebug("pagedListHolder created");
        }
        pagedListHolder.setPageSize(limit == null ? DEFAULT_PAGE_SIZE : limit);
        if (nav != null) {
            switch (nav) {
                case NAVIGATION_NEXT: {
                    pagedListHolder.nextPage();
                    break;
                }
                case NAVIGATION_PREV: {
                    pagedListHolder.previousPage();
                    break;
                }
                default: {
                    logDebug("Unknown direction %s", nav);
                }
            }
        }
        if (page != null) {
            if (page < 1) {
                page = 1;
            }
            if (page > pagedListHolder.getPageCount()) {
                page = pagedListHolder.getPageCount();
            }
            pagedListHolder.setPage(--page);
        }
        servletRequest.getSession().setAttribute(getClass().getName() + "_" + NODE_LIST_MODEL_PAGE, pagedListHolder);
        return pagedListHolder;

    }

    protected abstract List<T> getEntityList();
}
