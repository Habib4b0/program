/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.processscheduler.ui.form;

import com.stpl.app.adminconsole.common.dto.SessionDTO;
import com.stpl.app.adminconsole.common.util.CommonUtil;
import com.stpl.app.adminconsole.processscheduler.dto.ProcessSchedulerDTO;
import com.stpl.app.adminconsole.processscheduler.logic.ProcessSchedulerLogic;
import static com.stpl.app.adminconsole.processscheduler.logic.ProcessSchedulerLogic.getFtpBundleValue;
import com.stpl.app.adminconsole.processscheduler.logic.tableLogic.ManualTableLogic;
import com.stpl.app.adminconsole.processscheduler.logic.tableLogic.ProcessSchedulerTableLogic;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.adminconsole.util.Message;
import com.stpl.app.adminconsole.util.MessageUtil;
import com.stpl.app.adminconsole.util.ResponsiveUtils;
import com.stpl.app.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.CommonSecurityLogic;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.Page;
import com.vaadin.shared.Position;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import java.util.Arrays;
import java.util.Map;
import java.util.ResourceBundle;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Jayaram
 */
public class processSchedulerForm extends CustomComponent {

    private static final Logger LOGGER = Logger.getLogger(processSchedulerForm.class);

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

    ProcessSchedulerTableLogic tableLogic = new ProcessSchedulerTableLogic();

    private ExtPagedTable resultTable = new ExtPagedTable(tableLogic);

    public ManualTableLogic manualTabLogic = new ManualTableLogic();

    private ExtPagedTable manualProcTable = new ExtPagedTable(manualTabLogic);

    private BeanItemContainer<ProcessSchedulerDTO> manualProcSchContainerBean = new BeanItemContainer<ProcessSchedulerDTO>(ProcessSchedulerDTO.class);

    private BeanItemContainer<ProcessSchedulerDTO> resultBean = new BeanItemContainer<>(ProcessSchedulerDTO.class);

    public static ResourceBundle columnBundle = ResourceBundle.getBundle("properties.tableColumns");

    public static ResourceBundle listBundle = ResourceBundle.getBundle("properties.labelname");
    private String key = "processscheduler";
    boolean isload = false;
    ProcessSchedulerDTO ProcessDTO;
    @UiField("updateBtn")
    private Button updateBtn;
    private static final ResourceBundle confirmationMessage = ResourceBundle.getBundle("properties.message");
    public static final Object[] MANUAL_COLUMN = new Object[]{
        "processDisplayName", "manualLastRun"};

    public static final String[] MANUAL_HEADER = new String[]{
        "Process Name", "Last Run"};
    CommonUtil commonUtil = new CommonUtil();
    CommonSecurityLogic commonSecurity = new CommonSecurityLogic();
    SessionDTO sessionDTO;

    public processSchedulerForm(final SessionDTO sessionDTO) {
        super();
        this.sessionDTO = sessionDTO;
        init();
    }

    private void init() {
        try {
            LOGGER.info("Inside Init");
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
            LOGGER.info("Ending Init");
        } catch (Exception ex) {
            LOGGER.error(ex);
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

    private void configureFields() throws Exception {

        LOGGER.info("Entering configureFields");

        frequencyRadio.addItems("Interval", "Time");
        frequencyRadio.select("Interval");
        updateBtn.setEnabled(false);

        frequencyRadio.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if ("Interval".equals(event.getProperty().getValue().toString())) {
                    reset();
                    isload = false;
                    onLoad(isload);
                    hoursTwo.removeAllItems();
                    hoursTwo.addItems(getDdlbValues(false, key));
                } else {
                    reset();
                    isload = true;
                    onLoad(isload);
                    runOne.setValue("Run 1:");
                    hoursLB2.setCaption("hours");
                    hoursTwo.removeAllItems();
                    hoursTwo.addItems(getDdlbValues(true, key));
                }
            }
        });

        startDate.setDateFormat("MM/dd/yyyy");
        endDate.setDateFormat("MM/dd/yyyy");

        status.addItems(listBundle.getString("status" + key).split(","));
        status.setImmediate(true);
        status.setNullSelectionAllowed(true);
        status.setNullSelectionItemId("-Select One-");

        hoursOne.addItems(getDdlbValues(true, key));
        hoursOne.setImmediate(true);
        hoursOne.setNullSelectionAllowed(true);
        hoursOne.setNullSelectionItemId("-Select One-");

