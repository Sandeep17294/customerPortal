
package com.aetins.customerportal.web.utils;

import java.security.Key;

import javax.crypto.spec.SecretKeySpec;

/**
 * @author satendra
 */
public class Encryptor
{
	
	 private static final String ALGORITHM       = "AES";
	   private static final String myEncryptionKey = "ThisIsFoundation";
	   private static final String UNICODE_FORMAT  = "UTF8";
	   
	public static String encrypt(String keydum, String initVectordum,String valueToEnc) {
		 String encryptedValue="";
		/*try{
	      Key key = generateKey();
	      Cipher c = Cipher.getInstance(ALGORITHM);
	      c.init(Cipher.ENCRYPT_MODE, key);  
	      byte[] encValue = c.doFinal(valueToEnc.trim().getBytes(UNICODE_FORMAT));
	      encryptedValue= new BASE64Encoder().encode(encValue);
		}catch(Exception e){
			e.printStackTrace();
		}*/
	      //return encryptedValue;
		return valueToEnc;
	   }

	public static String decrypt(String keyDum, String initVectorDum,String encryptedValue) {
		 String decryptedValue ="";
		/*try{
	     Key key = generateKey();
	     Cipher c = Cipher.getInstance(ALGORITHM);
	     c.init(Cipher.DECRYPT_MODE, key);
	     byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedValue.trim());
	     byte[] decValue = c.doFinal(decordedValue);//////////LINE 50
	     decryptedValue= new String(decValue);
		}catch(Exception e){
			e.printStackTrace();
		}*/
	     //return decryptedValue;
		return encryptedValue;
	}

	private static Key generateKey() throws Exception {
	     byte[] keyAsBytes;
	     keyAsBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
	     Key key = new SecretKeySpec(keyAsBytes, ALGORITHM);
	     return key;
	}

	public static void main(String[] args) throws Exception {

	     String value = "MANish2244";
	    String valueEnc = Encryptor.encrypt("","",value);
	     String valueDec = Encryptor.decrypt("bgnfgnmg","fgnghhkhk",valueEnc);

	     System.out.println("Plain Text : " + value);
	    // System.out.println("Encrypted : " + valueEnc);
	     System.out.println("Decrypted : " + valueDec);
	}

	
	
    /**
     * @param key
     * @param initVector
     * @param value
     * @return
     */
    /*public static String encrypt(String key, String initVector, String value)
    {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());
            System.out.println("encrypted string: " + Base64.encodeBase64String(encrypted));

            return Base64.encodeBase64String(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    *//**
     * @param key
     * @param initVector
     * @param encrypted
     * @return
     *//*
    public static String decrypt(String key, String initVector, String encrypted)
    {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] original = cipher.doFinal(Base64.decodeBase64(encrypted));

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }*/
}
