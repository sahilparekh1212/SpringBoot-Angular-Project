package com.backend.utility;

import com.google.gson.Gson;

public final class Util {

    private Util(){
        // Prevent instantiation
    }
    
    public static <T> String getJSON(T obj) {
        return new Gson().toJson(obj);
    }

    public static String getStackTrace(Exception e){
        return getStackTrace(e,1500);
    }

    public static String getStackTrace(Exception e, int maxLength){
        return e.toString().substring(0, Math.min(e.getStackTrace().length,maxLength));
    }
}
