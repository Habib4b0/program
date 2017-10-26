package com.stpl.app.security.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.jboss.logging.Logger;

import com.stpl.app.contract.util.Constants;
import com.stpl.app.model.UsergroupBusinessrole;
import com.stpl.app.security.dao.StplSecurityDAO;
import com.stpl.app.security.dao.impl.StplSecurityDAOImpl;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.User;
import com.stpl.portal.service.UserLocalServiceUtil;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.lang.StringUtils;

/**
 * The Class StplSecurity.
 */
public class StplSecurity {

    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(StplSecurity.class);
    /**
     * The Constant TAB_VALUE.
     */
    public static final int TAB_VALUE = 0;
    /**
     * The Constant FUNCTION_VALUE.
     */
    public static final int FUNCTION_VALUE = 1;
    /**
     * The Constant FIELD_VALUE.
     */
    public static final int FIELD_VALUE = NumericConstants.TWO;
    /**
     * The dao.
     */
    private final StplSecurityDAO dao = new StplSecurityDAOImpl();

    /**
     * UserMap - Contains User System ID and User Name
     */
    public static Map<Integer, String> userMap = new ConcurrentHashMap<Integer, String>();

    /**
     * Method to get DAO
     *
     * @return
     */
    public StplSecurityDAO getDao() {
        return dao;
    }

    /**
     * Gets the user group id.
     *
     * @param userId the user id
     * @return the user group id
     */
    public Collection<Object> getUserGroupId(final long userId) throws PortalException ,SystemException {
        final Collection<Object> userGroupId = new ArrayList<Object>();
        LOGGER.debug("Enters getUserGroupId method");
        final User user = new UserLocalServiceUtil().getUser(userId);
        for (int i = 0; i < user.getUserGroups().size(); i++) {
            final Long userGroup = user.getUserGroups().get(i).getUserGroupId();
            userGroupId.add(Integer.parseInt(userGroup.toString()));

        }
        LOGGER.debug("End of getUserGroupId method");
        return userGroupId;
    }

    /**
     * Gets the business role ids.
     *
     * @param userGroupId the user group id
     * @return the business role ids
     */
    public String getBusinessRoleIds(final Collection<Object> userGroupId) throws SystemException {
        LOGGER.debug("enters getBusinessRoleIds()");
        String businessRoleIds = StringUtils.EMPTY;
        final DynamicQuery ugBusRoleDynamicQuery = DynamicQueryFactoryUtil.forClass(UsergroupBusinessrole.class);

        ugBusRoleDynamicQuery.add(RestrictionsFactoryUtil.in("usergroupId", userGroupId));

        final List<UsergroupBusinessrole> list = dao.userGroupBuisnessRoleDynamicQuery(ugBusRoleDynamicQuery);
        UsergroupBusinessrole usergroupBusinessroleMaster;

        final StringBuffer businessRoleBuffer = new StringBuffer(StringUtils.EMPTY);
        for (int i = 0; i < list.size(); i++) {
            usergroupBusinessroleMaster = (UsergroupBusinessrole) list.get(i);
            if (StringUtils.EMPTY.equals(businessRoleIds)) {
                businessRoleIds = String.valueOf(usergroupBusinessroleMaster.getBusinessroleMasterSid());
            } else {
                final String str = Constants.COMMA + usergroupBusinessroleMaster.getBusinessroleMasterSid();
                businessRoleBuffer.delete(0, businessRoleIds.length());
                businessRoleBuffer.append(businessRoleIds);
                businessRoleBuffer.append(str);
                businessRoleIds = businessRoleBuffer.toString();

            }
        }
        if (StringUtils.EMPTY.equals(businessRoleIds)) {
            businessRoleIds = "-1";
        }
        LOGGER.debug("End of getBusinessRoleIds()");
        return businessRoleIds;
    }

    /**
     * Gets the business tab permission.
     *
     * @param userId the user id
     * @param moduleName the module name
     * @return the business tab permission
     */
    public Map<String, AppPermission> getBusinessTabPermission(final String userId, final String moduleName) throws PortalException, SystemException{
        LOGGER.debug("Entering getBusinessTabPermission()");
        final Collection<Object> userGroupId = getUserGroupId(Long.parseLong(userId));
        final String businessRoleIds = getBusinessRoleIds(userGroupId);
        final List tabPermissionList = dao.getBuisnessTabPermission(businessRoleIds, moduleName);
        final Map<String, AppPermission> tabHm = listToAppPermissionMap(tabPermissionList, TAB_VALUE);
        LOGGER.debug("End of getBusinessTabPermission() with tabHm value size=" + tabHm.size());
        return tabHm;
    }

