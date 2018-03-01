/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.logic;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionList;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gtnforecasting.dao.SalesProjectionDAO;
import com.stpl.app.gtnforecasting.dao.impl.SalesProjectionDAOImpl;
import com.stpl.app.gtnforecasting.dto.LivesDTO;
import com.stpl.app.gtnforecasting.dto.PMPYRowDto;
import com.stpl.app.gtnforecasting.dto.PeriodDTO;
import com.stpl.app.gtnforecasting.dto.SalesProjectionDTO;
import com.stpl.app.gtnforecasting.dto.SalesRowDto;
import com.stpl.app.gtnforecasting.queryUtils.PPAQuerys;
import com.stpl.app.gtnforecasting.service.finderImpl.MasterDataAttributeFinderImpl;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import static com.stpl.app.gtnforecasting.utils.Constant.STRING_EMPTY;
import com.stpl.app.service.CcpDetailsLocalServiceUtil;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.service.ContractMasterLocalServiceUtil;
import com.stpl.app.service.ProjectionDetailsLocalServiceUtil;
import com.stpl.app.service.ProjectionMasterLocalServiceUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.ui.util.converters.DataTypeConverter;
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

import javax.naming.NamingException;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static final Logger LOGGER = LoggerFactory.getLogger(SalesProjectionLogic.class);

    /**
     * Generate SALES_SMALL result.
     *
     * @param selections the selections
     * @return the list
     */
    public List<SalesProjectionDTO> generateSalesResult() {
        try {
            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
            DynamicQuery query = null;

            salesProjectionDAO.getSalesProjectionResult(query);

        } catch (PortalException | SystemException ex) {
            LoggerFactory.getLogger(SalesProjectionLogic.class.getName()).error( StringUtils.EMPTY, ex);
        }

        return Collections.emptyList();
    }

    /**
     * Adjust SALES_SMALL projection.
     *
     * @param selections the selections
     * @return the list
     */

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

            LOGGER.debug("Entering callAdjustmentProcedure  ::::");
            if (connection != null) {
                statement = connection.prepareCall("{call PRC_SALES_MANUAL_ADJUSTMENT (?,?,?,?,?)}");
                statement.setObject(1, inputs[0]);  
                statement.setObject(NumericConstants.TWO, inputs[1]); 
                statement.setObject(NumericConstants.THREE, inputs[NumericConstants.TWO]); 
                statement.setObject(NumericConstants.FOUR, DataTypeConverter.convertObjectToInt(inputs[NumericConstants.THREE]));
                statement.setObject(NumericConstants.FIVE, DataTypeConverter.convertObjectToInt(inputs[NumericConstants.FOUR]));

                status = statement.execute();
            }

            LOGGER.debug("Ending callAdjustmentProcedure return  staus ::::= {}" , status);
        } catch (NumberFormatException | SQLException | NamingException ex) {
            LOGGER.error("Error from callAdjustmentProcedure: ", ex.getMessage());
            throw new SystemException(ex);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
            try {
                connection.close();
            } catch (SQLException e) {
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
            LoggerFactory.getLogger(SalesProjectionLogic.class.getName()).error( StringUtils.EMPTY, ex);
        }
        return Collections.emptyList();

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

            LOGGER.debug("Entering callCalculationProcedure  ::::");
            if (connection != null) {

                statement = connection.prepareCall("{call PRC_NM_SALES_PROJECTION (?,?,?)}");
                statement.setObject(1, inputs[0]);
                statement.setObject(NumericConstants.TWO, DataTypeConverter.convertObjectToInt(inputs[NumericConstants.NINE]));
                statement.setObject(NumericConstants.THREE, DataTypeConverter.convertObjectToInt(inputs[NumericConstants.TEN]));
                status = statement.execute();
            }

            LOGGER.debug("Ending callCalculationProcedure return  staus ::::= {}" , status);
        } catch (NumberFormatException | SQLException | NamingException ex) {
            LOGGER.error("Error from callCalculationProcedure: ", ex.getMessage());
            throw new SystemException(ex);

        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
               LOGGER.error(e.getMessage());
            }
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }

        return status;

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
        } catch (PortalException | SystemException ex) {
            LoggerFactory.getLogger(SalesProjectionLogic.class.getName()).error( StringUtils.EMPTY, ex);
        }

        return Collections.emptyList();

    }


    public int LoadHistoryValues(int projectionId) {
        int totalQuators = NumericConstants.FOUR;
        DynamicQuery dynamicquerryFrom = ProjectionMasterLocalServiceUtil.dynamicQuery();
        ProjectionList projectionListFrom = ProjectionFactoryUtil.projectionList();

        dynamicquerryFrom.add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_MASTER_SID, projectionId));

        projectionListFrom.add(ProjectionFactoryUtil.property("fromDate"));
        dynamicquerryFrom.setProjection(ProjectionFactoryUtil.distinct(projectionListFrom));
        try {
            List list = ProjectionMasterLocalServiceUtil.dynamicQuery(dynamicquerryFrom);
            String tempDate;
            tempDate = String.valueOf(list.get(0));

            Date currentDate = new Date();
            tempDate = tempDate.substring(0, NumericConstants.TEN);
            String temparry[] = tempDate.split("-");
            int fromYear = Integer.parseInt(String.valueOf(temparry[0]));
            int fromQuator = getQuator(Integer.parseInt(String.valueOf(temparry[1])));
            int toYear = currentDate.getYear() + NumericConstants.ONE_NINE_ZERO_ZERO;
            int toQuator = getQuator(currentDate.getMonth() + 1);

            if (getQuator(currentDate.getMonth()) == 0) {
                toYear = toYear - 1;
                toQuator = NumericConstants.FOUR;

            }
            totalQuators = getTotalHistoryPeriods(fromYear, toYear, fromQuator, toQuator);

        } catch (SystemException ex) {
            LOGGER.error(ex.getMessage());
        }

        return totalQuators;

    }

    public String loadTotalLives(Object[] inputs) {
        Double lives = 0.0;
        List<String> list;
        list = new MasterDataAttributeFinderImpl().getTotalLives(inputs);
        if (list != null) {
            for (String live : list) {
                lives = lives + Double.parseDouble(live);
            }
        }
        return String.valueOf(lives);
    }

    public List<PeriodDTO> loadAlternateHistory(final Object[] inputs) throws SystemException, SQLException {

        List<PeriodDTO> list = new ArrayList<>();
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

            LOGGER.debug("Entering callAlternateHistoryProcedure  ::::");

            if (connection != null) {

                statement = connection.prepareCall("{call PRC_NM_ALTERNATE_ACTUALS (?,?,?,?,?,?,?)}");

                LOGGER.debug("CONT_HierarchyNo= {}" , inputs[0]);
                LOGGER.debug("BRAND_RELATIONSHIP_LEVEL_SID= {}" , inputs[1]);
                LOGGER.debug("ALTER_CONTRACT_HOLDER_SID= {}" , inputs[NumericConstants.TWO]);
                LOGGER.debug("ALTER_BRAND_MASTER_SID= {}" , inputs[NumericConstants.THREE]);
                LOGGER.debug("PROJECTION_MASTER_SID= {}" , inputs[NumericConstants.FOUR]);
                LOGGER.debug("SESSION_ID= {}" , inputs[NumericConstants.FIVE]);
                LOGGER.debug("USER_ID= {}" , inputs[NumericConstants.SIX]);
                statement.setObject(1, inputs[0]);
                statement.setObject(NumericConstants.TWO, inputs[1]);
                statement.setObject(NumericConstants.THREE, inputs[NumericConstants.TWO]);
                statement.setObject(NumericConstants.FOUR, inputs[NumericConstants.THREE]);
                statement.setObject(NumericConstants.FIVE, inputs[NumericConstants.FOUR]);
                statement.setObject(NumericConstants.SIX, DataTypeConverter.convertObjectToInt(inputs[NumericConstants.FIVE]));
                statement.setObject(NumericConstants.SEVEN, DataTypeConverter.convertObjectToInt(inputs[NumericConstants.SIX]));

                status = statement.execute();
            }

            LOGGER.debug("Ending callAlternateHistoryProcedure return  staus ::::= {}" , status);
        } catch (NumberFormatException | SQLException | NamingException ex) {
            LOGGER.error("Error from callAlternateHistoryProcedure: ", ex.getMessage());
            throw new SystemException(ex);
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (SQLException e) {
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

            LOGGER.debug("Entering callSalesInsertProcedure  ::::");
            if (connection != null) {
                statement = connection.prepareCall("{call PRC_NM_SALES_INSERT (?,?,?)}");
                statement.setObject(1, inputs[0]);
                statement.setObject(NumericConstants.TWO, DataTypeConverter.convertObjectToInt(inputs[1]));
                statement.setObject(NumericConstants.THREE, DataTypeConverter.convertObjectToInt(inputs[NumericConstants.TWO]));

                status = statement.execute();
            }

            LOGGER.debug("Ending callSalesInsertProcedure return  staus ::::= {}" , status);
        } catch (NumberFormatException | SQLException | NamingException ex) {
            LOGGER.error("Error from callAlternateHistoryProcedure: ", ex.getMessage());
            throw new SystemException(ex);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }

        return status;
    }

    public Map<String, Map<Integer, Double>> getLivesForSelectedCustomers(Object inputs[]) {

        List list;
        SessionDTO session = (SessionDTO) inputs[NumericConstants.TWO];
        Map<String, Map<Integer, Double>> finalMap = new HashMap<>();
        List<LivesDTO> livesDto = new ArrayList<>();
        list = new MasterDataAttributeFinderImpl().getTotalLives(inputs);
        LivesDTO lives = null;
        Date startDate = null;
        Date endDate = null;
        session.getForecastDTO().getForecastEndDate();
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                Object[] obj = (Object[]) list.get(i);

                lives = new LivesDTO();

                if (!String.valueOf(obj[1]).equals(Constant.NULL) && !(String.valueOf(obj[1]).trim().isEmpty())) {
                    startDate = new Date(Date.parse((String) obj[1]));

                } else {
                    startDate = session.getForecastDTO().getHistoryStartDate();

                }
                if (!String.valueOf(obj[NumericConstants.TWO]).equals(Constant.NULL) && !(String.valueOf(obj[NumericConstants.TWO]).trim().isEmpty())) {
                    endDate = new Date(Date.parse((String) obj[NumericConstants.TWO]));
                } else {
                    endDate = session.getForecastDTO().getForecastEndDate();
                }

                lives.setEndYear(endDate.getYear());
                lives.setEndQuator(getQuator(endDate.getMonth() + 1));
                lives.setStartQuator(getQuator(startDate.getMonth() + 1));
                lives.setStartYear(startDate.getYear());
                lives.setStartDate(startDate);
                lives.setEndDate(endDate);
                lives.setLives(Double.parseDouble(String.valueOf(obj[NumericConstants.THREE])));
                lives.setComp_Name((String) obj[0]);
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
                    livesList = new ArrayList<>();

                }

                if (tempcompName.equals(companyDto.getComp_Name())) {
                    livesList.add(companyDto);

                } else {
                    tempcompName = companyDto.getComp_Name();
                    companyList.add(livesList);
                    livesList = new ArrayList<>();
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
                values = new HashMap<>();

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
                        String inputString = (dto.getStartYear() + NumericConstants.ONE_NINE_ZERO_ZERO) + StringUtils.EMPTY + dto.getStartQuator();
                        values.put(DataTypeConverter.convertStringToInteger(inputString), dto.getLives());

                        if (dto.getStartYear() == dto.getEndYear() && dto.getStartQuator() == dto.getEndQuator()) {

                            tempBool = false;
                        }
                        if ((dto.getStartQuator() % NumericConstants.FOUR) == 0) {
                            dto.setStartQuator(1);
                            dto.setStartYear(dto.getStartYear() + 1);
                        } else {
                            dto.setStartQuator(dto.getStartQuator() + 1);
                        }
                        if (ia > NumericConstants.TWO_HUNDRED) {
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
        if (month < NumericConstants.FOUR) {
            return 1;
        }
        if (month < NumericConstants.SEVEN) {
            return NumericConstants.TWO;
        }
        if (month < NumericConstants.TEN) {
            return NumericConstants.THREE;
        }
        return NumericConstants.FOUR;
    }

    public boolean saveAdjustmentSelections(Object[] selections) {
        boolean status = false;

        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        try {
            salesProjectionDAO.getSalesProjection(selections);
        } catch (PortalException | SystemException ex) {
            LoggerFactory.getLogger(SalesProjectionLogic.class.getName()).error( StringUtils.EMPTY, ex);
        }

        return status;
    }

    public boolean saveSalesProjections(Object[] inputs) {
        boolean status = false;
        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();

        try {
            salesProjectionDAO.getSalesProjection(inputs);
        } catch (PortalException | SystemException ex) {

            LoggerFactory.getLogger(SalesProjectionLogic.class.getName()).error( StringUtils.EMPTY, ex);
        }
        return status;
    }

    public List<SalesRowDto> loadSalesProjection() {
        List<SalesRowDto> resultList = new ArrayList<>();
     
        return resultList;
    }

    public boolean checkAll() {
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
            if (!list.isEmpty()) {
                count = Integer.parseInt(String.valueOf(list.get(0)));
            }
        } catch (PortalException | SystemException | NumberFormatException ex) {
            LoggerFactory.getLogger(SalesProjectionLogic.class.getName()).error( StringUtils.EMPTY, ex);
        }

        return count;
    }

    public boolean saveCalculationSelections(Object[] inputs) {

        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        try {
            salesProjectionDAO.getSalesProjection(inputs);
        } catch (PortalException | SystemException ex) {
            LoggerFactory.getLogger(SalesProjectionLogic.class.getName()).error( StringUtils.EMPTY, ex);
        }

        return true;
    }

    private List callPMPYProcedure(Object inputs[]) throws SQLException {

        List list = null;

        final DataSourceConnection dataSourceConnection = DataSourceConnection.getInstance();
        try (Connection connection = dataSourceConnection.getConnection();
                CallableStatement statement = connection.prepareCall("{call Nm_sales_pmpy (?,?,?,?)}")
                 )
        {
                LOGGER.debug("Starting callSalesInsertProcedure return  staus ::::");
                statement.setInt(1, Integer.parseInt((String) inputs[0]));  //PROJECTION_MASTER_SID
                statement.setString(NumericConstants.TWO, String.valueOf(inputs[1]));   //@PROJECTION_DETAILS_SID
                statement.setInt(NumericConstants.THREE, Integer.parseInt((String) inputs[NumericConstants.TWO])); //CONTRACT_HOLDER_SID
                statement.setInt(NumericConstants.FOUR, Integer.parseInt((String) inputs[NumericConstants.THREE]));   //TRADING_PARTNER_SID
                list = calculateResultSet(statement);
            LOGGER.debug("Ending callSalesInsertProcedure return  staus ::::");
        } catch (NumberFormatException | SQLException | NamingException ex) {
                 LOGGER.error("Error from callPMPYProcedure: ", ex.getMessage());

        } 
        return list;
    }

    private List calculateResultSet(final CallableStatement statement) throws SQLException {
        Object[] temp;
         List list = new ArrayList();
        try (ResultSet resList = statement.executeQuery())
        {
        while (resList.next()) {
            temp = new Object[NumericConstants.FIVE];
            temp[0] = resList.getString(1);
            temp[1] = resList.getString(NumericConstants.TWO);
            temp[NumericConstants.TWO] = resList.getString(NumericConstants.THREE);
            temp[NumericConstants.THREE] = resList.getString(NumericConstants.FOUR);
            temp[NumericConstants.FOUR] = resList.getString(NumericConstants.FIVE);
            list.add(temp);
        }
        } catch (SQLException ex)
        {
            LOGGER.debug(ex.getMessage());
        }
        return list;
    }

    public List<PMPYRowDto> getPMPYResultList(Object[] inputs) {

        List<PMPYRowDto> resultList = new ArrayList<>();

        List list = null;
        try {
            list = callPMPYProcedure(inputs);
        }  catch (SQLException ex) {

            LoggerFactory.getLogger(SalesProjectionLogic.class.getName()).error( StringUtils.EMPTY, ex);
        } 

        List<Object> propertyList = (List<Object>) inputs[NumericConstants.FOUR];

        if (list != null) {
            List<PeriodDTO> tempList = new ArrayList<>();
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
                if (obj[NumericConstants.TWO] != null) {
                    periodDTO.setActualSales(String.valueOf(CommonUtils.MONEY.format(Double.valueOf(obj[NumericConstants.TWO].toString()))));

                    
                }

                if (obj[NumericConstants.THREE] != null) {
                   
                    periodDTO.setActualUnits(String.valueOf(CommonUtils.UNITVOLUME.format(Double.valueOf(obj[NumericConstants.THREE].toString()))));
                    
                }
                if (obj[NumericConstants.FOUR] != null) {
                    
                    periodDTO.setLives(Double.valueOf(CommonUtils.UNITVOLUME.format(Double.valueOf(obj[NumericConstants.FOUR].toString()))));
                   
                }

                tempList.add(periodDTO);

            }

            PMPYRowDto pmpyRowDto1 = new PMPYRowDto();
            PMPYRowDto pmpyRowDto2 = new PMPYRowDto();
            PMPYRowDto pmpyRowDto3 = new PMPYRowDto();

            Map<String, String> properties1 = new HashMap<>();
            Map<String, String> properties2 = new HashMap<>();
            Map<String, String> properties3 = new HashMap<>();
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
            fromQuator = NumericConstants.FIVE - fromQuator;
            numberofQuators = fromQuator + toQuator;

        } else if (toYear - fromYear > 1) {

            numberofQuators = toQuator + fromQuator + (NumericConstants.FOUR * (toYear - fromYear));

        }

        return numberofQuators;
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

        List<SalesRowDto> levelList = new ArrayList<>();
        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        List list = null;
        try {
            list = salesProjectionDAO.getSalesProjection(input);
        } catch (PortalException | SystemException ex) {
            LoggerFactory.getLogger(SalesProjectionLogic.class.getName()).error( StringUtils.EMPTY, ex);
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
            DynamicQuery dynamicQuery = CcpDetailsLocalServiceUtil.dynamicQuery();

            final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();

            productProjectionList.add(ProjectionFactoryUtil.property(Constant.ITEM_MASTER_SID));
            dynamicQuery.add(PropertyFactoryUtil.forName(Constant.CCP_DETAILS_SID).in(
                    ProjectionDetailsLocalServiceUtil.dynamicQuery()
                    .add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_MASTER_SID, prMasterSId)).setProjection(ProjectionFactoryUtil.property(Constant.CCP_DETAILS_SID))));
            dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
            list = CcpDetailsLocalServiceUtil.dynamicQuery(dynamicQuery);
            if (!list.isEmpty()) {

                if (list.size() > 1) {

                    return -1;
                }
                projectionDettailsId = Integer.parseInt(list.get(0).toString());
            }
        } catch (SystemException | NumberFormatException ex) {
            LoggerFactory.getLogger(SalesProjectionLogic.class.getName()).error( StringUtils.EMPTY, ex);
        }

        return projectionDettailsId;
    }

    public List getTradingPartnerInfo(int prDetId) {
        List list = new ArrayList();
        List<String> resultList = new ArrayList<>();
        String tradingName = StringUtils.EMPTY;
        String tradingNo = StringUtils.EMPTY;
        String contractHolder = StringUtils.EMPTY;

        DynamicQuery dynamicQuery = CcpDetailsLocalServiceUtil.dynamicQuery();

        final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();

        productProjectionList.add(ProjectionFactoryUtil.property(Constant.COMPANYMASTERSID));
        productProjectionList.add(ProjectionFactoryUtil.property(Constant.CONTRACT_MASTER_SID));

        dynamicQuery.add(PropertyFactoryUtil.forName(Constant.CCP_DETAILS_SID).in(
                ProjectionDetailsLocalServiceUtil.dynamicQuery()
                .add(RestrictionsFactoryUtil.eq(Constant.PROJECTION_DETAILS_SID, prDetId))
                .setProjection(ProjectionFactoryUtil.property(Constant.CCP_DETAILS_SID))));

      
        dynamicQuery.setProjection(ProjectionFactoryUtil.distinct(productProjectionList));
        try {
            list = CcpDetailsLocalServiceUtil.dynamicQuery(dynamicQuery);
        } catch (SystemException ex) {
            LoggerFactory.getLogger(SalesProjectionLogic.class.getName()).error( StringUtils.EMPTY, ex);
        }
        if (!list.isEmpty()) {
            Object[] obj = (Object[]) list.get(0);
            String.valueOf(obj[0]);
            int companyMasterSid = Integer.parseInt(String.valueOf(obj[0]));

            int contractMasterSid = Integer.parseInt(String.valueOf(obj[1]));
            list = new ArrayList();
            DynamicQuery dynamicQuery1 = CompanyMasterLocalServiceUtil.dynamicQuery();

            final ProjectionList productProjectionList1 = ProjectionFactoryUtil.projectionList();

            productProjectionList1.add(ProjectionFactoryUtil.property(Constant.COMPANY_NAME));
            productProjectionList1.add(ProjectionFactoryUtil.property("companyNo"));
            dynamicQuery1.add(RestrictionsFactoryUtil.eq(Constant.COMPANYMASTERSID, companyMasterSid));
            dynamicQuery1.setProjection(ProjectionFactoryUtil.distinct(productProjectionList1));
            try {
                list = CompanyMasterLocalServiceUtil.dynamicQuery(dynamicQuery1);
            } catch (SystemException ex) {
                LoggerFactory.getLogger(SalesProjectionLogic.class.getName()).error( StringUtils.EMPTY, ex);
            }
            if (!list.isEmpty()) {

                Object[] obj1 = (Object[]) list.get(0);
                tradingName = String.valueOf(obj1[0]);
                tradingNo = String.valueOf(obj1[1]);
                list = new ArrayList();
                DynamicQuery dynamicQuery2 = CompanyMasterLocalServiceUtil.dynamicQuery();

                final ProjectionList productProjectionList2 = ProjectionFactoryUtil.projectionList();

                productProjectionList2.add(ProjectionFactoryUtil.property(Constant.COMPANY_NAME));

                dynamicQuery2.add(PropertyFactoryUtil.forName(Constant.COMPANYMASTERSID).in(
                        ContractMasterLocalServiceUtil.dynamicQuery()
                        .add(RestrictionsFactoryUtil.eq(Constant.CONTRACT_MASTER_SID, contractMasterSid))
                        .setProjection(ProjectionFactoryUtil.property("contHoldCompanyMasterSid"))));

                dynamicQuery2.setProjection(ProjectionFactoryUtil.distinct(productProjectionList2));
                try {
                    list = CompanyMasterLocalServiceUtil.dynamicQuery(dynamicQuery2);
                } catch (SystemException ex) {
                    LoggerFactory.getLogger(SalesProjectionLogic.class.getName()).error( StringUtils.EMPTY, ex);
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

        try {

            List input = new ArrayList();
            input.add(inputs[NumericConstants.THREE]);
            input.add(inputs[NumericConstants.TWO]);
            input.add(session.getSessionId());
            input.add(session.getUserId());
            input.add(inputs[1]);
            input.add(CommonUtils.getListToString((List) inputs[0]));

            String changeProperty;
            if (inputs[NumericConstants.FOUR].equals(Constant.SALES_CAPS)) {
                PPAQuerys.PPAUpdate(input, "PMPY-UPDATE-SALES");
                changeProperty = Constant.SALES_CAPS;
            } else {
                PPAQuerys.PPAUpdate(input, "PMPY-UPDATE-UNITS");
                changeProperty = Constant.UNITS_SMALL;
            }
            callManualEntry(session, changeProperty);

        } catch (Exception ex) {
            LoggerFactory.getLogger(SalesProjectionLogic.class.getName()).error( StringUtils.EMPTY, ex);
        }
    }

    public int getPMPYProtDetID(Object input[]) {
        List list = new ArrayList();
        int id = 0;
        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        try {
            list = salesProjectionDAO.getSalesProjection(input);
        } catch (PortalException | SystemException ex) {
            LoggerFactory.getLogger(SalesProjectionLogic.class.getName()).error( StringUtils.EMPTY, ex);
        }
        if (!list.isEmpty()) {
            id = list.size();
           
        }
        return id;
    }

    public List getProjectionDetailsSid(Object input[]) {
        List list = new ArrayList();
        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        try {
            list = salesProjectionDAO.getSalesProjection(input);
        } catch (PortalException | SystemException ex) {
            LoggerFactory.getLogger(SalesProjectionLogic.class.getName()).error( StringUtils.EMPTY, ex);
        }

       
        return list;
    }

    public int getLevelIndex(int projectionId, String hierarchy, String hierarchyNo, String selectedHiearchyNo) {
        int count = 1;
        Object input[] = new Object[NumericConstants.TEN];
        input[NumericConstants.EIGHT] = "getLevelIndex";
        input[0] = projectionId;
        input[1] = hierarchy;
        input[NumericConstants.TWO] = hierarchyNo;
        input[NumericConstants.THREE] = selectedHiearchyNo;

        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        try {
            List list = salesProjectionDAO.getSalesProjection(input);
            if (!list.isEmpty()) {
                count = Integer.parseInt(list.get(0).toString());
            }
        } catch (PortalException | SystemException | NumberFormatException ex) {
            LoggerFactory.getLogger(SalesProjectionLogic.class.getName()).error( StringUtils.EMPTY, ex);
        }
        return count;
    }

    public int getCount(Object[] input) {
        int count = 1;

        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        try {
            List list = salesProjectionDAO.getSalesProjection(input);
            count = Integer.parseInt(list.get(0).toString());
        } catch (PortalException | SystemException | NumberFormatException ex) {
            LoggerFactory.getLogger(SalesProjectionLogic.class.getName()).error( StringUtils.EMPTY, ex);
        }

        return count;
    }

    public List<String> loadGroup(Object[] input) {
        List<String> groupList = new ArrayList<>();
        try {
            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
            groupList = salesProjectionDAO.getSalesProjection(input);
        } catch (PortalException | SystemException ex) {
            LoggerFactory.getLogger(SalesProjectionLogic.class.getName()).error( StringUtils.EMPTY, ex);
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

            LOGGER.debug("Entering callManualEntryProcedure  ::::");

            if (connection != null) {

                statement = connection.prepareCall("{call PRC_SALES_PROJ_MANUAL_ENTRY (?,?,?,?)}");

           
                statement.setObject(1, session.getProjectionId()); //  @PROJECTION_SID
                statement.setObject(NumericConstants.TWO, DataTypeConverter.convertStringToInteger(session.getUserId())); //  @USER_ID
                statement.setObject(NumericConstants.THREE, DataTypeConverter.convertStringToInteger(session.getSessionId())); //  @SESSION_ID
                statement.setObject(NumericConstants.FOUR, changedProperty);
                status = statement.execute();
            }

            LOGGER.debug("Ending callManualEntryProcedure return  staus ::::= {}" , status);
        } catch (NumberFormatException | SQLException | NamingException ex) {
            LOGGER.error(ex.getMessage());

        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
            try {
                connection.close();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
            }
        }

        return status;

    }

    public List<Integer> getCheckedRecords(Object[] input) {

        List<Integer> projectionDetailsIdList = new ArrayList<>();

        try {
            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
            projectionDetailsIdList = salesProjectionDAO.getSalesProjection(input);
        } catch (PortalException | SystemException ex) {
            LoggerFactory.getLogger(SalesProjectionLogic.class.getName()).error( StringUtils.EMPTY, ex);
        }

        return projectionDetailsIdList;
    }

    public void updateByHierarchyNo(Object[] input) {

        try {
            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
            salesProjectionDAO.getSalesProjection(input);
        } catch (PortalException | SystemException ex) {
            LoggerFactory.getLogger(SalesProjectionLogic.class.getName()).error( StringUtils.EMPTY, ex);
        }
    }

    public void uncheckByDetailsId(Object[] input) {
        try {
            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
            salesProjectionDAO.getSalesProjection(input);
        } catch (PortalException | SystemException ex) {
            LoggerFactory.getLogger(SalesProjectionLogic.class.getName()).error( StringUtils.EMPTY, ex);
        }
    }

    public void updateByDetailsId(Object[] input) {
        try {
            SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
            salesProjectionDAO.getSalesProjection(input);
        } catch (PortalException | SystemException ex) {
            LoggerFactory.getLogger(SalesProjectionLogic.class.getName()).error( StringUtils.EMPTY, ex);
        }

    }

    public BigDecimal getTotalLives(SessionDTO session) {
        Double lastValue;
        int year = session.getCurrentDate().getYear() + NumericConstants.ONE_NINE_ZERO_ZERO;
        int quator = getQuator(session.getCurrentDate().getMonth() + 1);

        Map<Integer, Double> values;
        BigDecimal totalValue = new BigDecimal(0.0);

        Map<String, Map<Integer, Double>> finalMap;
        SalesProjectionLogic logic = new SalesProjectionLogic();
        Object[] inputs = new Object[NumericConstants.THREE];
        inputs[0] = session.getProjectionId();
        inputs[1] = STRING_EMPTY;
        inputs[NumericConstants.TWO] = session;

        finalMap = logic.getLivesForSelectedCustomers(inputs);

        int endPeriod = Integer.parseInt(session.getForecastDTO().getForecastEndYear() + StringUtils.EMPTY + logic.getQuator(session.getForecastDTO().getForecastEndMonth()));
        int tempNum = 0;
        int startYear = session.getForecastDTO().getHistoryStartYear();
        int startQuator = logic.getQuator(session.getForecastDTO().getHistoryStartMonth());
        List<Integer> allKeyList = new ArrayList<>();
        while (tempNum != endPeriod) {
            tempNum = Integer.parseInt(startYear + StringUtils.EMPTY + startQuator);
            allKeyList.add(tempNum);
            if (startQuator == NumericConstants.FOUR) {
                startQuator = 1;
                startYear = startYear + 1;
            } else {
                startQuator = startQuator + 1;
            }
        }

        for (String companyKey : finalMap.keySet()) {
            values = finalMap.get(companyKey);
            lastValue = values.get(DataTypeConverter.convertStringToInteger(year + StringUtils.EMPTY + quator));
            totalValue = totalValue.add(BigDecimal.valueOf(lastValue));
        }

        return totalValue;

    }

    public Map<String, Boolean> getCheckRecordDetails(Object[] inputs) {
        Map<String, Boolean> checkDetails = new HashMap<>();
        List list = null;
        SalesProjectionDAO salesProjectionDAO = new SalesProjectionDAOImpl();
        try {
            list = salesProjectionDAO.getSalesProjection(inputs);
        } catch (PortalException | SystemException ex) {
            LoggerFactory.getLogger(SalesProjectionLogic.class.getName()).error( StringUtils.EMPTY, ex);
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
            double count = Double.parseDouble(list.get(0).toString());
            if (count <= 0) {
                hasNoActuals = true;
            }
        } catch (PortalException | SystemException | NumberFormatException ex) {
            LoggerFactory.getLogger(SalesProjectionLogic.class.getName()).error( StringUtils.EMPTY, ex);
        }
        return hasNoActuals;
    }
    
}
