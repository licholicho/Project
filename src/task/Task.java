package task;

import location.Location;
import reminder.Reminder;

public class Task {

	private long id;
	private String title;
	private String description;
	private Location location;
	private Reminder reminder;
	private boolean done;
	
	
	public Task(String title, String description, Location location,
			Reminder reminder) {
		super();
		this.title = title;
		this.description = description;
		this.location = location;
		this.reminder = reminder;
		this.done = false;
	}

	
	
	public Task(String title, String description) {
		super();
		this.title = title;
		this.description = description;
		this.done = false;
		this.reminder = new Reminder();
		this.location = new Location();
	}

	public Task(){
		this.title = "";
		this.description = "";
		this.done = false;
		this.reminder = new Reminder();
		this.location = new Location();
	}

	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public float getReminder() {
		return reminder.getDistance();
	}
	
	public void setReminder(float reminder) {
		this.reminder.setDistance(reminder);
	}
	
	public Location getLocation() {
		return location;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}
	
	public void setDone(int d) {
		if (d == 1) this.done = true;
		else this.done = false;
	}
	
	public int done() {
		return done ? 1 : 0;
	}
	
	
}
