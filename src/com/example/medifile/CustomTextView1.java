package com.example.medifile;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class CustomTextView1 extends TextView {

	/**
	 * @param context
	 * @param attrs
	 * @param defStyleAttr
	 * @param defStyleRes
	 */
	public CustomTextView1(Context context, AttributeSet attrs,
			int defStyleAttr, int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		customfont();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyleAttr
	 */
	public CustomTextView1(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		customfont();
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public CustomTextView1(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		customfont();
	}

	/**
	 * @param context
	 */
	public CustomTextView1(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		customfont();
	}
	public void customfont()
	{
		Typeface tf=Typeface.createFromAsset(getContext().getAssets(), "Allura-Regular.ttf");
		setTypeface(tf); 
	}

}
