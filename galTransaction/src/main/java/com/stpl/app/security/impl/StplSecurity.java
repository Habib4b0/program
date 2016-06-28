package com.stpl.app.security.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

import com.stpl.app.security.permission.model.AppPermission;
import com.stpl.app.transactional.dao.impl.STPLSecurityDAOImpl;
import com.stpl.app.model.UsergroupBusinessrole;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.util.ConstantUtil;
import com.stpl.app.util.Constants;
import com.stpl.domain.transaction.security.STPLSecurityDAO;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.User;
import java.util.HashSet;
import java.util.Set;

/**
 * The Class StplSecurity.
 */
public class StplSecurity {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(StplSecurity.class);

	/** The dao. */
	private STPLSecurityDAO dao = new STPLSecurityDAOImpl();

	/**
	 * @return the dao
	 */
	public STPLSecurityDAO getDao() {
		return dao;
	}

	/**
	 * @param dao
	 *            the dao to set
	 */
	public void setDao(final STPLSecurityDAO dao) {
		this.dao = dao;
	}

	/**
	 * Gets the user group id.
	 *
	 * @param userId
	 *            the user id
	 * @return the user group id
	 */
	public Collection<Object> getUserGroupId(final long userId) throws SystemException, PortalException, Exception {
		final Collection<Object> userGroupId = new ArrayList<Object>();

		LOGGER.info("Entering getUserGroupId with userId value :::: " + userId);
		final User user = dao.getUser(userId);
		for (int i = 0; i < user.getUserGroups().size(); i++) {
			final Long userGroup = user.getUserGroups().get(i).getUserGroupId();
			userGroupId.add(Integer.parseInt(userGroup.toString()));

		}
		LOGGER.info("Ends getUserGroupId with userGroupId size :::: " + userGroupId.size());

		return userGroupId;
	}

	/**
	 * Gets the business role ids.
	 *
	 * @param userGroupId
	 *            the user group id
	 * @return the business role ids
	 */
	public String getBusinessRoleIds(final Collection<Object> userGroupId) throws SystemException {
		String businessRoleIds = StringUtils.EMPTY;

		LOGGER.info("Entering getBusinessRoleIds with userGroupId size :::: " + userGroupId.size());
             if (userGroupId.size() > 0) {
		final DynamicQuery ugBusRoleDynamicQuery = DynamicQueryFactoryUtil.forClass(UsergroupBusinessrole.class);
		ugBusRoleDynamicQuery.add(RestrictionsFactoryUtil.in(ConstantUtil.USERGROUPID, userGroupId));

		final List<UsergroupBusinessrole> list = dao.dynamicQuery(ugBusRoleDynamicQuery);
		UsergroupBusinessrole usergroupBusinessroleMaster;

		for (int i = 0; i < list.size(); i++) {
			usergroupBusinessroleMaster = (UsergroupBusinessrole) list.get(i);
			if (businessRoleIds.equals(StringUtils.EMPTY)) {
				businessRoleIds = String.valueOf(usergroupBusinessroleMaster.getBusinessroleMasterSid());
			} else {
				final StringBuffer tempStringBuffer = new StringBuffer();
				businessRoleIds = tempStringBuffer.append(businessRoleIds).append(",").append(usergroupBusinessroleMaster.getBusinessroleMasterSid()).toString();
				tempStringBuffer.delete(0, tempStringBuffer.length());
			}
		}
		if (businessRoleIds.equals(StringUtils.EMPTY)) {
			businessRoleIds = "-1";
		}
                }
		LOGGER.info("Ends getBusinessRoleIds with businessRoleIds value :::: " + businessRoleIds);

		return businessRoleIds;
	}

	/**
	 * Gets the business tab permission.
	 *
	 * @param userId
	 *            the user id
	 * @param moduleName
	 *            the module name
	 * @return the business tab permission
	 */
	public Map<String, AppPermission> getBusinessTabPermission(final String userId, final String moduleName) throws SystemException, PortalException, Exception {
		LOGGER.info("Entering getBusinessTabPermission with userId value :::: " + userId + " and moduleName value :::: " + moduleName);

		final Collection<Object> userGroupId = getUserGroupId(Long.parseLong(userId));
		final String businessRoleIds = getBusinessRoleIds(userGroupId);
		final List tabPermissionList = dao.getBusinessTabPermission(businessRoleIds, moduleName);
		final Map<String, AppPermission> businessTabPerm = listToAppPermissionMap(tabPermissionList, ConstantUtil.TAB_VALUE);
		LOGGER.info("Ends getBusinessTabPermission with businessTabPerm size :::: " + businessTabPerm.size());
		return businessTabPerm;

	}

