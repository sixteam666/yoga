package com.project.testutil;

import org.junit.Test;

import com.project.util.StringUtil;

public class StringUtilTest {

	@Test
	public void testIsEmpty() {
		String s1 = null;
		String s2 = "";
		String s3 = " ";
		String s4 = "test";
		System.out.println(StringUtil.isEmpty(s1));
		System.out.println(StringUtil.isEmpty(s2));
		System.out.println(StringUtil.isEmpty(s3));
		System.out.println(StringUtil.isEmpty(s4));
	}
	
	@Test
	public void testIsNotEmpty() {
		String s1 = null;
		String s2 = "";
		String s3 = " ";
		String s4 = "test";
		System.out.println(StringUtil.isNotEmpty(s1));
		System.out.println(StringUtil.isNotEmpty(s2));
		System.out.println(StringUtil.isNotEmpty(s3));
		System.out.println(StringUtil.isNotEmpty(s4));
	}
}
