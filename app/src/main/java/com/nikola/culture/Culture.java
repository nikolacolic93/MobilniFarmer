package com.nikola.culture;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;

public abstract class Culture implements Serializable {
    private static final long serialVersionUID = 1L;
    public enum CultureType {WHEAT, BARLEY,SOY,CORN,SUNFLOWER};
    private static HashSet<Culture> cultures;
    private static HashMap<String, Integer> cultureInfo;

    protected String landArea;

    protected Culture(){
    }

    public String getLandArea() {
        return landArea;
    }

    public void setLandArea(String landArea) {
        this.landArea = landArea;
    }

    public static HashSet<Culture> getCultures() {
        if (cultures == null){
            cultures = new HashSet<>(0);
        }
        return cultures;
    }

    public static HashMap<String, Integer> getCultureInfo() {
        if (cultureInfo == null){
            cultureInfo = new HashMap<>(0);
        }
        return cultureInfo;
    }

}
