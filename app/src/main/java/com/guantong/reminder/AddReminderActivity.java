package com.guantong.reminder;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class AddReminderActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_reminder, menu);
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

    public void addNewReminder(View view) throws Exception {
        try {
            EditText titleInput = (EditText) findViewById(R.id.titleInputText);
            EditText descInput = (EditText) findViewById(R.id.descInputText);
            EditText dueDateInput = (EditText) findViewById(R.id.dueDateInputText);
            CheckBox completeInput = (CheckBox) findViewById(R.id.completeCheckBox);

            String title = titleInput.getText().toString();
            String desc = descInput.getText().toString();
            String dueDate = dueDateInput.getText().toString();
            DateFormat format = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            Date date = format.parse(dueDate);
            Boolean complete = completeInput.isChecked();

            Date currentDate = new Date();

            if (date.before(currentDate) == false) {
                Reminder r = new Reminder(title, desc, date, complete);
                Intent i = new Intent(this, MainActivity.class);
                i.putExtra("Reminder", r);
                setResult(RESULT_OK, i);
                finish();
            }
            if (date.before(currentDate) == true) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

                // set title
                alertDialogBuilder.setTitle("Input Warning");

                // set dialog message
                alertDialogBuilder
                        .setMessage("Please set your date in future date, thanks")
                        .setCancelable(false)
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // if this button is clicked, just close
                                // the dialog box and do nothing
                                dialog.cancel();
                            }
                        });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();

                recreate();
            }

        } catch (Exception e) {
            throw new IOException(e.toString());
        }
    }
}
