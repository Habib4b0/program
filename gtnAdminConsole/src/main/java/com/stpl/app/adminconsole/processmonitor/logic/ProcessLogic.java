/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.processmonitor.logic;

import com.stpl.app.adminconsole.common.dto.SessionDTO;
import com.stpl.app.adminconsole.dao.CommonDAO;
import com.stpl.app.adminconsole.dao.impl.CommonDAOImpl;
import com.stpl.app.adminconsole.processmonitor.dto.ProcessMonitorDTO;
import com.stpl.app.adminconsole.processmonitor.util.CommonUtil;
import static com.stpl.app.adminconsole.processscheduler.logic.ProcessSchedulerLogic.getDBColumnName;
import static com.stpl.app.adminconsole.processscheduler.logic.ProcessSchedulerLogic.loadDbColumnName;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.adminconsole.util.HelperListUtil;
import com.stpl.app.model.WorkflowProfile;
import com.stpl.app.service.WorkflowProfileLocalServiceUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.User;
import com.stpl.portal.service.UserLocalServiceUtil;
import com.vaadin.server.VaadinSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.jboss.logging.Logger;

/**
 *
 * @author Asha
 */
public class ProcessLogic {

    private static final Logger LOGGER = Logger.getLogger(ProcessLogic.class);
        static HashMap<String, String> columnName=new HashMap<String, String>();


    final static CommonDAO dao = new CommonDAOImpl();
    HelperListUtil helperListUtil = HelperListUtil.getInstance();

    public List getSearchResult(boolean count, int start, int offset,boolean monitor,final List<SortByColumn> orderByColumns) {
        LOGGER.debug("Entering getSearchResult");
        try {
            boolean asc = false;
            String columnName = StringUtils.EMPTY;
            String dbColumnName = StringUtils.EMPTY;
            loadDbColumnName();
            if(!count){
                for (final Iterator<SortByColumn> iterator = orderByColumns.iterator(); iterator.hasNext();) {
                    final SortByColumn orderByColumn = (SortByColumn) iterator.next();

                    columnName = orderByColumn.getName();
                    dbColumnName = getDBColumnName(columnName);
                    if (orderByColumn.getType() == SortByColumn.Type.ASC) {
                        asc = false;
                    } else {
                        asc = true;
                    }
                }
            }
            String query = CommonUtil.workFlowQuery(count, start, offset, monitor, dbColumnName, asc);
//            String query = CommonUtil.workFlowQuery(count, start, offset);
            List list = (List) dao.executeSelectQuery(query, null, null);
            if (count) {
                return list;
            } else {
                return getCustomizedProcessMonitoring(list);
            }
        } catch (Exception ex) {

            LOGGER.error(ex);
            return null;
        }
    }
        public static String getDBColumnName(String visibleColumnName) {
         return columnName.get(visibleColumnName);         
    } 
                    
   public static HashMap<String, String> loadDbColumnName() {       
       columnName.put("processName", "PROCESS_NAME");
       columnName.put("processType", "PT.DESCRIPTION");
       columnName.put("calender", "SLA_CALENDAR_MASTER_SID");
       columnName.put("modifiedDate", "MODIFIED_DATE");
       columnName.put("modifiedBy", "USR.screenName");   
       return columnName;
   }

