package com.demo.entity;

import javax.persistence.*;


@Entity
@Table(name = "t_group")
public class Group extends BaseEntity {
	private String title;

	public Group(){}

	public Group(String title){
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
