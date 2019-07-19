package com.example.android.kilocounter.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.android.kilocounter.R;

public class DiaryDeets extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_deets);

        Context mContext = this;
    }

    public void launchCalcClick(View view){
        Intent intent = new Intent(this,CalcActivity.class);

        intent.putExtra("movie", "N/A");
        this.startActivity(intent);
    }
}
