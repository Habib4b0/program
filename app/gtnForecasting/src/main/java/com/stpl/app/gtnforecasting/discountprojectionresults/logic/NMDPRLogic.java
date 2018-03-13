/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.discountprojectionresults.logic;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gtnforecasting.dao.CommonDAO;
import com.stpl.app.gtnforecasting.dao.impl.CommonDAOImpl;
import com.stpl.app.gtnforecasting.discountprojectionresults.dto.DiscountProjectionResultsDTO;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.gtnforecasting.utils.Constant.FrequencyConstants.ANNUALLY;
import static com.stpl.app.gtnforecasting.utils.Constant.FrequencyConstants.SEMI_ANNUALLY;
import static com.stpl.app.gtnforecasting.utils.HeaderUtils.getMonthForInt;
import com.stpl.app.gtnforecasting.utils.xmlparser.SQlUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import static com.stpl.app.utils.Constants.ButtonConstants.ALL;
import static com.stpl.app.utils.Constants.FrequencyConstants.MONTHLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.QUARTERLY;
import static com.stpl.app.utils.Constants.IndicatorConstants.INDICATOR_VIEW_CUSTOMER;
import static com.stpl.app.utils.Constants.IndicatorConstants.INDICATOR_VIEW_PRODUCT;
import static com.stpl.app.utils.Constants.LabelConstants.ACTUALS;
import static com.stpl.app.utils.Constants.LabelConstants.BOTH;
import static com.stpl.app.utils.Constants.LabelConstants.CUSTOM;
import static com.stpl.app.utils.Constants.LabelConstants.CUSTOMER;
import static com.stpl.app.utils.Constants.LabelConstants.DASH;
import static com.stpl.app.utils.Constants.LabelConstants.PERIOD;
import static com.stpl.app.utils.Constants.LabelConstants.PRODUCT;
import static com.stpl.app.utils.Constants.LabelConstants.PROJECTIONS;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.QueryUtil;
import com.stpl.ifs.util.constants.BooleanConstant;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.collections.keyvalue.MultiKey;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author pvinoth
 */
public class NMDPRLogic {

    /**
     *
     */
    private static final long serialVersionUID = 4428373722392530081L;
    
    private final Map<String, String> periodMap = new HashMap<>();
    private static final DecimalFormat DOLLAR = new DecimalFormat("#,##0");
    private static final DecimalFormat DOLLAR_RPU = new DecimalFormat("#,##0.00");
    private static final DecimalFormat UNITVOLUME = new DecimalFormat("#,##0.000");
    private static final DecimalFormat CUR_ZERO = new DecimalFormat("$#,##0");
    private static final String ACTUALSRATE = "ActualsRate";
    private static final String ACTUALSAMOUNT = "ActualsAmount";
    private static final String PROJECTIONSRATE = "ProjectionsRate";
    private static final String PROJECTIONSAMOUNT = "ProjectionsAmount";
    private static final String ACTUALRPU = "ActualsRPU";
    private static final String PROJECTEDRPU = "ProjectedRPU";
    private static final String NULL = "null";
    private static final String Q_SMALL = "q";
    private static final String PERCENTAGE = Constant.PERCENT;
    private static final String DOLLAR_SYMBOL = "$";
    private static final String ZERO = "0";
    private static final String ONE = "1";
    private static final String TWO = "2";
    private static final String THREE = "3";
    private static final String FOUR = "4";
    private final Map<String, String> valueMap = new HashMap<>();
    private final List<Integer> startAndEndPeriods = new ArrayList<>();
    private static final CommonDAO commonDao = new CommonDAOImpl();
    protected Map<MultiKey, List> customerccpId = new HashMap<>();
    protected Map<MultiKey, List> productccpId = new HashMap<>();
    protected DPRQueryBuilder queryBuilder = new DPRQueryBuilder();
    private static final Logger LOGGER = LoggerFactory.getLogger(NMDPRLogic.class);
    public static final String PROJECTION_CUST_HIERARCHY = "PROJECTION_CUST_HIERARCHY";
    public static final String PROJECTION_PROD_HIERARCHY = "PROJECTION_PROD_HIERARCHY";

    public NMDPRLogic() {
        periodValueMap();
    }

