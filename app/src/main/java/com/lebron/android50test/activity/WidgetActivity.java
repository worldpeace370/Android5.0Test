package com.lebron.android50test.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WidgetActivity extends AppCompatActivity {
    public static final int NONE = 0;
    public static final int LIST_V = 1;
    public static final int LIST_H = 2;
    public static final int GRID_V = 3;
    public static final int GRID_H = 4;
    public static final int STAGGERED_GRID_V = 5;
    public static final int STAGGERED_GRID_H = 6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
