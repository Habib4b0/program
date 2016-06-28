package com.stpl.app.contract.global.util;

import com.stpl.app.contract.dao.impl.CFPSearchLogicDAOImpl;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

import com.stpl.app.contract.global.logic.ItemSearchLogic;
import com.stpl.app.contract.util.Constants;
import com.stpl.app.model.HelperTable;
import com.stpl.domain.contract.contractdashboard.CompanyFamilyplanDAO;
import com.stpl.ifs.ui.util.UIUtil;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.NativeSelect;
import java.util.HashMap;
import java.util.Map;


public class CommonUtils {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(CommonUtils.class);
    /**
     * The Constant EMPTY.
     */
    public static final String EMPTY = "";
    /**
     * The Constant STRING_ASTERISK.
     */
    public static final String STRING_ASTERISK = "*";
    /**
     * The Constant MMDDYYYY.
     */
    public static final String MMDDYYYY = "MM/dd/yyyy";
    /**
     * The Constant MMDDYYYYHHMMMSS.
     */
    public static final String MMDDYYYYHHMMMSS = "yyyy-MM-dd HH:mm:ss";
    /**
     * The Constant STRING_NULL.
     */
    public static final String STRING_NULL = "null";
    /**
     * The Constant STRING_ZERO.
     */
    public static final String STRING_ZERO = "0";
    /**
     * The Constant CHAR_PERCENT.
     */
    public static final char CHAR_PERCENT = '%';
    /**
     * The Constant CHAR_ASTERISK.
     */
    public static final char CHAR_ASTERISK = '*';
    /**
     * The Constant SALES_MASTER.
     */
    public static final String SALES_MASTER = "SALES_MASTER";
    /**
     * The Constant REBATE_PLAN_TYPE.
     */
    public static final String REBATE_PLAN_TYPE = "RP_TYPE";
    /**
     * The Constant REBATE_BASED_ON.
     */
    public static final String REBATE_BASED_ON = "REBATE_BASED_ON";
    /**
     * The Constant REBATE_STRUCTURE.
     */
    public static final String REBATE_STRUCTURE = "RP_STRUCTURE";
    /**
     * The Constant RS_TYPE.
     */
    public static final String RS_TYPE = "RS_TYPE";
    /**
     * The Constant STATUS.
     */
    public static final String STATUS = "STATUS";
    /**
     * The Constant RS_PROGRAM_TYPE.
     */
    public static final String REBATE_PROGRAM_TYPE = "REBATE_PROGRAM_TYPE";
    /**
     * The Constant PAYMENT_METHOD.
     */
    public static final String PAYMENT_METHOD = "PAYMENT_METHOD";
    /**
     * The Constant REBATE_FREQUENCY.
     */
    public static final String REBATE_FREQUENCY = "REBATE_FREQUENCY";
    /**
     * The Constant PAYMENT_FREQUENCY.
     */
    public static final String PAYMENT_FREQUENCY = "PAYMENT_FREQUENCY";
    /**
     * The Constant PAYMENT_TERMS.
     */
    public static final String PAYMENT_TERMS = "PAYMENT_TERMS";
    /**
     * The Constant CALENDAR.
     */
    public static final String CALENDAR = "RS_CALENDAR";
    /**
     * The Constant RS_VALIDN_PROFILE.
     */
    public static final String RS_VALIDN_PROFILE = "RS_VALIDATION_PROFILE";
    /**
     * The Constant REBATE_PLAN_LEVEL.
     */
    public static final String REBATE_PLAN_LEVEL = "REBATE_PLAN_LEVEL";
    /**
     * The Constant RS_RULE_ASSCTN.
     */
    public static final String RS_RULE_ASSCTN = "RS_RBT_RULE_ASSOCN";
     /**
     * The Constant RS_RULE_ASSCTN.
     */
    public static final String REBATE_RULE_TYPE="REBATE_RULE_TYPE";
    /**
     * The Constant IFP_TYPE.
     */
    public static final String IFP_TYPE = "IFP_TYPE";
    /**
     * The Constant UDC1.
     */
    public static final String UDC1 = "RS_UDC1";
    /**
     * The Constant UDC2.
     */
    public static final String UDC2 = "RS_UDC2";
    /**
     * The Constant UDC3.
     */
    public static final String UDC3 = "RS_UDC3";
    /**
     * The Constant UDC4.
     */
    public static final String UDC4 = "RS_UDC4";
    /**
     * The Constant UDC5.
     */
    public static final String UDC5 = "RS_UDC5";
    /**
     * The Constant UDC6.
     */
    public static final String UDC6 = "RS_UDC6";
    /**
     * The Constant FORECAST_SALE_BASIS.
     */
    public static final String FORECAST_SALE_BASIS = "FORECAST_SALES_BASIS";
    /**
     * The Constant RS_CATEGORY.
     */
    public static final String RS_CATEGORY = "RS_CATEGORY";
    /**
     * The Constant TRADE_CLASS.
     */
    public static final String TRADE_CLASS = "RS_TRADE_CLASS";
    /**
     * The Constant RS_DESIGNATION.
     */
    public static final String RS_DESIGNATION = "RS_DESIGNATION";
    /**
     * The Constant CONTRACT_ID.
     */
    public static final String CONTRACT_ID = "contractId";
    /**
     * The Constant CONTRACT_NO.
     */
    public static final String CONTRACT_NO = "contractNo";
     /**
     * The Constant CONTRACT_NO.
     */
    public static final String HELPER_TABLE_SID = "helperTableSid";
    /**
     * The Constant CONTRACT_NAME.
     */
    public static final String CONTRACT_NAME = "contractName";
    /**
     * The Constant CONTRACT_TYPE.
     */
    public static final String CONTRACT_TYPE = "contractType";
    /**
     * The Constant CONTRACT_STATUS.
     */
    public static final String CONTRACT_STATUS = "contractStatus";
    /**
     * The Constant TRADED_CLASS.
     */
    public static final String TRADED_CLASS = "tradeClass";
    public static final String USER_ID="userId";
    public static final String TEMP_ID= "TempId";
    public static final String SESSION_ID="sessionId";
    /**
     * The item logic.
     */
    private final ItemSearchLogic itemLogic = new ItemSearchLogic();
    /**
     * The Constant RS_COL_HEADERS.
     */
    public static final String[] RS_COL_HEADERS = new String[]{"Rebate Schedule ID", "Rebate Schedule No", "Rebate Schedule Name", "Rebate Schedule Status",
        "Rebate Schedule Type", "Rebate Program Type"};
    /**
     * The Constant RS_COL_COLUMNS.
     */
    public static final Object[] RS_COL_COLUMNS = new Object[]{"rebateScheduleId", "rebateScheduleNo", "rebateScheduleName", "displayRsStatus", "displayRsType",
        "displayRpType"};
    /**
     * The Constant CALCULATION_TYPE.
     */
    public static final String CALCULATION_TYPE = "CALCULATION_TYPE";
    /**
     * The Constant CALCULATION_LEVEL.
     */
    public static final String CALCULATION_LEVEL = "CALCULATION_LEVEL";
    /**
     * The Constant CALCULATION_LEVEL.
     */
    public static final String EVALUATION_RULE_TYPE = "EVALUATION_RULE_TYPE";
    static HashMap<String, String> columnName=new HashMap<String, String>();
     
