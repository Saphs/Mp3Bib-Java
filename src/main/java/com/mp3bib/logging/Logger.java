package com.mp3bib.logging;

/**
 * Interface for Loggers
 * @author Daniel Liebler
 * @version 1.0.0
 */
public interface Logger {
    /**
     * Logging Level constant for logging everything
     * @category Constant
     */
    public final int LOGLEVEL_TRACE = 1;
    /**
     * Logging Level constant for logging debug information
     * @category Constant
     */
    public final int LOGLEVEL_DEBUG = 2;
    /**
     * Logging Level constant for logging information
     * @category Constant
     */
    public final int LOGLEVEL_INFO = 3;
    /**
     * Logging Level constant for logging warnings
     * @category Constant
     */
    public final int LOGLEVEL_WARN = 4;
    /**
     * Logging Level constant for logging error states
     * @category Constant
     */
    public final int LOGLEVEL_ERROR = 5;
    /**
     * Logging Level constant for logging fatal occurences
     * @category Constant
     */
    public final int LOGLEVEL_FATAL = 6;

    /**
     * set the LogLevel
     * @param logLevel the LogLevel to be set
     * @see com.mp3bib.logging.Logger#LOGLEVEL_TRACE
     * @see com.mp3bib.logging.Logger#LOGLEVEL_DEBUG
     * @see com.mp3bib.logging.Logger#LOGLEVEL_INFO
     * @see com.mp3bib.logging.Logger#LOGLEVEL_WARN
     * @see com.mp3bib.logging.Logger#LOGLEVEL_ERROR
     * @see com.mp3bib.logging.Logger#LOGLEVEL_FATAL
     * @category Setter
     */
    static void setLogLevel(int logLevel) {}

    /**
     * get the LogLevel
     * @return logLevel the current Loglevel
     * @see com.mp3bib.logging.Logger#LOGLEVEL_TRACE
     * @see com.mp3bib.logging.Logger#LOGLEVEL_DEBUG
     * @see com.mp3bib.logging.Logger#LOGLEVEL_INFO
     * @see com.mp3bib.logging.Logger#LOGLEVEL_WARN
     * @see com.mp3bib.logging.Logger#LOGLEVEL_ERROR
     * @see com.mp3bib.logging.Logger#LOGLEVEL_FATAL
     * @category Getter
     */
    static int getLogLevel() {
        return -1;
    }

    /**
     * Logs the text with trace logLevel
     * @param customText the text
     * @see com.mp3bib.logging.Logger#LOGLEVEL_TRACE
     */
    static void trace(String customText) {}
    /**
     * Logs the text with trace logLevel
     * @param customText the text
     * @see com.mp3bib.logging.Logger#LOGLEVEL_DEBUG
     */
    static void debug(String customText) {}
    /**
     * Logs the text with trace logLevel
     * @param customText the text
     * @see com.mp3bib.logging.Logger#LOGLEVEL_INFO
     */
    static void info(String customText) {}
    /**
     * Logs the text with trace logLevel
     * @param customText the text
     * @see com.mp3bib.logging.Logger#LOGLEVEL_WARN
     */
    static void warn(String customText) {}
    /**
     * Logs the text with trace logLevel
     * @param customText the text
     * @see com.mp3bib.logging.Logger#LOGLEVEL_ERROR
     */
    static void error(String customText) {}
    /**
     * Logs the text with trace logLevel
     * @param customText the text
     * @see com.mp3bib.logging.Logger#LOGLEVEL_FATAL
     */
    static void fatal(String customText) {}
}
