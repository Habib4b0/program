package com.stpl.app.gtnforecasting.salesprojection.logic;

import static com.stpl.app.gtnforecasting.logic.CommonLogic.getCustomViewDetails;
import static com.stpl.app.gtnforecasting.logic.NonMandatedLogic.dataSelection;
import static com.stpl.app.gtnforecasting.salesprojection.utils.HeaderUtils.getMonthForInt;
import static com.stpl.app.gtnforecasting.utils.Constant.STRING_EMPTY;
import static com.stpl.app.gtnforecasting.utils.HeaderUtils.getCommonColumnHeader;
import static com.stpl.app.utils.Constants.CommonConstants.ACTION_VIEW;
import static com.stpl.app.utils.Constants.CommonConstants.NULL;
import static com.stpl.app.utils.Constants.FinderImplIndicators.INDICATOR;
import static com.stpl.app.utils.Constants.FinderImplIndicators.OFFSET;
import static com.stpl.app.utils.Constants.FinderImplIndicators.PM_SID;
import static com.stpl.app.utils.Constants.FinderImplIndicators.SESSION_ID;
import static com.stpl.app.utils.Constants.FinderImplIndicators.START;
import static com.stpl.app.utils.Constants.FinderImplIndicators.USER_ID;
import static com.stpl.app.utils.Constants.FrequencyConstants.ANNUAL;
import static com.stpl.app.utils.Constants.FrequencyConstants.ANNUALLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.MONTHLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.QUARTERLY;
import static com.stpl.app.utils.Constants.FrequencyConstants.SEMI_ANNUAL;
import static com.stpl.app.utils.Constants.IndicatorConstants.H_INDICATOR;
import static com.stpl.app.utils.Constants.IndicatorConstants.INDICATOR_LOGIC_CUSTOMER_HIERARCHY;
import static com.stpl.app.utils.Constants.IndicatorConstants.INDICATOR_LOGIC_CUSTOM_HIERARCHY;
import static com.stpl.app.utils.Constants.IndicatorConstants.INDICATOR_LOGIC_PRODUCT_HIERARCHY;
import static com.stpl.app.utils.Constants.IndicatorConstants.INPUT_MAP;
import static com.stpl.app.utils.Constants.IndicatorConstants.JOIN_MAP;
import static com.stpl.app.utils.Constants.IndicatorConstants.LEVEL_NO;
import static com.stpl.app.utils.Constants.IndicatorConstants.LEVEL_NO_C;
import static com.stpl.app.utils.Constants.IndicatorConstants.LEVEL_NO_P;
import static com.stpl.app.utils.Constants.LabelConstants.ACTUALS;
import static com.stpl.app.utils.Constants.LabelConstants.ASCENDING;
import static com.stpl.app.utils.Constants.LabelConstants.DASH;
import static com.stpl.app.utils.Constants.LabelConstants.LEVEL_BRAND;
import static com.stpl.app.utils.Constants.LabelConstants.LEVEL_NDC;
import static com.stpl.app.utils.Constants.LabelConstants.LEVEL_NDC_10;
import static com.stpl.app.utils.Constants.LabelConstants.LEVEL_NDC_11;
import static com.stpl.app.utils.Constants.LabelConstants.LEVEL_NDC_8;
import static com.stpl.app.utils.Constants.LabelConstants.MASS_FIELD_CS;
import static com.stpl.app.utils.Constants.LabelConstants.MASS_FIELD_POB;
import static com.stpl.app.utils.Constants.LabelConstants.PRODUCT_HIERARCHY;
import static com.stpl.app.utils.Constants.LabelConstants.PROJECTIONS;
import static com.stpl.app.utils.Constants.LabelConstants.SALES_PROJ;
import static com.stpl.app.utils.Constants.LabelConstants.SPRDASH;
import static com.stpl.app.utils.Constants.StringConstants.PERCENT;
import static com.stpl.app.utils.Constants.StringConstants.SPLIT_ARROW;

import java.math.BigDecimal;
import java.math.MathContext;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;

import com.stpl.app.gtnforecasting.dao.CommonDAO;
import com.stpl.app.gtnforecasting.dao.SalesProjectionDAO;
import com.stpl.app.gtnforecasting.dao.impl.CommonDAOImpl;
import com.stpl.app.gtnforecasting.dao.impl.SalesProjectionDAOImpl;
import com.stpl.app.gtnforecasting.dto.ContractBrandDTO;
import com.stpl.app.gtnforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.gtnforecasting.dto.SalesRowDto;
import com.stpl.app.gtnforecasting.logic.CommonLogic;
import com.stpl.app.gtnforecasting.logic.DataSelectionLogic;
import com.stpl.app.gtnforecasting.logic.DataSourceConnection;
import com.stpl.app.gtnforecasting.salesprojection.form.MSalesProjection;
import com.stpl.app.gtnforecasting.salesprojection.utils.QueryUtils;
import com.stpl.app.gtnforecasting.salesprojection.utils.SalesUtils;
import com.stpl.app.gtnforecasting.salesprojectionresults.logic.NMSalesProjectionResultsLogic;
import com.stpl.app.gtnforecasting.salesprojectionresults.logic.SPRCommonLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.AlternateLookupSource;
import com.stpl.app.gtnforecasting.utils.CommonQueryUtils;
import com.stpl.app.gtnforecasting.utils.CommonUtil;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.gtnforecasting.utils.xmlparser.SQlUtil;
import com.stpl.app.model.ChProjectionSelection;
import com.stpl.app.model.CompanyMaster;
import com.stpl.app.model.ContractMaster;
import com.stpl.app.model.CustomViewDetails;
import com.stpl.app.model.RelationshipLevelDefinition;
import com.stpl.app.service.ChProjectionSelectionLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.serviceUtils.Constants;
import com.stpl.app.utils.CumulativeCalculationUtils;
import com.stpl.app.utils.UiUtils;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.ifs.ui.util.GtnSmallHashMap;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.QueryUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionList;
import com.stpl.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.util.dao.orm.CustomSQLUtil;

/**
 *
 * @author sibi
 */
public class SalesLogic {

    public static final DecimalFormat MONEY = new DecimalFormat("$0.00");
    public static final DecimalFormat UNIT = new DecimalFormat("0.00");
    public static final DecimalFormat MONEYNODECIMAL = new DecimalFormat("$#,##0");
    public static final SimpleDateFormat DBDate = new SimpleDateFormat(Constant.DATE_FORMAT);
    public static final DecimalFormat UNITNODECIMAL = new DecimalFormat("#,##0");
    public static final DecimalFormat UNITTWODECIMAL = new DecimalFormat("#,##0.00");
    public static final DecimalFormat PROJECTEDUNITDECIMAL = new DecimalFormat("#,##0.0");
    public static final String UPDATE = "  UPDATE ";
    public static final String PROJECTED_UNITS1 = "-ProjectedUnits";
    private String  start;
    private String end;
    final CommonQueryUtils commonQueryUtils = CommonQueryUtils.getInstance();
    public static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(SalesLogic.class);
    SalesProjectionDAO salesAllocationDAO = new SalesProjectionDAOImpl();
    ResourceBundle resourceBundle = ResourceBundle.getBundle("properties.tablename");
    public static final String ACTUAL_SALES = "-ActualSales";
    public static final String FREQ_VAL = "@FREVAL@";
    public static final String PROJECTED_SALES = "-ProjectedSales";
    private SessionDTO session;
    NMSalesProjectionResultsLogic sprLogic = new NMSalesProjectionResultsLogic();
    CommonLogic commonLogic = new CommonLogic();
    com.stpl.app.utils.QueryUtils utils = new com.stpl.app.utils.QueryUtils();

    public SessionDTO getSession() {
        return session;
    }

    public void setSession(SessionDTO session) {
        this.session = session;
    }

