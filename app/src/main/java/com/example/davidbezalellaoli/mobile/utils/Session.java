package com.example.davidbezalellaoli.mobile.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.davidbezalellaoli.mobile.cores.models.Admin;
import com.example.davidbezalellaoli.mobile.cores.models.AuthenticateUser;
import com.example.davidbezalellaoli.mobile.cores.models.Citizen;
import com.google.gson.Gson;

/**
 * Created by davidbezalellaoli on 11/7/17.
 */

public class Session {

    SharedPreferences sharedprf;
    SharedPreferences.Editor editor;

    static Session session;

    Context context;

    private final int PRIVATEMODE = 0;

    public static final String PREFNAME       = "user.session";
    public static final String KEYISUSERLOGGEDIN = "is.user.loggedin";
    public static final String KEYUSERDATA    = "user.data";
    public static final String KEYUSERTOKEN   = "user.token";
    public static final String KEYISCITIZEN = "is.user.citizen";

    public Session (Context context) {
        this.context = context;
        sharedprf = context.getSharedPreferences(PREFNAME, PRIVATEMODE);
        editor = sharedprf.edit();
    }

    public static Session getInstance(Context context) {
        if (session == null) {
            session = new Session(context);
        }
        return session;
    }

    public void createSession(AuthenticateUser authenticateUser, String token, boolean isCitizen) {
        editor.putBoolean(KEYISUSERLOGGEDIN, true);
        if (isCitizen) {
            editor.putString(KEYUSERDATA, new Gson().toJson((Citizen) authenticateUser));
        } else {
            editor.putString(KEYUSERDATA, new Gson().toJson((Admin) authenticateUser));
        }
        editor.putString(KEYUSERTOKEN, token);
        editor.putBoolean(KEYISCITIZEN, isCitizen);
        editor.commit();
    }

    public boolean isUserLoggedIn() {
        return sharedprf.getBoolean(KEYISUSERLOGGEDIN, false);
    }

    public boolean isCitizen() {
        return sharedprf.getBoolean(KEYISCITIZEN, true);
    }

    public AuthenticateUser getUserLoggedIn() {
        return new Gson().fromJson(sharedprf.getString(KEYUSERDATA, ""), AuthenticateUser.class);
    }

    public String getToken() {
        return sharedprf.getString(KEYUSERTOKEN, "");
    }

    public void clearSession () {
        editor.clear();
        editor.commit();
    }
}
