package com.gangsin.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.gangsin.component.MapParamCollector;
import com.gangsin.component.PublicServiceInterface;
import com.gangsin.service.HomeService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	private final static String MAPPING = "/";
	private final static String ADMINMAPPING = "/admin/";

	@Autowired
	private HomeService HomeService;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = { MAPPING, ADMINMAPPING + "{action}" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView home(MapParamCollector requestMap, ModelAndView modelAndView,
			@PathVariable Optional<String> uri, ModelAndView modelandView) {

		String serviceName = (String) requestMap.get("_serviceName");

		String action = (String) requestMap.get("_action");

		Map<Object, Object> paramMap = requestMap.getMap();

		Object resultMap = new HashMap<String, Object>();

		String viewName = null;

		// divided depending on action value
		if (uri.isPresent()) {
			viewName = "/home/main";
		} else {
			resultMap = HomeService.getListAndSomething(paramMap);
			viewName = "/home/home";
		}

		// if you want other view, you can set up next viewName on Service method
		String nextViewName = (String) ((Map<String, Object>) resultMap).get("nextViewName");
		if (viewName == null) {
			if (nextViewName != null) {
				viewName = nextViewName;
			} else {
				viewName = "/" + serviceName + "/" + action;
			}
		}

		// Resolves Popup Yes or No(ex. URI : /*/*/poopup)
		String popupYn = (String) requestMap.get("_popupYn");

		if (popupYn != null) {
			viewName = viewName + "/" + popupYn;
		}

		modelAndView.setViewName(viewName);

		modelAndView.addObject("paramMap", paramMap);
		modelAndView.addObject("resultMap", resultMap);
		return modelandView;
	}
}
