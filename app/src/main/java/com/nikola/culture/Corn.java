package com.nikola.culture;

public class Corn extends Culture {
    final private CultureType culture;

    private static Corn instance = null;

    public static Corn getInstance(){
        if (instance == null){
            instance = new Corn();
        }
        return instance;
    }

    private Corn(){
        super();
        this.culture = CultureType.CORN;
        this.landArea = "";
    }
}
