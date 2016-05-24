package com.technologies.gimick.stories.activities;
/*
This is the login signup activty. Login signup is performed using fragment and this activity
 is the main parent activity to hold that fragment
 */


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.technologies.gimick.stories.R;
import com.technologies.gimick.stories.adapters.ViewPagerAdapter;
import com.technologies.gimick.stories.databinding.ActivityLoginSignUpBinding;
import com.technologies.gimick.stories.fragments.LoginFragment;
import com.technologies.gimick.stories.fragments.ParentFragment;
import com.technologies.gimick.stories.fragments.SignUpFragment;
import com.technologies.gimick.stories.interfaces.IChangeLoginSignUp;

import java.util.ArrayList;

public class LoginSignUpActivity extends AppCompatActivity implements IChangeLoginSignUp {

    public static IChangeLoginSignUp listner;
    ActivityLoginSignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login_sign_up);
        listner = this;
        setSupportActionBar(binding.toolbar);
        final LoginFragment loginFrag = new LoginFragment();

        final SignUpFragment signUpFrag = new SignUpFragment();

        ArrayList<ParentFragment> fragments = new ArrayList<ParentFragment>() {{
            add(loginFrag);
            add(signUpFrag);
        }};

        binding.innerContent.vpLoginSignup.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), fragments));

    }

    @Override
    public void signUpListner() {
        binding.innerContent.vpLoginSignup.setCurrentItem(1, true);
    }

    @Override
    public void loginListner() {
        binding.innerContent.vpLoginSignup.setCurrentItem(0, true);
    }
}
