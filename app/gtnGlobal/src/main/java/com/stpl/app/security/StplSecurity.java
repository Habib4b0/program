package com.stpl.app.security;

import com.stpl.app.global.dao.impl.StplSecurityDAOImpl;
import com.stpl.app.model.UsergroupBusinessrole;
import com.stpl.app.model.UsergroupDomainMaster;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.util.CommonUIUtils;
import com.stpl.app.util.Constants;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.domain.global.security.StplSecurityDAO;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.ifs.ui.util.converters.DataTypeConverter;
import com.liferay.portal.kernel.dao.orm.Disjunction;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.stpl.app.service.UsergroupBusinessroleLocalServiceUtil;
import com.stpl.app.service.UsergroupDomainMasterLocalServiceUtil;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

 
/**
 * Class holds the logic to grant permission to Users.
 *
 * @author
 */
public class StplSecurity {

    /** The Constant TAB_VALUE. */
    public static final int TAB_VALUE = 0;
    
    /** The Constant FUNCTION_VALUE. */
    public static final int FUNCTION_VALUE = 1;
    
    /** The Constant FIELD_VALUE. */
    public static final int FIELD_VALUE = NumericConstants.TWO;
    
    /** The Constant LOGGER. */
    private static final Logger LOGGER = LoggerFactory.getLogger(StplSecurity.class);
   
   /** The dao. */
        private final StplSecurityDAO dao = new StplSecurityDAOImpl();
	 
 	/** The domain ids1. */
 	private static final List<String> DOMAINIDS1 = null;
        
        /** UserMap - Contains User System ID and User Name */
 	private static final Map<Integer,String> userMap=new ConcurrentHashMap<>();   

        /**
     * Gets the dao.
     *
     * @return the dao
     */
public StplSecurityDAO getDto() {
	return dao;
}

