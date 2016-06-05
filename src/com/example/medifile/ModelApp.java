package com.example.medifile;

public class ModelApp {
	String id;
String title;
int date;
int month;
int year;
String time;
/**
 * @param title
 * @param date
 * @param month
 * @param year
 */

public ModelApp(String id,String title, int date, int month, int year, String time) {
	super();
	this.id=id;
	this.title = title;
	this.date = date;
	this.month = month;
	this.year = year;
	this.time = time;
}
public String getTitle() {
	return title;
}
public int getDate() {
	return date;
}
public int getMonth() {
	return month;
}
public int getYear() {
	return year;
}
public void setTitle(String title) {
	this.title = title;
}
public void setDate(int date) {
	this.date = date;
}
public void setMonth(int month) {
	this.month = month;
}
public void setYear(int year) {
	this.year = year;
	
}
public String getTime() {
	return time;
}
public void setTime(String time) {
	this.time = time;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}



}
