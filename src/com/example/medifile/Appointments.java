package com.example.medifile;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class Appointments extends ActionBarActivity  {
Databasehandler handler;
ListView lv,lv2;
List<ModelApp> list;
CustomApp adapter;
Toolbar toolbar;


ArrayList<NavModel> navList;
Custom_nav navAdapter;
DrawerLayout drawer;
ActionBarDrawerToggle drawerListener;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_appointments);
		
		toolbar=(Toolbar)findViewById(R.id.actionbar);
		toolbar.setTitle("My Appointments");
		setSupportActionBar(toolbar);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_nav);
		
		
		
		
		
		handler=new Databasehandler(this);
		
		
		lv=(ListView)findViewById(R.id.listView1);
		list=handler.getAllApp();
		adapter=new CustomApp(this, list);
		lv.setAdapter(adapter);
		navList = new ArrayList<NavModel>();
		drawer = (DrawerLayout) findViewById(R.id.drawerLayout);

		
		lv2=(ListView)findViewById(R.id.listView);
		
		
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Intent in=new Intent(Appointments.this,AppView.class);
				in.putExtra("id", list.get(position).getId());
				startActivity(in);
			}
		});
		
		navList.add(new NavModel("My History",R.drawable.historylist));
		navList.add(new NavModel("My Allergies",R.drawable.allergies_food));
		navList.add(new NavModel("My Appointments",R.drawable.app));
		navList.add(new NavModel("BMI",R.drawable.bmiicon));
		navList.add(new NavModel("Emergency",R.drawable.navemergency));
		navList.add(new NavModel("Settings",R.drawable.navsettings));
		navList.add(new NavModel("Share",R.drawable.navshare));
		navList.add(new NavModel("Feedback",R.drawable.navfeedback));
		navAdapter = new Custom_nav(this, navList);
		lv2.setAdapter(navAdapter);

		drawerListener = new ActionBarDrawerToggle(this, drawer,
				R.drawable.ic_nav, R.string.opennav, R.string.closenav);
		drawer.setDrawerListener(drawerListener);
		
		lv2.setOnItemClickListener(new  OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				switch(position)
				{
				case 0:
					
					startActivity(new Intent(Appointments.this,History.class));
					break;
				case 1:
					
					startActivity(new Intent(Appointments.this,Allergies.class));
					break;
				case 2:
					startActivity(new Intent(Appointments.this,Appointments.class));
					break;
				case 3:
					startActivity(new Intent(Appointments.this,BMI_Calculator.class));
					break;
				case 4:
					startActivity(new Intent(Appointments.this,Emergency.class));
					break;
				case 5:
					startActivity(new Intent(Appointments.this,Setting.class));
					break;
				case 6:
					startActivity(new Intent(Appointments.this,Share.class));
					break;
				case 7:
					startActivity(new Intent(Appointments.this,Feedback.class));
					break;
					
					
				}
				drawer.closeDrawer(lv2);
			}
		});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.appointments, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (drawerListener.onOptionsItemSelected(item)) {
			return true;
		}
		
		if (id == R.id.addNew) {
			startActivity(new Intent(this,Add_App.class));
			
		}
		return super.onOptionsItemSelected(item);
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

	
	
	
}
