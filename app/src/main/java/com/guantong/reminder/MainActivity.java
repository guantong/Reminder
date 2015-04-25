package com.guantong.reminder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private ListView reminderListView;
    private ArrayList<Reminder> reminders;
    private ReminderAdapter adapter;
    public static final int ADD_MONSTER_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the ListView associated with layout
        reminderListView = (ListView) findViewById(R.id.reminderListView);

        // Create our adapter and associate ArrayList
        reminders = new ArrayList<Reminder>();
        adapter = new ReminderAdapter(this, reminders);

        // Associate adapter with ListView
        reminderListView.setAdapter(adapter);

        //reminders.add(new Reminder("Sample", "Sample", null, false));
        reminderListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view,
                                    int position, long id) {

                Reminder r = reminders.get(position);
                Intent intent = new Intent(MainActivity.this, DisplayReminderDetailsActivity.class);
                intent.putExtra("Reminder", r);
                startActivity(intent);
            }
        });

//        Intent intent = getIntent();
//        String title = intent.getStringExtra("ChangeBoolean");
//
//        for (int i = 0; i < reminders.size(); i++) {
//            if (reminders.get(i).getTitle() == title) {
//                reminders.get(i).setCompleted(true);
//            }
//        }
    }

    // Creates menu items for ActionBar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // ActionBar handler for selected items
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                // Move to AddMonsterActivity and await result
                Intent i = new Intent(this, AddReminderActivity.class);
                startActivityForResult(i, ADD_MONSTER_REQUEST);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    // We override this method when we are expecting a result from an
    // activity we have called.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_MONSTER_REQUEST) {
            if (resultCode == RESULT_OK) {
                // Grab the Monster object out of the intent
                Reminder m = (Reminder) data.getSerializableExtra("Reminder");
                int size = reminders.size();

                if (size < 1) {
                    reminders.add(m);
                    reminderListView.setAdapter(new ReminderAdapter(this, reminders));
                }
                else if (size >= 1) {
                    for (int i = 0; i < reminders.size(); i++) {
                        if (m.getDueDate().after(reminders.get(i).getDueDate())) {
                            reminders.add(i + 1, m);
                            reminderListView.setAdapter(new ReminderAdapter(this, reminders));
                            break;
                        }
                        else if (m.getDueDate().before(reminders.get(i).getDueDate())) {
                            reminders.add(i, m);
                            reminderListView.setAdapter(new ReminderAdapter(this, reminders));
                            break;
                        }
                        else if (m.getDueDate().equals(reminders.get(i).getDueDate()))
                        {
                            reminders.add(i + 1, m);
                            reminderListView.setAdapter(new ReminderAdapter(this, reminders));
                            break;
                        }
                    }
                }
                // Apply new adapter and update count
                // reminderListView.setAdapter(new ReminderAdapter(this, reminders));
            }
        }
    }
}
