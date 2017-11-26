package com.example.davidbezalellaoli.mobile.modules.auth.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.davidbezalellaoli.mobile.R;
import com.example.davidbezalellaoli.mobile.api.ResponseCallback;
import com.example.davidbezalellaoli.mobile.api.presenters.Auth;
import com.example.davidbezalellaoli.mobile.cores.models.AuthenticateUser;
import com.example.davidbezalellaoli.mobile.cores.models.Citizen;
import com.example.davidbezalellaoli.mobile.cores.models.Response;
import com.example.davidbezalellaoli.mobile.modules.citizen.activities.Main;
import com.example.davidbezalellaoli.mobile.utils.Session;

public class LoginCitizen extends AppCompatActivity implements ResponseCallback {

    EditText nik, password;
    Button action;
    ProgressBar progressBar;
    AlertDialog alertDialog;
    AlertDialog.Builder alertDialogBuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_citizen);

        initview();
        initobject();
        event();
    }

    private void event() {
        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                if (TextUtils.isEmpty(nik.getText().toString()) || TextUtils.isEmpty(password.getText().toString())) {
                    progressBar.setVisibility(View.GONE);
                    alertDialogBuilder.setMessage("Harap isi semua field").setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    });
                    alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                } else {
                    Auth.getInstance(LoginCitizen.this).loginCitizen(nik.getText().toString(), password.getText().toString());
                }
            }
        });
    }

    private void initobject() {
        alertDialogBuilder = new AlertDialog.Builder(this);
    }

    private void initview() {
        nik = (EditText) findViewById(R.id.logincitizen_nik);
        password = (EditText) findViewById(R.id.logincitizen_password);
        action = (Button) findViewById(R.id.logincitizen_action);
        progressBar = (ProgressBar) findViewById(R.id.logincitizen_progress);

        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void success(Response response) {
        progressBar.setVisibility(View.GONE);
        Citizen _citizen = ((com.example.davidbezalellaoli.mobile.api.responses.Citizen)response).data;
        AuthenticateUser _user = (AuthenticateUser) _citizen;
        Session.getInstance(LoginCitizen.this).createSession(_user, _citizen.token, true);
        Intent _intent = new Intent(LoginCitizen.this, Main.class);
        startActivity(_intent);
        finish();
    }

    @Override
    public void fail(String message) {
        progressBar.setVisibility(View.GONE);
        alertDialogBuilder.setMessage(message).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void internalServerError(int message) {
        progressBar.setVisibility(View.GONE);
        alertDialogBuilder.setMessage(message).setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}
