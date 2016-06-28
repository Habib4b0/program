/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.processscheduler.ui.form;

import com.stpl.app.adminconsole.common.util.CommonUIUtil;
import com.stpl.app.adminconsole.processscheduler.dto.FtpProperties;
import com.stpl.app.adminconsole.processscheduler.dto.OutboundTableDTO;
import com.stpl.app.adminconsole.processscheduler.dto.ProcessSchedulerDTO;
import com.stpl.app.adminconsole.processscheduler.logic.OutboundLogic;
import com.stpl.app.adminconsole.processscheduler.logic.ProcessSchedulerLogic;
import static com.stpl.app.adminconsole.processscheduler.logic.ProcessSchedulerLogic.getPropertyFile;
import static com.stpl.app.adminconsole.processscheduler.ui.form.CFFSearchLookUp.EXCEL_OUTBOUND_HEADER;
import static com.stpl.app.adminconsole.processscheduler.ui.form.CFFSearchLookUp.EXCEL_OUTBOUND_VISIBLE_COLUMN;
import com.stpl.app.adminconsole.processscheduler.util.CommonUtil;
import com.stpl.app.adminconsole.util.SchedulerCSVEport;
import com.stpl.app.adminconsole.util.SchedulerExcelEport;
import com.stpl.app.customtreecontainer.CustomTreeContainer;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExcelExportUtil;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.ui.ExtCustomTreeTable;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author vinodhini
 */
public class OutboundProcess {

    
    private static final Logger LOGGER = Logger.getLogger(OutboundProcess.class);
    ExtCustomTreeTable exceltable = new ExtCustomTreeTable();
    CustomTreeContainer<OutboundTableDTO> excelResultBeanContainer = new CustomTreeContainer<>(OutboundTableDTO.class);
    CustomTableHeaderDTO fullHeader = new CustomTableHeaderDTO();
    OutboundLogic outboundLogic = new OutboundLogic();
    List<ProcessSchedulerDTO> cffCsvList;
    List<OutboundTableDTO> rbCsvList;
    ProcessSchedulerLogic processSchedulerLogic = new ProcessSchedulerLogic();
    FtpProperties ftpProperties = getFtpBundleValue();
    String filePath = ftpProperties.getScripts();

    SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
    SimpleDateFormat format_time = new SimpleDateFormat("YYYY-MM-dd HH-mm-ss");
    String processName;

