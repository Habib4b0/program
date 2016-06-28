/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.logic.tablelogic;

import com.stpl.app.customtreecontainer.CustomTreeContainer;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.app.galforecasting.dto.SalesRowDto;
import com.stpl.app.galforecasting.logic.CommonLogic;
import com.stpl.app.galforecasting.logic.SalesProjectionLogic;
import com.stpl.app.galforecasting.ui.form.SalesProjection;
import com.stpl.app.galforecasting.utils.CommonUtils;
import com.stpl.app.galforecasting.utils.Constant;
import static com.stpl.app.galforecasting.utils.Constant.DASH;
import static com.stpl.app.galforecasting.utils.Constant.STRING_EMPTY;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.vaadin.data.Container;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.PageTreeTableLogic;

/**
 *
 * @author maheshj
 */
public class CustomContainerLogic extends PageTreeTableLogic {

    CustomTableHeaderDTO rightDto = new CustomTableHeaderDTO();
    private boolean restrictLoad = true;
    private int projectionId = 0;
    private String viewType = "C";
    private List<Leveldto> currentHierarchy;
    private int levelNo = 1;
    private int expColLevelNo = 1;
    private String hierarchyIndicator = Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY;
    private boolean isCustomHierarchy;
    private String levelFilterValue = STRING_EMPTY;
    private String userId = DASH;
    private String sessionId = DASH;
    private String customId = DASH;
    private String levelName = STRING_EMPTY;
    private String userGroup = STRING_EMPTY;
    private String baseLine = STRING_EMPTY;
    private String methodology = STRING_EMPTY;
    private String lastCustomerHierarchyNo = Constant.PERCENT;
    private String lastProductHierarchyNo = Constant.PERCENT;
    private SalesProjection salesObject;
    String custRelationshipBuilderSid = StringUtils.EMPTY;
    String prodRelationshipBuilderSid = StringUtils.EMPTY;
    String hierarchyNumbers = StringUtils.EMPTY;

    public String getBaseLine() {
        return baseLine;
    }

    public void setBaseLine(String baseLine) {
        this.baseLine = baseLine;
    }

    public String getMethodology() {
        return methodology;
    }

    public void setMethodology(String methodology) {
        this.methodology = methodology;
    }

    public String getHierarchyNumbers() {
        return hierarchyNumbers;
    }

    public void setHierarchyNumbers(String hierarchyNumbers) {
        this.hierarchyNumbers = hierarchyNumbers;
    }

    public SalesProjection getSalesObject() {
        return salesObject;
    }

    public void setSalesObject(SalesProjection salesObject) {
        this.salesObject = salesObject;
    }

    public String getLastCustomerHierarchyNo() {
        return lastCustomerHierarchyNo;
    }

    public void setLastCustomerHierarchyNo(String lastCustomerHierarchyNo) {
        this.lastCustomerHierarchyNo = lastCustomerHierarchyNo;
    }

    public String getLastProductHierarchyNo() {
        return lastProductHierarchyNo;
    }

