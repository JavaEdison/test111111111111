package com.zjf.babasports.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.jasypt.contrib.org.apache.commons.codec_1_3.binary.Base64;

public class EncryptUtil {
	/**
	 * 生成加密密码
	 * @param password
	 * @return
	 */
	public static String getPasswordByEncrypt(String password){
		//MD5加密后的密码
		String pwdMD5AndB64 = new String(Base64.encodeBase64(DigestUtils.md5(password)));
		return pwdMD5AndB64;
	}
	public static void main(String[] args) {
		System.out.println(EncryptUtil.getPasswordByEncrypt("123456"));
	}
}
