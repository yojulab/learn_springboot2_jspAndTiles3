package com.gangsin.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.imgscalr.Scalr.Method;
import org.imgscalr.Scalr.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * 업로드 파일의 유효성을 체크하기 위한 클래스이다.
 * 
 */
@Component
public class FileUtil {

	@Autowired
	private CommonUtil commonUtil;
	
	// @Value("#{propertyConfigurer['attach.file.path.root']}") 
    private String attach_file_path_root;
    
//	@Value("#{propertyConfigurer['attach.file.size.default']}") 
    private int attach_file_size_default = 500000000;

    /**
     * 유효한 파일 확장자 체크
     * 
     * @param fileExtName 파일 확장자명
     * @return 유효하지 않은 파일 여부
     */
    public boolean checkAllowFileExt(String fileExtName) {
/*        StringTokenizer token = new StringTokenizer(ConfigManager.getProperty("opentok.allow.upload.img.ext")
                                                                 .replaceAll(" ", ""), ",");

        while (token.hasMoreElements()) {

            // 파일 업로드 확장자 체크
            if (FileUtil.getFileExtention(fileExtName).contains(token.nextToken())) {
                return true;
            }
        }
*/        return false;
    }

    /**
     * 파일명을 UUID로 새로 생성한다.
     * 
     * @return 새로운 파일명
     */
    private String getNewFileName() {
        return commonUtil.getUniqueSequence();
    }

    /**
     * 파일명을 년월일시를 조합하여 새로 생성한다.
     * 
     * @param fileName 파일명
     * @return 새로운 파일명
     */
    public String getNewFileName(String fileName) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSS");
        return fileName.substring(0, fileName.lastIndexOf(".")) + "_" + dateFormat.format(System.currentTimeMillis());
    }

    /**
     * 파일명을 년월일시를 조합하여 새로 생성한다. (확장자 없는 경우)
     * 
     * @param fileName 파일명
     * @return 새로운 파일명
     */
    public String getNewFileNameNotExt(String fileName) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSS");
        return fileName + "_" + dateFormat.format(System.currentTimeMillis());
    }

    /**
     * 파일의 확장자를 추출한다.
     * 
     * @param fileName 파일명
     * @return 파일의 확장자명
     */
    public String getFileExtention(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }
    
	public Object setMultipartList(MultipartHttpServletRequest multipartRequest) {
		List<Object> fileList = new ArrayList<Object>();

		String filePath = attach_file_path_root ;

		Iterator<String> multiFileList = multipartRequest.getFileNames();

		String fileName = null;
		String originalFileName = null;
		String physicalFileName = null;
		String physicalThumbnailFileName = null;
		String fileContentType = null;
		String pathWithFileName = null;

		MultipartFile multiFile = null;

		while (multiFileList.hasNext()) {
			fileName = multiFileList.next();

			multiFile = multipartRequest.getFile(fileName);

			int compareSize = (int) multiFile.getSize();
			if(compareSize > 0) {
				// limit file size
				if (multiFile.getSize() > attach_file_size_default) {
	//				return WebArgumentResolver.UNRESOLVED;
				}
	
				// original filename (ex. neopets.jpg)
				originalFileName = multiFile.getOriginalFilename().trim();
	
				if (originalFileName == "" || originalFileName.isEmpty()) {
					return WebArgumentResolver.UNRESOLVED;
				}
	
				// convert original filename with FileUtil.java to virtual
				// filename to upload in ftp
	
				physicalFileName = this.getNewFileName();
	
				// file 저장경로 + fileName
				pathWithFileName = filePath + physicalFileName;
	
				// file size (ex. 1KByte -> 1048)
				String fileSize = multiFile.getSize() + "";
	
				// get ContentType (ex. jpg(original file type) -> images/JPEG
				// ...)
				fileContentType = multiFile.getContentType();
	
				try {
					// file 저장위치에 이동
					multiFile.transferTo(new File(pathWithFileName));
					
					Map<Object, Object> fileMap = new HashMap<Object, Object>();
					
					String uniqueSequence = commonUtil.getUniqueSequence();					
					fileMap.put("ATTACHFILE_SEQ", uniqueSequence);
					fileMap.put("ORGINAL_NAME", originalFileName);
					fileMap.put("PHYSICAL_NAME", physicalFileName);
					fileMap.put("TYPE", fileContentType);
					fileMap.put("SIZE", fileSize);
					fileMap.put("PATH", filePath);
					physicalThumbnailFileName = (String) this.scaleImage(filePath, physicalFileName);
					fileMap.put("THUMBNAIL_NAME", physicalThumbnailFileName);
	
					fileList.add(fileMap);
	
				} catch (Exception e) {
					this.deleteFile(filePath, physicalFileName);
					e.printStackTrace();
				} finally {
				}
			}
		}
		
		if(fileList.size() <= 0) {
			fileList = null;
		}
		return fileList;
	}
	
	public Object deleteFile(String filePath, String fileName) {
		File file = new File(filePath + fileName);
		return file.delete();
	}
	
	public Object deleteFile(Object objectList) {
		List<Map> fileList = (List<Map>) objectList;
		
		int count = 0;
		String path = null;
		for(Map<String, String> dataMap : fileList) {
			path = dataMap.get("PATH");
			this.deleteFile(path, dataMap.get("PHYSICAL_NAME"));
			this.deleteFile(path, dataMap.get("THUMBNAIL_NAME"));
			count++;
		}
		
		return count;
	}

	public Object scaleImage(String filePath, String physicalFileName) {
		String pathWithFileName = filePath + physicalFileName;
		
		String physicalThumbnailFileName = physicalFileName + "_thumb";
		String pathWithThumbnailFileName = filePath + physicalThumbnailFileName;
		

		File physicalFile = new File(pathWithFileName);
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(physicalFile);
		
			//Quality indicate that the scaling implementation should do everything
			// create as nice of a result as possible , other options like speed
			// will return result as fast as possible
			//Automatic mode will calculate the resultant dimensions according
			//to image orientation .so resultant image may be size of 50*36.if you want
			//fixed size like 50*50 then use FIT_EXACT
			//other modes like FIT_TO_WIDTH..etc also available.
			BufferedImage thumbImg = Scalr.resize(bufferedImage, Method.QUALITY,Mode.AUTOMATIC, 
			             50,50, Scalr.OP_ANTIALIAS);
			
			//convert bufferedImage to outpurstream 
//			ByteArrayOutputStream os = new ByteArrayOutputStream();
//			ImageIO.write(thumbImg,"png",os);
			
			//or wrtite to a file
			File thumbFile = new File(pathWithThumbnailFileName);
			  
			ImageIO.write(thumbImg, "png", thumbFile);		
		} catch (IOException e) {
			this.deleteFile(filePath, physicalThumbnailFileName);
			e.printStackTrace();
		}
		
		return physicalThumbnailFileName;
	}
}
