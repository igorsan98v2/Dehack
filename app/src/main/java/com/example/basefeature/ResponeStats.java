package com.example.basefeature;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponeStats {
    @SerializedName("permissionGranted")
    @Expose
    private boolean isGranted;

    public boolean isGranted() {
        return isGranted;
    }
    public void setGranted(String granted) {
        isGranted =Boolean.getBoolean(granted);
    }
    public void setGranted(boolean granted) {
        isGranted = granted;
    }

}
