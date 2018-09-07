/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentsummary.form;

import com.stpl.app.arm.adjustmentsummary.logic.SummaryLogic;
import com.stpl.app.arm.businessprocess.commontemplates.SummarySelection;
import com.stpl.app.arm.common.dto.SessionDTO;
import com.stpl.app.arm.security.StplSecurity;
import com.stpl.app.arm.supercode.DefaultFocusable;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.CommonConstant;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.utils.CommonUtils;
import com.stpl.app.utils.VariableConstants;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.ARMMessages;
import com.vaadin.v7.data.Property;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.VerticalLayout;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customcombobox.CustomComboBox;
import org.asi.ui.custommenubar.CustomMenuBar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Asha.Ravi
 */
public class AdjustmentSummary extends VerticalLayout implements View, DefaultFocusable {

    private static final Logger LOGGER = LoggerFactory.getLogger(AdjustmentSummary.class);
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
     * generate the list view. When the â€œDeduction Level =
     * Deductionâ€�, the Deduction Values DDLB will show the possible
     * values as â€œRebate Schedule ID â€“ Rebate Schedule
     * Nameâ€�. In the list views it will also display as â€œRebate
     * Schedule ID â€“ Rebate Schedule Nameâ€�. Yes, at least one must
     * be selected	Format: Alphanumeric Justification: Left *
     */
    @UiField("deductionValue")
    private CustomMenuBar deductionValue;
    @UiField("reset")
    private Button reset;

    @UiField("generate")
    private Button generate;

    @UiField("fromDate")
    protected ComboBox fromDate;

    @UiField("toDate")
    protected ComboBox toDate;

    /**
     * SummarySelection is used to bind and pass through out the class
     * hierarchies
     */
    private SummarySelection summarySelection;

    protected CustomMenuBar.CustomMenuItem selecetedAdjustment;

    protected CustomMenuBar.CustomMenuItem selecedVariablesMenuItem;

    private AdjustmentSummaryResults adjustmentResults;

    private String defaultToDate;
    private SessionDTO sessionDTO;

    public AdjustmentSummary(SummarySelection selection, SessionDTO sessionDTO) {
        this.summarySelection = selection;
        this.sessionDTO = sessionDTO;
        init();
    }

