package com.example.mq.log;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * @author 钟金灿
 * @since 2022/9/18
 */
public class LogConfig  extends ClassicConverter {
    @Override
    public String convert(ILoggingEvent iLoggingEvent) {
        return "abc";
    }
}