	/**
	 * Gets the userGroupId based on the userId.
	 *
	 * @param userId - long
	 * @return Collection<Object>
	 * @throws PortalException the portal exception
	 * @throws SystemException the system exception
	 */
    public Collection<Object> getUserGroupId(final long userId)  throws PortalException {   
        final Collection<Object> userGroupId = new ArrayList<>();
            final User user = dao.getUserByUserId(userId);
            for (int i = 0; i < user.getUserGroups().size(); i++) {
                final Long userGroup = user.getUserGroups().get(i).getUserGroupId();
                userGroupId.add(DataTypeConverter.convertLongToInteger(userGroup));
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
    public String getBusinessRoleIds(final Collection<Object> userGroupId) throws PortalException {
    	String businessRoleIds=StringUtils.EMPTY;
        final DynamicQuery ugBusRoleDynamicQuery = UsergroupBusinessroleLocalServiceUtil.dynamicQuery();
        ugBusRoleDynamicQuery.add(RestrictionsFactoryUtil.in("usergroupId", userGroupId));
            final List<UsergroupBusinessrole> list = dao.getUsergroupBusinessroleMasterList(ugBusRoleDynamicQuery);
            UsergroupBusinessrole usergroupBusinessroleMaster;
            for (int i = 0; i < list.size(); i++) {
                usergroupBusinessroleMaster = (UsergroupBusinessrole) list.get(i);
                if (StringUtils.EMPTY.equals(businessRoleIds)) {
                    businessRoleIds=String.valueOf(usergroupBusinessroleMaster.getBusinessroleMasterSid());
                } else {
                    final StringBuilder tempStringBuffer = new StringBuilder();
                    businessRoleIds = tempStringBuffer.append(businessRoleIds).append(',').append(usergroupBusinessroleMaster.getBusinessroleMasterSid()).toString();
                    tempStringBuffer.delete(0, tempStringBuffer.length());
                } 
            }
            if (StringUtils.EMPTY.equals(businessRoleIds)) {
                businessRoleIds="-1";
            }

        return businessRoleIds;
    }

    /**
     * Gets the DomainIds based on the userGroupId.
     * @param userGroupId - Collection<Object>
     * @return List<String>
     */
    public List<String> getDomainIds(final Collection<Object> userGroupId) {
        List<String> domainIds = new ArrayList<>();
        final DynamicQuery ugDomainDynamicQuery = UsergroupDomainMasterLocalServiceUtil.dynamicQuery();
        ugDomainDynamicQuery.add(RestrictionsFactoryUtil.in("usergroupId", userGroupId));
        try {
            final List<UsergroupDomainMaster> list = dao.getUsergroupDomainMasterList(ugDomainDynamicQuery);
            UsergroupDomainMaster usergroupDomainMaster;

            for (int i = 0; i < list.size(); i++) {

                usergroupDomainMaster = (UsergroupDomainMaster) list.get(i);
                final String domainIdStr = String.valueOf(usergroupDomainMaster.getDomainId());
                domainIds.add(domainIdStr);

            }

            if (domainIds.isEmpty()) {
            
            	 domainIds=DOMAINIDS1;
            }

        } catch (SystemException e) {
            LOGGER.error(e.getMessage());
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
    public Map<String, AppPermission> getBusinessTabPermission(final String userId,final String moduleName) throws PortalException{
    	Map<String, AppPermission> tabHm = null;
        final Collection<Object> userGroupId = getUserGroupId(Long.parseLong(userId));
        final String businessRoleIds = getBusinessRoleIds(userGroupId);
        final List tabPermissionList = dao.getBusinessroleModuleMasterTabList(businessRoleIds, moduleName);
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
    public Map<String, AppPermission> getBusinessFunctionPermission(final String userId,final String moduleName) throws PortalException {
        LOGGER.debug("User id in getBusinessFunctionPermission {}" , userId);
         Map<String, AppPermission> functionHm;

        final Collection<Object> userGroupId = getUserGroupId(Long.parseLong(userId));
        final String businessRoleIds = getBusinessRoleIds(userGroupId);
        final List tabPermissionList = dao.getBusinessroleModuleMasterFunctionList(businessRoleIds, moduleName);
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

    public Map<String, AppPermission> getBusinessFieldPermission(final String userId,final String moduleName) throws PortalException {
            Map<String, AppPermission> fieldHm;
        final Collection<Object> userGroupId = getUserGroupId(Long.parseLong(userId));
        final String businessRoleIds = getBusinessRoleIds(userGroupId);
        final List tabPermissionList = dao.getBusinessroleModuleMasterFieldList(businessRoleIds, moduleName);
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
    public List<String> getDomainPermission(final String userId) throws PortalException {
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
    public DynamicQuery addDomainRestrictions(final DynamicQuery companyDynamicQuery,final String userId) throws PortalException {
        List<String> domainIds;
        domainIds = getDomainPermission(userId);
        final Disjunction disJunction = RestrictionsFactoryUtil.disjunction();
        for (int i = 0; i < domainIds.size(); i++) {
            final String dId = CommonUIUtils.CHAR_PERCENT + "D" + domainIds.get(i) + "D" + CommonUIUtils.CHAR_PERCENT;
            disJunction.add(RestrictionsFactoryUtil.like("domainId", dId));
        }
        companyDynamicQuery.add(disJunction);
        return companyDynamicQuery;
    }

    /**
     * Converts the obtained list from DataBase to Map Object.
     * @param permissionList - List
     * @param type - type
     * @return HashMap<String, AppPermission>
     */
    public Map<String, AppPermission> listToAppPermissionMap(final List permissionList,final int type) {
        final Map<String, AppPermission> permissionHm = new HashMap<>();
        int counter = 0;
        final int listSize = permissionList.size();
        AppPermission appPermission;
      //its altered to avoid MissingBreakInSwitch
        //it will work on the functionality of switch statement.                   
        if(type == Constants.ZERO){
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
        if(type == Constants.ZERO || type == Constants.ONE){
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
        if(type == Constants.ZERO || type ==Constants.ONE || type ==Constants.TWO){
            for (; counter < listSize; counter++) {
                final Object[] obj = (Object[]) permissionList.get(counter);
                final String propertyName = String.valueOf(obj[0]).trim();
                final String addFlag = String.valueOf(obj[1]);
                final String editFlag = String.valueOf(obj[NumericConstants.TWO]);
                final String viewFlag = String.valueOf(obj[NumericConstants.THREE]);
                appPermission = new AppPermission();
                appPermission.setPropertyName(propertyName);
                appPermission.setAddFlag(null != addFlag  &&  "1".equals(addFlag) ? true : false);
                appPermission.setEditFlag(null != editFlag &&  "1".equals(editFlag) ? true : false);
                appPermission.setViewFlag(null != viewFlag &&  "1".equals(viewFlag) ? true : false);
                appPermission.setSearchFlag(appPermission.isAddFlag() || appPermission.isEditFlag() || appPermission.isViewFlag() ? true : false);
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
        DynamicQuery dynamicQuery = UserLocalServiceUtil.dynamicQuery();
        List<User> userList = UserLocalServiceUtil.dynamicQuery(dynamicQuery);
        for (User user : userList) {
            getUsermap().put(Integer.valueOf(String.valueOf(user.getUserId())), user.getFullName());
        }
        LOGGER.debug("End of getUserName method");
        return getUsermap();
    } 
         public Map<String, AppPermission> getFieldOrColumnPermission(final String userId, final String moduleName ,final boolean column) throws PortalException {
        LOGGER.debug("Enters getBusinessColumnPermission()");
         List tabPermissionList;
         Set addPermission;
         Set viewPermission;
         Set editPermission;
        final Collection<Object> userGroupId = getUserGroupId(Long.parseLong(userId));
        final String businessRoleIds = getBusinessRoleIds(userGroupId);
        if(column){
          tabPermissionList = getBuisnessColumn(businessRoleIds, moduleName);
          addPermission = getModePermission(businessRoleIds, moduleName,true,false,false,true);
          viewPermission = getModePermission(businessRoleIds, moduleName,false,true,false,true);
          editPermission = getModePermission(businessRoleIds, moduleName,false,false,true,true);
        }else{

         tabPermissionList = dao.getBusinessroleModuleMasterFieldList(businessRoleIds, moduleName);

         addPermission = getModePermission(businessRoleIds, moduleName,true,false,false,false);
         viewPermission = getModePermission(businessRoleIds, moduleName,false,true,false,false);
         editPermission = getModePermission(businessRoleIds, moduleName,false,false,true,false);
        }
        final Map<String, AppPermission> fieldHm = listOfFieldAppPermissionMap(tabPermissionList,addPermission,viewPermission, editPermission, FIELD_VALUE);
        LOGGER.debug("End of getBusinessFieldPermission() with fieldHm value size= {}" , fieldHm.size());
        return fieldHm;
    }
  public Map<String, AppPermission> listOfFieldAppPermissionMap(final List permissionList,final Set addpermission,final Set viewpermission ,final Set editpermission, final int type) {
        LOGGER.debug("Entering listToAppPermissionMap()");
        final Map<String, AppPermission> permissionHm = new HashMap<>();
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
        LOGGER.debug("End of listToAppPermissionMap() with permissionHm value size= {}" , permissionHm.size());
        return permissionHm;
    }
  private List getBuisnessColumn(String businessRoleIdsSecurity, String moduleNameSecurity) {
        List columnListSecurity;

        String sqlQuery;
        String[] arr = null;
        String mod;
        if (moduleNameSecurity.contains(String.valueOf(ConstantsUtils.COMMA))) {
            arr = moduleNameSecurity.split(String.valueOf(ConstantsUtils.COMMA));
            mod = arr[0];
        } else {
            mod = moduleNameSecurity;
        }

        sqlQuery = "select distinct spm.PROPERTY_NAME from USERGROUP_BUSINESSROLE as ubm, \n"
                + "		BUSINESSROLE_MODULE as bmm, \n"
                + "              MODULE_PROPERTIES as spm where \n"
                + "            ubm.BUSINESSROLE_MASTER_SID=bmm.BUSINESSROLE_MASTER_SID and bmm.SUBMODULE_PROPERTY_ID=spm.MODULE_PROPERTY_SID and \n"
                + "            (spm.PROPERTY_NAME is not null or spm.PROPERTY_NAME != '') \n"
                + "            and spm.CATEGORY_NAME IN ('List view Header')";
        if (businessRoleIdsSecurity.length() != 0) {
            sqlQuery += " AND ubm.BUSINESSROLE_MASTER_SID in ("
                    + businessRoleIdsSecurity + ")";
        }
        if (mod.length() != 0) {
            sqlQuery += " AND spm.MODULE_NAME in ('" + mod + "') ";
        }
        if (arr != null && !arr[1].equals(StringUtils.EMPTY) && arr[1].length() != 0) {
            sqlQuery += " AND spm.TAB_NAME like ('" + arr[1] + "') ";
        }
        columnListSecurity = HelperTableLocalServiceUtil.executeSelectQuery(sqlQuery);
        return columnListSecurity;

    }

    public List fetchColumnForSecurity(String moduleNameSecurity, String tabNameSecurity) {
        List columnListFetchColumn;
        String fetchColumnQuery = "SELECT DISPLAY_NAME, PROPERTY_NAME FROM MODULE_PROPERTIES WHERE MODULE_NAME = '" + moduleNameSecurity + "' "
                + " AND TAB_NAME = '" + tabNameSecurity + "' AND CATEGORY_NAME IN ('List view Header') ";
        columnListFetchColumn = HelperTableLocalServiceUtil.executeSelectQuery(fetchColumnQuery);
        return columnListFetchColumn;
    }

    private Set getModePermission(String businessRoleIdsMode, String moduleNameGetMode , boolean addMode ,boolean viewMode ,boolean editMode,boolean columnField) {
       Set fieldValue = new HashSet();
        List columnListValues;
        String sqlQueryModePermission;
        String[] str = null;
        String mod;
        if (moduleNameGetMode.contains(String.valueOf(ConstantsUtils.COMMA))) {
            str = moduleNameGetMode.split(String.valueOf(ConstantsUtils.COMMA));
            mod = str[0];
        } else {
            mod = moduleNameGetMode;
        }
         sqlQueryModePermission = "select distinct spm.PROPERTY_NAME from USERGROUP_BUSINESSROLE as ubm, \n"
                + "		BUSINESSROLE_MODULE as bmm, \n"
                + "        MODULE_PROPERTIES as spm where \n"
                + "        ubm.BUSINESSROLE_MASTER_SID=bmm.BUSINESSROLE_MASTER_SID and bmm.SUBMODULE_PROPERTY_ID=spm.MODULE_PROPERTY_SID and \n"
                + "        (spm.PROPERTY_NAME is not null or spm.PROPERTY_NAME != '') \n";
         if(columnField){
             sqlQueryModePermission+= "  and spm.CATEGORY_NAME IN ('List view Header')";
         }else{
                sqlQueryModePermission+= " and spm.CATEGORY_NAME NOT IN ('Button','Tab') ";
         }
        if (businessRoleIdsMode.length() != 0) {
            sqlQueryModePermission += " AND ubm.BUSINESSROLE_MASTER_SID in ("
                    + businessRoleIdsMode + ")";
        }

        if (mod.length() != 0) {
            sqlQueryModePermission += " AND spm.MODULE_NAME in ('" + mod + "') ";
        }
        if (str != null && !str[1].equals(StringUtils.EMPTY) && str[1].length() != 0) {
            sqlQueryModePermission += " AND spm.TAB_NAME like ('" + str[1] + "') ";
        }
      
        if(addMode){
             sqlQueryModePermission+="AND bmm.ADD_FLAG = '1' ";
        }
        else if(viewMode){
             sqlQueryModePermission+="AND bmm.VIEW_FLAG = '1' ";
        }
        else if(editMode){
             sqlQueryModePermission+="AND bmm.EDIT_FLAG = '1' ";
        }
        columnListValues = HelperTableLocalServiceUtil.executeSelectQuery(sqlQueryModePermission);
        fieldValue.addAll(columnListValues);
        return fieldValue;
    }

	public static Map<Integer,String> getUsermap() {
		return userMap;
	}
}

