package com.example.android.kilocounter.Helpers;

import org.json.JSONObject;

import java.util.Date;

public class DiaryEntryModal {
    String date;
    int netCal;

    public DiaryEntryModal(int netCal, String date) {
        this.date = date;
        this.netCal = netCal;
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
