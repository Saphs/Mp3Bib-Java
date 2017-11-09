package com.mp3bib.communication.command;

/**
 * @author Tizian Rettig - Saphs
 * @version 1.0.0
 */
public class GetMusicList extends Command{

    //TODO: implement command

    @Override
    public String invoke(String json) {
        return "Get music command did something!";
    }

    @Override
    public String createSendable(String... args) {
        return null;
    }

    @Override
    public Command buildInstance(String jsonSendable) {
        return null;
    }


}
