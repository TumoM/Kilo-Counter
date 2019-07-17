package com.example.android.kilocounter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.android.kilocounter.Helpers.DiaryEntryModal;
import com.example.android.kilocounter.Helpers.DiaryListAdapter;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mContext = this;

        ListView listView = (ListView) findViewById(R.id.DiaryListView);

        DiaryEntryModal diaryEntryModal1 = new DiaryEntryModal(100, "2019/05/20");
        DiaryEntryModal diaryEntryModal2 = new DiaryEntryModal(-200, "2019/05/21");
        DiaryEntryModal diaryEntryModal3 = new DiaryEntryModal(500, "2019/05/22");
        DiaryEntryModal diaryEntryModal4 = new DiaryEntryModal(0, "2019/05/23");
        DiaryEntryModal diaryEntryModal5 = new DiaryEntryModal(-200, "2019/05/24");
        DiaryEntryModal diaryEntryModal6 = new DiaryEntryModal(500, "2019/05/25");
        DiaryEntryModal diaryEntryModal7 = new DiaryEntryModal(0, "2019/05/26");

        ArrayList<DiaryEntryModal> diaryEntires = new ArrayList<>();
        diaryEntires.add(diaryEntryModal1);
        diaryEntires.add(diaryEntryModal2);
        diaryEntires.add(diaryEntryModal3);
        diaryEntires.add(diaryEntryModal4);
        diaryEntires.add(diaryEntryModal5);
        diaryEntires.add(diaryEntryModal6);
        diaryEntires.add(diaryEntryModal7);


        DiaryListAdapter adapter = new DiaryListAdapter(this,R.layout.diary_item,diaryEntires);
        listView.setAdapter(adapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.CalcBtn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent = new Intent(mContext,CalcActivity.class);

                intent.putExtra("movie", "N/A");
                mContext.startActivity(intent);
            }
        });
    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
