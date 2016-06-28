/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.ui.form;

import com.stpl.app.accountclose.dto.ProcessMonitorDTO;
import com.stpl.app.accountclose.logic.ProcessLogic;
import com.stpl.app.accountclose.logic.ProcessMonitorLogic;
import com.stpl.app.accountclose.sessionutils.SessionDTO;
import com.stpl.app.accountclose.utils.AbstractNotificationUtils;
import com.stpl.app.accountclose.utils.Constants;
import static com.stpl.app.accountclose.utils.Constants.IndicatorConstants.HOLIDAY;
import static com.stpl.app.accountclose.utils.Constants.IndicatorConstants.HOLIDAY_SCHEDULE;
import static com.stpl.app.accountclose.utils.Constants.IndicatorConstants.SELECT_HOUR;
import static com.stpl.app.accountclose.utils.Constants.IndicatorConstants.SELECT_MINUTE;
import com.stpl.app.model.WorkflowProfile;
import com.stpl.app.service.WorkflowProfileLocalServiceUtil;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;
import com.stpl.app.accountclose.utils.Message;
import com.stpl.app.accountclose.utils.MessageUtil;
import org.jboss.logging.Logger;

/**
 *
 * @author mihirkumar.b
 */
public class ProcessMonitorIndex extends CustomComponent implements View {

    public void enter(ViewChangeListener.ViewChangeEvent event) {
    }
    SessionDTO session;
    private ProcessMonitorDTO processMonitorDTO;
    public CustomFieldGroup processMonitorBinder;
    @UiField("processName")
    public TextField processName;
    @UiField("processType")
    public ComboBox processType;
    @UiField("calendar")
    public ComboBox calendar;
    @UiField("hours1")
    public ComboBox hours1;
    @UiField("hours2")
    public ComboBox hours2;
    @UiField("hours3")
    public ComboBox hours3;
    @UiField("minutes1")
    public ComboBox minutes1;
    @UiField("minutes2")
    public ComboBox minutes2;
    @UiField("minutes3")
    public ComboBox minutes3;
    @UiField("email")
    public TextField email;
    @UiField("notificationMessage")
    public TextArea notificationMessage;
    @UiField("emailList")
    public TextArea emailList;
    @UiField("startDate")
    public PopupDateField startDate;
    @UiField("endDate")
    public PopupDateField endDate;
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
    private static final Logger LOGGER = Logger.getLogger(ProcessMonitorIndex.class);
    /**
     *
     * @param screenIndicator
     * @param processMonitorDTO
     * @param custom
     * @throws Exception
     */
    public ProcessMonitorIndex(ProcessMonitorDTO processMonitorDTO, CustomFieldGroup custom) throws Exception {
        setCompositionRoot(Clara.create(getClass().getResourceAsStream("/processMonitor.xml"), this));
        this.processMonitorDTO = processMonitorDTO;
        this.processMonitorBinder = custom;
        configureFields();
    }

    /**
     * Configure Scheduled Processes Table
     *
     */
    protected void configureFields() {
        try {

            reset();
            configureScheduleProcTable();
            updateBtn.setVisible(false);
            startDate.setDateFormat("MM/dd/yyyy");
            endDate.setDateFormat("MM/dd/yyyy");

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * Configure Scheduled Processes Table
     */
    public void configureScheduleProcTable() {
        tableLogic.setContainerDataSource(ProcessMonitorContainer);
        tableLogic.setPageLength(5);
        tableLogic.sinkItemPerPageWithPageLength(false);


        resultTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        resultTable.setWidth("930px");
        resultTable.setHeight("250px");
        resultTable.setPageLength(5);
        resultTable.setVisibleColumns(Constants.PROCESS_MONITOR_COLUMNS);
        resultTable.setColumnHeaders(Constants.PROCESS_MONITOR_HEADER);

        resultTable.markAsDirtyRecursive();
        resultTable.setImmediate(true);
        resultTable.setWidth("924px");
        resultTable.setSelectable(true);
        resultTable.markAsDirty();
        resultTable.setComponentError(null);
        resultTable.setValidationVisible(false);
        resultTable.setFilterBarVisible(false);
        monitorsTable.addComponent(resultTable);

        resultTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                if (event.isDoubleClick()) {
                    try {
                        final int sid = ((ProcessMonitorDTO) event.getItemId()).getSid();
                        final ProcessLogic logic = new ProcessLogic();
                        final ProcessMonitorDTO dto = logic.getProcessMonitorByID(sid);
                        setFieldValue(dto);
                        processType.setEnabled(false);
                        addBtn.setVisible(false);
                        updateBtn.setVisible(true);
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }
            }
        });
    }

