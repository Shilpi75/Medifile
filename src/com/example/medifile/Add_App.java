package com.example.medifile;

import java.util.Calendar;
import java.util.UUID;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.iangclifton.android.floatlabel.FloatLabel;

public class Add_App extends ActionBarActivity {
FloatLabel title;
TextView date,time;
ImageButton timepick,datepick;
Calendar current;
int curdate,curmonth,curyear,curhour,curmin,seldate,selmonth,selyear,selhour,selminute;
TimePickerDialog timedialog;
DatePickerDialog datedialog;
Databasehandler handler;
Toolbar toolbar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_appapp);
		title=(FloatLabel)findViewById(R.id.title);
		date=(TextView)findViewById(R.id.date);
		time=(TextView)findViewById(R.id.time);
		datepick=(ImageButton)findViewById(R.id.datepick);
		timepick=(ImageButton)findViewById(R.id.timepick);
		toolbar=(Toolbar)findViewById(R.id.actionbar);
		toolbar.setTitle("Add Appointment");
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		current=Calendar.getInstance();
		curdate=current.get(Calendar.DATE);
		curmonth=current.get(Calendar.MONTH);
		curyear=current.get(Calendar.YEAR);
		curhour=current.get(Calendar.HOUR_OF_DAY);
		curmin=current.get(Calendar.MINUTE);
		
		
		handler=new  Databasehandler(this);
		
		
		timepick.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				timedialog=new TimePickerDialog(Add_App.this, new OnTimeSetListener() {
					
					@Override
					public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
						// TODO Auto-generated method stub
						selhour=hourOfDay;
						selminute=minute;
						time.setText(hourOfDay+":"+minute);
					}
				}, curhour,curmin, true);
				timedialog.setTitle("select time");
				timedialog.show();
			}
		});
		
		datepick.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				datedialog=new DatePickerDialog(Add_App.this, new OnDateSetListener() {
					
					@Override
					public void onDateSet(DatePicker view, int year, int monthOfYear,
							int dayOfMonth) {
						// TODO Auto-generated method stub
						seldate=dayOfMonth;
						selmonth=monthOfYear+1;
						selyear=year;
						date.setText(dayOfMonth+"/"+monthOfYear+"/"+year);
						date.setError(null);
						
					}
				}, curyear, curmonth, curdate);
				datedialog.show();
			}
		});
		
		title.getEditText().addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if (s.length() != 0)
					title.getEditText().setError(null);

			}
		});
		
		
		
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add__app, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch(item.getItemId()){
		case android.R.id.home:
			onBackPressed();
			break;
		case R.id.done:
			String Title=title.getEditText().getText().toString();
			int Date=seldate;
			int Month=selmonth;
			int Year=selyear;
			String Time=time.getText().toString();
			String id= UUID.randomUUID().toString();

			if (Title.equals("") | Title.equals(" ")) {
				title.getEditText().setError("enter title");
//				Toast.makeText(this, "enter title", 0).show();
				CustomToast.printToast(this, "Enter title", 0, R.drawable.error);
			} else if (date.getText().toString().equals("")) {
				date.setError("choose date of visit");
				CustomToast.printToast(this, "Choose date of appointment", 0, R.drawable.error);
				//Toast.makeText(this, "choose date of visit", 0).show();
			}

			else {
				ModelApp obj=new ModelApp(id,Title, Date, Month, Year, Time);
				handler.appAdd(obj);
				handler.close();
				CustomToast.printToast(this," Added " ,0,R.drawable.anydo);
				startActivity(new Intent(this,Appointments.class));
				finish();

			}
			break;
			
			
			
		}
		
	return super.onOptionsItemSelected(item);
	}

}