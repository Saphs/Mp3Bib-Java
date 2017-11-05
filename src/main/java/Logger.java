public interface Logger {
    public final int LOGLEVEL_TRACE = 1;
    public final int LOGLEVEL_DEBUG = 2;
    public final int LOGLEVEL_INFO = 3;
    public final int LOGLEVEL_WARN = 4;
    public final int LOGLEVEL_ERROR = 5;
    public final int LOGLEVEL_FATAL = 6;

    static void setLogLevel(int logLevel) {}

    static int getLogLevel() {
        return -1;
    }

    static void trace(String customText) {}
    static void debug(String customText) {}
    static void info(String customText) {}
    static void warn(String customText) {}
    static void error(String customText) {}
    static void fatal(String customText) {}
}
