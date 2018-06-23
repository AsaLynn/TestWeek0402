package com.think.testweek0402;

import com.example.demonstrate.DialogPage;
import com.example.demonstrate.FirstActivity;
import com.think.testweek0402.listener.PageItem0;

public class MainActivity extends FirstActivity {


    @Override
    protected void click0() {
        DialogPage.getInstance()
                .setOnOnDialogItemListener(new PageItem0(this));
    }
}
