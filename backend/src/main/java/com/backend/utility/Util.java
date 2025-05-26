package com.backend.utility;

import com.google.gson.Gson;

public final class Util {

    private Util(){
        // Prevent instantiation
    }
    
    public static <T> String getJSON(T obj) {
        return new Gson().toJson(obj);
    }
}
