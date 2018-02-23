package com.stpl.app.security;

import com.liferay.portal.kernel.dao.orm.Disjunction;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.stpl.app.common.AppDataUtils;
import com.stpl.app.gtnforecasting.dao.impl.StplSecurityDAOImpl;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.app.model.UsergroupBusinessrole;
import com.stpl.app.model.UsergroupDomainMaster;
import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.service.UsergroupBusinessroleLocalServiceUtil;
import com.stpl.app.service.UsergroupDomainMasterLocalServiceUtil;
import com.stpl.app.serviceUtils.Constants;
import com.stpl.domain.forecast.security.StplSecurityDAO;
import com.stpl.ifs.ui.util.NumericConstants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Auto-generated Javadoc
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
    public static final int FIELD_VALUE = 2;
    
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(StplSecurity.class);
   
   /** The dao. */
  private final StplSecurityDAO dto = new StplSecurityDAOImpl();
	 
 	/** The domain ids1. */
 	private static final List<String> DOMAINIDS1 = null;
        
        /** UserMap - Contains User System ID and User Name */
 	private static Map<Integer,String> userMap=new ConcurrentHashMap<>();   
        
    /**
     * Gets the dto.
     *
     * @return the dto
     */
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
    public Collection<Object> getUserGroupId(final long userId)  throws PortalException, SystemException {   
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
    public String getBusinessRoleIds(final Collection<Object> userGroupId) throws PortalException, SystemException{
    	String businessRoleIds=StringUtils.EMPTY;
        final DynamicQuery ugBusRoleDynamicQuery = UsergroupBusinessroleLocalServiceUtil.dynamicQuery();
        ugBusRoleDynamicQuery.add(RestrictionsFactoryUtil.in("usergroupId", userGroupId));
            final List<UsergroupBusinessrole> list = dto.getUsergroupBusinessroleMasterList(ugBusRoleDynamicQuery);
            UsergroupBusinessrole usergroupBusinessroleMaster;
            for (int i = 0; i < list.size(); i++) {
                usergroupBusinessroleMaster = (UsergroupBusinessrole) list.get(i);
                if (StringUtils.EMPTY.equals(businessRoleIds)) {
                    businessRoleIds=String.valueOf(usergroupBusinessroleMaster.getBusinessroleMasterSid());
                } else {
                    final StringBuffer tempStringBuffer = new StringBuffer();
                    businessRoleIds = tempStringBuffer.append(businessRoleIds).append(",").append(usergroupBusinessroleMaster.getBusinessroleMasterSid()).toString();
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
            final List<UsergroupDomainMaster> list = dto.getUsergroupDomainMasterList(ugDomainDynamicQuery);
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
    public Map<String, AppPermission> getBusinessTabPermission(final String userId,final String moduleName) throws PortalException, SystemException {
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
    public Map<String, AppPermission> getBusinessFunctionPermission(final String userId,final String moduleName) throws PortalException, SystemException {
        LOGGER.debug("{},{}",StringUtils.EMPTY,userId);
         Map<String, AppPermission> functionHm = new HashMap<>();

        if(StringUtils.isNotBlank(userId) && !"null".equals(userId)){
        final Collection<Object> userGroupId = getUserGroupId(Long.parseLong(userId));
        final String businessRoleIds = getBusinessRoleIds(userGroupId);
        final List tabPermissionList = dto.getBusinessroleModuleMasterFunctionList(businessRoleIds, moduleName);
       
        functionHm = listToAppPermissionMap(tabPermissionList, FUNCTION_VALUE);
        }
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

    public Map<String, AppPermission> getBusinessFieldPermission(final String userId,final String moduleName) throws PortalException, SystemException {
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
    public DynamicQuery addDomainRestrictions(final DynamicQuery companyDynamicQuery,final String userId) throws SystemException, PortalException {
        List<String> domainIds;
        domainIds = getDomainPermission(userId);
        final Disjunction disJunction = RestrictionsFactoryUtil.disjunction();
        for (int i = 0; i < domainIds.size(); i++) {
            final String dId = CommonUtils.CHAR_PERCENT + "D" + domainIds.get(i) + "D" + CommonUtils.CHAR_PERCENT;
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
                      
        if(type == Constants.ZERO){
            for (; counter < listSize; counter++) {
                final Object[] obj = (Object[]) permissionList.get(counter);
                final String propertyName = String.valueOf(obj[0]).trim();
                final String accessModule = String.valueOf(obj[1]);
                appPermission = new AppPermission();
                appPermission.setPropertyName(propertyName);                    
                appPermission.setTabFlag(null != accessModule && Constant.STRING_ONE.equals(accessModule) ? true : false);
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
                appPermission.setFunctionFlag(null != accessModule && Constant.STRING_ONE.equals(accessModule) ? true : false);
                permissionHm.put(propertyName, appPermission);
            }        	
        }
        if(type == Constants.ZERO || type ==Constants.ONE || type ==Constants.TWO){
            for (; counter < listSize; counter++) {
                final Object[] obj = (Object[]) permissionList.get(counter);
                final String propertyName = String.valueOf(obj[0]).trim();
                final String addFlag = String.valueOf(obj[1]);
                final String editFlag = String.valueOf(obj[NumericConstants.TWO]);
                final String viewFlag = String.valueOf(obj[3]);
                appPermission = new AppPermission();
                appPermission.setPropertyName(propertyName);
                appPermission.setAddFlag(null != addFlag  &&  Constant.STRING_ONE.equals(addFlag) ? true : false);
                appPermission.setEditFlag(null != editFlag &&  Constant.STRING_ONE.equals(editFlag) ? true : false);
                appPermission.setViewFlag(null != viewFlag &&  Constant.STRING_ONE.equals(viewFlag) ? true : false);
                appPermission.setSearchFlag(appPermission.isAddFlag() || appPermission.isEditFlag() || appPermission.isViewFlag() ? true : false);
                permissionHm.put(propertyName, appPermission);
                                }        	
        }
         if(type == 4){
            for (; counter < listSize; counter++) {
                final Object[] obj = (Object[]) permissionList.get(counter);
                final String propertyName = String.valueOf(obj[0]).trim();
                final String accessModule = String.valueOf(obj[1]);
                final String headerName = String.valueOf(obj[2]);
                appPermission = new AppPermission();
                appPermission.setPropertyName(propertyName);                    
                appPermission.setModuleName(headerName);                    
                appPermission.setFunctionFlag(null != accessModule && Constant.STRING_ONE.equals(accessModule) ? true : false);
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
            userMap.put(Long.valueOf(user.getUserId()).intValue(), user.getFullName());
        }
        LOGGER.debug("End of getUserName method");
        return userMap;
    } 
        
     public Map<String, AppPermission> getBusinessFunctionPermissionForNm(final String businessRoleIds,final String moduleName) throws PortalException, SystemException {
         Map<String, AppPermission> functionHm;
        final List tabPermissionList = dto.getBusinessroleModuleMasterFunctionList(businessRoleIds, moduleName);
        functionHm = listToAppPermissionMap(tabPermissionList, FUNCTION_VALUE);
        return functionHm;
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
    public Map<String, AppPermission> getBusinessTabPermissionForNm(final String businessRoleIds,final String moduleName)  {
        Map<String, AppPermission> tabHm = null;
        final List tabPermissionList = dto.getBusinessroleModuleMasterTabList(businessRoleIds, moduleName);
        tabHm = listToAppPermissionMap(tabPermissionList, TAB_VALUE);
        return tabHm;
    }
    
    public Map<String, AppPermission> getBusinessFunctionPermission(final String userId, final String moduleName, final String subModuleName, final String tabName) throws PortalException, SystemException {
        Map<String, AppPermission> functionHm;

        final Collection<Object> userGroupId = getUserGroupId(Long.parseLong(userId));
        final String businessRoleIds = getBusinessRoleIds(userGroupId);
        List<Object[]> tabPermissionList = AppDataUtils.getAppData(getInput(businessRoleIds, moduleName, subModuleName, tabName), "buttonSecurity", null);
        functionHm = listToAppPermissionMap(tabPermissionList, 4);
        return functionHm;
    }

    private List getInput(final String businessRoleId, final String moduleName, final String subModuleName, final String tabName) {
        List input = new ArrayList();
        input.add("List View");
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
