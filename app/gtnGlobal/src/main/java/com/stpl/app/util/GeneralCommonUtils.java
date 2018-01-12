package com.stpl.app.util;

import com.stpl.app.global.dao.CommonDao;
import com.stpl.app.global.dao.impl.CommonDaoImpl;
import com.stpl.app.model.HelperTable;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.OrderFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.search.ParseException;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.NativeSelect;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;


/**
 * Common utility class that holds common logic methods and constants.
 *
 * @author
 */
public class GeneralCommonUtils {

	/** The Constant EMPTY. */
	public static final String EMPTY = "";
	
	/** The Constant STRING_ASTERISK. */
	public static final String STRING_ASTERISK = "*";
	
	/** The Constant MMDDYYYY. */
	public static final String MMDDYYYY = "MM/dd/yyyy";
	
	/** The Constant MMDDYYYYHHMMMSS. */
	public static final String MMDDYYYYHHMMMSS = "yyyy-MM-dd HH:mm:ss";
	
	/** The Constant STRING_NULL. */
	public static final String STRING_NULL = ConstantsUtils.NULL;
	
	/** The Constant CHAR_PERCENT. */
	public static final char CHAR_PERCENT = '%';
	
	/** The Constant CHAR_ASTERISK. */
	public static final char CHAR_ASTERISK = '*';

	/** The Constant SALES_MASTER. */
	public static final String SALES_MASTER = "SALES_MASTER";
	
	/** The Constant REBATE_PLAN_TYPE. */
	public static final String REBATE_PLAN_TYPE = "REBATE_PLAN_TYPE";
        
        /** The Constant REBATE_PLAN_TYPE. */
	public static final String REBATE_PLAN_FORMULA_TYPE = "FORMULA_TYPE";
        
        
        /** The Constant STATUS. */
	public static final String STATUS = "STATUS";
	
	/** The Constant REBATE_PLAN_STATUS. */
	public static final String REBATE_PLAN_STATUS = "RP_STATUS";
	
	/** The Constant REBATE_BASED_ON. */
	public static final String REBATE_BASED_ON = "REBATE_BASED_ON";
	
	/** The Constant REBATE_STRUCTURE. */
	public static final String REBATE_STRUCTURE = "REBATE_STRUCTURE";
	
	/** The Constant REBATE_RANGE_BASED_ON. */
	public static final String REBATE_RANGE_BASED_ON = "REBATE_RANGE_BASED_ON";
	
	/** The Constant REBATE_SCHEDULE_TYPE. */
	public static final String REBATE_SCHEDULE_TYPE = "RS_TYPE";
	
	/** The Constant REBATE_PROGRAM_TYPE. */
	public static final String REBATE_PROGRAM_TYPE = "REBATE_PROGRAM_TYPE";
	
	/** The Constant PAYMENT_METHOD. */
	public static final String PAYMENT_METHOD = "PAYMENT_METHOD";
        
        public static final String EVALUATION_RULE_LEVEL = "EVALUATION_RULE_LEVEL";
        
        public static final String EVALUATION_RULE_TYPE = "EVALUATION_RULE_TYPE";
        
        public static final String CALCULATION_RULE_LEVEL = "CALCULATION_RULE_LEVEL";
	
	/** The Constant REBATE_FREQUENCY. */
	public static final String REBATE_FREQUENCY = "REBATE_FREQUENCY";
	
	/** The Constant PAYMENT_FREQUENCY. */
	public static final String PAYMENT_FREQUENCY = "PAYMENT_FREQUENCY";
	
	/** The Constant PAYMENT_TERMS. */
	public static final String PAYMENT_TERMS = "PAYMENT_TERMS";
        
	/** The Constant RULE. */
	public static final String RULE = "Rule";        
	
	/** The Constant CALENDAR. */
	public static final String CALENDAR = "RS_CALENDAR";
    
    /** The Constant TIER_OPERATOR. */
    public static final String TIER_OPERATOR = "TIER_OPERATOR";
    
    /** The Constant RS_STATUS. */
    public static final String RS_STATUS = "RS_STATUS";

    /** The Constant REBATE_VALIDATION_PROFILE. */
    public static final String REBATE_VALIDATION_PROFILE = "RS_VALIDATION_PROFILE";
    
    /** The Constant REBATE_PLAN_LEVEL. */
    public static final String REBATE_PLAN_LEVEL = "RS_RP_LEVEL";
    
    /** The Constant REBATE_RULE_ASSOCIATION. */
    public static final String REBATE_RULE_ASSOCIATION = "RS_RBT_RULE_ASSOCN";
    
    /** The Constant REBATE_RULE_TYPE. */
    public static final String REBATE_RULE_TYPE = "REBATE_RULE_TYPE";
    
