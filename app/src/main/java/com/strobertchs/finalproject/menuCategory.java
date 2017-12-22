package com.strobertchs.finalproject;

public class menuCategory {
    private String cName;
    private String cImage;

    public menuCategory(String cName, String cImage) {
        this.cName = cName;
        this.cImage = cImage;
    }

    public String getcName() {
        return  this.cName;
    }

    public void setcName(String newName) {
        this.cName = newName;
    }

    public String getcImage() {
        return this.cImage;
    }

    public void setcImage(String newImage) {
        this.cImage = newImage;
    }
}
