package com.example.android.kilocounter.Helpers;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.example.android.kilocounter.Models.DiaryEntryModel;

import java.util.ArrayList;
import java.util.Arrays;

public class DiaryBundle extends DiaryEntryModel implements Parcelable {
    private ArrayList<Integer> foodArr = new ArrayList<>();
    private ArrayList<Integer> exeArr = new ArrayList<>();
    private Integer[] arrayInit = new Integer[]{0,0,0};

    @Override
    public int compareTo(@NonNull DiaryEntryModel entry) {
        return super.compareTo(entry);
    }

    public DiaryBundle() {
        super.date = "";
        super.NKI = 0;
        this.foodArr.addAll(Arrays.asList(0,0,0));
        this.exeArr.addAll(Arrays.asList(0,0,0));
    }

    public DiaryBundle(int NKI, String date) {
        this.date = date;
        this.NKI = NKI;
        this.foodArr.addAll(Arrays.asList(0,0,0));
        this.exeArr.addAll(Arrays.asList(0,0,0));
    }

    protected DiaryBundle(Parcel in) {
        this.NKI = in.readInt();
        this.date = in.readString();
        this.foodArr = in.readArrayList(Integer.class.getClassLoader());
        this.exeArr = in.readArrayList(Integer.class.getClassLoader());
    }


    public void clone(DiaryBundle diaryBundle) throws CloneNotSupportedException {
        this.NKI = diaryBundle.getNKI();
        this.date = diaryBundle.getDate();
        this.foodArr = diaryBundle.getFoodArr();
        this.exeArr = diaryBundle.getExeArr();
    }

    DiaryBundle(DiaryBundle diaryBundle) throws CloneNotSupportedException {
        this.clone(diaryBundle);
    }

    public DiaryBundle(String date, int NKI) {
        this.date = date;
        this.NKI = NKI;
        this.foodArr = new ArrayList<Integer>(3);
        this.foodArr.addAll(Arrays.asList(0,0,0));

        this.exeArr = new ArrayList<Integer>(3);
        this.exeArr.addAll(Arrays.asList(0,0,0));

    }

    public DiaryBundle(String date, int NKI, ArrayList<Integer> foodArr, ArrayList<Integer> exeArr) {
        this.date = date;
        this.NKI = NKI;
        this.foodArr = foodArr;
        this.exeArr = exeArr;
    }

    public static final Creator<DiaryBundle> CREATOR = new Creator<DiaryBundle>() {
        @Override
        public DiaryBundle createFromParcel(Parcel in) {
            return new DiaryBundle(in);
        }

        @Override
        public DiaryBundle[] newArray(int size) {
            return new DiaryBundle[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    /** save object in parcel */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(NKI);
        dest.writeString(date);
        dest.writeList(exeArr);
        dest.writeList(foodArr);
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

    public ArrayList<Integer> getFoodArr() {
        return foodArr;
    }


    public ArrayList<Integer> getExeArr() {
        return exeArr;
    }

    public void setExeArr(int index, int val) {
        this.exeArr.set(index,val);
    }

    public void setFoodArr(int index, int val) {
        this.foodArr.set(index,val);

    }

}
