package com.gangsin.component;

import java.security.Principal;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.HandlerMapping;

import com.gangsin.security.MemberInfo;
import com.gangsin.util.CommonUtil;
import com.gangsin.util.FileUtil;
import com.google.gson.Gson;


/**
 * Controller 클래스가 로드되기 전 파라미터 값에 따른 파일 업로드를 수행하기 위한 클래스.
 * 
 * @param <E>
 */
public class CustomizeMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Autowired
	private FileUtil fileUtil;

	@Autowired
	private CommonUtil commonUtil;

	@Override
	public boolean supportsParameter(MethodParameter methodParameter) {
		boolean resultBoolean = false;
		if(MapParamCollector.class.isAssignableFrom(methodParameter.getParameterType()) || 
				Principal.class.isAssignableFrom(methodParameter.getParameterType())) {
			resultBoolean = true;
		}

		return resultBoolean;
	}

	@Override
	public Object resolveArgument(MethodParameter methodParameter,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {
		Class<?> clazz = methodParameter.getParameterType();
		String paramName = methodParameter.getParameterName();

    	MapParamCollector requestMap = new MapParamCollector();
//		HttpServletRequest request = webRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletRequest request = (HttpServletRequest)webRequest.getNativeRequest();

        if (clazz.equals(MapParamCollector.class)) {
            Enumeration<?> enumeration = request.getParameterNames();

            while (enumeration.hasMoreElements()) {
                String key = (String)enumeration.nextElement();
                String[] values = request.getParameterValues(key);
                if (values != null && values[0].length() > 0) {
                	if("jsonObject".equals(key)) {
                		Gson gson = new Gson();
                		Object obj = gson.fromJson(values[0], List.class);
                        requestMap.put(key, obj);
                	} else {
                        requestMap.put(key, (values.length > 1) ? values : values[0]);
                	}
                }
            }

			if (request instanceof MultipartHttpServletRequest) {
				MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
				Iterator<String> fileList = multipartRequest.getFileNames();
				if(fileList.hasNext()) {
					requestMap.put("attachFileList", fileUtil.setMultipartList(multipartRequest));
				}
			}
			
        }

        // URI Tokenizer
        requestMap.putMap((Map) commonUtil.getURITokenizer(request));
        
        Principal principal = request.getUserPrincipal();
		if (principal != null) {
			MemberInfo currentUser = (MemberInfo) ((Authentication) principal).getPrincipal();
			if(requestMap.get("GRANDCOURSE_SEQ") != null) {
				currentUser.setGrandCourseSeq((String) requestMap.get("GRANDCOURSE_SEQ"));
			}
			requestMap.put("SESSION_MEMBER_SEQ", currentUser.getMemberSeq());
			requestMap.put("SESSION_ORGANIZATION_SEQ", currentUser.getOrganizationSeq());
			requestMap.put("SESSION_GRANDCOURSE_SEQ", currentUser.getGrandCourseSeq());
		}
		
        return requestMap;
//        return WebArgumentResolver.UNRESOLVED;
	}
	
}
