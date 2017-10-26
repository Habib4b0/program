/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.ui.form;

import com.stpl.app.accountclose.common.CommonLogic;
import com.stpl.app.accountclose.dto.ProcessDTO;
import com.stpl.app.accountclose.logic.ManualLogic;
import com.stpl.app.accountclose.logic.ManualTableLogic;
import com.stpl.app.accountclose.logic.ProcessSchedulerLogic;
import com.stpl.app.accountclose.logic.ProcessSchedulerTableLogic;
import com.stpl.app.accountclose.schedule.ScheduleTrigger;
import com.stpl.app.accountclose.utils.AbstractNotificationUtils;
import com.stpl.app.accountclose.utils.Constants;
import com.stpl.app.accountclose.utils.Message;
import com.stpl.app.accountclose.utils.MessageUtil;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import java.util.Date;
import org.apache.commons.lang.StringUtils;
import static org.asi.ui.extfilteringtable.ExtFilteringTableConstant.VALO_THEME_EXTFILTERING_TABLE;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author alok.v
 */
     
public class ProcessSchedulerIndex extends CustomComponent {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(ProcessSchedulerIndex.class);

    /**
     * Frequency Radio button
     */
    @UiField("frequencyRadio")
    private OptionGroup frequencyRadio;
    /**
     * status
     */
    @UiField("status")
    private ComboBox status;
    /**
     * hours combo box
     */
    @UiField("hoursOne")
    private ComboBox hoursOne;
    /**
     * hours combo box
     */
    @UiField("hoursTwo")
    private ComboBox hoursTwo;
    /**
     * hours combo box
     */
    @UiField("hoursThree")
    private ComboBox hoursThree;
    /**
     * minutes combo box
     */
    @UiField("minutesOne")
    private ComboBox minutesOne;
    /**
     * minutes combo box
     */
    @UiField("minutesTwo")
    private ComboBox minutesTwo;
    /**
     * minutes combo box
     */
    @UiField("minutesThree")
    private ComboBox minutesThree;
    /**
     * calendar ddlb
     */
    @UiField("calendar")
    private ComboBox calendar;

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
    /**
     * Vertical Layout
     */
    @UiField("scheduledProcessesTable")
    private VerticalLayout scheduledProcessesTable;

    @UiField("manualProcessesTable")
    private VerticalLayout manualProcessesTable;

    @UiField("runBtn1")
    private Button runBtn1;

    /**
     * Process Scheduler Table Logic
     */
    ProcessSchedulerTableLogic tableLogic = new ProcessSchedulerTableLogic();

    /**
     * ExtPagedTable
     */
    private ExtPagedTable resultTable = new ExtPagedTable(tableLogic);

    /*
     * Bean Item conatiner for Ext Paged Table
     */
    public ManualTableLogic manuallogic = new ManualTableLogic();

    public ExtPagedTable manualProcTable = new ExtPagedTable(manuallogic);

    private BeanItemContainer<ProcessDTO> manualProcSchContainer = new BeanItemContainer<ProcessDTO>(ProcessDTO.class);

    private BeanItemContainer<ProcessDTO> resultBean = new BeanItemContainer<ProcessDTO>(ProcessDTO.class);

    boolean isload = false;

    ProcessDTO ProcessDTO;

    private String a = "--Select One--,0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,";
    private String b = "--Select One--,0,15,30,45";
    private String c = "--Select One--,Active,Inactive";
    private String d = "Holiday schedule,Holiday";

    public ProcessSchedulerIndex(String screenIndicator, ProcessDTO processDTO, CustomFieldGroup custom) throws Exception {
        super();
        init();
    }

