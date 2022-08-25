package com.hossein.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="TASKS")
public class Tasks {

	@Id
	@SequenceGenerator(
				name = "seq_tasks",
				sequenceName = "seq_tasks",
				allocationSize = 1
			)
	@GeneratedValue(
				strategy = GenerationType.SEQUENCE,
				generator = "seq_tasks"
			)
	private Long id;
	
	@Column(name = "TASK_BODY")
	private String taskBody;
	
	@Column(name = "COMPLETED")
	private Boolean completed;

	public Tasks(String taskBody, Boolean completed) {
		this.taskBody = taskBody;
		this.completed = completed;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTaskBody() {
		return taskBody;
	}

	public void setTaskBody(String taskBody) {
		this.taskBody = taskBody;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}
	
}
