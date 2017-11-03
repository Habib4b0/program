/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.ifs.ui;

import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.util.TableResultCustom;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author mohamed.hameed
 */
public class CommonSecurityLogic {

    /**
     * Logger
     */
    private static final Logger LOGGER = Logger.getLogger(CommonSecurityLogic.class);
    /**
     * Add
     */
    public final static String ADD = "Add";
    /**
     * The add.
     */
    public final static String A = "A";
    /**
     * D
     */
    public final static String D = "D";
    /**
     * U
     */
    public final static String U = "U";
    /**
     * F
     */
    public final static String F = "F";
    /**
     * The delete.
     */
    public final static String DELETE = "Delete";
    /**
     * The edit.
     */
    public final static String EDIT = "Edit";
    /**
     * The reset.
     */
    public final static String RESET = "Reset";
    /**
     * The back.
     */
    public final static String BACK = "Back";
    /**
     * The update.
     */
    public final static String UPDATE = "Update";
    /**
     * Search
     */
    public static final String SEARCH = "search";
    /**
     * The constant auditSearch
     */
    public static final String AUDIT_SEARCH = "auditSearch";
    /**
     * View
     */
    public final static String VIEW = "view";

    /**
     * removeComponentOnPermission
     *
     * @param resultList
     * @param cssLayout
     * @param fieldIfpHM
     * @param mode
     */
    public void removeComponentOnPermission(List<Object> resultList, Object cssLayout, Map<String, AppPermission> fieldIfpHM, String mode) {
        try {
            int listSize = resultList.size();
            for (int i = 0; i < listSize; i++) {
                Object[] obj = (Object[]) resultList.get(i);
                getPermissionAndRemoveComponent(cssLayout, String.valueOf(obj[0]), String.valueOf(obj[1]), fieldIfpHM, mode);

            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * getPermissionAndRemoveComponent
     *
     * @param cssLayout
     * @param labelStr
     * @param fieldStr
     * @param fieldHM
     * @param mode
     */
    private void getPermissionAndRemoveComponent(Object cssLayout, String labelStr, String fieldStr, Map<String, AppPermission> fieldHM,
            String mode) {
        boolean appPermission = true;
        try {
            if (fieldStr != null && !fieldStr.equals("null")) {
                if (ADD.equals(mode)) {
                    appPermission = ((AppPermission) fieldHM.get(fieldStr)).isAddFlag();
                } else if (EDIT.equals(mode)) {
                    appPermission = ((AppPermission) fieldHM.get(fieldStr)).isEditFlag();
                } else if (VIEW.equals(mode)) {
                    appPermission = ((AppPermission) fieldHM.get(fieldStr)).isViewFlag();
                } else if (SEARCH.equals(mode)) {
                    if (((AppPermission) fieldHM.get(fieldStr)).isViewFlag() && ((AppPermission) fieldHM.get(fieldStr)).isEditFlag() &&
                            ((AppPermission) fieldHM.get(fieldStr)).isAddFlag()) {
                        appPermission = true;
                    } else {
                        appPermission = false;
                    }
                }
                if ((appPermission == false) && (labelStr != null)) {
                        removeComponents(cssLayout, labelStr, fieldStr);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }

    /**
     * Removing components from Layout based on the permission
     *
     * @param cssLayout
     * @param labelStr
     * @param fieldStr
     */
    private void removeComponents(Object cssLayout, String labelStr, String fieldStr) {
        if (cssLayout instanceof com.vaadin.ui.CssLayout) {
            CssLayout layout = (CssLayout) cssLayout;
            for (int j = 0; j < layout.getComponentCount(); j++) {
                if (((layout.getComponentCount() >= 1) && (layout.getComponent(j).getClass().isInstance(new VerticalLayout()))) || layout.getComponent(j).getClass().isInstance(new HorizontalLayout()) || layout.getComponent(j).getClass().isInstance(new GridLayout())) {
                    removeComponents(layout.getComponent(j), labelStr, fieldStr);
                } else {
                    int i = j;
                    if (layout.getComponent(i) != null && layout.getComponent(i).getClass().isInstance(new Label())) {
                        Label l = (Label) layout.getComponent(i);
                        if (labelStr.equals(l.getValue())) {
                            layout.removeComponent(layout.getComponent(i));
                        }
                    }
                    if (layout.getComponent(i) != null && layout.getComponent(i).getId() != null && layout.getComponent(i).getId().equals(fieldStr)) {
                        layout.removeComponent(layout.getComponent(i));
                    }
                }
            }
        } else if (cssLayout instanceof com.vaadin.ui.VerticalLayout) {
            VerticalLayout layout = (VerticalLayout) cssLayout;
            for (int j = 0; j < layout.getComponentCount(); j++) {
                if (((layout.getComponentCount() >= 1) && (layout.getComponent(j).getClass().isInstance(new VerticalLayout()))) || layout.getComponent(j).getClass().isInstance(new HorizontalLayout()) || layout.getComponent(j).getClass().isInstance(new GridLayout())) {
                    removeComponents(layout.getComponent(j), labelStr, fieldStr);
                } else {
                    int i = j;
                    if (layout.getComponent(i) != null && layout.getComponent(i).getClass().isInstance(new Label())) {
                        Label l = (Label) layout.getComponent(i);
                        if (labelStr.equals(l.getValue())) {

                            layout.removeComponent(layout.getComponent(i));
                        }
                    }
                    if (layout.getComponent(i).getId() != null && layout.getComponent(i).getId().equals(fieldStr)) {
                        layout.removeComponent(layout.getComponent(i));
                    }
                }
            }
        } else if (cssLayout instanceof com.vaadin.ui.HorizontalLayout) {
            HorizontalLayout layout = (HorizontalLayout) cssLayout;
            for (int j = 0; j < layout.getComponentCount(); j++) {

                if (((layout.getComponentCount() >= 1) && (layout.getComponent(j).getClass().isInstance(new VerticalLayout()))) || layout.getComponent(j).getClass().isInstance(new HorizontalLayout()) || layout.getComponent(j).getClass().isInstance(new GridLayout())) {
                    removeComponents(layout.getComponent(j), labelStr, fieldStr);
                } else {
                    int i = j;
                    if ((layout.getComponent(i) != null && layout.getComponent(i).getClass().isInstance(new Label())) && (labelStr.equals(((Label) layout.getComponent(i)).getValue()))) {
                            layout.removeComponent(layout.getComponent(i));
                    }
                    if (layout.getComponent(i) != null && layout.getComponent(i).getId() != null && layout.getComponent(i).getId().equals(fieldStr)) {
                        layout.removeComponent(layout.getComponent(i));
                    }
                }
            }
        } else if (cssLayout instanceof com.vaadin.ui.GridLayout) {
            GridLayout layout = (GridLayout) cssLayout;
            for (int j = 0; j < layout.getComponentCount(); j++) {
                if (layout.getComponentCount() >= 1) {
                    for (int r = 0; r < layout.getRows(); r++) {
                        for (int c = 0; c < layout.getColumns(); c++) {
                            if (layout.getComponent(r, c) != null) {
                                if (layout.getComponent(r, c).getClass().isInstance(new VerticalLayout()) 
                                        || layout.getComponent(r, c).getClass().isInstance(new HorizontalLayout()) 
                                        || layout.getComponent(r, c).getClass().isInstance(new GridLayout())) {
                                    removeComponents(layout.getComponent(r, c), labelStr, fieldStr);
                                } else {
                                    if (layout.getComponent(r, c) != null && (layout.getComponent(r, c).getClass().isInstance(new Label()))) {
                                        Label l = (Label) layout.getComponent(r, c);
                                        if (labelStr.equals(l.getValue())) {
                                            layout.removeComponent(layout.getComponent(r, c));
                                        }
                                    }
                                    if (layout.getComponent(r, c) != null && (layout.getComponent(r, c).getId() != null && layout.getComponent(r, c).getId().equals(fieldStr))) {
                                        layout.removeComponent(layout.getComponent(r, c));
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * getTableColumnsPermission TableResultCustom
     *
     * @param resultList
     * @param obj
     * @param fieldIfpHM
     * @param mode
     * @return
     */
    public TableResultCustom getTableColumnsPermission(List<Object> resultList, Object[] obj, Map<String, AppPermission> fieldIfpHM, String mode) {
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


        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return tableResultCustom;
    }

    /**
     * getTableColumnsPermission TableResultCustom
     *
     * @param resultList
     * @param obj
     * @param fieldIfpHM
     * @param mode
     * @return
     */
    public TableResultCustom getTableColumnsPermissionWithListViewCheck(List<Object> resultList, Object[] obj, Map<String, AppPermission> fieldIfpHM, String mode) {
        TableResultCustom tableResultCustom = new TableResultCustom();
        try {
            List<Object> strList = Arrays.asList(obj);
            List<String> columnList = new ArrayList<String>();
            List<Object> columnList1 = new ArrayList<Object>();
            List<String> headerList = new ArrayList<String>();
            List<String> headerList2 = new ArrayList<String>();
            for (int i = 0; i < resultList.size(); i++) {
                Object[] objResult = (Object[]) resultList.get(i);
                String catogoryName = objResult[NumericConstants.TWO].toString();
                String value = objResult[1].toString();
               
                if (("List view Header".equals(catogoryName)) && (strList.contains(value))) {
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


        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return tableResultCustom;
    }

    /**
     * modifyTableResultSecurity
     *
     * @param obj
     * @param header
     * @param fieldHM
     * @param mode
     * @return
     * @throws SystemException
     * @throws PortalException
     */
    public static TableResultCustom modifyTableResultSecurity(final Object[] obj, final String[] header, final Map<String, AppPermission> fieldHM, String mode) throws SystemException, PortalException {

        final TableResultCustom tblResultCustom = new TableResultCustom();
        boolean appPerm = false;

        String str;
        final List<Object> objResultList = new ArrayList();
        final List<String> objResultHeaderList = new ArrayList();
        if (mode != null) {
            for (int i = 0; i < obj.length; i++) {
                str = String.valueOf(obj[i]);
                if (fieldHM.get(str) != null) {
                    final AppPermission appPermission = fieldHM.get(str);
                    if ((ADD).equals(mode)) {
                        appPerm = appPermission.isAddFlag();
                    }
                    if ((EDIT).equals(mode)) {
                        appPerm = appPermission.isEditFlag();
                    }
                    if ((VIEW).equals(mode)) {
                        appPerm = appPermission.isViewFlag();
                    }
                    if (SEARCH.equals(mode)) {
                        if (appPermission.isViewFlag() && appPermission.isEditFlag() && appPermission.isAddFlag()) {
                            appPerm = true;
                        } else {
                            appPerm = false;
                        }
                    }

                    if (appPerm == true) {
                        objResultList.add(obj[i]);
                        objResultHeaderList.add(header[i]);
                    }
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

    public static TableResultCustom modifySearchTableResult(final Object[] obj, final String[] header, final Map<String, AppPermission> fieldHM) throws SystemException, PortalException {

        final TableResultCustom tblResultCustom = new TableResultCustom();
        String str;
        final List<Object> objResultList = new ArrayList();
        final List<String> objResultHeaderList = new ArrayList();

        LOGGER.debug("Entering modifyTableResult with obj length:" + obj.length + ", header length:" + header.length + ", fieldHM size:" + fieldHM.size());
        for (int i = 0; i < obj.length; i++) {
            str = String.valueOf(obj[i]);
            if (fieldHM.get(str) != null) {
                final AppPermission appPermission = fieldHM.get(str);

                if ((appPermission.isSearchFlag()) && (!objResultList.contains(obj[i]))) {

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

        LOGGER.debug("Ends modifyTableResult with tblResultCustom");
        return tblResultCustom;
    }

    /**
     * For Index Search
     *
     * @param resultList
     * @param cssLayout
     * @param fieldIfpHM
     */
    public void removeSearchComponentOnPermission(List<Object> resultList, CssLayout cssLayout, Map<String, AppPermission> fieldIfpHM) {
        try {
            int listSize = resultList.size();
            for (int i = 0; i < listSize; i++) {
                Object[] obj = (Object[]) resultList.get(i);
                getSearchPermissionAndRemoveComponent(cssLayout, String.valueOf(obj[0]), String.valueOf(obj[1]), fieldIfpHM);

            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * For Index Search
     *
     * @param cssLayout
     * @param labelStr
     * @param fieldStr
     * @param fieldHM
     */
    private void getSearchPermissionAndRemoveComponent(CssLayout cssLayout, String labelStr, String fieldStr, Map<String, AppPermission> fieldHM) {
        boolean appPermission = true;
        try {
            if (fieldStr != null) {

                appPermission = ((AppPermission) fieldHM.get(fieldStr)).isSearchFlag();

                if ((appPermission == false) && (labelStr != null)) {

                        removeComponents(cssLayout, labelStr, fieldStr);
                }
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }
    public static TableResultCustom modifyTableResult(final Object[] obj, final String[] header, final Map<String, AppPermission> fieldHM) throws SystemException, PortalException{

		final TableResultCustom tblResultCustom = new TableResultCustom();
		String str;
		final List<Object> objResultList = new ArrayList();
		final List<String> objResultHeaderList = new ArrayList();

		LOGGER.debug("Entering modifyTableResult ");
		if(obj!=null){
                for (int i = 0; i < obj.length; i++) {
			str = String.valueOf(obj[i]);
			if (fieldHM.get(str) != null) {
				final AppPermission appPermission = fieldHM.get(str);
                            
				if ((appPermission.isSearchFlag()) && (!objResultList.contains(obj[i]))) {

					objResultList.add(obj[i]);
					objResultHeaderList.add(header[i]);
				}
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

		LOGGER.debug("Ends modifyTableResult with tblResultCustom");
		return tblResultCustom;
	}
}
