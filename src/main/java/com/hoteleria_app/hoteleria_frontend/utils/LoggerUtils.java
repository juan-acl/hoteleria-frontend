package com.hoteleria_app.hoteleria_frontend.utils;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class LoggerUtils {
    public static Logger createLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }
}
