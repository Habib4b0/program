package com.stpl.app.arm.businessprocess.transaction7.form;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractSearchResults;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.transaction7.dto.Trx7SelectionDTO;
import com.stpl.app.arm.businessprocess.transaction7.dto.Trx7SalesFieldFactory;
import com.stpl.app.arm.businessprocess.transaction7.logic.Trx7SalesLogic;
import com.stpl.app.arm.supercode.ExcelInterface;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.utils.VariableConstants;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author srithar
 */
public class Trx7SalesSearchResult extends AbstractSearchResults {

    private Object[] doubleheadercolumns = {"ratePeriod"};
    private Object[] columns = {"month"};
    private Object[] leftColumns = {"group"};
    public static final Logger TR7_LOGGER = LoggerFactory.getLogger(Trx7SalesSearchResult.class);

    public Trx7SalesSearchResult(Trx7SalesLogic logic, AbstractSelectionDTO selection) {
        super(logic, selection);
    }

    @Override
    public void setVisibleColumnsAndHeaders() {
        Map properties = new HashMap();
        String[] variableColumns = VariableConstants.getVariableSalesVisibleColumn();
        for (String variableColumn : variableColumns) {
            properties.put(variableColumn, String.class);
        }

        configureOnSalesSearchResults();
        getTableLogic().setContainerDataSource(resultBeanContainer);
        leftTable = table.getLeftFreezeAsTable();
        rightTable = table.getRightFreezeAsTable();
        rightTable.setImmediate(true);
        leftTable.setImmediate(true);

        table.setDoubleHeaderVisible(false);
        leftTable.setDoubleHeaderVisibleColumns(columns);

        leftTable.setDoubleHeaderColumnHeaders("");

        leftTable.setVisibleColumns(leftColumns);

        leftTable.setColumnHeaders("Product");

        rightTable.setVisibleColumns(doubleheadercolumns);

        rightTable.setColumnHeaders("");

        for (Object propertyId : rightTable.getVisibleColumns()) {
            rightTable.setColumnAlignment(propertyId, ExtCustomTable.Align.RIGHT);

        }
        for (Object propertyId : leftTable.getVisibleColumns()) {
            leftTable.setColumnAlignment(propertyId, ExtCustomTable.Align.LEFT);
        }
        leftTable.setColumnCheckBox("checkRecord", true, false);
        resultBeanContainer.setColumnProperties(properties);
        abstractSearchContent.setWidth("100%");
        if (ARMUtils.VIEW_SMALL.equals(selection.getSessionDTO().getAction())) {
            configureFieldsOnViewMode();
        }
    }

    private void configureOnSalesSearchResults() {
        customerProductView.setVisible(false);
        cpLabel.setVisible(false);
        valueDdlb.setVisible(false);
        cancelOverride.setVisible(false);
        bbExport.setPrimaryStyleName("link");
        bbExport.setIcon(ARMUtils.EXCEL_EXPORT_IMAGE, "Excel Export");
    }

    @Override
    public void setExcelVisibleColumn() {
        Map properties = new HashMap();
        List<Object> header = getSummaryLogic().generateHeader((Trx7SelectionDTO) selection);
        List tx7RightSingleVisibleColumn = (List) header.get(0);
        List tx7RightSingleVisibleHeader = (List) header.get(1);
        for (Object variableColumn : tx7RightSingleVisibleColumn) {
            properties.put(variableColumn, String.class);
        }
        getExcelContainer().setColumnProperties(properties);
        getExcelContainer().setRecordHeader(tx7RightSingleVisibleColumn);
        List rightSingleVisibleColumn1 = new ArrayList(tx7RightSingleVisibleColumn);
        rightSingleVisibleColumn1.add(0, "group");
        tx7RightSingleVisibleHeader.add(0, "Product");
        getExcelTable().setVisibleColumns(rightSingleVisibleColumn1.toArray());
        getExcelTable().setColumnHeaders(Arrays.copyOf((tx7RightSingleVisibleHeader).toArray(), (tx7RightSingleVisibleHeader).size(), String[].class));
        setConverter(getExcelTable(), getExcelTable().getVisibleColumns());
    }

