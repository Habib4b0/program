/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.addons.tableexport.TableHolder;
import com.stpl.ifs.util.GtnFileUtil;

/**
 *
 * @author maheshj
 */
public class SchedulerExcelEport extends ExcelExport {

	private String serverPAth = "";
	private static final Logger LOGGER = LoggerFactory.getLogger(SchedulerExcelEport.class);

	public SchedulerExcelEport(final TableHolder tableHolder, final String sheetName, final String reportTitle,
			final String exportFileName, final boolean hasTotalsRow, final String serverPAth) {
		super(tableHolder, new HSSFWorkbook(), sheetName, reportTitle, exportFileName, hasTotalsRow);
		this.serverPAth = serverPAth;
	}

	   @Override
    public boolean sendConverted() {
        File tempFile;
        tempFile = GtnFileUtil.getFile(serverPAth + "/" + exportFileName);
        if (!tempFile.exists()) {
            tempFile.getParentFile().mkdirs();

        }

        try (FileOutputStream fileOut = new FileOutputStream(tempFile)) {
            workbook.write(fileOut);
        } catch (final IOException e) {
            LOGGER.error(e.getMessage());
            return false;
        } finally {
            tempFile.deleteOnExit();

        }
        return true;
    }

}
