import com.mp3bib.backend.BackendprocessService;
import com.mp3bib.communication.command.sys_Kill;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * @author Tizian Rettig - Saphs
 * @version 1.0.0
 */
public class Sys_KillTest {

    // The class should..

    @Test
    public void invokeCorrectly(){
        BackendprocessService testingBackend = BackendprocessService.getInstance();

        Thread T1 = new Thread(testingBackend);
        T1.start();

        new sys_Kill().invoke("Any");

        try{
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertFalse(T1.isAlive());
    }

}
