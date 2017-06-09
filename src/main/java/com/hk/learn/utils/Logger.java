package com.hk.learn.utils;

import static org.testng.internal.EclipseInterface.ASSERT_LEFT;
import static org.testng.internal.EclipseInterface.ASSERT_RIGHT;
import static org.testng.internal.EclipseInterface.CLOSING_CHARACTER;
import static org.testng.internal.EclipseInterface.OPENING_CHARACTER;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.testng.Reporter;


public class Logger {
	public static final String SplitChar = "#";
	public static final String CaptureChar = "@";
	
	private static final FastDateFormat dateFormat = FastDateFormat
			.getInstance("yyyy-MM-dd HH:mm:ss");
	
	public static void pass(String message) {
		//logger.info(message);
		writeLog(LogCategory.Pass, message);
	}

	public static void pass(String expected, String actual, String message) {
		writeLog(LogCategory.AssertPass, expected, actual, message);		//
	}
	
	public static void error(String message) {
		writeLog(LogCategory.Error, message);
	}

	public static void error(String expected, String actual, String message) {
		writeLog(LogCategory.AssertError, expected, actual, message);
	}


	public static void step(String message) {
		writeLog(LogCategory.Step, message);
	}

	public static void info(String expected, String actual, String message) {
		writeLog(LogCategory.Info, expected, actual, message);
	}
	
	public static void info(String message) {
		writeLog(LogCategory.Info, message);
	}

	private static void writeLog(LogCategory category, String message) {
		writeLog(category, message, null);
	}

	private static void writeLog(LogCategory category, String message,
			String imagePath) {
		message = StringUtils.trimToEmpty(message);
		writeToTestNG(category, message);
		
	}

	private static void writeLog(LogCategory category, String expected,
			String actual, String message) {
		String formatted = "";
		if (null != message) {
			formatted = message + " ";
		}

		String fm = formatted + ASSERT_LEFT + expected + CLOSING_CHARACTER
				+ " and found " + OPENING_CHARACTER + actual + ASSERT_RIGHT;
		writeToTestNG(category, fm);
		
	}
	
	private static void writeToTestNG(LogCategory category, String message) {
		//Reporter.log(String.format(category.toString() + "%s%s%s%s\n",
		//		SplitChar, message, SplitChar, dateFormat.format(new Date())));
		//Reporter.log(String.format(category.toString() + "%s%s%s\n",
		//		SplitChar, message, SplitChar));
		Reporter.log(String.format("%s\n",
				 message));
	}
	
	public enum LogCategory {

		Pass(0), Info(0), Step(0), Remark(0), File(0), Error(1), AssertError(2), AssertPass(3), Capture(4);

		private int val;

		private LogCategory(int val) {
			this.val = val;
		}

		public int getVal() {
			return val;
		}

	}

	enum FileType {
		xml, json, text;
	}
}
