/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.discountProjection.logic.tableLogic;

import com.stpl.app.customtreecontainer.CustomTreeContainer;
import com.stpl.app.galforecasting.discountProjection.dto.DiscountProjectionDTO;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.app.galforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.galforecasting.logic.CommonLogic;
import com.stpl.app.galforecasting.sessionutils.SessionDTO;
import com.stpl.app.galforecasting.utils.CommonUtils;
import com.stpl.app.galforecasting.utils.Constant;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.vaadin.data.Container;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import org.asi.ui.extfilteringtable.paged.logic.PageTreeTableLogic;

public class DiscountTableLoadLogic extends PageTreeTableLogic {

    CustomTableHeaderDTO rightDto = new CustomTableHeaderDTO();
    SessionDTO session;
    ProjectionSelectionDTO projectionSelection;
    String frequency;
    String history;
    String actualsOrProjections;
    String projectionPeriodorder;
    String discountType;
    String projectionType;
    int discountList = 0;
    List<Integer> startAndEndPeriods = new ArrayList<Integer>();
    String year;
    int levelNo;
    boolean isParent;
    boolean isCount;
    List<Leveldto> currentHierarchy;
    boolean isCustomHierarchy;
    String hierarchyIndicator;
    boolean dataLoad = false;
    boolean isChildrenAllowed = true;
    int lastLevelNo = 0;
    int customId = 0;
    private int maxExpandLevelNo = 0;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(DiscountTableLoadLogic.class);

