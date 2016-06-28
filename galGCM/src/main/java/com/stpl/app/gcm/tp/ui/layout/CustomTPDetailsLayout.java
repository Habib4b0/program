/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.tp.ui.layout;

import com.vaadin.ui.VerticalLayout;

/**
 *
 * @author sriram
 */
public abstract class CustomTPDetailsLayout extends VerticalLayout {

    public abstract boolean isCurrentContractRefresh();

    public abstract boolean isTransferContractRefresh();

    public abstract void setCurrentContractRefresh(boolean currentContractRefresh);

    public abstract void setTransferContractRefresh(boolean transferContractRefresh);

}
