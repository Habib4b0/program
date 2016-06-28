/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.calendar.ui;

import com.google.gwt.thirdparty.guava.common.collect.HashMultimap;
import com.google.gwt.thirdparty.guava.common.collect.Multimap;
import com.stpl.app.adminconsole.calendar.createcalendar.logic.CalendarLogic;
import com.stpl.app.adminconsole.common.dto.SessionDTO;
import com.stpl.app.adminconsole.util.AbstractNotificationUtils;
import com.stpl.app.adminconsole.util.Message;
import com.stpl.app.adminconsole.util.MessageUtil;
import com.stpl.app.adminconsole.util.QueryReader;
import com.stpl.app.parttwo.model.SlaCalendarMaster;
import com.stpl.app.parttwo.service.SlaCalendarMasterLocalServiceUtil;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.asi.calendarfield.CalendarField;
import org.asi.calendarfield.WeekDay;
import org.drools.core.util.StringUtils;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

/**
 *
 * @author SYSBIZ\abishekram.r
 */
public class CalendarIndex extends CustomComponent {

    @UiField("existingCalender")
    public ComboBox existingCalender;
    @UiField("year")
    public ComboBox year;
    @UiField("calendarName")
    public TextField calendarName;
    @UiField("defaultHoliday")
    public CheckBox defaultHoliday;
    GregorianCalendar calStart = new GregorianCalendar();
    GregorianCalendar calEnd = new GregorianCalendar();
    CalendarLogic commonLogic = new CalendarLogic();
    @UiField("calendarPanel")
    public Panel calendarPanel;
    public boolean isUpdate = false;
    CalendarField calendarField = new CalendarField();
    CalendarLogic cllogic = new CalendarLogic();
    @UiField("editBtn")
    Button editBtn;
    @UiField("resetBtn")
    Button resetBtn;
    @UiField("saveBtn")
    Button saveBtn;
    @UiField("deleteBtn")
    Button deleteBtn;
    @UiField("calendarNameLbel")
    public Label calendarNameLbel;
    @UiField("backBtn")
    Button backBtn;
    SessionDTO sessionDTO;
    public Boolean isFirst = true;
    Multimap<String, Date> masterTable = HashMultimap.create();
    int yearValue;
    boolean holiday;
    private static final Logger LOGGER = Logger.getLogger(CalendarIndex.class);

    public CalendarIndex(SessionDTO sessionDTO, boolean isUpdate) throws PortalException, SystemException {
        this.isUpdate = isUpdate;
        this.sessionDTO = sessionDTO;
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/clara/calenderConfiguration.xml"), this));
        configureField();
        addStyleName("adjustCaption");
    }

