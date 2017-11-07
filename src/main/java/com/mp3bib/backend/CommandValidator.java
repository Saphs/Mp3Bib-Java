package com.mp3bib.backend;

/**
 * This class will undergo heavy changes as the command and API structure changes
 * @author Tizian Rettig
 */
public class CommandValidator {

    private final static String[] validationList = {
            "help",

            "musicList",
            "setPath",
            "edit",

            "$Kill"
    };

    public static Boolean validate(String command){
        Boolean isValid = false;
        for ( String validationString: validationList ) {
            if (validationString.equals(command)) isValid = true;
        }
        return isValid;
    }

    public static String[] getValidationList() {
        return validationList;
    }
}
