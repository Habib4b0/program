package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link UsergroupDomainMasterLocalService}.
 *
 * @author
 * @see UsergroupDomainMasterLocalService
 * @generated
 */
public class UsergroupDomainMasterLocalServiceWrapper
    implements UsergroupDomainMasterLocalService,
        ServiceWrapper<UsergroupDomainMasterLocalService> {
    private UsergroupDomainMasterLocalService _usergroupDomainMasterLocalService;

    public UsergroupDomainMasterLocalServiceWrapper(
        UsergroupDomainMasterLocalService usergroupDomainMasterLocalService) {
        _usergroupDomainMasterLocalService = usergroupDomainMasterLocalService;
    }

    /**
    * Adds the usergroup domain master to the database. Also notifies the appropriate model listeners.
    *
    * @param usergroupDomainMaster the usergroup domain master
    * @return the usergroup domain master that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.UsergroupDomainMaster addUsergroupDomainMaster(
        com.stpl.app.model.UsergroupDomainMaster usergroupDomainMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _usergroupDomainMasterLocalService.addUsergroupDomainMaster(usergroupDomainMaster);
    }

    /**
    * Creates a new usergroup domain master with the primary key. Does not add the usergroup domain master to the database.
    *
    * @param usergroupDomainSid the primary key for the new usergroup domain master
    * @return the new usergroup domain master
    */
    @Override
    public com.stpl.app.model.UsergroupDomainMaster createUsergroupDomainMaster(
        int usergroupDomainSid) {
        return _usergroupDomainMasterLocalService.createUsergroupDomainMaster(usergroupDomainSid);
    }

    /**
    * Deletes the usergroup domain master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param usergroupDomainSid the primary key of the usergroup domain master
    * @return the usergroup domain master that was removed
    * @throws PortalException if a usergroup domain master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.UsergroupDomainMaster deleteUsergroupDomainMaster(
        int usergroupDomainSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _usergroupDomainMasterLocalService.deleteUsergroupDomainMaster(usergroupDomainSid);
    }

    /**
    * Deletes the usergroup domain master from the database. Also notifies the appropriate model listeners.
    *
    * @param usergroupDomainMaster the usergroup domain master
    * @return the usergroup domain master that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.UsergroupDomainMaster deleteUsergroupDomainMaster(
        com.stpl.app.model.UsergroupDomainMaster usergroupDomainMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _usergroupDomainMasterLocalService.deleteUsergroupDomainMaster(usergroupDomainMaster);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _usergroupDomainMasterLocalService.dynamicQuery();
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
        return _usergroupDomainMasterLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.UsergroupDomainMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _usergroupDomainMasterLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.UsergroupDomainMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _usergroupDomainMasterLocalService.dynamicQuery(dynamicQuery,
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
        return _usergroupDomainMasterLocalService.dynamicQueryCount(dynamicQuery);
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
        return _usergroupDomainMasterLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.UsergroupDomainMaster fetchUsergroupDomainMaster(
        int usergroupDomainSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _usergroupDomainMasterLocalService.fetchUsergroupDomainMaster(usergroupDomainSid);
    }

    /**
    * Returns the usergroup domain master with the primary key.
    *
    * @param usergroupDomainSid the primary key of the usergroup domain master
    * @return the usergroup domain master
    * @throws PortalException if a usergroup domain master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.UsergroupDomainMaster getUsergroupDomainMaster(
        int usergroupDomainSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _usergroupDomainMasterLocalService.getUsergroupDomainMaster(usergroupDomainSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _usergroupDomainMasterLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the usergroup domain masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.UsergroupDomainMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of usergroup domain masters
    * @param end the upper bound of the range of usergroup domain masters (not inclusive)
    * @return the range of usergroup domain masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.UsergroupDomainMaster> getUsergroupDomainMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _usergroupDomainMasterLocalService.getUsergroupDomainMasters(start,
            end);
    }

    /**
    * Returns the number of usergroup domain masters.
    *
    * @return the number of usergroup domain masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getUsergroupDomainMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _usergroupDomainMasterLocalService.getUsergroupDomainMastersCount();
    }

    /**
    * Updates the usergroup domain master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param usergroupDomainMaster the usergroup domain master
    * @return the usergroup domain master that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.UsergroupDomainMaster updateUsergroupDomainMaster(
        com.stpl.app.model.UsergroupDomainMaster usergroupDomainMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _usergroupDomainMasterLocalService.updateUsergroupDomainMaster(usergroupDomainMaster);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _usergroupDomainMasterLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _usergroupDomainMasterLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _usergroupDomainMasterLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public UsergroupDomainMasterLocalService getWrappedUsergroupDomainMasterLocalService() {
        return _usergroupDomainMasterLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedUsergroupDomainMasterLocalService(
        UsergroupDomainMasterLocalService usergroupDomainMasterLocalService) {
        _usergroupDomainMasterLocalService = usergroupDomainMasterLocalService;
    }

    @Override
    public UsergroupDomainMasterLocalService getWrappedService() {
        return _usergroupDomainMasterLocalService;
    }

    @Override
    public void setWrappedService(
        UsergroupDomainMasterLocalService usergroupDomainMasterLocalService) {
        _usergroupDomainMasterLocalService = usergroupDomainMasterLocalService;
    }
}
