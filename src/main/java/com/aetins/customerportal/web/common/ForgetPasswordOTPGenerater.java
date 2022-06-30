package com.aetins.customerportal.web.common;

import java.util.Random;

public class ForgetPasswordOTPGenerater {

	private String CharList = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private int length = 6;

	public String generateRandomString() {

		StringBuffer randStr = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = getRandomNumber();
			char ch = CharList.charAt(number);
			randStr.append(ch);
		}
		return randStr.toString();
	}

	private int getRandomNumber() {
		int randomInt = 0;
		Random randomGenerator = new Random();
		randomInt = randomGenerator.nextInt(CharList.length());
		if (randomInt - 1 == -1) {
			return randomInt;
		} else {
			return randomInt - 1;
		}
	}

	public String getCharList() {
		return CharList;
	}

	public void setCharList(String charList) {
		CharList = charList;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

}
