/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.processscheduler.logic;

import com.stpl.app.adminconsole.common.dto.SessionDTO;
import com.stpl.app.adminconsole.common.util.AbstractFilterLogic;
import com.stpl.app.adminconsole.dao.CommonDAO;
import com.stpl.app.adminconsole.dao.impl.CommonDAOImpl;
import com.stpl.app.adminconsole.processscheduler.dto.FtpProperties;
import com.stpl.app.adminconsole.processscheduler.dto.ProcessSchedulerDTO;
import com.stpl.app.adminconsole.processscheduler.util.CommonUtil;
import com.stpl.app.adminconsole.quartz.QuartzListener;
import com.stpl.app.adminconsole.util.CommonQueryUtils;
import com.stpl.app.adminconsole.util.CommonUtils;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.adminconsole.util.HelperListUtil;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.WorkflowProfile;
import static com.stpl.app.security.StplSecurity.userMap;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.WorkflowProfileLocalServiceUtil;
import com.stpl.ifs.util.ExcelExportUtil;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.User;
import com.stpl.portal.service.UserLocalServiceUtil;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.jboss.logging.Logger;

/**
 *
 * @author Jayaram
 */
public class ProcessSchedulerLogic {

    static CommonDAO dao = new CommonDAOImpl();
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(ProcessSchedulerLogic.class);
   // public static ResourceBundle InterfaceScripts = ResourceBundle.getBundle("properties.InterfaceScripts");
    public final static String QUOTE = "\"";

    public List getSearchResult(boolean count, List<SortByColumn> columns, int start, int offset, boolean scheduler) {
        LOGGER.info("Entering getSearchResult");
        try {
            String query = CommonUtil.workFlowQuery(start, offset, count, scheduler);
            LOGGER.info("======schedule===query=======" + query);
            List list = HelperTableLocalServiceUtil.executeSelectQuery(query);
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
        List<ProcessSchedulerDTO> returnList = new ArrayList<ProcessSchedulerDTO>();
        SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        SimpleDateFormat date1 = new SimpleDateFormat("MM/dd/yyyy");
        final HashMap<String, String> userInfoMap = (HashMap<String, String>) com.stpl.app.adminconsole.common.util.CommonUtil.getCreatedByUser();
        if (list != null && list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                Object[] obj = (Object[]) list.get(i);
                ProcessSchedulerDTO dto = new ProcessSchedulerDTO();
                dto.setProcessSid(Integer.valueOf(String.valueOf(obj[0])));
                LOGGER.info("==========obj[1]=======" + obj[1]);
                dto.setProcessName(String.valueOf(obj[1]));
                if ("Y".equals(String.valueOf(obj[2]))) {
                    dto.setStatus("Active");
                } else {
                    dto.setStatus("Inactive");
                }
                if (obj[3] != null && !ConstantsUtils.EMPTY.equals(String.valueOf(obj[3])) && !"null".equals(String.valueOf(obj[3]))) {
                    dto.setStartDate(new Date(date.format(obj[3])));
                }
                if (obj[4] != null && !ConstantsUtils.EMPTY.equals(String.valueOf(obj[4])) && !"null".equals(String.valueOf(obj[4]))) {
                    dto.setEndDate(new Date(date.format(obj[4])));
                }

                dto.setFrequencyRadio(String.valueOf(obj[5]));
                if (obj[10] != null) {
                    dto.setScheduleLastRun(date.format(obj[10]));
                }
                if (obj[7] != null) {
                    dto.setModifiedDate(new Date(date1.format(obj[7])));
                }
                dto.setModifiedBy(userInfoMap.get(obj[8]));
                dto.setProcessDisplayName(String.valueOf(obj[9]));
                returnList.add(dto);
            }
        }

        LOGGER.info("Ending getCustomizedSchedulerProcessing" + returnList.size());
        return returnList;
    }

