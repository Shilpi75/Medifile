package com.example.medifile;

import java.util.Calendar;

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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.iangclifton.android.floatlabel.FloatLabel;

public class EditApp extends ActionBarActivity implements OnClickListener {
String Id;
FloatLabel title;
TextView date,time;
ImageButton datepick,timepick;
Calendar calendar;
int curdate,curmonth,curyear,curhour,curmin,seldate,selmonth,selyear;
Databasehandler handler;
ModelApp obj;
DatePickerDialog datepicker;
TimePickerDialog timepicker;
Toolbar toolbar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_editapp);
		
		
		Bundle extras=getIntent().getExtras();
		Id=extras.getString("id");
		
		toolbar=(Toolbar)findViewById(R.id.actionbar);
		toolbar.setTitle("Appointment");
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		title=(FloatLabel)findViewById(R.id.title);
		date=(TextView)findViewById(R.id.date);
		time=(TextView)findViewById(R.id.time);
		datepick=(ImageButton)findViewById(R.id.datepick);
		timepick=(ImageButton)findViewById(R.id.timepick);
		
		calendar=Calendar.getInstance();
		curdate=calendar.get(Calendar.DATE);
		curmonth=calendar.get(Calendar.MONTH);
		curyear=calendar.get(Calendar.YEAR);
		curhour=calendar.get(Calendar.HOUR_OF_DAY);
		curmin=calendar.get(Calendar.MINUTE);
		
		handler=new Databasehandler(this);
		
		obj=handler.ViewApp(Id);
		
		title.setText(obj.getTitle());
		date.setText(obj.getDate()+"/"+obj.getMonth()+"/"+obj.getYear());
		time.setText(obj.getTime());
		
		
		datepick.setOnClickListener(this);
		timepick.setOnClickListener(this);
		

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

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_app, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.done) {
			
			String Title=title.getEditText().getText().toString();
			String Time=time.getText().toString();
			if (Title.equals("") | Title.equals(" ")) {
				title.getEditText().setError("enter title");
				CustomToast.printToast(this, "Enter title", 0, R.drawable.error);
			} else if (date.getText().toString().equals("")) {
				date.setError("choose date of visit");
				CustomToast.printToast(this, "Choose date of appointment", 0, R.drawable.error);
			}

			else {
				handler.AppUpdate(Id, Title, seldate, selmonth, selyear, Time);
				
				handler.close();
				CustomToast.printToast(this," Edited " ,0,R.drawable.anydo);
				startActivity(new Intent(this,Appointments.class));

			}
			
			
			
			
		}
		
		if(id==android.R.id.home)
			onBackPressed();
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		case R.id.datepick:
			datepicker = new DatePickerDialog(this, new OnDateSetListener() {

				@Override
				public void onDateSet(DatePicker view, int year,
						int monthOfYear, int dayOfMonth) {
					// TODO Auto-generated method stub
					String selected = dayOfMonth + "/" + (monthOfYear + 1)
							+ "/" + year;
					date.setError(null);
					date.setText(selected);
					selyear=year;
					selmonth=monthOfYear;
					seldate=dayOfMonth;

				}
			}, curyear, curmonth, curdate);
			datepicker.setTitle("Choose Date Of Visit");
			datepicker.setCancelable(false);
			datepicker.show();
			
			break;
		case R.id.timepick:
			
			timepicker=new TimePickerDialog(this, new OnTimeSetListener() {
				
				@Override
				public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
					// TODO Auto-generated method stub
					time.setText(hourOfDay+":"+minute);
				}
			},curhour, curmin, true);
			timepicker.setCancelable(true);
			timepicker.setTitle("Select Time");
			timepicker.show();
			break;
		}
		
	}
}
