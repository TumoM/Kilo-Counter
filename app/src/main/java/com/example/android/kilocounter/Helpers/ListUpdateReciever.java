package com.example.android.kilocounter.Helpers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class ListUpdateReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Toast.makeText(context, "List Update Intent Detected.", Toast.LENGTH_LONG).show();
    }
}