    public void setLastProductHierarchyNo(String lastProductHierarchyNo) {
        this.lastProductHierarchyNo = lastProductHierarchyNo;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    public String getCustRelationshipBuilderSid() {
        return custRelationshipBuilderSid;
    }

    public void setCustRelationshipBuilderSid(String custRelationshipBuilderSid) {
        this.custRelationshipBuilderSid = custRelationshipBuilderSid;
    }

    public String getProdRelationshipBuilderSid() {
        return prodRelationshipBuilderSid;
    }

    public void setProdRelationshipBuilderSid(String prodRelationshipBuilderSid) {
        this.prodRelationshipBuilderSid = prodRelationshipBuilderSid;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getLevelFilterValue() {
        return levelFilterValue;
    }

    public void setLevelFilterValue(String levelFilterValue) {
        this.levelFilterValue = levelFilterValue;
    }

    public boolean isIsCustomHierarchy() {
        return isCustomHierarchy;
    }

    public void setIsCustomHierarchy(boolean isCustomHierarchy) {
        this.isCustomHierarchy = isCustomHierarchy;
    }

    public String getHierarchyIndicator() {
        return hierarchyIndicator;
    }

    public void setHierarchyIndicator(String hierarchyIndicator) {
        this.hierarchyIndicator = hierarchyIndicator;
    }

    public int getLevelNo() {
        return levelNo;
    }

    public void setLevelNo(int levelNo) {
        this.levelNo = levelNo;
    }

    public List<Leveldto> getCurrentHierarchy() {
        return currentHierarchy;
    }

    public void setCurrentHierarchy(List<Leveldto> currentHierarchy) {
        this.currentHierarchy = currentHierarchy;
    }

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }

    public int getProjectionId() {
        return projectionId;
    }

    public void setProjectionId(int projectionId) {
        this.projectionId = projectionId;
    }

    public boolean isRestrictLoad() {
        return restrictLoad;
    }

    public void setRestrictLoad(boolean restrictLoad) {
        this.restrictLoad = restrictLoad;
    }

    public CustomTableHeaderDTO getRightDto() {
        return rightDto;
    }

    public void setRightDto(CustomTableHeaderDTO rightDto) {
        this.rightDto = rightDto;
    }

    public CustomContainerLogic(CustomTableHeaderDTO rightDto) {
        this.rightDto = rightDto;
    }

    public String getCustomId() {
        return customId;
    }

    public void setCustomId(String customId) {
        this.customId = customId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public CustomContainerLogic() {
    }

    @Override
    public Map<Integer, Object> loadData(int start, int offset) {
        Map<Integer, Object> map = new HashMap<Integer, Object>();
        List<SalesRowDto> list = new ArrayList<SalesRowDto>();
        if (!isRestrictLoad()) {
            list = loadLevelData(getLastParent(), start, offset);
        }
        int i = start;
        for (SalesRowDto dto : list) {
            map.put(i, dto);
            i++;
        }
        return map;
    }

    @Override
    public int getCount() {
        return getLevelCount(getLastParent());
    }

    public Collection<Object> getLevelList() {
        return getExpandedTreeList().values();
    }

    @Override
    public Object configureContainer(Object object, Container datasource) {

        if (restrictLoad) {
            datasource.removeAllItems();
        }
        SalesRowDto salesRowDto = (SalesRowDto) object;
        ((CustomTreeContainer<SalesRowDto>) datasource).addBean((SalesRowDto) object);
        if (levelFilterValue.equals(STRING_EMPTY)) {
            ((CustomTreeContainer<SalesRowDto>) datasource).setChildrenAllowed(salesRowDto, true);
        } else {
            ((CustomTreeContainer<SalesRowDto>) datasource).setChildrenAllowed(salesRowDto, false);
        }

        if (currentHierarchy != null) {

            Leveldto levelDto = currentHierarchy.get(currentHierarchy.size() - 1);
            if (!isCustomHierarchy) {
                if (levelDto.getLevelNo() == salesRowDto.getLevelNo()) {
                    ((CustomTreeContainer<SalesRowDto>) datasource).setChildrenAllowed(salesRowDto, false);
                }

            } else {
                if (levelDto.getTreeLevelNo() == salesRowDto.getTreeLevelNo() - 1) {
                    ((CustomTreeContainer<SalesRowDto>) datasource).setChildrenAllowed(salesRowDto, false);
                }
            }

        }

        return object;
    }

    @Override
    protected void saveCurrentPage() {
        super.saveCurrentPage();
        boolean reloadGroup = false;
        if (salesObject.editedGroupValues.size() > 0) {
            reloadGroup = true;
        }
        if (!isCustomHierarchy) {
            salesObject.saveEditedRecs();
        }
        if (reloadGroup) {
            salesObject.loadUserGroup();
            salesObject.loadLevelFilter();
            salesObject.setCustomId(Integer.parseInt(getCustomId()));
            salesObject.loadLevelFilterValue();
        }
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

    public List<SalesRowDto> loadLevelData(Object parentId, int start, int offset) {
        List<SalesRowDto> list = new ArrayList<SalesRowDto>();
        try {
            List<String> indexList = new ArrayList<String>(getNonFetchableData().keySet());
            int levelNumber = 1;
            String parentName = StringUtils.EMPTY;
            String hierarchyIndicator = "C";
            int treeLevelNo = 1;
            List customDetailsList = new ArrayList();

            Object[] selection = new Object[24];
            selection[0] = StringUtils.EMPTY + getProjectionId() + StringUtils.EMPTY;
            selection[1] = levelNo;
            selection[2] = levelName;
            selection[3] = rightDto;
            selection[4] = false;
            selection[5] = start;
            selection[6] = offset;
            selection[7] = false;
            selection[8] = "fetchSalesResult";
            selection[9] = getViewType();
            selection[11] = isCustomHierarchy;
            selection[13] = getUserId();
            selection[14] = getSessionId();
            selection[15] = customId;
            selection[17] = getViewType();

            SalesProjectionLogic logic = new SalesProjectionLogic();
            if (parentId != null && (parentId instanceof SalesRowDto)) {

                if (levelFilterValue.equals(STRING_EMPTY)) {

                    SalesRowDto dto = (SalesRowDto) parentId;
                    selection[1] = dto.getLevelNo() + 1;
                    selection[2] = dto.getActualLevel();
                    selection[16] = dto.getHierarchyNo();
                    selection[19] = dto.getLastCustomerHierarchyno();
                    selection[20] = dto.getLastProductHierarchyno();
                    int tempLevelNumber = dto.getLevelNo() + 1;
                    if (isCustomHierarchy) {
                        tempLevelNumber = dto.getTreeLevelNo();
                    }
                    Leveldto levelDto = CommonLogic.getNextLevel(tempLevelNumber, currentHierarchy);

                    if (levelDto != null) {
                        if (isCustomHierarchy) {
                            parentName = levelDto.getParentNode();
                            hierarchyIndicator = levelDto.getHierarchyIndicator();
                            levelNumber = levelDto.getLevelNo();
                            treeLevelNo = dto.getTreeLevelNo();
                            selection[2] = STRING_EMPTY;
                            selection[1] = dto.getTreeLevelNo();
                        } else {
                            levelNumber = levelDto.getLevelNo();
                            parentName = dto.getLevelName();
                            treeLevelNo = levelNumber;
                            hierarchyIndicator = levelDto.getHierarchyIndicator();
                        }

                        selection[9] = hierarchyIndicator;

                    }
                    if (isCustomHierarchy) {
                        selection[17] = dto.getParentHierarchyType();
                    }

                } else {

                    selection[8] = "fetchByHierarchyNo";
                    selection[12] = levelFilterValue;
                    selection[13] = getUserId();
                    selection[14] = getSessionId();
                }

            } else {

                lastCustomerHierarchyNo = Constant.PERCENT;
                lastProductHierarchyNo = Constant.PERCENT;

                selection[19] = lastCustomerHierarchyNo;
                selection[20] = lastProductHierarchyNo;

                if (levelFilterValue.equals(STRING_EMPTY)) {
                    int tempLevelNumber = levelNo;
                    if (isCustomHierarchy) {
                        tempLevelNumber = 1;
                    }

                    Leveldto levelDto = CommonLogic.getNextLevel(tempLevelNumber, currentHierarchy);

                    if (levelDto != null) {
                        parentName = levelDto.getParentNode();
                        hierarchyIndicator = levelDto.getHierarchyIndicator();
                        levelNumber = levelDto.getLevelNo();
                        if (isCustomHierarchy) {
                            treeLevelNo = 1;
                            selection[2] = STRING_EMPTY;
                            selection[1] = 1;

                        } else {
                            treeLevelNo = levelNumber;
                        }
                        selection[9] = hierarchyIndicator;
                    }

                } else {

                    selection[8] = "fetchByHierarchyNo";
                    selection[12] = levelFilterValue;
                    selection[13] = getUserId();
                    selection[14] = getSessionId();
                }
                selection[16] = STRING_EMPTY;
            }

            selection[18] = getUserGroup();
            selection[21] = getMethodology();
            selection[22] = getBaseLine();

            if (String.valueOf(selection[9]).equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
                if (!String.valueOf(selection[16]).equals(STRING_EMPTY)) {
                    lastCustomerHierarchyNo = (String) selection[16];
                }

            } else {
                if (!String.valueOf(selection[16]).equals(STRING_EMPTY)) {
                    lastProductHierarchyNo = (String) selection[16];
                }
            }

            String hierarchyNo = (String) selection[16];

            customDetailsList.add(levelNumber);
            customDetailsList.add(parentName);
            customDetailsList.add(treeLevelNo);

            selection[10] = customDetailsList;

            list = logic.generateSalesProjection(selection, salesObject);

        } catch (Exception ex) {
            Logger.getLogger(SalesProjectionLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public int getLevelCount(Object parentId) {
        SalesProjectionLogic logic = new SalesProjectionLogic();
        int count = 0;
        try {
            String hierarchyIndicat = "C";
            int treeLevelNo = levelNo;
            String hierarchyNo = StringUtils.EMPTY;
            String productHierarchyNo = StringUtils.EMPTY;
            String customerHierarchyNo = StringUtils.EMPTY;
            int useId = Integer.parseInt(getUserId());
            int sesId = Integer.parseInt(getSessionId());
            int custId = 0;
            if (isCustomHierarchy) {
                custId = Integer.parseInt(getCustomId());
            }
            if (parentId != null && (parentId instanceof SalesRowDto)) {

                SalesRowDto dto = (SalesRowDto) parentId;
                hierarchyNo = dto.getHierarchyNo();
                treeLevelNo = dto.getLevelNo() + 1;
                String parentInd = dto.getParentHierarchyType();
                if (isCustomHierarchy) {
                    treeLevelNo = dto.getTreeLevelNo();
                    productHierarchyNo = dto.getLastProductHierarchyno();
                    customerHierarchyNo = dto.getLastCustomerHierarchyno();
                    if (parentInd.equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
                        customerHierarchyNo = hierarchyNo;
                    } else if (parentInd.equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
                        productHierarchyNo = hierarchyNo;
                    }
                }
            }
            Leveldto levelDto = CommonLogic.getNextLevel(treeLevelNo, currentHierarchy);
            if (levelDto != null) {
                hierarchyIndicat = levelDto.getHierarchyIndicator();
            }
            if (!levelFilterValue.equals(STRING_EMPTY)) {

                treeLevelNo = Integer.parseInt(levelFilterValue);
            }
            expColLevelNo = treeLevelNo;
            count = CommonLogic.getSalesCount(projectionId, hierarchyIndicat, treeLevelNo, hierarchyNo, productHierarchyNo, customerHierarchyNo, isCustomHierarchy, custId, userGroup, useId, sesId, getCustRelationshipBuilderSid(), getProdRelationshipBuilderSid());

        } catch (Exception ex) {
            Logger.getLogger(SalesProjectionLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    protected void recursivelyLoadExpandData(Object parentId, String treeLevel, int expandLevelNo, int lastlevelNo) {
        int count = getLevelCount(parentId);
        LevelMap levelMap = new LevelMap(count, getColumnIdToFilterMap());
        addlevelMap(treeLevel, levelMap);
        if (expandLevelNo >= expColLevelNo) {
            List list = new ArrayList();
            int neededRecord = count;
            for (int j = 1; j <= count; j++) {
                String customTreeLevel = treeLevel + (j) + ".";
                Object ob = getCurrentPageData(customTreeLevel);

                if (ob != null) {

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
                SalesRowDto dto = (SalesRowDto) (list.get(j));
                String customTreeLevel = treeLevel + (index + j) + ".";
                addExpandedTreeList(customTreeLevel, dto);
                if (isCustomHierarchy) {
                    if ((dto.getTreeLevelNo() - 1) != lastlevelNo) {
                        recursivelyLoadExpandData(dto, customTreeLevel, expandLevelNo, lastlevelNo);
                    }
                } else {
                    if (dto.getLevelNo() != lastlevelNo) {
                        recursivelyLoadExpandData(dto, customTreeLevel, expandLevelNo, lastlevelNo);
                    }
                }
            }
        }
    }

    public void loadExpandData(int levelNo) {
        int lastlevelNo = levelNo;
        if (currentHierarchy != null) {
            Leveldto levelDto = currentHierarchy.get(currentHierarchy.size() - 1);
            if (isCustomHierarchy) {
                lastlevelNo = levelDto.getTreeLevelNo();
            } else {
                lastlevelNo = levelDto.getLevelNo();
            }
        }
        super.saveCurrentPage();
        recursivelyLoadExpandData(new Object(), StringUtils.EMPTY, levelNo, lastlevelNo);
        setRecordCount(getCalculatedTotalRecordCount());
        setCurrentPage(getTotalAmountOfPages());
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

    public void forRefresh(Set<String> hierachyNos) {

        addExpandedTreeDataFetchable(hierachyNos);

    }

    @Override
    public Map<String, Object> loadBulkData(Map<String, Object> bulkDataMap) {
        Map<String, String> tempMap = new HashMap<String, String>();
        List<String> hiearchyNoList = new ArrayList<String>();

        for (String key : bulkDataMap.keySet()) {
            String tempLevelValue = StringUtils.EMPTY;
            SalesRowDto dto = (SalesRowDto) bulkDataMap.get(key);
            tempLevelValue = dto.getHierarchyNo();
            tempMap.put(tempLevelValue, key);
            hiearchyNoList.add(tempLevelValue);
        }

        String hierarchyNumbers = CommonUtils.CollectionToString(hiearchyNoList, true);

        List customDetailsList = new ArrayList();
        customDetailsList.add(0);
        customDetailsList.add(StringUtils.EMPTY);
        customDetailsList.add(0);
        SalesProjectionLogic logic = new SalesProjectionLogic();

        Object selection[] = new Object[24];

        selection[8] = "refreshData";
        selection[0] = String.valueOf(getProjectionId());
        selection[13] = getUserId();
        selection[14] = getSessionId();
        selection[9] = getViewType();
        selection[12] = hierarchyNumbers;
        selection[3] = rightDto;
        selection[5] = 1;
        selection[6] = 1;
        selection[7] = false;
        selection[10] = customDetailsList;
        selection[11] = isIsCustomHierarchy();
        selection[18] = getUserGroup();
        selection[21] = getMethodology();
        selection[22] = getBaseLine();
        selection[19] = getLastCustomerHierarchyNo();
        selection[20] = getLastProductHierarchyNo();

        List<SalesRowDto> refreshedDataList = logic.generateSalesProjection(selection, salesObject);

        for (SalesRowDto dto : refreshedDataList) {
            bulkDataMap.put(tempMap.get(dto.getHierarchyNo()), dto);

        }

        return bulkDataMap;
    }

    public Object getExpandedTreeValues(String treeLevel) {
        return getExpandedTreeValue(treeLevel);
    }

}
