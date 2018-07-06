package wc.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import sun.misc.*;



public class EncryptUtil {
	public static final String KEY_SHA = "SHA";

	public static final String KEY_MD5 = "MD5";

	public static final String KEY_SHA1 = "SHA-1";

	private static final String KEY_SHA256 = "SHA-256";
	
	private static final String KEY_SHA512 = "SHA-512";


	public static byte[] decryptBASE64(String key) throws Exception {
		return (new BASE64Decoder()).decodeBuffer(key);
	}


	public static String encryptBASE64(byte[] key) throws Exception {
		return (new BASE64Encoder()).encodeBuffer(key);
	}


	public static byte[] encryptMD5(byte[] data) throws Exception {

		MessageDigest md5 = MessageDigest.getInstance(KEY_MD5);
		md5.update(data);

		return md5.digest();

	}


	public static byte[] encryptSHA(String shaType,byte[] data) throws Exception {

		MessageDigest sha = MessageDigest.getInstance(shaType);
		sha.update(data);

		return sha.digest();

	}


	public static byte[] encryptSHA1(byte[] data) throws Exception {

		return encryptSHA(KEY_SHA1, data);

	}

	public static byte[] encryptSHA256(byte[] data) throws Exception {

		return encryptSHA(KEY_SHA256, data);

	}
	

	public static byte[] encryptSHA512(byte[] data) throws Exception {

		return encryptSHA(KEY_SHA512, data);

	}


	public static String encryptMD5(String content, String charset) {
		try {
			byte[] b = encryptMD5(content.getBytes(charset));
			StringBuffer buffer = new StringBuffer();

			for (int i = 0; i < b.length; i++) {
				String shaHex = Integer.toHexString(b[i] & 0xFF);
				if (shaHex.length() < 2) {
					buffer.append(0);
				}
				buffer.append(shaHex);
			}
			return buffer.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";

	}


	public static String encryptMD5(String content) {
		return encryptMD5(content, "UTF-8");
	}


	public static String encryptSHA(String shaType,String content, String charset) {
		try {
			byte[] b = encryptSHA(shaType,content.getBytes(charset));
			StringBuffer buffer = new StringBuffer();
			
			for (int i = 0; i < b.length; i++) {
				String shaHex = Integer.toHexString(b[i] & 0xFF);
				if (shaHex.length() < 2) {
					buffer.append(0);
				}
				buffer.append(shaHex);
			}
			return buffer.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	

	public static String encryptSHA(String shaType,String content){
		return encryptSHA(shaType,content,"UTF-8");
	}
	

	public static String encryptSHA1(String content,String charset){
		
		return encryptSHA(KEY_SHA1, content,charset);
	}
	
	

	public static String encryptSHA1(String content){
		return encryptSHA1(content,"UTF-8");
	}
	

	public static String encryptSHA256(String content,String charset){
		
		return encryptSHA(KEY_SHA256, content,charset);
	}
	
	

	public static String encryptSHA256(String content){
		return encryptSHA256(content,"UTF-8");
	}
	

	public static String encryptSHA512(String content,String charset){
		
		return encryptSHA(KEY_SHA512, content,charset);
	}
	
	

	public static String encryptSHA512(String content){
		return encryptSHA512(content,"UTF-8");
	}

}