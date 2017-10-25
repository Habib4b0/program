/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.gtnbalancereport.logic.tableLogic;

import com.stpl.app.accountclose.gtnbalancereport.dto.BalanceReportDTO;
import com.stpl.app.accountclose.gtnbalancereport.logic.HistoryTabLogic;
import com.stpl.app.accountclose.sessionutils.SessionDTO;
import com.stpl.app.accountclose.utils.Constants;
import com.vaadin.data.Container;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtTreeContainer;
import org.asi.ui.extfilteringtable.paged.logic.PageTreeTableLogic;
import org.jboss.logging.Logger;

/**
 *
 * @author santanukumar
 */
public class HistoryTabTableLogic extends PageTreeTableLogic {

    boolean firstGenerated = false;
    SessionDTO session;
    BalanceReportDTO balanceReportDTO;
    List<String> selectedVariables;
    private HistoryTabLogic historyLogic = new HistoryTabLogic();
    public static final Logger LOGGER = Logger.getLogger(HistoryTabTableLogic.class);

    @Override
    public Map<Integer, Object> loadData(int start, int offset) {
        Map<Integer, Object> map = new HashMap<Integer, Object>();
        List<BalanceReportDTO> list = new ArrayList<BalanceReportDTO>();
        if (firstGenerated) {
            list = historyLogic.getBalanceReportResults(getLastParent(), start, offset, session, balanceReportDTO, selectedVariables, true);

        }
        int i = start;
        for (BalanceReportDTO list1 : list) {
            map.put(i, list1);
            i++;
        }
        return map;
    }

    @Override
    public int getCount() {
        int count = 0;
        if (firstGenerated) {
            count = historyLogic.getBalanceReportCount(getLastParent(), session, balanceReportDTO, selectedVariables, true);
        }
        return count;
    }

    @Override
    public Object configureContainer(Object object, Container datasource) {
        BalanceReportDTO dto = (BalanceReportDTO) object;
        ((ExtTreeContainer<BalanceReportDTO>) datasource).addBean(dto);
        if (dto.getParent() == 1) {
            ((ExtTreeContainer<BalanceReportDTO>) datasource).setChildrenAllowed(dto, true);
        } else {
            ((ExtTreeContainer<BalanceReportDTO>) datasource).setChildrenAllowed(dto, false);
        }
        return dto;
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

    public void setData(SessionDTO session, BalanceReportDTO balanceReportDTO, List<String> selectedVariables, boolean flag) {
        this.session = session;
        this.balanceReportDTO = balanceReportDTO;
        this.selectedVariables = selectedVariables;
        firstGenerated = !flag;
        clearAll();
        setCurrentPage(1);

    }

    public void loadExpandData(String view, String filterType, String filterValue) {
        try {
            BalanceReportDTO queryDto = (BalanceReportDTO) balanceReportDTO.clone();
            queryDto = setLevelSystemId(view, filterType, filterValue, queryDto);
            recursivelyLoadExpandData(new Object(), StringUtils.EMPTY, queryDto, queryDto.getLevelNo());
            setRecordCount(getCalculatedTotalRecordCount());
            setCurrentPage(getTotalAmountOfPages());
        } catch (CloneNotSupportedException ex) {
            LOGGER.error(ex);
        }
    }

    protected void recursivelyLoadExpandData(Object parentId, String treeLevel, BalanceReportDTO queryDto, int expandLevelNo) {

        int count = historyLogic.getExpandLevelCount(parentId, session, queryDto, selectedVariables, false);
        LevelMap levelMap = new LevelMap(count, getColumnIdToFilterMap());
        addlevelMap(treeLevel, levelMap);
        int levelNo = 0;
        if (parentId instanceof BalanceReportDTO) {
            levelNo = ((BalanceReportDTO) parentId).getLevelNo();
        }
        if ((expandLevelNo) > (levelNo + 1)) {
            List<BalanceReportDTO> list = historyLogic.getExpandLevelResult(parentId, 0, count, session, queryDto, selectedVariables, false);
            if (list != null && !list.isEmpty()) {
                int size = list.size();
                int index = 1;
                for (int j = 0; j < size; j++) {
                    String customTreeLevel = treeLevel + (index + j) + ".";
                    BalanceReportDTO dto = (BalanceReportDTO) list.get(j);
                    addExpandedTreeList(customTreeLevel, dto);
                    recursivelyLoadExpandData(dto, customTreeLevel, queryDto, expandLevelNo);
                }
            }
        }
    }

    private BalanceReportDTO setLevelSystemId(String view, String filterType, String filterValue, BalanceReportDTO queryDto) {
        int levelNo = 0;
        int levelValueSid = 0;
        if (Constants.IndicatorConstants.CUSTOMER.getConstant().equals(view)) {
            if (Constants.IndicatorConstants.CUSTOMER.getConstant().equals(filterType)) {
                levelNo = 1;
                levelValueSid = Integer.valueOf(filterValue);
                queryDto.setComapnySid(filterValue);
                queryDto.setLevelNo(levelNo);
            } else if (Constants.IndicatorConstants.BRAND.getConstant().equals(filterType)) {
                levelNo = 4;
                levelValueSid = Integer.valueOf(filterValue);
                queryDto.setBrandSid(filterValue);
                queryDto.setLevelNo(levelNo);
            } else if (Constants.IndicatorConstants.ITEM.getConstant().equals(filterType)) {
                levelNo = 5;
                levelValueSid = Integer.valueOf(filterValue);
                queryDto.setItemSid(filterValue);
                queryDto.setLevelNo(levelNo);
            }
        } else if (Constants.IndicatorConstants.BRAND.getConstant().equals(view)) {
            if (Constants.IndicatorConstants.CUSTOMER.getConstant().equals(filterType)) {
                levelNo = 3;
                levelValueSid = Integer.valueOf(filterValue);
                queryDto.setComapnySid(filterValue);
                queryDto.setLevelNo(levelNo);
            } else if (Constants.IndicatorConstants.BRAND.getConstant().equals(filterType)) {
                levelNo = 2;
                levelValueSid = Integer.valueOf(filterValue);
                queryDto.setBrandSid(filterValue);
                queryDto.setLevelNo(levelNo);
            } else if (Constants.IndicatorConstants.ITEM.getConstant().equals(filterType)) {
                levelNo = 5;
                levelValueSid = Integer.valueOf(filterValue);
                queryDto.setItemSid(filterValue);
                queryDto.setLevelNo(levelNo);
            }

        } else if (Constants.IndicatorConstants.ITEM.getConstant().equals(view)) {
            if (Constants.IndicatorConstants.CUSTOMER.getConstant().equals(filterType)) {
                levelNo = 3;
                levelValueSid = Integer.valueOf(filterValue);
                queryDto.setComapnySid(filterValue);
                queryDto.setLevelNo(levelNo);
            } else if (Constants.IndicatorConstants.ITEM.getConstant().equals(filterType)) {
                levelNo = 2;
                levelValueSid = Integer.valueOf(filterValue);
                queryDto.setItemSid(filterValue);
                queryDto.setLevelNo(levelNo);
            }
        }
        return queryDto;
    }
}
