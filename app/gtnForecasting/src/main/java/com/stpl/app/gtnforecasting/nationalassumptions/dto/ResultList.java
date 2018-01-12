/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.nationalassumptions.dto;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author gopinath
 */
public class ResultList {
    private DataSelectionDTO dataSelectionDTO;
	private Set<DataSelectionDTO> availableCustomersAndContracts = new HashSet<>();
	private Set<DataSelectionDTO> availableProducts = new HashSet<>();
	private Set<DataSelectionDTO> selectedCustomersAndContracts = new HashSet<>();
	private Set<DataSelectionDTO> selectedProducts = new HashSet<>();
	private String flag = "fail";

    public DataSelectionDTO getDataSelectionDTO() {
        return dataSelectionDTO;
    }

    public void setDataSelectionDTO(DataSelectionDTO dataSelectionDTO) {
        this.dataSelectionDTO = dataSelectionDTO;
    }

    public Set<DataSelectionDTO> getAvailableCustomersAndContracts() {
        return availableCustomersAndContracts == null ? availableCustomersAndContracts : Collections.unmodifiableSet(availableCustomersAndContracts);
    }

    public void setAvailableCustomersAndContracts(Set<DataSelectionDTO> availableCustomersAndContracts) {
        this.availableCustomersAndContracts = availableCustomersAndContracts == null ? availableCustomersAndContracts : Collections.unmodifiableSet(availableCustomersAndContracts);
    }

    public Set<DataSelectionDTO> getAvailableProducts() {
        return availableProducts == null ? availableProducts : Collections.unmodifiableSet(availableProducts);
    }

    public void setAvailableProducts(Set<DataSelectionDTO> availableProducts) {
        this.availableProducts = availableProducts == null ? availableProducts : Collections.unmodifiableSet(availableProducts);
    }

    public Set<DataSelectionDTO> getSelectedCustomersAndContracts() {
        return selectedCustomersAndContracts == null ? selectedCustomersAndContracts : Collections.unmodifiableSet(selectedCustomersAndContracts);
    }

    public void setSelectedCustomersAndContracts(Set<DataSelectionDTO> selectedCustomersAndContracts) {
        this.selectedCustomersAndContracts = selectedCustomersAndContracts == null ? selectedCustomersAndContracts : Collections.unmodifiableSet(selectedCustomersAndContracts);
    }

    public Set<DataSelectionDTO> getSelectedProducts() {
        return selectedProducts == null ? selectedProducts : Collections.unmodifiableSet(selectedProducts);
    }

    public void setSelectedProducts(Set<DataSelectionDTO> selectedProducts) {
        this.selectedProducts = selectedProducts == null ? selectedProducts : Collections.unmodifiableSet(selectedProducts);
    }

   

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
        
}
