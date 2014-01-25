package com.example.project;

import java.util.List;

import task.Task;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;
import database.TaskDbFacade;
import database.TaskDbHelper;

public class OngoingActivity extends Activity {

	private ListView taskLv;
	private List<Task> tasks;
	private TaskDbHelper dbOpenHelper = null;
    private TaskDbFacade dbHelper = null;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ongoing);
		
		setupDbEnv();
		/*tasks = new ArrayList<Task>();
		tasks.add(new Task("title1", "desc1"));
		tasks.add(new Task("title2", "desc2"));
		tasks.add(new Task("title3", "desc3"));
	*/
		if (dbHelper == null || dbOpenHelper == null)
			Log.i("top", "zle ");
		tasks = dbHelper.listAll();
		Log.i("top", ""+tasks.size());
		taskLv = (ListView) findViewById(R.id.ongoing_menu);
		taskLv.setAdapter(new MenuAdapter(this, tasks));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ongoing, menu);
		return true;
	}

	  private void setupDbEnv() {
		  Log.i("topics.database","setup!");
	        if (dbOpenHelper == null) {
	            dbOpenHelper = new TaskDbHelper(this);
	        } 
	        if (dbHelper == null) {
	            dbHelper = new TaskDbFacade(dbOpenHelper.getWritableDatabase());
	        }
	    }
	
}
