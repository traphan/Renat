package com.example.ren95.curswork3;


import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.app.Activity;



public class MainActivity extends AppCompatActivity {

    AlertDialog.Builder dial1;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CalendarView cv = (CalendarView) findViewById(R.id.calendarView);
        dial1=new AlertDialog.Builder(this);

        final String nameDialogBar []={"Просмотерть список дел","Добавить новое дело"};
        dial1.setItems(nameDialogBar, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(which==0) {
                   finish();
                   // Intent i=new Intent(MainActivity.this,.class);
                //    startActivity(i);
                }

            }
        });
        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                int mYear = year;
                int mMonth = month;
                int mDay = dayOfMonth;
                String selectedDate = new StringBuilder().append(mMonth + 1).append("-").append(mDay).append("-").append(mYear).append(" ").toString();
dial1.setTitle(selectedDate);
                dial1.show();
            }
        });
    }


    public void bb(View view) {
       // Intent in=new Intent(this,Show.class);
       // startActivity(in);
    }
}
