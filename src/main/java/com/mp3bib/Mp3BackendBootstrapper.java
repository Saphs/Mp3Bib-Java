package com.mp3bib;

import com.mp3bib.backend.BackendprocessService;
import com.mp3bib.frontend.ConsoleFrontend;
import com.mp3bib.logging.CustomLogger;
import com.mp3bib.logging.Logger;

/**
 * By Tizian Rettig
 */

public class Mp3BackendBootstrapper {

    public static Logger logger = new CustomLogger(Logger.LOGLEVEL_INFO);

    public static void main (String[] args) {
        logger.debug("Bootstrap:\t" + Mp3BackendBootstrapper.class.getSimpleName() + " on " + Thread.currentThread().getName() + " starts.");
        init(true); //temporary solution for entering Console-mode
        logger.debug("Bootstrap:\t" + Mp3BackendBootstrapper.class.getSimpleName() + " on " + Thread.currentThread().getName() + " finished.");
    }

    private static void init(Boolean consoleMode){ //sout.println will be replaced when a logging framework is introduced
        logger.info("Console mode: " + consoleMode);
        if(consoleMode){
            logger.info("Starting in Console mode...");
            ConsoleFrontend defaultFrontend = new ConsoleFrontend();
            BackendprocessService backend = BackendprocessService.getInstance();

            backend.bind(defaultFrontend);

            Thread consoleThread = new Thread(defaultFrontend);
            Thread backendThread = new Thread(backend);

            consoleThread.start();
            backendThread.start();
        }
        else {
            //TODO: GUI & GUI-binding
        }
    }
}
