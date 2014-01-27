package reminder;

import android.media.Ringtone;

public class Reminder {

	private float distance;
	private String message;
//	private Ringtone ringtone;
	
	public Reminder() {
		this.distance = 0;
		this.message = "";
	}
	
	public Reminder(float distance) {
		this.distance = distance;
		this.message = "";
	//	this.ringtone = 
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public float getDistance() {
		return distance;
	}
	
	public void setDistance(float distance) {
		this.distance = distance;
	}
/*
	public Ringtone getRingtone() {
		return ringtone;
	}

	public void setRingtone(Ringtone ringtone) {
		this.ringtone = ringtone;
	}
	
	*/
	
}
