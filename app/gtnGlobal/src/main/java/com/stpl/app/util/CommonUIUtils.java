package com.stpl.app.util;

import com.stpl.app.global.abstractsearch.util.ConstantUtil;
import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.deductioncalendar.logic.DeductionCalendarLogic;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.ifs.ui.util.NumericConstants;
import org.jboss.logging.Logger;

import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.Position;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import java.text.ParseException;
import com.vaadin.ui.UI;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import org.apache.commons.lang.StringUtils;
import org.vaadin.alump.beforeunload.BeforeUnload;

// TODO: Auto-generated Javadoc
/**
 * Common UI Utility class that contains Constants for table headers and
 * propertyIds.
 *
 * @author
 */
public class CommonUIUtils {

    private static ResourceBundle constantProperties = ResourceBundle.getBundle("properties.constants");

    /**
     * The Constant REBATE_PLAN_COLUMNS.
     */
    public final Object[] rebatePlanColumns = new Object[]{
        "rebatePlanId", "rebatePlanNo", ConstantsUtils.REBATE_PLAN_NAME,
        "rebatePlanStatus", "rebatePlanType", "formulaType",
        "rebateBasedOn", "rebateStructure", "rebateRangeBasedOn",
        "secondaryRebatePlanId", "secondaryRebatePlanNo",
        "secondaryRebatePlanName"};

    /**
     * The Constant REBATE_PLAN_HEADER.
     */
    public final String[] rebatePlanHeader = new String[]{
        "Rebate Plan ID", "Rebate Plan No", "Rebate Plan Name",
        "Rebate Plan Status", "Rebate Plan Type", "Formula Type",
        "Rebate Based On", "Rebate Structure", "Rebate Range Based On",
        "Secondary Rebate Plan ID", "Secondary Rebate Plan No",
        "Secondary Rebate Plan Name"};

    /**
     * The Constant REBATE_TIER_COLUMNS.
     */
    public final Object[] rebateTierColumns = new Object[]{"tierFrom", "tierTo", "tierOperatorValue", "tierValue",
        "tierTolerance", ConstantsUtils.FORMULA_NO, ConstantsUtils.FORMULA_NAME, "secondaryRebatePlanNo", "secondaryRebatePlanName"};

    /**
     * The Constant REBATE_TIER_HEADER.
     */
    public final String[] rebateTierHeader = new String[]{"From", "To", "Tier Operator", "Tier Value",
        "Tier Tolerance", "Formula No", "Formula Name", "Secondary Rebate Plan No", "Secondary Rebate Plan Name"};

    /**
     * The Constant REBATE_SCHEDULE_COLUMNS.
     */
    public final Object[] rebateScheduleColumns = new Object[]{"systemId",
        "rebateScheduleId", "rebateScheduleNo", "rebateScheduleName",
        "rebateScheduleType", "rebateScheduleStatus", "rsCategory", ConstantsUtils.START_DATE, ConstantsUtils.END_DATE, "rsDesignation", "parentId", "parentName"
    };

    /**
     * The Constant REBATE_SCHEDULE_HEADER.
     */
    public final String[] rebateScheduleHeader = new String[]{"System ID",
        "Rebate Schedule ID", "Rebate Schedule No",
        "Rebate Schedule Name", "Rebate Schedule Type",
        "Rebate Schedule Status", "Rebate Schedule Category", "Start Date", "End Date", "Rebate Schedule Designation", "Parent ID", "Parent Name"};

    /**
     * The Constant IFP_COLUMNS_IN_RS.
     */
    public final Object[] ifpColumnsInRs = new Object[]{"ifpId",
        "ifpNo", "ifpName", "ifpStatus", "ifpStartDate", "ifpEndDate", "ifpType", "ifpCategory"};

    /**
     * The Constant IFP_HEADER_IN_RS.
     */
    public final String[] ifpHeadersinRs = new String[]{"IFP ID",
        "IFP Number", "IFP Name", "IFP Status",
        "IFP Start Date", "IFP End Date", "IFP Type", "IFP Category"};

    /**
     * The Constant ITEM_DETAILS_COLUMNS_IN_RS.
     */
    public final Object[] itemDetailsColumnsInRsView = new Object[]{
        "checkbox", "itemNo", "itemName", "attachedStatusValue", ConstantsUtils.START_DATE, ConstantsUtils.END_DATE,
        ConstantsUtils.FORMULA_NO, ConstantsUtils.FORMULA_NAME, "formulaMethodId", ConstantsUtils.REBATE_PLAN_NAME, "rebateAmount", "attachedDate"};

    public final Object[] attachmentColumns = new Object[]{
        "documentName", "dateAdded", "userName"};
    public final String[] attachmentHeader = new String[]{
        "Document Name", "Date Added", "User Name"};
    /**
     * The Constant ITEM_DETAILS_COLUMNS_IN_RS_WITH_BUNDLE.
     */
    public final Object[] itemDetailsColumnsInRsWithBundle = new Object[]{
        "checkbox", "itemNo", "itemName", "attachedStatus", ConstantsUtils.START_DATE, ConstantsUtils.END_DATE, ConstantsUtils.FORMULA_NO, ConstantsUtils.FORMULA_NAME, "formulaMethodId", ConstantsUtils.REBATE_PLAN_NAME, "rebateAmount",
        "bundleNo", "attachedDate"
    };

