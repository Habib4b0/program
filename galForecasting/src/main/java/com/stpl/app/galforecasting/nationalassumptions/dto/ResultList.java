/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.nationalassumptions.dto;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author gopinath
 */
public class ResultList {
    private DataSelectionDTO dataSelectionDTO;
	private Set<DataSelectionDTO> availableCustomersAndContracts = new HashSet<DataSelectionDTO>();
	private Set<DataSelectionDTO> availableProducts = new HashSet<DataSelectionDTO>();
	private Set<DataSelectionDTO> selectedCustomersAndContracts = new HashSet<DataSelectionDTO>();
	private Set<DataSelectionDTO> selectedProducts = new HashSet<DataSelectionDTO>();
	private String flag = "fail";

    public DataSelectionDTO getDataSelectionDTO() {
        return dataSelectionDTO;
    }

    public void setDataSelectionDTO(DataSelectionDTO dataSelectionDTO) {
        this.dataSelectionDTO = dataSelectionDTO;
    }

    public Set<DataSelectionDTO> getAvailableCustomersAndContracts() {
        return availableCustomersAndContracts;
    }

    public void setAvailableCustomersAndContracts(Set<DataSelectionDTO> availableCustomersAndContracts) {
        this.availableCustomersAndContracts = availableCustomersAndContracts;
    }

    public Set<DataSelectionDTO> getAvailableProducts() {
        return availableProducts;
    }

    public void setAvailableProducts(Set<DataSelectionDTO> availableProducts) {
        this.availableProducts = availableProducts;
    }

    public Set<DataSelectionDTO> getSelectedCustomersAndContracts() {
        return selectedCustomersAndContracts;
    }

    public void setSelectedCustomersAndContracts(Set<DataSelectionDTO> selectedCustomersAndContracts) {
        this.selectedCustomersAndContracts = selectedCustomersAndContracts;
    }

    public Set<DataSelectionDTO> getSelectedProducts() {
        return selectedProducts;
    }

    public void setSelectedProducts(Set<DataSelectionDTO> selectedProducts) {
        this.selectedProducts = selectedProducts;
    }

   

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
        
}
