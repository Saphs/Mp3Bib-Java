package com.mp3bib.logging;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Custom implemented Logger
 * centralizes and handles all logging
 * @author Daniel Liebler
 * @version 1.0.0
 */
public class CustomLogger implements Logger {

    private static final String COLOR_RESET = (char)27 + "[0m";
    private static final String COLOR_BOLD_RED = (char)27 + "[31;43;1m";
    private static final String COLOR_RED = (char)27 + "[31m";
    private static final String COLOR_MAGENTA = (char)27 + "[35m";
    private static final String COLOR_YELLOW = (char)27 + "[33m";
    private static final String COLOR_GREEN = (char)27 + "[32m";
    private static final String COLOR_WHITE = (char)27 + "[37m";


    /**
     * Logging destination constant for logging to console
     */
    public static final int LOGDESTINATION_CONSOLE = 1;

    /**
     * Logging destination constant for logging to file
     * currently not supported
     */
    public static final int LOGDESTINATION_FILE = 2;


    private static int currentLogLevel = LOGLEVEL_INFO;
    private static int currentLogDestination = LOGDESTINATION_CONSOLE;


    public CustomLogger(int logLevel, int logDestination) {
        super();
        currentLogDestination = logDestination;
        currentLogLevel = logLevel;
        logLoggingConf();
    }
    public CustomLogger(int logLevel) {
        super();
        currentLogLevel = logLevel;
        logLoggingConf();
    }
    public CustomLogger() {
        super();
        logLoggingConf();
    }

    private void logLoggingConf() {
        log("Logging everything till Loglevel   " + currentLogLevel + "   to Destination   " + currentLogDestination, (char)27 + "[46;30;1m");
    }

    public void setLogLevel(int logLevel) {
        info("setting Logging Level to " + logLevel);
        currentLogLevel = logLevel;
    }

    public int getLogLevel() {
        return currentLogLevel;
    }

    /**
     * sets the log destination
     * @param dest the logging destination
     * @see com.mp3bib.logging.CustomLogger#LOGDESTINATION_CONSOLE
     * @see com.mp3bib.logging.CustomLogger#LOGDESTINATION_FILE
     */
    public void setLogDestination(int dest) {
        info("setting Logging Destination to " + dest);
        currentLogDestination = dest;
    }

    /**
     * gets the log destination
     * @return the current logging destination
     * @see com.mp3bib.logging.CustomLogger#LOGDESTINATION_CONSOLE
     * @see com.mp3bib.logging.CustomLogger#LOGDESTINATION_FILE
     */
    public int getLogDestination() {
        return currentLogDestination;
    }



    // logging methods -------------------------------------------------------------------------------------------------
    /**
     * Logs the text with trace logLevel
     * @param customText the text
     * @see com.mp3bib.logging.Logger#LOGLEVEL_TRACE
     */
    public void trace(String customText) {
        if (currentLogLevel >= LOGLEVEL_TRACE) {
            log(customText, COLOR_WHITE);
        }
    }
    /**
     * Logs the text with debug logLevel
     * @param customText the text
     * @see com.mp3bib.logging.Logger#LOGLEVEL_DEBUG
     */
    public void debug(String customText) {
        if (currentLogLevel >= LOGLEVEL_DEBUG) {
            log(customText, COLOR_GREEN);
        }
    }
    /**
     * Logs the text with info logLevel
     * @param customText the text
     * @see com.mp3bib.logging.Logger#LOGLEVEL_INFO
     */
    public void info(String customText) {
        if (currentLogLevel >= LOGLEVEL_INFO) {
            log(customText, COLOR_YELLOW);
        }
    }
    /**
     * Logs the text with warn logLevel
     * @param customText the text
     * @see com.mp3bib.logging.Logger#LOGLEVEL_WARN
     */
    public void warn(String customText) {
        if (currentLogLevel >= LOGLEVEL_WARN) {
            log(customText, COLOR_MAGENTA);
        }
    }
    /**
     * Logs the text with error logLevel
     * @param customText the text
     * @see com.mp3bib.logging.Logger#LOGLEVEL_ERROR
     */
    public void error(String customText) {
        if (currentLogLevel >= LOGLEVEL_ERROR) {
            log(customText, COLOR_RED);
        }
    }
    /**
     * Logs the text with fatal logLevel
     * @param customText the text
     * @see com.mp3bib.logging.Logger#LOGLEVEL_FATAL
     */
    public void fatal(String customText) {
        if (currentLogLevel >= LOGLEVEL_FATAL) {
            log(customText, COLOR_BOLD_RED);
        }
    }
    //------------------------------------------------------------------------------------------------------------------


    //function to log nevertheless which loglevel is used
    private void log(String customText, String color) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

        String dateString = formatter.format(date);
        String out = String.format("%s%s %s        %s" + COLOR_RESET, color, dateString, getCallerInfo(), customText);
        switch (currentLogDestination) {
            case LOGDESTINATION_CONSOLE:
                System.out.println(out);
                break;
            case LOGDESTINATION_FILE:
                //TODO: Implement logging to File
                break;
        }
    }

    //function to get Information of the calling function
    private String getCallerInfo() {
        StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
        for (int i=1; i<stElements.length; i++) {
            StackTraceElement caller = stElements[i];
            if (!caller.getClassName().equals(CustomLogger.class.getName()) && caller.getClassName().indexOf("java.lang.Thread")!=0) {
                return String.format("%40s %40s : line %3d", caller.getClassName(),caller.getMethodName(), caller.getLineNumber());
            }
        }
        return null;
    }
}
