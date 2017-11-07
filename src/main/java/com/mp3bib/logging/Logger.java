package com.mp3bib.logging;

/**
 * Interface for Loggers
 * @author Daniel Liebler
 * @version 1.0.0
 */
public interface Logger {
    /**
     * Logging Level constant for logging everything
     */
    public final int LOGLEVEL_TRACE = 1;
    /**
     * Logging Level constant for logging debug information
     */
    public final int LOGLEVEL_DEBUG = 2;
    /**
     * Logging Level constant for logging information
     */
    public final int LOGLEVEL_INFO = 3;
    /**
     * Logging Level constant for logging warnings
     */
    public final int LOGLEVEL_WARN = 4;
    /**
     * Logging Level constant for logging error states
     */
    public final int LOGLEVEL_ERROR = 5;
    /**
     * Logging Level constant for logging fatal occurrences
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
     */
    void setLogLevel(int logLevel);

    /**
     * get the LogLevel
     * @return logLevel the current Loglevel
     * @see com.mp3bib.logging.Logger#LOGLEVEL_TRACE
     * @see com.mp3bib.logging.Logger#LOGLEVEL_DEBUG
     * @see com.mp3bib.logging.Logger#LOGLEVEL_INFO
     * @see com.mp3bib.logging.Logger#LOGLEVEL_WARN
     * @see com.mp3bib.logging.Logger#LOGLEVEL_ERROR
     * @see com.mp3bib.logging.Logger#LOGLEVEL_FATAL
     */
    int getLogLevel();

    /**
     * Logs the text with trace logLevel
     * @param customText the text
     * @see com.mp3bib.logging.Logger#LOGLEVEL_TRACE
     */
    void trace(String customText);
    /**
     * Logs the text with trace logLevel
     * @param customText the text
     * @see com.mp3bib.logging.Logger#LOGLEVEL_DEBUG
     */
    void debug(String customText);
    /**
     * Logs the text with trace logLevel
     * @param customText the text
     * @see com.mp3bib.logging.Logger#LOGLEVEL_INFO
     */
    void info(String customText);
    /**
     * Logs the text with trace logLevel
     * @param customText the text
     * @see com.mp3bib.logging.Logger#LOGLEVEL_WARN
     */
    void warn(String customText);
    /**
     * Logs the text with trace logLevel
     * @param customText the text
     * @see com.mp3bib.logging.Logger#LOGLEVEL_ERROR
     */
    void error(String customText);
    /**
     * Logs the text with trace logLevel
     * @param customText the text
     * @see com.mp3bib.logging.Logger#LOGLEVEL_FATAL
     */
    void fatal(String customText);
}
