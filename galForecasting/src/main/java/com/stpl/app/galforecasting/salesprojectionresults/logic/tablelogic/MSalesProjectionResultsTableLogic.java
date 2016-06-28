/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.salesprojectionresults.logic.tablelogic;

import com.stpl.app.customtreecontainer.CustomTreeContainer;
import com.stpl.app.galforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.galforecasting.dto.SalesProjectionResultsDTO;
import com.stpl.app.galforecasting.salesprojectionresults.logic.NMSalesProjectionResultsLogic;
import com.stpl.app.galforecasting.salesprojectionresults.logic.SPRCommonLogic;
import com.stpl.app.galforecasting.utils.Constant;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.vaadin.data.Container;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.PageTreeTableLogic;
import org.jboss.logging.Logger;

/**
 *
 * @author sooriya.lakshmanan
 */
public class MSalesProjectionResultsTableLogic extends PageTreeTableLogic {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(MSalesProjectionResultsTableLogic.class);

    ProjectionSelectionDTO projSelDTO = new ProjectionSelectionDTO();
    private NMSalesProjectionResultsLogic sprLogic = new NMSalesProjectionResultsLogic();
    boolean firstGenerated = false;
    boolean firstTotal = false;
    int totalCount = 0;
    List totalList = new ArrayList();

    @Override
    public Map<Integer, Object> loadData(int start, int offset) {
        LOGGER.info("loadData initiated with firstGenerated=" + firstGenerated + " and start=" + start + " and offset=" + offset);
        Map<Integer, Object> map = new HashMap<Integer, Object>();
        if (firstGenerated && offset > 0) {
            List<String> indexList = new ArrayList<String>(getNonFetchableData().keySet());
            projSelDTO.setNonFetchableIndex(indexList);
            List<SalesProjectionResultsDTO> list = sprLogic.getConfiguredSalesProjectionResultsMandated(getLastParent(), start, offset, projSelDTO);
            int i = start;
            for (SalesProjectionResultsDTO dto : list) {
                map.put(i, dto);
                i++;
            }
            projSelDTO.clearNonFetchableIndex();
        }
        return map;
    }

    @Override
    public int getCount() {
        int count = 0;
        if (firstGenerated) {
            count = sprLogic.getConfiguredSalesProjectionResultsCountMandated(getLastParent(), projSelDTO, true);
        }
        LOGGER.info("Return Count " + count);
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

    public void setProjectionResultsData(ProjectionSelectionDTO projSelDTO, boolean isTotal, String spr) {

        this.projSelDTO = projSelDTO;
        projSelDTO.setProjTabName(spr);
        sprLogic = new NMSalesProjectionResultsLogic();
        firstTotal = isTotal;
        firstGenerated = true;
        clearAll();
        setCurrentPage(1);
    }

    public void loadExpandData(int levelNo) {
        recursivelyLoadExpandData(new Object(), StringUtils.EMPTY, levelNo, StringUtils.EMPTY, StringUtils.EMPTY);
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

    protected void recursivelyLoadExpandData(Object parentId, String treeLevel, int expandLevelNo, String productHierarchyNo, String customerHierarchyNo) {

        int count = sprLogic.getConfiguredSalesProjectionResultsCountMandated(parentId, projSelDTO, true);
        LevelMap levelMap = new LevelMap(count, getColumnIdToFilterMap());
        addlevelMap(treeLevel, levelMap);
        String hierarchyNo = projSelDTO.getHierarchyNo();
        int levelNo = 0;
        int custLevelNo = projSelDTO.getCustomLevelNo();
        if (projSelDTO.isCustomFlag()) {
            levelNo = projSelDTO.getCustomLevelNo();

        } else {
            levelNo = projSelDTO.getLevelNo();
        }

        if (expandLevelNo >= levelNo) {
            List<Leveldto> levelList = SPRCommonLogic.getConditionalLevelList(projSelDTO.getProjectionId(), 0, projSelDTO.getLevelCount(), projSelDTO.getHierarchyIndicator(), levelNo, hierarchyNo, productHierarchyNo, customerHierarchyNo, false, false, projSelDTO.isIsCustomHierarchy(), projSelDTO.getCustomId(), false, StringUtils.EMPTY);
            int size = levelList.size();
            int index = count - size + 1;
            for (int j = 0; j < size; j++) {
                Leveldto levelDto = levelList.get(j);
                String customTreeLevel = treeLevel + (index + j) + ".";
                SalesProjectionResultsDTO dto = new SalesProjectionResultsDTO();
                dto.setCustomLevelNo(custLevelNo);
                dto.setLevelNo(levelDto.getLevelNo());
                dto.setTreeLevelNo(levelDto.getTreeLevelNo());
                dto.setParentNode(levelDto.getParentNode());
                dto.setRelationshipLevelName(projSelDTO.getSessionDTO().getLevelValueDiscription(levelDto.getHierarchyNo(), levelDto.getHierarchyIndicator()));
                dto.setLevelValue(levelDto.getRelationshipLevelValue());
                dto.setHierarchyIndicator(levelDto.getHierarchyIndicator());
                dto.setHierarchyNo(levelDto.getHierarchyNo());
                if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
                    dto.setCustomerHierarchyNo(dto.getHierarchyNo());
                    customerHierarchyNo = dto.getCustomerHierarchyNo();
                    dto.setProductHierarchyNo(productHierarchyNo);
                } else if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
                    dto.setProductHierarchyNo(dto.getHierarchyNo());
                    productHierarchyNo = dto.getProductHierarchyNo();
                    dto.setCustomerHierarchyNo(customerHierarchyNo);
                }
                dto.setOnExpandTotalRow(1);
                dto.setParent(1);
                addExpandedTreeList(customTreeLevel, dto);
                recursivelyLoadExpandData(dto, customTreeLevel, expandLevelNo, productHierarchyNo, customerHierarchyNo);
            }
        }
    }
}
