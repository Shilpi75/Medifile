package com.example.medifile;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Databasehandler extends SQLiteOpenHelper {
	// table allergies
	static final String TABLE_ALLERGIES = "allergies";
	static final String DATABASE = "medifile";
	static final int VERSION = 1;
	static final String ALLERGIES_1 = "food";
	static final String ALLERGIES_2 = "medicines";
	static final String ALLERGIES_3 = "skin";
	static final String ALLERGIES_4 = "animals";

	// table login
	static final String TABLE_LOGIN = "login";
	static final String LOGIN_FIRSTNAME = "firstname";
	static final String LOGIN_LASTNAME = "lastname";
	static final String LOGIN_AGE = "age";
	static final String LOGIN_GENDER = "gender";
	static final String LOGIN_CONTACT = "contact";
	static final String LOGIN_ADDRESS = "address";
	static final String LOGIN_OCCUPATION = "occupation";
	static final String LOGIN_PASSWORD = "password";

	// table history

	static final String TABLE_HISTORY = "history";
	static final String HISTORY_ID = "id";
	static final String HISTORY_TITLE = "title";
	static final String HISTORY_DOCTOR = "doctorName";
	static final String HISTORY_HOSPITAL = "hospitalName";
	static final String HISTORY_LOCATION = "location";
	static final String HISTORY_DATEOFVISIT = "dateOfVisit";
	static final String HISTORY_PRESCRIPTION = "prescription";
	static final String HISTORY_SPECIFICATION = "specification";
	static final String HISTORY_SURGERY = "surgery";
	static final String HISTORY_FEES = "fees";
	static final String HISTORY_NEXTDAY = "nextDay";
	static final String HISTORY_NEXTMONTH = "nextMonth";
	static final String HISTORY_NEXTYEAR = "nextYear";
	static final String HISTORY_OTHER = "other";
	static final String HISTORY_CONTACT1 = "contact1";
	static final String HISTORY_CONTACT2 = "contact2";

	// table appointments
	static final String TABLE_APP = "appointments";
	static final String APP_ID="id";
	static final String APP_TTILE = "title";
	static final String APP_DATE = "date";
	static final String APP_MONTH = "month";
	static final String APP_YEAR = "year";
	static final String APP_TIME = "time";

	public Databasehandler(Context context) {
		super(context, DATABASE, null, VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String CREATE_ALLERGIES = "create table " + TABLE_ALLERGIES + " ( "
				+ ALLERGIES_1 + " text , " + ALLERGIES_2 + " text, "
				+ ALLERGIES_3 + " text, " + ALLERGIES_4 + " text );";
		db.execSQL(CREATE_ALLERGIES);

		String CREATE_LOGIN = "create table " + TABLE_LOGIN + " ( "
				+ LOGIN_FIRSTNAME + " TEXT, " + LOGIN_LASTNAME + " TEXT, "
				+ LOGIN_GENDER + " TEXT, " + LOGIN_AGE + " INTEGER, "
				+ LOGIN_CONTACT + " TEXT , " + LOGIN_ADDRESS + " TEXT, "
				+ LOGIN_OCCUPATION + " TEXT, " + LOGIN_PASSWORD + " TEXT );";
		db.execSQL(CREATE_LOGIN);

		String CREATE_HISTORY = "create table " + TABLE_HISTORY + " ( "
				+ HISTORY_ID + " text, " + HISTORY_TITLE + " Text, "
				+ HISTORY_DOCTOR + " Text, " + HISTORY_HOSPITAL + " text, "
				+ HISTORY_LOCATION + " text, " + HISTORY_DATEOFVISIT
				+ " text, " + HISTORY_PRESCRIPTION + " text, "
				+ HISTORY_SPECIFICATION + " text, " + HISTORY_SURGERY
				+ " Text, " + HISTORY_FEES + " integer, " + HISTORY_NEXTDAY
				+ " integer, " + HISTORY_NEXTMONTH + " integer, "
				+ HISTORY_NEXTYEAR + " integer, " + HISTORY_OTHER + " text, "
				+ HISTORY_CONTACT1 + " text, " + HISTORY_CONTACT2 + " text);";
		db.execSQL(CREATE_HISTORY);

		String CREATE_APP = "create table " + TABLE_APP + " ( " + APP_ID+" Text, "+APP_TTILE
				+ " text, " + APP_DATE + " integer, " + APP_MONTH
				+ " integer, " + APP_YEAR + " integer, " + APP_TIME + " text);";
		db.execSQL(CREATE_APP);
		// insert_app();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	void insert_allergies(Model_Allergies obj) {

		SQLiteDatabase db = this.getWritableDatabase();
		db.execSQL("delete from " + TABLE_ALLERGIES);
		String food = obj.getFood();
		String medicines = obj.getMedicines();
		String skin = obj.getSkin();
		String animals = obj.getAnimals();
		// Log.e("shilpi",food+medicines+skin+animals);
		ContentValues values = new ContentValues();
		values.put(ALLERGIES_1, food);
		values.put(ALLERGIES_2, medicines);
		values.put(ALLERGIES_3, skin);
		values.put(ALLERGIES_4, animals);
		db.insert(TABLE_ALLERGIES, null, values);
		db.close();

	}

	String getAllergies(String name) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from " + TABLE_ALLERGIES, null);

		if (cursor.moveToFirst()) {
			Log.e("shilpi", cursor.getString(0) + cursor.getString(1));
			switch (name) {
			case "food":

				return cursor.getString(0);

			case "medicines":
				return cursor.getString(1);

			case "skin":
				return cursor.getString(2);

			case "animals":
				return cursor.getString(3);

			default:
				return "not added";
			}
		}

		else {
			
			return "not added yet";
		}

	}
	
	
	void deleteAllergies()
	{
		SQLiteDatabase db=this.getWritableDatabase();
		db.execSQL("delete from " + TABLE_ALLERGIES);
		db.close();
	}

	// table login
	public void insertLogin(LogIn_model obj) {
		SQLiteDatabase db = this.getWritableDatabase();
		// db.execSQL("delete from "+TABLE_LOGIN);
		String firstname = obj.getFirtName();
		String lastname = obj.getLastName();
		String gender = obj.getGender();
		int age = obj.getAge();
		String contact = obj.getContact();
		String occupation = obj.getOccupation();
		String password = obj.getPassword();
		String address = obj.getAddress();
		ContentValues values = new ContentValues();
		values.put(LOGIN_FIRSTNAME, firstname);
		values.put(LOGIN_LASTNAME, lastname);
		values.put(LOGIN_GENDER, gender);
		values.put(LOGIN_AGE, age);
		values.put(LOGIN_CONTACT, contact);

		values.put(LOGIN_ADDRESS, address);
		values.put(LOGIN_OCCUPATION, occupation);

		values.put(LOGIN_PASSWORD, password);
		Log.e("shilpi", " 1." + password + firstname + " " + lastname);

		db.insert(TABLE_LOGIN, null, values);

		// Log.e("shilpi",password);
		db.close();
		SQLiteDatabase db1 = this.getReadableDatabase();
		Cursor cursor = db1.rawQuery("select * from " + TABLE_LOGIN, null);
		Log.e("shilpi", " 1." + cursor.moveToFirst());
	}

	public String getPass() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from " + TABLE_LOGIN, null);
		Log.e("shilpi", " " + cursor.moveToFirst());
		cursor.moveToFirst();
		String password = cursor.getString(7);
		Log.e("shilpi", password);
		return password;
	}

	// table history
	void insertHistory(HistoryModel obj) {
		SQLiteDatabase db = this.getWritableDatabase();
		String id = obj.getId();
		String title = obj.getTitle();
		String doctor = obj.getDoctorName();
		String hospital = obj.getHospitalName();
		String location = obj.getLocation();
		String date = obj.getDateOfVisit();
		String prescription = obj.getPrescription();
		String specification = obj.getSpecification();
		String surgery = obj.getSurgery();
		int fees = obj.getFees();
		int nextDay = obj.getNextVisit_day();
		int nextMonth = obj.getNextVisit_month();
		int nextYear = obj.getNextVisit_year();
		String other = obj.getOtherInfo();
		String contact1 = obj.getContactNo1();
		String contact2 = obj.getContactNo2();
		ContentValues values = new ContentValues();
		values.put(HISTORY_ID, id);
		values.put(HISTORY_TITLE, title);
		values.put(HISTORY_DOCTOR, doctor);
		values.put(HISTORY_HOSPITAL, hospital);
		values.put(HISTORY_LOCATION, location);
		values.put(HISTORY_DATEOFVISIT, date);
		values.put(HISTORY_PRESCRIPTION, prescription);
		values.put(HISTORY_SPECIFICATION, specification);
		values.put(HISTORY_SURGERY, surgery);
		values.put(HISTORY_FEES, fees);
		values.put(HISTORY_NEXTDAY, nextDay);
		values.put(HISTORY_NEXTMONTH, nextMonth);
		values.put(HISTORY_NEXTYEAR, nextYear);
		values.put(HISTORY_OTHER, other);
		values.put(HISTORY_CONTACT1, contact1);
		values.put(HISTORY_CONTACT2, contact2);
		db.insert(TABLE_HISTORY, null, values);
		db.close();
		
		SQLiteDatabase db2=this.getWritableDatabase();
		ContentValues values2=new  ContentValues();
		values2.put(APP_ID, id);
		values2.put(APP_TTILE, title);
		values2.put(APP_DATE, nextDay);
		values2.put(APP_MONTH, nextMonth);
		values2.put(APP_YEAR,nextYear);
		db2.insert(TABLE_APP, null, values2);
		db2.close();
	}

	List<HistoryModel> getAllHistory() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor;
		List<HistoryModel> list = new ArrayList<HistoryModel>();
		cursor = db.rawQuery("select * from " + TABLE_HISTORY, null);
		if (cursor != null) {
			if (cursor.moveToLast())
				do {
					String id = cursor.getString(0);
					String title = cursor.getString(1);
					String doctor = cursor.getString(2);
					String hospital = cursor.getString(3);
					String location = cursor.getString(4);
					String date = cursor.getString(5);
					String prescription = cursor.getString(6);
					String specification = cursor.getString(7);
					String surgery = cursor.getString(8);
					int fees = Integer.parseInt(cursor.getString(9));
					int nextdate = cursor.getInt(10);
					int nextmonth = cursor.getInt(11);
					int nextyear = cursor.getInt(12);
					String other = cursor.getString(13);
					String contact1 = cursor.getString(14);
					String contact2 = cursor.getString(15);

					HistoryModel obj = new HistoryModel(id, title, doctor,
							hospital, location, date, prescription,
							specification, surgery, fees, nextdate, nextmonth,
							nextyear, other, contact1, contact2);
					list.add(obj);
				} while (cursor.moveToPrevious());

		}
		return list;
	}

	HistoryModel getHistory(String id) {
		SQLiteDatabase db = this.getReadableDatabase();

		HistoryModel obj = null;
		Cursor cursor = db.rawQuery("select * from " + TABLE_HISTORY
				+ " where " + HISTORY_ID + " =?",
				new String[] { String.valueOf(id) });
		if (cursor.moveToFirst()) {
			String Id = cursor.getString(0);
			String title = cursor.getString(1);
			String doctor = cursor.getString(2);
			String hospital = cursor.getString(3);
			String location = cursor.getString(4);
			String date = cursor.getString(5);
			String prescription = cursor.getString(6);
			String specification = cursor.getString(7);
			String surgery = cursor.getString(8);
			int fees = Integer.parseInt(cursor.getString(9));
			int nextdate = cursor.getInt(10);
			int nextmonth = cursor.getInt(11);
			int nextyear = cursor.getInt(12);
			String other = cursor.getString(13);
			String contact1 = cursor.getString(14);
			String contact2 = cursor.getString(15);

			obj = new HistoryModel(id, title, doctor, hospital, location, date,
					prescription, specification, surgery, fees, nextdate,
					nextmonth, nextyear, other, contact1, contact2);
		}
		return obj;

	}

	void deleteHistory(String id) {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete(TABLE_HISTORY, HISTORY_ID + " =?",
				new String[] { String.valueOf(id) });
		db.delete(TABLE_APP, APP_ID+" =?", new String[]{id});
		db.close();

	}

	List<String> titleList() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from " + TABLE_HISTORY, null);
		List<String> list = new ArrayList<String>();
		if (cursor != null) {
			cursor.moveToFirst();
			do {
				String title = cursor.getString(1);
				list.add(title);
			} while (cursor.moveToNext());
			
		}
		return list;
	}
	
	String searchTitle(String item)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		String id;
		Cursor cursor=db.rawQuery("select * from "+TABLE_HISTORY+" where "+HISTORY_TITLE+" =?",new String[]{item});
		if(cursor!=null)
		{
			if(cursor.moveToFirst())
			id=cursor.getString(0);
			else
				id="null";
		}
		else
			id="null";
		return id;
		
	}

	
	List<String> docList() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from " + TABLE_HISTORY, null);
		List<String> list = new ArrayList<String>();
		if (cursor != null) {
			cursor.moveToFirst();
			do {
				String title = cursor.getString(2);
				list.add(title);
			} while (cursor.moveToNext());
			
		}
		return list;
	}
	
	String searchDoc(String item)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		String id;
		Cursor cursor=db.rawQuery("select * from "+TABLE_HISTORY+" where "+HISTORY_DOCTOR+" =?",new String[]{item});
		if(cursor!=null)
		{
			if(cursor.moveToFirst())
			id=cursor.getString(0);
			else
				id="null";
		}
		else
			id="null";
		return id;
		
	}
	
	
	void updateHistory(HistoryModel obj)
	{
		SQLiteDatabase db=this.getWritableDatabase();
		String id = obj.getId();
		String title = obj.getTitle();
		String doctor = obj.getDoctorName();
		String hospital = obj.getHospitalName();
		String location = obj.getLocation();
		String date = obj.getDateOfVisit();
		String prescription = obj.getPrescription();
		String specification = obj.getSpecification();
		String surgery = obj.getSurgery();
		int fees = obj.getFees();
		int nextDay = obj.getNextVisit_day();
		int nextMonth = obj.getNextVisit_month();
		int nextYear = obj.getNextVisit_year();
		String other = obj.getOtherInfo();
		String contact1 = obj.getContactNo1();
		String contact2 = obj.getContactNo2();
		ContentValues values = new ContentValues();
		ContentValues values2=new ContentValues();
		values.put(HISTORY_ID, id);
		values.put(HISTORY_TITLE, title);
		values.put(HISTORY_DOCTOR, doctor);
		values.put(HISTORY_HOSPITAL, hospital);
		values.put(HISTORY_LOCATION, location);
		values.put(HISTORY_DATEOFVISIT, date);
		values.put(HISTORY_PRESCRIPTION, prescription);
		values.put(HISTORY_SPECIFICATION, specification);
		values.put(HISTORY_SURGERY, surgery);
		values.put(HISTORY_FEES, fees);
		values.put(HISTORY_NEXTDAY, nextDay);
		values.put(HISTORY_NEXTMONTH, nextMonth);
		values.put(HISTORY_NEXTYEAR, nextYear);
		values.put(HISTORY_OTHER, other);
		values.put(HISTORY_CONTACT1, contact1);
		values.put(HISTORY_CONTACT2, contact2);
		
		
		values2.put(APP_ID, id);
		values2.put(APP_TTILE, title);
		values2.put(APP_DATE, nextDay);
		values2.put(APP_MONTH, nextMonth);
		values2.put(APP_YEAR, nextYear);
		
		db.update(TABLE_HISTORY, values,HISTORY_ID+" =?", new String[]{obj.getId()});
		db.update(TABLE_APP, values2, APP_ID+" =?", new String[]{obj.getId()});
		db.close();
	}
	// table appointments
	void insert_app()

	{
		ContentValues values = new ContentValues();
		String title;
		int date, month, year;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cur = db.rawQuery("select * from " + TABLE_HISTORY, null);
		if (cur.moveToFirst()) {
			do {
				title = cur.getString(0);
				date = cur.getInt(9);
				month = cur.getInt(10);
				year = cur.getInt(11);
				values.put(APP_TTILE, title);
				values.put(APP_DATE, date);
				values.put(APP_MONTH, month);
				values.put(APP_YEAR, year);
				db.insert(TABLE_APP, null, values);

			} while (cur.moveToNext());

		}

	}

	void appAdd(ModelApp obj) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		String id=obj.getId();
		String title = obj.getTitle();
		int date = obj.getDate();
		int month = obj.getMonth();
		int year = obj.getYear();
		String time = obj.getTime();
		values.put(APP_ID, id);
		values.put(APP_TTILE, title);
		values.put(APP_DATE, date);
		values.put(APP_MONTH, month);
		values.put(APP_YEAR, year);
		values.put(APP_TIME, time);
		Log.e("shilpi", title + " " + date + " " + month + " " + year + " "
				+ time);
		db.insert(TABLE_APP, null, values);
		db.close();
	}

	public List<ModelApp> getAllApp() {
		SQLiteDatabase db = this.getReadableDatabase();
		List<ModelApp> list = new ArrayList<ModelApp>();
		Cursor cursor;
		cursor = db.rawQuery("select * from " + TABLE_APP + " order by "
				+ APP_YEAR + " , " + APP_MONTH + " , " + APP_DATE, null);
		// cursor =db.query(TABLE_APP, new String[]{APP_TTILE, APP_DATE,
		// APP_MONTH, APP_YEAR, APP_TIME}, selection, selectionArgs, groupBy,
		// having, orderBy, limit)
		if (cursor.moveToFirst()) {
			do {
				String Id=cursor.getString(0);
				String Title = cursor.getString(1);
				int Date = cursor.getInt(2);
				int Month = cursor.getInt(3);
				int Year = cursor.getInt(4);
				String Time = cursor.getString(5);
				ModelApp obj = new ModelApp(Id,Title, Date, Month, Year, Time);
				list.add(obj);
			} while (cursor.moveToNext());

		}
		return list;

	}
	
	void deleteApp(String id)
	{
		SQLiteDatabase db=this.getWritableDatabase();
		db.delete(TABLE_APP, APP_ID+" =?", new String[]{id});
		db.close();
	}
	
	
	ModelApp ViewApp(String id)
	{
		ModelApp obj=null;
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cursor;
		cursor=db.rawQuery("select * from "+TABLE_APP+" where "+APP_ID+" =?", new String[]{id});
		if(cursor.moveToFirst())
		{
			obj=new ModelApp(id, cursor.getString(1), Integer.parseInt(cursor.getString(2)) , Integer.parseInt(cursor.getString(3)), Integer.parseInt(cursor.getString(4)), cursor.getString(5));
			
		}
		return obj;
	}
	
	void AppUpdate(String id,String title,int date,int month,int year,String time)
	{
		SQLiteDatabase db=this.getWritableDatabase();
		ContentValues values=new ContentValues();
		values.put(APP_ID, id);
		values.put(APP_TTILE, title);
		values.put(APP_DATE, (date));
		values.put(APP_MONTH, (month));
		values.put(APP_YEAR, (year));
		values.put(APP_TIME, time);
		db.update(TABLE_APP, values, APP_ID+" =?", new String[]{id});
		db.close();
	}
}
