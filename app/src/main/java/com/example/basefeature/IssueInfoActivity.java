package com.example.basefeature;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class IssueInfoActivity extends DialogFragment {
    private String title;
    private String description;
    private String category;
    private int id;
    private int vouteYes;
    private int vouteNo;


    private void setId(@org.jetbrains.annotations.NotNull Object id){
        this.id = Integer.parseInt(id.toString());
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view =  inflater.inflate(R.layout.issue_layout, container, false);
        return view;
    }
}