package com.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_contact")
public class Contact extends BaseEntity {
	private String groupId;
	private String firstName;
	private String lastName;
	private String phone;
	private String address;

	public Contact() {
	}

	public Contact(String groupId, String firstName, String lastName, String phone, String address) {
		this.groupId = groupId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.address = address;
	}

	public String getGroupID() {
		return groupId;
	}
	public void setGroupID(String groupID) {
		this.groupId = groupID;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