    /**
     * Returns the No. of rows in actual and projection sales per level Item.
     *
     * @param sessionDTO - Session DTO
     * @return
     * @throws PortalException
     * @throws Exception
     */
    public int getHistoryAndProjectionCount(final SessionDTO sessionDTO, final ProjectionSelectionDTO projectionSelectionDTO)  {
        String query = CustomSQLUtil.get("rows-per-level-item");
        if (Constant.VIEW.equals(projectionSelectionDTO.getSessionDTO().getAction()) && CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS.equals(projectionSelectionDTO.getScreenName())) {
            query = SQlUtil.getQuery("rows-per-level-item-view");
        }
        if (projectionSelectionDTO.getFrequencyDivision() == 1) {
            query = query.replace(Constant.FREQUENCY1_AT, "A");
        } else if (projectionSelectionDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            query = query.replace(Constant.FREQUENCY1_AT, Constant.Q);
        } else if (projectionSelectionDTO.getFrequencyDivision() == NumericConstants.TWO) {
            query = query.replace(Constant.FREQUENCY1_AT, Constant.S);
        } else if (projectionSelectionDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            query = query.replace(Constant.FREQUENCY1_AT, CommonUtils.BUSINESS_PROCESS_INDICATOR_MANDATED);
        }
        if (CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equals(projectionSelectionDTO.getScreenName())) {
            //Need to remove once the dynamic changes is done
            query = query.replace(Constant.USER_ID_ADDITION, "WHERE  AC.SESSION_ID = '@SESSION_ID' AND AC.USER_ID = '@USER_ID'");
            query = query.replace(Constant.UNION_AT, "UNION ALL");
            query = query.replace(Constant.ACTUAL_TABLE_AT, "ST_M_ACTUAL_SALES");
            query = query.replace(Constant.PROJECTION_TABLE, Constant.ST_M_SALES_PROJECTION);
            query = query.replace(Constant.USER_ID1_AT, sessionDTO.getUserId());
            query = query.replace(Constant.SESSION_ID1_AT, sessionDTO.getSessionId());
        } else if (CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS.equals(projectionSelectionDTO.getScreenName())) {
            //Need to remove once the dynamic changes is done
            String actualTable = Constant.VIEW.equals(projectionSelectionDTO.getSessionDTO().getAction()) ? "RETURNS_ACTUALS" : "ST_RETURNS_ACTUALS";
            String detailsTable = Constant.VIEW.equals(projectionSelectionDTO.getSessionDTO().getAction()) ? "RETURNS_PROJ_DETAILS" : "ST_RETURNS_PROJ_DETAILS";
            if (!Constant.VIEW.equals(projectionSelectionDTO.getSessionDTO().getAction())) {
                query = query.replace(Constant.USER_ID_ADDITION, StringUtils.EMPTY);
            }
            query = query.replace(Constant.UNION_AT, "UNION");
            query = query.replace(Constant.ACTUAL_TABLE_AT, actualTable);
            query = query.replace(Constant.PROJECTION_TABLE, detailsTable);
        } else {
            query = query.replace(Constant.USER_ID_ADDITION, StringUtils.EMPTY);
            query = query.replace(Constant.UNION_AT, "UNION ALL");
            query = query.replace(Constant.ACTUAL_TABLE_AT, "ST_NM_ACTUAL_SALES");
            query = query.replace(Constant.PROJECTION_TABLE, Constant.ST_NM_SALES_PROJECTION);
            query = query.replace(Constant.USER_ID1_AT, sessionDTO.getUserId());
            query = query.replace(Constant.SESSION_ID1_AT, sessionDTO.getSessionId());
        }
        List list = (List) HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, sessionDTO.getCurrentTableNames()));
        return Integer.valueOf(list.get(0).toString());
    }

    /**
     *
     * @param parentId
     * @param projSelDTO
     * @param isLevelsCount
     * @param initialProjSelDTO
     * @return
     */
    public int getConfiguredSalesProjectionCount(Object parentId, ProjectionSelectionDTO projSelDTO, boolean isLevelsCount, ProjectionSelectionDTO initialProjSelDTO) {
        int count = 0;
        if (projSelDTO.getPivotView() != null && Constant.VARIABLE.equals(projSelDTO.getPivotView())) {
            if (!projSelDTO.isIsFilter() && (parentId instanceof SalesRowDto)) {
                projSelDTO.setYear(Constant.ALL);

                if (projSelDTO.getActualsOrProjections().equals(Constant.BOTH)) {
                    projSelDTO.setActualsOrProjections("Actuals and Projections");
                }

                if (parentId instanceof SalesRowDto) {
                    projSelDTO.setIsProjectionTotal(false);
                    SalesRowDto parentDto = (SalesRowDto) parentId;
                    projSelDTO.setLevelNo(parentDto.getLevelNo());
                    projSelDTO.setTreeLevelNo(parentDto.getTreeLevelNo());
                    projSelDTO.setLevelValue(parentDto.getLevelValue());
                    projSelDTO.setParentNode(parentDto.getParentNode());
                    projSelDTO.setHierarchyNo(parentDto.getHierarchyNo());
                    if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equalsIgnoreCase(parentDto.getHierarchyIndicator())) {
                        projSelDTO.setCustomerHierarchyNo(projSelDTO.getHierarchyNo());
                        projSelDTO.setProductHierarchyNo(parentDto.getProductHierarchyNo());
                    } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equalsIgnoreCase(parentDto.getHierarchyIndicator())) {
                        projSelDTO.setProductHierarchyNo(projSelDTO.getHierarchyNo());
                        projSelDTO.setCustomerHierarchyNo(parentDto.getCustomerHierarchyNo());
                    }
                    projSelDTO.setHierarchyIndicator(parentDto.getHierarchyIndicator());
                    projSelDTO.setGroup(parentDto.getGroup());
                    projSelDTO.setIsTotal(parentDto.getOnExpandTotalRow() == 1);
                } else {
                    projSelDTO.setIsProjectionTotal(false);
                    if (projSelDTO.isIsCustomHierarchy()) {
                        projSelDTO.setLevelNo(1);
                        projSelDTO.setTreeLevelNo(1);
                    } else if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                        projSelDTO.setLevelNo(projSelDTO.getCustomerLevelNo() - 1);
                        projSelDTO.setTreeLevelNo(projSelDTO.getCustomerLevelNo() - 1);
                    } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                        projSelDTO.setLevelNo(projSelDTO.getProductLevelNo() - 1);
                        projSelDTO.setTreeLevelNo(projSelDTO.getProductLevelNo() - 1);
                    }
                    projSelDTO.setLevelValue(StringUtils.EMPTY);
                    projSelDTO.setHierarchyNo(StringUtils.EMPTY);
                    projSelDTO.setProductHierarchyNo(StringUtils.EMPTY);
                    projSelDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
                }
                count += sprLogic.getProjectionResultsCount(projSelDTO, isLevelsCount);
            } else {
                projSelDTO.setIsProjectionTotal(true);

                if (projSelDTO.isIsCustomHierarchy()) {
                    projSelDTO.setLevelNo(1);
                    projSelDTO.setTreeLevelNo(1);
                } else if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                    projSelDTO.setLevelNo(projSelDTO.getCustomerLevelNo());
                    projSelDTO.setTreeLevelNo(projSelDTO.getCustomerLevelNo());
                } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                    projSelDTO.setLevelNo(projSelDTO.getProductLevelNo());
                    projSelDTO.setTreeLevelNo(projSelDTO.getProductLevelNo());
                }
                projSelDTO.setLevelValue(StringUtils.EMPTY);
                projSelDTO.setHierarchyNo(StringUtils.EMPTY);
                projSelDTO.setProductHierarchyNo(StringUtils.EMPTY);
                projSelDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
                count += configureLevelsCount(projSelDTO);
            }
        } else if ((!projSelDTO.isIsFilter() && !projSelDTO.isFilterDdlb()) || parentId instanceof SalesRowDto) {
            if (parentId instanceof SalesRowDto) {
                SalesRowDto parentDto = (SalesRowDto) parentId;
                projSelDTO.setIsProjectionTotal(false);
                projSelDTO.setLevelNo(parentDto.getLevelNo() + 1);
                projSelDTO.setTreeLevelNo(parentDto.getTreeLevelNo() + 1);
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
            } else {
                projSelDTO.setIsProjectionTotal(true);
                projSelDTO.setIsTotal(true);
                projSelDTO.setHierarchyIndicator(initialProjSelDTO.getHierarchyIndicator());
                if (projSelDTO.isIsCustomHierarchy()) {
                    projSelDTO.setLevelNo(1);
                    projSelDTO.setTreeLevelNo(1);
                } else if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                    projSelDTO.setLevelNo(initialProjSelDTO.getCustomerLevelNo());
                    projSelDTO.setTreeLevelNo(initialProjSelDTO.getCustomerLevelNo());
                } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                    projSelDTO.setLevelNo(initialProjSelDTO.getProductLevelNo());
                    projSelDTO.setTreeLevelNo(initialProjSelDTO.getProductLevelNo());
                }
                projSelDTO.setHierarchyNo(StringUtils.EMPTY);
                projSelDTO.setProductHierarchyNo(StringUtils.EMPTY);
                projSelDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
            }
            count += configureLevelsCount(projSelDTO);
        } else if (isLevelsCount || projSelDTO.isFilterDdlb()) {
            projSelDTO.setIsProjectionTotal(false);
            projSelDTO.setIsTotal(true);
            projSelDTO.setLevelNo(projSelDTO.getFilterLevelNo());
            projSelDTO.setTreeLevelNo(projSelDTO.getFilterLevelNo());
            count += configureLevelsCount(projSelDTO);
        }
        return count;
    }

    /**
     *
     * @param projSelDTO
     * @return
     */
    public int configureLevelsCount(ProjectionSelectionDTO projSelDTO) {
        int levelCount = 0;
        String userGroup = StringUtils.EMPTY;
        if (!(StringUtils.isBlank(projSelDTO.getGroup())
                || Constant.NULL.equals(projSelDTO.getGroup()) || Constant.SHOW_ALL_GROUPS.equals(projSelDTO.getGroup()))) {
            userGroup = projSelDTO.getGroupFilter();
        }
        if (CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS.equals(projSelDTO.getScreenName())) {
            levelCount = getReturnsCount(projSelDTO);
        } else if (Constant.ALTERNATE_HISTORY.equals(projSelDTO.getFunctionality())) {
            projSelDTO.setTabName(projSelDTO.getFunctionality());
            levelCount = commonLogic.getCount(projSelDTO);
            if (projSelDTO.isIsProjectionTotal()) {
                levelCount += 1;
            }
        } else if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(projSelDTO.getScreenName()) || CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equals(projSelDTO.getScreenName())) {
            if (projSelDTO.isIsCustomHierarchy()) {
                if (!commonLogic.getHiearchyIndicatorFromCustomView(projSelDTO).isEmpty()) {
                    levelCount = commonLogic.getCountForCustomView(projSelDTO);
                } else {
                    return 0;
                }
            } else {
                levelCount = commonLogic.getCount(projSelDTO);
            }
        } else {
            levelCount = CommonLogic.getLevelListCount(projSelDTO.getProjectionId(), StringUtils.EMPTY, projSelDTO.getHierarchyIndicator(), projSelDTO.getTreeLevelNo(), projSelDTO.getHierarchyNo(),
                    projSelDTO.getProductHierarchyNo(), projSelDTO.getCustomerHierarchyNo(), projSelDTO.isIsFilter(), projSelDTO.isIsCustomHierarchy(),
                    projSelDTO.getCustomId(), userGroup, projSelDTO.getUserId(), projSelDTO.getSessionId(),
                    projSelDTO.getCustRelationshipBuilderSid(), projSelDTO.getProdRelationshipBuilderSid(), projSelDTO.getDiscountNoList(), projSelDTO);
        }
        projSelDTO.setLevelCount(levelCount);
        return levelCount;
    }

    /**
     *
     * @param parentId
     * @param start
     * @param offset
     * @param projSelDTO
     * @return
     */
    public List<SalesRowDto> getConfiguredSalesProjection(Object parentId, int start, int offset, ProjectionSelectionDTO projSelDTO) throws Exception {
        List<SalesRowDto> resultList;
        if ((CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equals(projSelDTO.getScreenName()) || CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(projSelDTO.getScreenName()))
                && (projSelDTO.getPivotView() != null && Constant.VARIABLE.equals(projSelDTO.getPivotView()))) {
            if (!projSelDTO.isIsFilter() && (parentId instanceof SalesRowDto)) {
                projSelDTO.setYear(Constant.ALL);

                if (projSelDTO.getActualsOrProjections().equals(Constant.BOTH)) {
                    projSelDTO.setActualsOrProjections("Actuals and Projections");
                }
                if (parentId instanceof SalesRowDto) {
                    projSelDTO.setIsProjectionTotal(false);
                    SalesRowDto parentDto = (SalesRowDto) parentId;
                    projSelDTO.setLevelNo(parentDto.getLevelNo());
                    projSelDTO.setTreeLevelNo(parentDto.getTreeLevelNo());
                    projSelDTO.setLevelValue(parentDto.getLevelValue());
                    projSelDTO.setParentNode(parentDto.getParentNode());
                    projSelDTO.setHierarchyNo(parentDto.getHierarchyNo());
                    if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equalsIgnoreCase(parentDto.getHierarchyIndicator())) {
                        projSelDTO.setCustomerHierarchyNo(projSelDTO.getHierarchyNo());
                        projSelDTO.setProductHierarchyNo(parentDto.getProductHierarchyNo());
                    } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equalsIgnoreCase(parentDto.getHierarchyIndicator())) {
                        projSelDTO.setProductHierarchyNo(projSelDTO.getHierarchyNo());
                        projSelDTO.setCustomerHierarchyNo(parentDto.getCustomerHierarchyNo());
                    }
                    projSelDTO.setHierarchyIndicator(parentDto.getHierarchyIndicator());
                    projSelDTO.setGroup(parentDto.getGroup());
                    projSelDTO.setIsTotal(parentDto.getOnExpandTotalRow() == 1);
                } else {
                    if (projSelDTO.getGroupFilter() == null || projSelDTO.getGroupFilter().isEmpty()) {
                        projSelDTO.setGroupFilter("All Sales Groups");
                    }

                    projSelDTO.setIsProjectionTotal(false);
                    if (projSelDTO.isIsCustomHierarchy()) {
                        projSelDTO.setLevelNo(1);
                        projSelDTO.setTreeLevelNo(1);
                    } else if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                        projSelDTO.setLevelNo(projSelDTO.getCustomerLevelNo());
                        projSelDTO.setTreeLevelNo(projSelDTO.getCustomerLevelNo());
                    } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                        projSelDTO.setLevelNo(projSelDTO.getProductLevelNo());
                        projSelDTO.setTreeLevelNo(projSelDTO.getProductLevelNo());
                    }
                    projSelDTO.setLevelValue(StringUtils.EMPTY);
                    projSelDTO.setHierarchyNo(StringUtils.EMPTY);
                    projSelDTO.setProductHierarchyNo(StringUtils.EMPTY);
                    projSelDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
                }
                resultList = getSalesProjectionPivotResults(start, offset, projSelDTO);
            } else {
                projSelDTO.setIsProjectionTotal(true);
                projSelDTO.setLevelValue(StringUtils.EMPTY);
                projSelDTO.setHierarchyNo(StringUtils.EMPTY);
                projSelDTO.setProductHierarchyNo(StringUtils.EMPTY);
                projSelDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
                if (projSelDTO.isIsCustomHierarchy()) {
                    projSelDTO.setLevelNo(1);
                    projSelDTO.setTreeLevelNo(1);
                } else if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                    projSelDTO.setLevelNo(projSelDTO.getCustomerLevelNo());
                    projSelDTO.setTreeLevelNo(projSelDTO.getCustomerLevelNo());
                } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                    projSelDTO.setLevelNo(projSelDTO.getProductLevelNo());
                    projSelDTO.setTreeLevelNo(projSelDTO.getProductLevelNo());
                }
                resultList = configureLevels(start, offset, start, projSelDTO);
            }
        } else if ((!projSelDTO.isIsFilter() && !projSelDTO.isFilterDdlb()) || parentId instanceof SalesRowDto) {
            if (parentId instanceof SalesRowDto) {
                SalesRowDto parentDto = (SalesRowDto) parentId;
                projSelDTO.setIsProjectionTotal(false);
                projSelDTO.setLevelNo(parentDto.getLevelNo() + 1);
                projSelDTO.setTreeLevelNo(parentDto.getTreeLevelNo() + 1);
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
                projSelDTO.setHierarchyIndicator(projSelDTO.isIsCustomHierarchy() ? getCustomViewHierarchyIndicator(projSelDTO.getCustomId(), projSelDTO.getTreeLevelNo()) : parentDto.getHierarchyIndicator());
                projSelDTO.setLevelIndicator(parentDto.getLevelIndicator());
            } else {
                projSelDTO.setIsProjectionTotal(true);
                projSelDTO.setIsTotal(true);
                if (projSelDTO.isIsCustomHierarchy()) {
                    projSelDTO.setLevelNo(1);
                    projSelDTO.setTreeLevelNo(1);
                    projSelDTO.setHierarchyIndicator(getCustomViewHierarchyIndicator(projSelDTO.getCustomId(), 1));
                } else if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                    projSelDTO.setLevelNo(projSelDTO.getCustomerLevelNo());
                    projSelDTO.setTreeLevelNo(projSelDTO.getCustomerLevelNo());
                } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(projSelDTO.getHierarchyIndicator())) {
                    projSelDTO.setLevelNo(projSelDTO.getProductLevelNo());
                    projSelDTO.setTreeLevelNo(projSelDTO.getProductLevelNo());
                }
                projSelDTO.setHierarchyNo(StringUtils.EMPTY);
                projSelDTO.setProductHierarchyNo(StringUtils.EMPTY);
                projSelDTO.setCustomerHierarchyNo(StringUtils.EMPTY);
            }
            resultList = offset > 0 ? (CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS.equals(projSelDTO.getScreenName()) ? getReturnsSalesResults(projSelDTO, start, offset) : getSalesResults(projSelDTO, start, offset)) : new ArrayList<SalesRowDto>();
        } else {
            projSelDTO.setIsProjectionTotal(false);
            projSelDTO.setIsTotal(true);
            projSelDTO.setLevelNo(projSelDTO.getFilterLevelNo());
            projSelDTO.setTreeLevelNo(projSelDTO.getFilterLevelNo());
            projSelDTO.setHierarchyNo(Constant.PERCENT);
            resultList = offset > 0 ? (CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS.equals(projSelDTO.getScreenName()) ? getReturnsSalesResults(projSelDTO, start, offset) : getSalesResults(projSelDTO, start, offset)) : new ArrayList<SalesRowDto>();
        }
        return resultList;
    }

    /**
     * Builds the query to retrieve the Sales Projection Data for Mandated and
     * Non-Mandated.
     *
     * @param projSelDTO
     * @param start
     * @param end
     * @return
     */
    public List<SalesRowDto> getSalesResults(ProjectionSelectionDTO projSelDTO, int start, int end, String sql) {
        if (projSelDTO.getFunctionality().equals(Constant.ALTERNATE_HISTORY)) {
            sql += SQlUtil.getQuery("alternate-join-loaddata");
            sql += SQlUtil.getQuery("alternate-histroy-sales-summary");
        } else if (projSelDTO.isIsCustomHierarchy() && CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(projSelDTO.getScreenName()) && !projSelDTO.isExcel()) {
            String queryNameforSales=CommonUtil.isValueEligibleForLoading()?"sales-customView-proj":"sales-customView";
            sql += SQlUtil.getQuery(queryNameforSales);
        } else {
            sql += CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(projSelDTO.getScreenName()) ? SQlUtil.getQuery("non-mandated-sales-query-new") : SQlUtil.getQuery("mandated-sales-query");
        }

        String forcastingFlavour;

        if (CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equals(projSelDTO.getScreenName())) {
            sql = sql.replace("[$USERID_SESSIONID]", StringUtils.EMPTY);
            sql = sql.replace("[$MSPM_USERID_SESSIONID]", StringUtils.EMPTY);
        }
        if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(projSelDTO.getScreenName())) {
            forcastingFlavour = "NM";
        } else {
            forcastingFlavour = "M";
        }
        sql = sql.replace("[$TABLE_NAME]", !ACTION_VIEW.getConstant().equalsIgnoreCase(projSelDTO.getSessionDTO().getAction()) ? "ST_" + forcastingFlavour + "_SALES_PROJECTION" : forcastingFlavour + "_SALES_PROJECTION");
        sql = sql.replace("[$ACTUAL_TABLE_NAME]", !ACTION_VIEW.getConstant().equalsIgnoreCase(projSelDTO.getSessionDTO().getAction()) ? "ST_" + forcastingFlavour + "_ACTUAL_SALES" : forcastingFlavour + "_ACTUAL_SALES");
        sql = sql.replace("[$MASTER_TABLE_NAME]", !ACTION_VIEW.getConstant().equalsIgnoreCase(projSelDTO.getSessionDTO().getAction()) ? "ST_" + forcastingFlavour + "_SALES_PROJECTION_MASTER" : forcastingFlavour + "_SALES_PROJECTION_MASTER");
        
        if (!ACTION_VIEW.getConstant().equalsIgnoreCase(projSelDTO.getSessionDTO().getAction()) || projSelDTO.getFunctionality().equals(Constant.ALTERNATE_HISTORY)) {
            sql = sql.replace(Constant.USER_ID1_AT, projSelDTO.getSessionDTO().getUserId());
            sql = sql.replace(Constant.SESSION_ID1_AT, projSelDTO.getSessionDTO().getSessionId());
        }
        sql = sql.replace(Constant.LEVEL_NO1, String.valueOf(projSelDTO.getTreeLevelNo()));
        sql = sql.replace("@HIERARCHY_INDICATOR", projSelDTO.getHierarchyIndicator());
        sql = sql.replace("@START", String.valueOf(start));
        sql = sql.replace("@END", String.valueOf(end));
        sql = sql.replace("@PROJECTION_MASTER_SID", String.valueOf(projSelDTO.getProjectionId()));
        if (CommonUtil.isValueEligibleForLoading()) {
            String joinQuery=" JOIN CCP_DETAILS CCP ON CCP.CCP_DETAILS_SID=SHN.CCP_DETAILS_SID LEFT JOIN ST_ITEM_UOM_DETAILS  UOM ON UOM.ITEM_MASTER_SID=CCP.ITEM_MASTER_SID AND UOM.UOM_CODE = '"+projSelDTO.getUomCode()+"'";
            sql = sql.replace("@SALESINCLUSION", projSelDTO.getSessionDTO().getSalesInclusion().equals(ALL) ? StringUtils.EMPTY : "and shn.sales_inclusion=" + projSelDTO.getSessionDTO().getSalesInclusion());
            sql = sql.replace("@UOMCODE", projSelDTO.getUomCode().equals("EACH") ? StringUtils.EMPTY : joinQuery);
            sql = sql.replace("@SUMPROJECTEDUNITS", projSelDTO.getUomCode().equals("EACH") ? "SUM(NMSP.PROJECTION_UNITS) AS PROJECTION_UNITS":"SUM(ISNULL(NMSP.PROJECTION_UNITS,0)*ISNULL(UOM.UOM_VALUE,0)) AS PROJECTION_UNITS");
            sql = sql.replace("@SUMACTUALUNITS", projSelDTO.getUomCode().equals("EACH") ? "Sum(NMSP.ACTUAL_UNITS) AS ACTUAL_UNITS":"Sum(ISNULL(NMSP.ACTUAL_UNITS,0)*ISNULL(UOM.UOM_VALUE,0)) AS ACTUAL_UNITS");
        }
        int freqNo = getFrequencyNumber(projSelDTO.getFrequency());
        sql = sql.replaceAll("@FREQDIVISION", String.valueOf(freqNo));
        if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(projSelDTO.getScreenName())) {
            sql = sql.replace("@USER_GROUP", StringUtils.isBlank(projSelDTO.getGroup())
                    || Constant.NULL.equals(projSelDTO.getGroup()) || Constant.SHOW_ALL_GROUPS.equals(projSelDTO.getGroup()) ? Constant.PERCENT : projSelDTO.getGroup());
        }
        if (!CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(projSelDTO.getScreenName()) && !CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equals(projSelDTO.getScreenName())) {
            switch (projSelDTO.getFrequencyDivision()) {
                case 1:
                    sql = sql.replace(Constant.AT_FREQUENCY_AT, "1 ");
                    sql = sql.replace(Constant.FREQUENCY_GROUP_AT, StringUtils.EMPTY);
                    break;
                case NumericConstants.TWO:
                    sql = sql.replace(Constant.AT_FREQUENCY_AT, "p.SEMI_ANNUAL");
                    sql = sql.replace(Constant.FREQUENCY_GROUP_AT, "p.SEMI_ANNUAL,");
                    break;
                case NumericConstants.FOUR:
                    sql = sql.replace(Constant.AT_FREQUENCY_AT, "p.QUARTER");
                    sql = sql.replace(Constant.FREQUENCY_GROUP_AT, "p.QUARTER,");
                    break;
                case NumericConstants.TWELVE:
                    sql = sql.replace(Constant.AT_FREQUENCY_AT, "p.MONTH");
                    sql = sql.replace(Constant.FREQUENCY_GROUP_AT, "p.MONTH,");
                    break;
                default:
                    break;
            }

        } else {
            switch (projSelDTO.getFrequencyDivision()) {

                case 1:
                    sql = sql.replace(Constant.AT_FREQUENCY_AT, "1 AS FREQ");
                    sql = sql.replace(Constant.AT_FREQUENCY_GROUP_AT, StringUtils.EMPTY);
                    sql = sql.replace("SH.@FRE@", "1 ");
                    sql = sql.replace("MSP.@FRE@", "1 ");
                    sql = sql.replace("mas.@FRE@", "1 ");
                    sql = sql.replace(",P.@FREVAL@", " ");
                    sql = sql.replace(Constant.FREQ_AT, "1 ");
                    break;
                case NumericConstants.TWO:
                    sql = sql.replace(Constant.AT_FREQUENCY_AT, "p.SEMI_ANNUAL");
                    sql = sql.replace(Constant.AT_FREQUENCY_GROUP_AT, ",p.SEMI_ANNUAL");
                    sql = sql.replace(Constant.FREQ_AT, Constant.SEMI_ANNUAL);
                    sql = sql.replace(FREQ_VAL, Constant.SEMI_ANNUAL);
                    break;
                case NumericConstants.FOUR:
                    sql = sql.replace(Constant.AT_FREQUENCY_AT, "p.QUARTER");
                    sql = sql.replace(Constant.AT_FREQUENCY_GROUP_AT, ",p.QUARTER");
                    sql = sql.replace(Constant.FREQ_AT, Constant.QUARTER);
                    sql = sql.replace(FREQ_VAL, Constant.QUARTER);
                    break;
                case NumericConstants.TWELVE:
                    sql = sql.replace(Constant.AT_FREQUENCY_AT, "p.MONTH");
                    sql = sql.replace(Constant.AT_FREQUENCY_GROUP_AT, ",p.MONTH");
                    sql = sql.replace(Constant.FREQ_AT, "MONTH");
                    sql = sql.replace(FREQ_VAL, "MONTH");
                    break;
                default:
                    break;
            } 
        }
        sql= projSelDTO.isExcel() ? sql.replaceAll(",INSTR INT", "").replace(",INSTR", ""):sql;
        String aaa=QueryUtil.replaceTableNames(sql, projSelDTO.getSessionDTO().getCurrentTableNames());
        List list = (List) HelperTableLocalServiceUtil.executeSelectQuery(aaa);
        return convertfinalResultLists(list, projSelDTO.isIsCustomHierarchy(), projSelDTO.getTreeLevelNo(), projSelDTO.getCustomerHierarchyNo(), projSelDTO.getProductHierarchyNo(), projSelDTO);
    }   

    public List<SalesRowDto> getSalesResults(ProjectionSelectionDTO projSelDTO, int start, int end)  {
        /*if no record available in ST_NM_ACTAUL_SALES table, we will show hierarchy in table */

        CommonLogic commonLogic = new CommonLogic();
        String sql;

        sql = commonLogic.insertAvailableHierarchyNo(projSelDTO);

        return getSalesResults(projSelDTO, start, end, sql);
    }

    public List<SalesRowDto> getSalesResultsForExpand(ProjectionSelectionDTO projSelDTO, Set hierarchyNoSet) {
        /*if no record available in ST_NM_ACTAUL_SALES table, we will show hierarchy in table */

        CommonLogic commonLogic = new CommonLogic();
        String sql;

        sql = commonLogic.insertSelectedHierarchHierarchyNo(hierarchyNoSet, projSelDTO);

        return getSalesResults(projSelDTO, 0, hierarchyNoSet.size(), sql);
    }

    /**
     * Converts the List of Object array returned from the database to List of
     * SalesRowDto.
     *
     * @param resulList
     * @param iscustom
     * @param treeLevelNo
     * @param lastCustomerHierNo
     * @param lastproductHierNo
     * @return
     */
    public List<SalesRowDto> convertfinalResultLists(List resulList, boolean iscustom, int treeLevelNo, String lastCustomerHierNo, String lastproductHierNo, final ProjectionSelectionDTO projectionSelectionDTO) {

        // Commented for reference
        // obj[0] -  Account Growth
        // obj[1] -  Product Growth
        // obj[2] -  Projected Sales
        // obj[3] -  Prjected Units
        // obj[4] -  Actual Sales
        // obj[5] -  Actual Units
        // obj[6] -  Level No
        // obj[7] -  LevelName
        // obj[8] -  Year
        // obj[9] -  Quarter
        // obj[10] - Base Line
        // obj[11] - Methodology
        // obj[12] - Relationship Level Sid
        // obj[13] - Hierarchy No
        // obj[14] - Row Count Map
        // obj[15] - Hierarchy Level Name
        // obj[16] - Actuals or Projection Rows
        // obj[17] - ActualsProjectionSales
        // obj[18] - ActualsProjectionUnits
        // obj[19] - CheckandUnchecked
        // obj[20] - Unchk Count
        // obj[21] - CCP Count
        // obj[22] - Hierarchy Indicator
        // obj[23] - User Group
        SalesRowDto salesRowDto = new SalesRowDto();
        List<SalesRowDto> salesRowList = new ArrayList<>();
        List<String> headerMapValue = new ArrayList<>();
        headerMapValue.addAll(projectionSelectionDTO.getHeaderMapColumn());
        String lastLevelValue = STRING_EMPTY;
        if (projectionSelectionDTO.isIsProjectionTotal() && Constant.ALTERNATE_HISTORY.equalsIgnoreCase(projectionSelectionDTO.getFunctionality())) {
            String endDate = String.format("%04d", projectionSelectionDTO.getEndYear()) + "-"
                    + String.format("%02d", projectionSelectionDTO.getEndMonth()) + "-"
                    + String.format("%02d", projectionSelectionDTO.getEndDay());

            String startDate = String.format("%04d", projectionSelectionDTO.getStartYear()) + "-"
                    + String.format("%02d", projectionSelectionDTO.getStartMonth()) + "-"
                    + String.format("%02d", projectionSelectionDTO.getStartDay());
            Object[] orderedArgs = {projectionSelectionDTO.getProjectionId(), projectionSelectionDTO.getFrequency(), "PERIOD", projectionSelectionDTO.getSessionDTO().getUserId(), projectionSelectionDTO.getSessionDTO().getSessionId(),
                projectionSelectionDTO.getStartMonth(), projectionSelectionDTO.getStartYear(), projectionSelectionDTO.getEndMonth(), projectionSelectionDTO.getEndYear(), startDate, endDate};
            List<Object[]> list = CommonLogic.callProcedure("PRC_PIVOT_PERIOD_RESULT", orderedArgs);
            salesRowDto.setLevelName("Total Alternate History");
            salesRowDto.setParent(0);
            for (int i = 0; i < list.size(); i++) {
                Object obj[] = (Object[]) list.get(i);
                int frequencyDivision = projectionSelectionDTO.getFrequencyDivision();
                String key = Constant.Q_SMALL + String.valueOf(obj[NumericConstants.THREE]) + "-" + String.valueOf(obj[NumericConstants.TWO]);
                if (frequencyDivision == 1) {
                    key = String.valueOf(obj[NumericConstants.TWO]);
                } else if (frequencyDivision == NumericConstants.FOUR) {
                    key = Constant.Q_SMALL + String.valueOf(obj[NumericConstants.THREE]) + "-" + String.valueOf(obj[NumericConstants.TWO]);
                } else if (frequencyDivision == NumericConstants.TWO) {
                    key = Constant.S_SMALL + String.valueOf(obj[NumericConstants.THREE]) + "-" + String.valueOf(obj[NumericConstants.TWO]);
                } else if (frequencyDivision == NumericConstants.TWELVE) {
                    String monthName = getMonthForInt(Integer.valueOf(String.valueOf(obj[NumericConstants.THREE])) - 1);
                    key = monthName.toLowerCase() + "-" + String.valueOf(obj[NumericConstants.TWO]);
                }
                salesRowDto.addStringProperties(StringUtils.EMPTY + key + Constant.ACTUAL_UNITS1, String.valueOf(PROJECTEDUNITDECIMAL.format(obj[0] == null ? 0 : obj[0])));
                salesRowDto.addStringProperties(StringUtils.EMPTY + key + PROJECTED_UNITS1, String.valueOf(PROJECTEDUNITDECIMAL.format(obj[1] == null ? 0 : obj[1])));
            }
            salesRowList.add(salesRowDto);
            salesRowDto = new SalesRowDto();
        }

        final SessionDTO sessionDTO = projectionSelectionDTO.getSessionDTO();
        final Map<String, List> relationshipDetailsMap = sessionDTO.getHierarchyLevelDetails();

        for (int i = 0; i < resulList.size(); i++) {
            Object obj[] = (Object[]) resulList.get(i);
            MSalesProjection.rowCountMap.put(String.valueOf(obj[NumericConstants.TEN]), obj[NumericConstants.ELEVEN]!=null ?Integer.parseInt(String.valueOf(obj[NumericConstants.ELEVEN])):null);
            if (lastLevelValue.equalsIgnoreCase(STRING_EMPTY) || lastLevelValue.equals(String.valueOf(obj[NumericConstants.TEN]))) {

                lastLevelValue = String.valueOf(obj[NumericConstants.TEN]);
                salesRowDto.setHierarchyIndicator(String.valueOf(obj[NumericConstants.SIXTEEN]));
                if (iscustom) {
                    salesRowDto.setTreeLevelNo(treeLevelNo);
                    salesRowDto.setCustomerHierarchyNo(lastCustomerHierNo);
                    salesRowDto.setProductHierarchyNo(lastproductHierNo);
                } else {
                    salesRowDto.setTreeLevelNo(Integer.valueOf(String.valueOf(relationshipDetailsMap.get(lastLevelValue).get(NumericConstants.TWO))));
                }
                salesRowDto.setHierarchyLevel(String.valueOf(relationshipDetailsMap.get(lastLevelValue).get(1)));
//                salesRowDto.setLevelName(projectionSelectionDTO.getSessionDTO().getLevelValueDiscription(String.valueOf(obj[NumericConstants.TEN]), String.valueOf(obj[NumericConstants.SIXTEEN])));
                salesRowDto.setLevelName(CommonUtil.getDisplayFormattedName(String.valueOf(obj[NumericConstants.TEN]), String.valueOf(obj[NumericConstants.SIXTEEN]), relationshipDetailsMap, projectionSelectionDTO.getSessionDTO(), projectionSelectionDTO.getDisplayFormat()));
                salesRowDto.setHierarchyIndicator(String.valueOf(obj[NumericConstants.SIXTEEN]));

            } else {

                salesRowList.add(salesRowDto);
                salesRowDto = new SalesRowDto();
                salesRowDto.setHierarchyIndicator(String.valueOf(obj[NumericConstants.SIXTEEN]));
                lastLevelValue = String.valueOf(obj[NumericConstants.TEN]);
                if (iscustom) {
                    salesRowDto.setTreeLevelNo(treeLevelNo);
                    salesRowDto.setCustomerHierarchyNo(lastCustomerHierNo);
                    salesRowDto.setProductHierarchyNo(lastproductHierNo);
                } else {
                    salesRowDto.setTreeLevelNo(Integer.valueOf(String.valueOf(relationshipDetailsMap.get(lastLevelValue).get(NumericConstants.TWO))));
                }
                salesRowDto.setHierarchyLevel(String.valueOf(relationshipDetailsMap.get(lastLevelValue).get(1)));
//                salesRowDto.setLevelName(projectionSelectionDTO.getSessionDTO().getLevelValueDiscription(String.valueOf(obj[NumericConstants.TEN]), String.valueOf(obj[NumericConstants.SIXTEEN])));
                salesRowDto.setLevelName(CommonUtil.getDisplayFormattedName(String.valueOf(obj[NumericConstants.TEN]), String.valueOf(obj[NumericConstants.SIXTEEN]), relationshipDetailsMap, projectionSelectionDTO.getSessionDTO(), projectionSelectionDTO.getDisplayFormat()));
                salesRowDto.setHierarchyIndicator(String.valueOf(obj[NumericConstants.SIXTEEN]));
            }
            salesRowDto.addBooleanProperties(Constant.CHECK, obj[NumericConstants.THIRTEEN]!=null?Integer.parseInt(String.valueOf(obj[NumericConstants.THIRTEEN])) == 0 ? new Boolean(false) : new Boolean(true):new Boolean(false));
            if (obj[NumericConstants.FOURTEEN] != null) {
                salesRowDto.setUncheckCount(Integer.parseInt(String.valueOf(obj[NumericConstants.FOURTEEN])));
            }

            salesRowDto.setParent(1);
            salesRowDto.setCcpCount(String.valueOf(obj[NumericConstants.FIFTEEN]));
            salesRowDto.addStringProperties(Constant.BASELINE, StringUtils.isBlank(String.valueOf(obj[NumericConstants.EIGHT])) || Constant.NULL.equals(String.valueOf(obj[NumericConstants.EIGHT])) ? "-" : String.valueOf(obj[NumericConstants.EIGHT]));
            salesRowDto.addStringProperties(Constant.METHODOLOGY, StringUtils.isBlank(String.valueOf(obj[NumericConstants.NINE])) || Constant.NULL.equals(String.valueOf(obj[NumericConstants.NINE])) ? "-" : String.valueOf(obj[NumericConstants.NINE]));
            salesRowDto.setHierarchyNo(String.valueOf(obj[NumericConstants.TEN]));

            if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(projectionSelectionDTO.getScreenName())) {
                if (Constant.TRADINGPARTNER.equalsIgnoreCase(salesRowDto.getHierarchyLevel()) || Constant.TRADING_PARTNER.equals(salesRowDto.getHierarchyLevel())) {
                    salesRowDto.setGroup(String.valueOf(obj[NumericConstants.SEVENTEEN]));
                } else {
                    salesRowDto.setGroup(StringUtils.EMPTY);
                }
                salesRowDto.setSecHierarchyNo(String.valueOf(obj[NumericConstants.EIGHTEEN]));
            }

            int frequencyDivision = projectionSelectionDTO.getFrequencyDivision();
            String key = Constant.Q_SMALL + String.valueOf(obj[NumericConstants.SEVEN]) + "-" + String.valueOf(obj[NumericConstants.SIX]);
            if (frequencyDivision == 1) {
                key = String.valueOf(obj[NumericConstants.SIX]);
            } else if (frequencyDivision == NumericConstants.FOUR) {
                key = Constant.Q_SMALL + String.valueOf(obj[NumericConstants.SEVEN]) + "-" + String.valueOf(obj[NumericConstants.SIX]);
            } else if (frequencyDivision == NumericConstants.TWO) {
                key = Constant.S_SMALL + String.valueOf(obj[NumericConstants.SEVEN]) + "-" + String.valueOf(obj[NumericConstants.SIX]);
            } else if (frequencyDivision == NumericConstants.TWELVE) {
                String monthName = getMonthForInt(Integer.valueOf(String.valueOf(obj[NumericConstants.SEVEN])) - 1);
                key = monthName.toLowerCase() + "-" + String.valueOf(obj[NumericConstants.SIX]);
            }
            if (CommonUtil.isValueEligibleForLoading()) {
                salesRowDto.setSalesInclusion(obj[NumericConstants.NINETEEN] != null?String.valueOf(obj[NumericConstants.NINETEEN]):StringUtils.EMPTY);
            }
            if (Integer.parseInt(String.valueOf(obj[NumericConstants.TWELVE])) == 0) {
                if (CommonUtil.isValueEligibleForLoading() &&salesRowDto.getSalesInclusion().isEmpty()) {
                    salesRowDto.addStringProperties(StringUtils.EMPTY + key + PROJECTED_SALES, StringUtils.EMPTY);
                    salesRowDto.addStringProperties(StringUtils.EMPTY + key + PROJECTED_UNITS1, StringUtils.EMPTY);
                    salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-ProductGrowth", StringUtils.EMPTY);
                    salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-AccountGrowth", StringUtils.EMPTY);
                    headerMapValue.remove(key + PROJECTED_SALES);
                    headerMapValue.remove(key + PROJECTED_UNITS1);
                } else {
                    salesRowDto.addStringProperties(StringUtils.EMPTY + key + PROJECTED_SALES, CommonUtil.getConversionFormattedValue(projectionSelectionDTO, obj[NumericConstants.TWO], true));
                    salesRowDto.addStringProperties(StringUtils.EMPTY + key + PROJECTED_UNITS1, String.valueOf(UNITNODECIMAL.format(obj[NumericConstants.THREE] == null ? 0 : obj[NumericConstants.THREE])));
                    salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-ProductGrowth", String.valueOf(UNITTWODECIMAL.format(obj[1] == null ? 0 : obj[1])) + Constant.PERCENT);
                    salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-AccountGrowth", String.valueOf(UNITTWODECIMAL.format(obj[0] == null ? 0 : obj[0])) + Constant.PERCENT);
                    headerMapValue.remove(key + PROJECTED_SALES);
                    headerMapValue.remove(key + PROJECTED_UNITS1);
                }
            } else {
                if (CommonUtil.isValueEligibleForLoading() && salesRowDto.getSalesInclusion().isEmpty()) {
                    salesRowDto.addStringProperties(StringUtils.EMPTY + key + ACTUAL_SALES, StringUtils.EMPTY);
                    salesRowDto.addStringProperties(StringUtils.EMPTY + key + Constant.ACTUAL_UNITS1, StringUtils.EMPTY);
                    salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-HistoryProjectedSales", StringUtils.EMPTY);
                    salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-HistoryProjectedUnits", StringUtils.EMPTY);
                    headerMapValue.remove(key + ACTUAL_SALES);
                    headerMapValue.remove(key + Constant.ACTUAL_UNITS1);
                } else {
                    salesRowDto.addStringProperties(StringUtils.EMPTY + key + ACTUAL_SALES, CommonUtil.getConversionFormattedValue(projectionSelectionDTO, obj[NumericConstants.FOUR], true));
                    salesRowDto.addStringProperties(StringUtils.EMPTY + key + Constant.ACTUAL_UNITS1, String.valueOf(UNITNODECIMAL.format(obj[NumericConstants.FIVE] == null ? 0 : obj[NumericConstants.FIVE])));
                    salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-HistoryProjectedSales", String.valueOf(0));
                    salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-HistoryProjectedUnits", String.valueOf(0));
                    headerMapValue.remove(key + ACTUAL_SALES);
                    headerMapValue.remove(key + Constant.ACTUAL_UNITS1);
                }
            }

            if (obj[NumericConstants.FOURTEEN] != null) {
                salesRowDto.setUncheckCount(Integer.parseInt(String.valueOf(obj[NumericConstants.FOURTEEN])));
            }
            salesRowDto.setCcpCount(String.valueOf(obj[NumericConstants.FIFTEEN]));
            if (projectionSelectionDTO.isLevelFilter()) {
                salesRowDto.setParent(0);
            }
            if (i == (resulList.size() - 1)) {
                salesRowList.add(salesRowDto);
            }
        }

        if (projectionSelectionDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            Set<String> groupLevel = new HashSet<>();
            for (int i = 0; i < salesRowList.size(); i++) {
                SalesRowDto salesDto = salesRowList.get(i);
                if (groupLevel.add(String.valueOf(salesDto.getLevelName()))) {
                    for (String headeValue : headerMapValue) {
                        if (headeValue.contains("Actual")) {
                            salesDto.addStringProperties(StringUtils.EMPTY + headeValue, String.valueOf(headeValue.contains("Sales") ? MONEYNODECIMAL.format(0) : PROJECTEDUNITDECIMAL.format(0)));
                        }
                    }
                    salesRowList.add(salesDto);
                }
            }
        }
        return salesRowList;

    }

    public int getSalesCount(SalesRowDto expandedParent, final Map<String, Object> parameters, ProjectionSelectionDTO projSelDTO) {
        int size = 0;
        try {
            Map<String, Object> input = new HashMap<>();
            Map<String, String> join = new HashMap<>();
            if (projSelDTO.isIsCustomHierarchy()) {
                prepareCustomCountParameters(parameters, input, join, expandedParent, projSelDTO, false, false);
            } else {
                configureCountParameterMap(expandedParent, input, join, parameters, projSelDTO);
            }
            parameters.put(JOIN_MAP.getConstant(), join);
            parameters.put(INPUT_MAP.getConstant(), input);
            List resultList = salesAllocationDAO.executeQuery(parameters);
            size = Integer.parseInt(String.valueOf(resultList.get(0)));
        } catch (Exception ex) {
            LOGGER.error(ex + " in getSalesCount");
        }

        return size;
    }

    private void prepareCustomCountParameters(final Map<String, Object> parameters, final Map<String, Object> input, final Map<String, String> join,
            final SalesRowDto expandedParent, final ProjectionSelectionDTO projSelDTO, final boolean isTotalSales, final boolean isUpdate) {
        join.put("?DECLARECCP?", QueryUtils.addDeclareQueryJoin(true, isUpdate));
        join.put("?JOINCCP?", QueryUtils.addCcpJoinQuery(true, isUpdate));

        if (!isUpdate) {
            input.put(Constant.HNOC1, (expandedParent == null || isTotalSales) ? PERCENT.getConstant() : expandedParent.getCustomerHierarchyNo());
            input.put(Constant.HNOP1, (expandedParent == null || isTotalSales) ? PERCENT.getConstant() : expandedParent.getProductHierarchyNo());
            input.put(LEVEL_NO_C.getConstant(), String.valueOf(parameters.get(LEVEL_NO_C.getConstant())));
            input.put(LEVEL_NO_P.getConstant(), String.valueOf(parameters.get(LEVEL_NO_P.getConstant())));
            input.put(H_INDICATOR.getConstant(), String.valueOf(parameters.get(H_INDICATOR.getConstant())));
        }
        input.put("?RBSIDC?", projSelDTO.getCustRelationshipBuilderSid());
        input.put("?RBSIDP?", projSelDTO.getProdRelationshipBuilderSid());
        if (ANNUAL.getConstant().equalsIgnoreCase(String.valueOf(projSelDTO.getFrequency())) || ANNUAL.getConstant().equalsIgnoreCase(String.valueOf(projSelDTO.getFrequency()))) {
            join.put(Constant.SELECTFREQJOIN1, " 'null' as FREQUENCY, P.\"YEAR\" AS FREQYR,");
            join.put(Constant.GROUPFREQJOIN1, Constant.PFREQUENCY_PYEAR);
            join.put(Constant.ORDERFREQJOIN1, "SA.FREQYR,");
        } else {
            join.put(Constant.SELECTFREQJOIN1, "P.?FREQUENCY? as FREQUENCY, P.\"YEAR\" AS FREQYR,");
            join.put(Constant.GROUPFREQJOIN1, Constant.PFREQUENCY_PYEAR);
            join.put(Constant.ORDERFREQJOIN1, "SA.FREQUENCY, SA.FREQYR,");
        }
        if (!StringUtils.isBlank(projSelDTO.getProjectionStartDate()) && !Constant.NULL.equalsIgnoreCase(projSelDTO.getProjectionStartDate())) {
            input.put(Constant.PROJECTION_STARTDATE, " AND P.PERIOD_DATE >= '" + projSelDTO.getProjectionStartDate() + "' ");
        } else {
            input.put(Constant.PROJECTION_STARTDATE, StringUtils.EMPTY);
        }
        input.put(Constant.FREQUENCY1, SalesUtils.getPeriodFrequecy(projSelDTO.getFrequency()));
        input.put("?CVSID?", projSelDTO.getCustomId());
        input.put("?COUNTFREQUENCY?", SalesUtils.getPeriodCountFrequecy(projSelDTO.getFrequency()));
        input.put(PM_SID.getConstant(), projSelDTO.getProjectionId());
    }

    private void configureCountParameterMap(final SalesRowDto expandedParent, final Map<String, Object> input, final Map<String, String> join, final Map<String, Object> parameters, final ProjectionSelectionDTO projSelDTO) {
        if (INDICATOR_LOGIC_CUSTOMER_HIERARCHY.getConstant().equalsIgnoreCase(projSelDTO.getHierarchyIndicator())) {
            input.put(Constant.PHTABLE, Constant.PROJECTION_CUST_HIERARCHY1);
            input.put(Constant.RBSID1, projSelDTO.getCustRelationshipBuilderSid());
            join.put("?PRODJOIN?", StringUtils.EMPTY);
        } else if (INDICATOR_LOGIC_PRODUCT_HIERARCHY.getConstant().equalsIgnoreCase(projSelDTO.getHierarchyIndicator())) {
            input.put(Constant.PHTABLE, Constant.PROJECTION_PROD_HIERARCHY1);
            input.put(Constant.RBSID1, projSelDTO.getProdRelationshipBuilderSid());
            join.put("?PRODJOIN?", "JOIN ITEM_MASTER IM ON IM.ITEM_MASTER_SID = CCP.ITEM_MASTER_SID " + QueryUtils.getTherapJoin(PERCENT.getConstant()));
        }
        if (!projSelDTO.isLevelFilter()) {
            input.put(Constant.HNO1, expandedParent == null ? PERCENT.getConstant() : expandedParent.getHierarchyNo());
            input.put(Constant.LEVELNO1, String.valueOf(parameters.get(LEVEL_NO.getConstant())));
        } else {
            input.put(Constant.LEVELNO1, projSelDTO.getLevelFilterValue());
            input.put(Constant.HNO1, PERCENT.getConstant());
        }
        input.put(PM_SID.getConstant(), projSelDTO.getProjectionId());
    }

    public List<SalesRowDto> generateSalesAllocation(SalesRowDto expandedParent, final Map<String, Object> parameters, ProjectionSelectionDTO projSelDTO,
            final int start, final int offset, final boolean isExpandCollapse, final boolean isTotalSales) {
        List<SalesRowDto> resultList = new ArrayList<>();
        try {
            Map<String, Object> inputs = new HashMap<>();
            if (projSelDTO.getHierarchyIndicator().equals(INDICATOR_LOGIC_PRODUCT_HIERARCHY.getConstant())) {
                resultList = generateProductView(expandedParent, projSelDTO, parameters, inputs, start, offset, isExpandCollapse, isTotalSales);
            } else if (projSelDTO.getHierarchyIndicator().equals(INDICATOR_LOGIC_CUSTOMER_HIERARCHY.getConstant())) {
                resultList = generateCustomerView(expandedParent, projSelDTO, parameters, inputs, start, offset, isExpandCollapse, isTotalSales);
            } else if (projSelDTO.getHierarchyIndicator().equals(INDICATOR_LOGIC_CUSTOM_HIERARCHY.getConstant())) {
                resultList = generateCustomView(expandedParent, projSelDTO, parameters, inputs, start, offset, isExpandCollapse, isTotalSales);
            }
        } catch (Exception ex) {
            LOGGER.error(ex + " in generateSalesAllocation");
        }
        return resultList;
    }

    private List<SalesRowDto> generateProductView(final SalesRowDto expandedParent, final ProjectionSelectionDTO projSelDTO,
            final Map<String, Object> parameters, final Map<String, Object> input, final int start, final int offset,
            final boolean isExpandCollapse, final boolean isTotalSales) throws PortalException, SystemException {
        prepareGenerateInputs(expandedParent, projSelDTO, parameters, input, start, offset, false, isTotalSales, false);
        List returnList = salesAllocationDAO.executeQuery(parameters);
        List<SalesRowDto> resultList = processSalesResultViewList(expandedParent, returnList, projSelDTO, isExpandCollapse, false, isTotalSales, parameters);
        return resultList;
    }

    private List<SalesRowDto> generateCustomerView(final SalesRowDto expandedParent, final ProjectionSelectionDTO projSelDTO, final Map<String, Object> parameters, final Map<String, Object> input,
            final int start, final int offset, final boolean isExpandCollapse, final boolean isTotalSales) throws PortalException, SystemException {
        prepareGenerateInputs(expandedParent, projSelDTO, parameters, input, start, offset, true, isTotalSales, false);
        List returnList = salesAllocationDAO.executeQuery(parameters);
        List<SalesRowDto> resultList = processSalesResultViewList(expandedParent, returnList, projSelDTO, isExpandCollapse, true, isTotalSales, parameters);
        return resultList;
    }

    private List<SalesRowDto> generateCustomView(final SalesRowDto expandedParent, final ProjectionSelectionDTO projSelDTO, final Map<String, Object> parameters, final Map<String, Object> input,
            final int start, final int offset, final boolean isExpandCollapse, final boolean isTotalSales) throws PortalException, SystemException {
        if (isTotalSales) {
            prepareGenerateInputs(expandedParent, projSelDTO, parameters, input, start, offset, true, isTotalSales, false);
        } else {
            prepareCustomGenerateInputs(expandedParent, projSelDTO, parameters, input, start, offset, false, false);
        }
        List returnList = salesAllocationDAO.executeQuery(parameters);
        List<SalesRowDto> resultList = processSalesResultViewList(expandedParent, returnList, projSelDTO, isExpandCollapse, true, isTotalSales, parameters);
        return resultList;
    }

    private void prepareGenerateInputs(final SalesRowDto expandedParent, final ProjectionSelectionDTO projSelDTO,
            final Map<String, Object> parameters, final Map<String, Object> input, final int start, final int offset,
            final boolean isCustomer, final boolean isTotalSales, final boolean isUpdate) {
        Map<String, String> join = new HashMap<>();
        join.put("?DECLARECCP?", QueryUtils.addDeclareQueryJoin(false, isUpdate));
        join.put("?JOINCCP?", QueryUtils.addCcpJoinQuery(false, isUpdate));
        if (!projSelDTO.isLevelFilter()) {
            if (!isUpdate) {
                input.put(Constant.HNO1, (expandedParent == null || isTotalSales) ? PERCENT.getConstant() : expandedParent.getHierarchyNo());
                input.put(Constant.LEVELNO1, String.valueOf(parameters.get(LEVEL_NO.getConstant())));
            }
        } else {
            input.put(Constant.LEVELNO1, projSelDTO.getLevelFilterValue());
            input.put(Constant.HNO1, PERCENT.getConstant());
        }
        if (ANNUAL.getConstant().equalsIgnoreCase(String.valueOf(projSelDTO.getFrequency())) || ANNUAL.getConstant().equalsIgnoreCase(String.valueOf(projSelDTO.getFrequency()))) {
            join.put(Constant.SELECTFREQJOIN1, " 'null' as FREQUENCY, P.\"YEAR\" AS FREQYR,");
            join.put(Constant.GROUPFREQJOIN1, Constant.PFREQUENCY_PYEAR);
            join.put(Constant.ORDERFREQJOIN1, "SA.FREQYR,");
        } else {
            join.put(Constant.SELECTFREQJOIN1, "P.?FREQUENCY? as FREQUENCY, P.\"YEAR\" AS FREQYR,");
            join.put(Constant.GROUPFREQJOIN1, Constant.PFREQUENCY_PYEAR);
            join.put(Constant.ORDERFREQJOIN1, "SA.FREQUENCY, SA.FREQYR,");
        }
        input.put(Constant.FREQUENCY1, SalesUtils.getPeriodFrequecy(projSelDTO.getFrequency()));

        if (isCustomer) {
            // inputs for customer hierarchy
            input.put(Constant.PHTABLE, Constant.PROJECTION_CUST_HIERARCHY1);
            input.put(Constant.RBSID1, projSelDTO.getCustRelationshipBuilderSid());
            input.put("?THERAP?", StringUtils.EMPTY);
            input.put(H_INDICATOR.getConstant(), INDICATOR_LOGIC_CUSTOMER_HIERARCHY.getConstant());
        } else {
            //inputs for product hierarchy
            input.put(Constant.PHTABLE, Constant.PROJECTION_PROD_HIERARCHY1);
            input.put(Constant.RBSID1, projSelDTO.getProdRelationshipBuilderSid());
            input.put("?THERAP?", QueryUtils.getTherapJoin(PERCENT.getConstant()));

            input.put(H_INDICATOR.getConstant(), INDICATOR_LOGIC_PRODUCT_HIERARCHY.getConstant());
        }
        if (!StringUtils.isBlank(projSelDTO.getProjectionStartDate()) && !Constant.NULL.equalsIgnoreCase(projSelDTO.getProjectionStartDate())) {
            input.put(Constant.PROJECTION_STARTDATE, " AND P.PERIOD_DATE > '" + projSelDTO.getProjectionStartDate() + "' ");
        } else {
            input.put(Constant.PROJECTION_STARTDATE, StringUtils.EMPTY);
        }
        input.put("?COUNTFREQUENCY?", SalesUtils.getPeriodCountFrequecy(projSelDTO.getFrequency()));
        input.put(USER_ID.getConstant(), projSelDTO.getUserId());
        input.put(SESSION_ID.getConstant(), projSelDTO.getSessionDTO().getSessionId());
        input.put(PM_SID.getConstant(), projSelDTO.getProjectionId());
        input.put(START.getConstant(), start);
        input.put(OFFSET.getConstant(), offset);
        parameters.put(JOIN_MAP.getConstant(), join);
        parameters.put(INPUT_MAP.getConstant(), input);
    }

    private void prepareCustomGenerateInputs(final SalesRowDto expandedParent, final ProjectionSelectionDTO projSelDTO,
            final Map<String, Object> parameters, final Map<String, Object> input, final int start,
            final int offset, final boolean isTotalSales, final boolean isUpdate) {

        Map<String, String> join = new HashMap<>();
        prepareCustomCountParameters(parameters, input, join, expandedParent, projSelDTO, isTotalSales, isUpdate);
        input.put(USER_ID.getConstant(), projSelDTO.getUserId());
        input.put(SESSION_ID.getConstant(), projSelDTO.getSessionDTO().getSessionId());
        input.put(START.getConstant(), start);
        input.put(OFFSET.getConstant(), offset);
        parameters.put(JOIN_MAP.getConstant(), join);
        parameters.put(INPUT_MAP.getConstant(), input);
    }

    private List<SalesRowDto> processSalesResultViewList(final SalesRowDto expandedParent, final List returnList,
            final ProjectionSelectionDTO projSelDTO, final boolean isExpandCollapse,
            final boolean isCustomer, final boolean isTotalSales, final Map<String, Object> parameters) {
        /*
         GTS_SALES, 0
         CONTRACT_SALES, 1 
         PER_OF_BUSINESS, 2 
         HIERARCHY_NO, 3
         LEVEL_NO, 4
         FREQUENCY, 5
         YEAR, 6
         CHECK_RECORD, 7
         CCPCOUNT, 8
         ACTUALPROJ, 9
         H_INDICATOR, 10
         LEVEL_NAME; 11
         */
        List<SalesRowDto> resultList = new ArrayList<>();
        SalesRowDto salesRowDto = new SalesRowDto();
        String hierarchyNo = StringUtils.EMPTY;
        String columnGts = "-GrossTradeSales";
        String columnPob = "-%OfBusiness";
        String columnCs = "-ContractSales";
        int maxLevelNo = 0;
        GtnSmallHashMap monthMap = new GtnSmallHashMap();
        String hierarchyIndicator = StringUtils.EMPTY;
        LOGGER.debug("isExpandCollapse"+isExpandCollapse);
        LOGGER.debug("isCustomer"+isCustomer);

        if (MONTHLY.getConstant().equalsIgnoreCase(projSelDTO.getFrequency())) {
            monthMap = SalesUtils.getMonthMap();
        }

        if (projSelDTO.isIsCustomHierarchy()) {
            hierarchyIndicator = INDICATOR_LOGIC_CUSTOM_HIERARCHY.getConstant();
        }
        maxLevelNo = projSelDTO.getLastLevelNo();
        for (int i = 0, j = returnList.size(); i < j; i++) {
            Object[] object = (Object[]) returnList.get(i);
            if (!hierarchyNo.equals(String.valueOf(object[NumericConstants.THREE]))) {
                if (i != 0) {
                    resultList.add(salesRowDto);
                }
                salesRowDto = new SalesRowDto();
                hierarchyNo = String.valueOf(object[NumericConstants.THREE]);

                salesRowDto.setParentHierarchyIndicator(expandedParent == null ? StringUtils.EMPTY : expandedParent.getHierarchyIndicator());
                salesRowDto.setParentHierarchyNo(expandedParent == null ? StringUtils.EMPTY : expandedParent.getHierarchyNo());
                salesRowDto.setHierarchyIndicator(hierarchyIndicator);
                if (isTotalSales) {
                    salesRowDto.setHierarchyNo(PERCENT.getConstant());
                    salesRowDto.setLevelNo(0);
                    salesRowDto.setLevelName("Total Sales");
                } else {
                    if (projSelDTO.isIsCustomHierarchy()) {
                        if (expandedParent == null) {
                            if (INDICATOR_LOGIC_CUSTOMER_HIERARCHY.getConstant().equalsIgnoreCase(String.valueOf(object[NumericConstants.TEN]))) {
                                salesRowDto.setCustomerHierarchyNo(hierarchyNo);
                                salesRowDto.setProductHierarchyNo(PERCENT.getConstant());
                            } else if (INDICATOR_LOGIC_PRODUCT_HIERARCHY.getConstant().equalsIgnoreCase(String.valueOf(object[NumericConstants.TEN]))) {
                                salesRowDto.setProductHierarchyNo(hierarchyNo);
                                salesRowDto.setCustomerHierarchyNo(PERCENT.getConstant());
                            }
                            if (INDICATOR_LOGIC_CUSTOMER_HIERARCHY.getConstant().equalsIgnoreCase(String.valueOf(object[NumericConstants.TEN]))) {
                                salesRowDto.setCustomerLevelNo(Constant.STRING_ONE);
                                salesRowDto.setProductLevelNo(PERCENT.getConstant());
                            } else if (INDICATOR_LOGIC_PRODUCT_HIERARCHY.getConstant().equalsIgnoreCase(String.valueOf(object[NumericConstants.TEN]))) {
                                salesRowDto.setProductLevelNo(Constant.STRING_ONE);
                                salesRowDto.setCustomerLevelNo(PERCENT.getConstant());
                            }
                        } else {
                            if (expandedParent.getHierarchyIndicator().equalsIgnoreCase(String.valueOf(object[NumericConstants.TEN]))) {
                                if (INDICATOR_LOGIC_CUSTOMER_HIERARCHY.getConstant().equalsIgnoreCase(String.valueOf(object[NumericConstants.TEN]))) {
                                    salesRowDto.setCustomerHierarchyNo(hierarchyNo);
                                    salesRowDto.setProductHierarchyNo(expandedParent.getProductHierarchyNo());
                                } else if (INDICATOR_LOGIC_PRODUCT_HIERARCHY.getConstant().equalsIgnoreCase(String.valueOf(object[NumericConstants.TEN]))) {
                                    salesRowDto.setProductHierarchyNo(hierarchyNo);
                                    salesRowDto.setCustomerHierarchyNo(expandedParent.getCustomerHierarchyNo());
                                }
                            } else if (INDICATOR_LOGIC_CUSTOMER_HIERARCHY.getConstant().equalsIgnoreCase(String.valueOf(object[NumericConstants.TEN]))) {
                                salesRowDto.setCustomerHierarchyNo(hierarchyNo);
                                salesRowDto.setProductHierarchyNo(expandedParent.getProductHierarchyNo());
                            } else if (INDICATOR_LOGIC_PRODUCT_HIERARCHY.getConstant().equalsIgnoreCase(String.valueOf(object[NumericConstants.TEN]))) {
                                salesRowDto.setProductHierarchyNo(hierarchyNo);
                                salesRowDto.setCustomerHierarchyNo(expandedParent.getCustomerHierarchyNo());
                            }
                            if (INDICATOR_LOGIC_CUSTOMER_HIERARCHY.getConstant().equalsIgnoreCase(String.valueOf(object[NumericConstants.TEN]))) {
                                salesRowDto.setCustomerLevelNo(String.valueOf(object[NumericConstants.FOUR]));
                                salesRowDto.setProductLevelNo(PERCENT.getConstant());
                            } else if (INDICATOR_LOGIC_PRODUCT_HIERARCHY.getConstant().equalsIgnoreCase(String.valueOf(object[NumericConstants.TEN]))) {
                                salesRowDto.setProductLevelNo(String.valueOf(object[NumericConstants.FOUR]));
                                salesRowDto.setCustomerLevelNo(PERCENT.getConstant());
                            }
                        }
                    }
                    if (session != null) {
                        if (projSelDTO.isIsCustomHierarchy()) {
                            salesRowDto.setLevelName(session.getLevelValueDiscription(hierarchyNo, String.valueOf(parameters.get(H_INDICATOR.getConstant()))));
                        } else if (projSelDTO.getItemMap() != null) {
                            Map<String, SalesRowDto> itemMap = projSelDTO.getItemMap();
                            SalesRowDto ndcTypeDto = itemMap.get(hierarchyNo);
                            if (ndcTypeDto != null) {
                                if (projSelDTO.getNdcType() != null && !Constant.NULL.equalsIgnoreCase(projSelDTO.getNdcType())) {
                                    if (LEVEL_NDC_8.getConstant().equalsIgnoreCase(projSelDTO.getNdcType())) {
                                        salesRowDto.setLevelName(ndcTypeDto.getNdc8());
                                    } else if (LEVEL_NDC_10.getConstant().equalsIgnoreCase(projSelDTO.getNdcType())) {
                                        salesRowDto.setLevelName(session.getLevelValueDiscription(hierarchyNo, projSelDTO.getHierarchyIndicator()));
                                    } else if (LEVEL_NDC_11.getConstant().equalsIgnoreCase(projSelDTO.getNdcType())) {
                                        salesRowDto.setLevelName(ndcTypeDto.getNdc11());
                                    } else {
                                        salesRowDto.setLevelName(session.getLevelValueDiscription(hierarchyNo, projSelDTO.getHierarchyIndicator()));
                                    }
                                }
                            } else {
                                salesRowDto.setLevelName(session.getLevelValueDiscription(hierarchyNo, projSelDTO.getHierarchyIndicator()));
                            }
                        } else {
                            salesRowDto.setLevelName(session.getLevelValueDiscription(hierarchyNo, projSelDTO.getHierarchyIndicator()));
                        }
                    } else {
                        salesRowDto.setLevelName(hierarchyNo);
                    }
                    salesRowDto.setHierarchyNo(hierarchyNo);
                    salesRowDto.setLevelNo(UiUtils.parseStringToInteger(String.valueOf(object[NumericConstants.FOUR])));
                }
                salesRowDto.setHierarchialLevelName(String.valueOf(object[NumericConstants.ELEVEN]));
                if (maxLevelNo == salesRowDto.getLevelNo() || isTotalSales) {
                    salesRowDto.setParent(0);
                } else if (PRODUCT_HIERARCHY.getConstant().equalsIgnoreCase(String.valueOf(projSelDTO.getView()))
                        && LEVEL_BRAND.getConstant().equalsIgnoreCase(projSelDTO.getLevel())
                        && String.valueOf(object[NumericConstants.ELEVEN]).contains(Constant.BRAND_CAPS)) {
                    salesRowDto.setParent(0);
                } else if (PRODUCT_HIERARCHY.getConstant().equalsIgnoreCase(String.valueOf(projSelDTO.getView()))
                        && LEVEL_NDC.getConstant().equalsIgnoreCase(projSelDTO.getLevel())
                        && String.valueOf(object[NumericConstants.ELEVEN]).contains(Constant.NDC)) {
                    salesRowDto.setParent(0);
                } else {
                    salesRowDto.setParent(1);
                }
                salesRowDto.setHierarchyIndicator(String.valueOf(object[NumericConstants.TEN]));
                salesRowDto.setCheckRecordCount(String.valueOf(object[NumericConstants.SEVEN]).equals(Constant.NULL) || StringUtils.isBlank(String.valueOf(object[NumericConstants.SEVEN])) ? Constant.DASH : String.valueOf(object[NumericConstants.SEVEN]));
                salesRowDto.setCcpCount(String.valueOf(object[NumericConstants.EIGHT]).equals(Constant.NULL) || StringUtils.isBlank(String.valueOf(object[NumericConstants.EIGHT])) ? Constant.DASH : String.valueOf(object[NumericConstants.EIGHT]));
                int value = Integer.valueOf((object[NumericConstants.SEVEN] == null) ? Constant.DASH : object[NumericConstants.SEVEN].toString());
                salesRowDto.addBooleanProperties(Constant.CHECK, value >= Integer.valueOf(salesRowDto.getCcpCount()));
            }
            hierarchyNo = String.valueOf(object[NumericConstants.THREE]);
            String key = StringUtils.EMPTY;
            if (QUARTERLY.getConstant().equalsIgnoreCase(projSelDTO.getFrequency())) {
                key = Constant.Q_SMALL + String.valueOf(object[NumericConstants.FIVE]) + String.valueOf(object[NumericConstants.SIX]);
            }
            if ((MONTHLY.getConstant().equalsIgnoreCase(projSelDTO.getFrequency())) && (monthMap != null)) {
                key = monthMap.get(String.valueOf(object[NumericConstants.FIVE])) + String.valueOf(object[NumericConstants.SIX]);
            }
            if (ANNUAL.getConstant().equalsIgnoreCase(projSelDTO.getFrequency())
                    || ANNUALLY.getConstant().equalsIgnoreCase(projSelDTO.getFrequency())) {
                key = String.valueOf(object[NumericConstants.SIX]);
            }
            salesRowDto.addStringProperties(key + columnGts, String.valueOf(object[0]));
            salesRowDto.addStringProperties(key + columnCs, String.valueOf(object[1]));
            salesRowDto.addStringProperties(key + columnPob, String.valueOf(object[NumericConstants.TWO]));
            if (i == returnList.size() - 1) {
                resultList.add(salesRowDto);
            }
        }
        return resultList;
    }

    public boolean checkSelectAll(final String sessionId, final String projectionId, final String userId) throws PortalException, SystemException {
        boolean returnValue = false;
        Map<String, Object> parameters = new HashMap<>();
        Map<String, String> input = new HashMap<>();
        input.put(Constant.SESSIONID1, sessionId);
        input.put(Constant.USERID1, userId);
        input.put(Constant.PMSID1, projectionId);
        parameters.put(Constant.INPUT, input);
        parameters.put(INDICATOR.getConstant(), "checkSelectAll");
        List resultList = salesAllocationDAO.executeQuery(parameters);
        if ((resultList != null) && (!resultList.isEmpty() && resultList.size() == 1)) {
            returnValue = resultList.get(0) == null ? false : Boolean.valueOf(String.valueOf(resultList.get(0)));
        }
        return returnValue;
    }

    /**
     * To call Discount Actuals Insert procedure
     *
     * @param projectionId
     * @param userId
     * @param sessionId
     * @return
     */
    public static boolean callSalesCalculationProcedure(int projectionId, String userId, String sessionId) {
        Connection connection = null;
        DataSource datasource;
        CallableStatement statement = null;
        try {
            Context initialContext = new InitialContext();
            datasource = (DataSource) initialContext.lookup("java:jboss/datasources/jdbc/appDataPool");
            if (datasource != null) {
                connection = datasource.getConnection();
            }
            if (connection != null) {
                LOGGER.debug(" Executing Sales PRC_CH_SALES_ALLOCATION_PROJ procedure ");
                statement = connection.prepareCall("{call PRC_CH_SALES_ALLOCATION_PROJ(?,?,?)}");

                statement.setInt(1, projectionId);
                statement.setInt(NumericConstants.TWO, Integer.parseInt(userId));
                statement.setInt(NumericConstants.THREE, Integer.parseInt(sessionId));
                statement.execute();
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException ex) {
                LOGGER.error(ex);
            }
        }
        return true;
    }

    public void saveForProcedureCall(final ProjectionSelectionDTO projectionDTO) throws PortalException, SystemException {
        Map<String, Object> parameters = new HashMap<>();
        Map<String, String> input = new HashMap<>();
        input.put("?METHODOLOGY?", projectionDTO.getMethodology());
        input.put("?CALCPERIOD?", projectionDTO.getBaseline());
        input.put(Constant.PMSID1, String.valueOf(projectionDTO.getProjectionId()));
        input.put(Constant.SESSIONID1, String.valueOf(projectionDTO.getSessionDTO().getSessionId()));
        input.put(Constant.USERID1, String.valueOf(projectionDTO.getUserId()));
        parameters.put(INDICATOR.getConstant(), "prepareProcedureCall");
        parameters.put(INPUT_MAP.getConstant(), input);
        salesAllocationDAO.executeQuery(parameters);
    }

    public List<String> getSelectedBaseLine(final String sessionId, final String projectionId, final String userId) throws PortalException, SystemException {
        Map<String, Object> parameters = new HashMap<>();
        Map<String, String> input = new HashMap<>();
        List<String> baseLineList = new ArrayList<>();
        input.put(Constant.SESSIONID1, sessionId);
        input.put(Constant.USERID1, userId);
        input.put(Constant.PMSID1, projectionId);
        parameters.put(Constant.INPUT, input);
        parameters.put(INDICATOR.getConstant(), "getSelectedBaseLine");
        List resultList = salesAllocationDAO.executeQuery(parameters);
        StringBuilder builder = new StringBuilder(StringUtils.EMPTY);
        if (resultList != null && !resultList.isEmpty()) {
            for (int loop = 0, limit = resultList.size(); loop < limit; loop++) {
                Object object = resultList.get(loop);
                if (object != null && !NULL.getConstant().equals(String.valueOf(object)) && !StringUtils.isBlank(String.valueOf(object))) {
                    builder.append(String.valueOf(object));
                    if (loop != (limit - 1)) {
                        builder.append(",");
                    }
                }
            }

        }
        return baseLineList;
    }

    public void uncheckAll(int projectionId, String sessionId, String userId) throws PortalException, SystemException {
        Map<String, Object> parameters = new HashMap<>();
        Map<String, Object> input = new HashMap<>();
        parameters.put(INDICATOR.getConstant(), "uncheckAll");
        input.put(Constant.PMSID1, String.valueOf(projectionId));
        input.put(Constant.USERID1, String.valueOf(userId));
        input.put(Constant.SESSIONID1, String.valueOf(sessionId));
        parameters.put(INPUT_MAP.getConstant(), input);
        salesAllocationDAO.executeQuery(parameters);
    }

    public boolean isCheckAll(final String sessionId, final String userId) throws PortalException, SystemException {
        boolean returnValue = false;
        Map<String, Object> parameters = new HashMap<>();
        Map<String, String> input = new HashMap<>();
        input.put(Constant.SESSIONID1, sessionId);
        input.put(Constant.USERID1, userId);
        parameters.put(Constant.INPUT, input);
        parameters.put(INDICATOR.getConstant(), "isCheckAll");
        List resultList = salesAllocationDAO.executeQuery(parameters);
        if (resultList != null && !resultList.isEmpty()) {
            if (resultList.size() == 1) {
                Object obj = (Object) resultList.get(0);
                if (Boolean.valueOf(String.valueOf(obj))) {
                    returnValue = true;
                }
            } else {
                returnValue = false;
            }
        }
        return returnValue;
    }

    public Map<String, Object> prepareForCheckAndUpdate(final String value, final String indicator, final boolean isMassUpdate,
            final String startPeriodValue, final String endPeriodValue, final ProjectionSelectionDTO projectionDTO, final String hierarchyIndicator) {

        String inputValue = StringUtils.EMPTY;
        String updatePeriod;
        Map<String, Object> input = new HashMap<>();
        if (MASS_FIELD_POB.getConstant().equals(indicator)) {

            inputValue = "((" + value + "*cast(AVG(TOTAL.GTS_SUM) as float))/100.00)";
        } else if (MASS_FIELD_CS.getConstant().equals(indicator)) {
            inputValue = value;
        }
        updatePeriod = formPeriod(startPeriodValue, endPeriodValue, isMassUpdate);
        input.put(H_INDICATOR.getConstant(), hierarchyIndicator);
        input.put(Constant.PMSID1, String.valueOf(projectionDTO.getProjectionId()));
        input.put(Constant.FREQUENCY1, projectionDTO.getFrequency());
        input.put("?UPDATEPERIOD?", updatePeriod);
        input.put("?INPUT?", String.valueOf(inputValue));
        input.put(Constant.SESSIONID1, String.valueOf(projectionDTO.getSessionDTO().getSessionId()));
        return input;
    }

    private String formPeriod(final String startPeriodValue, final String endPeriodValue, final boolean isMassUpdate) {
        String period;
        SimpleDateFormat format = new SimpleDateFormat(Constant.DATE_FORMAT);
        if (isMassUpdate) {

            int[] startQuarterAndYear = SalesUtils.getQuarterAndYear(startPeriodValue);
            int[] endQuarterAndYear = SalesUtils.getQuarterAndYear(endPeriodValue);
            List<Date> periodsList = SalesUtils.getStartAndEndDate(startQuarterAndYear[0], endQuarterAndYear[0], startQuarterAndYear[1], endQuarterAndYear[1]);
            period = " AND P2.PERIOD_DATE  BETWEEN CONVERT(DATE, '" + format.format(periodsList.get(0)) + "') AND CONVERT(DATE, '" + format.format(periodsList.get(1)) + "') ";
        } else {

            int[] quarterAndYear = SalesUtils.getQuarterAndYear(startPeriodValue);
            period = " AND P2.QUARTER = " + String.valueOf(quarterAndYear[0]) + " AND P2.YEAR = " + String.valueOf(quarterAndYear[1]) + " ";
        }
        return period;
    }

    public void updateRecord(final Map<String, Object> input, final ProjectionSelectionDTO projectionDTO, final SalesRowDto salesDto) throws PortalException, SystemException {
        Map<String, Object> parameters = new HashMap<>();
        prepareUpdateCheckParameters(parameters, input, projectionDTO, salesDto);
        parameters.put(INDICATOR.getConstant(), "updateRecord");
        salesAllocationDAO.executeQuery(parameters);
    }

    private void prepareUpdateCheckParameters(Map<String, Object> parameters, final Map<String, Object> input, final ProjectionSelectionDTO projectionDTO, final SalesRowDto salesDto) {
        if (!projectionDTO.isIsCustomHierarchy()) {
            if (INDICATOR_LOGIC_CUSTOMER_HIERARCHY.getConstant().equalsIgnoreCase(projectionDTO.getHierarchyIndicator())) {
                prepareGenerateInputs(null, projectionDTO, parameters, input, 0, 0, true, false, true);
            } else if (INDICATOR_LOGIC_PRODUCT_HIERARCHY.getConstant().equalsIgnoreCase(projectionDTO.getHierarchyIndicator())) {
                prepareGenerateInputs(null, projectionDTO, parameters, input, 0, 0, false, false, true);
            }
            input.put(Constant.HNO1, String.valueOf(salesDto.getHierarchyNo()));
            input.put(Constant.LEVELNO1, String.valueOf(salesDto.getLevelNo() == 0 ? Constant.STRING_ONE : salesDto.getLevelNo()));
        } else {
            input.put(Constant.RBSID1, projectionDTO.getCustRelationshipBuilderSid());
            input.put(LEVEL_NO_C.getConstant(), salesDto.getCustomerLevelNo());
            input.put(LEVEL_NO_P.getConstant(), salesDto.getProductLevelNo());
            input.put(H_INDICATOR.getConstant(), salesDto.getHierarchyIndicator());
            input.put(Constant.HNOC1, salesDto.getCustomerHierarchyNo());
            input.put(Constant.HNOP1, salesDto.getProductHierarchyNo());
            prepareCustomGenerateInputs(null, projectionDTO, parameters, input, 0, 0, false, true);
        }
    }

    public boolean checkHundredPercentage(final Map<String, Object> input, final ProjectionSelectionDTO projectionDTO, final SalesRowDto salesDto) throws PortalException, SystemException {
        boolean returnValue = false;
        Map<String, Object> parameters = new HashMap<>();
        prepareUpdateCheckParameters(parameters, input, projectionDTO, salesDto);
        parameters.put(INDICATOR.getConstant(), "checkHundredPercentage");
        String result = String.valueOf(salesAllocationDAO.executeQuery(parameters).get(0));
        if (result != null && !StringUtils.isBlank(result) && !NULL.getConstant().equals(result)) {
            double resultInt = Double.parseDouble(result);
            if (resultInt > 0) {
                returnValue = true;
            }
        }
        return returnValue;
    }

    public Map<String, SalesRowDto> prepareSalesItemMap(ProjectionSelectionDTO projectionSelectionDTO) throws PortalException, SystemException {
        Map<String, SalesRowDto> itemMap = new HashMap<>();
        Map<String, Object> parameters = new HashMap<>();
        Map<String, String> input = new HashMap<>();
        input.put(Constant.PMSID1, String.valueOf(projectionSelectionDTO.getProjectionId()));
        input.put(Constant.RBSID1, String.valueOf(projectionSelectionDTO.getProdRelationshipBuilderSid()));
        parameters.put(INDICATOR.getConstant(), "getNDCLabel");
        parameters.put(INPUT_MAP.getConstant(), input);
        List resultList = salesAllocationDAO.executeQuery(parameters);
        if (resultList != null) {
            SalesRowDto dto = null;
            for (int loop = 0, limit = resultList.size(); loop < limit; loop++) {
                Object[] obj = (Object[]) resultList.get(loop);
                dto = new SalesRowDto();

                dto.setItemRlv(String.valueOf(obj[0]));
                dto.setItemSid(String.valueOf(obj[1]));
                dto.setNdc11(String.valueOf(obj[NumericConstants.TWO]));
                dto.setNdc8(String.valueOf(obj[NumericConstants.THREE]));
                dto.setItemMapHno(String.valueOf(obj[NumericConstants.FOUR]));
                itemMap.put(String.valueOf(obj[NumericConstants.FOUR]), dto);
            }
        }

        return itemMap;
    }

    public void saveCheckRecord(final ProjectionSelectionDTO projectionDTO, final String checkedRecord, final boolean isSaveCheck, final String queryName) throws PortalException {
        try {
            Map<String, Object> parameters = new HashMap<>();
            Map<String, String> input = new HashMap<>();
            Map<String, String> join = new HashMap<>();
            String[] splitArray = null;
            if (!StringUtils.isBlank(checkedRecord)) {
                splitArray = checkedRecord.split(SPLIT_ARROW.getConstant());
            }
            input.put(Constant.PMSID1, String.valueOf(projectionDTO.getProjectionId()));
            if (!projectionDTO.isIsCustomHierarchy()) {

                if (INDICATOR_LOGIC_CUSTOMER_HIERARCHY.getConstant().equalsIgnoreCase(projectionDTO.getHierarchyIndicator())) {
                    // inputs for customer hierarchy
                    input.put(Constant.PHTABLE, Constant.PROJECTION_CUST_HIERARCHY1);
                    input.put(Constant.RBSID1, projectionDTO.getCustRelationshipBuilderSid());
                } else if (INDICATOR_LOGIC_PRODUCT_HIERARCHY.getConstant().equalsIgnoreCase(projectionDTO.getHierarchyIndicator())) {
                    //inputs for product hierarchy
                    input.put(Constant.PHTABLE, Constant.PROJECTION_PROD_HIERARCHY1);
                    input.put(Constant.RBSID1, projectionDTO.getProdRelationshipBuilderSid());
                }
                if (splitArray != null) {
                    input.put(Constant.HNO1, StringUtils.isBlank(String.valueOf(splitArray[1])) ? PERCENT.getConstant() : String.valueOf(splitArray[1]));
                    input.put(Constant.LEVELNO1, String.valueOf(splitArray[0]).equals(DASH) ? PERCENT.getConstant() : String.valueOf(splitArray[0]));
                } else {
                    input.put(Constant.HNO1, PERCENT.getConstant());
                    input.put(Constant.LEVELNO1, PERCENT.getConstant());
                }
            } else {
                //Custom Hierarchy
                input.put("?RBSIDC?", projectionDTO.getCustRelationshipBuilderSid());
                input.put("?RBSIDP?", projectionDTO.getProdRelationshipBuilderSid());
                input.put("?CVSID?", String.valueOf(projectionDTO.getCustomId()));
                input.put(LEVEL_NO_C.getConstant(), splitArray[NumericConstants.THREE]);
                input.put(LEVEL_NO_P.getConstant(), splitArray[NumericConstants.FOUR]);
                input.put(H_INDICATOR.getConstant(), splitArray[NumericConstants.FIVE]);
                input.put(Constant.HNOC1, splitArray[NumericConstants.SIX]);
                input.put(Constant.HNOP1, splitArray[NumericConstants.SEVEN]);
            }
            if (isSaveCheck) {
                if (splitArray != null && String.valueOf(splitArray[NumericConstants.TWO]).equals(Constant.TRUE)) {
                    input.put("?CHECKRECORD?", String.valueOf(1));
                } else {
                    input.put("?CHECKRECORD?", String.valueOf(0));
                }
            }
            input.put(Constant.SESSIONID1, String.valueOf(projectionDTO.getSessionDTO().getSessionId()));
            input.put(Constant.USERID1, String.valueOf(projectionDTO.getUserId()));
            parameters.put(JOIN_MAP.getConstant(), join);
            parameters.put(INPUT_MAP.getConstant(), input);
            parameters.put(INDICATOR.getConstant(), queryName);
            salesAllocationDAO.executeQuery(parameters);
        } catch (Exception ex) {
            LOGGER.error(ex + " in saveCheckRecord");
        }
    }

    public void saveProjectionSelection(Map<String, Object> projectionSelectionDTO, int projectionId) throws PortalException {
        List<ChProjectionSelection> list = null;
        CommonLogic logic = new CommonLogic();
        DynamicQuery query = DynamicQueryFactoryUtil.forClass(ChProjectionSelection.class);
        query.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_MASTER_SID, projectionId));
        query.add(RestrictionsFactoryUtil.eq(Constant.SCREEN_NAME, projectionSelectionDTO.get(Constant.SCREEN_NAME)));
        try {
            list = ChProjectionSelectionLocalServiceUtil.dynamicQuery(query);
            if (list != null && list.isEmpty()) {
                logic.saveSelection(projectionSelectionDTO, projectionId, String.valueOf(projectionSelectionDTO.get(Constant.SCREEN_NAME)), Constant.SAVE, "CH_PROJECTION_SELECTION");
            } else {
                logic.saveSelection(projectionSelectionDTO, projectionId, String.valueOf(projectionSelectionDTO.get(Constant.SCREEN_NAME)), Constant.UPDATE, "CH_PROJECTION_SELECTION");
            }
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * Resets the Check box on View Change.
     *
     * @param projectionSelectionDTO
     * @param value
     */
    public void resetChkbox(final ProjectionSelectionDTO projectionSelectionDTO, boolean value) {
        try {
            int val = value ? 1 : 0;

            if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equalsIgnoreCase(projectionSelectionDTO.getScreenName())
                    || CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equals(projectionSelectionDTO.getScreenName())) {
                updateCheckedRecords(projectionSelectionDTO, null, value, true);
            } else {

                StringBuilder queryBuilder1 = new StringBuilder(StringUtils.EMPTY);
                String tableName = CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equals(projectionSelectionDTO.getScreenName()) ? Constant.ST_M_SALES_PROJECTION_MASTER : Constant.ST_NM_SALES_PROJECTION_MASTER;
                queryBuilder1.append(" UPDATE ").append(tableName).append(" SET CHECK_RECORD=").append(val).append(" \n"
                        + "WHERE PROJECTION_DETAILS_SID in \n"
                        + "( Select PROJECTION_DETAILS_SID FROM PROJECTION_DETAILS WHERE PROJECTION_MASTER_SID=").append(projectionSelectionDTO.getProjectionId());
                if (CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equals(projectionSelectionDTO.getScreenName())) {
                    queryBuilder1.append("  AND USER_ID='").append(projectionSelectionDTO.getUserId()).append("' AND SESSION_ID='").append(projectionSelectionDTO.getSessionId()).append("'");
                }
                queryBuilder1.append(")");
                SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();

                salesProjectionDAO.executeUpdateQuery(QueryUtil.replaceTableNames(queryBuilder1.toString(), projectionSelectionDTO.getSessionDTO().getCurrentTableNames()));
            }
        } catch (PortalException ex) {
            LOGGER.error(ex);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * Resets the Check box on View Change.
     *
     * @param projectionSelectionDTO
     * @param value
     */
    public void resetChkboxForReturns(final ProjectionSelectionDTO projectionSelectionDTO, boolean value) {
        try {
            StringBuilder queryBuilder1 = new StringBuilder(StringUtils.EMPTY);
            queryBuilder1.append(" UPDATE ST_RETURNS_PROJ_MASTER ").append(" SET CHECK_RECORD=").append(value ? 1 : 0).append(" \n"
                    + "WHERE RETURNS_DETAILS_SID in \n"
                    + "(" + projectionSelectionDTO.getSessionDTO().getDetailsSID() + " )");
            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();

            salesProjectionDAO.executeUpdateQuery(QueryUtil.replaceTableNames(queryBuilder1.toString(), projectionSelectionDTO.getSessionDTO().getCurrentTableNames()));
        } catch (PortalException ex) {
            LOGGER.error(ex);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     *
     * @param customId
     * @param levelNo
     * @return
     * @throws PortalException
     * @throws Exception
     */
    public String getCustomViewHierarchyIndicator(int customId, int levelNo) throws PortalException, SystemException {
        String hierarchyIndicator;
        String hierarchyIndicatorQuery = "select HIERARCHY_INDICATOR from dbo.CUSTOM_VIEW_DETAILS where CUSTOM_VIEW_MASTER_SID=" + customId + " and LEVEL_NO=" + levelNo;
        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        List<Object> list = (List<Object>) salesProjectionDAO.executeSelectQuery(hierarchyIndicatorQuery);
        if (list != null && !list.isEmpty()) {
            Object ob = list.get(0);
            hierarchyIndicator = String.valueOf(ob);
        } else {
            hierarchyIndicator = StringUtils.EMPTY;
        }
        return hierarchyIndicator;
    }

    public int saveCheckedRecords(final ProjectionSelectionDTO projectionSelectionDTO, final SalesRowDto salesDTO, boolean isChecked, boolean isCheckAll) throws PortalException, SystemException {

        StringBuilder checkRecordsQuery = new StringBuilder();

        int count = 0;
        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        String masterTable = CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equals(projectionSelectionDTO.getScreenName()) ? Constant.ST_M_SALES_PROJECTION_MASTER : Constant.ST_NM_SALES_PROJECTION_MASTER;
        if (projectionSelectionDTO.isIsCustomHierarchy()) {
            checkRecordsQuery.append(SQlUtil.getQuery("NM_SALES_PROJECTION_CHECK_RECORD_UPDATE"));

            String custHierarchyNo = Constant.PERCENT;
            String prodHierarchyNo = Constant.PERCENT;
            String custLevelNo = Constant.PERCENT;
            String prodLevelNo = Constant.PERCENT;

            if (salesDTO != null && Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(salesDTO.getHierarchyIndicator())) {
                custLevelNo = String.valueOf(salesDTO.getTreeLevelNo());
                custHierarchyNo = salesDTO.getHierarchyNo();
                prodHierarchyNo = salesDTO.getProductHierarchyNo().trim().isEmpty() ? Constant.PERCENT : salesDTO.getProductHierarchyNo();
            } else if (salesDTO != null && Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(salesDTO.getHierarchyIndicator())) {
                prodLevelNo = String.valueOf(salesDTO.getTreeLevelNo());
                custHierarchyNo = salesDTO.getCustomerHierarchyNo();
                prodHierarchyNo = salesDTO.getHierarchyNo();
            }
            Object[] arr = {projectionSelectionDTO.getProjectionId(), projectionSelectionDTO.getCustomId(), custHierarchyNo, prodHierarchyNo,
                custLevelNo, prodLevelNo, projectionSelectionDTO.getUserId(), projectionSelectionDTO.getSessionId(), isChecked ? 1 : 0};
            List input = Arrays.asList(arr);
            for (Object temp : input) {
                checkRecordsQuery.replace(checkRecordsQuery.indexOf("?"), checkRecordsQuery.indexOf("?") + 1, String.valueOf(temp));
            }
            List list = salesProjectionDAO.executeUpdateQuery(checkRecordsQuery.toString());
            if (!list.isEmpty()) {
                count = Integer.parseInt(String.valueOf(list.get(0)));
            }
        } else {
            checkRecordsQuery.append(UPDATE).append(masterTable).append("  SET  ");
            if (isChecked) {
                checkRecordsQuery.append(" CHECK_RECORD=1 ");
            } else {
                checkRecordsQuery.append(" CHECK_RECORD=0 ");
            }
            checkRecordsQuery.append(" where PROJECTION_DETAILS_SID   in (  \n");
            checkRecordsQuery.append("  SELECT PROJECTION_DETAILS_SID FROM  PROJECTION_DETAILS WHERE CCP_DETAILS_SID IN  \n");

            checkRecordsQuery.append("   (SELECT LCCP.CCP_DETAILS_SID from       \n");
            checkRecordsQuery.append("   (SELECT CCPMAP.CCP_DETAILS_SID, HLD.HIERARCHY_NO, HLD.RELATIONSHIP_LEVEL_SID from       \n");
            checkRecordsQuery.append("   (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID       \n");
            checkRecordsQuery.append("   FROM RELATIONSHIP_LEVEL_DEFINITION RLD       \n");
            checkRecordsQuery.append("   JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID      \n");
            checkRecordsQuery.append("   JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID='").append(projectionSelectionDTO.getProjectionId()).append("'      \n");
            checkRecordsQuery.append("   JOIN PROJECTION_MASTER PM  ON PD.PROJECTION_MASTER_SID=PM.PROJECTION_MASTER_SID       \n");
            checkRecordsQuery.append("   WHERE PM.PROJECTION_MASTER_SID='").append(projectionSelectionDTO.getProjectionId()).append("') CCPMAP,       \n");
            checkRecordsQuery.append("   (SELECT RLD1.HIERARCHY_NO, RLD1.RELATIONSHIP_LEVEL_SID      \n");
            checkRecordsQuery.append("   FROM RELATIONSHIP_LEVEL_DEFINITION RLD1       \n");
            checkRecordsQuery.append("    JOIN ").append(getViewTypeQuery(projectionSelectionDTO.getHierarchyIndicator())).append(" PCH     \n");
            checkRecordsQuery.append("   ON PCH.RELATIONSHIP_LEVEL_SID=RLD1.RELATIONSHIP_LEVEL_SID       \n");
            checkRecordsQuery.append("   AND PCH.PROJECTION_MASTER_SID='").append(projectionSelectionDTO.getProjectionId()).append("'       \n");
            if (!isCheckAll) {
                checkRecordsQuery.append(" WHERE  RLD1.HIERARCHY_NO like '").append(salesDTO.getHierarchyNo()).append("%' \n");
            }
            checkRecordsQuery.append(" ) HLD  WHERE CCPMAP.HIERARCHY_NO like HLD.HIERARCHY_NO + '%' ) LCCP      \n");
            checkRecordsQuery.append("   WHERE LCCP.HIERARCHY_NO in       \n");
            checkRecordsQuery.append("   (SELECT RLD2.HIERARCHY_NO       \n");
            checkRecordsQuery.append("   FROM RELATIONSHIP_LEVEL_DEFINITION RLD2      \n");
            checkRecordsQuery.append("    JOIN ").append(getViewTypeQuery(projectionSelectionDTO.getHierarchyIndicator())).append(" PCH2     \n");
            checkRecordsQuery.append("   ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID       \n");
            checkRecordsQuery.append("   AND PCH2.PROJECTION_MASTER_SID='").append(projectionSelectionDTO.getProjectionId()).append("'       \n");
            if (!isCheckAll) {
                checkRecordsQuery.append("   WHERE RLD2.RELATIONSHIP_LEVEL_SID = ").append(salesDTO.getRelationLevelSid()).append("  \n");
            }
            checkRecordsQuery.append(" ) ) ) ");
            if (CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equals(projectionSelectionDTO.getScreenName())) {
                checkRecordsQuery.append(" AND USER_ID = '").append(projectionSelectionDTO.getUserId()).append("' AND SESSION_ID = '").append(projectionSelectionDTO.getSessionId()).append("'  \n");
            }
            List list = salesProjectionDAO.executeUpdateQuery(QueryUtil.replaceTableNames(checkRecordsQuery.toString(), projectionSelectionDTO.getSessionDTO().getCurrentTableNames()));
            if (!list.isEmpty()) {
                count = Integer.parseInt(String.valueOf(list.get(0)));
            }
        }
        return count;
    }

    public int updateCheckedRecords(final ProjectionSelectionDTO projectionSelectionDTO, final SalesRowDto salesDTO, boolean isChecked, boolean isCheckAll) throws PortalException, SystemException {

        String finalQuery;
        if (isCheckAll) {
            String queryName = CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equals(projectionSelectionDTO.getScreenName()) ? "check-all-record-update-mandated" : "check-all-record-update";
            finalQuery = SQlUtil.getQuery(queryName);
            finalQuery = finalQuery.replace("[?CHECK_RECORD]", isChecked ? "1" : "0");
        } else {
            CommonLogic commonLogic = new CommonLogic();

            String hierarchyInserQuery = SQlUtil.getQuery("selected-hierarchy-no");
            hierarchyInserQuery = hierarchyInserQuery.replace(Constant.QUESTION_HIERARCHY_NO_VALUES, "('" + salesDTO.getHierarchyNo() + "')");

            String hiearchyIndicator = salesDTO.getHierarchyIndicator();
            boolean isCustomView = projectionSelectionDTO.isIsCustomHierarchy();
            String customerHierarchyNo;
            String productHierarchyNo;

            if (isCustomView) {
                if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equalsIgnoreCase(salesDTO.getHierarchyIndicator())) {
                    customerHierarchyNo = salesDTO.getHierarchyNo();
                    productHierarchyNo = salesDTO.getProductHierarchyNo();
                } else {
                    customerHierarchyNo = salesDTO.getCustomerHierarchyNo();
                    productHierarchyNo = salesDTO.getHierarchyNo();
                }
            } else {
                customerHierarchyNo = StringUtils.EMPTY;
                productHierarchyNo = StringUtils.EMPTY;
            }

            hierarchyInserQuery = hierarchyInserQuery.replace("[?SELECTED_HIERARCHY_JOIN]", commonLogic.getHierarchyJoinQuery(isCustomView, customerHierarchyNo, productHierarchyNo, hiearchyIndicator));
            String queryName = CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equals(projectionSelectionDTO.getScreenName()) ? "check-record-update-mandated" : "check-record-update";
            String updateQuery = SQlUtil.getQuery(queryName);
            updateQuery = updateQuery.replace("[?CHECK_RECORD]", isChecked ? "1" : "0");

            finalQuery = hierarchyInserQuery + updateQuery;
        }

        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        List list = salesProjectionDAO.executeUpdateQuery(QueryUtil.replaceTableNames(finalQuery, projectionSelectionDTO.getSessionDTO().getCurrentTableNames()));
        int count = 0;
        if (!list.isEmpty()) {
            count = Integer.parseInt(String.valueOf(list.get(0)));
        }
        return count;
    }

    private String getViewTypeQuery(String viewType) {
        String table;
        if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(viewType)) {
            table = Constant.PROJECTION_CUST_HIERARCHY1;
        } else {
            table = Constant.PROJECTION_PROD_HIERARCHY1;
        }
        return table;
    }

    public String getCCPForCustomView(SalesRowDto salesRowDto, int customId, int projectionId) {

        String custHierarchyNo = Constant.PERCENT;
        String prodHierarchyNo = Constant.PERCENT;
        String custLevelNo = Constant.PERCENT;
        String prodLevelNo = Constant.PERCENT;

        if (salesRowDto != null && Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(salesRowDto.getHierarchyIndicator())) {
            custLevelNo = String.valueOf(salesRowDto.getTreeLevelNo());
            custHierarchyNo = salesRowDto.getHierarchyNo();
            prodHierarchyNo = salesRowDto.getProductHierarchyNo().trim().isEmpty() ? Constant.PERCENT : salesRowDto.getProductHierarchyNo();
        } else if (salesRowDto != null && Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(salesRowDto.getHierarchyIndicator())) {
            prodLevelNo = String.valueOf(salesRowDto.getTreeLevelNo());
            custHierarchyNo = salesRowDto.getCustomerHierarchyNo();
            prodHierarchyNo = salesRowDto.getHierarchyNo();
        }

        String customCCPQuery = "(SELECT DISTINCT CCPMAPC.CCP_DETAILS_SID FROM\n"
                + "  (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID FROM RELATIONSHIP_LEVEL_DEFINITION RLD\n"
                + "  JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID\n"
                + "  JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID='" + projectionId + "'\n"
                + "  ) CCPMAPC\n"
                + "  JOIN\n"
                + "  (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID FROM RELATIONSHIP_LEVEL_DEFINITION RLD\n"
                + "  JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID\n"
                + "  JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID='" + projectionId + "'\n"
                + "  ) CCPMAPP ON CCPMAPC.CCP_DETAILS_SID=CCPMAPP.CCP_DETAILS_SID\n"
                + " JOIN  (SELECT RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD\n"
                + "  JOIN dbo.CUSTOM_VIEW_MASTER CVM ON CVD.CUSTOM_VIEW_MASTER_SID='" + customId + "'  AND CVD.LEVEL_NO  like '" + custLevelNo + "'\n"
                + "  JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID\n"
                + "  JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID\n"
                + "  JOIN PROJECTION_CUST_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID='" + projectionId + "'\n"
                + "  WHERE RLD2.HIERARCHY_NO like '" + custHierarchyNo + "') HLDC ON CCPMAPC.HIERARCHY_NO like HLDC.HIERARCHY_NO+'%'\n"
                + " JOIN    (SELECT RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO FROM dbo.CUSTOM_VIEW_DETAILS CVD\n"
                + "  JOIN dbo.CUSTOM_VIEW_MASTER CVM ON\n"
                + "  CVD.CUSTOM_VIEW_MASTER_SID='" + customId + "' AND CVD.LEVEL_NO like '" + prodLevelNo + "'\n"
                + "  JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID\n"
                + "  JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID\n"
                + "  JOIN PROJECTION_PROD_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID='" + projectionId + "'\n"
                + "  WHERE RLD2.HIERARCHY_NO like '" + prodHierarchyNo + "' ) HLDP ON CCPMAPP.HIERARCHY_NO like HLDP.HIERARCHY_NO+'%'\n"
                + "  )";

        return customCCPQuery;

    }

    public void saveEditedRecsReturns(String propertyId, String editedValue, Double incOrDecPer, SalesRowDto salesDTO, ProjectionSelectionDTO projectionSelectionDTO) throws PortalException, SystemException {
        LOGGER.debug("Property Id-> " + propertyId + " EditedValue--> " + editedValue + " incOrDecPer--> " + incOrDecPer);
        Double actualAmount;
        String detailsIdValues[];
        if (StringUtils.isNotBlank(editedValue) && !Constant.NULL.equals(editedValue)) {
            String saveQuery;

            saveQuery = SQlUtil.getQuery("RETURNS_MANNUAL_ENTRY_QUERY");
            editedValue = editedValue.replace(Constant.PERCENT, StringUtils.EMPTY);
            editedValue = editedValue.replace("$", StringUtils.EMPTY);
            editedValue = editedValue.replace(",", StringUtils.EMPTY);
            editedValue = editedValue.trim();
            String returnDetailsId;
            int frequencyDivision = projectionSelectionDTO.getFrequencyDivision();
            int frequencyValue = 0;
            int year = 0;
            int quater = 0;
            String hierarchyNo = salesDTO.getHierarchyNo();
            String keyarr[] = propertyId.split("-");
            detailsIdValues = salesDTO.getReturnDetailsSid().split("\\s*,\\s*");
            String keyarray[] = propertyId.split("-");
            if (frequencyDivision == 1) {
                year = Integer.valueOf(keyarr[0]);
                keyarray[1] = StringUtils.EMPTY;
                frequencyValue = NumericConstants.TWELVE;
            } else if (frequencyDivision == NumericConstants.FOUR) {
                keyarr[0] = (keyarr[0]).replace('q', ' ');
                frequencyValue = NumericConstants.THREE;
            } else if (frequencyDivision == NumericConstants.TWO) {
                keyarr[0] = (keyarr[0]).replace('s', ' ');
                frequencyValue = NumericConstants.SIX;
            } else if (frequencyDivision == NumericConstants.TWELVE) {
                keyarr[0] = (keyarr[0]).replace(keyarr[0], String.valueOf(getMonthNo(keyarr[0])));
                frequencyValue = 1;
            }

            if (frequencyDivision != 1) {
                year = Integer.parseInt(keyarr[1]);
                quater = Integer.parseInt(keyarr[0].trim());
            }
            returnDetailsId = salesDTO.getReturnDetailsSid();

            saveQuery = saveQuery.replace(Constant.YEAR1_AT, StringUtils.EMPTY + year);
            saveQuery = saveQuery.replace(Constant.PERIOD1_AT, StringUtils.EMPTY + quater);
            saveQuery = addFrequencyInQuery(frequencyDivision, quater, saveQuery);
            String amountValue;
            if (propertyId.endsWith("ProjectedReturnAmount")) {
                saveQuery = saveQuery.replace(Constant.RETURNS_DETAILS_SID_AT, returnDetailsId);
                if (!incOrDecPer.isInfinite() && !incOrDecPer.isNaN()) {
                    actualAmount = incOrDecPer / 100;
                    amountValue = "PROJECTED_RETURN_AMOUNT+(PROJECTED_RETURN_AMOUNT*" + actualAmount + ")";
                    saveQuery = saveQuery.replace(Constant.USER_ENTERED_VALUE, StringUtils.EMPTY + amountValue);
                } else {
                    actualAmount = Double.valueOf(editedValue) / (detailsIdValues.length);
                    amountValue = String.valueOf(actualAmount / frequencyValue);
                }
                saveQuery = saveQuery.replace(Constant.USER_ENTERED_VALUE, StringUtils.EMPTY + amountValue);
                saveQuery = saveQuery.replace(Constant.USER_ENTERED_PROPERTY_VALUE, Constant.PROJECTED_RETURN_AMOUNT);
                salesAllocationDAO.executeUpdateQuery(QueryUtil.replaceTableNames(saveQuery, projectionSelectionDTO.getSessionDTO().getCurrentTableNames()));
            } else if (propertyId.endsWith(Constant.PROJECTEDRPU)) {
                saveQuery = saveQuery.replace(Constant.USER_ENTERED_PROPERTY_VALUE, "PROJECTED_RPU");
                if (salesDTO.getReturnDetailsSid().split(",").length == 1) {
                    saveQuery = saveQuery.replace(Constant.RETURNS_DETAILS_SID_AT, returnDetailsId);
                    actualAmount = Double.valueOf(editedValue);
                    saveQuery = saveQuery.replace(Constant.USER_ENTERED_VALUE, StringUtils.EMPTY + actualAmount);
                    salesAllocationDAO.executeUpdateQuery(QueryUtil.replaceTableNames(saveQuery, projectionSelectionDTO.getSessionDTO().getCurrentTableNames()));
                } else {
                    List<Map> mapList = getActiveExFactorySalesAndUnits(projectionSelectionDTO, year, quater);
                    Map<String, Double> salesAmount = mapList.get(0);
                    Map<String, Double> unitsMap = mapList.get(1);

                    String bulkQuery = calculationLogic(projectionSelectionDTO, hierarchyNo, editedValue, saveQuery, salesAmount, unitsMap);
                    salesAllocationDAO.executeUpdateQuery(QueryUtil.replaceTableNames(bulkQuery, projectionSelectionDTO.getSessionDTO().getCurrentTableNames()));
                }
            } else if (propertyId.endsWith("ProjectedReturn%")) {
                saveQuery = saveQuery.replace(Constant.USER_ENTERED_PROPERTY_VALUE, "PROJECTED_RETURN_PERCENT");
                if (salesDTO.getReturnDetailsSid().split(",").length == 1) {
                    saveQuery = saveQuery.replace(Constant.RETURNS_DETAILS_SID_AT, returnDetailsId);
                    actualAmount = Double.valueOf(editedValue);
                    saveQuery = saveQuery.replace(Constant.USER_ENTERED_VALUE, StringUtils.EMPTY + actualAmount);
                    salesAllocationDAO.executeUpdateQuery(QueryUtil.replaceTableNames(saveQuery, projectionSelectionDTO.getSessionDTO().getCurrentTableNames()));
                } else {
                    List<Map> mapList = getActiveExFactorySalesAndUnits(projectionSelectionDTO, year, quater);
                    Map<String, Double> salesAmount = mapList.get(0);
                    String bulkQuery = calculationLogic(projectionSelectionDTO, hierarchyNo, editedValue, saveQuery, salesAmount, salesAmount);
                    salesAllocationDAO.executeUpdateQuery(QueryUtil.replaceTableNames(bulkQuery, projectionSelectionDTO.getSessionDTO().getCurrentTableNames()));
                }
            } else if (propertyId.endsWith("GrowthRate")) {
                saveQuery = saveQuery.replace(Constant.USER_ENTERED_PROPERTY_VALUE, "GROWTH_RATE");
                saveQuery = saveQuery.replace(Constant.RETURNS_DETAILS_SID_AT, returnDetailsId);
                actualAmount = Double.valueOf(editedValue);
                saveQuery = saveQuery.replace(Constant.USER_ENTERED_VALUE, StringUtils.EMPTY + actualAmount);
                salesAllocationDAO.executeUpdateQuery(QueryUtil.replaceTableNames(saveQuery, projectionSelectionDTO.getSessionDTO().getCurrentTableNames()));
            }
            String refreshedPeriods = keyarray[0] + " " + keyarray[1];
            char first = Character.toUpperCase(refreshedPeriods.charAt(0));
            String refreshedPeriod = first + refreshedPeriods.substring(1);
            callRefreshProcedure(projectionSelectionDTO.getProjectionId(), salesDTO.getReturnDetailsSid(), refreshedPeriod, StringUtils.EMPTY + projectionSelectionDTO.getUserId(), StringUtils.EMPTY + projectionSelectionDTO.getSessionDTO().getSessionId(), false);
        }
    }

    public void saveEditedRecs(String propertyId, String editedValue, Double incOrDecPer, String changedValue, SalesRowDto salesDTO, ProjectionSelectionDTO projectionSelectionDTO, boolean checkAll, boolean isManualEntry) throws PortalException, SystemException {

        if (StringUtils.isNotBlank(editedValue) && !Constant.NULL.equals(editedValue)) {

            StringBuilder saveQuery = new StringBuilder(StringUtils.EMPTY);
            StringBuilder updateQuery = new StringBuilder(StringUtils.EMPTY);
            editedValue = editedValue.replace(Constant.PERCENT, StringUtils.EMPTY);
            editedValue = editedValue.replace("$", StringUtils.EMPTY);
            editedValue = editedValue.replace(",", StringUtils.EMPTY);
            editedValue = editedValue.trim();
            int frequencyDivision = projectionSelectionDTO.getFrequencyDivision();
            int year = 0;
            int quator = 0;

            BigDecimal value = new BigDecimal(editedValue);
            String hierarchyNo = salesDTO.getHierarchyNo();
            int rowcount = MSalesProjection.rowCountMap.get(hierarchyNo);
            String keyarr[] = propertyId.split("-");
            if (frequencyDivision == 1) {
                year = Integer.valueOf(keyarr[0]);
                rowcount = rowcount * NumericConstants.TWELVE;
            } else if (frequencyDivision == NumericConstants.FOUR) {
                keyarr[0] = (keyarr[0]).replace('q', ' ');
                rowcount = rowcount * NumericConstants.THREE;
            } else if (frequencyDivision == NumericConstants.TWO) {
                keyarr[0] = (keyarr[0]).replace('s', ' ');
                rowcount = rowcount * NumericConstants.SIX;
            } else if (frequencyDivision == NumericConstants.TWELVE) {
                keyarr[0] = (keyarr[0]).replace(keyarr[0], String.valueOf(getMonthNo(keyarr[0])));
            }
            String column = frequencyDivision == 1 ? keyarr[1] : keyarr[NumericConstants.TWO];
            if (frequencyDivision != 1) {
                year = Integer.parseInt(keyarr[1]);
                quator = Integer.parseInt(keyarr[0].trim());
            }
            BigDecimal finalvalue;
            String table = CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equals(projectionSelectionDTO.getScreenName()) ? Constant.ST_M_SALES_PROJECTION : Constant.ST_NM_SALES_PROJECTION;
            switch (column) {
                case "AccountGrowth":
                    saveQuery.append(UPDATE).append(table).append(" SET ACCOUNT_GROWTH='").append(value.toString()).append("' ");
                    break;
                case "ProductGrowth":
                    saveQuery.append(UPDATE).append(table).append(" SET PRODUCT_GROWTH='").append(value.toString()).append("' ");
                    break;
                case "ProjectedSales":
                    if (!incOrDecPer.isInfinite() && !incOrDecPer.isNaN()) {
                        finalvalue = new BigDecimal(incOrDecPer).divide(new BigDecimal(100), MathContext.DECIMAL64);
                        saveQuery.append(UPDATE).append(table).append(" SET PROJECTION_SALES=PROJECTION_SALES+(PROJECTION_SALES*").append(finalvalue.toString()).append(")");
                    } else {
                        finalvalue = value.divide(new BigDecimal(rowcount), MathContext.DECIMAL64);
                        saveQuery.append(UPDATE).append(table).append(" SET PROJECTION_SALES='").append(finalvalue.toString()).append("'");
                    }
                    break;
                case Constant.PROJECTED_UNITS1:
                    if (!incOrDecPer.isInfinite() && !incOrDecPer.isNaN()) {
                        saveQuery.append("DECLARE @PROJECTION_UNITS NUMERIC(22, 6)\n"
                                + "SET @PROJECTION_UNITS=(SELECT Sum(PROJECTION_UNITS)\n"
                                + "FROM   " + table + " " + " @Replace_Value" + "   )");
                        saveQuery.append(UPDATE).append(table).append(" SET PROJECTION_UNITS=PROJECTION_UNITS + ( PROJECTION_UNITS * ( ( ( ( " + value.toString() + " - @PROJECTION_UNITS ) / NULLIF(@PROJECTION_UNITS, 0) ) * 100 ) / 100 ) )");
                    } else {
                        finalvalue = value.divide(new BigDecimal(rowcount), MathContext.DECIMAL64);
                        saveQuery.append(UPDATE).append(table).append(" SET PROJECTION_UNITS='").append(finalvalue.toString()).append("' ");
                    }
                    break;
            }
            updateQuery.append("  where PROJECTION_DETAILS_SID  ");
            updateQuery.append("  in ( ");
            updateQuery.append("  SELECT PROJECTION_DETAILS_SID FROM  PROJECTION_DETAILS WHERE CCP_DETAILS_SID IN  ");

            if (projectionSelectionDTO.isIsCustomHierarchy()) {
                updateQuery.append(getCCPForCustomView(salesDTO, projectionSelectionDTO.getCustomId(), projectionSelectionDTO.getProjectionId()));
            } else {

                updateQuery.append("   (SELECT LCCP.CCP_DETAILS_SID from       ");
                updateQuery.append("   (SELECT CCPMAP.CCP_DETAILS_SID, HLD.HIERARCHY_NO, HLD.RELATIONSHIP_LEVEL_SID from       ");
                updateQuery.append("   (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID       ");
                updateQuery.append("   FROM RELATIONSHIP_LEVEL_DEFINITION RLD       ");
                updateQuery.append("   JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID      ");
                updateQuery.append("   JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID='").append(projectionSelectionDTO.getProjectionId()).append("'      ");
                if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projectionSelectionDTO.getHierarchyIndicator())) {
                    updateQuery.append("    JOIN PROJECTION_CUST_HIERARCHY PCH1     ");
                } else {
                    updateQuery.append("    JOIN PROJECTION_PROD_HIERARCHY PCH1     ");
                }
                updateQuery.append("  ON  RLD.RELATIONSHIP_LEVEL_SID=PCH1.RELATIONSHIP_LEVEL_SID  ");
                updateQuery.append("  JOIN PROJECTION_MASTER PM  ON PCH1.PROJECTION_MASTER_SID=PM.PROJECTION_MASTER_SID       ");
                updateQuery.append("  WHERE PM.PROJECTION_MASTER_SID='").append(projectionSelectionDTO.getProjectionId()).append("') CCPMAP,       ");
                updateQuery.append("  (SELECT RLD1.HIERARCHY_NO, RLD1.RELATIONSHIP_LEVEL_SID      ");
                updateQuery.append("  FROM RELATIONSHIP_LEVEL_DEFINITION RLD1       ");
                if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projectionSelectionDTO.getHierarchyIndicator())) {
                    updateQuery.append("    JOIN PROJECTION_CUST_HIERARCHY PCH     ");
                } else {
                    updateQuery.append("    JOIN PROJECTION_PROD_HIERARCHY PCH     ");
                }
                updateQuery.append("   ON PCH.RELATIONSHIP_LEVEL_SID=RLD1.RELATIONSHIP_LEVEL_SID       ");
                updateQuery.append("   AND PCH.PROJECTION_MASTER_SID='").append(projectionSelectionDTO.getProjectionId()).append("'       ");
                updateQuery.append("    WHERE RLD1.HIERARCHY_NO like '").append(hierarchyNo).append("' ) HLD      ");
                updateQuery.append("   WHERE CCPMAP.HIERARCHY_NO like concat(HLD.HIERARCHY_NO,'%') ) LCCP      ");
                updateQuery.append("   WHERE LCCP.HIERARCHY_NO in       ");
                updateQuery.append("   (SELECT RLD2.HIERARCHY_NO       ");
                updateQuery.append("   FROM RELATIONSHIP_LEVEL_DEFINITION RLD2      ");
                if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(projectionSelectionDTO.getHierarchyIndicator())) {
                    updateQuery.append("    JOIN PROJECTION_CUST_HIERARCHY PCH2     ");
                } else {
                    updateQuery.append("    JOIN PROJECTION_PROD_HIERARCHY PCH2     ");
                }
                updateQuery.append("   ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID       ");
                updateQuery.append("   AND PCH2.PROJECTION_MASTER_SID='").append(projectionSelectionDTO.getProjectionId()).append("'       ");
                updateQuery.append("   WHERE RLD2.HIERARCHY_NO = '").append(hierarchyNo).append("')       ");
                updateQuery.append(" ) AND PROJECTION_MASTER_SID = ").append(projectionSelectionDTO.getProjectionId()).append(" ");
            }
            updateQuery.append(") ");
            if (frequencyDivision == 1) {
                updateQuery.append("AND PERIOD_SID in(SELECT PERIOD_SID FROM PERIOD where YEAR ='").append(year).append("') ");
            } else if (frequencyDivision == NumericConstants.FOUR) {
                updateQuery.append("AND PERIOD_SID in(SELECT PERIOD_SID FROM PERIOD where YEAR ='").append(year).append("'  and QUARTER ='").append(quator).append("' ) ");
            } else if (frequencyDivision == NumericConstants.TWO) {
                updateQuery.append("AND PERIOD_SID  in(SELECT PERIOD_SID FROM PERIOD where YEAR ='").append(year).append("'  and SEMI_ANNUAL ='").append(quator).append("' ) ");
            } else if (frequencyDivision == NumericConstants.TWELVE) {
                updateQuery.append("AND  PERIOD_SID in(SELECT PERIOD_SID FROM PERIOD where YEAR ='").append(year).append("'  and MONTH ='").append(quator).append("' ) ");
            }

            if (CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equals(projectionSelectionDTO.getScreenName())) {
                updateQuery.append(" AND USER_ID = '").append(projectionSelectionDTO.getUserId()).append("'  AND SESSION_ID ='").append(projectionSelectionDTO.getSessionId()).append("' ");
            }
            saveQuery.append(updateQuery);
            String finQuery = saveQuery.toString();
            if (Constant.PROJECTED_UNITS1.equalsIgnoreCase(column)) {
                finQuery = finQuery.replace("@Replace_Value", updateQuery.toString());
            }
            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
            salesProjectionDAO.executeUpdateQuery(QueryUtil.replaceTableNames(finQuery, projectionSelectionDTO.getSessionDTO().getCurrentTableNames()));
        }
        if (isManualEntry) {
            saveRecordsForManualEntry(changedValue, projectionSelectionDTO, salesDTO, checkAll);
        }

    }

    public void saveRecords(String propertyId, String editedValue, Double incOrDecPer, String changedValue, SalesRowDto salesDTO, ProjectionSelectionDTO projectionSelectionDTO, boolean checkAll, boolean isManualEntry) throws PortalException, SystemException {

        if (StringUtils.isNotBlank(editedValue) && !Constant.NULL.equals(editedValue)) {

            StringBuilder updateLine = new StringBuilder(StringUtils.EMPTY);

            editedValue = editedValue.replace(Constant.PERCENT, StringUtils.EMPTY);
            editedValue = editedValue.replace("$", StringUtils.EMPTY);
            editedValue = editedValue.replace(",", StringUtils.EMPTY);
            editedValue = editedValue.trim();

            int frequencyDivision = projectionSelectionDTO.getFrequencyDivision();
            int year = 0;
            int frequency = 0;

            BigDecimal value = new BigDecimal(editedValue);
            String hierarchyNo = salesDTO.getHierarchyNo();
            int rowcount = MSalesProjection.rowCountMap.get(hierarchyNo);
            String keyarr[] = propertyId.split("-");
            if (frequencyDivision == 1) {
                year = Integer.valueOf(keyarr[0]);
                rowcount = rowcount * NumericConstants.TWELVE;
            } else if (frequencyDivision == NumericConstants.FOUR) {
                keyarr[0] = (keyarr[0]).replace('q', ' ');
                rowcount = rowcount * NumericConstants.THREE;
            } else if (frequencyDivision == NumericConstants.TWO) {
                keyarr[0] = (keyarr[0]).replace('s', ' ');
                rowcount = rowcount * NumericConstants.SIX;
            } else if (frequencyDivision == NumericConstants.TWELVE) {
                keyarr[0] = (keyarr[0]).replace(keyarr[0], String.valueOf(getMonthNo(keyarr[0])));
            }

            String column = frequencyDivision == 1 ? keyarr[1] : keyarr[NumericConstants.TWO];
            if (frequencyDivision != 1) {
                year = Integer.parseInt(keyarr[1]);
                frequency = Integer.parseInt(keyarr[0].trim());
            }

            BigDecimal finalvalue;

            switch (column) {
                case "AccountGrowth":
                    updateLine.append(" ACCOUNT_GROWTH='").append(value.toString()).append("' ");
                    break;
                case "ProductGrowth":
                    updateLine.append(" PRODUCT_GROWTH='").append(value.toString()).append("' ");
                    break;
                case "ProjectedSales":
                    if (!incOrDecPer.isInfinite() && !incOrDecPer.isNaN()) {
                        finalvalue = new BigDecimal(incOrDecPer).divide(new BigDecimal(100), MathContext.DECIMAL64);
                        updateLine.append(" PROJECTION_SALES = PROJECTION_SALES+(PROJECTION_SALES*").append(finalvalue.toString()).append(")");
                    } else {
                        finalvalue = value.divide(new BigDecimal(rowcount), MathContext.DECIMAL64);
                        updateLine.append(" PROJECTION_SALES=").append(finalvalue.toString()).append("");
                    }
                    break;
                case Constant.PROJECTED_UNITS1:
                    if (!incOrDecPer.isInfinite() && !incOrDecPer.isNaN()) {
                        finalvalue = new BigDecimal(incOrDecPer).divide(new BigDecimal(100), MathContext.DECIMAL64);
                        if (CommonUtil.isValueEligibleForLoading()) {
                            updateLine.append(" PROJECTION_UNITS= (PROJECTION_UNITS + ( PROJECTION_UNITS * ").append(finalvalue.toString()).append("))/NULLIF(UOM_VALUE,0) ");
                        } else {
                            updateLine.append(" PROJECTION_UNITS= PROJECTION_UNITS + ( PROJECTION_UNITS * ").append(finalvalue.toString()).append(")");
                        }
                    } else {
                        finalvalue = value.divide(new BigDecimal(rowcount), MathContext.DECIMAL64);
                        if (CommonUtil.isValueEligibleForLoading()) {
                            updateLine.append(" PROJECTION_UNITS=").append(finalvalue.toString()).append("/NULLIF(UOM_VALUE,0) ").append(" ");
                        } else {
                            updateLine.append(" PROJECTION_UNITS=").append(finalvalue.toString()).append(" ");
                        }

                    }
                    break;
            }

            StringBuilder periodRestriction = new StringBuilder();
            if (frequencyDivision == 1) {
                periodRestriction.append(Constant.WHERE_YEAR_EQUAL).append(year).append(" ");
            } else if (frequencyDivision == NumericConstants.FOUR) {
                periodRestriction.append(Constant.WHERE_YEAR_EQUAL).append(year).append(" AND QUARTER = ").append(frequency);
            } else if (frequencyDivision == NumericConstants.TWO) {
                periodRestriction.append(Constant.WHERE_YEAR_EQUAL).append(year).append(" AND SEMI_ANNUAL = ").append(frequency);
            } else if (frequencyDivision == NumericConstants.TWELVE) {
                periodRestriction.append(Constant.WHERE_YEAR_EQUAL).append(year).append(" AND MONTH = ").append(frequency);
            }

            CommonLogic commonLogic = new CommonLogic();
            String hierarchyInserQuery =  projectionSelectionDTO.isIsCustomHierarchy() ? SQlUtil.getQuery("selected-hierarchy-no-custom-view") : SQlUtil.getQuery("selected-hierarchy-no");
            hierarchyInserQuery = hierarchyInserQuery.replace(Constant.QUESTION_HIERARCHY_NO_VALUES, "('" + salesDTO.getHierarchyNo() + "')");
            hierarchyInserQuery = hierarchyInserQuery.replace(Constant.QUESTION_CUSTOMERPARENT,salesDTO.getSecHierarchyNo()); 
            hierarchyInserQuery = hierarchyInserQuery.replace(Constant.QUESTION_PRODUCTPARENT, salesDTO.getHierarchyNo()); 
            
            String hiearchyIndicator = salesDTO.getHierarchyIndicator();
            boolean isCustomView = projectionSelectionDTO.isIsCustomHierarchy();
            String customerHierarchyNo;
            String productHierarchyNo;

            if (isCustomView) {
                if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equalsIgnoreCase(salesDTO.getHierarchyIndicator())) {
                    customerHierarchyNo = salesDTO.getHierarchyNo();
                    productHierarchyNo = salesDTO.getProductHierarchyNo();
                } else {
                    customerHierarchyNo = salesDTO.getCustomerHierarchyNo();
                    productHierarchyNo = salesDTO.getHierarchyNo();
                }
            } else {
                customerHierarchyNo = StringUtils.EMPTY;
                productHierarchyNo = StringUtils.EMPTY;
            }

            hierarchyInserQuery = hierarchyInserQuery.replace("[?SELECTED_HIERARCHY_JOIN]", commonLogic.getHierarchyJoinQuery(isCustomView, customerHierarchyNo, productHierarchyNo, hiearchyIndicator));

            String updateQuery = SQlUtil.getQuery("line-level-update");
            updateQuery = updateQuery.replace("[?UPDATE_LINE]", updateLine.toString());
            String uomJoin=" JOIN CCP_DETAILS CCP ON CCP.CCP_DETAILS_SID=NMSP.CCP_DETAILS_SID  LEFT JOIN ST_ITEM_UOM_DETAILS UOM ON UOM.ITEM_MASTER_SID=CCP.ITEM_MASTER_SID AND UOM_CODE='"+projectionSelectionDTO.getUomCode()+"'";
            if (Constant.PROJECTED_UNITS1.equals(column) && CommonUtil.isValueEligibleForLoading()) {
                updateQuery = updateQuery.replace("@CCP_DETAIL_JOIN", uomJoin);
            } else {
                updateQuery = updateQuery.replace("@CCP_DETAIL_JOIN", StringUtils.EMPTY);
            }
            updateQuery = updateQuery.replace("[?PERIOD_RESTRICTION]", periodRestriction.toString());

            String finalQuery = hierarchyInserQuery + updateQuery;
            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
            salesProjectionDAO.executeUpdateQuery(QueryUtil.replaceTableNames(finalQuery, projectionSelectionDTO.getSessionDTO().getCurrentTableNames()));
        }
        if (isManualEntry) {
            saveRecordsForManualEntry(changedValue, projectionSelectionDTO, salesDTO, checkAll);
        }

    }

    /**
     *
     * @param changedProperty
     * @param projectionSelectionDTO
     * @param salesDTO
     * @param checkAll
     * @throws Exception
     */
    public void saveRecordsForManualEntry(String changedProperty, final ProjectionSelectionDTO projectionSelectionDTO, final SalesRowDto salesDTO, boolean checkAll) throws PortalException, SystemException {

        if (StringUtils.isNotBlank(changedProperty) && !Constant.NULL.equals(changedProperty)) {
            if (CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equals(projectionSelectionDTO.getScreenName())) {
                saveCheckedRecords(projectionSelectionDTO, salesDTO, true, checkAll);
            } else {
                updateCheckedRecords(projectionSelectionDTO, salesDTO, true, checkAll);
            }
            callManualEntry(projectionSelectionDTO, changedProperty.contains(Constant.SALES_SMALL) ? Constant.SALES_SMALL : Constant.UNITS_SMALL);
            if (!(Boolean) salesDTO.getPropertyValue(Constant.CHECK)) {
                if (CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equals(projectionSelectionDTO.getScreenName())) {
                    saveCheckedRecords(projectionSelectionDTO, salesDTO, false, checkAll);
                } else {
                    updateCheckedRecords(projectionSelectionDTO, salesDTO, false, checkAll);
                }
            }
        }
    }

    public boolean callManualEntry(final ProjectionSelectionDTO projectionSelectionDTO, String changedProperty) {
        boolean status = false;
        final DataSourceConnection dataSourceConnection = DataSourceConnection.getInstance();
        Connection connection = null;
        CallableStatement statement = null;
        SessionDTO session = projectionSelectionDTO.getSessionDTO();
        try {
            connection = dataSourceConnection.getConnection();
            LOGGER.debug("Entering callManualEntryProcedure  ::::");
            if (connection != null) {
                if (CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equals(projectionSelectionDTO.getScreenName())) {
                    statement = connection.prepareCall("{call PRC_SALES_PROJ_MANUAL_ENTRY (?,?,?,?)}");
                } else {
                    statement = connection.prepareCall("{call PRC_SALES_PROJ_MANUAL_ENTRY_TEMP (?,?,?,?,?)}");
                }
                LOGGER.debug("PRC_SALES_PROJ_MANUAL_ENTRY_TEMP");
                LOGGER.debug("1 " + session.getProjectionId());
                LOGGER.debug("2 " + session.getUserId());
                LOGGER.debug("3 " + session.getSessionId());
                LOGGER.debug("4 " + changedProperty);
                LOGGER.debug(projectionSelectionDTO.getSessionDTO().getSalesInclusion().equals(ALL) ? null : projectionSelectionDTO.getSessionDTO().getSalesInclusion());

                statement.setObject(1, session.getProjectionId()); //  @PROJECTION_SID
                statement.setObject(NumericConstants.TWO, Integer.parseInt(session.getUserId())); //  @USER_ID
                statement.setObject(NumericConstants.THREE, session.getSessionId()); //  @SESSION_ID
                statement.setObject(NumericConstants.FOUR, changedProperty);
                if (!CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equals(projectionSelectionDTO.getScreenName())) {
                    statement.setObject(NumericConstants.FIVE, projectionSelectionDTO.getSessionDTO().getSalesInclusion().equals(ALL) ? null : projectionSelectionDTO.getSessionDTO().getSalesInclusion());
                }
                status = statement.execute();
            }
            LOGGER.debug("Ending callManualEntryProcedure return  staus ::::" + status);
        } catch (Exception ex) {
            LOGGER.error(ex);
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
        return status;
    }

    /**
     *
     * @param projectionSelectionDTO
     * @param startYear
     * @param endYear
     * @param startQuarter
     * @param endQuarter
     * @param value
     * @param growth
     * @throws PortalException
     * @throws Exception
     */
 public void saveOnMassUpdate(final ProjectionSelectionDTO projectionSelectionDTO, Map<String,Object> inputParameters) throws PortalException, SystemException {
        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        String growth = String.valueOf(inputParameters.get("updateVariable"));
        int startQuarter = Integer.parseInt(String.valueOf(inputParameters.get("startQuarter")));
        int endQuarter = Integer.parseInt(String.valueOf(inputParameters.get("endQuarter")));
        int startYear = Integer.parseInt(String.valueOf(inputParameters.get("startYear")));
        int endYear = Integer.parseInt(String.valueOf(inputParameters.get("endYear")));
        String value = String.valueOf(inputParameters.get("enteredValue"));
        
        
        if (growth.equals(Constant.SALES_SMALL) || growth.equals(Constant.UNIT_VOLUME)) {
            int frequency = projectionSelectionDTO.getFrequencyDivision();
            String semiOrAnnualFreq = frequency == 2 ? "S" : "A";
            String monthOrQuarter = frequency == 4 ? "Q" : semiOrAnnualFreq;
            String freq = (frequency == 12 ? "M" : monthOrQuarter);
           
            List<Object> input = new ArrayList<>();
            input.add(freq+startQuarter +" "+ startYear);
            input.add(freq+endQuarter +" "+ endYear);
         
            input.add(value);
            
            input.add(freq);

            
            input.add(growth.equals(Constant.SALES_SMALL) ? "PROJECTION_SALES" : "PROJECTION_UNITS");
            salesAndUnitsMassUpdate(projectionSelectionDTO, salesProjectionDAO, input);
      
            return;
        }

        String projectionTable = CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equals(projectionSelectionDTO.getScreenName()) ? Constant.ST_M_SALES_PROJECTION : Constant.ST_NM_SALES_PROJECTION;
        String masterTable = CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equals(projectionSelectionDTO.getScreenName()) ? Constant.ST_M_SALES_PROJECTION_MASTER : Constant.ST_NM_SALES_PROJECTION_MASTER;

        String updateQuery = SQlUtil.getQuery("mass-update");

        //Need to remove once the dynamic changes is done in Government
        if (CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equals(projectionSelectionDTO.getScreenName())) {
            updateQuery = updateQuery.replace(Constant.USERID_ADD, "SP.USER_ID = @USER_ID AND SP.SESSION_ID = @SESSION_ID AND SPM.USER_ID = @USER_ID AND SPM.SESSION_ID = @SESSION_ID AND ");
            updateQuery = updateQuery.replace(Constant.JOIN_CONDITION_AT, "ON SPM.PROJECTION_DETAILS_SID = SP.PROJECTION_DETAILS_SID");
        } else {
            updateQuery = updateQuery.replace(Constant.USERID_ADD, StringUtils.EMPTY);
            updateQuery = updateQuery.replace(Constant.JOIN_CONDITION_AT, "ON SPM.CCP_DETAILS_SID = SP.CCP_DETAILS_SID");
        }
        updateQuery = updateQuery.replace(Constant.USER_ID1_AT, String.valueOf(projectionSelectionDTO.getUserId()));
        updateQuery = updateQuery.replace(Constant.SESSION_ID1_AT, String.valueOf(projectionSelectionDTO.getSessionId()));
        updateQuery = updateQuery.replace("@START_YEAR", String.valueOf(startYear));
        updateQuery = updateQuery.replace("@END_YEAR", String.valueOf(endYear));
        updateQuery = updateQuery.replace(Constant.PROJECTION_TABLE, projectionTable);
        updateQuery = updateQuery.replace(Constant.MASTER_TABLE, masterTable);

        if (growth.equals(Constant.PRODUCT_GROWTH)) {
            updateQuery = updateQuery.replace(Constant.COLUMN_NAME, "PRODUCT_GROWTH");
            updateQuery = updateQuery.replace(Constant.AT_TABLE_NAME, "SP");
            updateQuery = updateQuery.replace(Constant.VALUE1_AT, String.valueOf(value));
        } else if (growth.equals(Constant.ACCOUNT_GROWTH)) {
            updateQuery = updateQuery.replace(Constant.COLUMN_NAME, "ACCOUNT_GROWTH");
            updateQuery = updateQuery.replace(Constant.AT_TABLE_NAME, "SP");
            updateQuery = updateQuery.replace(Constant.VALUE1_AT, String.valueOf(value));
        } else if (growth.equals(Constant.GROUPFCAPS)) {
            updateQuery = updateQuery.replace(Constant.COLUMN_NAME, "USER_GROUP");
            updateQuery = updateQuery.replace(Constant.AT_TABLE_NAME, "SPM");
            StringBuilder finalValue = new StringBuilder();
            finalValue.append("'").append(String.valueOf(value)).append("'");
            updateQuery = updateQuery.replace(Constant.VALUE1_AT, finalValue.toString());
        }
        switch (projectionSelectionDTO.getFrequencyDivision()) {
            case 1:
                updateQuery = updateQuery.replace(Constant.START_FREQUENCY_AT, Constant.AND_MONTH_ZERO);
                updateQuery = updateQuery.replace(Constant.END_FREQUENCY, Constant.AND_MONTH_ZERO);
                break;
            case NumericConstants.TWELVE:
                updateQuery = updateQuery.replace(Constant.START_FREQUENCY_AT, " AND MONTH < " + startQuarter);
                updateQuery = updateQuery.replace(Constant.END_FREQUENCY, " AND MONTH > " + endQuarter);
                break;
            case NumericConstants.FOUR:
                updateQuery = updateQuery.replace(Constant.START_FREQUENCY_AT, " AND QUARTER < " + startQuarter);
                updateQuery = updateQuery.replace(Constant.END_FREQUENCY, " AND QUARTER > " + endQuarter);
                break;
            case NumericConstants.TWO:
                updateQuery = updateQuery.replace(Constant.START_FREQUENCY_AT, " AND SEMI_ANNUAL < " + startQuarter);
                updateQuery = updateQuery.replace(Constant.END_FREQUENCY, " AND SEMI_ANNUAL > " + endQuarter);
                break;
        }
        
        salesProjectionDAO.executeUpdateQuery(QueryUtil.replaceTableNames(updateQuery, projectionSelectionDTO.getSessionDTO().getCurrentTableNames()));
    }

    public void salesAndUnitsMassUpdate(final ProjectionSelectionDTO projectionSelectionDTO, SalesProjectionDAO salesProjectionDAO, List<Object> input)  throws PortalException, SystemException{
        SessionDTO sessionDto=projectionSelectionDTO.getSessionDTO();
        StringBuilder hierarchyNumber = new StringBuilder();
        String lowerMostLevelNo = projectionSelectionDTO.getHierarchyIndicator().equals("C") ? String.valueOf(sessionDto.getCustomerLevelNumber()) : String.valueOf(sessionDto.getProductLevelNumber());
        String prodCustVersion = projectionSelectionDTO.getHierarchyIndicator().equals("C") ? "PROJECTION_CUST_VERSION" : "PROJECTION_PROD_VERSION";
        String prodCustBuilderSid = projectionSelectionDTO.getHierarchyIndicator().equals("C") ? "CUST_RELATIONSHIP_BUILDER_SID" : "PROD_RELATIONSHIP_BUILDER_SID";
        String sqlQuery = SQlUtil.getQuery("selected-Hierarchy-Query-sales").replace("@HIER_NO", projectionSelectionDTO.getHierarchyIndicator().equals("C") ? "CUST_HIERARCHY_NO" : "PROD_HIERARCHY_NO")
                .replace("@LEVEL_NO",lowerMostLevelNo).replace("@PROJ_ID", String.valueOf(sessionDto.getProjectionId())).replace("@CUSTPRODVER", prodCustVersion)
               .replace("@REL_BUILD_ID", prodCustBuilderSid);
        List<String> hierarchyList = (List<String>)salesProjectionDAO.executeSelectQuery(QueryUtil.replaceTableNames(sqlQuery, sessionDto.getCurrentTableNames()));
        
        for (String hierarchy : hierarchyList) {
            hierarchyNumber.append("('").append(hierarchy).append("')").append(",");
        }
        hierarchyNumber.replace(hierarchyNumber.lastIndexOf(","), hierarchyNumber.length(), "");
        String sqlUnitsQuery=com.stpl.app.utils.QueryUtils.getQuery(input, "mass-update-sales-units");
        
        sqlUnitsQuery= sqlUnitsQuery.replace("@HIERARCHY_NO_VALUES", hierarchyNumber );
        salesProjectionDAO.executeUpdateQuery(QueryUtil.replaceTableNames(sqlUnitsQuery, projectionSelectionDTO.getSessionDTO().getCurrentTableNames()));
    }

    /**
     *
     * @param projectionSelectionDTO
     * @param startYear
     * @param endYear
     * @param startQuarter
     * @param endQuarter
     * @param value
     * @param growth
     * @throws PortalException
     * @throws Exception
     */
    public void saveOnMassUpdateReturns(final ProjectionSelectionDTO projectionSelectionDTO, final int startYear, final int endYear, final int startQuarter, final int endQuarter, final String enteredValue, final String updateVariable, final SalesRowDto salesDTO, boolean flag, final Map<String, Double> selectedValues) {
        LOGGER.debug("EnteredValue--> " + enteredValue);
        Double actualAmount = 0.0;
        int frequencyValue = 0;
        String frequency = StringUtils.EMPTY;
        try {
            String updateQuery;
            if (flag || updateVariable.equals(Constant.GROWTH_RATE)) {
                updateQuery = "UPDATE SP SET @USER_ENTERED_PROPERTY_VALUE=@USER_ENTERED_VALUE @VARIABLE FROM ST_RETURNS_PROJ_DETAILS SP   JOIN ST_RETURNS_PROJ_MASTER SPM ON SPM.RETURNS_DETAILS_SID = SP.RETURNS_DETAILS_SID   JOIN PERIOD P ON P.PERIOD_SID = SP.PERIOD_SID    WHERE   SPM.CHECK_RECORD = 1 AND P.PERIOD_SID IN (@PERIOD_QUERY);";
            } else {

                updateQuery = SQlUtil.getQuery("RETURNS_MANNUAL_ENTRY_QUERY");
            }

            String periodQuery = SQlUtil.getQuery("MASS_UPDATE_PERIOD_QUERY").replace("@START_YEAR", String.valueOf(startYear))
                    .replace("@END_YEAR", String.valueOf(endYear))
                    .replace("@START_QUARTER", String.valueOf(startQuarter))
                    .replace("@END_QUATER", String.valueOf(endQuarter));

            switch (projectionSelectionDTO.getFrequencyDivision()) {
                case 1:
                    frequencyValue = NumericConstants.TWELVE;
                    periodQuery = periodQuery.replace(Constant.START_FREQUENCY_AT, Constant.AND_MONTH_ZERO)
                            .replace(Constant.END_FREQUENCY, Constant.AND_MONTH_ZERO);
                    break;
                case NumericConstants.TWELVE:
                    frequencyValue = 1;
                    periodQuery = periodQuery.replace(Constant.START_FREQUENCY_AT, " AND MONTH < " + startQuarter)
                            .replace(Constant.END_FREQUENCY, " AND MONTH > " + endQuarter);
                    frequency = "P.MONTH,";
                    break;
                case NumericConstants.FOUR:
                    frequencyValue = NumericConstants.THREE;
                    periodQuery = periodQuery.replace(Constant.START_FREQUENCY_AT, " AND QUARTER < " + startQuarter)
                            .replace(Constant.END_FREQUENCY, " AND QUARTER > " + endQuarter);
                    frequency = "P.QUARTER,";
                    break;
                case NumericConstants.TWO:
                    frequencyValue = NumericConstants.SIX;
                    periodQuery = periodQuery.replace(Constant.START_FREQUENCY_AT, " AND SEMI_ANNUAL < " + startQuarter)
                            .replace(Constant.END_FREQUENCY, " AND SEMI_ANNUAL > " + endQuarter);
                    frequency = "P.SEMI_ANNUAL,";
                    break;
            }
            updateQuery = updateQuery.replace("@PERIOD_QUERY", periodQuery);
            if (updateVariable.equals(Constant.PROJECTED_RETURN_AMT)) {
                updateQuery = updateQuery.replace(Constant.VARIABLE1_AT, ",REFRESHED_NAME='PROJECTED_RETURN_AMOUNT'");
                updateQuery = updateQuery.replace(Constant.USER_ENTERED_PROPERTY_VALUE, Constant.PROJECTED_RETURN_AMOUNT).replace(Constant.RETURNS_DETAILS_SID_AT, salesDTO.getReturnDetailsSid());
                String query;
                String bulkQuery = StringUtils.EMPTY;
                String amountValue;
                if (salesDTO.getReturnDetailsSid().split(",").length == 1) {
                    updateQuery = updateQuery.replace(Constant.RETURNS_DETAILS_SID_AT, salesDTO.getReturnDetailsSid());
                    actualAmount = Double.valueOf(enteredValue) / frequencyValue;
                    updateQuery = updateQuery.replace(Constant.USER_ENTERED_VALUE, StringUtils.EMPTY + actualAmount).replace(Constant.VARIABLE1_AT, ",REFRESHED_NAME='PROJECTED_RETURN_PERCENT'");
                    salesAllocationDAO.executeUpdateQuery(QueryUtil.replaceTableNames(updateQuery, projectionSelectionDTO.getSessionDTO().getCurrentTableNames()));
                } else {
                    for (Map.Entry<String, Double> entrys : selectedValues.entrySet()) {

                        if (projectionSelectionDTO.getFrequencyDivision() == 1) {
                            query = updateQuery.replace(Constant.YEAR1_AT, StringUtils.EMPTY + entrys.getKey().split(",")[0]);
                            query = addFrequencyInQuery(projectionSelectionDTO.getFrequencyDivision(), Integer.valueOf(entrys.getKey().split(",")[0]), query);
                        } else {
                            query = updateQuery.replace(Constant.YEAR1_AT, StringUtils.EMPTY + entrys.getKey().split(",")[0]).replace(Constant.PERIOD1_AT, StringUtils.EMPTY + entrys.getKey().split(",")[1]);
                            query = addFrequencyInQuery(projectionSelectionDTO.getFrequencyDivision(), Integer.valueOf(entrys.getKey().split(",")[1]), query);
                        }
                        if (!entrys.getValue().isInfinite() && !entrys.getValue().isNaN()) {
                            actualAmount = entrys.getValue() / 100;
                            amountValue = "PROJECTED_RETURN_AMOUNT+(PROJECTED_RETURN_AMOUNT*" + actualAmount + ")";
                        } else {
                            actualAmount = Double.valueOf(enteredValue) / (salesDTO.getReturnDetailsSid().split(",").length);
                            amountValue = String.valueOf(actualAmount / frequencyValue);
                        }
                        bulkQuery += query.replace(Constant.USER_ENTERED_PROPERTY_VALUE, Constant.PROJECTED_RETURN_AMOUNT).replace(Constant.USER_ENTERED_VALUE, StringUtils.EMPTY + amountValue);
                    }
                    salesAllocationDAO.executeUpdateQuery(QueryUtil.replaceTableNames(bulkQuery, projectionSelectionDTO.getSessionDTO().getCurrentTableNames()));
                }
            } else if (updateVariable.equals(Constant.PROJECTED_RETURN_PER)) {
                updateQuery = updateQuery.replace(Constant.USER_ENTERED_PROPERTY_VALUE, "PROJECTED_RETURN_PERCENT");
                if (salesDTO.getReturnDetailsSid().split(",").length == 1) {
                    updateQuery = updateQuery.replace(Constant.RETURNS_DETAILS_SID_AT, salesDTO.getReturnDetailsSid());
                    actualAmount = Double.valueOf(enteredValue);
                    updateQuery = updateQuery.replace(Constant.USER_ENTERED_VALUE, StringUtils.EMPTY + actualAmount).replace(Constant.VARIABLE1_AT, ",REFRESHED_NAME='PROJECTED_RETURN_PERCENT'");

                    salesAllocationDAO.executeUpdateQuery(QueryUtil.replaceTableNames(updateQuery, projectionSelectionDTO.getSessionDTO().getCurrentTableNames()));
                } else {
                    List<Map> mapList = getActiveExFactorySalesAndUnitsForMassUpdate(projectionSelectionDTO, periodQuery, frequency);
                    Map<String, Map<String, Double>> salesMap = mapList.get(0);
                    String bulkQuery = StringUtils.EMPTY;
                    String query;
                    for (Map.Entry<String, Map<String, Double>> entrys : salesMap.entrySet()) {
                        query = updateQuery.replace(Constant.YEAR1_AT, StringUtils.EMPTY + entrys.getKey().split(",")[0]).replace(Constant.PERIOD1_AT, StringUtils.EMPTY + entrys.getKey().split(",")[1]);
                        query = addFrequencyInQuery(projectionSelectionDTO.getFrequencyDivision(), Integer.valueOf(entrys.getKey().split(",")[1]), query);
                        bulkQuery += calculationLogic(projectionSelectionDTO, salesDTO.getHierarchyNo(), enteredValue, query, entrys.getValue(), entrys.getValue());
                    }
                    salesAllocationDAO.executeUpdateQuery(QueryUtil.replaceTableNames(bulkQuery, projectionSelectionDTO.getSessionDTO().getCurrentTableNames()));
                }
            } else if (updateVariable.equals(Constant.PROJECTED_RPU)) {
                updateQuery = updateQuery.replace(Constant.USER_ENTERED_PROPERTY_VALUE, "PROJECTED_RPU");
                if (salesDTO.getReturnDetailsSid().split(",").length == 1) {
                    updateQuery = updateQuery.replace(Constant.RETURNS_DETAILS_SID_AT, salesDTO.getReturnDetailsSid());
                    actualAmount = Double.valueOf(enteredValue);
                    updateQuery = updateQuery.replace(Constant.USER_ENTERED_VALUE, StringUtils.EMPTY + actualAmount).replace(Constant.VARIABLE1_AT, ",REFRESHED_NAME='PROJECTED_RPU'");
                    salesAllocationDAO.executeUpdateQuery(QueryUtil.replaceTableNames(updateQuery, projectionSelectionDTO.getSessionDTO().getCurrentTableNames()));
                } else {
                    List<Map> mapList = getActiveExFactorySalesAndUnitsForMassUpdate(projectionSelectionDTO, periodQuery, frequency);
                    Map<String, Map<String, Double>> salesMap = mapList.get(0);
                    Map<String, Map<String, Double>> unitsMap = mapList.get(1);
                    String bulkQuery = StringUtils.EMPTY;
                    String query;
                    for (Map.Entry<String, Map<String, Double>> entrys : salesMap.entrySet()) {
                        query = updateQuery.replace(Constant.YEAR1_AT, StringUtils.EMPTY + entrys.getKey().split(",")[0]).replace(Constant.PERIOD1_AT, StringUtils.EMPTY + entrys.getKey().split(",")[1]);
                        query = addFrequencyInQuery(projectionSelectionDTO.getFrequencyDivision(), Integer.valueOf(entrys.getKey().split(",")[1]), query);
                        bulkQuery += calculationLogic(projectionSelectionDTO, salesDTO.getHierarchyNo(), enteredValue, query, entrys.getValue(), unitsMap.get(entrys.getKey()));
                    }
                    salesAllocationDAO.executeUpdateQuery(QueryUtil.replaceTableNames(bulkQuery, projectionSelectionDTO.getSessionDTO().getCurrentTableNames()));
                }
            } else if (updateVariable.equals(Constant.GROWTH_RATE)) {
                actualAmount = Double.valueOf(enteredValue);
                updateQuery = updateQuery.replace(Constant.USER_ENTERED_PROPERTY_VALUE, "GROWTH_RATE");
                updateQuery = updateQuery.replace(Constant.USER_ENTERED_VALUE, StringUtils.EMPTY + actualAmount).replace(Constant.VARIABLE1_AT, StringUtils.EMPTY);

                salesAllocationDAO.executeUpdateQuery(QueryUtil.replaceTableNames(updateQuery, projectionSelectionDTO.getSessionDTO().getCurrentTableNames()));
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    /**
     *
     * @param projectionSelectionDTO
     * @param adjType
     * @param adjVal
     * @param adjBasis
     * @param adsVar
     * @param adsMeth
     * @param historyPeriods
     * @param projectionPeriods
     * @throws SystemException
     * @throws SQLException
     * @throws Exception
     */
    public void adjustSalesProjection(final ProjectionSelectionDTO projectionSelectionDTO, final String adjType, final String adjVal,
            final String adjBasis, final String adsVar, final String adsMeth, final String historyPeriods, String projectionPeriods) throws SystemException, SQLException, PortalException {
        List<String> inputList = new ArrayList<>();
        inputList.add(projectionSelectionDTO.getFrequency());
        inputList.add(projectionPeriods);
        inputList.add(adjBasis);
        inputList.add(adjVal);
        inputList.add(adjType);
        String salesInclusion = ALL.equals(projectionSelectionDTO.getSessionDTO().getSalesInclusion()) ? null : projectionSelectionDTO.getSessionDTO().getSalesInclusion();
        inputList.add(salesInclusion);
        inputList.add(adsVar);
        inputList.add(projectionPeriods);
        com.stpl.app.utils.QueryUtils.updateAppDataUsingSessionTables(inputList, "sales-adjustment-query", projectionSelectionDTO.getSessionDTO());
    }


    public AlternateLookupSource searchAlternateCustomerAndBrand(final CustomFieldGroup searchBinder, final String searchType, boolean flag) throws SystemException {

        List<CompanyMaster> resultTPList;
        final AlternateLookupSource alternate = new AlternateLookupSource();

        LOGGER.debug("Entering searchAlternateBrand  ::::");

        if (Constant.TP.equalsIgnoreCase(searchType)) {
            final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class);

            if (!flag) {
                if (String.valueOf(searchBinder.getField(Constant.CONTRACT_HOLDER).getValue()) != null
                        && !StringUtils.EMPTY.equals(String.valueOf(searchBinder.getField(Constant.CONTRACT_HOLDER).getValue()))
                        && !CommonUtils.STRING_NULL.equals(String.valueOf(searchBinder.getField(Constant.CONTRACT_HOLDER).getValue()))) {

                    String contractHolderName = String.valueOf(searchBinder.getField(Constant.CONTRACT_HOLDER).getValue());
                    contractHolderName = contractHolderName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);

                    dynamicQuery.add(RestrictionsFactoryUtil.ilike(Constant.COMPANY_NAME, contractHolderName));

                } else if (String.valueOf(searchBinder.getField(Constant.CUSTOMER_ID).getValue()) != null && !StringUtils.EMPTY.equals(String.valueOf(searchBinder.getField(Constant.CUSTOMER_ID).getValue()))
                        && !CommonUtils.STRING_NULL.equals(String.valueOf(searchBinder.getField(Constant.CUSTOMER_ID).getValue()))) {
                    String customerId = String.valueOf(searchBinder.getField(Constant.CUSTOMER_ID).getValue());

                    customerId = customerId.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);

                    dynamicQuery.add(RestrictionsFactoryUtil.ilike(Constant.COMPANY_NO, customerId));
                }
            } else {
                dynamicQuery.add(RestrictionsFactoryUtil.ilike(Constant.COMPANY_NAME, Constant.PERCENT));
                dynamicQuery.add(RestrictionsFactoryUtil.ilike(Constant.COMPANY_NO, Constant.PERCENT));

            }
            dynamicQuery.add(PropertyFactoryUtil.forName(Constant.COMPANYMASTERSID).in(
                    DynamicQueryFactoryUtil.forClass(ContractMaster.class).setProjection(ProjectionFactoryUtil.property("contHoldCompanyMasterSid"))));
            resultTPList = dataSelection.getCompanyMasterList(dynamicQuery);
            LOGGER.debug("Size of resultTPList " + resultTPList.size());

            List<ContractBrandDTO> temp;
            temp = getAlternateTP(resultTPList);
            alternate.setContractcustomersList(temp);
        }

        LOGGER.debug("Ending searchAlternateBrand    ::::");
        return alternate;
    }

    public List<ContractBrandDTO> getAlternateTP(final List<CompanyMaster> list) {
        final List<ContractBrandDTO> resultList = new ArrayList<>();

        LOGGER.debug("Entering getAlternateTP  ::::");

        final int index = list.size();

        for (int i = 0; i < index; i++) {
            final CompanyMaster comapny = list.get(i);
            final ContractBrandDTO alternateTP = new ContractBrandDTO();
            alternateTP.setCustomerId(comapny.getCompanyNo());
            alternateTP.setContractHolder(comapny.getCompanyName());
            alternateTP.setContractSid(comapny.getCompanyMasterSid());
            resultList.add(alternateTP);
        }

        LOGGER.debug("Ending getAlternateTP return  size ::::" + resultList.size());
        return resultList;
    }

    public List searchAlternateContract(ContractBrandDTO contractBrandDTO) throws PortalException, SystemException {

        String companyNo = contractBrandDTO.getCustomer().replace("*", Constant.PERCENT);
        String contractName = contractBrandDTO.getContractName();
        String contractNo = contractBrandDTO.getContractNumber();

        if (companyNo.contains("*")) {
            companyNo = companyNo.replace("*", Constant.PERCENT);
        }

        if (contractName.contains("*")) {
            contractName = contractName.replace("*", Constant.PERCENT);
        }

        if (contractNo.contains("*")) {
            contractNo = contractNo.replace("*", Constant.PERCENT);
        }

        StringBuilder query = new StringBuilder();
        query.append("SELECT DISTINCT CM.COMPANY_NO,CO.CONTRACT_NO,CO.CONTRACT_NAME,CM.COMPANY_MASTER_SID,CO.CONTRACT_MASTER_SID FROM dbo.CCP_DETAILS CCP \n"
                + " JOIN dbo.COMPANY_MASTER CM ON CM.COMPANY_MASTER_SID = CCP.COMPANY_MASTER_SID \n"
                + " JOIN dbo.CONTRACT_MASTER CO ON CO.CONTRACT_MASTER_SID = CCP.CONTRACT_MASTER_SID \n"
                + " JOIN dbo.HELPER_TABLE HT ON HT.HELPER_TABLE_SID=CO.CONTRACT_TYPE AND HT.LIST_NAME = 'CONTRACT_TYPE' AND \n"
                + " HT.DESCRIPTION IN ('FFS','Medicaid FFS','SPAP','ADAP','PHS','Managed Medicaid','MM','Federal') WHERE CM.COMPANY_MASTER_SID = " + companyNo
                + " AND (CO.CONTRACT_NAME like '" + contractName + "' OR CO.CONTRACT_NO like '" + contractNo + "')");

        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        List list = (List) salesProjectionDAO.executeSelectQuery(query.toString());

        return convertAlternateContractResults(list);

    }

    public List convertAlternateContractResults(List list) {

        List<ContractBrandDTO> resultList = new ArrayList<>();

        for (Object list1 : list) {
            ContractBrandDTO contractBrandDTO = new ContractBrandDTO();
            Object[] obj = (Object[]) list1;
            contractBrandDTO.setCustomer(String.valueOf(obj[0]));
            contractBrandDTO.setContractNumber(String.valueOf(obj[1]));
            contractBrandDTO.setContractName(String.valueOf(obj[NumericConstants.TWO]));
            contractBrandDTO.setCustomerId(String.valueOf(obj[NumericConstants.THREE]));
            contractBrandDTO.setContractSid(Integer.valueOf(String.valueOf(obj[NumericConstants.FOUR])));
            resultList.add(contractBrandDTO);
        }

        return resultList;
    }

    public List loadAlternateCustomer() throws SystemException, PortalException {
        StringBuilder query = new StringBuilder();
        query.append("SELECT DISTINCT CM.COMPANY_MASTER_SID,CM.COMPANY_NO FROM dbo.CCP_DETAILS CCP \n"
                + " JOIN dbo.COMPANY_MASTER CM ON CM.COMPANY_MASTER_SID = CCP.COMPANY_MASTER_SID \n"
                + " JOIN dbo.CONTRACT_MASTER CO ON CO.CONTRACT_MASTER_SID = CCP.CONTRACT_MASTER_SID \n"
                + " JOIN dbo.HELPER_TABLE HT ON HT.HELPER_TABLE_SID=CO.CONTRACT_TYPE AND HT.LIST_NAME = 'CONTRACT_TYPE' AND \n"
                + " HT.DESCRIPTION IN ('FFS','Medicaid FFS','SPAP','ADAP','PHS','Managed Medicaid','MM','Federal');");

        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        return (List) salesProjectionDAO.executeSelectQuery(query.toString());

    }

    public List loadAlternateBrand(String brandName) throws PortalException, SystemException {

        if (brandName.contains("*")) {
            brandName = brandName.replace("*", Constant.PERCENT);
        }

        StringBuilder query = new StringBuilder();
        query.append("SELECT DISTINCT BRAND_MASTER_SID,BRAND_NAME,BRAND_ID FROM dbo.BRAND_MASTER WHERE BRAND_NAME like '").append(brandName).append("';");
        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        List list = (List) salesProjectionDAO.executeSelectQuery(query.toString());
        return convertAlternateBrandResults(list);
    }

    public List<ContractBrandDTO> convertAlternateBrandResults(List list) {

        List<ContractBrandDTO> resultList = new ArrayList<>();

        for (Object list1 : list) {
            ContractBrandDTO contractBrandDTO = new ContractBrandDTO();
            Object[] obj = (Object[]) list1;
            contractBrandDTO.setBrandMasterSid(Integer.valueOf(String.valueOf(obj[0])));
            contractBrandDTO.setBrand(String.valueOf(obj[1]));
            contractBrandDTO.setBrandSid(String.valueOf(obj[NumericConstants.TWO]));
            resultList.add(contractBrandDTO);
        }

        return resultList;
    }

    /**
     *
     * @param projectionSelectionDTO
     * @param historyPeriods
     * @param projectionPeriods
     * @return
     * @throws SystemException
     * @throws SQLException
     */
    public boolean callAdjustmentProcedure(final ProjectionSelectionDTO projectionSelectionDTO, final String historyPeriods, final String projectionPeriods, final String adjType, final String adjVal, final String adjBasis, final String adsVar, final String adsMeth) throws SQLException  {

        boolean status = false;

        final DataSourceConnection dataSourceConnection = DataSourceConnection.getInstance();
        Connection connection = null;
        CallableStatement statement = null;
        try {
            connection = dataSourceConnection.getConnection();

            if (connection != null) {
                LOGGER.info("PRC_SALES_ADJUSTMENT_TEMP");
                LOGGER.info("BASLINE_PERIODS" + historyPeriods);
                LOGGER.info("SELECTED_PERIODS" + projectionPeriods);
                LOGGER.info("PROJECTION_SID" + projectionSelectionDTO.getProjectionId());
                LOGGER.info("Frequency" + projectionSelectionDTO.getFrequency());
                LOGGER.info("USER_ID" + projectionSelectionDTO.getUserId());
                LOGGER.info("SESSION_ID" + projectionSelectionDTO.getSessionDTO().getSessionId());
                LOGGER.info("adjType " + adjType);
                LOGGER.info("adjBasis " + adjBasis);
                LOGGER.info("adsVar " + adsVar);
                LOGGER.info("adsMeth " +adsMeth);
                LOGGER.info("adjVal " + adjVal);
                LOGGER.info(projectionSelectionDTO.getSessionDTO().getSalesInclusion().equals(ALL) ? null : projectionSelectionDTO.getSessionDTO().getSalesInclusion());
                LOGGER.info(projectionSelectionDTO.getUomCode());
               
                if (Constants.BUSINESS_PROCESS_TYPE_NONMANDATED.equalsIgnoreCase(projectionSelectionDTO.getScreenName())) {
                    statement = connection.prepareCall("{call PRC_SALES_ADJUSTMENT_TEMP (?,?,?,?,?,?,?,?,?,?,?,?,?)}");
                } else {
                    statement = connection.prepareCall("{call PRC_SALES_ADJUSTMENT (?,?,?,?,?,?,?,?,?,?,?)}");
                }
                statement.setObject(1, historyPeriods); //  @BASLINE_PERIODS 
                statement.setObject(NumericConstants.TWO, projectionPeriods); //  @SELECTED_PERIODS
                statement.setObject(NumericConstants.THREE, projectionSelectionDTO.getProjectionId()); //  @PROJECTION_SID
                statement.setObject(NumericConstants.FOUR, projectionSelectionDTO.getFrequency());//Frequency
                statement.setObject(NumericConstants.FIVE, projectionSelectionDTO.getUserId()); //  @USER_ID
                statement.setObject(NumericConstants.SIX, projectionSelectionDTO.getSessionDTO().getSessionId()); //  @SESSION_ID
                statement.setObject(NumericConstants.SEVEN,adjType );
                statement.setObject(NumericConstants.EIGHT,adjBasis);
                statement.setObject(NumericConstants.NINE, adsVar);
                statement.setObject(NumericConstants.TEN,adsMeth);
                statement.setObject(NumericConstants.ELEVEN, adjVal);
                 if (Constants.BUSINESS_PROCESS_TYPE_NONMANDATED.equalsIgnoreCase(projectionSelectionDTO.getScreenName())) {
                     statement.setObject(NumericConstants.TWELVE, projectionSelectionDTO.getSessionDTO().getSalesInclusion().equals(ALL) ? null : projectionSelectionDTO.getSessionDTO().getSalesInclusion());
                     statement.setObject(NumericConstants.THIRTEEN, projectionSelectionDTO.getUomCode());
                 } 
                status = statement.execute();
            }

        } catch (Exception ex) {
            LOGGER.error(ex);
        } finally {
            statement.close();
            connection.close();
        }
        return status;
    }
    public static final String ALL = "ALL";

    /**
     * Saves the calculation selection before calculation in Commercial and
     * Government Forecast and then calculation procedure is called. In Case of
     * Government Forecast, MandatedDiscount Insert Procedure is called after
     * calculation.
     *
     *
     * @param projectionSelectionDTO
     * @param methodology
     * @param calcPeriods
     * @param calcBased
     * @param startPeriodSID
     * @param endPeriodSID
     * @param allocationBasis
     */
    public boolean calculateSalesProjection(final ProjectionSelectionDTO projectionSelectionDTO, final String methodology, final String calcPeriods, final String calcBased,
            final String startPeriodSID, final String endPeriodSID, final String allocationBasis) {
        boolean isSalesCalculated = false;
        projectionSelectionDTO.setTabName(SALES_PROJ.getConstant());
        try {
            saveSelectionForCalculation(projectionSelectionDTO, methodology, calcPeriods, calcBased, startPeriodSID, endPeriodSID, allocationBasis);
            if (Constant.PERC_OF_EX_FACTORY_SEASONAL_TREND.equalsIgnoreCase(methodology)) {
                callCalculationProcedure(projectionSelectionDTO,calcBased,allocationBasis);
                return true;
            }
            cumulativeCalculation(projectionSelectionDTO, calcBased, methodology, "st_sales_growth_factor_");
            isSalesCalculated = callCalculationProcedure(projectionSelectionDTO,calcBased,allocationBasis);
            if (isSalesCalculated && CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equals(projectionSelectionDTO.getScreenName())) {
                Thread thread = new Thread(createDiscountProcedureRunnable(projectionSelectionDTO));
                thread.start();
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return isSalesCalculated;
    }

    /**
     *
     * @param projectionSelectionDTO
     * @throws SQLException
     * @throws javax.naming.NamingException
     */
    public boolean callCalculationProcedure(final ProjectionSelectionDTO projectionSelectionDTO,final String calcBased,final String allocationBasis) throws SQLException, NamingException {
        LOGGER.info("callCalculationProcedure PRC_SALES_PROJECTION ");
        LOGGER.info("Projection ID --- " + projectionSelectionDTO.getProjectionId());
        LOGGER.info("UserID ---        " + projectionSelectionDTO.getUserId());
        LOGGER.info("Session ID ----   " + projectionSelectionDTO.getSessionDTO().getSessionId());
        LOGGER.info("Frequency ----    " + projectionSelectionDTO.getFrequency());
        LOGGER.info("ScreenName ----    " + projectionSelectionDTO.getScreenName());
        LOGGER.info("calcbased ----    " + calcBased);
        LOGGER.info("fstartid ----    " + start);
        LOGGER.info("fendid ----    " + end);
        LOGGER.info("allocationBasis ----    " + allocationBasis);
        LOGGER.info(projectionSelectionDTO.getSessionDTO().getSalesInclusion().equals(ALL) ? null : projectionSelectionDTO.getSessionDTO().getSalesInclusion());
        boolean isCalculated = false;
        final DataSourceConnection dataSourceConnection = DataSourceConnection.getInstance();
        Connection connection = dataSourceConnection.getConnection();
        if (connection != null) {
            if (Constants.BUSINESS_PROCESS_TYPE_NONMANDATED.equalsIgnoreCase(projectionSelectionDTO.getScreenName())) {
                getStatement(connection, "PRC_SALES_PROJECTION_TEMP",projectionSelectionDTO,start,end,calcBased,allocationBasis,"NM");
                isCalculated = true;
            } else {
                 getStatement(connection, "PRC_SALES_PROJECTION",projectionSelectionDTO,start,end,calcBased,allocationBasis,"M");
                isCalculated = true;
            }
        }
        return isCalculated;
    }
//@selectedfreq',
//        @year int = @startyear,
//        @period int = @startperiodvalue,
//        @year1 int = @endyear,
//        @period1 int = @endPeriodvalue,

    /**
     *
     * @param projectionSelectionDTO
     * @param methodology
     * @param calcPeriods
     * @param calcBased
     * @param startPeriodSID
     * @param endPeriodSID
     * @param allocationBasis
     * @throws PortalException
     * @throws Exception
     */
    public void saveSelectionForCalculation(final ProjectionSelectionDTO projectionSelectionDTO, final String methodology, final String calcPeriods, final String calcBased,
            final String startPeriod, final String endPeriod, final String allocationBasis) throws PortalException, SystemException {
        CommonLogic logic = new CommonLogic();
        String startyear = Constant.ANNUAL.equalsIgnoreCase(projectionSelectionDTO.getFrequency()) ? startPeriod.trim() : logic.getYearAndPeriod(startPeriod, projectionSelectionDTO.getFrequency(), true)[0];
        String startperiodValue = Constant.ANNUAL.equalsIgnoreCase(projectionSelectionDTO.getFrequency()) ? startPeriod.trim() : logic.getYearAndPeriod(startPeriod, projectionSelectionDTO.getFrequency(), true)[1];
        String endyear = Constant.NULL_CAPS;
        String endperiodValue = Constant.NULL_CAPS;
        if (!StringUtils.isEmpty(endPeriod.trim()) && !"null".equals(endPeriod.trim())) {
            endyear = Constant.ANNUAL.equalsIgnoreCase(projectionSelectionDTO.getFrequency()) ? endPeriod.trim() : logic.getYearAndPeriod(endPeriod, projectionSelectionDTO.getFrequency(), true)[0];
            endperiodValue = Constant.ANNUAL.equalsIgnoreCase(projectionSelectionDTO.getFrequency()) ? endPeriod.trim() : logic.getYearAndPeriod(endPeriod, projectionSelectionDTO.getFrequency(), true)[1];
            end = getPeriodSid(endPeriod, projectionSelectionDTO.getFrequency(), "Max");
        }
        String masterTable = CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equals(projectionSelectionDTO.getScreenName()) ? Constant.ST_M_SALES_PROJECTION_MASTER : Constant.ST_NM_SALES_PROJECTION_MASTER;

        String updateQuery;
        //Need to remove once the dynamic changes is done in Government
        if (CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equals(projectionSelectionDTO.getScreenName())) {
            updateQuery = CustomSQLUtil.get("saveCalculationSelectionForSP");
        } else {
            updateQuery = SQlUtil.getQuery("save-calculation-selection");
            updateQuery = updateQuery.replace(Constant.USERID_ADD, StringUtils.EMPTY);
        }
        updateQuery = updateQuery.replace("@selectedfreq", projectionSelectionDTO.getFrequency());
        updateQuery = updateQuery.replace("@startyear", startyear);
        updateQuery = updateQuery.replace("@startperiodvalue", startperiodValue);
        updateQuery = updateQuery.replace("@endyear", endyear);
        updateQuery = updateQuery.replace("@endPeriodvalue", endperiodValue);
        updateQuery = updateQuery.replace("@METHODOLOGY", methodology);
        updateQuery = updateQuery.replace("@CALCULATION_PERIODS", calcPeriods);
        updateQuery = updateQuery.replace("@CALCULATION_BASED", calcBased);
        updateQuery = updateQuery.replace("@PROJECTION_MASTER_SID", String.valueOf(projectionSelectionDTO.getProjectionId()));
        updateQuery = updateQuery.replace(Constant.MASTER_TABLE, masterTable);
        updateQuery = updateQuery.replace("@allocationbasis", (Constant.NULL.equals(allocationBasis) || StringUtils.isEmpty(allocationBasis)) ? Constant.NULL_CAPS : "'" + allocationBasis + "'");
        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        salesProjectionDAO.executeUpdateQuery(QueryUtil.replaceTableNames(updateQuery, projectionSelectionDTO.getSessionDTO().getCurrentTableNames()));
         start = getPeriodSid(startPeriod, projectionSelectionDTO.getFrequency(), "Min");

    }

    public String loadTotalLives(int projectionId) throws PortalException, SystemException {
        BigDecimal lives = new BigDecimal(0.0);
        List<String> list = getTotalLives(projectionId, false);
        if (list != null) {
            for (String live : list) {
                lives = lives.add(BigDecimal.valueOf(Double.parseDouble(live)));
            }
        }
        return String.valueOf(lives);
    }

    public List getTotalLives(int projectionId, boolean chartflag) throws PortalException, SystemException {

        List list;
        StringBuilder queryBuilder = new StringBuilder(StringUtils.EMPTY);

        if (!chartflag) {
            queryBuilder.append(" select MDA.COLUMN_3 ");
        } else {
            queryBuilder.append("  select MASTER_ID,COLUMN_1 as col1,COLUMN_2 as col2,COLUMN_3 as col3   ");
        }

        queryBuilder.append(" from dbo.MASTER_DATA_ATTRIBUTE MDA where MDA.MASTER_TYPE like'%COMPANY_MASTER%'  AND MDA.MASTER_ATTRIBUTE like'%COV_LIVES%' AND MDA.MASTER_ID in(  ");
        queryBuilder.append("  select cm.COMPANY_ID from dbo.COMPANY_MASTER cm where cm.COMPANY_MASTER_SID in (select ccpd.COMPANY_MASTER_SID from dbo.CCP_DETAILS ccpd  where ccpd.CCP_DETAILS_SID in (select pd.CCP_DETAILS_SID  ");
        queryBuilder.append("  from dbo.PROJECTION_DETAILS pd where  pd.PROJECTION_MASTER_SID=").append(projectionId).append("  )))");

        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        list = (List) salesProjectionDAO.executeSelectQuery(queryBuilder.toString());
        return list;
    }

    /**
     *
     * @param projectionSelectionDTO
     * @param hierarchyNo
     * @param userGroupValue
     * @throws PortalException
     * @throws Exception
     */
    public void saveSalesGroup(final ProjectionSelectionDTO projectionSelectionDTO, final String hierarchyNo, String userGroupValue) throws PortalException, SystemException {
        String saveQuery = SQlUtil.getQuery("group-filter-save");
        saveQuery = saveQuery.replace("[?USER_GROUP]", userGroupValue);
        saveQuery = saveQuery.replace("[?HIERARCHY_NO]", hierarchyNo);
        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        salesProjectionDAO.executeUpdateQuery(QueryUtil.replaceTableNames(saveQuery.toString(), projectionSelectionDTO.getSessionDTO().getCurrentTableNames()));
    }

    /**
     *
     * @param projectionSelectionDTO
     * @return
     * @throws PortalException
     * @throws Exception
     */
    public List loadSalesGroup(final ProjectionSelectionDTO projectionSelectionDTO) throws PortalException, SystemException {
        StringBuilder query = new StringBuilder(StringUtils.EMPTY);
        query.append("SELECT USER_GROUP FROM ST_NM_SALES_PROJECTION_MASTER ");
        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        LOGGER.debug("Sales Group ---------------------------------" + query.toString());
        List list = (List) salesProjectionDAO.executeSelectQuery(QueryUtil.replaceTableNames(query.toString(), projectionSelectionDTO.getSessionDTO().getCurrentTableNames()));
        List finalList = new ArrayList();
        for (Object object : list) {
            finalList.add(String.valueOf(object));
        }
        LOGGER.debug("Sales Group list Size---------------------------------" + list.size() + " " + finalList.size());
        return finalList;
    }

    private int getMonthNo(String month) {
        Map<String, Integer> asd = new HashMap<>();
        asd.put("jan", 1);
        asd.put("feb", NumericConstants.TWO);
        asd.put("mar", NumericConstants.THREE);
        asd.put("apr", NumericConstants.FOUR);
        asd.put("may", NumericConstants.FIVE);
        asd.put("jun", NumericConstants.SIX);
        asd.put("jul", NumericConstants.SEVEN);
        asd.put("aug", NumericConstants.EIGHT);
        asd.put("sep", NumericConstants.NINE);
        asd.put("oct", NumericConstants.TEN);
        asd.put("nov", NumericConstants.ELEVEN);
        asd.put("dec", NumericConstants.TWELVE);
        return asd.get(month);
    }

    public void callAlternateHistoryProcedure(SessionDTO sessionDTO, ContractBrandDTO contractBrandDTO) throws SQLException {

        final DataSourceConnection dataSourceConnection = DataSourceConnection.getInstance();
        Connection connection = null;
        CallableStatement statement = null;
        try {
            connection = dataSourceConnection.getConnection();
            if (connection != null) {
                statement = connection.prepareCall("{call PRC_M_ALTERNATE_ACTUALS (?,?,?,?,?,?,?)}");
                statement.setObject(1, contractBrandDTO.getContractHierarchyNo());
                statement.setObject(NumericConstants.TWO, contractBrandDTO.getBrandHierarchyNo());
                statement.setObject(NumericConstants.THREE, contractBrandDTO.getContractSid());
                statement.setObject(NumericConstants.FOUR, contractBrandDTO.getBrandMasterSid());
                statement.setObject(NumericConstants.FIVE, sessionDTO.getProjectionId());
                statement.setObject(NumericConstants.SIX, Integer.valueOf(sessionDTO.getSessionId()));
                statement.setObject(NumericConstants.SEVEN, Integer.valueOf(sessionDTO.getUserId()));
                statement.execute();
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        } finally {
            statement.close();
            connection.close();
        }
    }

    public void callAlternateHistoryProcedure(final Object[] inputs) throws SystemException, SQLException {
        final DataSourceConnection dataSourceConnection = DataSourceConnection.getInstance();
        Connection connection = null;
        CallableStatement statement = null;
        try {
            connection = dataSourceConnection.getConnection();
            LOGGER.debug("Entering callAlternateHistoryProcedure  ::::");
            if (connection != null) {
                statement = connection.prepareCall("{call PRC_NM_ALTERNATE_ACTUALS (?,?,?,?,?,?,?)}");
                LOGGER.debug("CONT_HierarchyNo=" + inputs[0]);
                LOGGER.debug("BRAND_RELATIONSHIP_LEVEL_SID=" + inputs[1]);
                LOGGER.debug("ALTER_CONTRACT_HOLDER_SID=" + inputs[NumericConstants.TWO]);
                LOGGER.debug("ALTER_BRAND_MASTER_SID=" + inputs[NumericConstants.THREE]);
                LOGGER.debug("PROJECTION_MASTER_SID=" + inputs[NumericConstants.FOUR]);
                LOGGER.debug("SESSION_ID=" + inputs[NumericConstants.FIVE]);
                LOGGER.debug("USER_ID=" + inputs[NumericConstants.SIX]);
                statement.setObject(1, inputs[0]);
                statement.setObject(NumericConstants.TWO, inputs[1]);
                statement.setObject(NumericConstants.THREE, inputs[NumericConstants.TWO]);
                statement.setObject(NumericConstants.FOUR, inputs[NumericConstants.THREE]);
                statement.setObject(NumericConstants.FIVE, inputs[NumericConstants.FOUR]);
                statement.setObject(NumericConstants.SIX, Integer.parseInt((String) inputs[NumericConstants.FIVE]));
                statement.setObject(NumericConstants.SEVEN, inputs[NumericConstants.SIX]);
                statement.execute();
            }
            LOGGER.debug("Ending callAlternateHistoryProcedure return  staus ::::");
        } catch (Exception ex) {
            LOGGER.error(new Date() + ex.getMessage());
            throw new SystemException(ex);
        } finally {
            statement.close();
            connection.close();
        }
    }

    public void saveMandatedSalesProjection(String userId, String sessionId) throws PortalException, SystemException {
        saveActualsSales(userId, sessionId);
        saveSalesProjection(userId, sessionId);
        saveSalesProjectionMaster(userId, sessionId);
    }

    public void saveNonMandatedSalesProjection(SessionDTO session) throws PortalException, SystemException {
        String saveQuery = CustomSQLUtil.get("nm.saveToMainTable");
        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        salesProjectionDAO.executeUpdateQuery(QueryUtil.replaceTableNames(saveQuery, session.getCurrentTableNames()));
    }

    public void saveActualsSales(String userId, String sessionId) throws PortalException, SystemException {

        StringBuilder query = new StringBuilder();
        query.append("MERGE M_ACTUAL_SALES AS TARGET\n"
                + "		USING ( \n"
                + " SELECT PROJECTION_DETAILS_SID,\n"
                + "		PERIOD_SID,\n"
                + "		ACTUAL_SALES,\n"
                + "		ACTUAL_UNITS,\n"
                + "		ACTUAL_PROJECTION_SALES,\n"
                + "		ACTUAL_PROJECTION_UNITS\n"
                + "		FROM dbo.ST_M_ACTUAL_SALES\n"
                + "	 WHERE USER_ID=" + userId + "AND SESSION_ID=" + sessionId + "\n"
                + "		) AS SOURCE  \n"
                + "		ON (TARGET.PROJECTION_DETAILS_SID=SOURCE.PROJECTION_DETAILS_SID AND TARGET.PERIOD_SID=SOURCE.PERIOD_SID)\n"
                + "	WHEN MATCHED \n"
                + "	 THEN \n"
                + "	UPDATE SET \n"
                + "		TARGET.ACTUAL_SALES=SOURCE.ACTUAL_SALES,\n"
                + "		TARGET.ACTUAL_UNITS=SOURCE.ACTUAL_UNITS,\n"
                + "		TARGET.ACTUAL_PROJECTION_SALES=SOURCE.ACTUAL_PROJECTION_SALES,\n"
                + "		TARGET.ACTUAL_PROJECTION_UNITS=SOURCE.ACTUAL_PROJECTION_UNITS\n"
                + "		 WHEN NOT MATCHED BY TARGET\n"
                + "	THEN \n"
                + "	INSERT VALUES(SOURCE.PROJECTION_DETAILS_SID,\n"
                + "		SOURCE.PERIOD_SID,\n"
                + "		SOURCE.ACTUAL_SALES,\n"
                + "		SOURCE.ACTUAL_UNITS,\n"
                + "		SOURCE.ACTUAL_PROJECTION_SALES,\n"
                + "		SOURCE.ACTUAL_PROJECTION_UNITS);");

        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        salesProjectionDAO.executeUpdateQuery(query.toString());
    }

    public void saveSalesProjectionMaster(String userId, String sessionId) throws PortalException, SystemException {

        StringBuilder query = new StringBuilder();
        query.append("MERGE M_SALES_PROJECTION_MASTER AS TARGET\n"
                + "		USING ( \n"
                + "		SELECT PROJECTION_DETAILS_SID,\n"
                + "		METHODOLOGY,\n"
                + "		CALCULATION_PERIODS,\n"
                + "		CALCULATION_BASED,\n"
                + "		CHECK_RECORD, \n"
                + "		FORECAST_START_PERIOD_SID,\n"
                + "		FORECAST_END_PERIOD_SID,\n"
                + "		ALLOCATION_BASIS \n"
                + "		FROM dbo.ST_M_SALES_PROJECTION_MASTER\n"
                + "		WHERE USER_ID=" + userId + " AND SESSION_ID=" + sessionId + "\n"
                + "		) AS SOURCE \n"
                + "		ON (TARGET.PROJECTION_DETAILS_SID=SOURCE.PROJECTION_DETAILS_SID)\n"
                + "		WHEN MATCHED \n"
                + "		 THEN \n"
                + "		UPDATE SET \n"
                + "		TARGET.METHODOLOGY=SOURCE.METHODOLOGY, \n"
                + "		TARGET.CALCULATION_PERIODS=SOURCE.CALCULATION_PERIODS, \n"
                + "		TARGET.CALCULATION_BASED=SOURCE.CALCULATION_BASED, \n"
                + "		TARGET.CHECK_RECORD=SOURCE.CHECK_RECORD, \n"
                + "		TARGET.FORECAST_START_PERIOD_SID=SOURCE.FORECAST_START_PERIOD_SID, \n"
                + "		TARGET.FORECAST_END_PERIOD_SID=SOURCE.FORECAST_END_PERIOD_SID, \n"
                + "		TARGET.ALLOCATION_BASIS=SOURCE.ALLOCATION_BASIS\n"
                + "		WHEN NOT MATCHED BY TARGET\n"
                + "		 THEN \n"
                + "		INSERT VALUES(SOURCE.PROJECTION_DETAILS_SID,\n"
                + "		SOURCE.METHODOLOGY,\n"
                + "		SOURCE.CALCULATION_PERIODS,\n"
                + "		SOURCE.CALCULATION_BASED,\n"
                + "		SOURCE.CHECK_RECORD,\n"
                + "		SOURCE.FORECAST_START_PERIOD_SID,\n"
                + "		SOURCE.FORECAST_END_PERIOD_SID,\n"
                + "		SOURCE.ALLOCATION_BASIS\n"
                + ");");

        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        salesProjectionDAO.executeUpdateQuery(query.toString());

    }

    public void saveSalesProjection(String userId, String sessionId) throws PortalException, SystemException {

        StringBuilder query = new StringBuilder();
        query.append("MERGE M_SALES_PROJECTION AS TARGET\n"
                + "	USING ( \n"
                + "		SELECT PROJECTION_DETAILS_SID,\n"
                + "		ACCOUNT_GROWTH,\n"
                + "		PRODUCT_GROWTH,\n"
                + "		PROJECTION_SALES,\n"
                + "		PROJECTION_UNITS,\n"
                + "		PERIOD_SID\n"
                + "		FROM dbo.ST_M_SALES_PROJECTION\n"
                + "		WHERE USER_ID=" + userId + " AND SESSION_ID=" + sessionId + "\n"
                + "		) AS SOURCE \n"
                + "		ON (TARGET.PROJECTION_DETAILS_SID=SOURCE.PROJECTION_DETAILS_SID AND TARGET.PERIOD_SID=SOURCE.PERIOD_SID)\n"
                + "		WHEN MATCHED \n"
                + "		THEN \n"
                + "		UPDATE SET \n"
                + "		TARGET.ACCOUNT_GROWTH=SOURCE.ACCOUNT_GROWTH,\n"
                + "		TARGET.PRODUCT_GROWTH=SOURCE.PRODUCT_GROWTH,\n"
                + "		TARGET.PROJECTION_SALES=SOURCE.PROJECTION_SALES,\n"
                + "		TARGET.PROJECTION_UNITS=SOURCE.PROJECTION_UNITS,\n"
                + "		TARGET.PERIOD_SID=SOURCE.PERIOD_SID\n"
                + "		WHEN NOT MATCHED BY TARGET\n"
                + "		THEN \n"
                + "		INSERT VALUES(SOURCE.PROJECTION_DETAILS_SID,\n"
                + "		SOURCE.ACCOUNT_GROWTH,\n"
                + "		SOURCE.PRODUCT_GROWTH,\n"
                + "		SOURCE.PROJECTION_SALES,\n"
                + "		SOURCE.PROJECTION_UNITS,\n"
                + "		SOURCE.PERIOD_SID);");

        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        salesProjectionDAO.executeUpdateQuery(query.toString());

    }

    public boolean checkActuals(String hierarchyNo) {
        String query = StringUtils.EMPTY;
        try {
            query = "SELECT ISNULL(SUM(AM.SALES_AMOUNT),0) AS sales FROM ACTUALS_MASTER AM "
                    + " JOIN CCP_DETAILS CCP"
                    + " ON CCP.CONTRACT_MASTER_SID = AM.CONTRACT_MASTER_SID "
                    + " AND CCP.COMPANY_MASTER_SID = AM.COMPANY_MASTER_SID "
                    + " AND CCP.ITEM_MASTER_SID = AM.ITEM_MASTER_SID "
                    + " JOIN CCP_MAP CCM ON CCM.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID "
                    + " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD ON RLD.RELATIONSHIP_LEVEL_SID = CCM.RELATIONSHIP_LEVEL_SID"
                    + " AND RLD.HIERARCHY_NO LIKE+ '" + hierarchyNo.trim() + "%'"
                    + " WHERE AM.QUANTITY_INCLUSION = 'Y'"
                    + " AND AM.ACCRUAL_ACTUAL_START_DATE >=(SELECT Dateadd(yy,Datediff(yy,0,Getdate()) - 3,0))"
                    + " AND AM.ACCRUAL_ACTUAL_END_DATE <=(SELECT Dateadd(DD,- 1,Dateadd(qq,Datediff(qq,0,Getdate()),0)))";

            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
            List<?> list = (List<?>) salesProjectionDAO.executeSelectQuery(query);
            if (list == null || list.isEmpty()) {
                return false;
            } else {
                BigDecimal obj = (BigDecimal) list.get(0);
                return obj.doubleValue() != 0.0;
            }
        } catch (Exception e) {
            LOGGER.error(e);
            return false;
        }
    }

    /**
     * Get the Custom Tree
     *
     * @param customId
     * @param customerRelationshipId
     * @param productRelationshipId
     * @return
     */
    public static List<Leveldto> getCustomTree(int customId, int customerRelationshipId, int productRelationshipId) {
        List<Leveldto> listValue = new ArrayList<>();
        if (customId != 0) {
            List<CustomViewDetails> customDetailsList = getCustomViewDetails(customId);
            for (CustomViewDetails ob : customDetailsList) {
                List list = getRelationshipLevels(ob.getHierarchyId(), ob.getHierarchyIndicator(), customerRelationshipId, productRelationshipId);
                if (list != null && !list.isEmpty()) {
                    Object[] obj = (Object[]) list.get(0);
                    if (obj.length > 1) {
                        Leveldto dto = new Leveldto();
                        dto.setHierarchyId(ob.getHierarchyId());
                        dto.setLevelNo(Integer.valueOf(String.valueOf(obj[1])));
                        dto.setLevel(String.valueOf(obj[0]));
                        dto.setTreeLevelNo(ob.getLevelNo());
                        dto.setHierarchyIndicator(ob.getHierarchyIndicator());
                        dto.setHierarchyNo(String.valueOf(obj[NumericConstants.TWO]));
                        listValue.add(dto);
                    }
                }
            }
        }
        return listValue;
    }

    public static List getRelationshipLevels(int hierarchyLevelId, String hierarchyIndicator, int customerRelationshipId, int productRelationshipId) {
        List list = null;
        try {
            CommonDAO commonDao = new CommonDAOImpl();
            DynamicQuery query = DynamicQueryFactoryUtil.forClass(RelationshipLevelDefinition.class);
            query.add(RestrictionsFactoryUtil.eq("hierarchyLevelDefinitionSid", hierarchyLevelId));
            if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(hierarchyIndicator)) {
                query.add(RestrictionsFactoryUtil.eq("relationshipBuilderSid", customerRelationshipId));
            } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(hierarchyIndicator)) {
                query.add(RestrictionsFactoryUtil.eq("relationshipBuilderSid", productRelationshipId));
            }
            ProjectionList projectionListFrom = ProjectionFactoryUtil.projectionList();
            projectionListFrom.add(ProjectionFactoryUtil.property(Constant.LEVELNAME));
            projectionListFrom.add(ProjectionFactoryUtil.property("levelNo"));
            projectionListFrom.add(ProjectionFactoryUtil.property(Constant.HIERARACHY_NO));
            query.setProjection(ProjectionFactoryUtil.distinct(projectionListFrom));
            list = commonDao.getRelationshipLevels(query);
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
        return list;
    }

    public int getFrequencyNumber(String frequency) {
        int frequencyNo = NumericConstants.THREE;
        if (frequency.equals(QUARTERLY.getConstant())) {
            frequencyNo = NumericConstants.THREE;
        } else if (frequency.equals(SEMI_ANNUAL.getConstant())) {
            frequencyNo = NumericConstants.SIX;
        } else if (frequency.equals(MONTHLY.getConstant())) {
            frequencyNo = 1;
        } else if (frequency.equals(ANNUAL.getConstant())) {
            frequencyNo = NumericConstants.TWELVE;
        }
        return frequencyNo;
    }

    /**
     * Contains the method to run the Government Discount Insert Procedure in a
     * separate thread.
     *
     * @param projectionSelectionDTO
     * @return
     */
    private Runnable createDiscountProcedureRunnable(final ProjectionSelectionDTO projectionSelectionDTO) {
        Runnable discountProcedureRunnable = new Runnable() {

            @Override
            public void run() {
                Object[] orderedArgs = {projectionSelectionDTO.getProjectionId(), projectionSelectionDTO.getUserId(), "SPAP", projectionSelectionDTO.getSessionId(), 0};
                CommonLogic.callProcedureforUpdate("PRC_M_DISCOUNT_INSERT", orderedArgs);
            }
        };
        return discountProcedureRunnable;
    }

    /**
     * Used to return the Sales Return Count
     *
     * @param projectionSelectionDTO
     * @return
     */
    private int getReturnsCount(final ProjectionSelectionDTO projectionSelectionDTO) {
        int count = 0;
        String query = SQlUtil.getQuery("RETURNS_SALES_QUERY").replace("@PROJECTION_SID", String.valueOf(projectionSelectionDTO.getProjectionId()))
                .replace(Constant.LEVEL_NO1, String.valueOf(projectionSelectionDTO.getLevelNo()));
        if (StringUtils.isNotBlank(projectionSelectionDTO.getHierarchyNo()) && StringUtils.isBlank(projectionSelectionDTO.getLevelFilterValue())) {
            query += " AND RLD.HIERARCHY_NO LIKE '" + projectionSelectionDTO.getHierarchyNo() + "%'";
        }
        try {
            List list = (List) salesAllocationDAO.executeSelectQuery(query);
            
            if (list != null && !list.isEmpty()) {
                String[] hierarchyArr = new String[list.size()];
                for (int i = 0; i < list.size(); i++) {
                    Object[] ob = (Object[]) list.get(i);
                    hierarchyArr[i] = ob[0].toString();
                    projectionSelectionDTO.setLevelName(ob[NumericConstants.TWO].toString());
                }
                count = list.size();
                projectionSelectionDTO.setReHierarchyNo(Arrays.toString(hierarchyArr).replace("[", StringUtils.EMPTY).replace("]", StringUtils.EMPTY));
            }
        } catch (PortalException ex) {
            LOGGER.debug("Query Error--> " + query);
            LOGGER.error(ex);
        } catch (Exception ex) {
            LOGGER.debug("Query Error--> " + query);
            LOGGER.error(ex);
        }
        return count;
    }

    /**
     * Used to return the Sales Results(load Data)
     *
     * @param projSelDTO
     * @param start
     * @param offset
     * @return
     */
    public List<SalesRowDto> getReturnsSalesResults(ProjectionSelectionDTO projSelDTO, int start, int offset) {
        LOGGER.debug("Load Data ---->>  start" + start + " --- offset" + offset + " HierarchyNo-> " + projSelDTO.getReHierarchyNo() + " projSelDTO.getHierarchyNo()" + projSelDTO.getHierarchyNo());
        LOGGER.debug("@USER_ID    " + projSelDTO.getUserId());
        LOGGER.debug("@SESSION_ID " + projSelDTO.getSessionId());
        LOGGER.debug("@LEVEL_NO   " + projSelDTO.getLevelNo());
        LOGGER.debug("@RowsPerLevelItem   " + projSelDTO.getRowsPerLevelItem());
        List list = new ArrayList();
        String queryResult;
        String queryName = Constant.VIEW.equals(projSelDTO.getSessionDTO().getAction()) ? "RETURNS_SALES_QUERY_RESULTS_VIEW" : "RETURNS_SALES_QUERY_RESULTS";
        String frequency = StringUtils.EMPTY;
        String returnDetailsSID = StringUtils.EMPTY;
        Map<String, String> returnMap = projSelDTO.getSessionDTO().getReturnsDetailsMap();
        for (Map.Entry<String, String> entr : returnMap.entrySet()) {
            if (entr.getKey().contains(projSelDTO.getHierarchyNo())) {
                returnDetailsSID += entr.getValue() + ",";
            }
        }
        LOGGER.debug("ReturnDetailsSID " + returnDetailsSID);
        StringBuilder query = new StringBuilder();
        query.append(SQlUtil.getQuery(queryName));
        queryResult = query.toString().replace("@PROJECTION_SID", String.valueOf(projSelDTO.getProjectionId()));
        queryResult = queryResult.replace("@RD_SID", StringUtils.isEmpty(returnDetailsSID) ? "RETURNS_DETAILS_SID LIKE '%'" : "RETURNS_DETAILS_SID IN (" + returnDetailsSID.substring(0, returnDetailsSID.lastIndexOf(",")) + ")");
        queryResult = queryResult.replace(Constant.LEVEL_NO1, String.valueOf(projSelDTO.getLevelNo()));

        if (projSelDTO.getFrequencyDivision() == 1) {
            queryResult = queryResult.replace(Constant.AT_FREQUENCY_AT, "1 AS 'YEARSS'");
            queryResult = queryResult.replace(Constant.FREQUENCY_DIVISION, "12");
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            frequency = " ,P.SEMI_ANNUAL ";
            queryResult = queryResult.replace(Constant.AT_FREQUENCY_AT, "P.SEMI_ANNUAL");
            queryResult = queryResult.replace(Constant.FREQUENCY_DIVISION, "6");
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            frequency = " ,P.QUARTER";
            queryResult = queryResult.replace(Constant.AT_FREQUENCY_AT, "P.QUARTER");
            queryResult = queryResult.replace(Constant.FREQUENCY_DIVISION, "3");
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            frequency = ",P.MONTH";
            queryResult = queryResult.replace(Constant.AT_FREQUENCY_AT, "P.MONTH");
            queryResult = queryResult.replace(Constant.FREQUENCY_DIVISION, Constant.STRING_ONE);
        }
        queryResult += frequency + " ORDER BY  RT_M.HIERARCHY_NO ";
        queryResult += "OFFSET " + (start * projSelDTO.getRowsPerLevelItem()) + Constant.ROWS_FETCH_NEXT_SPACE + (offset * projSelDTO.getRowsPerLevelItem()) + " ROWS ONLY ";
        try {
            list = (List) HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(queryResult, projSelDTO.getSessionDTO().getCurrentTableNames()));
        } catch (Exception e) {
            LOGGER.debug("Query Error-->  " + queryResult);
            LOGGER.error(e);
        }
        return convertReturnsSalesResults(projSelDTO, list);
    }

    /**
     * Used to convert List into SalesRowDto List
     *
     * @param projSelDTO
     * @param list
     * @return
     */
    public List<SalesRowDto> convertReturnsSalesResults(ProjectionSelectionDTO projSelDTO, final List resulList) {
        List<SalesRowDto> salesRowList = new ArrayList<>();
        SalesRowDto salesRowDto = new SalesRowDto();
        salesRowDto.setHierarchyNo(StringUtils.EMPTY);
        for (int i = 0; i < resulList.size(); i++) {
            Object obj[] = (Object[]) resulList.get(i);
            if (StringUtils.isNotBlank(salesRowDto.getHierarchyNo()) && obj[NumericConstants.TWO] != null && !obj[NumericConstants.TWO].equals(salesRowDto.getHierarchyNo())) {
                salesRowList.add(salesRowDto);
                salesRowDto = new SalesRowDto();
            }
            salesRowDto.setHierarchyNo(obj[NumericConstants.TWO].toString());
            salesRowDto.setLevelName(projSelDTO.getSessionDTO().getLevelValueDiscription(obj[NumericConstants.TWO].toString(), Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY));

            salesRowDto.setLevelNo(projSelDTO.getLevelNo());
            salesRowDto.setTreeLevelNo(projSelDTO.getLevelNo());
            salesRowDto.setReturnDetailsSid(projSelDTO.getSessionDTO().getReturnsDetailsMap().get(obj[NumericConstants.TWO].toString()));
            salesRowDto.setReHierarchyNo(projSelDTO.getReHierarchyNo());
            salesRowDto.setCcpCount(salesRowDto.getReturnDetailsSid() != null ? String.valueOf(salesRowDto.getReturnDetailsSid().split(",").length) : StringUtils.EMPTY);

            if (projSelDTO.isIsFilter() || (Constant.PRODUCT_LABEL.equals(projSelDTO.getLevelName()) || "Item".equals(projSelDTO.getLevelName()) || Constant.NDC.equals(projSelDTO.getLevelName()))) { //Removed condition for GAL-8970
                salesRowDto.setLog(obj[NumericConstants.FOUR] == null ? StringUtils.EMPTY : obj[NumericConstants.FOUR].toString());
                salesRowDto.setClosedDate(obj[NumericConstants.FIVE] == null ? StringUtils.EMPTY : obj[NumericConstants.FIVE].toString());
                salesRowDto.setLoeDate(obj[NumericConstants.SIX] == null ? StringUtils.EMPTY : obj[NumericConstants.SIX].toString());
                salesRowDto.setParent(0);
            } else {
                salesRowDto.setParent(1);
            }
            salesRowDto.setUncheckCount(Integer.valueOf(obj[NumericConstants.FIFTEEN].toString()));
            salesRowDto.setProductHierarchyNo(obj[NumericConstants.TWO].toString());
            salesRowDto.setHierarchyIndicator(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);

            int frequencyDivision = projSelDTO.getFrequencyDivision();

            String key = Constant.Q_SMALL + String.valueOf(obj[0]) + "-" + String.valueOf(obj[1]);
            if (frequencyDivision == 1) {
                key = String.valueOf(obj[1]);
            } else if (frequencyDivision == NumericConstants.FOUR) {
                key = Constant.Q_SMALL + String.valueOf(obj[0]) + "-" + String.valueOf(obj[1]);
            } else if (frequencyDivision == NumericConstants.TWO) {
                key = Constant.S_SMALL + String.valueOf(obj[0]) + "-" + String.valueOf(obj[1]);
            } else if (frequencyDivision == NumericConstants.TWELVE) {
                String monthName = getMonthForInt(Integer.valueOf(String.valueOf(obj[0])) - 1);
                key = monthName.toLowerCase() + "-" + String.valueOf(obj[1]);
            }
            salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-ActualReturned%", String.valueOf(UNIT.format(obj[NumericConstants.SEVEN] == null ? 0 : obj[NumericConstants.SEVEN])) + Constant.PERCENT);
            salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-ActualRPU", String.valueOf(MONEY.format(obj[NumericConstants.EIGHT] == null ? 0 : obj[NumericConstants.EIGHT])));
            salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-ActualReturnedAmount", String.valueOf(MONEYNODECIMAL.format(obj[NumericConstants.NINE] == null ? 0 : obj[NumericConstants.NINE])));
            if (!projSelDTO.getCommonColumn().isEmpty()) {
                for (String col : projSelDTO.getCommonColumn()) {
                    if (col.contains(key)) {
                        salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-HistoryProjectedReturn%", String.valueOf(UNIT.format(obj[NumericConstants.TEN] == null ? 0 : obj[NumericConstants.TEN])) + Constant.PERCENT);
                        salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-HistoryProjectedRPU", String.valueOf(MONEY.format(obj[NumericConstants.ELEVEN] == null ? 0 : obj[NumericConstants.ELEVEN])));
                        salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-HistoryProjectedReturnAmount", String.valueOf(MONEYNODECIMAL.format(obj[NumericConstants.TWELVE] == null ? 0 : obj[NumericConstants.TWELVE])));
                        salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-HistoryGrowthRate", String.valueOf(UNIT.format(obj[NumericConstants.THIRTEEN] == null ? 0 : obj[NumericConstants.THIRTEEN])) + Constant.PERCENT);
                    } else {
                        salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-ProjectedReturn%", String.valueOf(UNIT.format(obj[NumericConstants.TEN] == null ? 0 : obj[NumericConstants.TEN])) + Constant.PERCENT);
                        salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-ProjectedRPU", String.valueOf(MONEY.format(obj[NumericConstants.ELEVEN] == null ? 0 : obj[NumericConstants.ELEVEN])));
                        salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-ProjectedReturnAmount", String.valueOf(MONEYNODECIMAL.format(obj[NumericConstants.TWELVE] == null ? 0 : obj[NumericConstants.TWELVE])));
                        salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-GrowthRate", String.valueOf(UNIT.format(obj[NumericConstants.THIRTEEN] == null ? 0 : obj[NumericConstants.THIRTEEN])) + Constant.PERCENT);
                    }
                }
            } else {
                salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-ProjectedReturn%", String.valueOf(UNIT.format(obj[NumericConstants.TEN] == null ? 0 : obj[NumericConstants.TEN])) + Constant.PERCENT);
                salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-ProjectedRPU", String.valueOf(MONEY.format(obj[NumericConstants.ELEVEN] == null ? 0 : obj[NumericConstants.ELEVEN])));
                salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-ProjectedReturnAmount", String.valueOf(MONEYNODECIMAL.format(obj[NumericConstants.TWELVE] == null ? 0 : obj[NumericConstants.TWELVE])));
                salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-GrowthRate", String.valueOf(UNIT.format(obj[NumericConstants.THIRTEEN] == null ? 0 : obj[NumericConstants.THIRTEEN])) + Constant.PERCENT);
            }
            salesRowDto.addBooleanProperties(Constant.CHECK, Integer.parseInt(String.valueOf(obj[NumericConstants.FOURTEEN])) == 0 ? new Boolean(false) : new Boolean(true));
            if (i == (resulList.size() - 1)) {
                salesRowList.add(salesRowDto);
            }
        }
        return salesRowList;
    }

    public void saveReturnsSalesProjection(SessionDTO sessionDTO) throws PortalException {
        String saveQuery = StringUtils.EMPTY;
        try {
            saveQuery = SQlUtil.getQuery("RETURN_MAIN_TABLE_INSERT");
            salesAllocationDAO.executeUpdateQuery(QueryUtil.replaceTableNames(saveQuery, sessionDTO.getCurrentTableNames()));

        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    public int queryToUpdateCheckRecord(SessionDTO sessionDTO, int checkValue, boolean isUpdate, String returnDetailsSid) {
        StringBuilder queryBuilder = new StringBuilder();
        int count = 0;
        try {
            if (isUpdate) {
                queryBuilder.append(" Update ST_RETURNS_PROJ_MASTER SET CHECK_RECORD =" + checkValue + StringUtils.EMPTY);
                if (StringUtils.isNotBlank(returnDetailsSid)) {
                    queryBuilder.append(" WHERE  RETURNS_DETAILS_SID IN (" + returnDetailsSid + ")");
                }
                List list = salesAllocationDAO.executeUpdateQuery(QueryUtil.replaceTableNames(queryBuilder.toString(), sessionDTO.getCurrentTableNames()));
                if (!list.isEmpty()) {
                    count = Integer.parseInt(String.valueOf(list.get(0)));
                }
            }
        } catch (PortalException ex) {
            LOGGER.debug("queryToUpdateCheckRecord---> " + queryBuilder.toString());
            LOGGER.error(ex);
        } catch (Exception ex) {
            LOGGER.debug("queryToUpdateCheckRecord---> " + queryBuilder.toString());
            LOGGER.error(ex);
        }
        return count;
    }

    public boolean calculateReturnsProjection(final ProjectionSelectionDTO projectionSelectionDTO, final String methodology, final String calcPeriods,
            final String startPeriodSID, final String endPeriodSID) {
        boolean isSalesCalculated = false;
        try {
            LOGGER.debug("calculateReturnsProjection starts ");
            saveReturnsSelectionForCalculation(projectionSelectionDTO, methodology, calcPeriods, startPeriodSID, endPeriodSID);
            projectionSelectionDTO.setTabName("Returns");
            if ("Rolling Annual Trend".equals(methodology) || Constant.SINGLE_PERIOD.equals(methodology) || "Average".equals(methodology)) {
                cumulativeCalculation(projectionSelectionDTO, calcPeriods, methodology, "st_returns_growth_factor_");
            }

            DataSelectionLogic dsLogic = new DataSelectionLogic();
            isSalesCalculated = dsLogic.callReturnsCalculateProcedure(projectionSelectionDTO.getProjectionId(), String.valueOf(projectionSelectionDTO.getUserId()), String.valueOf(projectionSelectionDTO.getSessionDTO().getSessionId()), String.valueOf(projectionSelectionDTO.getFrequency()), SalesUtils.RETURNS_SALES_CALCULATE_PRO_NAME);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.debug("calculateReturnsProjection ends ");
        return isSalesCalculated;

    }

    public void saveReturnsSelectionForCalculation(final ProjectionSelectionDTO projectionSelectionDTO, final String methodology, final String calcPeriods,
            final String startPeriodSID, final String endPeriodSID) throws PortalException, SystemException {

        String updateQuery = "UPDATE ST_RETURNS_PROJ_MASTER   SET METHODOLOGY= '" + methodology + "',"
                + " CALCULATION_PERIODS= '" + calcPeriods + "',"
                + " METHODOLOGY_START_DATE='" + startPeriodSID + "',"
                + " METHODOLOGY_END_DATE='" + (StringUtils.isEmpty(endPeriodSID) ? Constant.NULL_CAPS : endPeriodSID) + "'"
                + " WHERE CHECK_RECORD = 1";
        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        salesProjectionDAO.executeUpdateQuery(QueryUtil.replaceTableNames(updateQuery, projectionSelectionDTO.getSessionDTO().getCurrentTableNames()));

    }

    public void callRefreshProcedure(int projectionId, String selectedItems, String refreshedPeriods, String userId, String sessionId, boolean flag) {

        LOGGER.debug("In callRefreshProcedure starts");
        LOGGER.debug("PRC---> PRC_RETURNS_REFRESH");
        LOGGER.debug("selectedItems---> " + selectedItems);
        LOGGER.debug("refreshedPeriods---> " + refreshedPeriods);
        LOGGER.debug("ProjectionId----> " + projectionId);
        LOGGER.debug("UserId----------> " + userId);
        LOGGER.debug("SessionId-------> " + sessionId);

        final DataSourceConnection dataSourceConnection = DataSourceConnection.getInstance();
        Connection connection = null;
        CallableStatement statement = null;
        try {
            connection = dataSourceConnection.getConnection();

            if (connection != null) {

                statement = connection.prepareCall("{call PRC_RETURNS_REFRESH (?,?,?,?,?,?)}");

                statement.setObject(1, projectionId); //  @PROJECTION_SID
                statement.setObject(NumericConstants.TWO, selectedItems);
                statement.setObject(NumericConstants.THREE, refreshedPeriods);
                statement.setObject(NumericConstants.FOUR, flag);
                statement.setObject(NumericConstants.FIVE, userId); //  @USER_ID
                statement.setObject(NumericConstants.SIX, sessionId); //  @SESSION_ID

                statement.execute();
            }

        } catch (Exception ex) {
            LOGGER.error(ex);
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }
        LOGGER.debug("In callRefreshProcedure ends");
    }

    private List<Map> getActiveExFactorySalesAndUnits(ProjectionSelectionDTO projectionSelectionDTO, final int year, final int period) throws PortalException, SystemException {
        List<Map> mapList = new ArrayList<>();
        Map<String, Double> unitsMap = new TreeMap<>();
        Map<String, Double> salesMap = new TreeMap<>();
        String query = SQlUtil.getQuery("SALES_AMOUNT_QUERY").replace("@RETURN_SID", String.valueOf(projectionSelectionDTO.getSessionDTO().getDetailsSID()))
                .replace(Constant.USER_ID1_AT, String.valueOf(projectionSelectionDTO.getUserId()))
                .replace(Constant.SESSION_ID1_AT, String.valueOf(projectionSelectionDTO.getSessionId()))
                .replace(Constant.YEAR1_AT, String.valueOf(year));
        query = addFrequencyInQuery(projectionSelectionDTO.getFrequencyDivision(), period, query);
        List resultsList = (List) salesAllocationDAO.executeSelectQuery(query);
        for (int i = 0; i < resultsList.size(); i++) {
            Object[] ob = (Object[]) resultsList.get(i);
            salesMap.put(ob[0] == null ? StringUtils.EMPTY : ob[0].toString(), ob[1] == null ? 0.0 : Double.valueOf(ob[1].toString()));
            unitsMap.put(ob[0] == null ? StringUtils.EMPTY : ob[0].toString(), ob[NumericConstants.TWO] == null ? 0.0 : Double.valueOf(ob[NumericConstants.TWO].toString()));
        }
        mapList.add(salesMap);
        mapList.add(unitsMap);
        return mapList;
    }

    /**
     * Summing the sales amount and multiplying with entered RPU to find the
     * Return Amount
     *
     * @param salesAmount
     * @param value
     * @param editedValue
     * @return return amount
     */
    private double calculateSalesAmouintForRPUValue(final Map<String, Double> unitsMap, final String value, final String editedValue) {
        LOGGER.debug("Entered Value--> " + editedValue);
        double amount = 0.0;
        String[] str = value.split(",");
        for (int i = 0; i < str.length; i++) {
            if (unitsMap.containsKey(str[i])) {
                amount += unitsMap.get(str[i]);
            }
        }
        amount = amount * Integer.valueOf(editedValue);
        return amount;
    }

    /**
     * Finding Ratio to calculate RPU
     *
     * @param salesAmount
     * @param value
     * @param amount
     * @return
     */
    private double getRPUValue(Map<String, Double> salesAmount, final Map.Entry<String, String> entry, Map<String, Double> calculatedAmount, final ProjectionSelectionDTO projectionSelectionDTO) {
        String parentDetailsSid = projectionSelectionDTO.getSessionDTO().getReturnsDetailsMap().get(entry.getKey().substring(0, entry.getKey().length() - NumericConstants.TWO));

        String hierarchy = entry.getKey();
        LOGGER.debug("ParentDetailsSid-->>  " + parentDetailsSid);
        LOGGER.debug("currentDetailsSid-->> " + entry.getValue());
        LOGGER.debug("hierarchy-->>         " + hierarchy);
        //Formula:(A)/SUM(B)*AMOUNT
        //Code to calculate A
        double amount = calculatedAmount.get(hierarchy.substring(0, hierarchy.length() - NumericConstants.TWO));
        double amountA = 0.0;
        for (String split : entry.getValue().split(",")) {
            if (salesAmount.containsKey(split)) {
                amountA += salesAmount.get(split);
            }
        }
        //Code to calculate B
        double amountB = 0.0;
        for (String split : parentDetailsSid.split(",")) {
            if (salesAmount.containsKey(split)) {
                amountB += salesAmount.get(split);
            }
        }
        LOGGER.debug("amountA-->> " + amountA);
        LOGGER.debug("amountB-->> " + amountB);
        LOGGER.debug("amount " + amount);

        amount = (amountA / amountB) * amount;
        boolean flag = Double.isNaN(amount);
        if (flag) {
            amount = 0.0;
        }
        return amount;
    }

    private String updateQuery(String saveQuery, double rpu, final String entryValue) {
        LOGGER.debug("RPU " + rpu + " ReturnDetailsSid--> " + entryValue);
        saveQuery = saveQuery.replace(Constant.USER_ENTERED_VALUE, StringUtils.EMPTY + (Double.isNaN(rpu) ? 0.0 : rpu));
        saveQuery = saveQuery.replace(Constant.RETURNS_DETAILS_SID_AT, entryValue);
        return saveQuery;
    }

    private List<Map> getActiveExFactorySalesAndUnitsForMassUpdate(ProjectionSelectionDTO projectionSelectionDTO, String periodQuery, String frequency) throws PortalException, SystemException {
        List<Map> mapList = new ArrayList<>();
        Map<String, Map<String, Double>> salesMap = new TreeMap<>();
        Map<String, Map<String, Double>> unitsMap = new TreeMap<>();
        Map<String, Double> units = new TreeMap<>();
        Map<String, Double> sales = new TreeMap<>();
        String query = SQlUtil.getQuery("SALES_AMOUNT_QUERY_MASS_UPDATE").replace("@RETURN_SID", String.valueOf(projectionSelectionDTO.getSessionDTO().getDetailsSID()))
                .replace(Constant.USER_ID1_AT, String.valueOf(projectionSelectionDTO.getUserId()))
                .replace(Constant.SESSION_ID1_AT, String.valueOf(projectionSelectionDTO.getSessionId()))
                .replace("@PERIOD_QUERY", periodQuery)
                .replace("@FREQUENCY_SELECTION", projectionSelectionDTO.getFrequencyDivision() == 1 ? "0 as period, " : frequency)
                .replace(Constant.FREQUENCY1_AT, frequency);
        if (StringUtils.isNotBlank(frequency)) {
            query += " order by " + (frequency.substring(0, frequency.length() - 1));
        } else {
            query += " ORDER BY P.YEAR";
        }
        query = QueryUtil.replaceTableNames(query, projectionSelectionDTO.getSessionDTO().getCurrentTableNames());//For GAL-9131
        List resultsList = (List) salesAllocationDAO.executeSelectQuery(query);
        int year = 0;
        int period = 0;
        if (resultsList != null) {
            for (int i = 0; i < resultsList.size(); i++) {
                Object[] ob = (Object[]) resultsList.get(i);
                if (year == 0) {
                    period = Integer.valueOf(ob[0].toString());
                    year = Integer.valueOf(ob[1].toString());
                } else if (period != Integer.valueOf(ob[0].toString()) || year != Integer.valueOf(ob[1].toString())) {
                    salesMap.put(year + "," + period, sales);
                    unitsMap.put(year + "," + period, units);
                    sales = new TreeMap<>();
                    units = new TreeMap<>();
                    year = Integer.valueOf(ob[1].toString());
                    period = Integer.valueOf(ob[0].toString());
                }
                sales.put(ob[NumericConstants.TWO] == null ? StringUtils.EMPTY : ob[NumericConstants.TWO].toString(), ob[NumericConstants.THREE] == null ? 0.0 : Double.valueOf(ob[NumericConstants.THREE].toString()));
                units.put(ob[NumericConstants.TWO] == null ? StringUtils.EMPTY : ob[NumericConstants.TWO].toString(), ob[NumericConstants.FOUR] == null ? 0.0 : Double.valueOf(ob[NumericConstants.FOUR].toString()));
                if (i == (resultsList.size() - 1)) {
                    salesMap.put(year + "," + period, sales);
                    unitsMap.put(year + "," + period, units);
                }
            }
        }
        mapList.add(salesMap);
        mapList.add(unitsMap);
        return mapList;
    }

    private String addFrequencyInQuery(int frequencyDivision, int period, String query) {
        switch (frequencyDivision) {
            case 1:
                query = query.replace(Constant.FREQUENCY1_AT, StringUtils.EMPTY);
                break;
            case NumericConstants.TWELVE:
                query = query.replace(Constant.FREQUENCY1_AT, " AND MONTH = " + period);
                break;
            case NumericConstants.FOUR:
                query = query.replace(Constant.FREQUENCY1_AT, " AND QUARTER = " + period);
                break;
            case NumericConstants.TWO:
                query = query.replace(Constant.FREQUENCY1_AT, " AND SEMI_ANNUAL = " + period);
                break;
        }
        return query;
    }

    private String calculationLogic(final ProjectionSelectionDTO projectionSelectionDTO, final String hierarchyNo, final String enteredValue, String query, final Map<String, Double> salesAmount, final Map<String, Double> unitsMap) {
        //This Map contains the updated Projection Return Amount for each hierarchy
        Map<String, Double> calculatedAmount = new HashMap<>();
        boolean enteredLevel = true;
        double amount;
        String bulkQuery = StringUtils.EMPTY;
        Map<String, String> returnsDetailsMap = new TreeMap<>(projectionSelectionDTO.getSessionDTO().getReturnsDetailsMap());
        for (Map.Entry<String, String> entry : returnsDetailsMap.entrySet()) {
            if (entry.getKey().contains(hierarchyNo)) {
                if (enteredLevel) {
                    amount = calculateSalesAmouintForRPUValue(unitsMap, entry.getValue(), enteredValue);
                    calculatedAmount.put(entry.getKey(), amount);
                    LOGGER.debug("INITAIL Amount " + amount);
                    enteredLevel = false;
                } else {
                    amount = getRPUValue(salesAmount, entry, calculatedAmount, projectionSelectionDTO);
                    calculatedAmount.put(entry.getKey(), amount);
                }
                LOGGER.debug("RESULTS:  Hierarchy--> " + entry.getKey() + " ReturnDetailsSid--> " + entry.getValue() + " Amount= " + amount);
                if (entry.getValue().split(",").length == 1) {
                    LOGGER.debug("Amount Value -> " + amount);
                    LOGGER.debug("Units Value -> " + unitsMap.get(entry.getValue()));
                    bulkQuery += updateQuery(query, amount / unitsMap.get(entry.getValue()), entry.getValue());
                }
            }
        }
        return bulkQuery;
    }

    public List<Integer> headerCheckALLQuery(SessionDTO sessionDTO, int checkValue, boolean isUpdate) {
        StringBuilder queryBuilder = new StringBuilder();
        try {
            if (isUpdate) {
                queryBuilder.append(" Update ST_RETURNS_PROJ_MASTER SET CHECK_RECORD =" + checkValue + " ");
                salesAllocationDAO.executeUpdateQuery(QueryUtil.replaceTableNames(queryBuilder.toString(), sessionDTO.getCurrentTableNames()));
            } else {
                queryBuilder.append(" SELECT DISTINCT CHECK_RECORD from ST_RETURNS_PROJ_MASTER where  (CHECK_RECORD IS NOT NULL OR CHECK_RECORD <> '')");

                return (List<Integer>) salesAllocationDAO.executeSelectQuery(QueryUtil.replaceTableNames(queryBuilder.toString(), sessionDTO.getCurrentTableNames()));
            }
        } catch (PortalException ex) {
            LOGGER.error(ex);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return Collections.emptyList();
    }

    public List<SalesRowDto> configureLevels(int start, int offset, int started, ProjectionSelectionDTO projSelDTO) {
        int neededRecord = offset;

        List<SalesRowDto> resultList = new ArrayList<>();
        if (projSelDTO.isIsProjectionTotal() && Constant.ALTERNATE_HISTORY.equals(projSelDTO.getFunctionality())) {
            SalesRowDto dto = new SalesRowDto();
            String commonColumn;
            String column;
            String value;
            String endDate = String.format("%04d", projSelDTO.getEndYear()) + "-"
                    + String.format("%02d", projSelDTO.getEndMonth()) + "-"
                    + String.format("%02d", projSelDTO.getEndDay());

            String startDate = String.format("%04d", projSelDTO.getStartYear()) + "-"
                    + String.format("%02d", projSelDTO.getStartMonth()) + "-"
                    + String.format("%02d", projSelDTO.getStartDay());
            Object[] orderedArgs = {projSelDTO.getProjectionId(), projSelDTO.getFrequency(), "PIVOT", projSelDTO.getSessionDTO().getUserId(), projSelDTO.getSessionDTO().getSessionId(),
                projSelDTO.getStartMonth(), projSelDTO.getStartYear(), projSelDTO.getEndMonth(), projSelDTO.getEndYear(), startDate, endDate};
            List<Object[]> list = CommonLogic.callProcedure("PRC_PIVOT_PERIOD_RESULT", orderedArgs);
            dto.setLevelName("Total Alternate History");
            for (Object rows : list) {
                final Object[] row = (Object[]) rows;
                commonColumn = "uv";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[0];
                    value = getFormattedValue(PROJECTEDUNITDECIMAL, value);
                    dto.addStringProperties(column, value);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[1];
                    value = getFormattedValue(PROJECTEDUNITDECIMAL, value);
                    dto.addStringProperties(column, value);
                }
            }
            dto.setParent(0);
            resultList.add(dto);
        }
        if (neededRecord > 0) {
            List<Leveldto> levelList = getConditionalLevelList(projSelDTO.getProjectionId(), start, offset, projSelDTO.getHierarchyIndicator(), projSelDTO.getTreeLevelNo(), projSelDTO.getHierarchyNo(), projSelDTO.getProductHierarchyNo(), projSelDTO.getCustomerHierarchyNo(), projSelDTO.isIsFilter(), false, projSelDTO.isIsCustomHierarchy(), projSelDTO.getCustomId(), projSelDTO.getGroupFilter(), projSelDTO.getUserId(), projSelDTO.getSessionId(), projSelDTO.getCustRelationshipBuilderSid(), projSelDTO.getProdRelationshipBuilderSid(), false, true, projSelDTO.getSessionDTO());

            for (int i = 0; i < levelList.size() && neededRecord > 0; neededRecord--, i++) {
                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + (started + i))) {
                    Leveldto levelDto = levelList.get(i);

                    SalesRowDto dto = new SalesRowDto();
                    dto.setLevelNo(levelDto.getLevelNo());
                    dto.setTreeLevelNo(levelDto.getTreeLevelNo());
                    dto.setParentNode(levelDto.getParentNode());
                    dto.setGroup(projSelDTO.getSessionDTO().getLevelValueDiscription(levelDto.getHierarchyNo(), levelDto.getHierarchyIndicator()));
                    dto.setLevelValue(projSelDTO.getSessionDTO().getLevelValueDiscription(levelDto.getHierarchyNo(), levelDto.getHierarchyIndicator()));
                    dto.setLevelName(projSelDTO.getSessionDTO().getLevelValueDiscription(levelDto.getHierarchyNo(), levelDto.getHierarchyIndicator()));
                    dto.setHierarchyNo(levelDto.getHierarchyNo());
                    dto.setHierarchyIndicator(levelDto.getHierarchyIndicator());
                    if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
                        dto.setCustomerHierarchyNo(dto.getHierarchyNo());
                        dto.setProductHierarchyNo(projSelDTO.getProductHierarchyNo());
                    } else if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
                        dto.setProductHierarchyNo(dto.getHierarchyNo());
                        dto.setCustomerHierarchyNo(projSelDTO.getCustomerHierarchyNo());
                    }
                    dto.setOnExpandTotalRow(1);
                    dto.setParent(1);
                    resultList.add(dto);
                }
                started++;
            }
        }
        return resultList;
    }

    public String getFormattedValue(DecimalFormat FORMAT, String value) {
        if (value.contains(Constant.NULL)) {
            value = SPRDASH.getConstant();
        } else {
            value = FORMAT.format(Double.valueOf(value));
        }
        return value;
    }

    public List<SalesRowDto> getSalesProjectionPivotResults(int start, int offset, ProjectionSelectionDTO projSelDTO) {
        int neededRecord = offset;
        int started = start;
        int mayBeAdded = 0;

        List<SalesRowDto> projDTOList = new ArrayList<>();
        if ((!projSelDTO.getLevelValue().startsWith(Constant.ALL)
                && !projSelDTO.getLevelValue().contains(Constant.SALES)) && (neededRecord > 0)) {
            int mayBeAddedRecord = started - mayBeAdded;
            if (mayBeAddedRecord < 0) {
                mayBeAddedRecord = 0;
            }
            if (mayBeAddedRecord < projSelDTO.getPeriodList().size()) {
                List<SalesRowDto> projectionDtoList;
                projSelDTO.setProjTabName("SPR");
                projectionDtoList = getProjectionPivot(projSelDTO);
                projSelDTO.setProjTabName(StringUtils.EMPTY);
                for (int k = mayBeAddedRecord; k < projectionDtoList.size() && neededRecord > 0; neededRecord--, k++) {
                    if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + k)) {
                        projDTOList.add(projectionDtoList.get(k));
                    }
                    started++;
                }
            }
            mayBeAdded += projSelDTO.getPeriodList().size();
        }

        if (neededRecord > 0 && !projSelDTO.isIsFilter()) {
            if ((projSelDTO.getTreeLevelNo() + 1) == projSelDTO.getTpLevel()
                    && ((projSelDTO.isIsCustomHierarchy()) || (!projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)))
                    && !projSelDTO.getLevelValue().startsWith(Constant.ALL)
                    && !projSelDTO.getLevelValue().contains(Constant.SALES)) {
                if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + started)) {
                    SalesRowDto dto = new SalesRowDto();
                    dto.setLevelNo(projSelDTO.getLevelNo());
                    dto.setTreeLevelNo(projSelDTO.getTreeLevelNo());
                    dto.setParentNode(projSelDTO.getParentNode());
                    dto.setGroup(projSelDTO.getGroupFilter());
                    dto.setLevelValue(projSelDTO.getGroupFilter());
                    dto.setLevelName(projSelDTO.getGroupFilter());
                    dto.setHierarchyIndicator(projSelDTO.getHierarchyIndicator());
                    dto.setHierarchyNo(projSelDTO.getHierarchyNo());
                    if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
                        dto.setCustomerHierarchyNo(dto.getHierarchyNo());
                        dto.setProductHierarchyNo(projSelDTO.getProductHierarchyNo());
                    } else if (dto.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
                        dto.setProductHierarchyNo(dto.getHierarchyNo());
                        dto.setCustomerHierarchyNo(projSelDTO.getCustomerHierarchyNo());
                    }
                    dto.setOnExpandTotalRow(1);
                    dto.setParent(1);
                    projDTOList.add(dto);
                }
            } else {
                int mayBeAddedRecord = started - mayBeAdded;
                if (mayBeAddedRecord < 0) {
                    mayBeAddedRecord = 0;
                }
                projSelDTO.setTreeLevelNo(projSelDTO.getTreeLevelNo() + 1);
                List<SalesRowDto> nextLevelValueList = configureLevels(mayBeAddedRecord, neededRecord, started, projSelDTO);
                projDTOList.addAll(nextLevelValueList);
            }
        }
        return projDTOList;
    }

    public List<SalesRowDto> getProjectionPivot(ProjectionSelectionDTO projSelDTO) {
        List<SalesRowDto> projDTOList = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        if (projSelDTO.getScreenName().equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
            LOGGER.debug("Entering getProjection Pivot NonMandated");
            builder.append(commonLogic.insertAvailableHierarchyNo(projSelDTO))
                    .append(SQlUtil.getQuery("alternate-join-loaddata"))
                    .append(SQlUtil.getQuery("alternate-histroy-period-date").replace("?F", String.valueOf(projSelDTO.getFrequency().charAt(0))))
                    .append(SQlUtil.getQuery("alternate-histroy-sales-summary-pivot").replace("@PERIOD_DATE", CommonLogic.getPeriodRestrictionQuery(projSelDTO)));
            List<Object> gtsList = (List<Object>) CommonLogic.executeSelectQuery(QueryUtil.replaceTableNames(builder.toString(), projSelDTO.getSessionDTO().getCurrentTableNames()), null, null);
            projDTOList = getCustomizedProjectionPivot(gtsList, projSelDTO);
            LOGGER.debug("Ending getProjection Pivot NonMandated");
        } else if (projSelDTO.getScreenName().equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED)) {
            LOGGER.debug("Entering getProjectionPivot Mandated");
            List<Object> gtsList = (List<Object>) SPRCommonLogic.executeSelectQuery(QueryUtil.replaceTableNames(getSalesProjectionUnitsQuery(projSelDTO), projSelDTO.getSessionDTO().getCurrentTableNames()), null, null);
            projDTOList = getCustomizedProjectionPivot(gtsList, projSelDTO);
            LOGGER.debug("Ends getProjectionPivot Mandated");
        }
        return projDTOList;
    }

    public List<SalesRowDto> getCustomizedProjectionPivot(List<Object> list, ProjectionSelectionDTO projSelDTO) {

        int frequencyDivision = projSelDTO.getFrequencyDivision();
        List<SalesRowDto> projDTOList = new ArrayList<>();
        List<String> periodList = new ArrayList<>(projSelDTO.getPeriodList());
        int col = NumericConstants.TWO;
        for (Object rows : list) {
            final Object[] row = (Object[]) rows;
            String column;
            int year = Integer.valueOf(String.valueOf(row[0]));
            int period = Integer.valueOf(String.valueOf(row[1]));
            List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
            String pcommonColumn = common.get(0);
            String commonHeader = common.get(1);
            String commonColumn;
            if (periodList.contains(pcommonColumn)) {
                periodList.remove(pcommonColumn);
                SalesRowDto projDTO = new SalesRowDto();
                List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
                columnList.remove("levelName");
                projDTO.setLevelValue(commonHeader);
                projDTO.setLevelName(commonHeader);
                String value;
                commonColumn = "uv";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.TWO];
                    value = getFormattedValue(PROJECTEDUNITDECIMAL, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + NumericConstants.THREE];
                    value = getFormattedValue(PROJECTEDUNITDECIMAL, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                projDTO.setParent(0);
                projDTO.setProjectionTotal(1);
                for (String columns : columnList) {
                    projDTO.addStringProperties(columns, getFormattedValue(UNITNODECIMAL, Constant.NULL));
                }
                projDTOList.add(projDTO);
            }
        }
        List<String> columnList = new ArrayList<>(projSelDTO.getColumns());
        columnList.remove("levelName");
        boolean leftFlag = false;
        for (String ob : periodList) {
            leftFlag = true;
            SalesRowDto projDTO = new SalesRowDto();
            projDTO.setParent(0);
            projDTO.setProjectionTotal(1);
            projDTO.setLevelValue(projSelDTO.getPeriodListMap().get(ob));
            projDTO.setLevelName(projSelDTO.getPeriodListMap().get(ob));
            for (String columns : columnList) {
                projDTO.addStringProperties(columns, getFormattedValue(UNITNODECIMAL, Constant.NULL));
            }
            projDTOList.add(projDTO);
        }
        if (projSelDTO.getProjectionOrder().equals(ASCENDING.getConstant())) {
            if (leftFlag) {
                Collections.sort(projDTOList, new SalesRowDto());
            }
        } else {
            if (leftFlag) {
                Collections.sort(projDTOList, new SalesRowDto());
            }
            Collections.reverse(projDTOList);
        }
        return projDTOList;
    }

    public String getSalesProjectionUnitsQuery(ProjectionSelectionDTO projSelDTO) {

        String selectClause = Constant.SELECT_SMALL_SPACE;
        String groupBy = ",H.LEVEL_NAME , I.\"YEAR\"\n";
        String customQuery;
        String masterTable = StringUtils.EMPTY;
        String historyQuery = StringUtils.EMPTY;
        groupBy = ", H.HIERARCHY_NO " + groupBy;
        selectClause += "I.\"YEAR\" as YEARS, ";
        if (projSelDTO.getFrequencyDivision() == NumericConstants.FOUR) {
            selectClause += "I.QUARTER as PERIODS, \n";
            groupBy += Constant.IQUARTER;
        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWO) {
            selectClause += "I.SEMI_ANNUAL as PERIODS, \n";
            groupBy += ", I.SEMI_ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += "'0' as PERIODS, \n";
            groupBy += StringUtils.EMPTY;

        } else if (projSelDTO.getFrequencyDivision() == NumericConstants.TWELVE) {
            selectClause += "I.\"MONTH\" as PERIODS, \n";
            groupBy += ", I.\"MONTH\"";
        }

        // To filter the data according to selected period
        String periodFilter = StringUtils.EMPTY;
        //period filter should be in common once the dynamic changes is done in Government
        if (CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equals(projSelDTO.getScreenName())) {
            periodFilter = CommonLogic.getPeriodRestrictionQuery(projSelDTO);
            masterTable = Constant.ST_M_SALES_PROJECTION_MASTER;
        } else if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(projSelDTO.getScreenName())) {
            masterTable = Constant.ST_NM_SALES_PROJECTION_MASTER;
            periodFilter = "and " + CommonLogic.getPeriodRestrictionQuery(projSelDTO);
        }

        String customSql = "  PROJECTION_DETAILS E \n"
                + " JOIN  " + masterTable + " B ON B.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID \n"
                + " JOIN @CCP CCP ON CCP.CCP_DETAILS_SID = E.CCP_DETAILS_SID \n"
                + " JOIN RELATIONSHIP_LEVEL_DEFINITION H ON H.RELATIONSHIP_LEVEL_SID = CCP.RELATIONSHIP_LEVEL_SID";
        if (CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equals(projSelDTO.getScreenName())) {
            String customSqlPeriod = "\n LEFT JOIN ST_ALTERNATE_HIST_ALLOCATION SAHL ON SAHL.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID\n"
                    + " AND SAHL.SESSION_ID = A.SESSION_ID\n"
                    + " AND SAHL.USER_ID = A.USER_ID\n"
                    + " AND A.PERIOD_SID = I.PERIOD_SID\n"
                    + " AND SAHL.PERIOD_SID = A.PERIOD_SID"
                    + " where  \n"
                    + CommonLogic.getUserSessionQueryCondition(projSelDTO.getUserId(), projSelDTO.getSessionId(), "B").replaceFirst("and", StringUtils.EMPTY)
                    + " and E.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                    + " and  H.LEVEL_NO = " + projSelDTO.getLevelNo()
                    + " group by H.LEVEL_NO " + groupBy;

            historyQuery = selectClause + " sum(A.ACTUAL_SALES) as SALES_ACTUAL_SALES, \n"
                    + " sum(A.ACTUAL_PROJECTION_SALES) as SALES_PROJECTION_SALES, \n"
                    + " Sum(ISNULL(A.ACTUAL_UNITS,0) + ISNULL(SAHL.ACTUAL_UNITS,0)) as ACTUAL_UNITS, \n"
                    + " sum(ISNULL(A.ACTUAL_PROJECTION_UNITS,0) + ISNULL(SAHL.PROJECTION_UNITS,0)) as PROJECTION_UNITS \n"
                    + Constant.FROM_SMALL
                    + customSql
                    + " JOIN ST_M_ACTUAL_SALES A ON A.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID\n"
                    + " AND B.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID\n"
                    + " AND A.USER_ID = B.USER_ID\n"
                    + " AND A.SESSION_ID = B.SESSION_ID"
                    + " JOIN PERIOD I ON A.PERIOD_SID = I.PERIOD_SID " + periodFilter.replaceFirst("and", "and I.")
                    + customSqlPeriod;
        } else if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(projSelDTO.getScreenName())) {

            String customSqlPeriod = "\n LEFT JOIN ST_ALTERNATE_HIST_ALLOCATION SAHL ON SAHL.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID\n"
                    + " AND A.PERIOD_SID = I.PERIOD_SID\n"
                    + " AND SAHL.PERIOD_SID = A.PERIOD_SID"
                    + " where \n"
                    + " E.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                    + " and H.LEVEL_NO = " + projSelDTO.getLevelNo()
                    + " group by H.LEVEL_NO " + groupBy;
            historyQuery = selectClause + " sum(A.ACTUAL_SALES) as SALES_ACTUAL_SALES, \n"
                    + " sum(A.HISTORY_PROJECTION_SALES) as SALES_PROJECTION_SALES, \n"
                    + " Sum(ISNULL(A.ACTUAL_UNITS,0) + ISNULL(SAHL.ACTUAL_UNITS,0)) as ACTUAL_UNITS, \n"
                    + " sum(ISNULL(A.HISTORY_PROJECTION_UNITS,0) + ISNULL(SAHL.PROJECTION_UNITS,0)) as PROJECTION_UNITS \n"
                    + Constant.FROM_SMALL
                    + customSql
                    + " JOIN ST_NM_ACTUAL_SALES A ON A.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID\n"
                    + " AND B.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID\n"
                    + " JOIN PERIOD I ON A.PERIOD_SID = I.PERIOD_SID " + periodFilter.replaceFirst("and", "and I.")
                    + customSqlPeriod;
        }
        String futureQuery = selectClause + " 0 as SALES_ACTUAL_SALES, \n"
                + " sum(A.PROJECTION_SALES) as SALES_PROJECTION_SALES, \n"
                + " 0 as ACTUAL_UNITS, \n"
                + " sum(A.PROJECTION_UNITS) as PROJECTION_UNITS \n"
                + Constant.FROM_SMALL
                + customSql
                + " JOIN ST_NM_SALES_PROJECTION A ON B.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID\n"
                + " JOIN PERIOD I ON A.PERIOD_SID = I.PERIOD_SID AND I." + periodFilter.replaceFirst("and", StringUtils.EMPTY) + "\n"
                + " where \n"
                + "  E.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + " and H.LEVEL_NO = " + projSelDTO.getLevelNo()
                + " group by  H.LEVEL_NO " + groupBy;
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause("HISTORY", "FUTURE", "on");
        String finalWhereCond = list.get(1);
        String orderBy = list.get(NumericConstants.TWO);
        String finalSelectClause = "select " + list.get(0) + "\n Isnull(HISTORY.SALES_ACTUAL_SALES, FUTURE.SALES_ACTUAL_SALES) as ACTUAL_SALES,\n Isnull(FUTURE.SALES_PROJECTION_SALES, HISTORY.SALES_PROJECTION_SALES) as PROJECTION_SALES,"
                + "\n Isnull(HISTORY.ACTUAL_UNITS, FUTURE.ACTUAL_UNITS) as ACTUAL_UNITS,\n Isnull(FUTURE.PROJECTION_UNITS, HISTORY.PROJECTION_UNITS) as PROJECTION_UNITS  ";

        customQuery = (Constant.ALTERNATE_HISTORY.equalsIgnoreCase(projSelDTO.getFunctionality()) ? StringUtils.EMPTY : finalSelectClause + " from (\n") + historyQuery + (Constant.ALTERNATE_HISTORY.equalsIgnoreCase(projSelDTO.getFunctionality()) ? StringUtils.EMPTY : "\n) HISTORY FULL OUTER JOIN (\n" + futureQuery + "\n) FUTURE \n" + finalWhereCond + " Order By " + orderBy);
        return customQuery;
    }

    public List<Leveldto> getConditionalLevelList(int projectionId, int start, int offset, String hierarchyIndicator, int levelNo, String hierarchyNo, String productHierarchyNo, String customerHierarchyNo, boolean isFilter, boolean isExpand, boolean isCustom, int customId, String userGroup, int userId, int sessionId, String custRelSid, String prodRelSid, boolean isCount, boolean isLimit, SessionDTO session) {
        List<Leveldto> listValue = new ArrayList<>();
        try {
            String query = getLevelListQuery(projectionId, hierarchyIndicator, levelNo, hierarchyNo, productHierarchyNo, customerHierarchyNo, isFilter, isExpand, isCount, start, offset, isLimit, isCustom, customId, userGroup, userId, sessionId, custRelSid, prodRelSid);

            List<Object> list = (List<Object>) HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));
            if (list != null && !list.isEmpty()) {
                for (Object list1 : list) {
                    final Object[] obj = (Object[]) list1;
                    Leveldto dto = getCustomizedView(obj, false);
                    listValue.add(dto);
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return listValue;
    }

    public String getLevelListQuery(int projectionId, String hierarchyIndicator, int levelNo, String hierarchyNo, String productHierarchyNo, String customerHierarchyNo, boolean isFilter, boolean isExpand, boolean isCount, int start, int offset, boolean isLimit, boolean isCustom, int customId, String userGroup, int userId, int sessionId, String custRelSid, String prodRelSid) {
        if (isCustom) {

            String hierarchyIndicatorQuery = "select HIERARCHY_INDICATOR from dbo.CUSTOM_VIEW_DETAILS where CUSTOM_VIEW_MASTER_SID=" + customId + " and LEVEL_NO=" + levelNo;
            List<Object> list = (List<Object>) HelperTableLocalServiceUtil.executeSelectQuery(hierarchyIndicatorQuery);
            if (list != null && !list.isEmpty()) {
                Object ob = list.get(0);
                hierarchyIndicator = String.valueOf(ob);
            } else {
                hierarchyIndicator = StringUtils.EMPTY;
            }
        }
        String hierarchyNo1 = StringUtils.EMPTY;
        String whereCond = " ";
        if (hierarchyNo != null) {
            if ((!hierarchyNo.equals(StringUtils.EMPTY)) && (isExpand)) {
                whereCond = " and HLD" + hierarchyIndicator.trim() + ".HIERARCHY_NO='" + hierarchyNo + "' ";
            }
            if ((!hierarchyNo.equals(StringUtils.EMPTY)) && (!isFilter)) {
                hierarchyNo1 = hierarchyNo;
            }
        }
        String recordNumber = StringUtils.EMPTY;
        String selectClause = "select ";
        if (isCount) {
            selectClause += " Count(distinct HLD" + hierarchyIndicator.trim() + ".HIERARCHY_NO) ";
        } else {
            selectClause += " distinct HLD" + hierarchyIndicator.trim() + ".LEVEL_NO, "
                    + " HLD" + hierarchyIndicator.trim() + ".TREE_LEVEL_NO, "
                    + " '" + hierarchyIndicator + Constant.AS_HIERARCHY_INDICATOR_COMMA
                    + " HLD" + hierarchyIndicator.trim() + ".LEVEL_NAME,"
                    + " HLD" + hierarchyIndicator.trim() + ".RELATIONSHIP_LEVEL_VALUES,"
                    + " HLD" + hierarchyIndicator.trim() + ".PARENT_NODE,"
                    + " HLD" + hierarchyIndicator.trim() + ".HIERARCHY_NO ";
            if (isLimit) {
                recordNumber += " ORDER BY HLD" + hierarchyIndicator.trim() + ".HIERARCHY_NO ASC OFFSET " + start + Constant.ROWS_FETCH_NEXT_SPACE + offset + Constant.ROWS_ONLY_SPACE;
            } else {
                selectClause += ", ROW_NUMBER() OVER (ORDER BY HLD" + hierarchyIndicator.trim() + ".HIERARCHY_NO ASC) AS TEMP_INDEX ";
            }
        }
        String customSql = selectClause;
        if (isCustom) {

            String customViewQuery = getCustomViewLevelListQuery(projectionId, customId, hierarchyIndicator, levelNo, productHierarchyNo, customerHierarchyNo, custRelSid, prodRelSid, userId, sessionId);
            customSql += Constant.FROM_SMALL + customViewQuery;
        } else {
            String relationshipBuilderSid = custRelSid;
            if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(hierarchyIndicator)) {
                relationshipBuilderSid = prodRelSid;
            }
            String selectClause1 = "(SELECT RLD.relationship_level_values,RLD.hierarchy_no,CCP.ccp_details_sid,RLD.hierarchy_level_definition_sid,RLD.level_no,RLD.level_no as TREE_LEVEL_NO," + "'" + hierarchyIndicator + "'" + " HIERARCHY_INDICATOR,RLD.PARENT_NODE ";
            String selectClause2 = " (SELECT RLD1.hierarchy_no,RLD1.relationship_level_sid,RLD1.relationship_level_values,RLD1.level_no,RLD1.level_name,RLD1.level_no as TREE_LEVEL_NO," + "'" + hierarchyIndicator + "'" + " HIERARCHY_INDICATOR,RLD1.hierarchy_level_definition_sid,RLD1.PARENT_NODE ";
            String joinQuery1 = " relationship_level_definition RLD JOIN ccp_map CCP ON RLD.relationship_level_sid = CCP.relationship_level_sid"
                    + " AND RLD.RELATIONSHIP_BUILDER_SID=" + relationshipBuilderSid + " \n"
                    + " JOIN  ST_ALTERNATE_HIST_ALLOCATION STHA ON \n"
                    + "	 STHA.CCP_DETAILS_SID = CCP.ccp_details_sid\n"
                    + getGroupFilterQuery(userGroup, false) + ") CCPMAP,";

            String joinQuery2 = " relationship_level_definition RLD1 JOIN " + getViewTableName(hierarchyIndicator) + " PCH  ON PCH.relationship_level_sid = RLD1.relationship_level_sid \n"
                    + " AND PCH.projection_master_sid =" + projectionId
                    + " WHERE  RLD1.hierarchy_no LIKE '" + hierarchyNo1 + "%' AND RLD1.LEVEL_NO = " + levelNo + ") HLD" + hierarchyIndicator.trim();
            String mainJoin = " WHERE  CCPMAP.hierarchy_no LIKE HLD" + hierarchyIndicator.trim() + ".hierarchy_no + '%'";

            customSql += Constant.FROM_SMALL + selectClause1 + Constant.FROM_SMALL + joinQuery1 + " " + selectClause2 + Constant.FROM_SMALL + joinQuery2 + " " + mainJoin
                    + whereCond;
        }
        customSql += recordNumber;
        return customSql;
    }

    public String getCustomViewLevelListQuery(int projectionId, int customId, String hierarchyIndicator, int levelNo, String productHierarchyNo, String customerHierarchyNo, String custRelSid, String prodRelSid, int userId, int sessionId) {
        customerHierarchyNo += Constant.PERCENT;
        productHierarchyNo += Constant.PERCENT;
        String customerLevelNo = Constant.PERCENT;
        String productLevelNo = Constant.PERCENT;

        if (hierarchyIndicator.equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
            customerLevelNo = StringUtils.EMPTY + levelNo;
        } else if (hierarchyIndicator.equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)) {
            productLevelNo = StringUtils.EMPTY + levelNo;
        }
        String customViewQuery = "(SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID "
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD "
                + " JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID"
                + " AND RLD.RELATIONSHIP_BUILDER_SID = " + custRelSid + "\n"
                + " JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=" + projectionId
                + " JOIN ST_ALTERNATE_HIST_ALLOCATION STHA ON \n"
                + "	 STHA.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID\n"
                + "	 AND STHA.USER_ID = \n" + userId
                + "	 AND STHA.SESSION_ID = " + sessionId
                + " ) CCPMAPC"
                + " JOIN"
                + " (SELECT RLD.RELATIONSHIP_LEVEL_VALUES, RLD.HIERARCHY_NO, CCP.CCP_DETAILS_SID "
                + " FROM RELATIONSHIP_LEVEL_DEFINITION RLD "
                + " JOIN CCP_MAP CCP ON RLD.RELATIONSHIP_LEVEL_SID=CCP.RELATIONSHIP_LEVEL_SID"
                + " AND RLD.RELATIONSHIP_BUILDER_SID = " + prodRelSid + "\n"
                + " JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID=" + projectionId
                + " JOIN ST_ALTERNATE_HIST_ALLOCATION STHA ON \n"
                + "	 STHA.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID\n"
                + "	 AND STHA.USER_ID = \n" + userId
                + "	 AND STHA.SESSION_ID = " + sessionId
                + " ) CCPMAPP "
                + " ON CCPMAPC.CCP_DETAILS_SID=CCPMAPP.CCP_DETAILS_SID"
                + " JOIN "
                + " (SELECT distinct RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO as TREE_LEVEL_NO, RLD2.LEVEL_NO,RLD2.RELATIONSHIP_LEVEL_VALUES,RLD2.PARENT_NODE,RLD2.LEVEL_NAME \n"
                + " FROM dbo.CUSTOM_VIEW_DETAILS CVD "
                + " JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID"
                + " AND CVD.CUSTOM_VIEW_MASTER_SID=" + customId + " AND CVD.LEVEL_NO  like '" + customerLevelNo + "'"
                + " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID "
                + " JOIN PROJECTION_CUST_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID=" + projectionId
                + " WHERE RLD2.HIERARCHY_NO like '" + customerHierarchyNo + "') HLDC ON CCPMAPC.HIERARCHY_NO like HLDC.HIERARCHY_NO+'%'"
                + " JOIN "
                + " (SELECT distinct RLD2.HIERARCHY_NO,RLD2.RELATIONSHIP_LEVEL_SID, CVD.LEVEL_NO as TREE_LEVEL_NO, RLD2.LEVEL_NO,RLD2.RELATIONSHIP_LEVEL_VALUES,RLD2.PARENT_NODE,RLD2.LEVEL_NAME FROM dbo.CUSTOM_VIEW_DETAILS CVD "
                + " JOIN dbo.HIERARCHY_LEVEL_DEFINITION HLD ON CVD.HIERARCHY_ID=HLD.HIERARCHY_LEVEL_DEFINITION_SID"
                + " AND CVD.CUSTOM_VIEW_MASTER_SID=" + customId + " AND CVD.LEVEL_NO  like '" + productLevelNo + "'"
                + " JOIN RELATIONSHIP_LEVEL_DEFINITION RLD2 ON HLD.HIERARCHY_LEVEL_DEFINITION_SID=RLD2.HIERARCHY_LEVEL_DEFINITION_SID "
                + " JOIN PROJECTION_PROD_HIERARCHY PCH2 ON PCH2.RELATIONSHIP_LEVEL_SID=RLD2.RELATIONSHIP_LEVEL_SID AND PCH2.PROJECTION_MASTER_SID=" + projectionId
                + " WHERE RLD2.HIERARCHY_NO like '" + productHierarchyNo + Constant.HLDP_ON_CCP_MAP_HIERARCHY_NO_LIKE;
        return customViewQuery;
    }

    public String getViewTableName(String hierarchyIndicator) {
        String viewtable = StringUtils.EMPTY;
        if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(hierarchyIndicator)) {
            viewtable = "PROJECTION_CUST_HIERARCHY";
        } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(hierarchyIndicator)) {
            viewtable = "PROJECTION_PROD_HIERARCHY";
        }
        return viewtable;
    }

    public String getGroupFilterQuery(String userGroup, boolean isPrior) {
        String query = StringUtils.EMPTY;
        if (!userGroup.isEmpty()) {
            if (userGroup.startsWith(Constant.ALL)) {
                userGroup = " like '%' ";
                query = getGroupFilterSalesQuery(userGroup, isPrior);
            } else if (userGroup.startsWith(Constant.SALES)) {
                userGroup = " = '" + userGroup.replace(Constant.SALES, StringUtils.EMPTY) + "' ";
                query = getGroupFilterSalesQuery(userGroup, isPrior);
            }
        }
        return query;
    }

    public String getGroupFilterSalesQuery(String userGroup, boolean isPrior) {
        String tableIndicator = StringUtils.EMPTY;
        if (!isPrior) {
            tableIndicator = "ST_";
        }
        String query = "JOIN " + tableIndicator + "NM_SALES_PROJECTION_MASTER S ON S.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID WHERE  S.USER_GROUP " + userGroup;
        return query;
    }

    public Leveldto getCustomizedView(Object[] obj, boolean isHierarchy) {
        Leveldto dto = new Leveldto();
        dto.setLevelNo(Integer.valueOf(String.valueOf(obj[0])));
        dto.setTreeLevelNo(Integer.valueOf(String.valueOf(obj[1])));
        dto.setHierarchyIndicator(String.valueOf(obj[NumericConstants.TWO]));
        dto.setLevel(String.valueOf(obj[NumericConstants.THREE]));
        if (isHierarchy) {
            dto.setHierarchyId(Integer.valueOf(String.valueOf(obj[NumericConstants.FOUR])));
        } else {
            dto.setRelationshipLevelValue(String.valueOf(obj[NumericConstants.FOUR]));
            dto.setParentNode(String.valueOf(obj[NumericConstants.FIVE]));
            dto.setHierarchyNo(String.valueOf(obj[NumericConstants.SIX]));
        }
        return dto;
    }

    public int getLevelListCount(int projectionId, String hierarchyIndicator, int levelNo, String hierarchyNo, String productHierarchyNo, String customerHierarchyNo, boolean isFilter, boolean isCustom, int customId, String userGroup, int userId, int sessionId, String custRelSid, String prodRelSid, SessionDTO session) {
        int count = 0;
        try {
            String query = getLevelListQuery(projectionId, hierarchyIndicator, levelNo, hierarchyNo, productHierarchyNo, customerHierarchyNo, isFilter, false, true, 0, 0, false, isCustom, customId, userGroup, userId, sessionId, custRelSid, prodRelSid);
            List<Object> list = (List<Object>) HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.replaceTableNames(query, session.getCurrentTableNames()));
            if (list != null && !list.isEmpty()) {
                Object ob = list.get(0);
                count = Integer.valueOf(String.valueOf(ob));
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return count;
    }

private void cumulativeCalculation(ProjectionSelectionDTO projectionSelectionDTO, String calcBased, String methodology, final String tableName   ) {
        try {
            Object[] procedureInputs = null;

            CommonUtil.getInstance().waitsForOtherThreadsToComplete(projectionSelectionDTO.getSessionDTO().getFutureValue(Constant.FILE_INSERT, 0));
            LOGGER.debug("PRC_GROWTH_CALCULATION--------------------------------------- ");

            procedureInputs = new Object[]{projectionSelectionDTO.getProjectionId(), projectionSelectionDTO.getUserId(), projectionSelectionDTO.getSessionDTO().getSessionId(), projectionSelectionDTO.getTabName(), calcBased, projectionSelectionDTO.getFrequency(), UiUtils.getDate(),null,start,end};
			// Procedure calling part moved to Webservice
			new CumulativeCalculationUtils(procedureInputs, String.valueOf(projectionSelectionDTO.getUserId()),
					projectionSelectionDTO.getSessionDTO().getSessionId(), methodology,
					projectionSelectionDTO.getTabName(), tableName);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }

    }

    public Set availableHierarchy(List currentHierarchy, ProjectionSelectionDTO projectionSelectionDTO, int start, int end, int expandLevelNo) {
        int forecastlevel = Integer.valueOf(projectionSelectionDTO.getHierarchyIndicator().equals("C") ? projectionSelectionDTO.getSessionDTO().getCustomerLevelNumber() : projectionSelectionDTO.getSessionDTO().getProductLevelNumber());
        Set hierachies = new LinkedHashSet();
        for (int i = start; i < end && i < currentHierarchy.size(); i++) {
            String hierarachy = String.valueOf(currentHierarchy.get(i));
            int levelNoForcurrent = Integer.valueOf((String) projectionSelectionDTO.getSessionDTO().getHierarchyLevelDetails().get(hierarachy).get(2));
            while (!hierarachy.isEmpty()) {
                if ((levelNoForcurrent >= forecastlevel) && (levelNoForcurrent <= expandLevelNo) && (!hierachies.contains(hierarachy))) {
                    hierachies.add(hierarachy);
                }
                hierarachy = hierarachy.substring(0, hierarachy.length() - 1);
                hierarachy = hierarachy.substring(0, hierarachy.lastIndexOf(".") + 1);
                if (!hierarachy.isEmpty()) {
                    levelNoForcurrent = Integer.valueOf((String) projectionSelectionDTO.getSessionDTO().getHierarchyLevelDetails().get(hierarachy).get(2));
                }
            }
        }
        return hierachies;
    }

    public String generateHierearchy(LinkedHashMap<Integer, AtomicInteger> map, String asd) {
        int count = StringUtils.countMatches(asd, ".");
        StringBuilder builder = new StringBuilder();
        if (map.containsKey(count)) {
            map.get(count).incrementAndGet();
            if (map.containsKey(count + 1)) {
                map.put(count + 1, new AtomicInteger());
            }
        } else {
            map.put(count, new AtomicInteger(1));

        }
        for (Map.Entry object : map.entrySet()) {
            if ((Integer) object.getKey() > count) {
                break;
            } else {
                builder.append(object.getValue()).append('.');
            }
        }
        return builder.toString();
    }

    private void getStatement(Connection connection, String prc_sales_projection_temp,ProjectionSelectionDTO projectionSelectionDTO,String start, String end,String calcBased,String allocationBasis,String indicator) {
        StringBuilder procedure = new StringBuilder("{call ");
        if (indicator.equals("NM")) {
            procedure.append(prc_sales_projection_temp).append(" (?,?,?,?,?,?,?,?,?)}");
        } else {
            procedure.append(prc_sales_projection_temp).append(" (?,?,?,?,?,?,?,?)}");
        }
        try (CallableStatement statement = connection.prepareCall(procedure.toString())) {
            statement.setObject(1, projectionSelectionDTO.getProjectionId());
            statement.setObject(NumericConstants.TWO, projectionSelectionDTO.getUserId());
            statement.setObject(NumericConstants.THREE, projectionSelectionDTO.getSessionDTO().getSessionId());
            statement.setObject(NumericConstants.FOUR, projectionSelectionDTO.getFrequency());
            statement.setObject(NumericConstants.FIVE,start);
            statement.setObject(NumericConstants.SIX,end);
            statement.setObject(NumericConstants.SEVEN, calcBased);
            statement.setObject(NumericConstants.EIGHT, allocationBasis);
            if (indicator.equals("NM")) {
                statement.setObject(NumericConstants.NINE, projectionSelectionDTO.getSessionDTO().getSalesInclusion().equals(ALL) ? null : projectionSelectionDTO.getSessionDTO().getSalesInclusion());
            }
            statement.execute();
        } catch (SQLException ex) {
            LOGGER.error(ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                LOGGER.error(ex);
            }
        }

    }
      public String getPeriodSid(String period, String fre, String order) throws SystemException, PortalException {
        List periodSid = (List) salesAllocationDAO.executeSelectQuery(utils.periodQuery(period, fre, order));
        return periodSid.get(0).toString();
    }
}
