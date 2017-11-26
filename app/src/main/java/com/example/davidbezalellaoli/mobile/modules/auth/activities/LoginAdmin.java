package com.example.davidbezalellaoli.mobile.modules.auth.activities;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.davidbezalellaoli.mobile.R;
import com.example.davidbezalellaoli.mobile.api.ResponseCallback;
import com.example.davidbezalellaoli.mobile.api.presenters.Auth;
import com.example.davidbezalellaoli.mobile.cores.models.Admin;
import com.example.davidbezalellaoli.mobile.cores.models.Response;

public class LoginAdmin extends AppCompatActivity implements ResponseCallback {

    EditText username, password;
    Button action;
    AlertDialog.Builder alertDialogBuilder;
    AlertDialog alertDialog;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_admin);

        initview();
        initobject();
        event();
    }

    private void initobject() {
        alertDialogBuilder = new AlertDialog.Builder(this);
    }

    private void event() {
        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                if (TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(password.getText().toString())) {
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
                    Auth.getInstance(LoginAdmin.this).loginAdmin(username.getText().toString(), password.getText().toString());
                }
            }
        });
    }

    private void initview() {
        username = (EditText) findViewById(R.id.loginadmin_username);
        password = (EditText) findViewById(R.id.loginadmin_password);
        action = (Button) findViewById(R.id.loginadmin_action);
        progressBar = (ProgressBar) findViewById(R.id.loginadmin_progress);

        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void success(Response response) {
        progressBar.setVisibility(View.GONE);
        Admin _admin = ((com.example.davidbezalellaoli.mobile.api.responses.Admin) response).data;
        Toast.makeText(this, _admin.token, Toast.LENGTH_SHORT).show();
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
