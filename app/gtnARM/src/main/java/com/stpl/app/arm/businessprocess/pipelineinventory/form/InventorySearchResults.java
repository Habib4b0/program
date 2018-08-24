/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.pipelineinventory.form;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractSearchResults;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.pipelineinventory.dto.InventoryFieldFactory;
import com.stpl.app.arm.businessprocess.pipelineinventory.logic.InventoryLogic;
import com.stpl.app.arm.supercode.CommonUI;
import com.stpl.app.arm.supercode.ExcelInterface;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.utils.ExcelUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.stpl.ifs.util.constants.ARMConstants;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.asi.container.ExtContainer;
import org.asi.container.ExtTreeContainer;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Porchelvi.Gunasekara
 */
public class InventorySearchResults extends AbstractSearchResults {

    public static final Logger LOGGER = LoggerFactory.getLogger(InventorySearchResults.class);
    private ExtTreeContainer<AdjustmentDTO> resultBeanContainerInv = new ExtTreeContainer<>(
            AdjustmentDTO.class, ExtContainer.DataStructureMode.LIST);
    private String[] righttablesingleheaders = {"Total Inventory", "Weeks on Hand", "Units Per Retail"};
    private Object[] doubleheadercolumns = {"rate"};
    private Object[] columns = {"month"};
    private Object[] leftColumns = {"branditemno"};
    private Object[] singleheader = {"dateType", "price", "exclusionDetails"};

    private String[] doubleheader = {"Managed Care Base"};

    public InventorySearchResults(InventoryLogic logic, AbstractSelectionDTO selection) {
        super(logic, selection);
    }

    @Override
    public void setVisibleColumnsAndHeaders() {
        configureOnInventorySearchResults();

        getTableLogic().setContainerDataSource(resultBeanContainerInv);
        leftTable = table.getLeftFreezeAsTable();
        rightTable = table.getRightFreezeAsTable();
        rightTable.setImmediate(true);
        leftTable.setImmediate(true);

        table.setDoubleHeaderVisible(false);
        leftTable.setDoubleHeaderVisibleColumns(columns);

        leftTable.setDoubleHeaderColumnHeaders("");

        leftTable.setVisibleColumns(leftColumns);

        leftTable.setColumnHeaders("Product");

        rightTable.setVisibleColumns(singleheader);

        rightTable.setColumnHeaders(righttablesingleheaders);

        for (Object propertyId : rightTable.getVisibleColumns()) {
            rightTable.setColumnAlignment(propertyId, ExtCustomTable.Align.CENTER);

        }
        for (Object propertyId : rightTable.getDoubleHeaderVisibleColumns()) {
            rightTable.setColumnAlignment(propertyId, ExtCustomTable.Align.CENTER);

        }
        for (Object propertyId : leftTable.getVisibleColumns()) {
            leftTable.setColumnAlignment(propertyId, ExtCustomTable.Align.LEFT);
        }
        rightTable.setDoubleHeaderVisibleColumns(doubleheadercolumns);
        rightTable.setDoubleHeaderColumnHeaders(doubleheader);
        rightTable.setDoubleHeaderColumnWidth("rate", NumericConstants.FIVE_HUNDRED);

        leftTable.setColumnCheckBox("checkRecord", true, false);
        abstractSearchContent.setWidth("100%");
    }

    private void configureOnInventorySearchResults() {
        panelCaption.setCaption("Inventory Results");
        customerProductView.setVisible(false);
        valueDdlb.setVisible(false);
        cancelOverride.setVisible(false);
        cpLabel.setVisible(false);

        bbExport.setPrimaryStyleName("link");
        bbExport.setIcon(ARMUtils.EXCEL_EXPORT_IMAGE, "Excel Export");
    }

    @Override
    public void setExcelVisibleColumn() {
        Map properties = new HashMap();
        List<Object> header = getSummaryLogic().generateInventoryHeader(selection);
        List rightSingleVisibleColumn = (ArrayList) header.get(0);
        List rightSingleVisibleHeader = (ArrayList) header.get(1);
        for (Object variableColumn : rightSingleVisibleColumn) {
            properties.put(variableColumn, String.class);
        }
        getExcelContainer().setColumnProperties(properties);
        getExcelContainer().setRecordHeader(rightSingleVisibleColumn);
        List rightSingleVisibleColumn1 = new ArrayList(rightSingleVisibleColumn);
        rightSingleVisibleColumn1.add(0, "month");
        rightSingleVisibleHeader.add(0, "Product");
        getExcelTable().setVisibleColumns(rightSingleVisibleColumn1.toArray());
        getExcelTable().setColumnHeaders(Arrays.copyOf((rightSingleVisibleHeader).toArray(), (rightSingleVisibleHeader).size(), String[].class));
        setConverter(getExcelTable(), getExcelTable().getVisibleColumns());
    }

    @Override
    protected void configureRightTable() {
        LOGGER.debug("inside configureRightTable Method");
    }

    @Override
    protected boolean calculateLogic() {
        LOGGER.debug("Inside calculate ButtonClick Btn");
        try {
            Object[] orderedArgs = {getSelection().getProjectionMasterSid(), getSelection().getInventoryDetails(), getSelection().getInventoryOptionGroup(),
                getSelection().getInventoryreserveDate(), getSelection().getPrice(), "1", getSelection().getSessionDTO().getUserId(), getSelection().getSessionDTO().getSessionId()};
            getSummaryLogic().getInventoryResults(orderedArgs);
        } catch (Exception e) {
            LOGGER.error("Error in calculateLogic :", e);
        }
        return false;
    }

    @Override
    protected void customerProductValueChange() {
        LOGGER.debug("inside customerProductValueChange Method");

    }