    /**
     * Init
     */
    private void init() {
        try {
            LOGGER.info("Inside Init");
            addStyleName("bootstrap");
            addStyleName("bootstrap-admin");
            setCompositionRoot(Clara.create(getClass().getResourceAsStream("/process.xml"), this));
            onLoad(isload);
            configureFields();
            configureResultTable();
            LOGGER.info("Ending Init");
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * OnLoad
     *
     * @param isload
     */
    private void onLoad(boolean isload) {
        runOne.setCaption("Run Every:");
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

    /**
     * Configure Fields
     *
     * @throws Exception
     */
    private void configureFields() throws Exception {

        LOGGER.info("Entering configureFields");

        frequencyRadio.addItems("Interval", "Time");
        frequencyRadio.select("Interval");

        ScheduleTrigger trigger = new ScheduleTrigger();
        trigger.scheduleTrigger();

        frequencyRadio.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if ("Interval".equals(event.getProperty().getValue().toString())) {
                    reset();
                    isload = false;
                    onLoad(isload);
                    hoursTwo.removeAllItems();
                    hoursTwo.addItems(b.split(","));
                } else {
                    reset();
                    isload = true;
                    onLoad(isload);
                    runOne.setCaption("Run 1:");
                    hoursLB2.setCaption("hours");
                    hoursTwo.removeAllItems();
                    hoursTwo.addItems(a.split(","));
                }
            }
        });

        startDate.setDateFormat("MM/dd/yyyy");
        endDate.setDateFormat("MM/dd/yyyy");

        status.setImmediate(true);
        status.setNullSelectionAllowed(true);
        status.setNullSelectionItemId("-Select One-");
        status.addItems(c.split(","));

        hoursOne.setImmediate(true);
        hoursOne.setNullSelectionAllowed(true);
        hoursOne.setNullSelectionItemId("-Select One-");
        hoursOne.addItems(a.split(","));

        hoursTwo.setImmediate(true);
        hoursTwo.setNullSelectionAllowed(true);
        hoursTwo.setNullSelectionItemId("-Select One-");
        hoursTwo.addItems(a.split(","));

        hoursThree.setImmediate(true);
        hoursThree.setNullSelectionAllowed(true);
        hoursThree.setNullSelectionItemId("-Select One-");
        hoursThree.addItems(a.split(","));

        minutesOne.setImmediate(true);
        minutesOne.setNullSelectionAllowed(true);
        minutesOne.setNullSelectionItemId("-Select One-");
        minutesOne.addItems(b.split(","));

        minutesTwo.setImmediate(true);
        minutesTwo.setNullSelectionAllowed(true);
        minutesTwo.setNullSelectionItemId("-Select One-");
        minutesTwo.addItems(b.split(","));

        minutesThree.setImmediate(true);
        minutesThree.setNullSelectionAllowed(true);
        minutesThree.setNullSelectionItemId("-Select One-");
        minutesThree.addItems(b.split(","));

        calendar.addItem("-Select One-");
        calendar.setImmediate(true);
        calendar.setNullSelectionAllowed(true);
        calendar.setNullSelectionItemId("-Select One-");
        calendar.addItems(d.split(","));

        LOGGER.info("Ending configureFields");

    }

    /**
     * Reset
     */
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

    /**
     * result table configure
     *
     * @throws Exception
     */
    private void configureResultTable() throws Exception {

        LOGGER.info("Entering configureResultTable");
        manuallogic.setContainerDataSource(manualProcSchContainer);
        manuallogic.setPageLength(10);
        manuallogic.sinkItemPerPageWithPageLength(false);
        scheduledProcessesTable.addComponent(resultTable);
        manualProcessesTable.addComponent(manualProcTable);
        tableLogic.setContainerDataSource(resultBean);
        tableLogic.setPageLength(10);
        HorizontalLayout controls = tableLogic.createControls();
        HorizontalLayout controlLayout = CommonLogic.getResponsiveControls(controls);
        scheduledProcessesTable.addComponent(controlLayout);
        resultTable.setVisibleColumns(Constants.PROCESS_SCHEDULAR_COLUMNS);
        resultTable.setColumnHeaders(Constants.PROCESS_SCHEDULAR_HEADER);

        resultTable.markAsDirtyRecursive();
        resultTable.setImmediate(true);
        resultTable.setWidth("924px");
        resultTable.setSelectable(true);
        resultTable.markAsDirty();
        resultTable.setComponentError(null);
        resultTable.setValidationVisible(false);
        resultTable.setFilterBarVisible(false);

        manualProcTable.addStyleName(VALO_THEME_EXTFILTERING_TABLE);
        manualProcTable.setWidth("470px");
        manualProcTable.setHeight("630px");
        manualProcTable.setPageLength(10);
        manualProcTable.setSelectable(true);
        manualProcTable.setMultiSelect(false);
        HorizontalLayout controls1 = manuallogic.createControls();
        HorizontalLayout controlLayout1 = CommonLogic.getResponsiveControls(controls1);
        manualProcessesTable.addComponent(controlLayout1);
        manualProcTable.setVisibleColumns(Constants.MANUAL_PROCESS_SCHEDULAR_COLUMNS);
        manualProcTable.setColumnHeaders(Constants.MANUAL_PROCESS_SCHEDULAR_HEADER);
        manualProcTable.setColumnCheckBox(Constants.CHECK_RECORD, Boolean.TRUE);

        for (Object header : resultTable.getVisibleColumns()) {
            resultTable.setColumnWidth(header, 150);
            resultTable.setColumnAlignment(header, ExtCustomTable.Align.CENTER);
        }
        tableLogic.setPageLength(10);

        resultTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {

            @Override
            public void itemClick(ItemClickEvent event) {
                if (event.isDoubleClick()) {
                    try {
                        LOGGER.info("Inside Double click event");
                        int sid = ((ProcessDTO) event.getItemId()).getProcessSid();
                        final ProcessSchedulerLogic logic = new ProcessSchedulerLogic();
                        ProcessDTO dto = logic.getProcessScheduleByID(sid);
                        ProcessDTO = setFieldValue(dto, sid);
                        LOGGER.info("Ending Double click event");
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }
            }
        });

        LOGGER.info("Ending configureResultTable");
    }

