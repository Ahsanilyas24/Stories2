package com.technologies.gimick.stories;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.technologies.gimick.stories.activities.DiscActivity;
import com.technologies.gimick.stories.activities.DiscriptionActivity;

import org.junit.Test;

/**
 * Created by Khubab Hamza on 5/24/2016.
 */
public class DiscriptionActivityTest extends ActivityInstrumentationTestCase2<DiscriptionActivity> {
    public DiscriptionActivityTest() {
        super(DiscriptionActivity.class);
    }

    @Override
    protected  void setUp() throws Exception
    {
        super.setUp();

    }

    @Test
    public void testTextViewNotNull()
    {
        TextView textView=(TextView) getActivity().findViewById(R.id.tvto);
        assertNotNull(textView);
    }
    @Test
    public void testImageviewNotNull()
    {
        ImageView img=(ImageView) getActivity().findViewById(R.id.iv_MainImage);
        assertNotNull(img);
    }
    @Test
    public void testTextView2NotNull()
    {
        TextView textView2=(TextView) getActivity().findViewById(R.id.tvflip);
        assertNotNull(textView2);
    }
    @Test
    public void testTextView3NotNull()
    {
        TextView textView3=(TextView) getActivity().findViewById(R.id.tvwhat);
        assertNotNull(textView3);
    }
    @Test
    public void testTextView4NotNull()
    {
        TextView textView4=(TextView) getActivity().findViewById(R.id.tvdisc1);
        assertNotNull(textView4);
    }
    @Test
    public void testTextView5NotNull()
    {
        TextView textView5=(TextView) getActivity().findViewById(R.id.tvdisc2);
        assertNotNull(textView5);
    }
    @Test
    public void testTextView6NotNull()
    {
        TextView textView6=(TextView) getActivity().findViewById(R.id.tvdisc3);
        assertNotNull(textView6);
    }

    @Test
    public void testButtonNotNull()
    {
        Button btn=(Button) getActivity().findViewById(R.id.btndiscr);
        assertNotNull(btn);
    }
}
