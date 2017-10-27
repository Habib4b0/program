/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.processmonitor.ui.form;

import com.stpl.app.adminconsole.util.StringConstantUtils;
import com.stpl.app.adminconsole.common.dto.SessionDTO;
import com.stpl.app.adminconsole.common.util.CommonUtil;
import com.stpl.app.adminconsole.dao.CommonDAO;
import com.stpl.app.adminconsole.dao.impl.CommonDAOImpl;
import com.stpl.app.adminconsole.processmonitor.dto.ProcessMonitorDTO;
import com.stpl.app.adminconsole.processmonitor.logic.ProcessLogic;
import com.stpl.app.adminconsole.processmonitor.logic.ProcessMonitorLogic;
import com.stpl.app.adminconsole.util.AbstractNotificationUtils;
import com.stpl.app.adminconsole.util.CommonUtils;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import static com.stpl.app.adminconsole.util.ConstantsUtils.IndicatorConstants.HOLIDAY;
import static com.stpl.app.adminconsole.util.ConstantsUtils.IndicatorConstants.HOLIDAY_SCHEDULE;
import static com.stpl.app.adminconsole.util.ConstantsUtils.IndicatorConstants.HOUR;
import com.stpl.app.adminconsole.util.HelperListUtil;
import com.stpl.app.adminconsole.util.Message;
import com.stpl.app.adminconsole.util.MessageUtil;
import com.stpl.app.adminconsole.util.QueryReader;
import com.stpl.app.adminconsole.util.ResponsiveUtils;
import com.stpl.app.model.WorkflowProfile;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.service.ImtdIfpDetailsLocalServiceUtil;
import com.stpl.app.service.WorkflowProfileLocalServiceUtil;
import static com.stpl.app.adminconsole.util.ConstantsUtils.IndicatorConstants.SELECT_ONE;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.TableResultCustom;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Asha
 */
public class ProcessMonitorIndex extends CustomComponent implements View {

    public void enter(ViewChangeListener.ViewChangeEvent event) {
        return;
    }
    SessionDTO session;
    private ProcessMonitorDTO processMonitorDTO;
    public CustomFieldGroup processMonitorBinder;
    @UiField("processName")
    public TextField processName;
    @UiField("processNameLabel")
    public Label processNameLabel;
    @UiField("processType")
    public ComboBox processType;
    @UiField("processTypeLabel")
    public Label processTypeLabel;
    @UiField("calendar")
    public ComboBox calendar;
    @UiField("calendarLabel")
    public Label calendarLabel;
    @UiField("hours1")
    public ComboBox hours1;
    @UiField("hours1Label")
    public Label hours1Label;
    @UiField("hours2Label")
    public Label hours2Label;
    @UiField("hours3Label")
    public Label hours3Label;
    @UiField("hours2")
    public ComboBox hours2;
    @UiField("hours3")
    public ComboBox hours3;
    @UiField("minutes1")
    public ComboBox minutes1;
    @UiField("minutes1Label")
    public Label minutes1Label;
    @UiField("minutes2Label")
    public Label minutes2Label;
    @UiField("minutes3Label")
    public Label minutes3Label;
    @UiField("minutes2")
    public ComboBox minutes2;
    @UiField("minutes3")
    public ComboBox minutes3;
    @UiField("startDate")
    public PopupDateField startDate;
    @UiField("startDateLabel")
    public Label startDateLabel;
    @UiField("endDate")
    public PopupDateField endDate;
    @UiField("endDateLabel")
    public Label endDateLabel;
    @UiField("updateBtn")
    Button updateBtn;
    @UiField("addBtn")
    Button addBtn;
    @UiField("monitorsTable")
    public VerticalLayout monitorsTable;
    ProcessMonitorLogic tableLogic = new ProcessMonitorLogic();
    private ExtPagedTable resultTable = new ExtPagedTable(tableLogic);
    private BeanItemContainer<ProcessMonitorDTO> ProcessMonitorContainer = new BeanItemContainer(ProcessMonitorDTO.class);
    private String a = "0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23";
    private String b = "0,15,30,45";
    final CommonUtils commonsUtil = new CommonUtils();
    final static CommonDAO dao = new CommonDAOImpl();
    int sid;
    SessionDTO sessionDTO;
    private static final Logger LOGGER = Logger.getLogger(ProcessMonitorIndex.class);
    CommonSecurityLogic commonSecurity = new CommonSecurityLogic();
    public static final String MANUAL = "Manual";

