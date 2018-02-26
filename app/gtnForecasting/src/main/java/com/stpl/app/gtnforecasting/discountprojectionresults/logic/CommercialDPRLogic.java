
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.discountprojectionresults.logic;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gtnforecasting.discountprojectionresults.dto.DiscountProjectionResultsDTO;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.xmlparser.SQlUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.app.utils.Constants;
import static com.stpl.app.utils.Constants.ButtonConstants.ALL;
import static com.stpl.app.utils.Constants.FrequencyConstants.*;
import static com.stpl.app.utils.Constants.IndicatorConstants.INDICATOR_VIEW_CUSTOMER;
import static com.stpl.app.utils.Constants.IndicatorConstants.INDICATOR_VIEW_PRODUCT;
import static com.stpl.app.utils.Constants.LabelConstants.*;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.QueryUtil;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Sibi.Chakaravarthy
 */
public class CommercialDPRLogic {

    protected Logger LOGGER = LoggerFactory.getLogger(CommercialDPRLogic.class);
    private static final DecimalFormat DOLLAR_RPU_FORMAT = new DecimalFormat("#,##0.00");
    private static final DecimalFormat UNIT_VOLUME_FORMAT = new DecimalFormat("#,##0.000");
    private static final DecimalFormat EXFAC_PER_FORMAT = new DecimalFormat("#,##0.00");

    /**
     * This method used to get count of the child node
     *
     * @param parentId
     * @param projSelDTO
     * @param isLevelsCount
     * @return
     *
     */
    public int getConfiguredProjectionResultsCount(Object parentId, ProjectionSelectionDTO projSelDTO, boolean isLevelsCount)  {
        int count = 0;
        if (!projSelDTO.isIsFilter() || (parentId instanceof DiscountProjectionResultsDTO)) {
            projSelDTO.setYear(ALL.getConstant());
            if (BOTH.getConstant().equals(projSelDTO.getActualsOrProjections())) {
                projSelDTO.setActualsOrProjections(ACTUALS.getConstant() + Constant.AND_SMALL_SPACE + PROJECTIONS.getConstant());
            }
            if (parentId instanceof DiscountProjectionResultsDTO) {
                projSelDTO.setIsProjectionTotal(false);
                DiscountProjectionResultsDTO parentDto = (DiscountProjectionResultsDTO) parentId;
                projSelDTO.setLevelNo(parentDto.getLevelNo());
                projSelDTO.setTreeLevelNo(parentDto.getTreeLevelNo());
                projSelDTO.setLevelValue(parentDto.getLevelValue());
                projSelDTO.setParentNode(parentDto.getParentNode());
                projSelDTO.setHierarchyNo(parentDto.getHierarchyNo());
                if (INDICATOR_VIEW_CUSTOMER.getConstant().equals(parentDto.getHierarchyIndicator())) {
                    projSelDTO.setCustomerHierarchyNo(projSelDTO.getHierarchyNo());
                    projSelDTO.setProductHierarchyNo(parentDto.getProductHierarchyNo());
                } else if (INDICATOR_VIEW_PRODUCT.getConstant().equals(parentDto.getHierarchyIndicator())) {
                    projSelDTO.setProductHierarchyNo(projSelDTO.getHierarchyNo());
                    projSelDTO.setCustomerHierarchyNo(parentDto.getCustomerHierarchyNo());
                }
                projSelDTO.setHierarchyIndicator(parentDto.getHierarchyIndicator());
                projSelDTO.setIsProjectionTotal(parentDto.getProjectionTotal() == 1);
                projSelDTO.setGroup(parentDto.getGroup());
                projSelDTO.setIsTotal(parentDto.getParent() == 1);
                projSelDTO.setTotal(parentDto.getTotal());
            } else {
                projSelDTO.setIsProjectionTotal(true);
                projSelDTO.setIsTotal(false);
                if (projSelDTO.isIsCustomHierarchy()) {
                    projSelDTO.setLevelNo(0);
                    projSelDTO.setTreeLevelNo(0);
                } else if (INDICATOR_VIEW_CUSTOMER.getConstant().equals(projSelDTO.getHierarchyIndicator())) {
                    projSelDTO.setLevelNo(projSelDTO.getCustomerLevelNo() - 1);
                    projSelDTO.setTreeLevelNo(projSelDTO.getCustomerLevelNo() - 1);
                } else if (INDICATOR_VIEW_PRODUCT.getConstant().equals(projSelDTO.getHierarchyIndicator())) {
                    projSelDTO.setLevelNo(projSelDTO.getProductLevelNo() - 1);
                    projSelDTO.setTreeLevelNo(projSelDTO.getProductLevelNo() - 1);
                }
                projSelDTO.setGroup(StringUtils.EMPTY);
                projSelDTO.setHierarchyNo(StringUtils.EMPTY);
                projSelDTO.setProductHierarchyNo(StringUtils.EMPTY);
                projSelDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
            }
            count += getProjectionResultsCount(projSelDTO, isLevelsCount, parentId);
        } else {
            projSelDTO.setIsProjectionTotal(false);
            projSelDTO.setIsTotal(true);
            projSelDTO.setHierarchyNo(StringUtils.EMPTY);
            projSelDTO.setLevelNo(projSelDTO.getFilterLevelNo());
            projSelDTO.setTreeLevelNo(projSelDTO.getFilterLevelNo());
            count += configureLevelsCount(projSelDTO);
        }
        return count;
    }

