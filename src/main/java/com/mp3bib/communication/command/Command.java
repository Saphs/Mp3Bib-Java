package com.mp3bib.communication.command;

import com.google.gson.Gson;
import com.mp3bib.backend.BackendprocessService;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Defines values and signature that every command needs
 * to implement to be used in the CommandCaller class.
 * @see com.mp3bib.backend.CommandCaller
 *
 * @author Tizian Rettig - Saphs
 * @version 1.0.0
 */
public abstract class Command {

    //protected final Gson gson = new Gson();
    //final BackendprocessService backend = BackendprocessService.getInstance();
    final String commandName = this.getClass().getSimpleName();

    public Command() {}

    public Command createObject(String param) throws IllegalArgumentException { return null; }

    /**
     * Will be called to check is a given request fits this command call.
     * @param command The request that it is checked against.
     * @return A boolean value that is true if a request fits this command.
     */
    public Boolean meetsConstraints(String command) {
        return command.equalsIgnoreCase(commandName);
    }

    /**
     * used to separate args by spaces
     * used to parse from cmdline interface to arguments
     * @param txt the full parameterstring
     * @return the arguments
     */
    protected static ArrayList<String> seperateArgs(String txt) {
        ArrayList<String> args = new ArrayList<String>();

        Scanner sc = new Scanner(txt);
        sc.useDelimiter(" ");
        while (sc.hasNext()) {
            args.add(sc.next());
        }
        return args;
    }

    /**
     * The call that is made to invoke and therefore execute the command.
     * @param json The command that met the constrains (gives context or parameters).
     * @return needs to give a Json-String response.
     */
    public abstract String invoke(String json);

    /**
     * 'createSendable' is meant to be implemented as a static method to provide
     * an easy way to send a given command as a Json-String.
     * @return needs to provide a Json-String that can be send directly.
     */
    public abstract String createSendable();
}
