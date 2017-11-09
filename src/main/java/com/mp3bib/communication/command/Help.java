package com.mp3bib.communication.command;

import com.mp3bib.backend.CommandCaller;

/**
 * @author Tizian Rettig - Saphs
 * @version 1.0.0
 */
public class Help extends Command {
    @Override
    public String invoke(String json) {
        StringBuilder commandList = new StringBuilder("All known Commands are: ");
        for (Command cmd : CommandCaller.getKnownCommands()){
            commandList = commandList.append(cmd.getClass().getSimpleName()).append(' ');
        }
        return commandList.toString();
    }

    @Override
    public String createSendable() {
        return null;
    }
}
