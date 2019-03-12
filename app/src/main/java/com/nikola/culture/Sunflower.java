package com.nikola.culture;

public class Sunflower extends Culture {
    final private CultureType culture;

    private static Sunflower instance = null;

    public static Sunflower getInstance(){
        if (instance == null){
            instance = new Sunflower();
        }
        return instance;
    }

    private Sunflower(){
        this.culture = CultureType.SUNFLOWER;
        this.landArea = "";
    }
}