    /** The Constant IFP_TYPE. */
    public static final String IFP_TYPE = "IFP_TYPE";

    /** The Constant REBATE_SCHEDULE_DESIGNATION. */
    public static final String REBATE_SCHEDULE_DESIGNATION = "RS_DESIGNATION";
    
    /** The Constant REBATE_SCHEDULE_CATEGORY. */
    public static final String REBATE_SCHEDULE_CATEGORY = "RS_CATEGORY";
    
    /** The Constant TRADE_CLASS. */
    public static final String TRADE_CLASS = "RS_TRADE_CLASS";
    
    /** The Constant UDC1. */
    public static final String UDC1 = "RS_UDC1";
    
    /** The Constant UDC2. */
    public static final String UDC2 = "RS_UDC2";
    
    /** The Constant UDC3. */
    public static final String UDC3 = "RS_UDC3";
    
    /** The Constant UDC4. */
    public static final String UDC4 = "RS_UDC4";
    
    /** The Constant UDC5. */
    public static final String UDC5 = "RS_UDC5";
    
    /** The Constant UDC6. */
    public static final String UDC6 = "RS_UDC6";
      
    /** The Constant CALCULATION_TYPE. */
    public static final String CALCULATION_TYPE = "CALCULATION_TYPE";
    
    /** The Constant CALCULATION_LEVEL. */
    public static final String CALCULATION_LEVEL = "CALCULATION_LEVEL";
    
    /** The Constant CALCULATION_TYPE. */
    public static final String INTEREST_BEARING_INDICATOR = "INTEREST_BEARING_INDICATOR";
    
    /** The Constant CALCULATION_LEVEL. */
    public static final String INTEREST_BEARING_BASIS = "INTEREST_BEARING_BASIS";
        
    /** The Constant FORECAST_SALE_BASIS. */
    public static final String FORECAST_SALE_BASIS = "RS_FRCST_SALES_BASIS";
    
    /** The Constant LOGGER. */
    private static final Logger LOGGER = Logger.getLogger(GeneralCommonUtils.class);
    
    /** The Constant ZERO. */
    public static final int ZERO = 0;
    
    /** The Constant ONE. */
    public static final int ONE = 1;
    /**
     * Null representation of HelperDTO (Select one item)
     */
    public static final HelperDTO NULL_HELPER_DTO = new HelperDTO(ConstantsUtils.SELECT_ONE);
    
    public static final CommonDao DAO = CommonDaoImpl.getInstance();

    /**
     * Add items to the NativeSelect from list of HelperDTO.
     *
     * @param select - NativeSelect
     * @param helperList - List of HelperDTO.
     * @return NativeSelect with added items.
     */
    public NativeSelect getNativeSelect(final NativeSelect select,
            final List<HelperDTO> helperList) {
        try{
            
        for (final HelperDTO helperDTO : helperList) {
            select.addItem(helperDTO.getId());
            select.setItemCaption(helperDTO.getId(),helperDTO.getDescription());
            
        }
        } catch (UnsupportedOperationException e) {
            LOGGER.error(e);
        }
        return select;
    }
    
    public ComboBox getComboBoxV1(final ComboBox select,
            final List<HelperDTO> helperList) {
        try{
            
        for (final HelperDTO helperDTO : helperList) {
            select.addItem(helperDTO.getId());
            select.setItemCaption(helperDTO.getId(),helperDTO.getDescription());
            
        }
        } catch (UnsupportedOperationException e) {
            LOGGER.error(e);
        }
        return select;
    }    

    /**
     * Add the items to NativeSelect and returns it.
     *
     * @param select - NativeSelect.
     * @return NativeSelect with added items.
     */
    public NativeSelect getStatusSelect(final NativeSelect select) {
        try{
        select.addItem("Active");
        select.addItem("Inactive");
         } catch (UnsupportedOperationException e) {
            LOGGER.error(e);
        }
        return select;
    }

    /**
     * Adds the list of string to the Native select and returns it.
     *
     * @param select - NativeSelect
     * @param list -List of String.
     * @return - NativeSelect with the added items.
     */
    public NativeSelect getNativeList(final NativeSelect select, final List<String> list) {
         for (final String str : list) {
            select.addItem(str);
        }
        return select;
    }

    /**
     * Converts the String object to Date object and returns the Date object.
     *
     * @param strDate - String date.
     * @return - date object
     * @throws ParseException the parse exception
     * @throws ParseException the parse exception
     */
    public static Date convertStringToDate(final String strDate){
        if (strDate == null || strDate.equals(EMPTY)
                || strDate.equals(STRING_NULL)) {
            return null;
        }
        Date aDate = new Date();
		try {
			aDate = convertStringToDate(MMDDYYYY, strDate);
		} catch (Exception e) {
			LOGGER.error(e);
		}
        return aDate;
    }