    /**
     * Gets the business function permission.
     *
     * @param userId the user id
     * @param moduleName the module name
     * @return the business function permission
     */
    public Map<String, AppPermission> getBusinessFunctionPermission(final String userId, final String moduleName) throws PortalException, SystemException {
        LOGGER.debug("Enters getBusinessFunctionPermission()");
        final Collection<Object> userGroupId = getUserGroupId(Long.parseLong(userId));
        final String businessRoleIds = getBusinessRoleIds(userGroupId);
        final List tabPermissionList = dao.getBuisnessFunctionPermission(businessRoleIds, moduleName);
        final Map<String, AppPermission> functionHm = listToAppPermissionMap(tabPermissionList, FUNCTION_VALUE);
        LOGGER.debug("End of getBusinessFunctionPermission() with functionHm value size=" + functionHm.size());
        return functionHm;
    }

    

    public Map<String, AppPermission> getFieldOrColumnPermission(final String userId, final String moduleName ,final boolean column) throws PortalException , SystemException {
        Map<String, AppPermission> fieldHm = null;
        try{
        LOGGER.debug("Enters getBusinessColumnPermission()");
         List tabPermissionList = new ArrayList();
         Set addPermission = new HashSet();
         Set viewPermission = new HashSet();
         Set editPermission = new HashSet();
        final Collection<Object> userGroupId = getUserGroupId(Long.parseLong(userId));
        final String businessRoleIds = getBusinessRoleIds(userGroupId);
        if(column==true){
          tabPermissionList = getBuisnessColumn(businessRoleIds, moduleName);
          addPermission = getModePermission(businessRoleIds, moduleName,true,false,false,true);
          viewPermission = getModePermission(businessRoleIds, moduleName,false,true,false,true);
          editPermission = getModePermission(businessRoleIds, moduleName,false,false,true,true);
        }else{
         tabPermissionList = dao.getBuisnessFieldPermission(businessRoleIds, moduleName);
         addPermission = getModePermission(businessRoleIds, moduleName,true,false,false,false);
         viewPermission = getModePermission(businessRoleIds, moduleName,false,true,false,false);
         editPermission = getModePermission(businessRoleIds, moduleName,false,false,true,false);
        }
        fieldHm = listOfFieldAppPermissionMap(tabPermissionList,addPermission,viewPermission, editPermission, FIELD_VALUE);
        LOGGER.debug("End of getBusinessFieldPermission() with fieldHm value size=" + fieldHm.size());
        }
        catch(SystemException e){
        LOGGER.error(e);
        }
        return fieldHm;
    }

