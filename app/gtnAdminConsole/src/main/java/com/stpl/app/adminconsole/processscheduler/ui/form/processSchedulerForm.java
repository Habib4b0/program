/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.processscheduler.ui.form;

import static com.stpl.app.adminconsole.processscheduler.logic.ProcessSchedulerLogic.getFtpBundleValue;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.ResourceBundle;

import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

import com.stpl.app.adminconsole.common.dto.SessionDTO;
import com.stpl.app.adminconsole.processscheduler.dto.ProcessSchedulerDTO;
import com.stpl.app.adminconsole.processscheduler.logic.ProcessSchedulerLogic;
import com.stpl.app.adminconsole.processscheduler.logic.tableLogic.ManualTableLogic;
import com.stpl.app.adminconsole.processscheduler.logic.tableLogic.ProcessSchedulerTableLogic;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.adminconsole.util.Message;
import com.stpl.app.adminconsole.util.MessageUtil;
import com.stpl.app.adminconsole.util.ResponsiveUtils;
import com.stpl.app.adminconsole.util.StringConstantUtils;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.BooleanConstant;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.event.ItemClickEvent;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.v7.ui.OptionGroup;
import com.vaadin.v7.ui.PopupDateField;
import com.vaadin.v7.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.v7.ui.VerticalLayout;
import com.vaadin.ui.Window;
import org.asi.ui.extfilteringtable.ExtCustomTable;

/**
 *
 * @author Jayaram
 */
public class processSchedulerForm extends CustomComponent {

    private static final Logger LOGGER = LoggerFactory.getLogger(processSchedulerForm.class);

    @UiField("frequencyRadio")
    private OptionGroup frequencyRadio;

    @UiField("status")
    private ComboBox status;

    @UiField("hoursOne")
    private ComboBox hoursOne;

    @UiField("hoursTwo")
    private ComboBox hoursTwo;

    @UiField("hoursThree")
    private ComboBox hoursThree;

    @UiField("minutesOne")
    private ComboBox minutesOne;

    @UiField("minutesTwo")
    private ComboBox minutesTwo;

    @UiField("minutesThree")
    private ComboBox minutesThree;

    @UiField("runOne")
    private Label runOne;
    
    @UiField("hoursLB1")
    private Label hoursLB1;
    
    @UiField("minutesLB1")
    private Label minutesLB1;
    
    @UiField("runTwo")
    private Label runTwo;
    
    @UiField("hoursLB2")
    private Label hoursLB2;
    
    @UiField("minutesLB2")
    private Label minutesLB2;
    
    @UiField("runThree")
    private Label runThree;
    
    @UiField("hoursLB3")
    private Label hoursLB3;
    
    @UiField("minutesLB3")
    private Label minutesLB3;
    
    @UiField("processName")
    private TextField processName;

    @UiField("startDate")
    private PopupDateField startDate;
    
    @UiField("endDate")
    private PopupDateField endDate;

    @UiField("scheduledProcessesTable")
    private VerticalLayout scheduledProcessesTable;

    @UiField("manualProcessesTable")
    private VerticalLayout manualProcessesTable;

    @UiField("runBtn1")
    private Button run;

    private ProcessSchedulerTableLogic tableLogic = new ProcessSchedulerTableLogic();

    private ExtPagedTable resultTable = new ExtPagedTable(tableLogic);

    private ManualTableLogic manualTabLogic = new ManualTableLogic();

    private ExtPagedTable manualProcTable = new ExtPagedTable(manualTabLogic);

    private BeanItemContainer<ProcessSchedulerDTO> manualProcSchContainerBean = new BeanItemContainer<>(ProcessSchedulerDTO.class);

    private BeanItemContainer<ProcessSchedulerDTO> resultBean = new BeanItemContainer<>(ProcessSchedulerDTO.class);

    public static final ResourceBundle COLUMN_BUNDLE = ResourceBundle.getBundle("properties.tableColumns");

