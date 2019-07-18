package com.example.android.kilocounter.Models;

import android.support.annotation.NonNull;

public class DiaryEntryModel implements Comparable<DiaryEntryModel> {
    String date;
    int netCal;

    public DiaryEntryModel(int netCal, String date) {
        this.date = date;
        this.netCal = netCal;
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

    public int getNetCal() {
        return netCal;
    }

    public void setNetCal(int netCal) {
        this.netCal = netCal;
    }
}
