package com.example.android.kilocounter.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.android.kilocounter.Helpers.diaryBundle;
import com.example.android.kilocounter.R;

public class DiaryDeets extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_deets);

        Context mContext = this;

        Bundle data = getIntent().getExtras();
        assert data != null;
        diaryBundle entry = (diaryBundle) data.getParcelable("data");

        TextView textView = (TextView) findViewById(R.id.nkiTV);
        textView.setText(String.valueOf(entry.getNKI()));

    }

    public void launchCalcClick(View view){
        Intent intent = new Intent(this,CalcActivity.class);

        intent.putExtra("movie", "N/A");
        this.startActivity(intent);
    }
}
