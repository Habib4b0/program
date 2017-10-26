package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link UsergroupBusinessroleLocalService}.
 *
 * @author
 * @see UsergroupBusinessroleLocalService
 * @generated
 */
public class UsergroupBusinessroleLocalServiceWrapper
    implements UsergroupBusinessroleLocalService,
        ServiceWrapper<UsergroupBusinessroleLocalService> {
    private UsergroupBusinessroleLocalService _usergroupBusinessroleLocalService;

    public UsergroupBusinessroleLocalServiceWrapper(
        UsergroupBusinessroleLocalService usergroupBusinessroleLocalService) {
        _usergroupBusinessroleLocalService = usergroupBusinessroleLocalService;
    }

    /**
    * Adds the usergroup businessrole to the database. Also notifies the appropriate model listeners.
    *
    * @param usergroupBusinessrole the usergroup businessrole
    * @return the usergroup businessrole that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.UsergroupBusinessrole addUsergroupBusinessrole(
        com.stpl.app.model.UsergroupBusinessrole usergroupBusinessrole)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _usergroupBusinessroleLocalService.addUsergroupBusinessrole(usergroupBusinessrole);
    }

    /**
    * Creates a new usergroup businessrole with the primary key. Does not add the usergroup businessrole to the database.
    *
    * @param usergroupBusinessroleSid the primary key for the new usergroup businessrole
    * @return the new usergroup businessrole
    */
    @Override
    public com.stpl.app.model.UsergroupBusinessrole createUsergroupBusinessrole(
        int usergroupBusinessroleSid) {
        return _usergroupBusinessroleLocalService.createUsergroupBusinessrole(usergroupBusinessroleSid);
    }

    /**
    * Deletes the usergroup businessrole with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param usergroupBusinessroleSid the primary key of the usergroup businessrole
    * @return the usergroup businessrole that was removed
    * @throws PortalException if a usergroup businessrole with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.UsergroupBusinessrole deleteUsergroupBusinessrole(
        int usergroupBusinessroleSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _usergroupBusinessroleLocalService.deleteUsergroupBusinessrole(usergroupBusinessroleSid);
    }

    /**
    * Deletes the usergroup businessrole from the database. Also notifies the appropriate model listeners.
    *
    * @param usergroupBusinessrole the usergroup businessrole
    * @return the usergroup businessrole that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.UsergroupBusinessrole deleteUsergroupBusinessrole(
        com.stpl.app.model.UsergroupBusinessrole usergroupBusinessrole)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _usergroupBusinessroleLocalService.deleteUsergroupBusinessrole(usergroupBusinessrole);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _usergroupBusinessroleLocalService.dynamicQuery();
    }

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _usergroupBusinessroleLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.UsergroupBusinessroleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @return the range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException {
        return _usergroupBusinessroleLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.UsergroupBusinessroleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @Override
    @SuppressWarnings("rawtypes")
    public java.util.List dynamicQuery(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _usergroupBusinessroleLocalService.dynamicQuery(dynamicQuery,
            start, end, orderByComparator);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    @Override
    public long dynamicQueryCount(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _usergroupBusinessroleLocalService.dynamicQueryCount(dynamicQuery);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @param projection the projection to apply to the query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    @Override
    public long dynamicQueryCount(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
        com.stpl.portal.kernel.dao.orm.Projection projection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _usergroupBusinessroleLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.UsergroupBusinessrole fetchUsergroupBusinessrole(
        int usergroupBusinessroleSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _usergroupBusinessroleLocalService.fetchUsergroupBusinessrole(usergroupBusinessroleSid);
    }

    /**
    * Returns the usergroup businessrole with the primary key.
    *
    * @param usergroupBusinessroleSid the primary key of the usergroup businessrole
    * @return the usergroup businessrole
    * @throws PortalException if a usergroup businessrole with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.UsergroupBusinessrole getUsergroupBusinessrole(
        int usergroupBusinessroleSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _usergroupBusinessroleLocalService.getUsergroupBusinessrole(usergroupBusinessroleSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _usergroupBusinessroleLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the usergroup businessroles.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.UsergroupBusinessroleModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of usergroup businessroles
    * @param end the upper bound of the range of usergroup businessroles (not inclusive)
    * @return the range of usergroup businessroles
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.UsergroupBusinessrole> getUsergroupBusinessroles(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _usergroupBusinessroleLocalService.getUsergroupBusinessroles(start,
            end);
    }

    /**
    * Returns the number of usergroup businessroles.
    *
    * @return the number of usergroup businessroles
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getUsergroupBusinessrolesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _usergroupBusinessroleLocalService.getUsergroupBusinessrolesCount();
    }

    /**
    * Updates the usergroup businessrole in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param usergroupBusinessrole the usergroup businessrole
    * @return the usergroup businessrole that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.UsergroupBusinessrole updateUsergroupBusinessrole(
        com.stpl.app.model.UsergroupBusinessrole usergroupBusinessrole)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _usergroupBusinessroleLocalService.updateUsergroupBusinessrole(usergroupBusinessrole);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _usergroupBusinessroleLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _usergroupBusinessroleLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _usergroupBusinessroleLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public UsergroupBusinessroleLocalService getWrappedUsergroupBusinessroleLocalService() {
        return _usergroupBusinessroleLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedUsergroupBusinessroleLocalService(
        UsergroupBusinessroleLocalService usergroupBusinessroleLocalService) {
        _usergroupBusinessroleLocalService = usergroupBusinessroleLocalService;
    }

    @Override
    public UsergroupBusinessroleLocalService getWrappedService() {
        return _usergroupBusinessroleLocalService;
    }

    @Override
    public void setWrappedService(
        UsergroupBusinessroleLocalService usergroupBusinessroleLocalService) {
        _usergroupBusinessroleLocalService = usergroupBusinessroleLocalService;
    }
}
