/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.arm.businessprocess.pipelineinventory.dto;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Karthikeyan.Subraman
 */
public class PipelineInventorySelectionDTO extends AbstractSelectionDTO{
    
    private List<String[]> sales_variables;
    private String inventory_Details;
    private String inventory_price;
    private String inventory_reserveDate;
    private String[] variableVisibleColumns;
    private Map<String,String> inventory_fixed_ColumnList;

    public List<String[]> getSales_variables() {
        return sales_variables;
    }

    public void setSales_variables(List<String[]> sales_variables) {
        this.sales_variables = sales_variables;
    }
   

    public void setInventory_Details(String inventory_Details) {
        this.inventory_Details = inventory_Details;
    }

    public String getInventory_Details() {
        return inventory_Details;
    }

    public String getInventory_price() {
        return inventory_price;
    }

    public void setInventory_price(String inventory_price) {
        this.inventory_price = inventory_price;
    }

    public String getInventory_reserveDate() {
        return inventory_reserveDate;
    }

    public void setInventory_reserveDate(String inventory_reserveDate) {
        this.inventory_reserveDate = inventory_reserveDate;
    }

    public List<String> getSelectedAdjustmentType() {
        return selectedAdjustmentType;
    }

    public void setSelectedAdjustmentType(List<String> selectedAdjustmentType) {
        this.selectedAdjustmentType = selectedAdjustmentType;
    }

    public String getDetail_Level() {
        return detail_Level;
    }

    public void setDetail_Level(String detail_Level) {
        this.detail_Level = detail_Level;
    }

    public List<String> getDetail_reserveAcount() {
        return detail_reserveAcount;
    }

    public void setDetail_reserveAcount(List<String> detail_reserveAcount) {
        this.detail_reserveAcount = detail_reserveAcount;
    }

    public List<String> getDetail_variables() {
        return detail_variables;
    }

    public void setDetail_variables(List<String> detail_variables) {
        this.detail_variables = detail_variables;
    }

    public String getSummary_glDate() {
        return summary_glDate;
    }

    public void setSummary_glDate(String summary_glDate) {
        this.summary_glDate = summary_glDate;
    }

    public String[] getVariableVisibleColumns() {
        return variableVisibleColumns;
    }

    public void setVariableVisibleColumns(String[] variableVisibleColumns) {
        this.variableVisibleColumns = variableVisibleColumns;
    }

    public Map<String, String> getInventory_fixed_ColumnList() {
        return inventory_fixed_ColumnList;
    }

    public void setInventory_fixed_ColumnList(Map<String, String> inventory_fixed_ColumnList) {
        this.inventory_fixed_ColumnList = inventory_fixed_ColumnList;
    }

   
    
}
