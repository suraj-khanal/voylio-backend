package com.rest.appvoylio.dto;

import java.sql.Timestamp;

import com.rest.appvoylio.entity.SignupEntity;

public class TaskDTO {
	
	private int tid;
	private String taskTitle;
	private String description;
	private Timestamp dueDate;
	private boolean isDone;
	
	private SignupEntity signupEntity;

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getTaskTitle() {
		return taskTitle;
	}

	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getDueDate() {
		return dueDate;
	}

	public void setDueDate(Timestamp dueDate) {
		this.dueDate = dueDate;
	}

	public boolean isDone() {
		return isDone;
	}

	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}

	public SignupEntity getSignupEntity() {
		return signupEntity;
	}

	public void setSignupEntity(SignupEntity signupEntity) {
		this.signupEntity = signupEntity;
	}

	@Override
	public String toString() {
		return "TaskEntity [tid=" + tid + ", taskTitle=" + taskTitle + ", description=" + description + ", dueDate="
				+ dueDate + ", isDone=" + isDone + ", signupEntity=" + signupEntity + "]";
	}

}
