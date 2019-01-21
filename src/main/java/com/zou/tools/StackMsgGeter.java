package com.zou.tools;

import java.io.PrintWriter;
import java.io.StringWriter;

public class StackMsgGeter {
	private  static StringWriter sw = new StringWriter();;
	public static String printStackTraceToString(Throwable t) {
	    t.printStackTrace(new PrintWriter(sw, true));
	    return sw.getBuffer().toString();
	}
}
