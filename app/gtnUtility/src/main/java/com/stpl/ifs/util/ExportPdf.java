/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.ifs.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import com.stpl.ifs.ui.util.NumericConstants;
import java.io.File;
import java.io.FileOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;

/**
 *
 * @author soundarrajan
 */
public class ExportPdf {

    private File filePath;
    private String header;
    private File pdfFile;
    public final static String PDF_EXT = ".pdf";

    public ExportPdf(String header, File filePath, File logo, File pdfFile) {
        this.header = header;
        this.pdfFile = pdfFile;
        this.filePath = filePath;
    }

    public void export(String fileContent) {


        Font fontHeader = FontFactory.getFont("Times-Roman", NumericConstants.FOURTEEN, Font.BOLD);
        Font fontNotes = FontFactory.getFont("Times-Roman", NumericConstants.TWELVE, Font.NORMAL);
        Paragraph headerPara = new Paragraph();
        Paragraph notesPara = new Paragraph();

        notesPara.add(fileContent);
        notesPara.setFont(fontNotes);
        notesPara.setAlignment(Element.ALIGN_LEFT);


        headerPara.setAlignment(Element.ALIGN_CENTER);
        headerPara.add(new Phrase(header));
        headerPara.setFont(fontHeader);



        if (filePath.isDirectory() == false) {
            filePath.mkdirs();
        }
        if (pdfFile.exists() != true) {
            try {
                pdfFile.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(ExportPdf.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ExportPdf.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
}