    public void generateButtonLogic(AbstractSelectionDTO selection) {
        List<Object> header = getSummaryLogic().generateInventoryHeader(selection);

        List rightSingleVisibleColumn = (ArrayList) header.get(0);
        Map properties = new HashMap();
        Object[] variableColumns = new String[rightSingleVisibleColumn.size()];
        variableColumns = rightSingleVisibleColumn.toArray(variableColumns);
        for (int i = 0; i < variableColumns.length; i++) {
            properties.put(variableColumns[i], String.class);
        }

        rightTable.setContainerDataSource(getTableLogic().getContainerDataSource());
        resultBeanContainerInv.setColumnProperties(properties);
        resultBeanContainerInv.setRecordHeader(rightSingleVisibleColumn);
        rightTable.setVisibleColumns(rightSingleVisibleColumn.toArray());
        rightTable.setColumnHeaders(Arrays.copyOf(((List) header.get(1)).toArray(), ((List) header.get(1)).size(), String[].class));
        for (Object propertyId : rightTable.getVisibleColumns()) {
            rightTable.setColumnAlignment(propertyId, ExtCustomTable.Align.RIGHT);
        }

        InventoryFieldFactory inventoryFieldFactory = new InventoryFieldFactory(getSummaryLogic(), selection);
        rightTable.setEditable(true);
        rightTable.setTableFieldFactory(inventoryFieldFactory);
        List<String> keys = new ArrayList<>(Arrays.asList(new String[]{"sales_projectionId", "inventoryDetails", "customerOrCG", "reserveDate", "price", "generatePriceOverride", "userId", "sessionId", "inventoryList"}));
        Object[] orderedArgs = {selection.getDataSelectionDTO().getProjectionId(), selection.getInventoryDetails(), selection.getInventoryOptionGroup(), selection.getInventoryreserveDate(), selection.getPrice(), "0",
            selection.getSessionDTO().getUserId(), selection.getSessionDTO().getSessionId()};
        List<Object> values = new ArrayList<>(Arrays.asList(orderedArgs));
        if ("Customer".equals(selection.getInventoryOptionGroup())) {
            values.add(selection.getCustomerList());
        } else {
            values.add(selection.getCustomerGroupList());
        }

        if (getSummaryLogic().checkProcedureCallAllowed(selection, keys, values)) {
            getSummaryLogic().getInventoryResults(orderedArgs);
        }
        setConverter(rightTable, rightTable.getVisibleColumns());
        getTableLogic().loadSetData(Boolean.FALSE);
    }

    @Override
    protected boolean setRespectiveLevelFileterValue(String levelValue, int levelNo
    ) {
        getSelection().setSaleslevelFilterValue(levelValue);
        return true;
    }

    @Override
    public InventoryLogic getSummaryLogic() {
        return (InventoryLogic) super.getSummaryLogic();
    }

    @Override
    public ExcelInterface getExcelLogic() {
        return (ExcelInterface) getSummaryLogic();
    }

    @Override
    public Object[] getExcelHierarchy() {
        return new Object[]{"B", "I"};
    }

    @Override
    public List getExcelExportVisibleColumn() {
        return getSelection().getSummarycolumnList();
    }

    @Override
    public String getExcelFileName() {
        return "Inventory";
    }

    @Override
    public boolean getisFixedColumns() {
        return Boolean.TRUE;
    }

    @Override
    public int getInterval() {
        return NumericConstants.SEVEN;
    }

    @Override
    public int discountColumnNeeded() {
        return NumericConstants.TWO;
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
    protected void excelExportLogic() {
        try {
            tableLayout.addComponent(getExcelTable());
            getExcelTable().setContainerDataSource(getExcelContainer());
            getExcelTable().setRefresh(Boolean.FALSE);
            getExcelTable().setVisible(Boolean.FALSE);
            setExcelVisibleColumn();
            List list = getExcelLogic().getExcelResultList(getSelection());
            List<Object> listData = new ArrayList<>();
            listData.add(getisFixedColumns());
            listData.add(Boolean.FALSE);
            listData.add(Boolean.FALSE);
            listData.add(getInterval());

            ExcelUtils.setExcelData(list, getExcelHierarchy(), getExcelExportVisibleColumn(), getExcelContainer(), discountColumnNeeded(), ARMConstants.getPipelineInventoryTrueUp(), listData);
            ((CommonUI) getUI()).setExcelFlag(Boolean.TRUE);
            ExtCustomTableHolder customTableHolder = new ExtCustomTableHolder(getExcelTable());
            ExcelExport export = new ExcelExport(customTableHolder, getExcelFileName(), getExcelFileName(), getExcelFileName() + ".xls", false);
            export.setUseTableFormatPropertyValue(Boolean.TRUE);
            export.export();
            getExcelContainer().removeAllItems();
            tableLayout.removeComponent(getExcelTable());
        } catch (Exception ex) {
            LOGGER.error("Error in excelExportLogic :" + ex);
        }
    }

    @Override
    protected void loadLevelFilterValueDdlb(String levelValue, int levelNo) {
        LOGGER.debug("Inside loadLevelFilterValueDdlb Method");

    }

    @Override
    protected void valueDdlbValueChange(int masterSids) {
        LOGGER.debug("Inside valueDdlbValueChange Method");

    }

    @Override
    protected boolean getIsDemandSreen() {
        return Boolean.FALSE;
    }

    /**
     * Method is used to check the column is formatted with numeric two decimal
     * format
     *
     * @param column
     * @return
     */
    @Override
    protected boolean isNumericTwoDecimalFormat(String column) {
        return column.contains("weeksOnHand") || column.contains("unitsPerRetail");
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
