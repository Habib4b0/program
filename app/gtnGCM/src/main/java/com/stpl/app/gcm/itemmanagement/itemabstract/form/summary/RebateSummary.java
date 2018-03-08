/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.itemabstract.form.summary;

import com.stpl.app.gcm.globalchange.dto.SelectionDTO;
import com.stpl.app.gcm.itemmanagement.index.dto.ItemIndexDto;
import com.stpl.app.gcm.itemmanagement.index.util.ConstantsUtil;
import com.stpl.app.gcm.itemmanagement.itemabstract.dto.AbstractSummaryDTO;
import com.stpl.app.gcm.itemmanagement.remove.logic.RemoveItemLogic;
import com.stpl.app.gcm.util.Constants;
import com.stpl.app.gcm.util.CustomExcelExport;
import com.stpl.app.gcm.util.ConstantsUtils;
import com.stpl.ifs.util.ExtCustomTableHolder;
import com.stpl.ifs.util.constants.BooleanConstant;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vaadin.teemu.clara.binder.annotation.UiHandler;

/**
 *
 * @author Srithar
 */
public class RebateSummary extends Summary {
 public static final Logger LOGGER = LoggerFactory.getLogger(RebateSummary.class);
 
    public RebateSummary(List<ItemIndexDto> itemList, SelectionDTO selection) {
        super(itemList, selection);
    }

    @Override
    public void setMainPanelCaption() {
        if (selection.getButtonMode().equals(ConstantsUtil.EDIT)) {
            mainPanel.setCaption("Update Details - Rebates");
        } else if (selection.getButtonMode().equals(ConstantsUtil.DELETE)) {
            mainPanel.setCaption("Delete Details - Rebates");
        } else if (selection.getButtonMode().equals(ConstantsUtil.TRANSFER)) {
            mainPanel.setCaption("Transfer Details - Rebates");
        }
    }

    @UiHandler("excelBtn")
    public void excelButtonLogic(Button.ClickEvent event) {
        configureExcelResultTable();
        loadExcelResultTable();
        exportPeriodViewTable.setRefresh(BooleanConstant.getTrueFlag());
        Map<String, String> formatter = new HashMap<>();
        formatter.put("currencyNoDecimal", "Amount");
        formatter.put("perTwoDecimal", "Rate");
        VaadinSession.getCurrent().setAttribute(ConstantsUtils.EXCEL_CLOSE, Constants.TRUE);
        CustomExcelExport exp = new CustomExcelExport(new ExtCustomTableHolder(exportPeriodViewTable), "Rebate Projection", "Rebate Projection", "Rebate_Projection.xls", false, formatter);
        exp.export();
        tradingPartnerSalesTableLayout.removeComponent(exportPeriodViewTable);
    }

    @SuppressWarnings("serial")
 @Override
    public void loadExcelResultTable() {
        excelResultBean.removeAllItems();
        RemoveItemLogic.getIdAndForecastingType(selectionDTO, selection);
        if (selection.getButtonMode().equals(ConstantsUtil.TRANSFER) || selection.getButtonMode().equals(ConstantsUtil.PROJECTIONTRANSFER)) {
            this.selectionDTO.setOperation(ConstantsUtil.TRANSFER_SUMMARY);
        } else {
            this.selectionDTO.setOperation(ConstantsUtil.SUMMARY);
        }

        List<AbstractSummaryDTO> resultList = logic.getConfiguredRebateTabResults(new Object(), selectionDTO, true);
        LOGGER.debug("resultList--->>" + resultList);
        loadDataToContainer(resultList, null);
    }

    public void loadDataToContainerForRebate(List<AbstractSummaryDTO> resultList, Object parentId) {
        for (AbstractSummaryDTO dto : resultList) {
            excelResultBean.addBean(dto);
            if (parentId != null) {
                excelResultBean.setParent(dto, parentId);
            }
            if (dto.getParent() == 1) {
                excelResultBean.setChildrenAllowed(dto, true);
                addLowerLevelsForExport(dto);
            } else {
                excelResultBean.setChildrenAllowed(dto, false);
            }
        }
    }

 @Override
    public void addLowerLevelsForExport(Object id) {
        List<AbstractSummaryDTO> resultList = logic.getConfiguredRebateTabResults(id, selectionDTO, true);
        loadDataToContainer(resultList, id);
    }

    @Override
    public boolean isSales() {
        return false;
    }
}