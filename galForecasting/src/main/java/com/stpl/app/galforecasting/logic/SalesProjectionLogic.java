/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.logic;

import com.stpl.app.galforecasting.dao.SalesProjectionDAO;
import com.stpl.app.galforecasting.dao.impl.SalesProjectionDAOImpl;
import com.stpl.app.galforecasting.dto.LivesDTO;
import com.stpl.app.galforecasting.dto.PMPYRowDto;
import com.stpl.app.galforecasting.dto.PeriodDTO;
import com.stpl.app.galforecasting.dto.SalesProjectionDTO;
import com.stpl.app.galforecasting.dto.SalesRowDto;
import com.stpl.app.model.CcpDetails;
import com.stpl.app.model.CompanyMaster;
import com.stpl.app.model.ContractMaster;
import com.stpl.app.model.MasterDataAttribute;
import com.stpl.app.model.ProjectionDetails;
import com.stpl.app.model.ProjectionMaster;
import com.stpl.app.galforecasting.queryUtils.PPAQuerys;
import com.stpl.app.service.CcpDetailsLocalServiceUtil;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.service.MasterDataAttributeLocalServiceUtil;
import com.stpl.app.service.ProjectionMasterLocalServiceUtil;
import com.stpl.app.galforecasting.sessionutils.SessionDTO;
import com.stpl.app.galforecasting.ui.form.SalesProjection;
import com.stpl.app.galforecasting.utils.CommonUtils;
import com.stpl.app.galforecasting.utils.Constant;
import static com.stpl.app.galforecasting.utils.Constant.STRING_EMPTY;
import com.stpl.ifs.util.CustomTableHeaderDTO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionList;
import com.stpl.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.StringUtils;

// TODO: Auto-generated Javadoc
/**
 * The Class SalesProjectionLogic.
 *
 * @author maheshj
 */
public class SalesProjectionLogic {

    /**
     * The Constant LOGGER.
     */
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(SalesProjectionLogic.class);

