package com.example.medifile;

import com.example.medifile.R.color;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

public class CustomEditText1 extends EditText{

	/**
	 * @param context
	 * @param attrs
	 * @param defStyleAttr
	 * @param defStyleRes
	 */
	public CustomEditText1(Context context, AttributeSet attrs,
			int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		// TODO Auto-generated constructor stub
		custom();
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyleAttr
	 */
	public CustomEditText1(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		custom();
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public CustomEditText1(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		custom();
	}

	/**
	 * @param context
	 */
	public CustomEditText1(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		custom();
	}
	public void custom()
	{
		setTextColor(getResources().getColor(color.black));
		Typeface tf=Typeface.createFromAsset(getContext().getAssets(), "Sansation-Regular.ttf");
		setTypeface(tf); 
		setTextSize(20);
		setBackgroundColor(getResources().getColor(color.white));
		
	}

}
