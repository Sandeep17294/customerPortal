package org.aetins;

import java.time.LocalTime;
import java.util.Date;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {
	
	public static void main(String[] args) {
		
		
		/*
		 * BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); String pa =
		 * encoder.encode("TEST123"); System.out.println(pa);
		 */
		 
		/*
		 * Date d2 = new Date(); long d3 = d2.getTime(); String
		 * datas=String.valueOf(d3); System.out.println(d3+"");
		 */
		
			/*
			 * Date date =DateUtil.getTodayDateTime(); System.out.println(date.toString());
			 */
		
		  LocalTime localTime = LocalTime.now();
		  System.out.println(new Date().toInstant().toString());

	}


}
