package com.stpl.app.global.item.util;

import com.stpl.app.global.dao.impl.ItemSearchLogicDAOImpl;
import com.stpl.app.global.dao.impl.PSLogicDAOImpl;
import com.stpl.app.global.item.logic.ItemSearchLogic;
import com.stpl.app.model.HelperTable;
import com.stpl.app.model.ItemMaster;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.app.util.GeneralCommonUtils;
import com.stpl.app.util.HelperDTO;
import com.stpl.app.util.ValidationUtils;
import com.stpl.domain.global.item.ItemDAO;
import com.stpl.domain.global.priceschedule.PriceScheduleDAO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.OrderFactoryUtil;
import com.stpl.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.search.ParseException;
import com.vaadin.data.Container;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.NativeSelect;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

// TODO: Auto-generated Javadoc
/**
 * The Class GeneralCommonUtils.
 */
public class CommonUtils {

    /**
     * The logger.
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
    public static final String STRING_NULL = ConstantsUtils.NULL;
    /**
     * The Constant STRING_ZERO.
     */
    public static final String STRING_ZERO = "0";
    /**
     * The Constant STRING_NATIVE_SELECT.
     */
    public static final String STRING_NATIVE_SELECT = "return NativeSelect :";
    /**
     * The Constant NOT_IN.
     */
    public static final String NOT_IN = " not in (";
    /**
     * The Constant STRING_IN.
     */
    public static final String STRING_IN = " in (";
    /**
     * The Constant STRING_RETURN.
     */
    public static final String STRING_RETURN = "return returnValue  ";
    /**
     * The Constant CHAR_PERCENT.
     */
    public static final char CHAR_PERCENT = '%';
    /**
     * The Constant CHAR_ASTERISK.
     */
    public static final char CHAR_ASTERISK = '*';
    /**
     * The Constant ZERO.
     */
    public static final int ZERO = 0;
    /**
     * The Constant ONE.
     */
    public static final int ONE = 1;
    /**
     * The Constant CONSTANT.
     */
    public static final int CONSTANT = 999;
    /**
     * Exception message.
     */
    public static final String EXCEPTION_MSG = "Exception in isValidValue -";
    /**
     * The Constant QUOTE.
     */
    public static final String QUOTE = "\"";
    /*
     * @param listTypeId- listType typeid of listtype table to get the description
     * @return String - The description for the specific listtype id
     */
    public static final GeneralCommonUtils commonUtils = new GeneralCommonUtils();
    /**
     * The item logic.
     */
    private final ItemSearchLogic itemLogic = new ItemSearchLogic();
    
     
    
//    private List<HelperTable> list;
//
//    {
//        try {
//            PriceScheduleDAO DAO = new PSLogicDAOImpl();
//            list = DAO.getHelperTableDetailsByListName(UIUtils.ALL);
//        } catch (SystemException ex) {
//            java.util.logging.Logger.getLogger(CommonUtils.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }

    /**
     * Gets the item logic.
     *
     * @return the item logic
     */
    public ItemSearchLogic getItemLogic() {
        return itemLogic;
    }

    /**
     * Gets the native select.
     *
     * @param select the select
     * @param helperList the helper list
     * @return the native select
     */
    public NativeSelect getNativeSelect(final NativeSelect select, final List<HelperDTO> helperList) throws Exception {
        for (int i = 0; i < helperList.size(); i++) {
            final HelperDTO helperDTO = helperList.get(i);
            select.addItem(helperDTO.getDescription());
        }
        return select;
    }

     /**
     * To get the company native select.
     *
     * @param select the select
     * @param helperList the helper list
     * @return the native select
     */   
 public ComboBox getNativeSelect(final ComboBox select, final List<HelperDTO> helperList) throws Exception {
        select.addItem(0);
        select.setItemCaption(0, ConstantsUtils.SELECT_ONE);
        for (int i = 0; i < helperList.size(); i++) {
            final HelperDTO helperDTO = helperList.get(i);
            select.addItem(helperDTO.getId());
            select.setItemCaption(helperDTO.getId(), helperDTO.getDescription());
        }
        return select;
    }
    
