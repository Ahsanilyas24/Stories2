package com.technologies.gimick.stories.utils;

/*
This is a class for sending email. When user forget password and provide email address in the text field then
this class will send user with the email having new password.
 */

import android.content.Context;
import android.widget.Toast;

import com.technologies.gimick.stories.apiinterfaces.IForgetPassword;
import com.technologies.gimick.stories.application.AppController;
import com.technologies.gimick.stories.models.FlatDTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Asad on 5/8/2016.
 */
public class ForgetPasswordApi implements Callback<FlatDTO> {
    private final Context context;

    public ForgetPasswordApi(String email, Context context) {
        this.context = context;
        forgetPassword(email);
    }

    private void forgetPassword(String email) {
        IForgetPassword forgetPassword = AppController.getRetroInstance().create(IForgetPassword.class);
        Call<FlatDTO> callApi = forgetPassword.forgetPassword(email);
        callApi.enqueue(this);
    }

    @Override
    public void onResponse(Call<FlatDTO> call, Response<FlatDTO> response) {
        FlatDTO dto = response.body();
        if (dto.status == 1) {
            Toast.makeText(context, "Email has been sent to your email...\nPlease check your Email...", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Problem Occured", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Call<FlatDTO> call, Throwable t) {

    }
}
