/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.discountprojectionresults.logic;

import com.liferay.portal.kernel.exception.PortalException;
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
import com.stpl.app.service.HelperTableLocalServiceUtil;
import static com.stpl.app.utils.Constants.ButtonConstants.ALL;
import static com.stpl.app.utils.Constants.FrequencyConstants.MONTHLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.QUARTERLY;
import static com.stpl.app.utils.Constants.IndicatorConstants.INDICATOR_VIEW_CUSTOMER;
import static com.stpl.app.utils.Constants.IndicatorConstants.INDICATOR_VIEW_PRODUCT;
import static com.stpl.app.utils.Constants.LabelConstants.ACTUALS;
import static com.stpl.app.utils.Constants.LabelConstants.BOTH;
import static com.stpl.app.utils.Constants.LabelConstants.DASH;
import static com.stpl.app.utils.Constants.LabelConstants.PERIOD;
import static com.stpl.app.utils.Constants.LabelConstants.PROJECTIONS;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.QueryUtil;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author pvinoth
 */
public class NMDPRLogic {

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
    private static final String PERCENTAGE = Constant.PERCENT;
    private static final String DOLLAR_SYMBOL = "$";
    private static final String ZERO = "0";
    private static final CommonDAO commonDao = new CommonDAOImpl();
    protected DPRQueryBuilder queryBuilder = new DPRQueryBuilder();
    private static final Logger LOGGER = LoggerFactory.getLogger(NMDPRLogic.class);
    public static final String PROJECTION_CUST_HIERARCHY = "PROJECTION_CUST_HIERARCHY";
    public static final String PROJECTION_PROD_HIERARCHY = "PROJECTION_PROD_HIERARCHY";
    private static final String[] ARRAY_ALL_MONTH = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

    public NMDPRLogic() {
        //NMDPRLogic
    }

    public int getIntegerForMonth(String month) {
        return Arrays.asList(ARRAY_ALL_MONTH).indexOf(month) + 1;
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

    public String getFormattedValue(DecimalFormat format, String value) {
        String valueToFormat;
        if (value.contains(NULL)) {
            valueToFormat = DASH.getConstant();
        } else {
            Double newValue = Double.valueOf(value);
            if (format.toPattern().contains(Constant.PERCENT)) {
                newValue = newValue / NumericConstants.HUNDRED;
            }
            valueToFormat = format.format(newValue);
        }
        return valueToFormat;
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

    public static Object executeSelectQuery(String query)  {

        return commonDao.executeSelectQuery(query);

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
                + "WHERE  B.RS_CONTRACT_SID IN ( " + CommonUtils.collectionToStringMethod(projSelDTO.getDiscountNoList(), false) + " )";

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
        DiscountProjectionResultsDTO dtoTotal = dto;
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
                                dtoTotal = new DiscountProjectionResultsDTO();
                                finalList.add(dtoTotal);
                                dtoTotal.setGroup(newDiscount);
                                dtoTotal.setLevelNo(0);
                                dtoTotal.setHierarchylevelId(ZERO);
                                dtoTotal.setIsParent(Constant.STRING_ONE);
                                dtoTotal.setHierarchyNo(Constant.STRING_ONE);
                                dtoTotal.setParent(0);
                                dtoTotal.setTotal(1);
                            }
                            setActualProjectionValue(dtoTotal, obj, column);
                            oldDiscount = newDiscount;
                        } else {
                            setActualProjectionValue(dtoTotal, obj, column);
                        }
                        periodList.remove(column);
                        removeList.add(column);
                    }

                    if (i == result.size() - 1 && !rsNeeded) {
                        dtoTotal = putHyphenForDTO(periodList, dtoTotal);
                        DiscountProjectionResultsDTO dtoTotalNew = dtoTotal;
                        finalList.add(dtoTotalNew);
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
            freq = monthName.toLowerCase(Locale.ENGLISH) + String.valueOf(obj[0]);
        }
        return freq;
    }
}