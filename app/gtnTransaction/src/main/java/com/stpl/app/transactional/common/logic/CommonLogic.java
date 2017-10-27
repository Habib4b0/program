package com.stpl.app.transactional.common.logic;

import com.stpl.app.model.AccrualMaster;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.TransactionModuleDetails;
import com.stpl.app.model.TransactionModuleMaster;
import com.stpl.app.security.impl.StplSecurity;
import com.stpl.app.service.AccrualMasterLocalServiceUtil;
import com.stpl.app.service.BusinessroleModuleLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.service.TransactionModuleDetailsLocalServiceUtil;
import com.stpl.app.service.TransactionModuleMasterLocalServiceUtil;
import com.stpl.app.transactional.common.dto.DetailsDTO;
import com.stpl.app.transactional.common.dto.FtpProperties;
import com.stpl.app.util.HelperListUtil;
import com.stpl.app.util.ConstantUtil;
import com.stpl.app.util.service.AdjustmentGTNSchedulerSynchronizer;
import com.stpl.app.util.service.AdjustmentReserveSchedulerSynchronizer;
import com.stpl.app.util.service.ArmSchedulerSynchronizer;
import com.stpl.app.util.service.SchedulerSynchronizer;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.OrderFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionList;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.ExtCustomTable;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
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
    static boolean adjustDemand = false;
    static int isMaster = 0;
    static boolean invalidAdjustedDemand = false;
    public static ResourceBundle InterfaceScripts = ResourceBundle.getBundle(ConstantUtil.PROP_INTERFACE_SCRIPTS);
    public List<String> PROPERTY_LIST = Arrays.asList(ConstantUtil.INVENTORY_UNIT_CHANGE, ConstantUtil.UNCAPTURED_UNITS, ConstantUtil.UNCAPTURED_UNITS_RATIO, ConstantUtil.FORECASTNAME, ConstantUtil.FORECASTVER);
    Map<Integer, Boolean> SelectedAccrualsId = new LinkedHashMap<>();
    public List<String> GTS_INVAID_LIST = Arrays.asList(ConstantUtil.CUS_GTS_FORECAST_ID);
    public List<String> ACCURAL_INVAID_LIST = Arrays.asList("COMPANY_QUALIFIER_ID");
    static int isForecast = 0;
    static int isForecastDemand = 0;
    static SimpleDateFormat dateformate = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
    static SimpleDateFormat formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    DecimalFormat decimalformat = new DecimalFormat("$#,###.00");
    DecimalFormat decimalformatper = new DecimalFormat("#0.00");
    DecimalFormat decimalformatNodec = new DecimalFormat("#####");
    DecimalFormat decimalformatdol = new DecimalFormat("$#0.00");
    DecimalFormat format = new DecimalFormat("#,###.##########");
    public final static String FTP_PROPERTIES_PATH = "conf/BPI Configuration/FTPConfiguration.properties";
    Map<Integer, String> userMap = StplSecurity.userMap;
    private final DecimalFormat precisionFormat = new DecimalFormat();

    /**
     * Get the all the fields to be create in dynamic
     *
     * @param moduleName
     * @param tabName
     * @return
     * @throws Exception
     */
    public Object[] getFiledNames(String moduleName, String tabName) throws PortalException, SystemException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        LOGGER.debug("getFieldNames  moduleName =" + moduleName + " tabName=" + tabName);
        int sysId = 0;
        Object[] ob = new Object[NumericConstants.FIVE];
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
        LOGGER.debug("System Id = " + sysId);
        ob[0] = convertToDetailsDto(getDetailsTableByID(sysId, false), obArr[NumericConstants.ONE], 0);
        int tabcount = getMasterCount(moduleName);
        ob[NumericConstants.ONE] = convertToDetailsDto(getDetailsTableByID(sysId, true), obArr[NumericConstants.ONE], tabcount);
        ob[NumericConstants.TWO] = tabcount;
        ob[NumericConstants.THREE] = obArr[NumericConstants.TWO];
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
    public DynamicQuery getPrimaryKeyValues(int sysId, DynamicQuery detailsQuery, boolean flag) {
        detailsQuery.add(RestrictionsFactoryUtil.eq(ConstantUtil.MASTER_SID, sysId));
        if (flag) {
            detailsQuery.add(RestrictionsFactoryUtil.eq(ConstantUtil.CATEGORY_NAME, ConstantUtil.PRIMARY_KEY));
        } else {
            detailsQuery.add(RestrictionsFactoryUtil.ne(ConstantUtil.CATEGORY_NAME, ConstantUtil.PRIMARY_KEY));
        }
        if (!flag && ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL.equalsIgnoreCase(String.valueOf(VaadinSession.getCurrent().getAttribute("getName2")))) {
            detailsQuery.add(RestrictionsFactoryUtil.eq(ConstantUtil.FLAG, ConstantUtil.KEY_N));

        } else if (!ConstantUtil.INVALID_RECORD_COUNT.equalsIgnoreCase(String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.TABLE_NAME1)))) {
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
        List<DetailsDTO> dtoList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            DetailsDTO dto = new DetailsDTO();
            Object[] ob = (Object[]) list.get(i);
            dto.setPropertyName(ob[0] == null ? StringUtils.EMPTY : ob[0].toString());
            dto.setCategoryName(ob[NumericConstants.ONE] == null ? StringUtils.EMPTY : ob[NumericConstants.ONE].toString());
            dto.setDisplayName(ob[NumericConstants.TWO] == null ? StringUtils.EMPTY : ob[NumericConstants.TWO].toString());
            dto.setValidation(ob[NumericConstants.THREE] == null ? StringUtils.EMPTY : ob[NumericConstants.THREE].toString());
            dto.setPropertyIndex(ob[NumericConstants.FOUR] == null ? StringUtils.EMPTY : ob[NumericConstants.FOUR].toString());
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
    public Object[] getValuesById(final int sysID, final Object[] ob) throws SystemException {
        LOGGER.debug("Entering getMasterById with id value  :::: " + sysID);
        StringBuilder query = new StringBuilder();
        query.append("Select ");
        String tableName = StringUtils.EMPTY;
        String queryval = "";
        String invalidtableName = StringUtils.EMPTY;
        HashMap hm = null;
        if (adjustDemand == true) {
            hm = new HashMap();
            hm.put(ConstantUtil.FORECASTVER, ConstantUtil.FORECAST_VERSION);
            hm.put("FORECAST_MONTH", "MONTH");
            hm.put("FORECAST_YEAR", "YEAR");
            hm.put("TOTAL_DEMAND_AMOUNT", "TOTAL_ADJUSTED_DEMAND_AMOUNT");
            hm.put("TOTAL_DEMAND_UNITS", "TOTAL_ADJUSTED_DEMAND_UNITS");
            if (invalidAdjustedDemand) {
                hm.put("DEMAND_INT_SID", "ADJUSTED_DEMAND_FORECAST_INTF_ID");
            } else {
                hm.put("ADJUSTED_DEMAND_FORECAST_INTF_ID", "DEMAND_INT_SID");
            }
        }
        int i = 0;
        for (DetailsDTO moduleDetail : (List<DetailsDTO>) ob[0]) {

            if (!ConstantUtil.BUTTON.equals(moduleDetail.getCategoryName()) && !ConstantUtil.PRIMARY_KEY.equals(moduleDetail.getCategoryName())) {
                tableName = moduleDetail.getTableName();
                if (!(ConstantUtil.ADJUST_DEMAND_VIEW.equals(tableName) || ConstantUtil.DEMAND_VIEW.equals(tableName)
                        || ConstantUtil.VW_IVLD_DEMAND_FPRECAST.equals(tableName) || ConstantUtil.INVALID_ADJUST_DEMAND_VIEW.equals(tableName)) && (adjustDemand == true || invalidAdjustedDemand == true)) {
                    adjustDemand = false;
                    invalidAdjustedDemand = false;
                }
                if (adjustDemand == true) {
                    query.append(hm.get(moduleDetail.getPropertyName()) == null ? moduleDetail.getPropertyName() : hm.get(moduleDetail.getPropertyName())).append(",");
                } else {
                    query.append(moduleDetail.getPropertyName()).append(",");
                }

                i++;
            }
        }

        if (((tableName.equals(ConstantUtil.DEMAND_VIEW) && adjustDemand == false) || (adjustDemand == true)) && tableValue1.equals(ConstantUtil.ACTUALS)) {
                queryval = actualQuery(PROPERTY_LIST, 0, query.toString());
                query.delete(0, query.length());
                query.append(queryval);
        }
        if (ConstantUtil.INVALID_ACCRUAL.equals(String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.INVALID_TABLE_NAME)))) {
            queryval = actualQuery(ACCURAL_INVAID_LIST, 0, query.toString());
            query.delete(0, query.length());
            query.append(queryval);

        }
        query = new StringBuilder(query.substring(0, query.length() - NumericConstants.ONE));
        if (ConstantUtil.INVALID_RECORD_COUNT.equalsIgnoreCase(String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.TABLE_NAME1)))) {
            if (adjustDemand == true && invalidAdjustedDemand == true) {
                invalidtableName = ConstantUtil.INVALID_ADJUST_DEMAND_VIEW;
            } else {
                invalidtableName = String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.INVALID_TABLE_NAME));
            }
            query.append(" from ").append(invalidtableName).append(" where "
                    + String.valueOf(VaadinSession.getCurrent().getAttribute(ConstantUtil.VIEW_SID_NAME))
                    + " = ").append(sysID);
            if (invalidtableName.equals(ConstantUtil.INVALID_INVENTORYVIEW_TABLE_IN_VIEW)) {
                query.append(ConstantUtil.AND_IS_FORECAST).append(isForecast).append(ConstantUtil.AND_IS_MASTER).append(isMaster).append(ConstantUtil.APOSTROPHE);
            }
        } else {
            if (adjustDemand == true && !invalidAdjustedDemand == true) {
                tableName = ConstantUtil.ADJUST_DEMAND_VIEW;
            }
            query.append(" from ").append(tableName).append(" where ");
            if (adjustDemand == true && invalidAdjustedDemand == false) {
                query.append("ADJUSTED_DEMAND_FORECAST_ID");
            } else {
                query.append(((List<DetailsDTO>) ob[NumericConstants.ONE]).get(0).getDisplayName());
            }
            query.append(" = ").append(sysID);
            if (tableName.equals(ConstantUtil.INVENTORYVIEW_TABLE_IN_VIEW)) {
                query.append(ConstantUtil.AND_IS_FORECAST).append(isForecast).append(ConstantUtil.AND_IS_MASTER).append(isMaster).append(ConstantUtil.APOSTROPHE);
            }
            if (tableName.equals(ConstantUtil.DEMAND_VIEW) || tableName.equals(ConstantUtil.ADJUST_DEMAND_VIEW)) {
                query.append(ConstantUtil.AND_IS_FORECAST).append(isForecastDemand).append(ConstantUtil.APOSTROPHE);
            }
        }
        if (tableName.equals(ConstantUtil.ACCURAL_MASTER) && "true".equals(VaadinSession.getCurrent().getAttribute("AccrualDetails")) && !ConstantUtil.INVALID_ACCRUAL.equals(invalidtableName)) {
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
        } else if (invalidtableName.equalsIgnoreCase("IVLD_COMPANY_TRADE_CLASS")) {
            String sql = query.toString();
            sql = sql.replace(",COMPANY_TRADE_CLASS", ",TRADE_CLASS");
            query = new StringBuilder();
            query.append(sql);

        } else if (invalidtableName.equalsIgnoreCase("IVLD_COMPANY_MASTER")) {
            String sql = query.toString();
            sql = sql.replace("ADDRESS1", "ADDRESS_1");
            sql = sql.replace("ADDRESS2", "ADDRESS_2");
            query = new StringBuilder();
            query.append(sql);

        } else if (invalidtableName.equalsIgnoreCase(ConstantUtil.IVLD_ACTUAL_MASTER) || tableName.equalsIgnoreCase("ACTUALS_MASTER")) {
            String sql = query.toString();
            sql = sql.replaceAll("\\bQUANTITY\\b", "Cast(CONVERT(DECIMAL(10,2),QUANTITY) as nvarchar) AS QUANTITY");
            sql = sql.replaceAll("\\bSALES_AMOUNT\\b", "Cast(CONVERT(DECIMAL(10,2),SALES_AMOUNT) as nvarchar) AS SALES_AMOUNT");
            sql = sql.replaceAll("\\bAMOUNT\\b", "Cast(CONVERT(DECIMAL(10,2),AMOUNT) as nvarchar) AS AMOUNT");
            query = new StringBuilder();
            query.append(sql);
        }
        List<Object> objList = (List<Object>) BusinessroleModuleLocalServiceUtil.executeSelectQuery(invalidtableName.equals(ConstantUtil.IVLD_SALES_MASTER) ? query.toString().replace("ITEM_PARENT_NO", "PARENT_ITEM_NO") : query.toString(), null);
        
        if (tableName.contains("VW_INVENTORY_WD_ACTUAL_PROJ_MAS")) {
            if (objList != null && !objList.isEmpty()) {
                Object[] result = (Object[]) objList.get(0);
                result[NumericConstants.ZERO] = result[NumericConstants.ZERO] == null ? 0 : result[NumericConstants.ZERO];
                result[NumericConstants.EIGHT] = result[NumericConstants.EIGHT] == null ? 0 : result[NumericConstants.EIGHT];
                result[NumericConstants.TEN] = result[NumericConstants.TEN] == null ? 0 : result[NumericConstants.TEN];
                result[NumericConstants.ELEVEN] = result[NumericConstants.ELEVEN] == null ? 0 : result[NumericConstants.ELEVEN];
                result[NumericConstants.TWELVE] = result[NumericConstants.TWELVE] == null ? 0 : result[NumericConstants.TWELVE];
                result[NumericConstants.THIRTEEN] = result[NumericConstants.THIRTEEN] == null ? 0 : result[NumericConstants.THIRTEEN];
                result[NumericConstants.FOURTEEN] = result[NumericConstants.FOURTEEN] == null ? 0 : result[NumericConstants.FOURTEEN];
                return result;
            } else {
                return new Object[0];
            }
        } else if (tableName.contains(ConstantUtil.CUSTOMER_GTS_FORECAST)) {
            if (objList != null && !objList.isEmpty()) {
                Object[] result = (Object[]) objList.get(0);
                result[NumericConstants.THIRTEEN] = result[NumericConstants.THIRTEEN] == null ? 0 : result[NumericConstants.THIRTEEN];
                result[NumericConstants.FOURTEEN] = result[NumericConstants.FOURTEEN] == null ? 0 : result[NumericConstants.FOURTEEN];
                result[NumericConstants.SEVENTEEN] = result[NumericConstants.SEVENTEEN] == null ? 0 : result[NumericConstants.SEVENTEEN];
                result[NumericConstants.EIGHTEEN] = result[NumericConstants.EIGHTEEN] == null ? 0 : result[NumericConstants.EIGHTEEN];
                return result;
            } else {
                return new Object[0];
            }
        } else if (tableName.contains("ACCRUAL_MASTER")) {
            if (objList != null && !objList.isEmpty()) {
                Object[] result = (Object[]) objList.get(0);
                if (ConstantUtil.KEY_Y.equals(result[NumericConstants.TWENTY_SEVEN])) {
                    result[NumericConstants.TWENTY_SEVEN] = ConstantUtil.YES;
                } else if (ConstantUtil.KEY_N.equals(result[NumericConstants.TWENTY_SEVEN])) {
                    result[NumericConstants.TWENTY_SEVEN] = ConstantUtil.NO;
                } else {
                    result[NumericConstants.TWENTY_SEVEN] = StringUtils.EMPTY;
                }
            }

        } else if (tableName.contains("FORECASTING_MASTER")) {
            if (objList != null && !objList.isEmpty()) {
                Object[] result = (Object[]) objList.get(0);
                result[NumericConstants.TWO] = result[NumericConstants.TWO] == null ? 0 : result[NumericConstants.TWO];
                result[NumericConstants.FOUR] = result[NumericConstants.FOUR] == null ? 0 : result[NumericConstants.FOUR];
                result[NumericConstants.SIX] = result[NumericConstants.SIX] == null ? 0 : result[NumericConstants.SIX];
                }
        } else if (tableName.contains("CPI_INDEX_MASTER")) {
            if (objList != null && !objList.isEmpty()) {
                Object[] result = (Object[]) objList.get(0);
                result[NumericConstants.ZERO] = result[NumericConstants.ZERO] == null ? 0 : result[NumericConstants.ZERO];
                }
        } else if (tableName.contains("AUDIT_MASTER_INBOUND")) {
            if (objList != null && !objList.isEmpty()) {
                Object[] result = (Object[]) objList.get(0);
                result[NumericConstants.ZERO] = result[NumericConstants.ZERO] == null ? 0 : result[NumericConstants.ZERO];
                result[NumericConstants.SIX] = result[NumericConstants.SIX] == null ? 0 : result[NumericConstants.SIX];
                result[NumericConstants.SEVEN] = result[NumericConstants.SEVEN] == null ? 0 : result[NumericConstants.SEVEN];
                result[NumericConstants.EIGHT] = result[NumericConstants.EIGHT] == null ? 0 : result[NumericConstants.EIGHT];
                result[NumericConstants.TEN] = result[NumericConstants.TEN] == null ? 0 : result[NumericConstants.TEN];
                result[NumericConstants.ELEVEN] = result[NumericConstants.ELEVEN] == null ? 0 : result[NumericConstants.ELEVEN];
                result[NumericConstants.THIRTEEN] = result[NumericConstants.THIRTEEN] == null ? 0 : result[NumericConstants.THIRTEEN];
                result[NumericConstants.FOURTEEN] = result[NumericConstants.FOURTEEN] == null ? 0 : result[NumericConstants.FOURTEEN];
                result[NumericConstants.FIFTEEN] = result[NumericConstants.FIFTEEN] == null ? 0 : result[NumericConstants.FIFTEEN];
                result[NumericConstants.SIXTEEN] = result[NumericConstants.SIXTEEN] == null ? 0 : result[NumericConstants.SIXTEEN];
                result[NumericConstants.SEVENTEEN] = result[NumericConstants.SEVENTEEN] == null ? 0 : result[NumericConstants.SEVENTEEN];
                result[NumericConstants.EIGHTEEN] = result[NumericConstants.EIGHTEEN] == null ? 0 : result[NumericConstants.EIGHTEEN];
                }
        } else if (tableName.contains("GL_BALANCE_MASTER")) {
            if (objList != null && !objList.isEmpty()) {
                Object[] result = (Object[]) objList.get(0);
                result[NumericConstants.FIVE] = result[NumericConstants.FIVE] == null ? 0 : result[NumericConstants.FIVE];
        }

        } else if (tableName.contains("SALES_MASTER")) {
            if (objList != null && !objList.isEmpty()) {
                Object[] result = (Object[]) objList.get(0);
                result[NumericConstants.TEN] = result[NumericConstants.TEN] == null ? 0 : result[NumericConstants.TEN];
                result[NumericConstants.TWENTY] = result[NumericConstants.TWENTY] == null ? 0 : result[NumericConstants.TWENTY];
            }

        } else if (tableName.contains(ConstantUtil.VW_ITEM_MASTER_CAPS)) {
            if (objList != null && !objList.isEmpty()) {
                Object[] result = (Object[]) objList.get(0);
                if (invalidtableName.equals("IVLD_ITEM_MASTER")) {
                    VaadinSession.getCurrent().setAttribute(ConstantUtil.BASE_CPI_PRECISION, result[NumericConstants.SEVENTY_SIX] == null ? 0 : result[NumericConstants.SEVENTY_SIX]);
                    VaadinSession.getCurrent().setAttribute(ConstantUtil.BASELINE_AMP_PRECISION, result[NumericConstants.SEVENTY_SEVEN] == null ? 0 : result[NumericConstants.SEVENTY_SEVEN]);
                } else {
                    VaadinSession.getCurrent().setAttribute(ConstantUtil.BASE_CPI_PRECISION, result[NumericConstants.SEVENTY_FIVE] == null ? 0 : result[NumericConstants.SEVENTY_FIVE]);
                    VaadinSession.getCurrent().setAttribute(ConstantUtil.BASELINE_AMP_PRECISION, result[NumericConstants.SEVENTY_SIX] == null ? 0 : result[NumericConstants.SEVENTY_SIX]);
                }
            }

        } 
        else if (tableName.contains(ConstantUtil.VW_ITEM_PRICING_CAPS)) {
            if (objList != null && !objList.isEmpty()) {
                Object[] result = (Object[]) objList.get(0);
                VaadinSession.getCurrent().setAttribute(ConstantUtil.ITEM_PRICE_PRECISION, result[NumericConstants.SEVENTEEN] == null ? 0 : result[NumericConstants.SEVENTEEN]);
              
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
            if (s.length() > NumericConstants.ONE) {
                sb.append(s.substring(NumericConstants.ONE, s.length()).toLowerCase());
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
            String listName) throws SystemException  {
        BeanItemContainer<HelperDTO> resultContainer = new BeanItemContainer<>(HelperDTO.class);
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
                select.setDescription((String) (select.getValue() == null ? ConstantUtil.SELECT_ONE : ((HelperDTO) select.getValue()).getDescription()));
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
    public List<HelperDTO> getHelperResults(final String listType) throws SystemException{

        final List<HelperDTO> helperList = new ArrayList<>();
        final DynamicQuery cfpDynamicQuery;
        final List list;
        LOGGER.debug("Entering getHelperResults  " + listType);
        if (!listType.isEmpty()) {
            if (ConstantUtil.GL_BALANCE.equals(listType) || ConstantUtil.POSTING_INDICATOR.equals(listType)) {
                helperList.add(new HelperDTO(NumericConstants.FOUR, ConstantUtil.SELECT_ONE));
                helperList.add(new HelperDTO(NumericConstants.ONE, ConstantUtil.YES));
                helperList.add(new HelperDTO(0, ConstantUtil.NO));
            } else if (ConstantUtil.QUANTITY_INCLUSION.equals(listType)) {
                helperList.add(new HelperDTO(NumericConstants.FOUR, ConstantUtil.SELECT_ONE));
                helperList.add(new HelperDTO(0, ConstantUtil.KEY_N));
                helperList.add(new HelperDTO(NumericConstants.ONE, ConstantUtil.KEY_Y));
            } else if (ConstantUtil.RS_CATEGORY.equals(listType) || ConstantUtil.RS_TYPE.equals(listType) || ConstantUtil.REBATE_PROGRAM_TYPE.equals(listType) || ConstantUtil.CFF_TYPE.equals(listType)) {
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
                        helperList.add(new HelperDTO(Integer.parseInt(obj[0].toString()), obj[NumericConstants.ONE].toString()));
                    }
                }
            } else if (ConstantUtil.BUSINESS_UNIT_NAME.equals(listType) || ConstantUtil.COST_CENTER.equals(listType) || ConstantUtil.PROGRAM_TYPE.equals(listType)) {
                cfpDynamicQuery = DynamicQueryFactoryUtil.forClass(AccrualMaster.class);
                list = AccrualMasterLocalServiceUtil.dynamicQuery(cfpDynamicQuery);
                helperList.add(new HelperDTO(0, ConstantUtil.SELECT_ONE));
                if (list != null) {
                    for (int i = 0; i < list.size(); i++) {
                        helperList.add(new HelperDTO(i, list.get(i).toString()));
                    }
                }
            } else if (ConstantUtil.BUSINESS_UNIT_ACCRUAL.equals(listType) || ConstantUtil.GL_COMP.equals(listType)) {
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
                        helperList.add(new HelperDTO(Integer.parseInt(obj[0].toString()), obj[NumericConstants.ONE].toString()));

                    }
                }
            } else if (ConstantUtil.BUSINESS_UNIT.equals(listType)) {
                String query = "SELECT   Distinct CM.COMPANY_MASTER_SID,CM.COMPANY_NAME\n"
                        + "                        FROM   COMPANY_MASTER CM \n"
                        + "                        JOIN HELPER_TABLE HT ON CM.COMPANY_TYPE = HT.HELPER_TABLE_SID\n"
                        + "where  HT.DESCRIPTION = 'BUSINESS UNIT'  AND CM.INBOUND_STATUS <> 'D' "
                        + "                        ORDER BY CM.COMPANY_NAME;  ";
                list = HelperTableLocalServiceUtil.executeSelectQuery(query);
                helperList.add(new HelperDTO(0, ConstantUtil.SELECT_ONE));
                if (list != null) {
                    for (int i = 0; i < list.size(); i++) {
                        Object[] obj = (Object[]) list.get(i);
                        helperList.add(new HelperDTO(Integer.parseInt(obj[0].toString()), obj[NumericConstants.ONE].toString()));

                    }
                }

            } else if (ConstantUtil.COMPANY_COST_CENTER_ACCRUAL.equals(listType)) {

            } else if (ConstantUtil.ADJUSTMENT_TYPE.equals(listType) || ConstantUtil.DEDUCTION_LEVEL.equals(listType)
                    || ConstantUtil.ARM_ACCOUNT_TYPE.equals(listType) || ConstantUtil.ACCOUNT_CATEGORY.equals(listType)) {
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
                        helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable.getDescription()));
                    }
                }
            } else {
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
                        helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable.getDescription()));
                    }
                }
            }
        }
        LOGGER.debug("helperList size  " + helperList.size());
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
            Object[] excelVisibleColumnArr, String tableName, final DetailsDTO primaryDTO)throws PortalException, SystemException, ClassNotFoundException, ParseException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException  {
        SearchLogic sl = new SearchLogic();
        List<Object> list = null;
        
        DateFormat timeFormatExcel = new SimpleDateFormat(ConstantUtil.TIME_FORMAT);
        DateFormat dateFormatExcel = new SimpleDateFormat(ConstantUtil.MMDDYYYY);
        timeFormatExcel.setLenient(false);
      
        if (ConstantUtil.ACTUALS_MASTER.equals(tableName) || ConstantUtil.IVID_ACTUAL_MASTER.equals(tableName)) {
            list = sl.searchFindForActualMaster(searchValues, start, end, null, null, tableName, true, excelVisibleColumnArr, primaryDTO);
        } else if ("IvldSalesMaster".equals(tableName)) {
            list = sl.excelLogicForInvalidSales(searchValues, start, end);
        } else {
            list = sl.searchFind(searchValues, start, end, null, null, tableName, true, excelVisibleColumnArr, SelectedAccrualsId, primaryDTO);
        }
        
        int demandindex = ConstantUtil.DEMANDVIEW_TABLE.equals(tableName) ? NumericConstants.SEVEN : ConstantUtil.IVLD_DEMANDVIEW_TABLE.equals(tableName)
                ? NumericConstants.SIX : NumericConstants.ZERO;

        Object forcastTypeDDLB = searchValues.get("isForecast");
        if (demandindex != NumericConstants.ZERO && (forcastTypeDDLB != null && String.valueOf(forcastTypeDDLB).trim().equals(ConstantUtil.ONE))) {
            for (int i = 0; i < list.size(); i++) {
                Object[] ob = (Object[]) list.get(i);
                if (ConstantUtil.ONE.equals(ob[demandindex])) {
                    ob[demandindex] = ConstantUtil.PROJECTION;
                } else {
                    ob[demandindex] = ConstantUtil.ACTUALS;
                }
            }
        }
        if ("ForecastingMaster".equals(tableName)) { // Sales Forecast
            for (int i = 0; i < list.size(); i++) {
                Object[] ob = (Object[]) list.get(i);
                String dollarValue = new BigDecimal((Double) ob[NumericConstants.TEN]).toPlainString();
                ob[NumericConstants.TEN] = format.format(Double.valueOf(String.valueOf(dollarValue)));
            }
        }

        int index = ConstantUtil.GL_BALANCE_MASTER.equals(tableName) ? NumericConstants.FOUR : ConstantUtil.ACCURAL_MASTER.equals(tableName)
                ? NumericConstants.FOURTEEN : NumericConstants.ZERO;

        if (index != NumericConstants.ZERO) {
            for (int i = 0; i < list.size(); i++) {
                Object[] ob = (Object[]) list.get(i);
                if (ConstantUtil.ONE.equals(ob[index])) {
                    ob[index] = ConstantUtil.YES;
                } else {
                    ob[index] = ConstantUtil.NO;
                }
            }
                }

        if ("CpiIndexMaster".equals(tableName)) {
            for (int i = 0; i < list.size(); i++) {
                Object[] ob = (Object[]) list.get(i);
                if (ob[NumericConstants.ONE] != null && (String.valueOf(ob[NumericConstants.ONE])).matches(ConstantUtil.REGEX)) {
                    ob[NumericConstants.ONE] = CommonLogic.getDescription(Integer.valueOf(String.valueOf(ob[NumericConstants.ONE])));
                }
            }

        }
        if (tableName.contains("IvldReturns")) {
            for (int i = 0; i < list.size(); i++) {
                try {
                    Object[] ob = (Object[]) list.get(i);
                    ob[NumericConstants.NINE] = ob[NumericConstants.NINE] == null ? StringUtils.EMPTY : dateFormatExcel.format(timeFormatExcel.parse(String.valueOf(ob[NumericConstants.NINE])));
                    ob[NumericConstants.SIXTEEN] = ob[NumericConstants.SIXTEEN] == null ? StringUtils.EMPTY : dateFormatExcel.format(timeFormatExcel.parse(String.valueOf(ob[NumericConstants.SIXTEEN])));
                    ob[NumericConstants.SEVENTEEN] = ob[NumericConstants.SEVENTEEN] == null ? StringUtils.EMPTY : dateFormatExcel.format(timeFormatExcel.parse(String.valueOf(ob[NumericConstants.SEVENTEEN])));
                    ob[NumericConstants.EIGHTEEN] = ob[NumericConstants.EIGHTEEN] == null ? StringUtils.EMPTY : dateFormatExcel.format(timeFormatExcel.parse(String.valueOf(ob[NumericConstants.EIGHTEEN])));
                    ob[NumericConstants.NINETEEN] = ob[NumericConstants.NINETEEN] == null ? StringUtils.EMPTY : dateFormatExcel.format(timeFormatExcel.parse(String.valueOf(ob[NumericConstants.NINETEEN])));
                    ob[NumericConstants.THIRTY_FOUR] = ob[NumericConstants.THIRTY_FOUR] == null ? StringUtils.EMPTY : dateFormatExcel.format(timeFormatExcel.parse(String.valueOf(ob[NumericConstants.THIRTY_FOUR])));
                    ob[NumericConstants.THIRTY_EIGHT] = ob[NumericConstants.THIRTY_EIGHT] == null ? StringUtils.EMPTY : dateFormatExcel.format(timeFormatExcel.parse(String.valueOf(ob[NumericConstants.THIRTY_EIGHT])));
                } catch (Exception e) {
                    LOGGER.error("Un parseable Date" + e);
                }
            }
        }
        if (tableName.equals("IvldItemMaster")) {
            for (int i = 0; i < list.size(); i++) {
                try {
                    Object[] ob = (Object[]) list.get(i);
                    ob[NumericConstants.SIXTEEN] = ob[NumericConstants.SIXTEEN] == null ? StringUtils.EMPTY : dateFormatExcel.format(parseDate(String.valueOf(ob[NumericConstants.SIXTEEN])));
                    ob[NumericConstants.THIRTY_TWO] = ob[NumericConstants.THIRTY_TWO] == null ? StringUtils.EMPTY : dateFormatExcel.format(parseDate(String.valueOf(ob[NumericConstants.THIRTY_TWO])));
                    ob[NumericConstants.FORTY] = ob[NumericConstants.FORTY] == null ? StringUtils.EMPTY : dateFormatExcel.format(parseDate(String.valueOf(ob[NumericConstants.FORTY])));
                } catch (Exception e) {
                    LOGGER.error(e);
                }
            }
        }
        if (tableName.equals("ActualsMaster")) {
            for (int i = 0; i < list.size(); i++) {
                try {
                    Object[] ob = (Object[]) list.get(i);
                    ob[NumericConstants.TWELVE] = ob[NumericConstants.TWELVE] == null ? StringUtils.EMPTY : decimalformatper.format(Double.valueOf(ob[NumericConstants.TWELVE].toString()));
                    ob[NumericConstants.THIRTEEN] = ob[NumericConstants.THIRTEEN] == null ? StringUtils.EMPTY : decimalformatper.format(Double.valueOf(ob[NumericConstants.THIRTEEN].toString()));
                    ob[NumericConstants.EIGHTEEN] = ob[NumericConstants.EIGHTEEN] == null ? StringUtils.EMPTY : decimalformatper.format(Double.valueOf(ob[NumericConstants.EIGHTEEN].toString()));
                } catch (NumberFormatException e) {
                    LOGGER.error(e);
                }
            }
        }
        if (tableName.equals("IvldCompanyMaster")) {
            for (int i = 0; i < list.size(); i++) {
                try {
                    Object[] ob = (Object[]) list.get(i);

                    ob[NumericConstants.EIGHT] = ob[NumericConstants.EIGHT] == null ? StringUtils.EMPTY : dateFormatExcel.format(parseDate(String.valueOf(ob[NumericConstants.EIGHT])));
                    ob[NumericConstants.NINE] = ob[NumericConstants.NINE] == null ? StringUtils.EMPTY : dateFormatExcel.format(parseDate(String.valueOf(ob[NumericConstants.NINE])));

                    ob[NumericConstants.TWENTY_FIVE] = ob[NumericConstants.TWENTY_FIVE] == null ? StringUtils.EMPTY : dateFormatExcel.format(parseDate(String.valueOf(ob[NumericConstants.TWENTY_FIVE])));
                } catch (Exception e) {
                    LOGGER.error(e);
                }
            }
        }
        if (tableName.equals("IvldCompanyIdentifier")) {
            for (int i = 0; i < list.size(); i++) {
                try {
                    Object[] ob = (Object[]) list.get(i);
                    ob[NumericConstants.SEVEN] = ob[NumericConstants.SEVEN] == null ? StringUtils.EMPTY : dateFormatExcel.format(parseDate(String.valueOf(ob[NumericConstants.SEVEN])));
                    ob[NumericConstants.EIGHT] = ob[NumericConstants.EIGHT] == null ? StringUtils.EMPTY : dateFormatExcel.format(parseDate(String.valueOf(ob[NumericConstants.EIGHT])));
                } catch (Exception e) {
                    LOGGER.error(e);
                }
            }
        }
        if (tableName.equals("IvldCompanyParent")) {
            for (int i = 0; i < list.size(); i++) {
                try {
                    Object[] ob = (Object[]) list.get(i);
                    ob[NumericConstants.TWO] = ob[NumericConstants.TWO] == null ? StringUtils.EMPTY : dateFormatExcel.format(parseDate(String.valueOf(ob[NumericConstants.TWO])));
                    ob[NumericConstants.THREE] = ob[NumericConstants.THREE] == null ? StringUtils.EMPTY : dateFormatExcel.format(parseDate(String.valueOf(ob[NumericConstants.THREE])));
                    ob[NumericConstants.FOUR] = ob[NumericConstants.FOUR] == null ? StringUtils.EMPTY : dateFormatExcel.format(parseDate(String.valueOf(ob[NumericConstants.FOUR])));
                    ob[NumericConstants.FIVE] = ob[NumericConstants.FIVE] == null ? StringUtils.EMPTY : dateFormatExcel.format(parseDate(String.valueOf(ob[NumericConstants.FIVE])));
                } catch (Exception e) {
                    LOGGER.error(e);
                }
            }
        }
        if (tableName.equals("IvldCompanyTradeClass")) {
            for (int i = 0; i < list.size(); i++) {
                try {
                    Object[] ob = (Object[]) list.get(i);
                    ob[NumericConstants.ONE] = ob[NumericConstants.ONE] == null ? StringUtils.EMPTY : dateFormatExcel.format(parseDate(String.valueOf(ob[NumericConstants.ONE])));
                    ob[NumericConstants.TWO] = ob[NumericConstants.TWO] == null ? StringUtils.EMPTY : dateFormatExcel.format(parseDate(String.valueOf(ob[NumericConstants.TWO])));
                    ob[NumericConstants.FOUR] = ob[NumericConstants.FOUR] == null ? StringUtils.EMPTY : dateFormatExcel.format(parseDate(String.valueOf(ob[NumericConstants.FOUR])));
                    ob[NumericConstants.FIVE] = ob[NumericConstants.FIVE] == null ? StringUtils.EMPTY : dateFormatExcel.format(parseDate(String.valueOf(ob[NumericConstants.FIVE])));
                } catch (Exception e) {
                    LOGGER.error(e);
                }
            }
        }
        if (tableName.equals("IvldItemIdentifier")) {
            for (int i = 0; i < list.size(); i++) {
                try {
                    Object[] ob = (Object[]) list.get(i);
                    ob[NumericConstants.SEVEN] = ob[NumericConstants.SEVEN] == null ? StringUtils.EMPTY : dateFormatExcel.format(parseDate(String.valueOf(ob[NumericConstants.SEVEN])));
                    ob[NumericConstants.EIGHT] = ob[NumericConstants.EIGHT] == null ? StringUtils.EMPTY : dateFormatExcel.format(parseDate(String.valueOf(ob[NumericConstants.EIGHT])));
                } catch (Exception e) {
                    LOGGER.error(e);
                }
            }
        }
        if (tableName.equals("IvldItemPricing")) {
            for (int i = 0; i < list.size(); i++) {
                try {
                    Object[] ob = (Object[]) list.get(i);
                    precisionFormat.applyPattern(SearchLogic.pattern(Integer.valueOf(String.valueOf(VaadinSession.getCurrent().getAttribute("IvldItemPricePrecision")))));
                    ob[NumericConstants.SIX] = ob[NumericConstants.SIX] == null ? StringUtils.EMPTY : "$" + precisionFormat.format(Double.valueOf(String.valueOf(ob[NumericConstants.SIX])));
                    ob[NumericConstants.EIGHT] = ob[NumericConstants.EIGHT] == null ? StringUtils.EMPTY : dateFormatExcel.format(parseDate(String.valueOf(ob[NumericConstants.EIGHT])));
                    ob[NumericConstants.NINE] = ob[NumericConstants.NINE] == null ? StringUtils.EMPTY : dateFormatExcel.format(parseDate(String.valueOf(ob[NumericConstants.NINE])));
                } catch (Exception e) {
                    LOGGER.error(e);
                }
            }
        }
        if (tableName.equals("VwItemMaster")) {
            try {
                for (int i = 0; i < list.size(); i++) {
                    try {
                        Object[] ob = (Object[]) list.get(i);
                        precisionFormat.applyPattern(SearchLogic.pattern(Integer.valueOf(String.valueOf(VaadinSession.getCurrent().getAttribute("BaseCpiPrecision")))));
                        ob[NumericConstants.SIXTY_NINE] = ob[NumericConstants.SIXTY_NINE] == null ? StringUtils.EMPTY : precisionFormat.format(Double.valueOf(String.valueOf(ob[NumericConstants.SIXTY_SEVEN])));
                        ob[NumericConstants.FIFTY_SEVEN] = ob[NumericConstants.FIFTY_SEVEN] == null ? StringUtils.EMPTY : decimalformatdol.format(Double.valueOf(String.valueOf(ob[NumericConstants.FIFTY_SEVEN])));
                        ob[NumericConstants.FIFTY_EIGHT] = ob[NumericConstants.FIFTY_EIGHT] == null ? StringUtils.EMPTY : decimalformatdol.format(Double.valueOf(String.valueOf(ob[NumericConstants.FIFTY_EIGHT])));
                        ob[NumericConstants.FIFTY_NINE] = ob[NumericConstants.FIFTY_NINE] == null ? StringUtils.EMPTY : decimalformatdol.format(Double.valueOf(String.valueOf(ob[NumericConstants.FIFTY_NINE])));
                        precisionFormat.applyPattern(SearchLogic.pattern(Integer.valueOf(String.valueOf(VaadinSession.getCurrent().getAttribute("BaselineAmpPrecision")))));
                        ob[NumericConstants.SIXTY_SEVEN] = ob[NumericConstants.SIXTY_SEVEN] == null ? StringUtils.EMPTY : "$" + precisionFormat.format(Double.valueOf(String.valueOf(ob[NumericConstants.SIXTY_SEVEN])));
                    } catch (Exception e) {
                        LOGGER.error(e);
                    }
                }
            } catch (Exception ex) {
               LOGGER.error(ex);
            }
        }

        if (tableName.equals("VwItemPricing")) {
            try {
                for (int i = 0; i < list.size(); i++) {
                    try {
                        Object[] ob = (Object[]) list.get(i);
                        List<Integer> precisionList = sl.getPrecisionFromItemPricing();
                        Object[] obj = precisionList.toArray();
                        precisionFormat.applyPattern(obj[i] == null ? StringUtils.EMPTY : SearchLogic.pattern(Integer.valueOf(String.valueOf(obj[i]))));
                        ob[NumericConstants.SIX] = ob[NumericConstants.SIX] == null ? StringUtils.EMPTY : "$" + precisionFormat.format(Double.valueOf(String.valueOf(ob[NumericConstants.SIX])));
                    } catch (Exception e) {
                        LOGGER.error(e);
                    }
                }
            } catch (Exception ex) {
               LOGGER.error(ex);
            }
        }

        if (ConstantUtil.IVLD_RETURN_RESERVE.equals(tableName) || ConstantUtil.RETURN_RESERVE.equals(tableName)) {
            try {
                List visibleCOlumnList = Arrays.asList(excelVisibleColumnArr);
                int amountIndexVal = visibleCOlumnList.indexOf("amount");
                int unitIndexVal = visibleCOlumnList.indexOf("units");
                for (int i = 0; i < list.size(); i++) {
                        Object[] ob = (Object[]) list.get(i);

                    ob[amountIndexVal] = (ob[amountIndexVal] != null && String.valueOf(ob[amountIndexVal]).matches("^[1-9]\\d*(\\.\\d+)?$"))
                            ? ob[amountIndexVal] = decimalformatdol.format(Double.valueOf(String.valueOf(ob[amountIndexVal])))
                            : StringUtils.EMPTY;

                    ob[unitIndexVal] = (ob[unitIndexVal] != null && String.valueOf(ob[unitIndexVal]).matches("^[1-9]\\d*(\\.\\d+)?$"))
                            ? ob[unitIndexVal] = decimalformatper.format(Double.valueOf(String.valueOf(ob[unitIndexVal])))
                                       : StringUtils.EMPTY;

                        }
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }

        if (ConstantUtil.ACCRUAL_DETAILS.equals(tableName)) {
            for (int i = 0; i < list.size(); i++) {
                Object[] ob = (Object[]) list.get(i);
                ob[NumericConstants.FOURTEEN] = ConstantUtil.KEY_Y.equals(ob[NumericConstants.FOURTEEN]) ? ConstantUtil.YES
                        : ConstantUtil.KEY_N.equals(ob[NumericConstants.FOURTEEN])
                        ? ConstantUtil.NO : StringUtils.EMPTY;
                }
            }
        if (AllInvalidScreenTables(tableName)
                || AllValidTables(tableName)) {

            Map<Integer, String> userMap = StplSecurity.userMap;
            List visibleCOlumnList = Arrays.asList(excelVisibleColumnArr);
            int createdByIndexVal = visibleCOlumnList.indexOf(ConstantUtil.CREATED_BY);
            int modifiedByIndexVal = visibleCOlumnList.indexOf(ConstantUtil.MODIFIED_BY);

            for (int i = 0; i < list.size(); i++) {
                Object[] ob = (Object[]) list.get(i);
                if (!ConstantUtil.STRING_NULL.equalsIgnoreCase(String.valueOf(ob[createdByIndexVal])) && ob[createdByIndexVal] != null
                        && (String.valueOf(ob[createdByIndexVal])).matches(ConstantUtil.REGEX)  && AllValidTables(tableName)) {
                    ob[createdByIndexVal] = userMap.get(Integer.valueOf(String.valueOf(ob[createdByIndexVal])));
                }
                
                // In Active Created User
                if (!ConstantUtil.STRING_NULL.equalsIgnoreCase(String.valueOf(ob[createdByIndexVal])) && ob[createdByIndexVal] != null
                        && (String.valueOf(ob[createdByIndexVal])).matches(ConstantUtil.REGEX) && AllInvalidScreenTables(tableName)) {
                    ob[createdByIndexVal] = userMap.get(Integer.valueOf(String.valueOf(ob[createdByIndexVal]))) != null
                            ? userMap.get(Integer.valueOf(String.valueOf(ob[createdByIndexVal]))) : ConstantUtil.INACTIVE_USER;
                }
                if (!ConstantUtil.INVALID_ACCURAL_INBOUND.equals(tableName)) {
                    if (!ConstantUtil.STRING_NULL.equals(String.valueOf(ob[modifiedByIndexVal])) && ob[modifiedByIndexVal] != null
                            && (String.valueOf(ob[modifiedByIndexVal])).matches(ConstantUtil.REGEX)) {
                        ob[modifiedByIndexVal] = userMap.get(Integer.valueOf(String.valueOf(ob[modifiedByIndexVal])));
                    }
                }
            }
        }

        if (tableName.contains(ConstantUtil.VW_CUSTOMER_GTS_FORECAST) || tableName.contains(ConstantUtil.INVALID_GTS_CUSTOMER)) {
            HelperListUtil helper = HelperListUtil.getInstance();

            for (int i = 0; i < list.size(); i++) {
                Object[] ob = (Object[]) list.get(i);
                if (tableName.contains(ConstantUtil.VW_CUSTOMER_GTS_FORECAST)) {
                    ob[NumericConstants.EIGHT] = ob[NumericConstants.EIGHT] != null && !"0".equals(ob[NumericConstants.EIGHT]) && !"null".equals(ob[NumericConstants.EIGHT]) ? helper.getHelperDTOByID(Integer.parseInt(String.valueOf(ob[NumericConstants.EIGHT]))).getDescription() : StringUtils.EMPTY;
                    ob[NumericConstants.TEN] = ob[NumericConstants.TEN] != null && !"0".equals(ob[NumericConstants.TEN]) && !"null".equals(ob[NumericConstants.TEN]) ? helper.getHelperDTOByID(Integer.parseInt(String.valueOf(ob[NumericConstants.TEN]))).getDescription() : StringUtils.EMPTY;
                    ob[NumericConstants.NINE] = ob[NumericConstants.NINE] != null && !"0".equals(ob[NumericConstants.NINE]) && !"null".equals(ob[NumericConstants.NINE]) ? helper.getHelperDTOByID(Integer.parseInt(String.valueOf(ob[NumericConstants.NINE]))).getDescription() : StringUtils.EMPTY;
                    ob[NumericConstants.TWENTY_THREE] = (ob[NumericConstants.TWENTY_THREE] != null && !"0".equals(ob[NumericConstants.TWENTY_THREE]) && !"null".equals(ob[NumericConstants.TWENTY_THREE]) && !StringUtils.isEmpty(String.valueOf(ob[NumericConstants.TWENTY_THREE]).trim())) ? helper.getHelperDTOByID(Integer.parseInt(String.valueOf(ob[NumericConstants.TWENTY_THREE]))).getDescription() : StringUtils.EMPTY;
                    ob[NumericConstants.TWENTY_FOUR] = (ob[NumericConstants.TWENTY_FOUR] != null && !"0".equals(ob[NumericConstants.TWENTY_FOUR]) && !"null".equals(ob[NumericConstants.TWENTY_FOUR]) && !StringUtils.isEmpty(String.valueOf(ob[NumericConstants.TWENTY_FOUR]).trim())) ? helper.getHelperDTOByID(Integer.parseInt(String.valueOf(ob[NumericConstants.TWENTY_FOUR]))).getDescription() : StringUtils.EMPTY;
                    ob[NumericConstants.TWENTY_FIVE] = (ob[NumericConstants.TWENTY_FIVE] != null && !"0".equals(ob[NumericConstants.TWENTY_FIVE]) && !"null".equals(ob[NumericConstants.TWENTY_FIVE]) && !StringUtils.isEmpty(String.valueOf(ob[NumericConstants.TWENTY_FIVE]).trim())) ? helper.getHelperDTOByID(Integer.parseInt(String.valueOf(ob[NumericConstants.TWENTY_FIVE]))).getDescription() : StringUtils.EMPTY;
                    ob[NumericConstants.TWENTY_SIX] = (ob[NumericConstants.TWENTY_SIX] != null && !"0".equals(ob[NumericConstants.TWENTY_SIX]) && !"null".equals(ob[NumericConstants.TWENTY_SIX]) && !StringUtils.isEmpty(String.valueOf(ob[NumericConstants.TWENTY_SIX]).trim())) ? helper.getHelperDTOByID(Integer.parseInt(String.valueOf(ob[NumericConstants.TWENTY_SIX]))).getDescription() : StringUtils.EMPTY;
                    ob[NumericConstants.TWENTY_SEVEN] = (ob[NumericConstants.TWENTY_SEVEN] != null && !"0".equals(ob[NumericConstants.TWENTY_SEVEN]) && !"null".equals(ob[NumericConstants.TWENTY_SEVEN]) && !StringUtils.isEmpty(String.valueOf(ob[NumericConstants.TWENTY_SEVEN]).trim())) ? helper.getHelperDTOByID(Integer.parseInt(String.valueOf(ob[NumericConstants.TWENTY_SEVEN]))).getDescription() : StringUtils.EMPTY;
                    ob[NumericConstants.TWENTY_EIGHT] = (ob[NumericConstants.TWENTY_EIGHT] != null && !"0".equals(ob[NumericConstants.TWENTY_EIGHT]) && !"null".equals(ob[NumericConstants.TWENTY_EIGHT]) && !StringUtils.isEmpty(String.valueOf(ob[NumericConstants.TWENTY_EIGHT]).trim())) ? helper.getHelperDTOByID(Integer.parseInt(String.valueOf(ob[NumericConstants.TWENTY_EIGHT]))).getDescription() : StringUtils.EMPTY;

                }
            }
        }
        if (ConstantUtil.ST_CFF_OUTBOUND.equals(tableName)) {

            int listSize = list.size();
            HelperListUtil helper = HelperListUtil.getInstance();
            for (int j = 0; j < listSize; j++) {
                Object[] ob = (Object[]) list.get(j);

                ob[NumericConstants.TWO] = ob[NumericConstants.TWO] != null && !"0".equals(ob[NumericConstants.TWO]) && !"null".equals(ob[NumericConstants.TWO]) ? helper.getHelperDTObyID(Integer.parseInt(String.valueOf(ob[NumericConstants.TWO]))).getDescription() : StringUtils.EMPTY;
                ob[NumericConstants.TEN] = ob[NumericConstants.TEN] != null && !"0".equals(ob[NumericConstants.TEN]) && !"null".equals(ob[NumericConstants.TEN]) ? helper.getHelperDTObyID(Integer.parseInt(String.valueOf(ob[NumericConstants.TEN]))).getDescription() : StringUtils.EMPTY;
                ob[NumericConstants.TWENTY_SIX] = ob[NumericConstants.TWENTY_SIX] != null && !"0".equals(ob[NumericConstants.TWENTY_SIX]) && !"null".equals(ob[NumericConstants.TWENTY_SIX]) ? helper.getHelperDTObyID(Integer.parseInt(String.valueOf(ob[NumericConstants.TWENTY_SIX]))).getDescription() : StringUtils.EMPTY;
                ob[NumericConstants.TWENTY_SEVEN] = ob[NumericConstants.TWENTY_SEVEN] != null && !"0".equals(ob[NumericConstants.TWENTY_SEVEN]) && !"null".equals(ob[NumericConstants.TWENTY_SEVEN]) ? helper.getHelperDTObyID(Integer.parseInt(String.valueOf(ob[NumericConstants.TWENTY_SEVEN]))).getDescription() : StringUtils.EMPTY;
                ob[NumericConstants.TWENTY_EIGHT] = ob[NumericConstants.TWENTY_EIGHT] != null && !"0".equals(ob[NumericConstants.TWENTY_EIGHT]) && !"null".equals(ob[NumericConstants.TWENTY_EIGHT]) ? helper.getHelperDTObyID(Integer.parseInt(String.valueOf(ob[NumericConstants.TWENTY_EIGHT]))).getDescription() : StringUtils.EMPTY;
                ob[NumericConstants.TWENTY_NINE] = ob[NumericConstants.TWENTY_NINE] != null && !"0".equals(ob[NumericConstants.TWENTY_NINE]) && !"null".equals(ob[NumericConstants.TWENTY_NINE]) ? helper.getHelperDTObyID(Integer.parseInt(String.valueOf(ob[NumericConstants.TWENTY_NINE]))).getDescription() : StringUtils.EMPTY;
                ob[NumericConstants.THIRTY] = ob[NumericConstants.THIRTY] != null && !"0".equals(ob[NumericConstants.THIRTY]) && !"null".equals(ob[NumericConstants.THIRTY]) ? helper.getHelperDTObyID(Integer.parseInt(String.valueOf(ob[NumericConstants.THIRTY]))).getDescription() : StringUtils.EMPTY;
                ob[NumericConstants.THIRTY_ONE] = ob[NumericConstants.THIRTY_ONE] != null && !"0".equals(ob[NumericConstants.THIRTY_ONE]) && !"null".equals(ob[NumericConstants.THIRTY_ONE]) ? helper.getHelperDTObyID(Integer.parseInt(String.valueOf(ob[NumericConstants.THIRTY_ONE]))).getDescription() : StringUtils.EMPTY;
                ob[NumericConstants.THIRTY_TWO] = ob[NumericConstants.THIRTY_TWO] != null && !"0".equals(ob[NumericConstants.THIRTY_TWO]) && !"null".equals(ob[NumericConstants.THIRTY_TWO]) ? helper.getHelperDTObyID(Integer.parseInt(String.valueOf(ob[NumericConstants.THIRTY_TWO]))).getDescription() : StringUtils.EMPTY;
                ob[NumericConstants.THIRTY_THREE] = ob[NumericConstants.THIRTY_THREE] != null && !"0".equals(ob[NumericConstants.THIRTY_THREE]) && !"null".equals(ob[NumericConstants.THIRTY_THREE]) ? helper.getHelperDTObyID(Integer.parseInt(String.valueOf(ob[NumericConstants.THIRTY_THREE]))).getDescription() : StringUtils.EMPTY;
                ob[NumericConstants.THIRTY_FOUR] = ob[NumericConstants.THIRTY_FOUR] != null && !"0".equals(ob[NumericConstants.THIRTY_FOUR]) && !"null".equals(ob[NumericConstants.THIRTY_FOUR]) ? helper.getHelperDTObyID(Integer.parseInt(String.valueOf(ob[NumericConstants.THIRTY_FOUR]))).getDescription() : StringUtils.EMPTY;
                ob[NumericConstants.THIRTY_FIVE] = ob[NumericConstants.THIRTY_FIVE] != null && !"0".equals(ob[NumericConstants.THIRTY_FIVE]) && !"null".equals(ob[NumericConstants.THIRTY_FIVE]) ? helper.getHelperDTObyID(Integer.parseInt(String.valueOf(ob[NumericConstants.THIRTY_FIVE]))).getDescription() : StringUtils.EMPTY;

            }
        }


            if (ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL.equals(tableName)) {
                int listSize = list.size();
                for (int j = 0; j < listSize; j++) {
                    Object[] ob = (Object[]) list.get(j);
                    ob[0] = ob[0] != null && !"0".equals(ob[0]) && !"null".equals(ob[0]) ? com.stpl.app.util.HelperListUtil.getInstance().getDescById(Integer.valueOf(ob[0].toString()), ConstantUtil.ADJUSTMENT_TYPE) : StringUtils.EMPTY;
                    ob[NumericConstants.ONE] = ob[NumericConstants.ONE] != null && !"0".equals(ob[NumericConstants.ONE]) && !"null".equals(ob[NumericConstants.ONE]) ? CommonLogic.getDescription(Integer.valueOf(ob[NumericConstants.ONE].toString())) : StringUtils.EMPTY;
                    ob[NumericConstants.TWO] = ob[NumericConstants.TWO] != null && !"0".equals(ob[NumericConstants.TWO]) && !"null".equals(ob[NumericConstants.TWO]) ? CommonLogic.getDescription(Integer.valueOf(ob[NumericConstants.TWO].toString())) : StringUtils.EMPTY;
                    ob[NumericConstants.THREE] = ob[NumericConstants.THREE] != null && !"0".equals(ob[NumericConstants.THREE]) && !"null".equals(ob[NumericConstants.THREE]) ? CommonLogic.getDescription(Integer.valueOf(ob[NumericConstants.THREE].toString())) : StringUtils.EMPTY;
                    ob[NumericConstants.THIRTY_TWO] = ob[NumericConstants.THIRTY_TWO] != null && !"0".equals(ob[NumericConstants.THIRTY_TWO]) && !"null".equals(ob[NumericConstants.THIRTY_TWO]) ? CommonLogic.getDescription(Integer.valueOf(ob[NumericConstants.THIRTY_TWO].toString())) : StringUtils.EMPTY;
                    ob[NumericConstants.THIRTY_THREE] = ob[NumericConstants.THIRTY_THREE] != null && !"0".equals(ob[NumericConstants.THIRTY_THREE]) && !"null".equals(ob[NumericConstants.THIRTY_THREE]) ? CommonLogic.getDescription(Integer.valueOf(ob[NumericConstants.THIRTY_THREE].toString())) : StringUtils.EMPTY;
                    ob[NumericConstants.THIRTY_FOUR] = ob[NumericConstants.THIRTY_FOUR] != null && !"0".equals(ob[NumericConstants.THIRTY_FOUR]) && !"null".equals(ob[NumericConstants.THIRTY_FOUR]) ? CommonLogic.getDescription(Integer.valueOf(ob[NumericConstants.THIRTY_FOUR].toString())) : StringUtils.EMPTY;
                    ob[NumericConstants.THIRTY_FIVE] = ob[NumericConstants.THIRTY_FIVE] != null && !"0".equals(ob[NumericConstants.THIRTY_FIVE]) && !"null".equals(ob[NumericConstants.THIRTY_FIVE]) ? CommonLogic.getDescription(Integer.valueOf(ob[NumericConstants.THIRTY_FIVE].toString())) : StringUtils.EMPTY;
                    ob[NumericConstants.THIRTY_SIX] = ob[NumericConstants.THIRTY_SIX] != null && !"0".equals(ob[NumericConstants.THIRTY_SIX]) && !"null".equals(ob[NumericConstants.THIRTY_SIX]) ? CommonLogic.getDescription(Integer.valueOf(ob[NumericConstants.THIRTY_SIX].toString())) : StringUtils.EMPTY;
                    ob[NumericConstants.THIRTY_SEVEN] = ob[NumericConstants.THIRTY_SEVEN] != null && !"0".equals(ob[NumericConstants.THIRTY_SEVEN]) && !"null".equals(ob[NumericConstants.THIRTY_SEVEN]) ? CommonLogic.getDescription(Integer.valueOf(ob[NumericConstants.THIRTY_SEVEN].toString())) : StringUtils.EMPTY;
                    ob[NumericConstants.THIRTY_EIGHT] = ob[NumericConstants.THIRTY_EIGHT] != null && !"0".equals(ob[NumericConstants.THIRTY_EIGHT]) && !"null".equals(ob[NumericConstants.THIRTY_EIGHT]) ? CommonLogic.getDescription(Integer.valueOf(ob[NumericConstants.THIRTY_EIGHT].toString())) : StringUtils.EMPTY;
                    ob[NumericConstants.THIRTY_NINE] = ob[NumericConstants.THIRTY_NINE] != null && !"0".equals(ob[NumericConstants.THIRTY_NINE]) && !"null".equals(ob[NumericConstants.THIRTY_NINE]) ? CommonLogic.getDescription(Integer.valueOf(ob[NumericConstants.THIRTY_NINE].toString())) : StringUtils.EMPTY;
                    ob[NumericConstants.FORTY] = ob[NumericConstants.FORTY] != null && !"0".equals(ob[NumericConstants.FORTY]) && !"null".equals(ob[NumericConstants.FORTY]) ? CommonLogic.getDescription(Integer.valueOf(ob[NumericConstants.FORTY].toString())) : StringUtils.EMPTY;
                    ob[NumericConstants.FORTY_ONE] = ob[NumericConstants.FORTY_ONE] != null && !"0".equals(ob[NumericConstants.FORTY_ONE]) && !"null".equals(ob[NumericConstants.FORTY_ONE]) ? CommonLogic.getDescription(Integer.valueOf(ob[NumericConstants.FORTY_ONE].toString())) : StringUtils.EMPTY;
                    ob[NumericConstants.FIFTY_THREE] = ob[NumericConstants.FIFTY_THREE] != null && !"0".equals(ob[NumericConstants.FIFTY_THREE]) && !"null".equals(ob[NumericConstants.FIFTY_THREE]) ? CommonLogic.getDescription(Integer.valueOf(ob[NumericConstants.FIFTY_THREE].toString())) : StringUtils.EMPTY;
                    ob[NumericConstants.FIFTY_FOUR] = ob[NumericConstants.FIFTY_FOUR] != null && !"0".equals(ob[NumericConstants.FIFTY_FOUR]) && !"null".equals(ob[NumericConstants.FIFTY_FOUR]) ? CommonLogic.getDescription(Integer.valueOf(ob[NumericConstants.FIFTY_FOUR].toString())) : StringUtils.EMPTY;
                    ob[NumericConstants.FIFTY_FIVE] = ob[NumericConstants.FIFTY_FIVE] != null && !"0".equals(ob[NumericConstants.FIFTY_FIVE]) && !"null".equals(ob[NumericConstants.FIFTY_FIVE]) ? CommonLogic.getDescription(Integer.valueOf(ob[NumericConstants.FIFTY_FIVE].toString())) : StringUtils.EMPTY;
                    ob[NumericConstants.FIFTY_SIX] = ob[NumericConstants.FIFTY_SIX] != null && !"0".equals(ob[NumericConstants.FIFTY_SIX]) && !"null".equals(ob[NumericConstants.FIFTY_SIX]) ? CommonLogic.getDescription(Integer.valueOf(ob[NumericConstants.FIFTY_SIX].toString())) : StringUtils.EMPTY;
                    ob[NumericConstants.FIFTY_SEVEN] = ob[NumericConstants.FIFTY_SEVEN] != null && !"0".equals(ob[NumericConstants.FIFTY_SEVEN]) && !"null".equals(ob[NumericConstants.FIFTY_SEVEN]) ? CommonLogic.getDescription(Integer.valueOf(ob[NumericConstants.FIFTY_SEVEN].toString())) : StringUtils.EMPTY;
                    ob[NumericConstants.FIFTY_EIGHT] = ob[NumericConstants.FIFTY_EIGHT] != null && !"0".equals(ob[NumericConstants.FIFTY_EIGHT]) && !"null".equals(ob[NumericConstants.FIFTY_EIGHT]) ? CommonLogic.getDescription(Integer.valueOf(ob[NumericConstants.FIFTY_EIGHT].toString())) : StringUtils.EMPTY;

                }
            } else if (ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL.equals(tableName)) {
                int listSize = list.size();
                for (int j = 0; j < listSize; j++) {
                    Object[] ob = (Object[]) list.get(j);
                    ob[0] = ob[0] != null && !"0".equals(ob[0]) && !"null".equals(ob[0]) ? com.stpl.app.util.HelperListUtil.getInstance().getDescById(Integer.valueOf(ob[0].toString()), ConstantUtil.ADJUSTMENT_TYPE) : StringUtils.EMPTY;
                    ob[NumericConstants.ONE] = ob[NumericConstants.ONE] != null && !"0".equals(ob[NumericConstants.ONE]) && !"null".equals(ob[NumericConstants.ONE]) ? String.valueOf(ob[NumericConstants.ONE]) : StringUtils.EMPTY;
                    ob[NumericConstants.TWO] = ob[NumericConstants.TWO] != null && !"0".equals(ob[NumericConstants.TWO]) && !"null".equals(ob[NumericConstants.TWO]) ? String.valueOf(ob[NumericConstants.TWO]) : StringUtils.EMPTY;
                    ob[NumericConstants.THREE] = ob[NumericConstants.THREE] != null && !"0".equals(ob[NumericConstants.THREE]) && !"null".equals(ob[NumericConstants.THREE]) ? String.valueOf(ob[NumericConstants.THREE]) : StringUtils.EMPTY;
                    ob[NumericConstants.TEN] = ob[NumericConstants.TEN] != null && !"0".equals(ob[NumericConstants.TEN]) && !"null".equals(ob[NumericConstants.TEN]) ? CommonLogic.getDescription(Integer.valueOf(ob[NumericConstants.TEN].toString())) : StringUtils.EMPTY;
                    ob[NumericConstants.THIRTY] = ob[NumericConstants.THIRTY] != null && !"0".equals(ob[NumericConstants.THIRTY]) && !"null".equals(ob[NumericConstants.THIRTY]) ? getDescription(Integer.valueOf(ob[NumericConstants.THIRTY].toString())) : StringUtils.EMPTY;
                    ob[NumericConstants.THIRTY_ONE] = ob[NumericConstants.THIRTY_ONE] != null && !"0".equals(ob[NumericConstants.THIRTY_ONE]) && !"null".equals(ob[NumericConstants.THIRTY_ONE]) ? getDescription(Integer.valueOf(ob[NumericConstants.THIRTY_ONE].toString())) : StringUtils.EMPTY;
                    ob[NumericConstants.THIRTY_TWO] = ob[NumericConstants.THIRTY_TWO] != null && !"0".equals(ob[NumericConstants.THIRTY_TWO]) && !"null".equals(ob[NumericConstants.THIRTY_TWO]) ? CommonLogic.getDescription(Integer.valueOf(ob[NumericConstants.THIRTY_TWO].toString())) : StringUtils.EMPTY;
                    ob[NumericConstants.THIRTY_THREE] = ob[NumericConstants.THIRTY_THREE] != null && !"0".equals(ob[NumericConstants.THIRTY_THREE]) && !"null".equals(ob[NumericConstants.THIRTY_THREE]) ? CommonLogic.getDescription(Integer.valueOf(ob[NumericConstants.THIRTY_THREE].toString())) : StringUtils.EMPTY;
                    ob[NumericConstants.THIRTY_FOUR] = ob[NumericConstants.THIRTY_FOUR] != null && !"0".equals(ob[NumericConstants.THIRTY_FOUR]) && !"null".equals(ob[NumericConstants.THIRTY_FOUR]) ? CommonLogic.getDescription(Integer.valueOf(ob[NumericConstants.THIRTY_FOUR].toString())) : StringUtils.EMPTY;
                    ob[NumericConstants.THIRTY_FIVE] = ob[NumericConstants.THIRTY_FIVE] != null && !"0".equals(ob[NumericConstants.THIRTY_FIVE]) && !"null".equals(ob[NumericConstants.THIRTY_FIVE]) ? CommonLogic.getDescription(Integer.valueOf(ob[NumericConstants.THIRTY_FIVE].toString())) : StringUtils.EMPTY;
                    ob[NumericConstants.THIRTY_SIX] = ob[NumericConstants.THIRTY_SIX] != null && !"0".equals(ob[NumericConstants.THIRTY_SIX]) && !"null".equals(ob[NumericConstants.THIRTY_SIX]) ? CommonLogic.getDescription(Integer.valueOf(ob[NumericConstants.THIRTY_SIX].toString())) : StringUtils.EMPTY;
                    ob[NumericConstants.THIRTY_SEVEN] = ob[NumericConstants.THIRTY_SEVEN] != null && !"0".equals(ob[NumericConstants.THIRTY_SEVEN]) && !"null".equals(ob[NumericConstants.THIRTY_SEVEN]) ? CommonLogic.getDescription(Integer.valueOf(ob[NumericConstants.THIRTY_SEVEN].toString())) : StringUtils.EMPTY;
                    ob[NumericConstants.THIRTY_EIGHT] = ob[NumericConstants.THIRTY_EIGHT] != null && !"0".equals(ob[NumericConstants.THIRTY_EIGHT]) && !"null".equals(ob[NumericConstants.THIRTY_EIGHT]) ? CommonLogic.getDescription(Integer.valueOf(ob[NumericConstants.THIRTY_EIGHT].toString())) : StringUtils.EMPTY;
                    
                }
            }
            
            return list;
    }

    /**
     * Gets the is active.
     *
     * @return the checks if is active
     */
    public List<HelperDTO> getIsActive() throws SystemException, PortalException {
        LOGGER.debug("Entering getIsActive");
        final List<HelperDTO> helperList = new ArrayList<>();
        for (int i = 0; i < NumericConstants.THREE; i++) {
            HelperDTO dto;
            if (i == 0) {
                dto = new HelperDTO(i, ConstantUtil.SELECT_ONE);
            } else if (i == NumericConstants.ONE) {
                dto = new HelperDTO(i, ConstantUtil.YES);
            } else {
                dto = new HelperDTO(i, ConstantUtil.NO);
            }
            helperList.add(dto);

        }
        LOGGER.debug(" Ends getIsActive with the  helperList size    ::::  " + helperList.size());
        return helperList;
    }

    private int getMasterCount(String moduleName) throws SystemException  {
        final DynamicQuery companyDynamicQuery = DynamicQueryFactoryUtil.forClass(TransactionModuleMaster.class);
        companyDynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantUtil.MODULE_NAME, moduleName));
        return (int) TransactionModuleMasterLocalServiceUtil.dynamicQueryCount(companyDynamicQuery);
    }

    public List<Object> getTabName(String moduleName) throws SystemException {
        LOGGER.debug("getTabName  " + moduleName);
        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(TransactionModuleMaster.class);
        dynamicQuery.add(RestrictionsFactoryUtil.eq(ConstantUtil.MODULE_NAME, moduleName));
        dynamicQuery.add(RestrictionsFactoryUtil.ne(ConstantUtil.TAB_NAME, ConstantUtil.SEARCH));
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
        LOGGER.debug("Entering getTableName");
        try {
            List list = new ArrayList();
            list.add(ConstantUtil.SELECT_ONE);
            list.add("Demand");
            list.add("GTS Forecast");
            list.add("Sales Forecast");
            list.add("GTS Actual");
            list.add("Inventory Withdrawals");
            list.add("Payments");
            list.add("Returns");
            list.add("Average Shelf Life");
            list.add("Lot Master");
            list.add("CPI");
            list.add("Sales Master");
            list.add("Global Files Company Master");
            list.add("Global Files Company Identifier");
            list.add("Global Files Company Parent");
            list.add("Global Files Company Trade Class");
            list.add("Global Files Item Master");
            list.add("Global Files Item Identifier");
            list.add("Global Files Item Pricing");
            list.add("GL Cost Center");
            list.add("Accrual Details");
            list.add("GL Balance");
            Collections.sort(list);
            return list;
        } catch (Exception ex) {
            LOGGER.error(ex);
            return Collections.emptyList();
        }

    }

    public void reprocessSelectedRecords(String invalidTable,String stagingTable,List<Integer> selectedRecords, String viewNameSid) {
        String insertSql = StringUtils.EMPTY;
        String processName = StringUtils.EMPTY;
        SchedulerSynchronizer process = SchedulerSynchronizer.getInstance(ConstantUtil.TRANSACTION);
        ArmSchedulerSynchronizer arpProcess = ArmSchedulerSynchronizer.getInstance(ConstantUtil.TRANSACTION);
        AdjustmentGTNSchedulerSynchronizer GtnProcess = AdjustmentGTNSchedulerSynchronizer.getInstance(ConstantUtil.TRANSACTION);
        AdjustmentReserveSchedulerSynchronizer reserveProcess = AdjustmentReserveSchedulerSynchronizer.getInstance(ConstantUtil.TRANSACTION);
        if (!ConstantUtil.ST_CFF_OUTBOUND.equals(invalidTable) && !invalidTable.equalsIgnoreCase(ConstantUtil.ST_ARP_OUTBOUND)
                && !ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL.equals(invalidTable) && !ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL.equals(invalidTable)) {
            if (invalidTable.equalsIgnoreCase("IVLD_AVERAGE_SHELF_LIFE")) {
                insertSql = "INSERT INTO STAG_AVERAGE_SHELF_LIFE \n"
                        + "(AVERAGE_SHELF_LIFE_INTFID, ITEM_ID_TYPE,ITEM_ID,AVG_SHELF_LIFE,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,BATCHID,\"SOURCE\",ADD_CHG_DEL_INDICATOR)\n"
                        + "SELECT AVERAGE_SHELF_LIFE_INTFID, ITEM_ID_TYPE, ITEM_ID, AVG_SHELF_LIFE,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,'IVLD_' + REPLACE(BATCH_ID,'IVLD_','') BATCH_ID, \"SOURCE\", ADD_CHG_DEL_INDICATOR\n"
                        + "FROM IVLD_AVERAGE_SHELF_LIFE where  IVLD_AVERAGE_SHELF_LIFE.CHECK_RECORD = 1; ";
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
                        + "FROM IVLD_FORECAST_SALES where IVLD_FORECAST_SALES.CHECK_RECORD = 1;";
                processName = "FORECAST_SALES_INTERFACE";
            }
            if (invalidTable.equalsIgnoreCase("IVLD_GL_BALANCE")) {
                insertSql = "INSERT INTO STAG_GL_BALANCE \n"
                        + "(GL_BALANCE_INTFID,ACCOUNT_ID,ACCOUNT_NO,BALANCE,UPLOAD_DATE,IS_ACTIVE,INSERTED_DATE,\"YEAR\", \"PERIOD\", CLOSE_INDICATOR,\n"
                        + "CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE, BATCH_ID, \"SOURCE\", ADD_CHG_DEL_INDICATOR)\n"
                        + "SELECT GL_BALANCE_INTFID,ACCOUNT_ID,ACCOUNT_NO,BALANCE,UPLOAD_DATE,IS_ACTIVE,INSERTED_DATE,\"YEAR\", \"PERIOD\", CLOSE_INDICATOR,\n"
                        + "CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE, 'IVLD_' + REPLACE(BATCH_ID,'IVLD_','') BATCH_ID, \"SOURCE\", ADD_CHG_DEL_INDICATOR\n"
                        + "FROM IVLD_GL_BALANCE where IVLD_GL_BALANCE.CHECK_RECORD = 1";
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
                        + "FROM IVLD_SALES_MASTER where IVLD_SALES_MASTER.CHECK_RECORD = 1";
                processName = "SALES_MASTER_INTERFACE";
            }
            if (invalidTable.equalsIgnoreCase("IVLD_CPI")) {
                insertSql = "INSERT INTO STAG_CPI \n"
                        + "(CPI_INTFID,INDEX_ID, STATUS, INDEX_TYPE, EFFECTIVE_DATE, INDEX_VALUE, CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE, BATCH_ID, \"SOURCE\", ADD_CHG_DEL_INDICATOR)\n"
                        + "SELECT CPI_INTFID,INDEX_ID, STATUS, INDEX_TYPE, EFFECTIVE_DATE, INDEX_VALUE, CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE, 'IVLD_' + REPLACE(BATCH_ID,'IVLD_','') BATCH_ID, \"SOURCE\", ADD_CHG_DEL_INDICATOR\n"
                        + "FROM IVLD_CPI where IVLD_CPI.CHECK_RECORD = 1";
                processName = "CPI_INTERFACE";
            }
            if (invalidTable.equalsIgnoreCase("IVLD_GL_COST_CENTER")) {
                insertSql = "INSERT INTO STAG_GL_COST_CENTER \n"
                        + "(GL_COST_CENTER_INTFID, COMPANY_COST_CENTER, COMPANY_CODE, NDC8, UPLOAD_DATE, CREATED_BY, CREATED_DATE,MODIFIED_BY, MODIFIED_DATE, BATCH_ID, \"SOURCE\", ADD_CHG_DEL_INDICATOR)\n"
                        + "SELECT GL_COST_CENTER_INTFID, COMPANY_COST_CENTER, COMPANY_CODE, NDC8, UPLOAD_DATE, CREATED_BY, CREATED_DATE,MODIFIED_BY, MODIFIED_DATE, 'IVLD_' + REPLACE(BATCH_ID,'IVLD_','') BATCH_ID, \"SOURCE\", ADD_CHG_DEL_INDICATOR\n"
                        + "FROM IVLD_GL_COST_CENTER where IVLD_GL_COST_CENTER.CHECK_RECORD = 1";
                processName = "GL_COST_CENTER_INTERFACE";
            }
            if (invalidTable.equalsIgnoreCase("IVLD_LOT_MASTER")) {
                insertSql = "INSERT INTO STAG_LOT_MASTER \n"
                        + "(LOT_MASTER_INTFID, ITEM_ID, LOT_NO, LAST_LOT_SOLD_DATE, LOT_EXPIRATION_DATE, CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE,BATCH_ID, \"SOURCE\", ADD_CHG_DEL_INDICATOR)\n"
                        + "SELECT LOT_MASTER_INTFID, ITEM_ID, LOT_NO, LAST_LOT_SOLD_DATE, LOT_EXPIRATION_DATE, CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE,'IVLD_' + REPLACE(BATCH_ID,'IVLD_','') BATCH_ID, \"SOURCE\", ADD_CHG_DEL_INDICATOR\n"
                        + "FROM IVLD_LOT_MASTER where IVLD_LOT_MASTER.CHECK_RECORD = 1";
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
                        + "FROM IVLD_MASTER_DATA_ATTRIBUTE where IVLD_MASTER_DATA_ATTRIBUTE.CHECK_RECORD = 1;";
                processName = "MASTER_DATA_ATTRIBUTE_INTERFACE";
            }
            if (invalidTable.equalsIgnoreCase("IVLD_BEST_PRICE")) {
                insertSql = "INSERT INTO STAG_BEST_PRICE \n"
                        + "(BEST_PRICE_INTFID, ITEM_ID, ITEM_NO, ITEM_DESC, BEGINNING_WAC_PACKAGE, SELLING_PRICE, INITIAL_BEST_PRICE, INITIAL_DISCOUNT, CONTRACT_NO, CONTRACT_ID, ACCOUNT_ID, CONTRACT_START_DATE, \n"
                        + "CONTRACT_END_DATE, \"PERIOD\", CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE, BATCH_ID, \"SOURCE\", ADD_CHG_DEL_INDICATOR)\n"
                        + "SELECT BEST_PRICE_INTFID, ITEM_ID, ITEM_NO, ITEM_DESC, BEGINNING_WAC_PACKAGE, SELLING_PRICE, INITIAL_BEST_PRICE, INITIAL_DISCOUNT, CONTRACT_NO, CONTRACT_ID, ACCOUNT_ID, CONTRACT_START_DATE, \n"
                        + "CONTRACT_END_DATE, \"PERIOD\", CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE, 'IVLD_' + REPLACE(BATCH_ID,'IVLD_','') BATCH_ID, \"SOURCE\", ADD_CHG_DEL_INDICATOR\n"
                        + "FROM IVLD_BEST_PRICE where IVLD_BEST_PRICE.CHECK_RECORD = 1;";
                processName = "BEST_PRICE_INTERFACE";
            }
            if (invalidTable.equalsIgnoreCase(ConstantUtil.IVLD_ACTUAL_MASTER)) {
                insertSql = "INSERT INTO STAG_ACTUAL_MASTER \n"
                        + "(ACTUAL_INTFID,ACTUAL_ID,CONTRACT_ID,UPLOAD_DATE,PROVISION_ID,ACCRUAL_ACTUAL_START_DATE,ACCRUAL_ACTUAL_END_DATE,ITEM_ID,ITEM_IDENTIFIER_CODE_QUALIFIER,ITEM_NO,SETTLEMENT_METHOD,\n"
                        + "CASH_PAID_DATE,AMOUNT,QUANTITY,QUANTITY_INCLUSION,SETTLEMENT_NO,INVOICE_LINE_NUMBER,ACCOUNT_ID,ACCT_IDENTIFIER_CODE_QUALIFIER,ACCOUNT_NO,ACCOUNT_NAME,ANALYSIS_CODE,IS_ACTIVE,COM_DIV_MKT_BRAND_PROD_KEY,\n"
                        + "PARENTCOM_DIVMKT_BRAND_PRODKEY,PRICE_ADJUSTMENT_NAME,PRICE,RECORD_SEQUENCE,SENT_OUT,ACCRUAL_PROCESSED,DIVISION_ID,MARKET_ID,BRAND_ID,CLAIM_INDICATOR,SALES_AMOUNT,ORGANIZATION_KEY,MANDATED_DISCOUNT_AMOUNT,"
                        + "\n"
                        + "PROVISION_CLAIM_INDICATOR,PROGRAM_STATE_CODE,DISPENSED_DATE,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,BATCH_ID,\"SOURCE\",ADD_CHG_DEL_INDICATOR)\n"
                        + "SELECT ACTUAL_INTFID,ACTUAL_ID,CONTRACT_ID,UPLOAD_DATE,PROVISION_ID,ACCRUAL_ACTUAL_START_DATE,ACCRUAL_ACTUAL_END_DATE,ITEM_ID,ITEM_IDENTIFIER_CODE_QUALIFIER,ITEM_NO,SETTLEMENT_METHOD_NO,\n"
                        + "CASH_PAID_DATE,AMOUNT,QUANTITY,QUANTITY_INCLUSION,SETTLEMENT_NO,INVOICE_LINE_NO,ACCOUNT_ID,ACCT_IDENTIFIER_CODE_QUALIFIER,ACCOUNT_NO,ACCOUNT_NAME,ANALYSIS_CODE,IS_ACTIVE,COM_DIV_MKT_BRAND_PROD_KEY,\n"
                        + "PARENTCOM_DIVMKT_BRAND_PRODKEY,PRICE_ADJUSTMENT_NAME,PRICE,RECORD_SEQUENCE,SENT_OUT,ACCRUAL_PROCESSED,DIVISION_ID,MARKET_ID,BRAND_ID,CLAIM_INDICATOR,SALES_AMOUNT,ORGANIZATION_KEY,MANDATED_DISCOUNT_AMOUNT,"
                        + "\n"
                        + "PROVISION_CLAIM_INDICATOR,PROGRAM_STATE_CODE,DISPENSED_DATE,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,'IVLD_' + REPLACE(BATCH_ID,'IVLD_','') BATCH_ID,\"SOURCE\",ADD_CHG_DEL_INDICATOR\n"
                        + "FROM IVLD_ACTUAL_MASTER where IVLD_ACTUAL_MASTER.CHECK_RECORD = 1 ;";
                processName = "ACTUAL_MASTER_INTERFACE";
            }
            if (invalidTable.equalsIgnoreCase("IVLD_FORMULA_DETAILS")) {
                insertSql = "INSERT INTO STAG_FORMULA_DETAILS \n"
                        + "(FORMULA_DETAILS_INTFID,FORMULA_ID,FORMULA_NO,FORMULA_DESC,COMPANY_ID,ITEM_ID,START_DATE,END_DATE,REBATE_PERCENT_1,REBATE_PERCENT_2,REBATE_PERCENT_3,\n"
                        + "CONTRACT_PRICE_1,CONTRACT_PRICE_2,CONTRACT_PRICE_3,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,BATCH_ID,\"SOURCE\",ADD_CHG_DEL_INDICATOR)\n"
                        + "SELECT FORMULA_DETAILS_INTFID,FORMULA_ID,FORMULA_NO,FORMULA_DESC,COMPANY_ID,ITEM_ID,START_DATE,END_DATE,REBATE_PERCENT_1,REBATE_PERCENT_2,REBATE_PERCENT_3,\n"
                        + "CONTRACT_PRICE_1,CONTRACT_PRICE_2,CONTRACT_PRICE_3,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,'IVLD_' + REPLACE(BATCH_ID,'IVLD_','') BATCH_ID,\"SOURCE\",ADD_CHG_DEL_INDICATOR\n"
                        + "FROM IVLD_FORMULA_DETAILS where IVLD_FORMULA_DETAILS.CHECK_RECORD = 1 ;";
                processName = "FORMULA_DETAILS_INTERFACE";
            }
            if (invalidTable.equalsIgnoreCase("IVLD_ITEM_HIERARCHY")) {
                insertSql = "INSERT INTO STAG_ITEM_HIERARCHY \n"
                        + "(ITEM_HIERARCHY_INTFID,LEVEL0_TAG,LEVEL0,LEVEL0_ALIAS,LEVEL1,LEVEL1_ALIAS,LEVEL2,LEVEL2_ALIAS,LEVEL3,LEVEL3_ALIAS,LEVEL4,LEVEL4_ALIAS,LEVEL5,LEVEL5_ALIAS,LEVEL6,LEVEL6_ALIAS,LEVEL7,\n"
                        + "LEVEL7_ALIAS,LEVEL8,LEVEL8_ALIAS,LEVEL9,LEVEL9_ALIAS,LEVEL10,LEVEL10_ALIAS,LEVEL11,LEVEL11_ALIAS,LEVEL12,LEVEL12_ALIAS,LEVEL13,LEVEL13_ALIAS,LEVEL14,LEVEL14_ALIAS,LEVEL15,LEVEL15_ALIAS,LEVEL16,"
                        + "\n"
                        + "LEVEL16_ALIAS,LEVEL17,LEVEL17_ALIAS,LEVEL18,LEVEL18_ALIAS,LEVEL19,LEVEL19_ALIAS,LEVEL20,LEVEL20_ALIAS,GTN_BRAND,GTN_THERAPEUTIC_CLASS,GTN_COMPANY,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,BATCH_ID,\n"
                        + "\"SOURCE\",ADD_CHG_DEL_INDICATOR)\n"
                        + "SELECT ITEM_HIERARCHY_INTFID,LEVEL0_TAG,LEVEL0,LEVEL0_ALIAS,LEVEL1,LEVEL1_ALIAS,LEVEL2,LEVEL2_ALIAS,LEVEL3,LEVEL3_ALIAS,LEVEL4,LEVEL4_ALIAS,LEVEL5,LEVEL5_ALIAS,LEVEL6,LEVEL6_ALIAS,LEVEL7,\n"
                        + "LEVEL7_ALIAS,LEVEL8,LEVEL8_ALIAS,LEVEL9,LEVEL9_ALIAS,LEVEL10,LEVEL10_ALIAS,LEVEL11,LEVEL11_ALIAS,LEVEL12,LEVEL12_ALIAS,LEVEL13,LEVEL13_ALIAS,LEVEL14,LEVEL14_ALIAS,LEVEL15,LEVEL15_ALIAS,LEVEL16,"
                        + "\n"
                        + "LEVEL16_ALIAS,LEVEL17,LEVEL17_ALIAS,LEVEL18,LEVEL18_ALIAS,LEVEL19,LEVEL19_ALIAS,LEVEL20,LEVEL20_ALIAS,GTN_BRAND,GTN_THERAPEUTIC_CLASS,GTN_COMPANY,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,'IVLD_' + REPLACE(BATCH_ID,'IVLD_','') BATCH_ID,\n"
                        + "\"SOURCE\",ADD_CHG_DEL_INDICATOR\n"
                        + "FROM IVLD_ITEM_HIERARCHY where IVLD_ITEM_HIERARCHY.CHECK_RECORD = 1 ;";
                processName = "ITEM_HIERARCHY_INTERFACE";
            }
            if (invalidTable.equalsIgnoreCase("IVLD_ITEM_HIERARCHY_DEFINITION")) {
                insertSql = "INSERT INTO STAG_ITEM_HIERARCHY_DEFINITION \n"
                        + "(ITEM_HIERARCHY_DEFN_INTFID,MEMBER,\"ALIAS\",BPI_LVL,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,BATCH_ID,\"SOURCE\",ADD_CHG_DEL_INDICATOR)\n"
                        + "SELECT ITEM_HIERARCHY_DEFN_INTFID,MEMBER,\"ALIAS\",BPI_LVL,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,'IVLD_' + REPLACE(BATCH_ID,'IVLD_','') BATCH_ID,\"SOURCE\",ADD_CHG_DEL_INDICATOR\n"
                        + "FROM IVLD_ITEM_HIERARCHY_DEFINITION where IVLD_ITEM_HIERARCHY_DEFINITION.CHECK_RECORD = 1 ;";
                processName = "ITEM_HIERARCHY_DEFINITION_INTERFACE";
            }
            if (invalidTable.equalsIgnoreCase(ConstantUtil.VW_IVLD_INVENTORY_WD_ACTUAL_PROJ_MAS)) {
                if (ConstantUtil.ACTUALS.equalsIgnoreCase(typeValue)) {
                    insertSql = "INSERT INTO STAG_INVENTORY_WD_ACTUAL_MAS \n"
                            + " (INVENTORY_WD_ACTUAL_MAS_INTF_ID,\"YEAR\",\"MONTH\",\"DAY\",WEEK,ITEM_ID,\n"
                            + " UNITS_WITHDRAWN,AMOUNT_WITHDRAWN,UNITS_ON_HAND,AMOUNT_ON_HAND,QUANTITY_ON_ORDER,AMOUNT_ON_ORDER,QUANTITY_RECEIVED,AMOUNT_RECEIVED,CREATED_BY,\n"
                            + " CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,ADD_CHG_DEL_INDICATOR,BATCH_ID,\"SOURCE\",COUNTRY,ORGANIZATION_KEY)\n"
                            + " SELECT INVENTORY_WD_ACTUAL_PROJ_MAS_INTF_ID,\"YEAR\",\"MONTH\",\"DAY\",WEEK,ITEM_ID,\n"
                            + " UNITS_WITHDRAWN,AMOUNT_WITHDRAWN,UNITS_ON_HAND,AMOUNT_ON_HAND,QUANTITY_ON_ORDER,AMOUNT_ON_ORDER,QUANTITY_RECEIVED,AMOUNT_RECEIVED,CREATED_BY,\n"
                            + " CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,ADD_CHG_DEL_INDICATOR,'IVLD_' + REPLACE(BATCH_ID,'IVLD_',''),\"SOURCE\",COUNTRY,ORGANIZATION_KEY\n"
                            + " FROM VW_IVLD_INVENTORY_WD_ACTUAL_PROJ_MAS where VW_IVLD_INVENTORY_WD_ACTUAL_PROJ_MAS.CHECK_RECORD = 1 AND VW_IVLD_INVENTORY_WD_ACTUAL_PROJ_MAS.IS_FORECAST=" + 0 + " ";
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
                            + " FROM VW_IVLD_INVENTORY_WD_ACTUAL_PROJ_MAS where VW_IVLD_INVENTORY_WD_ACTUAL_PROJ_MAS.CHECK_RECORD = 1 AND VW_IVLD_INVENTORY_WD_ACTUAL_PROJ_MAS.IS_FORECAST=" + 1 + " ";
                    processName = "INVENTORY_WITHDRAWAL_FORECAST_INTERFACE";
                }
            }

            if (invalidTable.equalsIgnoreCase("IVLD_MASTER_DATA_ATTRIBUTE")) {
                insertSql = "INSERT INTO STAG_MASTER_DATA_ATTRIBUTE \n"
                        + "(DATA_ATTRIBUTE_INTFID, MASTER_TYPE, MASTER_ID, MASTER_ATTRIBUTE, COLUMN_1, COLUMN_2, COLUMN_3, COLUMN_4, COLUMN_5, COLUMN_6, COLUMN_7, COLUMN_8, COLUMN_9, COLUMN_10,\n"
                        + "COLUMN_11, COLUMN_12, COLUMN_13, COLUMN_14, COLUMN_15, COLUMN_16, COLUMN_17, COLUMN_18, COLUMN_19, COLUMN_20, CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE, BATCH_ID, \n"
                        + "\"SOURCE\", ADD_CHG_DEL_INDICATOR)\n"
                        + "SELECT DATA_ATTRIBUTE_INTFID, MASTER_TYPE, MASTER_ID, MASTER_ATTRIBUTE, COLUMN_1, COLUMN_2, COLUMN_3, COLUMN_4, COLUMN_5, COLUMN_6, COLUMN_7, COLUMN_8, COLUMN_9, COLUMN_10,\n"
                        + "COLUMN_11, COLUMN_12, COLUMN_13, COLUMN_14, COLUMN_15, COLUMN_16, COLUMN_17, COLUMN_18, COLUMN_19, COLUMN_20, CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE, 'IVLD_' + REPLACE(BATCH_ID,'IVLD_','') BATCH_ID, \n"
                        + "\"SOURCE\", ADD_CHG_DEL_INDICATOR\n"
                        + "FROM IVLD_MASTER_DATA_ATTRIBUTE where IVLD_MASTER_DATA_ATTRIBUTE.CHECK_RECORD = 1 ;";
                processName = "MASTER_DATA_ATTRIBUTE_INTERFACE";
            }
            if (invalidTable.equalsIgnoreCase("IVLD_BEST_PRICE")) {
                insertSql = "INSERT INTO STAG_BEST_PRICE \n"
                        + "(BEST_PRICE_INTFID, ITEM_ID, ITEM_NO, ITEM_DESC, BEGINNING_WAC_PACKAGE, SELLING_PRICE, INITIAL_BEST_PRICE, INITIAL_DISCOUNT, CONTRACT_NO, CONTRACT_ID, ACCOUNT_ID, CONTRACT_START_DATE, \n"
                        + "CONTRACT_END_DATE, \"PERIOD\", CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE, BATCH_ID, \"SOURCE\", ADD_CHG_DEL_INDICATOR)\n"
                        + "SELECT BEST_PRICE_INTFID, ITEM_ID, ITEM_NO, ITEM_DESC, BEGINNING_WAC_PACKAGE, SELLING_PRICE, INITIAL_BEST_PRICE, INITIAL_DISCOUNT, CONTRACT_NO, CONTRACT_ID, ACCOUNT_ID, CONTRACT_START_DATE, \n"
                        + "CONTRACT_END_DATE, \"PERIOD\", CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE, 'IVLD_' + REPLACE(BATCH_ID,'IVLD_','') BATCH_ID, \"SOURCE\", ADD_CHG_DEL_INDICATOR\n"
                        + "FROM IVLD_BEST_PRICE where IVLD_BEST_PRICE.CHECK_RECORD = 1 ;";
                processName = "BEST_PRICE_INTERFACE";
            }
            if (invalidTable.equalsIgnoreCase(ConstantUtil.IVLD_ACTUAL_MASTER)) {
                insertSql = "INSERT INTO STAG_ACTUAL_MASTER \n"
                        + "(ACTUAL_INTFID,ACTUAL_ID,CONTRACT_ID,UPLOAD_DATE,PROVISION_ID,ACCRUAL_ACTUAL_START_DATE,ACCRUAL_ACTUAL_END_DATE,ITEM_ID,ITEM_IDENTIFIER_CODE_QUALIFIER,ITEM_NO,SETTLEMENT_METHOD,\n"
                        + "CASH_PAID_DATE,AMOUNT,QUANTITY,QUANTITY_INCLUSION,SETTLEMENT_NO,INVOICE_LINE_NUMBER,ACCOUNT_ID,ACCT_IDENTIFIER_CODE_QUALIFIER,ACCOUNT_NO,ACCOUNT_NAME,ANALYSIS_CODE,IS_ACTIVE,COM_DIV_MKT_BRAND_PROD_KEY,\n"
                        + "PARENTCOM_DIVMKT_BRAND_PRODKEY,PRICE_ADJUSTMENT_NAME,PRICE,RECORD_SEQUENCE,SENT_OUT,ACCRUAL_PROCESSED,DIVISION_ID,MARKET_ID,BRAND_ID,CLAIM_INDICATOR,SALES_AMOUNT,ORGANIZATION_KEY,MANDATED_DISCOUNT_AMOUNT,\n"
                        + "PROVISION_CLAIM_INDICATOR,PROGRAM_STATE_CODE,DISPENSED_DATE,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,BATCH_ID,\"SOURCE\",ADD_CHG_DEL_INDICATOR)\n"
                        + "SELECT ACTUAL_INTFID,ACTUAL_ID,CONTRACT_ID,UPLOAD_DATE,PROVISION_ID,ACCRUAL_ACTUAL_START_DATE,ACCRUAL_ACTUAL_END_DATE,ITEM_ID,ITEM_IDENTIFIER_CODE_QUALIFIER,ITEM_NO,SETTLEMENT_METHOD_NO,\n"
                        + "CASH_PAID_DATE,AMOUNT,QUANTITY,QUANTITY_INCLUSION,SETTLEMENT_NO,INVOICE_LINE_NO,ACCOUNT_ID,ACCT_IDENTIFIER_CODE_QUALIFIER,ACCOUNT_NO,ACCOUNT_NAME,ANALYSIS_CODE,IS_ACTIVE,COM_DIV_MKT_BRAND_PROD_KEY,\n"
                        + "PARENTCOM_DIVMKT_BRAND_PRODKEY,PRICE_ADJUSTMENT_NAME,PRICE,RECORD_SEQUENCE,SENT_OUT,ACCRUAL_PROCESSED,DIVISION_ID,MARKET_ID,BRAND_ID,CLAIM_INDICATOR,SALES_AMOUNT,ORGANIZATION_KEY,MANDATED_DISCOUNT_AMOUNT,\n"
                        + "PROVISION_CLAIM_INDICATOR,PROGRAM_STATE_CODE,DISPENSED_DATE,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,'IVLD_' + REPLACE(BATCH_ID,'IVLD_','') BATCH_ID,\"SOURCE\",ADD_CHG_DEL_INDICATOR\n"
                        + "FROM IVLD_ACTUAL_MASTER where IVLD_ACTUAL_MASTER.CHECK_RECORD= 1";
                processName = "ACTUAL_MASTER_INTERFACE";
            }
            if (invalidTable.equalsIgnoreCase("IVLD_FORMULA_DETAILS")) {
                insertSql = "INSERT INTO STAG_FORMULA_DETAILS \n"
                        + "(FORMULA_DETAILS_INTFID,FORMULA_ID,FORMULA_NO,FORMULA_DESC,COMPANY_ID,ITEM_ID,START_DATE,END_DATE,REBATE_PERCENT_1,REBATE_PERCENT_2,REBATE_PERCENT_3,\n"
                        + "CONTRACT_PRICE_1,CONTRACT_PRICE_2,CONTRACT_PRICE_3,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,BATCH_ID,\"SOURCE\",ADD_CHG_DEL_INDICATOR)\n"
                        + "SELECT FORMULA_DETAILS_INTFID,FORMULA_ID,FORMULA_NO,FORMULA_DESC,COMPANY_ID,ITEM_ID,START_DATE,END_DATE,REBATE_PERCENT_1,REBATE_PERCENT_2,REBATE_PERCENT_3,\n"
                        + "CONTRACT_PRICE_1,CONTRACT_PRICE_2,CONTRACT_PRICE_3,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,'IVLD_' + REPLACE(BATCH_ID,'IVLD_','') BATCH_ID,\"SOURCE\",ADD_CHG_DEL_INDICATOR\n"
                        + "FROM IVLD_FORMULA_DETAILS where IVLD_FORMULA_DETAILS.CHECK_RECORD = 1; ";
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
                        + "FROM IVLD_ITEM_HIERARCHY where IVLD_ITEM_HIERARCHY.CHECK_RECORD = 1; ";
                processName = "ITEM_HIERARCHY_INTERFACE";
            }
            if (invalidTable.equalsIgnoreCase("IVLD_ITEM_HIERARCHY_DEFINITION")) {
                insertSql = "INSERT INTO STAG_ITEM_HIERARCHY_DEFINITION \n"
                        + "(ITEM_HIERARCHY_DEFN_INTFID,MEMBER,\"ALIAS\",BPI_LVL,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,BATCH_ID,\"SOURCE\",ADD_CHG_DEL_INDICATOR)\n"
                        + "SELECT ITEM_HIERARCHY_DEFN_INTFID,MEMBER,\"ALIAS\",BPI_LVL,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,'IVLD_' + REPLACE(BATCH_ID,'IVLD_','') BATCH_ID,\"SOURCE\",ADD_CHG_DEL_INDICATOR\n"
                        + "FROM IVLD_ITEM_HIERARCHY_DEFINITION where IVLD_ITEM_HIERARCHY_DEFINITION.CHECK_RECORD = 1 ;";
                processName = "ITEM_HIERARCHY_DEFINITION_INTERFACE";
            }

            if (invalidTable.equalsIgnoreCase(ConstantUtil.VW_IVLD_DEMAND_FPRECAST)) {
                if (ConstantUtil.PROJECTION.equalsIgnoreCase(tableValue1)) {
                    insertSql = "INSERT INTO STAG_DEMAND_FORECAST \n"
                            + "(DEMAND_FORECAST_INTERFACE_ID,FORECAST_TYPE,FORECAST_YEAR,FORECAST_MONTH,ITEM_ID,BRAND_ID,SEGMENT,MARKET_SIZE_UNITS,MARKET_SHARE_RATIO,MARKET_SHARE_UNITS,UNCAPTURED_UNITS,UNCAPTURED_UNITS_RATIO,TOTAL_DEMAND_UNITS,\n"
                            + "TOTAL_DEMAND_AMOUNT,INVENTORY_UNIT_CHANGE,GROSS_UNITS,GROSS_PRICE,GROSS_AMOUNT,NET_SALES_PRICE,NET_SALES_AMOUNT,ADD_CHG_DEL_INDICATOR,BATCH_ID,\"SOURCE\",FORECAST_NAME,FORECAST_VER,COUNTRY,ORGANIZATION_KEY)\n"
                            + "SELECT DEMAND_INT_SID,FORECAST_TYPE,FORECAST_YEAR,FORECAST_MONTH,ITEM_ID,BRAND_ID,SEGMENT,MARKET_SIZE_UNITS,MARKET_SHARE_RATIO,MARKET_SHARE_UNITS,UNCAPTURED_UNITS,UNCAPTURED_UNITS_RATIO,TOTAL_DEMAND_UNITS,\n"
                            + "TOTAL_DEMAND_AMOUNT,INVENTORY_UNIT_CHANGE,GROSS_UNITS,GROSS_PRICE,GROSS_AMOUNT,NET_SALES_PRICE,NET_SALES_AMOUNT,ADD_CHG_DEL_INDICATOR,'IVLD_' + REPLACE(BATCH_ID,'IVLD_','') BATCH_ID,\"SOURCE\",FORECAST_NAME,FORECAST_VER,COUNTRY,ORGANIZATION_KEY"
                            + " FROM VW_IVLD_DEMAND_FORECAST_ACTUAL where VW_IVLD_DEMAND_FORECAST_ACTUAL.CHECK_RECORD = 1 AND VW_IVLD_DEMAND_FORECAST_ACTUAL.IS_FORECAST=" + 1 + " ";
                    processName = "DEMAND_FORECAST_INTERFACE";
                } else {
                    insertSql = "INSERT INTO STAG_DEMAND_ACTUAL \n"
                            + "(DEMAND_ACTUAL_INTERFACE_ID,FORECAST_TYPE,FORECAST_YEAR,FORECAST_MONTH,ITEM_ID,BRAND_ID,SEGMENT,MARKET_SIZE_UNITS,MARKET_SHARE_RATIO,MARKET_SHARE_UNITS,TOTAL_DEMAND_UNITS,TOTAL_DEMAND_AMOUNT,\n"
                            + "GROSS_UNITS,GROSS_PRICE,GROSS_AMOUNT,NET_SALES_PRICE,NET_SALES_AMOUNT,ADD_CHG_DEL_INDICATOR,BATCH_ID,\"SOURCE\",COUNTRY,ORGANIZATION_KEY)\n"
                            + "SELECT DEMAND_INT_SID,FORECAST_TYPE,FORECAST_YEAR,FORECAST_MONTH,ITEM_ID,BRAND_ID,SEGMENT,MARKET_SIZE_UNITS,MARKET_SHARE_RATIO,MARKET_SHARE_UNITS,TOTAL_DEMAND_UNITS,TOTAL_DEMAND_AMOUNT,\n"
                            + "GROSS_UNITS,GROSS_PRICE,GROSS_AMOUNT,NET_SALES_PRICE,NET_SALES_AMOUNT,ADD_CHG_DEL_INDICATOR,'IVLD_' + REPLACE(BATCH_ID,'IVLD_','') BATCH_ID,\"SOURCE\",COUNTRY,ORGANIZATION_KEY"
                            + " FROM VW_IVLD_DEMAND_FORECAST_ACTUAL where VW_IVLD_DEMAND_FORECAST_ACTUAL.CHECK_RECORD = 1 AND VW_IVLD_DEMAND_FORECAST_ACTUAL.IS_FORECAST=" + 0 + " ";
                    processName = "DEMAND_ACTUAL_INTERFACE";
                }
            }
            if (invalidTable.equalsIgnoreCase(ConstantUtil.VW_IVLD_ADJ_DEMAND_FORE_ACTUAL)) {
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
                            + "where VW_IVLD_ADJ_DEMAND_FORE_ACTUAL.CHECK_RECORD = 1"
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
                            + " where VW_IVLD_ADJ_DEMAND_FORE_ACTUAL.CHECK_RECORD = 1"
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
                        + "UPPER_LIMIT,LOWER_LIMIT,ADD_CHG_DEL_INDICATOR,'IVLD_' + REPLACE(BATCH_ID,'IVLD_','') BATCH_ID,\"SOURCE\" from IVLD_RETURNS where IVLD_RETURNS.CHECK_RECORD = 1; ";
                processName = "RETURNS_INTERFACE";
            }
            if (invalidTable.equalsIgnoreCase("IVLD_CUSTOMER_GTS_FORECAST")) {
                insertSql = "INSERT INTO STAG_CUSTOMER_GTS_FORECAST (CUSTOMER_GTS_FORECAST_INTF_ID,\n"
                        + "FORECAST_YEAR,FORECAST_MONTH,ITEM_ID,COMPANY_ID,UNITS,\n"
                        + "PRICE_TYPE,PRICE,SALES_AMOUNT,SALES_INCLUSION,DEDUCTION_ID,DEDUCTION_CATEGORY,DEDUCTION_TYPE,DEDUCTION_PROGRAM_TYPE,\n"
                        + "ADJUSTMENT_CODE,DEDUCTION_RATE,DEDUCTION_AMOUNT,DEDUCTION_INCLUSION,FORECAST_VALUE_TYPE,\n"
                        + "BRAND,SEGMENT,ADD_CHG_DEL_INDICATOR,FORECAST_VER,COUNTRY,FORECAST_NAME,FORECAST_DATE,UDC1,\n"
                        + "UDC2,UDC3,UDC4,UDC5,UDC6,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,BATCH_ID,\"SOURCE\")\n"
                        + "Select CUSTOMER_GTS_FORECAST_INTF_ID,\n"
                        + "FORECAST_YEAR,FORECAST_MONTH,ITEM_ID,COMPANY_ID,UNITS,\n"
                        + "PRICE_TYPE,PRICE,SALES_AMOUNT,SALES_INCLUSION,DEDUCTION_ID,DEDUCTION_CATEGORY,DEDUCTION_TYPE,DEDUCTION_PROGRAM_TYPE,\n"
                        + "ADJUSTMENT_CODE,DEDUCTION_RATE,DEDUCTION_AMOUNT,DEDUCTION_INCLUSION ,FORECAST_VALUE_TYPE,\n"
                        + "BRAND,SEGMENT,ADD_CHG_DEL_INDICATOR,FORECAST_VER,COUNTRY,FORECAST_NAME,FORECAST_DATE,UDC1,\n"
                        + "UDC2,UDC3,UDC4,UDC5,UDC6,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,'IVLD_' + REPLACE(BATCH_ID,'IVLD_','') BATCH_ID,\"SOURCE\" from IVLD_CUSTOMER_GTS_FORECAST where IVLD_CUSTOMER_GTS_FORECAST.CHECK_RECORD = 1;";
                processName = "CUSTOMER_GTS_FORECAST_INTERFACE";
            }
            if (invalidTable.equalsIgnoreCase("IVLD_CUSTOMER_GTS_ACTUAL")) {
                insertSql = "INSERT INTO STAG_CUSTOMER_GTS_ACTUAL (CUSTOMER_GTS_ACTUAL_INTF_ID,SALES_ID,ORGANIZATION_KEY,ITEM_ID,\n"
                        + "ITEM_UOM,ORDER_NUMBER,INVOICE_NUMBER,INVOICE_LINE_NUMBER,INVOICE_DATE,\n"
                        + "QUANTITY,LOT_NO,AMOUNT,CONTRACT_ID,ACCOUNT_ID,ORDER_RECEIVED_DATE,BATCH_ID,\"SOURCE\",CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,ADD_CHG_DEL_INDICATOR)\n"
                        + "Select  CUSTOMER_GTS_ACTUAL_INTF_ID,SALES_ID,ORGANIZATION_KEY,ITEM_ID,\n"
                        + "ITEM_UOM,ORDER_NUMBER,INVOICE_NUMBER,INVOICE_LINE_NUMBER,INVOICE_DATE,\n"
                        + "QUANTITY,LOT_NO,AMOUNT,CONTRACT_ID,ACCOUNT_ID,ORDER_RECEIVED_DATE,'IVLD_' + REPLACE(BATCH_ID,'IVLD_','') BATCH_ID,\"SOURCE\",CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,ADD_CHG_DEL_INDICATOR from IVLD_CUSTOMER_GTS_ACTUAL where IVLD_CUSTOMER_GTS_ACTUAL.IVLD_CUSTOMER_GTS_ACTUAL_SID = 1;";
                processName = "CUSTOMER_GTS_ACTUAL_INTERFACE";
            }
            if (invalidTable.equalsIgnoreCase(ConstantUtil.INVALID_ACCRUAL)) {
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
                        + "WHERE IVLD_ACCRUAL_INBOUND.CHECK_RECORD = 1 ;";
                processName = "ACCRUAL_MASTER_INTERFACE";
            }
            if (invalidTable.equalsIgnoreCase("IVLD_COMPANY_MASTER")) {
                insertSql = "INSERT INTO  STAG_COMPANY_MASTER (COMPANY_MASTER_INTFID,ORGANIZATION_KEY, COMPANY_ID, COMPANY_NO, COMPANY_NAME, COMPANY_TYPE, COMPANY_STATUS, LIVES, COMPANY_END_DATE,\n"
                        + "                    UDC1, UDC2, UDC3, UDC4, UDC5, UDC6, COMPANY_GROUP, FINANCIAL_SYSTEM, ADDRESS_1,ADDRESS_2,\n"
                        + "                    CITY,\"STATE\",ZIP_CODE,COUNTRY,REGION_CODE,LAST_UPDATED_DATE,COMPANY_START_DATE,\"STATUS\",\n"
                        + "                    COMPANY_CATEGORY,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,BATCH_ID,\"SOURCE\",ADD_CHG_DEL_INDICATOR)\n"
                        + "select COMPANY_MASTER_INTFID,ORGANIZATION_KEY,case when COMPANY_ID is null then '' else COMPANY_ID end as COMPANY_ID ,case when COMPANY_NO is null then '' else COMPANY_NO end as COMPANY_NO ,case when COMPANY_NAME is null then '' else COMPANY_NAME end as COMPANY_NAME ,case when COMPANY_TYPE is null then '' else COMPANY_TYPE end as COMPANY_TYPE ,case when COMPANY_STATUS is null then '' else COMPANY_STATUS end as COMPANY_STATUS ,LIVES,(CAST(COMPANY_END_DATE AS datetime)) as COMPANY_END_DATE,\n"
                        + "                    UDC1,UDC2,UDC3,UDC4,UDC5,UDC6,COMPANY_GROUP,FINANCIAL_SYSTEM,ADDRESS_1,ADDRESS_2,\n"
                        + "                    CITY,\"STATE\",ZIP_CODE,COUNTRY,REGION_CODE,(CAST(LAST_UPDATED_DATE AS datetime)) as LAST_UPDATED_DATE,(CAST(COMPANY_START_DATE AS datetime)) as COMPANY_START_DATE, STATUS,\n"
                        + "                    COMPANY_CATEGORY,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,isnull('IVLD_' + convert(varchar(50),'IVLD_' +batch_id) ,'') as BATCH_ID,\"SOURCE\", case when ADD_CHG_DEL_INDICATOR is null then '' else ADD_CHG_DEL_INDICATOR end as ADD_CHG_DEL_INDICATOR  \n"
                        + "                    FROM IVLD_COMPANY_MASTER  WHERE  IVLD_COMPANY_MASTER.CHECK_RECORD = 1 ;";
                processName = "COMPANY_MASTER_INTERFACE";
            }

            if (invalidTable.equalsIgnoreCase("IVLD_COMPANY_IDENTIFIER")) {
                insertSql = "INSERT INTO  STAG_COMPANY_IDENTIFIER (COMPANY_IDENTIFIER_INTFID,COMPANY_ID,COMPANY_NO,COMPANY_NAME,IDENTIFIER_CODE_QUALIFIER,IDENTIFIER_CODE_QUALIFIER_NAME,COMPANY_IDENTIFIER  ,IDENTIFIER_STATUS,START_DATE,\n"
                        + "                    END_DATE,ENTITY_CODE,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,BATCH_ID,SOURCE,ADD_CHG_DEL_INDICATOR)\n"
                        + "                    select case when COMPANY_IDENTIFIER_INTFID is null then '' else COMPANY_IDENTIFIER_INTFID end as COMPANY_IDENTIFIER_INTFID ,case when COMPANY_ID is null then '' else COMPANY_ID end as COMPANY_ID ,case when COMPANY_NO is null then '' else COMPANY_NO end as COMPANY_NO,case when COMPANY_NAME is null then '' else COMPANY_NAME end as COMPANY_NAME ,case when IDENTIFIER_CODE_QUALIFIER is null then '' else IDENTIFIER_CODE_QUALIFIER end as IDENTIFIER_CODE_QUALIFIER ,case when IDENTIFIER_CODE_QUALIFIER_NAME is null then '' else IDENTIFIER_CODE_QUALIFIER_NAME end as IDENTIFIER_CODE_QUALIFIER_NAME ,case when COMPANY_IDENTIFIER is null then '' else COMPANY_IDENTIFIER end as COMPANY_IDENTIFIER ,case when IDENTIFIER_STATUS is null then '' else IDENTIFIER_STATUS end as IDENTIFIER_STATUS ,START_DATE,\n"
                        + "                    END_DATE,ENTITY_CODE,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,case when BATCH_ID is null then '' else BATCH_ID end as BATCH_ID ,SOURCE,case when ADD_CHG_DEL_INDICATOR is null then '' else ADD_CHG_DEL_INDICATOR end as ADD_CHG_DEL_INDICATOR  \n"
                        + "                    FROM IVLD_COMPANY_IDENTIFIER where IVLD_COMPANY_IDENTIFIER.CHECK_RECORD = 1 ;";
                processName = "COMPANY_IDENTIFIER_INTERFACE";
            }

            if (invalidTable.equalsIgnoreCase("IVLD_COMPANY_PARENT")) {
                insertSql = "INSERT INTO  STAG_COMPANY_PARENT (PARENT_DETAILS_INTFID,COMPANY_ID,PARENT_COMPANY_ID,PARENT_START_DATE,PARENT_END_DATE,PRIOR_PARENT_COMPANY_ID,PRIOR_PARENT_START_DATE,LAST_UPDATED_DATE,CREATED_BY,\n"
                        + "                    CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,BATCH_ID,SOURCE,ADD_CHG_DEL_INDICATOR)\n"
                        + "                   select case when PARENT_DETAILS_INTFID is null then '' else PARENT_DETAILS_INTFID end as PARENT_DETAILS_INTFID ,case when COMPANY_ID is null then '' else COMPANY_ID end as COMPANY_ID ,case when PARENT_COMPANY_ID is null then '' else PARENT_COMPANY_ID end as PARENT_COMPANY_ID ,case when PARENT_START_DATE is null then '' else PARENT_START_DATE end as  PARENT_START_DATE ,PARENT_END_DATE,PRIOR_PARENT_COMPANY_ID,PRIOR_PARENT_START_DATE,LAST_UPDATED_DATE,CREATED_BY,\n"
                        + "                    CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,case when BATCH_ID is null then '' else BATCH_ID end as BATCH_ID ,SOURCE,case when ADD_CHG_DEL_INDICATOR is null then '' else ADD_CHG_DEL_INDICATOR end as ADD_CHG_DEL_INDICATOR \n"
                        + "                     FROM IVLD_COMPANY_PARENT where IVLD_COMPANY_PARENT.CHECK_RECORD = 1 ;";

                processName = "COMPANY_PARENT_DETAILS_INTERFACE";
            }

            if (invalidTable.equalsIgnoreCase("IVLD_COMPANY_TRADE_CLASS")) {
                insertSql = "INSERT INTO  STAG_COMPANY_TRADE_CLASS (TRADE_CLASS_INTFID,COMPANY_ID,TRADE_CLASS_START_DATE,\n"
                        + "TRADE_CLASS_END_DATE,TRADE_CLASS,PRIOR_TRADE_CLASS,PRIOR_TRADE_CLASS_START_DATE,LAST_UPDATED_DATE,CREATED_BY,\n"
                        + "                    CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,BATCH_ID,\"SOURCE\",ADD_CHG_DEL_INDICATOR)\n"
                        + "                    select TRADE_CLASS_INTFID,--NumericConstants.THIRTY_EIGHT\n"
                        + "case when COMPANY_ID is null then '' else COMPANY_ID end as COMPANY_ID ,--200\n"
                        + "case when TRADE_CLASS_START_DATE is null then '' else TRADE_CLASS_START_DATE end as TRADE_CLASS_START_DATE ,TRADE_CLASS_END_DATE,--200,200,200,200\n"
                        + "case when TRADE_CLASS is null then '' else TRADE_CLASS end as TRADE_CLASS ,\n"
                        + "PRIOR_TRADE_CLASS,\n"
                        + "PRIOR_TRADE_CLASS_START_DATE,\n"
                        + "LAST_UPDATED_DATE,\n"
                        + "CREATED_BY,\n"
                        + "CREATED_DATE,MODIFIED_BY,\n"
                        + "MODIFIED_DATE,isnull('IVLD_' + convert(varchar(50),'IVLD_' +batch_id) ,'') as batch_id ,\n"
                        + "\"SOURCE\",case when ADD_CHG_DEL_INDICATOR is null then '' else ADD_CHG_DEL_INDICATOR end as ADD_CHG_DEL_INDICATOR  \n"
                        + "                    FROM IVLD_COMPANY_TRADE_CLASS WHERE  IVLD_COMPANY_TRADE_CLASS.CHECK_RECORD = 1 ;";
                processName = "COMPANY_TRADE_CLASS_INTERFACE";
            }

            if (invalidTable.equalsIgnoreCase("IVLD_ITEM_MASTER")) {
                insertSql = "INSERT INTO  STAG_ITEM_MASTER (ITEM_MASTER_INTFID,ITEM_ID,ITEM_NO,ITEM_CODE,ITEM_NAME,ITEM_DESC,PACKAGE_SIZE,PACKAGE_SIZE_CODE,PACKAGE_SIZE_INTRO_DATE,\n"
                        + "UPPS,ITEM_START_DATE,ITEM_END_DATE,ITEM_STATUS,MANUFACTURER_ID,MANUFACTURER_NO,MANUFACTURER_NAME,LABELER_CODE,ORGANIZATION_KEY,ACQUISITION_DATE,\n"
                        + "AUTHORIZED_GENERIC,AUTHORIZED_GENERIC_START_DATE,AUTHORIZED_GENERIC_END_DATE,BRAND,FORM,STRENGTH,THERAPEUTIC_CLASS,FIRST_SALE_DATE,\n"
                        + "ITEM_TYPE_INDICATION,ITEM_CLASS,ITEM_TYPE,MARKET_TERMINATION_DATE,NEW_FORMULATION_INDICATOR,NEW_FORMULATION,NEW_FORMULATION_START_DATE,NEW_FORMULATION_END_DATE,\n"
                        + "PEDIATRIC_EXCLUSIVE_INDICATOR,PEDIATRIC_EXCLUSIVE_START_DATE,PEDIATRIC_EXCLUSIVE_END_DATE,CLOTTING_FACTOR_INDICATOR,CLOTTING_FACTOR_START_DATE,CLOTTING_FACTOR_END_DATE,\n"
                        + "PRIMARY_UOM,SECONDARY_UOM,SHELF_LIFE,SHELF_LIFE_TYPE,DUAL_PRICING_INDICATOR,ITEM_FAMILY_ID,UDC1,UDC2,UDC3,UDC4,UDC5,UDC6,ACQUIRED_AMP,ACQUIRED_BAMP,OBRA_BAMP,\n"
                        + "DRA,DOSES_PER_UNIT,STATUS,DISCONTINUATION_DATE,LASTLOTEXPIRATION_DATE,DIVESTITURE_DATE,NON_FEDERAL_EXPIRATION_DATE,NDC9,NDC8,DISPLAY_BRAND,BRAND_ID,ITEM_CATEGORY,\n"
                        + "BASELINE_AMP,BASE_CPI_PERIOD,BASE_CPI,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,BATCH_ID,\"SOURCE\",ADD_CHG_DEL_INDICATOR)\n"
                        + "select ITEM_MASTER_INTFID,case when ITEM_ID is null then '' else ITEM_ID end as ITEM_ID ,case when ITEM_NO is null then '' else ITEM_NO end as ITEM_NO,case when ITEM_CODE is null then '' else ITEM_CODE end as ITEM_CODE,case when ITEM_NAME is null then '' else ITEM_NAME end as ITEM_NAME,ITEM_DESC,PACKAGE_SIZE,PACKAGE_SIZE_CODE,PACKAGE_SIZE_INTRO_DATE,\n"
                        + "UPPS,ITEM_START_DATE,ITEM_END_DATE,case when ITEM_STATUS is null then '' else ITEM_STATUS end as ITEM_STATUS ,MANUFACTURER_ID,MANUFACTURER_NO,MANUFACTURER_NAME,LABELER_CODE,ORGANIZATION_KEY,ACQUISITION_DATE,\n"
                        + "AUTHORIZED_GENERIC,AUTHORIZED_GENERIC_START_DATE,AUTHORIZED_GENERIC_END_DATE,BRAND,case when FORM is null then '' else FORM end as FORM ,case when STRENGTH is null then '' else STRENGTH end  as STRENGTH,THERAPEUTIC_CLASS,FIRST_SALE_DATE,\n"
                        + "ITEM_TYPE_INDICATION,ITEM_CLASS,ITEM_TYPE,MARKET_TERMINATION_DATE,NEW_FORMULATION_INDICATOR,NEW_FORMULATION,NEW_FORMULATION_START_DATE,NEW_FORMULATION_END_DATE,\n"
                        + "PEDIATRIC_EXCLUSIVE_INDICATOR,PEDIATRIC_EXCLUSIVE_START_DATE,PEDIATRIC_EXCLUSIVE_END_DATE,CLOTTING_FACTOR_INDICATOR,CLOTTING_FACTOR_START_DATE,CLOTTING_FACTOR_END_DATE,\n"
                        + "PRIMARY_UOM,SECONDARY_UOM,SHELF_LIFE,SHELF_LIFE_TYPE,DUAL_PRICING_INDICATOR,ITEM_FAMILY_ID,UDC1,UDC2,UDC3,UDC4,UDC5,UDC6,ACQUIRED_AMP,ACQUIRED_BAMP,OBRA_BAMP,\n"
                        + "DRA,DOSES_PER_UNIT,STATUS,DISCONTINUATION_DATE,LAST_LOT_EXPIRATION_DATE,DIVESTITURE_DATE,NON_FEDERAL_EXPIRATION_DATE,case when NDC9 is null then '' else NDC9 END as NDC9,case when NDC8 is null then '' else NDC8 END as NDC8,DISPLAY_BRAND,case when BRAND_ID is null then '' else BRAND_ID end as BRAND_ID,ITEM_CATEGORY,\n"
                        + "BASELINE_AMP,BASE_CPI_PERIOD,BASE_CPI,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,isnull('IVLD_' + convert(varchar(50),'IVLD_' +batch_id) ,'') as batch_id ,\"SOURCE\",case when ADD_CHG_DEL_INDICATOR is null then '' else ADD_CHG_DEL_INDICATOR end as ADD_CHG_DEL_INDICATOR\n"
                        + " FROM IVLD_ITEM_MASTER \n"
                        + "WHERE  IVLD_ITEM_MASTER.CHECK_RECORD = 1;";
                processName = "ITEM_MASTER_INTERFACE";
            }

            if (invalidTable.equalsIgnoreCase("IVLD_ITEM_IDENTIFIER")) {
                insertSql = "INSERT INTO  STAG_ITEM_IDENTIFIER (ITEM_IDENTIFIER_INTFID,ITEM_ID,ITEM_NO,ITEM_NAME,IDENTIFIER_CODE_QUALIFIER,IDENTIFIER_CODE_QUALIFIER_NAME,ITEM_IDENTIFIER,ITEM_STATUS,START_DATE,END_DATE,\n"
                        + "ENTITY_CODE,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,BATCH_ID,\"SOURCE\",ADD_CHG_DEL_INDICATOR)\n"
                        + "select ITEM_IDENTIFIER_INTFID,case when ITEM_ID is null then '' else ITEM_ID end as ITEM_ID ,case when ITEM_NO is null then '' else ITEM_NO end as ITEM_NO ,ITEM_NAME,case when IDENTIFIER_CODE_QUALIFIER is null then '' else IDENTIFIER_CODE_QUALIFIER end as IDENTIFIER_CODE_QUALIFIER,IDENTIFIER_CODE_QUALIFIER_NAME,case when ITEM_IDENTIFIER  is null then '' else ITEM_IDENTIFIER end as ITEM_IDENTIFIER ,case when ITEM_STATUS is null then '' else ITEM_STATUS end as ITEM_STATUS,START_DATE,END_DATE,\n"
                        + "ENTITY_CODE,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,isnull('IVLD_' + convert(varchar(50),'IVLD_' +batch_id) ,'') as batch_id,\"SOURCE\",ADD_CHG_DEL_INDICATOR\n"
                        + "from IVLD_ITEM_IDENTIFIER\n"
                        + "WHERE  IVLD_ITEM_IDENTIFIER.CHECK_RECORD = 1 ;";
                processName = "ITEM_IDENTIFIER_INTERFACE";
            }

            if (invalidTable.equalsIgnoreCase("IVLD_ITEM_PRICING")) {
                insertSql = "INSERT INTO STAG_ITEM_PRICING (ITEM_PRICING_INTFID,ITEM_ID,ITEM_NO,ITEM_NAME,ITEM_UOM,PRICING_CODE_QUALIFIER,PRICING_CODE_QUALIFIER_NAME,\n"
                        + "ITEM_PRICE,PRICING_CODE_STATUS,START_DATE,END_DATE,ENTITY_CODE,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,BATCH_ID,\n"
                        + "\"SOURCE\",ADD_CHG_DEL_INDICATOR )\n"
                        + "select ITEM_PRICING_INTFID,case when ITEM_ID is null then '' else ITEM_ID end as ITEM_ID,case when ITEM_NO is null then '' else ITEM_NO end as ITEM_NO,ITEM_NAME,case when ITEM_UOM is null then '' else ITEM_UOM end as ITEM_UOM,case when PRICING_CODE_QUALIFIER is null then '' else PRICING_CODE_QUALIFIER end as PRICING_CODE_QUALIFIER,PRICING_CODE_QUALIFIER_NAME,\n"
                        + "case when convert(numeric,ITEM_PRICE) is null then 0 else convert(numeric,ITEM_PRICE) end as ITEM_PRICE,PRICING_CODE_STATUS,isnull(convert(datetime,START_DATE),'') as START_DATE ,convert(datetime,END_DATE) as END_DATE ,ENTITY_CODE,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,isnull(convert(varchar(50),'IVLD_' +batch_id) ,'') as BATCH_ID,\n"
                        + "\"SOURCE\",ADD_CHG_DEL_INDICATOR from IVLD_ITEM_PRICING\n"
                        + "WHERE  IVLD_ITEM_PRICING.CHECK_RECORD = 1 ;";
                processName = "ITEM_PRICING_INTERFACE";
            }
            if (invalidTable.equalsIgnoreCase("VW_IVLD_RETURN_RESERVE")) {
                insertSql = "INSERT INTO STAG_RETURN_RESERVE (RETURN_RESERVE_INTF_ID,\"YEAR\",\"MONTH\",BRAND_ID,\n"
                        + "BRAND_NAME,ITEM_ID,ITEM_NO,ITEM_NAME,UNITS,AMOUNT,COUNTRY,BUSINESS_UNIT,GL_COMPANY,\n"
                        + "COMPANY_NO,DIVISION,COST_CENTER,ACCOUNT,PROJECT,FUTURE1,FUTURE2,\n"
                        + "UDC1,UDC2,UDC3,UDC4,UDC5,UDC6,ADD_CHG_DEL_INDICATOR,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,\n"
                        + "BATCH_ID,\"SOURCE\")\n"
                        + "Select  RETURN_RESERVE_INTF_ID,\"YEAR\",\"MONTH\",BRAND_ID,\n"
                        + "BRAND_NAME,ITEM_ID,ITEM_NO,ITEM_NAME,UNITS,AMOUNT,COUNTRY,BUSINESS_UNIT,GL_COMPANY,\n"
                        + "COMPANY_NO,DIVISION,COST_CENTER,ACCOUNT,PROJECT,FUTURE1,FUTURE2,\n"
                        + "UDC1,UDC2,UDC3,UDC4,UDC5,UDC6,ADD_CHG_DEL_INDICATOR,CREATED_BY,CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,\n"
                        + "'IVLD_' + REPLACE(BATCH_ID,'IVLD_','') BATCH_ID,\"SOURCE\" from VW_IVLD_RETURN_RESERVE where VW_IVLD_RETURN_RESERVE.CHECK_RECORD = 1 ;";
                processName = "RETURN_RESERVE_INTERFACE";
            }
            updateFlagForReprocessedOrRemovedRecords();
            HelperTableLocalServiceUtil.executeUpdateQuery(insertSql);
            String interfaceScriptName = InterfaceScripts.getString(processName);
            runJob(getFtpBundleValue(), interfaceScriptName, false,selectedRecords,viewNameSid,stagingTable);
            updateAllInInvalidTable(invalidTable, "0");
        } else {
             
            SearchLogic logic = new SearchLogic();
            try {
                if (invalidTable.equalsIgnoreCase("StArpOutbound")) {
                    LOGGER.debug("inside arp outboud-------");
                    processName = "ACCRUAL_RATE_PROJECTION_INTERFACE";
                    arpProcess.lock();
                    LOGGER.debug("after process lock-------" + logic.checkETLRecords());
                    if (logic.checkETLRecords()) {

                        String interfaceScriptName = InterfaceScripts.getString(processName);
                        runJob(getFtpBundleValue(), interfaceScriptName, true,selectedRecords,viewNameSid,stagingTable);
                        logic.deleteARPTempTable();
                    }
                    LOGGER.debug("ARP outboud-----ends--");

                } else if (invalidTable.equalsIgnoreCase(ConstantUtil.ST_CFF_OUTBOUND)) {
                    LOGGER.debug("inside CFF outboud-------");
                    process.lock();
                    processName = "CFF_OUTBOUND_INTERFACE";
                    if (logic.checkCffETLRecords()) {
                        String interfaceScriptName = InterfaceScripts.getString(processName);
                        runJob(getFtpBundleValue(), interfaceScriptName, true,selectedRecords,viewNameSid,stagingTable);
                        logic.deleteReprocessCFFTempTable();
                    }
                    LOGGER.debug("CFF outboud-----ends--");
                } else if (ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL.equalsIgnoreCase(invalidTable)) {

                    LOGGER.debug("before process lock in Gtn detail -------");
                    GtnProcess.lock();
                    LOGGER.debug("after process lock in Gtn detail -------");
                    processName = "ADJUSTMENT_GTN_DETAIL";
                    int i = 0;
                    if (logic.checkGTNETLRecords()) {
                        String interfaceScriptName = InterfaceScripts.getString(processName);
                        runJob(getFtpBundleValue(), interfaceScriptName, true,selectedRecords,viewNameSid,stagingTable);
                        while (logic.GtnExistsQuery()) {
                            LOGGER.debug("inside while-------" + interfaceScriptName);
//                    // Waiting block for ETL to end
                            Thread.sleep(NumericConstants.THREE_THOUSAND);
                            i++;
                            if (i == NumericConstants.HUNDRED) {
                                logic.deleteGTNTempTableValues();
                                break;
                            }
                        }
                    }

                    LOGGER.debug("Gtn outboud-----ends--");

                } else if (ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL.equalsIgnoreCase(invalidTable)) {

                    LOGGER.debug("before process lock in Reserve detail -------");
                    reserveProcess.lock();
                    LOGGER.debug("after process lock in Reserve detail -------");
                    processName = "ADJUSTMENT_RESERVE_DETAIL";
                    int i = 0;
                    if (logic.checkReserveETLRecords()) {
                        String interfaceScriptName = InterfaceScripts.getString(processName);
                        runJob(getFtpBundleValue(), interfaceScriptName, true,selectedRecords,viewNameSid,stagingTable);
                        while (logic.ReserveExistsQuery()) {
                            LOGGER.debug("inside while-------" + interfaceScriptName);
//                    // Waiting block for ETL to end
                            Thread.sleep(NumericConstants.THREE_THOUSAND);
                            i++;
                            if (i == NumericConstants.HUNDRED) {
                                logic.deleteReserveTempTableValues();
                                break;
                            }
                        }
                    }

                    LOGGER.debug("Reserve outboud-----ends--");

                }
            } catch (Exception ex) {
                LOGGER.error(ex);
            } finally {
                LOGGER.debug("inside---------finaly-----------------------------------");
                if (invalidTable.equalsIgnoreCase("StArpOutbound")) {
                    LOGGER.debug("ARP_OUTBOUND--------------------");
                    arpProcess.unlock();
                } else if (invalidTable.equalsIgnoreCase(ConstantUtil.ST_CFF_OUTBOUND)) {
                    LOGGER.debug("ST_CFF_OUTBOUND----------------");
                    process.unlock();
                } else if (invalidTable.equalsIgnoreCase(ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL)) {
                    GtnProcess.unlock();
                } else if (invalidTable.equalsIgnoreCase(ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL)) {
                    reserveProcess.unlock();
                }
            }
        }
    }

    public void updateFlagForReprocessedOrRemovedRecords() {
        String tableName = (String) VaadinSession.getCurrent().getAttribute(ConstantUtil.INVALID_TABLE_NAME);
        if (ConstantUtil.VW_IVLD_DEMAND_FPRECAST.equalsIgnoreCase(tableName)) {
            if (ConstantUtil.PROJECTION.equalsIgnoreCase(tableValue1)) {
                tableName = ConstantUtil.IVLD_DEMAND_FORECAST;
            } else {
                tableName = ConstantUtil.IVLD_DEMAND_ACTUAL;
            }
        }
        if (ConstantUtil.VW_IVLD_ADJ_DEMAND_FORE_ACTUAL.equalsIgnoreCase(tableName)) {
            if (ConstantUtil.PROJECTION.equalsIgnoreCase(tableValue1)) {
                tableName = ConstantUtil.IVLD_ADJUSTED_DEMAND_FORECAST;
            } else {
                tableName = ConstantUtil.IVLD_ADJUSTED_DEMAND_ACTUAL;
            }
        }
        if (ConstantUtil.VW_IVLD_INVENTORY_WD_ACTUAL_PROJ_MAS.equalsIgnoreCase(tableName)) {
            if (ConstantUtil.PROJECTION.equalsIgnoreCase(typeValue)) {
                tableName = ConstantUtil.IVLD_INVENTORY_WD_PROJ_MAS;
            } else {
                tableName = ConstantUtil.IVLD_INVENTORY_WD_ACTUAL_MAS;
            }
        }
        String query = " Update " + tableName + " set REPROCESSED_FLAG = 'Y' where CHECK_RECORD = 1 ;";
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
    }
   
    public void runJob(FtpProperties ftpProperties, String processName, boolean isToWait,List<Integer> selectedRecords,String viewNameSid,String stagingTable) {
        LOGGER.debug("inside runjob---------------");
        String invalidTableName = (String) VaadinSession.getCurrent().getAttribute(ConstantUtil.INVALID_TABLE_NAME);
        try {
            String jbossHome = System.getProperty("jboss.home.dir");
            if (!"null".equals(jbossHome)) {
                String ftppath[] = jbossHome.split("jboss-7.1.1");
                if (ftppath.length != 0) {
                    java.util.Properties prop = getPropertyFile(ftppath[0] + FTP_PROPERTIES_PATH);
                    LOGGER.debug("after getPropertyFile---------------");
                    ftpProperties.setScripts(prop.getProperty("scripts"));
                    LOGGER.debug("after ftpProperties---------------");
                    if (isToWait) {
                        runShellScriptAndWait(ftpProperties.getScripts(), processName);
                    } else {
                        runShellScript(ftpProperties.getScripts(), processName);
                        updateReprocessedFlag(selectedRecords, stagingTable, processName, viewNameSid, invalidTableName);     // Added for CEL-330      
                    }
                }
            }
            LOGGER.debug("run job ends--------------??????????");
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    private void runShellScript(String scriptPath, String scriptName) {
        try {
            LOGGER.debug("run shell script method");

            String cmd = scriptPath + "/" + scriptName; // this is the command to execute in the Unix shell
            LOGGER.debug("cmd----" + cmd);
            ProcessBuilder pb = new ProcessBuilder("bash", "-c", cmd);
            pb.redirectErrorStream(true); // use this to capture messages sent to stderr
            Process shell = pb.start();
            InputStream shellIn = shell.getInputStream(); // this captures the output from the command
            try {
                shellIn.close();
            } catch (IOException ignoreMe) {
                LOGGER.error(ignoreMe);
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    private void runShellScriptAndWait(String scriptPath, String scriptName) {
        try {
            LOGGER.debug("run shell script method");

            String cmd = scriptPath + "/" + scriptName; // this is the command to execute in the Unix shell
            LOGGER.debug("Script is waiting till Process to Exist for " + cmd);
            ProcessBuilder pb = new ProcessBuilder("bash", "-c", cmd);
            pb.redirectErrorStream(true); // use this to capture messages sent to stderr
            Process shell = pb.start();
            LOGGER.debug("Script Started");
            LOGGER.debug("Script Pid Exists with code " + shell.waitFor());
            InputStream shellIn = shell.getInputStream(); // this captures the output from the command
            try {
                shellIn.close();
            } catch (IOException ignoreMe) {
                LOGGER.error(ignoreMe);
            }
        } catch (IOException | InterruptedException ex) {
            LOGGER.error(ex);
        }
    }

    public static FtpProperties getFtpBundleValue() {
        FtpProperties ftpProperties = new FtpProperties();
        try {
            String jbossHome = System.getProperty("jboss.home.dir");
            if (!"null".equals(jbossHome)) {
                String ftppath[] = jbossHome.split("jboss-7.1.1");
                if (ftppath.length != 0) {
                    LOGGER.info(ftppath[0] + FTP_PROPERTIES_PATH);
                    java.util.Properties prop = getPropertyFile(ftppath[0] + FTP_PROPERTIES_PATH);
                    ftpProperties.setScripts(prop.getProperty("scripts"));
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return ftpProperties;
    }

    private static java.util.Properties getPropertyFile(String bpiPropLoc) {
        LOGGER.debug("inside getPropertyFile---------------");
        java.util.Properties prop = new java.util.Properties();
        try {
            FileInputStream fileIS = null;
            fileIS = new FileInputStream(bpiPropLoc);
            prop.load(fileIS);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.debug("inside getPropertyFile--------------end -");
        return prop;

    }

    public static String getDescription(int code) {
        try {
            HelperTable table = HelperTableLocalServiceUtil.getHelperTable(code);
            return table.getDescription();
        } catch (PortalException ex) {
            LOGGER.error(ex);
            return null;
        } catch (SystemException ex) {
            LOGGER.error(ex);
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

    public void generateOutbound(List Accrualsid, List<Integer> selectedRecords, String viewNameSid,String stagingTable) {
        String query = "insert into TEMP_ACCRUAL_DETAILS values (" + Accrualsid.get(0) + ")";
        for (int i = NumericConstants.ONE; i < Accrualsid.size(); i++) {
            query += ",(" + Accrualsid.get(i) + ")";

        }
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
        String interfaceScriptName = InterfaceScripts.getString("ACCRUAL_OUTBOUND");
        runJob(getFtpBundleValue(), interfaceScriptName, false,selectedRecords,viewNameSid,stagingTable);

    }

    public static String dataForCFFoutbound(Map<Object, Object> searchValues) {
        String tempValue;
        String query = " WHERE";

        if (searchValues.containsKey(ConstantUtil.CFF_ID)) {
            tempValue = searchValues.get(ConstantUtil.CFF_ID).toString().replace(ConstantUtil.CHAR_ASTERISK, ConstantUtil.CHAR_PERCENT);
            query += " AND CFF.FINANCIAL_FORECAST_ID like '" + tempValue + ConstantUtil.APOSTROPHE;
        }
        if (searchValues.containsKey(ConstantUtil.PROJECTION_ID)) {
            tempValue = searchValues.get(ConstantUtil.PROJECTION_ID).toString().replace(ConstantUtil.CHAR_ASTERISK, ConstantUtil.CHAR_PERCENT);
            query += " AND CFF.PROJECT_ID like '" + tempValue + ConstantUtil.APOSTROPHE;
        }
        if (searchValues.containsKey(ConstantUtil.COMPANY_NO)) {
            tempValue = searchValues.get(ConstantUtil.COMPANY_NO).toString().replace(ConstantUtil.CHAR_ASTERISK, ConstantUtil.CHAR_PERCENT);
            query += " AND CFF.COMPANY_NO like '" + tempValue + ConstantUtil.APOSTROPHE;
        }
        if (searchValues.containsKey(ConstantUtil.COMPANY_NAME)) {
            tempValue = searchValues.get(ConstantUtil.COMPANY_NAME).toString().replace(ConstantUtil.CHAR_ASTERISK, ConstantUtil.CHAR_PERCENT);
            query += " AND CFF.COMPANY_NAME like '" + tempValue + ConstantUtil.APOSTROPHE;
        }
        if (searchValues.containsKey(ConstantUtil.BUSINESS_UNIT_NAME)) {
            tempValue = searchValues.get(ConstantUtil.BUSINESS_UNIT_NAME).toString().replace(ConstantUtil.CHAR_ASTERISK, ConstantUtil.CHAR_PERCENT);
            query += " AND CFF.BUSINESS_UNIT_NAME like '" + tempValue + ConstantUtil.APOSTROPHE;
        }
        if (searchValues.containsKey(ConstantUtil.BUSINESS_UNIT_NO)) {
            tempValue = searchValues.get(ConstantUtil.BUSINESS_UNIT_NO).toString().replace(ConstantUtil.CHAR_ASTERISK, ConstantUtil.CHAR_PERCENT);
            query += " AND CFF.BUSINESS_UNIT_NO like '" + tempValue + ConstantUtil.APOSTROPHE;
        }
        if (searchValues.containsKey(ConstantUtil.CUSTOMER_NO)) {
            tempValue = searchValues.get(ConstantUtil.CUSTOMER_NO).toString().replace(ConstantUtil.CHAR_ASTERISK, ConstantUtil.CHAR_PERCENT);
            query += " AND CFF.CUSTOMER_NO like '" + tempValue + ConstantUtil.APOSTROPHE;
        }
        if (searchValues.containsKey(ConstantUtil.CFF_NAME)) {
            tempValue = searchValues.get(ConstantUtil.CFF_NAME).toString().replace(ConstantUtil.CHAR_ASTERISK, ConstantUtil.CHAR_PERCENT);
            query += " AND CFF.FINANCIAL_FORECAST_NAME like '" + tempValue + ConstantUtil.APOSTROPHE;
        }
        if (searchValues.containsKey(ConstantUtil.PROJECTION_NAME)) {
            tempValue = searchValues.get(ConstantUtil.PROJECTION_NAME).toString().replace(ConstantUtil.CHAR_ASTERISK, ConstantUtil.CHAR_PERCENT);
            query += " AND CFF.PROJECTION_NAME LIKE '" + tempValue + ConstantUtil.APOSTROPHE;
        }
        if (searchValues.containsKey(ConstantUtil.CUSTOMER_NAME)) {
            tempValue = searchValues.get(ConstantUtil.CUSTOMER_NAME).toString().replace(ConstantUtil.CHAR_ASTERISK, ConstantUtil.CHAR_PERCENT);
            query += " AND CFF.CUSTOMER_NAME LIKE '" + tempValue + ConstantUtil.APOSTROPHE;
        }
        if (searchValues.containsKey(ConstantUtil.CONTRACT_NO)) {
            tempValue = searchValues.get(ConstantUtil.CONTRACT_NO).toString().replace(ConstantUtil.CHAR_ASTERISK, ConstantUtil.CHAR_PERCENT);
            query += " AND CFF.CONTRACT_NO LIKE '" + tempValue + ConstantUtil.APOSTROPHE;
        }
        if (searchValues.containsKey(ConstantUtil.CONTRACT_NAME)) {
            tempValue = searchValues.get(ConstantUtil.CONTRACT_NAME).toString().replace(ConstantUtil.CHAR_ASTERISK, ConstantUtil.CHAR_PERCENT);
            query += " AND CFF.CONTRACT_NAME LIKE '" + tempValue + ConstantUtil.APOSTROPHE;
        }
        if (searchValues.containsKey(ConstantUtil.ITEMNO)) {
            tempValue = searchValues.get(ConstantUtil.ITEMNO).toString().replace(ConstantUtil.CHAR_ASTERISK, ConstantUtil.CHAR_PERCENT);
            query += " AND CFF.ITEM_NO LIKE '" + tempValue + ConstantUtil.APOSTROPHE;
        }
        if (searchValues.containsKey(ConstantUtil.ITEM_NAME)) {
            query += " AND CFF.ITEM_NAME LIKE '" + searchValues.get(ConstantUtil.ITEM_NAME) + ConstantUtil.APOSTROPHE;
        }
        if (searchValues.containsKey(ConstantUtil.CFF_CREATION_FROM_DATE) && searchValues.containsKey(ConstantUtil.CFF_CREATION_TO_DATE)) {
            try {
                String formattedDateFrom = formattedDate.format(dateformate.parse(searchValues.get(ConstantUtil.CFF_CREATION_FROM_DATE).toString()));
                String formattedDateTo = formattedDate.format(dateformate.parse(searchValues.get(ConstantUtil.CFF_CREATION_TO_DATE).toString()));
                query += " AND CFF.FINANCIAL_FORECAST_CREATION_DATE BETWEEN '" + formattedDateFrom + "' AND DATEADD(dd,1,'" + formattedDateTo + ConstantUtil.APOSTROPHE + ")";
            } catch (ParseException ex) {
                LOGGER.error(ex);
            }
        } else if (searchValues.containsKey(ConstantUtil.CFF_CREATION_FROM_DATE)) {
            try {
                String formattedDateFrom = formattedDate.format(dateformate.parse(searchValues.get(ConstantUtil.CFF_CREATION_FROM_DATE).toString()));
                query += " AND CFF.FINANCIAL_FORECAST_CREATION_DATE >= '" + formattedDateFrom + ConstantUtil.APOSTROPHE;
            } catch (ParseException ex) {
                LOGGER.error(ex);
            }
        } else if (searchValues.containsKey(ConstantUtil.CFF_CREATION_TO_DATE)) {
            try {
                String formattedDateTo = formattedDate.format(dateformate.parse(searchValues.get(ConstantUtil.CFF_CREATION_TO_DATE).toString()));
                query += " AND CFF.FINANCIAL_FORECAST_CREATION_DATE <= '" + formattedDateTo + ConstantUtil.APOSTROPHE + " OR CFF.FINANCIAL_FORECAST_CREATION_DATE < DATEADD(dd,1,'" + formattedDateTo + ConstantUtil.APOSTROPHE + ")";
            } catch (ParseException ex) {
                LOGGER.error(ex);
            }
        }
        if (searchValues.containsKey(ConstantUtil.CFF_APPROVED_FROM_DATE) && searchValues.containsKey(ConstantUtil.CFF_APPROVED_TO_DATE)) {
            try {
                String formattedDateFrom = formattedDate.format(dateformate.parse(searchValues.get(ConstantUtil.CFF_APPROVED_FROM_DATE).toString()));
                String formattedDateTo = formattedDate.format(dateformate.parse(searchValues.get(ConstantUtil.CFF_APPROVED_TO_DATE).toString()));
                query += " AND CFF.FINANCIAL_FORECAST_APPROVAL_DATE BETWEEN '" + formattedDateFrom + "' AND DATEADD(dd,1,'" + formattedDateTo + ConstantUtil.APOSTROPHE + ")";
            } catch (ParseException ex) {
                LOGGER.error(ex);
            }
        } else if (searchValues.containsKey(ConstantUtil.CFF_APPROVED_FROM_DATE)) {
            try {

                String formattedDateFrom = formattedDate.format(dateformate.parse(searchValues.get(ConstantUtil.CFF_APPROVED_FROM_DATE).toString()));

                query += " AND CFF.FINANCIAL_FORECAST_APPROVAL_DATE >= '" + formattedDateFrom + ConstantUtil.APOSTROPHE;
            } catch (ParseException ex) {
                LOGGER.error(ex);
            }
        } else if (searchValues.containsKey(ConstantUtil.CFF_APPROVED_TO_DATE)) {
            try {
                String formattedDateTo = formattedDate.format(dateformate.parse(searchValues.get(ConstantUtil.CFF_APPROVED_TO_DATE).toString()));

                query += " AND CFF.FINANCIAL_FORECAST_APPROVAL_DATE <= '" + formattedDateTo + ConstantUtil.APOSTROPHE + " OR CFF.FINANCIAL_FORECAST_APPROVAL_DATE < DATEADD(dd,1,'" + formattedDateTo + ConstantUtil.APOSTROPHE + ")";
            } catch (ParseException ex) {
                LOGGER.error(ex);
            }
        }

        if (searchValues.containsKey(ConstantUtil.TYPE)) {
            SearchLogic searchlogic = new SearchLogic();
            tempValue = searchlogic.getHelperValues(searchValues.get(ConstantUtil.TYPE).toString(), ConstantUtil.CFF_TYPE);
            query += " AND CFF.TYPE = '" + tempValue + ConstantUtil.APOSTROPHE;
        }
        query = query.replace("WHERE AND", ConstantUtil.WHERE);
        return query;
    }

    public static String dataForARMoutbound(Map<Object, Object> searchValues,String query,String userId,String sessionId) {
      query=query.replace("$DEDUCTION_LEVEL", searchValues.containsKey("deductionLevel")? " = '" +searchValues.get("deductionLevel").toString()+ConstantUtil.APOSTROPHE : StringUtils.EMPTY);
      query=query.replace("$DEDUCTION_VALUE", searchValues.containsKey("deductionValue")? " = '" +searchValues.get("deductionValue").toString()+ConstantUtil.APOSTROPHE : StringUtils.EMPTY);
      query=query.replace("$WORKFLOW_ID", searchValues.containsKey("workflowId")? " = '" +searchValues.get("workflowId").toString().replace(ConstantUtil.CHAR_ASTERISK, ConstantUtil.CHAR_PERCENT)+ConstantUtil.APOSTROPHE : StringUtils.EMPTY);
      query=query.replace("$WORKFLOW_NAME", searchValues.containsKey("workflowName")? " = '" +searchValues.get("workflowName").toString().replace(ConstantUtil.CHAR_ASTERISK, ConstantUtil.CHAR_PERCENT)+ConstantUtil.APOSTROPHE : StringUtils.EMPTY);
      query=query.replace("$COMPANY_NO", searchValues.containsKey("companyNo")? " = '" +searchValues.get("companyNo").toString().replace(ConstantUtil.CHAR_ASTERISK, ConstantUtil.CHAR_PERCENT)+ConstantUtil.APOSTROPHE  : StringUtils.EMPTY);
      query=query.replace("$COMPANY_NAME", searchValues.containsKey("companyName")? " = '" +searchValues.get("companyName").toString().replace(ConstantUtil.CHAR_ASTERISK, ConstantUtil.CHAR_PERCENT)+ConstantUtil.APOSTROPHE  : StringUtils.EMPTY);
      query=query.replace("$ITEM_NO", searchValues.containsKey("itemNo")? " = '" +searchValues.get("itemNo").toString().replace(ConstantUtil.CHAR_ASTERISK, ConstantUtil.CHAR_PERCENT)+ConstantUtil.APOSTROPHE  : StringUtils.EMPTY);
      query=query.replace("$ITEM_NAME", searchValues.containsKey("itemName")? " = '" +searchValues.get("itemName").toString().replace(ConstantUtil.CHAR_ASTERISK, ConstantUtil.CHAR_PERCENT)+ConstantUtil.APOSTROPHE  : StringUtils.EMPTY);
      query=query.replace("$BRAND_NAME", searchValues.containsKey("brandName")? " = '" +searchValues.get("brandName").toString().replace(ConstantUtil.CHAR_ASTERISK, ConstantUtil.CHAR_PERCENT)+ConstantUtil.APOSTROPHE  : StringUtils.EMPTY);
      query=query.replace("$ADJUSTMENT_TYPE", searchValues.containsKey("adjustmentType")? " = '" +searchValues.get("adjustmentType").toString()+ConstantUtil.APOSTROPHE  : StringUtils.EMPTY);
      query=query.replace("$TRANSACTION_LEVEL", searchValues.containsKey("transactionLevel")? " = '" +searchValues.get("transactionLevel").toString()+ConstantUtil.APOSTROPHE  : StringUtils.EMPTY);
      query=query.replace("$GL_COMPANY_MASTER_SID", searchValues.containsKey("glCompanyName")? " = '" +searchValues.get("glCompanyName").toString()+ConstantUtil.APOSTROPHE  : StringUtils.EMPTY);
      query=query.replace("$BU_COMPANY_MASTER_SID", searchValues.containsKey("businessUnitId")? " = '" +searchValues.get("businessUnitId").toString()+ConstantUtil.APOSTROPHE  : StringUtils.EMPTY);
      query=query.replace("$GL_DATE", searchValues.containsKey("glDate")? " = '" +searchValues.get("glDate").toString()+ConstantUtil.APOSTROPHE  : StringUtils.EMPTY);
      query=query.replace("$CREATED_DATE", searchValues.containsKey(ConstantUtil.CREATED_DATE)? " = '" +searchValues.get(ConstantUtil.CREATED_DATE).toString()+ConstantUtil.APOSTROPHE  : StringUtils.EMPTY);
      query=query.replace("$ACCOUNT_CATEGORY", searchValues.containsKey("accountCategory")? " = '" +searchValues.get("accountCategory").toString()+ConstantUtil.APOSTROPHE  : StringUtils.EMPTY);
      query=query.replace("$ACCOUNT_TYPE", searchValues.containsKey("accountType")? " = '" +searchValues.get("accountType").toString()+ConstantUtil.APOSTROPHE  : StringUtils.EMPTY);
      query=query.replace("$ACCOUNT_NAME", searchValues.containsKey("account")? " = '" +searchValues.get("account").toString().replace(ConstantUtil.CHAR_ASTERISK, ConstantUtil.CHAR_PERCENT)+ConstantUtil.APOSTROPHE  : StringUtils.EMPTY);
      query=query.replace("$POSITION_INDICATOR", searchValues.containsKey("postingIndicator")? " = '" +searchValues.get("postingIndicator").toString()+ConstantUtil.APOSTROPHE : StringUtils.EMPTY);
      query=query.replace("$ADJUSTMENT_LEVEL", searchValues.containsKey("adjustmentLevel")? " = '" +searchValues.get("adjustmentLevel").toString()+ConstantUtil.APOSTROPHE  : StringUtils.EMPTY);
      query=query.replace("$REDEMPTION_FROM_PERIOD", searchValues.containsKey("redemptionPeriod")? " = '" +searchValues.get("redemptionPeriod").toString()+ConstantUtil.APOSTROPHE  : StringUtils.EMPTY);
      query=query.replace("$REDEMPTION_TO_PERIOD", searchValues.containsKey(ConstantUtil.REDEMPTION_TO_DATE)? " = '" +searchValues.get(ConstantUtil.REDEMPTION_TO_DATE).toString()+ConstantUtil.APOSTROPHE  : StringUtils.EMPTY);
      query=query.replace("$BATCH_ID", searchValues.containsKey("originalBatchId")? " = '" +searchValues.get("originalBatchId").toString().replace(ConstantUtil.CHAR_ASTERISK, ConstantUtil.CHAR_PERCENT)+ConstantUtil.APOSTROPHE  : StringUtils.EMPTY);
      query=query.replace("$SESSION_ID", sessionId );
      query=query.replace("$USER_ID",userId);
        return query;
    }
 
    public String[] getArpOutBoundHeader(String[] column) {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        String[] returnHeader = new String[column.length];
        for (int i = 0; i < column.length; i++) {
            String header = column[i];
            if (header.contains("Current Year")) {
                if (header.contains("+2")) {
                    header = header.replace("Current Year+2", "");
                    header = year + NumericConstants.TWO + header;
                } else if (header.contains("+1")) {
                    header = header.replace("Current Year+1", "");
                    header = year + 1 + header;
                } else {
                    header = header.replace("Current Year", "");
                    header = year + header;
                }
                returnHeader[i] = header;
            } else {
                returnHeader[i] = header;

            }

        }

        return returnHeader;

    }

    public Date parseDate(String d) {
        String[] formats = {
            "dd-MMM-yy", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd",
            "yyyy-MM-dd HH:mm:ss.S", "MM/dd/yyyy HH:mm:ss", "dd/MM/yyyy", "MMM dd yyyy hh:mm", "dd/MM/yy",
            "dd/MM/yyyy", "d/M/yy", "d/M/yyyy", "ddMMyy", "ddMMyyyy", "ddMMMyy", "ddMMMyyyy", "dd-MMM-yyyy", "dMMMyy",
            "dMMMyyyy", "d-MMM-yy", "d-MMM-yyyy", "d-MMMM-yy", "d-MMMM-yyyy", "yyMMdd", "yyyyMMdd", "yy/MM/dd",
            "yyyy/MM/dd", "MMddyy", "MMddyyyy", "MM/dd/yy", "MM/dd/yyyy", "MMM-dd-yy", "MMM-dd-yyyy", "yyyy-MM-dd", "MMM dd yyyy hh:mm aaa"};

        if (d != null) {
            for (String parse : formats) {
                SimpleDateFormat sdf = new SimpleDateFormat(parse);
                try {
                    sdf.setLenient(false);
                    return sdf.parse(d);
                } catch (ParseException e) {
                    continue;
                }
            }
        }
        return null;
    }

    public void tableAlign(ExtPagedTable table) {
        table.setColumnAlignment(ConstantUtil.CREATED_DATE, ExtCustomTable.Align.CENTER);
        table.setColumnAlignment("modifiedDate", ExtCustomTable.Align.CENTER);
        table.setColumnAlignment("amount", ExtCustomTable.Align.RIGHT);
        table.setColumnAlignment("units", ExtCustomTable.Align.RIGHT);
    }

    public List<Object> getARPExcelList(final Map<Object, Object> searchValues, final int start, final int end,
             String tableName) throws ParseException {
        SearchLogic sl = new SearchLogic();
        List<Object> list = null;

        if (ConstantUtil.ARP_OUTBOUND.equals(tableName)) {
            list = sl.searchFindForArpOutBound(searchValues, start, end, null, null, true,false);
        }
        return list;
    }
    public List<?> getColumns(String flag) {
        String query = "select PROPERTY_NAME,DISPLAY_NAME from TRANSACTION_MODULE_DETAILS TD JOIN \n"
                + "TRANSACTION_MODULE_MASTER TM on  TD.TRANSACTION_MODULE_MASTER_SID=TM.TRANSACTION_MODULE_MASTER_SID \n"
                + "WHERE TM.MODULE_NAME='AdjustmentDetails' and TD.CATEGORY_NAME='Column' and FLAG='" + flag + "' ORDER BY PROPERTY_INDEX";
        List<Object[]> obj = HelperTableLocalServiceUtil.executeSelectQuery(query);
        Object[] columns = new Object[obj.size()];
        String[] headers = new String[obj.size()];

        int i = 0;
        for (Object[] objects : obj) {
            columns[i] = objects[0];
            headers[i] = String.valueOf(objects[NumericConstants.ONE]);
            i++;
        }
        obj.clear();
        obj.add(columns);
        obj.add(headers);
        return obj;
    }

    public void dataForAdjustmentGTNoutbound(String userId, String sessionId) {

        String deleteQuery = "DELETE FROM ST_ADJUSTMENT_GTN_DETAIL WHERE USER_ID = " + userId + " AND SESSION_ID ='" + sessionId + "' ";
        HelperTableLocalServiceUtil.executeUpdateQuery(deleteQuery);
    }

    public void dataForAdjustmentReserveoutbound(String userId, String sessionId) {
        String deleteQuery = "DELETE FROM ST_ADJUSTMENT_RESERVE_DETAIL WHERE USER_ID = " + userId + " AND SESSION_ID ='" + sessionId + "' ";
        HelperTableLocalServiceUtil.executeUpdateQuery(deleteQuery);
    }

    public String getHelperTableSidforReserveGTN() {
        String query = "SELECT HELPER_TABLE_SID from HELPER_TABLE where LIST_NAME = 'ARM_CONFIGURATION_TYPE' and DESCRIPTION = 'Reserve Detail'";
        List<Object> list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        return list == null ? "" : String.valueOf(list.get(0));

    }
    
    public String getHelperTableSidforPostingIndicator(String id) {
        String query = "SELECT DESCRIPTION from HELPER_TABLE where LIST_NAME = 'ARM_POSTING_INDICATOR' and HELPER_TABLE_SID =" + id;
        List<Object> list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        return list == null ? "" : String.valueOf(list.get(0));

    }

    public List<Object> getARPExcelList(final Map<Object, Object> searchValues) throws ParseException  {
        SearchLogic sl = new SearchLogic();
        return sl.searchFindForArpOutBound(searchValues, 0, 0, null, null, true, false);
    }
    
    public void updateInvalidTable(int selectedRecordSid, String selectedRecordProperty, String invalidTable, int isChecked,String dropValue) {
    
        String insertSql = "";
        if (invalidTable.equalsIgnoreCase(ConstantUtil.VW_IVLD_INVENTORY_WD_ACTUAL_PROJ_MAS)) {
            if (ConstantUtil.ACTUALS.equalsIgnoreCase(typeValue)) {
                if (dropValue.equalsIgnoreCase("1")) {
                    insertSql = "UPDATE IVLD_INVENTORY_WD_ACTUAL_MAS SET CHECK_RECORD = " + isChecked + " WHERE IVLD_INVENTORY_WD_ACTUAL_MAS_SID = " + selectedRecordSid + "";
            } else {
                    insertSql = "UPDATE IVLD_INVENTORY_WD_ACTUAL_DT SET CHECK_RECORD = " + isChecked + " WHERE IVLD_INVENTORY_WD_ACTUAL_DT_SID = " + selectedRecordSid + "";
            }
            } else {
                 if (dropValue.equalsIgnoreCase("1")) {
                    insertSql = "UPDATE IVLD_INVENTORY_WD_PROJ_MAS SET CHECK_RECORD = " + isChecked + " WHERE IVLD_INVENTORY_WD_PROJ_MAS_SID = " + selectedRecordSid + " ";
                } else {
                    insertSql = "UPDATE IVLD_INVENTORY_WD_PROJ_DT SET CHECK_RECORD = " + isChecked + " WHERE IVLD_INVENTORY_WD_PROJ_DT_sid = " + selectedRecordSid + "";
                }
            }
        } else if (invalidTable.equalsIgnoreCase(ConstantUtil.VW_IVLD_DEMAND_FPRECAST)) {
            if (ConstantUtil.PROJECTION.equalsIgnoreCase(tableValue1)) {
              insertSql = "UPDATE IVLD_DEMAND_FORECAST SET CHECK_RECORD = " + isChecked + " WHERE IVLD_DEMAND_FORECAST_SID = " + selectedRecordSid + " ";
            } else {
                insertSql = "UPDATE IVLD_DEMAND_ACTUAL SET CHECK_RECORD = " + isChecked + " WHERE IVLD_DEMAND_ACTUAL_SID = " + selectedRecordSid + " ";
            }
        } else if (invalidTable.equalsIgnoreCase(ConstantUtil.VW_IVLD_ADJ_DEMAND_FORE_ACTUAL)) {
            if (ConstantUtil.PROJECTION.equalsIgnoreCase(tableValue1)) {
                insertSql = "UPDATE IVLD_ADJUSTED_DEMAND_FORECAST SET CHECK_RECORD = " + isChecked + " WHERE IVLD_ADJUSTED_DEMAND_FORECAST_SID = " + selectedRecordSid + "";
            } else {
                 insertSql = "UPDATE IVLD_ADJUSTED_DEMAND_ACTUAL SET CHECK_RECORD = " + isChecked + " WHERE IVLD_ADJUSTED_DEMAND_ACTUAL_SID = " + selectedRecordSid + "";
            }
        } else {
            insertSql = "UPDATE " + invalidTable + " SET CHECK_RECORD = " + isChecked + ConstantUtil.WHERE + selectedRecordProperty + " = " + selectedRecordSid + ";";
        }
        System.out.println("insertSql = " + insertSql);
        HelperTableLocalServiceUtil.executeUpdateQuery(insertSql);
    }
    public boolean checkandUncheckALL(String invalidTableName) {
        String insertSql = "SELECT  COUNT(*) FROM " + invalidTableName+" WHERE CHECK_RECORD = 0 AND REPROCESSED_FLAG = 'N'";
        int count =(Integer) HelperTableLocalServiceUtil.executeSelectQuery(insertSql).get(0);
        return count == 0;
    }
    public boolean CFFcheckandUncheckALL(String userId, String sessionId) {
        String insertSql = "SELECT COUNT(*) FROM ST_CFF_OUTBOUND_MASTER WHERE CHECK_RECORD = 0" +" AND USER_ID= " +userId+ " AND SESSION_ID= " +sessionId;
        int count =(Integer) HelperTableLocalServiceUtil.executeSelectQuery(insertSql).get(0);
        return count == 0;
    }
    
    public void updateAllInInvalidTable(String inValidTableName, String isChecked) {
        try{
        String tableName = inValidTableName;
        if((ConstantUtil.VW_IVLD_DEMAND_FPRECAST).equals(inValidTableName) && invalidAdjustedDemand){
            inValidTableName=ConstantUtil.INVALID_ADJUST_DEMAND_VIEW;
        }
        if (ConstantUtil.VW_IVLD_DEMAND_FPRECAST.equalsIgnoreCase(inValidTableName)) {
            if (ConstantUtil.PROJECTION.equalsIgnoreCase(tableValue1)) {
                tableName = ConstantUtil.IVLD_DEMAND_FORECAST;
            } else {
                tableName = ConstantUtil.IVLD_DEMAND_ACTUAL;
            }
        }
        if (ConstantUtil.INVALID_ADJUST_DEMAND_VIEW.equalsIgnoreCase(inValidTableName)) {
            if (ConstantUtil.PROJECTION.equalsIgnoreCase(tableValue1)) {
                tableName = ConstantUtil.IVLD_ADJUSTED_DEMAND_FORECAST;
            } else {
                tableName = ConstantUtil.IVLD_ADJUSTED_DEMAND_ACTUAL;
            }
        }
        if (ConstantUtil.VW_IVLD_INVENTORY_WD_ACTUAL_PROJ_MAS.equalsIgnoreCase(inValidTableName)) {
            if (ConstantUtil.PROJECTION.equalsIgnoreCase(typeValue)) {
                tableName = ConstantUtil.IVLD_INVENTORY_WD_PROJ_MAS;
            } else {
                tableName = ConstantUtil.IVLD_INVENTORY_WD_ACTUAL_MAS;
            }
        }
        String query = ConstantUtil.UPDATE + tableName + " SET CHECK_RECORD =" + isChecked + " ;";
        System.out.println("query = " + query);
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    public void updateAllInInvalidTableOnCheckAll(List<Integer> selectedRecords, String selectedRecordProperty,String inValidTableName, String isChecked, boolean isReprocess,String batchId) {
        String tableName = inValidTableName;
        if (ConstantUtil.VW_IVLD_DEMAND_FPRECAST.equalsIgnoreCase(inValidTableName)) {
            if (ConstantUtil.PROJECTION.equalsIgnoreCase(tableValue1)) {
                tableName = ConstantUtil.IVLD_DEMAND_FORECAST;
            } else {
                tableName = ConstantUtil.IVLD_DEMAND_ACTUAL;
            }
        }
        if (ConstantUtil.VW_IVLD_ADJ_DEMAND_FORE_ACTUAL.equalsIgnoreCase(inValidTableName)) {
            if (ConstantUtil.PROJECTION.equalsIgnoreCase(tableValue1)) {
                tableName = ConstantUtil.IVLD_ADJUSTED_DEMAND_FORECAST;
            } else {
                tableName = ConstantUtil.IVLD_ADJUSTED_DEMAND_ACTUAL;
            }
        }
        if (ConstantUtil.VW_IVLD_INVENTORY_WD_ACTUAL_PROJ_MAS.equalsIgnoreCase(inValidTableName)) {
            if (ConstantUtil.PROJECTION.equalsIgnoreCase(typeValue)) {
                tableName = ConstantUtil.IVLD_INVENTORY_WD_PROJ_MAS;
            } else {
                tableName = ConstantUtil.IVLD_INVENTORY_WD_ACTUAL_MAS;
            }
        }
        String selectedRecordsList = StringUtils.EMPTY;
        for (int i = 0; i < selectedRecords.size(); i++) {
            if(i != 0){
                selectedRecordsList =  selectedRecordsList+ "," +selectedRecords.get(i);
            }  else {
                selectedRecordsList = selectedRecordsList+selectedRecords.get(i);
            }          
        }
         String query = StringUtils.EMPTY;
        if(!isReprocess){
            query = ConstantUtil.UPDATE + tableName + " SET CHECK_RECORD =" + isChecked + ConstantUtil.WHERE + selectedRecordProperty + " in ( " + selectedRecordsList + ");";
        } else {
            if(!batchId.isEmpty()){
                query = ConstantUtil.UPDATE + tableName + " SET REPROCESSED_FLAG = 'N' WHERE "+selectedRecordProperty + " in ( " + selectedRecordsList + ");";
            } else {
                
            }            
        }
        
        HelperTableLocalServiceUtil.executeUpdateQuery(query);
    }
    
    public boolean isRecordChecked(String inValidTableName) {

        if ((ConstantUtil.VW_IVLD_DEMAND_FPRECAST).equals(inValidTableName) && invalidAdjustedDemand) {
            inValidTableName = ConstantUtil.INVALID_ADJUST_DEMAND_VIEW;

        }
        String query = " SELECT COUNT(*) FROM " + inValidTableName + " WHERE CHECK_RECORD = 1 ;";
        System.out.println("query = " + query);
        List<Object> list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        return list == null ? false : (int) list.get(0) != 0;
    }

    public boolean AllInvalidScreenTables(String tableName) {

        return ConstantUtil.INVALID_COMPANY_MASTER.equals(tableName)
                || ConstantUtil.INVALID_COMPANY_IDENTIFIER.equals(tableName)
                || ConstantUtil.INVALID_COMPANY_PARENT.equals(tableName)
                || ConstantUtil.INVALID_COMPANY_TRADE_CLASS.equals(tableName)
                || ConstantUtil.IBID_ITEM_MASTER.equals(tableName)
                || ConstantUtil.IVID_ITEM_IDENTIFIER.equals(tableName)
                || ConstantUtil.IVID_ITEM_PRICING.equals(tableName)
                || ConstantUtil.INVALID_CUSTOMER_GTS_ACTUAL.equals(tableName)
                || ConstantUtil.INVALID_GTS_CUSTOMER.equals(tableName)
                || ConstantUtil.IVLD_RETURN_RESERVE.equals(tableName)
                || ConstantUtil.INVALID_INVENTORYVIEW_TABLE.equals(tableName)
                || ConstantUtil.IVLD_RETURN_RESERVE.equals(tableName)
                || ConstantUtil.INVENTORYVIEW_TABLE_IN_VIEW.equals(tableName)
                || ConstantUtil.INVALID_ACCURAL_INBOUND.equals(tableName)
                || ConstantUtil.INVALID_INVENTORYVIEW_TABLE_IN_VIEW.equals(tableName);
    }

    public boolean AllValidTables(String tableName) {

        return ConstantUtil.VW_COMPANY_MASTER.equals(tableName)
                || ConstantUtil.VW_COMPANY_IDENTIFIER.equals(tableName)
                || ConstantUtil.VW_COMPANY_PARENT_DETAILS.equals(tableName)
                || ConstantUtil.VW_COMPANY_TRADE_CLASS.equals(tableName)
                || ConstantUtil.VW_ITEM_MASTER.equals(tableName)
                || ConstantUtil.VW_ITEM_IDENTIFIER.equals(tableName)
                || ConstantUtil.VW_ITEM_PRICING.equals(tableName)
                || ConstantUtil.CUSTOMER_GTS_FORECAST.equals(tableName)
                || ConstantUtil.CUSTOMER_GTS_ACTUAL.equals(tableName)
                || ConstantUtil.VW_CUSTOMER_GTS_FORECAST.equals(tableName)
                || ConstantUtil.INVENTORYVIEW_TABLE.equals(tableName)
                || ConstantUtil.ST_ADJUSTMENT_GTN_DETAIL.equals(tableName)
                || ConstantUtil.ST_ADJUSTMENT_RESERVE_DETAIL.equals(tableName)
                || ConstantUtil.RETURN_RESERVE.equals(tableName);
    }
    
    public void updateReprocessedFlag(List<Integer> selectedRecords, String stagingTable, String processName, String viewNameSid, String invalidTableName) {
        String selectedRecordsList = StringUtils.EMPTY;
        for (int i = 0; i < selectedRecords.size(); i++) {
            if (i != 0) {
                selectedRecordsList = selectedRecordsList + "," + selectedRecords.get(i);
            } else {
                selectedRecordsList = selectedRecordsList + selectedRecords.get(i);
            }
        }
        String str;
        if(stagingTable.equals("ARP_OUTBOUND")){
            str = "SELECT DISTINCT ORIGINAL_BATCH_ID FROM "+ stagingTable +"";
        } else {
            str = "SELECT DISTINCT BATCH_ID FROM "+ stagingTable +"";
        }
        List batchIdList = HelperTableLocalServiceUtil.executeSelectQuery(str);
        String batchIds = StringUtils.EMPTY;
        if (batchIdList != null) {
            for (int i = 0; i < batchIdList.size(); i++) {
                if (i != 0) {
                    batchIds = batchIds + ",'" + batchIdList.get(i) + "'";
                } else {
                    batchIds = "'" + batchIdList.get(i).toString() + "'";
                }
            }
        }
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
        String query = "select TOP 1 STATUS,BATCH_ID from AUDIT_MASTER_INBOUND where APPLICATION_PROCESS = '" + processName + "' AND BATCH_ID IN (" + batchIds + ") AND MODIFIED_DATE > '" + sdf.format(new Date()) + "' ORDER BY MODIFIED_DATE DESC";
        List list = HelperTableLocalServiceUtil.executeSelectQuery(query);
        if (list.isEmpty()) {
            updateAllInInvalidTableOnCheckAll(selectedRecords, viewNameSid, invalidTableName, "1", true, StringUtils.EMPTY);
        } else {
            Object[] obj = (Object[]) list.get(0);
            if (String.valueOf(obj[0]).equalsIgnoreCase("E")) {
                updateAllInInvalidTableOnCheckAll(selectedRecords, viewNameSid, invalidTableName, "1", true, String.valueOf(obj[1]));
            }
        }
    }
}
