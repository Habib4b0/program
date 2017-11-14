/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.logic;

import com.stpl.app.gtnforecasting.dao.CommonDAO;
import com.stpl.app.gtnforecasting.dao.impl.CommonDAOImpl;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.app.gtnforecasting.dto.DiscountProjectionResultsDTO;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.model.ProjectionDetails;
import com.stpl.app.service.NmDiscountProjMasterLocalServiceUtil;
import com.stpl.app.service.ProjectionDetailsLocalServiceUtil;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.gtnforecasting.utils.HeaderUtils.getMonthForInt;
import static com.stpl.app.utils.Constants.FrequencyConstants.MONTHLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.QUARTERLY;
import static com.stpl.app.utils.Constants.LabelConstants.ACTUALS;
import static com.stpl.app.utils.Constants.LabelConstants.BOTH;
import static com.stpl.app.utils.Constants.LabelConstants.DASH;
import static com.stpl.app.utils.Constants.LabelConstants.PERIOD;
import static com.stpl.app.utils.Constants.LabelConstants.PROJECTIONS;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionList;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.SystemException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Nandhakumar
 */
public class DiscountProjectionResultsLogic {

    Map<String, String> periodMap = new HashMap<>();
    private static final DecimalFormat DOLLAR = new DecimalFormat("#,##0");
    private static final DecimalFormat UNITVOLUME = new DecimalFormat("#,##0.0");
    private static final DecimalFormat CUR_ZERO = new DecimalFormat("$#,##0");
    public static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(DiscountProjectionResultsLogic.class);
    private final String ACTUALSRATE = "ActualsRate";
    private final String ACTUALSAMOUNT = "ActualsAmount";
    private final String PROJECTIONSRATE = "ProjectionsRate";
    private final String PROJECTIONSAMOUNT = "ProjectionsAmount";
    private final String NULL = "null";
    private final String HYPHEN = "-";
    private final String PERCENTAGE = "%";
    private final String DOLLAR_SYMBOL = "$";
    private final String ZERO_SYMBOL = "0";
    Map<String, String> monthMap = new HashMap<>();
    Map<String, String> valueMap = new HashMap<>();

