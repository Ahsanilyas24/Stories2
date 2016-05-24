package com.technologies.gimick.stories;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.technologies.gimick.stories.activities.DiscActivity;
import com.technologies.gimick.stories.activities.StoriesActivity;

import org.junit.Test;


public class StoriesActivityTest  extends ActivityInstrumentationTestCase2<StoriesActivity> {
    public StoriesActivityTest() {
        super(StoriesActivity.class);
    }

    @Override
    protected  void setUp() throws Exception
    {
        super.setUp();

    }

    @Test
    public void testTextViewNotNull()
    {
        TextView textView=(TextView) getActivity().findViewById(R.id.textView2);
        assertNotNull(textView);
    }
    @Test
    public void testImageviewNotNull()
    {
        ImageView img=(ImageView) getActivity().findViewById(R.id.ivHand);
        assertNotNull(img);
    }
    @Test
    public void testImageview1NotNull()
    {
        ImageView img=(ImageView) getActivity().findViewById(R.id.ivLeft);
        assertNotNull(img);
    }
    @Test
    public void testImageview2NotNull()
    {
        ImageView img=(ImageView) getActivity().findViewById(R.id.ivRight);
        assertNotNull(img);
    }
    @Test
    public void testImageview3NotNull()
    {
        ImageView img=(ImageView) getActivity().findViewById(R.id.ivhandDown);
        assertNotNull(img);
    }

    @Test
    public void testButtonNotNull()
    {
        Button textView4=(Button) getActivity().findViewById(R.id.btnGotIt);
        assertNotNull(textView4);
    }

}
