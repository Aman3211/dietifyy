package com.aman.loginapp;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        hideActionBar();
        hideNavigationBar();
    }

    private void hideActionBar() {
        // Get the support action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            // Hide the action bar
            actionBar.hide();
        }
    }

    private void hideNavigationBar() {
        // Hide both the navigation bar and the status bar
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        // Keep the content from resizing when the navigation bar hides
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
    }
}
