package org.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class DiagnosticsServletTest
{

	@ArquillianResource
	private URL webappUrl;

	@Deployment(testable = false)
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class)
				.addClasses(CrossContextRedirectFilter.class)
				.addClasses(DiagnosticsServletTest.class)
				.addAsWebInfResource("web.xml", "web.xml");
	}

	@Test
	public void getInfo() throws Exception {

		String html = getHTML(webappUrl.toURI().toString() + "diagnostics");
		System.out.println(html);

		String html2 = getHTML(webappUrl.toURI().toString() + "crosscontext/diagnostics");
		System.out.println(html2);
	}

	public static String getHTML(String urlToRead) throws Exception {
		StringBuilder result = new StringBuilder();
		URL url = new URL(urlToRead);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line;
		while ((line = rd.readLine()) != null) {
			result.append(line);
			result.append('\n');
		}
		rd.close();
		return result.toString();
	}

}