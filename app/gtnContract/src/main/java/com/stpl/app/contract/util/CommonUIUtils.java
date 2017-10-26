/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.contract.util;

import com.stpl.app.contract.common.dto.SessionDTO;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.Position;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import java.text.ParseException;
import com.vaadin.ui.UI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import org.vaadin.alump.beforeunload.BeforeUnload;

/**
 *
 * @author sooriya.lakshmanan
 */
public final class CommonUIUtils {
    
     /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(CommonUIUtils.class);
     public static final Object[] ATTACHMENT_COLUMNS = new Object[]{
            "documentName", "dateAdded", "userName"};
        public static final String[] ATTACHMENT_HEADER = new String[]{
            "Document Name", "Date Added", "User Name"};
        public static final String MMDDYYYY = "MM/dd/yyyy";
        
          /**
     * Method is used to display Success Notification after save.
     *
     * @param message - message that is to be displayed.
     */
    public static void successNotification(final String message) {
        try{
        final Notification notif = new Notification(message,
                Notification.Type.HUMANIZED_MESSAGE);
        notif.setPosition(Position.MIDDLE_CENTER);
        notif.setStyleName("mystyle");
        notif.show(Page.getCurrent());
        } catch(Exception e){
          LOGGER.error(e);
        }
    }
    
      /**
     * Checks if is numeric.
     *
     * @param str the str
     * @return true, if checks if is numeric
     */
    public static boolean isValid(final String value,final String Regex) {
        boolean flag = false;
        if (StringUtils.isNotBlank(value) && !value.matches(Regex)) {
                flag = true;
        }

        return flag;
    }
    
        public void removeComponentOnPermission(List<Object> resultList, CssLayout cssLayout,Map<String, AppPermission> fieldIfpHM, String mode,CustomFieldGroup binder){
        int listSize = resultList.size();
        for (int i = 0; i < listSize; i++) {
            Object[] obj = (Object[]) resultList.get(i);
            getPermissionAndRemoveComponent(cssLayout, String.valueOf(obj[0]), String.valueOf(obj[1]), fieldIfpHM, binder, mode);
            
        }
    }

