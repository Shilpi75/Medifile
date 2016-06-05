package com.example.medifile;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Custom_History extends ArrayAdapter<HistoryModel> {
	Context context;
	List<HistoryModel> model;
	HistoryModel obj;
	HistoryViewHolder holder;
	public Custom_History(Context context, List<HistoryModel> objects) {
		super(context, NO_SELECTION, objects);

		this.context = context;
		this.model = objects;

	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		TextView t1, t2;
		CheckBox c1;
		ImageButton edit, call;
		// TODO Auto-generated method stub
		obj = this.getItem(position);
		if (convertView == null) {
			LayoutInflater inflator = LayoutInflater.from(context);
			convertView = inflator.inflate(R.layout.list_history, null);
			t1 = (TextView) convertView.findViewById(R.id.textView1);
			t2 = (TextView) convertView.findViewById(R.id.textView2);
			c1 = (CheckBox) convertView.findViewById(R.id.checkBox1);
			call = (ImageButton) convertView.findViewById(R.id.imageButton2);
			edit = (ImageButton) convertView.findViewById(R.id.imageButton1);
			convertView.setTag(new HistoryViewHolder(t1, t2, call, edit, c1));

			// c1.setOnClickListener(new OnClickListener() {
			//
			// @Override
			// public void onClick(View v) {
			// // TODO Auto-generated method stub
			// CheckBox cb = (CheckBox) v;
			// HistoryModel obj = (HistoryModel) cb.getTag();
			// obj.setChecked(cb.isChecked());
			// }
			// });
		} else {
			
			holder = (HistoryViewHolder) convertView.getTag();
			t1 = holder.getT1();
			t2 = holder.getT2();
			call = holder.getCall();
			edit = holder.getEdit();
			c1 = holder.getC1();

		}

		call.setFocusable(false);
		call.setFocusableInTouchMode(false);
		edit.setFocusable(false);
		edit.setFocusableInTouchMode(false);
		c1.setFocusable(false);
		c1.setFocusableInTouchMode(false);
		c1.setVisibility(View.GONE);
		String title = obj.getTitle();
		String doc = obj.getDoctorName();

		Typeface tf=Typeface.createFromAsset(getContext().getAssets(), "arial.ttf");
t1.setTypeface(tf);
		
		
		t1.setText(title);
		if(doc.equals(""))
		t2.setText(doc);
		else
			t2.setText("Dr. "+doc);

		
		
		
		call.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String contact = model.get(position).getContactNo1();

				if (contact.isEmpty())
					contact = model.get(position).getContactNo2();
				if(contact.isEmpty())
					CustomToast.printToast(context, "No number saved..", 0, R.drawable.error);
				try {
					// set the data

					String uri = "tel:" + contact;

					Intent callIntent = new Intent(Intent.ACTION_CALL, Uri
							.parse(uri));

					context.startActivity(callIntent);

				} catch (Exception e) {

					CustomToast.printToast(context, "Call has failed", 0, R.drawable.ic_error);
					e.printStackTrace();

				}
			}
		});

		c1.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				Log.e("shilpi", "check change" + isChecked);
				obj=model.get(position);
				obj.setChecked(isChecked);
				Log.e("check change list"," "+obj.getTitle());
				if (!isChecked) {

					buttonView.setVisibility(View.GONE);

				}

			}
		});

		edit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent in = new Intent(context, EditHistory.class);
				in.putExtra("id", model.get(position).getId());
				context.startActivity(in);

			}
		});

		return convertView;
	}

}
