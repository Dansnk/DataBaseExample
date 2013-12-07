package cl.telematica.databaseexample;

import java.util.ArrayList;
import java.util.List;

import cl.telematica.databaseexample.adapters.RssAdapterTwo;
import cl.telematica.databaseexample.database.DataBaseClass;
import cl.telematica.databaseexample.models.EarthQuakeDataModel;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

public class DetailsActivity extends Activity {
	
	private EarthQuakeDataModel EQM;
	private List<EarthQuakeDataModel> list;
	private ListView listView;
	
	
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		EQM = new EarthQuakeDataModel();
		list = new ArrayList<EarthQuakeDataModel>();
		DataBaseClass dbInstance = new DataBaseClass(this);
		SQLiteDatabase db = dbInstance.getReadableDatabase();
		
		listView = (ListView) findViewById(R.id.listView2);
		
		Cursor c = db.rawQuery("SELECT * FROM alumnos", null);
		int i = 0;
		if(c.moveToFirst()){
			do{
				EQM.title = c.getString(1);
				EQM.magnitude = c.getString(2);
				EQM.location = c.getString(3);
				EQM.depth = c.getString(4);
				EQM.latitude = c.getString(5);
				EQM.longitude = c.getString(6);
				EQM.dateTime = c.getString(7);
				EQM.link = c.getString(8);
				list.add(EQM);
				EQM = new EarthQuakeDataModel();
				i++;
			}while(c.moveToNext() && (i<8));
		}		
		RssAdapterTwo adapter = new RssAdapterTwo(getApplicationContext(), R.string.app_name, list);
		listView.setAdapter(adapter);    
	}
}
