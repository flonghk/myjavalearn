package com.hk.learn.utils;

import static org.testng.internal.EclipseInterface.ASSERT_LEFT;
import static org.testng.internal.EclipseInterface.ASSERT_LEFT2;
import static org.testng.internal.EclipseInterface.ASSERT_MIDDLE;
import static org.testng.internal.EclipseInterface.ASSERT_RIGHT;
import static com.hk.learn.utils.Logger.pass;
import static com.hk.learn.utils.Logger.error;

public class Assertion {

	/**
	 * Asserts that two Strings are equal. If they are not, an AssertionError,
	 * with the given message, is thrown.
	 * 
	 * @param actual
	 *            the actual value
	 * @param expected
	 *            the expected value
	 * @param message
	 *            the assertion error message
	 */
	public static void assertEquals(String actual, String expected,
			String message) {
		assertEquals((Object) actual, (Object) expected, message);
	}

	/**
	 * Asserts that two Strings are equal. If they are not, an AssertionError is
	 * thrown.
	 * 
	 * @param actual
	 *            the actual value
	 * @param expected
	 *            the expected value
	 */
	public static void assertEquals(String actual, String expected) {
		assertEquals(actual, expected, null);
	}

	/*
	 * public static void assertJsonNodeEquals(Object expected, String json,
	 * String jsonPath) { assertEquals(expected,
	 * JsonPath.parse(json).read(jsonPath)); }
	 */
	/**
	 * Asserts that two objects are equal. If they are not, an AssertionError,
	 * with the given message, is thrown.
	 * 
	 * @param actual
	 *            the actual value
	 * @param expected
	 *            the expected value
	 * @param message
	 *            the assertion error message
	 */
	public static void assertEquals(Object actual, Object expected,
			String message) {
		if ((expected == null) && (actual == null)) {
			pass(String.valueOf(actual), String.valueOf(expected), message);
			return;
		}
		if (expected != null) {
			if (expected.equals(actual)) {
				pass(String.valueOf(actual), String.valueOf(expected), message);
				return;
			}
			/*
			if (expected.getClass().isArray()) {
				assertArrayEquals(actual, expected, message);
				pass(String.valueOf(actual), String.valueOf(expected), message);
				return;
			} else if (expected.equals(actual)) {
				pass(String.valueOf(actual), String.valueOf(expected), message);
				return;
			}
			*/
		}
		failNotEquals(actual, expected, message);
	}
	
	private static void failNotEquals(Object actual, Object expected,String message) {
		System.out.println("fail");
		fail(format(actual, expected, message));
		//error(String.valueOf(actual), String.valueOf(expected), message);
	}
	
	/**
	 * Fails a test with the given message.
	 * 
	 * @param message
	 *            the assertion error message
	 */
	public static void fail(String message) {
		throw new AssertionError(message);
	}

	/**
	 * Fails a test with no message.
	 */
	public static void fail() {
		fail(null);
	}
	
	private static String format(Object actual, Object expected, String message) {
		String formatted = "";
		if (null != message) {
			formatted = message + " ";
		}

		return formatted + ASSERT_LEFT + expected + ASSERT_MIDDLE + actual
				+ ASSERT_RIGHT;
	}
}
