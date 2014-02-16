package com.yanka.goodcauses.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * @author <a href="mailto:maksim.kanev@gmail.com">Maksim Kanev</a>
 */
public class LoggedClass implements Serializable {

    private static final long serialVersionUID = 2163919862005646481L;
    private transient Logger log;

    public LoggedClass() {
        log = LoggerFactory.getLogger(getClass().getName());
    }

    protected LoggedClass(Class<?> c) {
        log = LoggerFactory.getLogger(c);
    }

    public Logger getLogger() {
        return log;
    }

    public void logDebug(String msg, Object... messageParams) {
        logDebug(msg, null, messageParams);
    }

    public void logDebug(Exception e) {
        logDebug(e.getLocalizedMessage(), e);
    }

    public void logDebug(String message, Exception e, Object... messageParams) {
        if (log.isDebugEnabled()) {
            log.debug(String.format(message, messageParams), e);
        }
    }

    public void logWarning(String message, Object... messageParams) {
        logWarning(message, null, messageParams);
    }

    public void logWarning(Exception e) {
        logWarning(e.getLocalizedMessage(), e);
    }

    public void logWarning(String message, Exception e, Object... messageParams) {
        log.warn(String.format(message, messageParams), e);
    }

    public void logError(String message, Object... messageParams) {
        logError(message, null, messageParams);
    }

    public void logError(Exception e) {
        logError(e.getLocalizedMessage(), e);
    }

    public void logError(String message, Exception e, Object... messageParams) {
        log.error(String.format(message, messageParams), e);
    }

}