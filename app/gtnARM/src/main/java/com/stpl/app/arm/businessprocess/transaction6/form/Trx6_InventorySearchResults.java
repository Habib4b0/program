/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction6.form;

import com.stpl.addons.tableexport.ExcelExport;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.form.AbstractSearchResults;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.transaction6.dto.Trx6_SelectionDTO;
import com.stpl.app.arm.businessprocess.transaction6.logic.Trx6_InventoryLogic;
import com.stpl.app.arm.supercode.CommonUI;
import com.stpl.app.arm.supercode.ExcelInterface;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.app.utils.ExcelUtils;
import com.stpl.ifs.ui.util.AbstractNotificationUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.stpl.ifs.util.constants.ARMConstants;
import com.stpl.ifs.util.constants.ARMMessages;
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
 * @author Srithar
 */
public class Trx6_InventorySearchResults<T extends Trx6_SelectionDTO> extends AbstractSearchResults<Trx6_SelectionDTO> {

    private ExtTreeContainer<AdjustmentDTO> resultBeanContainer = new ExtTreeContainer<AdjustmentDTO>(
            AdjustmentDTO.class, ExtContainer.DataStructureMode.LIST);
    String[] righttablesingleheaders = {"Total Inventory", "Baseline Price", "Baseline Price Override"};
    Object[] doubleheadercolumns = {"rate"};
    Object[] columns = {"month"};
    Object[] leftColumns = {"brand_item_no"};
    Object[] singleheader = {"dateType", "price", "exclusionDetails"};

    String[] doubleheader = {"Managed Care Base"};
    public static final Logger LOGGER = Logger.getLogger(Trx6_InventorySearchResults.class);

    public Trx6_InventorySearchResults(Trx6_InventoryLogic logic, Trx6_SelectionDTO selection) {
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
        leftTable.setWidth(NumericConstants.HUNDRED, UNITS_PERCENTAGE);

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
            List input = new ArrayList();
            input.add(getSelection().getSessionDTO().getCurrentTableNames().get("ST_ARM_INFLATION_INVENTORY"));
            input.add(getSelection().getProjectionMasterSid());
            
            List list = getLogic().getInflationFactor(input);
            if (!list.isEmpty()) {
                AbstractNotificationUtils.getErrorNotification(ARMMessages.getCalculateMessageName(), ARMMessages.getCalculateMessageMsgId_001());
            } else {
                getLogic().updateForCalculte(input,getSelection().getSessionDTO().getCurrentTableNames().get("ST_ARM_INFLATION_INVENTORY_ADJ"));
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return false;
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

        Trx6_Inventory_FieldFactory inventoryFieldFactory = new Trx6_Inventory_FieldFactory(getLogic(), selection);
        rightTable.setEditable(true);
        rightTable.setTableFieldFactory(inventoryFieldFactory);
        Object[] orderedArgs = {selection.getDataSelectionDTO().getProjectionId(), selection.getInventory_Details(), selection.getPrice(), getSelection().getAdjustedPrice(),
            selection.getSessionDTO().getUserId(), selection.getSessionDTO().getSessionId()};
        getLogic().getInventoryResults(orderedArgs);
        setConverter(rightTable, rightTable.getVisibleColumns());
        getTableLogic().loadSetData(Boolean.FALSE);

    }

    @Override
    protected boolean setRespectiveLevelFileterValue(String levelValue, int levelNo) {
        getSelection().setSales_levelFilterValue(levelValue);
        return true;
    }

    public void inventoryProcedureCall(AbstractSelectionDTO selection, List<String> propertyList) {
        Object[] orderedArgs = {selection.getProjectionMasterSid(), selection.getInventory_Details(), selection.getInventoryOptionGroup(), selection.getInventory_reserveDate(), selection.getPrice(), "0"};
        if (selection.isFieldInput(propertyList.get(0))
                && selection.isFieldInput(propertyList.get(1)) && selection.isFieldInput(propertyList.get(NumericConstants.TWO))) {
            selection.setProcedureInputs(propertyList.get(0), selection.getInventory_Details().trim());
            selection.setProcedureInputs(propertyList.get(1), selection.getPrice().trim());
            selection.setProcedureInputs(propertyList.get(NumericConstants.TWO), selection.getInventory_reserveDate().trim());
            getLogic().getInventoryResults(orderedArgs);
        } else if (!(selection.getProcedureInputs(propertyList.get(0).trim()).equals(selection.getInventory_Details().trim()))
                && selection.getProcedureInputs(propertyList.get(1).trim()).equals(selection.getPrice().trim()) && selection.getProcedureInputs(propertyList.get(NumericConstants.TWO).trim()).equals(selection.getInventory_reserveDate().trim())) {
            selection.setProcedureInputs(propertyList.get(0), selection.getInventory_Details().trim());
            selection.setProcedureInputs(propertyList.get(1), selection.getPrice().trim());
            selection.setProcedureInputs(propertyList.get(NumericConstants.TWO), selection.getInventory_reserveDate().trim());
            getLogic().getInventoryResults(orderedArgs);
        }
    }

    @Override
    public Trx6_InventoryLogic getLogic() {
        return (Trx6_InventoryLogic) super.getLogic();
    }

    @Override
    public ExcelInterface getExcelLogic() {
        return (ExcelInterface) getLogic();
    }

    public Object[] getExcelHierarchy() {
        return new Object[]{"T", "B", "I"};
    }

    @Override
    public List getExcelExportVisibleColumn() {
        return getSelection().getInventoryHeaderList();
    }

    @Override
    public String getExcelFileName() {
        return "Inventory";
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
        getSelection().setSales_hierarchy(ARMUtils.getLevelAndLevelFilter(ARMConstants.getDeductionCustomer()));
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
            ExcelUtils.setExcelData(list, getExcelHierarchy(), getExcelExportVisibleColumn(), getExcelContainer(), getisFixedColumns(), getInterval(), discountColumnNeeded(), Boolean.FALSE, Boolean.FALSE, getIsDemandSreen());
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
    public Trx6_SelectionDTO getSelection() {
        return super.getSelection();
    }

    @Override
    protected void customerProductValueChange() {

    }

    @Override
    protected boolean getIsDemandSreen() {
        return Boolean.FALSE;
    }

    @Override
    protected boolean isUnitColumn(String column) {
        return column.contains("TotalInventory");
    }

    @Override
    protected boolean isPercentageColumn_2_Decimal(String column) {
        return column.contains("PriceChangePercent"); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Method is used to check the column is formatted currency with numeric two
     * decimal format
     *
     * @param column
     * @return
     */
    @Override
    protected boolean isNumericTwoDecimalFormat(String column) {
        return column.contains("InflationFactor");
    }
}
