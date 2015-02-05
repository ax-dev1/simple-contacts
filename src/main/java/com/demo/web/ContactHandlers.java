package com.demo.web;

import com.britesnow.snow.web.param.annotation.PathVar;
import com.britesnow.snow.web.param.annotation.WebParam;
import com.britesnow.snow.web.rest.annotation.WebDelete;
import com.britesnow.snow.web.rest.annotation.WebGet;
import com.britesnow.snow.web.rest.annotation.WebPost;
import com.britesnow.snow.web.rest.annotation.WebPut;
import com.demo.dao.ContactDao;
import com.demo.dao.GroupDao;
import com.demo.entity.Contact;
import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Singleton
public class ContactHandlers {
    private static Logger log = LoggerFactory.getLogger(ContactHandlers.class);
    @Inject
    private ContactDao contactDao;

    @Inject
    private GroupDao groupDao;

    @WebGet("/contact/list")
    public String list()
    {

        List<Contact> contacts = contactDao.getAll();
        Gson gson = new Gson();
        return gson.toJson(contacts);
    }

    @WebPost("/contact")
	public void updateContact(@WebParam("address") String address,@WebParam("id") String id,
			@WebParam("firstName") String firstName,@WebParam("lastName") String lastName,@WebParam("groupId") String groupId,
			@WebParam("phone") String phone)
	{

			Contact contact = new Contact();
			contact.setAddress(address);
			contact.setFirstName(firstName);
			contact.setGroupID(groupId);
			contact.setLastName(lastName);
			contact.setPhone(phone);
            contact.setId(Integer.valueOf(id));
            contactDao.update(contact);
	}


    @WebPut("/contact")
	public void createContact(@WebParam("address") String address,@WebParam("id") String id,
			@WebParam("firstName") String firstName,@WebParam("lastName") String lastName,@WebParam("groupId") String groupId,
			@WebParam("phone") String phone)
	{
		Contact contact = new Contact();
		contact.setAddress(address);
		contact.setFirstName(firstName);
		contact.setGroupID(groupId);
		contact.setLastName(lastName);
		contact.setPhone(phone);
        contactDao.save(contact);
	}

    @WebDelete("/contact/{id}")
	public void deleteContact(@PathVar("id") String id)
	{
	    contactDao.delete(Integer.valueOf(id));
	}

	@WebGet("/contact")
	public String getContact(@WebParam("id") String id)
	{
		Gson gson = new Gson();
		Contact contact = contactDao.get(Integer.valueOf(id));
		return gson.toJson(contact);
	}
}
