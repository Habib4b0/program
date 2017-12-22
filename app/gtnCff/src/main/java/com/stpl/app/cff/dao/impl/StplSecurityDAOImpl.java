package com.stpl.app.cff.dao.impl;

import com.stpl.app.cff.dao.StplSecurityDAO;
import com.stpl.app.model.UsergroupBusinessrole;
import com.stpl.app.model.UsergroupDomainMaster;
import com.stpl.app.service.UsergroupBusinessroleLocalServiceUtil;
import com.stpl.app.service.UsergroupDomainMasterLocalServiceUtil;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.stpl.app.cff.dao.CommonServiceImpl;
import java.util.List;

/**
 * DAO operations for Security
 *
 * @author porchelvi.g
 */
public class StplSecurityDAOImpl implements StplSecurityDAO {

    private CommonServiceImpl commonService = CommonServiceImpl.getInstance();

    /**
     * Returns the user details corresponding to the userId
     *
     * @param userId
     * @return User modal object
     * @throws SystemException
     * @throws PortalException
     */
    public User getUserByUserId(final long userId) throws SystemException, PortalException {
        return UserLocalServiceUtil.getUser(userId);
    }

    @Override
    public List<UsergroupBusinessrole> getUsergroupBusinessroleMasterList(DynamicQuery query) throws PortalException, SystemException {
        return UsergroupBusinessroleLocalServiceUtil.dynamicQuery(query);
    }

    @Override
    public List<UsergroupDomainMaster> getUsergroupDomainMasterList(DynamicQuery query) throws SystemException {
        return UsergroupDomainMasterLocalServiceUtil.dynamicQuery(query);
    }

    @Override
    public List getBusinessroleModuleMasterFunctionList(String businessRoleIds, String moduleName) throws PortalException, SystemException {
        return commonService.getBusinessFunctionPermission(businessRoleIds, moduleName);
    }

    @Override
    public List getBusinessroleModuleMasterFieldList(String businessRoleIds, String moduleName) throws PortalException, SystemException {
        return commonService.getBusinessFieldPermission(businessRoleIds, moduleName);
    }

    @Override
    public List getBusinessroleModuleMasterTabList(String businessRoleIds, String moduleName) {
        return commonService.getBusinessTabPermission(businessRoleIds, moduleName);
    }
    /**
     * Returns the list of BusinessroleModuleMaster
     *
     * @param businessRoleIds
     * @param moduleName
     * @return list of BusinessroleModuleMaster
     * @throws SystemException
     * @throws PortalException
     */

}
