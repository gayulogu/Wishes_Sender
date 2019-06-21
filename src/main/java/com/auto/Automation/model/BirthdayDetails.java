package com.auto.Automation.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Entity
@Table(name = "birthday_details")
@EntityListeners(EnableJpaAuditing.class)
public class BirthdayDetails implements Serializable {
	
	/**
	 * default version id
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "employee_id")
	int employeeId;

	String name;
	
	@Column(name = "birthday_date")
	String birthdayDate;
	
	@Column(name = "email_id")
	String emailId;
	
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthdayDate() {
		return birthdayDate;
	}
	public void setBirthdayDate(String birthdayDate) {
		this.birthdayDate = birthdayDate;
	}
	
	
	@Override
	public String toString() {
		return "BirthdayDetails [employeeId=" + employeeId + ", name=" + name + ", birthdayDate=" + birthdayDate + "]";
	}
}
