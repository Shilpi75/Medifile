package com.example.medifile;

import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

public class HistoryViewHolder {
	TextView t1,t2;
	ImageButton call,edit;
	CheckBox c1;
	/**
	 * @param t1
	 * @param t2
	 * @param call
	 * @param edit
	 * @param c1
	 */
	public HistoryViewHolder(TextView t1, TextView t2, ImageButton call,
			ImageButton edit, CheckBox c1) {
		super();
		this.t1 = t1;
		this.t2 = t2;
		this.call = call;
		this.edit = edit;
		this.c1 = c1;
	}
	public TextView getT1() {
		return t1;
	}
	public TextView getT2() {
		return t2;
	}
	public ImageButton getCall() {
		return call;
	}
	public ImageButton getEdit() {
		return edit;
	}
	public CheckBox getC1() {
		return c1;
	}
	public void setT1(TextView t1) {
		this.t1 = t1;
	}
	public void setT2(TextView t2) {
		this.t2 = t2;
	}
	public void setCall(ImageButton call) {
		this.call = call;
	}
	public void setEdit(ImageButton edit) {
		this.edit = edit;
	}
	public void setC1(CheckBox c1) {
		this.c1 = c1;
	}

}
