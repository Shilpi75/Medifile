package com.example.medifile;

import java.util.Calendar;
import java.util.UUID;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.iangclifton.android.floatlabel.FloatLabel;


public class HistoryAdd extends AppCompatActivity implements OnClickListener {
	Toolbar toolbar;
	
	ImageButton date1,date2;
	TextView date, appointment;
	FloatLabel  prescription,
	specification, surgery, fees, other,location,hospitalname,doctorname,title,contact1, contact2;
	
	Calendar current;
	DatePickerDialog datepicker;
	int currentDate, currentMonth, currentYear, nextDate, nextMonth, nextYear;
	Databasehandler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_history_add);

		
		toolbar = (Toolbar) findViewById(R.id.actionbar);
		toolbar.setTitle("Add History");
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

	
		title = (FloatLabel) findViewById(R.id.title);
		doctorname = (FloatLabel) findViewById(R.id.docname);
		hospitalname = (FloatLabel) findViewById(R.id.hospitalname);
		location = (FloatLabel) findViewById(R.id.location);
		date = (TextView) findViewById(R.id.dateofvisit);
		date1 = (ImageButton) findViewById(R.id.date1);
		prescription = (FloatLabel) findViewById(R.id.priscription);
		specification = (FloatLabel) findViewById(R.id.specification);
		surgery = (FloatLabel) findViewById(R.id.surgery);
		fees = (FloatLabel) findViewById(R.id.fees);
		appointment = (TextView) findViewById(R.id.appointment);
		date2 = (ImageButton) findViewById(R.id.date2);
		other = (FloatLabel) findViewById(R.id.other);
		contact1 = (FloatLabel) findViewById(R.id.contact1);
		contact2 = (FloatLabel) findViewById(R.id.contact2);

		handler = new Databasehandler(this);

		date1.setOnClickListener(this);
		date2.setOnClickListener(this);
		
		current = Calendar.getInstance();
		currentDate = current.get(Calendar.DATE);
		currentMonth = current.get(Calendar.MONTH);
		currentYear = current.get(Calendar.YEAR);

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
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.date1:
			datepicker = new DatePickerDialog(this, new OnDateSetListener() {

				@Override
				public void onDateSet(DatePicker view, int year,
						int monthOfYear, int dayOfMonth) {
					// TODO Auto-generated method stub
					String selected = dayOfMonth + "/" + (monthOfYear + 1)
							+ "/" + year;
					date.setError(null);
					date.setText(selected);

				}
			}, currentYear, currentMonth, currentDate);
			datepicker.setTitle("Choose Date Of Visit");
			datepicker.setCancelable(false);
			datepicker.show();
			break;

		case R.id.date2:

			datepicker = new DatePickerDialog(this, new OnDateSetListener() {

				@Override
				public void onDateSet(DatePicker view, int year,
						int monthOfYear, int dayOfMonth) {
					// TODO Auto-generated method stub
					String selected = dayOfMonth + "/" + (monthOfYear + 1)
							+ "/" + year;
					appointment.setText(selected);
					nextDate = dayOfMonth;
					nextMonth = monthOfYear + 1;
					nextYear = year;

				}
			}, currentYear, currentDate, currentMonth);
			datepicker.setTitle("select date");
			datepicker.show();
			break;

		
		}
	}
	

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.history_add, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch(item.getItemId()){
		case R.id.save:
String Title = title.getEditText().getText().toString();
			
			String Doctor = doctorname.getEditText().getText().toString();
			Log.e("sh",Doctor);
			String Hospital = hospitalname.getEditText().getText().toString();
			String Location = location.getEditText().getText().toString();
			String Date = date.getText().toString();
			Log.e("shilpi", Date);
			String Prescription = prescription.getEditText().getText().toString();
			String Specification = specification.getEditText().getText().toString();
			String Surgery = surgery.getEditText().getText().toString();
			String Fees = (fees.getEditText().getText().toString());
			Log.e("shilpi", nextDate + " " + nextMonth + " " + nextYear);
			int NextDate = nextDate;
			int NextMonth = nextMonth;
			int NextYear = nextYear;
			String Other = other.getEditText().getText().toString();
			String Contact1 = contact1.getEditText().getText().toString();
			String Contact2 = contact2.getEditText().getText().toString();
			String Id = UUID.randomUUID().toString();
			if (Fees.equals("")) {
				Fees = "0";

			}

			if (Title.equals("") | Title.equals(" ")) {
				title.getEditText().setError("enter title");
//				Toast.makeText(this, "enter title", 0).show();
				CustomToast.printToast(this, "Enter title", 0, R.drawable.error);
			} else if (Date.equals("")) {
				date.setError("choose date of visit");
				CustomToast.printToast(this, "Choose date of visit", 0, R.drawable.error);
				//Toast.makeText(this, "choose date of visit", 0).show();
			}

			else {
				Log.e("shilpi", " " + Title + " " + Doctor + " " + Hospital
						+ " " + Location + " " + Date + " " + Prescription
						+ " " + Specification + " " + Surgery + " " + Fees
						+ " " + NextDate + " " + NextMonth + " " + NextYear
						+ " " + Other + " " + Contact1 + " " + Contact2);
				HistoryModel obj = new HistoryModel(Id, Title, Doctor,
						Hospital, Location, Date, Prescription, Specification,
						Surgery, Integer.parseInt(Fees), NextDate, NextMonth,
						NextYear, Other, Contact1, Contact2);

				handler.insertHistory(obj);
				handler.close();
				CustomToast.printToast(this," Added " ,0,R.drawable.anydo);
				startActivity(new Intent(this,History.class));
				finish();

			}
			break;
		case android.R.id.home:
			onBackPressed();
			break;
		
		}
		
		return super.onOptionsItemSelected(item);

	}
	
}