    private void init() {
        loadInitialSelection();
        adjustmentResults = new AdjustmentSummaryResults(new SummaryLogic(), summarySelection);
        adjustmentResults.getResults();
        adjustmentResults.init();
        addComponent(Clara.create(getClass().getResourceAsStream("/bussinessprocess/adjustmentSummaryDetails.xml"), this));
        setWidth("100%");
        addComponent(adjustmentResults);
        configureFields();
        List<String> finalList = CommonUtils.getToAndFromByFrequency(ARMUtils.frequencyVarables.MONTHLY.toString(), summarySelection.getDataSelectionDTO().getFromDate(), summarySelection.getDataSelectionDTO().getToDate());
        loadFrequency(finalList);
        attachStatusDdlbListener();
        configureSecurity();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void loadExpandCollapseddLb() {
        LOGGER.debug("Inside loadExpandCollapseddLb Method");

    }

    private void loadInitialSelection() {
        summarySelection.setFrequency(ARMUtils.frequencyVarables.QUARTERLY.toString());
    }

    private boolean loadSeletion() {
        List<List> adjustmentValues = CommonUtils.getSelectedVariables(selecetedAdjustment, Boolean.FALSE);
        boolean isAdjustNotSelected = false;
        boolean isDeductionNotSelected = false;
        if (!adjustmentValues.isEmpty()) {
            summarySelection.setSelectedAdjustmentType(adjustmentValues.get(NumericConstants.TWO));
            summarySelection.setSelectedAdjustmentTypeValues(adjustmentValues.get(1));
        } else {
            isAdjustNotSelected = true;
        }
        List<List> deductionValues = CommonUtils.getSelectedVariables(selecedVariablesMenuItem, Boolean.FALSE);
        if (!deductionValues.isEmpty()) {
            summarySelection.setDeductionVariableIds(deductionValues.get(NumericConstants.TWO));
            summarySelection.setDeductionVariablesValues(deductionValues.get(1));
            summarySelection.setDeductionVariablesName(deductionValues.get(0));
        } else {
            isDeductionNotSelected = true;
        }
        summarySelection.setSummarydeductionLevel((int) deductionlevelDdlb.getValue());
        summarySelection.setSummarydeductionLevelDes(String.valueOf(deductionlevelDdlb.getItemCaption(deductionlevelDdlb.getValue())));
        String deductions;
        List<String> listSize = summarySelection.getDeductionVariablesName();
        List finalDeductionList = new ArrayList();
        if (!listSize.isEmpty()) {
            for (int i = 0; i < listSize.size(); i++) {
                String[] arr = listSize.get(i).split("~");
                finalDeductionList.add(arr[0]);
            }
        }
        deductions = ARMUtils.SINGLE_QUOTES + StringUtils.join(finalDeductionList.toArray(), "','") + ARMUtils.SINGLE_QUOTES;
        summarySelection.setSummarydeductionValues(deductions);

        if (isAdjustNotSelected || isDeductionNotSelected || checkMandatoryFields() || VariableConstants.SELECT_ONE.equals(String.valueOf(fromDate.getValue()))) {
            AdjustmentSummaryCustomNotification.getErrorNotification(ARMMessages.getGenerateMessageName_001(), ARMMessages.getGenerateMessage_MsgId_002());
            return false;
        }
        summarySelection.setFrequency(frequencyDdlb.getItemCaption(frequencyDdlb.getValue()));
        summarySelection.setSummaryFrequencyName(summarySelection.getFrequency());
        summarySelection.setFromDate(String.valueOf(fromDate.getValue()));
        if (toDate.getValue() != null && !VariableConstants.SELECT_ONE.equals(String.valueOf(toDate.getValue()))) {
            summarySelection.setToDate(String.valueOf(toDate.getValue()));
        } else {
            summarySelection.setToDate(defaultToDate);
        }
        LOGGER.debug("String.valueOf(fromDate.getValue()---->>{}", fromDate.getValue().toString());
        LOGGER.debug("String.valueOf(toDate.getValue()---->>{}", toDate.getValue().toString());
        List<List> statusValues = CommonUtils.getSelectedVariables(adjustmentResults.statusMenuItem, Boolean.FALSE);
        summarySelection.setStatus(statusValues.get(1).toString().toUpperCase(Locale.ENGLISH));
        summarySelection.setSummaryStatusID(statusValues.get(NumericConstants.TWO).toString());
        return true;
    }

    private boolean checkMandatoryFields() {
        if (Integer.valueOf(frequencyDdlb.getValue().toString()) == 0 || Integer.valueOf(deductionlevelDdlb.getValue().toString()) == 0
                || summarySelection.getSelectedAdjustmentTypeValues().isEmpty() || summarySelection.getDeductionVariableIds().isEmpty()
                || VariableConstants.SELECT_ONE.equals(summarySelection.getStatus())) {
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    @Override
    public Focusable getDefaultFocusComponent() {
        return frequencyDdlb;
    }

    private void attachStatusDdlbListener() {
        adjustmentResults.statusDdlb.addSubMenuCloseListener(new CustomMenuBar.SubMenuCloseListener() {

            @Override
            public void subMenuClose(CustomMenuBar.SubMenuCloseEvent event) {
                if (loadSeletion()) {
                    adjustmentResults.generateBtnLogic();
                    loadExpandCollapseddLb();
                }
            }
        });
    }

    private void configureSecurityForButton(Map<String, AppPermission> functionHM) {
        if (functionHM.get("collapseBtn") != null && !(functionHM.get("collapseBtn")).isFunctionFlag()) {
            adjustmentResults.getCollapseBtn().setVisible(false);
        } else {
            adjustmentResults.getCollapseBtn().setVisible(true);
        }
        if (functionHM.get("BBExport") != null && !(functionHM.get("BBExport")).isFunctionFlag()) {
            adjustmentResults.getBbExport().setVisible(false);
        } else {
            adjustmentResults.getBbExport().setVisible(true);
        }
    }

    class AdjustmentSummaryCustomNotification extends AbstractNotificationUtils {

        private String summaryButtonName;

        AdjustmentSummaryCustomNotification() {

        }

        @Override
        public void noMethod() {
            LOGGER.debug("Inside the CustomNotification Listener NO Method");
        }

        @Override
        public void yesMethod() {
            LOGGER.debug("buttonName :{}", summaryButtonName);
            if (null != summaryButtonName) {
                switch (summaryButtonName) {
                    case CommonConstant.RESET:
                        break;
                    case "save":
                        break;
                    default:
                }
            }
        }

        public void setButtonName(String buttonName) {
            this.summaryButtonName = buttonName;
        }

    }

    public void configureFields() {
        CommonUtils.loadComboBoxWithIntegerForComboBox(frequencyDdlb, "PAYMENT_FREQUENCY", false);
        selecedVariablesMenuItem = CommonUtils.loadSummaryDeductionsDdlb(deductionlevelDdlb, deductionValue, summarySelection.getDataSelectionDTO().getProjectionId());
        selecetedAdjustment = adjustmentType.addItem("  - Select Value -  ", null);
        CommonUtils.loadAdjustmentTypeDdlb(adjustmentType, selecetedAdjustment);

    }

    @UiHandler("reset")
    public void resetButtonClick(Button.ClickEvent event) {
        try {
            new AbstractNotificationUtils() {
                @Override
                public void yesMethod() {
                    try {
                        frequencyDdlb.setValue(0);
                        deductionlevelDdlb.setValue(0);
                        CommonUtils.loadAdjustmentTypeDdlb(adjustmentType, selecetedAdjustment);
                    } catch (Exception ex) {
                        LOGGER.error("Error in resetButtonClick :", ex);
                    }
                }

                @Override
                public void noMethod() {
                    LOGGER.debug("Inside the resetButtonClick Listener NO Method");

                }
            }.getConfirmationMessage(ARMMessages.getResetMessageName_001(), ARMMessages.getResetMessageID002());
        } catch (Exception e) {
            LOGGER.error("Error in resetButtonClick:", e);
        }
    }

    @UiHandler("generate")
    public void generateButtonClick(Button.ClickEvent event) {
        LOGGER.debug("Inside generate ButtonClick Btn");
        try {
            if (loadSeletion()) {
                adjustmentResults.generateBtnLogic();
                loadExpandCollapseddLb();
            }
        } catch (Exception e) {
            LOGGER.error("Error in generateButtonClick :", e);
        }
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
            for (Iterator<CustomMenuBar.CustomMenuItem> it = items.iterator(); it.hasNext();) {
                CustomMenuBar.CustomMenuItem customMenuItem1 = it.next();
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
                getResults(result);
            }
            return result;
        }
        return Collections.emptyMap();
    }

    private void getResults(Map<Integer, String> result) {
        for (CustomMenuBar.CustomMenuItem item : deductionValue.getItems()) {
            if (item != null) {
                getCheckedResults(item, result);
            }
        }
    }

    private void getCheckedResults(CustomMenuBar.CustomMenuItem item, Map<Integer, String> result) {
        for (CustomMenuBar.CustomMenuItem child : item.getChildren()) {
            if (child.isChecked()) {
                result.put(child.getMenuItem().getId(), child.getMenuItem().getCaption());
            }
        }
    }

    protected void loadFromAndTo(SummarySelection selection) {
        try {
            List<String[]> list = new ArrayList<>();

            String fd = selection.getDataSelectionDTO().getFromPeriod();
            String td = selection.getDataSelectionDTO().getToPeriod();
            Date from = ARMUtils.getInstance().getDateFormatter().parse(fd);
            Date to = ARMUtils.getInstance().getDateFormatter().parse(td);
            Calendar fromPeriod = Calendar.getInstance();
            Calendar toPeriod = Calendar.getInstance();
            fromPeriod.setTime(from);
            toPeriod.setTime(to);
            int startMonth = fromPeriod.get(Calendar.MONTH);
            int endMonth = toPeriod.get(Calendar.MONTH);
            int startYear = fromPeriod.get(Calendar.YEAR);
            int endYear = toPeriod.get(Calendar.YEAR);
            while (fromPeriod.before(toPeriod) || (startYear == endYear && startMonth <= endMonth)) {
                String item = ARMUtils.getInstance().getDateFormatter().format(fromPeriod.getTime());
                String caption = VariableConstants.getMONTHS()[fromPeriod.get(Calendar.MONTH)] + " " + fromPeriod.get(Calendar.YEAR);
                fromDate.addItem(item);
                fromDate.setItemCaption(item, caption);
                toDate.addItem(item);
                toDate.setItemCaption(item, caption);
                list.add(new String[]{item, caption});
                fromPeriod.set(Calendar.MONTH, fromPeriod.get(Calendar.MONTH) + 1);
                startMonth = fromPeriod.get(Calendar.MONTH);
                endMonth = toPeriod.get(Calendar.MONTH);
                startYear = fromPeriod.get(Calendar.YEAR);
                endYear = toPeriod.get(Calendar.YEAR);
            }
            fromDate.select(list.get(0)[0]);
            toDate.select(list.get(list.size() - 1)[0]);
            fromDate.setImmediate(true);
            toDate.setImmediate(true);
        } catch (Exception ex) {
            LOGGER.error("Error in loadFromAndTo :", ex);
        }
    }

    @UiHandler("frequencyDdlb")
    public void frequencyChangeLogic(Property.ValueChangeEvent event) {
        if ((int) frequencyDdlb.getValue() != 0) {
            List<String> finalList = CommonUtils.getToAndFromByFrequency(frequencyDdlb.getItemCaption(frequencyDdlb.getValue()), summarySelection.getDataSelectionDTO().getFromDate(), summarySelection.getDataSelectionDTO().getToDate());
            if (!finalList.isEmpty()) {
                loadFrequency(finalList);
            }
        } else {
            fromDate.select(0);
            toDate.select(0);
        }
    }

    private void loadFrequency(List<String> finalList) {
        fromDate.removeAllItems();
        toDate.removeAllItems();
        for (int i = 0; i < finalList.size(); i++) {
            fromDate.addItem(finalList.get(i));
            toDate.addItem(finalList.get(i));
        }
        fromDate.select(finalList.get(1));
        toDate.select(finalList.get(finalList.size() - 1));
        defaultToDate = finalList.get(finalList.size() - 1);
    }

    private void configureSecurity() {
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(sessionDTO.getUserId());
        Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, "Adjustment Summary" + ARMUtils.COMMA_CHAR + "Landing screen");
        if (functionHM.get(CommonConstant.RESET) != null && !(functionHM.get(CommonConstant.RESET)).isFunctionFlag()) {
            reset.setVisible(false);
        } else {
            reset.setVisible(true);
        }
        if (functionHM.get("generate") != null && !(functionHM.get("generate")).isFunctionFlag()) {
            generate.setVisible(false);
        } else {
            generate.setVisible(true);
        }
        if (functionHM.get("expandbtn") != null && !(functionHM.get("expandbtn")).isFunctionFlag()) {
            adjustmentResults.getExpandbtn().setVisible(false);
        } else {
            adjustmentResults.getExpandbtn().setVisible(true);
        }
        configureSecurityForButton(functionHM);
    }

    @Override
    public boolean equals(Object summaryobj) {
        return super.equals(summaryobj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    private void writeObject(ObjectOutputStream summaryobj) throws IOException {
        summaryobj.defaultWriteObject();
    }

    private void readObject(ObjectInputStream summaryobj) throws IOException, ClassNotFoundException {
        summaryobj.defaultReadObject();
    }
}
