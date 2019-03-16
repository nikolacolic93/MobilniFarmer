package com.nikola.culture;

public class Soy extends Culture {
    final private CultureType culture;

    private static Soy instance = null;

    public static Soy getInstance(){
        if (instance == null){
            instance = new Soy();
        }
        return instance;
    }

    private Soy(){
        super();
        this.culture = CultureType.SOY;
        this.landArea = "";
    }
}