    /**
     * The Constant ITEM_DETAILS_HEADER_IN_RS_WITH_BUNDLE.
     */
    public final String[] itemDetailsHeaderInRsWithBundle = new String[]{
        "", "Item No", "Item Name", "RS Status", "RS Start Date", "RS End Date", "Formula No", "Formula Name", "Formula Method ID", "Rebate Plan Name", "Rebate Amount",
        "Bundle No", "Attached Date"};

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(CommonUIUtils.class);

    public static final String MMDDYYYY = "MM/dd/yyyy";

    /**
     * Default Constructor.
     */
    public CommonUIUtils() {
        //constructor

    }

    /**
     * Method is used to display Success Notification after save.
     *
     * @param message - message that is to be displayed.
     */
    public static void successNotification(final String message) {
        try {
            final Notification notif = new Notification(message,
                    Notification.Type.HUMANIZED_MESSAGE);
            notif.setPosition(Position.MIDDLE_CENTER);
            notif.setStyleName("mystyle");
            notif.setDelayMsec(NumericConstants.THOUSAND);
            notif.show(Page.getCurrent());
        } catch (Exception e) {

            LOGGER.error(e);
        }
    }

    /**
     * To get the Permission based on mode
     *
     * @param cssLayout
     * @param labelValue
     * @param componentValue
     * @param fieldHM
     * @param fieldValue
     * @param moduleName
     * @param mode
     */
    public void getPermissionBasedOnMode(CssLayout cssLayout, Label labelValue, Component componentValue, Map<String, AppPermission> fieldHM,
            String fieldValue, String mode) {
        if (ConstantsUtils.ADD.equals(mode)) {
            ResponsiveUtils.removeComponentFromCssLayout(cssLayout, ResponsiveUtils.makeLabel(labelValue, true), componentValue, ((AppPermission) fieldHM
                    .get(fieldValue) == null) ? false : ((AppPermission) fieldHM.get(fieldValue)).isAddFlag());
        } else if (ConstantsUtils.EDIT.equals(mode)) {
            ResponsiveUtils.removeComponentFromCssLayout(cssLayout, ResponsiveUtils.makeLabel(labelValue, true), componentValue, ((AppPermission) fieldHM
                    .get(fieldValue) == null) ? false : ((AppPermission) fieldHM.get(fieldValue)).isEditFlag());
        } else if (ConstantsUtils.VIEW.equals(mode)) {
            ResponsiveUtils.removeComponentFromCssLayout(cssLayout, ResponsiveUtils.makeLabel(labelValue, true), componentValue, ((AppPermission) fieldHM
                    .get(fieldValue) == null) ? false : ((AppPermission) fieldHM.get(fieldValue)).isViewFlag());
        }
    }

    public void removeComponentOnPermission(List<Object> resultList, CssLayout cssLayout, Map<String, AppPermission> fieldIfpHM, String mode, ErrorfulFieldGroup binder) {
        int listSize = resultList.size();
        for (int i = 0; i < listSize; i++) {
            Object[] obj = (Object[]) resultList.get(i);
            getPermissionAndRemoveComponent(cssLayout, String.valueOf(obj[0]), String.valueOf(obj[1]), fieldIfpHM, binder, mode);

        }
    }

