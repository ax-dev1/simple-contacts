package com.demo;

import com.britesnow.snow.util.PackageScanner;
import com.britesnow.snow.web.auth.AuthRequest;
import com.britesnow.snow.web.binding.EntityClasses;
import com.demo.entity.BaseEntity;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class AppConfig extends AbstractModule {
	    @Override
	    protected void configure() {
	    }
	@Singleton
	@Provides
	@EntityClasses
	public Class[] provideEntityClasses() {
		try {
			return new PackageScanner(BaseEntity.class.getPackage().getName()).findAnnotatedClasses(javax.persistence.Entity.class);
		} catch (Throwable e) {
			throw new RuntimeException("Cannot get all the entity class: " + e.getMessage());
		}
	}
	}
