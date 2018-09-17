/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.balancesummaryreport.ui;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

import com.stpl.app.arm.balancesummaryreport.logic.AbstractBSummaryLogic;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractSummarySearchResults;
import com.stpl.app.arm.businessprocess.commontemplates.SummarySelection;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.arm.utils.CommonConstant;
import com.stpl.app.utils.ExcelUtils;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.util.constants.ARMConstants;
import com.stpl.ifs.util.constants.GlobalConstants;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.ui.Label;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Locale;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtCustomTable;

/**
 *
 * @author Srithar.Raju
 */
public abstract class AbstractBalanceSummaryResutls extends AbstractSummarySearchResults {

    private String[] righttablesingleheaders = {StringUtils.EMPTY};
    private Object[] doubleheadercolumns = {"rate"};

    private Object[] singleheader = {"dateType"};

    private String[] doubleheader = {StringUtils.EMPTY};
    @UiField("valueLabel")
    private Label valueLabel;
    private SummarySelection summarySelection;

    private boolean isValueChange;
    private String leftHeader = CommonConstant.CUSTOMER;
    private AbstractBSummaryLogic abstractBSummaryLogic;
    public static final Logger ABS_BAL_SUM_LOGGER = LoggerFactory.getLogger(AbstractBalanceSummaryResutls.class);

    public AbstractBalanceSummaryResutls(AbstractBSummaryLogic logic, SummarySelection selection) {
        super(logic, selection);
        this.summarySelection = selection;
        this.abstractBSummaryLogic = logic;
    }

    public void init() {
        configureFields();
        loadSelection();
    }

    protected void loadSelection() {
        calculateBtn.setVisible(false);
        summarySelection.setStatus("Pending");
        summarySelection.setSummaryDeductionView(String.valueOf(customerProductView.getValue()));
        summarySelection.setSummaryLevel(ARMUtils.getADJSummaryLevel(String.valueOf(customerProductView.getValue())));
    }

    @Override
    public void setVisibleColumnsAndHeaders() {
        Object[] list = getSummaryLogic().getLeftTableHeaders();
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
        rightTable.setDoubleHeaderMap(configureRightDoubleHeaderMap());
        abstractSearchContent.setWidth("100%");
    }

    @Override
    protected void customerProductValueChange() {
        if (isValueChange) {
            String viewType = String.valueOf(customerProductView.getValue());
            leftHeader = viewType.equals(ARMConstants.getDeductionProduct()) ? "Product" : CommonConstant.CUSTOMER;
            leftTable.setColumnHeaders(leftHeader);
            setRespectiveHierarchy(viewType);
            configureLevelAndLevelFilter();
            loadSelection();
            getTableLogic().loadSetData(false);
        }
    }

    @Override
    protected boolean calculateLogic() {
        return false;
    }

    @Override
    protected boolean setRespectiveLevelFileterValue(String levelValue, int levelNo) {
        summarySelection.setSummarylevelFilterNo(levelNo);
        summarySelection.setSummarylevelFilterValue(levelValue);
        summarySelection.setSummaryvalueSid(0);
        return true;
    }

    @Override
    public Object[] getExcelHierarchy() {
        Map<Integer, String> summaryHierarchy = getHierarchy();
        Object[] value = new Object[summaryHierarchy.size()];
        for (int i = 0; i < summaryHierarchy.size(); i++) {
            String bsrSummaryVal = summaryHierarchy.get(i + 1);
            if (bsrSummaryVal.equalsIgnoreCase(ARMUtils.levelVariablesVarables.DEDUCTION.toString())) {
                bsrSummaryVal = getSelection().getSummarydeductionLevelDes().toUpperCase(Locale.ENGLISH);
            }
            value[i] = ARMUtils.getLevelExcelQueryName(bsrSummaryVal);
        }
        getSelection().setExcelHierarchy(value);
        return value;
    }

    @Override
    public List getExcelExportVisibleColumn() {
        ABS_BAL_SUM_LOGGER.debug("inside getExcelExport");
        return getSelection().getExcelVisibleColumn();
    }

    @Override
    public boolean getisFixedColumns() {
        ABS_BAL_SUM_LOGGER.debug("inside getisFixedColumns");
        return true;
    }

    @Override
    public int getInterval() {
        ABS_BAL_SUM_LOGGER.debug("inside getInterval");
        return 1;
    }

    @Override
    public int discountColumnNeeded() {
        ABS_BAL_SUM_LOGGER.debug("inside discountColumnNeeded");
        return 2;
    }

    @Override
    public boolean getisDeductionCustomer() {
        ABS_BAL_SUM_LOGGER.debug("inside getisDeductionCustomer");
        return false;
    }

    class BsrResultsCustomNotification extends AbstractNotificationUtils {

        private String bsrResultsbuttonName;

        public BsrResultsCustomNotification() {
            /*
        THE DEFAULT CONSTRUCTOR
             */
        }

        @Override
        public void noMethod() {
            ABS_BAL_SUM_LOGGER.debug("Inside the CustomNotification Listener NO Method");
        }

