package com.example.davidbezalellaoli.mobile.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by davidbezalellaoli on 11/7/17.
 */

public class ConnectionApi {
    private static final String BASEURL = "http://192.168.43.74:83/api/";
    private static ConnectionApi instance;
    private static ApiModel api;
    private static Retrofit retrofit;

    /*
    * @return Object: ConnectionApi
    */
    public static ConnectionApi getInstance() {
        return (instance == null) ? new ConnectionApi() : instance;
    }

    /*
    * @return object: ApiModel
    */
    public ApiModel getApiModel() {
        return api;
    }


    /*
    * TO-DO: build: Retrofit
    * TO-DO: create: ApiModel
    */
    private ConnectionApi() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        httpClient.addInterceptor(logging);

        retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        api = retrofit.create(ApiModel.class);
    }
}
