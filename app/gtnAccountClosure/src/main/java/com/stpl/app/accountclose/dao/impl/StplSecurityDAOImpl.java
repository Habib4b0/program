package com.stpl.app.accountclose.dao.impl;

import com.stpl.app.accountclose.dao.StplSecurityDAO;
import com.stpl.app.model.UsergroupBusinessrole;
import com.stpl.app.model.UsergroupDomainMaster;
import com.stpl.app.service.BusinessroleModuleLocalServiceUtil;
import com.stpl.app.service.UsergroupBusinessroleLocalServiceUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.model.User;
import com.stpl.portal.service.UserLocalServiceUtil;
import java.util.List;

/**
 * DAO operations for Security
 *
 * @author porchelvi.g
 */
public class StplSecurityDAOImpl implements StplSecurityDAO {

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
    public List getBusinessroleModuleMasterFunctionList(final String businessRoleIds, final String moduleName) throws PortalException, SystemException {
        return BusinessroleModuleLocalServiceUtil.getBusinessFunctionPermission(businessRoleIds, moduleName);
    }

    @Override
    public List<UsergroupBusinessrole> getUsergroupBusinessroleMasterList(DynamicQuery query) throws PortalException, SystemException {
        return UsergroupBusinessroleLocalServiceUtil.dynamicQuery(query);
    }

    @Override
    public List<UsergroupDomainMaster> getUsergroupDomainMasterList(DynamicQuery query) throws SystemException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List getBusinessroleModuleMasterFieldList(String businessRoleIds, String moduleName) throws PortalException, SystemException, RuntimeException {
        return BusinessroleModuleLocalServiceUtil.getBusinessFieldPermission(businessRoleIds, moduleName);
    }

    @Override
    public List getBusinessroleModuleMasterTabList(String businessRoleIds, String moduleName) {
        return BusinessroleModuleLocalServiceUtil.getBusinessTabPermission(businessRoleIds, moduleName);
    }
}
