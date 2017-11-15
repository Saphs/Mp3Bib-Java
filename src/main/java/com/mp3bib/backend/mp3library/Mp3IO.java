package com.mp3bib.backend.mp3library;

import com.beaglebuddy.mp3.MP3;
import com.mp3bib.backend.BackendprocessService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
            if (!checkMp3Errors(mp3)) return null;                      // check for errors in Mp3-Tags
            if (mp3.getAudioDuration() == 0)  mp3.setAudioDuration();   // calculate audio duration if not already done
            mp3.save();
            return mp3;
        } catch (IOException ex) {
            BackendprocessService.getInstance().logger.error("An error occurred while saving the mp3 file: " + mp3.toString());
            return null;
        }
    }
    public static MP3 getMP3Tags(File file) {
        try {
            MP3 mp3 = new MP3(file);
            return getMP3Tags(mp3);
        } catch (IOException ex) {
            BackendprocessService.getInstance().logger.error("An error occurred while creating mp3 from file: " + file.toString());
            return null;
        }
    }
    public static MP3 getMP3Tags(String dir) {
        try {
            MP3 mp3 = new MP3(dir);
            return getMP3Tags(mp3);
        } catch (IOException ex) {
            BackendprocessService.getInstance().logger.error("An error occurred while creating mp3 from path: " + dir);
            return null;
        }
    }

    private static List<File> readDirectoriesFromFile(File file) throws IOException {
        List<File> list = new ArrayList<File>();
        if (!file.exists() || !file.isFile()) throw new FileNotFoundException("listing File not found");
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            String fPath = sc.nextLine();
            File f = new File(fPath);
            if (f.exists()) {
                list.add(f);
            } else {
                BackendprocessService.getInstance().logger.warn("File/Directory not found: " + fPath);
            }
        }
        return list;
    }

    public static void indirectIndiceDirectories(File file) throws IOException {
        List<File> list = readDirectoriesFromFile(file);
        for (File dir : list) {
            indiceFiles(dir);
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
        if (mp3.hasErrors()) {                                              // check for tag errors
            List<String> errors = mp3.getErrors();
            for(String error : errors) {
                BackendprocessService.getInstance().logger.error(error + " " + mp3.getPath());    // log tag errors
            }
            mp3.save();
            return false;
        }

        List<String> errors = mp3.validateMPEGFrames();                     // check for MPEG-frame errors
        if (errors.size() != 0) {
            for(String error : errors) {
                BackendprocessService.getInstance().logger.error(error);    // MPEG-frame errors
            }
            return false;
        }
        return true;
    }

    private static void indiceSingleFile(File f) throws FileNotFoundException {
        if (!(f.exists() && f.isFile())) {
            throw new FileNotFoundException();
        }
        if (f.getName().toLowerCase().endsWith(".mp3")) {
            try {
                MP3 mp3 = getMP3Tags(f);
                if(mp3 != null) BackendprocessService.getInstance().database.addEntry(mp3);
            } catch (IOException e) {
                BackendprocessService.getInstance().logger.error(f.toString() + " couldn't be read " + e.toString());
            }
        }
    }
}
