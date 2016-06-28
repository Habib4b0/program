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
import com.stpl.app.adminconsole.util.HelperListUtil;
import com.stpl.app.model.WorkflowProfile;
import com.stpl.app.service.WorkflowProfileLocalServiceUtil;
import com.stpl.app.util.ConstantUtil;
import com.vaadin.server.VaadinSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.jboss.logging.Logger;

/**
 *
 * @author Asha
 */
public class ProcessLogic {

    private static final Logger LOGGER = Logger.getLogger(ProcessLogic.class);

    final static CommonDAO dao = new CommonDAOImpl();
    HelperListUtil helperListUtil = HelperListUtil.getInstance();

    public List getSearchResult(boolean count, List<SortByColumn> columns, int start, int offset) {
        LOGGER.info("Entering getSearchResult");
        try {
            String query = CommonUtil.workFlowQuery(count, start, offset);
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

    private List getCustomizedProcessMonitoring(List list) throws Exception {
        LOGGER.info("Entering getCustomizedProcessMonitoring" + list.size());
        List<ProcessMonitorDTO> returnList = new ArrayList<ProcessMonitorDTO>();
        SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy");
        final HashMap<String, String> userInfoMap = (HashMap<String, String>) com.stpl.app.adminconsole.common.util.CommonUtil.getCreatedByUser();

        if (list != null && list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                Object[] obj = (Object[]) list.get(i);
                ProcessMonitorDTO dto = new ProcessMonitorDTO();
                dto.setProcessName(String.valueOf(obj[1]));
                dto.setProcessType(((helperListUtil.getIdHelperDTOMap().get(obj[10] != null ? Integer.valueOf(String.valueOf(obj[10])) : 0))));
                if (obj[3] != null) {
                    dto.setStartDate(new Date(date.format(obj[3])));
                }
                if (obj[4] != null) {
                    dto.setEndDate(new Date(date.format(obj[4])));
                }
                dto.setCalender(String.valueOf(obj[5]));
                dto.setModifiedDate(date.format(obj[8]));
                dto.setModifiedBy(userInfoMap.get(obj[9]));
                dto.setSid(Integer.valueOf(String.valueOf(obj[0])));
                returnList.add(dto);
            }
        }
        LOGGER.info("Ending getCustomizedSchedulerProcessing" + returnList.size());
        return returnList;
    }

    public ProcessMonitorDTO getProcessMonitorByID(int sid) {
        try {
            LOGGER.info("Entering getProcessScheduleByID");
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
            dto.setSid(sid);
            LOGGER.info("Ending getProcessScheduleByID");
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
            profile.setStartHour1(processMonitorDTO.getHours1() == null || "null".equals(processMonitorDTO.getHours1()) ? 24 : Integer.valueOf(processMonitorDTO.getHours1()));
            profile.setStartHour2(processMonitorDTO.getHours2() == null || "null".equals(processMonitorDTO.getHours2()) ? 24 : Integer.valueOf(processMonitorDTO.getHours2()));
            profile.setStartHour3(processMonitorDTO.getHours3() == null || "null".equals(processMonitorDTO.getHours3()) ? 24 : Integer.valueOf(processMonitorDTO.getHours3()));
            profile.setStartMinutes1(processMonitorDTO.getMinutes1() == null || "null".equals(processMonitorDTO.getMinutes1()) ? 24 : Integer.valueOf(processMonitorDTO.getMinutes1()));
            profile.setStartMinutes2(processMonitorDTO.getMinutes2() == null || "null".equals(processMonitorDTO.getMinutes2()) ? 24 : Integer.valueOf(processMonitorDTO.getMinutes2()));
            profile.setStartMinutes3(processMonitorDTO.getMinutes3() == null || "null".equals(processMonitorDTO.getMinutes3()) ? 24 : Integer.valueOf(processMonitorDTO.getMinutes3()));
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
            LOGGER.info("Entering update");
            if (processMonitorDTO.getSid() != 0) {
                WorkflowProfile profile = WorkflowProfileLocalServiceUtil.getWorkflowProfile(processMonitorDTO.getSid());

                profile.setProcessName(processMonitorDTO.getProcessName());
                profile.setProcessDisplayName(processMonitorDTO.getProcessDisplayName());
                profile.setProcessType(processMonitorDTO.getProcessType().getId());
                profile.setStartDate(processMonitorDTO.getStartDate());
                profile.setEndDate(processMonitorDTO.getEndDate());
                profile.setStartHour1(processMonitorDTO.getHours1() == null || "null".equals(processMonitorDTO.getHours1()) ? 24 : Integer.valueOf(processMonitorDTO.getHours1()));
                profile.setStartHour2(processMonitorDTO.getHours2() == null || "null".equals(processMonitorDTO.getHours2()) ? 24 : Integer.valueOf(processMonitorDTO.getHours2()));
                profile.setStartHour3(processMonitorDTO.getHours3() == null || "null".equals(processMonitorDTO.getHours3()) ? 24 : Integer.valueOf(processMonitorDTO.getHours3()));
                profile.setStartMinutes1(processMonitorDTO.getMinutes1() == null || "null".equals(processMonitorDTO.getMinutes1()) ? 24 : Integer.valueOf(processMonitorDTO.getMinutes1()));
                profile.setStartMinutes2(processMonitorDTO.getMinutes2() == null || "null".equals(processMonitorDTO.getMinutes2()) ? 24 : Integer.valueOf(processMonitorDTO.getMinutes2()));
                profile.setStartMinutes3(processMonitorDTO.getMinutes3() == null || "null".equals(processMonitorDTO.getMinutes3()) ? 24 : Integer.valueOf(processMonitorDTO.getMinutes3()));
                profile.setModifiedBy(Integer.valueOf(String.valueOf(sessionDTO.getUserId())));
                profile.setModifiedDate(new Date());
                WorkflowProfileLocalServiceUtil.updateWorkflowProfile(profile);
            }
            LOGGER.info("Ending update");
        } catch (Exception ex) {

            LOGGER.error(ex);
        }
    }

}
