package com.hossein.beans;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.hossein.models.Tasks;

@ManagedBean
@ViewScoped
public class TasksBean {
	
//	@PersistenceContext(unitName = "jsf-unit")
	private EntityManager em = Persistence.createEntityManagerFactory("jsf-unit").createEntityManager();
	
	private List<Tasks> tasks;
	
	private String taskBody;
	
	@PostConstruct
	public void init() {
	}
	
	public List<Tasks> getAllTasks() {
		Session session = this.em.unwrap(Session.class);
		
		Query<Tasks> query = session.createQuery("From Tasks");
		
		return query.list();
	}
	
	@Transactional
	public String addTask() {
		Tasks task = new Tasks(this.getTaskBody(), false);
		this.em.getTransaction().begin();
		this.em.persist(task);
		this.em.getTransaction().commit();
		this.setTasks(this.getAllTasks());
		this.setTaskBody(null);
		return null;
	}
	
	@Transactional
	public String deleteTask(Tasks task) {
		this.em.getTransaction().begin();
		this.em.remove(task);
		this.em.getTransaction().commit();
		this.setTasks(this.getAllTasks());
		this.setTaskBody(null);
		return null;
	}
	
	public String editTask(Tasks task) {
		this.deleteTask(task);
		this.setTaskBody(task.getTaskBody());
		return null;
	}
	
	@Transactional
	public String completeTask(Tasks task) {
		System.out.println("Hello Bitch");
		this.em.getTransaction().begin();
		task.setCompleted(!task.getCompleted());
		this.em.merge(task);
		this.em.getTransaction().commit();
		return null;
	}

	public List<Tasks> getTasks() {
		return this.tasks;
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

//	public EntityManager getEm() {
//		EntityManagerFactory factory = Persistence.createEntityManagerFactory("jsf-unit");
//		return factory.createEntityManager();
//	}
//
//	public void setEm(EntityManager em) {
//		this.em = em;
//	}

}
