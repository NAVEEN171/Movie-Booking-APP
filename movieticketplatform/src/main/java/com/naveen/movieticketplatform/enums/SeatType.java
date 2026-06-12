package com.naveen.movieticketplatform.enums;


public enum SeatType {
    REGULAR("Regular Seat"),
    PREMIUM("Premium Seat"),
    RECLINER("Recliner Seat");

    private final String displayName;

    SeatType(String displayName){
        this.displayName=displayName;
    }
    public String getDisplayName(){
        return displayName;
    }
}
