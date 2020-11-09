package com.proto.controller;


import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {
    private static Logger logger = LoggerFactory.getLogger(LogController.class);

    @RequestMapping(value = "logLevel/{logLevel}")
    public String changeLogLevel(@PathVariable("logLevel") String logLevel) {

        try {
            LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();
            loggerContext.getLogger("root").setLevel(Level.valueOf(logLevel));
        } catch (Exception e) {
            logger.error("动态修改日志级别出错", e);
            return "fail";
        }

        return "success";
    }
}
