/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.logic.tablelogic;

import com.stpl.app.gtnforecasting.dto.PPAProjectionDTO;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.dto.SaveDTO;
import com.stpl.app.gtnforecasting.logic.PPAProjectionLogic;
import com.stpl.app.gtnforecasting.ppaprojection.form.PPAProjection;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.ifs.ui.util.NumericConstants;

import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.vaadin.data.Container;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtTreeContainer;
import org.asi.ui.extfilteringtable.paged.logic.ContainerLogic;
import org.asi.ui.extfilteringtable.paged.logic.PageTreeTableLogic;
import org.jboss.logging.Logger;

/**
 *
 * @author srithar
 */
public class PPATableLoadLogic extends PageTreeTableLogic {

    CustomTableHeaderDTO rightDto = new CustomTableHeaderDTO();

    SessionDTO session;
    List<SaveDTO> saveList;
    PPAProjectionLogic logic = new PPAProjectionLogic();

    public PPAProjectionLogic getLogic() {
        return logic;
    }

    public void setLogic(PPAProjectionLogic logic) {
        this.logic = logic;
    }
    ProjectionSelectionDTO selection;
    int pageNumber = 1;
    boolean isRefresh;
    private static final Logger LOGGER = Logger.getLogger(PPAProjectionLogic.class);

    public boolean isIsRefresh() {
        return isRefresh;
    }

    public void setIsRefresh(boolean isRefresh) {
        this.isRefresh = isRefresh;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public ProjectionSelectionDTO getSelection() {
        return selection;
    }

    public void setSelection(ProjectionSelectionDTO selection) {
        this.selection = selection;
    }

    public List<SaveDTO> getSaveList() {
        return saveList;
    }

    public void setSaveList(List<SaveDTO> saveList) {
        this.saveList = saveList;
    }

    public SessionDTO getSession() {
        return session;
    }

    public void setSession(SessionDTO session) {
        this.session = session;
    }

    public CustomTableHeaderDTO getRightDto() {
        return rightDto;
    }

    public void setRightDto(CustomTableHeaderDTO rightDto) {
        this.rightDto = rightDto;
    }

    public PPATableLoadLogic(CustomTableHeaderDTO rightDto) {
        this.rightDto = rightDto;
    }

    public PPATableLoadLogic() {

    }

    @Override
    public Map<Integer, Object> loadData(int start, int offset) {
        Map<Integer, Object> finalMap = new HashMap<>();
        List<PPAProjectionDTO> list = null;
        try {
            list = logic.getPPALogicData(selection, getLastParent(), start, offset, rightDto);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        int i = start;
        for (PPAProjectionDTO dto : list) {
            finalMap.put(i, dto);
            i++;
        }

        LOGGER.debug("End of load data");
        return finalMap;

    }

    @Override
    public int getCount() {
        PPAProjection.waitForSave();
        int count = 0;

        count = logic.getPPAProjectionResultsCount(selection, getLastParent(), session);

        return count;
    }

    @Override
    public Object configureContainer(Object object, Container datasource) {
        ((ExtTreeContainer<PPAProjectionDTO>) datasource).addBean((PPAProjectionDTO) object);

        PPAProjectionDTO dto = (PPAProjectionDTO) object;
        if (selection.isIsFilter() || dto.getLevelNo().equals(session.getLowerMostCustomerLevelNo())) {
            ((ExtTreeContainer<PPAProjectionDTO>) datasource).setChildrenAllowed(dto, false);
        }

        return object;
    }

    public ContainerLogic.LevelMap getLevelMap(int levelValue, int count, int levelIndex) {
        int page = getPageForItem(count + levelIndex + levelValue);
        int index = getItemIndex(count + levelIndex + levelValue);
        int start = getStartIndex(count + levelIndex, index);
        int end = getPageLength();
        ContainerLogic.LevelMap levelMap = new ContainerLogic.LevelMap(start, end, page, getPageLength(), index, getColumnIdToFilterMap());
        return levelMap;
    }

    public int getPageForItem(int pos) {
        int curPage = ((pos - NumericConstants.TWO) / getPageLength()) + 1;
        return curPage;
    }

    public int getItemIndex(int pos) {
        int index = (pos - NumericConstants.TWO) % getPageLength();
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

    protected void recursivelyLoadExpandData(Object parentId, String treeLevel, int expandLevelNo) {
        int count = logic.getPPAProjectionResultsCount(selection, parentId, session);
        LevelMap levelMap = new LevelMap(count, getColumnIdToFilterMap());
        addlevelMap(treeLevel, levelMap);

        int levelNo = selection.getLevelNo();
        if (expandLevelNo >= levelNo) {
            List<PPAProjectionDTO> levelList = logic.getPPALogicData(selection, parentId, 0, count, rightDto);
            int size = levelList.size();
            int index = 1;
            for (int j = 0; j < size; j++) {
                PPAProjectionDTO dto = levelList.get(j);
                String customTreeLevel = treeLevel + (index + j) + ".";
                addExpandedTreeList(customTreeLevel, dto);
                recursivelyLoadExpandData(dto, customTreeLevel, expandLevelNo);
            }
        }
    }

    public void loadExpandData(int levelNo) {
        try {
            recursivelyLoadExpandData(new Object(), StringUtils.EMPTY, levelNo);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(PPATableLoadLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
        setRecordCount(getCalculatedTotalRecordCount());
        setCurrentPage(getTotalAmountOfPages());
    }

    public Object getParent(String hierarchyNo) {
        return getExpandedTreeValue(hierarchyNo);

    }

    public void forRefresh(Set<String> finalHirarechyNo) {
        addExpandedTreeDataFetchable(finalHirarechyNo);

    }

}
