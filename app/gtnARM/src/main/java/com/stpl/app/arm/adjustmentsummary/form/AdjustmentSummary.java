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
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.utils.CommonUtils;
import com.stpl.app.utils.VariableConstants;
import static com.stpl.app.utils.VariableConstants.MONTHS;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import static com.stpl.ifs.ui.util.AbstractNotificationUtils.LOGGER;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.constants.ARMMessages;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.VerticalLayout;
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.customcombobox.CustomComboBox;
import org.asi.ui.custommenubar.CustomMenuBar;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Asha.Ravi
 */
public class AdjustmentSummary extends VerticalLayout implements View, DefaultFocusable {

    private static final Logger LOGGER = Logger.getLogger(AdjustmentSummary.class);
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
     * “Adjustment Configuration UI. Example are the following: Pipeline Accrual
     * Demand Accrual Pipeline Inventory True-Up Demand Payment True-Up Demand
     * Accrual Reforecast Inflation Adjustment Distribution Fees Etc.
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
     * generate the list view. When the “Deduction Level = Deduction”, the
     * Deduction Values DDLB will show the possible values as “Rebate Schedule
     * ID – Rebate Schedule Name”. In the list views it will also display as
     * “Rebate Schedule ID – Rebate Schedule Name”. Yes, at least one must be
     * selected	Format: Alphanumeric Justification: Left *
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
     * SummaryLogic logic has all depended methods for all summary logic classes
     */
    SummaryLogic logic = new SummaryLogic();
    /**
     * SummarySelection is used to bind and pass through out the class
     * hierarchies
     */
    SummarySelection selection;

    protected CustomMenuBar.CustomMenuItem selecetedAdjustment;

    protected CustomMenuBar.CustomMenuItem selecedVariablesMenuItem;

    AdjustmentSummaryResults adjustmentResults;

    String defaultToDate;
    SessionDTO sessionDTO;

    public AdjustmentSummary(SummarySelection selection, SessionDTO sessionDTO) throws Exception {
        this.selection = selection;
        this.sessionDTO = sessionDTO;
        init();
    }

    private void init() throws Exception {
        loadInitialSelection();
        adjustmentResults = new AdjustmentSummaryResults(logic, selection);
        adjustmentResults.getResults();
        adjustmentResults.init();
        addComponent(Clara.create(getClass().getResourceAsStream("/bussinessprocess/adjustmentSummaryDetails.xml"), this));
        setWidth("100%");
        addComponent(adjustmentResults);
        configureFields();
        List<String> finalList = CommonUtils.getToAndFromByFrequency(ARMUtils.frequencyVarables.MONTHLY.toString(), selection.getDataSelectionDTO().getFromDate(), selection.getDataSelectionDTO().getToDate());
        loadFrequency(finalList);
        attachStatusDdlbListener();
        configureSecurity();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            selection.setSelectedAdjustmentType(adjustmentValues.get(NumericConstants.TWO));
            selection.setSelectedAdjustmentTypeValues(adjustmentValues.get(1));
        } else {
            isAdjustNotSelected = true;
        }
        List<List> deductionValues = CommonUtils.getSelectedVariables(selecedVariablesMenuItem, Boolean.FALSE);
        if (!deductionValues.isEmpty()) {
            selection.setDeductionVariableIds(deductionValues.get(NumericConstants.TWO));
            selection.setDeductionVariablesValues(deductionValues.get(1));
            selection.setDeductionVariablesName(deductionValues.get(0));
        } else {
            isDeductionNotSelected = true;
        }
        selection.setSummary_deductionLevel((int) deductionlevelDdlb.getValue());
        selection.setSummary_deductionLevelDes(String.valueOf(deductionlevelDdlb.getItemCaption(deductionlevelDdlb.getValue())));
        String deductions = StringUtils.EMPTY;
        List<String> listSize = selection.getDeductionVariablesName();
        List finalDeductionList = new ArrayList();
        if (!listSize.isEmpty()) {
            for (int i = 0; i < listSize.size(); i++) {
                String[] arr = listSize.get(i).split("~");
                finalDeductionList.add(arr[0]);
            }
        }
        deductions = "'" + StringUtils.join(finalDeductionList.toArray(), "','") + "'";
        selection.setSummary_deductionValues(deductions);

