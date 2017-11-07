package com.mp3bib.backend;

import com.mp3bib.communication.command.Command;
import com.mp3bib.communication.command.GetMusicList;
import com.mp3bib.communication.command.Help;
import com.mp3bib.communication.command.sys_Kill;
import com.mp3bib.logging.Logger;

import java.util.ArrayList;
import java.util.Arrays;

import static com.mp3bib.Mp3BackendBootstrapper.logger;

public class CommandCaller {

    /**
     * Provides a list of known commands that needs to
     * be expanded as new commands are implemented.
     */
    private final static Command[] knownCommands = {

            new GetMusicList(),
            new Help(),
            new sys_Kill()

    };


    public String invoke(String command){
        Command choosenCommand = null;

        for (Command cmd : knownCommands) {
            if (cmd.meetsConstraints(command)){
                choosenCommand = cmd;
                break;
            }
        }

        if (choosenCommand != null){

            return choosenCommand.invoke(command);
        }
        else {
            logger.warn("Invokation failed in CommandCaller. ");
            return "Empty response.";
        }
    }

    public static Command[] getKnownCommands() {
        return knownCommands;
    }
}
