package com.sk.addressbook.bean;

import java.io.Serializable;

public class SKContact implements Serializable {
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	private String address;

	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Override
	public String toString() {
		return "SKContact [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", phone=" + phone
				+ ", address=" + address + "]";
	}
	

		
	
	
}
