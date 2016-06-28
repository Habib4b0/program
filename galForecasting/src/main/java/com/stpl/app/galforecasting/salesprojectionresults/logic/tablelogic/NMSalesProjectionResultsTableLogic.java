/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.salesprojectionresults.logic.tablelogic;

import com.stpl.app.customtreecontainer.CustomTreeContainer;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.app.galforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.galforecasting.dto.SalesProjectionResultsDTO;
import com.stpl.app.galforecasting.logic.CommonLogic;
import com.stpl.app.galforecasting.salesprojectionresults.logic.NMSalesProjectionResultsLogic;
import com.stpl.app.galforecasting.utils.Constant;
import com.vaadin.data.Container;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.PageTreeTableLogic;

/**
 *
 * @author Lokeshwari
 */

public class NMSalesProjectionResultsTableLogic extends PageTreeTableLogic {
    ProjectionSelectionDTO projSelDTO = new ProjectionSelectionDTO();
    private NMSalesProjectionResultsLogic sprLogic = new NMSalesProjectionResultsLogic();
    boolean firstGenerated = false;
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(NMSalesProjectionResultsTableLogic.class);

  
    @Override
    public Map<Integer, Object> loadData(int start, int offset){
        LOGGER.info("loadData initiated with firstGenerated=" + firstGenerated + " and start=" + start + " and offset=" + offset);
        Map<Integer, Object> map = new HashMap<Integer, Object>();
        if (firstGenerated && offset > 0) {
            List<String> indexList = new ArrayList<String>(getNonFetchableData().keySet());            
            projSelDTO.setNonFetchableIndex(indexList);
            List<SalesProjectionResultsDTO> list = sprLogic.getConfiguredSalesProjectionResults(getLastParent(), start, offset, projSelDTO);
            int i = start;
            for (SalesProjectionResultsDTO dto : list) {
                while (projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + i)) {
                    i++;
                }
                map.put(i, dto);
                i++;
            }
            projSelDTO.clearNonFetchableIndex();
        }
        LOGGER.info("loadData ends with record size=" + map.size());

        return map;
    }

    @Override
    public int getCount(){
        int count = 0;
        if (firstGenerated) {
            count = sprLogic.getConfiguredSalesProjectionResultsCount(getLastParent(), projSelDTO,true);
        }
        LOGGER.info("count= "+count);
        return count;
    }

    @Override
    public Object configureContainer(Object object, Container datasource) {
        SalesProjectionResultsDTO dto = (SalesProjectionResultsDTO) object;
        ((CustomTreeContainer<SalesProjectionResultsDTO>) datasource).addBean(dto);
        if (dto.getParent() == 1) {
            ((CustomTreeContainer<SalesProjectionResultsDTO>) datasource).setChildrenAllowed(dto, true);
        } else {
            ((CustomTreeContainer<SalesProjectionResultsDTO>) datasource).setChildrenAllowed(dto, false);
        }
        return dto;
    }


    public void setProjectionResultsData(ProjectionSelectionDTO projSelDTO,String spr) {
        this.projSelDTO = projSelDTO;
        sprLogic = new NMSalesProjectionResultsLogic();
        firstGenerated = true;
        clearAll();
        setCurrentPage(1);
    }

    protected void recursivelyLoadExpandData(Object parentId, String treeLevel,int expandLevelNo) {
        int count = sprLogic.getConfiguredSalesProjectionResultsCount(parentId, projSelDTO, true);
        LevelMap levelMap = new LevelMap(count, getColumnIdToFilterMap());
        addlevelMap(treeLevel, levelMap);
        String productHierarchyNo=projSelDTO.getProductHierarchyNo();
        String customerHierarchyNo=projSelDTO.getCustomerHierarchyNo();
        String hierarchyNo=projSelDTO.getHierarchyNo();
        int levelNo=projSelDTO.getTreeLevelNo();
        if (expandLevelNo >= levelNo) {
        if (projSelDTO.isGroupCount()) {
            String customTreeLevel = treeLevel + (count) + ".";
            SalesProjectionResultsDTO dto = new SalesProjectionResultsDTO();
            dto.setLevelNo(projSelDTO.getLevelNo());
            dto.setTreeLevelNo(projSelDTO.getTreeLevelNo());
            dto.setParentNode(projSelDTO.getParentNode());
            dto.setGroup(projSelDTO.getGroupFilter());
            dto.setLevelValue(projSelDTO.getGroupFilter());
            dto.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
            dto.setHierarchyNo(hierarchyNo);
            if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
                dto.setCustomerHierarchyNo(dto.getHierarchyNo());
                dto.setProductHierarchyNo(productHierarchyNo);
            } else if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
                dto.setProductHierarchyNo(dto.getHierarchyNo());
                dto.setCustomerHierarchyNo(customerHierarchyNo);
            }
            dto.setOnExpandTotalRow(1);
            dto.setParent(1);            
            addExpandedTreeList(customTreeLevel, dto);
                recursivelyLoadExpandData(dto, customTreeLevel,expandLevelNo);
        } else {
            List<Leveldto> levelList = CommonLogic.getConditionalLevelList(projSelDTO.getProjectionId(), 0,projSelDTO.getLevelCount() , projSelDTO.getHierarchyIndicator(), levelNo, hierarchyNo, productHierarchyNo, customerHierarchyNo, false, false, projSelDTO.isIsCustomHierarchy(), projSelDTO.getCustomId(), projSelDTO.getGroupFilter(), projSelDTO.getUserId(), projSelDTO.getSessionId(),projSelDTO.getCustRelationshipBuilderSid(),projSelDTO.getProdRelationshipBuilderSid(), false, true);
            int size = levelList.size();
            int index = count - size + 1;
            for (int j = 0; j < size; j++) {
                Leveldto levelDto = levelList.get(j);
                String customTreeLevel = treeLevel + (index + j) + ".";
                SalesProjectionResultsDTO dto = new SalesProjectionResultsDTO();
                dto.setLevelNo(levelDto.getLevelNo());
                dto.setTreeLevelNo(levelDto.getTreeLevelNo());
                dto.setParentNode(levelDto.getParentNode());
                dto.setGroup(projSelDTO.getSessionDTO().getLevelValueDiscription(levelDto.getHierarchyNo(), levelDto.getHierarchyIndicator()));
                dto.setLevelValue(projSelDTO.getSessionDTO().getLevelValueDiscription(levelDto.getHierarchyNo(), levelDto.getHierarchyIndicator()));
                dto.setHierarchyIndicator(levelDto.getHierarchyIndicator());
                dto.setHierarchyNo(levelDto.getHierarchyNo());
                if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
                    dto.setCustomerHierarchyNo(dto.getHierarchyNo());
                    dto.setProductHierarchyNo(productHierarchyNo);
                } else if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
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

    public void loadExpandData(int levelNo) {
            recursivelyLoadExpandData(new Object(), StringUtils.EMPTY, levelNo);
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
    protected void expandCollapseStart(boolean isExpand){ 
         setExpandCollapseProgress(true);
    }
    @Override
    protected void expandCollapseEnd(boolean isExpand){
        setExpandCollapseProgress(false);
    }
    
    @Override
    public void setColumnIdToFilterValue(Object prop, Object value) {
        super.setColumnIdToFilterValue(prop, value);
        Object groupDdlb = getColumnIdToFilterValue("levelValue");
        if (groupDdlb != null) {
            if (projSelDTO.isIsCustomHierarchy() || !projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
                projSelDTO.setGroupFilter(String.valueOf(groupDdlb));
            }
            clearAll();
        }
    }
}