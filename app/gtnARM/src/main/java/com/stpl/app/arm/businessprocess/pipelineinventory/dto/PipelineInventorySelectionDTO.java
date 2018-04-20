/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.pipelineinventory.dto;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.common.CommonLogic;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Karthikeyan.Subraman
 */
public class PipelineInventorySelectionDTO extends AbstractSelectionDTO {

    private List<String[]> salesvariablesList;
    private String inventoryDetails;
    private String inventoryprice;
    private String inventoryreserveDate;
    private String[] variableVisibleColumns;
    private Map<String, String> inventoryfixedColumnList;

    public PipelineInventorySelectionDTO() {
        /*
        THE DEFAULT CONSTRUCTOR
         */
    }

    @Override
    public List<String[]> getSalesVariables() {
        return CommonLogic.getInstance().getArrayListCloned(salesvariablesList);
    }

    @Override
    public void setSalesVariables(List<String[]> salesvariablesList) {
        this.salesvariablesList = CommonLogic.getInstance().getArrayListCloned(salesvariablesList);
    }

    @Override
    public void setInventoryDetails(String inventoryDetails) {
        this.inventoryDetails = inventoryDetails;
    }

    @Override
    public String getInventoryDetails() {
        return inventoryDetails;
    }

    public String getInventoryprice() {
        return inventoryprice;
    }

    public void setInventoryprice(String inventoryprice) {
        this.inventoryprice = inventoryprice;
    }

    @Override
    public String getInventoryreserveDate() {
        return inventoryreserveDate;
    }

    @Override
    public void setInventoryreserveDate(String inventoryreserveDate) {
        this.inventoryreserveDate = inventoryreserveDate;
    }

    @Override
    public List<String> getSelectedAdjustmentType() {
        return selectedAdjustmentType;
    }

    @Override
    public void setSelectedAdjustmentType(List<String> selectedAdjustmentType) {
        this.selectedAdjustmentType = selectedAdjustmentType;
    }

    @Override
    public String getDetailLevel() {
        return detailLevel;
    }

    @Override
    public void setDetailLevel(String detailLevel) {
        this.detailLevel = detailLevel;
    }

    @Override
    public List<String> getDetailreserveAcount() {
        return detailreserveAcount;
    }

    @Override
    public void setDetailreserveAcount(List<String> detailReserveAcount) {
        this.detailreserveAcount = detailReserveAcount;
    }

    @Override
    public List<String> getDetailvariables() {
        return detailvariables;
    }

    @Override
    public void setDetailvariables(List<String> detailVariables) {
        this.detailvariables = detailVariables;
    }

    @Override
    public String getSummaryglDate() {
        return summaryglDate;
    }

    @Override
    public void setSummaryglDate(String summaryGLDate) {
        this.summaryglDate = summaryGLDate;
    }

    @Override
    public String[] getVariableVisibleColumns() {
        return CommonLogic.getInstance().getStringArrayCloned(variableVisibleColumns);
    }

    @Override
    public void setVariableVisibleColumns(String[] variableVisibleColumns) {
        this.variableVisibleColumns = CommonLogic.getInstance().getStringArrayCloned(variableVisibleColumns);
    }

    public Map<String, String> getInventoryfixedColumnList() {
        return inventoryfixedColumnList;
    }

    public void setInventoryfixedColumnList(Map<String, String> inventoryfixedColumnList) {
        this.inventoryfixedColumnList = inventoryfixedColumnList;
    }

}
