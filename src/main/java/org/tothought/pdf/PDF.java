package org.tothought.pdf;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;

import org.htmlcleaner.TagNode;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.itextpdf.text.DocumentException;

public class PDF {

	private String url;
	private OutputStream outputStream;
	private File resourceDirectory;
	private String applicationUrl;

	public PDF(String url, OutputStream outputStream, String resourceDirectory, String applicationUrl) {
		this.url = url;
		this.outputStream = outputStream;
		this.resourceDirectory = new File(resourceDirectory);
		this.applicationUrl = applicationUrl;
	}

	public void generate() {
		try {
			TagNode rootNode = Cleaner.clean(this.url);
			Cleaner.isolateMainContent(rootNode);
			Cleaner.resolveExternalContent(rootNode, this.resourceDirectory, this.applicationUrl);
			String xml = Cleaner.getXmlAsString(rootNode);
			System.out.println(xml);
			
			ITextRenderer renderer = new ITextRenderer();

			renderer.setDocumentFromString(xml);
			renderer.layout();
			renderer.createPDF(this.outputStream);

		} catch (MalformedURLException e) {
			//e.printStackTrace();
		} catch (IOException e) {
			//e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}catch (Exception e){
			e.printStackTrace();
		}
	}

}
