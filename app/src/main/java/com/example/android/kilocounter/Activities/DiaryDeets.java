package com.example.android.kilocounter.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class DiaryDeets extends AppCompatActivity {

    ArrayList<DiaryBundle> diaryBundleArrayList;
    DiaryBundle entry;
    int index;

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


        nkiTV.setText(String.valueOf(entry.getNKI()));
        dateTV.setText(entry.getDate());
        breakfastTV.setText("Breakfast:\n" + entry.getExeArr().get(0).toString());
        lunchTV.setText("lunch:\n" + entry.getExeArr().get(1).toString());
        dinnerTV.setText("Dinner:\n" + entry.getExeArr().get(2).toString());
        runningTV.setText("Run:\n" + entry.getFoodArr().get(0).toString());
        gymTV.setText("Gym:\n" + entry.getFoodArr().get(1).toString());
        otherTV.setText("Other:\n" + entry.getFoodArr().get(2).toString());

        LoadPreferences();
    }

    public void launchCalcClick(View view){
        Intent intent = new Intent(this,CalcActivity.class);

        intent.putExtra("movie", "N/A");
        this.startActivity(intent);
    }

    public void overviewClick(View view){
        Intent intent = new Intent(this,MainActivity.class);
        intent.putExtra("diaryBundleArrayList", diaryBundleArrayList);
        this.startActivity(intent);
        this.finish();
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