        hoursTwo.addItems(getDdlbValues(false, key));
        hoursTwo.setImmediate(true);
        hoursTwo.setNullSelectionAllowed(true);
        hoursTwo.setNullSelectionItemId("-Select One-");

        hoursThree.addItems(getDdlbValues(true, key));
        hoursThree.setImmediate(true);
        hoursThree.setNullSelectionAllowed(true);
        hoursThree.setNullSelectionItemId("-Select One-");

        minutesOne.addItems(getDdlbValues(false, key));
        minutesOne.setImmediate(true);
        minutesOne.setNullSelectionAllowed(true);
        minutesOne.setNullSelectionItemId("-Select One-");

        minutesTwo.addItems(getDdlbValues(false, key));
        minutesTwo.setImmediate(true);
        minutesTwo.setNullSelectionAllowed(true);
        minutesTwo.setNullSelectionItemId("-Select One-");

        minutesThree.addItems(getDdlbValues(false, key));
        minutesThree.setImmediate(true);
        minutesThree.setNullSelectionAllowed(true);
        minutesThree.setNullSelectionItemId("-Select One-");

        LOGGER.info("Ending configureFields");

    }

    private void reset() {
        LOGGER.info("Inside Reset()");

        hoursOne.select("-Select One-");
        minutesOne.select("-Select One-");
        hoursTwo.select("-Select One-");
        minutesTwo.select("-Select One-");
        hoursThree.select("-Select One-");
        minutesThree.select("-Select One-");

        LOGGER.info("Ending Reset()");
    }

    private void configureResultTable() throws Exception {

        LOGGER.info("Entering configureResultTable");
        addScheduleLayout();
        addManualLayout();
        configureSchulerTable();
        configureManualTable();

        for (Object header : resultTable.getVisibleColumns()) {
            resultTable.setColumnWidth(header, 150);
            resultTable.setColumnAlignment(header, ExtCustomTable.Align.CENTER);
        }
        tableLogic.setPageLength(5);

        resultTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent event) {
                if (event.isDoubleClick()) {
                    try {
                        LOGGER.info("Inside Double click event");
                        int sid = ((ProcessSchedulerDTO) event.getItemId()).getProcessSid();
                        final ProcessSchedulerLogic logic = new ProcessSchedulerLogic();
                        ProcessSchedulerDTO dto = logic.getProcessScheduleByID(sid);
                        ProcessDTO = setFieldValue(dto);
                        updateBtn.setEnabled(true);

                        LOGGER.info("Ending Double click event");
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }
            }
        });

        LOGGER.info("Ending configureResultTable");
    }

    private void addScheduleLayout() {
        LOGGER.info("Inside addScheduleLayout");
        scheduledProcessesTable.addComponent(resultTable);
        HorizontalLayout layout1 = new HorizontalLayout();
        scheduledProcessesTable.addComponent(layout1);
        HorizontalLayout layout = new HorizontalLayout();
        ResponsiveUtils.getResponsiveControls(tableLogic.createControls(), layout);
        scheduledProcessesTable.addComponent(layout);
        HorizontalLayout layout2 = new HorizontalLayout();
        scheduledProcessesTable.addComponent(layout2);
        LOGGER.info("Ending addScheduleLayout");
    }

    private void addManualLayout() {
        LOGGER.info("Inside addManualLayout");
        manualTabLogic.sinkItemPerPageWithPageLength(true);
        manualProcessesTable.addComponent(manualProcTable);
        HorizontalLayout layout1 = new HorizontalLayout();
        manualProcessesTable.addComponent(layout1);
        LOGGER.info("Ending addManualLayout");
    }

    private void configureSchulerTable() throws Exception {
        LOGGER.info("Inside configureSchulerTable");
        tableLogic.setContainerDataSource(resultBean);
        tableLogic.setPageLength(5);
        resultTable.setVisibleColumns(getColumns(true, key));
        resultTable.setColumnHeaders(Arrays.copyOf(getColumns(false, key), getColumns(false, key).length, String[].class));
        resultTable.markAsDirtyRecursive();
        resultTable.setImmediate(true);
        resultTable.setWidth("967px");
        resultTable.setHeight("253px");
        resultTable.setSelectable(true);
        resultTable.markAsDirty();
        resultTable.setComponentError(null);
        resultTable.setValidationVisible(false);
        resultTable.setFilterBarVisible(false);
        LOGGER.info("Ending configureSchulerTable");
    }

    private void configureManualTable() throws Exception {
        LOGGER.info("Inside configureManualTable");
        manualTabLogic.setContainerDataSource(manualProcSchContainerBean);
        manualTabLogic.setPageLength(15);
        manualProcTable.setWidth("490px");
        manualProcTable.setHeight("560px");
        manualProcTable.setSelectable(true);
        manualProcTable.setMultiSelect(false);
        HorizontalLayout controls = manualTabLogic.createControls();
        HorizontalLayout controlLayout = ResponsiveUtils.getResponsiveControls(controls);
        manualProcessesTable.addComponent(controlLayout);
        manualProcTable.setContainerDataSource(manualProcSchContainerBean);

        manualProcTable.setVisibleColumns(MANUAL_COLUMN);
        manualProcTable.setColumnHeaders(MANUAL_HEADER);

        LOGGER.info("Ending configureManualTable");
    }

    private ProcessSchedulerDTO setFieldValue(ProcessSchedulerDTO dto) {

        frequencyRadio.select(dto.getFrequencyRadio());
        processName.setValue(dto.getProcessDisplayName());
        status.select(dto.getStatus());
        if ("Interval".equals(dto.getFrequencyRadio())) {
            hoursOne.select("24".equals(dto.getRunHours()) ? "-Select One-" : dto.getRunHours());
            hoursTwo.select("24".equals(dto.getRunMinutes()) ? "-Select One-" : dto.getRunMinutes());
        } else {
            hoursOne.select("24".equals(dto.getHoursOne()) ? "-Select One-" : dto.getHoursOne());
            minutesOne.select("24".equals(dto.getMinutesOne()) ? "-Select One-" : dto.getMinutesOne());
            hoursTwo.select("24".equals(dto.getHoursTwo()) ? "-Select One-" : dto.getHoursTwo());
            minutesTwo.select("24".equals(dto.getMinutesTwo()) ? "-Select One-" : dto.getMinutesTwo());
            hoursThree.select("24".equals(dto.getHoursThree()) ? "-Select One-" : dto.getHoursThree());
            minutesThree.select("24".equals(dto.getMinutesThree()) ? "-Select One-" : dto.getMinutesThree());
        }
        startDate.setValue(dto.getStartDate());
        endDate.setValue(dto.getEndDate());
        return dto;
    }

    private Object[] getColumns(boolean isColumns, String key) throws Exception {
        return (columnBundle.getString(isColumns ? "columns" + key : "headers" + key)).split(",");
    }

    private Object[] getDdlbValues(boolean hours, String key) {
        return ((listBundle.getString(hours ? "hours" + key : "minutes" + key)).split(","));
    }

    @UiHandler("updateBtn")
    public void updateBtn(Button.ClickEvent event) {
        try {
            if (check()) {
                if(checkStartDateValidation()){
                getFieldValue(ProcessDTO);
                ProcessSchedulerLogic.update(ProcessDTO, sessionDTO);
                Notification notif = null;
                notif = new Notification(confirmationMessage.getString("MSG_ID_034"), Notification.Type.HUMANIZED_MESSAGE);
                notif.setPosition(Position.MIDDLE_CENTER);
                notif.setStyleName(ConstantsUtils.MY_STYLE);
                notif.setDelayMsec(3000);
                notif.show(Page.getCurrent());
                tableLogic.setCurrentPage(1);
                reset();
                processName.setValue("");
                startDate.setValue(null);
                endDate.setValue(null);
                status.select("-Select One-");
                updateBtn.setEnabled(false);
                }
            } else {
                AbstractNotificationUtils.getErrorNotification(MessageUtil.getMessage(Message.ERROR_HEADER), confirmationMessage.getString("MSG_ID_047"));
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    private boolean check() {
        if ("Interval".equals(frequencyRadio.getValue())) {
            if ("".equals(processName.getValue()) && status.getValue() == null
                    && startDate.getValue() == null && endDate.getValue() == null
                    && hoursOne.getValue() == null && hoursTwo.getValue() == null) {
                return false;
            } else {
                return true;
            }
        } else {
            if ("".equals(processName.getValue()) && status.getValue() == null
                    && startDate.getValue() == null && endDate.getValue() == null
                    && hoursOne.getValue() == null && minutesOne.getValue() == null
                    && hoursTwo.getValue() == null && minutesTwo.getValue() == null
                    && hoursThree.getValue() == null && minutesThree.getValue() == null) {
                return false;
            } else {
                return true;
            }
        }
    }

    private void getFieldValue(ProcessSchedulerDTO dto) {
        dto.setProcessDisplayName(processName.getValue());
        dto.setFrequencyRadio(frequencyRadio.getValue().toString());
        dto.setStatus(String.valueOf(status.getValue()));
        dto.setStartDate(startDate.getValue());
        dto.setEndDate(endDate.getValue());
        if ("Interval".equals(dto.getFrequencyRadio())) {
            dto.setRunHours(String.valueOf(hoursOne.getValue()));
            dto.setRunMinutes(String.valueOf(hoursTwo.getValue()));
            dto.setHoursOne(String.valueOf(24));
            dto.setHoursTwo(String.valueOf(24));
            dto.setHoursThree(String.valueOf(24));
            dto.setMinutesOne(String.valueOf(60));
            dto.setMinutesTwo(String.valueOf(60));
            dto.setMinutesThree(String.valueOf(60));
        } else {
            dto.setHoursOne(String.valueOf(hoursOne.getValue()));
            dto.setHoursTwo(String.valueOf(hoursTwo.getValue()));
            dto.setHoursThree(String.valueOf(hoursThree.getValue()));
            dto.setMinutesOne(String.valueOf(minutesOne.getValue()));
            dto.setMinutesTwo(String.valueOf(minutesTwo.getValue()));
            dto.setMinutesThree(String.valueOf(minutesThree.getValue()));
            dto.setRunHours(String.valueOf(24));
            dto.setRunMinutes(String.valueOf(60));
        }
    }

    @UiHandler("runBtn1")
    public void runBtn1(Button.ClickEvent event) {
        ProcessSchedulerDTO processSchedulerDTO = (ProcessSchedulerDTO) manualProcTable.getValue();
        if (manualProcTable.getValue() == null) {
            AbstractNotificationUtils.getErrorNotification(MessageUtil.getMessage(Message.ERROR_HEADER), confirmationMessage.getString("MSG_ID_044"));
        } else if ("HIERARCHY_DEFINITION_OUTBOUND".equals(processSchedulerDTO.getProcessName())) {
            HierarchyOutboundLookUp hierarchyOutboundLookUp = new HierarchyOutboundLookUp();
            UI.getCurrent().addWindow(hierarchyOutboundLookUp);
        } else if ("RELATIONSHIP_BUILDER_OUTBOUND".equals(processSchedulerDTO.getProcessName())) {
            RelationshipOutboundProcess obprocess = new RelationshipOutboundProcess();
            UI.getCurrent().addWindow(obprocess);
        } else {
            ProcessSchedulerLogic manualSchedule = new ProcessSchedulerLogic();
            try {
                if ("CFF_OUTBOUND_INTERFACE".equals(processSchedulerDTO.getProcessName())) {
                    final CFFSearchLookUp lookUp = new CFFSearchLookUp(sessionDTO);
                    UI.getCurrent().addWindow(lookUp);

                } else {
                    manualSchedule.runJob(getFtpBundleValue(), processSchedulerDTO.getScriptName());
                    manualSchedule.updateLastRun(processSchedulerDTO.getProcessSid(), false);

                    Notification notif = new Notification(processSchedulerDTO.getProcessDisplayName() + confirmationMessage.getString("MSG_ID_045"), Notification.Type.HUMANIZED_MESSAGE);
                    notif.setPosition(Position.MIDDLE_CENTER);
                    notif.setStyleName(ConstantsUtils.MY_STYLE);
                    notif.show(Page.getCurrent());
                    notif.setDelayMsec(2000);
                }

            } catch (Exception e) {
                LOGGER.error(e);
                AbstractNotificationUtils.getErrorNotification(MessageUtil.getMessage(Message.ERROR_HEADER), processSchedulerDTO.getProcessDisplayName() + confirmationMessage.getString("MSG_ID_046"));
            }
        }
        }

    private void setButtonLevelSecurity(Map<String, AppPermission> functionHM) {
        if (functionHM.get("updateBtn") != null && ((AppPermission) functionHM.get("updateBtn")).isFunctionFlag()) {
            updateBtn.setVisible(Boolean.TRUE);
        } else {
            updateBtn.setVisible(Boolean.FALSE);
        }

        if (functionHM.get("runBtn1") != null && ((AppPermission) functionHM.get("runBtn1")).isFunctionFlag()) {
            run.setVisible(Boolean.TRUE);
        } else {
            run.setVisible(Boolean.FALSE);
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

}
