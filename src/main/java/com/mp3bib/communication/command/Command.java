package com.mp3bib.communication.command;

import com.mp3bib.backend.BackendprocessService;

public abstract class Command {

    final BackendprocessService backend = BackendprocessService.getInstance();
    final String commandName = this.getClass().getSimpleName();

    /**
     * TODO:Add Documentation.
     * @param command
     * @return
     */
    public Boolean meetsConstraints(String command) { return command.equalsIgnoreCase(commandName); }
    public abstract String invoke(String json);

    /**
     * 'createSendable' is meant to be implemented as a static method to provide
     * an easy way to send a given command as a Json-String.
     * @param args provide potential arguments for more complex requests.
     * @return needs to provide a Json-String that can be send directly.
     */
    public abstract String createSendable(String... args);

    /**
     * 'buildInstance' is meant to be implemented as a static method to provide
     * an easy way to retrieve a Json-String based instance, formerly created by
     * 'createSendable of the same class.
     * @param jsonSendable should be a Json-String formerly created using 'createSendable'.
     * @return needs return the implementing command class from the given Json-String.
     */
    public abstract Command buildInstance(String jsonSendable);
}
