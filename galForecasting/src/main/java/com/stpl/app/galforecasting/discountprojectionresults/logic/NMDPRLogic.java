/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.discountprojectionresults.logic;

import com.stpl.app.galforecasting.dao.CommonDAO;
import com.stpl.app.galforecasting.dao.impl.CommonDAOImpl;
import com.stpl.app.galforecasting.discountprojectionresults.dto.DiscountProjectionResultsDTO;
import com.stpl.app.galforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.galforecasting.logic.CommonLogic;
import com.stpl.app.galforecasting.sessionutils.SessionDTO;
import com.stpl.app.galforecasting.utils.CommonUtils;
import com.stpl.app.galforecasting.utils.Constant;
import static com.stpl.app.galforecasting.utils.Constant.FrequencyConstants.ANNUALLY;
import static com.stpl.app.galforecasting.utils.Constant.FrequencyConstants.SEMI_ANNUALLY;
import static com.stpl.app.galforecasting.utils.HeaderUtils.getMonthForInt;
import com.stpl.app.model.NmProjectionSelection;
import com.stpl.app.model.ProjectionDetails;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.NmDiscountProjMasterLocalServiceUtil;
import com.stpl.app.service.ProjectionDetailsLocalServiceUtil;
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
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionList;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author pvinoth
 */
public class NMDPRLogic {

    /**
     *
     */
    private static final long serialVersionUID = 4428373722392530081L;
    private Map<String, String> periodMap = new HashMap<String, String>();
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
    private static final String HYPHEN = "-";
    private static final String Q_SMALL = "q";
    private static final String Q_BIG = "Q";
    private static final String PERCENTAGE = Constant.PERCENT;
    private static final String DOLLAR_SYMBOL = "$";
    private static final String ZERO = "0";
    private static final String ONE = "1";
    private static final String TWO = "2";
    private static final String THREE = "3";
    private static final String FOUR = "4";
    private static final String PROJECTION_MASTER_SID = "projectionMasterSid";
    private static final String PROJECTION_DETAILS_SID = "projectionDetailsSid";
    private static final String CCP_DETAILS_SID = "ccpDetailsSid";
    private Map<String, String> monthMap = new HashMap<String, String>();
    private Map<String, String> valueMap = new HashMap<String, String>();
    private List<Integer> startAndEndPeriods = new ArrayList<Integer>();
    private static final CommonDAO commonDao = new CommonDAOImpl();

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
    public List<DiscountProjectionResultsDTO> getPeriodProjectionTotal(int projectionId, List<Integer> startAndEndPeriods, ProjectionSelectionDTO projSelDTO) throws SystemException {
        List<DiscountProjectionResultsDTO> discountProjList = new ArrayList<DiscountProjectionResultsDTO>();
        String selectedView = projSelDTO.getView();
        String userId = String.valueOf(projSelDTO.getUserId());
        String sessionId = String.valueOf(projSelDTO.getSessionId());
        int user = Integer.parseInt(userId);
        int session = Integer.parseInt(sessionId);
        List ccpId = null;
        int projId=projSelDTO.getProjectionId();
        int divideRPU = 0;
        if ( isProgramcategory(projSelDTO.getProjectionId())) {
            divideRPU=getDiscountRSCount(userId, sessionId, projId);
        }
        
        if (CUSTOMER.getConstant().equals(selectedView)) {
            ccpId = NmDiscountProjMasterLocalServiceUtil.getCCPDetailsID(projectionId, PERCENTAGE, Constant.STRING_ONE);
        }
        if (PRODUCT.getConstant().equals(selectedView)) {
            ccpId = NmDiscountProjMasterLocalServiceUtil.getCCPDetailsIDForProductHierarchy(projectionId, PERCENTAGE, Constant.STRING_ONE);
        }
        if (CUSTOM.getConstant().equals(selectedView)) {
            ccpId = NmDiscountProjMasterLocalServiceUtil.getCCPDetailsID(projectionId, PERCENTAGE, Constant.STRING_ONE);
        }
        if (ccpId != null && !ccpId.isEmpty()) {
            List<Integer> proDetailsSid = new ArrayList<Integer>();
            final DynamicQuery projectiondetailsDynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionDetails.class);
            projectiondetailsDynamicQuery.add(RestrictionsFactoryUtil.eq(PROJECTION_MASTER_SID, projectionId));
            projectiondetailsDynamicQuery.add(RestrictionsFactoryUtil.in(CCP_DETAILS_SID, ccpId));
            final ProjectionList projList = ProjectionFactoryUtil.projectionList();
            projList.add(ProjectionFactoryUtil.property(PROJECTION_DETAILS_SID));
            projectiondetailsDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projList));
            proDetailsSid = ProjectionDetailsLocalServiceUtil.dynamicQuery(projectiondetailsDynamicQuery);
            if (proDetailsSid != null && !proDetailsSid.isEmpty()) {
                String freq = projSelDTO.getFrequency();
                String order = projSelDTO.getProjectionOrder();
                String projection = projSelDTO.getView();
                List<String> discountList = new ArrayList<String>();
                discountList = projSelDTO.getDiscountNameList();
                String discountString = getDiscountName(discountList);
                projSelDTO.setCcpCount(proDetailsSid.size());
                List list = NmDiscountProjMasterLocalServiceUtil.getDiscountProjectionResults(proDetailsSid, freq, discountString, projection, Constant.PARENT, order, startAndEndPeriods, user, session);
                DiscountProjectionResultsDTO discountDto = null;
                if (list != null && !list.isEmpty()) {
                    if (QUARTERLY.getConstant().equals(freq)) {
                        discountDto = new DiscountProjectionResultsDTO();
                        discountDto.setGroup(Constant.PROJECTION_TOTAL);
                        discountDto.setLevelNo(0);
                        discountDto.setHierarchylevelId(ZERO);
                        discountDto.setIsParent(Constant.STRING_ONE);
                        discountDto.setHierarchyNo(Constant.STRING_ONE);
                        discountDto.setParent(0);
                        discountDto.setTotal(1);
                        discountDto = getValueForDTO(projSelDTO, list, discountDto, Q_SMALL,divideRPU);
                        discountProjList.add(discountDto);
                        return discountProjList;
                    }
                    if (SEMI_ANNUALLY.getConstant().equals(freq)) {
                        discountDto = new DiscountProjectionResultsDTO();
                        discountDto.setGroup(Constant.PROJECTION_TOTAL);
                        discountDto.setHierarchySid(ZERO);
                        discountDto.setLevelNo(0);
                        discountDto.setHierarchylevelId(ZERO);
                        discountDto.setIsParent(Constant.STRING_ONE);
                        discountDto.setParent(0);
                        discountDto.setHierarchyNo(Constant.STRING_ONE);
                        discountDto.setTotal(1);
                        discountDto = getValueForDTO(projSelDTO, list, discountDto, Constant.S_SMALL,divideRPU);
                        discountProjList.add(discountDto);
                        return discountProjList;
                    }
                    if (ANNUALLY.getConstant().equals(freq)) {
                        discountDto = new DiscountProjectionResultsDTO();
                        discountDto.setGroup(Constant.PROJECTION_TOTAL);
                        discountDto.setHierarchySid(ZERO);
                        discountDto.setLevelNo(0);
                        discountDto.setHierarchylevelId(ZERO);
                        discountDto.setIsParent(Constant.STRING_ONE);
                        discountDto.setParent(0);
                        discountDto.setHierarchyNo(Constant.STRING_ONE);
                        discountDto.setTotal(1);
                        discountDto = getValueForYearDTO(projSelDTO, list, discountDto,divideRPU);
                        discountProjList.add(discountDto);
                        return discountProjList;
                    }
                    if (MONTHLY.getConstant().equals(freq)) {
                        discountDto = new DiscountProjectionResultsDTO();
                        discountDto.setGroup(Constant.PROJECTION_TOTAL);
                        discountDto.setHierarchySid(ZERO);
                        discountDto.setLevelNo(0);
                        discountDto.setHierarchylevelId(ZERO);
                        discountDto.setIsParent(Constant.STRING_ONE);
                        discountDto.setParent(0);
                        discountDto.setHierarchyNo(Constant.STRING_ONE);
                        discountDto.setTotal(1);
                        discountDto = getValueForMonthDTO(projSelDTO, list, discountDto,divideRPU);
                        discountProjList.add(discountDto);
                        return discountProjList;
                    }
                }
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
    public List<DiscountProjectionResultsDTO> getPeriodHierarchy(ProjectionSelectionDTO proSelDTO, List<Integer> startAndEndPeriods, List<String> discountList) throws SystemException, PortalException {
        List<DiscountProjectionResultsDTO> discountProjList = new ArrayList<DiscountProjectionResultsDTO>();
        String level = String.valueOf(proSelDTO.getTreeLevelNo());
        String hierarchyNo = String.valueOf(proSelDTO.getHierarchyNo());
        int projectionMasterId = proSelDTO.getProjectionId();
        String hierarchy = proSelDTO.getView();
        List ccpid = null;
        if (CUSTOMER.getConstant().equals(hierarchy)) {
            ccpid = NmDiscountProjMasterLocalServiceUtil.getCCPDetailsID(projectionMasterId, hierarchyNo, level);
        }
        if (PRODUCT.getConstant().equals(hierarchy)) {
            ccpid = NmDiscountProjMasterLocalServiceUtil.getCCPDetailsIDForProductHierarchy(projectionMasterId, hierarchyNo, level);
        }
        if (CUSTOM.getConstant().equals(hierarchy)) {
            proSelDTO.setIsCustomHierarchy(true);
            String customQuery = CommonLogic.getCustomCCPQuery(proSelDTO);
            List<Object> list = (List<Object>) executeSelectQuery(customQuery, null, null);
            if (list != null && !list.isEmpty()) {
                ccpid = new ArrayList();
                for (int i = 0; i < list.size(); i++) {
                    Object[] obj = (Object[]) list.get(i);
                    String id = String.valueOf(obj[1]);
                    if (!NULL.equals(id)) {
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
            final DynamicQuery projectionDetailsDynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionDetails.class);
            projectionDetailsDynamicQuery.add(RestrictionsFactoryUtil.in(CCP_DETAILS_SID, ccpid));
            projectionDetailsDynamicQuery.add(RestrictionsFactoryUtil.eq(PROJECTION_MASTER_SID, projectionMasterId));
            ProjectionList projList = ProjectionFactoryUtil.projectionList();
            projList.add(ProjectionFactoryUtil.property(PROJECTION_DETAILS_SID));
            projectionDetailsDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projList));
            List<Integer> projectionDetailsId = ProjectionDetailsLocalServiceUtil.dynamicQuery(projectionDetailsDynamicQuery);
            if (projectionDetailsId != null && !projectionDetailsId.isEmpty()) {
                String userId = String.valueOf(proSelDTO.getUserId());
                String sessionId = String.valueOf(proSelDTO.getSessionId());
                int user = Integer.parseInt(userId);
                int session = Integer.parseInt(sessionId);
                String freq = String.valueOf(proSelDTO.getFrequency());
                String discountString = CommonUtils.collectionToString(discountList, true);
                if (discountString.equals(StringUtils.EMPTY)) {
                    discountString = ZERO;
                }
                List list = NmDiscountProjMasterLocalServiceUtil.getAllPesriodDiscount(projectionDetailsId, freq, discountString, StringUtils.EMPTY, StringUtils.EMPTY, StringUtils.EMPTY, startAndEndPeriods, user, session);
                if (list != null && !list.isEmpty()) {
                    if (QUARTERLY.getConstant().equals(freq)) {
                        discountProjList = getDiscountListDto(proSelDTO, list, discountProjList, Q_SMALL, discountList);
                    }
                    if (SEMI_ANNUALLY.getConstant().equals(freq)) {
                        discountProjList = getDiscountListDto(proSelDTO, list, discountProjList, Constant.S_SMALL, discountList);
                    }
                    if (ANNUALLY.getConstant().equals(freq)) {
                        discountProjList = getDiscountListYearDto(proSelDTO, list, discountProjList, discountList);
                    }
                    if (MONTHLY.getConstant().equals(freq)) {
                        discountProjList = getDiscountListMonthDto(proSelDTO, list, discountProjList, discountList);
                    }
                }
            }
        }
        return discountProjList;
    }

    private void periodValueMap() {
        periodMap.put(MONTHLY.getConstant(), "MONTH");
        periodMap.put(QUARTERLY.getConstant(), "QUARTER");
        periodMap.put(SEMI_ANNUALLY.getConstant(), "QUARTER");
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
            List<Integer> startAndEndPeriods, ProjectionSelectionDTO projSelDTO, List<String> discountList) throws SystemException, PortalException {
        List<DiscountProjectionResultsDTO> discountProjList = new ArrayList<DiscountProjectionResultsDTO>();
        List ccpId = null;
        String selectedView = projSelDTO.getView();
        if (CUSTOMER.getConstant().equals(selectedView)) {
            ccpId = NmDiscountProjMasterLocalServiceUtil.getCCPDetailsID(projectionId, PERCENTAGE, Constant.STRING_ONE);
        } else if (PRODUCT.getConstant().equals(selectedView)) {
            ccpId = NmDiscountProjMasterLocalServiceUtil.getCCPDetailsIDForProductHierarchy(projectionId, PERCENTAGE, Constant.STRING_ONE);
        } else if (CUSTOM.getConstant().equals(selectedView)) {
            ccpId = NmDiscountProjMasterLocalServiceUtil.getCCPDetailsID(projectionId, PERCENTAGE, Constant.STRING_ONE);
        }
        if (ccpId != null && !ccpId.isEmpty()) {
            List<Integer> proDetailsSid = new ArrayList<Integer>();
            final DynamicQuery projectiondetailsDynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionDetails.class);
            projectiondetailsDynamicQuery.add(RestrictionsFactoryUtil.eq(PROJECTION_MASTER_SID, projectionId));
            projectiondetailsDynamicQuery.add(RestrictionsFactoryUtil.in(CCP_DETAILS_SID, ccpId));
            final ProjectionList projList = ProjectionFactoryUtil.projectionList();
            projList.add(ProjectionFactoryUtil.property(PROJECTION_DETAILS_SID));
            projectiondetailsDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projList));
            proDetailsSid = ProjectionDetailsLocalServiceUtil.dynamicQuery(projectiondetailsDynamicQuery);
            if (proDetailsSid != null && !proDetailsSid.isEmpty()) {
                String freq = projSelDTO.getFrequency();
                String order = projSelDTO.getProjectionOrder();
                String userId = String.valueOf(projSelDTO.getUserId());
                String sessionId = String.valueOf(projSelDTO.getSessionId());
                int user = Integer.parseInt(userId);
                int session = Integer.parseInt(sessionId);
                String discountString = CommonUtils.collectionToString(discountList, true);
                projSelDTO.setCcpCount(proDetailsSid.size());
                if (discountString.equals(StringUtils.EMPTY)) {
                    discountString = ZERO;
                }
                List list = NmDiscountProjMasterLocalServiceUtil.getAllPesriodDiscount(proDetailsSid, freq, discountString, StringUtils.EMPTY, StringUtils.EMPTY, order, startAndEndPeriods, user, session);
                if (list != null && !list.isEmpty()) {
                    if (QUARTERLY.getConstant().equals(freq)) {
                        discountProjList = getDiscountListDto(projSelDTO, list, discountProjList, Q_SMALL, discountList);
                        return discountProjList;
                    }
                    if (SEMI_ANNUALLY.getConstant().equals(freq)) {
                        discountProjList = getDiscountListDto(projSelDTO, list, discountProjList, Constant.S_SMALL, discountList);
                        return discountProjList;
                    }
                    if (ANNUALLY.getConstant().equals(freq)) {
                        discountProjList = getDiscountListYearDto(projSelDTO, list, discountProjList, discountList);
                        return discountProjList;
                    }

                    if (MONTHLY.getConstant().equals(freq)) {
                        discountProjList = getDiscountListMonthDto(projSelDTO, list, discountProjList, discountList);
                        return discountProjList;
                    }
                }
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
            ProjectionSelectionDTO projSelDTO, List<Integer> startAndEndPeriods) throws SystemException, PortalException {
        List<DiscountProjectionResultsDTO> discountProjList = new ArrayList<DiscountProjectionResultsDTO>();
        List<Object> view = new ArrayList<Object>();
        // Used to Load Discount
        List<Integer> proDetailsSid = new ArrayList<Integer>();
        String selectedView = String.valueOf(projSelDTO.getView());
        List ccpId = null;
        if (CUSTOMER.getConstant().equals(selectedView)) {
            ccpId = NmDiscountProjMasterLocalServiceUtil.getCCPDetailsID(projectionId, PERCENTAGE, Constant.STRING_ONE);
        }
        if (PRODUCT.getConstant().equals(selectedView)) {
            ccpId = NmDiscountProjMasterLocalServiceUtil.getCCPDetailsIDForProductHierarchy(projectionId, PERCENTAGE, Constant.STRING_ONE);
        }
        if (CUSTOM.getConstant().equals(selectedView)) {
            ccpId = NmDiscountProjMasterLocalServiceUtil.getCCPDetailsID(projectionId, PERCENTAGE, Constant.STRING_ONE);
        }
        if (ccpId != null && !ccpId.isEmpty()) {
            final DynamicQuery projectiondetailsDynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionDetails.class);
            projectiondetailsDynamicQuery.add(RestrictionsFactoryUtil.eq(PROJECTION_MASTER_SID, projectionId));
            projectiondetailsDynamicQuery.add(RestrictionsFactoryUtil.in(CCP_DETAILS_SID, ccpId));
            final ProjectionList projList = ProjectionFactoryUtil.projectionList();
            projList.add(ProjectionFactoryUtil.property(PROJECTION_DETAILS_SID));
            projectiondetailsDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projList));
            proDetailsSid = ProjectionDetailsLocalServiceUtil.dynamicQuery(projectiondetailsDynamicQuery);
            if (proDetailsSid != null && !proDetailsSid.isEmpty()) {
                String freq = String.valueOf(projSelDTO.getFrequency());
                String userId = String.valueOf(projSelDTO.getUserId());
                String sessionId = String.valueOf(projSelDTO.getSessionId());
                int user = Integer.parseInt(userId);
                int session = Integer.parseInt(sessionId);
                List<String> discountList = new ArrayList<String>();
                discountList = projSelDTO.getDiscountNameList();
                String discountString = getDiscountName(discountList);
                projSelDTO.setCcpCount(proDetailsSid.size());
                view.add(projSelDTO.getProjectionOrder());
                List list = NmDiscountProjMasterLocalServiceUtil.getTotalDiscountNumber(proDetailsSid, freq, discountString, startAndEndPeriods, user, session,view);
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
                    double actualSales = 0;
                    double actualAmount = 0;
                    double projectedSales = 0;
                    double projectedAmount = 0;
                    double projectedRate = 0;
                    double actualRPU = 0;
                    double projectedRPU = 0;
                    String commonColumn = StringUtils.EMPTY;
                    Object[] object = (Object[]) list.get(0);
                    String currentDiscount = String.valueOf(object[8]);
                    commonColumn = currentDiscount.replace(" ", StringUtils.EMPTY);
                    if (object[2] != null) {
                        Double aSales = Double.parseDouble(String.valueOf(object[2]));
                        actualSales = actualSales + aSales;
                    }
                    if (object[3] != null) {
                        Double aAmount = Double.parseDouble(String.valueOf(object[3]));
                        actualAmount = actualAmount + aAmount;
                    }
                    if (object[4] != null) {
                        Double pSales = Double.parseDouble(String.valueOf(object[4]));
                        projectedSales = projectedSales + pSales;
                    }
                    if (object[5] != null) {
                        Double pAmount = Double.parseDouble(String.valueOf(object[5]));
                        projectedAmount = projectedAmount + pAmount;
                    }
                    if (object[9] != null) {
                        Double prate = Double.parseDouble(String.valueOf(object[9]));
                        projectedRate = projectedRate + prate;
                    }
                    if (object[10] != null) {
                        Double acRPU = Double.parseDouble(String.valueOf(object[10]));
                        actualRPU = actualRPU + acRPU;
                    }
                    if (object[11] != null) {
                        Double prRPU = Double.parseDouble(String.valueOf(object[11]));
                        projectedRPU = projectedRPU + prRPU;
                    }
                    if (list.size() == 1) {
                        Double arate = actualAmount / actualSales;
                        if (arate.isNaN() || arate.isInfinite()) {
                            arate = 0.0;
                        }
                        arate = arate * 100;
                        String actRate = String.valueOf(arate);
                        discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                        Double actualAmt = actualAmount;
//                        actualAmt = actualAmt / 100;
                        if (actualAmt.isNaN() || actualAmt.isInfinite()) {
                            actualAmt = 0.0;
                        }
                        String actAmt = String.valueOf(actualAmt);
                        discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                        Double prate = projectedAmount / projectedSales;

                        if (prate.isNaN() || prate.isInfinite()) {
                            prate = 0.0;
                        }
                        prate = prate * 100;

                        String proRate = String.valueOf(prate);
                        discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                        Double projectedAmtAmt = projectedAmount;
//                        projectedAmtAmt = projectedAmtAmt / 100;
                        if (projectedAmtAmt.isNaN() || projectedAmtAmt.isInfinite()) {
                            projectedAmtAmt = 0.0;
                        }
                        String proAmount = String.valueOf(projectedAmtAmt);
                        discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                        Double aRPU = actualAmount / actualRPU;
                        if (aRPU.isNaN() || aRPU.isInfinite()) {
                            aRPU = 0.0;
                        }
                        String actRPU = String.valueOf(aRPU);
                        discountDto.addStringProperties(commonColumn + ACTUALRPU, actRPU != null && !NULL.equals(String.valueOf(actRPU)) && !StringUtils.EMPTY.equals(String.valueOf(actRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(actRPU)))) : HYPHEN);
                        Double pRPU = projectedAmount / projectedRPU;
                        if (pRPU.isNaN() || pRPU.isInfinite()) {
                            pRPU = 0.0;
                        }
                        String proRPU = String.valueOf(pRPU);
                        discountDto.addStringProperties(commonColumn + PROJECTEDRPU, proRPU != null && !NULL.equals(String.valueOf(proRPU)) && !StringUtils.EMPTY.equals(String.valueOf(proRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(proRPU)))) : HYPHEN);
                    } else {

                        for (int i = 1; i < list.size(); i++) {
                            Object[] obj = (Object[]) list.get(i);
                            String selectedDiscount = String.valueOf(obj[8]);
                            if (currentDiscount.equals(selectedDiscount)) {
                                if (obj[2] != null) {
                                    Double aSales = Double.parseDouble(String.valueOf(obj[2]));
                                    actualSales = actualSales + aSales;
                                }
                                if (obj[3] != null) {
                                    Double aAmount = Double.parseDouble(String.valueOf(obj[3]));
                                    actualAmount = actualAmount + aAmount;
                                }
                                if (obj[4] != null) {
                                    Double pSales = Double.parseDouble(String.valueOf(obj[4]));
                                    projectedSales = projectedSales + pSales;
                                }
                                if (obj[5] != null) {
                                    Double pAmount = Double.parseDouble(String.valueOf(obj[5]));
                                    projectedAmount = projectedAmount + pAmount;
                                }
                                if (obj[9] != null) {
                                    Double prate = Double.parseDouble(String.valueOf(obj[9]));
                                    projectedRate = projectedRate + prate;
                                }
                                if (obj[10] != null) {
                                    Double acRPU = Double.parseDouble(String.valueOf(obj[10]));
                                    actualRPU = actualRPU + acRPU;
                                }
                                if (obj[11] != null) {
                                    Double prRPU = Double.parseDouble(String.valueOf(obj[11]));
                                    projectedRPU = projectedRPU + prRPU;
                                }
                            } else {
                                Double arate = actualAmount / actualSales;
                                if (arate.isNaN() || arate.isInfinite()) {
                                    arate = 0.0;
                                }
                                arate = arate * 100;
                                String actRate = String.valueOf(arate);

                                discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                Double actualAmt = actualAmount;
//                                actualAmt = actualAmt / 100;
                                if (actualAmt.isNaN() || actualAmt.isInfinite()) {
                                    actualAmt = 0.0;
                                }
                                String actAmt = String.valueOf(actualAmt);
                                discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                                Double prate = projectedAmount / projectedSales;
                                if (prate.isNaN() || prate.isInfinite()) {
                                    prate = 0.0;
                                }
                                prate = prate * 100;

                                String proRate = String.valueOf(prate);
                                discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                Double projectedAmtAmt = projectedAmount;
//                                projectedAmtAmt = projectedAmtAmt / 100;
                                if (projectedAmtAmt.isNaN() || projectedAmtAmt.isInfinite()) {
                                    projectedAmtAmt = 0.0;
                                }
                                String proAmount = String.valueOf(projectedAmtAmt);
                                discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                Double aRPU = actualAmount / actualRPU;
                                if (aRPU.isNaN() || aRPU.isInfinite()) {
                                    aRPU = 0.0;
                                }
                                String actRPU = String.valueOf(aRPU);
                                discountDto.addStringProperties(commonColumn + ACTUALRPU, actRPU != null && !NULL.equals(String.valueOf(actRPU)) && !StringUtils.EMPTY.equals(String.valueOf(actRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(actRPU)))) : HYPHEN);
                                Double pRPU = projectedAmount / projectedRPU;
                                if (pRPU.isNaN() || pRPU.isInfinite()) {
                                    pRPU = 0.0;
                                }
                                String proRPU = String.valueOf(pRPU);
                                discountDto.addStringProperties(commonColumn + PROJECTEDRPU, proRPU != null && !NULL.equals(String.valueOf(proRPU)) && !StringUtils.EMPTY.equals(String.valueOf(proRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(proRPU)))) : HYPHEN);
                                commonColumn = StringUtils.EMPTY;
                                actualSales = 0;
                                actualAmount = 0;
                                projectedSales = 0;
                                projectedAmount = 0;
                                projectedRate = 0;
                                actualRPU = 0;
                                projectedRPU = 0;
                                currentDiscount = selectedDiscount;
                                commonColumn = currentDiscount.replace(" ", StringUtils.EMPTY);
                                if (obj[2] != null) {
                                    Double aSales = Double.parseDouble(String.valueOf(obj[2]));
                                    actualSales = actualSales + aSales;
                                }
                                if (obj[3] != null) {
                                    Double aAmount = Double.parseDouble(String.valueOf(obj[3]));
                                    actualAmount = actualAmount + aAmount;
                                }

                                if (obj[4] != null) {
                                    Double pSales = Double.parseDouble(String.valueOf(obj[4]));
                                    projectedSales = projectedSales + pSales;
                                }
                                if (obj[5] != null) {
                                    Double pAmount = Double.parseDouble(String.valueOf(obj[5]));
                                    projectedAmount = projectedAmount + pAmount;
                                }
                                if (obj[9] != null) {
                                    Double projRate = Double.parseDouble(String.valueOf(obj[9]));
                                    projectedRate = projectedRate + projRate;
                                }
                                if (obj[10] != null) {
                                    Double acRPU = Double.parseDouble(String.valueOf(obj[10]));
                                    actualRPU = actualRPU + acRPU;
                                }
                                if (obj[11] != null) {
                                    Double prRPU = Double.parseDouble(String.valueOf(obj[11]));
                                    projectedRPU = projectedRPU + prRPU;
                                }
                            }
                            if (i == list.size() - 1) {
                                Double arate = actualAmount / actualSales;
                                if (arate.isNaN() || arate.isInfinite()) {
                                    arate = 0.0;
                                }
                                arate = arate * 100;
                                String actRate = String.valueOf(arate);
                                discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                Double actualAmt = actualAmount;
//                                actualAmt = actualAmt / 100;
                                if (actualAmt.isNaN() || actualAmt.isInfinite()) {
                                    actualAmt = 0.0;
                                }
                                String actAmt = String.valueOf(actualAmt);
                                discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                                Double prate = projectedAmount / projectedSales;
                                if (prate.isNaN() || prate.isInfinite()) {
                                    prate = 0.0;
                                }
                                prate = prate * 100;

                                String proRate = String.valueOf(prate);
                                discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                Double projectedAmtAmt = projectedAmount;
//                                projectedAmtAmt = projectedAmtAmt / 100;
                                if (projectedAmtAmt.isNaN() || projectedAmtAmt.isInfinite()) {
                                    projectedAmtAmt = 0.0;
                                }
                                String proAmount = String.valueOf(projectedAmtAmt);
                                discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                Double aRPU = actualAmount / actualRPU;
                                if (aRPU.isNaN() || aRPU.isInfinite()) {
                                    aRPU = 0.0;
                                }
                                String actRPU = String.valueOf(aRPU);
                                discountDto.addStringProperties(commonColumn + ACTUALRPU, actRPU != null && !NULL.equals(String.valueOf(actRPU)) && !StringUtils.EMPTY.equals(String.valueOf(actRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(actRPU)))) : HYPHEN);
                                Double pRPU = projectedAmount / projectedRPU;
                                if (pRPU.isNaN() || pRPU.isInfinite()) {
                                    pRPU = 0.0;
                                }
                                String proRPU = String.valueOf(pRPU);
                                discountDto.addStringProperties(commonColumn + PROJECTEDRPU, proRPU != null && !NULL.equals(String.valueOf(proRPU)) && !StringUtils.EMPTY.equals(String.valueOf(proRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(proRPU)))) : HYPHEN);
                            }
                        }
                    }
                    discountProjList.add(discountDto);
                }
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
    public List<DiscountProjectionResultsDTO> getPivotProjectionTotalDiscount(ProjectionSelectionDTO projSelDTO, List<Integer> startAndEndPeriods) throws SystemException, PortalException {
        List<DiscountProjectionResultsDTO> discountProjList = new ArrayList<DiscountProjectionResultsDTO>();
        int projectionId = projSelDTO.getProjectionId();
        List ccpId = NmDiscountProjMasterLocalServiceUtil.getCCPDetailsID(projectionId, PERCENTAGE, Constant.STRING_ONE);
        if (ccpId != null && !ccpId.isEmpty()) {
            List<Integer> proDetailsSid = new ArrayList<Integer>();
            final DynamicQuery projectiondetailsDynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionDetails.class);
            projectiondetailsDynamicQuery.add(RestrictionsFactoryUtil.eq(PROJECTION_MASTER_SID, projectionId));
            projectiondetailsDynamicQuery.add(RestrictionsFactoryUtil.in(CCP_DETAILS_SID, ccpId));
            final ProjectionList projList = ProjectionFactoryUtil.projectionList();
            projList.add(ProjectionFactoryUtil.property(PROJECTION_DETAILS_SID));
            projectiondetailsDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projList));
            proDetailsSid = ProjectionDetailsLocalServiceUtil.dynamicQuery(projectiondetailsDynamicQuery);
            if (proDetailsSid != null && !proDetailsSid.isEmpty()) {
                String userId = String.valueOf(projSelDTO.getUserId());
                String sessionId = String.valueOf(projSelDTO.getSessionId());
                int user = Integer.parseInt(userId);
                int session = Integer.parseInt(sessionId);
                String freq = String.valueOf(projSelDTO.getFrequency());
                List<String> discountList = new ArrayList<String>();
                discountList = projSelDTO.getDiscountNameList();
                String discountString = getDiscountName(discountList);
                projSelDTO.setCcpCount(proDetailsSid.size());
                List list = NmDiscountProjMasterLocalServiceUtil.getSubDiscount(proDetailsSid, freq, discountString, startAndEndPeriods, user, session);
                if (list != null && !list.isEmpty()) {
                    if (ANNUALLY.getConstant().equals(freq)) {
                        double actualSales = 0;
                        double actualAmount = 0;
                        double projectedSales = 0;
                        double projectedAmount = 0;
                        double projectedRate = 0;
                        double actualRPU = 0;
                        double projectedRPU = 0;
                        int frequencyDivision = 12;
                        String commonColumn = StringUtils.EMPTY;
                        List<String> periodList = new ArrayList<String>(projSelDTO.getPeriodList());
                        DiscountProjectionResultsDTO discountDto = new DiscountProjectionResultsDTO();
                        Object[] object = (Object[]) list.get(0);
                        String currentDiscount = String.valueOf(object[8]);
                        int currentYear = (Integer) object[0];
                        commonColumn = currentDiscount.replace(" ", StringUtils.EMPTY);
                        discountDto.setGroup(String.valueOf(currentYear));
                        discountDto.setIsParent(ZERO);
                        if (object[2] != null) {
                            Double aSales = Double.parseDouble(String.valueOf(object[2]));
                            actualSales = actualSales + aSales;
                        }
                        if (object[3] != null) {
                            Double aAmount = Double.parseDouble(String.valueOf(object[3]));
                            actualAmount = actualAmount + aAmount;
                        }
                        if (object[4] != null) {
                            Double pSales = Double.parseDouble(String.valueOf(object[4]));
                            projectedSales = projectedSales + pSales;
                        }
                        if (object[5] != null) {
                            Double pAmount = Double.parseDouble(String.valueOf(object[5]));
                            projectedAmount = projectedAmount + pAmount;
                        }
                        if (object[9] != null) {
                            Double projRate = Double.parseDouble(String.valueOf(object[9]));
                            projectedRate = projectedRate + projRate;
                        }
                        if (object[10] != null) {
                            Double acRPU = Double.parseDouble(String.valueOf(object[10]));
                            actualRPU = actualRPU + acRPU;
                        }
                        if (object[11] != null) {
                            Double prRPU = Double.parseDouble(String.valueOf(object[11]));
                            projectedRPU = projectedRPU + prRPU;
                        }
                        if (list.size() == 1) {
                            Double arate = actualAmount / actualSales;
                            if (arate.isNaN() || arate.isInfinite()) {
                                arate = 0.0;
                            }
                            arate = arate * 100;
                            String actRate = String.valueOf(arate);

                            discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                            Double actualAmt = actualAmount;
//                            actualAmt = actualAmt / 100;
                            if (actualAmt.isNaN() || actualAmt.isInfinite()) {
                                actualAmt = 0.0;
                            }
                            String actAmt = String.valueOf(actualAmt);
                            discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                            Double prate = projectedAmount / projectedSales;

                            if (prate.isNaN() || prate.isInfinite()) {
                                prate = 0.0;
                            }
                            prate = prate * 100;

                            String proRate = String.valueOf(prate);
                            discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                            Double projectedAmtAmt = projectedAmount;
//                            projectedAmtAmt = projectedAmtAmt / 100;
                            if (projectedAmtAmt.isNaN() || projectedAmtAmt.isInfinite()) {
                                projectedAmtAmt = 0.0;
                            }
                            String proAmount = String.valueOf(projectedAmtAmt);
                            discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                            Double aRPU = actualAmount / actualRPU;
                            if (aRPU.isNaN() || aRPU.isInfinite()) {
                                aRPU = 0.0;
                            }
                            String actRPU = String.valueOf(aRPU);
                            discountDto.addStringProperties(commonColumn + ACTUALRPU, actRPU != null && !NULL.equals(String.valueOf(actRPU)) && !StringUtils.EMPTY.equals(String.valueOf(actRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(actRPU)))) : HYPHEN);
                            Double pRPU = projectedAmount / projectedRPU;
                            if (pRPU.isNaN() || pRPU.isInfinite()) {
                                pRPU = 0.0;
                            }
                            String proRPU = String.valueOf(pRPU);
                            discountDto.addStringProperties(commonColumn + PROJECTEDRPU, proRPU != null && !NULL.equals(String.valueOf(proRPU)) && !StringUtils.EMPTY.equals(String.valueOf(proRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(proRPU)))) : HYPHEN);
                            commonColumn = StringUtils.EMPTY;
                            discountProjList.add(discountDto);
                        } else {
                            for (int i = 1; i < list.size(); i++) {
                                Object[] obj = (Object[]) list.get(i);
                                String selectedDiscount = String.valueOf(obj[8]);
                                selectedDiscount = selectedDiscount.replace(" ", StringUtils.EMPTY);
                                int selectedYear = (Integer) obj[0];
                                if (currentYear == selectedYear) {
                                    if (currentDiscount.equalsIgnoreCase(selectedDiscount)) {
                                        if (obj[2] != null) {
                                            Double aSales = Double.parseDouble(String.valueOf(obj[2]));
                                            actualSales = actualSales + aSales;
                                        }
                                        if (obj[3] != null) {
                                            Double aAmount = Double.parseDouble(String.valueOf(obj[3]));
                                            actualAmount = actualAmount + aAmount;
                                        }
                                        if (obj[4] != null) {
                                            Double pSales = Double.parseDouble(String.valueOf(obj[4]));
                                            projectedSales = projectedSales + pSales;
                                        }
                                        if (obj[5] != null) {
                                            Double pAmount = Double.parseDouble(String.valueOf(obj[5]));
                                            projectedAmount = projectedAmount + pAmount;
                                        }
                                        if (obj[9] != null) {
                                            Double projRate = Double.parseDouble(String.valueOf(obj[9]));
                                            projectedRate = projectedRate + projRate;
                                        }
                                        if (obj[10] != null) {
                                            Double acRPU = Double.parseDouble(String.valueOf(obj[10]));
                                            actualRPU = actualRPU + acRPU;
                                        }
                                        if (obj[11] != null) {
                                            Double prRPU = Double.parseDouble(String.valueOf(obj[11]));
                                            projectedRPU = projectedRPU + prRPU;
                                        }
                                    } else {
                                        Double arate = actualAmount / actualSales;
                                        if (arate.isNaN() || arate.isInfinite()) {
                                            arate = 0.0;
                                        }
                                        arate = arate * 100;
                                        String actRate = String.valueOf(arate);
                                        discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double actualAmt = actualAmount;
//                                        actualAmt = actualAmt / 100;
                                        if (actualAmt.isNaN() || actualAmt.isInfinite()) {
                                            actualAmt = 0.0;
                                        }
                                        String actAmt = String.valueOf(actualAmt);
                                        discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                                        Double prate = projectedAmount / projectedSales;
                                        if (prate.isNaN() || prate.isInfinite()) {
                                            prate = 0.0;
                                        }
                                        prate = prate * 100;

                                        String proRate = String.valueOf(prate);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double projectedAmtAmt = projectedAmount;
//                                        projectedAmtAmt = projectedAmtAmt / 100;
                                        if (projectedAmtAmt.isNaN() || projectedAmtAmt.isInfinite()) {
                                            projectedAmtAmt = 0.0;
                                        }
                                        String proAmount = String.valueOf(projectedAmtAmt);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                        Double aRPU = actualAmount / actualRPU;
                                        if (aRPU.isNaN()) {
                                            aRPU = 0.0;
                                        }
                                        String actRPU = String.valueOf(aRPU);
                                        discountDto.addStringProperties(commonColumn + ACTUALRPU, actRPU != null && !NULL.equals(String.valueOf(actRPU)) && !StringUtils.EMPTY.equals(String.valueOf(actRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(actRPU)))) : HYPHEN);
                                        Double pRPU = projectedAmount / projectedRPU;
                                        if (pRPU.isNaN() || pRPU.isInfinite()) {
                                            pRPU = 0.0;
                                        }
                                        String proRPU = String.valueOf(pRPU);
                                        discountDto.addStringProperties(commonColumn + PROJECTEDRPU, proRPU != null && !NULL.equals(String.valueOf(proRPU)) && !StringUtils.EMPTY.equals(String.valueOf(proRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(proRPU)))) : HYPHEN);
                                        commonColumn = StringUtils.EMPTY;
                                        actualSales = 0;
                                        actualAmount = 0;
                                        projectedSales = 0;
                                        projectedAmount = 0;
                                        projectedRate = 0;
                                        actualRPU = 0;
                                        projectedRPU = 0;
                                        currentDiscount = selectedDiscount;
                                        commonColumn = selectedDiscount.replace(" ", StringUtils.EMPTY);
                                        if (obj[2] != null) {
                                            Double aSales = Double.parseDouble(String.valueOf(obj[2]));
                                            actualSales = actualSales + aSales;
                                        }
                                        if (obj[3] != null) {
                                            Double aAmount = Double.parseDouble(String.valueOf(obj[3]));
                                            actualAmount = actualAmount + aAmount;
                                        }
                                        if (obj[4] != null) {
                                            Double pSales = Double.parseDouble(String.valueOf(obj[4]));
                                            projectedSales = projectedSales + pSales;
                                        }
                                        if (obj[5] != null) {
                                            Double pAmount = Double.parseDouble(String.valueOf(obj[5]));
                                            projectedAmount = projectedAmount + pAmount;
                                        }
                                        if (obj[9] != null) {
                                            Double projRate = Double.parseDouble(String.valueOf(obj[9]));
                                            projectedRate = projectedRate + projRate;
                                        }
                                        if (obj[10] != null) {
                                            Double acRPU = Double.parseDouble(String.valueOf(obj[10]));
                                            actualRPU = actualRPU + acRPU;
                                        }
                                        if (obj[11] != null) {
                                            Double prRPU = Double.parseDouble(String.valueOf(obj[11]));
                                            projectedRPU = projectedRPU + prRPU;
                                        }
                                    }
                                } else {
                                    if (periodList.contains(discountDto.getGroup())) {
                                        Double arate = actualAmount / actualSales;
                                        if (arate.isNaN() || arate.isInfinite()) {
                                            arate = 0.0;
                                        }
                                        arate = arate * 100;
                                        String actRate = String.valueOf(arate);
                                        discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double actualAmt = actualAmount;
//                                        actualAmt = actualAmt / 100;
                                        if (actualAmt.isNaN() || actualAmt.isInfinite()) {
                                            actualAmt = 0.0;
                                        }
                                        String actAmt = String.valueOf(actualAmt);
                                        discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);

                                        Double prate = projectedAmount / projectedSales;

                                        if (prate.isNaN() || prate.isInfinite()) {
                                            prate = 0.0;
                                        }
                                        prate = prate * 100;

                                        String proRate = String.valueOf(prate);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double projectedAmtAmt = projectedAmount;
//                                        projectedAmtAmt = projectedAmtAmt / 100;
                                        if (projectedAmtAmt.isNaN() || projectedAmtAmt.isInfinite()) {
                                            projectedAmtAmt = 0.0;
                                        }
                                        String proAmount = String.valueOf(projectedAmtAmt);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                        Double aRPU = actualAmount / actualRPU;
                                        if (aRPU.isNaN() || aRPU.isInfinite()) {
                                            aRPU = 0.0;
                                        }
                                        String actRPU = String.valueOf(aRPU);
                                        discountDto.addStringProperties(commonColumn + ACTUALRPU, actRPU != null && !NULL.equals(String.valueOf(actRPU)) && !StringUtils.EMPTY.equals(String.valueOf(actRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(actRPU)))) : HYPHEN);
                                        Double pRPU = projectedAmount / projectedRPU;
                                        if (pRPU.isNaN() || pRPU.isInfinite()) {
                                            pRPU = 0.0;
                                        }
                                        String proRPU = String.valueOf(pRPU);
                                        discountDto.addStringProperties(commonColumn + PROJECTEDRPU, proRPU != null && !NULL.equals(String.valueOf(proRPU)) && !StringUtils.EMPTY.equals(String.valueOf(proRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(proRPU)))) : HYPHEN);
                                        discountProjList.add(discountDto);
                                        periodList.remove(discountDto.getGroup());
                                    }
                                    actualSales = 0;
                                    actualAmount = 0;
                                    projectedSales = 0;
                                    projectedAmount = 0;
                                    projectedRate = 0;
                                    actualRPU = 0;
                                    projectedRPU = 0;
                                    discountDto = new DiscountProjectionResultsDTO();
                                    currentDiscount = selectedDiscount;
                                    discountDto.setGroup(String.valueOf(selectedYear));
                                    currentYear = selectedYear;
                                    commonColumn = selectedDiscount.replace(" ", StringUtils.EMPTY);
                                    discountDto.setIsParent(ZERO);
                                    if (obj[2] != null) {
                                        Double aSales = Double.parseDouble(String.valueOf(obj[2]));
                                        actualSales = actualSales + aSales;
                                    }
                                    if (obj[3] != null) {
                                        Double aAmount = Double.parseDouble(String.valueOf(obj[3]));
                                        actualAmount = actualAmount + aAmount;
                                    }
                                    if (obj[4] != null) {
                                        Double pSales = Double.parseDouble(String.valueOf(obj[4]));
                                        projectedSales = projectedSales + pSales;
                                    }
                                    if (obj[5] != null) {
                                        Double pAmount = Double.parseDouble(String.valueOf(obj[5]));
                                        projectedAmount = projectedAmount + pAmount;
                                    }
                                    if (obj[9] != null) {
                                        Double projRate = Double.parseDouble(String.valueOf(obj[9]));
                                        projectedRate = projectedRate + projRate;
                                    }
                                    if (obj[10] != null) {
                                        Double acRPU = Double.parseDouble(String.valueOf(obj[10]));
                                        actualRPU = actualRPU + acRPU;
                                    }
                                    if (obj[11] != null) {
                                        Double prRPU = Double.parseDouble(String.valueOf(obj[11]));
                                        projectedRPU = projectedRPU + prRPU;
                                    }
                                }
                                if ((i == list.size() - 1) && periodList.contains(discountDto.getGroup())) {
                                    Double arate = actualAmount / actualSales;
                                    if (arate.isNaN() || arate.isInfinite()) {
                                        arate = 0.0;
                                    }
                                    arate = arate * 100;
                                    String actRate = String.valueOf(arate);
                                    discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                    Double actualAmt = actualAmount;
//                                    actualAmt = actualAmt / 100;
                                    if (actualAmt.isNaN() || actualAmt.isInfinite()) {
                                        actualAmt = 0.0;
                                    }
                                    String actAmt = String.valueOf(actualAmt);
                                    discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                                    Double prate = projectedAmount / projectedSales;

                                    if (prate.isNaN() || prate.isInfinite()) {
                                        prate = 0.0;
                                    }
                                    prate = prate * 100;

                                    String proRate = String.valueOf(prate);
                                    discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                    Double projectedAmtAmt = projectedAmount;
//                                    projectedAmtAmt = projectedAmtAmt / 100;
                                    if (projectedAmtAmt.isNaN() || projectedAmtAmt.isInfinite()) {
                                        projectedAmtAmt = 0.0;
                                    }
                                    String proAmount = String.valueOf(projectedAmtAmt);
                                    discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                    Double aRPU = actualAmount / actualRPU;
                                    if (aRPU.isNaN() || aRPU.isInfinite()) {
                                        aRPU = 0.0;
                                    }
                                    String actRPU = String.valueOf(aRPU);
                                    discountDto.addStringProperties(commonColumn + ACTUALRPU, actRPU != null && !NULL.equals(String.valueOf(actRPU)) && !StringUtils.EMPTY.equals(String.valueOf(actRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(actRPU)))) : HYPHEN);
                                    Double pRPU = projectedAmount / projectedRPU;
                                    if (pRPU.isNaN()) {
                                        pRPU = 0.0;
                                    }
                                    String proRPU = String.valueOf(pRPU);
                                    discountDto.addStringProperties(commonColumn + PROJECTEDRPU, proRPU != null && !NULL.equals(String.valueOf(proRPU)) && !StringUtils.EMPTY.equals(String.valueOf(proRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(proRPU)))) : HYPHEN);
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
                                    projDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, NULL));

                                    String discountActualAmount = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns1 = discountActualAmount + ACTUALSAMOUNT;
                                    projDTO.addStringProperties(columns1, getFormattedValue(CUR_ZERO, NULL));

                                    String discountProjectionAmount = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns2 = discountProjectionAmount + PROJECTIONSAMOUNT;
                                    projDTO.addStringProperties(columns2, getFormattedValue(CUR_ZERO, NULL));

                                    String discountProjectionRate = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns3 = discountProjectionRate + PROJECTIONSRATE;
                                    projDTO.addStringProperties(columns3, getFormattedValue(CUR_ZERO, NULL));

                                    String discountActualRPU = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns4 = discountActualRPU + ACTUALRPU;
                                    projDTO.addStringProperties(columns4, getFormattedValue(CUR_ZERO, NULL));

                                    String discountProjectionRPU = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns5 = discountProjectionRPU + PROJECTEDRPU;
                                    projDTO.addStringProperties(columns5, getFormattedValue(CUR_ZERO, NULL));
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
                        double projectedRate = 0;
                        double actualRPU = 0;
                        double projectedRPU = 0;
                        int frequencyDivision = 3;
                        String commonColumn = StringUtils.EMPTY;
                        List<String> periodList = new ArrayList<String>(projSelDTO.getPeriodList());
                        DiscountProjectionResultsDTO discountDto = new DiscountProjectionResultsDTO();
                        Object[] object = (Object[]) list.get(0);
                        String currentDiscount = String.valueOf(object[8]);
                        int currentYear = (Integer) object[0];
                        int currentQuarter = (Integer) object[6];
                        currentDiscount = currentDiscount.replace(" ", StringUtils.EMPTY);
                        commonColumn = currentDiscount;
                        discountDto.setGroup(Q_BIG + object[1] + " " + object[0]);
                        discountDto.setIsParent(ZERO);
                        if (object[2] != null) {
                            Double aSales = Double.parseDouble(String.valueOf(object[2]));
                            actualSales = actualSales + aSales;
                        }
                        if (object[3] != null) {
                            Double aAmount = Double.parseDouble(String.valueOf(object[3]));
                            actualAmount = actualAmount + aAmount;
                        }
                        if (object[4] != null) {
                            Double pSales = Double.parseDouble(String.valueOf(object[4]));
                            projectedSales = projectedSales + pSales;
                        }
                        if (object[5] != null) {
                            Double pAmount = Double.parseDouble(String.valueOf(object[5]));
                            projectedAmount = projectedAmount + pAmount;
                        }
                        if (object[9] != null) {
                            Double projRate = Double.parseDouble(String.valueOf(object[9]));
                            projectedRate = projectedRate + projRate;
                        }
                        if (object[10] != null) {
                            Double acRPU = Double.parseDouble(String.valueOf(object[10]));
                            actualRPU = actualRPU + acRPU;
                        }
                        if (object[11] != null) {
                            Double prRPU = Double.parseDouble(String.valueOf(object[11]));
                            projectedRPU = projectedRPU + prRPU;
                        }

                        if (list.size() == 1) {
                            Double arate = actualAmount / actualSales;
                            if (arate.isNaN()) {
                                arate = 0.0;
                            }
                            arate = arate * 100;
                            String actRate = String.valueOf(arate);
                            discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                            Double actualAmt = actualAmount;
//                            actualAmt = actualAmt / 100;
                            if (actualAmt.isNaN() || actualAmt.isInfinite()) {
                                actualAmt = 0.0;
                            }
                            String actAmt = String.valueOf(actualAmt);
                            discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                            Double prate = projectedAmount / projectedSales;

                            if (prate.isNaN() || prate.isInfinite()) {
                                prate = 0.0;
                            }

                            prate = prate * 100;
                            String proRate = String.valueOf(prate);
                            discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                            Double projectedAmtAmt = projectedAmount;
//                            projectedAmtAmt = projectedAmtAmt / 100;
                            if (projectedAmtAmt.isNaN() || projectedAmtAmt.isInfinite()) {
                                projectedAmtAmt = 0.0;
                            }
                            String proAmount = String.valueOf(projectedAmtAmt);
                            discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                            Double aRPU = actualAmount / actualRPU;
                            if (aRPU.isNaN() || aRPU.isInfinite()) {
                                aRPU = 0.0;
                            }
                            String actRPU = String.valueOf(aRPU);
                            discountDto.addStringProperties(commonColumn + ACTUALRPU, actRPU != null && !NULL.equals(String.valueOf(actRPU)) && !StringUtils.EMPTY.equals(String.valueOf(actRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(actRPU)))) : HYPHEN);
                            Double pRPU = projectedAmount / projectedRPU;
                            if (pRPU.isNaN()) {
                                pRPU = 0.0;
                            }
                            String proRPU = String.valueOf(pRPU);
                            discountDto.addStringProperties(commonColumn + PROJECTEDRPU, proRPU != null && !NULL.equals(String.valueOf(proRPU)) && !StringUtils.EMPTY.equals(String.valueOf(proRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(proRPU)))) : HYPHEN);
                            commonColumn = StringUtils.EMPTY;
                            discountProjList.add(discountDto);
                        } else {
                            for (int i = 1; i < list.size(); i++) {
                                Object[] obj = (Object[]) list.get(i);
                                String selectedDiscount = String.valueOf(obj[8]);
                                selectedDiscount = selectedDiscount.replace(" ", StringUtils.EMPTY);
                                int selectedYear = (Integer) obj[0];
                                int selectedQuarter = (Integer) obj[6];
                                if (currentYear == selectedYear && currentQuarter == selectedQuarter) {
                                    if (currentDiscount.equalsIgnoreCase(selectedDiscount)) {
                                        if (obj[2] != null) {
                                            Double aSales = Double.parseDouble(String.valueOf(obj[2]));
                                            actualSales = actualSales + aSales;
                                        }
                                        if (obj[3] != null) {
                                            Double aAmount = Double.parseDouble(String.valueOf(obj[3]));
                                            actualAmount = actualAmount + aAmount;
                                        }
                                        if (obj[4] != null) {
                                            Double pSales = Double.parseDouble(String.valueOf(obj[4]));
                                            projectedSales = projectedSales + pSales;
                                        }
                                        if (obj[5] != null) {
                                            Double pAmount = Double.parseDouble(String.valueOf(obj[5]));
                                            projectedAmount = projectedAmount + pAmount;
                                        }
                                        if (obj[9] != null) {
                                            Double projRate = Double.parseDouble(String.valueOf(obj[9]));
                                            projectedRate = projectedRate + projRate;
                                        }
                                        if (obj[10] != null) {
                                            Double acRPU = Double.parseDouble(String.valueOf(obj[10]));
                                            actualRPU = actualRPU + acRPU;
                                        }
                                        if (obj[11] != null) {
                                            Double prRPU = Double.parseDouble(String.valueOf(obj[11]));
                                            projectedRPU = projectedRPU + prRPU;
                                        }
                                    } else {
                                        Double arate = actualAmount / actualSales;
                                        if (arate.isNaN() || arate.isInfinite()) {
                                            arate = 0.0;
                                        }
                                        arate = arate * 100;
                                        String actRate = String.valueOf(arate);
                                        discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double actualAmt = actualAmount;
//                                        actualAmt = actualAmt / 100;
                                        if (actualAmt.isNaN() || actualAmt.isInfinite()) {
                                            actualAmt = 0.0;
                                        }
                                        String actAmt = String.valueOf(actualAmt);
                                        discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                                        Double prate = projectedAmount / projectedSales;

                                        if (prate.isNaN() || prate.isInfinite()) {
                                            prate = 0.0;
                                        }

                                        prate = prate * 100;
                                        String proRate = String.valueOf(prate);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double projectedAmtAmt = projectedAmount;
//                                        projectedAmtAmt = projectedAmtAmt / 100;
                                        if (projectedAmtAmt.isNaN() || projectedAmtAmt.isInfinite()) {
                                            projectedAmtAmt = 0.0;
                                        }
                                        String proAmount = String.valueOf(projectedAmtAmt);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                        Double aRPU = actualAmount / actualRPU;
                                        if (aRPU.isNaN() || aRPU.isInfinite()) {
                                            aRPU = 0.0;
                                        }
                                        String actRPU = String.valueOf(aRPU);
                                        discountDto.addStringProperties(commonColumn + ACTUALRPU, actRPU != null && !NULL.equals(String.valueOf(actRPU)) && !StringUtils.EMPTY.equals(String.valueOf(actRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(actRPU)))) : HYPHEN);
                                        Double pRPU = projectedAmount / projectedRPU;
                                        if (pRPU.isNaN() || pRPU.isInfinite()) {
                                            pRPU = 0.0;
                                        }
                                        String proRPU = String.valueOf(pRPU);
                                        discountDto.addStringProperties(commonColumn + PROJECTEDRPU, proRPU != null && !NULL.equals(String.valueOf(proRPU)) && !StringUtils.EMPTY.equals(String.valueOf(proRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(proRPU)))) : HYPHEN);
                                        commonColumn = StringUtils.EMPTY;
                                        actualSales = 0;
                                        actualAmount = 0;
                                        projectedSales = 0;
                                        projectedAmount = 0;
                                        projectedRate = 0;
                                        actualRPU = 0;
                                        projectedRPU = 0;
                                        commonColumn = selectedDiscount;
                                        currentDiscount = selectedDiscount;
                                        if (obj[2] != null) {
                                            Double aSales = Double.parseDouble(String.valueOf(obj[2]));
                                            actualSales = actualSales + aSales;
                                        }
                                        if (obj[3] != null) {
                                            Double aAmount = Double.parseDouble(String.valueOf(obj[3]));
                                            actualAmount = actualAmount + aAmount;
                                        }

                                        if (obj[4] != null) {
                                            Double pSales = Double.parseDouble(String.valueOf(obj[4]));
                                            projectedSales = projectedSales + pSales;
                                        }
                                        if (obj[5] != null) {
                                            Double pAmount = Double.parseDouble(String.valueOf(obj[5]));
                                            projectedAmount = projectedAmount + pAmount;
                                        }
                                        if (obj[9] != null) {
                                            Double projRate = Double.parseDouble(String.valueOf(obj[9]));
                                            projectedRate = projectedRate + projRate;
                                        }
                                        if (obj[10] != null) {
                                            Double acRPU = Double.parseDouble(String.valueOf(obj[10]));
                                            actualRPU = actualRPU + acRPU;
                                        }
                                        if (obj[11] != null) {
                                            Double prRPU = Double.parseDouble(String.valueOf(obj[11]));
                                            projectedRPU = projectedRPU + prRPU;
                                        }
                                    }
                                } else {
                                    String column = discountDto.getGroup().replace(" ", StringUtils.EMPTY);
                                    if (periodList.contains(column.replace(Q_BIG, Q_SMALL))) {
                                        Double arate = actualAmount / actualSales;
                                        if (arate.isNaN() || arate.isInfinite ()) {
                                            arate = 0.0;
                                        }
                                        arate = arate * 100;
                                        String actRate = String.valueOf(arate);
                                        discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double actualAmt = actualAmount;
//                                        actualAmt = actualAmt / 100;
                                        if (actualAmt.isNaN() || actualAmt.isInfinite()) {
                                            actualAmt = 0.0;
                                        }
                                        String actAmt = String.valueOf(actualAmt);
                                        discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                                        Double prate = projectedAmount / projectedSales;

                                        if (prate.isNaN() || prate.isInfinite()) {
                                            prate = 0.0;
                                        }
                                        prate = prate * 100;

                                        String proRate = String.valueOf(prate);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double projectedAmtAmt = projectedAmount;
//                                        projectedAmtAmt = projectedAmtAmt / 100;
                                        if (projectedAmtAmt.isNaN() || projectedAmtAmt.isInfinite()) {
                                            projectedAmtAmt = 0.0;
                                        }
                                        String proAmount = String.valueOf(projectedAmtAmt);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                        Double aRPU = actualAmount / actualRPU;
                                        if (aRPU.isNaN() || aRPU.isInfinite()) {
                                            aRPU = 0.0;
                                        }
                                        String actRPU = String.valueOf(aRPU);
                                        discountDto.addStringProperties(commonColumn + ACTUALRPU, actRPU != null && !NULL.equals(String.valueOf(actRPU)) && !StringUtils.EMPTY.equals(String.valueOf(actRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(actRPU)))) : HYPHEN);
                                        Double pRPU = projectedAmount / projectedRPU;
                                        if (pRPU.isNaN() || pRPU.isInfinite()) {
                                            pRPU = 0.0;
                                        }
                                        String proRPU = String.valueOf(pRPU);
                                        discountDto.addStringProperties(commonColumn + PROJECTEDRPU, proRPU != null && !NULL.equals(String.valueOf(proRPU)) && !StringUtils.EMPTY.equals(String.valueOf(proRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(proRPU)))) : HYPHEN);
                                        discountProjList.add(discountDto);
                                        periodList.remove(column.replace(Q_BIG, Q_SMALL));
                                    }
                                    actualSales = 0;
                                    actualAmount = 0;
                                    projectedSales = 0;
                                    projectedAmount = 0;
                                    projectedRate = 0;
                                    actualRPU = 0;
                                    projectedRPU = 0;
                                    discountDto = new DiscountProjectionResultsDTO();
                                    discountDto.setGroup(Q_BIG + obj[1] + " " + obj[0]);
                                    currentYear = selectedYear;
                                    currentQuarter = selectedQuarter;
                                    currentDiscount = selectedDiscount;
                                    commonColumn = selectedDiscount;
                                    discountDto.setIsParent(ZERO);
                                    if (obj[2] != null) {
                                        Double aSales = Double.parseDouble(String.valueOf(obj[2]));
                                        actualSales = actualSales + aSales;
                                    }
                                    if (obj[3] != null) {
                                        Double aAmount = Double.parseDouble(String.valueOf(obj[3]));
                                        actualAmount = actualAmount + aAmount;
                                    }
                                    if (obj[4] != null) {
                                        Double pSales = Double.parseDouble(String.valueOf(obj[4]));
                                        projectedSales = projectedSales + pSales;
                                    }
                                    if (obj[5] != null) {
                                        Double pAmount = Double.parseDouble(String.valueOf(obj[5]));
                                        projectedAmount = projectedAmount + pAmount;
                                    }
                                    if (obj[9] != null) {
                                        Double projRate = Double.parseDouble(String.valueOf(obj[9]));
                                        projectedRate = projectedRate + projRate;
                                    }
                                    if (obj[10] != null) {
                                        Double acRPU = Double.parseDouble(String.valueOf(obj[10]));
                                        actualRPU = actualRPU + acRPU;
                                    }
                                    if (obj[11] != null) {
                                        Double prRPU = Double.parseDouble(String.valueOf(obj[11]));
                                        projectedRPU = projectedRPU + prRPU;
                                    }
                                }
                                if (i == list.size() - 1) {
                                    String column = discountDto.getGroup().replace(" ", StringUtils.EMPTY);
                                    if (periodList.contains(column.replace(Q_BIG, Q_SMALL))) {
                                        Double arate = actualAmount / actualSales;
                                        if (arate.isNaN() || arate.isInfinite()) {
                                            arate = 0.0;
                                        }
                                        arate = arate * 100;
                                        String actRate = String.valueOf(arate);
                                        discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double actualAmt = actualAmount;
//                                        actualAmt = actualAmt / 100;
                                        if (actualAmt.isNaN() || actualAmt.isInfinite()) {
                                            actualAmt = 0.0;
                                        }
                                        String actAmt = String.valueOf(actualAmt);
                                        discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                                        Double prate = projectedAmount / projectedSales;

                                        if (prate.isNaN() || prate.isInfinite()) {
                                            prate = 0.0;
                                        }
                                        prate = prate * 100;

                                        String proRate = String.valueOf(prate);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double projectedAmtAmt = projectedAmount;
//                                        projectedAmtAmt = projectedAmtAmt / 100;
                                        if (projectedAmtAmt.isNaN() || projectedAmtAmt.isInfinite()) {
                                            projectedAmtAmt = 0.0;
                                        }
                                        String proAmount = String.valueOf(projectedAmtAmt);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                        Double aRPU = actualAmount / actualRPU;
                                        if (aRPU.isNaN() || aRPU.isInfinite()) {
                                            aRPU = 0.0;
                                        }
                                        String actRPU = String.valueOf(aRPU);
                                        discountDto.addStringProperties(commonColumn + ACTUALRPU, actRPU != null && !NULL.equals(String.valueOf(actRPU)) && !StringUtils.EMPTY.equals(String.valueOf(actRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(actRPU)))) : HYPHEN);
                                        Double pRPU = projectedAmount / projectedRPU;
                                        if (pRPU.isNaN() || pRPU.isInfinite()) {
                                            pRPU = 0.0;
                                        }
                                        String proRPU = String.valueOf(pRPU);
                                        discountDto.addStringProperties(commonColumn + PROJECTEDRPU, proRPU != null && !NULL.equals(String.valueOf(proRPU)) && !StringUtils.EMPTY.equals(String.valueOf(proRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(proRPU)))) : HYPHEN);
                                        discountProjList.add(discountDto);
                                        periodList.remove(column.replace(Q_BIG, Q_SMALL));
                                    }
                                }
                            }
                        }
                        if (!periodList.isEmpty() && !projSelDTO.getDiscountNameList().isEmpty()) {
                            for (int i = 0; i < periodList.size(); i++) {
                                DiscountProjectionResultsDTO projDTO = new DiscountProjectionResultsDTO();
                                projDTO.setParent(0);
                                projDTO.setProjectionTotal(1);
                                String group = String.valueOf(periodList.get(i).replace(Q_SMALL, Q_BIG));
                                String year = group.substring(group.length() - 4, group.length());
                                String frequency = group.replace(year, StringUtils.EMPTY);
                                group = frequency + " " + year;
                                projDTO.setGroup(group);
                                for (String discount : projSelDTO.getDiscountNameList()) {
                                    String discountRate = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns = discountRate + ACTUALSRATE;
                                    projDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, NULL));

                                    String discountActualAmount = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns1 = discountActualAmount + ACTUALSAMOUNT;
                                    projDTO.addStringProperties(columns1, getFormattedValue(CUR_ZERO, NULL));

                                    String discountProjectionAmount = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns2 = discountProjectionAmount + PROJECTIONSAMOUNT;
                                    projDTO.addStringProperties(columns2, getFormattedValue(CUR_ZERO, NULL));

                                    String discountProjectionRate = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns3 = discountProjectionRate + PROJECTIONSRATE;
                                    projDTO.addStringProperties(columns3, getFormattedValue(CUR_ZERO, NULL));

                                    String discountActualRPU = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns4 = discountActualRPU + ACTUALRPU;
                                    projDTO.addStringProperties(columns4, getFormattedValue(CUR_ZERO, NULL));

                                    String discountProjectionRPU = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns5 = discountProjectionRPU + PROJECTEDRPU;
                                    projDTO.addStringProperties(columns5, getFormattedValue(CUR_ZERO, NULL));
                                }
                                discountProjList.add(projDTO);
                            }
                        }

                    }
                    if (SEMI_ANNUALLY.getConstant().equals(freq)) {
                        double actualSales = 0;
                        double actualAmount = 0;
                        double projectedSales = 0;
                        double projectedAmount = 0;
                        double projectedRate = 0;
                        double actualRPU = 0;
                        double projectedRPU = 0;
                        int frequencyDivision = 6;
                        String commonColumn = StringUtils.EMPTY;
                        List<String> periodList = new ArrayList<String>(projSelDTO.getPeriodList());
                        DiscountProjectionResultsDTO discountDto = new DiscountProjectionResultsDTO();
                        Object[] object = (Object[]) list.get(0);
                        String currentDiscount = String.valueOf(object[8]);
                        int currentYear = (Integer) object[0];
                        int currentQuarter = (Integer) object[6];
                        commonColumn = currentDiscount.replace(" ", StringUtils.EMPTY);
                        discountDto.setGroup(Constant.S + object[1] + " " + object[0]);
                        discountDto.setIsParent(ZERO);
                        if (object[2] != null) {
                            Double aSales = Double.parseDouble(String.valueOf(object[2]));
                            actualSales = actualSales + aSales;
                        }
                        if (object[3] != null) {
                            Double aAmount = Double.parseDouble(String.valueOf(object[3]));
                            actualAmount = actualAmount + aAmount;
                        }
                        if (object[4] != null) {
                            Double pSales = Double.parseDouble(String.valueOf(object[4]));
                            projectedSales = projectedSales + pSales;
                        }
                        if (object[5] != null) {
                            Double pAmount = Double.parseDouble(String.valueOf(object[5]));
                            projectedAmount = projectedAmount + pAmount;
                        }
                        if (object[9] != null) {
                            Double projRate = Double.parseDouble(String.valueOf(object[9]));
                            projectedRate = projectedRate + projRate;
                        }
                        if (object[10] != null) {
                            Double acRPU = Double.parseDouble(String.valueOf(object[10]));
                            actualRPU = actualRPU + acRPU;
                        }
                        if (object[11] != null) {
                            Double prRPU = Double.parseDouble(String.valueOf(object[11]));
                            projectedRPU = projectedRPU + prRPU;
                        }
                        if (list.size() == 1) {
                            Double arate = actualAmount / actualSales;
                            if (arate.isNaN()) {
                                arate = 0.0;
                            }
                            arate = arate * 100;
                            String actRate = String.valueOf(arate);
                            discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                            Double actualAmt = actualAmount;
//                            actualAmt = actualAmt / 100;
                            if (actualAmt.isNaN() || actualAmt.isInfinite()) {
                                actualAmt = 0.0;
                            }
                            String actAmt = String.valueOf(actualAmt);
                            discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                            Double prate = projectedAmount / projectedSales;
                            if (prate.isNaN() || prate.isInfinite()) {
                                prate = 0.0;
                            }
                            prate = prate * 100;
                            String proRate = String.valueOf(prate);
                            discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                            Double projectedAmtAmt = projectedAmount;
                            if (projectedAmtAmt.isNaN() || projectedAmtAmt.isInfinite()) {
                                projectedAmtAmt = 0.0;
                            }
                            String proAmount = String.valueOf(projectedAmtAmt);
                            discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                            Double aRPU = actualAmount / actualRPU;
                            if (aRPU.isNaN() || aRPU.isInfinite()) {
                                aRPU = 0.0;
                            }
                            String actRPU = String.valueOf(aRPU);
                            discountDto.addStringProperties(commonColumn + ACTUALRPU, actRPU != null && !NULL.equals(String.valueOf(actRPU)) && !StringUtils.EMPTY.equals(String.valueOf(actRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(actRPU)))) : HYPHEN);
                            Double pRPU = projectedAmount / projectedRPU;
                            if (pRPU.isNaN() || pRPU.isInfinite()) {
                                pRPU = 0.0;
                            }
                            String proRPU = String.valueOf(pRPU);
                            discountDto.addStringProperties(commonColumn + PROJECTEDRPU, proRPU != null && !NULL.equals(String.valueOf(proRPU)) && !StringUtils.EMPTY.equals(String.valueOf(proRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(proRPU)))) : HYPHEN);
                            commonColumn = StringUtils.EMPTY;
                            discountProjList.add(discountDto);
                        } else {
                            for (int i = 1; i < list.size(); i++) {
                                Object[] obj = (Object[]) list.get(i);
                                String selectedDiscount = String.valueOf(obj[8]);
                                int selectedYear = (Integer) obj[0];
                                int selectedQuarter = (Integer) obj[6];
                                if (currentYear == selectedYear && currentQuarter == selectedQuarter) {
                                    if (currentDiscount.equalsIgnoreCase(selectedDiscount)) {
                                        if (obj[2] != null) {
                                            Double aSales = Double.parseDouble(String.valueOf(obj[2]));
                                            actualSales = actualSales + aSales;
                                        }
                                        if (obj[3] != null) {
                                            Double aAmount = Double.parseDouble(String.valueOf(obj[3]));
                                            actualAmount = actualAmount + aAmount;
                                        }
                                        if (obj[4] != null) {
                                            Double pSales = Double.parseDouble(String.valueOf(obj[4]));
                                            projectedSales = projectedSales + pSales;
                                        }
                                        if (obj[5] != null) {
                                            Double pAmount = Double.parseDouble(String.valueOf(obj[5]));
                                            projectedAmount = projectedAmount + pAmount;
                                        }
                                        if (obj[9] != null) {
                                            Double projRate = Double.parseDouble(String.valueOf(obj[9]));
                                            projectedRate = projectedRate + projRate;
                                        }
                                        if (obj[10] != null) {
                                            Double acRPU = Double.parseDouble(String.valueOf(obj[10]));
                                            actualRPU = actualRPU + acRPU;
                                        }
                                        if (obj[11] != null) {
                                            Double prRPU = Double.parseDouble(String.valueOf(obj[11]));
                                            projectedRPU = projectedRPU + prRPU;
                                        }
                                    } else {
                                        Double arate = actualAmount / actualSales;
                                        if (arate.isNaN() || arate.isInfinite()) {
                                            arate = 0.0;
                                        }
                                        arate = arate * 100;
                                        String actRate = String.valueOf(arate);
                                        discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double actualAmt = actualAmount;//                                      
                                        if (actualAmt.isNaN() || actualAmt.isInfinite()) {
                                            actualAmt = 0.0;
                                        }
                                        String actAmt = String.valueOf(actualAmt);
                                        discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                                        Double prate = projectedAmount / projectedSales;
                                        if (prate.isNaN() || prate.isInfinite()) {
                                            prate = 0.0;
                                        }
                                        prate = prate * 100;

                                        String proRate = String.valueOf(prate);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double projectedAmtAmt = projectedAmount;
                                        if (projectedAmtAmt.isNaN() || projectedAmtAmt.isInfinite()) {
                                            projectedAmtAmt = 0.0;
                                        }
                                        String proAmount = String.valueOf(projectedAmtAmt);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                        Double aRPU = actualAmount / actualRPU;
                                        if (aRPU.isNaN() || aRPU.isInfinite()) {
                                            aRPU = 0.0;
                                        }
                                        String actRPU = String.valueOf(aRPU);
                                        discountDto.addStringProperties(commonColumn + ACTUALRPU, actRPU != null && !NULL.equals(String.valueOf(actRPU)) && !StringUtils.EMPTY.equals(String.valueOf(actRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(actRPU)))) : HYPHEN);
                                        Double pRPU = projectedAmount / projectedRPU;
                                        if (pRPU.isNaN() || pRPU.isInfinite()) {
                                            pRPU = 0.0;
                                        }
                                        String proRPU = String.valueOf(pRPU);
                                        discountDto.addStringProperties(commonColumn + PROJECTEDRPU, proRPU != null && !NULL.equals(String.valueOf(proRPU)) && !StringUtils.EMPTY.equals(String.valueOf(proRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(proRPU)))) : HYPHEN);
                                        commonColumn = StringUtils.EMPTY;
                                        actualSales = 0;
                                        actualAmount = 0;
                                        projectedSales = 0;
                                        projectedAmount = 0;
                                        projectedRate = 0;
                                        actualRPU = 0;
                                        projectedRPU = 0;
                                        currentDiscount = selectedDiscount;
                                        commonColumn = selectedDiscount.replace(" ", StringUtils.EMPTY);
                                        if (obj[2] != null) {
                                            Double aSales = Double.parseDouble(String.valueOf(obj[2]));
                                            actualSales = actualSales + aSales;
                                        }
                                        if (obj[3] != null) {
                                            Double aAmount = Double.parseDouble(String.valueOf(obj[3]));
                                            actualAmount = actualAmount + aAmount;
                                        }

                                        if (obj[4] != null) {
                                            Double pSales = Double.parseDouble(String.valueOf(obj[4]));
                                            projectedSales = projectedSales + pSales;
                                        }
                                        if (obj[5] != null) {
                                            Double pAmount = Double.parseDouble(String.valueOf(obj[5]));
                                            projectedAmount = projectedAmount + pAmount;
                                        }
                                        if (obj[9] != null) {
                                            Double projRate = Double.parseDouble(String.valueOf(obj[9]));
                                            projectedRate = projectedRate + projRate;
                                        }
                                        if (obj[10] != null) {
                                            Double acRPU = Double.parseDouble(String.valueOf(obj[10]));
                                            actualRPU = actualRPU + acRPU;
                                        }
                                        if (obj[11] != null) {
                                            Double prRPU = Double.parseDouble(String.valueOf(obj[11]));
                                            projectedRPU = projectedRPU + prRPU;
                                        }
                                    }
                                } else {
                                    String column = discountDto.getGroup().replace(" ", StringUtils.EMPTY);
                                    periodList.contains(column.replace(Constant.S, Constant.S_SMALL));
                                    Double arate = actualAmount / actualSales;
                                    if (arate.isNaN() || arate.isInfinite()) {
                                        arate = 0.0;
                                    }
                                    arate = arate * 100;
                                    String actRate = String.valueOf(arate);
                                    discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                    Double actualAmt = actualAmount;
                                    if (actualAmt.isNaN() || actualAmt.isInfinite()) {
                                        actualAmt = 0.0;
                                    }
                                    String actAmt = String.valueOf(actualAmt);
                                    discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                                    Double prate = projectedAmount / projectedSales;
                                    if (prate.isNaN() || prate.isInfinite()) {
                                        prate = 0.0;
                                    }
                                    prate = prate * 100;

                                    String proRate = String.valueOf(prate);
                                    discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                    Double projectedAmtAmt = projectedAmount;
                                    if (projectedAmtAmt.isNaN() || projectedAmtAmt.isInfinite()) {
                                        projectedAmtAmt = 0.0;
                                    }
                                    String proAmount = String.valueOf(projectedAmtAmt);
                                    discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                    Double aRPU = actualAmount / actualRPU;
                                    if (aRPU.isNaN() || aRPU.isInfinite()) {
                                        aRPU = 0.0;
                                    }
                                    String actRPU = String.valueOf(aRPU);
                                    discountDto.addStringProperties(commonColumn + ACTUALRPU, actRPU != null && !NULL.equals(String.valueOf(actRPU)) && !StringUtils.EMPTY.equals(String.valueOf(actRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(actRPU)))) : HYPHEN);
                                    Double pRPU = projectedAmount / projectedRPU;
                                    if (pRPU.isNaN() || pRPU.isInfinite()) {
                                        pRPU = 0.0;
                                    }
                                    String proRPU = String.valueOf(pRPU);
                                    discountDto.addStringProperties(commonColumn + PROJECTEDRPU, proRPU != null && !NULL.equals(String.valueOf(proRPU)) && !StringUtils.EMPTY.equals(String.valueOf(proRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(proRPU)))) : HYPHEN);
                                    discountProjList.add(discountDto);
                                    periodList.remove(column.replace("S", "s"));
                                    actualSales = 0;
                                    actualAmount = 0;
                                    projectedSales = 0;
                                    projectedAmount = 0;
                                    projectedRate = 0;
                                    actualRPU = 0;
                                    projectedRPU = 0;
                                    discountDto = new DiscountProjectionResultsDTO();
                                    discountDto.setGroup(Constant.S + obj[1] + " " + obj[0]);
                                    currentYear = selectedYear;
                                    currentQuarter = selectedQuarter;
                                    currentDiscount = selectedDiscount;
                                    commonColumn = selectedDiscount.replace(" ", StringUtils.EMPTY);
                                    discountDto.setIsParent(ZERO);
                                    if (obj[2] != null) {
                                        Double aSales = Double.parseDouble(String.valueOf(obj[2]));
                                        actualSales = actualSales + aSales;
                                    }
                                    if (obj[3] != null) {
                                        Double aAmount = Double.parseDouble(String.valueOf(obj[3]));
                                        actualAmount = actualAmount + aAmount;
                                    }
                                    if (obj[4] != null) {
                                        Double pSales = Double.parseDouble(String.valueOf(obj[4]));
                                        projectedSales = projectedSales + pSales;
                                    }
                                    if (obj[5] != null) {
                                        Double pAmount = Double.parseDouble(String.valueOf(obj[5]));
                                        projectedAmount = projectedAmount + pAmount;
                                    }
                                    if (obj[9] != null) {
                                        Double projRate = Double.parseDouble(String.valueOf(obj[9]));
                                        projectedRate = projectedRate + projRate;
                                    }
                                    if (obj[10] != null) {
                                        Double acRPU = Double.parseDouble(String.valueOf(obj[10]));
                                        actualRPU = actualRPU + acRPU;
                                    }
                                    if (obj[11] != null) {
                                        Double prRPU = Double.parseDouble(String.valueOf(obj[11]));
                                        projectedRPU = projectedRPU + prRPU;
                                    }
                                }
                                if (i == list.size() - 1) {
                                    String column = discountDto.getGroup().replace(" ", StringUtils.EMPTY);
                                    if (periodList.contains(column.replace(Constant.S, Constant.S_SMALL))) {
                                        Double arate = actualAmount / actualSales;
                                        if (arate.isNaN() || arate.isInfinite()) {
                                            arate = 0.0;
                                        }
                                        arate = arate * 100;
                                        String actRate = String.valueOf(arate);
                                        discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double actualAmt = actualAmount;
//                                        actualAmt = actualAmt / 100;
                                        if (actualAmt.isNaN() || actualAmt.isInfinite()) {
                                            actualAmt = 0.0;
                                        }
                                        String actAmt = String.valueOf(actualAmt);
                                        discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                                        Double prate = projectedAmount / projectedSales;
                                        if (prate.isNaN() || prate.isInfinite()) {
                                            prate = 0.0;
                                        }
                                        prate = prate * 100;

                                        String proRate = String.valueOf(prate);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double projectedAmtAmt = projectedAmount;
//                                        projectedAmtAmt = projectedAmtAmt / 100;
                                        if (projectedAmtAmt.isNaN() || projectedAmtAmt.isInfinite()) {
                                            projectedAmtAmt = 0.0;
                                        }
                                        String proAmount = String.valueOf(projectedAmtAmt);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                        Double aRPU = actualAmount / actualRPU;
                                        if (aRPU.isNaN() || aRPU.isInfinite()) {
                                            aRPU = 0.0;
                                        }
                                        String actRPU = String.valueOf(aRPU);
                                        discountDto.addStringProperties(commonColumn + ACTUALRPU, actRPU != null && !NULL.equals(String.valueOf(actRPU)) && !StringUtils.EMPTY.equals(String.valueOf(actRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(actRPU)))) : HYPHEN);
                                        Double pRPU = projectedAmount / projectedRPU;
                                        if (pRPU.isNaN() || pRPU.isInfinite()) {
                                            pRPU = 0.0;
                                        }
                                        String proRPU = String.valueOf(pRPU);
                                        discountDto.addStringProperties(commonColumn + PROJECTEDRPU, proRPU != null && !NULL.equals(String.valueOf(proRPU)) && !StringUtils.EMPTY.equals(String.valueOf(proRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(proRPU)))) : HYPHEN);
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
                                String year = group.substring(group.length() - 4, group.length());
                                String frequency = group.replace(year, StringUtils.EMPTY);
                                group = frequency + " " + year;
                                projDTO.setGroup(group);
                                for (String discount : projSelDTO.getDiscountNameList()) {
                                    String discountRate = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns = discountRate + ACTUALSRATE;
                                    projDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, NULL));

                                    String discountActualAmount = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns1 = discountActualAmount + ACTUALSAMOUNT;
                                    projDTO.addStringProperties(columns1, getFormattedValue(CUR_ZERO, NULL));

                                    String discountProjectionAmount = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns2 = discountProjectionAmount + PROJECTIONSAMOUNT;
                                    projDTO.addStringProperties(columns2, getFormattedValue(CUR_ZERO, NULL));

                                    String discountProjectionRate = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns3 = discountProjectionRate + PROJECTIONSRATE;
                                    projDTO.addStringProperties(columns3, getFormattedValue(CUR_ZERO, NULL));

                                    String discountActualRPU = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns4 = discountActualRPU + ACTUALRPU;
                                    projDTO.addStringProperties(columns4, getFormattedValue(CUR_ZERO, NULL));

                                    String discountProjectionRPU = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns5 = discountProjectionRPU + PROJECTEDRPU;
                                    projDTO.addStringProperties(columns5, getFormattedValue(CUR_ZERO, NULL));
                                }
                                discountProjList.add(projDTO);
                            }
                        }
                    }
                    if (MONTHLY.getConstant().equals(freq)) {
                        double actualSales = 0;
                        double actualAmount = 0;
                        double projectedSales = 0;
                        double projectedAmount = 0;
                        double projectedRate = 0;
                        double actualRPU = 0;
                        double projectedRPU = 0;
                        String commonColumn = StringUtils.EMPTY;
                        loadMap();
                        List<String> periodList = new ArrayList<String>(projSelDTO.getPeriodList());
                        DiscountProjectionResultsDTO discountDto = new DiscountProjectionResultsDTO();
                        Object[] object = (Object[]) list.get(0);
                        String currentDiscount = String.valueOf(object[8]);
                        int currentYear = (Integer) object[0];
                        int currentMonth = (Integer) object[6];
                        commonColumn = currentDiscount.replace(" ", StringUtils.EMPTY);
                        discountDto.setGroup(CommonUtils.BUSINESS_PROCESS_INDICATOR_MANDATED + object[1] + " " + object[0]);
                        discountDto.setIsParent(ZERO);
                        if (object[2] != null) {
                            Double aSales = Double.parseDouble(String.valueOf(object[2]));
                            actualSales = actualSales + aSales;
                        }
                        if (object[3] != null) {
                            Double aAmount = Double.parseDouble(String.valueOf(object[3]));
                            actualAmount = actualAmount + aAmount;
                        }
                        if (object[4] != null) {
                            Double pSales = Double.parseDouble(String.valueOf(object[4]));
                            projectedSales = projectedSales + pSales;
                        }
                        if (object[5] != null) {
                            Double pAmount = Double.parseDouble(String.valueOf(object[5]));
                            projectedAmount = projectedAmount + pAmount;
                        }
                        if (object[9] != null) {
                            Double projRate = Double.parseDouble(String.valueOf(object[9]));
                            projectedRate = projectedRate + projRate;
                        }
                        if (object[10] != null) {
                            Double acRPU = Double.parseDouble(String.valueOf(object[10]));
                            actualRPU = actualRPU + acRPU;
                        }
                        if (object[11] != null) {
                            Double prRPU = Double.parseDouble(String.valueOf(object[11]));
                            projectedRPU = projectedRPU + prRPU;
                        }
                        if (list.size() == 1) {
                            Double arate = actualAmount / actualSales;
                            if (arate.isNaN()) {
                                arate = 0.0;
                            }
                            arate = arate * 100;
                            String actRate = String.valueOf(arate);
                            discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                            Double actualAmt = actualAmount;
//                            actualAmt = actualAmt / 100;
                            if (actualAmt.isNaN() || actualAmt.isInfinite()) {
                                actualAmt = 0.0;
                            }
                            String actAmt = String.valueOf(actualAmt);
                            discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                            Double prate = projectedAmount / projectedSales;
                            if (prate.isNaN() || prate.isInfinite()) {
                                prate = 0.0;
                            }
                            prate = prate * 100;

                            String proRate = String.valueOf(prate);
                            discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                            Double projectedAmtAmt = projectedAmount;
//                            projectedAmtAmt = projectedAmtAmt / 100;
                            if (projectedAmtAmt.isNaN() || projectedAmtAmt.isInfinite()) {
                                projectedAmtAmt = 0.0;
                            }
                            String proAmount = String.valueOf(projectedAmtAmt);
                            discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                            Double aRPU = actualAmount / actualRPU;
                            if (aRPU.isNaN() || aRPU.isInfinite()) {
                                aRPU = 0.0;
                            }
                            String actRPU = String.valueOf(aRPU);
                            discountDto.addStringProperties(commonColumn + ACTUALRPU, actRPU != null && !NULL.equals(String.valueOf(actRPU)) && !StringUtils.EMPTY.equals(String.valueOf(actRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(actRPU)))) : HYPHEN);
                            Double pRPU = projectedAmount / projectedRPU;
                            if (pRPU.isNaN() || pRPU.isInfinite()) {
                                pRPU = 0.0;
                            }
                            String proRPU = String.valueOf(pRPU);
                            discountDto.addStringProperties(commonColumn + PROJECTEDRPU, proRPU != null && !NULL.equals(String.valueOf(proRPU)) && !StringUtils.EMPTY.equals(String.valueOf(proRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(proRPU)))) : HYPHEN);
                            commonColumn = StringUtils.EMPTY;
                            discountProjList.add(discountDto);
                        } else {
                            for (int i = 1; i < list.size(); i++) {
                                Object[] obj = (Object[]) list.get(i);
                                String selectedDiscount = String.valueOf(obj[8]);
                                int selectedYear = (Integer) obj[0];
                                int selectedMonth = (Integer) obj[6];
                                if (currentYear == selectedYear && currentMonth == selectedMonth) {
                                    if (currentDiscount.equalsIgnoreCase(selectedDiscount)) {
                                        if (obj[2] != null) {
                                            Double aSales = Double.parseDouble(String.valueOf(obj[2]));
                                            actualSales = actualSales + aSales;
                                        }
                                        if (obj[3] != null) {
                                            Double aAmount = Double.parseDouble(String.valueOf(obj[3]));
                                            actualAmount = actualAmount + aAmount;
                                        }
                                        if (obj[4] != null) {
                                            Double pSales = Double.parseDouble(String.valueOf(obj[4]));
                                            projectedSales = projectedSales + pSales;
                                        }
                                        if (obj[5] != null) {
                                            Double pAmount = Double.parseDouble(String.valueOf(obj[5]));
                                            projectedAmount = projectedAmount + pAmount;
                                        }
                                        if (obj[9] != null) {
                                            Double projRate = Double.parseDouble(String.valueOf(obj[9]));
                                            projectedRate = projectedRate + projRate;
                                        }
                                        if (obj[10] != null) {
                                            Double acRPU = Double.parseDouble(String.valueOf(obj[10]));
                                            actualRPU = actualRPU + acRPU;
                                        }
                                        if (obj[11] != null) {
                                            Double prRPU = Double.parseDouble(String.valueOf(obj[11]));
                                            projectedRPU = projectedRPU + prRPU;
                                        }
                                    } else {
                                        Double arate = actualAmount / actualSales;
                                        if (arate.isNaN() || arate.isInfinite()) {
                                            arate = 0.0;
                                        }
                                        arate = arate * 100;
                                        String actRate = String.valueOf(arate);
                                        discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double actualAmt = actualAmount;
//                                        actualAmt = actualAmt / 100;
                                        if (actualAmt.isNaN() || actualAmt.isInfinite()) {
                                            actualAmt = 0.0;
                                        }
                                        String actAmt = String.valueOf(actualAmt);
                                        discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                                        Double prate = projectedAmount / projectedSales;
                                        if (prate.isNaN() || prate.isInfinite()) {
                                            prate = 0.0;
                                        }
                                        prate = prate * 100;

                                        String proRate = String.valueOf(prate);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double projectedAmtAmt = projectedAmount;
//                                        projectedAmtAmt = projectedAmtAmt / 100;
                                        if (projectedAmtAmt.isNaN() || projectedAmtAmt.isInfinite()) {
                                            projectedAmtAmt = 0.0;
                                        }
                                        String proAmount = String.valueOf(projectedAmtAmt);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                        Double aRPU = actualAmount / actualRPU;
                                        if (aRPU.isNaN() || aRPU.isInfinite()) {
                                            aRPU = 0.0;
                                        }
                                        String actRPU = String.valueOf(aRPU);
                                        discountDto.addStringProperties(commonColumn + ACTUALRPU, actRPU != null && !NULL.equals(String.valueOf(actRPU)) && !StringUtils.EMPTY.equals(String.valueOf(actRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(actRPU)))) : HYPHEN);
                                        Double pRPU = projectedAmount / projectedRPU;
                                        if (pRPU.isNaN() || pRPU.isInfinite()) {
                                            pRPU = 0.0;
                                        }
                                        String proRPU = String.valueOf(pRPU);
                                        discountDto.addStringProperties(commonColumn + PROJECTEDRPU, proRPU != null && !NULL.equals(String.valueOf(proRPU)) && !StringUtils.EMPTY.equals(String.valueOf(proRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(proRPU)))) : HYPHEN);
                                        commonColumn = StringUtils.EMPTY;
                                        actualSales = 0;
                                        actualAmount = 0;
                                        projectedSales = 0;
                                        projectedAmount = 0;
                                        projectedRate = 0;
                                        actualRPU = 0;
                                        projectedRPU = 0;
                                        currentDiscount = selectedDiscount;
                                        commonColumn = selectedDiscount.replace(" ", StringUtils.EMPTY);
                                        if (obj[2] != null) {
                                            Double aSales = Double.parseDouble(String.valueOf(obj[2]));
                                            actualSales = actualSales + aSales;
                                        }
                                        if (obj[3] != null) {
                                            Double aAmount = Double.parseDouble(String.valueOf(obj[3]));
                                            actualAmount = actualAmount + aAmount;
                                        }
                                        if (obj[4] != null) {
                                            Double pSales = Double.parseDouble(String.valueOf(obj[4]));
                                            projectedSales = projectedSales + pSales;
                                        }
                                        if (obj[5] != null) {
                                            Double pAmount = Double.parseDouble(String.valueOf(obj[5]));
                                            projectedAmount = projectedAmount + pAmount;
                                        }
                                        if (obj[9] != null) {
                                            Double projRate = Double.parseDouble(String.valueOf(obj[9]));
                                            projectedRate = projectedRate + projRate;
                                        }
                                        if (obj[10] != null) {
                                            Double acRPU = Double.parseDouble(String.valueOf(obj[10]));
                                            actualRPU = actualRPU + acRPU;
                                        }
                                        if (obj[11] != null) {
                                            Double prRPU = Double.parseDouble(String.valueOf(obj[11]));
                                            projectedRPU = projectedRPU + prRPU;
                                        }
                                    }
                                } else {
                                    String column = createColumn(String.valueOf(discountDto.getGroup().replace(" ", StringUtils.EMPTY)));
                                    if (periodList.contains(column)) {
                                        Double arate = actualAmount / actualSales;
                                        if (arate.isNaN() || arate.isInfinite()) {
                                            arate = 0.0;
                                        }
                                        arate = arate * 100;
                                        String actRate = String.valueOf(arate);
                                        discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double actualAmt = actualAmount;
//                                        actualAmt = actualAmt / 100;
                                        if (actualAmt.isNaN() || actualAmt.isInfinite()) {
                                            actualAmt = 0.0;
                                        }
                                        String actAmt = String.valueOf(actualAmt);
                                        discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                                        Double prate = projectedAmount / projectedSales;
                                        if (prate.isNaN() || prate.isInfinite()) {
                                            prate = 0.0;
                                        }
                                        prate = prate * 100;

                                        String proRate = String.valueOf(prate);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double projectedAmtAmt = projectedAmount;
//                                        projectedAmtAmt = projectedAmtAmt / 100;
                                        if (projectedAmtAmt.isNaN() || projectedAmtAmt.isInfinite()) {
                                            projectedAmtAmt = 0.0;
                                        }
                                        String proAmount = String.valueOf(projectedAmtAmt);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                        Double aRPU = actualAmount / actualRPU;
                                        if (aRPU.isNaN() || aRPU.isInfinite()) {
                                            aRPU = 0.0;
                                        }
                                        String actRPU = String.valueOf(aRPU);
                                        discountDto.addStringProperties(commonColumn + ACTUALRPU, actRPU != null && !NULL.equals(String.valueOf(actRPU)) && !StringUtils.EMPTY.equals(String.valueOf(actRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(actRPU)))) : HYPHEN);
                                        Double pRPU = projectedAmount / projectedRPU;
                                        if (pRPU.isNaN() || pRPU.isInfinite()) {
                                            pRPU = 0.0;
                                        }
                                        String proRPU = String.valueOf(pRPU);
                                        discountDto.addStringProperties(commonColumn + PROJECTEDRPU, proRPU != null && !NULL.equals(String.valueOf(proRPU)) && !StringUtils.EMPTY.equals(String.valueOf(proRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(proRPU)))) : HYPHEN);
                                        discountProjList.add(discountDto);
                                        periodList.remove(column);
                                    }
                                    actualSales = 0;
                                    actualAmount = 0;
                                    projectedSales = 0;
                                    projectedAmount = 0;
                                    projectedRate = 0;
                                    actualRPU = 0;
                                    projectedRPU = 0;
                                    discountDto = new DiscountProjectionResultsDTO();
                                    discountDto.setGroup(CommonUtils.BUSINESS_PROCESS_INDICATOR_MANDATED + obj[1] + " " + obj[0]);
                                    currentYear = selectedYear;
                                    currentMonth = selectedMonth;
                                    commonColumn = selectedDiscount.replace(" ", StringUtils.EMPTY);
                                    currentDiscount = selectedDiscount;
                                    discountDto.setIsParent(ZERO);
                                    if (obj[2] != null) {
                                        Double aSales = Double.parseDouble(String.valueOf(obj[2]));
                                        actualSales = actualSales + aSales;
                                    }
                                    if (obj[3] != null) {
                                        Double aAmount = Double.parseDouble(String.valueOf(obj[3]));
                                        actualAmount = actualAmount + aAmount;
                                    }
                                    if (obj[4] != null) {
                                        Double pSales = Double.parseDouble(String.valueOf(obj[4]));
                                        projectedSales = projectedSales + pSales;
                                    }
                                    if (obj[5] != null) {
                                        Double pAmount = Double.parseDouble(String.valueOf(obj[5]));
                                        projectedAmount = projectedAmount + pAmount;
                                    }
                                    if (obj[9] != null) {
                                        Double projRate = Double.parseDouble(String.valueOf(obj[9]));
                                        projectedRate = projectedRate + projRate;
                                    }
                                    if (obj[10] != null) {
                                        Double acRPU = Double.parseDouble(String.valueOf(obj[10]));
                                        actualRPU = actualRPU + acRPU;
                                    }
                                    if (obj[11] != null) {
                                        Double prRPU = Double.parseDouble(String.valueOf(obj[11]));
                                        projectedRPU = projectedRPU + prRPU;
                                    }
                                }
                                if (i == list.size() - 1) {
                                    String column = createColumn(String.valueOf(discountDto.getGroup()).replace(" ", StringUtils.EMPTY));
                                    if (periodList.contains(column)) {
                                        Double arate = actualAmount / actualSales;
                                        if (arate.isNaN() || arate.isInfinite()) {
                                            arate = 0.0;
                                        }
                                        arate = arate * 100;
                                        String actRate = String.valueOf(arate);
                                        discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double actualAmt = actualAmount;
//                                        actualAmt = actualAmt / 100;
                                        if (actualAmt.isNaN() || actualAmt.isInfinite()) {
                                            actualAmt = 0.0;
                                        }
                                        String actAmt = String.valueOf(actualAmt);
                                        discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                                        Double prate = projectedAmount / projectedSales;
                                        if (prate.isNaN() || prate.isInfinite()) {
                                            prate = 0.0;
                                        }
                                        prate = prate * 100;

                                        String proRate = String.valueOf(prate);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double projectedAmtAmt = projectedAmount;
//                                        projectedAmtAmt = projectedAmtAmt / 100;
                                        if (projectedAmtAmt.isNaN() || projectedAmtAmt.isInfinite()) {
                                            projectedAmtAmt = 0.0;
                                        }
                                        String proAmount = String.valueOf(projectedAmtAmt);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                        Double aRPU = actualAmount / actualRPU;
                                        if (aRPU.isNaN() || aRPU.isInfinite()) {
                                            aRPU = 0.0;
                                        }
                                        String actRPU = String.valueOf(aRPU);
                                        discountDto.addStringProperties(commonColumn + ACTUALRPU, actRPU != null && !NULL.equals(String.valueOf(actRPU)) && !StringUtils.EMPTY.equals(String.valueOf(actRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(actRPU)))) : HYPHEN);
                                        Double pRPU = projectedAmount / projectedRPU;
                                        if (pRPU.isNaN() || pRPU.isInfinite()) {
                                            pRPU = 0.0;
                                        }
                                        String proRPU = String.valueOf(pRPU);
                                        discountDto.addStringProperties(commonColumn + PROJECTEDRPU, proRPU != null && !NULL.equals(String.valueOf(proRPU)) && !StringUtils.EMPTY.equals(String.valueOf(proRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(proRPU)))) : HYPHEN);
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
                                String month = group.substring(0, 3);
                                String year = group.replace(month, StringUtils.EMPTY);
                                String column = valueMap.get(month);

                                String groupColumn = CommonUtils.BUSINESS_PROCESS_INDICATOR_MANDATED + column + " " + year;
                                projDTO.setGroup(String.valueOf(groupColumn));
                                for (String discount : projSelDTO.getDiscountNameList()) {
                                    String discountRate = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns = discountRate + ACTUALSRATE;
                                    projDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, NULL));

                                    String discountActualAmount = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns1 = discountActualAmount + ACTUALSAMOUNT;
                                    projDTO.addStringProperties(columns1, getFormattedValue(CUR_ZERO, NULL));

                                    String discountProjectionAmount = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns2 = discountProjectionAmount + PROJECTIONSAMOUNT;
                                    projDTO.addStringProperties(columns2, getFormattedValue(CUR_ZERO, NULL));

                                    String discountProjectionRate = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns3 = discountProjectionRate + PROJECTIONSRATE;
                                    projDTO.addStringProperties(columns3, getFormattedValue(CUR_ZERO, NULL));

                                    String discountActualRPU = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns4 = discountActualRPU + ACTUALRPU;
                                    projDTO.addStringProperties(columns4, getFormattedValue(CUR_ZERO, NULL));

                                    String discountProjectionRPU = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns5 = discountProjectionRPU + PROJECTEDRPU;
                                    projDTO.addStringProperties(columns5, getFormattedValue(CUR_ZERO, NULL));
                                }
                                discountProjList.add(projDTO);
                            }
                        }
                    }
                    if (Constant.DESCENDING.equals(projSelDTO.getProjectionOrder())) {
                        Collections.reverse(discountProjList);
                    }

                }
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
    public List<DiscountProjectionResultsDTO> getPivotHierarchy(ProjectionSelectionDTO projSelDTO, List<Integer> startAndEndPeriods) throws SystemException, PortalException {
        List<DiscountProjectionResultsDTO> discountProjList = new ArrayList<DiscountProjectionResultsDTO>();
        int projectionMasterId = projSelDTO.getProjectionId();
        String selectedView = projSelDTO.getView();
        String level = String.valueOf(projSelDTO.getTreeLevelNo());
        String hierarchyNo = String.valueOf(projSelDTO.getHierarchyNo());
        List ccpId = null;
        if (CUSTOMER.getConstant().equals(selectedView)) {
            ccpId = NmDiscountProjMasterLocalServiceUtil.getCCPDetailsID(projectionMasterId, hierarchyNo, level);
        }
        if (PRODUCT.getConstant().equals(selectedView)) {
            ccpId = NmDiscountProjMasterLocalServiceUtil.getCCPDetailsIDForProductHierarchy(projectionMasterId, hierarchyNo, level);
        }
        if (CUSTOM.getConstant().equals(selectedView)) {
            projSelDTO.setIsCustomHierarchy(true);
            String customQuery = CommonLogic.getCustomCCPQuery(projSelDTO);
            List<Object> list = (List<Object>) executeSelectQuery(customQuery, null, null);
            if (list != null && !list.isEmpty()) {
                ccpId = new ArrayList();
                for (int i = 0; i < list.size(); i++) {
                    Object[] obj = (Object[]) list.get(i);
                    String id = String.valueOf(obj[1]);
                    if (!NULL.equals(id)) {
                        int ccp = Integer.parseInt(id);
                        ccpId.add(ccp);
                    }
                }
            }
        }
        if (ccpId != null && !ccpId.isEmpty()) {
            List<Integer> proDetailsSid = new ArrayList<Integer>();
            final DynamicQuery projectiondetailsDynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionDetails.class);
            projectiondetailsDynamicQuery.add(RestrictionsFactoryUtil.eq(PROJECTION_MASTER_SID, projectionMasterId));
            projectiondetailsDynamicQuery.add(RestrictionsFactoryUtil.in(CCP_DETAILS_SID, ccpId));
            final ProjectionList projList = ProjectionFactoryUtil.projectionList();
            projList.add(ProjectionFactoryUtil.property(PROJECTION_DETAILS_SID));
            projectiondetailsDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projList));
            proDetailsSid = ProjectionDetailsLocalServiceUtil.dynamicQuery(projectiondetailsDynamicQuery);
            if (proDetailsSid != null && !proDetailsSid.isEmpty()) {
                String userId = String.valueOf(projSelDTO.getUserId());
                String sessionId = String.valueOf(projSelDTO.getSessionId());
                int user = Integer.parseInt(userId);
                int session = Integer.parseInt(sessionId);
                String frequ = String.valueOf(projSelDTO.getFrequency());
                List<String> discountList = new ArrayList<String>();
                List<String> tmpList = new ArrayList<String>();
                discountList = projSelDTO.getDiscountNameList();
                String discountString = getDiscountName(discountList);
                projSelDTO.setCcpCount(proDetailsSid.size());
                List list = NmDiscountProjMasterLocalServiceUtil.getSubDiscount(proDetailsSid, frequ, discountString, startAndEndPeriods, user, session);
                if (list != null && !list.isEmpty()) {
                    if (ANNUALLY.getConstant().equals(frequ)) {
                        double actualSales = 0;
                        double actualAmount = 0;
                        double projectedSales = 0;
                        double projectedAmount = 0;
                        double projectedRate = 0;
                        double actualRPU = 0;
                        double projectedRPU = 0;
                        int frequencyDivision = 12;
                        tmpList.addAll(discountList);
                        String commonColumn = StringUtils.EMPTY;
                        List<String> periodList = new ArrayList<String>(projSelDTO.getPeriodList());
                        DiscountProjectionResultsDTO discountDto = new DiscountProjectionResultsDTO();
                        Object[] object = (Object[]) list.get(0);
                        String currentDiscount = String.valueOf(object[8]);
                        tmpList.remove(currentDiscount);
                        int currentYear = (Integer) object[0];
                        commonColumn = currentDiscount.replace(" ", StringUtils.EMPTY);
                        discountDto.setGroup(String.valueOf(currentYear));
                        discountDto.setIsParent(ZERO);
                        discountDto.setParent(0);
                        if (object[2] != null) {
                            Double aSales = Double.parseDouble(String.valueOf(object[2]));
                            actualSales = actualSales + aSales;
                        }
                        if (object[3] != null) {
                            Double aAmount = Double.parseDouble(String.valueOf(object[3]));
                            actualAmount = actualAmount + aAmount;
                        }
                        if (object[4] != null) {
                            Double pSales = Double.parseDouble(String.valueOf(object[4]));
                            projectedSales = projectedSales + pSales;
                        }
                        if (object[5] != null) {
                            Double pAmount = Double.parseDouble(String.valueOf(object[5]));
                            projectedAmount = projectedAmount + pAmount;
                        }
                        if (object[9] != null) {
                            Double projRate = Double.parseDouble(String.valueOf(object[9]));
                            projectedRate = projectedRate + projRate;
                        }
                        if (object[10] != null) {
                            Double acRPU = Double.parseDouble(String.valueOf(object[10]));
                            actualRPU = actualRPU + acRPU;
                        }
                        if (object[11] != null) {
                            Double prRPU = Double.parseDouble(String.valueOf(object[11]));
                            projectedRPU = projectedRPU + prRPU;
                        }

                        if (list.size() == 1) {
                            if (periodList.contains(discountDto.getGroup())) {
                                Double arate = actualAmount / actualSales;
                                if (arate.isNaN() || arate.isInfinite()) {
                                    arate = 0.0;
                                }
                                arate = arate * 100;
                                String actRate = String.valueOf(arate);
                                discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                Double actualAmt = actualAmount;
//                                actualAmt = actualAmt / 100;
                                if (actualAmt.isNaN() || actualAmt.isInfinite()) {
                                    actualAmt = 0.0;
                                }
                                String actAmt = String.valueOf(actualAmt);
                                discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                                Double prate = projectedAmount / projectedSales;

                                if (prate.isNaN() || prate.isInfinite()) {
                                    prate = 0.0;
                                }
                                prate = prate * 100;

                                String proRate = String.valueOf(prate);
                                discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                Double projectedAmtAmt = projectedAmount;
//                                projectedAmtAmt = projectedAmtAmt / 100;
                                if (projectedAmtAmt.isNaN() || projectedAmtAmt.isInfinite()) {
                                    projectedAmtAmt = 0.0;
                                }
                                String proAmount = String.valueOf(projectedAmtAmt);
                                discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                Double aRPU = actualAmount / actualRPU;
                                if (aRPU.isNaN() || aRPU.isInfinite()) {
                                    aRPU = 0.0;
                                }
                                String actRPU = String.valueOf(aRPU);
                                discountDto.addStringProperties(commonColumn + ACTUALRPU, actRPU != null && !NULL.equals(String.valueOf(actRPU)) && !StringUtils.EMPTY.equals(String.valueOf(actRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(actRPU)))) : HYPHEN);
                                Double pRPU = projectedAmount / projectedRPU;
                                if (pRPU.isNaN() || pRPU.isInfinite()) {
                                    pRPU = 0.0;
                                }
                                String proRPU = String.valueOf(pRPU);
                                discountDto.addStringProperties(commonColumn + PROJECTEDRPU, proRPU != null && !NULL.equals(String.valueOf(proRPU)) && !StringUtils.EMPTY.equals(String.valueOf(proRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(proRPU)))) : HYPHEN);
                                commonColumn = StringUtils.EMPTY;
                                if (!tmpList.isEmpty()) {
                                    for (int j = 0; j < tmpList.size(); j++) {
                                        String column = tmpList.get(j);
                                        commonColumn = column.replace(" ", StringUtils.EMPTY);
                                        String columns = commonColumn + ACTUALSRATE;
                                        discountDto.addStringProperties(columns, getFormattedValue(CUR_ZERO, NULL));
                                        String columns1 = commonColumn + ACTUALSAMOUNT;
                                        discountDto.addStringProperties(columns1, getFormattedValue(CUR_ZERO, NULL));
                                        String columns2 = commonColumn + PROJECTIONSAMOUNT;
                                        discountDto.addStringProperties(columns2, getFormattedValue(CUR_ZERO, NULL));
                                        String columns3 = commonColumn + PROJECTIONSRATE;
                                        discountDto.addStringProperties(columns3, getFormattedValue(CUR_ZERO, NULL));
                                        String columns4 = commonColumn + ACTUALRPU;
                                        discountDto.addStringProperties(columns4, getFormattedValue(CUR_ZERO, NULL));
                                        String columns5 = commonColumn + PROJECTEDRPU;
                                        discountDto.addStringProperties(columns5, getFormattedValue(CUR_ZERO, NULL));
                                    }
                                }
                                discountProjList.add(discountDto);

                                periodList.remove(discountDto.getGroup());
                            }
                        } else {
                            for (int i = 1; i < list.size(); i++) {
                                Object[] obj = (Object[]) list.get(i);
                                String selectedDiscount = String.valueOf(obj[8]);
                                tmpList.remove(selectedDiscount);
                                selectedDiscount = selectedDiscount.replace(" ", StringUtils.EMPTY);
                                int selectedYear = (Integer) obj[0];
                                if (currentYear == selectedYear) {
                                    if (currentDiscount.equalsIgnoreCase(selectedDiscount)) {
                                        if (obj[2] != null) {
                                            Double aSales = Double.parseDouble(String.valueOf(obj[2]));
                                            actualSales = actualSales + aSales;
                                        }
                                        if (obj[3] != null) {
                                            Double aAmount = Double.parseDouble(String.valueOf(obj[3]));
                                            actualAmount = actualAmount + aAmount;
                                        }
                                        if (obj[4] != null) {
                                            Double pSales = Double.parseDouble(String.valueOf(obj[4]));
                                            projectedSales = projectedSales + pSales;
                                        }
                                        if (obj[5] != null) {
                                            Double pAmount = Double.parseDouble(String.valueOf(obj[5]));
                                            projectedAmount = projectedAmount + pAmount;
                                        }
                                        if (obj[9] != null) {
                                            Double projRate = Double.parseDouble(String.valueOf(obj[9]));
                                            projectedRate = projectedRate + projRate;
                                        }
                                        if (obj[10] != null) {
                                            Double acRPU = Double.parseDouble(String.valueOf(obj[10]));
                                            actualRPU = actualRPU + acRPU;
                                        }
                                        if (obj[11] != null) {
                                            Double prRPU = Double.parseDouble(String.valueOf(obj[11]));
                                            projectedRPU = projectedRPU + prRPU;
                                        }
                                    } else {
                                        Double arate = actualAmount / actualSales;
                                        if (arate.isNaN() || arate.isInfinite()) {
                                            arate = 0.0;
                                        }
                                        arate = arate * 100;
                                        String actRate = String.valueOf(arate);
                                        discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double actualAmt = actualAmount;
//                                        actualAmt = actualAmt / 100;
                                        if (actualAmt.isNaN() || actualAmt.isInfinite()) {
                                            actualAmt = 0.0;
                                        }
                                        String actAmt = String.valueOf(actualAmt);
                                        discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                                        Double prate = projectedAmount / projectedSales;

                                        if (prate.isNaN() || prate.isInfinite()) {
                                            prate = 0.0;
                                        }
                                        prate = prate * 100;

                                        String proRate = String.valueOf(prate);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double projectedAmtAmt = projectedAmount;
//                                        projectedAmtAmt = projectedAmtAmt / 100;
                                        if (projectedAmtAmt.isNaN() || projectedAmtAmt.isInfinite()) {
                                            projectedAmtAmt = 0.0;
                                        }
                                        String proAmount = String.valueOf(projectedAmtAmt);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                        Double aRPU = actualAmount / actualRPU;
                                        if (aRPU.isNaN() || aRPU.isInfinite()) {
                                            aRPU = 0.0;
                                        }
                                        String actRPU = String.valueOf(aRPU);
                                        discountDto.addStringProperties(commonColumn + ACTUALRPU, actRPU != null && !NULL.equals(String.valueOf(actRPU)) && !StringUtils.EMPTY.equals(String.valueOf(actRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(actRPU)))) : HYPHEN);
                                        Double pRPU = projectedAmount / projectedRPU;
                                        if (pRPU.isNaN() || pRPU.isInfinite()) {
                                            pRPU = 0.0;
                                        }
                                        String proRPU = String.valueOf(pRPU);
                                        discountDto.addStringProperties(commonColumn + PROJECTEDRPU, proRPU != null && !NULL.equals(String.valueOf(proRPU)) && !StringUtils.EMPTY.equals(String.valueOf(proRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(proRPU)))) : HYPHEN);
                                        commonColumn = StringUtils.EMPTY;
                                        actualSales = 0;
                                        actualAmount = 0;
                                        projectedSales = 0;
                                        projectedAmount = 0;
                                        projectedRate = 0;
                                        actualRPU = 0;
                                        projectedRPU = 0;
                                        tmpList.remove(selectedDiscount);
                                        commonColumn = selectedDiscount.replace(" ", StringUtils.EMPTY);
                                        currentDiscount = selectedDiscount;
                                        if (obj[2] != null) {
                                            Double aSales = Double.parseDouble(String.valueOf(obj[2]));
                                            actualSales = actualSales + aSales;
                                        }
                                        if (obj[3] != null) {
                                            Double aAmount = Double.parseDouble(String.valueOf(obj[3]));
                                            actualAmount = actualAmount + aAmount;
                                        }
                                        if (obj[4] != null) {
                                            Double pSales = Double.parseDouble(String.valueOf(obj[4]));
                                            projectedSales = projectedSales + pSales;
                                        }
                                        if (obj[5] != null) {
                                            Double pAmount = Double.parseDouble(String.valueOf(obj[5]));
                                            projectedAmount = projectedAmount + pAmount;
                                        }
                                        if (obj[9] != null) {
                                            Double projRate = Double.parseDouble(String.valueOf(obj[9]));
                                            projectedRate = projectedRate + projRate;
                                        }
                                        if (obj[10] != null) {
                                            Double acRPU = Double.parseDouble(String.valueOf(obj[10]));
                                            actualRPU = actualRPU + acRPU;
                                        }
                                        if (obj[11] != null) {
                                            Double prRPU = Double.parseDouble(String.valueOf(obj[11]));
                                            projectedRPU = projectedRPU + prRPU;
                                        }
                                    }
                                } else {
                                    if (periodList.contains(discountDto.getGroup())) {
                                        Double arate = actualAmount / actualSales;
                                        if (arate.isNaN() || arate.isInfinite()) {
                                            arate = 0.0;
                                        }
                                        arate = arate * 100;
                                        String actRate = String.valueOf(arate);
                                        discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double actualAmt = actualAmount;
//                                        actualAmt = actualAmt / 100;
                                        if (actualAmt.isNaN() || actualAmt.isInfinite()) {
                                            actualAmt = 0.0;
                                        }
                                        String actAmt = String.valueOf(actualAmt);
                                        discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                                        Double prate = projectedAmount / projectedSales;

                                        if (prate.isNaN() || prate.isInfinite()) {
                                            prate = 0.0;
                                        }
                                        prate = prate * 100;

                                        String proRate = String.valueOf(prate);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double projectedAmtAmt = projectedAmount;
//                                        projectedAmtAmt = projectedAmtAmt / 100;
                                        if (projectedAmtAmt.isNaN() || projectedAmtAmt.isInfinite()) {
                                            projectedAmtAmt = 0.0;
                                        }
                                        String proAmount = String.valueOf(projectedAmtAmt);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                        Double aRPU = actualAmount / actualRPU;
                                        if (aRPU.isNaN() || aRPU.isInfinite()) {
                                            aRPU = 0.0;
                                        }
                                        String actRPU = String.valueOf(aRPU);
                                        discountDto.addStringProperties(commonColumn + ACTUALRPU, actRPU != null && !NULL.equals(String.valueOf(actRPU)) && !StringUtils.EMPTY.equals(String.valueOf(actRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(actRPU)))) : HYPHEN);
                                        Double pRPU = projectedAmount / projectedRPU;
                                        if (pRPU.isNaN() || pRPU.isInfinite()) {
                                            pRPU = 0.0;
                                        }
                                        String proRPU = String.valueOf(pRPU);
                                        discountDto.addStringProperties(commonColumn + PROJECTEDRPU, proRPU != null && !NULL.equals(String.valueOf(proRPU)) && !StringUtils.EMPTY.equals(String.valueOf(proRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(proRPU)))) : HYPHEN);

                                        if (!tmpList.isEmpty()) {
                                            for (int j = 0; j < tmpList.size(); j++) {
                                                String column = tmpList.get(j);
                                                commonColumn = column.replace(" ", StringUtils.EMPTY);
                                                String columns = commonColumn + ACTUALSRATE;
                                                discountDto.addStringProperties(columns, getFormattedValue(CUR_ZERO, NULL));
                                                String columns1 = commonColumn + ACTUALSAMOUNT;
                                                discountDto.addStringProperties(columns1, getFormattedValue(CUR_ZERO, NULL));
                                                String columns2 = commonColumn + PROJECTIONSAMOUNT;
                                                discountDto.addStringProperties(columns2, getFormattedValue(CUR_ZERO, NULL));
                                                String columns3 = commonColumn + PROJECTIONSRATE;
                                                discountDto.addStringProperties(columns3, getFormattedValue(CUR_ZERO, NULL));
                                                String columns4 = commonColumn + ACTUALRPU;
                                                discountDto.addStringProperties(columns4, getFormattedValue(CUR_ZERO, NULL));
                                                String columns5 = commonColumn + PROJECTEDRPU;
                                                discountDto.addStringProperties(columns5, getFormattedValue(CUR_ZERO, NULL));
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
                                    projectedRate = 0;
                                    actualRPU = 0;
                                    projectedRPU = 0;
                                    discountDto = new DiscountProjectionResultsDTO();
                                    currentDiscount = selectedDiscount;
                                    discountDto.setGroup(String.valueOf(selectedYear));
                                    currentYear = selectedYear;
                                    tmpList.remove(selectedDiscount);
                                    commonColumn = selectedDiscount.replace(" ", StringUtils.EMPTY);
                                    discountDto.setIsParent(ZERO);
                                    discountDto.setParent(0);
                                    if (obj[2] != null) {
                                        Double aSales = Double.parseDouble(String.valueOf(obj[2]));
                                        actualSales = actualSales + aSales;
                                    }
                                    if (obj[3] != null) {
                                        Double aAmount = Double.parseDouble(String.valueOf(obj[3]));
                                        actualAmount = actualAmount + aAmount;
                                    }
                                    if (obj[4] != null) {
                                        Double pSales = Double.parseDouble(String.valueOf(obj[4]));
                                        projectedSales = projectedSales + pSales;
                                    }
                                    if (obj[5] != null) {
                                        Double pAmount = Double.parseDouble(String.valueOf(obj[5]));
                                        projectedAmount = projectedAmount + pAmount;
                                    }
                                    if (obj[9] != null) {
                                        Double projRate = Double.parseDouble(String.valueOf(obj[9]));
                                        projectedRate = projectedRate + projRate;
                                    }
                                    if (obj[10] != null) {
                                        Double acRPU = Double.parseDouble(String.valueOf(obj[10]));
                                        actualRPU = actualRPU + acRPU;
                                    }
                                    if (obj[11] != null) {
                                        Double prRPU = Double.parseDouble(String.valueOf(obj[11]));
                                        projectedRPU = projectedRPU + prRPU;
                                    }
                                }
                                if ((i == list.size() - 1) && periodList.contains(discountDto.getGroup())) {
                                    Double arate = actualAmount / actualSales;
                                    if (arate.isNaN() || arate.isInfinite()) {
                                        arate = 0.0;
                                    }
                                    arate = arate * 100;
                                    String actRate = String.valueOf(arate);
                                    discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                    Double actualAmt = actualAmount;
//                                    actualAmt = actualAmt / 100;
                                    if (actualAmt.isNaN() || actualAmt.isInfinite()) {
                                        actualAmt = 0.0;
                                    }
                                    String actAmt = String.valueOf(actualAmt);
                                    discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);

                                    Double prate = projectedAmount / projectedSales;

                                    if (prate.isNaN() || prate.isInfinite()) {
                                        prate = 0.0;
                                    }
                                    prate = prate * 100;

                                    String proRate = String.valueOf(prate);
                                    discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                    Double projectedAmtAmt = projectedAmount;
//                                    projectedAmtAmt = projectedAmtAmt / 100;
                                    if (projectedAmtAmt.isNaN() || projectedAmtAmt.isInfinite()) {
                                        projectedAmtAmt = 0.0;
                                    }
                                    String proAmount = String.valueOf(projectedAmtAmt);
                                    discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                    Double aRPU = actualAmount / actualRPU;
                                    if (aRPU.isNaN() || aRPU.isInfinite()) {
                                        aRPU = 0.0;
                                    }
                                    String actRPU = String.valueOf(aRPU);
                                    discountDto.addStringProperties(commonColumn + ACTUALRPU, actRPU != null && !NULL.equals(String.valueOf(actRPU)) && !StringUtils.EMPTY.equals(String.valueOf(actRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(actRPU)))) : HYPHEN);
                                    Double pRPU = projectedAmount / projectedRPU;
                                    if (pRPU.isNaN() || pRPU.isInfinite()) {
                                        pRPU = 0.0;
                                    }
                                    String proRPU = String.valueOf(pRPU);
                                    discountDto.addStringProperties(commonColumn + PROJECTEDRPU, proRPU != null && !NULL.equals(String.valueOf(proRPU)) && !StringUtils.EMPTY.equals(String.valueOf(proRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(proRPU)))) : HYPHEN);

                                    if (!tmpList.isEmpty()) {
                                        for (int j = 0; j < tmpList.size(); j++) {
                                            String column = tmpList.get(j);
                                            commonColumn = column.replace(" ", StringUtils.EMPTY);
                                            String columns = commonColumn + ACTUALSRATE;
                                            discountDto.addStringProperties(columns, getFormattedValue(CUR_ZERO, NULL));
                                            String columns1 = commonColumn + ACTUALSAMOUNT;
                                            discountDto.addStringProperties(columns1, getFormattedValue(CUR_ZERO, NULL));
                                            String columns2 = commonColumn + PROJECTIONSAMOUNT;
                                            discountDto.addStringProperties(columns2, getFormattedValue(CUR_ZERO, NULL));
                                            String columns3 = commonColumn + PROJECTIONSRATE;
                                            discountDto.addStringProperties(columns3, getFormattedValue(CUR_ZERO, NULL));
                                            String columns4 = commonColumn + ACTUALRPU;
                                            discountDto.addStringProperties(columns4, getFormattedValue(CUR_ZERO, NULL));
                                            String columns5 = commonColumn + PROJECTEDRPU;
                                            discountDto.addStringProperties(columns5, getFormattedValue(CUR_ZERO, NULL));
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
                                    projDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, NULL));

                                    String discountActualAmount = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns1 = discountActualAmount + ACTUALSAMOUNT;
                                    projDTO.addStringProperties(columns1, getFormattedValue(CUR_ZERO, NULL));

                                    String discountProjectionAmount = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns2 = discountProjectionAmount + PROJECTIONSAMOUNT;
                                    projDTO.addStringProperties(columns2, getFormattedValue(CUR_ZERO, NULL));

                                    String discountProjectionRate = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns3 = discountProjectionRate + PROJECTIONSRATE;
                                    projDTO.addStringProperties(columns3, getFormattedValue(CUR_ZERO, NULL));

                                    String discountActualRPU = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns4 = discountActualRPU + ACTUALRPU;
                                    projDTO.addStringProperties(columns4, getFormattedValue(CUR_ZERO, NULL));

                                    String discountProjectionRPU = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns5 = discountProjectionRPU + PROJECTEDRPU;
                                    projDTO.addStringProperties(columns5, getFormattedValue(CUR_ZERO, NULL));
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
                        double projectedRate = 0;
                        double actualRPU = 0;
                        double projectedRPU = 0;
                        int frequencyDivision = 3;
                        tmpList.addAll(discountList);
                        List<String> periodList = new ArrayList<String>(projSelDTO.getPeriodList());
                        String commonColumn = StringUtils.EMPTY;
                        DiscountProjectionResultsDTO discountDto = new DiscountProjectionResultsDTO();
                        Object[] object = (Object[]) list.get(0);
                        String currentDiscount = String.valueOf(object[8]);
                        tmpList.remove(currentDiscount);
                        int currentYear = (Integer) object[0];
                        int currentQuarter = (Integer) object[6];
                        currentDiscount = currentDiscount.replace(" ", StringUtils.EMPTY);
                        commonColumn = currentDiscount;
                        discountDto.setGroup(Q_BIG + object[1] + " " + object[0]);
                        discountDto.setIsParent(ZERO);
                        if (object[2] != null) {
                            Double aSales = Double.parseDouble(String.valueOf(object[2]));
                            actualSales = actualSales + aSales;
                        }
                        if (object[3] != null) {
                            Double aAmount = Double.parseDouble(String.valueOf(object[3]));
                            actualAmount = actualAmount + aAmount;
                        }
                        if (object[4] != null) {
                            Double pSales = Double.parseDouble(String.valueOf(object[4]));
                            projectedSales = projectedSales + pSales;
                        }
                        if (object[5] != null) {
                            Double pAmount = Double.parseDouble(String.valueOf(object[5]));
                            projectedAmount = projectedAmount + pAmount;
                        }
                        if (object[9] != null) {
                            Double projRate = Double.parseDouble(String.valueOf(object[9]));
                            projectedRate = projectedRate + projRate;
                        }
                        if (object[10] != null) {
                            Double acRPU = Double.parseDouble(String.valueOf(object[10]));
                            actualRPU = actualRPU + acRPU;
                        }
                        if (object[11] != null) {
                            Double prRPU = Double.parseDouble(String.valueOf(object[11]));
                            projectedRPU = projectedRPU + prRPU;
                        }
                        if (list.size() == 1) {
                            Double arate = actualAmount / actualSales;
                            if (arate.isNaN() || arate.isInfinite()) {
                                arate = 0.0;
                            }
                            arate = arate * 100;
                            String actRate = String.valueOf(arate);
                            discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                            Double actualAmt = actualAmount;
//                            actualAmt = actualAmt / 100;
                            if (actualAmt.isNaN() || actualAmt.isInfinite()) {
                                actualAmt = 0.0;
                            }
                            String actAmt = String.valueOf(actualAmt);
                            discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                            Double prate = projectedAmount / projectedSales;
                            if (prate.isNaN() || prate.isInfinite()) {
                                prate = 0.0;
                            }
                            prate = prate * 100;

                            String proRate = String.valueOf(prate);
                            discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                            Double projectedAmtAmt = projectedAmount;
//                            projectedAmtAmt = projectedAmtAmt / 100;
                            if (projectedAmtAmt.isNaN() || projectedAmtAmt.isInfinite()) {
                                projectedAmtAmt = 0.0;
                            }
                            String proAmount = String.valueOf(projectedAmtAmt);
                            discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                            Double aRPU = actualAmount / actualRPU;
                            if (aRPU.isNaN() || aRPU.isInfinite()) {
                                aRPU = 0.0;
                            }
                            String actRPU = String.valueOf(aRPU);
                            discountDto.addStringProperties(commonColumn + ACTUALRPU, actRPU != null && !NULL.equals(String.valueOf(actRPU)) && !StringUtils.EMPTY.equals(String.valueOf(actRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(actRPU)))) : HYPHEN);
                            Double pRPU = projectedAmount / projectedRPU;
                            if (pRPU.isNaN() || pRPU.isInfinite()) {
                                pRPU = 0.0;
                            }
                            String proRPU = String.valueOf(pRPU);
                            discountDto.addStringProperties(commonColumn + PROJECTEDRPU, proRPU != null && !NULL.equals(String.valueOf(proRPU)) && !StringUtils.EMPTY.equals(String.valueOf(proRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(proRPU)))) : HYPHEN);

                            commonColumn = StringUtils.EMPTY;
                            if (!tmpList.isEmpty()) {
                                discountDto = putHyphenForDiscount(tmpList, discountDto);
                            }
                            discountProjList.add(discountDto);
                            tmpList.clear();
                            tmpList.addAll(discountList);
                        } else {
                            for (int i = 1; i < list.size(); i++) {
                                Object[] obj = (Object[]) list.get(i);
                                String selectedDiscount = String.valueOf(obj[8]);
                                tmpList.remove(selectedDiscount);
                                selectedDiscount = selectedDiscount.replace(" ", StringUtils.EMPTY);
                                int selectedYear = (Integer) obj[0];
                                int selectedQuarter = (Integer) obj[6];
                                if (currentYear == selectedYear && currentQuarter == selectedQuarter) {
                                    if (currentDiscount.equalsIgnoreCase(selectedDiscount)) {
                                        if (obj[2] != null) {
                                            Double aSales = Double.parseDouble(String.valueOf(obj[2]));
                                            actualSales = actualSales + aSales;
                                        }
                                        if (obj[3] != null) {
                                            Double aAmount = Double.parseDouble(String.valueOf(obj[3]));
                                            actualAmount = actualAmount + aAmount;
                                        }
                                        if (obj[4] != null) {
                                            Double pSales = Double.parseDouble(String.valueOf(obj[4]));
                                            projectedSales = projectedSales + pSales;
                                        }
                                        if (obj[5] != null) {
                                            Double pAmount = Double.parseDouble(String.valueOf(obj[5]));
                                            projectedAmount = projectedAmount + pAmount;
                                        }
                                        if (obj[9] != null) {
                                            Double projRate = Double.parseDouble(String.valueOf(obj[9]));
                                            projectedRate = projectedRate + projRate;
                                        }
                                        if (obj[10] != null) {
                                            Double acRPU = Double.parseDouble(String.valueOf(obj[10]));
                                            actualRPU = actualRPU + acRPU;
                                        }
                                        if (obj[11] != null) {
                                            Double prRPU = Double.parseDouble(String.valueOf(obj[11]));
                                            projectedRPU = projectedRPU + prRPU;
                                        }
                                    } else {
                                        Double arate = actualAmount / actualSales;
                                        if (arate.isNaN() || arate.isInfinite()) {
                                            arate = 0.0;
                                        }
                                        arate = arate * 100;
                                        String actRate = String.valueOf(arate);
                                        discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double actualAmt = actualAmount;
//                                        actualAmt = actualAmt / 100;
                                        if (actualAmt.isNaN() || actualAmt.isInfinite()) {
                                            actualAmt = 0.0;
                                        }
                                        String actAmt = String.valueOf(actualAmt);
                                        discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);

                                        Double prate = projectedAmount / projectedSales;

                                        if (prate.isNaN() || prate.isInfinite()) {
                                            prate = 0.0;
                                        }
                                        prate = prate * 100;

                                        String proRate = String.valueOf(prate);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double projectedAmtAmt = projectedAmount;
//                                        projectedAmtAmt = projectedAmtAmt / 100;
                                        if (projectedAmtAmt.isNaN() || projectedAmtAmt.isInfinite()) {
                                            projectedAmtAmt = 0.0;
                                        }
                                        String proAmount = String.valueOf(projectedAmtAmt);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                        Double aRPU = actualAmount / actualRPU;
                                        if (aRPU.isNaN() || aRPU.isInfinite()) {
                                            aRPU = 0.0;
                                        }
                                        String actRPU = String.valueOf(aRPU);
                                        discountDto.addStringProperties(commonColumn + ACTUALRPU, actRPU != null && !NULL.equals(String.valueOf(actRPU)) && !StringUtils.EMPTY.equals(String.valueOf(actRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(actRPU)))) : HYPHEN);
                                        Double pRPU = projectedAmount / projectedRPU;
                                        if (pRPU.isNaN() || pRPU.isInfinite()) {
                                            pRPU = 0.0;
                                        }
                                        String proRPU = String.valueOf(pRPU);
                                        discountDto.addStringProperties(commonColumn + PROJECTEDRPU, proRPU != null && !NULL.equals(String.valueOf(proRPU)) && !StringUtils.EMPTY.equals(String.valueOf(proRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(proRPU)))) : HYPHEN);

                                        commonColumn = StringUtils.EMPTY;
                                        actualSales = 0;
                                        actualAmount = 0;
                                        projectedSales = 0;
                                        projectedAmount = 0;
                                        projectedRate = 0;
                                        actualRPU = 0;
                                        projectedRPU = 0;
                                        commonColumn = selectedDiscount;

                                        currentDiscount = selectedDiscount;
                                        if (obj[2] != null) {
                                            Double aSales = Double.parseDouble(String.valueOf(obj[2]));
                                            actualSales = actualSales + aSales;
                                        }
                                        if (obj[3] != null) {
                                            Double aAmount = Double.parseDouble(String.valueOf(obj[3]));
                                            actualAmount = actualAmount + aAmount;
                                        }
                                        if (obj[4] != null) {
                                            Double pSales = Double.parseDouble(String.valueOf(obj[4]));
                                            projectedSales = projectedSales + pSales;
                                        }
                                        if (obj[5] != null) {
                                            Double pAmount = Double.parseDouble(String.valueOf(obj[5]));
                                            projectedAmount = projectedAmount + pAmount;
                                        }
                                        if (obj[9] != null) {
                                            Double projRate = Double.parseDouble(String.valueOf(obj[9]));
                                            projectedRate = projectedRate + projRate;
                                        }
                                        if (obj[10] != null) {
                                            Double acRPU = Double.parseDouble(String.valueOf(obj[10]));
                                            actualRPU = actualRPU + acRPU;
                                        }
                                        if (obj[11] != null) {
                                            Double prRPU = Double.parseDouble(String.valueOf(obj[11]));
                                            projectedRPU = projectedRPU + prRPU;
                                        }
                                    }
                                } else {
                                    String column = discountDto.getGroup().replace(" ", StringUtils.EMPTY);
                                    if (periodList.contains(String.valueOf(column).replace(Q_BIG, Q_SMALL))) {
                                        Double arate = actualAmount / actualSales;
                                        if (arate.isNaN() || arate.isInfinite()) {
                                            arate = 0.0;
                                        }
                                        arate = arate * 100;
                                        String actRate = String.valueOf(arate);
                                        discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double actualAmt = actualAmount;
//                                        actualAmt = actualAmt / 100;
                                        if (actualAmt.isNaN() || actualAmt.isInfinite()) {
                                            actualAmt = 0.0;
                                        }
                                        String actAmt = String.valueOf(actualAmt);
                                        discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);

                                        Double prate = projectedAmount / projectedSales;

                                        if (prate.isNaN() || prate.isInfinite()) {
                                            prate = 0.0;
                                        }
                                        prate = prate * 100;

                                        String proRate = String.valueOf(prate);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double projectedAmtAmt = projectedAmount;
//                                        projectedAmtAmt = projectedAmtAmt / 100;
                                        if (projectedAmtAmt.isNaN() || projectedAmtAmt.isInfinite()) {
                                            projectedAmtAmt = 0.0;
                                        }
                                        String proAmount = String.valueOf(projectedAmtAmt);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                        Double aRPU = actualAmount / actualRPU;
                                        if (aRPU.isNaN() || aRPU.isInfinite()) {
                                            aRPU = 0.0;
                                        }
                                        String actRPU = String.valueOf(aRPU);
                                        discountDto.addStringProperties(commonColumn + ACTUALRPU, actRPU != null && !NULL.equals(String.valueOf(actRPU)) && !StringUtils.EMPTY.equals(String.valueOf(actRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(actRPU)))) : HYPHEN);
                                        Double pRPU = projectedAmount / projectedRPU;
                                        if (pRPU.isNaN() || pRPU.isInfinite()) {
                                            pRPU = 0.0;
                                        }
                                        String proRPU = String.valueOf(pRPU);
                                        discountDto.addStringProperties(commonColumn + PROJECTEDRPU, proRPU != null && !NULL.equals(String.valueOf(proRPU)) && !StringUtils.EMPTY.equals(String.valueOf(proRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(proRPU)))) : HYPHEN);

                                        if (!tmpList.isEmpty()) {
                                            discountDto = putHyphenForDiscount(tmpList, discountDto);
                                        }
                                        discountProjList.add(discountDto);
                                        periodList.remove(String.valueOf(column).replace(Q_BIG, Q_SMALL));
                                        tmpList.clear();
                                        tmpList.addAll(discountList);
                                    }
                                    actualSales = 0;
                                    actualAmount = 0;
                                    projectedSales = 0;
                                    projectedAmount = 0;
                                    projectedRate = 0;
                                    actualRPU = 0;
                                    projectedRPU = 0;
                                    discountDto = new DiscountProjectionResultsDTO();
                                    discountDto.setGroup(Q_BIG + obj[1] + " " + obj[0]);
                                    currentYear = selectedYear;
                                    currentQuarter = selectedQuarter;
                                    tmpList.remove(selectedDiscount);
                                    commonColumn = selectedDiscount;
                                    currentDiscount = selectedDiscount;
                                    discountDto.setIsParent(ZERO);
                                    if (obj[2] != null) {
                                        Double aSales = Double.parseDouble(String.valueOf(obj[2]));
                                        actualSales = actualSales + aSales;
                                    }
                                    if (obj[3] != null) {
                                        Double aAmount = Double.parseDouble(String.valueOf(obj[3]));
                                        actualAmount = actualAmount + aAmount;
                                    }
                                    if (obj[4] != null) {
                                        Double pSales = Double.parseDouble(String.valueOf(obj[4]));
                                        projectedSales = projectedSales + pSales;
                                    }
                                    if (obj[5] != null) {
                                        Double pAmount = Double.parseDouble(String.valueOf(obj[5]));
                                        projectedAmount = projectedAmount + pAmount;
                                    }
                                    if (obj[9] != null) {
                                        Double projRate = Double.parseDouble(String.valueOf(obj[9]));
                                        projectedRate = projectedRate + projRate;
                                    }
                                    if (obj[10] != null) {
                                        Double acRPU = Double.parseDouble(String.valueOf(obj[10]));
                                        actualRPU = actualRPU + acRPU;
                                    }
                                    if (obj[11] != null) {
                                        Double prRPU = Double.parseDouble(String.valueOf(obj[11]));
                                        projectedRPU = projectedRPU + prRPU;
                                    }
                                }
                                if (i == list.size() - 1) {
                                    String column = discountDto.getGroup().replace(" ", StringUtils.EMPTY);
                                    if (periodList.contains(String.valueOf(column).replace(Q_BIG, Q_SMALL))) {
                                        Double arate = actualAmount / actualSales;
                                        if (arate.isNaN() || arate.isInfinite()) {
                                            arate = 0.0;
                                        }
                                        arate = arate * 100;
                                        String actRate = String.valueOf(arate);
                                        discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double actualAmt = actualAmount;
//                                        actualAmt = actualAmt / 100;
                                        if (actualAmt.isNaN() || actualAmt.isInfinite()) {
                                            actualAmt = 0.0;
                                        }
                                        String actAmt = String.valueOf(actualAmt);
                                        discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);

                                        Double prate = projectedAmount / projectedSales;

                                        if (prate.isNaN() || prate.isInfinite()) {
                                            prate = 0.0;
                                        }
                                        prate = prate * 100;

                                        String proRate = String.valueOf(prate);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double projectedAmtAmt = projectedAmount;
//                                        projectedAmtAmt = projectedAmtAmt / 100;
                                        if (projectedAmtAmt.isNaN() || projectedAmtAmt.isInfinite()) {
                                            projectedAmtAmt = 0.0;
                                        }
                                        String proAmount = String.valueOf(projectedAmtAmt);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                        Double aRPU = actualAmount / actualRPU;
                                        if (aRPU.isNaN() || aRPU.isInfinite()) {
                                            aRPU = 0.0;
                                        }
                                        String actRPU = String.valueOf(aRPU);
                                        discountDto.addStringProperties(commonColumn + ACTUALRPU, actRPU != null && !NULL.equals(String.valueOf(actRPU)) && !StringUtils.EMPTY.equals(String.valueOf(actRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(actRPU)))) : HYPHEN);
                                        Double pRPU = projectedAmount / projectedRPU;
                                        if (pRPU.isNaN() || pRPU.isInfinite()) {
                                            pRPU = 0.0;
                                        }
                                        String proRPU = String.valueOf(pRPU);
                                        discountDto.addStringProperties(commonColumn + PROJECTEDRPU, proRPU != null && !NULL.equals(String.valueOf(proRPU)) && !StringUtils.EMPTY.equals(String.valueOf(proRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(proRPU)))) : HYPHEN);

                                        if (!tmpList.isEmpty()) {
                                            discountDto = putHyphenForDiscount(tmpList, discountDto);
                                        }
                                        discountProjList.add(discountDto);
                                        tmpList.clear();
                                        tmpList.addAll(discountList);
                                        periodList.remove(String.valueOf(column).replace(Q_BIG, Q_SMALL));
                                    }
                                }
                            }
                        }
                        if (!periodList.isEmpty() && !projSelDTO.getDiscountNameList().isEmpty()) {
                            for (int i = 0; i < periodList.size(); i++) {
                                DiscountProjectionResultsDTO projDTO = new DiscountProjectionResultsDTO();
                                projDTO.setParent(0);
                                projDTO.setProjectionTotal(1);
                                String group = String.valueOf(periodList.get(i).replace(Q_SMALL, Q_BIG));
                                String year = group.substring(group.length() - 4, group.length());
                                String frequency = group.replace(year, StringUtils.EMPTY);
                                group = frequency + " " + year;
                                projDTO.setGroup(group);
                                for (String discount : projSelDTO.getDiscountNameList()) {
                                    String discountRate = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns = discountRate + ACTUALSRATE;
                                    projDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, NULL));
                                    String discountActualAmount = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns1 = discountActualAmount + ACTUALSAMOUNT;
                                    projDTO.addStringProperties(columns1, getFormattedValue(CUR_ZERO, NULL));
                                    String discountProjectionAmount = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns2 = discountProjectionAmount + PROJECTIONSAMOUNT;
                                    projDTO.addStringProperties(columns2, getFormattedValue(CUR_ZERO, NULL));

                                    String discountProjectionRate = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns3 = discountProjectionRate + PROJECTIONSRATE;
                                    projDTO.addStringProperties(columns3, getFormattedValue(CUR_ZERO, NULL));

                                    String discountActualRPU = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns4 = discountActualRPU + ACTUALRPU;
                                    projDTO.addStringProperties(columns4, getFormattedValue(CUR_ZERO, NULL));

                                    String discountProjectionRPU = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns5 = discountProjectionRPU + PROJECTEDRPU;
                                    projDTO.addStringProperties(columns5, getFormattedValue(CUR_ZERO, NULL));
                                }
                                discountProjList.add(projDTO);
                            }
                        }
                    }
                    if (SEMI_ANNUALLY.getConstant().equals(frequ)) {
                        double actualSales = 0;
                        double actualAmount = 0;
                        double projectedSales = 0;
                        double projectedAmount = 0;
                        double projectedRate = 0;
                        double actualRPU = 0;
                        double projectedRPU = 0;
                        int frequencyDivision = 6;
                        String commonColumn = StringUtils.EMPTY;
                        List<String> periodList = new ArrayList<String>(projSelDTO.getPeriodList());
                        tmpList.addAll(discountList);
                        DiscountProjectionResultsDTO discountDto = new DiscountProjectionResultsDTO();
                        Object[] object = (Object[]) list.get(0);
                        String currentDiscount = String.valueOf(object[8]);
                        tmpList.remove(currentDiscount);
                        int currentYear = (Integer) object[0];
                        int currentQuarter = (Integer) object[6];
                        commonColumn = currentDiscount.replace(" ", StringUtils.EMPTY);
                        discountDto.setGroup(Constant.S + object[1] + " " + object[0]);
                        discountDto.setIsParent(ZERO);
                        if (object[2] != null) {
                            Double aSales = Double.parseDouble(String.valueOf(object[2]));
                            actualSales = actualSales + aSales;
                        }
                        if (object[3] != null) {
                            Double aAmount = Double.parseDouble(String.valueOf(object[3]));
                            actualAmount = actualAmount + aAmount;
                        }
                        if (object[4] != null) {
                            Double pSales = Double.parseDouble(String.valueOf(object[4]));
                            projectedSales = projectedSales + pSales;
                        }
                        if (object[5] != null) {
                            Double pAmount = Double.parseDouble(String.valueOf(object[5]));
                            projectedAmount = projectedAmount + pAmount;
                        }
                        if (object[9] != null) {
                            Double pAmount = Double.parseDouble(String.valueOf(object[9]));
                            projectedRate = projectedRate + pAmount;
                        }
                        if (object[10] != null) {
                            Double acRPU = Double.parseDouble(String.valueOf(object[10]));
                            actualRPU = actualRPU + acRPU;
                        }
                        if (object[11] != null) {
                            Double prRPU = Double.parseDouble(String.valueOf(object[11]));
                            projectedRPU = projectedRPU + prRPU;
                        }
                        if (list.size() == 1) {
                            Double arate = actualAmount / actualSales;
                            if (arate.isNaN()) {
                                arate = 0.0;
                            }
                            arate = arate * 100;
                            String actRate = String.valueOf(arate);
                            discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                            Double actualAmt = actualAmount;
//                            actualAmt = actualAmt / 100;
                            if (actualAmt.isNaN() || actualAmt.isNaN()) {
                                actualAmt = 0.0;
                            }
                            String actAmt = String.valueOf(actualAmt);
                            discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);

                            Double prate = projectedAmount / projectedSales;

                            if (prate.isNaN() || prate.isInfinite()) {
                                prate = 0.0;
                            }
                            prate = prate * 100;

                            String proRate = String.valueOf(prate);
                            discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                            Double projectedAmtAmt = projectedAmount;
//                            projectedAmtAmt = projectedAmtAmt / 100;
                            if (projectedAmtAmt.isNaN() || projectedAmtAmt.isNaN()) {
                                projectedAmtAmt = 0.0;
                            }
                            String proAmount = String.valueOf(projectedAmtAmt);
                            discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                            Double aRPU = actualAmount / actualRPU;
                            if (aRPU.isNaN()) {
                                aRPU = 0.0;
                            }
                            String actRPU = String.valueOf(aRPU);
                            discountDto.addStringProperties(commonColumn + ACTUALRPU, actRPU != null && !NULL.equals(String.valueOf(actRPU)) && !StringUtils.EMPTY.equals(String.valueOf(actRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(actRPU)))) : HYPHEN);
                            Double pRPU = projectedAmount / projectedRPU;
                            if (pRPU.isNaN() || pRPU.isNaN()) {
                                pRPU = 0.0;
                            }
                            String proRPU = String.valueOf(pRPU);
                            discountDto.addStringProperties(commonColumn + PROJECTEDRPU, proRPU != null && !NULL.equals(String.valueOf(proRPU)) && !StringUtils.EMPTY.equals(String.valueOf(proRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(proRPU)))) : HYPHEN);

                            commonColumn = StringUtils.EMPTY;
                            if (!tmpList.isEmpty()) {
                                discountDto = putHyphenForDiscount(tmpList, discountDto);
                            }
                            discountProjList.add(discountDto);
                            tmpList.clear();
                            tmpList.addAll(discountList);
                        } else {
                            for (int i = 1; i < list.size(); i++) {
                                Object[] obj = (Object[]) list.get(i);
                                String selectedDiscount = String.valueOf(obj[8]);
                                int selectedYear = (Integer) obj[0];
                                int selectedQuarter = (Integer) obj[6];
                                if (currentYear == selectedYear && currentQuarter == selectedQuarter) {
                                    if (currentDiscount.equalsIgnoreCase(selectedDiscount)) {
                                        if (obj[2] != null) {
                                            Double aSales = Double.parseDouble(String.valueOf(obj[2]));
                                            actualSales = actualSales + aSales;
                                        }
                                        if (obj[3] != null) {
                                            Double aAmount = Double.parseDouble(String.valueOf(obj[3]));
                                            actualAmount = actualAmount + aAmount;
                                        }
                                        if (obj[4] != null) {
                                            Double pSales = Double.parseDouble(String.valueOf(obj[4]));
                                            projectedSales = projectedSales + pSales;
                                        }
                                        if (obj[5] != null) {
                                            Double pAmount = Double.parseDouble(String.valueOf(obj[5]));
                                            projectedAmount = projectedAmount + pAmount;
                                        }
                                        if (obj[9] != null) {
                                            Double pAmount = Double.parseDouble(String.valueOf(obj[9]));
                                            projectedRate = projectedRate + pAmount;
                                        }
                                        if (obj[10] != null) {
                                            Double acRPU = Double.parseDouble(String.valueOf(obj[10]));
                                            actualRPU = actualRPU + acRPU;
                                        }
                                        if (obj[11] != null) {
                                            Double prRPU = Double.parseDouble(String.valueOf(obj[11]));
                                            projectedRPU = projectedRPU + prRPU;
                                        }
                                    } else {
                                        Double arate = actualAmount / actualSales;
                                        if (arate.isNaN() || arate.isNaN()) {
                                            arate = 0.0;
                                        }
                                        arate = arate * 100;
                                        String actRate = String.valueOf(arate);
                                        discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double actualAmt = actualAmount;
//                                        actualAmt = actualAmt / 100;
                                        if (actualAmt.isNaN() || actualAmt.isNaN()) {
                                            actualAmt = 0.0;
                                        }
                                        String actAmt = String.valueOf(actualAmt);
                                        discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);

                                        Double prate = projectedAmount / projectedSales;

                                        if (prate.isNaN() || prate.isInfinite()) {
                                            prate = 0.0;
                                        }
                                        prate = prate / (frequencyDivision * projSelDTO.getCcpCount());
                                        String proRate = String.valueOf(prate);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double projectedAmtAmt = projectedAmount;
                                        projectedAmtAmt = projectedAmtAmt / 100;
                                        if (projectedAmtAmt.isNaN() || projectedAmtAmt.isNaN()) {
                                            projectedAmtAmt = 0.0;
                                        }
                                        String proAmount = String.valueOf(projectedAmtAmt);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                        Double aRPU = actualAmount / actualRPU;
                                        if (aRPU.isNaN() || aRPU.isInfinite()) {
                                            aRPU = 0.0;
                                        }
                                        String actRPU = String.valueOf(aRPU);
                                        discountDto.addStringProperties(commonColumn + ACTUALRPU, actRPU != null && !NULL.equals(String.valueOf(actRPU)) && !StringUtils.EMPTY.equals(String.valueOf(actRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(actRPU)))) : HYPHEN);
                                        Double pRPU = projectedAmount / projectedRPU;
                                        if (pRPU.isNaN() || pRPU.isInfinite()) {
                                            pRPU = 0.0;
                                        }
                                        String proRPU = String.valueOf(pRPU);
                                        discountDto.addStringProperties(commonColumn + PROJECTEDRPU, proRPU != null && !NULL.equals(String.valueOf(proRPU)) && !StringUtils.EMPTY.equals(String.valueOf(proRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(proRPU)))) : HYPHEN);

                                        commonColumn = StringUtils.EMPTY;
                                        actualSales = 0;
                                        actualAmount = 0;
                                        projectedSales = 0;
                                        projectedAmount = 0;
                                        projectedRate = 0;
                                        actualRPU = 0;
                                        projectedRPU = 0;
                                        currentDiscount = selectedDiscount;
                                        tmpList.remove(selectedDiscount);
                                        commonColumn = selectedDiscount.replace(" ", StringUtils.EMPTY);
                                        if (obj[2] != null) {
                                            Double aSales = Double.parseDouble(String.valueOf(obj[2]));
                                            actualSales = actualSales + aSales;
                                        }
                                        if (obj[3] != null) {
                                            Double aAmount = Double.parseDouble(String.valueOf(obj[3]));
                                            actualAmount = actualAmount + aAmount;
                                        }
                                        if (obj[4] != null) {
                                            Double pSales = Double.parseDouble(String.valueOf(obj[4]));
                                            projectedSales = projectedSales + pSales;
                                        }
                                        if (obj[5] != null) {
                                            Double pAmount = Double.parseDouble(String.valueOf(obj[5]));
                                            projectedAmount = projectedAmount + pAmount;
                                        }
                                        if (obj[9] != null) {
                                            Double pAmount = Double.parseDouble(String.valueOf(obj[9]));
                                            projectedRate = projectedRate + pAmount;
                                        }
                                        if (obj[10] != null) {
                                            Double acRPU = Double.parseDouble(String.valueOf(obj[10]));
                                            actualRPU = actualRPU + acRPU;
                                        }
                                        if (obj[11] != null) {
                                            Double prRPU = Double.parseDouble(String.valueOf(obj[11]));
                                            projectedRPU = projectedRPU + prRPU;
                                        }
                                    }
                                } else {
                                    String column = discountDto.getGroup().replace(" ", StringUtils.EMPTY);
                                    if (periodList.contains(String.valueOf(column).replace(Constant.S, Constant.S_SMALL))) {
                                        Double arate = actualAmount / actualSales;
                                        if (arate.isNaN() || arate.isInfinite()) {
                                            arate = 0.0;
                                        }
                                        arate = arate * 100;
                                        String actRate = String.valueOf(arate);
                                        discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double actualAmt = arate * actualSales;
                                        actualAmt = actualAmt / 100;
                                        if (actualAmt.isNaN() || actualAmt.isInfinite()) {
                                            actualAmt = 0.0;
                                        }
                                        String actAmt = String.valueOf(actualAmt);
                                        discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                                        Double prate = projectedAmount / projectedSales;

                                        if (prate.isNaN() || prate.isInfinite()) {
                                            prate = 0.0;
                                        }
                                        prate = prate / (frequencyDivision * projSelDTO.getCcpCount());
                                        String proRate = String.valueOf(prate);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double projectedAmtAmt = projectedAmount;
                                        projectedAmtAmt = projectedAmtAmt / 100;
                                        if (projectedAmtAmt.isNaN() || projectedAmtAmt.isInfinite()) {
                                            projectedAmtAmt = 0.0;
                                        }
                                        String proAmount = String.valueOf(projectedAmtAmt);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                        Double aRPU = actualAmount / actualRPU;
                                        if (aRPU.isNaN() || aRPU.isInfinite()) {
                                            aRPU = 0.0;
                                        }
                                        String actRPU = String.valueOf(aRPU);
                                        discountDto.addStringProperties(commonColumn + ACTUALRPU, actRPU != null && !NULL.equals(String.valueOf(actRPU)) && !StringUtils.EMPTY.equals(String.valueOf(actRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(actRPU)))) : HYPHEN);
                                        Double pRPU = projectedAmount / projectedRPU;
                                        if (pRPU.isNaN() || pRPU.isInfinite()) {
                                            pRPU = 0.0;
                                        }
                                        String proRPU = String.valueOf(pRPU);
                                        discountDto.addStringProperties(commonColumn + PROJECTEDRPU, proRPU != null && !NULL.equals(String.valueOf(proRPU)) && !StringUtils.EMPTY.equals(String.valueOf(proRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(proRPU)))) : HYPHEN);

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
                                    projectedRate = 0;
                                    actualRPU = 0;
                                    projectedRPU = 0;
                                    discountDto = new DiscountProjectionResultsDTO();
                                    discountDto.setGroup(Constant.S + obj[1] + " " + obj[0]);
                                    currentYear = selectedYear;
                                    currentQuarter = selectedQuarter;
                                    currentDiscount = selectedDiscount;
                                    tmpList.remove(selectedDiscount);
                                    commonColumn = selectedDiscount.replace(" ", StringUtils.EMPTY);
                                    discountDto.setIsParent(ZERO);
                                    if (obj[2] != null) {
                                        Double aSales = Double.parseDouble(String.valueOf(obj[2]));
                                        actualSales = actualSales + aSales;
                                    }
                                    if (obj[3] != null) {
                                        Double aAmount = Double.parseDouble(String.valueOf(obj[3]));
                                        actualAmount = actualAmount + aAmount;
                                    }
                                    if (obj[4] != null) {
                                        Double pSales = Double.parseDouble(String.valueOf(obj[4]));
                                        projectedSales = projectedSales + pSales;
                                    }
                                    if (obj[5] != null) {
                                        Double pAmount = Double.parseDouble(String.valueOf(obj[5]));
                                        projectedAmount = projectedAmount + pAmount;
                                    }
                                    if (obj[9] != null) {
                                        Double pAmount = Double.parseDouble(String.valueOf(obj[9]));
                                        projectedRate = projectedRate + pAmount;
                                    }
                                    if (obj[10] != null) {
                                        Double acRPU = Double.parseDouble(String.valueOf(obj[10]));
                                        actualRPU = actualRPU + acRPU;
                                    }
                                    if (obj[11] != null) {
                                        Double prRPU = Double.parseDouble(String.valueOf(obj[11]));
                                        projectedRPU = projectedRPU + prRPU;
                                    }
                                }
                                if (i == list.size() - 1) {
                                    String column = discountDto.getGroup().replace(" ", StringUtils.EMPTY);
                                    if (periodList.contains(String.valueOf(column).replace(Constant.S, Constant.S_SMALL))) {
                                        Double arate = actualAmount / actualSales;
                                        if (arate.isNaN()) {
                                            arate = 0.0;
                                        }
                                        arate = arate * 100;
                                        String actRate = String.valueOf(arate);
                                        discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double actualAmt = arate * actualSales;
                                        actualAmt = actualAmt / 100;
                                        if (actualAmt.isNaN() || actualAmt.isInfinite()) {
                                            actualAmt = 0.0;
                                        }
                                        String actAmt = String.valueOf(actualAmt);
                                        discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);

                                        Double prate = projectedAmount / projectedSales;

                                        if (prate.isNaN() || prate.isInfinite()) {
                                            prate = 0.0;
                                        }
                                        prate = prate / (frequencyDivision * projSelDTO.getCcpCount());
                                        String proRate = String.valueOf(prate);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double projectedAmtAmt = projectedAmount;
                                        projectedAmtAmt = projectedAmtAmt / 100;
                                        if (projectedAmtAmt.isNaN() || projectedAmtAmt.isInfinite()) {
                                            projectedAmtAmt = 0.0;
                                        }
                                        String proAmount = String.valueOf(projectedAmtAmt);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                        Double aRPU = actualAmount / actualRPU;
                                        if (aRPU.isNaN() || aRPU.isInfinite()) {
                                            aRPU = 0.0;
                                        }
                                        String actRPU = String.valueOf(aRPU);
                                        discountDto.addStringProperties(commonColumn + ACTUALRPU, actRPU != null && !NULL.equals(String.valueOf(actRPU)) && !StringUtils.EMPTY.equals(String.valueOf(actRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(actRPU)))) : HYPHEN);
                                        Double pRPU = projectedAmount / projectedRPU;
                                        if (pRPU.isNaN()|| aRPU.isInfinite()) {
                                            pRPU = 0.0;
                                        }
                                        String proRPU = String.valueOf(pRPU);
                                        discountDto.addStringProperties(commonColumn + PROJECTEDRPU, proRPU != null && !NULL.equals(String.valueOf(proRPU)) && !StringUtils.EMPTY.equals(String.valueOf(proRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(proRPU)))) : HYPHEN);

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
                                String year = group.substring(group.length() - 4, group.length());
                                String frequency = group.replace(year, StringUtils.EMPTY);
                                group = frequency + " " + year;
                                projDTO.setGroup(group);
                                for (String discount : projSelDTO.getDiscountNameList()) {
                                    String discountRate = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns = discountRate + ACTUALSRATE;
                                    projDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, NULL));

                                    String discountActualAmount = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns1 = discountActualAmount + ACTUALSAMOUNT;
                                    projDTO.addStringProperties(columns1, getFormattedValue(CUR_ZERO, NULL));

                                    String discountProjectionAmount = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns2 = discountProjectionAmount + PROJECTIONSAMOUNT;
                                    projDTO.addStringProperties(columns2, getFormattedValue(CUR_ZERO, NULL));

                                    String discountProjectionRate = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns3 = discountProjectionRate + PROJECTIONSRATE;
                                    projDTO.addStringProperties(columns3, getFormattedValue(CUR_ZERO, NULL));

                                    String discountActualRPU = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns4 = discountActualRPU + ACTUALRPU;
                                    projDTO.addStringProperties(columns4, getFormattedValue(CUR_ZERO, NULL));

                                    String discountProjectionRPU = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns5 = discountProjectionRPU + PROJECTEDRPU;
                                    projDTO.addStringProperties(columns5, getFormattedValue(CUR_ZERO, NULL));
                                }
                                discountProjList.add(projDTO);
                            }
                        }
                    }
                    if (MONTHLY.getConstant().equals(frequ)) {
                        double actualSales = 0;
                        double actualAmount = 0;
                        double projectedSales = 0;
                        double projectedAmount = 0;
                        double projectedRate = 0;
                        double actualRPU = 0;
                        double projectedRPU = 0;
                        loadMap();
                        List<String> periodList = new ArrayList<String>(projSelDTO.getPeriodList());
                        String commonColumn = StringUtils.EMPTY;
                        tmpList.addAll(discountList);
                        DiscountProjectionResultsDTO discountDto = new DiscountProjectionResultsDTO();
                        Object[] object = (Object[]) list.get(0);
                        String currentDiscount = String.valueOf(object[8]);
                        int currentYear = (Integer) object[0];
                        int currentMonth = (Integer) object[6];
                        tmpList.remove(currentDiscount);
                        commonColumn = currentDiscount.replace(" ", StringUtils.EMPTY);
                        discountDto.setGroup(CommonUtils.BUSINESS_PROCESS_INDICATOR_MANDATED + object[1] + " " + object[0]);
                        discountDto.setIsParent(ZERO);
                        if (object[2] != null) {
                            Double aSales = Double.parseDouble(String.valueOf(object[2]));
                            actualSales = actualSales + aSales;
                        }
                        if (object[3] != null) {
                            Double aAmount = Double.parseDouble(String.valueOf(object[3]));
                            actualAmount = actualAmount + aAmount;
                        }
                        if (object[4] != null) {
                            Double pSales = Double.parseDouble(String.valueOf(object[4]));
                            projectedSales = projectedSales + pSales;
                        }
                        if (object[5] != null) {
                            Double pAmount = Double.parseDouble(String.valueOf(object[5]));
                            projectedAmount = projectedAmount + pAmount;
                        }
                        if (object[9] != null) {
                            Double pAmount = Double.parseDouble(String.valueOf(object[9]));
                            projectedRate = projectedRate + pAmount;
                        }
                        if (object[10] != null) {
                            Double acRPU = Double.parseDouble(String.valueOf(object[10]));
                            actualRPU = actualRPU + acRPU;
                        }
                        if (object[11] != null) {
                            Double prRPU = Double.parseDouble(String.valueOf(object[11]));
                            projectedRPU = projectedRPU + prRPU;
                        }
                        if (list.size() == 1) {
                            Double arate = actualAmount / actualSales;
                            if (arate.isNaN()) {
                                arate = 0.0;
                            }
                            arate = arate * 100;
                            String actRate = String.valueOf(arate);
                            discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                            Double actualAmt = arate * actualSales;
                            actualAmt = actualAmt / 100;
                            if (actualAmt.isNaN() || actualAmt.isInfinite()) {
                                actualAmt = 0.0;
                            }
                            String actAmt = String.valueOf(actualAmt);
                            discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);

                            Double prate = projectedAmount / projectedSales;

                            if (prate.isNaN() || prate.isInfinite()) {
                                prate = 0.0;
                            }
                            prate = prate * 100;

                            String proRate = String.valueOf(prate);
                            discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                            Double projectedAmtAmt = projectedAmount;
                            projectedAmtAmt = projectedAmtAmt / 100;
                            if (projectedAmtAmt.isNaN() || projectedAmtAmt.isInfinite()) {
                                projectedAmtAmt = 0.0;
                            }
                            String proAmount = String.valueOf(projectedAmtAmt);
                            discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                            Double aRPU = actualAmount / actualRPU;
                            if (aRPU.isNaN() || aRPU.isInfinite()) {
                                aRPU = 0.0;
                            }
                            String actRPU = String.valueOf(aRPU);
                            discountDto.addStringProperties(commonColumn + ACTUALRPU, actRPU != null && !NULL.equals(String.valueOf(actRPU)) && !StringUtils.EMPTY.equals(String.valueOf(actRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(actRPU)))) : HYPHEN);
                            Double pRPU = projectedAmount / projectedRPU;
                            if (pRPU.isNaN() || pRPU.isInfinite()) {
                                pRPU = 0.0;
                            }
                            String proRPU = String.valueOf(pRPU);
                            discountDto.addStringProperties(commonColumn + PROJECTEDRPU, proRPU != null && !NULL.equals(String.valueOf(proRPU)) && !StringUtils.EMPTY.equals(String.valueOf(proRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(proRPU)))) : HYPHEN);

                            commonColumn = StringUtils.EMPTY;
                            if (!tmpList.isEmpty()) {
                                discountDto = putHyphenForDiscount(tmpList, discountDto);
                            }
                            discountProjList.add(discountDto);
                            tmpList.clear();
                            tmpList.addAll(discountList);
                        } else {
                            for (int i = 1; i < list.size(); i++) {
                                Object[] obj = (Object[]) list.get(i);
                                String selectedDiscount = String.valueOf(obj[8]);
                                int selectedYear = (Integer) obj[0];
                                int selectedMonth = (Integer) obj[6];
                                if (currentYear == selectedYear && currentMonth == selectedMonth) {
                                    if (currentDiscount.equalsIgnoreCase(selectedDiscount)) {
                                        if (obj[2] != null) {
                                            Double aSales = Double.parseDouble(String.valueOf(obj[2]));
                                            actualSales = actualSales + aSales;
                                        }
                                        if (obj[3] != null) {
                                            Double aAmount = Double.parseDouble(String.valueOf(obj[3]));
                                            actualAmount = actualAmount + aAmount;
                                        }
                                        if (obj[4] != null) {
                                            Double pSales = Double.parseDouble(String.valueOf(obj[4]));
                                            projectedSales = projectedSales + pSales;
                                        }
                                        if (obj[5] != null) {
                                            Double pAmount = Double.parseDouble(String.valueOf(obj[5]));
                                            projectedAmount = projectedAmount + pAmount;
                                        }
                                        if (obj[9] != null) {
                                            Double pAmount = Double.parseDouble(String.valueOf(obj[9]));
                                            projectedRate = projectedRate + pAmount;
                                        }
                                        if (obj[10] != null) {
                                            Double acRPU = Double.parseDouble(String.valueOf(obj[10]));
                                            actualRPU = actualRPU + acRPU;
                                        }
                                        if (obj[11] != null) {
                                            Double prRPU = Double.parseDouble(String.valueOf(obj[11]));
                                            projectedRPU = projectedRPU + prRPU;
                                        }
                                    } else {
                                        Double arate = actualAmount / actualSales;
                                        if (arate.isNaN() || arate.isInfinite()) {
                                            arate = 0.0;
                                        }
                                        arate = arate * 100;
                                        String actRate = String.valueOf(arate);
                                        discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double actualAmt = arate * actualSales;
                                        actualAmt = actualAmt / 100;
                                        if (actualAmt.isNaN() || actualAmt.isInfinite()) {
                                            actualAmt = 0.0;
                                        }
                                        String actAmt = String.valueOf(actualAmt);
                                        discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);

                                        Double prate = projectedAmount / projectedSales;

                                        if (prate.isNaN() || prate.isInfinite()) {
                                            prate = 0.0;
                                        }
                                        prate = prate * 100;

                                        String proRate = String.valueOf(prate);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double projectedAmtAmt = projectedAmount;
                                        projectedAmtAmt = projectedAmtAmt / 100;
                                        if (projectedAmtAmt.isNaN() || projectedAmtAmt.isInfinite()) {
                                            projectedAmtAmt = 0.0;
                                        }
                                        String proAmount = String.valueOf(projectedAmtAmt);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                        Double aRPU = actualAmount / actualRPU;
                                        if (aRPU.isNaN() || aRPU.isInfinite()) {
                                            aRPU = 0.0;
                                        }
                                        String actRPU = String.valueOf(aRPU);
                                        discountDto.addStringProperties(commonColumn + ACTUALRPU, actRPU != null && !NULL.equals(String.valueOf(actRPU)) && !StringUtils.EMPTY.equals(String.valueOf(actRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(actRPU)))) : HYPHEN);
                                        Double pRPU = projectedAmount / projectedRPU;
                                        if (pRPU.isNaN() || pRPU.isInfinite()) {
                                            pRPU = 0.0;
                                        }
                                        String proRPU = String.valueOf(pRPU);
                                        discountDto.addStringProperties(commonColumn + PROJECTEDRPU, proRPU != null && !NULL.equals(String.valueOf(proRPU)) && !StringUtils.EMPTY.equals(String.valueOf(proRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(proRPU)))) : HYPHEN);

                                        commonColumn = StringUtils.EMPTY;
                                        actualSales = 0;
                                        actualAmount = 0;
                                        projectedSales = 0;
                                        projectedAmount = 0;
                                        projectedRate = 0;
                                        actualRPU = 0;
                                        projectedRPU = 0;
                                        currentDiscount = selectedDiscount;
                                        tmpList.remove(selectedDiscount);
                                        commonColumn = selectedDiscount.replace(" ", StringUtils.EMPTY);
                                        if (obj[2] != null) {
                                            Double aSales = Double.parseDouble(String.valueOf(obj[2]));
                                            actualSales = actualSales + aSales;
                                        }
                                        if (obj[3] != null) {
                                            Double aAmount = Double.parseDouble(String.valueOf(obj[3]));
                                            actualAmount = actualAmount + aAmount;
                                        }

                                        if (obj[4] != null) {
                                            Double pSales = Double.parseDouble(String.valueOf(obj[4]));
                                            projectedSales = projectedSales + pSales;
                                        }
                                        if (obj[5] != null) {
                                            Double pAmount = Double.parseDouble(String.valueOf(obj[5]));
                                            projectedAmount = projectedAmount + pAmount;
                                        }
                                        if (obj[9] != null) {
                                            Double pAmount = Double.parseDouble(String.valueOf(obj[9]));
                                            projectedRate = projectedRate + pAmount;
                                        }
                                        if (obj[10] != null) {
                                            Double acRPU = Double.parseDouble(String.valueOf(obj[10]));
                                            actualRPU = actualRPU + acRPU;
                                        }
                                        if (obj[11] != null) {
                                            Double prRPU = Double.parseDouble(String.valueOf(obj[11]));
                                            projectedRPU = projectedRPU + prRPU;
                                        }
                                    }
                                } else {
                                    String column = createColumn(String.valueOf(discountDto.getGroup()).replace(" ", StringUtils.EMPTY));
                                    if (periodList.contains(column)) {
                                        Double arate = actualAmount / actualSales;
                                        if (arate.isNaN() || arate.isInfinite()) {
                                            arate = 0.0;
                                        }
                                        arate = arate * 100;
                                        String actRate = String.valueOf(arate);
                                        discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double actualAmt = arate * actualSales;
                                        actualAmt = actualAmt / 100;
                                        if (actualAmt.isNaN() || actualAmt.isInfinite()) {
                                            actualAmt = 0.0;
                                        }
                                        String actAmt = String.valueOf(actualAmt);
                                        discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);

                                        Double prate = projectedAmount / projectedSales;

                                        if (prate.isNaN() || prate.isInfinite()) {
                                            prate = 0.0;
                                        }
                                        prate = prate * 100;

                                        String proRate = String.valueOf(prate);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double projectedAmtAmt = projectedAmount;
                                        projectedAmtAmt = projectedAmtAmt / 100;
                                        if (projectedAmtAmt.isNaN() || projectedAmtAmt.isInfinite()) {
                                            projectedAmtAmt = 0.0;
                                        }
                                        String proAmount = String.valueOf(projectedAmtAmt);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                        Double aRPU = actualAmount / actualRPU;
                                        if (aRPU.isNaN() || aRPU.isInfinite()) {
                                            aRPU = 0.0;
                                        }
                                        String actRPU = String.valueOf(aRPU);
                                        discountDto.addStringProperties(commonColumn + ACTUALRPU, actRPU != null && !NULL.equals(String.valueOf(actRPU)) && !StringUtils.EMPTY.equals(String.valueOf(actRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(actRPU)))) : HYPHEN);
                                        Double pRPU = projectedAmount / projectedRPU;
                                        if (pRPU.isNaN() || pRPU.isInfinite()) {
                                            pRPU = 0.0;
                                        }
                                        String proRPU = String.valueOf(pRPU);
                                        discountDto.addStringProperties(commonColumn + PROJECTEDRPU, proRPU != null && !NULL.equals(String.valueOf(proRPU)) && !StringUtils.EMPTY.equals(String.valueOf(proRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(proRPU)))) : HYPHEN);

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
                                    projectedRate = 0;
                                    actualRPU = 0;
                                    projectedRPU = 0;
                                    discountDto = new DiscountProjectionResultsDTO();
                                    discountDto.setGroup(CommonUtils.BUSINESS_PROCESS_INDICATOR_MANDATED + obj[1] + " " + obj[0]);
                                    currentYear = selectedYear;
                                    currentMonth = selectedMonth;
                                    currentDiscount = selectedDiscount;
                                    tmpList.remove(selectedDiscount);
                                    commonColumn = selectedDiscount.replace(" ", StringUtils.EMPTY);
                                    discountDto.setIsParent(ZERO);
                                    if (obj[2] != null) {
                                        Double aSales = Double.parseDouble(String.valueOf(obj[2]));
                                        actualSales = actualSales + aSales;
                                    }
                                    if (obj[3] != null) {
                                        Double aAmount = Double.parseDouble(String.valueOf(obj[3]));
                                        actualAmount = actualAmount + aAmount;
                                    }
                                    if (obj[4] != null) {
                                        Double pSales = Double.parseDouble(String.valueOf(obj[4]));
                                        projectedSales = projectedSales + pSales;
                                    }
                                    if (obj[5] != null) {
                                        Double pAmount = Double.parseDouble(String.valueOf(obj[5]));
                                        projectedAmount = projectedAmount + pAmount;
                                    }
                                    if (obj[9] != null) {
                                        Double pAmount = Double.parseDouble(String.valueOf(obj[9]));
                                        projectedRate = projectedRate + pAmount;
                                    }
                                    if (obj[10] != null) {
                                        Double acRPU = Double.parseDouble(String.valueOf(obj[10]));
                                        actualRPU = actualRPU + acRPU;
                                    }
                                    if (obj[11] != null) {
                                        Double prRPU = Double.parseDouble(String.valueOf(obj[11]));
                                        projectedRPU = projectedRPU + prRPU;
                                    }
                                }
                                if (i == list.size() - 1) {
                                    String column = createColumn(String.valueOf(discountDto.getGroup()).replace(" ", StringUtils.EMPTY));
                                    if (periodList.contains(column)) {
                                        Double arate = actualAmount / actualSales;
                                        if (arate.isNaN() || arate.isInfinite()) {
                                            arate = 0.0;
                                        }
                                        arate = arate * 100;
                                        String actRate = String.valueOf(arate);
                                        discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double actualAmt = actualAmount;
//                                        actualAmt = actualAmt / 100;
                                        if (actualAmt.isNaN() || actualAmt.isInfinite()) {
                                            actualAmt = 0.0;
                                        }
                                        String actAmt = String.valueOf(actualAmt);
                                        discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);

                                        Double prate = projectedAmount / projectedSales;

                                        if (prate.isNaN() || prate.isInfinite()) {
                                            prate = 0.0;
                                        }
                                        prate = prate * 100;

                                        String proRate = String.valueOf(prate);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                        Double projectedAmtAmt = projectedAmount;
//                                        projectedAmtAmt = projectedAmtAmt / 100;
                                        if (projectedAmtAmt.isNaN() || projectedAmtAmt.isInfinite()) {
                                            projectedAmtAmt = 0.0;
                                        }
                                        String proAmount = String.valueOf(projectedAmtAmt);
                                        discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                        Double aRPU = actualAmt / actualRPU;
                                        if (aRPU.isNaN() || aRPU.isInfinite()) {
                                            aRPU = 0.0;
                                        }
                                        String actRPU = String.valueOf(aRPU);
                                        discountDto.addStringProperties(commonColumn + ACTUALRPU, actRPU != null && !NULL.equals(String.valueOf(actRPU)) && !StringUtils.EMPTY.equals(String.valueOf(actRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(actRPU)))) : HYPHEN);
                                        Double pRPU = projectedAmount / projectedRPU;
                                        if (pRPU.isNaN() || pRPU.isInfinite()) {
                                            pRPU = 0.0;
                                        }
                                        String proRPU = String.valueOf(pRPU);
                                        discountDto.addStringProperties(commonColumn + PROJECTEDRPU, proRPU != null && !NULL.equals(String.valueOf(proRPU)) && !StringUtils.EMPTY.equals(String.valueOf(proRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(proRPU)))) : HYPHEN);

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
                                String month = group.substring(0, 3);
                                String year = group.replace(month, StringUtils.EMPTY);
                                String column = valueMap.get(month);

                                String groupColumn = CommonUtils.BUSINESS_PROCESS_INDICATOR_MANDATED + column + " " + year;
                                projDTO.setGroup(String.valueOf(groupColumn));
                                for (String discount : projSelDTO.getDiscountNameList()) {
                                    String discountRate = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns = discountRate + ACTUALSRATE;
                                    projDTO.addStringProperties(columns, getFormattedValue(CUR_ZERO, NULL));

                                    String discountActualAmount = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns1 = discountActualAmount + ACTUALSAMOUNT;
                                    projDTO.addStringProperties(columns1, getFormattedValue(CUR_ZERO, NULL));

                                    String discountProjectionAmount = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns2 = discountProjectionAmount + PROJECTIONSAMOUNT;
                                    projDTO.addStringProperties(columns2, getFormattedValue(CUR_ZERO, NULL));

                                    String discountProjectionRate = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns3 = discountProjectionRate + PROJECTIONSRATE;
                                    projDTO.addStringProperties(columns3, getFormattedValue(CUR_ZERO, NULL));

                                    String discountActualRPU = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns4 = discountActualRPU + ACTUALRPU;
                                    projDTO.addStringProperties(columns4, getFormattedValue(CUR_ZERO, NULL));

                                    String discountProjectionRPU = discount.replaceAll(" ", StringUtils.EMPTY);
                                    String columns5 = discountProjectionRPU + PROJECTEDRPU;
                                    projDTO.addStringProperties(columns5, getFormattedValue(CUR_ZERO, NULL));
                                }
                                discountProjList.add(projDTO);
                            }
                        }
                    }
                }
                if (Constant.DESCENDING.equals(projSelDTO.getProjectionOrder())) {
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
        List<DiscountProjectionResultsDTO> discountProjList = new ArrayList<DiscountProjectionResultsDTO>();
        int projectionMasterId = session.getProjectionId();
        String hierachyNumber = String.valueOf(dto.getHierarchyNo());
        dto.setIsParent(Constant.STRING_ONE);
        Integer levelNo = dto.getTreeLevelNo();
        hierachyNumber = hierachyNumber + PERCENTAGE;
        List ccpId = NmDiscountProjMasterLocalServiceUtil.getCCPDetailsID(projectionMasterId, hierachyNumber, String.valueOf(levelNo));
        String frequency = String.valueOf(selection.get(Constant.FREQUENCY));
        if (ccpId != null && !ccpId.isEmpty()) {
            final DynamicQuery projectionDetailsDynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionDetails.class);
            projectionDetailsDynamicQuery.add(RestrictionsFactoryUtil.in(CCP_DETAILS_SID, ccpId));
            projectionDetailsDynamicQuery.add(RestrictionsFactoryUtil.eq(PROJECTION_MASTER_SID, projectionMasterId));
            ProjectionList projList = ProjectionFactoryUtil.projectionList();
            projList.add(ProjectionFactoryUtil.property(PROJECTION_DETAILS_SID));
            projectionDetailsDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projList));
            List<Integer> projectionDetailsId = ProjectionDetailsLocalServiceUtil.dynamicQuery(projectionDetailsDynamicQuery);
            if (projectionDetailsId != null && !projectionDetailsId.isEmpty()) {
                String userId = String.valueOf(selection.get(Constant.USER_ID));
                String sessionId = String.valueOf(selection.get(Constant.SESSION_ID));
                int user = Integer.parseInt(userId);
                int session1 = Integer.parseInt(sessionId);
                List list = NmDiscountProjMasterLocalServiceUtil.getDiscountProjectionResults(projectionDetailsId, frequency, StringUtils.EMPTY, StringUtils.EMPTY, Constant.PARENT, StringUtils.EMPTY, startAndEndPeriods, user, session1);
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
                            if (object[6] != null) {
                                selectedQuarter = (Integer) object[6];
                            }
                            if (year == selectedYear && quarter == selectedQuarter) {
                                if (object[2] != null) {
                                    Double aSales = Double.parseDouble(String.valueOf(object[2]));
                                    actualSales = actualSales + aSales;
                                }
                                if (object[3] != null) {
                                    Double aAmount = Double.parseDouble(String.valueOf(object[3]));
                                    actualAmount = actualAmount + aAmount;
                                }
                                if (object[4] != null) {
                                    Double pSales = Double.parseDouble(String.valueOf(object[4]));
                                    projectedSales = projectedSales + pSales;
                                }
                                if (object[5] != null) {
                                    Double pAmount = Double.parseDouble(String.valueOf(object[5]));
                                    projectedAmount = projectedAmount + pAmount;
                                }
                                if (j == list.size() - 1) {
                                    Double arate = actualAmount / actualSales;
                                    if (arate.isNaN()) {
                                        arate = 0.0;
                                    }
                                    arate = arate * 100;
                                    String actualsRate = String.valueOf(arate);
                                    dto.addStringProperties(commonColumn + ACTUALSRATE, actualsRate != null && !NULL.equals(String.valueOf(actualsRate)) && !StringUtils.EMPTY.equals(String.valueOf(actualsRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actualsRate)))).concat(PERCENTAGE) : HYPHEN);
                                    Double actualAmt = actualAmount;
                                    if (actualAmt.isNaN() || actualAmt.isInfinite()) {
                                        actualAmt = 0.0;
                                    }
                                    String actualsAmount = String.valueOf(actualAmt);
                                    dto.addStringProperties(commonColumn + ACTUALSAMOUNT, actualsAmount != null && !NULL.equals(String.valueOf(actualsAmount)) && !StringUtils.EMPTY.equals(String.valueOf(actualsAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actualsAmount)))) : HYPHEN);
                                    Double prate = projectedAmount / projectedSales;
                                    if (prate.isNaN() || prate.isInfinite()) {
                                        prate = 0.0;
                                    }
                                    prate = prate * 100;
                                    dto.addStringProperties(commonColumn + PROJECTIONSRATE, prate != null && !NULL.equals(String.valueOf(prate)) && !StringUtils.EMPTY.equals(String.valueOf(prate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(prate)))).concat(PERCENTAGE) : HYPHEN);
                                    Double projectedAmtAmt = projectedAmount;
                                    if (projectedAmtAmt.isNaN() || projectedAmtAmt.isInfinite()) {
                                        projectedAmtAmt = 0.0;
                                    }
                                    dto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, projectedAmtAmt != null && !NULL.equals(String.valueOf(projectedAmtAmt)) && !StringUtils.EMPTY.equals(String.valueOf(projectedAmtAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(projectedAmtAmt)))) : HYPHEN);
                                    commonColumn = StringUtils.EMPTY;
                                    actualSales = 0;
                                    actualAmount = 0;
                                    projectedSales = 0;
                                    projectedAmount = 0;
                                    commonColumn = Q_BIG + object[6] + object[0];
                                    year = (Integer) object[0];
                                    quarter = (Integer) object[6];
                                }
                            } else {
                                if (j == 0) {
                                    year = selectedYear;
                                    quarter = selectedQuarter;
                                    commonColumn = Q_BIG + object[6] + object[0];
                                    if (object[2] != null) {
                                        Double aSales = Double.parseDouble(String.valueOf(object[2]));
                                        actualSales = actualSales + aSales;
                                    }
                                    if (object[3] != null) {
                                        Double aAmount = Double.parseDouble(String.valueOf(object[3]));
                                        actualAmount = actualAmount + aAmount;
                                    }
                                    if (object[4] != null) {
                                        Double pSales = Double.parseDouble(String.valueOf(object[4]));
                                        projectedSales = projectedSales + pSales;
                                    }
                                    if (object[5] != null) {
                                        Double pAmount = Double.parseDouble(String.valueOf(object[5]));
                                        projectedAmount = projectedAmount + pAmount;
                                    }

                                } else {
                                    Double arate = actualAmount / actualSales;
                                    if (arate.isNaN()) {
                                        arate = 0.0;
                                    }
                                    arate = arate * 100;
                                    String actualsRate = String.valueOf(arate);
                                    dto.addStringProperties(commonColumn + ACTUALSRATE, actualsRate != null && !NULL.equals(String.valueOf(actualsRate)) && !StringUtils.EMPTY.equals(String.valueOf(actualsRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actualsRate)))).concat(PERCENTAGE) : HYPHEN);
                                    Double actualAmt = arate * actualSales;
                                    if (actualAmt.isNaN() || actualAmt.isInfinite()) {
                                        actualAmt = 0.0;
                                    }
                                    String actualsAmount = String.valueOf(actualAmt);
                                    dto.addStringProperties(commonColumn + ACTUALSAMOUNT, actualsAmount != null && !NULL.equals(String.valueOf(actualsAmount)) && !StringUtils.EMPTY.equals(String.valueOf(actualsAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actualsAmount)))) : HYPHEN);
                                    Double prate = projectedAmount / projectedSales;
                                    if (prate.isNaN() || prate.isInfinite()) {
                                        prate = 0.0;
                                    }
                                    prate = prate * 100;
                                    String prorate = String.valueOf(prate);
                                    dto.addStringProperties(commonColumn + PROJECTIONSRATE, prorate != null && !NULL.equals(String.valueOf(prorate)) && !StringUtils.EMPTY.equals(String.valueOf(prorate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(prorate)))).concat(PERCENTAGE) : HYPHEN);
                                    Double projectedAmtAmt = projectedAmount;
                                    if (projectedAmtAmt.isNaN() || projectedAmtAmt.isInfinite()) {
                                        projectedAmtAmt = 0.0;
                                    }
                                    String proAmount = String.valueOf(projectedAmtAmt);
                                    dto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                    commonColumn = StringUtils.EMPTY;
                                    actualSales = 0;
                                    actualAmount = 0;
                                    projectedSales = 0;
                                    projectedAmount = 0;
                                    commonColumn = Q_BIG + object[6] + object[0];
                                    year = (Integer) object[0];
                                    quarter = (Integer) object[6];
                                    if (object[2] != null) {
                                        Double aSales = Double.parseDouble(String.valueOf(object[2]));
                                        actualSales = actualSales + aSales;
                                    }
                                    if (object[3] != null) {
                                        Double aAmount = Double.parseDouble(String.valueOf(object[3]));
                                        actualAmount = actualAmount + aAmount;
                                    }
                                    if (object[4] != null) {
                                        Double pSales = Double.parseDouble(String.valueOf(object[4]));
                                        projectedSales = projectedSales + pSales;
                                    }
                                    if (object[5] != null) {
                                        Double pAmount = Double.parseDouble(String.valueOf(object[5]));
                                        projectedAmount = projectedAmount + pAmount;
                                    }
                                }
                            }
                        }
                    }
                    if (SEMI_ANNUALLY.getConstant().equals(frequency)) {
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
                            if (object[6] != null) {
                                selectedSemiAnnual = (Integer) object[1];
                            }
                            if (year == selectedYear && semiAnnual == selectedSemiAnnual) {
                                if (object[2] != null) {
                                    Double aSales = Double.parseDouble(String.valueOf(object[2]));
                                    actualSales = actualSales + aSales;
                                }
                                if (object[3] != null) {
                                    Double aAmount = Double.parseDouble(String.valueOf(object[3]));
                                    actualAmount = actualAmount + aAmount;
                                }
                                if (object[4] != null) {
                                    Double pSales = Double.parseDouble(String.valueOf(object[4]));
                                    projectedSales = projectedSales + pSales;
                                }
                                if (object[5] != null) {
                                    Double pAmount = Double.parseDouble(String.valueOf(object[5]));
                                    projectedAmount = projectedAmount + pAmount;
                                }
                                if (j == list.size() - 1) {
                                    Double arate = actualAmount / actualSales;
                                    if (arate.isNaN()) {
                                        arate = 0.0;
                                    }
                                    arate = arate * 100;
                                    dto.addStringProperties(commonColumn + ACTUALSRATE, arate != null && !NULL.equals(String.valueOf(arate)) && !StringUtils.EMPTY.equals(String.valueOf(arate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(arate)))).concat(PERCENTAGE) : HYPHEN);
                                    Double actualAmt = arate * actualSales;
                                    if (actualAmt.isNaN() || actualAmt.isInfinite()) {
                                        actualAmt = 0.0;
                                    }
                                    dto.addStringProperties(commonColumn + ACTUALSAMOUNT, actualAmt != null && !NULL.equals(String.valueOf(actualAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actualAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actualAmt)))) : HYPHEN);
                                    Double prate = projectedAmount / projectedSales;
                                    if (prate.isNaN() || prate.isInfinite()) {
                                        prate = 0.0;
                                    }
                                    prate = prate * 100;
                                    dto.addStringProperties(commonColumn + PROJECTIONSRATE, prate != null && !NULL.equals(String.valueOf(prate)) && !StringUtils.EMPTY.equals(String.valueOf(prate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(prate)))).concat(PERCENTAGE) : HYPHEN);
                                    Double projectedAmtAmt = projectedAmount;
                                    if (projectedAmtAmt.isNaN() || projectedAmtAmt.isInfinite()) {
                                        projectedAmtAmt = 0.0;
                                    }
                                    dto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, projectedAmtAmt != null && !NULL.equals(String.valueOf(projectedAmtAmt)) && !StringUtils.EMPTY.equals(String.valueOf(projectedAmtAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(projectedAmtAmt)))) : HYPHEN);
                                    commonColumn = StringUtils.EMPTY;
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
                                    if (object[2] != null) {
                                        Double aSales = Double.parseDouble(String.valueOf(object[2]));
                                        actualSales = actualSales + aSales;
                                    }
                                    if (object[3] != null) {
                                        Double aAmount = Double.parseDouble(String.valueOf(object[3]));
                                        actualAmount = actualAmount + aAmount;
                                    }
                                    if (object[4] != null) {
                                        Double pSales = Double.parseDouble(String.valueOf(object[4]));
                                        projectedSales = projectedSales + pSales;
                                    }
                                    if (object[5] != null) {
                                        Double pAmount = Double.parseDouble(String.valueOf(object[5]));
                                        projectedAmount = projectedAmount + pAmount;
                                    }
                                } else {
                                    Double arate = actualAmount / actualSales;
                                    if (arate.isNaN() || arate.isInfinite()) {
                                        arate = 0.0;
                                    }
                                    arate = arate * 100;
                                    dto.addStringProperties(commonColumn + ACTUALSRATE, arate != null && !NULL.equals(String.valueOf(arate)) && !StringUtils.EMPTY.equals(String.valueOf(arate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(arate)))).concat(PERCENTAGE) : HYPHEN);
                                    Double actualAmt = arate * actualSales;
                                    if (actualAmt.isNaN() || actualAmt.isInfinite()) {
                                        actualAmt = 0.0;
                                    }
                                    dto.addStringProperties(commonColumn + ACTUALSAMOUNT, actualAmt != null && !NULL.equals(String.valueOf(actualAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actualAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actualAmt)))) : HYPHEN);
                                    Double prate = projectedAmount / projectedSales;
                                    if (prate.isNaN() || prate.isInfinite()) {
                                        prate = 0.0;
                                    }
                                    prate = prate * 100;
                                    dto.addStringProperties(commonColumn + PROJECTIONSRATE, prate != null && !NULL.equals(String.valueOf(prate)) && !StringUtils.EMPTY.equals(String.valueOf(prate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(prate)))).concat(PERCENTAGE) : HYPHEN);
                                    Double projectedAmtAmt =projectedAmount;
                                    if (projectedAmtAmt.isNaN() || projectedAmtAmt.isInfinite()) {
                                        projectedAmtAmt = 0.0;
                                    }
                                    dto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, projectedAmtAmt != null && !NULL.equals(String.valueOf(projectedAmtAmt)) && !StringUtils.EMPTY.equals(String.valueOf(projectedAmtAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(projectedAmtAmt)))) : HYPHEN);
                                    commonColumn = StringUtils.EMPTY;
                                    actualSales = 0;
                                    actualAmount = 0;
                                    projectedSales = 0;
                                    projectedAmount = 0;
                                    commonColumn = Constant.S + object[1] + object[0];
                                    year = (Integer) object[0];
                                    semiAnnual = (Integer) object[1];
                                    if (object[2] != null) {
                                        Double aSales = Double.parseDouble(String.valueOf(object[2]));
                                        actualSales = actualSales + aSales;
                                    }
                                    if (object[3] != null) {
                                        Double aAmount = Double.parseDouble(String.valueOf(object[3]));
                                        actualAmount = actualAmount + aAmount;
                                    }
                                    if (object[4] != null) {
                                        Double pSales = Double.parseDouble(String.valueOf(object[4]));
                                        projectedSales = projectedSales + pSales;
                                    }
                                    if (object[5] != null) {
                                        Double pAmount = Double.parseDouble(String.valueOf(object[5]));
                                        projectedAmount = projectedAmount + pAmount;
                                    }
                                }
                            }
                        }
                    }
                    if (ANNUALLY.getConstant().equals(frequency)) {
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
                                if (object[2] != null) {
                                    Double aSales = Double.parseDouble(String.valueOf(object[2]));
                                    actualSales = actualSales + aSales;
                                }
                                if (object[3] != null) {
                                    Double aAmount = Double.parseDouble(String.valueOf(object[3]));
                                    actualAmount = actualAmount + aAmount;
                                }
                                if (object[4] != null) {
                                    Double pSales = Double.parseDouble(String.valueOf(object[4]));
                                    projectedSales = projectedSales + pSales;
                                }
                                if (object[5] != null) {
                                    Double pAmount = Double.parseDouble(String.valueOf(object[5]));
                                    projectedAmount = projectedAmount + pAmount;
                                }
                                if (j == list.size() - 1) {
                                    Double arate = actualAmount / actualSales;
                                    if (arate.isNaN() || arate.isInfinite()) {
                                        arate = 0.0;
                                    }
                                    arate = arate * 100;
                                    dto.addStringProperties(commonColumn + ACTUALSRATE, arate != null && !NULL.equals(String.valueOf(arate)) && !StringUtils.EMPTY.equals(String.valueOf(arate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(arate)))).concat(PERCENTAGE) : HYPHEN);
                                    Double actualAmt = arate * actualSales;
                                    if (actualAmt.isNaN() || actualAmt.isInfinite()) {
                                        actualAmt = 0.0;
                                    }
                                    dto.addStringProperties(commonColumn + ACTUALSAMOUNT, actualAmt != null && !NULL.equals(String.valueOf(actualAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actualAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actualAmt)))) : HYPHEN);
                                    Double prate = projectedAmount / projectedSales;
                                    if (prate.isNaN() || prate.isInfinite()) {
                                        prate = 0.0;
                                    }
                                    prate = prate * 100;
                                    dto.addStringProperties(commonColumn + PROJECTIONSRATE, prate != null && !NULL.equals(String.valueOf(prate)) && !StringUtils.EMPTY.equals(String.valueOf(prate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(prate)))).concat(PERCENTAGE) : HYPHEN);
                                    Double projectedAmtAmt =projectedAmount;
                                    if (projectedAmtAmt.isNaN() || projectedAmtAmt.isInfinite()) {
                                        projectedAmtAmt = 0.0;
                                    }
                                    dto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, projectedAmtAmt != null && !NULL.equals(String.valueOf(projectedAmtAmt)) && !StringUtils.EMPTY.equals(String.valueOf(projectedAmtAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(projectedAmtAmt)))) : HYPHEN);
                                    commonColumn = StringUtils.EMPTY;
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
                                    if (object[2] != null) {
                                        Double aSales = Double.parseDouble(String.valueOf(object[2]));
                                        actualSales = actualSales + aSales;
                                    }
                                    if (object[3] != null) {
                                        Double aAmount = Double.parseDouble(String.valueOf(object[3]));
                                        actualAmount = actualAmount + aAmount;
                                    }
                                    if (object[4] != null) {
                                        Double pSales = Double.parseDouble(String.valueOf(object[4]));
                                        projectedSales = projectedSales + pSales;
                                    }
                                    if (object[5] != null) {
                                        Double pAmount = Double.parseDouble(String.valueOf(object[5]));
                                        projectedAmount = projectedAmount + pAmount;
                                    }
                                } else {
                                    Double arate = actualAmount / actualSales;
                                    if (arate.isNaN() || arate.isInfinite()) {
                                        arate = 0.0;
                                    }
                                    arate = arate * 100;
                                    dto.addStringProperties(commonColumn + ACTUALSRATE, arate != null && !NULL.equals(String.valueOf(arate)) && !StringUtils.EMPTY.equals(String.valueOf(arate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(arate)))).concat(PERCENTAGE) : HYPHEN);
                                    Double actualAmt = arate * actualSales;
                                    if (actualAmt.isNaN() || actualAmt.isInfinite()) {
                                        actualAmt = 0.0;
                                    }
                                    dto.addStringProperties(commonColumn + ACTUALSAMOUNT, actualAmt != null && !NULL.equals(String.valueOf(actualAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actualAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actualAmt)))) : HYPHEN);
                                    Double prate = projectedAmount / projectedSales;
                                    if (prate.isNaN() || prate.isInfinite()) {
                                        prate = 0.0;
                                    }
                                    prate = prate * 100;
                                    dto.addStringProperties(commonColumn + PROJECTIONSRATE, prate != null && !NULL.equals(String.valueOf(prate)) && !StringUtils.EMPTY.equals(String.valueOf(prate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(prate)))).concat(PERCENTAGE) : HYPHEN);
                                    Double projectedAmtAmt =projectedAmount;
                                    if (projectedAmtAmt.isNaN() || projectedAmtAmt.isInfinite()) {
                                        projectedAmtAmt = 0.0;
                                    }
                                    dto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, projectedAmtAmt != null && !NULL.equals(String.valueOf(projectedAmtAmt)) && !StringUtils.EMPTY.equals(String.valueOf(projectedAmtAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(projectedAmtAmt)))) : HYPHEN);
                                    commonColumn = StringUtils.EMPTY;
                                    actualSales = 0;
                                    actualAmount = 0;
                                    projectedSales = 0;
                                    projectedAmount = 0;
                                    commonColumn = StringUtils.EMPTY + object[0];
                                    year = (Integer) object[0];
                                    if (object[2] != null) {
                                        Double aSales = Double.parseDouble(String.valueOf(object[2]));
                                        actualSales = actualSales + aSales;
                                    }
                                    if (object[3] != null) {
                                        Double aAmount = Double.parseDouble(String.valueOf(object[3]));
                                        actualAmount = actualAmount + aAmount;
                                    }
                                    if (object[4] != null) {
                                        Double pSales = Double.parseDouble(String.valueOf(object[4]));
                                        projectedSales = projectedSales + pSales;
                                    }
                                    if (object[5] != null) {
                                        Double pAmount = Double.parseDouble(String.valueOf(object[5]));
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
                            if (object[6] != null) {
                                selectedMonth = (Integer) object[1];
                            }
                            if (year == selectedYear && month == selectedMonth) {
                                if (object[2] != null) {
                                    Double aSales = Double.parseDouble(String.valueOf(object[2]));
                                    actualSales = actualSales + aSales;
                                }
                                if (object[3] != null) {
                                    Double aAmount = Double.parseDouble(String.valueOf(object[3]));
                                    actualAmount = actualAmount + aAmount;
                                }
                                if (object[4] != null) {
                                    Double pSales = Double.parseDouble(String.valueOf(object[4]));
                                    projectedSales = projectedSales + pSales;
                                }
                                if (object[5] != null) {
                                    Double pAmount = Double.parseDouble(String.valueOf(object[5]));
                                    projectedAmount = projectedAmount + pAmount;
                                }
                                if (j == list.size() - 1) {
                                    Double arate = actualAmount / actualSales;
                                    if (arate.isNaN() || arate.isInfinite()) {
                                        arate = 0.0;
                                    }
                                    arate = arate * 100;
                                    dto.addStringProperties(commonColumn + ACTUALSRATE, arate != null && !NULL.equals(String.valueOf(arate)) && !StringUtils.EMPTY.equals(String.valueOf(arate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(arate)))).concat(PERCENTAGE) : HYPHEN);
                                    Double actualAmt = arate * actualSales;
                                    if (actualAmt.isNaN() || actualAmt.isInfinite()) {
                                        actualAmt = 0.0;
                                    }
                                    dto.addStringProperties(commonColumn + ACTUALSAMOUNT, actualAmt != null && !NULL.equals(String.valueOf(actualAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actualAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actualAmt)))) : HYPHEN);
                                    Double prate = projectedAmount / projectedSales;
                                    if (prate.isNaN() || prate.isInfinite()) {
                                        prate = 0.0;
                                    }
                                    prate = prate * 100;
                                    dto.addStringProperties(commonColumn + PROJECTIONSRATE, prate != null && !NULL.equals(String.valueOf(prate)) && !StringUtils.EMPTY.equals(String.valueOf(prate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(prate)))).concat(PERCENTAGE) : HYPHEN);
                                    Double projectedAmtAmt =projectedAmount;
                                    if (projectedAmtAmt.isNaN() || projectedAmtAmt.isInfinite()) {
                                        projectedAmtAmt = 0.0;
                                    }
                                    dto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, projectedAmtAmt != null && !NULL.equals(String.valueOf(projectedAmtAmt)) && !StringUtils.EMPTY.equals(String.valueOf(projectedAmtAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(projectedAmtAmt)))) : HYPHEN);
                                    commonColumn = StringUtils.EMPTY;
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
                                    if (object[2] != null) {
                                        Double aSales = Double.parseDouble(String.valueOf(object[2]));
                                        actualSales = actualSales + aSales;
                                    }
                                    if (object[3] != null) {
                                        Double aAmount = Double.parseDouble(String.valueOf(object[3]));
                                        actualAmount = actualAmount + aAmount;
                                    }
                                    if (object[4] != null) {
                                        Double pSales = Double.parseDouble(String.valueOf(object[4]));
                                        projectedSales = projectedSales + pSales;
                                    }
                                    if (object[5] != null) {
                                        Double pAmount = Double.parseDouble(String.valueOf(object[5]));
                                        projectedAmount = projectedAmount + pAmount;
                                    }
                                } else {
                                    Double arate = actualAmount / actualSales;
                                    if (arate.isNaN() || arate.isInfinite()) {
                                        arate = 0.0;
                                    }
                                    arate = arate * 100;
                                    dto.addStringProperties(commonColumn + ACTUALSRATE, arate != null && !NULL.equals(String.valueOf(arate)) && !StringUtils.EMPTY.equals(String.valueOf(arate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(arate)))).concat(PERCENTAGE) : HYPHEN);
                                    Double actualAmt = arate * actualSales;
                                    if (actualAmt.isNaN() || actualAmt.isInfinite()) {
                                        actualAmt = 0.0;
                                    }
                                    dto.addStringProperties(commonColumn + ACTUALSAMOUNT, actualAmt != null && !NULL.equals(String.valueOf(actualAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actualAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actualAmt)))) : HYPHEN);
                                    Double prate = projectedAmount / projectedSales;
                                    if (prate.isNaN() || prate.isInfinite()) {
                                        prate = 0.0;
                                    }
                                    prate = prate * 100;
                                    dto.addStringProperties(commonColumn + PROJECTIONSRATE, prate != null && !NULL.equals(String.valueOf(prate)) && !StringUtils.EMPTY.equals(String.valueOf(prate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(prate)))).concat(PERCENTAGE) : HYPHEN);
                                    Double projectedAmtAmt =projectedAmount;
                                    if (projectedAmtAmt.isNaN() || projectedAmtAmt.isInfinite()) {
                                        projectedAmtAmt = 0.0;
                                    }
                                    dto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, projectedAmtAmt != null && !NULL.equals(String.valueOf(projectedAmtAmt)) && !StringUtils.EMPTY.equals(String.valueOf(projectedAmtAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(projectedAmtAmt)))) : HYPHEN);
                                    commonColumn = StringUtils.EMPTY;
                                    actualSales = 0;
                                    actualAmount = 0;
                                    projectedSales = 0;
                                    projectedAmount = 0;
                                    String monthName = getMonthForInt(Integer.valueOf(StringUtils.EMPTY + object[1]) - 1);
                                    commonColumn = monthName + object[0];
                                    year = (Integer) object[0];
                                    month = (Integer) object[1];
                                    if (object[2] != null) {
                                        Double aSales = Double.parseDouble(String.valueOf(object[2]));
                                        actualSales = actualSales + aSales;
                                    }
                                    if (object[3] != null) {
                                        Double aAmount = Double.parseDouble(String.valueOf(object[3]));
                                        actualAmount = actualAmount + aAmount;
                                    }
                                    if (object[4] != null) {
                                        Double pSales = Double.parseDouble(String.valueOf(object[4]));
                                        projectedSales = projectedSales + pSales;
                                    }
                                    if (object[5] != null) {
                                        Double pAmount = Double.parseDouble(String.valueOf(object[5]));
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

    /**
     *
     * @param start
     * @param offset
     * @param projSelDTO
     * @return
     *
     */
    public List<DiscountProjectionResultsDTO> getProjectionResults(int start, int offset, ProjectionSelectionDTO projSelDTO) throws SystemException, PortalException {
        int neededRecord = offset;
        int mayBeAdded = 0;
        List<DiscountProjectionResultsDTO> projDTOList = new ArrayList<DiscountProjectionResultsDTO>();
        List<Integer> yearList = new ArrayList<Integer>();
        yearList.add(projSelDTO.getForecastDTO().getHistoryStartYear());
        yearList.add(projSelDTO.getForecastDTO().getHistoryStartMonth());
        yearList.add(projSelDTO.getForecastDTO().getHistoryEndYear());
        yearList.add(projSelDTO.getForecastDTO().getHistoryEndMonth());
        yearList.add(projSelDTO.getForecastDTO().getForecastStartYear());
        String freq = String.valueOf(projSelDTO.getFrequency());
        if (QUARTERLY.getConstant().equals(freq)) {
            int month = projSelDTO.getForecastDTO().getForecastStartMonth();
            if (month <= 3) {
                yearList.add(1);
            } else if (month >= 4 && month < 7) {
                yearList.add(4);
            } else if (month >= 7 && month < 10) {
                yearList.add(7);
            } else if (month >= 10 && month < 13) {
                yearList.add(10);
            }
        } else if (SEMI_ANNUALLY.getConstant().equals(freq)) {
            int month = projSelDTO.getForecastDTO().getForecastStartMonth();
            if (month <= 6) {
                yearList.add(1);
            } else if (month >= 7 && month < 13) {
                yearList.add(7);
            }
        } else if (ANNUALLY.getConstant().equals(freq)) {
            yearList.add(1);
        } else if (MONTHLY.getConstant().equals(freq)) {
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
                    List<String> discountList = new ArrayList<String>();
                    int mayBeAddedRecord = start - mayBeAdded;
                    if (mayBeAddedRecord < 0) {
                        mayBeAddedRecord = 0;
                    }
                    for (int i = mayBeAddedRecord; i < projSelDTO.getDiscountNameList().size(); i++) {
                        discountList.add(projSelDTO.getDiscountNameList().get(i));
                    }
                    List<DiscountProjectionResultsDTO> discountDtoList = getPeriodProjectionTotalDiscount(projSelDTO.getProjectionId(), yearList, projSelDTO, discountList);
                    for (int k = 0; k < discountDtoList.size() && neededRecord > 0; k++) {
                        projDTOList.add(discountDtoList.get(k));
                        neededRecord--;
                    }
                    mayBeAdded += projSelDTO.getDiscountNameList().size();
                }
            } else {
                List<Integer> pivotYearList = new ArrayList<Integer>();
                String frequency = String.valueOf(projSelDTO.getFrequency());
                if (ANNUALLY.getConstant().equals(frequency)) {
                    pivotYearList.add(Integer.valueOf(projSelDTO.getPeriodList().get(0)));
                    pivotYearList.add(1);
                    pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndYear());
                    pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndMonth());
                } else if (QUARTERLY.getConstant().equals(frequency)) {
                    String column = String.valueOf(projSelDTO.getPeriodList().get(0));
                    column = column.replace(Q_SMALL, StringUtils.EMPTY);
                    String year = column.substring(column.length() - 4, column.length());
                    String fre = column.replace(year, StringUtils.EMPTY);
                    pivotYearList.add(Integer.valueOf(year));
                    if (ONE.equals(fre)) {
                        pivotYearList.add(1);
                    } else if (TWO.equals(fre)) {
                        pivotYearList.add(4);
                    } else if (THREE.equals(fre)) {
                        pivotYearList.add(7);
                    } else if (FOUR.equals(fre)) {
                        pivotYearList.add(10);
                    }
                    pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndYear());
                    pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndMonth());
                } else if (MONTHLY.getConstant().equals(frequency)) {
                    loadKeyMap();
                    String column = String.valueOf(projSelDTO.getPeriodList().get(0));
                    String fre = column.substring(0, 3);
                    String year = column.replace(fre, StringUtils.EMPTY);
                    String month = valueMap.get(fre);
                    pivotYearList.add(Integer.valueOf(year));
                    pivotYearList.add(Integer.valueOf(month));
                    pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndYear());
                    pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndMonth());

                } else if (SEMI_ANNUALLY.getConstant().equals(frequency)) {
                    String column = String.valueOf(projSelDTO.getPeriodList().get(0));
                    column = column.replace(Constant.S_SMALL, StringUtils.EMPTY);
                    String year = column.substring(column.length() - 4, column.length());
                    String fre = column.replace(year, StringUtils.EMPTY);
                    pivotYearList.add(Integer.valueOf(year));
                    if (ONE.equals(fre)) {
                        pivotYearList.add(1);
                    } else if (TWO.equals(fre)) {
                        pivotYearList.add(7);
                    }
                    pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndYear());
                    pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndMonth());
                }
                pivotYearList.add(projSelDTO.getForecastDTO().getForecastStartYear());
                String frequ = String.valueOf(projSelDTO.getFrequency());
                if (QUARTERLY.getConstant().equals(frequ)) {
                    int month = projSelDTO.getForecastDTO().getForecastStartMonth();
                    if (month <= 3) {
                        pivotYearList.add(1);
                    } else if (month >= 4 && month < 7) {
                        pivotYearList.add(4);
                    } else if (month >= 7 && month < 10) {
                        pivotYearList.add(7);
                    } else if (month >= 10 && month < 13) {
                        pivotYearList.add(10);
                    }
                } else if (SEMI_ANNUALLY.getConstant().equals(frequ)) {
                    int month = projSelDTO.getForecastDTO().getForecastStartMonth();
                    if (month <= 6) {
                        pivotYearList.add(1);
                    } else if (month >= 7 && month < 13) {
                        pivotYearList.add(7);
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
                    List<DiscountProjectionResultsDTO> discountDtoList = new ArrayList<DiscountProjectionResultsDTO>();
                    discountDtoList = getPivotProjectionTotal(projSelDTO.getProjectionId(), projSelDTO, pivotYearList);
                    if (discountDtoList != null && !discountDtoList.isEmpty()) {
                        projDTOList.add(discountDtoList.get(0));
                        neededRecord--;
                    }
                }
                mayBeAdded++;
                if (neededRecord > 0) {
                    List<DiscountProjectionResultsDTO> periodList = getPivotProjectionTotalDiscount(projSelDTO, pivotYearList);

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
                List<DiscountProjectionResultsDTO> discountDtoList;
                if (neededRecord > 0) {
                    List<String> discountList = new ArrayList<String>();
                    int mayBeAddedRecord = start - mayBeAdded;
                    if (mayBeAddedRecord < 0) {
                        mayBeAddedRecord = 0;
                    }
                    for (int i = mayBeAddedRecord; i < projSelDTO.getDiscountNameList().size(); i++) {
                        discountList.add(projSelDTO.getDiscountNameList().get(i));
                    }
                    discountDtoList = getPeriodHierarchy(projSelDTO, yearList, discountList);
                    for (int k = 0; k < discountDtoList.size() && neededRecord > 0; k++) {
                        projDTOList.add(discountDtoList.get(k));
                        neededRecord--;
                    }
                    mayBeAdded += projSelDTO.getDiscountNameList().size();
                }
            } else {
                List<Integer> pivotYearList = new ArrayList<Integer>();
                String frequency = String.valueOf(projSelDTO.getFrequency());
                if (ANNUALLY.getConstant().equals(frequency)) {
                    pivotYearList.add(Integer.valueOf(projSelDTO.getPeriodList().get(0)));
                    pivotYearList.add(1);
                    pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndYear());
                    pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndMonth());
                } else if (QUARTERLY.getConstant().equals(frequency)) {
                    String column = String.valueOf(projSelDTO.getPeriodList().get(0));
                    column = column.replace(Q_SMALL, StringUtils.EMPTY);
                    String year = column.substring(column.length() - 4, column.length());
                    String fre = column.replace(year, StringUtils.EMPTY);
                    pivotYearList.add(Integer.valueOf(year));
                    if (ONE.equals(fre)) {
                        pivotYearList.add(1);
                    } else if (TWO.equals(fre)) {
                        pivotYearList.add(4);
                    } else if (THREE.equals(fre)) {
                        pivotYearList.add(7);
                    } else if (FOUR.equals(fre)) {
                        pivotYearList.add(10);
                    }
                    pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndYear());
                    pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndMonth());
                } else if (MONTHLY.getConstant().equals(frequency)) {
                    loadKeyMap();
                    String column = String.valueOf(projSelDTO.getPeriodList().get(0));
                    String fre = column.substring(0, 3);
                    String year = column.replace(fre, StringUtils.EMPTY);
                    String month = valueMap.get(fre);
                    pivotYearList.add(Integer.valueOf(year));
                    pivotYearList.add(Integer.valueOf(month));
                    pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndYear());
                    pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndMonth());

                } else if (SEMI_ANNUALLY.getConstant().equals(frequency)) {
                    String column = String.valueOf(projSelDTO.getPeriodList().get(0));
                    column = column.replace(Constant.S_SMALL, StringUtils.EMPTY);
                    String year = column.substring(column.length() - 4, column.length());
                    String fre = column.replace(year, StringUtils.EMPTY);
                    pivotYearList.add(Integer.valueOf(year));
                    if (ONE.equals(fre)) {
                        pivotYearList.add(1);
                    } else if (TWO.equals(fre)) {
                        pivotYearList.add(7);
                    }
                    pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndYear());
                    pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndMonth());
                }
                pivotYearList.add(projSelDTO.getForecastDTO().getForecastStartYear());
                String frequ = String.valueOf(projSelDTO.getFrequency());
                if (QUARTERLY.getConstant().equals(frequ)) {
                    int month = projSelDTO.getForecastDTO().getForecastStartMonth();
                    if (month <= 3) {
                        pivotYearList.add(1);
                    } else if (month >= 4 && month < 7) {
                        pivotYearList.add(4);
                    } else if (month >= 7 && month < 10) {
                        pivotYearList.add(7);
                    } else if (month >= 10 && month < 13) {
                        pivotYearList.add(10);
                    }
                } else if (SEMI_ANNUALLY.getConstant().equals(frequ)) {
                    int month = projSelDTO.getForecastDTO().getForecastStartMonth();
                    if (month <= 6) {
                        pivotYearList.add(1);
                    } else if (month >= 7 && month < 13) {
                        pivotYearList.add(7);
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
        }

        if (neededRecord > 0 && !projSelDTO.isIsFilter()) {
            if ((projSelDTO.getTreeLevelNo() + 1) == projSelDTO.getTpLevel()
                    && ((projSelDTO.isIsCustomHierarchy()) || (!INDICATOR_VIEW_PRODUCT.getConstant().equals(projSelDTO.getHierarchyIndicator())))
                    && !projSelDTO.getGroup().startsWith(Constant.DISCOUNT) && !projSelDTO.getGroupFilter().startsWith("All Discount Groups")) {
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
                DiscountProjectionResultsDTO discountDTO = new DiscountProjectionResultsDTO();
                if (Constant.PERIOD.equals(projSelDTO.getPivotView())) {

                    discountDTO = getChildNodeValues(dto, projSelDTO, null);
                } else {
                    discountDTO = getPivotChildNodeValues(dto, projSelDTO);
                }

                projDTOList.add(discountDTO);
                neededRecord--;
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
    public List<DiscountProjectionResultsDTO> getConfiguredProjectionResults(Object parentId, int start, int offset, ProjectionSelectionDTO projSelDTO) throws SystemException, PortalException {
        List<DiscountProjectionResultsDTO> resultList = new ArrayList<DiscountProjectionResultsDTO>();
        if (!projSelDTO.isIsFilter() || (parentId instanceof DiscountProjectionResultsDTO)) {

            projSelDTO.setYear(ALL.getConstant());
            if (BOTH.getConstant().equals(projSelDTO.getActualsOrProjections())) {
                projSelDTO.setActualsOrProjections(ACTUALS.getConstant() + " and " + PROJECTIONS.getConstant());
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
    public List<DiscountProjectionResultsDTO> configureLevels(int start, int offset, ProjectionSelectionDTO projSelDTO) throws SystemException, PortalException {
        int neededRecord = offset;
        List<DiscountProjectionResultsDTO> resultList = new ArrayList<DiscountProjectionResultsDTO>();
        if (neededRecord > 0) {
            List<Leveldto> levelList = CommonLogic.getConditionalLevelList(projSelDTO.getProjectionId(), start, offset, projSelDTO.getHierarchyIndicator(), projSelDTO.getTreeLevelNo(), projSelDTO.getHierarchyNo(), projSelDTO.getProductHierarchyNo(), projSelDTO.getCustomerHierarchyNo(), projSelDTO.isIsFilter(), false, projSelDTO.isIsCustomHierarchy(), projSelDTO.getCustomId(), projSelDTO.getGroupFilter(), projSelDTO.getUserId(), projSelDTO.getSessionId(), projSelDTO.getCustRelationshipBuilderSid(), projSelDTO.getProdRelationshipBuilderSid(), false, true);
            for (int i = 0; i < levelList.size() && neededRecord > 0; i++) {
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
                DiscountProjectionResultsDTO discountDTO = new DiscountProjectionResultsDTO();
                if (Constant.PERIOD.equals(projSelDTO.getPivotView())) {
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
                neededRecord--;
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
    public int getConfiguredProjectionResultsCount(Object parentId, ProjectionSelectionDTO projSelDTO, boolean isLevelsCount) throws SystemException, PortalException {

        int count = 0;
        if (!projSelDTO.isIsFilter() || (parentId instanceof DiscountProjectionResultsDTO)) {
            projSelDTO.setYear(ALL.getConstant());
            if (BOTH.getConstant().equals(projSelDTO.getActualsOrProjections())) {
                projSelDTO.setActualsOrProjections(ACTUALS.getConstant() + " and " + PROJECTIONS.getConstant());
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

    public int getProjectionResultsCount(ProjectionSelectionDTO projSelDTO, boolean isLevelsCount) throws SystemException, PortalException {
        int count = 0;
        projSelDTO.setGroupCount(false);
        if (!projSelDTO.getGroup().startsWith(Constant.DISCOUNT)) {
            if (projSelDTO.getPivotView().contains(PERIOD.getConstant())) {
                if (projSelDTO.isIsProjectionTotal()) {
                    count = count + projSelDTO.getDiscountNoList().size();
                } else {
                    String query = CommonLogic.getCCPQuery(projSelDTO,Boolean.FALSE) + getDiscountCountForCurrentHierarchy(projSelDTO);
                    List<Object> list = (List<Object>) CommonLogic.executeSelectQuery(query, null, null);
                    if (list != null && !list.isEmpty()) {
                        Object ob = list.get(0);
                        count = count + Integer.valueOf(String.valueOf(ob));;
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
                    && !"All Discount Groups".equals(projSelDTO.getGroupFilter()) && !projSelDTO.getGroup().startsWith(Constant.DISCOUNT)) {
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

    public int configureLevelsCount(ProjectionSelectionDTO projSelDTO) throws SystemException, PortalException {
        return CommonLogic.getLevelListCount(projSelDTO.getProjectionId(), projSelDTO.getHierarchyIndicator(), projSelDTO.getTreeLevelNo(), projSelDTO.getHierarchyNo(), projSelDTO.getProductHierarchyNo(), projSelDTO.getCustomerHierarchyNo(), projSelDTO.isIsFilter(), projSelDTO.isIsCustomHierarchy(), projSelDTO.getCustomId(), projSelDTO.getGroupFilter(), projSelDTO.getUserId(), projSelDTO.getSessionId(), projSelDTO.getCustRelationshipBuilderSid(), projSelDTO.getProdRelationshipBuilderSid());
    }

    /**
     * This method is used to Load aggregate value of child node
     *
     * @param dto
     * @param projSelDTO
     * @return
     *
     */
    public DiscountProjectionResultsDTO getChildNodeValues(DiscountProjectionResultsDTO dto, ProjectionSelectionDTO projSelDTO, List ccp) throws SystemException, PortalException {
        int projectionMasterId = projSelDTO.getProjectionId();
        dto.setIsParent(Constant.STRING_ONE);
        dto.setParent(1);
        String hierachyNumber = String.valueOf(dto.getHierarchyNo());
        hierachyNumber = hierachyNumber + PERCENTAGE;
        List<Integer> yearList = new ArrayList<Integer>();
        yearList.add(projSelDTO.getForecastDTO().getHistoryStartYear());
        yearList.add(projSelDTO.getForecastDTO().getHistoryStartMonth());
        yearList.add(projSelDTO.getForecastDTO().getHistoryEndYear());
        yearList.add(projSelDTO.getForecastDTO().getHistoryEndMonth());
          String userId = String.valueOf(projSelDTO.getUserId());
        String sessionId = String.valueOf(projSelDTO.getSessionId());
        int projId=projSelDTO.getProjectionId();
         int divideRPU = 0;
        if ( isProgramcategory(projSelDTO.getProjectionId())) {
            divideRPU=getDiscountRSCount(userId, sessionId, projId);
        }

        yearList.add(projSelDTO.getForecastDTO().getForecastStartYear());
        String freq = String.valueOf(projSelDTO.getFrequency());
        if (QUARTERLY.getConstant().equals(freq)) {
            int month = projSelDTO.getForecastDTO().getForecastStartMonth();
            if (month <= 3) {
                yearList.add(1);
            } else if (month >= 4 && month < 7) {
                yearList.add(4);
            } else if (month >= 7 && month < 10) {
                yearList.add(7);
            } else if (month >= 10 && month < 13) {
                yearList.add(10);
            }
        } else if (SEMI_ANNUALLY.getConstant().equals(freq)) {
            int month = projSelDTO.getForecastDTO().getForecastStartMonth();
            if (month <= 6) {
                yearList.add(1);
            } else if (month >= 7 && month < 13) {
                yearList.add(7);
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
        if (ccp == null) {
            if (CUSTOMER.getConstant().equals(selectedView)) {
                ccpId = NmDiscountProjMasterLocalServiceUtil.getCCPDetailsID(projectionMasterId, hierachyNumber, String.valueOf(dto.getTreeLevelNo()));
            }
            if (PRODUCT.getConstant().equals(selectedView)) {
                ccpId = NmDiscountProjMasterLocalServiceUtil.getCCPDetailsIDForProductHierarchy(projectionMasterId, hierachyNumber, String.valueOf(dto.getTreeLevelNo()));
            }
            if (CUSTOM.getConstant().equals(selectedView)) {
                projSelDTO.setIsCustomHierarchy(true);
                String customQuery = CommonLogic.getCustomCCPQuery(projSelDTO);
                List<Object> list = (List<Object>) executeSelectQuery(customQuery, null, null);
                if (list != null && !list.isEmpty()) {
                    ccpId = new ArrayList();
                    for (int i = 0; i < list.size(); i++) {
                        Object[] obj = (Object[]) list.get(i);
                        String id = String.valueOf(obj[1]);
                        if (!NULL.equals(id)) {
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
            final DynamicQuery projectionDetailsDynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionDetails.class);
            projectionDetailsDynamicQuery.add(RestrictionsFactoryUtil.in(CCP_DETAILS_SID, ccpId));
            projectionDetailsDynamicQuery.add(RestrictionsFactoryUtil.eq(PROJECTION_MASTER_SID, projectionMasterId));
            ProjectionList projList = ProjectionFactoryUtil.projectionList();
            projList.add(ProjectionFactoryUtil.property(PROJECTION_DETAILS_SID));
            projectionDetailsDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projList));
            List<Integer> projectionDetailsId = ProjectionDetailsLocalServiceUtil.dynamicQuery(projectionDetailsDynamicQuery);
            if (projectionDetailsId != null && !projectionDetailsId.isEmpty()) {
               userId = String.valueOf(projSelDTO.getUserId());
               sessionId = String.valueOf(projSelDTO.getSessionId());
                int user = Integer.parseInt(userId);
                int session = Integer.parseInt(sessionId);
                String frequency = projSelDTO.getFrequency();
                List<String> discountList = new ArrayList<String>();
                discountList = projSelDTO.getDiscountNameList();
                String discountString = getDiscountName(discountList);
                projSelDTO.setCcpCount(projectionDetailsId.size());
                List list = NmDiscountProjMasterLocalServiceUtil.getDiscountProjectionResults(projectionDetailsId, frequency, discountString, StringUtils.EMPTY, Constant.PARENT, StringUtils.EMPTY, yearList, user, session);
                if (list != null && !list.isEmpty()) {
                    if (QUARTERLY.getConstant().equals(frequency)) {
                        dto = getValueForDTO(projSelDTO, list, dto, Q_SMALL,divideRPU);
                    }
                    if (SEMI_ANNUALLY.getConstant().equals(frequency)) {
                        dto = getValueForDTO(projSelDTO, list, dto, Constant.S_SMALL,divideRPU);
                    }
                    if (ANNUALLY.getConstant().equals(frequency)) {
                        dto = getValueForYearDTO(projSelDTO, list, dto,divideRPU);
                    }
                    if (MONTHLY.getConstant().equals(frequency)) {
                        dto = getValueForMonthDTO(projSelDTO, list, dto,divideRPU);
                    }
                }
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
    public DiscountProjectionResultsDTO getPivotChildNodeValues(DiscountProjectionResultsDTO dto, ProjectionSelectionDTO projSelDTO) throws SystemException, PortalException {
        int projectionMasterId = projSelDTO.getProjectionId();
        dto.setIsParent(Constant.STRING_ONE);
        dto.setParent(1);
        String hierachyNumber = String.valueOf(dto.getHierarchyNo());
        hierachyNumber = hierachyNumber + PERCENTAGE;
        List<Integer> pivotYearList = new ArrayList<Integer>();
        String frequency = String.valueOf(projSelDTO.getFrequency());
        if (ANNUALLY.getConstant().equals(frequency)) {
            pivotYearList.add(Integer.valueOf(projSelDTO.getPeriodList().get(0)));
            pivotYearList.add(1);
            pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndYear());
            pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndMonth());
        } else if (QUARTERLY.getConstant().equals(frequency)) {
            String column = String.valueOf(projSelDTO.getPeriodList().get(0));
            column = column.replace(Q_SMALL, StringUtils.EMPTY);
            String year = column.substring(column.length() - 4, column.length());
            String fre = column.replace(year, StringUtils.EMPTY);
            pivotYearList.add(Integer.valueOf(year));
            if (ONE.equals(fre)) {
                pivotYearList.add(1);
            } else if (TWO.equals(fre)) {
                pivotYearList.add(4);
            } else if (THREE.equals(fre)) {
                pivotYearList.add(7);
            } else if (FOUR.equals(fre)) {
                pivotYearList.add(10);
            }
            pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndYear());
            pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndMonth());
        } else if (MONTHLY.getConstant().equals(frequency)) {
            loadKeyMap();
            String column = String.valueOf(projSelDTO.getPeriodList().get(0));
            String fre = column.substring(0, 3);
            String year = column.replace(fre, StringUtils.EMPTY);
            String month = valueMap.get(fre);
            pivotYearList.add(Integer.valueOf(year));
            pivotYearList.add(Integer.valueOf(month));
            pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndYear());
            pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndMonth());
        } else if (SEMI_ANNUALLY.getConstant().equals(frequency)) {
            String column = String.valueOf(projSelDTO.getPeriodList().get(0));
            column = column.replace(Constant.S_SMALL, StringUtils.EMPTY);
            String year = column.substring(column.length() - 4, column.length());
            String fre = column.replace(year, StringUtils.EMPTY);
            pivotYearList.add(Integer.valueOf(year));
            if (ONE.equals(fre)) {
                pivotYearList.add(1);
            } else if (TWO.equals(fre)) {
                pivotYearList.add(7);
            }
            pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndYear());
            pivotYearList.add(projSelDTO.getForecastDTO().getHistoryEndMonth());
        }

        pivotYearList.add(projSelDTO.getForecastDTO().getForecastStartYear());

        String frequ = String.valueOf(projSelDTO.getFrequency());
        if (QUARTERLY.getConstant().equals(frequ)) {
            int month = projSelDTO.getForecastDTO().getForecastStartMonth();
            if (month <= 3) {
                pivotYearList.add(1);
            } else if (month >= 4 && month < 7) {
                pivotYearList.add(4);
            } else if (month >= 7 && month < 10) {
                pivotYearList.add(7);
            } else if (month >= 10 && month < 13) {
                pivotYearList.add(10);
            }
        } else if (SEMI_ANNUALLY.getConstant().equals(frequ)) {
            int month = projSelDTO.getForecastDTO().getForecastStartMonth();
            if (month <= 6) {
                pivotYearList.add(1);
            } else if (month >= 7 && month < 13) {
                pivotYearList.add(7);
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
        if (CUSTOMER.getConstant().equals(selectedView)) {
            ccpId = NmDiscountProjMasterLocalServiceUtil.getCCPDetailsID(projectionMasterId, hierachyNumber, String.valueOf(dto.getTreeLevelNo()));
        }
        if (PRODUCT.getConstant().equals(selectedView)) {
            ccpId = NmDiscountProjMasterLocalServiceUtil.getCCPDetailsIDForProductHierarchy(projectionMasterId, hierachyNumber, String.valueOf(dto.getTreeLevelNo()));
        }
        if (CUSTOM.getConstant().equals(selectedView)) {
            projSelDTO.setIsCustomHierarchy(true);
            String customQuery = CommonLogic.getCustomCCPQuery(projSelDTO);
            List<Object> list = (List<Object>) executeSelectQuery(customQuery, null, null);
            if (list != null && !list.isEmpty()) {
                ccpId = new ArrayList();
                for (int i = 0; i < list.size(); i++) {
                    Object[] obj = (Object[]) list.get(i);
                    String id = String.valueOf(obj[1]);
                    if (!NULL.equals(id)) {
                        int ccpIds = Integer.parseInt(id);
                        ccpId.add(ccpIds);
                    }
                }
            }
        }
        if (ccpId != null && !ccpId.isEmpty()) {
               List<Object> view = new ArrayList<Object>();
             view.add(projSelDTO.getProjectionOrder());
            final DynamicQuery projectiondetailsDynamicQuery = DynamicQueryFactoryUtil.forClass(ProjectionDetails.class);
            projectiondetailsDynamicQuery.add(RestrictionsFactoryUtil.eq(PROJECTION_MASTER_SID, projectionMasterId));
            projectiondetailsDynamicQuery.add(RestrictionsFactoryUtil.in(CCP_DETAILS_SID, ccpId));
            final ProjectionList projList = ProjectionFactoryUtil.projectionList();
            projList.add(ProjectionFactoryUtil.property(PROJECTION_DETAILS_SID));
            projectiondetailsDynamicQuery.setProjection(ProjectionFactoryUtil.distinct(projList));
            List proDetailsSid = ProjectionDetailsLocalServiceUtil.dynamicQuery(projectiondetailsDynamicQuery);
            if (proDetailsSid != null && !proDetailsSid.isEmpty()) {
                String freq = String.valueOf(projSelDTO.getFrequency());
                String userId = String.valueOf(projSelDTO.getUserId());
                String sessionId = String.valueOf(projSelDTO.getSessionId());
                int user = Integer.parseInt(userId);
                int session = Integer.parseInt(sessionId);
                List<String> discountList = new ArrayList<String>();
                List<String> tmpList = new ArrayList<String>();
                discountList = projSelDTO.getDiscountNameList();
                String discountString = getDiscountName(discountList);
                List list = NmDiscountProjMasterLocalServiceUtil.getTotalDiscountNumber(proDetailsSid, freq, discountString, pivotYearList, user, session,view);
                if (list != null && !list.isEmpty()) {
                    DiscountProjectionResultsDTO discountDto = new DiscountProjectionResultsDTO();
                    discountDto.setHierarchySid(ZERO);
                    discountDto.setLevelNo(0);
                    discountDto.setHierarchylevelId(ZERO);
                    discountDto.setIsParent(ONE);
                    discountDto.setHierarchyNo(ONE);
                    discountDto.setTotal(1);
                    dto.setParent(1);
                    double actualSales = 0;
                    double actualAmount = 0;
                    double projectedSales = 0;
                    double projectedAmount = 0;
                    double projectedRate = 0;
                    double actualRPU = 0;
                    double projectedRPU = 0;
                    String commonColumn = StringUtils.EMPTY;
                    Object[] object = (Object[]) list.get(0);
                    String currentDiscount = String.valueOf(object[8]);
                    tmpList.addAll(discountList);
                    tmpList.remove(currentDiscount);
                    if (object[2] != null) {
                        Double aSales = Double.parseDouble(String.valueOf(object[2]));
                        actualSales = actualSales + aSales;
                    }
                    if (object[3] != null) {
                        Double aAmount = Double.parseDouble(String.valueOf(object[3]));
                        actualAmount = actualAmount + aAmount;
                    }
                    if (object[4] != null) {
                        Double pSales = Double.parseDouble(String.valueOf(object[4]));
                        projectedSales = projectedSales + pSales;
                    }
                    if (object[5] != null) {
                        Double pAmount = Double.parseDouble(String.valueOf(object[5]));
                        projectedAmount = projectedAmount + pAmount;
                    }
                    if (object[9] != null) {
                        Double projRate = Double.parseDouble(String.valueOf(object[9]));
                        projectedRate = projectedRate + projRate;
                    }
                    if (object[10] != null) {
                        Double acRPU = Double.parseDouble(String.valueOf(object[10]));
                        actualRPU = actualRPU + acRPU;
                    }
                    if (object[11] != null) {
                        Double prRPU = Double.parseDouble(String.valueOf(object[11]));
                        projectedRPU = projectedRPU + prRPU;
                    }

                    commonColumn = currentDiscount.replace(" ", StringUtils.EMPTY);
                    if (list.size() == 1) {
                        Double arate = actualAmount / actualSales;
                        if (arate.isNaN() || arate.isInfinite()) {
                            arate = 0.0;
                        }
                        arate = arate * 100;
                        String actRate = String.valueOf(arate);
                        dto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                        Double actualAmt = arate * actualSales;
                        if (actualAmt.isNaN() || actualAmt.isInfinite()) {
                            actualAmt = 0.0;
                        }
                        String actAmt = String.valueOf(actualAmt);
                        dto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                        Double prate = projectedAmount / projectedSales;
                        if (prate.isNaN() || prate.isInfinite()) {
                            prate = 0.0;
                        }
                        prate = prate * 100;

                        String proRate = String.valueOf(prate);
                        dto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                        Double projectedAmtAmt =projectedAmount;
                        if (projectedAmtAmt.isNaN() || projectedAmtAmt.isInfinite()) {
                            projectedAmtAmt = 0.0;
                        }
                        String proAmount = String.valueOf(projectedAmtAmt);
                        dto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                        discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                        Double aRPU = actualAmt / actualRPU;
                        if (aRPU.isNaN() || aRPU.isInfinite()) {
                            aRPU = 0.0;
                        }
                        String actRPU = String.valueOf(aRPU);
                        discountDto.addStringProperties(commonColumn + ACTUALRPU, actRPU != null && !NULL.equals(String.valueOf(actRPU)) && !StringUtils.EMPTY.equals(String.valueOf(actRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(actRPU)))) : HYPHEN);
                        Double pRPU = projectedAmount / projectedRPU;
                        if (pRPU.isNaN() || pRPU.isInfinite()) {
                            pRPU = 0.0;
                        }
                        String proRPU = String.valueOf(pRPU);
                        discountDto.addStringProperties(commonColumn + PROJECTEDRPU, proRPU != null && !NULL.equals(String.valueOf(proRPU)) && !StringUtils.EMPTY.equals(String.valueOf(proRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(proRPU)))) : HYPHEN);
                    } else {
                        for (int i = 1; i < list.size(); i++) {
                            Object[] obj = (Object[]) list.get(i);
                            String selectedDiscount = String.valueOf(obj[8]);
                            if (currentDiscount.equals(selectedDiscount)) {
                                if (obj[2] != null) {
                                    Double aSales = Double.parseDouble(String.valueOf(obj[2]));
                                    actualSales = actualSales + aSales;
                                }
                                if (obj[3] != null) {
                                    Double aAmount = Double.parseDouble(String.valueOf(obj[3]));
                                    actualAmount = actualAmount + aAmount;
                                }
                                if (obj[4] != null) {
                                    Double pSales = Double.parseDouble(String.valueOf(obj[4]));
                                    projectedSales = projectedSales + pSales;
                                }
                                if (obj[5] != null) {
                                    Double pAmount = Double.parseDouble(String.valueOf(obj[5]));
                                    projectedAmount = projectedAmount + pAmount;
                                }
                                if (obj[9] != null) {
                                    Double projRate = Double.parseDouble(String.valueOf(obj[9]));
                                    projectedRate = projectedRate + projRate;
                                }
                                if (object[10] != null) {
                                    Double acRPU = Double.parseDouble(String.valueOf(object[10]));
                                    actualRPU = actualRPU + acRPU;
                                }
                                if (object[11] != null) {
                                    Double prRPU = Double.parseDouble(String.valueOf(object[11]));
                                    projectedRPU = projectedRPU + prRPU;
                                }
                            } else {
                                Double arate = actualAmount / actualSales;
                                if (arate.isNaN() || arate.isInfinite()) {
                                    arate = 0.0;
                                }
                                arate = arate * 100;
                                String actRate = String.valueOf(arate);

                                dto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                Double actualAmt = arate * actualSales;
                                if (actualAmt.isNaN() || actualAmt.isInfinite()) {
                                    actualAmt = 0.0;
                                }
                                String actAmt = String.valueOf(actualAmt);
                                dto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                                Double prate = projectedAmount / projectedSales;

                                if (prate.isNaN() || prate.isInfinite()) {
                                    prate = 0.0;
                                }
                                prate = prate * 100;

                                String proRate = String.valueOf(prate);
                                dto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                Double projectedAmtAmt =projectedAmount;
                                if (projectedAmtAmt.isNaN() || projectedAmtAmt.isInfinite()) {
                                    projectedAmtAmt = 0.0;
                                }
                                String proAmount = String.valueOf(projectedAmtAmt);
                                dto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                Double aRPU = actualAmt / actualRPU;
                                if (aRPU.isNaN() || aRPU.isInfinite()) {
                                    aRPU = 0.0;
                                }
                                String actRPU = String.valueOf(aRPU);
                                discountDto.addStringProperties(commonColumn + ACTUALRPU, actRPU != null && !NULL.equals(String.valueOf(actRPU)) && !StringUtils.EMPTY.equals(String.valueOf(actRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(actRPU)))) : HYPHEN);
                                Double pRPU = projectedAmount / projectedRPU;
                                if (pRPU.isNaN() || pRPU.isInfinite()) {
                                    pRPU = 0.0;
                                }
                                String proRPU = String.valueOf(pRPU);
                                discountDto.addStringProperties(commonColumn + PROJECTEDRPU, proRPU != null && !NULL.equals(String.valueOf(proRPU)) && !StringUtils.EMPTY.equals(String.valueOf(proRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(proRPU)))) : HYPHEN);
                                commonColumn = StringUtils.EMPTY;
                                actualSales = 0;
                                actualAmount = 0;
                                projectedSales = 0;
                                projectedAmount = 0;
                                projectedRate = 0;
                                actualRPU = 0;
                                projectedRPU = 0;
                                currentDiscount = selectedDiscount;
                                tmpList.remove(currentDiscount);
                                commonColumn = currentDiscount.replace(" ", StringUtils.EMPTY);
                                if (obj[2] != null) {
                                    Double aSales = Double.parseDouble(String.valueOf(obj[2]));
                                    actualSales = actualSales + aSales;
                                }
                                if (obj[3] != null) {
                                    Double aAmount = Double.parseDouble(String.valueOf(obj[3]));
                                    actualAmount = actualAmount + aAmount;
                                }
                                if (obj[4] != null) {
                                    Double pSales = Double.parseDouble(String.valueOf(obj[4]));
                                    projectedSales = projectedSales + pSales;
                                }
                                if (obj[5] != null) {
                                    Double pAmount = Double.parseDouble(String.valueOf(obj[5]));
                                    projectedAmount = projectedAmount + pAmount;
                                }
                                if (obj[9] != null) {
                                    Double projRate = Double.parseDouble(String.valueOf(obj[9]));
                                    projectedRate = projectedRate + projRate;
                                }
                                if (object[10] != null) {
                                    Double acRPU = Double.parseDouble(String.valueOf(object[10]));
                                    actualRPU = actualRPU + acRPU;
                                }
                                if (object[11] != null) {
                                    Double prRPU = Double.parseDouble(String.valueOf(object[11]));
                                    projectedRPU = projectedRPU + prRPU;
                                }

                            }
                            if (i == list.size() - 1) {
                                Double arate = actualAmount / actualSales;
                                if (arate.isNaN() || arate.isInfinite()) {
                                    arate = 0.0;
                                }
                                arate = arate * 100;
                                String actRate = String.valueOf(arate);
                                dto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                                Double actualAmt = arate * actualSales;
                                if (actualAmt.isNaN() || actualAmt.isInfinite()) {
                                    actualAmt = 0.0;
                                }
                                String actAmt = String.valueOf(actualAmt);
                                dto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                                Double prate = projectedAmount / projectedSales;

                                if (prate.isNaN() || prate.isInfinite()) {
                                    prate = 0.0;
                                }
                                prate = prate * 100;
                                String proRate = String.valueOf(prate);
                                dto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                                Double projectedAmtAmt = projectedAmount;
                                if (projectedAmtAmt.isNaN()) {
                                    projectedAmtAmt = 0.0;
                                }
                                String proAmount = String.valueOf(projectedAmtAmt);
                                dto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                                Double aRPU = actualAmt / actualRPU;
                                if (aRPU.isNaN() || aRPU.isInfinite()) {
                                    aRPU = 0.0;
                                }
                                String actRPU = String.valueOf(aRPU);
                                discountDto.addStringProperties(commonColumn + ACTUALRPU, actRPU != null && !NULL.equals(String.valueOf(actRPU)) && !StringUtils.EMPTY.equals(String.valueOf(actRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(actRPU)))) : HYPHEN);
                                Double pRPU = projectedAmount / projectedRPU;
                                if (pRPU.isNaN() || pRPU.isInfinite()) {
                                    pRPU = 0.0;
                                }
                                String proRPU = String.valueOf(pRPU);
                                discountDto.addStringProperties(commonColumn + PROJECTEDRPU, proRPU != null && !NULL.equals(String.valueOf(proRPU)) && !StringUtils.EMPTY.equals(String.valueOf(proRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(proRPU)))) : HYPHEN);
                            }
                        }
                    }
                    if (!tmpList.isEmpty()) {
                        for (int j = 0; j < tmpList.size(); j++) {
                            String column = tmpList.get(j);
                            commonColumn = column.replace(" ", StringUtils.EMPTY);
                            String columns = commonColumn + ACTUALSRATE;
                            dto.addStringProperties(columns, getFormattedValue(CUR_ZERO, NULL));
                            String columns1 = commonColumn + ACTUALSAMOUNT;
                            dto.addStringProperties(columns1, getFormattedValue(CUR_ZERO, NULL));
                            String columns2 = commonColumn + PROJECTIONSAMOUNT;
                            dto.addStringProperties(columns2, getFormattedValue(CUR_ZERO, NULL));
                            String columns3 = commonColumn + PROJECTIONSRATE;
                            dto.addStringProperties(columns3, getFormattedValue(CUR_ZERO, NULL));
                            String columns4 = commonColumn + ACTUALRPU;
                            dto.addStringProperties(columns4, getFormattedValue(CUR_ZERO, NULL));
                            String columns5 = commonColumn + PROJECTEDRPU;
                            dto.addStringProperties(columns5, getFormattedValue(CUR_ZERO, NULL));
                        }
                    }

                    return dto;
                }
            }
        }
        return dto;
    }

    public String getGroupFilterQuery(int projectionMasterId, String userGroup, int levelNo, int sessionId) {
        return "Select Distinct HLD.RELATIONSHIP_LEVEL_VALUES FROM (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO,"
                + " CCP.CCP_DETAILS_SID FROM   RELATIONSHIP_LEVEL_DEFINITION RLD JOIN   CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID = CCP.RELATIONSHIP_LEVEL_SID"
                + " JOIN   PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID =" + projectionMasterId + StringUtils.EMPTY
                + " JOIN ST_NM_DISCOUNT_PROJ_MASTER A1 ON A1.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID WHERE  A1.USER_GROUP='" + userGroup + "' AND A1.SESSION_ID=" + sessionId + StringUtils.EMPTY
                + " )CCPMAP ,(SELECT RLD1.HIERARCHY_NO, RLD1.RELATIONSHIP_LEVEL_SID, RLD1.RELATIONSHIP_LEVEL_VALUES, RLD1.LEVEL_NO,"
                + " RLD1.LEVEL_NAME FROM   RELATIONSHIP_LEVEL_DEFINITION RLD1 JOIN   PROJECTION_CUST_HIERARCHY  PCH ON PCH.RELATIONSHIP_LEVEL_SID = RLD1.RELATIONSHIP_LEVEL_SID"
                + " AND PCH.PROJECTION_MASTER_SID =" + projectionMasterId + " WHERE  RLD1.HIERARCHY_NO LIKE '%' AND RLD1.LEVEL_NO >=" + levelNo + " ) HLD WHERE  CCPMAP.HIERARCHY_NO"
                + " LIKE HLD.HIERARCHY_NO+'%'";
    }

    public String getGroup(int projectionMasterId, int sessionId) {
        return "SELECT DISTINCT M.USER_GROUP FROM ST_NM_DISCOUNT_PROJ_MASTER M"
                + "  JOIN PROJECTION_DETAILS PD ON M.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID"
                + " JOIN PROJECTION_CUST_HIERARCHY PCH ON PCH.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID"
                + " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD ON RLD.RELATIONSHIP_LEVEL_SID = PCH.RELATIONSHIP_LEVEL_SID"
                + " WHERE RLD.LEVEL_NAME = 'Trading Partner' AND M.USER_GROUP IS NOT NULL AND PD.PROJECTION_MASTER_SID ='" + projectionMasterId + "' "
                + " AND M.SESSION_ID=" + sessionId;
    }

    public String getTradingPartnerLevel(int projectionMasterId) {
        return "SELECT distinct RLD1.level_no from  relationship_level_definition RLD1 JOIN PROJECTION_CUST_HIERARCHY PCH "
                + " ON PCH.relationship_level_sid = RLD1.relationship_level_sid  AND PCH.projection_master_sid =" + projectionMasterId + StringUtils.EMPTY
                + " WHERE RLD1.level_Name='Trading Partner'";
    }

    public String getFormattedValue(DecimalFormat format, String value) {
        if (value.contains(NULL)) {
            value = DASH.getConstant();
        } else {
            Double newValue = Double.valueOf(value);
            if (format.toPattern().contains(Constant.PERCENT)) {
                newValue = newValue / 100;
            }
            value = format.format(newValue);
        }
        return value;
    }

    private String createColumn(String group) {
        String value = group.replace(CommonUtils.BUSINESS_PROCESS_INDICATOR_MANDATED, StringUtils.EMPTY);
        String year = value.substring(value.length() - 4, value.length());
        String mon = value.replace(year, StringUtils.EMPTY);
        String month = monthMap.get(mon);
        return month + year;
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

    private DiscountProjectionResultsDTO getValueForDTO(ProjectionSelectionDTO projSelDTO, List list, DiscountProjectionResultsDTO discountDto, String freq,int divideRPU) {
        double actualSales = 0;
        double actualAmount = 0;
        double actualRPU = 0;
        double projectedSales = 0;
        double projectedAmount = 0;
        double projectedRPU = 0;
        double projectedRate = 0;
        int year = 0;
        int quarter = 0;
        int frequncyDivision = Q_SMALL.equals(freq) ? 3 : 6;
        List<String> periodList = new ArrayList<String>(projSelDTO.getPeriodList());
        String commonColumn = StringUtils.EMPTY;
        for (int i = 0; i < list.size(); i++) {
            final Object[] obj = (Object[]) list.get(i);
            int selectedYear = 0;
            int selectedQuarter = 0;
            if (obj[0] != null) {
                selectedYear = (Integer) obj[0];
            }
            if (obj[6] != null) {
                selectedQuarter = (Integer) obj[6];
            }
            if (year == selectedYear && quarter == selectedQuarter) {
                if (obj[2] != null) {
                    Double aSales = Double.parseDouble(String.valueOf(obj[2]));
                    actualSales = actualSales + aSales;
                }
                if (obj[3] != null) {
                    Double aAmount = Double.parseDouble(String.valueOf(obj[3]));
                    actualAmount = actualAmount + aAmount;
                }
                if (obj[4] != null) {
                    Double pSales = Double.parseDouble(String.valueOf(obj[4]));
                    projectedSales = projectedSales + pSales;
                }
                if (obj[5] != null) {
                    Double pAmount = Double.parseDouble(String.valueOf(obj[5]));
                    projectedAmount = projectedAmount + pAmount;
                }
                if (obj[8] != null) {
                    Double proRate = Double.parseDouble(String.valueOf(obj[8]));
                    projectedRate = projectedRate + proRate;
                }
                if (obj[9] != null) {
                    Double aRPU = Double.parseDouble(String.valueOf(obj[9]));
                    actualRPU = actualRPU + aRPU;
                }
                if (obj[10] != null) {
                    Double pRPU = Double.parseDouble(String.valueOf(obj[10]));
                    projectedRPU = projectedRPU + pRPU;
                }

            } else {
                if (i == 0) {
                    year = selectedYear;
                    quarter = selectedQuarter;
                    commonColumn = freq + obj[6] + obj[0];
                    if (obj[2] != null) {
                        Double aSales = Double.parseDouble(String.valueOf(obj[2]));
                        actualSales = actualSales + aSales;
                    }
                    if (obj[3] != null) {
                        Double aAmount = Double.parseDouble(String.valueOf(obj[3]));
                        actualAmount = actualAmount + aAmount;
                    }

                    if (obj[4] != null) {
                        Double pSales = Double.parseDouble(String.valueOf(obj[4]));
                        projectedSales = projectedSales + pSales;
                    }
                    if (obj[5] != null) {
                        Double pAmount = Double.parseDouble(String.valueOf(obj[5]));
                        projectedAmount = projectedAmount + pAmount;
                    }
                    if (obj[8] != null) {
                        Double proRate = Double.parseDouble(String.valueOf(obj[8]));
                        projectedRate = projectedRate + proRate;
                    }
                    if (obj[9] != null) {
                        Double aRPU = Double.parseDouble(String.valueOf(obj[9]));
                        actualRPU = actualRPU + aRPU;
                    }
                    if (obj[10] != null) {
                        Double pRPU = Double.parseDouble(String.valueOf(obj[10]));
                        projectedRPU = projectedRPU + pRPU;
                    }
                } else {
                    Double arate = actualAmount / actualSales;
                    if (arate.isNaN() || arate.isInfinite()) {
                        arate = 0.0;
                    }
                    arate = arate * 100;
                    String actualsRate = String.valueOf(arate);
                    discountDto.addStringProperties(commonColumn + ACTUALSRATE, actualsRate != null && !NULL.equals(String.valueOf(actualsRate)) && !StringUtils.EMPTY.equals(String.valueOf(actualsRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actualsRate)))).concat(PERCENTAGE) : HYPHEN);

                    String actualsAmount = String.valueOf(actualAmount);
                    discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actualsAmount != null && !NULL.equals(String.valueOf(actualsAmount)) && !StringUtils.EMPTY.equals(String.valueOf(actualsAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actualsAmount)))) : HYPHEN);
                    Double prate = projectedAmount / projectedSales;
                    prate = prate * 100;

                    if (prate.isNaN() || prate.isInfinite()) {
                        prate = 0.0;
                    }
                    String prorate = String.valueOf(prate);
                    discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, prorate != null && !NULL.equals(String.valueOf(prorate)) && !StringUtils.EMPTY.equals(String.valueOf(prorate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(prorate)))).concat(PERCENTAGE) : HYPHEN);

                    String proAmount = String.valueOf(projectedAmount);
                    discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                    Double acRPU = actualAmount / actualRPU;
                    acRPU*=(divideRPU==0? 1:divideRPU);
                    if(acRPU.isNaN() || acRPU.isInfinite()){
                        acRPU = 0.0;
                    }
                    String actRPU = String.valueOf(acRPU);
                    discountDto.addStringProperties(commonColumn + ACTUALRPU, actRPU != null && !NULL.equals(String.valueOf(actRPU)) && !StringUtils.EMPTY.equals(String.valueOf(actRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(actRPU)))) : HYPHEN);
                    Double proRPU = projectedAmount / projectedRPU;
                         proRPU*=(divideRPU==0? 1:divideRPU);
                    if(proRPU.isNaN() || proRPU.isInfinite()){
                        proRPU = 0.0;
                    }
                    String projRPU = String.valueOf(proRPU);
                    discountDto.addStringProperties(commonColumn + PROJECTEDRPU, projRPU != null && !NULL.equals(String.valueOf(projRPU)) && !StringUtils.EMPTY.equals(String.valueOf(projRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(projRPU)))) : HYPHEN);
                    periodList.remove(commonColumn);
                    commonColumn = StringUtils.EMPTY;
                    actualSales = 0;
                    actualAmount = 0;
                    actualRPU = 0;
                    projectedSales = 0;
                    projectedAmount = 0;
                    projectedRPU = 0;
                    projectedRate = 0;
                    commonColumn = freq + obj[6] + obj[0];
                    year = (Integer) obj[0];
                    quarter = (Integer) obj[6];
                    if (obj[2] != null) {
                        Double aSales = Double.parseDouble(String.valueOf(obj[2]));
                        actualSales = actualSales + aSales;
                    }
                    if (obj[3] != null) {
                        Double aAmount = Double.parseDouble(String.valueOf(obj[3]));
                        actualAmount = actualAmount + aAmount;
                    }
                    if (obj[4] != null) {
                        Double pSales = Double.parseDouble(String.valueOf(obj[4]));
                        projectedSales = projectedSales + pSales;
                    }
                    if (obj[5] != null) {
                        Double pAmount = Double.parseDouble(String.valueOf(obj[5]));
                        projectedAmount = projectedAmount + pAmount;
                    }
                    if (obj[8] != null) {
                        Double proRate = Double.parseDouble(String.valueOf(obj[8]));
                        projectedRate = projectedRate + proRate;
                    }
                    if (obj[9] != null) {
                        Double aRPU = Double.parseDouble(String.valueOf(obj[9]));
                        actualRPU = aRPU;
                    }
                    if (obj[10] != null) {
                        Double pRPU = Double.parseDouble(String.valueOf(obj[10]));
                        projectedRPU = pRPU;
                    }
                }
            }
            if (i == list.size() - 1) {
                Double arate = actualAmount / actualSales;
                if (arate.isNaN() || arate.isInfinite()) {
                    arate = 0.0;
                }
                arate = arate * 100;
                String actualsRate = String.valueOf(arate);
                discountDto.addStringProperties(commonColumn + ACTUALSRATE, actualsRate != null && !NULL.equals(String.valueOf(actualsRate)) && !StringUtils.EMPTY.equals(String.valueOf(actualsRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actualsRate)))).concat(PERCENTAGE) : HYPHEN);

                String actualsAmount = String.valueOf(actualAmount);
                discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actualsAmount != null && !NULL.equals(String.valueOf(actualsAmount)) && !StringUtils.EMPTY.equals(String.valueOf(actualsAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actualsAmount)))) : HYPHEN);

                Double prate = projectedAmount / projectedSales;
                prate = prate * 100;

                if (prate.isNaN() || prate.isInfinite()) {
                    prate = 0.0;
                }
                discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, prate != null && !NULL.equals(String.valueOf(prate)) && !StringUtils.EMPTY.equals(String.valueOf(prate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(prate)))).concat(PERCENTAGE) : HYPHEN);
                Double projectedAmtAmt = projectedAmount;
//                projectedAmtAmt = projectedAmount;
                if (projectedAmtAmt.isNaN() || projectedAmtAmt.isInfinite()) {
                    projectedAmtAmt = 0.0;
                }
                discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, projectedAmtAmt != null && !NULL.equals(String.valueOf(projectedAmtAmt)) && !StringUtils.EMPTY.equals(String.valueOf(projectedAmtAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(projectedAmtAmt)))) : HYPHEN);
                periodList.remove(commonColumn);
                Double aRPU = actualAmount / actualRPU;
                if (aRPU.isNaN() || aRPU.isInfinite()) {
                    aRPU = 0.0;
                }
                    aRPU*=(divideRPU==0? 1:divideRPU);
                String actRPU = String.valueOf(aRPU);
                discountDto.addStringProperties(commonColumn + ACTUALRPU, actRPU != null && !NULL.equals(String.valueOf(actRPU)) && !StringUtils.EMPTY.equals(String.valueOf(actRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(actRPU)))) : HYPHEN);
                Double pRPU = projectedAmtAmt / projectedRPU;
                      pRPU*=(divideRPU==0? 1:divideRPU);
                if (pRPU.isNaN() || pRPU.isInfinite()) {
                    pRPU = 0.0;
                }
                String proRPU = String.valueOf(pRPU);
                discountDto.addStringProperties(commonColumn + PROJECTEDRPU, proRPU != null && !NULL.equals(String.valueOf(proRPU)) && !StringUtils.EMPTY.equals(String.valueOf(proRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(proRPU)))) : HYPHEN);
                commonColumn = StringUtils.EMPTY;
                actualSales = 0;
                actualAmount = 0;
                actualRPU = 0;
                projectedSales = 0;
                projectedAmount = 0;
                projectedRPU = 0;
                projectedRate = 0;
                commonColumn = freq + obj[6] + obj[0];
                year = (Integer) obj[0];
                quarter = (Integer) obj[6];
            }
        }
        discountDto = putHyphenForDTO(periodList, discountDto);
        return discountDto;
    }

    private DiscountProjectionResultsDTO getValueForYearDTO(ProjectionSelectionDTO projSelDTO, List list, DiscountProjectionResultsDTO discountDto,int divideRPU) {
        double actualSales = 0;
        double actualAmount = 0;
        double projectedSales = 0;
        double projectedAmount = 0;
        double projectedRate = 0;
        double actualRPU = 0;
        double projectedRPU = 0;
        int year = 0;
        int frequncyDivision = 12;
        List<String> periodList = new ArrayList<String>(projSelDTO.getPeriodList());
        String commonColumn = StringUtils.EMPTY;
        for (int i = 0; i < list.size(); i++) {
            final Object[] obj = (Object[]) list.get(i);
            int selectedYear = 0;
            if (obj[0] != null) {
                selectedYear = (Integer) obj[0];
            }
            if (year == selectedYear) {
                if (obj[2] != null) {
                    Double aSales = Double.parseDouble(String.valueOf(obj[2]));
                    actualSales = actualSales + aSales;
                }
                if (obj[3] != null) {
                    Double aAmount = Double.parseDouble(String.valueOf(obj[3]));
                    actualAmount = actualAmount + aAmount;
                }
                if (obj[4] != null) {
                    Double pSales = Double.parseDouble(String.valueOf(obj[4]));
                    projectedSales = projectedSales + pSales;
                }
                if (obj[5] != null) {
                    Double pAmount = Double.parseDouble(String.valueOf(obj[5]));
                    projectedAmount = projectedAmount + pAmount;
                }
                if (obj[8] != null) {
                    Double proRate = Double.parseDouble(String.valueOf(obj[8]));
                    projectedRate = projectedRate + proRate;
                }
                if (obj[9] != null) {
                    Double aRPU = Double.parseDouble(String.valueOf(obj[9]));
                    actualRPU = actualRPU + aRPU;
                }
                if (obj[10] != null) {
                    Double pRPU = Double.parseDouble(String.valueOf(obj[10]));
                    projectedRPU = projectedRPU + pRPU;
                }

            } else {
                if (i == 0) {
                    year = selectedYear;
                    commonColumn = StringUtils.EMPTY + obj[0];
                    if (obj[2] != null) {
                        Double aSales = Double.parseDouble(String.valueOf(obj[2]));
                        actualSales = actualSales + aSales;
                    }
                    if (obj[3] != null) {
                        Double aAmount = Double.parseDouble(String.valueOf(obj[3]));
                        actualAmount = actualAmount + aAmount;
                    }
                    if (obj[4] != null) {
                        Double pSales = Double.parseDouble(String.valueOf(obj[4]));
                        projectedSales = projectedSales + pSales;
                    }
                    if (obj[5] != null) {
                        Double pAmount = Double.parseDouble(String.valueOf(obj[5]));
                        projectedAmount = projectedAmount + pAmount;
                    }
                    if (obj[8] != null) {
                        Double proRate = Double.parseDouble(String.valueOf(obj[8]));
                        projectedRate = projectedRate + proRate;
                    }
                    if (obj[9] != null) {
                        Double aRPU = Double.parseDouble(String.valueOf(obj[9]));
                        actualRPU = actualRPU + aRPU;
                    }
                    if (obj[10] != null) {
                        Double pRPU = Double.parseDouble(String.valueOf(obj[10]));
                        projectedRPU = projectedRPU + pRPU;
                    }

                } else {
                    Double arate = actualAmount / actualSales;
                    if (arate.isNaN() || arate.isInfinite()) {
                        arate = 0.0;
                    }
                    arate = arate * 100;
                    discountDto.addStringProperties(commonColumn + ACTUALSRATE, arate != null && !NULL.equals(String.valueOf(arate)) && !StringUtils.EMPTY.equals(String.valueOf(arate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(arate)))).concat(PERCENTAGE) : HYPHEN);
                    Double actualAmt = actualAmount;
//                    actualAmt = actualAmt / 100;
                    if (actualAmt.isNaN() || actualAmt.isInfinite()) {
                        actualAmt = 0.0;
                    }
                    discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actualAmt != null && !NULL.equals(String.valueOf(actualAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actualAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actualAmt)))) : HYPHEN);

                    Double prate = projectedAmount / projectedSales;

                    if (prate.isNaN() || prate.isInfinite()) {
                        prate = 0.0;
                    }

                    discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, prate != null && !NULL.equals(String.valueOf(prate)) && !StringUtils.EMPTY.equals(String.valueOf(prate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(prate)))).concat(PERCENTAGE) : HYPHEN);
                    Double projectedAmtAmt = projectedAmount;
//                    projectedAmtAmt = projectedAmtAmt / 100;
                    if (projectedAmtAmt.isNaN() || projectedAmtAmt.isInfinite()) {
                        projectedAmtAmt = 0.0;
                    }
                    discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, projectedAmtAmt != null && !NULL.equals(String.valueOf(projectedAmtAmt)) && !StringUtils.EMPTY.equals(String.valueOf(projectedAmtAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(projectedAmtAmt)))) : HYPHEN);
                    Double acRPU = actualAmount / actualRPU;
                    if (acRPU.isNaN() || acRPU.isInfinite()) {
                        acRPU = 0.0;
                    }
                     acRPU*=(divideRPU==0? 1:divideRPU);
                    String actRPU = String.valueOf(acRPU);
                    discountDto.addStringProperties(commonColumn + ACTUALRPU, actRPU != null && !NULL.equals(String.valueOf(actRPU)) && !StringUtils.EMPTY.equals(String.valueOf(actRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(actRPU)))) : HYPHEN);
                    Double prRPU = projectedAmount / projectedRPU;
                    if (prRPU.isNaN() || prRPU.isInfinite()) {
                        prRPU = 0.0;
                    }
                     prRPU*=(divideRPU==0? 1:divideRPU);
                    String proRPU = String.valueOf(prRPU);
                    discountDto.addStringProperties(commonColumn + PROJECTEDRPU, proRPU != null && !NULL.equals(String.valueOf(proRPU)) && !StringUtils.EMPTY.equals(String.valueOf(proRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(proRPU)))) : HYPHEN);

                    periodList.remove(commonColumn);
                    commonColumn = StringUtils.EMPTY;
                    actualSales = 0;
                    actualAmount = 0;
                    projectedSales = 0;
                    projectedAmount = 0;
                    projectedRate = 0;
                    actualRPU = 0;
                    projectedRPU = 0;
                    commonColumn = StringUtils.EMPTY + obj[0];
                    year = (Integer) obj[0];
                    if (obj[2] != null) {
                        Double aSales = Double.parseDouble(String.valueOf(obj[2]));
                        actualSales = actualSales + aSales;
                    }
                    if (obj[3] != null) {
                        Double aAmount = Double.parseDouble(String.valueOf(obj[3]));
                        actualAmount = actualAmount + aAmount;
                    }
                    if (obj[4] != null) {
                        Double pSales = Double.parseDouble(String.valueOf(obj[4]));
                        projectedSales = projectedSales + pSales;
                    }
                    if (obj[5] != null) {
                        Double pAmount = Double.parseDouble(String.valueOf(obj[5]));
                        projectedAmount = projectedAmount + pAmount;
                    }
                    if (obj[8] != null) {
                        Double proRate = Double.parseDouble(String.valueOf(obj[8]));
                        projectedRate = projectedRate + proRate;
                    }
                    if (obj[9] != null) {
                        Double aRPU = Double.parseDouble(String.valueOf(obj[9]));
                        actualRPU = actualRPU + aRPU;
                    }
                    if (obj[10] != null) {
                        Double pRPU = Double.parseDouble(String.valueOf(obj[10]));
                        projectedRPU = projectedRPU + pRPU;
                    }
                }
            }
            if (i == list.size() - 1) {
                Double arate = actualAmount / actualSales;
                if (arate.isNaN() || arate.isInfinite()) {
                    arate = 0.0;
                }
                arate = arate * 100;
                discountDto.addStringProperties(commonColumn + ACTUALSRATE, arate != null && !NULL.equals(String.valueOf(arate)) && !StringUtils.EMPTY.equals(String.valueOf(arate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(arate)))).concat(PERCENTAGE) : HYPHEN);
                Double actualAmt = actualAmount;
                if (actualAmt.isNaN() || actualAmt.isInfinite()) {
                    actualAmt = 0.0;
                }
                discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actualAmt != null && !NULL.equals(String.valueOf(actualAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actualAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actualAmt)))) : HYPHEN);
                Double prate = projectedAmount / projectedSales;

                if (prate.isNaN() || prate.isInfinite()) {
                    prate = 0.0;
                }

                discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, prate != null && !NULL.equals(String.valueOf(prate)) && !StringUtils.EMPTY.equals(String.valueOf(prate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(prate)))).concat(PERCENTAGE) : HYPHEN);
                Double projectedAmtAmt = projectedAmount;
                if (projectedAmtAmt.isNaN() || projectedAmtAmt.isInfinite()) {
                    projectedAmtAmt = 0.0;
                }
                discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, projectedAmtAmt != null && !NULL.equals(String.valueOf(projectedAmtAmt)) && !StringUtils.EMPTY.equals(String.valueOf(projectedAmtAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(projectedAmtAmt)))) : HYPHEN);
                Double aRPU = actualAmount / actualRPU;
                if (aRPU.isNaN() || aRPU.isInfinite()) {
                    aRPU = 0.0;
                }
                 aRPU*=(divideRPU==0? 1:divideRPU);
                String actRPU = String.valueOf(aRPU);
                discountDto.addStringProperties(commonColumn + ACTUALRPU, actRPU != null && !NULL.equals(String.valueOf(actRPU)) && !StringUtils.EMPTY.equals(String.valueOf(actRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(actRPU)))) : HYPHEN);
                Double pRPU = projectedAmount / projectedRPU;
                if (pRPU.isNaN() || pRPU.isInfinite()) {
                    pRPU = 0.0;
                }
                pRPU*=(divideRPU==0? 1:divideRPU);
                String proRPU = String.valueOf(pRPU);
                discountDto.addStringProperties(commonColumn + PROJECTEDRPU, proRPU != null && !NULL.equals(String.valueOf(proRPU)) && !StringUtils.EMPTY.equals(String.valueOf(proRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(proRPU)))) : HYPHEN);

                periodList.remove(commonColumn);
                commonColumn = StringUtils.EMPTY;
                actualSales = 0;
                actualAmount = 0;
                projectedSales = 0;
                projectedAmount = 0;
                projectedRate = 0;
                actualRPU = 0;
                projectedRPU = 0;
                commonColumn = StringUtils.EMPTY + obj[0];
                year = (Integer) obj[0];
            }
        }
        discountDto = putHyphenForDTO(periodList, discountDto);
        return discountDto;
    }

    private DiscountProjectionResultsDTO getValueForMonthDTO(ProjectionSelectionDTO projSelDTO, List list, DiscountProjectionResultsDTO discountDto,int divideRPU) {

        double actualSales = 0;
        double actualAmount = 0;
        double projectedSales = 0;
        double projectedAmount = 0;
        double projectedRate = 0;
        double actualRPU = 0;
        double projectedRPU = 0;
        int year = 0;
        int month = 0;
        List<String> periodList = new ArrayList<String>(projSelDTO.getPeriodList());
        String commonColumn = StringUtils.EMPTY;
        for (int i = 0; i < list.size(); i++) {
            final Object[] obj = (Object[]) list.get(i);
            int selectedYear = 0;
            int selectedMonth = 0;
            if (obj[0] != null) {
                selectedYear = (Integer) obj[0];
            }
            if (obj[6] != null) {
                selectedMonth = (Integer) obj[1];
            }
            if (year == selectedYear && month == selectedMonth) {
                if (obj[2] != null) {
                    Double aSales = Double.parseDouble(String.valueOf(obj[2]));
                    actualSales = actualSales + aSales;
                }
                if (obj[3] != null) {
                    Double aAmount = Double.parseDouble(String.valueOf(obj[3]));
                    actualAmount = actualAmount + aAmount;
                }
                if (obj[4] != null) {
                    Double pSales = Double.parseDouble(String.valueOf(obj[4]));
                    projectedSales = projectedSales + pSales;
                }
                if (obj[5] != null) {
                    Double pAmount = Double.parseDouble(String.valueOf(obj[5]));
                    projectedAmount = projectedAmount + pAmount;
                }
                if (obj[8] != null) {
                    Double proRate = Double.parseDouble(String.valueOf(obj[8]));
                    projectedRate = projectedRate + proRate;
                }
                if (obj[9] != null) {
                    Double aRPU = Double.parseDouble(String.valueOf(obj[9]));
                    actualRPU = actualRPU + aRPU;
                }
                if (obj[10] != null) {
                    Double pRPU = Double.parseDouble(String.valueOf(obj[10]));
                    projectedRPU = projectedRPU + pRPU;
                }
            } else {
                if (i == 0) {
                    year = selectedYear;
                    month = selectedMonth;
                    String monthName = getMonthForInt(Integer.valueOf(StringUtils.EMPTY + obj[1]) - 1);
                    monthName = monthName.toLowerCase();
                    commonColumn = monthName + obj[0];
                    if (obj[2] != null) {
                        Double aSales = Double.parseDouble(String.valueOf(obj[2]));
                        actualSales = actualSales + aSales;
                    }
                    if (obj[3] != null) {
                        Double aAmount = Double.parseDouble(String.valueOf(obj[3]));
                        actualAmount = actualAmount + aAmount;
                    }
                    if (obj[4] != null) {
                        Double pSales = Double.parseDouble(String.valueOf(obj[4]));
                        projectedSales = projectedSales + pSales;
                    }
                    if (obj[5] != null) {
                        Double pAmount = Double.parseDouble(String.valueOf(obj[5]));
                        projectedAmount = projectedAmount + pAmount;
                    }
                    if (obj[8] != null) {
                        Double proRate = Double.parseDouble(String.valueOf(obj[8]));
                        projectedRate = projectedRate + proRate;
                    }
                    if (obj[9] != null) {
                        Double aRPU = Double.parseDouble(String.valueOf(obj[9]));
                        actualRPU = actualRPU + aRPU;
                    }
                    if (obj[10] != null) {
                        Double pRPU = Double.parseDouble(String.valueOf(obj[10]));
                        projectedRPU = projectedRPU + pRPU;
                    }
                } else {
                    Double arate = actualAmount / actualSales;
                    if (arate.isNaN() || arate.isInfinite()) {
                        arate = 0.0;
                    }
                    arate = arate * 100;
                    discountDto.addStringProperties(commonColumn + ACTUALSRATE, arate != null && !NULL.equals(String.valueOf(arate)) && !StringUtils.EMPTY.equals(String.valueOf(arate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(arate)))).concat(PERCENTAGE) : HYPHEN);
                    Double actualAmt = actualAmount;
                    if (actualAmt.isNaN() || actualAmt.isInfinite()) {
                        actualAmt = 0.0;
                    }
                    discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actualAmt != null && !NULL.equals(String.valueOf(actualAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actualAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actualAmt)))) : HYPHEN);
                    Double prate = projectedAmount / projectedSales;
                    
                    if (prate.isNaN() || prate.isInfinite()) {
                        prate = 0.0;
                    }
                    prate = prate * 100;
 
                    discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, prate != null && !NULL.equals(String.valueOf(prate)) && !StringUtils.EMPTY.equals(String.valueOf(prate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(prate)))).concat(PERCENTAGE) : HYPHEN);
                    Double projectedAmtAmt = projectedAmount;
                    if (projectedAmtAmt.isNaN() || projectedAmtAmt.isInfinite()) {
                        projectedAmtAmt = 0.0;
                    }
                    discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, projectedAmtAmt != null && !NULL.equals(String.valueOf(projectedAmtAmt)) && !StringUtils.EMPTY.equals(String.valueOf(projectedAmtAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(projectedAmtAmt)))) : HYPHEN);
                    Double aRPU = actualAmount / actualRPU;
                    if (aRPU.isNaN() || aRPU.isInfinite()) {
                        aRPU = 0.0;
                    }
                     aRPU*=(divideRPU==0? 1:divideRPU);
                    String actRPU = String.valueOf(aRPU);
                    discountDto.addStringProperties(commonColumn + ACTUALRPU, actRPU != null && !NULL.equals(String.valueOf(actRPU)) && !StringUtils.EMPTY.equals(String.valueOf(actRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(actRPU)))) : HYPHEN);
                    Double pRPU = projectedAmount / projectedRPU;
                    pRPU*=(divideRPU==0? 1:divideRPU);
                    if (pRPU.isNaN() || pRPU.isInfinite()) {
                        pRPU = 0.0;
                    }
                    
                    String proRPU = String.valueOf(pRPU);
                    discountDto.addStringProperties(commonColumn + PROJECTEDRPU, proRPU != null && !NULL.equals(String.valueOf(proRPU)) && !StringUtils.EMPTY.equals(String.valueOf(proRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(proRPU)))) : HYPHEN);

                    periodList.remove(commonColumn);
                    commonColumn = StringUtils.EMPTY;
                    actualSales = 0;
                    actualAmount = 0;
                    projectedSales = 0;
                    projectedAmount = 0;
                    projectedRate = 0;
                    actualRPU = 0;
                    projectedRPU = 0;
                    String monthName = getMonthForInt(Integer.valueOf(StringUtils.EMPTY + obj[1]) - 1);
                    monthName = monthName.toLowerCase();
                    commonColumn = monthName + obj[0];
                    year = (Integer) obj[0];
                    month = (Integer) obj[1];
                    if (obj[2] != null) {
                        Double aSales = Double.parseDouble(String.valueOf(obj[2]));
                        actualSales = actualSales + aSales;
                    }
                    if (obj[3] != null) {
                        Double aAmount = Double.parseDouble(String.valueOf(obj[3]));
                        actualAmount = actualAmount + aAmount;
                    }
                    if (obj[4] != null) {
                        Double pSales = Double.parseDouble(String.valueOf(obj[4]));
                        projectedSales = projectedSales + pSales;
                    }
                    if (obj[5] != null) {
                        Double pAmount = Double.parseDouble(String.valueOf(obj[5]));
                        projectedAmount = projectedAmount + pAmount;
                    }
                    if (obj[8] != null) {
                        Double proRate = Double.parseDouble(String.valueOf(obj[8]));
                        projectedRate = projectedRate + proRate;
                    }
                    if (obj[9] != null) {
                        Double acRPU = Double.parseDouble(String.valueOf(obj[9]));
                        actualRPU = actualRPU + acRPU;
                    }
                    if (obj[10] != null) {
                        Double prRPU = Double.parseDouble(String.valueOf(obj[10]));
                        projectedRPU = projectedRPU + prRPU;
                    }
                }
            }
            if (i == list.size() - 1) {
                Double arate = actualAmount / actualSales;
                if (arate.isNaN() || arate.isInfinite()) {
                    arate = 0.0;
                }
                arate = arate * 100;
                discountDto.addStringProperties(commonColumn + ACTUALSRATE, arate != null && !NULL.equals(String.valueOf(arate)) && !StringUtils.EMPTY.equals(String.valueOf(arate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(arate)))).concat(PERCENTAGE) : HYPHEN);
                Double actualAmt = actualAmount;
                if (actualAmt.isNaN() || actualAmt.isInfinite()) {
                    actualAmt = 0.0;
                }
                discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actualAmt != null && !NULL.equals(String.valueOf(actualAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actualAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actualAmt)))) : HYPHEN);
                Double prate = projectedAmount / projectedSales;

                if (prate.isNaN() || prate.isInfinite()) {
                    prate = 0.0;
                }
                prate = prate * 100;

                discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, prate != null && !NULL.equals(String.valueOf(prate)) && !StringUtils.EMPTY.equals(String.valueOf(prate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(prate)))).concat(PERCENTAGE) : HYPHEN);
                Double projectedAmtAmt = projectedAmount;
                if (projectedAmtAmt.isNaN() || projectedAmtAmt.isInfinite()) {
                    projectedAmtAmt = 0.0;
                }
                discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, projectedAmtAmt != null && !NULL.equals(String.valueOf(projectedAmtAmt)) && !StringUtils.EMPTY.equals(String.valueOf(projectedAmtAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(projectedAmtAmt)))) : HYPHEN);
                Double aRPU = actualAmount / actualRPU;
                 aRPU*=(divideRPU==0? 1:divideRPU);
                if (aRPU.isNaN() || aRPU.isInfinite()) {
                    aRPU = 0.0;
                }
                String actRPU = String.valueOf(aRPU);
                discountDto.addStringProperties(commonColumn + ACTUALRPU, actRPU != null && !NULL.equals(String.valueOf(actRPU)) && !StringUtils.EMPTY.equals(String.valueOf(actRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(actRPU)))) : HYPHEN);
                Double pRPU = projectedAmount / projectedRPU;
                 pRPU*=(divideRPU==0? 1:divideRPU);
                if (pRPU.isNaN() || pRPU.isInfinite()) {
                    pRPU = 0.0;
                }
                String proRPU = String.valueOf(pRPU);
                discountDto.addStringProperties(commonColumn + PROJECTEDRPU, proRPU != null && !NULL.equals(String.valueOf(proRPU)) && !StringUtils.EMPTY.equals(String.valueOf(proRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(proRPU)))) : HYPHEN);
                periodList.remove(commonColumn);
                commonColumn = StringUtils.EMPTY;
                actualSales = 0;
                actualAmount = 0;
                projectedSales = 0;
                projectedAmount = 0;
                projectedRate = 0;
                actualRPU = 0;
                projectedRPU = 0;
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
        double projectedRate = 0;
        double actualRPU = 0;
        double projectedRPU = 0;
        int frequencyDivision = Q_SMALL.equals(freq) ? 3 : 6;
        DiscountProjectionResultsDTO discountDto = new DiscountProjectionResultsDTO();
        List<String> periodList = new ArrayList<String>(projSelDTO.getPeriodList());
        String commonColumn = StringUtils.EMPTY;
        for (int i = 0; i < list.size(); i++) {
            Object[] obj = (Object[]) list.get(i);
            String currentDiscount = String.valueOf(obj[8]);
            int selectedYear = (Integer) obj[0];
            int selectedQuarter = (Integer) obj[6];
            if (currentDiscount.equalsIgnoreCase(discountName)) {
                if (year == selectedYear && quarter == selectedQuarter) {
                    if (obj[2] != null) {
                        Double aSales = Double.parseDouble(String.valueOf(obj[2]));
                        actualSales = actualSales + aSales;
                    }
                    if (obj[3] != null) {
                        Double aAmount = Double.parseDouble(String.valueOf(obj[3]));
                        actualAmount = actualAmount + aAmount;
                    }
                    if (obj[4] != null) {
                        Double pSales = Double.parseDouble(String.valueOf(obj[4]));
                        projectedSales = projectedSales + pSales;
                    }
                    if (obj[5] != null) {
                        Double pAmount = Double.parseDouble(String.valueOf(obj[5]));
                        projectedAmount = projectedAmount + pAmount;
                    }
                    if (obj[9] != null) {
                        Double projRate = Double.parseDouble(String.valueOf(obj[9]));
                        projectedRate = projectedRate + projRate;
                    }
                    if (obj[10] != null) {
                        Double acRPU = Double.parseDouble(String.valueOf(obj[10]));
                        actualRPU = actualRPU + acRPU;
                    }
                    if (obj[11] != null) {
                        Double prRPU = Double.parseDouble(String.valueOf(obj[11]));
                        projectedRPU = projectedRPU + prRPU;
                    }
                } else {
                    Double arate = actualAmount / actualSales;
                    if (arate.isNaN() || arate.isInfinite()) {
                        arate = 0.0;
                    }
                    arate = arate * 100;
                    String actRate = String.valueOf(arate);
                    discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                    Double actualAmt = actualAmount;
                    if (actualAmt.isNaN() || actualAmt.isInfinite()) {
                        actualAmt = 0.0;
                    }
                    String actAmt = String.valueOf(actualAmount);
                    discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                    Double prate = projectedAmount / projectedSales;
                    prate = prate * 100;
                    if (prate.isNaN() || prate.isInfinite()) {
                        prate = 0.0;
                    }

                    String proRate = String.valueOf(prate);
                    discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                    Double projectedAmtAmt = projectedAmount;
                    if (projectedAmtAmt.isNaN() || projectedAmtAmt.isInfinite()) {
                        projectedAmtAmt = 0.0;
                    }
                    String proAmount = String.valueOf(projectedAmtAmt);
                    discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                    Double aRPU = actualAmount / actualRPU;
                    if (aRPU.isNaN() || aRPU.isInfinite() ) {
                        aRPU = 0.0;
                    }
                    String actRPU = String.valueOf(aRPU);
                    discountDto.addStringProperties(commonColumn + ACTUALRPU, actRPU != null && !NULL.equals(String.valueOf(actRPU)) && !StringUtils.EMPTY.equals(String.valueOf(actRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(actRPU)))) : HYPHEN);
                    Double pRPU = projectedAmount / projectedRPU;
                    if (pRPU.isNaN()|| arate.isInfinite() || pRPU.isInfinite()) {
                        pRPU = 0.0;
                    }
                    String proRPU = String.valueOf(pRPU);
                    discountDto.addStringProperties(commonColumn + PROJECTEDRPU, proRPU != null && !NULL.equals(String.valueOf(proRPU)) && !StringUtils.EMPTY.equals(String.valueOf(proRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(proRPU)))) : HYPHEN);
                    periodList.remove(commonColumn);
                    commonColumn = StringUtils.EMPTY;
                    actualSales = 0;
                    actualAmount = 0;
                    projectedSales = 0;
                    projectedAmount = 0;
                    projectedRate = 0;
                    actualRPU = 0;
                    projectedRPU = 0;
                    commonColumn = freq + obj[6] + obj[0];
                    if (obj[2] != null) {
                        Double aSales = Double.parseDouble(String.valueOf(obj[2]));
                        actualSales = actualSales + aSales;
                    }
                    if (obj[3] != null) {
                        Double aAmount = Double.parseDouble(String.valueOf(obj[3]));
                        actualAmount = actualAmount + aAmount;
                    }
                    if (obj[4] != null) {
                        Double pSales = Double.parseDouble(String.valueOf(obj[4]));
                        projectedSales = projectedSales + pSales;
                    }
                    if (obj[5] != null) {
                        Double pAmount = Double.parseDouble(String.valueOf(obj[5]));
                        projectedAmount = projectedAmount + pAmount;
                    }
                    if (obj[9] != null) {
                        Double projRate = Double.parseDouble(String.valueOf(obj[9]));
                        projectedRate = projectedRate + projRate;
                    }
                    if (obj[10] != null) {
                        Double acRPU = Double.parseDouble(String.valueOf(obj[10]));
                        actualRPU = actualRPU + acRPU;
                    }
                    if (obj[11] != null) {
                        Double prRPU = Double.parseDouble(String.valueOf(obj[11]));
                        projectedRPU = projectedRPU + prRPU;
                    }
                    year = selectedYear;
                    quarter = selectedQuarter;
                }
            } else {
                if (i == 0) {
                    discountDto = new DiscountProjectionResultsDTO();
                    discountDto.setGroup(String.valueOf(obj[8]));
                    discountList.remove(String.valueOf(obj[8]));
                    discountDto.setIsParent(ZERO);
                    year = selectedYear;
                    quarter = selectedQuarter;
                    discountName = currentDiscount;
                    commonColumn = freq + obj[6] + obj[0];
                    if (obj[2] != null) {
                        Double aSales = Double.parseDouble(String.valueOf(obj[2]));
                        actualSales = actualSales + aSales;
                    }
                    if (obj[3] != null) {
                        Double aAmount = Double.parseDouble(String.valueOf(obj[3]));
                        actualAmount = actualAmount + aAmount;
                    }
                    if (obj[4] != null) {
                        Double pSales = Double.parseDouble(String.valueOf(obj[4]));
                        projectedSales = projectedSales + pSales;
                    }
                    if (obj[5] != null) {
                        Double pAmount = Double.parseDouble(String.valueOf(obj[5]));
                        projectedAmount = projectedAmount + pAmount;
                    }
                    if (obj[9] != null) {
                        Double projRate = Double.parseDouble(String.valueOf(obj[9]));
                        projectedRate = projectedRate + projRate;
                    }
                    if (obj[10] != null) {
                        Double acRPU = Double.parseDouble(String.valueOf(obj[10]));
                        actualRPU = actualRPU + acRPU;
                    }
                    if (obj[11] != null) {
                        Double prRPU = Double.parseDouble(String.valueOf(obj[11]));
                        projectedRPU = projectedRPU + prRPU;
                    }

                } else {
                    Double arate = actualAmount / actualSales;
                    if (arate.isNaN()|| arate.isInfinite()) {
                        arate = 0.0;
                    }
                    arate = arate * 100;
                    String actRate = String.valueOf(arate);
                    discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                    Double actualAmt = actualAmount;
                    if (actualAmt.isNaN() || actualAmt.isInfinite() ) {
                        actualAmt = 0.0;
                    }
                    String actAmt = String.valueOf(actualAmt);
                    discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                    Double prate = projectedAmount / projectedSales;
                    prate = prate * 100;
                    if (prate.isNaN() || prate.isInfinite()) {
                        prate = 0.0;
                    }

                    String proRate = String.valueOf(prate);
                    discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                    Double projectedAmtAmt = projectedAmount;
                    if (projectedAmtAmt.isNaN()|| arate.isInfinite() || projectedAmtAmt.isInfinite()) {
                        projectedAmtAmt = 0.0;
                    }
                    String proAmount = String.valueOf(projectedAmtAmt);
                    discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                    Double aRPU = actualAmount / actualRPU;
                    if (aRPU.isNaN()|| arate.isInfinite() || aRPU.isInfinite()) {
                        aRPU = 0.0;
                    }
                    String actRPU = String.valueOf(aRPU);
                    discountDto.addStringProperties(commonColumn + ACTUALRPU, actRPU != null && !NULL.equals(String.valueOf(actRPU)) && !StringUtils.EMPTY.equals(String.valueOf(actRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(actRPU)))) : HYPHEN);
                    Double pRPU = projectedAmount / projectedRPU;
                    if (pRPU.isNaN()|| arate.isInfinite() || pRPU.isInfinite()) {
                        pRPU = 0.0;
                    }
                    String proRPU = String.valueOf(pRPU);
                    discountDto.addStringProperties(commonColumn + PROJECTEDRPU, proRPU != null && !NULL.equals(String.valueOf(proRPU)) && !StringUtils.EMPTY.equals(String.valueOf(proRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(proRPU)))) : HYPHEN);

                    periodList.remove(commonColumn);
                    discountDto = putHyphenForDTO(periodList, discountDto);
                    discountProjList.add(discountDto);
                    actualSales = 0;
                    actualAmount = 0;
                    projectedSales = 0;
                    projectedAmount = 0;
                    projectedRate = 0;
                    actualRPU = 0;
                    projectedRPU = 0;
                    discountDto = new DiscountProjectionResultsDTO();
                    discountName = currentDiscount;
                    discountDto.setGroup(discountName);
                    discountList.remove(discountName);
                    year = selectedYear;
                    quarter = selectedQuarter;
                    commonColumn = freq + obj[6] + obj[0];
                    discountDto.setIsParent(ZERO);
                    if (obj[2] != null) {
                        Double aSales = Double.parseDouble(String.valueOf(obj[2]));
                        actualSales = actualSales + aSales;
                    }
                    if (obj[3] != null) {
                        Double aAmount = Double.parseDouble(String.valueOf(obj[3]));
                        actualAmount = actualAmount + aAmount;
                    }
                    if (obj[4] != null) {
                        Double pSales = Double.parseDouble(String.valueOf(obj[4]));
                        projectedSales = projectedSales + pSales;
                    }
                    if (obj[5] != null) {
                        Double pAmount = Double.parseDouble(String.valueOf(obj[5]));
                        projectedAmount = projectedAmount + pAmount;
                    }
                    if (obj[9] != null) {
                        Double projRate = Double.parseDouble(String.valueOf(obj[9]));
                        projectedRate = projectedRate + projRate;
                    }
                    if (obj[10] != null) {
                        Double acRPU = Double.parseDouble(String.valueOf(obj[10]));
                        actualRPU = actualRPU + acRPU;
                    }
                    if (obj[11] != null) {
                        Double prRPU = Double.parseDouble(String.valueOf(obj[11]));
                        projectedRPU = projectedRPU + prRPU;
                    }
                }
            }
            if (i == list.size() - 1) {
                Double arate = actualAmount / actualSales;
                if (arate.isNaN() || arate.isInfinite()) {
                    arate = 0.0;
                }
                arate = arate * 100;
                String actRate = String.valueOf(arate);
                discountDto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                Double actualAmt = actualAmount;
                if (actualAmt.isNaN()|| arate.isInfinite() || actualAmt.isInfinite()) {
                    actualAmt = 0.0;
                }
                String actAmt = String.valueOf(actualAmt);
                discountDto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                Double prate = projectedAmount / projectedSales;
                if (prate.isNaN() || prate.isInfinite()) {
                    prate = 0.0;
                }
                prate = prate * 100;

                String proRate = String.valueOf(prate);
                discountDto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                Double projectedAmtAmt = projectedAmount;
                if (projectedAmtAmt.isNaN() || projectedAmtAmt.isInfinite() ) {
                    projectedAmtAmt = 0.0;
                }
                String proAmount = String.valueOf(projectedAmtAmt);
                discountDto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                Double aRPU = actualAmount / actualRPU;
                if (aRPU.isNaN()|| arate.isInfinite() || aRPU.isInfinite() ) {
                    aRPU = 0.0;
                }
                String actRPU = String.valueOf(aRPU);
                discountDto.addStringProperties(commonColumn + ACTUALRPU, actRPU != null && !NULL.equals(String.valueOf(actRPU)) && !StringUtils.EMPTY.equals(String.valueOf(actRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(actRPU)))) : HYPHEN);
                Double pRPU = projectedAmount / projectedRPU;
                if (pRPU.isNaN()|| arate.isInfinite() || pRPU.isInfinite() ) {
                    pRPU = 0.0;
                }
                String proRPU = String.valueOf(pRPU);
                discountDto.addStringProperties(commonColumn + PROJECTEDRPU, proRPU != null && !NULL.equals(String.valueOf(proRPU)) && !StringUtils.EMPTY.equals(String.valueOf(proRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(proRPU)))) : HYPHEN);
                periodList.remove(commonColumn);
                discountDto = putHyphenForDTO(periodList, discountDto);
                discountProjList.add(discountDto);

//                if (!discountList.isEmpty()) {
//                    for (int k = 0; k < discountList.size(); k++) {
//                        String group = discountList.get(k);
//                        discountDto = new DiscountProjectionResultsDTO();
//                        periodList = new ArrayList<String>(projSelDTO.getPeriodList());
//                        discountDto.setGroup(group);
//                        discountDto = putHyphenForDTO(periodList, discountDto);
//                        discountProjList.add(discountDto);
//                    }
//
//                }
            }
        }
        return discountProjList;
    }

    private List<DiscountProjectionResultsDTO> getDiscountListYearDto(ProjectionSelectionDTO projSelDTO, List list, List<DiscountProjectionResultsDTO> discountProjList, List<String> discountList) {
        double actualSales = 0;
        double actualAmount = 0;
        double projectedSales = 0;
        double projectedAmount = 0;
        double projectedRate = 0;
        double actualRPU = 0;
        double projectedRPU = 0;
        String commonColumn = StringUtils.EMPTY;
        List<String> periodList = new ArrayList<String>(projSelDTO.getPeriodList());
        DiscountProjectionResultsDTO dto = new DiscountProjectionResultsDTO();
        Object[] object = (Object[]) list.get(0);
        String currentDiscount = String.valueOf(object[8]);
        int currentYear = (Integer) object[0];
        dto.setIsParent(ZERO);
        dto.setGroup(currentDiscount);
        discountList.remove(currentDiscount);
        commonColumn = StringUtils.EMPTY + object[0];
        if (object[2] != null) {
            Double aSales = Double.parseDouble(String.valueOf(object[2]));
            actualSales = actualSales + aSales;
        }
        if (object[3] != null) {
            Double aAmount = Double.parseDouble(String.valueOf(object[3]));
            actualAmount = actualAmount + aAmount;
        }
        if (object[4] != null) {
            Double pSales = Double.parseDouble(String.valueOf(object[4]));
            projectedSales = projectedSales + pSales;
        }
        if (object[5] != null) {
            Double pAmount = Double.parseDouble(String.valueOf(object[5]));
            projectedAmount = projectedAmount + pAmount;
        }
        if (object[9] != null) {
            Double projRate = Double.parseDouble(String.valueOf(object[9]));
            projectedRate = projectedRate + projRate;
        }
        if (object[10] != null) {
            Double acRPU = Double.parseDouble(String.valueOf(object[10]));
            actualRPU = actualRPU + acRPU;
        }
        if (object[11] != null) {
            Double prRPU = Double.parseDouble(String.valueOf(object[11]));
            projectedRPU = projectedRPU + prRPU;
        }
        if (list.size() == 1) {
            Double arate = actualAmount / actualSales;
            if (arate.isNaN() || arate.isInfinite()) {
                arate = 0.0;
            }
            arate = arate * 100;
            String actRate = String.valueOf(arate);
            dto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
            Double actualAmt = actualAmount;
            if (actualAmt.isNaN() || actualAmt.isInfinite()) {
                actualAmt = 0.0;
            }
            String actAmt = String.valueOf(actualAmt);
            dto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
            Double prate = projectedAmount / projectedSales;

            if (prate.isNaN() || prate.isInfinite()) {
                prate = 0.0;
            }
            prate = prate * 100;

            String proRate = String.valueOf(prate);
            dto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
            Double projectedAmtAmt = projectedAmount;
            if (projectedAmtAmt.isNaN() || projectedAmtAmt.isInfinite()) {
                projectedAmtAmt = 0.0;
            }
            String proAmount = String.valueOf(projectedAmtAmt);
            dto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
            Double aRPU = actualAmount / actualRPU;
            if (aRPU.isNaN() || aRPU.isInfinite()) {
                aRPU = 0.0;
            }
            String actRPU = String.valueOf(aRPU);
            dto.addStringProperties(commonColumn + ACTUALRPU, actRPU != null && !NULL.equals(String.valueOf(actRPU)) && !StringUtils.EMPTY.equals(String.valueOf(actRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(actRPU)))) : HYPHEN);
            Double pRPU = projectedAmount / projectedRPU;
            if (pRPU.isNaN() || pRPU.isInfinite()) {
                pRPU = 0.0;
            }
            String proRPU = String.valueOf(pRPU);
            dto.addStringProperties(commonColumn + PROJECTEDRPU, proRPU != null && !NULL.equals(String.valueOf(proRPU)) && !StringUtils.EMPTY.equals(String.valueOf(proRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(proRPU)))) : HYPHEN);
            periodList.remove(commonColumn);
            commonColumn = StringUtils.EMPTY;
        } else {
            for (int i = 1; i < list.size(); i++) {
                Object[] obj = (Object[]) list.get(i);
                String selectedDiscount = String.valueOf(obj[8]);
                int selectedYear = (Integer) obj[0];
                if (currentDiscount.equalsIgnoreCase(selectedDiscount)) {
                    if (currentYear == selectedYear) {
                        if (obj[2] != null) {
                            Double aSales = Double.parseDouble(String.valueOf(obj[2]));
                            actualSales = actualSales + aSales;
                        }
                        if (obj[3] != null) {
                            Double aAmount = Double.parseDouble(String.valueOf(obj[3]));
                            actualAmount = actualAmount + aAmount;
                        }
                        if (obj[4] != null) {
                            Double pSales = Double.parseDouble(String.valueOf(obj[4]));
                            projectedSales = projectedSales + pSales;
                        }
                        if (obj[5] != null) {
                            Double pAmount = Double.parseDouble(String.valueOf(obj[5]));
                            projectedAmount = projectedAmount + pAmount;
                        }
                        if (obj[9] != null) {
                            Double projRate = Double.parseDouble(String.valueOf(obj[9]));
                            projectedRate = projectedRate + projRate;
                        }
                        if (obj[10] != null) {
                            Double acRPU = Double.parseDouble(String.valueOf(obj[10]));
                            actualRPU = actualRPU + acRPU;
                        }
                        if (obj[11] != null) {
                            Double prRPU = Double.parseDouble(String.valueOf(obj[11]));
                            projectedRPU = projectedRPU + prRPU;
                        }
                    } else {
                        Double arate = actualAmount / actualSales;
                        if (arate.isNaN() || arate.isInfinite()) {
                            arate = 0.0;
                        }
                        arate = arate * 100;
                        String actRate = String.valueOf(arate);
                        dto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                        Double actualAmt = actualAmount;
                        if (actualAmt.isNaN() || actualAmt.isInfinite()) {
                            actualAmt = 0.0;
                        }

                        String actAmt = String.valueOf(actualAmt);
                        dto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                        Double prate = projectedAmount / projectedSales;
                        if (prate.isNaN() || prate.isInfinite()) {
                            prate = 0.0;
                        }
                        prate = prate * 100;

                        String proRate = String.valueOf(prate);
                        dto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                        Double projectedAmtAmt = projectedAmount;

                        if (projectedAmtAmt.isNaN()) {
                            projectedAmtAmt = 0.0;
                        }
                        String proAmount = String.valueOf(projectedAmtAmt);
                        dto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                        Double aRPU = actualAmount / actualRPU;
                        if (aRPU.isNaN() || aRPU.isInfinite()) {
                            aRPU = 0.0;
                        }
                        String actRPU = String.valueOf(aRPU);
                        dto.addStringProperties(commonColumn + ACTUALRPU, actRPU != null && !NULL.equals(String.valueOf(actRPU)) && !StringUtils.EMPTY.equals(String.valueOf(actRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(actRPU)))) : HYPHEN);
                        Double pRPU = projectedAmount / projectedRPU;
                        if (pRPU.isNaN() || pRPU.isInfinite()) {
                            pRPU = 0.0;
                        }
                        String proRPU = String.valueOf(pRPU);
                        dto.addStringProperties(commonColumn + PROJECTEDRPU, proRPU != null && !NULL.equals(String.valueOf(proRPU)) && !StringUtils.EMPTY.equals(String.valueOf(proRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(proRPU)))) : HYPHEN);
                        periodList.remove(commonColumn);
                        commonColumn = StringUtils.EMPTY;
                        actualSales = 0;
                        actualAmount = 0;
                        projectedSales = 0;
                        projectedAmount = 0;
                        projectedRate = 0;
                        actualRPU = 0;
                        projectedRPU = 0;
                        commonColumn = StringUtils.EMPTY + obj[0];
                        currentYear = selectedYear;
                        dto.setIsParent(ZERO);
                        if (obj[2] != null) {
                            Double aSales = Double.parseDouble(String.valueOf(obj[2]));
                            actualSales = actualSales + aSales;
                        }
                        if (obj[3] != null) {
                            Double aAmount = Double.parseDouble(String.valueOf(obj[3]));
                            actualAmount = actualAmount + aAmount;
                        }
                        if (obj[4] != null) {
                            Double pSales = Double.parseDouble(String.valueOf(obj[4]));
                            projectedSales = projectedSales + pSales;
                        }
                        if (obj[5] != null) {
                            Double pAmount = Double.parseDouble(String.valueOf(obj[5]));
                            projectedAmount = projectedAmount + pAmount;
                        }
                        if (obj[9] != null) {
                            Double projRate = Double.parseDouble(String.valueOf(obj[9]));
                            projectedRate = projectedRate + projRate;
                        }
                        if (obj[10] != null) {
                            Double acRPU = Double.parseDouble(String.valueOf(obj[10]));
                            actualRPU = actualRPU + acRPU;
                        }
                        if (obj[11] != null) {
                            Double prRPU = Double.parseDouble(String.valueOf(obj[11]));
                            projectedRPU = projectedRPU + prRPU;
                        }
                    }
                } else {
                    Double arate = actualAmount / actualSales;
                    if (arate.isNaN() || arate.isInfinite()) {
                        arate = 0.0;
                    }
                    arate = arate * 100;
                    String actRate = String.valueOf(arate);
                    dto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                    Double actualAmt = actualAmount;
                    if (actualAmt.isNaN() || actualAmt.isInfinite()) {
                        actualAmt = 0.0;
                    }
                    String actAmt = String.valueOf(actualAmt);
                    dto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                    Double prate = projectedAmount / projectedSales;
                    if (prate.isNaN() || prate.isInfinite()) {
                        prate = 0.0;
                    }
                    prate = prate * 100;

                    String proRate = String.valueOf(prate);
                    dto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                    Double projectedAmtAmt = projectedAmount;
                    if (projectedAmtAmt.isNaN() || projectedAmtAmt.isInfinite()) {
                        projectedAmtAmt = 0.0;
                    }
                    String proAmount = String.valueOf(projectedAmtAmt);
                    dto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                    Double aRPU = actualAmount / actualRPU;
                    if (aRPU.isNaN() || aRPU.isInfinite()) {
                        aRPU = 0.0;
                    }
                    String actRPU = String.valueOf(aRPU);
                    dto.addStringProperties(commonColumn + ACTUALRPU, actRPU != null && !NULL.equals(String.valueOf(actRPU)) && !StringUtils.EMPTY.equals(String.valueOf(actRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(actRPU)))) : HYPHEN);
                    Double pRPU = projectedAmount / projectedRPU;
                    if (pRPU.isNaN() || pRPU.isInfinite()) {
                        pRPU = 0.0;
                    }
                    String proRPU = String.valueOf(pRPU);
                    dto.addStringProperties(commonColumn + PROJECTEDRPU, proRPU != null && !NULL.equals(String.valueOf(proRPU)) && !StringUtils.EMPTY.equals(String.valueOf(proRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(proRPU)))) : HYPHEN);
                    periodList.remove(commonColumn);
                    dto = putHyphenForDTO(periodList, dto);
                    discountProjList.add(dto);
                    actualSales = 0;
                    actualAmount = 0;
                    projectedSales = 0;
                    projectedAmount = 0;
                    projectedRate = 0;
                    actualRPU = 0;
                    projectedRPU = 0;
                    dto = new DiscountProjectionResultsDTO();
                    periodList = new ArrayList<String>(projSelDTO.getPeriodList());
                    currentDiscount = selectedDiscount;
                    dto.setGroup(currentDiscount);
                    discountList.remove(currentDiscount);
                    commonColumn = StringUtils.EMPTY + obj[0];
                    currentYear = selectedYear;
                    if (obj[2] != null) {
                        Double aSales = Double.parseDouble(String.valueOf(obj[2]));
                        actualSales = actualSales + aSales;
                    }
                    if (obj[3] != null) {
                        Double aAmount = Double.parseDouble(String.valueOf(obj[3]));
                        actualAmount = actualAmount + aAmount;
                    }
                    if (obj[4] != null) {
                        Double pSales = Double.parseDouble(String.valueOf(obj[4]));
                        projectedSales = projectedSales + pSales;
                    }
                    if (obj[5] != null) {
                        Double pAmount = Double.parseDouble(String.valueOf(obj[5]));
                        projectedAmount = projectedAmount + pAmount;
                    }
                    if (obj[9] != null) {
                        Double projRate = Double.parseDouble(String.valueOf(obj[9]));
                        projectedRate = projectedRate + projRate;
                    }
                    if (obj[10] != null) {
                        Double acRPU = Double.parseDouble(String.valueOf(obj[10]));
                        actualRPU = actualRPU + acRPU;
                    }
                    if (obj[11] != null) {
                        Double prRPU = Double.parseDouble(String.valueOf(obj[11]));
                        projectedRPU = projectedRPU + prRPU;
                    }
                }
                if (i == list.size() - 1) {
                    Double arate = actualAmount / actualSales;
                    if (arate.isNaN() || arate.isInfinite()) {
                        arate = 0.0;
                    }
                    arate = arate * 100;
                    String actRate = String.valueOf(arate);
                    dto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                    Double actualAmt = actualAmount;
//                    actualAmt = actualAmt / 100;
                    if (actualAmt.isNaN() || actualAmt.isInfinite()) {
                        actualAmt = 0.0;
                    }
                    String actAmt = String.valueOf(actualAmt);
                    dto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);
                    Double prate = projectedAmount / projectedSales;
                    if (prate.isNaN() || prate.isInfinite()) {
                        prate = 0.0;
                    }
                    prate = prate * 100;

                    String proRate = String.valueOf(prate);
                    dto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                    Double projectedAmtAmt = projectedAmount;
                    if (projectedAmtAmt.isNaN() || projectedAmtAmt.isInfinite()) {
                        projectedAmtAmt = 0.0;
                    }
                    String proAmount = String.valueOf(projectedAmtAmt);
                    dto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                    Double aRPU = actualAmount / actualRPU;
                    if (aRPU.isNaN() || aRPU.isInfinite()) {
                        aRPU = 0.0;
                    }
                    String actRPU = String.valueOf(aRPU);
                    dto.addStringProperties(commonColumn + ACTUALRPU, actRPU != null && !NULL.equals(String.valueOf(actRPU)) && !StringUtils.EMPTY.equals(String.valueOf(actRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(actRPU)))) : HYPHEN);
                    Double pRPU = projectedAmount / projectedRPU;
                    if (pRPU.isNaN() || pRPU.isInfinite()) {
                        pRPU = 0.0;
                    }
                    String proRPU = String.valueOf(pRPU);
                    dto.addStringProperties(commonColumn + PROJECTEDRPU, proRPU != null && !NULL.equals(String.valueOf(proRPU)) && !StringUtils.EMPTY.equals(String.valueOf(proRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(proRPU)))) : HYPHEN);
                    periodList.remove(commonColumn);
                    dto = putHyphenForDTO(periodList, dto);
                    discountProjList.add(dto);
                }
            }
//            if (!discountList.isEmpty()) {
//                for (int k = 0; k < discountList.size(); k++) {
//                    String group = discountList.get(k);
//                    dto = new DiscountProjectionResultsDTO();
//                    periodList = new ArrayList<String>(projSelDTO.getPeriodList());
//                    dto.setGroup(group);
//                    dto = putHyphenForDTO(periodList, dto);
//                    discountProjList.add(dto);
//                }
//
//            }

        }
        return discountProjList;
    }

    private List<DiscountProjectionResultsDTO> getDiscountListMonthDto(ProjectionSelectionDTO projSelDTO, List list, List<DiscountProjectionResultsDTO> discountProjList, List<String> discountList) {
        double actualSales = 0;
        double actualAmount = 0;
        double projectedSales = 0;
        double projectedAmount = 0;
        double projectionRate = 0;
        double actualRPU = 0;
        double projectedRPU = 0;
        String commonColumn = StringUtils.EMPTY;
        List<String> periodList = new ArrayList<String>(projSelDTO.getPeriodList());
        DiscountProjectionResultsDTO dto = new DiscountProjectionResultsDTO();
        Object[] object = (Object[]) list.get(0);
        String currentDiscount = String.valueOf(object[8]);
        int currentYear = (Integer) object[0];
        int currentMonth = (Integer) object[7];
        String monthName = getMonthForInt(Integer.valueOf(StringUtils.EMPTY + object[7]) - 1);
        monthName = monthName.toLowerCase();
        commonColumn = monthName + object[0];
        dto.setIsParent(ZERO);
        dto.setGroup(currentDiscount);
        discountList.remove(currentDiscount);
        if (object[2] != null) {
            Double aSales = Double.parseDouble(String.valueOf(object[2]));
            actualSales = actualSales + aSales;
        }
        if (object[3] != null) {
            Double aAmount = Double.parseDouble(String.valueOf(object[3]));
            actualAmount = actualAmount + aAmount;
        }
        if (object[4] != null) {
            Double pSales = Double.parseDouble(String.valueOf(object[4]));
            projectedSales = projectedSales + pSales;
        }
        if (object[5] != null) {
            Double pAmount = Double.parseDouble(String.valueOf(object[5]));
            projectedAmount = projectedAmount + pAmount;
        }
        if (object[9] != null) {
            Double prate = Double.parseDouble(String.valueOf(object[9]));
            projectionRate = projectionRate + prate;
        }
        if (object[10] != null) {
            Double acRPU = Double.parseDouble(String.valueOf(object[10]));
            actualRPU = actualRPU + acRPU;
        }
        if (object[11] != null) {
            Double prRPU = Double.parseDouble(String.valueOf(object[11]));
            projectedRPU = projectedRPU + prRPU;
        }
        if (list.size() == 1) {
            Double arate = actualAmount / actualSales;
            if (arate.isNaN() || arate.isInfinite()) {
                arate = 0.0;
            }
            arate = arate * 100;
            String actRate = String.valueOf(arate);
            dto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
            Double actualAmt = actualAmount;
//            actualAmt = actualAmt / 100;
            if (actualAmt.isNaN() || actualAmt.isInfinite()) {
                actualAmt = 0.0;
            }
            String actAmt = String.valueOf(actualAmt);
            dto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);

            Double prate = projectedAmount / projectedSales;

            if (prate.isNaN() || prate.isInfinite()) {
                prate = 0.0;
            }
            prate = prate * 100;

            String proRate = String.valueOf(prate);
            dto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
            Double projectedAmtAmt = projectedAmount;
//            projectedAmtAmt = projectedAmtAmt / 100;
            if (projectedAmtAmt.isNaN() || projectedAmtAmt.isInfinite()) {
                projectedAmtAmt = 0.0;
            }
            String proAmount = String.valueOf(projectedAmtAmt);
            dto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
            Double aRPU = actualAmount / actualRPU;
            if (aRPU.isNaN() || aRPU.isInfinite()) {
                aRPU = 0.0;
            }
            String actRPU = String.valueOf(aRPU);
            dto.addStringProperties(commonColumn + ACTUALRPU, actRPU != null && !NULL.equals(String.valueOf(actRPU)) && !StringUtils.EMPTY.equals(String.valueOf(actRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(actRPU)))) : HYPHEN);
            Double pRPU = projectedAmount / projectedRPU;
            if (pRPU.isNaN() || pRPU.isInfinite()) {
                pRPU = 0.0;
            }
            String proRPU = String.valueOf(pRPU);
            dto.addStringProperties(commonColumn + PROJECTEDRPU, proRPU != null && !NULL.equals(String.valueOf(proRPU)) && !StringUtils.EMPTY.equals(String.valueOf(proRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(proRPU)))) : HYPHEN);
            periodList.remove(commonColumn);
            commonColumn = StringUtils.EMPTY;
        } else {
            for (int i = 1; i < list.size(); i++) {
                Object[] obj = (Object[]) list.get(i);
                String selectedDiscount = String.valueOf(obj[8]);
                int selectedYear = (Integer) obj[0];
                int selectedMonth = (Integer) obj[7];
                if (currentDiscount.equalsIgnoreCase(selectedDiscount)) {
                    if (currentYear == selectedYear && currentMonth == selectedMonth) {
                        if (obj[2] != null) {
                            Double aSales = Double.parseDouble(String.valueOf(obj[2]));
                            actualSales = actualSales + aSales;
                        }
                        if (obj[3] != null) {
                            Double aAmount = Double.parseDouble(String.valueOf(obj[3]));
                            actualAmount = actualAmount + aAmount;
                        }
                        if (obj[4] != null) {
                            Double pSales = Double.parseDouble(String.valueOf(obj[4]));
                            projectedSales = projectedSales + pSales;
                        }
                        if (obj[5] != null) {
                            Double pAmount = Double.parseDouble(String.valueOf(obj[5]));
                            projectedAmount = projectedAmount + pAmount;
                        }
                        if (obj[9] != null) {
                            Double prate = Double.parseDouble(String.valueOf(obj[9]));
                            projectionRate = projectionRate + prate;
                        }
                        if (obj[10] != null) {
                            Double acRPU = Double.parseDouble(String.valueOf(obj[10]));
                            actualRPU = actualRPU + acRPU;
                        }
                        if (obj[11] != null) {
                            Double prRPU = Double.parseDouble(String.valueOf(obj[11]));
                            projectedRPU = projectedRPU + prRPU;
                        }
                    } else {
                        Double arate = actualAmount / actualSales;
                        if (arate.isNaN() || arate.isInfinite()) {
                            arate = 0.0;
                        }
                        arate = arate * 100;
                        String actRate = String.valueOf(arate);
                        dto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                        Double actualAmt = actualAmount;
                        if (actualAmt.isNaN() || actualAmt.isInfinite()) {
                            actualAmt = 0.0;
                        }
                        String actAmt = String.valueOf(actualAmt);
                        dto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);

                        Double prate = projectedAmount / projectedSales;

                        if (prate.isNaN() || prate.isInfinite()) {
                            prate = 0.0;
                        }
                        prate = prate * 100;

                        String proRate = String.valueOf(prate);
                        dto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                        Double projectedAmtAmt = projectedAmount;
                        if (projectedAmtAmt.isNaN() || projectedAmtAmt.isInfinite()) {
                            projectedAmtAmt = 0.0;
                        }
                        String proAmount = String.valueOf(projectedAmtAmt);
                        dto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                        Double aRPU = actualAmount / actualRPU;
                        if (aRPU.isNaN() || aRPU.isInfinite()) {
                            aRPU = 0.0;
                        }
                        String actRPU = String.valueOf(aRPU);
                        dto.addStringProperties(commonColumn + ACTUALRPU, actRPU != null && !NULL.equals(String.valueOf(actRPU)) && !StringUtils.EMPTY.equals(String.valueOf(actRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(actRPU)))) : HYPHEN);
                        Double pRPU = projectedAmount / projectedRPU;
                        if (pRPU.isNaN() || pRPU.isInfinite()) {
                            pRPU = 0.0;
                        }
                        String proRPU = String.valueOf(pRPU);
                        dto.addStringProperties(commonColumn + PROJECTEDRPU, proRPU != null && !NULL.equals(String.valueOf(proRPU)) && !StringUtils.EMPTY.equals(String.valueOf(proRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(proRPU)))) : HYPHEN);
                        periodList.remove(commonColumn);
                        commonColumn = StringUtils.EMPTY;
                        actualSales = 0;
                        actualAmount = 0;
                        projectedSales = 0;
                        projectedAmount = 0;
                        projectionRate = 0;
                        actualRPU = 0;
                        projectedRPU = 0;
                        monthName = getMonthForInt(Integer.valueOf(StringUtils.EMPTY + obj[7]) - 1);
                        monthName = monthName.toLowerCase();
                        commonColumn = monthName + obj[0];
                        currentYear = selectedYear;
                        currentMonth = selectedMonth;
                        dto.setIsParent(ZERO);
                        if (obj[2] != null) {
                            Double aSales = Double.parseDouble(String.valueOf(obj[2]));
                            actualSales = actualSales + aSales;
                        }
                        if (obj[3] != null) {
                            Double aAmount = Double.parseDouble(String.valueOf(obj[3]));
                            actualAmount = actualAmount + aAmount;
                        }

                        if (obj[4] != null) {
                            Double pSales = Double.parseDouble(String.valueOf(obj[4]));
                            projectedSales = projectedSales + pSales;
                        }
                        if (obj[5] != null) {
                            Double pAmount = Double.parseDouble(String.valueOf(obj[5]));
                            projectedAmount = projectedAmount + pAmount;
                        }
                        if (obj[9] != null) {
                            Double projRate = Double.parseDouble(String.valueOf(obj[9]));
                            projectionRate = projectionRate + projRate;
                        }
                        if (obj[10] != null) {
                            Double acRPU = Double.parseDouble(String.valueOf(obj[10]));
                            actualRPU = actualRPU + acRPU;
                        }
                        if (obj[11] != null) {
                            Double prRPU = Double.parseDouble(String.valueOf(obj[11]));
                            projectedRPU = projectedRPU + prRPU;
                        }
                    }
                } else {
                    Double arate = actualAmount / actualSales;
                    if (arate.isNaN() || arate.isInfinite()) {
                        arate = 0.0;
                    }
                    arate = arate * 100;
                    String actRate = String.valueOf(arate);
                    dto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                    Double actualAmt = actualAmount;
                    if (actualAmt.isNaN() || actualAmt.isInfinite()) {
                        actualAmt = 0.0;
                    }
                    String actAmt = String.valueOf(actualAmt);
                    dto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);

                    Double prate = projectedAmount / projectedSales;

                    if (prate.isNaN() || prate.isInfinite()) {
                        prate = 0.0;
                    }
                    prate = prate * 100;

                    String proRate = String.valueOf(prate);
                    dto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                    Double projectedAmtAmt = projectedAmount;
                    if (projectedAmtAmt.isNaN() || projectedAmtAmt.isInfinite()) {
                        projectedAmtAmt = 0.0;
                    }
                    String proAmount = String.valueOf(projectedAmtAmt);
                    dto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                    Double aRPU = actualAmount / actualRPU;
                    if (aRPU.isNaN() || aRPU.isInfinite()) {
                        aRPU = 0.0;
                    }
                    String actRPU = String.valueOf(aRPU);
                    dto.addStringProperties(commonColumn + ACTUALRPU, actRPU != null && !NULL.equals(String.valueOf(actRPU)) && !StringUtils.EMPTY.equals(String.valueOf(actRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(actRPU)))) : HYPHEN);
                    Double pRPU = projectedAmount / projectedRPU;
                    if (pRPU.isNaN()) {
                        pRPU = 0.0;
                    }
                    String proRPU = String.valueOf(pRPU);
                    dto.addStringProperties(commonColumn + PROJECTEDRPU, proRPU != null && !NULL.equals(String.valueOf(proRPU)) && !StringUtils.EMPTY.equals(String.valueOf(proRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(proRPU)))) : HYPHEN);
                    periodList.remove(commonColumn);
                    dto = putHyphenForDTO(periodList, dto);
                    discountProjList.add(dto);
                    actualSales = 0;
                    actualAmount = 0;
                    projectedSales = 0;
                    projectedAmount = 0;
                    projectionRate = 0;
                    actualRPU = 0;
                    projectedRPU = 0;
                    dto = new DiscountProjectionResultsDTO();
                    periodList = new ArrayList<String>(projSelDTO.getPeriodList());
                    currentDiscount = selectedDiscount;
                    dto.setGroup(currentDiscount);
                    discountList.remove(currentDiscount);
                    monthName = getMonthForInt(Integer.valueOf(StringUtils.EMPTY + obj[7]) - 1);
                    monthName = monthName.toLowerCase();
                    commonColumn = monthName + obj[0];
                    currentYear = selectedYear;
                    currentMonth = selectedMonth;
                    if (obj[2] != null) {
                        Double aSales = Double.parseDouble(String.valueOf(obj[2]));
                        actualSales = actualSales + aSales;
                    }
                    if (obj[3] != null) {
                        Double aAmount = Double.parseDouble(String.valueOf(obj[3]));
                        actualAmount = actualAmount + aAmount;
                    }
                    if (obj[4] != null) {
                        Double pSales = Double.parseDouble(String.valueOf(obj[4]));
                        projectedSales = projectedSales + pSales;
                    }
                    if (obj[5] != null) {
                        Double pAmount = Double.parseDouble(String.valueOf(obj[5]));
                        projectedAmount = projectedAmount + pAmount;
                    }
                    if (obj[9] != null) {
                        Double projRate = Double.parseDouble(String.valueOf(obj[9]));
                        projectionRate = projectionRate + projRate;
                    }
                    if (obj[10] != null) {
                        Double acRPU = Double.parseDouble(String.valueOf(obj[10]));
                        actualRPU = actualRPU + acRPU;
                    }
                    if (obj[11] != null) {
                        Double prRPU = Double.parseDouble(String.valueOf(obj[11]));
                        projectedRPU = projectedRPU + prRPU;
                    }
                }
                if (i == list.size() - 1) {
                    Double arate = actualAmount / actualSales;
                    if (arate.isNaN() || arate.isInfinite()) {
                        arate = 0.0;
                    }
                    arate = arate * 100;
                    String actRate = String.valueOf(arate);
                    dto.addStringProperties(commonColumn + ACTUALSRATE, actRate != null && !NULL.equals(String.valueOf(actRate)) && !StringUtils.EMPTY.equals(String.valueOf(actRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(actRate)))).concat(PERCENTAGE) : HYPHEN);
                    Double actualAmt = actualAmount;
                    if (actualAmt.isNaN() || actualAmt.isInfinite()) {
                        actualAmt = 0.0;
                    }
                    String actAmt = String.valueOf(actualAmt);
                    dto.addStringProperties(commonColumn + ACTUALSAMOUNT, actAmt != null && !NULL.equals(String.valueOf(actAmt)) && !StringUtils.EMPTY.equals(String.valueOf(actAmt)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(actAmt)))) : HYPHEN);

                    Double prate = projectedAmount / projectedSales;

                    if (prate.isNaN() || prate.isInfinite()) {
                        prate = 0.0;
                    }
                    prate = prate * 100;

                    String proRate = String.valueOf(prate);
                    dto.addStringProperties(commonColumn + PROJECTIONSRATE, proRate != null && !NULL.equals(String.valueOf(proRate)) && !StringUtils.EMPTY.equals(String.valueOf(proRate)) ? (UNITVOLUME.format(Double.parseDouble(String.valueOf(proRate)))).concat(PERCENTAGE) : HYPHEN);
                    Double projectedAmtAmt = projectedAmount;
                    if (projectedAmtAmt.isNaN() || projectedAmtAmt.isInfinite()) {
                        projectedAmtAmt = 0.0;
                    }
                    String proAmount = String.valueOf(projectedAmtAmt);
                    dto.addStringProperties(commonColumn + PROJECTIONSAMOUNT, proAmount != null && !NULL.equals(String.valueOf(proAmount)) && !StringUtils.EMPTY.equals(String.valueOf(proAmount)) ? DOLLAR_SYMBOL.concat(DOLLAR.format(Double.parseDouble(String.valueOf(proAmount)))) : HYPHEN);
                    Double aRPU = actualAmount / actualRPU;
                    if (aRPU.isNaN() || aRPU.isInfinite()) {
                        aRPU = 0.0;
                    }
                    String actRPU = String.valueOf(aRPU);
                    dto.addStringProperties(commonColumn + ACTUALRPU, actRPU != null && !NULL.equals(String.valueOf(actRPU)) && !StringUtils.EMPTY.equals(String.valueOf(actRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(actRPU)))) : HYPHEN);
                    Double pRPU = projectedAmount / projectedRPU;
                    if (pRPU.isNaN() || pRPU.isInfinite()) {
                        pRPU = 0.0;
                    }
                    String proRPU = String.valueOf(pRPU);
                    dto.addStringProperties(commonColumn + PROJECTEDRPU, proRPU != null && !NULL.equals(String.valueOf(proRPU)) && !StringUtils.EMPTY.equals(String.valueOf(proRPU)) ? DOLLAR_SYMBOL.concat(DOLLAR_RPU.format(Double.parseDouble(String.valueOf(proRPU)))) : HYPHEN);
                    periodList.remove(commonColumn);
                    dto = putHyphenForDTO(periodList, dto);
                    commonColumn = StringUtils.EMPTY;
                    discountProjList.add(dto);
                }
            }
//            if (!discountList.isEmpty()) {
//                for (int k = 0; k < discountList.size(); k++) {
//                    String group = discountList.get(k);
//                    dto = new DiscountProjectionResultsDTO();
//                    periodList = new ArrayList<String>(projSelDTO.getPeriodList());
//                    dto.setGroup(group);
//                    dto = putHyphenForDTO(periodList, dto);
//                    discountProjList.add(dto);
//                }
//
//            }

        }
        return discountProjList;
    }

    public static Object executeSelectQuery(String query, Object udc1, Object udc2) throws SystemException, PortalException {

        return commonDao.executeSelectQuery(query, udc1, udc2);

    }

    private DiscountProjectionResultsDTO putHyphenForDiscount(List<String> tmpList, DiscountProjectionResultsDTO discountDto) {

        for (int j = 0; j < tmpList.size(); j++) {
            String column = tmpList.get(j);
            String commonColumn = column.replace(" ", StringUtils.EMPTY);
            String columns = commonColumn + ACTUALSRATE;
            discountDto.addStringProperties(columns, getFormattedValue(CUR_ZERO, NULL));
            String columns1 = commonColumn + ACTUALSAMOUNT;
            discountDto.addStringProperties(columns1, getFormattedValue(CUR_ZERO, NULL));
            String columns2 = commonColumn + PROJECTIONSAMOUNT;
            discountDto.addStringProperties(columns2, getFormattedValue(CUR_ZERO, NULL));
            String columns3 = commonColumn + PROJECTIONSRATE;
            discountDto.addStringProperties(columns3, getFormattedValue(CUR_ZERO, NULL));
        }

        return discountDto;

    }
    boolean isProgramcategory(int projectionMasterId){
        String query = "SELECT FIELD_VALUES FROM NM_PROJECTION_SELECTION where FIELD_NAME like 'level' and SCREEN_NAME like 'Discount Projection' and PROJECTION_MASTER_SID=" + projectionMasterId;
        List list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        if (list != null && list.get(0).toString().equals("Program")) {
            return false;
        } else {
            return true;
        }
    }
   int getDiscountRSCount(String userId,String sessionId,int projId ){
     
       String sql = "SELECT count(rs_model_sid) FROM ST_NM_DISCOUNT_PROJ_MASTER\n"
               + "WHERE USER_ID = " + userId + " \n"
               + "	AND   SESSION_ID = " + sessionId + "\n"
               + "	   AND  PRICE_GROUP_TYPE in (select FIELD_VALUES from dbo.NM_PROJECTION_SELECTION where FIELD_NAME like 'Selected Discounts' and SCREEN_NAME like 'Discount Projection'\n"
               + "and PROJECTION_MASTER_SID=" + projId + ")\n";
       List list = HelperTableLocalServiceUtil.executeSelectQuery(sql);
       return list!=null ? (int) list.get(0) : 0;
      
   }
   
   /**
     * Used to get the count of discount comes under expanded hierarchy in Total
     * Discount
     *
     * @return
     */
    private String getDiscountCountForCurrentHierarchy(ProjectionSelectionDTO projSelDTO) {
        return "SELECT Count(DISTINCT RS_MODEL_sid)\n"
                + "FROM   ST_NM_DISCOUNT_PROJ_MASTER B\n"
                + "       JOIN PROJECTION_DETAILS pd\n"
                + "         ON pd.PROJECTION_DETAILS_SID = b.PROJECTION_DETAILS_SID\n"
                + "       JOIN @CCP CCP\n"
                + "         ON pd.ccp_details_sid = ccp.CCP_DETAILS_SID\n"
                + "WHERE  B.USER_ID = " + projSelDTO.getUserId() + " \n"
                + "       AND B.SESSION_ID = " + projSelDTO.getSessionId() + " \n"
                + "            AND B.RS_MODEL_SID IN ( " + CommonUtils.CollectionToString(projSelDTO.getDiscountNoList(), false) + " )";

    }
}
