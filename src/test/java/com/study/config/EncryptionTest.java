package com.study.config;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class EncryptionTest {

	@Autowired
	private StringEncryptor stringEncryptor;
	
	@Test
	public void encryptPwd() {
		String result = stringEncryptor.encrypt("cyx@1897");
		System.out.println(result);
	}
}
