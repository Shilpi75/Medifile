package com.example.medifile;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class Custom_nav extends ArrayAdapter<NavModel> {
	Context context;
	ArrayList<NavModel> model;

	public Custom_nav(Context context, ArrayList<NavModel> objects) {
		super(context, NO_SELECTION, objects);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.model = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
			LayoutInflater inflator = LayoutInflater.from(context);
			convertView = inflator.inflate(R.layout.navigationdrawer, null);
		}

		TextView t1=(TextView)convertView.findViewById(R.id.textView1);
		ImageView image=(ImageView)convertView.findViewById(R.id.imageView1);
		t1.setText(model.get(position).getTitle());
		image.setImageResource(model.get(position).getImage());
		
		
		return convertView;
	}

}
