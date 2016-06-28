/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.transfercontract.form;

import com.vaadin.ui.VerticalLayout;
import org.vaadin.teemu.clara.Clara;

/**
 *
 * @author Harlin
 */
public class TransferDetailsSalesRebate extends VerticalLayout {

    public TransferDetailsSalesRebate() {
        addComponent(Clara.create(getClass().getResourceAsStream("/TransferContract/transferDetailsSalesRebate.xml"), this));
    }
}
