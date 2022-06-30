package com.aetins.customerportal.web.utils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.util.IOUtils;
import org.primefaces.model.UploadedFile;
import org.springframework.util.FileCopyUtils;
public class FileUtils {
	static Logger logger = Logger.getLogger(FileUtils.class);
	
	/**
	 * Created By	: Mathi
	 * Created On	: 29 Oct 2013
	 * Description	: To write a string to a file. If the file exists delete it.
	 */
	public static void writeToFile(String fileName, String content){
		logger.info("Before Writing into file : " + fileName);
		try{
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
				    new FileOutputStream(fileName), "UTF-8"));
				
		    out.write(content);
		    out.close();
		}catch(Exception e){
			logger.info("An error occurred while writing into file :  " + fileName + " : " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Created By	: Mathi
	 * Created On	: 29 Oct 2013
	 * Description	: To write a string to a file. If the file exists delete it.
	 */
	public static void writeToFile(String fileName, byte[] content){
		logger.info("Before Writing into file : " + fileName);
		try{
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
				    new FileOutputStream(fileName), "UTF-8"));
				
		    out.write(new String(content));
		    out.close();
		}catch(Exception e){
			logger.info("An error occurred while writing into file :  " + fileName + " : " + e.getMessage());
			e.printStackTrace();
		}
	}
	
	/**
	 * Created By	: Mathi
	 * Created On	: 24 Jan 2014
	 * Description	: To load property file in UTF-8 format.
	 */
	public static Map<String, String> loadProperties(String fileName){
		Map<String, String> prop = new HashMap<String, String>();
		logger.info("Before reading from file : " + fileName);
		try{
			BufferedReader in = new BufferedReader(new InputStreamReader(
				    new FileInputStream(fileName), "UTF-8"));
				
			String oneLine;
			String[] tokens = null;
			
            while ((oneLine=in.readLine()) != null) {
                if(!oneLine.startsWith("#")){
                	tokens = oneLine.split("=");
                	if(BSLUtils.isNotNull(tokens) && tokens.length > 1)
                		prop.put(tokens[0], tokens[1]);
                }
            }
		    in.close();
		}catch(Exception e){
			logger.info("An error occurred while reading from file :  " + fileName + " : " + e.getMessage());
			e.printStackTrace();
		}
		return prop;
	}
	
	public static void saveURLToFile(String strUrl, String fileName) throws Exception{
		byte[] bytes = getByteFromURL(strUrl);
		
		FileOutputStream fos = new FileOutputStream(fileName);
		fos.write(bytes);
		fos.close();
	}
	
	public static byte[] getByteFromURL(String strUrl) throws Exception{
		URL url = new URL(strUrl);

		URLConnection connection = url.openConnection();
		InputStream in = connection.getInputStream();
		int contentLength = connection.getContentLength();

		ByteArrayOutputStream tmpOut;
		if (contentLength != -1) {
			tmpOut = new ByteArrayOutputStream(contentLength);
		} else {
			tmpOut = new ByteArrayOutputStream(16384);
		}

		byte[] buf = new byte[512];
		while (true) {
			int len = in.read(buf);
			if (len == -1) {
				break;
			}
			tmpOut.write(buf, 0, len);
		}
		in.close();
		tmpOut.close();
		return tmpOut.toByteArray();
	}
	
	/**
	 * Created By	: Mathi
	 * Created On	: 29 Oct 2013
	 * Description	: To delete the files that are older than a day from the specified folder.
	 */
	public static void cleanAllFilesOlderThanToday(String folderName){
		logger.info("Folder in which files to delete = " + folderName);
		int daysBack = 1;

		final File directory = new File(folderName);
		if(directory.exists()){
			logger.info(" Directory Exists");
		  final File[] listFiles = directory.listFiles();          
		  final long purgeTime = System.currentTimeMillis() - (daysBack * 24 * 60 * 60 * 1000);
		  logger.info("System.currentTimeMillis " + System.currentTimeMillis());
		  logger.info("purgeTime " + purgeTime);

		  for(File listFile : listFiles) {
			  logger.info("Length : "+ listFiles.length);
			  logger.info("listFile.getName() : " +listFile.getName());
			  logger.info("listFile.lastModified() :"+listFile.lastModified());
		      if(listFile.lastModified() < purgeTime) {
		    	  logger.info("Inside File Delete");
		    	  listFile.delete();
		      }
		  }
		} else {
			logger.info("The dir doesn't exist : " + folderName);
		}
	}
	
	public static void deleteFile(String fileName){
		try{
			File fileToDelete = new File(fileName);
			if(fileToDelete.exists()){
				fileToDelete.delete();
			}
		}catch(Exception e){
			logger.info("An Error occurred while Deleting the file : " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void callUrl(String url) throws Exception{
		getByteFromURL(url);
	}

	public static void rename(String fileName, String toFileName) throws Exception {
		try{
			File f = new File(fileName);
			f.renameTo(new File(toFileName));
		}catch(Exception e){
			throw e;
		}
	}
	
	public static String readFileContentToString(String fileName) throws Exception{
		FileInputStream fis = null;
		int i = 0;
	    char c;
	    byte[] encoded = null;
		fis = new FileInputStream(fileName);
		encoded=IOUtils.toByteArray(fis);
		//encoded = fis.r
		//i=fis.read(encoded);
		//byte[] encoded = Files.readAll(new FileInputStream(fileName));
		String fileContent = new String(encoded, "UTF-8");
		return fileContent;
	}
	
	public static void main(String[] arg){
		Map<String, String> prop = loadProperties("D:/Mathi/Projects/UATCP/WebContent/WEB-INF/classes/errorMessagesArb.properties");
		System.out.println("Content = " + prop.get("submitClaim.dateOfLossEmpty"));
	}

	public static boolean isExist(String fileName) {
		File file = new File(fileName);
		return file.exists();
	}

	/*public static void copyFile(String todayGraphFileName3Months, String templateFileName3Months) throws IOException {
		File srcFile = new File(templateFileName3Months);
		File destFile = new File(todayGraphFileName3Months);
		org.apache.commons.io.FileUtils.copyFile(srcFile, destFile);
	}*/
	/**
	 * Created By	: viswakarthick
	 * Created On	: 08-02-2017
	 * Description	: To copy file using spring framework. If the file exists delete it.
	 */
	
	public static boolean springCopyFile(String sourcePath,UploadedFile file) throws Exception {
		File targetFolder = new File(sourcePath);
		InputStream is = null;
		OutputStream os = null;

		if (!targetFolder.exists())
			targetFolder.mkdir();
		File[] subFiles=targetFolder.listFiles();
		for (File file2 : subFiles) {
			if(file2.getName().equals(file.getFileName()))
				return false;
		}
		is = file.getInputstream();
		os = new FileOutputStream(new File(targetFolder, file.getFileName()));
		FileCopyUtils.copy(is, os);
		is.close();
		os.flush();
		os.close();
						
		return true;
	}
}