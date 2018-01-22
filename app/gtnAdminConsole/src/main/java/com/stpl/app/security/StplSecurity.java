/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.security;

import com.stpl.app.adminconsole.common.util.CommonUtil;
import com.stpl.app.adminconsole.dao.impl.StplSecurityDAOImpl;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.model.UsergroupBusinessrole;
import com.stpl.app.model.UsergroupDomainMaster;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.serviceUtils.Constants;
import com.stpl.domain.adminconsole.security.StplSecurityDAO;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.dao.orm.Disjunction;
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
import org.jboss.logging.Logger;

/**
 *
 * @author nimisha.rakesh
 */
public class StplSecurity {

    public static final int TAB_VALUE = 0;

    public static final int FUNCTION_VALUE = 1;

    public static final int FIELD_VALUE = 2;

    private static final Logger LOGGER = Logger.getLogger(StplSecurity.class);

    final private StplSecurityDAO dto = new StplSecurityDAOImpl();

    private static final List<String> DOMAINIDS1 = null;

    private static Map<Integer, String> userMap = new ConcurrentHashMap<Integer, String>();

    public StplSecurityDAO getDto() {
        return dto;
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
        final Collection<Object> userGroupId = new ArrayList<>();
        final User user = dto.getUserByUserId(userId);
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
        final List<UsergroupBusinessrole> list = dto.getUsergroupBusinessroleMasterList(ugBusRoleDynamicQuery);
        UsergroupBusinessrole usergroupBusinessroleMaster;
        for (int i = 0; i < list.size(); i++) {
            usergroupBusinessroleMaster = (UsergroupBusinessrole) list.get(i);
            if (ConstantsUtils.EMPTY.equals(businessRoleIds)) {
                businessRoleIds = String.valueOf(usergroupBusinessroleMaster.getBusinessroleMasterSid());
            } else {
                final StringBuffer tempStringBuffer = new StringBuffer();
                businessRoleIds = tempStringBuffer.append(businessRoleIds).append(",").append(usergroupBusinessroleMaster.getBusinessroleMasterSid()).toString();
                tempStringBuffer.delete(0, tempStringBuffer.length());
            }
        }
        if (ConstantsUtils.EMPTY.equals(businessRoleIds)) {
            businessRoleIds = "-1";
        }

        return businessRoleIds;
    }

    /**
     * Gets the DomainIds based on the userGroupId.
     *
     * @param userGroupId - Collection<Object>
     * @return List<String>
     */
    public List<String> getDomainIds(final Collection<Object> userGroupId) {
        List<String> domainIds = new ArrayList<>();
        final DynamicQuery ugDomainDynamicQuery = DynamicQueryFactoryUtil
                .forClass(UsergroupDomainMaster.class);
        ugDomainDynamicQuery.add(RestrictionsFactoryUtil.in("usergroupId", userGroupId));
        try {
            final List<UsergroupDomainMaster> list = dto.getUsergroupDomainMasterList(ugDomainDynamicQuery);
            UsergroupDomainMaster usergroupDomainMaster;

            for (int i = 0; i < list.size(); i++) {

                usergroupDomainMaster = (UsergroupDomainMaster) list.get(i);
                final String domainIdStr = String.valueOf(usergroupDomainMaster.getDomainId());
                domainIds.add(domainIdStr);

            }

            if (domainIds.isEmpty()) {

                domainIds = DOMAINIDS1;
            }

        } catch (Exception e) {
            LOGGER.error(e);
        }

        return domainIds;
    }

    /**
     * Gets the Business Tab Permission based on the userId and moduleName.
     *
     * @param userId - String
     * @param moduleName - String
     * @return HashMap<String, AppPermission>
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     */
    public Map<String, AppPermission> getBusinessTabPermission(final String userId, final String moduleName) throws PortalException, SystemException {
        Map<String, AppPermission> tabHm = null;
        final Collection<Object> userGroupId = getUserGroupId(Long.parseLong(userId));
        final String businessRoleIds = getBusinessRoleIds(userGroupId);
        final List tabPermissionList = dto.getBusinessroleModuleMasterTabList(businessRoleIds, moduleName);
        tabHm = listToAppPermissionMap(tabPermissionList, TAB_VALUE);
        return tabHm;
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
        LOGGER.debug("" + userId);
        Map<String, AppPermission> functionHm;

        final Collection<Object> userGroupId = getUserGroupId(Long.parseLong(userId));
        final String businessRoleIds = getBusinessRoleIds(userGroupId);
        final List tabPermissionList = dto.getBusinessroleModuleMasterFunctionList(businessRoleIds, moduleName);
        functionHm = listToAppPermissionMap(tabPermissionList, FUNCTION_VALUE);
        return functionHm;
    }

    /**
     * Gets the Business Function Field based on the userId and moduleName.
     *
     * @param userId - String
     * @param moduleName - String
     * @return HashMap<String, AppPermission>
     * @throws PortalException the portal exception
     * @throws SystemException the system exception
     */
    public Map<String, AppPermission> getBusinessFieldPermission(final String userId, final String moduleName) throws PortalException, SystemException {
        Map<String, AppPermission> fieldHm;
        final Collection<Object> userGroupId = getUserGroupId(Long.parseLong(userId));
        final String businessRoleIds = getBusinessRoleIds(userGroupId);
        final List tabPermissionList = dto.getBusinessroleModuleMasterFieldList(businessRoleIds, moduleName);
        fieldHm = listToAppPermissionMap(tabPermissionList, FIELD_VALUE);
        return fieldHm;
    }