    /**
     * Converts the String date to date object with the given format.
     *
     * @param aMask - format for date.
     * @param strDate - String date that format need to be changed.
     * @return - date object with the particular format.
     * @throws ParseException the parse exception
     * @throws ParseException the parse exception
     */
    public static final Date convertStringToDate(final String aMask, final String strDate){
       Date date = null;
        try{
        SimpleDateFormat dateFormat = null;
        
        dateFormat = new SimpleDateFormat(aMask);
        date = dateFormat.parse(strDate);
         } catch (java.text.ParseException e) {
            LOGGER.error(e);
        }
        return date;
    }

    /**
     * Formats the two digit year format to four digit year and returns the Date
     * object.
     *
     * @param enteredDate - Date Object that format need to be changed.
     * @return Date Object with formatted date object.
     */
    public static final Date convert2DigitTo4DigitYearFormat(final Date enteredDate) {
    	Date enterDate = enteredDate == null ? null : (Date) enteredDate.clone();

        if (enterDate != null && !enterDate.equals("")) {
            try {
                final SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yy");
                final SimpleDateFormat fmt = new SimpleDateFormat("MM/dd/yyyy");
                final Calendar cal = Calendar.getInstance();
                cal.add(Calendar.YEAR, -NumericConstants.FOURTEEN);
                sdf.set2DigitYearStart(cal.getTime());
                final String datesVal = sdf.format(enterDate);
                final Date temp = GeneralCommonUtils.convertStringToDate(fmt.format(sdf.parse(datesVal)));
                enterDate = temp;
            } catch (java.text.ParseException e) {
                LOGGER.error(e);
            }
        }
        return enterDate;
    }

