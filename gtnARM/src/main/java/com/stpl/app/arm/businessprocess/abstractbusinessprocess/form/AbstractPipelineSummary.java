/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.abstractbusinessprocess.form;

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
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.constants.ARMConstants;
import com.stpl.ifs.util.constants.ARMMessages;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.vaadin.data.Property;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.VerticalLayout;
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
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

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
    protected PopupDateField glImpactDate;
    Date date;
    protected String[] variableHeader;
    protected String[] variableHeader_deduction;
    public CustomMenuBar.CustomMenuItem customMenuItem;
    public CustomMenuBar.CustomMenuItem deductionCustomMenuItem;
    CustomTableHeaderDTO tableHeaderDTO = new CustomTableHeaderDTO();
    protected final AbstractPipelineSummaryResults summaryResults;
    protected String[] variableVisibleColumns;
    protected String[] variableVisibleColumns_deduction;
    protected final AbstractSelectionDTO selectionDto;
    protected HelperListUtil helperId = HelperListUtil.getInstance();
    private String format = "MM/dd/yyyy";
    DateFormat dateFormat = new SimpleDateFormat(format);
    public DataSelectionLogic dataSelectionLogic = new DataSelectionLogic();
    private Date glChangeDate;
    protected Date defaultWorkFlowDate;
    protected Date resetWorkFlowDate;
    private Logger LOGGER = Logger.getLogger(AbstractPipelineSummary.class);
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
        generate();
        reset();
    }

    @Override
    public abstract void enter(ViewChangeListener.ViewChangeEvent event);
    private final CustomNotification notifier = new CustomNotification();

    protected abstract AbstractPipelineSummaryResults getResultsObject(AbstractSummaryLogic logic, AbstractSelectionDTO selectionDto);

    class CustomNotification extends AbstractNotificationUtils {

        String buttonName;

        @Override
        public void noMethod() {
        }

        @Override
        public void yesMethod() {
            LOGGER.debug("buttonName :" + buttonName);
            if (null != buttonName) {
                switch (buttonName) {
                    case "reset":
                        try {
                            defaultFocus();
                            CommonUtils.unCheckMenuBarItem(customMenuItem);
                            CommonUtils.unCheckMenuBarItem(deductionCustomMenuItem);
                            if (selectionDto.getSessionDTO().isWorkFlow()) {
                                glImpactDate.removeValueChangeListener(glWorkflowListener);
                                glImpactDate.setValue(resetWorkFlowDate);
                                selectionDto.setSummary_glDate(dateFormat.format(resetWorkFlowDate));
                                defaultWorkFlowDate = resetWorkFlowDate;
                                glImpactDate.addValueChangeListener(glWorkflowListener);
                            } else {
                                glImpactDate.removeValueChangeListener(glListener);
                                glImpactDate.setValue(glChangeDate);
                                selectionDto.setSummary_glDate(dateFormat.format(glChangeDate));
                                glImpactDate.addValueChangeListener(glListener);
                            }
                        } catch (Exception e) {
                            LOGGER.error(e);
                        }
                        break;
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
                    LOGGER.error(e);
                }
            }
        });
    }

    public void generate() {
        generate.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    selectionDto.setSummary_variables(CommonUtils.getCheckedValues(customMenuItem));
                    selectionDto.setSummary_deductionVariables(CommonUtils.getCheckedValues(deductionCustomMenuItem));
                    selectionDto.setSummary_deductionLevel((int) deductionLevelDdlb.getValue());

                    selectionDto.setSummary_deductionLevelDes(String.valueOf(deductionLevelDdlb.getItemCaption(deductionLevelDdlb.getValue())));
                    List<String[]> listSize = selectionDto.getSummary_deductionVariables();
                    String deductionValues = StringUtils.EMPTY;
                    if (!listSize.isEmpty()) {
                        for (int i = 0; i < listSize.size(); i++) {
                            String value = listSize.get(i)[0];
                            listSize.get(i)[0] = value.replace(" ", StringUtils.EMPTY).trim();
                            if (i != listSize.size() - 1) {
                                deductionValues += "'" + value + "',";
                            } else {
                                deductionValues += "'" + value + "'";
                            }
                        }
                    }
                    selectionDto.setSummary_deductionValues(deductionValues);

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
                    String[] arry = (String[]) ArrayUtils.clone(variableVisibleColumns_deduction);
                    summaryResults.generateButtonLogic(arry);

                    int configLevelid = HelperListUtil.getInstance().getIdByDesc("ARM_CONFIGURATION_TYPE", selectionDto.getDetail_Level());
                    logic.saveGLImpact(glImpactDate.getValue(), configLevelid, selectionDto.getProjectionMasterSid());
                } catch (Exception e) {
                    LOGGER.error(e);
                }
            }
        });
    }

    public void configureFields(int projectionId) {
        try {

            glImpactDate.setDateFormat("MM/dd/yyyy");

            glImpactDate.setStyleName("Alignment.MIDDLE_CENTER");
            glImpactDate.addStyleName("align-center");
            glChangeDate = logic.getGlImpactDate(selectionDto);
            glImpactDate.setValue(glChangeDate);
            glImpactDate.addValueChangeListener(glListener);
            deductionCustomMenuItem = CommonUtils.loadSummaryDeductionsDdlb(deductionLevelDdlb, deductionValueDdlb, projectionId);
            variablesDdlb.setScrollable(true);
            variablesDdlb.setPageLength(NumericConstants.FOUR);
            configureSummary();
            customMenuItem = variablesDdlb.addItem("-Select Variables-", null);

            loadVariablesDdlb();

            selectionDto.setSummary_glDate(dateFormat.format(glChangeDate));
            deductionLevelDdlb.addValueChangeListener(new Property.ValueChangeListener() {

                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                    deductionLevelValue(event);
                }
            });

            defaultFocus();
        } catch (Exception e) {
            LOGGER.error(e);
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
        LOGGER.debug("Inside deductionLevelDdlb ValueChange");
        try {

            String deductionType = String.valueOf(deductionLevelDdlb.getItemCaption(event.getProperty().getValue()));
            if (deductionType.equals(ARMConstants.getDeduction())) {
                variablesDdlb.setPageLength(variableHeader_deduction.length);
                CommonUtils.loadCustomMenu(customMenuItem, variableHeader_deduction, variableVisibleColumns_deduction);
            } else {
                variablesDdlb.setPageLength(variableHeader.length);
                CommonUtils.loadCustomMenu(customMenuItem, variableHeader, variableVisibleColumns);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    @Override
    public boolean saveAssets() {

        int configLevelid = HelperListUtil.getInstance().getIdByDesc("ARM_CONFIGURATION_TYPE", selectionDto.getDetail_Level());
        return logic.saveGLImpact(glImpactDate.getValue(), configLevelid, selectionDto.getProjectionMasterSid());
    }

    public ExtTreeContainer<AdjustmentDTO> getResultBeanContainer() {
        return summaryResults.getResultBeanContainer();
    }

    private void configureWorkFlow() {
        if (selectionDto.getSessionDTO().isWorkFlow()) {
            try {
                loadDetails();
                selectionDto.setSummary_deductionVariables(CommonUtils.getCheckedValues(deductionCustomMenuItem));
                selectionDto.setSummary_variables(CommonUtils.getCheckedValues(customMenuItem));
                String dedLevel = helperId.getDescriptionByID(selectionDto.getSummary_deductionLevel());
                selectionDto.setSummary_deductionLevelDes(dedLevel);
                List<String[]> listSize = selectionDto.getSummary_deductionVariables();
                String deductionValues = StringUtils.EMPTY;
                if (!listSize.isEmpty()) {
                    for (int i = 0; i < listSize.size(); i++) {
                        String value = listSize.get(i)[0];
                        listSize.get(i)[0] = value.replace(" ", StringUtils.EMPTY).trim();
                        if (i != listSize.size() - 1) {
                            deductionValues += "'" + value + "',";
                        } else {
                            deductionValues += "'" + value + "'";
                        }
                    }
                }
                selectionDto.setSummary_deductionValues(deductionValues);
                String[] arry = (String[]) ArrayUtils.clone(variableVisibleColumns_deduction);
                selectionDto.setSummary_deductionLevel((int) deductionLevelDdlb.getValue());
                glImpactDate.setValue(dateFormat.parse(selectionDto.getSummary_glDate()));
                summaryResults.generateButtonLogic(arry);
                if (ARMUtils.VIEW_SMALL.equals(selectionDto.getSessionDTO().getAction())) {

                    configureFieldsOnViewMode();
                }
            } catch (ParseException ex) {
                LOGGER.error(ex);
            }
        }
    }

    private void configureFieldsOnViewMode() {
        glImpactDate.setEnabled(false);
        reset.setEnabled(false);
    }

    public void loadDetails() {
        deductionLevelDdlb.setValue(selectionDto.getSummary_deductionLevel());
        List<Object[]> list = CommonLogic.loadPipelineAccrual(selectionDto.getProjectionMasterSid());
        for (int i = 0; i < list.size(); i++) {
            Object[] obj = (Object[]) list.get(i);
            if ("summary_deductionValues".equals(String.valueOf(obj[0]))) {
                String str1 = (String) obj[1];
                String[] str2 = str1.split(",");
                String str3 = null;
                for (String strings : str2) {
                    str3 = strings;
                    CommonUtils.checkMenuBarItem(deductionCustomMenuItem, str3);
                }
            } else if ("summary_variables".equals(String.valueOf(obj[0]))) {
                String str1 = (String) obj[1];
                String[] str2 = str1.split(",");
                String str3 = null;
                for (String strings : str2) {
                    str3 = strings;
                    CommonUtils.checkMenuBarItem(customMenuItem, str3);
                }

            } else if (!"detail_variables".equals(String.valueOf(obj[0])) && !"detail_reserveAcount".equals(String.valueOf(obj[0]))
                    && !"sales_variables".equals(String.valueOf(obj[0]))
                    && !"rate_DeductionValue".equals(String.valueOf(obj[0])) && !VariableConstants.DETAIL_AMOUNT_FILTER.equals(String.valueOf(obj[0]))) {
                try {
                    BeanUtils.setProperty(selectionDto, String.valueOf(obj[0]), obj[1]);
                } catch (Exception ex) {
                    LOGGER.error(ex);

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
    public Property.ValueChangeListener glListener = new Property.ValueChangeListener() {
        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            try {
                Date glDate = (Date) event.getProperty().getValue();
                Calendar glDateCal = Calendar.getInstance();
                glDateCal.setTime(glDate);
                dataSelectionLogic.dateCheckforGLCompAndBu(selectionDto.getDataSelectionDTO(), false);
                List closedList = selectionDto.getDataSelectionDTO().getNewClosedSummary_glList();
                String selectGlDate = glDateCal.get(Calendar.MONTH) + 1 + "-" + glDateCal.get(Calendar.YEAR);
                String dateString = dateFormat.format(glDate);
                if (closedList != null && !closedList.isEmpty() && closedList.contains(selectGlDate) && !(dateString.equals(selectionDto.getSummary_glDate()))) {
                    AbstractNotificationUtils.getErrorNotification("Error", ARMMessages.getGLImpactMessageId001());
                    glImpactDate.setValue(dateFormat.parse(selectionDto.getSummary_glDate()));
                } else {
                    selectionDto.setSummary_glDate(dateFormat.format(glDate));
                }
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }
    };
    public Property.ValueChangeListener glWorkflowListener = new Property.ValueChangeListener() {
        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            try {
                String defaultWorkFlowDateString = "";
                Date glDate = (Date) event.getProperty().getValue();
                Calendar glDateCal = Calendar.getInstance();
                glDateCal.setTime(glDate);
                dataSelectionLogic.dateCheckforGLCompAndBu(selectionDto.getDataSelectionDTO(), false);
                List closedList = selectionDto.getDataSelectionDTO().getNewClosedSummary_glList();
                String selectGlDate = glDateCal.get(Calendar.MONTH) + 1 + "-" + glDateCal.get(Calendar.YEAR);
                String dateString = dateFormat.format(glDate);
                if (defaultWorkFlowDate != null) {
                    defaultWorkFlowDateString = dateFormat.format(defaultWorkFlowDate);
                }
                if (closedList != null && !closedList.isEmpty() && closedList.contains(selectGlDate)) {
                    if (defaultWorkFlowDateString.equals(dateString)) {
                        glImpactDate.setValue(defaultWorkFlowDate);
                        selectionDto.setSummary_glDate(dateFormat.format(defaultWorkFlowDate));
                    } else {
                        AbstractNotificationUtils.getErrorNotification("Error", ARMMessages.getGLImpactMessageId001());
                        glImpactDate.setValue(dateFormat.parse(selectionDto.getSummary_glDate()));
                    }
                } else {
                    defaultWorkFlowDate = null;
                    selectionDto.setSummary_glDate(dateFormat.format(glDate));
                }
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }
    };

}
