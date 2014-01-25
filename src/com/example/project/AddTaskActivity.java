package com.example.project;

import task.Task;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import database.TaskDbFacade;
import database.TaskDbHelper;

public class AddTaskActivity extends Activity {

	private EditText title;
	private EditText description;
	private EditText reminderDistance;
	private TaskDbHelper dbOpenHelper = null;
    private TaskDbFacade dbHelper = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_task);
		setupDbEnv();

		title = (EditText) findViewById(R.id.title_et);
		description = (EditText) findViewById(R.id.desc_et);
		reminderDistance = (EditText) findViewById(R.id.rem_et);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_task, menu);
		return true;
	}

	 @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	    	switch (item.getItemId()) {
			case R.id.action_save:
				saveTask();
				goBack();
				break;
			case R.id.action_cancel:
				goBack();
				break;
	    }
	    	return super.onOptionsItemSelected(item);
	    }
	
	 public void saveTask(){
		 Log.i("topics","save");
		 Task task = new Task();
		 task.setTitle(title.getText().toString());
		 task.setDescription(description.getText().toString());
		 task.setReminder(Float.valueOf(reminderDistance.getText().toString()));
		 Log.i("topics","save1");
		 dbHelper.insert(task);
		 Log.i("topics","save2");
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
	  
	  private void goBack() {
			Intent i = new Intent(this, MainActivity.class);
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        	startActivity(i);  
	  }
}
