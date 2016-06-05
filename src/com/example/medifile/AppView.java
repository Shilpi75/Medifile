package com.example.medifile;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class AppView extends ActionBarActivity {
Toolbar toolbar;
TextView date,time,title;
Databasehandler handler;
ModelApp obj;
String id;
AlertDialog.Builder dialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_app_view);
		
		toolbar=(Toolbar)findViewById(R.id.actionbar);
		toolbar.setTitle("My Appointments");
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		title=(TextView)findViewById(R.id.title);
		date=(TextView)findViewById(R.id.date);
		time=(TextView)findViewById(R.id.time);
		
		handler=new Databasehandler(this);
		
		dialog=new AlertDialog.Builder(this);
		
		Bundle extras=getIntent().getExtras();
		if(extras!=null)
		{
			 id=extras.getString("id");
		}
		
		obj=handler.ViewApp(id);
		title.setText(obj.getTitle());
		date.setText(obj.getDate()+"/"+obj.getMonth()+"/"+obj.getYear());
		time.setText(obj.getTime());
		
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.app_view, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == android.R.id.home) {
			onBackPressed();
		}
		if(id==R.id.edit)
		{
			Intent in=new Intent(AppView.this,EditApp.class);
			in.putExtra("id", obj.getId());
			startActivity(in);
			finish();
			
			
		}
		if(id==R.id.delete)
		{
			dialog.setTitle("Delete Appointment")
			.setMessage("Are you sure to delete Appointment?")
			.setCancelable(true)
			.setPositiveButton("Delete",new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					handler.deleteApp(obj.getId());
					startActivity(new Intent(AppView.this,Appointments.class));
					finish();
				}
			}) 
			.setNegativeButton("Cancel", null)
			.show();
		}
		
		return super.onOptionsItemSelected(item);
	}
}
