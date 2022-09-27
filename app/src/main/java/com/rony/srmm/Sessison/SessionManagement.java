package com.rony.srmm.Sessison;

import android.content.Context;
import android.content.SharedPreferences;

import com.rony.srmm.Response.SessionDataModel;

public class SessionManagement {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String SHARED_PREF_NAME = "session";
    String SESSION_KEY_1 = "user_password";
    String SESSION_KEY_2 = "access_token";
    String SESSION_KEY_3 = "phone";

    String accessToken;
    String userEmail;
    String password;

    public SessionManagement() {
    }

    public SessionManagement(Context context){
        this.sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        this.editor = sharedPreferences.edit();
    }

    public void setLoginSession(SessionDataModel model) {

        this.userEmail = model.getUserEmail();
        this.accessToken = model.getAccessToken();
        this.password = model.getPassword();

        editor.putString(SESSION_KEY_1, password).commit();
        editor.putString(SESSION_KEY_2, accessToken).commit();
        editor.putString(SESSION_KEY_3, userEmail).commit();

    }

    public SessionDataModel getSessionModel() {

        userEmail = sharedPreferences.getString(SESSION_KEY_3, "null");
        password = sharedPreferences.getString(SESSION_KEY_1, "null");
        accessToken = sharedPreferences.getString(SESSION_KEY_2, "null");

        return new SessionDataModel(accessToken, userEmail, password);

    }

    public void removeLoginSession() {

        editor.putString(SESSION_KEY_1, "null").commit();
        editor.putString(SESSION_KEY_2, "null").commit();
        editor.putString(SESSION_KEY_3, "null").commit();

    }

}
