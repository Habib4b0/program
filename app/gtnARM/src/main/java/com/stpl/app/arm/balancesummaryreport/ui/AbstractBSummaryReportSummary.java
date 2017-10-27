/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.balancesummaryreport.ui;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic.AbstractSummaryLogic;
import com.stpl.app.arm.businessprocess.commontemplates.SummarySelection;
import com.stpl.app.arm.common.CommonLogic;
import com.stpl.app.arm.security.StplSecurity;
import com.stpl.app.arm.supercode.DefaultFocusable;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.CommonConstant;
import com.stpl.app.arm.utils.HelperListUtil;
import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.serviceUtils.ConstantUtil;
import com.stpl.app.utils.ConstantsUtils;
import com.stpl.app.utils.CommonUtils;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import static com.stpl.ifs.ui.util.AbstractNotificationUtils.LOGGER;
import com.stpl.ifs.util.constants.ARMMessages;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customcombobox.CustomComboBox;
import org.asi.ui.custommenubar.CustomMenuBar;
import org.asi.ui.custommenubar.MenuItemDTO;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Srithar.Raju
 */
public abstract class AbstractBSummaryReportSummary extends VerticalLayout implements DefaultFocusable {

    private static final Logger LOGGER = Logger.getLogger(AbstractBSummaryReportSummary.class);
    /**
     * Allows the user to select what frequency the period that are included in
     * the report will be displayed in. Options are: Monthly, Quarterly,
     * Semi-Annually, Annually
     *
     */
    @UiField("frequencyDdlb")
    private ComboBox frequencyDdlb;
    /**
     * Allows the user to select the adjustment types they want displayed in the
     * list view. Multiple values can be selected. Options will be will
     * determined by the Adjustment Types that have been configured in the
     * â€œAdjustment Configuration UI. Example are the following: Pipeline
     * Accrual Demand Accrual Pipeline Inventory True-Up Demand Payment True-Up
     * Demand Accrual Reforecast Inflation Adjustment Distribution Fees Etc.
     *
     */
    @UiField("adjustmentType")
    private CustomMenuBar adjustmentType;

    /**
     * Ddlb options for Deduction Level are: DDLB Value = Deduction Category,
     * mapped to Rebate Schedule > Rebate Schedule Category DDLB Value =
     * Deduction Type, mapped to Rebate Schedule > Rebate Schedule Type DDLB
     * Value = Deduction Program, mapped to Rebate Schedule > Rebate Program
     * Type DDLB Value = Deduction mapped to Rebate Schedule > Rebate Schedule
     * ID DDLB Value = Deduction Category 1, mapped to Rebate Schedule > UDC 1
     * DDLB Value = Deduction Category DDLB Value = Deduction Category 2, mapped
     * to Rebate Schedule > UDC 2 DDLB Value = Deduction Category 3, mapped to
     * Rebate Schedule > UDC 3 DDLB Value = Deduction Category 4, mapped to
     * Rebate Schedule > UDC 4 DDLB Value = Deduction Category 5, mapped to
     * Rebate Schedule > UDC 5 DDLB Value = Deduction Category 6, mapped to
     * Rebate Schedule > UDC 6 DDLB Value = Deduction, mapped to Rebate Schedule
     * ID
     *
     */
    @UiField("deductionlevelDdlb")
    private CustomComboBox deductionlevelDdlb;
    /**
     * Deduction Value	Drop down list box	Deduction Value ddlb values will
     * depend on the value selected in Deduction Level. This ddlb will display
     * all of the unique values under the selected Deduction Level. For example,
     * if Deduction Level = Deduction Program, Deduction Value ddlb values will
     * be all of the unique values under Deduction Program (maps to Contract >
     * Rebate tab > Rebate Program Type) Allows the user to select multiple
     * values from the drop down. The user must select at least one variable to
     * generate the list view. When the â€œDeduction Level = Deductionâ€�, the
     * Deduction Values DDLB will show the possible values as â€œRebate Schedule
     * ID â€“ Rebate Schedule Nameâ€�. In the list views it will also display as
     * â€œRebate Schedule ID â€“ Rebate Schedule Nameâ€�. Yes, at least one must
     * be selected	Format: Alphanumeric Justification: Left *
     */
    @UiField("deductionValue")
    private CustomMenuBar deductionValue;

    @UiField("fromDate")
    protected ComboBox fromDate;

    @UiField("toDate")
    protected ComboBox toDate;

    @UiField("reset")
    Button resetBtn;

    @UiField("generate")
    Button generateBtn;

    protected CustomMenuBar.CustomMenuItem selecetedAdjustment;

    protected CustomMenuBar.CustomMenuItem selecedVariablesMenuItem;

