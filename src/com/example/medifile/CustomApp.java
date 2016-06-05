package com.example.medifile;

import java.util.List;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

public class CustomApp extends ArrayAdapter<ModelApp>{
Context context;
List<ModelApp> model;
Databasehandler handler;
AlertDialog.Builder dialog;
	public CustomApp(Context context, List<ModelApp> objects) {
		super(context,NO_SELECTION, objects);
		// TODO Auto-generated constructor stub
		this.context=context;
		this.model=objects;
	}
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view;
		if( convertView==null)
		{
			LayoutInflater inflator = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			view = inflator.inflate(R.layout.list_appointments, null);
		}
		else
			view=convertView;
		Log.e("shilpi","in Custom app");
		TextView t1=(TextView)view.findViewById(R.id.textView1);
		TextView t2=(TextView)view.findViewById(R.id.textView2);
		ImageButton edit=(ImageButton)view.findViewById(R.id.imageButton1);
		ImageButton delete=(ImageButton)view.findViewById(R.id.imageButton2);
		
		edit.setFocusable(false);
		edit.setFocusableInTouchMode(false);

		delete.setFocusable(false);
		delete.setFocusableInTouchMode(false);
		
		
		handler=new Databasehandler(context);
		
		
		String Title=model.get(position).getTitle();
		int Date=model.get(position).getDate();
		int Month=model.get(position).getMonth();
		int Year=model.get(position).getYear();
		
		Typeface tf=Typeface.createFromAsset(getContext().getAssets(), "arial.ttf");
		t1.setTypeface(tf);
		t2.setTypeface(tf);	
		
		t1.setText(Date+"/"+Month+"/"+Year);
		t2.setText(Title);
		
		edit.setOnClickListener(new  OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in=new Intent(context,EditApp.class);
				in.putExtra("id", model.get(position).getId());
				context.startActivity(in);
			}
		});
		
		delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog=new AlertDialog.Builder(context);
				dialog.setTitle("Delete Appointment")
				.setMessage("Are you sure to delete Appointment?")
				.setCancelable(true)
				.setPositiveButton("Delete",new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						handler.deleteApp(model.get(position).getId());
						context.startActivity(new Intent(context,Appointments.class));
					
					}
				}) 
				.setNegativeButton("Cancel", null)
				.show();
			}
			
		});
		return view;
	}
	

}
