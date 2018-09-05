/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.abstractbusinessprocess.form;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic.AbstractDemandSummaryLogic;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic.AbstractSummaryLogic;
import com.stpl.app.arm.dataselection.logic.DataSelectionLogic;
import com.stpl.app.arm.utils.HelperListUtil;

import com.stpl.app.utils.CommonUtils;
import com.stpl.app.utils.VariableConstants;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.constants.ARMConstants;
import com.stpl.ifs.util.constants.ARMMessages;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.vaadin.v7.data.Property;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.OptionGroup;
import com.vaadin.v7.ui.PopupDateField;
import com.vaadin.v7.ui.VerticalLayout;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.custommenubar.CustomMenuBar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

/**
 *
 * @author Nimisha.Rakesh
 */
public abstract class AbstractDemandSummarySelection extends VerticalLayout implements Summary {

    @UiField("view")
    protected OptionGroup view;
    @UiField("reset")
    protected Button reset;
    @UiField("generate")
    protected Button generate;
    @UiField("frequencyDdlb")
    protected ComboBox frequencyDdlb;
    @UiField("fromDate")
    protected ComboBox fromDate;
    @UiField("toDate")
    protected ComboBox toDate;
    @UiField("variables")
    private CustomMenuBar variables;
    @UiField("glImpactDate")
    protected PopupDateField glImpactDate;
    protected String[] variableHeader;
    protected String[] variableHeaderDeduction;
    protected String[] variableVisibleColumns;
    protected String[] variableVisibleColumnsDeduction;
    protected CustomMenuBar.CustomMenuItem deductionCustomMenuItem;
    protected CustomMenuBar.CustomMenuItem customMenuItem;
    @UiField("deductionLevelDdlb")
    protected ComboBox deductionLevelDdlb;
    @UiField("deductionValue")
    private CustomMenuBar deductionValue;
    private List frequencyList;
    protected AbstractSelectionDTO selectionDTO;
    protected AbstractDemandSummaryResults adjustmentResults;
    protected Map<Integer, HelperDTO> descriptionMap;
    protected final AbstractDemandSummaryLogic logic;
    protected DataSelectionLogic dataSelectionLogic = new DataSelectionLogic();
    protected String format = "MM/dd/yyyy";
    protected DateFormat dateFormat = new SimpleDateFormat(format);
    private Date glDateField;
    protected Date defaultWorkFlowDate;
    protected Date resetWorkFlowDate;
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public AbstractDemandSummarySelection(AbstractSelectionDTO selectionDTO, AbstractDemandSummaryLogic logic) {
        this.selectionDTO = selectionDTO;
        this.logic = logic;
        init();
    }

    private void init() {
        selectionDTO.setProjectionMasterSid(selectionDTO.getDataSelectionDTO().getProjectionId());
        selectionDTO.setCompanyMasterSid(selectionDTO.getDataSelectionDTO().getCompanyMasterSid());
        adjustmentResults = getResults(logic, selectionDTO);
        addComponent(Clara.create(getClass().getResourceAsStream("/bussinessprocess/adjustmentSummaryDemand.xml"), this));
        view.addItem(ARMConstants.getSinglePeriod());
        view.addItem(ARMConstants.getMultiplePeriod());
        adjustmentResults.getResults();
        addComponent(adjustmentResults);
        configureFields();
        generate();
        reset();
    }

