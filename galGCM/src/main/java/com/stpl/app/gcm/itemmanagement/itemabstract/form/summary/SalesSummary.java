/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.itemabstract.form.summary;

import com.stpl.app.gcm.globalchange.dto.SelectionDTO;
import com.stpl.app.gcm.itemmanagement.index.dto.ItemIndexDto;
import com.stpl.app.gcm.itemmanagement.index.util.ConstantsUtil;
import com.stpl.app.gcm.util.Constants;
import com.stpl.app.gcm.util.CustomExcelExport;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Srithar
 */
public class SalesSummary extends Summary {

    public SalesSummary(List<ItemIndexDto> itemList, SelectionDTO selection) {
        super(itemList, selection);
    }

    /**
     *
     */
    @Override
    public void setMainPanelCaption() {
        if (selection.getButtonMode().equals(ConstantsUtil.EDIT)) {
            mainPanel.setCaption("Update Details - Sales");
        } else if (selection.getButtonMode().equals(ConstantsUtil.DELETE)) {
            mainPanel.setCaption("Delete Details - Sales");
        } else if (selection.getButtonMode().equals(ConstantsUtil.TRANSFER)) {
            mainPanel.setCaption("Transfer Details - Sales");
        }
        else if (selection.getButtonMode().equals(ConstantsUtil.PROJECTIONTRANSFER)) {
            mainPanel.setCaption("Transfer Details - Sales Projection");
        }
    }

    /**
     * Excel button logic.
     *
     * @param event the event
     */
    @UiHandler("excelBtn")
    public void excelButtonLogic(Button.ClickEvent event) {
        configureExcelResultTable();
        loadExcelResultTable();
        exportPeriodViewTable.setRefresh(Boolean.TRUE);
        Map<String, String> formatter = new HashMap<String, String>();
        formatter.put("currencyNoDecimal", "Sales");
        formatter.put("unitOneDecimal", "Units");
        VaadinSession.getCurrent().setAttribute(ConstantsUtils.EXCEL_CLOSE, Constants.TRUE);
        CustomExcelExport exp = new CustomExcelExport(new ExtCustomTableHolder(exportPeriodViewTable), "Sales Projection", "Sales Projection", "Sales Projection.xls", false, formatter);
        exp.export();
        tradingPartnerSalesTableLayout.removeComponent(exportPeriodViewTable);
    }
    
    @Override
    public boolean isSales() {
        return true;
    }

}
