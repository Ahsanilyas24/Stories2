package com.technologies.gimick.stories.utils;

/*
This class is used for password changing purposes. It will provide user with three input fileds 1 for
old password and 2 for new and confirm password. And this class will chane user password.

 */

import android.content.Context;
import android.widget.Toast;

import com.technologies.gimick.stories.apiinterfaces.IForgetPassword;
import com.technologies.gimick.stories.application.AppController;
import com.technologies.gimick.stories.models.FlatDTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ResetPasswordApi implements Callback<FlatDTO> {
    private final Context context;

    public ResetPasswordApi(Context context) {
        this.context = context;

    }

    public void resetPassword(String email, String oldPass, String newPass) {
        IForgetPassword forgetPassword = AppController.getRetroInstance().create(IForgetPassword.class);
        Call<FlatDTO> callApi = forgetPassword.resetPassword(email, oldPass, newPass);
        callApi.enqueue(this);
    }

    @Override
    public void onResponse(Call<FlatDTO> call, Response<FlatDTO> response) {
        FlatDTO dto = response.body();
        if (dto.status == 1) {
            Toast.makeText(context, "Your Password has been changed...", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Problem Occured", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Call<FlatDTO> call, Throwable t) {

    }
}
