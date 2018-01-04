package com.stpl.ifs.util;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.addons.tableexport.TemporaryFileDownloadResource;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.UI;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 * This is class file is having generic method for excel export
 *
 * @author Abhiram
 */
public class ExcelExportforBB {

    private static final String createWorkSheetContent = "createWorkSheetContent";
    private static final String TMP = "tmp";
    private static final String SESSION_ID = "sessionId";
    final static SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    public final static String QUOTE = "\"";
    private static final Logger LOGGER = Logger.getLogger(ExcelExportforBB.class.getName());

    /**
     * This method is used to create a worksheet and logic for writing into the
     * file
     *
     * @param headers
     * @param recordCount
     * @param obj
     * @param ui
     * @param moduleName
     * @throws SystemException
     * @throws PortalException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public static void createWorkSheet(String[] headers, long recordCount, Object obj, UI ui, String moduleName) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException  {
        int maxRecords = ExcelExportUtil.maxRecords;

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
            createHeaderRow(pw, headers);
            pw.flush();
            pw.close();
            writer.close();
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(obj.getClass().getName()).log(Level.SEVERE, null, ex);
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
                    Method method;
                    method = obj.getClass().getMethod(createWorkSheetContent, Integer.class, Integer.class, PrintWriter.class);
                    method.invoke(obj, start, end, pw);
                    remainingCount = remainingCount - (end - start);
                    start = end;

                } while (remainingCount > 0);

            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(obj.getClass().getName()).log(Level.SEVERE, null, ex);
            } finally {
                pw.flush();
                pw.close();
                try {
                    writer.close();
                } catch (IOException ex) {
                    java.util.logging.Logger.getLogger(obj.getClass().getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        sendConvertedFileToUser(UI.getCurrent(), tempFile, moduleName + ExcelExportUtil.CSV);
    }

    public static File createSecondThreadContent(String[] headers, long recordCount, Object obj, UI ui, String moduleName) throws SystemException, PortalException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
        int maxcount = (int) recordCount - NumericConstants.FIVE_LAKHS;
        int start = NumericConstants.FIVE_LAKHS, end = NumericConstants.FIVE_LAKHS;
        File tempFile = null;
        FileWriter writer = null;
        PrintWriter pw = null;

        try {
            tempFile = File.createTempFile(TMP + VaadinSession.getCurrent().getAttribute(SESSION_ID), ExcelExportUtil.CSV);
            writer = new FileWriter(tempFile, true);
            pw = new PrintWriter(writer);
            createHeaderRow(pw, headers);
            pw.flush();
            pw.close();
            writer.close();
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(obj.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        try {
            writer = new FileWriter(tempFile, true);
            pw = new PrintWriter(writer);
            int reccount = NumericConstants.FIFTY_THOUSAND;
            if (maxcount > NumericConstants.FIVE_LAKHS) {
                maxcount = NumericConstants.FIVE_LAKHS;
            }
            int remain = maxcount;
            int worksheetCount = (int) (maxcount / reccount);

            if (maxcount % reccount != 0 || maxcount < reccount) {
                worksheetCount++;

            }
            for (int i = 1; i <= worksheetCount; i++) {
                if (remain < reccount) {
                    end = end + remain;
                } else {
                    end = end + reccount;
                    remain = remain - reccount;
                }
                Method method;
                method = obj.getClass().getMethod(createWorkSheetContent, Integer.class, Integer.class, PrintWriter.class);

                method.invoke(obj, start, end, pw);
                start = end;

            }
        } catch (Exception e) {
            java.util.logging.Logger.getLogger(obj.getClass().getName()).log(Level.SEVERE, null, e);
        } finally {
            pw.flush();
            pw.close();
            try {
                writer.close();
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(obj.getClass().getName()).log(Level.SEVERE, null, ex);
            }
        }
        return tempFile;
    }

    public static File createFirstThreadContent(String[] headers, long recordCount, Object obj, UI ui, String moduleName) throws SystemException, PortalException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
        int maxcount = (int) recordCount;

        int start = 0, end = 0;
        File tempFile = null;
        FileWriter writer = null;
        PrintWriter pw = null;

        try {
            tempFile = File.createTempFile(TMP + VaadinSession.getCurrent().getAttribute(SESSION_ID), ExcelExportUtil.CSV);
            writer = new FileWriter(tempFile, true);
            pw = new PrintWriter(writer);
            createHeaderRow(pw, headers);
            pw.flush();
            pw.close();
            writer.close();
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(obj.getClass().getName()).log(Level.SEVERE, null, ex);
        }
        try {
            writer = new FileWriter(tempFile, true);
            pw = new PrintWriter(writer);
            int reccount = NumericConstants.FIFTY_THOUSAND;
            if (maxcount > NumericConstants.FIVE_LAKHS) {
                maxcount = NumericConstants.FIVE_LAKHS;
            }
            int remain = maxcount;
            int worksheetCount = (int) (maxcount / reccount);

            if (maxcount % reccount != 0 || maxcount < reccount) {
                worksheetCount++;
            }
            for (int i = 1; i <= worksheetCount; i++) {
                if (remain < reccount) {
                    end = end + remain;
                } else {
                    end = end + reccount;
                    remain = remain - reccount;
                }
                Method method;
                method = obj.getClass().getMethod(createWorkSheetContent, Integer.class, Integer.class, PrintWriter.class);
                method.invoke(obj, start, end, pw);
                start = end;
            }
        } catch (Exception e) {
            java.util.logging.Logger.getLogger(obj.getClass().getName()).log(Level.SEVERE, null, e);
        } finally {
            pw.flush();
            pw.close();
            try {
                writer.close();
            } catch (IOException ex) {
                java.util.logging.Logger.getLogger(obj.getClass().getName()).log(Level.SEVERE, null, ex);
            }
        }
        return tempFile;
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
            exportFileName = exportFileName.replace(" ", "_");
            resource
                    = new TemporaryFileDownloadResource(app, exportFileName, ExcelExportUtil.EXCEL_MIME_TYPE, fileToExport);
            app.getPage().open(resource, ExcelExportUtil.WINDOW_NAME, true);
        } catch (final FileNotFoundException e) {
            LOGGER.error(e);
            return false;
        }
        return true;
    }

    public static void createFileContent(Object[] visibleColumns, List searchList, PrintWriter printWriter) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        for (Object value : searchList) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < visibleColumns.length; i++) {
                String methodStringValue;
                Object methodValue = getFieldValue(value, visibleColumns[i].toString());
                if (methodValue != null &&(methodValue instanceof Timestamp || methodValue instanceof Date)) {
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

    private static Object getFieldValue(Object myDTO, String variable) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Field field = null;
        Object value = null;
        try {
            if (variable.matches("[a-zA-Z0-9_]+\\.\\d+$")) {
                Class[] obj = new Class[]{String.class};
                Method method = myDTO.getClass().getSuperclass().getDeclaredMethod("getPropertyValue", obj);
                if (method != null) {
                    value = method.invoke(myDTO, (Object[]) new String[]{variable});
                }
            } else {
                field = myDTO.getClass().getDeclaredField(variable);
                field.setAccessible(true);
                value = field.get(myDTO);
            }
            return value;
        } catch (NoSuchFieldException ex) {
            Class[] obj = new Class[]{String.class};
            Method method = myDTO.getClass().getSuperclass().getDeclaredMethod("getPropertyValue", obj);
            if (method != null) {
                value = method.invoke(myDTO, (Object[]) new String[]{variable});
            }
            LOGGER.error(ex);
            return value;
        }
    }
    
}