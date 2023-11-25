package com.rest.appvoylio.entity;

import java.sql.Timestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="task_tbl")
public class TaskEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="task_id")
	private int tid;
	private String taskTitle;
	private String description;
	private Timestamp dueDate;
	private boolean isDone;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name="username", unique = false, nullable =false)
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
