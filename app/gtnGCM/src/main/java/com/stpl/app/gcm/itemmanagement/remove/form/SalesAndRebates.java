/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.remove.form;

import com.stpl.app.gcm.globalchange.dto.SelectionDTO;
import com.stpl.app.gcm.itemmanagement.index.dto.ItemIndexDto;
import com.stpl.app.gcm.itemmanagement.itemabstract.form.summary.RebateSummary;
import com.stpl.app.gcm.itemmanagement.itemabstract.form.summary.SalesSummary;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import java.util.List;
import org.jboss.logging.Logger;

/**
 *
 * @author hazi.s
 */
public class SalesAndRebates extends VerticalLayout {

    VerticalLayout tradingPartnerSalesTableLayout = new VerticalLayout();
    VerticalLayout tradingPartnerRebatesTableLayout = new VerticalLayout();
    SelectionDTO session;
    boolean isLoad;
    ComboBox frequency;
    List<ItemIndexDto> itemList;
    SalesSummary sales = new SalesSummary(itemList, session);
    RebateSummary rebate = new RebateSummary(itemList, session);
    /**
     * The Constant LOGGER.
     */
    public static final Logger LOGGER = Logger.getLogger(SalesAndRebates.class);

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
