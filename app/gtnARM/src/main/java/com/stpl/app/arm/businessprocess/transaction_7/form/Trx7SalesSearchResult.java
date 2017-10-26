package com.stpl.app.arm.businessprocess.transaction_7.form;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractSearchResults;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.commontemplates.AdjustmentTableLogic;
import com.stpl.app.arm.businessprocess.transaction_7.dto.Trx7SelectionDTO;
import com.stpl.app.arm.businessprocess.transaction_7.dto.Trx7SalesFieldFactory;
import com.stpl.app.arm.businessprocess.transaction_7.logic.Trx7SalesLogic;
import com.stpl.app.arm.supercode.ExcelInterface;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.utils.VariableConstants;
import com.vaadin.ui.ExtCustomTable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jboss.logging.Logger;

/**
 *
 * @author srithar
 */
public class Trx7SalesSearchResult extends AbstractSearchResults {

    String[] righttablesingleheaders = {"Total Units", "Total Sales", "Excluded Units", "Excluded Sales", "Net Units"};
    Object[] doubleheadercolumns = {"ratePeriod"};
    Object[] columns = {"month"};
    Object[] leftColumns = {"group"};
    Object[] singleheader = {"dateType", "price", "exclusionDetails", "rateBasis", "rateFrequency"};
    List<String> rightColumnsList = new ArrayList<>();
    String[] doubleheader = {"Managed Care Base"};
    String variables;
    public static final Logger LOGGER = Logger.getLogger(Trx7SalesSearchResult.class);

    public Trx7SalesSearchResult(Trx7SalesLogic logic, AbstractSelectionDTO selection) {
        super(logic, selection);
    }

    @Override
    public void setVisibleColumnsAndHeaders() {
        Map properties = new HashMap();
        Object[] variableColumns = VariableConstants.VARIABLE_SALES_VISIBLE_COLUMN;
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
    }

    private void configureOnSalesSearchResults() {
        customerProductView.setVisible(false);
        cpLabel.setVisible(false);
        valueDdlb.setVisible(false);
        cancelOverride.setVisible(false);
        BBExport.setPrimaryStyleName("link");
        BBExport.setIcon(ARMUtils.EXCEL_EXPORT_IMAGE, "Excel Export");
    }

    @Override
    public void setExcelVisibleColumn() {
        Map properties = new HashMap();
        List<Object> header = getLogic().generateHeader((Trx7SelectionDTO) selection);
        List right_singleVisibleColumn = (ArrayList) header.get(0);
        List right_singleVisibleHeader = (ArrayList) header.get(1);
        for (Object variableColumn : right_singleVisibleColumn) {
            properties.put(variableColumn, String.class);
        }
        getExcelContainer().setColumnProperties(properties);
        getExcelContainer().setRecordHeader(right_singleVisibleColumn);
        List right_singleVisibleColumn1 = new ArrayList(right_singleVisibleColumn);
        right_singleVisibleColumn1.add(0, "group");
        right_singleVisibleHeader.add(0, "Product");
        getExcelTable().setVisibleColumns(right_singleVisibleColumn1.toArray());
        getExcelTable().setColumnHeaders(Arrays.copyOf((right_singleVisibleHeader).toArray(), (right_singleVisibleHeader).size(), String[].class));
        setConverter(getExcelTable(), getExcelTable().getVisibleColumns());
    }

    public void generateButtonLogic(Trx7SelectionDTO selection, boolean isEditButtonClick) {
        LOGGER.debug("Inside generate ButtonClick Btn" + selection.getSales_variables().size());
        List<Object> header = getLogic().generateHeader(selection);
        List right_singleVisibleColumn = (ArrayList) header.get(0);
        Map properties = new HashMap();
        Object[] variableColumns = VariableConstants.VARIABLE_SALES_VISIBLE_COLUMN;
        for (int i = 0; i < variableColumns.length; i++) {
            properties.put(variableColumns[i], String.class);
        }
        if (!selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL) && !isEditButtonClick) {
            salesProcedureCall(selection);
        }
        rightTable.setContainerDataSource(getTableLogic().getContainerDataSource());
        resultBeanContainer.setColumnProperties(properties);
        resultBeanContainer.setRecordHeader(right_singleVisibleColumn);
        rightTable.setVisibleColumns(right_singleVisibleColumn.toArray());
        rightTable.setColumnHeaders(Arrays.copyOf(((List) header.get(1)).toArray(), ((List) header.get(1)).size(), String[].class));
        for (Object propertyId : rightTable.getVisibleColumns()) {
            rightTable.setColumnAlignment(propertyId, ExtCustomTable.Align.RIGHT);
        }
        Trx7SalesFieldFactory salesFieldFactory = new Trx7SalesFieldFactory(getLogic(), selection.getProjectionMasterSid(), selection.getSessionDTO().getSessionId(), selection);
        rightTable.setEditable(true);
        rightTable.setTableFieldFactory(salesFieldFactory);
        selection.setUserId(selection.getSessionDTO().getUserId());
        selection.setSessionId(selection.getSessionDTO().getSessionId());
        setConverter(rightTable, rightTable.getVisibleColumns());
        getTableLogic().loadSetData(Boolean.FALSE);
    }

    @Override
    protected void configureRightTable() {
    }

    @Override
    protected boolean calculateLogic() {
        LOGGER.debug("Inside calculate ButtonClick Btn");
        try {
            Object[] orderedArgs = {selection.getProjectionMasterSid(), selection.getDateType(), selection.getPrice(), "1", selection.getSessionDTO().getUserId(), selection.getSessionDTO().getSessionId()};
            getLogic().getSalesResults(orderedArgs);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return false;
    }

    @Override
    protected void customerProductValueChange() {

    }

    private void configureFieldsOnViewMode() {
        calculateBtn.setEnabled(false);
        cancelOverride.setEnabled(false);
    }

    public void salesProcedureCall(Trx7SelectionDTO selection) {
        Object[] orderedArgs = {selection.getProjectionMasterSid(), selection.getDateType(), selection.getPrice(), "0", selection.getSessionDTO().getUserId(), selection.getSessionDTO().getSessionId()};
        getLogic().getSalesResults(orderedArgs);
    }

    @Override
    public AdjustmentTableLogic getTableLogic() {
        return super.getTableLogic();
    }

    @Override
    public Trx7SalesLogic getLogic() {
        return (Trx7SalesLogic) super.getLogic();
    }

    @Override
    protected boolean setRespectiveLevelFileterValue(String levelValue, int levelNo) {
        selection.setSales_levelFilterValue(levelValue);
        return true;
    }

    @Override
    public ExcelInterface getExcelLogic() {
        return getLogic();
    }

    public Object[] getExcelHierarchy() {
        return new Object[]{"C", "B", "I"};
    }

    @Override
    public List getExcelExportVisibleColumn() {
        return Arrays.asList(VariableConstants.VARIABLE_SALES_VISIBLE_COLUMN);
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
        return getSelection().getSales_hierarchy();
    }

    @Override
    public void setRespectiveHierarchy(String viewType) {
        getSelection().setSales_hierarchy(ARMUtils.getTYrx7LevelAndLevelFilter(viewType + "Sales"));
    }

    @Override
    public boolean getisDeductionCustomer() {
        return Boolean.FALSE;
    }

    @Override
    protected void valueDdlbValueChange(int masterSids) {

    }

    @Override
    protected void loadLevelFilterValueDdlb(String levelValue, int levelNo) {

    }

    @Override
    protected boolean getIsDemandSreen() {
        return Boolean.FALSE;
    }

}
