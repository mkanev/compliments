package com.yanka.goodcauses.model;

/**
 * @author <a href="mailto:maksim.kanev@waveaccess.ru">Maksim Kanev</a>
 */
public enum OrganizationType {
    FUND, //
    PARTNER, //
    ;

    public static OrganizationType find(String typeStr) {
        for (OrganizationType organizationType : values()) {
            if (organizationType.name().equalsIgnoreCase(typeStr)) {
                return organizationType;
            }
        }
        return null;
    }
}
