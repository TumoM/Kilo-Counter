package com.example.android.kilocounter.Activities;

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
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.kilocounter.R;

import java.text.DateFormat;
import java.util.Objects;

public class CalcActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private TextView foodTotalTV ;
    private TextView exerciseTotalTV;
    private TextView netTotalTV;
    private TextView datePickerTV;
    private EditText breakfastET;
    private EditText runningET;
    private EditText lunchET;
    private EditText dinnerET;
    private EditText gymET;
    private EditText otherET;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        EditText dateTV = (EditText) findViewById(R.id.CalcDatePicker);
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
        breakfastET.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable editable) {
                String foodTotalStr = (String) foodTotalTV.getText();
                Toast.makeText(CalcActivity.this, foodTotalStr, Toast.LENGTH_LONG).show();
                foodTotalTV.setText(editable.toString());
            }
        });

        lunchET.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable editable) {
                foodTotalTV.setText(editable.toString().toUpperCase());
            }
        });

        dinnerET.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable editable) {
                foodTotalTV.setText(editable.toString().toUpperCase());
            }
        });

        runningET.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable editable) {
                foodTotalTV.setText(editable.toString().toUpperCase());
            }
        });

        gymET.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable editable) {
                foodTotalTV.setText(editable.toString().toUpperCase());
            }
        });

        otherET.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable editable) {
                foodTotalTV.setText(editable.toString().toUpperCase());
            }
        });
    }

    // TODO this: private TextWatcher textWatcherConstructor

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
            return new DatePickerDialog(Objects.requireNonNull(getActivity()), (DatePickerDialog.OnDateSetListener) getActivity(), year, month, day);
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
