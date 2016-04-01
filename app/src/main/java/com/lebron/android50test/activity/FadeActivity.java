package com.lebron.android50test.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.view.Window;

import com.lebron.android50test.R;

public class FadeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        getWindow().setAllowEnterTransitionOverlap(true);
        getWindow().setAllowReturnTransitionOverlap(true);
        setContentView(R.layout.activity_animation);

        Fade fade = new Fade();
        fade.setDuration(5000);
        //fade.addTarget(findViewById(R.id.view));
        getWindow().setEnterTransition(fade);
        getWindow().setExitTransition(fade);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAfterTransition();
    }
}
