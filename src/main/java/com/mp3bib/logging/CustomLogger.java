package com.mp3bib.logging;

import java.util.Date;

/**
 * Custom implemented Logger
 * centralizes and handles all logging
 * @author Daniel Liebler
 * @version 1.0.0
 */
public class CustomLogger implements Logger {

    /**
     * Logging destination constant for logging to console
     * @category Constant
     */
    public static final int LOGDESTINATION_CONSOLE = 1;

    /**
     * Logging destination constant for logging to file
     * currently not supported
     * @category Constant
     */
    public static final int LOGDESTINATION_FILE = 2;


    private static int currentLogLevel = LOGLEVEL_INFO;
    private static int currentLogDestination = LOGDESTINATION_CONSOLE;

    public static void setLogLevel(int logLevel) {
        currentLogLevel = logLevel;
    }

    static int getLogLevel() {
        return currentLogLevel;
    }

    /**
     * sets the log destination
     * @param dest the logging destination
     * @see com.mp3bib.logging.CustomLogger#LOGDESTINATION_CONSOLE
     * @see com.mp3bib.logging.CustomLogger#LOGDESTINATION_FILE
     * @category Setter
     */
    static void setLogDestination(int dest) {
        currentLogDestination = dest;
    }

    /**
     * gets the log destination
     * @return the current logging destination
     * @see com.mp3bib.logging.CustomLogger#LOGDESTINATION_CONSOLE
     * @see com.mp3bib.logging.CustomLogger#LOGDESTINATION_FILE
     * @category Getter
     */
    static int getLogDestination() {
        return currentLogDestination;
    }



    // logging methods -------------------------------------------------------------------------------------------------
    /**
     * Logs the text with trace logLevel
     * @param customText the text
     * @see com.mp3bib.logging.Logger#LOGLEVEL_TRACE
     */
    static void trace(String customText) {
        if (currentLogLevel >= LOGLEVEL_TRACE) {
            log(customText);
        }
    }
    /**
     * Logs the text with debug logLevel
     * @param customText the text
     * @see com.mp3bib.logging.Logger#LOGLEVEL_DEBUG
     */
    static void debug(String customText) {
        if (currentLogLevel >= LOGLEVEL_DEBUG) {
            log(customText);
        }
    }
    /**
     * Logs the text with info logLevel
     * @param customText the text
     * @see com.mp3bib.logging.Logger#LOGLEVEL_INFO
     */
    static void info(String customText) {
        if (currentLogLevel >= LOGLEVEL_INFO) {
            log(customText);
        }
    }
    /**
     * Logs the text with warn logLevel
     * @param customText the text
     * @see com.mp3bib.logging.Logger#LOGLEVEL_WARN
     */
    static void warn(String customText) {
        if (currentLogLevel >= LOGLEVEL_WARN) {
            log(customText);
        }
    }
    /**
     * Logs the text with error logLevel
     * @param customText the text
     * @see com.mp3bib.logging.Logger#LOGLEVEL_ERROR
     */
    static void error(String customText) {
        if (currentLogLevel >= LOGLEVEL_ERROR) {
            log(customText);
        }
    }
    /**
     * Logs the text with fatal logLevel
     * @param customText the text
     * @see com.mp3bib.logging.Logger#LOGLEVEL_FATAL
     */
    static void fatal(String customText) {
        if (currentLogLevel >= LOGLEVEL_FATAL) {
            log(customText);
        }
    }
    //------------------------------------------------------------------------------------------------------------------


    //function to log nevertheless which loglevel is used
    private static void log(String customText) {
        Date date = new Date();
        String out = String.format("%tF %tT:%tQ \t%s \t%s", date, getCallerInfo(), customText);
        switch (currentLogDestination) {
            case LOGDESTINATION_CONSOLE:
                System.out.println(out);
                break;
            case LOGDESTINATION_FILE:
                break;
        }
    }

    //function to get Information of the calling function
    private static String getCallerInfo() {
        StackTraceElement[] stElements = Thread.currentThread().getStackTrace();
        for (int i=1; i<stElements.length; i++) {
            StackTraceElement caller = stElements[i];
            if (!caller.getClassName().equals(CustomLogger.class.getName()) && caller.getClassName().indexOf("java.lang.Thread")!=0) {
                return String.format("in %s, %s : line %d",caller.getMethodName(), caller.getClassName(), caller.getLineNumber());
            }
        }
        return null;
    }
}
