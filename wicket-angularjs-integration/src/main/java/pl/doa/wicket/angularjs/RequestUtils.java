package pl.doa.wicket.angularjs;

import java.io.BufferedReader;

import javax.servlet.http.HttpServletRequest;

public class RequestUtils {

	public static String readRequestBody(HttpServletRequest request)
			throws Exception {
		StringBuffer buffer = new StringBuffer();
		String line = null;

		BufferedReader reader = request.getReader();
		while ((line = reader.readLine()) != null)
			buffer.append(line);

		return buffer.toString();
	}

}
