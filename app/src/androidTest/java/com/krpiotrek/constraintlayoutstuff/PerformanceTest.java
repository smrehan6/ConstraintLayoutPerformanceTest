package com.krpiotrek.constraintlayoutstuff;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;

@RunWith(AndroidJUnit4.class)
public class PerformanceTest {

    @Test
    public void test() {
        final long linearLayoutTime = getLayoutTime(R.layout.item_old_linear);
        final long relativeLayoutTime = getLayoutTime(R.layout.item_old_relative);
        final long constraintLayoutTime = getLayoutTime(R.layout.item_new);

        Log.i("time", "Linear : " + linearLayoutTime);
        Log.i("time", "Relative : " + relativeLayoutTime);
        Log.i("time", "Constraint : " + constraintLayoutTime);
    }

    private long getLayoutTime(int layoutRes) {
        final Context targetContext = getInstrumentation().getTargetContext();
        final LayoutInflater layoutInflater = LayoutInflater.from(targetContext);

        final long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            final View view = layoutInflater.inflate(layoutRes, null);
            view.setLayoutParams(new ViewGroup.LayoutParams(0, 0));

            view.measure(View.MeasureSpec.makeMeasureSpec(1000, View.MeasureSpec.EXACTLY), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            final int measuredHeight = view.getMeasuredHeight();
            final int measuredWidth = view.getMeasuredWidth();

            view.layout(0, 0, measuredWidth, measuredHeight);
        }
        return System.currentTimeMillis() - startTime;
    }
}
