/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.balancesummaryreport.ui;

import com.stpl.app.arm.adjustmentsummary.form.AdjustmentSummaryResults;
import com.stpl.app.arm.balancesummaryreport.logic.AbstractBSummaryLogic;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractSummarySearchResults;
import com.stpl.app.arm.businessprocess.commontemplates.SummarySelection;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.utils.ExcelUtils;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.util.constants.ARMConstants;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.vaadin.data.Property;
import com.vaadin.ui.ExtCustomTable;
import com.vaadin.ui.Label;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import org.asi.ui.customtextfield.CustomTextField;
import org.jboss.logging.Logger;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Srithar.Raju
 */
public abstract class AbstractBalanceSummaryResutls extends AbstractSummarySearchResults {

    String[] righttablesingleheaders = {"Actual Payments", "Period Balance", "Payment Ratio"};
    Object[] doubleheadercolumns = {"rate"};

    Object[] rightcolumns = {"month"};
    Object[] singleheader = {"dateType", "price", "exclusionDetails"};

    String[] doubleheader = {"Cumulative Balance"};
    @UiField("valueLabel")
    private Label valueLabel;
    SummarySelection selection;
    private final Label labelStatus = new Label("Calculation Profile");

    protected final CustomTextField calculationProfile = new CustomTextField();
  
    boolean isValueChange;
    String leftHeader = "Customer";
    AbstractBSummaryLogic logic;
    /**
     * The Constant LOGGER.
     */
    protected final Logger LOGGER = Logger.getLogger(getClass());

    public AbstractBalanceSummaryResutls(AbstractBSummaryLogic logic, SummarySelection selection) {
        super(logic, selection);
        this.selection = selection;
        this.logic = logic;
    }

    public void init() {
        configureFields();
        loadSelection();
    }

    protected void loadSelection() {
        calculateBtn.setVisible(Boolean.FALSE);

        selection.setStatus("Pending");
        selection.setSummaryLevel(ARMUtils.getADJ_SummaryLevel(String.valueOf(customerProductView.getValue())));
    }

    
    @Override
    public void setVisibleColumnsAndHeaders() {
        Object[] list = getLogic().getLeftTableHeaders();
        leftTable.setVisibleColumns((Object[]) (list[0]));
        leftTable.setColumnHeaders((String[]) (list[1]));
        leftTable.setDoubleHeaderVisibleColumns((Object[]) (list[2]));
        leftTable.setDoubleHeaderColumnHeaders((String[]) list[3]);
        leftTable.setDoubleHeaderMap((Map) list[4]);
        for (Object propertyId : rightTable.getVisibleColumns()) {
            rightTable.setColumnAlignment(propertyId, ExtCustomTable.Align.CENTER);
        }
        for (Object propertyId : rightTable.getDoubleHeaderVisibleColumns()) {
            rightTable.setDoubleHeaderColumnAlignment(propertyId, ExtCustomTable.Align.CENTER);
        }
        for (Object propertyId : leftTable.getVisibleColumns()) {
            leftTable.setColumnAlignment(propertyId, ExtCustomTable.Align.LEFT);
        }
        for (Object propertyId : rightTable.getVisibleColumns()) {
            rightTable.setColumnAlignment(propertyId, ExtCustomTable.Align.RIGHT);
        }
        rightTable.setVisibleColumns(singleheader);
        rightTable.setColumnHeaders(righttablesingleheaders);
        rightTable.setDoubleHeaderVisibleColumns(doubleheadercolumns);
        rightTable.setDoubleHeaderColumnHeaders(doubleheader);
        rightTable.setDoubleHeaderMap(configure_RightDoubleHeaderMap());
        abstractSearchContent.setWidth("100%");
    }

    @Override
    protected void customerProductValueChange() {
        if (isValueChange) {
            String viewType = String.valueOf(customerProductView.getValue());
            leftHeader = viewType.equals(ARMConstants.getDeductionProduct()) ? "Product" : "Customer";
            leftTable.setColumnHeaders(leftHeader);
            setRespectiveHierarchy(viewType);
            configureLevelAndLevelFilter();
            loadSelection();
            getTableLogic().loadSetData(Boolean.FALSE);
        }
    }

