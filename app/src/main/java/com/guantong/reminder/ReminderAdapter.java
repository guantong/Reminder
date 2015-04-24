package com.guantong.reminder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Alien on 4/18/2015.
 */
public class ReminderAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Reminder> reminders;

    public ReminderAdapter(Context context, ArrayList<Reminder> reminders) {
        this.context = context;
        this.reminders = reminders;
    }

    @Override
    public int getCount() {
        return reminders.size();
    }

    @Override
    public Object getItem(int i) {
        return reminders.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // Check if the view has been created for the row. If not, lets inflate it
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_reminder_item, null); // List layout here
        }

        // Grab the TextViews in our custom layout
        TextView titleText = (TextView) view.findViewById(R.id.titleTextView);
        TextView dueDateText = (TextView) view.findViewById(R.id.dueDateTextView);

        // Assign values to the TextViews using the Monster object
        titleText.setText(reminders.get(i).getTitle());
//        String a = reminders.get(i).getDueDate().toString();
//        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
//        try {
//            String date = sdf.format(sdf.parse(a));
//            dueDateText.setText(date);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        dueDateText.setText(reminders.get(i).getDueDate().toString());

        // Return the completed View of the row being processed
        return view;
    }
}