/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.lazyLoad;

import com.stpl.app.cff.dto.ProjectionSelectionDTO;
import com.stpl.app.cff.logic.CommonLogic;
import com.stpl.app.cff.ui.projectionresults.dto.ProjectionResultsDTO;
import com.stpl.app.cff.ui.projectionresults.logic.ProjectionResultsLogic;
import com.stpl.app.cff.util.CommonUtils;
import com.stpl.app.customtreecontainer.CustomTreeContainer;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.vaadin.data.Container;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.PageTreeLogicBase;
import org.asi.ui.extfilteringtable.paged.logic.PageTreeTableLogic;
import org.jboss.logging.Logger;

/**
 *
 * @author porchelvi.g
 */
public class ResultsTableLogic extends PageTreeTableLogic {

    ProjectionSelectionDTO projSelDTO = new ProjectionSelectionDTO();
    private final ProjectionResultsLogic projectionResultsLogic = new ProjectionResultsLogic();
    boolean firstGenerated = false;
    private static final Logger LOGGER = Logger.getLogger(ResultsTableLogic.class);

    @Override
    public Map<Integer, Object> loadData(int start, int offset) {
        LOGGER.info("loadData initiated with firstGenerated=" + firstGenerated + " and start=" + start + " and offset=" + offset);
        Map<Integer, Object> map = new HashMap<>();
        if (firstGenerated && offset > 0) {
            try {
                List<String> indexList = new ArrayList<>(getNonFetchableData().keySet());
                projSelDTO.setNonFetchableIndex(indexList);
                List<ProjectionResultsDTO> list = loadDataByForecastName(getLastParent(), start, offset);
                int i = start;
                for (ProjectionResultsDTO dto : list) {                    
                    while (projSelDTO.hasNonFetchableIndex("" + i)) {
                        i++;
                    }
                    map.put(i, dto);
                    i++;
                }
                projSelDTO.clearNonFetchableIndex();
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }
        LOGGER.info("loadData ended " + map.size());
        return map;
    }

    @Override
    public int getCount() {
        LOGGER.info("getCount initiated with firstGenerated=" + firstGenerated);
        int count = 0;
        if (firstGenerated) {
            try {
                count = getCountByForecastName(getLastParent());
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }
        LOGGER.info("getCount ended with count=" + count);
        return count;
    }

    @Override
    public Object configureContainer(Object object, Container datasource) {
        ProjectionResultsDTO dto = (ProjectionResultsDTO) object;
        ((CustomTreeContainer<ProjectionResultsDTO>) datasource).addBean(dto);
        if (dto.getParent() == 1) {
            ((CustomTreeContainer<ProjectionResultsDTO>) datasource).setChildrenAllowed(dto, true);
        } else {
            ((CustomTreeContainer<ProjectionResultsDTO>) datasource).setChildrenAllowed(dto, false);
        }
        return dto;
    }

    @Override
    public void setColumnIdToFilterValue(Object prop, Object value) {
        super.setColumnIdToFilterValue(prop, value);
        Object groupDdlb = getColumnIdToFilterValue("group");
        if (groupDdlb != null) {
            if (projSelDTO.isIsCustomHierarchy() || !projSelDTO.getHierarchyIndicator().equals("P")) {
                projSelDTO.setGroupFilter(String.valueOf(groupDdlb));
            }
            clearAll();
        }
    }

    public void setProjectionResultsData(ProjectionSelectionDTO projSelDTO) {
        this.projSelDTO = projSelDTO;
        clearAll();
        firstGenerated = true;
        setCurrentPage(1);
    }

    protected void recursivelyLoadExpandData(Object parentId, String treeLevel, int expandLevelNo) throws Exception {
        int count = getCountByForecastName(parentId);
        PageTreeLogicBase.LevelMap levelMap = new PageTreeLogicBase.LevelMap(count, getColumnIdToFilterMap());
        addlevelMap(treeLevel, levelMap);
        String productHierarchyNo = projSelDTO.getProductHierarchyNo();
        String customerHierarchyNo = projSelDTO.getCustomerHierarchyNo();
        String hierarchyNo = projSelDTO.getHierarchyNo();
        int levelNo = projSelDTO.getTreeLevelNo();
        if (expandLevelNo >= levelNo) {
            if (projSelDTO.isGroupCount()) {
                String customTreeLevel = treeLevel + (count) + ".";
                ProjectionResultsDTO dto = new ProjectionResultsDTO();
                dto.setLevelNo(projSelDTO.getLevelNo());
                dto.setTreeLevelNo(projSelDTO.getTreeLevelNo());
                dto.setParentNode(projSelDTO.getParentNode());
                dto.setGroup(projSelDTO.getGroupFilter());
                dto.setLevelValue(projSelDTO.getLevelValue());
                dto.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
                dto.setHierarchyNo(hierarchyNo);
                if (dto.getHierarchyIndicator().equals("C")) {
                    dto.setCustomerHierarchyNo(dto.getHierarchyNo());
                    dto.setProductHierarchyNo(productHierarchyNo);
                } else if (dto.getHierarchyIndicator().equals("P")) {
                    dto.setProductHierarchyNo(dto.getHierarchyNo());
                    dto.setCustomerHierarchyNo(customerHierarchyNo);
                }
                dto.setOnExpandTotalRow(1);
                dto.setParent(1);
                addExpandedTreeList(customTreeLevel, dto);
                recursivelyLoadExpandData(dto, customTreeLevel, expandLevelNo);
            } else {
                List<Leveldto> levelList = CommonLogic.getConditionalLevelList(projSelDTO.getProjectionId(), 0, projSelDTO.getLevelCount(), projSelDTO.getHierarchyIndicator(), levelNo, hierarchyNo, productHierarchyNo, customerHierarchyNo, false, false, projSelDTO.isIsCustomHierarchy(), projSelDTO.getCustomId(), projSelDTO.getGroupFilter(), projSelDTO.getUserId(), projSelDTO.getSessionId(), projSelDTO.getCustRelationshipBuilderSid(), projSelDTO.getProdRelationshipBuilderSid(), false, true);
                int size = levelList.size();
                int index = count - size + 1;
                for (int j = 0; j < size; j++) {
                    Leveldto levelDto = levelList.get(j);
                    String customTreeLevel = treeLevel + (index + j) + ".";
                    ProjectionResultsDTO dto = new ProjectionResultsDTO();
                    dto.setLevelNo(levelDto.getLevelNo());
                    dto.setTreeLevelNo(levelDto.getTreeLevelNo());
                    dto.setParentNode(levelDto.getParentNode());
                    dto.setGroup(projSelDTO.getSessionDTO().getLevelValueDiscription(levelDto.getHierarchyNo(), levelDto.getHierarchyIndicator()));
                    dto.setLevelValue(levelDto.getRelationshipLevelValue());
                    dto.setHierarchyIndicator(levelDto.getHierarchyIndicator());
                    dto.setHierarchyNo(levelDto.getHierarchyNo());
                    if (dto.getHierarchyIndicator().equals("C")) {
                        dto.setCustomerHierarchyNo(dto.getHierarchyNo());
                        dto.setProductHierarchyNo(productHierarchyNo);
                    } else if (dto.getHierarchyIndicator().equals("P")) {
                        dto.setProductHierarchyNo(dto.getHierarchyNo());
                        dto.setCustomerHierarchyNo(customerHierarchyNo);
                    }
                    dto.setOnExpandTotalRow(1);
                    dto.setParent(1);
                    addExpandedTreeList(customTreeLevel, dto);
                    recursivelyLoadExpandData(dto, customTreeLevel, expandLevelNo);
                }
            }
        }
    }

    public void loadExpandData(int levelNo) throws Exception {
        recursivelyLoadExpandData(new Object(), "", levelNo);
        setRecordCount(getCalculatedTotalRecordCount());
        setCurrentPage(getTotalAmountOfPages());
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

    /**
     *
     * @param parentId
     * @return
     */
    private int getCountByForecastName(final Object parentId) throws Exception {
        int count;
        count = projectionResultsLogic.getConfiguredProjectionResultsCount(parentId, projSelDTO, true);
        LOGGER.info("===count==========================>>>>>"+count);
        return count;
    }

    private List<ProjectionResultsDTO> loadDataByForecastName(final Object parentId, final int start, final int offset) throws Exception {
        List<ProjectionResultsDTO> list;
        String screenName = StringUtils.isBlank(projSelDTO.getScreenName()) ? StringUtils.EMPTY : projSelDTO.getScreenName();
        LOGGER.info("Screen Name is " + screenName);
        LOGGER.info("Projection results load data method with start = " + start + " and offset = " + offset);
        list = projectionResultsLogic.getConfiguredProjectionResults(parentId, start, offset, projSelDTO);
        return list;
    }
    
}