    public DiscountProjectionResultsLogic() {
        periodValueMap();
    }
    List<Integer> startAndEndPeriods = new ArrayList<>();
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
            int user = Integer.valueOf(userId);
            int session = Integer.valueOf(sessionId);
            List ccpId = null;
            if (selectedView.equals(Constant.CUSTOMER_SMALL)) {
                ccpId = NmDiscountProjMasterLocalServiceUtil.getCCPDetailsID(projectionId, PERCENTAGE, Constant.STRING_ONE);
            }
            if (selectedView.equals(Constant.PRODUCT_LABEL)) {
                ccpId = NmDiscountProjMasterLocalServiceUtil.getCCPDetailsIDForProductHierarchy(projectionId, PERCENTAGE, Constant.STRING_ONE);
            }
            if (selectedView.equals(Constant.CUSTOM_LABEL)) {
                ccpId = NmDiscountProjMasterLocalServiceUtil.getCCPDetailsID(projectionId, PERCENTAGE, Constant.STRING_ONE);
            }
            if (ccpId != null && !ccpId.isEmpty()) {
                List<Integer> proDetailsSid;
                final DynamicQuery projectiondetailsDynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionDetails.class);
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
                    List list = NmDiscountProjMasterLocalServiceUtil.getDiscountProjectionResults(proDetailsSid, freq, discountString, projection, Constant.PARENT, order, startAndEndPeriods, user, session,viewFlag);
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
        } catch (Exception e) {
            Logger.getLogger(DiscountProjectionResultsLogic.class.getName()).log(Level.SEVERE, null, e);
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
                ccpid = NmDiscountProjMasterLocalServiceUtil.getCCPDetailsID(projectionMasterId, hierarchyNo, level);
            }
            if (hierarchy.equals(Constant.PRODUCT_LABEL)) {
                ccpid = NmDiscountProjMasterLocalServiceUtil.getCCPDetailsIDForProductHierarchy(projectionMasterId, hierarchyNo, level);
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
                            int ccp = Integer.valueOf(id);
                            ccpid.add(ccp);
                        }
                    }
                }
            }
            if (proSelDTO.getGroup().startsWith(Constant.DISCOUNT)) {
                ccpid = null;
            }
            if (ccpid != null && !ccpid.isEmpty()) {
                final DynamicQuery projectionDetailsDynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionDetails.class);
                projectionDetailsDynamicQuery.add(RestrictionsFactoryUtil.in(Constant.CCP_DETAILS_SID, ccpid));
                projectionDetailsDynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_MASTER_SID, projectionMasterId));
                ProjectionList projList = ProjectionFactoryUtil.projectionList();
                projList.add(ProjectionFactoryUtil.property(Constant.PROJECTION_DETAILS_SID));
                projectionDetailsDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projList));
                List<Integer> projectionDetailsId = ProjectionDetailsLocalServiceUtil.dynamicQuery(projectionDetailsDynamicQuery);
                if (projectionDetailsId != null && !projectionDetailsId.isEmpty()) {
                    String userId = String.valueOf(proSelDTO.getUserId());
                    String sessionId = String.valueOf(proSelDTO.getSessionId());
                    int user = Integer.valueOf(userId);
                    int session = Integer.valueOf(sessionId);
                    String freq = String.valueOf(proSelDTO.getFrequency());
                    String discountString = CommonUtils.CollectionToString(discountList, true);
                    if (discountString.equals(StringUtils.EMPTY)) {
                        discountString = ZERO_SYMBOL;
                    }
                    List list = NmDiscountProjMasterLocalServiceUtil.getAllPesriodDiscount(projectionDetailsId, freq, discountString, StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, startAndEndPeriods, user, session);
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
        } catch (Exception e) {
            Logger.getLogger(DiscountProjectionResultsLogic.class.getName()).log(Level.SEVERE, null, e);
        }
        return discountProjList;
    }

    private void periodValueMap() {
        periodMap.put(Constant.MONTHLY, "MONTH");
        periodMap.put(Constant.QUARTERLY, Constant.QUARTER);
        periodMap.put(Constant.SEMI_ANNUALLY, Constant.QUARTER);
        periodMap.put(Constant.ANNUALLY, "YEAR");
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
                ccpId = NmDiscountProjMasterLocalServiceUtil.getCCPDetailsID(projectionId, PERCENTAGE, Constant.STRING_ONE);
            } else if (selectedView.equals(Constant.PRODUCT_LABEL)) {
                ccpId = NmDiscountProjMasterLocalServiceUtil.getCCPDetailsIDForProductHierarchy(projectionId, PERCENTAGE, Constant.STRING_ONE);
            } else if (selectedView.equals(Constant.CUSTOM_LABEL)) {
                ccpId = NmDiscountProjMasterLocalServiceUtil.getCCPDetailsID(projectionId, PERCENTAGE, Constant.STRING_ONE);
            }
            if (ccpId != null && !ccpId.isEmpty()) {
                List<Integer> proDetailsSid;
                final DynamicQuery projectiondetailsDynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionDetails.class);
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
                    int user = Integer.valueOf(userId);
                    int session = Integer.valueOf(sessionId);
                    String discountString = CommonUtils.CollectionToString(discountList, true);

                    if (discountString.equals(StringUtils.EMPTY)) {
                        discountString = ZERO_SYMBOL;
                    }
                    List list = NmDiscountProjMasterLocalServiceUtil.getAllPesriodDiscount(proDetailsSid, freq, discountString, StringUtils.EMPTY, StringUtils.EMPTY, order, startAndEndPeriods, user, session);
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
            Logger.getLogger(DiscountProjectionResultsLogic.class.getName()).log(Level.SEVERE, null, ex);
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
                ccpId = NmDiscountProjMasterLocalServiceUtil.getCCPDetailsID(projectionId, PERCENTAGE, Constant.STRING_ONE);
            }
            if (selectedView.equals(Constant.PRODUCT_LABEL)) {
                ccpId = NmDiscountProjMasterLocalServiceUtil.getCCPDetailsIDForProductHierarchy(projectionId, PERCENTAGE, Constant.STRING_ONE);
            }
            if (selectedView.equals(Constant.CUSTOM_LABEL)) {
                ccpId = NmDiscountProjMasterLocalServiceUtil.getCCPDetailsID(projectionId, PERCENTAGE, Constant.STRING_ONE);
            }
            if (ccpId != null && !ccpId.isEmpty()) {
                final DynamicQuery projectiondetailsDynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionDetails.class);
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
                    int user = Integer.valueOf(userId);
                    int session = Integer.valueOf(sessionId);
                    List<String> discountList;
                    discountList = projSelDTO.getDiscountNameList();
                    String discountString = getDiscountName(discountList);
                    List list = NmDiscountProjMasterLocalServiceUtil.getTotalDiscountNumber(proDetailsSid, freq, discountString, startAndEndPeriods, user, session,null);
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
                            Double aSales = Double.parseDouble(String.valueOf(object[NumericConstants.TWO]));
                            actualSales = actualSales + aSales;
                        }
                        if (object[NumericConstants.THREE] != null) {
                            Double aAmount = Double.parseDouble(String.valueOf(object[NumericConstants.THREE]));
                            actualAmount = actualAmount + aAmount;
                        }
                        if (object[NumericConstants.FOUR] != null) {
                            Double pSales = Double.parseDouble(String.valueOf(object[NumericConstants.FOUR]));
                            projectedSales = projectedSales + pSales;
                        }
                        if (object[NumericConstants.FIVE] != null) {
                            Double pAmount = Double.parseDouble(String.valueOf(object[NumericConstants.FIVE]));
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
                        } else {
                            for (int i = 1; i < list.size(); i++) {
                                Object[] obj = (Object[]) list.get(i);
                                String selectedDiscount = String.valueOf(obj[NumericConstants.EIGHT]);
                                if (currentDiscount.equals(selectedDiscount)) {
                                    if (obj[NumericConstants.TWO] != null) {
                                        Double aSales = Double.parseDouble(String.valueOf(obj[NumericConstants.TWO]));
                                        actualSales = actualSales + aSales;
                                    }
                                    if (obj[NumericConstants.THREE] != null) {
                                        Double aAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.THREE]));
                                        actualAmount = actualAmount + aAmount;
                                    }
                                    if (obj[NumericConstants.FOUR] != null) {
                                        Double pSales = Double.parseDouble(String.valueOf(obj[NumericConstants.FOUR]));
                                        projectedSales = projectedSales + pSales;
                                    }
                                    if (obj[NumericConstants.FIVE] != null) {
                                        Double pAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE]));
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
                                    commonColumn = currentDiscount.replace(" ", StringUtils.EMPTY);
                                    if (obj[NumericConstants.TWO] != null) {
                                        Double aSales = Double.parseDouble(String.valueOf(obj[NumericConstants.TWO]));
                                        actualSales = actualSales + aSales;
                                    }
                                    if (obj[NumericConstants.THREE] != null) {
                                        Double aAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.THREE]));
                                        actualAmount = actualAmount + aAmount;
                                    }

                                    if (obj[NumericConstants.FOUR] != null) {
                                        Double pSales = Double.parseDouble(String.valueOf(obj[NumericConstants.FOUR]));
                                        projectedSales = projectedSales + pSales;
                                    }
                                    if (obj[NumericConstants.FIVE] != null) {
                                        Double pAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE]));
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
                                }
                            }
                        }
                        discountProjList.add(discountDto);
                    }
                    return discountProjList;
                }
            }
        } catch (SystemException ex) {
            Logger.getLogger(DiscountProjectionResultsLogic.class.getName()).log(Level.SEVERE, null, ex);
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
            List ccpId = NmDiscountProjMasterLocalServiceUtil.getCCPDetailsID(projectionId, PERCENTAGE, Constant.STRING_ONE);
            if (ccpId != null && !ccpId.isEmpty()) {
                List<Integer> proDetailsSid;
                final DynamicQuery projectiondetailsDynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionDetails.class);
                projectiondetailsDynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_MASTER_SID, projectionId));
                projectiondetailsDynamicQuery.add(RestrictionsFactoryUtil.in(Constant.CCP_DETAILS_SID, ccpId));
                final ProjectionList projList = ProjectionFactoryUtil.projectionList();
                projList.add(ProjectionFactoryUtil.property(Constant.PROJECTION_DETAILS_SID));
                projectiondetailsDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projList));
                proDetailsSid = ProjectionDetailsLocalServiceUtil.dynamicQuery(projectiondetailsDynamicQuery);
                if (proDetailsSid != null && !proDetailsSid.isEmpty()) {
                    String userId = String.valueOf(projSelDTO.getUserId());
                    String sessionId = String.valueOf(projSelDTO.getSessionId());
                    int user = Integer.valueOf(userId);
                    int session = Integer.valueOf(sessionId);
                    String freq = String.valueOf(projSelDTO.getFrequency());
                    List<String> discountList;
                    discountList = projSelDTO.getDiscountNameList();
                    String discountString = getDiscountName(discountList);
                    List list = NmDiscountProjMasterLocalServiceUtil.getSubDiscount(proDetailsSid, freq, discountString, startAndEndPeriods, user, session);
                    if (list != null && !list.isEmpty()) {
                        if (freq.equals(Constant.ANNUALLY)) {
                            double actualSales = 0;
                            double actualAmount = 0;
                            double projectedSales = 0;
                            double projectedAmount = 0;
                            String commonColumn;
                            List<String> periodList = new ArrayList<>(projSelDTO.getPeriodList());
                            DiscountProjectionResultsDTO discountDto = new DiscountProjectionResultsDTO();
                            Object[] object = (Object[]) list.get(0);
                            String currentDiscount = String.valueOf(object[NumericConstants.EIGHT]);
                            int currentYear = (Integer) object[0];
                            commonColumn = currentDiscount.replace(" ", StringUtils.EMPTY);
                            discountDto.setGroup(String.valueOf(currentYear));
                            discountDto.setIsParent(ZERO_SYMBOL);
                            if (object[NumericConstants.TWO] != null) {
                                Double aSales = Double.parseDouble(String.valueOf(object[NumericConstants.TWO]));
                                actualSales = actualSales + aSales;
                            }
                            if (object[NumericConstants.THREE] != null) {
                                Double aAmount = Double.parseDouble(String.valueOf(object[NumericConstants.THREE]));
                                actualAmount = actualAmount + aAmount;
                            }
                            if (object[NumericConstants.FOUR] != null) {
                                Double pSales = Double.parseDouble(String.valueOf(object[NumericConstants.FOUR]));
                                projectedSales = projectedSales + pSales;
                            }
                            if (object[NumericConstants.FIVE] != null) {
                                Double pAmount = Double.parseDouble(String.valueOf(object[NumericConstants.FIVE]));
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
                                discountProjList.add(discountDto);
                            } else {
                                for (int i = 1; i < list.size(); i++) {
                                    Object[] obj = (Object[]) list.get(i);
                                    String selectedDiscount = String.valueOf(obj[NumericConstants.EIGHT]);
                                    selectedDiscount = selectedDiscount.replace(" ", StringUtils.EMPTY);
                                    int selectedYear = (Integer) obj[0];
                                    if (currentYear == selectedYear) {
                                        if (currentDiscount.equalsIgnoreCase(selectedDiscount)) {
                                            if (obj[NumericConstants.TWO] != null) {
                                                Double aSales = Double.parseDouble(String.valueOf(obj[NumericConstants.TWO]));
                                                actualSales = actualSales + aSales;
                                            }
                                            if (obj[NumericConstants.THREE] != null) {
                                                Double aAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.THREE]));
                                                actualAmount = actualAmount + aAmount;
                                            }
                                            if (obj[NumericConstants.FOUR] != null) {
                                                Double pSales = Double.parseDouble(String.valueOf(obj[NumericConstants.FOUR]));
                                                projectedSales = projectedSales + pSales;
                                            }
                                            if (obj[NumericConstants.FIVE] != null) {
                                                Double pAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE]));
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
                                            commonColumn = selectedDiscount.replace(" ", StringUtils.EMPTY);
                                            if (obj[NumericConstants.TWO] != null) {
                                                Double aSales = Double.parseDouble(String.valueOf(obj[NumericConstants.TWO]));
                                                actualSales = actualSales + aSales;
                                            }
                                            if (obj[NumericConstants.THREE] != null) {
                                                Double aAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.THREE]));
                                                actualAmount = actualAmount + aAmount;
                                            }
                                            if (obj[NumericConstants.FOUR] != null) {
                                                Double pSales = Double.parseDouble(String.valueOf(obj[NumericConstants.FOUR]));
                                                projectedSales = projectedSales + pSales;
                                            }
                                            if (obj[NumericConstants.FIVE] != null) {
                                                Double pAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE]));
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
                                            discountProjList.add(discountDto);
                                            periodList.remove(discountDto.getGroup());
                                        }
                                        actualSales = 0;
                                        actualAmount = 0;
                                        projectedSales = 0;
                                        projectedAmount = 0;
                                        discountDto = new DiscountProjectionResultsDTO();
                                        currentDiscount = selectedDiscount;
                                        discountDto.setGroup(String.valueOf(selectedYear));
                                        currentYear = selectedYear;
                                        commonColumn = selectedDiscount.replace(" ", StringUtils.EMPTY);
                                        discountDto.setIsParent(ZERO_SYMBOL);
                                        if (obj[NumericConstants.TWO] != null) {
                                            Double aSales = Double.parseDouble(String.valueOf(obj[NumericConstants.TWO]));
                                            actualSales = actualSales + aSales;
                                        }
                                        if (obj[NumericConstants.THREE] != null) {
                                            Double aAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.THREE]));
                                            actualAmount = actualAmount + aAmount;
                                        }
                                        if (obj[NumericConstants.FOUR] != null) {
                                            Double pSales = Double.parseDouble(String.valueOf(obj[NumericConstants.FOUR]));
                                            projectedSales = projectedSales + pSales;
                                        }
                                        if (obj[NumericConstants.FIVE] != null) {
                                            Double pAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE]));
                                            projectedAmount = projectedAmount + pAmount;
                                        }
                                    }
                                        if ((i == list.size() - 1)&& (periodList.contains(discountDto.getGroup()))) {
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

                        if (freq.equals(QUARTERLY.getConstant())) {
                            double actualSales = 0;
                            double actualAmount = 0;
                            double projectedSales = 0;
                            double projectedAmount = 0;
                            String commonColumn;
                            List<String> periodList = new ArrayList<>(projSelDTO.getPeriodList());
                            DiscountProjectionResultsDTO discountDto = new DiscountProjectionResultsDTO();
                            Object[] object = (Object[]) list.get(0);
                            String currentDiscount = String.valueOf(object[NumericConstants.EIGHT]);
                            int currentYear = (Integer) object[0];
                            int currentQuarter = (Integer) object[NumericConstants.SIX];
                            currentDiscount = currentDiscount.replace(" ", StringUtils.EMPTY);
                            commonColumn = currentDiscount;
                            discountDto.setGroup(Constant.Q + object[1] + " " + object[0]);
                            discountDto.setIsParent(ZERO_SYMBOL);
                            if (object[NumericConstants.TWO] != null) {
                                Double aSales = Double.parseDouble(String.valueOf(object[NumericConstants.TWO]));
                                actualSales = actualSales + aSales;
                            }
                            if (object[NumericConstants.THREE] != null) {
                                Double aAmount = Double.parseDouble(String.valueOf(object[NumericConstants.THREE]));
                                actualAmount = actualAmount + aAmount;
                            }
                            if (object[NumericConstants.FOUR] != null) {
                                Double pSales = Double.parseDouble(String.valueOf(object[NumericConstants.FOUR]));
                                projectedSales = projectedSales + pSales;
                            }
                            if (object[NumericConstants.FIVE] != null) {
                                Double pAmount = Double.parseDouble(String.valueOf(object[NumericConstants.FIVE]));
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
                                discountProjList.add(discountDto);
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
                                                Double aSales = Double.parseDouble(String.valueOf(obj[NumericConstants.TWO]));
                                                actualSales = actualSales + aSales;
                                            }
                                            if (obj[NumericConstants.THREE] != null) {
                                                Double aAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.THREE]));
                                                actualAmount = actualAmount + aAmount;
                                            }
                                            if (obj[NumericConstants.FOUR] != null) {
                                                Double pSales = Double.parseDouble(String.valueOf(obj[NumericConstants.FOUR]));
                                                projectedSales = projectedSales + pSales;
                                            }
                                            if (obj[NumericConstants.FIVE] != null) {
                                                Double pAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE]));
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
                                                Double aSales = Double.parseDouble(String.valueOf(obj[NumericConstants.TWO]));
                                                actualSales = actualSales + aSales;
                                            }
                                            if (obj[NumericConstants.THREE] != null) {
                                                Double aAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.THREE]));
                                                actualAmount = actualAmount + aAmount;
                                            }

                                            if (obj[NumericConstants.FOUR] != null) {
                                                Double pSales = Double.parseDouble(String.valueOf(obj[NumericConstants.FOUR]));
                                                projectedSales = projectedSales + pSales;
                                            }
                                            if (obj[NumericConstants.FIVE] != null) {
                                                Double pAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE]));
                                                projectedAmount = projectedAmount + pAmount;
                                            }
                                        }
                                    } else {
                                        String column = discountDto.getGroup().replace(" ", StringUtils.EMPTY);
                                        if (periodList.contains(column.replace(Constant.Q, Constant.Q_SMALL))) {
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
                                            discountProjList.add(discountDto);
                                            periodList.remove(column.replace(Constant.Q, Constant.Q_SMALL));
                                        }
                                        actualSales = 0;
                                        actualAmount = 0;
                                        projectedSales = 0;
                                        projectedAmount = 0;
                                        discountDto = new DiscountProjectionResultsDTO();
                                        discountDto.setGroup(Constant.Q + obj[1] + " " + obj[0]);
                                        currentYear = selectedYear;
                                        currentQuarter = selectedQuarter;
                                        currentDiscount = selectedDiscount;
                                        commonColumn = selectedDiscount;
                                        discountDto.setIsParent(ZERO_SYMBOL);
                                        if (obj[NumericConstants.TWO] != null) {
                                            Double aSales = Double.parseDouble(String.valueOf(obj[NumericConstants.TWO]));
                                            actualSales = actualSales + aSales;
                                        }
                                        if (obj[NumericConstants.THREE] != null) {
                                            Double aAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.THREE]));
                                            actualAmount = actualAmount + aAmount;
                                        }
                                        if (obj[NumericConstants.FOUR] != null) {
                                            Double pSales = Double.parseDouble(String.valueOf(obj[NumericConstants.FOUR]));
                                            projectedSales = projectedSales + pSales;
                                        }
                                        if (obj[NumericConstants.FIVE] != null) {
                                            Double pAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE]));
                                            projectedAmount = projectedAmount + pAmount;
                                        }
                                    }
                                    if (i == list.size() - 1) {
                                        String column = discountDto.getGroup().replace(" ", StringUtils.EMPTY);
                                        if (periodList.contains(column.replace(Constant.Q, Constant.Q_SMALL))) {
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
                                            discountProjList.add(discountDto);
                                            periodList.remove(column.replace(Constant.Q, Constant.Q_SMALL));
                                        }
                                    }
                                }
                            }
                            if (!periodList.isEmpty() && !projSelDTO.getDiscountNameList().isEmpty()) {
                                for (int i = 0; i < periodList.size(); i++) {
                                    DiscountProjectionResultsDTO projDTO = new DiscountProjectionResultsDTO();
                                    projDTO.setParent(0);
                                    projDTO.setProjectionTotal(1);
                                    String group = String.valueOf(periodList.get(i).replace(Constant.Q_SMALL, Constant.Q));
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
                            DiscountProjectionResultsDTO discountDto = new DiscountProjectionResultsDTO();
                            Object[] object = (Object[]) list.get(0);
                            String currentDiscount = String.valueOf(object[NumericConstants.EIGHT]);
                            int currentYear = (Integer) object[0];
                            int currentQuarter = (Integer) object[NumericConstants.SIX];
                            commonColumn = currentDiscount.replace(" ", StringUtils.EMPTY);
                            discountDto.setGroup(Constant.S + object[1] + " " + object[0]);
                            discountDto.setIsParent(ZERO_SYMBOL);
                            if (object[NumericConstants.TWO] != null) {
                                Double aSales = Double.parseDouble(String.valueOf(object[NumericConstants.TWO]));
                                actualSales = actualSales + aSales;
                            }
                            if (object[NumericConstants.THREE] != null) {
                                Double aAmount = Double.parseDouble(String.valueOf(object[NumericConstants.THREE]));
                                actualAmount = actualAmount + aAmount;
                            }
                            if (object[NumericConstants.FOUR] != null) {
                                Double pSales = Double.parseDouble(String.valueOf(object[NumericConstants.FOUR]));
                                projectedSales = projectedSales + pSales;
                            }
                            if (object[NumericConstants.FIVE] != null) {
                                Double pAmount = Double.parseDouble(String.valueOf(object[NumericConstants.FIVE]));
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
                                discountProjList.add(discountDto);
                            } else {
                                for (int i = 1; i < list.size(); i++) {
                                    Object[] obj = (Object[]) list.get(i);
                                    String selectedDiscount = String.valueOf(obj[NumericConstants.EIGHT]);
                                    int selectedYear = (Integer) obj[0];
                                    int selectedQuarter = (Integer) obj[NumericConstants.SIX];
                                    if (currentYear == selectedYear && currentQuarter == selectedQuarter) {
                                        if (currentDiscount.equalsIgnoreCase(selectedDiscount)) {
                                            if (obj[NumericConstants.TWO] != null) {
                                                Double aSales = Double.parseDouble(String.valueOf(obj[NumericConstants.TWO]));
                                                actualSales = actualSales + aSales;
                                            }
                                            if (obj[NumericConstants.THREE] != null) {
                                                Double aAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.THREE]));
                                                actualAmount = actualAmount + aAmount;
                                            }
                                            if (obj[NumericConstants.FOUR] != null) {
                                                Double pSales = Double.parseDouble(String.valueOf(obj[NumericConstants.FOUR]));
                                                projectedSales = projectedSales + pSales;
                                            }
                                            if (obj[NumericConstants.FIVE] != null) {
                                                Double pAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE]));
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
                                            commonColumn = selectedDiscount.replace(" ", StringUtils.EMPTY);
                                            if (obj[NumericConstants.TWO] != null) {
                                                Double aSales = Double.parseDouble(String.valueOf(obj[NumericConstants.TWO]));
                                                actualSales = actualSales + aSales;
                                            }
                                            if (obj[NumericConstants.THREE] != null) {
                                                Double aAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.THREE]));
                                                actualAmount = actualAmount + aAmount;
                                            }

                                            if (obj[NumericConstants.FOUR] != null) {
                                                Double pSales = Double.parseDouble(String.valueOf(obj[NumericConstants.FOUR]));
                                                projectedSales = projectedSales + pSales;
                                            }
                                            if (obj[NumericConstants.FIVE] != null) {
                                                Double pAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE]));
                                                projectedAmount = projectedAmount + pAmount;
                                            }
                                        }
                                    } else {
                                        String column = discountDto.getGroup().replace(" ", StringUtils.EMPTY);
                                        periodList.contains(column.replace(Constant.S, Constant.S_SMALL));
                                        {
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
                                            discountProjList.add(discountDto);
                                            periodList.remove(column.replace(Constant.S, Constant.S_SMALL));
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
                                        commonColumn = selectedDiscount.replace(" ", StringUtils.EMPTY);
                                        discountDto.setIsParent(ZERO_SYMBOL);
                                        if (obj[NumericConstants.TWO] != null) {
                                            Double aSales = Double.parseDouble(String.valueOf(obj[NumericConstants.TWO]));
                                            actualSales = actualSales + aSales;
                                        }
                                        if (obj[NumericConstants.THREE] != null) {
                                            Double aAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.THREE]));
                                            actualAmount = actualAmount + aAmount;
                                        }
                                        if (obj[NumericConstants.FOUR] != null) {
                                            Double pSales = Double.parseDouble(String.valueOf(obj[NumericConstants.FOUR]));
                                            projectedSales = projectedSales + pSales;
                                        }
                                        if (obj[NumericConstants.FIVE] != null) {
                                            Double pAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE]));
                                            projectedAmount = projectedAmount + pAmount;
                                        }
                                    }
                                    if (i == list.size() - 1) {
                                        String column = discountDto.getGroup().replace(" ", StringUtils.EMPTY);
                                        if (periodList.contains(column.replace(Constant.S, Constant.S_SMALL))) {
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
                                            discountProjList.add(discountDto);
                                            periodList.remove(column.replace(Constant.S, Constant.S_SMALL));
                                        }
                                    }
                                }
                            }
                            if (!periodList.isEmpty() && !projSelDTO.getDiscountNameList().isEmpty()) {
                                for (int i = 0; i < periodList.size(); i++) {
                                    DiscountProjectionResultsDTO projDTO = new DiscountProjectionResultsDTO();
                                    projDTO.setParent(0);
                                    projDTO.setProjectionTotal(1);
                                    String group = String.valueOf(periodList.get(i).replace(Constant.S_SMALL, Constant.S));
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
                            DiscountProjectionResultsDTO discountDto = new DiscountProjectionResultsDTO();
                            Object[] object = (Object[]) list.get(0);
                            String currentDiscount = String.valueOf(object[NumericConstants.EIGHT]);
                            int currentYear = (Integer) object[0];
                            int currentMonth = (Integer) object[NumericConstants.SIX];
                            commonColumn = currentDiscount.replace(" ", StringUtils.EMPTY);
                            discountDto.setGroup(CommonUtils.BUSINESS_PROCESS_INDICATOR_MANDATED + object[1] + " " + object[0]);
                            discountDto.setIsParent(ZERO_SYMBOL);
                            if (object[NumericConstants.TWO] != null) {
                                Double aSales = Double.parseDouble(String.valueOf(object[NumericConstants.TWO]));
                                actualSales = actualSales + aSales;
                            }
                            if (object[NumericConstants.THREE] != null) {
                                Double aAmount = Double.parseDouble(String.valueOf(object[NumericConstants.THREE]));
                                actualAmount = actualAmount + aAmount;
                            }
                            if (object[NumericConstants.FOUR] != null) {
                                Double pSales = Double.parseDouble(String.valueOf(object[NumericConstants.FOUR]));
                                projectedSales = projectedSales + pSales;
                            }
                            if (object[NumericConstants.FIVE] != null) {
                                Double pAmount = Double.parseDouble(String.valueOf(object[NumericConstants.FIVE]));
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
                                discountProjList.add(discountDto);
                            } else {
                                for (int i = 1; i < list.size(); i++) {
                                    Object[] obj = (Object[]) list.get(i);
                                    String selectedDiscount = String.valueOf(obj[NumericConstants.EIGHT]);
                                    int selectedYear = (Integer) obj[0];
                                    int selectedMonth = (Integer) obj[NumericConstants.SIX];
                                    if (currentYear == selectedYear && currentMonth == selectedMonth) {
                                        if (currentDiscount.equalsIgnoreCase(selectedDiscount)) {
                                            if (obj[NumericConstants.TWO] != null) {
                                                Double aSales = Double.parseDouble(String.valueOf(obj[NumericConstants.TWO]));
                                                actualSales = actualSales + aSales;
                                            }
                                            if (obj[NumericConstants.THREE] != null) {
                                                Double aAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.THREE]));
                                                actualAmount = actualAmount + aAmount;
                                            }
                                            if (obj[NumericConstants.FOUR] != null) {
                                                Double pSales = Double.parseDouble(String.valueOf(obj[NumericConstants.FOUR]));
                                                projectedSales = projectedSales + pSales;
                                            }
                                            if (obj[NumericConstants.FIVE] != null) {
                                                Double pAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE]));
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
                                            commonColumn = selectedDiscount.replace(" ", StringUtils.EMPTY);
                                            if (obj[NumericConstants.TWO] != null) {
                                                Double aSales = Double.parseDouble(String.valueOf(obj[NumericConstants.TWO]));
                                                actualSales = actualSales + aSales;
                                            }
                                            if (obj[NumericConstants.THREE] != null) {
                                                Double aAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.THREE]));
                                                actualAmount = actualAmount + aAmount;
                                            }
                                            if (obj[NumericConstants.FOUR] != null) {
                                                Double pSales = Double.parseDouble(String.valueOf(obj[NumericConstants.FOUR]));
                                                projectedSales = projectedSales + pSales;
                                            }
                                            if (obj[NumericConstants.FIVE] != null) {
                                                Double pAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE]));
                                                projectedAmount = projectedAmount + pAmount;
                                            }
                                        }
                                    } else {
                                        String column = createColumn(String.valueOf(discountDto.getGroup().replace(" ", StringUtils.EMPTY)));
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
                                            discountProjList.add(discountDto);
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
                                        commonColumn = selectedDiscount.replace(" ", StringUtils.EMPTY);
                                        currentDiscount = selectedDiscount;
                                        discountDto.setIsParent(ZERO_SYMBOL);
                                        if (obj[NumericConstants.TWO] != null) {
                                            Double aSales = Double.parseDouble(String.valueOf(obj[NumericConstants.TWO]));
                                            actualSales = actualSales + aSales;
                                        }
                                        if (obj[NumericConstants.THREE] != null) {
                                            Double aAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.THREE]));
                                            actualAmount = actualAmount + aAmount;
                                        }
                                        if (obj[NumericConstants.FOUR] != null) {
                                            Double pSales = Double.parseDouble(String.valueOf(obj[NumericConstants.FOUR]));
                                            projectedSales = projectedSales + pSales;
                                        }
                                        if (obj[NumericConstants.FIVE] != null) {
                                            Double pAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE]));
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
                        if (projSelDTO.getProjectionOrder().equals(Constant.DESCENDING)) {
                            Collections.reverse(discountProjList);
                        }

                        return discountProjList;
                    }
                }
            }
        } catch (SystemException ex) {
            Logger.getLogger(DiscountProjectionResultsLogic.class.getName()).log(Level.SEVERE, null, ex);
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
            ccpId = NmDiscountProjMasterLocalServiceUtil.getCCPDetailsID(projectionMasterId, hierarchyNo, level);
        }
        if (selectedView.equals(Constant.PRODUCT_LABEL)) {
            ccpId = NmDiscountProjMasterLocalServiceUtil.getCCPDetailsIDForProductHierarchy(projectionMasterId, hierarchyNo, level);
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
                        int ccp = Integer.valueOf(id);
                        ccpId.add(ccp);
                    }
                }
            }
        }
        if (ccpId != null && !ccpId.isEmpty()) {
            List<Integer> proDetailsSid;
            final DynamicQuery projectiondetailsDynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionDetails.class);
            projectiondetailsDynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_MASTER_SID, projectionMasterId));
            projectiondetailsDynamicQuery.add(RestrictionsFactoryUtil.in(Constant.CCP_DETAILS_SID, ccpId));
            final ProjectionList projList = ProjectionFactoryUtil.projectionList();
            projList.add(ProjectionFactoryUtil.property(Constant.PROJECTION_DETAILS_SID));
            projectiondetailsDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projList));
            proDetailsSid = ProjectionDetailsLocalServiceUtil.dynamicQuery(projectiondetailsDynamicQuery);
            if (proDetailsSid != null && !proDetailsSid.isEmpty()) {
                String userId = String.valueOf(projSelDTO.getUserId());
                String sessionId = String.valueOf(projSelDTO.getSessionId());
                int user = Integer.valueOf(userId);
                int session = Integer.valueOf(sessionId);
                String frequ = String.valueOf(projSelDTO.getFrequency());
                List<String> discountList;
                List<String> tmpList = new ArrayList<>();
                discountList = projSelDTO.getDiscountNameList();
                String discountString = getDiscountName(discountList);
                List list = NmDiscountProjMasterLocalServiceUtil.getSubDiscount(proDetailsSid, frequ, discountString, startAndEndPeriods, user, session);
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
                            Double aSales = Double.parseDouble(String.valueOf(object[NumericConstants.TWO]));
                            actualSales = actualSales + aSales;
                        }
                        if (object[NumericConstants.THREE] != null) {
                            Double aAmount = Double.parseDouble(String.valueOf(object[NumericConstants.THREE]));
                            actualAmount = actualAmount + aAmount;
                        }
                        if (object[NumericConstants.FOUR] != null) {
                            Double pSales = Double.parseDouble(String.valueOf(object[NumericConstants.FOUR]));
                            projectedSales = projectedSales + pSales;
                        }
                        if (object[NumericConstants.FIVE] != null) {
                            Double pAmount = Double.parseDouble(String.valueOf(object[NumericConstants.FIVE]));
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
                                            Double aSales = Double.parseDouble(String.valueOf(obj[NumericConstants.TWO]));
                                            actualSales = actualSales + aSales;
                                        }
                                        if (obj[NumericConstants.THREE] != null) {
                                            Double aAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.THREE]));
                                            actualAmount = actualAmount + aAmount;
                                        }
                                        if (obj[NumericConstants.FOUR] != null) {
                                            Double pSales = Double.parseDouble(String.valueOf(obj[NumericConstants.FOUR]));
                                            projectedSales = projectedSales + pSales;
                                        }
                                        if (obj[NumericConstants.FIVE] != null) {
                                            Double pAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE]));
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
                                            Double aSales = Double.parseDouble(String.valueOf(obj[NumericConstants.TWO]));
                                            actualSales = actualSales + aSales;
                                        }
                                        if (obj[NumericConstants.THREE] != null) {
                                            Double aAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.THREE]));
                                            actualAmount = actualAmount + aAmount;
                                        }
                                        if (obj[NumericConstants.FOUR] != null) {
                                            Double pSales = Double.parseDouble(String.valueOf(obj[NumericConstants.FOUR]));
                                            projectedSales = projectedSales + pSales;
                                        }
                                        if (obj[NumericConstants.FIVE] != null) {
                                            Double pAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE]));
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
                                        Double aSales = Double.parseDouble(String.valueOf(obj[NumericConstants.TWO]));
                                        actualSales = actualSales + aSales;
                                    }
                                    if (obj[NumericConstants.THREE] != null) {
                                        Double aAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.THREE]));
                                        actualAmount = actualAmount + aAmount;
                                    }
                                    if (obj[NumericConstants.FOUR] != null) {
                                        Double pSales = Double.parseDouble(String.valueOf(obj[NumericConstants.FOUR]));
                                        projectedSales = projectedSales + pSales;
                                    }
                                    if (obj[NumericConstants.FIVE] != null) {
                                        Double pAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE]));
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
                            Double aSales = Double.parseDouble(String.valueOf(object[NumericConstants.TWO]));
                            actualSales = actualSales + aSales;
                        }
                        if (object[NumericConstants.THREE] != null) {
                            Double aAmount = Double.parseDouble(String.valueOf(object[NumericConstants.THREE]));
                            actualAmount = actualAmount + aAmount;
                        }
                        if (object[NumericConstants.FOUR] != null) {
                            Double pSales = Double.parseDouble(String.valueOf(object[NumericConstants.FOUR]));
                            projectedSales = projectedSales + pSales;
                        }
                        if (object[NumericConstants.FIVE] != null) {
                            Double pAmount = Double.parseDouble(String.valueOf(object[NumericConstants.FIVE]));
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
                                            Double aSales = Double.parseDouble(String.valueOf(obj[NumericConstants.TWO]));
                                            actualSales = actualSales + aSales;
                                        }
                                        if (obj[NumericConstants.THREE] != null) {
                                            Double aAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.THREE]));
                                            actualAmount = actualAmount + aAmount;
                                        }
                                        if (obj[NumericConstants.FOUR] != null) {
                                            Double pSales = Double.parseDouble(String.valueOf(obj[NumericConstants.FOUR]));
                                            projectedSales = projectedSales + pSales;
                                        }
                                        if (obj[NumericConstants.FIVE] != null) {
                                            Double pAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE]));
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
                                            Double aSales = Double.parseDouble(String.valueOf(obj[NumericConstants.TWO]));
                                            actualSales = actualSales + aSales;
                                        }
                                        if (obj[NumericConstants.THREE] != null) {
                                            Double aAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.THREE]));
                                            actualAmount = actualAmount + aAmount;
                                        }
                                        if (obj[NumericConstants.FOUR] != null) {
                                            Double pSales = Double.parseDouble(String.valueOf(obj[NumericConstants.FOUR]));
                                            projectedSales = projectedSales + pSales;
                                        }
                                        if (obj[NumericConstants.FIVE] != null) {
                                            Double pAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE]));
                                            projectedAmount = projectedAmount + pAmount;
                                        }
                                    }
                                } else {
                                    String column = discountDto.getGroup().replace(" ", StringUtils.EMPTY);
                                    if (periodList.contains(String.valueOf(column).replace(Constant.Q, Constant.Q_SMALL))) {
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
                                        periodList.remove(String.valueOf(column).replace(Constant.Q, Constant.Q_SMALL));
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
                                        Double aSales = Double.parseDouble(String.valueOf(obj[NumericConstants.TWO]));
                                        actualSales = actualSales + aSales;
                                    }
                                    if (obj[NumericConstants.THREE] != null) {
                                        Double aAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.THREE]));
                                        actualAmount = actualAmount + aAmount;
                                    }
                                    if (obj[NumericConstants.FOUR] != null) {
                                        Double pSales = Double.parseDouble(String.valueOf(obj[NumericConstants.FOUR]));
                                        projectedSales = projectedSales + pSales;
                                    }
                                    if (obj[NumericConstants.FIVE] != null) {
                                        Double pAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE]));
                                        projectedAmount = projectedAmount + pAmount;
                                    }
                                }
                                if (i == list.size() - 1) {
                                    String column = discountDto.getGroup().replace(" ", StringUtils.EMPTY);
                                    if (periodList.contains(String.valueOf(column).replace(Constant.Q, Constant.Q_SMALL))) {
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
                                        periodList.remove(String.valueOf(column).replace(Constant.Q, Constant.Q_SMALL));
                                    }
                                }
                            }
                        }
                        if (!periodList.isEmpty() && !projSelDTO.getDiscountNameList().isEmpty()) {
                            for (int i = 0; i < periodList.size(); i++) {
                                DiscountProjectionResultsDTO projDTO = new DiscountProjectionResultsDTO();
                                projDTO.setParent(0);
                                projDTO.setProjectionTotal(1);
                                String group = String.valueOf(periodList.get(i).replace(Constant.Q_SMALL, Constant.Q));
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
                            Double aSales = Double.parseDouble(String.valueOf(object[NumericConstants.TWO]));
                            actualSales = actualSales + aSales;
                        }
                        if (object[NumericConstants.THREE] != null) {
                            Double aAmount = Double.parseDouble(String.valueOf(object[NumericConstants.THREE]));
                            actualAmount = actualAmount + aAmount;
                        }
                        if (object[NumericConstants.FOUR] != null) {
                            Double pSales = Double.parseDouble(String.valueOf(object[NumericConstants.FOUR]));
                            projectedSales = projectedSales + pSales;
                        }
                        if (object[NumericConstants.FIVE] != null) {
                            Double pAmount = Double.parseDouble(String.valueOf(object[NumericConstants.FIVE]));
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
                                            Double aSales = Double.parseDouble(String.valueOf(obj[NumericConstants.TWO]));
                                            actualSales = actualSales + aSales;
                                        }
                                        if (obj[NumericConstants.THREE] != null) {
                                            Double aAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.THREE]));
                                            actualAmount = actualAmount + aAmount;
                                        }
                                        if (obj[NumericConstants.FOUR] != null) {
                                            Double pSales = Double.parseDouble(String.valueOf(obj[NumericConstants.FOUR]));
                                            projectedSales = projectedSales + pSales;
                                        }
                                        if (obj[NumericConstants.FIVE] != null) {
                                            Double pAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE]));
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
                                            Double aSales = Double.parseDouble(String.valueOf(obj[NumericConstants.TWO]));
                                            actualSales = actualSales + aSales;
                                        }
                                        if (obj[NumericConstants.THREE] != null) {
                                            Double aAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.THREE]));
                                            actualAmount = actualAmount + aAmount;
                                        }
                                        if (obj[NumericConstants.FOUR] != null) {
                                            Double pSales = Double.parseDouble(String.valueOf(obj[NumericConstants.FOUR]));
                                            projectedSales = projectedSales + pSales;
                                        }
                                        if (obj[NumericConstants.FIVE] != null) {
                                            Double pAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE]));
                                            projectedAmount = projectedAmount + pAmount;
                                        }
                                    }
                                } else {
                                    String column = discountDto.getGroup().replace(" ", StringUtils.EMPTY);
                                    if (periodList.contains(String.valueOf(column).replace(Constant.S, Constant.S_SMALL))) {
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
                                        periodList.remove(String.valueOf(column).replace(Constant.S, Constant.S_SMALL));
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
                                        Double aSales = Double.parseDouble(String.valueOf(obj[NumericConstants.TWO]));
                                        actualSales = actualSales + aSales;
                                    }
                                    if (obj[NumericConstants.THREE] != null) {
                                        Double aAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.THREE]));
                                        actualAmount = actualAmount + aAmount;
                                    }
                                    if (obj[NumericConstants.FOUR] != null) {
                                        Double pSales = Double.parseDouble(String.valueOf(obj[NumericConstants.FOUR]));
                                        projectedSales = projectedSales + pSales;
                                    }
                                    if (obj[NumericConstants.FIVE] != null) {
                                        Double pAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE]));
                                        projectedAmount = projectedAmount + pAmount;
                                    }
                                }
                                if (i == list.size() - 1) {
                                    String column = discountDto.getGroup().replace(" ", StringUtils.EMPTY);
                                    if (periodList.contains(String.valueOf(column).replace(Constant.S, Constant.S_SMALL))) {
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
                                        periodList.remove(String.valueOf(column).replace(Constant.S, Constant.S_SMALL));

                                    }
                                }
                            }
                        }
                        if (!periodList.isEmpty() && !projSelDTO.getDiscountNameList().isEmpty()) {
                            for (int i = 0; i < periodList.size(); i++) {
                                DiscountProjectionResultsDTO projDTO = new DiscountProjectionResultsDTO();
                                projDTO.setParent(0);
                                projDTO.setProjectionTotal(1);
                                String group = String.valueOf(periodList.get(i).replace(Constant.S_SMALL, Constant.S));
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
                            Double aSales = Double.parseDouble(String.valueOf(object[NumericConstants.TWO]));
                            actualSales = actualSales + aSales;
                        }
                        if (object[NumericConstants.THREE] != null) {
                            Double aAmount = Double.parseDouble(String.valueOf(object[NumericConstants.THREE]));
                            actualAmount = actualAmount + aAmount;
                        }
                        if (object[NumericConstants.FOUR] != null) {
                            Double pSales = Double.parseDouble(String.valueOf(object[NumericConstants.FOUR]));
                            projectedSales = projectedSales + pSales;
                        }
                        if (object[NumericConstants.FIVE] != null) {
                            Double pAmount = Double.parseDouble(String.valueOf(object[NumericConstants.FIVE]));
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
                                            Double aSales = Double.parseDouble(String.valueOf(obj[NumericConstants.TWO]));
                                            actualSales = actualSales + aSales;
                                        }
                                        if (obj[NumericConstants.THREE] != null) {
                                            Double aAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.THREE]));
                                            actualAmount = actualAmount + aAmount;
                                        }
                                        if (obj[NumericConstants.FOUR] != null) {
                                            Double pSales = Double.parseDouble(String.valueOf(obj[NumericConstants.FOUR]));
                                            projectedSales = projectedSales + pSales;
                                        }
                                        if (obj[NumericConstants.FIVE] != null) {
                                            Double pAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE]));
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
                                            Double aSales = Double.parseDouble(String.valueOf(obj[NumericConstants.TWO]));
                                            actualSales = actualSales + aSales;
                                        }
                                        if (obj[NumericConstants.THREE] != null) {
                                            Double aAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.THREE]));
                                            actualAmount = actualAmount + aAmount;
                                        }

                                        if (obj[NumericConstants.FOUR] != null) {
                                            Double pSales = Double.parseDouble(String.valueOf(obj[NumericConstants.FOUR]));
                                            projectedSales = projectedSales + pSales;
                                        }
                                        if (obj[NumericConstants.FIVE] != null) {
                                            Double pAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE]));
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
                                        Double aSales = Double.parseDouble(String.valueOf(obj[NumericConstants.TWO]));
                                        actualSales = actualSales + aSales;
                                    }
                                    if (obj[NumericConstants.THREE] != null) {
                                        Double aAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.THREE]));
                                        actualAmount = actualAmount + aAmount;
                                    }
                                    if (obj[NumericConstants.FOUR] != null) {
                                        Double pSales = Double.parseDouble(String.valueOf(obj[NumericConstants.FOUR]));
                                        projectedSales = projectedSales + pSales;
                                    }
                                    if (obj[NumericConstants.FIVE] != null) {
                                        Double pAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE]));
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
        String[] array = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
        return Arrays.asList(array).indexOf(month) + 1;
    }

    public List<DiscountProjectionResultsDTO> getLevelFilterSum(DiscountProjectionResultsDTO dto, Map selection, SessionDTO session) throws SystemException {
        List<DiscountProjectionResultsDTO> discountProjList = new ArrayList<>();
          boolean viewFlag=Constant.VIEW.equals(session.getAction());
        int projectionMasterId = session.getProjectionId();
        String hierachyNumber = String.valueOf(dto.getHierarchyNo());
        dto.setIsParent(Constant.STRING_ONE);
        Integer levelNo = dto.getTreeLevelNo();
        hierachyNumber = hierachyNumber + PERCENTAGE;
        List ccpId = NmDiscountProjMasterLocalServiceUtil.getCCPDetailsID(projectionMasterId, hierachyNumber, String.valueOf(levelNo));
        String frequency = String.valueOf(selection.get(Constant.FREQUENCY));
        if (ccpId != null && !ccpId.isEmpty()) {
            final DynamicQuery projectionDetailsDynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionDetails.class);
            projectionDetailsDynamicQuery.add(RestrictionsFactoryUtil.in(Constant.CCP_DETAILS_SID, ccpId));
            projectionDetailsDynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_MASTER_SID, projectionMasterId));
            ProjectionList projList = ProjectionFactoryUtil.projectionList();
            projList.add(ProjectionFactoryUtil.property(Constant.PROJECTION_DETAILS_SID));
            projectionDetailsDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projList));
            List<Integer> projectionDetailsId = ProjectionDetailsLocalServiceUtil.dynamicQuery(projectionDetailsDynamicQuery);
            if (projectionDetailsId != null && !projectionDetailsId.isEmpty()) {
                String userId = String.valueOf(selection.get(Constant.USER_ID));
                String sessionId = String.valueOf(selection.get(Constant.SESSION_ID));
                int user = Integer.valueOf(userId);
                int session1 = Integer.valueOf(sessionId);
                List list = NmDiscountProjMasterLocalServiceUtil.getDiscountProjectionResults(projectionDetailsId, frequency, StringUtils.EMPTY, StringUtils.EMPTY, Constant.PARENT, StringUtils.EMPTY, startAndEndPeriods, user, session1,viewFlag);
                if (list != null && !list.isEmpty()) {
                    if (frequency.equals(QUARTERLY.getConstant())) {
                        double actualSales = 0;
                        double actualAmount = 0;
                        double projectedSales = 0;
                        double projectedAmount = 0;
                        int year = 0;
                        int Quarter = 0;
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
                            if (year == selectedYear && Quarter == selectedQuarter) {
                                if (object[NumericConstants.TWO] != null) {
                                    Double aSales = Double.parseDouble(String.valueOf(object[NumericConstants.TWO]));
                                    actualSales = actualSales + aSales;
                                }
                                if (object[NumericConstants.THREE] != null) {
                                    Double aAmount = Double.parseDouble(String.valueOf(object[NumericConstants.THREE]));
                                    actualAmount = actualAmount + aAmount;
                                }
                                if (object[NumericConstants.FOUR] != null) {
                                    Double pSales = Double.parseDouble(String.valueOf(object[NumericConstants.FOUR]));
                                    projectedSales = projectedSales + pSales;
                                }
                                if (object[NumericConstants.FIVE] != null) {
                                    Double pAmount = Double.parseDouble(String.valueOf(object[NumericConstants.FIVE]));
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
                                    Quarter = (Integer) object[NumericConstants.SIX];
                                }
                            } else {
                                if (j == 0) {
                                    year = selectedYear;
                                    Quarter = selectedQuarter;
                                    commonColumn = Constant.Q + object[NumericConstants.SIX] + object[0];
                                    if (object[NumericConstants.TWO] != null) {
                                        Double aSales = Double.parseDouble(String.valueOf(object[NumericConstants.TWO]));
                                        actualSales = actualSales + aSales;
                                    }
                                    if (object[NumericConstants.THREE] != null) {
                                        Double aAmount = Double.parseDouble(String.valueOf(object[NumericConstants.THREE]));
                                        actualAmount = actualAmount + aAmount;
                                    }
                                    if (object[NumericConstants.FOUR] != null) {
                                        Double pSales = Double.parseDouble(String.valueOf(object[NumericConstants.FOUR]));
                                        projectedSales = projectedSales + pSales;
                                    }
                                    if (object[NumericConstants.FIVE] != null) {
                                        Double pAmount = Double.parseDouble(String.valueOf(object[NumericConstants.FIVE]));
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
                                    Quarter = (Integer) object[NumericConstants.SIX];
                                    if (object[NumericConstants.TWO] != null) {
                                        Double aSales = Double.parseDouble(String.valueOf(object[NumericConstants.TWO]));
                                        actualSales = actualSales + aSales;
                                    }
                                    if (object[NumericConstants.THREE] != null) {
                                        Double aAmount = Double.parseDouble(String.valueOf(object[NumericConstants.THREE]));
                                        actualAmount = actualAmount + aAmount;
                                    }
                                    if (object[NumericConstants.FOUR] != null) {
                                        Double pSales = Double.parseDouble(String.valueOf(object[NumericConstants.FOUR]));
                                        projectedSales = projectedSales + pSales;
                                    }
                                    if (object[NumericConstants.FIVE] != null) {
                                        Double pAmount = Double.parseDouble(String.valueOf(object[NumericConstants.FIVE]));
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
                                    Double aSales = Double.parseDouble(String.valueOf(object[NumericConstants.TWO]));
                                    actualSales = actualSales + aSales;
                                }
                                if (object[NumericConstants.THREE] != null) {
                                    Double aAmount = Double.parseDouble(String.valueOf(object[NumericConstants.THREE]));
                                    actualAmount = actualAmount + aAmount;
                                }
                                if (object[NumericConstants.FOUR] != null) {
                                    Double pSales = Double.parseDouble(String.valueOf(object[NumericConstants.FOUR]));
                                    projectedSales = projectedSales + pSales;
                                }
                                if (object[NumericConstants.FIVE] != null) {
                                    Double pAmount = Double.parseDouble(String.valueOf(object[NumericConstants.FIVE]));
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
                                        Double aSales = Double.parseDouble(String.valueOf(object[NumericConstants.TWO]));
                                        actualSales = actualSales + aSales;
                                    }
                                    if (object[NumericConstants.THREE] != null) {
                                        Double aAmount = Double.parseDouble(String.valueOf(object[NumericConstants.THREE]));
                                        actualAmount = actualAmount + aAmount;
                                    }
                                    if (object[NumericConstants.FOUR] != null) {
                                        Double pSales = Double.parseDouble(String.valueOf(object[NumericConstants.FOUR]));
                                        projectedSales = projectedSales + pSales;
                                    }
                                    if (object[NumericConstants.FIVE] != null) {
                                        Double pAmount = Double.parseDouble(String.valueOf(object[NumericConstants.FIVE]));
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
                                        Double aSales = Double.parseDouble(String.valueOf(object[NumericConstants.TWO]));
                                        actualSales = actualSales + aSales;
                                    }
                                    if (object[NumericConstants.THREE] != null) {
                                        Double aAmount = Double.parseDouble(String.valueOf(object[NumericConstants.THREE]));
                                        actualAmount = actualAmount + aAmount;
                                    }
                                    if (object[NumericConstants.FOUR] != null) {
                                        Double pSales = Double.parseDouble(String.valueOf(object[NumericConstants.FOUR]));
                                        projectedSales = projectedSales + pSales;
                                    }
                                    if (object[NumericConstants.FIVE] != null) {
                                        Double pAmount = Double.parseDouble(String.valueOf(object[NumericConstants.FIVE]));
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
                                    Double aSales = Double.parseDouble(String.valueOf(object[NumericConstants.TWO]));
                                    actualSales = actualSales + aSales;
                                }
                                if (object[NumericConstants.THREE] != null) {
                                    Double aAmount = Double.parseDouble(String.valueOf(object[NumericConstants.THREE]));
                                    actualAmount = actualAmount + aAmount;
                                }
                                if (object[NumericConstants.FOUR] != null) {
                                    Double pSales = Double.parseDouble(String.valueOf(object[NumericConstants.FOUR]));
                                    projectedSales = projectedSales + pSales;
                                }
                                if (object[NumericConstants.FIVE] != null) {
                                    Double pAmount = Double.parseDouble(String.valueOf(object[NumericConstants.FIVE]));
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
                                        Double aSales = Double.parseDouble(String.valueOf(object[NumericConstants.TWO]));
                                        actualSales = actualSales + aSales;
                                    }
                                    if (object[NumericConstants.THREE] != null) {
                                        Double aAmount = Double.parseDouble(String.valueOf(object[NumericConstants.THREE]));
                                        actualAmount = actualAmount + aAmount;
                                    }
                                    if (object[NumericConstants.FOUR] != null) {
                                        Double pSales = Double.parseDouble(String.valueOf(object[NumericConstants.FOUR]));
                                        projectedSales = projectedSales + pSales;
                                    }
                                    if (object[NumericConstants.FIVE] != null) {
                                        Double pAmount = Double.parseDouble(String.valueOf(object[NumericConstants.FIVE]));
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
                                        Double aSales = Double.parseDouble(String.valueOf(object[NumericConstants.TWO]));
                                        actualSales = actualSales + aSales;
                                    }
                                    if (object[NumericConstants.THREE] != null) {
                                        Double aAmount = Double.parseDouble(String.valueOf(object[NumericConstants.THREE]));
                                        actualAmount = actualAmount + aAmount;
                                    }
                                    if (object[NumericConstants.FOUR] != null) {
                                        Double pSales = Double.parseDouble(String.valueOf(object[NumericConstants.FOUR]));
                                        projectedSales = projectedSales + pSales;
                                    }
                                    if (object[NumericConstants.FIVE] != null) {
                                        Double pAmount = Double.parseDouble(String.valueOf(object[NumericConstants.FIVE]));
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
                                    Double aSales = Double.parseDouble(String.valueOf(object[NumericConstants.TWO]));
                                    actualSales = actualSales + aSales;
                                }
                                if (object[NumericConstants.THREE] != null) {
                                    Double aAmount = Double.parseDouble(String.valueOf(object[NumericConstants.THREE]));
                                    actualAmount = actualAmount + aAmount;
                                }
                                if (object[NumericConstants.FOUR] != null) {
                                    Double pSales = Double.parseDouble(String.valueOf(object[NumericConstants.FOUR]));
                                    projectedSales = projectedSales + pSales;
                                }
                                if (object[NumericConstants.FIVE] != null) {
                                    Double pAmount = Double.parseDouble(String.valueOf(object[NumericConstants.FIVE]));
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
                                    String monthName = getMonthForInt(Integer.valueOf(StringUtils.EMPTY + object[1]) - 1);
                                    commonColumn = monthName + object[0];
                                    year = (Integer) object[0];
                                    month = (Integer) object[1];
                                }
                            } else {
                                if (j == 0) {
                                    year = selectedYear;
                                    month = selectedMonth;
                                    String monthName = getMonthForInt(Integer.valueOf(StringUtils.EMPTY + object[1]) - 1);
                                    commonColumn = monthName + object[0];
                                    if (object[NumericConstants.TWO] != null) {
                                        Double aSales = Double.parseDouble(String.valueOf(object[NumericConstants.TWO]));
                                        actualSales = actualSales + aSales;
                                    }
                                    if (object[NumericConstants.THREE] != null) {
                                        Double aAmount = Double.parseDouble(String.valueOf(object[NumericConstants.THREE]));
                                        actualAmount = actualAmount + aAmount;
                                    }
                                    if (object[NumericConstants.FOUR] != null) {
                                        Double pSales = Double.parseDouble(String.valueOf(object[NumericConstants.FOUR]));
                                        projectedSales = projectedSales + pSales;
                                    }
                                    if (object[NumericConstants.FIVE] != null) {
                                        Double pAmount = Double.parseDouble(String.valueOf(object[NumericConstants.FIVE]));
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
                                    String monthName = getMonthForInt(Integer.valueOf(StringUtils.EMPTY + object[1]) - 1);
                                    commonColumn = monthName + object[0];
                                    year = (Integer) object[0];
                                    month = (Integer) object[1];
                                    if (object[NumericConstants.TWO] != null) {
                                        Double aSales = Double.parseDouble(String.valueOf(object[NumericConstants.TWO]));
                                        actualSales = actualSales + aSales;
                                    }
                                    if (object[NumericConstants.THREE] != null) {
                                        Double aAmount = Double.parseDouble(String.valueOf(object[NumericConstants.THREE]));
                                        actualAmount = actualAmount + aAmount;
                                    }
                                    if (object[NumericConstants.FOUR] != null) {
                                        Double pSales = Double.parseDouble(String.valueOf(object[NumericConstants.FOUR]));
                                        projectedSales = projectedSales + pSales;
                                    }
                                    if (object[NumericConstants.FIVE] != null) {
                                        Double pAmount = Double.parseDouble(String.valueOf(object[NumericConstants.FIVE]));
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
                        Logger.getLogger(DiscountProjectionResultsLogic.class.getName()).log(Level.SEVERE, null, ex);
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

                        Logger.getLogger(DiscountProjectionResultsLogic.class.getName()).log(Level.SEVERE, null, ex);
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

                        Logger.getLogger(DiscountProjectionResultsLogic.class.getName()).log(Level.SEVERE, null, ex);
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
                    ccpId = NmDiscountProjMasterLocalServiceUtil.getCCPDetailsID(projectionMasterId, hierachyNumber, String.valueOf(dto.getTreeLevelNo()));
                }
                if (selectedView.equals(Constant.PRODUCT_LABEL)) {
                    ccpId = NmDiscountProjMasterLocalServiceUtil.getCCPDetailsIDForProductHierarchy(projectionMasterId, hierachyNumber, String.valueOf(dto.getTreeLevelNo()));
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
                                int ccpIds = Integer.valueOf(id);
                                ccpId.add(ccpIds);
                            }
                        }
                    }
                }
            } else {
                ccpId = ccp;
            }
            if (ccpId != null && !ccpId.isEmpty()) {
                final DynamicQuery projectionDetailsDynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionDetails.class);
                projectionDetailsDynamicQuery.add(RestrictionsFactoryUtil.in(Constant.CCP_DETAILS_SID, ccpId));
                projectionDetailsDynamicQuery.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_MASTER_SID, projectionMasterId));
                ProjectionList projList = ProjectionFactoryUtil.projectionList();
                projList.add(ProjectionFactoryUtil.property(Constant.PROJECTION_DETAILS_SID));
                projectionDetailsDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projList));
                List<Integer> projectionDetailsId = ProjectionDetailsLocalServiceUtil.dynamicQuery(projectionDetailsDynamicQuery);
                if (projectionDetailsId != null && !projectionDetailsId.isEmpty()) {
                    String userId = String.valueOf(projSelDTO.getUserId());
                    String sessionId = String.valueOf(projSelDTO.getSessionId());
                    int user = Integer.valueOf(userId);
                    int session = Integer.valueOf(sessionId);
                    String frequency = projSelDTO.getFrequency();
                    List<String> discountList;
                    discountList = projSelDTO.getDiscountNameList();
                    String discountString = getDiscountName(discountList);
                    List list = NmDiscountProjMasterLocalServiceUtil.getDiscountProjectionResults(projectionDetailsId, frequency, discountString, StringUtils.EMPTY, Constant.PARENT, StringUtils.EMPTY, yearList, user, session,viewFlag);
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
        } catch (Exception e) {
            Logger.getLogger(DiscountProjectionResultsLogic.class.getName()).log(Level.SEVERE, null, e);
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
                ccpId = NmDiscountProjMasterLocalServiceUtil.getCCPDetailsID(projectionMasterId, hierachyNumber, String.valueOf(dto.getTreeLevelNo()));
            }
            if (selectedView.equals(Constant.PRODUCT_LABEL)) {
                ccpId = NmDiscountProjMasterLocalServiceUtil.getCCPDetailsIDForProductHierarchy(projectionMasterId, hierachyNumber, String.valueOf(dto.getTreeLevelNo()));
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
                            int ccpIds = Integer.valueOf(id);
                            ccpId.add(ccpIds);
                        }
                    }
                }
            }
            if (ccpId != null && !ccpId.isEmpty()) {
                final DynamicQuery projectiondetailsDynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionDetails.class);
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
                    int user = Integer.valueOf(userId);
                    int session = Integer.valueOf(sessionId);
                    List<String> discountList;
                    List<String> tmpList = new ArrayList<>();
                    discountList = projSelDTO.getDiscountNameList();
                    String discountString = getDiscountName(discountList);
                    List list = NmDiscountProjMasterLocalServiceUtil.getTotalDiscountNumber(proDetailsSid, freq, discountString, pivotYearList, user, session,null);
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
                            Double aSales = Double.parseDouble(String.valueOf(object[NumericConstants.TWO]));
                            actualSales = actualSales + aSales;
                        }
                        if (object[NumericConstants.THREE] != null) {
                            Double aAmount = Double.parseDouble(String.valueOf(object[NumericConstants.THREE]));
                            actualAmount = actualAmount + aAmount;
                        }
                        if (object[NumericConstants.FOUR] != null) {
                            Double pSales = Double.parseDouble(String.valueOf(object[NumericConstants.FOUR]));
                            projectedSales = projectedSales + pSales;
                        }
                        if (object[NumericConstants.FIVE] != null) {
                            Double pAmount = Double.parseDouble(String.valueOf(object[NumericConstants.FIVE]));
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
                                        Double aSales = Double.parseDouble(String.valueOf(obj[NumericConstants.TWO]));
                                        actualSales = actualSales + aSales;
                                    }
                                    if (obj[NumericConstants.THREE] != null) {
                                        Double aAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.THREE]));
                                        actualAmount = actualAmount + aAmount;
                                    }
                                    if (obj[NumericConstants.FOUR] != null) {
                                        Double pSales = Double.parseDouble(String.valueOf(obj[NumericConstants.FOUR]));
                                        projectedSales = projectedSales + pSales;
                                    }
                                    if (obj[NumericConstants.FIVE] != null) {
                                        Double pAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE]));
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
                                        Double aSales = Double.parseDouble(String.valueOf(obj[NumericConstants.TWO]));
                                        actualSales = actualSales + aSales;
                                    }
                                    if (obj[NumericConstants.THREE] != null) {
                                        Double aAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.THREE]));
                                        actualAmount = actualAmount + aAmount;
                                    }
                                    if (obj[NumericConstants.FOUR] != null) {
                                        Double pSales = Double.parseDouble(String.valueOf(obj[NumericConstants.FOUR]));
                                        projectedSales = projectedSales + pSales;
                                    }
                                    if (obj[NumericConstants.FIVE] != null) {
                                        Double pAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE]));
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
        } catch (Exception e) {
            Logger.getLogger(DiscountProjectionResultsLogic.class.getName()).log(Level.SEVERE, null, e);
        }
        return dto;
    }



    public String getTradingPartnerLevel(int projectionMasterId) {
        String query = "SELECT distinct RLD1.level_no from  relationship_level_definition RLD1 JOIN PROJECTION_CUST_HIERARCHY PCH "
                + " ON PCH.relationship_level_sid = RLD1.relationship_level_sid  AND PCH.projection_master_sid =" + projectionMasterId + StringUtils.EMPTY
                + " WHERE RLD1.level_Name='Trading Partner'";
        return query;
    }

    public String getFormattedValue(DecimalFormat FORMAT, String value) {
        if (value.contains(Constant.NULL)) {
            value = DASH.getConstant();
        } else {
            Double newValue = Double.valueOf(value);
            if (FORMAT.toPattern().contains(Constant.PERCENT)) {
                newValue = newValue / NumericConstants.HUNDRED;
            }
            value = FORMAT.format(newValue);
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
        int Quarter = 0;

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
            if (year == selectedYear && Quarter == selectedQuarter) {
                if (obj[NumericConstants.TWO] != null) {
                    Double aSales = Double.parseDouble(String.valueOf(obj[NumericConstants.TWO]));
                    actualSales = actualSales + aSales;
                }
                if (obj[NumericConstants.THREE] != null) {
                    Double aAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.THREE]));
                    actualAmount = actualAmount + aAmount;
                }
                if (obj[NumericConstants.FOUR] != null) {
                    Double pSales = Double.parseDouble(String.valueOf(obj[NumericConstants.FOUR]));
                    projectedSales = projectedSales + pSales;
                }
                if (obj[NumericConstants.FIVE] != null) {
                    Double pAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE]));
                    projectedAmount = projectedAmount + pAmount;
                }
            } else {
                if (i == 0) {
                    year = selectedYear;
                    Quarter = selectedQuarter;
                    commonColumn = freq + obj[NumericConstants.SIX] + obj[0];
                    if (obj[NumericConstants.TWO] != null) {
                        Double aSales = Double.parseDouble(String.valueOf(obj[NumericConstants.TWO]));
                        actualSales = actualSales + aSales;
                    }
                    if (obj[NumericConstants.THREE] != null) {
                        Double aAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.THREE]));
                        actualAmount = actualAmount + aAmount;
                    }

                    if (obj[NumericConstants.FOUR] != null) {
                        Double pSales = Double.parseDouble(String.valueOf(obj[NumericConstants.FOUR]));
                        projectedSales = projectedSales + pSales;
                    }
                    if (obj[NumericConstants.FIVE] != null) {
                        Double pAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE]));
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
                    Quarter = (Integer) obj[NumericConstants.SIX];
                    if (obj[NumericConstants.TWO] != null) {
                        Double aSales = Double.parseDouble(String.valueOf(obj[NumericConstants.TWO]));
                        actualSales = actualSales + aSales;
                    }
                    if (obj[NumericConstants.THREE] != null) {
                        Double aAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.THREE]));
                        actualAmount = actualAmount + aAmount;
                    }
                    if (obj[NumericConstants.FOUR] != null) {
                        Double pSales = Double.parseDouble(String.valueOf(obj[NumericConstants.FOUR]));
                        projectedSales = projectedSales + pSales;
                    }
                    if (obj[NumericConstants.FIVE] != null) {
                        Double pAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE]));
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
                Quarter = (Integer) obj[NumericConstants.SIX];
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
                    Double aSales = Double.parseDouble(String.valueOf(obj[NumericConstants.TWO]));
                    actualSales = actualSales + aSales;
                }
                if (obj[NumericConstants.THREE] != null) {
                    Double aAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.THREE]));
                    actualAmount = actualAmount + aAmount;
                }
                if (obj[NumericConstants.FOUR] != null) {
                    Double pSales = Double.parseDouble(String.valueOf(obj[NumericConstants.FOUR]));
                    projectedSales = projectedSales + pSales;
                }
                if (obj[NumericConstants.FIVE] != null) {
                    Double pAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE]));
                    projectedAmount = projectedAmount + pAmount;
                }

            } else {
                if (i == 0) {
                    year = selectedYear;
                    commonColumn = StringUtils.EMPTY + obj[0];
                    if (obj[NumericConstants.TWO] != null) {
                        Double aSales = Double.parseDouble(String.valueOf(obj[NumericConstants.TWO]));
                        actualSales = actualSales + aSales;
                    }
                    if (obj[NumericConstants.THREE] != null) {
                        Double aAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.THREE]));
                        actualAmount = actualAmount + aAmount;
                    }
                    if (obj[NumericConstants.FOUR] != null) {
                        Double pSales = Double.parseDouble(String.valueOf(obj[NumericConstants.FOUR]));
                        projectedSales = projectedSales + pSales;
                    }
                    if (obj[NumericConstants.FIVE] != null) {
                        Double pAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE]));
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
                        Double aSales = Double.parseDouble(String.valueOf(obj[NumericConstants.TWO]));
                        actualSales = actualSales + aSales;
                    }
                    if (obj[NumericConstants.THREE] != null) {
                        Double aAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.THREE]));
                        actualAmount = actualAmount + aAmount;
                    }
                    if (obj[NumericConstants.FOUR] != null) {
                        Double pSales = Double.parseDouble(String.valueOf(obj[NumericConstants.FOUR]));
                        projectedSales = projectedSales + pSales;
                    }
                    if (obj[NumericConstants.FIVE] != null) {
                        Double pAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE]));
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
                    Double aSales = Double.parseDouble(String.valueOf(obj[NumericConstants.TWO]));
                    actualSales = actualSales + aSales;
                }
                if (obj[NumericConstants.THREE] != null) {
                    Double aAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.THREE]));
                    actualAmount = actualAmount + aAmount;
                }
                if (obj[NumericConstants.FOUR] != null) {
                    Double pSales = Double.parseDouble(String.valueOf(obj[NumericConstants.FOUR]));
                    projectedSales = projectedSales + pSales;
                }
                if (obj[NumericConstants.FIVE] != null) {
                    Double pAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE]));
                    projectedAmount = projectedAmount + pAmount;
                }

            } else {
                if (i == 0) {
                    year = selectedYear;
                    month = selectedMonth;
                    String monthName = getMonthForInt(Integer.valueOf(StringUtils.EMPTY + obj[1]) - 1);
                    monthName = monthName.toLowerCase();
                    commonColumn = monthName + obj[0];
                    if (obj[NumericConstants.TWO] != null) {
                        Double aSales = Double.parseDouble(String.valueOf(obj[NumericConstants.TWO]));
                        actualSales = actualSales + aSales;
                    }
                    if (obj[NumericConstants.THREE] != null) {
                        Double aAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.THREE]));
                        actualAmount = actualAmount + aAmount;
                    }
                    if (obj[NumericConstants.FOUR] != null) {
                        Double pSales = Double.parseDouble(String.valueOf(obj[NumericConstants.FOUR]));
                        projectedSales = projectedSales + pSales;
                    }
                    if (obj[NumericConstants.FIVE] != null) {
                        Double pAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE]));
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
                    String monthName = getMonthForInt(Integer.valueOf(StringUtils.EMPTY + obj[1]) - 1);
                    monthName = monthName.toLowerCase();
                    commonColumn = monthName + obj[0];
                    year = (Integer) obj[0];
                    month = (Integer) obj[1];
                    if (obj[NumericConstants.TWO] != null) {
                        Double aSales = Double.parseDouble(String.valueOf(obj[NumericConstants.TWO]));
                        actualSales = actualSales + aSales;
                    }
                    if (obj[NumericConstants.THREE] != null) {
                        Double aAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.THREE]));
                        actualAmount = actualAmount + aAmount;
                    }
                    if (obj[NumericConstants.FOUR] != null) {
                        Double pSales = Double.parseDouble(String.valueOf(obj[NumericConstants.FOUR]));
                        projectedSales = projectedSales + pSales;
                    }
                    if (obj[NumericConstants.FIVE] != null) {
                        Double pAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE]));
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
                String monthName = getMonthForInt(Integer.valueOf(StringUtils.EMPTY + obj[1]) - 1);
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
                        Double aSales = Double.parseDouble(String.valueOf(obj[NumericConstants.TWO]));
                        actualSales = actualSales + aSales;
                    }
                    if (obj[NumericConstants.THREE] != null) {
                        Double aAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.THREE]));
                        actualAmount = actualAmount + aAmount;
                    }
                    if (obj[NumericConstants.FOUR] != null) {
                        Double pSales = Double.parseDouble(String.valueOf(obj[NumericConstants.FOUR]));
                        projectedSales = projectedSales + pSales;
                    }
                    if (obj[NumericConstants.FIVE] != null) {
                        Double pAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE]));
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
                        Double aSales = Double.parseDouble(String.valueOf(obj[NumericConstants.TWO]));
                        actualSales = actualSales + aSales;
                    }
                    if (obj[NumericConstants.THREE] != null) {
                        Double aAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.THREE]));
                        actualAmount = actualAmount + aAmount;
                    }
                    if (obj[NumericConstants.FOUR] != null) {
                        Double pSales = Double.parseDouble(String.valueOf(obj[NumericConstants.FOUR]));
                        projectedSales = projectedSales + pSales;
                    }
                    if (obj[NumericConstants.FIVE] != null) {
                        Double pAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE]));
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
                        Double aSales = Double.parseDouble(String.valueOf(obj[NumericConstants.TWO]));
                        actualSales = actualSales + aSales;
                    }
                    if (obj[NumericConstants.THREE] != null) {
                        Double aAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.THREE]));
                        actualAmount = actualAmount + aAmount;
                    }
                    if (obj[NumericConstants.FOUR] != null) {
                        Double pSales = Double.parseDouble(String.valueOf(obj[NumericConstants.FOUR]));
                        projectedSales = projectedSales + pSales;
                    }
                    if (obj[NumericConstants.FIVE] != null) {
                        Double pAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE]));
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
                        Double aSales = Double.parseDouble(String.valueOf(obj[NumericConstants.TWO]));
                        actualSales = actualSales + aSales;
                    }
                    if (obj[NumericConstants.THREE] != null) {
                        Double aAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.THREE]));
                        actualAmount = actualAmount + aAmount;
                    }
                    if (obj[NumericConstants.FOUR] != null) {
                        Double pSales = Double.parseDouble(String.valueOf(obj[NumericConstants.FOUR]));
                        projectedSales = projectedSales + pSales;
                    }
                    if (obj[NumericConstants.FIVE] != null) {
                        Double pAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE]));
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
            Double aSales = Double.parseDouble(String.valueOf(object[NumericConstants.TWO]));
            actualSales = actualSales + aSales;
        }
        if (object[NumericConstants.THREE] != null) {
            Double aAmount = Double.parseDouble(String.valueOf(object[NumericConstants.THREE]));
            actualAmount = actualAmount + aAmount;
        }
        if (object[NumericConstants.FOUR] != null) {
            Double pSales = Double.parseDouble(String.valueOf(object[NumericConstants.FOUR]));
            projectedSales = projectedSales + pSales;
        }
        if (object[NumericConstants.FIVE] != null) {
            Double pAmount = Double.parseDouble(String.valueOf(object[NumericConstants.FIVE]));
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
                            Double aSales = Double.parseDouble(String.valueOf(obj[NumericConstants.TWO]));
                            actualSales = actualSales + aSales;
                        }
                        if (obj[NumericConstants.THREE] != null) {
                            Double aAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.THREE]));
                            actualAmount = actualAmount + aAmount;
                        }
                        if (obj[NumericConstants.FOUR] != null) {
                            Double pSales = Double.parseDouble(String.valueOf(obj[NumericConstants.FOUR]));
                            projectedSales = projectedSales + pSales;
                        }
                        if (obj[NumericConstants.FIVE] != null) {
                            Double pAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE]));
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
                            Double aSales = Double.parseDouble(String.valueOf(obj[NumericConstants.TWO]));
                            actualSales = actualSales + aSales;
                        }
                        if (obj[NumericConstants.THREE] != null) {
                            Double aAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.THREE]));
                            actualAmount = actualAmount + aAmount;
                        }
                        if (obj[NumericConstants.FOUR] != null) {
                            Double pSales = Double.parseDouble(String.valueOf(obj[NumericConstants.FOUR]));
                            projectedSales = projectedSales + pSales;
                        }
                        if (obj[NumericConstants.FIVE] != null) {
                            Double pAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE]));
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
                        Double aSales = Double.parseDouble(String.valueOf(obj[NumericConstants.TWO]));
                        actualSales = actualSales + aSales;
                    }
                    if (obj[NumericConstants.THREE] != null) {
                        Double aAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.THREE]));
                        actualAmount = actualAmount + aAmount;
                    }
                    if (obj[NumericConstants.FOUR] != null) {
                        Double pSales = Double.parseDouble(String.valueOf(obj[NumericConstants.FOUR]));
                        projectedSales = projectedSales + pSales;
                    }
                    if (obj[NumericConstants.FIVE] != null) {
                        Double pAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE]));
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
        String monthName = getMonthForInt(Integer.valueOf(StringUtils.EMPTY + object[NumericConstants.SEVEN]) - 1);
        monthName = monthName.toLowerCase();
        commonColumn = monthName + object[0];
        dto.setIsParent(ZERO_SYMBOL);
        dto.setGroup(currentDiscount);
        discountList.remove(currentDiscount);
        if (object[NumericConstants.TWO] != null) {
            Double aSales = Double.parseDouble(String.valueOf(object[NumericConstants.TWO]));
            actualSales = actualSales + aSales;
        }
        if (object[NumericConstants.THREE] != null) {
            Double aAmount = Double.parseDouble(String.valueOf(object[NumericConstants.THREE]));
            actualAmount = actualAmount + aAmount;
        }
        if (object[NumericConstants.FOUR] != null) {
            Double pSales = Double.parseDouble(String.valueOf(object[NumericConstants.FOUR]));
            projectedSales = projectedSales + pSales;
        }
        if (object[NumericConstants.FIVE] != null) {
            Double pAmount = Double.parseDouble(String.valueOf(object[NumericConstants.FIVE]));
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
                            Double aSales = Double.parseDouble(String.valueOf(obj[NumericConstants.TWO]));
                            actualSales = actualSales + aSales;
                        }
                        if (obj[NumericConstants.THREE] != null) {
                            Double aAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.THREE]));
                            actualAmount = actualAmount + aAmount;
                        }
                        if (obj[NumericConstants.FOUR] != null) {
                            Double pSales = Double.parseDouble(String.valueOf(obj[NumericConstants.FOUR]));
                            projectedSales = projectedSales + pSales;
                        }
                        if (obj[NumericConstants.FIVE] != null) {
                            Double pAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE]));
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
                        monthName = getMonthForInt(Integer.valueOf(StringUtils.EMPTY + obj[NumericConstants.SEVEN]) - 1);
                        monthName = monthName.toLowerCase();
                        commonColumn = monthName + obj[0];
                        currentYear = selectedYear;
                        currentMonth = selectedMonth;
                        dto.setIsParent(ZERO_SYMBOL);
                        if (obj[NumericConstants.TWO] != null) {
                            Double aSales = Double.parseDouble(String.valueOf(obj[NumericConstants.TWO]));
                            actualSales = actualSales + aSales;
                        }
                        if (obj[NumericConstants.THREE] != null) {
                            Double aAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.THREE]));
                            actualAmount = actualAmount + aAmount;
                        }

                        if (obj[NumericConstants.FOUR] != null) {
                            Double pSales = Double.parseDouble(String.valueOf(obj[NumericConstants.FOUR]));
                            projectedSales = projectedSales + pSales;
                        }
                        if (obj[NumericConstants.FIVE] != null) {
                            Double pAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE]));
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
                    monthName = getMonthForInt(Integer.valueOf(StringUtils.EMPTY + obj[NumericConstants.SEVEN]) - 1);
                    monthName = monthName.toLowerCase();
                    commonColumn = monthName + obj[0];
                    currentYear = selectedYear;
                    currentMonth = selectedMonth;
                    if (obj[NumericConstants.TWO] != null) {
                        Double aSales = Double.parseDouble(String.valueOf(obj[NumericConstants.TWO]));
                        actualSales = actualSales + aSales;
                    }
                    if (obj[NumericConstants.THREE] != null) {
                        Double aAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.THREE]));
                        actualAmount = actualAmount + aAmount;
                    }
                    if (obj[NumericConstants.FOUR] != null) {
                        Double pSales = Double.parseDouble(String.valueOf(obj[NumericConstants.FOUR]));
                        projectedSales = projectedSales + pSales;
                    }
                    if (obj[NumericConstants.FIVE] != null) {
                        Double pAmount = Double.parseDouble(String.valueOf(obj[NumericConstants.FIVE]));
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
                    Logger.getLogger(DiscountProjectionResultsLogic.class.getName()).log(Level.SEVERE, null, ex);
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
