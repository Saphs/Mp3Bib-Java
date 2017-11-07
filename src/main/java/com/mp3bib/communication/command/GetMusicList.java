package com.mp3bib.communication.command;

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
