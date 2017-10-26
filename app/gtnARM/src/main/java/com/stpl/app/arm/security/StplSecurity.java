/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.security;

import com.stpl.app.arm.utils.QueryUtils;
import com.stpl.app.model.UsergroupBusinessrole;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.serviceUtils.Constants;
import com.stpl.app.serviceUtils.ConstantsUtils;
import static com.stpl.ifs.ui.util.AbstractNotificationUtils.LOGGER;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.User;
import com.stpl.portal.service.UserLocalServiceUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author paranthaman.vasu
 */
public class StplSecurity {

    /**
     * The Constant FUNCTION_VALUE.
     */
    public static final int FUNCTION_VALUE = 1;
    public static final int FIELD_VALUE = 2;

    /**
     * UserMap - Contains User System ID and User Name
     */
    public static Map<Integer, String> userMap = new ConcurrentHashMap<Integer, String>();

    /**
     * The dao.
     */
    final private StplSecurityDAO dao = new StplSecurityDAOImpl();

    /**
     * Gets the dao.
     *
     * @return the dao
     */
    public StplSecurityDAO getDto() {
        return dao;
    }

    /**
     * Gets the Business Function Permission based on the userId and moduleName.
     *
     * @param userId - String
     * @param moduleName - String
     * @return HashMap<String, AppPermission>
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     */
    public Map<String, AppPermission> getBusinessFunctionPermission(final String userId, final String moduleName) throws PortalException, SystemException {
        LOGGER.debug(StringUtils.EMPTY + userId);
        Map<String, AppPermission> functionHm = new HashMap<String, AppPermission>();

        final Collection<Object> userGroupId = getUserGroupId(Long.parseLong(userId));
        final String businessRoleIds = getBusinessRoleIds(userGroupId);
        final List tabPermissionList = dao.getBusinessroleModuleMasterFunctionList(businessRoleIds, moduleName);
        functionHm = listToAppPermissionMap(tabPermissionList, FUNCTION_VALUE);
        return functionHm;

    }
/**
     * Gets the Business Function Permission based on the userId and moduleName.
     *
     * @param userId - String
     * @param moduleName - String
     * @return HashMap<String, AppPermission>
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     */
    public Map<String, AppPermission> getBusinessFunctionPermission(final String userId, final String moduleName,final String subModuleName, final String tabName) throws PortalException, SystemException {
        Map<String, AppPermission> functionHm = new HashMap<>();

        final Collection<Object> userGroupId = getUserGroupId(Long.parseLong(userId));
        final String businessRoleIds = getBusinessRoleIds(userGroupId);
        List<Object[]> tabPermissionList = QueryUtils.getItemData(getInput(businessRoleIds, moduleName,subModuleName, tabName), "buttonSecurity", null);
        functionHm = listToAppPermissionMap(tabPermissionList, FUNCTION_VALUE);
        return functionHm;
    }

