package com.naveen.movieticketplatform.enums;

public enum TimingsType {
    DEFAULT("Default timings"),
    MANUAL("Manual timings");

    private final String displayName;
    TimingsType(String displayName){
        this.displayName=displayName;
    }
    public String getDisplayName(){
        return this.displayName;
    }
}
