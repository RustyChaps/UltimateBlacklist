package me.gurwi.ultimateblacklist.utils.customlogger;

import me.gurwi.ultimateblacklist.UltimateBlacklist;

import java.util.logging.Logger;

public class CustomLogger {

    private static final Logger logger = UltimateBlacklist.getInstance().getLogger();

    public static void success(String msg) {
        logger.info(LoggerTags.SUCCESS_TAG.getTag() + msg);
    }

    public static void load(String msg) {
        logger.info(LoggerTags.LOAD_TAG.getTag() + msg);
    }

    public static void info(String msg) {
        logger.info(LoggerTags.INFO_TAG.getTag() + msg);
    }

    public static void error(String msg) {
        logger.info(LoggerTags.ERROR_TAG.getTag() + msg);
    }

    public static void warning(String msg) {
        logger.info(LoggerTags.WARNING_TAG.getTag() + msg);
    }

    public static void configError(String msg) {
        logger.info(LoggerTags.CONFIG_ERROR_TAG.getTag() + msg);
    }

}
