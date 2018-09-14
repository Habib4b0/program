package com.stpl.app.arm.businessprocess.pipelineaccrual.form;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractSearchResults;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
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
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author srithar
 */
public class SalesSearchResults extends AbstractSearchResults {

    private Object[] salesResultsDoubleheadercolumns = {"ratePeriod"};
    private Object[] salesResultsColumns = {"month"};
    private Object[] salesResultsLeftColumns = {"group"};
    public static final Logger LOGGER = LoggerFactory.getLogger(SalesSearchResults.class);
    private static final String PRODUCT = "Product";

    public SalesSearchResults(SalesLogic logic, AbstractSelectionDTO selection) {
        super(logic, selection);
    }

    @Override
    public void setVisibleColumnsAndHeaders() {
        Map salesProperties = new HashMap();
        Object[] salesVariableColumns = VariableConstants.getVariableSalesVisibleColumn();
        for (int i = 0; i < salesVariableColumns.length; i++) {
            salesProperties.put(salesVariableColumns[i], String.class);
        }

        configureOnSalesSearchResults();
        getTableLogic().setContainerDataSource(resultBeanContainer);
        leftTable = table.getLeftFreezeAsTable();
        rightTable = table.getRightFreezeAsTable();
        boolean immediate = true;
        rightTable.setImmediate(immediate);
        leftTable.setImmediate(immediate);

        table.setDoubleHeaderVisible(!immediate);
        leftTable.setDoubleHeaderVisibleColumns(salesResultsColumns);
        leftTable.setDoubleHeaderColumnHeaders(StringUtils.EMPTY);
        leftTable.setVisibleColumns(salesResultsLeftColumns);
        leftTable.setColumnHeaders(PRODUCT);
        rightTable.setVisibleColumns(salesResultsDoubleheadercolumns);
        rightTable.setColumnHeaders(StringUtils.EMPTY);

        for (Object salesPropertyId : rightTable.getVisibleColumns()) {
            rightTable.setColumnAlignment(salesPropertyId, ExtCustomTable.Align.RIGHT);

        }
        for (Object salesPropertyId : leftTable.getVisibleColumns()) {
            leftTable.setColumnAlignment(salesPropertyId, ExtCustomTable.Align.LEFT);
        }
        leftTable.setColumnCheckBox("checkRecord", true, false);
        resultBeanContainer.setColumnProperties(salesProperties);
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
        List salesRightSingleVisibleColumn = (ArrayList) header.get(0);
        List salesRightSingleVisibleHeader = (ArrayList) header.get(1);
        for (Object variableColumn : salesRightSingleVisibleColumn) {
            properties.put(variableColumn, String.class);
        }
        getExcelContainer().setColumnProperties(properties);
        getExcelContainer().setRecordHeader(salesRightSingleVisibleColumn);
        List rightSingleVisibleColumn1 = new ArrayList(salesRightSingleVisibleColumn);
        rightSingleVisibleColumn1.add(0, "group");
        salesRightSingleVisibleHeader.add(0, PRODUCT);
        getExcelTable().setVisibleColumns(rightSingleVisibleColumn1.toArray());
        getExcelTable().setColumnHeaders(Arrays.copyOf((salesRightSingleVisibleHeader).toArray(), (salesRightSingleVisibleHeader).size(), String[].class));
        setConverter(getExcelTable(), getExcelTable().getVisibleColumns());
    }

