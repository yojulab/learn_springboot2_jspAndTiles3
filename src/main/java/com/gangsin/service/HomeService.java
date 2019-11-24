package com.gangsin.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gangsin.component.PublicServiceInterface;
import com.gangsin.dao.ShareDao;
import com.gangsin.util.CommonUtil;
import com.gangsin.util.Pagination;

@Service
public class HomeService implements PublicServiceInterface{

	@Autowired
	private ShareDao dao;
	
	@Autowired
	private CommonUtil commonUtil;
	
//	@Autowired
//	private CommoncodeService commonCodeService;

	// @Autowired
	// private OrganizationCourseService OrganizationCourseService;

	// @Autowired
	// private LectureMemberService LectureMemberService;

	@Override
	public Object getOtherAndSomething(Object dataMap) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.putAll((Map) dataMap);
		Map resultObject = new HashMap<String, Object>();
		
		resultObject = (Map) this.getFormWithRelatedObject(paramMap);
		
		return resultObject;
	}

	public Object getForm(Object dataMap) {
		Map<String, Object> resultObject = new HashMap<String, Object>();
		
		return resultObject;
	}

	public Object getFormWithRelatedObject(Object dataMap) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.putAll((Map) dataMap);
		Map resultObject = new HashMap<String, Object>();
		
//		paramMap.put("PARENT_COMMONCODE_ID", "SYSTEM_CODE_YN");
//		resultObject.put("systemCodeYN", commonCodeService.getList(paramMap));
		
		return resultObject;
	}

	@Override
	public Object getFormAndSomething(Object dataMap) {
		Object resultObject = this.getFormWithRelatedObject(dataMap);
		
		return resultObject;
	}

	public Object getOne(Object dataMap) {
		String sqlMapId = "Home.selectByPrimaryKey";
		
		Object resultObject = dao.getObject(sqlMapId, dataMap);
		
		return resultObject;
	}

	public Object getOneWithRelatedObject(Object dataMap) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.putAll((Map) dataMap);
		
		Object resultObject = this.getOne(paramMap);
		
		return resultObject;
	}

	@Override
	public Object getOneAndSomething(Object dataMap) {
		Map resultObject = new HashMap<String, Object>();
		
		resultObject = (Map) this.getOneWithRelatedObject(dataMap);

		// put nextViewName
		((Map) resultObject).put("nextViewName", "/home/edit");

		return resultObject;
	}

	public Object getTotalCount(Object dataMap) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.putAll((Map) dataMap);

		String sqlMapId = "Home.countByExample";

		paramMap.put("totalCount", dao.getObject(sqlMapId, dataMap));

		Pagination pagination = new Pagination(paramMap);

		return pagination;
	}

	public Object getList(Object dataMap) {
		
		String sqlMapId = "Home.selectByExample";
		
		Object resultObject = dao.getList(sqlMapId, dataMap);

		return resultObject;
	}

//	As Related Other Service
	public Object getListWithRelatedObject(Object dataMap) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.putAll((Map) dataMap);

		Map resultObject = new HashMap<String, Object>();

		resultObject.put("resultListOrganizationCourse", this.getList(paramMap));

		return resultObject;
	}

	@Override
	public Object getListAndSomething(Object dataMap) {
		Object resultObject = this.getListWithRelatedObject(dataMap);
		
		return resultObject;
	}

	public Object saveObject(Object dataMap) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.putAll((Map) dataMap);

		String sqlMapId = "Home.insert";
		Object resultKey = dao.saveObject(sqlMapId, paramMap);
		
		return resultKey;
	}

	public Object saveObjectWithRelatedObject(Object dataMap) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.putAll((Map) dataMap);

		paramMap.put("TYPE", "LECTURE_ATTEND_TYPE_LISTENER");
		paramMap.put("KIND", "LECTURE_ATTEND_KIND_APPLY");
		paramMap.put("STATUS", "AFTER_LECTURE_STATUS_TYPE_LISTENER");
		paramMap.put("MEMBER_SEQ", paramMap.get("SESSION_MEMBER_SEQ"));
		paramMap.put("USE_YN", "USE_YN_NO");
		// Object resultKey = LectureMemberService.saveObject(paramMap);
		
		// return resultKey;
		return null;
	}

	@Override
	public Object saveObjectAndSomething(Object dataMap) {
		Object resultKey = this.saveObjectWithRelatedObject(dataMap);
		
		Object resultObject = this.getListWithRelatedObject(dataMap);

		// put nextViewName
		((Map) resultObject).put("nextViewName", "/home/home");

		return resultObject;
	}

	public Object modifyObject(Object dataMap) {
		String sqlMapId = "Home.updateByPrimaryKeySelective";

		Object resultKey = dao.saveObject(sqlMapId, dataMap);

		return resultKey;
	}

	public Object modifyObjectWithRelatedObject(Object dataMap) {
		
		Object resultKey = this.modifyObject(dataMap);

		return resultKey;
	}

	@Override
	public Object modifyObjectAndSomething(Object dataMap) {
		Object resultKey = this.modifyObjectWithRelatedObject(dataMap);
		
		Object resultObject = this.getListWithRelatedObject(dataMap);
		
		return resultObject;
	}

	public Object deleteObject(Object dataMap) {

		String sqlMapId = "Home.deleteByPrimaryKey";

		Object resultKey = dao.deleteObject(sqlMapId, dataMap);

		return resultKey;
	}

	public Object deleteObjectWithRelatedObject(Object dataMap) {
		
		Object resultKey = this.deleteObject(dataMap);
		
		return resultKey;
	}

	@Override
	public Object deleteObjectAndSomething(Object dataMap) {
		Object resultKey = this.deleteObjectWithRelatedObject(dataMap);
		
		Object resultObject = this.getListWithRelatedObject(dataMap);
		
		return resultObject;
	}
}
