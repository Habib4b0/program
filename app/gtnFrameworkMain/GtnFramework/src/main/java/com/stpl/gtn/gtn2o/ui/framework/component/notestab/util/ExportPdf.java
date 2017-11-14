/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.component.notestab.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class ExportPdf {
	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(ExportPdf.class);
	private File filePath;
	private String header;
	private File pdfFile;
	public static final String PDF_EXT = ".pdf";

	public ExportPdf(String header, File filePath, File pdfFile) {
		this.header = header;
		this.pdfFile = pdfFile;
		this.filePath = filePath;
	}

	public void export(String fileContent) {

		Font fontHeader = FontFactory.getFont("Times-Roman", 14, Font.BOLD);
		Font fontNotes = FontFactory.getFont("Times-Roman", 12, Font.NORMAL);
		Paragraph headerPara = new Paragraph();
		Paragraph notesPara = new Paragraph();

		notesPara.add(fileContent);
		notesPara.setFont(fontNotes);
		notesPara.setAlignment(Element.ALIGN_LEFT);

		headerPara.setAlignment(Element.ALIGN_CENTER);
		headerPara.add(new Phrase(header));
		headerPara.setFont(fontHeader);

		if (!filePath.isDirectory()) {
			filePath.mkdirs();
		}
		if (!pdfFile.exists()) {
			try {
				if (!pdfFile.createNewFile()) {
					gtnLogger.error("Unable to create New File in Path >" + filePath);
				}
			} catch (IOException ex) {
				gtnLogger.error(ex.getMessage());
			}
		}
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
			document.open();
			document.add(headerPara);
			document.add(notesPara);
			document.close();
		} catch (Exception ex) {
			gtnLogger.error(ex.getMessage());
		}

	}
}