    /**
     * Converts the obtained list from DataBase to Map Object.
     *
     * @param permissionList - List
     * @param type - type
     * @return HashMap<String, AppPermission>
     */
    public Map<String, AppPermission> listToAppPermissionMap(final List permissionList, final int type) {
        final Map<String, AppPermission> permissionHm = new HashMap<String, AppPermission>();
        int counter = 0;
        final int listSize = permissionList.size();
        AppPermission appPermission;
        //its altered to avoid MissingBreakInSwitch
        //it will work on the functionality of switch statement.                   
        if (type == Constants.ZERO) {
            for (; counter < listSize; counter++) {
                final Object[] obj = (Object[]) permissionList.get(counter);
                final String propertyName = String.valueOf(obj[0]).trim();
                final String accessModule = String.valueOf(obj[1]);
                appPermission = new AppPermission();
                appPermission.setPropertyName(propertyName);
                appPermission.setTabFlag(null != accessModule && "1".equals(accessModule) ? true : false);
                permissionHm.put(propertyName, appPermission);
            }
        }
        if (type == Constants.ZERO || type == Constants.ONE) {
            for (; counter < listSize; counter++) {
                final Object[] obj = (Object[]) permissionList.get(counter);
                final String propertyName = String.valueOf(obj[0]).trim();
                final String accessModule = String.valueOf(obj[1]);
                appPermission = new AppPermission();
                appPermission.setPropertyName(propertyName);
                appPermission.setFunctionFlag(null != accessModule && "1".equals(accessModule) ? true : false);
                permissionHm.put(propertyName, appPermission);
            }
        }
        if (type == Constants.ZERO || type == Constants.ONE || type == Constants.TWO) {
            for (; counter < listSize; counter++) {
                final Object[] obj = (Object[]) permissionList.get(counter);
                final String propertyName = String.valueOf(obj[0]).trim();
                final String addFlag = String.valueOf(obj[1]);
                final String editFlag = String.valueOf(obj[NumericConstants.TWO]);
                final String viewFlag = String.valueOf(obj[NumericConstants.THREE]);
                appPermission = new AppPermission();
                appPermission.setPropertyName(propertyName);
                appPermission.setAddFlag(null != addFlag && "1".equals(addFlag) ? true : false);
                appPermission.setEditFlag(null != editFlag && "1".equals(editFlag) ? true : false);
                appPermission.setViewFlag(null != viewFlag && "1".equals(viewFlag) ? true : false);
                appPermission.setSearchFlag(appPermission.isAddFlag() || appPermission.isEditFlag() || appPermission.isViewFlag() ? true : false);
                final String accessModule = String.valueOf(obj[1]);
                appPermission.setFunctionFlag(null != accessModule && "1".equals(accessModule) ? true : false);
                permissionHm.put(propertyName, appPermission);
            }
        }
        return permissionHm;
    }

    /**
     * Retrieves all the user name and stores that in the Concurrent Hash Map.
     *
     * @return the Map
     */
    public static Map<Integer, String> getUserName() throws SystemException {
        LOGGER.debug("Enters getUserName method");
        DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(User.class);
        List<User> userList = UserLocalServiceUtil.dynamicQuery(dynamicQuery);
        for (User user : userList) {
            userMap.put(Long.valueOf(user.getUserId()).intValue(), user.getFullName());
        }
        LOGGER.debug("End of getUserName method");
        return userMap;
    }

    /**
     * Gets the userGroupId based on the userId.
     *
     * @param userId - long
     * @return Collection<Object>
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     */
    public Collection<Object> getUserGroupId(final long userId) throws PortalException, SystemException {
        final Collection<Object> userGroupId = new ArrayList<Object>();
        final User user = dao.getUserByUserId(userId);
        for (int i = 0; i < user.getUserGroups().size(); i++) {
            final Long userGroup = user.getUserGroups().get(i).getUserGroupId();
            userGroupId.add(Integer.parseInt(userGroup.toString()));
        }
        return userGroupId;
    }

    /**
     * Gets the businessRoleIds based on the userGroupId.
     *
     * @param userGroupId - Collection<Object>
     * @return String
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     */
    public String getBusinessRoleIds(final Collection<Object> userGroupId) throws PortalException, SystemException {
        String businessRoleIds = StringUtils.EMPTY;
        final DynamicQuery ugBusRoleDynamicQuery = DynamicQueryFactoryUtil
                .forClass(UsergroupBusinessrole.class);
        ugBusRoleDynamicQuery.add(RestrictionsFactoryUtil.in("usergroupId", userGroupId));
        final List<UsergroupBusinessrole> list = dao.getUsergroupBusinessroleMasterList(ugBusRoleDynamicQuery);
        UsergroupBusinessrole usergroupBusinessroleMaster;
        for (int i = 0; i < list.size(); i++) {
            usergroupBusinessroleMaster = (UsergroupBusinessrole) list.get(i);
            if (StringUtils.EMPTY.equals(businessRoleIds)) {
                businessRoleIds = String.valueOf(usergroupBusinessroleMaster.getBusinessroleMasterSid());
            } else {
                final StringBuffer tempStringBuffer = new StringBuffer();
                businessRoleIds = tempStringBuffer.append(businessRoleIds).append(ConstantsUtils.COMMA).append(usergroupBusinessroleMaster.getBusinessroleMasterSid()).toString();
                tempStringBuffer.delete(0, tempStringBuffer.length());
            }
        }
        if (StringUtils.EMPTY.equals(businessRoleIds)) {
            businessRoleIds = "-1";
        }

        return businessRoleIds;
    }

