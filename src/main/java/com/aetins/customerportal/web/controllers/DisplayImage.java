package com.aetins.customerportal.web.controllers;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DisplayImage extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException
	{
	if(request.getParameter("custRefNo")!=null&&request.getParameter("custRefNo")!=""){
		String custRefNo=request.getParameter("custRefNo");
		if(custRefNo!=null&&custRefNo!=""){
			if(custRefNo.trim().startsWith("temp")){
				System.out.println("custRefNo:"+custRefNo);
				response.setContentType("image/jpeg");
				ServletOutputStream out;
				out = response.getOutputStream();
				ServletContext servletContext = getServletContext();
				String finalImageActualLocation=servletContext.getRealPath("") + File.separator + "resources" + File.separator +"crop" + File.separator +custRefNo.trim()+ ".jpg";
				FileInputStream fin = new FileInputStream(finalImageActualLocation);
				
				BufferedInputStream bin = new BufferedInputStream(fin);
				BufferedOutputStream bout = new BufferedOutputStream(out);
				int ch =0; ;
				while((ch=bin.read())!=-1)
				{
				bout.write(ch);
				}
				
				bin.close();
				fin.close();
				bout.close();
				out.close();				
			}else{
				System.out.println("custRefNo else part:"+custRefNo);
				response.setContentType("image/jpeg");
				ServletOutputStream out;
				out = response.getOutputStream();
				ServletContext servletContext = getServletContext();
				String finalImageActualLocation=servletContext.getRealPath("") + File.separator + "resources" + File.separator +"profileImage" + File.separator +custRefNo.trim()+ ".jpg";
				FileInputStream fin = new FileInputStream(finalImageActualLocation);
				
				BufferedInputStream bin = new BufferedInputStream(fin);
				BufferedOutputStream bout = new BufferedOutputStream(out);
				int ch =0; ;
				while((ch=bin.read())!=-1)
				{
				bout.write(ch);
				}
				
				bin.close();
				fin.close();
				bout.close();
				out.close();
			}
			
		}
		}		
	}
}