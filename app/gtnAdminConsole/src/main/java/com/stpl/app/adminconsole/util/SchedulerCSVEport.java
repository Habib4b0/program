/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.util;

import com.stpl.ifs.util.ExcelExportUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.jboss.logging.Logger;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author maheshj
 */
public class SchedulerCSVEport {

    private static final String CREATE_WORK_SHEET_CONTENT = "createWorkSheetContent";
    final static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
    public final static String QUOTE = "\"";
    private static final Logger LOGGER = Logger.getLogger(SchedulerCSVEport.class);

    /**
     * This method is used to create a worksheet and logic for writing into the
     * file
     *
     * @param headers
     * @param recordCount
     * @param obj
     * @param serverPath
     * @param csvName
     * @throws SystemException
     * @throws PortalException
     * @throws NoSuchMethodException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     */
    public static void createWorkSheet(long recordCount, Object obj, String serverPath, String csvName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        int maxRecords = ExcelExportUtil.maxRecords;
        FileOutputStream fileOut = null;
        int worksheetCount = (int) (recordCount / maxRecords);

        if (recordCount % maxRecords != 0 || recordCount < maxRecords) {
            worksheetCount++;

        }

        int remainingCount;
        int iterationCount = ExcelExportUtil.iterationCount;
        int start = 0, end = 0;
        File tempFile = null;
        PrintWriter pw = null;

        try {

            tempFile = new File(serverPath + "/" + csvName + ExcelExportUtil.CSV);
            if (!tempFile.exists()) {
                tempFile.getParentFile().mkdirs();
                tempFile.createNewFile();

            }
            fileOut = new FileOutputStream(tempFile);

            pw = new PrintWriter(fileOut);
            pw.flush();
            pw.close();
            fileOut.close();
        } catch (IOException ex) {
            LOGGER.error(ex);
        }
        for (int worksheetNo = 1; worksheetNo <= worksheetCount; worksheetNo++) {
            try {

                fileOut = new FileOutputStream(tempFile);
                pw = new PrintWriter(fileOut);

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
                    method = obj.getClass().getMethod(CREATE_WORK_SHEET_CONTENT, Integer.class, Integer.class, PrintWriter.class);
                    method.invoke(obj, start, end, pw);
                    remainingCount = remainingCount - (end - start);
                    start = end;

                } while (remainingCount > 0);

            } catch (IOException ex) {
                LOGGER.error(ex);
            } finally {
                pw.flush();
                pw.close();
                try {
                    fileOut.close();
                } catch (IOException ex) {
                    LOGGER.error(ex);
                }
            }
        }
    }

    public static void createFileContent(Object[] visibleColumns, List searchList, PrintWriter printWriter) throws NoSuchFieldException,  IllegalAccessException {
        for (Object value : searchList) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < visibleColumns.length; i++) {
                String methodStringValue;
                Object methodValue = getFieldValue(value, visibleColumns[i].toString());
                if (methodValue != null && (methodValue.getClass().equals(Timestamp.class) || methodValue.getClass().equals(Date.class))) {
                    methodStringValue = DATE_FORMAT.format(methodValue);
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

    private static Object getFieldValue(Object myDTO, String variable) throws NoSuchFieldException, IllegalAccessException {
        Field field = myDTO.getClass().getDeclaredField(variable);
        field.setAccessible(true);
        Object value = field.get(myDTO);
        return value;
    }
}