    @Override
    protected boolean calculateLogic() {
        return false;
    }

    @Override
    protected boolean setRespectiveLevelFileterValue(String levelValue, int levelNo) {
        selection.setSummary_levelFilterNo(levelNo);
        selection.setSummary_levelFilterValue(levelValue);
        selection.setSummary_valueSid(0);
        return true;
    }

    @Override
    public Object[] getExcelHierarchy() {
        Map<Integer, String> hierarchy = getHierarchy();
        Object[] value = new Object[hierarchy.size()];
        for (int i = 0; i < hierarchy.size(); i++) {
            String val = hierarchy.get(i + 1);
            if (val.equalsIgnoreCase(ARMUtils.levelVariablesVarables.DEDUCTION.toString())) {
                val = getSelection().getSummary_deductionLevelDes().toUpperCase();
            }
            value[i] = ARMUtils.getLevelExcelQueryName(val);
        }
        getSelection().setExcelHierarchy(value);
        return value;
    }

    @Override
    public List getExcelExportVisibleColumn() {
        return getSelection().getExcelVisibleColumn();
    }


    @Override
    public boolean getisFixedColumns() {
        return Boolean.TRUE;
    }

    @Override
    public int getInterval() {
        return 1;
    }

    @Override
    public int discountColumnNeeded() {
        return 2;
    }

