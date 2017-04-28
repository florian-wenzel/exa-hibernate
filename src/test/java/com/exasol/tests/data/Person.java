package com.exasol.tests.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table( name = "PERSON" )
public class Person {

	
	private Long pid;
	private String name;
	private int age;
	
	
	
	public Person(String name, int age){
		this.name = name;
		this.age = age;
	}

	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	public Long getPid() {
		return pid;
	}

	
	public void setPid(Long pid) {
		this.pid = pid;
	}

	@Column(name="PERSON_NAME")
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	@Column(name="PERSON_AGE")
	public int getAge() {
		return age;
	}

	
	public void setAge(int age) {
		this.age = age;
	}


	
	
	
	
}
