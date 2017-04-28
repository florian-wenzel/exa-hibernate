package com.exasol.tests.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table( name = "DETAILS" )
public class EventDetails {

	private Long did;
	private String street;
	private boolean valid;
	
	
	public EventDetails(String street, boolean valid){
		this.street=street;
		this.valid=valid;
	}


	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment")
	public Long getId() {
		return did;
	}


	public void setId(Long id) {
		this.did = id;
	}

	@Column(name="DETAIL_STREET")
	public String getStreet() {
		return street;
	}


	public void setStreet(String street) {
		this.street = street;
	}


	public boolean isValid() {
		return valid;
	}

	@Column(name="DETAILS_VALID")
	public void setValid(boolean valid) {
		this.valid = valid;
	}
	
	
}