    private List getCustomizedProcessMonitoring(List list) throws SystemException, PortalException {
        LOGGER.debug("Entering getCustomizedProcessMonitoring" + list.size());
        List<ProcessMonitorDTO> returnList = new ArrayList<ProcessMonitorDTO>();
        SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy");
        final HashMap<String, String> userInfoMap = (HashMap<String, String>) com.stpl.app.adminconsole.common.util.CommonUtil.getCreatedByUser();

        if (list != null && list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                Object[] obj = (Object[]) list.get(i);
                ProcessMonitorDTO dto = new ProcessMonitorDTO();
                dto.setProcessName(String.valueOf(obj[1]));
                dto.setProcessType(helperListUtil.getIdHelperDTOMap().get(obj[NumericConstants.TEN] != null ? Integer.valueOf(String.valueOf(obj[NumericConstants.TEN])) : 0));
                if (obj[NumericConstants.THREE] != null) {
                    dto.setStartDate(new Date(date.format(obj[NumericConstants.THREE])));
                }
                if (obj[NumericConstants.FOUR] != null) {
                    dto.setEndDate(new Date(date.format(obj[NumericConstants.FOUR])));
                }
                dto.setCalender(obj[NumericConstants.FIVE] != null ? String.valueOf(obj[NumericConstants.FIVE]) : StringUtils.EMPTY);
                dto.setModifiedDate(date.format(obj[NumericConstants.EIGHT]));
                dto.setModifiedBy(userInfoMap.get(String.valueOf(obj[NumericConstants.NINE])));   
                

                dto.setSid(Integer.valueOf(String.valueOf(obj[0])));
                returnList.add(dto);
            }
        }
        LOGGER.debug("Ending getCustomizedSchedulerProcessing" + returnList.size());
                return returnList;
    }

    public ProcessMonitorDTO getProcessMonitorByID(int sid) {
        try {
            LOGGER.debug("Entering getProcessScheduleByID");
            ProcessMonitorDTO dto = new ProcessMonitorDTO();
            WorkflowProfile profile = WorkflowProfileLocalServiceUtil.getWorkflowProfile(sid);
            dto.setProcessName(profile.getProcessName());
            dto.setProcessType(helperListUtil.getIdHelperDTOMap().get(profile.getProcessType()));
            dto.setStartDate(profile.getStartDate());
            dto.setEndDate(profile.getEndDate());
            dto.setHours1(String.valueOf(profile.getStartHour1()));
            dto.setMinutes1(String.valueOf(profile.getStartMinutes1()));

            dto.setHours2(String.valueOf(profile.getStartHour2()));
            dto.setMinutes2(String.valueOf(profile.getStartMinutes2()));
            dto.setHours3(String.valueOf(profile.getStartHour3()));
            dto.setMinutes3(String.valueOf(profile.getStartMinutes3()));
            dto.setModifiedBy(String.valueOf(profile.getModifiedBy()));
            dto.setSid(sid);
            LOGGER.debug("Ending getProcessScheduleByID");
            return dto;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return null;
        }
    }

    public static void add(ProcessMonitorDTO processMonitorDTO, SessionDTO sessionDTO) {
        try {
            WorkflowProfile profile = WorkflowProfileLocalServiceUtil.createWorkflowProfile(0);
            profile.setProcessName(processMonitorDTO.getProcessName());
            profile.setProcessType(processMonitorDTO.getProcessType().getId());
            profile.setProcessDisplayName(processMonitorDTO.getProcessDisplayName());
            profile.setStartDate(processMonitorDTO.getStartDate());
            profile.setEndDate(processMonitorDTO.getEndDate());
            profile.setStartHour1(processMonitorDTO.getHours1() == null || "null".equals(processMonitorDTO.getHours1()) ? NumericConstants.TWENTY_FOUR : Integer.valueOf(processMonitorDTO.getHours1()));
            profile.setStartHour2(processMonitorDTO.getHours2() == null || "null".equals(processMonitorDTO.getHours2()) ? NumericConstants.TWENTY_FOUR : Integer.valueOf(processMonitorDTO.getHours2()));
            profile.setStartHour3(processMonitorDTO.getHours3() == null || "null".equals(processMonitorDTO.getHours3()) ? NumericConstants.TWENTY_FOUR : Integer.valueOf(processMonitorDTO.getHours3()));
            profile.setStartMinutes1(processMonitorDTO.getMinutes1() == null || "null".equals(processMonitorDTO.getMinutes1()) ? NumericConstants.TWENTY_FOUR : Integer.valueOf(processMonitorDTO.getMinutes1()));
            profile.setStartMinutes2(processMonitorDTO.getMinutes2() == null || "null".equals(processMonitorDTO.getMinutes2()) ? NumericConstants.TWENTY_FOUR : Integer.valueOf(processMonitorDTO.getMinutes2()));
            profile.setStartMinutes3(processMonitorDTO.getMinutes3() == null || "null".equals(processMonitorDTO.getMinutes3()) ? NumericConstants.TWENTY_FOUR : Integer.valueOf(processMonitorDTO.getMinutes3()));
            profile.setActiveFlag("Y");
            profile.setSchemaName("BPI");
            profile.setInboundStatus("A");
            profile.setCreatedDate(new Date());
            profile.setFrequency("Time");
            profile.setSlaCalendarMasterSid(1);
            profile.setUserSid(Integer.valueOf(sessionDTO.getUserId()));
            profile.setCreatedBy(Integer.valueOf(sessionDTO.getUserId()));
            profile.setCreatedDate(new Date());
            profile.setModifiedBy(Integer.valueOf(String.valueOf(sessionDTO.getUserId())));
            profile.setModifiedDate(new Date());
            profile = WorkflowProfileLocalServiceUtil.addWorkflowProfile(profile);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }

    }

    public static void update(ProcessMonitorDTO processMonitorDTO,SessionDTO sessionDTO) {
        try {
            LOGGER.debug("Entering update");
            if (processMonitorDTO.getSid() != 0) {
                WorkflowProfile profile = WorkflowProfileLocalServiceUtil.getWorkflowProfile(processMonitorDTO.getSid());

                profile.setProcessName(processMonitorDTO.getProcessName());
                profile.setProcessDisplayName(processMonitorDTO.getProcessDisplayName());
                profile.setProcessType(processMonitorDTO.getProcessType().getId());
                profile.setStartDate(processMonitorDTO.getStartDate());
                profile.setEndDate(processMonitorDTO.getEndDate());
                profile.setStartHour1(processMonitorDTO.getHours1() == null || "null".equals(processMonitorDTO.getHours1()) ? NumericConstants.TWENTY_FOUR : Integer.valueOf(processMonitorDTO.getHours1()));
                profile.setStartHour2(processMonitorDTO.getHours2() == null || "null".equals(processMonitorDTO.getHours2()) ? NumericConstants.TWENTY_FOUR : Integer.valueOf(processMonitorDTO.getHours2()));
                profile.setStartHour3(processMonitorDTO.getHours3() == null || "null".equals(processMonitorDTO.getHours3()) ? NumericConstants.TWENTY_FOUR : Integer.valueOf(processMonitorDTO.getHours3()));
                profile.setStartMinutes1(processMonitorDTO.getMinutes1() == null || "null".equals(processMonitorDTO.getMinutes1()) ? NumericConstants.TWENTY_FOUR : Integer.valueOf(processMonitorDTO.getMinutes1()));
                profile.setStartMinutes2(processMonitorDTO.getMinutes2() == null || "null".equals(processMonitorDTO.getMinutes2()) ? NumericConstants.TWENTY_FOUR : Integer.valueOf(processMonitorDTO.getMinutes2()));
                profile.setStartMinutes3(processMonitorDTO.getMinutes3() == null || "null".equals(processMonitorDTO.getMinutes3()) ? NumericConstants.TWENTY_FOUR : Integer.valueOf(processMonitorDTO.getMinutes3()));
                profile.setModifiedBy(Integer.valueOf(String.valueOf(sessionDTO.getUserId())));
                profile.setModifiedDate(new Date());
                WorkflowProfileLocalServiceUtil.updateWorkflowProfile(profile);
            }
            LOGGER.debug("Ending update");
        } catch (Exception ex) {

            LOGGER.error(ex);
        }
    }

}
