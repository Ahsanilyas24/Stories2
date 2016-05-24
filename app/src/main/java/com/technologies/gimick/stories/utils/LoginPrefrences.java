package com.technologies.gimick.stories.utils;

/*

Shared preferences is work just like seesions. Shared preferences of user will be handled in this class.

 */

import android.app.Activity;
import android.content.SharedPreferences;


public class LoginPrefrences {
    private static final String FILE_NAME = "USER_LOGIN";
    private static final String CODE_FILE_NAME = "USER_CODE";
    private final Activity activity;

    public LoginPrefrences(Activity activity) {
        this.activity = activity;
    }

    public void saveUser(int userId, String email, String password) {
        SharedPreferences.Editor editor = activity.getSharedPreferences(FILE_NAME, activity.MODE_PRIVATE).edit();
        editor.putInt("id", userId);
        editor.putString("email", email);
        editor.putString("password", password);
        editor.commit();
    }

    public void loginUser(boolean flag) {
        SharedPreferences.Editor editor = activity.getSharedPreferences(FILE_NAME, activity.MODE_PRIVATE).edit();
        editor.putBoolean("flag", flag);
        editor.commit();
    }

    public void logOutUser() {
        SharedPreferences.Editor editor = activity.getSharedPreferences(FILE_NAME, activity.MODE_PRIVATE).edit();
        editor.putBoolean("flag", false);
        editor.commit();
    }


    public boolean getLoginFlag() {
        SharedPreferences prefs = activity.getSharedPreferences(FILE_NAME, activity.MODE_PRIVATE);
        return prefs.getBoolean("flag", false);
    }

    /*public UserDTO getUser() {
        SharedPreferences prefs = activity.getSharedPreferences(FILE_NAME, activity.MODE_PRIVATE);
        int userId = prefs.getInt("id", 0);
        String email = prefs.getString("email", "No name defined");//"No name defined" is the default value.
        String password = prefs.getString("password", "");
        return new UserDTO(userId, email, password);
    }*/


    public void setFirstTimeTutorial() {
        SharedPreferences.Editor editor = activity.getSharedPreferences(FILE_NAME, activity.MODE_PRIVATE).edit();
        editor.putBoolean("firstTimeTut", true);
        editor.commit();
    }

    public boolean getFirstTimeTutorial() {
        SharedPreferences prefs = activity.getSharedPreferences(FILE_NAME, activity.MODE_PRIVATE);
        boolean status = prefs.getBoolean("firstTimeTut", false);
        return status;
    }
}