    public void setDiscountVariablesForLogic(SessionDTO session, ProjectionSelectionDTO projectionSelection, List<Integer> startAndEndPeriods, int discountList,
            int levelNo, boolean isParent, CustomTableHeaderDTO rightDto, String hierarchyIndicator, List<Leveldto> currentHierarchy,
            boolean isCustomHierarchy, int customId) {
        clearAll();
        this.rightDto = rightDto;
        this.session = session;
        this.frequency = projectionSelection.getFrequency();
        this.history = projectionSelection.getHistory();
        this.actualsOrProjections = projectionSelection.getActualsOrProjections();
        this.projectionPeriodorder = projectionSelection.getProjectionOrder();
        this.discountType = projectionSelection.getDiscountType();
        this.projectionType = projectionSelection.getProjectionType();
        this.discountList = session.getDiscountTypeId();
        this.year = projectionSelection.getYear();
        this.isCustomHierarchy = isCustomHierarchy;
        this.levelNo = levelNo;
        this.hierarchyIndicator = hierarchyIndicator;
        this.currentHierarchy = currentHierarchy;
        this.isParent = isParent;
        this.startAndEndPeriods = startAndEndPeriods;
        this.customId = customId;
        this.projectionSelection = projectionSelection;
        if (!isCustomHierarchy) {
            if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(hierarchyIndicator)) {
                lastLevelNo = session.getLowerMostCustomerLevelNo();
            } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(hierarchyIndicator)) {
                lastLevelNo = session.getLowerMostProductLevelNo();
            } else {
                lastLevelNo = 0;
            }
        } else {
            lastLevelNo = currentHierarchy.size();
        }
        setCurrentPage(1);
    }

    public void filterLevels(int levelNo) {
        this.levelNo = levelNo;
        setCurrentPage(1);
    }

    public void setChildrenAllowed(boolean isChildrenAllowed) {
        this.isChildrenAllowed = isChildrenAllowed;
    }

    public DiscountTableLoadLogic() {

    }

    @Override
    public Map<Integer, Object> loadData(int start, int offset) {
        LOGGER.info("inside loadData method ------------------------------------------------------");
        Map<Integer, Object> retmap = new HashMap<Integer, Object>();
        List list = getLevelData(getLastParent(), start, offset);
        int i = start;
        for (Object dto : list) {
            retmap.put(i, dto);
            i++;
        }
        return retmap;
    }

    private List getLevelData(Object parentId, int start, int offset) {
        List list = new ArrayList();

        try {
            int levelNumber = 0;
            String hierarchyNo = StringUtils.EMPTY;
            String tempHierarchyIndicator = StringUtils.EMPTY;
            String customerHierarchyNo = StringUtils.EMPTY;
            String productHierarchyNo = StringUtils.EMPTY;

            int treeLevelNo = 0;
            List customDetailsList = new ArrayList();
            LOGGER.info(" Custom hierarcht is bool " + isCustomHierarchy);

            Boolean isParentChecked = false;
            if (parentId != null && (parentId instanceof DiscountProjectionDTO)) {
                // For child
                DiscountProjectionDTO dto = (DiscountProjectionDTO) parentId;
                LOGGER.info(" dto.getLevelNo() " + dto.getTreeLevelNo());
                isParentChecked = (Boolean) dto.getPropertyValue(Constant.CHECKRECORD);
                Leveldto levelDto = CommonLogic.getNextLevel(dto.getTreeLevelNo() + 1, currentHierarchy);
                if (levelDto != null) {
                    if (isCustomHierarchy) {
                        hierarchyNo = levelDto.getParentNode();
                        tempHierarchyIndicator = levelDto.getHierarchyIndicator();
                        levelNumber = levelDto.getLevelNo();
                        treeLevelNo = levelDto.getTreeLevelNo();
                        if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(dto.getHierarchyIndicator())) {
                            customerHierarchyNo = dto.getHierarchyNo();
                            productHierarchyNo = dto.getProductHierarchyNo();
                        } else {
                            customerHierarchyNo = dto.getCustomerHierarchyNo();
                            productHierarchyNo = dto.getHierarchyNo();
                        }

                    } else {
                        levelNumber = levelDto.getLevelNo();
                        hierarchyNo = dto.getHierarchyNo();
                        treeLevelNo = levelNumber;
                        tempHierarchyIndicator = levelDto.getHierarchyIndicator();
                        LOGGER.info(" This is not custom hierarchy child");
                    }
                }

            } else {
                // For parent
                if (currentHierarchy != null) {
                    LOGGER.info(" This is parent");
                    LOGGER.info(" level No" + levelNo);
                    Leveldto levelDto = CommonLogic.getNextLevel(levelNo, currentHierarchy);

                    if (levelDto != null) {
                        tempHierarchyIndicator = levelDto.getHierarchyIndicator();
                        levelNumber = levelDto.getLevelNo();
                        if (isCustomHierarchy) {
                            treeLevelNo = levelDto.getTreeLevelNo();
                            customerHierarchyNo = StringUtils.EMPTY;
                            productHierarchyNo = StringUtils.EMPTY;
                        } else {
                            treeLevelNo = levelNumber;
                        }
                    }
                }
            }

            customDetailsList.add(levelNumber);
            customDetailsList.add(hierarchyNo);
            customDetailsList.add(treeLevelNo);

            List<String> customViewDetails = new ArrayList<String>();
            if (isCustomHierarchy) {
                String customerLevelNo;
                String productLevelNo;

                if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(tempHierarchyIndicator)) {
                    customerLevelNo = String.valueOf(treeLevelNo);
                    productLevelNo = Constant.PERCENT;
                } else {
                    customerLevelNo = Constant.PERCENT;
                    productLevelNo = String.valueOf(treeLevelNo);
                }

                LOGGER.info(" Custom hierarchy --- \n customId " + customId);
                LOGGER.info(" customerLevelNo " + customerLevelNo);
                LOGGER.info(" customerHierarchyNo " + customerHierarchyNo);
                LOGGER.info(" productLevelNo " + productLevelNo);
                LOGGER.info(" productHierarchyNo " + productHierarchyNo);
                customViewDetails.add(StringUtils.EMPTY + customId);
                customViewDetails.add(customerLevelNo);
                customViewDetails.add(customerHierarchyNo);
                customViewDetails.add(productLevelNo);
                customViewDetails.add(productHierarchyNo);
            } else {
                LOGGER.info(" Hierarchy No === " + hierarchyNo);
                LOGGER.info(" level No ===" + levelNumber);
                LOGGER.info(" Hierarchy indicator ===" + tempHierarchyIndicator);
            }
            LOGGER.info(" customTreeLevelNo ===" + treeLevelNo);

            LOGGER.info("Exiting loadData method ------------------------------------------------------" + list.size());
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return list;
    }

    @Override
    public int getCount() {
        LOGGER.info("inside getCount method");
        return getLevelCount(getLastParent());
    }

    private int getLevelCount(Object parentId) {
        if (!dataLoad) {
            return 0;
        }
        List list = null;
        try {
            int levelNumber = 0;
            String hierarchyNo = StringUtils.EMPTY;
            String tempHierarchyIndicator = StringUtils.EMPTY;
            String customerHierarchyNo = StringUtils.EMPTY;
            String productHierarchyNo = StringUtils.EMPTY;

            int treeLevelNo = 0;
            List customDetailsList = new ArrayList();
            LOGGER.info(" Custom hierarcht is bool " + isCustomHierarchy);

            if (parentId != null && (parentId instanceof DiscountProjectionDTO)) {
                // For child
                DiscountProjectionDTO dto = (DiscountProjectionDTO) parentId;
                LOGGER.info(" dto.getLevelNo() " + dto.getTreeLevelNo() + "---->>" + currentHierarchy);
                Leveldto levelDto = CommonLogic.getNextLevel(dto.getTreeLevelNo() + 1, currentHierarchy);
                if (levelDto != null) {
                    if (isCustomHierarchy) {
                        hierarchyNo = levelDto.getParentNode();
                        tempHierarchyIndicator = levelDto.getHierarchyIndicator();
                        levelNumber = levelDto.getLevelNo();
                        treeLevelNo = levelDto.getTreeLevelNo();

                        if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(dto.getHierarchyIndicator())) {
                            customerHierarchyNo = dto.getHierarchyNo();
                            productHierarchyNo = dto.getProductHierarchyNo();
                        } else {
                            customerHierarchyNo = dto.getCustomerHierarchyNo();
                            productHierarchyNo = dto.getHierarchyNo();
                        }

                    } else {
                        levelNumber = levelDto.getLevelNo();
                        hierarchyNo = dto.getHierarchyNo();
                        treeLevelNo = levelNumber;
                        tempHierarchyIndicator = levelDto.getHierarchyIndicator();
                        LOGGER.info(" This is not custom hierarchy child");
                    }
                }

            } else {
                // For parent
                if (currentHierarchy != null) {
                    LOGGER.info(" This is parent");
                    LOGGER.info(" level No " + levelNo);
                    Leveldto levelDto = CommonLogic.getNextLevel(levelNo, currentHierarchy);

                    if (levelDto != null) {
                        tempHierarchyIndicator = levelDto.getHierarchyIndicator();
                        levelNumber = levelDto.getLevelNo();
                        if (isCustomHierarchy) {
                            treeLevelNo = levelDto.getTreeLevelNo();
                            customerHierarchyNo = StringUtils.EMPTY;
                            productHierarchyNo = StringUtils.EMPTY;
                        } else {
                            treeLevelNo = levelNumber;
                        }
                    }
                }
            }

            customDetailsList.add(levelNumber);
            customDetailsList.add(hierarchyNo);
            customDetailsList.add(treeLevelNo);
            LOGGER.info(" Hierarchy No === " + hierarchyNo);
            LOGGER.info(" level No ===" + levelNumber);
            LOGGER.info(" Hierarchy indicator ===" + tempHierarchyIndicator);
            LOGGER.info(" customTreeLevelNo ===" + treeLevelNo);

            List<String> customViewDetails = new ArrayList<String>();
            if (isCustomHierarchy) {
                String customerLevelNo;
                String productLevelNo;

                if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(tempHierarchyIndicator)) {
                    customerLevelNo = String.valueOf(treeLevelNo);
                    productLevelNo = Constant.PERCENT;
                } else {
                    customerLevelNo = Constant.PERCENT;
                    productLevelNo = String.valueOf(treeLevelNo);
                }

                LOGGER.info(" Custom hierarchy --- \n customId " + customId);
                LOGGER.info(" customerLevelNo " + customerLevelNo);
                LOGGER.info(" customerHierarchyNo " + customerHierarchyNo);
                LOGGER.info(" productLevelNo " + productLevelNo);
                LOGGER.info(" productHierarchyNo " + productHierarchyNo);
                customViewDetails.add(StringUtils.EMPTY + customId);
                customViewDetails.add(customerLevelNo);
                customViewDetails.add(customerHierarchyNo);
                customViewDetails.add(productLevelNo);
                customViewDetails.add(productHierarchyNo);
            }

            if (levelNumber != 0 && treeLevelNo != 0) {
                setMaxExpandLevelNo(treeLevelNo);
            }
            if (list == null || list.size() == 0) {
                return 0;
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return Integer.valueOf(list.get(0).toString());
    }

    public int getMaxExpandLevelNo() {
        return maxExpandLevelNo;
    }

    public void setMaxExpandLevelNo(int maxExpandLevelNo) {
        this.maxExpandLevelNo = maxExpandLevelNo;
    }

    protected void recursivelyLoadExpandData(Object parentId, String treeLevel, int expandLevelNo) {
        int count = getLevelCount(parentId);
        LevelMap levelMap = new LevelMap(count, getColumnIdToFilterMap());
        addlevelMap(treeLevel, levelMap);

        if (expandLevelNo >= getMaxExpandLevelNo()) {

            List list = new ArrayList();
            int neededRecord = count;
            for (int j = 1; j <= count; j++) {
                String customTreeLevel = treeLevel + (j) + ".";
                Object ob = getCurrentPageData(customTreeLevel);
                if (ob != null) {
                    LOGGER.info("data is there");
                    neededRecord--;
                    list.add(ob);
                }

            }
            if (neededRecord > 0) {
                List list1 = getLevelData(parentId, count - neededRecord, neededRecord);
                list.addAll(list1);
            }

            int size = list.size();
            int index = count - size + 1;
            for (int j = 0; j < size; j++) {
                DiscountProjectionDTO dto = (DiscountProjectionDTO) (list.get(j));
                String customTreeLevel = treeLevel + (index + j) + ".";
                addExpandedTreeList(customTreeLevel, dto);
                recursivelyLoadExpandData(dto, customTreeLevel, expandLevelNo);
            }
        }
    }

    public void loadExpandData(int levelNo) {
        super.saveCurrentPage();
        recursivelyLoadExpandData(new Object(), StringUtils.EMPTY, levelNo);
        setRecordCount(getCalculatedTotalRecordCount());
        setCurrentPage(getTotalAmountOfPages());
    }

    @Override
    public Object configureContainer(Object object, Container datasource) {
        LOGGER.info("inside configure container method");
        DiscountProjectionDTO dto = (DiscountProjectionDTO) object;
        ((CustomTreeContainer<DiscountProjectionDTO>) datasource).addBean(dto);
        if (dto.getTreeLevelNo() == lastLevelNo) {
            ((CustomTreeContainer<DiscountProjectionDTO>) datasource).setChildrenAllowed(dto, false);
        } else {
            ((CustomTreeContainer<DiscountProjectionDTO>) datasource).setChildrenAllowed(dto, isChildrenAllowed);
        }
        return object;
    }

    public boolean isDataLoad() {
        return dataLoad;
    }

    public void setDataLoad(boolean dataLoad) {
        this.dataLoad = dataLoad;
    }

    public Object getExpandedTreeValues(String treeLevel) {
        return getExpandedTreeValue(treeLevel);
    }

    public void setHierarchyToRefresh(Set<String> finalHirarechyNo) {
        addExpandedTreeDataFetchable(finalHirarechyNo);
    }

    @Override
    public Map<String, Object> loadBulkData(Map<String, Object> bulkDataMap) {
        Map<String, String> tempMap = new HashMap<String, String>();
        List<String> hiearchyNoList = new ArrayList<String>();

        for (String key : bulkDataMap.keySet()) {
            String tempLevelValue = StringUtils.EMPTY;
            DiscountProjectionDTO dto = (DiscountProjectionDTO) bulkDataMap.get(key);
            tempLevelValue = dto.getHierarchyNo();
            tempMap.put(tempLevelValue, key);
            hiearchyNoList.add(tempLevelValue);
        }

        String hierarchyNumbers = CommonUtils.CollectionToString(hiearchyNoList, true);

        List<String> customViewDetails = new ArrayList<String>();
        List customDetailsList = new ArrayList();
        customDetailsList.add(0);
        customDetailsList.add(StringUtils.EMPTY);
        customDetailsList.add(0);

        return bulkDataMap;
    }
}
