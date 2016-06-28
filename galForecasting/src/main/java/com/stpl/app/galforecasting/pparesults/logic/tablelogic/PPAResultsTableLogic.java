/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.pparesults.logic.tablelogic;

import com.stpl.app.customtreecontainer.CustomTreeContainer;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.app.galforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.galforecasting.logic.CommonLogic;
import com.stpl.app.galforecasting.pparesults.dto.PPAProjectionResultsDTO;
import com.stpl.app.galforecasting.pparesults.logic.PPAProjectionResultsLogic;
import com.stpl.app.galforecasting.sessionutils.SessionDTO;
import com.stpl.app.galforecasting.utils.Constant;
import com.vaadin.data.Container;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.ContainerLogic;
import org.asi.ui.extfilteringtable.paged.logic.PageTreeLogicBase;
import org.asi.ui.extfilteringtable.paged.logic.PageTreeTableLogic;
import org.jboss.logging.Logger;

/**
 *
 * @author srithar
 */
public class PPAResultsTableLogic extends PageTreeTableLogic {

    ProjectionSelectionDTO selection = new ProjectionSelectionDTO();
    PPAProjectionResultsLogic logic = new PPAProjectionResultsLogic();
    SessionDTO session;
    boolean firstGenerated = false;
    int totalCount = 0;
    List totalList = new ArrayList();

    public PPAProjectionResultsLogic getLogic() {
        return logic;
    }

    public void setLogic(PPAProjectionResultsLogic logic) {
        this.logic = logic;
    }

    public SessionDTO getSession() {
        return session;
    }

    public void setSession(SessionDTO session) {
        this.session = session;
    }

    public ProjectionSelectionDTO getSelection() {
        return selection;
    }

    public void setSelection(ProjectionSelectionDTO selection) {
        this.selection = selection;
    }
    private static final Logger LOGGER = Logger
            .getLogger(PPAResultsTableLogic.class);

    @Override
    public Map<Integer, Object> loadData(int start, int offset) {
        LOGGER.info(start + "Inside load data" + offset);
        Map<Integer, Object> finalMap = new HashMap<Integer, Object>();
        List<PPAProjectionResultsDTO> list = new ArrayList<PPAProjectionResultsDTO>();
        if (firstGenerated && offset > 0) {
            int maybeadded = 0;
            if (!(getLastParent() instanceof PPAProjectionResultsDTO) && !selection.isIsFilter()) {
                selection.setIsProjectionTotal(Boolean.TRUE);
                list = logic.getPPAProjectionResultsTotal(start, offset, selection, session);

            }
            maybeadded = list.size();
            if (offset > maybeadded) {
                List<String> indexList = new ArrayList<String>(getNonFetchableData().keySet());
                selection.setNonFetchableIndex(indexList);
                list.addAll(logic.getConfiguredPPAProjectionResults1(getLastParent(), start, offset - maybeadded, selection, session));
            }
        }
        int i = start;
        for (PPAProjectionResultsDTO dto : list) {
            finalMap.put(i, dto);
            i++;
        }
        selection.clearNonFetchableIndex();
        LOGGER.info("End of load data" + offset);
        return finalMap;
    }

    @Override
    public int getCount() {
        int count = 0;
        if (firstGenerated) {
            count = logic.getConfiguredPPAProjectionResultsCount(getLastParent(), selection, Boolean.TRUE);
        }
        return count;
    }

