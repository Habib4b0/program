/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.nationalassumptions.logic.tablelogic;

import com.stpl.app.gtnforecasting.nationalassumptions.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.nationalassumptions.dto.TableDTO;
import com.stpl.app.gtnforecasting.nationalassumptions.logic.PhsResultsLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.v7.data.Container;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.asi.container.ExtTreeContainer;
import org.asi.ui.extfilteringtable.paged.logic.ContainerLogic;
import org.asi.ui.extfilteringtable.paged.logic.PageTreeTableLogic;

/**
 *
 * @author Nadhiya
 */
public class PhsWorksheetTableLogic extends PageTreeTableLogic {
    int levelNo;
    String hierarchyNo;
    boolean firstGenerated = false;
    ProjectionSelectionDTO projSelDTO = new ProjectionSelectionDTO();
    private PhsResultsLogic phsResLogic = new PhsResultsLogic();
    SessionDTO sessionDTO;

    @Override
    public int getCount(){
        int count = 0;

        if (firstGenerated) {
            count = phsResLogic.getConfiguredPhsWorkSheetCount(getLastParent(), projSelDTO);
        }
        return count;
    }

     @Override
    public Map<Integer, Object> loadData(int start, int offset) {
        Map<Integer, Object> map = new HashMap<>();
        List<TableDTO> list = new ArrayList<>();
        if (firstGenerated) {
            list = (List<TableDTO>) phsResLogic.getConfiguredPhsWorkSheetResults(lastParent, start, offset, projSelDTO, levelNo, sessionDTO);
        }
        levelNo = 0;

        int i = start;
        for (TableDTO dto : list) {

            map.put(i, dto);
            i++;
        }
        return map;
    }
    
    @Override
    public Object configureContainer(Object object, Container datasource) {
        TableDTO dto = (TableDTO) object;
        ((ExtTreeContainer<TableDTO>) datasource).addBean(dto);
        if (dto.getParent() == 1) {
            ((ExtTreeContainer<TableDTO>) datasource).setChildrenAllowed(dto, true);
        } else {
            ((ExtTreeContainer<TableDTO>) datasource).setChildrenAllowed(dto, false);
        }
        return dto;
    }

     public ContainerLogic.LevelMap getLevelMap(int levelValue, int count, int levelIndex) {
        int page = getPageForItem(count + levelIndex + levelValue);
        int index = getItemIndex(count + levelIndex + levelValue);
        int start = getStartIndex(count + levelIndex, index);
        int end = getPageLength();
        ContainerLogic.LevelMap levelMap = new ContainerLogic.LevelMap(start, end, page, getPageLength(), index, getColumnIdToFilterMap());
        return levelMap;
    }
    
   public void setProjectionResultsData(ProjectionSelectionDTO selection, SessionDTO sessionDTO) {

        firstGenerated = true;
        clearAll();

        this.projSelDTO = selection;
        this.sessionDTO=sessionDTO;
        phsResLogic = new PhsResultsLogic();
        firstGenerated = true;
        setCurrentPage(1);
    }

    public boolean isFirstGenerated() {
        return firstGenerated;
    }

    public void setFirstGenerated(boolean firstGenerated) {
        this.firstGenerated = firstGenerated;
    }

    public void groupChange() {
        clearAll();
        setCurrentPage(1);
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
    protected void expandCollapseStart(boolean isExpand){ 
         setExpandCollapseProgress(true);
    }
    @Override
    protected void expandCollapseEnd(boolean isExpand){
        setExpandCollapseProgress(false);
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
}
