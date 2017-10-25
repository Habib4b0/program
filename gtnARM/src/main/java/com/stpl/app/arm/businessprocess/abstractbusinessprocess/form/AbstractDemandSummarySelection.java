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
import com.stpl.app.arm.security.StplSecurity;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.HelperListUtil;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.serviceUtils.ConstantUtil;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.app.utils.CommonUtils;
import com.stpl.app.utils.VariableConstants;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.ifs.util.constants.ARMConstants;
import com.stpl.ifs.util.constants.ARMMessages;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.VerticalLayout;
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
import org.jboss.logging.Logger;
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
    protected String[] variableHeader_deduction;
    protected String[] variableVisibleColumns;
    protected String[] variableVisibleColumns_deduction;
    protected CustomMenuBar.CustomMenuItem deductionCustomMenuItem;
    protected CustomMenuBar.CustomMenuItem customMenuItem;
    @UiField("deductionLevelDdlb")
    protected ComboBox deductionLevelDdlb;
    @UiField("deductionValue")
    private CustomMenuBar deductionValue;
    private List frequencyList;
    public AbstractSelectionDTO selectionDTO;
    public AbstractDemandSummaryResults adjustmentResults;
    public Map<Integer, HelperDTO> descriptionMap;
    protected final AbstractDemandSummaryLogic logic;
    public DataSelectionLogic dataSelectionLogic = new DataSelectionLogic();
    protected String format = "MM/dd/yyyy";
    protected DateFormat dateFormat = new SimpleDateFormat(format);
    private Date glDateField;
    protected Date defaultWorkFlowDate;
    protected Date resetWorkFlowDate;
    private final Logger LOGGER = Logger.getLogger(getClass());

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
                            deductionLevelDdlb.select(HelperListUtil.getInstance().getIdByDesc(VariableConstants.DEDUCTION_LEVELS, VariableConstants.DEDUCTION_PROGRAM));
                            CommonUtils.unCheckMenuBarItem(customMenuItem);
                            frequencyDdlb.select(0);
                            view.select(ARMConstants.getSinglePeriod());
                            toDate.select(ConstantsUtils.SELECT_ONE);
                            fromDate.select(ConstantsUtils.SELECT_ONE);
                            if (selectionDTO.getSessionDTO().isWorkFlow()) {
                                glImpactDate.removeValueChangeListener(glWorkflowListener);
                                glImpactDate.setValue(resetWorkFlowDate);
                                selectionDTO.setSummary_glDate(dateFormat.format(resetWorkFlowDate));
                                defaultWorkFlowDate = resetWorkFlowDate;
                                glImpactDate.addValueChangeListener(glWorkflowListener);
                            } else {
                                glImpactDate.removeValueChangeListener(glListener);
                                glImpactDate.setValue(glDateField);
                                selectionDTO.setSummary_glDate(dateFormat.format(glDateField));
                                glImpactDate.addValueChangeListener(glListener);
                            }
                            customizeResetButtonLogic();
                        } catch (Exception ex) {
                            LOGGER.error(ex);
                        }
                        break;
                    case "save":
                        // save logic
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
                LOGGER.debug("Inside generate ButtonClick Btn in abstractdemandsummaryselection");
                try {
                    generateButtonLogic();
                } catch (Exception e) {
                    LOGGER.error(e);
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
            selectionDTO.setSummary_demand_view(String.valueOf(view.getValue()));
            variables.setPageLength(variableHeader.length);
            customMenuItem = variables.addItem("-Select Variables-", null);
            CommonUtils.loadCustomMenu(customMenuItem, variableHeader, variableVisibleColumns);
            deductionCustomMenuItem = CommonUtils.loadSummaryDeductionsDdlb(deductionLevelDdlb, deductionValue, selectionDTO.getProjectionMasterSid());
            deductionLevelDdlb.select(HelperListUtil.getInstance().getIdByDesc(VariableConstants.DEDUCTION_LEVELS, VariableConstants.DEDUCTION_PROGRAM));
            glDateField = logic.getGlImpactDate(selectionDTO);
            glImpactDate.setValue(glDateField);
            selectionDTO.setSummary_glDate(dateFormat.format(glDateField));
            toDate.select(ConstantsUtils.SELECT_ONE);
            fromDate.select(ConstantsUtils.SELECT_ONE);
            descriptionMap = HelperListUtil.getInstance().getIdHelperDTOMap();
            frequencyDdlb.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                    int ddlbVal = Integer.valueOf(String.valueOf(event.getProperty().getValue()));
                    if (ddlbVal > 0) {
                        String frequency = HelperListUtil.getInstance().getIdDescMap().get(ddlbVal);
                        if (!selectionDTO.getSessionDTO().isWorkFlow()) {
                            if ("Monthly".equalsIgnoreCase(frequency) && deductionLevelDdlb.getItemCaption(deductionLevelDdlb.getValue()).equals(ARMConstants.getDeduction())) {
                                CommonUtils.loadCustomMenu(customMenuItem, variableHeader_deduction, variableVisibleColumns_deduction);
                            } else {
                                CommonUtils.loadCustomMenu(customMenuItem, variableHeader, variableVisibleColumns);
                            }
                        }
                        selectionDTO.setSummary_demand_frequency(frequency);
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
                            selectionDTO.setSummary_frequencyList(listTolistOfStringArray(new ArrayList(Arrays.asList(String.valueOf(fromDate.getValue())))));
                        } else {
                            int fromRange = frequencyList.indexOf(String.valueOf(fromDate.getValue()));
                            int toRange = frequencyList.indexOf(String.valueOf(toDate.getValue()));
                            List frequencylist = frequencyList.subList(fromRange, toRange + 1);
                            selectionDTO.setSummary_frequencyList(listTolistOfStringArray(frequencylist));
                        }
                    } else {
                        selectionDTO.setSummary_demand_frequency(StringUtils.EMPTY);
                        fromDate.select(ConstantsUtils.SELECT_ONE);
                        toDate.select(ConstantsUtils.SELECT_ONE);
                    }
                }
            });
            deductionLevelDdlb.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                    String deductionType = String.valueOf(deductionLevelDdlb.getItemCaption(event.getProperty().getValue()));
                    String frequency = StringUtils.EMPTY;
                    if (!selectionDTO.getSessionDTO().isWorkFlow()) {
                        frequency = selectionDTO.getSummary_demand_frequency();
                    } else if (frequency.matches("^[-+]?\\d+(\\.\\d+)?$")) {
                        frequency = HelperListUtil.getInstance().getIdDescMap().get(selectionDTO.getSummary_demand_frequency());
                    } else {
                        frequency = selectionDTO.getSummary_demand_frequency();
                    }
                    if (selectionDTO.getSessionDTO().isWorkFlow()) {
                        frequency = HelperListUtil.getInstance().getIdDescMap().get(Integer.valueOf(selectionDTO.getSummary_demand_frequency()));
                    }
                    if (deductionType.equals(ARMConstants.getDeduction()) && "Monthly".equalsIgnoreCase(frequency)) {
                        CommonUtils.loadCustomMenu(customMenuItem, variableHeader_deduction, variableVisibleColumns_deduction);
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
                        selectionDTO.setSummary_demand_view(viewType);
                        if (ARMConstants.getSinglePeriod().equalsIgnoreCase(viewType)) {
                            toDate.select(String.valueOf(fromDate.getValue()));
                            toDate.setEnabled(false);
                            selectionDTO.setSummary_frequencyList(listTolistOfStringArray(new ArrayList(Arrays.asList(String.valueOf(fromDate.getValue())))));
                        } else {
                            toDate.setEnabled(true);
                            List<String> list = (List<String>) toDate.getItemIds();
                            toDate.select(String.valueOf(list.get(list.size() - 1)));
                            if (frequencyList != null) {
                                int fromRange = frequencyList.indexOf(String.valueOf(fromDate.getValue()));
                                int toRange = frequencyList.indexOf(String.valueOf(toDate.getValue()));
                                List frequency = frequencyList.subList(fromRange, toRange + 1);
                                selectionDTO.setSummary_frequencyList(listTolistOfStringArray(frequency));
                            }
                        }
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }
            });
            fromDate.addValueChangeListener(new Property.ValueChangeListener() {
                @Override
                public void valueChange(Property.ValueChangeEvent event) {
                    if (event != null) {
                        String fromValue = String.valueOf(event.getProperty().getValue());
                        if ((!fromValue.equals(ConstantsUtils.SELECT_ONE) && !fromValue.equals("null")) && (frequencyList != null && frequencyList.contains(fromValue))) {
                            int fromRange = frequencyList.indexOf(fromValue);
                            List frequency = frequencyList.subList(fromRange, frequencyList.size());
                            toDate.removeAllItems();
                            toDate.addItem(ConstantsUtils.SELECT_ONE);
                            toDate.addItems(frequency);
                            toDate.select(ConstantsUtils.SELECT_ONE);
                            selectionDTO.setSummary_frequencyList(listTolistOfStringArray(new ArrayList(Arrays.asList(fromValue))));
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
                        String ToDate = String.valueOf(event.getProperty().getValue());
                        if ((!ToDate.equals(ConstantsUtils.SELECT_ONE) && !ToDate.equals("null")) && (ARMConstants.getMultiplePeriod().equalsIgnoreCase(selectionDTO.getSummary_demand_view()))) {
                            int fromRange = frequencyList.indexOf(String.valueOf(fromDate.getValue()));
                            int toRange = frequencyList.indexOf(String.valueOf(toDate.getValue()));
                            List frequency = frequencyList.subList(fromRange, toRange + 1);
                            selectionDTO.setSummary_frequencyList(listTolistOfStringArray(frequency));
                        }
                    } catch (Exception ex) {
                        LOGGER.error(ex);
                    }
                }

            });
            glImpactDate.addValueChangeListener(glListener);
            if (selectionDTO.getSessionDTO().isWorkFlow()) {
                frequencyDdlb.setEnabled(false);
            }
            deductionLevelDdlb.select(HelperListUtil.getInstance().getIdByDesc(VariableConstants.DEDUCTION_LEVELS, VariableConstants.DEDUCTION_PROGRAM));
        } catch (Exception e) {
            LOGGER.error(e);
        }

    }

    protected abstract void configureSummary();

    private List<String[]> listTolistOfStringArray(List frequency) {
        List<String[]> frequencyList = new ArrayList<>();
        for (int i = 0; i < frequency.size(); i++) {
            String strings = (String) frequency.get(i);
            String[] stringArray = new String[NumericConstants.TWO];
            stringArray[0] = strings.replace(" ", StringUtils.EMPTY);
            stringArray[1] = strings;
            frequencyList.add(stringArray);
        }
        return frequencyList;
    }

    public abstract void enter(ViewChangeListener.ViewChangeEvent event);

    protected abstract void customizeResetButtonLogic();

    protected abstract String[] getTableColumns();

    public void generateButtonLogic() {
        selectionDTO.setSummary_deductionVariables(CommonUtils.getCheckedValues(deductionCustomMenuItem));
        selectionDTO.setSummary_variables(CommonUtils.getCheckedValues(customMenuItem));
        selectionDTO.setSummary_deductionLevel((int) deductionLevelDdlb.getValue());
        selectionDTO.setSummary_demand_fromDate(String.valueOf(fromDate.getValue()));
        selectionDTO.setSummary_demand_toDate(String.valueOf(toDate.getValue()));
        List<String[]> listSize = selectionDTO.getSummary_deductionVariables();
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
        selectionDTO.setSummary_deductionValues(deductionValues);
        selectionDTO.setSummary_deductionLevelDes(String.valueOf(deductionLevelDdlb.getItemCaption(deductionLevelDdlb.getValue())));
        String frequencyString = HelperListUtil.getInstance().getIdDescMap().get(Integer.valueOf(String.valueOf(frequencyDdlb.getValue())));
        selectionDTO.setSummary_demand_frequency(frequencyString);
        selectionDTO.setSummary_frequency((int) frequencyDdlb.getValue());
        adjustmentResults.configureLevelDDLBs();
        if (!logic.generateButtonCheck(selectionDTO) && !selectionDTO.getSessionDTO().isWorkFlow()) {
            AbstractNotificationUtils.getErrorNotification(ARMMessages.getGenerateMessageName_001(), ARMMessages.getGenerateMessage_Demand());
            return;
        }
        adjustmentResults.generateButtonLogic(getTableColumns());
        int configLevelid = HelperListUtil.getInstance().getIdByDesc("ARM_CONFIGURATION_TYPE", selectionDTO.getDetail_Level());
        logic.saveGLImpact(glImpactDate.getValue(), configLevelid, selectionDTO.getProjectionMasterSid());
    }

    public CustomMenuBar.CustomMenuItem getDeductionCustomMenuItem() {
        return deductionCustomMenuItem;
    }

    @Override
    public boolean saveAssets() {
        int configLevelid = HelperListUtil.getInstance().getIdByDesc("ARM_CONFIGURATION_TYPE", selectionDTO.getDetail_Level());
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
    public Property.ValueChangeListener glListener = new Property.ValueChangeListener() {
        @Override
        public void valueChange(Property.ValueChangeEvent event) {
            try {
                Date glDate = (Date) event.getProperty().getValue();
                Calendar glDateCal = Calendar.getInstance();
                glDateCal.setTime(glDate);
                dataSelectionLogic.dateCheckforGLCompAndBu(selectionDTO.getDataSelectionDTO(), false);
                List closedList = selectionDTO.getDataSelectionDTO().getNewClosedSummary_glList();
                String selectGlDate = glDateCal.get(Calendar.MONTH) + 1 + "-" + glDateCal.get(Calendar.YEAR);
                String dateString = dateFormat.format(glDate);
                if (closedList != null && !closedList.isEmpty() && closedList.contains(selectGlDate) && !(dateString.equals(selectionDTO.getSummary_glDate()))) {
                    AbstractNotificationUtils.getErrorNotification("Error", ARMMessages.getGLImpactMessageId001());
                    glImpactDate.setValue(dateFormat.parse(selectionDTO.getSummary_glDate()));
                } else {
                    selectionDTO.setSummary_glDate(dateFormat.format(glDate));
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
                dataSelectionLogic.dateCheckforGLCompAndBu(selectionDTO.getDataSelectionDTO(), false);
                List closedList = selectionDTO.getDataSelectionDTO().getNewClosedSummary_glList();
                String selectGlDate = glDateCal.get(Calendar.MONTH) + 1 + "-" + glDateCal.get(Calendar.YEAR);
                String dateString = dateFormat.format(glDate);
                if (defaultWorkFlowDate != null) {
                    defaultWorkFlowDateString = dateFormat.format(defaultWorkFlowDate);
                }
                if (closedList != null && !closedList.isEmpty() && closedList.contains(selectGlDate)) {
                    if (defaultWorkFlowDateString.equals(dateString)) {
                        glImpactDate.setValue(defaultWorkFlowDate);
                        selectionDTO.setSummary_glDate(dateFormat.format(defaultWorkFlowDate));
                    } else {
                        AbstractNotificationUtils.getErrorNotification("Error", ARMMessages.getGLImpactMessageId001());
                        glImpactDate.setValue(dateFormat.parse(selectionDTO.getSummary_glDate()));
                    }
                } else {
                    defaultWorkFlowDate = null;
                    selectionDTO.setSummary_glDate(dateFormat.format(glDate));
                }
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }
    };

}