    private void getPermissionAndRemoveComponent(CssLayout cssLayout, String labelStr, String fieldStr, Map<String, AppPermission> fieldHM,
             CustomFieldGroup binder, String mode) {
        boolean appPermission = true;
        try {
            if (fieldStr != null && ("null").equals(fieldStr)) {
                if (Constants.ADD.equals(mode)) {
                    appPermission = ((AppPermission) fieldHM.get(fieldStr)).isAddFlag();
                } else if (Constants.EDIT.equals(mode)) {
                    appPermission = ((AppPermission) fieldHM.get(fieldStr)).isEditFlag();
                } else if (Constants.VIEW.equals(mode)) {
                    appPermission = ((AppPermission) fieldHM.get(fieldStr)).isViewFlag();
                }
                if (appPermission == false) {
                    if (labelStr != null) {
                        java.lang.reflect.Field field = this.getClass().getDeclaredField(labelStr);
                        if (field.get(this) instanceof Label) {
                            cssLayout.removeComponent((Label) field.get(this));
                        }
                    }
                    cssLayout.removeComponent(binder.getField(fieldStr));
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);

        }
    }    
               
    public static TableResultCustom modifyTableResultSecurity(final Object[] obj, final String[] header, final Map<String, AppPermission> fieldHM, String mode) throws SystemException, PortalException {

        TableResultCustom tblResultCustom = new TableResultCustom();
        boolean appPerm = false;

        String str = "";
        final List<Object> objResultList = new ArrayList();
        final List<String> objResultHeaderList = new ArrayList();

        for (int i = 0; i < obj.length; i++) {
            str = String.valueOf(obj[i]);
            if (fieldHM.get(str) != null) {
                final AppPermission appPermission = fieldHM.get(str);
                if (mode.equals(Constants.ADD)) {
                    appPerm = appPermission.isAddFlag();
                }
                if (mode.equals(Constants.EDIT)) {
                    appPerm = appPermission.isEditFlag();
                }
                if (mode.equals(Constants.VIEW)) {
                    appPerm = appPermission.isViewFlag();
                }
                if (appPerm == true) {
                    objResultList.add(obj[i]);
                    objResultHeaderList.add(header[i]);
                }
            }
        }
        Object[] objResult = new Object[objResultList.size()];
        String[] objResultHeader = new String[objResultList.size()];
        for (int i = 0; i < objResultList.size(); i++) {
            objResult[i] = objResultList.get(i);
            objResultHeader[i] = objResultHeaderList.get(i);
        }
        tblResultCustom.setObjResult(objResult);
        tblResultCustom.setObjResultHeader(objResultHeader);

        return tblResultCustom;
    }

    /**
     * <h1>Before Unload function</h1>
     * <p> Purpose : Fix for the Communication Error <br>
     * The Before Unload function is used to Close the Vaadin UI when the UI is unloaded from the browser 
     * , so that the data will not retained when the user navigates back to same UI. <br>
     * 
     * Comments about Code : Action performed after the listener is fix for the Refresh problem <br>
     * Refresh Problems Faced : As the Vaadin Session Values has been changed to Sesison DTO, on refresh the Id gets flushed and Screen collapse.
     * The Actions performed after the Unload listener is to maintain the required ID
     *
     * @param uI - UI Object
     * @param sessionDTO - SessionDTO Object
     * @return
     */
    public static final void beforeUnloadCloseUi(final UI uI, final SessionDTO sessionDTO) {
        BeforeUnload ob = BeforeUnload.closeBeforeUnload(uI);
        ob.addUnloadListener(new BeforeUnload.UnloadListener() {
            @Override
            public void unload(BeforeUnload.UnloadEvent event) {
                VaadinSession.getCurrent().setAttribute(ConstantsUtils.SYSTEM_ID, sessionDTO.getSystemId());
                VaadinSession.getCurrent().setAttribute(ConstantsUtils.GLOBAL_FILES_MODE, sessionDTO.getMode());
                VaadinSession.getCurrent().setAttribute(Constants.CONTRACT_SYSTEM_ID, sessionDTO.getContractSystemId());
                VaadinSession.getCurrent().setAttribute(Constants.IFP_ID, sessionDTO.getIfpSystemId());
                VaadinSession.getCurrent().setAttribute(Constants.CFP_ID, sessionDTO.getCfpSystemId());
                VaadinSession.getCurrent().setAttribute(Constants.PS_ID, sessionDTO.getPsSystemId());
                VaadinSession.getCurrent().setAttribute(Constants.RS_ID, sessionDTO.getRsSystemId());
                VaadinSession.getCurrent().setAttribute(Constants.TEMP_ID, sessionDTO.getUiSessionId());
                VaadinSession.getCurrent().setAttribute(Constants.SESSION_DATE, sessionDTO.getSessionDate());
                VaadinSession.getCurrent().setAttribute(Constants.EDIT, sessionDTO.getEdit());
                if (!"true".equals(VaadinSession.getCurrent().getAttribute(Constants.EXCEL_CLOSE))) {
                    uI.close();
                } else {
                    VaadinSession.getCurrent().setAttribute(Constants.EXCEL_CLOSE, "");
                }
            }
        });
        if (VaadinSession.getCurrent().getAttribute(ConstantsUtils.GLOBAL_FILES_MODE) != null && !"".equals(VaadinSession.getCurrent().getAttribute(ConstantsUtils.GLOBAL_FILES_MODE))) {
            sessionDTO.setMode((String) VaadinSession.getCurrent().getAttribute(ConstantsUtils.GLOBAL_FILES_MODE));
            VaadinSession.getCurrent().setAttribute(ConstantsUtils.GLOBAL_FILES_MODE, "");
        }
        if (VaadinSession.getCurrent().getAttribute(ConstantsUtils.EDIT) != null && !"".equals(VaadinSession.getCurrent().getAttribute(ConstantsUtils.EDIT))) {
            sessionDTO.setEdit((String) VaadinSession.getCurrent().getAttribute(ConstantsUtils.EDIT));
            VaadinSession.getCurrent().setAttribute(ConstantsUtils.EDIT, "");
        }
        if (VaadinSession.getCurrent().getAttribute(ConstantsUtils.SYSTEM_ID) != null && !"".equals(VaadinSession.getCurrent().getAttribute(ConstantsUtils.SYSTEM_ID))) {
            sessionDTO.setSystemId((Integer) VaadinSession.getCurrent().getAttribute(ConstantsUtils.SYSTEM_ID));
            VaadinSession.getCurrent().setAttribute(ConstantsUtils.SYSTEM_ID, "");
        }
        if (VaadinSession.getCurrent().getAttribute(Constants.CONTRACT_SYSTEM_ID) != null && !"".equals(VaadinSession.getCurrent().getAttribute(Constants.CONTRACT_SYSTEM_ID))) {
            sessionDTO.setContractSystemId((Integer) VaadinSession.getCurrent().getAttribute(Constants.CONTRACT_SYSTEM_ID));
            VaadinSession.getCurrent().setAttribute(Constants.CONTRACT_SYSTEM_ID, "");
        }
        if (VaadinSession.getCurrent().getAttribute(Constants.IFP_ID) != null && !"".equals(VaadinSession.getCurrent().getAttribute(Constants.IFP_ID))) {
            sessionDTO.setIfpSystemId((Integer) VaadinSession.getCurrent().getAttribute(Constants.IFP_ID));
            VaadinSession.getCurrent().setAttribute(Constants.IFP_ID, "");
        }
        if (VaadinSession.getCurrent().getAttribute(Constants.CFP_ID) != null && !"".equals(VaadinSession.getCurrent().getAttribute(Constants.CFP_ID))) {
            sessionDTO.setCfpSystemId((Integer) VaadinSession.getCurrent().getAttribute(Constants.CFP_ID));
            VaadinSession.getCurrent().setAttribute(Constants.CFP_ID, "");
        }
        if (VaadinSession.getCurrent().getAttribute(Constants.PS_ID) != null && !"".equals(VaadinSession.getCurrent().getAttribute(Constants.PS_ID))) {
            sessionDTO.setPsSystemId((Integer) VaadinSession.getCurrent().getAttribute(Constants.PS_ID));
            VaadinSession.getCurrent().setAttribute(Constants.PS_ID, "");
        }
        if (VaadinSession.getCurrent().getAttribute(Constants.RS_ID) != null && !"".equals(VaadinSession.getCurrent().getAttribute(Constants.RS_ID))) {
            sessionDTO.setRsSystemId((Integer) VaadinSession.getCurrent().getAttribute(Constants.RS_ID));
            VaadinSession.getCurrent().setAttribute(Constants.RS_ID, "");
        }
    } 
    
  /**
   * Table level Security for the Contract
   * @param resultList
   * @param obj
   * @param fieldIfpHM
   * @param mode
   * @return 
   */
    public static TableResultCustom getTableColumnsPermission(List<Object> resultList, Object[] obj, Map<String, AppPermission> fieldIfpHM, String mode) {
        TableResultCustom tableResultCustom = new TableResultCustom();
        try {
            List<Object> strList = Arrays.asList(obj);
            List<String> columnList = new ArrayList<String>();
            List<Object> columnList1 = new ArrayList<Object>();
            List<String> headerList = new ArrayList<String>();
            List<String> headerList2 = new ArrayList<String>();
            for (int i = 0; i < resultList.size(); i++) {
                Object[] objResult = (Object[]) resultList.get(i);
                String value = objResult[1].toString();
                if (strList.contains(value)) {
                    columnList.add(value.toString());
                    headerList.add(objResult[0].toString());
                }
            }
            for (Object headerList1 : strList) {
                if (columnList.contains(headerList1.toString())) {
                    columnList1.add(headerList1);
                    headerList2.add(headerList.get(columnList.indexOf(headerList1.toString())));
                }
            }
            String[] headerArray = new String[headerList2.size()];
            headerArray = headerList2.toArray(headerArray);
            tableResultCustom = modifyTableResultSecurity(columnList1.toArray(), headerArray, fieldIfpHM, mode);

        } catch (SystemException ex) {
            LOGGER.error(ex);
        } catch (PortalException ex) {
            LOGGER.error(ex);
        }
        return tableResultCustom;
    }
    /**
     * To convert, less than 4 digit year to 4 digit year by adding current century
     * 
     * @param aDate - Entered date
     * @return converted 4 digit year date
     */
    public static final String convert2DigitTo4DigitYear(final Date aDate) {
        if(aDate != null){
            Calendar calendar = Calendar.getInstance();
            int currentYear = calendar.get(Calendar.YEAR);
            calendar.setTime(aDate);
            int year = calendar.get(Calendar.YEAR);
            int length = 0;
            if(year != 0){
                length = (int) Math.log10(year) + 1;            
            }
            if (length < NumericConstants.FOUR) {
                    int century = currentYear / NumericConstants.HUNDRED;
                    aDate.setYear(((century * NumericConstants.HUNDRED) + year) -NumericConstants.ONE_NINE_ZERO_ZERO);
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
            Date date = new Date();
            date = inputDateFormatter.parse(stringDate);
            return outputDateFormatter.format(date);
        } catch (ParseException ex) {
            LOGGER.error(ex);
        }
        return null;
    }
}
    

