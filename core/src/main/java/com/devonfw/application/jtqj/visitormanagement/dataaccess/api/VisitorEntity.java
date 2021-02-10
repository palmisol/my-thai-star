package com.devonfw.application.jtqj.visitormanagement.dataaccess.api;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Visitor")
public class VisitorEntity {
	  
	private String username;

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the acceptedCommercial
	 */
	public Boolean getAcceptedCommercial() {
		return acceptedCommercial;
	}

	/**
	 * @param acceptedCommercial the acceptedCommercial to set
	 */
	public void setAcceptedCommercial(Boolean acceptedCommercial) {
		this.acceptedCommercial = acceptedCommercial;
	}

	/**
	 * @return the acceptedTerms
	 */
	public Boolean getAcceptedTerms() {
		return acceptedTerms;
	}

	/**
	 * @param acceptedTerms the acceptedTerms to set
	 */
	public void setAcceptedTerms(Boolean acceptedTerms) {
		this.acceptedTerms = acceptedTerms;
	}

	/**
	 * @return the userType
	 */
	public Boolean getUserType() {
		return userType;
	}

	/**
	 * @param userType the userType to set
	 */
	public void setUserType(Boolean userType) {
		this.userType = userType;
	}

	private String name;

	private String phoneNumber;

	private String password;

	private Boolean acceptedCommercial;

	private Boolean acceptedTerms;

	private Boolean userType;
}
