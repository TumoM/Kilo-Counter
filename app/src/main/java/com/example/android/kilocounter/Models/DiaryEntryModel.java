package com.example.android.kilocounter.Models;

import android.icu.util.Calendar;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.EditText;

import com.example.android.kilocounter.R;

import java.text.DateFormat;

public class DiaryEntryModel implements Comparable<DiaryEntryModel> {
    protected String date;
    protected int NKI;
    protected Calendar cal;

    public DiaryEntryModel(int NKI, String date) {
       String dateTime[] = date.split("/");
            //Log.d("onDateSetReturned", String.valueOf(year + ' ' + month + ' ' + dayOfMonth));
            Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR, Integer.parseInt(dateTime[0]));
            c.set(Calendar.MONTH, Integer.parseInt(dateTime[1]));
            c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateTime[2]));
            String currentDateString = DateFormat.getDateInstance(DateFormat.LONG).format(c.getTime());
        this.date = currentDateString;
        this.NKI = NKI;
    }

    public DiaryEntryModel() {
    }

    @Override
    public int compareTo(@NonNull DiaryEntryModel entry) {
        if (getDate() == null || entry.getDate() == null) {
            return 0;
        }
        return getDate().compareTo(entry.getDate());
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getNKI() {
        return NKI;
    }

    public void setNKI(int NKI) {
        this.NKI = NKI;
    }
}
