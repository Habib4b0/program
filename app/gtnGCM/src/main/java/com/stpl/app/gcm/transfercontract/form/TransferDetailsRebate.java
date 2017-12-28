/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.transfercontract.form;

import com.vaadin.v7.ui.VerticalLayout;
import org.vaadin.teemu.clara.Clara;

/**
 *
 * @author Harlin
 */
public class TransferDetailsRebate extends VerticalLayout {

    public TransferDetailsRebate() {
        addComponent(Clara.create(getClass().getResourceAsStream("/TransferContract/transferDetailsRebate.xml"), this));
    }
}
