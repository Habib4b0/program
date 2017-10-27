/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.logic;

import com.stpl.app.accountclose.common.CommonLogic;
import com.stpl.app.accountclose.common.CommonQuery;
import com.stpl.app.accountclose.common.CommonUtil;
import com.stpl.app.accountclose.dao.BaseRateDAO;
import com.stpl.app.accountclose.dao.impl.BaseRateDAOImpl;
import com.stpl.app.accountclose.dto.BaseRateDTO;
import com.stpl.app.accountclose.dto.GroupDTO;
import com.stpl.app.accountclose.dto.BaseRateSummaryDTO;
import com.stpl.app.accountclose.dto.TreeDTO;
import com.stpl.app.accountclose.dto.WorkflowMasterDTO;
import com.stpl.app.accountclose.gtnbalancereport.logic.DataSelectionLogic;
import static com.stpl.app.accountclose.logic.BaseRateCalculationLogic.LOGGER;
import com.stpl.app.accountclose.sessionutils.SessionDTO;
import com.stpl.app.accountclose.utils.BaseRateUtil;
import com.stpl.app.accountclose.utils.CommonUtils;
import com.stpl.app.accountclose.utils.Constants;
import static com.stpl.app.accountclose.utils.Constants.FrequencyConstants.ANNUALLY;
import static com.stpl.app.accountclose.utils.Constants.FrequencyConstants.MONTHLY;
import static com.stpl.app.accountclose.utils.Constants.FrequencyConstants.QUARTERLY;
import static com.stpl.app.accountclose.utils.Constants.FrequencyConstants.SEMI_ANNUALLY;
import static com.stpl.app.accountclose.utils.Constants.IndicatorConstants.AVERAGE;
import static com.stpl.app.accountclose.utils.Constants.IndicatorConstants.EXPONENTIAL_SMOOTHING;
import static com.stpl.app.accountclose.utils.Constants.IndicatorConstants.GROWTH;
import static com.stpl.app.accountclose.utils.Constants.IndicatorConstants.MOVING_AVERAGE;
import static com.stpl.app.accountclose.utils.Constants.IndicatorConstants.TIME_WEIGHTED_AVERAGE;
import static com.stpl.app.accountclose.utils.Constants.IndicatorConstants.WEIGHTED_AVERAGE;
import com.stpl.app.accountclose.utils.Converters;
import static com.stpl.app.accountclose.utils.HeaderUtils.getMonthForInt;
import com.stpl.app.model.CompanyGroupDetails;
import com.stpl.app.model.CompanyMaster;
import com.stpl.app.model.ForecastConfig;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.ItemGroupDetails;
import com.stpl.app.model.ItemMaster;
import com.stpl.app.model.WorkflowMaster;
import com.stpl.app.parttwo.model.AcBaseRateBaselineDetails;
import com.stpl.app.parttwo.model.AcBrMethodologyDetails;
import com.stpl.app.parttwo.model.AccClosureDetails;
import com.stpl.app.parttwo.model.AccClosureMaster;
import com.stpl.app.parttwo.model.StAccClosureDetails;
import com.stpl.app.parttwo.service.AcBaseRateBaselineDetailsLocalServiceUtil;
import com.stpl.app.parttwo.service.AcBrMethodologyDetailsLocalServiceUtil;
import com.stpl.app.parttwo.service.AccClosureDetailsLocalServiceUtil;
import com.stpl.app.parttwo.service.AccClosureMasterLocalServiceUtil;
import com.stpl.app.parttwo.service.StAccClosureDetailsLocalServiceUtil;
import com.stpl.app.service.CompanyGroupDetailsLocalServiceUtil;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.service.ItemGroupDetailsLocalServiceUtil;
import com.stpl.app.service.ItemMasterLocalServiceUtil;
import com.stpl.app.service.WorkflowMasterLocalServiceUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionList;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.vaadin.server.VaadinSession;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author alok.v
 */
public class BaseRateCalculationLogic {
    
    private DataQueryLogic dqLogic = new DataQueryLogic();
    private CommonUtils utils = new CommonUtils();
    private CommonQuery comquery = new CommonQuery();
    private BaseRateDAO dao = new BaseRateDAOImpl();
    public static final SimpleDateFormat DBDATE = new SimpleDateFormat("yyyy-MM-dd");
    public static final Logger LOGGER = Logger.getLogger(BaseRateCalculationLogic.class);
    CommonLogic logic = new CommonLogic();
    DataSelectionLogic dsLogic = new DataSelectionLogic();
    String userId = String.valueOf(VaadinSession.getCurrent().getAttribute(Constants.USER_ID));
    BaseRateUtil brUtil = new BaseRateUtil();
    /**
     * The Percent Two Decimal Places Format.
     */
    private static final DecimalFormat PERCENTAGE_FORMAT = new DecimalFormat("###0.00%");
    final NumberFormat GTS = NumberFormat.getCurrencyInstance();
    String symbol = StringUtils.EMPTY;
    /**
     * The Constant RATE.
     */

    /**
     *
     * @param query
     * @return
     * @throws Exception
     */
    public List<String> executeQuery(final String query) throws Exception {
        LOGGER.info("Entered executeQuery " + query);
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("query", query);
        
        List resultList = dqLogic.executeQuery(parameters);
        List<String> returnList = new ArrayList<String>();
        if (returnList.isEmpty()) {
            for (Object value : resultList) {
                returnList.add(String.valueOf(value));
            }
        }
        LOGGER.info("Ended executeQuery " + query);
        return returnList;
    }

    /**
     * Get Customer Group Logic
     *
     * @param customerName
     * @param customerNo
     * @param companySids
     * @return
     * @throws Exception
     */
    public List<GroupDTO> getCustomerGroup(String customerName, String customerNo, List<String> companySids) throws Exception {
        LOGGER.info("Entered getCustomerGroup");
        Map<String, Object> parameters = new HashMap<String, Object>();
        customerName = customerName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
        customerNo = customerNo.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
        parameters.put("customerNo", customerNo);
        parameters.put("customerName", customerName);
        parameters.put("companySids", companySids);
        parameters.put("indicator", "CustomerGroup");
        List resultList = dqLogic.getCustomerProductGroup(parameters);
        LOGGER.info("Ended getCustomerGroup");
        return Converters.convertCustomerGroupList(resultList);
    }

