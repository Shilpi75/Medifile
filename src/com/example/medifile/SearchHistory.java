package com.example.medifile;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.Toast;

public class SearchHistory extends ActionBarActivity implements OnClickListener {
Toolbar toolbar;
AutoCompleteTextView byTitle,byDoc;
ImageButton search1,search2;
List<String> titleList,docList;
Databasehandler handler;
ArrayAdapter<String> adapter1,adapter2;
String id;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_searchhistory);
		toolbar=(Toolbar)findViewById(R.id.actionbar);
		byTitle=(AutoCompleteTextView)findViewById(R.id.byTitle);
		byDoc=(AutoCompleteTextView)findViewById(R.id.byDoc);
		search1=(ImageButton)findViewById(R.id.imageButton1);
		search2=(ImageButton)findViewById(R.id.imageButton2);
		
		//actionbar
		toolbar.setTitle("Search History");
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		try{
		handler=new Databasehandler(this);
		
		titleList =handler.titleList();
		docList=handler.docList();
		adapter1=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, titleList);
		adapter2=new  ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, docList);
		byTitle.setAdapter(adapter1);
		byDoc.setAdapter(adapter2);
		}
		catch(Exception e)
		{
			Toast.makeText(this,"no history added", 0).show();
		}
		
		search1.setOnClickListener(this);
		search2.setOnClickListener(this);
		
		byTitle.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					final int position, long id) {
				// TODO Auto-generated method stub
			String ID=handler.searchTitle(parent.getItemAtPosition(position).toString());
						
				Intent in=new Intent(SearchHistory.this,ViewHistory.class);
				in.putExtra("id", ID);
				startActivity(in);
			}
		});
		
		byDoc.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					final int position, long id) {
				// TODO Auto-generated method stub
			String ID=handler.searchDoc(parent.getItemAtPosition(position).toString());
						
				Intent in=new Intent(SearchHistory.this,ViewHistory.class);
				in.putExtra("id", ID);
				startActivity(in);
			}
		});
		
		
		
		
		
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		//search1
		case R.id.imageButton1:
			id=handler.searchTitle(byTitle.getText().toString());
			if(id=="null")
				CustomToast.printToast(this,"Sorry!! No Data Found",0,R.drawable.ic_error);
//				Toast.makeText(this, "no data found", 0).show();
			else
			{
			Intent in=new Intent(SearchHistory.this,ViewHistory.class);
			in.putExtra("id", id);
			startActivity(in);
			}
			break;
		case R.id.imageButton2:
			id=handler.searchDoc(byDoc.getText().toString());
			if(id=="null")
				CustomToast.printToast(this,"Sorry!! No Data Found",0,R.drawable.ic_error);
			else
			{
			Intent in2=new Intent(SearchHistory.this,ViewHistory.class);
			in2.putExtra("id", id);
			startActivity(in2);
			}
			break;
			
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.search_history, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		if(id==android.R.id.home)
			onBackPressed();
		return super.onOptionsItemSelected(item);
	}

	
}
