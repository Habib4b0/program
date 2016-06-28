/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.discountProjection.logic.tableLogic;

import com.stpl.app.customtreecontainer.CustomTreeContainer;
import com.stpl.app.galforecasting.discountProjection.form.NMDiscountProjection;
import com.stpl.app.galforecasting.dto.DiscountProjectionDTO;
import com.stpl.app.galforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.galforecasting.logic.CommonLogic;
import com.stpl.app.galforecasting.logic.DiscountProjectionLogic;
import com.stpl.app.galforecasting.sessionutils.SessionDTO;
import com.stpl.app.galforecasting.utils.CommonUtils;
import com.stpl.app.galforecasting.utils.Constant;
import com.stpl.app.utils.Constants;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.vaadin.data.Container;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.PageTreeTableLogic;
import org.jboss.logging.Logger;

/**
 *
 * @author sriram
 */
public class NMDiscountTableLoadLogic extends PageTreeTableLogic {

    CustomTableHeaderDTO rightDto = new CustomTableHeaderDTO();
    SessionDTO session;
    String frequency;
    String history;
    String actualsOrProjections;
    String projectionPeriodorder;
    boolean isProgram;
    List<String> discountList = new ArrayList<String>();
    List<Integer> startAndEndPeriods = new ArrayList<Integer>();
    String year;
    int levelNo;
    boolean isParent;
    boolean isCount;
    List<Leveldto> currentHierarchy;
    boolean isCustomHierarchy;
    String hierarchyIndicator;
//    String hierarchyStartNumber = "";
    boolean dataLoad = false;
    boolean isChildrenAllowed = true;
    int lastLevelNo = 0;
    String userGroup = StringUtils.EMPTY;
    int customId = 0;
    boolean checkAll = false;
    String relationshipBuilderSid = StringUtils.EMPTY;
    DiscountProjectionLogic logic = new DiscountProjectionLogic();
    NMDiscountProjection nmDiscountProjection;
    List levelListBeforeUpdate = new ArrayList();
    ProjectionSelectionDTO projectionSelection;

    private int maxExpandLevelNo = 0;
    
    private boolean isRefresh = false;
            
    private String refreshHierarchyNumbers = StringUtils.EMPTY;

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(NMDiscountTableLoadLogic.class);

    public void setDiscountVariablesForLogic(SessionDTO session, ProjectionSelectionDTO projectionSelection, List<Integer> startAndEndPeriods, boolean isProgram, List<String> discountList,
            int levelNo, boolean isParent, CustomTableHeaderDTO rightDto, String hierarchyIndicator, String userGroup, List<Leveldto> currentHierarchy,
            boolean isCustomHierarchy, int customId, String relationshipBuilderSid) {
        this.rightDto = rightDto;
        this.session = session;
        this.frequency = projectionSelection.getFrequency();
        this.history = projectionSelection.getHistory();
        this.actualsOrProjections = projectionSelection.getActualsOrProjections();
        this.projectionPeriodorder = projectionSelection.getProjectionOrder();
        this.isProgram = isProgram;
        this.discountList = discountList;
        this.year = projectionSelection.getYear();
        this.isCustomHierarchy = isCustomHierarchy;
        this.levelNo = levelNo;
        this.hierarchyIndicator = hierarchyIndicator;
        this.currentHierarchy = currentHierarchy;
        this.isParent = isParent;
        this.startAndEndPeriods = startAndEndPeriods;
//        this.hierarchyStartNumber = hierarchyStartNumber;
        this.customId = customId;
        this.userGroup = userGroup;
        this.relationshipBuilderSid = relationshipBuilderSid;
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
        setLevelNo(levelNo);
        setCurrentPage(1);
    }

    public void setLevelNo(int levelNo) {
        this.levelNo = levelNo;
    }

    public void setCheckAll(boolean checkAll) {
        this.checkAll = checkAll;
    }

