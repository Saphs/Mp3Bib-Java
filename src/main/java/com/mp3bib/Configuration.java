package com.mp3bib;

import com.google.gson.*;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Configuration {
    public static final String CONFIG_FILE = "settings.conf";

    private static final Gson gson = new Gson();
    private static JsonObject conf = new JsonObject();
    private static boolean confLoaded = false;


    public static void readConfig() throws IOException{
        readConfig(CONFIG_FILE);
    }

    public static void readConfig(String fname) throws IOException {
        File file = new File(fname);
        if (!file.exists() || !file.isFile()) {
            throw new IOException();
        }
        Scanner scn = new Scanner(file);
        String text = "";
        while (scn.hasNext()) {
            text += scn.nextLine();
        }
        JsonElement jelem = gson.fromJson(text, JsonElement.class);
        conf = jelem.getAsJsonObject();
        confLoaded = true;
    }

    public static boolean getConfigAsBool(String key) throws IOException {
        JsonPrimitive primitive = getPrimitive(key);
        if (primitive.isBoolean()) {
            return primitive.getAsBoolean();
        }
        throw new IllegalArgumentException();
    }

    public static String getConfigAsString(String key) throws IOException {
        JsonPrimitive primitive = getPrimitive(key);
        if (primitive.isString()) {
            return primitive.getAsString();
        }
        throw new IllegalArgumentException();
    }

    public static int getConfigAsInt(String key) throws IOException {
        JsonPrimitive primitive = getPrimitive(key);
        if (primitive.isNumber()) {
            return primitive.getAsInt();
        }
        throw new IllegalArgumentException();
    }

    private static JsonPrimitive getPrimitive(String key) throws IOException {
        if (!confLoaded) readConfig();
        if (!conf.has(key)) throw new IllegalArgumentException();
        JsonElement elem = conf.get(key);
        if (elem.isJsonPrimitive()) {
            return elem.getAsJsonPrimitive();
        }
        throw new IllegalArgumentException();
    }
}
