/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.ppaprojection.logic.tablelogic;

import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.dto.SaveDTO;
import com.stpl.app.gtnforecasting.ppaprojection.dto.PPAProjectionDTO;
import com.stpl.app.gtnforecasting.ppaprojection.form.PPAProjection;
import com.stpl.app.gtnforecasting.ppaprojection.logic.PPAProjectionLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.gtnforecasting.utils.Constant.CommonConstants.SELECT_ONE;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.ifs.util.constants.BooleanConstant;
import com.vaadin.v7.data.Container;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.asi.container.ExtTreeContainer;
import org.asi.ui.extfilteringtable.paged.logic.ContainerLogic;
import org.asi.ui.extfilteringtable.paged.logic.PageTreeTableLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Jayaram
 */
public class PPAProjectionTableLogic extends PageTreeTableLogic {

    protected CustomTableHeaderDTO rightDto = new CustomTableHeaderDTO();
    protected SessionDTO session;
    protected List<SaveDTO> saveList;
    protected PPAProjectionLogic logic = new PPAProjectionLogic();
    private PPAProjection ppaProjection = null;

    public PPAProjectionLogic getLogic() {
        return logic;
    }

    public void setLogic(PPAProjectionLogic logic) {
        this.logic = logic;
    }
    protected ProjectionSelectionDTO selection;
    protected int pageNumber = 1;
    protected boolean isRefresh;
    protected boolean isGenerated = false;
    private static final Logger LOGGER = LoggerFactory.getLogger(PPAProjectionLogic.class);
    

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

    public PPAProjectionTableLogic(CustomTableHeaderDTO rightDto) {
        this.rightDto = rightDto;
    }

    public PPAProjectionTableLogic(PPAProjection ppaProjection) {
        this.ppaProjection = ppaProjection;

    }

    @Override
    public Map<Integer, Object> loadData(int start, int offset) {

        Map<Integer, Object> finalMap = new HashMap<>();
        List<PPAProjectionDTO> list = null;
        Object lp = getLastParent();
        try {
            list = logic.getPPALogicData(selection, lp, start, offset, rightDto);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        int i = start;
        for (int j = 0; j < list.size();) {
            PPAProjectionDTO dto = list.remove(j);
            finalMap.put(i, dto);
            i++;
        }
        LOGGER.debug("End of load data");
        return finalMap;

    }

    @Override
    public int getCount() {
        int count = 0;
        if (isGenerated) {
            PPAProjection.waitForSave();
            count = logic.getPPAProjectionResultsCount(selection, getLastParent(), session);
        }
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
        PPAProjection.setValueChangeAllowed(BooleanConstant.getFalseFlag());
        setCurrentPageProgress(true);
        setRefresh(BooleanConstant.getFalseFlag());
    }

    @Override
    protected void createCurrentPageEnd() {
        setCurrentPageProgress(false);
        setRefresh(BooleanConstant.getTrueFlag());
        PPAProjection.setValueChangeAllowed(BooleanConstant.getTrueFlag());
    }

    @Override
    protected void expandCollapseStart(boolean isExpand) {
        PPAProjection.setValueChangeAllowed(BooleanConstant.getFalseFlag());
        setExpandCollapseProgress(true);
    }

    @Override
    protected void expandCollapseEnd(boolean isExpand) {
        setExpandCollapseProgress(false);
        PPAProjection.setValueChangeAllowed(BooleanConstant.getTrueFlag());
    }

    protected void recursivelyLoadExpandData(Object parentId, String treeLevel, int expandLevelNo) {
        PPAProjection.setValueChangeAllowed(BooleanConstant.getFalseFlag());
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
        PPAProjection.setValueChangeAllowed(BooleanConstant.getTrueFlag());
    }

    public void loadExpandData(int levelNo) {
        try {
            recursivelyLoadExpandData(new Object(), StringUtils.EMPTY, levelNo);
        } catch (Exception ex) {
            LoggerFactory.getLogger(PPAProjectionTableLogic.class.getName()).error( StringUtils.EMPTY, ex);
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

    public void setProjectionResultsData(boolean isGenerated) {
        this.isGenerated = isGenerated;

    }

    public Object getExpandedTreeValues(String hierarchyNo) {
        return getExpandedTreeValue(hierarchyNo);
    }

    @Override
    public void setColumnIdToFilterValue(Object prop, Object value) {
        super.setColumnIdToFilterValue(prop, value);

        if ((prop != null && Constant.GROUP.equals(prop.toString())) && (!ppaProjection.isGroupChangeFlag())) {

                PPAProjection.setValueChangeAllowed(BooleanConstant.getFalseFlag());
                ppaProjection.setValueChangeForColumnCheckBox(BooleanConstant.getTrueFlag());
                String valu = Constant.NULL.equals(String.valueOf(value)) ? Constant.ALL_GROUP : String.valueOf(Constant.PPA + value);
                selection.setGroupFilter(valu);
                selection.setIsFilter(BooleanConstant.getFalseFlag());
                setProjectionResultsData(true);
                clearAll();
                ppaProjection.setLevelFilterValue(SELECT_ONE);
                PPAProjection.setValueChangeAllowed(BooleanConstant.getTrueFlag());
                ppaProjection.setValueChangeForColumnCheckBox(BooleanConstant.getFalseFlag());
        }

    }
}
