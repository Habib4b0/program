/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.ifs.util;

import com.stpl.ifs.ui.util.NumericConstants;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

/**
 *
 * @author Elangovan
 */
public class ExportWord {

    private File filePath;
    private File wordFile;
    private String NOTES_HISTORY = "Notes History";
    public final static String DOC_EXT = ".doc";
    public final static String DOCX_EXT = ".docx";

    public ExportWord(File filePath, File wordFile) {
        this.filePath = filePath;
        this.wordFile = wordFile;
    }

    public void export(String fileContent) {


        if (filePath.isDirectory() == false) {
            filePath.mkdirs();
        }

        if (wordFile.exists() != true) {
            try {
                wordFile.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(ExportWord.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            wordFile.delete();
        }


        XWPFDocument doc = new XWPFDocument();
        XWPFParagraph logoPara = doc.createParagraph();
        XWPFParagraph headerPara = doc.createParagraph();
        XWPFParagraph para = doc.createParagraph();

        XWPFRun lineRun = para.createRun();
        XWPFRun headerRun = headerPara.createRun();

        logoPara.setAlignment(ParagraphAlignment.LEFT);
        headerPara.setAlignment(ParagraphAlignment.CENTER);
        para.setAlignment(ParagraphAlignment.LEFT);


        headerRun.setBold(true);
        headerRun.setText(NOTES_HISTORY);
        headerRun.setFontSize(NumericConstants.FOURTEEN);
   if (fileContent.contains("\n")) {
                String[] lines = fileContent.split("\n");
                lineRun.setText(lines[0], 0);
                for(int i=1;i<lines.length;i++){
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
            Logger.getLogger(ExportWord.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
     public void wordExport(List<String> notesList) {

        if (filePath.isDirectory() == false) {
            filePath.mkdirs();
        }

        if (wordFile.exists() != true) {
            try {
                wordFile.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(ExportWord.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            wordFile.delete();
        }

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
        headerRun.setText(NOTES_HISTORY);
        headerRun.setFontSize(NumericConstants.FOURTEEN);

        doc.enforceCommentsProtection();

        FileOutputStream out;
        try {
            out = new FileOutputStream(wordFile);
            doc.write(out);
            out.close();
        } catch (Exception ex) {
            Logger.getLogger(ExportWord.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
