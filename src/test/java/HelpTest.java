
import com.mp3bib.communication.command.Help;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Tizian Rettig - Saphs
 * @version 1.0.0
 */
public class HelpTest {

    // The class should..

    @Test
    public void invokeCorrectly(){

        Help helpCommand = new Help();
        String invokationResult = helpCommand.invoke("Any");

        String expectation = "\"All known Commands are: GetMusicList Help sys_Kill \"";
        assertEquals(invokationResult, expectation);
    }

}
