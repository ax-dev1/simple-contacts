package com.demo.hook;


import com.britesnow.snow.web.db.hibernate.HibernateSessionInViewHandler;
import com.britesnow.snow.web.hook.AppPhase;
import com.britesnow.snow.web.hook.annotation.WebApplicationHook;
import com.demo.dao.ContactDao;
import com.demo.dao.GroupDao;
import com.demo.entity.Contact;
import com.demo.entity.Group;
import com.google.inject.Singleton;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class ContactDataHook {

    @WebApplicationHook(phase = AppPhase.INIT)
    public void seedStore(GroupDao groupDao, ContactDao contactDao, HibernateSessionInViewHandler inView) {
        inView.openSessionInView();
        if(groupDao.getAll().size()==0)
        {
            String[] groups = {"Family","Friend","Classmates"};
            for(String title:groups) {
               groupDao.save(new Group(title));
            }
        }
        if(contactDao.getAll().size()==0)
        {
            List<Contact> contactList = new ArrayList<Contact>();
            contactList.add(new Contact("1,2", "Alan", "Xing", "1111111", "xx1 St. xx1 St. xx1 St. xx1 St. xx1 St. xx1 St." ));
            contactList.add(new Contact("1,3", "Amos", "Wong", "22222222", "St2." ));
            contactList.add(new Contact("1", "Richard", "Wells", "3333333333", " St3." ));

            for(Contact c:contactList) {
                contactDao.save(c);
            }
        }


        inView.closeSessionInView();
    }
}
