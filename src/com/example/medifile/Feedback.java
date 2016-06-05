package com.example.medifile;

import java.util.ArrayList;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class Feedback extends ActionBarActivity implements TextWatcher {
EditText e1,e2;
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
		setContentView(R.layout.activity_feedback);
		
		toolbar=(Toolbar)findViewById(R.id.actionbar);
		toolbar.setTitle("Feedback");
		setSupportActionBar(toolbar);
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_nav);
		
		navList = new ArrayList<NavModel>();
		getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
		
		e1=(EditText)findViewById(R.id.editText1);
		e2=(EditText)findViewById(R.id.editText2);
		lv=(ListView)findViewById(R.id.listView1);
		drawer=(DrawerLayout)findViewById(R.id.drawerLayout);
		b1=(Button)findViewById(R.id.button1);
		e1.addTextChangedListener(this);
		e2.addTextChangedListener(this);
		b1.setEnabled(false);
		b1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String subject=e1.getText().toString();
				String message=e2.getText().toString();
				
				Intent in=new Intent(Intent.ACTION_SEND);
				in.putExtra(Intent.EXTRA_SUBJECT, subject);
				in.putExtra(Intent.EXTRA_TEXT, message);
				in.putExtra(Intent.EXTRA_EMAIL,new String[]{"shilpigupta_96@yahoo.com"});
				in.setType("message/rfc822");
				startActivity(in);
				CustomToast.printToast(Feedback.this,"Thank You", 0, R.drawable.anydo);
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
					startActivity(new Intent(Feedback.this,History.class));
					break;
				case 1:
					startActivity(new Intent(Feedback.this,Allergies.class));
					break;
				case 2:
					startActivity(new Intent(Feedback.this,Appointments.class));
					break;
				case 3:
					startActivity(new Intent(Feedback.this,BMI_Calculator.class));
					break;
				case 4:
					startActivity(new Intent(Feedback.this,Emergency.class));
					break;
				case 5:
					startActivity(new Intent(Feedback.this,Setting.class));
					break;
				case 6:
					startActivity(new Intent(Feedback.this,Share.class));
					break;
				case 7:
					startActivity(new Intent(Feedback.this,Feedback.class));
					break;
					
					
				}
				drawer.closeDrawer(lv);
			}
		});
	}

	
	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub
		
		b1.setEnabled(false);
	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub
		b1.setEnabled(false);
	}

	@Override
	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub
		if((e1.getText().length()>0)&&(e2.getText().length()>0))
			b1.setEnabled(true);
		else
			b1.setEnabled(false);
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
		getMenuInflater().inflate(R.menu.feedback, menu);
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
