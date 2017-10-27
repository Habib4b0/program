/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.itemabstract.logic.abstracttablelogic;

import org.asi.container.ExtTreeContainer;
import com.stpl.app.gcm.globalchange.dto.SelectionDTO;
import com.stpl.app.gcm.itemmanagement.index.dto.ItemIndexDto;
import com.stpl.app.gcm.itemmanagement.index.util.ConstantsUtil;
import com.stpl.app.gcm.itemmanagement.itemabstract.dto.AbstractSummaryDTO;
import com.stpl.app.gcm.itemmanagement.remove.logic.RemoveItemLogic;
import com.stpl.app.gcm.tp.dto.TabSelectionDTO;
import com.vaadin.data.Container;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.asi.ui.extfilteringtable.paged.logic.PageTreeTableLogic;

/**
 *
 * @author Srithar
 */
public class SummaryTableLogic extends PageTreeTableLogic {

    SelectionDTO selection;
    TabSelectionDTO tabSelectionDTO;
    boolean firstGenerated = false;
    RemoveItemLogic logic = new RemoveItemLogic();
    boolean isSales;

    @Override
    public int getCount() {
        int count = 0;

        if (firstGenerated) {
            if (isSales) {
                count = logic.getLevelCount(getLastParent(), tabSelectionDTO, selection);
            } else {
                count = logic.getLevelCountForRebate(getLastParent(), tabSelectionDTO, selection);
            }
        }
        return count;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        AbstractSummaryDTO itemId = (AbstractSummaryDTO) object;
        ((ExtTreeContainer<AbstractSummaryDTO>) container).addBean(itemId);
        if (itemId != null && itemId.getParent() == 1) {
            ((ExtTreeContainer<AbstractSummaryDTO>) container).setChildrenAllowed(itemId, true);
        } else {
            ((ExtTreeContainer<AbstractSummaryDTO>) container).setChildrenAllowed(itemId, false);
        }
        return itemId;
    }

    @Override
    public Map<Integer, Object> loadData(int start, int offset) {
        Map<Integer, Object> map = new HashMap<>();
        List<AbstractSummaryDTO> list;
        if (isSales) {
            list = logic.getConfiguredSalesTabResults(getLastParent(), tabSelectionDTO, false);
        } else {
            list = logic.getConfiguredRebateTabResults(getLastParent(), tabSelectionDTO, false);
        }
        int i = start;
        for (AbstractSummaryDTO dto : list) {
            map.put(i, dto);
            i++;
        }
        return map;
    }

    public void setProjectionResultsData(List<ItemIndexDto> itemList, SelectionDTO selection, boolean isSales) {
        this.isSales = isSales;
        this.tabSelectionDTO = selection.getTabSelection();
        tabSelectionDTO.setItemList(itemList);
        if (selection.getButtonMode().equals(ConstantsUtil.TRANSFER) || selection.getButtonMode().equals(ConstantsUtil.PROJECTIONTRANSFER)) {
            this.tabSelectionDTO.setOperation(ConstantsUtil.TRANSFER_SUMMARY);
        } else {
            this.tabSelectionDTO.setOperation(ConstantsUtil.SUMMARY);
        }
        firstGenerated = true;
        this.selection = selection;
        clearAll();
        setCurrentPage(1);
    }
}
