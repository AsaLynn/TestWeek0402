package com.think.testweek0402.listener;

import android.app.Activity;

import com.example.demonstrate.DialogPage;
import com.think.testweek0402.R;
import com.think.testweek0402.activity.Test0Activity;

/**
 * Created by think on 2018/3/13.
 */

public class PageItem0 implements DialogPage.OnDialogItemListener {

    private final Activity mActivity;

    public PageItem0(Activity activity) {
        mActivity = activity;
    }

    @Override
    public Activity getActivity() {
        return mActivity;
    }

    @Override
    public String getTitle() {
        return mActivity
                .getResources()
                .getString(R.string.test4_name)
                .concat(
                        mActivity
                                .getResources()
                                .getString(R.string.week_num2)
                ).concat(mActivity
                        .getResources()
                        .getString(R.string.page_num2)
                );
    }

    @Override
    public Class<?> getStartActivity(int which) {
        if ( which == 0){
            return Test0Activity.class;
        }

        return null;
    }
}
