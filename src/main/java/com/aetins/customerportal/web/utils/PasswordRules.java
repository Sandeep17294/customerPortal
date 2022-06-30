package com.aetins.customerportal.web.utils;

public class PasswordRules {
	private int upperCase = 0;
	private int lowerCase = 0;
	private int alpha = 0;
	private int digit = 0;
	private int specialChar = 0;

	public void rules(String password) {
		upperCase = 0;
		lowerCase = 0;
		alpha = 0;
		digit = 0;
		specialChar = 0;
		for (int i = 0; i < password.length(); i++) {
			if (Character.isAlphabetic(password.charAt(i))) {
				alpha++;
			}
			if (Character.isUpperCase(password.charAt(i))) {
				upperCase++;
			} else if (Character.isLowerCase(password.charAt(i))) {
				lowerCase++;
			} else if (Character.isDigit(password.charAt(i))) {
				digit++;
			} else {
				specialChar++;
			}

		}
	}

	public int getUpperCase() {
		return upperCase;
	}

	public void setUpperCase(int upperCase) {
		this.upperCase = upperCase;
	}

	public int getLowerCase() {
		return lowerCase;
	}

	public void setLowerCase(int lowerCase) {
		this.lowerCase = lowerCase;
	}

	public int getAlpha() {
		return alpha;
	}

	public void setAlpha(int alpha) {
		this.alpha = alpha;
	}

	public int getDigit() {
		return digit;
	}

	public void setDigit(int digit) {
		this.digit = digit;
	}

	public int getSpecialChar() {
		return specialChar;
	}

	public void setSpecialChar(int specialChar) {
		this.specialChar = specialChar;
	}

}
