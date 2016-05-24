package com.technologies.gimick.stories.utils;

/*
This is class for declaring and calling progress dialogs.

 */

import android.app.ProgressDialog;
import android.content.Context;


public class Dialogs {

    private static Dialogs instance;
    private static ProgressDialog progressDialog;
    private static  Context context;
    private static void constructonExceptions(){
        instance = new Dialogs();
        progressDialog = new ProgressDialog(context);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setProgressStyle(android.R.style.Widget_DeviceDefault_Light_ProgressBar);
    }

    public static Dialogs getInstance(Context context) {
        Dialogs.context = context;
        if (instance == null) {
            instance = new Dialogs();
            progressDialog = new ProgressDialog(context);
            progressDialog.setIndeterminate(true);
            progressDialog.setCancelable(false);
            progressDialog.setProgressStyle(android.R.style.Widget_DeviceDefault_Light_ProgressBar);
        }
        return instance;
    }

    public void showProgressDialog(String message) {

        try {
            if (!progressDialog.isShowing()) {
                progressDialog.setMessage(message);


                progressDialog.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            constructonExceptions();
            try {
                if (!progressDialog.isShowing()) {
                    progressDialog.setMessage(message);


                    progressDialog.show();
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }

        }
    }

    public void dismissProgressDialog() {
       try {
           progressDialog.dismiss();
       }catch (Exception ex){
           ex.printStackTrace();
       }
    }
}