    @UiHandler("addBtn")
    public void addBtn(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {
                if (processType.getValue().equals("Automatic")) {
                    automatic();
                } else if (processType.getValue().equals("Manual")) {
                    manual();
                }
            }

            @Override
            public void noMethod() {
            }
        }.getConfirmationMessage(MessageUtil.getMessage(Message.ADD_CONFIRMATION), MessageUtil.getMessage(Message.ADD_CONFIRMATION_MSG));
    }

    public void automatic() {
        try {
            if (StringUtils.EMPTY.equals(processName.getValue())) {
                  AbstractNotificationUtils.getErrorNotification(MessageUtil.getMessage(Message.NO_PROCESS_NAME_HEADER), MessageUtil.getMessage(Message.NO_PROCESS_NAME_MSG));
            } else {
                getFieldValue(processMonitorDTO);
                ProcessLogic.add(processMonitorDTO);
                tableLogic.setCurrentPage(1);
                AbstractNotificationUtils.getInfoNotification(MessageUtil.getMessage(Message.CONFIRMATION_HEADER), MessageUtil.getMessage(Message.CONFIRMATION_MSG));
                processName.setValue(StringUtils.EMPTY);
                reset();
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }

    }

    public void manual() {

        try {
            if (StringUtils.EMPTY.equals(processName.getValue())) {
                AbstractNotificationUtils.getErrorNotification(MessageUtil.getMessage(Message.NO_PROCESS_NAME_HEADER), MessageUtil.getMessage(Message.NO_PROCESS_NAME_MSG));
            } else {
                getFieldValue(processMonitorDTO);
                ProcessLogic.addManual(processMonitorDTO);
                tableLogic.setCurrentPage(1);
                AbstractNotificationUtils.getInfoNotification(MessageUtil.getMessage(Message.CONFIRMATION_HEADER), MessageUtil.getMessage(Message.CONFIRMATION_MSG));
                processName.setValue(StringUtils.EMPTY);
                reset();
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }

    }

    private ProcessMonitorDTO setFieldValue(ProcessMonitorDTO dto) {
        processName.setValue(dto.getProcessName());
        processType.setValue(dto.getProcessType());

        hours1.select("24".equals(dto.getHours1()) ? "-Select One-" : dto.getHours1());
        minutes1.select("24".equals(dto.getMinutes1()) ? "-Select One-" : dto.getMinutes1());
        hours2.select("24".equals(dto.getHours2()) ? "-Select One-" : dto.getHours2());
        minutes2.select("24".equals(dto.getMinutes2()) ? "-Select One-" : dto.getMinutes2());
        hours3.select("24".equals(dto.getHours3()) ? "-Select One-" : dto.getHours3());
        minutes3.select("24".equals(dto.getMinutes3()) ? "-Select One-" : dto.getMinutes3());
        int sid = dto.getSid();
        processMonitorDTO.setSid(sid);
        startDate.setValue(dto.getStartDate());
        endDate.setValue(dto.getEndDate());
        return dto;
    }

    private List getCustomizedSchedulerProcessing(List list) throws Exception {
        List<ProcessMonitorDTO> returnList = new ArrayList<ProcessMonitorDTO>();
        SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy");
        if (list != null && list.size() != 0) {
            for (int i = 0; i < list.size(); i++) {
                Object[] obj = (Object[]) list.get(i);
                ProcessMonitorDTO dto = new ProcessMonitorDTO();
                dto.setProcessName(String.valueOf(obj[1]));
                dto.setModifiedBy(String.valueOf(obj[9]));
                dto.setCalender(String.valueOf(obj[10]));
                dto.setSid(Integer.valueOf(String.valueOf(obj[0])));
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
                returnList.add(dto);
            }
        }
        return returnList;
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
        dto.setProcessType(String.valueOf(processType.getValue()));
        dto.setCalender(String.valueOf(calendar.getValue()));
        dto.setStartDate(startDate.getValue());
        dto.setEndDate(endDate.getValue());
        dto.setHours1(String.valueOf(hours1.getValue()));
        dto.setHours2(String.valueOf(hours2.getValue()));
        dto.setHours3(String.valueOf(hours3.getValue()));
        dto.setMinutes1(String.valueOf(minutes1.getValue()));
        dto.setMinutes2(String.valueOf(minutes2.getValue()));
        dto.setMinutes3(String.valueOf(minutes3.getValue()));
        dto.setEmail(String.valueOf(email.getValue()));
        dto.setMessage(String.valueOf(notificationMessage.getValue()));
    }

    public void reset() {
        processName.setValue(StringUtils.EMPTY);
        startDate.setValue(null);
        endDate.setValue(null);
        processType.addItem("Automatic");
        processType.addItem("Manual");
        processType.setNullSelectionAllowed(false);

        email.setNullSettingAllowed(false);
        email.setValue(StringUtils.EMPTY);
        calendar.addItem(HOLIDAY_SCHEDULE.getConstant());
        calendar.addItem(HOLIDAY.getConstant());
        calendar.setNullSelectionAllowed(true);
        calendar.setNullSelectionItemId(HOLIDAY_SCHEDULE.getConstant());

        hours1.addItem(SELECT_HOUR.getConstant());
        hours1.setNullSelectionAllowed(true);
        hours1.setNullSelectionItemId(SELECT_HOUR.getConstant());
        hours1.addItems(a.split(","));

        minutes1.addItem(SELECT_MINUTE.getConstant());
        minutes1.setNullSelectionAllowed(true);
        minutes1.setNullSelectionItemId(SELECT_MINUTE.getConstant());
        minutes1.addItems(b.split(","));

        hours2.addItem(SELECT_HOUR.getConstant());
        hours2.setNullSelectionAllowed(true);
        hours2.setNullSelectionItemId(SELECT_HOUR.getConstant());
        hours2.addItems(a.split(","));

        minutes2.addItem(SELECT_MINUTE.getConstant());
        minutes2.setNullSelectionAllowed(true);
        minutes2.setNullSelectionItemId(SELECT_MINUTE.getConstant());
        minutes2.addItems(b.split(","));

        hours3.addItem(SELECT_HOUR.getConstant());
        hours3.setNullSelectionAllowed(true);
        hours3.setNullSelectionItemId(SELECT_HOUR.getConstant());
        hours3.addItems(a.split(","));

        minutes3.addItem(SELECT_MINUTE.getConstant());
        minutes3.setNullSelectionAllowed(true);
        minutes3.setNullSelectionItemId(SELECT_MINUTE.getConstant());
        minutes3.addItems(b.split(","));
        notificationMessage.setValue(StringUtils.EMPTY);
        emailList.setValue(StringUtils.EMPTY);
        addBtn.setVisible(true);
        updateBtn.setVisible(false);
    }

    @UiHandler("updateBtn")
    public void updateBtn(Button.ClickEvent event) {
        new AbstractNotificationUtils() {
            @Override
            public void yesMethod() {
                try {
                    if (StringUtils.EMPTY.equals(processName.getValue())) {
                        AbstractNotificationUtils.getErrorNotification(MessageUtil.getMessage(Message.NO_PROCESS_NAME_HEADER), MessageUtil.getMessage(Message.NO_PROCESS_NAME_MSG));
                    } else {
                        getFieldValue(processMonitorDTO);
                        ProcessLogic.update(processMonitorDTO);
                        tableLogic.setCurrentPage(1);
                        AbstractNotificationUtils.getInfoNotification(MessageUtil.getMessage(Message.CONFIRMATION_HEADER), MessageUtil.getMessage(Message.UPDATE_CONFIRMATION_MSG));
                        processName.setValue(StringUtils.EMPTY);
                        reset();
                    }
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }

            @Override
            public void noMethod() {
            }
        }.getConfirmationMessage(MessageUtil.getMessage(Message.ADD_CONFIRMATION), MessageUtil.getMessage(Message.UPDATE_CONFIRMATION));


    }
}
