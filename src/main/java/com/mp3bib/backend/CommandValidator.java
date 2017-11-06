package com.mp3bib.backend;

/**
 * This class will undergo heavy changes as the command & API structure changes
 */
public class CommandValidator {

    private static String[] validationList = {
            "command1",
            "command2",

            "$Kill"
    };

    public static Boolean validateCommand(String command){
        Boolean isValid = false;
        for ( String validationString: validationList ) {
            if (validationString.equals(command)) isValid = true;
        }
        return isValid;
    }
}
