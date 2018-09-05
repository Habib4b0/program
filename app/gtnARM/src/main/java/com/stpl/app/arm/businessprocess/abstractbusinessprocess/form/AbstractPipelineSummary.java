/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.abstractbusinessprocess.form;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtTreeContainer;
import org.asi.ui.custommenubar.CustomMenuBar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic.AbstractSummaryLogic;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.dataselection.logic.DataSelectionLogic;
import com.stpl.app.arm.supercode.LeaveCheckAble;
import com.stpl.app.arm.supercode.LeaveConfirmMessageAble;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.HelperListUtil;
import com.stpl.app.utils.CommonUtils;
import com.stpl.app.utils.VariableConstants;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.ARMConstants;
import com.stpl.ifs.util.constants.ARMMessages;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.vaadin.v7.data.Property;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.PopupDateField;
import com.vaadin.v7.ui.VerticalLayout;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Karthikeyan.Subraman
 */
public abstract class AbstractPipelineSummary extends VerticalLayout implements Summary, LeaveCheckAble, LeaveConfirmMessageAble {

    @UiField("reset")
    protected Button reset;
    @UiField("generate")
    protected Button generate;

    protected final AbstractSummaryLogic logic;
    @UiField("deductionLevelDdlb")
    public ComboBox deductionLevelDdlb;
    @UiField("deductionValueDdlb")
    private CustomMenuBar deductionValueDdlb;
    @UiField("variablesDdlb")
    private CustomMenuBar variablesDdlb;
    @UiField("glImpactDate")
    public PopupDateField glImpactDate;
    protected String[] variableHeader;
    protected String[] variableHeaderDeduction;
    protected CustomMenuBar.CustomMenuItem customMenuItem;
    protected CustomMenuBar.CustomMenuItem deductionCustomMenuItem;
    protected final AbstractPipelineSummaryResults summaryResults;
    protected String[] variableVisibleColumns;
    protected String[] variableVisibleColumnsDeduction;
    protected final AbstractSelectionDTO selectionDto;
    protected HelperListUtil helperId = HelperListUtil.getInstance();
    private String format = "MM/dd/yyyy";
    protected DateFormat dateFormat = new SimpleDateFormat(format);
    protected DataSelectionLogic dataSelectionLogic = new DataSelectionLogic();
    private Date glChangeDate;
    protected Date defaultWorkFlowDate;
    protected Date resetWorkFlowDate;
    protected Logger logger = LoggerFactory.getLogger(AbstractPipelineSummary.class);

    public AbstractPipelineSummary(AbstractSummaryLogic logic, AbstractSelectionDTO selectionDto) {
        this.logic = logic;
        this.selectionDto = selectionDto;
        this.summaryResults = getResultsObject(this.logic, this.selectionDto);
    }

    protected void init() {
        selectionDto.setProjectionMasterSid(selectionDto.getDataSelectionDTO().getProjectionId());
        selectionDto.setCompanyMasterSid(selectionDto.getDataSelectionDTO().getCompanyMasterSid());
        addComponent(Clara.create(getClass().getResourceAsStream("/bussinessprocess/adjustmentSummary.xml"), this));
        summaryResults.getResults();
        addComponent(summaryResults);
        configureFields(selectionDto.getProjectionMasterSid());
        configureWorkFlow();
        reset();
    }

    @Override
    public abstract void enter(ViewChangeListener.ViewChangeEvent event);
    private final CustomNotification notifier = new CustomNotification();

    protected abstract AbstractPipelineSummaryResults getResultsObject(AbstractSummaryLogic logic, AbstractSelectionDTO selectionDto);

    class CustomNotification extends AbstractNotificationUtils {

        private String buttonName;

        public CustomNotification() {
            /*
        THE DEFAULT CONSTRUCTOR
             */
        }

        @Override
        public void noMethod() {
            LOGGER.debug("Inside the CustomNotification Listener NO Method");
        }

        @Override
        public void yesMethod() {
            LOGGER.debug("buttonName :{}", buttonName);
            if (null != buttonName && "reset".equals(buttonName)) {
                try {
                    defaultFocus();
                    CommonUtils.unCheckMenuBarItem(customMenuItem);
                    CommonUtils.unCheckMenuBarItem(deductionCustomMenuItem);
                    if (selectionDto.getSessionDTO().isWorkFlow()) {
                        glImpactDate.removeValueChangeListener(glWorkflowListener);
                        glImpactDate.setValue(resetWorkFlowDate);
                        selectionDto.setSummaryglDate(dateFormat.format(resetWorkFlowDate));
                        defaultWorkFlowDate = resetWorkFlowDate;
                        glImpactDate.addValueChangeListener(glWorkflowListener);
                    } else {
                        glImpactDate.removeValueChangeListener(glListener);
                        glImpactDate.setValue(glChangeDate);
                        selectionDto.setSummaryglDate(dateFormat.format(glChangeDate));
                        glImpactDate.addValueChangeListener(glListener);
                    }
                } catch (Exception e) {
                    LOGGER.error("Error in CustomNotification", e);
                }
            }
        }

