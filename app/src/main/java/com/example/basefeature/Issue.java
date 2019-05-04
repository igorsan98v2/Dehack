package com.example.basefeature;

import com.google.android.gms.maps.model.LatLng;

public class Issue {
    private IssueInfo issueInfo;
    private String  photoPath;

    public Issue(IssueInfo issueInfo, String photoPath) {
        this.issueInfo = issueInfo;
        this.photoPath = photoPath;
    }

}
