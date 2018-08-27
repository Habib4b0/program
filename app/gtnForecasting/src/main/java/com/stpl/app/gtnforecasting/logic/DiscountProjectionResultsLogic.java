/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.logic;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionList;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gtnforecasting.dao.CommonDAO;
import com.stpl.app.gtnforecasting.dao.impl.CommonDAOImpl;
import com.stpl.app.gtnforecasting.dto.DiscountProjectionResultsDTO;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.service.finderImpl.NmDiscountImpl;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.gtnforecasting.utils.HeaderUtils.getMonthForInt;
import com.stpl.app.service.ProjectionDetailsLocalServiceUtil;
import static com.stpl.app.utils.Constants.FrequencyConstants.MONTHLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.QUARTERLY;
import static com.stpl.app.utils.Constants.LabelConstants.ACTUALS;
import static com.stpl.app.utils.Constants.LabelConstants.BOTH;
import static com.stpl.app.utils.Constants.LabelConstants.DASH;
import static com.stpl.app.utils.Constants.LabelConstants.PERIOD;
import static com.stpl.app.utils.Constants.LabelConstants.PROJECTIONS;
import com.stpl.ifs.ui.util.converters.DataTypeConverter;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.util.NumericConstants;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Nandhakumar
 */
public class DiscountProjectionResultsLogic {

    private static final DecimalFormat DOLLAR = new DecimalFormat("#,##0");
    private static final DecimalFormat UNITVOLUME = new DecimalFormat("#,##0.0");
    private static final DecimalFormat CUR_ZERO = new DecimalFormat("$#,##0");
    public static final Logger LOGGER = LoggerFactory.getLogger(DiscountProjectionResultsLogic.class);
    private static final String ACTUALSRATE = "ActualsRate";
    private static final String ACTUALSAMOUNT = "ActualsAmount";
    private static final String PROJECTIONSRATE = "ProjectionsRate";
    private static final String PROJECTIONSAMOUNT = "ProjectionsAmount";
    private static final String NULL = "null";
    private static final String HYPHEN = "-";
    private static final String PERCENTAGE = "%";
    private static final String DOLLAR_SYMBOL = "$";
    private static final String ZERO_SYMBOL = "0";
    private final Map<String, String> monthMap = new HashMap<>();
    private final Map<String, String> valueMap = new HashMap<>();
    private static final String[] ALL_MONTH = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

