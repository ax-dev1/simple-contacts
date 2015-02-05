package com.demo.web;

import java.util.Map;

import com.britesnow.snow.web.handler.annotation.WebModelHandler;
import com.britesnow.snow.web.param.annotation.WebModel;
import com.google.inject.Singleton;

@Singleton
public class Home {
	
	@WebModelHandler(startsWith="/")
    public void allPages(@WebModel Map m){
    }

}
