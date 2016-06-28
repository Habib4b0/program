/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.logic;

import com.stpl.app.accountclose.dao.BaseRateDAO;
import com.stpl.app.accountclose.dao.impl.BaseRateDAOImpl;
import com.stpl.app.accountclose.dto.ProcessDTO;
import com.stpl.app.accountclose.schedule.dto.FtpProperties;
import com.stpl.app.accountclose.utils.CommonUtils;
import com.stpl.app.accountclose.utils.Constants;
import com.stpl.app.accountclose.utils.Converters;
import com.stpl.app.model.WorkflowProfile;
import com.stpl.app.service.WorkflowProfileLocalServiceUtil;
import com.vaadin.server.VaadinSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.jboss.logging.Logger;

/**
 *
 * @author hazi.s
 */
public class ProcessSchedulerLogic {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(ProcessSchedulerLogic.class);

    BaseRateDAO dao = new BaseRateDAOImpl();

    public List getSearchResult(boolean count, List<SortByColumn> columns) {
        LOGGER.info("Entering getSearchResult");
        try {
            String query = CommonUtils.workFlowQuery();
            List list = (List) dao.executeSelectQuery(query);
            if (count) {
                return list;
            } else {
                return getCustomizedSchedulerProcessing(list);
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
            return null;
        }
    }

    private List getCustomizedSchedulerProcessing(List list) throws Exception {
        LOGGER.info("Entering getCustomizedSchedulerProcessing" + list.size());
        List<ProcessDTO> returnList = new ArrayList<ProcessDTO>();
        SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy");

        if (list != null && list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                Object[] obj = (Object[]) list.get(i);
                ProcessDTO dto = new ProcessDTO();
                dto.setProcessSid(Integer.valueOf(Converters.convertNullToEmpty(obj[0])));
                dto.setProcessName(Converters.convertNullToEmpty(obj[1]));
                if ("Y".equals(Converters.convertNullToEmpty(obj[2]))) {
                    dto.setStatus("Active");
                } else {
                    dto.setStatus("Inactive");
                }
                if (obj[3] != null && obj[3] != "null") {
                    dto.setStartDate(new Date(date.format((Date) obj[3])));
                } else {
                    dto.setStartDate(null);
                }
                if (obj[4] != null && obj[4] != "null") {
                    dto.setEndDate(new Date(date.format((Date) obj[4])));
                } else {
                    dto.setEndDate(null);
                }
                dto.setFrequencyRadio(Converters.convertNullToEmpty(obj[6]));
                dto.setModifiedDate(new Date(date.format(obj[8])));
                if (obj[7] != null && obj[7] != "null") {
                    dto.setLastRunDate(new Date(date.format((Date) obj[7])));
                } else {
                    dto.setLastRunDate(null);
                }

                dto.setModifiedBy(Converters.convertNullToEmpty(obj[9]));
                dto.setCalendar(Converters.convertNullToEmpty(obj[10]));
                returnList.add(dto);
            }

        }
        LOGGER.info("Ending getCustomizedSchedulerProcessing" + returnList.size());
        return returnList;
    }

    public ProcessDTO getProcessScheduleByID(int sid) {
        try {
            LOGGER.info("Entering getProcessScheduleByID");
            ProcessDTO dto = new ProcessDTO();
            WorkflowProfile profile = WorkflowProfileLocalServiceUtil.getWorkflowProfile(sid);
            dto.setProcessSid(profile.getProcessSid());
            dto.setProcessName(profile.getProcessName());
            dto.setFrequencyRadio(profile.getFrequency());
            if ("Y".equals(profile.getActiveFlag())) {
                dto.setStatus("Active");
            } else {
                dto.setStatus("Inactive");
            }
            if ("Interval".equals(dto.getFrequencyRadio())) {
                dto.setRunHours(Converters.convertNullToEmpty(profile.getStartHour()));
                dto.setRunMinutes(Converters.convertNullToEmpty(profile.getStartMinutes()));
            } else {
                dto.setHoursOne(Converters.convertNullToEmpty(profile.getStartHour1()));
                dto.setMinutesOne(Converters.convertNullToEmpty(profile.getStartMinutes1()));
                dto.setHoursTwo(Converters.convertNullToEmpty(profile.getStartHour2()));
                dto.setMinutesTwo(Converters.convertNullToEmpty(profile.getStartMinutes2()));
                dto.setHoursThree(Converters.convertNullToEmpty(profile.getStartHour3()));
                dto.setMinutesThree(Converters.convertNullToEmpty(profile.getStartMinutes3()));
            }
            dto.setStartDate(profile.getStartDate());
            dto.setEndDate(profile.getEndDate());
            LOGGER.info("Ending getProcessScheduleByID");
            return dto;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return null;
        }

    }

