package com.example.android.kilocounter.Activities;

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
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.kilocounter.Helpers.diaryBundle;
import com.example.android.kilocounter.Models.DiaryEntryModel;
import com.example.android.kilocounter.Helpers.DiaryListAdapter;
import com.example.android.kilocounter.R;

import java.util.ArrayList;
import java.util.Collections;


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

        DiaryEntryModel diaryEntryModel1 = new DiaryEntryModel(100, "2019/05/20");
        DiaryEntryModel diaryEntryModel2 = new DiaryEntryModel(-200, "2019/05/21");
        DiaryEntryModel diaryEntryModel3 = new DiaryEntryModel(500, "2019/05/22");
        DiaryEntryModel diaryEntryModel4 = new DiaryEntryModel(0, "2019/05/23");
        DiaryEntryModel diaryEntryModel5 = new DiaryEntryModel(-200, "2019/05/24");
        DiaryEntryModel diaryEntryModel6 = new DiaryEntryModel(500, "2019/05/25");
        DiaryEntryModel diaryEntryModel7 = new DiaryEntryModel(0, "2019/05/26");

        ArrayList<DiaryEntryModel> diaryEntires = new ArrayList<>();
        diaryEntires.add(diaryEntryModel1);
        diaryEntires.add(diaryEntryModel2);
        diaryEntires.add(diaryEntryModel5);
        diaryEntires.add(diaryEntryModel6);
        diaryEntires.add(diaryEntryModel7);
        diaryEntires.add(diaryEntryModel3);
        diaryEntires.add(diaryEntryModel4);


        Collections.sort(diaryEntires);
        DiaryListAdapter adapter = new DiaryListAdapter(this,R.layout.diary_item,diaryEntires);
        listView.setAdapter(adapter);


        // Sets the ListView onClick() listener after the view is populated.
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DiaryEntryModel item = (DiaryEntryModel) parent.getItemAtPosition(position);

                Intent intent = new Intent(mContext,DiaryDeets.class);

                //Todo Pass the information of the clicked entry to the Details screen.
                intent.putExtra("data", new diaryBundle(item.getDate(),item.getNetCal()));
                intent.putExtra("movie", "N/A");
                //based on item add info to intent
                startActivity(intent);
            }
        });


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