    /**
     * List to app permission map.
     *
     * @param permissionList the permission list
     * @param type the type
     * @return the hash map< string, app permission>
     */
    public Map<String, AppPermission> listToAppPermissionMap(final List permissionList, final int type) throws PortalException, SystemException {
        LOGGER.debug("Entering listToAppPermissionMap()");
        final Map<String, AppPermission> permissionHm = new HashMap<String, AppPermission>();
        int counter = 0;

        if (type == Constants.ZERO) {
            final int listSize = permissionList.size();
            for (; counter < listSize; counter++) {
                final Object[] obj = (Object[]) permissionList.get(counter);
                final String propertyName = String.valueOf(obj[0]);
                final String accessModule = String.valueOf(obj[1]);
                final AppPermission appPermission = new AppPermission();
                appPermission.setPropertyName(propertyName);
                appPermission.setTabFlag(accessModule != null && "1".equals(accessModule) ? true : false);
                permissionHm.put(propertyName, appPermission);

            }
        }
        if (type == Constants.ZERO || type == Constants.ONE) {
            final int listSize = permissionList.size();
            for (; counter < listSize; counter++) {
                final Object[] obj = (Object[]) permissionList.get(counter);
                final String propertyName = String.valueOf(obj[0]);
                final String accessModule = String.valueOf(obj[1]);
                final AppPermission appPermission = new AppPermission();
                appPermission.setPropertyName(propertyName);
                appPermission.setFunctionFlag(accessModule != null && "1".equals(accessModule) ? true : false);
                permissionHm.put(propertyName, appPermission);

            }
        }
        if (type == Constants.ZERO || type == Constants.ONE || type == Constants.TWO) {
            final int listSize = permissionList.size();
            for (; counter < listSize; counter++) {
                final Object[] obj = (Object[]) permissionList.get(counter);
                final String propertyName = String.valueOf(obj[0]);
                final String addFlag = String.valueOf(obj[1]);
                final String editFlag = String.valueOf(obj[NumericConstants.TWO]);
                final String viewFlag = String.valueOf(obj[NumericConstants.THREE]);
                final AppPermission appPermission = new AppPermission();
                appPermission.setPropertyName(propertyName);

                appPermission.setAddFlag(addFlag != null && "1".equals(addFlag) ? true : false);
                appPermission.setEditFlag(editFlag != null && "1".equals(editFlag) ? true : false);
                appPermission.setViewFlag(viewFlag != null && "1".equals(viewFlag) ? true : false);
                appPermission.setSearchFlag(appPermission.isAddFlag() || appPermission.isEditFlag() || appPermission.isViewFlag() ? true : false);
                permissionHm.put(propertyName, appPermission);

            }
        }
        LOGGER.debug("End of listToAppPermissionMap() with permissionHm value size=" + permissionHm.size());
        return permissionHm;
    }
    /**
     * List to app permission map.
     *
     * @param permissionList the permission list
     * @param type the type
     * @return the hash map< string, app permission>
     */
    public Map<String, AppPermission> listOfFieldAppPermissionMap(final List permissionList,final Set addpermission,final Set viewpermission ,final Set editpermission, final int type) throws PortalException ,SystemException {
        LOGGER.debug("Entering listToAppPermissionMap()");
        final Map<String, AppPermission> permissionHm = new HashMap<String, AppPermission>();
        int counter = 0;

       
        if (type == Constants.ZERO || type == Constants.ONE || type == Constants.TWO) {
            final int listSize = permissionList.size();
            for (; counter < listSize; counter++) {
                final Object[] obj = (Object[]) permissionList.get(counter);
                final String propertyName = String.valueOf(obj[0]);
                final String addFlag =  StringUtils.EMPTY;
                final String editFlag = StringUtils.EMPTY;
                final String viewFlag = StringUtils.EMPTY ;
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

    private List getBuisnessColumn(String businessRoleIds, String moduleName) {
        List columnList = new ArrayList();

        String query = StringUtils.EMPTY;
        String[] str = null;
        String mod;
        if (moduleName.contains(Constants.COMMA)) {
            str = moduleName.split(Constants.COMMA);
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
        List columnList = new ArrayList();
        String query = "SELECT DISPLAY_NAME, PROPERTY_NAME FROM MODULE_PROPERTIES WHERE MODULE_NAME = '" + moduleName + "' "
                + " AND TAB_NAME = '" + tabName + "' AND CATEGORY_NAME IN ('List view Header') ";
        columnList = HelperTableLocalServiceUtil.executeSelectQuery(query);
        return columnList;
    }

    private Set getModePermission(String businessRoleIds, String moduleName , boolean add ,boolean view ,boolean edit,boolean column) {
       Set field = new HashSet();
        List columnList = new ArrayList();
        String query = StringUtils.EMPTY;
        String[] str = null;
        String mod;
        if (moduleName.contains(Constants.COMMA)) {
            str = moduleName.split(Constants.COMMA);
            mod = str[0];
        } else {
            mod = moduleName;
        }
         query = "select distinct spm.PROPERTY_NAME from USERGROUP_BUSINESSROLE as ubm, \n"
                + "		BUSINESSROLE_MODULE as bmm, \n"
                + "        MODULE_PROPERTIES as spm where \n"
                + "        ubm.BUSINESSROLE_MASTER_SID=bmm.BUSINESSROLE_MASTER_SID and bmm.SUBMODULE_PROPERTY_ID=spm.MODULE_PROPERTY_SID and \n"
                + "        (spm.PROPERTY_NAME is not null or spm.PROPERTY_NAME != '') \n";
         if(column==true){
             query+= "  and spm.CATEGORY_NAME IN ('List view Header')";
         }else{
                query+= " and spm.CATEGORY_NAME NOT IN ('Button','Tab') ";
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
      
        if(add==true){
             query+="AND bmm.ADD_FLAG = '1' ";
        }
        else if(view==true){
             query+="AND bmm.VIEW_FLAG = '1' ";
        }
        else if(edit==true){
             query+="AND bmm.EDIT_FLAG = '1' ";
        }
        columnList = HelperTableLocalServiceUtil.executeSelectQuery(query);
        field.addAll(columnList);
        return field;
    }
}
