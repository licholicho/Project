package user;

import java.util.List;

import location.Location;
import reminder.Reminder;
import task.Task;

public class User {

	private String login;
	private String password;
	private List<Task> taskList;
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public List<Task> getTaskList() {
		return taskList;
	}
	public void setTaskList(List<Task> taskList) {
		this.taskList = taskList;
	}
	
	public void createNewTask(String title, String description, Location location, Reminder reminder) {
		taskList.add(new Task(title, description, location, reminder));
	}
	
	
}