    public Map<String, AppPermission> getBusinessFieldPermission(final String userId, final String moduleName) throws PortalException, SystemException {
        Map<String, AppPermission> fieldHm = new HashMap<String, AppPermission>();
        final Collection<Object> userGroupId = getUserGroupId(Long.parseLong(userId));
        final String businessRoleIds = getBusinessRoleIds(userGroupId);
        final List tabPermissionList = dao.getBusinessroleModuleMasterFieldList(businessRoleIds, moduleName);
        fieldHm = listToAppPermissionMap(tabPermissionList, FIELD_VALUE);
        return fieldHm;
    }
    public Map<String, AppPermission> getFieldOrColumnPermission(final String userId, final String moduleName, final boolean column) throws PortalException, SystemException {
        LOGGER.debug("Enters getBusinessColumnPermission()");
        List tabPermissionList = new ArrayList();
        Set addPermission = new HashSet();
        Set viewPermission = new HashSet();
        Set editPermission = new HashSet();
        final Collection<Object> userGroupId = getUserGroupId(Long.parseLong(userId));
        final String businessRoleIds = getBusinessRoleIds(userGroupId);
        if (column == true) {
            tabPermissionList = getBuisnessColumn(businessRoleIds, moduleName);
            addPermission = getModePermission(businessRoleIds, moduleName, true, false, false, true);
            viewPermission = getModePermission(businessRoleIds, moduleName, false, true, false, true);
            editPermission = getModePermission(businessRoleIds, moduleName, false, false, true, true);
        } else {
            tabPermissionList = dao.getBusinessroleModuleMasterFieldList(businessRoleIds, moduleName);
            addPermission = getModePermission(businessRoleIds, moduleName, true, false, false, false);
            viewPermission = getModePermission(businessRoleIds, moduleName, false, true, false, false);
            editPermission = getModePermission(businessRoleIds, moduleName, false, false, true, false);
        }
        final Map<String, AppPermission> fieldHm = listOfFieldAppPermissionMap(tabPermissionList, addPermission, viewPermission, editPermission, FIELD_VALUE);
        LOGGER.debug("End of getBusinessFieldPermission() with fieldHm value size=" + fieldHm.size());
        return fieldHm;
    }
    private List getBuisnessColumn(String businessRoleIds, String moduleName) {
        List columnList = new ArrayList();

        String query = StringUtils.EMPTY;
        String[] str = null;
        String mod;
        if (moduleName.contains(",")) {
            str = moduleName.split(",");
            mod = str[0];
        } else {
            mod = moduleName;
        }

        query = "select distinct spm.PROPERTY_NAME from USERGROUP_BUSINESSROLE as ubm, \n"
                + "		BUSINESSROLE_MODULE as bmm, \n"
                + "              MODULE_PROPERTIES as spm where \n"
                + "            ubm.BUSINESSROLE_MASTER_SID=bmm.BUSINESSROLE_MASTER_SID and bmm.SUBMODULE_PROPERTY_ID=spm.MODULE_PROPERTY_SID and \n"
                + "            (spm.PROPERTY_NAME is not null or spm.PROPERTY_NAME != '') \n"
                + "            and spm.CATEGORY_NAME IN ('List view Header')";
        if (businessRoleIds.length() != 0) {
            query += " AND ubm.BUSINESSROLE_MASTER_SID in ("
                    + businessRoleIds + ")";
        }
        if (mod.length() != 0) {
            query += " AND spm.MODULE_NAME in ('" + mod + "') ";
        }
        if (str != null && !str[1].equals(StringUtils.EMPTY) && str[1].length() != 0) {
            query += " AND spm.TAB_NAME like ('" + str[1] + "') ";
        }
        columnList = HelperTableLocalServiceUtil.executeSelectQuery(query);
        return columnList;

    }
    private Set getModePermission(String businessRoleIds, String moduleName, boolean add, boolean view, boolean edit, boolean column) {
        Set field = new HashSet();
        List columnList = new ArrayList();
        String query = StringUtils.EMPTY;
        String[] str = null;
        String mod;
        if (moduleName.contains(",")) {
            str = moduleName.split(",");
            mod = str[0];
        } else {
            mod = moduleName;
        }
        query = "select distinct spm.PROPERTY_NAME from USERGROUP_BUSINESSROLE as ubm, \n"
                + "		BUSINESSROLE_MODULE as bmm, \n"
                + "        MODULE_PROPERTIES as spm where \n"
                + "        ubm.BUSINESSROLE_MASTER_SID=bmm.BUSINESSROLE_MASTER_SID and bmm.SUBMODULE_PROPERTY_ID=spm.MODULE_PROPERTY_SID and \n"
                + "        (spm.PROPERTY_NAME is not null or spm.PROPERTY_NAME != '') \n";
        if (column == true) {
            query += "  and spm.CATEGORY_NAME IN ('List view Header')";
        } else {
            query += " and spm.CATEGORY_NAME NOT IN ('Button','Tab') ";
        }
        if (businessRoleIds.length() != 0) {
            query += " AND ubm.BUSINESSROLE_MASTER_SID in ("
                    + businessRoleIds + ")";
        }

        if (mod.length() != 0) {
            query += " AND spm.MODULE_NAME in ('" + mod + "') ";
        }
        if (str != null && !str[1].equals(StringUtils.EMPTY) && str[1].length() != 0) {
            query += " AND spm.TAB_NAME like ('" + str[1] + "') ";
        }

        if (add == true) {
            query += "AND bmm.ADD_FLAG = '1' ";
        } else if (view == true) {
            query += "AND bmm.VIEW_FLAG = '1' ";
        } else if (edit == true) {
            query += "AND bmm.EDIT_FLAG = '1' ";
        }
        columnList = HelperTableLocalServiceUtil.executeSelectQuery(query);
        field.addAll(columnList);
        return field;
    }
    public Map<String, AppPermission> listOfFieldAppPermissionMap(final List permissionList, final Set addpermission, final Set viewpermission, final Set editpermission, final int type) {
        LOGGER.debug("Entering listToAppPermissionMap()");
        final Map<String, AppPermission> permissionHm = new HashMap<String, AppPermission>();
        int counter = 0;

        if (type == Constants.ZERO || type == Constants.ONE || type == Constants.TWO) {
            final int listSize = permissionList.size();
            for (; counter < listSize; counter++) {
                final Object[] obj = (Object[]) permissionList.get(counter);
                final String propertyName = String.valueOf(obj[0]);
                final String addFlag = ConstantsUtils.EMPTY;
                final String editFlag = ConstantsUtils.EMPTY;
                final String viewFlag = ConstantsUtils.EMPTY;
                final AppPermission appPermission = new AppPermission();
                appPermission.setPropertyName(propertyName);

                appPermission.setAddFlag(addFlag != null && addpermission.contains(propertyName) ? true : false);

                appPermission.setEditFlag(editFlag != null && editpermission.contains(propertyName) ? true : false);
                appPermission.setViewFlag(viewFlag != null && viewpermission.contains(propertyName) ? true : false);
                appPermission.setSearchFlag(appPermission.isAddFlag() || appPermission.isEditFlag() || appPermission.isViewFlag() ? true : false);
                permissionHm.put(propertyName, appPermission);

            }
        }
        LOGGER.debug("End of listToAppPermissionMap() with permissionHm value size=" + permissionHm.size());
        return permissionHm;
    }
       private List getInput(final String businessRoleId, final String moduleName,final String subModuleName, final String tabName) {
        List input = new ArrayList();
        input.add("Button");
        input.add(moduleName);
        input.add(subModuleName);
        input.add(tabName);
        if (businessRoleId.length() != 0) {
            String sql = " AND ubm.BUSINESSROLE_MASTER_SID in ("
                    + businessRoleId + ")";
            input.add(sql);
        } else {
            input.add(StringUtils.EMPTY);
        }
        return input;
    }
}