        public void setButtonName(String buttonName) {
            this.buttonName = buttonName;
        }

    }

    public void reset() {
        reset.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    notifier.setButtonName("reset");
                    notifier.getOkCancelMessage(ARMMessages.getResetMessageName_001(), ARMMessages.getResetMessageID002());
                } catch (Exception e) {
                    logger.error("Error in reset", e);
                }
            }
        });
    }

    @UiHandler("generate")
    public void generateButtonClick(Button.ClickEvent event) {
        try {
            selectionDto.setSummaryvariables(CommonUtils.getCheckedValues(customMenuItem));
            selectionDto.setSummarydeductionVariables(CommonUtils.getCheckedValues(deductionCustomMenuItem));
            selectionDto.setSummarydeductionLevel((int) deductionLevelDdlb.getValue());

            selectionDto.setSummarydeductionLevelDes(String.valueOf(deductionLevelDdlb.getItemCaption(deductionLevelDdlb.getValue())));
            List<String[]> listSize = selectionDto.getSummarydeductionVariables();
            StringBuilder deductionValues = new StringBuilder();
            if (!listSize.isEmpty()) {
                for (int i = 0; i < listSize.size(); i++) {
                    String value = listSize.get(i)[0];
                    listSize.get(i)[0] = value.replace(" ", StringUtils.EMPTY).trim();
                    if (i != listSize.size() - 1) {
                        deductionValues.append(ARMUtils.SINGLE_QUOTES).append(value).append("',");
                    } else {
                        deductionValues.append(ARMUtils.SINGLE_QUOTES).append(value).append(ARMUtils.SINGLE_QUOTES);
                    }
                }
            }
            selectionDto.setSummarydeductionValues(deductionValues.toString());

            if (!logic.generateButtonCheck(selectionDto)) {

                if (ARMConstants.getPipelineInventoryTrueUp().equals(selectionDto.getDataSelectionDTO().getAdjustmentType())) {
                    AbstractNotificationUtils.getErrorNotification(ARMMessages.getGenerateMessageName_001(), ARMMessages.getGenerateMessage_Demand());
                } else if (ARMConstants.getTransaction6().equals(selectionDto.getDataSelectionDTO().getAdjustmentType())) {
                    AbstractNotificationUtils.getErrorNotification(ARMMessages.getGenerateMessageName_001(), ARMMessages.getGenerateMessage_Demand());
                } else {
                    AbstractNotificationUtils.getErrorNotification(ARMMessages.getGenerateMessageName_001(), ARMMessages.getGenerateMessage_MsgId_003());
                }
                return;
            }
            String[] arry = (String[]) ArrayUtils.clone(variableVisibleColumnsDeduction);
            summaryResults.generateButtonLogic(arry);

            int configLevelid = HelperListUtil.getInstance().getIdByDesc("ARM_CONFIGURATION_TYPE", selectionDto.getDetailLevel());
            logic.saveGLImpact(glImpactDate.getValue(), configLevelid, selectionDto.getProjectionMasterSid());
        } catch (Exception e) {
            logger.error("Error in generate", e);
        }
    }

    public void configureFields(int projectionId) {
        try {

            glImpactDate.setDateFormat("MM/dd/yyyy");

            glImpactDate.setStyleName("Alignment.MIDDLE_CENTER");
            glImpactDate.addStyleName("align-center");
            glChangeDate = logic.getGlImpactDate(selectionDto);
            glImpactDate.setValue(glChangeDate);
            glImpactDate.addValueChangeListener(glListener);
            loadDeductionValue(projectionId);
            variablesDdlb.setScrollable(true);
            variablesDdlb.setPageLength(NumericConstants.FOUR);
            configureSummary();
            customMenuItem = variablesDdlb.addItem("-Select Variables-", null);

            loadVariablesDdlb();

            selectionDto.setSummaryglDate(dateFormat.format(glChangeDate));
            deductionLevelDdlb.addValueChangeListener(new Property.ValueChangeListener() {

                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                    deductionLevelValue(event);
                }
            });

            defaultFocus();
        } catch (Exception e) {
            logger.error("Error in configurefield", e);
        }
        deductionValueDdlb.addStyleName("deductioncombobox");
    }

    protected abstract void configureSummary();

    public void configureDropDowns(ComboBox box) {
        box.setImmediate(true);
        box.setNullSelectionAllowed(true);
        box.addItem(GlobalConstants.getSelectOne());
        box.setNullSelectionItemId(GlobalConstants.getSelectOne());
        box.select(null);
    }

    private void deductionLevelValue(Property.ValueChangeEvent event) {
        logger.debug("Inside deductionLevelDdlb ValueChange");
        try {

            String deductionType = String.valueOf(deductionLevelDdlb.getItemCaption(event.getProperty().getValue()));
            if (deductionType.equals(ARMConstants.getDeduction())) {
                variablesDdlb.setPageLength(variableHeaderDeduction.length);
                CommonUtils.loadCustomMenu(customMenuItem, variableHeaderDeduction, variableVisibleColumnsDeduction);
            } else {
                variablesDdlb.setPageLength(variableHeader.length);
                CommonUtils.loadCustomMenu(customMenuItem, variableHeader, variableVisibleColumns);
            }
        } catch (Exception e) {
            logger.error("Error in deductionvalue :", e);
        }
    }

    @Override
    public boolean saveAssets() {

        int configLevelid = HelperListUtil.getInstance().getIdByDesc("ARM_CONFIGURATION_TYPE", selectionDto.getDetailLevel());
        return logic.saveGLImpact(glImpactDate.getValue(), configLevelid, selectionDto.getProjectionMasterSid());
    }

    @Override
    public ExtTreeContainer<AdjustmentDTO> getResultBeanContainer() {
        return summaryResults.getResultBeanContainerVal();
    }

    public void configureWorkFlow() {
        if (selectionDto.getSessionDTO().isWorkFlow()) {
            try {
                loadDetails();
                selectionDto.setSummarydeductionVariables(CommonUtils.getCheckedValues(deductionCustomMenuItem));
                selectionDto.setSummaryvariables(CommonUtils.getCheckedValues(customMenuItem));
                String dedLevel = helperId.getDescriptionByID(selectionDto.getSummarydeductionLevel());
                selectionDto.setSummarydeductionLevelDes(dedLevel);
                List<String[]> listSize = selectionDto.getSummarydeductionVariables();
                StringBuilder deductionValues = new StringBuilder();
                if (!listSize.isEmpty()) {
                    for (int i = 0; i < listSize.size(); i++) {
                        String value = listSize.get(i)[0];
                        listSize.get(i)[0] = value.replace(" ", StringUtils.EMPTY).trim();
                        if (i != listSize.size() - 1) {
                            deductionValues.append(ARMUtils.SINGLE_QUOTES).append(value).append("',");
                        } else {
                            deductionValues.append(ARMUtils.SINGLE_QUOTES).append(value).append(ARMUtils.SINGLE_QUOTES);
                        }
                    }
                }
                selectionDto.setSummarydeductionValues(deductionValues.toString());
                String[] arry = (String[]) ArrayUtils.clone(variableVisibleColumnsDeduction);
                selectionDto.setSummarydeductionLevel((int) deductionLevelDdlb.getValue());
                glImpactDate.setValue(dateFormat.parse(selectionDto.getSummaryglDate()));
                summaryResults.generateButtonLogic(arry);
                if (ARMUtils.VIEW_SMALL.equals(selectionDto.getSessionDTO().getAction())) {
                    configureFieldsOnViewMode();
                }
            } catch (ParseException ex) {
                logger.error("Error in configureworkflow" , ex);
            }
        }
    }

    public void configureFieldsOnViewMode() {
        glImpactDate.setEnabled(false);
        reset.setEnabled(false);
    }

    public void loadDetails() {
        deductionLevelDdlb.setValue(selectionDto.getSummarydeductionLevel());
        List<Object[]> list = CommonLogic.loadPipelineAccrual(selectionDto.getProjectionMasterSid());
        for (int i = 0; i < list.size(); i++) {
            Object[] obj = list.get(i);
            if (VariableConstants.SUMMARY_DEDUCTION_VALUE.equals(String.valueOf(obj[0]))) {
                String str1 = (String) obj[1];
                String[] str2 = str1.split(",");
                String str3 = null;
                for (String strings : str2) {
                    str3 = strings;
                    CommonUtils.checkMenuBarItem(deductionCustomMenuItem, str3);
                }
            } else if (VariableConstants.SUMMARY_VARIABLES.equals(String.valueOf(obj[0]))) {
                String str1 = (String) obj[1];
                String[] str2 = str1.split(",");
                String str3 = null;
                for (String strings : str2) {
                    str3 = strings;
                    CommonUtils.checkMenuBarItem(customMenuItem, str3);
                }

            } else if (!CommonLogic.getInstance().getVariablesList().contains(obj[0])) {
                try {
                    BeanUtils.setProperty(selectionDto, String.valueOf(obj[0]), obj[1]);
                } catch (Exception ex) {
                    logger.error("Error in loadDetails :" , ex);

                }

            }
        }

    }

    @Override
    public Focusable getDefaultFocusComponent() {
        return deductionLevelDdlb;
    }

    public void defaultFocus() {
        deductionLevelDdlb.select(HelperListUtil.getInstance().getIdByDesc("DEDUCTION_LEVELS", "Deduction Program"));
    }

    @Override
    public boolean isGenerated() {
        return summaryResults.isGenerated();
    }

    protected void loadVariablesDdlb() {
        CommonUtils.loadCustomMenu(customMenuItem, variableHeader, variableVisibleColumns);
    }

    @Override
    public boolean checkLeave() {
        return summaryResults.checkLeave();
    }

    @Override
    public String leaveConfirmationHeader() {
        return ARMMessages.getSalesLeaveConfirmHeaderTransaction1();
    }

    @Override
    public String leaveConfirmationMessage() {
        return ARMMessages.getSalesLeaveConfirmMessageTransaction1();
    }

    @Override
    public boolean isRestrict() {
        return summaryResults.isRestrict();
    }

    @Override
    public String leaveRestrictionHeader() {
        return ARMMessages.getSalesLeaveConfirmHeaderTransaction1();
    }

    @Override
    public String leaveRestrictionMessage() {
        return ARMMessages.getSalesLeaveConfirmMessageTransaction1();
    }
    protected Property.ValueChangeListener glListener = new Property.ValueChangeListener() {
        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            try {
                Date glDate = (Date) event.getProperty().getValue();
                Calendar glDateCal = Calendar.getInstance();
                glDateCal.setTime(glDate);
                dataSelectionLogic.dateCheckforGLCompAndBu(selectionDto.getDataSelectionDTO(), false);
                List closedList = selectionDto.getDataSelectionDTO().getNewClosedSummaryglList();
                String selectGlDate = glDateCal.get(Calendar.MONTH) + 1 + "-" + glDateCal.get(Calendar.YEAR);
                String dateString = dateFormat.format(glDate);
                if (closedList != null && !closedList.isEmpty() && closedList.contains(selectGlDate) && !(dateString.equals(selectionDto.getSummaryglDate()))) {
                    AbstractNotificationUtils.getErrorNotification("Error", ARMMessages.getGLImpactMessageId001());
                    glImpactDate.setValue(dateFormat.parse(selectionDto.getSummaryglDate()));
                } else {
                    selectionDto.setSummaryglDate(dateFormat.format(glDate));
                }
            } catch (Exception ex) {
                logger.error("Error in glListener :" , ex);
            }
        }
    };
    protected Property.ValueChangeListener glWorkflowListener = new Property.ValueChangeListener() {
        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            try {
                String defaultWorkFlowDateString = "";
                Date glDate = (Date) event.getProperty().getValue();
                Calendar glDateCal = Calendar.getInstance();
                glDateCal.setTime(glDate);
                dataSelectionLogic.dateCheckforGLCompAndBu(selectionDto.getDataSelectionDTO(), false);
                List closedList = selectionDto.getDataSelectionDTO().getNewClosedSummaryglList();
                String selectGlDate = glDateCal.get(Calendar.MONTH) + 1 + "-" + glDateCal.get(Calendar.YEAR);
                String dateString = dateFormat.format(glDate);
                if (defaultWorkFlowDate != null) {
                    defaultWorkFlowDateString = dateFormat.format(defaultWorkFlowDate);
                }
                if (closedList != null && !closedList.isEmpty() && closedList.contains(selectGlDate)) {
                    if (defaultWorkFlowDateString.equals(dateString)) {
                        glImpactDate.setValue(defaultWorkFlowDate);
                        selectionDto.setSummaryglDate(dateFormat.format(defaultWorkFlowDate));
                    } else {
                        AbstractNotificationUtils.getErrorNotification("Error", ARMMessages.getGLImpactMessageId001());
                        glImpactDate.setValue(dateFormat.parse(selectionDto.getSummaryglDate()));
                    }
                } else {
                    defaultWorkFlowDate = null;
                    selectionDto.setSummaryglDate(dateFormat.format(glDate));
                }
            } catch (Exception ex) {
                logger.error("Error in glWorkflowListener :" , ex);
            }
        }
    };

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    private void loadDeductionValue(int projectionId) {
        deductionCustomMenuItem = CommonUtils.loadSummaryDeductionsDdlbForTrx6(deductionLevelDdlb, deductionValueDdlb, projectionId);
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}
