package com.technologies.gimick.stories.fragments;

/*
This is login ftragment class. All login process is done in this class. It will get email and password from the
 user and send it to the server for authorization and verification. After verification it will redirect user to main screen
*/

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.technologies.gimick.stories.R;
import com.technologies.gimick.stories.activities.LoginSignUpActivity;
import com.technologies.gimick.stories.activities.StoriesActivity;
import com.technologies.gimick.stories.apiinterfaces.UserLoginSignUp;
import com.technologies.gimick.stories.application.AppController;
import com.technologies.gimick.stories.databinding.FragmentLoginBinding;
import com.technologies.gimick.stories.models.FlatDTO;
import com.technologies.gimick.stories.utils.Constants;
import com.technologies.gimick.stories.utils.Dialogs;
import com.technologies.gimick.stories.utils.LoginPrefrences;
import com.technologies.gimick.stories.utils.PasswordEncrypter;
import com.technologies.gimick.stories.utils.UiAlertDialog;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginFragment extends ParentFragment implements View.OnClickListener, Callback<FlatDTO> {

    FragmentLoginBinding binding;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        binding.btnSignIn.setOnClickListener(this);
        binding.btnSignUp.setOnClickListener(this);
        binding.appComBtnSkip.setOnClickListener(this);
        binding.appComBtnForgotPassword.setOnClickListener(this);
        return binding.getRoot();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSignIn:
                String email = binding.tilEmail.getEditText().getText().toString();
                String pass = binding.tilPassword.getEditText().getText().toString();
                String encPass = PasswordEncrypter.getInstance().getEncryptedPassword(pass + Constants.SALT_KEY);

                Log.d("ENC_PASS", encPass);

                if (email != null && email.length() > 0 && pass != null && pass.length() > 0) {
                    if (pass.length() >= 5) {
                        Dialogs.getInstance(getContext()).showProgressDialog("Signing In...");
                        UserLoginSignUp login = AppController.getInstace().getRetroInstance().create(UserLoginSignUp.class);
                        Call<FlatDTO> callLogin = login.signIn(email, encPass);
                        callLogin.enqueue(this);
                    } else {
                        binding.tilPassword.getEditText().setError("Password must be atleast 5 characters");
                    }
                } else {
                    binding.tilEmail.getEditText().setError("Email Empty!!");
                    binding.tilPassword.getEditText().setError("Password Empty");
                }
                break;
            case R.id.btnSignUp:
                clearFields();
                if (LoginSignUpActivity.listner != null) {
                    LoginSignUpActivity.listner.signUpListner();
                }
                break;
            case R.id.appComBtn_Skip:
                getActivity().finish();
                startActivity(new Intent(getContext(), StoriesActivity.class));
                break;
            case R.id.appComBtn_ForgotPassword:
                UiAlertDialog.forgetPassDialog(getActivity(), getContext(), "Forget Password");
                break;
        }

    }

    private void clearFields() {
        binding.tilEmail.getEditText().setText("");
        binding.tilPassword.getEditText().setText("");
    }

    @Override
    public void onResponse(Call<FlatDTO> call, Response<FlatDTO> response) {
        Dialogs.getInstance(getContext()).dismissProgressDialog();
        FlatDTO dto = response.body();
        if (dto.status == 1) {
            clearFields();
            new LoginPrefrences(getActivity()).loginUser(true);
            getActivity().finish();
            startActivity(new Intent(getContext(), StoriesActivity.class));
        } else {
            Toast.makeText(getContext(), "Email or password is not correct", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Call<FlatDTO> call, Throwable t) {
        Dialogs.getInstance(getContext()).dismissProgressDialog();
        Toast.makeText(getContext(), "Problem Occured", Toast.LENGTH_SHORT).show();

    }
}
