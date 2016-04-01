package com.lebron.android50test.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.view.Window;

import com.lebron.android50test.R;

public class ExplodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setAllowEnterTransitionOverlap(true);
        getWindow().setAllowReturnTransitionOverlap(true);
        Explode explode = new Explode();
        explode.setDuration(1000);
        getWindow().setEnterTransition(explode);
        getWindow().setExitTransition(explode);

        setContentView(R.layout.activity_animation);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAfterTransition();
    }
}
