package com.mp3bib.backend.mp3library;

import com.beaglebuddy.mp3.MP3;
import com.mp3bib.backend.BackendprocessService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

/**
 * Custom implemented adapter to beaglebuddy
 * see http://www.beaglebuddy.com/
 * centralizes and handles all mp3 access
 * @author Daniel Liebler
 * @version 1.0.0
 */
public class Mp3IO {

    public static MP3 getMP3Tags(MP3 mp3){
        try {
            if (!checkMp3Errors(mp3)) {
                return null;
            }

            if (mp3.getAudioDuration() == 0)       // otherwise, if the length of the song hasn't been specified,
                mp3.setAudioDuration();             // then calculate it from the mpeg audio frames

            mp3.save();
            return mp3;
        } catch (IOException ex) {
            BackendprocessService.getInstance().logger.error("An error occurred while reading/saving the mp3 file.");
            return null;
        }
    }
    public static MP3 getMP3Tags(File file) {
        try {
            MP3 mp3 = new MP3(file);
            getMP3Tags(mp3);
        } catch (IOException ex) {
            BackendprocessService.getInstance().logger.error("An error occurred while reading/saving the mp3 file.");
            return null;
        }
    }
    public static MP3 getMP3Tags(String dir) {
        try {
            MP3 mp3 = new MP3(dir);
            getMP3Tags(mp3);
        } catch (IOException ex) {
            BackendprocessService.getInstance().logger.error("An error occurred while reading/saving the mp3 file.");
            return null;
        }
    }

    public static void indiceFiles(File dir) throws FileNotFoundException {
        if (!dir.exists()) {
            throw new FileNotFoundException();
        }
        if (dir.isFile()) {
            indiceSingleFile(dir);
        } else if (dir.isDirectory()) {
            for (File f : dir.listFiles()) {
                indiceFiles(f);
            }
        }
    }

    private static boolean checkMp3Errors(MP3 mp3) throws IOException {
        // if there was any invalid information (ie, ID3v2.x frames) in the .mp3 file,
        // then display the errors to the user
        if (mp3.hasErrors()) {                                      // display the errors that were found
            List<String> errors = mp3.getErrors();
            for(String error : errors) {
                BackendprocessService.getInstance().logger.error(error);
            }
            mp3.save();
            return false;                       // discard the invalid information (ID3v2.x frames) and
        }                                       // save only the valid frames back to the .mp3 file

        // see if the .mp3 file has valid MPEG audio frames
        List<String> errors = mp3.validateMPEGFrames();

        if (errors.size() != 0)
        {
            for(String error : errors) {
                BackendprocessService.getInstance().logger.error(error);
            }
            return false;
        }

        return true;
    }

    private static void indiceSingleFile(File f) throws FileNotFoundException {
        if (!(f.exists() && f.isFile())) {
            throw new FileNotFoundException();
        }
        //TODO: add File to Database
    }
}
