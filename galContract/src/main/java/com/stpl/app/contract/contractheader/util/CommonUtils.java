/*
 * 
 */
package com.stpl.app.contract.contractheader.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

import com.stpl.app.contract.abstractsearch.util.ValidationUtil;
import com.stpl.app.contract.util.Constants;
import com.stpl.app.model.BrandMaster;
import com.stpl.app.model.HelperTable;
import com.stpl.app.service.BrandMasterLocalServiceUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.portal.NoSuchUserException;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.User;
import com.stpl.portal.service.UserLocalServiceUtil;
import com.vaadin.data.Property;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.TextField;

/**
 * Common utility class.
 *
 * @author sibi
 */
public class CommonUtils {

    /**
     * Constant Logger object for logging.
     */
    private static final Logger LOGGER = Logger.getLogger(CommonUtils.class);
     /** The object. */
    private static CommonUtils object;

    /**
     * Gets the single instance of CommonUtil.
     *
     * @return single instance of CommonUtil
     */
    public static CommonUtils getInstance() {
        if (object == null) {
            object = new CommonUtils();
        }
        return object;
    }

    /**
     * Empty String Constant.
     *
     * @param select the select
     * @param helperList the helper list
     * @return the native select
     */
    /**
     * Add items to the NativeSelect
     *
     * @param select
     * @param helperList
     * @return NativeSelect
     */
    public NativeSelect getNativeSelect(final NativeSelect select, final List<String> helperList) {
        LOGGER.info("enters getNativeSelect()");
        for (int i = 0; i < helperList.size(); i++) {
            final String descr = helperList.get(i);
            select.addItem(descr);
        }
        LOGGER.info("method getNativeSelect ends and returns select");
        return select;
    }
    
    public ComboBox getNativeSource(final ComboBox select, final List<HelperDTO> helperList) {
        LOGGER.info("enters getNativeDrop()");

        for (int i = 0; i < helperList.size(); i++) {
            final HelperDTO helperDTO = helperList.get(i);
            select.addItem(helperDTO.getDescription());
        }

        LOGGER.info("method getNativeDrop ends and returns select");
        return select;
    }

    public ComboBox getNativeSelect(final ComboBox select, final List<HelperDTO> helperList) {
        LOGGER.info("enters getNativeSelect()");
        for (int i = 0; helperList.size() > i; i++) {
            select.addItem(String.valueOf(helperList.get(i)
                    .getId()));
            select.setItemCaption(String.valueOf(helperList.get(i)
                    .getId()), helperList.get(i).getDescription());

        }
        LOGGER.info("method getNativeSelect ends and returns select");
        return select;
    }

    /**
     * Adds item to the NativeSelect.
     *
     * @param select the select
     * @param helperList the helper list
     * @return NativeSelect
     */
    public NativeSelect getNativeDrop(final NativeSelect select, final List<HelperDTO> helperList) {
        LOGGER.info("enters getNativeDrop()");

        for (int i = 0; i < helperList.size(); i++) {
            final HelperDTO helperDTO = helperList.get(i);
            select.addItem(helperDTO.getDescription());
        }

        LOGGER.info("method getNativeDrop ends and returns select");
        return select;
    }

    /**
     * Add items to the NativeSelect.
     *
     * @param select the select
     * @return NativeSelect
     */
    public ComboBox getStatusSelect(final ComboBox select) {
        LOGGER.info("enters getStatusSelect()");
        select.addItem(Constants.SELECT_ONE);
        select.addItem("Active");
        select.addItem("Inactive");
        LOGGER.info("method getStatusSelect ends and returns select");
        return select;
    }   

    /**
     * Convert string to date.
     *
     * @param strDate the str date
     * @return the date
     * @throws SystemException the system exception
     */
    @SuppressWarnings("PMD")
    /**
     * to convert String To Date
     *
     * @param strDate
     * @return
     * @throws java.text.ParseException
     */
    public static Date convertStringToDate(final String strDate) throws SystemException {

        LOGGER.info("enters convert2DigitTo4DigitYearFormat() with parameter strDate=" + strDate);
        LOGGER.info("End of convertStringToDate method by returning date");
        return convertStringToDate(Constants.MM_DD_YYYY, strDate);
    }

    /**
     * to convert String to Date.
     *
     * @param aMask the a mask
     * @param strDate the str date
     * @return the date
     * @throws SystemException the system exception
     */
    public static final Date convertStringToDate(final String aMask, final String strDate) throws SystemException {
        LOGGER.info("enters convert2DigitTo4DigitYearFormat() with parameters aMask= " + aMask + " strDate= " + strDate);
        final SimpleDateFormat dateFormat = new SimpleDateFormat(aMask);
        Date formattedDate = null;
        try {
            formattedDate = dateFormat.parse(strDate);
        } catch (ParseException ex) {
            LOGGER.error(ex);
            throw new SystemException(ex);
        }
        LOGGER.info("convertStringToDate method ends with returning formattedDate=" + formattedDate);
        return formattedDate;
    }

    /**
     * Add items to the NativeSelect
     *
     * @param select
     * @param helperList
     * @return NativeSelect
     */
    public ComboBox getComboBox(final ComboBox select, final List<HelperDTO> helperList) {
        LOGGER.info("enters getNativeSelect()");

        for (int i = 0; i < helperList.size(); i++) {
            select.addItem(String.valueOf(helperList.get(i).getId()));
            select.setItemCaption(String.valueOf(helperList.get(i).getId()),helperList.get(i).getDescription());
        }

        LOGGER.info("method getNativeSelect ends and returns select");
        return select;
    }

