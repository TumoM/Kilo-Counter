package com.example.android.kilocounter.Helpers;

import android.support.annotation.NonNull;

import org.json.JSONObject;

import java.util.Date;

public class DiaryEntryModal implements Comparable<DiaryEntryModal> {
    String date;
    int netCal;

    public DiaryEntryModal(int netCal, String date) {
        this.date = date;
        this.netCal = netCal;
    }

    @Override
    public int compareTo(@NonNull DiaryEntryModal entry) {
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

    public int getNetCal() {
        return netCal;
    }

    public void setNetCal(int netCal) {
        this.netCal = netCal;
    }
}
