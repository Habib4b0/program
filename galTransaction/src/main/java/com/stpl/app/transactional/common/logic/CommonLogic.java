package com.stpl.app.transactional.common.logic;

import com.stpl.app.model.AccrualDetails;
import com.stpl.app.model.AccrualMaster;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.stpl.app.model.HelperTable;
import com.stpl.app.model.TransactionModuleDetails;
import com.stpl.app.model.TransactionModuleMaster;
import com.stpl.app.model.VwInventoryWdActualProjMas;
import com.stpl.app.model.VwIvldInventoryWdActualProjMas;
import com.stpl.app.parttwo.model.CustomerGtsActual;
import com.stpl.app.parttwo.model.IvldCustomerGtsActual;
import com.stpl.app.service.AccrualMasterLocalServiceUtil;
import com.stpl.app.service.BusinessroleModuleLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.TransactionModuleDetailsLocalServiceUtil;
import com.stpl.app.service.TransactionModuleMasterLocalServiceUtil;
import com.stpl.app.transactional.common.dto.DetailsDTO;
import com.stpl.app.transactional.common.dto.FtpProperties;
import com.stpl.app.util.ConstantUtil;
import com.stpl.app.util.HelperDTO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.OrderFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionList;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.User;
import com.stpl.portal.service.UserLocalServiceUtil;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.ComboBox;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.concurrent.ConcurrentHashMap;
import org.jboss.logging.Logger;

/**
 *
 * @author sooriya.lakshmanan
 */