    /**
     * Gets the price type native.
     *
     * @param select the select
     * @param helperList the helper list
     * @return the price type native
     */
    public NativeSelect getPriceTypeNative(final NativeSelect select, final List<HelperDTO> helperList) throws Exception {

        LOGGER.info("getPriceTypeNative() P1: NativeSelect size " + select.size() + " P2:List<HelperDTO> size " + helperList.size());
        for (int i = 0; i < helperList.size(); i++) {
            final HelperDTO helperDTO = helperList.get(i);
            select.addItem(String.valueOf(helperDTO.getId()));
            select.setItemCaption(String.valueOf(helperDTO.getId()), helperDTO.getDescription());
        }
        LOGGER.info(STRING_NATIVE_SELECT + select.size());

        return select;
    }

    /**
     * Gets the identifier native select.
     *
     * @param select the select
     * @param helperList the helper list
     * @return the identifier native select
     */
    public NativeSelect getIdentifierNativeSelect(final NativeSelect select, final List<HelperDTO> helperList) throws Exception {

        LOGGER.info("getIdentifierNativeSelect() P1: NativeSelect size " + select.size() + " P2:List<HelperDTO> size " + helperList.size());
        for (int i = 0; i < helperList.size(); i++) {
            final HelperDTO helperDTO = helperList.get(i);
            select.addItem(helperDTO.getDescription());
        }
        select.addItem(FieldNameUtils.EDITLIST);
        LOGGER.info(STRING_NATIVE_SELECT + select.size());

        return select;
    }

    /**
     * Gets the selet null.
     *
     * @param select the select
     * @return the selet null
     */
    public static NativeSelect getSeletNull(final NativeSelect select) throws Exception {
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        return select;
    }

    public static ComboBox getComboBox(final ComboBox select) throws Exception {
        select.setNullSelectionAllowed(true);
        select.markAsDirty();
        select.setPageLength(7);
        select.setInputPrompt(ConstantsUtils.SELECT_ONE);
        select.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        select.addItem(ConstantsUtils.SELECT_ONE);
        return select;
    }

    /**
     * Gets the native select for identifier.
     *
     * @param select the select
     * @param helperList the helper list
     * @return the native select for identifier
     */
    public NativeSelect getNativeSelectForIdentifier(final NativeSelect select, final List<HelperDTO> helperList) {
        LOGGER.info("getIdentifierNativeSelect() P1: NativeSelect size " + select.size() + " P2:List<HelperDTO> size " + helperList.size());
        for (int i = 0; i < helperList.size(); i++) {
            final HelperDTO helperDTO = helperList.get(i);
            select.addItem(helperDTO.getDescription());
        }

        select.addItem(FieldNameUtils.EDITLIST);
        LOGGER.info(STRING_NATIVE_SELECT + select.size());

        return select;
    }

    /**
     * Gets the identifier.
     *
     * @return the identifier
     */
    public Container getIdentifier() throws SystemException, PortalException {
        final List<String> list = new ArrayList<String>();

        LOGGER.info("getIdentifier()");
        final List<HelperDTO> helperList = itemLogic.getItemQualifier();


        for (int i = 0; i < helperList.size(); i++) {
            final HelperDTO helperDTO = helperList.get(i);
            list.add(helperDTO.getDescription());
        }

        list.add(FieldNameUtils.EDITLIST);
        LOGGER.info("return Container list size :" + list.size());

        return new IndexedContainer(list);
    }

    /**
     * Gets the identifier.
     *
     * @return the identifier
     */
    public Container getPricingIdentifier() throws SystemException, PortalException, Exception {
        final List<String> list = new ArrayList<String>();

        LOGGER.info("getIdentifier()");
        List<HelperDTO> helperList;

        helperList = itemLogic.getPricingQualifier();

        for (int i = 0; i < helperList.size(); i++) {
            final HelperDTO helperDTO = helperList.get(i);
            list.add(helperDTO.getDescription());
        }

        list.add(FieldNameUtils.EDITLIST);
        LOGGER.info("return Container list size :" + list.size());

        return new IndexedContainer(list);
    }

