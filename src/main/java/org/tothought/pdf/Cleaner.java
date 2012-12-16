package org.tothought.pdf;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;

import org.apache.commons.io.FileUtils;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.PrettyXmlSerializer;
import org.htmlcleaner.TagNode;

public class Cleaner {

	static CleanerProperties cleanerProperties = new CleanerProperties();
	static HtmlCleaner htmlCleaner;
	static PrettyXmlSerializer prettyXmlSerializer;
	static TagNode parentNode;

	static {
		cleanerProperties.setTranslateSpecialEntities(true);
		cleanerProperties.setTransResCharsToNCR(true);
		cleanerProperties.setOmitComments(true);
		htmlCleaner = new HtmlCleaner(cleanerProperties);
		prettyXmlSerializer = new PrettyXmlSerializer(cleanerProperties);
	}

	public static TagNode clean(String url) throws MalformedURLException, IOException {
		return htmlCleaner.clean(new URL(url));
	}

	public static void isolateMainContent(TagNode node) {
		TagNode body = node.getElementsByName("body", true)[0];
		TagNode content = body.getElementsByAttValue("id", "center-panel", true, false)[0];

		body.removeAllChildren();
		body.addChild(content);
	}

	public static void resolveExternalContent(TagNode node, File resourceDirectory, String applicationUrl) {
		Cleaner.resolveStyleSheets(node, resourceDirectory, applicationUrl);
		Cleaner.resolveImages(node, resourceDirectory, applicationUrl);
	}

	private static void resolveImages(TagNode node, File resourceDirectory, String applicationUrl) {
		TagNode[] images = node.getElementsByName("img", true);

		for (TagNode image : images) {

			String src = image.getAttributeByName("src");
			String imageName = src.substring(src.lastIndexOf("/"));

			Iterator<File> iter = FileUtils.iterateFiles(resourceDirectory, new String[] { "png", "jpg" },
					true);

			while (iter.hasNext()) {
				File file = iter.next();

				if (imageName.contains(file.getName())) {
					System.out.println(applicationUrl + src);
					image.setAttribute("src", applicationUrl + src);
				}
			}
		}
	}

	private static void resolveStyleSheets(TagNode node, File resourceDirectory, String applicationUrl) {
		TagNode[] styleSheets = node.getElementsByName("link", true);

		for (TagNode styleSheet : styleSheets) {

			String href = styleSheet.getAttributeByName("href");
			String styleSheetName = href.substring(href.lastIndexOf("/"));

			if (NodeUtil.attributeEquals(styleSheet, "rel", "stylesheet") && !href.contains("http:")) {
				Iterator<File> iter = FileUtils.iterateFiles(resourceDirectory, new String[] { "css" }, true);

				while (iter.hasNext()) {
					File file = iter.next();

					if (styleSheetName.contains(file.getName())) {
						styleSheet.setAttribute("href", applicationUrl + href);
					}
				}
			}
		}
	}

	public static String getXmlAsString(TagNode node) throws IOException {
		return prettyXmlSerializer.getAsString(node);
	}
}