    public int getTotalDiscountCount(ProjectionSelectionDTO projSelDTO, Object parentId) {
        String query = getDiscountCountForCurrentHierarchy(projSelDTO);
        List<Object> list = (List<Object>) HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames()));
        if (list != null && !list.isEmpty()) {
            Object ob = list.get(0);
            if (parentId instanceof DiscountProjectionResultsDTO) {
                DiscountProjectionResultsDTO parentDTO = (DiscountProjectionResultsDTO) parentId;
                parentDTO.setTotalDiscountCount(Integer.valueOf(String.valueOf(ob)));
                return parentDTO.getTotalDiscountCount();
            }
        }
        return 0;
    }

    public int getProjectionResultsCount(ProjectionSelectionDTO projSelDTO, boolean isLevelsCount, Object parentId)  {
        int count = 0;
        projSelDTO.setGroupCount(false);
        if (!projSelDTO.getGroup().startsWith(Constant.DISCOUNT)) {
            if (projSelDTO.getPivotView().contains(PERIOD.getConstant())) {
                if (projSelDTO.isIsProjectionTotal()) {
                    count = count + projSelDTO.getDiscountList().get(0).size();
                } else {
                    count = count + getTotalDiscountCount(projSelDTO, parentId);
                }
            } else {
                count = count + projSelDTO.getPeriodList().size();
            }
        }
        if (projSelDTO.isIsProjectionTotal()) {
            count++;
        }
        try {
            if (isLevelsCount && !projSelDTO.isIsFilter()) {
                if ((projSelDTO.getTreeLevelNo() + 1) == projSelDTO.getTpLevel()
                        && ((projSelDTO.isIsCustomHierarchy()) || (!INDICATOR_VIEW_PRODUCT.getConstant().equals(projSelDTO.getHierarchyIndicator())))
                        && !Constant.ALL_DISCOUNT_GROUP.equals(projSelDTO.getGroupFilter()) && !projSelDTO.getGroup().startsWith(Constant.DISCOUNT)) {                    
                    count = count + 1;
                    projSelDTO.setGroupCount(true);
                    projSelDTO.setLevelCount(1);
                } else {
                    int maxLevelNo = Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projSelDTO.getHierarchyIndicator()) ? projSelDTO.getSessionDTO().getMaximumCustomerLevel() : projSelDTO.getSessionDTO().getMaximumProductLevel();
                    if (projSelDTO.getTreeLevelNo() + 1 <= maxLevelNo) {
                        projSelDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo() + 1);
                        int ct = configureLevelsCount(projSelDTO);
                        count = count + ct;
                        projSelDTO.setLevelCount(ct);
                    } else {
                        projSelDTO.setLevelCount(0);
                    }
                }
            }
        } catch (Exception ex) {            
            LOGGER.error(ex.getMessage());
        }
        return count;
    }

    /**
     *
     * @param selection
     * @return
     */
    private String getDiscountCountForCurrentHierarchy(ProjectionSelectionDTO selection) {
        CommonLogic commonLogic = new CommonLogic();
        String countQuery;
        if (selection.isIsCustomHierarchy()) {
            countQuery = SQlUtil.getQuery(Constant.INSERT_SELECTED_RS_MODEL);
            countQuery += SQlUtil.getQuery("get-custom-count-rebate");
            countQuery = countQuery.replace(Constant.RS_CONTRACT_SID_QUESTION, getSelectedRSIDForQuery(selection));
            countQuery = countQuery.replace("[?HIERARCHY_NO]", selection.getHierarchyNo());
            Map<String, String> customHierarchyRestriction = getHierarchyDetailsForCustomView(selection.getCustomerHierarchyNo(), selection.getProductHierarchyNo(), commonLogic.getHiearchyIndicatorFromCustomView(selection));
            countQuery = countQuery.replace(Constant.HIERARCHY_COLUMN_QUESTION, customHierarchyRestriction.get(Constant.CURRENT_HIERARCHY));
            countQuery = countQuery.replace(Constant.PREVIOUS_HIERARCHY_NO_QUESTION, customHierarchyRestriction.containsKey(Constant.PRIOR_HIERARCHY) ? customHierarchyRestriction.get(Constant.PRIOR_HIERARCHY) : StringUtils.EMPTY);
        } else {
            countQuery = SQlUtil.getQuery(Constant.INSERT_SELECTED_RS_MODEL);
            countQuery += SQlUtil.getQuery("get-count-rebate");
            countQuery = countQuery.replace(Constant.RS_CONTRACT_SID_QUESTION, getSelectedRSIDForQuery(selection));
            countQuery = countQuery.replace(Constant.HIERARCHY_COLUMN_QUESTION, commonLogic.getColumnName(selection.getHierarchyIndicator()));
            countQuery = countQuery.replace("[?HIERARCHY_NO]", selection.getHierarchyNo());
        }
        return countQuery;
    }

    /**
     *
     * @param selection
     * @return
     */
    private int configureLevelsCount(ProjectionSelectionDTO selection) {
        String query;
        if (selection.isIsCustomHierarchy()) {
            query = getCustomLevelCount(selection);
        } else {
            query = getLevelCount(selection);
        } 
        List list = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, selection.getSessionDTO().getCurrentTableNames()));
        int count = 0;
        if (list != null && !list.isEmpty()) {
            count = Integer.parseInt(list.get(0).toString());
        }
        return count;
    }

    private String getLevelCount(ProjectionSelectionDTO selection) {
        CommonLogic commonLogic = new CommonLogic();
        String countQuery = SQlUtil.getQuery(Constant.INSERT_SELECTED_RS_MODEL);
        countQuery += SQlUtil.getQuery("insert-selected-hierarchy-no-dpr");
        countQuery += SQlUtil.getQuery("get-count-dpr");

        countQuery = countQuery.replace(Constant.RS_CONTRACT_SID_QUESTION, getSelectedRSIDForQuery(selection));
        countQuery = countQuery.replace(Constant.QUESTION_HIERARCHY_NO_VALUES, commonLogic.getSelectedHierarchy(selection.getSessionDTO(), selection.getHierarchyNo(), selection.getHierarchyIndicator(), selection.getTreeLevelNo()));
        countQuery = countQuery.replace("[?SELECTED_HIERARCHY_JOIN]", commonLogic.getHierarchyJoinQuery(selection));
          if(selection.getHierarchyIndicator().equalsIgnoreCase("C") && !selection.getGroupFilter().equalsIgnoreCase(Constant.ALL_DISCOUNT_GROUP)){
                countQuery = countQuery.replace(Constant.USER_GROUP_JOIN, SQlUtil.getQuery(Constant.USER_GROUP_CONDITIONQUERY).replace(USER_GROUP_QUESTION, selection.getGroupFilter())); 
            }else{
               countQuery = countQuery.replace(Constant.USER_GROUP_JOIN, StringUtils.EMPTY);  
            }
        countQuery = countQuery.replace(Constant.HIERARCHY_COLUMN_QUESTION, commonLogic.getColumnName(selection.getHierarchyIndicator()));
        
        return countQuery;

    }
    public static final String USER_GROUP_QUESTION = "[?USER_GROUP]";

    private String getCustomLevelCount(ProjectionSelectionDTO selection) {
        CommonLogic commonLogic = new CommonLogic();
        String countQuery = SQlUtil.getQuery(Constant.INSERT_SELECTED_RS_MODEL);
        countQuery += SQlUtil.getQuery("insert-selected-hierarchy-no-dpr-custom-view");
        countQuery += SQlUtil.getQuery("get-count-dpr");

        countQuery = countQuery.replace(Constant.RS_CONTRACT_SID_QUESTION, getSelectedRSIDForQuery(selection));

        String currentHierarchyIndicator = commonLogic.getHiearchyIndicatorFromCustomView(selection);
        int levelNo = commonLogic.getActualLevelNoFromCustomView(selection);
        switch (String.valueOf(currentHierarchyIndicator)) {
            case Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY:
                countQuery = countQuery.replace(Constant.QUESTION_HIERARCHY_NO_VALUES, commonLogic.getSelectedHierarchy(selection.getSessionDTO(), selection.getCustomerHierarchyNo(), currentHierarchyIndicator, levelNo));
                break;
            case Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY:
                countQuery = countQuery.replace(Constant.QUESTION_HIERARCHY_NO_VALUES, commonLogic.getSelectedHierarchy(selection.getSessionDTO(), selection.getProductHierarchyNo(), currentHierarchyIndicator, levelNo));
                break;
            default:
                LOGGER.warn("Invalid Hierarchy Indicator+ {}" , currentHierarchyIndicator);
        }
        Map<String, String> customHierarchyRestriction = getHierarchyDetailsForCustomView(selection.getCustomerHierarchyNo(), selection.getProductHierarchyNo(), commonLogic.getHiearchyIndicatorFromCustomView(selection));
        countQuery = countQuery.replace("[?CURRENT_HIERARCHY_NO]", customHierarchyRestriction.get(Constant.CURRENT_HIERARCHY));
        countQuery = countQuery.replace(Constant.PREVIOUS_HIERARCHY_NO_QUESTION, customHierarchyRestriction.containsKey(Constant.PRIOR_HIERARCHY) ? customHierarchyRestriction.get(Constant.PRIOR_HIERARCHY) : StringUtils.EMPTY);

        return countQuery;

    }

    /**
     * Method is used to build the join query. This join determines that
     * hierarchy no associated with CCP that is used to manipulate the data.
     *
     * @param isCustomHierarchy
     * @param customerHierarchyNo
     * @param productHierarchyNo
     * @param hierarchyIndicator
     * @return
     */
    public Map<String, String> getHierarchyDetailsForCustomView(String customerHierarchyNo, String productHierarchyNo, String hierarchyIndicator) {

        Map<String, String> map = new HashMap<>(NumericConstants.TWO);

        switch (String.valueOf(hierarchyIndicator)) {
            case Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY:
                map.put(Constant.CURRENT_HIERARCHY, "CUST_HIERARCHY_NO ");
                if (StringUtils.isNotBlank(productHierarchyNo)) {
                    StringBuilder priorHierarchy = new StringBuilder();
                    priorHierarchy.append(" AND CH.PROD_HIERARCHY_NO LIKE '");
                    priorHierarchy.append(productHierarchyNo);
                    priorHierarchy.append("%' ");
                    map.put(Constant.PRIOR_HIERARCHY, priorHierarchy.toString());
                }
                break;
            case Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY:
                map.put(Constant.CURRENT_HIERARCHY, "PROD_HIERARCHY_NO ");
                if (StringUtils.isNotBlank(customerHierarchyNo)) {
                    StringBuilder priorHierarchy = new StringBuilder();
                    priorHierarchy.append(" AND CH.CUST_HIERARCHY_NO LIKE '");
                    priorHierarchy.append(customerHierarchyNo);
                    priorHierarchy.append("%' ");
                    map.put(Constant.PRIOR_HIERARCHY, priorHierarchy.toString());
                }
                break;
            default:
                LOGGER.warn("Invalid hierarchy Indicator= {}" , hierarchyIndicator);
        }

        return map;
    }

    /**
     * Returns the List of RS Model SID as Comma separated String.
     *
     * @param sessionDTO
     * @return
     */
    private String getSelectedRSIDForQuery(ProjectionSelectionDTO selection) {
        StringBuilder stringBuilder = new StringBuilder();
        List<String> discountIdList = selection.getDiscountList().get(0);
        for (String rsId : discountIdList) {
            stringBuilder.append("(");
            stringBuilder.append(rsId);
            stringBuilder.append(")");
            stringBuilder.append(ConstantsUtils.COMMA);
        }
        return stringBuilder.substring(0, stringBuilder.length() - 1);
    }

    /**
     *
     * @param parentId
     * @param start
     * @param offset
     * @param projSelDTO
     * @return
     *
     */
    public List<DiscountProjectionResultsDTO> getConfiguredProjectionResults(Object parentId, int start, int offset, ProjectionSelectionDTO projSelDTO) {
        List<DiscountProjectionResultsDTO> resultList;
        if (!projSelDTO.isIsFilter() || (parentId instanceof DiscountProjectionResultsDTO)) {

            projSelDTO.setYear(ALL.getConstant());
            if (BOTH.getConstant().equals(projSelDTO.getActualsOrProjections())) {
                projSelDTO.setActualsOrProjections(ACTUALS.getConstant() + Constant.AND_SMALL_SPACE + PROJECTIONS.getConstant());
            }
            if (parentId instanceof DiscountProjectionResultsDTO) {
                projSelDTO.setIsProjectionTotal(false);
                DiscountProjectionResultsDTO parentDto = (DiscountProjectionResultsDTO) parentId;
                projSelDTO.setLevelNo(parentDto.getLevelNo());
                projSelDTO.setTreeLevelNo(parentDto.getTreeLevelNo());
                projSelDTO.setLevelValue(parentDto.getLevelValue());
                projSelDTO.setParentNode(parentDto.getParentNode());
                projSelDTO.setHierarchyNo(parentDto.getHierarchyNo());
                if (INDICATOR_VIEW_CUSTOMER.getConstant().equals(parentDto.getHierarchyIndicator())) {
                    projSelDTO.setCustomerHierarchyNo(projSelDTO.getHierarchyNo());
                    projSelDTO.setProductHierarchyNo(parentDto.getProductHierarchyNo());
                } else if (INDICATOR_VIEW_PRODUCT.getConstant().equals(parentDto.getHierarchyIndicator())) {
                    projSelDTO.setProductHierarchyNo(projSelDTO.getHierarchyNo());
                    projSelDTO.setCustomerHierarchyNo(parentDto.getCustomerHierarchyNo());
                }
                projSelDTO.setHierarchyIndicator(parentDto.getHierarchyIndicator());
                projSelDTO.setIsProjectionTotal(parentDto.getProjectionTotal() == 1);
                projSelDTO.setGroup(parentDto.getGroup());
                projSelDTO.setIsTotal(parentDto.getParent() == 1);
                /* Added By Nanadhaa */
                projSelDTO.setTotal(parentDto.getTotal());
            } else {
                projSelDTO.setIsProjectionTotal(true);
                projSelDTO.setIsTotal(false);
                if (projSelDTO.isIsCustomHierarchy()) {
                    projSelDTO.setTreeLevelNo(0);
                    projSelDTO.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
                } else if (INDICATOR_VIEW_CUSTOMER.getConstant().equals(projSelDTO.getHierarchyIndicator())) {
                    projSelDTO.setLevelNo(projSelDTO.getCustomerLevelNo() - 1);
                    projSelDTO.setTreeLevelNo(projSelDTO.getCustomerLevelNo() - 1);
                } else if (INDICATOR_VIEW_PRODUCT.getConstant().equals(projSelDTO.getHierarchyIndicator())) {
                    projSelDTO.setLevelNo(projSelDTO.getProductLevelNo() - 1);
                    projSelDTO.setTreeLevelNo(projSelDTO.getProductLevelNo() - 1);
                }

                projSelDTO.setGroup(StringUtils.EMPTY);
                projSelDTO.setHierarchyNo(StringUtils.EMPTY);
                projSelDTO.setProductHierarchyNo(StringUtils.EMPTY);
                projSelDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
            }
            resultList = getProjectionResults(start, offset, projSelDTO,parentId);
        } else {
            projSelDTO.setIsProjectionTotal(false);
            projSelDTO.setIsTotal(true);
            projSelDTO.setLevelNo(projSelDTO.getFilterLevelNo());
            projSelDTO.setTreeLevelNo(projSelDTO.getFilterLevelNo());
            boolean isPeriodView = projSelDTO.getPivotView().contains(PERIOD.getConstant());
            resultList = configureLevels(start, offset, projSelDTO, isPeriodView);
        }
        return resultList;
    }

    public List<DiscountProjectionResultsDTO> getProjectionResults(int start, int offset, ProjectionSelectionDTO projSelDTO, final Object parentId) {
        int neededRecord = offset;
        List<DiscountProjectionResultsDTO> dataList = new ArrayList<>();
        boolean isPeriodView = projSelDTO.getPivotView().contains(PERIOD.getConstant());

        if (projSelDTO.isIsProjectionTotal()) {

            if (start == 0) {
                dataList.addAll(getProjectionTotal(projSelDTO, isPeriodView));
                neededRecord--;

                if (!projSelDTO.getDiscountNameList().isEmpty()) {
                    List discountList = getAllRSDiscount(projSelDTO, isPeriodView);
                    if (isPeriodView) {
                        dataList.addAll(discountList);
                        neededRecord = neededRecord - discountList.size();
                    } else {
                        int recordsToFetch = neededRecord < discountList.size() ? neededRecord : discountList.size();
                        dataList.addAll(discountList.subList(start, start + recordsToFetch));
                        neededRecord = neededRecord < discountList.size() ? 0 : neededRecord - discountList.size();
                    }
                }

            } else {
                start--;
                if (isPeriodView) {                    
                    start = start - projSelDTO.getDiscountList().get(0).size();
                } else {
                    if (start < projSelDTO.getPeriodList().size()) {
                        List discountList = getAllRSDiscount(projSelDTO, isPeriodView);
                        int recordsToFetch = neededRecord < discountList.size() ? Math.abs(discountList.size() - start) : discountList.size();
                        dataList.addAll(discountList.subList(start, start + recordsToFetch));
                        neededRecord = neededRecord < discountList.size() ? Math.abs(discountList.size() - start - neededRecord) : neededRecord - discountList.size();
                        start = 0;
                    } else {
                        start = Math.abs(start - projSelDTO.getPeriodList().size());
                    }
                }
            }

        } else {
            if (parentId instanceof DiscountProjectionResultsDTO) {
                DiscountProjectionResultsDTO parentDTO = (DiscountProjectionResultsDTO) parentId;
                if (start == 0 || start < parentDTO.getTotalDiscountCount()) {
                    List discountList = getDiscountBasedOnLevelValue(start, offset, projSelDTO, isPeriodView, parentId);
                    if (isPeriodView) {
                        neededRecord = neededRecord - discountList.size();
                        dataList.addAll(discountList);
                    } else {
                        int recordsToFetch = neededRecord < discountList.size() ? neededRecord : Math.abs(discountList.size() - start);
                        dataList.addAll(discountList.subList(start, start + recordsToFetch));
                        neededRecord = neededRecord < discountList.size() ? 0 : neededRecord - discountList.size();
                    }
                } else {

                    if (isPeriodView) {
                        start = Math.abs(start - parentDTO.getTotalDiscountCount());
                    } else {
                        if (start < projSelDTO.getPeriodList().size()) {
                            List discountList = getDiscountBasedOnLevelValue(start, offset, projSelDTO, isPeriodView, parentId);
                            int recordsToFetch = neededRecord < discountList.size() ? Math.abs(discountList.size() - start) : Math.abs(discountList.size() - start);
                            dataList.addAll(discountList.subList(start, start + recordsToFetch));
                            neededRecord = neededRecord < discountList.size() ? Math.abs(discountList.size() - start - neededRecord) : Math.abs(discountList.size() - start - neededRecord);
                            start = 0;
                        } else {
                            start = Math.abs(start - projSelDTO.getPeriodList().size());
                        }
                    }
                }
            }
        }

        int maxLevelNo = Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projSelDTO.getHierarchyIndicator()) ? projSelDTO.getSessionDTO().getMaximumCustomerLevel() : projSelDTO.getSessionDTO().getMaximumProductLevel();

        if (neededRecord > 0 && projSelDTO.getTreeLevelNo() + 1 <= maxLevelNo) {
            projSelDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo() + 1);
            dataList.addAll(configureLevels(start, neededRecord, projSelDTO, isPeriodView));
        }

        return dataList;
    }

    /**
     *
     * @param projSelDTO
     * @return
     * @throws SystemException
     */
    public List<DiscountProjectionResultsDTO> getAllRSDiscount(ProjectionSelectionDTO projSelDTO, boolean isPeriodView)  {

        String totalQuery = SQlUtil.getQuery("dpr-total-discount-query");
        totalQuery = totalQuery.replace(Constant.FREQUENCY_QUESTION, projSelDTO.getFrequency().substring(0, 1));
        totalQuery = totalQuery.replace(Constant.RS_CONTRACT_SID_QUESTION, getSelectedRSIDForQuery(projSelDTO));
        List resultList = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(totalQuery, projSelDTO.getSessionDTO().getCurrentTableNames()));
        if (isPeriodView) {
            return customizeForPeriodView(resultList, projSelDTO, true, null,true);
        } else {
            return customizeForPivotView(resultList, projSelDTO);
        }

    }

    /**
     *
     * @param projSelDTO
     * @param isPeriodView
     * @return
     * @throws SystemException
     */
    public List<DiscountProjectionResultsDTO> getProjectionTotal(ProjectionSelectionDTO projSelDTO, boolean isPeriodView)  {
        if (isPeriodView) {
            String totalQuery = SQlUtil.getQuery("dpr-total-query");
            totalQuery = totalQuery.replace(Constant.FREQUENCY_QUESTION, projSelDTO.getFrequency().substring(0, 1));
            totalQuery = totalQuery.replace(Constant.RS_CONTRACT_SID_QUESTION, getSelectedRSIDForQuery(projSelDTO));
            List resultList = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(totalQuery, projSelDTO.getSessionDTO().getCurrentTableNames()));
            return customizeForPeriodView(resultList, projSelDTO, true, null,false);
        } else {
            DiscountProjectionResultsDTO dto = new DiscountProjectionResultsDTO();
            setDetailsForTotalLevels(dto, "Projection Total", null);
            List<DiscountProjectionResultsDTO> resultList = new ArrayList<>(1);
            resultList.add(dto);
            return resultList;
        }
    }

    /**
     *
     * @param start
     * @param offset
     * @param projSelDTO
     * @param isPeriodView
     * @return
     * @throws SystemException
     * @throws PortalException
     */
    public List<DiscountProjectionResultsDTO> getDiscountBasedOnLevelValue(int start, int offset, ProjectionSelectionDTO projSelDTO, boolean isPeriodView,final Object parentId) {
        CommonLogic commonLogic = new CommonLogic();
        String query = projSelDTO.isIsCustomHierarchy() ? SQlUtil.getQuery("dpr-custom-hierarchy-discount-query") : SQlUtil.getQuery("dpr-hierarchy-discount-query");
        if (projSelDTO.isIsCustomHierarchy()) {
            Map<String, String> customHierarchyRestriction = getHierarchyDetailsForCustomView(projSelDTO.getCustomerHierarchyNo(), projSelDTO.getProductHierarchyNo(), commonLogic.getHiearchyIndicatorFromCustomView(projSelDTO));
            query = query.replace(Constant.HIERARCHY_COLUMN_QUESTION, customHierarchyRestriction.get(Constant.CURRENT_HIERARCHY));
            query = query.replace(Constant.PREVIOUS_HIERARCHY_NO_QUESTION, customHierarchyRestriction.containsKey(Constant.PRIOR_HIERARCHY) ? customHierarchyRestriction.get(Constant.PRIOR_HIERARCHY) : StringUtils.EMPTY);
        } else {
            query = query.replace(Constant.HIERARCHY_COLUMN_QUESTION, commonLogic.getColumnName(projSelDTO.getHierarchyIndicator()));
            query = query.replace(Constant.PREVIOUS_HIERARCHY_NO_QUESTION, StringUtils.EMPTY);
        }
        if (projSelDTO.getHierarchyIndicator().equalsIgnoreCase("C") && !projSelDTO.getGroupFilter().equalsIgnoreCase(Constant.ALL_DISCOUNT_GROUP)) {
            query = query.replace(Constant.USER_GROUP_JOIN, SQlUtil.getQuery(Constant.USER_GROUP_CONDITIONQUERY).replace(USER_GROUP_QUESTION, projSelDTO.getGroupFilter()));
        } else {
            query = query.replace(Constant.USER_GROUP_JOIN, StringUtils.EMPTY);
        }
        query = query.replace(Constant.QUESTION_HIERARCHY_NO_VALUES, "('" + projSelDTO.getHierarchyNo() + "')");
        query = query.replace(Constant.FREQUENCY_QUESTION, projSelDTO.getFrequency().substring(0, 1));
        query = query.replace(Constant.RS_CONTRACT_SID_QUESTION, getSelectedRSIDForQuery(projSelDTO));
        
        if (isPeriodView) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("ORDER BY RS_CONTRACT_SID OFFSET ").append(start).append(Constant.ROWS_FETCH_NEXT_SPACE).append(offset).append(Constant.ROWS_ONLY_SPACE);
            query = query.replace("[?PAGINATION_PARAMETERS]", StringUtils.EMPTY);       
          
            List resultList = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames()));
            return customizeForPeriodView(resultList, projSelDTO, true, parentId,true);
        } else {
            query = query.replace("[?PAGINATION_PARAMETERS]", StringUtils.EMPTY);                        
            List resultList = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames()));            
            return customizeForPivotView(resultList, projSelDTO);
        }
    }

    /**
     *
     * @param start
     * @param offset
     * @param projSelDTO
     * @return
     * @throws SystemException
     * @throws PortalException
     */
    public List<DiscountProjectionResultsDTO> configureLevels(int start, int offset, ProjectionSelectionDTO projSelDTO, boolean isPeriodView) {
        CommonLogic commonLogic = new CommonLogic();
        if (isPeriodView) {
            String query = SQlUtil.getQuery("dpr-hierarchy-query");
            if (projSelDTO.isIsCustomHierarchy()) {
                String currentHierarchyIndicator = commonLogic.getHiearchyIndicatorFromCustomView(projSelDTO);
                int levelNo = commonLogic.getActualLevelNoFromCustomView(projSelDTO);

                switch (String.valueOf(currentHierarchyIndicator)) {
                    case Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY:
                        query = query.replace(Constant.QUESTION_HIERARCHY_NO_VALUES, commonLogic.getSelectedHierarchy(projSelDTO.getSessionDTO(), projSelDTO.getCustomerHierarchyNo(), currentHierarchyIndicator, levelNo));
                        break;
                    case Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY:
                        query = query.replace(Constant.QUESTION_HIERARCHY_NO_VALUES, commonLogic.getSelectedHierarchy(projSelDTO.getSessionDTO(), projSelDTO.getProductHierarchyNo(), currentHierarchyIndicator, levelNo));
                        break;
                    default:
                        LOGGER.warn("Invalid Hierarchy Indicator= {}" , currentHierarchyIndicator);
                }

                Map<String, String> customHierarchyRestriction = getHierarchyDetailsForCustomView(projSelDTO.getCustomerHierarchyNo(), projSelDTO.getProductHierarchyNo(), currentHierarchyIndicator);
                query = query.replace(Constant.HIERARCHY_COLUMN_QUESTION, customHierarchyRestriction.get(Constant.CURRENT_HIERARCHY));
                query = query.replace(Constant.PREVIOUS_HIERARCHY_NO_QUESTION, customHierarchyRestriction.containsKey(Constant.PRIOR_HIERARCHY) ? customHierarchyRestriction.get(Constant.PRIOR_HIERARCHY) : StringUtils.EMPTY);

            } else {
                query = query.replace(Constant.QUESTION_HIERARCHY_NO_VALUES, commonLogic.getSelectedHierarchy(projSelDTO.getSessionDTO(), projSelDTO.getHierarchyNo(), projSelDTO.getHierarchyIndicator(), projSelDTO.getTreeLevelNo()));
                query = query.replace(Constant.HIERARCHY_COLUMN_QUESTION, commonLogic.getColumnName(projSelDTO.getHierarchyIndicator()));
                query = query.replace(Constant.PREVIOUS_HIERARCHY_NO_QUESTION, StringUtils.EMPTY);
            }
            if(projSelDTO.getHierarchyIndicator().equalsIgnoreCase("C") && !projSelDTO.getGroupFilter().equalsIgnoreCase(Constant.ALL_DISCOUNT_GROUP)){
                query = query.replace(Constant.USER_GROUP_JOIN, SQlUtil.getQuery(Constant.USER_GROUP_CONDITIONQUERY).replace(USER_GROUP_QUESTION, projSelDTO.getGroupFilter())); 
            }else{
               query = query.replace(Constant.USER_GROUP_JOIN, StringUtils.EMPTY);  
            }
            query = query.replace("[?START]", StringUtils.EMPTY + start);
            query = query.replace("[?OFFSET]", StringUtils.EMPTY + offset);
            query = query.replace(Constant.FREQUENCY_QUESTION, projSelDTO.getFrequency().substring(0, 1));
            query = query.replace(Constant.RS_CONTRACT_SID_QUESTION, getSelectedRSIDForQuery(projSelDTO));
            List resultList = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames()));
            return customizeForPeriodView(resultList, projSelDTO, false, null,false);
        } else {
            List resultList = projSelDTO.isIsCustomHierarchy() ? commonLogic.getHiearchyNoForCustomView(projSelDTO, start, offset) : commonLogic.getHiearchyNoAsList(projSelDTO, start, offset);
            return customizeHierarchyLevels(resultList, projSelDTO);
        }
    }

    private List<DiscountProjectionResultsDTO> customizeForPeriodView(List<Object[]> resultList, ProjectionSelectionDTO proSelDTO, boolean isTotalLevel, final Object parentId,boolean isRSLevel) {

        List finalList = new ArrayList();

        if (!resultList.isEmpty()) {

            DiscountProjectionResultsDTO dto = new DiscountProjectionResultsDTO();
            String lastChangeValue = StringUtils.EMPTY;
            String frequency = proSelDTO.getFrequency();
            

            for (Object[] object : resultList) {
                if (StringUtils.isBlank(lastChangeValue) || !lastChangeValue.equals(object[0].toString())) {
                    dto = new DiscountProjectionResultsDTO();
                    finalList.add(dto);
                    lastChangeValue = object[0].toString();
                    if(isRSLevel){
                        setDetailsForTotalLevels(dto, object[NumericConstants.TEN].toString(), parentId);
                    }else if (isTotalLevel) {
                        setDetailsForTotalLevels(dto, lastChangeValue, parentId);
                    }else  {
                        setDetailsForHierarchyLevels(dto, lastChangeValue, proSelDTO);
                    }
                }
                setActualProjectionValue(dto, object, getFrequency(frequency, object));
            }
        }

        return finalList;
    }

    /**
     *
     * @param result
     * @param proSelDTO
     * @return
     */
    List<DiscountProjectionResultsDTO> customizeForPivotView(List<Object[]> result, ProjectionSelectionDTO proSelDTO) {

        List<DiscountProjectionResultsDTO> finalList = new ArrayList<>();
        List<String> periodList = new ArrayList<>(proSelDTO.getPeriodList());
        Map<String, DiscountProjectionResultsDTO> map = new LinkedHashMap<>();
        String frequency = proSelDTO.getFrequency();
        DiscountProjectionResultsDTO dto;        

        if (result != null && !result.isEmpty()) {

            String column;
            String visibleColumn;

            for (Object[] object : result) {

                column = getFrequency(frequency, object);

                if (periodList.contains(column)) {
                    visibleColumn = String.valueOf(object[0]).replace(" ", StringUtils.EMPTY);
                    
                    dto = map.get(column);
                    if (dto == null) {
                        dto = new DiscountProjectionResultsDTO();
                        finalList.add(dto);
                        dto.setGroup(getFrequencyForGroup(frequency, object));
                        dto.setIsParent("0");
                        map.put(column, dto);
                    }
                    setActualProjectionValue(dto, object, visibleColumn);
                }
            }
            
            List<String> rebateList = new ArrayList<>();
            List<String> rebateNameList = proSelDTO.getSessionDTO().getDiscountRSlist().get(1);
            for (String rebateName : rebateNameList) {
                rebateList.add(rebateName.replace(" ", StringUtils.EMPTY));
            }
            setDefaultValues(periodList, map, rebateList);
        }
        if (proSelDTO.getProjectionOrder().equalsIgnoreCase(Constant.DESCENDING)) {
            Collections.reverse(finalList);
        }

        return finalList;

    }

    private void setDefaultValues(List<String> periodList, Map<String, DiscountProjectionResultsDTO> map, List<String> rebateList) {

        for (String key : periodList) {
            DiscountProjectionResultsDTO dto = map.get(key);
            for (String rebate : rebateList) {
                if (dto.getPropertyValue(rebate + ACTUALRATE.getConstant()) == null) {
                    dto.addStringProperties(rebate + ACTUALRATE.getConstant(), "-");
                }
                if (dto.getPropertyValue(rebate + ACTUALAMOUNT.getConstant()) == null) {
                    dto.addStringProperties(rebate + ACTUALAMOUNT.getConstant(), "-");
                }
                if (dto.getPropertyValue(rebate + ACTUALRPU.getConstant()) == null) {
                    dto.addStringProperties(rebate + ACTUALRPU.getConstant(), "-");
                }
                if (dto.getPropertyValue(rebate + PROJECTEDRATE.getConstant()) == null) {
                    dto.addStringProperties(rebate + PROJECTEDRATE.getConstant(), "-");
                }
                if (dto.getPropertyValue(rebate + PROJECTEDAMOUNT.getConstant()) == null) {
                    dto.addStringProperties(rebate + PROJECTEDAMOUNT.getConstant(), "-");
                }
                if (dto.getPropertyValue(rebate + PROJECTEDRPU.getConstant()) == null) {
                    dto.addStringProperties(rebate + PROJECTEDRPU.getConstant(), "-");
                }
                if (dto.getPropertyValue(rebate + ACTUALEXFACTORY.getConstant()) == null) {
                    dto.addStringProperties(rebate + ACTUALEXFACTORY.getConstant(), "-");
                }
                if (dto.getPropertyValue(rebate + PROJECTEDEXFACTORY.getConstant()) == null) {
                    dto.addStringProperties(rebate + PROJECTEDEXFACTORY.getConstant(), "-");
                }
            }
        }
    }

    private List<DiscountProjectionResultsDTO> customizeHierarchyLevels(List<String> resultList, ProjectionSelectionDTO proSelDTO) {

        List finalList = new ArrayList();
        if (!resultList.isEmpty()) {
            DiscountProjectionResultsDTO dto;
            String lastChangeValue = StringUtils.EMPTY;
            for (String hierarchyNo : resultList) {
                if (StringUtils.isBlank(lastChangeValue) || !lastChangeValue.equals(hierarchyNo)) {
                    dto = new DiscountProjectionResultsDTO();
                    finalList.add(dto);
                    lastChangeValue = hierarchyNo;
                    setDetailsForHierarchyLevels(dto, lastChangeValue, proSelDTO);
                }
            }
        }

        return finalList;
    }

    private void setDetailsForTotalLevels(DiscountProjectionResultsDTO dto, String lastChangeValue, final Object parentId) {
        dto.setGroup(lastChangeValue);
        dto.setLevelNo(0);
        dto.setHierarchylevelId("0");
        dto.setIsParent(Constant.STRING_ONE);
        dto.setHierarchyNo(Constant.STRING_ONE);
        dto.setParent(0);
        dto.setTotal(1);
        if (parentId instanceof DiscountProjectionResultsDTO) {
            DiscountProjectionResultsDTO parentDTO = (DiscountProjectionResultsDTO) parentId;
            parentDTO.setAddedDiscountCount(parentDTO.getAddedDiscountCount() + 1);
        }
    }

    private void setDetailsForHierarchyLevels(DiscountProjectionResultsDTO dto, String lastChangeValue, ProjectionSelectionDTO proSelDTO) {

        Map<String, List> hierarchyLevelDetails = proSelDTO.getSessionDTO().getHierarchyLevelDetails();
        dto.setLevelNo(Integer.valueOf(hierarchyLevelDetails.get(lastChangeValue).get(NumericConstants.TWO).toString()));
        if (proSelDTO.isIsCustomHierarchy()) {
            dto.setTreeLevelNo(proSelDTO.getTreeLevelNo());
        } else {
            dto.setTreeLevelNo(Integer.valueOf(hierarchyLevelDetails.get(lastChangeValue).get(NumericConstants.TWO).toString()));
        }        
        dto.setGroup(hierarchyLevelDetails.get(lastChangeValue).get(0).toString());
        dto.setHierarchyIndicator(hierarchyLevelDetails.get(lastChangeValue).get(NumericConstants.FOUR).toString());
        dto.setHierarchyNo(lastChangeValue);
        if (INDICATOR_VIEW_CUSTOMER.getConstant().equals(dto.getHierarchyIndicator())) {
            dto.setCustomerHierarchyNo(dto.getHierarchyNo());
            dto.setProductHierarchyNo(proSelDTO.getProductHierarchyNo());
        } else if (INDICATOR_VIEW_PRODUCT.getConstant().equals(dto.getHierarchyIndicator())) {
            dto.setProductHierarchyNo(dto.getHierarchyNo());
            dto.setCustomerHierarchyNo(proSelDTO.getCustomerHierarchyNo());
        }
        dto.setParent(1);
        dto.setTotal(0);
    }


    private void setActualProjectionValue(DiscountProjectionResultsDTO dto, Object[] obj, String column) {        
        if (String.valueOf(obj[NumericConstants.NINE]).equals("0")) {
            dto.addStringProperties(column + ACTUALRATE.getConstant(), UNIT_VOLUME_FORMAT.format(getvalue(obj[NumericConstants.THREE])) + PERCENT.getConstant());
            dto.addStringProperties(column + ACTUALAMOUNT.getConstant(), DOLLAR.getConstant() + DOLLAR_RPU_FORMAT.format(getvalue(obj[NumericConstants.FOUR])));
            dto.addStringProperties(column + ACTUALRPU.getConstant(), DOLLAR.getConstant() + DOLLAR_RPU_FORMAT.format(getvalue(obj[NumericConstants.FIVE])));
            dto.addStringProperties(column + ACTUALEXFACTORY.getConstant(), EXFAC_PER_FORMAT.format(getvalue(obj[NumericConstants.ELEVEN])) + PERCENT.getConstant());
        } else {
            dto.addStringProperties(column + PROJECTEDRATE.getConstant(), UNIT_VOLUME_FORMAT.format(getvalue(obj[NumericConstants.SIX])) + PERCENT.getConstant());
            dto.addStringProperties(column + PROJECTEDAMOUNT.getConstant(), DOLLAR.getConstant() + DOLLAR_RPU_FORMAT.format(getvalue(obj[NumericConstants.SEVEN])));
            dto.addStringProperties(column + PROJECTEDRPU.getConstant(), DOLLAR.getConstant() + DOLLAR_RPU_FORMAT.format(getvalue(obj[NumericConstants.EIGHT])));
            dto.addStringProperties(column + PROJECTEDEXFACTORY.getConstant(), EXFAC_PER_FORMAT.format(getvalue(obj[NumericConstants.TWELVE])) + PERCENT.getConstant());
        }
    }

    private Object getvalue(Object obj) {
        if (obj == null || StringUtils.isBlank(obj.toString())) {
            return 0;
        } else {
            return obj;
        }
    }

    private String getMonthForInt(int num) {
        String month = StringUtils.EMPTY;
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getShortMonths();
        if (num >= 0 && num <= NumericConstants.ELEVEN) {
            month = months[num];
        }
        return month;
    }

    private String getFrequency(String frequency, Object[] object) {
        String column = StringUtils.EMPTY;
        if (ANNUALLY.getConstant().equals(frequency)) {
            column = String.valueOf(object[1]);
        } else if (QUARTERLY.getConstant().equals(frequency)) {
            column = Constant.Q_SMALL + String.valueOf(object[NumericConstants.TWO]) + String.valueOf(object[1]);
        } else if (SEMI_ANNUALLY.getConstant().equals(frequency)) {
            column = Constant.S_SMALL + String.valueOf(object[NumericConstants.TWO]) + String.valueOf(object[1]);
        } else if (MONTHLY.getConstant().equals(frequency)) {
            String monthName = getMonthForInt(Integer.parseInt(String.valueOf(object[NumericConstants.TWO])) - 1);
            column = monthName.toLowerCase() + String.valueOf(object[1]);
        }
        return column;
    }  
    
    private String getFrequencyForGroup(String frequency, Object[] object) {
        String column = StringUtils.EMPTY;
        if (ANNUALLY.getConstant().equals(frequency)) {
            column = String.valueOf(object[1]);
        } else if (QUARTERLY.getConstant().equals(frequency)) {
            column = Constant.Q + String.valueOf(object[NumericConstants.TWO]) + Constants.CommonConstants.SPACE + String.valueOf(object[1]);
        } else if (SEMI_ANNUALLY.getConstant().equals(frequency)) {
            column = Constant.S + String.valueOf(object[NumericConstants.TWO]) + Constants.CommonConstants.SPACE + String.valueOf(object[1]);
        } else if (MONTHLY.getConstant().equals(frequency)) {
            String monthName = getMonthForInt(Integer.parseInt(String.valueOf(object[NumericConstants.TWO])) - 1);
            column = monthName + Constants.CommonConstants.SPACE + String.valueOf(object[1]);
        }
        return column;
    }
    
 /**
     *
     * @param start
     * @param offset
     * @param projSelDTO
     * @return
     * @throws SystemException
     * @throws PortalException
     */
    public List configureLevelsForExcel(int start, int offset, ProjectionSelectionDTO projSelDTO, boolean isPeriodView,String hierarchy)  {
        CommonLogic commonLogic = new CommonLogic();
        if (isPeriodView) {
            String query =projSelDTO.isIsCustomHierarchy()? SQlUtil.getQuery("dpr-hierarchy-query-custom-query"):SQlUtil.getQuery("dpr-hierarchy-query");
            if (projSelDTO.isIsCustomHierarchy()) {
                query = query.replace("[?CUST_RELATIONSHIP_BUILDER_SID]", projSelDTO.getCustRelationshipBuilderSid());
                query = query.replace("[?PROD_RELATIONSHIP_BUILDER_SID]", projSelDTO.getProdRelationshipBuilderSid());
                query = query.replace("[?CUSTOM_VIEW_MASTER_SID]", String.valueOf(projSelDTO.getCustomId()));
                 query = query.replace("[?RS_MODEL_SID]", getSelectedRSIDForQuery(projSelDTO));
            } else {
                if (projSelDTO.getHierarchyIndicator().equalsIgnoreCase("C") && !projSelDTO.getGroupFilter().equalsIgnoreCase(Constant.ALL_DISCOUNT_GROUP)) {
                    query = query.replace(Constant.USER_GROUP_JOIN, SQlUtil.getQuery(Constant.USER_GROUP_CONDITIONQUERY).replace(USER_GROUP_QUESTION, projSelDTO.getGroupFilter()));
                } else {
                    query = query.replace(Constant.USER_GROUP_JOIN, StringUtils.EMPTY);
                }
                query = query.replace(Constant.QUESTION_HIERARCHY_NO_VALUES, hierarchy);
                query = query.replace(Constant.HIERARCHY_COLUMN_QUESTION, commonLogic.getColumnName(projSelDTO.getHierarchyIndicator()));
                query = query.replace(Constant.PREVIOUS_HIERARCHY_NO_QUESTION, StringUtils.EMPTY);
                query = query.replace("[?START]", StringUtils.EMPTY + start);
                query = query.replace("[?OFFSET]", StringUtils.EMPTY + offset);
                query = query.replace(Constant.RS_CONTRACT_SID_QUESTION, getSelectedRSIDForQuery(projSelDTO));
            }
            query = query.replace(Constant.FREQUENCY_QUESTION, projSelDTO.getFrequency().substring(0, 1));
            List resultList = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames()));
            return resultList;
        } else {
            List resultList = projSelDTO.isIsCustomHierarchy() ? commonLogic.getHiearchyNoForCustomViewForExcel(projSelDTO, start) : commonLogic.getHiearchyNoAsListForExcel(projSelDTO, start, offset,hierarchy);
            return resultList;
        }
    }
    
 /**
     *
     * @param start
     * @param offset
     * @param projSelDTO
     * @param isPeriodView
     * @return
     * @throws SystemException
     * @throws PortalException
     */
    public List getDiscountForAllContractExcel(ProjectionSelectionDTO projSelDTO, boolean isPeriodView,String hierarchy)  {
        CommonLogic commonLogic = new CommonLogic();
        String query = projSelDTO.isIsCustomHierarchy() ? SQlUtil.getQuery("dpr-custom-hierarchy-discount-excel-query") : SQlUtil.getQuery("dpr-hierarchy-discount-excel-query");
        if (projSelDTO.isIsCustomHierarchy()) {
            if (!isPeriodView) {
                query = query.replace(Constant.ORDER_BY_QUESTION, "LEVEL_NO,DPM.HIERARCHY_NO,PARENT_HIERARCHY_NO,YEAR,PERIOD,RS_NAME,AP_TABLE_INDICATOR");
            } else {
                query = query.replace(Constant.ORDER_BY_QUESTION, "LEVEL_NO,DPM.HIERARCHY_NO,PARENT_HIERARCHY_NO,RS_NAME,YEAR,PERIOD,AP_TABLE_INDICATOR");
            }
            query = query.replace("[?CUST_RELATIONSHIP_BUILDER_SID]", projSelDTO.getCustRelationshipBuilderSid());
            query = query.replace("[?PROD_RELATIONSHIP_BUILDER_SID]", projSelDTO.getProdRelationshipBuilderSid());
            query = query.replace("[?CUSTOM_VIEW_MASTER_SID]", String.valueOf(projSelDTO.getCustomId()));
        } else {
            if (!isPeriodView) {
                query = query.replace(Constant.ORDER_BY_QUESTION, "AD.HIERARCHY_NO,DPM.discount,AD.YEAR,AD.PERIOD ,DPM.RS_CONTRACT_SID,AP_TABLE_INDICATOR");
            } else {
                query = query.replace(Constant.ORDER_BY_QUESTION, "AD.HIERARCHY_NO,DPM.discount,AD.YEAR,AD.PERIOD ,DPM.RS_CONTRACT_SID,AP_TABLE_INDICATOR");
            }
            if(projSelDTO.getHierarchyIndicator().equalsIgnoreCase("C") && !projSelDTO.getGroupFilter().equalsIgnoreCase(Constant.ALL_DISCOUNT_GROUP)){
                String userGroupCondition=SQlUtil.getQuery(Constant.USER_GROUP_CONDITIONQUERY);
                userGroupCondition=userGroupCondition.replace("AND", "WHERE");
                userGroupCondition=userGroupCondition.replace(USER_GROUP_QUESTION, projSelDTO.getGroupFilter());
                query = query.replace(Constant.USER_GROUP_JOIN, userGroupCondition); 
            }else{
               query = query.replace(Constant.USER_GROUP_JOIN, StringUtils.EMPTY);  
            }
            query = query.replace(Constant.HIERARCHY_COLUMN_QUESTION, commonLogic.getColumnName(projSelDTO.getHierarchyIndicator()));
            query = query.replace(Constant.PREVIOUS_HIERARCHY_NO_QUESTION, StringUtils.EMPTY);
            query = query.replace(Constant.QUESTION_HIERARCHY_NO_VALUES, hierarchy);
        }
        query = query.replace(Constant.FREQUENCY_QUESTION, projSelDTO.getFrequency().substring(0, 1));
        query = query.replace("[?RS_MODEL_SID]", getSelectedRSIDForQuery(projSelDTO));
        List resultList = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames()));
        return resultList;
        
    }
}