    protected abstract AbstractDemandSummaryResults getResults(AbstractSummaryLogic logic, AbstractSelectionDTO selectionDTO);
    private final CustomNotification notifier = new CustomNotification();

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
            if (null != buttonName) {
                switch (buttonName) {

                    case "reset":

                        try {
                            deductionLevelDdlb.select(HelperListUtil.getInstance().getIdByDesc(VariableConstants.DEDUCTION_LEVELS, VariableConstants.DEDUCTION_PROGRAM));
                            CommonUtils.unCheckMenuBarItem(customMenuItem);
                            frequencyDdlb.select(0);
                            view.select(ARMConstants.getSinglePeriod());
                            toDate.select(GlobalConstants.getSelectOne());
                            fromDate.select(GlobalConstants.getSelectOne());
                            if (selectionDTO.getSessionDTO().isWorkFlow()) {
                                glImpactDate.removeValueChangeListener(glWorkflowListener);
                                glImpactDate.setValue(resetWorkFlowDate);
                                selectionDTO.setSummaryglDate(dateFormat.format(resetWorkFlowDate));
                                defaultWorkFlowDate = resetWorkFlowDate;
                                glImpactDate.addValueChangeListener(glWorkflowListener);
                            } else {
                                glImpactDate.removeValueChangeListener(glListener);
                                glImpactDate.setValue(glDateField);
                                selectionDTO.setSummaryglDate(dateFormat.format(glDateField));
                                glImpactDate.addValueChangeListener(glListener);
                            }
                            customizeResetButtonLogic();
                        } catch (Exception ex) {
                            LOGGER.error("Error in reset" , ex);
                        }
                        break;
                    case "save":
                        // save logic
                        break;
                    default:
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

    public void generate() {
        generate.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                logger.debug("Inside generate ButtonClick Btn in abstractdemandsummaryselection");
                try {
                    generateButtonLogic();
                } catch (Exception e) {
                    logger.error("Error in generate", e);
                }
            }
        });
    }

    public void configureFields() {
        try {
            toDate.setNullSelectionAllowed(false);
            fromDate.setNullSelectionAllowed(false);
            deductionValue.addStyleName("menuleft");
            List<String> periodValue = CommonUtils.getToAndFromByFrequency("", selectionDTO.getDataSelectionDTO().getFromDate(), selectionDTO.getDataSelectionDTO().getToDate());
            fromDate.addItems(periodValue);
            toDate.addItems(periodValue);
            glImpactDate.setDateFormat("MM/dd/yyyy");
            glImpactDate.addStyleName("align-center");
            frequencyDdlb.focus();
            CommonUtils.loadComboBoxWithIntegerForComboBoxForDemandSummary(frequencyDdlb, "PAYMENT_FREQUENCY", false);
            variables.setScrollable(true);
            configureSummary();
            selectionDTO.setSummarydemandview(String.valueOf(view.getValue()));
            variables.setPageLength(variableHeader.length);
            customMenuItem = variables.addItem("-Select One-", null);
            CommonUtils.loadCustomMenu(customMenuItem, variableHeader, variableVisibleColumns);
            loadDeductionValue();
            deductionLevelDdlb.select(HelperListUtil.getInstance().getIdByDesc(VariableConstants.DEDUCTION_LEVELS, VariableConstants.DEDUCTION_PROGRAM));
            glDateField = logic.getGlImpactDate(selectionDTO);
            glImpactDate.setValue(glDateField);
            selectionDTO.setSummaryglDate(dateFormat.format(glDateField));
            toDate.select(GlobalConstants.getSelectOne());
            fromDate.select(GlobalConstants.getSelectOne());
            descriptionMap = HelperListUtil.getInstance().getIdHelperDTOMap();
            frequencyDdlb.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                    int ddlbVal = Integer.parseInt(String.valueOf(event.getProperty().getValue()));
                    if (ddlbVal > 0) {
                        String frequency = HelperListUtil.getInstance().getIdDescMap().get(ddlbVal);
                        if (!selectionDTO.getSessionDTO().isWorkFlow()) {
                            if ("Monthly".equalsIgnoreCase(frequency) && deductionLevelDdlb.getItemCaption(deductionLevelDdlb.getValue()).equals(ARMConstants.getDeduction())) {
                                CommonUtils.loadCustomMenu(customMenuItem, variableHeaderDeduction, variableVisibleColumnsDeduction);
                            } else {
                                CommonUtils.loadCustomMenu(customMenuItem, variableHeader, variableVisibleColumns);
                            }
                        }
                        selectionDTO.setSummarydemandfrequency(frequency);
                        List<String> periodValue = CommonUtils.getToAndFromByFrequency(frequency, selectionDTO.getDataSelectionDTO().getFromDate(), selectionDTO.getDataSelectionDTO().getToDate());
                        fromDate.removeAllItems();
                        fromDate.addItems(periodValue);
                        fromDate.select(periodValue.get(1));
                        toDate.setEnabled(true);
                        toDate.removeAllItems();
                        toDate.addItems(periodValue);
                        frequencyList = periodValue;
                        toDate.select(periodValue.get(periodValue.size() - 1));
                        if (ARMConstants.getSinglePeriod().equalsIgnoreCase(String.valueOf(view.getValue()))) {
                            toDate.select(periodValue.get(1));
                            toDate.setEnabled(false);
                            selectionDTO.setSummaryfrequencyList(listTolistOfStringArray(new ArrayList(Arrays.asList(String.valueOf(fromDate.getValue())))));
                        } else {
                            int fromRange = frequencyList.indexOf(String.valueOf(fromDate.getValue()));
                            int toRange = frequencyList.indexOf(String.valueOf(toDate.getValue()));
                            List frequencylist = frequencyList.subList(fromRange, toRange + 1);
                            selectionDTO.setSummaryfrequencyList(listTolistOfStringArray(frequencylist));
                        }
                    } else {
                        selectionDTO.setSummarydemandfrequency(StringUtils.EMPTY);
                        fromDate.select(GlobalConstants.getSelectOne());
                        toDate.select(GlobalConstants.getSelectOne());
                    }
                }
            });
            deductionLevelDdlb.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                    String deductionType = String.valueOf(deductionLevelDdlb.getItemCaption(event.getProperty().getValue()));
                    String frequency = StringUtils.EMPTY;
                    if (!selectionDTO.getSessionDTO().isWorkFlow()) {
                        frequency = selectionDTO.getSummarydemandfrequency();
                    } else if (frequency.matches("^[-+]?\\d+(\\.\\d+)?$")) {
                        frequency = HelperListUtil.getInstance().getIdDescMap().get(selectionDTO.getSummarydemandfrequency());
                    } else {
                        frequency = selectionDTO.getSummarydemandfrequency();
                    }
                    if (selectionDTO.getSessionDTO().isWorkFlow()) {
                        frequency = HelperListUtil.getInstance().getIdDescMap().get(Integer.valueOf(selectionDTO.getSummarydemandfrequency()));
                    }
                    if (deductionType.equals(ARMConstants.getDeduction()) && "Monthly".equalsIgnoreCase(frequency)) {
                        CommonUtils.loadCustomMenu(customMenuItem, variableHeaderDeduction, variableVisibleColumnsDeduction);
                    } else {
                        variables.setPageLength(variableHeader.length);
                        CommonUtils.loadCustomMenu(customMenuItem, variableHeader, variableVisibleColumns);
                    }
                }
            });
            view.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                    try {
                        String viewType = String.valueOf(event.getProperty().getValue());
                        selectionDTO.setSummarydemandview(viewType);
                        if (ARMConstants.getSinglePeriod().equalsIgnoreCase(viewType)) {
                            toDate.select(String.valueOf(fromDate.getValue()));
                            toDate.setEnabled(false);
                            selectionDTO.setSummaryfrequencyList(listTolistOfStringArray(new ArrayList(Arrays.asList(String.valueOf(fromDate.getValue())))));
                        } else {
                            toDate.setEnabled(true);
                            List<String> list = (List<String>) toDate.getItemIds();
                            toDate.select(String.valueOf(list.get(list.size() - 1)));
                            if (frequencyList != null) {
                                int fromRange = frequencyList.indexOf(String.valueOf(fromDate.getValue()));
                                int toRange = frequencyList.indexOf(String.valueOf(toDate.getValue()));
                                List frequency = frequencyList.subList(fromRange, toRange + 1);
                                selectionDTO.setSummaryfrequencyList(listTolistOfStringArray(frequency));
                            }
                        }
                    } catch (Exception ex) {
                        logger.error("Error in view" , ex);
                    }
                }
            });
            fromDate.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                    if (event != null) {
                        String fromValue = String.valueOf(event.getProperty().getValue());
                        if ((!fromValue.equals(GlobalConstants.getSelectOne()) && !"null".equals(fromValue)) && (frequencyList != null && frequencyList.contains(fromValue))) {
                            int fromRange = frequencyList.indexOf(fromValue);
                            List frequency = frequencyList.subList(fromRange, frequencyList.size());
                            toDate.removeAllItems();
                            toDate.addItem(GlobalConstants.getSelectOne());
                            toDate.addItems(frequency);
                            toDate.select(GlobalConstants.getSelectOne());
                            selectionDTO.setSummaryfrequencyList(listTolistOfStringArray(new ArrayList(Arrays.asList(fromValue))));
                        }
                        if (ARMConstants.getSinglePeriod().equalsIgnoreCase(String.valueOf(view.getValue()))) {
                            toDate.setEnabled(true);
                            toDate.select(fromValue);
                            toDate.setEnabled(false);
                        } else {
                            toDate.setEnabled(true);
                        }
                    }
                }
            });
            toDate.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                    try {
                        String toDateValue = String.valueOf(event.getProperty().getValue());
                        if ((!toDateValue.equals(GlobalConstants.getSelectOne()) && !"null".equals(toDateValue)) && (ARMConstants.getMultiplePeriod().equalsIgnoreCase(selectionDTO.getSummarydemandview()))) {
                            int fromRange = frequencyList.indexOf(String.valueOf(fromDate.getValue()));
                            int toRange = frequencyList.indexOf(String.valueOf(toDate.getValue()));
                            List frequency = frequencyList.subList(fromRange, toRange + 1);
                            selectionDTO.setSummaryfrequencyList(listTolistOfStringArray(frequency));
                        }
                    } catch (Exception ex) {
                        logger.error("Error in todate" , ex);
                    }
                }

            });
            glImpactDate.addValueChangeListener(glListener);
            if (selectionDTO.getSessionDTO().isWorkFlow()) {
                frequencyDdlb.setEnabled(false);
            }
            deductionLevelDdlb.select(HelperListUtil.getInstance().getIdByDesc(VariableConstants.DEDUCTION_LEVELS, VariableConstants.DEDUCTION_PROGRAM));
        } catch (Exception e) {
            logger.error("Error in glimpactdate", e);
        }

    }

    protected abstract void configureSummary();

    private List<String[]> listTolistOfStringArray(List frequency) {
        List<String[]> frequencyListValue = new ArrayList<>();
        for (int i = 0; i < frequency.size(); i++) {
            String strings = (String) frequency.get(i);
            String[] stringArray = new String[NumericConstants.TWO];
            stringArray[0] = strings.replace(" ", StringUtils.EMPTY);
            stringArray[1] = strings;
            frequencyListValue.add(stringArray);
        }
        return frequencyListValue;
    }

    @Override
    public abstract void enter(ViewChangeListener.ViewChangeEvent event);

    protected abstract void customizeResetButtonLogic();

    protected abstract String[] getTableColumns();

    public void generateButtonLogic() {
        selectionDTO.setSummarydeductionVariables(CommonUtils.getCheckedValues(deductionCustomMenuItem));
        selectionDTO.setSummaryvariables(CommonUtils.getCheckedValues(customMenuItem));
        selectionDTO.setSummarydeductionLevel((int) deductionLevelDdlb.getValue());
        selectionDTO.setSummarydemandfromDate(String.valueOf(fromDate.getValue()));
        selectionDTO.setSummarydemandtoDate(String.valueOf(toDate.getValue()));
        List<String[]> listSize = selectionDTO.getSummarydeductionVariables();
        StringBuilder deductionValues = new StringBuilder();
        if (!listSize.isEmpty()) {
            for (int i = 0; i < listSize.size(); i++) {
                String value = listSize.get(i)[0];
                listSize.get(i)[0] = value.replace(" ", StringUtils.EMPTY).trim();
                if (i != listSize.size() - 1) {
                    deductionValues.append("'").append(value).append("',");
                } else {
                    deductionValues.append("'").append(value).append("'");
                }
            }
        }
        selectionDTO.setSummarydeductionValues(deductionValues.toString());
        selectionDTO.setSummarydeductionLevelDes(String.valueOf(deductionLevelDdlb.getItemCaption(deductionLevelDdlb.getValue())));
        String frequencyString = HelperListUtil.getInstance().getIdDescMap().get((Integer)(frequencyDdlb.getValue()));
        selectionDTO.setSummarydemandfrequency(frequencyString);
        selectionDTO.setSummaryfrequency((int) frequencyDdlb.getValue());
        adjustmentResults.configureLevelDDLBs();
        if (!logic.generateButtonCheck(selectionDTO) && !selectionDTO.getSessionDTO().isWorkFlow()) {
            AbstractNotificationUtils.getErrorNotification(ARMMessages.getGenerateMessageName_001(), ARMMessages.getGenerateMessage_Demand());
            return;
        }
        adjustmentResults.generateButtonLogic(getTableColumns());
        int configLevelid = HelperListUtil.getInstance().getIdByDesc("ARM_CONFIGURATION_TYPE", selectionDTO.getDetailLevel());
        logic.saveGLImpact(glImpactDate.getValue(), configLevelid, selectionDTO.getProjectionMasterSid());
    }

    public CustomMenuBar.CustomMenuItem getDeductionCustomMenuItem() {
        return deductionCustomMenuItem;
    }

    @Override
    public boolean saveAssets() {
        int configLevelid = HelperListUtil.getInstance().getIdByDesc("ARM_CONFIGURATION_TYPE", selectionDTO.getDetailLevel());
        return logic.saveGLImpact(glImpactDate.getValue(), configLevelid, selectionDTO.getProjectionMasterSid());
    }

    @Override
    public Focusable getDefaultFocusComponent() {
        return frequencyDdlb;
    }

    protected void setDefaultDeductionLevel(String value) {
        if (deductionLevelDdlb != null) {
            deductionLevelDdlb.select(HelperListUtil.getInstance().getIdByDesc(VariableConstants.DEDUCTION_LEVELS, value));
        }
    }

    @Override
    public boolean isGenerated() {
        return adjustmentResults.isGenerated();
    }
    protected Property.ValueChangeListener glListener = new Property.ValueChangeListener() {
        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            try {
                Date glDate = (Date) event.getProperty().getValue();
                Calendar glDateCal = Calendar.getInstance();
                glDateCal.setTime(glDate);
                dataSelectionLogic.dateCheckforGLCompAndBu(selectionDTO.getDataSelectionDTO(), false);
                List closedList = selectionDTO.getDataSelectionDTO().getNewClosedSummaryglList();
                String selectGlDate = glDateCal.get(Calendar.MONTH) + 1 + "-" + glDateCal.get(Calendar.YEAR);
                String dateString = dateFormat.format(glDate);
                if (closedList != null && !closedList.isEmpty() && closedList.contains(selectGlDate) && !(dateString.equals(selectionDTO.getSummaryglDate()))) {
                    AbstractNotificationUtils.getErrorNotification("Error", ARMMessages.getGLImpactMessageId001());
                    glImpactDate.setValue(dateFormat.parse(selectionDTO.getSummaryglDate()));
                } else {
                    selectionDTO.setSummaryglDate(dateFormat.format(glDate));
                }
            } catch (Exception ex) {
                logger.error("Error in glListener" , ex);
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
                dataSelectionLogic.dateCheckforGLCompAndBu(selectionDTO.getDataSelectionDTO(), false);
                List closedList = selectionDTO.getDataSelectionDTO().getNewClosedSummaryglList();
                String selectGlDate = glDateCal.get(Calendar.MONTH) + 1 + "-" + glDateCal.get(Calendar.YEAR);
                String dateString = dateFormat.format(glDate);
                if (defaultWorkFlowDate != null) {
                    defaultWorkFlowDateString = dateFormat.format(defaultWorkFlowDate);
                }
                if (closedList != null && !closedList.isEmpty() && closedList.contains(selectGlDate)) {
                    if (defaultWorkFlowDateString.equals(dateString)) {
                        glImpactDate.setValue(defaultWorkFlowDate);
                        selectionDTO.setSummaryglDate(dateFormat.format(defaultWorkFlowDate));
                    } else {
                        AbstractNotificationUtils.getErrorNotification("Error", ARMMessages.getGLImpactMessageId001());
                        glImpactDate.setValue(dateFormat.parse(selectionDTO.getSummaryglDate()));
                    }
                } else {
                    defaultWorkFlowDate = null;
                    selectionDTO.setSummaryglDate(dateFormat.format(glDate));
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

    private void loadDeductionValue() {
        deductionCustomMenuItem = CommonUtils.loadSummaryDeductionsDdlbForTrx6(deductionLevelDdlb, deductionValue, selectionDTO.getProjectionMasterSid());
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }

}