	/**
	 * Gets the business function permission.
	 *
	 * @param userId
	 *            the user id
	 * @param moduleName
	 *            the module name
	 * @return the business function permission
	 */
	public Map<String, AppPermission> getBusinessFunctionPermission(final String userId, final String moduleName) throws SystemException, PortalException, Exception {
		LOGGER.info("Entering getBusinessFunctionPermission with userId value :::: " + userId + " and moduleName value :::: " + moduleName);

		final Collection<Object> userGroupId = getUserGroupId(Long.parseLong(userId));
		final String businessRoleIds = getBusinessRoleIds(userGroupId);
		final List tabPermissionList = dao.getBusinessFunctionPermission(businessRoleIds, moduleName);
		final Map<String, AppPermission> businessFunctionPerm = listToAppPermissionMap(tabPermissionList, ConstantUtil.FUNCTION_VALUE);
		LOGGER.info("Ends getBusinessFunctionPermission with businessFunctionPerm size :::: " + businessFunctionPerm.size());

		return businessFunctionPerm;
	}

	/**
	 * Gets the business field permission.
	 *
	 * @param userId
	 *            the user id
	 * @param moduleName
	 *            the module name
	 * @return the business field permission
	 */
	   public Map<String, AppPermission> getBusinessFieldPermission(final String userId, final String moduleName) throws SystemException, PortalException, Exception {
        LOGGER.info("Entering getBusinessFieldPermission with userId value :::: " + userId + " and moduleName value :::: " + moduleName);

        final Collection<Object> userGroupId = getUserGroupId(Long.parseLong(userId));
        final String businessRoleIds = getBusinessRoleIds(userGroupId);
            final List tabPermissionList = dao.getBusinessFieldPermission(businessRoleIds, moduleName);
            final Map<String, AppPermission> businessFieldPerm = listToAppPermissionMap(tabPermissionList, ConstantUtil.FIELD_VALUE);
            LOGGER.info("Ends getBusinessFieldPermission with businessFieldPerm size :::: " + businessFieldPerm.size());

            return businessFieldPerm;
	}

