package com.example.medifile;

public class NavModel {
String title;
int image;
/**
 * @param title
 * @param image
 */
public NavModel(String title, int image) {
	super();
	this.title = title;
	this.image = image;
}
public String getTitle() {
	return title;
}
public int getImage() {
	return image;
}
public void setTitle(String title) {
	this.title = title;
}
public void setImage(int image) {
	this.image = image;
}
}
