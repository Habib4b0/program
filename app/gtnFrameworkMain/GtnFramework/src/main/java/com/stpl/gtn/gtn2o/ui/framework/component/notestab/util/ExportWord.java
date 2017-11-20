/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.component.notestab.util;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.util.List;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class ExportWord {
	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(ExportWord.class);
	private File filePath;
	private File wordFile;
	private String notesHistory = "Notes History";
	public static final String DOC_EXT = ".doc";
	public static final String DOCX_EXT = ".docx";

	public ExportWord(File filePath, File wordFile) {
		this.filePath = filePath;
		this.wordFile = wordFile;
	}

	public void export(String fileContent) {

		createWordDocument();

		XWPFDocument doc = new XWPFDocument();
		XWPFParagraph logoPara = doc.createParagraph();
		XWPFParagraph headerPara = doc.createParagraph();
		XWPFParagraph para = doc.createParagraph();

		XWPFRun lineRun = para.createRun();
		XWPFRun headerRun = headerPara.createRun();
		logoPara.createRun();

		logoPara.setAlignment(ParagraphAlignment.LEFT);
		headerPara.setAlignment(ParagraphAlignment.CENTER);
		para.setAlignment(ParagraphAlignment.LEFT);

		headerRun.setBold(true);
		headerRun.setText(notesHistory);
		headerRun.setFontSize(14);
		if (fileContent.contains("\n")) {
			String[] lines = fileContent.split("\n");
			lineRun.setText(lines[0], 0);
			for (int i = 1; i < lines.length; i++) {
				lineRun.addBreak();
				lineRun.setText(lines[i]);
			}
		} else {
			lineRun.setText(fileContent, 0);
		}
		doc.enforceCommentsProtection();

		FileOutputStream out;
		try {
			out = new FileOutputStream(wordFile);
			doc.write(out);
			out.close();
		} catch (Exception ex) {
			gtnLogger.error(ex.getMessage());
		}

	}

	public void wordExport(List<String> notesList) {

		createWordDocument();

		XWPFDocument doc = new XWPFDocument();
		XWPFParagraph logoPara = doc.createParagraph();
		XWPFParagraph headerPara = doc.createParagraph();
		XWPFParagraph para = doc.createParagraph();
		XWPFRun lineRun;
		for (String string : notesList) {

			para = doc.createParagraph();
			lineRun = para.createRun();
			lineRun.setText(string);
		}
		XWPFRun headerRun = headerPara.createRun();
		logoPara.setAlignment(ParagraphAlignment.LEFT);
		headerPara.setAlignment(ParagraphAlignment.CENTER);
		para.setAlignment(ParagraphAlignment.LEFT);

		headerRun.setBold(true);
		headerRun.setText(notesHistory);
		headerRun.setFontSize(14);

		doc.enforceCommentsProtection();

		FileOutputStream out;
		try {
			out = new FileOutputStream(wordFile);
			doc.write(out);
			out.close();
		} catch (Exception ex) {
			gtnLogger.error(ex.getMessage());
		}

	}

	private void createWordDocument() {
		if (!filePath.isDirectory()) {
			filePath.mkdirs();
		}
		try {
			Files.deleteIfExists(filePath.toPath());
			if (!wordFile.createNewFile()) {
				gtnLogger.error("Unable to create New File in Path >" + filePath);
			}
		} catch (Exception ex) {
			gtnLogger.error(ex.getMessage());
		}
	}

}
