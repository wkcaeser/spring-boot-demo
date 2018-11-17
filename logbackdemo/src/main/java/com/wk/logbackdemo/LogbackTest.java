package com.wk.logbackdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LogbackTest {

    private final static Logger logger = LoggerFactory.getLogger(LogbackTest.class);

    public static void main(String[] args) {
        int num = (int) 1;
        while (num-- > 0) {
            logger.debug("test debug");
            logger.info("test info");
            logger.warn("test warn");
            logger.error("test error");
        }
    }
}