	/**
	 * List to app permission map.
	 *
	 * @param permissionList
	 *            the permission list
	 * @param type
	 *            the type
	 * @return the hash map< string, app permission>
	 */
	public Map<String, AppPermission> listToAppPermissionMap(final List permissionList, final int type) throws Exception {
		final Map<String, AppPermission> permissionHm = new HashMap<String, AppPermission>();

		LOGGER.info("Entering listToAppPermissionMap with permissionList size :::: " + permissionList.size() + " and type value :::: " + type);

		AppPermission appPermission;
		int counter = 0;
		if (type == ConstantUtil.ZERO_NUM) {
			for (; counter < permissionList.size(); counter++) {
				final Object[] obj = (Object[]) permissionList.get(counter);
				final String propertyName = String.valueOf(obj[0]);
				final String accessModule = String.valueOf(obj[1]);
				appPermission = new AppPermission();
				appPermission.setPropertyName(propertyName);
				appPermission.setTabFlag(accessModule != null && accessModule.equals(ConstantUtil.ONE) ? true : false);
				permissionHm.put(propertyName, appPermission);
			}
		}
		if (type == ConstantUtil.ZERO_NUM || type == ConstantUtil.ONE_NUM) {
			for (; counter < permissionList.size(); counter++) {
				final Object[] obj = (Object[]) permissionList.get(counter);
				final String propertyName = String.valueOf(obj[0]);
				final String accessModule = String.valueOf(obj[1]);
				appPermission = new AppPermission();
				appPermission.setPropertyName(propertyName);
				appPermission.setFunctionFlag(accessModule != null && accessModule.equals(ConstantUtil.ONE) ? true : false);
				permissionHm.put(propertyName, appPermission);
			}
		}
		if (type == ConstantUtil.ZERO_NUM || type == ConstantUtil.ONE_NUM || type == ConstantUtil.TWO_NUM) {
			for (; counter < permissionList.size(); counter++) {
				final Object[] obj = (Object[]) permissionList.get(counter);
				final String propertyName = String.valueOf(obj[0]);
				final String addFlag = String.valueOf(obj[1]);
				final String editFlag = String.valueOf(obj[2]);
				final String viewFlag = String.valueOf(obj[3]);
				appPermission = new AppPermission();
				appPermission.setPropertyName(propertyName);

				appPermission.setAddFlag(addFlag != null && addFlag.equals(ConstantUtil.ONE) ? true : false);
				appPermission.setEditFlag(editFlag != null && editFlag.equals(ConstantUtil.ONE) ? true : false);
				appPermission.setViewFlag(viewFlag != null && viewFlag.equals(ConstantUtil.ONE) ? true : false);
				appPermission.setSearchFlag(appPermission.isAddFlag() || appPermission.isEditFlag() || appPermission.isViewFlag() ? true : false);
				permissionHm.put(propertyName, appPermission);

			}
		}
		LOGGER.info("Ends listToAppPermissionMap with permissionHm size :::: " + permissionHm.size());

		return permissionHm;
	}
      public Map<String, AppPermission> getFieldOrColumnPermission(final String userId, final String moduleName ,final boolean column) throws PortalException, SystemException, Exception {
        LOGGER.info("Enters getBusinessColumnPermission()");
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
         tabPermissionList = dao.getBusinessFieldPermission(businessRoleIds, moduleName);
         addPermission = getModePermission(businessRoleIds, moduleName,true,false,false,false);
         viewPermission = getModePermission(businessRoleIds, moduleName,false,true,false,false);
         editPermission = getModePermission(businessRoleIds, moduleName,false,false,true,false);
        }
        final Map<String, AppPermission> fieldHm = listOfFieldAppPermissionMap(tabPermissionList,addPermission,viewPermission, editPermission, ConstantUtil.FIELD_VALUE);
        LOGGER.info("End of getBusinessFieldPermission() with fieldHm value size=" + fieldHm.size());
        return fieldHm;
    }
  public Map<String, AppPermission> listOfFieldAppPermissionMap(final List permissionList,final Set addpermission,final Set viewpermission ,final Set editpermission, final int type) throws Exception {
        LOGGER.info("Entering listToAppPermissionMap()");
        final Map<String, AppPermission> permissionHm = new HashMap<String, AppPermission>();
        int counter = 0;
        if (type == Constants.ZERO || type == Constants.ONE || type == Constants.TWO) {
            final int listSize = permissionList.size();
            for (; counter < listSize; counter++) {
                final Object[] obj = (Object[]) permissionList.get(counter);
                final String propertyName = String.valueOf(obj[0]);
                final String addFlag =  "";
                final String editFlag = "";
                final String viewFlag = "" ;
                final AppPermission appPermission = new AppPermission();
                appPermission.setPropertyName(propertyName);
                
                appPermission.setAddFlag(addFlag != null && addpermission.contains(propertyName) ? true : false);
             
                appPermission.setEditFlag(editFlag != null && editpermission.contains(propertyName) ? true : false);
                appPermission.setViewFlag(viewFlag != null && viewpermission.contains(propertyName) ? true : false);
                appPermission.setSearchFlag(appPermission.isAddFlag() || appPermission.isEditFlag() || appPermission.isViewFlag() ? true : false);
                permissionHm.put(propertyName, appPermission);

            }
        }
        LOGGER.info("End of listToAppPermissionMap() with permissionHm value size=" + permissionHm.size());
        return permissionHm;
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
        query=(("Demand".equalsIgnoreCase(moduleName)) || ("Returns".equalsIgnoreCase(moduleName))|| ("Inventory".equalsIgnoreCase(moduleName))
              || ("Item Hierarchy".equalsIgnoreCase(moduleName)) || ("IvldReturns".equalsIgnoreCase(moduleName)) || ("Customer Sales".equalsIgnoreCase(moduleName)) || ("IvldCustomerGtsForecast".equalsIgnoreCase(moduleName))|| ("IvldCustomerGtsActual".equalsIgnoreCase(moduleName))|| ("IvldInventoryWithdrawalSummary".equalsIgnoreCase(moduleName))  ||("Sales Master".equalsIgnoreCase(moduleName)))?query.replace("distinct", ""):query;
        columnList = HelperTableLocalServiceUtil.executeSelectQuery(query);
        field.addAll(columnList);
        return field;
    }
}
