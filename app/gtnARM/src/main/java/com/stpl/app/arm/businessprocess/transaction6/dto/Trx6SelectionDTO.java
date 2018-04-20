/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.transaction6.dto;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.common.CommonLogic;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Srithar.Raju
 */
public class Trx6SelectionDTO extends AbstractSelectionDTO {

    private String adjustedPrice;
    private List<String> inventoryHeaderList;
    private Map<String, String> inventoryfixedColumnList;
    private boolean isLevelFilter;

    public Trx6SelectionDTO() {
        /*
        THE DEFAULT CONSTRUCTOR
         */
    }

    public boolean isIsLevelFilter() {
        return isLevelFilter;
    }

    public void setIsLevelFilter(boolean isLevelFilter) {
        this.isLevelFilter = isLevelFilter;
    }

    @Override
    public String getAdjustedPrice() {
        return adjustedPrice;
    }

    @Override
    public void setAdjustedPrice(String adjustedPrice) {
        this.adjustedPrice = adjustedPrice;
    }

    public List<String> getInventoryHeaderList() {
        return CommonLogic.getInstance().getArrayListCloned(inventoryHeaderList);
    }

    public void setInventoryHeaderList(List<String> inventoryHeaderList) {
        this.inventoryHeaderList = CommonLogic.getInstance().getArrayListCloned(inventoryHeaderList);
    }

    public Map<String, String> getInventoryfixedColumnList() {
        return inventoryfixedColumnList;
    }

    public void setInventoryfixedColumnList(Map<String, String> inventoryfixedColumnList) {
        this.inventoryfixedColumnList = inventoryfixedColumnList;
    }

}