    public static ComboBox getSelectNull(final ComboBox select) {
        select.setNullSelectionAllowed(true);
        select.markAsDirty();
        select.setPageLength(7);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(Constants.ZERO);
        return select;
    }

    public static ComboBox setNullValue(final ComboBox select) {
        if (select.getValue() != null && select.getValue().equals(StringUtils.EMPTY)) {
            select.setValue(null);
        }
        return select;

    }

    /**
     *
     * @param select
     * @return
     */
    public static ComboBox getSeletNullWithListner(final ComboBox select) {
        select.setNullSelectionAllowed(true);
        select.markAsDirty();
        select.setPageLength(7);
        select.setInputPrompt(Constants.SELECT_ONE);
        select.setNullSelectionItemId(Constants.SELECT_ONE);
        select.addItem(Constants.SELECT_ONE);
        select.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                CommonUtils.setNullValue(select);
            }
        });
        return select;
    }
    
    /**
     * 
     * @param helperId
     * @return 
     */
    public static String loadDescription(final int helperId) {
        String descValue=StringUtils.EMPTY;
           HelperTable description;
        try {
            description = HelperTableLocalServiceUtil.getHelperTable(helperId);
            descValue=description.getDescription();
        } catch (PortalException ex) {
            LOGGER.error(ex);
        } catch (SystemException ex) {
            LOGGER.error(ex);
        }
        return descValue;
    }
    
    public static String getHelperDescription(int code) throws PortalException, SystemException {
        HelperTable helperTable = HelperTableLocalServiceUtil.getHelperTable(code);
        return helperTable.getDescription();
        }

    public List<HelperDTO> getBrandDropDown() throws SystemException, PortalException {

        final List<HelperDTO> helperList = new ArrayList<HelperDTO>();
        DynamicQuery dynamicQuery  = DynamicQueryFactoryUtil
                        .forClass(BrandMaster.class);
        dynamicQuery.add(RestrictionsFactoryUtil.isNotNull("brandName"));
        final List<BrandMaster> list = BrandMasterLocalServiceUtil.dynamicQuery(dynamicQuery);
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                final BrandMaster brandMaster = (BrandMaster) list.get(i);
                helperList.add(new HelperDTO(brandMaster.getBrandMasterSid(), brandMaster
                        .getBrandName()));

            }
        }

        LOGGER.info("return helperList " + helperList.size());
        return helperList;
    }
     public ComboBox getBrandList(final ComboBox select,
            final List<HelperDTO> helperList) {
        select.setPageLength(7);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(0);
        select.addItem(0);
        select.setItemCaption(0,"Show all");
        for (int i = 0; i < helperList.size(); i++) {
            final HelperDTO helperDTO = helperList.get(i);
            select.addItem(String.valueOf(helperList.get(i)
                    .getId()));
            select.setItemCaption(String.valueOf(helperList.get(i)
                    .getId()), helperList.get(i).getDescription());
        }
        select.select(0);
        select.addValueChangeListener(new Property.ValueChangeListener() {

            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty() != null && event.getProperty().getValue() != null && (StringUtils.EMPTY.equals(event.getProperty().getValue())||Constants.NULL.equals(event.getProperty().getValue()))) {
                    select.select(0);
                }
            }
        });
        return select;
    }
     /**
     * To Validate Text Fields
     *
     * @param obj
     * @param key
     */
    public void textValidation(Object obj, Object key) {
        try {
            if (obj != null && key != null && !Constants.NULL.equals(key) && obj instanceof TextField) {
                TextField tempObj = (TextField) obj;
                String[] rules = String.valueOf(key).split(Constants.COMMA);
                if (rules[0] != null && ValidationUtil.getMessage(rules[0]) != null && StringUtils.isNotEmpty(ValidationUtil.getMessage(rules[0]))) {
                    String[] temp = ValidationUtil.getMessage(rules[0]).split(Constants.COMMA);
                    tempObj.addValidator(new StringLengthValidator(ValidationUtil.getMessage(rules[1]), Integer.valueOf(temp[0]), Integer.valueOf(temp[1]), Boolean.valueOf(temp[2])));
                }

                if (rules[2] != null && !"null".equals(rules[2]) && rules[2] != null && !"null".equals(ValidationUtil.getMessage(rules[2])) && StringUtils.isNotEmpty(ValidationUtil.getMessage(rules[2]))) {
                    tempObj.addValidator(new RegexpValidator(ValidationUtil.getMessage(rules[2]), ValidationUtil.getMessage(rules[3])));
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }
     public static User getUser(final String userId) throws PortalException, SystemException {
        User loggedUserDetails = null;
         LOGGER.info("userId"+userId);

        try {
            loggedUserDetails = UserLocalServiceUtil.getUser(Long.valueOf(userId));
        } catch (NoSuchUserException noSuchUserException) {
            loggedUserDetails = null;
            LOGGER.error(noSuchUserException);
        }
         LOGGER.info("loggedUserDetails"+loggedUserDetails);
        return loggedUserDetails;
    }
}
