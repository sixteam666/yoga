package com.project.util;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	/*
	 * 更改图片的名字，防止重名
	 */
	public String changeName(String name){
		String newName = name +"_"+ UUID.randomUUID().toString();
		return newName;
	}
	
	/**
	 * 传单个文件
	 * @param multipartFile
	 * @param req
	 */
	public String getFileName(MultipartFile multipartFile,HttpServletRequest req){
		//获取到tomcat的物理路径 将虚拟路径转化为物理路径
		ServletContext context = req.getServletContext();
		String path = context.getRealPath("/upload");
		File filepath = new File(path);
		if (!filepath.exists()) {
            filepath.mkdirs();				
		}
		//传过来的文件名经过加工防止重复
		String fileName = changeName(multipartFile.getOriginalFilename());
		//将文件保存path里面
		File file = new File(path,fileName);
		//将客户端传递过来的文件信息复制给path目录下面的文件
		try {
			multipartFile.transferTo(file);
			return fileName;
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	


}
