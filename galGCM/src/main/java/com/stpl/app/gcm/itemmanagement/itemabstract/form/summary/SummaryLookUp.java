/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.itemabstract.form.summary;

import com.stpl.app.gcm.globalchange.dto.SelectionDTO;
import com.stpl.app.gcm.itemmanagement.index.dto.ItemIndexDto;
import com.stpl.app.gcm.itemmanagement.itemabstract.form.AbstractItemDetails;
import com.stpl.app.gcm.itemmanagement.remove.form.SalesAndRebates;
import com.stpl.app.gcm.security.StplSecurity;
import com.stpl.app.gcm.util.Constants;
import com.stpl.app.security.permission.model.AppPermission;
import com.vaadin.data.fieldgroup.FieldGroup;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TabSheet;
import com.vaadin.ui.themes.ValoTheme;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author maheshj
 */
public class SummaryLookUp extends CustomComponent {

    SelectionDTO selection;
    TabSheet mainTab = new TabSheet();
    int tabPosition = 0;
    List<Integer> tabList = new ArrayList<Integer>();
    Summary sales;
    List<ItemIndexDto> itemList;
    Summary rebate;
    SalesAndRebates salesAndRebate;
    AbstractItemDetails itemDetails;
    /**
     * The Constant LOGGER.
     */
    public static final Logger LOGGER = Logger.getLogger(SummaryLookUp.class);
    final StplSecurity stplSecurity = new StplSecurity();
    Map<String, AppPermission> functionHM = new HashMap<String, AppPermission>();
    private String userId = VaadinSession.getCurrent().getAttribute(Constants.USER_ID).toString();

    public SummaryLookUp(List<ItemIndexDto> itemList, SelectionDTO selection) {
    }

    public Component getContent(List<ItemIndexDto> itemList, SelectionDTO selection) {
        LOGGER.info("getContent method starts");

        this.selection = selection;
        this.itemList = itemList;
        Panel panel = new Panel();
        panel.setCaption(StringUtils.EMPTY);
        panel.setContent(mainTab);
        try {
            mainTab.addStyleName(ValoTheme.TABSHEET_FRAMED);
            mainTab.addStyleName(ValoTheme.TABSHEET_PADDED_TABBAR);
            itemDetails = new AbstractItemDetails(selection);
            sales = new SalesSummary(itemList, selection);
            rebate = new RebateSummary(itemList, selection);
            salesAndRebate = new SalesAndRebates(itemList, selection);
            mainTab.addTab(itemDetails, "Item Details", null, 0);
            mainTab.addTab(sales, "Sales", null, 1);
            mainTab.addTab(rebate, "Rebates", null, 2);
            mainTab.addTab(salesAndRebate, "Sales and Rebates", null, 3);
            configureFields();
        } catch (Exception ex) {
          LOGGER.error(ex);
        }
        setCompositionRoot(panel);
        return panel;
    }

    public void configureFields() {
        mainTab.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {
            public void selectedTabChange(TabSheet.SelectedTabChangeEvent event) {
                final TabSheet.Tab tab = (TabSheet.Tab) event.getTabSheet().getTab(event.getTabSheet().getSelectedTab());
                tabPosition = event.getTabSheet().getTabPosition(tab);
                if (!tabList.contains(tabPosition)) {
                    tabLazyLoad(tabPosition);
                }
                if (tabPosition == 1) {
                    sales.loadResultTable();
                } else if (tabPosition == 2) {
                    rebate.loadResultTable();
                } else if (tabPosition == 3) {
                    sales.loadResultTable();
                    rebate.loadResultTable();
                }
            }
        });
    }

    private void tabLazyLoad(int tabPosition) {
        if (tabPosition == 1) {
            if (selection.isIsIFP()) {
            } else {
                mainTab.replaceComponent(sales, sales.getContent(itemList, selection));
            }
            tabList.add(tabPosition);
        }
        if (tabPosition == 2) {
            if (selection.isIsIFP()) {
            } else {
                mainTab.replaceComponent(rebate, rebate.getContent(itemList, selection));
            }
            tabList.add(tabPosition);
        }
        if (tabPosition == 3) {
            if (selection.isIsIFP()) {
            } else {
                mainTab.replaceComponent(salesAndRebate, salesAndRebate.getContent(itemList, selection));
            }
            tabList.add(tabPosition);
        }
    }

    public void loadSummaryTable() throws FieldGroup.CommitException {
            itemDetails.loadData();
        }

    public BeanItemContainer getItemDetails(Boolean isTransfer) {
        return itemDetails.getIemDetailsContainer(isTransfer);
    }
}
