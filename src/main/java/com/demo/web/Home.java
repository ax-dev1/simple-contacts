package com.demo.web;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.britesnow.snow.web.handler.annotation.WebModelHandler;
import com.britesnow.snow.web.param.annotation.PathVar;
import com.britesnow.snow.web.param.annotation.WebModel;
import com.britesnow.snow.web.param.annotation.WebParam;
import com.britesnow.snow.web.param.annotation.WebUser;
import com.britesnow.snow.web.rest.annotation.WebDelete;
import com.britesnow.snow.web.rest.annotation.WebGet;
import com.britesnow.snow.web.rest.annotation.WebPost;
import com.britesnow.snow.web.rest.annotation.WebPut;
import com.demo.data.DataPool;
import com.demo.entity.Contact;
import com.demo.entity.Group;
import com.google.gson.Gson;
import com.google.inject.Singleton;

@Singleton
public class Home {
	
	@WebModelHandler(startsWith="/")
    public void allPages(@WebModel Map m){
    }
	
	@WebGet("/getContacts")
	public String getContacts()
	{
		DataPool pool = DataPool.getInstance();
		Gson gson = new Gson();
		return gson.toJson(pool.getAllContact());
		
	}
	
	@WebGet("/getGroups")
	public String getGroups()
	{
		DataPool pool = DataPool.getInstance();
		Gson gson = new Gson();
		return gson.toJson(pool.getAllGroup());
		
	}
	
	
	@WebPost("/group")
	public void updateGroup(@WebParam("title") String title,@WebParam("id") String id)
	{
		
			DataPool pool = DataPool.getInstance();
			Group group = new Group();
			group.setTitle(title);
			group.setId(id);
		    pool.updateGroup(group);
	}
	
	@WebPut("/group")
	public void createGroups(@WebParam("title") String title,@WebParam("id") String id)
	{
		DataPool pool = DataPool.getInstance();
		Group group = new Group();
		group.setId(id);
		group.setTitle(title);
		pool.saveGroup(group);
	}
	
	@WebDelete("/group/{id}")
	public void deleteGroups(HttpServletRequest req ,@PathVar("id") String id)
	{
		DataPool pool = DataPool.getInstance();
		Group group = new Group();
		group.setId(id);
		pool.deleteGroup(group);
	}
	
	@WebPost("/contact")
	public void updateContact(@WebParam("address") String address,@WebParam("id") String id,
			@WebParam("firstName") String firstName,@WebParam("lastName") String lastName,@WebParam("groupId") String groupId,
			@WebParam("phone") String phone)
	{
		
			Contact contact = new Contact();
			contact.setId(id);
			contact.setAddress(address);
			contact.setFirstName(firstName);
			contact.setGroupID(groupId);
			contact.setLastName(lastName);
			contact.setPhone(phone);
			DataPool pool = DataPool.getInstance();
			pool.updateContact(contact);
	}
	
	
	@WebPut("/contact")
	public void createContact(@WebParam("address") String address,@WebParam("id") String id,
			@WebParam("firstName") String firstName,@WebParam("lastName") String lastName,@WebParam("groupId") String groupId,
			@WebParam("phone") String phone)
	{
		DataPool pool = DataPool.getInstance();
		Contact contact = new Contact();
		contact.setId(id);
		contact.setAddress(address);
		contact.setFirstName(firstName);
		contact.setGroupID(groupId);
		contact.setLastName(lastName);
		contact.setPhone(phone);
		pool.saveContact(contact);
	}
	
	
	@WebDelete("/contact/{id}")
	public void deleteContact(@PathVar("id") String id)
	{
		DataPool pool = DataPool.getInstance();
		Contact contact = new Contact();
		contact.setId(id);
		pool.deleteContact(contact);
	}
	
	
	@WebModelHandler(startsWith="/edit")
	public void editPage(){
		
	}
	
	@WebPost("/post_edit")
	public void editPage(HttpServletRequest req){
		System.out.println(req.getParameter("firstName"));
	}
}