    private void configureField() throws PortalException, SystemException {
        year.setNullSelectionAllowed(true);
        calendarField.setYearResolution(true);
        calendarField.setImmediate(true);
        calendarField.setMatrix(CalendarField.Matrix.ROW_3xCOL_4);
        yearValue = Calendar.getInstance().getTime().getYear();
        calendarField.setRangeEnd(new Date(yearValue, 11, 31));
        calendarField.setRangeStart(new Date(yearValue, 0, 1));
        createYear();
        calendarName.setRequired(false);
        calendarName.addStyleName("acctmandatoryfields");
        backBtn.setVisible(false);
        calendarPanel.setContent(calendarField);
        existingCalender.setPageLength(7);
        existingCalender.setReadOnly(false);
        existingCalender.addValueChangeListener(new Property.ValueChangeListener() {

            public void valueChange(Property.ValueChangeEvent event) {
                if (!"0".equals(String.valueOf(existingCalender.getValue())) && !"null".equals(String.valueOf(existingCalender.getValue()))) {
                    calendarName.setReadOnly(true);
                    year.setReadOnly(true);
                    defaultHoliday.setReadOnly(true);
                    saveBtn.setEnabled(false);
                } else {
                    calendarName.setReadOnly(false);
                    year.setReadOnly(false);
                    defaultHoliday.setReadOnly(false);
                    saveBtn.setEnabled(true);
                }
            }
        });

        calendarName.addValueChangeListener(new Property.ValueChangeListener() {

            public void valueChange(Property.ValueChangeEvent event) {
                if (!StringUtils.EMPTY.equals(calendarName.getValue())) {
                    existingCalender.setReadOnly(true);
                    editBtn.setEnabled(false);
                    deleteBtn.setEnabled(false);
                } else {
                    existingCalender.setReadOnly(false);
                    editBtn.setEnabled(true);
                    deleteBtn.setEnabled(true);
                }
            }
        });

        year.addValueChangeListener(new Property.ValueChangeListener() {
            public void valueChange(Property.ValueChangeEvent event) {
                if (year.getValue() != null) {
                    int yearValue = (Integer) year.getValue();
                    Date startDate = new Date(yearValue - 1900, 0, 1);
                    Date endDate = new Date(yearValue - 1900, 11, 31);
                    if (startDate.after(calendarField.getRangeEnd())) {
                        calendarField.setRangeEnd(endDate);
                        calendarField.setRangeStart(startDate);
                    } else {
                        calendarField.setRangeStart(startDate);
                        calendarField.setRangeEnd(endDate);
                    }

                    if (isUpdate) {
                        LOGGER.info("year.getValue()" + year.getValue());
                        LOGGER.info("inside update");

                    } else {
                        calendarField.setValue(new ArrayList());
                    }

                    if (holiday) {
                        defaultHoliday.setValue(false);
                        calendarField.setValue(new ArrayList());
                    }
                    calendarField.setValue(new ArrayList());

                }
            }
        });

        defaultHoliday.addValueChangeListener(new Property.ValueChangeListener() {

            public void valueChange(Property.ValueChangeEvent event) {
                if (defaultHoliday.getValue()) {
                    calendarField.setSelectedWeekDays(WeekDay.SATURDAY, WeekDay.SUNDAY);
                    holiday = true;
                } else {
                    calendarField.setSelectedWeekDays();
                }
            }
        });
        loadCombobox();
        configureButtons();
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(sessionDTO.getUserId());
        Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, "Calendar Configuration");
        setButtonLevelSecurity(functionHM);
    }

    private void configureButtons() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        resetBtn.addClickListener(new Button.ClickListener() {

            public void buttonClick(Button.ClickEvent event) {
                year.setReadOnly(false);
                year.setValue(null);
                year.setReadOnly(true);
                calendarName.setReadOnly(false);
                calendarName.setValue("");
                calendarName.setReadOnly(true);
                defaultHoliday.setReadOnly(false);
                defaultHoliday.setValue(false);
                defaultHoliday.setReadOnly(true);
                existingCalender.setReadOnly(false);
                existingCalender.removeAllItems();
                existingCalender.setReadOnly(true);
                loadCombobox();
                int yearValue = Calendar.getInstance().getTime().getYear();
                Date startDate = new Date(yearValue, 0, 1);
                Date endDate = new Date(yearValue, 11, 31);
                if (startDate.after(calendarField.getRangeEnd())) {
                    calendarField.setRangeEnd(endDate);
                    calendarField.setRangeStart(startDate);
                } else {
                    calendarField.setRangeStart(startDate);
                    calendarField.setRangeEnd(endDate);
                }
            }
        });
        editBtn.addClickListener(new Button.ClickListener() {

            public void buttonClick(Button.ClickEvent event) {
                if (existingCalender.getValue() == null || (String) existingCalender.getValue() == "0") {
                    AbstractNotificationUtils.getErrorNotification(MessageUtil.getMessage(Message.ERROR_HEADER), MessageUtil.getMessage(Message.ERROR_MSG));
                } else {
                    isUpdate = true;
                    existingCalender.setReadOnly(true);
                    calendarNameLbel.setVisible(false);
                    calendarName.setVisible(false);
                    resetBtn.setVisible(false);
                    editBtn.setVisible(false);
                    deleteBtn.setVisible(false);
                    backBtn.setVisible(true);
                    saveBtn.setEnabled(true);
                    year.setReadOnly(false);
                    defaultHoliday.setReadOnly(false);
                    isFirst = true;
                    viewLogic();
                }
            }
        });
        saveBtn.addClickListener(new Button.ClickListener() {

            public void buttonClick(Button.ClickEvent event) {
                calendarName.setRequired(true);
                calendarName.setNullSettingAllowed(false);
                if ((calendarName.getValue() == null || calendarName.getValue() == "") && (!isUpdate)) {
                    AbstractNotificationUtils.getErrorNotification(MessageUtil.getMessage(Message.ERROR_HEADER), MessageUtil.getMessage(Message.NAME_ERROR_MSG));
                } else if ((year.getValue() == null) && (!isUpdate || isUpdate)) {
                    AbstractNotificationUtils.getErrorNotification(MessageUtil.getMessage(Message.ERROR_HEADER), MessageUtil.getMessage(Message.YEAR_ERROR_MSG));
                } else if ((calendarField.getValue().size() == 0) && (!isUpdate || isUpdate)) {
                    AbstractNotificationUtils.getErrorNotification(MessageUtil.getMessage(Message.ERROR_HEADER), MessageUtil.getMessage(Message.NO_DATES_ERROR_MSG));
                } else {
                    addlogic(calendarName.getValue());
                    loadCombobox();
                    calendarName.setVisible(true);
                    calendarName.setReadOnly(false);
                    calendarName.setValue("");
                    calendarNameLbel.setVisible(true);
                    year.setReadOnly(false);
                    year.setValue(null);
                    defaultHoliday.setReadOnly(false);
                    defaultHoliday.setValue(false);
                    if (isUpdate) {
                        isUpdate = false;
                        existingCalender.setReadOnly(false);
                        existingCalender.removeAllItems();
                        loadCombobox();
                    }
                    int yearValue1 = Calendar.getInstance().getTime().getYear();
                    Date startDate = new Date(yearValue1, 0, 1);
                    Date endDate = new Date(yearValue1, 11, 31);

                    if (startDate.after(calendarField.getRangeEnd())) {
                        calendarField.setRangeEnd(endDate);
                        calendarField.setRangeStart(startDate);
                    } else {
                        calendarField.setRangeStart(startDate);
                        calendarField.setRangeEnd(endDate);
                    }
                    editBtn.setVisible(true);
                    deleteBtn.setVisible(true);
                    editBtn.setEnabled(true);
                    deleteBtn.setEnabled(true);
                    backBtn.setVisible(false);
                    resetBtn.setVisible(true);
                }
            }
        });
        deleteBtn.addClickListener(new Button.ClickListener() {

            public void buttonClick(Button.ClickEvent event) {
                if (existingCalender.getValue() == null || (String) existingCalender.getValue() == "0") {
                    AbstractNotificationUtils.getErrorNotification(MessageUtil.getMessage(Message.ERROR_HEADER), MessageUtil.getMessage(Message.ERROR_MSG));
                } else {
                    new AbstractNotificationUtils() {

                        @Override
                        public void noMethod() {

                        }

                        @Override
                        public void yesMethod() {
                            try {
                                CalendarLogic cllogic = new CalendarLogic();
                                cllogic.Delete(Integer.valueOf(String.valueOf(existingCalender.getValue())));
                                loadCombobox();
                            } catch (SystemException ex) {
                                LOGGER.error(ex);
                            }
                        }
                    }.getConfirmationMessage(MessageUtil.getMessage(Message.REMOVE_LINE_HEADER), MessageUtil.getMessage(Message.REMOVE_LINE_MSG));
                }
            }
        });

        backBtn.addClickListener(new Button.ClickListener() {
            public void buttonClick(Button.ClickEvent event) {
                calendarNameLbel.setVisible(true);
                calendarName.setVisible(true);
                resetBtn.setVisible(true);
                editBtn.setVisible(true);
                deleteBtn.setVisible(true);
                backBtn.setVisible(false);
                saveBtn.setEnabled(true);
                calendarName.setReadOnly(false);
                year.setReadOnly(false);
                year.setValue(null);
                defaultHoliday.setReadOnly(false);
                defaultHoliday.setValue(false);
                existingCalender.setReadOnly(false);
                existingCalender.removeAllItems();
                isUpdate = false;
                loadCombobox();
                int yearValue = Calendar.getInstance().getTime().getYear();
                Date startDate = new Date(yearValue, 0, 1);
                Date endDate = new Date(yearValue, 11, 31);
                if (startDate.after(calendarField.getRangeEnd())) {
                    calendarField.setRangeEnd(endDate);
                    calendarField.setRangeStart(startDate);
                } else {
                    calendarField.setRangeStart(startDate);
                    calendarField.setRangeEnd(endDate);
                }
            }
        });

    }

    private void loadCombobox() {
        if (!isUpdate) {
            List<String> ids = new ArrayList<String>();
            ids.add("%");
            List<HelperDTO> dtos = commonLogic.getDdlbList("existingCAlendar", ids, false);
            try {
                existingCalender.setReadOnly(false);
                existingCalender.removeAllItems();
                existingCalender = commonLogic.getComboBox(existingCalender, dtos);
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
            existingCalender.setNullSelectionAllowed(false);
            existingCalender.addStyleName("acctmandatoryfields");
        }
    }

    public void addlogic(String CalendarName) {
        if (!isUpdate) {
            try {
                cllogic.addLogic(CalendarName, calendarField.getValue(), sessionDTO, defaultHoliday.getValue());
            } catch (SystemException ex) {
                LOGGER.error(ex);
            }
        } else {
            updateHoliday(Integer.valueOf(String.valueOf(existingCalender.getValue())));
            cllogic.update(Integer.valueOf(String.valueOf(existingCalender.getValue())), calendarField.getValue(), sessionDTO);

        }
    }

    public void viewLogic() {
        LOGGER.info("viewLogic");
        if (existingCalender.getValue() != null) {
            calendarField.setValue(getDatesDdb(Integer.valueOf(String.valueOf(existingCalender.getValue()))));
            setHolidays(Integer.valueOf(String.valueOf(existingCalender.getValue())));
        }
    }

    public List getDatesDdb(int id) {
        List SelectedDate = new ArrayList();
        String query = QueryReader.getQuery("GeteditCalendar");
        Map<String, Object> input = new HashMap();
        input.put("?id", id);
        List list = (List) QueryReader.executeSelectQuery(input, query);
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String savedyear = "";
        for (Object nsrList : list) {
            final Object[] object = (Object[]) nsrList;

            savedyear = object[2].toString();
            try {
                String combinedyear = object[3].toString();
                Date date = df.parse(combinedyear);

                SelectedDate.add(date);
            } catch (Exception e) {
                LOGGER.error(e);
            }

        }
        if (!"".equals(savedyear)) {
            year.setValue(Integer.parseInt(savedyear));
        }
        LOGGER.info("saved year" + savedyear);

        return SelectedDate;
    }

    public void setHolidays(int id) {
        String query = QueryReader.getQuery("holiday");
        Map<String, Object> input = new HashMap();
        input.put("?id", id);
        List list = (List) QueryReader.executeSelectQuery(input, query);
        if (list.get(0) != null) {
            boolean holidays = (Boolean) list.get(0);
            defaultHoliday.setValue(holidays);
        }
    }

    private void createYear() {
        Date today = new Date();
        this.year.addItem("-Select One");
        this.year.setNullSelectionItemId("-Select One");
        int year = 1950;
        for (int i = 0; i < 100; i++) {
            year += 1;
            this.year.addItem(year);
            if (year == today.getYear() + 1900) {
            }
        }

    }

    /**
     * Set Button Level Security
     *
     * @param functionHM
     */
    private void setButtonLevelSecurity(Map<String, AppPermission> functionHM) {
        if (functionHM.get("editBtn") != null && ((AppPermission) functionHM.get("editBtn")).isFunctionFlag()) {
            editBtn.setVisible(Boolean.TRUE);
        } else {
            editBtn.setVisible(Boolean.FALSE);
        }

        if (functionHM.get("saveBtn") != null && ((AppPermission) functionHM.get("saveBtn")).isFunctionFlag()) {
            saveBtn.setVisible(Boolean.TRUE);
        } else {
            saveBtn.setVisible(Boolean.FALSE);
        }
        if (functionHM.get("deleteBtn") != null && ((AppPermission) functionHM.get("deleteBtn")).isFunctionFlag()) {
            deleteBtn.setVisible(Boolean.TRUE);
        } else {
            deleteBtn.setVisible(Boolean.FALSE);
        }
    }

    public void updateHoliday(int masterId) {
        try {
            SlaCalendarMaster master = SlaCalendarMasterLocalServiceUtil.getSlaCalendarMaster(masterId);
            master.setDefaultHolidays(defaultHoliday.getValue());
            SlaCalendarMasterLocalServiceUtil.updateSlaCalendarMaster(master);
        } catch (PortalException ex) {
            LOGGER.error(ex);
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
    }
}
