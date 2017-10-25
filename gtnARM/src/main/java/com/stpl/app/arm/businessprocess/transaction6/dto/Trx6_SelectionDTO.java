/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction6.dto;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Srithar.Raju
 */
public class Trx6_SelectionDTO extends AbstractSelectionDTO {

    String adjustedPrice;
    List<String> inventoryHeaderList;
    private Map<String, String> inventory_fixed_ColumnList;
    boolean isLevelFilter;

    public boolean isIsLevelFilter() {
        return isLevelFilter;
    }

    public void setIsLevelFilter(boolean isLevelFilter) {
        this.isLevelFilter = isLevelFilter;
    }

    public String getAdjustedPrice() {
        return adjustedPrice;
    }

    public void setAdjustedPrice(String adjustedPrice) {
        this.adjustedPrice = adjustedPrice;
    }

    public List<String> getInventoryHeaderList() {
        return inventoryHeaderList;
    }

    public void setInventoryHeaderList(List<String> inventoryHeaderList) {
        this.inventoryHeaderList = inventoryHeaderList;
    }

    public Map<String, String> getInventory_fixed_ColumnList() {
        return inventory_fixed_ColumnList;
    }

    public void setInventory_fixed_ColumnList(Map<String, String> inventory_fixed_ColumnList) {
        this.inventory_fixed_ColumnList = inventory_fixed_ColumnList;
    }

}
