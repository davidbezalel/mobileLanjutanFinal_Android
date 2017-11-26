package com.example.davidbezalellaoli.mobile.modules.auth.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.davidbezalellaoli.mobile.R;

public class AuthNavigation extends AppCompatActivity {

    Button loginCitizen, loginAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_navigation);

        initview();
        event();
    }

    private void initview() {
        loginCitizen = (Button) findViewById(R.id.authnavigation_citizen);
        loginAdmin = (Button) findViewById(R.id.authnavigation_admin);
    }

    private void event() {
        loginAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent _intent = new Intent(getApplicationContext(), LoginAdmin.class);
                startActivity(_intent);
            }
        });

        loginCitizen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent _intent = new Intent(getApplicationContext(), LoginCitizen.class);
                startActivity(_intent);
            }
        });
    }
}