    AbstractBalanceSummaryResutls adjustmentResults;

    String defaultToDate;

    /**
     * SummaryLogic logic has all depended methods for all summary logic classes
     */
    AbstractSummaryLogic logic;
    /**
     * SummarySelection is used to bind and pass through out the class
     * hierarchies
     */
    private SummarySelection selection;

    public AbstractBSummaryReportSummary(AbstractSummaryLogic logic, SummarySelection tselection) {
        this.logic = logic;
        this.selection = tselection;
        this.removeAllComponents();
        setSpacing(true);
        createTempTables();

    }

    protected void init() {
        loadInitialSelection();
        adjustmentResults = getResultsObject();
        adjustmentResults.getResults();
        adjustmentResults.init();
        addComponent(Clara.create(getClass().getResourceAsStream("/bussinessprocess/adjustmentSummaryDetails.xml"), this));
        setWidth("100%");
        addComponent(adjustmentResults);
        configurePermission();
        configureFields();
        List<String> finalList = CommonUtils.getToAndFromByFrequency(ARMUtils.frequencyVarables.MONTHLY.toString(), selection.getDataSelectionDTO().getFromDate(), selection.getDataSelectionDTO().getToDate());
        loadFrequency(finalList);
    }

    private void loadExpandCollapseddLb() {

    }

    private void loadInitialSelection() {
        selection.setFrequency(ARMUtils.frequencyVarables.QUARTERLY.toString());

    }

    private boolean loadSeletion() {
        List<List> adjustmentValues = CommonUtils.getSelectedVariables(selecetedAdjustment, Boolean.FALSE);
        boolean isAdjustNotSelected = false;
        boolean isDeductionNotSelected = false;
        if (!adjustmentValues.isEmpty()) {
            selection.setSelectedAdjustmentType(adjustmentValues.get(2));
            selection.setSelectedAdjustmentTypeValues(adjustmentValues.get(1));
        } else {
            isAdjustNotSelected = true;
        }
        List<List> deductionValues = CommonUtils.getSelectedVariables(selecedVariablesMenuItem, Boolean.FALSE);
        if (!deductionValues.isEmpty()) {
            selection.setDeductionVariableIds(deductionValues.get(2));
            selection.setDeductionVariablesValues(deductionValues.get(1));
            selection.setDeductionVariablesName(deductionValues.get(0));
        } else {
            isDeductionNotSelected = true;
        }
        selection.setSummarydeductionLevel((int) deductionlevelDdlb.getValue());
        selection.setSummarydeductionLevelDes(String.valueOf(deductionlevelDdlb.getItemCaption(deductionlevelDdlb.getValue())));
        String deductions;
        List<String> listSize = selection.getDeductionVariablesName();
        List finalDeductionList = new ArrayList();
        if (!listSize.isEmpty()) {
            for (String listSize1 : listSize) {
                String[] arr = listSize1.split("~");
                finalDeductionList.add(arr[0]);
            }
        }
        deductions = "'" + StringUtils.join(finalDeductionList.toArray(), "','") + "'";
        selection.setSummarydeductionValues(deductions);

        if (isAdjustNotSelected || isDeductionNotSelected || checkMandatoryFields() || (CommonConstant.SELECT_ONE).equals(String.valueOf(fromDate.getValue()))) {
            CustomNotification.getErrorNotification(ARMMessages.getGenerateMessageName_001(), ARMMessages.getGenerateMessage_MsgId_002());
            return false;
        }
        selection.setFrequency(frequencyDdlb.getItemCaption(frequencyDdlb.getValue()));
        selection.setSummaryFrequencyName(selection.getFrequency());
        selection.setFromDate(String.valueOf(fromDate.getValue()));
        selection.setFromDateFilter(CommonLogic.getFromDateFilter(String.valueOf(fromDate.getValue()),selection.getFrequency()));
        selection.setToDateFilter(CommonLogic.getFromDateFilter(String.valueOf(toDate.getValue()),selection.getFrequency()));
        selection.setDataSelectionFromDate(CommonLogic.getFromAndToPeriodBasedonFrequency(selection.getDataSelectionDTO().getFromDate(),selection.getFrequency()));
        selection.setDataSelectionToDate(CommonLogic.getFromAndToPeriodBasedonFrequency(selection.getDataSelectionDTO().getToDate(),selection.getFrequency()));
        if (toDate.getValue() != null && !(CommonConstant.SELECT_ONE).equals(String.valueOf(toDate.getValue()))) {
            selection.setToDate(String.valueOf(toDate.getValue()));
        } else {
            selection.setToDate(defaultToDate);
        }
        selection.setSummaryPeriods(CommonLogic.getSummaryPeriods(String.valueOf(fromDate.getValue()), String.valueOf(toDate.getValue()), selection.getFrequency()));
        selection.setPeriodSidList(CommonLogic.getPeriodSidsList(String.valueOf(fromDate.getValue()), String.valueOf(toDate.getValue()), selection.getFrequency()));
        return true;
    }

