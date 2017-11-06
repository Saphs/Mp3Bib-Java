package com.mp3bib.backend;

import java.util.Arrays;

/**
 * By Tizian Rettig
 */

public class CommandExecuter {

    public String help() {
        return "List of all available commands: " + Arrays.toString(CommandValidator.getValidationList());
    }

    public String setPath(String path){
        return "setPath method called";
        //TODO: implement a way to set internal path variable
    }

    public String getMusicList(){
        return "getMusicList method called";
        //TODO: implement a way to retrieve all music information
    }

    public String edit(String placeHolder){
        return "edit placeHolder: " + placeHolder + " method called";
        //TODO: implement a way to edit one of the existing Mp3s
    }
}
