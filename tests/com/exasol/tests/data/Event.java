package com.exasol.tests.data;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import org.hibernate.annotations.Type;


@Entity
@Table( name = "EVENTS" )
public class Event {
	
	private Long id;
	private String title;
	private Date date;
	private int attendees;
	private double price; 
	private int degree;
    private EventDetails details;
    private List<Person> person;
    
	public Event() {
	}

	public Event(String title, Date date, int attendees, double price, int degree) {
		this.title = title;
		this.date = date;
		this.attendees=attendees;
		this.price=price;
		this.degree=degree;
	}

	
	//TABLE -> working
	/*@Id
	@TableGenerator(name="id_gen",table="U_SEQUENCES",pkColumnName="S_ID",valueColumnName="S_NEXTNUM",pkColumnValue="id",allocationSize=1000)
	@GeneratedValue(strategy=GenerationType.TABLE,generator="id_gen")*/

	//IDENTITY -> working
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	
	//INCREMENT -> working
	/*@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")*/
	public Long getId() {
		return id;
	}

	private void setId(Long id) {
		this.id = id;
	}

	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "EVENT_DATE")
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Column(name="EVENT_TITLE")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
	@Column(name="EVENT_ATTENDEES")
	 public int getAttendees() {
		return attendees;
	}

	public void setAttendees(int attendees) {
		this.attendees = attendees;
	}

	@Column(name="EVENT_PRICE")
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Column(name="EVENT_DEGREE")
	public int getDegree() {
		return degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}


	@OneToOne(cascade = CascadeType.ALL)
	@Type(type="com.exasol.testing.EventDetails")
	public EventDetails getDetails() {
		return details;
	}

	public void setDetails(EventDetails details) {
		this.details = details;
	}

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "EVENT_PERSON", joinColumns = { @JoinColumn(name = "id") }, inverseJoinColumns = { @JoinColumn(name = "pid") })
	@Type(type="com.exasol.testing.Person")
	public List<Person> getPerson() {
		return person;
	}

	public void setPerson(List<Person> person) {
		this.person = person;
	}
}