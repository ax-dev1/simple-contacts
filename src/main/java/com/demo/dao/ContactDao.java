package com.demo.dao;


import com.britesnow.snow.web.db.hibernate.HibernateDaoHelper;
import com.demo.entity.Contact;
import com.google.inject.Inject;

import java.util.List;

public class ContactDao implements IDao<Contact> {

    @Inject
    private HibernateDaoHelper daoHelper;

    @Override
    public Contact save(Contact contact) {
        return daoHelper.save(contact);
    }

    @Override
    public Contact get(int id) {
        return daoHelper.get(Contact.class, id);
    }

    @Override
    public void delete(int id) {
        daoHelper.delete(Contact.class,id);
    }

    @Override
    public Contact update(Contact contact) {
        return daoHelper.update(contact);
    }

    @Override
    public List<Contact> getAll() {
        return (List<Contact>)daoHelper.find(0,1000,"from "+Contact.class.getSimpleName());
    }
}