    /**
     * Get Company Group Details Logic
     *
     * @param companyGroupSid
     * @return
     * @throws Exception
     */
    public List<String> getCustomerGroupDetails(int companyGroupSid) throws Exception {
        LOGGER.info("Entered getCustomerGroupDetails");
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyGroupDetails.class);
        
        dynamicQuery.add(RestrictionsFactoryUtil.eq("companyGroupSid", companyGroupSid));
        
        final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
        productProjectionList.add(ProjectionFactoryUtil.property("companyMasterSid"));
        dynamicQuery.setProjection(productProjectionList);
        List resultList = CompanyGroupDetailsLocalServiceUtil.dynamicQuery(dynamicQuery);
        
        List<String> returnList = new ArrayList<String>();
        for (Object companySid : resultList) {
            returnList.add(String.valueOf(companySid));
        }
        LOGGER.info("Ended getCustomerGroupDetails");
        return returnList;
    }

    /**
     * Get Company system id
     *
     * @param companySids
     * @return
     * @throws Exception
     */
    public List<CompanyMaster> getCompanyFromSids(final List<String> companySids) throws Exception {
        LOGGER.info("Entered getCompanyFromSids");
        List<Integer> sids = new ArrayList<Integer>();
        
        for (String sid : companySids) {
            sids.add(Integer.parseInt(sid));
        }
        
        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class);
        dynamicQuery.add(RestrictionsFactoryUtil.in("companyMasterSid", sids));
        List<CompanyMaster> companies = CompanyMasterLocalServiceUtil.dynamicQuery(dynamicQuery);
        LOGGER.info("Ended getCompanyFromSids");
        return companies;
    }

    /**
     * Gets the Product group.
     *
     * @param productName the Product group name search criteria
     * @param productNo the Product group no search criteria
     * @return the Product group result list
     * @throws java.lang.Exception
     */
    public List<GroupDTO> getProductGroup(String productName, String productNo, List<String> itemSids) throws Exception {
        LOGGER.info("Entered getProductGroup");
        Map<String, Object> parameters = new HashMap<String, Object>();
        productName = productName.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
        productNo = productNo.replace(CommonUtils.CHAR_ASTERISK, CommonUtils.CHAR_PERCENT);
        
        parameters.put("productName", productName);
        parameters.put("productNo", productNo);
        parameters.put("itemSids", itemSids);
        parameters.put("indicator", "ProductGroup");
        List<GroupDTO> resultList = dqLogic.getCustomerProductGroup(parameters);
        LOGGER.info("Ended getProductGroup");
        return Converters.convertItemGroupList(resultList);
    }

    /**
     * Gets the Item Group Details
     *
     * @param itemGroupSid
     * @return
     * @throws Exception
     */
    public List<String> getItemGroupDetails(int itemGroupSid) throws Exception {
        LOGGER.info("Entered getItemGroupDetails");
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ItemGroupDetails.class);
        dynamicQuery.add(RestrictionsFactoryUtil.eq("itemGroupSid", itemGroupSid));
        final ProjectionList productProjectionList = ProjectionFactoryUtil.projectionList();
        productProjectionList.add(ProjectionFactoryUtil.property("itemMasterSid"));
        dynamicQuery.setProjection(productProjectionList);
        List resultList = ItemGroupDetailsLocalServiceUtil.dynamicQuery(dynamicQuery);
        
        List<String> returnList = new ArrayList<String>();
        for (Object companySid : resultList) {
            returnList.add(String.valueOf(companySid));
        }
        LOGGER.info("Ended getItemGroupDetails");
        return returnList;
    }

    /**
     * Get The Item System ID
     *
     * @param itemSids
     * @return
     * @throws Exception
     */
    public List<ItemMaster> getItemsFromSids(final List<String> itemSids) throws Exception {
        LOGGER.info("Entering getItemsFromSids");
        List<Integer> sids = new ArrayList<Integer>();
        List<ItemMaster> items = null;
        for (String sid : itemSids) {
            sids.add(Integer.parseInt(sid));
        }
        if (itemSids != null && !itemSids.isEmpty()) {
            final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ItemMaster.class);
            dynamicQuery.add(RestrictionsFactoryUtil.in("itemMasterSid", sids));
            items = ItemMasterLocalServiceUtil.dynamicQuery(dynamicQuery);
        }
        LOGGER.info("Ended getItemsFromSids");
        return items;
    }

    /**
     * Load data
     *
     * @param parentId
     * @param projSelDTO
     * @return results list
     */
    public List<BaseRateSummaryDTO> getConfigureLoadData(Object parentId, BaseRateSummaryDTO projSelDTO) {
        LOGGER.info("Entering getConfigureLoadData");
        List<BaseRateSummaryDTO> salesList = new ArrayList<BaseRateSummaryDTO>();
        if (parentId instanceof BaseRateSummaryDTO) {
            BaseRateSummaryDTO dto = (BaseRateSummaryDTO) parentId;
            projSelDTO.setLevelNo(dto.getLevelNo() + 1);
        } else {
            projSelDTO.setLevelNo(1);
        }
        projSelDTO.setParentId(parentId);
        salesList = configureLevels(projSelDTO);
        LOGGER.info("Ended getConfigureLoadData");
        return salesList;
    }

    /**
     * Get sales count
     *
     * @param parentId
     * @param projSelDTO
     * @return integer
     */
    public int getConfigureCount(Object parentId, BaseRateSummaryDTO projSelDTO) {
        LOGGER.info("Entering getConfigureCount");
        List list = new ArrayList();
        try {
            if (parentId instanceof BaseRateSummaryDTO) {
                BaseRateSummaryDTO dto = (BaseRateSummaryDTO) parentId;
                projSelDTO.setLevelNo(dto.getLevelNo() + 1);
            } else {
                projSelDTO.setLevelNo(1);
            }
            String query = comquery.getSummaryQuery(projSelDTO, "brSumCount");
            list = (List<Object[]>) dao.executeSelectQuery(query);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        Object obj = list.get(0);
        LOGGER.info("Ending getConfigureCount" + (Integer) obj);
        return obj == null ? 0 : (Integer) obj;
    }

    /**
     * This method saves the selected records in temp table
     *
     * @param dto
     */
    public void saveBRDetails(TreeDTO dto) {
        LOGGER.info("Entering saveBRDetails" + dto.getComapnySid());
        try {
            String query = comquery.getLoadDataQuery(dto, "inserttempccp");
            List list = (List<Object[]>) dao.executeSelectQuery(query);
            int count = list.size();
            for (int i = 0; i < count; i++) {
                Object[] obj = (Object[]) list.get(i);
                StAccClosureDetails acDetails = StAccClosureDetailsLocalServiceUtil.createStAccClosureDetails(0);
                acDetails.setCompanyMasterSid(Integer.parseInt(String.valueOf(obj[0])));
                acDetails.setContractMasterSid(Integer.parseInt(String.valueOf(obj[1])));
                acDetails.setItemMasterSid(Integer.parseInt(String.valueOf(obj[2])));
                acDetails.setCcpDetailsSid(Integer.parseInt(String.valueOf(obj[3])));
                acDetails.setModuleName("BaseRate");
                
                acDetails.setUserId(Integer.parseInt(userId));
                acDetails.setSessionId(dto.getSessionId());
                acDetails.setLastModifiedDate(new Date());
                StAccClosureDetailsLocalServiceUtil.addStAccClosureDetails(acDetails);
            }
            
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.info("Ending saveBRDetails");
    }

    /**
     * Saves the Base Rate Master and details before generate button
     *
     * @param dto
     * @return
     */
    public BaseRateDTO saveBRMaster(TreeDTO dto, boolean callGen) {
        LOGGER.info("Entering saveBRMaster" + callGen);
        BaseRateDTO brDto = new BaseRateDTO();
        try {
            
            String query = comquery.getLoadDataQuery(dto, "insertccp");
            ForecastConfig forecastConfig = dsLogic.getTimePeriod();
            List list = (List<Object[]>) dao.executeSelectQuery(query);
            int count = list.size();
            AccClosureMaster master = AccClosureMasterLocalServiceUtil.createAccClosureMaster(0);
            AccClosureDetails details;
            master.setGlCompanyMasterSid(Integer.parseInt(dto.getGlComapnySid()));
            master.setContractType(Integer.parseInt(dto.getContractTypeSid()));
            master.setRsType(Integer.parseInt(dto.getAcctTypeSid()));
            master.setRsCategory(Integer.valueOf(dto.getAcctSubTypeSid()));
            if (!utils.getNull(dto.getCustGroupSid())) {
                master.setCompanyGroupSid(dto.getCustGroupSid());
            }
            if (!utils.getNull(dto.getItemSid()) && dto.getItemSid() != "0") {
                master.setItemMasterSid(Integer.valueOf(dto.getItemSid()));
            }
            
            if (!utils.getNull(dto.getContractSid()) && dto.getContractSid() != "0") {
                master.setContractMasterSid(Integer.valueOf(dto.getContractSid()));
            }
            
            if (!utils.getNull(dto.getPrdGroupSid())) {
                master.setItemGroupSid(dto.getPrdGroupSid());
            }
            master.setProductIdentifier(dto.getNdc());
            master.setModuleType("BaseRate");
            if (forecastConfig != null) {
                master.setFromDate(forecastConfig.getFromDate());
                master.setToDate(forecastConfig.getToDate());
            }
            master.setCreatedBy(Integer.parseInt(userId));
            master.setCreatedDate(new Date());
            master.setModifiedBy(Integer.parseInt(userId));
            master.setModifiedDate(new Date());
            master = AccClosureMasterLocalServiceUtil.addAccClosureMaster(master);
            brDto.setAcMasterSid(master.getAccClosureMasterSid());
            for (int i = 0; i < count; i++) {
                Object[] obj = (Object[]) list.get(i);
                details = AccClosureDetailsLocalServiceUtil.createAccClosureDetails(0);
                details.setAccClosureMasterSid(brDto.getAcMasterSid());
                details.setRsModelSid(Integer.parseInt(String.valueOf(obj[4])));
                details.setCcpDetailsSid(Integer.parseInt(String.valueOf(obj[3])));
                AccClosureDetailsLocalServiceUtil.addAccClosureDetails(details);
            }
            if (callGen) {
                callGenerateProcedure(brDto);
            }
            
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.info("Ended saveBRMaster" + brDto.getAcMasterSid());
        return brDto;
    }

    /**
     * The Base Rate procedure for Generate
     *
     * @param session
     * @return
     */
    public boolean callGenerateProcedure(BaseRateDTO brDto) {
        LOGGER.info("Entering callDiscountProjectionProcedure" + brDto.getAcMasterSid());
        Connection connection = null;
        DataSource datasource;
        CallableStatement statement = null;
        boolean result = false;
        try {
            Context initialContext = new InitialContext();
            datasource = (DataSource) initialContext.lookup("java:jboss/datasources/jdbc/appDataPool");
            if (datasource != null) {
                connection = datasource.getConnection();
            }
            if (connection != null) {
                statement = connection.prepareCall("{call PRC_AC_BASE_RATE_INSERT (?)}");
                
                statement.setInt(1, brDto.getAcMasterSid());
                result = statement.execute();
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (Exception e) {
                LOGGER.error(e);
            }
        }
        LOGGER.info("Ending callDiscountProjectionProcedure" + result);
        return false;
    }

    /**
     * This method sets the value for the history/projections list view
     *
     * @return
     */
    public List<BaseRateDTO> setBRValues(String fre, int sid, String from, String to) {
        LOGGER.info("Entering setBRValues");
        List<BaseRateDTO> brList = new ArrayList<BaseRateDTO>();
        BaseRateDTO dto = new BaseRateDTO();
        Map<String, String> inputMap = new HashMap<String, String>();
        String commonColumn = StringUtils.EMPTY;
        Calendar ob = Calendar.getInstance();
        int curMonth = ob.get(Calendar.MONTH);
        int curYear = ob.get(Calendar.YEAR);
        int current = 1;
        int division = 1;
        String dateQuery = StringUtils.EMPTY;
        if (fre.equals(QUARTERLY.getConstant())) {
            current = (curMonth / 3);
            division = 4;
            dateQuery = ",I.QUARTER";
        } else if (fre.equals(SEMI_ANNUALLY.getConstant())) {
            current = (curMonth / 6);
            division = 2;
            dateQuery = ",I.SEMI_ANNUAL";
        } else if (fre.equals(MONTHLY.getConstant())) {
            current = curMonth;
            division = 12;
            dateQuery = ",I.MONTH";
        } else if (fre.equals(ANNUALLY.getConstant())) {
            current = curYear;
            division = 1;
        }
        int pastYear = curYear;
        
        int startFreq = current + 1;
        int frequencyRange = utils.getFrequencyRange(fre);
        int tempFreq = frequencyRange - current;
        
        if (tempFreq > 0) {
            pastYear = pastYear - tempFreq / division;
            startFreq = 1;
            if (tempFreq % division > 0) {
                
                pastYear = pastYear - 1;
                
                startFreq = division - (tempFreq % division) + 1;
                if (!fre.equals(MONTHLY.getConstant())) {
                    startFreq = startFreq + 1;
                }
                
                if (fre.equals(QUARTERLY.getConstant())) {
                    if (startFreq < 3) {
                        startFreq = 1;
                    } else if (startFreq >= 3 && startFreq < 6) {
                        startFreq = 2;
                    } else if (startFreq >= 6 && startFreq < 9) {
                        startFreq = 3;
                    } else if (startFreq >= 9 && startFreq < 12) {
                        startFreq = 4;
                    }
                }
                
            }
        } else {
            startFreq = startFreq - frequencyRange;
        }
        
        int squr = startFreq;
        int syear = pastYear;
        if (fre.contains(ANNUALLY.getConstant()) && !fre.contains(SEMI_ANNUALLY.getConstant())) {
            syear = current - frequencyRange;
        }
        try {
            String periodQuery = brUtil.getDateRangeQuery(fre, from, to);
            inputMap.put("?FRE?", dateQuery);
            inputMap.put("?ACMID?", String.valueOf(sid));
            inputMap.put("?SD?", periodQuery);
            String query = CommonUtil.getQuery(inputMap, "brHistPrj");
            List list = (List<Object[]>) dao.executeSelectQuery(query);
            for (int j = 0; j < 12; j++) {
                dto = new BaseRateDTO();
                for (int i = 0; i < list.size(); i++) {
                    
                    Object[] obj = (Object[]) list.get(i);
                    NumberFormat FORMAT = setFormat(j);
                    if (j == 0) {
                        dto.setLevelValue("Actual GTS");
                    } else if (j == 1) {
                        dto.setLevelValue("Projected GTS");
                    } else if (j == 2) {
                        dto.setLevelValue("Actual Payments");
                    } else if (j == 3) {
                        dto.setLevelValue("Actual Rate");
                    } else if (j == 4) {
                        dto.setLevelValue("Projected Payment");
                    } else if (j == 5) {
                        dto.setLevelValue("Projected Rate");
                    } else if (j == 6) {
                        dto.setLevelValue("Accrual");
                    } else if (j == 7) {
                        dto.setLevelValue("Auto Accruals");
                    } else if (j == 8) {
                        dto.setLevelValue("Manual Adjustments");
                    } else if (j == 9) {
                        dto.setLevelValue("Payment True-Up");
                    } else if (j == 10) {
                        dto.setLevelValue("Other");
                    } else if (j == 11) {
                        dto.setLevelValue("Accrual Rate");
                    }
                    List< Object> doubleHeaderMap = new ArrayList<Object>();
                    List<Object> tripleHeaderMap = new ArrayList<Object>();
                    List<Object> actualDoubleHeaderMap = new ArrayList<Object>();
                    List<Object> prjDoubleHeaderMap = new ArrayList<Object>();
                    String actualColumn = "Actuals";
                    String projectionColumn = "Projections";
                    String commonHeader = StringUtils.EMPTY;
                    syear = Integer.parseInt(String.valueOf(obj[0]));
                    if (!utils.getNull(String.valueOf(obj[1])) && !fre.equals(ANNUALLY.getConstant())) {
                        squr = Integer.parseInt(String.valueOf(obj[1]));
                    }
                    if (fre.contains(QUARTERLY.getConstant())) {
                        commonColumn = "Q" + squr + StringUtils.EMPTY + syear;
                        commonHeader = "Q" + squr + StringUtils.EMPTY + syear + "    ";
                    } else if (fre.contains(SEMI_ANNUALLY.getConstant())) {
                        commonColumn = "S" + squr + StringUtils.EMPTY + syear;
                        commonHeader = "S" + squr + " " + syear + "    ";
                    } else if (fre.contains(ANNUALLY.getConstant())) {
                        commonColumn = StringUtils.EMPTY + syear;
                        commonHeader = StringUtils.EMPTY + syear + "    ";
                    } else if (fre.contains(MONTHLY.getConstant())) {
                        String monthName = getMonthForInt(squr - 1);
                        commonColumn = (monthName + syear);
                        commonHeader = monthName + " " + syear + "    ";
                    }
                    
                    Object singleColumn = commonColumn;
                    actualColumn = commonColumn + "Actuals";
                    projectionColumn = commonColumn + "Actuals";
                    if (!fre.equals(ANNUALLY.getConstant())) {
                        dto.addStringProperties(commonColumn, utils.getNull(String.valueOf(obj[2 + j])) ? setZeroValue(j) : getFormattedValue(FORMAT, String.valueOf(obj[2 + j])));
                    } else {
                        dto.addStringProperties(commonColumn, utils.getNull(String.valueOf(obj[1 + j])) ? setZeroValue(j) : getFormattedValue(FORMAT, String.valueOf(obj[1 + j])));
                    }

                    squr++;
                    if (squr > division) {
                        squr = 1;
                        syear++;
                    }
                    
                }
                brList.add(dto);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.info("Ended setBRValues");
        return brList;
    }

    /**
     * Get configure level
     *
     * @param projSelDTO
     * @return
     */
    public List<BaseRateSummaryDTO> configureLevels(BaseRateSummaryDTO projSelDTO) {
        LOGGER.info("Entering configureLevelSales" + projSelDTO.getLevelNo());
        List<BaseRateSummaryDTO> resultList = new ArrayList<BaseRateSummaryDTO>();
        try {
            String query = comquery.getSummaryQuery(projSelDTO, "brSum");
            List list = (List<Object[]>) dao.executeSelectQuery(query);
            Set<String> levelName = new HashSet<String>();
            BaseRateSummaryDTO dto;
            for (Object object : list) {
                final Object[] obj = (Object[]) object;
                dto = new BaseRateSummaryDTO();
                dto.setCustomerName(obj[0] == null ? StringUtils.EMPTY : obj[0].toString());
                
                dto.setParent(1);
                if (projSelDTO.getLevelNo() == 5) {
                    dto.setMethodologySid(Integer.parseInt(String.valueOf(obj[3])));
                    dto.setParent(0);
                    if (!utils.getNull(String.valueOf(obj[4]))) {
                        dto.setCurrentRate(String.valueOf(obj[4]));
                    }
                    if (!utils.getNull(String.valueOf(obj[5]))) {
                        dto.setSuggestedRate(String.valueOf(obj[5]));
                    }
                    if (!utils.getNull(String.valueOf(obj[6]))) {
                        dto.setAccrualRate(String.valueOf(obj[6]));
                    }
                    if (!utils.getNull(String.valueOf(obj[7]))) {
                        dto.setStartDate(String.valueOf(obj[7]));
                    }
                    if (!utils.getNull(String.valueOf(obj[8]))) {
                        dto.setEndDate(String.valueOf(obj[8]));
                    }
                    dto.setMethodology(String.valueOf(obj[9]));
                    if (!utils.getNull(String.valueOf(obj[10]))) {
                        dto.setReasonCode(String.valueOf(obj[10]));
                    }
                    if (!utils.getNull(String.valueOf(obj[11]))) {
                        dto.setNotes(String.valueOf(obj[11]));
                    }
                }

                dto.setLevelNo(projSelDTO.getLevelNo());

                resultList.add(dto);
            }
            
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.info("Ending configureLevelSales" + resultList.size());
        return resultList;
    }

    /**
     * The Base Rate procedure for calculate
     *
     * @param session
     * @return
     */
    public String callCalculationProcedure(BaseRateDTO brDto) {
        LOGGER.info("Entering callCalculationProcedure" + brDto.getAcMasterSid());
        Connection connection = null;
        DataSource datasource;
        CallableStatement statement = null;
        boolean result = false;
        ResultSet rs = null;
        String value = StringUtils.EMPTY;
        double total = 0;
        StringBuilder builder = new StringBuilder();
        int rateOrPayment = Integer.valueOf(brDto.getRateOrPayment());
        List<Object[]> objectList = new ArrayList<Object[]>();
        try {
            Context initialContext = new InitialContext();
            datasource = (DataSource) initialContext.lookup("java:jboss/datasources/jdbc/appDataPool");
            if (datasource != null) {
                connection = datasource.getConnection();
            }
            if (connection != null) {
                statement = connection.prepareCall("{call PRC_AC_BASE_RATE_METHODOLOGY (?,?)}");
                LOGGER.info("rateOrPayment-->" + rateOrPayment);
                statement.setInt(1, brDto.getAcMasterSid());
                statement.setInt(2, rateOrPayment);
                rs = statement.executeQuery();
                objectList = convertResultSetToList(rs);
                for (Object[] objects : objectList) {
                    final Object[] object = (Object[]) objects;
                    value = String.valueOf(object[0]);
                }
                LOGGER.info("callCalculationProcedure output-->" + value);
                
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (Exception e) {
                LOGGER.error(e);
            }
        }
        LOGGER.info("Ending callCalculationProcedure" + result);
        return value;
    }

    /**
     * Saves the methodology details before calculate
     */
    public String saveMethodology(BaseRateDTO dto, List selectedPeriods, String manual) {
        LOGGER.info("Entered Save Methodology" + selectedPeriods.size());
        String calc = StringUtils.EMPTY;
        try {
            AcBrMethodologyDetails details = AcBrMethodologyDetailsLocalServiceUtil.createAcBrMethodologyDetails(0);
            AcBaseRateBaselineDetails baseDetails = AcBaseRateBaselineDetailsLocalServiceUtil.createAcBaseRateBaselineDetails(0);
            details.setAccClosureMasterSid(dto.getAcMasterSid());
            details.setMethodologyName(getMethodology().get(dto.getMethodologyName()));
            details.setSalesBasis(0);
            details.setMethodologyStartDate(dto.getMethodStartDate());
            details.setMethodologyEndDate(dto.getMethodEndDate());
            details.setCalculationFlag(true);
            details.setFrequency(dto.getFrequecny());
            if (!utils.getNull(dto.getProvisionGrowthRate())) {
                details.setProvisionGrowthRate(Double.valueOf(dto.getProvisionGrowthRate()));
            }
            if (!utils.getNull(dto.getSalesGrowthRate())) {
                details.setSalesGrowthRate(Double.valueOf(dto.getSalesGrowthRate()));
            }
            if ("Exponential Smoothing".equals(manual)) {
                details.setDampingFactor(Double.valueOf(dto.getDampingFactor()));
            }
            details = AcBrMethodologyDetailsLocalServiceUtil.addAcBrMethodologyDetails(details);
            int count = selectedPeriods.size();
            for (Object object : selectedPeriods) {
                final Object[] obj = (Object[]) object;
                baseDetails.setAcBrMethodologyDetailsSid(details.getAcBrMethodologyDetailsSid());
                baseDetails.setSalesPeriod(obj[1]!=null && Integer.valueOf(String.valueOf(obj[1]))==1 ? Boolean.TRUE:Boolean.FALSE);
                baseDetails.setPaymentsPeriod(obj[2]!=null && Integer.valueOf(String.valueOf(obj[2]))==1 ? Boolean.TRUE:Boolean.FALSE);
                baseDetails.setPeriodSid(Integer.parseInt(String.valueOf(obj[0])));
                if ("Time Weighted Average".equals(manual)) {
                    baseDetails.setPeriodValue(Double.valueOf(String.valueOf(obj[3])));
                }
                AcBaseRateBaselineDetailsLocalServiceUtil.addAcBaseRateBaselineDetails(baseDetails);
            }
            
            calc = callCalculationProcedure(dto);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.info("Ended Save Methodology" + calc);
        return calc;
    }

    /**
     * Sets the workflow for the base rate
     *
     * @param masterId
     */
    public WorkflowMasterDTO setWorkflow(int masterId, String status, boolean flag, WorkflowMasterDTO wfSid) {
        LOGGER.info("Entering setWorkflow method" + masterId);
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil
                .forClass(AccClosureMaster.class);
        AccClosureMaster master = AccClosureMasterLocalServiceUtil.createAccClosureMaster(0);
        dynamicQuery.add(RestrictionsFactoryUtil.eq(
                "moduleType", CommonUtils.BUSINESS_PROCESS_TYPE_BR));
        dynamicQuery.add(RestrictionsFactoryUtil.eq("accClosureMasterSid",
                masterId));
        WorkflowMasterDTO workflow = new WorkflowMasterDTO();
        
        List<AccClosureMaster> resultList;
        try {
            int statusSid = getStatusId(status);
            resultList = dao.getBRMaster(dynamicQuery);
            
            for (AccClosureMaster pm : resultList) {
                AccClosureMaster projMaster = pm;

                projMaster.setWorkflowStatus((statusSid));
                projMaster.setModifiedDate(new Date());
                dao.updateBRMaster(projMaster);
                
            }
            LOGGER.info("Ending submitNMProjection ");
            if (wfSid.getProjectionId() != 0) {
                masterId = wfSid.getProjectionId();
            }
            workflow = logic.saveWorkflow(masterId, userId, statusSid, flag, wfSid);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.info("Ending setWorkflow method");
        return workflow;
    }

    /**
     * It retrieves the helper table sid for the workflow status
     *
     * @return
     */
    public int getStatusId(String wStatus) throws Exception {
        LOGGER.info("Entering getStatusId method" + wStatus);
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil
                .forClass(HelperTable.class);
        dynamicQuery.add(RestrictionsFactoryUtil.eq(
                "listName", CommonUtils.WORKFLOWSTATUS));
        dynamicQuery.add(RestrictionsFactoryUtil.eq("description",
                wStatus));
        List<HelperTable> resultList = dao.getHelperSid(dynamicQuery);
        int sid = resultList.get(0).getHelperTableSid();
        LOGGER.info("Ending getStatusId method" + sid);
        return sid;
    }
    
    public void setSessionDates(SessionDTO session) throws Exception {
        ForecastConfig forecastConfig = dsLogic.getTimePeriod();
        if (forecastConfig != null) {
            session.setDsFromDate(forecastConfig.getFromDate());
            session.setDsToDate(forecastConfig.getToDate());
        }
    }

    /**
     * To get the formatted value for Excel
     *
     * @param FORMAT
     * @param value
     * @return
     */
    public String getFormattedValue(NumberFormat FORMAT, String value) {
        if (value.contains("null") || value.equals("-")) {
            value = "-";
        } else {
            Double newValue = Double.valueOf(value);
            if (((DecimalFormat)FORMAT).toPattern().contains("%")) {
                newValue = newValue / 100;
            }
            value = FORMAT.format(newValue);
        }
        return value;
    }
    
    public String setZeroValue(final int i) {
        String symbol = StringUtils.EMPTY;
        if (i == 3 || i == 5 || i == 11) {
            symbol = "00.00%";
        } else {
            symbol = "$0.00";
        }
        return symbol;
    }

    /**
     * Insert values into the summary table during the tab change
     *
     * @param acSid
     */
    public void insertSummary(BaseRateDTO baseRateDTO) {
        try {
            String query = StringUtils.EMPTY;
            LOGGER.info("Entered getLoadDataQuery insertSummary" + baseRateDTO.getAcMasterSid());
            Map<String, String> inputMap = new HashMap<String, String>();
            LOGGER.info("?QU?" + String.valueOf(baseRateDTO.getAcMasterSid()));
            LOGGER.info("?UID?" + String.valueOf(userId));
            LOGGER.info("?SID?" + String.valueOf(baseRateDTO.getSessionId()));
            inputMap.put("?QU?", String.valueOf(baseRateDTO.getAcMasterSid()));
            inputMap.put("?UID?", String.valueOf(userId));
            inputMap.put("?SID?", String.valueOf(baseRateDTO.getSessionId()));
            query = CommonUtil.getQuery(inputMap, "br.insertSummary");
            dao.executeUpdateQuery(query);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    /**
     * To convert the given Result Set into List of Objects
     *
     * @param rs
     * @return
     */
    private static List<Object[]> convertResultSetToList(ResultSet rs) {
        List<Object[]> objList = new ArrayList<Object[]>();
        
        try {
            ResultSetMetaData rsMetaData = rs.getMetaData();
            int columnCount = rsMetaData.getColumnCount();
            Object[] header = new Object[columnCount];
            for (int i = 1; i <= columnCount; ++i) {
                Object label = rsMetaData.getColumnLabel(i);
                header[i - 1] = label;
            }
            while (rs.next()) {
                Object[] str = new Object[columnCount];
                for (int i = 1; i <= columnCount; ++i) {
                    Object obj = rs.getObject(i);
                    str[i - 1] = obj;
                }
                objList.add(str);
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        } finally {
            try {
                rs.close();
            } catch (SQLException ex) {
                LOGGER.error(ex);
            }
        }
        return objList;
    }

    /**
     *
     * @param checkedList
     * @param fre
     * @return
     * @throws Exception
     */
    public List getPeriodSid(List checkedList, String fre,
            Map<String, Integer> salesMap, Map<String, Integer> paymentMap, Map<String, Integer> textMap) throws Exception {
        LOGGER.info("Entered getPeriodSid" + checkedList.size());
        List periods = new ArrayList();
        List sid = new ArrayList();
        Map<String, String> map = new HashMap<String, String>();
        String periodQuery = StringUtils.EMPTY;
        for (int i = 0; i < checkedList.size(); i++) {
            periodQuery = comquery.periodSidQuery(checkedList.get(i).toString(), fre,
                    salesMap, paymentMap, textMap);
            periods = (List<Object[]>) dao.executeSelectQuery(periodQuery);
            for (int j = 0; j < periods.size(); j++) {
                sid.add(periods.get(j));
            }
            
        }
        
        LOGGER.info("Ending getPeriodSid list" + sid.size());
        return sid;
    }
    
    public int getTransCount(BaseRateSummaryDTO projSelDTO) {
        LOGGER.info("Entering getTransCount");
        List list = new ArrayList();
        try {
            String query = comquery.getSummaryQuery(projSelDTO, "brTransCount");
            list = (List<Object[]>) dao.executeSelectQuery(query);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.info("list" + list.size());
        Object obj = list.get(0);
        LOGGER.info("Ending getTransCount" + (Integer) obj);
        return obj == null ? 0 : (Integer) obj;
    }
    
    public List<BaseRateSummaryDTO> getTransData(BaseRateSummaryDTO projSelDTO) {
        LOGGER.info("Entering getConfigureLoadData");
        List<BaseRateSummaryDTO> salesList = new ArrayList<BaseRateSummaryDTO>();
        salesList = configureTransData(projSelDTO);
        LOGGER.info("Ended getConfigureLoadData");
        return salesList;
    }

    /**
     * Get configure level
     *
     * @param projSelDTO
     * @return
     */
    public List<BaseRateSummaryDTO> configureTransData(BaseRateSummaryDTO projSelDTO) {
        LOGGER.info("Entering configureTransData" + projSelDTO.getLevelNo());
        List<BaseRateSummaryDTO> resultList = new ArrayList<BaseRateSummaryDTO>();
        try {
            String query = comquery.getSummaryQuery(projSelDTO, "brTrans");
            List list = (List<Object[]>) dao.executeSelectQuery(query);
            Set<String> levelName = new HashSet<String>();
            BaseRateSummaryDTO dto;
            for (Object object : list) {
                final Object[] obj = (Object[]) object;
                dto = new BaseRateSummaryDTO();
                dto.setCustomerNo(String.valueOf(obj[0]));
                dto.setCustomerName(String.valueOf(obj[1]));
                dto.setContractNo(String.valueOf(obj[2]));
                dto.setContractName(String.valueOf(obj[3]));
                dto.setTherpeutic(String.valueOf(obj[4]));
                dto.setBrandId(String.valueOf(obj[5]));
                dto.setBrandName(String.valueOf(obj[6]));
                dto.setItemName(String.valueOf(obj[7]));
                dto.setStartDate(String.valueOf(obj[8]));
                dto.setEndDate(String.valueOf(obj[9]));
                dto.setAccrualRate(String.valueOf(obj[10]));
                dto.setSuggestedRate(String.valueOf(obj[11]));
                if (!utils.getNull(String.valueOf(obj[12]))) {
                    dto.setReasonCode(String.valueOf(obj[12]));
                }
                if (!utils.getNull(String.valueOf(obj[13]))) {
                    
                    dto.setNotes(String.valueOf(obj[13]));
                }
                resultList.add(dto);
            }
            
        } catch (Exception e) {
            LOGGER.error(e);
        }
        LOGGER.info("Ending configureTransData" + resultList.size());
        return resultList;
    }

    /**
     * This method will update the summary table for the populate functionality
     *
     * @param dto
     */
    public void updateSummary(BaseRateSummaryDTO dto, String popValue) {
        String query = StringUtils.EMPTY;
        LOGGER.info("Entered updateSummary" + dto.getAcMasterSid());
        try {
            Map<String, String> inputMap = new HashMap<String, String>();
            inputMap.put("?VAL?", popValue);
            inputMap.put("?QU?", String.valueOf(dto.getAcMasterSid()));
            inputMap.put("?UID?", String.valueOf(userId));
            inputMap.put("?SID?", String.valueOf(dto.getSessionId()));
            query = CommonUtil.getQuery(inputMap, "br.summarypopulate");
            dao.executeUpdateQuery(query);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        
    }

    /**
     * This will return map for the methodologies
     *
     * @return
     */
    public Map<String, String> getMethodology() {
        Map<String, String> map = new HashMap<String, String>();
        map.put(AVERAGE.getConstant(), "AVERAGE");
        map.put(WEIGHTED_AVERAGE.getConstant(), "WEIGHTED_AVERAGE");
        map.put(TIME_WEIGHTED_AVERAGE.getConstant(), "TIME_WEIGHTED_AVERAGE");
        map.put(MOVING_AVERAGE.getConstant(), "MOVING_AVERAGE");
        map.put(GROWTH.getConstant(), "GROWTH");
        map.put(EXPONENTIAL_SMOOTHING.getConstant(), "EXPONENTIAL_SMOOTHING");
        
        return map;
    }
    
    public void saveLogic(int sid) {
        try {
            dao.saveLogic(sid);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }
    
    public void updateCheckQuery(BaseRateSummaryDTO dto, int val, String sessionId) {
        try {
            String query = "UPDATE STS SET STS.CHECK_RECORD = '" + val + "' FROM ST_AC_BASE_RATE_SUMMARY STS\n"
                    + "JOIN ACC_CLOSURE_DETAILS ACD ON STS.ACC_CLOSURE_DETAILS_SID = ACD.ACC_CLOSURE_DETAILS_SID\n"
                    + "JOIN ACC_CLOSURE_MASTER ACM ON ACD.ACC_CLOSURE_MASTER_SID = ACM.ACC_CLOSURE_MASTER_SID\n"
                    + "JOIN AC_BR_METHODOLOGY_DETAILS ACBR ON ACM.ACC_CLOSURE_MASTER_SID = ACBR.ACC_CLOSURE_MASTER_SID\n"
                    + "AND ACBR.AC_BR_METHODOLOGY_DETAILS_SID = '" + dto.getMethodologySid() + "'"
                    + "AND STS.USER_ID = '" + userId + "' AND STS.SESSION_ID = " + sessionId;
            dao.executeUpdateQuery(query);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    /**
     * This method will delete the summary table
     *
     * @param dto
     */
    public void deleteSummary(BaseRateSummaryDTO dto, List<String> deleteList) {
        String query = StringUtils.EMPTY;
        LOGGER.info("Entered updateSummary" + dto.getAcMasterSid() + deleteList.size());
        try {
            Map<String, String> inputMap = new HashMap<String, String>();
            inputMap.put("?QU?", String.valueOf(dto.getAcMasterSid()));
            inputMap.put("?UID?", String.valueOf(userId));
            inputMap.put("?SID?", String.valueOf(dto.getSessionId()));
            inputMap.put("?MET?", utils.CollectionToString(deleteList, true));
            query = CommonUtil.getQuery(inputMap, "br.summarydelete");
            dao.executeUpdateQuery(query);
        } catch (Exception e) {
            LOGGER.error(e);
        }
        
    }
    
    public int saveBRSummary(BaseRateSummaryDTO dto) {
        String query = StringUtils.EMPTY;
        LOGGER.info("Entered updateSummary");
        try {
            Map<String, String> inputMap = new HashMap<String, String>();
            inputMap.put("?UID?", String.valueOf(userId));
            inputMap.put("?SID?", String.valueOf(dto.getSessionId()));
            query = CommonUtil.getQuery(inputMap, "ac.saveToMainTable");
            return dao.executeUpdateQuery(query);
        } catch (Exception e) {
            LOGGER.error(e);
            return 0;
        }
    }

    /**
     * This method is to set format for the tables inside the table
     *
     * @param i
     * @return
     */
    private NumberFormat setFormat(int i) {
        NumberFormat FORMAT;
        if (i == 3 || i == 5 || i == 11) {
            FORMAT = PERCENTAGE_FORMAT;
            symbol = "%";
        } else {
            FORMAT = GTS;
            symbol = "$";
        }
        return FORMAT;
    }

    /**
     * This method will save the master records
     */
    public void saveAccruals(BaseRateDTO baseRateDTO) {
        try {
            List input = new ArrayList();
            input.add(baseRateDTO.getAcMasterSid());
            CommonQueryLogic.itemUpdate(input, "Save Accruals");
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    /**
     * Load the default values in edit scenario based on the account closure
     * master id
     *
     * @param projSelDTO
     * @return
     */
    public BaseRateDTO loadEdit(int sid) {
        BaseRateDTO dto = new BaseRateDTO();
        try {
            List input = new ArrayList();
            input.add(sid);
            Map<String, String> inputMap = new HashMap<String, String>();
            inputMap.put("?AID?", String.valueOf(sid));
            String query = CommonUtil.getQuery(inputMap, "ac.loadEditValues");
            List list = (List) dao.executeSelectQuery(query);
            Object[] obj = (Object[]) list.get(0);
            dto.setGlComapnySid(String.valueOf(obj[0]));
            dto.setContractTypeSid(String.valueOf(obj[1]));
            dto.setDiscType(String.valueOf(obj[2]));
            dto.setDiscSubType(String.valueOf(obj[3]));
            dto.setContract(String.valueOf(obj[4]));
            dto.setProduct(String.valueOf(obj[5]));
            dto.setNdc(String.valueOf(obj[6]));
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return dto;
    }
    
    public boolean stopDate(int sid) throws Exception {
        boolean returnDate = false;
        String query = "select \n"
                + "TOP 1\n"
                + "CASE WHEN \n"
                + "ACB.METHODOLOGY_START_DATE<GETDATE()\n"
                + "THEN \n"
                + "'TRUE'\n"
                + "ELSE\n"
                + "'FALSE'\n"
                + "END \n"
                + "START_DATE\n"
                + " from AC_BASE_RATE_SUMMARY ACB\n"
                + "JOIN\n"
                + "ACC_CLOSURE_DETAILS ACD\n"
                + "ON \n"
                + "ACD.ACC_CLOSURE_DETAILS_SID=ACB.ACC_CLOSURE_DETAILS_SID\n"
                + "WHERE ACD.ACC_CLOSURE_MASTER_SID=" + sid + " ";
        List list = (List) dao.executeSelectQuery(query);
        if (!list.isEmpty()) {
            returnDate = Boolean.valueOf(list.get(0).toString());
        }
        LOGGER.info("returnDate=---------------" + returnDate);
        return returnDate;
        
    }

    /**
     * This method will return the workflow
     *
     * @param sid
     * @return
     * @throws Exception
     */
    public WorkflowMasterDTO getWorkFlowDetails(int sid, WorkflowMasterDTO wmDTO) throws Exception {
        WorkflowMaster master = WorkflowMasterLocalServiceUtil.createWorkflowMaster(0);
        master = WorkflowMasterLocalServiceUtil.getWorkflowMaster(sid);
        wmDTO.setCreatedBy(master.getCreatedBy());
        wmDTO.setModifiedBy(master.getModifiedBy());
        return wmDTO;
    }
    
    public void updateMethodologyFlag(int sid, String methodology) {
        try {
            String query = "UPDATE AC_BR_METHODOLOGY_DETAILS SET CALCULATION_FLAG = '1'\n"
                    + "WHERE ACC_CLOSURE_MASTER_SID = '" + sid + "' and METHODOLOGY_NAME = '" + getMethodology().get(methodology) + "'";
            dao.executeUpdateQuery(query);
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }
   
    /**
     * The save procedure for Accruals
     *
     * @param session
     * @return
     */
    public boolean callAccrualProcedure(BaseRateDTO brDto) {
        LOGGER.info("Entering callAccrualProcedureProcedure" + brDto.getAcMasterSid());
        Connection connection = null;
        DataSource datasource;
        CallableStatement statement = null;
        boolean result = false;
        try {
            Context initialContext = new InitialContext();
            datasource = (DataSource) initialContext.lookup("java:jboss/datasources/jdbc/appDataPool");
            if (datasource != null) {
                connection = datasource.getConnection();
            }
            if (connection != null) {
                statement = connection.prepareCall("{call PRC_AC_ACCRUAL_DETAILS_INSERT (?)}");
                
                statement.setInt(1, brDto.getAcMasterSid());
                result = statement.execute();
            }
        } catch (Exception ex) {  
            LOGGER.error(ex);
        } finally {
            try {
                statement.close();
                connection.close();
            } catch (Exception e) {
                LOGGER.error(e);
            }
        }
        LOGGER.info("Ending callAccrualProcedureProcedure" + result);
        return false;
    }
    /**
     * This method will update the check Record to 0 in the database for reset button.
     * @param sessionId
     * @return 
     */
       public int resetBRSummary(String sessionId) {
    String query = StringUtils.EMPTY;
        LOGGER.info("Entered updateSummary");
        try {
            Map<String, String> inputMap = new HashMap<String, String>();
            inputMap.put("?UID?", String.valueOf(userId));
            inputMap.put("?SID?", String.valueOf(sessionId));
            query = CommonUtil.getQuery(inputMap, "ac.resetBR");
            return dao.executeUpdateQuery(query);
        } catch (Exception e) {
            LOGGER.error(e);
            return 0;
        }
    }
}

