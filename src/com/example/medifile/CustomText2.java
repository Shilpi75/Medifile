package com.example.medifile;

import com.example.medifile.R.color;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class CustomText2 extends TextView {

	/**
	 * @param context
	 * @param attrs
	 * @param defStyleAttr
	 * @param defStyleRes
	 */
	public CustomText2(Context context, AttributeSet attrs, int defStyleAttr,
			int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		// TODO Auto-generated constructor stub
		custom();
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyleAttr
	 */
	public CustomText2(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		custom();
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public CustomText2(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		custom();
	}

	/**
	 * @param context
	 */
	public CustomText2(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		custom();
	}

	public void custom() {
		setTextColor(getResources().getColor(color.myPrimaryColor));
		Typeface tf=Typeface.createFromAsset(getContext().getAssets(), "journal.ttf");
		setTypeface(tf); 
		setTextSize(30);
		

	}

}
