/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.remove.form;

import static com.stpl.app.gcm.discount.ui.form.ExistingDiscountTab.LOGGER;
import com.stpl.app.gcm.globalchange.dto.SelectionDTO;
import com.stpl.app.gcm.itemmanagement.index.util.ConstantsUtil;
import com.stpl.app.gcm.itemmanagement.itemabstract.dto.AbstractContractSearchDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.form.AbstractComponentInfo;
import com.stpl.app.gcm.util.Constants;
import com.vaadin.v7.data.Property;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.v7.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hazi.s
 */
public class RemoveContractSelection extends CustomComponent {

    private VerticalLayout mainLayout = new VerticalLayout();
    private SelectionDTO selection;
    private List selectedItemList;
    private AbstractComponentInfo componentDetails;
    private RemoveContractSearch contractSearch;
    private AbstractContractSearchDTO componentInfoDTO = new AbstractContractSearchDTO();

    public RemoveContractSelection(SelectionDTO selection, List selectedItemList) {
        try {
            this.selection = selection;
            this.selectedItemList = selectedItemList == null ? selectedItemList : new ArrayList<>(selectedItemList);
        } catch (Exception e) {
            LOGGER.error("",e);
        }
    }

    public Component getContent() {
        configureFields();
        return mainLayout;
    }

    private void configureFields() {
        contractSearch = new RemoveContractSearch(selection, selectedItemList);
        componentDetails = new AbstractComponentInfo(ConstantsUtil.RS, selection);
        mainLayout.addComponent(contractSearch);
        mainLayout.addComponent(componentDetails);
        contractSearch.contractSelectionTable.addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method called when available results value is changed.
             */
            @SuppressWarnings("PMD")
            @Override
            public void valueChange(final Property.ValueChangeEvent event) {
                resultsItemClick(event.getProperty().getValue());
            }
        });
    }

    protected void resultsItemClick(final Object obj) {
        if (obj == null) {
            componentInfoDTO = null;
        } else {
            componentInfoDTO = (AbstractContractSearchDTO) obj;
            selection.setContractSid(componentInfoDTO.getContractSid());
            selection.setCompanySid(componentInfoDTO.getCompanySid());
            selection.setCfpContractSid(componentInfoDTO.getCfpContractSid());
            selection.setIfpConteractSid(componentInfoDTO.getIfpConteractSid());
            selection.setPsContractSid(componentInfoDTO.getPsContractSid());
            selection.setRsContractSid(componentInfoDTO.getRsContractSid());
            componentDetails.fireComponentListener(Constants.RS, selection);
        }
    }

    public boolean submitLogic() {
        return contractSearch.submit();
    }

    public boolean loadSetDataCall() {
        selection.setCountQueryName("Item Load contract Count");
        selection.setDataQueryName("Load contract Item");
        return contractSearch.contractSelectionTableLogic.loadSetData(selection, true, selectedItemList, contractSearch.getInput());
    }
}