    /**
     * To get the combo box select.
     *
     * @param select the select
     * @param helperList the helper list
     * @return the native select
     */
    public ComboBox getComboBox(final ComboBox select,
            final List<HelperDTO> helperList) {
        select.setPageLength(NumericConstants.SEVEN);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        select.addItem(0);
        select.setItemCaption(0,ConstantsUtils.SELECT_ONE);
        for (int i = 0; i < helperList.size(); i++) {
            final HelperDTO helperDTO = helperList.get(i);
            select.addItem(helperDTO.getSystemId());
            select.setItemCaption(helperDTO.getSystemId(),helperDTO.getDescription());
        }
        select.select(ConstantsUtils.SELECT_ONE);
        select.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty() != null && event.getProperty().getValue() != null && (StringUtils.EMPTY.equals(event.getProperty().getValue())||ConstantsUtils.NULL.equals(event.getProperty().getValue()))) {
                    select.select(ConstantsUtils.SELECT_ONE);
                }
            }
        });
        return select;
    }
    
    public ComboBox getNativeSelectForFilter(final ComboBox select, final List<HelperDTO> helperList) {
        select.setPageLength(NumericConstants.SEVEN);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(0);
        select.addItem(0);
        select.setItemCaption(0,"Show All");
        for (int i = 0; i < helperList.size(); i++) {
            final HelperDTO helperDTO = helperList.get(i);
            select.addItem(helperDTO.getSystemId());
            select.setItemCaption(helperDTO.getSystemId(),helperDTO.getDescription());
        }
        select.select(0);
        select.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty() != null && event.getProperty().getValue() != null && (StringUtils.EMPTY.equals(event.getProperty().getValue())||ConstantsUtils.NULL.equals(event.getProperty().getValue()))) {
                    select.select(0);
                }
            }
        });
        return select;
            
    }
    
    /**
     * To get the combo box select.
     *
     * @param select the select
     * @param helperList the helper list
     * @return the native select
     */
    public ComboBox getBrandList(final ComboBox select,
            final List<HelperDTO> helperList) {
        select.setPageLength(NumericConstants.SEVEN);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(0);
        select.addItem(0);
        select.setItemCaption(0,ConstantsUtils.SHOW_ALL);
        for (int i = 0; i < helperList.size(); i++) {
            final HelperDTO helperDTO = helperList.get(i);
            select.addItem(helperDTO.getSystemId());
            select.setItemCaption(helperDTO.getSystemId(),helperDTO.getDescription());
        }
        select.select(0);
        select.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty() != null && event.getProperty().getValue() != null && (StringUtils.EMPTY.equals(event.getProperty().getValue())||ConstantsUtils.NULL.equals(event.getProperty().getValue()))) {
                    select.select(0);
                }
            }
        });
        return select;
    }

    /**
     * Adds the list of string to the Native select and returns it.
     *
     * @param select - NativeSelect
     * @param list -List of String.
     * @return - NativeSelect with the added items.
     */
    public ComboBox getNativeList(final ComboBox select, final List<HelperDTO> list) {
        select.setPageLength(NumericConstants.SEVEN);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        select.addItem(0);
        select.setItemCaption(0,ConstantsUtils.SELECT_ONE);
        for (final HelperDTO dto : list) {
            select.addItem(dto.getSystemId());
            select.setItemCaption(dto.getSystemId(),dto.getDescription());
            select.setDescription(select.getItemCaption(dto.getSystemId()));
        }
        select.select(0);
        select.addValueChangeListener(new Property.ValueChangeListener() {

            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                select.setDescription(select.getItemCaption(event.getProperty().getValue()));
                if (event.getProperty() != null && event.getProperty().getValue() != null && (StringUtils.EMPTY.equals(event.getProperty().getValue())||ConstantsUtils.NULL.equals(event.getProperty().getValue()))) {
                    select.select(0);
                }
            }
        });
        return select;
    }
       public static String getYesterdayDate() {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        date.setDate(date.getDate()-1);
        return dateFormat.format(date);
    }
       public static ComboBox getCustomizedComboBox(ComboBox select){
             select.select("5");
             select.removeItem("10");
             select.removeItem("20");
             select.removeItem("25");
             select.removeItem("50");
             select.addItem("100");
             
             return select;
       }
     
    public static String getHelperDescription(int code) throws PortalException, SystemException {
        HelperTable helperTable = HelperTableLocalServiceUtil.getHelperTable(code);
        return helperTable.getDescription();
    }
    
    public static int getHelperCode(String listName, String description) throws PortalException, SystemException {
        
        int code=0;
        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HelperTable.class);
        dynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.LIST_NAME, listName));
        dynamicQuery.add(RestrictionsFactoryUtil.ilike(ConstantsUtils.DESCRIPTION, description));
        dynamicQuery.setProjection(ProjectionFactoryUtil.property(ConstantsUtils.HELPER_TABLE_SID));
        List result=DAO.getHelperTableList(dynamicQuery);
        if(result !=null && !result.isEmpty()){
           code= Integer.valueOf(result.get(ZERO).toString());
        }
        return code;
    }
    
    public static Map<Integer, String> getCodeDescription() throws PortalException, SystemException{
        Map<Integer, String> helperTableMap = new HashMap<>();
        final List<HelperTable> list = DAO.getHelperTableDetailsByListName();
        for(HelperTable helperTable: list){
            helperTableMap.put(helperTable.getHelperTableSid(), helperTable.getDescription());
        }
        return helperTableMap;
    }
    
    /**
     * Format a time from a given input format to given target format
     * 
     * @param inputFormat
     * @param inputTimeStamp
     * @param outputFormat
     * @return
     * @throws java.text.ParseException
     */
    public static String timeStampConverter(final String inputFormat,
            String inputTimeStamp, final String outputFormat)
            throws java.text.ParseException {
        return new SimpleDateFormat(outputFormat).format(new SimpleDateFormat(
                inputFormat).parse(inputTimeStamp));
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
            String listName) throws SystemException, PortalException {
        
        final HelperDTO defaultValue=new HelperDTO(ConstantsUtils.SELECT_ONE);
        select.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        select.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
        BeanItemContainer<HelperDTO> resultContainer= new BeanItemContainer<>(HelperDTO.class);
        List<HelperDTO> helperList=getHelperResults(listName);
        resultContainer.addAll(helperList);
        select.setContainerDataSource(resultContainer);
        select.select(defaultValue);
        select.setValidationVisible(true);
        select.markAsDirty();
        
        select.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty() != null && event.getProperty().getValue() != null && (StringUtils.EMPTY.equals(event.getProperty().getValue()) || ConstantsUtils.NULL.equals(event.getProperty().getValue()))) {
                    select.select(defaultValue);
                }
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
    public List<HelperDTO> getHelperResults(final String listType) throws SystemException, PortalException {
        
        CommonDao daoImpl = CommonDaoImpl.getInstance();
        final List<HelperDTO> helperList = new ArrayList<>();
        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil
                .forClass(HelperTable.class);
        dynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.LIST_NAME,
                listType));
        dynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.DESCRIPTION));
        final List<HelperTable> list = daoImpl.getHelperTableList(dynamicQuery);
        helperList.add(new HelperDTO(0, ConstantsUtils.SELECT_ONE));
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final HelperTable helperTable = (HelperTable) list.get(i);
                helperList.add(new HelperDTO(helperTable.getHelperTableSid(), helperTable
                        .getDescription()));

            }
        }
        return helperList;
    }
    
        }
