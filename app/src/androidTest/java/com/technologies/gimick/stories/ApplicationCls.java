package com.technologies.gimick.stories;

import android.app.Application;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;
import android.test.ApplicationTestCase;
import android.test.suitebuilder.annotation.LargeTest;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.ImageView;
import android.widget.TextView;

import com.technologies.gimick.stories.activities.SplashActivity;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ApplicationCls extends ActivityInstrumentationTestCase2<SplashActivity> {

    ImageView textView;

    public ApplicationCls() {
        super(SplashActivity.class);
    }

    @Override
    protected  void setUp() throws Exception
    {
        super.setUp();

    }

    @Test
   public void testTextViewNotNull()
    {
        textView=(ImageView) getActivity().findViewById(R.id.iv_MainImage);
        assertNull(textView);
    }
}