        @Override
        public void yesMethod() {
            ABS_BAL_SUM_LOGGER.debug("buttonName :{}", bsrResultsbuttonName);
            if (null != bsrResultsbuttonName) {
                switch (bsrResultsbuttonName) {
                    case "reset":
                        break;
                    case "save":
                        break;
                    default:
                }
            }
        }

        public void setButtonName(String buttonName) {
            this.bsrResultsbuttonName = buttonName;
        }

    }

    @Override
    public void configureFields() {
        super.configureFields();
        valueLabel.setCaption("Value:");
        bbExport.setPrimaryStyleName("link");
        bbExport.setIcon(ARMUtils.EXCEL_EXPORT_IMAGE, "Excel Export");
        cancelOverride.setVisible(false);
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
        isValueChange = true;
        tableLogic.loadSetData(false);

    }

    private void loadTableHeaderForGenerate() {
        Object[] list = getSummaryLogic().getLeftTableHeaders();
        leftTable.setVisibleColumns((Object[]) (list[0]));
        String viewType = String.valueOf(customerProductView.getValue());
        leftHeader = viewType.equals(ARMConstants.getDeductionProduct()) ? "Product" : CommonConstant.CUSTOMER;
        leftTable.setColumnHeaders(leftHeader);
        leftTable.setDoubleHeaderVisibleColumns((Object[]) (list[2]));
        leftTable.setDoubleHeaderColumnHeaders((String[]) list[3]);
        leftTable.setDoubleHeaderMap((Map) list[4]);
        configureRightTable();
    }

    @Override
    public void configureRightTable() {
        table.constructRightFreeze(true);
        List rightList = getSummaryLogic().getRightTableHeaders(summarySelection);
        Map<Object, Class> properties = new HashMap<>();
        if (!rightList.isEmpty()) {
            for (Object obj : (List) rightList.get(0)) {
                properties.put(obj, String.class);
            }
        }
        rightTable = table.getRightFreezeAsTable();
        rightTable.setImmediate(true);
        rightTable.setDoubleHeaderVisible(true);
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

    }

    /**
     * Field ddlb.
     *
     * @param event the event
     */
    @UiHandler("valueDdlb")
    public void valueDdlb(Property.ValueChangeEvent event) {
        ABS_BAL_SUM_LOGGER.debug("valueDdlb value change listener starts");
    }

    @Override
    public Map<Integer, String> getHierarchy() {
        return getSelection().getSummeryhierarchy();
    }

    @Override
    public void setRespectiveHierarchy(String viewType) {
        summarySelection.setSummeryhierarchy(ARMUtils.getLevelAndLevelFilterMultiPeriod(viewType));
    }

    @Override
    protected void valueDdlbValueChange(int masterSid) {
        if (isLevelFilterValueDdlbEnable()) {
            getSelection().setSummaryvalueSid(masterSid);
            tableLogic.loadSetData(false);
        }
    }

    @Override
    protected void callExcelCustomization(List list, Object[] excelHierarchy) throws IllegalAccessException, InvocationTargetException {
        List excelMapList = abstractBSummaryLogic.bsExcelCustomizer(list, getExcelHierarchy(), getExcelExportVisibleColumn(), getisFixedColumns(), getInterval(), discountColumnNeeded(), getSelection());
        ExcelUtils.setExcelHierarchyData(excelMapList, getExcelContainer());
    }

    @Override
    protected void loadLevelFilterValueDdlb(String levelValue, int levelNo) {
        getLevelFilterValueDdlb().removeAllItems();
        getLevelFilterValueDdlb().addItem(0);
        getLevelFilterValueDdlb().setItemCaption(0, GlobalConstants.getSelectOne());
        if (!levelValue.equals(GlobalConstants.getSelectOne())) {
            Map<Integer, String> levelVal = getSummaryLogic().getLevelFilterValueData(levelValue, summarySelection.getProjectionMasterSid(), summarySelection.getSummarydeductionLevel(), summarySelection.getSummarydeductionValues());
            for (Map.Entry<Integer, String> entry : levelVal.entrySet()) {
                getLevelFilterValueDdlb().addItem(entry.getKey());
                getLevelFilterValueDdlb().setItemCaption(entry.getKey(), entry.getValue());
            }
        }
        getLevelFilterValueDdlb().setValue(0);
    }

    @Override
    protected boolean getIsDemandSreen() {
        return false;
    }

    protected Map<Object, Object[]> configureRightDoubleHeaderMap() {
        Map<Object, Object[]> headerMap = new HashMap<>();
        headerMap.put("rate", singleheader);
        return headerMap;
    }

    @Override
    public AbstractBSummaryLogic getSummaryLogic() {
        return (AbstractBSummaryLogic) super.getSummaryLogic();
    }

    @Override
    protected boolean isPercentageColumn2Decimal(String column) {
        return false;
    }

    @Override
    protected boolean isPercentageColumn3Decimal(String column) {
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public SummarySelection getSummarySelection() {
        return summarySelection;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}
