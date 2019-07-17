package com.example.android.kilocounter.Helpers;

import android.content.Context;
import android.graphics.Movie;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android.kilocounter.MainActivity;
import com.example.android.kilocounter.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DiaryListAdapter extends ArrayAdapter<DiaryEntryModal> {

    private Context mContext;
    private int mResource;

    /**
     * Constructor
     *
     * @param context  The current context.
     * @param resource The resource ID for a layout file containing a TextView to use when
     *                 instantiating views.
     * @param objects  The objects to represent in the ListView.
     */
    public DiaryListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<DiaryEntryModal> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        int netCal = getItem(position).getNetCal();
        String date = getItem(position).getDate();

        DiaryEntryModal diaryEntryModal = new DiaryEntryModal(netCal, date);


        // Todo make this thread safe?
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
        }

        TextView kiloCal = (TextView) convertView.findViewById(R.id.KilojoulCountTextView);
        TextView dateText = (TextView) convertView.findViewById(R.id.DiaryDateTextView);

        kiloCal.setText(Integer.toString(netCal));
        dateText.setText(date);

        return convertView;
    }
}
