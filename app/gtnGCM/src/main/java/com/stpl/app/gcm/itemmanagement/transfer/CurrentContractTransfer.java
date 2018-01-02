/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.transfer;

import static com.stpl.app.gcm.discount.ui.form.ExistingDiscountTab.LOGGER;
import com.stpl.app.gcm.globalchange.dto.SelectionDTO;
import com.stpl.app.gcm.itemmanagement.index.util.ConstantsUtil;
import com.stpl.app.gcm.itemmanagement.itemabstract.dto.AbstractContractSearchDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.form.AbstractComponentInfo;
import com.stpl.app.gcm.util.Constants;
import com.vaadin.data.Property;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.VerticalLayout;
import java.util.List;

/**
 *
 * @author hazi.s
 */
public class CurrentContractTransfer extends CustomComponent {

    private VerticalLayout mainLayout = new VerticalLayout();
    public CurrentContractContractSearch contractSearch;
    private SelectionDTO selection;
    public List selectedItemList;
    private AbstractComponentInfo componentDetails;
    private AbstractContractSearchDTO componentInfoDTO = new AbstractContractSearchDTO();

    public boolean isRemoveProjectionBooleanVal() {
        if (contractSearch != null) {
            return contractSearch.isRemoveProjectionBooleanVal();
        }
        return false;
    }

    public CurrentContractTransfer(SelectionDTO selection, List selectedItemList) {
        try {
            this.selection = selection;
            this.selectedItemList = selectedItemList;
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    public Component getContent() {
        configureFields();
        return mainLayout;
    }

    private void configureFields() {
        componentDetails = new AbstractComponentInfo(ConstantsUtil.RS, selection);
        contractSearch = new CurrentContractContractSearch(selection, selectedItemList);
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

        mainLayout.addComponent(contractSearch);
        mainLayout.addComponent(componentDetails);
    }

    protected void resultsItemClick(final Object obj) {
        if (obj == null) {
            componentInfoDTO = null;
        } else {
            componentInfoDTO = (AbstractContractSearchDTO) obj;
            selection.setContractSid(componentInfoDTO.getContractMasterSid());
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
        if (selection.getButtonMode().equals(ConstantsUtil.PROJECTIONTRANSFER)) {
            selection.setCountQueryName("ProjectionTransferItemCountQuery");
            selection.setDataQueryName("ProjectionTransferItemDataQuery");
        } else {
            selection.setCountQueryName("Item Load contract Count");
            selection.setDataQueryName("Load contract Item");
        }
        return contractSearch.contractSelectionTableLogic.loadSetData(selection, true, selectedItemList, contractSearch.getInput());
    }
}
