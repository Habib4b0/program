/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.utils;

import com.stpl.app.gtnforecasting.dto.AlternateHistoryDTO;
import com.stpl.app.gtnforecasting.dto.ContractBrandDTO;
import java.util.ArrayList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class AlternateLookupSource.
 *
 * @author lokeshwari
 */
public class AlternateLookupSource {

    
    
    
    private List<ContractBrandDTO> contractcustomersList = new ArrayList<>();
    /**
     * The customers list.
     */
    private List<AlternateHistoryDTO> customersList = new ArrayList<>();
    /**
     * The brand list.
     */
    private List<AlternateHistoryDTO> brandList = new ArrayList<>();

    /**
     * Gets the customers list.
     *
     * @return the customers list
     */
    public List<AlternateHistoryDTO> getCustomersList() {
        return customersList;
    }

    /**
     * Sets the customers list.
     *
     * @param customersList the new customers list
     */
    public void setCustomersList(final List<AlternateHistoryDTO> customersList) {
        this.customersList = customersList;
    }

    /**
     * Gets the brand list.
     *
     * @return the brand list
     */
    public List<AlternateHistoryDTO> getBrandList() {
        return brandList;
    }

    /**
     * Sets the brand list.
     *
     * @param brandList the new brand list
     */
    public void setBrandList(final List<AlternateHistoryDTO> brandList) {
        this.brandList = brandList;
    }
    public List<ContractBrandDTO> getContractcustomersList() {
        return contractcustomersList;
    }

    public void setContractcustomersList(List<ContractBrandDTO> contractcustomersList) {
        this.contractcustomersList = contractcustomersList;
    }
}
