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
import com.stpl.app.arm.businessprocess.transaction6.dto.Trx6SelectionDTO;
import com.stpl.app.arm.businessprocess.transaction6.logic.Trx6InventoryLogic;
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
public class Trx6InventorySearchResults<T extends Trx6SelectionDTO> extends AbstractSearchResults<Trx6SelectionDTO> {

    private ExtTreeContainer<AdjustmentDTO> resultBeanContainertRX6 = new ExtTreeContainer<>(
            AdjustmentDTO.class, ExtContainer.DataStructureMode.LIST);
    String[] righttablesingleheaders = {"Total Inventory", "Baseline Price", "Baseline Price Override"};
    Object[] doubleheadercolumns = {"rate"};
    Object[] columns = {"month"};
    Object[] leftColumns = {"branditemno"};
    Object[] singleheader = {"dateType", "price", "exclusionDetails"};

    String[] doubleheader = {"Managed Care Base"};
    public static final Logger LOGGER = Logger.getLogger(Trx6InventorySearchResults.class);

    public Trx6InventorySearchResults(Trx6InventoryLogic logic, Trx6SelectionDTO selection) {
        super(logic, selection);
    }

    @Override
    public void setVisibleColumnsAndHeaders() {
        configureOnInventorySearchResults();

        getTableLogic().setContainerDataSource(resultBeanContainertRX6);
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
        LOGGER.debug("Inside configureRightTable MEthod");
    }

    @Override
    protected boolean calculateLogic() {
        LOGGER.debug("Inside calculate ButtonClick Btn");
        try {
            List input = new ArrayList();
            input.add(getSelection().getSessionDTO().getCurrentTableNames().get("ST_ARM_INFLATION_INVENTORY"));
            input.add(getSelection().getProjectionMasterSid());

            List list = getSummaryLogic().getInflationFactor(input);
            if (!list.isEmpty()) {
                AbstractNotificationUtils.getErrorNotification(ARMMessages.getCalculateMessageName(), ARMMessages.getCalculateMessageMsgId_001());
            } else {
                getSummaryLogic().updateForCalculte(input, getSelection().getSessionDTO().getCurrentTableNames().get("ST_ARM_INFLATION_INVENTORY_ADJ"));
            }
        } catch (Exception e) {
            LOGGER.error("Error in calculateLogic :"+e);
        }
        return false;
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
        resultBeanContainertRX6.setColumnProperties(properties);
        resultBeanContainertRX6.setRecordHeader(rightSingleVisibleColumn);
        rightTable.setVisibleColumns(rightSingleVisibleColumn.toArray());
        rightTable.setColumnHeaders(Arrays.copyOf(((List) header.get(1)).toArray(), ((List) header.get(1)).size(), String[].class));
        for (Object propertyId : rightTable.getVisibleColumns()) {
            rightTable.setColumnAlignment(propertyId, ExtCustomTable.Align.RIGHT);
        }

        Trx6InventoryFieldFactory inventoryFieldFactory = new Trx6InventoryFieldFactory(getSummaryLogic(), selection);
        rightTable.setEditable(true);
        rightTable.setTableFieldFactory(inventoryFieldFactory);
        Object[] orderedArgs = {selection.getDataSelectionDTO().getProjectionId(), selection.getInventoryDetails(), selection.getPrice(), getSelection().getAdjustedPrice(),
            selection.getSessionDTO().getUserId(), selection.getSessionDTO().getSessionId()};
        getSummaryLogic().getInventoryResults(orderedArgs);
        setConverter(rightTable, rightTable.getVisibleColumns());
        getTableLogic().loadSetData(Boolean.FALSE);

    }

    @Override
    protected boolean setRespectiveLevelFileterValue(String levelValue, int levelNo) {
        getSelection().setSaleslevelFilterValue(levelValue);
        return true;
    }

    public void inventoryProcedureCall(AbstractSelectionDTO selection, List<String> propertyList) {
        Object[] orderedArgs = {selection.getProjectionMasterSid(), selection.getInventoryDetails(), selection.getInventoryOptionGroup(), selection.getInventoryreserveDate(), selection.getPrice(), "0"};
        if (selection.isFieldInput(propertyList.get(0))
                && selection.isFieldInput(propertyList.get(1)) && selection.isFieldInput(propertyList.get(NumericConstants.TWO))) {
            selection.setProcedureInputs(propertyList.get(0), selection.getInventoryDetails().trim());
            selection.setProcedureInputs(propertyList.get(1), selection.getPrice().trim());
            selection.setProcedureInputs(propertyList.get(NumericConstants.TWO), selection.getInventoryreserveDate().trim());
            getSummaryLogic().getInventoryResults(orderedArgs);
        } else if (!(selection.getProcedureInputs(propertyList.get(0).trim()).equals(selection.getInventoryDetails().trim()))
                && selection.getProcedureInputs(propertyList.get(1).trim()).equals(selection.getPrice().trim()) && selection.getProcedureInputs(propertyList.get(NumericConstants.TWO).trim()).equals(selection.getInventoryreserveDate().trim())) {
            selection.setProcedureInputs(propertyList.get(0), selection.getInventoryDetails().trim());
            selection.setProcedureInputs(propertyList.get(1), selection.getPrice().trim());
            selection.setProcedureInputs(propertyList.get(NumericConstants.TWO), selection.getInventoryreserveDate().trim());
            getSummaryLogic().getInventoryResults(orderedArgs);
        }
    }

    @Override
    public Trx6InventoryLogic getSummaryLogic() {
        return (Trx6InventoryLogic) super.getSummaryLogic();
    }

    @Override
    public ExcelInterface getExcelLogic() {
        return (ExcelInterface) getSummaryLogic();
    }

    @Override
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
        return getSelection().getSaleshierarchy();
    }

    @Override
    public void setRespectiveHierarchy(String viewType) {
        getSelection().setSaleshierarchy(ARMUtils.getLevelAndLevelFilter(ARMConstants.getDeductionCustomer()));
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
            listData.add(getIsDemandSreen());
            listData.add(getInterval());
            ExcelUtils.setExcelData(list, getExcelHierarchy(), getExcelExportVisibleColumn(), getExcelContainer(), discountColumnNeeded(), ARMConstants.getPipelineInventoryTrueUp(), listData);
            ((CommonUI) getUI()).setExcelFlag(Boolean.TRUE);
            ExcelExport export = new ExcelExport(new ExtCustomTableHolder(getExcelTable()), getExcelFileName(), getExcelFileName(), getExcelFileName() + ".xls", false);
            export.setUseTableFormatPropertyValue(Boolean.TRUE);
            export.export();
            getExcelContainer().removeAllItems();
            tableLayout.removeComponent(getExcelTable());
        } catch (Exception ex) {
            LOGGER.error("Error in excelExportLogic :"+ex);
        }
    }

    @Override
    protected void loadLevelFilterValueDdlb(String levelValue, int levelNo) {
        LOGGER.debug("inside loadLevelFilterValueDdlb Method");
    }

    @Override
    protected void valueDdlbValueChange(int masterSids) {
        LOGGER.debug("inside valueDdlbValueChange Method");
    }

    @Override
    public Trx6SelectionDTO getSelection() {
        return super.getSelection();
    }

    @Override
    protected void customerProductValueChange() {
        LOGGER.debug("inside customerProductValueChange Method");

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
    protected boolean isPercentageColumn2Decimal(String column) {
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

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