    public void generateButtonLogic(Trx7SelectionDTO selection, boolean isEditButtonClick) {
        TR7_LOGGER.debug("Inside generate ButtonClick Btn{}", selection.getSalesVariables().size());
        List<Object> header = getSummaryLogic().generateHeader(selection);
        List rightSingleVisibleColumn = (List) header.get(0);
        Map properties = new HashMap();
        String[] variableColumns = VariableConstants.getVariableSalesVisibleColumn();
        for (String variableColumn : variableColumns) {
            properties.put(variableColumn, String.class);
        }
        if (!isEditButtonClick && !selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL)) {
            salesProcedureCall(selection);
        }
        rightTable.setContainerDataSource(getTableLogic().getContainerDataSource());
        resultBeanContainer.setColumnProperties(properties);
        resultBeanContainer.setRecordHeader(rightSingleVisibleColumn);
        resultBeanContainer.setIndexable(true);
        rightTable.setVisibleColumns(rightSingleVisibleColumn.toArray());
        rightTable.setColumnHeaders(Arrays.copyOf(((List) header.get(1)).toArray(), ((List) header.get(1)).size(), String[].class));
        for (Object propertyId : rightTable.getVisibleColumns()) {
            rightTable.setColumnAlignment(propertyId, ExtCustomTable.Align.RIGHT);
        }
        Trx7SalesFieldFactory salesFieldFactory = new Trx7SalesFieldFactory(getSummaryLogic(), selection.getProjectionMasterSid(), selection);
        rightTable.setEditable(true);
        rightTable.setTableFieldFactory(salesFieldFactory);
        selection.setUserId(selection.getSessionDTO().getUserId());
        selection.setSessionId(selection.getSessionDTO().getSessionId());
        setConverter(rightTable, rightTable.getVisibleColumns());
        getTableLogic().loadSetData(false);
    }

    @Override
    protected void configureRightTable() {
        TR7_LOGGER.debug("inside configureRightTable Method");
    }

    @Override
    protected boolean calculateLogic() {
        TR7_LOGGER.debug("Inside calculate ButtonClick Btn");
        try {
            Object[] orderedArgs = {selection.getProjectionMasterSid(), selection.getDateType(), selection.getPrice(), "1", selection.getSessionDTO().getUserId(), selection.getSessionDTO().getSessionId()};
            getSummaryLogic().getSalesResults(orderedArgs);
        } catch (Exception e) {
            TR7_LOGGER.error("Error in calculateLogic :", e);
        }
        return false;
    }

    @Override
    protected void customerProductValueChange() {
        TR7_LOGGER.debug("inside customerProductValueChange Method");

    }

    private void configureFieldsOnViewMode() {
        calculateBtn.setEnabled(false);
        cancelOverride.setEnabled(false);
    }

    public void salesProcedureCall(Trx7SelectionDTO selection) {
        Object[] orderedArgs = {selection.getProjectionMasterSid(), selection.getDateType(), selection.getPrice(), "0", selection.getSessionDTO().getUserId(), selection.getSessionDTO().getSessionId()};
        getSummaryLogic().getSalesResults(orderedArgs);
    }

    @Override
    public Trx7SalesLogic getSummaryLogic() {
        return (Trx7SalesLogic) super.getSummaryLogic();
    }

    @Override
    protected boolean setRespectiveLevelFileterValue(String levelValue, int levelNo) {
        selection.setSaleslevelFilterValue(levelValue);
        return true;
    }

    @Override
    public ExcelInterface getExcelLogic() {
        return getSummaryLogic();
    }

    @Override
    public Object[] getExcelHierarchy() {
        return new Object[]{"C", "B", "I"};
    }

    @Override
    public List getExcelExportVisibleColumn() {
        return Arrays.asList(VariableConstants.getVariableSalesVisibleColumn());
    }

    @Override
    public String getExcelFileName() {
        return "Sales";
    }

    @Override
    public boolean getisFixedColumns() {
        return false;
    }

    @Override
    public int getInterval() {
        return 0;
    }

    @Override
    public int discountColumnNeeded() {
        return 0;
    }

    @Override
    public Map<Integer, String> getHierarchy() {
        return getSelection().getSaleshierarchy();
    }

    @Override
    public void setRespectiveHierarchy(String viewType) {
        getSelection().setSaleshierarchy(ARMUtils.getInstance().getTYrx7LevelAndLevelFilter(viewType + "Sales"));
    }

    @Override
    public boolean getisDeductionCustomer() {
        return false;
    }

    @Override
    protected void valueDdlbValueChange(int masterSids) {
        TR7_LOGGER.debug("inside valueDdlbValueChange Method");

    }

    @Override
    protected void loadLevelFilterValueDdlb(String levelValue, int levelNo) {
        TR7_LOGGER.debug("inside loadLevelFilterValueDdlb Method");
    }

    @Override
    protected boolean getIsDemandSreen() {
        return false;
    }

    @Override
    public boolean equals(Object tr7Obj) {
        return super.equals(tr7Obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    private void writeObject(ObjectOutputStream tr7Obj) throws IOException {
        tr7Obj.defaultWriteObject();
    }

    private void readObject(ObjectInputStream tr7Obj) throws IOException, ClassNotFoundException {
        tr7Obj.defaultReadObject();
    }
}
