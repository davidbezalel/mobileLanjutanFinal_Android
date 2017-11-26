package com.example.davidbezalellaoli.mobile.api.presenters;

import android.content.Context;

import com.example.davidbezalellaoli.mobile.R;
import com.example.davidbezalellaoli.mobile.api.ConnectionApi;
import com.example.davidbezalellaoli.mobile.api.ResponseCallback;
import com.example.davidbezalellaoli.mobile.api.responses.Admin;
import com.example.davidbezalellaoli.mobile.api.responses.Citizen;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by davidbezalellaoli on 11/8/17.
 */

public class Auth {
    private static Auth instance;
    private ResponseCallback responseCallback;

    public Auth(ResponseCallback responseCallback) {
        this.responseCallback = responseCallback;
    }

    public static Auth getInstance(ResponseCallback responseCallback) {
        if (instance == null)
            instance = new Auth(responseCallback);
        return instance;
    }

    public void loginAdmin (String username, String password) {
        Map<String, String> _requestBody = new HashMap<>();
        _requestBody.put("username", username);
        _requestBody.put("password", password);
        ConnectionApi.getInstance().getApiModel().login(_requestBody).enqueue(new Callback<Admin>() {
            @Override
            public void onResponse(Call<Admin> call, Response<Admin> response) {
                if (response.body().code == 200) {
                    responseCallback.success(response.body());
                } else if (response.body().code == 401 || response.body().code == 400) {
                    responseCallback.fail(response.body().message);
                } else if (response.body().code == 500) {
                    responseCallback.internalServerError(R.string.internal_server_error);
                }
            }

            @Override
            public void onFailure(Call<Admin> call, Throwable t) {
                responseCallback.internalServerError(R.string.internal_server_error);
            }
        });
    }

    public void loginCitizen (String nik, String password) {
        Map<String, String> _requestBody = new HashMap<>();
        _requestBody.put("nik", nik);
        _requestBody.put("password", password);

        ConnectionApi.getInstance().getApiModel().loginCitizen(_requestBody).enqueue(new Callback<Citizen>() {
            @Override
            public void onResponse(Call<Citizen> call, Response<Citizen> response) {
                if (response.body().code == 200) {
                    responseCallback.success(response.body());
                } else if (response.body().code == 401 || response.body().code == 400) {
                    responseCallback.fail(response.body().message);
                } else if (response.body().code == 500) {
                    responseCallback.internalServerError(R.string.internal_server_error);
                }
            }

            @Override
            public void onFailure(Call<Citizen> call, Throwable t) {
                responseCallback.internalServerError(R.string.internal_server_error);
            }
        });
    }

}
