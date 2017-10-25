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
import static com.stpl.app.adminconsole.processscheduler.logic.ProcessSchedulerLogic.FTP_PROPERTIES_PATH;
import static com.stpl.app.adminconsole.processscheduler.logic.ProcessSchedulerLogic.getPropertyFile;
import com.stpl.app.adminconsole.processscheduler.util.CommonUtil;
import com.stpl.app.adminconsole.util.SchedulerCSVEport;
import com.stpl.app.adminconsole.util.SchedulerExcelEport;
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
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtContainer;
import org.asi.container.ExtTreeContainer;
import org.jboss.logging.Logger;

/**
 *
 * @author vinodhini
 */
public class OutboundProcess {

    private static final Logger LOGGER = Logger.getLogger(OutboundProcess.class);
    ExtCustomTreeTable exceltable = new ExtCustomTreeTable();
    ExtTreeContainer<OutboundTableDTO> excelResultBeanContainer = new ExtTreeContainer<>(OutboundTableDTO.class,ExtContainer.DataStructureMode.MAP);
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
            LOGGER.debug("__________Entering AutomatedOutbound_____________" + processName);
            this.processName = processName;
            Long startTime = System.currentTimeMillis();
            if ("RELATIONSHIP_BUILDER_OUTBOUND".equalsIgnoreCase(processName)) {
                filePath = filePath + "/Relationship_Builder/" + format.format(new Date()) + "/";
                LOGGER.debug(" Relationship builder filePath=" + filePath);
                createWorkSheet();
            }
            if ("HIERARCHY_DEFINITION_OUTBOUND".equalsIgnoreCase(processName)) {
                filePath = filePath + "/Hierarchy_Definition/" + format.format(new Date()) + "";
                String excelName = "Hierarchy Definition Outbound Process";
                LOGGER.debug("Hierarchy filePath=" + filePath);
                configureHierarchyExcel(StringUtils.EMPTY, true);
                loadHierarchyExcel(StringUtils.EMPTY, true);
                SchedulerExcelEport exp = new SchedulerExcelEport(new ExtCustomTableHolder(exceltable), excelName, excelName, "Hierarchy_Definition_" + format_time.format(new Date()) + ".xls", false, filePath);
                exp.export();
            }
            Long endTime = System.currentTimeMillis();
            LOGGER.debug("__________Total Time taken===in milli sesconds=" + (endTime - startTime));
            LOGGER.debug("__________Total Time taken==in seconds==" + TimeUnit.MILLISECONDS.toSeconds(endTime - startTime));
            LOGGER.debug("__________Ending AutomatedOutbound_____________" + processName);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    private void configureHierarchyExcel(String checkedIds, boolean isScheduled) {
        exceltable = new ExtCustomTreeTable();
        excelResultBeanContainer = new ExtTreeContainer<>(OutboundTableDTO.class,ExtContainer.DataStructureMode.MAP);
        fullHeader = new CustomTableHeaderDTO();
        int maxLevel = 0;
        List levelCount = outboundLogic.hierarchyQueryResults(checkedIds, "getHierarchyDefinitionOutboundAllMaxLevel", isScheduled);

        if (levelCount != null && !levelCount.isEmpty()) {
            maxLevel = Integer.parseInt(StringUtils.isNotBlank(String.valueOf(levelCount.get(0))) ? String.valueOf(levelCount.get(0)) : "0");
        }
        LOGGER.debug(" Maximum Level=" + maxLevel);
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

    private void loadHierarchyExcel(String checkedIds, boolean isScheduled) {
        excelResultBeanContainer.removeAllItems();
        List<OutboundTableDTO> resultList = outboundLogic.hierarchyDefinitionOutbound(checkedIds, "getHierarchyDefinitionOuboundScheduleResults", isScheduled);
        excelResultBeanContainer.addAll(resultList);
    }

    public void createWorkSheet() throws SystemException, PortalException {
        long recordCount;
        try {
            if ("RELATIONSHIP_BUILDER_OUTBOUND".equalsIgnoreCase(processName)) {
                String csvName = "Relationship_Builder_Outbound";
                rbCsvList = outboundLogic.getRelationShipSheduledResults();
                recordCount = rbCsvList.size();
                SchedulerCSVEport.createWorkSheet(recordCount, this, filePath, csvName + format_time.format(new Date()));
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }

    }

    public void createWorkSheetContent(final Integer start,final Integer end, final PrintWriter printWriter) throws SystemException, PortalException {
        try {
            LOGGER.info("Start index of record is : "+start);
            if (end != 0 && "RELATIONSHIP_BUILDER_OUTBOUND".equalsIgnoreCase(processName)) {
                    for (int headerCount = 0; headerCount < CommonUIUtil.RELATIONSHIP_OUTBOUND_EXCEL_HEADER.length; headerCount++) {
                        if (headerCount < CommonUIUtil.RELATIONSHIP_OUTBOUND_EXCEL_HEADER.length - 1) {
                            printWriter.print(CommonUIUtil.RELATIONSHIP_OUTBOUND_EXCEL_HEADER[headerCount] + ExcelExportUtil.COMMA);
                        } else {
                            printWriter.println(CommonUIUtil.RELATIONSHIP_OUTBOUND_EXCEL_HEADER[headerCount]);
                        }
                    }
                    SchedulerCSVEport.createFileContent(CommonUIUtil.RELATIONSHIP_OUTBOUND_EXCEL_COLUMNS, rbCsvList, printWriter);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    public static FtpProperties getFtpBundleValue() {
        LOGGER.debug("getFtpBundleValue===================>starts");
        FtpProperties ftpProperties = new FtpProperties();
        try {
            String jbossHome = System.getProperty("jboss.home.dir");
            if (!"null".equals(jbossHome)) {
                String ftppath[] = jbossHome.split("jboss-7.1.1");
                if (ftppath.length != 0) {
                    LOGGER.info(ftppath[0] + FTP_PROPERTIES_PATH);
                    java.util.Properties prop = getPropertyFile(ftppath[0] + FTP_PROPERTIES_PATH);
                    ftpProperties.setScripts(prop.getProperty("Outbound_Excel"));
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.debug("getFtpBundleValue===================>ends");
        return ftpProperties;
    }

}
