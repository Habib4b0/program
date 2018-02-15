/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.tp.ui.form;

import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.vaadin.v7.ui.VerticalLayout;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.Clara;
import org.vaadin.teemu.clara.binder.annotation.UiField;

/**
 *
 * @author Lokeshwari
 */
public class SalesRebateTab extends VerticalLayout {

    @UiField("tradingPartnerSalesTableLayout")
    private VerticalLayout tradingPartnerSalesTableLayout;
    @UiField("tradingPartnerRebatesTableLayout")
    private VerticalLayout tradingPartnerRebatesTableLayout;
    private final SessionDTO session;
    private final boolean isLoad;
    /**
     * The Constant LOGGER.
     */
    public static final Logger LOGGER = LoggerFactory.getLogger(SalesRebateTab.class);

    public SalesRebateTab(SessionDTO session, boolean isLoad) {
        this.session = session;
        this.isLoad = isLoad;
        getContent();
    }

    public void getContent() {
        LOGGER.debug("getContent method starts");

        addComponent(Clara.create(getClass().getResourceAsStream("/TradingPartner/salesAndRebatesTab.xml"), this));
        configureFields();

        LOGGER.debug("getContent method ends");

    }

    public void configureFields() {
        tradingPartnerSalesTableLayout.addComponent(new SalesTab(session, false));
        tradingPartnerRebatesTableLayout.addComponent(new RebateTab(session, false));
    }

    public void loadTable(int projectionId) {
        tradingPartnerSalesTableLayout.removeAllComponents();
        tradingPartnerRebatesTableLayout.removeAllComponents();

        SalesTab sales = new SalesTab(session, isLoad);
        RebateTab rebate = new RebateTab(session, isLoad);

        tradingPartnerSalesTableLayout.addComponent(sales);
        tradingPartnerRebatesTableLayout.addComponent(rebate);

        sales.loadResultTable(session, projectionId);
        rebate.loadResultTable(session, projectionId);
    }

    public void enter() {
        return;
    }

}
