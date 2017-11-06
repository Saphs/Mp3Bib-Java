

/**
 * By Tizian Rettig
 */

public class Mp3BackendBootstrapper {

    public static void main (String[] args) {
        System.out.println("Bootstrap:\t" + Mp3BackendBootstrapper.class.getSimpleName() + " on " + Thread.currentThread().getName() + " starts.");
        init(true); //temporary solution for entering Console-mode
        System.out.println("Bootstrap:\t" + Mp3BackendBootstrapper.class.getSimpleName() + " on " + Thread.currentThread().getName() + " finished.");
    }

    private static void init(Boolean consoleMode){ //sout.println will be replaced when a logging framework is introduced
        System.out.println("Console mode: " + consoleMode);
        if(consoleMode){
            System.out.println("Starting in Console mode...");
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