    /**
     * Gets the Domain Permission based on userId.
     *
     * @param userId - String
     * @return List<String>
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    public List<String> getDomainPermission(final String userId) throws SystemException, PortalException {
        Collection<Object> userGroupId;
        List<String> domainIds = null;
        if (null != userId) {
            userGroupId = getUserGroupId(Long.parseLong(userId));
            domainIds = getDomainIds(userGroupId);
        }
        return domainIds;
    }

    /**
     * Adds the restriction to dynamic query based on userid.
     *
     * @param companyDynamicQuery - DynamicQuery
     * @param userId - String
     * @return DynamicQuery
     * @throws SystemException the system exception
     * @throws PortalException the portal exception
     */
    public DynamicQuery addDomainRestrictions(final DynamicQuery companyDynamicQuery, final String userId) throws SystemException, PortalException {
        List<String> domainIds;
        domainIds = getDomainPermission(userId);
        final Disjunction disJunction = RestrictionsFactoryUtil.disjunction();
        for (int i = 0; i < domainIds.size(); i++) {
            final String dId = CommonUtil.CHAR_PERCENT + "D" + domainIds.get(i) + "D" + CommonUtil.CHAR_PERCENT;
            disJunction.add(RestrictionsFactoryUtil.like("domainId", dId));
        }
        companyDynamicQuery.add(disJunction);
        return companyDynamicQuery;
    }

    /**
     * Converts the obtained list from DataBase to Map Object.
     *
     * @param permissionList - List
     * @param type - type
     * @return HashMap<String, AppPermission>
     */
    public Map<String, AppPermission> listToAppPermissionMap(final List permissionList, final int type) {
        final Map<String, AppPermission> permissionHm = new HashMap<>();
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
     * Gets the domain ids1.
     *
     * @return the domain ids1
     */
    public List<String> getDomainIds1() {
        return DOMAINIDS1;
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
            getUserMap().put(Long.valueOf(user.getUserId()).intValue(), user.getFullName());
        }
        LOGGER.debug("End of getUserName method");
        return getUserMap();
    }

    public Map<String, AppPermission> getFieldOrColumnPermission(final String userId, final String moduleName, final boolean column) throws PortalException, SystemException {
        LOGGER.debug("Enters getBusinessColumnPermission()");
        List tabPermissionList;
        Set addPermission;
        Set viewPermission;
        Set editPermission;
        final Collection<Object> userGroupId = getUserGroupId(Long.parseLong(userId));
        final String businessRoleIds = getBusinessRoleIds(userGroupId);
        if (column == true) {
            tabPermissionList = getBuisnessColumn(businessRoleIds, moduleName);
            addPermission = getModePermission(businessRoleIds, moduleName, true, false, false, true);
            viewPermission = getModePermission(businessRoleIds, moduleName, false, true, false, true);
            editPermission = getModePermission(businessRoleIds, moduleName, false, false, true, true);
        } else {
            tabPermissionList = dto.getBusinessroleModuleMasterFieldList(businessRoleIds, moduleName);
            addPermission = getModePermission(businessRoleIds, moduleName, true, false, false, false);
            viewPermission = getModePermission(businessRoleIds, moduleName, false, true, false, false);
            editPermission = getModePermission(businessRoleIds, moduleName, false, false, true, false);
        }
        final Map<String, AppPermission> fieldHm = listOfFieldAppPermissionMap(tabPermissionList, addPermission, viewPermission, editPermission, FIELD_VALUE);
        LOGGER.debug("End of getBusinessFieldPermission() with fieldHm value size=" + fieldHm.size());
        return fieldHm;
    }

    public Map<String, AppPermission> listOfFieldAppPermissionMap(final List permissionList, final Set addpermission, final Set viewpermission, final Set editpermission, final int type) {
        LOGGER.debug("Entering listToAppPermissionMap()");
        final Map<String, AppPermission> permissionHm = new HashMap<>();
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

    private List getBuisnessColumn(String businessRoleIds, String moduleName) {
        List columnList;

        String query;
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

    public List fetchColumnForSecurity(String moduleName, String tabName) {
        List columnList;
        String query = "SELECT DISPLAY_NAME, PROPERTY_NAME FROM MODULE_PROPERTIES WHERE MODULE_NAME = '" + moduleName + "' "
                + " AND TAB_NAME = '" + tabName + "' AND CATEGORY_NAME IN ('List view Header') ";
        columnList = HelperTableLocalServiceUtil.executeSelectQuery(query);
        return columnList;
    }

    private Set getModePermission(String businessRoleIds, String moduleName, boolean add, boolean view, boolean edit, boolean column) {
        Set field = new HashSet();
        List columnList;
        String query;
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

	public static Map<Integer, String> getUserMap() {
		return userMap;
	}

	public static void setUserMap(Map<Integer, String> userMap) {
		StplSecurity.userMap = userMap;
	}
}
