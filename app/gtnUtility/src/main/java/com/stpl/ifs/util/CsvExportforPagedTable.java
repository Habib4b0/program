package com.stpl.ifs.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.addons.tableexport.TemporaryFileDownloadResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.jboss.logging.Logger;

/**
 * This is class file is having generic method for excel export
 *
 * @author Abhiram
 */
public class CsvExportforPagedTable {

    private static final String TMP = "tmp";
    private static final String SESSION_ID = "sessionId";
    final static SimpleDateFormat dateFormat = new SimpleDateFormat(ExcelExportUtil.DATE_FORMAT, Locale.getDefault());
    public final static String QUOTE = "\"";
    private static final Logger LOGGER = Logger.getLogger(CsvExportforPagedTable.class.getName());

    /**
     * This method is used to create a worksheet and logic for writing into the
     * file
     *
     * @param visibleHeaders
     * @param visibleColumns
     * @param tableLogic
     * @param fileName
     * @throws SystemException
     * @throws PortalException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public static void createWorkSheet(String[] visibleHeaders, Object[] visibleColumns, PageTableLogic tableLogic, String fileName)
            throws SystemException, PortalException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
        int maxRecords = ExcelExportUtil.maxRecords;
        int recordCount = tableLogic.getCount();

        int worksheetCount = (int) (recordCount / maxRecords);

        if (recordCount % maxRecords != 0 || recordCount < maxRecords) {
            worksheetCount++;

        }

        int remainingCount;
        int iterationCount = ExcelExportUtil.iterationCount;
        int start = 0, end = 0;
        File tempFile = null;
        FileWriter writer = null;
        PrintWriter pw = null;

        try {
            tempFile = File.createTempFile(TMP + VaadinSession.getCurrent().getAttribute(SESSION_ID), ExcelExportUtil.CSV);
            writer = new FileWriter(tempFile, true);
            pw = new PrintWriter(writer);
            createHeaderRow(pw, visibleHeaders);
            pw.flush();
            pw.close();
            writer.close();
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(CsvExportforPagedTable.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int worksheetNo = 1; worksheetNo <= worksheetCount; worksheetNo++) {
            try {

                writer = new FileWriter(tempFile, true);
                pw = new PrintWriter(writer);

                if (recordCount < maxRecords) {
                    remainingCount = (int) recordCount;
                } else {
                    remainingCount = maxRecords;
                }

                do {
                    if (remainingCount > iterationCount) {
                        end = end + iterationCount;
                    } else {
                        end = end + remainingCount;
                    }

                    if (end > ExcelExportUtil.limit) {
                        break;
                    }
                    List searchList = new ArrayList();
                    if (end > 0) {
                        searchList = tableLogic.loadData(start, end);
                    }
                    createFileContent(visibleColumns, searchList, pw);
                    remainingCount = remainingCount - (end - start);
                    start = end;

                } while (remainingCount > 0);

            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(CsvExportforPagedTable.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                pw.flush();
                pw.close();
                try {
                    writer.close();
                } catch (IOException ex) {
                    java.util.logging.Logger.getLogger(CsvExportforPagedTable.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        fileName = fileName.replace(" ", "_"); //Added for CSV export firefox fix
        sendConvertedFileToUser(UI.getCurrent(), tempFile, fileName + ExcelExportUtil.CSV);
    }

    /**
     * Used to create header in excel sheet
     *
     * @param pw
     * @param header
     */
    private static void createHeaderRow(PrintWriter pw, String[] header) {

        for (int headerCount = 0; headerCount < header.length; headerCount++) {
            if (headerCount < header.length - 1) {
                pw.print(header[headerCount] + ExcelExportUtil.COMMA);
            } else {
                pw.println(header[headerCount]);
            }
        }
    }

    /**
     * Used to convert and help to download the file
     *
     * @param app
     * @param fileToExport
     * @param exportFileName
     * @return
     */
    public static boolean sendConvertedFileToUser(UI app, File fileToExport, String exportFileName) {
        TemporaryFileDownloadResource resource;
        try {
            resource = new TemporaryFileDownloadResource(app, exportFileName, ExcelExportUtil.EXCEL_MIME_TYPE, fileToExport);
            app.getPage().open(resource, ExcelExportUtil.WINDOW_NAME, true);
        } catch (final FileNotFoundException e) {
            LOGGER.error(e);
            return false;
        }
        return true;
    }

    private static void createFileContent(Object[] visibleColumns, List searchList, PrintWriter printWriter) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        
        for (Object value : searchList) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < visibleColumns.length; i++) {
                String methodStringValue;
                Object methodValue = getFieldValue(value, visibleColumns[i].toString());
                if (Object.class.equals(Date.class)) {
                    methodStringValue = dateFormat.format(methodValue);
                } else if (methodValue != null && StringUtils.isNotBlank(String.valueOf(methodValue)) && !"-Select One-".equals(String.valueOf(methodValue))) {
                    methodStringValue = String.valueOf(methodValue);
                } else {
                    methodStringValue = StringUtils.EMPTY;
                }

                if (visibleColumns.length == i) {
                    builder.append(QUOTE).append(methodStringValue).append(QUOTE);
                } else {
                    builder.append(QUOTE).append(methodStringValue).append(QUOTE).append(ExcelExportUtil.COMMA);
                }
            }
            printWriter.println(builder);
        }
    }

    private static Object getFieldValue(Object myDTO, String variable) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Field field = myDTO.getClass().getDeclaredField(variable);
        field.setAccessible(true);
        Object value = field.get(myDTO);
        return value;
    }

}
