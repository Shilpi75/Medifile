package com.example.medifile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main1 extends ActionBarActivity {
	Toolbar toolbar;
	Button b1;
	SharedPreferences pref;
	SharedPreferences.Editor editor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main1);
		toolbar = (Toolbar) findViewById(R.id.actionbar);
		Button b1 = (Button) findViewById(R.id.button1);
		toolbar.setTitle("Medifile");
		setSupportActionBar(toolbar);
		getSupportActionBar().setIcon(R.drawable.ic_launcher);
		Typeface tf = Typeface.createFromAsset(this.getAssets(), "journal.ttf");
		b1.setTypeface(tf);
		pref = getSharedPreferences("main", Context.MODE_PRIVATE);
		editor = pref.edit();

		if (pref.getBoolean("getStarted", true) == false) {
			startActivity(new Intent(Main1.this, Login.class));
			finish();
		}
	
		// startActivity(new Intent(Main1.this,Screensplash.class));
		b1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				editor.putBoolean("getStarted", false);
				editor.commit();
				startActivity(new Intent(Main1.this, Login.class));
				finish();
			}
		});

	}
}