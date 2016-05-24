package com.technologies.gimick.stories;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.technologies.gimick.stories.activities.LoginSignUpActivity;
import com.technologies.gimick.stories.activities.StoriesByCatActivity;

import org.junit.Test;


public class StoriesByCtegoryTest extends ActivityInstrumentationTestCase2<StoriesByCatActivity> {
    public StoriesByCtegoryTest() {
        super(StoriesByCatActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

    }

    @Test
    public void testtextViewNotNull() {
        TextView img = (TextView) getActivity().findViewById(R.id.tvNoData);
        assertNotNull(img);
    }
    @Test
    public void testProgressbarNotNull() {
        ProgressBar img = (ProgressBar) getActivity().findViewById(R.id.pb);
        assertNotNull(img);
    }
}