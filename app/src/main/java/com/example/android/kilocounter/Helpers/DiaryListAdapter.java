package com.example.android.kilocounter.Helpers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.kilocounter.Models.DiaryEntryModel;
import com.example.android.kilocounter.R;

import java.util.ArrayList;

public class DiaryListAdapter extends ArrayAdapter<DiaryEntryModel> {

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
    public DiaryListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<DiaryEntryModel> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        int netCal = getItem(position).getNetCal();
        String date = getItem(position).getDate();

        DiaryEntryModel diaryEntryModel = new DiaryEntryModel(netCal, date);


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

    @Nullable
    @Override
    public DiaryEntryModel getItem(int position) {
        return super.getItem(position);
    }


}
