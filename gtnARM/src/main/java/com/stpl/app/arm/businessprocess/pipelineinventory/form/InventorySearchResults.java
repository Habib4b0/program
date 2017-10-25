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
import com.vaadin.ui.ExtCustomTable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.asi.container.ExtContainer;
import org.asi.container.ExtTreeContainer;
import org.jboss.logging.Logger;

/**
 *
 * @author Porchelvi.Gunasekara
 */
public class InventorySearchResults extends AbstractSearchResults {
    
    public static final Logger LOGGER = Logger.getLogger(InventorySearchResults.class);
    private ExtTreeContainer<AdjustmentDTO> resultBeanContainer = new ExtTreeContainer<AdjustmentDTO>(
            AdjustmentDTO.class, ExtContainer.DataStructureMode.LIST);
    String[] righttablesingleheaders = {"Total Inventory", "Weeks on Hand", "Units Per Retail"};
    Object[] doubleheadercolumns = {"rate"};
    Object[] columns = {"month"};
    Object[] leftColumns = {"brand_item_no"};
    Object[] singleheader = {"dateType", "price", "exclusionDetails"};

    String[] doubleheader = {"Managed Care Base"};

    public InventorySearchResults(InventoryLogic logic, AbstractSelectionDTO selection) {
        super(logic, selection);
    }

    @Override
    public void setVisibleColumnsAndHeaders() {
        configureOnInventorySearchResults();

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

        BBExport.setPrimaryStyleName("link");
        BBExport.setIcon(ARMUtils.EXCEL_EXPORT_IMAGE, "Excel Export");
    }

    @Override
    public void setExcelVisibleColumn() {
        Map properties = new HashMap();
        List<Object> header = getLogic().generateInventoryHeader(selection);
        List right_singleVisibleColumn = (ArrayList) header.get(0);
        List right_singleVisibleHeader = (ArrayList) header.get(1);
        for (Object variableColumn : right_singleVisibleColumn) {
            properties.put(variableColumn, String.class);
        }
        getExcelContainer().setColumnProperties(properties);
        getExcelContainer().setRecordHeader(right_singleVisibleColumn);
        List right_singleVisibleColumn1 = new ArrayList(right_singleVisibleColumn);
        right_singleVisibleColumn1.add(0, "month");
        right_singleVisibleHeader.add(0, "Product");
        getExcelTable().setVisibleColumns(right_singleVisibleColumn1.toArray());
        getExcelTable().setColumnHeaders(Arrays.copyOf((right_singleVisibleHeader).toArray(), (right_singleVisibleHeader).size(), String[].class));
        setConverter(getExcelTable(), getExcelTable().getVisibleColumns());
    }

    @Override
    protected void configureRightTable() {
    }

    @Override
    protected boolean calculateLogic() {
        LOGGER.debug("Inside calculate ButtonClick Btn");
        try {
            Object[] orderedArgs = {getSelection().getProjectionMasterSid(), getSelection().getInventory_Details(), getSelection().getInventoryOptionGroup(),
                getSelection().getInventory_reserveDate(), getSelection().getPrice(), "1", getSelection().getSessionDTO().getUserId(), getSelection().getSessionDTO().getSessionId()};
            getLogic().getInventoryResults(orderedArgs);
        } catch (Exception e) {
             LOGGER.error(e);
        }
        return false;
    }

    @Override
    protected void customerProductValueChange() {

    }

    public void generateButtonLogic(AbstractSelectionDTO selection) {
        List<Object> header = getLogic().generateInventoryHeader(selection);

        List right_singleVisibleColumn = (ArrayList) header.get(0);
        Map properties = new HashMap();
        Object[] variableColumns = new String[right_singleVisibleColumn.size()];
        variableColumns = right_singleVisibleColumn.toArray(variableColumns);
        for (int i = 0; i < variableColumns.length; i++) {
            properties.put(variableColumns[i], String.class);
        }

        rightTable.setContainerDataSource(getTableLogic().getContainerDataSource());
        resultBeanContainer.setColumnProperties(properties);
        resultBeanContainer.setRecordHeader(right_singleVisibleColumn);
        rightTable.setVisibleColumns(right_singleVisibleColumn.toArray());
        rightTable.setColumnHeaders(Arrays.copyOf(((List) header.get(1)).toArray(), ((List) header.get(1)).size(), String[].class));
        for (Object propertyId : rightTable.getVisibleColumns()) {
            rightTable.setColumnAlignment(propertyId, ExtCustomTable.Align.RIGHT);
        }

        InventoryFieldFactory inventoryFieldFactory = new InventoryFieldFactory(getLogic(), selection);
        rightTable.setEditable(true);
        rightTable.setTableFieldFactory(inventoryFieldFactory);
        List<String> keys = new ArrayList<>(Arrays.asList(new String[]{"sales_projectionId", "inventoryDetails", "customerOrCG", "reserveDate", "price", "generatePriceOverride", "userId", "sessionId", "inventoryList"}));
        Object[] orderedArgs = {selection.getDataSelectionDTO().getProjectionId(), selection.getInventory_Details(), selection.getInventoryOptionGroup(), selection.getInventory_reserveDate(), selection.getPrice(), "0",
            selection.getSessionDTO().getUserId(), selection.getSessionDTO().getSessionId()};
        List<Object> values = new ArrayList<>(Arrays.asList(orderedArgs));
        if ("Customer".equals(selection.getInventoryOptionGroup())) {
            values.add(selection.getCustomerList());
        } else {
            values.add(selection.getCustomerGroupList());
        }

        if (getLogic().checkProcedureCallAllowed(selection, keys, values)) {
            getLogic().getInventoryResults(orderedArgs);
        }
        setConverter(rightTable, rightTable.getVisibleColumns());
        getTableLogic().loadSetData(Boolean.FALSE);
    }

    @Override
    protected boolean setRespectiveLevelFileterValue(String levelValue, int levelNo
    ) {
        getSelection().setSales_levelFilterValue(levelValue);
        return true;
    }

    @Override
    public InventoryLogic getLogic() {
        return (InventoryLogic) super.getLogic();
    }

    @Override
    public ExcelInterface getExcelLogic() {
        return (ExcelInterface) getLogic();
    }

    public Object[] getExcelHierarchy() {
        return new Object[]{"B", "I"};
    }

    @Override
    public List getExcelExportVisibleColumn() {
        return getSelection().getSummary_columnList();
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
        return getSelection().getSales_hierarchy();
    }

    @Override
    public void setRespectiveHierarchy(String viewType) {
        getSelection().setSales_hierarchy(ARMUtils.getLevelAndLevelFilter(viewType));
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
            ExcelUtils.setExcelData(list, getExcelHierarchy(), getExcelExportVisibleColumn(), getExcelContainer(), getisFixedColumns(), getInterval(), discountColumnNeeded(), Boolean.FALSE, Boolean.TRUE, Boolean.FALSE);
            ((CommonUI) getUI()).setExcelFlag(Boolean.TRUE);
            ExcelExport export = new ExcelExport(new ExtCustomTableHolder(getExcelTable()), getExcelFileName(), getExcelFileName(), getExcelFileName() + ".xls", false);
            export.setUseTableFormatPropertyValue(Boolean.TRUE);
            export.export();
            getExcelContainer().removeAllItems();
            tableLayout.removeComponent(getExcelTable());
        } catch (Exception ex) {
             LOGGER.error(ex);
        }
    }

    @Override
    protected void loadLevelFilterValueDdlb(String levelValue, int levelNo) {

    }

    @Override
    protected void valueDdlbValueChange(int masterSids) {

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

}