     static HashMap<String, String> columnNameForTemp=new HashMap<String, String>();
     
     static HashMap<String, String> viewColumnNameForTemp=new HashMap<String, String>();
     
     static HashMap<String, String> columnNames=new HashMap<String, String>();

    /**
     * Populates the NativeSelect from HelperDTO and returns it.
     *
     * @param select - NativeSelect
     * @return NativeSelect
     * @throws com.stpl.portal.kernel.exception.SystemException
     */
    public NativeSelect getItemStatus(final NativeSelect select) throws SystemException {
        final List<HelperDTO> stsLst = itemLogic.getItemType(UIUtil.ITEM_STATUS);
        for (int i = 0; i < stsLst.size(); i++) {
            final HelperDTO helper = (HelperDTO) stsLst.get(i);
            select.addItem(helper.getDescription());
        }
        return select;
    }

    /**
     * Populates the NativeSelect with given list and returns it.
     *
     * @param select - NativeSelect
     * @param helperList - List of String.
     * @return NativeSelect
     */
    public NativeSelect getNativeSelect(final NativeSelect select, final List<HelperDTO> helperList) {
        for (int i = 0; i < helperList.size(); i++) {
            final HelperDTO helperDTO = helperList.get(i);
            select.addItem(helperDTO.getId());
            select.setItemCaption(helperDTO.getId(),helperDTO.getDescription());
        }
        return select;
    }

    /**
     * Converts the Date object to String and returns it.
     *
     * @param aDate - Date
     * @return String
     */
    public static final String convertDateToString(final Date aDate) {
        return getDateTime(MMDDYYYY, aDate);
    }

    /**
     * Converts the Date object to String with the given format.
     *
     * @param aMask - format
     * @param aDate - Date
     * @return String
     */
    public static final String getDateTime(final String aMask, final Date aDate) {
        SimpleDateFormat sdf;
        String returnValue;
        if (aDate == null) {
            returnValue = StringUtils.EMPTY;
        } else {
            sdf = new SimpleDateFormat(aMask);
            returnValue = sdf.format(aDate);
        }
        return returnValue;
    }

