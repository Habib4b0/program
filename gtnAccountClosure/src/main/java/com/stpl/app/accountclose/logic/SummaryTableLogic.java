/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.logic;

import com.stpl.app.accountclose.dto.BaseRateSummaryDTO;
import org.asi.container.ExtTreeContainer;
import com.vaadin.data.Container;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.asi.ui.extfilteringtable.paged.logic.PageTreeTableLogic;

/**
 *
 * @author vigneshkanna
 */
public class SummaryTableLogic extends PageTreeTableLogic {

    BaseRateCalculationLogic logic = new BaseRateCalculationLogic();
    boolean generate = false;
    int count;
    BaseRateSummaryDTO projSelDTO;
    boolean summary;

    @Override
    public Map<Integer, Object> loadData(int start, int offset) {
        Map<Integer, Object> map = new HashMap<Integer, Object>();
        List<BaseRateSummaryDTO> list = new ArrayList<BaseRateSummaryDTO>();
        projSelDTO.setStartIndex(start);
        projSelDTO.setOffSet(offset);
        if (summary) {
            list = logic.getConfigureLoadData(getLastParent(), projSelDTO);
        } else {
            list = logic.getTransData(projSelDTO);
        }
        int i = start;
        for (BaseRateSummaryDTO dto : list) {
            map.put(i, dto);
            i++;
        }
        return map;
    }

    public int getCount() {
        if (generate) {
            if (summary) {
                count = logic.getConfigureCount(getLastParent(), projSelDTO);
            } else {
                count = logic.getTransCount(projSelDTO);
            }
            return count;
        }
        return 0;
    }

    @Override
    public Object configureContainer(Object object, Container datasource) {
        BaseRateSummaryDTO dto = (BaseRateSummaryDTO) object;
        ((ExtTreeContainer<BaseRateSummaryDTO>) datasource).addBean(dto);
        if (dto.getParent() == 1) {
            ((ExtTreeContainer<BaseRateSummaryDTO>) datasource).setChildrenAllowed(dto, true);
        } else {
            ((ExtTreeContainer<BaseRateSummaryDTO>) datasource).setChildrenAllowed(dto, false);
        }
        return dto;
    }

    public boolean setData(BaseRateSummaryDTO projSelDTO, boolean summary) {
        this.projSelDTO = projSelDTO;
        this.summary = summary;
        generate = true;
        clearAll();
        setCurrentPage(1);
        return count == 0;
    }

    public void forRefresh(Set<String> finalHirarechyNo) {
        addExpandedTreeDataFetchable(finalHirarechyNo);
    }

    public Object getParent(String hierarchyNo) {
        return getExpandedTreeValue(hierarchyNo);

    }

}
