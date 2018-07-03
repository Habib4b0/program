/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.abstractbusinessprocess.form;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AdjustmentDTO;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.logic.AbstractPipelineLogic;
import com.stpl.app.arm.businessprocess.transaction8.tablegenerator.RatesTableGenerator;
import com.stpl.app.arm.supercode.LogicAble;
import com.stpl.app.arm.utils.ARMUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.ui.util.converters.DataFormatConverter;
import com.stpl.ifs.util.QueryUtil;
import com.stpl.ifs.util.constants.ARMConstants;
import org.asi.ui.extfilteringtable.ExtCustomTable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtContainer;
import org.asi.container.ExtTreeContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author vinoth.parthasarathy
 */
public abstract class AbstractRatesSearchResults extends AbstractSearchResults {

    private Object[] visibleColumns;
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected ExtTreeContainer<AdjustmentDTO> resultBeanContainerVal = new ExtTreeContainer<>(
            AdjustmentDTO.class, ExtContainer.DataStructureMode.LIST);

    public AbstractRatesSearchResults(LogicAble logic, AbstractSelectionDTO selection) {
        super(logic, selection);
    }

    @Override
    public void setVisibleColumnsAndHeaders() {
        configureOnRatesSearchResults();
        leftTable = table.getLeftFreezeAsTable();
        rightTable = table.getRightFreezeAsTable();
        rightTable.setImmediate(true);
        leftTable.setImmediate(true);
        leftTable.setSortEnabled(false);
        rightTable.setSortEnabled(false);
        loadTableHeader(null);
        for (Object propertyId : rightTable.getVisibleColumns()) {
            rightTable.setColumnAlignment(propertyId, ExtCustomTable.Align.RIGHT);

        }
        for (Object propertyId : leftTable.getVisibleColumns()) {
            leftTable.setColumnAlignment(propertyId, ExtCustomTable.Align.LEFT);
        }
        abstractSearchContent.setWidth("100%");
    }

    @Override
    public ExtTreeContainer<AdjustmentDTO> getResultBeanContainerVal() {
        return resultBeanContainerVal;
    }
    private static final Object[] searchResult = {ARMConstants.getDeductionProduct(),
        ARMConstants.getDeductionCustomer(), ARMConstants.getDeductionCustomerContract()};

    protected void configureOnRatesSearchResults() {
        calculateBtn.setVisible(false);
        cancelOverride.setVisible(false);
        panelCaption.setCaption("Rate Results");
        valueDdlb.setVisible(false);
        bbExport.setPrimaryStyleName("link");
        bbExport.setIcon(ARMUtils.EXCEL_EXPORT_IMAGE, "Excel Export");
        customerProductView.addItems(searchResult);
        customerProductView.setValue(ARMConstants.getDeductionProduct());
    }

    @Override
    public void setExcelVisibleColumn() {
        Map properties = new HashMap();
        excelTable.setContainerDataSource(excelBeanContainer);
        excelBeanContainer.setRecordHeader(resultBeanContainerVal.getRecordHeader());
        for (Object prop : resultBeanContainerVal.getRecordHeader().toArray()) {
            properties.put(prop, String.class);
        }
        excelBeanContainer.setColumnProperties(properties);
        List<Object> visibleColumn = new ArrayList<>();
        List<String> columnHeader = new ArrayList<>();
        visibleColumn.addAll(Arrays.asList(table.getLeftFreezeAsTable().getVisibleColumns()));
        visibleColumn.addAll(Arrays.asList(table.getRightFreezeAsTable().getVisibleColumns()));
        List<String> headerList = Arrays.asList(table.getLeftFreezeAsTable().getColumnHeaders());
        List<String> headerList2 = Arrays.asList(table.getRightFreezeAsTable().getColumnHeaders());
        columnHeader.addAll(headerList);
        columnHeader.addAll(headerList2);
        excelTable.setVisibleColumns(visibleColumn.toArray());
        excelTable.setColumnHeaders(columnHeader.toArray(new String[0]));
        for (Object visibleColumnsValue : excelTable.getVisibleColumns()) {
            if (!"group".equals(visibleColumnsValue.toString())) {
                excelTable.setConverter(visibleColumnsValue, new DataFormatConverter("#,##0.000", "%"), Boolean.FALSE);
            }
        }
    }

    /**
     *
     * @param selectionDTO
     */
    public void generateLogic(AbstractSelectionDTO selectionDTO) {
        if (!selection.getSessionDTO().getAction().equals(ARMUtils.VIEW_SMALL)) {
            if (selectionDTO.getModuleName().equals(ARMConstants.getTransaction7())) {
                trx7RateProcedureCall(selectionDTO);
            } else {
                rateProcedureCall(selectionDTO);
            }
        }
        setDeductionView(selectionDTO);
        setRespectiveHierarchy(selectionDTO.getRateDeductionView());
        configureLevelAndLevelFilter();
        selectionDTO.setLevelNo(1);
        loadTableHeader(selectionDTO.getRateColumnList());
        tableLogic.loadSetData(Boolean.FALSE);
    }

