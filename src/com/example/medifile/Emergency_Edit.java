package com.example.medifile;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Emergency_Edit extends ActionBarActivity implements OnClickListener {
EditText contact;
Button save;
SharedPreferences pref;
SharedPreferences.Editor editor;
Toolbar toolbar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_emergencyedit);
		
		

		toolbar = (Toolbar) findViewById(R.id.actionbar);
		toolbar.setTitle("Emergency Number");
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		
		save=(Button)findViewById(R.id.button1);
		contact=(EditText)findViewById(R.id.editText1);
		pref=getSharedPreferences("emergency", Context.MODE_PRIVATE);
		editor=pref.edit();
		String current=pref.getString("contact", null);
		contact.setText(current);
		save.setOnClickListener(this);
	}



	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String newContact=contact.getText().toString();
		if(newContact.equals("") )
		{
			CustomToast.printToast(Emergency_Edit.this,
					"Enter valid number", 0, R.drawable.error);
		}
			
		else{
		editor.putString("contact",newContact);
		editor.commit();
		CustomToast.printToast(this, "Done", 0, R.drawable.anydo);
		startActivity(new Intent(Emergency_Edit.this,Emergency.class));
		}
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.emergency__edit, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		if(item.getItemId()==android.R.id.home)
		onBackPressed();
		return super.onOptionsItemSelected(item);
	}

}
