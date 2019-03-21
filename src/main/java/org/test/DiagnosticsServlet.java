package org.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DiagnosticsServlet extends HttpServlet
{

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		response.setContentType("text/plain");

		PrintWriter printWriter = response.getWriter();
		printKV(printWriter, "requestURI", request.getRequestURI());
		printKV(printWriter, "requestURL", request.getRequestURL());
		printKV(printWriter, "contextpath", request.getContextPath());
		printKV(printWriter, "servletpath", request.getServletPath());
	}

	public void destroy() {
		// do nothing.
	}

	private void printKV(PrintWriter printWriter, String key, Object value) {
		printWriter.println(String.format("%s=%s", key, value));
	}
}