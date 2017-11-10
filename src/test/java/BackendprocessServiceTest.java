import com.mp3bib.backend.BackendprocessService;
import com.mp3bib.frontend.TestingFrontend;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * @author Tizian Rettig - Saphs
 * @version 1.0.0
 */
public class BackendprocessServiceTest {

    // The class should..

    @Test
    public void exitOnSys_Kill(){

        BackendprocessService testingBackend = BackendprocessService.getInstance();

        Thread T1 = new Thread(testingBackend);
        T1.start();

        testingBackend.pushRequest("sys_Kill");

        try{
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertFalse(T1.isAlive());
    }

    @Test
    public void actOnCalls(){

        BackendprocessService testingBackend = BackendprocessService.getInstance();
        TestingFrontend testingFrontend = new TestingFrontend();
        testingBackend.bind(testingFrontend);

        Thread T1 = new Thread(testingBackend);
        T1.start();

        testingFrontend.callBackend("Any");
        String result = testingFrontend.lastResponse();

        assertEquals(result, "Empty response.");

    }


}
