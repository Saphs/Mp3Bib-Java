package com.mp3bib.backend;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mp3bib.communication.command.*;
import com.mp3bib.logging.Logger;

import java.util.ArrayList;
import java.util.Arrays;

import static com.mp3bib.Mp3BackendBootstrapper.logger;


/**
 * Implements the mapping of requests to functions
 * and executes them accordingly.
 *
 * @author Tizian Rettig - Saphs
 * @version 1.0.1
 */
public class CommandCaller {

    /**
     * Provides a list of known commands that needs to
     * be expanded as new commands are implemented.
     */
    private final static Command[] knownCommands = {

            new GetMusicList(),
            new Help(),
            new sys_Kill(),
            new GetDetailedMetadata()
    };

    /**
     * Selects and invokes the corresponding command to a given request.
     * @param command the given request that is checked and possibly executed.
     * @return the result of a given command or the String 'Empty response.' if
     * the command did not match any known command.
     */
    String invoke(String command){
        Command choosenCommand = null;

        JsonElement jelem = BackendprocessService.gson.fromJson(command, JsonElement.class);
        JsonObject jobj = jelem.getAsJsonObject();
        if (!jobj.has("commandName") || !jobj.get("commandName").isJsonPrimitive() || !jobj.get("commandName").getAsJsonPrimitive().isString()) {
            return "Command Failed";
        }

        String cmdName = jobj.get("commandName").getAsJsonPrimitive().getAsString();

        for (Command cmd : knownCommands) {
            if (cmd.meetsConstraints(cmdName)){
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
