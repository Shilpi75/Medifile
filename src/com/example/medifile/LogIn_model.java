package com.example.medifile;

public class LogIn_model {
/**
	 * @param firtName
	 * @param lastName
	 * @param age
	 * @param gender
	 * @param contact
	 * @param address
	 * @param occupation
	 * @param password
	 */
	public LogIn_model(String firtName, String lastName, int age,
			String gender, String contact, String address, String occupation,
			String password) {
		super();
		this.firtName = firtName;
		LastName = lastName;
		this.age = age;
		this.gender = gender;
		this.contact = contact;
		this.address = address;
		this.occupation = occupation;
		this.password = password;
	}
String firtName;
String LastName;
int age;
String gender;
String contact;
String address;
String occupation;
String password;
public String getFirtName() {
	return firtName;
}
public String getLastName() {
	return LastName;
}
public int getAge() {
	return age;
}
public String getGender() {
	return gender;
}
public String getContact() {
	return contact;
}
public String getAddress() {
	return address;
}
public String getOccupation() {
	return occupation;
}
public String getPassword() {
	return password;
}
public void setFirtName(String firtName) {
	this.firtName = firtName;
}
public void setLastName(String lastName) {
	LastName = lastName;
}
public void setAge(int age) {
	this.age = age;
}
public void setGender(String gender) {
	this.gender = gender;
}
public void setContact(String contact) {
	this.contact = contact;
}
public void setAddress(String address) {
	this.address = address;
}
public void setOccupation(String occupation) {
	this.occupation = occupation;
}
public void setPassword(String password) {
	this.password = password;
}


}
