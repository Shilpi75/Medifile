package com.example.medifile;

import java.util.ArrayList;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class Share extends ActionBarActivity {
Button b1;
Toolbar toolbar;

ArrayList<NavModel> navList;
Custom_nav adapter;
DrawerLayout drawer;
ActionBarDrawerToggle drawerListener;
ListView lv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_share);
		
		toolbar=(Toolbar)findViewById(R.id.actionbar);
		toolbar.setTitle("Share Medifile");
		setSupportActionBar(toolbar);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_nav);
		
		navList = new ArrayList<NavModel>();
		
		
		b1=(Button)findViewById(R.id.button1);
		lv=(ListView)findViewById(R.id.listView1);
		drawer=(DrawerLayout)findViewById(R.id.drawerLayout);

	
		
		Typeface tf=Typeface.createFromAsset(this.getAssets(), "journal.ttf");
		b1.setTypeface(tf); 
		
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in=new Intent(Intent.ACTION_SEND);
				in.setType("text/plain");
				in.putExtra(Intent.EXTRA_TEXT, "Loved Medifile..Now easily manage all your medical data in a click..Download Medifile now.");
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
					startActivity(new Intent(Share.this,History.class));
					break;
				case 1:
					startActivity(new Intent(Share.this,Allergies.class));
					break;
				case 2:
					startActivity(new Intent(Share.this,Appointments.class));
					break;
				case 3:
					startActivity(new Intent(Share.this,BMI_Calculator.class));
					break;
				case 4:
					startActivity(new Intent(Share.this,Emergency.class));
					break;
				case 5:
					startActivity(new Intent(Share.this,Setting.class));
					break;
				case 6:
					startActivity(new Intent(Share.this,Share.class));
					break;
				case 7:
					startActivity(new Intent(Share.this,Feedback.class));
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
		getMenuInflater().inflate(R.menu.share, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		if (drawerListener.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	
}
