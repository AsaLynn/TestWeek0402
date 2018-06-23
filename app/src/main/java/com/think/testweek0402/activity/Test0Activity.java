package com.think.testweek0402.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.think.testweek0402.R;

import org.json.JSONException;
import org.json.JSONObject;

public class Test0Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test0);
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setTitle("自定义控件圆形百分比");
        try {
            JSONObject jsonObject = new JSONObject("");
            Object opt = jsonObject.opt("");
            String s = jsonObject.optString("");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


}
