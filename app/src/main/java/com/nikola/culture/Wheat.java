package com.nikola.culture;

public class Wheat extends Culture{
    final private CultureType culture;

    private static Wheat instance = null;

    public static Wheat getInstance(){
        if (instance == null){
            instance = new Wheat();
        }
        return instance;
    }

    private Wheat(){
        this.culture = CultureType.WHEAT;
        this.landArea = "";
    }
}