    public ProcessMonitorIndex(ProcessMonitorDTO processMonitorDTO, CustomFieldGroup custom, SessionDTO sessionDTO) {
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/clara/processMonitor.xml"), this));
        this.processMonitorDTO = processMonitorDTO;
        this.processMonitorBinder = custom;
        this.sessionDTO = sessionDTO;
        configurePermission();
        configureFields();
    }

    protected void configureFields() {
        try {
            reset();
            configureScheduleProcTable();
            updateBtn.setVisible(false);
            startDate.setDateFormat(CommonUtil.MMDDYYYY);
            endDate.setDateFormat(CommonUtil.MMDDYYYY);

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    public void configureScheduleProcTable() {
        try {
            final StplSecurity stplSecurity = new StplSecurity();
            String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID));
            final Map<String, AppPermission> fieldIfpHM = stplSecurity.getFieldOrColumnPermission(userId, StringConstantUtils.PROCESS_MONITOR + ConstantsUtils.COMMA + StringConstantUtils.LANDING_SCREEN, false);
            List<Object> resultList = getFieldsForSecurity(StringConstantUtils.PROCESS_MONITOR, StringConstantUtils.LANDING_SCREEN);
            Object[] obj = ConstantsUtils.getInstance().processMonitorColumns;
            TableResultCustom tableResultCustom = commonSecurity.getTableColumnsPermission(resultList, obj, fieldIfpHM, "Add");
            if (tableResultCustom.getObjResult().length == 0) {
                resultTable.setVisible(false);
            }
            tableLogic.setPageLength(NumericConstants.FIVE);
            tableLogic.setContainerDataSource(ProcessMonitorContainer);
            tableLogic.sinkItemPerPageWithPageLength(true);

            resultTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
            resultTable.setWidth("930px");
            resultTable.setHeight("250px");
            resultTable.setPageLength(NumericConstants.FIVE);
            resultTable.setVisibleColumns(tableResultCustom.getObjResult());
            resultTable.setColumnHeaders(tableResultCustom.getObjResultHeader());

            resultTable.markAsDirtyRecursive();
            resultTable.setImmediate(true);
            resultTable.setWidth("924px");
            resultTable.setSelectable(true);
            resultTable.markAsDirty();
            resultTable.setComponentError(null);
            resultTable.setValidationVisible(false);
            resultTable.setFilterBarVisible(false);
            monitorsTable.addComponent(resultTable);
            HorizontalLayout controls = tableLogic.createControls();
            HorizontalLayout controlLayout = ResponsiveUtils.getResponsiveControls(controls);
            monitorsTable.addComponent(controlLayout);

            resultTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {
                @Override
                public void itemClick(ItemClickEvent event) {
                    if (event.isDoubleClick()) {
                        try {
                            sid = ((ProcessMonitorDTO) event.getItemId()).getSid();
                            final ProcessLogic logic = new ProcessLogic();
                            final ProcessMonitorDTO dto = logic.getProcessMonitorByID(sid);
                            setFieldValue(dto);
                            addBtn.setVisible(false);
                            updateBtn.setVisible(true);
                        } catch (Exception ex) {
                            LOGGER.error(ex);
                        }
                    }
                }
            });

            processType.addValueChangeListener(new Property.ValueChangeListener() {

                public void valueChange(Property.ValueChangeEvent event) {
                    if (MANUAL.equals(String.valueOf(processType.getValue()))) {
                        hours1.setEnabled(false);
                        hours2.setEnabled(false);
                        hours3.setEnabled(false);
                        minutes1.setEnabled(false);
                        minutes2.setEnabled(false);
                        minutes3.setEnabled(false);
                    } else {
                        hours1.setEnabled(true);
                        hours2.setEnabled(true);
                        hours3.setEnabled(true);
                        minutes1.setEnabled(true);
                        minutes2.setEnabled(true);
                        minutes3.setEnabled(true);
                    }
                }
            });
        } catch (PortalException ex) {
            LOGGER.error(ex);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    @UiHandler("addBtn")
    public void addBtn(Button.ClickEvent event) {
        try {
            final String proType = String.valueOf(processType.getValue());
            if (StringUtils.EMPTY.equals(processName.getValue())) {
                AbstractNotificationUtils.getErrorNotification(MessageUtil.getMessage(Message.NO_PROCESS_NAME_HEADER), MessageUtil.getMessage(Message.NO_PROCESS_NAME_MSG));
            } else if (CommonUtil.STRING_NULL.equals(proType) || proType == null) {
                AbstractNotificationUtils.getErrorNotification(MessageUtil.getMessage(Message.NO_PROCESS_TYPE_HEADER), MessageUtil.getMessage(Message.NO_PROCESS_TYPE_MSG));
            } else if (startDate.getValue() == null) {
                AbstractNotificationUtils.getErrorNotification(MessageUtil.getMessage(Message.NO_START_DATE_HEADER), MessageUtil.getMessage(Message.NO_START_DATE_MSG));
            } else if (endDate.getValue() == null) {
                AbstractNotificationUtils.getErrorNotification(MessageUtil.getMessage(Message.NO_END_DATE_HEADER), MessageUtil.getMessage(Message.NO_END_DATE_MSG));
            } else if (startDate.getValue().after(endDate.getValue())) {
                AbstractNotificationUtils.getErrorNotification("Error", "End date should be after Start date");
            } else if (startDate.getValue().equals(endDate.getValue())) {
                AbstractNotificationUtils.getErrorNotification("Error", "Start date and End date are equal");
            } else if (!(MANUAL.equals(proType)) && "null".equals(String.valueOf(hours1.getValue())) && "null".equals(String.valueOf(hours2.getValue())) && "null".equals(String.valueOf(hours3.getValue()))) {
                AbstractNotificationUtils.getErrorNotification(MessageUtil.getMessage(Message.ERROR_HEADER), MessageUtil.getMessage(Message.NO_RUN_TIME));
            } else if (!(MANUAL.equals(proType)) && "null".equals(String.valueOf(minutes1.getValue())) && "null".equals(String.valueOf(minutes2.getValue())) && "null".equals(String.valueOf(minutes3.getValue()))) {
                AbstractNotificationUtils.getErrorNotification(MessageUtil.getMessage(Message.ERROR_HEADER), MessageUtil.getMessage(Message.NO_RUN_TIME));
            } else if ("null".equals(String.valueOf(hours1.getValue())) && !("null".equals(String.valueOf(minutes1.getValue())))) {
                AbstractNotificationUtils.getErrorNotification(MessageUtil.getMessage(Message.NO_HOUR_HEADER), MessageUtil.getMessage(Message.NO_HOUR1_MSG));
            } else if (!("null".equals(String.valueOf(hours1.getValue()))) && ("null".equals(String.valueOf(minutes1.getValue())))) {
                AbstractNotificationUtils.getErrorNotification(MessageUtil.getMessage(Message.NO_MIN_HEADER), MessageUtil.getMessage(Message.NO_MIN1_MSG));
            } else if ("null".equals(String.valueOf(hours2.getValue())) && !("null".equals(String.valueOf(minutes2.getValue())))) {
                AbstractNotificationUtils.getErrorNotification(MessageUtil.getMessage(Message.NO_HOUR_HEADER), MessageUtil.getMessage(Message.NO_HOUR2_MSG));
            } else if (!("null".equals(String.valueOf(hours2.getValue()))) && ("null".equals(String.valueOf(minutes2.getValue())))) {
                AbstractNotificationUtils.getErrorNotification(MessageUtil.getMessage(Message.NO_MIN_HEADER), MessageUtil.getMessage(Message.NO_MIN2_MSG));
            } else if ("null".equals(String.valueOf(hours3.getValue())) && !("null".equals(String.valueOf(minutes3.getValue())))) {
                AbstractNotificationUtils.getErrorNotification(MessageUtil.getMessage(Message.NO_HOUR_HEADER), MessageUtil.getMessage(Message.NO_HOUR3_MSG));
            } else if (!("null".equals(String.valueOf(hours3.getValue()))) && ("null".equals(String.valueOf(minutes3.getValue())))) {
                AbstractNotificationUtils.getErrorNotification(MessageUtil.getMessage(Message.NO_MIN_HEADER), MessageUtil.getMessage(Message.NO_MIN3_MSG));
            } else if (!StringUtils.EMPTY.equals(processName.getValue())) {
                String query = QueryReader.getQuery("duplicateProcessName");
                Map<String, Object> input = new HashMap();
                input.put("?PROCESS_NAME", processName.getValue());
                List list = (List) QueryReader.executeSelectQuery(input, query);
                if (list.size() > 0) {
                    AbstractNotificationUtils.getErrorNotification(MessageUtil.getMessage(Message.DUPLICATE_PROCESS_HEADER), MessageUtil.getMessage(Message.DUPLICATE_PROCESS_MSG));
                } else {
                    new AbstractNotificationUtils() {
                        @Override
                        public void yesMethod() {
                            save();
                        }

                        @Override
                        public void noMethod() {
                            return;
                        }
                    }.getConfirmationMessage(MessageUtil.getMessage(Message.ADD_CONFIRMATION), MessageUtil.getMessage(Message.ADD_CONFIRMATION_MSG));
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    public void save() {
        try {
            getFieldValue(processMonitorDTO);
            ProcessLogic.add(processMonitorDTO, sessionDTO);
            tableLogic.setCurrentPage(1);
            AbstractNotificationUtils.getInfoNotification(MessageUtil.getMessage(Message.CONFIRMATION_HEADER), MessageUtil.getMessage(Message.CONFIRMATION_MSG));
            reset();
        } catch (Exception ex) {
            LOGGER.error(ex);
        }

    }

    private ProcessMonitorDTO setFieldValue(ProcessMonitorDTO dto) {
        processName.setValue(dto.getProcessName());
        processType.setValue(dto.getProcessType());
        hours1.setEnabled(true);
        hours1.setValue(HOUR.getConstant().equals(dto.getHours1()) ? SELECT_ONE.getConstant() : dto.getHours1());
        hours1.setEnabled(false);
        minutes1.setEnabled(true);
        minutes1.setValue(HOUR.getConstant().equals(dto.getMinutes1()) ? SELECT_ONE.getConstant() : dto.getMinutes1());
        minutes1.setEnabled(false);
        hours2.setEnabled(true);
        hours2.setValue(HOUR.getConstant().equals(dto.getHours2()) ? SELECT_ONE.getConstant() : dto.getHours2());
        hours2.setEnabled(false);
        minutes2.setEnabled(true);
        minutes2.setValue(HOUR.getConstant().equals(dto.getMinutes2()) ? SELECT_ONE.getConstant() : dto.getMinutes2());
        minutes2.setEnabled(false);
        hours3.setEnabled(true);
        hours3.setValue(HOUR.getConstant().equals(dto.getHours3()) ? SELECT_ONE.getConstant() : dto.getHours3());
        hours3.setEnabled(false);
        minutes3.setEnabled(true);
        minutes3.setValue(HOUR.getConstant().equals(dto.getMinutes3()) ? SELECT_ONE.getConstant() : dto.getMinutes3());
        minutes3.setEnabled(false);
        int sid = dto.getSid();
        processMonitorDTO.setSid(sid);
        startDate.setValue(dto.getStartDate());
        endDate.setValue(dto.getEndDate());
        return dto;
    }

    public ProcessMonitorDTO getProcessScheduleByID(int sid) {
        ProcessMonitorDTO dto = new ProcessMonitorDTO();
        try {
            WorkflowProfile profile = WorkflowProfileLocalServiceUtil.getWorkflowProfile(sid);
            dto.setProcessName(profile.getProcessName());
            dto.setStartDate(profile.getStartDate());
            dto.setEndDate(profile.getEndDate());
            dto.setHours1(String.valueOf(profile.getStartHour1()));
            dto.setMinutes1(String.valueOf(profile.getStartMinutes1()));
            dto.setHours2(String.valueOf(profile.getStartHour2()));
            dto.setMinutes2(String.valueOf(profile.getStartMinutes2()));
            dto.setHours3(String.valueOf(profile.getStartHour3()));
            dto.setMinutes3(String.valueOf(profile.getStartMinutes3()));
        } catch (Exception ex) {
            LOGGER.error(ex);
            return null;
        }
        return dto;
    }

    private void getFieldValue(ProcessMonitorDTO dto) {

        int sid = dto.getSid();
        dto.setSid(sid);
        dto.setProcessName(processName.getValue());
        dto.setProcessDisplayName(processName.getValue());
        dto.setProcessType((HelperDTO) processType.getValue());
        dto.setCalender(String.valueOf(calendar.getValue()));
        dto.setStartDate(startDate.getValue());
        dto.setEndDate(endDate.getValue());
        dto.setHours1(String.valueOf(hours1.getValue()));
        dto.setHours2(String.valueOf(hours2.getValue()));
        dto.setHours3(String.valueOf(hours3.getValue()));
        dto.setMinutes1(String.valueOf(minutes1.getValue()));
        dto.setMinutes2(String.valueOf(minutes2.getValue()));
        dto.setMinutes3(String.valueOf(minutes3.getValue()));
    }

    public void reset() {
        processName.setValue(ConstantsUtils.EMPTY);
        startDate.setValue(null);
        endDate.setValue(null);
        HelperListUtil helperListUtil = HelperListUtil.getInstance();
        helperListUtil.loadValuesWithListName("processmonitor");
        commonsUtil.loadComboBox(processType, ConstantsUtils.PROCESS_TYPE, false);
        calendar.addItem(HOLIDAY_SCHEDULE.getConstant());
        calendar.addItem(HOLIDAY.getConstant());
        calendar.setNullSelectionAllowed(true);
        calendar.setNullSelectionItemId(HOLIDAY_SCHEDULE.getConstant());

        hours1.addItem(SELECT_ONE.getConstant());
        hours1.setNullSelectionAllowed(true);
        hours1.setNullSelectionItemId(SELECT_ONE.getConstant());
        hours1.setValue(SELECT_ONE.getConstant());
        hours1.addItems(a.split(","));

        minutes1.addItem(SELECT_ONE.getConstant());
        minutes1.setNullSelectionAllowed(true);
        minutes1.setNullSelectionItemId(SELECT_ONE.getConstant());
        minutes1.setValue(SELECT_ONE.getConstant());
        minutes1.addItems(b.split(","));

        hours2.addItem(SELECT_ONE.getConstant());
        hours2.setNullSelectionAllowed(true);
        hours2.setNullSelectionItemId(SELECT_ONE.getConstant());
        hours2.setValue(SELECT_ONE.getConstant());
        hours2.addItems(a.split(","));

        minutes2.addItem(SELECT_ONE.getConstant());
        minutes2.setNullSelectionAllowed(true);
        minutes2.setNullSelectionItemId(SELECT_ONE.getConstant());
        minutes2.setValue(SELECT_ONE.getConstant());
        minutes2.addItems(b.split(","));

        hours3.addItem(SELECT_ONE.getConstant());
        hours3.setNullSelectionAllowed(true);
        hours3.setNullSelectionItemId(SELECT_ONE.getConstant());
        hours3.setValue(SELECT_ONE.getConstant());
        hours3.addItems(a.split(","));

        minutes3.addItem(SELECT_ONE.getConstant());
        minutes3.setNullSelectionAllowed(true);
        minutes3.setNullSelectionItemId(SELECT_ONE.getConstant());
        minutes3.setValue(SELECT_ONE.getConstant());
        minutes3.addItems(b.split(","));
        addBtn.setVisible(true);
        updateBtn.setVisible(false);
    }

    @UiHandler("updateBtn")
    public void updateBtn(Button.ClickEvent event) {
        final String proType = String.valueOf(processType.getValue());
        if (StringUtils.EMPTY.equals(processName.getValue())) {
            AbstractNotificationUtils.getErrorNotification(MessageUtil.getMessage(Message.NO_PROCESS_NAME_HEADER), MessageUtil.getMessage(Message.NO_PROCESS_NAME_MSG));
        } else if ("null".equals(proType) || proType == null) {
            AbstractNotificationUtils.getErrorNotification(MessageUtil.getMessage(Message.NO_PROCESS_TYPE_HEADER), MessageUtil.getMessage(Message.NO_PROCESS_TYPE_MSG));
        } else if (startDate.getValue() == null) {
            AbstractNotificationUtils.getErrorNotification(MessageUtil.getMessage(Message.NO_START_DATE_HEADER), MessageUtil.getMessage(Message.NO_START_DATE_MSG));
        }else if (endDate.getValue() == null) {
                AbstractNotificationUtils.getErrorNotification(MessageUtil.getMessage(Message.NO_END_DATE_HEADER), MessageUtil.getMessage(Message.NO_END_DATE_MSG));
            }
        else if (!(MANUAL.equals(proType)) && "null".equals(String.valueOf(hours1.getValue())) && "null".equals(String.valueOf(hours2.getValue())) && "null".equals(String.valueOf(hours3.getValue()))) {
                AbstractNotificationUtils.getErrorNotification(MessageUtil.getMessage(Message.ERROR_HEADER), MessageUtil.getMessage(Message.NO_RUN_TIME));
            } 
            else if (!(MANUAL.equals(proType)) && "null".equals(String.valueOf(minutes1.getValue())) && "null".equals(String.valueOf(minutes2.getValue())) && "null".equals(String.valueOf(minutes3.getValue()))) {
                AbstractNotificationUtils.getErrorNotification(MessageUtil.getMessage(Message.ERROR_HEADER), MessageUtil.getMessage(Message.NO_RUN_TIME));
            }
            else if ("null".equals(String.valueOf(hours1.getValue())) && !("null".equals(String.valueOf(minutes1.getValue())))) {
                AbstractNotificationUtils.getErrorNotification(MessageUtil.getMessage(Message.NO_HOUR_HEADER), MessageUtil.getMessage(Message.NO_HOUR1_MSG));
            }
            else if (!("null".equals(String.valueOf(hours1.getValue()))) && ("null".equals(String.valueOf(minutes1.getValue())))) {
                AbstractNotificationUtils.getErrorNotification(MessageUtil.getMessage(Message.NO_MIN_HEADER), MessageUtil.getMessage(Message.NO_MIN1_MSG));
            }
             else if ("null".equals(String.valueOf(hours2.getValue())) && !("null".equals(String.valueOf(minutes2.getValue())))) {
                AbstractNotificationUtils.getErrorNotification(MessageUtil.getMessage(Message.NO_HOUR_HEADER), MessageUtil.getMessage(Message.NO_HOUR2_MSG));
            }
             else if (!("null".equals(String.valueOf(hours2.getValue()))) && ("null".equals(String.valueOf(minutes2.getValue())))) {
                AbstractNotificationUtils.getErrorNotification(MessageUtil.getMessage(Message.NO_MIN_HEADER), MessageUtil.getMessage(Message.NO_MIN2_MSG));
            }
             else if ("null".equals(String.valueOf(hours3.getValue())) && !("null".equals(String.valueOf(minutes3.getValue())))) {
                AbstractNotificationUtils.getErrorNotification(MessageUtil.getMessage(Message.NO_HOUR_HEADER), MessageUtil.getMessage(Message.NO_HOUR3_MSG));
            }
            else if (!("null".equals(String.valueOf(hours3.getValue()))) && ("null".equals(String.valueOf(minutes3.getValue())))) {
                AbstractNotificationUtils.getErrorNotification(MessageUtil.getMessage(Message.NO_MIN_HEADER), MessageUtil.getMessage(Message.NO_MIN3_MSG));
            }else if (!StringUtils.EMPTY.equals(processName.getValue())) {
            String query = QueryReader.getQuery("duplicateProcessUpdate");
            Map<String, Object> input = new HashMap();
            input.put("?PROCESS_NAME", processName.getValue());
            input.put("?PROCESS_SID", sid);
            List list = (List) QueryReader.executeSelectQuery(input, query);
            if (list.size() > 0) {
                AbstractNotificationUtils.getErrorNotification(MessageUtil.getMessage(Message.DUPLICATE_PROCESS_HEADER), MessageUtil.getMessage(Message.DUPLICATE_PROCESS_MSG));
            } else {
                new AbstractNotificationUtils() {
                    @Override
                    public void yesMethod() {
                        getFieldValue(processMonitorDTO);
                        ProcessLogic.update(processMonitorDTO, sessionDTO);
                        tableLogic.setCurrentPage(1);
                        AbstractNotificationUtils.getInfoNotification(MessageUtil.getMessage(Message.CONFIRMATION_HEADER), MessageUtil.getMessage(Message.UPDATE_CONFIRMATION_MSG));
                        try {
                            reset();
                        } catch (Exception ex) {
                            LOGGER.error(ex);
                        }
                    }

                    @Override
                    public void noMethod() {
                        return;
                    }
                }.getConfirmationMessage(MessageUtil.getMessage(Message.ADD_CONFIRMATION), MessageUtil.getMessage(Message.UPDATE_CONFIRMATION));
            }
        }

    }

    public void configurePermission() {
        try {

            final StplSecurity stplSecurity = new StplSecurity();
            final String userId = String.valueOf(sessionDTO.getUserId());
            Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, StringConstantUtils.PROCESS_MONITOR + "," + StringConstantUtils.LANDING_SCREEN);
            if (functionHM.get("addBtn") != null && !((AppPermission) functionHM.get("addBtn")).isFunctionFlag()) {
                addBtn.setVisible(false);
            } else {
                addBtn.setVisible(true);
            }
            if (functionHM.get("processName") != null && !((AppPermission) functionHM.get("processName")).isFunctionFlag()) {
                processName.setVisible(false);
                processNameLabel.setVisible(false);

            } else {
                processName.setVisible(true);
                processNameLabel.setVisible(true);
            }
            if (functionHM.get("processType") != null && ((AppPermission) functionHM.get("processType")).isFunctionFlag()) {
                processType.setVisible(false);
                processTypeLabel.setVisible(false);

            } else {
                processType.setVisible(true);
                processTypeLabel.setVisible(true);

            }
            if (functionHM.get("calendar") != null && ((AppPermission) functionHM.get("calendar")).isFunctionFlag()) {
                calendar.setVisible(false);
                calendarLabel.setVisible(false);

            } else {
                calendar.setVisible(true);
                calendarLabel.setVisible(true);

            }
            if (functionHM.get("startDate") != null && ((AppPermission) functionHM.get("startDate")).isFunctionFlag()) {
                startDate.setVisible(false);
                startDateLabel.setVisible(false);

            } else {
                startDate.setVisible(true);
                startDateLabel.setVisible(true);

            }
            if (functionHM.get("endDate") != null && !((AppPermission) functionHM.get("endDate")).isFunctionFlag()) {
                endDate.setVisible(false);
                endDateLabel.setVisible(false);

            } else {
                endDate.setVisible(true);
                endDateLabel.setVisible(true);

            }
            if (functionHM.get("hours1") != null && !((AppPermission) functionHM.get("hours1")).isFunctionFlag()) {
                hours1.setVisible(false);
                hours1Label.setVisible(false);

            } else {
                hours1.setVisible(true);
                hours1Label.setVisible(true);

            }
            if (functionHM.get("hours2") != null && !((AppPermission) functionHM.get("hours2")).isFunctionFlag()) {
                hours2.setVisible(false);
                hours2Label.setVisible(false);
            } else {
                hours2.setVisible(true);
                hours2Label.setVisible(true);
            }
            if (functionHM.get("hours3") != null && !((AppPermission) functionHM.get("hours3")).isFunctionFlag()) {
                hours3.setVisible(false);
                hours3Label.setVisible(false);
            } else {
                hours3.setVisible(true);
                hours3Label.setVisible(true);
            }
            if (functionHM.get("minutes1") != null && !((AppPermission) functionHM.get("minutes1")).isFunctionFlag()) {
                minutes1.setVisible(false);
                minutes1Label.setVisible(false);
            } else {
                minutes1.setVisible(true);
                minutes1Label.setVisible(true);
            }
            if (functionHM.get("minutes2") != null && !((AppPermission) functionHM.get("minutes2")).isFunctionFlag()) {
                minutes2.setVisible(false);
                minutes2Label.setVisible(false);
            } else {
                minutes2.setVisible(true);
                minutes2Label.setVisible(true);
            }
            if (functionHM.get("minutes3") != null && !((AppPermission) functionHM.get("minutes3")).isFunctionFlag()) {
                minutes3.setVisible(false);
                minutes3Label.setVisible(false);
            } else {
                minutes3.setVisible(true);
                minutes3Label.setVisible(true);
            }
        } catch (PortalException ex) {
            LOGGER.error(ex);
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * Search forecast results to load table .
     *
     * @param forecastDTO the forecast dto
     * @return object of list or count
     */
    public List<Object> getFieldsForSecurity(String moduleName, String tabName) {
        List<Object> resultList = new ArrayList<>();
        try {
            resultList = ImtdIfpDetailsLocalServiceUtil.fetchFieldsForSecurity(moduleName, tabName, null, null, null);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return resultList;
    }
}
