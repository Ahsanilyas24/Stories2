package com.technologies.gimick.stories.fragments;

/*
This is main signup fragment used for all signup processed. It will get users credentials
 and transfer it to the server for user registration
*/


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.technologies.gimick.stories.R;
import com.technologies.gimick.stories.activities.LoginSignUpActivity;
import com.technologies.gimick.stories.apiinterfaces.UserLoginSignUp;
import com.technologies.gimick.stories.application.AppController;
import com.technologies.gimick.stories.databinding.FragmentSignupBinding;
import com.technologies.gimick.stories.models.FlatDTO;
import com.technologies.gimick.stories.utils.Constants;
import com.technologies.gimick.stories.utils.Dialogs;
import com.technologies.gimick.stories.utils.PasswordEncrypter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SignUpFragment extends ParentFragment implements View.OnClickListener, Callback<FlatDTO> {

    FragmentSignupBinding binding;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_signup, container, false);
        binding.btnBackSignIn.setOnClickListener(this);
        binding.btnRegister.setOnClickListener(this);
        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnRegister:
                String name = binding.tilName.getEditText().getText().toString();
                String email = binding.tilEmail.getEditText().getText().toString();
                String pass = binding.tilPassword.getEditText().getText().toString();

                if (email != null && email.length() > 0 && pass != null && pass.length() > 0 && name != null && name.length() > 0) {
                    if (pass.length() >= 5) {
                        Dialogs.getInstance(getContext()).showProgressDialog("Creating Account");
                        String encPass = PasswordEncrypter.getInstance().getEncryptedPassword(pass + Constants.SALT_KEY);
                        UserLoginSignUp userSIgnUp = AppController.getInstace().getRetroInstance().create(UserLoginSignUp.class);
                        Call<FlatDTO> call = userSIgnUp.signUp(name, email, encPass);
                        call.enqueue(this);
                    } else {
                        binding.tilPassword.getEditText().setError("Password must be atleast 5 characters");
                    }
                } else {
                    binding.tilName.getEditText().setError("Name Empty!!");
                    binding.tilEmail.getEditText().setError("Email Empty!!");
                    binding.tilPassword.getEditText().setError("Password Empty!!");

                }
                break;
            case R.id.btnBackSignIn:
                if (LoginSignUpActivity.listner != null) {
                    LoginSignUpActivity.listner.loginListner();
                }
                break;
        }

    }

    @Override
    public void onResponse(Call<FlatDTO> call, Response<FlatDTO> response) {
        Dialogs.getInstance(getContext()).dismissProgressDialog();
        FlatDTO dto = response.body();
        if (dto.status == 1) {
            Toast.makeText(getContext(), "SIgnUP Successful... Please Login to Continue", Toast.LENGTH_LONG).show();
            binding.tilName.getEditText().setText("");
            binding.tilEmail.getEditText().setText("");
            binding.tilPassword.getEditText().setText("");
            if (LoginSignUpActivity.listner != null) {
                LoginSignUpActivity.listner.loginListner();
            } else {
                Toast.makeText(getContext(), "Account Already Exists", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onFailure(Call<FlatDTO> call, Throwable t) {
        Dialogs.getInstance(getContext()).dismissProgressDialog();
        Toast.makeText(getContext(), "Problem Occured", Toast.LENGTH_SHORT).show();

    }
}