    /**
     * Gets the status select.
     *
     * @param select the select
     * @return the status select
     */
    public NativeSelect getStatusSelect(final NativeSelect select) {
        select.addItem("Active");
        select.addItem("Inactive");
        return select;
    }

    /**
     * Gets the status select.
     *
     * @return the status select
     */
    public Container getStatusSelect() throws Exception {
        final List<String> list = new ArrayList<String>();

        list.add("Active");
        list.add("InActive");
        return new IndexedContainer(list);
    }

    /**
     * Gets the item status.
     *
     * @param select the select
     * @return the item status
     */
    public NativeSelect getItemStatus(final NativeSelect select) throws Exception {
        final List<HelperDTO> stsLst = itemLogic.getItemType(UIUtils.ITEM_STATUS);
        for (int i = 0; i < stsLst.size(); i++) {
            final HelperDTO helper = (HelperDTO) stsLst.get(i);
            select.addItem(helper.getDescription());

        }
        return select;

    }

    /**
     * Convert string to date.
     *
     * @param strDate the str date
     * @return the date
     * @throws ParseException the parse exception
     * @throws ParseException the parse exception
     */
    public static Date convertStringToDate(final String strDate) throws ParseException, java.text.ParseException {
        LOGGER.info("convertStringToDate() P1: strDate " + strDate);

        if (strDate == null || strDate.equals(EMPTY) || strDate.equals(STRING_NULL)) {
            return null;
        }
        final Date aDate = convertStringToDate(MMDDYYYY, strDate);
        LOGGER.info("return aDate :" + aDate);
        return aDate;


    }

    /**
     * Convert string to date.
     *
     * @param aMask the a mask
     * @param strDate the str date
     * @return the date
     * @throws ParseException the parse exception
     * @throws ParseException the parse exception
     */
    public static final Date convertStringToDate(final String aMask, final String strDate) throws ParseException, java.text.ParseException {

        final SimpleDateFormat dateFormat = new SimpleDateFormat(aMask);
        return dateFormat.parse(strDate);


    }

    /**
     * Convert date to string.
     *
     * @param aDate the a date
     * @return the string
     */
    public static final String convertDateToString(final Date aDate) throws Exception {

        return getDateTime(MMDDYYYY, aDate);

    }

    /**
     * Gets the date time.
     *
     * @param aMask the a mask
     * @param aDate the a date
     * @return the date time
     */
    public static final String getDateTime(final String aMask, final Date aDate) {


        if (aDate != null) {
            final SimpleDateFormat dateFormat = new SimpleDateFormat(aMask);
            return dateFormat.format(aDate);

        }

        return EMPTY;
    }
    /**
     * Checks if is numeric.
     *
     * @param str the str
     * @return true, if checks if is numeric
     */
    public static boolean isNumeric(final String massValue) throws Exception {
        LOGGER.info("isNumeric() P1: str" + massValue);
      boolean flag=false;
      
            if (StringUtils.isNotBlank(massValue)) {
                    if (!massValue.matches(ValidationUtils.REBATE_SCHEDULE_AMT_DOUBLE)) {
                               flag=true;
                             } 
                        }

        return flag;
    }

