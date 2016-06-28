/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.common;

import com.stpl.app.accountclose.utils.Constants;
import com.stpl.app.accountclose.utils.HelperListUtil;
import com.stpl.app.accountclose.utils.ValidationUtil;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.ifs.util.HelperDTO;
import com.stpl.util.dao.orm.CustomSQLUtil;
import com.vaadin.data.Property;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.TextField;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Santanukumar
 */
public class CommonUtil {

    private static CommonUtil object;
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(CommonUtil.class);

    HelperListUtil helperListUtil = HelperListUtil.getInstance();

    /**
     * The Constant CALENDAR.
     */
    public static CommonUtil getInstance() {
        if (object == null) {
            object = new CommonUtil();
        }
        return object;
    }
    private static final ResourceBundle confirmationMessage = ResourceBundle.getBundle("properties.message");

    public static String getQuery(final Map<String, String> input, final String queryName) {
        StringBuilder queryString = new StringBuilder(CustomSQLUtil.get(queryName));
        if (input != null) {
            for (Map.Entry<String, String> entry : input.entrySet()) {
                final String key = entry.getKey();
                final String value = entry.getValue();
                while (queryString.toString().contains(key)) {
                    queryString.replace(queryString.indexOf(key), queryString.indexOf(key) + key.length(), value);
                }
            }
        }
        return queryString.toString();
    }

    public static String astToPerConverter(final String inputString) {
        return StringUtils.isBlank(inputString) || "null".equals(inputString) ? "%" : inputString.replace("*", "%");
    }

