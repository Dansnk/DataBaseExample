package cl.telematica.databaseexample;

import java.util.ArrayList;
import java.util.List;

import cl.telematica.databaseexample.adapters.RssAdapter;
import cl.telematica.databaseexample.adapters.RssAdapterTwo;
import cl.telematica.databaseexample.database.DataBaseClass;
import cl.telematica.databaseexample.models.EarthQuakeDataModel;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

public class DetailsActivity extends Activity {
	
	private EarthQuakeDataModel EQModel;
	private List<EarthQuakeDataModel> list;
	private ListView listView;
	
	
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		EarthQuakeDataModel EQModel = new EarthQuakeDataModel();
		list = new ArrayList<EarthQuakeDataModel>();
		DataBaseClass dbInstance = new DataBaseClass(this);
		SQLiteDatabase db = dbInstance.getReadableDatabase();
		
		listView = (ListView) findViewById(R.id.listView2);
		
		Cursor c = db.rawQuery("SELECT * FROM alumnos", null);
		if(c.moveToFirst()){
			do{
				EQModel.title = c.getString(0);
				EQModel.magnitude = c.getString(1);
				EQModel.location = c.getString(2);
				EQModel.depth = c.getString(3);
				EQModel.latitude = c.getString(4);
				EQModel.longitude = c.getString(5);
				EQModel.dateTime = c.getString(6);
				EQModel.dateTime = c.getString(7);
				list.add(EQModel);	
			}while(c.moveToNext());
		}		
		RssAdapterTwo adapter = new RssAdapterTwo(getApplicationContext(), R.string.app_name, list);
		listView.setAdapter(adapter);    
	}
}
