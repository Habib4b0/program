/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.calendar.createcalendar.logic;

import com.stpl.app.adminconsole.common.dto.SessionDTO;
import com.stpl.app.adminconsole.dao.CommonDAO;
import com.stpl.app.adminconsole.dao.impl.CommonDAOImpl;
import com.stpl.app.adminconsole.util.Message;
import com.stpl.app.adminconsole.util.MessageUtil;
import com.stpl.app.parttwo.model.SlaCalendarDetails;
import com.stpl.app.parttwo.model.SlaCalendarMaster;
import com.stpl.app.parttwo.service.SlaCalendarDetailsLocalServiceUtil;
import com.stpl.app.parttwo.service.SlaCalendarMasterLocalServiceUtil;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.ifs.ui.util.CommonUIUtils;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionList;
import com.stpl.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.util.dao.orm.CustomSQLUtil;
import com.vaadin.ui.ComboBox;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author SYSBIZ\abishekram.r
 */
public class CalendarLogic {

    static int id;
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(CalendarLogic.class);
    final static CommonDAO baseRateDAO = new CommonDAOImpl();
    public static final HelperDTO ddlbDefaultValue = new HelperDTO(0, ConstantsUtils.SELECT_ONE);

    public void addLogic(String calendarName, List<Date> selectedDates, SessionDTO sessionDTO, boolean defaultHoliday) throws SystemException {
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(SlaCalendarMaster.class);
        final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
        productProjectionList.add(ProjectionFactoryUtil.property("slaCalendarMasterSid"));
        dynamicQuery.add(PropertyFactoryUtil.forName("calendarName").eq(calendarName));
        dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
        List results = null;
        try {
            results = SlaCalendarMasterLocalServiceUtil.dynamicQuery(dynamicQuery);
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
        Integer detailsid = null;
        if (results.size() > 0) {
            for (Iterator it = results.iterator(); it.hasNext();) {
                detailsid = (Integer) it.next();
            }
            SlaCalendarMaster calendar;
            if (detailsid != null) {
                calendar = SlaCalendarMasterLocalServiceUtil.fetchSlaCalendarMaster(detailsid);
                calendar.setInboundStatus("A");
                calendar.setCreatedBy(Integer.valueOf(sessionDTO.getUserId()));
                calendar.setCreatedDate(new Date());
                calendar.setModifiedBy(Integer.valueOf(sessionDTO.getUserId()));
                calendar.setModifiedDate(new Date());
                calendar.setDefaultHolidays(defaultHoliday);
                SlaCalendarMasterLocalServiceUtil.updateSlaCalendarMaster(calendar);
                addinDetails(detailsid, selectedDates, sessionDTO);
            }
        } else {
            addinMaster(calendarName, sessionDTO, defaultHoliday);
            addinDetails(id, selectedDates, sessionDTO);
        }

    }

    public void addinMaster(String calendarName, SessionDTO sessionDTO, boolean defaultHoliday) throws SystemException {
        SlaCalendarMaster calendar = SlaCalendarMasterLocalServiceUtil.createSlaCalendarMaster(0);
        calendar.setCalendarName(calendarName);
        calendar.setInboundStatus("A");
        calendar.setCreatedBy(Integer.valueOf(sessionDTO.getUserId()));
        calendar.setCreatedDate(new Date());
        calendar.setModifiedBy(Integer.valueOf(sessionDTO.getUserId()));
        calendar.setModifiedDate(new Date());
        calendar.setDefaultHolidays(defaultHoliday);
        calendar = SlaCalendarMasterLocalServiceUtil.addSlaCalendarMaster(calendar);
        this.id = calendar.getSlaCalendarMasterSid();
    }

    public List getDatesDdb(String year, int id) {

        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(SlaCalendarDetails.class);
        final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
        productProjectionList.add(ProjectionFactoryUtil.property("holidayCombined"));
        dynamicQuery.add(RestrictionsFactoryUtil.eq("slaCalendarMasterSid", id));
        dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
        List results = null;
        try {
            results = SlaCalendarDetailsLocalServiceUtil.dynamicQuery(dynamicQuery);
        } catch (SystemException ex) {
            LOGGER.error(ex);

        }
        return results;

    }

    public void update(int id, List<Date> selectedDates, SessionDTO sessionDTO) {
        updateDelete(id);
        try {
            addinDetails(id, selectedDates, sessionDTO);
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
    }

    public void updateDelete(int id) {
        LOGGER.info("UpdateDelete ID---" + id);
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(SlaCalendarDetails.class);
        final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
        productProjectionList.add(ProjectionFactoryUtil.property("slaCalendarDetailsSid"));
        dynamicQuery.add(PropertyFactoryUtil.forName("slaCalendarMasterSid").eq(id));
        dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
        List results = null;
        try {
            results = SlaCalendarDetailsLocalServiceUtil.dynamicQuery(dynamicQuery);
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
        for (Iterator it = results.iterator(); it.hasNext();) {
            try {
                Integer detailsid = (Integer) it.next();
                LOGGER.info("Deleting+++++++++++++++++++++" + detailsid);
                SlaCalendarDetailsLocalServiceUtil.deleteSlaCalendarDetails(detailsid);
            } catch (PortalException ex) {
                LOGGER.error(ex);
            } catch (SystemException ex) {
                LOGGER.error(ex);
            }
        }
    }

    public void Delete(int id) throws SystemException {
        updateDelete(id);
        SlaCalendarMaster calendar;
        calendar = SlaCalendarMasterLocalServiceUtil.fetchSlaCalendarMaster(id);
        calendar.setInboundStatus("D");
        SlaCalendarMasterLocalServiceUtil.updateSlaCalendarMaster(calendar);
        CommonUIUtils.getMessageNotification(MessageUtil.getMessage(Message.DELETE_CALENDER));

    }

    private void addinDetails(int id, List<Date> selectedDates, SessionDTO sessionDTO) throws SystemException {

        for (Date temp : selectedDates) {
            if (!(temp == null)) {
                SlaCalendarDetails tempdatesEntry = new SlaCalendarDetailsLocalServiceUtil().createSlaCalendarDetails(0);
                tempdatesEntry.setModifiedBy(Integer.valueOf(sessionDTO.getUserId()));
                tempdatesEntry.setCreatedBy(Integer.valueOf(sessionDTO.getUserId()));
                tempdatesEntry.setCreatedDate(new Date());
                tempdatesEntry.setModifiedDate(new Date());
                tempdatesEntry.setHolidayMonth(String.valueOf(temp.getMonth()));
                tempdatesEntry.setHolidayYear(String.valueOf(temp.getYear() + 1900));
                tempdatesEntry.setHolidayDay(String.valueOf(temp.getDate()));
                tempdatesEntry.setInboundStatus("A");
                tempdatesEntry.setHolidayCombined(temp);
                tempdatesEntry.setSlaCalendarMasterSid(id);
                SlaCalendarDetailsLocalServiceUtil.addSlaCalendarDetails(tempdatesEntry);
            }
        }
    }

    //This method will retrieve the Ddlb values
    public List<HelperDTO> getDdlbList(String QueryName, final List<String> input, boolean isLazyload) {
        List<Object[]> list = getDdlbData(input, QueryName, null);
        List<HelperDTO> resultList = new ArrayList<HelperDTO>();
        if (isLazyload) {
            if (Integer.valueOf(String.valueOf(input.get(1))) == 0) {
                resultList.add(ddlbDefaultValue);
            }
        } else {
            resultList.add(ddlbDefaultValue);
        }

        for (Object[] str : list) {
            HelperDTO dto = new HelperDTO();
            dto.setId(str[0] == null ? 0 : Integer.valueOf(str[0].toString()));
            dto.setDescription(str[1] == null ? "0" : String.valueOf(str[1]));
            resultList.add(dto);
        }
        return resultList;
    }

    public static ComboBox getComboBox(final ComboBox select, final List<HelperDTO> helperList) throws Exception {
        select.removeAllItems();
        int size = helperList.size();
        for (int i = 0; i < size; i++) {
            final HelperDTO helperDTO = helperList.get(i);
            String itemId1 = "" + helperDTO.getId();
            select.addItem(itemId1);
            select.setItemCaption(itemId1, helperDTO.getDescription());
        }
        select.setValue("0");
        return select;
    }

    public static List getDdlbData(List input, String queryName, String quaryName2) {
        LOGGER.info("Inside getData " + queryName);
        StringBuilder sql = new StringBuilder();
        if (queryName != null && !queryName.isEmpty()) {
            sql = new StringBuilder(CustomSQLUtil.get(queryName));
            for (Object temp : input) {
                sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
            }
        }

        List list = (List<Object[]>) baseRateDAO.executeSelectQuery(sql.toString(), null, null);

        LOGGER.info("End of getDdlbData");
        return list;
    }

}
