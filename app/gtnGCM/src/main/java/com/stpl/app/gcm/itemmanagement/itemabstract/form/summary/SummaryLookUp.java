/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.itemabstract.form.summary;

import com.stpl.app.gcm.util.StringConstantsUtil;
import com.stpl.app.gcm.common.CommonLogic;
import com.stpl.app.gcm.globalchange.dto.SelectionDTO;
import com.stpl.app.gcm.itemmanagement.index.dto.ItemIndexDto;
import com.stpl.app.gcm.itemmanagement.index.util.ConstantsUtil;
import com.stpl.app.gcm.itemmanagement.itemabstract.form.AbstractItemDetails;
import com.stpl.app.gcm.itemmanagement.remove.form.SalesAndRebates;
import com.stpl.app.gcm.security.StplSecurity;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.v7.data.util.BeanItemContainer;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author maheshj
 */
public class SummaryLookUp extends CustomComponent {

    private SelectionDTO selection;
    private final TabSheet mainTab = new TabSheet();
    private int tabPosition = 0;
    private Summary sales;
    private List<ItemIndexDto> itemList;
    private Summary rebate;
    private SalesAndRebates salesAndRebate;
    private AbstractItemDetails itemDetails;
    /**
     * The Constant LOGGER.
     */
    public static final Logger LOGGER = LoggerFactory.getLogger(SummaryLookUp.class);
    private final StplSecurity stplSecurity = new StplSecurity();
    private Map<String, AppPermission> functionHM = new HashMap<>();

    public SummaryLookUp(List<ItemIndexDto> itemList, SelectionDTO selection) {
    }

    public Component getContent(List<ItemIndexDto> itemListSummary, SelectionDTO selection) {
        LOGGER.debug("getContent method starts");

        this.selection = selection;
        this.itemList = itemListSummary == null ? itemListSummary : new ArrayList<>(itemListSummary);
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
            mainTab.addTab(sales.getContent(itemList, selection), "Sales", null, 1);
            mainTab.addTab(rebate.getContent(itemList, selection), "Rebates", null, NumericConstants.TWO);
            mainTab.addTab(salesAndRebate.getContent(itemList, selection), "Sales and Rebates", null, NumericConstants.THREE);
            configureFields();
        } catch (Exception ex) {
            LOGGER.error("",ex);
        }
        setCompositionRoot(panel);
        return panel;
    }

    public void configureFields() {
        mainTab.addSelectedTabChangeListener(new TabSheet.SelectedTabChangeListener() {
            @Override
            public void selectedTabChange(TabSheet.SelectedTabChangeEvent event) {
                final TabSheet.Tab tab = (TabSheet.Tab) event.getTabSheet().getTab(event.getTabSheet().getSelectedTab());
                tabPosition = event.getTabSheet().getTabPosition(tab);
                if (tabPosition == 1) {
                    sales.loadResultTable();
                } else if (tabPosition == NumericConstants.TWO) {
                    rebate.loadResultTable();
                } else if (tabPosition == NumericConstants.THREE) {
                    salesAndRebate.loadTable();
                }
            }
        });
    }

    

    public void loadSummaryTable() {
        itemDetails.loadData();
    }

    public BeanItemContainer getItemDetails(Boolean isTransfer) {
        return itemDetails.getIemDetailsContainer(isTransfer);
    }
    /**
     * ConfigureBtnFor security
     * 
     * @param screenName 
     */
    public void configureSecurityPermissions(String screenName) {
        try {
            if (ConstantsUtil.DELETE.equals(screenName)) {
                functionHM = stplSecurity.getBusinessFunctionPermission(String.valueOf(selection.getUserId()), StringConstantsUtil.GCM_ITEM_MANAGEMENT, "Item Remove", StringConstantsUtil.SUMMARY_FIELD);
                itemDetails.getCancelremove().setVisible(CommonLogic.isButtonVisibleAccess(StringConstantsUtil.CANCEL_REMOVE, functionHM));
            } else if (ConstantsUtil.EDIT.equals(screenName)) {
                functionHM = stplSecurity.getBusinessFunctionPermission(String.valueOf(selection.getUserId()), StringConstantsUtil.GCM_ITEM_MANAGEMENT, "Item Update", "Summary Tab");
                itemDetails.getCancelremove().setVisible(CommonLogic.isButtonVisibleAccess(StringConstantsUtil.CANCEL_REMOVE, functionHM));
                itemDetails.getCancelremoveForTransfer().setVisible(CommonLogic.isButtonVisibleAccess(StringConstantsUtil.CANCELREMOVE_FOR_TRANSFER, functionHM));
            } else if (ConstantsUtil.TRANSFER.equals(screenName)) {
                functionHM = stplSecurity.getBusinessFunctionPermission(String.valueOf(selection.getUserId()), StringConstantsUtil.GCM_ITEM_MANAGEMENT, "Item Transfer", StringConstantsUtil.SUMMARY_FIELD);
                itemDetails.getCancelremove().setVisible(CommonLogic.isButtonVisibleAccess(StringConstantsUtil.CANCEL_REMOVE, functionHM));
                itemDetails.getCancelremoveForTransfer().setVisible(CommonLogic.isButtonVisibleAccess(StringConstantsUtil.CANCELREMOVE_FOR_TRANSFER, functionHM));
            } else if (ConstantsUtil.PROJECTIONTRANSFER.equals(screenName)) {
                functionHM = stplSecurity.getBusinessFunctionPermission(String.valueOf(selection.getUserId()), StringConstantsUtil.GCM_ITEM_MANAGEMENT, "Projection details Transfer-Item", StringConstantsUtil.SUMMARY_FIELD);
                itemDetails.getCancelremove().setVisible(CommonLogic.isButtonVisibleAccess(StringConstantsUtil.CANCEL_REMOVE, functionHM));
                itemDetails.getCancelremoveForTransfer().setVisible(CommonLogic.isButtonVisibleAccess(StringConstantsUtil.CANCELREMOVE_FOR_TRANSFER, functionHM));
            }
            setFunctionHM(functionHM);
        } catch (Exception ex) {
            LOGGER.error("",ex);
        }
    }

    public Map<String, AppPermission> getFunctionHM() {
        return functionHM;
    }

    public void setFunctionHM(Map<String, AppPermission> functionHM) {
        this.functionHM = functionHM;
    }
    
}
