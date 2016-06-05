package com.example.medifile;

import java.util.ArrayList;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class Setting extends ActionBarActivity {
Toolbar toolbar;
ListView lv,list;
AlertDialog.Builder dialog;
ArrayList<NavModel> navList,setList;
Custom_nav adapter,setAdapter;
DrawerLayout drawer;
ActionBarDrawerToggle drawerListener;
SharedPreferences pref;
SharedPreferences.Editor editor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		
		toolbar=(Toolbar)findViewById(R.id.actionbar);
		toolbar.setTitle("Settings");
		setSupportActionBar(toolbar);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_nav);
		
		
		dialog =new AlertDialog.Builder(this);
		
		//navigation drawer
		navList = new ArrayList<NavModel>();
		lv=(ListView)findViewById(R.id.listView1);
		drawer=(DrawerLayout)findViewById(R.id.drawerLayout);
		
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
					startActivity(new Intent(Setting.this,History.class));
					break;
				case 1:
					startActivity(new Intent(Setting.this,Allergies.class));
					break;
				case 2:
					startActivity(new Intent(Setting.this,Appointments.class));
					break;
				case 3:
					startActivity(new Intent(Setting.this,BMI_Calculator.class));
					break;
				case 4:
					startActivity(new Intent(Setting.this,Emergency.class));
					break;
				case 5:
					startActivity(new Intent(Setting.this,Setting.class));
					break;
				case 6:
					startActivity(new Intent(Setting.this,Share.class));
					break;
				case 7:
					startActivity(new Intent(Setting.this,Feedback.class));
					break;
					
					
				}
				drawer.closeDrawer(lv);
			}
		});
		
		
		
		//setting list
		list=(ListView)findViewById(R.id.listView2);
		
		setList=new ArrayList<NavModel>();
		setList.add(new NavModel("Password Protection",R.drawable.set1));
		setList.add(new NavModel("Change Password", R.drawable.ic_launcher));
		setList.add(new NavModel("Logout", R.drawable.set2));
		
		setAdapter=new Custom_nav(this,setList);
		list.setAdapter(setAdapter);
		
		pref=getSharedPreferences("settings", Context.MODE_PRIVATE);
		editor=pref.edit();
		
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				switch(position){
				case 0:
					dialog.setTitle("Password Protection")
					.setMessage("By turning it on, you will have to enter password every time you access medifile.")
					.setNegativeButton("Turn-off",new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							editor.putBoolean("passProtection", false);
							editor.commit();
						}
					})
					.setPositiveButton("Turn-On", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							editor.putBoolean("passProtection", true);
							editor.commit();
						}
					})
					.setCancelable(true)
					.show();
					break;
				case 2:
					dialog.setTitle("Logout")
					.setMessage("Are you sure to logout?")
					.setCancelable(true)
					.setNegativeButton("Cancel", null)
					.setPositiveButton("Logout", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							Intent intent = new Intent(getApplicationContext(), Password.class);
							intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
							startActivity(intent);
						}
					})
					.show();
					break;
				}
				
			}
		});
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.setting, menu);
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