    public void generateButtonLogic(PipelineAccrualSelectionDTO selection, boolean isEditButtonClick) {
        LOGGER.debug("Inside generate ButtonClick Btn{}", selection.getSalesVariables().size());
        List<Object> header = getSummaryLogic().generateHeader(selection);
        List salesRightSingleVisibleColumn = (ArrayList) header.get(0);
        Map salesProperties = new HashMap();
        Object[] variableColumns = VariableConstants.getVariableSalesVisibleColumn();
        for (int i = 0; i < variableColumns.length; i++) {
            salesProperties.put(variableColumns[i], String.class);
        }
        if (!selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL) && !isEditButtonClick) {
            salesProcedureCall(selection);
        }
        rightTable.setContainerDataSource(getTableLogic().getContainerDataSource());
        resultBeanContainer.setColumnProperties(salesProperties);
        resultBeanContainer.setRecordHeader(salesRightSingleVisibleColumn);
        resultBeanContainer.setIndexable(true);
        rightTable.setVisibleColumns(salesRightSingleVisibleColumn.toArray());
        rightTable.setColumnHeaders(Arrays.copyOf(((List) header.get(1)).toArray(), ((List) header.get(1)).size(), String[].class));
        for (Object propertyId : rightTable.getVisibleColumns()) {
            rightTable.setColumnAlignment(propertyId, ExtCustomTable.Align.RIGHT);
        }
        SalesFieldFactory salesFieldFactory = new SalesFieldFactory(getSummaryLogic(), selection);
        rightTable.setEditable(true);
        rightTable.setTableFieldFactory(salesFieldFactory);
        selection.setUserId(selection.getSessionDTO().getUserId());
        selection.setSessionId(selection.getSessionDTO().getSessionId());
        getTableLogic().loadSetData(false);
    }

    @Override
    protected void configureRightTable() {
        LOGGER.debug("Inside configureRightTable Method");
    }

    @Override
    protected boolean calculateLogic() {
        LOGGER.debug("Inside Sales calculate ButtonClick Btn");
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
        LOGGER.debug("inside Sales customerProductValueChange MEthod");

    }

    private void configureFieldsOnViewMode() {
        calculateBtn.setEnabled(false);
    }

    public void salesProcedureCall(PipelineAccrualSelectionDTO selection) {
        Object[] procedureArgs = {selection.getProjectionMasterSid(), selection.getDateType(), selection.getPrice(), "0",
            selection.getSessionDTO().getUserId(), selection.getSessionDTO().getSessionId()};
        getSummaryLogic().getSalesResults(procedureArgs);
    }

    @Override
    public SalesLogic getSummaryLogic() {
        LOGGER.debug("inside Sales SummaryLogic");
        return (SalesLogic) super.getSummaryLogic();
    }

    @Override
    protected boolean setRespectiveLevelFileterValue(String levelValue, int levelNo) {
        LOGGER.debug("inside Sales setRespectiveLevelFileterValue");
        selection.setSaleslevelFilterValue(levelValue);
        return true;
    }

    @Override
    public ExcelInterface getExcelLogic() {
        LOGGER.debug("inside Sales getExcelLogic");
        return getSummaryLogic();
    }

    @Override
    public Object[] getExcelHierarchy() {
        LOGGER.debug("inside Sales getExcelHierarchy");
        return new Object[]{"B", "I"};
    }

    @Override
    public List getExcelExportVisibleColumn() {
        LOGGER.debug("inside Sales getExcelExportVisibleColumn");
        return Arrays.asList(VariableConstants.getVariableSalesVisibleColumn());
    }

    @Override
    public String getExcelFileName() {
        LOGGER.debug("inside Sales getExcelFileName");
        return "Sales";
    }

    @Override
    public boolean getisFixedColumns() {
        LOGGER.debug("inside Sales getisFixedColumns");
        return false;
    }

    @Override
    public int getInterval() {
        LOGGER.debug("inside Sales getInterval");
        return 0;
    }

    @Override
    public int discountColumnNeeded() {
        LOGGER.debug("inside Sales discountColumnNeeded");
        return 0;
    }

    @Override
    public Map<Integer, String> getHierarchy() {
        LOGGER.debug("inside Sales getHierarchy");
        return getSelection().getSaleshierarchy();
    }

    @Override
    public void setRespectiveHierarchy(String viewType) {
        LOGGER.debug("inside Sales setRespectiveHierarchy");
        getSelection().setSaleshierarchy(ARMUtils.getLevelAndLevelFilter(viewType));
    }

    @Override
    public boolean getisDeductionCustomer() {
        LOGGER.debug("inside Sales getisDeductionCustomer");
        return false;
    }

    @Override
    protected void valueDdlbValueChange(int masterSids) {
        LOGGER.debug("Inside Sales valueDdlbValueChange Method");

    }

    @Override
    protected void loadLevelFilterValueDdlb(String levelValue, int levelNo) {
        LOGGER.debug("Inside Sales valueDdlbValueChange Method");

    }

    @Override
    protected boolean getIsDemandSreen() {
        LOGGER.debug("inside Sales getIsDemandSreen");
        return false;
    }

    @Override
    public boolean equals(Object salesSearchResobj) {
        return super.equals(salesSearchResobj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    private void writeObject(ObjectOutputStream salesSearchResobj) throws IOException {
        salesSearchResobj.defaultWriteObject();
    }

    private void readObject(ObjectInputStream salesSearchResobj) throws IOException, ClassNotFoundException {
        salesSearchResobj.defaultReadObject();
    }
}
