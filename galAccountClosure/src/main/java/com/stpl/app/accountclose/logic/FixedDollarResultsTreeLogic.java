/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.logic;

import com.stpl.app.accountclose.dto.FixedDollarDTO;

import com.vaadin.data.Container;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.asi.container.ExtTreeContainer;
import org.asi.ui.extfilteringtable.paged.logic.PageTreeTableLogic;

/**
 *
 * @author alok.v
 */
public class FixedDollarResultsTreeLogic extends PageTreeTableLogic {

    FixedDollarDataSelectionLogic logic = new FixedDollarDataSelectionLogic();
    boolean generate = false;
    int count;
    FixedDollarDTO fixedDollarDTO;

    @Override
    public Map<Integer, Object> loadData(int start, int offset) {
        Map<Integer, Object> map = new HashMap<Integer, Object>();
        fixedDollarDTO.setStartIndex(start);
        fixedDollarDTO.setOffSet(offset);
        List<FixedDollarDTO> list = logic.getConfigureFixedDollarLoadData(getLastParent(), fixedDollarDTO);
        int i = start;
        for (FixedDollarDTO dto : list) {
            map.put(i, dto);
            i++;
        }
        return map;
    }

    public int getCount() {
        if (generate) {
            count = logic.getConfigureFdResultCount(getLastParent(), fixedDollarDTO);
            return count;
        }
        return 0;
    }

    @Override
    public Object configureContainer(Object object, Container datasource) {
        FixedDollarDTO dto = (FixedDollarDTO) object;
        ((ExtTreeContainer<FixedDollarDTO>) datasource).addBean(dto);
        if (dto.getParent() == 1) {
            ((ExtTreeContainer<FixedDollarDTO>) datasource).setChildrenAllowed(dto, true);
        } else {
            ((ExtTreeContainer<FixedDollarDTO>) datasource).setChildrenAllowed(dto, false);
        }
        return dto;
    }

    public boolean setData(FixedDollarDTO fixedDollarDTO) {
        this.fixedDollarDTO = fixedDollarDTO;
        generate = true;
        clearAll();
        setCurrentPage(1);
        return count == 0;
    }

    public int getPageForItem(int pos) {
        int curPage = ((pos - 2) / getPageLength()) + 1;
        return curPage;
    }

    public int getItemIndex(int pos) {
        int index = (pos - 2) % getPageLength();
        return index;
    }

    public int getStartIndex(int count, int index) {
        int start = count - index;
        return start;
    }

    @Override
    protected void createCurrentPageStart() {
        setCurrentPageProgress(true);
        setRefresh(Boolean.FALSE);
    }

    @Override
    protected void createCurrentPageEnd() {
        setCurrentPageProgress(false);
        setRefresh(Boolean.TRUE);
    }

    @Override
    protected void expandCollapseStart(boolean isExpand) {
        setExpandCollapseProgress(true);
    }

    @Override
    protected void expandCollapseEnd(boolean isExpand) {
        setExpandCollapseProgress(false);
    }

}
