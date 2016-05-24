package com.technologies.gimick.stories;

import android.content.Context;

import com.technologies.gimick.stories.social.TwitterSharing;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
Context ctx;

    @Test
    public void testConvertFahrenheitToCelsius() {
        TwitterSharing tw=new TwitterSharing(ctx);
        tw.shareTweet("Test","Hi how r u");
        //float actual = ConverterUtil.convertCelsiusToFahrenheit(100);
        // expected value is 212
       // float expected = 212;
        // use this method because float is not precise
        //assertEquals("Test Done Successfully", expected,
          //      actual, 0.001);
    }
}