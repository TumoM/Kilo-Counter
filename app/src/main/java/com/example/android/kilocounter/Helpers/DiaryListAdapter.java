package com.example.android.kilocounter.Helpers;

import android.content.Context;
import android.icu.util.Calendar;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.kilocounter.Models.DiaryEntryModel;
import com.example.android.kilocounter.R;

import java.text.DateFormat;
import java.util.ArrayList;

public class DiaryListAdapter extends ArrayAdapter<DiaryBundle> {

    private Context mContext;
    private int mResource;

    /**
     * Constructor
     *  @param context  The current context.
     * @param resource The resource ID for a layout file containing a TextView to use when
     *                 instantiating views.
     * @param objects  The objects to represent in the ListView.
     */
    public DiaryListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<DiaryBundle> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        int netCal = getItem(position).getNKI();
        String date = getItem(position).getDate();

        if (date.split("/").length > 1) {
            String dateTime[] = date.split("/");
            //Log.d("onDateSetReturned", String.valueOf(year + ' ' + month + ' ' + dayOfMonth));
            Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR, Integer.parseInt(dateTime[0]));
            c.set(Calendar.MONTH, Integer.parseInt(dateTime[1]));
            c.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateTime[2]));
            date = DateFormat.getDateInstance(DateFormat.LONG).format(c.getTime());
        }


        // Todo make this thread safe?
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
        }

        TextView kiloCal = (TextView) convertView.findViewById(R.id.KilojoulCountTextView);
        TextView dateText = (TextView) convertView.findViewById(R.id.DiaryDateTextView);

        kiloCal.setText((Integer.toString(netCal)+ " "+ mContext.getString(R.string.measure_unit)));
        dateText.setText(date);

        return convertView;
    }

    @Nullable
    @Override
    public DiaryBundle getItem(int position) {
        return super.getItem(position);
    }


}
