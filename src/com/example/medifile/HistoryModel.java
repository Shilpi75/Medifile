package com.example.medifile;

public class HistoryModel {
	boolean checked=false;
	String id;
	String title;
	String doctorName;
	String hospitalName;
	String location;
	String dateOfVisit;
	String prescription;
	String specification;
	String surgery;
	int fees;
	int nextVisit_day;
	int nextVisit_month;
	int nextVisit_year;
	String otherInfo;
	String contactNo1;
	String contactNo2;

	public HistoryModel(String id,String title, String doctorName, String hospitalName,
			String location, String dateOfVisit, String prescription,
			String specification, String surgery, int fees, int nextVisit_day,
			int nextVisit_month, int nextVisit_year, String otherInfo,
			String contactNo1, String contactNo2) {
this.id=id;
		this.title = title;
		this.doctorName = doctorName;
		this.hospitalName = hospitalName;
		this.location = location;
		this.dateOfVisit = dateOfVisit;
		this.prescription = prescription;
		this.specification = specification;
		this.surgery = surgery;
		this.fees = fees;
		this.nextVisit_day = nextVisit_day;
		this.nextVisit_month = nextVisit_month;
		this.nextVisit_year = nextVisit_year;
		this.otherInfo = otherInfo;
		this.contactNo1 = contactNo1;
		this.contactNo2 = contactNo2;
	}

	public String getTitle() {
		return title;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public String getLocation() {
		return location;
	}

	public String getDateOfVisit() {
		return dateOfVisit;
	}

	public String getPrescription() {
		return prescription;
	}

	public String getSpecification() {
		return specification;
	}

	public String getSurgery() {
		return surgery;
	}

	public int getFees() {
		return fees;
	}

	public int getNextVisit_day() {
		return nextVisit_day;
	}

	public int getNextVisit_month() {
		return nextVisit_month;
	}

	public int getNextVisit_year() {
		return nextVisit_year;
	}

	public String getOtherInfo() {
		return otherInfo;
	}

	public String getContactNo1() {
		return contactNo1;
	}

	public String getContactNo2() {
		return contactNo2;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setDateOfVisit(String dateOfVisit) {
		this.dateOfVisit = dateOfVisit;
	}

	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}

	public void setSpecification(String specification) {
		this.specification = specification;
	}

	public void setSurgery(String surgery) {
		this.surgery = surgery;
	}

	public void setFees(int fees) {
		this.fees = fees;
	}

	public void setNextVisit_day(int nextVisit_day) {
		this.nextVisit_day = nextVisit_day;
	}

	public void setNextVisit_month(int nextVisit_month) {
		this.nextVisit_month = nextVisit_month;
	}

	public void setNextVisit_year(int nextVisit_year) {
		this.nextVisit_year = nextVisit_year;
	}

	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}

	public void setContactNo1(String contactNo1) {
		this.contactNo1 = contactNo1;
	}

	public void setContactNo2(String contactNo2) {
		this.contactNo2 = contactNo2;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

}
