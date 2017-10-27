package com.stpl.app.global.company.util;

import com.stpl.app.model.HelperTable;
import com.stpl.app.model.impl.HelperTableImpl;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.HelperDTO;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Property;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.OptionGroup;
import java.util.HashMap;

// TODO: Auto-generated Javadoc
/**
 * It holds the commonly used methods and constants form company module.
 */
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
     * The Constant PERCENT.
     */
    public static final String PERCENT = "%";
    /**
     * The Constant SLASH_PERCENT.
     */
    public static final String SLASH_PERCENT = "\\%";
    /**
     * The Constant ESCAPE_SLASH.
     */
    public static final String ESCAPE_SLASH = "ESCAPE '\\'";
    
     static HashMap<String, String> columnName=new HashMap<String, String>();
     
     static HashMap<String, String> columnNameForTemp=new HashMap<String, String>();
     
     static HashMap<String, String> viewColumnNameForTemp=new HashMap<String, String>();
     
     static HashMap<String, String> columnNames=new HashMap<String, String>();    

    /**
     * To convert String date to date object.
     *
     * @param strDate - input date in string format
     * @return Date - The Date object of the strDate in the format of
     * "MM/dd/yyyy"
     * @throws ParseException the parse exception
     * @throws ParseException the parse exception
     */
    public static Date convertStringToDate(final String strDate) throws SystemException {
        LOGGER.debug("convertStringToDate p1:" + strDate);
        if (strDate == null || strDate.equals(EMPTY)
                || strDate.equals(STRING_NULL)) {
            return null;
        }
        final Date aDate = convertStringToDate(MMDDYYYY, strDate);
        LOGGER.debug("return aDate:" + aDate);
        return aDate;
    }

    /**
     * To convert the date to given format in aMask.
     *
     * @param aMask - Date format input
     * @param strDate - input date in string format
     * @return Date - The Date object of the strDate
     * @throws ParseException the parse exception
     * @throws ParseException the parse exception
     */
    public static final Date convertStringToDate(final String aMask,
            final String strDate) throws SystemException {
        LOGGER.debug("convertStringToDate p1:" + aMask + " p 2:" + strDate);
        try {
            final SimpleDateFormat dateFormat = new SimpleDateFormat(aMask);
            final Date date = dateFormat.parse(strDate);
            LOGGER.debug("return date:" + date);
            return date;
        } catch (ParseException e) {
            LOGGER.error(e);
            throw new SystemException(e);
        }
    }

    /**
     * Convert the date to "MM/dd/yyyy" format.
     *
     * @param aDate - The Date object to be converted to string
     * @return String - date in string format in the format of "MM/dd/yyyy"
     */
    public static final String convertDateToString(final Date aDate) {

        return getDateTime(MMDDYYYY, aDate);
    }

    /**
     * Get the date in string on given format.
     *
     * @param aMask - Date format input
     * @param aDate - The Date object to be converted to string
     * @return String - date in string format
     */
    public static final String getDateTime(final String aMask, final Date aDate) {
        if (aDate == null) {
        } else {
            final SimpleDateFormat dateFormat = new SimpleDateFormat(aMask);
            final String returnValue = dateFormat.format(aDate);
            return returnValue;
        }
        return StringUtils.EMPTY;
    }

    /**
     * To get the company native select.
     *
     * @param select the select
     * @param helperList the helper list
     * @return the native select
     */   
 public ComboBox getNativeSelect(final ComboBox select, final List<HelperDTO> helperList) {
        select.addItem(0);
        select.setItemCaption(0, ConstantsUtils.SELECT_ONE);
       for (int i = 0; i < helperList.size(); i++) {
            final HelperDTO helperDTO = helperList.get(i);
            select.addItem(helperDTO.getId());
            select.setItemCaption(helperDTO.getId(), helperDTO.getDescription());
            select.setDescription(select.getItemCaption(helperDTO.getId()));
        }
        return select;
    }
 
 public ComboBox getNativeSelectForFilter(final ComboBox select, final List<HelperDTO> helperList) {
        select.addItem(0);
        select.setItemCaption(0, ConstantsUtils.SHOW_ALL);
       for (int i = 0; i < helperList.size(); i++) {
            final HelperDTO helperDTO = helperList.get(i);
            select.addItem(helperDTO.getId());
            select.setItemCaption(helperDTO.getId(), helperDTO.getDescription());
        }
        return select;
    }
    
 public OptionGroup getNativeSelect(final OptionGroup select, final List<HelperDTO> helperList) {
       for (int i = 0; i < helperList.size(); i++) {
            final HelperDTO helperDTO = helperList.get(i);
            select.addItem(helperDTO.getId());
            select.setItemCaption(helperDTO.getId(), helperDTO.getDescription());
        }
        return select;
    } 

    /**
     * To get the identifier native select.
     *
     * @param select the select
     * @param helperList the helper list
     * @return the native select for identifier
     */
    public ComboBox getNativeSelectForIdentifier(final ComboBox select,
            final List<HelperDTO> helperList) {
        select.setPageLength(NumericConstants.SEVEN);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        select.addItem(ConstantsUtils.SELECT_ONE);
        for (int i = 0; i < helperList.size(); i++) {
            final HelperDTO helperDTO = helperList.get(i);
            select.addItem(helperDTO.getDescription());
        }
        select.addItem("Edit List");
        select.select(ConstantsUtils.SELECT_ONE);
        select.addValueChangeListener(new Property.ValueChangeListener() {
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty() != null && event.getProperty().getValue() != null && StringUtils.EMPTY.equals(event.getProperty().getValue())) {
                    select.select(ConstantsUtils.SELECT_ONE);
                }
            }
        });
        return select;
    }

    /**
     * To get the status native select.
     *
     * @param select the select
     * @return the status select
     */
    public ComboBox getStatusSelect(final ComboBox select) {
        select.setPageLength(NumericConstants.SEVEN);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        select.addItem(ConstantsUtils.SELECT_ONE);
        select.addItem("Active");
        select.addItem("Inactive");
        select.select(ConstantsUtils.SELECT_ONE);
        select.addValueChangeListener(new Property.ValueChangeListener() {
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty() != null && event.getProperty().getValue() != null && StringUtils.EMPTY.equals(event.getProperty().getValue())) {
                    select.select(ConstantsUtils.SELECT_ONE);
                }
            }
        });
        return select;
    }

    /**
     * To get the company native select.
     *
     * @param select the select
     * @param helperList the helper list
     * @return the native select
     */
    public ComboBox getComboBox(final ComboBox select,
            final List<HelperDTO> helperList) {

        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        for (int i = 0; i < helperList.size(); i++) {
            final HelperDTO helperDTO = helperList.get(i);
            if (i == 0) {
                select.addItem(ConstantsUtils.SELECT_ONE);
            }
            select.addItem(helperDTO.getDescription());
        }
        select.select(ConstantsUtils.SELECT_ONE);
        select.addValueChangeListener(new Property.ValueChangeListener() {
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty() != null && event.getProperty().getValue() != null && StringUtils.EMPTY.equals(event.getProperty().getValue())) {
                    select.select(ConstantsUtils.SELECT_ONE);
                }
            }
        });
        return select;
    }

    /**
     * To get the company native select.
     *
     * @param select the select
     * @param helperList the helper list
     * @return the native select
     */
    public NativeSelect getNativeSelect(final NativeSelect select,
            final List<HelperDTO> helperList) {
        for (int i = 0; i < helperList.size(); i++) {
            final HelperDTO helperDTO = helperList.get(i);
            select.addItem(helperDTO.getDescription());
        }
        return select;
    }

    /**
     * Get the date in string on given format.
     *
     * @param aMask - Date format input
     * @param aDate - The Date object to be converted to string
     * @return String - date in string format
     */
    public static Date getDateTime() {
        try {
            final SimpleDateFormat fmt = new SimpleDateFormat("MM/dd/yyyy");
            Date date = fmt.parse(fmt.format(new Date()));
            return date;
        } catch (ParseException ex) {
            LOGGER.error(ex);
        }
        return null;
    }
        public static String formateDateString(final String aMask,
            final Date strDate) {
            if(strDate==null){
                return StringUtils.EMPTY;
            }else{
            final SimpleDateFormat dateFormat = new SimpleDateFormat(aMask);
            return dateFormat.format(strDate);
            }
       
    }

        public static String loadDescription(int code) throws PortalException, SystemException {
        String descValue;
           HelperTable description=HelperTableLocalServiceUtil.getHelperTable(code);
            descValue=description.getDescription();
        
        return descValue;
    }
        
    public static String getDBColumnName(String visibleColumnName) {
         return columnName.get(visibleColumnName);  
       
    } 
    
   public static HashMap<String, String> loadColumnName() {
       
       columnName.put("companyFamilyPlanId", "cfpId");
       columnName.put("companyFamilyPlanNo", "cfpNo");
       columnName.put("companyFamilyPlanName", "cfpName");
       columnName.put(ConstantsUtils.CFP_STATUS, "cfpStatus");
       columnName.put("companyFamilyPlanType", "cfpType");
       columnName.put(ConstantsUtils.CFP_START_DATE, "cfpStartDate");
       columnName.put(ConstantsUtils.CFP_END_DATE, "cfpEndDate");
       columnName.put("companyFamilyPlanTradeClass", "cfpTradeClass");
       columnName.put("companyFamilyPlanCategory", "cfpCategory");
       columnName.put("companyFamilyPlanDesignation", "cfpDesignation");
       columnName.put(ConstantsUtils.PARENT_CFP_ID, "parentCfpId");
       columnName.put(ConstantsUtils.PARENT_CFP_NAME, "parentCfpName");
       
       return columnName;
   }     
       
    public static String getDBTempColumnName(String visibleColumnName) {
         return columnNameForTemp.get(visibleColumnName);  
     } 
        
   public static HashMap<String, String> loadTempColumnName() {
       columnNameForTemp.put(ConstantsUtils.CFP_STATUS, ConstantsUtils.C_STATUS);
       columnNameForTemp.put(ConstantsUtils.CFP_START_DATE, "CFP_DETAILS_START_DATE");
       columnNameForTemp.put(ConstantsUtils.CFP_END_DATE, "CFP_DETAILS_END_DATE");
       columnNameForTemp.put("companyId", "COMPANY_ID");
       columnNameForTemp.put("checkbox", "CHECK_RECORD");
       columnNameForTemp.put("companyNo", "COMPANY_NO");
       columnNameForTemp.put("companyName", "COMPANY_NAME");
       columnNameForTemp.put("companyStartDate", "COMPANY_START_DATE");
       columnNameForTemp.put("companyEndDate", "COMPANY_END_DATE");
       columnNameForTemp.put("companyStatusValue", "ccstatus");
       columnNameForTemp.put("companyTypeValue", ConstantsUtils.C_TYPE);
       columnNameForTemp.put("tradingPartnerContractNo", "TRADING_PARTNER_CONTRACT_NO");
       columnNameForTemp.put("attachedDate","CFP_DETAILS_ATTACHED_DATE");
       columnNameForTemp.put("createdBy","CFP_DETAILS_CREATED_BY");
       columnNameForTemp.put("modifiedBy","CFP_DETAILS_MODIFIED_BY");
       return columnNameForTemp;
   }
   
   public static String getDbColumnNames(String visibleColumnName) {
         return columnNames.get(visibleColumnName);  
     }
   
   public static HashMap<String, String> loadColumnNames() {
       columnNames.put("companyFamilyPlanSystemId", "CFP_MODEL_SID");
       columnNames.put("companyFamilyPlanId", "CFP_ID");
       columnNames.put("companyFamilyPlanNo", "CFP_NO");
       columnNames.put("companyFamilyPlanName", "CFP_NAME");
       columnNames.put(ConstantsUtils.CFP_STATUS, ConstantsUtils.C_STATUS);
       columnNames.put("companyFamilyPlanType", ConstantsUtils.C_TYPE);
       columnNames.put(ConstantsUtils.CFP_START_DATE, "CFP_START_DATE");
       columnNames.put(ConstantsUtils.CFP_END_DATE, "CFP_END_DATE");
       columnNames.put("companyFamilyPlanTradeClass", "trade");
       columnNames.put("companyFamilyPlanCategory", "category");
       columnNames.put("companyFamilyPlanDesignation", "CFP_DESIGNATION");
       columnNames.put(ConstantsUtils.PARENT_CFP_ID, "PARENT_ID");
       columnNames.put(ConstantsUtils.PARENT_CFP_NAME, "PARENT_NO");
        columnNames.put("modifiedBy", "MODIFIED_BY");
         columnNames.put("modifiedDate", "MODIFIED_DATE");
          columnNames.put("createdBy", "CREATED_BY");
           columnNames.put("createdDate", "CREATED_DATE");
       return columnNames;
   }
   
    public static String getDBTempViewColumnName(String visibleColumnName) {
        return viewColumnNameForTemp.get(visibleColumnName);  
     } 
    
    public static HashMap<String, String> loadTempViewColumnName() {
         viewColumnNameForTemp.put("companyFamilyPlanStatusValue","CFPstatus");
         viewColumnNameForTemp.put(ConstantsUtils.CFP_START_DATE,"COMPANY_START_DATE");
         viewColumnNameForTemp.put(ConstantsUtils.CFP_END_DATE,"COMPANY_END_DATE");
         viewColumnNameForTemp.put("companyId","COMPANY_ID");
         viewColumnNameForTemp.put("companyNo","COMPANY_NO");
         viewColumnNameForTemp.put("companyName","COMPANY_NAME");
         viewColumnNameForTemp.put("companyStartDate","C_StartDate");
         viewColumnNameForTemp.put("companyEndDate","C_EndDate");
         viewColumnNameForTemp.put("companyStatusValue",ConstantsUtils.C_STATUS);
         viewColumnNameForTemp.put("companyTypeValue",ConstantsUtils.C_TYPE);
         viewColumnNameForTemp.put("tradingPartnerContractNo","TRADING_PARTNER_CONTRACT_NO");
         viewColumnNameForTemp.put("attachedDate","COMPANY_CFP_ATTACHED_DATE");
         return viewColumnNameForTemp;
    }    
   
    public static String getDescription(int code){
        HelperTable helper = new HelperTableImpl();
        try {
            helper = HelperTableLocalServiceUtil.getHelperTable(code);
        } catch (PortalException ex) {
            LOGGER.error(ex);
            return StringUtils.EMPTY;
        } catch (SystemException ex) {
            LOGGER.error(ex);
            return StringUtils.EMPTY;
        }
        return helper.getDescription();
    } 
    
}
