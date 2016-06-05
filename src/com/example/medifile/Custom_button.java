package com.example.medifile;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

public class Custom_button extends Button {

	/**
	 * @param context
	 * @param attrs
	 * @param defStyleAttr
	 * @param defStyleRes
	 */
	public Custom_button(Context context, AttributeSet attrs, int defStyleAttr,
			int defStyleRes) {
		super(context, attrs, defStyleAttr, defStyleRes);
		// TODO Auto-generated constructor stub
		customButton();
	}

	/**
	 * @param context
	 * @param attrs
	 * @param defStyleAttr
	 */
	public Custom_button(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		customButton();
	}

	/**
	 * @param context
	 * @param attrs
	 */
	public Custom_button(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		customButton();
	}

	/**
	 * @param context
	 */
	public Custom_button(Context context) {
		super(context);
		// TODO Auto-generated constructor stub;
		customButton();
	}

	
	void customButton()
	{
		Typeface tf=Typeface.createFromAsset(getContext().getAssets(), "journal.ttf");
		setTypeface(tf); 
	}
}
