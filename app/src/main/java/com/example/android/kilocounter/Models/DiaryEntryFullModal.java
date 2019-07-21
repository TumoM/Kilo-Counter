package com.example.android.kilocounter.Models;

public class DiaryEntryFullModal {
    private String date;
    private int NKI;
    private int[] foodArr = new int[]{0,0,0};
    private int[] exeArr = new int[]{0,0,0};

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

    public int[] getFoodArr() {
        return foodArr;
    }

    public void setFoodArr(int[] foodArr) {
        this.foodArr = foodArr;
    }

    public int[] getExeArr() {
        return exeArr;
    }

    public void setExeArr(int[] exeArr, int index, int val) {
        this.exeArr[index] = val;
    }

    public void setFoodArr(int index, int val) {
        this.foodArr[index] = val;
    }
}
