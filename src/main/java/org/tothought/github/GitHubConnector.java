package org.tothought.github;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.Collections;

import org.eclipse.egit.github.core.Gist;
import org.eclipse.egit.github.core.GistFile;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.GistService;

public class GitHubConnector {

	public static String getPage(String url) {
		StringBuilder sb = new StringBuilder();
		try {
			URL webLink = new URL(url);
			InputStream response = webLink.openStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					response));

			for (String line; (line = reader.readLine()) != null;) {
				sb.append(line);
			}

			reader.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static void main(String[] args) {

		try {
			System.out.println(getPage("https://api.github.com/users/kmb385"));
			doPost();
			System.out.println(getPage("https://api.github.com/users/user"));
			System.out.println(getPage("https://api.github.com/?u=kmb385"));
			doConnection();

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static void doPost() {
		try {
			// Construct data
			String data = URLEncoder.encode("username", "UTF-8") + "="
					+ URLEncoder.encode("kmb385", "UTF-8");
			data += "&" + URLEncoder.encode("password", "UTF-8") + "="
					+ URLEncoder.encode("shamrock1", "UTF-8");

			// Send data
			URL url = new URL("https://api.github.com/authorizations");
			URLConnection conn = url.openConnection();
			conn.setRequestProperty
		    ("Content-Type", "application/x-www-form-urlencoded");

			conn.setDoOutput(true);
			OutputStreamWriter wr = new OutputStreamWriter(
					conn.getOutputStream());
			wr.write(data);
			wr.flush();

			// Get the response
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			String line;
			while ((line = rd.readLine()) != null) {
				System.out.println(line);
			}
			wr.close();
			rd.close();
		} catch (Exception e) {
		} finally {
		}

	}
	
	public static void doConnection(){
		GitHubClient client = new GitHubClient();
		client.setCredentials("kmb385", "shamrock1");
		GistService service = new GistService(client);
		
		Gist gist = new Gist();
		GistFile gistFile = new GistFile();
		gistFile.setContent("System.out.println(\"Hello World\");");
		
		gist.setFiles(Collections.singletonMap("Hello.java", gistFile));
		gist.setPublic(true);
		
		try {
			service.createGist(gist);
			gist = service.createGist(gist);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		System.out.println(gist.getId());
		//service.getGist(id);
	}

}
