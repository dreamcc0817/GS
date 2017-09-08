package com.dreamcc.gs.test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dreamcc.gs.util.MD5Util;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class MD5Password {

	@Test
	public void test() {
		String password = "admin";
		System.out.println(MD5Util.MD5EncodeUtf8(password));
//		password = "test";
//		System.out.println(MD5Util.MD5EncodeUtf8(password));
	}

}