    private boolean checkMandatoryFields() {
        if (Integer.valueOf(frequencyDdlb.getValue().toString()) == 0 || Integer.valueOf(deductionlevelDdlb.getValue().toString()) == 0
                || selection.getSelectedAdjustmentTypeValues().isEmpty() || selection.getDeductionVariableIds().isEmpty()
                || CommonConstant.SELECT_ONE.equals(selection.getStatus())) {
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    @Override
    public Component.Focusable getDefaultFocusComponent() {
        return frequencyDdlb;
    }

    private Object[] getProcedureInput() {
        Object[] inputs = new Object[3];
        inputs[0] = selection.getDataSelectionDTO().getProjectionId();
        inputs[1] = selection.getSessionDTO().getUserId();
        inputs[2] = selection.getSessionDTO().getSessionId();
        return inputs;
    }

    private void createTempTables() {
        Map createTempTables = QueryUtils.createTempTables(getTempTablesPropertyName(), selection.getDataSelectionDTO().getProjectionId(), String.valueOf(selection.getSessionDTO().getUserId()), String.valueOf(selection.getSessionDTO().getSessionId()));
        selection.getSessionDTO().setCurrentTableNames(createTempTables);
    }

    class CustomNotification extends AbstractNotificationUtils {

        String buttonName;

        @Override
        public void noMethod() {
            LOGGER.debug("Inside the CustomNotification Listener NO Method");
        }

        @Override
        public void yesMethod() {
            LOGGER.debug("buttonName :" + buttonName);
            if (null != buttonName) {
                switch (buttonName) {
                    case CommonConstant.RESET:
                        break;
                    case "save":
                        break;
                    default:
                }
            }
        }

        public void setButtonName(String buttonName) {
            this.buttonName = buttonName;
        }

    }

    public void configureFields() {
        CommonUtils.loadComboBoxWithIntegerForComboBox(frequencyDdlb, "PAYMENT_FREQUENCY", false);
        selecedVariablesMenuItem = CommonUtils.loadSummaryDeductionsDdlb(deductionlevelDdlb, deductionValue, selection.getDataSelectionDTO().getProjectionId());
        selecetedAdjustment = adjustmentType.addItem("  - Select Value -  ", null);
        loadAdjustmentTypeDdlb(adjustmentType, selecetedAdjustment);
        deductionlevelDdlb.setValue(HelperListUtil.getInstance().getIdByDesc("DEDUCTION_LEVELS", "Deduction Program"));
        fromDate.setNullSelectionAllowed(false);
        toDate.setNullSelectionAllowed(false);

    }

    public void resetBtn() {
        resetBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    new AbstractNotificationUtils() {
                        @Override
                        public void yesMethod() {
                            try {
                                frequencyDdlb.setValue(0);
                                deductionlevelDdlb.setValue(0);
                                loadAdjustmentTypeDdlb(adjustmentType, selecetedAdjustment);
                                List<String> finalList = CommonUtils.getToAndFromByFrequency(ARMUtils.frequencyVarables.MONTHLY.toString(), selection.getDataSelectionDTO().getFromDate(), selection.getDataSelectionDTO().getToDate());
                                loadFrequency(finalList);
                            } catch (Exception ex) {
                                LOGGER.error("Error in resetBtn :"+ex);
                            }
                        }

                        @Override
                        public void noMethod() {
                            LOGGER.debug("Inside the resetbtn Listener NO Method");
                        }
                    }.getConfirmationMessage(ARMMessages.getResetMessageName_001(), ARMMessages.getResetMessageID002());
                } catch (Exception e) {
                    LOGGER.error("Error in resetBtn :"+e);
                }
            }
        });
    }

    public void generateBtn() {
        generateBtn.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                try {
                    if (loadSeletion()) {
                        CommonUtils.callInsertProcedure(getProcedureName(), getProcedureInput());
                        adjustmentResults.generateBtnLogic();
                        loadExpandCollapseddLb();
                    }
                } catch (Exception e) {
                    LOGGER.error("Error in generateBtn :"+e);
                }
            }
        });
    }

    /**
     * Method to get Values for adjustment type
     *
     * @return String List
     */
    protected List<String> getCheckedValues() {
        if (selecetedAdjustment != null && selecetedAdjustment.getSize() > 0) {
            List<String> result = new ArrayList<>();
            List<CustomMenuBar.CustomMenuItem> items = selecetedAdjustment.getChildren();
            for (CustomMenuBar.CustomMenuItem customMenuItem1 : items) {
                if (customMenuItem1.isChecked()) {
                    result.add(customMenuItem1.getMenuItem().getCaption());
                }
            }
            return result;
        }
        return Collections.emptyList();
    }

    /**
     * Method to get Values for Deduction value.
     *
     * @return String List
     */
    protected Map<Integer, String> getDeductionValue() {
        if (deductionValue.getItems() != null) {
            Map<Integer, String> result = new HashMap<>();
            if (deductionValue.getItems() != null) {
                for (CustomMenuBar.CustomMenuItem item : deductionValue.getItems()) {
                    if (item != null) {
                        for (CustomMenuBar.CustomMenuItem child : item.getChildren()) {
                            if (child.isChecked()) {
                                result.put(child.getMenuItem().getId(), child.getMenuItem().getCaption());
                            }
                        }
                    }
                }
            }
            return result;
        }
        return Collections.emptyMap();
    }

    @UiHandler("frequencyDdlb")
    public void frequencyChangeLogic(Property.ValueChangeEvent event) {
        if ((int) frequencyDdlb.getValue() != 0) {
            List<String> finalList = CommonUtils.getToAndFromByFrequency(frequencyDdlb.getItemCaption(frequencyDdlb.getValue()), selection.getDataSelectionDTO().getFromDate(), selection.getDataSelectionDTO().getToDate());
            if (!finalList.isEmpty()) {
                loadFrequency(finalList);
            }
        } else {
            fromDate.removeAllItems();
            fromDate.addItem(0);
            fromDate.setItemCaption(0,GlobalConstants.getSelectOne());
            fromDate.select(0);
            toDate.removeAllItems();
            toDate.addItem(0);
            toDate.setItemCaption(0,GlobalConstants.getSelectOne());
            toDate.select(0);
        }
    }

    private void loadFrequency(List<String> finalList) {
        fromDate.removeAllItems();
        toDate.removeAllItems();
        for (String finalList1 : finalList) {
            fromDate.addItem(finalList1);
            toDate.addItem(finalList1);
        }
        fromDate.select(finalList.get(1));
        toDate.select(finalList.get(finalList.size() - 1));
        defaultToDate = finalList.get(finalList.size() - 1);
    }

    public final CustomMenuBar.CustomMenuItem loadAdjustmentTypeDdlb(final CustomMenuBar menuBar, CustomMenuBar.CustomMenuItem customMenuItemDed) {
        menuBar.setScrollable(true);
        menuBar.setPageLength(10);
        String[] defaultColumn = getAdjustmentTypeColumn();
        String[] defaultColumnHeader = getAdjustmentTypeHeader();
        CustomMenuBar.CustomMenuItem[] customItem = new CustomMenuBar.CustomMenuItem[defaultColumnHeader.length];
        customMenuItemDed.removeChildren();
        
        for (int i = 0; i < defaultColumn.length; i++) {
            MenuItemDTO menudto = new MenuItemDTO(defaultColumn[i], defaultColumnHeader[i]);
            customItem[i] = customMenuItemDed.addItem(menudto, null);
            customItem[i].setCheckable(true);
            customItem[i].setItemClickable(true);
            customItem[i].setItemClickNotClosable(true);
        }
        return customMenuItemDed;
    }

    public abstract String[] getDefaultColumnsHeader();

    public abstract String[] getDefaultColumns();
    
    public abstract String[] getAdjustmentTypeColumn();
    
    public abstract String[] getAdjustmentTypeHeader();

    public abstract String getTempTablesPropertyName();

    public abstract AbstractBalanceSummaryResutls getResultsObject();

    public abstract String getProcedureName();

    private void configurePermission() {
        try {
            final StplSecurity stplSecurity = new StplSecurity();
            final String userId = String.valueOf(VaadinSession.getCurrent()
                    .getAttribute(ConstantsUtils.USER_ID));

            final Map<String, AppPermission> functionCfpHM = stplSecurity.getBusinessFunctionPermission(userId, ARMUtils.BALANCE_SUMMARY_REPORT + ConstantUtil.COMMA + ConstantsUtils.SUMMARY);
            if (functionCfpHM.get("generate") != null && (functionCfpHM.get("generate")).isFunctionFlag()) {
                generateBtn();
            } else {
                generateBtn.setVisible(false);
            }
            if (functionCfpHM.get(CommonConstant.RESET) != null && (functionCfpHM.get(CommonConstant.RESET)).isFunctionFlag()) {
                resetBtn();
            } else {
                resetBtn.setVisible(false);
            }

        } catch (PortalException | SystemException ex) {
            LOGGER.error("Error in configurePermission :"+ex);
        }
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public SummarySelection getSelection() {
        return selection;
    }
}
