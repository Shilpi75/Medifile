package com.example.medifile;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CustomToast {
	public static void printToast(Context context, String message,
			int duration, int image) {
		LayoutInflater inflator = LayoutInflater.from(context);
		View mainLayout = inflator.inflate(R.layout.customtoast, null);
		View rootLayout = mainLayout.findViewById(R.id.roottoast);
		TextView t1 = (TextView) mainLayout.findViewById(R.id.textView1);
		t1.setText(message);
		ImageView img = (ImageView) mainLayout.findViewById(R.id.imageView1);
		img.setImageResource(image);
		Toast toast = new Toast(context);
		toast.setDuration(duration);
		toast.setView(rootLayout);
		toast.show();

	}
}
