/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.processscheduler.ui.form;

import static com.stpl.app.adminconsole.processscheduler.logic.ProcessSchedulerLogic.FTP_PROPERTIES_PATH;
import static com.stpl.app.adminconsole.processscheduler.logic.ProcessSchedulerLogic.getPropertyFile;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtContainer;
import org.asi.container.ExtTreeContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stpl.app.adminconsole.common.util.CommonUIUtil;
import com.stpl.app.adminconsole.processscheduler.dto.FtpProperties;
import com.stpl.app.adminconsole.processscheduler.dto.OutboundTableDTO;
import com.stpl.app.adminconsole.processscheduler.logic.OutboundLogic;
import com.stpl.app.adminconsole.processscheduler.util.CommonUtil;
import com.stpl.app.adminconsole.util.SchedulerCSVEport;
import com.stpl.app.adminconsole.util.SchedulerExcelEport;
import com.stpl.app.adminconsole.util.StringConstantUtils;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.ExcelExportUtil;
import com.stpl.ifs.util.ExtCustomTableHolder;
import org.asi.ui.extfilteringtable.ExtCustomTreeTable;

/**
 *
 * @author vinodhini
 */
public class OutboundProcess {

    private static final Logger LOGGER = LoggerFactory.getLogger(OutboundProcess.class);
    private ExtCustomTreeTable exceltable = new ExtCustomTreeTable();
    private ExtTreeContainer<OutboundTableDTO> excelResultBeanContainer = new ExtTreeContainer<>(OutboundTableDTO.class,ExtContainer.DataStructureMode.MAP);
    private CustomTableHeaderDTO fullHeader = new CustomTableHeaderDTO();
    private OutboundLogic outboundLogic = new OutboundLogic();
    private List<OutboundTableDTO> rbCsvList;
    private FtpProperties ftpProperties = getFtpBundleValue();
    private String filePath = ftpProperties.getScripts();

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat formattime = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
    private String processName;

    public OutboundProcess() {
    	LOGGER.info("OutboundProcess is invoked");
	}
    public void getAutomatedOutbound(String processName) {

        try {
            LOGGER.debug("__________Entering AutomatedOutbound_____________= {}" , processName);
            this.processName = processName;
            Long startTime = System.currentTimeMillis();
            if (StringConstantUtils.RELATIONSHIP_BUILDER_OUTBOUND.equalsIgnoreCase(processName)) {
                filePath = filePath + "/Relationship_Builder/" + format.format(new Date()) + "/";
                LOGGER.debug(" Relationship builder filePath= {}" , filePath);
                createWorkSheet();
            }
            if ("HIERARCHY_DEFINITION_OUTBOUND".equalsIgnoreCase(processName)) {
                filePath = filePath + "/Hierarchy_Definition/" + format.format(new Date()) + "";
                String excelName = "Hierarchy Definition Outbound Process";
                LOGGER.debug("Hierarchy filePath= {}" , filePath);
                configureHierarchyExcel(StringUtils.EMPTY, true);
                loadHierarchyExcel(StringUtils.EMPTY, true);
                SchedulerExcelEport exp = new SchedulerExcelEport(new ExtCustomTableHolder(exceltable), excelName, excelName, "Hierarchy_Definition_" + formattime.format(new Date()) + ".xls", false, filePath);
                exp.export();
            }
            Long endTime = System.currentTimeMillis();
            LOGGER.debug("__________Total Time taken===in milli seconds= {}" , (endTime - startTime));
            LOGGER.debug("__________Total Time taken==in seconds= {}" , TimeUnit.MILLISECONDS.toSeconds(endTime - startTime));
            LOGGER.debug("__________Ending AutomatedOutbound_____________= {}" , processName);
        } catch (Exception ex) {
           LOGGER.error(ex.getMessage());
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
        LOGGER.debug(" Maximum Level= {}" , maxLevel);
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

    public void createWorkSheet() {
        long recordCount;
        try {
            if (StringConstantUtils.RELATIONSHIP_BUILDER_OUTBOUND.equalsIgnoreCase(processName)) {
                String csvName = "Relationship_Builder_Outbound";
                rbCsvList = outboundLogic.getRelationShipSheduledResults();
                recordCount = rbCsvList.size();
                SchedulerCSVEport.createWorkSheet(recordCount, this, filePath, csvName + formattime.format(new Date()));
            }
        } catch (Exception ex) {
           LOGGER.error(ex.getMessage());
        }

    }

    public void createWorkSheetContent( final Integer start, final Integer end, final PrintWriter printWriter) {
        try {
            LOGGER.info("Start index of record is= {} ", start);
            if (end != 0 && StringConstantUtils.RELATIONSHIP_BUILDER_OUTBOUND.equalsIgnoreCase(processName)) {
                    for (int headerCount = 0; headerCount < CommonUIUtil.getrelationshipOutboundExcelHeader().length; headerCount++) {
                        if (headerCount < CommonUIUtil.getrelationshipOutboundExcelHeader().length - 1) {
                            printWriter.print(CommonUIUtil.getrelationshipOutboundExcelHeader()[headerCount] + ExcelExportUtil.COMMA);
                        } else {
                            printWriter.println(CommonUIUtil.getrelationshipOutboundExcelHeader()[headerCount]);
                        }
                    }
                    SchedulerCSVEport.createFileContent(CommonUIUtil.getrelationshipOutboundExcelColumns(), rbCsvList, printWriter);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    public static FtpProperties getFtpBundleValue() {
        LOGGER.debug("getFtpBundleValue===================>starts");
        FtpProperties ftpProperties = new FtpProperties();
        try {
            String jbossHome = System.getProperty("jboss.home.dir");
            if (!"null".equals(jbossHome)) {
                String[] ftppath = jbossHome.split("jboss-7.1.1");
                if (ftppath.length != 0) {
                    LOGGER.info("{}, {}", ftppath[0] , FTP_PROPERTIES_PATH);
                    java.util.Properties prop = getPropertyFile(ftppath[0] + FTP_PROPERTIES_PATH);
                    ftpProperties.setScripts(prop.getProperty("Outbound_Excel"));
                }
            }
        } catch (Exception ex) {
           LOGGER.error(ex.getMessage());
        }
        LOGGER.debug("getFtpBundleValue===================>ends");
        return ftpProperties;
    }

}
