/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.logic;

import com.stpl.app.accountclose.common.CommonLogic;
import com.stpl.app.accountclose.dto.TreeDTO;
import com.stpl.app.customtreecontainer.CustomTreeContainer;
import com.vaadin.data.Container;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.asi.ui.extfilteringtable.paged.logic.PageTreeTableLogic;
import org.jboss.logging.Logger;

/**
 *
 * @author vigneshkanna
 */
public class TreeTableLogic extends PageTreeTableLogic {

    CommonLogic logic = new CommonLogic();
    boolean generate = false;
    boolean isLookUpFlag = false;
    String accMasSid = "0";
    int count;
    TreeDTO projSelDTO;
    String query;
    SuggestedAdjLookUpLogic saLoigic = new SuggestedAdjLookUpLogic();
    List<String> ccpIds = new ArrayList<String>();
    public static final Logger LOGGER = Logger.getLogger(TreeTableLogic.class);

    @Override
    public Map<Integer, Object> loadData(int start, int offset) {
        Map<Integer, Object> map = new HashMap<Integer, Object>();
        List<TreeDTO> list = new ArrayList<TreeDTO>();
        if (isLookUpFlag) {
            list = saLoigic.getData(getLastParent(), ccpIds, start, offset, accMasSid);
        } else {
            list = logic.getConfigureLoadData(getLastParent(), projSelDTO, query, start, offset);
        }
        LOGGER.info("available loadData" + list.size());
        int i = start;
        for (TreeDTO dto : list) {
            map.put(i, dto);
            i++;
        }
        return map;
    }

    public int getCount() {
        if (isLookUpFlag) {
            count = saLoigic.getCount(getLastParent(), ccpIds, accMasSid);
            return count;
        } else {
            if (generate) {
                count = logic.getConfigureCount(getLastParent(), projSelDTO, query);
                LOGGER.info("available count" + count);
                return count;
            }
        }
        return 0;
    }

    @Override
    public Object configureContainer(Object object, Container datasource) {
        TreeDTO dto = (TreeDTO) object;
        ((CustomTreeContainer<TreeDTO>) datasource).addBean(dto);
        if (dto.getParent() == 1) {
            ((CustomTreeContainer<TreeDTO>) datasource).setChildrenAllowed(dto, true);
        } else {
            ((CustomTreeContainer<TreeDTO>) datasource).setChildrenAllowed(dto, false);
        }
        return dto;
    }

    public boolean setData(TreeDTO projSelDTO, String query, boolean flag) {
        this.projSelDTO = projSelDTO;
        this.query = query;
        generate = flag;
        clearAll();
        setCurrentPage(1);
        return count == 0;
    }

    public boolean setLookUpData() {
        this.isLookUpFlag = true;
        clearAll();
        setCurrentPage(1);

        return true;
    }

    public List<String> getCcpIds() {
        return ccpIds;
    }

    public void setCcpIds(List<String> ccpIds) {
        this.ccpIds = ccpIds;
    }

    public String getAccMasSid() {
        return accMasSid;
    }

    public void setAccMasSid(String accMasSid) {
        this.accMasSid = accMasSid;
    }

    public LevelMap getLevelMap(int levelValue, int count, int levelIndex) {
        int page = getPageForItem(count + levelIndex + levelValue);
        int index = getItemIndex(count + levelIndex + levelValue);
        int start = getStartIndex(count + levelIndex, index);
        int end = getPageLength();
        LevelMap levelMap = new LevelMap(start, end, page, getPageLength(), index, getColumnIdToFilterMap());
        return levelMap;
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
}
