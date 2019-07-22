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
import android.widget.TextView;

import com.example.android.kilocounter.Helpers.DiaryBundle;
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

        DiaryBundle diaryEntryModel1 = new DiaryBundle(100, "2019/05/20");
        DiaryBundle diaryEntryModel2 = new DiaryBundle(-200, "2019/05/21");
        DiaryBundle diaryEntryModel3 = new DiaryBundle(500, "2019/05/22");
        DiaryBundle diaryEntryModel4 = new DiaryBundle(0, "2019/05/23");
        DiaryBundle diaryEntryModel5 = new DiaryBundle(-200, "2019/05/24");
        DiaryBundle diaryEntryModel6 = new DiaryBundle(500, "2019/05/25");
        DiaryBundle diaryEntryModel7 = new DiaryBundle(0, "2019/05/26");

        final ArrayList<DiaryBundle> diaryEntires = new ArrayList<>();
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

        TextView dailyAverageText = (TextView) findViewById(R.id.dailyAverageText);
        int average = 0;
        for (DiaryBundle b:diaryEntires){
            average += b.getNKI();
        }
        average = average/diaryEntires.size();
        dailyAverageText.setText(String.valueOf(average));


        // Sets the ListView onClick() listener after the view is populated.
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DiaryEntryModel item = (DiaryEntryModel) parent.getItemAtPosition(position);

                Intent intent = new Intent(mContext,DiaryDeets.class);

                //Todo Pass the information of the clicked entry to the Details screen.
                intent.putExtra("data", new DiaryBundle(item.getDate(),item.getNKI()));
                intent.putExtra("list", diaryEntires);
                intent.putExtra("index", position);
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
