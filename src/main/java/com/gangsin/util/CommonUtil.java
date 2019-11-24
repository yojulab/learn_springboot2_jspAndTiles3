package com.gangsin.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.HandlerMapping;

@Component
public class CommonUtil {
	/**
	 * Unique sequence Key 부여  
	 * @param 
	 * @return String 
	 */
    public String getUniqueSequence(String MapSeduence) {
		String uniqueSequence = MapSeduence;
		if(uniqueSequence == null || uniqueSequence.length() <= 0){
			uniqueSequence = this.getUniqueSequence();
		}

		return uniqueSequence;
    }

    /**
	 * Unique sequence Key 생성 
	 * @param 
	 * @return String 
	 */
    public String getUniqueSequence() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-", "");
    }
    
	/**
	 * DB sequence Key List 생성 
	 * @param int cnt
	 * @return List 
	 */
	public List<String> makeSequenceList(int cnt) {
		List<String> result = new ArrayList<String>();
		UUID uuid;
		for(int i=0; i<= cnt; i++){
			uuid = UUID.randomUUID();
			result.add(uuid.toString().replaceAll("-", "")+i);
		}
		
		return result ;
	}
	
	/**
	 * PasswordEncoderGenerator 
	 * @param 
	 * @return String 
	 */
    public String PasswordEncoderGenerator(String password) {
    	String hashedPassword = "";
    	if(password != null){
        	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    		hashedPassword = passwordEncoder.encode(password);
    	}
		return hashedPassword;
    }

	/**
	 * PasswordDecoderGenerator 
	 * @param 
	 * @return String 
	 */
    public String PasswordDecoderGenerator(String password) {
    	String hashedPassword = "";
    	if(password != null){
    		BCryptPasswordEncoder passwordDecoder = new BCryptPasswordEncoder();
    		hashedPassword = passwordDecoder.encode(password);
    	}
		return hashedPassword;
    }
    
    public String workingPhysicalDirectory(MultipartHttpServletRequest multipartRequest) {
//		System.getProperty( "catalina.base" ) 	//C:\sts-bundle\pivotal-tc-server-developer-3.2.9.RELEASE\base-instance

    	//    	String fullPath = this.getClass().getClassLoader().getResource("").getPath();
//		String fullPath = URLDecoder.decode(path, "UTF-8");
//		String pathArr[] = fullPath.split("/WEB-INF/classes/");
//    	String physicalDirectory = pathArr[0] + "";

    	String addRealPath = "/resources/uploads/";
    	String physicalDirectory = multipartRequest.getSession().getServletContext().getRealPath(addRealPath);
		
//    	String physicalDirectory = "C:\\Users\\student\\git\\Lecture_SpringFramework\\src\\main\\webapp\\resources\\uploads\\";
		return physicalDirectory;
    }
    
    // URI Tokenizer
	public Object getURITokenizer(HttpServletRequest request) {
		HashMap<String,String> resultMap = new HashMap<String, String>();
        String fullURI = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
        resultMap.put("fullURI", fullURI);
        
        StringTokenizer URITokenizer = new StringTokenizer(fullURI, "/"); 
        List<String> URIDeeps = Arrays.asList("_adminYn", "_serviceName", "_action", "_popupYn");
        
        int indexCnt = 0;
        String deeps = null;
        while (URITokenizer.hasMoreTokens()) {
        	deeps = URITokenizer.nextToken();
        	if(!"admin".equals(deeps) && !"ws".equals(deeps) && indexCnt == 0) {
            	indexCnt++;
        	}
        	resultMap.put(URIDeeps.get(indexCnt), deeps);
        	indexCnt++;
        }
	    return resultMap;
	}
	
    // URI Tokenizer
	public boolean checkNull(Object object) {
		boolean result = true;
		if(object == null && ((String) object).length() <= 0) {
			result = false;
		}
		return result;
	}

    // replace Format With Parameters
	public Object replaceFormatWithParameters(Object dataMap) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.putAll((Map) dataMap);

//		paramMap.put("PARENT_COMMONCODE_ID", "_TYPE");
//		resultObject = commonCodeService.getList(paramMap);

		String replaceObject = null; 
		Map resultObject = new HashMap<String, Object>();
		
		// replace title
		replaceObject = (String) paramMap.get("TITLE"); 
		replaceObject = replaceObject.replaceAll("\\{\\{NAME\\}\\}", (String) paramMap.get("NAME"));
		resultObject.put("TITLE", replaceObject);
		
		// replace content
		replaceObject = (String) paramMap.get("CONTENT"); 
		replaceObject = replaceObject.replaceAll("\\{\\{NAME\\}\\}", (String) paramMap.get("NAME"));
		replaceObject = replaceObject.replaceAll("\\{\\{MEMBER_SEQ\\}\\}", (String) paramMap.get("MEMBER_SEQ"));
		resultObject.put("CONTENT", replaceObject);
		
		return resultObject;
	}
}
