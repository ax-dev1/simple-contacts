package com.demo.dao;

import com.britesnow.snow.web.db.hibernate.HibernateDaoHelper;
import com.demo.entity.Group;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.List;


@Singleton
public class GroupDao implements IDao<Group> {

	@Inject
	private HibernateDaoHelper daoHelper;
	
	@Override
	public Group save(Group group) {
		
		return daoHelper.save(group);
	}
	
	@Override
	public Group get(int id) {
		// TODO Auto-generated method stub
		return daoHelper.get(Group.class,id);
	}
	
	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		daoHelper.delete(Group.class,id);
	}

	@Override
	public Group update(Group group) {
		// TODO Auto-generated method stub
		return daoHelper.update(group);
	}
	
	@Override
	public List<Group> getAll() {
		// TODO Auto-generated method stub
		return (List<Group>) daoHelper.find(0,1000,"from "+Group.class.getSimpleName());
	}

}
