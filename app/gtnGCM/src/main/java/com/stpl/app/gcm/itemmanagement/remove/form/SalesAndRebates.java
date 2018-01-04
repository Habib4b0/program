/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.remove.form;

import com.stpl.app.gcm.globalchange.dto.SelectionDTO;
import com.stpl.app.gcm.itemmanagement.index.dto.ItemIndexDto;
import com.stpl.app.gcm.itemmanagement.itemabstract.form.summary.RebateSummary;
import com.stpl.app.gcm.itemmanagement.itemabstract.form.summary.SalesSummary;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Panel;
import com.vaadin.v7.ui.VerticalLayout;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author hazi.s
 */
public class SalesAndRebates extends VerticalLayout {

    private final VerticalLayout tradingPartnerSalesTableLayout = new VerticalLayout();
    private final VerticalLayout tradingPartnerRebatesTableLayout = new VerticalLayout();
    private SelectionDTO session;
    private List<ItemIndexDto> itemList;
    private final SalesSummary sales = new SalesSummary(itemList, session);
    private final RebateSummary rebate = new RebateSummary(itemList, session);
    /**
     * The Constant LOGGER.
     */
    public static final Logger LOGGER = LoggerFactory.getLogger(SalesAndRebates.class);

    public SalesAndRebates(List<ItemIndexDto> itemList, SelectionDTO session) {
        this.session = session;
        this.itemList = itemList;
    }

    public Component getContent(List<ItemIndexDto> selecteditemList, final SelectionDTO selection) {
        this.session = selection;
        this.itemList = selecteditemList;
        VerticalLayout layout = new VerticalLayout();
        layout.addComponent(tradingPartnerSalesTableLayout);
        layout.addComponent(tradingPartnerRebatesTableLayout);
        layout.setMargin(true);
        layout.setSpacing(true);
        Panel panel = new Panel();
        panel.setContent(layout);
        loadTable();
        return panel;
    }

    public void loadTable() {
        tradingPartnerSalesTableLayout.removeAllComponents();
        tradingPartnerRebatesTableLayout.removeAllComponents();
        tradingPartnerSalesTableLayout.addComponent(sales.getContent(itemList, session));
        tradingPartnerRebatesTableLayout.addComponent(rebate.getContent(itemList, session));
    }
}