    public static final ResourceBundle LIST_BUNDLE = ResourceBundle.getBundle("properties.labelname");
    public static final String MSG_ID_061 = "MSG_ID_061";
    private String key = "processscheduler";
    private boolean isload = false;
    private ProcessSchedulerDTO processDTO;
    private Date date = new Date();
    @UiField("updateBtn")
    private Button updateBtn;
    private static final ResourceBundle confirmationMessage = ResourceBundle.getBundle("properties.message");
    
    public final Object[] manualColumn = new Object[]{
        "processDisplayName", "manualLastRun"};

    public final String[] manualHeader = new String[]{
        "Process Name", "Last Run"};
    private SessionDTO sessionDTO;
    private String timeHourOne;
    private String timeHourTwo;
    private String intervalHourOne;
    private String intervalHourTwo;

    public processSchedulerForm(final SessionDTO sessionDTO) {
        super();
        this.sessionDTO = sessionDTO;
        init();
    }

    private void init() {
        try {
            LOGGER.debug("Inside Init");
            addStyleName("bootstrap");
            addStyleName("bootstrap-admin");
            setCompositionRoot(Clara.create(getClass().getResourceAsStream("/clara/processScheduler.xml"), this));
            onLoad(isload);
            configureFields();
            configureResultTable();
            final StplSecurity stplSecurity = new StplSecurity();
            final String userId = String.valueOf(sessionDTO.getUserId());
            Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, "Process Schedule");
            setButtonLevelSecurity(functionHM);
            LOGGER.debug("Ending Init");
        } catch (Exception ex) {
           LOGGER.error(ex.getMessage());
        }
    }

    private void onLoad(boolean isload) {
        runOne.setValue("Run Every:");
        minutesOne.setVisible(isload);
        minutesLB1.setVisible(isload);
        runTwo.setVisible(isload);
        hoursLB2.setCaption("minutes");
        minutesTwo.setVisible(isload);
        minutesLB2.setVisible(isload);
        runThree.setVisible(isload);
        hoursLB3.setVisible(isload);
        hoursThree.setVisible(isload);
        minutesThree.setVisible(isload);
        minutesLB3.setVisible(isload);
    }

    private void configureFields() {

        LOGGER.debug("Entering configureFields");

        frequencyRadio.addItems(StringConstantUtils.INTERVAL, "Time");
        frequencyRadio.select(StringConstantUtils.INTERVAL);
        updateBtn.setEnabled(false);

        frequencyRadioValueChangeListener();

        hourOneValueChangeListener();

        hourTwoValueChangeListener();

        startDate.setDateFormat("MM/dd/yyyy");
        endDate.setDateFormat("MM/dd/yyyy");

        statusConfig();

        hoursOneConfig();

        hoursTwoConfig();

        hoursThreeConfig();

        minutesOne.addItems(getDdlbValues(false, key));
        minutesOne.setImmediate(true);
        minutesOne.setNullSelectionAllowed(true);
        minutesOne.setNullSelectionItemId(StringConstantUtils.SELECT_ONE);

        minutesTwo.addItems(getDdlbValues(false, key));
        minutesTwo.setImmediate(true);
        minutesTwo.setNullSelectionAllowed(true);
        minutesTwo.setNullSelectionItemId(StringConstantUtils.SELECT_ONE);

        minutesThree.addItems(getDdlbValues(false, key));
        minutesThree.setImmediate(true);
        minutesThree.setNullSelectionAllowed(true);
        minutesThree.setNullSelectionItemId(StringConstantUtils.SELECT_ONE);

        statusValueChangeListener();

        LOGGER.debug("Ending configureFields");

    }

    public void hoursThreeConfig() {
        hoursThree.addItems(getDdlbValues(true, key));
        hoursThree.setImmediate(true);
        hoursThree.setNullSelectionAllowed(true);
        hoursThree.setNullSelectionItemId(StringConstantUtils.SELECT_ONE);
    }

    public void hoursTwoConfig() {
        hoursTwo.addItems(getDdlbValues(false, key));
        hoursTwo.setImmediate(true);
        hoursTwo.setNullSelectionAllowed(true);
        hoursTwo.setNullSelectionItemId(StringConstantUtils.SELECT_ONE);
    }

    public void hoursOneConfig() {
        hoursOne.addItems(getDdlbValues(true, key));
        hoursOne.setImmediate(true);
        hoursOne.setNullSelectionAllowed(true);
        hoursOne.setNullSelectionItemId(StringConstantUtils.SELECT_ONE);
    }

    public void statusConfig() {
        status.addItems(LIST_BUNDLE.getString("status" + key).split(","));
        status.setImmediate(true);
        status.setNullSelectionAllowed(true);
        status.setNullSelectionItemId(StringConstantUtils.SELECT_ONE);
    }

    public void statusValueChangeListener() {
        status.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             *
             */
            @Override
			public void valueChange(final Property.ValueChangeEvent event) {
                if (String.valueOf(status.getValue()).equalsIgnoreCase("Inactive")) {
                    processName.setEnabled(false);
                } else {
                    processName.setEnabled(true);
                }
            }
        });
    }

    public void frequencyRadioValueChangeListener() {
        frequencyRadio.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (StringConstantUtils.INTERVAL.equals(event.getProperty().getValue().toString())) {
                    isload = false;
                    onLoad(isload);
                    hoursTwo.removeAllItems();
                    hoursTwo.addItems(getDdlbValues(false, key));
                    loadIntervalHourddlb();
                } else {
                    isload = true;
                    onLoad(isload);
                    runOne.setValue("Run 1:");
                    hoursLB2.setCaption("hours");
                    hoursTwo.removeAllItems();
                    hoursTwo.addItems(getDdlbValues(true, key));
                    loadTimeHourddlb();
                    
                }
            }
            
        });
    }

    public void hourOneValueChangeListener() {
        hoursOne.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty().getValue() != null) {
                    if (StringConstantUtils.INTERVAL.equals(frequencyRadio.getValue().toString())) {
                        intervalHourOne = String.valueOf(event.getProperty().getValue());
                    } else {
                        timeHourOne = String.valueOf(event.getProperty().getValue().toString());
                    }
                }
                
            }
            
        });
    }

    public void hourTwoValueChangeListener() {
        hoursTwo.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty().getValue() != null) {
                    if (StringConstantUtils.INTERVAL.equals(frequencyRadio.getValue().toString())) {
                        intervalHourTwo = String.valueOf(event.getProperty().getValue());
                    } else {
                        timeHourTwo = String.valueOf(event.getProperty().getValue());
                    }
                    
                }
            }
        });
    }

    private void loadIntervalHourddlb() {
        hoursOne.select(intervalHourOne);
        hoursTwo.select(intervalHourTwo);
    }

    private void loadTimeHourddlb() {
        hoursOne.select(timeHourOne);
        hoursTwo.select(timeHourTwo);
    }

    private void reset() {
        LOGGER.debug("Inside Reset()");

        hoursOne.select(StringConstantUtils.SELECT_ONE);
        minutesOne.select(StringConstantUtils.SELECT_ONE);
        hoursTwo.select(StringConstantUtils.SELECT_ONE);
        minutesTwo.select(StringConstantUtils.SELECT_ONE);
        hoursThree.select(StringConstantUtils.SELECT_ONE);
        minutesThree.select(StringConstantUtils.SELECT_ONE);

        LOGGER.debug("Ending Reset()");
    }

    private void configureResultTable() {

        LOGGER.debug("Entering configureResultTable");
        addScheduleLayout();
        addManualLayout();
        configureSchulerTable();
        configureManualTable();

        for (Object header : resultTable.getVisibleColumns()) {
            resultTable.setColumnWidth(header, NumericConstants.ONE_FIVE_ZERO);
            resultTable.setColumnAlignment(header, ExtCustomTable.Align.CENTER);
        }

        resultTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                if (event.isDoubleClick()) {
                    try {
                        LOGGER.debug("Inside Double click event");
                        int sid = ((ProcessSchedulerDTO) event.getItemId()).getProcessSid();
                        final ProcessSchedulerLogic logic = new ProcessSchedulerLogic();
                        ProcessSchedulerDTO dto = logic.getProcessScheduleByID(sid);
                        processDTO = setFieldValue(dto);
                        updateBtn.setEnabled(true);

                        LOGGER.debug("Ending Double click event");
                    } catch (Exception ex) {
                       LOGGER.error(ex.getMessage());
                    }
                }
            }
        });

        LOGGER.debug("Ending configureResultTable");
    }

    private void addScheduleLayout() {
        LOGGER.debug("Inside addScheduleLayout");
        scheduledProcessesTable.addComponent(resultTable);
        HorizontalLayout layout1 = new HorizontalLayout();
        scheduledProcessesTable.addComponent(layout1);
        HorizontalLayout layout = new HorizontalLayout();
        ResponsiveUtils.getResponsiveControls(tableLogic.createControls(), layout);
        scheduledProcessesTable.addComponent(layout);
        HorizontalLayout layout2 = new HorizontalLayout();
        scheduledProcessesTable.addComponent(layout2);
        LOGGER.debug("Ending addScheduleLayout");
    }

    private void addManualLayout() {
        LOGGER.debug("Inside addManualLayout");
        manualTabLogic.sinkItemPerPageWithPageLength(true);
        manualProcessesTable.addComponent(manualProcTable);
        HorizontalLayout layout1 = new HorizontalLayout();
        manualProcessesTable.addComponent(layout1);
        HorizontalLayout layout = new HorizontalLayout();
        ResponsiveUtils.getResponsiveControls(manualTabLogic.createControls(), layout);
        manualProcessesTable.addComponent(layout);
        HorizontalLayout layout2 = new HorizontalLayout();
        manualProcessesTable.addComponent(layout2);
        LOGGER.debug("Ending addManualLayout");
    }

    private void configureSchulerTable() {
        LOGGER.debug("Inside configureSchulerTable");
        tableLogic.setContainerDataSource(resultBean);
        tableLogic.setPageLength(10);
        resultTable.setVisibleColumns(getColumns(true, key));
        resultTable.setColumnHeaders(Arrays.copyOf(getColumns(false, key), getColumns(false, key).length, String[].class));
        resultTable.markAsDirtyRecursive();
        resultTable.setImmediate(true);
		resultTable.setWidth("100%");
		resultTable.setHeight("253px");
        resultTable.setItemsPerPage(10);
        resultTable.setSelectable(true);
        resultTable.markAsDirty();
        resultTable.setComponentError(null);
        resultTable.setValidationVisible(false);
        resultTable.setFilterBarVisible(false);
        LOGGER.debug("Ending configureSchulerTable");
    }

    private void configureManualTable() {
        LOGGER.debug("Inside configureManualTable");
        manualTabLogic.setContainerDataSource(manualProcSchContainerBean);
        manualTabLogic.setPageLength(10);
        manualTabLogic.sinkItemPerPageWithPageLength(false);
        manualProcTable.setWidth("550px");
        manualProcTable.setSelectable(true);
        manualProcTable.setMultiSelect(false);
        manualProcTable.markAsDirty();
        manualProcTable.setComponentError(null);
        manualProcTable.setValidationVisible(false);
        manualProcTable.setFilterBarVisible(false);
        manualProcTable.markAsDirtyRecursive();
        manualProcTable.setImmediate(true);
        manualTabLogic.setItemsPerPage(10);
        manualProcTable.setVisibleColumns(manualColumn);
        manualProcTable.setColumnHeaders(manualHeader);

        LOGGER.debug("Ending configureManualTable");
    }

    private ProcessSchedulerDTO setFieldValue(ProcessSchedulerDTO dto) {

        frequencyRadio.select(dto.getFrequencyRadio());
        processName.setValue(dto.getProcessDisplayName());
        status.select(dto.getStatus());
        if (StringConstantUtils.INTERVAL.equals(dto.getFrequencyRadio())) {
            hoursOne.select("24".equals(dto.getRunHours()) ? StringConstantUtils.SELECT_ONE : dto.getRunHours());
            hoursTwo.select("24".equals(dto.getRunMinutes()) ? StringConstantUtils.SELECT_ONE : dto.getRunMinutes());
        } else {
            hoursOne.select("24".equals(dto.getHoursOne()) ? StringConstantUtils.SELECT_ONE : dto.getHoursOne());
            minutesOne.select("24".equals(dto.getMinutesOne()) ? StringConstantUtils.SELECT_ONE : dto.getMinutesOne());
            hoursTwo.select("24".equals(dto.getHoursTwo()) ? StringConstantUtils.SELECT_ONE : dto.getHoursTwo());
            minutesTwo.select("24".equals(dto.getMinutesTwo()) ? StringConstantUtils.SELECT_ONE : dto.getMinutesTwo());
            hoursThree.select("24".equals(dto.getHoursThree()) ? StringConstantUtils.SELECT_ONE : dto.getHoursThree());
            minutesThree.select("24".equals(dto.getMinutesThree()) ? StringConstantUtils.SELECT_ONE : dto.getMinutesThree());
        }
        startDate.setValue(dto.getStartDate());
        endDate.setValue(dto.getEndDate());
        return dto;
    }

    private Object[] getColumns(boolean isColumns, String key)  {
        return (COLUMN_BUNDLE.getString(isColumns ? "columns" + key : "headers" + key)).split(",");
    }

    private Object[] getDdlbValues(boolean hours, String key) {
        return (LIST_BUNDLE.getString(hours ? "hours" + key : "minutes" + key)).split(",");
    }

    @UiHandler("updateBtn")
    public void updateBtn(Button.ClickEvent event) {
        try {
            if ((StringConstantUtils.INACTIVE).equals(String.valueOf(status.getValue()))) {
                processDTO.setStartDate((Date) (startDate.getValue() == null ? date : startDate.getValue()));
                processDTO.setEndDate((Date) (endDate.getValue() == null ? date : endDate.getValue()));
                getFieldValueInactive(processDTO);
                ProcessSchedulerLogic.update(processDTO);
                Notification notif = null;
                notif = new Notification(confirmationMessage.getString("MSG_ID_034"), Notification.Type.HUMANIZED_MESSAGE);
                notif.setPosition(Position.MIDDLE_CENTER);
                notif.setStyleName(ConstantsUtils.MY_STYLE);
                notif.setDelayMsec(NumericConstants.THREE_THOUSAND);
                notif.show(Page.getCurrent());
                tableLogic.setCurrentPage(1);
                reset();
                processName.setValue("");
                startDate.setValue(null);
                endDate.setValue(null);
                status.select(StringConstantUtils.SELECT_ONE);
                updateBtn.setEnabled(false);
            } else if (check()) {
                if (checkStartDateValidation() && hourAndMinValidation()) {
                    getFieldValue(processDTO);
                    ProcessSchedulerLogic.update(processDTO);
                    Notification notif = null;
                    notif = new Notification(confirmationMessage.getString("MSG_ID_034"), Notification.Type.HUMANIZED_MESSAGE);
                    notif.setPosition(Position.MIDDLE_CENTER);
                    notif.setStyleName(ConstantsUtils.MY_STYLE);
                    notif.setDelayMsec(NumericConstants.THREE_THOUSAND);
                    notif.show(Page.getCurrent());
                    tableLogic.setCurrentPage(1);
                    reset();
                    processName.setValue("");
                    startDate.setValue(null);
                    endDate.setValue(null);
                    status.select(StringConstantUtils.SELECT_ONE);
                    updateBtn.setEnabled(false);
                }
            } else {
                AbstractNotificationUtils.getErrorNotification(MessageUtil.getMessage(Message.ERROR_HEADER), confirmationMessage.getString(MSG_ID_061));
            }
        } catch (Exception ex) {
           LOGGER.error(ex.getMessage());
        }
    }

    private boolean check() {
        if (("Time".equals(String.valueOf(frequencyRadio.getValue()))) && ("".equals(processName.getValue()) || status.getValue() == null
                || startDate.getValue() == null || endDate.getValue() == null
                || frequencyRadio.getValue() == null)) {
            return false;
        } else return !(("Interval".equals(String.valueOf(frequencyRadio.getValue()))) && ("".equals(processName.getValue()) || status.getValue() == null
                || startDate.getValue() == null || endDate.getValue() == null
                || frequencyRadio.getValue() == null ));
    }

    private void getFieldValue(ProcessSchedulerDTO dto) {
        dto.setProcessDisplayName(processName.getValue());
        dto.setFrequencyRadio(frequencyRadio.getValue().toString());
        dto.setStatus(String.valueOf(status.getValue()));
        dto.setStartDate(startDate.getValue());
        dto.setEndDate(endDate.getValue());
        if (StringConstantUtils.INTERVAL.equals(dto.getFrequencyRadio())) {
            dto.setRunHours(String.valueOf(hoursOne.getValue()));
            dto.setRunMinutes(String.valueOf(hoursTwo.getValue()));
            dto.setHoursOne(String.valueOf(NumericConstants.TWENTY_FOUR));
            dto.setHoursTwo(String.valueOf(NumericConstants.TWENTY_FOUR));
            dto.setHoursThree(String.valueOf(NumericConstants.TWENTY_FOUR));
            dto.setMinutesOne(String.valueOf(NumericConstants.SIXTY));
            dto.setMinutesTwo(String.valueOf(NumericConstants.SIXTY));
            dto.setMinutesThree(String.valueOf(NumericConstants.SIXTY));
        } else {
            dto.setHoursOne(String.valueOf(hoursOne.getValue()));
            dto.setHoursTwo(String.valueOf(hoursTwo.getValue()));
            dto.setHoursThree(String.valueOf(hoursThree.getValue()));
            dto.setMinutesOne(String.valueOf(minutesOne.getValue()));
            dto.setMinutesTwo(String.valueOf(minutesTwo.getValue()));
            dto.setMinutesThree(String.valueOf(minutesThree.getValue()));
            dto.setRunHours(String.valueOf(NumericConstants.TWENTY_FOUR));
            dto.setRunMinutes(String.valueOf(NumericConstants.SIXTY));
        }
    }

    private void getFieldValueInactive(ProcessSchedulerDTO dto) {
        dto.setProcessDisplayName(processName.getValue());
        dto.setFrequencyRadio(frequencyRadio.getValue().toString());
        dto.setStatus(String.valueOf(status.getValue()));
        if (StringConstantUtils.INTERVAL.equals(dto.getFrequencyRadio())) {
            dto.setRunHours(String.valueOf(hoursOne.getValue() == null ? String.valueOf(NumericConstants.TWENTY_FOUR) : String.valueOf(hoursOne.getValue())));
            dto.setRunMinutes(String.valueOf(hoursTwo.getValue() == null ? String.valueOf(NumericConstants.SIXTY) : String.valueOf(hoursTwo.getValue())));
            dto.setHoursOne(String.valueOf(NumericConstants.TWENTY_FOUR));
            dto.setHoursTwo(String.valueOf(NumericConstants.TWENTY_FOUR));
            dto.setHoursThree(String.valueOf(NumericConstants.TWENTY_FOUR));
            dto.setMinutesOne(String.valueOf(NumericConstants.SIXTY));
            dto.setMinutesTwo(String.valueOf(NumericConstants.SIXTY));
            dto.setMinutesThree(String.valueOf(NumericConstants.SIXTY));
        } else {
            dto.setHoursOne(String.valueOf(hoursOne.getValue() == null ? String.valueOf(NumericConstants.TWENTY_FOUR) : String.valueOf(hoursOne.getValue())));
            dto.setHoursTwo(String.valueOf(hoursTwo.getValue() == null ? String.valueOf(NumericConstants.TWENTY_FOUR) : String.valueOf(hoursTwo.getValue())));
            dto.setHoursThree(String.valueOf(hoursThree.getValue() == null ? String.valueOf(NumericConstants.TWENTY_FOUR) : String.valueOf(hoursThree.getValue())));
            dto.setMinutesOne(String.valueOf(minutesOne.getValue() == null ? String.valueOf(NumericConstants.SIXTY) : String.valueOf(minutesOne.getValue())));
            dto.setMinutesTwo(String.valueOf(minutesTwo.getValue() == null ? String.valueOf(NumericConstants.SIXTY) : String.valueOf(minutesTwo.getValue())));
            dto.setMinutesThree(String.valueOf(minutesThree.getValue() == null ? String.valueOf(NumericConstants.SIXTY) : String.valueOf(minutesThree.getValue())));
            dto.setRunHours(String.valueOf(NumericConstants.TWENTY_FOUR));
            dto.setRunMinutes(String.valueOf(NumericConstants.SIXTY));
        }
    }

    @UiHandler("runBtn1")
    public void runBtn1(Button.ClickEvent event) {
        ProcessSchedulerLogic manualSchedule = new ProcessSchedulerLogic();
        ProcessSchedulerLogic logic = new ProcessSchedulerLogic();
        ProcessSchedulerDTO processSchedulerDTO = (ProcessSchedulerDTO) manualProcTable.getValue();
        final Date tempDate = new Date();
        final SimpleDateFormat fmtID = new SimpleDateFormat("hhmmssms");
        sessionDTO.setSessionId(fmtID.format(tempDate));
        boolean notificationFlag = false;
        if (manualProcTable.getValue() == null) {
            AbstractNotificationUtils.getErrorNotification(MessageUtil.getMessage(Message.ERROR_HEADER), confirmationMessage.getString("MSG_ID_044"));
        } else if (logic.checkForRunning(processSchedulerDTO.getProcessName())) {
            AbstractNotificationUtils.getInfoNotification(processSchedulerDTO.getProcessName() + " Manual process underway.", "Selected record " + processSchedulerDTO.getProcessName() + " is currently being processed. Please wait till the process is completed, to run the interface again.");
        } else if ("HIERARCHY_DEFINITION_OUTBOUND".equals(processSchedulerDTO.getProcessName())) {
            HierarchyOutboundLookUp hierarchyOutboundLookUp = new HierarchyOutboundLookUp();
            UI.getCurrent().addWindow(hierarchyOutboundLookUp);
        } else if ("RELATIONSHIP_BUILDER_OUTBOUND".equals(processSchedulerDTO.getProcessName())) {
            RelationshipOutboundProcess obprocess = new RelationshipOutboundProcess();
            UI.getCurrent().addWindow(obprocess);
        } else if ("ACCRUAL_RATE_PROJECTION_INTERFACE".equals(processSchedulerDTO.getProcessName())) {
            logic.callSriptForArp(sessionDTO.getUserId(), sessionDTO.getArpSessionId());
            manualSchedule.updateLastRun(processSchedulerDTO.getProcessSid(), false);
            notificationFlag = true;
        } else if (ConstantsUtils.ADJ_RESERVER_DETAIL_INTERFACE.equals(processSchedulerDTO.getProcessName()) || ConstantsUtils.ADJ_GTN_DETAIL_INTERFACE.equals(processSchedulerDTO.getProcessName())) {
            logic.updateCheckRecord(processSchedulerDTO.getProcessName(), sessionDTO.getUserId(), sessionDTO.getArpSessionId());
            manualSchedule.runJob(getFtpBundleValue(), processSchedulerDTO.getScriptName());
            manualSchedule.updateLastRun(processSchedulerDTO.getProcessSid(), false);
            notificationFlag = true;
        } else {

            try {
                if ("CFF_OUTBOUND_INTERFACE".equals(processSchedulerDTO.getProcessName())) {

                    final CFFSearchLookUp lookUp = new CFFSearchLookUp(sessionDTO, processSchedulerDTO.getScriptName(), processSchedulerDTO.getProcessSid().toString(), processSchedulerDTO.getProcessDisplayName());
                    UI.getCurrent().addWindow(lookUp);
                    lookUp.addCloseListener(new Window.CloseListener() {

                        @Override
                        public void windowClose(Window.CloseEvent e) {
                            lookUp.deleteOnClose();
                            lookUp.close();
                        }
                    });

                } else {
                    manualSchedule.runJob(getFtpBundleValue(), processSchedulerDTO.getScriptName());
                    manualSchedule.updateLastRun(processSchedulerDTO.getProcessSid(), false);
                    notificationFlag = true;
                }

            } catch (Exception e) {
                LOGGER.error(e.getMessage());
                AbstractNotificationUtils.getErrorNotification(MessageUtil.getMessage(Message.ERROR_HEADER), processSchedulerDTO.getProcessDisplayName() + confirmationMessage.getString("MSG_ID_046"));
            }
        }

        if (notificationFlag) {
            Notification notif = new Notification(processSchedulerDTO.getProcessDisplayName() + " " + confirmationMessage.getString("MSG_ID_045"), Notification.Type.HUMANIZED_MESSAGE);
            notif.setPosition(Position.MIDDLE_CENTER);
            notif.setStyleName(ConstantsUtils.MY_STYLE);
            notif.show(Page.getCurrent());
            notif.setDelayMsec(NumericConstants.TWO_THOUSAND);
        }
    }

    private void setButtonLevelSecurity(Map<String, AppPermission> functionHM) {
        if (functionHM.get("updateBtn") != null && ((AppPermission) functionHM.get("updateBtn")).isFunctionFlag()) {
            updateBtn.setVisible(BooleanConstant.getTrueFlag());
        } else {
            updateBtn.setVisible(BooleanConstant.getFalseFlag());
        }

        if (functionHM.get("runBtn1") != null && ((AppPermission) functionHM.get("runBtn1")).isFunctionFlag()) {
            run.setVisible(BooleanConstant.getTrueFlag());
        } else {
            run.setVisible(BooleanConstant.getFalseFlag());
        }
    }

    private boolean checkStartDateValidation() {
        if (startDate.getValue().after(endDate.getValue())) {
            AbstractNotificationUtils.getErrorNotification("Error", "End date should be after Start date");
            return false;
        } else if (startDate.getValue().equals(endDate.getValue())) {
            AbstractNotificationUtils.getErrorNotification("Error", "Start date and End date are equal");
            return false;
            } else{
            return true;
        }
    }

    private boolean hourAndMinValidation() {

        if ("Time".equals(String.valueOf(frequencyRadio.getValue()))) {
            if (null == hoursOne.getValue() || (null == minutesOne.getValue())) {
                getNotification(Message.ERROR_HEADER, MSG_ID_061);
                return false;
            } else if (null == hoursOne.getValue()) {
                getNotification(Message.NO_HOUR_HEADER, Message.NO_HOUR1_MSG);
                return false;
            } else if ((null != hoursOne.getValue()) && (null == (minutesOne.getValue()))) {
                getNotification(Message.NO_MIN_HEADER, Message.NO_MIN1_MSG);
                return false;
            } else if (null == hoursTwo.getValue() && (null != minutesTwo.getValue())) {
                getNotification(Message.NO_HOUR_HEADER, Message.NO_HOUR2_MSG);
                return false;
            } else if ((null != hoursTwo.getValue()) && (null == (minutesTwo.getValue()))) {
                getNotification(Message.NO_MIN_HEADER, Message.NO_MIN2_MSG);
                return false;
            } else if (null == hoursThree.getValue() && (null != minutesThree.getValue())) {
                getNotification(Message.NO_HOUR_HEADER, Message.NO_HOUR3_MSG);
                return false;
            } else if ((null != hoursThree.getValue()) && (null == (minutesThree.getValue()))) {
                getNotification(Message.NO_MIN_HEADER, Message.NO_MIN3_MSG);
                return false;
            } else {
                return true;
            }
        } else {
            if (null == hoursOne.getValue() && null == hoursTwo.getValue()) {
                getNotification(Message.ERROR_HEADER, MSG_ID_061);
                return false;
            } else if (null == hoursOne.getValue()) {
                getNotification(Message.NO_HOUR_HEADER, Message.NO_HOUR_MSG);
                return false;
            } else if (null == hoursTwo.getValue()) {
                getNotification(Message.NO_MIN_HEADER, Message.NO_MIN_MSG);
                return false;
            } else {
                return true;
            }
        }
    }
    private void getNotification(String messageHeader,String messageBody){
        AbstractNotificationUtils.getErrorNotification(MessageUtil.getMessage(messageHeader), MessageUtil.getMessage(messageBody));
    }
    
}