    public void getAutomatedOutbound(String processName) {

        try {
            LOGGER.info("__________Entering AutomatedOutbound_____________" + processName);
            this.processName=processName;
            Long startTime = System.currentTimeMillis();
            if ("RELATIONSHIP_BUILDER_OUTBOUND".equalsIgnoreCase(processName)) {
                filePath = filePath + "/Relationship_Builder/" + format.format(new Date()) + "/";
                LOGGER.info(" Relationship builder filePath=" + filePath);
                createWorkSheet();
            }
            if ("HIERARCHY_DEFINITION_OUTBOUND".equalsIgnoreCase(processName)) {
                filePath = filePath + "/Hierarchy_Definition/" + format.format(new Date()) + "";
                String excelName = "Hierarchy Definition Outbound Process";
                LOGGER.info("Hierarchy filePath=" + filePath);
                    configureHierarchyExcel(StringUtils.EMPTY,true);
                    loadHierarchyExcel(StringUtils.EMPTY,true);
                    SchedulerExcelEport exp = new SchedulerExcelEport(new ExtCustomTableHolder(exceltable), excelName, excelName, "Hierarchy_Definition_" + format_time.format(new Date()) + ".xls", false, filePath);
                    exp.export();
            }
            if ("CFF_OUTBOUND_INTERFACE".equalsIgnoreCase(processName)) {
                filePath = filePath + "/Cff/" + format.format(new Date());
                LOGGER.info("CFF filePath=" + filePath);
                createWorkSheet();

            }
            Long endTime = System.currentTimeMillis();
            LOGGER.info("__________Total Time taken===in milli sesconds=" + (endTime - startTime));
            LOGGER.info("__________Total Time taken==in seconds==" + TimeUnit.MILLISECONDS.toSeconds(endTime - startTime));
            LOGGER.info("__________Ending AutomatedOutbound_____________" + processName);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    private void configureHierarchyExcel(String checkedIds,boolean isScheduled) {
        exceltable = new ExtCustomTreeTable();
        excelResultBeanContainer = new CustomTreeContainer<>(OutboundTableDTO.class);
        fullHeader = new CustomTableHeaderDTO();
        int maxLevel = 0;
        List levelCount = outboundLogic.hierarchyQueryResults(checkedIds, "getHierarchyDefinitionOutboundAllMaxLevel",isScheduled);
       
        if (levelCount != null && !levelCount.isEmpty()) {
            maxLevel = Integer.parseInt((StringUtils.isNotBlank(String.valueOf(levelCount.get(0))) ? String.valueOf(levelCount.get(0)) : "0"));
        }
        LOGGER.info(" Maximum Level=" + maxLevel);
        CommonUtil.getHdOutboundCalculatedColumns(fullHeader, maxLevel);
        excelResultBeanContainer.setColumnProperties(fullHeader.getProperties());
        exceltable.setRefresh(false);
        exceltable.setVisible(false);
        exceltable.setContainerDataSource(excelResultBeanContainer);
        exceltable.setVisibleColumns(fullHeader.getSingleColumns().toArray());
        exceltable.setColumnHeaders(fullHeader.getSingleHeaders().toArray(new String[fullHeader.getSingleHeaders().size()]));
        exceltable.setDoubleHeaderVisible(true);
        exceltable
                .setDoubleHeaderVisibleColumns(fullHeader.getDoubleColumns().toArray());
        exceltable
                .setDoubleHeaderColumnHeaders(fullHeader.getDoubleHeaders().toArray(new String[fullHeader.getDoubleHeaders().size()]));

        exceltable.setDoubleHeaderMap(fullHeader.getDoubleHeaderMaps());
        exceltable.setRefresh(true);
    }

    private void loadHierarchyExcel(String checkedIds,boolean isScheduled) {
        excelResultBeanContainer.removeAllItems();
        List<OutboundTableDTO> resultList = outboundLogic.hierarchyDefinitionOutbound(checkedIds, "getHierarchyDefinitionOuboundScheduleResults",isScheduled);
        excelResultBeanContainer.addAll(resultList);
    }
  
    public void createWorkSheet() throws SystemException, PortalException, Exception {
        long recordCount;
        try {
            if ("CFF_OUTBOUND_INTERFACE".equalsIgnoreCase(processName)) {
                String csvName = "CFF_Outbound";
                Set checkedCffsSet = outboundLogic.getAllCffIds();
                cffCsvList = ProcessSchedulerLogic.getResultsForCffOutbound(checkedCffsSet);
                recordCount = cffCsvList.size();
                SchedulerCSVEport.createWorkSheet(EXCEL_OUTBOUND_HEADER, recordCount, this, filePath, csvName + format_time.format(new Date()));
            } else if ("RELATIONSHIP_BUILDER_OUTBOUND".equalsIgnoreCase(processName)) {
                String csvName = "Relationship_Builder_Outbound";
                rbCsvList = outboundLogic.getRelationShipSheduledResults();
                recordCount = rbCsvList.size();
                SchedulerCSVEport.createWorkSheet(CommonUIUtil.RELATIONSHIP_OUTBOUND_EXCEL_HEADER, recordCount, this, filePath, csvName + format_time.format(new Date()));
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
    }

      
    }

    public void createWorkSheetContent(final Integer start, final Integer end, final PrintWriter printWriter) throws SystemException, PortalException, Exception {
        try {
            if (end != 0) {
                if ("CFF_OUTBOUND_INTERFACE".equalsIgnoreCase(processName)) {
                for (int headerCount = 0; headerCount < EXCEL_OUTBOUND_HEADER.length; headerCount++) {
                    if (headerCount < EXCEL_OUTBOUND_HEADER.length - 1) {
                        printWriter.print(EXCEL_OUTBOUND_HEADER[headerCount] + ExcelExportUtil.COMMA);
                    } else {
                        printWriter.println(EXCEL_OUTBOUND_HEADER[headerCount]);
                    }
                }
                SchedulerCSVEport.createFileContent(EXCEL_OUTBOUND_VISIBLE_COLUMN, cffCsvList, printWriter);
                }else if ("RELATIONSHIP_BUILDER_OUTBOUND".equalsIgnoreCase(processName)) {
                  for (int headerCount = 0; headerCount < CommonUIUtil.RELATIONSHIP_OUTBOUND_EXCEL_HEADER.length; headerCount++) {
                    if (headerCount < CommonUIUtil.RELATIONSHIP_OUTBOUND_EXCEL_HEADER.length - 1) {
                        printWriter.print(CommonUIUtil.RELATIONSHIP_OUTBOUND_EXCEL_HEADER[headerCount] + ExcelExportUtil.COMMA);
                    } else {
                        printWriter.println(CommonUIUtil.RELATIONSHIP_OUTBOUND_EXCEL_HEADER[headerCount]);
                    }
                }
                    SchedulerCSVEport.createFileContent(CommonUIUtil.RELATIONSHIP_OUTBOUND_EXCEL_COLUMNS, rbCsvList, printWriter); 
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }
    public static FtpProperties getFtpBundleValue() {
        LOGGER.info("getFtpBundleValue===================>starts");
        FtpProperties ftpProperties = new FtpProperties();
        try {
            java.util.Properties prop = getPropertyFile(FtpProperties.FTP_CONFIGURATION_PATH);
            ftpProperties.setScripts(prop.getProperty("Outbound_Excel"));
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.info("getFtpBundleValue===================>ends");
        return ftpProperties;
    }

}
