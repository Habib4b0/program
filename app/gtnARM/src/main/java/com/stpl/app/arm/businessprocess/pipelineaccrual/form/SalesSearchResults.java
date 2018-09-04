package com.stpl.app.arm.businessprocess.pipelineaccrual.form;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractSearchResults;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.commontemplates.AdjustmentTableLogic;
import com.stpl.app.arm.businessprocess.pipelineaccrual.dto.PipelineAccrualSelectionDTO;
import com.stpl.app.arm.businessprocess.pipelineaccrual.dto.SalesFieldFactory;
import com.stpl.app.arm.businessprocess.pipelineaccrual.logic.SalesLogic;
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
public class SalesSearchResults extends AbstractSearchResults {

    private Object[] doubleheadercolumns = {"ratePeriod"};
    private Object[] columns = {"month"};
    private Object[] leftColumns = {"group"};
    public static final Logger LOGGER = LoggerFactory.getLogger(SalesSearchResults.class);

    public SalesSearchResults(SalesLogic logic, AbstractSelectionDTO selection) {
        super(logic, selection);
    }

    @Override
    public void setVisibleColumnsAndHeaders() {
        Map properties = new HashMap();
        Object[] variableColumns = VariableConstants.getVariableSalesVisibleColumn();
        for (int i = 0; i < variableColumns.length; i++) {
            properties.put(variableColumns[i], String.class);
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
        setConverter(rightTable, VariableConstants.getVariableSalesVisibleColumn());
    }

    private void configureOnSalesSearchResults() {
        cancelOverride.setVisible(false);
        customerProductView.setVisible(false);
        cpLabel.setVisible(false);
        valueDdlb.setVisible(false);
        bbExport.setPrimaryStyleName("link");
        bbExport.setIcon(ARMUtils.EXCEL_EXPORT_IMAGE, "Excel Export");
    }

    @Override
    public void setExcelVisibleColumn() {
        Map properties = new HashMap();
        List<Object> header = getSummaryLogic().generateHeader((PipelineAccrualSelectionDTO) selection);
        List rightSingleVisibleColumn = (ArrayList) header.get(0);
        List rightSingleVisibleHeader = (ArrayList) header.get(1);
        for (Object variableColumn : rightSingleVisibleColumn) {
            properties.put(variableColumn, String.class);
        }
        getExcelContainer().setColumnProperties(properties);
        getExcelContainer().setRecordHeader(rightSingleVisibleColumn);
        List rightSingleVisibleColumn1 = new ArrayList(rightSingleVisibleColumn);
        rightSingleVisibleColumn1.add(0, "group");
        rightSingleVisibleHeader.add(0, "Product");
        getExcelTable().setVisibleColumns(rightSingleVisibleColumn1.toArray());
        getExcelTable().setColumnHeaders(Arrays.copyOf((rightSingleVisibleHeader).toArray(), (rightSingleVisibleHeader).size(), String[].class));
        setConverter(getExcelTable(), getExcelTable().getVisibleColumns());
    }

    public void generateButtonLogic(PipelineAccrualSelectionDTO selection, boolean isEditButtonClick) {
        LOGGER.debug("Inside generate ButtonClick Btn{}", selection.getSalesVariables().size());
        List<Object> header = getSummaryLogic().generateHeader(selection);
        List rightSingleVisibleColumn = (ArrayList) header.get(0);
        Map properties = new HashMap();
        Object[] variableColumns = VariableConstants.getVariableSalesVisibleColumn();
        for (int i = 0; i < variableColumns.length; i++) {
            properties.put(variableColumns[i], String.class);
        }
        if (!selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL) && !isEditButtonClick) {
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
        SalesFieldFactory salesFieldFactory = new SalesFieldFactory(getSummaryLogic(), selection);
        rightTable.setEditable(true);
        rightTable.setTableFieldFactory(salesFieldFactory);
        selection.setUserId(selection.getSessionDTO().getUserId());
        selection.setSessionId(selection.getSessionDTO().getSessionId());
        getTableLogic().loadSetData(Boolean.FALSE);
    }

    @Override
    protected void configureRightTable() {
        LOGGER.debug("Inside configureRightTable Method");
    }

    @Override
    protected boolean calculateLogic() {
        LOGGER.debug("Inside calculate ButtonClick Btn");
        try {
            Object[] orderedArgs = {selection.getProjectionMasterSid(), selection.getDateType(), selection.getPrice(), "1", selection.getSessionDTO().getUserId(), selection.getSessionDTO().getSessionId()};
            getSummaryLogic().getSalesResults(orderedArgs);
        } catch (Exception e) {
            LOGGER.error("Error in calculateLogic :", e);
        }
        return false;
    }

    @Override
    protected void customerProductValueChange() {
        LOGGER.debug("inside customerProductValueChange MEthod");

    }

    private void configureFieldsOnViewMode() {
        calculateBtn.setEnabled(false);
    }

    public void salesProcedureCall(PipelineAccrualSelectionDTO selection) {
        Object[] orderedArgs = {selection.getProjectionMasterSid(), selection.getDateType(), selection.getPrice(), "0",
            selection.getSessionDTO().getUserId(), selection.getSessionDTO().getSessionId()};
        getSummaryLogic().getSalesResults(orderedArgs);
    }

    @Override
    public AdjustmentTableLogic getTableLogic() {
        return super.getTableLogic();
    }

    @Override
    public SalesLogic getSummaryLogic() {
        return (SalesLogic) super.getSummaryLogic();
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
        return new Object[]{"B", "I"};
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
        return Boolean.FALSE;
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
        getSelection().setSaleshierarchy(ARMUtils.getLevelAndLevelFilter(viewType));
    }

    @Override
    public boolean getisDeductionCustomer() {
        return Boolean.FALSE;
    }

    @Override
    protected void valueDdlbValueChange(int masterSids) {
        LOGGER.debug("Inside valueDdlbValueChange Method");

    }

    @Override
    protected void loadLevelFilterValueDdlb(String levelValue, int levelNo) {
        LOGGER.debug("Inside valueDdlbValueChange Method");

    }

    @Override
    protected boolean getIsDemandSreen() {
        return Boolean.FALSE;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
    }
}
