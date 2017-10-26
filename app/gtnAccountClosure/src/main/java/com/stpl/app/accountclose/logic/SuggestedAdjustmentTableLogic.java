/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.accountclose.logic;

import com.stpl.app.accountclose.common.CommonLogic;
import com.stpl.app.accountclose.dto.TreeDTO;
import org.asi.container.ExtTreeContainer;
import com.vaadin.data.Container;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.PageTreeTableLogic;

/**
 *
 * @author Maheshj
 */
public class SuggestedAdjustmentTableLogic extends PageTreeTableLogic  {
    SuggestedAdjLookUpLogic saLoigic=new SuggestedAdjLookUpLogic();
    CommonLogic logic = new CommonLogic();
     
    List<String> ccpIds=new ArrayList<String>();
    boolean generate = false;
    int count;
    TreeDTO projSelDTO;
    String query;

    @Override
    public Map<Integer, Object> loadData(int start, int offset) {
       Map<Integer, Object> map = new HashMap<Integer, Object>();
     List<TreeDTO> list = logic.getConfigureLoadData(getLastParent(), projSelDTO, query,start,offset);
        int i = start;
        for (TreeDTO dto : list) {
            map.put(i, dto);
            i++;
        }
        return map;
    }

    public int getCount() {
        int count=0;
        count=saLoigic.getCount(getLastParent(), ccpIds,StringUtils.EMPTY);
        return count;
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

    public boolean setData() {
        clearAll();
        setCurrentPage(1);
        return count == 0;
    }

    public List<String> getCcpIds() {
        return ccpIds;
    }

    public void setCcpIds(List<String> ccpIds) {
        this.ccpIds = ccpIds;
    }
    
}
