package com.example.android.kilocounter.Activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
// import android.app.DialogFragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.icu.util.Calendar;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.kilocounter.Helpers.DiaryBundle;
import com.example.android.kilocounter.R;
import com.google.gson.Gson;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class CalcActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private TextView foodTotalTV ;
    private TextView exerciseTotalTV;
    private TextView netTotalTV;
    private EditText dateTV;
    private EditText breakfastET;
    private EditText runningET;
    private EditText lunchET;
    private EditText dinnerET;
    private EditText gymET;
    private EditText otherET;
    ArrayList<DiaryBundle> diaryEntires = new ArrayList<>(3);
    Gson gson = new Gson();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        this.diaryEntires = getIntent().getParcelableArrayListExtra("list");

        dateTV = (EditText) findViewById(R.id.CalcDatePicker);
        dateTV.setShowSoftInputOnFocus(false);
        breakfastET = (EditText) findViewById(R.id.BreakfastEditText);
        lunchET = (EditText) findViewById(R.id.LunchEditText);
        dinnerET = (EditText) findViewById(R.id.DinnerEditText);
        runningET = (EditText) findViewById(R.id.Exe1EditText);
        gymET = (EditText) findViewById(R.id.Exe2EditText);
        otherET = (EditText) findViewById(R.id.Exe3EditText);
        foodTotalTV = (TextView) findViewById(R.id.FoodTotalValue);
        exerciseTotalTV = (TextView) findViewById(R.id.ExerciseTotalValue);
        netTotalTV = (TextView) findViewById(R.id.NetTotalValue);

        /*
        Text watchers
         */
        breakfastET.addTextChangedListener(textWatcherConstructor(foodTotalTV, lunchET, dinnerET));

        lunchET.addTextChangedListener(textWatcherConstructor(foodTotalTV, breakfastET, dinnerET));

        dinnerET.addTextChangedListener(textWatcherConstructor(foodTotalTV, breakfastET, lunchET));

        runningET.addTextChangedListener(textWatcherConstructor(exerciseTotalTV, gymET, otherET));

        gymET.addTextChangedListener(textWatcherConstructor(exerciseTotalTV, runningET, otherET));

        otherET.addTextChangedListener(textWatcherConstructor(exerciseTotalTV, runningET, gymET));

        foodTotalTV.addTextChangedListener(textWatcherConstructorNet(netTotalTV, foodTotalTV, exerciseTotalTV));

        exerciseTotalTV.addTextChangedListener(textWatcherConstructorNet(netTotalTV, foodTotalTV, exerciseTotalTV));

        // Load any previous info after the text watchers are set.
        LoadPreferences();
    }

    private TextWatcher textWatcherConstructorNet(final TextView targetName, final TextView otherCat1, final TextView otherCat2) {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Boolean cat1 = otherCat1.getText().toString().length() > 0 && otherCat1.getText().toString().length() < 10;
                Boolean cat2 = otherCat2.getText().toString().length() > 0 && otherCat2.getText().toString().length() < 10;
                Boolean thisVal = editable.toString().length() > 0;

                targetName.setText("0");

                Boolean validTarget = (targetName.getText().toString().length() > 0);
                int runningTotal = 0;
                if (validTarget) {
                    runningTotal = Integer.parseInt(targetName.getText().toString());
                }
/*
                if (thisVal) {

                    runningTotal = Integer.parseInt(editable.toString());
                } else {
                    runningTotal = 0;
                    targetName.setText(String.valueOf(runningTotal));

                }*/
                if (cat1) {
                    Log.e("afterTextChangedBOY", "Value: \"" + otherCat1.toString() + "\"");
                    runningTotal += Integer.parseInt(otherCat1.getText().toString());
                }
                if (cat2) {
                    runningTotal -= Integer.parseInt(otherCat2.getText().toString());

                }

                if (runningTotal == 0){
                    targetName.setTextColor(Color.GRAY);
                }
                else if (runningTotal < 0){
                    targetName.setTextColor(Color.RED);

                }
                else{
                    targetName.setTextColor(Color.GREEN);
                }
                targetName.setText(String.valueOf(runningTotal));

            }
        };
    }


    private TextWatcher textWatcherConstructor(final TextView targetName, final EditText otherCat1, final EditText otherCat2){
        return new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Toast.makeText(CalcActivity.this, "Old char seq: " + s.toString(), Toast.LENGTH_SHORT).show();
                /*if (s.length() == 0){
                    targetName.setText("0");
                }
                if (!(targetName.getText().toString().equals(s.toString())) && (targetName.getText().toString().length() > 0)) {
                    targetName.setText(
                            Integer.parseInt(targetName.getText().toString())
                    );
                }*/
            }

            @Override
            public void afterTextChanged(Editable editable) {
                Boolean cat1 = otherCat1.getText().toString().length() > 0 && otherCat1.getText().toString().length() < 10;
                Boolean cat2 = otherCat2.getText().toString().length() > 0 && otherCat2.getText().toString().length() < 10;
                Boolean thisVal = editable.toString().length() > 0;
                Boolean validTarget = (targetName.getText().toString().length() > 0);
                int runningTotal = 0;
                if (validTarget){
                runningTotal = Integer.parseInt(targetName.getText().toString());
                }

                if (thisVal) {

                    runningTotal = Integer.parseInt(editable.toString());
                }
                else{
                    runningTotal = 0;
                    targetName.setText(String.valueOf(runningTotal));

                }
                    if (cat1){
                        Log.e("afterTextChangedBOY", "Value: \""+otherCat1.toString()+"\"");
                        runningTotal += Integer.parseInt(otherCat1.getText().toString());
                    }
                    if (cat2){
                        runningTotal += Integer.parseInt(otherCat2.getText().toString());

                    }

                targetName.setText(String.valueOf(runningTotal));
            }
        };
    }


    private TextWatcher textWatcherConstructor(final TextView targetName){
        return new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //Toast.makeText(CalcActivity.this, "Old char seq: " + s.toString(), Toast.LENGTH_SHORT).show();
                /*if (s.length() == 0){
                    targetName.setText("0");
                }
                if (!(targetName.getText().toString().equals(s.toString())) && (targetName.getText().toString().length() > 0)) {
                    targetName.setText(
                            Integer.parseInt(targetName.getText().toString())
                    );
                }*/
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //Toast.makeText(CalcActivity.this, "New char seq: " + editable.toString(), Toast.LENGTH_SHORT).show();
                String runningTotalStr = targetName.getText().toString();
                int runningTotalInt;
                Toast.makeText(CalcActivity.this, "runningTotalStr1: " + runningTotalStr, Toast.LENGTH_SHORT).show();
                Log.e("runningTotalStr!!!: ", runningTotalStr);
                if (!(runningTotalStr.equals(""))) {
                    runningTotalInt = Integer.parseInt(runningTotalStr);
                }
                else{
                    targetName.setText("0");
                }
                Toast.makeText(CalcActivity.this, runningTotalStr, Toast.LENGTH_LONG).show();
                targetName.setText(editable.toString());
            }
        };
    }

    /*
    This section of code deals with the datePicker used for User input
     */
    public void showDatePickerDialog(View v) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Log.d("onDateSetReturned", String.valueOf(year + ' ' + month + ' ' + dayOfMonth));
        Toast.makeText(this, String.valueOf(year + ' ' + month + ' ' + dayOfMonth), Toast.LENGTH_SHORT).show();
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, month);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        String currentDateString = DateFormat.getDateInstance(DateFormat.DEFAULT).format(c.getTime());

        EditText editText = (EditText) findViewById(R.id.CalcDatePicker);
        editText.setText(currentDateString);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * When called, returns to the previous activity in the stack.
     * @param view Gives us context of the calling application.
     */
    public void goBackClick(View view){
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("jsonData", null);
        editor.remove("jsonData");
        editor.commit();
        finish();
    }

    public void newEntryClick(View view) throws CloneNotSupportedException {
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        // Todo Check if Date + Values present.
            assert dateTV !=null;
            if (dateTV.getText().length() < 1) {
                Snackbar.make(view, "Check the dates brew.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            } else {
                Snackbar.make(view, "Adding the new entry", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                // Todo Add new entry to list
                DiaryBundle diaryBundle = new DiaryBundle();
                diaryBundle.setNKI(Integer.parseInt(netTotalTV.getText().toString()));
                diaryBundle.setDate(dateTV.getText().toString());
                if (!breakfastET.getText().toString().equals("")) {
                    diaryBundle.setFoodArr(0, Integer.parseInt(breakfastET.getText().toString()));
                } else {diaryBundle.setFoodArr(0,0);}
                if (!lunchET.getText().toString().equals("")) {
                    diaryBundle.setFoodArr(1, Integer.parseInt(lunchET.getText().toString()));
                } else {diaryBundle.setFoodArr(1,0);}
                if (!dinnerET.getText().toString().equals("")) {
                    diaryBundle.setFoodArr(2, Integer.parseInt(dinnerET.getText().toString()));
                } else {diaryBundle.setFoodArr(2,0);}
                if (!runningET.getText().toString().equals("")) {
                    diaryBundle.setExeArr(0, Integer.parseInt(runningET.getText().toString()));
                } else {diaryBundle.setExeArr(0,0);}
                if (!gymET.getText().toString().equals("")) {
                    diaryBundle.setExeArr(1, Integer.parseInt(gymET.getText().toString()));
                } else {diaryBundle.setExeArr(1,0);}
                if (!otherET.getText().toString().equals("")) {
                    diaryBundle.setExeArr(2, Integer.parseInt(otherET.getText().toString()));
                } else {diaryBundle.setExeArr(2,0);}
                if (this.diaryEntires != null) {
                    this.diaryEntires.add(diaryBundle);
                }
                else {
                    this.diaryEntires = new ArrayList<>();
                    this.diaryEntires.add(diaryBundle);
                }
                Collections.sort(diaryEntires);
                int position = diaryEntires.indexOf(diaryBundle);
                Intent intent = new Intent(this, DiaryDeets.class);
                intent.putExtra("data", new DiaryBundle(diaryBundle));
                intent.putExtra("list", diaryEntires);
                intent.putExtra("index", position);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                String listJSON = gson.toJson(diaryEntires);
                editor.putString("list",listJSON);
                editor.commit();
                this.startActivity(intent);
                finish();



                // Todo Launch DiaryDeets Activity
            }
    }

    // Todo Finish this. Pull out all EditText text data, including date.
    private void SavePreferences(){
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // Todo Put data into the editor.putExtra("state", button.isEnabled());
        DiaryBundle diaryBundle = new DiaryBundle();
        diaryBundle.setNKI(Integer.parseInt(netTotalTV.getText().toString()));
        diaryBundle.setDate(dateTV.getText().toString());
        if (!breakfastET.getText().toString().equals("")) {
            diaryBundle.setFoodArr(0, Integer.parseInt(breakfastET.getText().toString()));
        } else {diaryBundle.setFoodArr(0,0);}
        if (!lunchET.getText().toString().equals("")) {
            diaryBundle.setFoodArr(1, Integer.parseInt(lunchET.getText().toString()));
        } else {diaryBundle.setFoodArr(1,0);}
        if (!dinnerET.getText().toString().equals("")) {
            diaryBundle.setFoodArr(2, Integer.parseInt(dinnerET.getText().toString()));
        } else {diaryBundle.setFoodArr(2,0);}
        if (!runningET.getText().toString().equals("")) {
            diaryBundle.setExeArr(0, Integer.parseInt(runningET.getText().toString()));
        } else {diaryBundle.setExeArr(0,0);}
        if (!gymET.getText().toString().equals("")) {
            diaryBundle.setExeArr(1, Integer.parseInt(gymET.getText().toString()));
        } else {diaryBundle.setExeArr(1,0);}
        if (!otherET.getText().toString().equals("")) {
            diaryBundle.setExeArr(2, Integer.parseInt(otherET.getText().toString()));
        } else {diaryBundle.setExeArr(2,0);}

        String json = gson.toJson(diaryBundle);
        String listJSON = gson.toJson(diaryEntires);
        editor.putString("jsonData", json);
        editor.putString("jsonList", listJSON);
        editor.commit();   // I missed to save the data to preference here,.
    }

    @NonNull
    private DiaryBundle getDiaryBundle() {
        DiaryBundle diaryBundle = new DiaryBundle();
        diaryBundle.setNKI(Integer.parseInt(netTotalTV.getText().toString()));
        diaryBundle.setDate(dateTV.getText().toString());
        if (!breakfastET.getText().toString().equals("")) {
            diaryBundle.setFoodArr(0, Integer.parseInt(breakfastET.getText().toString()));
        } else {diaryBundle.setFoodArr(0,0);}
        if (!lunchET.getText().toString().equals("")) {
            diaryBundle.setFoodArr(1, Integer.parseInt(lunchET.getText().toString()));
        } else {diaryBundle.setFoodArr(1,0);}
        if (!dinnerET.getText().toString().equals("")) {
            diaryBundle.setFoodArr(2, Integer.parseInt(dinnerET.getText().toString()));
        } else {diaryBundle.setFoodArr(2,0);}
        if (!runningET.getText().toString().equals("")) {
            diaryBundle.setExeArr(0, Integer.parseInt(runningET.getText().toString()));
        } else {diaryBundle.setExeArr(0,0);}
        if (!gymET.getText().toString().equals("")) {
            diaryBundle.setExeArr(1, Integer.parseInt(gymET.getText().toString()));
        } else {diaryBundle.setExeArr(1,0);}
        if (!otherET.getText().toString().equals("")) {
            diaryBundle.setExeArr(2, Integer.parseInt(otherET.getText().toString()));
        } else {diaryBundle.setExeArr(2,0);}
        return diaryBundle;
    }

    private void LoadPreferences(){
        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        String jsonData = sharedPreferences.getString("jsonData", null);
        if (jsonData != null){
            Gson gson = new Gson();
            DiaryBundle diaryBundle = gson.fromJson(jsonData, DiaryBundle.class);
            netTotalTV.setText(String.valueOf(diaryBundle.getNKI()));
            dateTV.setText(diaryBundle.getDate());
            breakfastET.setText(diaryBundle.getFoodArr().get(0) > 0 ? String.valueOf(diaryBundle.getFoodArr().get(0)) : "");
            lunchET.setText(diaryBundle.getFoodArr().get(1) > 0 ? String.valueOf(diaryBundle.getFoodArr().get(1)) : "");
            dinnerET.setText(diaryBundle.getFoodArr().get(2) > 0 ? String.valueOf(diaryBundle.getFoodArr().get(2)) : "");
            runningET.setText(diaryBundle.getExeArr().get(0) > 0 ? String.valueOf(diaryBundle.getExeArr().get(0)) : "");
            gymET.setText(diaryBundle.getExeArr().get(1) > 0 ? String.valueOf(diaryBundle.getExeArr().get(1)) : "");
            otherET.setText(diaryBundle.getExeArr().get(2) > 0 ? String.valueOf(diaryBundle.getExeArr().get(2)) : "");

        }
        // button.setEnabled(state);
    }

    @Override
    public void onBackPressed() {
        SavePreferences();
        super.onBackPressed();
    }


    public static class DatePickerFragment extends DialogFragment{

        String TAG = "SWAG";

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(Objects.requireNonNull(getActivity()), (DatePickerDialog.OnDateSetListener) getActivity(), year, month, day);
        }



        @Override
        public void onDismiss(DialogInterface dialog) {
            super.onDismiss(dialog);
            Log.d(TAG, "onDismiss: Launched");
        }


    }
}
