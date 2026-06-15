package com.naveen.movieticketplatform.enums;

public enum TheaterType {
    MULTIPLEX("Multiplex"),
    SINGLE_SCREEN("Single Screen"),
    IMAX("Imax");
    private final String displayName;


    TheaterType(String name){
        this.displayName=name;
    }

    public String getDisplayName(){
        return this.displayName;
    }
}
