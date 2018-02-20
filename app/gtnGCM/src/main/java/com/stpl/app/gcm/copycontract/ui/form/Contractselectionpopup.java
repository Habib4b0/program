/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.copycontract.ui.form;

import com.stpl.app.gcm.copycontract.dto.ContractSelectionDTO;
import com.stpl.app.gcm.discount.ui.layout.CopyContractWindow;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.stpl.app.gcm.util.Constants;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.v7.data.Property;
import com.vaadin.server.Sizeable;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.ui.UI;
import com.vaadin.ui.Window;

import java.util.ArrayList;
import java.util.List;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author kasiammal.m
 */
public class Contractselectionpopup extends Window {

 
    @UiField("contractscount")
    public ComboBox contractscount;
    private final List<ContractSelectionDTO> selectedList;

    public Contractselectionpopup(List<ContractSelectionDTO> selectedList) {
        super();
        addStyleName(Constants.BOOTSTRAP);
        addStyleName("bootstrap-bb");
        addStyleName(Constants.BOOTSTRAP_FORECAST_BOOTSTRAP_NM);
        center();
        this.selectedList = selectedList == null ? selectedList : new ArrayList<>(selectedList);
        setModal(true);
        setWidth(NumericConstants.FLOAT_THIRTY_FIVE, Sizeable.Unit.PERCENTAGE);
        setHeight(NumericConstants.FLOAT_FIFTEEN, Sizeable.Unit.PERCENTAGE);
        setContent(Clara.create(getClass().getResourceAsStream("/contractselectionpopup.xml"), this));
        configureFields();
        setResizable(false);

    }

    protected void configureFields() {
        contractscount.addItem(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        contractscount.addItem("1");
        contractscount.addItem("2");
        contractscount.addItem("3");
        contractscount.addItem("4");
        contractscount.addItem("5");
        contractscount.setValue(Constants.IndicatorConstants.SELECT_ONE.getConstant());
        contractscount.setNullSelectionItemId(Constants.IndicatorConstants.SELECT_ONE.getConstant());
    }

    @UiHandler("contractscount")
    public void contractscountchange(Property.ValueChangeEvent event) {

        if (contractscount.getValue() != null && !contractscount.getValue().toString().equalsIgnoreCase(Constants.IndicatorConstants.SELECT_ONE.getConstant())) {

            UI.getCurrent().removeWindow(this);
            CopyContractWindow CCWindow = new CopyContractWindow(new SessionDTO(), selectedList, contractscount.getValue().toString());
            UI.getCurrent().addWindow(CCWindow);

        }
    }
}
