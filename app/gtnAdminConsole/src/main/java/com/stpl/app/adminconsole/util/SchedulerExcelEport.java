/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.util;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.addons.tableexport.TableHolder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jboss.logging.Logger;

/**
 *
 * @author maheshj
 */
public class SchedulerExcelEport extends ExcelExport {

    String serverPAth = "";
    private static final Logger LOGGER = Logger.getLogger(SchedulerExcelEport.class);

    public SchedulerExcelEport(final TableHolder tableHolder, final String sheetName, final String reportTitle,
            final String exportFileName, final boolean hasTotalsRow, final String serverPAth) {
        super(tableHolder, new HSSFWorkbook(), sheetName, reportTitle, exportFileName, hasTotalsRow);
        this.serverPAth = serverPAth;
    }

    @Override
    public boolean sendConverted() {
        File tempFile = null;
        FileOutputStream fileOut = null;
        try {

            tempFile = new File(serverPAth + "/" + exportFileName);
            if (!tempFile.exists()) {
                tempFile.getParentFile().mkdirs();

            }
            fileOut = new FileOutputStream(tempFile);

            workbook.write(fileOut);


        } catch (final IOException e) {
            LOGGER.error(e);
            return false;
        } finally {
            tempFile.deleteOnExit();
            try {
                fileOut.close();
            } catch (final IOException e) {
                LOGGER.error(e);
            }
        }
        return true;
    }

}