    public void setChildrenAllowed(boolean isChildrenAllowed) {
        this.isChildrenAllowed = isChildrenAllowed;
    }

    public int getMaxExpandLevelNo() {
        return maxExpandLevelNo;
    }

    public void setMaxExpandLevelNo(int maxExpandLevelNo) {
        this.maxExpandLevelNo = maxExpandLevelNo;
    }

    public NMDiscountTableLoadLogic(NMDiscountProjection nmDiscountProjection) {
        this.nmDiscountProjection = nmDiscountProjection;
    }

    @Override
    public Map<Integer, Object> loadData(int start, int offset) {
        Map<Integer, Object> map = new HashMap<Integer, Object>();
        List<DiscountProjectionDTO> list = new ArrayList<DiscountProjectionDTO>();
        list = loadLevelData(getLastParent(), start, offset);

        int i = start;
        for (DiscountProjectionDTO dto : list) {

            map.put(i, dto);
            i++;
        }
        return map;
    }

    private List<DiscountProjectionDTO> loadLevelData(Object parentId, int start, int offset) {
        LOGGER.info("inside discount projection loadData method ");
        DiscountProjectionLogic logic = new DiscountProjectionLogic();
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
                LOGGER.info(" dto.getLevelNo() " + dto.getTreeLevelNo()+"    dto.getLevelName()= "+dto.getLevelName());
                isParentChecked = (Boolean) dto.getPropertyValue("checkRecord");
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
//                    if (!hierarchyStartNumber.isEmpty()) {
//                        LOGGER.info(" hierarchyStartNumber " + hierarchyStartNumber);
//                        levelNo = StringUtils.countMatches(hierarchyStartNumber, ".");
////                        levelNo = StringUtils.countMatches(hierarchyStartNumber, ".") + 1;
//                        hierarchyNo = hierarchyStartNumber;
//                    }
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
                customViewDetails.add(session.getCustRelationshipBuilderSid());
                customViewDetails.add(session.getProdRelationshipBuilderSid());
            } else {
                LOGGER.info(" Hierarchy No === " + hierarchyNo);
                LOGGER.info(" level No ===" + levelNumber);
                LOGGER.info(" Hierarchy indicator ===" + tempHierarchyIndicator);
            }
            LOGGER.info(" customTreeLevelNo ===" + treeLevelNo);

            if (checkAll) {
                isParentChecked = true;
            }
            if (levelNumber != 0 && treeLevelNo != 0) {
                list = logic.getDiscountProjection(session, frequency, startAndEndPeriods,
                        history, tempHierarchyIndicator, projectionPeriodorder, userGroup,
                        isProgram, discountList, year, customDetailsList, isParent, isCustomHierarchy, rightDto, start, offset, false, isParentChecked, customViewDetails, false, false, StringUtils.EMPTY, relationshipBuilderSid, false,Collections.EMPTY_LIST,false,StringUtils.EMPTY, StringUtils.EMPTY,Collections.EMPTY_LIST,new HashMap<String,String>());
            }

        } catch (Exception ex) {
             isRefresh = false;
             refreshHierarchyNumbers = StringUtils.EMPTY;
            LOGGER.error("load data" + ex);
            ex.printStackTrace();
        }
                isRefresh = false;
                refreshHierarchyNumbers = StringUtils.EMPTY;
        return list;
    }

    @Override
    public int getCount() {
        return getLevelCount(getLastParent());
    }

    public int getLevelCount(Object parentId) {
        if (!dataLoad) {
            return 0;
        }
        LOGGER.info("inside discount projection getCount method");
        List list = null;
        DiscountProjectionLogic logic = new DiscountProjectionLogic();
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
                LOGGER.info(" dto.getLevelNo() " + dto.getTreeLevelNo());
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
                customViewDetails.add(session.getCustRelationshipBuilderSid());
                customViewDetails.add(session.getProdRelationshipBuilderSid());
            }

            if (levelNumber != 0 && treeLevelNo != 0) {
                setMaxExpandLevelNo(treeLevelNo);
                list = logic.getDiscountProjection(session, frequency, startAndEndPeriods,
                        history, tempHierarchyIndicator, projectionPeriodorder, userGroup,
                        isProgram, discountList, year, customDetailsList, isParent, isCustomHierarchy, rightDto, 0, 0, true, false, customViewDetails, false, false, StringUtils.EMPTY, relationshipBuilderSid, false,Collections.EMPTY_LIST,false,StringUtils.EMPTY, StringUtils.EMPTY,Collections.EMPTY_LIST,new HashMap<String,String>());
            }
            if (list == null || list.isEmpty()) {
                return 0;
            }
        } catch (Exception ex) {
            LOGGER.error("getCount - " + ex);
//            ex.printStackTrace();
        }
        return Integer.valueOf(list.get(0).toString());
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
                List list1 = loadLevelData(parentId, count - neededRecord, neededRecord);
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