    /**
     * Generate SALES_SMALL result.
     *
     * @param selections the selections
     * @return the list
     */
    public List<SalesProjectionDTO> generateSalesResult(Object selections[]) {
        try {
            List list = null;
            String frequency = (String) selections[0];
            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
            DynamicQuery query = null;

            list = salesProjectionDAO.getSalesProjectionResult(query);

        } catch (PortalException ex) {
            Logger.getLogger(SalesProjectionLogic.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SalesProjectionLogic.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    /**
     * Generate SALES_SMALL projection.
     *
     * @param selections the selections
     * @return the list
     */
    public List<SalesRowDto> generateSalesProjection(Object[] selections, SalesProjection salesObject) {
        List list = null;
        List<SalesRowDto> resultList = new ArrayList<SalesRowDto>();
        try {
            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
            boolean iscustom = false;
            boolean count = false;
            int treeLeveNo = 0;
            String method = (String) selections[8];
            String parentViewType = STRING_EMPTY;
            String viewType = "C";
            if (!method.equals("customRefresh")) {
                int rowCount = ((CustomTableHeaderDTO) selections[3]).getSingleColumns().size() - 2;
               
                List custList = (List) selections[10];
                iscustom = (Boolean) selections[11];
                treeLeveNo = (Integer) custList.get(2);

                count = (Boolean) selections[7];
                parentViewType = String.valueOf(selections[17]);
                viewType = String.valueOf(selections[9]);

            }

            String lastCustomerHierarchyno = Constant.PERCENT;
            String lastProductHierarchyno = Constant.PERCENT;

            if (method.equals("fetchSalesResult")) {
                if (iscustom) {
                    lastCustomerHierarchyno = (String) selections[19];
                    lastProductHierarchyno = (String) selections[20];
                    parentViewType = String.valueOf(selections[17]);
                    viewType = String.valueOf(selections[9]);
                    if (String.valueOf(selections[17]).equals(Constant.INDICATOR_LOGIC_CUSTOMER_HIERARCHY)) {
                        if (!String.valueOf(selections[16]).equals(STRING_EMPTY)) {
                            lastCustomerHierarchyno = (String) selections[16];
                        }

                    } else {
                        if (!String.valueOf(selections[16]).equals(STRING_EMPTY)) {
                            lastProductHierarchyno = (String) selections[16];
                        }
                    }

                }
            }

            list = salesProjectionDAO.getSalesProjection(selections);

            resultList = convertfinalResultLists(list, iscustom, treeLeveNo + 1, salesObject, lastCustomerHierarchyno, lastProductHierarchyno, viewType, parentViewType);

        } catch (Exception ex) {
            Logger.getLogger(SalesProjectionLogic.class.getName()).log(Level.SEVERE, null, ex);
        }

        return resultList;
    }

    /**
     * Adjust SALES_SMALL projection.
     *
     * @param selections the selections
     * @return the list
     */
    private List<SalesRowDto> adjustSalesProjection(Object[] selections, Object[] inputs) {
        List<SalesRowDto> list = null;
        try {
            if (saveAdjustmentSelections(selections));

            callAdjustmentProcedure(inputs);

        } catch (Exception ex) {
            Logger.getLogger(SalesProjectionLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    /**
     * to call the Adjustment procedure.
     *
     * @param selections the selections
     * @return true, if successful
     */
    public boolean callAdjustmentProcedure(Object inputs[]) throws SystemException, SQLException {
        boolean status = false;

        final DataSourceConnection dataSourceConnection = DataSourceConnection.getInstance();
        Connection connection = null;
        CallableStatement statement = null;
        try {
            connection = dataSourceConnection.getConnection();

            LOGGER.info("Entering callAdjustmentProcedure  ::::");
            if (connection != null) {
                statement = connection.prepareCall("{call PRC_SALES_MANUAL_ADJUSTMENT (?,?,?,?,?)}");
                statement.setObject(1, inputs[0]); //  @BASLINE_PERIODS 
                statement.setObject(2, inputs[1]); //  @SELECTED_PERIODS
                statement.setObject(3, inputs[2]); //  @PROJECTION_SID
                statement.setObject(4, Integer.parseInt((String) inputs[3])); //  @USER_ID
                statement.setObject(5, Integer.parseInt((String) inputs[4])); //  @SESSION_ID

                status = statement.execute();
            }

            LOGGER.info("Ending callAdjustmentProcedure return  staus ::::" + status);
        } catch (Exception ex) {
            LOGGER.error(new Date() + ex.getMessage());
            throw new SystemException(ex);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
            try {
                connection.close();
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
        }

        return status;

    }

    /**
     * Adjust SALES_SMALL projection.
     *
     * @param selections the selections
     * @return the list
     */
    public List<SalesProjectionDTO> calculateSalesProjection(Object inputs[]) {
        try {

            saveCalculationSelections(inputs);
        } catch (Exception ex) {
            Logger.getLogger(SalesProjectionLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    /**
     * to call the Adjustment procedure.
     *
     * @param selections the selections
     * @return true, if successful
     */
    public boolean callCalculationProcedure(Object inputs[]) throws SystemException, SQLException {
        boolean status = false;

        final DataSourceConnection dataSourceConnection = DataSourceConnection.getInstance();
        Connection connection = null;
        CallableStatement statement = null;
        try {
            connection = dataSourceConnection.getConnection();

            LOGGER.info("Entering callCalculationProcedure  ::::");
            if (connection != null) {

                statement = connection.prepareCall("{call PRC_NM_SALES_PROJECTION (?,?,?)}");
                statement.setObject(1, inputs[0]);
                statement.setObject(2, Integer.parseInt((String) inputs[9]));
                statement.setObject(3, Integer.parseInt((String) inputs[10]));
//  @PROJECTION_SID
                status = statement.execute();
            }

            LOGGER.info("Ending callCalculationProcedure return  staus ::::" + status);
        } catch (Exception ex) {
            LOGGER.error(new Date() + ex.getMessage());
            throw new SystemException(ex);

        } finally {
            try {
                statement.close();
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
            try {
                connection.close();
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
        }

        return status;

    }

    /**
     * Adjust SALES_SMALL projection.
     *
     * @param selections the selections
     * @return the list
     */
    public List<SalesProjectionDTO> levelFilterSalesProjection(Object selections[]) {
        try {
            List list = null;
            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
            DynamicQuery query = null;

        } catch (Exception ex) {
            Logger.getLogger(SalesProjectionLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    /**
     * Mass update SALES_SMALL projection.
     *
     * @param selections the selections
     * @return the list
     */
    public List<SalesProjectionDTO> massUpdateSalesProjection(Object selections[]) {

        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        try {
            salesProjectionDAO.getSalesProjection(selections);
        } catch (PortalException ex) {
            Logger.getLogger(SalesProjectionLogic.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SalesProjectionLogic.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

    /**
     * to call the Mass Update procedure.
     *
     * @param selections the selections
     * @return true, if successful
     */
    public boolean callMassUpdateProcedure(Object selections[]) {
        try {

        } catch (Exception ex) {

        }

        return true;
    }

    public int LoadHistoryValues(int projectionId) {
        int totalQuators = 4;
        List listDate = null;
        DynamicQuery dynamicquerryFrom = DynamicQueryFactoryUtil.forClass(ProjectionMaster.class);
        ProjectionList projectionListFrom = ProjectionFactoryUtil.projectionList();

        dynamicquerryFrom.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_MASTER_SID, projectionId));

        projectionListFrom.add(ProjectionFactoryUtil.property("fromDate"));
        dynamicquerryFrom.setProjection(ProjectionFactoryUtil.distinct(projectionListFrom));
        try {
            List list = ProjectionMasterLocalServiceUtil.dynamicQuery(dynamicquerryFrom);
            String tempDate = StringUtils.EMPTY;
            tempDate = String.valueOf(list.get(0));

            Date currentDate = new Date();
            tempDate = tempDate.substring(0, 10);
            String temparry[] = tempDate.split("-");
            int fromYear = Integer.parseInt(String.valueOf(temparry[0]));
            int fromQuator = getQuator(Integer.parseInt(String.valueOf(temparry[1])));
            int toYear = currentDate.getYear() + 1900;
            int toQuator = getQuator(currentDate.getMonth() + 1);

            if (getQuator(currentDate.getMonth()) == 0) {
                toYear = toYear - 1;
                toQuator = 4;

            }
            totalQuators = getTotalHistoryPeriods(fromYear, toYear, fromQuator, toQuator);

        } catch (SystemException ex) {
            Logger.getLogger(SalesProjection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return totalQuators;

    }

    public String loadTotalLives(Object[] inputs) {
        Double lives = 0.0;
        List<String> list = new ArrayList<String>();
        list = MasterDataAttributeLocalServiceUtil.getTotalLives(inputs);
        if (list != null) {
            for (String live : list) {
                lives = lives + Double.parseDouble(live);
            }
        }
        return String.valueOf(lives);
    }

    public List<PeriodDTO> loadAlternateHistory(final Object[] inputs) throws SystemException, SQLException {

        List<PeriodDTO> list = new ArrayList<PeriodDTO>();
        if (callAlternateHistoryProcedure(inputs)) {

        }

        return list;
    }

    public boolean callAlternateHistoryProcedure(final Object[] inputs) throws SystemException, SQLException {

        boolean status = false;

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

                status = statement.execute();
            }

            LOGGER.info("Ending callAlternateHistoryProcedure return  staus ::::" + status);
        } catch (Exception ex) {
            LOGGER.error(new Date() + ex.getMessage());
            throw new SystemException(ex);
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
        }

        return status;
    }

    public boolean callSalesInsert(final Object[] inputs) throws SystemException, SQLException {

        boolean status = false;

        final DataSourceConnection dataSourceConnection = DataSourceConnection.getInstance();
        Connection connection = null;
        CallableStatement statement = null;
        try {
            connection = dataSourceConnection.getConnection();

            LOGGER.info("Entering callSalesInsertProcedure  ::::");
            if (connection != null) {
                statement = connection.prepareCall("{call PRC_NM_SALES_INSERT (?,?,?)}");
                statement.setObject(1, inputs[0]);
                statement.setObject(2, Integer.parseInt((String) inputs[1]));
                statement.setObject(3, Integer.parseInt((String) inputs[2]));

                status = statement.execute();
            }

            LOGGER.info("Ending callSalesInsertProcedure return  staus ::::" + status);
        } catch (Exception ex) {
            LOGGER.error(new Date() + ex.getMessage());
            throw new SystemException(ex);
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
            try {
                connection.close();
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
        }

        return status;
    }

    private List<SalesRowDto> convertfinalResultLists(List list, boolean iscustom, int treeLevelNo, SalesProjection salesObject, String lastCustomerHierarchyno, String lastProductHierarchyno, String viewType, String parentViewType) {
        SalesRowDto salesRowDto = null;
        SalesRowDto lastSalesRowDto = null;
        List<SalesRowDto> salesRowList = new ArrayList<SalesRowDto>();
        int j = 0;

        String tempCcpid = STRING_EMPTY;
        PeriodDTO dto = new PeriodDTO();

        for (int i = 0; i < list.size(); i++) {
            dto = new PeriodDTO();
            Object obj[] = (Object[]) list.get(i);
            int calcBasedCount = 0;
            int calcPeriodCount = 0;
            int methodolgyCount = 0;
            if (obj[21] != null) {
                calcPeriodCount = Integer.parseInt(String.valueOf(obj[21]));
            }
            if (obj[22] != null) {
                methodolgyCount = Integer.parseInt(String.valueOf(obj[22]));
            }
            if (obj[23] != null) {
                calcBasedCount = Integer.parseInt(String.valueOf(obj[23]));
            }
            if (obj[24] != null) {
                dto.setUncheckCount(Integer.parseInt(String.valueOf(obj[24])));
            }

            if (obj[0] != null) {

                dto.setAccountGrowth(String.valueOf(obj[0]));
            }
            if (obj[1] != null) {

                dto.setProductGrowth(String.valueOf(obj[1]));
            }

            if (obj[2] != null) {

                dto.setProjSales(String.valueOf(obj[2]));
            }
            if (obj[3] != null) {
                dto.setProjUnits(String.valueOf(obj[3]));
            }
            if (obj[4] != null) {
                dto.setActualSales(String.valueOf(obj[4]));
            }
            if (obj[5] != null) {
                dto.setActualUnits(String.valueOf(obj[5]));
            }
            if (obj[6] != null) {
                dto.setLevelNo(Integer.parseInt(String.valueOf(obj[6])));
            }

            if (obj[7] != null) {
                dto.setLevelName(String.valueOf(obj[7]));
            }
            if (obj[8] != null) {
                dto.setYear(String.valueOf(obj[8]));
            }
            if (obj[9] != null) {
                dto.setQuator(String.valueOf(obj[9]));
            }

            if (obj[10] != null) {
                if (((Constant.TRADINGPARTNER.equals(dto.getHierarchyLevel()) || Constant.TRADING_PARTNER.equals(dto.getHierarchyLevel())))) {
                    dto.setUserGroup((String.valueOf(obj[10])));
                }
            }
            if (obj[11] != null) {
                if (1 == calcPeriodCount && 1 == methodolgyCount && calcBasedCount == 1) {
                    dto.setBaseLine((String.valueOf(obj[11])));
                }
            }
            if (obj[12] != null) {
                if (1 == calcPeriodCount && 1 == methodolgyCount && calcBasedCount == 1) {
                    dto.setMethodologyUsed((String.valueOf(obj[12])));
                }
            }
            if (obj[13] != null) {
                dto.setRelationshipLevelSid(Integer.parseInt(String.valueOf(obj[13])));
            }
            if (obj[14] != null) {
                dto.setHierarchyNo(String.valueOf(obj[14]));
            }

            if (obj[15] != null) {

                salesObject.rowCountMap.put(dto.getHierarchyNo(), Integer.parseInt(String.valueOf(obj[15])));
                dto.setCcpCount(Integer.parseInt(String.valueOf(obj[15])));
            }

            if (obj[16] != null) {
                dto.setActualsOrProj(Integer.parseInt(String.valueOf(obj[16])));
            }

            if (obj[17] != null) {
                dto.setActualPrpjectionSales((String.valueOf(obj[17])));
            }

            if (obj[18] != null) {
                dto.setActualProjectionUnits(String.valueOf(obj[18]));
            }

            if (obj[19] != null) {
                dto.setCheckUncheckRec(Integer.parseInt(String.valueOf(obj[19])));
            }

            if (obj[20] != null) {
                dto.setHierarchyLevel(String.valueOf(obj[20]));
            }

            if (obj[10] != null) {
                if (((Constant.TRADINGPARTNER.equals(dto.getHierarchyLevel()) || Constant.TRADING_PARTNER.equals(dto.getHierarchyLevel())))) {
                    dto.setUserGroup((String.valueOf(obj[10])));
                }
            }

            if (tempCcpid.equalsIgnoreCase(STRING_EMPTY)) {
                tempCcpid = dto.getLevelName();
                lastSalesRowDto = new SalesRowDto();
                if (dto.getCheckUncheckRec() == 0) {
                    lastSalesRowDto.addBooleanProperties(Constant.CHECK, new Boolean(false));
                } else {
                    lastSalesRowDto.addBooleanProperties(Constant.CHECK, new Boolean(true));
                }
                lastSalesRowDto.addStringProperties(Constant.GROUP, String.valueOf(dto.getUserGroup()));
                lastSalesRowDto.addStringProperties(Constant.BASELINE, String.valueOf(dto.getBaseLine()));
                lastSalesRowDto.addStringProperties(Constant.METHODOLOGY, String.valueOf(dto.getMethodologyUsed()));
                lastSalesRowDto.setRelationLevelSid(dto.getRelationshipLevelSid());
                lastSalesRowDto.setHierarchyNo(dto.getHierarchyNo());
                lastSalesRowDto.setHierarchyLevel(dto.getHierarchyLevel());
                lastSalesRowDto.setLevelNo(dto.getLevelNo());
                lastSalesRowDto.setTreeLevelNo(treeLevelNo);
                lastSalesRowDto.setLastCustomerHierarchyno(lastCustomerHierarchyno);
                lastSalesRowDto.setLastProductHierarchyno(lastProductHierarchyno);
                lastSalesRowDto.setParentHierarchyType(viewType);
                lastSalesRowDto.setUncheckCount(dto.getUncheckC0unt());
                lastSalesRowDto.setCcpCount(String.valueOf(dto.getCcpCount()));
                salesRowDto = new SalesRowDto();
            }

            if (tempCcpid.equalsIgnoreCase(dto.getLevelName())) {

                salesRowDto.setLevelName(dto.getLevelName());
                salesRowDto.setActualLevel(dto.getLevelName());
                salesRowDto.setLevelNo(dto.getLevelNo());

                String key = Constant.Q_SMALL + dto.getQuator() + "-" + dto.getYear();

                if (dto.getActualsOrProj() == 0) {
                    salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-ProjectedSales", String.valueOf(dto.getProjSales()));
                    salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-ProjectedUnits", String.valueOf(dto.getProjUnits()));
                    salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-ProductGrowth", String.valueOf(dto.getProductGrowth()));
                    salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-AccountGrowth", String.valueOf(dto.getAccountGrowth()));
                } else {
                    salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-ActualSales", String.valueOf(dto.getProjSales()));
                    salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-ActualUnits", String.valueOf(dto.getProjUnits()));
                    salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-HistoryProjectedSales", String.valueOf(0.0));
                    salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-HistoryProjectedUnits", String.valueOf(0.0));

                }

            } else {

                salesRowDto.addBooleanProperties(Constant.CHECK, (Boolean) lastSalesRowDto.getPropertyValue(Constant.CHECK));
                salesRowDto.addStringProperties(Constant.GROUP, String.valueOf((String) lastSalesRowDto.getPropertyValue(Constant.GROUP)));
                salesRowDto.addStringProperties(Constant.BASELINE, String.valueOf((String) lastSalesRowDto.getPropertyValue(Constant.BASELINE)));
                salesRowDto.addStringProperties(Constant.METHODOLOGY, String.valueOf((String) lastSalesRowDto.getPropertyValue(Constant.METHODOLOGY)));
                salesRowDto.setRelationLevelSid(lastSalesRowDto.getRelationLevelSid());
                salesRowDto.setHierarchyNo(lastSalesRowDto.getHierarchyNo());
                salesRowDto.setHierarchyLevel(lastSalesRowDto.getHierarchyLevel());
                salesRowDto.setTreeLevelNo(lastSalesRowDto.getTreeLevelNo());
                               salesRowDto.setLastCustomerHierarchyno(lastCustomerHierarchyno);
                salesRowDto.setLastProductHierarchyno(lastProductHierarchyno);
                salesRowDto.setParentHierarchyType(viewType);
                salesRowDto.setUncheckCount(lastSalesRowDto.getUncheckCount());
                salesRowDto.setCcpCount(lastSalesRowDto.getCcpCount());
                salesObject.keymap.put(salesRowDto.getLevelName(), j);
                j = j + 1;

                salesRowDto.setLevelName(salesObject.getSalesSession().getLevelValueDiscription(salesRowDto.getHierarchyNo(), viewType));

                salesRowList.add(salesRowDto);
                tempCcpid = dto.getLevelName();

                lastSalesRowDto = new SalesRowDto();
                if (dto.getCheckUncheckRec() == 0) {
                    lastSalesRowDto.addBooleanProperties(Constant.CHECK, new Boolean(false));
                } else {
                    lastSalesRowDto.addBooleanProperties(Constant.CHECK, new Boolean(true));
                }
                lastSalesRowDto.addStringProperties(Constant.GROUP, String.valueOf(dto.getUserGroup()));
                lastSalesRowDto.addStringProperties(Constant.BASELINE, String.valueOf(dto.getBaseLine()));
                lastSalesRowDto.addStringProperties(Constant.METHODOLOGY, String.valueOf(dto.getMethodologyUsed()));
                lastSalesRowDto.setRelationLevelSid(dto.getRelationshipLevelSid());
                lastSalesRowDto.setHierarchyNo(dto.getHierarchyNo());
                lastSalesRowDto.setHierarchyLevel(dto.getHierarchyLevel());
                lastSalesRowDto.setTreeLevelNo(treeLevelNo);
                lastSalesRowDto.setLastCustomerHierarchyno(lastCustomerHierarchyno);
                lastSalesRowDto.setLastProductHierarchyno(lastProductHierarchyno);
                lastSalesRowDto.setParentHierarchyType(viewType);
                lastSalesRowDto.setUncheckCount(dto.getUncheckC0unt());
                lastSalesRowDto.setCcpCount(String.valueOf(dto.getCcpCount()));
                salesRowDto = new SalesRowDto();
                salesRowDto.setLevelName(dto.getLevelName());
                salesRowDto.setActualLevel(dto.getLevelName());
                salesRowDto.setLevelNo(dto.getLevelNo());
                salesRowDto.setTreeLevelNo(treeLevelNo);
                salesRowDto.setLastCustomerHierarchyno(lastCustomerHierarchyno);
                salesRowDto.setLastProductHierarchyno(lastProductHierarchyno);
                salesRowDto.setParentHierarchyType(viewType);
                String key = Constant.Q_SMALL + dto.getQuator() + "-" + dto.getYear();

                if (dto.getActualsOrProj() == 0) {
                    salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-ProjectedSales", String.valueOf(dto.getProjSales()));
                    salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-ProjectedUnits", String.valueOf(dto.getProjUnits()));
                    salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-ProductGrowth", String.valueOf(dto.getProductGrowth()));
                    salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-AccountGrowth", String.valueOf(dto.getAccountGrowth()));
                } else {
                    salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-ActualSales", String.valueOf(dto.getProjSales()));
                    salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-ActualUnits", String.valueOf(dto.getProjUnits()));
                    salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-HistoryProjectedSales", String.valueOf(0.0));
                    salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-HistoryProjectedUnits", String.valueOf(0.0));
                }

            }
            if (i == (list.size() - 1)) {
                salesRowDto.setLevelName(dto.getLevelName());
                salesRowDto.setActualLevel(dto.getLevelName());
                salesRowDto.setLevelNo(dto.getLevelNo());

                String key = Constant.Q_SMALL + dto.getQuator() + "-" + dto.getYear();

                if (dto.getActualsOrProj() == 0) {
                    salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-ProjectedSales", String.valueOf(dto.getProjSales()));
                    salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-ProjectedUnits", String.valueOf(dto.getProjUnits()));
                    salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-ProductGrowth", String.valueOf(dto.getProductGrowth()));
                    salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-AccountGrowth", String.valueOf(dto.getAccountGrowth()));
                } else {
                    salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-ActualSales", String.valueOf(dto.getProjSales()));
                    salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-ActualUnits", String.valueOf(dto.getProjUnits()));
                    salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-HistoryProjectedSales", String.valueOf(0.0));
                    salesRowDto.addStringProperties(StringUtils.EMPTY + key + "-HistoryProjectedUnits", String.valueOf(0.0));

                }
                salesRowDto.addBooleanProperties(Constant.CHECK, (Boolean) lastSalesRowDto.getPropertyValue(Constant.CHECK));
                salesRowDto.addStringProperties(Constant.GROUP, String.valueOf((String) lastSalesRowDto.getPropertyValue(Constant.GROUP)));
                salesRowDto.addStringProperties(Constant.BASELINE, String.valueOf((String) lastSalesRowDto.getPropertyValue(Constant.BASELINE)));
                salesRowDto.addStringProperties(Constant.METHODOLOGY, String.valueOf((String) lastSalesRowDto.getPropertyValue(Constant.METHODOLOGY)));
                salesRowDto.setRelationLevelSid(lastSalesRowDto.getRelationLevelSid());
                salesRowDto.setHierarchyNo(lastSalesRowDto.getHierarchyNo());
                salesRowDto.setHierarchyLevel(lastSalesRowDto.getHierarchyLevel());
                salesRowDto.setTreeLevelNo(lastSalesRowDto.getTreeLevelNo());
                salesRowDto.setLastCustomerHierarchyno(lastCustomerHierarchyno);
                salesRowDto.setLastProductHierarchyno(lastProductHierarchyno);
                salesRowDto.setParentHierarchyType(viewType);
                salesRowDto.addStringProperties(Constant.GROUP, String.valueOf(dto.getUserGroup()));
                salesRowDto.addStringProperties(Constant.BASELINE, String.valueOf(dto.getBaseLine()));
                salesRowDto.addStringProperties(Constant.METHODOLOGY, String.valueOf(dto.getMethodologyUsed()));
                salesRowDto.setRelationLevelSid(dto.getRelationshipLevelSid());
                salesRowDto.setHierarchyNo(dto.getHierarchyNo());
                salesRowDto.setHierarchyLevel(dto.getHierarchyLevel());

                salesRowDto.setTreeLevelNo(treeLevelNo);
                salesRowDto.setLastCustomerHierarchyno(lastCustomerHierarchyno);
                salesRowDto.setLastProductHierarchyno(lastProductHierarchyno);
                salesRowDto.setUncheckCount(dto.getUncheckC0unt());
                salesRowDto.setCcpCount(String.valueOf(dto.getCcpCount()));
                calcBasedCount = 0;
                calcPeriodCount = 0;
                methodolgyCount = 0;
                salesObject.keymap.put(salesRowDto.getLevelName(), j);
                j = j + 1;
                salesRowDto.setLevelName(salesObject.getSalesSession().getLevelValueDiscription(salesRowDto.getHierarchyNo(), viewType));

                salesRowList.add(salesRowDto);
            }
        }
        return salesRowList;
    }

    public Map<String, Map<Integer, Double>> getLivesForSelectedCustomers(Object inputs[]) {

        List list = new ArrayList<MasterDataAttribute>();
        SessionDTO session = (SessionDTO) inputs[2];
        Map<String, Map<Integer, Double>> finalMap = new HashMap<String, Map<Integer, Double>>();
        List<LivesDTO> livesDto = new ArrayList<LivesDTO>();
        list = MasterDataAttributeLocalServiceUtil.getTotalLives(inputs);
        LivesDTO lives = null;
        Date startDate = null;
        Date endDate = null;
        session.getForecastDTO().getForecastEndDate();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Object[] obj = (Object[]) list.get(i);

                lives = new LivesDTO();

                if (!String.valueOf(obj[1]).equals(Constant.NULL) && !(String.valueOf(obj[1]).trim().isEmpty())) {
                    startDate = new Date(Date.parse(((String) obj[1])));

                } else {
                    startDate = session.getForecastDTO().getHistoryStartDate();

                }
                if (!String.valueOf(obj[2]).equals(Constant.NULL) && !(String.valueOf(obj[2]).trim().isEmpty())) {
                    endDate = new Date(Date.parse(((String) obj[2])));
                } else {
                    endDate = session.getForecastDTO().getForecastEndDate();
                }

                lives.setEndYear(endDate.getYear());
                lives.setEndQuator(getQuator(endDate.getMonth() + 1));
                lives.setStartQuator(getQuator(startDate.getMonth() + 1));
                lives.setStartYear(startDate.getYear());
                lives.setStartDate(startDate);
                lives.setEndDate(endDate);
                lives.setLives((Double.valueOf(String.valueOf(obj[3]))));
                lives.setComp_Name(((String) obj[0]));
                livesDto.add(lives);

            }
            List companyList = new ArrayList();
            List<LivesDTO> livesList = null;
            String tempcompName = STRING_EMPTY;
            LivesDTO companyDto = null;
            for (int i = 0; i < livesDto.size(); i++) {
                companyDto = livesDto.get(i);
                if (tempcompName.equals(STRING_EMPTY)) {
                    tempcompName = companyDto.getComp_Name();
                    livesList = new ArrayList<LivesDTO>();

                }

                if (tempcompName.equals(companyDto.getComp_Name())) {
                    livesList.add(companyDto);

                } else {
                    tempcompName = companyDto.getComp_Name();
                    companyList.add(livesList);
                    livesList = new ArrayList<LivesDTO>();
                    livesList.add(companyDto);
                }
                if (i == (livesDto.size() - 1)) {

                    companyList.add(livesList);
                }

            }
            Map<Integer, Double> values = null;
            String tempCompanyName = STRING_EMPTY;
            for (int i = 0; i < companyList.size(); i++) {
                List<LivesDTO> livesDtoList = (List<LivesDTO>) companyList.get(i);
                values = new HashMap<Integer, Double>();

                Collections.sort(livesDtoList, new Comparator<LivesDTO>() {
                    @Override
                    public int compare(LivesDTO o1, LivesDTO o2) {
                        return o1.getStartYear() - o2.getStartYear();
                    }
                });

                for (LivesDTO dto : livesDtoList) {

                    tempCompanyName = dto.getComp_Name();
                    boolean tempBool = true;
                    int ia = 0;
                    while (tempBool) {

                        values.put(Integer.parseInt((dto.getStartYear() + 1900) + StringUtils.EMPTY + dto.getStartQuator()), dto.getLives());

                        if (dto.getStartYear() == dto.getEndYear() && dto.getStartQuator() == dto.getEndQuator()) {

                            tempBool = false;
                        }
                        if ((dto.getStartQuator() % 4) == 0) {
                            dto.setStartQuator(1);
                            dto.setStartYear(dto.getStartYear() + 1);
                        } else {
                            dto.setStartQuator(dto.getStartQuator() + 1);
                        }
                        if (ia > 200) {
                            break;
                        } else {
                            ia++;
                        }
                    }

                }
                if (values != null) {
                    finalMap.put(tempCompanyName, values);
                } else {

                }

            }
        } else {

        }

        return finalMap;
    }

    public int getQuator(int month) {
        if (month < 4) {
            return 1;
        }
        if (month < 7) {
            return 2;
        }
        if (month < 10) {
            return 3;
        }
        return 4;
    }

    public boolean saveAdjustmentSelections(Object[] selections) {
        boolean status = false;

        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        try {
            salesProjectionDAO.getSalesProjection(selections);
        } catch (PortalException ex) {
            Logger.getLogger(SalesProjectionLogic.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SalesProjectionLogic.class.getName()).log(Level.SEVERE, null, ex);
        }

        return status;
    }

    public boolean saveSalesProjections(Object[] inputs) {
        boolean status = false;
        List list = null;
        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();

        try {
            list = salesProjectionDAO.getSalesProjection(inputs);
        } catch (PortalException ex) {

            Logger.getLogger(SalesProjectionLogic.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {

            Logger.getLogger(SalesProjectionLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return status;
    }

    public List<SalesRowDto> loadSalesProjection(Object salesSelection[]) {
        List<SalesRowDto> resultList = new ArrayList<SalesRowDto>();
     
        return resultList;
    }

    public boolean checkAll(int projectionMasterSid) {
        boolean status = false;

        return status;
    }

    public boolean checkSelected() {
        boolean status = false;

        return status;

    }

    public int savecheckedRecords(Object[] inputs) {
        int count = 0;
        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        try {
            List list = salesProjectionDAO.getSalesProjection(inputs);
            if (list.size() > 0) {
                count = Integer.parseInt(String.valueOf(list.get(0)));
            }
        } catch (PortalException ex) {
            Logger.getLogger(SalesProjectionLogic.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SalesProjectionLogic.class.getName()).log(Level.SEVERE, null, ex);
        }

        return count;
    }

    public boolean saveCalculationSelections(Object[] inputs) {

        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        try {
            salesProjectionDAO.getSalesProjection(inputs);
        } catch (PortalException ex) {
            Logger.getLogger(SalesProjectionLogic.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SalesProjectionLogic.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }

    private List callPMPYProcedure(Object inputs[]) throws SystemException, SQLException {

        List list = null;

        final DataSourceConnection dataSourceConnection = DataSourceConnection.getInstance();
        Connection connection = null;
        CallableStatement statement = null;
        ResultSet resList = null;
        try {
            connection = dataSourceConnection.getConnection();

            LOGGER.info("Entering callSalesInsertProcedure  ::::");

            if (connection != null) {
                statement = connection.prepareCall("{call Nm_sales_pmpy (?,?,?,?)}");

                statement.setInt(1, Integer.parseInt((String) inputs[0]));  //PROJECTION_MASTER_SID
                statement.setString(2, String.valueOf(inputs[1]));   //@PROJECTION_DETAILS_SID
                statement.setInt(3, Integer.parseInt((String) inputs[2])); //CONTRACT_HOLDER_SID
                statement.setInt(4, Integer.parseInt((String) inputs[3]));   //TRADING_PARTNER_SID

                resList = statement.executeQuery();
            }

            list = new ArrayList();

            Object[] temp = null;
            while (resList.next()) {

                temp = new Object[5];

                temp[0] = resList.getString(1);
                temp[1] = resList.getString(2);
                temp[2] = resList.getString(3);
                temp[3] = resList.getString(4);
                temp[4] = resList.getString(5);
                list.add(temp);

            }

            LOGGER.info("Ending callSalesInsertProcedure return  staus ::::");
        } catch (Exception ex) {

            LOGGER.error(new Date() + ex.getMessage());
        } finally {
            try {
                statement.close();
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
            try {
                connection.close();
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
        }

        return list;

    }

    public List<PMPYRowDto> getPMPYResultList(Object[] inputs) {

        List<PMPYRowDto> resultList = new ArrayList<PMPYRowDto>();

        List list = null;
        try {
            list = callPMPYProcedure(inputs);
        } catch (SystemException ex) {

            Logger.getLogger(SalesProjectionLogic.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {

            Logger.getLogger(SalesProjectionLogic.class.getName()).log(Level.SEVERE, null, ex);
        }

        List<Object> propertyList = (List<Object>) inputs[4];

        if (list != null) {
            List<PeriodDTO> tempList = new ArrayList<PeriodDTO>();
            PeriodDTO periodDTO = null;
            for (int i = 0; i < list.size(); i++) {
                Object obj[] = (Object[]) list.get(i);
                periodDTO = new PeriodDTO();
                if (obj[0] != null) {
                    periodDTO.setQuator(String.valueOf(obj[0]));

                  
                }
                if (obj[1] != null) {
                    periodDTO.setYear(String.valueOf(obj[1]));

                  
                }
                if (obj[2] != null) {
                    periodDTO.setActualSales(String.valueOf(CommonUtils.MONEY.format(Double.valueOf(obj[2].toString()))));

                    
                }

                if (obj[3] != null) {
                   
                    periodDTO.setActualUnits(String.valueOf(CommonUtils.UNITVOLUME.format(Double.valueOf(obj[3].toString()))));
                    
                }
                if (obj[4] != null) {
                    
                    periodDTO.setLives(Double.valueOf(CommonUtils.UNITVOLUME.format(Double.valueOf(obj[4].toString()))));
                   
                }

                tempList.add(periodDTO);

            }

            PMPYRowDto pmpyRowDto1 = new PMPYRowDto();
            PMPYRowDto pmpyRowDto2 = new PMPYRowDto();
            PMPYRowDto pmpyRowDto3 = new PMPYRowDto();

            Map<String, String> properties1 = new HashMap<String, String>();
            Map<String, String> properties2 = new HashMap<String, String>();
            Map<String, String> properties3 = new HashMap<String, String>();
            for (Object obj : propertyList) {

                properties1.put(String.valueOf(obj), "0.0");
                properties2.put(String.valueOf(obj), "0.0");
                properties3.put(String.valueOf(obj), "0.0");

            }

            for (PeriodDTO pDto : tempList) {
                properties1.put(Constant.Q_SMALL + pDto.getQuator() + "-" + pDto.getYear(), StringUtils.EMPTY + pDto.getActualSales() + StringUtils.EMPTY);
                properties2.put(Constant.Q_SMALL + pDto.getQuator() + "-" + pDto.getYear(), StringUtils.EMPTY + pDto.getActualUnits() + StringUtils.EMPTY);
                properties3.put(Constant.Q_SMALL + pDto.getQuator() + "-" + pDto.getYear(), StringUtils.EMPTY + pDto.getLives() + StringUtils.EMPTY);
               
            }

            pmpyRowDto1.setProperties(properties1);
            pmpyRowDto1.setType(Constant.SALES_SMALL);
            pmpyRowDto2.setProperties(properties2);
            pmpyRowDto2.setType(Constant.UNITS_SMALL);
            pmpyRowDto3.setProperties(properties3);
            pmpyRowDto3.setType(Constant.LIVES);

            resultList.add(pmpyRowDto1);
            resultList.add(pmpyRowDto2);
            resultList.add(pmpyRowDto3);

        }

        return resultList;
    }

    public int getTotalHistoryPeriods(int fromYear, int toYear, int fromQuator, int toQuator) {

        int numberofQuators = 0;

        if (toYear - fromYear == 0) {

            numberofQuators = (toQuator - fromQuator) + 1;

        } else if (toYear - fromYear == 1) {
            fromQuator = 5 - fromQuator;
            numberofQuators = fromQuator + toQuator;

        } else if (toYear - fromYear > 1) {

            numberofQuators = toQuator + fromQuator + (4 * (toYear - fromYear));

        }

        return (numberofQuators);
    }

    public List getCurrent() {
        List list = new ArrayList();
        Calendar ob = Calendar.getInstance();
        int curMonth = ob.get(Calendar.MONTH);
        int curYear = ob.get(Calendar.YEAR);
        int curQuator = getQuator(curMonth + 1);
        list.add(curMonth);
        list.add(curQuator);
        list.add(curYear);
        return list;
    }

    public List<SalesRowDto> getLevelFilterValues(Object input[]) {

        List<SalesRowDto> levelList = new ArrayList<SalesRowDto>();
        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        List list = null;
        try {
            list = salesProjectionDAO.getSalesProjection(input);
        } catch (PortalException ex) {
            Logger.getLogger(SalesProjectionLogic.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SalesProjectionLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
        SalesRowDto dto;
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                dto = new SalesRowDto();
                Object obj[] = (Object[]) list.get(i);

                if (obj[0] != null) {

                    dto.setLevelName(String.valueOf(obj[0]));
                  
                }
                if (obj[1] != null) {

                    dto.setHierarchyNo(String.valueOf(obj[1]));
                   
                }
                levelList.add(dto);
            }

        }
        return levelList;
    }

    public int getProductCount(int prMasterSId) {
        int projectionDettailsId = 0;
        List list = new ArrayList();

        try {
            DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CcpDetails.class);

            final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();

            productProjectionList.add(ProjectionFactoryUtil.property(Constant.ITEM_MASTER_SID));
            dynamicQuery.add(PropertyFactoryUtil.forName(Constant.CCP_DETAILS_SID).in(
                    DynamicQueryFactoryUtil.forClass(ProjectionDetails.class)
                    .add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_MASTER_SID, prMasterSId)).setProjection(ProjectionFactoryUtil.property(Constant.CCP_DETAILS_SID))));
            dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
            list = CcpDetailsLocalServiceUtil.dynamicQuery(dynamicQuery);
            if (!list.isEmpty()) {

                if (list.size() > 1) {

                    return -1;
                }
                projectionDettailsId = Integer.valueOf(list.get(0).toString());
            }
        } catch (Exception ex) {
            Logger.getLogger(SalesProjectionLogic.class.getName()).log(Level.SEVERE, null, ex);
        }

        return projectionDettailsId;
    }

    public List getTradingPartnerInfo(int prDetId) {
        List list = new ArrayList();
        List<String> resultList = new ArrayList<String>();
        String tradingName = StringUtils.EMPTY;
        String tradingNo = StringUtils.EMPTY;
        String contractHolder = StringUtils.EMPTY;

        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CcpDetails.class);

        final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();

        productProjectionList.add(ProjectionFactoryUtil.property(Constant.COMPANYMASTERSID));
        productProjectionList.add(ProjectionFactoryUtil.property(Constant.CONTRACT_MASTER_SID));

        dynamicQuery.add(PropertyFactoryUtil.forName(Constant.CCP_DETAILS_SID).in(
                DynamicQueryFactoryUtil.forClass(ProjectionDetails.class)
                .add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_DETAILS_SID, prDetId))
                .setProjection(ProjectionFactoryUtil.property(Constant.CCP_DETAILS_SID))));

      
        dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
        try {
            list = CcpDetailsLocalServiceUtil.dynamicQuery(dynamicQuery);
        } catch (SystemException ex) {
            Logger.getLogger(SalesProjectionLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (!list.isEmpty()) {
            Object[] obj = (Object[]) list.get(0);
            String.valueOf(obj[0]);
            int companyMasterSid = Integer.parseInt(String.valueOf(obj[0]));

            int contractMasterSid = Integer.parseInt(String.valueOf(obj[1]));
            list = new ArrayList();
            DynamicQuery dynamicQuery1 = DynamicQueryFactoryUtil.forClass(CompanyMaster.class);

            final ProjectionList productProjectionList1 = ProjectionFactoryUtil.projectionList();

            productProjectionList1.add(ProjectionFactoryUtil.property(Constant.COMPANY_NAME));
            productProjectionList1.add(ProjectionFactoryUtil.property("companyNo"));
            dynamicQuery1.add(RestrictionsFactoryUtil.eq(Constant.COMPANYMASTERSID, companyMasterSid));
            dynamicQuery1.setProjection(ProjectionFactoryUtil.distinct(productProjectionList1));
            try {
                list = CompanyMasterLocalServiceUtil.dynamicQuery(dynamicQuery1);
            } catch (SystemException ex) {
                Logger.getLogger(SalesProjectionLogic.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (!list.isEmpty()) {

                Object[] obj1 = (Object[]) list.get(0);
                tradingName = String.valueOf(obj1[0]);
                tradingNo = String.valueOf(obj1[1]);
                list = new ArrayList();
                DynamicQuery dynamicQuery2 = DynamicQueryFactoryUtil.forClass(CompanyMaster.class);

                final ProjectionList productProjectionList2 = ProjectionFactoryUtil.projectionList();

                productProjectionList2.add(ProjectionFactoryUtil.property(Constant.COMPANY_NAME));

                dynamicQuery2.add(PropertyFactoryUtil.forName(Constant.COMPANYMASTERSID).in(
                        DynamicQueryFactoryUtil.forClass(ContractMaster.class)
                        .add(RestrictionsFactoryUtil.eq(Constant.CONTRACT_MASTER_SID, contractMasterSid))
                        .setProjection(ProjectionFactoryUtil.property("contHoldCompanyMasterSid"))));

                dynamicQuery2.setProjection(ProjectionFactoryUtil.distinct(productProjectionList2));
                try {
                    list = CompanyMasterLocalServiceUtil.dynamicQuery(dynamicQuery2);
                } catch (SystemException ex) {
                    Logger.getLogger(SalesProjectionLogic.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (!list.isEmpty()) {
                    contractHolder = (String) list.get(0);
                   
                }

              
            }
        }

        resultList.add(tradingName);
        resultList.add(tradingNo);
        resultList.add(contractHolder);

        return resultList;
    }


    public void importPMPY(Object inputs[], SessionDTO session) {

        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        try {

            List input = new ArrayList();
            input.add(inputs[3]);
            input.add(inputs[2]);
            input.add(session.getSessionId());
            input.add(session.getUserId());
            input.add(inputs[1]);
            input.add(CommonUtils.getListToString((List) inputs[0]));

            String changeProperty;
            if (inputs[4].equals(Constant.SALES_CAPS)) {
                PPAQuerys.PPAUpdate(input, "PMPY-UPDATE-SALES");
                changeProperty = Constant.SALES_CAPS;
            } else {
                PPAQuerys.PPAUpdate(input, "PMPY-UPDATE-UNITS");
                changeProperty = Constant.UNITS_SMALL;
            }
            callManualEntry(session, changeProperty);

        } catch (Exception ex) {
            Logger.getLogger(SalesProjectionLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getPMPYProtDetID(Object input[]) {
        List list = new ArrayList();
        int id = 0;
        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        try {
            list = salesProjectionDAO.getSalesProjection(input);
        } catch (PortalException ex) {
            Logger.getLogger(SalesProjectionLogic.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SalesProjectionLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (list.size() > 0) {
            id = list.size();
           
        }
        return id;
    }

    public List getProjectionDetailsSid(Object input[]) {
        List list = new ArrayList();
        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        try {
            list = salesProjectionDAO.getSalesProjection(input);
        } catch (PortalException ex) {
            Logger.getLogger(SalesProjectionLogic.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SalesProjectionLogic.class.getName()).log(Level.SEVERE, null, ex);
        }

       
        return list;
    }

    public int getLevelIndex(int projectionId, String hierarchy, String hierarchyNo, String selectedHiearchyNo) {
        int count = 1;
        Object input[] = new Object[10];
        input[8] = "getLevelIndex";
        input[0] = projectionId;
        input[1] = hierarchy;
        input[2] = hierarchyNo;
        input[3] = selectedHiearchyNo;

        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        try {
            List list = salesProjectionDAO.getSalesProjection(input);
            if (list.size() > 0) {
                count = Integer.valueOf(list.get(0).toString());
            }
        } catch (PortalException ex) {
            Logger.getLogger(SalesProjectionLogic.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SalesProjectionLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public int getCount(Object[] input) {
        int count = 1;

        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        try {
            List list = salesProjectionDAO.getSalesProjection(input);
            count = Integer.valueOf(list.get(0).toString());
        } catch (PortalException ex) {
            Logger.getLogger(SalesProjectionLogic.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SalesProjectionLogic.class.getName()).log(Level.SEVERE, null, ex);
        }

        return count;
    }

    public List<String> loadGroup(Object[] input) {
        List<String> groupList = new ArrayList<String>();
        try {
            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
            groupList = salesProjectionDAO.getSalesProjection(input);
        } catch (PortalException ex) {
            Logger.getLogger(SalesProjectionLogic.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SalesProjectionLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return groupList;
    }

    public boolean callManualEntry(SessionDTO session, String changedProperty) {
        boolean status = false;

        final DataSourceConnection dataSourceConnection = DataSourceConnection.getInstance();
        Connection connection = null;
        CallableStatement statement = null;
        try {
            connection = dataSourceConnection.getConnection();

            LOGGER.info("Entering callManualEntryProcedure  ::::");

            if (connection != null) {

                statement = connection.prepareCall("{call PRC_SALES_PROJ_MANUAL_ENTRY (?,?,?,?)}");

           
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
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
            try {
                connection.close();
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
            }
        }

        return status;

    }

    public List<Integer> getCheckedRecords(Object[] input) {

        List<Integer> projectionDetailsIdList = new ArrayList<Integer>();

        try {
            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
            projectionDetailsIdList = salesProjectionDAO.getSalesProjection(input);
        } catch (PortalException ex) {
            Logger.getLogger(SalesProjectionLogic.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SalesProjectionLogic.class.getName()).log(Level.SEVERE, null, ex);
        }

        return projectionDetailsIdList;
    }

    public void updateByHierarchyNo(Object[] input) {

        try {
            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
            salesProjectionDAO.getSalesProjection(input);
        } catch (PortalException ex) {
            Logger.getLogger(SalesProjectionLogic.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SalesProjectionLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void uncheckByDetailsId(Object[] input) {
        try {
            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
            salesProjectionDAO.getSalesProjection(input);
        } catch (PortalException ex) {
            Logger.getLogger(SalesProjectionLogic.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SalesProjectionLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateByDetailsId(Object[] input) {
        try {
            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
            salesProjectionDAO.getSalesProjection(input);
        } catch (PortalException ex) {
            Logger.getLogger(SalesProjectionLogic.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SalesProjectionLogic.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public BigDecimal getTotalLives(SessionDTO session) {
        Double lastValue = 0.0;
        int year = session.getCurrentDate().getYear() + 1900;
        int quator = getQuator(session.getCurrentDate().getMonth() + 1);

        Map<Integer, Double> values = new HashMap<Integer, Double>();
        BigDecimal totalValue = new BigDecimal(0.0);

        Map<String, Map<Integer, Double>> finalMap = new HashMap<String, Map<Integer, Double>>();
        SalesProjectionLogic logic = new SalesProjectionLogic();
        Object[] inputs = new Object[3];
        inputs[0] = session.getProjectionId();
        inputs[1] = STRING_EMPTY;
        inputs[2] = session;

        finalMap = logic.getLivesForSelectedCustomers(inputs);

        int endPeriod = Integer.parseInt(session.getForecastDTO().getForecastEndYear() + StringUtils.EMPTY + logic.getQuator(session.getForecastDTO().getForecastEndMonth()));
        int tempNum = 0;
        int startYear = session.getForecastDTO().getHistoryStartYear();
        int startQuator = logic.getQuator(session.getForecastDTO().getHistoryStartMonth());
        List<Integer> allKeyList = new ArrayList<Integer>();
        while (tempNum != endPeriod) {
            tempNum = Integer.parseInt(startYear + StringUtils.EMPTY + startQuator);
            allKeyList.add(tempNum);
            if (startQuator == 4) {
                startQuator = 1;
                startYear = startYear + 1;
            } else {
                startQuator = startQuator + 1;
            }
        }

        for (String companyKey : finalMap.keySet()) {
            values = finalMap.get(companyKey);
            lastValue = values.get(Integer.parseInt(year + StringUtils.EMPTY + quator));
            totalValue = totalValue.add(BigDecimal.valueOf(lastValue));
        }

        return totalValue;

    }

    public Map<String, Boolean> getCheckRecordDetails(Object[] inputs) {
        Map<String, Boolean> checkDetails = new HashMap<String, Boolean>();
        List list = null;
        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        try {
            list = salesProjectionDAO.getSalesProjection(inputs);
        } catch (PortalException ex) {
            Logger.getLogger(SalesProjectionLogic.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(SalesProjectionLogic.class.getName()).log(Level.SEVERE, null, ex);
        }

        for (int i = 0; i < list.size(); i++) {
            Object obj[] = (Object[]) list.get(i);

            if (obj[0] != null && obj[1] != null) {
                int value = Integer.parseInt(String.valueOf(obj[1]));
                if (value == 0) {
                    checkDetails.put(String.valueOf(obj[0]), new Boolean(false));
                } else {
                    checkDetails.put(String.valueOf(obj[0]), new Boolean(true));
                }
            }
        }
        return checkDetails;
    }

    public boolean isNoActuals(Object[] selection) {
        boolean hasNoActuals = false;
        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        try {
            List list = salesProjectionDAO.getSalesProjection(selection);
            double count = Double.valueOf(list.get(0).toString());
            if (count <= 0) {
                hasNoActuals = true;
            }
        } catch (Exception ex) {
            Logger.getLogger(SalesProjectionLogic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hasNoActuals;
    }

}
