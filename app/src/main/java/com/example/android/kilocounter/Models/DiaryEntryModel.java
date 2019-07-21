package com.example.android.kilocounter.Models;

import android.support.annotation.NonNull;

public class DiaryEntryModel implements Comparable<DiaryEntryModel> {
    String date;
    int NKI;

    public DiaryEntryModel(int NKI, String date) {
        this.date = date;
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