//    public void loadExpandData(boolean isExpand, int StartLevelNo, String hierarchyNo) {
//        try {
//            Object ob = new Object();
//
//            List<String> allHierarchyList = CommonLogic.getAllHierarchyNo(hierarchyNo);
//            if (allHierarchyList != null) {
//                clearAll();
//                int flow = allHierarchyList.size();
//                List levelList = new ArrayList();
//                String hierarchyNo2 = "";
//                for (int j = StartLevelNo - 1; j < flow; j++) {
//                    String hierarchyNo1 = String.valueOf(allHierarchyList.get(j));
//                    int levelNo = j + 1;
//                    //To be removed after admin console migration
////                    if(hierarchyNo1.endsWith(".")){
////                        hierarchyNo1 = hierarchyNo1.substring(0, hierarchyNo1.length()-1);
////                    }
//                    DiscountProjectionLogic logic = new DiscountProjectionLogic();
//                    List customDetailsList = new ArrayList();
//                    customDetailsList.add(levelNo);
//                    customDetailsList.add(hierarchyNo1);
//                    customDetailsList.add(levelNo);
//                    boolean isParentChecked = false;
//                    if (checkAll) {
//                        isParentChecked = true;
//                    }
//                    List finalLevelList = logic.getDiscountProjection(session, frequency, startAndEndPeriods,
//                            history, hierarchyIndicator, projectionPeriodorder, userGroup,
//                            isProgram, discountList, year, customDetailsList, isParent, isCustomHierarchy, rightDto, 0, 1000, false, isParentChecked, new ArrayList<String>(), false, false, "", relationshipBuilderSid);
//
//                    levelList.add(finalLevelList.get(0));
//                    int index = logic.getLevelIndex(session.getProjectionId(), hierarchyIndicator, hierarchyNo2, hierarchyNo1);
//
//                    int levelValue = levelList.size();
//                    LevelMap lvlMap = getLevelMap(levelValue, 0, index);
//                    addlevelMap("" + levelValue, lvlMap);
//
//                    if (j < flow - 1) {
//                        hierarchyNo2 = hierarchyNo1;
//                    }
//
//                }
//                if (levelList.size() > 0) {
//                    setLevelList(levelList);
//                    ob = levelList.get(levelList.size() - 1);
//                }
//            }
//            if (isExpand) {
//                updateOnExpand(ob, true);
//            } else {
//                updateOnCollapse(ob, true);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            LOGGER.error(e);
//        }
//    }
//    public void refreshCustomView() {
//        try {
//            Object ob = new Object();
//            List newLevelList = new ArrayList();
//            List oldLevelList = getLevelList();
//            for (int i = 0; i < oldLevelList.size(); i++) {
//                Object ob1 = oldLevelList.get(i);
//                DiscountProjectionDTO oldDto = (DiscountProjectionDTO) ob1;
//                DiscountProjectionLogic logic = new DiscountProjectionLogic();
//                List customList = new ArrayList();
//                customList.add(oldDto.getLevelNo());
//                customList.add(oldDto.getHierarchyNo());
//                customList.add(oldDto.getTreeLevelNo());
//
//                LOGGER.info(" RCV getLevelNo " + oldDto.getLevelNo());
//                LOGGER.info(" RCV getHierarchyNo " + oldDto.getHierarchyNo());
//                LOGGER.info(" RCV getTreeLevelNo " + oldDto.getTreeLevelNo());
//
//                String customerLevelNo = "";
//                String productLevelNo = "";
//                if ("C".equals(oldDto.getHierarchyIndicator())) {
//                    customerLevelNo = "" + oldDto.getTreeLevelNo();
//                    productLevelNo = "%";
//                } else {
//                    customerLevelNo = "%";
//                    productLevelNo = "" + oldDto.getTreeLevelNo();
//                }
//                List<String> customViewDetails = new ArrayList<String>();
//                customViewDetails = new ArrayList<String>();
//                customViewDetails.add("" + customId);
//                customViewDetails.add(customerLevelNo);
//                customViewDetails.add(oldDto.getCustomerHierarchyNo());
//                customViewDetails.add(productLevelNo);
//                customViewDetails.add(oldDto.getProductHierarchyNo());
//                customViewDetails.add(session.getCustRelationshipBuilderSid());
//                customViewDetails.add(session.getProdRelationshipBuilderSid());
//                LOGGER.info(" RCV Custom Id " + customId);
//                LOGGER.info(" RCV customerLevelNo " + customerLevelNo);
//                LOGGER.info(" RCV customerHierarchyNo " + oldDto.getCustomerHierarchyNo());
//                LOGGER.info(" RCV productLevelNo " + productLevelNo);
//                LOGGER.info(" RCV productHierarchyNo " + oldDto.getProductHierarchyNo());
//
//                boolean isParentChecked = false;
//                if (checkAll) {
//                    isParentChecked = true;
//                }
//
//                List<DiscountProjectionDTO> refreshedDataList = logic.getDiscountProjection(session, frequency, startAndEndPeriods,
//                        history, oldDto.getHierarchyIndicator(), projectionPeriodorder, userGroup,
//                        isProgram, discountList, year, customList, isParent, isCustomHierarchy, rightDto, 0, 1000, false, isParentChecked, customViewDetails, false, false, "", relationshipBuilderSid);
//                DiscountProjectionDTO newDto = refreshedDataList.get(0);
//                LOGGER.info("level Name=---" + newDto.getLevelValue());
//                LOGGER.info("getCustomerHierarchyNo=---" + newDto.getCustomerHierarchyNo());
//                LOGGER.info("getProductHierarchyNo=---" + newDto.getProductHierarchyNo());
//                LOGGER.info("getLevelNo=---" + newDto.getLevelNo());
//                LOGGER.info("getTreeLevelNo=-----" + newDto.getTreeLevelNo());
//                newLevelList.add(newDto);
//            }
//            setLevelList(newLevelList);
//            LOGGER.info(" getLevelList() " + getLevelList().size());
//            if (getLevelList().size() > 0) {
//                ob = getLevelList().get(getLevelList().size() - 1);
//            }
//            updateOnExpand(ob, true);
//        } catch (Exception e) {
//            LOGGER.error(e);
////            e.printStackTrace();
//        }
//    }

    @Override
    public Object configureContainer(Object object, Container datasource) {
//        LOGGER.info("inside configure container method");
        DiscountProjectionDTO dto = (DiscountProjectionDTO) object;
        ((CustomTreeContainer<DiscountProjectionDTO>) datasource).addBean(dto);
         if (dto.getTreeLevelNo() == lastLevelNo) {
            ((CustomTreeContainer<DiscountProjectionDTO>) datasource).setChildrenAllowed(dto, false);
        } else {
            ((CustomTreeContainer<DiscountProjectionDTO>) datasource).setChildrenAllowed(dto, isChildrenAllowed);
        }
        return dto;
    }

