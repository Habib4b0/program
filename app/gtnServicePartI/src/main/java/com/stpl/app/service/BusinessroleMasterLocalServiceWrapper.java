package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link BusinessroleMasterLocalService}.
 *
 * @author
 * @see BusinessroleMasterLocalService
 * @generated
 */
public class BusinessroleMasterLocalServiceWrapper
    implements BusinessroleMasterLocalService,
        ServiceWrapper<BusinessroleMasterLocalService> {
    private BusinessroleMasterLocalService _businessroleMasterLocalService;

    public BusinessroleMasterLocalServiceWrapper(
        BusinessroleMasterLocalService businessroleMasterLocalService) {
        _businessroleMasterLocalService = businessroleMasterLocalService;
    }

    /**
    * Adds the businessrole master to the database. Also notifies the appropriate model listeners.
    *
    * @param businessroleMaster the businessrole master
    * @return the businessrole master that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.BusinessroleMaster addBusinessroleMaster(
        com.stpl.app.model.BusinessroleMaster businessroleMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _businessroleMasterLocalService.addBusinessroleMaster(businessroleMaster);
    }

    /**
    * Creates a new businessrole master with the primary key. Does not add the businessrole master to the database.
    *
    * @param businessroleMasterSid the primary key for the new businessrole master
    * @return the new businessrole master
    */
    @Override
    public com.stpl.app.model.BusinessroleMaster createBusinessroleMaster(
        int businessroleMasterSid) {
        return _businessroleMasterLocalService.createBusinessroleMaster(businessroleMasterSid);
    }

    /**
    * Deletes the businessrole master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param businessroleMasterSid the primary key of the businessrole master
    * @return the businessrole master that was removed
    * @throws PortalException if a businessrole master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.BusinessroleMaster deleteBusinessroleMaster(
        int businessroleMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _businessroleMasterLocalService.deleteBusinessroleMaster(businessroleMasterSid);
    }

    /**
    * Deletes the businessrole master from the database. Also notifies the appropriate model listeners.
    *
    * @param businessroleMaster the businessrole master
    * @return the businessrole master that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.BusinessroleMaster deleteBusinessroleMaster(
        com.stpl.app.model.BusinessroleMaster businessroleMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _businessroleMasterLocalService.deleteBusinessroleMaster(businessroleMaster);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _businessroleMasterLocalService.dynamicQuery();
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
        return _businessroleMasterLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BusinessroleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _businessroleMasterLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BusinessroleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _businessroleMasterLocalService.dynamicQuery(dynamicQuery,
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
        return _businessroleMasterLocalService.dynamicQueryCount(dynamicQuery);
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
        return _businessroleMasterLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.BusinessroleMaster fetchBusinessroleMaster(
        int businessroleMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _businessroleMasterLocalService.fetchBusinessroleMaster(businessroleMasterSid);
    }

    /**
    * Returns the businessrole master with the primary key.
    *
    * @param businessroleMasterSid the primary key of the businessrole master
    * @return the businessrole master
    * @throws PortalException if a businessrole master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.BusinessroleMaster getBusinessroleMaster(
        int businessroleMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _businessroleMasterLocalService.getBusinessroleMaster(businessroleMasterSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _businessroleMasterLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the businessrole masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BusinessroleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of businessrole masters
    * @param end the upper bound of the range of businessrole masters (not inclusive)
    * @return the range of businessrole masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.BusinessroleMaster> getBusinessroleMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _businessroleMasterLocalService.getBusinessroleMasters(start, end);
    }

    /**
    * Returns the number of businessrole masters.
    *
    * @return the number of businessrole masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getBusinessroleMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _businessroleMasterLocalService.getBusinessroleMastersCount();
    }

    /**
    * Updates the businessrole master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param businessroleMaster the businessrole master
    * @return the businessrole master that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.BusinessroleMaster updateBusinessroleMaster(
        com.stpl.app.model.BusinessroleMaster businessroleMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _businessroleMasterLocalService.updateBusinessroleMaster(businessroleMaster);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _businessroleMasterLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _businessroleMasterLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _businessroleMasterLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public BusinessroleMasterLocalService getWrappedBusinessroleMasterLocalService() {
        return _businessroleMasterLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedBusinessroleMasterLocalService(
        BusinessroleMasterLocalService businessroleMasterLocalService) {
        _businessroleMasterLocalService = businessroleMasterLocalService;
    }

    @Override
    public BusinessroleMasterLocalService getWrappedService() {
        return _businessroleMasterLocalService;
    }

    @Override
    public void setWrappedService(
        BusinessroleMasterLocalService businessroleMasterLocalService) {
        _businessroleMasterLocalService = businessroleMasterLocalService;
    }
}
