package com.technologies.gimick.stories;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.ImageView;
import android.widget.TextView;

import com.technologies.gimick.stories.activities.LoginSignUpActivity;
import com.technologies.gimick.stories.activities.StoriesActivity;

import org.junit.Test;

/**
 * Created by Khubab Hamza on 5/24/2016.
 */
public class LoginSignupMainTest  extends ActivityInstrumentationTestCase2<LoginSignUpActivity> {
    public LoginSignupMainTest() {
        super(LoginSignUpActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

    }

    @Test
    public void testImageviewNotNull() {
        ImageView img = (ImageView) getActivity().findViewById(R.id.ivImage);
        assertNotNull(img);
    }
}