    public ProcessSchedulerDTO getProcessScheduleByID(int sid) {
        try {
            LOGGER.info("Entering getProcessScheduleByID");
            ProcessSchedulerDTO dto = new ProcessSchedulerDTO();
            WorkflowProfile profile = WorkflowProfileLocalServiceUtil.getWorkflowProfile(sid);
            dto.setProcessSid(profile.getProcessSid());
            dto.setProcessName(profile.getProcessName());
            dto.setProcessDisplayName(profile.getProcessDisplayName());
            dto.setFrequencyRadio(profile.getFrequency());
            if ("Y".equals(profile.getActiveFlag())) {
                dto.setStatus("Active");
            } else {
                dto.setStatus("Inactive");
            }
            if ("Interval".equals(dto.getFrequencyRadio())) {
                dto.setRunHours(String.valueOf(profile.getStartHour()));
                dto.setRunMinutes(String.valueOf(profile.getStartMinutes()));
            } else {
                dto.setHoursOne(String.valueOf(profile.getStartHour1()));
                dto.setMinutesOne(String.valueOf(profile.getStartMinutes1()));
                dto.setHoursTwo(String.valueOf(profile.getStartHour2()));
                dto.setMinutesTwo(String.valueOf(profile.getStartMinutes2()));
                dto.setHoursThree(String.valueOf(profile.getStartHour3()));
                dto.setMinutesThree(String.valueOf(profile.getStartMinutes3()));
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

    public static void update(ProcessSchedulerDTO ProcessDTO, final SessionDTO sessionDTO) {
        try {
            LOGGER.info("Entering update");
            if (ProcessDTO.getProcessSid() != 0) {

                WorkflowProfile profile = WorkflowProfileLocalServiceUtil.getWorkflowProfile(ProcessDTO.getProcessSid());

                profile.setProcessName(ProcessDTO.getProcessName());
                profile.setProcessDisplayName(ProcessDTO.getProcessDisplayName());
                profile.setFrequency(ProcessDTO.getFrequencyRadio());
                if ("Active".equals(ProcessDTO.getStatus())) {
                    profile.setActiveFlag("Y");
                } else {
                    profile.setActiveFlag("N");
                }
                if ("Interval".equals(ProcessDTO.getFrequencyRadio())) {
                    profile.setStartHour(ProcessDTO.getRunHours() == null || "null".equals(ProcessDTO.getRunHours()) ? 24 : Integer.valueOf(ProcessDTO.getRunHours()));
                    profile.setStartMinutes(ProcessDTO.getRunMinutes() == null || "null".equals(ProcessDTO.getRunMinutes()) ? 60 : Integer.valueOf(ProcessDTO.getRunMinutes()));
                    profile.setStartHour1(24);
                    profile.setStartHour2(24);
                    profile.setStartHour3(24);
                    profile.setStartMinutes1(60);
                    profile.setStartMinutes2(60);
                    profile.setStartMinutes3(60);
 
                } else {
                    profile.setStartHour1(ProcessDTO.getHoursOne() == null || "null".equals(ProcessDTO.getHoursOne()) ? 24 : Integer.valueOf(ProcessDTO.getHoursOne()));
                    profile.setStartHour2(ProcessDTO.getHoursTwo() == null || "null".equals(ProcessDTO.getHoursTwo()) ? 24 : Integer.valueOf(ProcessDTO.getHoursTwo()));
                    profile.setStartHour3(ProcessDTO.getHoursThree() == null || "null".equals(ProcessDTO.getHoursThree()) ? 24 : Integer.valueOf(ProcessDTO.getHoursThree()));
                    profile.setStartMinutes1(ProcessDTO.getMinutesOne() == null || "null".equals(ProcessDTO.getMinutesOne()) ? 60 : Integer.valueOf(ProcessDTO.getMinutesOne()));
                    profile.setStartMinutes2(ProcessDTO.getMinutesTwo() == null || "null".equals(ProcessDTO.getMinutesTwo()) ? 60 : Integer.valueOf(ProcessDTO.getMinutesTwo()));
                    profile.setStartMinutes3(ProcessDTO.getMinutesThree() == null || "null".equals(ProcessDTO.getMinutesThree()) ? 60 : Integer.valueOf(ProcessDTO.getMinutesThree()));
                    profile.setStartHour(24);
                   profile.setStartMinutes(60);
                }
                profile.setStartDate(ProcessDTO.getStartDate());
                profile.setEndDate(ProcessDTO.getEndDate());
                profile.setModifiedBy(Integer.valueOf(String.valueOf(sessionDTO.getUserId())));
                profile.setModifiedDate(new Date());
                WorkflowProfileLocalServiceUtil.updateWorkflowProfile(profile);

                QuartzListener.reScheduleJobs(profile);
            }
            LOGGER.info("Ending update");
        } catch (Exception ex) {

            LOGGER.error(ex);
        }
    }

    public void runJob(FtpProperties ftpProperties, String scriptName) {
        try {
            LOGGER.info("Script Name==========================>" + scriptName);
            java.util.Properties prop = getPropertyFile(FtpProperties.FTP_CONFIGURATION_PATH);
            ftpProperties.setScripts(prop.getProperty("scripts"));
            runShellScript(ftpProperties.getScripts(),scriptName.trim());
            LOGGER.info("runShellScript===================>ends1");
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.info("runJob ends");
    }

    private boolean runShellScript(String scriptPath, String scriptName) {
        LOGGER.info("runShellScript===================>starts");
        try {

            String cmd = scriptPath + "/" + scriptName; // this is the command to execute in the Unix shell
            // create a process for the shell
            ProcessBuilder pb = new ProcessBuilder("bash", "-c", cmd);
            pb.redirectErrorStream(true); // use this to capture messages sent to stderr
            Process shell = pb.start();
            InputStream shellIn = shell.getInputStream(); // this captures the output from the command

            // close the stream
            try {
                shellIn.close();
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
            //Instead of "bash" you can use a program or script name (replacing "-c" and cmd wit
        } catch (Exception ex) {
            LOGGER.error(ex);
            return false;
        }
        LOGGER.info("runShellScript===================>ends");
        return true;
    }

    public static FtpProperties getFtpBundleValue() {
        LOGGER.info("getFtpBundleValue===================>starts");
        FtpProperties ftpProperties = new FtpProperties();
        try {
            java.util.Properties prop = getPropertyFile(FtpProperties.FTP_CONFIGURATION_PATH);
            ftpProperties.setScripts(prop.getProperty("scripts"));
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.info("getFtpBundleValue===================>ends");
        return ftpProperties;
    }

    public static java.util.Properties getPropertyFile(String bpiPropLoc) {
        LOGGER.info("getPropertyFile===================>starts");
        java.util.Properties prop = new java.util.Properties();
        try {
            FileInputStream fileIS = null;
            String filePath = "/opt";
            fileIS = new FileInputStream(bpiPropLoc);
            prop.load(fileIS);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.info("getPropertyFile===================>ends");
        return prop;

    }

    public void updateLastRun(Integer processId, boolean schedulerFlag) {
        LOGGER.info("Entering updateLastRun");
        if (processId != 0) {

            try {
                WorkflowProfile profile = WorkflowProfileLocalServiceUtil.getWorkflowProfile(processId);
                if (!schedulerFlag) {
                    profile.setManualLastRun(new Date());
                } else {
                    profile.setScheduleLastRun(new Date());
                }
                WorkflowProfileLocalServiceUtil.updateWorkflowProfile(profile);

            } catch (PortalException ex) {
                LOGGER.error(ex);
            } catch (SystemException ex) {
                LOGGER.error(ex);
            }

            LOGGER.info("ends updateLastRun");
        }
    }

    public int getSearchCount(final ProcessSchedulerDTO binderDto) throws Exception {
        int count = 0;
        List parameters;
        List resultCountList;
        String filterQuery = AbstractFilterLogic.getInstance().filterQueryGenerator(binderDto.getFilters(),
                getFilterMap()).toString();
        parameters = getParametersForCffSearch(binderDto);
        if (filterQuery != null) {
            filterQuery = filterQuery.replace("where", "AND");
            parameters.add(filterQuery);
        } else {
            parameters.add(" ");
        }
        resultCountList = (List) CommonQueryUtils.executeSelectQuery(parameters, "getCffSearchCount");
        count = getCount(resultCountList);
        LOGGER.info("Count For method" + count);
        return count;
    }

   /**
    * Gets the search results.
    * @param binderDto
    * @param isCheckAll
    * @return
    * @throws Exception 
    */
    public List<ProcessSchedulerDTO> getSearchResults(final ProcessSchedulerDTO binderDto,boolean isCheckAll) throws Exception {
        LOGGER.info("Inside Search Results");
        Boolean excelFlag = true;
        String filterQuery = AbstractFilterLogic.getInstance().filterQueryGenerator(binderDto.getFilters(), getFilterMap()).toString();
        String orderBy = AbstractFilterLogic.getInstance().orderByQueryGenerator(binderDto.getOrderByColumns(), getFilterMap()).toString();
        List<ProcessSchedulerDTO> cffMasterList = new ArrayList<ProcessSchedulerDTO>();
        List parameterslist;
        parameterslist = getParametersForCffSearch(binderDto);
        List<Object[]> resultList;
        if (filterQuery != null) {
            filterQuery = filterQuery.replace("where", "AND");
            parameterslist.add(filterQuery);
        } else {
            parameterslist.add(" ");
        }
        if (orderBy != null) {
            parameterslist.add(orderBy);
        }
        parameterslist.add(binderDto.getStartIndex());
        parameterslist.add(binderDto.getEndIndex());
        resultList = (List) CommonQueryUtils.executeSelectQuery(parameterslist, "getCffSearchResults");
        cffMasterList = getCustomizedSearchResults(resultList, excelFlag,isCheckAll);
        return cffMasterList;
    }

       /**
     *
     * @param parameters
     * @return
     */
    public List getParametersForCffSearch(ProcessSchedulerDTO binderDto) {
        List input = new ArrayList();
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        try {
            if (CommonUtils.isValidCriteria(binderDto.getFinancialForecastId())) {
                String financialForecastId = binderDto.getFinancialForecastId();
                financialForecastId = financialForecastId.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
                input.add(financialForecastId);
            } else {
                input.add(CommonUtils.CHAR_PERCENT);
            }
            if (CommonUtils.isValidCriteria(binderDto.getFinancialForecastName())) {
                String financialForecastName = binderDto.getFinancialForecastName();
                financialForecastName = financialForecastName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
                input.add(financialForecastName);
            } else {
                input.add(CommonUtils.CHAR_PERCENT);
            }
            if (binderDto.getTypeDdlb() != null) {
                input.add(binderDto.getTypeDdlb().getId());
            } else {
                input.add(CommonUtils.CHAR_PERCENT);
            }
            if (binderDto.getStatusDdlb() != null) {
                input.add(binderDto.getStatusDdlb().getId());
            } else {
                input.add(CommonUtils.CHAR_PERCENT);
            }
            if (binderDto.getCreationFromDate() != null) {
                input.add(" AND CREATED_DATE <= '" + sdf.format(binderDto.getCreationFromDate()) + "'");
            } else {
                input.add(" ");
            }
            if (binderDto.getCreationToDate() != null) {
                input.add(" AND CREATED_DATE >= '" + sdf.format(binderDto.getCreationToDate()) + "'");
            } else {
                input.add(" ");
            }
            if (binderDto.getApprovalFromDate() != null) {
                input.add(" AND APPROVED_DATE >= '" + sdf.format(binderDto.getApprovalFromDate()) + "'");
            } else {
                input.add(" ");
            }
            if (binderDto.getApprovalToDate() != null) {
                input.add(" AND APPROVED_DATE <= '" + sdf.format(binderDto.getApprovalToDate()) + "'");
            } else {
                input.add(" ");
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return input;
    }

      private Map<String, String> getFilterMap() {
        Map<String, String> filterMap = new HashMap<>();
        filterMap.put("financialForecastId", "CFF_MASTER_SID");
        filterMap.put("financialForecastName", "cff_name");
        filterMap.put("typeDesc", "cff_type");
        filterMap.put("statusDesc", "approval_status");
        filterMap.put("finalApprovalDate", "APPROVED_DATE");
        filterMap.put("approvedby", "APPROVED_BY");
        filterMap.put("activeFromDate", "ACTIVE_FROM_DATE");
        filterMap.put("activeToDate", "ACTIVE_TO_DATE");
        return filterMap;
    }

    private int getCount(List<Object[]> list) {
        if (!list.isEmpty()) {
            Object obj = list.get(0);
            int count = obj == null ? 0 : (Integer) obj;
            return count;
        }
        return 0;
    }

    /**
     * Gets the drop down list based on list name.
     *
     * @param listName the list name
     * @return the drop down list
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     * @throws Exception the exception
     */
    public static List<HelperDTO> getDropDownList(final String listName) throws SystemException, PortalException, Exception {
        LOGGER.info("Entering getDropDownList p1:" + listName);
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        final List<HelperTable> list = dao.getHelperTableDetailsByListName(listName);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable.getDescription()));

            }
        }
        LOGGER.info("return DropDownList :" + helperList.size());
        return helperList;
    }

    /**
     * Gets the customized search results.
     *
     * @param resultList the result list
     * @param excelFlag
     * @param isCheckAll
     * @return the customized search results
     * @throws java.lang.Exception
     */
    public List<ProcessSchedulerDTO> getCustomizedSearchResults(List<Object[]> resultList, Boolean excelFlag,boolean isCheckAll) throws Exception {
        List<ProcessSchedulerDTO> cffSearchDTOs = new ArrayList<>();
        ProcessSchedulerDTO cffSearchDTOLoop;
          Map<Integer, String> userInfo = getUserName();
        for (Object[] obj : resultList) {
            cffSearchDTOLoop = new ProcessSchedulerDTO();
            cffSearchDTOLoop.setFinancialForecastId(String.valueOf(obj[0]));
            if (obj[1] != null) {
                cffSearchDTOLoop.setFinancialForecastName(String.valueOf(obj[1]));
            } else {
                cffSearchDTOLoop.setFinancialForecastName(StringUtils.EMPTY);
            }
            if (obj[2] != null) {
                HelperDTO helper = HelperListUtil.getInstance().getHelperDTObyID(Integer.valueOf(String.valueOf(obj[2])));
                cffSearchDTOLoop.setTypeDesc((ConstantsUtils.SELECT_ONE).equals(helper.getDescription())?ConstantsUtils.EMPTY:helper.getDescription());
               
            }
            if (obj[3] != null) {
                HelperDTO statusdto = HelperListUtil.getInstance().getHelperDTObyID(Integer.valueOf(String.valueOf(obj[3])));
                cffSearchDTOLoop.setStatusDesc((ConstantsUtils.SELECT_ONE).equals(statusdto.getDescription())?ConstantsUtils.EMPTY:statusdto.getDescription());
            }
            cffSearchDTOLoop.setFinalApprovalDate((Date) obj[4]);
            if (obj[5] != null) {
                cffSearchDTOLoop.setApprovedBy(String.valueOf(userInfo.get(obj[5])));
            } else {
                cffSearchDTOLoop.setApprovedBy(ConstantsUtils.EMPTY);
            }
            if (obj[6] != null) {
                cffSearchDTOLoop.setActiveFromDate((Date) obj[6]);
            }
            if (obj[7] != null) {
                cffSearchDTOLoop.setActiveToDate((Date) obj[7]);
            }
            if (obj[9] != null) {
                cffSearchDTOLoop.setCreationFromDate((Date) obj[9]);
            }
            cffSearchDTOLoop.setCffMasterSid(Integer.valueOf(String.valueOf(obj[0])));
            cffSearchDTOLoop.setCheckRecord(isCheckAll);
            cffSearchDTOs.add(cffSearchDTOLoop);
        }
        return cffSearchDTOs;
    }

    /**
     * Gets the results for cff outbound
     *
     *
     * @param ids
     * @return
     */
    public static List<ProcessSchedulerDTO> getResultsForCffOutbound(Set ids) {
        List<ProcessSchedulerDTO> cffSearchDTOs = new ArrayList<ProcessSchedulerDTO>();
        try {
            StringBuilder sb = new StringBuilder();
            for (Object id : ids) {
                if (sb.length() > 0) {
                    sb.append(",").append(id);
                } else {
                    sb.append(id);
                }
            }
            List<Object[]> resultList = cffOutboundProcedure(sb.toString());
            ProcessSchedulerDTO cffSearchDTOLoop;
            for (Object[] obj : resultList) {
                cffSearchDTOLoop = new ProcessSchedulerDTO();
                cffSearchDTOLoop.setCffMasterSid(Integer.valueOf(String.valueOf(obj[0])));
                if (obj[1] != null) {
                    cffSearchDTOLoop.setFinancialForecastName(String.valueOf(obj[1]));
                } else {
                    cffSearchDTOLoop.setFinancialForecastName(StringUtils.EMPTY);
                }

                if (obj[2] != null) {
                    cffSearchDTOLoop.setCffType(obj[2].toString());
                }

                if (obj[4] != null) {
                    cffSearchDTOLoop.setProjectionName(obj[4].toString());
                }

                if (obj[5] != null) {
                    cffSearchDTOLoop.setContract(obj[5].toString());
                }

                if (obj[6] != null) {
                    cffSearchDTOLoop.setCustomer(obj[6].toString());
                }

                if (obj[7] != null) {
                    cffSearchDTOLoop.setItem(obj[7].toString());
                }
                if (obj[8] != null) {
                    cffSearchDTOLoop.setRsId(String.valueOf(obj[8]));
                }

                if (obj[9] != null) {
                    cffSearchDTOLoop.setYear(Integer.valueOf(obj[9].toString()));
                }

                if (obj[10] != null) {
                    cffSearchDTOLoop.setMonth(Integer.valueOf(obj[10].toString()));
                }

                if (obj[11] != null) {
                    cffSearchDTOLoop.setSalesDollars(obj[11].toString());
                }

                if (obj[12] != null) {
                    cffSearchDTOLoop.setSalesUnits(obj[12].toString());
                }

                if (obj[13] != null) {
                    cffSearchDTOLoop.setRebateDollars(obj[13].toString());
                }

                if (obj[14] != null) {
                    cffSearchDTOLoop.setRebateRate(obj[14].toString());
                }

                if (obj[15] != null) {
                    cffSearchDTOLoop.setRebatePerUnit(obj[15].toString());
                }

                if (obj[16] != null) {
                    cffSearchDTOLoop.setNetSalesDollars(obj[16].toString());
                }

                if (obj[17] != null) {
                    cffSearchDTOLoop.setNetProfutDollars(obj[17].toString());
                }
                if (obj[18] != null) {
                    cffSearchDTOLoop.setNetProfitUnits(obj[18].toString());
                }

                if (obj[19] != null) {
                    cffSearchDTOLoop.setCostOfGoodsSoldUnits(obj[19].toString());
                }

                if (obj[20] != null) {
                    cffSearchDTOLoop.setCostOfGoodsSoldDollars(obj[20].toString());
                }

                cffSearchDTOs.add(cffSearchDTOLoop);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return cffSearchDTOs;
    }

    public static void CreateWorkSheetforCff(Set ids, final PrintWriter printWriter) {
        try {

            StringBuilder sb = new StringBuilder();
            for (Object id : ids) {
                if (sb.length() > 0) {
                    sb.append(",").append(id);
                } else {
                    sb.append(id);
                }
            }

            List<Object[]> resultList = cffOutboundProcedure(sb.toString());

            for (Object[] obj : resultList) {
                StringBuilder builder = new StringBuilder();
                builder.append(QUOTE).append(obj[1] != null ? String.valueOf(obj[1]) : StringUtils.EMPTY).append(QUOTE).append(ExcelExportUtil.COMMA);
                builder.append(QUOTE).append(obj[2] != null ? String.valueOf(obj[2]) : StringUtils.EMPTY).append(QUOTE).append(ExcelExportUtil.COMMA);
                builder.append(QUOTE).append(obj[4] != null ? String.valueOf(obj[4]) : StringUtils.EMPTY).append(QUOTE).append(ExcelExportUtil.COMMA);
                builder.append(QUOTE).append(obj[5] != null ? String.valueOf(obj[5]) : StringUtils.EMPTY).append(QUOTE).append(ExcelExportUtil.COMMA);
                builder.append(QUOTE).append(obj[6] != null ? String.valueOf(obj[6]) : StringUtils.EMPTY).append(QUOTE).append(ExcelExportUtil.COMMA);
                builder.append(QUOTE).append(obj[7] != null ? String.valueOf(obj[7]) : StringUtils.EMPTY).append(QUOTE).append(ExcelExportUtil.COMMA);

                builder.append(QUOTE).append(obj[11] != null ? String.valueOf(obj[11]) : StringUtils.EMPTY).append(QUOTE).append(ExcelExportUtil.COMMA);
                builder.append(QUOTE).append(obj[12] != null ? String.valueOf(obj[12]) : StringUtils.EMPTY).append(QUOTE).append(ExcelExportUtil.COMMA);

                builder.append(QUOTE).append(obj[13] != null ? String.valueOf(obj[13]) : StringUtils.EMPTY).append(QUOTE).append(ExcelExportUtil.COMMA);
                builder.append(QUOTE).append(obj[14] != null ? String.valueOf(obj[14]) : StringUtils.EMPTY).append(QUOTE).append(ExcelExportUtil.COMMA);

                builder.append(QUOTE).append(obj[15] != null ? String.valueOf(obj[15]) : StringUtils.EMPTY).append(QUOTE).append(ExcelExportUtil.COMMA);
                builder.append(QUOTE).append(obj[16] != null ? String.valueOf(obj[16]) : StringUtils.EMPTY).append(QUOTE).append(ExcelExportUtil.COMMA);

                builder.append(QUOTE).append(obj[19] != null ? String.valueOf(obj[19]) : StringUtils.EMPTY).append(QUOTE).append(ExcelExportUtil.COMMA);
                builder.append(QUOTE).append(obj[18] != null ? String.valueOf(obj[18]) : StringUtils.EMPTY).append(QUOTE).append(ExcelExportUtil.COMMA);

                builder.append(QUOTE).append(StringUtils.EMPTY).append(QUOTE).append(ExcelExportUtil.COMMA);
                builder.append(QUOTE).append(obj[17] != null ? String.valueOf(obj[17]) : StringUtils.EMPTY).append(QUOTE).append(ExcelExportUtil.COMMA);

                builder.append(QUOTE).append(obj[9] != null ? String.valueOf(obj[9]) : StringUtils.EMPTY).append(QUOTE).append(ExcelExportUtil.COMMA);
                builder.append(QUOTE).append(obj[10] != null ? String.valueOf(obj[10]) : StringUtils.EMPTY).append(QUOTE).append(ExcelExportUtil.COMMA);

                printWriter.println(builder);
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    public static List<Object[]> cffOutboundProcedure(String ids) throws Exception {
        Connection connection = null;
        DataSource datasource;
        CallableStatement statement = null;
        ResultSet resultSet = null;
        List<Object[]> objectList = new ArrayList<Object[]>();
        try {
            Context initialContext = new InitialContext();
            datasource = (DataSource) initialContext.lookup("java:jboss/datasources/jdbc/appDataPool");
            if (datasource != null) {
                connection = datasource.getConnection();
            } else {
                LOGGER.info("Failed to lookup datasource.");
            }
            if (connection != null) {
                statement = connection.prepareCall("{call " + "PRC_CFF_OUTBOUND" + "(?)}");
                statement.setString(1, ids);
                resultSet = statement.executeQuery();
                objectList = convertResultSetToList(resultSet);
                LOGGER.info("After Converting objectList size " + objectList.size());
            }

        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
            System.gc();
        }
        return objectList;
    }

    private static List<Object[]> convertResultSetToList(ResultSet rs) throws Exception {
        List<Object[]> objList = new ArrayList<Object[]>();

        try {
            while (rs.next()) {
                ResultSetMetaData metadata = rs.getMetaData();
                int numberOfColumns = metadata.getColumnCount();
                Object[] obj = new Object[numberOfColumns];
                for (int i = 1; i <= numberOfColumns; i++) {
                    obj[i - 1] = rs.getObject(i);
                }
                objList.add(obj);
            }

        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return objList;
    }

    public Set getAllCheckedCffIds(final ProcessSchedulerDTO binderDto) {
        Set checkedCffsSet = new HashSet();

        List parameterslist=getParametersForCffSearch(binderDto);
        List<Object[]> resultList;
        resultList = (List) CommonQueryUtils.executeSelectQuery(parameterslist, "getCffOuboundCheckAllResults");

        if (resultList != null) {
            for (int i = 0; i < resultList.size(); i++) {
                checkedCffsSet.add(String.valueOf(resultList.get(i)));
            }
        }
        return checkedCffsSet;
    }
    
       /**
     * Retrieves all the user name and stores that in the Concurrent Hash Map.
     *
     * @return the Map
     * @throws com.stpl.portal.kernel.exception.SystemException
     */
    public static Map<Integer, String> getUserName() throws SystemException {
        LOGGER.info("Enters getUserName method");
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(User.class);
        List<User> userList = UserLocalServiceUtil.dynamicQuery(dynamicQuery);
        for (User user : userList) {
            userMap.put(Long.valueOf(user.getUserId()).intValue(), user.getLastName() + ", " + user.getFirstName());
        }
        LOGGER.info("End of getUserName method");
        return userMap;
    }

}
    
    
  