public class CommonLogic {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(CommonLogic.class);
    static String tableValue1 = null;
    static String typeValue = null;
    static boolean invalid = false;
    static boolean invalidActualGTS = false;
    static boolean adjustDemand= false;
    static int isMaster= 0;
    static boolean invalidAdjustedDemand = false;
    public static ResourceBundle InterfaceScripts = ResourceBundle.getBundle(ConstantUtil.PROP_INTERFACE_SCRIPTS);
    public List<String> PROPERTY_LIST = Arrays.asList(ConstantUtil.INVENTORY_UNIT_CHANGE,ConstantUtil.UNCAPTURED_UNITS,ConstantUtil.UNCAPTURED_UNITS_RATIO,ConstantUtil.FORECASTNAME,ConstantUtil.FORECASTVER);
    Map<Integer, Boolean> SelectedAccrualsId = new LinkedHashMap<Integer, Boolean>();
    public List<String> GTS_INVAID_LIST =Arrays.asList(ConstantUtil.CUS_GTS_FORECAST_ID);
    public List<String> ACCURAL_INVAID_LIST =Arrays.asList("COMPANY_QUALIFIER_ID");
    static int isForecast = 0;
    static int isForecastDemand = 0;
    /**
     * Get the all the fields to be create in dynamic
     *
     * @param moduleName
     * @param tabName
     * @return
     * @throws Exception
     */
    public Object[] getFiledNames(String moduleName, String tabName) throws Exception {
        LOGGER.info("getFieldNames  moduleName =" + moduleName + " tabName=" + tabName);
        int sysId = 0;
        Object[] ob = new Object[5];
        Class mainClass = Class.forName(ConstantUtil.TRANSACTION_MODULE_MASTER);
        Class returnClass = Class.forName(ConstantUtil.TRANSACTION_MODULE_MASTER_UTIL);
        Method method = returnClass.getMethod(ConstantUtil.DYNAMICQUERY, DynamicQuery.class);
        final DynamicQuery companyDynamicQuery = DynamicQueryFactoryUtil.forClass(mainClass);
        companyDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantUtil.MODULE_NAME, moduleName));
        companyDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantUtil.TAB_NAME, tabName));
        ProjectionList projectonList = ProjectionFactoryUtil.projectionList();
        projectonList.add(ProjectionFactoryUtil.property(ConstantUtil.MASTER_SID));
        projectonList.add(ProjectionFactoryUtil.property(ConstantUtil.TABLE_NAME));
        projectonList.add(ProjectionFactoryUtil.property(ConstantUtil.INVALID_TABLE_NAME));
        companyDynamicQuery.setProjection(projectonList);
        List<Object> moduleMasterValue = (List<Object>) method.invoke(returnClass.newInstance(), companyDynamicQuery);
        Object[] obArr = (Object[]) moduleMasterValue.get(0);
        sysId = (Integer) obArr[0];
        LOGGER.info("System Id = " + sysId);
        ob[0] = convertToDetailsDto(getDetailsTableByID(sysId, false), obArr[1], 0);
        int tabcount = getMasterCount(moduleName);
        ob[1] = convertToDetailsDto(getDetailsTableByID(sysId, true), obArr[1], tabcount);
        ob[2] = tabcount;
        ob[3] = obArr[2];
        return ob;
    }

    /**
     * DB Hit to get primaryKey
     *
     * @param sysId
     * @param detailsQuery
     * @param flag
     * @return
     * @throws Exception
     */
    public DynamicQuery getPrimaryKeyValues(int sysId, DynamicQuery detailsQuery, boolean flag) throws Exception {
        detailsQuery.add(RestrictionsFactoryUtil.eq(ConstantUtil.MASTER_SID, sysId));
        if (flag) {
                detailsQuery.add(RestrictionsFactoryUtil.eq(ConstantUtil.CATEGORY_NAME, ConstantUtil.PRIMARY_KEY));
        } else {
            detailsQuery.add(RestrictionsFactoryUtil.ne(ConstantUtil.CATEGORY_NAME, ConstantUtil.PRIMARY_KEY));
        }
        if (!ConstantUtil.INVALID_RECORD_COUNT.equalsIgnoreCase(String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.TABLE_NAME1)))) {
            detailsQuery.add(RestrictionsFactoryUtil.eq(ConstantUtil.FLAG, ConstantUtil.KEY_Y));
        }
        ProjectionList projectonDetailsList = ProjectionFactoryUtil.projectionList();
        projectonDetailsList.add(ProjectionFactoryUtil.property(ConstantUtil.PROPERTY_NAME));
        projectonDetailsList.add(ProjectionFactoryUtil.property(ConstantUtil.CATEGORY_NAME));
        projectonDetailsList.add(ProjectionFactoryUtil.property(ConstantUtil.DISPLAY_NAME));
        projectonDetailsList.add(ProjectionFactoryUtil.property(ConstantUtil.VALIDATION));
        projectonDetailsList.add(ProjectionFactoryUtil.property(ConstantUtil.INDEX));
        detailsQuery.addOrder(OrderFactoryUtil.asc(ConstantUtil.INDEX));
        detailsQuery.setProjection(projectonDetailsList);

        return detailsQuery;
    }

    /**
     * setting the object list in to DTO
     *
     * @param list
     * @return
     */
    public List<DetailsDTO> convertToDetailsDto(List<Object> list, Object tableName, int count) {
        List<DetailsDTO> dtoList = new ArrayList<DetailsDTO>();
        for (int i = 0; i < list.size(); i++) {
            DetailsDTO dto = new DetailsDTO();
            Object[] ob = (Object[]) list.get(i);
            dto.setPropertyName(ob[0] == null ? StringUtils.EMPTY : ob[0].toString());
            dto.setCategoryName(ob[1] == null ? StringUtils.EMPTY : ob[1].toString());
            dto.setDisplayName(ob[2] == null ? StringUtils.EMPTY : ob[2].toString());
            dto.setValidation(ob[3] == null ? StringUtils.EMPTY : ob[3].toString());
            dto.setPropertyIndex(ob[4] == null ? StringUtils.EMPTY : ob[4].toString());
            dto.setTableName(tableName.toString());
            dto.setScreenCount(count);
            dtoList.add(dto);
        }
        return dtoList;
    }

    /**
     * Fetching the values from DB for view Pages
     *
     * @param sysID
     * @param moduleDetails
     * @return
     * @throws Exception
     */
    public Object[] getValuesById(final int sysID, final Object[] ob) throws Exception {
        LOGGER.info("Entering getMasterById with id value  :::: " + sysID);
        StringBuilder query = new StringBuilder();
        query.append("Select ");
        String tableName = StringUtils.EMPTY;
        String queryval = "";
        String invalidtableName = StringUtils.EMPTY;
         HashMap hm=null;
        if(adjustDemand==true){
           hm=new HashMap();
            hm.put(ConstantUtil.FORECASTVER,ConstantUtil.FORECAST_VERSION);
            hm.put("FORECAST_MONTH","MONTH");
            hm.put("FORECAST_YEAR","YEAR");
            hm.put("TOTAL_DEMAND_AMOUNT","TOTAL_ADJUSTED_DEMAND_AMOUNT");
            hm.put("TOTAL_DEMAND_UNITS","TOTAL_ADJUSTED_DEMAND_UNITS");
            if(invalidAdjustedDemand){
               hm.put("DEMAND_INT_SID","ADJUSTED_DEMAND_FORECAST_INTF_ID");  
            }else{
                hm.put("ADJUSTED_DEMAND_FORECAST_INTF_ID","DEMAND_INT_SID");  
            }
        }
        int createdByIndex=0;
        int modifyByIndex=0;
        int i=0;
        for (DetailsDTO moduleDetail : (List<DetailsDTO>) ob[0]) {
            
            if (!ConstantUtil.Button.equals(moduleDetail.getCategoryName()) && !ConstantUtil.PRIMARY_KEY.equals(moduleDetail.getCategoryName())) {
                tableName = moduleDetail.getTableName();
                if(!(ConstantUtil.ADJUST_DEMAND_VIEW.equals(tableName) || ConstantUtil.DEMAND_VIEW.equals(tableName) ||
                  "VW_IVLD_DEMAND_FORECAST_ACTUAL".equals(tableName) || ConstantUtil.INVALID_ADJUST_DEMAND_VIEW.equals(tableName)) && (adjustDemand==true || invalidAdjustedDemand==true)){
                    adjustDemand=false;
                    invalidAdjustedDemand=false;
                }
                if(adjustDemand==true){
                   query.append(hm.get(moduleDetail.getPropertyName())==null?moduleDetail.getPropertyName():hm.get(moduleDetail.getPropertyName())).append(",");
                }else{
                query.append(moduleDetail.getPropertyName()).append(",");
                }
                
               if("CREATED_BY".equals(moduleDetail.getPropertyName())){
                   createdByIndex=i;
               }else if("MODIFIED_BY".equals(moduleDetail.getPropertyName())){
                   modifyByIndex=i;
               }
               i++;
            }
        }
      
         if ((tableName.equals(ConstantUtil.DEMAND_VIEW) && adjustDemand==false)  || (adjustDemand==true)) {
             if(tableValue1.equals(ConstantUtil.ACTUALS)){
            queryval = actualQuery(PROPERTY_LIST, 0, query.toString());
            query.delete(0, query.length());
            query.append(queryval);
             }
        }
         if ("IVLD_ACCRUAL_INBOUND".equals(String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.INVALID_TABLE_NAME))) ) {
            queryval = actualQuery(ACCURAL_INVAID_LIST, 0, query.toString());
            query.delete(0, query.length());
            query.append(queryval);
          
        }
        query = new StringBuilder(query.substring(0, query.length() - 1));
        if (ConstantUtil.INVALID_RECORD_COUNT.equalsIgnoreCase(String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.TABLE_NAME1)))) {
             if(adjustDemand==true && invalidAdjustedDemand==true){
              invalidtableName=ConstantUtil.INVALID_ADJUST_DEMAND_VIEW;   
            }else{
            invalidtableName = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.INVALID_TABLE_NAME));
             }
            query.append(" from ").append(invalidtableName).append(" where "
                    + String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.VIEW_SID_NAME))
                    + " = ").append(sysID);
            if(invalidtableName.equals(ConstantUtil.INVALID_INVENTORYVIEW_TABLE_IN_VIEW)){ 
                query.append(" and IS_FORECAST = '").append(isForecast).append("' and IS_MASTER = '").append(isMaster).append("'");
            } 
        } else {
            if(adjustDemand==true && !invalidAdjustedDemand==true){
            tableName=ConstantUtil.ADJUST_DEMAND_VIEW;
            }
            query.append(" from ").append(tableName).append(" where " ); 
                    if(adjustDemand==true && invalidAdjustedDemand==false){
                query.append("ADJUSTED_DEMAND_FORECAST_ID") ;       
                    }
                        else{
                query.append( ((List<DetailsDTO>) ob[1]).get(0).getDisplayName() );
                    }
                    query.append( " = ").append(sysID);
                if(tableName.equals(ConstantUtil.INVENTORYVIEW_TABLE_IN_VIEW)){ 
                query.append(" and IS_FORECAST = '").append(isForecast).append("' and IS_MASTER = '").append(isMaster).append("'");
            }
                if(tableName.equals(ConstantUtil.DEMAND_VIEW) || tableName.equals(ConstantUtil.ADJUST_DEMAND_VIEW)){ 
                query.append(" and IS_FORECAST = '").append(isForecastDemand).append("'");
            }
        }        
        if (tableName.equals(ConstantUtil.ACCURAL_MASTER) && "true".equals(VaadinSession.getCurrent().getAttribute("AccrualDetails")) && !"IVLD_ACCRUAL_INBOUND".equals(invalidtableName)){
            String sql = query.toString();
            sql = sql.replace("COMP_IDENT_CODE_QUALIFIER", "COMPANY_IDENTIFIER_CODE_QUALIFIER");
            sql = sql.replace("ACCT_IDENT_CODE_QUALIFIER", "ACCT_IDENTIFIER_CODE_QUALIFIER");
            sql = sql.replace("ITEM_IDENT_CODE_QUALIFIER", "ITEM_IDENTIFIER_CODE_QUALIFIER");
            sql = sql.replace("ACCRUAL_MASTER", "ACCRUAL_DETAILS");
            query = new StringBuilder();
            query.append(sql);
        }
        if (invalidtableName.equalsIgnoreCase("IVLD_RETURNS")) {
            String sql = query.toString();
            sql = sql.replace("RETURNS_INTERFACE_ID", "RRESERVE_INTERFACE_ID");
            query = new StringBuilder();
            query.append(sql);
        }
   
        List<Object> objList = (List<Object>) BusinessroleModuleLocalServiceUtil.executeSelectQuery(invalidtableName.equals(ConstantUtil.IVLD_SALES_MASTER) ? query.toString().replace("ITEM_PARENT_NO", "PARENT_ITEM_NO") : query.toString(), null);
        
        if (tableName.contains("VW_INVENTORY_WD_ACTUAL_PROJ_MAS")){
            if (objList != null && !objList.isEmpty()) {
                Map<Integer, String> userMap = new ConcurrentHashMap<>();
                DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(User.class);
                List<User> userList = UserLocalServiceUtil.dynamicQuery(dynamicQuery);
                for (User user : userList) {
                    userMap.put(Long.valueOf(user.getUserId()).intValue(), user.getFullName());
                }
                Object[] result = (Object[]) objList.get(0);
                if (result[createdByIndex] != null && !result[createdByIndex].toString().isEmpty() && String.valueOf(result[createdByIndex]).matches("\\-?\\d+")) {
                    result[createdByIndex] = userMap.get(Integer.valueOf(String.valueOf(result[16])));
                }
                if (result[modifyByIndex] != null && !result[modifyByIndex].toString().isEmpty() && String.valueOf(result[modifyByIndex]).matches("\\-?\\d+")) {
                    result[modifyByIndex] = userMap.get(Integer.valueOf(String.valueOf(result[modifyByIndex])));
                }
                return result;
            } else {
                return new Object[0];
            }
        }else if  (tableName.contains("CUSTOMER_GTS_ACTUAL")){
              if (objList != null && !objList.isEmpty()) {
                Map<Integer, String> userMap = new ConcurrentHashMap<>();
                DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(User.class);
                List<User> userList = UserLocalServiceUtil.dynamicQuery(dynamicQuery);
                for (User user : userList) {
                    userMap.put(Long.valueOf(user.getUserId()).intValue(), user.getFullName());
                }
                Object[] result = (Object[]) objList.get(0);
                if (result[createdByIndex] != null && !result[17].toString().isEmpty() && String.valueOf(result[createdByIndex]).matches("\\-?\\d+")) {
                    result[createdByIndex] = userMap.get(Integer.valueOf(String.valueOf(result[createdByIndex])));
                }
                if (result[modifyByIndex] != null && !result[modifyByIndex].toString().isEmpty() && String.valueOf(result[modifyByIndex]).matches("\\-?\\d+")) {
                    result[modifyByIndex] = userMap.get(Integer.valueOf(String.valueOf(result[modifyByIndex])));
                }
                return result;
            } else {
                return new Object[0];
            }
        }else if  (tableName.contains("VW_CUSTOMER_GTS_FORECAST")){
              if (objList != null && !objList.isEmpty()) {
                Map<Integer, String> userMap = new ConcurrentHashMap<>();
                DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(User.class);
                List<User> userList = UserLocalServiceUtil.dynamicQuery(dynamicQuery);
                for (User user : userList) {
                    userMap.put(Long.valueOf(user.getUserId()).intValue(), user.getFullName());
                }
                Object[] result = (Object[]) objList.get(0);
                if (result[createdByIndex] != null && !result[createdByIndex].toString().isEmpty() && String.valueOf(result[createdByIndex]).matches("\\-?\\d+")) {
                    result[createdByIndex] = userMap.get(Integer.valueOf(String.valueOf(result[createdByIndex])));
                }
                if (result[modifyByIndex] != null && !result[modifyByIndex].toString().isEmpty() && String.valueOf(result[modifyByIndex]).matches("\\-?\\d+")) {
                    result[modifyByIndex] = userMap.get(Integer.valueOf(String.valueOf(result[modifyByIndex])));
                }
                return result;
            } else {
                return new Object[0];
            }
        } else if (tableName.contains("ACCRUAL_MASTER")) {
            if (objList != null && !objList.isEmpty()) {
                Object[] result = (Object[]) objList.get(0);
                if (ConstantUtil.KEY_Y.equals(result[27])) {
                        result[27] = ConstantUtil.YES;
                    } else if (ConstantUtil.KEY_N.equals(result[27])) {
                        result[27] = ConstantUtil.NO;
                    } else {
                        result[27] = StringUtils.EMPTY;
                    }
            }
        }
        return objList.isEmpty() ? new Object[0] : (Object[]) objList.get(0);
        }

    public static String getUpperCamelString(final String sourceString, final boolean flag) {
        StringBuffer sb = new StringBuffer();
        for (String s : sourceString.split("_")) {
            if (flag) {
                sb.append(" ");
            }
            sb.append(Character.toUpperCase(s.charAt(0)));
            if (s.length() > 1) {
                sb.append(s.substring(1, s.length()).toLowerCase());
            }
        }
        return sb.toString().trim();
    }

    /**
     * To get the combo box select.
     *
     * @param select the select
     * @param listName
     * @return the native select
     * @throws java.lang.Exception
     */
    public ComboBox loadComboBox(final ComboBox select,
            String listName) throws Exception {
        BeanItemContainer<HelperDTO> resultContainer = new BeanItemContainer<HelperDTO>(HelperDTO.class);
        final List<HelperDTO> helperList = getHelperResults(listName);
        resultContainer.addAll(helperList);
        select.setContainerDataSource(resultContainer);
        select.setNullSelectionItemId(helperList.get(0));
        select.setItemCaptionPropertyId(ConstantUtil.DESCRIPTION);
        select.select(helperList.get(0));
        select.markAsDirty();
        select.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty() != null && event.getProperty().getValue() != null && (StringUtils.EMPTY.equals(event.getProperty().getValue()) || "null".equals(event.getProperty().getValue()))) {
                    select.select(helperList.get(0));
                }
         select.setDescription((String) (select.getValue()==null ? ConstantUtil.SELECT_ONE : ((HelperDTO)select.getValue()).getDescription()));        
            }
        });
        return select;
    }

    /**
     * Gets the item type.
     *
     * @param listType the list type
     * @return the item type
     */
    public List<HelperDTO> getHelperResults(final String listType) throws SystemException, Exception {

        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        final DynamicQuery cfpDynamicQuery;
        final List list;
        final ProjectionList projList;
        LOGGER.info("Entering getHelperResults  "+listType);
         if(!listType.isEmpty()){
        if (ConstantUtil.GL_BALANCE.equals(listType) || ConstantUtil.POSTING_INDICATOR.equals(listType)) {
            helperList.add(new HelperDTO(4, ConstantUtil.SELECT_ONE));
            helperList.add(new HelperDTO(1, ConstantUtil.YES));
            helperList.add(new HelperDTO(0, ConstantUtil.NO));
        } else if (ConstantUtil.QUANTITY_INCLUSION.equals(listType)) {
            helperList.add(new HelperDTO(4, ConstantUtil.SELECT_ONE));
            helperList.add(new HelperDTO(1, ConstantUtil.KEY_Y));
            helperList.add(new HelperDTO(0, ConstantUtil.KEY_N));
        } else if (ConstantUtil.RS_CATEGORY.equals(listType) || ConstantUtil.RS_TYPE.equals(listType) || ConstantUtil.REBATE_PROGRAM_TYPE.equals(listType) ) {
            cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(HelperTable.class);
            cfpDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantUtil.LIST_NAME, listType));
            ProjectionList projectonList = ProjectionFactoryUtil.projectionList();
        projectonList.add(ProjectionFactoryUtil.property(ConstantUtil.HELPER_TABLE_SID));
        projectonList.add(ProjectionFactoryUtil.property(ConstantUtil.DESCRIPTION));
         cfpDynamicQuery.setProjection(projectonList);
            list = HelperTableLocalServiceUtil.dynamicQuery(cfpDynamicQuery);
             helperList.add(new HelperDTO(0, ConstantUtil.SELECT_ONE));
                if (list != null) {
                    for (int i = 0; i < list.size(); i++) {
                         Object[] obj = (Object[]) list.get(i);
                        helperList.add(new HelperDTO(Integer.parseInt(obj[0].toString()), obj[1].toString()));
                    }
                }
        } else {
            if (ConstantUtil.BUSINESS_UNIT_NAME.equals(listType) || ConstantUtil.COST_CENTER.equals(listType) || ConstantUtil.PROGRAM_TYPE.equals(listType)) {
                cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(AccrualMaster.class);
                list = AccrualMasterLocalServiceUtil.dynamicQuery(cfpDynamicQuery);
                helperList.add(new HelperDTO(0, ConstantUtil.SELECT_ONE));
                if (list != null) {
                    for (int i = 0; i < list.size(); i++) {
                        helperList.add(new HelperDTO(i, list.get(i).toString()));
                    }
                }
            } else if (ConstantUtil.BUSINESS_UNIT_ACCRUAL.equals(listType)) {
                String query = "SELECT   Distinct CM.COMPANY_MASTER_SID,CM.COMPANY_NAME\n"
                        + "                        FROM   COMPANY_MASTER CM \n"
                        + "                        JOIN HELPER_TABLE HT ON CM.COMPANY_TYPE = HT.HELPER_TABLE_SID\n"
                        + "where  HT.DESCRIPTION = 'GLCOMP'  AND CM.INBOUND_STATUS <> 'D' "
                        + "                        ORDER BY CM.COMPANY_NAME;  ";
                list = HelperTableLocalServiceUtil.executeSelectQuery(query);
                helperList.add(new HelperDTO(0, ConstantUtil.SELECT_ONE));
                if (list != null) {
                    for (int i = 0; i < list.size(); i++) {
                        Object[] obj = (Object[]) list.get(i);
                        helperList.add(new HelperDTO(Integer.parseInt(obj[0].toString()), obj[1].toString()));

                    }
                }

            } 
            else if(ConstantUtil.COMPANY_COST_CENTER_ACCRUAL.equals(listType))
            {
                
            }
            else {
                cfpDynamicQuery = DynamicQueryFactoryUtil
                        .forClass(HelperTable.class);
                cfpDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantUtil.LIST_NAME,
                        listType));
                cfpDynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantUtil.DESCRIPTION));
                list = HelperTableLocalServiceUtil.dynamicQuery(cfpDynamicQuery);

                helperList.add(new HelperDTO(0, ConstantUtil.SELECT_ONE));
                if (list != null) {
                    for (int i = 0; i < list.size(); i++) {
                        final HelperTable helperTable = (HelperTable) list.get(i);
                        helperList.add(new HelperDTO(helperTable.getDescription()));
                    }
                }
            }
        }
         }
        LOGGER.info("helperList size  " + helperList.size());
        return helperList;
    }

    /**
     * To get result for excel generation.
     *
     * @param searchValues
     * @param start
     * @param end
     * @param excelVisibleColumnArr visible column array
     * @param tableName
     * @return
     * @throws Exception
     */
    public List<Object> getExcelList(final Map<Object, Object> searchValues, final int start, final int end,
            Object[] excelVisibleColumnArr, String tableName,final DetailsDTO primaryDTO) throws Exception {
        SearchLogic sl = new SearchLogic();
        List<Object> list = null;
        if (ConstantUtil.ACTUALS_MASTER.equals(tableName)) {
            list = sl.searchFindForActualMaster(searchValues, start, end, null, null, tableName, null, true, excelVisibleColumnArr,primaryDTO);
        } else if ("IvldSalesMaster".equals(tableName)) {
            list = sl.excelLogicForInvalidSales(searchValues,start,end);
        }
        else {
            list = sl.searchFind(searchValues, start, end, null, null, tableName, null, true, excelVisibleColumnArr, SelectedAccrualsId,primaryDTO);
        }
       
        if (ConstantUtil.GL_BALANCE_MASTER.equals(tableName)) {
            for (int i = 0; i < list.size(); i++) {
                Object[] ob = (Object[]) list.get(i);
                if ("1".equals(ob[4])) {
                    ob[4] = ("Yes");
                } else {
                    ob[4] = ("No");
                }
            }
        }
          else  if (ConstantUtil.ACCURAL_MASTER.equals(tableName)) {
                for (int i = 0; i < list.size(); i++) {
                    Object[] ob = (Object[]) list.get(i);
                    if ("1".equals(ob[14])) {
                        ob[14] = ("Yes");
                    } else {
                        ob[14] = ("No");
                    }
                }
            }
          else if ("CpiIndexMaster".equals(tableName)) {
                for (int i = 0; i < list.size(); i++) {
                    Object[] ob = (Object[]) list.get(i);
                    if (ob[1] != null) {
                        ob[1] = (CommonLogic.getDescription(Integer.valueOf(String.valueOf(ob[1]))));
                    }
                }

            }    
         if (tableName.contains("IvldReturns")) {
            for (int i = 0; i < list.size(); i++) {
                try {
                    DateFormat df = new SimpleDateFormat("MMM dd yyyy HH:mm");
                    DateFormat df2 = new SimpleDateFormat("MM/dd/yyyy");
                    df.setLenient(false);
                    Object[] ob = (Object[]) list.get(i);
                    ob[9] = (ob[9]==null?StringUtils.EMPTY: df2.format(df.parse(String.valueOf(ob[9]))));
                    ob[16] = (ob[16]==null?StringUtils.EMPTY: df2.format(df.parse(String.valueOf(ob[16]))));
                    ob[17] = (ob[17]==null?StringUtils.EMPTY: df2.format(df.parse(String.valueOf(ob[17]))));
                    ob[18] = (ob[18]==null?StringUtils.EMPTY: df2.format(df.parse(String.valueOf(ob[18]))));
                    ob[19] = (ob[19]==null?StringUtils.EMPTY: df2.format(df.parse(String.valueOf(ob[19]))));
                    ob[34] = (ob[34]==null?StringUtils.EMPTY: df2.format(df.parse(String.valueOf(ob[34]))));
                    ob[38] = (ob[38]==null?StringUtils.EMPTY: df2.format(df.parse(String.valueOf(ob[38]))));
                } catch (Exception e) {
                    LOGGER.error("Un parseable Date" + e.getCause());
                }
            }
        }
          if (ConstantUtil.ACCRUAL_DETAILS.equals(tableName)) {
                for (int i = 0; i < list.size(); i++) {
                    Object[] ob = (Object[]) list.get(i);
                    if (ConstantUtil.KEY_Y.equals(ob[14])) {
                        ob[14] = ConstantUtil.YES;
                    } else if (ConstantUtil.KEY_N.equals(ob[14])) {
                        ob[14] = ConstantUtil.NO;
                    } else {
                        ob[14] = StringUtils.EMPTY;
                    }
                }
            }
          if (tableName.contains(ConstantUtil.INVENTORYVIEW_TABLE) || tableName.contains(ConstantUtil.INVALID_INVENTORYVIEW_TABLE)) {
                Map<Integer, String> userMap = new ConcurrentHashMap<>();
                 List visibleCOlumnList=Arrays.asList(excelVisibleColumnArr);
                 int createdByIndexVal=visibleCOlumnList.indexOf("createdBy");
                 int modifiedByIndexVal=visibleCOlumnList.indexOf("modifiedBy");
                DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(User.class);
                List<User> userList = UserLocalServiceUtil.dynamicQuery(dynamicQuery);
                for (User user : userList) {
                    userMap.put(Long.valueOf(user.getUserId()).intValue(), user.getFullName());
                }
                for (int i = 0; i < list.size(); i++) {
                        Object[] ob = (Object[]) list.get(i);
                        if (!"null".equalsIgnoreCase(String.valueOf(ob[createdByIndexVal])) && ob[createdByIndexVal] != null 
                                && (String.valueOf(ob[createdByIndexVal])).matches("\\-?\\d+") ) {
                            ob[createdByIndexVal]=(userMap.get(Integer.valueOf(String.valueOf(ob[createdByIndexVal]))));
                        }
                        if (!"null".equals(String.valueOf(ob[modifiedByIndexVal])) && ob[modifiedByIndexVal] != null 
                                && (String.valueOf(ob[modifiedByIndexVal])).matches("\\-?\\d+")) {
                            ob[modifiedByIndexVal]=(userMap.get(Integer.valueOf(String.valueOf(ob[modifiedByIndexVal]))));
                        }
                    }
                }
            
            if (tableName.contains(ConstantUtil.CUSTOMER_GTS_ACTUAL) || tableName.contains(ConstantUtil.INVALID_CUSTOMER_GTS_ACTUAL)) {
                Map<Integer, String> userMap = new ConcurrentHashMap<>();
                DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(User.class);
                List<User> userList = UserLocalServiceUtil.dynamicQuery(dynamicQuery);
                for (User user : userList) {
                    userMap.put(Long.valueOf(user.getUserId()).intValue(), user.getFullName());
                }
                for (int i = 0; i < list.size(); i++) {
                        Object[] ob = (Object[]) list.get(i);
                        if (!"null".equals(ob[17]) && ob[17] != null && (String.valueOf(ob[17])).matches("\\-?\\d+")) {
                    ob[17] = userMap.get(Integer.valueOf((String) ob[17]));
                }if (!"null".equals(ob[19]) && ob[19] != null && (String.valueOf(ob[17])).matches("\\-?\\d+")) {
                    ob[19] = userMap.get(Integer.valueOf((String) ob[19]));
                }
                }
            }
            if (tableName.contains(ConstantUtil.VW_CUSTOMER_GTS_FORECAST) || tableName.contains(ConstantUtil.INVALID_GTS_CUSTOMER)) {
                Map<Integer, String> userMap = new ConcurrentHashMap<>();
                
                DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(User.class);
                List<User> userList = UserLocalServiceUtil.dynamicQuery(dynamicQuery);
                for (User user : userList) {
                    userMap.put(Long.valueOf(user.getUserId()).intValue(), user.getFullName());
                }
                for (int i = 0; i < list.size(); i++) {
                        Object[] ob = (Object[]) list.get(i);
                        if (!"null".equals(ob[31]) && ob[31] != null && (String.valueOf(ob[31])).matches("\\-?\\d+")) {
                    ob[31] = userMap.get(Integer.valueOf((String) ob[31]));
                }if (!"null".equals(ob[33]) && ob[33] != null && (String.valueOf(ob[33])).matches("\\-?\\d+")) {
                    ob[33] = userMap.get(Integer.valueOf((String) ob[33]));
                }
                if(tableName.contains(ConstantUtil.VW_CUSTOMER_GTS_FORECAST)){
               ob[8]=(ob[8] !=null && !"null".equals(ob[8]) && !"0".equals(ob[8])  ? getDescription(Integer.valueOf(String.valueOf(ob[8]))) : StringUtils.EMPTY);
               ob[10]=(ob[10] !=null && !"null".equals(ob[10]) && !"0".equals(ob[10])? getDescription(Integer.valueOf(String.valueOf(ob[10]))) : StringUtils.EMPTY);
                ob[9]=(ob[9] !=null  && !"null".equals(ob[9]) && !"0".equals(ob[9])? getDescription(Integer.valueOf(String.valueOf(ob[9]))) : StringUtils.EMPTY);
                 ob[23]=(ob[23] !=null && !"null".equals(ob[23]) && !"0".equals(ob[23]) ? getDescription(Integer.valueOf(String.valueOf(ob[23]))) : StringUtils.EMPTY);
                ob[24]=(ob[24] !=null  && !"null".equals(ob[24]) && !"0".equals(ob[24])? getDescription(Integer.valueOf(String.valueOf(ob[24]))) : StringUtils.EMPTY);
                 ob[25]=(ob[25] !=null && !"null".equals(ob[25]) && !"0".equals(ob[25]) ? getDescription(Integer.valueOf(String.valueOf(ob[25]))) : StringUtils.EMPTY);
                ob[26]=(ob[26] !=null  && !"null".equals(ob[26]) && !"0".equals(ob[26])? getDescription(Integer.valueOf(String.valueOf(ob[26]))) : StringUtils.EMPTY);
               ob[27]=(ob[27] !=null  && !"null".equals(ob[27]) && !"0".equals(ob[27])? getDescription(Integer.valueOf(String.valueOf(ob[27]))) : StringUtils.EMPTY);
                ob[28]=(ob[28] !=null  && !"null".equals(ob[28]) && !"0".equals(ob[28])? getDescription(Integer.valueOf(String.valueOf(ob[28]))) : StringUtils.EMPTY); 
                
                }}
            }
         return list;
        }

    /**
     * Gets the is active.
     *
     * @return the checks if is active
     */
    public List<HelperDTO> getIsActive() throws SystemException, PortalException, Exception {
        LOGGER.info("Entering getIsActive");
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        for (int i = 0; i < 3; i++) {
            HelperDTO dto;
            if (i == 0) {
                dto = new HelperDTO(i, ConstantUtil.SELECT_ONE);
            } else if (i == 1) {
                dto = new HelperDTO(i, ConstantUtil.YES);
            } else {
                dto = new HelperDTO(i,ConstantUtil.NO);
            }
            helperList.add(dto);

        }
        LOGGER.info(" Ends getIsActive with the  helperList size    ::::  " + helperList.size());
        return helperList;
    }

    private int getMasterCount(String moduleName) throws Exception {
        final DynamicQuery companyDynamicQuery = DynamicQueryFactoryUtil.forClass(TransactionModuleMaster.class);
        companyDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantUtil.MODULE_NAME, moduleName));
        return (int) TransactionModuleMasterLocalServiceUtil.dynamicQueryCount(companyDynamicQuery);
    }

    public List<Object> getTabName(String moduleName) throws SystemException {
        LOGGER.info("getTabName  " + moduleName);
        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(TransactionModuleMaster.class);
        dynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantUtil.MODULE_NAME, moduleName));
        dynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantUtil.TAB_NAME,ConstantUtil.SEARCH));
        ProjectionList projectonDetailsList = ProjectionFactoryUtil.projectionList();
        projectonDetailsList.add(ProjectionFactoryUtil.property(ConstantUtil.MASTER_SID));
        projectonDetailsList.add(ProjectionFactoryUtil.property(ConstantUtil.TAB_NAME));
        dynamicQuery.setProjection(projectonDetailsList);
        return TransactionModuleMasterLocalServiceUtil.dynamicQuery(dynamicQuery);
    }

    public List<Object> getDetailsTableByID(int sysId, boolean flag) {
        try {
            DynamicQuery detailsQuery = DynamicQueryFactoryUtil.forClass(TransactionModuleDetails.class);
            detailsQuery = getPrimaryKeyValues(sysId, detailsQuery, flag);
            return (List<Object>) TransactionModuleDetailsLocalServiceUtil.dynamicQuery(detailsQuery);
        } catch (Exception ex) {
            LOGGER.error(ex);
            return null;
        }
    }
    public List getTableName() {
        LOGGER.info("Entering getTableName");
        try {
            List list = new ArrayList();
            list.add(ConstantUtil.SELECT_ONE);
            list.add("Actuals Master");
            list.add("Average Shelf Life");
            list.add("Best Price");
            list.add("CPI Master");
            list.add("Forecast Sales");
            list.add("Formula Details");
            list.add("GL Balance Master");
            list.add("Gl Cost Center Master");
            list.add("Item Hierarchy");
            list.add("Item Hierarchy Definition");
            list.add("Lot Master");
            list.add("Master Data Attribute Master");
            list.add("Sales Master");
            list.add("Demand");
            list.add("Returns");
            list.add("Inventory Withdrawal Summary");
            list.add("Projected GTS–Customer Level");
            list.add("Actual GTS–Customer Level");
            list.add("Accrual Inbound Details");
            return list;
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            return null;
        }

    }

    public void reprocessSelectedRecords(long selectedRecordSid, String invalidTable) {
        String insertSql = StringUtils.EMPTY;
        String processName = StringUtils.EMPTY;
        if (invalidTable.equalsIgnoreCase("IVLD_AVERAGE_SHELF_LIFE")) {
            insertSql = "INSERT INTO STAG_AVERAGE_SHELF_LIFE \n"
                    + "(AVERAGE_SHELF_LIFE_INTFID, ITEM_ID_TYPE,ITEM_ID,AVG_SHELF_LIFE,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,BATCHID,\"SOURCE\",ADD_CHG_DEL_INDICATOR)\n"
                    + "SELECT AVERAGE_SHELF_LIFE_INTFID, ITEM_ID_TYPE, ITEM_ID, AVG_SHELF_LIFE,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,'IVLD_' + REPLACE(BATCH_ID,'IVLD_','') BATCH_ID, \"SOURCE\", ADD_CHG_DEL_INDICATOR\n"
                    + "FROM IVLD_AVERAGE_SHELF_LIFE where  IVLD_AVERAGE_SHELF_LIFE.IVLD_AVERAGE_SHELF_LIFE_SID= " + selectedRecordSid + " ";
            processName = "AVERAGE_SHELF_LIFE_INTERFACE";
        }
        if (invalidTable.equalsIgnoreCase("IVLD_FORECAST_SALES")) {
            insertSql = "INSERT INTO STAG_FORECAST_SALES \n"
                    + "(FORECAST_INTFID,FORECAST_YEAR,FORECAST_MONTH,NDC,FORECAST_START_DATE,UNITS,DOLLARS,FORECAST_VALUE_TYPE,FORECAST_VALUE,PRODUCT,BRAND,SEGMENT,PERCENTAGE_ESTIMATE,\n"
                    + "PERCENTAGE_ESTIMATE_YEAR,ACTUAL_SALES_PERCENTAGE,ACTUAL_SALES_PERCENTAGE_MONTH,FORECASTED_SALES_PERCENTAGE,FORECASTED_SALES_PERCENT_MONTH,FORECAST_VER,\n"
                    + "PRICE,COUNTRY,FORECAST_NAME,FORECAST_DATE,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,BATCH_ID,\"SOURCE\",ADD_CHG_DEL_INDICATOR)\n"
                    + "SELECT FORECAST_INTFID,FORECAST_YEAR,FORECAST_MONTH,NDC,FORECAST_START_DATE,UNITS,DOLLARS,FORECAST_VALUE_TYPE,FORECAST_VALUE,PRODUCT,BRAND,SEGMENT,PERCENTAGE_ESTIMATE,\n"
                    + "PERCENTAGE_ESTIMATE_YEAR,ACTUAL_SALES_PERCENTAGE,ACTUAL_SALES_PERCENTAGE_MONTH,FORECASTED_SALES_PERCENTAGE,FORECASTED_SALES_PERCENT_MONTH,FORECAST_VER,\n"
                    + "PRICE,COUNTRY,FORECAST_NAME,FORECAST_DATE,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,'IVLD_' + REPLACE(BATCH_ID,'IVLD_','') BATCH_ID,\"SOURCE\",ADD_CHG_DEL_INDICATOR\n"
                    + "FROM IVLD_FORECAST_SALES where IVLD_FORECAST_SALES.IVLD_FORECAST_SALES_SID = " + selectedRecordSid + " ";
            processName = "FORECAST_SALES_INTERFACE";
        }
        if (invalidTable.equalsIgnoreCase("IVLD_GL_BALANCE")) {
            insertSql = "INSERT INTO STAG_GL_BALANCE \n"
                    + "(GL_BALANCE_INTFID,ACCOUNT_ID,ACCOUNT_NO,BALANCE,UPLOAD_DATE,IS_ACTIVE,INSERTED_DATE,\"YEAR\", \"PERIOD\", CLOSE_INDICATOR,\n"
                    + "CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE, BATCH_ID, \"SOURCE\", ADD_CHG_DEL_INDICATOR)\n"
                    + "SELECT GL_BALANCE_INTFID,ACCOUNT_ID,ACCOUNT_NO,BALANCE,UPLOAD_DATE,IS_ACTIVE,INSERTED_DATE,\"YEAR\", \"PERIOD\", CLOSE_INDICATOR,\n"
                    + "CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE, 'IVLD_' + REPLACE(BATCH_ID,'IVLD_','') BATCH_ID, \"SOURCE\", ADD_CHG_DEL_INDICATOR\n"
                    + "FROM IVLD_GL_BALANCE where IVLD_GL_BALANCE.IVLD_FORECAST_SALES_SID = " + selectedRecordSid + " ";
            processName = "GL_BALANCE_INTERFACE";
        }
        if (invalidTable.equalsIgnoreCase(ConstantUtil.IVLD_SALES_MASTER)) {
            insertSql = "INSERT INTO STAG_SALES_MASTER \n"
                    + "(SALES_INTFID,SALES_ID,ORGANIZATION_KEY, ITEM_ID,ITEM_NO, ACCOUNT_CODE_QUALIFIER, PARENT_ITEM_ID,PARENT_ITEM_NO, ITEM_UOM,DOC_TYPE,ORDER_NUMBER,INVOICE_NUMBER,\n"
                    + "INVOICE_LINE_NUMBER,INVOICE_DATE, ORDER_TYPE, ORDER_SUBTYPE, ANALYSIS_CODE, PRICE_ADJUSTMENT_NAME, RECORD_SEQUENCE,PRICE, QUANTITY,\n"
                    + "LOT_NO, AMOUNT, CONTRACT_ID, CONTRACT_NO, ACCOUNT_TYPE, CUSTOMER_SUBTYPE, WHOLESALE_OWNER_ID,ACCOUNT_NO, ACCOUNT_NAME, IDENTIFIER_CODE_QUALIFIER,CUSTOMER_COMPANY_CODE,\n"
                    + "IS_ACTIVE,COMPANY_ID,DIVISION_ID, MARKET_ID, BRAND_ID, UPLOAD_DATE,ORDER_RECEIVED_DATE, PROVISION_ID, ACCOUNT_ID, CREATED_BY, CREATED_DATE,\n"
                    + "MODIFIED_BY, MODIFIED_DATE, BATCH_ID, \"SOURCE\", ADD_CHG_DEL_INDICATOR )\n"
                    + "SELECT SALES_INTFID,SALES_ID,ORGANIZATION_KEY, ITEM_ID,ITEM_NO, ACCOUNT_CODE_QUALIFIER, PARENT_ITEM_ID,PARENT_ITEM_NO, ITEM_UOM,DOC_TYPE,ORDER_NUMBER,INVOICE_NUMBER,\n"
                    + "INVOICE_LINE_NUMBER,INVOICE_DATE, ORDER_TYPE, ORDER_SUBTYPE, ANALYSIS_CODE, PRICE_ADJUSTMENT_NAME, RECORD_SEQUENCE,PRICE, QUANTITY,\n"
                    + "LOT_NO, AMOUNT, CONTRACT_ID, CONTRACT_NO, ACCOUNT_TYPE, CUSTOMER_SUBTYPE, WHOLESALE_OWNER_ID,ACCOUNT_NO, ACCOUNT_NAME, IDENTIFIER_CODE_QUALIFIER,CUSTOMER_COMPANY_CODE,\n"
                    + "IS_ACTIVE,COMPANY_ID,DIVISION_ID, MARKET_ID, BRAND_ID, UPLOAD_DATE,ORDER_RECEIVED_DATE, PROVISION_ID, ACCOUNT_ID, CREATED_BY, CREATED_DATE,\n"
                    + "MODIFIED_BY, MODIFIED_DATE, 'IVLD_' + REPLACE(BATCH_ID,'IVLD_','') BATCH_ID, \"SOURCE\", ADD_CHG_DEL_INDICATOR \n"
                    + "FROM IVLD_SALES_MASTER where IVLD_SALES_MASTER.IVLD_SALES_MASTER_SID = " + selectedRecordSid + " ";
            processName = "SALES_MASTER_INTERFACE";
        }
        if (invalidTable.equalsIgnoreCase("IVLD_CPI")) {
            insertSql = "INSERT INTO STAG_CPI \n"
                    + "(CPI_INTFID,INDEX_ID, STATUS, INDEX_TYPE, EFFECTIVE_DATE, INDEX_VALUE, CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE, BATCH_ID, \"SOURCE\", ADD_CHG_DEL_INDICATOR)\n"
                    + "SELECT CPI_INTFID,INDEX_ID, STATUS, INDEX_TYPE, EFFECTIVE_DATE, INDEX_VALUE, CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE, 'IVLD_' + REPLACE(BATCH_ID,'IVLD_','') BATCH_ID, \"SOURCE\", ADD_CHG_DEL_INDICATOR\n"
                    + "FROM IVLD_CPI where IVLD_CPI.IVLD_CPI_SID = " + selectedRecordSid + " ";
            processName = "CPI_INTERFACE";
        }
        if (invalidTable.equalsIgnoreCase("IVLD_GL_COST_CENTER")) {
            insertSql = "INSERT INTO STAG_GL_COST_CENTER \n"
                    + "(GL_COST_CENTER_INTFID, COMPANY_COST_CENTER, COMPANY_CODE, NDC8, UPLOAD_DATE, CREATED_BY, CREATED_DATE,MODIFIED_BY, MODIFIED_DATE, BATCH_ID, \"SOURCE\", ADD_CHG_DEL_INDICATOR)\n"
                    + "SELECT GL_COST_CENTER_INTFID, COMPANY_COST_CENTER, COMPANY_CODE, NDC8, UPLOAD_DATE, CREATED_BY, CREATED_DATE,MODIFIED_BY, MODIFIED_DATE, 'IVLD_' + REPLACE(BATCH_ID,'IVLD_','') BATCH_ID, \"SOURCE\", ADD_CHG_DEL_INDICATOR\n"
                    + "FROM IVLD_GL_COST_CENTER where IVLD_GL_COST_CENTER.IVLD_GL_COST_CENTER_SID = " + selectedRecordSid + " ";
            processName = "GL_COST_CENTER_INTERFACE";
        }
        if (invalidTable.equalsIgnoreCase("IVLD_LOT_MASTER")) {
            insertSql = "INSERT INTO STAG_LOT_MASTER \n"
                    + "(LOT_MASTER_INTFID, ITEM_ID, LOT_NO, LAST_LOT_SOLD_DATE, LOT_EXPIRATION_DATE, CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE,BATCH_ID, \"SOURCE\", ADD_CHG_DEL_INDICATOR)\n"
                    + "SELECT LOT_MASTER_INTFID, ITEM_ID, LOT_NO, LAST_LOT_SOLD_DATE, LOT_EXPIRATION_DATE, CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE,'IVLD_' + REPLACE(BATCH_ID,'IVLD_','') BATCH_ID, \"SOURCE\", ADD_CHG_DEL_INDICATOR\n"
                    + "FROM IVLD_LOT_MASTER where IVLD_LOT_MASTER.IVLD_LOT_MASTER_SID = " + selectedRecordSid + " ";
            processName = "LOT_MASTER_INTERFACE";
        }

        if (invalidTable.equalsIgnoreCase("IVLD_MASTER_DATA_ATTRIBUTE")) {
            insertSql = "INSERT INTO STAG_MASTER_DATA_ATTRIBUTE \n"
                    + "(DATA_ATTRIBUTE_INTFID, MASTER_TYPE, MASTER_ID, MASTER_ATTRIBUTE, COLUMN_1, COLUMN_2, COLUMN_3, COLUMN_4, COLUMN_5, COLUMN_6, COLUMN_7, COLUMN_8, COLUMN_9, COLUMN_10,\n"
                    + "COLUMN_11, COLUMN_12, COLUMN_13, COLUMN_14, COLUMN_15, COLUMN_16, COLUMN_17, COLUMN_18, COLUMN_19, COLUMN_20, CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE, BATCH_ID, \n"
                    + "\"SOURCE\", ADD_CHG_DEL_INDICATOR)\n"
                    + "SELECT DATA_ATTRIBUTE_INTFID, MASTER_TYPE, MASTER_ID, MASTER_ATTRIBUTE, COLUMN_1, COLUMN_2, COLUMN_3, COLUMN_4, COLUMN_5, COLUMN_6, COLUMN_7, COLUMN_8, COLUMN_9, COLUMN_10,\n"
                    + "COLUMN_11, COLUMN_12, COLUMN_13, COLUMN_14, COLUMN_15, COLUMN_16, COLUMN_17, COLUMN_18, COLUMN_19, COLUMN_20, CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE, 'IVLD_' + REPLACE(BATCH_ID,'IVLD_','') BATCH_ID, \n"
                    + "\"SOURCE\", ADD_CHG_DEL_INDICATOR\n"
                    + "FROM IVLD_MASTER_DATA_ATTRIBUTE where IVLD_MASTER_DATA_ATTRIBUTE.IVLD_MASTER_DATA_ATBTE_SID = ;\n"
                    + " " + selectedRecordSid + " ";
            processName = "MASTER_DATA_ATTRIBUTE_INTERFACE";
        }
        if (invalidTable.equalsIgnoreCase("IVLD_BEST_PRICE")) {
            insertSql = "INSERT INTO STAG_BEST_PRICE \n"
                    + "(BEST_PRICE_INTFID, ITEM_ID, ITEM_NO, ITEM_DESC, BEGINNING_WAC_PACKAGE, SELLING_PRICE, INITIAL_BEST_PRICE, INITIAL_DISCOUNT, CONTRACT_NO, CONTRACT_ID, ACCOUNT_ID, CONTRACT_START_DATE, \n"
                    + "CONTRACT_END_DATE, \"PERIOD\", CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE, BATCH_ID, \"SOURCE\", ADD_CHG_DEL_INDICATOR)\n"
                    + "SELECT BEST_PRICE_INTFID, ITEM_ID, ITEM_NO, ITEM_DESC, BEGINNING_WAC_PACKAGE, SELLING_PRICE, INITIAL_BEST_PRICE, INITIAL_DISCOUNT, CONTRACT_NO, CONTRACT_ID, ACCOUNT_ID, CONTRACT_START_DATE, \n"
                    + "CONTRACT_END_DATE, \"PERIOD\", CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE, 'IVLD_' + REPLACE(BATCH_ID,'IVLD_','') BATCH_ID, \"SOURCE\", ADD_CHG_DEL_INDICATOR\n"
                    + "FROM IVLD_BEST_PRICE where IVLD_BEST_PRICE.IVLD_BEST_PRICE_SID = " + selectedRecordSid + " ";
            processName = "BEST_PRICE_INTERFACE";
        }
        if (invalidTable.equalsIgnoreCase("IVLD_ACTUAL_MASTER")) {
            insertSql = "INSERT INTO STAG_ACTUAL_MASTER \n"
                    + "(ACTUAL_INTFID,ACTUAL_ID,CONTRACT_ID,UPLOAD_DATE,PROVISION_ID,ACCRUAL_ACTUAL_START_DATE,ACCRUAL_ACTUAL_END_DATE,ITEM_ID,ITEM_IDENTIFIER_CODE_QUALIFIER,ITEM_NO,SETTLEMENT_METHOD,\n"
                    + "CASH_PAID_DATE,AMOUNT,QUANTITY,QUANTITY_INCLUSION,SETTLEMENT_NO,INVOICE_LINE_NUMBER,ACCOUNT_ID,ACCT_IDENTIFIER_CODE_QUALIFIER,ACCOUNT_NO,ACCOUNT_NAME,ANALYSIS_CODE,IS_ACTIVE,COM_DIV_MKT_BRAND_PROD_KEY,\n"
                    + "PARENTCOM_DIVMKT_BRAND_PRODKEY,PRICE_ADJUSTMENT_NAME,PRICE,RECORD_SEQUENCE,SENT_OUT,ACCRUAL_PROCESSED,DIVISION_ID,MARKET_ID,BRAND_ID,CLAIM_INDICATOR,SALES_AMOUNT,ORGANIZATION_KEY,MANDATED_DISCOUNT_AMOUNT,\n"
                    + "PROVISION_CLAIM_INDICATOR,PROGRAM_STATE_CODE,DISPENSED_DATE,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,BATCH_ID,\"SOURCE\",ADD_CHG_DEL_INDICATOR)\n"
                    + "SELECT ACTUAL_INTFID,ACTUAL_ID,CONTRACT_ID,UPLOAD_DATE,PROVISION_ID,ACCRUAL_ACTUAL_START_DATE,ACCRUAL_ACTUAL_END_DATE,ITEM_ID,ITEM_IDENTIFIER_CODE_QUALIFIER,ITEM_NO,SETTLEMENT_METHOD_NO,\n"
                    + "CASH_PAID_DATE,AMOUNT,QUANTITY,QUANTITY_INCLUSION,SETTLEMENT_NO,INVOICE_LINE_NO,ACCOUNT_ID,ACCT_IDENTIFIER_CODE_QUALIFIER,ACCOUNT_NO,ACCOUNT_NAME,ANALYSIS_CODE,IS_ACTIVE,COM_DIV_MKT_BRAND_PROD_KEY,\n"
                    + "PARENTCOM_DIVMKT_BRAND_PRODKEY,PRICE_ADJUSTMENT_NAME,PRICE,RECORD_SEQUENCE,SENT_OUT,ACCRUAL_PROCESSED,DIVISION_ID,MARKET_ID,BRAND_ID,CLAIM_INDICATOR,SALES_AMOUNT,ORGANIZATION_KEY,MANDATED_DISCOUNT_AMOUNT,\n"
                    + "PROVISION_CLAIM_INDICATOR,PROGRAM_STATE_CODE,DISPENSED_DATE,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,'IVLD_' + REPLACE(BATCH_ID,'IVLD_','') BATCH_ID,\"SOURCE\",ADD_CHG_DEL_INDICATOR\n"
                    + "FROM IVLD_ACTUAL_MASTER where IVLD_ACTUAL_MASTER.IVLD_ACTUAL_MASTER_SID= " + selectedRecordSid + " ";
            processName = "ACTUAL_MASTER_INTERFACE";
        }
        if (invalidTable.equalsIgnoreCase("IVLD_FORMULA_DETAILS")) {
            insertSql = "INSERT INTO STAG_FORMULA_DETAILS \n"
                    + "(FORMULA_DETAILS_INTFID,FORMULA_ID,FORMULA_NO,FORMULA_DESC,COMPANY_ID,ITEM_ID,START_DATE,END_DATE,REBATE_PERCENT_1,REBATE_PERCENT_2,REBATE_PERCENT_3,\n"
                    + "CONTRACT_PRICE_1,CONTRACT_PRICE_2,CONTRACT_PRICE_3,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,BATCH_ID,\"SOURCE\",ADD_CHG_DEL_INDICATOR)\n"
                    + "SELECT FORMULA_DETAILS_INTFID,FORMULA_ID,FORMULA_NO,FORMULA_DESC,COMPANY_ID,ITEM_ID,START_DATE,END_DATE,REBATE_PERCENT_1,REBATE_PERCENT_2,REBATE_PERCENT_3,\n"
                    + "CONTRACT_PRICE_1,CONTRACT_PRICE_2,CONTRACT_PRICE_3,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,'IVLD_' + REPLACE(BATCH_ID,'IVLD_','') BATCH_ID,\"SOURCE\",ADD_CHG_DEL_INDICATOR\n"
                    + "FROM IVLD_FORMULA_DETAILS where IVLD_FORMULA_DETAILS.IVLD_FORMULA_DETAILS_SID= " + selectedRecordSid + " ";
            processName = "FORMULA_DETAILS_INTERFACE";
        }
        if (invalidTable.equalsIgnoreCase("IVLD_ITEM_HIERARCHY")) {
            insertSql = "INSERT INTO STAG_ITEM_HIERARCHY \n"
                    + "(ITEM_HIERARCHY_INTFID,LEVEL0_TAG,LEVEL0,LEVEL0_ALIAS,LEVEL1,LEVEL1_ALIAS,LEVEL2,LEVEL2_ALIAS,LEVEL3,LEVEL3_ALIAS,LEVEL4,LEVEL4_ALIAS,LEVEL5,LEVEL5_ALIAS,LEVEL6,LEVEL6_ALIAS,LEVEL7,\n"
                    + "LEVEL7_ALIAS,LEVEL8,LEVEL8_ALIAS,LEVEL9,LEVEL9_ALIAS,LEVEL10,LEVEL10_ALIAS,LEVEL11,LEVEL11_ALIAS,LEVEL12,LEVEL12_ALIAS,LEVEL13,LEVEL13_ALIAS,LEVEL14,LEVEL14_ALIAS,LEVEL15,LEVEL15_ALIAS,LEVEL16,\n"
                    + "LEVEL16_ALIAS,LEVEL17,LEVEL17_ALIAS,LEVEL18,LEVEL18_ALIAS,LEVEL19,LEVEL19_ALIAS,LEVEL20,LEVEL20_ALIAS,GTN_BRAND,GTN_THERAPEUTIC_CLASS,GTN_COMPANY,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,BATCH_ID,\n"
                    + "\"SOURCE\",ADD_CHG_DEL_INDICATOR)\n"
                    + "SELECT ITEM_HIERARCHY_INTFID,LEVEL0_TAG,LEVEL0,LEVEL0_ALIAS,LEVEL1,LEVEL1_ALIAS,LEVEL2,LEVEL2_ALIAS,LEVEL3,LEVEL3_ALIAS,LEVEL4,LEVEL4_ALIAS,LEVEL5,LEVEL5_ALIAS,LEVEL6,LEVEL6_ALIAS,LEVEL7,\n"
                    + "LEVEL7_ALIAS,LEVEL8,LEVEL8_ALIAS,LEVEL9,LEVEL9_ALIAS,LEVEL10,LEVEL10_ALIAS,LEVEL11,LEVEL11_ALIAS,LEVEL12,LEVEL12_ALIAS,LEVEL13,LEVEL13_ALIAS,LEVEL14,LEVEL14_ALIAS,LEVEL15,LEVEL15_ALIAS,LEVEL16,\n"
                    + "LEVEL16_ALIAS,LEVEL17,LEVEL17_ALIAS,LEVEL18,LEVEL18_ALIAS,LEVEL19,LEVEL19_ALIAS,LEVEL20,LEVEL20_ALIAS,GTN_BRAND,GTN_THERAPEUTIC_CLASS,GTN_COMPANY,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,'IVLD_' + REPLACE(BATCH_ID,'IVLD_','') BATCH_ID,\n"
                    + "\"SOURCE\",ADD_CHG_DEL_INDICATOR\n"
                    + "FROM IVLD_ITEM_HIERARCHY where IVLD_ITEM_HIERARCHY.IVLD_ITEM_HIERARCHY_SID= " + selectedRecordSid + " ";
            processName = "ITEM_HIERARCHY_INTERFACE";
        }
        if (invalidTable.equalsIgnoreCase("IVLD_ITEM_HIERARCHY_DEFINITION")) {
            insertSql = "INSERT INTO STAG_ITEM_HIERARCHY_DEFINITION \n"
                    + "(ITEM_HIERARCHY_DEFN_INTFID,MEMBER,\"ALIAS\",BPI_LVL,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,BATCH_ID,\"SOURCE\",ADD_CHG_DEL_INDICATOR)\n"
                    + "SELECT ITEM_HIERARCHY_DEFN_INTFID,MEMBER,\"ALIAS\",BPI_LVL,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,'IVLD_' + REPLACE(BATCH_ID,'IVLD_','') BATCH_ID,\"SOURCE\",ADD_CHG_DEL_INDICATOR\n"
                    + "FROM IVLD_ITEM_HIERARCHY_DEFINITION where IVLD_ITEM_HIERARCHY_DEFINITION.IVLD_ITEM_HIERARCHY_DEFINITION_SID= " + selectedRecordSid + " ";
            processName = "ITEM_HIERARCHY_DEFINITION_INTERFACE";
        }
        if (invalidTable.equalsIgnoreCase("VW_IVLD_INVENTORY_WD_ACTUAL_PROJ_MAS")) {
            if (ConstantUtil.ACTUALS.equalsIgnoreCase(typeValue)) {
                insertSql = "INSERT INTO STAG_INVENTORY_WD_ACTUAL_MAS \n"
                        + " (INVENTORY_WD_ACTUAL_MAS_INTF_ID,\"YEAR\",\"MONTH\",\"DAY\",WEEK,ITEM_ID,\n"
                        + " UNITS_WITHDRAWN,AMOUNT_WITHDRAWN,UNITS_ON_HAND,AMOUNT_ON_HAND,QUANTITY_ON_ORDER,AMOUNT_ON_ORDER,QUANTITY_RECEIVED,AMOUNT_RECEIVED,CREATED_BY,\n"
                        + " CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,ADD_CHG_DEL_INDICATOR,BATCH_ID,\"SOURCE\",COUNTRY,ORGANIZATION_KEY)\n"
                        + " SELECT INVENTORY_WD_ACTUAL_PROJ_MAS_INTF_ID,\"YEAR\",\"MONTH\",\"DAY\",WEEK,ITEM_ID,\n"
                        + " UNITS_WITHDRAWN,AMOUNT_WITHDRAWN,UNITS_ON_HAND,AMOUNT_ON_HAND,QUANTITY_ON_ORDER,AMOUNT_ON_ORDER,QUANTITY_RECEIVED,AMOUNT_RECEIVED,CREATED_BY,\n"
                        + " CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,ADD_CHG_DEL_INDICATOR,'IVLD_' + REPLACE(BATCH_ID,'IVLD_',''),\"SOURCE\",COUNTRY,ORGANIZATION_KEY\n"
                        + " FROM VW_IVLD_INVENTORY_WD_ACTUAL_PROJ_MAS where VW_IVLD_INVENTORY_WD_ACTUAL_PROJ_MAS.IVLD_INVENTORY_WD_ACTUAL_PROJ_MAS_SID =  " + selectedRecordSid + " AND VW_IVLD_INVENTORY_WD_ACTUAL_PROJ_MAS.IS_FORECAST=" + 0 + " ";
                processName = "INVENTORY_WITHDRAWAL_ACTUAL_INTERFACE";
            } else {

                insertSql = "INSERT INTO STAG_INVENTORY_WD_PROJ_MAS \n"
                        + " (INVENTORY_WD_PROJ_MAS_INTF_ID,\"YEAR\",\"MONTH\",\"DAY\",WEEK,ITEM_ID,\n"
                        + " UNITS_WITHDRAWN,AMOUNT_WITHDRAWN,PRICE,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,ADD_CHG_DEL_INDICATOR,BATCH_ID,\"SOURCE\",\n"
                        + " FORECAST_NAME,FORECAST_VER,COUNTRY,ORGANIZATION_KEY)\n"
                        + " SELECT INVENTORY_WD_ACTUAL_PROJ_MAS_INTF_ID,\"YEAR\",\"MONTH\",\"DAY\",WEEK,ITEM_ID,\n"
                        + " UNITS_WITHDRAWN,AMOUNT_WITHDRAWN,PRICE,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,ADD_CHG_DEL_INDICATOR,"
                        + " 'IVLD_' + REPLACE(BATCH_ID,'IVLD_',''),\"SOURCE\",\n"
                        + " FORECAST_NAME,FORECAST_VER,COUNTRY,ORGANIZATION_KEY\n"
                        + " FROM VW_IVLD_INVENTORY_WD_ACTUAL_PROJ_MAS where VW_IVLD_INVENTORY_WD_ACTUAL_PROJ_MAS.IVLD_INVENTORY_WD_ACTUAL_PROJ_MAS_SID =   " + selectedRecordSid + " AND VW_IVLD_INVENTORY_WD_ACTUAL_PROJ_MAS.IS_FORECAST=" + 1 + " ";
                processName = "INVENTORY_WITHDRAWAL_FORECAST_INTERFACE";
            }
        }
        if (invalidTable.equalsIgnoreCase("VW_IVLD_DEMAND_FORECAST_ACTUAL")) {
            if (ConstantUtil.PROJECTION.equalsIgnoreCase(tableValue1)) {
                insertSql = "INSERT INTO STAG_DEMAND_FORECAST \n"
                        + "(DEMAND_FORECAST_INTERFACE_ID,FORECAST_TYPE,FORECAST_YEAR,FORECAST_MONTH,ITEM_ID,BRAND_ID,SEGMENT,MARKET_SIZE_UNITS,MARKET_SHARE_RATIO,MARKET_SHARE_UNITS,UNCAPTURED_UNITS,UNCAPTURED_UNITS_RATIO,TOTAL_DEMAND_UNITS,\n"
                        + "TOTAL_DEMAND_AMOUNT,INVENTORY_UNIT_CHANGE,GROSS_UNITS,GROSS_PRICE,GROSS_AMOUNT,NET_SALES_PRICE,NET_SALES_AMOUNT,ADD_CHG_DEL_INDICATOR,BATCH_ID,\"SOURCE\",FORECAST_NAME,FORECAST_VER,COUNTRY,ORGANIZATION_KEY)\n"
                        + "SELECT DEMAND_INT_SID,FORECAST_TYPE,FORECAST_YEAR,FORECAST_MONTH,ITEM_ID,BRAND_ID,SEGMENT,MARKET_SIZE_UNITS,MARKET_SHARE_RATIO,MARKET_SHARE_UNITS,UNCAPTURED_UNITS,UNCAPTURED_UNITS_RATIO,TOTAL_DEMAND_UNITS,\n"
                        + "TOTAL_DEMAND_AMOUNT,INVENTORY_UNIT_CHANGE,GROSS_UNITS,GROSS_PRICE,GROSS_AMOUNT,NET_SALES_PRICE,NET_SALES_AMOUNT,ADD_CHG_DEL_INDICATOR,'IVLD_' + REPLACE(BATCH_ID,'IVLD_','') BATCH_ID,\"SOURCE\",FORECAST_NAME,FORECAST_VER,COUNTRY,ORGANIZATION_KEY"
                        + " FROM VW_IVLD_DEMAND_FORECAST_ACTUAL where VW_IVLD_DEMAND_FORECAST_ACTUAL.IVLD_DEMAND_ACTUAL_FORECAST_SID= " + selectedRecordSid + " AND VW_IVLD_DEMAND_FORECAST_ACTUAL.IS_FORECAST=" + 1 + " ";
                processName = "DEMAND_FORECAST_INTERFACE";
            } else {
                insertSql = "INSERT INTO STAG_DEMAND_ACTUAL \n"
                        + "(DEMAND_ACTUAL_INTERFACE_ID,FORECAST_TYPE,FORECAST_YEAR,FORECAST_MONTH,ITEM_ID,BRAND_ID,SEGMENT,MARKET_SIZE_UNITS,MARKET_SHARE_RATIO,MARKET_SHARE_UNITS,TOTAL_DEMAND_UNITS,TOTAL_DEMAND_AMOUNT,\n"
                        + "GROSS_UNITS,GROSS_PRICE,GROSS_AMOUNT,NET_SALES_PRICE,NET_SALES_AMOUNT,ADD_CHG_DEL_INDICATOR,BATCH_ID,\"SOURCE\",COUNTRY,ORGANIZATION_KEY)\n"
                        + "SELECT DEMAND_INT_SID,FORECAST_TYPE,FORECAST_YEAR,FORECAST_MONTH,ITEM_ID,BRAND_ID,SEGMENT,MARKET_SIZE_UNITS,MARKET_SHARE_RATIO,MARKET_SHARE_UNITS,TOTAL_DEMAND_UNITS,TOTAL_DEMAND_AMOUNT,\n"
                        + "GROSS_UNITS,GROSS_PRICE,GROSS_AMOUNT,NET_SALES_PRICE,NET_SALES_AMOUNT,ADD_CHG_DEL_INDICATOR,'IVLD_' + REPLACE(BATCH_ID,'IVLD_','') BATCH_ID,\"SOURCE\",COUNTRY,ORGANIZATION_KEY"
                        + " FROM VW_IVLD_DEMAND_FORECAST_ACTUAL where VW_IVLD_DEMAND_FORECAST_ACTUAL.IVLD_DEMAND_ACTUAL_FORECAST_SID= " + selectedRecordSid + " AND VW_IVLD_DEMAND_FORECAST_ACTUAL.IS_FORECAST=" + 0 + " ";
                processName = "DEMAND_ACTUAL_INTERFACE";
            }
        }
         if (invalidTable.equalsIgnoreCase("VW_IVLD_ADJ_DEMAND_FORE_ACTUAL")) {
            if (ConstantUtil.PROJECTION.equalsIgnoreCase(tableValue1)) {
                insertSql = "\n"
                        + "             INSERT INTO STAG_ADJUSTED_DEMAND_FORECAST \n"
                        + "             (ADJUSTED_DEMAND_FORECAST_INTF_ID,FORECAST_TYPE,YEAR,MONTH,ITEM_ID,\n"
                        + "BRAND_ID,SEGMENT,MARKET_SIZE_UNITS,MARKET_SHARE_RATIO,MARKET_SHARE_UNITS\n"
                        + ",UNCAPTURED_UNITS,UNCAPTURED_UNITS_RATIO,TOTAL_DEMAND_UNITS,TOTAL_DEMAND_AMOUNT,INVENTORY_UNIT_CHANGE,GROSS_UNITS\n"
                        + ",GROSS_PRICE,GROSS_AMOUNT,NET_SALES_PRICE,NET_SALES_AMOUNT,\n"
                        + "ADD_CHG_DEL_INDICATOR,BATCH_ID,SOURCE,FORECAST_NAME\n"
                        + ",FORECAST_VER,COUNTRY,ORGANIZATION_KEY\n"
                        + ",ITEM_IDENTIFIER_CODE_QUALIFIER,ITEM_IDENTIFIER,\n"
                        + "CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE\n"
                        + ") SELECT ADJUSTED_DEMAND_FORECAST_INTF_ID,FORECAST_TYPE,YEAR,MONTH,ITEM_ID,BRAND_ID,SEGMENT\n"
                        + ",MARKET_SIZE_UNITS,MARKET_SHARE_RATIO,MARKET_SHARE_UNITS,UNCAPTURED_UNITS,UNCAPTURED_UNITS_RATIO,TOTAL_ADJUSTED_DEMAND_UNITS\n"
                        + ",TOTAL_ADJUSTED_DEMAND_AMOUNT,INVENTORY_UNIT_CHANGE,GROSS_UNITS,GROSS_PRICE,GROSS_AMOUNT,NET_SALES_PRICE\n"
                        + ",NET_SALES_AMOUNT,ADD_CHG_DEL_INDICATOR\n"
                        + ",'IVLD_' + REPLACE(BATCH_ID,'IVLD_','') BATCH_ID,SOURCE\n"
                        + ",FORECAST_NAME,FORECAST_VERSION,COUNTRY,ORGANIZATION_KEY\n"
                        + ",ITEM_IDENTIFIER_CODE_QUALIFIER,ITEM_IDENTIFIER,\n"
                        + "CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE\n"
                        + "              FROM VW_IVLD_ADJ_DEMAND_FORE_ACTUAL \n"
                        + "where VW_IVLD_ADJ_DEMAND_FORE_ACTUAL.IVLD_ADJUSTED_DEMAND_FORECAST_SID= " + selectedRecordSid + " "
                        + "AND IVLD_ADJUSTED_DEMAND_FORECAST.IS_FORECAST=" + 1;
                processName = "ADJUST_DEMAND_FORECAST_INTERFACE";
            } else {
                insertSql = " INSERT INTO STAG_ADJUSTED_DEMAND_ACTUAL \n"
                        + "             (ADJUSTED_DEMAND_ACTUAL_INTF_ID\n"
                        + ",FORECAST_TYPE,FORECAST_YEAR,FORECAST_MONTH,ITEM_ID,ITEM_IDENTIFIER_CODE_QUALIFIER,ITEM_IDENTIFIER,BRAND_ID,SEGMENT\n"
                        + ",MARKET_SIZE_UNITS,MARKET_SHARE_RATIO,MARKET_SHARE_UNITS,TOTAL_DEMAND_UNITS,TOTAL_DEMAND_AMOUNT,GROSS_UNITS\n"
                        + ",GROSS_PRICE,GROSS_AMOUNT,NET_SALES_PRICE,NET_SALES_AMOUNT,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE\n"
                        + ",ADD_CHG_DEL_INDICATOR,BATCH_ID,SOURCE,COUNTRY\n"
                        + ")\n"
                        + "             SELECT ADJUSTED_DEMAND_FORECAST_INTF_ID,FORECAST_TYPE,YEAR,MONTH,ITEM_ID,ITEM_IDENTIFIER_CODE_QUALIFIER\n"
                        + ",ITEM_IDENTIFIER,BRAND_ID,SEGMENT,MARKET_SIZE_UNITS,MARKET_SHARE_RATIO,MARKET_SHARE_UNITS,TOTAL_ADJUSTED_DEMAND_UNITS\n"
                        + ",TOTAL_ADJUSTED_DEMAND_AMOUNT,GROSS_UNITS,GROSS_PRICE,GROSS_AMOUNT,NET_SALES_PRICE,NET_SALES_AMOUNT,CREATED_BY\n"
                        + ",CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,ADD_CHG_DEL_INDICATOR,"
                        + "'IVLD_' + REPLACE(BATCH_ID,'IVLD_','')BATCH_ID,SOURCE,COUNTRY    FROM VW_IVLD_ADJ_DEMAND_FORE_ACTUAL \n"
                        + "where VW_IVLD_ADJ_DEMAND_FORE_ACTUAL.IVLD_ADJUSTED_DEMAND_FORECAST_SID=  " + selectedRecordSid
                        + "AND IVLD_ADJUSTED_DEMAND_FORECAST.IS_FORECAST=" + 0 + " ";
                processName = "ADJUST_DEMAND_ACTUAL_INTERFACE";
            }
        } 
        if (invalidTable.equalsIgnoreCase("IVLD_RETURNS")) {
            insertSql = "INSERT INTO STAG_RETURNS (RRESERVE_INTERFACE_ID,RRESERVE_ID,VERSION,SKU,"
                    + "BRAND,DESCRIPTION,ORIG_SALE_MONTH,ORIG_SALE_UNITS,ORIG_SALE_DOLLARS,ASP,"
                    + "CUM_RETURN_UNITS,ACTUAL_RETURN_RATE,WEIGHTED_AVG_MONTHS,FIRST_RETURN,"
                    + "LAST_RETURN,MAX_EXPIRED_MONTH,MAX_EXPIRED_MONS_PLUSCUTOFF,RETURN_COMPLETE,"
                    + "CALC_USED,ORIG_SALE_MONTH_CUT_OFF,EXPECTED_RETURN_RATE,ESTIMATED_RETURN_UNITS,"
                    + "ADJ_ESTIMATED_RETURN_UNITS,ADJ_VALUE_AT_ORIG_ASP,POS_ESTIMATED_RETURN_UNITS,"
                    + "VALUE_AT_ORIG_ASP,LOAD_DATE,PAST_EXPIRATION,WITHIN_50QRTILE,MAXIMUM,PCT_75TH,"
                    + "PCT_50TH,PCT_25TH,UPPER_LIMIT,LOWER_LIMIT,ADD_CHG_DEL_INDICATOR,BATCH_ID,\"SOURCE\")\n"
                    + "select RRESERVE_INTERFACE_ID,RRESERVE_ID,VERSION,SKU,BRAND,DESCRIPTION,"
                    + "ORIG_SALE_MONTH,ORIG_SALE_UNITS,ORIG_SALE_DOLLARS,ASP,CUM_RETURN_UNITS,"
                    + "ACTUAL_RETURN_RATE,WEIGHTED_AVG_MONTHS,FIRST_RETURN,LAST_RETURN,MAX_EXPIRED_MONTH,"
                    + "MAX_EXPIRED_MONS_PLUSCUTOFF,RETURN_COMPLETE,CALC_USED,ORIG_SALE_MONTH_CUT_OFF,"
                    + "EXPECTED_RETURN_RATE,ESTIMATED_RETURN_UNITS,ADJ_ESTIMATED_RETURN_UNITS,"
                     + "ADJ_VALUE_AT_ORIG_ASP,POS_ESTIMATED_RETURN_UNITS,VALUE_AT_ORIG_ASP,"
                    + "LOAD_DATE,PAST_EXPIRATION,WITHIN_50QRTILE,MAXIMUM,PCT_75TH,PCT_50TH,PCT_25TH,"
                    + "UPPER_LIMIT,LOWER_LIMIT,ADD_CHG_DEL_INDICATOR,'IVLD_' + REPLACE(BATCH_ID,'IVLD_','') BATCH_ID,\"SOURCE\" from IVLD_RETURNS where IVLD_RETURNS.IVLD_RETURNS_SID = " + selectedRecordSid + " ";
            processName = "RETURNS_INTERFACE";
        }
        if (invalidTable.equalsIgnoreCase("IVLD_CUSTOMER_GTS_FORECAST")) {
            insertSql = "INSERT INTO STAG_CUSTOMER_GTS_FORECAST (CUSTOMER_GTS_FORECAST_INTF_ID,\n" +
                        "FORECAST_YEAR,FORECAST_MONTH,ITEM_ID,COMPANY_ID,UNITS,\n" +
                        "PRICE_TYPE,PRICE,SALES_AMOUNT,SALES_INCLUSION,DEDUCTION_ID,DEDUCTION_CATEGORY,DEDUCTION_TYPE,DEDUCTION_PROGRAM_TYPE,\n" +
                        "ADJUSTMENT_CODE,DEDUCTION_RATE,DEDUCTION_AMOUNT,DEDUCTION_INCLUSION,FORECAST_VALUE_TYPE,\n" +
                        "BRAND,SEGMENT,ADD_CHG_DEL_INDICATOR,FORECAST_VER,COUNTRY,FORECAST_NAME,FORECAST_DATE,UDC1,\n" +
                        "UDC2,UDC3,UDC4,UDC5,UDC6,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,BATCH_ID,\"SOURCE\")\n"
                    +   "Select CUSTOMER_GTS_FORECAST_INTF_ID,\n" +
                        "FORECAST_YEAR,FORECAST_MONTH,ITEM_ID,COMPANY_ID,UNITS,\n" +
                        "PRICE_TYPE,PRICE,SALES_AMOUNT,SALES_INCLUSION,DEDUCTION_ID,DEDUCTION_CATEGORY,DEDUCTION_TYPE,DEDUCTION_PROGRAM_TYPE,\n" +
                        "ADJUSTMENT_CODE,DEDUCTION_RATE,DEDUCTION_AMOUNT,DEDUCTION_INCLUSION ,FORECAST_VALUE_TYPE,\n" +
                        "BRAND,SEGMENT,ADD_CHG_DEL_INDICATOR,FORECAST_VER,COUNTRY,FORECAST_NAME,FORECAST_DATE,UDC1,\n" +
                        "UDC2,UDC3,UDC4,UDC5,UDC6,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,'IVLD_' + REPLACE(BATCH_ID,'IVLD_','') BATCH_ID,\"SOURCE\" from IVLD_CUSTOMER_GTS_FORECAST where IVLD_CUSTOMER_GTS_FORECAST.IVLD_CUSTOMER_GTS_FORECAST_SID = " + selectedRecordSid + " ";
            processName = "CUSTOMER_GTS_FORECAST_INTERFACE";
        }
        if (invalidTable.equalsIgnoreCase("IVLD_CUSTOMER_GTS_ACTUAL")) {
            insertSql = "INSERT INTO STAG_CUSTOMER_GTS_ACTUAL (CUSTOMER_GTS_ACTUAL_INTF_ID,SALES_ID,ORGANIZATION_KEY,ITEM_ID,\n"
                    + "ITEM_UOM,ORDER_NUMBER,INVOICE_NUMBER,INVOICE_LINE_NUMBER,INVOICE_DATE,\n"
                    + "QUANTITY,LOT_NO,AMOUNT,CONTRACT_ID,ACCOUNT_ID,ORDER_RECEIVED_DATE,BATCH_ID,\"SOURCE\",CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,ADD_CHG_DEL_INDICATOR)\n"
                    + "Select  CUSTOMER_GTS_ACTUAL_INTF_ID,SALES_ID,ORGANIZATION_KEY,ITEM_ID,\n"
                    + "ITEM_UOM,ORDER_NUMBER,INVOICE_NUMBER,INVOICE_LINE_NUMBER,INVOICE_DATE,\n"
                    + "QUANTITY,LOT_NO,AMOUNT,CONTRACT_ID,ACCOUNT_ID,ORDER_RECEIVED_DATE,'IVLD_' + REPLACE(BATCH_ID,'IVLD_','') BATCH_ID,\"SOURCE\",CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,ADD_CHG_DEL_INDICATOR from IVLD_CUSTOMER_GTS_ACTUAL where IVLD_CUSTOMER_GTS_ACTUAL.IVLD_CUSTOMER_GTS_ACTUAL_SID = " + selectedRecordSid + " ";
            processName = "CUSTOMER_GTS_ACTUAL_INTERFACE";
        }
        if (invalidTable.equalsIgnoreCase("IVLD_ACCRUAL_INBOUND")) {
            insertSql = "INSERT INTO STAG_ACCRUAL_INBOUND(ACCRUAL_INBOUND_INTERFACE_ID,ACCRUAL_ID,SALES_MASTER_ID,\n"
                    + "GL_ACCOUNT,COMPANY_ID,COMPANY_NO,COMP_IDENT_CODE_QUALIFIER,COMPANY_COST_CENTER,ACCOUNT_ID,\n"
                    + "ACCOUNT_NO,ACCOUNT_NAME,ACCT_IDENT_CODE_QUALIFIER,ITEM_ID,ITEM_NO,ITEM_IDENT_CODE_QUALIFIER,\n"
                    + "CONTRACT_ID,CONTRACT_NO,CONTRACT_NAME,BRAND_ID,CATEGORY_ID,PROVISION_ID,ACCRUAL_TYPE,\n"
                    + "ACCRUAL_PERIOD_START_DATE,ACCRUAL_PERIOD_END_DATE,AMOUNT,SUB_LEDGER,SUB_LEDGER_TYPE,\n"
                    + "DOCUMENT_TYPE,POSTING_DATE,GL_DATE,RECORD_CREATED_DATE,BATCH_ID,SOURCE,CREATED_BY,CREATED_DATE,\n"
                    + "MODIFIED_BY,MODIFIED_DATE,ADD_CHG_DEL_INDICATOR) \n"
                    + "SELECT ACCRUAL_INTFID,ACCRUAL_ID,SALES_MASTER_ID,\n"
                    + "GL_ACCOUNT,COMPANY_ID,COMPANY_NO,COMP_IDENT_CODE_QUALIFIER,COMPANY_COST_CENTER,ACCOUNT_ID,\n"
                    + "ACCOUNT_NO,ACCOUNT_NAME,ACCT_IDENT_CODE_QUALIFIER,ITEM_ID,ITEM_NO,ITEM_IDENT_CODE_QUALIFIER,\n"
                    + "CONTRACT_ID,CONTRACT_NO,CONTRACT_NAME,BRAND_ID,CATEGORY_ID,PROVISION_ID,ACCRUAL_TYPE,\n"
                    + "ACCRUAL_PERIOD_START_DATE,ACCRUAL_PERIOD_END_DATE,AMOUNT,SUB_LEDGER,SUB_LEDGER_TYPE,\n"
                    + "DOCUMENT_TYPE,POSTING_DATE,GL_DATE,RECORD_CREATED_DATE,BATCH_ID,SOURCE,CREATED_BY,CREATED_DATE,\n"
                    + "MODIFIED_BY,MODIFIED_DATE,ADD_CHG_DEL_INDICATOR FROM IVLD_ACCRUAL_INBOUND \n"
                    + "WHERE IVLD_ACCRUAL_INBOUND.IVLD_ACCRUAL_INBOUND_SID = " + selectedRecordSid + " ";
            processName = "ACCRUAL_MASTER_INTERFACE";
        }
        updateFlagForReprocessedOrRemovedRecords(selectedRecordSid);
        HelperTableLocalServiceUtil.executeUpdateQuery(insertSql);
        String interfaceScriptName = InterfaceScripts.getString(processName);
        runJob(getFtpBundleValue(), interfaceScriptName);
    }

    public void updateFlagForReprocessedOrRemovedRecords(long removeId) {
        String sidName = (String) VaadinSession.getCurrent().getAttribute(ConstantUtil.VIEW_SID_NAME);
        String tableName = (String) VaadinSession.getCurrent().getAttribute(ConstantUtil.INVALID_TABLE_NAME);
        if ("VW_IVLD_DEMAND_FORECAST_ACTUAL".equalsIgnoreCase(tableName)) {
            if (ConstantUtil.PROJECTION.equalsIgnoreCase(tableValue1)) {
                tableName = "IVLD_DEMAND_FORECAST";
                sidName = "IVLD_DEMAND_FORECAST_SID";
            } else {
                tableName = "IVLD_DEMAND_ACTUAL";
                sidName = "IVLD_DEMAND_ACTUAL_SID";
            }
        }
        if ("VW_IVLD_ADJ_DEMAND_FORE_ACTUAL".equalsIgnoreCase(tableName)) {
            if (ConstantUtil.PROJECTION.equalsIgnoreCase(tableValue1)) {
                tableName = "IVLD_ADJUSTED_DEMAND_FORECAST";
                sidName = "IVLD_ADJUSTED_DEMAND_FORECAST_SID";
            } else {
                tableName = "IVLD_ADJUSTED_DEMAND_ACTUAL";
                sidName = "IVLD_ADJUSTED_DEMAND_ACTUAL_SID";
            }
        }
        if ("VW_IVLD_INVENTORY_WD_ACTUAL_PROJ_MAS".equalsIgnoreCase(tableName)) {
            if (ConstantUtil.PROJECTION.equalsIgnoreCase(typeValue)) {
                tableName = "IVLD_INVENTORY_WD_PROJ_MAS";
                sidName = "IVLD_INVENTORY_WD_PROJ_MAS_SID";
            } else {
                tableName = "IVLD_INVENTORY_WD_ACTUAL_MAS";
                sidName = "IVLD_INVENTORY_WD_ACTUAL_MAS_SID";
            }
        }
        String query = " Update " + tableName + " set REPROCESSED_FLAG = 'Y' where " + sidName + " = " + removeId;
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
    }

    public void runJob(FtpProperties ftpProperties, String processName) {
        try {
            java.util.Properties prop = getPropertyFile(FtpProperties.FTP_CONFIGURATION_PATH);
            ftpProperties.setScripts(prop.getProperty("scripts"));
            runShellScript(ftpProperties.getScripts(), processName);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    private void runShellScript(String scriptPath, String scriptName) {
        try {
            LOGGER.info("run shell script method");

            String cmd = scriptPath + "/" + scriptName; // this is the command to execute in the Unix shell
           LOGGER.info("cmd----" + cmd);
            ProcessBuilder pb = new ProcessBuilder("bash", "-c", cmd);
            pb.redirectErrorStream(true); // use this to capture messages sent to stderr
            Process shell = pb.start();
            InputStream shellIn = shell.getInputStream(); // this captures the output from the command
            try {
                shellIn.close();
            } catch (IOException ignoreMe) {
              LOGGER.error(ignoreMe.getMessage()); 
            }
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
    }

    public static FtpProperties getFtpBundleValue() {
        FtpProperties ftpProperties = new FtpProperties();
        try {
            java.util.Properties prop = getPropertyFile(FtpProperties.FTP_CONFIGURATION_PATH);
            ftpProperties.setScripts(prop.getProperty("scripts"));
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return ftpProperties;
    }

    private static java.util.Properties getPropertyFile(String bpiPropLoc) {
        java.util.Properties prop = new java.util.Properties();
        try {
            FileInputStream fileIS = null;
            String filePath = "/opt";
            fileIS = new FileInputStream(bpiPropLoc);
            prop.load(fileIS);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return prop;

    }

    public static String getDescription(int code) {
        try {
            HelperTable table = HelperTableLocalServiceUtil.getHelperTable(code);
            return table.getDescription();
        } catch (PortalException ex) {
            LOGGER.error(ex.getMessage());
            return null;
        } catch (SystemException ex) {
            LOGGER.error(ex.getMessage());
            return null;
        }
    }

    public static void setTableValue(final String tableValue) {
        tableValue1 = tableValue;
    }
    public static void setInventoryTableValue(final String tableValue) {
        typeValue = tableValue;
    }

    public static void setAdjustDemand(boolean adjustDemand1) {
       adjustDemand = adjustDemand1;
    }
    public static void setIsMaster(int isMaster1) {
        isMaster = isMaster1;
    }
    public static void setIsForecast(int isForecast1) {
        isForecast = isForecast1;
    }

    public static void setInvalidAdjustedDemand(boolean invalidAdjustedDemand1) {
        invalidAdjustedDemand = invalidAdjustedDemand1;
    }
    public static void setIsForecastDemand(int isForecast) {
        isForecastDemand = isForecast;
    }
    
    String actualQuery(List<String> headList, int index, String querybuilder) {
        String query = querybuilder;
        for (index = 0; index < headList.size(); index++) {
            query = query.replace(headList.get(index) + ",", "");
        }
        return query;
    }

    public List organisationKeyList() {
        List l = new ArrayList();
        String query = " select cm.company_name,cm.COMPANY_MASTER_SID from COMPANY_master cm\n";

        List list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                Object[] obj = (Object[]) list.get(i);
                l.add(String.valueOf(obj[0]));

            }

        }
        return l;

    }

    public void generateOutbound(List Accrualsid) {
        String query = "insert into TEMP_ACCRUAL_DETAILS values (" + Accrualsid.get(0) + ")";
        for (int i = 1; i < Accrualsid.size(); i++) {
            query += ",(" + Accrualsid.get(i) + ")";

        }
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
        String interfaceScriptName = InterfaceScripts.getString("ACCRUAL_OUTBOUND");
        runJob(getFtpBundleValue(), interfaceScriptName);

    }
    }