//    @Override
//    public void configureBeanContainer(Object itemId, Container datasource) {
//
//        LOGGER.info("inside beanscontainer method");
//        ((CustomTreeContainer<DiscountProjectionDTO>) datasource).addBean((DiscountProjectionDTO) itemId);
//    }
    @Override
    protected void saveCurrentPage() {
        LOGGER.info(" Inside saveCurrentPage ");
        super.saveCurrentPage();
        if (nmDiscountProjection != null) {
//            discountProjection.updateCheckedRecord();
            nmDiscountProjection.saveDiscountProjectionListview();
        }
        LOGGER.info(" Exiting saveCurrentPage ");
    }

    public boolean isDataLoad() {
        return dataLoad;
    }

    public void setDataLoad(boolean dataLoad) {
        this.dataLoad = dataLoad;
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

    public Object getExpandedTreeValues(String hierarchyNo) {
        return getExpandedTreeValue(hierarchyNo);
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
        boolean isCustomHierarchy = Constants.IndicatorConstants.INDICATOR_LOGIC_CUSTOM_HIERARCHY.equals(hierarchyIndicator);
        List<DiscountProjectionDTO> refreshedDataList = logic.getDiscountProjection(session, frequency, startAndEndPeriods,
                history, hierarchyIndicator, projectionPeriodorder, userGroup,
                isProgram, discountList, year,
                customDetailsList, true, isCustomHierarchy, rightDto, 0, 0, false, false, customViewDetails, false, true, hierarchyNumbers, relationshipBuilderSid, false,Collections.EMPTY_LIST,false,StringUtils.EMPTY,StringUtils.EMPTY,Collections.EMPTY_LIST,new HashMap<String,String>());

        for (DiscountProjectionDTO dto : refreshedDataList) {
            bulkDataMap.put(tempMap.get(dto.getHierarchyNo()), dto);

        }
        return bulkDataMap;
    }

    public void forRefresh(Set<String> hierachyNos) {
        addExpandedTreeDataFetchable(hierachyNos);
    }

    @Override
    public void setColumnIdToFilterValue(Object prop, Object value) {
        super.setColumnIdToFilterValue(prop, value);
        Object groupDdlb = getColumnIdToFilterValue(Constant.GROUP);
        projectionSelection = getProjectionSelection();
        if (groupDdlb != null) {
            if (projectionSelection != null) {
                if (projectionSelection.isIsCustomHierarchy() || !projectionSelection.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
                    projectionSelection.setGroupFilter(String.valueOf(groupDdlb));
                }
            }
            clearAll();
        }
    }

    public ProjectionSelectionDTO getProjectionSelection() {
        return projectionSelection;
    }

    public void setProjectionSelection(ProjectionSelectionDTO projectionSelection) {
        this.projectionSelection = projectionSelection;
    }

    public CustomTableHeaderDTO getRightDto() {
        return rightDto;
    }

    public String getFrequency() {
        return frequency;
    }

    public List<String> getDiscountList() {
        return discountList;
    }

    public List<Integer> getStartAndEndPeriods() {
        return startAndEndPeriods;
    }

    public int getCustomId() {
        return customId;
    }

    public String getRelationshipBuilderSid() {
        return relationshipBuilderSid;
    }

    public List<Leveldto> getCurrentHierarchy() {
        return currentHierarchy;
    }

    public boolean isIsCustomHierarchy() {
        return isCustomHierarchy;
    }

    public String getHierarchyIndicator() {
        return hierarchyIndicator;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public int getLevelNo() {
        return levelNo;
    }

    /**
     * To set the Hierarchy to refresh
     *
     * @param tableLevelNo
     * @param b
     */
    public void setRefreshHierarchyNo(String hierarchyNo, boolean isrefresh) {
        this.refreshHierarchyNumbers = hierarchyNo;
        this.isRefresh = isrefresh;
    }
    
}
