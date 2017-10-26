/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.logic;

import com.stpl.app.accountclose.dto.TreeDTO;

import com.vaadin.data.Container;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.asi.ui.extfilteringtable.paged.logic.PageTreeTableLogic;
import org.asi.container.ExtTreeContainer;

/**
 *
 * @author alok.v
 */
public class CustomerProductTreeLogic extends PageTreeTableLogic {

    FixedDollarDataSelectionLogic logic = new FixedDollarDataSelectionLogic();
    boolean generate = false;
    int count;
    TreeDTO projSelDTO;

    @Override
    public Map<Integer, Object> loadData(int start, int offset) {
        Map<Integer, Object> map = new HashMap<>();
        projSelDTO.setStartIndex(start);
        projSelDTO.setOffSet(offset);
        List<TreeDTO> list = logic.getConfigureFdLoadData(getLastParent(), projSelDTO);
        int i = start;
        for (TreeDTO dto : list) {
            map.put(i, dto);
            i++;
        }
        return map;
    }

    public int getCount() {
        if (generate) {
             count = logic.getConfigureFdCount(getLastParent(), projSelDTO);
            return count;
        }
        return 0;
    }

    @Override
    public Object configureContainer(Object object, Container datasource) {
        TreeDTO dto = (TreeDTO) object;
        ((ExtTreeContainer<TreeDTO>) datasource).addBean(dto);
        if (dto.getParent() == 1) {
            ((ExtTreeContainer<TreeDTO>) datasource).setChildrenAllowed(dto, true);
        } else {
            ((ExtTreeContainer<TreeDTO>) datasource).setChildrenAllowed(dto, false);
        }
        return dto;
    }

    public boolean setData(TreeDTO projSelDTO) {
        this.projSelDTO = projSelDTO;
        generate = true;
        clearAll();
        setCurrentPage(1);
        return count == 0;
    }
}
