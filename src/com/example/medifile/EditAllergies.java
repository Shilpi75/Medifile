package com.example.medifile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditAllergies extends ActionBarActivity  {
	Toolbar toolbar;
EditText e1,e2,e3,e4;
Databasehandler handler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_allergies);
		
		toolbar=(Toolbar)findViewById(R.id.actionbar);
		toolbar.setTitle("Edit Allergies");
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		
		e1=(EditText)findViewById(R.id.editText1);
		e2=(EditText)findViewById(R.id.editText2);
		e3=(EditText)findViewById(R.id.editText3);
		e4=(EditText)findViewById(R.id.editText4);
		
		handler=new Databasehandler(this);
		String food=handler.getAllergies("food");
		e1.setText(food);
		e2.setText(handler.getAllergies("medicines"));
		e3.setText(handler.getAllergies("skin"));
		e4.setText(handler.getAllergies("animals"));
		
		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.edit_allergies, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if(id==R.id.done)
		{
			String food=e1.getText().toString();
			String medicines=e2.getText().toString();
			String skin=e3.getText().toString();
			String animals=e4.getText().toString();
			
			Model_Allergies obj=new Model_Allergies(food, medicines, skin, animals);
			handler.insert_allergies(obj);
			CustomToast.printToast(this, "Allergies sucessfully added", 0,R.drawable.anydo);
			startActivity(new Intent(this,Allergies.class));
		}
		if(id==android.R.id.home)
			onBackPressed();
		return super.onOptionsItemSelected(item);
	}


}
