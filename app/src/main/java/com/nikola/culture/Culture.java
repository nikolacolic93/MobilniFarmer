package com.nikola.culture;

public abstract class Culture {
    public enum CultureType {WHEAT, BARLEY,SOY,CORN,SUNFLOWER};
    protected String landArea;

    protected Culture(){

    }

    public String getLandArea() {
        return landArea;
    }

    public void setLandArea(String landArea) {
        this.landArea = landArea;
    }
}
