package com.demo.data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.demo.entity.Contact;
import com.demo.entity.Group;

public class DataPool {
	private Map<String,Contact> data_contact = new HashMap();
	private Map<String,Group> data_group = new HashMap();
	private long contactId = 0;
	private long groupId = 0;
	private static DataPool pool = null;
	
	private DataPool()
	{
		this.initData();
	}
	
	private void initData()
	{
		 if (data_contact.isEmpty())
		 {
			 contactId++;
			 Contact contact = new Contact();
			 contact.setId(String.valueOf(contactId) );
			 contact.setFirstName("Alan");
			 contact.setLastName("Xing");
			 contact.setPhone("11111111");
			 contact.setAddress("xx1 St. xx1 St. xx1 St. xx1 St. xx1 St. xx1 St");
			 contact.setGroupID("1,2");
			 data_contact.put(String.valueOf(contactId), contact);
			 
			 contactId++;
			 contact = new Contact();
			 contact.setId(String.valueOf(contactId));
			 contact.setFirstName("Amos");
			 contact.setLastName("Wong");
			 contact.setPhone("22222222");
			 contact.setAddress("xx2 St");
			 contact.setGroupID("2,3");
			 data_contact.put(String.valueOf(contactId), contact);
			 
			 contactId++;
			 contact = new Contact();
			 contact.setId(String.valueOf(contactId));
			 contact.setFirstName("Richard");
			 contact.setLastName("Wells");
			 contact.setPhone("33333333");
			 contact.setAddress("xx3 St.");
			 contact.setGroupID("1,2");
			 data_contact.put(String.valueOf(contactId), contact);
			 
			 contactId++;
			 contact = new Contact();
			 contact.setId(String.valueOf(contactId));
			 contact.setFirstName("Ray");
			 contact.setLastName("Bing");
			 contact.setPhone("44444444");
			 contact.setAddress("xx4 St.");
			 contact.setGroupID("1,3");
			 data_contact.put(String.valueOf(contactId), contact);
		 }
		 
		 if(data_group.isEmpty())
		 {
			 groupId++;
			 Group group = new Group();
			 group.setId(String.valueOf(groupId));
			 group.setTitle("Family");
			 data_group.put(String.valueOf(groupId), group);
			 
			 groupId++;
			 group = new Group();
			 group.setId(String.valueOf(groupId));
			 group.setTitle("Work");
			 data_group.put(String.valueOf(groupId), group);
			 
			 groupId++;
			 group = new Group();
			 group.setId(String.valueOf(groupId));
			 group.setTitle("Friends");
			 data_group.put(String.valueOf(groupId), group);
			 
		 }
	}
	
	public long saveContact(Contact contact)
	{
		this.data_contact.put(String.valueOf(contact.getId()), contact);
		return this.contactId;
	}
	
	public long saveGroup(Group group)
	{
		this.data_group.put(group.getId(), group);
		return this.groupId;
	}
	
	public void updateContact(Contact contact)
	{
		this.data_contact.put(String.valueOf(contact.getId()), contact);
	}
	
	public void updateGroup(Group group)
	{
		this.data_group.put(String.valueOf(group.getId()), group);
	}
	
	public void deleteContact(Contact contact)
	{
		this.data_contact.remove(String.valueOf(contact.getId()));
	}
	
	public void deleteGroup(Group group)
	{
		for(String key : this.data_contact.keySet())
		{
			Contact value = this.data_contact.get(key);
			value.setGroupID(value.getGroupID().replace(","+group.getId(),""));
			value.setGroupID(value.getGroupID().replace(group.getId()+",",""));
			this.data_contact.put(key, value);
		}
		
		
		this.data_group.remove(String.valueOf(group.getId()));
	}
	
	public List<Contact> getAllContact()
	{
		List<Contact> list = new ArrayList();
		for(Contact value : this.data_contact.values())
		{
			list.add(value);
		}
		return list;
		
	}
	
	public List<Group> getAllGroup()
	{
		List<Group> list = new ArrayList();
		for(Group value : this.data_group.values())
		{
			list.add(value);
		}
		return list;
	}
	
	public Contact getContactById(String Id)
	{
		return this.data_contact.get(Id);
	}
	
	public Group getGroupById(String Id)
	{
		return this.data_group.get(Id);
		
	}
	
	public static synchronized DataPool getInstance()
	{
		if(pool == null)
		{
			pool = new DataPool();
		}
		return pool;
	}
}
