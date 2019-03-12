package com.nikola.culture;

public class Barley extends Culture {
    final private CultureType culture;

    private static Barley instance = null;

    public static Barley getInstance(){
        if (instance == null){
            instance = new Barley();
        }
        return instance;
    }

    private Barley(){
        this.culture = CultureType.BARLEY;
        this.landArea = "";
    }
}
