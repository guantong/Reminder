package com.guantong.reminder;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Date;


public class DisplayReminderDetailsActivity extends Activity {
    private Reminder currentReminder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_reminder_details);

        Intent intent = getIntent();
        currentReminder = (Reminder) intent.getSerializableExtra("Reminder");

        TextView title = (TextView) findViewById(R.id.titleDetailView);
        TextView desc = (TextView) findViewById(R.id.descriptionDetailView);
        TextView dueDate = (TextView) findViewById(R.id.dueDateDetailView);
        CheckBox complete = (CheckBox) findViewById(R.id.completeDetailCheckBox);

        title.setText(currentReminder.getTitle());
        desc.setText(currentReminder.getDescription());
        dueDate.setText(currentReminder.getDueDate().toString());
        complete.setChecked(currentReminder.isCompleted());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_display_reminder_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    public void changeComplete(View view){
//        TextView title = (TextView)findViewById(R.id.titleDetailView);
//        String titleS = title.getText().toString();
//
//        Intent intent = new Intent(this, MainActivity.class);
//        intent.putExtra("ChangeBoolean", titleS);
//        startActivity(intent);
//    }
}
