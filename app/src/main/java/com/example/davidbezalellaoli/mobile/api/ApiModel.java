package com.example.davidbezalellaoli.mobile.api;

import com.example.davidbezalellaoli.mobile.api.responses.Admin;
import com.example.davidbezalellaoli.mobile.api.responses.Citizen;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by davidbezalellaoli on 11/7/17.
 */

public interface ApiModel {
    @POST("admin/login")
    Call<Admin> login(@Body Map<String, String> requestBody);

    @POST("citizen/login")
    Call<Citizen> loginCitizen(@Body Map<String, String> requestBody);
}