    public DiscountProjectionResultsLogic() {
        // DiscountProjectionResultsLogic
    }
    private final List<Integer> startAndEndPeriods = new ArrayList<>();
    private static final CommonDAO commonDao = new CommonDAOImpl();
    public static final String AND_SMALL = " and ";
    /**
     * Method is Used To Load Projection Total of period Mode
     *
     * @param projectionId
     * @param selection
     * @return
     */
    public List<DiscountProjectionResultsDTO> getPeriodProjectionTotal(int projectionId, List<Integer> startAndEndPeriods, ProjectionSelectionDTO projSelDTO) {
        try {
            List<DiscountProjectionResultsDTO> discountProjList = new ArrayList<>();
              boolean viewFlag=Constant.VIEW.equals(projSelDTO.getSessionDTO().getAction());
            String selectedView = projSelDTO.getView();
            String userId = String.valueOf(projSelDTO.getUserId());
            String sessionId = String.valueOf(projSelDTO.getSessionId());
            int user = Integer.parseInt(userId);
            int session = Integer.parseInt(sessionId);
            List ccpId = null;
            if (selectedView.equals(Constant.CUSTOMER_SMALL)) {
                ccpId = new NmDiscountImpl().getCCPDetailsID(projectionId, PERCENTAGE, Constant.STRING_ONE);
            }
            if (selectedView.equals(Constant.PRODUCT_LABEL)) {
                ccpId = new NmDiscountImpl().getCCPDetailsIDForProductHierarchy(projectionId, PERCENTAGE, Constant.STRING_ONE);
            }
            if (selectedView.equals(Constant.CUSTOM_LABEL)) {
                ccpId = new NmDiscountImpl().getCCPDetailsID(projectionId, PERCENTAGE, Constant.STRING_ONE);
            }
            if (ccpId != null && !ccpId.isEmpty()) {
                List<Integer> proDetailsSid;
                final DynamicQuery projectiondetailsDynamicQuery = ProjectionDetailsLocalServiceUtil.dynamicQuery();
                projectiondetailsDynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_MASTER_SID, projectionId));
                projectiondetailsDynamicQuery.add(RestrictionsFactoryUtil.in(Constant.CCP_DETAILS_SID, ccpId));
                final ProjectionList projList = ProjectionFactoryUtil.projectionList();
                projList.add(ProjectionFactoryUtil.property(Constant.PROJECTION_DETAILS_SID));
                projectiondetailsDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projList));
                proDetailsSid = ProjectionDetailsLocalServiceUtil.dynamicQuery(projectiondetailsDynamicQuery);
                if (proDetailsSid != null && !proDetailsSid.isEmpty()) {
                  
                    String freq = projSelDTO.getFrequency();
                    String order = projSelDTO.getProjectionOrder();
                    String projection = projSelDTO.getView();
                    List<String> discountList;
                    discountList = projSelDTO.getDiscountNameList();
                    String discountString = getDiscountName(discountList);
                    List list = new NmDiscountImpl().getDiscountProjectionResults(proDetailsSid, freq, discountString, projection, Constant.PARENT, order, startAndEndPeriods, user, session,viewFlag);
                    DiscountProjectionResultsDTO discountDto = null;
                    if (list != null && !list.isEmpty()) {
                        if (freq.equals(QUARTERLY.getConstant())) {
                            discountDto = new DiscountProjectionResultsDTO();
                            discountDto.setGroup(Constant.PROJECTION_TOTAL);
                            discountDto.setLevelNo(0);
                            discountDto.setHierarchylevelId(ZERO_SYMBOL);
                            discountDto.setIsParent(Constant.STRING_ONE);
                            discountDto.setHierarchyNo(Constant.STRING_ONE);
                            discountDto.setParent(0);
                            discountDto.setTotal(1);
                            discountDto = getValueForDTO(projSelDTO, list, discountDto, Constant.Q_SMALL);
                            discountProjList.add(discountDto);
                            return discountProjList;
                        }
                        if (freq.equals(Constant.SEMI_ANNUALLY)) {
                            discountDto = new DiscountProjectionResultsDTO();
                            discountDto.setGroup(Constant.PROJECTION_TOTAL);
                            discountDto.setHierarchySid(ZERO_SYMBOL);
                            discountDto.setLevelNo(0);
                            discountDto.setHierarchylevelId(ZERO_SYMBOL);
                            discountDto.setIsParent(Constant.STRING_ONE);
                            discountDto.setParent(0);
                            discountDto.setHierarchyNo(Constant.STRING_ONE);
                            discountDto.setTotal(1);
                            discountDto = getValueForDTO(projSelDTO, list, discountDto, Constant.S_SMALL);
                            discountProjList.add(discountDto);
                            return discountProjList;
                        }
                        if (freq.equals(Constant.ANNUALLY)) {

                            discountDto = new DiscountProjectionResultsDTO();
                            discountDto.setGroup(Constant.PROJECTION_TOTAL);
                            discountDto.setHierarchySid(ZERO_SYMBOL);
                            discountDto.setLevelNo(0);
                            discountDto.setHierarchylevelId(ZERO_SYMBOL);
                            discountDto.setIsParent(Constant.STRING_ONE);
                            discountDto.setParent(0);
                            discountDto.setHierarchyNo(Constant.STRING_ONE);
                            discountDto.setTotal(1);
                            discountDto = getValueForYearDTO(projSelDTO, list, discountDto);
                            discountProjList.add(discountDto);
                            return discountProjList;
                        }
                        if (freq.equals(MONTHLY.getConstant())) {

                            discountDto = new DiscountProjectionResultsDTO();
                            discountDto.setGroup(Constant.PROJECTION_TOTAL);
                            discountDto.setHierarchySid(ZERO_SYMBOL);
                            discountDto.setLevelNo(0);
                            discountDto.setHierarchylevelId(ZERO_SYMBOL);
                            discountDto.setIsParent(Constant.STRING_ONE);
                            discountDto.setParent(0);
                            discountDto.setHierarchyNo(Constant.STRING_ONE);
                            discountDto.setTotal(1);
                            discountDto = getValueForMonthDTO(projSelDTO, list, discountDto);
                            discountProjList.add(discountDto);
                            return discountProjList;
                        }
                    }
                }
            }
            return discountProjList;
        } catch (SystemException | NumberFormatException e) {
            LoggerFactory.getLogger(DiscountProjectionResultsLogic.class.getName()).error( StringUtils.EMPTY, e);
            return Collections.emptyList();
        }
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
    public List<DiscountProjectionResultsDTO> getPeriodHierarchy(ProjectionSelectionDTO proSelDTO, List<Integer> startAndEndPeriods, List<String> discountList) throws SystemException {
        List<DiscountProjectionResultsDTO> discountProjList = new ArrayList<>();
        
        try {
            String level = String.valueOf(proSelDTO.getTreeLevelNo());
            String hierarchyNo = String.valueOf(proSelDTO.getHierarchyNo());
            int projectionMasterId = proSelDTO.getProjectionId();
            String hierarchy = proSelDTO.getView();
            List ccpid = null;
            if (hierarchy.equals(Constant.CUSTOMER_SMALL)) {
                ccpid = new NmDiscountImpl().getCCPDetailsID(projectionMasterId, hierarchyNo, level);
            }
            if (hierarchy.equals(Constant.PRODUCT_LABEL)) {
                ccpid = new NmDiscountImpl().getCCPDetailsIDForProductHierarchy(projectionMasterId, hierarchyNo, level);
            }
            if (hierarchy.equals(Constant.CUSTOM_LABEL)) {
                proSelDTO.setIsCustomHierarchy(true);
                String customQuery = CommonLogic.getCustomCCPQuery(proSelDTO);
                List<Object> list = (List<Object>) executeSelectQuery(customQuery, null, null);
                if (list != null && !list.isEmpty()) {
                    ccpid = new ArrayList();
                    for (int i = 0; i < list.size(); i++) {
                        Object[] obj = (Object[]) list.get(i);
                        String id = String.valueOf(obj[1]);
                        if (!id.equals(Constant.NULL)) {
                            int ccp = Integer.parseInt(id);
                            ccpid.add(ccp);
                        }
                    }
                }
            }
            if (proSelDTO.getGroup().startsWith(Constant.DISCOUNT)) {
                ccpid = null;
            }
            if (ccpid != null && !ccpid.isEmpty()) {
                final DynamicQuery projectionDetailsDynamicQuery = ProjectionDetailsLocalServiceUtil.dynamicQuery();
                projectionDetailsDynamicQuery.add(RestrictionsFactoryUtil.in(Constant.CCP_DETAILS_SID, ccpid));
                projectionDetailsDynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_MASTER_SID, projectionMasterId));
                ProjectionList projList = ProjectionFactoryUtil.projectionList();
                projList.add(ProjectionFactoryUtil.property(Constant.PROJECTION_DETAILS_SID));
                projectionDetailsDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projList));
                List<Integer> projectionDetailsId = ProjectionDetailsLocalServiceUtil.dynamicQuery(projectionDetailsDynamicQuery);
                if (projectionDetailsId != null && !projectionDetailsId.isEmpty()) {
                    String userId = String.valueOf(proSelDTO.getUserId());
                    String sessionId = String.valueOf(proSelDTO.getSessionId());
                    int user = Integer.parseInt(userId);
                    int session = Integer.parseInt(sessionId);
                    String freq = String.valueOf(proSelDTO.getFrequency());
                    String discountString = CommonUtils.CollectionToString(discountList, true);
                    if (discountString.equals(StringUtils.EMPTY)) {
                        discountString = ZERO_SYMBOL;
                    }
                    List list = new NmDiscountImpl().getAllPesriodDiscount(projectionDetailsId, freq, discountString, StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, startAndEndPeriods, user, session);
                    if (list != null && !list.isEmpty()) {
                        if (freq.equals(QUARTERLY.getConstant())) {
                            discountProjList = getDiscountListDto(proSelDTO, list, discountProjList, Constant.Q_SMALL, discountList);
                        }
                        if (freq.equals(Constant.SEMI_ANNUALLY)) {
                            discountProjList = getDiscountListDto(proSelDTO, list, discountProjList, Constant.S_SMALL, discountList);
                        }
                        if (freq.equals(Constant.ANNUALLY)) {
                            discountProjList = getDiscountListYearDto(proSelDTO, list, discountProjList, discountList);
                        }
                        if (freq.equals(MONTHLY.getConstant())) {
                            discountProjList = getDiscountListMonthDto(proSelDTO, list, discountProjList, discountList);
                        }
                    }
                }
            }
        } catch (SystemException | NumberFormatException e) {
            LoggerFactory.getLogger(DiscountProjectionResultsLogic.class.getName()).error( StringUtils.EMPTY, e);
        }
        return discountProjList;
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
    public List<DiscountProjectionResultsDTO> getPeriodProjectionTotalDiscount(int projectionId,List<Integer> startAndEndPeriods, ProjectionSelectionDTO projSelDTO, List<String> discountList) {
        List<DiscountProjectionResultsDTO> discountProjList = new ArrayList<>();
        try {
            List ccpId = null;
            String selectedView = projSelDTO.getView();
            if (selectedView.equals(Constant.CUSTOMER_SMALL)) {
                ccpId = new NmDiscountImpl().getCCPDetailsID(projectionId, PERCENTAGE, Constant.STRING_ONE);
            } else if (selectedView.equals(Constant.PRODUCT_LABEL)) {
                ccpId = new NmDiscountImpl().getCCPDetailsIDForProductHierarchy(projectionId, PERCENTAGE, Constant.STRING_ONE);
            } else if (selectedView.equals(Constant.CUSTOM_LABEL)) {
                ccpId = new NmDiscountImpl().getCCPDetailsID(projectionId, PERCENTAGE, Constant.STRING_ONE);
            }
            if (ccpId != null && !ccpId.isEmpty()) {
                List<Integer> proDetailsSid;
                final DynamicQuery projectiondetailsDynamicQuery = ProjectionDetailsLocalServiceUtil.dynamicQuery();
                projectiondetailsDynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_MASTER_SID, projectionId));
                projectiondetailsDynamicQuery.add(RestrictionsFactoryUtil.in(Constant.CCP_DETAILS_SID, ccpId));
                final ProjectionList projList = ProjectionFactoryUtil.projectionList();
                projList.add(ProjectionFactoryUtil.property(Constant.PROJECTION_DETAILS_SID));
                projectiondetailsDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projList));
                proDetailsSid = ProjectionDetailsLocalServiceUtil.dynamicQuery(projectiondetailsDynamicQuery);
                if (proDetailsSid != null && !proDetailsSid.isEmpty()) {
                    String freq = projSelDTO.getFrequency();
                    String order = projSelDTO.getProjectionOrder();
                    String userId = String.valueOf(projSelDTO.getUserId());
                    String sessionId = String.valueOf(projSelDTO.getSessionId());
                    int user = Integer.parseInt(userId);
                    int session = Integer.parseInt(sessionId);
                    String discountString = CommonUtils.CollectionToString(discountList, true);

                    if (discountString.equals(StringUtils.EMPTY)) {
                        discountString = ZERO_SYMBOL;
                    }
                    List list = new NmDiscountImpl().getAllPesriodDiscount(proDetailsSid, freq, discountString, StringUtils.EMPTY, StringUtils.EMPTY, order, startAndEndPeriods, user, session);
                    if (list != null && !list.isEmpty()) {
                        if (freq.equals(QUARTERLY.getConstant())) {
                            discountProjList = getDiscountListDto(projSelDTO, list, discountProjList, Constant.Q_SMALL, discountList);
                            return discountProjList;
                        }
                        if (freq.equals(Constant.SEMI_ANNUALLY)) {
                            discountProjList = getDiscountListDto(projSelDTO, list, discountProjList, Constant.S_SMALL, discountList);
                            return discountProjList;
                        }
                        if (freq.equals(Constant.ANNUALLY)) {
                            discountProjList = getDiscountListYearDto(projSelDTO, list, discountProjList, discountList);
                            return discountProjList;
                        }

                        if (freq.equals(MONTHLY.getConstant())) {
                            discountProjList = getDiscountListMonthDto(projSelDTO, list, discountProjList, discountList);
                            return discountProjList;
                        }
                    }
                }
            }
        } catch (SystemException ex) {
            LoggerFactory.getLogger(DiscountProjectionResultsLogic.class.getName()).error( StringUtils.EMPTY, ex);
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
    public List<DiscountProjectionResultsDTO> getPivotProjectionTotal(int projectionId, ProjectionSelectionDTO projSelDTO, List<Integer> startAndEndPeriods) {
        List<DiscountProjectionResultsDTO> discountProjList = new ArrayList<>();
        try {
            // Used to Load Discount
            List<Integer> proDetailsSid;
            String selectedView = String.valueOf(projSelDTO.getView());
            List ccpId = null;
            if (selectedView.equals(Constant.CUSTOMER_SMALL)) {
                ccpId = new NmDiscountImpl().getCCPDetailsID(projectionId, PERCENTAGE, Constant.STRING_ONE);
            }
            if (selectedView.equals(Constant.PRODUCT_LABEL)) {
                ccpId = new NmDiscountImpl().getCCPDetailsIDForProductHierarchy(projectionId, PERCENTAGE, Constant.STRING_ONE);
            }
            if (selectedView.equals(Constant.CUSTOM_LABEL)) {
                ccpId = new NmDiscountImpl().getCCPDetailsID(projectionId, PERCENTAGE, Constant.STRING_ONE);
            }
            if (ccpId != null && !ccpId.isEmpty()) {
                final DynamicQuery projectiondetailsDynamicQuery = ProjectionDetailsLocalServiceUtil.dynamicQuery();
                projectiondetailsDynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_MASTER_SID, projectionId));
                projectiondetailsDynamicQuery.add(RestrictionsFactoryUtil.in(Constant.CCP_DETAILS_SID, ccpId));
                final ProjectionList projList = ProjectionFactoryUtil.projectionList();
                projList.add(ProjectionFactoryUtil.property(Constant.PROJECTION_DETAILS_SID));
                projectiondetailsDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projList));
                proDetailsSid = ProjectionDetailsLocalServiceUtil.dynamicQuery(projectiondetailsDynamicQuery);
                if (proDetailsSid != null && !proDetailsSid.isEmpty()) {
                    String freq = String.valueOf(projSelDTO.getFrequency());
                    String userId = String.valueOf(projSelDTO.getUserId());
                    String sessionId = String.valueOf(projSelDTO.getSessionId());
                    int user = Integer.parseInt(userId);
                    int session = Integer.parseInt(sessionId);
                    List<String> discountList;
                    discountList = projSelDTO.getDiscountNameList();
                    String discountString = getDiscountName(discountList);
                    List list = new NmDiscountImpl().getTotalDiscountNumber(proDetailsSid, freq, discountString, startAndEndPeriods, user, session,null);
                    if (list != null && !list.isEmpty()) {
                        DiscountProjectionResultsDTO discountDto = new DiscountProjectionResultsDTO();
                        discountDto.setGroup(Constant.PROJECTION_TOTAL);
                        discountDto.setHierarchySid(ZERO_SYMBOL);
                        discountDto.setLevelNo(0);
                        discountDto.setHierarchylevelId(ZERO_SYMBOL);
                        discountDto.setIsParent(Constant.STRING_ONE);
                        discountDto.setHierarchyNo(Constant.STRING_ONE);
                        discountDto.setTotal(1);
                        discountDto.setParent(0);
                        double actualSales = 0;
                        double actualAmount = 0;
                        double projectedSales = 0;
                        double projectedAmount = 0;
                        String commonColumn;
                        Object[] object = (Object[]) list.get(0);
                        String currentDiscount = String.valueOf(object[NumericConstants.EIGHT]);
                        commonColumn = currentDiscount.replace(" ", StringUtils.EMPTY);
                        if (object[NumericConstants.TWO] != null) {
                            Double aPivotSales = DataTypeConverter.convertObjectToDouble(object[NumericConstants.TWO]);
                            actualSales = actualSales + aPivotSales;
                        }
                        if (object[NumericConstants.THREE] != null) {
                            Double aPivotAmount = DataTypeConverter.convertObjectToDouble(object[NumericConstants.THREE]);
                            actualAmount = actualAmount + aPivotAmount;
                        }
                        if (object[NumericConstants.FOUR] != null) {
                            Double pPivotSales = DataTypeConverter.convertObjectToDouble(object[NumericConstants.FOUR]);
                            projectedSales = projectedSales + pPivotSales;
                        }
                        if (object[NumericConstants.FIVE] != null) {
                            Double pPivotAmount = DataTypeConverter.convertObjectToDouble(object[NumericConstants.FIVE]);
                            projectedAmount = projectedAmount + pPivotAmount;
                        }

                        if (list.size() == 1) {
                            Double aPivotrate = actualAmount / actualSales;
                            if (aPivotrate.isNaN()) {
                                aPivotrate = 0.0;
                            }
                            aPivotrate = aPivotrate * NumericConstants.HUNDRED;
                            String actPivotRate = String.valueOf(aPivotrate);
                            discountDto.addStringProperties(commonColumn + ACTUALSRATE, actPivotRate != null && !NULL.equals(String.valueOf(actPivotRate)) && !StringUtils.EMPTY.equals(String.valueOf(actPivotRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actPivotRate)))).concat(PERCENTAGE) : HYPHEN);
                            Double actualPivotAmt = aPivotrate * actualSales;
                            actualPivotAmt = actualPivotAmt / NumericConstants.HUNDRED;
                            if (actualPivotAmt.isNaN()) {
                                actualPivotAmt = 0.0;
                            }
                            String actPivotAmt = String.valueOf(actualPivotAmt);
                            discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actPivotAmt != null && !NULL.equals(String.valueOf(actPivotAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actPivotAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actPivotAmt)))) : HYPHEN);
                            Double pPivotrate = projectedAmount / projectedSales;
                            if (pPivotrate.isNaN() || pPivotrate.isInfinite()) {
                                pPivotrate = 0.0;
                            }
                            pPivotrate = pPivotrate * NumericConstants.HUNDRED;
                            String proPivotRate = String.valueOf(pPivotrate);
                            discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proPivotRate != null && !NULL.equals(String.valueOf(proPivotRate)) && !StringUtils.EMPTY.equals(String.valueOf(proPivotRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proPivotRate)))).concat(PERCENTAGE) : HYPHEN);
                            Double projectedPivotAmtAmt = pPivotrate * projectedSales;
                            projectedPivotAmtAmt = projectedPivotAmtAmt / NumericConstants.HUNDRED;
                            if (projectedPivotAmtAmt.isNaN()) {
                                projectedPivotAmtAmt = 0.0;
                            }
                            String proPivotAmount = String.valueOf(projectedPivotAmtAmt);
                            discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proPivotAmount != null && !NULL.equals(String.valueOf(proPivotAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proPivotAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proPivotAmount)))) : HYPHEN);
                        } else {
                            for (int i = 1; i < list.size(); i++) {
                                Object[] obj = (Object[]) list.get(i);
                                String selectedPivotDiscount = String.valueOf(obj[NumericConstants.EIGHT]);
                                if (currentDiscount.equals(selectedPivotDiscount)) {
                                    if (obj[NumericConstants.TWO] != null) {
                                        Double aSalesPivot = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.TWO]);
                                        actualSales = actualSales + aSalesPivot;
                                    }
                                    if (obj[NumericConstants.THREE] != null) {
                                        Double aAmountPivot = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.THREE]);
                                        actualAmount = actualAmount + aAmountPivot;
                                    }
                                    if (obj[NumericConstants.FOUR] != null) {
                                        Double pSalesPivot = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FOUR]);
                                        projectedSales = projectedSales + pSalesPivot;
                                    }
                                    if (obj[NumericConstants.FIVE] != null) {
                                        Double pAmountPivot = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FIVE]);
                                        projectedAmount = projectedAmount + pAmountPivot;
                                    }
                                } else {
                                    Double aratePivot = actualAmount / actualSales;
                                    if (aratePivot.isNaN()) {
                                        aratePivot = 0.0;
                                    }
                                    aratePivot = aratePivot * NumericConstants.HUNDRED;
                                    String actRate = String.valueOf(aratePivot);

                                    discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                    Double actualAmtPivot = aratePivot * actualSales;
                                    actualAmtPivot = actualAmtPivot / NumericConstants.HUNDRED;
                                    if (actualAmtPivot.isNaN()) {
                                        actualAmtPivot = 0.0;
                                    }
                                    String actAmtPivot = String.valueOf(actualAmtPivot);
                                    discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmtPivot != null && !NULL.equals(String.valueOf(actAmtPivot)) && !StringUtils.EMPTY.equals(String.valueOf(actAmtPivot)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmtPivot)))) : HYPHEN);
                                    Double pratePivot = projectedAmount / projectedSales;
                                    if (pratePivot.isNaN() || pratePivot.isInfinite()) {
                                        pratePivot = 0.0;
                                    }
                                    pratePivot = pratePivot * NumericConstants.HUNDRED;
                                    String proRate = String.valueOf(pratePivot);
                                    discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                    Double projectedAmtAmt = pratePivot * projectedSales;
                                    projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                                    if (projectedAmtAmt.isNaN()) {
                                        projectedAmtAmt = 0.0;
                                    }
                                    String proAmountPivot = String.valueOf(projectedAmtAmt);
                                    discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmountPivot != null && !NULL.equals(String.valueOf(proAmountPivot)) && !StringUtils.EMPTY.equals(String.valueOf(proAmountPivot)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmountPivot)))) : HYPHEN);
                                    actualSales = 0;
                                    actualAmount = 0;
                                    projectedSales = 0;
                                    projectedAmount = 0;
                                    currentDiscount = selectedPivotDiscount;
                                    commonColumn = currentDiscount.replace(" ", StringUtils.EMPTY);
                                    if (obj[NumericConstants.TWO] != null) {
                                        Double aSalesDiscount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.TWO]);
                                        actualSales = actualSales + aSalesDiscount;
                                    }
                                    if (obj[NumericConstants.THREE] != null) {
                                        Double aAmountDiscount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.THREE]);
                                        actualAmount = actualAmount + aAmountDiscount;
                                    }

                                    if (obj[NumericConstants.FOUR] != null) {
                                        Double pSalesDiscount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FOUR]);
                                        projectedSales = projectedSales + pSalesDiscount;
                                    }
                                    if (obj[NumericConstants.FIVE] != null) {
                                        Double pAmountDiscount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FIVE]);
                                        projectedAmount = projectedAmount + pAmountDiscount;
                                    }
                                }
                                if (i == list.size() - 1) {
                                    Double arateDiscount = actualAmount / actualSales;
                                    if (arateDiscount.isNaN()) {
                                        arateDiscount = 0.0;
                                    }
                                    arateDiscount = arateDiscount * NumericConstants.HUNDRED;
                                    String actRate = String.valueOf(arateDiscount);
                                    discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                    Double actualAmtDiscount = arateDiscount * actualSales;
                                    actualAmtDiscount = actualAmtDiscount / NumericConstants.HUNDRED;
                                    if (actualAmtDiscount.isNaN()) {
                                        actualAmtDiscount = 0.0;
                                    }
                                    String actAmtDiscount = String.valueOf(actualAmtDiscount);
                                    discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmtDiscount != null && !NULL.equals(String.valueOf(actAmtDiscount)) && !StringUtils.EMPTY.equals(String.valueOf(actAmtDiscount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmtDiscount)))) : HYPHEN);
                                    Double prateDiscount = projectedAmount / projectedSales;
                                    if (prateDiscount.isNaN() || prateDiscount.isInfinite()) {
                                        prateDiscount = 0.0;
                                    }
                                    prateDiscount = prateDiscount * NumericConstants.HUNDRED;
                                    String proRate = String.valueOf(prateDiscount);
                                    discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                    Double projectedAmtAmtDiscount = prateDiscount * projectedSales;
                                    projectedAmtAmtDiscount = projectedAmtAmtDiscount / NumericConstants.HUNDRED;
                                    if (projectedAmtAmtDiscount.isNaN()) {
                                        projectedAmtAmtDiscount = 0.0;
                                    }
                                    String proAmount = String.valueOf(projectedAmtAmtDiscount);
                                    discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                }
                            }
                        }
                        discountProjList.add(discountDto);
                    }
                    return discountProjList;
                }
            }
        } catch (SystemException ex) {
            LoggerFactory.getLogger(DiscountProjectionResultsLogic.class.getName()).error( StringUtils.EMPTY, ex);
        }
        return Collections.emptyList();
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
    public List<DiscountProjectionResultsDTO> getPivotProjectionTotalDiscount(ProjectionSelectionDTO projSelDTO, List<Integer> startAndEndPeriods) throws SystemException {
        List<DiscountProjectionResultsDTO> discountProjList = new ArrayList<>();
        try {
            int projectionId = projSelDTO.getProjectionId();
            List ccpId = new NmDiscountImpl().getCCPDetailsID(projectionId, PERCENTAGE, Constant.STRING_ONE);
            if (ccpId != null && !ccpId.isEmpty()) {
                List<Integer> proDetailsSid;
                final DynamicQuery projectiondetailsDynamicQuery = ProjectionDetailsLocalServiceUtil.dynamicQuery();
                projectiondetailsDynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_MASTER_SID, projectionId));
                projectiondetailsDynamicQuery.add(RestrictionsFactoryUtil.in(Constant.CCP_DETAILS_SID, ccpId));
                final ProjectionList projList = ProjectionFactoryUtil.projectionList();
                projList.add(ProjectionFactoryUtil.property(Constant.PROJECTION_DETAILS_SID));
                projectiondetailsDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projList));
                proDetailsSid = ProjectionDetailsLocalServiceUtil.dynamicQuery(projectiondetailsDynamicQuery);
                if (proDetailsSid != null && !proDetailsSid.isEmpty()) {
                    String userId = String.valueOf(projSelDTO.getUserId());
                    String sessionId = String.valueOf(projSelDTO.getSessionId());
                    int user = Integer.parseInt(userId);
                    int session = Integer.parseInt(sessionId);
                    String freq = String.valueOf(projSelDTO.getFrequency());
                    List<String> discountList;
                    discountList = projSelDTO.getDiscountNameList();
                    String discountString = getDiscountName(discountList);
                    List list = new NmDiscountImpl().getSubDiscount(proDetailsSid, freq, discountString, startAndEndPeriods, user, session);
                    if (list != null && !list.isEmpty()) {
                        if (freq.equals(Constant.ANNUALLY)) {
                            double actualSales = 0;
                            double actualAmount = 0;
                            double projectedSales = 0;
                            double projectedAmount = 0;
                            String commonColumn;
                            List<String> periodList = new ArrayList<>(projSelDTO.getPeriodList());
                            DiscountProjectionResultsDTO discountAnnualDto = new DiscountProjectionResultsDTO();
                            Object[] object = (Object[]) list.get(0);
                            String currentDiscount = String.valueOf(object[NumericConstants.EIGHT]);
                            int currentYear = (Integer) object[0];
                            commonColumn = currentDiscount.replace(" ", StringUtils.EMPTY);
                            discountAnnualDto.setGroup(String.valueOf(currentYear));
                            discountAnnualDto.setIsParent(ZERO_SYMBOL);
                            if (object[NumericConstants.TWO] != null) {
                                Double aTotalDiscountSales = DataTypeConverter.convertObjectToDouble(object[NumericConstants.TWO]);
                                actualSales = actualSales + aTotalDiscountSales;
                            }
                            if (object[NumericConstants.THREE] != null) {
                                Double aTotalDiscountAmount = DataTypeConverter.convertObjectToDouble(object[NumericConstants.THREE]);
                                actualAmount = actualAmount + aTotalDiscountAmount;
                            }
                            if (object[NumericConstants.FOUR] != null) {
                                Double pTotalDiscountSales = DataTypeConverter.convertObjectToDouble(object[NumericConstants.FOUR]);
                                projectedSales = projectedSales + pTotalDiscountSales;
                            }
                            if (object[NumericConstants.FIVE] != null) {
                                Double pTotalDiscountAmount = DataTypeConverter.convertObjectToDouble(object[NumericConstants.FIVE]);
                                projectedAmount = projectedAmount + pTotalDiscountAmount;
                            }

                            if (list.size() == 1) {
                                Double aTotalDiscountrate = actualAmount / actualSales;
                                if (aTotalDiscountrate.isNaN()) {
                                    aTotalDiscountrate = 0.0;
                                }
                                aTotalDiscountrate = aTotalDiscountrate * NumericConstants.HUNDRED;
                                String actRate = String.valueOf(aTotalDiscountrate);

                                discountAnnualDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                Double actualTotalDiscountAmt = aTotalDiscountrate * actualSales;
                                actualTotalDiscountAmt = actualTotalDiscountAmt / NumericConstants.HUNDRED;
                                if (actualTotalDiscountAmt.isNaN()) {
                                    actualTotalDiscountAmt = 0.0;
                                }
                                String actAmt = String.valueOf(actualTotalDiscountAmt);
                                discountAnnualDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                                Double pTotalDiscountrate = projectedAmount / projectedSales;
                                if (pTotalDiscountrate.isNaN() || pTotalDiscountrate.isInfinite()) {
                                    pTotalDiscountrate = 0.0;
                                }
                                pTotalDiscountrate = pTotalDiscountrate * NumericConstants.HUNDRED;
                                String proRate = String.valueOf(pTotalDiscountrate);
                                discountAnnualDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                Double projectedAmtAmt = pTotalDiscountrate * projectedSales;
                                projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                                if (projectedAmtAmt.isNaN()) {
                                    projectedAmtAmt = 0.0;
                                }
                                String proTotalDiscountAmount = String.valueOf(projectedAmtAmt);
                                discountAnnualDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proTotalDiscountAmount != null && !NULL.equals(String.valueOf(proTotalDiscountAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proTotalDiscountAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proTotalDiscountAmount)))) : HYPHEN);
                                discountProjList.add(discountAnnualDto);
                            } else {
                                for (int i = 1; i < list.size(); i++) {
                                    Object[] obj = (Object[]) list.get(i);
                                    String selectedDiscount = String.valueOf(obj[NumericConstants.EIGHT]);
                                    selectedDiscount = selectedDiscount.replace(" ", StringUtils.EMPTY);
                                    int selectedYear = (Integer) obj[0];
                                    if (currentYear == selectedYear) {
                                        if (currentDiscount.equalsIgnoreCase(selectedDiscount)) {
                                            if (obj[NumericConstants.TWO] != null) {
                                                Double acurrentDiscountSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.TWO]);
                                                actualSales = actualSales + acurrentDiscountSales;
                                            }
                                            if (obj[NumericConstants.THREE] != null) {
                                                Double acurrentDiscountAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.THREE]);
                                                actualAmount = actualAmount + acurrentDiscountAmount;
                                            }
                                            if (obj[NumericConstants.FOUR] != null) {
                                                Double pcurrentDiscountSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FOUR]);
                                                projectedSales = projectedSales + pcurrentDiscountSales;
                                            }
                                            if (obj[NumericConstants.FIVE] != null) {
                                                Double pcurrentDiscountAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FIVE]);
                                                projectedAmount = projectedAmount + pcurrentDiscountAmount;
                                            }
                                        } else {
                                            Double acurrentDiscountrate = actualAmount / actualSales;
                                            if (acurrentDiscountrate.isNaN()) {
                                                acurrentDiscountrate = 0.0;
                                            }
                                            acurrentDiscountrate = acurrentDiscountrate * NumericConstants.HUNDRED;
                                            String actRate = String.valueOf(acurrentDiscountrate);
                                            discountAnnualDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                            Double actualAmt = acurrentDiscountrate * actualSales;
                                            actualAmt = actualAmt / NumericConstants.HUNDRED;
                                            if (actualAmt.isNaN()) {
                                                actualAmt = 0.0;
                                            }
                                            String actcurrentDiscountAmt = String.valueOf(actualAmt);
                                            discountAnnualDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actcurrentDiscountAmt != null && !NULL.equals(String.valueOf(actcurrentDiscountAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actcurrentDiscountAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actcurrentDiscountAmt)))) : HYPHEN);
                                            Double pcurrentDiscountrate = projectedAmount / projectedSales;
                                            if (pcurrentDiscountrate.isNaN() || pcurrentDiscountrate.isInfinite()) {
                                                pcurrentDiscountrate = 0.0;
                                            }
                                            pcurrentDiscountrate = pcurrentDiscountrate * NumericConstants.HUNDRED;
                                            String proRate = String.valueOf(pcurrentDiscountrate);
                                            discountAnnualDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                            Double projectedAmtAmt = pcurrentDiscountrate * projectedSales;
                                            projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                                            if (projectedAmtAmt.isNaN()) {
                                                projectedAmtAmt = 0.0;
                                            }
                                            String procurrentDiscountAmount = String.valueOf(projectedAmtAmt);
                                            discountAnnualDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, procurrentDiscountAmount != null && !NULL.equals(String.valueOf(procurrentDiscountAmount)) && !StringUtils.EMPTY.equals(String.valueOf(procurrentDiscountAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(procurrentDiscountAmount)))) : HYPHEN);
                                            actualSales = 0;
                                            actualAmount = 0;
                                            projectedSales = 0;
                                            projectedAmount = 0;
                                            currentDiscount = selectedDiscount;
                                            commonColumn = selectedDiscount.replace(" ", StringUtils.EMPTY);
                                            if (obj[NumericConstants.TWO] != null) {
                                                Double aDiscountSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.TWO]);
                                                actualSales = actualSales + aDiscountSales;
                                            }
                                            if (obj[NumericConstants.THREE] != null) {
                                                Double aDiscountAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.THREE]);
                                                actualAmount = actualAmount + aDiscountAmount;
                                            }
                                            if (obj[NumericConstants.FOUR] != null) {
                                                Double pDiscountSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FOUR]);
                                                projectedSales = projectedSales + pDiscountSales;
                                            }
                                            if (obj[NumericConstants.FIVE] != null) {
                                                Double pDiscountAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FIVE]);
                                                projectedAmount = projectedAmount + pDiscountAmount;
                                            }

                                        }
                                    } else {
                                        if (periodList.contains(discountAnnualDto.getGroup())) {
                                            Double aDiscountrate = actualAmount / actualSales;
                                            if (aDiscountrate.isNaN()) {
                                                aDiscountrate = 0.0;
                                            }
                                            aDiscountrate = aDiscountrate * NumericConstants.HUNDRED;
                                            String actRate = String.valueOf(aDiscountrate);
                                            discountAnnualDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                            Double actualAmt = aDiscountrate * actualSales;
                                            actualAmt = actualAmt / NumericConstants.HUNDRED;
                                            if (actualAmt.isNaN()) {
                                                actualAmt = 0.0;
                                            }
                                            String actDiscountAmt = String.valueOf(actualAmt);
                                            discountAnnualDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actDiscountAmt != null && !NULL.equals(String.valueOf(actDiscountAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actDiscountAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actDiscountAmt)))) : HYPHEN);
                                            Double prate = projectedAmount / projectedSales;
                                            if (prate.isNaN() || prate.isInfinite()) {
                                                prate = 0.0;
                                            }
                                            prate = prate * NumericConstants.HUNDRED;
                                            String proDiscountRate = String.valueOf(prate);
                                            discountAnnualDto.addStringProperties(commonColumn + PROJECTIONSRATE, proDiscountRate != null && !NULL.equals(String.valueOf(proDiscountRate)) && !StringUtils.EMPTY.equals(String.valueOf(proDiscountRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proDiscountRate)))).concat(PERCENTAGE) : HYPHEN);
                                            Double projectedAmtAmt = prate * projectedSales;
                                            projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                                            if (projectedAmtAmt.isNaN()) {
                                                projectedAmtAmt = 0.0;
                                            }
                                            String proDiscountAmount = String.valueOf(projectedAmtAmt);
                                            discountAnnualDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proDiscountAmount != null && !NULL.equals(String.valueOf(proDiscountAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proDiscountAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proDiscountAmount)))) : HYPHEN);
                                            discountProjList.add(discountAnnualDto);
                                            periodList.remove(discountAnnualDto.getGroup());
                                        }
                                        actualSales = 0;
                                        actualAmount = 0;
                                        projectedSales = 0;
                                        projectedAmount = 0;
                                        discountAnnualDto = new DiscountProjectionResultsDTO();
                                        currentDiscount = selectedDiscount;
                                        discountAnnualDto.setGroup(String.valueOf(selectedYear));
                                        currentYear = selectedYear;
                                        commonColumn = selectedDiscount.replace(" ", StringUtils.EMPTY);
                                        discountAnnualDto.setIsParent(ZERO_SYMBOL);
                                        if (obj[NumericConstants.TWO] != null) {
                                            Double aPivotProjectionSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.TWO]);
                                            actualSales = actualSales + aPivotProjectionSales;
                                        }
                                        if (obj[NumericConstants.THREE] != null) {
                                            Double aPivotProjectionAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.THREE]);
                                            actualAmount = actualAmount + aPivotProjectionAmount;
                                        }
                                        if (obj[NumericConstants.FOUR] != null) {
                                            Double pPivotProjectionSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FOUR]);
                                            projectedSales = projectedSales + pPivotProjectionSales;
                                        }
                                        if (obj[NumericConstants.FIVE] != null) {
                                            Double pPivotProjectionAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FIVE]);
                                            projectedAmount = projectedAmount + pPivotProjectionAmount;
                                        }
                                    }
                                        if ((i == list.size() - 1)&& (periodList.contains(discountAnnualDto.getGroup()))) {
                                            Double aPivotProjectionrate = actualAmount / actualSales;
                                            if (aPivotProjectionrate.isNaN()) {
                                                aPivotProjectionrate = 0.0;
                                            }
                                            aPivotProjectionrate = aPivotProjectionrate * NumericConstants.HUNDRED;
                                            String actRate = String.valueOf(aPivotProjectionrate);
                                            discountAnnualDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                            Double actualAmt = aPivotProjectionrate * actualSales;
                                            actualAmt = actualAmt / NumericConstants.HUNDRED;
                                            if (actualAmt.isNaN()) {
                                                actualAmt = 0.0;
                                            }
                                            String actPivotProjectionAmt = String.valueOf(actualAmt);
                                            discountAnnualDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actPivotProjectionAmt != null && !NULL.equals(String.valueOf(actPivotProjectionAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actPivotProjectionAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actPivotProjectionAmt)))) : HYPHEN);
                                            Double pPivotProjectionrate = projectedAmount / projectedSales;
                                            if (pPivotProjectionrate.isNaN() || pPivotProjectionrate.isInfinite()) {
                                                pPivotProjectionrate = 0.0;
                                            }
                                            pPivotProjectionrate = pPivotProjectionrate * NumericConstants.HUNDRED;
                                            String proRate = String.valueOf(pPivotProjectionrate);
                                            discountAnnualDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                            Double projectedAmtAmt = pPivotProjectionrate * projectedSales;
                                            projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                                            if (projectedAmtAmt.isNaN()) {
                                                projectedAmtAmt = 0.0;
                                            }
                                            String proPivotProjectionAmount = String.valueOf(projectedAmtAmt);
                                            discountAnnualDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proPivotProjectionAmount != null && !NULL.equals(String.valueOf(proPivotProjectionAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proPivotProjectionAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proPivotProjectionAmount)))) : HYPHEN);
                                            discountProjList.add(discountAnnualDto);
                                            periodList.remove(discountAnnualDto.getGroup());
                                        }
                                }
                            }

                            if (!periodList.isEmpty() && !projSelDTO.getDiscountNameList().isEmpty()) {
                                for (int i = 0; i < periodList.size(); i++) {
                                    DiscountProjectionResultsDTO projDTO = new DiscountProjectionResultsDTO();
                                    projDTO.setParent(0);
                                    projDTO.setProjectionTotal(1);
                                    projDTO.setGroup(String.valueOf(periodList.get(i)));
                                    for (String discount : projSelDTO.getDiscountNameList()) {
                                        String discountPivotProjectionRate = discount.replaceAll(" ", StringUtils.EMPTY);
                                        String columns = discountPivotProjectionRate + ACTUALSRATE;
                                        projDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));

                                        String discountPivotProjectionActualAmount = discount.replaceAll(" ", StringUtils.EMPTY);
                                        String columns1 = discountPivotProjectionActualAmount + ACTUALSAMOUNT;
                                        projDTO.addStringProperties(columns1, getFormattedValue(CUR_ZERO, Constant.NULL));

                                        String discountPivotProjectionProjectionAmount = discount.replaceAll(" ", StringUtils.EMPTY);
                                        String columns2 = discountPivotProjectionProjectionAmount + PROJECTIONSAMOUNT;
                                        projDTO.addStringProperties(columns2, getFormattedValue(CUR_ZERO, Constant.NULL));

                                        String discountPivotProjectionProjectionRate = discount.replaceAll(" ", StringUtils.EMPTY);
                                        String columns3 = discountPivotProjectionProjectionRate + PROJECTIONSRATE;
                                        projDTO.addStringProperties(columns3, getFormattedValue(CUR_ZERO, Constant.NULL));
                                    }
                                    discountProjList.add(projDTO);
                                }
                            }
                        }

                        if (freq.equals(QUARTERLY.getConstant())) {
                            double actualSales = 0;
                            double actualAmount = 0;
                            double projectedSales = 0;
                            double projectedAmount = 0;
                            String commonColumn;
                            List<String> periodList = new ArrayList<>(projSelDTO.getPeriodList());
                            DiscountProjectionResultsDTO discountQuarterlyDto = new DiscountProjectionResultsDTO();
                            Object[] object = (Object[]) list.get(0);
                            String currentDiscount = String.valueOf(object[NumericConstants.EIGHT]);
                            int currentYear = (Integer) object[0];
                            int currentQuarter = (Integer) object[NumericConstants.SIX];
                            currentDiscount = currentDiscount.replace(" ", StringUtils.EMPTY);
                            commonColumn = currentDiscount;
                            discountQuarterlyDto.setGroup(Constant.Q + object[1] + " " + object[0]);
                            discountQuarterlyDto.setIsParent(ZERO_SYMBOL);
                            if (object[NumericConstants.TWO] != null) {
                                Double actSales = DataTypeConverter.convertObjectToDouble(object[NumericConstants.TWO]);
                                actualSales = actualSales + actSales;
                            }
                            if (object[NumericConstants.THREE] != null) {
                                Double actAmount = DataTypeConverter.convertObjectToDouble(object[NumericConstants.THREE]);
                                actualAmount = actualAmount + actAmount;
                            }
                            if (object[NumericConstants.FOUR] != null) {
                                Double projSales = DataTypeConverter.convertObjectToDouble(object[NumericConstants.FOUR]);
                                projectedSales = projectedSales + projSales;
                            }
                            if (object[NumericConstants.FIVE] != null) {
                                Double projAmount = DataTypeConverter.convertObjectToDouble(object[NumericConstants.FIVE]);
                                projectedAmount = projectedAmount + projAmount;
                            }

                            if (list.size() == 1) {
                                Double aRateQuarter = actualAmount / actualSales;
                                if (aRateQuarter.isNaN()) {
                                    aRateQuarter = 0.0;
                                }
                                aRateQuarter = aRateQuarter * NumericConstants.HUNDRED;
                                String actRate = String.valueOf(aRateQuarter);
                                discountQuarterlyDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                Double actualAmt = aRateQuarter * actualSales;
                                actualAmt = actualAmt / NumericConstants.HUNDRED;
                                if (actualAmt.isNaN()) {
                                    actualAmt = 0.0;
                                }
                                String actAmt = String.valueOf(actualAmt);
                                discountQuarterlyDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                                Double prate = projectedAmount / projectedSales;
                                if (prate.isNaN() || prate.isInfinite()) {
                                    prate = 0.0;
                                }
                                prate = prate * NumericConstants.HUNDRED;
                                String proRate = String.valueOf(prate);
                                discountQuarterlyDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                Double projectedAmtAmt = prate * projectedSales;
                                projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                                if (projectedAmtAmt.isNaN()) {
                                    projectedAmtAmt = 0.0;
                                }
                                String proAmount = String.valueOf(projectedAmtAmt);
                                discountQuarterlyDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                discountProjList.add(discountQuarterlyDto);
                            } else {
                                for (int i = 1; i < list.size(); i++) {
                                    Object[] obj = (Object[]) list.get(i);
                                    String selectedDiscount = String.valueOf(obj[NumericConstants.EIGHT]);
                                    selectedDiscount = selectedDiscount.replace(" ", StringUtils.EMPTY);
                                    int selectedYear = (Integer) obj[0];
                                    int selectedQuarter = (Integer) obj[NumericConstants.SIX];
                                    if (currentYear == selectedYear && currentQuarter == selectedQuarter) {
                                        if (currentDiscount.equalsIgnoreCase(selectedDiscount)) {
                                            if (obj[NumericConstants.TWO] != null) {
                                                Double aSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.TWO]);
                                                actualSales = actualSales + aSales;
                                            }
                                            if (obj[NumericConstants.THREE] != null) {
                                                Double aAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.THREE]);
                                                actualAmount = actualAmount + aAmount;
                                            }
                                            if (obj[NumericConstants.FOUR] != null) {
                                                Double pSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FOUR]);
                                                projectedSales = projectedSales + pSales;
                                            }
                                            if (obj[NumericConstants.FIVE] != null) {
                                                Double pAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FIVE]);
                                                projectedAmount = projectedAmount + pAmount;
                                            }
                                        } else {
                                            Double arate = actualAmount / actualSales;
                                            if (arate.isNaN()) {
                                                arate = 0.0;
                                            }
                                            arate = arate * NumericConstants.HUNDRED;
                                            String actRate = String.valueOf(arate);
                                            discountQuarterlyDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                            Double actualAmt = arate * actualSales;
                                            actualAmt = actualAmt / NumericConstants.HUNDRED;
                                            if (actualAmt.isNaN()) {
                                                actualAmt = 0.0;
                                            }
                                            String actAmt = String.valueOf(actualAmt);
                                            discountQuarterlyDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                                            Double prate = projectedAmount / projectedSales;
                                            if (prate.isNaN() || prate.isInfinite()) {
                                                prate = 0.0;
                                            }
                                            prate = prate * NumericConstants.HUNDRED;
                                            String proRate = String.valueOf(prate);
                                            discountQuarterlyDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                            Double projectedAmtAmt = prate * projectedSales;
                                            projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                                            if (projectedAmtAmt.isNaN()) {
                                                projectedAmtAmt = 0.0;
                                            }
                                            String proAmount = String.valueOf(projectedAmtAmt);
                                            discountQuarterlyDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                            actualSales = 0;
                                            actualAmount = 0;
                                            projectedSales = 0;
                                            projectedAmount = 0;
                                            commonColumn = selectedDiscount;
                                            currentDiscount = selectedDiscount;
                                            if (obj[NumericConstants.TWO] != null) {
                                                Double aSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.TWO]);
                                                actualSales = actualSales + aSales;
                                            }
                                            if (obj[NumericConstants.THREE] != null) {
                                                Double aAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.THREE]);
                                                actualAmount = actualAmount + aAmount;
                                            }

                                            if (obj[NumericConstants.FOUR] != null) {
                                                Double pSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FOUR]);
                                                projectedSales = projectedSales + pSales;
                                            }
                                            if (obj[NumericConstants.FIVE] != null) {
                                                Double pAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FIVE]);
                                                projectedAmount = projectedAmount + pAmount;
                                            }
                                        }
                                    } else {
                                        String column = discountQuarterlyDto.getGroup().replace(" ", StringUtils.EMPTY);
                                        if (periodList.contains(column.replace('Q', 'q'))) {
                                            Double arate = actualAmount / actualSales;
                                            if (arate.isNaN()) {
                                                arate = 0.0;
                                            }
                                            arate = arate * NumericConstants.HUNDRED;
                                            String actRate = String.valueOf(arate);
                                            discountQuarterlyDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                            Double actualAmt = arate * actualSales;
                                            actualAmt = actualAmt / NumericConstants.HUNDRED;
                                            if (actualAmt.isNaN()) {
                                                actualAmt = 0.0;
                                            }
                                            String actAmt = String.valueOf(actualAmt);
                                            discountQuarterlyDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                                            Double prate = projectedAmount / projectedSales;
                                            if (prate.isNaN() || prate.isInfinite()) {
                                                prate = 0.0;
                                            }
                                            prate = prate * NumericConstants.HUNDRED;
                                            String proRate = String.valueOf(prate);
                                            discountQuarterlyDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                            Double projectedAmtAmt = prate * projectedSales;
                                            projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                                            if (projectedAmtAmt.isNaN()) {
                                                projectedAmtAmt = 0.0;
                                            }
                                            String proAmount = String.valueOf(projectedAmtAmt);
                                            discountQuarterlyDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                            discountProjList.add(discountQuarterlyDto);
                                            periodList.remove(column.replace('Q', 'q'));
                                        }
                                        actualSales = 0;
                                        actualAmount = 0;
                                        projectedSales = 0;
                                        projectedAmount = 0;
                                        discountQuarterlyDto = new DiscountProjectionResultsDTO();
                                        discountQuarterlyDto.setGroup(Constant.Q + obj[1] + " " + obj[0]);
                                        currentYear = selectedYear;
                                        currentQuarter = selectedQuarter;
                                        currentDiscount = selectedDiscount;
                                        commonColumn = selectedDiscount;
                                        discountQuarterlyDto.setIsParent(ZERO_SYMBOL);
                                        if (obj[NumericConstants.TWO] != null) {
                                            Double aSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.TWO]);
                                            actualSales = actualSales + aSales;
                                        }
                                        if (obj[NumericConstants.THREE] != null) {
                                            Double aAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.THREE]);
                                            actualAmount = actualAmount + aAmount;
                                        }
                                        if (obj[NumericConstants.FOUR] != null) {
                                            Double pSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FOUR]);
                                            projectedSales = projectedSales + pSales;
                                        }
                                        if (obj[NumericConstants.FIVE] != null) {
                                            Double pAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FIVE]);
                                            projectedAmount = projectedAmount + pAmount;
                                        }
                                    }
                                    if (i == list.size() - 1) {
                                        String column = discountQuarterlyDto.getGroup().replace(" ", StringUtils.EMPTY);
                                        if (periodList.contains(column.replace('Q', 'q'))) {
                                            Double arate = actualAmount / actualSales;
                                            if (arate.isNaN()) {
                                                arate = 0.0;
                                            }
                                            arate = arate * NumericConstants.HUNDRED;
                                            String actRate = String.valueOf(arate);
                                            discountQuarterlyDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                            Double actualAmt = arate * actualSales;
                                            actualAmt = actualAmt / NumericConstants.HUNDRED;
                                            if (actualAmt.isNaN()) {
                                                actualAmt = 0.0;
                                            }
                                            String actAmt = String.valueOf(actualAmt);
                                            discountQuarterlyDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                                            Double prate = projectedAmount / projectedSales;
                                            if (prate.isNaN() || prate.isInfinite()) {
                                                prate = 0.0;
                                            }
                                            prate = prate * NumericConstants.HUNDRED;
                                            String proRate = String.valueOf(prate);
                                            discountQuarterlyDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                            Double projectedAmtAmt = prate * projectedSales;
                                            projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                                            if (projectedAmtAmt.isNaN()) {
                                                projectedAmtAmt = 0.0;
                                            }
                                            String proAmount = String.valueOf(projectedAmtAmt);
                                            discountQuarterlyDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                            discountProjList.add(discountQuarterlyDto);
                                            periodList.remove(column.replace('Q', 'q'));
                                        }
                                    }
                                }
                            }
                            if (!periodList.isEmpty() && !projSelDTO.getDiscountNameList().isEmpty()) {
                                for (int i = 0; i < periodList.size(); i++) {
                                    DiscountProjectionResultsDTO projDTO = new DiscountProjectionResultsDTO();
                                    projDTO.setParent(0);
                                    projDTO.setProjectionTotal(1);
                                    String group = String.valueOf(periodList.get(i).replace('q', 'Q'));
                                    String year = group.substring(group.length() - NumericConstants.FOUR, group.length());
                                    String frequency = group.replace(year, StringUtils.EMPTY);
                                    group = frequency + " " + year;
                                    projDTO.setGroup(group);
                                    for (String discount : projSelDTO.getDiscountNameList()) {
                                        String discountRate = discount.replaceAll(" ", StringUtils.EMPTY);
                                        String columns = discountRate + ACTUALSRATE;
                                        projDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));

                                        String discountActualAmount = discount.replaceAll(" ", StringUtils.EMPTY);
                                        String columns1 = discountActualAmount + ACTUALSAMOUNT;
                                        projDTO.addStringProperties(columns1, getFormattedValue(CUR_ZERO, Constant.NULL));

                                        String discountProjectionAmount = discount.replaceAll(" ", StringUtils.EMPTY);
                                        String columns2 = discountProjectionAmount + PROJECTIONSAMOUNT;
                                        projDTO.addStringProperties(columns2, getFormattedValue(CUR_ZERO, Constant.NULL));

                                        String discountProjectionRate = discount.replaceAll(" ", StringUtils.EMPTY);
                                        String columns3 = discountProjectionRate + PROJECTIONSRATE;
                                        projDTO.addStringProperties(columns3, getFormattedValue(CUR_ZERO, Constant.NULL));
                                    }
                                    discountProjList.add(projDTO);
                                }
                            }

                        }
                        if (freq.equals(Constant.SEMI_ANNUALLY)) {
                            double actualSales = 0;
                            double actualAmount = 0;
                            double projectedSales = 0;
                            double projectedAmount = 0;
                            String commonColumn;
                            List<String> periodList = new ArrayList<>(projSelDTO.getPeriodList());
                            DiscountProjectionResultsDTO discountSemiAnnualDto = new DiscountProjectionResultsDTO();
                            Object[] object = (Object[]) list.get(0);
                            String currentDiscount = String.valueOf(object[NumericConstants.EIGHT]);
                            int currentYear = (Integer) object[0];
                            int currentQuarter = (Integer) object[NumericConstants.SIX];
                            commonColumn = currentDiscount.replace(" ", StringUtils.EMPTY);
                            discountSemiAnnualDto.setGroup(Constant.S + object[1] + " " + object[0]);
                            discountSemiAnnualDto.setIsParent(ZERO_SYMBOL);
                            if (object[NumericConstants.TWO] != null) {
                                Double actuSales = DataTypeConverter.convertObjectToDouble(object[NumericConstants.TWO]);
                                actualSales = actualSales + actuSales;
                            }
                            if (object[NumericConstants.THREE] != null) {
                                Double actuAmount = DataTypeConverter.convertObjectToDouble(object[NumericConstants.THREE]);
                                actualAmount = actualAmount + actuAmount;
                            }
                            if (object[NumericConstants.FOUR] != null) {
                                Double projectSales = DataTypeConverter.convertObjectToDouble(object[NumericConstants.FOUR]);
                                projectedSales = projectedSales + projectSales;
                            }
                            if (object[NumericConstants.FIVE] != null) {
                                Double projectAmount = DataTypeConverter.convertObjectToDouble(object[NumericConstants.FIVE]);
                                projectedAmount = projectedAmount + projectAmount;
                            }
                            if (list.size() == 1) {
                                Double aRateSemi = actualAmount / actualSales;
                                if (aRateSemi.isNaN()) {
                                    aRateSemi = 0.0;
                                }
                                aRateSemi = aRateSemi * NumericConstants.HUNDRED;
                                String actRate = String.valueOf(aRateSemi);
                                discountSemiAnnualDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                Double actualAmt = aRateSemi * actualSales;
                                actualAmt = actualAmt / NumericConstants.HUNDRED;
                                if (actualAmt.isNaN()) {
                                    actualAmt = 0.0;
                                }
                                String actAmt = String.valueOf(actualAmt);
                                discountSemiAnnualDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                                Double pRateSemi = projectedAmount / projectedSales;
                                if (pRateSemi.isNaN() || pRateSemi.isInfinite()) {
                                    pRateSemi = 0.0;
                                }
                                pRateSemi = pRateSemi * NumericConstants.HUNDRED;
                                String proRate = String.valueOf(pRateSemi);
                                discountSemiAnnualDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                Double projectedAmtAmt = pRateSemi * projectedSales;
                                projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                                if (projectedAmtAmt.isNaN()) {
                                    projectedAmtAmt = 0.0;
                                }
                                String proAmount = String.valueOf(projectedAmtAmt);
                                discountSemiAnnualDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                discountProjList.add(discountSemiAnnualDto);
                            } else {
                                for (int i = 1; i < list.size(); i++) {
                                    Object[] obj = (Object[]) list.get(i);
                                    String selectedDiscount = String.valueOf(obj[NumericConstants.EIGHT]);
                                    int selectedYear = (Integer) obj[0];
                                    int selectedQuarter = (Integer) obj[NumericConstants.SIX];
                                    if (currentYear == selectedYear && currentQuarter == selectedQuarter) {
                                        if (currentDiscount.equalsIgnoreCase(selectedDiscount)) {
                                            if (obj[NumericConstants.TWO] != null) {
                                                Double aSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.TWO]);
                                                actualSales = actualSales + aSales;
                                            }
                                            if (obj[NumericConstants.THREE] != null) {
                                                Double aAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.THREE]);
                                                actualAmount = actualAmount + aAmount;
                                            }
                                            if (obj[NumericConstants.FOUR] != null) {
                                                Double pSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FOUR]);
                                                projectedSales = projectedSales + pSales;
                                            }
                                            if (obj[NumericConstants.FIVE] != null) {
                                                Double pAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FIVE]);
                                                projectedAmount = projectedAmount + pAmount;
                                            }
                                        } else {
                                            Double arate = actualAmount / actualSales;
                                            if (arate.isNaN()) {
                                                arate = 0.0;
                                            }
                                            arate = arate * NumericConstants.HUNDRED;
                                            String actRate = String.valueOf(arate);
                                            discountSemiAnnualDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                            Double actualAmt = arate * actualSales;
                                            actualAmt = actualAmt / NumericConstants.HUNDRED;
                                            if (actualAmt.isNaN()) {
                                                actualAmt = 0.0;
                                            }
                                            String actAmt = String.valueOf(actualAmt);
                                            discountSemiAnnualDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                                            Double prate = projectedAmount / projectedSales;
                                            if (prate.isNaN() || prate.isInfinite()) {
                                                prate = 0.0;
                                            }
                                            prate = prate * NumericConstants.HUNDRED;
                                            String proRate = String.valueOf(prate);
                                            discountSemiAnnualDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                            Double projectedAmtAmt = prate * projectedSales;
                                            projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                                            if (projectedAmtAmt.isNaN()) {
                                                projectedAmtAmt = 0.0;
                                            }
                                            String proAmount = String.valueOf(projectedAmtAmt);
                                            discountSemiAnnualDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                            actualSales = 0;
                                            actualAmount = 0;
                                            projectedSales = 0;
                                            projectedAmount = 0;
                                            currentDiscount = selectedDiscount;
                                            commonColumn = selectedDiscount.replace(" ", StringUtils.EMPTY);
                                            if (obj[NumericConstants.TWO] != null) {
                                                Double aSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.TWO]);
                                                actualSales = actualSales + aSales;
                                            }
                                            if (obj[NumericConstants.THREE] != null) {
                                                Double aAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.THREE]);
                                                actualAmount = actualAmount + aAmount;
                                            }

                                            if (obj[NumericConstants.FOUR] != null) {
                                                Double pSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FOUR]);
                                                projectedSales = projectedSales + pSales;
                                            }
                                            if (obj[NumericConstants.FIVE] != null) {
                                                Double pAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FIVE]);
                                                projectedAmount = projectedAmount + pAmount;
                                            }
                                        }
                                    } else {
                                        String column = discountSemiAnnualDto.getGroup().replace(" ", StringUtils.EMPTY);
                                        periodList.contains(column.replace('S', 's'));
                                        {
                                            Double arate = actualAmount / actualSales;
                                            if (arate.isNaN()) {
                                                arate = 0.0;
                                            }
                                            arate = arate * NumericConstants.HUNDRED;
                                            String actRate = String.valueOf(arate);
                                            discountSemiAnnualDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                            Double actualAmt = arate * actualSales;
                                            actualAmt = actualAmt / NumericConstants.HUNDRED;
                                            if (actualAmt.isNaN()) {
                                                actualAmt = 0.0;
                                            }
                                            String actAmt = String.valueOf(actualAmt);
                                            discountSemiAnnualDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                                            Double prate = projectedAmount / projectedSales;
                                            if (prate.isNaN() || prate.isInfinite()) {
                                                prate = 0.0;
                                            }
                                            prate = prate * NumericConstants.HUNDRED;
                                            String proRate = String.valueOf(prate);
                                            discountSemiAnnualDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                            Double projectedAmtAmt = prate * projectedSales;
                                            projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                                            if (projectedAmtAmt.isNaN()) {
                                                projectedAmtAmt = 0.0;
                                            }
                                            String proAmount = String.valueOf(projectedAmtAmt);
                                            discountSemiAnnualDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                            discountProjList.add(discountSemiAnnualDto);
                                            periodList.remove(column.replace('S', 's'));
                                        }
                                        actualSales = 0;
                                        actualAmount = 0;
                                        projectedSales = 0;
                                        projectedAmount = 0;
                                        discountSemiAnnualDto = new DiscountProjectionResultsDTO();
                                        discountSemiAnnualDto.setGroup(Constant.S + obj[1] + " " + obj[0]);
                                        currentYear = selectedYear;
                                        currentQuarter = selectedQuarter;
                                        currentDiscount = selectedDiscount;
                                        commonColumn = selectedDiscount.replace(" ", StringUtils.EMPTY);
                                        discountSemiAnnualDto.setIsParent(ZERO_SYMBOL);
                                        if (obj[NumericConstants.TWO] != null) {
                                            Double aSal = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.TWO]);
                                            actualSales = actualSales + aSal;
                                        }
                                        if (obj[NumericConstants.THREE] != null) {
                                            Double aAmt = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.THREE]);
                                            actualAmount = actualAmount + aAmt;
                                        }
                                        if (obj[NumericConstants.FOUR] != null) {
                                            Double pSal = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FOUR]);
                                            projectedSales = projectedSales + pSal;
                                        }
                                        if (obj[NumericConstants.FIVE] != null) {
                                            Double pAmt = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FIVE]);
                                            projectedAmount = projectedAmount + pAmt;
                                        }
                                    }
                                    if (i == list.size() - 1) {
                                        String column = discountSemiAnnualDto.getGroup().replace(" ", StringUtils.EMPTY);
                                        if (periodList.contains(column.replace('S', 's'))) {
                                            Double arate = actualAmount / actualSales;
                                            if (arate.isNaN()) {
                                                arate = 0.0;
                                            }
                                            arate = arate * NumericConstants.HUNDRED;
                                            String actRate = String.valueOf(arate);
                                            discountSemiAnnualDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                            Double actualAmt = arate * actualSales;
                                            actualAmt = actualAmt / NumericConstants.HUNDRED;
                                            if (actualAmt.isNaN()) {
                                                actualAmt = 0.0;
                                            }
                                            String actAmt = String.valueOf(actualAmt);
                                            discountSemiAnnualDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                                            Double prate = projectedAmount / projectedSales;
                                            if (prate.isNaN() || prate.isInfinite()) {
                                                prate = 0.0;
                                            }
                                            prate = prate * NumericConstants.HUNDRED;
                                            String proRate = String.valueOf(prate);
                                            discountSemiAnnualDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                            Double projectedAmtAmt = prate * projectedSales;
                                            projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                                            if (projectedAmtAmt.isNaN()) {
                                                projectedAmtAmt = 0.0;
                                            }
                                            String proAmount = String.valueOf(projectedAmtAmt);
                                            discountSemiAnnualDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                            discountProjList.add(discountSemiAnnualDto);
                                            periodList.remove(column.replace('S', 's'));
                                        }
                                    }
                                }
                            }
                            if (!periodList.isEmpty() && !projSelDTO.getDiscountNameList().isEmpty()) {
                                for (int i = 0; i < periodList.size(); i++) {
                                    DiscountProjectionResultsDTO projDTO = new DiscountProjectionResultsDTO();
                                    projDTO.setParent(0);
                                    projDTO.setProjectionTotal(1);
                                    String group = String.valueOf(periodList.get(i).replace('s', 'S'));
                                    String year = group.substring(group.length() - NumericConstants.FOUR, group.length());
                                    String frequency = group.replace(year, StringUtils.EMPTY);
                                    group = frequency + " " + year;
                                    projDTO.setGroup(group);
                                    for (String discount : projSelDTO.getDiscountNameList()) {
                                        String discountRate = discount.replaceAll(" ", StringUtils.EMPTY);
                                        String columns = discountRate + ACTUALSRATE;
                                        projDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));

                                        String discountActualAmount = discount.replaceAll(" ", StringUtils.EMPTY);
                                        String columns1 = discountActualAmount + ACTUALSAMOUNT;
                                        projDTO.addStringProperties(columns1, getFormattedValue(CUR_ZERO, Constant.NULL));

                                        String discountProjectionAmount = discount.replaceAll(" ", StringUtils.EMPTY);
                                        String columns2 = discountProjectionAmount + PROJECTIONSAMOUNT;
                                        projDTO.addStringProperties(columns2, getFormattedValue(CUR_ZERO, Constant.NULL));

                                        String discountProjectionRate = discount.replaceAll(" ", StringUtils.EMPTY);
                                        String columns3 = discountProjectionRate + PROJECTIONSRATE;
                                        projDTO.addStringProperties(columns3, getFormattedValue(CUR_ZERO, Constant.NULL));
                                    }
                                    discountProjList.add(projDTO);
                                }
                            }
                        }
                        if (freq.equals(Constant.MONTHLY)) {
                            double actualSales = 0;
                            double actualAmount = 0;
                            double projectedSales = 0;
                            double projectedAmount = 0;
                            String commonColumn;
                            loadMap();
                            List<String> periodList = new ArrayList<>(projSelDTO.getPeriodList());
                            DiscountProjectionResultsDTO discountMonthlyDto = new DiscountProjectionResultsDTO();
                            Object[] object = (Object[]) list.get(0);
                            String currentDiscount = String.valueOf(object[NumericConstants.EIGHT]);
                            int currentYear = (Integer) object[0];
                            int currentMonth = (Integer) object[NumericConstants.SIX];
                            commonColumn = currentDiscount.replace(" ", StringUtils.EMPTY);
                            discountMonthlyDto.setGroup(CommonUtils.BUSINESS_PROCESS_INDICATOR_MANDATED + object[1] + " " + object[0]);
                            discountMonthlyDto.setIsParent(ZERO_SYMBOL);
                            if (object[NumericConstants.TWO] != null) {
                                Double aSalesMonthly = DataTypeConverter.convertObjectToDouble(object[NumericConstants.TWO]);
                                actualSales = actualSales + aSalesMonthly;
                            }
                            if (object[NumericConstants.THREE] != null) {
                                Double aAmountMonthly = DataTypeConverter.convertObjectToDouble(object[NumericConstants.THREE]);
                                actualAmount = actualAmount + aAmountMonthly;
                            }
                            if (object[NumericConstants.FOUR] != null) {
                                Double pSalesMonthly = DataTypeConverter.convertObjectToDouble(object[NumericConstants.FOUR]);
                                projectedSales = projectedSales + pSalesMonthly;
                            }
                            if (object[NumericConstants.FIVE] != null) {
                                Double pAmount = DataTypeConverter.convertObjectToDouble(object[NumericConstants.FIVE]);
                                projectedAmount = projectedAmount + pAmount;
                            }
                            if (list.size() == 1) {
                                Double actualrate = actualAmount / actualSales;
                                if (actualrate.isNaN()) {
                                    actualrate = 0.0;
                                }
                                actualrate = actualrate * NumericConstants.HUNDRED;
                                String actRate = String.valueOf(actualrate);
                                discountMonthlyDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                Double actualAmt = actualrate * actualSales;
                                actualAmt = actualAmt / NumericConstants.HUNDRED;
                                if (actualAmt.isNaN()) {
                                    actualAmt = 0.0;
                                }
                                String actAmt = String.valueOf(actualAmt);
                                discountMonthlyDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                                Double prate = projectedAmount / projectedSales;
                                if (prate.isNaN() || prate.isInfinite()) {
                                    prate = 0.0;
                                }
                                prate = prate * NumericConstants.HUNDRED;
                                String proRate = String.valueOf(prate);
                                discountMonthlyDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                Double projectedAmtAmt = prate * projectedSales;
                                projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                                if (projectedAmtAmt.isNaN()) {
                                    projectedAmtAmt = 0.0;
                                }
                                String proAmount = String.valueOf(projectedAmtAmt);
                                discountMonthlyDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                discountProjList.add(discountMonthlyDto);
                            } else {
                                for (int i = 1; i < list.size(); i++) {
                                    Object[] obj = (Object[]) list.get(i);
                                    String selectedDiscount = String.valueOf(obj[NumericConstants.EIGHT]);
                                    int selectedYear = (Integer) obj[0];
                                    int selectedMonth = (Integer) obj[NumericConstants.SIX];
                                    if (currentYear == selectedYear && currentMonth == selectedMonth) {
                                        if (currentDiscount.equalsIgnoreCase(selectedDiscount)) {
                                            if (obj[NumericConstants.TWO] != null) {
                                                Double aSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.TWO]);
                                                actualSales = actualSales + aSales;
                                            }
                                            if (obj[NumericConstants.THREE] != null) {
                                                Double aAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.THREE]);
                                                actualAmount = actualAmount + aAmount;
                                            }
                                            if (obj[NumericConstants.FOUR] != null) {
                                                Double pSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FOUR]);
                                                projectedSales = projectedSales + pSales;
                                            }
                                            if (obj[NumericConstants.FIVE] != null) {
                                                Double pAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FIVE]);
                                                projectedAmount = projectedAmount + pAmount;
                                            }
                                        } else {
                                            Double arate = actualAmount / actualSales;
                                            if (arate.isNaN()) {
                                                arate = 0.0;
                                            }
                                            arate = arate * NumericConstants.HUNDRED;
                                            String actRate = String.valueOf(arate);
                                            discountMonthlyDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                            Double actualAmt = arate * actualSales;
                                            actualAmt = actualAmt / NumericConstants.HUNDRED;
                                            if (actualAmt.isNaN()) {
                                                actualAmt = 0.0;
                                            }
                                            String actAmt = String.valueOf(actualAmt);
                                            discountMonthlyDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                                            Double prate = projectedAmount / projectedSales;
                                            if (prate.isNaN() || prate.isInfinite()) {
                                                prate = 0.0;
                                            }
                                            prate = prate * NumericConstants.HUNDRED;
                                            String proRate = String.valueOf(prate);
                                            discountMonthlyDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                            Double projectedAmtAmt = prate * projectedSales;
                                            projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                                            if (projectedAmtAmt.isNaN()) {
                                                projectedAmtAmt = 0.0;
                                            }
                                            String proAmount = String.valueOf(projectedAmtAmt);
                                            discountMonthlyDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                            actualSales = 0;
                                            actualAmount = 0;
                                            projectedSales = 0;
                                            projectedAmount = 0;
                                            currentDiscount = selectedDiscount;
                                            commonColumn = selectedDiscount.replace(" ", StringUtils.EMPTY);
                                            if (obj[NumericConstants.TWO] != null) {
                                                Double aSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.TWO]);
                                                actualSales = actualSales + aSales;
                                            }
                                            if (obj[NumericConstants.THREE] != null) {
                                                Double aAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.THREE]);
                                                actualAmount = actualAmount + aAmount;
                                            }
                                            if (obj[NumericConstants.FOUR] != null) {
                                                Double pSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FOUR]);
                                                projectedSales = projectedSales + pSales;
                                            }
                                            if (obj[NumericConstants.FIVE] != null) {
                                                Double pAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FIVE]);
                                                projectedAmount = projectedAmount + pAmount;
                                            }
                                        }
                                    } else {
                                        String column = createColumn(String.valueOf(discountMonthlyDto.getGroup().replace(" ", StringUtils.EMPTY)));
                                        if (periodList.contains(column)) {
                                            Double arate = actualAmount / actualSales;
                                            if (arate.isNaN()) {
                                                arate = 0.0;
                                            }
                                            arate = arate * NumericConstants.HUNDRED;
                                            String actRate = String.valueOf(arate);
                                            discountMonthlyDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                            Double actualAmt = arate * actualSales;
                                            actualAmt = actualAmt / NumericConstants.HUNDRED;
                                            if (actualAmt.isNaN()) {
                                                actualAmt = 0.0;
                                            }
                                            String actAmt = String.valueOf(actualAmt);
                                            discountMonthlyDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                                            Double prate = projectedAmount / projectedSales;
                                            if (prate.isNaN() || prate.isInfinite()) {
                                                prate = 0.0;
                                            }
                                            prate = prate * NumericConstants.HUNDRED;
                                            String proRate = String.valueOf(prate);
                                            discountMonthlyDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                            Double projectedAmtAmt = prate * projectedSales;
                                            projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                                            if (projectedAmtAmt.isNaN()) {
                                                projectedAmtAmt = 0.0;
                                            }
                                            String proAmount = String.valueOf(projectedAmtAmt);
                                            discountMonthlyDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                            discountProjList.add(discountMonthlyDto);
                                            periodList.remove(column);
                                        }
                                        actualSales = 0;
                                        actualAmount = 0;
                                        projectedSales = 0;
                                        projectedAmount = 0;
                                        discountMonthlyDto = new DiscountProjectionResultsDTO();
                                        discountMonthlyDto.setGroup(CommonUtils.BUSINESS_PROCESS_INDICATOR_MANDATED + obj[1] + " " + obj[0]);
                                        currentYear = selectedYear;
                                        currentMonth = selectedMonth;
                                        commonColumn = selectedDiscount.replace(" ", StringUtils.EMPTY);
                                        currentDiscount = selectedDiscount;
                                        discountMonthlyDto.setIsParent(ZERO_SYMBOL);
                                        if (obj[NumericConstants.TWO] != null) {
                                            Double aSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.TWO]);
                                            actualSales = actualSales + aSales;
                                        }
                                        if (obj[NumericConstants.THREE] != null) {
                                            Double aAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.THREE]);
                                            actualAmount = actualAmount + aAmount;
                                        }
                                        if (obj[NumericConstants.FOUR] != null) {
                                            Double pSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FOUR]);
                                            projectedSales = projectedSales + pSales;
                                        }
                                        if (obj[NumericConstants.FIVE] != null) {
                                            Double pAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FIVE]);
                                            projectedAmount = projectedAmount + pAmount;
                                        }
                                    }
                                    if (i == list.size() - 1) {
                                        String column = createColumn(String.valueOf(discountMonthlyDto.getGroup()).replace(" ", StringUtils.EMPTY));
                                        if (periodList.contains(column)) {
                                            Double arate = actualAmount / actualSales;
                                            if (arate.isNaN()) {
                                                arate = 0.0;
                                            }
                                            arate = arate * NumericConstants.HUNDRED;
                                            String actRate = String.valueOf(arate);
                                            discountMonthlyDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                            Double actualAmt = arate * actualSales;
                                            actualAmt = actualAmt / NumericConstants.HUNDRED;
                                            if (actualAmt.isNaN()) {
                                                actualAmt = 0.0;
                                            }
                                            String actAmt = String.valueOf(actualAmt);
                                            discountMonthlyDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                                            Double prate = projectedAmount / projectedSales;
                                            if (prate.isNaN() || prate.isInfinite()) {
                                                prate = 0.0;
                                            }
                                            prate = prate * NumericConstants.HUNDRED;
                                            String proRate = String.valueOf(prate);
                                            discountMonthlyDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                            Double projectedAmtAmt = prate * projectedSales;
                                            projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                                            if (projectedAmtAmt.isNaN()) {
                                                projectedAmtAmt = 0.0;
                                            }
                                            String proAmount = String.valueOf(projectedAmtAmt);
                                            discountMonthlyDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                            discountProjList.add(discountMonthlyDto);
                                            periodList.remove(column);
                                        }
                                    }
                                }
                            }
                            if (!periodList.isEmpty() && !projSelDTO.getDiscountNameList().isEmpty()) {
                                for (int i = 0; i < periodList.size(); i++) {
                                    DiscountProjectionResultsDTO projDTO = new DiscountProjectionResultsDTO();
                                    loadKeyMap();
                                    projDTO.setParent(0);
                                    projDTO.setProjectionTotal(1);
                                    String group = String.valueOf(periodList.get(i));
                                    String month = group.substring(0, NumericConstants.THREE);
                                    String year = group.replace(month, StringUtils.EMPTY);
                                    String column = valueMap.get(month);

                                    String groupColumn = CommonUtils.BUSINESS_PROCESS_INDICATOR_MANDATED + column + " " + year;
                                    projDTO.setGroup(String.valueOf(groupColumn));
                                    for (String discount : projSelDTO.getDiscountNameList()) {
                                        String discountRate = discount.replaceAll(" ", StringUtils.EMPTY);
                                        String columns = discountRate + ACTUALSRATE;
                                        projDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));

                                        String discountActualAmount = discount.replaceAll(" ", StringUtils.EMPTY);
                                        String columns1 = discountActualAmount + ACTUALSAMOUNT;
                                        projDTO.addStringProperties(columns1, getFormattedValue(CUR_ZERO, Constant.NULL));

                                        String discountProjectionAmount = discount.replaceAll(" ", StringUtils.EMPTY);
                                        String columns2 = discountProjectionAmount + PROJECTIONSAMOUNT;
                                        projDTO.addStringProperties(columns2, getFormattedValue(CUR_ZERO, Constant.NULL));

                                        String discountProjectionRate = discount.replaceAll(" ", StringUtils.EMPTY);
                                        String columns3 = discountProjectionRate + PROJECTIONSRATE;
                                        projDTO.addStringProperties(columns3, getFormattedValue(CUR_ZERO, Constant.NULL));
                                    }
                                    discountProjList.add(projDTO);
                                }
                            }
                        }
                        if (projSelDTO.getProjectionOrder().equals(Constant.DESCENDING)) {
                            Collections.reverse(discountProjList);
                        }

                        return discountProjList;
                    }
                }
            }
        } catch (SystemException ex) {
            LoggerFactory.getLogger(DiscountProjectionResultsLogic.class.getName()).error( StringUtils.EMPTY, ex);
        }
        return Collections.emptyList();
    }

    public List<DiscountProjectionResultsDTO> configureLevels(int projectionId, int levelNo, List<Leveldto> currentHierarchy) {
        Leveldto lvlDto = CommonLogic.getNextLevel(levelNo, currentHierarchy);
        List<DiscountProjectionResultsDTO> result = new ArrayList<>();
        if (lvlDto != null) {
            List<Leveldto> levelList = CommonLogic.getLevelListDiscount(projectionId, lvlDto.getHierarchyIndicator(), lvlDto.getLevelNo(), lvlDto.getHierarchyId());
            for (Leveldto levelDto : levelList) {
                DiscountProjectionResultsDTO dto = new DiscountProjectionResultsDTO();
                dto.setLevelNo(levelDto.getLevelNo());
                dto.setGroup(levelDto.getRelationshipLevelValue());
                dto.setHierarchylevelId(String.valueOf(levelDto.getHierarchyLevelDefnId()));
                dto.setHierarchySid(levelDto.getRelationShipBuilderId());
                dto.setIsParent(Constant.STRING_ONE);
                result.add(dto);
            }
        }
        return result;
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
    public List<DiscountProjectionResultsDTO> getPivotHierarchy(ProjectionSelectionDTO projSelDTO, List<Integer> startAndEndPeriods) throws SystemException {
        List<DiscountProjectionResultsDTO> discountProjList = new ArrayList<>();
        int projectionMasterId = projSelDTO.getProjectionId();
        String selectedView = projSelDTO.getView();
        String level = String.valueOf(projSelDTO.getTreeLevelNo());
        String hierarchyNo = String.valueOf(projSelDTO.getHierarchyNo());
        List ccpId = null;
        if (selectedView.equals(Constant.CUSTOMER_SMALL)) {
            ccpId = new NmDiscountImpl().getCCPDetailsID(projectionMasterId, hierarchyNo, level);
        }
        if (selectedView.equals(Constant.PRODUCT_LABEL)) {
            ccpId = new NmDiscountImpl().getCCPDetailsIDForProductHierarchy(projectionMasterId, hierarchyNo, level);
        }
        if (selectedView.equals(Constant.CUSTOM_LABEL)) {
            projSelDTO.setIsCustomHierarchy(true);
            String customQuery = CommonLogic.getCustomCCPQuery(projSelDTO);
            List<Object> list = (List<Object>) executeSelectQuery(customQuery, null, null);
            if (list != null && !list.isEmpty()) {
                ccpId = new ArrayList();
                for (int i = 0; i < list.size(); i++) {
                    Object[] obj = (Object[]) list.get(i);
                    String id = String.valueOf(obj[1]);
                    if (!id.equals(Constant.NULL)) {
                        int ccp = Integer.parseInt(id);
                        ccpId.add(ccp);
                    }
                }
            }
        }
        if (ccpId != null && !ccpId.isEmpty()) {
            List<Integer> proDetailsSid;
            final DynamicQuery projectiondetailsDynamicQuery = ProjectionDetailsLocalServiceUtil.dynamicQuery();
            projectiondetailsDynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_MASTER_SID, projectionMasterId));
            projectiondetailsDynamicQuery.add(RestrictionsFactoryUtil.in(Constant.CCP_DETAILS_SID, ccpId));
            final ProjectionList projList = ProjectionFactoryUtil.projectionList();
            projList.add(ProjectionFactoryUtil.property(Constant.PROJECTION_DETAILS_SID));
            projectiondetailsDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projList));
            proDetailsSid = ProjectionDetailsLocalServiceUtil.dynamicQuery(projectiondetailsDynamicQuery);
            if (proDetailsSid != null && !proDetailsSid.isEmpty()) {
                String userId = String.valueOf(projSelDTO.getUserId());
                String sessionId = String.valueOf(projSelDTO.getSessionId());
                int user = Integer.parseInt(userId);
                int session = Integer.parseInt(sessionId);
                String frequ = String.valueOf(projSelDTO.getFrequency());
                List<String> discountList;
                List<String> tmpList = new ArrayList<>();
                discountList = projSelDTO.getDiscountNameList();
                String discountString = getDiscountName(discountList);
                List list = new NmDiscountImpl().getSubDiscount(proDetailsSid, frequ, discountString, startAndEndPeriods, user, session);
                if (list != null && !list.isEmpty()) {
                    if (frequ.equals(Constant.ANNUALLY)) {
                        double actualSales = 0;
                        double actualAmount = 0;
                        double projectedSales = 0;
                        double projectedAmount = 0;
                        tmpList.addAll(discountList);
                        String commonColumn;
                        List<String> periodList = new ArrayList<>(projSelDTO.getPeriodList());
                        DiscountProjectionResultsDTO discountDto = new DiscountProjectionResultsDTO();
                        Object[] object = (Object[]) list.get(0);
                        String currentDiscount = String.valueOf(object[NumericConstants.EIGHT]);
                        tmpList.remove(currentDiscount);
                        int currentYear = (Integer) object[0];
                        commonColumn = currentDiscount.replace(" ", StringUtils.EMPTY);
                        discountDto.setGroup(String.valueOf(currentYear));
                        discountDto.setIsParent(ZERO_SYMBOL);
                        discountDto.setParent(0);
                        if (object[NumericConstants.TWO] != null) {
                            Double aSales = DataTypeConverter.convertObjectToDouble(object[NumericConstants.TWO]);
                            actualSales = actualSales + aSales;
                        }
                        if (object[NumericConstants.THREE] != null) {
                            Double aAmount = DataTypeConverter.convertObjectToDouble(object[NumericConstants.THREE]);
                            actualAmount = actualAmount + aAmount;
                        }
                        if (object[NumericConstants.FOUR] != null) {
                            Double pSales = DataTypeConverter.convertObjectToDouble(object[NumericConstants.FOUR]);
                            projectedSales = projectedSales + pSales;
                        }
                        if (object[NumericConstants.FIVE] != null) {
                            Double pAmount = DataTypeConverter.convertObjectToDouble(object[NumericConstants.FIVE]);
                            projectedAmount = projectedAmount + pAmount;
                        }

                        if (list.size() == 1) {
                            if (periodList.contains(discountDto.getGroup())) {
                                Double arate = actualAmount / actualSales;
                                if (arate.isNaN()) {
                                    arate = 0.0;
                                }
                                arate = arate * NumericConstants.HUNDRED;
                                String actRate = String.valueOf(arate);
                                discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                Double actualAmt = arate * actualSales;
                                actualAmt = actualAmt / NumericConstants.HUNDRED;
                                if (actualAmt.isNaN()) {
                                    actualAmt = 0.0;
                                }
                                String actAmt = String.valueOf(actualAmt);
                                discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                                Double prate = projectedAmount / projectedSales;
                                if (prate.isNaN() || prate.isInfinite()) {
                                    prate = 0.0;
                                }
                                prate = prate * NumericConstants.HUNDRED;
                                String proRate = String.valueOf(prate);
                                discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                Double projectedAmtAmt = prate * projectedSales;
                                projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                                if (projectedAmtAmt.isNaN()) {
                                    projectedAmtAmt = 0.0;
                                }
                                String proAmount = String.valueOf(projectedAmtAmt);
                                discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                if (!tmpList.isEmpty()) {
                                    for (int j = 0; j < tmpList.size(); j++) {
                                        String column = tmpList.get(j);
                                        commonColumn = column.replace(" ", StringUtils.EMPTY);
                                        String columns = commonColumn + ACTUALSRATE;
                                        discountDto.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
                                        String columns1 = commonColumn + ACTUALSAMOUNT;
                                        discountDto.addStringProperties(columns1, getFormattedValue(CUR_ZERO, Constant.NULL));
                                        String columns2 = commonColumn + PROJECTIONSAMOUNT;
                                        discountDto.addStringProperties(columns2, getFormattedValue(CUR_ZERO, Constant.NULL));
                                        String columns3 = commonColumn + PROJECTIONSRATE;
                                        discountDto.addStringProperties(columns3, getFormattedValue(CUR_ZERO, Constant.NULL));
                                    }
                                }
                                discountProjList.add(discountDto);

                                periodList.remove(discountDto.getGroup());
                            }
                        } else {
                            for (int i = 1; i < list.size(); i++) {
                                Object[] obj = (Object[]) list.get(i);
                                String selectedDiscount = String.valueOf(obj[NumericConstants.EIGHT]);
                                tmpList.remove(selectedDiscount);
                                selectedDiscount = selectedDiscount.replace(" ", StringUtils.EMPTY);
                                int selectedYear = (Integer) obj[0];
                                if (currentYear == selectedYear) {
                                    if (currentDiscount.equalsIgnoreCase(selectedDiscount)) {
                                        if (obj[NumericConstants.TWO] != null) {
                                            Double aSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.TWO]);
                                            actualSales = actualSales + aSales;
                                        }
                                        if (obj[NumericConstants.THREE] != null) {
                                            Double aAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.THREE]);
                                            actualAmount = actualAmount + aAmount;
                                        }
                                        if (obj[NumericConstants.FOUR] != null) {
                                            Double pSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FOUR]);
                                            projectedSales = projectedSales + pSales;
                                        }
                                        if (obj[NumericConstants.FIVE] != null) {
                                            Double pAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FIVE]);
                                            projectedAmount = projectedAmount + pAmount;
                                        }
                                    } else {
                                        Double arate = actualAmount / actualSales;
                                        if (arate.isNaN()) {
                                            arate = 0.0;
                                        }
                                        arate = arate * NumericConstants.HUNDRED;
                                        String actRate = String.valueOf(arate);
                                        discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double actualAmt = arate * actualSales;
                                        actualAmt = actualAmt / NumericConstants.HUNDRED;
                                        if (actualAmt.isNaN()) {
                                            actualAmt = 0.0;
                                        }
                                        String actAmt = String.valueOf(actualAmt);
                                        discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                                        Double prate = projectedAmount / projectedSales;
                                        if (prate.isNaN() || prate.isInfinite()) {
                                            prate = 0.0;
                                        }
                                        prate = prate * NumericConstants.HUNDRED;
                                        String proRate = String.valueOf(prate);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double projectedAmtAmt = prate * projectedSales;
                                        projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                                        if (projectedAmtAmt.isNaN()) {
                                            projectedAmtAmt = 0.0;
                                        }
                                        String proAmount = String.valueOf(projectedAmtAmt);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                        actualSales = 0;
                                        actualAmount = 0;
                                        projectedSales = 0;
                                        projectedAmount = 0;
                                        tmpList.remove(selectedDiscount);
                                        commonColumn = selectedDiscount.replace(" ", StringUtils.EMPTY);
                                        currentDiscount = selectedDiscount;
                                        if (obj[NumericConstants.TWO] != null) {
                                            Double aSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.TWO]);
                                            actualSales = actualSales + aSales;
                                        }
                                        if (obj[NumericConstants.THREE] != null) {
                                            Double aAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.THREE]);
                                            actualAmount = actualAmount + aAmount;
                                        }
                                        if (obj[NumericConstants.FOUR] != null) {
                                            Double pSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FOUR]);
                                            projectedSales = projectedSales + pSales;
                                        }
                                        if (obj[NumericConstants.FIVE] != null) {
                                            Double pAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FIVE]);
                                            projectedAmount = projectedAmount + pAmount;
                                        }
                                    }
                                } else {
                                    if (periodList.contains(discountDto.getGroup())) {
                                        Double arate = actualAmount / actualSales;
                                        if (arate.isNaN()) {
                                            arate = 0.0;
                                        }
                                        arate = arate * NumericConstants.HUNDRED;
                                        String actRate = String.valueOf(arate);
                                        discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double actualAmt = arate * actualSales;
                                        actualAmt = actualAmt / NumericConstants.HUNDRED;
                                        if (actualAmt.isNaN()) {
                                            actualAmt = 0.0;
                                        }
                                        String actAmt = String.valueOf(actualAmt);
                                        discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                                        Double prate = projectedAmount / projectedSales;
                                        if (prate.isNaN() || prate.isInfinite()) {
                                            prate = 0.0;
                                        }
                                        prate = prate * NumericConstants.HUNDRED;
                                        String proRate = String.valueOf(prate);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double projectedAmtAmt = prate * projectedSales;
                                        projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                                        if (projectedAmtAmt.isNaN()) {
                                            projectedAmtAmt = 0.0;
                                        }
                                        String proAmount = String.valueOf(projectedAmtAmt);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);

                                        if (!tmpList.isEmpty()) {
                                            for (int j = 0; j < tmpList.size(); j++) {
                                                String column = tmpList.get(j);
                                                commonColumn = column.replace(" ", StringUtils.EMPTY);
                                                String columns = commonColumn + ACTUALSRATE;
                                                discountDto.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
                                                String columns1 = commonColumn + ACTUALSAMOUNT;
                                                discountDto.addStringProperties(columns1, getFormattedValue(CUR_ZERO, Constant.NULL));
                                                String columns2 = commonColumn + PROJECTIONSAMOUNT;
                                                discountDto.addStringProperties(columns2, getFormattedValue(CUR_ZERO, Constant.NULL));
                                                String columns3 = commonColumn + PROJECTIONSRATE;
                                                discountDto.addStringProperties(columns3, getFormattedValue(CUR_ZERO, Constant.NULL));
                                            }
                                        }
                                        discountProjList.add(discountDto);
                                        periodList.remove(discountDto.getGroup());
                                        tmpList.clear();
                                        tmpList.addAll(discountList);
                                    }
                                    actualSales = 0;
                                    actualAmount = 0;
                                    projectedSales = 0;
                                    projectedAmount = 0;
                                    discountDto = new DiscountProjectionResultsDTO();
                                    currentDiscount = selectedDiscount;
                                    discountDto.setGroup(String.valueOf(selectedYear));
                                    currentYear = selectedYear;
                                    tmpList.remove(selectedDiscount);
                                    commonColumn = selectedDiscount.replace(" ", StringUtils.EMPTY);
                                    discountDto.setIsParent(ZERO_SYMBOL);
                                    discountDto.setParent(0);
                                    if (obj[NumericConstants.TWO] != null) {
                                        Double aSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.TWO]);
                                        actualSales = actualSales + aSales;
                                    }
                                    if (obj[NumericConstants.THREE] != null) {
                                        Double aAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.THREE]);
                                        actualAmount = actualAmount + aAmount;
                                    }
                                    if (obj[NumericConstants.FOUR] != null) {
                                        Double pSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FOUR]);
                                        projectedSales = projectedSales + pSales;
                                    }
                                    if (obj[NumericConstants.FIVE] != null) {
                                        Double pAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FIVE]);
                                        projectedAmount = projectedAmount + pAmount;
                                    }
                                }
                                    if ((i == list.size() - 1) && (periodList.contains(discountDto.getGroup()))) {
                                        Double arate = actualAmount / actualSales;
                                        if (arate.isNaN()) {
                                            arate = 0.0;
                                        }
                                        arate = arate * NumericConstants.HUNDRED;
                                        String actRate = String.valueOf(arate);
                                        discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double actualAmt = arate * actualSales;
                                        actualAmt = actualAmt / NumericConstants.HUNDRED;
                                        if (actualAmt.isNaN()) {
                                            actualAmt = 0.0;
                                        }
                                        String actAmt = String.valueOf(actualAmt);
                                        discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                                        Double prate = projectedAmount / projectedSales;
                                        if (prate.isNaN() || prate.isInfinite()) {
                                            prate = 0.0;
                                        }
                                        prate = prate * NumericConstants.HUNDRED;
                                        String proRate = String.valueOf(prate);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double projectedAmtAmt = prate * projectedSales;
                                        projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                                        if (projectedAmtAmt.isNaN()) {
                                            projectedAmtAmt = 0.0;
                                        }
                                        String proAmount = String.valueOf(projectedAmtAmt);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                        if (!tmpList.isEmpty()) {
                                            for (int j = 0; j < tmpList.size(); j++) {
                                                String column = tmpList.get(j);
                                                commonColumn = column.replace(" ", StringUtils.EMPTY);
                                                String columns = commonColumn + ACTUALSRATE;
                                                discountDto.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
                                                String columns1 = commonColumn + ACTUALSAMOUNT;
                                                discountDto.addStringProperties(columns1, getFormattedValue(CUR_ZERO, Constant.NULL));
                                                String columns2 = commonColumn + PROJECTIONSAMOUNT;
                                                discountDto.addStringProperties(columns2, getFormattedValue(CUR_ZERO, Constant.NULL));
                                                String columns3 = commonColumn + PROJECTIONSRATE;
                                                discountDto.addStringProperties(columns3, getFormattedValue(CUR_ZERO, Constant.NULL));
                                            }
                                        }
                                        discountProjList.add(discountDto);
                                        periodList.remove(discountDto.getGroup());
                                    }
                            }
                        }
                        if (!periodList.isEmpty() && !projSelDTO.getDiscountNameList().isEmpty()) {
                            for (int i = 0; i < periodList.size(); i++) {
                                DiscountProjectionResultsDTO projDTO = new DiscountProjectionResultsDTO();
                                projDTO.setParent(0);
                                projDTO.setProjectionTotal(1);
                                projDTO.setGroup(String.valueOf(periodList.get(i)));
                                for (String discount : projSelDTO.getDiscountNameList()) {
                                    String discountRate = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns = discountRate + ACTUALSRATE;
                                    projDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));

                                    String discountActualAmount = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns1 = discountActualAmount + ACTUALSAMOUNT;
                                    projDTO.addStringProperties(columns1, getFormattedValue(CUR_ZERO, Constant.NULL));

                                    String discountProjectionAmount = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns2 = discountProjectionAmount + PROJECTIONSAMOUNT;
                                    projDTO.addStringProperties(columns2, getFormattedValue(CUR_ZERO, Constant.NULL));

                                    String discountProjectionRate = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns3 = discountProjectionRate + PROJECTIONSRATE;
                                    projDTO.addStringProperties(columns3, getFormattedValue(CUR_ZERO, Constant.NULL));
                                }
                                discountProjList.add(projDTO);
                            }
                        }
                    }

                    if (frequ.equals(QUARTERLY.getConstant())) {
                        double actualSales = 0;
                        double actualAmount = 0;
                        double projectedSales = 0;
                        double projectedAmount = 0;
                        tmpList.addAll(discountList);
                        List<String> periodList = new ArrayList<>(projSelDTO.getPeriodList());
                        String commonColumn;
                        DiscountProjectionResultsDTO discountDto = new DiscountProjectionResultsDTO();
                        Object[] object = (Object[]) list.get(0);
                        String currentDiscount = String.valueOf(object[NumericConstants.EIGHT]);
                        tmpList.remove(currentDiscount);
                        int currentYear = (Integer) object[0];
                        int currentQuarter = (Integer) object[NumericConstants.SIX];
                        currentDiscount = currentDiscount.replace(" ", StringUtils.EMPTY);
                        commonColumn = currentDiscount;
                        discountDto.setGroup(Constant.Q + object[1] + " " + object[0]);
                        discountDto.setIsParent(ZERO_SYMBOL);
                        if (object[NumericConstants.TWO] != null) {
                            Double aSales = DataTypeConverter.convertObjectToDouble(object[NumericConstants.TWO]);
                            actualSales = actualSales + aSales;
                        }
                        if (object[NumericConstants.THREE] != null) {
                            Double aAmount = DataTypeConverter.convertObjectToDouble(object[NumericConstants.THREE]);
                            actualAmount = actualAmount + aAmount;
                        }
                        if (object[NumericConstants.FOUR] != null) {
                            Double pSales = DataTypeConverter.convertObjectToDouble(object[NumericConstants.FOUR]);
                            projectedSales = projectedSales + pSales;
                        }
                        if (object[NumericConstants.FIVE] != null) {
                            Double pAmount = DataTypeConverter.convertObjectToDouble(object[NumericConstants.FIVE]);
                            projectedAmount = projectedAmount + pAmount;
                        }
                        if (list.size() == 1) {
                            Double arate = actualAmount / actualSales;
                            if (arate.isNaN()) {
                                arate = 0.0;
                            }
                            arate = arate * NumericConstants.HUNDRED;
                            String actRate = String.valueOf(arate);
                            discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                            Double actualAmt = arate * actualSales;
                            actualAmt = actualAmt / NumericConstants.HUNDRED;
                            if (actualAmt.isNaN()) {
                                actualAmt = 0.0;
                            }
                            String actAmt = String.valueOf(actualAmt);
                            discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                            Double prate = projectedAmount / projectedSales;
                            if (prate.isNaN() || prate.isInfinite()) {
                                prate = 0.0;
                            }
                            prate = prate * NumericConstants.HUNDRED;
                            String proRate = String.valueOf(prate);
                            discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                            Double projectedAmtAmt = prate * projectedSales;
                            projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                            if (projectedAmtAmt.isNaN()) {
                                projectedAmtAmt = 0.0;
                            }
                            String proAmount = String.valueOf(projectedAmtAmt);
                            discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                            if (!tmpList.isEmpty()) {
                                discountDto = putHyphenForDiscount(tmpList, discountDto);
                            }
                            discountProjList.add(discountDto);
                            tmpList.clear();
                            tmpList.addAll(discountList);
                        } else {
                            for (int i = 1; i < list.size(); i++) {
                                Object[] obj = (Object[]) list.get(i);
                                String selectedDiscount = String.valueOf(obj[NumericConstants.EIGHT]);
                                tmpList.remove(selectedDiscount);
                                selectedDiscount = selectedDiscount.replace(" ", StringUtils.EMPTY);
                                int selectedYear = (Integer) obj[0];
                                int selectedQuarter = (Integer) obj[NumericConstants.SIX];
                                if (currentYear == selectedYear && currentQuarter == selectedQuarter) {
                                    if (currentDiscount.equalsIgnoreCase(selectedDiscount)) {
                                        if (obj[NumericConstants.TWO] != null) {
                                            Double aSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.TWO]);
                                            actualSales = actualSales + aSales;
                                        }
                                        if (obj[NumericConstants.THREE] != null) {
                                            Double aAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.THREE]);
                                            actualAmount = actualAmount + aAmount;
                                        }
                                        if (obj[NumericConstants.FOUR] != null) {
                                            Double pSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FOUR]);
                                            projectedSales = projectedSales + pSales;
                                        }
                                        if (obj[NumericConstants.FIVE] != null) {
                                            Double pAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FIVE]);
                                            projectedAmount = projectedAmount + pAmount;
                                        }
                                    } else {
                                        Double arate = actualAmount / actualSales;
                                        if (arate.isNaN()) {
                                            arate = 0.0;
                                        }
                                        arate = arate * NumericConstants.HUNDRED;
                                        String actRate = String.valueOf(arate);
                                        discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double actualAmt = arate * actualSales;
                                        actualAmt = actualAmt / NumericConstants.HUNDRED;
                                        if (actualAmt.isNaN()) {
                                            actualAmt = 0.0;
                                        }
                                        String actAmt = String.valueOf(actualAmt);
                                        discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                                        Double prate = projectedAmount / projectedSales;
                                        if (prate.isNaN() || prate.isInfinite()) {
                                            prate = 0.0;
                                        }
                                        prate = prate * NumericConstants.HUNDRED;
                                        String proRate = String.valueOf(prate);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double projectedAmtAmt = prate * projectedSales;
                                        projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                                        if (projectedAmtAmt.isNaN()) {
                                            projectedAmtAmt = 0.0;
                                        }
                                        String proAmount = String.valueOf(projectedAmtAmt);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                        actualSales = 0;
                                        actualAmount = 0;
                                        projectedSales = 0;
                                        projectedAmount = 0;
                                        commonColumn = selectedDiscount;

                                        currentDiscount = selectedDiscount;
                                        if (obj[NumericConstants.TWO] != null) {
                                            Double aSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.TWO]);
                                            actualSales = actualSales + aSales;
                                        }
                                        if (obj[NumericConstants.THREE] != null) {
                                            Double aAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.THREE]);
                                            actualAmount = actualAmount + aAmount;
                                        }
                                        if (obj[NumericConstants.FOUR] != null) {
                                            Double pSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FOUR]);
                                            projectedSales = projectedSales + pSales;
                                        }
                                        if (obj[NumericConstants.FIVE] != null) {
                                            Double pAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FIVE]);
                                            projectedAmount = projectedAmount + pAmount;
                                        }
                                    }
                                } else {
                                    String column = discountDto.getGroup().replace(" ", StringUtils.EMPTY);
                                    if (periodList.contains(String.valueOf(column).replace('Q', 'q'))) {
                                        Double arate = actualAmount / actualSales;
                                        if (arate.isNaN()) {
                                            arate = 0.0;
                                        }
                                        arate = arate * NumericConstants.HUNDRED;
                                        String actRate = String.valueOf(arate);
                                        discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double actualAmt = arate * actualSales;
                                        actualAmt = actualAmt / NumericConstants.HUNDRED;
                                        if (actualAmt.isNaN()) {
                                            actualAmt = 0.0;
                                        }
                                        String actAmt = String.valueOf(actualAmt);
                                        discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                                        Double prate = projectedAmount / projectedSales;
                                        if (prate.isNaN() || prate.isInfinite()) {
                                            prate = 0.0;
                                        }
                                        prate = prate * NumericConstants.HUNDRED;
                                        String proRate = String.valueOf(prate);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double projectedAmtAmt = prate * projectedSales;
                                        projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                                        if (projectedAmtAmt.isNaN()) {
                                            projectedAmtAmt = 0.0;
                                        }
                                        String proAmount = String.valueOf(projectedAmtAmt);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                        if (!tmpList.isEmpty()) {
                                            discountDto = putHyphenForDiscount(tmpList, discountDto);
                                        }
                                        discountProjList.add(discountDto);
                                        periodList.remove(String.valueOf(column).replace('Q', 'q'));
                                        tmpList.clear();
                                        tmpList.addAll(discountList);
                                    }
                                    actualSales = 0;
                                    actualAmount = 0;
                                    projectedSales = 0;
                                    projectedAmount = 0;
                                    discountDto = new DiscountProjectionResultsDTO();
                                    discountDto.setGroup(Constant.Q + obj[1] + " " + obj[0]);
                                    currentYear = selectedYear;
                                    currentQuarter = selectedQuarter;
                                    tmpList.remove(selectedDiscount);
                                    commonColumn = selectedDiscount;
                                    currentDiscount = selectedDiscount;
                                    discountDto.setIsParent(ZERO_SYMBOL);
                                    if (obj[NumericConstants.TWO] != null) {
                                        Double aSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.TWO]);
                                        actualSales = actualSales + aSales;
                                    }
                                    if (obj[NumericConstants.THREE] != null) {
                                        Double aAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.THREE]);
                                        actualAmount = actualAmount + aAmount;
                                    }
                                    if (obj[NumericConstants.FOUR] != null) {
                                        Double pSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FOUR]);
                                        projectedSales = projectedSales + pSales;
                                    }
                                    if (obj[NumericConstants.FIVE] != null) {
                                        Double pAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FIVE]);
                                        projectedAmount = projectedAmount + pAmount;
                                    }
                                }
                                if (i == list.size() - 1) {
                                    String column = discountDto.getGroup().replace(" ", StringUtils.EMPTY);
                                    if (periodList.contains(String.valueOf(column).replace('Q', 'q'))) {
                                        Double arate = actualAmount / actualSales;
                                        if (arate.isNaN()) {
                                            arate = 0.0;
                                        }
                                        arate = arate * NumericConstants.HUNDRED;
                                        String actRate = String.valueOf(arate);
                                        discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double actualAmt = arate * actualSales;
                                        actualAmt = actualAmt / NumericConstants.HUNDRED;
                                        if (actualAmt.isNaN()) {
                                            actualAmt = 0.0;
                                        }
                                        String actAmt = String.valueOf(actualAmt);
                                        discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                                        Double prate = projectedAmount / projectedSales;
                                        if (prate.isNaN() || prate.isInfinite()) {
                                            prate = 0.0;
                                        }
                                        prate = prate * NumericConstants.HUNDRED;
                                        String proRate = String.valueOf(prate);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double projectedAmtAmt = prate * projectedSales;
                                        projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                                        if (projectedAmtAmt.isNaN()) {
                                            projectedAmtAmt = 0.0;
                                        }
                                        String proAmount = String.valueOf(projectedAmtAmt);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                        if (!tmpList.isEmpty()) {
                                            discountDto = putHyphenForDiscount(tmpList, discountDto);
                                        }
                                        discountProjList.add(discountDto);
                                        tmpList.clear();
                                        tmpList.addAll(discountList);
                                        periodList.remove(String.valueOf(column).replace('Q', 'q'));
                                    }
                                }
                            }
                        }
                        if (!periodList.isEmpty() && !projSelDTO.getDiscountNameList().isEmpty()) {
                            for (int i = 0; i < periodList.size(); i++) {
                                DiscountProjectionResultsDTO projDTO = new DiscountProjectionResultsDTO();
                                projDTO.setParent(0);
                                projDTO.setProjectionTotal(1);
                                String group = String.valueOf(periodList.get(i).replace('q', 'Q'));
                                String year = group.substring(group.length() - NumericConstants.FOUR, group.length());
                                String frequency = group.replace(year, StringUtils.EMPTY);
                                group = frequency + " " + year;
                                projDTO.setGroup(group);
                                for (String discount : projSelDTO.getDiscountNameList()) {
                                    String discountRate = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns = discountRate + ACTUALSRATE;
                                    projDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
                                    String discountActualAmount = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns1 = discountActualAmount + ACTUALSAMOUNT;
                                    projDTO.addStringProperties(columns1, getFormattedValue(CUR_ZERO, Constant.NULL));
                                    String discountProjectionAmount = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns2 = discountProjectionAmount + PROJECTIONSAMOUNT;
                                    projDTO.addStringProperties(columns2, getFormattedValue(CUR_ZERO, Constant.NULL));

                                    String discountProjectionRate = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns3 = discountProjectionRate + PROJECTIONSRATE;
                                    projDTO.addStringProperties(columns3, getFormattedValue(CUR_ZERO, Constant.NULL));
                                }
                                discountProjList.add(projDTO);
                            }
                        }
                    }
                    if (frequ.equals(Constant.SEMI_ANNUALLY)) {
                        double actualSales = 0;
                        double actualAmount = 0;
                        double projectedSales = 0;
                        double projectedAmount = 0;
                        String commonColumn;
                        List<String> periodList = new ArrayList<>(projSelDTO.getPeriodList());
                        tmpList.addAll(discountList);
                        DiscountProjectionResultsDTO discountDto = new DiscountProjectionResultsDTO();
                        Object[] object = (Object[]) list.get(0);
                        String currentDiscount = String.valueOf(object[NumericConstants.EIGHT]);
                        tmpList.remove(currentDiscount);
                        int currentYear = (Integer) object[0];
                        int currentQuarter = (Integer) object[NumericConstants.SIX];
                        commonColumn = currentDiscount.replace(" ", StringUtils.EMPTY);
                        discountDto.setGroup(Constant.S + object[1] + " " + object[0]);
                        discountDto.setIsParent(ZERO_SYMBOL);
                        if (object[NumericConstants.TWO] != null) {
                            Double aSales = DataTypeConverter.convertObjectToDouble(object[NumericConstants.TWO]);
                            actualSales = actualSales + aSales;
                        }
                        if (object[NumericConstants.THREE] != null) {
                            Double aAmount = DataTypeConverter.convertObjectToDouble(object[NumericConstants.THREE]);
                            actualAmount = actualAmount + aAmount;
                        }
                        if (object[NumericConstants.FOUR] != null) {
                            Double pSales = DataTypeConverter.convertObjectToDouble(object[NumericConstants.FOUR]);
                            projectedSales = projectedSales + pSales;
                        }
                        if (object[NumericConstants.FIVE] != null) {
                            Double pAmount = DataTypeConverter.convertObjectToDouble(object[NumericConstants.FIVE]);
                            projectedAmount = projectedAmount + pAmount;
                        }
                        if (list.size() == 1) {
                            Double arate = actualAmount / actualSales;
                            if (arate.isNaN()) {
                                arate = 0.0;
                            }
                            arate = arate * NumericConstants.HUNDRED;
                            String actRate = String.valueOf(arate);
                            discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                            Double actualAmt = arate * actualSales;
                            actualAmt = actualAmt / NumericConstants.HUNDRED;
                            if (actualAmt.isNaN()) {
                                actualAmt = 0.0;
                            }
                            String actAmt = String.valueOf(actualAmt);
                            discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                            Double prate = projectedAmount / projectedSales;
                            if (prate.isNaN() || prate.isInfinite()) {
                                prate = 0.0;
                            }
                            prate = prate * NumericConstants.HUNDRED;
                            String proRate = String.valueOf(prate);
                            discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                            Double projectedAmtAmt = prate * projectedSales;
                            projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                            if (projectedAmtAmt.isNaN()) {
                                projectedAmtAmt = 0.0;
                            }
                            String proAmount = String.valueOf(projectedAmtAmt);
                            discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                            if (!tmpList.isEmpty()) {
                                discountDto = putHyphenForDiscount(tmpList, discountDto);
                            }
                            discountProjList.add(discountDto);
                            tmpList.clear();
                            tmpList.addAll(discountList);
                        } else {
                            for (int i = 1; i < list.size(); i++) {
                                Object[] obj = (Object[]) list.get(i);
                                String selectedDiscount = String.valueOf(obj[NumericConstants.EIGHT]);
                                int selectedYear = (Integer) obj[0];
                                int selectedQuarter = (Integer) obj[NumericConstants.SIX];
                                if (currentYear == selectedYear && currentQuarter == selectedQuarter) {
                                    if (currentDiscount.equalsIgnoreCase(selectedDiscount)) {
                                        if (obj[NumericConstants.TWO] != null) {
                                            Double aSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.TWO]);
                                            actualSales = actualSales + aSales;
                                        }
                                        if (obj[NumericConstants.THREE] != null) {
                                            Double aAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.THREE]);
                                            actualAmount = actualAmount + aAmount;
                                        }
                                        if (obj[NumericConstants.FOUR] != null) {
                                            Double pSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FOUR]);
                                            projectedSales = projectedSales + pSales;
                                        }
                                        if (obj[NumericConstants.FIVE] != null) {
                                            Double pAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FIVE]);
                                            projectedAmount = projectedAmount + pAmount;
                                        }
                                    } else {
                                        Double arate = actualAmount / actualSales;
                                        if (arate.isNaN()) {
                                            arate = 0.0;
                                        }
                                        arate = arate * NumericConstants.HUNDRED;
                                        String actRate = String.valueOf(arate);
                                        discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double actualAmt = arate * actualSales;
                                        actualAmt = actualAmt / NumericConstants.HUNDRED;
                                        if (actualAmt.isNaN()) {
                                            actualAmt = 0.0;
                                        }
                                        String actAmt = String.valueOf(actualAmt);
                                        discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                                        Double prate = projectedAmount / projectedSales;
                                        if (prate.isNaN() || prate.isInfinite()) {
                                            prate = 0.0;
                                        }
                                        prate = prate * NumericConstants.HUNDRED;
                                        String proRate = String.valueOf(prate);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double projectedAmtAmt = prate * projectedSales;
                                        projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                                        if (projectedAmtAmt.isNaN()) {
                                            projectedAmtAmt = 0.0;
                                        }
                                        String proAmount = String.valueOf(projectedAmtAmt);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                        actualSales = 0;
                                        actualAmount = 0;
                                        projectedSales = 0;
                                        projectedAmount = 0;
                                        currentDiscount = selectedDiscount;
                                        tmpList.remove(selectedDiscount);
                                        commonColumn = selectedDiscount.replace(" ", StringUtils.EMPTY);
                                        if (obj[NumericConstants.TWO] != null) {
                                            Double aSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.TWO]);
                                            actualSales = actualSales + aSales;
                                        }
                                        if (obj[NumericConstants.THREE] != null) {
                                            Double aAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.THREE]);
                                            actualAmount = actualAmount + aAmount;
                                        }
                                        if (obj[NumericConstants.FOUR] != null) {
                                            Double pSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FOUR]);
                                            projectedSales = projectedSales + pSales;
                                        }
                                        if (obj[NumericConstants.FIVE] != null) {
                                            Double pAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FIVE]);
                                            projectedAmount = projectedAmount + pAmount;
                                        }
                                    }
                                } else {
                                    String column = discountDto.getGroup().replace(" ", StringUtils.EMPTY);
                                    if (periodList.contains(String.valueOf(column).replace('S', 's'))) {
                                        Double arate = actualAmount / actualSales;
                                        if (arate.isNaN()) {
                                            arate = 0.0;
                                        }
                                        arate = arate * NumericConstants.HUNDRED;
                                        String actRate = String.valueOf(arate);
                                        discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double actualAmt = arate * actualSales;
                                        actualAmt = actualAmt / NumericConstants.HUNDRED;
                                        if (actualAmt.isNaN()) {
                                            actualAmt = 0.0;
                                        }
                                        String actAmt = String.valueOf(actualAmt);
                                        discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                                        Double prate = projectedAmount / projectedSales;
                                        if (prate.isNaN() || prate.isInfinite()) {
                                            prate = 0.0;
                                        }
                                        prate = prate * NumericConstants.HUNDRED;
                                        String proRate = String.valueOf(prate);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double projectedAmtAmt = prate * projectedSales;
                                        projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                                        if (projectedAmtAmt.isNaN()) {
                                            projectedAmtAmt = 0.0;
                                        }
                                        String proAmount = String.valueOf(projectedAmtAmt);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                        if (!tmpList.isEmpty()) {
                                            discountDto = putHyphenForDiscount(tmpList, discountDto);
                                        }
                                        discountProjList.add(discountDto);
                                        periodList.remove(String.valueOf(column).replace('S', 's'));
                                        tmpList.clear();
                                        tmpList.addAll(discountList);
                                    }
                                    actualSales = 0;
                                    actualAmount = 0;
                                    projectedSales = 0;
                                    projectedAmount = 0;
                                    discountDto = new DiscountProjectionResultsDTO();
                                    discountDto.setGroup(Constant.S + obj[1] + " " + obj[0]);
                                    currentYear = selectedYear;
                                    currentQuarter = selectedQuarter;
                                    currentDiscount = selectedDiscount;
                                    tmpList.remove(selectedDiscount);
                                    commonColumn = selectedDiscount.replace(" ", StringUtils.EMPTY);
                                    discountDto.setIsParent(ZERO_SYMBOL);
                                    if (obj[NumericConstants.TWO] != null) {
                                        Double aSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.TWO]);
                                        actualSales = actualSales + aSales;
                                    }
                                    if (obj[NumericConstants.THREE] != null) {
                                        Double aAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.THREE]);
                                        actualAmount = actualAmount + aAmount;
                                    }
                                    if (obj[NumericConstants.FOUR] != null) {
                                        Double pSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FOUR]);
                                        projectedSales = projectedSales + pSales;
                                    }
                                    if (obj[NumericConstants.FIVE] != null) {
                                        Double pAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FIVE]);
                                        projectedAmount = projectedAmount + pAmount;
                                    }
                                }
                                if (i == list.size() - 1) {
                                    String column = discountDto.getGroup().replace(" ", StringUtils.EMPTY);
                                    if (periodList.contains(String.valueOf(column).replace('S', 's'))) {
                                        Double arate = actualAmount / actualSales;
                                        if (arate.isNaN()) {
                                            arate = 0.0;
                                        }
                                        arate = arate * NumericConstants.HUNDRED;
                                        String actRate = String.valueOf(arate);
                                        discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double actualAmt = arate * actualSales;
                                        actualAmt = actualAmt / NumericConstants.HUNDRED;
                                        if (actualAmt.isNaN()) {
                                            actualAmt = 0.0;
                                        }
                                        String actAmt = String.valueOf(actualAmt);
                                        discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                                        Double prate = projectedAmount / projectedSales;
                                        if (prate.isNaN() || prate.isInfinite()) {
                                            prate = 0.0;
                                        }
                                        prate = prate * NumericConstants.HUNDRED;
                                        String proRate = String.valueOf(prate);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double projectedAmtAmt = prate * projectedSales;
                                        projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                                        if (projectedAmtAmt.isNaN()) {
                                            projectedAmtAmt = 0.0;
                                        }
                                        String proAmount = String.valueOf(projectedAmtAmt);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                        if (!tmpList.isEmpty()) {
                                            discountDto = putHyphenForDiscount(tmpList, discountDto);
                                        }
                                        discountProjList.add(discountDto);
                                        periodList.remove(String.valueOf(column).replace('S', 's'));

                                    }
                                }
                            }
                        }
                        if (!periodList.isEmpty() && !projSelDTO.getDiscountNameList().isEmpty()) {
                            for (int i = 0; i < periodList.size(); i++) {
                                DiscountProjectionResultsDTO projDTO = new DiscountProjectionResultsDTO();
                                projDTO.setParent(0);
                                projDTO.setProjectionTotal(1);
                                String group = String.valueOf(periodList.get(i).replace('s', 'S'));
                                String year = group.substring(group.length() - NumericConstants.FOUR, group.length());
                                String frequency = group.replace(year, StringUtils.EMPTY);
                                group = frequency + " " + year;
                                projDTO.setGroup(group);
                                for (String discount : projSelDTO.getDiscountNameList()) {
                                    String discountRate = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns = discountRate + ACTUALSRATE;
                                    projDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));

                                    String discountActualAmount = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns1 = discountActualAmount + ACTUALSAMOUNT;
                                    projDTO.addStringProperties(columns1, getFormattedValue(CUR_ZERO, Constant.NULL));

                                    String discountProjectionAmount = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns2 = discountProjectionAmount + PROJECTIONSAMOUNT;
                                    projDTO.addStringProperties(columns2, getFormattedValue(CUR_ZERO, Constant.NULL));

                                    String discountProjectionRate = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns3 = discountProjectionRate + PROJECTIONSRATE;
                                    projDTO.addStringProperties(columns3, getFormattedValue(CUR_ZERO, Constant.NULL));
                                }
                                discountProjList.add(projDTO);
                            }
                        }
                    }
                    if (frequ.equals(Constant.MONTHLY)) {
                        double actualSales = 0;
                        double actualAmount = 0;
                        double projectedSales = 0;
                        double projectedAmount = 0;
                        loadMap();
                        List<String> periodList = new ArrayList<>(projSelDTO.getPeriodList());
                        String commonColumn;
                        tmpList.addAll(discountList);
                        DiscountProjectionResultsDTO discountDto = new DiscountProjectionResultsDTO();
                        Object[] object = (Object[]) list.get(0);
                        String currentDiscount = String.valueOf(object[NumericConstants.EIGHT]);
                        int currentYear = (Integer) object[0];
                        int currentMonth = (Integer) object[NumericConstants.SIX];
                        tmpList.remove(currentDiscount);
                        commonColumn = currentDiscount.replace(" ", StringUtils.EMPTY);
                        discountDto.setGroup(CommonUtils.BUSINESS_PROCESS_INDICATOR_MANDATED + object[1] + " " + object[0]);
                        discountDto.setIsParent(ZERO_SYMBOL);
                        if (object[NumericConstants.TWO] != null) {
                            Double aSales = DataTypeConverter.convertObjectToDouble(object[NumericConstants.TWO]);
                            actualSales = actualSales + aSales;
                        }
                        if (object[NumericConstants.THREE] != null) {
                            Double aAmount = DataTypeConverter.convertObjectToDouble(object[NumericConstants.THREE]);
                            actualAmount = actualAmount + aAmount;
                        }
                        if (object[NumericConstants.FOUR] != null) {
                            Double pSales = DataTypeConverter.convertObjectToDouble(object[NumericConstants.FOUR]);
                            projectedSales = projectedSales + pSales;
                        }
                        if (object[NumericConstants.FIVE] != null) {
                            Double pAmount = DataTypeConverter.convertObjectToDouble(object[NumericConstants.FIVE]);
                            projectedAmount = projectedAmount + pAmount;
                        }
                        if (list.size() == 1) {
                            Double arate = actualAmount / actualSales;
                            if (arate.isNaN()) {
                                arate = 0.0;
                            }
                            arate = arate * NumericConstants.HUNDRED;
                            String actRate = String.valueOf(arate);
                            discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                            Double actualAmt = arate * actualSales;
                            actualAmt = actualAmt / NumericConstants.HUNDRED;
                            if (actualAmt.isNaN()) {
                                actualAmt = 0.0;
                            }
                            String actAmt = String.valueOf(actualAmt);
                            discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                            Double prate = projectedAmount / projectedSales;
                            if (prate.isNaN() || prate.isInfinite()) {
                                prate = 0.0;
                            }
                            prate = prate * NumericConstants.HUNDRED;
                            String proRate = String.valueOf(prate);
                            discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                            Double projectedAmtAmt = prate * projectedSales;
                            projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                            if (projectedAmtAmt.isNaN()) {
                                projectedAmtAmt = 0.0;
                            }
                            String proAmount = String.valueOf(projectedAmtAmt);
                            discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                            if (!tmpList.isEmpty()) {
                                discountDto = putHyphenForDiscount(tmpList, discountDto);
                            }
                            discountProjList.add(discountDto);
                            tmpList.clear();
                            tmpList.addAll(discountList);
                        } else {
                            for (int i = 1; i < list.size(); i++) {
                                Object[] obj = (Object[]) list.get(i);
                                String selectedDiscount = String.valueOf(obj[NumericConstants.EIGHT]);
                                int selectedYear = (Integer) obj[0];
                                int selectedMonth = (Integer) obj[NumericConstants.SIX];
                                if (currentYear == selectedYear && currentMonth == selectedMonth) {
                                    if (currentDiscount.equalsIgnoreCase(selectedDiscount)) {
                                        if (obj[NumericConstants.TWO] != null) {
                                            Double aSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.TWO]);
                                            actualSales = actualSales + aSales;
                                        }
                                        if (obj[NumericConstants.THREE] != null) {
                                            Double aAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.THREE]);
                                            actualAmount = actualAmount + aAmount;
                                        }
                                        if (obj[NumericConstants.FOUR] != null) {
                                            Double pSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FOUR]);
                                            projectedSales = projectedSales + pSales;
                                        }
                                        if (obj[NumericConstants.FIVE] != null) {
                                            Double pAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FIVE]);
                                            projectedAmount = projectedAmount + pAmount;
                                        }
                                    } else {
                                        Double arate = actualAmount / actualSales;
                                        if (arate.isNaN()) {
                                            arate = 0.0;
                                        }
                                        arate = arate * NumericConstants.HUNDRED;
                                        String actRate = String.valueOf(arate);
                                        discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double actualAmt = arate * actualSales;
                                        actualAmt = actualAmt / NumericConstants.HUNDRED;
                                        if (actualAmt.isNaN()) {
                                            actualAmt = 0.0;
                                        }
                                        String actAmt = String.valueOf(actualAmt);
                                        discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                                        Double prate = projectedAmount / projectedSales;
                                        if (prate.isNaN() || prate.isInfinite()) {
                                            prate = 0.0;
                                        }
                                        prate = prate * NumericConstants.HUNDRED;
                                        String proRate = String.valueOf(prate);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double projectedAmtAmt = prate * projectedSales;
                                        projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                                        if (projectedAmtAmt.isNaN()) {
                                            projectedAmtAmt = 0.0;
                                        }
                                        String proAmount = String.valueOf(projectedAmtAmt);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                        actualSales = 0;
                                        actualAmount = 0;
                                        projectedSales = 0;
                                        projectedAmount = 0;
                                        currentDiscount = selectedDiscount;
                                        tmpList.remove(selectedDiscount);
                                        commonColumn = selectedDiscount.replace(" ", StringUtils.EMPTY);
                                        if (obj[NumericConstants.TWO] != null) {
                                            Double aSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.TWO]);
                                            actualSales = actualSales + aSales;
                                        }
                                        if (obj[NumericConstants.THREE] != null) {
                                            Double aAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.THREE]);
                                            actualAmount = actualAmount + aAmount;
                                        }

                                        if (obj[NumericConstants.FOUR] != null) {
                                            Double pSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FOUR]);
                                            projectedSales = projectedSales + pSales;
                                        }
                                        if (obj[NumericConstants.FIVE] != null) {
                                            Double pAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FIVE]);
                                            projectedAmount = projectedAmount + pAmount;
                                        }
                                    }
                                } else {
                                    String column = createColumn(String.valueOf(discountDto.getGroup()).replace(" ", StringUtils.EMPTY));
                                    if (periodList.contains(column)) {
                                        Double arate = actualAmount / actualSales;
                                        if (arate.isNaN()) {
                                            arate = 0.0;
                                        }
                                        arate = arate * NumericConstants.HUNDRED;
                                        String actRate = String.valueOf(arate);
                                        discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double actualAmt = arate * actualSales;
                                        actualAmt = actualAmt / NumericConstants.HUNDRED;
                                        if (actualAmt.isNaN()) {
                                            actualAmt = 0.0;
                                        }
                                        String actAmt = String.valueOf(actualAmt);
                                        discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                                        Double prate = projectedAmount / projectedSales;
                                        if (prate.isNaN() || prate.isInfinite()) {
                                            prate = 0.0;
                                        }
                                        prate = prate * NumericConstants.HUNDRED;
                                        String proRate = String.valueOf(prate);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double projectedAmtAmt = prate * projectedSales;
                                        projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                                        if (projectedAmtAmt.isNaN()) {
                                            projectedAmtAmt = 0.0;
                                        }
                                        String proAmount = String.valueOf(projectedAmtAmt);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                        if (!tmpList.isEmpty()) {
                                            discountDto = putHyphenForDiscount(tmpList, discountDto);
                                        }
                                        discountProjList.add(discountDto);
                                        tmpList.clear();
                                        tmpList.addAll(discountList);
                                        periodList.remove(column);
                                    }
                                    actualSales = 0;
                                    actualAmount = 0;
                                    projectedSales = 0;
                                    projectedAmount = 0;
                                    discountDto = new DiscountProjectionResultsDTO();
                                    discountDto.setGroup(CommonUtils.BUSINESS_PROCESS_INDICATOR_MANDATED + obj[1] + " " + obj[0]);
                                    currentYear = selectedYear;
                                    currentMonth = selectedMonth;
                                    currentDiscount = selectedDiscount;
                                    tmpList.remove(selectedDiscount);
                                    commonColumn = selectedDiscount.replace(" ", StringUtils.EMPTY);
                                    discountDto.setIsParent(ZERO_SYMBOL);
                                    if (obj[NumericConstants.TWO] != null) {
                                        Double aSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.TWO]);
                                        actualSales = actualSales + aSales;
                                    }
                                    if (obj[NumericConstants.THREE] != null) {
                                        Double aAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.THREE]);
                                        actualAmount = actualAmount + aAmount;
                                    }
                                    if (obj[NumericConstants.FOUR] != null) {
                                        Double pSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FOUR]);
                                        projectedSales = projectedSales + pSales;
                                    }
                                    if (obj[NumericConstants.FIVE] != null) {
                                        Double pAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FIVE]);
                                        projectedAmount = projectedAmount + pAmount;
                                    }
                                }
                                if (i == list.size() - 1) {
                                    String column = createColumn(String.valueOf(discountDto.getGroup()).replace(" ", StringUtils.EMPTY));
                                    if (periodList.contains(column)) {
                                        Double arate = actualAmount / actualSales;
                                        if (arate.isNaN()) {
                                            arate = 0.0;
                                        }
                                        arate = arate * NumericConstants.HUNDRED;
                                        String actRate = String.valueOf(arate);
                                        discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double actualAmt = arate * actualSales;
                                        actualAmt = actualAmt / NumericConstants.HUNDRED;
                                        if (actualAmt.isNaN()) {
                                            actualAmt = 0.0;
                                        }
                                        String actAmt = String.valueOf(actualAmt);
                                        discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                                        Double prate = projectedAmount / projectedSales;
                                        if (prate.isNaN() || prate.isInfinite()) {
                                            prate = 0.0;
                                        }
                                        prate = prate * NumericConstants.HUNDRED;
                                        String proRate = String.valueOf(prate);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double projectedAmtAmt = prate * projectedSales;
                                        projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                                        if (projectedAmtAmt.isNaN()) {
                                            projectedAmtAmt = 0.0;
                                        }
                                        String proAmount = String.valueOf(projectedAmtAmt);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                        if (!tmpList.isEmpty()) {
                                            discountDto = putHyphenForDiscount(tmpList, discountDto);
                                        }
                                        discountProjList.add(discountDto);
                                        periodList.remove(column);
                                    }
                                }
                            }
                        }
                        if (!periodList.isEmpty() && !projSelDTO.getDiscountNameList().isEmpty()) {
                            for (int i = 0; i < periodList.size(); i++) {
                                DiscountProjectionResultsDTO projDTO = new DiscountProjectionResultsDTO();
                                loadKeyMap();
                                projDTO.setParent(0);
                                projDTO.setProjectionTotal(1);
                                String group = String.valueOf(periodList.get(i));
                                String month = group.substring(0, NumericConstants.THREE);
                                String year = group.replace(month, StringUtils.EMPTY);
                                String column = valueMap.get(month);

                                String groupColumn = CommonUtils.BUSINESS_PROCESS_INDICATOR_MANDATED + column + " " + year;
                                projDTO.setGroup(String.valueOf(groupColumn));
                                for (String discount : projSelDTO.getDiscountNameList()) {
                                    String discountRate = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns = discountRate + ACTUALSRATE;
                                    projDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));

                                    String discountActualAmount = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns1 = discountActualAmount + ACTUALSAMOUNT;
                                    projDTO.addStringProperties(columns1, getFormattedValue(CUR_ZERO, Constant.NULL));

                                    String discountProjectionAmount = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns2 = discountProjectionAmount + PROJECTIONSAMOUNT;
                                    projDTO.addStringProperties(columns2, getFormattedValue(CUR_ZERO, Constant.NULL));

                                    String discountProjectionRate = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns3 = discountProjectionRate + PROJECTIONSRATE;
                                    projDTO.addStringProperties(columns3, getFormattedValue(CUR_ZERO, Constant.NULL));
                                }
                                discountProjList.add(projDTO);
                            }
                        }
                    }
                }
                if (projSelDTO.getProjectionOrder().equals(Constant.DESCENDING)) {
                    Collections.reverse(discountProjList);
                }
            }
        }
        return discountProjList;
    }

    public int getIntegerForMonth(String month) {
        return Arrays.asList(ALL_MONTH).indexOf(month) + 1;
    }

    public List<DiscountProjectionResultsDTO> getLevelFilterSum(DiscountProjectionResultsDTO dto, Map selection, SessionDTO session) throws SystemException {
        List<DiscountProjectionResultsDTO> discountProjList = new ArrayList<>();
          boolean viewFlag=Constant.VIEW.equals(session.getAction());
        int projectionMasterId = session.getProjectionId();
        String hierachyNumber = String.valueOf(dto.getHierarchyNo());
        dto.setIsParent(Constant.STRING_ONE);
        Integer levelNo = dto.getTreeLevelNo();
        hierachyNumber = hierachyNumber + PERCENTAGE;
        List ccpId = new NmDiscountImpl().getCCPDetailsID(projectionMasterId, hierachyNumber, String.valueOf(levelNo));
        String frequency = String.valueOf(selection.get(Constant.FREQUENCY));
        if (ccpId != null && !ccpId.isEmpty()) {
            final DynamicQuery projectionDetailsDynamicQuery = ProjectionDetailsLocalServiceUtil.dynamicQuery();
            projectionDetailsDynamicQuery.add(RestrictionsFactoryUtil.in(Constant.CCP_DETAILS_SID, ccpId));
            projectionDetailsDynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_MASTER_SID, projectionMasterId));
            ProjectionList projList = ProjectionFactoryUtil.projectionList();
            projList.add(ProjectionFactoryUtil.property(Constant.PROJECTION_DETAILS_SID));
            projectionDetailsDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projList));
            List<Integer> projectionDetailsId = ProjectionDetailsLocalServiceUtil.dynamicQuery(projectionDetailsDynamicQuery);
            if (projectionDetailsId != null && !projectionDetailsId.isEmpty()) {
                String userId = String.valueOf(selection.get(Constant.USER_ID));
                String sessionId = String.valueOf(selection.get(Constant.SESSION_ID));
                int user = Integer.parseInt(userId);
                int session1 = Integer.parseInt(sessionId);
                List list = new NmDiscountImpl().getDiscountProjectionResults(projectionDetailsId, frequency, StringUtils.EMPTY, StringUtils.EMPTY, Constant.PARENT, StringUtils.EMPTY, startAndEndPeriods, user, session1,viewFlag);
                if (list != null && !list.isEmpty()) {
                    if (frequency.equals(QUARTERLY.getConstant())) {
                        double actualSales = 0;
                        double actualAmount = 0;
                        double projectedSales = 0;
                        double projectedAmount = 0;
                        int year = 0;
                        int quarter = 0;
                        String commonColumn = StringUtils.EMPTY;
                        for (int j = 0; j < list.size(); j++) {
                            final Object[] object = (Object[]) list.get(j);
                            int selectedYear = 0;
                            int selectedQuarter = 0;
                            if (object[0] != null) {
                                selectedYear = (Integer) object[0];
                            }
                            if (object[NumericConstants.SIX] != null) {
                                selectedQuarter = (Integer) object[NumericConstants.SIX];
                            }
                            if (year == selectedYear && quarter == selectedQuarter) {
                                if (object[NumericConstants.TWO] != null) {
                                    Double aSales = DataTypeConverter.convertObjectToDouble(object[NumericConstants.TWO]);
                                    actualSales = actualSales + aSales;
                                }
                                if (object[NumericConstants.THREE] != null) {
                                    Double aAmount = DataTypeConverter.convertObjectToDouble(object[NumericConstants.THREE]);
                                    actualAmount = actualAmount + aAmount;
                                }
                                if (object[NumericConstants.FOUR] != null) {
                                    Double pSales = DataTypeConverter.convertObjectToDouble(object[NumericConstants.FOUR]);
                                    projectedSales = projectedSales + pSales;
                                }
                                if (object[NumericConstants.FIVE] != null) {
                                    Double pAmount = DataTypeConverter.convertObjectToDouble(object[NumericConstants.FIVE]);
                                    projectedAmount = projectedAmount + pAmount;
                                }
                                if (j == list.size() - 1) {
                                    Double arate = actualAmount / actualSales;
                                    if (arate.isNaN()) {
                                        arate = 0.0;
                                    }
                                    arate = arate * NumericConstants.HUNDRED;
                                    String actualsRate = String.valueOf(arate);
                                    dto.addStringProperties(commonColumn + ACTUALSRATE, actualsRate != null && !NULL.equals(String.valueOf(actualsRate)) && !StringUtils.EMPTY.equals(String.valueOf(actualsRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actualsRate)))).concat(PERCENTAGE) : HYPHEN);
                                    Double actualAmt = arate * actualSales;
                                    actualAmt = actualAmt / NumericConstants.HUNDRED;
                                    if (actualAmt.isNaN()) {
                                        actualAmt = 0.0;
                                    }
                                    String actualsAmount = String.valueOf(actualAmt);
                                    dto.addStringProperties(commonColumn + ACTUALSAMOUNT, actualsAmount != null && !NULL.equals(String.valueOf(actualsAmount)) && !StringUtils.EMPTY.equals(String.valueOf(actualsAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actualsAmount)))) : HYPHEN);
                                    Double prate = projectedAmount / projectedSales;
                                    if (prate.isNaN() || prate.isInfinite()) {
                                        prate = 0.0;
                                    }
                                    prate = prate * NumericConstants.HUNDRED;
                                    dto.addStringProperties(commonColumn + PROJECTIONSRATE, prate != null && !NULL.equals(String.valueOf(prate)) && !StringUtils.EMPTY.equals(String.valueOf(prate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(prate)))).concat(PERCENTAGE) : HYPHEN);
                                    Double projectedAmtAmt = prate * projectedSales;
                                    projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                                    if (projectedAmtAmt.isNaN()) {
                                        projectedAmtAmt = 0.0;
                                    }
                                    dto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, projectedAmtAmt != null && !NULL.equals(String.valueOf(projectedAmtAmt)) && !StringUtils.EMPTY.equals(String.valueOf(projectedAmtAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(projectedAmtAmt)))) : HYPHEN);
                                    actualSales = 0;
                                    actualAmount = 0;
                                    projectedSales = 0;
                                    projectedAmount = 0;
                                    commonColumn = Constant.Q + object[NumericConstants.SIX] + object[0];
                                    year = (Integer) object[0];
                                    quarter = (Integer) object[NumericConstants.SIX];
                                }
                            } else {
                                if (j == 0) {
                                    year = selectedYear;
                                    quarter = selectedQuarter;
                                    commonColumn = Constant.Q + object[NumericConstants.SIX] + object[0];
                                    if (object[NumericConstants.TWO] != null) {
                                        Double aSales = DataTypeConverter.convertObjectToDouble(object[NumericConstants.TWO]);
                                        actualSales = actualSales + aSales;
                                    }
                                    if (object[NumericConstants.THREE] != null) {
                                        Double aAmount = DataTypeConverter.convertObjectToDouble(object[NumericConstants.THREE]);
                                        actualAmount = actualAmount + aAmount;
                                    }
                                    if (object[NumericConstants.FOUR] != null) {
                                        Double pSales = DataTypeConverter.convertObjectToDouble(object[NumericConstants.FOUR]);
                                        projectedSales = projectedSales + pSales;
                                    }
                                    if (object[NumericConstants.FIVE] != null) {
                                        Double pAmount = DataTypeConverter.convertObjectToDouble(object[NumericConstants.FIVE]);
                                        projectedAmount = projectedAmount + pAmount;
                                    }

                                } else {
                                    Double arate = actualAmount / actualSales;
                                    if (arate.isNaN()) {
                                        arate = 0.0;
                                    }
                                    arate = arate * NumericConstants.HUNDRED;
                                    String actualsRate = String.valueOf(arate);
                                    dto.addStringProperties(commonColumn + ACTUALSRATE, actualsRate != null && !NULL.equals(String.valueOf(actualsRate)) && !StringUtils.EMPTY.equals(String.valueOf(actualsRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actualsRate)))).concat(PERCENTAGE) : HYPHEN);
                                    Double actualAmt = arate * actualSales;
                                    actualAmt = actualAmt / NumericConstants.HUNDRED;
                                    if (actualAmt.isNaN()) {
                                        actualAmt = 0.0;
                                    }
                                    String actualsAmount = String.valueOf(actualAmt);
                                    dto.addStringProperties(commonColumn + ACTUALSAMOUNT, actualsAmount != null && !NULL.equals(String.valueOf(actualsAmount)) && !StringUtils.EMPTY.equals(String.valueOf(actualsAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actualsAmount)))) : HYPHEN);
                                    Double prate = projectedAmount / projectedSales;
                                    if (prate.isNaN() || prate.isInfinite()) {
                                        prate = 0.0;
                                    }
                                    prate = prate * NumericConstants.HUNDRED;
                                    String prorate = String.valueOf(prate);
                                    dto.addStringProperties(commonColumn + PROJECTIONSRATE, prorate != null && !NULL.equals(String.valueOf(prorate)) && !StringUtils.EMPTY.equals(String.valueOf(prorate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(prorate)))).concat(PERCENTAGE) : HYPHEN);
                                    Double projectedAmtAmt = prate * projectedSales;
                                    projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                                    if (projectedAmtAmt.isNaN()) {
                                        projectedAmtAmt = 0.0;
                                    }
                                    String proAmount = String.valueOf(projectedAmtAmt);
                                    dto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                    actualSales = 0;
                                    actualAmount = 0;
                                    projectedSales = 0;
                                    projectedAmount = 0;
                                    commonColumn = Constant.Q + object[NumericConstants.SIX] + object[0];
                                    year = (Integer) object[0];
                                    quarter = (Integer) object[NumericConstants.SIX];
                                    if (object[NumericConstants.TWO] != null) {
                                        Double aSales = DataTypeConverter.convertObjectToDouble(object[NumericConstants.TWO]);
                                        actualSales = actualSales + aSales;
                                    }
                                    if (object[NumericConstants.THREE] != null) {
                                        Double aAmount = DataTypeConverter.convertObjectToDouble(object[NumericConstants.THREE]);
                                        actualAmount = actualAmount + aAmount;
                                    }
                                    if (object[NumericConstants.FOUR] != null) {
                                        Double pSales = DataTypeConverter.convertObjectToDouble(object[NumericConstants.FOUR]);
                                        projectedSales = projectedSales + pSales;
                                    }
                                    if (object[NumericConstants.FIVE] != null) {
                                        Double pAmount = DataTypeConverter.convertObjectToDouble(object[NumericConstants.FIVE]);
                                        projectedAmount = projectedAmount + pAmount;
                                    }
                                }
                            }
                        }
                    }
                    if (frequency.equals(Constant.SEMI_ANNUALLY)) {
                        double actualSales = 0;
                        double actualAmount = 0;
                        double projectedSales = 0;
                        double projectedAmount = 0;
                        int year = 0;
                        int semiAnnual = 0;
                        String commonColumn = StringUtils.EMPTY;
                        for (int j = 0; j < list.size(); j++) {
                            final Object[] object = (Object[]) list.get(j);
                            int selectedYear = 0;
                            int selectedSemiAnnual = 0;
                            if (object[0] != null) {
                                selectedYear = (Integer) object[0];
                            }
                            if (object[NumericConstants.SIX] != null) {
                                selectedSemiAnnual = (Integer) object[1];
                            }
                            if (year == selectedYear && semiAnnual == selectedSemiAnnual) {
                                if (object[NumericConstants.TWO] != null) {
                                    Double aSales = DataTypeConverter.convertObjectToDouble(object[NumericConstants.TWO]);
                                    actualSales = actualSales + aSales;
                                }
                                if (object[NumericConstants.THREE] != null) {
                                    Double aAmount = DataTypeConverter.convertObjectToDouble(object[NumericConstants.THREE]);
                                    actualAmount = actualAmount + aAmount;
                                }
                                if (object[NumericConstants.FOUR] != null) {
                                    Double pSales = DataTypeConverter.convertObjectToDouble(object[NumericConstants.FOUR]);
                                    projectedSales = projectedSales + pSales;
                                }
                                if (object[NumericConstants.FIVE] != null) {
                                    Double pAmount = DataTypeConverter.convertObjectToDouble(object[NumericConstants.FIVE]);
                                    projectedAmount = projectedAmount + pAmount;
                                }
                                if (j == list.size() - 1) {
                                    Double arate = actualAmount / actualSales;
                                    if (arate.isNaN()) {
                                        arate = 0.0;
                                    }
                                    arate = arate * NumericConstants.HUNDRED;
                                    dto.addStringProperties(commonColumn + ACTUALSRATE, arate != null && !NULL.equals(String.valueOf(arate)) && !StringUtils.EMPTY.equals(String.valueOf(arate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(arate)))).concat(PERCENTAGE) : HYPHEN);
                                    Double actualAmt = arate * actualSales;
                                    actualAmt = actualAmt / NumericConstants.HUNDRED;
                                    if (actualAmt.isNaN()) {
                                        actualAmt = 0.0;
                                    }
                                    dto.addStringProperties(commonColumn + ACTUALSAMOUNT, actualAmt != null && !NULL.equals(String.valueOf(actualAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actualAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actualAmt)))) : HYPHEN);
                                    Double prate = projectedAmount / projectedSales;
                                    if (prate.isNaN() || prate.isInfinite()) {
                                        prate = 0.0;
                                    }
                                    prate = prate * NumericConstants.HUNDRED;
                                    dto.addStringProperties(commonColumn + PROJECTIONSRATE, prate != null && !NULL.equals(String.valueOf(prate)) && !StringUtils.EMPTY.equals(String.valueOf(prate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(prate)))).concat(PERCENTAGE) : HYPHEN);
                                    Double projectedAmtAmt = prate * projectedSales;
                                    projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                                    if (projectedAmtAmt.isNaN()) {
                                        projectedAmtAmt = 0.0;
                                    }
                                    dto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, projectedAmtAmt != null && !NULL.equals(String.valueOf(projectedAmtAmt)) && !StringUtils.EMPTY.equals(String.valueOf(projectedAmtAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(projectedAmtAmt)))) : HYPHEN);
                                    actualSales = 0;
                                    actualAmount = 0;
                                    projectedSales = 0;
                                    projectedAmount = 0;
                                    commonColumn = Constant.S + object[1] + object[0];
                                    year = (Integer) object[0];
                                    semiAnnual = (Integer) object[1];
                                }
                            } else {
                                if (j == 0) {
                                    year = selectedYear;
                                    semiAnnual = selectedSemiAnnual;
                                    commonColumn = Constant.S + object[1] + object[0];
                                    if (object[NumericConstants.TWO] != null) {
                                        Double aSales = DataTypeConverter.convertObjectToDouble(object[NumericConstants.TWO]);
                                        actualSales = actualSales + aSales;
                                    }
                                    if (object[NumericConstants.THREE] != null) {
                                        Double aAmount = DataTypeConverter.convertObjectToDouble(object[NumericConstants.THREE]);
                                        actualAmount = actualAmount + aAmount;
                                    }
                                    if (object[NumericConstants.FOUR] != null) {
                                        Double pSales = DataTypeConverter.convertObjectToDouble(object[NumericConstants.FOUR]);
                                        projectedSales = projectedSales + pSales;
                                    }
                                    if (object[NumericConstants.FIVE] != null) {
                                        Double pAmount = DataTypeConverter.convertObjectToDouble(object[NumericConstants.FIVE]);
                                        projectedAmount = projectedAmount + pAmount;
                                    }
                                } else {
                                    Double arate = actualAmount / actualSales;
                                    if (arate.isNaN()) {
                                        arate = 0.0;
                                    }
                                    arate = arate * NumericConstants.HUNDRED;
                                    dto.addStringProperties(commonColumn + ACTUALSRATE, arate != null && !NULL.equals(String.valueOf(arate)) && !StringUtils.EMPTY.equals(String.valueOf(arate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(arate)))).concat(PERCENTAGE) : HYPHEN);
                                    Double actualAmt = arate * actualSales;
                                    actualAmt = actualAmt / NumericConstants.HUNDRED;
                                    if (actualAmt.isNaN()) {
                                        actualAmt = 0.0;
                                    }
                                    dto.addStringProperties(commonColumn + ACTUALSAMOUNT, actualAmt != null && !NULL.equals(String.valueOf(actualAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actualAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actualAmt)))) : HYPHEN);
                                    Double prate = projectedAmount / projectedSales;
                                    if (prate.isNaN() || prate.isInfinite()) {
                                        prate = 0.0;
                                    }
                                    prate = prate * NumericConstants.HUNDRED;
                                    dto.addStringProperties(commonColumn + PROJECTIONSRATE, prate != null && !NULL.equals(String.valueOf(prate)) && !StringUtils.EMPTY.equals(String.valueOf(prate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(prate)))).concat(PERCENTAGE) : HYPHEN);
                                    Double projectedAmtAmt = prate * projectedSales;
                                    projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                                    if (projectedAmtAmt.isNaN()) {
                                        projectedAmtAmt = 0.0;
                                    }
                                    dto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, projectedAmtAmt != null && !NULL.equals(String.valueOf(projectedAmtAmt)) && !StringUtils.EMPTY.equals(String.valueOf(projectedAmtAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(projectedAmtAmt)))) : HYPHEN);
                                    actualSales = 0;
                                    actualAmount = 0;
                                    projectedSales = 0;
                                    projectedAmount = 0;
                                    commonColumn = Constant.S + object[1] + object[0];
                                    year = (Integer) object[0];
                                    semiAnnual = (Integer) object[1];
                                    if (object[NumericConstants.TWO] != null) {
                                        Double aSales = DataTypeConverter.convertObjectToDouble(object[NumericConstants.TWO]);
                                        actualSales = actualSales + aSales;
                                    }
                                    if (object[NumericConstants.THREE] != null) {
                                        Double aAmount = DataTypeConverter.convertObjectToDouble(object[NumericConstants.THREE]);
                                        actualAmount = actualAmount + aAmount;
                                    }
                                    if (object[NumericConstants.FOUR] != null) {
                                        Double pSales = DataTypeConverter.convertObjectToDouble(object[NumericConstants.FOUR]);
                                        projectedSales = projectedSales + pSales;
                                    }
                                    if (object[NumericConstants.FIVE] != null) {
                                        Double pAmount = DataTypeConverter.convertObjectToDouble(object[NumericConstants.FIVE]);
                                        projectedAmount = projectedAmount + pAmount;
                                    }
                                }
                            }
                        }
                    }
                    if (frequency.equals(Constant.ANNUALLY)) {
                        double actualSales = 0;
                        double actualAmount = 0;
                        double projectedSales = 0;
                        double projectedAmount = 0;
                        int year = 0;
                        String commonColumn = StringUtils.EMPTY;
                        for (int j = 0; j < list.size(); j++) {
                            final Object[] object = (Object[]) list.get(j);
                            int selectedYear = 0;
                            if (object[0] != null) {
                                selectedYear = (Integer) object[0];
                            }
                            if (year == selectedYear) {
                                if (object[NumericConstants.TWO] != null) {
                                    Double aSales = DataTypeConverter.convertObjectToDouble(object[NumericConstants.TWO]);
                                    actualSales = actualSales + aSales;
                                }
                                if (object[NumericConstants.THREE] != null) {
                                    Double aAmount = DataTypeConverter.convertObjectToDouble(object[NumericConstants.THREE]);
                                    actualAmount = actualAmount + aAmount;
                                }
                                if (object[NumericConstants.FOUR] != null) {
                                    Double pSales = DataTypeConverter.convertObjectToDouble(object[NumericConstants.FOUR]);
                                    projectedSales = projectedSales + pSales;
                                }
                                if (object[NumericConstants.FIVE] != null) {
                                    Double pAmount = DataTypeConverter.convertObjectToDouble(object[NumericConstants.FIVE]);
                                    projectedAmount = projectedAmount + pAmount;
                                }
                                if (j == list.size() - 1) {
                                    Double arate = actualAmount / actualSales;
                                    if (arate.isNaN()) {
                                        arate = 0.0;
                                    }
                                    arate = arate * NumericConstants.HUNDRED;
                                    dto.addStringProperties(commonColumn + ACTUALSRATE, arate != null && !NULL.equals(String.valueOf(arate)) && !StringUtils.EMPTY.equals(String.valueOf(arate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(arate)))).concat(PERCENTAGE) : HYPHEN);
                                    Double actualAmt = arate * actualSales;
                                    actualAmt = actualAmt / NumericConstants.HUNDRED;
                                    if (actualAmt.isNaN()) {
                                        actualAmt = 0.0;
                                    }
                                    dto.addStringProperties(commonColumn + ACTUALSAMOUNT, actualAmt != null && !NULL.equals(String.valueOf(actualAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actualAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actualAmt)))) : HYPHEN);
                                    Double prate = projectedAmount / projectedSales;
                                    if (prate.isNaN() || prate.isInfinite()) {
                                        prate = 0.0;
                                    }
                                    prate = prate * NumericConstants.HUNDRED;
                                    dto.addStringProperties(commonColumn + PROJECTIONSRATE, prate != null && !NULL.equals(String.valueOf(prate)) && !StringUtils.EMPTY.equals(String.valueOf(prate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(prate)))).concat(PERCENTAGE) : HYPHEN);
                                    Double projectedAmtAmt = prate * projectedSales;
                                    projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                                    if (projectedAmtAmt.isNaN()) {
                                        projectedAmtAmt = 0.0;
                                    }
                                    dto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, projectedAmtAmt != null && !NULL.equals(String.valueOf(projectedAmtAmt)) && !StringUtils.EMPTY.equals(String.valueOf(projectedAmtAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(projectedAmtAmt)))) : HYPHEN);
                                    actualSales = 0;
                                    actualAmount = 0;
                                    projectedSales = 0;
                                    projectedAmount = 0;
                                    commonColumn = StringUtils.EMPTY + object[0];
                                    year = (Integer) object[0];
                                }
                            } else {
                                if (j == 0) {
                                    year = selectedYear;
                                    commonColumn = StringUtils.EMPTY + object[0];
                                    if (object[NumericConstants.TWO] != null) {
                                        Double aSales = DataTypeConverter.convertObjectToDouble(object[NumericConstants.TWO]);
                                        actualSales = actualSales + aSales;
                                    }
                                    if (object[NumericConstants.THREE] != null) {
                                        Double aAmount = DataTypeConverter.convertObjectToDouble(object[NumericConstants.THREE]);
                                        actualAmount = actualAmount + aAmount;
                                    }
                                    if (object[NumericConstants.FOUR] != null) {
                                        Double pSales = DataTypeConverter.convertObjectToDouble(object[NumericConstants.FOUR]);
                                        projectedSales = projectedSales + pSales;
                                    }
                                    if (object[NumericConstants.FIVE] != null) {
                                        Double pAmount = DataTypeConverter.convertObjectToDouble(object[NumericConstants.FIVE]);
                                        projectedAmount = projectedAmount + pAmount;
                                    }
                                } else {
                                    Double arate = actualAmount / actualSales;
                                    if (arate.isNaN()) {
                                        arate = 0.0;
                                    }
                                    arate = arate * NumericConstants.HUNDRED;
                                    dto.addStringProperties(commonColumn + ACTUALSRATE, arate != null && !NULL.equals(String.valueOf(arate)) && !StringUtils.EMPTY.equals(String.valueOf(arate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(arate)))).concat(PERCENTAGE) : HYPHEN);
                                    Double actualAmt = arate * actualSales;
                                    actualAmt = actualAmt / NumericConstants.HUNDRED;
                                    if (actualAmt.isNaN()) {
                                        actualAmt = 0.0;
                                    }
                                    dto.addStringProperties(commonColumn + ACTUALSAMOUNT, actualAmt != null && !NULL.equals(String.valueOf(actualAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actualAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actualAmt)))) : HYPHEN);
                                    Double prate = projectedAmount / projectedSales;
                                    if (prate.isNaN() || prate.isInfinite()) {
                                        prate = 0.0;
                                    }
                                    prate = prate * NumericConstants.HUNDRED;
                                    dto.addStringProperties(commonColumn + PROJECTIONSRATE, prate != null && !NULL.equals(String.valueOf(prate)) && !StringUtils.EMPTY.equals(String.valueOf(prate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(prate)))).concat(PERCENTAGE) : HYPHEN);
                                    Double projectedAmtAmt = prate * projectedSales;
                                    projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                                    if (projectedAmtAmt.isNaN()) {
                                        projectedAmtAmt = 0.0;
                                    }
                                    dto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, projectedAmtAmt != null && !NULL.equals(String.valueOf(projectedAmtAmt)) && !StringUtils.EMPTY.equals(String.valueOf(projectedAmtAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(projectedAmtAmt)))) : HYPHEN);
                                    actualSales = 0;
                                    actualAmount = 0;
                                    projectedSales = 0;
                                    projectedAmount = 0;
                                    commonColumn = StringUtils.EMPTY + object[0];
                                    year = (Integer) object[0];
                                    if (object[NumericConstants.TWO] != null) {
                                        Double aSales = DataTypeConverter.convertObjectToDouble(object[NumericConstants.TWO]);
                                        actualSales = actualSales + aSales;
                                    }
                                    if (object[NumericConstants.THREE] != null) {
                                        Double aAmount = DataTypeConverter.convertObjectToDouble(object[NumericConstants.THREE]);
                                        actualAmount = actualAmount + aAmount;
                                    }
                                    if (object[NumericConstants.FOUR] != null) {
                                        Double pSales = DataTypeConverter.convertObjectToDouble(object[NumericConstants.FOUR]);
                                        projectedSales = projectedSales + pSales;
                                    }
                                    if (object[NumericConstants.FIVE] != null) {
                                        Double pAmount = DataTypeConverter.convertObjectToDouble(object[NumericConstants.FIVE]);
                                        projectedAmount = projectedAmount + pAmount;
                                    }
                                }
                            }
                        }
                    }
                    if (frequency.equals(MONTHLY.getConstant())) {
                        double actualSales = 0;
                        double actualAmount = 0;
                        double projectedSales = 0;
                        double projectedAmount = 0;
                        int year = 0;
                        int month = 0;
                        String commonColumn = StringUtils.EMPTY;
                        for (int j = 0; j < list.size(); j++) {
                            final Object[] object = (Object[]) list.get(j);
                            int selectedYear = 0;
                            int selectedMonth = 0;
                            if (object[0] != null) {
                                selectedYear = (Integer) object[0];
                            }
                            if (object[NumericConstants.SIX] != null) {
                                selectedMonth = (Integer) object[1];
                            }
                            if (year == selectedYear && month == selectedMonth) {
                                if (object[NumericConstants.TWO] != null) {
                                    Double aSales = DataTypeConverter.convertObjectToDouble(object[NumericConstants.TWO]);
                                    actualSales = actualSales + aSales;
                                }
                                if (object[NumericConstants.THREE] != null) {
                                    Double aAmount = DataTypeConverter.convertObjectToDouble(object[NumericConstants.THREE]);
                                    actualAmount = actualAmount + aAmount;
                                }
                                if (object[NumericConstants.FOUR] != null) {
                                    Double pSales = DataTypeConverter.convertObjectToDouble(object[NumericConstants.FOUR]);
                                    projectedSales = projectedSales + pSales;
                                }
                                if (object[NumericConstants.FIVE] != null) {
                                    Double pAmount = DataTypeConverter.convertObjectToDouble(object[NumericConstants.FIVE]);
                                    projectedAmount = projectedAmount + pAmount;
                                }
                                if (j == list.size() - 1) {
                                    Double arate = actualAmount / actualSales;
                                    if (arate.isNaN()) {
                                        arate = 0.0;
                                    }
                                    arate = arate * NumericConstants.HUNDRED;
                                    dto.addStringProperties(commonColumn + ACTUALSRATE, arate != null && !NULL.equals(String.valueOf(arate)) && !StringUtils.EMPTY.equals(String.valueOf(arate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(arate)))).concat(PERCENTAGE) : HYPHEN);
                                    Double actualAmt = arate * actualSales;
                                    actualAmt = actualAmt / NumericConstants.HUNDRED;
                                    if (actualAmt.isNaN()) {
                                        actualAmt = 0.0;
                                    }
                                    dto.addStringProperties(commonColumn + ACTUALSAMOUNT, actualAmt != null && !NULL.equals(String.valueOf(actualAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actualAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actualAmt)))) : HYPHEN);
                                    Double prate = projectedAmount / projectedSales;
                                    if (prate.isNaN() || prate.isInfinite()) {
                                        prate = 0.0;
                                    }
                                    prate = prate * NumericConstants.HUNDRED;
                                    dto.addStringProperties(commonColumn + PROJECTIONSRATE, prate != null && !NULL.equals(String.valueOf(prate)) && !StringUtils.EMPTY.equals(String.valueOf(prate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(prate)))).concat(PERCENTAGE) : HYPHEN);
                                    Double projectedAmtAmt = prate * projectedSales;
                                    projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                                    if (projectedAmtAmt.isNaN()) {
                                        projectedAmtAmt = 0.0;
                                    }
                                    dto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, projectedAmtAmt != null && !NULL.equals(String.valueOf(projectedAmtAmt)) && !StringUtils.EMPTY.equals(String.valueOf(projectedAmtAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(projectedAmtAmt)))) : HYPHEN);
                                    actualSales = 0;
                                    actualAmount = 0;
                                    projectedSales = 0;
                                    projectedAmount = 0;
                                    String monthName = getMonthForInt(Integer.parseInt(StringUtils.EMPTY + object[1]) - 1);
                                    commonColumn = monthName + object[0];
                                    year = (Integer) object[0];
                                    month = (Integer) object[1];
                                }
                            } else {
                                if (j == 0) {
                                    year = selectedYear;
                                    month = selectedMonth;
                                    String monthName = getMonthForInt(Integer.parseInt(StringUtils.EMPTY + object[1]) - 1);
                                    commonColumn = monthName + object[0];
                                    if (object[NumericConstants.TWO] != null) {
                                        Double aSales = DataTypeConverter.convertObjectToDouble(object[NumericConstants.TWO]);
                                        actualSales = actualSales + aSales;
                                    }
                                    if (object[NumericConstants.THREE] != null) {
                                        Double aAmount = DataTypeConverter.convertObjectToDouble(object[NumericConstants.THREE]);
                                        actualAmount = actualAmount + aAmount;
                                    }
                                    if (object[NumericConstants.FOUR] != null) {
                                        Double pSales = DataTypeConverter.convertObjectToDouble(object[NumericConstants.FOUR]);
                                        projectedSales = projectedSales + pSales;
                                    }
                                    if (object[NumericConstants.FIVE] != null) {
                                        Double pAmount = DataTypeConverter.convertObjectToDouble(object[NumericConstants.FIVE]);
                                        projectedAmount = projectedAmount + pAmount;
                                    }
                                } else {
                                    Double arate = actualAmount / actualSales;
                                    if (arate.isNaN()) {
                                        arate = 0.0;
                                    }
                                    arate = arate * NumericConstants.HUNDRED;
                                    dto.addStringProperties(commonColumn + ACTUALSRATE, arate != null && !NULL.equals(String.valueOf(arate)) && !StringUtils.EMPTY.equals(String.valueOf(arate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(arate)))).concat(PERCENTAGE) : HYPHEN);
                                    Double actualAmt = arate * actualSales;
                                    actualAmt = actualAmt / NumericConstants.HUNDRED;
                                    if (actualAmt.isNaN()) {
                                        actualAmt = 0.0;
                                    }
                                    dto.addStringProperties(commonColumn + ACTUALSAMOUNT, actualAmt != null && !NULL.equals(String.valueOf(actualAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actualAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actualAmt)))) : HYPHEN);
                                    Double prate = projectedAmount / projectedSales;
                                    if (prate.isNaN() || prate.isInfinite()) {
                                        prate = 0.0;
                                    }
                                    prate = prate * NumericConstants.HUNDRED;
                                    dto.addStringProperties(commonColumn + PROJECTIONSRATE, prate != null && !NULL.equals(String.valueOf(prate)) && !StringUtils.EMPTY.equals(String.valueOf(prate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(prate)))).concat(PERCENTAGE) : HYPHEN);
                                    Double projectedAmtAmt = prate * projectedSales;
                                    projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                                    if (projectedAmtAmt.isNaN()) {
                                        projectedAmtAmt = 0.0;
                                    }
                                    dto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, projectedAmtAmt != null && !NULL.equals(String.valueOf(projectedAmtAmt)) && !StringUtils.EMPTY.equals(String.valueOf(projectedAmtAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(projectedAmtAmt)))) : HYPHEN);
                                    actualSales = 0;
                                    actualAmount = 0;
                                    projectedSales = 0;
                                    projectedAmount = 0;
                                    String monthName = getMonthForInt(Integer.parseInt(StringUtils.EMPTY + object[1]) - 1);
                                    commonColumn = monthName + object[0];
                                    year = (Integer) object[0];
                                    month = (Integer) object[1];
                                    if (object[NumericConstants.TWO] != null) {
                                        Double aSales = DataTypeConverter.convertObjectToDouble(object[NumericConstants.TWO]);
                                        actualSales = actualSales + aSales;
                                    }
                                    if (object[NumericConstants.THREE] != null) {
                                        Double aAmount = DataTypeConverter.convertObjectToDouble(object[NumericConstants.THREE]);
                                        actualAmount = actualAmount + aAmount;
                                    }
                                    if (object[NumericConstants.FOUR] != null) {
                                        Double pSales = DataTypeConverter.convertObjectToDouble(object[NumericConstants.FOUR]);
                                        projectedSales = projectedSales + pSales;
                                    }
                                    if (object[NumericConstants.FIVE] != null) {
                                        Double pAmount = DataTypeConverter.convertObjectToDouble(object[NumericConstants.FIVE]);
                                        projectedAmount = projectedAmount + pAmount;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        discountProjList.add(dto);
        return discountProjList;
    }
    //Added after refactor

    /**
     *
     * @param start
     * @param offset
     * @param projSelDTO
     * @return
     *
     */
    public List<DiscountProjectionResultsDTO> getProjectionResults(int start, int offset, ProjectionSelectionDTO projSelDTO) {
        int neededRecord = offset;
        int mayBeAdded = 0;
        List<DiscountProjectionResultsDTO> projDTOList = new ArrayList<>();
        List<Integer> yearList = new ArrayList<>();
        yearList.add(projSelDTO.getForecastDTO().getHistoryStartYear());
        yearList.add(projSelDTO.getForecastDTO().getHistoryStartMonth());
        yearList.add(projSelDTO.getForecastDTO().getHistoryEndYear());
        yearList.add(projSelDTO.getForecastDTO().getHistoryEndMonth());
        yearList.add(projSelDTO.getForecastDTO().getForecastStartYear());
        String freq = String.valueOf(projSelDTO.getFrequency());
        if (freq.equals(Constant.QUARTERLY)) {
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
        } else if (freq.equals(Constant.SEMI_ANNUALLY)) {
            int month = projSelDTO.getForecastDTO().getForecastStartMonth();
            if (month <= NumericConstants.SIX) {
                yearList.add(1);
            } else if (month >= NumericConstants.SEVEN && month < NumericConstants.THIRTEEN) {
                yearList.add(NumericConstants.SEVEN);
            }
        } else if (freq.equals(Constant.ANNUALLY)) {
            yearList.add(1);
        } else if (freq.equals(Constant.MONTHLY)) {
            int month = projSelDTO.getForecastDTO().getForecastStartMonth();
            yearList.add(month);
        }
        yearList.add(projSelDTO.getForecastDTO().getForecastEndYear());
        yearList.add(projSelDTO.getForecastDTO().getForecastEndMonth());
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
                if (frequency.equals(Constant.ANNUALLY)) {
                    pivotYearList.add(Integer.valueOf(projSelDTO.getPeriodList().get(0)));
                    pivotYearList.add(1);
                    pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndYear());
                    pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndMonth());
                } else if (frequency.equals(Constant.QUARTERLY)) {
                    String column = String.valueOf(projSelDTO.getPeriodList().get(0));
                    column = column.replace(Constant.Q_SMALL, StringUtils.EMPTY);
                    String year = column.substring(column.length() - NumericConstants.FOUR, column.length());
                    String fre = column.replace(year, StringUtils.EMPTY);
                    pivotYearList.add(Integer.valueOf(year));
                    if (fre.equals(Constant.STRING_ONE)) {
                        pivotYearList.add(1);
                    } else if (fre.equals("2")) {
                        pivotYearList.add(NumericConstants.FOUR);
                    } else if (fre.equals("3")) {
                        pivotYearList.add(NumericConstants.SEVEN);
                    } else if (fre.equals("4")) {
                        pivotYearList.add(NumericConstants.TEN);
                    }
                    pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndYear());
                    pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndMonth());
                } else if (frequency.equals(Constant.MONTHLY)) {
                    loadKeyMap();
                    String column = String.valueOf(projSelDTO.getPeriodList().get(0));
                    String fre = column.substring(0, NumericConstants.THREE);
                    String year = column.replace(fre, StringUtils.EMPTY);
                    String month = valueMap.get(fre);
                    pivotYearList.add(Integer.valueOf(year));
                    pivotYearList.add(Integer.valueOf(month));
                    pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndYear());
                    pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndMonth());

                } else if (frequency.equals(Constant.SEMI_ANNUALLY)) {
                    String column = String.valueOf(projSelDTO.getPeriodList().get(0));
                    column = column.replace(Constant.S_SMALL, StringUtils.EMPTY);
                    String year = column.substring(column.length() - NumericConstants.FOUR, column.length());
                    String fre = column.replace(year, StringUtils.EMPTY);
                    pivotYearList.add(Integer.valueOf(year));
                    if (fre.equals(Constant.STRING_ONE)) {
                        pivotYearList.add(1);
                    } else if (fre.equals("2")) {
                        pivotYearList.add(NumericConstants.SEVEN);
                    }
                    pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndYear());
                    pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndMonth());
                }
                pivotYearList.add(projSelDTO.getForecastDTO().getForecastStartYear());
                String frequ = String.valueOf(projSelDTO.getFrequency());
                if (frequ.equals(Constant.QUARTERLY)) {
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
                } else if (frequ.equals(Constant.SEMI_ANNUALLY)) {
                    int month = projSelDTO.getForecastDTO().getForecastStartMonth();
                    if (month <= NumericConstants.SIX) {
                        pivotYearList.add(1);
                    } else if (month >= NumericConstants.SEVEN && month < NumericConstants.THIRTEEN) {
                        pivotYearList.add(NumericConstants.SEVEN);
                    }
                } else if (frequ.equals(Constant.ANNUALLY)) {
                    pivotYearList.add(1);
                } else if (frequ.equals(Constant.MONTHLY)) {
                    int month = projSelDTO.getForecastDTO().getForecastStartMonth();
                    pivotYearList.add(month);
                }
                pivotYearList.add(projSelDTO.getForecastDTO().getForecastEndYear());
                pivotYearList.add(projSelDTO.getForecastDTO().getForecastEndMonth());
                if (start < 1) {
                    List<DiscountProjectionResultsDTO> discountDtoList;
                    discountDtoList = getPivotProjectionTotal(projSelDTO.getProjectionId(), projSelDTO, pivotYearList);
                    if (discountDtoList != null && !discountDtoList.isEmpty()) {
                        projDTOList.add(discountDtoList.get(0));
                        neededRecord--;
                    }
                }
                mayBeAdded++;
                if (neededRecord > 0) {
                    List<DiscountProjectionResultsDTO> periodList = new ArrayList<>();
                    try {
                        periodList = getPivotProjectionTotalDiscount(projSelDTO, pivotYearList);
                    } catch (SystemException ex) {
                        LoggerFactory.getLogger(DiscountProjectionResultsLogic.class.getName()).error( StringUtils.EMPTY, ex);
                    }
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
        } else {
            if (neededRecord > 0 && projSelDTO.getPivotView().contains(PERIOD.getConstant())) {
                List<DiscountProjectionResultsDTO> discountDtoList = new ArrayList<>();
                if (neededRecord > 0) {
                    List<String> discountList = new ArrayList<>();
                    int mayBeAddedRecord = start - mayBeAdded;
                    if (mayBeAddedRecord < 0) {
                        mayBeAddedRecord = 0;
                    }
                    for (int i = mayBeAddedRecord; i < projSelDTO.getDiscountNameList().size(); i++) {
                        discountList.add(projSelDTO.getDiscountNameList().get(i));
                    }
                    try {
                        discountDtoList = getPeriodHierarchy(projSelDTO, yearList, discountList);
                    } catch (SystemException ex) {

                        LoggerFactory.getLogger(DiscountProjectionResultsLogic.class.getName()).error( StringUtils.EMPTY, ex);
                    }
                    for (int k = 0; k < discountDtoList.size() && neededRecord > 0; neededRecord--, k++) {
                        projDTOList.add(discountDtoList.get(k));
                    }
                    mayBeAdded += projSelDTO.getDiscountNameList().size();
                }
            } else {
                List<Integer> pivotYearList = new ArrayList<>();
                String frequency = String.valueOf(projSelDTO.getFrequency());
                if (frequency.equals(Constant.ANNUALLY)) {
                    pivotYearList.add(Integer.valueOf(projSelDTO.getPeriodList().get(0)));
                    pivotYearList.add(1);
                    pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndYear());
                    pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndMonth());
                } else if (frequency.equals(Constant.QUARTERLY)) {
                    String column = String.valueOf(projSelDTO.getPeriodList().get(0));
                    column = column.replace(Constant.Q_SMALL, StringUtils.EMPTY);
                    String year = column.substring(column.length() - NumericConstants.FOUR, column.length());
                    String fre = column.replace(year, StringUtils.EMPTY);
                    pivotYearList.add(Integer.valueOf(year));
                    if (fre.equals(Constant.STRING_ONE)) {
                        pivotYearList.add(1);
                    } else if (fre.equals("2")) {
                        pivotYearList.add(NumericConstants.FOUR);
                    } else if (fre.equals("3")) {
                        pivotYearList.add(NumericConstants.SEVEN);
                    } else if (fre.equals("4")) {
                        pivotYearList.add(NumericConstants.TEN);
                    }
                    pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndYear());
                    pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndMonth());
                } else if (frequency.equals(Constant.MONTHLY)) {
                    loadKeyMap();
                    String column = String.valueOf(projSelDTO.getPeriodList().get(0));
                    String fre = column.substring(0, NumericConstants.THREE);
                    String year = column.replace(fre, StringUtils.EMPTY);
                    String month = valueMap.get(fre);
                    pivotYearList.add(Integer.valueOf(year));
                    pivotYearList.add(Integer.valueOf(month));
                    pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndYear());
                    pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndMonth());

                } else if (frequency.equals(Constant.SEMI_ANNUALLY)) {
                    String column = String.valueOf(projSelDTO.getPeriodList().get(0));
                    column = column.replace(Constant.S_SMALL, StringUtils.EMPTY);
                    String year = column.substring(column.length() - NumericConstants.FOUR, column.length());
                    String fre = column.replace(year, StringUtils.EMPTY);
                    pivotYearList.add(Integer.valueOf(year));
                    if (fre.equals(Constant.STRING_ONE)) {
                        pivotYearList.add(1);
                    } else if (fre.equals("2")) {
                        pivotYearList.add(NumericConstants.SEVEN);
                    }
                    pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndYear());
                    pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndMonth());
                }
                pivotYearList.add(projSelDTO.getForecastDTO().getForecastStartYear());
                String frequ = String.valueOf(projSelDTO.getFrequency());
                if (frequ.equals(Constant.QUARTERLY)) {
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
                } else if (frequ.equals(Constant.SEMI_ANNUALLY)) {
                    int month = projSelDTO.getForecastDTO().getForecastStartMonth();
                    if (month <= NumericConstants.SIX) {
                        pivotYearList.add(1);
                    } else if (month >= NumericConstants.SEVEN && month < NumericConstants.THIRTEEN) {
                        pivotYearList.add(NumericConstants.SEVEN);
                    }
                } else if (frequ.equals(Constant.ANNUALLY)) {
                    pivotYearList.add(1);
                } else if (frequ.equals(Constant.MONTHLY)) {
                    int month = projSelDTO.getForecastDTO().getForecastStartMonth();
                    pivotYearList.add(month);
                }
                pivotYearList.add(projSelDTO.getForecastDTO().getForecastEndYear());
                pivotYearList.add(projSelDTO.getForecastDTO().getForecastEndMonth());

                if (neededRecord > 0 && !projSelDTO.getGroup().startsWith(Constant.DISCOUNT)) {
                    List<DiscountProjectionResultsDTO> periodList = new ArrayList<>();
                    try {
                        periodList = getPivotHierarchy(projSelDTO, pivotYearList);
                    } catch (SystemException ex) {

                        LoggerFactory.getLogger(DiscountProjectionResultsLogic.class.getName()).error( StringUtils.EMPTY, ex);
                    }
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
        }

        if (neededRecord > 0 && !projSelDTO.isIsFilter()) {
            if ((projSelDTO.getTreeLevelNo() + 1) == projSelDTO.getTpLevel()
                    && ((projSelDTO.isIsCustomHierarchy()) || (!projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)))
                    && !projSelDTO.getGroup().startsWith(Constant.DISCOUNT) && !projSelDTO.getGroupFilter().startsWith(Constant.ALL_DISCOUNT_GROUP)) {
                DiscountProjectionResultsDTO dto = new DiscountProjectionResultsDTO();
                dto.setLevelNo(projSelDTO.getLevelNo());
                dto.setTreeLevelNo(projSelDTO.getTreeLevelNo());
                dto.setParentNode(projSelDTO.getParentNode());
                dto.setGroup(projSelDTO.getGroupFilter());
                dto.setLevelValue(projSelDTO.getLevelValue());
                dto.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
                dto.setHierarchyNo(projSelDTO.getHierarchyNo());
                if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
                    dto.setCustomerHierarchyNo(dto.getHierarchyNo());
                    dto.setProductHierarchyNo(projSelDTO.getProductHierarchyNo());
                } else if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
                    dto.setProductHierarchyNo(dto.getHierarchyNo());
                    dto.setCustomerHierarchyNo(projSelDTO.getCustomerHierarchyNo());
                }

                dto.setParent(1);
                DiscountProjectionResultsDTO discountDTO;
                if (projSelDTO.getPivotView().equals(Constant.PERIOD)) {

                    discountDTO = getChildNodeValues(dto, projSelDTO, null);
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
    public List<DiscountProjectionResultsDTO> getConfiguredProjectionResults(Object parentId, int start, int offset, ProjectionSelectionDTO projSelDTO) {
        List<DiscountProjectionResultsDTO> resultList;
        if (!projSelDTO.isIsFilter() || (parentId instanceof DiscountProjectionResultsDTO)) {

            projSelDTO.setYear(Constant.ALL);
            if (projSelDTO.getActualsOrProjections().equals(BOTH.getConstant())) {
                projSelDTO.setActualsOrProjections(ACTUALS.getConstant() + AND_SMALL + PROJECTIONS.getConstant());
            }
            if (parentId instanceof DiscountProjectionResultsDTO) {
                projSelDTO.setIsProjectionTotal(false);
                DiscountProjectionResultsDTO parentDto = (DiscountProjectionResultsDTO) parentId;
                projSelDTO.setLevelNo(parentDto.getLevelNo());
                projSelDTO.setTreeLevelNo(parentDto.getTreeLevelNo());
                projSelDTO.setLevelValue(parentDto.getLevelValue());
                projSelDTO.setParentNode(parentDto.getParentNode());
                projSelDTO.setHierarchyNo(parentDto.getHierarchyNo());
                if (parentDto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
                    projSelDTO.setCustomerHierarchyNo(projSelDTO.getHierarchyNo());
                    projSelDTO.setProductHierarchyNo(parentDto.getProductHierarchyNo());
                } else if (parentDto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
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
                    projSelDTO.setLevelNo(0);
                    projSelDTO.setTreeLevelNo(0);
                } else if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                    projSelDTO.setLevelNo(projSelDTO.getCustomerLevelNo() - 1);
                    projSelDTO.setTreeLevelNo(projSelDTO.getCustomerLevelNo() - 1);
                } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
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
    public List<DiscountProjectionResultsDTO> configureLevels(int start, int offset, ProjectionSelectionDTO projSelDTO) {
        int neededRecord = offset;
        List<DiscountProjectionResultsDTO> resultList = new ArrayList<>();
        if (neededRecord > 0) {
            List<Leveldto> levelList = CommonLogic.getConditionalLevelList(projSelDTO.getProjectionId(), Constant.DISCOUNT_PROJECTION_RESULTS, start, offset, projSelDTO.getHierarchyIndicator(), projSelDTO.getTreeLevelNo(), projSelDTO.getHierarchyNo(), projSelDTO.getProductHierarchyNo(), projSelDTO.getCustomerHierarchyNo(), projSelDTO.isIsFilter(), false, projSelDTO.isIsCustomHierarchy(), projSelDTO.getCustomId(), projSelDTO.getGroupFilter(), projSelDTO.getUserId(), projSelDTO.getSessionId(), projSelDTO.getCustRelationshipBuilderSid(), projSelDTO.getProdRelationshipBuilderSid(), false, true, projSelDTO.getDiscountNoList(),projSelDTO);
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
                if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
                    dto.setCustomerHierarchyNo(dto.getHierarchyNo());
                    dto.setProductHierarchyNo(projSelDTO.getProductHierarchyNo());
                } else if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
                    dto.setProductHierarchyNo(dto.getHierarchyNo());
                    dto.setCustomerHierarchyNo(projSelDTO.getCustomerHierarchyNo());
                }

                dto.setParent(1);
                dto.setTotal(0);
                DiscountProjectionResultsDTO discountDTO;
                if (projSelDTO.getPivotView().equals(Constant.PERIOD)) {
                    projSelDTO.setHierarchyIndicator(levelDto.getHierarchyIndicator());
                    projSelDTO.setTreeLevelNo(levelDto.getTreeLevelNo());
                    projSelDTO.setCustomerHierarchyNo(dto.getCustomerHierarchyNo());
                    projSelDTO.setProductHierarchyNo(dto.getProductHierarchyNo());
                    discountDTO = getChildNodeValues(dto, projSelDTO, null);
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
    public int getConfiguredProjectionResultsCount(Object parentId, ProjectionSelectionDTO projSelDTO, boolean isLevelsCount) {

        int count = 0;
        if (!projSelDTO.isIsFilter() || (parentId instanceof DiscountProjectionResultsDTO)) {
            projSelDTO.setYear(Constant.ALL);
            if (projSelDTO.getActualsOrProjections().equals(Constant.BOTH)) {
                projSelDTO.setActualsOrProjections(ACTUALS.getConstant() + AND_SMALL + PROJECTIONS.getConstant());
            }
            if (parentId instanceof DiscountProjectionResultsDTO) {
                projSelDTO.setIsProjectionTotal(false);
                DiscountProjectionResultsDTO parentDto = (DiscountProjectionResultsDTO) parentId;
                projSelDTO.setLevelNo(parentDto.getLevelNo());
                projSelDTO.setTreeLevelNo(parentDto.getTreeLevelNo());
                projSelDTO.setLevelValue(parentDto.getLevelValue());
                projSelDTO.setParentNode(parentDto.getParentNode());
                projSelDTO.setHierarchyNo(parentDto.getHierarchyNo());
                if (parentDto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
                    projSelDTO.setCustomerHierarchyNo(projSelDTO.getHierarchyNo());
                    projSelDTO.setProductHierarchyNo(parentDto.getProductHierarchyNo());
                } else if (parentDto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
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
                } else if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                    projSelDTO.setLevelNo(projSelDTO.getCustomerLevelNo() - 1);
                    projSelDTO.setTreeLevelNo(projSelDTO.getCustomerLevelNo() - 1);
                } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
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

    public int getProjectionResultsCount(ProjectionSelectionDTO projSelDTO, boolean isLevelsCount) {
        int count = 0;
        projSelDTO.setGroupCount(false);
        if (!projSelDTO.getGroup().startsWith(Constant.DISCOUNT)) {
            if (projSelDTO.getPivotView().contains(PERIOD.getConstant())) {
                count = count + projSelDTO.getDiscountNoList().size();
            } else {
                count = count + projSelDTO.getPeriodList().size();
            }
        }
        if (projSelDTO.isIsProjectionTotal()) {
            count++;
        }

        if (isLevelsCount && !projSelDTO.isIsFilter()) {
            if ((projSelDTO.getTreeLevelNo() + 1) == projSelDTO.getTpLevel()
                    && ((projSelDTO.isIsCustomHierarchy()) || (!projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)))
                    && !projSelDTO.getGroupFilter().equals(Constant.ALL_DISCOUNT_GROUP) && !projSelDTO.getGroup().startsWith(Constant.DISCOUNT)) {
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

    public int configureLevelsCount(ProjectionSelectionDTO projSelDTO) {
        return CommonLogic.getLevelListCount(projSelDTO.getProjectionId(), Constant.DISCOUNT_PROJECTION_RESULTS, projSelDTO.getHierarchyIndicator(), projSelDTO.getTreeLevelNo(), projSelDTO.getHierarchyNo(), projSelDTO.getProductHierarchyNo(), projSelDTO.getCustomerHierarchyNo(), projSelDTO.isIsFilter(), projSelDTO.isIsCustomHierarchy(), projSelDTO.getCustomId(), projSelDTO.getGroupFilter(), projSelDTO.getUserId(), projSelDTO.getSessionId(), projSelDTO.getCustRelationshipBuilderSid(), projSelDTO.getProdRelationshipBuilderSid(), projSelDTO.getDiscountNoList(),projSelDTO);
    }

    /**
     * This method is used to Load aggregate value of child node
     *
     * @param dto
     * @param projSelDTO
     * @return
     *
     */
    public DiscountProjectionResultsDTO getChildNodeValues(DiscountProjectionResultsDTO dto, ProjectionSelectionDTO projSelDTO, List ccp) {
        try {
              boolean viewFlag=Constant.VIEW.equals(projSelDTO.getSessionDTO().getAction());
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
            if (freq.equals(Constant.QUARTERLY)) {
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
            } else if (freq.equals(Constant.SEMI_ANNUALLY)) {
                int month = projSelDTO.getForecastDTO().getForecastStartMonth();
                if (month <= NumericConstants.SIX) {
                    yearList.add(1);
                } else if (month >= NumericConstants.SEVEN && month < NumericConstants.THIRTEEN) {
                    yearList.add(NumericConstants.SEVEN);
                }
            } else if (freq.equals(Constant.ANNUALLY)) {
                yearList.add(1);
            } else if (freq.equals(Constant.MONTHLY)) {
                int month = projSelDTO.getForecastDTO().getForecastStartMonth();
                yearList.add(month);
            }
            yearList.add(projSelDTO.getForecastDTO().getForecastEndYear());
            yearList.add(projSelDTO.getForecastDTO().getForecastEndMonth());
            String selectedView = projSelDTO.getView();
            List ccpId = null;
            if (ccp == null) {
                if (selectedView.equals(Constant.CUSTOMER_SMALL)) {
                    ccpId = new NmDiscountImpl().getCCPDetailsID(projectionMasterId, hierachyNumber, String.valueOf(dto.getTreeLevelNo()));
                }
                if (selectedView.equals(Constant.PRODUCT_LABEL)) {
                    ccpId = new NmDiscountImpl().getCCPDetailsIDForProductHierarchy(projectionMasterId, hierachyNumber, String.valueOf(dto.getTreeLevelNo()));
                }
                if (selectedView.equals(Constant.CUSTOM_LABEL)) {
                    projSelDTO.setIsCustomHierarchy(true);
                    String customQuery = CommonLogic.getCustomCCPQuery(projSelDTO);
                    List<Object> list = (List<Object>) executeSelectQuery(customQuery, null, null);
                    if (list != null && !list.isEmpty()) {
                        ccpId = new ArrayList();
                        for (int i = 0; i < list.size(); i++) {
                            Object[] obj = (Object[]) list.get(i);
                            String id = String.valueOf(obj[1]);
                            if (!id.equals(Constant.NULL)) {
                                int ccpIds = Integer.parseInt(id);
                                ccpId.add(ccpIds);
                            }
                        }
                    }
                }
            } else {
                ccpId = ccp;
            }
            if (ccpId != null && !ccpId.isEmpty()) {
                final DynamicQuery projectionDetailsDynamicQuery = ProjectionDetailsLocalServiceUtil.dynamicQuery();
                projectionDetailsDynamicQuery.add(RestrictionsFactoryUtil.in(Constant.CCP_DETAILS_SID, ccpId));
                projectionDetailsDynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_MASTER_SID, projectionMasterId));
                ProjectionList projList = ProjectionFactoryUtil.projectionList();
                projList.add(ProjectionFactoryUtil.property(Constant.PROJECTION_DETAILS_SID));
                projectionDetailsDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projList));
                List<Integer> projectionDetailsId = ProjectionDetailsLocalServiceUtil.dynamicQuery(projectionDetailsDynamicQuery);
                if (projectionDetailsId != null && !projectionDetailsId.isEmpty()) {
                    String userId = String.valueOf(projSelDTO.getUserId());
                    String sessionId = String.valueOf(projSelDTO.getSessionId());
                    int user = Integer.parseInt(userId);
                    int session = Integer.parseInt(sessionId);
                    String frequency = projSelDTO.getFrequency();
                    List<String> discountList;
                    discountList = projSelDTO.getDiscountNameList();
                    String discountString = getDiscountName(discountList);
                    List list = new NmDiscountImpl().getDiscountProjectionResults(projectionDetailsId, frequency, discountString, StringUtils.EMPTY, Constant.PARENT, StringUtils.EMPTY, yearList, user, session,viewFlag);
                    if (list != null && !list.isEmpty()) {
                        if (frequency.equals(QUARTERLY.getConstant())) {
                            dto = getValueForDTO(projSelDTO, list, dto, Constant.Q_SMALL);
                        }
                        if (frequency.equals(Constant.SEMI_ANNUALLY)) {
                            dto = getValueForDTO(projSelDTO, list, dto, Constant.S_SMALL);
                        }
                        if (frequency.equals(Constant.ANNUALLY)) {
                            dto = getValueForYearDTO(projSelDTO, list, dto);
                        }
                        if (frequency.equals(MONTHLY.getConstant())) {
                            dto = getValueForMonthDTO(projSelDTO, list, dto);
                        }
                    }
                }
            }
        } catch (SystemException | NumberFormatException e) {
            LoggerFactory.getLogger(DiscountProjectionResultsLogic.class.getName()).error( StringUtils.EMPTY, e);
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
    public DiscountProjectionResultsDTO getPivotChildNodeValues(DiscountProjectionResultsDTO dto, ProjectionSelectionDTO projSelDTO) {
        try {
            int projectionMasterId = projSelDTO.getProjectionId();
            dto.setIsParent(Constant.STRING_ONE);
            dto.setParent(1);
            String hierachyNumber = String.valueOf(dto.getHierarchyNo());
            hierachyNumber = hierachyNumber + PERCENTAGE;
            List<Integer> pivotYearList = new ArrayList<>();
            String frequency = String.valueOf(projSelDTO.getFrequency());
            if (frequency.equals(Constant.ANNUALLY)) {
                pivotYearList.add(Integer.valueOf(projSelDTO.getPeriodList().get(0)));
                pivotYearList.add(1);
                pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndYear());
                pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndMonth());
            } else if (frequency.equals(Constant.QUARTERLY)) {
                String column = String.valueOf(projSelDTO.getPeriodList().get(0));
                column = column.replace(Constant.Q_SMALL, StringUtils.EMPTY);
                String year = column.substring(column.length() - NumericConstants.FOUR, column.length());
                String fre = column.replace(year, StringUtils.EMPTY);
                pivotYearList.add(Integer.valueOf(year));
                if (fre.equals(Constant.STRING_ONE)) {
                    pivotYearList.add(1);
                } else if (fre.equals("2")) {
                    pivotYearList.add(NumericConstants.FOUR);
                } else if (fre.equals("3")) {
                    pivotYearList.add(NumericConstants.SEVEN);
                } else if (fre.equals("4")) {
                    pivotYearList.add(NumericConstants.TEN);
                }
                pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndYear());
                pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndMonth());
            } else if (frequency.equals(Constant.MONTHLY)) {
                loadKeyMap();
                String column = String.valueOf(projSelDTO.getPeriodList().get(0));
                String fre = column.substring(0, NumericConstants.THREE);
                String year = column.replace(fre, StringUtils.EMPTY);
                String month = valueMap.get(fre);
                pivotYearList.add(Integer.valueOf(year));
                pivotYearList.add(Integer.valueOf(month));
                pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndYear());
                pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndMonth());
            } else if (frequency.equals(Constant.SEMI_ANNUALLY)) {
                String column = String.valueOf(projSelDTO.getPeriodList().get(0));
                column = column.replace(Constant.S_SMALL, StringUtils.EMPTY);
                String year = column.substring(column.length() - NumericConstants.FOUR, column.length());
                String fre = column.replace(year, StringUtils.EMPTY);
                pivotYearList.add(Integer.valueOf(year));
                if (fre.equals(Constant.STRING_ONE)) {
                    pivotYearList.add(1);
                } else if (fre.equals("2")) {
                    pivotYearList.add(NumericConstants.SEVEN);
                }
                pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndYear());
                pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndMonth());
            }

            pivotYearList.add(projSelDTO.getForecastDTO().getForecastStartYear());
            pivotYearList.add(projSelDTO.getForecastDTO().getForecastStartMonth());
            pivotYearList.add(projSelDTO.getForecastDTO().getForecastEndYear());
            pivotYearList.add(projSelDTO.getForecastDTO().getForecastEndMonth());
            String selectedView = projSelDTO.getView();
            List ccpId = null;
            if (selectedView.equals(Constant.CUSTOMER_SMALL)) {
                ccpId = new NmDiscountImpl().getCCPDetailsID(projectionMasterId, hierachyNumber, String.valueOf(dto.getTreeLevelNo()));
            }
            if (selectedView.equals(Constant.PRODUCT_LABEL)) {
                ccpId = new NmDiscountImpl().getCCPDetailsIDForProductHierarchy(projectionMasterId, hierachyNumber, String.valueOf(dto.getTreeLevelNo()));
            }
            if (selectedView.equals(Constant.CUSTOM_LABEL)) {
                projSelDTO.setIsCustomHierarchy(true);
                String customQuery = CommonLogic.getCustomCCPQuery(projSelDTO);
                List<Object> list = (List<Object>) executeSelectQuery(customQuery, null, null);
                if (list != null && !list.isEmpty()) {
                    ccpId = new ArrayList();
                    for (int i = 0; i < list.size(); i++) {
                        Object[] obj = (Object[]) list.get(i);
                        String id = String.valueOf(obj[1]);
                        if (!id.equals(Constant.NULL)) {
                            int ccpIds = Integer.parseInt(id);
                            ccpId.add(ccpIds);
                        }
                    }
                }
            }
            if (ccpId != null && !ccpId.isEmpty()) {
                final DynamicQuery projectiondetailsDynamicQuery = ProjectionDetailsLocalServiceUtil.dynamicQuery();
                projectiondetailsDynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_MASTER_SID, projectionMasterId));
                projectiondetailsDynamicQuery.add(RestrictionsFactoryUtil.in(Constant.CCP_DETAILS_SID, ccpId));
                final ProjectionList projList = ProjectionFactoryUtil.projectionList();
                projList.add(ProjectionFactoryUtil.property(Constant.PROJECTION_DETAILS_SID));
                projectiondetailsDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projList));
                List proDetailsSid = ProjectionDetailsLocalServiceUtil.dynamicQuery(projectiondetailsDynamicQuery);
                if (proDetailsSid != null && !proDetailsSid.isEmpty()) {
                    String freq = String.valueOf(projSelDTO.getFrequency());
                    String userId = String.valueOf(projSelDTO.getUserId());
                    String sessionId = String.valueOf(projSelDTO.getSessionId());
                    int user = Integer.parseInt(userId);
                    int session = Integer.parseInt(sessionId);
                    List<String> discountList;
                    List<String> tmpList = new ArrayList<>();
                    discountList = projSelDTO.getDiscountNameList();
                    String discountString = getDiscountName(discountList);
                    List list = new NmDiscountImpl().getTotalDiscountNumber(proDetailsSid, freq, discountString, pivotYearList, user, session,null);
                    if (list != null && !list.isEmpty()) {
                        DiscountProjectionResultsDTO discountDto = new DiscountProjectionResultsDTO();
                        discountDto.setHierarchySid(ZERO_SYMBOL);
                        discountDto.setLevelNo(0);
                        discountDto.setHierarchylevelId(ZERO_SYMBOL);
                        discountDto.setIsParent(Constant.STRING_ONE);
                        discountDto.setHierarchyNo(Constant.STRING_ONE);
                        discountDto.setTotal(1);
                        dto.setParent(1);
                        double actualSales = 0;
                        double actualAmount = 0;
                        double projectedSales = 0;
                        double projectedAmount = 0;
                        String commonColumn;
                        Object[] object = (Object[]) list.get(0);
                        String currentDiscount = String.valueOf(object[NumericConstants.EIGHT]);
                        tmpList.addAll(discountList);

                        tmpList.remove(currentDiscount);
                        if (object[NumericConstants.TWO] != null) {
                            Double aSales = DataTypeConverter.convertObjectToDouble(object[NumericConstants.TWO]);
                            actualSales = actualSales + aSales;
                        }
                        if (object[NumericConstants.THREE] != null) {
                            Double aAmount = DataTypeConverter.convertObjectToDouble(object[NumericConstants.THREE]);
                            actualAmount = actualAmount + aAmount;
                        }
                        if (object[NumericConstants.FOUR] != null) {
                            Double pSales = DataTypeConverter.convertObjectToDouble(object[NumericConstants.FOUR]);
                            projectedSales = projectedSales + pSales;
                        }
                        if (object[NumericConstants.FIVE] != null) {
                            Double pAmount = DataTypeConverter.convertObjectToDouble(object[NumericConstants.FIVE]);
                            projectedAmount = projectedAmount + pAmount;
                        }
                        commonColumn = currentDiscount.replace(" ", StringUtils.EMPTY);
                        if (list.size() == 1) {
                            Double arate = actualAmount / actualSales;
                            if (arate.isNaN()) {
                                arate = 0.0;
                            }
                            arate = arate * NumericConstants.HUNDRED;
                            String actRate = String.valueOf(arate);
                            dto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                            Double actualAmt = arate * actualSales;
                            actualAmt = actualAmt / NumericConstants.HUNDRED;
                            if (actualAmt.isNaN()) {
                                actualAmt = 0.0;
                            }
                            String actAmt = String.valueOf(actualAmt);
                            dto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                            Double prate = projectedAmount / projectedSales;
                            if (prate.isNaN() || prate.isInfinite()) {
                                prate = 0.0;
                            }
                            prate = prate * NumericConstants.HUNDRED;
                            String proRate = String.valueOf(prate);
                            dto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                            Double projectedAmtAmt = prate * projectedSales;
                            projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                            if (projectedAmtAmt.isNaN()) {
                                projectedAmtAmt = 0.0;
                            }
                            String proAmount = String.valueOf(projectedAmtAmt);
                            dto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                        } else {
                            for (int i = 1; i < list.size(); i++) {
                                Object[] obj = (Object[]) list.get(i);
                                String selectedDiscount = String.valueOf(obj[NumericConstants.EIGHT]);
                                if (currentDiscount.equals(selectedDiscount)) {
                                    if (obj[NumericConstants.TWO] != null) {
                                        Double aSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.TWO]);
                                        actualSales = actualSales + aSales;
                                    }
                                    if (obj[NumericConstants.THREE] != null) {
                                        Double aAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.THREE]);
                                        actualAmount = actualAmount + aAmount;
                                    }
                                    if (obj[NumericConstants.FOUR] != null) {
                                        Double pSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FOUR]);
                                        projectedSales = projectedSales + pSales;
                                    }
                                    if (obj[NumericConstants.FIVE] != null) {
                                        Double pAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FIVE]);
                                        projectedAmount = projectedAmount + pAmount;
                                    }
                                } else {
                                    Double arate = actualAmount / actualSales;
                                    if (arate.isNaN()) {
                                        arate = 0.0;
                                    }
                                    arate = arate * NumericConstants.HUNDRED;
                                    String actRate = String.valueOf(arate);

                                    dto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                    Double actualAmt = arate * actualSales;
                                    actualAmt = actualAmt / NumericConstants.HUNDRED;
                                    if (actualAmt.isNaN()) {
                                        actualAmt = 0.0;
                                    }
                                    String actAmt = String.valueOf(actualAmt);
                                    dto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                                    Double prate = projectedAmount / projectedSales;
                                    if (prate.isNaN() || prate.isInfinite()) {
                                        prate = 0.0;
                                    }
                                    prate = prate * NumericConstants.HUNDRED;
                                    String proRate = String.valueOf(prate);
                                    dto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                    Double projectedAmtAmt = prate * projectedSales;
                                    projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                                    if (projectedAmtAmt.isNaN()) {
                                        projectedAmtAmt = 0.0;
                                    }
                                    String proAmount = String.valueOf(projectedAmtAmt);
                                    dto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                    actualSales = 0;
                                    actualAmount = 0;
                                    projectedSales = 0;
                                    projectedAmount = 0;
                                    currentDiscount = selectedDiscount;
                                    tmpList.remove(currentDiscount);
                                    commonColumn = currentDiscount.replace(" ", StringUtils.EMPTY);
                                    if (obj[NumericConstants.TWO] != null) {
                                        Double aSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.TWO]);
                                        actualSales = actualSales + aSales;
                                    }
                                    if (obj[NumericConstants.THREE] != null) {
                                        Double aAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.THREE]);
                                        actualAmount = actualAmount + aAmount;
                                    }
                                    if (obj[NumericConstants.FOUR] != null) {
                                        Double pSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FOUR]);
                                        projectedSales = projectedSales + pSales;
                                    }
                                    if (obj[NumericConstants.FIVE] != null) {
                                        Double pAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FIVE]);
                                        projectedAmount = projectedAmount + pAmount;
                                    }
                                }
                                if (i == list.size() - 1) {
                                    Double arate = actualAmount / actualSales;
                                    if (arate.isNaN()) {
                                        arate = 0.0;
                                    }
                                    arate = arate * NumericConstants.HUNDRED;
                                    String actRate = String.valueOf(arate);
                                    dto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                    Double actualAmt = arate * actualSales;
                                    actualAmt = actualAmt / NumericConstants.HUNDRED;
                                    if (actualAmt.isNaN()) {
                                        actualAmt = 0.0;
                                    }
                                    String actAmt = String.valueOf(actualAmt);
                                    dto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                                    Double prate = projectedAmount / projectedSales;
                                    if (prate.isNaN() || prate.isInfinite()) {
                                        prate = 0.0;
                                    }
                                    prate = prate * NumericConstants.HUNDRED;
                                    String proRate = String.valueOf(prate);
                                    dto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                    Double projectedAmtAmt = prate * projectedSales;
                                    projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                                    if (projectedAmtAmt.isNaN()) {
                                        projectedAmtAmt = 0.0;
                                    }
                                    String proAmount = String.valueOf(projectedAmtAmt);
                                    dto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                }
                            }
                        }
                        if (!tmpList.isEmpty()) {
                            for (int j = 0; j < tmpList.size(); j++) {
                                String column = tmpList.get(j);
                                commonColumn = column.replace(" ", StringUtils.EMPTY);
                                String columns = commonColumn + ACTUALSRATE;
                                dto.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
                                String columns1 = commonColumn + ACTUALSAMOUNT;
                                dto.addStringProperties(columns1, getFormattedValue(CUR_ZERO, Constant.NULL));
                                String columns2 = commonColumn + PROJECTIONSAMOUNT;
                                dto.addStringProperties(columns2, getFormattedValue(CUR_ZERO, Constant.NULL));
                                String columns3 = commonColumn + PROJECTIONSRATE;
                                dto.addStringProperties(columns3, getFormattedValue(CUR_ZERO, Constant.NULL));
                            }
                        }

                        return dto;
                    }
                }
            }
        } catch (SystemException | NumberFormatException e) {
            LoggerFactory.getLogger(DiscountProjectionResultsLogic.class.getName()).error( StringUtils.EMPTY, e);
        }
        return dto;
    }



    public String getTradingPartnerLevel(int projectionMasterId) {
        String query = "SELECT distinct RLD1.level_no from  relationship_level_definition RLD1 JOIN PROJECTION_CUST_HIERARCHY PCH "
                + " ON PCH.relationship_level_sid = RLD1.relationship_level_sid  AND PCH.projection_master_sid =" + projectionMasterId + StringUtils.EMPTY
                + " WHERE RLD1.level_Name='Trading Partner'";
        return query;
    }

    public String getFormattedValue(DecimalFormat decFormat, String value) {
        if (value.contains(Constant.NULL)) {
            value = DASH.getConstant();
        } else {
            Double newValue = Double.valueOf(value);
            if (decFormat.toPattern().contains(Constant.PERCENT)) {
                newValue = newValue / NumericConstants.HUNDRED;
            }
            value = decFormat.format(newValue);
        }
        return value;
    }

    private String createColumn(String group) {
        String value = group.replace(CommonUtils.BUSINESS_PROCESS_INDICATOR_MANDATED, StringUtils.EMPTY);
        String year = value.substring(value.length() - NumericConstants.FOUR, value.length());
        String mon = value.replace(year, StringUtils.EMPTY);
        String month = monthMap.get(mon);
        String commonColumn = month + year;
        return commonColumn;
    }

    private void loadMap() {
        monthMap.put(Constant.STRING_ONE, "jan");
        monthMap.put("2", "feb");
        monthMap.put("3", "mar");
        monthMap.put("4", "apr");
        monthMap.put("5", "may");
        monthMap.put("6", "jun");
        monthMap.put("7", "jul");
        monthMap.put("8", "aug");
        monthMap.put("9", "sep");
        monthMap.put("10", "oct");
        monthMap.put("11", "nov");
        monthMap.put("12", "dec");
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
            discountString = ZERO_SYMBOL;
        }
        return discountString;
    }

    private DiscountProjectionResultsDTO putHyphenForDTO(List<String> periodList, DiscountProjectionResultsDTO discountDto) {
        if (!periodList.isEmpty()) {
            for (int j = 0; j < periodList.size(); j++) {
                String column = periodList.get(j);
                String columns = column + ACTUALSRATE;
                discountDto.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
                String columns1 = column + ACTUALSAMOUNT;
                discountDto.addStringProperties(columns1, getFormattedValue(CUR_ZERO, Constant.NULL));
                String columns2 = column + PROJECTIONSAMOUNT;
                discountDto.addStringProperties(columns2, getFormattedValue(CUR_ZERO, Constant.NULL));
                String columns3 = column + PROJECTIONSRATE;
                discountDto.addStringProperties(columns3, getFormattedValue(CUR_ZERO, Constant.NULL));
            }
        }
        return discountDto;
    }

    private DiscountProjectionResultsDTO getValueForDTO(ProjectionSelectionDTO projSelDTO, List list, DiscountProjectionResultsDTO discountDto, String freq) {
        double actualSales = 0;
        double actualAmount = 0;
        double projectedSales = 0;
        double projectedAmount = 0;
        int year = 0;
        int quarter = 0;

        List<String> periodList = new ArrayList<>(projSelDTO.getPeriodList());
        String commonColumn = StringUtils.EMPTY;
        for (int i = 0; i < list.size(); i++) {
            final Object[] obj = (Object[]) list.get(i);
            int selectedYear = 0;
            int selectedQuarter = 0;
            if (obj[0] != null) {
                selectedYear = (Integer) obj[0];
            }
            if (obj[NumericConstants.SIX] != null) {
                selectedQuarter = (Integer) obj[NumericConstants.SIX];
            }
            if (year == selectedYear && quarter == selectedQuarter) {
                if (obj[NumericConstants.TWO] != null) {
                    Double aSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.TWO]);
                    actualSales = actualSales + aSales;
                }
                if (obj[NumericConstants.THREE] != null) {
                    Double aAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.THREE]);
                    actualAmount = actualAmount + aAmount;
                }
                if (obj[NumericConstants.FOUR] != null) {
                    Double pSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FOUR]);
                    projectedSales = projectedSales + pSales;
                }
                if (obj[NumericConstants.FIVE] != null) {
                    Double pAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FIVE]);
                    projectedAmount = projectedAmount + pAmount;
                }
            } else {
                if (i == 0) {
                    year = selectedYear;
                    quarter = selectedQuarter;
                    commonColumn = freq + obj[NumericConstants.SIX] + obj[0];
                    if (obj[NumericConstants.TWO] != null) {
                        Double aSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.TWO]);
                        actualSales = actualSales + aSales;
                    }
                    if (obj[NumericConstants.THREE] != null) {
                        Double aAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.THREE]);
                        actualAmount = actualAmount + aAmount;
                    }

                    if (obj[NumericConstants.FOUR] != null) {
                        Double pSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FOUR]);
                        projectedSales = projectedSales + pSales;
                    }
                    if (obj[NumericConstants.FIVE] != null) {
                        Double pAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FIVE]);
                        projectedAmount = projectedAmount + pAmount;
                    }
                } else {
                    Double arate = actualAmount / actualSales;
                    if (arate.isNaN()) {
                        arate = 0.0;
                    }
                    arate = arate * NumericConstants.HUNDRED;
                    String actualsRate = String.valueOf(arate);
                    discountDto.addStringProperties(commonColumn + ACTUALSRATE, actualsRate != null && !NULL.equals(String.valueOf(actualsRate)) && !StringUtils.EMPTY.equals(String.valueOf(actualsRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actualsRate)))).concat(PERCENTAGE) : HYPHEN);
                    Double actualAmt = arate * actualSales;
                    actualAmt = actualAmt / NumericConstants.HUNDRED;
                    if (actualAmt.isNaN()) {
                        actualAmt = 0.0;
                    }
                    String actualsAmount = String.valueOf(actualAmt);
                    discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actualsAmount != null && !NULL.equals(String.valueOf(actualsAmount)) && !StringUtils.EMPTY.equals(String.valueOf(actualsAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actualsAmount)))) : HYPHEN);
                    Double prate = projectedAmount / projectedSales;
                    if (prate.isNaN() || prate.isInfinite()) {
                        prate = 0.0;
                    }
                    prate = prate * NumericConstants.HUNDRED;
                    String prorate = String.valueOf(prate);
                    discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, prorate != null && !NULL.equals(String.valueOf(prorate)) && !StringUtils.EMPTY.equals(String.valueOf(prorate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(prorate)))).concat(PERCENTAGE) : HYPHEN);
                    Double projectedAmtAmt = prate * projectedSales;
                    projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                    if (projectedAmtAmt.isNaN()) {
                        projectedAmtAmt = 0.0;
                    }
                    String proAmount = String.valueOf(projectedAmtAmt);
                    discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                    periodList.remove(commonColumn);
                    actualSales = 0;
                    actualAmount = 0;
                    projectedSales = 0;
                    projectedAmount = 0;
                    commonColumn = freq + obj[NumericConstants.SIX] + obj[0];
                    year = (Integer) obj[0];
                    quarter = (Integer) obj[NumericConstants.SIX];
                    if (obj[NumericConstants.TWO] != null) {
                        Double aSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.TWO]);
                        actualSales = actualSales + aSales;
                    }
                    if (obj[NumericConstants.THREE] != null) {
                        Double aAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.THREE]);
                        actualAmount = actualAmount + aAmount;
                    }
                    if (obj[NumericConstants.FOUR] != null) {
                        Double pSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FOUR]);
                        projectedSales = projectedSales + pSales;
                    }
                    if (obj[NumericConstants.FIVE] != null) {
                        Double pAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FIVE]);
                        projectedAmount = projectedAmount + pAmount;
                    }
                }
            }
            if (i == list.size() - 1) {
                Double arate = actualAmount / actualSales;
                if (arate.isNaN()) {
                    arate = 0.0;
                }
                arate = arate * NumericConstants.HUNDRED;
                String actualsRate = String.valueOf(arate);
                discountDto.addStringProperties(commonColumn + ACTUALSRATE, actualsRate != null && !NULL.equals(String.valueOf(actualsRate)) && !StringUtils.EMPTY.equals(String.valueOf(actualsRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actualsRate)))).concat(PERCENTAGE) : HYPHEN);
                Double actualAmt = arate * actualSales;
                actualAmt = actualAmt / NumericConstants.HUNDRED;
                if (actualAmt.isNaN()) {
                    actualAmt = 0.0;
                }
                String actualsAmount = String.valueOf(actualAmt);
                discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actualsAmount != null && !NULL.equals(String.valueOf(actualsAmount)) && !StringUtils.EMPTY.equals(String.valueOf(actualsAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actualsAmount)))) : HYPHEN);
                Double prate = projectedAmount / projectedSales;
                if (prate.isNaN() || prate.isInfinite()) {
                    prate = 0.0;
                }
                prate = prate * NumericConstants.HUNDRED;
                discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, prate != null && !NULL.equals(String.valueOf(prate)) && !StringUtils.EMPTY.equals(String.valueOf(prate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(prate)))).concat(PERCENTAGE) : HYPHEN);
                Double projectedAmtAmt = prate * projectedSales;
                projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                if (projectedAmtAmt.isNaN()) {
                    projectedAmtAmt = 0.0;
                }
                discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, projectedAmtAmt != null && !NULL.equals(String.valueOf(projectedAmtAmt)) && !StringUtils.EMPTY.equals(String.valueOf(projectedAmtAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(projectedAmtAmt)))) : HYPHEN);
                periodList.remove(commonColumn);
                actualSales = 0;
                actualAmount = 0;
                projectedSales = 0;
                projectedAmount = 0;
                commonColumn = freq + obj[NumericConstants.SIX] + obj[0];
                year = (Integer) obj[0];
                quarter = (Integer) obj[NumericConstants.SIX];
            }
        }
        discountDto = putHyphenForDTO(periodList, discountDto);
        return discountDto;
    }

    private DiscountProjectionResultsDTO getValueForYearDTO(ProjectionSelectionDTO projSelDTO, List list, DiscountProjectionResultsDTO discountDto) {
        double actualSales = 0;
        double actualAmount = 0;
        double projectedSales = 0;
        double projectedAmount = 0;
        int year = 0;
        List<String> periodList = new ArrayList<>(projSelDTO.getPeriodList());
        String commonColumn = StringUtils.EMPTY;
        for (int i = 0; i < list.size(); i++) {
            final Object[] obj = (Object[]) list.get(i);
            if (i == 0) {

            }
            int selectedYear = 0;
            if (obj[0] != null) {
                selectedYear = (Integer) obj[0];
            }
            if (year == selectedYear) {
                if (obj[NumericConstants.TWO] != null) {
                    Double aSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.TWO]);
                    actualSales = actualSales + aSales;
                }
                if (obj[NumericConstants.THREE] != null) {
                    Double aAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.THREE]);
                    actualAmount = actualAmount + aAmount;
                }
                if (obj[NumericConstants.FOUR] != null) {
                    Double pSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FOUR]);
                    projectedSales = projectedSales + pSales;
                }
                if (obj[NumericConstants.FIVE] != null) {
                    Double pAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FIVE]);
                    projectedAmount = projectedAmount + pAmount;
                }

            } else {
                if (i == 0) {
                    year = selectedYear;
                    commonColumn = StringUtils.EMPTY + obj[0];
                    if (obj[NumericConstants.TWO] != null) {
                        Double aSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.TWO]);
                        actualSales = actualSales + aSales;
                    }
                    if (obj[NumericConstants.THREE] != null) {
                        Double aAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.THREE]);
                        actualAmount = actualAmount + aAmount;
                    }
                    if (obj[NumericConstants.FOUR] != null) {
                        Double pSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FOUR]);
                        projectedSales = projectedSales + pSales;
                    }
                    if (obj[NumericConstants.FIVE] != null) {
                        Double pAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FIVE]);
                        projectedAmount = projectedAmount + pAmount;
                    }
                } else {
                    Double arate = actualAmount / actualSales;
                    if (arate.isNaN()) {
                        arate = 0.0;
                    }
                    arate = arate * NumericConstants.HUNDRED;
                    discountDto.addStringProperties(commonColumn + ACTUALSRATE, arate != null && !NULL.equals(String.valueOf(arate)) && !StringUtils.EMPTY.equals(String.valueOf(arate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(arate)))).concat(PERCENTAGE) : HYPHEN);
                    Double actualAmt = arate * actualSales;
                    actualAmt = actualAmt / NumericConstants.HUNDRED;
                    if (actualAmt.isNaN()) {
                        actualAmt = 0.0;
                    }
                    discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actualAmt != null && !NULL.equals(String.valueOf(actualAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actualAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actualAmt)))) : HYPHEN);
                    Double prate = projectedAmount / projectedSales;
                    if (prate.isNaN() || prate.isInfinite()) {
                        prate = 0.0;
                    }
                    prate = prate * NumericConstants.HUNDRED;
                    discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, prate != null && !NULL.equals(String.valueOf(prate)) && !StringUtils.EMPTY.equals(String.valueOf(prate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(prate)))).concat(PERCENTAGE) : HYPHEN);
                    Double projectedAmtAmt = prate * projectedSales;
                    projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                    if (projectedAmtAmt.isNaN()) {
                        projectedAmtAmt = 0.0;
                    }
                    discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, projectedAmtAmt != null && !NULL.equals(String.valueOf(projectedAmtAmt)) && !StringUtils.EMPTY.equals(String.valueOf(projectedAmtAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(projectedAmtAmt)))) : HYPHEN);
                    periodList.remove(commonColumn);
                    actualSales = 0;
                    actualAmount = 0;
                    projectedSales = 0;
                    projectedAmount = 0;
                    commonColumn = StringUtils.EMPTY + obj[0];
                    year = (Integer) obj[0];
                    if (obj[NumericConstants.TWO] != null) {
                        Double aSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.TWO]);
                        actualSales = actualSales + aSales;
                    }
                    if (obj[NumericConstants.THREE] != null) {
                        Double aAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.THREE]);
                        actualAmount = actualAmount + aAmount;
                    }
                    if (obj[NumericConstants.FOUR] != null) {
                        Double pSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FOUR]);
                        projectedSales = projectedSales + pSales;
                    }
                    if (obj[NumericConstants.FIVE] != null) {
                        Double pAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FIVE]);
                        projectedAmount = projectedAmount + pAmount;
                    }
                }
            }
            if (i == list.size() - 1) {
                Double arate = actualAmount / actualSales;
                if (arate.isNaN()) {
                    arate = 0.0;
                }
                arate = arate * NumericConstants.HUNDRED;
                discountDto.addStringProperties(commonColumn + ACTUALSRATE, arate != null && !NULL.equals(String.valueOf(arate)) && !StringUtils.EMPTY.equals(String.valueOf(arate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(arate)))).concat(PERCENTAGE) : HYPHEN);
                Double actualAmt = arate * actualSales;
                actualAmt = actualAmt / NumericConstants.HUNDRED;
                if (actualAmt.isNaN()) {
                    actualAmt = 0.0;
                }
                discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actualAmt != null && !NULL.equals(String.valueOf(actualAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actualAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actualAmt)))) : HYPHEN);
                Double prate = projectedAmount / projectedSales;
                if (prate.isNaN() || prate.isInfinite()) {
                    prate = 0.0;
                }
                prate = prate * NumericConstants.HUNDRED;
                discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, prate != null && !NULL.equals(String.valueOf(prate)) && !StringUtils.EMPTY.equals(String.valueOf(prate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(prate)))).concat(PERCENTAGE) : HYPHEN);
                Double projectedAmtAmt = prate * projectedSales;
                projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                if (projectedAmtAmt.isNaN()) {
                    projectedAmtAmt = 0.0;
                }
                discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, projectedAmtAmt != null && !NULL.equals(String.valueOf(projectedAmtAmt)) && !StringUtils.EMPTY.equals(String.valueOf(projectedAmtAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(projectedAmtAmt)))) : HYPHEN);
                periodList.remove(commonColumn);
                actualSales = 0;
                actualAmount = 0;
                projectedSales = 0;
                projectedAmount = 0;
                commonColumn = StringUtils.EMPTY + obj[0];
                year = (Integer) obj[0];
            }
        }
        discountDto = putHyphenForDTO(periodList, discountDto);
        return discountDto;
    }

    private DiscountProjectionResultsDTO getValueForMonthDTO(ProjectionSelectionDTO projSelDTO, List list, DiscountProjectionResultsDTO discountDto) {

        double actualSales = 0;
        double actualAmount = 0;
        double projectedSales = 0;
        double projectedAmount = 0;
        int year = 0;
        int month = 0;
        List<String> periodList = new ArrayList<>(projSelDTO.getPeriodList());
        String commonColumn = StringUtils.EMPTY;
        for (int i = 0; i < list.size(); i++) {
            final Object[] obj = (Object[]) list.get(i);
            if (i == 0) {
            }
            int selectedYear = 0;
            int selectedMonth = 0;
            if (obj[0] != null) {
                selectedYear = (Integer) obj[0];
            }
            if (obj[NumericConstants.SIX] != null) {
                selectedMonth = (Integer) obj[1];
            }
            if (year == selectedYear && month == selectedMonth) {
                if (obj[NumericConstants.TWO] != null) {
                    Double aSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.TWO]);
                    actualSales = actualSales + aSales;
                }
                if (obj[NumericConstants.THREE] != null) {
                    Double aAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.THREE]);
                    actualAmount = actualAmount + aAmount;
                }
                if (obj[NumericConstants.FOUR] != null) {
                    Double pSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FOUR]);
                    projectedSales = projectedSales + pSales;
                }
                if (obj[NumericConstants.FIVE] != null) {
                    Double pAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FIVE]);
                    projectedAmount = projectedAmount + pAmount;
                }

            } else {
                if (i == 0) {
                    year = selectedYear;
                    month = selectedMonth;
                    String monthName = getMonthForInt(Integer.parseInt(StringUtils.EMPTY + obj[1]) - 1);
                    monthName = monthName.toLowerCase();
                    commonColumn = monthName + obj[0];
                    if (obj[NumericConstants.TWO] != null) {
                        Double aSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.TWO]);
                        actualSales = actualSales + aSales;
                    }
                    if (obj[NumericConstants.THREE] != null) {
                        Double aAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.THREE]);
                        actualAmount = actualAmount + aAmount;
                    }
                    if (obj[NumericConstants.FOUR] != null) {
                        Double pSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FOUR]);
                        projectedSales = projectedSales + pSales;
                    }
                    if (obj[NumericConstants.FIVE] != null) {
                        Double pAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FIVE]);
                        projectedAmount = projectedAmount + pAmount;
                    }

                } else {
                    Double arate = actualAmount / actualSales;
                    if (arate.isNaN()) {
                        arate = 0.0;
                    }
                    arate = arate * NumericConstants.HUNDRED;
                    discountDto.addStringProperties(commonColumn + ACTUALSRATE, arate != null && !NULL.equals(String.valueOf(arate)) && !StringUtils.EMPTY.equals(String.valueOf(arate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(arate)))).concat(PERCENTAGE) : HYPHEN);
                    Double actualAmt = arate * actualSales;
                    actualAmt = actualAmt / NumericConstants.HUNDRED;
                    if (actualAmt.isNaN()) {
                        actualAmt = 0.0;
                    }
                    discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actualAmt != null && !NULL.equals(String.valueOf(actualAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actualAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actualAmt)))) : HYPHEN);
                    Double prate = projectedAmount / projectedSales;
                    if (prate.isNaN() || prate.isInfinite()) {
                        prate = 0.0;
                    }
                    prate = prate * NumericConstants.HUNDRED;
                    discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, prate != null && !NULL.equals(String.valueOf(prate)) && !StringUtils.EMPTY.equals(String.valueOf(prate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(prate)))).concat(PERCENTAGE) : HYPHEN);
                    Double projectedAmtAmt = prate * projectedSales;
                    projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                    if (projectedAmtAmt.isNaN()) {
                        projectedAmtAmt = 0.0;
                    }
                    discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, projectedAmtAmt != null && !NULL.equals(String.valueOf(projectedAmtAmt)) && !StringUtils.EMPTY.equals(String.valueOf(projectedAmtAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(projectedAmtAmt)))) : HYPHEN);
                    periodList.remove(commonColumn);
                    actualSales = 0;
                    actualAmount = 0;
                    projectedSales = 0;
                    projectedAmount = 0;
                    String monthName = getMonthForInt(Integer.parseInt(StringUtils.EMPTY + obj[1]) - 1);
                    monthName = monthName.toLowerCase();
                    commonColumn = monthName + obj[0];
                    year = (Integer) obj[0];
                    month = (Integer) obj[1];
                    if (obj[NumericConstants.TWO] != null) {
                        Double aSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.TWO]);
                        actualSales = actualSales + aSales;
                    }
                    if (obj[NumericConstants.THREE] != null) {
                        Double aAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.THREE]);
                        actualAmount = actualAmount + aAmount;
                    }
                    if (obj[NumericConstants.FOUR] != null) {
                        Double pSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FOUR]);
                        projectedSales = projectedSales + pSales;
                    }
                    if (obj[NumericConstants.FIVE] != null) {
                        Double pAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FIVE]);
                        projectedAmount = projectedAmount + pAmount;
                    }
                }
            }
            if (i == list.size() - 1) {
                Double arate = actualAmount / actualSales;
                if (arate.isNaN()) {
                    arate = 0.0;
                }
                arate = arate * NumericConstants.HUNDRED;
                discountDto.addStringProperties(commonColumn + ACTUALSRATE, arate != null && !NULL.equals(String.valueOf(arate)) && !StringUtils.EMPTY.equals(String.valueOf(arate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(arate)))).concat(PERCENTAGE) : HYPHEN);
                Double actualAmt = arate * actualSales;
                actualAmt = actualAmt / NumericConstants.HUNDRED;
                if (actualAmt.isNaN()) {
                    actualAmt = 0.0;
                }
                discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actualAmt != null && !NULL.equals(String.valueOf(actualAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actualAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actualAmt)))) : HYPHEN);
                Double prate = projectedAmount / projectedSales;
                if (prate.isNaN() || prate.isInfinite()) {
                    prate = 0.0;
                }
                prate = prate * NumericConstants.HUNDRED;
                discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, prate != null && !NULL.equals(String.valueOf(prate)) && !StringUtils.EMPTY.equals(String.valueOf(prate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(prate)))).concat(PERCENTAGE) : HYPHEN);
                Double projectedAmtAmt = prate * projectedSales;
                projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                if (projectedAmtAmt.isNaN()) {
                    projectedAmtAmt = 0.0;
                }
                discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, projectedAmtAmt != null && !NULL.equals(String.valueOf(projectedAmtAmt)) && !StringUtils.EMPTY.equals(String.valueOf(projectedAmtAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(projectedAmtAmt)))) : HYPHEN);
                periodList.remove(commonColumn);
                actualSales = 0;
                actualAmount = 0;
                projectedSales = 0;
                projectedAmount = 0;
                String monthName = getMonthForInt(Integer.parseInt(StringUtils.EMPTY + obj[1]) - 1);
                monthName = monthName.toLowerCase();
                commonColumn = monthName + obj[0];
                year = (Integer) obj[0];
                month = (Integer) obj[1];
            }
        }
        discountDto = putHyphenForDTO(periodList, discountDto);
        return discountDto;
    }

    private List<DiscountProjectionResultsDTO> getDiscountListDto(ProjectionSelectionDTO projSelDTO, List list, List<DiscountProjectionResultsDTO> discountProjList, String freq, List<String> discountList) {
        int year = 0;
        int quarter = 0;
        String discountName = StringUtils.EMPTY;
        double actualSales = 0;
        double actualAmount = 0;
        double projectedSales = 0;
        double projectedAmount = 0;
        DiscountProjectionResultsDTO discountDto = new DiscountProjectionResultsDTO();
        List<String> periodList = new ArrayList<>(projSelDTO.getPeriodList());
        String commonColumn = StringUtils.EMPTY;
        for (int i = 0; i < list.size(); i++) {
            Object[] obj = (Object[]) list.get(i);
            String currentDiscount = String.valueOf(obj[NumericConstants.EIGHT]);
            int selectedYear = (Integer) obj[0];
            int selectedQuarter = (Integer) obj[NumericConstants.SIX];
            if (currentDiscount.equalsIgnoreCase(discountName)) {
                if (year == selectedYear && quarter == selectedQuarter) {
                    if (obj[NumericConstants.TWO] != null) {
                        Double aSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.TWO]);
                        actualSales = actualSales + aSales;
                    }
                    if (obj[NumericConstants.THREE] != null) {
                        Double aAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.THREE]);
                        actualAmount = actualAmount + aAmount;
                    }
                    if (obj[NumericConstants.FOUR] != null) {
                        Double pSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FOUR]);
                        projectedSales = projectedSales + pSales;
                    }
                    if (obj[NumericConstants.FIVE] != null) {
                        Double pAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FIVE]);
                        projectedAmount = projectedAmount + pAmount;
                    }
                } else {
                    Double arate = actualAmount / actualSales;
                    if (arate.isNaN()) {
                        arate = 0.0;
                    }
                    arate = arate * NumericConstants.HUNDRED;
                    String actRate = String.valueOf(arate);
                    discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                    Double actualAmt = arate * actualSales;
                    actualAmt = actualAmt / NumericConstants.HUNDRED;
                    if (actualAmt.isNaN()) {
                        actualAmt = 0.0;
                    }
                    String actAmt = String.valueOf(actualAmt);
                    discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                    Double prate = projectedAmount / projectedSales;
                    if (prate.isNaN() || prate.isInfinite()) {
                        prate = 0.0;
                    }
                    prate = prate * NumericConstants.HUNDRED;
                    String proRate = String.valueOf(prate);
                    discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                    Double projectedAmtAmt = prate * projectedSales;
                    projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                    if (projectedAmtAmt.isNaN()) {
                        projectedAmtAmt = 0.0;
                    }
                    String proAmount = String.valueOf(projectedAmtAmt);
                    discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                    periodList.remove(commonColumn);
                    actualSales = 0;
                    actualAmount = 0;
                    projectedSales = 0;
                    projectedAmount = 0;
                    commonColumn = freq + obj[NumericConstants.SIX] + obj[0];
                    if (obj[NumericConstants.TWO] != null) {
                        Double aSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.TWO]);
                        actualSales = actualSales + aSales;
                    }
                    if (obj[NumericConstants.THREE] != null) {
                        Double aAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.THREE]);
                        actualAmount = actualAmount + aAmount;
                    }
                    if (obj[NumericConstants.FOUR] != null) {
                        Double pSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FOUR]);
                        projectedSales = projectedSales + pSales;
                    }
                    if (obj[NumericConstants.FIVE] != null) {
                        Double pAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FIVE]);
                        projectedAmount = projectedAmount + pAmount;
                    }
                    year = selectedYear;
                    quarter = selectedQuarter;
                }
            } else {
                if (i == 0) {
                    discountDto = new DiscountProjectionResultsDTO();
                    discountDto.setGroup(String.valueOf(obj[NumericConstants.EIGHT]));
                    discountList.remove(String.valueOf(obj[NumericConstants.EIGHT]));
                    discountDto.setIsParent(ZERO_SYMBOL);
                    year = selectedYear;
                    quarter = selectedQuarter;
                    discountName = currentDiscount;
                    commonColumn = freq + obj[NumericConstants.SIX] + obj[0];
                    if (obj[NumericConstants.TWO] != null) {
                        Double aSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.TWO]);
                        actualSales = actualSales + aSales;
                    }
                    if (obj[NumericConstants.THREE] != null) {
                        Double aAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.THREE]);
                        actualAmount = actualAmount + aAmount;
                    }
                    if (obj[NumericConstants.FOUR] != null) {
                        Double pSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FOUR]);
                        projectedSales = projectedSales + pSales;
                    }
                    if (obj[NumericConstants.FIVE] != null) {
                        Double pAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FIVE]);
                        projectedAmount = projectedAmount + pAmount;
                    }
                } else {
                    Double arate = actualAmount / actualSales;
                    if (arate.isNaN()) {
                        arate = 0.0;
                    }
                    arate = arate * NumericConstants.HUNDRED;
                    String actRate = String.valueOf(arate);
                    discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                    Double actualAmt = arate * actualSales;
                    actualAmt = actualAmt / NumericConstants.HUNDRED;
                    if (actualAmt.isNaN()) {
                        actualAmt = 0.0;
                    }
                    String actAmt = String.valueOf(actualAmt);
                    discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                    Double prate = projectedAmount / projectedSales;
                    if (prate.isNaN() || prate.isInfinite()) {
                        prate = 0.0;
                    }
                    prate = prate * NumericConstants.HUNDRED;
                    String proRate = String.valueOf(prate);
                    discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                    Double projectedAmtAmt = prate * projectedSales;
                    projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                    if (projectedAmtAmt.isNaN()) {
                        projectedAmtAmt = 0.0;
                    }
                    String proAmount = String.valueOf(projectedAmtAmt);
                    discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                    periodList.remove(commonColumn);
                    discountDto = putHyphenForDTO(periodList, discountDto);
                    discountProjList.add(discountDto);
                    actualSales = 0;
                    actualAmount = 0;
                    projectedSales = 0;
                    projectedAmount = 0;
                    discountDto = new DiscountProjectionResultsDTO();
                    discountName = currentDiscount;
                    discountDto.setGroup(discountName);
                    discountList.remove(discountName);
                    year = selectedYear;
                    quarter = selectedQuarter;
                    commonColumn = freq + obj[NumericConstants.SIX] + obj[0];
                    discountDto.setIsParent(ZERO_SYMBOL);
                    if (obj[NumericConstants.TWO] != null) {
                        Double aSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.TWO]);
                        actualSales = actualSales + aSales;
                    }
                    if (obj[NumericConstants.THREE] != null) {
                        Double aAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.THREE]);
                        actualAmount = actualAmount + aAmount;
                    }
                    if (obj[NumericConstants.FOUR] != null) {
                        Double pSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FOUR]);
                        projectedSales = projectedSales + pSales;
                    }
                    if (obj[NumericConstants.FIVE] != null) {
                        Double pAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FIVE]);
                        projectedAmount = projectedAmount + pAmount;
                    }
                }
            }
            if (i == list.size() - 1) {
                Double arate = actualAmount / actualSales;
                if (arate.isNaN()) {
                    arate = 0.0;
                }
                arate = arate * NumericConstants.HUNDRED;
                String actRate = String.valueOf(arate);
                discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                Double actualAmt = arate * actualSales;
                actualAmt = actualAmt / NumericConstants.HUNDRED;
                if (actualAmt.isNaN()) {
                    actualAmt = 0.0;
                }
                String actAmt = String.valueOf(actualAmt);
                discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                Double prate = projectedAmount / projectedSales;
                if (prate.isNaN() || prate.isInfinite()) {
                    prate = 0.0;
                }
                prate = prate * NumericConstants.HUNDRED;
                String proRate = String.valueOf(prate);
                discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                Double projectedAmtAmt = prate * projectedSales;
                projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                if (projectedAmtAmt.isNaN()) {
                    projectedAmtAmt = 0.0;
                }
                String proAmount = String.valueOf(projectedAmtAmt);
                discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                periodList.remove(commonColumn);
                discountDto = putHyphenForDTO(periodList, discountDto);
                discountProjList.add(discountDto);

                if (!discountList.isEmpty()) {
                    for (int k = 0; k < discountList.size(); k++) {
                        String group = discountList.get(k);
                        discountDto = new DiscountProjectionResultsDTO();
                        periodList = new ArrayList<>(projSelDTO.getPeriodList());
                        discountDto.setGroup(group);
                        discountDto = putHyphenForDTO(periodList, discountDto);
                        discountProjList.add(discountDto);
                    }

                }
            }
        }
        return discountProjList;
    }

    private List<DiscountProjectionResultsDTO> getDiscountListYearDto(ProjectionSelectionDTO projSelDTO, List list, List<DiscountProjectionResultsDTO> discountProjList, List<String> discountList) {
        double actualSales = 0;
        double actualAmount = 0;
        double projectedSales = 0;
        double projectedAmount = 0;
        String commonColumn;
        List<String> periodList = new ArrayList<>(projSelDTO.getPeriodList());
        DiscountProjectionResultsDTO dto = new DiscountProjectionResultsDTO();
        Object[] object = (Object[]) list.get(0);
        String currentDiscount = String.valueOf(object[NumericConstants.EIGHT]);
        int currentYear = (Integer) object[0];
        dto.setIsParent(ZERO_SYMBOL);
        dto.setGroup(currentDiscount);
        discountList.remove(currentDiscount);
        commonColumn = StringUtils.EMPTY + object[0];
        if (object[NumericConstants.TWO] != null) {
            Double aSales = DataTypeConverter.convertObjectToDouble(object[NumericConstants.TWO]);
            actualSales = actualSales + aSales;
        }
        if (object[NumericConstants.THREE] != null) {
            Double aAmount = DataTypeConverter.convertObjectToDouble(object[NumericConstants.THREE]);
            actualAmount = actualAmount + aAmount;
        }
        if (object[NumericConstants.FOUR] != null) {
            Double pSales = DataTypeConverter.convertObjectToDouble(object[NumericConstants.FOUR]);
            projectedSales = projectedSales + pSales;
        }
        if (object[NumericConstants.FIVE] != null) {
            Double pAmount = DataTypeConverter.convertObjectToDouble(object[NumericConstants.FIVE]);
            projectedAmount = projectedAmount + pAmount;
        }
        if (list.size() == 1) {
            Double arate = actualAmount / actualSales;
            if (arate.isNaN()) {
                arate = 0.0;
            }
            arate = arate * NumericConstants.HUNDRED;
            String actRate = String.valueOf(arate);
            dto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
            Double actualAmt = arate * actualSales;
            actualAmt = actualAmt / NumericConstants.HUNDRED;
            if (actualAmt.isNaN()) {
                actualAmt = 0.0;
            }
            String actAmt = String.valueOf(actualAmt);
            dto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
            Double prate = projectedAmount / projectedSales;
            if (prate.isNaN() || prate.isInfinite()) {
                prate = 0.0;
            }
            prate = prate * NumericConstants.HUNDRED;
            String proRate = String.valueOf(prate);
            dto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
            Double projectedAmtAmt = prate * projectedSales;
            projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
            if (projectedAmtAmt.isNaN()) {
                projectedAmtAmt = 0.0;
            }
            String proAmount = String.valueOf(projectedAmtAmt);
            dto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
            periodList.remove(commonColumn);
        } else {
            for (int i = 1; i < list.size(); i++) {
                Object[] obj = (Object[]) list.get(i);
                String selectedDiscount = String.valueOf(obj[NumericConstants.EIGHT]);
                int selectedYear = (Integer) obj[0];
                if (currentDiscount.equalsIgnoreCase(selectedDiscount)) {
                    if (currentYear == selectedYear) {
                        if (obj[NumericConstants.TWO] != null) {
                            Double aSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.TWO]);
                            actualSales = actualSales + aSales;
                        }
                        if (obj[NumericConstants.THREE] != null) {
                            Double aAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.THREE]);
                            actualAmount = actualAmount + aAmount;
                        }
                        if (obj[NumericConstants.FOUR] != null) {
                            Double pSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FOUR]);
                            projectedSales = projectedSales + pSales;
                        }
                        if (obj[NumericConstants.FIVE] != null) {
                            Double pAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FIVE]);
                            projectedAmount = projectedAmount + pAmount;
                        }
                    } else {
                        Double arate = actualAmount / actualSales;
                        if (arate.isNaN() || arate.isInfinite()) {
                            arate = 0.0;
                        }
                        arate = arate * NumericConstants.HUNDRED;
                        String actRate = String.valueOf(arate);
                        dto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                        Double actualAmt = arate * actualSales;
                        actualAmt = actualAmt / NumericConstants.HUNDRED;
                        if (actualAmt.isNaN()) {
                            actualAmt = 0.0;
                        }

                        String actAmt = String.valueOf(actualAmt);
                        dto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                        Double prate = projectedAmount / projectedSales;
                        if (prate.isNaN() || prate.isInfinite()) {
                            prate = 0.0;
                        }
                        prate = prate * NumericConstants.HUNDRED;
                        if (prate.isInfinite()) {
                            prate = 0.0;
                        }
                        String proRate = String.valueOf(prate);
                        dto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                        Double projectedAmtAmt = prate * projectedSales;
                        projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                        if (projectedAmtAmt.isNaN()) {
                            projectedAmtAmt = 0.0;
                        }
                        String proAmount = String.valueOf(projectedAmtAmt);
                        dto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                        periodList.remove(commonColumn);
                        actualSales = 0;
                        actualAmount = 0;
                        projectedSales = 0;
                        projectedAmount = 0;
                        commonColumn = StringUtils.EMPTY + obj[0];
                        currentYear = selectedYear;
                        dto.setIsParent(ZERO_SYMBOL);
                        if (obj[NumericConstants.TWO] != null) {
                            Double aSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.TWO]);
                            actualSales = actualSales + aSales;
                        }
                        if (obj[NumericConstants.THREE] != null) {
                            Double aAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.THREE]);
                            actualAmount = actualAmount + aAmount;
                        }
                        if (obj[NumericConstants.FOUR] != null) {
                            Double pSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FOUR]);
                            projectedSales = projectedSales + pSales;
                        }
                        if (obj[NumericConstants.FIVE] != null) {
                            Double pAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FIVE]);
                            projectedAmount = projectedAmount + pAmount;
                        }
                    }
                } else {
                    Double arate = actualAmount / actualSales;
                    if (arate.isNaN()) {
                        arate = 0.0;
                    }
                    arate = arate * NumericConstants.HUNDRED;
                    String actRate = String.valueOf(arate);
                    dto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                    Double actualAmt = arate * actualSales;
                    actualAmt = actualAmt / NumericConstants.HUNDRED;
                    if (actualAmt.isNaN()) {
                        actualAmt = 0.0;
                    }
                    String actAmt = String.valueOf(actualAmt);
                    dto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                    Double prate = projectedAmount / projectedSales;
                    if (prate.isNaN() || prate.isInfinite()) {
                        prate = 0.0;
                    }
                    prate = prate * NumericConstants.HUNDRED;
                    String proRate = String.valueOf(prate);
                    dto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                    Double projectedAmtAmt = prate * projectedSales;
                    projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                    if (projectedAmtAmt.isNaN()) {
                        projectedAmtAmt = 0.0;
                    }
                    String proAmount = String.valueOf(projectedAmtAmt);
                    dto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                    periodList.remove(commonColumn);
                    dto = putHyphenForDTO(periodList, dto);
                    discountProjList.add(dto);
                    actualSales = 0;
                    actualAmount = 0;
                    projectedSales = 0;
                    projectedAmount = 0;
                    dto = new DiscountProjectionResultsDTO();
                    periodList = new ArrayList<>(projSelDTO.getPeriodList());
                    currentDiscount = selectedDiscount;
                    dto.setGroup(currentDiscount);
                    discountList.remove(currentDiscount);
                    commonColumn = StringUtils.EMPTY + obj[0];
                    currentYear = selectedYear;
                    if (obj[NumericConstants.TWO] != null) {
                        Double aSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.TWO]);
                        actualSales = actualSales + aSales;
                    }
                    if (obj[NumericConstants.THREE] != null) {
                        Double aAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.THREE]);
                        actualAmount = actualAmount + aAmount;
                    }
                    if (obj[NumericConstants.FOUR] != null) {
                        Double pSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FOUR]);
                        projectedSales = projectedSales + pSales;
                    }
                    if (obj[NumericConstants.FIVE] != null) {
                        Double pAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FIVE]);
                        projectedAmount = projectedAmount + pAmount;
                    }
                }
                if (i == list.size() - 1) {
                    Double arate = actualAmount / actualSales;
                    if (arate.isNaN()) {
                        arate = 0.0;
                    }
                    arate = arate * NumericConstants.HUNDRED;
                    String actRate = String.valueOf(arate);
                    dto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                    Double actualAmt = arate * actualSales;
                    actualAmt = actualAmt / NumericConstants.HUNDRED;
                    if (actualAmt.isNaN()) {
                        actualAmt = 0.0;
                    }
                    String actAmt = String.valueOf(actualAmt);
                    dto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                    Double prate = projectedAmount / projectedSales;
                    if (prate.isNaN() || prate.isInfinite()) {
                        prate = 0.0;
                    }
                    prate = prate * NumericConstants.HUNDRED;
                    String proRate = String.valueOf(prate);
                    dto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                    Double projectedAmtAmt = prate * projectedSales;
                    projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                    if (projectedAmtAmt.isNaN()) {
                        projectedAmtAmt = 0.0;
                    }
                    String proAmount = String.valueOf(projectedAmtAmt);
                    dto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);

                    periodList.remove(commonColumn);
                    dto = putHyphenForDTO(periodList, dto);
                    discountProjList.add(dto);
                }
            }
            if (!discountList.isEmpty()) {
                for (int k = 0; k < discountList.size(); k++) {
                    String group = discountList.get(k);
                    dto = new DiscountProjectionResultsDTO();
                    periodList = new ArrayList<>(projSelDTO.getPeriodList());
                    dto.setGroup(group);
                    dto = putHyphenForDTO(periodList, dto);
                    discountProjList.add(dto);
                }

            }

        }
        return discountProjList;
    }

    private List<DiscountProjectionResultsDTO> getDiscountListMonthDto(ProjectionSelectionDTO projSelDTO, List list, List<DiscountProjectionResultsDTO> discountProjList, List<String> discountList) {
        double actualSales = 0;
        double actualAmount = 0;
        double projectedSales = 0;
        double projectedAmount = 0;
        String commonColumn;
        List<String> periodList = new ArrayList<>(projSelDTO.getPeriodList());
        DiscountProjectionResultsDTO dto = new DiscountProjectionResultsDTO();
        Object[] object = (Object[]) list.get(0);
        String currentDiscount = String.valueOf(object[NumericConstants.EIGHT]);
        int currentYear = (Integer) object[0];
        int currentMonth = (Integer) object[NumericConstants.SEVEN];
        String monthName = getMonthForInt(Integer.parseInt(StringUtils.EMPTY + object[NumericConstants.SEVEN]) - 1);
        monthName = monthName.toLowerCase();
        commonColumn = monthName + object[0];
        dto.setIsParent(ZERO_SYMBOL);
        dto.setGroup(currentDiscount);
        discountList.remove(currentDiscount);
        if (object[NumericConstants.TWO] != null) {
            Double aSales = DataTypeConverter.convertObjectToDouble(object[NumericConstants.TWO]);
            actualSales = actualSales + aSales;
        }
        if (object[NumericConstants.THREE] != null) {
            Double aAmount = DataTypeConverter.convertObjectToDouble(object[NumericConstants.THREE]);
            actualAmount = actualAmount + aAmount;
        }
        if (object[NumericConstants.FOUR] != null) {
            Double pSales = DataTypeConverter.convertObjectToDouble(object[NumericConstants.FOUR]);
            projectedSales = projectedSales + pSales;
        }
        if (object[NumericConstants.FIVE] != null) {
            Double pAmount = DataTypeConverter.convertObjectToDouble(object[NumericConstants.FIVE]);
            projectedAmount = projectedAmount + pAmount;
        }
        if (list.size() == 1) {
            Double arate = actualAmount / actualSales;
            if (arate.isNaN()) {
                arate = 0.0;
            }
            arate = arate * NumericConstants.HUNDRED;
            String actRate = String.valueOf(arate);
            dto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
            Double actualAmt = arate * actualSales;
            actualAmt = actualAmt / NumericConstants.HUNDRED;
            if (actualAmt.isNaN()) {
                actualAmt = 0.0;
            }
            String actAmt = String.valueOf(actualAmt);
            dto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
            Double prate = projectedAmount / projectedSales;
            if (prate.isNaN() || prate.isInfinite()) {
                prate = 0.0;
            }
            prate = prate * NumericConstants.HUNDRED;
            String proRate = String.valueOf(prate);
            dto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
            Double projectedAmtAmt = prate * projectedSales;
            projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
            if (projectedAmtAmt.isNaN()) {
                projectedAmtAmt = 0.0;
            }
            String proAmount = String.valueOf(projectedAmtAmt);
            dto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
            periodList.remove(commonColumn);
        } else {
            for (int i = 1; i < list.size(); i++) {
                Object[] obj = (Object[]) list.get(i);
                String selectedDiscount = String.valueOf(obj[NumericConstants.EIGHT]);
                int selectedYear = (Integer) obj[0];
                int selectedMonth = (Integer) obj[NumericConstants.SEVEN];
                if (currentDiscount.equalsIgnoreCase(selectedDiscount)) {
                    if (currentYear == selectedYear && currentMonth == selectedMonth) {
                        if (obj[NumericConstants.TWO] != null) {
                            Double aSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.TWO]);
                            actualSales = actualSales + aSales;
                        }
                        if (obj[NumericConstants.THREE] != null) {
                            Double aAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.THREE]);
                            actualAmount = actualAmount + aAmount;
                        }
                        if (obj[NumericConstants.FOUR] != null) {
                            Double pSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FOUR]);
                            projectedSales = projectedSales + pSales;
                        }
                        if (obj[NumericConstants.FIVE] != null) {
                            Double pAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FIVE]);
                            projectedAmount = projectedAmount + pAmount;
                        }
                    } else {
                        Double arate = actualAmount / actualSales;
                        if (arate.isNaN()) {
                            arate = 0.0;
                        }
                        arate = arate * NumericConstants.HUNDRED;
                        String actRate = String.valueOf(arate);
                        dto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                        Double actualAmt = arate * actualSales;
                        actualAmt = actualAmt / NumericConstants.HUNDRED;
                        if (actualAmt.isNaN()) {
                            actualAmt = 0.0;
                        }
                        String actAmt = String.valueOf(actualAmt);
                        dto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                        Double prate = projectedAmount / projectedSales;
                        if (prate.isNaN() || prate.isInfinite()) {
                            prate = 0.0;
                        }
                        prate = prate * NumericConstants.HUNDRED;
                        String proRate = String.valueOf(prate);
                        dto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                        Double projectedAmtAmt = prate * projectedSales;
                        projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                        if (projectedAmtAmt.isNaN()) {
                            projectedAmtAmt = 0.0;
                        }
                        String proAmount = String.valueOf(projectedAmtAmt);
                        dto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                        periodList.remove(commonColumn);
                        actualSales = 0;
                        actualAmount = 0;
                        projectedSales = 0;
                        projectedAmount = 0;
                        monthName = getMonthForInt(Integer.parseInt(StringUtils.EMPTY + obj[NumericConstants.SEVEN]) - 1);
                        monthName = monthName.toLowerCase();
                        commonColumn = monthName + obj[0];
                        currentYear = selectedYear;
                        currentMonth = selectedMonth;
                        dto.setIsParent(ZERO_SYMBOL);
                        if (obj[NumericConstants.TWO] != null) {
                            Double aSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.TWO]);
                            actualSales = actualSales + aSales;
                        }
                        if (obj[NumericConstants.THREE] != null) {
                            Double aAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.THREE]);
                            actualAmount = actualAmount + aAmount;
                        }

                        if (obj[NumericConstants.FOUR] != null) {
                            Double pSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FOUR]);
                            projectedSales = projectedSales + pSales;
                        }
                        if (obj[NumericConstants.FIVE] != null) {
                            Double pAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FIVE]);
                            projectedAmount = projectedAmount + pAmount;
                        }
                    }
                } else {
                    Double arate = actualAmount / actualSales;
                    if (arate.isNaN()) {
                        arate = 0.0;
                    }
                    arate = arate * NumericConstants.HUNDRED;
                    String actRate = String.valueOf(arate);
                    dto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                    Double actualAmt = arate * actualSales;
                    actualAmt = actualAmt / NumericConstants.HUNDRED;
                    if (actualAmt.isNaN()) {
                        actualAmt = 0.0;
                    }
                    String actAmt = String.valueOf(actualAmt);
                    dto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                    Double prate = projectedAmount / projectedSales;
                    if (prate.isNaN() || prate.isInfinite()) {
                        prate = 0.0;
                    }
                    prate = prate * NumericConstants.HUNDRED;
                    String proRate = String.valueOf(prate);
                    dto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                    Double projectedAmtAmt = prate * projectedSales;
                    projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                    if (projectedAmtAmt.isNaN()) {
                        projectedAmtAmt = 0.0;
                    }
                    String proAmount = String.valueOf(projectedAmtAmt);
                    dto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                    periodList.remove(commonColumn);
                    dto = putHyphenForDTO(periodList, dto);
                    discountProjList.add(dto);
                    actualSales = 0;
                    actualAmount = 0;
                    projectedSales = 0;
                    projectedAmount = 0;
                    dto = new DiscountProjectionResultsDTO();
                    periodList = new ArrayList<>(projSelDTO.getPeriodList());
                    currentDiscount = selectedDiscount;
                    dto.setGroup(currentDiscount);
                    discountList.remove(currentDiscount);
                    monthName = getMonthForInt(Integer.parseInt(StringUtils.EMPTY + obj[NumericConstants.SEVEN]) - 1);
                    monthName = monthName.toLowerCase();
                    commonColumn = monthName + obj[0];
                    currentYear = selectedYear;
                    currentMonth = selectedMonth;
                    if (obj[NumericConstants.TWO] != null) {
                        Double aSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.TWO]);
                        actualSales = actualSales + aSales;
                    }
                    if (obj[NumericConstants.THREE] != null) {
                        Double aAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.THREE]);
                        actualAmount = actualAmount + aAmount;
                    }
                    if (obj[NumericConstants.FOUR] != null) {
                        Double pSales = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FOUR]);
                        projectedSales = projectedSales + pSales;
                    }
                    if (obj[NumericConstants.FIVE] != null) {
                        Double pAmount = DataTypeConverter.convertObjectToDouble(obj[NumericConstants.FIVE]);
                        projectedAmount = projectedAmount + pAmount;
                    }
                }
                if (i == list.size() - 1) {
                    Double arate = actualAmount / actualSales;
                    if (arate.isNaN()) {
                        arate = 0.0;
                    }
                    arate = arate * NumericConstants.HUNDRED;
                    String actRate = String.valueOf(arate);
                    dto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                    Double actualAmt = arate * actualSales;
                    actualAmt = actualAmt / NumericConstants.HUNDRED;
                    if (actualAmt.isNaN()) {
                        actualAmt = 0.0;
                    }
                    String actAmt = String.valueOf(actualAmt);
                    dto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                    Double prate = projectedAmount / projectedSales;
                    if (prate.isNaN() || prate.isInfinite()) {
                        prate = 0.0;
                    }
                    prate = prate * NumericConstants.HUNDRED;
                    String proRate = String.valueOf(prate);
                    dto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                    Double projectedAmtAmt = prate * projectedSales;
                    projectedAmtAmt = projectedAmtAmt / NumericConstants.HUNDRED;
                    if (projectedAmtAmt.isNaN()) {
                        projectedAmtAmt = 0.0;
                    }
                    String proAmount = String.valueOf(projectedAmtAmt);
                    dto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                    periodList.remove(commonColumn);
                    dto = putHyphenForDTO(periodList, dto);
                    commonColumn = StringUtils.EMPTY;
                    discountProjList.add(dto);
                }
            }
            if (!discountList.isEmpty()) {
                for (int k = 0; k < discountList.size(); k++) {
                    String group = discountList.get(k);
                    dto = new DiscountProjectionResultsDTO();
                    periodList = new ArrayList<>(projSelDTO.getPeriodList());
                    dto.setGroup(group);
                    dto = putHyphenForDTO(periodList, dto);
                    discountProjList.add(dto);
                }

            }

        }
        return discountProjList;
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
    public List<DiscountProjectionResultsDTO> getConfiguredProjectionResultsTotal(int start, int offset, ProjectionSelectionDTO projSelDTO) {
        List<DiscountProjectionResultsDTO> resultList = new ArrayList<>();
        projSelDTO.setIsProjectionTotal(true);
        if (!projSelDTO.isIsFilter()) {

            projSelDTO.setYear(Constant.ALL);
            if (projSelDTO.getActualsOrProjections().equals(BOTH.getConstant())) {
                projSelDTO.setActualsOrProjections(ACTUALS.getConstant() + AND_SMALL + PROJECTIONS.getConstant());
            }
            projSelDTO.setIsTotal(false);
            if (projSelDTO.isIsCustomHierarchy()) {
                projSelDTO.setLevelNo(0);
                projSelDTO.setTreeLevelNo(0);
            } else if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                projSelDTO.setLevelNo(projSelDTO.getCustomerLevelNo() - 1);
                projSelDTO.setTreeLevelNo(projSelDTO.getCustomerLevelNo() - 1);
            } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                projSelDTO.setLevelNo(projSelDTO.getProductLevelNo() - 1);
                projSelDTO.setTreeLevelNo(projSelDTO.getProductLevelNo() - 1);
            }

            projSelDTO.setGroup(StringUtils.EMPTY);
            projSelDTO.setHierarchyNo(StringUtils.EMPTY);
            projSelDTO.setProductHierarchyNo(StringUtils.EMPTY);
            projSelDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
            resultList = getProjectionResultsTotal(start, offset, projSelDTO);
        }
        return resultList;
    }

    /**
     *
     * @param start
     * @param offset
     * @param projSelDTO
     * @return
     *
     */
    public List<DiscountProjectionResultsDTO> getProjectionResultsTotal(int start, int offset, ProjectionSelectionDTO projSelDTO) {
        int neededRecord = offset;
        int mayBeAdded = 0;
        List<DiscountProjectionResultsDTO> projDTOList = new ArrayList<>();
        List<Integer> yearList = new ArrayList<>();
        yearList.add(projSelDTO.getForecastDTO().getHistoryStartYear());
        yearList.add(projSelDTO.getForecastDTO().getHistoryStartMonth());
        yearList.add(projSelDTO.getForecastDTO().getHistoryEndYear());
        yearList.add(projSelDTO.getForecastDTO().getHistoryEndMonth());
        yearList.add(projSelDTO.getForecastDTO().getForecastStartYear());
        String freq = String.valueOf(projSelDTO.getFrequency());
        if (freq.equals(Constant.QUARTERLY)) {
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
        } else if (freq.equals(Constant.SEMI_ANNUALLY)) {
            int month = projSelDTO.getForecastDTO().getForecastStartMonth();
            if (month <= NumericConstants.SIX) {
                yearList.add(1);
            } else if (month >= NumericConstants.SEVEN && month < NumericConstants.THIRTEEN) {
                yearList.add(NumericConstants.SEVEN);
            }
        } else if (freq.equals(Constant.ANNUALLY)) {
            yearList.add(1);
        } else if (freq.equals(Constant.MONTHLY)) {
            int month = projSelDTO.getForecastDTO().getForecastStartMonth();
            yearList.add(month);
        }
        yearList.add(projSelDTO.getForecastDTO().getForecastEndYear());
        yearList.add(projSelDTO.getForecastDTO().getForecastEndMonth());

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
            }
        } else {
            List<Integer> pivotYearList = new ArrayList<>();
            String frequency = String.valueOf(projSelDTO.getFrequency());
            if (frequency.equals(Constant.ANNUALLY)) {
                pivotYearList.add(Integer.valueOf(projSelDTO.getPeriodList().get(0)));
                pivotYearList.add(1);
                pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndYear());
                pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndMonth());
            } else if (frequency.equals(Constant.QUARTERLY)) {
                String column = String.valueOf(projSelDTO.getPeriodList().get(0));
                column = column.replace(Constant.Q_SMALL, StringUtils.EMPTY);
                String year = column.substring(column.length() - NumericConstants.FOUR, column.length());
                String fre = column.replace(year, StringUtils.EMPTY);
                pivotYearList.add(Integer.valueOf(year));
                if (fre.equals(Constant.STRING_ONE)) {
                    pivotYearList.add(1);
                } else if (fre.equals("2")) {
                    pivotYearList.add(NumericConstants.FOUR);
                } else if (fre.equals("3")) {
                    pivotYearList.add(NumericConstants.SEVEN);
                } else if (fre.equals("4")) {
                    pivotYearList.add(NumericConstants.TEN);
                }
                pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndYear());
                pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndMonth());
            } else if (frequency.equals(Constant.MONTHLY)) {
                loadKeyMap();
                String column = String.valueOf(projSelDTO.getPeriodList().get(0));
                String fre = column.substring(0, NumericConstants.THREE);
                String year = column.replace(fre, StringUtils.EMPTY);
                String month = valueMap.get(fre);
                pivotYearList.add(Integer.valueOf(year));
                pivotYearList.add(Integer.valueOf(month));

            } else if (frequency.equals(Constant.SEMI_ANNUALLY)) {
                String column = String.valueOf(projSelDTO.getPeriodList().get(0));
                column = column.replace(Constant.S_SMALL, StringUtils.EMPTY);
                String year = column.substring(column.length() - NumericConstants.FOUR, column.length());
                String fre = column.replace(year, StringUtils.EMPTY);
                pivotYearList.add(Integer.valueOf(year));
                if (fre.equals(Constant.STRING_ONE)) {
                    pivotYearList.add(1);
                } else if (fre.equals("2")) {
                    pivotYearList.add(NumericConstants.SEVEN);
                }
                pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndYear());
                pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndMonth());
            }
            pivotYearList.add(projSelDTO.getForecastDTO().getForecastStartYear());
            String frequ = String.valueOf(projSelDTO.getFrequency());
            if (frequ.equals(Constant.QUARTERLY)) {
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
            } else if (frequ.equals(Constant.SEMI_ANNUALLY)) {
                int month = projSelDTO.getForecastDTO().getForecastStartMonth();
                if (month <= NumericConstants.SIX) {
                    pivotYearList.add(1);
                } else if (month >= NumericConstants.SEVEN && month < NumericConstants.THIRTEEN) {
                    pivotYearList.add(NumericConstants.SEVEN);
                }
            } else if (frequ.equals(Constant.ANNUALLY)) {
                pivotYearList.add(1);
            } else if (frequ.equals(Constant.MONTHLY)) {
                int month = projSelDTO.getForecastDTO().getForecastStartMonth();
                pivotYearList.add(month);
            }
            pivotYearList.add(projSelDTO.getForecastDTO().getForecastEndYear());
            pivotYearList.add(projSelDTO.getForecastDTO().getForecastEndMonth());
            if (start < 1) {
                List<DiscountProjectionResultsDTO> discountDtoList;
                discountDtoList = getPivotProjectionTotal(projSelDTO.getProjectionId(), projSelDTO, pivotYearList);
                if (discountDtoList != null && !discountDtoList.isEmpty()) {
                    projDTOList.add(discountDtoList.get(0));
                    neededRecord--;
                }
            }
            mayBeAdded++;
            if (neededRecord > 0) {
                List<DiscountProjectionResultsDTO> periodList = new ArrayList<>();
                try {
                    periodList = getPivotProjectionTotalDiscount(projSelDTO, yearList);
                } catch (SystemException ex) {
                    LoggerFactory.getLogger(DiscountProjectionResultsLogic.class.getName()).error( StringUtils.EMPTY, ex);
                }
                int mayBeAddedRecord = start - mayBeAdded;
                if (mayBeAddedRecord < 0) {
                    mayBeAddedRecord = 0;
                }
                for (int i = mayBeAddedRecord; i < periodList.size(); i++) {
                    projDTOList.add(periodList.get(i));
                    neededRecord--;
                }
            }
        }
        return projDTOList;
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
    public int getConfiguredProjectionResultsTotalCount(ProjectionSelectionDTO projSelDTO) {

        int count = 0;
        projSelDTO.setIsProjectionTotal(true);
        if (!projSelDTO.isIsFilter()) {
            projSelDTO.setYear(Constant.ALL);
            if (projSelDTO.getActualsOrProjections().equals(Constant.BOTH)) {
                projSelDTO.setActualsOrProjections(ACTUALS.getConstant() + AND_SMALL + PROJECTIONS.getConstant());
            }
            projSelDTO.setIsTotal(false);
            if (projSelDTO.isIsCustomHierarchy()) {
                projSelDTO.setLevelNo(0);
                projSelDTO.setTreeLevelNo(0);
            } else if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                projSelDTO.setLevelNo(projSelDTO.getCustomerLevelNo() - 1);
                projSelDTO.setTreeLevelNo(projSelDTO.getCustomerLevelNo() - 1);
            } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                projSelDTO.setLevelNo(projSelDTO.getProductLevelNo() - 1);
                projSelDTO.setTreeLevelNo(projSelDTO.getProductLevelNo() - 1);
            }
            projSelDTO.setGroup(StringUtils.EMPTY);
            projSelDTO.setHierarchyNo(StringUtils.EMPTY);
            projSelDTO.setProductHierarchyNo(StringUtils.EMPTY);
            projSelDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
            count += getProjectionResultsTotalCount(projSelDTO);
        }
        return count;
    }

    /**
     *
     * @param projSelDTO
     * @param isLevelsCount
     * @return
     *
     */
    public int getProjectionResultsTotalCount(ProjectionSelectionDTO projSelDTO) {
        int count = 1;
        if (projSelDTO.getPivotView().contains(PERIOD.getConstant())) {
            count = count + projSelDTO.getDiscountNoList().size();
        } else {
            count = count + projSelDTO.getPeriodList().size();
        }
        return count;
    }

    public static Object executeSelectQuery(String query, Object udc1, Object udc2) {

        return commonDao.executeSelectQuery(query, udc1, udc2);

    }

    private DiscountProjectionResultsDTO putHyphenForDiscount(List<String> tmpList, DiscountProjectionResultsDTO discountDto) {

        for (int j = 0; j < tmpList.size(); j++) {
            String column = tmpList.get(j);
            String commonColumn = column.replace(" ", StringUtils.EMPTY);
            String columns = commonColumn + ACTUALSRATE;
            discountDto.addStringProperties(columns, getFormattedValue(CUR_ZERO, Constant.NULL));
            String columns1 = commonColumn + ACTUALSAMOUNT;
            discountDto.addStringProperties(columns1, getFormattedValue(CUR_ZERO, Constant.NULL));
            String columns2 = commonColumn + PROJECTIONSAMOUNT;
            discountDto.addStringProperties(columns2, getFormattedValue(CUR_ZERO, Constant.NULL));
            String columns3 = commonColumn + PROJECTIONSRATE;
            discountDto.addStringProperties(columns3, getFormattedValue(CUR_ZERO, Constant.NULL));
        }

        return discountDto;

    }

}
