package com.hk.learn.myjavalearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class LogTest {
	private static Logger log = LoggerFactory.getLogger(LogTest.class);  
	
	@Test
	public void f() {
        log.trace("======trace");  
        log.debug("======debug");  
        log.info("======info");  
        log.warn("======warn");  
        log.error("======error"); 
	}
}
