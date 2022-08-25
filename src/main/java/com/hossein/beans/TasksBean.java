package com.hossein.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.hossein.models.Tasks;

@ManagedBean
@ViewScoped
public class TasksBean {
	
	@PersistenceContext(unitName = "jsf-unit")
	private EntityManager em;
	
	@Resource
	private List<Tasks> tasks;
	
	private String taskBody;
	
	private Long taskId;
	
	@PostConstruct
	public void init() {
		this.getTasks();
	}
	
	public List<Tasks> getAllTasks() {
		Session session = this.em.unwrap(Session.class);
		
		Query<Tasks> query = session.createQuery("From Tasks");
		
		return query.list();
	}
	
	public void addTask() {
		Tasks task = new Tasks(this.getTaskBody(), false);
		this.em.persist(task);
	}
	
	public void deleteTask() {
		Tasks task = this.em.find(Tasks.class, taskId);
		this.em.remove(task);
	}

	public List<Tasks> getTasks() {
		return this.getAllTasks();
	}

	public void setTasks(List<Tasks> tasks) {
		this.tasks = tasks;
	}

	public String getTaskBody() {
		return taskBody;
	}

	public void setTaskBody(String taskBody) {
		this.taskBody = taskBody;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

}
