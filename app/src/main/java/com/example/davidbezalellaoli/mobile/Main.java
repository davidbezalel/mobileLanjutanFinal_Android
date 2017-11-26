package com.example.davidbezalellaoli.mobile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.davidbezalellaoli.mobile.modules.auth.activities.AuthNavigation;
import com.example.davidbezalellaoli.mobile.utils.Session;

public class Main extends AppCompatActivity {

    Intent _intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        if (!Session.getInstance(this).isUserLoggedIn()) {
            _intent = new Intent(this, AuthNavigation.class);
        } else if (Session.getInstance(this).isCitizen()) {
            _intent = new Intent(this, com.example.davidbezalellaoli.mobile.modules.citizen.activities.Main.class);
        }

        startActivity(_intent);
        finish();
    }
}