    @Override
    public boolean getisDeductionCustomer() {
        return Boolean.FALSE;
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

    @Override
    public void configureFields() {
        super.configureFields();
        valueLabel.setCaption("Value:");
        BBExport.setPrimaryStyleName("link");
        BBExport.setIcon(ARMUtils.EXCEL_EXPORT_IMAGE, "Excel Export");
        cancelOverride.setVisible(false);
        searchResultsGrid.addComponent(labelStatus);
        searchResultsGrid.addComponent(calculationProfile);
        calculationProfile.setImmediate(true);
        calculationProfile.addStyleName("menuleft");
        calculationProfile.addStyleName("searchText");
    }

    @Override
    protected void loadLevelValues() {
        super.loadLevelValues();
        customerProductView.addItem(ARMConstants.getCustomerDedection());
        customerProductView.setValue(ARMConstants.getDeductionProduct());
    }

    public void generateBtnLogic() {

        loadTableHeaderForGenerate();
        loadSelection();
        isValueChange = Boolean.TRUE;
        tableLogic.loadSetData(Boolean.FALSE);

    }

    private void loadTableHeaderForGenerate() {
        Object[] list = getLogic().getLeftTableHeaders();
        leftTable.setVisibleColumns((Object[]) (list[0]));
        String viewType = String.valueOf(customerProductView.getValue());
        leftHeader = viewType.equals(ARMConstants.getDeductionProduct()) ? "Product" : "Customer";
        leftTable.setColumnHeaders(leftHeader);
        leftTable.setDoubleHeaderVisibleColumns((Object[]) (list[2]));
        leftTable.setDoubleHeaderColumnHeaders((String[]) list[3]);
        leftTable.setDoubleHeaderMap((Map) list[4]);
        configureRightTable();
    }

    @Override
    public void configureRightTable() {
        try {
            table.constructRightFreeze(true);
            List rightList = getLogic().getRightTableHeaders(selection);
            Map<Object, Class> properties = new HashMap<>();
            if (!rightList.isEmpty()) {
                for (Object obj : (List) rightList.get(0)) {
                    properties.put(obj, String.class);
                }
            }
            rightTable = table.getRightFreezeAsTable();
            rightTable.setImmediate(true);
            rightTable.setDoubleHeaderVisible(Boolean.TRUE);
            rightTable.setContainerDataSource(resultBeanContainer);
            resultBeanContainer.setColumnProperties(properties);
            rightTable.setVisibleColumns(((List) rightList.get(0)).toArray());
            resultBeanContainer.setRecordHeader((List) rightList.get(0));
            rightTable.setColumnHeaders(Arrays.copyOf(((List) rightList.get(1)).toArray(), ((List) rightList.get(1)).toArray().length, String[].class));
            rightTable.setDoubleHeaderVisibleColumns(((List) rightList.get(2)).toArray());
            rightTable.setDoubleHeaderColumnHeaders(Arrays.copyOf(((List) rightList.get(3)).toArray(), ((List) rightList.get(3)).toArray().length, String[].class));
            rightTable.setDoubleHeaderMap((Map) rightList.get(4));
            for (Object propertyId : rightTable.getVisibleColumns()) {
                rightTable.setColumnAlignment(propertyId, ExtCustomTable.Align.RIGHT);
            }
             for (Object propertyId : rightTable.getDoubleHeaderVisibleColumns()) {
              rightTable.setDoubleHeaderColumnAlignment(propertyId, ExtCustomTable.Align.CENTER);
            }

            setConverter(rightTable, rightTable.getVisibleColumns());

        } catch (ParseException ex) {
            java.util.logging.Logger.getLogger(AdjustmentSummaryResults.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Field ddlb.
     *
     * @param event the event
     */
    @UiHandler("valueDdlb")
    public void valueDdlb(Property.ValueChangeEvent event) {
        LOGGER.debug("valueDdlb value change listener starts");
    }

    @Override
    public Map<Integer, String> getHierarchy() {
        return getSelection().getSummery_hierarchy();
    }

    @Override
    public void setRespectiveHierarchy(String viewType) {
        selection.setSummery_hierarchy(ARMUtils.getLevelAndLevelFilterMultiPeriod(viewType));
    }

    @Override
    protected void valueDdlbValueChange(int masterSid) {
        if (isLevelFilterValueDdlbEnable()) {
            getSelection().setSummary_valueSid(masterSid);
            tableLogic.loadSetData(Boolean.FALSE);
        }
    }

    @Override
    protected void callExcelCustomization(List list, Object[] excelHierarchy) throws IllegalAccessException, InvocationTargetException {
        List excelMapList = logic.bsExcelCustomizer(list, getExcelHierarchy(), getExcelExportVisibleColumn(), getisFixedColumns(), getInterval(), discountColumnNeeded(), getSelection());
        ExcelUtils.setExcelHierarchyData(excelMapList, getExcelContainer());
    }

    @Override
    protected void loadLevelFilterValueDdlb(String levelValue, int levelNo) {
        getLevelFilterValueDdlb().removeAllItems();
        getLevelFilterValueDdlb().addItem(0);
        getLevelFilterValueDdlb().setItemCaption(0, GlobalConstants.getSelectOne());
        if (!levelValue.equals(GlobalConstants.getSelectOne())) {
            Map<Integer, String> levelVal = getLogic().getLevelFilterValueData(levelValue, selection.getProjectionMasterSid(), selection.getSummary_deductionLevel(), selection.getSummary_deductionValues());
            for (Map.Entry<Integer, String> entry : levelVal.entrySet()) {
                getLevelFilterValueDdlb().addItem(entry.getKey());
                getLevelFilterValueDdlb().setItemCaption(entry.getKey(), entry.getValue());
            }
        }
        getLevelFilterValueDdlb().setValue(0);
    }

    @Override
    protected boolean getIsDemandSreen() {
        return Boolean.FALSE;
    }

    protected Map<Object, Object[]> configure_RightDoubleHeaderMap() {
        Map<Object, Object[]> headerMap = new HashMap<>();
        headerMap.put("rate", singleheader);
        return headerMap;
    }
    
     @Override
    public AbstractBSummaryLogic getLogic() {
        return (AbstractBSummaryLogic) super.getLogic();
    }
    
    protected boolean isPercentageColumn_2_Decimal(String column) {
       return column.contains("PaymentRatio");
    }
    
    protected boolean isPercentageColumn_3_Decimal(String column) {
        return false;
    }
}
