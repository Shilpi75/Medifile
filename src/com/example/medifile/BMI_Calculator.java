package com.example.medifile;


import java.util.ArrayList;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.iangclifton.android.floatlabel.FloatLabel;

public class BMI_Calculator extends AppCompatActivity implements
		OnClickListener {

	Button check;;
	FloatLabel height, weight;
	Toolbar toolbar;
	AlertDialog.Builder dialog;
	ArrayList<NavModel> navList;
	Custom_nav adapter;
	DrawerLayout drawer;
	ActionBarDrawerToggle drawerListener;
ListView lv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bmi__calulator);

		toolbar = (Toolbar) findViewById(R.id.actionbar);
		toolbar.setTitle("BMI Calculator");
		setSupportActionBar(toolbar);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_nav);

		
		navList = new ArrayList<NavModel>();
		
		dialog=new AlertDialog.Builder(this);


		
		check = (Button) findViewById(R.id.button1);
		height = (FloatLabel) findViewById(R.id.editText1);
		weight = (FloatLabel) findViewById(R.id.editText2);
		lv=(ListView)findViewById(R.id.listView1);
		drawer=(DrawerLayout)findViewById(R.id.drawerLayout);

	
		check.setOnClickListener(this);
		
		navList.add(new NavModel("My History",R.drawable.historylist));
		navList.add(new NavModel("My Allergies",R.drawable.allergies_food));
		navList.add(new NavModel("My Appointments",R.drawable.app));
		navList.add(new NavModel("BMI",R.drawable.bmiicon));
		navList.add(new NavModel("Emergency",R.drawable.navemergency));
		navList.add(new NavModel("Settings",R.drawable.navsettings));
		navList.add(new NavModel("Share",R.drawable.navshare));
		navList.add(new NavModel("Feedback",R.drawable.navfeedback));
		
		adapter = new Custom_nav(this, navList);
		lv.setAdapter(adapter);

		drawerListener = new ActionBarDrawerToggle(this, drawer,
				R.drawable.ic_nav, R.string.opennav, R.string.closenav);
		drawer.setDrawerListener(drawerListener);
		
		lv.setOnItemClickListener(new  OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				switch(position)
				{
				case 0:
					startActivity(new Intent(BMI_Calculator.this,History.class));
					break;
				case 1:
					startActivity(new Intent(BMI_Calculator.this,Allergies.class));
					break;
				case 2:
					startActivity(new Intent(BMI_Calculator.this,Appointments.class));
					break;
				case 3:
					startActivity(new Intent(BMI_Calculator.this,BMI_Calculator.class));
					break;
				case 4:
					startActivity(new Intent(BMI_Calculator.this,Emergency.class));
					break;
				case 5:
					startActivity(new Intent(BMI_Calculator.this,Setting.class));
					break;
				case 6:
					startActivity(new Intent(BMI_Calculator.this,Share.class));
					break;
				case 7:
					startActivity(new Intent(BMI_Calculator.this,Feedback.class));
					break;
					
					
				}
				drawer.closeDrawer(lv);
			}
		});


	}

	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		drawerListener.onConfigurationChanged(newConfig);
	}

	
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		drawerListener.syncState();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bmi__calulator, menu);
		return true;
	}
	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		if (drawerListener.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Double Height, Weight, Result;
		try{
		Height = Double.parseDouble(height.getEditText().getText().toString());
		Weight = Double.parseDouble(weight.getEditText().getText().toString());
				Result = (Weight * 100 * 100) / (Height * Height);
		if (Result <= 18.5) {
		
			dialog.setTitle("BMI Result")
			.setCancelable(true)
			.setMessage("You are underweight!!!...Be healthy")
			.setPositiveButton("OK", null)
			.setIcon(R.drawable.bmi)
			.show();
		} else if (Result > 18.5 && Result <= 25) {
			
			dialog.setTitle("BMI Result")
			.setCancelable(true)
			.setMessage("Nice!!..You are fit and healthy.")
			.setPositiveButton("OK", null)
			.setIcon(R.drawable.bmi)
			.show();

		} else {

		
			dialog.setTitle("BMI Result")
			.setCancelable(true)
			.setMessage("You are overweight!!...Lose some weight.")
			.setPositiveButton("OK", null)
			.setIcon(R.drawable.bmi)
			.show();

		}

	}catch(Exception e)
	{
		CustomToast.printToast(this,"Enter height and weight", 0, R.drawable.error);
	}
	}
	
}