    /**
     *
     * @param columnList
     */
    protected void loadTableHeader(List<List> columnList) {
        Map properties = new HashMap();
        String[] visibleHeader;
        if (columnList != null && !columnList.isEmpty()) {
            getTotalHeader(columnList);
            visibleColumns = (columnList.get(0)).toArray();
            visibleHeader = Arrays.copyOf(columnList.get(1).toArray(), columnList.get(1).size(), String[].class);
            for (Object visibleColumn : visibleColumns) {
                properties.put(visibleColumn, String.class);
            }
        } else {
            visibleColumns = new Object[]{"defaultColumn"};
            visibleHeader = new String[]{StringUtils.EMPTY};
        }

        tableLogic.setContainerDataSource(resultBeanContainerVal);
        resultBeanContainerVal.setRecordHeader(Arrays.asList(visibleColumns));
        resultBeanContainerVal.setColumnProperties(properties);
        leftTable.setVisibleColumns(ARMUtils.getPipelineAccrualLeftColumn());
        leftTable.setColumnHeaders(ARMConstants.getDeductionProduct().equalsIgnoreCase(String.valueOf(customerProductView.getValue()))
                ? ARMUtils.PRODUCT : ARMUtils.CUSTOMER_SMALL);
        rightTable.setVisibleColumns(visibleColumns);
        rightTable.setColumnHeaders(visibleHeader);
        for (Object propertyId : rightTable.getVisibleColumns()) {
            rightTable.setColumnAlignment(propertyId, ExtCustomTable.Align.RIGHT);

        }
        configureRightTable();
    }

    @Override
    protected void configureRightTable() {
        RatesTableGenerator fieldFactory = new RatesTableGenerator(getSummaryLogic(), getSelection(), ARMConstants.getDeductionContractCustomer().equals(String.valueOf(customerProductView.getValue()))
                && ARMConstants.getDeduction().equals(selection.getRateDeductionLevelName()), visibleColumns, tableLogic, false);
        rightTable.setEditable(true);
        rightTable.setTableFieldFactory(fieldFactory);
    }

    @Override
    public AbstractPipelineLogic getSummaryLogic() {
        return (AbstractPipelineLogic) super.getSummaryLogic();
    }

    @Override
    protected boolean calculateLogic() {
        return false;
    }

    @Override
    protected void customerProductValueChange() {
        if (resultBeanContainerVal != null && resultBeanContainerVal.size() > 0) {
            leftTable.setColumnHeaders(ARMConstants.getDeductionProduct().equalsIgnoreCase(String.valueOf(customerProductView.getValue()))
                    ? ARMUtils.PRODUCT : ARMUtils.CUSTOMER_SMALL);
            setDeductionView(selection);
            setRespectiveHierarchy(selection.getRateDeductionView());
            configureLevelAndLevelFilter();
            configureRightTable();
            tableLogic.loadSetData(Boolean.FALSE);
        }
    }

    /**
     *
     * @param selectionDTO
     */
    public void setDeductionView(AbstractSelectionDTO selectionDTO) {
        selectionDTO.setRateDeductionView(String.valueOf(customerProductView.getValue()));
        String contractVal = ARMConstants.getDeductionCustomerContract().equals(selectionDTO.getRateDeductionView()) ? "CUSTOMER" : "CONTRACT";
        String value = (ARMConstants.getDeduction().equals(selectionDTO.getRateDeductionLevelName()) ? "DEDUCTION" : contractVal);
        String deductionCustomer = ARMConstants.getDeductionCustomer().equals(selectionDTO.getRateDeductionView())
                ? "CUSTOMER" : value;
        selectionDTO.setRateLevelName(ARMConstants.getDeductionProduct().equals(selectionDTO.getRateDeductionView())
                ? "BRAND" : deductionCustomer);
    }

    protected abstract void getTotalHeader(List<List> columnList);

    @Override
    protected boolean setRespectiveLevelFileterValue(String levelValue, int levelNo) {
        selection.setRateslevelFilterNo(levelNo);
        selection.setRateLevelName(levelValue.toUpperCase(Locale.ENGLISH));
        if (levelNo == 0) {
            setDeductionView(selection);
        }
        return true;
    }

    public void rateProcedureCall(AbstractSelectionDTO selectionDTO) {
        Object[] orderedArgs = {selectionDTO.getDataSelectionDTO().getProjectionId(), selectionDTO.getRateBasisName(),
            selectionDTO.getRatePeriodValue(), selectionDTO.getRateFrequencyName(), selectionDTO.getModuleName(), selectionDTO.getRatePeriodValue(),
            selectionDTO.getSessionDTO().getUserId(), selectionDTO.getSessionDTO().getSessionId()};
        selectionDTO.setRatesOverrideFlag(NumericConstants.ZERO);
        QueryUtil.callProcedure("PRC_ARM_PIPELINE_RATE", orderedArgs);
    }

    @Override
    public Map<Integer, String> getHierarchy() {
        return getSelection().getRateshierarchy();
    }

    @Override
    public void setRespectiveHierarchy(String viewType) {
        String viewTypeValue;
        if (viewType.equals(ARMConstants.getDeductionCustomerContract()) && !getSelection().getRateDeductionLevelName().equals(ARMConstants.getDeduction())) {
            viewTypeValue = "non" + ARMConstants.getDeductionCustomerContract();
        } else if (viewType.equals(ARMConstants.getDeductionContractCustomer()) && !getSelection().getRateDeductionLevelName().equals(ARMConstants.getDeduction())) {
            viewTypeValue = "non" + ARMConstants.getDeductionContractCustomer();

        } else {
            viewTypeValue = viewType;
        }
        getSelection().setRateshierarchy(ARMUtils.getLevelAndLevelFilter(viewTypeValue));
    }

    public void trx7RateProcedureCall(AbstractSelectionDTO selectionDTO) {
        Object[] orderedArgs = {selectionDTO.getDataSelectionDTO().getProjectionId(), "Monthly",
            selectionDTO.getSessionDTO().getUserId(), selectionDTO.getSessionDTO().getSessionId()};
        QueryUtil.callProcedure("PRC_ARM_DISTRIBUTION_FEES_RATE", orderedArgs);
        selectionDTO.setRatesOverrideFlag(NumericConstants.ZERO);
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