    /**
     * @param dto
     */
    private ProcessDTO setFieldValue(ProcessDTO dto, int sid) {

        frequencyRadio.select(dto.getFrequencyRadio());
        processName.setValue(dto.getProcessName());
        status.select(dto.getStatus());
        if ("Interval".equals(dto.getFrequencyRadio())) {
            hoursOne.select("24".equals(dto.getRunHours()) ? "-Select One-" : dto.getRunHours());
            hoursTwo.select("24".equals(dto.getRunMinutes()) ? "-Select One-" : dto.getRunMinutes());
        } else {
            hoursOne.select("24".equals(dto.getHoursOne()) ? "-Select One-" : dto.getHoursOne());
            minutesOne.select("24".equals(dto.getMinutesOne()) ? "-Select One-" : dto.getMinutesOne());
            hoursTwo.select("24".equals(dto.getHoursOne()) ? "-Select One-" : dto.getHoursOne());
            minutesTwo.select("24".equals(dto.getMinutesTwo()) ? "-Select One-" : dto.getMinutesTwo());
            hoursThree.select("24".equals(dto.getHoursOne()) ? "-Select One-" : dto.getHoursOne());
            minutesThree.select("24".equals(dto.getMinutesThree()) ? "-Select One-" : dto.getMinutesThree());
        }
        startDate.setValue(dto.getStartDate());
        endDate.setValue(dto.getEndDate());
        dto.setProcessSid(sid);
        return dto;
    }

    @UiHandler("updateBtn")
    public void updateBtn(Button.ClickEvent event) {
        if ((endDate.getValue()).after(startDate.getValue())) {
            try {
                if (StringUtils.EMPTY.equals(processName.getValue())) {
                    AbstractNotificationUtils.getErrorNotification(MessageUtil.getMessage(Message.NO_PROCESS_NAME_HEADER), MessageUtil.getMessage(Message.NO_PROCESS_NAME_MSG));
                } else {
                    getFieldValue(ProcessDTO);
                    ProcessSchedulerLogic.update(ProcessDTO);
                    tableLogic.setCurrentPage(1);
                    reset();
                    processName.setValue(StringUtils.EMPTY);
                    startDate.setValue(null);
                    endDate.setValue(null);
                    status.select("-Select One-");
                    ScheduleTrigger trigger = new ScheduleTrigger();
                    trigger.scheduleTrigger();
                }
            } catch (Exception ex) {
                LOGGER.error(ex);
            }

        }else{
            AbstractNotificationUtils.getErrorNotification(MessageUtil.getMessage(Message.WRONG_DATE_ENTRY_HEADER), MessageUtil.getMessage(Message.WRONG_DATE_ENTRY_MSG));
        }
    }
/**
 * 
 * @param dto 
 */
    private void getFieldValue(ProcessDTO dto) {
        dto.setProcessName(processName.getValue());
        dto.setFrequencyRadio(frequencyRadio.getValue().toString());
        dto.setStatus(String.valueOf(status.getValue()));
        if ("Interval".equals(dto.getFrequencyRadio())) {
            dto.setRunHours(String.valueOf(hoursOne.getValue()));
            dto.setRunMinutes(String.valueOf(hoursTwo.getValue()));
        } else {
            dto.setHoursOne(String.valueOf(hoursOne.getValue()));
            dto.setHoursTwo(String.valueOf(hoursTwo.getValue()));
            dto.setHoursThree(String.valueOf(hoursThree.getValue()));
            dto.setMinutesOne(String.valueOf(minutesOne.getValue()));
            dto.setMinutesTwo(String.valueOf(minutesTwo.getValue()));
            dto.setMinutesThree(String.valueOf(minutesThree.getValue()));
        }
        dto.setStartDate(startDate.getValue());
        dto.setEndDate(endDate.getValue());
    }

    @UiHandler("runBtn1")
    public void runBtn1(Button.ClickEvent event) {
        ProcessDTO dtoValue = (ProcessDTO) manualProcTable.getValue();
        manualProcTable.getContainerProperty(manualProcTable.getValue(), "modifiedDate").setValue(new Date());
        ProcessSchedulerLogic manualSchedule = new ProcessSchedulerLogic();
        manualSchedule.file();

        ManualLogic manSch = new ManualLogic();
        manSch.getLastRunUpdate(dtoValue.getProcessSid());
    }

}
