package com.stpl.app.galforecasting.salesprojection.logic;

import com.stpl.app.galforecasting.dao.CommonDAO;
import com.stpl.app.galforecasting.dao.SalesProjectionDAO;
import com.stpl.app.galforecasting.dao.impl.CommonDAOImpl;
import com.stpl.app.galforecasting.dao.impl.SalesProjectionDAOImpl;
import com.stpl.app.galforecasting.dto.ContractBrandDTO;
import com.stpl.app.galforecasting.logic.DataSourceConnection;
import static com.stpl.app.galforecasting.logic.NonMandatedLogic.dataSelection;
import com.stpl.app.galforecasting.utils.AlternateLookupSource;
import com.stpl.app.model.BrandMaster;
import com.stpl.app.model.CompanyMaster;
import com.stpl.app.model.ContractMaster;
import com.stpl.app.galforecasting.dto.ProjectionSelectionDTO;
import com.stpl.app.galforecasting.dto.SalesRowDto;
import com.stpl.app.galforecasting.logic.CommonLogic;
import static com.stpl.app.galforecasting.logic.CommonLogic.getCustomViewDetails;
import com.stpl.app.galforecasting.logic.DataSelectionLogic;
import com.stpl.app.galforecasting.salesprojection.form.MSalesProjection;
import static com.stpl.app.galforecasting.salesprojection.utils.HeaderUtils.getMonthForInt;
import com.stpl.app.galforecasting.salesprojection.utils.QueryUtils;
import com.stpl.app.galforecasting.salesprojection.utils.SalesUtils;
import com.stpl.app.galforecasting.salesprojectionresults.logic.NMSalesProjectionResultsLogic;
import com.stpl.app.galforecasting.salesprojectionresults.logic.SPRCommonLogic;
import com.stpl.app.galforecasting.sessionutils.SessionDTO;
import com.stpl.app.model.ChProjectionSelection;
import com.stpl.app.service.ChProjectionSelectionLocalServiceUtil;
import com.stpl.app.galforecasting.utils.CommonQueryUtils;
import com.stpl.app.galforecasting.utils.CommonUtils;
import com.stpl.app.galforecasting.utils.Constant;
import static com.stpl.app.galforecasting.utils.Constant.STRING_EMPTY;
import static com.stpl.app.galforecasting.utils.HeaderUtils.getCommonColumnHeader;
import com.stpl.app.galforecasting.utils.xmlparser.SQlUtil;
import com.stpl.app.model.CustomViewDetails;
import com.stpl.app.model.RelationshipLevelDefinition;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import static com.stpl.app.utils.Constants.CommonConstants.*;
import static com.stpl.app.utils.Constants.FinderImplIndicators.*;
import static com.stpl.app.utils.Constants.FrequencyConstants.*;
import static com.stpl.app.utils.Constants.IndicatorConstants.*;
import static com.stpl.app.utils.Constants.LabelConstants.*;
import static com.stpl.app.utils.Constants.StringConstants.PERCENT;
import static com.stpl.app.utils.Constants.StringConstants.SPLIT_ARROW;
import com.stpl.app.utils.UiUtils;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.forecastds.dto.Leveldto;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.stpl.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionList;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.util.dao.orm.CustomSQLUtil;
import java.math.BigDecimal;
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
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeMap;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.apache.commons.lang.StringUtils;

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

    final CommonQueryUtils commonQueryUtils = CommonQueryUtils.getInstance();
    public static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(SalesLogic.class);
    SalesProjectionDAO salesAllocationDAO = new SalesProjectionDAOImpl();
    ResourceBundle resourceBundle = ResourceBundle.getBundle("properties.tablename");
    private SessionDTO session;
    NMSalesProjectionResultsLogic sprLogic = new NMSalesProjectionResultsLogic();

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
    public int getHistoryAndProjectionCount(final SessionDTO sessionDTO, final ProjectionSelectionDTO projectionSelectionDTO) throws PortalException, Exception {
        String query = CustomSQLUtil.get("rows-per-level-item");
        query = query.replace("@USER_ID", sessionDTO.getUserId());
        query = query.replace("@SESSION_ID", sessionDTO.getSessionId());
        if (projectionSelectionDTO.getFrequencyDivision() == 1) {
            query = query.replace("@FREQUENCY", "A");
        } else if (projectionSelectionDTO.getFrequencyDivision() == 4) {
            query = query.replace("@FREQUENCY", Constant.Q);
        } else if (projectionSelectionDTO.getFrequencyDivision() == 2) {
            query = query.replace("@FREQUENCY", Constant.S);
        } else if (projectionSelectionDTO.getFrequencyDivision() == 12) {
            query = query.replace("@FREQUENCY", CommonUtils.BUSINESS_PROCESS_INDICATOR_MANDATED);
        }
        if (CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equals(projectionSelectionDTO.getScreenName())) {
            query = query.replace("@UNION", "UNION ALL");
            query = query.replace("@ACTUAL_TABLE", "ST_M_ACTUAL_SALES");
            query = query.replace("@PROJECTION_TABLE", "ST_M_SALES_PROJECTION");
        } else if(CommonUtils.BUSINESS_PROCESS_TYPE_RETURNS.equals(projectionSelectionDTO.getScreenName())){
            query = query.replace("@UNION", "UNION");
            query = query.replace("@ACTUAL_TABLE", "ST_RETURNS_ACTUALS");
            query = query.replace("@PROJECTION_TABLE", "ST_RETURNS_PROJ_DETAILS");
        } else {
            query = query.replace("@UNION", "UNION ALL");
            query = query.replace("@ACTUAL_TABLE", "ST_NM_ACTUAL_SALES");
            query = query.replace("@PROJECTION_TABLE", "ST_NM_SALES_PROJECTION");
        }
        List list = (List) HelperTableLocalServiceUtil.executeSelectQuery(query);
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
        if(projSelDTO.getPivotView() != null && Constant.VARIABLE.equals(projSelDTO.getPivotView())) {
            if (!projSelDTO.isIsFilter() && (parentId instanceof SalesRowDto)) {
                projSelDTO.setYear(Constant.All);

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
        } else {
            if ((!projSelDTO.isIsFilter() && !projSelDTO.isFilterDdlb()) || parentId instanceof SalesRowDto) {
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
        } else if ("Alternate_History".equals(projSelDTO.getFunctionality())) {
            levelCount = getLevelListCount(projSelDTO.getProjectionId(), projSelDTO.getHierarchyIndicator(), projSelDTO.getTreeLevelNo(), projSelDTO.getHierarchyNo(),
                    projSelDTO.getProductHierarchyNo(), projSelDTO.getCustomerHierarchyNo(), projSelDTO.isIsFilter(), projSelDTO.isIsCustomHierarchy(),
                    projSelDTO.getCustomId(), userGroup, projSelDTO.getUserId(), projSelDTO.getSessionId(),
                    projSelDTO.getCustRelationshipBuilderSid(), projSelDTO.getProdRelationshipBuilderSid());
            if (projSelDTO.isIsProjectionTotal()) {
                levelCount += 1;
            }
        } else {
            levelCount = CommonLogic.getLevelListCount(projSelDTO.getProjectionId(), projSelDTO.getHierarchyIndicator(), projSelDTO.getTreeLevelNo(), projSelDTO.getHierarchyNo(),
                    projSelDTO.getProductHierarchyNo(), projSelDTO.getCustomerHierarchyNo(), projSelDTO.isIsFilter(), projSelDTO.isIsCustomHierarchy(),
                    projSelDTO.getCustomId(), userGroup, projSelDTO.getUserId(), projSelDTO.getSessionId(),
                    projSelDTO.getCustRelationshipBuilderSid(), projSelDTO.getProdRelationshipBuilderSid());
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
                projSelDTO.setYear(Constant.All);

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
        } else {
            if ((!projSelDTO.isIsFilter() && !projSelDTO.isFilterDdlb()) || parentId instanceof SalesRowDto) {
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
    public List<SalesRowDto> getSalesResults(ProjectionSelectionDTO projSelDTO, int start, int end) throws Exception {
       /*if no record available in ST_NM_ACTAUL_SALES table, we will show hierarchy in table */
        String sql = commonQueryUtils.getCCPQuery(projSelDTO);
        if (projSelDTO.getFunctionality().equals("Alternate_History")) {
            sql += SQlUtil.getQuery("alternate-histroy-sales-summary");
        } else {
            sql += CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(projSelDTO.getScreenName()) ? CustomSQLUtil.get("non-mandated-sales-query") : CustomSQLUtil.get("mandated-sales-query");
        }
        sql = sql.replace("@USER_ID", projSelDTO.getSessionDTO().getUserId());
        sql = sql.replace("@SESSION_ID", projSelDTO.getSessionDTO().getSessionId());
        sql = sql.replace("@LEVEL_NO", String.valueOf(projSelDTO.getTreeLevelNo()));
        sql = sql.replace("@HIERARCHY_INDICATOR", projSelDTO.getHierarchyIndicator());
        sql = sql.replace("@START", String.valueOf(start));
        sql = sql.replace("@END", String.valueOf(end));
        sql = sql.replace("@PROJECTION_MASTER_SID", String.valueOf(projSelDTO.getProjectionId()));
        int freqNo = getFrequencyNumber(projSelDTO.getFrequency());
        sql = sql.replaceAll("@FREQDIVISION", String.valueOf(freqNo));
        if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(projSelDTO.getScreenName())) {
            sql = sql.replace("@USER_GROUP", StringUtils.isBlank(projSelDTO.getGroup())
                    || Constant.NULL.equals(projSelDTO.getGroup()) || Constant.SHOW_ALL_GROUPS.equals(projSelDTO.getGroup()) ? Constant.PERCENT : projSelDTO.getGroup());
        }
        switch (projSelDTO.getFrequencyDivision()) {
            case 1:
                sql = sql.replace("@FREQUENCY@", "1 ");
                sql = sql.replace("@FREQUENCY_GROUP@,", StringUtils.EMPTY);
                break;
            case 2:
                sql = sql.replace("@FREQUENCY@", "p.SEMI_ANNUAL");
                sql = sql.replace("@FREQUENCY_GROUP@,", "p.SEMI_ANNUAL,");
                break;
            case 4:
                sql = sql.replace("@FREQUENCY@", "p.QUARTER");
                sql = sql.replace("@FREQUENCY_GROUP@,", "p.QUARTER,");
                break;
            case 12:
                sql = sql.replace("@FREQUENCY@", "p.MONTH");
                sql = sql.replace("@FREQUENCY_GROUP@,", "p.MONTH,");
                break;
            default:
                break;
        }
        List list = (List) HelperTableLocalServiceUtil.executeSelectQuery(sql);
        return convertfinalResultLists(list, projSelDTO.isIsCustomHierarchy(), projSelDTO.getTreeLevelNo(), projSelDTO.getCustomerHierarchyNo(), projSelDTO.getProductHierarchyNo(), projSelDTO);
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
        List<String> headerMapValue= new ArrayList<>();
        headerMapValue.addAll(projectionSelectionDTO.getHeaderMapColumn());
        String lastLevelValue = STRING_EMPTY;
            if (projectionSelectionDTO.isIsProjectionTotal() && "Alternate_History".equalsIgnoreCase(projectionSelectionDTO.getFunctionality())) {
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
            for(int i=0;i<list.size();i++) {
                Object obj[] = (Object[]) list.get(i);
                int frequencyDivision = projectionSelectionDTO.getFrequencyDivision();
                String key = Constant.Q_SMALL + String.valueOf(obj[3]) + "-" + String.valueOf(obj[2]);
                if (frequencyDivision == 1) {
                    key = String.valueOf(obj[2]);
                } else if (frequencyDivision == 4) {
                    key = Constant.Q_SMALL + String.valueOf(obj[3]) + "-" + String.valueOf(obj[2]);
                } else if (frequencyDivision == 2) {
                    key = Constant.S_SMALL + String.valueOf(obj[3]) + "-" + String.valueOf(obj[2]);
                } else if (frequencyDivision == 12) {
                    String monthName = getMonthForInt(Integer.valueOf(String.valueOf(obj[3])) - 1);
                    key = monthName.toLowerCase() + "-" + String.valueOf(obj[2]);
                }
                salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-ActualUnits", String.valueOf(PROJECTEDUNITDECIMAL.format(obj[0] == null ? 0 : obj[0])));
                salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-ProjectedUnits", String.valueOf(PROJECTEDUNITDECIMAL.format(obj[1] == null ? 0 : obj[1])));
            }
            salesRowList.add(salesRowDto);
            salesRowDto = new SalesRowDto();
        }
            for (int i = 0; i < resulList.size(); i++) {
                Object obj[] = (Object[]) resulList.get(i);
                MSalesProjection.rowCountMap.put(String.valueOf(obj[13]), Integer.parseInt(String.valueOf(obj[14])));
                if (lastLevelValue.equalsIgnoreCase(STRING_EMPTY) || lastLevelValue.equals(String.valueOf(obj[7]))) {
                    lastLevelValue = String.valueOf(obj[7]);
                    salesRowDto.setHierarchyIndicator(String.valueOf(obj[22]));
                } else {
                    salesRowList.add(salesRowDto);
                    salesRowDto = new SalesRowDto();
                    salesRowDto.setHierarchyIndicator(String.valueOf(obj[22]));
                    lastLevelValue = String.valueOf(obj[7]);
                }
                salesRowDto.addBooleanProperties(Constant.CHECK, Integer.parseInt(String.valueOf(obj[19])) == 0 ? new Boolean(false) : new Boolean(true));
                if (obj[20] != null) {
                    salesRowDto.setUncheckCount(Integer.parseInt(String.valueOf(obj[20])));
                }
                salesRowDto.setParent(1);
                salesRowDto.setCcpCount(String.valueOf(obj[21]));
                salesRowDto.setHierarchyIndicator(String.valueOf(obj[22]));
                salesRowDto.addStringProperties(Constant.BASELINE, StringUtils.isBlank(String.valueOf(obj[10])) || Constant.NULL.equals(String.valueOf(obj[10])) ? "-" : String.valueOf(obj[10]));
                salesRowDto.addStringProperties(Constant.METHODOLOGY, StringUtils.isBlank(String.valueOf(obj[11])) || Constant.NULL.equals(String.valueOf(obj[11])) ? "-" : String.valueOf(obj[11]));
                salesRowDto.setRelationLevelSid(Integer.parseInt(String.valueOf(obj[12])));
                salesRowDto.setHierarchyNo(String.valueOf(obj[13]));
                salesRowDto.setHierarchyLevel(String.valueOf(obj[15]));
                if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(projectionSelectionDTO.getScreenName())) {
                    if ((Constant.TRADINGPARTNER.equalsIgnoreCase(salesRowDto.getHierarchyLevel()) || Constant.TRADING_PARTNER.equals(salesRowDto.getHierarchyLevel()))) {
                        salesRowDto.setGroup(String.valueOf(obj[23]));
                    } else {
                        salesRowDto.setGroup(StringUtils.EMPTY);
                    }
                }
                if (iscustom) {
                    salesRowDto.setTreeLevelNo(treeLevelNo);
                    salesRowDto.setCustomerHierarchyNo(lastCustomerHierNo);
                    salesRowDto.setProductHierarchyNo(lastproductHierNo);
                } else {
                    salesRowDto.setTreeLevelNo(Integer.valueOf(String.valueOf((obj[6]))));
                }
                salesRowDto.setLevelName(projectionSelectionDTO.getSessionDTO().getLevelValueDiscription(String.valueOf(obj[13]), String.valueOf(obj[22])));
                int frequencyDivision = projectionSelectionDTO.getFrequencyDivision();
                String key = Constant.Q_SMALL + String.valueOf(obj[9]) + "-" + String.valueOf(obj[8]);
                if (frequencyDivision == 1) {
                    key = String.valueOf(obj[8]);
                } else if (frequencyDivision == 4) {
                    key = Constant.Q_SMALL + String.valueOf(obj[9]) + "-" + String.valueOf(obj[8]);
                } else if (frequencyDivision == 2) {
                    key = Constant.S_SMALL + String.valueOf(obj[9]) + "-" + String.valueOf(obj[8]);
                } else if (frequencyDivision == 12) {
                    String monthName = getMonthForInt(Integer.valueOf(String.valueOf(obj[9])) - 1);
                    key = monthName.toLowerCase() + "-" + String.valueOf(obj[8]);
                }

                if (Integer.parseInt(String.valueOf(obj[16])) == 0) {
                    salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-ProjectedSales", String.valueOf(MONEYNODECIMAL.format(obj[2] == null ? 0 : obj[2])));
                    salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-ProjectedUnits", String.valueOf(PROJECTEDUNITDECIMAL.format(obj[3] == null ? 0 : obj[3])));
                    salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-ProductGrowth", String.valueOf(UNITTWODECIMAL.format(obj[1] == null ? 0 : obj[1])) + Constant.PERCENT);
                    salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-AccountGrowth", String.valueOf(UNITTWODECIMAL.format(obj[0] == null ? 0 : obj[0])) + Constant.PERCENT);
                    headerMapValue.remove(key + "-ProjectedSales");
                    headerMapValue.remove(key + "-ProjectedUnits");
                } else {
                    salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-ActualSales", String.valueOf(MONEYNODECIMAL.format(obj[4] == null ? 0 : obj[4])));
                    salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-ActualUnits", String.valueOf(PROJECTEDUNITDECIMAL.format(obj[5] == null ? 0 : obj[5])));
                    salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-HistoryProjectedSales", String.valueOf(0));
                    salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-HistoryProjectedUnits", String.valueOf(0));
                    headerMapValue.remove(key + "-ActualSales");
                    headerMapValue.remove(key + "-ActualUnits");
                }

                if (obj[20] != null) {
                    salesRowDto.setUncheckCount(Integer.parseInt(String.valueOf(obj[20])));
                }
                salesRowDto.setCcpCount(String.valueOf(obj[21]));
                if (projectionSelectionDTO.isLevelFilter()) {
                    salesRowDto.setParent(0);
                }
                if (i == (resulList.size() - 1)) {
                    salesRowList.add(salesRowDto);
                }
            }
        if (projectionSelectionDTO.getFrequencyDivision() == 12) {
            Set<String> groupLevel = new HashSet<String>();
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
            resulList = null;
        return salesRowList;

    }

    public int getSalesCount(SalesRowDto expandedParent, final Map<String, Object> parameters, ProjectionSelectionDTO projSelDTO) throws Exception {
        int size = 0;
        try {
            Map<String, Object> input = new HashMap<String, Object>();
            Map<String, String> join = new HashMap<String, String>();
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
            input.put("?HNOC?", ((expandedParent == null || isTotalSales) ? PERCENT.getConstant() : expandedParent.getCustomerHierarchyNo()));
            input.put("?HNOP?", ((expandedParent == null || isTotalSales) ? PERCENT.getConstant() : expandedParent.getProductHierarchyNo()));
            input.put(LEVEL_NO_C.getConstant(), String.valueOf(parameters.get(LEVEL_NO_C.getConstant())));
            input.put(LEVEL_NO_P.getConstant(), String.valueOf(parameters.get(LEVEL_NO_P.getConstant())));
            input.put(H_INDICATOR.getConstant(), String.valueOf(parameters.get(H_INDICATOR.getConstant())));
        }
        input.put("?RBSIDC?", projSelDTO.getCustRelationshipBuilderSid());
        input.put("?RBSIDP?", projSelDTO.getProdRelationshipBuilderSid());
        if (ANNUAL.getConstant().equalsIgnoreCase(String.valueOf(projSelDTO.getFrequency())) || ANNUAL.getConstant().equalsIgnoreCase(String.valueOf(projSelDTO.getFrequency()))) {
            join.put("?SELECTFREQJOIN?", " 'null' as FREQUENCY, P.\"YEAR\" AS FREQYR,");
            join.put("?GROUPFREQJOIN?", "P.?FREQUENCY?, P.\"YEAR\"");
            join.put("?ORDERFREQJOIN?", "SA.FREQYR,");
        } else {
            join.put("?SELECTFREQJOIN?", "P.?FREQUENCY? as FREQUENCY, P.\"YEAR\" AS FREQYR,");
            join.put("?GROUPFREQJOIN?", "P.?FREQUENCY?, P.\"YEAR\"");
            join.put("?ORDERFREQJOIN?", "SA.FREQUENCY, SA.FREQYR,");
        }
        if (!StringUtils.isBlank(projSelDTO.getProjectionStartDate()) && !Constant.NULL.equalsIgnoreCase(projSelDTO.getProjectionStartDate())) {
            input.put("?PROJECTIONSTARTDATE?", " AND P.PERIOD_DATE >= '" + projSelDTO.getProjectionStartDate() + "' ");
        } else {
            input.put("?PROJECTIONSTARTDATE?", StringUtils.EMPTY);
        }
        input.put("?FREQUENCY?", SalesUtils.getPeriodFrequecy(projSelDTO.getFrequency()));
        input.put("?CVSID?", projSelDTO.getCustomId());
        input.put("?COUNTFREQUENCY?", SalesUtils.getPeriodCountFrequecy(projSelDTO.getFrequency()));
        input.put(PM_SID.getConstant(), projSelDTO.getProjectionId());
    }

    private void configureCountParameterMap(final SalesRowDto expandedParent, final Map<String, Object> input, final Map<String, String> join, final Map<String, Object> parameters, final ProjectionSelectionDTO projSelDTO) {
        if (INDICATOR_LOGIC_CUSTOMER_HIERARCHY.getConstant().equalsIgnoreCase(projSelDTO.getHierarchyIndicator())) {
            input.put("?PHTABLE?", " PROJECTION_CUST_HIERARCHY ");
            input.put("?RBSID?", projSelDTO.getCustRelationshipBuilderSid());
            join.put("?PRODJOIN?", StringUtils.EMPTY);
        } else if (INDICATOR_LOGIC_PRODUCT_HIERARCHY.getConstant().equalsIgnoreCase(projSelDTO.getHierarchyIndicator())) {
            input.put("?PHTABLE?", " PROJECTION_PROD_HIERARCHY ");
            input.put("?RBSID?", projSelDTO.getProdRelationshipBuilderSid());
            join.put("?PRODJOIN?", "JOIN ITEM_MASTER IM ON IM.ITEM_MASTER_SID = CCP.ITEM_MASTER_SID " + QueryUtils.getTherapJoin(PERCENT.getConstant()));
        }
        if (!projSelDTO.isLevelFilter()) {
            input.put("?HNO?", (expandedParent == null ? PERCENT.getConstant() : expandedParent.getHierarchyNo()));
            input.put("?LEVELNO?", String.valueOf(parameters.get(LEVEL_NO.getConstant())));
        } else {
            input.put("?LEVELNO?", projSelDTO.getLevelFilterValue());
            input.put("?HNO?", PERCENT.getConstant());
        }
        input.put(PM_SID.getConstant(), projSelDTO.getProjectionId());
    }

    public List<SalesRowDto> generateSalesAllocation(SalesRowDto expandedParent, final Map<String, Object> parameters, ProjectionSelectionDTO projSelDTO,
            final int start, final int offset, final boolean isExpandCollapse, final boolean isTotalSales) throws Exception {
        List<SalesRowDto> resultList = new ArrayList<SalesRowDto>();
        try {
            Map<String, Object> inputs = new HashMap<String, Object>();
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
            final boolean isExpandCollapse, final boolean isTotalSales) throws PortalException, Exception {
        prepareGenerateInputs(expandedParent, projSelDTO, parameters, input, start, offset, false, isTotalSales, false);
        List returnList = salesAllocationDAO.executeQuery(parameters);
        List<SalesRowDto> resultList = processSalesResultViewList(expandedParent, returnList, projSelDTO, isExpandCollapse, false, isTotalSales, parameters);
        return resultList;
    }

    private List<SalesRowDto> generateCustomerView(final SalesRowDto expandedParent, final ProjectionSelectionDTO projSelDTO, final Map<String, Object> parameters, final Map<String, Object> input,
            final int start, final int offset, final boolean isExpandCollapse, final boolean isTotalSales) throws PortalException, Exception {
        prepareGenerateInputs(expandedParent, projSelDTO, parameters, input, start, offset, true, isTotalSales, false);
        List returnList = salesAllocationDAO.executeQuery(parameters);
        List<SalesRowDto> resultList = processSalesResultViewList(expandedParent, returnList, projSelDTO, isExpandCollapse, true, isTotalSales, parameters);
        return resultList;
    }

    private List<SalesRowDto> generateCustomView(final SalesRowDto expandedParent, final ProjectionSelectionDTO projSelDTO, final Map<String, Object> parameters, final Map<String, Object> input,
            final int start, final int offset, final boolean isExpandCollapse, final boolean isTotalSales) throws PortalException, Exception {
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
        Map<String, String> join = new HashMap<String, String>();
        join.put("?DECLARECCP?", QueryUtils.addDeclareQueryJoin(false, isUpdate));
        join.put("?JOINCCP?", QueryUtils.addCcpJoinQuery(false, isUpdate));
        if (!projSelDTO.isLevelFilter()) {
            if (!isUpdate) {
                input.put("?HNO?", ((expandedParent == null || isTotalSales) ? PERCENT.getConstant() : expandedParent.getHierarchyNo()));
                input.put("?LEVELNO?", String.valueOf(parameters.get(LEVEL_NO.getConstant())));
            }
        } else {
            input.put("?LEVELNO?", projSelDTO.getLevelFilterValue());
            input.put("?HNO?", PERCENT.getConstant());
        }
        if (ANNUAL.getConstant().equalsIgnoreCase(String.valueOf(projSelDTO.getFrequency())) || ANNUAL.getConstant().equalsIgnoreCase(String.valueOf(projSelDTO.getFrequency()))) {
            join.put("?SELECTFREQJOIN?", " 'null' as FREQUENCY, P.\"YEAR\" AS FREQYR,");
            join.put("?GROUPFREQJOIN?", "P.?FREQUENCY?, P.\"YEAR\"");
            join.put("?ORDERFREQJOIN?", "SA.FREQYR,");
        } else {
            join.put("?SELECTFREQJOIN?", "P.?FREQUENCY? as FREQUENCY, P.\"YEAR\" AS FREQYR,");
            join.put("?GROUPFREQJOIN?", "P.?FREQUENCY?, P.\"YEAR\"");
            join.put("?ORDERFREQJOIN?", "SA.FREQUENCY, SA.FREQYR,");
        }
        input.put("?FREQUENCY?", SalesUtils.getPeriodFrequecy(projSelDTO.getFrequency()));

        if (isCustomer) {
            // inputs for customer hierarchy
            input.put("?PHTABLE?", " PROJECTION_CUST_HIERARCHY ");
            input.put("?RBSID?", projSelDTO.getCustRelationshipBuilderSid());
            input.put("?THERAP?", StringUtils.EMPTY);
            input.put(H_INDICATOR.getConstant(), INDICATOR_LOGIC_CUSTOMER_HIERARCHY.getConstant());
        } else {
            //inputs for product hierarchy
            input.put("?PHTABLE?", " PROJECTION_PROD_HIERARCHY ");
            input.put("?RBSID?", projSelDTO.getProdRelationshipBuilderSid());
            input.put("?THERAP?", QueryUtils.getTherapJoin(PERCENT.getConstant()));

            input.put(H_INDICATOR.getConstant(), INDICATOR_LOGIC_PRODUCT_HIERARCHY.getConstant());
        }
        if (!StringUtils.isBlank(projSelDTO.getProjectionStartDate()) && !Constant.NULL.equalsIgnoreCase(projSelDTO.getProjectionStartDate())) {
            input.put("?PROJECTIONSTARTDATE?", " AND P.PERIOD_DATE > '" + projSelDTO.getProjectionStartDate() + "' ");
        } else {
            input.put("?PROJECTIONSTARTDATE?", StringUtils.EMPTY);
        }
        input.put("?COUNTFREQUENCY?", SalesUtils.getPeriodCountFrequecy(projSelDTO.getFrequency()));
        input.put(USER_ID.getConstant(), projSelDTO.getUserId());
        input.put(SESSION_ID.getConstant(), projSelDTO.getSessionId());
        input.put(PM_SID.getConstant(), projSelDTO.getProjectionId());
        input.put(START.getConstant(), start);
        input.put(OFFSET.getConstant(), offset);
        parameters.put(JOIN_MAP.getConstant(), join);
        parameters.put(INPUT_MAP.getConstant(), input);
    }

    private void prepareCustomGenerateInputs(final SalesRowDto expandedParent, final ProjectionSelectionDTO projSelDTO,
            final Map<String, Object> parameters, final Map<String, Object> input, final int start,
            final int offset, final boolean isTotalSales, final boolean isUpdate) {

        Map<String, String> join = new HashMap<String, String>();
        prepareCustomCountParameters(parameters, input, join, expandedParent, projSelDTO, isTotalSales, isUpdate);
        input.put(USER_ID.getConstant(), projSelDTO.getUserId());
        input.put(SESSION_ID.getConstant(), projSelDTO.getSessionId());
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
        List<SalesRowDto> resultList = new ArrayList<SalesRowDto>();
        SalesRowDto salesRowDto = new SalesRowDto();
        String hierarchyNo = StringUtils.EMPTY;
        String columnGts = "-GrossTradeSales";
        String columnPob = "-%OfBusiness";
        String columnCs = "-ContractSales";
        int maxLevelNo = 0;
        Map<String, String> monthMap = new HashMap<String, String>();
        String hierarchyIndicator = StringUtils.EMPTY;
        if (MONTHLY.getConstant().equalsIgnoreCase(projSelDTO.getFrequency())) {
            monthMap = SalesUtils.getMonthMap();
        }

        if (projSelDTO.isIsCustomHierarchy()) {
            hierarchyIndicator = INDICATOR_LOGIC_CUSTOM_HIERARCHY.getConstant();
        }
        maxLevelNo = projSelDTO.getLastLevelNo();
        for (int i = 0, j = returnList.size(); i < j; i++) {
            Object[] object = (Object[]) returnList.get(i);
            if (!hierarchyNo.equals(String.valueOf(object[3]))) {
                if (i != 0) {
                    resultList.add(salesRowDto);
                }
                salesRowDto = new SalesRowDto();
                hierarchyNo = String.valueOf(object[3]);

                salesRowDto.setParentHierarchyIndicator((expandedParent == null ? StringUtils.EMPTY : expandedParent.getHierarchyIndicator()));
                salesRowDto.setParentHierarchyNo((expandedParent == null ? StringUtils.EMPTY : expandedParent.getHierarchyNo()));
                salesRowDto.setHierarchyIndicator(hierarchyIndicator);
                if (isTotalSales) {
                    salesRowDto.setHierarchyNo(PERCENT.getConstant());
                    salesRowDto.setLevelNo(0);
                    salesRowDto.setLevelName("Total Sales");
                } else {
                    if (projSelDTO.isIsCustomHierarchy()) {
                        if (expandedParent == null) {
                            if (INDICATOR_LOGIC_CUSTOMER_HIERARCHY.getConstant().equalsIgnoreCase(String.valueOf(object[10]))) {
                                salesRowDto.setCustomerHierarchyNo(hierarchyNo);
                                salesRowDto.setProductHierarchyNo(PERCENT.getConstant());
                            } else if (INDICATOR_LOGIC_PRODUCT_HIERARCHY.getConstant().equalsIgnoreCase(String.valueOf(object[10]))) {
                                salesRowDto.setProductHierarchyNo(hierarchyNo);
                                salesRowDto.setCustomerHierarchyNo(PERCENT.getConstant());
                            }
                            if (INDICATOR_LOGIC_CUSTOMER_HIERARCHY.getConstant().equalsIgnoreCase(String.valueOf(object[10]))) {
                                salesRowDto.setCustomerLevelNo(Constant.STRING_ONE);
                                salesRowDto.setProductLevelNo(PERCENT.getConstant());
                            } else if (INDICATOR_LOGIC_PRODUCT_HIERARCHY.getConstant().equalsIgnoreCase(String.valueOf(object[10]))) {
                                salesRowDto.setProductLevelNo(Constant.STRING_ONE);
                                salesRowDto.setCustomerLevelNo(PERCENT.getConstant());
                            }
                        } else {
                            if (expandedParent.getHierarchyIndicator().equalsIgnoreCase(String.valueOf(object[10]))) {
                                if (INDICATOR_LOGIC_CUSTOMER_HIERARCHY.getConstant().equalsIgnoreCase(String.valueOf(object[10]))) {
                                    salesRowDto.setCustomerHierarchyNo(hierarchyNo);
                                    salesRowDto.setProductHierarchyNo(expandedParent.getProductHierarchyNo());
                                } else if (INDICATOR_LOGIC_PRODUCT_HIERARCHY.getConstant().equalsIgnoreCase(String.valueOf(object[10]))) {
                                    salesRowDto.setProductHierarchyNo(hierarchyNo);
                                    salesRowDto.setCustomerHierarchyNo(expandedParent.getCustomerHierarchyNo());
                                }
                            } else {
                                if (INDICATOR_LOGIC_CUSTOMER_HIERARCHY.getConstant().equalsIgnoreCase(String.valueOf(object[10]))) {
                                    salesRowDto.setCustomerHierarchyNo(hierarchyNo);
                                    salesRowDto.setProductHierarchyNo(expandedParent.getProductHierarchyNo());
                                } else if (INDICATOR_LOGIC_PRODUCT_HIERARCHY.getConstant().equalsIgnoreCase(String.valueOf(object[10]))) {
                                    salesRowDto.setProductHierarchyNo(hierarchyNo);
                                    salesRowDto.setCustomerHierarchyNo(expandedParent.getCustomerHierarchyNo());
                                }
                            }
                            if (INDICATOR_LOGIC_CUSTOMER_HIERARCHY.getConstant().equalsIgnoreCase(String.valueOf(object[10]))) {
                                salesRowDto.setCustomerLevelNo(String.valueOf(object[4]));
                                salesRowDto.setProductLevelNo(PERCENT.getConstant());
                            } else if (INDICATOR_LOGIC_PRODUCT_HIERARCHY.getConstant().equalsIgnoreCase(String.valueOf(object[10]))) {
                                salesRowDto.setProductLevelNo(String.valueOf(object[4]));
                                salesRowDto.setCustomerLevelNo(PERCENT.getConstant());
                            }
                        }
                    }
                    if (session != null) {
                        if (projSelDTO.isIsCustomHierarchy()) {
                            salesRowDto.setLevelName(session.getLevelValueDiscription(hierarchyNo, String.valueOf(parameters.get(H_INDICATOR.getConstant()))));
                        } else {
                            if (projSelDTO.getItemMap() != null) {
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
                        }
                    } else {
                        salesRowDto.setLevelName(hierarchyNo);
                    }
                    salesRowDto.setHierarchyNo(hierarchyNo);
                    salesRowDto.setLevelNo(UiUtils.parseStringToInteger(String.valueOf(object[4])));
                }
                salesRowDto.setHierarchialLevelName(String.valueOf(object[11]));
                if (maxLevelNo == salesRowDto.getLevelNo() || isTotalSales) {
                    salesRowDto.setParent(0);
                } else if (PRODUCT_HIERARCHY.getConstant().equalsIgnoreCase(String.valueOf(projSelDTO.getView()))
                        && LEVEL_BRAND.getConstant().equalsIgnoreCase(projSelDTO.getLevel())
                        && String.valueOf(object[11]).contains(Constant.BRAND_CAPS)) {
                    salesRowDto.setParent(0);
                } else if (PRODUCT_HIERARCHY.getConstant().equalsIgnoreCase(String.valueOf(projSelDTO.getView()))
                        && LEVEL_NDC.getConstant().equalsIgnoreCase(projSelDTO.getLevel())
                        && String.valueOf(object[11]).contains(Constant.NDC)) {
                    salesRowDto.setParent(0);
                } else {
                    salesRowDto.setParent(1);
                }
                salesRowDto.setHierarchyIndicator(String.valueOf(object[10]));
                salesRowDto.setCheckRecordCount((String.valueOf(object[7]).equals(Constant.NULL) || StringUtils.isBlank(String.valueOf(object[7])) ? Constant.DASH : String.valueOf(object[7])));
                salesRowDto.setCcpCount((String.valueOf(object[8]).equals(Constant.NULL) || StringUtils.isBlank(String.valueOf(object[8])) ? Constant.DASH : String.valueOf(object[8])));
                int value = Integer.valueOf((object[7] == null) ? Constant.DASH : object[7].toString());
                salesRowDto.addBooleanProperties(Constant.CHECK, (value >= Integer.valueOf(salesRowDto.getCcpCount())));
            }
            hierarchyNo = String.valueOf(object[3]);
            String key = StringUtils.EMPTY;
            if (QUARTERLY.getConstant().equalsIgnoreCase(projSelDTO.getFrequency())) {
                key = Constant.Q_SMALL + String.valueOf(object[5]) + String.valueOf(object[6]);
            }
            if (MONTHLY.getConstant().equalsIgnoreCase(projSelDTO.getFrequency())) {
                if (monthMap != null) {
                    key = monthMap.get(String.valueOf(object[5])) + String.valueOf(object[6]);
                }
            }
            if (ANNUAL.getConstant().equalsIgnoreCase(projSelDTO.getFrequency())
                    || ANNUALLY.getConstant().equalsIgnoreCase(projSelDTO.getFrequency())) {
                key = String.valueOf(object[6]);
            }
            salesRowDto.addStringProperties(key + columnGts, String.valueOf(object[0]));
            salesRowDto.addStringProperties(key + columnCs, String.valueOf(object[1]));
            salesRowDto.addStringProperties(key + columnPob, String.valueOf(object[2]));
            if (i == returnList.size() - 1) {
                resultList.add(salesRowDto);
            }
        }
        return resultList;
    }

    public boolean checkSelectAll(final String sessionId, final String projectionId, final String userId) throws PortalException, Exception {
        boolean returnValue = false;
        Map<String, Object> parameters = new HashMap<String, Object>();
        Map<String, String> input = new HashMap<String, String>();
        input.put("?SESSIONID?", sessionId);
        input.put("?USERID?", userId);
        input.put("?PMSID?", projectionId);
        parameters.put("input", input);
        parameters.put(INDICATOR.getConstant(), "checkSelectAll");
        List resultList = salesAllocationDAO.executeQuery(parameters);
        if (resultList != null) {
            if (!resultList.isEmpty() && resultList.size() == 1) {
                returnValue = (resultList.get(0) == null ? false : Boolean.valueOf(String.valueOf(resultList.get(0))));
            }
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
                LOGGER.info(" Executing Sales PRC_CH_SALES_ALLOCATION_PROJ procedure ");
                statement = connection.prepareCall("{call PRC_CH_SALES_ALLOCATION_PROJ(?,?,?)}");

                statement.setInt(1, projectionId);
                statement.setInt(2, Integer.parseInt(userId));
                statement.setInt(3, Integer.parseInt(sessionId));
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

    public void saveForProcedureCall(final ProjectionSelectionDTO projectionDTO) throws PortalException, Exception {
        Map<String, Object> parameters = new HashMap<String, Object>();
        Map<String, String> input = new HashMap<String, String>();
        input.put("?METHODOLOGY?", projectionDTO.getMethodology());
        input.put("?CALCPERIOD?", projectionDTO.getBaseline());
        input.put("?PMSID?", String.valueOf(projectionDTO.getProjectionId()));
        input.put("?SESSIONID?", String.valueOf(projectionDTO.getSessionId()));
        input.put("?USERID?", String.valueOf(projectionDTO.getUserId()));
        parameters.put(INDICATOR.getConstant(), "prepareProcedureCall");
        parameters.put(INPUT_MAP.getConstant(), input);
        salesAllocationDAO.executeQuery(parameters);
    }

    public List<String> getSelectedBaseLine(final String sessionId, final String projectionId, final String userId) throws PortalException, Exception {
        Map<String, Object> parameters = new HashMap<String, Object>();
        Map<String, String> input = new HashMap<String, String>();
        List<String> baseLineList = new ArrayList<String>();
        input.put("?SESSIONID?", sessionId);
        input.put("?USERID?", userId);
        input.put("?PMSID?", projectionId);
        parameters.put("input", input);
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

    public void uncheckAll(int projectionId, String sessionId, String userId) throws PortalException, Exception {
        Map<String, Object> parameters = new HashMap<String, Object>();
        Map<String, Object> input = new HashMap<String, Object>();
        parameters.put(INDICATOR.getConstant(), "uncheckAll");
        input.put("?PMSID?", String.valueOf(projectionId));
        input.put("?USERID?", String.valueOf(userId));
        input.put("?SESSIONID?", String.valueOf(sessionId));
        parameters.put(INPUT_MAP.getConstant(), input);
        salesAllocationDAO.executeQuery(parameters);
    }

    public boolean isCheckAll(final String sessionId, final String userId) throws PortalException, Exception {
        boolean returnValue = false;
        Map<String, Object> parameters = new HashMap<String, Object>();
        Map<String, String> input = new HashMap<String, String>();
        input.put("?SESSIONID?", sessionId);
        input.put("?USERID?", userId);
        parameters.put("input", input);
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
        String updatePeriod = StringUtils.EMPTY;
        Map<String, Object> input = new HashMap<String, Object>();
        if (MASS_FIELD_POB.getConstant().equals(indicator)) {

            inputValue = "((" + value + "*cast(AVG(TOTAL.GTS_SUM) as float))/100.00)";
        } else if (MASS_FIELD_CS.getConstant().equals(indicator)) {
            inputValue = value;
        }
        updatePeriod = formPeriod(startPeriodValue, endPeriodValue, isMassUpdate);
        input.put(H_INDICATOR.getConstant(), hierarchyIndicator);
        input.put("?PMSID?", String.valueOf(projectionDTO.getProjectionId()));
        input.put("?FREQUENCY?", projectionDTO.getFrequency());
        input.put("?UPDATEPERIOD?", updatePeriod);
        input.put("?INPUT?", String.valueOf(inputValue));
        input.put("?SESSIONID?", String.valueOf(projectionDTO.getSessionId()));
        return input;
    }

    private String formPeriod(final String startPeriodValue, final String endPeriodValue, final boolean isMassUpdate) {
        String period = StringUtils.EMPTY;
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

    public void updateRecord(final Map<String, Object> input, final ProjectionSelectionDTO projectionDTO, final SalesRowDto salesDto) throws PortalException, Exception {
        Map<String, Object> parameters = new HashMap<String, Object>();
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
            input.put("?HNO?", String.valueOf(salesDto.getHierarchyNo()));
            input.put("?LEVELNO?", String.valueOf(salesDto.getLevelNo() == 0 ? Constant.STRING_ONE : salesDto.getLevelNo()));
        } else {
            input.put("?RBSID?", projectionDTO.getCustRelationshipBuilderSid());
            input.put(LEVEL_NO_C.getConstant(), salesDto.getCustomerLevelNo());
            input.put(LEVEL_NO_P.getConstant(), salesDto.getProductLevelNo());
            input.put(H_INDICATOR.getConstant(), salesDto.getHierarchyIndicator());
            input.put("?HNOC?", (salesDto.getCustomerHierarchyNo()));
            input.put("?HNOP?", (salesDto.getProductHierarchyNo()));
            prepareCustomGenerateInputs(null, projectionDTO, parameters, input, 0, 0, false, true);
        }
    }

    public boolean checkHundredPercentage(final Map<String, Object> input, final ProjectionSelectionDTO projectionDTO, final SalesRowDto salesDto) throws PortalException, Exception {
        boolean returnValue = false;
        Map<String, Object> parameters = new HashMap<String, Object>();
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

    public Map<String, SalesRowDto> prepareSalesItemMap(ProjectionSelectionDTO projectionSelectionDTO) throws PortalException, Exception {
        Map<String, SalesRowDto> itemMap = new HashMap<String, SalesRowDto>();
        Map<String, Object> parameters = new HashMap<String, Object>();
        Map<String, String> input = new HashMap<String, String>();
        input.put("?PMSID?", String.valueOf(projectionSelectionDTO.getProjectionId()));
        input.put("?RBSID?", String.valueOf(projectionSelectionDTO.getProdRelationshipBuilderSid()));
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
                dto.setNdc11(String.valueOf(obj[2]));
                dto.setNdc8(String.valueOf(obj[3]));
                dto.setItemMapHno(String.valueOf(obj[4]));
                itemMap.put(String.valueOf(obj[4]), dto);
            }
        }

        return itemMap;
    }

    public void saveCheckRecord(final ProjectionSelectionDTO projectionDTO, final String checkedRecord, final boolean isSaveCheck, final String queryName) throws PortalException, Exception {
        try {
            Map<String, Object> parameters = new HashMap<String, Object>();
            Map<String, String> input = new HashMap<String, String>();
            Map<String, String> join = new HashMap<String, String>();
            String[] splitArray = null;
            if (!StringUtils.isBlank(checkedRecord)) {
                splitArray = checkedRecord.split(SPLIT_ARROW.getConstant());
            }
            input.put("?PMSID?", String.valueOf(projectionDTO.getProjectionId()));
            if (!projectionDTO.isIsCustomHierarchy()) {

                if (INDICATOR_LOGIC_CUSTOMER_HIERARCHY.getConstant().equalsIgnoreCase(projectionDTO.getHierarchyIndicator())) {
                    // inputs for customer hierarchy
                    input.put("?PHTABLE?", " PROJECTION_CUST_HIERARCHY ");
                    input.put("?RBSID?", projectionDTO.getCustRelationshipBuilderSid());
                } else if (INDICATOR_LOGIC_PRODUCT_HIERARCHY.getConstant().equalsIgnoreCase(projectionDTO.getHierarchyIndicator())) {
                    //inputs for product hierarchy
                    input.put("?PHTABLE?", " PROJECTION_PROD_HIERARCHY ");
                    input.put("?RBSID?", projectionDTO.getProdRelationshipBuilderSid());
                }
                if (splitArray != null) {
                    input.put("?HNO?", (StringUtils.isBlank(String.valueOf(splitArray[1]))) ? PERCENT.getConstant() : String.valueOf(splitArray[1]));
                    input.put("?LEVELNO?", (String.valueOf(splitArray[0]).equals(DASH)) ? PERCENT.getConstant() : String.valueOf(splitArray[0]));
                } else {
                    input.put("?HNO?", PERCENT.getConstant());
                    input.put("?LEVELNO?", PERCENT.getConstant());
                }
            } else {
                //Custom Hierarchy
                input.put("?RBSIDC?", projectionDTO.getCustRelationshipBuilderSid());
                input.put("?RBSIDP?", projectionDTO.getProdRelationshipBuilderSid());
                input.put("?CVSID?", String.valueOf(projectionDTO.getCustomId()));
                input.put(LEVEL_NO_C.getConstant(), splitArray[3]);
                input.put(LEVEL_NO_P.getConstant(), splitArray[4]);
                input.put(H_INDICATOR.getConstant(), splitArray[5]);
                input.put("?HNOC?", splitArray[6]);
                input.put("?HNOP?", splitArray[7]);
            }
            if (isSaveCheck) {
                if (splitArray != null && String.valueOf(splitArray[2]).equals(Constant.TRUE)) {
                    input.put("?CHECKRECORD?", String.valueOf(1));
                } else {
                    input.put("?CHECKRECORD?", String.valueOf(0));
                }
            }
            input.put("?SESSIONID?", String.valueOf(projectionDTO.getSessionId()));
            input.put("?USERID?", String.valueOf(projectionDTO.getUserId()));
            parameters.put(JOIN_MAP.getConstant(), join);
            parameters.put(INPUT_MAP.getConstant(), input);
            parameters.put(INDICATOR.getConstant(), queryName);
            salesAllocationDAO.executeQuery(parameters);
        } catch (Exception ex) {
            LOGGER.error(ex + " in saveCheckRecord");
        }
    }

    public void saveProjectionSelection(Map<String, Object> projectionSelectionDTO, int projectionId) throws Exception {
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
            LOGGER.info(ex.getCause());
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
            StringBuilder queryBuilder1 = new StringBuilder(StringUtils.EMPTY);
            String tableName = CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equals(projectionSelectionDTO.getScreenName()) ? "ST_M_SALES_PROJECTION_MASTER" : "ST_NM_SALES_PROJECTION_MASTER";
            queryBuilder1.append(" UPDATE ").append(tableName).append(" SET CHECK_RECORD=").append(val).append(" \n"
                    + "WHERE PROJECTION_DETAILS_SID in \n"
                    + "( Select PROJECTION_DETAILS_SID FROM PROJECTION_DETAILS WHERE PROJECTION_MASTER_SID=").append(projectionSelectionDTO.getProjectionId());
            queryBuilder1.append("  AND USER_ID='").append(projectionSelectionDTO.getUserId()).append("' AND SESSION_ID='").append(projectionSelectionDTO.getSessionId()).append("')");
            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
            salesProjectionDAO.executeUpdateQuery(queryBuilder1.toString());
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
            queryBuilder1.append("  AND USER_ID='").append(projectionSelectionDTO.getUserId()).append("' AND SESSION_ID='").append(projectionSelectionDTO.getSessionId()).append("'");
            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();

            salesProjectionDAO.executeUpdateQuery(queryBuilder1.toString());
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
    public String getCustomViewHierarchyIndicator(int customId, int levelNo) throws PortalException, Exception {
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

    public int saveCheckedRecords(final ProjectionSelectionDTO projectionSelectionDTO, final SalesRowDto salesDTO, boolean isChecked, boolean isCheckAll) throws PortalException, Exception {

        StringBuilder checkRecordsQuery = new StringBuilder();

        int count = 0;
        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        String masterTable = CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equals(projectionSelectionDTO.getScreenName()) ? "ST_M_SALES_PROJECTION_MASTER" : "ST_NM_SALES_PROJECTION_MASTER";
        if (projectionSelectionDTO.isIsCustomHierarchy()) {
            checkRecordsQuery.append("  UPDATE ").append(masterTable).append("  SET  ");
            if (isChecked) {
                checkRecordsQuery.append(" CHECK_RECORD=1 ");
            } else {
                checkRecordsQuery.append(" CHECK_RECORD=0 ");
            }
            checkRecordsQuery.append(" where PROJECTION_DETAILS_SID   in (  \n");
            checkRecordsQuery.append("  SELECT PROJECTION_DETAILS_SID FROM  PROJECTION_DETAILS WHERE CCP_DETAILS_SID IN  \n");
            checkRecordsQuery.append(getCCPForCustomView(salesDTO, projectionSelectionDTO.getCustomId(), projectionSelectionDTO.getProjectionId()));
            checkRecordsQuery.append(" AND PROJECTION_MASTER_SID = ").append(projectionSelectionDTO.getProjectionId()).append(")  AND USER_ID = '").append(projectionSelectionDTO.getUserId()).append("' AND SESSION_ID = '").append(projectionSelectionDTO.getSessionId()).append("'  \n");
            List list = salesProjectionDAO.executeUpdateQuery(checkRecordsQuery.toString());
            if (list.size() > 0) {
                count = Integer.parseInt(String.valueOf(list.get(0)));
            }
        } else {
            checkRecordsQuery.append("  UPDATE ").append(masterTable).append("  SET  ");
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
            checkRecordsQuery.append(" ) ) )  AND USER_ID = '").append(projectionSelectionDTO.getUserId()).append("' AND SESSION_ID = '").append(projectionSelectionDTO.getSessionId()).append("'  \n");
            List list = salesProjectionDAO.executeUpdateQuery(checkRecordsQuery.toString());
            if (list.size() > 0) {
                count = Integer.parseInt(String.valueOf(list.get(0)));
            }
        }
        return count;
    }

    private String getViewTypeQuery(String viewType) {
        String table;
        if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(viewType)) {
            table = " PROJECTION_CUST_HIERARCHY ";
        } else {
            table = " PROJECTION_PROD_HIERARCHY ";
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
            prodHierarchyNo = ((salesRowDto.getProductHierarchyNo().trim().isEmpty()) ? Constant.PERCENT : salesRowDto.getProductHierarchyNo());
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

    public void saveEditedRecsReturns(String propertyId, String editedValue, Double incOrDecPer, SalesRowDto salesDTO, ProjectionSelectionDTO projectionSelectionDTO, boolean checkAll, boolean isManualEntry) throws PortalException, Exception {
        LOGGER.info("Property Id-> " + propertyId + " EditedValue--> " + editedValue + " incOrDecPer--> " + incOrDecPer);
        Double actualAmount = 0.0;
        String detailsIdValues[];
        if (StringUtils.isNotBlank(editedValue) && !Constant.NULL.equals(editedValue)) {
            String saveQuery;

            saveQuery = SQlUtil.getQuery("RETURNS_MANNUAL_ENTRY_QUERY");
            editedValue = editedValue.replace(Constant.PERCENT, StringUtils.EMPTY);
            editedValue = editedValue.replace("$", StringUtils.EMPTY);
            editedValue = editedValue.replace(",", StringUtils.EMPTY);
            editedValue = editedValue.trim();
            String returnDetailsId = StringUtils.EMPTY;
            int frequencyDivision = projectionSelectionDTO.getFrequencyDivision();
            int frequencyValue = 0;
            int year = 0;
            int quater = 0;
            Double value = Double.valueOf(editedValue);
            String hierarchyNo = salesDTO.getHierarchyNo();
            String keyarr[] = propertyId.split("-");
            detailsIdValues = salesDTO.getReturnDetailsSid().split("\\s*,\\s*");
            String keyarray[] = propertyId.split("-");
            if (frequencyDivision == 1) {
                year = Integer.valueOf(keyarr[0]);
                keyarray[1] = StringUtils.EMPTY;
                frequencyValue = 12;
            } else if (frequencyDivision == 4) {
                keyarr[0] = (keyarr[0]).replace('q', ' ');
                frequencyValue = 3;
            } else if (frequencyDivision == 2) {
                keyarr[0] = (keyarr[0]).replace('s', ' ');
                frequencyValue = 6;
            } else if (frequencyDivision == 12) {
                keyarr[0] = (keyarr[0]).replace(keyarr[0], String.valueOf(getMonthNo(keyarr[0])));
                frequencyValue = 1;
            }

            if (frequencyDivision != 1) {
                year = Integer.parseInt(keyarr[1]);
                quater = Integer.parseInt(keyarr[0].trim());
            }
            returnDetailsId = salesDTO.getReturnDetailsSid();

            saveQuery = saveQuery.replace("@YEAR", StringUtils.EMPTY + year);
            saveQuery = saveQuery.replace("@PERIOD", StringUtils.EMPTY + quater);
            saveQuery = saveQuery.replace("@USER_ID", StringUtils.EMPTY + projectionSelectionDTO.getUserId());
            saveQuery = saveQuery.replace("@SESSION_ID", StringUtils.EMPTY + projectionSelectionDTO.getSessionId());
            saveQuery = addFrequencyInQuery(frequencyDivision, quater, saveQuery);
            String amountValue = StringUtils.EMPTY;
            if (propertyId.endsWith("ProjectedReturnAmount")) {
                saveQuery = saveQuery.replace("@RETURNS_DETAILS_SID", returnDetailsId);
                if (!incOrDecPer.isInfinite() && !incOrDecPer.isNaN()) {
                    actualAmount = incOrDecPer / 100;
                    amountValue = "PROJECTED_RETURN_AMOUNT+(PROJECTED_RETURN_AMOUNT*" + actualAmount + ")";
                    saveQuery = saveQuery.replace("@USER_ENTERED_VALUE", StringUtils.EMPTY + amountValue);
                } else {
                    actualAmount = Double.valueOf(editedValue) / (detailsIdValues.length);
                    amountValue = String.valueOf(actualAmount / frequencyValue);
                }
                saveQuery = saveQuery.replace("@USER_ENTERED_VALUE", StringUtils.EMPTY + amountValue);
                saveQuery = saveQuery.replace("@USER_ENTERED_PROPERTY_VALUE", "PROJECTED_RETURN_AMOUNT");
                salesAllocationDAO.executeUpdateQuery(saveQuery.toString());
            } else if (propertyId.endsWith(Constant.ProjectedRPU)) {
                saveQuery = saveQuery.replace("@USER_ENTERED_PROPERTY_VALUE", "PROJECTED_RPU");
                if (salesDTO.getReturnDetailsSid().split(",").length == 1) {
                    saveQuery = saveQuery.replace("@RETURNS_DETAILS_SID", returnDetailsId);
                    actualAmount = Double.valueOf(editedValue);
                    saveQuery = saveQuery.replace("@USER_ENTERED_VALUE", StringUtils.EMPTY + actualAmount);
                    salesAllocationDAO.executeUpdateQuery(saveQuery);
                } else {
                    List<Map> mapList = getActiveExFactorySalesAndUnits(projectionSelectionDTO, salesDTO, year, quater);
                    //This map contains the ACTIVE_EXFACTORY_SALES_AMOUNT of each NDC;
                    Map<String, Double> salesAmount = mapList.get(0);
                    //This map contains the ACTIVE_EXFACTORY_SALES_UNITS of each NDC;
                    Map<String, Double> unitsMap = mapList.get(1);

                    String bulkQuery = calculationLogic(projectionSelectionDTO, hierarchyNo, editedValue, saveQuery, salesAmount, unitsMap);
                    salesAllocationDAO.executeUpdateQuery(bulkQuery);
                }
            } else if (propertyId.endsWith("ProjectedReturn%")) {
                saveQuery = saveQuery.replace("@USER_ENTERED_PROPERTY_VALUE", "PROJECTED_RETURN_PERCENT");
                if (salesDTO.getReturnDetailsSid().split(",").length == 1) {
                    saveQuery = saveQuery.replace("@RETURNS_DETAILS_SID", returnDetailsId);
                    actualAmount = Double.valueOf(editedValue);
                    saveQuery = saveQuery.replace("@USER_ENTERED_VALUE", StringUtils.EMPTY + actualAmount);
                    salesAllocationDAO.executeUpdateQuery(saveQuery);
                } else {
                    List<Map> mapList = getActiveExFactorySalesAndUnits(projectionSelectionDTO, salesDTO, year, quater);
                    //This map contains the ACTIVE_EXFACTORY_SALES_AMOUNT of each NDC;
                    Map<String, Double> salesAmount = mapList.get(0);
                    String bulkQuery = calculationLogic(projectionSelectionDTO, hierarchyNo, editedValue, saveQuery, salesAmount, salesAmount);
                    salesAllocationDAO.executeUpdateQuery(bulkQuery.toString());
                }
            } else if (propertyId.endsWith("GrowthRate")) {
                saveQuery = saveQuery.replace("@USER_ENTERED_PROPERTY_VALUE", "GROWTH_RATE");
                saveQuery = saveQuery.replace("@RETURNS_DETAILS_SID", returnDetailsId);
                actualAmount = Double.valueOf(editedValue);
                saveQuery = saveQuery.replace("@USER_ENTERED_VALUE", StringUtils.EMPTY + actualAmount);
                salesAllocationDAO.executeUpdateQuery(saveQuery);
            }
            String refreshedPeriods = keyarray[0] + " " + keyarray[1];
            char first = Character.toUpperCase(refreshedPeriods.charAt(0));
            String refreshedPeriod = first + refreshedPeriods.substring(1);
            callRefreshProcedure(projectionSelectionDTO.getProjectionId(), salesDTO.getReturnDetailsSid(), refreshedPeriod, StringUtils.EMPTY + projectionSelectionDTO.getUserId(), StringUtils.EMPTY + projectionSelectionDTO.getSessionId(), false);
        }
    }

    public void saveEditedRecs(String propertyId, String editedValue, Double incOrDecPer, String changedValue, SalesRowDto salesDTO, ProjectionSelectionDTO projectionSelectionDTO, boolean checkAll, boolean isManualEntry) throws PortalException, Exception {

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

            Double value = Double.valueOf(editedValue);
            String hierarchyNo = salesDTO.getHierarchyNo();
            String keyarr[] = propertyId.split("-");
            if (frequencyDivision == 1) {
                year = Integer.valueOf(keyarr[0]);
            } else if (frequencyDivision == 4) {
                keyarr[0] = (keyarr[0]).replace('q', ' ');
            } else if (frequencyDivision == 2) {
                keyarr[0] = (keyarr[0]).replace('s', ' ');
            } else if (frequencyDivision == 12) {
                keyarr[0] = (keyarr[0]).replace(keyarr[0], String.valueOf(getMonthNo(keyarr[0])));
            }
            String column = frequencyDivision == 1 ? keyarr[1] : keyarr[2];
            if (frequencyDivision != 1) {
                year = Integer.parseInt(keyarr[1]);
                quator = Integer.parseInt(keyarr[0].trim());
            }
            int rowcount = MSalesProjection.rowCountMap.get(hierarchyNo);
            Double finalvalue = 0.0;
            String table = CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equals(projectionSelectionDTO.getScreenName()) ? "ST_M_SALES_PROJECTION" : "ST_NM_SALES_PROJECTION";
            switch (column) {
                case "AccountGrowth":
                    finalvalue = value;
                    saveQuery.append("  UPDATE ").append(table).append(" SET ACCOUNT_GROWTH='").append(finalvalue).append("' ");
                    break;
                case "ProductGrowth":
                    finalvalue = value;
                    saveQuery.append("  UPDATE ").append(table).append(" SET PRODUCT_GROWTH='").append(finalvalue).append("' ");
                    break;
                case "ProjectedSales":
                    if (!incOrDecPer.isInfinite() && !incOrDecPer.isNaN()) {
                        finalvalue = incOrDecPer / 100;
                        saveQuery.append("  UPDATE ").append(table).append(" SET PROJECTION_SALES=PROJECTION_SALES+(PROJECTION_SALES*").append(finalvalue).append(")");
                    } else {
                        finalvalue = value / rowcount;
                        saveQuery.append("  UPDATE ").append(table).append(" SET PROJECTION_SALES='").append(finalvalue).append("'");
                    }
                    break;
                case "ProjectedUnits":
                    if (!incOrDecPer.isInfinite() && !incOrDecPer.isNaN()) {
                        finalvalue = incOrDecPer / 100;
                        saveQuery.append("DECLARE @PROJECTION_UNITS NUMERIC(22, 6)\n"
                                + "SET @PROJECTION_UNITS=(SELECT Sum(PROJECTION_UNITS)\n"
                                + "FROM   "+table+" " +" @Replace_Value" +"   )");
                        saveQuery.append("  UPDATE ").append(table).append(" SET PROJECTION_UNITS=PROJECTION_UNITS + ( PROJECTION_UNITS * ( ( ( ( "+value+" - @PROJECTION_UNITS ) / NULLIF(@PROJECTION_UNITS, 0) ) * 100 ) / 100 ) )");
                    } else {
                        finalvalue = value / rowcount;
                        saveQuery.append("  UPDATE ").append(table).append(" SET PROJECTION_UNITS='").append(finalvalue).append("' ");
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
            } else if (frequencyDivision == 4) {
                updateQuery.append("AND PERIOD_SID in(SELECT PERIOD_SID FROM PERIOD where YEAR ='").append(year).append("'  and QUARTER ='").append(quator).append("' ) ");
            } else if (frequencyDivision == 2) {
                updateQuery.append("AND PERIOD_SID in(SELECT PERIOD_SID FROM PERIOD where YEAR ='").append(year).append("'  and SEMI_ANNUAL ='").append(quator).append("' ) ");
            } else if (frequencyDivision == 12) {
                updateQuery.append("AND PERIOD_SID in(SELECT PERIOD_SID FROM PERIOD where YEAR ='").append(year).append("'  and MONTH ='").append(quator).append("' ) ");
            }
            updateQuery.append(" AND USER_ID = '").append(projectionSelectionDTO.getUserId()).append("'  AND SESSION_ID ='").append(projectionSelectionDTO.getSessionId()).append("' ");
            
            saveQuery.append(updateQuery);
            String finQuery=saveQuery.toString();
            if("ProjectedUnits".equalsIgnoreCase(column)){
            finQuery=finQuery.replace("@Replace_Value",updateQuery.toString());
            }
            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
//            System.out.println("saveQuery:===================================>"+finQuery);
            salesProjectionDAO.executeUpdateQuery(finQuery);
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
    public void saveRecordsForManualEntry(String changedProperty, final ProjectionSelectionDTO projectionSelectionDTO, final SalesRowDto salesDTO, boolean checkAll) throws Exception {

        if (StringUtils.isNotBlank(changedProperty) && !Constant.NULL.equals(changedProperty)) {
            saveCheckedRecords(projectionSelectionDTO, salesDTO, true, checkAll);
            callManualEntry(projectionSelectionDTO, changedProperty.contains(Constant.SALES_SMALL) ? Constant.SALES_SMALL : Constant.UNITS_SMALL);
            if (!(Boolean) salesDTO.getPropertyValue(Constant.CHECK)) {
                saveCheckedRecords(projectionSelectionDTO, salesDTO, false, checkAll);
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
            LOGGER.info("Entering callManualEntryProcedure  ::::");
            if (connection != null) {
                statement = connection.prepareCall("{call PRC_SALES_PROJ_MANUAL_ENTRY (?,?,?,?)}");
                LOGGER.info("Projection Id --> " + session.getProjectionId());
                LOGGER.info("User Id -->       " + session.getUserId());
                LOGGER.info("Session Id -->    " + session.getSessionId());
                LOGGER.info("changedProperty-> " + changedProperty);

                statement.setObject(1, session.getProjectionId()); //  @PROJECTION_SID
                statement.setObject(2, Integer.parseInt(session.getUserId())); //  @USER_ID
                statement.setObject(3, Integer.parseInt(session.getSessionId())); //  @SESSION_ID
                statement.setObject(4, changedProperty);
                status = statement.execute();
            }
            LOGGER.info("Ending callManualEntryProcedure return  staus ::::" + status);
        } catch (Exception ex) {
            LOGGER.error(new Date() + ex.getMessage());
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
    public void saveOnMassUpdate(final ProjectionSelectionDTO projectionSelectionDTO, final int startYear, final int endYear, final int startQuarter, final int endQuarter, final String value, final String growth) throws PortalException, Exception {

        String projectionTable = CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equals(projectionSelectionDTO.getScreenName()) ? "ST_M_SALES_PROJECTION" : "ST_NM_SALES_PROJECTION";
        String masterTable = CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equals(projectionSelectionDTO.getScreenName()) ? "ST_M_SALES_PROJECTION_MASTER" : "ST_NM_SALES_PROJECTION_MASTER";

        String updateQuery = CustomSQLUtil.get("massUpdate");
        updateQuery = updateQuery.replace("@USER_ID", String.valueOf(projectionSelectionDTO.getUserId()));
        updateQuery = updateQuery.replace("@SESSION_ID", String.valueOf(projectionSelectionDTO.getSessionId()));
        updateQuery = updateQuery.replace("@START_YEAR", String.valueOf(startYear));
        updateQuery = updateQuery.replace("@END_YEAR", String.valueOf(endYear));
        updateQuery = updateQuery.replace("@PROJECTION_TABLE", projectionTable);
        updateQuery = updateQuery.replace("@MASTER_TABLE", masterTable);

        if (growth.equals(Constant.PRODUCT_GROWTH)) {
            updateQuery = updateQuery.replace("@COLUMN_NAME", "PRODUCT_GROWTH");
            updateQuery = updateQuery.replace("@TABLE_NAME", "SP");
            updateQuery = updateQuery.replace("@VALUE", String.valueOf(value));
        } else if (growth.equals(Constant.ACCOUNT_GROWTH)) {
            updateQuery = updateQuery.replace("@COLUMN_NAME", "ACCOUNT_GROWTH");
            updateQuery = updateQuery.replace("@TABLE_NAME", "SP");
            updateQuery = updateQuery.replace("@VALUE", String.valueOf(value));
        } else if (growth.equals(Constant.GROUPFCAPS)) {
            updateQuery = updateQuery.replace("@COLUMN_NAME", "USER_GROUP");
            updateQuery = updateQuery.replace("@TABLE_NAME", "SPM");
            StringBuilder finalValue = new StringBuilder();
            finalValue.append("'").append(String.valueOf(value)).append("'");
            updateQuery = updateQuery.replace("@VALUE", finalValue.toString());
        }
        switch (projectionSelectionDTO.getFrequencyDivision()) {
            case 1:
                updateQuery = updateQuery.replace("@START_FREQUENCY", " AND \"MONTH\" = 0 ");
                updateQuery = updateQuery.replace("@END_FREQUENCY", " AND \"MONTH\" = 0 ");
                break;
            case 12:
                updateQuery = updateQuery.replace("@START_FREQUENCY", " AND MONTH < " + startQuarter);
                updateQuery = updateQuery.replace("@END_FREQUENCY", " AND MONTH > " + endQuarter);
                break;
            case 4:
                updateQuery = updateQuery.replace("@START_FREQUENCY", " AND QUARTER < " + startQuarter);
                updateQuery = updateQuery.replace("@END_FREQUENCY", " AND QUARTER > " + endQuarter);
                break;
            case 2:
                updateQuery = updateQuery.replace("@START_FREQUENCY", " AND SEMI_ANNUAL < " + startQuarter);
                updateQuery = updateQuery.replace("@END_FREQUENCY", " AND SEMI_ANNUAL > " + endQuarter);
                break;
        }
        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        salesProjectionDAO.executeUpdateQuery(updateQuery);
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
        LOGGER.info("EnteredValue--> " + enteredValue);
        Double actualAmount = 0.0;
        int frequencyValue = 0;
        String frequency = StringUtils.EMPTY;
        try {
            String updateQuery = StringUtils.EMPTY;
            if (flag || updateVariable.equals(Constant.GROWTH_RATE)) {
                updateQuery = "UPDATE SP SET @USER_ENTERED_PROPERTY_VALUE=@USER_ENTERED_VALUE @VARIABLE FROM ST_RETURNS_PROJ_DETAILS SP   JOIN ST_RETURNS_PROJ_MASTER SPM ON SPM.RETURNS_DETAILS_SID = SP.RETURNS_DETAILS_SID   JOIN PERIOD P ON P.PERIOD_SID = SP.PERIOD_SID    WHERE SP.USER_ID =@USER_ID AND SP.SESSION_ID =@SESSION_ID AND   SPM.USER_ID = @USER_ID AND SPM.SESSION_ID = @SESSION_ID    AND SPM.CHECK_RECORD = 1 AND P.PERIOD_SID IN (@PERIOD_QUERY);";
            } else {

                updateQuery = SQlUtil.getQuery("RETURNS_MANNUAL_ENTRY_QUERY");
            }

            String periodQuery = SQlUtil.getQuery("MASS_UPDATE_PERIOD_QUERY").replace("@START_YEAR", String.valueOf(startYear))
                    .replace("@END_YEAR", String.valueOf(endYear))
                    .replace("@START_QUARTER", String.valueOf(startQuarter))
                    .replace("@END_QUATER", String.valueOf(endQuarter));
            updateQuery = updateQuery.replace("@USER_ID", String.valueOf(projectionSelectionDTO.getUserId()))
                    .replace("@SESSION_ID", String.valueOf(projectionSelectionDTO.getSessionId()));

            switch (projectionSelectionDTO.getFrequencyDivision()) {
                case 1:
                    frequencyValue = 12;
                    periodQuery = periodQuery.replace("@START_FREQUENCY", " AND \"MONTH\" = 0 ")
                            .replace("@END_FREQUENCY", " AND \"MONTH\" = 0 ");
                    break;
                case 12:
                    frequencyValue = 1;
                    periodQuery = periodQuery.replace("@START_FREQUENCY", " AND MONTH < " + startQuarter)
                            .replace("@END_FREQUENCY", " AND MONTH > " + endQuarter);
                    frequency = "P.MONTH,";
                    break;
                case 4:
                    frequencyValue = 3;
                    periodQuery = periodQuery.replace("@START_FREQUENCY", " AND QUARTER < " + startQuarter)
                            .replace("@END_FREQUENCY", " AND QUARTER > " + endQuarter);
                    frequency = "P.QUARTER,";
                    break;
                case 2:
                    frequencyValue = 6;
                    periodQuery = periodQuery.replace("@START_FREQUENCY", " AND SEMI_ANNUAL < " + startQuarter)
                            .replace("@END_FREQUENCY", " AND SEMI_ANNUAL > " + endQuarter);
                    frequency = "P.SEMI_ANNUAL,";
                    break;
            }
            updateQuery = updateQuery.replace("@PERIOD_QUERY", periodQuery);
            if (updateVariable.equals(Constant.PROJECTED_RETURN_AMT)) {
                updateQuery = updateQuery.replace("@VARIABLE", ",REFRESHED_NAME='PROJECTED_RETURN_AMOUNT'");
                updateQuery = updateQuery.replace("@USER_ENTERED_PROPERTY_VALUE", "PROJECTED_RETURN_AMOUNT").replace("@RETURNS_DETAILS_SID", salesDTO.getReturnDetailsSid());
                String query = StringUtils.EMPTY;
                String bulkQuery = StringUtils.EMPTY;
                String amountValue = StringUtils.EMPTY;
                if (salesDTO.getReturnDetailsSid().split(",").length == 1) {
                    updateQuery = updateQuery.replace("@RETURNS_DETAILS_SID", salesDTO.getReturnDetailsSid());
                    actualAmount = Double.valueOf(enteredValue) / frequencyValue;
                    updateQuery = updateQuery.replace("@USER_ENTERED_VALUE", StringUtils.EMPTY + actualAmount).replace("@VARIABLE", ",REFRESHED_NAME='PROJECTED_RETURN_PERCENT'");
                    salesAllocationDAO.executeUpdateQuery(updateQuery);
                } else {
                    for (Map.Entry<String, Double> entrys : selectedValues.entrySet()) {

                        if (projectionSelectionDTO.getFrequencyDivision() == 1) {
                            query = updateQuery.replace("@YEAR", StringUtils.EMPTY + entrys.getKey().split(",")[0]);
                            query = addFrequencyInQuery(projectionSelectionDTO.getFrequencyDivision(), Integer.valueOf(entrys.getKey().split(",")[0]), query);
                        } else {
                            query = updateQuery.replace("@YEAR", StringUtils.EMPTY + entrys.getKey().split(",")[0]).replace("@PERIOD", StringUtils.EMPTY + entrys.getKey().split(",")[1]);
                            query = addFrequencyInQuery(projectionSelectionDTO.getFrequencyDivision(), Integer.valueOf(entrys.getKey().split(",")[1]), query);
                        }
                        if (!entrys.getValue().isInfinite() && !entrys.getValue().isNaN()) {
                            actualAmount = entrys.getValue() / 100;
                            amountValue = "PROJECTED_RETURN_AMOUNT+(PROJECTED_RETURN_AMOUNT*" + actualAmount + ")";
                        } else {
                            actualAmount = Double.valueOf(enteredValue) / (salesDTO.getReturnDetailsSid().split(",").length);
                            amountValue = String.valueOf(actualAmount / frequencyValue);
                        }
                        bulkQuery += query.replace("@USER_ENTERED_PROPERTY_VALUE", "PROJECTED_RETURN_AMOUNT").replace("@USER_ENTERED_VALUE", StringUtils.EMPTY + amountValue);
                    }
                    salesAllocationDAO.executeUpdateQuery(bulkQuery);
                }
            } else if (updateVariable.equals(Constant.PROJECTED_RETURN_PER)) {
                updateQuery = updateQuery.replace("@USER_ENTERED_PROPERTY_VALUE", "PROJECTED_RETURN_PERCENT");
                if (salesDTO.getReturnDetailsSid().split(",").length == 1) {
                    updateQuery = updateQuery.replace("@RETURNS_DETAILS_SID", salesDTO.getReturnDetailsSid());
                    actualAmount = Double.valueOf(enteredValue);
                    updateQuery = updateQuery.replace("@USER_ENTERED_VALUE", StringUtils.EMPTY + actualAmount).replace("@VARIABLE", ",REFRESHED_NAME='PROJECTED_RETURN_PERCENT'");

                    salesAllocationDAO.executeUpdateQuery(updateQuery.toString());
                } else {
                    List<Map> mapList = getActiveExFactorySalesAndUnitsForMassUpdate(projectionSelectionDTO, periodQuery, frequency);
                    //This map contains the ACTIVE_EXFACTORY_SALES_AMOUNT of each NDC;
                    Map<String, Map<String, Double>> salesMap = mapList.get(0);
                    String bulkQuery = StringUtils.EMPTY;
                    String query = StringUtils.EMPTY;
                    for (Map.Entry<String, Map<String, Double>> entrys : salesMap.entrySet()) {
                        query = updateQuery.replace("@YEAR", StringUtils.EMPTY + entrys.getKey().split(",")[0]).replace("@PERIOD", StringUtils.EMPTY + entrys.getKey().split(",")[1]);
                        query = addFrequencyInQuery(projectionSelectionDTO.getFrequencyDivision(), Integer.valueOf(entrys.getKey().split(",")[1]), query);
                        bulkQuery += calculationLogic(projectionSelectionDTO, salesDTO.getHierarchyNo(), enteredValue, query, entrys.getValue(), entrys.getValue());
                    }
                    salesAllocationDAO.executeUpdateQuery(bulkQuery);
                }
            } else if (updateVariable.equals(Constant.PROJECTED_RPU)) {
                updateQuery = updateQuery.replace("@USER_ENTERED_PROPERTY_VALUE", "PROJECTED_RPU");
                if (salesDTO.getReturnDetailsSid().split(",").length == 1) {
                    updateQuery = updateQuery.replace("@RETURNS_DETAILS_SID", salesDTO.getReturnDetailsSid());
                    actualAmount = Double.valueOf(enteredValue);
                    updateQuery = updateQuery.replace("@USER_ENTERED_VALUE", StringUtils.EMPTY + actualAmount).replace("@VARIABLE", ",REFRESHED_NAME='PROJECTED_RPU'");;;
                    salesAllocationDAO.executeUpdateQuery(updateQuery.toString());
                } else {
                    List<Map> mapList = getActiveExFactorySalesAndUnitsForMassUpdate(projectionSelectionDTO, periodQuery, frequency);
                    //This map contains the ACTIVE_EXFACTORY_SALES_AMOUNT of each NDC;
                    Map<String, Map<String, Double>> salesMap = mapList.get(0);
                    //This map contains the ACTIVE_EXFACTORY_SALES_UNITS of each NDC;
                    Map<String, Map<String, Double>> unitsMap = mapList.get(1);
                    String bulkQuery = StringUtils.EMPTY;
                    String query = StringUtils.EMPTY;
                    for (Map.Entry<String, Map<String, Double>> entrys : salesMap.entrySet()) {
                        query = updateQuery.replace("@YEAR", StringUtils.EMPTY + entrys.getKey().split(",")[0]).replace("@PERIOD", StringUtils.EMPTY + entrys.getKey().split(",")[1]);
                        query = addFrequencyInQuery(projectionSelectionDTO.getFrequencyDivision(), Integer.valueOf(entrys.getKey().split(",")[1]), query);
                        bulkQuery += calculationLogic(projectionSelectionDTO, salesDTO.getHierarchyNo(), enteredValue, query, entrys.getValue(), unitsMap.get(entrys.getKey()));
                    }
                    salesAllocationDAO.executeUpdateQuery(bulkQuery);
                }
            } else if (updateVariable.equals(Constant.GROWTH_RATE)) {
                actualAmount = Double.valueOf(enteredValue);
                updateQuery = updateQuery.replace("@USER_ENTERED_PROPERTY_VALUE", "GROWTH_RATE");
                updateQuery = updateQuery.replace("@USER_ENTERED_VALUE", StringUtils.EMPTY + actualAmount).replace("@VARIABLE", StringUtils.EMPTY);

                salesAllocationDAO.executeUpdateQuery(updateQuery);
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
            final String adjBasis, final String adsVar, final String adsMeth, final String historyPeriods, String projectionPeriods) throws SystemException, SQLException, Exception {
        saveAdjustmentSelections(projectionSelectionDTO, adjType, adjVal, adjBasis, adsVar, adsMeth);
        callAdjustmentProcedure(projectionSelectionDTO, historyPeriods, projectionPeriods);
    }

    /**
     *
     * @param projectionSelectionDTO
     * @param adjType
     * @param adjVal
     * @param adjBasis
     * @param adsVar
     * @param adsMeth
     * @throws PortalException
     * @throws Exception
     */
    public void saveAdjustmentSelections(final ProjectionSelectionDTO projectionSelectionDTO, final String adjType, final String adjVal,
            final String adjBasis, final String adsVar, final String adsMeth) throws PortalException, Exception {

        String projectionTable = CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equals(projectionSelectionDTO.getScreenName()) ? "ST_M_SALES_PROJECTION" : "ST_NM_SALES_PROJECTION";
        String masterTable = CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equals(projectionSelectionDTO.getScreenName()) ? "ST_M_SALES_PROJECTION_MASTER" : "ST_NM_SALES_PROJECTION_MASTER";
        String updateQuery = CustomSQLUtil.get("saveAdjustmentSelection");
        updateQuery = updateQuery.replace("@ADJUSTMENT_TYPE", adjType);
        updateQuery = updateQuery.replace("@ADJUSTMENT_VALUES", adjVal);
        updateQuery = updateQuery.replace("@ADJUSTMENT_BASIS", adjBasis);
        updateQuery = updateQuery.replace("@ADJUSTMENT_VARIABLE", adsVar);
        updateQuery = updateQuery.replace("@ADJUSTMENT_METHODOLOGY", adsMeth);
        updateQuery = updateQuery.replace("@USER_ID", String.valueOf(projectionSelectionDTO.getUserId()));
        updateQuery = updateQuery.replace("@SESSION_ID", String.valueOf(projectionSelectionDTO.getSessionId()));
        updateQuery = updateQuery.replace("@PROJECTION_TABLE", projectionTable);
        updateQuery = updateQuery.replace("@MASTER_TABLE", masterTable);
        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        salesProjectionDAO.executeUpdateQuery(updateQuery);

    }

    public AlternateLookupSource searchAlternateCustomerAndBrand(final CustomFieldGroup searchBinder, final String searchType, boolean flag) throws SystemException, Exception {

        List<CompanyMaster> resultTPList = new ArrayList<CompanyMaster>();
        List<BrandMaster> resultBrandList = new ArrayList<BrandMaster>();
        final AlternateLookupSource alternate = new AlternateLookupSource();

        LOGGER.info("Entering searchAlternateBrand  ::::");

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
            LOGGER.info("Size of resultTPList " + resultTPList.size());

            List<ContractBrandDTO> temp = new ArrayList<ContractBrandDTO>();
            temp = getAlternateTP(resultTPList);
            alternate.setContractcustomersList(temp);
        }

        LOGGER.info("Ending searchAlternateBrand    ::::");
        return alternate;
    }

    public List<ContractBrandDTO> getAlternateTP(final List<CompanyMaster> list) {
        final List<ContractBrandDTO> resultList = new ArrayList<ContractBrandDTO>();

        LOGGER.info("Entering getAlternateTP  ::::");

        final int index = list.size();

        for (int i = 0; i < index; i++) {
            final CompanyMaster comapny = list.get(i);
            final ContractBrandDTO alternateTP = new ContractBrandDTO();
            alternateTP.setCustomerId(comapny.getCompanyNo());
            alternateTP.setContractHolder(comapny.getCompanyName());
            alternateTP.setContractSid(comapny.getCompanyMasterSid());
            resultList.add(alternateTP);
        }

        LOGGER.info("Ending getAlternateTP return  size ::::" + resultList.size());
        return resultList;
    }

    public List searchAlternateContract(ContractBrandDTO contractBrandDTO) throws PortalException, Exception {

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

        List<ContractBrandDTO> resultList = new ArrayList<ContractBrandDTO>();

        for (Object list1 : list) {
            ContractBrandDTO contractBrandDTO = new ContractBrandDTO();
            Object[] obj = (Object[]) list1;
            contractBrandDTO.setCustomer(String.valueOf(obj[0]));
            contractBrandDTO.setContractNumber(String.valueOf(obj[1]));
            contractBrandDTO.setContractName(String.valueOf(obj[2]));
            contractBrandDTO.setCustomerId(String.valueOf(obj[3]));
            contractBrandDTO.setContractSid(Integer.valueOf(String.valueOf(obj[4])));
            resultList.add(contractBrandDTO);
        }
        list = null;

        return resultList;
    }

    public List loadAlternateCustomer(String marketType) throws SystemException, Exception {
        StringBuilder query = new StringBuilder();
        query.append("SELECT DISTINCT CM.COMPANY_MASTER_SID,CM.COMPANY_NO FROM dbo.CCP_DETAILS CCP \n"
                + " JOIN dbo.COMPANY_MASTER CM ON CM.COMPANY_MASTER_SID = CCP.COMPANY_MASTER_SID \n"
                + " JOIN dbo.CONTRACT_MASTER CO ON CO.CONTRACT_MASTER_SID = CCP.CONTRACT_MASTER_SID \n"
                + " JOIN dbo.HELPER_TABLE HT ON HT.HELPER_TABLE_SID=CO.CONTRACT_TYPE AND HT.LIST_NAME = 'CONTRACT_TYPE' AND \n"
                + " HT.DESCRIPTION IN ('FFS','Medicaid FFS','SPAP','ADAP','PHS','Managed Medicaid','MM','Federal');");

        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        return (List) salesProjectionDAO.executeSelectQuery(query.toString());

    }

    public List loadAlternateBrand(String brandName) throws PortalException, Exception {

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

        List<ContractBrandDTO> resultList = new ArrayList<ContractBrandDTO>();

        for (Object list1 : list) {
            ContractBrandDTO contractBrandDTO = new ContractBrandDTO();
            Object[] obj = (Object[]) list1;
            contractBrandDTO.setBrandMasterSid(Integer.valueOf(String.valueOf(obj[0])));
            contractBrandDTO.setBrand(String.valueOf(obj[1]));
            contractBrandDTO.setBrandSid(String.valueOf(obj[2]));
            resultList.add(contractBrandDTO);
        }
        list = null;

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
    public boolean callAdjustmentProcedure(final ProjectionSelectionDTO projectionSelectionDTO, final String historyPeriods, final String projectionPeriods) throws SystemException, SQLException {

        boolean status = false;

        final DataSourceConnection dataSourceConnection = DataSourceConnection.getInstance();
        Connection connection = null;
        CallableStatement statement = null;
        try {
            connection = dataSourceConnection.getConnection();

            if (connection != null) {
                LOGGER.info("BASLINE_PERIODS" + historyPeriods);
                LOGGER.info("SELECTED_PERIODS" + projectionPeriods);
                LOGGER.info("PROJECTION_SID" + projectionSelectionDTO.getProjectionId());
                LOGGER.info("Frequency" + projectionSelectionDTO.getFrequency());
                LOGGER.info("USER_ID" + projectionSelectionDTO.getUserId());
                LOGGER.info("SESSION_ID" + projectionSelectionDTO.getSessionId());
                statement = connection.prepareCall("{call PRC_SALES_ADJUSTMENT (?,?,?,?,?,?)}");
                statement.setObject(1, historyPeriods); //  @BASLINE_PERIODS 
                statement.setObject(2, projectionPeriods); //  @SELECTED_PERIODS
                statement.setObject(3, projectionSelectionDTO.getProjectionId()); //  @PROJECTION_SID
                statement.setObject(4, projectionSelectionDTO.getFrequency());//Frequency
                statement.setObject(5, projectionSelectionDTO.getUserId()); //  @USER_ID
                statement.setObject(6, projectionSelectionDTO.getSessionId()); //  @SESSION_ID
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
        try {
            saveSelectionForCalculation(projectionSelectionDTO, methodology, calcPeriods, calcBased, startPeriodSID, endPeriodSID, allocationBasis);
            isSalesCalculated = callCalculationProcedure(projectionSelectionDTO);
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
     * @throws Exception
     */
    public boolean callCalculationProcedure(final ProjectionSelectionDTO projectionSelectionDTO) throws SQLException, Exception {
        LOGGER.info("callCalculationProcedure PRC_SALES_PROJECTION ");
        LOGGER.info("Projection ID --- " + projectionSelectionDTO.getProjectionId());
        LOGGER.info("UserID ---        " + projectionSelectionDTO.getUserId());
        LOGGER.info("Session ID ----   " + projectionSelectionDTO.getSessionId());
        LOGGER.info("Frequency ----    " + projectionSelectionDTO.getFrequency());
        boolean isCalculated = false;
        final DataSourceConnection dataSourceConnection = DataSourceConnection.getInstance();
        Connection connection = dataSourceConnection.getConnection();
        CallableStatement statement = null;
        if (connection != null) {
            statement = connection.prepareCall("{call PRC_SALES_PROJECTION (?,?,?,?)}");
            statement.setObject(1, projectionSelectionDTO.getProjectionId());
            statement.setObject(2, projectionSelectionDTO.getUserId());
            statement.setObject(3, projectionSelectionDTO.getSessionId());
            statement.setObject(4, projectionSelectionDTO.getFrequency());
            try {
                statement.execute();
                isCalculated = true;
            } catch (SQLException ex) {
                LOGGER.error(ex);
            } finally {
                statement.close();
                connection.close();
            }
        }
        return isCalculated;
    }

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
            final String startPeriodSID, final String endPeriodSID, final String allocationBasis) throws PortalException, Exception {

        String masterTable = CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equals(projectionSelectionDTO.getScreenName()) ? "ST_M_SALES_PROJECTION_MASTER" : "ST_NM_SALES_PROJECTION_MASTER";
        String updateQuery = CustomSQLUtil.get("saveCalculationSelection");
        updateQuery = updateQuery.replace("@METHODOLOGY", methodology);
        updateQuery = updateQuery.replace("@CALCULATION_PERIODS", calcPeriods);
        updateQuery = updateQuery.replace("@CALCULATION_BASED", calcBased);
        updateQuery = updateQuery.replace("@USER_ID", String.valueOf(projectionSelectionDTO.getUserId()));
        updateQuery = updateQuery.replace("@SESSION_ID", String.valueOf(projectionSelectionDTO.getSessionId()));
        updateQuery = updateQuery.replace("@PROJECTION_MASTER_SID", String.valueOf(projectionSelectionDTO.getProjectionId()));
        updateQuery = updateQuery.replace("@MASTER_TABLE", masterTable);
        updateQuery = updateQuery.replace("@startperiod", startPeriodSID);
        updateQuery = updateQuery.replace("@endperiod", StringUtils.isEmpty(endPeriodSID) ? Constant.NULL_CAPS : endPeriodSID);
        updateQuery = updateQuery.replace("@allocationbasis", allocationBasis);
        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        salesProjectionDAO.executeUpdateQuery(updateQuery);

    }

    public String loadTotalLives(int projectionId) throws Exception {
        BigDecimal lives = new BigDecimal(0.0);
        List<String> list = getTotalLives(projectionId, false);
        if (list != null) {
            for (String live : list) {
                lives = lives.add(BigDecimal.valueOf(Double.parseDouble(live)));
            }
        }
        return String.valueOf(lives);
    }

    public List getTotalLives(int projectionId, boolean chartflag) throws PortalException, Exception {

        List list = new ArrayList();
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
    public void saveSalesGroup(final ProjectionSelectionDTO projectionSelectionDTO, final String hierarchyNo, String userGroupValue) throws PortalException, Exception {
        StringBuilder saveGroupQuery = new StringBuilder(StringUtils.EMPTY);
        saveGroupQuery.append("UPDATE ST_NM_SALES_PROJECTION_MASTER SET USER_GROUP = '");
        saveGroupQuery.append(userGroupValue);
        saveGroupQuery.append("' WHERE PROJECTION_DETAILS_SID IN (SELECT PD.PROJECTION_DETAILS_SID FROM RELATIONSHIP_LEVEL_DEFINITION RLD JOIN CCP_MAP CCM ON RLD.RELATIONSHIP_LEVEL_SID = CCM.RELATIONSHIP_LEVEL_SID AND RLD.HIERARCHY_NO LIKE '");
        saveGroupQuery.append(hierarchyNo);
        saveGroupQuery.append("' JOIN PROJECTION_DETAILS PD ON CCM.CCP_DETAILS_SID = PD.CCP_DETAILS_SID AND PD.PROJECTION_MASTER_SID = ");
        saveGroupQuery.append(projectionSelectionDTO.getProjectionId());
        saveGroupQuery.append(" JOIN ST_NM_SALES_PROJECTION_MASTER SPM ON SPM.PROJECTION_DETAILS_SID = SPM.PROJECTION_DETAILS_SID WHERE SPM.USER_ID = ");
        saveGroupQuery.append(projectionSelectionDTO.getUserId());
        saveGroupQuery.append(" AND SPM.SESSION_ID = ");
        saveGroupQuery.append(projectionSelectionDTO.getSessionId());
        saveGroupQuery.append(");");
        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        salesProjectionDAO.executeUpdateQuery(saveGroupQuery.toString());
    }

    /**
     *
     * @param projectionSelectionDTO
     * @return
     * @throws PortalException
     * @throws Exception
     */
    public List loadSalesGroup(final ProjectionSelectionDTO projectionSelectionDTO) throws PortalException, Exception {
        StringBuilder query = new StringBuilder(StringUtils.EMPTY);
        query.append("SELECT STM.USER_GROUP FROM ST_NM_SALES_PROJECTION_MASTER STM JOIN PROJECTION_DETAILS PD ON PD.PROJECTION_DETAILS_SID = STM.PROJECTION_DETAILS_SID AND PD.PROJECTION_MASTER_SID = ");
        query.append(projectionSelectionDTO.getProjectionId());
        query.append(" WHERE USER_ID =");
        query.append(projectionSelectionDTO.getUserId());
        query.append(" AND SESSION_ID =");
        query.append(projectionSelectionDTO.getSessionId()).append(" ;");
        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        List list = (List) salesProjectionDAO.executeSelectQuery(query.toString());
        List finalList = new ArrayList();
        for (Object object : list) {
            finalList.add(String.valueOf(object));
        }
        return finalList;
    }

    private int getMonthNo(String month) {
        Map<String, Integer> asd = new HashMap<String, Integer>();
        asd.put("jan", 1);
        asd.put("feb", 2);
        asd.put("mar", 3);
        asd.put("apr", 4);
        asd.put("may", 5);
        asd.put("jun", 6);
        asd.put("jul", 7);
        asd.put("aug", 8);
        asd.put("sep", 9);
        asd.put("oct", 10);
        asd.put("nov", 11);
        asd.put("dec", 12);
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
                statement.setObject(2, contractBrandDTO.getBrandHierarchyNo());
                statement.setObject(3, contractBrandDTO.getContractSid());
                statement.setObject(4, contractBrandDTO.getBrandMasterSid());
                statement.setObject(5, sessionDTO.getProjectionId());
                statement.setObject(6, Integer.valueOf(sessionDTO.getSessionId()));
                statement.setObject(7, Integer.valueOf(sessionDTO.getUserId()));
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
            LOGGER.info("Entering callAlternateHistoryProcedure  ::::");
            if (connection != null) {
                statement = connection.prepareCall("{call PRC_NM_ALTERNATE_ACTUALS (?,?,?,?,?,?,?)}");
                LOGGER.info("CONT_HierarchyNo=" + inputs[0]);
                LOGGER.info("BRAND_RELATIONSHIP_LEVEL_SID=" + inputs[1]);
                LOGGER.info("ALTER_CONTRACT_HOLDER_SID=" + inputs[2]);
                LOGGER.info("ALTER_BRAND_MASTER_SID=" + inputs[3]);
                LOGGER.info("PROJECTION_MASTER_SID=" + inputs[4]);
                LOGGER.info("SESSION_ID=" + inputs[5]);
                LOGGER.info("USER_ID=" + inputs[6]);
                statement.setObject(1, inputs[0]);
                statement.setObject(2, inputs[1]);
                statement.setObject(3, inputs[2]);
                statement.setObject(4, inputs[3]);
                statement.setObject(5, inputs[4]);
                statement.setObject(6, Integer.parseInt((String) inputs[5]));
                statement.setObject(7, Integer.parseInt((String) inputs[6]));
                statement.execute();
            }
            LOGGER.info("Ending callAlternateHistoryProcedure return  staus ::::");
        } catch (Exception ex) {
            LOGGER.error(new Date() + ex.getMessage());
            throw new SystemException(ex);
        } finally {
            statement.close();
            connection.close();
        }
    }

    public void saveMandatedSalesProjection(String userId, String sessionId) throws PortalException, Exception {
        saveActualsSales(userId, sessionId);
        saveSalesProjection(userId, sessionId);
        saveSalesProjectionMaster(userId, sessionId);
    }

    public void saveNonMandatedSalesProjection(String userId, String sessionId) throws PortalException, Exception {
        String saveQuery = CustomSQLUtil.get("nm.saveToMainTable");
        saveQuery = saveQuery.replace("?UID", userId);
        saveQuery = saveQuery.replace("?SID", sessionId);
        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        salesProjectionDAO.executeUpdateQuery(saveQuery.toString());
    }

    public void saveActualsSales(String userId, String sessionId) throws PortalException, Exception {

        StringBuilder query = new StringBuilder();
        query.append("MERGE M_ACTUAL_SALES AS TARGET\n"
                + "		USING ( \n"
                + "		SELECT PROJECTION_DETAILS_SID,\n"
                + "		PERIOD_SID,\n"
                + "		ACTUAL_SALES,\n"
                + "		ACTUAL_UNITS,\n"
                + "		ACTUAL_PROJECTION_SALES,\n"
                + "		ACTUAL_PROJECTION_UNITS\n"
                + "		FROM dbo.ST_M_ACTUAL_SALES\n"
                + "		WHERE USER_ID=" + userId + "AND SESSION_ID=" + sessionId + "\n"
                + "		) AS SOURCE \n"
                + "		ON (TARGET.PROJECTION_DETAILS_SID=SOURCE.PROJECTION_DETAILS_SID AND TARGET.PERIOD_SID=SOURCE.PERIOD_SID)\n"
                + "		WHEN MATCHED \n"
                + "		THEN \n"
                + "		UPDATE SET \n"
                + "		TARGET.ACTUAL_SALES=SOURCE.ACTUAL_SALES,\n"
                + "		TARGET.ACTUAL_UNITS=SOURCE.ACTUAL_UNITS,\n"
                + "		TARGET.ACTUAL_PROJECTION_SALES=SOURCE.ACTUAL_PROJECTION_SALES,\n"
                + "		TARGET.ACTUAL_PROJECTION_UNITS=SOURCE.ACTUAL_PROJECTION_UNITS\n"
                + "		WHEN NOT MATCHED BY TARGET\n"
                + "		THEN \n"
                + "		INSERT VALUES(SOURCE.PROJECTION_DETAILS_SID,\n"
                + "		SOURCE.PERIOD_SID,\n"
                + "		SOURCE.ACTUAL_SALES,\n"
                + "		SOURCE.ACTUAL_UNITS,\n"
                + "		SOURCE.ACTUAL_PROJECTION_SALES,\n"
                + "		SOURCE.ACTUAL_PROJECTION_UNITS);");

        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        salesProjectionDAO.executeUpdateQuery(query.toString());
    }

    public void saveSalesProjectionMaster(String userId, String sessionId) throws PortalException, Exception {

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
                + "		THEN \n"
                + "		UPDATE SET \n"
                + "		TARGET.METHODOLOGY=SOURCE.METHODOLOGY, \n"
                + "		TARGET.CALCULATION_PERIODS=SOURCE.CALCULATION_PERIODS, \n"
                + "		TARGET.CALCULATION_BASED=SOURCE.CALCULATION_BASED, \n"
                + "		TARGET.CHECK_RECORD=SOURCE.CHECK_RECORD, \n"
                + "		TARGET.FORECAST_START_PERIOD_SID=SOURCE.FORECAST_START_PERIOD_SID, \n"
                + "		TARGET.FORECAST_END_PERIOD_SID=SOURCE.FORECAST_END_PERIOD_SID, \n"
                + "		TARGET.ALLOCATION_BASIS=SOURCE.ALLOCATION_BASIS\n"
                + "		WHEN NOT MATCHED BY TARGET\n"
                + "		THEN \n"
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

    public void saveSalesProjection(String userId, String sessionId) throws PortalException, Exception {

        StringBuilder query = new StringBuilder();
        query.append("MERGE M_SALES_PROJECTION AS TARGET\n"
                + "		USING ( \n"
                + "		SELECT PROJECTION_DETAILS_SID,\n"
                + "		ACCOUNT_GROWTH,\n"
                + "		PRODUCT_GROWTH,\n"
                + "		PROJECTION_SALES,\n"
                + "		PROJECTION_UNITS,\n"
                + "		PERIOD_SID,\n"
                + "		ADJUSTMENT_TYPE,\n"
                + "		ADJUSTMENT_BASIS,\n"
                + "		ADJUSTMENT_VARIABLE,\n"
                + "		ADJUSTMENT_METHODOLOGY,\n"
                + "		ADJUSTMENT_VALUES\n"
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
                + "		TARGET.PERIOD_SID=SOURCE.PERIOD_SID,\n"
                + "		TARGET.ADJUSTMENT_TYPE=SOURCE.ADJUSTMENT_TYPE,\n"
                + "		TARGET.ADJUSTMENT_BASIS=SOURCE.ADJUSTMENT_BASIS,\n"
                + "		TARGET.ADJUSTMENT_VARIABLE=SOURCE.ADJUSTMENT_VARIABLE,\n"
                + "		TARGET.ADJUSTMENT_METHODOLOGY=SOURCE.ADJUSTMENT_METHODOLOGY,\n"
                + "		TARGET.ADJUSTMENT_VALUES=SOURCE.ADJUSTMENT_VALUES\n"
                + "		WHEN NOT MATCHED BY TARGET\n"
                + "		THEN \n"
                + "		INSERT VALUES(SOURCE.PROJECTION_DETAILS_SID,\n"
                + "		SOURCE.ACCOUNT_GROWTH,\n"
                + "		SOURCE.PRODUCT_GROWTH,\n"
                + "		SOURCE.PROJECTION_SALES,\n"
                + "		SOURCE.PROJECTION_UNITS,\n"
                + "		SOURCE.PERIOD_SID,\n"
                + "		SOURCE.ADJUSTMENT_TYPE,\n"
                + "		SOURCE.ADJUSTMENT_BASIS,\n"
                + "		SOURCE.ADJUSTMENT_VARIABLE,\n"
                + "		SOURCE.ADJUSTMENT_METHODOLOGY,\n"
                + "		SOURCE.ADJUSTMENT_VALUES);");

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
            LOGGER.error("Query============================>" + query);
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
        List<Leveldto> listValue = new ArrayList<Leveldto>();
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
                        dto.setHierarchyNo(String.valueOf(obj[2]));
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
        int frequencyNo = 3;
        if (frequency.equals(QUARTERLY.getConstant())) {
            frequencyNo = 3;
        } else if (frequency.equals(SEMI_ANNUAL.getConstant())) {
            frequencyNo = 6;
        } else if (frequency.equals(MONTHLY.getConstant())) {
            frequencyNo = 1;
        } else if (frequency.equals(ANNUAL.getConstant())) {
            frequencyNo = 12;
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
                Object[] orderedArgs = {projectionSelectionDTO.getProjectionId(), projectionSelectionDTO.getUserId(), "SPAP", projectionSelectionDTO.getSessionId()};
                CommonLogic.callProcedure("PRC_M_DISCOUNT_INSERT", orderedArgs);
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
                .replace("@LEVEL_NO", String.valueOf(projectionSelectionDTO.getLevelNo()));
                if (StringUtils.isNotBlank(projectionSelectionDTO.getHierarchyNo()) && StringUtils.isBlank(projectionSelectionDTO.getLevelFilterValue())) {
            query += " AND RLD.HIERARCHY_NO LIKE '" + projectionSelectionDTO.getHierarchyNo() + "%'";
        }
        try {
            List list = (List) salesAllocationDAO.executeSelectQuery(query);
            String[] hierarchyArr = new String[list.size()];
            if (list != null && !list.isEmpty()) {
                for (int i = 0; i < list.size(); i++) {
                    Object[] ob = (Object[]) list.get(i);
                    hierarchyArr[i] = ob[0].toString();
                    projectionSelectionDTO.setLevelName(ob[2].toString());
                }
                count = list.size();
                projectionSelectionDTO.setReHierarchyNo(Arrays.toString(hierarchyArr).replace("[", StringUtils.EMPTY).replace("]", StringUtils.EMPTY));
            }
        } catch (PortalException ex) {
            LOGGER.info("Query Error--> " + query);
            LOGGER.error(ex);
        } catch (Exception ex) {
            LOGGER.info("Query Error--> " + query);
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
        LOGGER.info("Load Data ---->>  start" + start + " --- offset" + offset + " HierarchyNo-> " + projSelDTO.getReHierarchyNo() + " projSelDTO.getHierarchyNo()" + projSelDTO.getHierarchyNo());
        LOGGER.info("@USER_ID    " + projSelDTO.getUserId());
        LOGGER.info("@SESSION_ID " + projSelDTO.getSessionId());
        LOGGER.info("@LEVEL_NO   " + projSelDTO.getLevelNo());
        LOGGER.info("@RowsPerLevelItem   " + projSelDTO.getRowsPerLevelItem());
        List list = new ArrayList();
        String queryResult = StringUtils.EMPTY;
        String frequency = StringUtils.EMPTY;
        String[] str = projSelDTO.getReHierarchyNo().split(",");
        String returnDetailsSID = StringUtils.EMPTY;
         Map<String, String> returnMap = projSelDTO.getSessionDTO().getReturnsDetailsMap();
            for (Map.Entry<String, String> entr : returnMap.entrySet()) {
                if (entr.getKey().contains(projSelDTO.getHierarchyNo())) {
                    returnDetailsSID += entr.getValue() + ",";
                }
            }
        LOGGER.info("ReturnDetailsSID " + returnDetailsSID);
        StringBuilder query = new StringBuilder();
        query.append(SQlUtil.getQuery("RETURNS_SALES_QUERY_RESULTS"));
        queryResult = query.toString().replace("@PROJECTION_SID", String.valueOf(projSelDTO.getProjectionId()));
        queryResult = queryResult.replace("@USER_ID", String.valueOf(projSelDTO.getUserId()));
        queryResult = queryResult.replace("@SESSION_ID", String.valueOf(projSelDTO.getSessionId()));
        queryResult = queryResult.replace("@RD_SID", StringUtils.isEmpty(returnDetailsSID) ? "RETURNS_DETAILS_SID LIKE '%'" : "RETURNS_DETAILS_SID IN (" + returnDetailsSID.substring(0, returnDetailsSID.lastIndexOf(",")) + ")");
        queryResult = queryResult.replace("@LEVEL_NO", String.valueOf(projSelDTO.getLevelNo()));

        if (projSelDTO.getFrequencyDivision() == 1) {
            queryResult = queryResult.replace("@FREQUENCY@", "1 AS 'YEARSS'");
            queryResult = queryResult.replace("@FREQUENCY_DIVISION", "12");
        } else if (projSelDTO.getFrequencyDivision() == 2) {
            frequency = " ,P.SEMI_ANNUAL ";
            queryResult = queryResult.replace("@FREQUENCY@", "P.SEMI_ANNUAL");
            queryResult = queryResult.replace("@FREQUENCY_DIVISION", "6");
        } else if (projSelDTO.getFrequencyDivision() == 4) {
            frequency = " ,P.QUARTER";
            queryResult = queryResult.replace("@FREQUENCY@", "P.QUARTER");
            queryResult = queryResult.replace("@FREQUENCY_DIVISION", "3");
        } else if (projSelDTO.getFrequencyDivision() == 12) {
            frequency = ",P.MONTH";
            queryResult = queryResult.replace("@FREQUENCY@", "P.MONTH");
            queryResult = queryResult.replace("@FREQUENCY_DIVISION", Constant.STRING_ONE);
        }
        queryResult += frequency + " ORDER BY  RT_M.HIERARCHY_NO ";
            queryResult += "OFFSET " + (start*projSelDTO.getRowsPerLevelItem()) + " ROWS FETCH NEXT " + (offset*projSelDTO.getRowsPerLevelItem()) + " ROWS ONLY ";
        try {
            list = (List) HelperTableLocalServiceUtil.executeSelectQuery(queryResult);
        } catch (Exception e) {
            LOGGER.info("Query Error-->  " + queryResult);
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
            if (StringUtils.isNotBlank(salesRowDto.getHierarchyNo()) && obj[2] != null && !obj[2].equals(salesRowDto.getHierarchyNo())) {
                salesRowList.add(salesRowDto);
                salesRowDto = new SalesRowDto();
            }
            salesRowDto.setHierarchyNo(obj[2].toString());
            salesRowDto.setLevelName(projSelDTO.getSessionDTO().getLevelValueDiscription(obj[2].toString(), Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY));

            salesRowDto.setLevelNo(projSelDTO.getLevelNo());
            salesRowDto.setTreeLevelNo(projSelDTO.getLevelNo());
            salesRowDto.setReturnDetailsSid(projSelDTO.getSessionDTO().getReturnsDetailsMap().get(obj[2].toString()));
            salesRowDto.setReHierarchyNo(projSelDTO.getReHierarchyNo());
            salesRowDto.setCcpCount(String.valueOf(salesRowDto.getReturnDetailsSid().split(",").length));

            if (projSelDTO.isIsFilter() || 1 == salesRowDto.getReturnDetailsSid().split(",").length && (Constant.PRODUCT.equals(projSelDTO.getLevelName()) || "Item".equals(projSelDTO.getLevelName()) || Constant.NDC.equals(projSelDTO.getLevelName()))) {
                salesRowDto.setLog(obj[4] == null ? StringUtils.EMPTY : obj[4].toString());
                salesRowDto.setClosedDate(obj[5] == null ? StringUtils.EMPTY : obj[5].toString());
                salesRowDto.setLoeDate(obj[6] == null ? StringUtils.EMPTY : obj[6].toString());
                salesRowDto.setParent(0);
            } else {
                salesRowDto.setParent(1);
            }
            salesRowDto.setUncheckCount(Integer.valueOf(obj[15].toString()));
            salesRowDto.setProductHierarchyNo(obj[2].toString());
            salesRowDto.setHierarchyIndicator(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY);

            int frequencyDivision = projSelDTO.getFrequencyDivision();

            String key = Constant.Q_SMALL + String.valueOf(obj[0]) + "-" + String.valueOf(obj[1]);
            if (frequencyDivision == 1) {
                key = String.valueOf(obj[1]);
            } else if (frequencyDivision == 4) {
                key = Constant.Q_SMALL + String.valueOf(obj[0]) + "-" + String.valueOf(obj[1]);
            } else if (frequencyDivision == 2) {
                key = Constant.S_SMALL + String.valueOf(obj[0]) + "-" + String.valueOf(obj[1]);
            } else if (frequencyDivision == 12) {
                String monthName = getMonthForInt(Integer.valueOf(String.valueOf(obj[0])) - 1);
                key = monthName.toLowerCase() + "-" + String.valueOf(obj[1]);
            }
            salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-ActualReturned%", String.valueOf(UNIT.format(obj[7] == null ? 0 : obj[7])) + Constant.PERCENT);
            salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-ActualRPU", String.valueOf(MONEY.format(obj[8] == null ? 0 : obj[8])));
            salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-ActualReturnedAmount", String.valueOf(MONEYNODECIMAL.format(obj[9] == null ? 0 : obj[9])));
            if (!projSelDTO.getCommonColumn().isEmpty()) {
                for (String col : projSelDTO.getCommonColumn()) {
                    if (col.contains(key)) {
                        salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-HistoryProjectedReturn%", String.valueOf(UNIT.format(obj[10] == null ? 0 : obj[10])) + Constant.PERCENT);
                        salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-HistoryProjectedRPU", String.valueOf(MONEY.format(obj[11] == null ? 0 : obj[11])));
                        salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-HistoryProjectedReturnAmount", String.valueOf(MONEYNODECIMAL.format(obj[12] == null ? 0 : obj[12])));
                        salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-HistoryGrowthRate", String.valueOf(UNIT.format(obj[13] == null ? 0 : obj[13])) + Constant.PERCENT);
                    } else {
                        salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-ProjectedReturn%", String.valueOf(UNIT.format(obj[10] == null ? 0 : obj[10])) + Constant.PERCENT);
                        salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-ProjectedRPU", String.valueOf(MONEY.format(obj[11] == null ? 0 : obj[11])));
                        salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-ProjectedReturnAmount", String.valueOf(MONEYNODECIMAL.format(obj[12] == null ? 0 : obj[12])));
                        salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-GrowthRate", String.valueOf(UNIT.format(obj[13] == null ? 0 : obj[13])) + Constant.PERCENT);
                    }
                }
            } else {
                salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-ProjectedReturn%", String.valueOf(UNIT.format(obj[10] == null ? 0 : obj[10])) + Constant.PERCENT);
                salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-ProjectedRPU", String.valueOf(MONEY.format(obj[11] == null ? 0 : obj[11])));
                salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-ProjectedReturnAmount", String.valueOf(MONEYNODECIMAL.format(obj[12] == null ? 0 : obj[12])));
                salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-GrowthRate", String.valueOf(UNIT.format(obj[13] == null ? 0 : obj[13])) + Constant.PERCENT);
            }
            salesRowDto.addBooleanProperties(Constant.CHECK, Integer.parseInt(String.valueOf(obj[14])) == 0 ? new Boolean(false) : new Boolean(true));
            if (i == (resulList.size() - 1)) {
                salesRowList.add(salesRowDto);
            }
        }
        return salesRowList;
    }

    public void saveReturnsSalesProjection(String userId, String sessionId) throws PortalException, Exception {
        String saveQuery = StringUtils.EMPTY;
        try {
            saveQuery = SQlUtil.getQuery("RETURN_MAIN_TABLE_INSERT").replace("@USER_ID", String.valueOf(userId));
            saveQuery = saveQuery.replace("@SESSION_ID", String.valueOf(sessionId));
            salesAllocationDAO.executeUpdateQuery(saveQuery.toString());

        } catch (SQLException e) {
            LOGGER.info("QUERY ERROR ---->>> " + saveQuery);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    public int queryToUpdateCheckRecord(SessionDTO sessionDTO, int checkValue, boolean isUpdate, String returnDetailsSid) {
        StringBuilder queryBuilder = new StringBuilder();
        int count = 0;
        try {
            if (isUpdate) {
                queryBuilder.append(" Update ST_RETURNS_PROJ_MASTER SET CHECK_RECORD =" + checkValue + " WHERE USER_ID= " + sessionDTO.getUserId() + " and SESSION_ID= " + sessionDTO.getSessionId() + StringUtils.EMPTY);
                if (StringUtils.isNotBlank(returnDetailsSid)) {
                    queryBuilder.append(" AND RETURNS_DETAILS_SID IN (" + returnDetailsSid + ")");
                }
                List list = salesAllocationDAO.executeUpdateQuery(queryBuilder.toString());
                if (list.size() > 0) {
                    count = Integer.parseInt(String.valueOf(list.get(0)));
                }
            }
        } catch (PortalException ex) {
            LOGGER.info("queryToUpdateCheckRecord---> " + queryBuilder.toString());
            LOGGER.error(ex);
        } catch (Exception ex) {
            LOGGER.info("queryToUpdateCheckRecord---> " + queryBuilder.toString());
            LOGGER.error(ex);
        }
        return count;
    }

    public boolean calculateReturnsProjection(final ProjectionSelectionDTO projectionSelectionDTO, final String methodology, final String calcPeriods,
            final String startPeriodSID, final String endPeriodSID) {
        boolean isSalesCalculated = false;
        try {
            LOGGER.info("calculateReturnsProjection starts ");
            saveReturnsSelectionForCalculation(projectionSelectionDTO, methodology, calcPeriods, startPeriodSID, endPeriodSID);
            DataSelectionLogic dsLogic = new DataSelectionLogic();
            dsLogic.callSalesInsertProcedure(projectionSelectionDTO.getProjectionId(), String.valueOf(projectionSelectionDTO.getUserId()), String.valueOf(projectionSelectionDTO.getSessionId()), SalesUtils.RETURNS_SALES_CALCULATE_PRO_NAME);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.info("calculateReturnsProjection ends ");
        return isSalesCalculated;

    }

    public void saveReturnsSelectionForCalculation(final ProjectionSelectionDTO projectionSelectionDTO, final String methodology, final String calcPeriods,
            final String startPeriodSID, final String endPeriodSID) throws PortalException, Exception {

        String updateQuery = "UPDATE ST_RETURNS_PROJ_MASTER   SET METHODOLOGY= '" + methodology + "',"
                + " CALCULATION_PERIODS= '" + calcPeriods + "',"
                + " METHODOLOGY_START_DATE='" + startPeriodSID + "',"
                + " METHODOLOGY_END_DATE='" + (StringUtils.isEmpty(endPeriodSID) ? Constant.NULL_CAPS : endPeriodSID) + "'"
                + " WHERE USER_ID = " + String.valueOf(projectionSelectionDTO.getUserId())
                + " AND SESSION_ID = " + String.valueOf(projectionSelectionDTO.getSessionId())
                + " AND CHECK_RECORD = 1";
        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        salesProjectionDAO.executeUpdateQuery(updateQuery);

    }

    public void callRefreshProcedure(int projectionId, String selectedItems, String refreshedPeriods, String userId, String sessionId, boolean flag) {
        boolean status = false;

        LOGGER.info("In callRefreshProcedure starts");
        LOGGER.info("PRC---> PRC_RETURNS_REFRESH");
        LOGGER.info("selectedItems---> " + selectedItems);
        LOGGER.info("refreshedPeriods---> " + refreshedPeriods);
        LOGGER.info("ProjectionId----> " + projectionId);
        LOGGER.info("UserId----------> " + userId);
        LOGGER.info("SessionId-------> " + sessionId);

        final DataSourceConnection dataSourceConnection = DataSourceConnection.getInstance();
        Connection connection = null;
        CallableStatement statement = null;
        try {
            connection = dataSourceConnection.getConnection();

            if (connection != null) {

                statement = connection.prepareCall("{call PRC_RETURNS_REFRESH (?,?,?,?,?,?)}");

                statement.setObject(1, projectionId); //  @PROJECTION_SID
                statement.setObject(2, selectedItems);
                statement.setObject(3, refreshedPeriods);
                statement.setObject(4, flag);
                statement.setObject(5, userId); //  @USER_ID
                statement.setObject(6, sessionId); //  @SESSION_ID

                status = statement.execute();
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
        LOGGER.info("In callRefreshProcedure ends");
    }

    private List<Map> getActiveExFactorySalesAndUnits(ProjectionSelectionDTO projectionSelectionDTO, SalesRowDto dto, final int year, final int period) throws PortalException, Exception {
        List<Map> mapList = new ArrayList<Map>();
        Map<String, Double> unitsMap = new TreeMap<String, Double>();
        Map<String, Double> salesMap = new TreeMap<String, Double>();
        String query = SQlUtil.getQuery("SALES_AMOUNT_QUERY").replace("@RETURN_SID", String.valueOf(projectionSelectionDTO.getSessionDTO().getDetailsSID()))
                .replace("@USER_ID", String.valueOf(projectionSelectionDTO.getUserId()))
                .replace("@SESSION_ID", String.valueOf(projectionSelectionDTO.getSessionId()))
                .replace("@YEAR", String.valueOf(year));
        query = addFrequencyInQuery(projectionSelectionDTO.getFrequencyDivision(), period, query);
        List resultsList = (List) salesAllocationDAO.executeSelectQuery(query);
        for (int i = 0; i < resultsList.size(); i++) {
            Object[] ob = (Object[]) resultsList.get(i);
            salesMap.put(ob[0] == null ? StringUtils.EMPTY : ob[0].toString(), ob[1] == null ? 0.0 : Double.valueOf(ob[1].toString()));
            unitsMap.put(ob[0] == null ? StringUtils.EMPTY : ob[0].toString(), ob[2] == null ? 0.0 : Double.valueOf(ob[2].toString()));
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
        LOGGER.info("Entered Value--> " + editedValue);
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
        String parentDetailsSid = projectionSelectionDTO.getSessionDTO().getReturnsDetailsMap().get(entry.getKey().substring(0, entry.getKey().length() - 2));

        String hierarchy = entry.getKey();
        LOGGER.info("ParentDetailsSid-->>  " + parentDetailsSid);
        LOGGER.info("currentDetailsSid-->> " + entry.getValue());
        LOGGER.info("hierarchy-->>         " + hierarchy);
        //Formula:(A)/SUM(B)*AMOUNT
        //Code to calculate A
        double amount = calculatedAmount.get(hierarchy.substring(0, (hierarchy.length() - 2)));
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
        LOGGER.info("amountA-->> " + amountA);
        LOGGER.info("amountB-->> " + amountB);
        LOGGER.info("amount " + amount);

        amount = (amountA / amountB) * amount;
        return amount;
    }

    private String updateQuery(String saveQuery, double rpu, final String entryValue) {
        LOGGER.info("RPU " + rpu + " ReturnDetailsSid--> " + entryValue);
        saveQuery = saveQuery.replace("@USER_ENTERED_VALUE", StringUtils.EMPTY + (Double.isNaN(rpu) ? 0.0 : rpu));
        saveQuery = saveQuery.replace("@RETURNS_DETAILS_SID", entryValue);
        return saveQuery;
    }

    private List<Map> getActiveExFactorySalesAndUnitsForMassUpdate(ProjectionSelectionDTO projectionSelectionDTO, String periodQuery, String frequency) throws PortalException, Exception {
        List<Map> mapList = new ArrayList<Map>();
        Map<String, Map<String, Double>> salesMap = new TreeMap<String, Map<String, Double>>();
        Map<String, Map<String, Double>> unitsMap = new TreeMap<String, Map<String, Double>>();
        Map<String, Double> units = new TreeMap<String, Double>();
        Map<String, Double> sales = new TreeMap<String, Double>();
        String query = SQlUtil.getQuery("SALES_AMOUNT_QUERY_MASS_UPDATE").replace("@RETURN_SID", String.valueOf(projectionSelectionDTO.getSessionDTO().getDetailsSID()))
                .replace("@USER_ID", String.valueOf(projectionSelectionDTO.getUserId()))
                .replace("@SESSION_ID", String.valueOf(projectionSelectionDTO.getSessionId()))
                .replace("@PERIOD_QUERY", periodQuery)
                .replace("@FREQUENCY_SELECTION", projectionSelectionDTO.getFrequencyDivision() == 1 ? "0 as period, " : frequency)
                .replace("@FREQUENCY", frequency);
        if (StringUtils.isNotBlank(frequency)) {
            query += " order by " + (frequency.substring(0, frequency.length() - 1));
        } else {
            query += " ORDER BY P.YEAR";
        }
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
                    sales = new TreeMap<String, Double>();
                    units = new TreeMap<String, Double>();
                    year = Integer.valueOf(ob[1].toString());
                    period = Integer.valueOf(ob[0].toString());
                }
                sales.put(ob[2] == null ? StringUtils.EMPTY : ob[2].toString(), ob[3] == null ? 0.0 : Double.valueOf(ob[3].toString()));
                units.put(ob[2] == null ? StringUtils.EMPTY : ob[2].toString(), ob[4] == null ? 0.0 : Double.valueOf(ob[4].toString()));
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
                query = query.replace("@FREQUENCY", StringUtils.EMPTY);
                break;
            case 12:
                query = query.replace("@FREQUENCY", " AND MONTH = " + period);
                break;
            case 4:
                query = query.replace("@FREQUENCY", " AND QUARTER = " + period);
                break;
            case 2:
                query = query.replace("@FREQUENCY", " AND SEMI_ANNUAL = " + period);
                break;
        }
        return query;
    }

    private String calculationLogic(final ProjectionSelectionDTO projectionSelectionDTO, final String hierarchyNo, final String enteredValue, String query, final Map<String, Double> salesAmount, final Map<String, Double> unitsMap) {
        //This Map contains the updated Projection Return Amount for each hierarchy
        Map<String, Double> calculatedAmount = new HashMap<String, Double>();
        boolean enteredLevel = true;
        double amount = 0.0;
        String bulkQuery = StringUtils.EMPTY;
        Map<String, String> returnsDetailsMap = new TreeMap<String, String>(projectionSelectionDTO.getSessionDTO().getReturnsDetailsMap());
        for (Map.Entry<String, String> entry : returnsDetailsMap.entrySet()) {
            if (entry.getKey().contains(hierarchyNo)) {
                if (enteredLevel) {
                    amount = calculateSalesAmouintForRPUValue(unitsMap, entry.getValue(), enteredValue);
                    calculatedAmount.put(entry.getKey(), amount);
                    LOGGER.info("INITAIL Amount " + amount);
                    enteredLevel = false;
                } else {
                    amount = getRPUValue(salesAmount, entry, calculatedAmount, projectionSelectionDTO);
                    calculatedAmount.put(entry.getKey(), amount);
                }
                LOGGER.info("RESULTS:  Hierarchy--> " + entry.getKey() + " ReturnDetailsSid--> " + entry.getValue() + " Amount= " + amount);
                if (entry.getValue().split(",").length == 1) {
                    LOGGER.info("Amount Value -> " + amount);
                    LOGGER.info("Units Value -> " + unitsMap.get(entry.getValue()));
                    bulkQuery += updateQuery(query, (amount / unitsMap.get(entry.getValue())), entry.getValue());
                }
            }
        }
        return bulkQuery;
    }
    public List<Integer> headerCheckALLQuery(SessionDTO sessionDTO, int checkValue, boolean isUpdate) {
        StringBuilder queryBuilder = new StringBuilder();
        try {
            if (isUpdate) {
                queryBuilder.append(" Update ST_RETURNS_PROJ_MASTER SET CHECK_RECORD =" + checkValue + " WHERE USER_ID= " + sessionDTO.getUserId() + " and SESSION_ID= " + sessionDTO.getSessionId() + StringUtils.EMPTY);
                salesAllocationDAO.executeUpdateQuery(queryBuilder.toString());
            } else {
                queryBuilder.append(" SELECT DISTINCT CHECK_RECORD from ST_RETURNS_PROJ_MASTER where USER_ID= " + sessionDTO.getUserId() + " and SESSION_ID= " + sessionDTO.getSessionId() + " AND (CHECK_RECORD IS NOT NULL OR CHECK_RECORD <> '')");
                
                return (List<Integer>) salesAllocationDAO.executeSelectQuery(queryBuilder.toString());
            }
        } catch (PortalException ex) {
            LOGGER.error(ex.getMessage());
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return null;
    }
    
    public List<SalesRowDto> configureLevels(int start, int offset, int started, ProjectionSelectionDTO projSelDTO) {
        int neededRecord = offset;

        List<SalesRowDto> resultList = new ArrayList<SalesRowDto>();
        if (projSelDTO.isIsProjectionTotal() && "Alternate_History".equals(projSelDTO.getFunctionality())) {
            SalesRowDto dto = new SalesRowDto();
            String commonColumn = StringUtils.EMPTY;
            String column = StringUtils.EMPTY;
            String value = Constant.NULL;
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
            List<Leveldto> levelList = getConditionalLevelList(projSelDTO.getProjectionId(), start, offset, projSelDTO.getHierarchyIndicator(), projSelDTO.getTreeLevelNo(), projSelDTO.getHierarchyNo(), projSelDTO.getProductHierarchyNo(), projSelDTO.getCustomerHierarchyNo(), projSelDTO.isIsFilter(), false, projSelDTO.isIsCustomHierarchy(), projSelDTO.getCustomId(), projSelDTO.getGroupFilter(), projSelDTO.getUserId(), projSelDTO.getSessionId(), projSelDTO.getCustRelationshipBuilderSid(), projSelDTO.getProdRelationshipBuilderSid(), false, true);

            for (int i = 0; i < levelList.size() && neededRecord > 0; i++) {
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
                neededRecord--;
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
        String freq = StringUtils.EMPTY;
        if (projSelDTO.getFrequencyDivision() == 1 || Constant.ANNUALLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
            freq = "ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 2 || Constant.SEMI_ANNUALLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
            freq = "SEMI-ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 4 || Constant.QUARTERLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
            freq = "QUARTERLY";
        } else if (projSelDTO.getFrequencyDivision() == 12 || Constant.MONTHLY.equalsIgnoreCase(projSelDTO.getFrequency())) {
            freq = "MONTHLY";
        }

        List<SalesRowDto> projDTOList = new ArrayList<SalesRowDto>();
        if (!projSelDTO.getLevelValue().startsWith(Constant.All)
                && !projSelDTO.getLevelValue().contains(Constant.Sales)) {
            if (neededRecord > 0) {
                int mayBeAddedRecord = started - mayBeAdded;
                if (mayBeAddedRecord < 0) {
                    mayBeAddedRecord = 0;
                }
                if (mayBeAddedRecord < projSelDTO.getPeriodList().size()) {
                    List<SalesRowDto> projectionDtoList = new ArrayList<SalesRowDto>();
                    projSelDTO.setProjTabName("SPR");
                    projectionDtoList = getProjectionPivot(projSelDTO);
                    projSelDTO.setProjTabName(StringUtils.EMPTY);
                    for (int k = mayBeAddedRecord; k < projectionDtoList.size() && neededRecord > 0; k++) {
                        if (!projSelDTO.hasNonFetchableIndex(StringUtils.EMPTY + k)) {
                            projDTOList.add(projectionDtoList.get(k));
                        }
                        started++;
                        neededRecord--;
                    }
                }
                mayBeAdded += projSelDTO.getPeriodList().size();
            }
        }

        if (neededRecord > 0 && !projSelDTO.isIsFilter()) {
            if ((projSelDTO.getTreeLevelNo() + 1) == projSelDTO.getTpLevel()
                    && ((projSelDTO.isIsCustomHierarchy()) || (!projSelDTO.getHierarchyIndicator().equals(Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY)))
                    && !projSelDTO.getLevelValue().startsWith(Constant.All)
                    && !projSelDTO.getLevelValue().contains(Constant.Sales)) {
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
                started++;
                neededRecord--;
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
        List<SalesRowDto> projDTOList = new ArrayList<SalesRowDto>();
        
        if (projSelDTO.getScreenName().equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
            LOGGER.info("Entering getProjection Pivot NonMandated");
            projDTOList = new ArrayList<SalesRowDto>();

            List<Object> gtsList = (List<Object>) CommonLogic.executeSelectQuery(CommonLogic.getCCPQuery(projSelDTO,Boolean.FALSE) + " \n" + getSalesProjectionUnitsQuery(projSelDTO), null, null);
            projDTOList = getCustomizedProjectionPivot(gtsList, projSelDTO);
            LOGGER.info("Ending getProjection Pivot NonMandated");
        } else if (projSelDTO.getScreenName().equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED)) {
            LOGGER.info("Entering getProjectionPivot Mandated");
            projDTOList = new ArrayList<SalesRowDto>();
            List<Object> gtsList = (List<Object>) SPRCommonLogic.executeSelectQuery(getSalesProjectionUnitsQuery(projSelDTO), null, null);
            projDTOList = getCustomizedProjectionPivot(gtsList, projSelDTO);
            LOGGER.info("Ends getProjectionPivot Mandated");
        } 
        return projDTOList;
    }
    
    public List<SalesRowDto> getCustomizedProjectionPivot(List<Object> list, ProjectionSelectionDTO projSelDTO) {

        int frequencyDivision = projSelDTO.getFrequencyDivision();
        List<SalesRowDto> projDTOList = new ArrayList<SalesRowDto>();
        List<String> periodList = new ArrayList<String>(projSelDTO.getPeriodList());
        int col = 2;
        for (Object rows : list) {
            final Object[] row = (Object[]) rows;
            String column = StringUtils.EMPTY;
            int year = Integer.valueOf(String.valueOf(row[0]));
            int period = Integer.valueOf(String.valueOf(row[1]));
            List<String> common = getCommonColumnHeader(frequencyDivision, year, period);
            String pcommonColumn = common.get(0);
            String commonHeader = common.get(1);
            String commonColumn = StringUtils.EMPTY;
            if (periodList.contains(pcommonColumn)) {
                periodList.remove(pcommonColumn);
                SalesRowDto projDTO = new SalesRowDto();
                List<String> columnList = new ArrayList<String>(projSelDTO.getColumns());
                columnList.remove("levelName");
                projDTO.setLevelValue(commonHeader);
                projDTO.setLevelName(commonHeader);
                String value = Constant.NULL;
                commonColumn = "uv";
                column = commonColumn + ACTUALS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 2];
                    value = getFormattedValue(PROJECTEDUNITDECIMAL, value);
                    projDTO.addStringProperties(column, value);
                    columnList.remove(column);
                }
                column = commonColumn + PROJECTIONS.getConstant();
                if (projSelDTO.hasColumn(column)) {
                    value = StringUtils.EMPTY + row[col + 3];
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
        List<String> columnList = new ArrayList<String>(projSelDTO.getColumns());
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

        String selectClause = " select ";
        String whereClause = StringUtils.EMPTY;
        String groupBy = ",H.LEVEL_NAME , I.\"YEAR\"\n";
        String customQuery = StringUtils.EMPTY;
        String masterTable = StringUtils.EMPTY;
        String historyQuery = StringUtils.EMPTY;
        String ccpWhereCond = CommonLogic.getCCPWhereConditionQuery("H", "E", "CCP");
        groupBy = ", H.RELATIONSHIP_LEVEL_VALUES " + groupBy;
        selectClause += "I.\"YEAR\" as YEARS, ";
        if (CommonUtils.isInteger(projSelDTO.getYear())) {

            whereClause += " and I.\"YEAR\" = " + projSelDTO.getYear();
        }
        if (projSelDTO.getFrequencyDivision() == 4) {
            selectClause += "I.QUARTER as PERIODS, \n";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.QUARTER";
        } else if (projSelDTO.getFrequencyDivision() == 2) {
            selectClause += "I.SEMI_ANNUAL as PERIODS, \n";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.SEMI_ANNUAL";
        } else if (projSelDTO.getFrequencyDivision() == 1) {
            selectClause += "'0' as PERIODS, \n";
            whereClause += StringUtils.EMPTY;
            groupBy += StringUtils.EMPTY;

        } else if (projSelDTO.getFrequencyDivision() == 12) {
            selectClause += "I.\"MONTH\" as PERIODS, \n";
            whereClause += StringUtils.EMPTY;
            groupBy += ", I.\"MONTH\"";
        }

        // To filter the data according to selected period
        String periodFilter = CommonLogic.getPeriodRestrictionQuery(projSelDTO);
        if (CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equals(projSelDTO.getScreenName())) {
            masterTable = "ST_M_SALES_PROJECTION_MASTER";
        } else if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(projSelDTO.getScreenName())) {
            masterTable = "ST_NM_SALES_PROJECTION_MASTER";
        }

        String customSql = "  PROJECTION_DETAILS E \n"
                + " JOIN " + masterTable + " B ON B.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID \n"
                + " JOIN @CCP CCP ON CCP.CCP_DETAILS_SID = E.CCP_DETAILS_SID \n"
                + " JOIN RELATIONSHIP_LEVEL_DEFINITION H ON H.RELATIONSHIP_LEVEL_SID = CCP.RELATIONSHIP_LEVEL_SID";

        String customSqlPeriod = "\n LEFT JOIN ST_ALTERNATE_HIST_ALLOCATION SAHL ON SAHL.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID\n"
                + " AND SAHL.SESSION_ID = A.SESSION_ID\n"
                + " AND SAHL.USER_ID = A.USER_ID\n"
                + " AND A.PERIOD_SID = I.PERIOD_SID\n"
                + " AND SAHL.PERIOD_SID = A.PERIOD_SID"
                + " where \n"
                + CommonLogic.getUserSessionQueryCondition(projSelDTO.getUserId(), projSelDTO.getSessionId(), "B").replaceFirst("and", StringUtils.EMPTY)
                + " and E.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + " and H.LEVEL_NO = " + projSelDTO.getLevelNo()
                + " group by H.LEVEL_NO " + groupBy;
        if (CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED.equals(projSelDTO.getScreenName())) {
            historyQuery = selectClause + " sum(A.ACTUAL_SALES) as SALES_ACTUAL_SALES, \n"
                    + " sum(A.ACTUAL_PROJECTION_SALES) as SALES_PROJECTION_SALES, \n"
                    + " Sum(ISNULL(A.ACTUAL_UNITS,0) + ISNULL(SAHL.ACTUAL_UNITS,0)) as ACTUAL_UNITS, \n"
                    + " sum(ISNULL(A.ACTUAL_PROJECTION_UNITS,0) + ISNULL(SAHL.PROJECTION_UNITS,0)) as PROJECTION_UNITS \n"
                    + " from "
                    + customSql
                    + " JOIN ST_M_ACTUAL_SALES A ON A.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID\n"
                    + " AND B.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID\n"
                    + " AND A.USER_ID = B.USER_ID\n"
                    + " AND A.SESSION_ID = B.SESSION_ID"
                    + " JOIN PERIOD I ON A.PERIOD_SID = I.PERIOD_SID " + periodFilter.replaceFirst("and", "and I.")
                    + customSqlPeriod;
        } else if (CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED.equals(projSelDTO.getScreenName())) {
            historyQuery = selectClause + " sum(A.ACTUAL_SALES) as SALES_ACTUAL_SALES, \n"
                    + " sum(A.HISTORY_PROJECTION_SALES) as SALES_PROJECTION_SALES, \n"
                    + " Sum(ISNULL(A.ACTUAL_UNITS,0) + ISNULL(SAHL.ACTUAL_UNITS,0)) as ACTUAL_UNITS, \n"
                    + " sum(ISNULL(A.HISTORY_PROJECTION_UNITS,0) + ISNULL(SAHL.PROJECTION_UNITS,0)) as PROJECTION_UNITS \n"
                    + " from "
                    + customSql
                    + " JOIN ST_NM_ACTUAL_SALES A ON A.PROJECTION_DETAILS_SID = E.PROJECTION_DETAILS_SID\n"
                    + " AND B.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID\n"
                    + " AND A.USER_ID = B.USER_ID\n"
                    + " AND A.SESSION_ID = B.SESSION_ID"
                    + " JOIN PERIOD I ON A.PERIOD_SID = I.PERIOD_SID " + periodFilter.replaceFirst("and", "and I.")
                    + customSqlPeriod;
        }
        String futureQuery = selectClause + " 0 as SALES_ACTUAL_SALES, \n"
                + " sum(A.PROJECTION_SALES) as SALES_PROJECTION_SALES, \n"
                + " 0 as ACTUAL_UNITS, \n"
                + " sum(A.PROJECTION_UNITS) as PROJECTION_UNITS \n"
                + " from "
                + customSql
                + " JOIN ST_NM_SALES_PROJECTION A ON B.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID\n"
                
                + CommonLogic.getUserSessionQueryCondition(projSelDTO.getUserId(), projSelDTO.getSessionId(), "A")
                + " JOIN PERIOD I ON A.PERIOD_SID = I.PERIOD_SID AND I." + periodFilter.replaceFirst("and", StringUtils.EMPTY) + "\n"
                + " where \n"
                + CommonLogic.getUserSessionQueryCondition(projSelDTO.getUserId(), projSelDTO.getSessionId(), "B").replaceFirst("and", StringUtils.EMPTY)
                + " and E.PROJECTION_MASTER_SID = " + projSelDTO.getProjectionId() + "\n"
                + " and H.LEVEL_NO = " + projSelDTO.getLevelNo()
                + " group by H.LEVEL_NO " + groupBy;
        List<String> list = CommonLogic.getCommonSelectWhereOrderGroupByClause("HISTORY", "FUTURE", "on");
        String finalWhereCond = list.get(1);
        String orderBy = list.get(2);
        String finalSelectClause = "select " + list.get(0) + "\n Isnull(HISTORY.SALES_ACTUAL_SALES, FUTURE.SALES_ACTUAL_SALES) as ACTUAL_SALES,\n Isnull(FUTURE.SALES_PROJECTION_SALES, HISTORY.SALES_PROJECTION_SALES) as PROJECTION_SALES,"
                + "\n Isnull(HISTORY.ACTUAL_UNITS, FUTURE.ACTUAL_UNITS) as ACTUAL_UNITS,\n Isnull(FUTURE.PROJECTION_UNITS, HISTORY.PROJECTION_UNITS) as PROJECTION_UNITS  ";

        customQuery = ("Alternate_History".equalsIgnoreCase(projSelDTO.getFunctionality())?StringUtils.EMPTY:finalSelectClause + " from (\n") + historyQuery +("Alternate_History".equalsIgnoreCase(projSelDTO.getFunctionality())?StringUtils.EMPTY:"\n) HISTORY FULL OUTER JOIN (\n" + futureQuery + "\n) FUTURE \n" + finalWhereCond + " Order By " + orderBy);
        return customQuery;
    }
    
    public List<Leveldto> getConditionalLevelList(int projectionId, int start, int offset, String hierarchyIndicator, int levelNo, String hierarchyNo, String productHierarchyNo, String customerHierarchyNo, boolean isFilter, boolean isExpand, boolean isCustom, int customId, String userGroup, int userId, int sessionId, String custRelSid, String prodRelSid, boolean isCount, boolean isLimit) {
        List<Leveldto> listValue = new ArrayList<>();
        try {
            String query = getLevelListQuery(projectionId, hierarchyIndicator, levelNo, hierarchyNo, productHierarchyNo, customerHierarchyNo, isFilter, isExpand, isCount, start, offset, isLimit, isCustom, customId, userGroup, userId, sessionId, custRelSid, prodRelSid);
            
            List<Object> list = (List<Object>) HelperTableLocalServiceUtil.executeSelectQuery(query);
            if (list != null && !list.isEmpty()) {
                for (Object list1 : list) {
                    final Object[] obj = (Object[]) list1;
                    Leveldto dto = getCustomizedView(obj, false);
                    listValue.add(dto);
                }
            }
        } catch (Exception ex) {
            LOGGER.info(ex.getCause());
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
            if (!hierarchyNo.equals(StringUtils.EMPTY)) {
                if (isExpand) {
                    whereCond = " and HLD" + hierarchyIndicator.trim() + ".HIERARCHY_NO='" + hierarchyNo + "' ";
                }
                if (!isFilter) {
                    hierarchyNo1 = hierarchyNo;
                }
            }
        }
        String recordNumber = StringUtils.EMPTY;
        String selectClause = "select ";
        if (isCount) {
            selectClause += " Count(distinct HLD" + hierarchyIndicator.trim() + ".RELATIONSHIP_LEVEL_VALUES) ";
        } else {
            selectClause += " distinct HLD" + hierarchyIndicator.trim() + ".LEVEL_NO, "
                    + " HLD" + hierarchyIndicator.trim() + ".TREE_LEVEL_NO, "
                    + " '" + hierarchyIndicator + "' as HIERARCHY_INDICATOR,"
                    + " HLD" + hierarchyIndicator.trim() + ".LEVEL_NAME,"
                    + " HLD" + hierarchyIndicator.trim() + ".RELATIONSHIP_LEVEL_VALUES,"
                    + " HLD" + hierarchyIndicator.trim() + ".PARENT_NODE,"
                    + " HLD" + hierarchyIndicator.trim() + ".HIERARCHY_NO ";
            if (isLimit) {
                recordNumber += " ORDER BY HLD" + hierarchyIndicator.trim() + ".RELATIONSHIP_LEVEL_VALUES ASC OFFSET " + start + " ROWS FETCH NEXT " + offset + " ROWS ONLY";
            } else {
                selectClause += ", ROW_NUMBER() OVER (ORDER BY HLD" + hierarchyIndicator.trim() + ".RELATIONSHIP_LEVEL_VALUES ASC) AS TEMP_INDEX ";
            }
        }
        String customSql = selectClause;
        if (isCustom) {

            String customViewQuery = getCustomViewLevelListQuery(projectionId, customId, hierarchyIndicator, levelNo, productHierarchyNo, customerHierarchyNo, custRelSid, prodRelSid, userId, sessionId);
            customSql += " from " + customViewQuery;
        } else {
            String relationshipBuilderSid = custRelSid;
            if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(hierarchyIndicator)) {
                relationshipBuilderSid = prodRelSid;
            }
            String selectClause1 = "(SELECT RLD.relationship_level_values,RLD.hierarchy_no,CCP.ccp_details_sid,RLD.hierarchy_level_definition_sid,RLD.level_no,RLD.level_no as TREE_LEVEL_NO," + "'" + hierarchyIndicator + "'" + " HIERARCHY_INDICATOR,RLD.PARENT_NODE ";
            String selectClause2 = " (SELECT RLD1.hierarchy_no,RLD1.relationship_level_sid,RLD1.relationship_level_values,RLD1.level_no,RLD1.level_name,RLD1.level_no as TREE_LEVEL_NO," + "'" + hierarchyIndicator + "'" + " HIERARCHY_INDICATOR,RLD1.hierarchy_level_definition_sid,RLD1.PARENT_NODE ";
            String joinQuery1 = " relationship_level_definition RLD JOIN ccp_map CCP ON RLD.relationship_level_sid = CCP.relationship_level_sid"
                    + " AND RLD.RELATIONSHIP_BUILDER_SID=" + relationshipBuilderSid + " \n"
                    + " JOIN projection_details PD "
                    + "  ON PD.ccp_details_sid = CCP.ccp_details_sid  AND PD.projection_master_sid =" + projectionId 
                    + " JOIN ST_ALTERNATE_HIST_ALLOCATION STHA ON \n"
                    + "	 STHA.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID\n"
                    + "	 AND STHA.USER_ID = " + userId 
                    + "	 AND STHA.SESSION_ID = " + sessionId                    
                    + getGroupFilterQuery(userGroup, userId, sessionId, false) + ") CCPMAP,";

            String joinQuery2 = " relationship_level_definition RLD1 JOIN " + getViewTableName(hierarchyIndicator) + " PCH  ON PCH.relationship_level_sid = RLD1.relationship_level_sid \n"
                    + " AND PCH.projection_master_sid =" + projectionId
                    + " WHERE  RLD1.hierarchy_no LIKE '" + hierarchyNo1 + "%' AND RLD1.LEVEL_NO = " + levelNo + ") HLD" + hierarchyIndicator.trim();
            String mainJoin = " WHERE  CCPMAP.hierarchy_no LIKE HLD" + hierarchyIndicator.trim() + ".hierarchy_no + '%'";

            customSql += " from " + selectClause1 + " from " + joinQuery1 + " " + selectClause2 + " from " + joinQuery2 + " " + mainJoin
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
                + " WHERE RLD2.HIERARCHY_NO like '" + productHierarchyNo + "') HLDP ON CCPMAPP.HIERARCHY_NO like HLDP.HIERARCHY_NO+'%'";
        return customViewQuery;
    }
 
    public  String getViewTableName(String hierarchyIndicator) {
        String viewtable = StringUtils.EMPTY;
        if (Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY.equals(hierarchyIndicator)) {
            viewtable = "PROJECTION_CUST_HIERARCHY";
        } else if (Constant.INDICATOR_LOGIC_PRODUCT_HIERARCHY.equals(hierarchyIndicator)) {
            viewtable = "PROJECTION_PROD_HIERARCHY";
        }
        return viewtable;
    }
    
    public  String getGroupFilterQuery(String userGroup, int userId, int sessionId, boolean isPrior) {
        String query = StringUtils.EMPTY;
        if (!userGroup.isEmpty()) {
            if (userGroup.startsWith(Constant.All)) {
                userGroup = " like '%' ";
                query = getGroupFilterSalesQuery(userGroup, userId, sessionId, isPrior);
            } else if (userGroup.startsWith(Constant.Sales)) {
                userGroup = " = '" + userGroup.replace(Constant.Sales, StringUtils.EMPTY) + "' ";
                query = getGroupFilterSalesQuery(userGroup, userId, sessionId, isPrior);
            }
        }
        return query;
    }
            
    public  String getGroupFilterSalesQuery(String userGroup, int userId, int sessionId, boolean isPrior) {
        String tableIndicator = StringUtils.EMPTY;
        if (!isPrior) {
            tableIndicator = "ST_";
        }
        String query = "JOIN " + tableIndicator + "NM_SALES_PROJECTION_MASTER S ON S.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID WHERE  S.USER_GROUP " + userGroup;
        if (!isPrior) {
            query += getUserSessionQueryCondition(userId, sessionId, Constant.S);
        }
        return query;
    }
    
    public  String getUserSessionQueryCondition(int userId, int sessionId, String table) {
        String user = " and " + table + ".USER_ID=" + userId + " and " + table + ".SESSION_ID=" + sessionId + " \n";
        return user;
    }
    
    public Leveldto getCustomizedView(Object[] obj, boolean isHierarchy) {
        Leveldto dto = new Leveldto();
        dto.setLevelNo(Integer.valueOf(String.valueOf(obj[0])));
        dto.setTreeLevelNo(Integer.valueOf(String.valueOf(obj[1])));
        dto.setHierarchyIndicator(String.valueOf(obj[2]));
        dto.setLevel(String.valueOf(obj[3]));
        if (isHierarchy) {
            dto.setHierarchyId(Integer.valueOf(String.valueOf(obj[4])));
        } else {
            dto.setRelationshipLevelValue(String.valueOf(obj[4]));
            dto.setParentNode(String.valueOf(obj[5]));
            dto.setHierarchyNo(String.valueOf(obj[6]));
        }
        return dto;
    }
    
    public int getLevelListCount(int projectionId, String hierarchyIndicator, int levelNo, String hierarchyNo, String productHierarchyNo, String customerHierarchyNo, boolean isFilter, boolean isCustom, int customId, String userGroup, int userId, int sessionId, String custRelSid, String prodRelSid) {
        int count = 0;
        try {
            String query = getLevelListQuery(projectionId, hierarchyIndicator, levelNo, hierarchyNo, productHierarchyNo, customerHierarchyNo, isFilter, false, true, 0, 0, false, isCustom, customId, userGroup, userId, sessionId, custRelSid, prodRelSid);
            List<Object> list = (List<Object>) HelperTableLocalServiceUtil.executeSelectQuery(query);
            if (list != null && !list.isEmpty()) {
                Object ob = list.get(0);
                count = Integer.valueOf(String.valueOf(ob));
            }
        } catch (Exception ex) {
            LOGGER.info(ex.getCause());
        }
        return count;
    }
    
}
