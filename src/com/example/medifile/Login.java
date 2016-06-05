package com.example.medifile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class Login extends ActionBarActivity implements OnClickListener {
EditText firstname,lastname,age,contact,password,address,occupation,password2;
RadioButton male,female;
Button b1;
Databasehandler handler;
Toolbar toolbar;
SharedPreferences pref;
SharedPreferences.Editor editor;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		
		handler=new Databasehandler(this);
		toolbar=(Toolbar)findViewById(R.id.actionbar);
		toolbar.setTitle("Medifile Login");
		setSupportActionBar(toolbar);
		pref=getSharedPreferences("main",Context.MODE_PRIVATE);
		editor=pref.edit();
		if(pref.getBoolean("login", false)==true)
			{startActivity(new Intent(this,ScreenSplash.class));
			finish();
			}
			
		getSupportActionBar().setIcon(R.drawable.ic_launcher);
		b1=(Button)findViewById(R.id.button1);
		firstname=(EditText)findViewById(R.id.firstname);
		lastname=(EditText)findViewById(R.id.lastname);
		age=(EditText)findViewById(R.id.age);
		contact=(EditText)findViewById(R.id.contact);
		address=(EditText)findViewById(R.id.address);
		password=(EditText)findViewById(R.id.password);
		male=(RadioButton)findViewById(R.id.radio1);
		female=(RadioButton)findViewById(R.id.radio2);
		occupation=(EditText)findViewById(R.id.occupation);
		password2=(EditText)findViewById(R.id.password2);
		Typeface tf=Typeface.createFromAsset(this.getAssets(), "journal.ttf");
		male.setTypeface(tf); 
		female.setTypeface(tf);
		b1.setOnClickListener(this);
		b1.setTypeface(tf);
	
		firstname.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
//				Log.e("shilpi",""+s.length());
//				if(s.length()<3){
//					firstname.setError("First name must be at least 3 letters");
//				}
//				else if (!s.toString().matches("/^[a-z]+$"))
//				firstname.setError("First name must be alphabets only");
//				else{
//					firstname.setError(null);
//				}
//				if((s.toString().matches("/^[a-z]+$")))
//				firstname.setError("First name must be alphabets only");
//				else
//					firstname.setError(null);
//			}
			}
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
//				if(s.length()<3){
//					firstname.setError("First name must be at least 3 letters");
//				}
//				else if (!s.toString().matches("/^[a-z]+$"))
//				firstname.setError("First name must be alphabets only");
				
				if(!(s.toString().matches("^[a-zA-Z ]{3,30}$")))
					firstname.setError("First name must be at least 3 letters and alphabets only");
				else{
					firstname.setError(null);
				}
//				if((s.toString().matches("/^[a-z]+$")))
//				firstname.setError("First name must be alphabets only");
//				else
//					firstname.setError(null);
//				else if (s.toString().matches("/^[a-z]+$"))
//					firstname.setError("First name must be alphabets only");
			}
		});
		
		password.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if(password2.getText().toString().equals(s.toString()))
					password2.setError(null);
			}
		});
		
		
		age.addTextChangedListener(new  TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if(Integer.parseInt(s.toString())<18 | s.toString().equals(""))
					age.setError("Age must be greater than 18");
				
			}
		});
		
		
		
		password.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				String p = s.toString();
				if (!checkPassword(p))
					password.setError("password must be at least 6 characters");

			}
		});
		
		
		
		password2.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if(!s.toString().equals(password.getText().toString()))
						password2.setError("");
				else
					password2.setError(null);
					
				
			}
		});
		
	}

	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		String FirstName=firstname.getText().toString();
		String LastName=lastname.getText().toString();
		String gender;
		if(male.isChecked())
			gender="male";
		else if(female.isChecked())
			gender="female";
		else
			gender=" ";
		String Password=password.getText().toString();
		String Contact=contact.getText().toString();
		String Address=address.getText().toString();
		String Age=age.getText().toString();
	//int Age=Integer.parseInt(age.getText().toString());
		String Occupation=occupation.getText().toString();
		if(!(FirstName.toString().matches("^[a-zA-Z ]{3,30}$")))
			firstname.setError("First name must be at least 3 letters and alphabets only");
		else if(Age.equals(""))
			age.setError("Age must be greater than 18");
		else if(Integer.parseInt(Age)<18)
		age.setError("Age must be greater than 18");
		else if(gender.equals(" "))
			Toast.makeText(Login.this,"Select gender", 0).show();
		else if(!checkEmail(Contact.toString()))
			contact.setError("enter valid email id.");
		else if(!checkPassword(Password))

			password.setError("password must be at least 6 characters");
		else if(!(password2.getText().toString().equals(Password)))
			password2.setError("password didnt match.");
		else{
		
	
		
		LogIn_model obj=new LogIn_model(FirstName, LastName, Integer.parseInt(Age), gender, Contact, Address, Occupation, Password);
		handler.insertLogin(obj);
		handler.close();
		CustomToast.printToast(this,"Successfully Added",0, R.drawable.anydo);
		editor.putBoolean("login", true);
		editor.commit();
		startActivity(new Intent(this,ScreenSplash.class));
		this.finish();
		}
		
		
	}
	

	public boolean checkEmail(String mail) {
		String Check = "^[_A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(Check);
		Matcher matcher = pattern.matcher(mail);
		return matcher.matches();
	}
		
		public boolean checkPassword(String pass) {
			if (pass != null && pass.length() < 6)
				return false;

			else
				return true;

		}
		

		
	
	@Override
	public void onBackPressed()
	{
		finish();
	}
	
}