    private void getPermissionAndRemoveComponent(CssLayout cssLayout, String labelStr, String fieldStr, Map<String, AppPermission> fieldHM,
            ErrorfulFieldGroup binder, String mode) {
        boolean appPermission = true;
        try {
            if (fieldStr != null) {
                if (ConstantsUtils.ADD.equals(mode)) {
                    appPermission = ((AppPermission) fieldHM.get(fieldStr)).isAddFlag();
                } else if (ConstantsUtils.EDIT.equals(mode)) {
                    appPermission = ((AppPermission) fieldHM.get(fieldStr)).isEditFlag();
                } else if (ConstantsUtils.VIEW.equals(mode)) {
                    appPermission = ((AppPermission) fieldHM.get(fieldStr)).isViewFlag();
                }
                if (appPermission == false) {
                    if (labelStr != null) {
                        for (java.lang.reflect.Field field : this.getClass().getDeclaredFields()) {
                            if (labelStr.equals(String.valueOf(field)) && field.get(labelStr) instanceof Label) {
                                cssLayout.removeComponent((Label) field.get(labelStr));
                            }
                        }
                    }
                    cssLayout.removeComponent(binder.getField(fieldStr));
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    /**
     * To convert, less than 4 digit year to 4 digit year by adding current
     * century
     *
     * @param aDate - Entered date
     * @return converted 4 digit year date
     */
    public static final String convert2DigitTo4DigitYear(final Date aDate) {
        if (aDate != null) {
            Calendar calendar = Calendar.getInstance();
            int currentYear = calendar.get(Calendar.YEAR);
            calendar.setTime(aDate);
            int year = calendar.get(Calendar.YEAR);
            int length = 0;
            if (year != 0) {
                length = (int) Math.log10(year) + 1;
            }
            if (length < NumericConstants.FOUR) {
                int century = currentYear / NumericConstants.HUNDRED;
                aDate.setYear(((century * NumericConstants.HUNDRED) + year) - NumericConstants.ONE_NINE_ZERO_ZERO);
            }
        }
        return getDateTime(MMDDYYYY, aDate);
    }

    /**
     * To format the date
     *
     * @param aMask - Date format
     * @param aDate - Entered date
     * @return Formatted date
     */
    public static final String getDateTime(final String aMask, final Date aDate) {
        if (aDate != null) {
            final SimpleDateFormat dateFormat = new SimpleDateFormat(aMask);
            final String returnValue = dateFormat.format(aDate);
            return returnValue;
        }
        return StringUtils.EMPTY;
    }

    public static String convertStringToDate(String stringDate, String inputFormat, String outputFormat) {
        try {
            SimpleDateFormat inputDateFormatter = new SimpleDateFormat(inputFormat);
            SimpleDateFormat outputDateFormatter = new SimpleDateFormat(outputFormat);
            Date date;
            date = inputDateFormatter.parse(stringDate);
            return outputDateFormatter.format(date);
        } catch (ParseException ex) {
            LOGGER.error(ex);
        }
        return null;
    }

    /**
     * <h1>Before Unload function</h1>
     * <p>
     * Purpose : Fix for the Communication Error <br>
     * The Before Unload function is used to Close the Vaadin UI when the UI is
     * unloaded from the browser , so that the data will not retained when the
     * user navigates back to same UI. <br>
     *
     * Comments about Code : Action performed after the listener is fix for the
     * Refresh problem <br>
     * Refresh Problems Faced : As the Vaadin Session Values has been changed to
     * Sesison DTO, on refresh the Id gets flushed and Screen collapse. The
     * Actions performed after the Unload listener is to maintain the required
     * ID
     *
     *
     * @param uI - UI Object
     * @param sessionDTO - SessionDTO Object
     * @return
     */
    public static final void beforeUnloadCloseUi(final UI uI, final SessionDTO sessionDTO, final String moduleName) {
        BeforeUnload ob = BeforeUnload.closeBeforeUnload(uI);
        ob.addUnloadListener(new BeforeUnload.UnloadListener() {

            @Override
            public void unload(BeforeUnload.UnloadEvent event) {
                VaadinSession.getCurrent().setAttribute(ConstantsUtils.SYSTEM_ID, sessionDTO.getSystemId());
                VaadinSession.getCurrent().setAttribute(ConstantsUtils.GLOBAL_FILES_MODE, sessionDTO.getMode());
                if (com.stpl.app.global.abstractsearch.util.ConstantUtil.DEDUCTION_CALENDAR.equalsIgnoreCase(moduleName)) {
                    new DeductionCalendarLogic().deleteTempDeductionDetails(sessionDTO);
                    new DeductionCalendarLogic().deleteTempSeletionTable(sessionDTO);
                }
                uI.close();
            }
        });
        if (VaadinSession.getCurrent().getAttribute(ConstantsUtils.GLOBAL_FILES_MODE) != null && !"".equals(VaadinSession.getCurrent().getAttribute(ConstantsUtils.GLOBAL_FILES_MODE))) {
            sessionDTO.setMode((String) VaadinSession.getCurrent().getAttribute(ConstantsUtils.GLOBAL_FILES_MODE));
            VaadinSession.getCurrent().setAttribute(ConstantsUtils.GLOBAL_FILES_MODE, "");
        }
        if (VaadinSession.getCurrent().getAttribute(ConstantsUtils.SYSTEM_ID) != null && !"".equals(VaadinSession.getCurrent().getAttribute(ConstantsUtils.SYSTEM_ID))) {
            sessionDTO.setSystemId((Integer) VaadinSession.getCurrent().getAttribute(ConstantsUtils.SYSTEM_ID));
            VaadinSession.getCurrent().setAttribute(ConstantsUtils.SYSTEM_ID, "");
        }

    }

    /**
     * Used to load the Ddlb from property file
     *
     * @param comboBox
     * @param key
     * @return
     */
    public static ComboBox loadDDLBFromProperties(ComboBox comboBox, String key) {
        comboBox.addItem(ConstantUtil.SELECT_ONE);
        comboBox.setNullSelectionItemId(ConstantUtil.SELECT_ONE);
        List<String> ddlbValues = Arrays.asList(constantProperties.getString(key).split(","));
        if (ddlbValues != null && !ddlbValues.isEmpty()) {
            for (String ddlbValue : ddlbValues) {
                comboBox.addItem(ddlbValue);
            }
        }
        comboBox.select(ConstantUtil.SELECT_ONE);
        return comboBox;
    }
}
