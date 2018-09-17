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
import com.vaadin.v7.data.Property;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.v7.ui.VerticalLayout;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author hazi.s
 */
public class CurrentContractTransfer extends CustomComponent {

    private VerticalLayout mainLayout = new VerticalLayout();
    private CurrentContractContractSearch contractSearch;
    private SelectionDTO selection;
    private List selectedItemList;
    private AbstractComponentInfo componentDetails;
    

    public boolean isRemoveProjectionBooleanVal() {
        if (getContractSearch() != null) {
            return getContractSearch().isRemoveProjectionBooleanVal();
        }
        return false;
    }

    public CurrentContractTransfer(SelectionDTO selection, List selectedItemList) {
        try {
            this.selection = selection;
            this.selectedItemList = selectedItemList == null ? selectedItemList : Collections.unmodifiableList(selectedItemList);
        } catch (Exception e) {
            LOGGER.error("",e);
        }
    }

    public Component getContent() {
        configureFields();
        return mainLayout;
    }

    private void configureFields() {
        componentDetails = new AbstractComponentInfo(ConstantsUtil.RS, selection);
        setContractSearch(new CurrentContractContractSearch(selection, selectedItemList));
        getContractSearch().getContractSelectionTable().addValueChangeListener(new Property.ValueChangeListener() {
            /**
             * Method called when available results value is changed.
             */
            @SuppressWarnings("PMD")
            @Override
            public void valueChange(final Property.ValueChangeEvent event) {
                resultsItemClick(event.getProperty().getValue());
            }
        });

        mainLayout.addComponent(getContractSearch());
        mainLayout.addComponent(componentDetails);
    }

    protected void resultsItemClick(final Object obj) {
         AbstractContractSearchDTO componentInfoDTO ;
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
        return getContractSearch().submit();
    }

    public boolean loadSetDataCall() {
        if (selection.getButtonMode().equals(ConstantsUtil.PROJECTIONTRANSFER)) {
            selection.setCountQueryName("ProjectionTransferItemCountQuery");
            selection.setDataQueryName("ProjectionTransferItemDataQuery");
        } else {
            selection.setCountQueryName("Item Load contract Count");
            selection.setDataQueryName("Load contract Item");
        }
        return getContractSearch().getContractSelectionTableLogic().loadSetData(selection, true, selectedItemList, getContractSearch().getInput());
    }

	public CurrentContractContractSearch getContractSearch() {
		return contractSearch;
	}

	public void setContractSearch(CurrentContractContractSearch contractSearch) {
		this.contractSearch = contractSearch;
	}
}
