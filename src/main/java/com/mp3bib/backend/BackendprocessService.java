package com.mp3bib.backend;

import com.mp3bib.communication.BindableBackend;
import com.mp3bib.logging.CustomLogger;
import com.mp3bib.logging.Logger;
import java.util.ArrayList;


/**
 * Gives an single point of access to request data from the backend via
 * defined request and response types in form of (soon to be) Json-Strings.
 *
 * This class is implemented as an singleton,
 * use the getInstance() instead of a normal constructor call.
 *
 * @author Tizian Rettig - Saphs
 * @version 1.0.1
 */
public class BackendprocessService extends BindableBackend implements Runnable {

    public Logger logger = new CustomLogger(Logger.LOGLEVEL_INFO);

    private ArrayList<String> requestBuffer = new ArrayList<>();
    private CommandCaller commandCaller = new CommandCaller();
    private ResponseDistributer responseDistributer = new ResponseDistributer(super.bindables);

    private Boolean closeRequest = false;

    // Singelton implementation ----------------------------------------------------------------------------------------
    private static BackendprocessService instance;

    private BackendprocessService() {
        logger.debug("New Object of " + getClass().getName() + "instantiated.");
    }

    public static BackendprocessService getInstance() {
        if (instance == null) {
            instance = new BackendprocessService();
        }
        return instance;
    }
    //------------------------------------------------------------------------------------------------------------------


    // Implementation ----------------------------------------------------------------------------------------------------
    @Override
    public void run() {

        logger.info("Backend:\t" + getClass().getTypeName() + " on " + Thread.currentThread().getName() + " starts.");

        synchronized (this) {
            while (!closeRequest) {
                waitForRequest();
                if (!closeRequest) {

                    String currentRequest = requestBuffer.get(0);
                    String response = commandCaller.invoke(currentRequest);

                    responseDistributer.answerAny(response);
                    requestBuffer.remove(0);
                }
            }
        }

        logger.info("Backend:\t" + getClass().getTypeName() + " on " + Thread.currentThread().getName() + " finished.");
    }

    @Override
    public synchronized void pushRequest(String request) {
        requestBuffer.add(request);
        notify();
    }

    @Override
    public Boolean needsToClose() {
        return super.bindables.isEmpty();
    }

    @Override
    public void killBackend() {
        pushRequest("sys_Kill");
    }
    //------------------------------------------------------------------------------------------------------------------

    // Helper methods --------------------------------------------------------------------------------------------------
    private void waitForRequest() {
        while (requestBuffer.isEmpty()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void giveCloseRequest() {
        closeRequest = true;
    }

    //------------------------------------------------------------------------------------------------------------------
}