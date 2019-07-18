package com.example.android.kilocounter;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
// import android.app.DialogFragment;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.icu.util.Calendar;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;

public class CalcActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        EditText dateTV = (EditText) findViewById(R.id.CalcDatePicker);
        dateTV.setShowSoftInputOnFocus(false);
    }

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

    public void goBackClick(View view){
        finish();
    }

    public void newEntryClick(View view){
        // Todo Check if Date + Values present.
        Snackbar.make(view, "Run check before adding new entry.", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
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
            return new DatePickerDialog(getActivity(), (DatePickerDialog.OnDateSetListener) getActivity(), year, month, day);
        }



        @Override
        public void onDismiss(DialogInterface dialog) {
            super.onDismiss(dialog);
            Log.d(TAG, "onDismiss: Launched");
        }


        public void onDateSet(DatePicker view, int year, int month, int day) {
            // Do something with the date chosen by the user
        }
    }
}