    public static boolean emptyCheck(String value) {
        LOGGER.info("Entering INSIDE Check method");

        if (StringUtils.isBlank(value) || Constants.SELECT_ONE.equals(String.valueOf(value))) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean emptyCheck(Object value) {
        LOGGER.info("Entering INSIDE Check method");

        if (value == null || String.valueOf(value).equals("null") || String.valueOf(value).equals("0") || Constants.SELECT_ONE.equals(String.valueOf(value))) {
            return false;
        } else {
            return true;
        }
    }

    public static String workFlowQuery() {
        String query;
        query = "SELECT WP.PROCESS_SID, WP.PROCESS_NAME, WP.ACTIVE_FLAG AS STATUS, WP.START_DATE, WP.END_DATE, WP.SLA_CALENDAR_MASTER_SID, WP.FREQUENCY, WP.MANUAL_LAST_RUN, WP.MODIFIED_DATE, WP.MODIFIED_BY\n"
                + "FROM WORKFLOW_PROFILE WP \n"
                + "LEFT JOIN HELPER_TABLE HTF ON HTF.HELPER_TABLE_SID = WP.FREQUENCY\n"
                + "LEFT JOIN SLA_CALENDAR_MASTER SCM ON SCM.SLA_CALENDAR_MASTER_SID = WP.SLA_CALENDAR_MASTER_SID";

        return query;
    }

    public static Date parsetDate(String value) throws ParseException {
        Date date = null;
        String tempDate = StringUtils.EMPTY;
        SimpleDateFormat parse = new SimpleDateFormat(com.stpl.app.accountclose.gtnbalancereport.utils.Constants.DateFormatConstants.yyyyMMddhhmmssSSS.getConstant());
        SimpleDateFormat format = new SimpleDateFormat(com.stpl.app.accountclose.gtnbalancereport.utils.Constants.DateFormatConstants.MMddyyyyhhmmss.getConstant());
        if (value != null && !StringUtils.EMPTY.equals(value) && !Constants.NULL.equals(value)) {
            tempDate = format.format(parse.parse(value));
            date = (format.parse(tempDate));
        }
        return date;
    }

    public String getHeaderMessage() {
        return confirmationMessage.getString("MSG_ID_001");
    }

    public String getResetMessage() {
        return confirmationMessage.getString("MSG_ID_002");
    }

    public String getResetMessage1() {
        return confirmationMessage.getString("MSG_ID_003");
    }

    public String getcancelBtnCGLHeader() {
        return confirmationMessage.getString("MSG_ID_004");
    }

    public String getcancelBtnCGLMessage() {
        return confirmationMessage.getString("MSG_ID_005");
    }

    public String getNotificationMessage() {
        return confirmationMessage.getString("MSG_ID_006");
    }

    public String getSelectBtnMessage() {
        return confirmationMessage.getString("MSG_ID_007");
    }

    public String getNavigationMessage() {
        return confirmationMessage.getString("MSG_ID_008");
    }

    public String getBackBtnMessage() {
        return confirmationMessage.getString("MSG_ID_009");
    }

    public String getSaveBtnHeader() {
        return confirmationMessage.getString("MSG_ID_010");
    }

    public String getSaveBtnMessage() {
        return confirmationMessage.getString("MSG_ID_011");
    }

    public String getapproveBtnHeader() {
        return confirmationMessage.getString("MSG_ID_012");
    }

    public String getapproveBtnMessage() {
        return confirmationMessage.getString("MSG_ID_013");
    }

    public String getcloseBtnHeader() {
        return confirmationMessage.getString("MSG_ID_014");
    }

    public String getcloseBtnMessage() {
        return confirmationMessage.getString("MSG_ID_015");
    }

    public void textValidation(Object obj, Object key) {
        try {
            if (obj != null && key != null && !"null".equals(key) && obj instanceof TextField) {
                TextField tempObj = (TextField) obj;
                String[] rules = String.valueOf(key).split(",");
                if (rules[0] != null && ValidationUtil.getMessage(rules[0]) != null && StringUtils.isNotEmpty(ValidationUtil.getMessage(rules[0]))) {
                    String[] temp = ValidationUtil.getMessage(rules[0]).split(",");
                    tempObj.addValidator(new StringLengthValidator(ValidationUtil.getMessage(rules[1]), Integer.valueOf(temp[0]), Integer.valueOf(temp[1]), Boolean.valueOf(temp[2])));
                }
                if (rules[2] != null && ValidationUtil.getMessage(rules[2]) != null && StringUtils.isNotEmpty(ValidationUtil.getMessage(rules[2]))) {
                    tempObj.addValidator(new RegexpValidator(ValidationUtil.getMessage(rules[2]), ValidationUtil.getMessage(rules[3])));
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    /**
     * To get the combo box select.
     *
     * @param select the select
     * @param listName the list name
     * @param isFilter the is filter
     * @return the native select
     * @throws Exception the exception
     */
    public ComboBox loadComboBox(final ComboBox select,
            String listName, boolean isFilter) throws Exception {
        select.removeAllItems();
        final HelperDTO defaultValue = new HelperDTO(0, isFilter ? ConstantsUtils.SHOW_ALL : ConstantsUtils.SELECT_ONE);
        select.setValidationVisible(true);
        select.setImmediate(true);
        select.setNullSelectionAllowed(true);
        select.setNullSelectionItemId(defaultValue);
        select.setItemCaptionPropertyId(ConstantsUtils.DESCRIPTION);
        select.setData(listName);

        List<HelperDTO> helperList = new ArrayList<>();
        helperList.add(defaultValue);
        BeanItemContainer<HelperDTO> resultContainer = new BeanItemContainer<>(HelperDTO.class);
        if (helperListUtil.getListNameMap().get(listName) != null) {
            helperList.addAll(helperListUtil.getListNameMap().get(listName));
        }
        resultContainer.addAll(helperList);
        select.setContainerDataSource(resultContainer);
        select.select(defaultValue);

        select.addValueChangeListener(new Property.ValueChangeListener() {
            @Override
            public void valueChange(Property.ValueChangeEvent event) {
                if (event.getProperty() != null && event.getProperty().getValue() != null && (StringUtils.EMPTY.equals(event.getProperty().getValue()) || ConstantsUtils.NULL.equals(event.getProperty().getValue()))) {
                    select.select(defaultValue);
                }
                select.setDescription((String) (select.getValue() == null ? ConstantsUtils.SELECT_ONE : ((HelperDTO) select.getValue()).getDescription()));
            }
        });
        return select;
    }

    /**
     * Gets the Error code of the key.
     *
     * @param key the key
     * @return the errorcode
     */
    public static String getMessage(final String key) {
        try {
            return confirmationMessage.getString(key);
        } catch (MissingResourceException e) {
            LOGGER.error(e);
            return StringUtils.EMPTY;
        }
    }
}
