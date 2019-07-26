package com.example.android.kilocounter.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.kilocounter.Helpers.DiaryBundle;
import com.example.android.kilocounter.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;

public class DiaryDeets extends AppCompatActivity {

    ArrayList<DiaryBundle> diaryBundleArrayList;
    DiaryBundle entry;
    int index;
    Gson gson = new Gson();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_deets);

        Context mContext = this;

        // Get a Bundle from the MainActivity, which contains the data to be displayed in detail.
        Bundle data = getIntent().getExtras();
        this.diaryBundleArrayList = (ArrayList<DiaryBundle>) data.get("diaryBundleArrayList");
        assert data != null;
        this.entry = (DiaryBundle) data.getParcelable("data");
        this.index = data.getInt("index");

        TextView nkiTV = (TextView) findViewById(R.id.nkiTV);
        TextView dateTV = (TextView) findViewById(R.id.dateTV);
        TextView breakfastTV = (TextView) findViewById(R.id.breakfastTV);
        TextView lunchTV = (TextView) findViewById(R.id.lunchTV);
        TextView dinnerTV = (TextView) findViewById(R.id.dinnerTV);
        TextView runningTV = (TextView) findViewById(R.id.runningTV);
        TextView gymTV = (TextView) findViewById(R.id.gymTV);
        TextView otherTV = (TextView) findViewById(R.id.otherTV);


        int runningTotal = entry.getNKI();
        nkiTV.setText(String.format("%s %s", String.valueOf(runningTotal), getString(R.string.measure_unit)));
        dateTV.setText(entry.getDate());
        breakfastTV.setText(String.format("%s\n%s %s", getString(R.string.breakfastHeader), entry.getExeArr().get(0).toString(), getString(R.string.measure_unit)));
        lunchTV.setText(String.format("%s\n%s %s", getString(R.string.lunchHeader), entry.getExeArr().get(1).toString(), getString(R.string.measure_unit)));
        dinnerTV.setText(String.format("%s\n%s %s", getString(R.string.dinnerHeading), entry.getExeArr().get(2).toString(), getString(R.string.measure_unit)));
        runningTV.setText(String.format("%s\n%s %s", getString(R.string.runningHeader), entry.getFoodArr().get(0).toString(), getString(R.string.measure_unit)));
        gymTV.setText(String.format("%s\n%s %s", getString(R.string.gymHeader), entry.getFoodArr().get(1).toString(), getString(R.string.measure_unit)));
        otherTV.setText(String.format("%s\n%s %s", getString(R.string.otherHeader), entry.getFoodArr().get(2).toString(), getString(R.string.measure_unit)));
        if (runningTotal == 0){
            nkiTV.setTextColor(Color.GRAY);
        }
        else if (runningTotal < 0){
            nkiTV.setTextColor(Color.RED);

        }
        else{
            nkiTV.setTextColor(Color.GREEN);
        }

        LoadPreferences();
    }

    public void launchCalcClick(View view){
        Intent intent = new Intent(this,CalcActivity.class);

        intent.putExtra("movie", "N/A");
        this.startActivity(intent);
    }

    public void overviewClick(View view){
        Intent intent = new Intent(this,MainActivity.class);
        //intent.putExtra("diaryBundleArrayList", diaryBundleArrayList);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        this.startActivity(intent);
        new Thread(new Runnable() {
            public void run() {
                finishAffinity();
                finish();
            }
        }).start();

    }

    public void nextEntryClick(View view) throws CloneNotSupportedException {
        if (diaryBundleArrayList != null && index < diaryBundleArrayList.size()-1) {
            Intent intent = new Intent(this,DiaryDeets.class);
            DiaryBundle item = new DiaryBundle("null",1);
            item.clone(diaryBundleArrayList.get(index+1));
            intent.putExtra("data", item);
            intent.putExtra("diaryBundleArrayList", diaryBundleArrayList);
            intent.putExtra("index", index+1);
            this.startActivity(intent);
        } else {
            Snackbar.make(view, "This is the last entry fam.", Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show();
        }

    }



    public void prevEntryClick(View view) throws CloneNotSupportedException {
        if (index > 0) {
            Intent intent = new Intent(this,DiaryDeets.class);
            DiaryBundle item = new DiaryBundle("null",1);
            item.clone(diaryBundleArrayList.get(index-1));
            intent.putExtra("data", item);
            intent.putExtra("diaryBundleArrayList", diaryBundleArrayList);
            intent.putExtra("index", (index-1));
            this.startActivity(intent);
        } else {
            Snackbar.make(view, "This is the first entry fam.", Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show();
        }

    }

    private void LoadPreferences() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("preferenceFile",Context.MODE_PRIVATE);
        String listJSON = sharedPreferences.getString("jsonList", null);
        if (listJSON != null) {
            Gson gson = new Gson();
            Type feedsType = new TypeToken<ArrayList<DiaryBundle>>(){}.getType();
            this.diaryBundleArrayList = gson.fromJson(listJSON, feedsType);
        }
    }
}


