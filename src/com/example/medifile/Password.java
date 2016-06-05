package com.example.medifile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class Password extends ActionBarActivity implements OnClickListener {
ImageButton b1;
TextView t1;
EditText e1;
ImageView image;
Databasehandler handler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_password);
		b1=(ImageButton)findViewById(R.id.button1);
		t1=(TextView)findViewById(R.id.textView1);
		e1=(EditText)findViewById(R.id.editText1);
		image=(ImageView)findViewById(R.id.image1);
		handler=new Databasehandler(this);
		t1.setVisibility(View.GONE);
		image.setVisibility(View.GONE);
		b1.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.password, menu);
		return true;
		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String password=e1.getText().toString();
		
		String logined=handler.getPass();
		
		if(password.equals(logined))
		{
			t1.setText("password matched");
			image.setImageResource(R.drawable.anydo);
			t1.setVisibility(View.VISIBLE);
			image.setVisibility(View.VISIBLE);
			startActivity(new Intent(this,History.class));
			finish();
		}
		
		else
		{
			t1.setText("Sorry!! password didnt match!! try again");
			image.setImageResource(R.drawable.ic_error);
			image.setVisibility(View.VISIBLE);
			t1.setVisibility(View.VISIBLE);
		}
		
	}
}