    public static ComboBox setNullValue(final ComboBox select) {
        if (select.getValue() != null && select.getValue().equals(StringUtils.EMPTY)) {
            select.setValue(ConstantsUtils.SELECT_ONE);
        }
        return select;

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
        select.setPageLength(7);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(ConstantsUtils.ZERO_INT);
        select.addItem(0);
        select.setItemCaption(0, ConstantsUtils.SELECT_ONE);
        for (int i = 0; i < helperList.size(); i++) {
            final HelperDTO helperDTO = helperList.get(i);;
            select.addItem(helperDTO.getSystemId());
            select.setItemCaption(helperDTO.getSystemId(), helperDTO.getDescription());
        }
        select.addValueChangeListener(new Property.ValueChangeListener() {
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty() != null && event.getProperty().getValue() != null && (StringUtils.EMPTY.equals(event.getProperty().getValue()) || ConstantsUtils.NULL.equals(event.getProperty().getValue()))) {
                    select.select(ConstantsUtils.SELECT_ONE);
                }
            }
        });
        return select;
    }
    
     public ComboBox getItemType(final ComboBox select,
            final List<HelperDTO> helperList) {
        select.setPageLength(7);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        select.addItem(ConstantsUtils.SELECT_ONE);
        select.setItemCaption(0, ConstantsUtils.SELECT_ONE);
        for (int i = 0; i < helperList.size(); i++) {
            final HelperDTO helperDTO = helperList.get(i);;
            select.addItem(helperDTO.getSystemId());
            select.setItemCaption(helperDTO.getSystemId(), helperDTO.getDescription());
        }
        select.addValueChangeListener(new Property.ValueChangeListener() {
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty() != null && event.getProperty().getValue() != null && (StringUtils.EMPTY.equals(event.getProperty().getValue()) || ConstantsUtils.NULL.equals(event.getProperty().getValue()))) {
                    select.select(ConstantsUtils.SELECT_ONE);
                }
            }
        });
        return select;
    }

    /**
     * Gets the select null.
     *
     * @param select the select
     * @return the selet null
     */
    public static ComboBox getSelectNull(final ComboBox select) throws Exception {
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        return select;
    }

    /**
     * Gets the status select.
     *
     * @param select the select
     * @return the status select
     */
    public ComboBox getStatus(final ComboBox select) {
        select.addItem(ConstantsUtils.SELECT_ONE);
        select.addItem("Active");
        select.addItem("Inactive");
        select.select(ConstantsUtils.SELECT_ONE);
        return select;
    }

    /**
     * Gets the item status.
     *
     * @param select the select
     * @return the item status
     */
    public ComboBox getItemStatus(final ComboBox select) throws Exception {
        final List<HelperDTO> stsLst = itemLogic.getItemType(UIUtils.STATUS);
        select.addItem(ConstantsUtils.SELECT_ONE);
        for (int i = 0; i < stsLst.size(); i++) {
            final HelperDTO helper = (HelperDTO) stsLst.get(i);
            select.addItem(helper.getDescription());

        }
        select.select(ConstantsUtils.SELECT_ONE);
        return select;

    }

    public static String getHelperDescription(int code) throws PortalException, SystemException {
        HelperTable helperTable = HelperTableLocalServiceUtil.getHelperTable(code);
        return helperTable.getDescription();
    }

    public static HelperDTO getDescriptionFromHelperTable(int id) {
        HelperDTO hDTO = new HelperDTO();
        try {

            HelperTable helperTable = HelperTableLocalServiceUtil.getHelperTable(id);
            hDTO.setId(helperTable.getHelperTableSid());
            hDTO.setDescription(helperTable.getDescription());
            return hDTO;
        } catch (PortalException ex) {

            LOGGER.error(ex);
            return new HelperDTO(0, ConstantsUtils.SELECT_ONE);
        } catch (SystemException ex) {
            LOGGER.error(ex);
            return new HelperDTO(0, ConstantsUtils.SELECT_ONE);
        }

    }

    public ComboBox getComboBoxHelperDTO(final ComboBox select,
            final List<HelperDTO> helperList) {
        select.setPageLength(7);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        select.addItem(new HelperDTO(ConstantsUtils.SELECT_ONE));
        for (int i = 0; i < helperList.size(); i++) {
            final HelperDTO helperDTO = helperList.get(i);
            select.addItem(helperDTO);
        }
        select.select(new HelperDTO(ConstantsUtils.SELECT_ONE));
        select.addValueChangeListener(new Property.ValueChangeListener() {
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty() != null && event.getProperty().getValue() != null && StringUtils.EMPTY.equals(event.getProperty().getValue())) {
                    select.select(new HelperDTO(ConstantsUtils.SELECT_ONE));
                }
            }
        });
        return select;
    }

    public static String getDescription(int id) {
        HelperDTO hDTO = new HelperDTO();
        try {
            if (id != 0) {
                HelperTable helperTable = HelperTableLocalServiceUtil.getHelperTable(id);
                hDTO.setId(helperTable.getHelperTableSid());
                hDTO.setDescription(helperTable.getDescription());
            }
            return id == 0 ? StringUtils.EMPTY : hDTO.getDescription();
        } catch (PortalException ex) {

            LOGGER.error(ex);
            return StringUtils.EMPTY;
        } catch (SystemException ex) {
            LOGGER.error(ex);
            return StringUtils.EMPTY;
        }

    }
    public static String getId(int id) {
        HelperDTO hDTO = new HelperDTO();
        try {
            if (id != 0) {
                HelperTable helperTable = HelperTableLocalServiceUtil.getHelperTable(id);
                hDTO.setId(helperTable.getHelperTableSid());
                hDTO.setDescription(helperTable.getDescription());
            }
            return id == 0 ? StringUtils.EMPTY : String.valueOf(hDTO.getId());
        } catch (PortalException ex) {

            LOGGER.error(ex);
            return StringUtils.EMPTY;
        } catch (SystemException ex) {
            LOGGER.error(ex);
            return StringUtils.EMPTY;
        }

    }

    public static int getHelperTableSId(String description, String listName) {
        int id = 0;
        try {
            if (description != null && !description.equals(StringUtils.EMPTY)) {
                List<HelperTable> helperTable = HelperTableLocalServiceUtil.findByHelperTableDetails(listName);
                for (HelperTable helperTable1 : helperTable) {
                    if (helperTable1.getDescription().equals(description)) {
                        id = helperTable1.getHelperTableSid();
                        break;
                    }
                }
            }
            return description == null || StringUtils.EMPTY.equals(description) ? 0 : id;
        } catch (SystemException ex) {
            LOGGER.error(ex);
            return 0;
        }

    }
     
      public static String loadDescription(int code) throws PortalException, SystemException {
        String descValue=StringUtils.EMPTY;
           HelperTable description=HelperTableLocalServiceUtil.getHelperTable(code);
           descValue=description.getDescription();
        
        return descValue;
    }
      
      /**
     *
     * @param comboBox
     * @param listName
     * @throws com.stpl.portal.kernel.exception.SystemException
     */
    public static void loadDDLB(ComboBox comboBox, String listName) throws SystemException {
        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HelperTable.class);
        dynamicQuery.add(RestrictionsFactoryUtil.eq("listName", listName));
        dynamicQuery.setProjection(ProjectionFactoryUtil.property("description"));
        List<String> list = HelperTableLocalServiceUtil.dynamicQuery(dynamicQuery);
        if (!list.isEmpty()) {
            for (String obj : list) {
                comboBox.addItem(obj);
            }
        }
    }
     public static Integer getHelperSID(String desc) throws SystemException {
        final DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(HelperTable.class);
        dynamicQuery.add(RestrictionsFactoryUtil.eq("description", desc));
        dynamicQuery.setProjection(ProjectionFactoryUtil.property("helperTableSid"));
        List<Integer> list = HelperTableLocalServiceUtil.dynamicQuery(dynamicQuery);
        int id=0;
        if (!list.isEmpty()) {
            for (Integer obj : list) {
                id=obj;
            }
        }
        return id;
    }
    
    public static String convertBigDecimal(String value) throws PortalException, SystemException {
        BigDecimal bd = new BigDecimal(value);
        return String.valueOf(bd);
    }
    
    public static String convertWith2Decimal(String value) throws PortalException, SystemException {
        BigDecimal bd = new BigDecimal(value);
        DecimalFormat df = new DecimalFormat("0.00");
        return String.valueOf(df.format(bd));
    }
    
    public static boolean isValidlength(final String massValue) throws Exception {
        LOGGER.info("isValidlength() P1: str" + massValue);
      boolean flag=false;
      
            if (StringUtils.isNotBlank(massValue)) {
                    if (!massValue.matches(ValidationUtils.REBATE_SCHEDULE_AMT_SAVE)) {
                               flag=true;
                             } 
                        }

        return flag;
    }
    
    /**
     * 
     * @param stringList
     * @return 
     */
    public static String stringListToString(List<String> stringList) {
        StringBuilder builder = new StringBuilder(StringUtils.EMPTY);
        if (stringList != null && !stringList.isEmpty()) {
            for (int loop = 0, limit = stringList.size(); loop < limit; loop++) {
                builder.append("'");
                builder.append(stringList.get(loop));
                builder.append("'");
                if (loop != (limit - 1)) {
                    builder.append(", ");
                }
            }
        }
        return builder.toString();
    }
    
    /**
     * 
     * @param objectList
     * @return 
     */
    public static List<String> objectListToStringList(List<Object> objectList) {
        List<String> stringList = new ArrayList<String>();
        if (objectList != null) {
            for (Object object : objectList) {
                stringList.add(String.valueOf(object));
            }
        }
        return stringList;
    }
    
    public ComboBox getNativeSelectNdc(final ComboBox select, final List<ItemMaster> list) throws Exception {
        select.addItem(ConstantsUtils.SELECT_ONE);
        for (int i = 0; i < list.size(); i++) {
            final ItemMaster im = list.get(i);
            select.addItem(im.getNdc8());
        }
        return select;
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
        
        final HelperDTO defaultValue=new HelperDTO(ConstantsUtils.SELECT_ONE);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(ConstantsUtils.SELECT_ONE);
        select.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
        BeanItemContainer<HelperDTO> resultContainer= new BeanItemContainer<HelperDTO>(HelperDTO.class);
        List<HelperDTO> helperList=getHelperResults(listName);
        resultContainer.addAll(helperList);
        select.setContainerDataSource(resultContainer);
        select.select(defaultValue);
        select.setImmediate(true);
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
    public List<HelperDTO> getHelperResults(final String listType) throws SystemException, Exception {
        final ItemDAO DAO = new ItemSearchLogicDAOImpl();
        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        final DynamicQuery cfpDynamicQuery = DynamicQueryFactoryUtil
                .forClass(HelperTable.class);
        cfpDynamicQuery.add(RestrictionsFactoryUtil.like(ConstantsUtils.LIST_NAME,
                listType));
        cfpDynamicQuery.addOrder(OrderFactoryUtil.asc(ConstantsUtils.DESCRIPTION));
        final List<HelperTable> list = DAO.getHelperTableList(cfpDynamicQuery);
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
     /**
     * To convert List<String> into a comma separated String
     *
     * @param collectionOfString
     * @param toAddQuote
     * @return
     */
    public static String CollectionToString(Collection<?> collectionOfString, boolean toAddQuote) {
        return CollectionToString(collectionOfString, toAddQuote, false);
    }

    /**
     * To convert List<String> into a comma separated String
     *
     * @param collectionOfString
     * @param toAddQuote
     * @param toRemoveSpace
     * @return
     */
    public static String CollectionToString(Collection<?> collectionOfString, boolean toAddQuote, boolean toRemoveSpace) {

        String framedString = "";
        if (collectionOfString != null && !collectionOfString.isEmpty()) {
            if (toAddQuote) {
                framedString += Arrays.toString(collectionOfString.toArray()).replace("[", "'").replace("]", "'").replace(", ", "','");
            } else {
                framedString += Arrays.toString(collectionOfString.toArray()).replace("[", "").replace("]", "");
            }

            if (toRemoveSpace) {
                framedString.replace(", ", "");
            }
        }
        return framedString;
    }
    
                             }
