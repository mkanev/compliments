package com.yanka.goodcauses.rest.resources;

import com.yanka.goodcauses.model.Compliment;
import com.yanka.goodcauses.service.ComplimentManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import javax.ws.rs.Path;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
@Component
@Path("/compliment")
public class ComplimentResource extends GenericEntityResource<Compliment> {

    private ComplimentManager complimentManager;

    @Autowired
    public ComplimentResource(ComplimentManager complimentManager) {
        super(complimentManager);
        this.complimentManager = complimentManager;
    }

    @Override
    protected List<Compliment> getEntityList() {
        return complimentManager.getExistingEntityList();
    }
}
