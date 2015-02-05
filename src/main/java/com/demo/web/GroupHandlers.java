package com.demo.web;


import com.britesnow.snow.web.param.annotation.PathVar;
import com.britesnow.snow.web.param.annotation.WebParam;
import com.britesnow.snow.web.rest.annotation.WebDelete;
import com.britesnow.snow.web.rest.annotation.WebGet;
import com.britesnow.snow.web.rest.annotation.WebPost;
import com.britesnow.snow.web.rest.annotation.WebPut;
import com.demo.dao.GroupDao;
import com.demo.entity.Group;
import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Singleton
public class GroupHandlers {

    @Inject
    private GroupDao groupDao;

    @WebGet("/group/list")
    public String list()
    {
        List<Group> group = groupDao.getAll();
        Gson gson = new Gson();
        return gson.toJson(group);
    }

    @WebPost("/group")
	public void updateGroup(@WebParam("title") String title,@WebParam("id") String id)
	{
			Group group = new Group();
			group.setTitle(title);
            group.setId(Integer.parseInt(id));
            groupDao.update(group);
	}

    @WebPut("/group")
	public void createGroup(@WebParam("title") String title)
	{
		Group group = new Group();
		group.setTitle(title);
		groupDao.save(group);
	}

    @WebDelete("/group/{id}")
	public void deleteGroup(HttpServletRequest req ,@PathVar("id") String id)
	{
		groupDao.delete(Integer.valueOf(id) );
	}

	@WebGet("/group")
	public String getGroup(@WebParam("id") String id)
	{
		Gson gson = new Gson();
		Group group = groupDao.get(Integer.valueOf(id));
		return gson.toJson(group);
	}

}
