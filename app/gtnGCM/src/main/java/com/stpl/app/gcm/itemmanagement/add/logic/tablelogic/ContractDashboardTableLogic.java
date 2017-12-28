/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.add.logic.tablelogic;

import org.asi.container.ExtTreeContainer;
import com.stpl.app.gcm.globalchange.dto.SelectionDTO;
import com.stpl.app.gcm.itemmanagement.add.dto.ContractDashboardDTO;
import com.stpl.app.gcm.itemmanagement.add.logic.SummaryLogic;
import com.vaadin.data.Container;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.asi.ui.extfilteringtable.paged.logic.PageTreeTableLogic;

/**
 *
 * @author porchelvi.g
 */
public class ContractDashboardTableLogic extends PageTreeTableLogic {

    boolean isGenerated = true;
    SummaryLogic logic = new SummaryLogic();
    SelectionDTO selection = new SelectionDTO();
    ContractDashboardDTO idDTO = new ContractDashboardDTO();

    @Override
    public int getCount() {
        if (!isGenerated) {
            return logic.getConfigureCount(lastParent, selection, idDTO);
        }
        return 0;
    }

    @Override
    public Map<Integer, Object> loadData(int start, int offset) {
        Map<Integer, Object> map = new HashMap<>();
        selection.setStartIndex(start);
        selection.setOffSet(offset);
        List<ContractDashboardDTO> list = logic.getConfigureLoadData(getLastParent(), selection, idDTO);
        int i = start;
        for (ContractDashboardDTO dto : list) {
            map.put(i, dto);
            i++;
        }
        return map;
    }

    /**
     * Container
     *
     * @param object
     * @param datasource
     * @return Object
     */
    @Override
    public Object configureContainer(Object object, Container datasource) {
        ContractDashboardDTO dto = (ContractDashboardDTO) object;
        ((ExtTreeContainer<ContractDashboardDTO>) datasource).addBean(dto);
        if (dto.getParent() == 1) {
            ((ExtTreeContainer<ContractDashboardDTO>) datasource).setChildrenAllowed(dto, true);
        } else {
            ((ExtTreeContainer<ContractDashboardDTO>) datasource).setChildrenAllowed(dto, false);
        }
        return dto;
    }

    /**
     * Setting up the datas which we pass to the Logic
     *
     * @param binderDto
     * @return boolean - count is 0 or not
     */
    public boolean loadSetData(SelectionDTO selection, ContractDashboardDTO idDTO) {
        isGenerated = selection.isReset();
        this.idDTO = idDTO;
        clearAll();
        setCurrentPage(1);
        return getRecordCount() != 0;
    }
}
