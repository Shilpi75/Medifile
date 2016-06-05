package com.example.medifile;

import java.util.List;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class ViewHistory extends ActionBarActivity {
	String id, message,Contact1,Contact2;
	Toolbar toolbar;
	List<HistoryModel> model;
	Databasehandler handler;
	HistoryModel obj;
	TextView title, doctor, hospital, location, date, prescription,
			specification, surgery, nextapp, others, contact1, contact2, fees;

	AlertDialog.Builder dialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_history);

		toolbar = (Toolbar) findViewById(R.id.actionbar);
		toolbar.setTitle("Your History");
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		dialog=new AlertDialog.Builder(this);
		
		title = (TextView) findViewById(R.id.title);
		doctor = (TextView) findViewById(R.id.doctor);
		hospital = (TextView) findViewById(R.id.hospital);
		location = (TextView) findViewById(R.id.location);
		date = (TextView) findViewById(R.id.date);
		prescription = (TextView) findViewById(R.id.presription);
		specification = (TextView) findViewById(R.id.specification);
		surgery = (TextView) findViewById(R.id.surgery);
		nextapp = (TextView) findViewById(R.id.nextapp);
		others = (TextView) findViewById(R.id.others);
		fees = (TextView) findViewById(R.id.fees);
		contact1 = (TextView) findViewById(R.id.contact1);
		contact2 = (TextView) findViewById(R.id.contact2);

		Bundle extras = getIntent().getExtras();
		handler = new Databasehandler(this);
		if (extras != null)
			id = extras.getString("id");
		HistoryModel obj = handler.getHistory(id);
		// model=handler.getAllHistory();
		String Title = obj.getTitle();
		String Doctor = obj.getDoctorName();
		String Hospital = obj.getHospitalName();
		String Location = obj.getLocation();
		String Date = obj.getDateOfVisit();
		String Prescription = obj.getPrescription();
		String Specification = obj.getSpecification();
		String Surgery = obj.getSurgery();
		int Fees = obj.getFees();
		int nextDate = obj.getNextVisit_day();
		int nextMonth = obj.getNextVisit_month();
		int nextYear = obj.getNextVisit_year();
		String Others = obj.getOtherInfo();
		 Contact1 = obj.getContactNo1();
		Contact2 = obj.getContactNo2();

		message = "Doctor name: " + obj.getDoctorName() + "\nLocation: "
				+ obj.getLocation() + "\n" + "Prescription: "
				+ obj.getPrescription() + "\n Contact no. "
				+ obj.getContactNo1() + " " + obj.getContactNo2();

		title.setText(Title);
		doctor.setText(Doctor);
		hospital.setText(Hospital);
		location.setText(Location);
		date.setText(Date);
		prescription.setText(Prescription);
		specification.setText(Specification);
		surgery.setText(Specification);
		nextapp.setText(nextDate + "/" + nextMonth + "/" + nextYear);
		others.setText(Others);
		contact1.setText(Contact1);
		contact2.setText(Contact2);

	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_history, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
		case R.id.edit:
			Intent in = new Intent(this, EditHistory.class);
			in.putExtra("id", id);
			startActivity(in);
			break;

		case R.id.delete:
			handler.deleteHistory(id);
			dialog.setTitle("Delete History")
			.setMessage("Are you sure to delete selected items?")
			.setCancelable(true)
			.setNegativeButton("Cancel", null)
			.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					CustomToast.printToast(ViewHistory.this, "successfully deleted", 0,
							R.drawable.anydo);
					startActivity(new Intent(ViewHistory.this, History.class));
				}
			})
			.show();
			
			break;

		case R.id.share:
			Intent in2 = new Intent(Intent.ACTION_SEND);
			in2.setType("text/plain");

			in2.putExtra(Intent.EXTRA_TEXT, message);
			startActivity(in2);

			break;
		case android.R.id.home:
			onBackPressed();
			break;
		case R.id.call:
			
			String contact = Contact1;

			if (contact.isEmpty())
				contact = Contact2;
			if(contact.isEmpty())
				Toast.makeText(this, "No number saved..",

						Toast.LENGTH_LONG).show();

			try {
				// set the data

				String uri = "tel:" + contact;

				Intent callIntent = new Intent(Intent.ACTION_CALL, Uri
						.parse(uri));

				startActivity(callIntent);

			} catch (Exception e) {

				Toast.makeText(this, "Your call has failed...",

				Toast.LENGTH_LONG).show();

				e.printStackTrace();

			}
			break;
		
		}

		return super.onOptionsItemSelected(item);

	}

}