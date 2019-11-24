package com.gangsin.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gangsin.service.HomeService;

@Component
public class RuntimeClassFactory {

	@Autowired
	private HomeService HomeService;


	// @Override
	public PublicServiceInterface getInstance(String className) {

		if ("home".equals(className)) {
			return HomeService;
		}
		return null;
	}

}