    /**
     * Converts the String date to Date object.
     *
     * @param strDate - String Date
     * @return Date
     * @throws ParseException the parse exception
     * @throws ParseException the parse exception
     */
    public static Date convertStringToDate(final String strDate) throws SystemException {

        if (strDate == null || strDate.equals(EMPTY) || strDate.equals(STRING_NULL)) {
            return null;
        }
        final Date aDate = convertStringToDate(MMDDYYYY, strDate);
        return aDate;
    }

    /**
     * Converts the String date to Date object with the given format.
     *
     * @param aMask - format
     * @param strDate - String Date
     * @return Date
     * @throws ParseException the parse exception
     * @throws ParseException the parse exception
     */
    public static final Date convertStringToDate(final String aMask, final String strDate) throws SystemException {
        try {
            SimpleDateFormat sdf;

            sdf = new SimpleDateFormat(aMask);
            final Date date = sdf.parse(strDate);
            return date;
        } catch (ParseException ex) {
            LOGGER.error(ex);
            throw new SystemException(ex);
        }
    }   

    /**
     * Gets the item logic.
     *
     * @return the item logic
     */
    public ItemSearchLogic getItemLogic() {
        return itemLogic;
    }
    public  ComboBox getSelectNull(final ComboBox select) throws SystemException {
        select.setNullSelectionAllowed(true);
        select.markAsDirty();
        select.setPageLength(7);
        select.setInputPrompt(Constants.SELECT_ONE);
        select.setNullSelectionItemId(Constants.SELECT_ONE);
        select.addItem(Constants.SELECT_ONE);
        return select;
    }
    
    public static String getString(final Object str) {
         String stringOut ="";
    try{
      stringOut = StringUtils.trimToEmpty(String.valueOf(str));
        if (Constants.NULL.equals(stringOut)) {
            
            stringOut = StringUtils.EMPTY;
        }
  } catch (Exception e) {
            LOGGER.error(e);
        }
        return stringOut;
    }
    
    public static HashMap<String, String> loadColumnName() {
        columnName.put("companyFamilyPlanId", "cfpId");
        columnName.put("companyFamilyPlanNo", "cfpNo");
        columnName.put("companyFamilyPlanName", "cfpName");
        columnName.put("companyFamilyPlanStatus", "cfpStatus");
        columnName.put("companyFamilyPlanType", "cfpType");
        columnName.put("companyFamilyPlanStartDate", "cfpStartDate");
        columnName.put("companyFamilyPlanEndDate", "cfpEndDate");
        columnName.put("companyFamilyPlanTradeClass", "cfpTradeClass");
        columnName.put("companyFamilyPlanCategory", "cfpCategory");
        columnName.put("companyFamilyPlanDesignation", "cfpDesignation");
        columnName.put("parentCompanyFamilyPlanId", "parentCfpId");
        columnName.put("parentCompanyFamilyPlanName", "parentCfpName");
        return columnName;
    }

    public static HashMap<String, String> loadColumnNames() {
        columnNames.put("companyFamilyPlanSystemId", "CFP_MODEL_SID");
        columnNames.put("companyFamilyPlanId", "CFP_ID");
        columnNames.put("companyFamilyPlanNo", "CFP_NO");
        columnNames.put("companyFamilyPlanName", "CFP_NAME");
        columnNames.put("companyFamilyPlanStatus", "cstatus");
        columnNames.put("companyFamilyPlanType", "ctype");
        columnNames.put("companyFamilyPlanStartDate", "CFP_START_DATE");
        columnNames.put("companyFamilyPlanEndDate", "CFP_END_DATE");
        columnNames.put("companyFamilyPlanTradeClass", "trade");
        columnNames.put("companyFamilyPlanCategory", "category");
        columnNames.put("companyFamilyPlanDesignation", "CFP_DESIGNATION");
        columnNames.put("parentCompanyFamilyPlanId", "PARENT_ID");
        columnNames.put("parentCompanyFamilyPlanName", "PARENT_CFP_NAME");
        columnNames.put("modifiedBy", "MODIFIED_BY");
        columnNames.put("modifiedDate", "MODIFIED_DATE");
        columnNames.put("createdBy", "CREATED_BY");
        columnNames.put("createdDate", "CREATED_DATE");
        return columnNames;
    }
    
    public static String getDbColumnNames(String visibleColumnName) {
        return columnNames.get(visibleColumnName);
    }
    
    public static Map<Integer, String> getCodeDescription() throws PortalException, SystemException{
        CompanyFamilyplanDAO dao = new CFPSearchLogicDAOImpl();
        Map<Integer, String> helperTableMap = new HashMap<Integer, String>();
        final List<HelperTable> list = dao.getHelperTableDetailsByListName();
        for(HelperTable helperTable: list){
            helperTableMap.put(helperTable.getHelperTableSid(), helperTable.getDescription());
        }
        return helperTableMap;
    }
}