    @Override
    public Object configureContainer(Object object, Container datasource) {
        ((CustomTreeContainer<PPAProjectionResultsDTO>) datasource).addBean((PPAProjectionResultsDTO) object);

        PPAProjectionResultsDTO dto = (PPAProjectionResultsDTO) object;
        if (dto.getLevelNo() == 0) {
            ((CustomTreeContainer<PPAProjectionResultsDTO>) datasource).setChildrenAllowed(dto, false);
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

    public void setProjectionResultsData(ProjectionSelectionDTO selection, boolean isTotal) {

        this.selection = selection;
        firstGenerated = true;
        clearAll();
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
    protected void expandCollapseStart(boolean isExpand) {
        setExpandCollapseProgress(true);
    }

    @Override
    protected void expandCollapseEnd(boolean isExpand) {
        setExpandCollapseProgress(false);
    }

    protected void recursivelyLoadExpandData(Object parentId, String treeLevel, int expandLevelNo) {
        int count = logic.getConfiguredPPAProjectionResultsCount(parentId, selection, true);
        PageTreeLogicBase.LevelMap levelMap = new PageTreeLogicBase.LevelMap(count, getColumnIdToFilterMap());
        addlevelMap(treeLevel, levelMap);
        String productHierarchyNo = selection.getProductHierarchyNo();
        String customerHierarchyNo = selection.getCustomerHierarchyNo();
        String hierarchyNo = selection.getHierarchyNo();
        int levelNo = selection.getTreeLevelNo();
        if (expandLevelNo >= levelNo) {
            if (selection.isGroupCount()) {
                String customTreeLevel = treeLevel + (count) + ".";
                PPAProjectionResultsDTO dto = new PPAProjectionResultsDTO();
                dto.setLevelNo(selection.getLevelNo());
                dto.setTreeLevelNo(selection.getTreeLevelNo());
                dto.setGroup(selection.getGroupFilter());
                dto.setHirarechyIndicater(selection.getHierarchyIndicator());
                dto.setHirarechyNo(hierarchyNo);
                if (dto.getHirarechyIndicater().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
                    dto.setCustomerHierarchyNo(dto.getHirarechyNo());
                    dto.setProductHierarchyNo(productHierarchyNo);
                } else if (dto.getHirarechyIndicater().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
                    dto.setProductHierarchyNo(dto.getHirarechyNo());
                    dto.setCustomerHierarchyNo(customerHierarchyNo);
                }

                addExpandedTreeList(customTreeLevel, dto);
                recursivelyLoadExpandData(dto, customTreeLevel, expandLevelNo);
            } else {
                List<Leveldto> levelList = CommonLogic.getConditionalLevelList(selection.getProjectionId(), 0, selection.getLevelCount(), selection.getHierarchyIndicator(), levelNo, hierarchyNo, productHierarchyNo, customerHierarchyNo, false, false, selection.isIsCustomHierarchy(), selection.getCustomId(), selection.getGroupFilter(), selection.getUserId(), selection.getSessionId(), selection.getCustRelationshipBuilderSid(), selection.getProdRelationshipBuilderSid(), false, true);
                int size = levelList.size();
                int index = count - size + 1;
                for (int j = 0; j < size; j++) {
                    Leveldto levelDto = levelList.get(j);
                    String customTreeLevel = treeLevel + (index + j) + ".";
                    PPAProjectionResultsDTO dto = new PPAProjectionResultsDTO();
                    dto.setLevelNo(levelDto.getLevelNo());
                    dto.setTreeLevelNo(levelDto.getTreeLevelNo());
                    dto.setGroup(selection.getSessionDTO().getLevelValueDiscription(levelDto.getHierarchyNo(), levelDto.getHierarchyIndicator()));
                    dto.setHirarechyIndicater(levelDto.getHierarchyIndicator());
                    dto.setHirarechyNo(levelDto.getHierarchyNo());
                    if (dto.getHirarechyIndicater().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
                        dto.setCustomerHierarchyNo(dto.getHirarechyNo());
                        dto.setProductHierarchyNo(productHierarchyNo);
                    } else if (dto.getHirarechyIndicater().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
                        dto.setProductHierarchyNo(dto.getHirarechyNo());
                        dto.setCustomerHierarchyNo(customerHierarchyNo);
                    }

                    addExpandedTreeList(customTreeLevel, dto);
                    recursivelyLoadExpandData(dto, customTreeLevel, expandLevelNo);
                }
            }
        }
    }

    public void loadExpandData(int levelNo) {
        recursivelyLoadExpandData(new Object(), StringUtils.EMPTY, levelNo);
        setRecordCount(getCalculatedTotalRecordCount());
        setCurrentPage(getTotalAmountOfPages());
    }

    @Override
    public void setColumnIdToFilterValue(Object prop, Object value) {
        super.setColumnIdToFilterValue(prop, value);
        Object groupDdlb = getColumnIdToFilterValue(Constant.GROUP);
        if (groupDdlb != null) {
            if (selection.isIsCustomHierarchy() || !selection.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
                selection.setGroupFilter(String.valueOf(groupDdlb));
            }
            clearAll();
        }
    }
}
