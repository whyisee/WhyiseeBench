package com.logs;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.net.SyslogAppender;

/**
 * use for :
 *
 * @author zoukh
 * Created in:  2019/3/27 14:43
 * @version 1.0
 * @Modified By:
 * @used in: WorkTest
 */
public final class MarketLogger
{
    private static final Logger LOGGER = LogManager.getLogger(MarketLogger.class);
    // private static final KafkaMsgProducer producer = new KafkaMsgProducer();

    private MarketLogger()
    {
    }

    private static class MarketLoggerLevel extends Level
    {
        private static final long serialVersionUID = -5025508665034969684L;

        public MarketLoggerLevel(int level, String levelStr, int syslogEquivalent)
        {
            super(level, levelStr, syslogEquivalent);
        }
    }

    private static final String MARKET_LOGGER_NAME = "BUSI";

    private static final Level MARKET_LOGGER_LEVEL = new MarketLoggerLevel(90000, MARKET_LOGGER_NAME,
            SyslogAppender.LOG_LOCAL0);

    public static void log(LogEntity logInfo)
    {
        if (null != logInfo)
        {
            // 实时营销业务日志推往实时监控KAFKA
            // producer.send(logInfo);
            LOGGER.log(MARKET_LOGGER_LEVEL, logInfo);
        }
    }

    public static void log(Logger logger, LogEntity logInfo)
    {
        if (null != logger && null != logInfo)
        {
            logger.log(MARKET_LOGGER_LEVEL, logInfo);
        }
    }
}