        if (isAdjustNotSelected || isDeductionNotSelected || checkMandatoryFields() || VariableConstants.SELECT_ONE.equals(String.valueOf(fromDate.getValue()))) {
            CustomNotification.getErrorNotification(ARMMessages.getGenerateMessageName_001(), ARMMessages.getGenerateMessage_MsgId_002());
            return false;
        }
        selection.setFrequency(frequencyDdlb.getItemCaption(frequencyDdlb.getValue()));
        selection.setSummary_FrequencyName(selection.getFrequency());
        selection.setFromDate(String.valueOf(fromDate.getValue()));
        if (toDate.getValue() != null && !VariableConstants.SELECT_ONE.equals(String.valueOf(toDate.getValue()))) {
            selection.setToDate(String.valueOf(toDate.getValue()));
        } else {
            selection.setToDate(defaultToDate);
        }
        LOGGER.debug("String.valueOf(fromDate.getValue()---->>" + String.valueOf(fromDate.getValue()));
        LOGGER.debug("String.valueOf(toDate.getValue()---->>" + String.valueOf(toDate.getValue()));
        List<List> statusValues = CommonUtils.getSelectedVariables(adjustmentResults.statusMenuItem, Boolean.FALSE);
        selection.setStatus(statusValues.get(1).toString().toUpperCase());
        selection.setSummary_StatusID(statusValues.get(NumericConstants.TWO).toString());
        return true;
    }

    private boolean checkMandatoryFields() {
        if (Integer.valueOf(frequencyDdlb.getValue().toString()) == 0 || Integer.valueOf(deductionlevelDdlb.getValue().toString()) == 0
                || selection.getSelectedAdjustmentTypeValues().size() < 1 || selection.getDeductionVariableIds().size() < 1
                || VariableConstants.SELECT_ONE.equals(selection.getStatus())) {
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
                        break;
                    case "save":
                        break;
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
                        LOGGER.error(ex);
                    }
                }

                @Override
                public void noMethod() {
                }
            }.getConfirmationMessage(ARMMessages.getResetMessageName_001(), ARMMessages.getResetMessageID002());
        } catch (Exception e) {
            LOGGER.error(e);
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
            LOGGER.error(e);
        }
    }

    /**
     * Method to get Values for adjustment type
     *
     * @return String List
     */
    protected List<String> getCheckedValues() {
        if (selecetedAdjustment != null && selecetedAdjustment.getSize() > 0) {
            List<String> result = new ArrayList<String>();
            List<CustomMenuBar.CustomMenuItem> items = selecetedAdjustment.getChildren();
            for (Iterator<CustomMenuBar.CustomMenuItem> it = items.iterator(); it.hasNext();) {
                CustomMenuBar.CustomMenuItem customMenuItem1 = it.next();
                if (customMenuItem1.isChecked()) {
                    result.add(customMenuItem1.getMenuItem().getCaption());
                }
            }
            return result;
        }
        return Collections.EMPTY_LIST;
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
        return Collections.EMPTY_MAP;
    }

    protected void loadFromAndTo(SummarySelection selection) {
        try {
            List<String[]> list = new ArrayList<>();

            String fd = selection.getDataSelectionDTO().getFromPeriod();
            String td = selection.getDataSelectionDTO().getToPeriod();
            Date from = ARMUtils.DATE_FORMATER.parse(fd);
            Date to = ARMUtils.DATE_FORMATER.parse(td);
            Calendar fromPeriod = Calendar.getInstance();
            Calendar toPeriod = Calendar.getInstance();
            fromPeriod.setTime(from);
            toPeriod.setTime(to);
            int startMonth = fromPeriod.get(Calendar.MONTH);
            int endMonth = toPeriod.get(Calendar.MONTH);
            int startYear = fromPeriod.get(Calendar.YEAR);
            int endYear = toPeriod.get(Calendar.YEAR);
            while (fromPeriod.before(toPeriod) || (startYear == endYear && startMonth <= endMonth)) {
                String item = ARMUtils.DATE_FORMATER.format(fromPeriod.getTime());
                String caption = MONTHS[fromPeriod.get(Calendar.MONTH)] + " " + fromPeriod.get(Calendar.YEAR);
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
            LOGGER.error(ex);
        }
    }

    @UiHandler("frequencyDdlb")
    public void frequencyChangeLogic(Property.ValueChangeEvent event) {
        if ((int) frequencyDdlb.getValue() != 0) {
            List<String> finalList = CommonUtils.getToAndFromByFrequency(frequencyDdlb.getItemCaption(frequencyDdlb.getValue()), selection.getDataSelectionDTO().getFromDate(), selection.getDataSelectionDTO().getToDate());
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

    private void configureSecurity() throws Exception {
        final StplSecurity stplSecurity = new StplSecurity();
        final String userId = String.valueOf(sessionDTO.getUserId());
        Map<String, AppPermission> functionHM = stplSecurity.getBusinessFunctionPermission(userId, "Adjustment Summary" + "," + "Landing screen");
        if (functionHM.get("reset") != null && !((AppPermission) functionHM.get("reset")).isFunctionFlag()) {
            reset.setVisible(false);
        } else {
            reset.setVisible(true);
        }
        if (functionHM.get("generate") != null && !((AppPermission) functionHM.get("generate")).isFunctionFlag()) {
            generate.setVisible(false);
        } else {
            generate.setVisible(true);
        }
        if (functionHM.get("expandbtn") != null && !((AppPermission) functionHM.get("expandbtn")).isFunctionFlag()) {
            adjustmentResults.getExpandbtn().setVisible(false);
        } else {
            adjustmentResults.getExpandbtn().setVisible(true);
        }
        if (functionHM.get("collapseBtn") != null && !((AppPermission) functionHM.get("collapseBtn")).isFunctionFlag()) {
            adjustmentResults.getCollapseBtn().setVisible(false);
        } else {
            adjustmentResults.getCollapseBtn().setVisible(true);
        }
        if (functionHM.get("BBExport") != null && !((AppPermission) functionHM.get("BBExport")).isFunctionFlag()) {
            adjustmentResults.getBBExport().setVisible(false);
        } else {
            adjustmentResults.getBBExport().setVisible(true);
        }
    }

}