    /**
     * Method is Used To Load Projection Total of period Mode
     *
     * @param projectionId
     * @param selection
     * @return
     */
    public List<DiscountProjectionResultsDTO> getPeriodProjectionTotal(int projectionId, List<Integer> startAndEndPeriods, ProjectionSelectionDTO projSelDTO) {
        List<DiscountProjectionResultsDTO> discountProjList = new ArrayList<>();
        String selectedView = projSelDTO.getView();
        List ccpId = null;
        String query = null;
        String ccpTableName = "";
        /**
         * MultiKey to put input . if input is same then fetch from map else
         * fetch from db and put
         */
        MultiKey key = new MultiKey(projectionId, PERCENTAGE, Constant.STRING_ONE);
        if (CUSTOMER.getConstant().equals(selectedView)) {
            ccpTableName = PROJECTION_CUST_HIERARCHY;
            if (projSelDTO.isIsGenerate() || customerccpId == null || !customerccpId.containsKey(key)) {
                query = SQlUtil.getQuery(Constant.NM_PROJ_DETAILS_SID_QUERY);
                query += Constant.SELECT_FROM_PROJECTION_DETAILS;
                query = query.replaceAll(Constant.TABLENAME_QUESTION, ccpTableName).replaceAll(Constant.PROJSID, String.valueOf(projectionId)).replaceAll(Constant.HIERNO, PERCENTAGE).replaceAll(Constant.LEVELNO_QUESTION, Constant.STRING_ONE);
                ccpId = HelperTableLocalServiceUtil.executeSelectQuery(query);
                customerccpId.put(key, ccpId);
            } else {
                ccpId = customerccpId.get(key);
            }
        }
        if (PRODUCT.getConstant().equals(selectedView)) {
            ccpTableName = PROJECTION_PROD_HIERARCHY;
            if (projSelDTO.isIsGenerate() || productccpId == null || !productccpId.containsKey(key)) {
                query = SQlUtil.getQuery(Constant.NM_PROJ_DETAILS_SID_QUERY);
                query += Constant.SELECT_FROM_PROJECTION_DETAILS;
                query = query.replaceAll(Constant.TABLENAME_QUESTION, ccpTableName).replaceAll(Constant.PROJSID, String.valueOf(projectionId)).replaceAll(Constant.HIERNO, PERCENTAGE).replaceAll(Constant.LEVELNO_QUESTION, Constant.STRING_ONE);
                ccpId = HelperTableLocalServiceUtil.executeSelectQuery(query);
                productccpId.put(key, ccpId);
            } else {
                ccpId = productccpId.get(key);
            }
        }
        if (CUSTOM.getConstant().equals(selectedView) && projSelDTO.getCustomId() != 0) {
            if (projSelDTO.isIsGenerate() || customerccpId == null || !customerccpId.containsKey(key)) {
                query = CommonLogic.getCustomCCPQueryDPR(projSelDTO);
                query += Constant.SELECT_FROM_PROJECTION_DETAILS;
                ccpId = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames()));
                customerccpId.put(key, ccpId);
            } else {
                ccpId = customerccpId.get(key);
            }
        }
        if (ccpId != null && !ccpId.isEmpty()) {
            String order = projSelDTO.getProjectionOrder();
            List<String> discountList;
            discountList = projSelDTO.getDiscountNameList();
            String discountString = getDiscountName(discountList);
            projSelDTO.setCcpCount(ccpId.size());
            List list = getDPRProjectionTotal(projSelDTO, discountString, Constant.PARENT, order, startAndEndPeriods, ccpTableName, BooleanConstant.getFalseFlag(), PERCENTAGE, Constant.STRING_ONE, BooleanConstant.getFalseFlag());
            if (list != null && !list.isEmpty()) {
                DiscountProjectionResultsDTO dto = new DiscountProjectionResultsDTO();
                dto.setGroup(Constant.PROJECTION_TOTAL);
                dto.setLevelNo(0);
                dto.setHierarchylevelId(ZERO);
                dto.setIsParent(Constant.STRING_ONE);
                dto.setHierarchyNo(Constant.STRING_ONE);
                dto.setParent(0);
                dto.setTotal(1);
                dto = getCustomizedTotal(list, projSelDTO, false, dto).get(0);
                discountProjList.add(dto);
            }
        }
        return discountProjList;
    }

    /**
     * This Method is used to get parent node discount
     *
     * @param relationshipId
     * @param LevelNo
     * @param hierarchyLevelSid
     * @param value
     * @return
     * @throws SystemException
     */
    public List<DiscountProjectionResultsDTO> getPeriodHierarchy(ProjectionSelectionDTO proSelDTO, List<Integer> startAndEndPeriods, List<String> discountList)  {
        List<DiscountProjectionResultsDTO> discountProjList = new ArrayList<>();
        String level = String.valueOf(proSelDTO.getTreeLevelNo());
        String hierarchyNo = String.valueOf(proSelDTO.getHierarchyNo());
        int projectionMasterId = proSelDTO.getProjectionId();
        String hierarchy = proSelDTO.getView();
        List ccpid = null;
        String query = "";
        String ccpTableName = "";
        if (CUSTOMER.getConstant().equals(hierarchy)) {
            query = SQlUtil.getQuery(Constant.NM_PROJ_DETAILS_SID_QUERY);
            query += Constant.SELECT_FROM_PROJECTION_DETAILS;
            ccpTableName = PROJECTION_CUST_HIERARCHY;
            query = query.replaceAll(Constant.TABLENAME_QUESTION, ccpTableName).replaceAll(Constant.PROJSID, String.valueOf(projectionMasterId)).replaceAll(Constant.HIERNO, hierarchyNo).replaceAll(Constant.LEVELNO_QUESTION, level);
        }
        if (PRODUCT.getConstant().equals(hierarchy)) {
            query = SQlUtil.getQuery(Constant.NM_PROJ_DETAILS_SID_QUERY);
            query += Constant.SELECT_FROM_PROJECTION_DETAILS;
            ccpTableName = PROJECTION_PROD_HIERARCHY;
            query = query.replaceAll(Constant.TABLENAME_QUESTION, ccpTableName).replaceAll(Constant.PROJSID, String.valueOf(projectionMasterId)).replaceAll(Constant.HIERNO, hierarchyNo).replaceAll(Constant.LEVELNO_QUESTION, level);
        }
        if (CUSTOM.getConstant().equals(hierarchy)) {
            query = CommonLogic.getCustomCCPQueryDPR(proSelDTO);
            query += Constant.SELECT_FROM_PROJECTION_DETAILS;
        }
        ccpid = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, proSelDTO.getSessionDTO().getCurrentTableNames()));
        if (ccpid != null && !ccpid.isEmpty()) {
            String discountString = CommonUtils.convertCollectionToString(discountList, true);
            if (discountString.equals(StringUtils.EMPTY)) {
                discountString = ZERO;
            }
            String order = proSelDTO.getProjectionOrder();
            List list = getDPRProjectionTotal(proSelDTO, discountString, Constant.PARENT, order, startAndEndPeriods, 
                    ccpTableName, BooleanConstant.getTrueFlag(), hierarchyNo, level, BooleanConstant.getFalseFlag());
            if (list != null && !list.isEmpty()) {
                discountProjList.addAll(getCustomizedTotal(list, proSelDTO, true, new DiscountProjectionResultsDTO()));
            }
        }
        return discountProjList;
    }

    private void periodValueMap() {
        periodMap.put(MONTHLY.getConstant(), "MONTH");
        periodMap.put(QUARTERLY.getConstant(), Constant.QUARTER);
        periodMap.put(SEMI_ANNUALLY.getConstant(), Constant.QUARTER);
        periodMap.put(ANNUALLY.getConstant(), "YEAR");
    }

    /**
     * This method is used to load all the discount of the projection total
     *
     * @param projectionId
     * @param selection
     * @param startAndEndPeriods
     * @param projSelDTO
     * @param discountList
     * @return
     *
     */
    public List<DiscountProjectionResultsDTO> getPeriodProjectionTotalDiscount(int projectionId,
            List<Integer> startAndEndPeriods, ProjectionSelectionDTO projSelDTO, List<String> discountList)  {
        List<DiscountProjectionResultsDTO> discountProjList = new ArrayList<>();
        List ccpId = null;
        String selectedView = projSelDTO.getView();
        /**
         * MultiKey to put input . if input is same then fetch from map else
         * fetch from db and put
         */
        MultiKey key = new MultiKey(projectionId, PERCENTAGE, Constant.STRING_ONE);
        String ccpTableName = "";
        String query = "";
        if (CUSTOMER.getConstant().equals(selectedView)) {
            ccpTableName = PROJECTION_CUST_HIERARCHY;
            if (projSelDTO.isIsGenerate() || customerccpId == null || !customerccpId.containsKey(key)) {
                query = SQlUtil.getQuery(Constant.NM_PROJ_DETAILS_SID_QUERY);
                query += Constant.SELECT_FROM_PROJECTION_DETAILS;
                query = query.replaceAll(Constant.TABLENAME_QUESTION, ccpTableName).replaceAll(Constant.PROJSID, String.valueOf(projectionId)).replaceAll(Constant.HIERNO, PERCENTAGE).replaceAll(Constant.LEVELNO_QUESTION, Constant.STRING_ONE);
                ccpId = HelperTableLocalServiceUtil.executeSelectQuery(query);
                customerccpId.put(key, ccpId);
            } else {
                ccpId = customerccpId.get(key);
            }
        }
        if (PRODUCT.getConstant().equals(selectedView)) {
            ccpTableName = PROJECTION_PROD_HIERARCHY;
            if (projSelDTO.isIsGenerate() || productccpId == null || !productccpId.containsKey(key)) {
                query = SQlUtil.getQuery(Constant.NM_PROJ_DETAILS_SID_QUERY);
                query += Constant.SELECT_FROM_PROJECTION_DETAILS;
                query = query.replaceAll(Constant.TABLENAME_QUESTION, ccpTableName).replaceAll(Constant.PROJSID, String.valueOf(projectionId)).replaceAll(Constant.HIERNO, PERCENTAGE).replaceAll(Constant.LEVELNO_QUESTION, Constant.STRING_ONE);
                ccpId = HelperTableLocalServiceUtil.executeSelectQuery(query);
                productccpId.put(key, ccpId);
            } else {
                ccpId = productccpId.get(key);
            }
        }
        if (CUSTOM.getConstant().equals(selectedView) && projSelDTO.getCustomId() != 0) {
            if (projSelDTO.isIsGenerate() || customerccpId == null || !customerccpId.containsKey(key)) {
                query = CommonLogic.getCustomCCPQueryDPR(projSelDTO);
                query += Constant.SELECT_FROM_PROJECTION_DETAILS;
                ccpId = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames()));
                customerccpId.put(key, ccpId);
            } else {
                ccpId = customerccpId.get(key);
            }
        }
        if (ccpId != null && !ccpId.isEmpty()) {
            String order = projSelDTO.getProjectionOrder();
            String discountString = CommonUtils.convertCollectionToString(discountList, true);
            projSelDTO.setCcpCount(ccpId.size());
            if (discountString.equals(StringUtils.EMPTY)) {
                discountString = ZERO;
            }
            List list = getDPRProjectionTotal(projSelDTO, discountString, Constant.PARENT, order, startAndEndPeriods, ccpTableName, BooleanConstant.getTrueFlag(), PERCENTAGE, Constant.STRING_ONE, BooleanConstant.getFalseFlag());
            if (list != null && !list.isEmpty()) {
                return getCustomizedTotal(list, projSelDTO, true, new DiscountProjectionResultsDTO());
            }
        }
        return discountProjList;
    }

    /**
     * This method is used to load Projection Total for Pivot mode
     *
     * @param projectionId
     * @param projSelDTO
     * @param startAndEndPeriods
     * @return
     *
     *
     */
    public List<DiscountProjectionResultsDTO> getPivotProjectionTotal(int projectionId,
            ProjectionSelectionDTO projSelDTO, List<Integer> startAndEndPeriods)  {
        List<DiscountProjectionResultsDTO> discountProjList = new ArrayList<>();
        List<Object> view = new ArrayList<>();
        String selectedView = String.valueOf(projSelDTO.getView());
        List ccpId = null;
        /**
         * MultiKey to put input . if input is same then fetch from map else
         * fetch from db and put
         */
        MultiKey key = new MultiKey(projectionId, PERCENTAGE, Constant.STRING_ONE);
        String query;
        String ccpTableName = StringUtils.EMPTY;
        if (CUSTOMER.getConstant().equals(selectedView)) {
            ccpTableName = PROJECTION_CUST_HIERARCHY;
            if (projSelDTO.isIsGenerate() || customerccpId == null || !customerccpId.containsKey(key)) {
                query = SQlUtil.getQuery(Constant.NM_PROJ_DETAILS_SID_QUERY);
                query += Constant.SELECT_FROM_PROJECTION_DETAILS;
                query = query.replaceAll(Constant.TABLENAME_QUESTION, ccpTableName).replaceAll(Constant.PROJSID, String.valueOf(projectionId)).replaceAll(Constant.HIERNO, PERCENTAGE).replaceAll(Constant.LEVELNO_QUESTION, Constant.STRING_ONE);
                ccpId = HelperTableLocalServiceUtil.executeSelectQuery(query);
                customerccpId.put(key, ccpId);
            } else {
                ccpId = customerccpId.get(key);
            }
        }
        if (PRODUCT.getConstant().equals(selectedView)) {
            ccpTableName = PROJECTION_PROD_HIERARCHY;
            if (projSelDTO.isIsGenerate() || productccpId == null || !productccpId.containsKey(key)) {
                query = SQlUtil.getQuery(Constant.NM_PROJ_DETAILS_SID_QUERY);
                query += Constant.SELECT_FROM_PROJECTION_DETAILS;
                query = query.replaceAll(Constant.TABLENAME_QUESTION, ccpTableName).replaceAll(Constant.PROJSID, String.valueOf(projectionId)).replaceAll(Constant.HIERNO, PERCENTAGE).replaceAll(Constant.LEVELNO_QUESTION, Constant.STRING_ONE);
                ccpId = HelperTableLocalServiceUtil.executeSelectQuery(query);
                productccpId.put(key, ccpId);
            } else {
                ccpId = productccpId.get(key);
            }
        }
        if (CUSTOM.getConstant().equals(selectedView) && projSelDTO.getCustomId() != 0) {
            if (projSelDTO.isIsGenerate() || customerccpId == null || !customerccpId.containsKey(key)) {
                query = CommonLogic.getCustomCCPQueryDPR(projSelDTO);
                query += Constant.SELECT_FROM_PROJECTION_DETAILS;
                ccpId = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames()));
                customerccpId.put(key, ccpId);
            } else {
                ccpId = customerccpId.get(key);
            }
        }
        if (ccpId != null && !ccpId.isEmpty()) {
            List<String> discountList;
            discountList = projSelDTO.getDiscountNameList();
            String discountString = getDiscountName(discountList);
            projSelDTO.setCcpCount(ccpId.size());
            view.add(projSelDTO.getProjectionOrder());
            String order = projSelDTO.getProjectionOrder();
            List list = getDPRProjectionTotal(projSelDTO, discountString, Constant.PARENT, order, startAndEndPeriods, ccpTableName, BooleanConstant.getTrueFlag(), PERCENTAGE, Constant.STRING_ONE, BooleanConstant.getTrueFlag());
            if (list != null && !list.isEmpty()) {
                DiscountProjectionResultsDTO discountDto = new DiscountProjectionResultsDTO();
                discountDto.setGroup(Constant.PROJECTION_TOTAL);
                discountDto.setHierarchySid(ZERO);
                discountDto.setLevelNo(0);
                discountDto.setHierarchylevelId(ZERO);
                discountDto.setIsParent(Constant.STRING_ONE);
                discountDto.setHierarchyNo(Constant.STRING_ONE);
                discountDto.setTotal(1);
                discountDto.setParent(0);
                discountDto = getCustomizedTotalPivot(list, projSelDTO, true, discountDto).get(0);
                discountProjList.add(discountDto);
            }
        }
        return discountProjList;
    }

    /**
     * This method is used to load Projection Total of Pivot view
     *
     * @param projSelDTO
     * @param startAndEndPeriods
     * @return
     * @throws SystemException
     *
     */
    public List<DiscountProjectionResultsDTO> getPivotProjectionTotalDiscount(ProjectionSelectionDTO projSelDTO, List<Integer> startAndEndPeriods)  {
        List<DiscountProjectionResultsDTO> discountProjList = new ArrayList<>();
        int projectionId = projSelDTO.getProjectionId();
        List ccpId = null;
        String query;
        String ccpTableName = StringUtils.EMPTY;
        String selectedView = String.valueOf(projSelDTO.getView());
        /**
         * MultiKey to put input . if input is same then fetch from map else
         * fetch from db and put
         */
        MultiKey key = new MultiKey(projectionId, PERCENTAGE, Constant.STRING_ONE);
        if (CUSTOMER.getConstant().equals(selectedView)) {
            ccpTableName = PROJECTION_CUST_HIERARCHY;
            if (projSelDTO.isIsGenerate() || customerccpId == null || !customerccpId.containsKey(key)) {
                query = SQlUtil.getQuery(Constant.NM_PROJ_DETAILS_SID_QUERY);
                query += Constant.SELECT_FROM_PROJECTION_DETAILS;
                query = query.replaceAll(Constant.TABLENAME_QUESTION, ccpTableName).replaceAll(Constant.PROJSID, String.valueOf(projectionId)).replaceAll(Constant.HIERNO, PERCENTAGE).replaceAll(Constant.LEVELNO_QUESTION, Constant.STRING_ONE);
                ccpId = HelperTableLocalServiceUtil.executeSelectQuery(query);
                customerccpId.put(key, ccpId);
            } else {
                ccpId = customerccpId.get(key);
            }
        }

        if (PRODUCT.getConstant().equals(selectedView)) {
            ccpTableName = PROJECTION_PROD_HIERARCHY;
            if (projSelDTO.isIsGenerate() || productccpId == null || !productccpId.containsKey(key)) {
                query = SQlUtil.getQuery(Constant.NM_PROJ_DETAILS_SID_QUERY);
                query += Constant.SELECT_FROM_PROJECTION_DETAILS;
                query = query.replaceAll(Constant.TABLENAME_QUESTION, ccpTableName).replaceAll(Constant.PROJSID, String.valueOf(projectionId)).replaceAll(Constant.HIERNO, PERCENTAGE).replaceAll(Constant.LEVELNO_QUESTION, Constant.STRING_ONE);
                ccpId = HelperTableLocalServiceUtil.executeSelectQuery(query);
                productccpId.put(key, ccpId);
            } else {
                ccpId = productccpId.get(key);
            }
        }
        if (CUSTOM.getConstant().equals(selectedView) && projSelDTO.getCustomId() != 0) {
            if (projSelDTO.isIsGenerate() || customerccpId == null || !customerccpId.containsKey(key)) {
                query = CommonLogic.getCustomCCPQueryDPR(projSelDTO);
                query += Constant.SELECT_FROM_PROJECTION_DETAILS;
                ccpId = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames()));
                customerccpId.put(key, ccpId);
            } else {
                ccpId = customerccpId.get(key);
            }
        }
        if (ccpId != null && !ccpId.isEmpty()) {
            List<String> discountList;
            discountList = projSelDTO.getDiscountNameList();
            String discountString = getDiscountName(discountList);
            projSelDTO.setCcpCount(ccpId.size());
            String order = projSelDTO.getProjectionOrder();
            List list = getDPRProjectionTotal(projSelDTO, discountString, "Child", order, startAndEndPeriods, ccpTableName, BooleanConstant.getTrueFlag(), PERCENTAGE, Constant.STRING_ONE, BooleanConstant.getFalseFlag());
            if (list != null && !list.isEmpty()) {
                discountProjList.addAll(getCustomizedTotalPivot(list, projSelDTO, false, new DiscountProjectionResultsDTO()));
            }
        }
        return discountProjList;
    }

    /**
     * This method is used to load discount value of parent hierarchy.
     *
     * @param projSelDTO
     * @param startAndEndPeriods
     * @return
     * @throws SystemException
     *
     */
    public List<DiscountProjectionResultsDTO> getPivotHierarchy(ProjectionSelectionDTO projSelDTO, List<Integer> startAndEndPeriods)  {
        List<DiscountProjectionResultsDTO> discountProjList = new ArrayList<>();
        int projectionMasterId = projSelDTO.getProjectionId();
        String selectedView = projSelDTO.getView();
        String level = String.valueOf(projSelDTO.getTreeLevelNo());
        String hierarchyNo = String.valueOf(projSelDTO.getHierarchyNo());
        List ccpId = null;
        String query;
        String ccpTableName = StringUtils.EMPTY;
        /**
         * MultiKey to put input . if input is same then fetch from map else
         * fetch from db and put
         */
        MultiKey key = new MultiKey(projectionMasterId, hierarchyNo, level);
        if (CUSTOMER.getConstant().equals(selectedView)) {
            ccpTableName = PROJECTION_CUST_HIERARCHY;
            if (projSelDTO.isIsGenerate() || customerccpId == null || !customerccpId.containsKey(key)) {
                query = SQlUtil.getQuery(Constant.NM_PROJ_DETAILS_SID_QUERY);
                query += Constant.SELECT_FROM_PROJECTION_DETAILS;
                query = query.replaceAll(Constant.TABLENAME_QUESTION, ccpTableName).replaceAll(Constant.PROJSID, String.valueOf(projectionMasterId)).replaceAll(Constant.HIERNO, hierarchyNo).replaceAll(Constant.LEVELNO_QUESTION, level);
                ccpId = HelperTableLocalServiceUtil.executeSelectQuery(query);
                customerccpId.put(key, ccpId);
            } else {
                ccpId = customerccpId.get(key);
            }
        }
        if (PRODUCT.getConstant().equals(selectedView)) {
            ccpTableName = PROJECTION_PROD_HIERARCHY;
            if (projSelDTO.isIsGenerate() || productccpId == null || !productccpId.containsKey(key)) {
                query = SQlUtil.getQuery(Constant.NM_PROJ_DETAILS_SID_QUERY);
                query += Constant.SELECT_FROM_PROJECTION_DETAILS;
                query = query.replaceAll(Constant.TABLENAME_QUESTION, ccpTableName).replaceAll(Constant.PROJSID, String.valueOf(projectionMasterId)).replaceAll(Constant.HIERNO, hierarchyNo).replaceAll(Constant.LEVELNO_QUESTION, level);
                ccpId = HelperTableLocalServiceUtil.executeSelectQuery(query);
                productccpId.put(key, ccpId);
            } else {
                ccpId = productccpId.get(key);
            }
        }

        if (CUSTOM.getConstant().equals(selectedView) && projSelDTO.getCustomId() != 0) {
            if (projSelDTO.isIsGenerate() || customerccpId == null || !customerccpId.containsKey(key)) {
                query = CommonLogic.getCustomCCPQueryDPR(projSelDTO);
                query += Constant.SELECT_FROM_PROJECTION_DETAILS;
                ccpId = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames()));
                customerccpId.put(key, ccpId);
            } else {
                ccpId = customerccpId.get(key);
            }
        }
        if (ccpId != null && !ccpId.isEmpty()) {
            List<String> discountList;
            discountList = projSelDTO.getDiscountNameList();
            String discountString = getDiscountName(discountList);
            projSelDTO.setCcpCount(ccpId.size());
            String order = projSelDTO.getProjectionOrder();
            List list = getDPRProjectionTotal(projSelDTO, discountString, "Child", order, startAndEndPeriods, ccpTableName, BooleanConstant.getTrueFlag(), hierarchyNo, level, BooleanConstant.getFalseFlag());
            if (list != null && !list.isEmpty()) {
                discountProjList.addAll(getCustomizedTotalPivot(list, projSelDTO, false, new DiscountProjectionResultsDTO()));
            }
            if (Constant.DESCENDING.equals(projSelDTO.getProjectionOrder())) {
                Collections.reverse(discountProjList);
            }
        }
        return discountProjList;
    }

    public int getIntegerForMonth(String month) {
        String[] array = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        return Arrays.asList(array).indexOf(month) + 1;
    }

    /**
     *
     * @param start
     * @param offset
     * @param projSelDTO
     * @return
     *
     */
    public List<DiscountProjectionResultsDTO> getProjectionResults(int start, int offset, ProjectionSelectionDTO projSelDTO)  {
        int neededRecord = offset;
        int mayBeAdded = 0;
        List<DiscountProjectionResultsDTO> projDTOList = new ArrayList<>();
        List<Integer> yearList = new ArrayList<>();
        yearList.add(projSelDTO.getStartYear());
        yearList.add(projSelDTO.getStartMonth());
        yearList.add(projSelDTO.getHistoryEndYear());
        yearList.add(projSelDTO.getHistoryEndMonth());
        yearList.add(projSelDTO.getForecastStartYear());
        String freq = String.valueOf(projSelDTO.getFrequency());
        if (QUARTERLY.getConstant().equals(freq)) {
            int month = projSelDTO.getForecastStartMonth();
            if (month <= NumericConstants.THREE) {
                yearList.add(1);
            } else if (month >= NumericConstants.FOUR && month < NumericConstants.SEVEN) {
                yearList.add(NumericConstants.FOUR);
            } else if (month >= NumericConstants.SEVEN && month < NumericConstants.TEN) {
                yearList.add(NumericConstants.SEVEN);
            } else if (month >= NumericConstants.TEN && month < NumericConstants.THIRTEEN) {
                yearList.add(NumericConstants.TEN);
            }
        } else if (SEMI_ANNUALLY.getConstant().equals(freq)) {
            int month = projSelDTO.getForecastDTO().getForecastStartMonth();
            if (month <= NumericConstants.SIX) {
                yearList.add(1);
            } else if (month >= NumericConstants.SEVEN && month < NumericConstants.THIRTEEN) {
                yearList.add(NumericConstants.SEVEN);
            }
        } else if (ANNUALLY.getConstant().equals(freq)) {
            yearList.add(1);
        } else if (MONTHLY.getConstant().equals(freq)) {
            int month = projSelDTO.getForecastDTO().getForecastStartMonth();
            yearList.add(month);
        }
        yearList.add(projSelDTO.getEndYear());
        yearList.add(projSelDTO.getEndMonth());
        if (projSelDTO.isIsProjectionTotal()) {
            if (neededRecord > 0 && projSelDTO.getPivotView().contains(PERIOD.getConstant())) {
                if (start < 1) {
                    List<DiscountProjectionResultsDTO> list = getPeriodProjectionTotal(projSelDTO.getProjectionId(), yearList, projSelDTO);
                    if (list != null && !list.isEmpty()) {
                        projDTOList.add(list.get(0));
                        neededRecord--;
                    }
                }
                mayBeAdded += 1;
                if (neededRecord > 0) {
                    List<String> discountList = new ArrayList<>();
                    int mayBeAddedRecord = start - mayBeAdded;
                    if (mayBeAddedRecord < 0) {
                        mayBeAddedRecord = 0;
                    }
                    for (int i = mayBeAddedRecord; i < projSelDTO.getDiscountNameList().size(); i++) {
                        discountList.add(projSelDTO.getDiscountNameList().get(i));
                    }
                    List<DiscountProjectionResultsDTO> discountDtoList = getPeriodProjectionTotalDiscount(projSelDTO.getProjectionId(), yearList, projSelDTO, discountList);
                    for (int k = 0; k < discountDtoList.size() && neededRecord > 0; neededRecord--, k++) {
                        projDTOList.add(discountDtoList.get(k));
                    }
                    mayBeAdded += projSelDTO.getDiscountNameList().size();
                }
            } else {
                List<Integer> pivotYearList = new ArrayList<>();
                String frequency = String.valueOf(projSelDTO.getFrequency());
                if (ANNUALLY.getConstant().equals(frequency)) {
                    pivotYearList.add(Integer.valueOf(projSelDTO.getPeriodList().get(0)));
                    pivotYearList.add(1);
                    pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndYear());
                    pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndMonth());
                } else if (QUARTERLY.getConstant().equals(frequency)) {
                    String column = String.valueOf(projSelDTO.getPeriodList().get(0));
                    column = column.replace(Q_SMALL, StringUtils.EMPTY);
                    String year = column.substring(column.length() - NumericConstants.FOUR, column.length());
                    String fre = column.replace(year, StringUtils.EMPTY);
                    pivotYearList.add(Integer.valueOf(year));
                    if (ONE.equals(fre)) {
                        pivotYearList.add(1);
                    } else if (TWO.equals(fre)) {
                        pivotYearList.add(NumericConstants.FOUR);
                    } else if (THREE.equals(fre)) {
                        pivotYearList.add(NumericConstants.SEVEN);
                    } else if (FOUR.equals(fre)) {
                        pivotYearList.add(NumericConstants.TEN);
                    }
                    pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndYear());
                    pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndMonth());
                } else if (MONTHLY.getConstant().equals(frequency)) {
                    loadKeyMap();
                    String column = String.valueOf(projSelDTO.getPeriodList().get(0));
                    String fre = column.substring(0, NumericConstants.THREE);
                    String year = column.replace(fre, StringUtils.EMPTY);
                    String month = valueMap.get(fre);
                    pivotYearList.add(Integer.valueOf(year));
                    pivotYearList.add(Integer.valueOf(month));
                    pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndYear());
                    pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndMonth());

                } else if (SEMI_ANNUALLY.getConstant().equals(frequency)) {
                    String column = String.valueOf(projSelDTO.getPeriodList().get(0));
                    column = column.replace(Constant.S_SMALL, StringUtils.EMPTY);
                    String year = column.substring(column.length() - NumericConstants.FOUR, column.length());
                    String fre = column.replace(year, StringUtils.EMPTY);
                    pivotYearList.add(Integer.valueOf(year));
                    if (ONE.equals(fre)) {
                        pivotYearList.add(1);
                    } else if (TWO.equals(fre)) {
                        pivotYearList.add(NumericConstants.SEVEN);
                    }
                    pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndYear());
                    pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndMonth());
                }
                pivotYearList.add(projSelDTO.getForecastDTO().getForecastStartYear());
                String frequ = String.valueOf(projSelDTO.getFrequency());
                if (QUARTERLY.getConstant().equals(frequ)) {
                    int month = projSelDTO.getForecastDTO().getForecastStartMonth();
                    if (month <= NumericConstants.THREE) {
                        pivotYearList.add(1);
                    } else if (month >= NumericConstants.FOUR && month < NumericConstants.SEVEN) {
                        pivotYearList.add(NumericConstants.FOUR);
                    } else if (month >= NumericConstants.SEVEN && month < NumericConstants.TEN) {
                        pivotYearList.add(NumericConstants.SEVEN);
                    } else if (month >= NumericConstants.TEN && month < NumericConstants.THIRTEEN) {
                        pivotYearList.add(NumericConstants.TEN);
                    }
                } else if (SEMI_ANNUALLY.getConstant().equals(frequ)) {
                    int month = projSelDTO.getForecastDTO().getForecastStartMonth();
                    if (month <= NumericConstants.SIX) {
                        pivotYearList.add(1);
                    } else if (month >= NumericConstants.SEVEN && month < NumericConstants.THIRTEEN) {
                        pivotYearList.add(NumericConstants.SEVEN);
                    }
                } else if (ANNUALLY.getConstant().equals(frequ)) {
                    pivotYearList.add(1);
                } else if (MONTHLY.getConstant().equals(frequ)) {
                    int month = projSelDTO.getForecastDTO().getForecastStartMonth();
                    pivotYearList.add(month);
                }
                pivotYearList.add(projSelDTO.getForecastDTO().getForecastEndYear());
                pivotYearList.add(projSelDTO.getForecastDTO().getForecastEndMonth());
                if (start < 1) {
                    List<DiscountProjectionResultsDTO> discountDtoList;
                    discountDtoList = getPivotProjectionTotal(projSelDTO.getProjectionId(), projSelDTO, yearList);
                    if (discountDtoList != null && !discountDtoList.isEmpty()) {
                        projDTOList.add(discountDtoList.get(0));
                        neededRecord--;
                    }
                }
                mayBeAdded++;
                if (neededRecord > 0) {
                    List<DiscountProjectionResultsDTO> periodList = getPivotProjectionTotalDiscount(projSelDTO, yearList);

                    int mayBeAddedRecord = start - mayBeAdded;
                    if (mayBeAddedRecord < 0) {
                        mayBeAddedRecord = 0;
                    }
                    for (int i = mayBeAddedRecord; i < periodList.size(); i++) {
                        projDTOList.add(periodList.get(i));
                        neededRecord--;
                    }
                    mayBeAdded += periodList.size();
                }
            }
        } else if (neededRecord > 0 && projSelDTO.getPivotView().contains(PERIOD.getConstant())) {
            List<DiscountProjectionResultsDTO> discountDtoList;
            if (neededRecord > 0) {
                List<String> discountList = new ArrayList<>();
                int mayBeAddedRecord = start - mayBeAdded;
                if (mayBeAddedRecord < 0) {
                    mayBeAddedRecord = 0;
                }
                for (int i = mayBeAddedRecord; i < projSelDTO.getDiscountNameList().size(); i++) {
                    discountList.add(projSelDTO.getDiscountNameList().get(i));
                }
                discountDtoList = getPeriodHierarchy(projSelDTO, yearList, discountList);
                for (int k = 0; k < discountDtoList.size() && neededRecord > 0; neededRecord--, k++) {
                    projDTOList.add(discountDtoList.get(k));
                }
                mayBeAdded += projSelDTO.getDiscountNameList().size();
            }
        } else {
            List<Integer> pivotYearList = new ArrayList<>();
            String frequency = String.valueOf(projSelDTO.getFrequency());
            if (ANNUALLY.getConstant().equals(frequency)) {
                pivotYearList.add(Integer.valueOf(projSelDTO.getPeriodList().get(0)));
                pivotYearList.add(1);
                pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndYear());
                pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndMonth());
            } else if (QUARTERLY.getConstant().equals(frequency)) {
                String column = String.valueOf(projSelDTO.getPeriodList().get(0));
                column = column.replace(Q_SMALL, StringUtils.EMPTY);
                String year = column.substring(column.length() - NumericConstants.FOUR, column.length());
                String fre = column.replace(year, StringUtils.EMPTY);
                pivotYearList.add(Integer.valueOf(year));
                if (ONE.equals(fre)) {
                    pivotYearList.add(1);
                } else if (TWO.equals(fre)) {
                    pivotYearList.add(NumericConstants.FOUR);
                } else if (THREE.equals(fre)) {
                    pivotYearList.add(NumericConstants.SEVEN);
                } else if (FOUR.equals(fre)) {
                    pivotYearList.add(NumericConstants.TEN);
                }
                pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndYear());
                pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndMonth());
            } else if (MONTHLY.getConstant().equals(frequency)) {
                loadKeyMap();
                String column = String.valueOf(projSelDTO.getPeriodList().get(0));
                String fre = column.substring(0, NumericConstants.THREE);
                String year = column.replace(fre, StringUtils.EMPTY);
                String month = valueMap.get(fre);
                pivotYearList.add(Integer.valueOf(year));
                pivotYearList.add(Integer.valueOf(month));
                pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndYear());
                pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndMonth());

            } else if (SEMI_ANNUALLY.getConstant().equals(frequency)) {
                String column = String.valueOf(projSelDTO.getPeriodList().get(0));
                column = column.replace(Constant.S_SMALL, StringUtils.EMPTY);
                String year = column.substring(column.length() - NumericConstants.FOUR, column.length());
                String fre = column.replace(year, StringUtils.EMPTY);
                pivotYearList.add(Integer.valueOf(year));
                if (ONE.equals(fre)) {
                    pivotYearList.add(1);
                } else if (TWO.equals(fre)) {
                    pivotYearList.add(NumericConstants.SEVEN);
                }
                pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndYear());
                pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndMonth());
            }
            pivotYearList.add(projSelDTO.getForecastDTO().getForecastStartYear());
            String frequ = String.valueOf(projSelDTO.getFrequency());
            if (QUARTERLY.getConstant().equals(frequ)) {
                int month = projSelDTO.getForecastDTO().getForecastStartMonth();
                if (month <= NumericConstants.THREE) {
                    pivotYearList.add(1);
                } else if (month >= NumericConstants.FOUR && month < NumericConstants.SEVEN) {
                    pivotYearList.add(NumericConstants.FOUR);
                } else if (month >= NumericConstants.SEVEN && month < NumericConstants.TEN) {
                    pivotYearList.add(NumericConstants.SEVEN);
                } else if (month >= NumericConstants.TEN && month < NumericConstants.THIRTEEN) {
                    pivotYearList.add(NumericConstants.TEN);
                }
            } else if (SEMI_ANNUALLY.getConstant().equals(frequ)) {
                int month = projSelDTO.getForecastDTO().getForecastStartMonth();
                if (month <= NumericConstants.SIX) {
                    pivotYearList.add(1);
                } else if (month >= NumericConstants.SEVEN && month < NumericConstants.THIRTEEN) {
                    pivotYearList.add(NumericConstants.SEVEN);
                }
            } else if (ANNUALLY.getConstant().equals(frequ)) {
                pivotYearList.add(1);
            } else if (MONTHLY.getConstant().equals(frequ)) {
                int month = projSelDTO.getForecastDTO().getForecastStartMonth();
                pivotYearList.add(month);
            }
            pivotYearList.add(projSelDTO.getForecastDTO().getForecastEndYear());
            pivotYearList.add(projSelDTO.getForecastDTO().getForecastEndMonth());

            if (neededRecord > 0 && !projSelDTO.getGroup().startsWith(Constant.DISCOUNT)) {
                List<DiscountProjectionResultsDTO> periodList = getPivotHierarchy(projSelDTO, pivotYearList);
                int mayBeAddedRecord = start - mayBeAdded;
                if (mayBeAddedRecord < 0) {
                    mayBeAddedRecord = 0;
                }
                for (int i = mayBeAddedRecord; i < periodList.size(); i++) {
                    projDTOList.add(periodList.get(i));
                    neededRecord--;
                }
                mayBeAdded += periodList.size();
            }
        }

        if (neededRecord > 0 && !projSelDTO.isIsFilter()) {
            if ((projSelDTO.getTreeLevelNo() + 1) == projSelDTO.getTpLevel()
                    && ((projSelDTO.isIsCustomHierarchy()) || (!INDICATOR_VIEW_PRODUCT.getConstant().equals(projSelDTO.getHierarchyIndicator())))
                    && !projSelDTO.getGroup().startsWith(Constant.DISCOUNT) && !projSelDTO.getGroupFilter().startsWith(Constant.ALL_DISCOUNT_GROUP)) {
                DiscountProjectionResultsDTO dto = new DiscountProjectionResultsDTO();
                dto.setLevelNo(projSelDTO.getLevelNo());
                dto.setTreeLevelNo(projSelDTO.getTreeLevelNo());
                dto.setParentNode(projSelDTO.getParentNode());
                dto.setGroup(projSelDTO.getGroupFilter());
                dto.setLevelValue(projSelDTO.getLevelValue());
                dto.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
                dto.setHierarchyNo(projSelDTO.getHierarchyNo());
                if (INDICATOR_VIEW_CUSTOMER.getConstant().equals(dto.getHierarchyIndicator())) {
                    dto.setCustomerHierarchyNo(dto.getHierarchyNo());
                    dto.setProductHierarchyNo(projSelDTO.getProductHierarchyNo());
                } else if (INDICATOR_VIEW_PRODUCT.getConstant().equals(dto.getHierarchyIndicator())) {
                    dto.setProductHierarchyNo(dto.getHierarchyNo());
                    dto.setCustomerHierarchyNo(projSelDTO.getCustomerHierarchyNo());
                }
                dto.setParent(1);
                DiscountProjectionResultsDTO discountDTO;
                if (Constant.PERIOD.equals(projSelDTO.getPivotView())) {

                    discountDTO = getChildNodeValues(dto, projSelDTO);
                } else {
                    discountDTO = getPivotChildNodeValues(dto, projSelDTO);
                }

                projDTOList.add(discountDTO);
            } else {
                int mayBeAddedRecord = start - mayBeAdded;
                if (mayBeAddedRecord < 0) {
                    mayBeAddedRecord = 0;
                }
                projSelDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo() + 1);
                List<DiscountProjectionResultsDTO> nextLevelValueList = configureLevels(mayBeAddedRecord, neededRecord, projSelDTO);
                projDTOList.addAll(nextLevelValueList);
            }
        }
        return projDTOList;
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
    public List<DiscountProjectionResultsDTO> getConfiguredProjectionResults(Object parentId, int start, int offset, ProjectionSelectionDTO projSelDTO)  {
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
                projSelDTO.setHierarchyIndicator(CUSTOM.getConstant().equals(projSelDTO.getView()) ? getCustomViewHierarchyIndicator(projSelDTO.getCustomId(), projSelDTO.getTreeLevelNo()) : parentDto.getHierarchyIndicator());
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
                    projSelDTO.setHierarchyIndicator(CUSTOM.getConstant().equals(projSelDTO.getView()) ? getCustomViewHierarchyIndicator(projSelDTO.getCustomId(), 1) : projSelDTO.getHierarchyIndicator());
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
            resultList = getProjectionResults(start, offset, projSelDTO);
        } else {
            projSelDTO.setIsProjectionTotal(false);
            projSelDTO.setIsTotal(true);
            projSelDTO.setLevelNo(projSelDTO.getFilterLevelNo());
            projSelDTO.setTreeLevelNo(projSelDTO.getFilterLevelNo());
            resultList = configureLevels(start, offset, projSelDTO);
        }
        return resultList;
    }

    /**
     * This method is used to Load Child Node with value
     *
     * @param start
     * @param offset
     * @param projSelDTO
     * @return
     *
     */
    public List<DiscountProjectionResultsDTO> configureLevels(int start, int offset, ProjectionSelectionDTO projSelDTO)  {
        int neededRecord = offset;
        List<DiscountProjectionResultsDTO> resultList = new ArrayList<>();
        if (neededRecord > 0) {
            List<Leveldto> levelList = CommonLogic.getConditionalLevelList(projSelDTO.getProjectionId(), Constant.DISCOUNT_PROJECTION_RESULTS, start, offset, projSelDTO.getHierarchyIndicator(), projSelDTO.getTreeLevelNo(), projSelDTO.getHierarchyNo(), projSelDTO.getProductHierarchyNo(), projSelDTO.getCustomerHierarchyNo(), projSelDTO.isIsFilter(), false, projSelDTO.isIsCustomHierarchy(), projSelDTO.getCustomId(), projSelDTO.getGroupFilter(), projSelDTO.getUserId(), projSelDTO.getSessionId(), projSelDTO.getCustRelationshipBuilderSid(), projSelDTO.getProdRelationshipBuilderSid(), false, true, projSelDTO.getDiscountNoList(), projSelDTO);
            for (int i = 0; i < levelList.size() && neededRecord > 0; neededRecord--, i++) {
                Leveldto levelDto = levelList.get(i);

                DiscountProjectionResultsDTO dto = new DiscountProjectionResultsDTO();
                dto.setLevelNo(levelDto.getLevelNo());
                dto.setTreeLevelNo(levelDto.getTreeLevelNo());
                dto.setParentNode(levelDto.getParentNode());
                dto.setGroup(projSelDTO.getSessionDTO().getLevelValueDiscription(levelDto.getHierarchyNo(), levelDto.getHierarchyIndicator()));
                dto.setLevelValue(levelDto.getRelationshipLevelValue());
                dto.setHierarchyIndicator(levelDto.getHierarchyIndicator());
                dto.setHierarchyNo(levelDto.getHierarchyNo());
                if (INDICATOR_VIEW_CUSTOMER.getConstant().equals(dto.getHierarchyIndicator())) {
                    dto.setCustomerHierarchyNo(dto.getHierarchyNo());
                    dto.setProductHierarchyNo(projSelDTO.getProductHierarchyNo());
                } else if (INDICATOR_VIEW_PRODUCT.getConstant().equals(dto.getHierarchyIndicator())) {
                    dto.setProductHierarchyNo(dto.getHierarchyNo());
                    dto.setCustomerHierarchyNo(projSelDTO.getCustomerHierarchyNo());
                }
                dto.setParent(1);
                dto.setTotal(0);
                DiscountProjectionResultsDTO discountDTO;
                if (Constant.PERIOD.equals(projSelDTO.getPivotView())) {
                    projSelDTO.setHierarchyIndicator(levelDto.getHierarchyIndicator());
                    projSelDTO.setTreeLevelNo(levelDto.getTreeLevelNo());
                    projSelDTO.setCustomerHierarchyNo(dto.getCustomerHierarchyNo());
                    projSelDTO.setProductHierarchyNo(dto.getProductHierarchyNo());
                    discountDTO = getChildNodeValues(dto, projSelDTO);
                } else {
                    projSelDTO.setHierarchyIndicator(levelDto.getHierarchyIndicator());
                    projSelDTO.setTreeLevelNo(levelDto.getTreeLevelNo());
                    projSelDTO.setCustomerHierarchyNo(dto.getCustomerHierarchyNo());
                    projSelDTO.setProductHierarchyNo(dto.getProductHierarchyNo());
                    discountDTO = getPivotChildNodeValues(dto, projSelDTO);
                }
                discountDTO.setTreeLevelNo(dto.getTreeLevelNo());
                resultList.add(discountDTO);
            }
        }

        return resultList;
    }

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
            count += getProjectionResultsCount(projSelDTO, isLevelsCount);
        } else if (isLevelsCount) {
            projSelDTO.setIsProjectionTotal(false);
            projSelDTO.setIsTotal(true);

            projSelDTO.setLevelNo(projSelDTO.getFilterLevelNo());
            projSelDTO.setTreeLevelNo(projSelDTO.getFilterLevelNo());
            count += configureLevelsCount(projSelDTO);
        }
        return count;
    }

    public int getProjectionResultsCount(ProjectionSelectionDTO projSelDTO, boolean isLevelsCount)  {
        int count = 0;
        projSelDTO.setGroupCount(false);
        if (!projSelDTO.getGroup().startsWith(Constant.DISCOUNT)) {
            if (projSelDTO.getPivotView().contains(PERIOD.getConstant())) {
                if (projSelDTO.isIsProjectionTotal()) {
                    count = count + projSelDTO.getDiscountNoList().size();
                } else {
                    String query = CommonLogic.getCCPQuery(projSelDTO, Boolean.FALSE) + getDiscountCountForCurrentHierarchy(projSelDTO);
                    List<Object> list = (List<Object>) HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames()));
                    if (list != null && !list.isEmpty()) {
                        Object ob = list.get(0);
                        count = count + Integer.parseInt(String.valueOf(ob));
                    }
                }
            } else {
                count = count + projSelDTO.getPeriodList().size();
            }
        }
        if (projSelDTO.isIsProjectionTotal()) {
            count++;
        }

        if (isLevelsCount && !projSelDTO.isIsFilter()) {
            if ((projSelDTO.getTreeLevelNo() + 1) == projSelDTO.getTpLevel()
                    && ((projSelDTO.isIsCustomHierarchy()) || (!INDICATOR_VIEW_PRODUCT.getConstant().equals(projSelDTO.getHierarchyIndicator())))
                    && !Constant.ALL_DISCOUNT_GROUP.equals(projSelDTO.getGroupFilter()) && !projSelDTO.getGroup().startsWith(Constant.DISCOUNT)) {
                count = count + 1;
                projSelDTO.setGroupCount(true);
                projSelDTO.setLevelCount(1);
            } else {
                projSelDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo() + 1);
                int ct = configureLevelsCount(projSelDTO);
                count = count + ct;
                projSelDTO.setLevelCount(ct);
            }
        }
        return count;
    }

    public int configureLevelsCount(ProjectionSelectionDTO projSelDTO)  {
        return CommonLogic.getLevelListCount(projSelDTO.getProjectionId(), Constant.DISCOUNT_PROJECTION_RESULTS, projSelDTO.getHierarchyIndicator(), projSelDTO.getTreeLevelNo(), projSelDTO.getHierarchyNo(), projSelDTO.getProductHierarchyNo(), projSelDTO.getCustomerHierarchyNo(), projSelDTO.isIsFilter(), projSelDTO.isIsCustomHierarchy(), projSelDTO.getCustomId(), projSelDTO.getGroupFilter(), projSelDTO.getUserId(), projSelDTO.getSessionId(), projSelDTO.getCustRelationshipBuilderSid(), projSelDTO.getProdRelationshipBuilderSid(), projSelDTO.getDiscountNoList(), projSelDTO);
    }

    /**
     * This method is used to Load aggregate value of child node
     *
     * @param dto
     * @param projSelDTO
     * @return
     *
     */
    public DiscountProjectionResultsDTO getChildNodeValues(DiscountProjectionResultsDTO dto, ProjectionSelectionDTO projSelDTO)  {
        int projectionMasterId = projSelDTO.getProjectionId();
        dto.setIsParent(Constant.STRING_ONE);
        dto.setParent(1);
        String hierachyNumber = String.valueOf(dto.getHierarchyNo());
        hierachyNumber = hierachyNumber + PERCENTAGE;
        List<Integer> yearList = new ArrayList<>();
        yearList.add(projSelDTO.getForecastDTO().getHistoryStartYear());
        yearList.add(projSelDTO.getForecastDTO().getHistoryStartMonth());
        yearList.add(projSelDTO.getForecastDTO().getHistoryEndYear());
        yearList.add(projSelDTO.getForecastDTO().getHistoryEndMonth());

        yearList.add(projSelDTO.getForecastDTO().getForecastStartYear());
        String freq = String.valueOf(projSelDTO.getFrequency());
        if (QUARTERLY.getConstant().equals(freq)) {
            int month = projSelDTO.getForecastDTO().getForecastStartMonth();
            if (month <= NumericConstants.THREE) {
                yearList.add(1);
            } else if (month >= NumericConstants.FOUR && month < NumericConstants.SEVEN) {
                yearList.add(NumericConstants.FOUR);
            } else if (month >= NumericConstants.SEVEN && month < NumericConstants.TEN) {
                yearList.add(NumericConstants.SEVEN);
            } else if (month >= NumericConstants.TEN && month < NumericConstants.THIRTEEN) {
                yearList.add(NumericConstants.TEN);
            }
        } else if (SEMI_ANNUALLY.getConstant().equals(freq)) {
            int month = projSelDTO.getForecastDTO().getForecastStartMonth();
            if (month <= NumericConstants.SIX) {
                yearList.add(1);
            } else if (month >= NumericConstants.SEVEN && month < NumericConstants.THIRTEEN) {
                yearList.add(NumericConstants.SEVEN);
            }
        } else if (ANNUALLY.getConstant().equals(freq)) {
            yearList.add(1);
        } else if (MONTHLY.getConstant().equals(freq)) {
            int month = projSelDTO.getForecastDTO().getForecastStartMonth();
            yearList.add(month);
        }
        yearList.add(projSelDTO.getForecastDTO().getForecastEndYear());
        yearList.add(projSelDTO.getForecastDTO().getForecastEndMonth());
        String selectedView = projSelDTO.getView();
        List ccpId = null;
        String query = "";
        String ccpTableName = "";
        /**
         * MultiKey to put input . if input is same then fetch from map else
         * fetch from db and put
         */
        MultiKey key = new MultiKey(projectionMasterId, hierachyNumber, String.valueOf(dto.getTreeLevelNo()));
        if (CUSTOMER.getConstant().equals(selectedView)) {
            ccpTableName = PROJECTION_CUST_HIERARCHY;
            if (projSelDTO.isIsGenerate() || customerccpId == null || !customerccpId.containsKey(key)) {
                query = SQlUtil.getQuery(Constant.NM_PROJ_DETAILS_SID_QUERY);
                query += Constant.SELECT_FROM_PROJECTION_DETAILS;
                query = query.replaceAll(Constant.TABLENAME_QUESTION, ccpTableName).replaceAll(Constant.PROJSID, String.valueOf(projectionMasterId)).replaceAll(Constant.HIERNO, hierachyNumber).replaceAll(Constant.LEVELNO_QUESTION, String.valueOf(dto.getTreeLevelNo()));
                ccpId = HelperTableLocalServiceUtil.executeSelectQuery(query);
                customerccpId.put(key, ccpId);
            } else {
                ccpId = customerccpId.get(key);
            }
        }
        if (PRODUCT.getConstant().equals(selectedView)) {
            ccpTableName = PROJECTION_PROD_HIERARCHY;
            if (projSelDTO.isIsGenerate() || productccpId == null || !productccpId.containsKey(key)) {
                query = SQlUtil.getQuery(Constant.NM_PROJ_DETAILS_SID_QUERY);
                query += Constant.SELECT_FROM_PROJECTION_DETAILS;
                query = query.replaceAll(Constant.TABLENAME_QUESTION, ccpTableName).replaceAll(Constant.PROJSID, String.valueOf(projectionMasterId)).replaceAll(Constant.HIERNO, hierachyNumber).replaceAll(Constant.LEVELNO_QUESTION, String.valueOf(dto.getTreeLevelNo()));
                ccpId = HelperTableLocalServiceUtil.executeSelectQuery(query);
                productccpId.put(key, ccpId);
            } else {
                ccpId = productccpId.get(key);
            }
        }
        if (CUSTOM.getConstant().equals(selectedView) && projSelDTO.getCustomId() != 0) {
            query = CommonLogic.getCustomCCPQueryDPR(projSelDTO);
            query += Constant.SELECT_FROM_PROJECTION_DETAILS;
            ccpId = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames()));
        }
        if (ccpId != null && !ccpId.isEmpty()) {
            List<String> discountList;
            discountList = projSelDTO.getDiscountNameList();
            String discountString = getDiscountName(discountList);
            projSelDTO.setCcpCount(ccpId.size());
            String order = projSelDTO.getProjectionOrder();
            List list = getDPRProjectionTotal(projSelDTO, discountString, Constant.PARENT, order, startAndEndPeriods, ccpTableName, 
                    BooleanConstant.getFalseFlag(), hierachyNumber, String.valueOf(dto.getTreeLevelNo()), BooleanConstant.getFalseFlag());
            if (list != null && !list.isEmpty()) {
                dto = getCustomizedTotal(list, projSelDTO, false, dto).get(0);
            }
        }
        return dto;
    }

    /**
     * This method is used to load the aggregate value of child node in pivot
     * mode
     *
     * @param dto
     * @param projSelDTO
     * @return
     *
     */
    public DiscountProjectionResultsDTO getPivotChildNodeValues(DiscountProjectionResultsDTO dto, ProjectionSelectionDTO projSelDTO)  {
        int projectionMasterId = projSelDTO.getProjectionId();
        dto.setIsParent(Constant.STRING_ONE);
        dto.setParent(1);
        String hierachyNumber = String.valueOf(dto.getHierarchyNo());
        hierachyNumber = hierachyNumber + PERCENTAGE;
        List<Integer> pivotYearList = new ArrayList<>();
        pivotYearList.add(projSelDTO.getStartYear());
        pivotYearList.add(projSelDTO.getStartMonth());
        pivotYearList.add(projSelDTO.getHistoryEndYear());
        pivotYearList.add(projSelDTO.getHistoryEndMonth());
        pivotYearList.add(projSelDTO.getForecastStartYear());

        String frequ = String.valueOf(projSelDTO.getFrequency());
        if (QUARTERLY.getConstant().equals(frequ)) {
            int month = projSelDTO.getForecastDTO().getForecastStartMonth();
            if (month <= NumericConstants.THREE) {
                pivotYearList.add(1);
            } else if (month >= NumericConstants.FOUR && month < NumericConstants.SEVEN) {
                pivotYearList.add(NumericConstants.FOUR);
            } else if (month >= NumericConstants.SEVEN && month < NumericConstants.TEN) {
                pivotYearList.add(NumericConstants.SEVEN);
            } else if (month >= NumericConstants.TEN && month < NumericConstants.THIRTEEN) {
                pivotYearList.add(NumericConstants.TEN);
            }
        } else if (SEMI_ANNUALLY.getConstant().equals(frequ)) {
            int month = projSelDTO.getForecastDTO().getForecastStartMonth();
            if (month <= NumericConstants.SIX) {
                pivotYearList.add(1);
            } else if (month >= NumericConstants.SEVEN && month < NumericConstants.THIRTEEN) {
                pivotYearList.add(NumericConstants.SEVEN);
            }
        } else if (ANNUALLY.getConstant().equals(frequ)) {
            pivotYearList.add(1);
        } else if (MONTHLY.getConstant().equals(frequ)) {
            int month = projSelDTO.getForecastDTO().getForecastStartMonth();
            pivotYearList.add(month);
        }

        pivotYearList.add(projSelDTO.getForecastDTO().getForecastEndYear());
        pivotYearList.add(projSelDTO.getForecastDTO().getForecastEndMonth());
        String selectedView = projSelDTO.getView();
        List ccpId = null;
        String query = "";
        String ccpTableName = "";
        /**
         * MultiKey to put input . if input is same then fetch from map else
         * fetch from db and put
         */
        MultiKey key = new MultiKey(projectionMasterId, hierachyNumber, String.valueOf(dto.getTreeLevelNo()));
        if (CUSTOMER.getConstant().equals(selectedView)) {
            ccpTableName = PROJECTION_CUST_HIERARCHY;
            if (projSelDTO.isIsGenerate() || customerccpId == null || !customerccpId.containsKey(key)) {
                query = SQlUtil.getQuery(Constant.NM_PROJ_DETAILS_SID_QUERY);
                query += Constant.SELECT_FROM_PROJECTION_DETAILS;
                query = query.replaceAll(Constant.TABLENAME_QUESTION, ccpTableName).replaceAll(Constant.PROJSID, String.valueOf(projectionMasterId)).replaceAll(Constant.HIERNO, hierachyNumber).replaceAll(Constant.LEVELNO_QUESTION, String.valueOf(dto.getTreeLevelNo()));
                ccpId = HelperTableLocalServiceUtil.executeSelectQuery(query);
                customerccpId.put(key, ccpId);
            } else {
                ccpId = customerccpId.get(key);
            }
        }
        if (PRODUCT.getConstant().equals(selectedView)) {
            ccpTableName = PROJECTION_PROD_HIERARCHY;
            if (projSelDTO.isIsGenerate() || productccpId == null || !productccpId.containsKey(key)) {
                query = SQlUtil.getQuery(Constant.NM_PROJ_DETAILS_SID_QUERY);
                query += Constant.SELECT_FROM_PROJECTION_DETAILS;
                query = query.replaceAll(Constant.TABLENAME_QUESTION, ccpTableName).replaceAll(Constant.PROJSID, String.valueOf(projectionMasterId)).replaceAll(Constant.HIERNO, hierachyNumber).replaceAll(Constant.LEVELNO_QUESTION, String.valueOf(dto.getTreeLevelNo()));
                ccpId = HelperTableLocalServiceUtil.executeSelectQuery(query);
                productccpId.put(key, ccpId);
            } else {
                ccpId = productccpId.get(key);
            }
        }

        if (CUSTOM.getConstant().equals(selectedView) && projSelDTO.getCustomId() != 0) {
            query = CommonLogic.getCustomCCPQueryDPR(projSelDTO);
            query += Constant.SELECT_FROM_PROJECTION_DETAILS;
            ccpId = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, projSelDTO.getSessionDTO().getCurrentTableNames()));
        }
        if (ccpId != null && !ccpId.isEmpty()) {
            List<String> discountList;
            discountList = projSelDTO.getDiscountNameList();
            String discountString = getDiscountName(discountList);
            String order = projSelDTO.getProjectionOrder();
            List list = getDPRProjectionTotal(projSelDTO, discountString, Constant.PARENT, order, pivotYearList, ccpTableName, BooleanConstant.getTrueFlag(), hierachyNumber, String.valueOf(dto.getTreeLevelNo()), BooleanConstant.getTrueFlag());

            if (list != null && !list.isEmpty()) {
                DiscountProjectionResultsDTO discountDto = new DiscountProjectionResultsDTO();
                discountDto.setHierarchySid(ZERO);
                discountDto.setLevelNo(0);
                discountDto.setHierarchylevelId(ZERO);
                discountDto.setIsParent(ONE);
                discountDto.setHierarchyNo(ONE);
                discountDto.setTotal(1);
                dto.setParent(1);
                dto = getCustomizedTotalPivot(list, projSelDTO, true, dto).get(0);
                return dto;
            }
        }
        return dto;
    }


    public String getFormattedValue(DecimalFormat format, String value) {
        if (value.contains(NULL)) {
            value = DASH.getConstant();
        } else {
            Double newValue = Double.valueOf(value);
            if (format.toPattern().contains(Constant.PERCENT)) {
                newValue = newValue / NumericConstants.HUNDRED;
            }
            value = format.format(newValue);
        }
        return value;
    }


    private void loadKeyMap() {
        valueMap.put("jan", Constant.STRING_ONE);
        valueMap.put("feb", "2");
        valueMap.put("mar", "3");
        valueMap.put("apr", "4");
        valueMap.put("may", "5");
        valueMap.put("jun", "6");
        valueMap.put("jul", "7");
        valueMap.put("aug", "8");
        valueMap.put("sep", "9");
        valueMap.put("oct", "10");
        valueMap.put("nov", "11");
        valueMap.put("dec", "12");

    }

    private String getDiscountName(List<String> discountList) {
        String discountString = StringUtils.EMPTY;
        for (int i = 0; i < discountList.size(); i++) {
            if (i != discountList.size() - 1) {
                discountString = discountString.concat("'") + discountList.get(i) + "',";
            } else {
                discountString = discountString.concat("'") + discountList.get(i) + "'";
            }
        }
        if (discountString.equals(StringUtils.EMPTY)) {
            discountString = ZERO;
        }
        return discountString;
    }

    private DiscountProjectionResultsDTO putHyphenForDTO(List<String> periodList, DiscountProjectionResultsDTO discountDto) {
        if (!periodList.isEmpty()) {
            for (int j = 0; j < periodList.size(); j++) {
                String column = periodList.get(j);
                String columns = column + ACTUALSRATE;
                discountDto.addStringProperties(columns, getFormattedValue(CUR_ZERO, NULL));
                String columns1 = column + ACTUALSAMOUNT;
                discountDto.addStringProperties(columns1, getFormattedValue(CUR_ZERO, NULL));
                String columns2 = column + PROJECTIONSAMOUNT;
                discountDto.addStringProperties(columns2, getFormattedValue(CUR_ZERO, NULL));
                String columns3 = column + PROJECTIONSRATE;
                discountDto.addStringProperties(columns3, getFormattedValue(CUR_ZERO, NULL));
                String columns4 = column + ACTUALRPU;
                discountDto.addStringProperties(columns4, getFormattedValue(CUR_ZERO, NULL));
                String columns5 = column + PROJECTEDRPU;
                discountDto.addStringProperties(columns5, getFormattedValue(CUR_ZERO, NULL));
            }
        }
        return discountDto;
    }

    public static Object executeSelectQuery(String query, Object udc1, Object udc2)  {

        return commonDao.executeSelectQuery(query, udc1, udc2);

    }


    /**
     * *
     * to get the selected discount count
     *
     * @param list
     * @return
     */
    int getDiscountRSCount(List<List<String>> list) {
        return list != null && list.get(0) != null ? list.get(0).size() : 0;

    }

    /**
     * Used to get the count of discount comes under expanded hierarchy in Total
     * Discount
     *
     * @return
     */
    private String getDiscountCountForCurrentHierarchy(ProjectionSelectionDTO projSelDTO) {
        String tableName = Constant.VIEW.equals(projSelDTO.getSessionDTO().getAction()) ? StringUtils.EMPTY : "ST_";
        return "SELECT Count(DISTINCT RS_CONTRACT_SID)\n"
                + "FROM " + tableName + "NM_DISCOUNT_PROJ_MASTER B\n"
                + "       JOIN PROJECTION_DETAILS pd\n"
                + "         ON pd.PROJECTION_DETAILS_SID = b.PROJECTION_DETAILS_SID\n"
                + "       JOIN @CCP CCP\n"
                + "         ON pd.ccp_details_sid = ccp.CCP_DETAILS_SID\n"
                + "WHERE  B.RS_CONTRACT_SID IN ( " + CommonUtils.CollectionToString(projSelDTO.getDiscountNoList(), false) + " )";

    }

    private List getDPRProjectionTotal(ProjectionSelectionDTO proSelDTO, String discountString, String view, String order, List<Integer> startAndEndPeriods, String ccpTableName, boolean rsRequired, String hierarchyNo, String levelNo, boolean isPivotHier) {
        String queryName = Constant.VIEW.equals(proSelDTO.getSessionDTO().getAction()) ? "getNMProjDetailsSid_CustomQuery_View_New" : "getNMProjDetailsSid_CustomQuery_New";
        String totalQueryName = Constant.VIEW.equals(proSelDTO.getSessionDTO().getAction()) ? "getNMDPRProjectionTotal_Query_View_New" : "getNMDPRProjectionTotal_Query_New";
        int projectionId = proSelDTO.getProjectionId();
        String frequency = proSelDTO.getFrequency();
        String startPeriod = "";
        String forecastStartPeriod = "";
        String forecastEndPeriod = "";
        if (discountString.equals("0")) {
            discountString = "'" + discountString + "'";
        }
        if (startAndEndPeriods != null && startAndEndPeriods.size() != 0) {
            String hsYear = String.valueOf(startAndEndPeriods.get(0));
            String hsMonth = String.valueOf(startAndEndPeriods.get(1));
            String feYear = String.valueOf(startAndEndPeriods.get(NumericConstants.SIX));
            String feMonth = String.valueOf(startAndEndPeriods.get(NumericConstants.SEVEN));

            if (hsMonth.length() == 1) {
                hsMonth = "0" + hsMonth;
            }
            startPeriod = hsYear + hsMonth;

            Calendar calendar = Calendar.getInstance();
            int month = calendar.get(Calendar.MONTH) + 1;
            calendar.add(Calendar.MONTH, -((month % NumericConstants.THREE) == 0 ? NumericConstants.THREE : (month % NumericConstants.THREE)));
            calendar = Calendar.getInstance();
            month = calendar.get(Calendar.MONTH) + 1;
            calendar.add(Calendar.MONTH, -((month % NumericConstants.THREE) == 0 ? NumericConstants.THREE : (month % NumericConstants.THREE)));
            forecastStartPeriod = startPeriod;
            if (feMonth.length() == 1) {
                feMonth = "0" + feMonth;
            }
            forecastEndPeriod = feYear + feMonth;

        }
        if (frequency.equals(QUARTERLY.getConstant())) {
            frequency = Constant.QUARTER;
        }
        if (frequency.equals(ANNUALLY.getConstant())) {
            frequency = "YEAR";
        }
        if (frequency.equals(SEMI_ANNUALLY.getConstant())) {
            frequency = Constant.SEMI_ANNUAL;

        }
        if (frequency.equals(MONTHLY.getConstant())) {
            frequency = "MONTH";
        }

        String rsName = rsRequired ? " ,NMDPM.RS_NAME " : "";

        String query;
        //CCP query 
        if (!proSelDTO.isIsCustomHierarchy()) {
            query = SQlUtil.getQuery(Constant.NM_PROJ_DETAILS_SID_QUERY);

            //For replacing corresponding values in CCP query
            query = query.replaceAll(Constant.TABLENAME_QUESTION, ccpTableName).replaceAll(Constant.PROJSID, String.valueOf(projectionId)).replaceAll(Constant.HIERNO, hierarchyNo).replaceAll(Constant.LEVELNO_QUESTION, levelNo);

            //Main query
            query += SQlUtil.getQuery(totalQueryName);

            //For replacing in main select query
            query = query.replaceAll("\\?FREQUENCY", getFrequencyCondition(frequency, isPivotHier)).replaceAll("\\?DISCOUNTNAME", discountString)
                    .replaceAll("\\?RSNAME", rsName).replaceAll("\\?GRSNAME", getGroupByCondition(frequency, isPivotHier, rsRequired))
                    .replaceAll("\\?PERIODCOND", getPeriodCondition(isPivotHier, forecastStartPeriod, forecastEndPeriod));
        } else if (proSelDTO.getCustomId() != 0) {
            query = CommonLogic.getCustomCCPQueryDPR(proSelDTO);

            //Main query
            query += SQlUtil.getQuery(queryName);

            //For replacing in main select query
            query = query.replaceAll("\\?FREQUENCY", getFrequencyCondition(frequency, isPivotHier)).replaceAll("\\?DISCOUNTNAME", discountString)
                    .replaceAll("\\?RSNAME", rsName).replaceAll("\\?GRSNAME", getGroupByCondition(frequency, isPivotHier, rsRequired))
                    .replaceAll("\\?PERIODCOND", getPeriodCondition(isPivotHier, forecastStartPeriod, forecastEndPeriod));
        } else {
            return Collections.emptyList();
        }

        String viewOrder = "Descending".equalsIgnoreCase(order) ? " DESC " : "";

        if (isPivotHier) {
            query += "ORDER BY NMDPM.RS_NAME";
        } else if (view.equalsIgnoreCase("parent")) {
            rsName = rsRequired ? " NMDPM.RS_NAME, " : "";
            if (frequency.equals("YEAR")) {
                query = query + "ORDER BY " + rsName + " PR.YEAR " + viewOrder;
            } else {
                query = query + "ORDER BY " + rsName + " PR.YEAR " + viewOrder + ",PR." + frequency;
            }
        } else {
            rsName = rsRequired ? " ,NMDPM.RS_NAME " : "";
            if (frequency.equals("YEAR")) {
                query = query + "ORDER BY PR.YEAR" + viewOrder + rsName;
            } else {
                query = query + "ORDER BY PR.YEAR" + viewOrder + ",PR." + frequency + viewOrder + rsName;
            }
        }

        List resultList = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, proSelDTO.getSessionDTO().getCurrentTableNames()));
        return resultList;
    }

    private String getFrequencyCondition(String frequency, boolean isPivotHier) {
        String query = isPivotHier ? " 0 AS YEAR , 0 AS " + frequency + "," : " PR.YEAR,PR." + frequency + ",";
        return query;
    }

    private String getPeriodCondition(boolean isPivotHier, String start, String end) {
        String query = isPivotHier ? " AND cast(PR.YEAR AS VARCHAR(4)) + RIGHT('0' + CAST(PR.MONTH AS VARCHAR), 2) >= '"+start+"' AND cast(PR.YEAR AS VARCHAR(4)) + RIGHT('0' + CAST(PR.MONTH AS VARCHAR), 2) <= '"+end+"'"
                : StringUtils.EMPTY;
        return query;
    }

    private String getGroupByCondition(String frequency, boolean isPivotHier, boolean rsRequired) {
        String rsCond = rsRequired ? ",NMDPM.RS_NAME " : "";
        String query = isPivotHier ? " GROUP BY NMDPM.RS_NAME" : " GROUP BY PR.YEAR,PR." + frequency + rsCond;
        return query;
    }

    /**
     *
     * @param customId
     * @param levelNo
     * @return
     * @throws PortalException
     * @throws Exception
     */
    public String getCustomViewHierarchyIndicator(int customId, int levelNo) {

        String hierarchyIndicator = StringUtils.EMPTY;
        String hierarchyIndicatorQuery = "select HIERARCHY_INDICATOR from dbo.CUSTOM_VIEW_DETAILS where CUSTOM_VIEW_MASTER_SID=" + customId + " and LEVEL_NO=" + levelNo;
        try {
            List<Object> list = (List<Object>) HelperTableLocalServiceUtil.executeSelectQuery(hierarchyIndicatorQuery);
            if (list != null && !list.isEmpty()) {
                Object ob = list.get(0);
                hierarchyIndicator = String.valueOf(ob);
            } else {
                hierarchyIndicator = StringUtils.EMPTY;
            }
            return hierarchyIndicator;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            return hierarchyIndicator;
        }
    }

    List<DiscountProjectionResultsDTO> getCustomizedTotal(List result, ProjectionSelectionDTO proSelDTO, boolean rsNeeded, DiscountProjectionResultsDTO dto) {

        List finalList = new ArrayList();
        try {
            List<String> periodList = new ArrayList<>(proSelDTO.getPeriodList());
            Set<String> removeList = new HashSet<>();
            String oldDiscount = StringUtils.EMPTY;
            String newDiscount;
            String frequency = proSelDTO.getFrequency();
            String column;
            if (result != null && !result.isEmpty()) {

                for (int i = 0; i < result.size(); i++) {
                    Object[] obj = (Object[]) result.get(i);
                    column = getFrequency(frequency, obj);
                    if (periodList.contains(column) || removeList.contains(column)) {
                        if (rsNeeded) {
                            newDiscount = String.valueOf(obj[NumericConstants.ELEVEN]);
                            if (!oldDiscount.equals(newDiscount)) {
                                dto = new DiscountProjectionResultsDTO();
                                finalList.add(dto);
                                dto.setGroup(newDiscount);
                                dto.setLevelNo(0);
                                dto.setHierarchylevelId(ZERO);
                                dto.setIsParent(Constant.STRING_ONE);
                                dto.setHierarchyNo(Constant.STRING_ONE);
                                dto.setParent(0);
                                dto.setTotal(1);
                            }
                            setActualProjectionValue(dto, obj, column);
                            oldDiscount = newDiscount;
                        } else {
                            setActualProjectionValue(dto, obj, column);
                        }
                        periodList.remove(column);
                        removeList.add(column);
                    }

                    if (i == result.size() - 1 && !rsNeeded) {
                        dto = putHyphenForDTO(periodList, dto);
                        finalList.add(dto);
                    }
                }

            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return finalList;
    }

    private void setActualProjectionValue(DiscountProjectionResultsDTO dto, Object[] obj, String column) {
        if (String.valueOf(obj[NumericConstants.TEN]).equals(ACTUALS.getConstant())) {
            //Actual
            dto.addStringProperties(column + ACTUALSRATE, UNITVOLUME.format(getvalue(obj[NumericConstants.EIGHT])) + PERCENTAGE);
            dto.addStringProperties(column + ACTUALRPU, DOLLAR_SYMBOL + DOLLAR_RPU.format(getvalue(obj[NumericConstants.NINE])));
            dto.addStringProperties(column + ACTUALSAMOUNT, DOLLAR_SYMBOL + DOLLAR.format(getvalue(obj[NumericConstants.THREE])));
        } else if (String.valueOf(obj[NumericConstants.TEN]).equals(PROJECTIONS.getConstant())) {
            //Projection
            dto.addStringProperties(column + PROJECTIONSRATE, UNITVOLUME.format(getvalue(obj[NumericConstants.SIX])) + PERCENTAGE);
            dto.addStringProperties(column + PROJECTEDRPU, DOLLAR_SYMBOL + DOLLAR_RPU.format(getvalue(obj[NumericConstants.SEVEN])));
            dto.addStringProperties(column + PROJECTIONSAMOUNT, DOLLAR_SYMBOL + DOLLAR.format(getvalue(obj[NumericConstants.FIVE])));
        }
    }

    private Object getvalue(Object obj) {
        if (obj == null || StringUtils.isBlank(String.valueOf(obj))) {
            return 0;
        } else {
            return obj;
        }
    }

    List<DiscountProjectionResultsDTO> getCustomizedTotalPivot(List result, ProjectionSelectionDTO proSelDTO, boolean isHierachy, DiscountProjectionResultsDTO hierarchyDTO) {
        try {
            List<DiscountProjectionResultsDTO> finalList = new ArrayList<>();
            List<String> periodList = new ArrayList<>(proSelDTO.getPeriodList());
            Map<String, DiscountProjectionResultsDTO> map = new LinkedHashMap<>();
            String frequency = proSelDTO.getFrequency();
            String column;
            String visibleColumn;
            DiscountProjectionResultsDTO dto = null;
            if (result != null && !result.isEmpty()) {
                for (int i = 0; i < result.size(); i++) {
                    Object[] obj = (Object[]) result.get(i);
                    if (!isHierachy) {
                        column = getFrequency(frequency, obj);
                        if (periodList.contains(column)) {
                            visibleColumn = String.valueOf(obj[NumericConstants.ELEVEN]).replace(" ", StringUtils.EMPTY);
                            dto = map.get(column);
                            if (dto == null) {
                                dto = new DiscountProjectionResultsDTO();
                                dto.setGroup(proSelDTO.getPeriodListMap().get(column));
                                dto.setIsParent(ZERO);
                                map.put(column, dto);
                            }
                            setActualProjectionValue(dto, obj, visibleColumn);
                        }
                    } else {
                        dto = hierarchyDTO;
                        visibleColumn = String.valueOf(obj[NumericConstants.ELEVEN]).replace(" ", StringUtils.EMPTY);
                        map.put(dto.getGroup(), dto);
                        setActualProjectionValue(dto, obj, visibleColumn);
                    }
                }
            }
            finalList.addAll(map.values());
            return finalList;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            return Collections.emptyList();
        }
    }

    private String getFrequency(String frequency, Object[] obj) {
        String freq = StringUtils.EMPTY;
        if (ANNUALLY.getConstant().equals(frequency)) {
            freq = String.valueOf(obj[0]);
        } else if (QUARTERLY.getConstant().equals(frequency)) {
            freq = Constant.Q_SMALL + String.valueOf(obj[1]) + String.valueOf(obj[0]);
        } else if (SEMI_ANNUALLY.getConstant().equals(frequency)) {
            freq = Constant.S_SMALL + String.valueOf(obj[1]) + String.valueOf(obj[0]);
        } else if (MONTHLY.getConstant().equals(frequency)) {
            String monthName = getMonthForInt(Integer.parseInt(String.valueOf(obj[1])) - 1);
            freq = monthName.toLowerCase() + String.valueOf(obj[0]);
        }
        return freq;
    }
}