    public static void update(ProcessDTO ProcessDTO) {
        try {
            LOGGER.info("Entering update");
            if (ProcessDTO.getProcessSid() != 0) {

                WorkflowProfile profile = WorkflowProfileLocalServiceUtil.getWorkflowProfile(ProcessDTO.getProcessSid());

                profile.setProcessName(ProcessDTO.getProcessName());
                profile.setFrequency(ProcessDTO.getFrequencyRadio());

                if ("Active".equals(ProcessDTO.getStatus())) {
                    profile.setActiveFlag("Y");
                } else {
                    profile.setActiveFlag("N");
                }

                if ("Interval".equals(ProcessDTO.getFrequencyRadio())) {
                    profile.setStartHour(ProcessDTO.getRunHours() == null || "null".equals(ProcessDTO.getRunHours()) ? 24 : Integer.valueOf(ProcessDTO.getRunHours()));
                    profile.setStartMinutes(ProcessDTO.getRunMinutes() == null || "null".equals(ProcessDTO.getRunMinutes()) ? 24 : Integer.valueOf(ProcessDTO.getRunMinutes()));
                } else {
                    profile.setStartHour1(ProcessDTO.getHoursOne() == null || "null".equals(ProcessDTO.getHoursOne()) ? 24 : Integer.valueOf(ProcessDTO.getHoursOne()));
                    profile.setStartHour2(ProcessDTO.getHoursTwo() == null || "null".equals(ProcessDTO.getHoursTwo()) ? 24 : Integer.valueOf(ProcessDTO.getHoursTwo()));
                    profile.setStartHour3(ProcessDTO.getHoursThree() == null || "null".equals(ProcessDTO.getHoursThree()) ? 24 : Integer.valueOf(ProcessDTO.getHoursThree()));
                    profile.setStartMinutes1(ProcessDTO.getMinutesOne() == null || "null".equals(ProcessDTO.getMinutesOne()) ? 24 : Integer.valueOf(ProcessDTO.getMinutesOne()));
                    profile.setStartMinutes2(ProcessDTO.getMinutesTwo() == null || "null".equals(ProcessDTO.getMinutesTwo()) ? 24 : Integer.valueOf(ProcessDTO.getMinutesTwo()));
                    profile.setStartMinutes3(ProcessDTO.getMinutesThree() == null || "null".equals(ProcessDTO.getMinutesThree()) ? 24 : Integer.valueOf(ProcessDTO.getMinutesThree()));
                }
                profile.setStartDate(ProcessDTO.getStartDate());
                profile.setEndDate(ProcessDTO.getEndDate());
                profile.setModifiedBy(Integer.valueOf(Converters.convertNullToEmpty(VaadinSession.getCurrent().getAttribute(Constants.USER_ID))));
                profile.setModifiedDate(new Date());
                WorkflowProfileLocalServiceUtil.updateWorkflowProfile(profile);
            }
            LOGGER.info("Ending update");
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    public void file() {
        try {
            runJob(getFtpBundleValue());
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    private void runJob(FtpProperties ftpProperties) {
        try {
            runShellScript(ftpProperties.getScripts(), FtpProperties.GL_POSTING_SH);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    private void runShellScript(String scriptPath, String scriptName) {
        try {

            String cmd = scriptPath + "/" + scriptName; // this is the command to execute in the Unix shell
            // create a process for the shell
            ProcessBuilder pb = new ProcessBuilder("bash", "-c", cmd);
            pb.redirectErrorStream(true); // use this to capture messages sent to stderr
            Process shell = pb.start();
            InputStream shellIn = shell.getInputStream(); // this captures the output from the command
            try {
                shellIn.close();
            } catch (IOException ignoreMe) {
                LOGGER.error(ignoreMe);
            }
            //Instead of "bash" you can use a program or script name (replacing "-c" and cmd wit
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    public static FtpProperties getFtpBundleValue() {
        FtpProperties ftpProperties = new FtpProperties();
        try {
            java.util.Properties prop = getPropertyFile(FtpProperties.FTP_CONFIGURATION_PATH);
            ftpProperties.setScripts(prop.getProperty("scripts"));
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return ftpProperties;
    }

    private static java.util.Properties getPropertyFile(String bpiPropLoc) {
        java.util.Properties prop = new java.util.Properties();
        try {
            FileInputStream fileIS = null;
            String filePath = "/opt";
            fileIS = new FileInputStream(filePath + bpiPropLoc);
            prop.load(fileIS);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return prop;

    }
}
