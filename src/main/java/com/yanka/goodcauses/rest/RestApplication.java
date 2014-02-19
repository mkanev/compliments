package com.yanka.goodcauses.rest;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
@ApplicationPath("/rest/")
public class RestApplication extends ResourceConfig {

    public RestApplication() {
        packages("com.yanka.goodcauses.rest");
    }
}
