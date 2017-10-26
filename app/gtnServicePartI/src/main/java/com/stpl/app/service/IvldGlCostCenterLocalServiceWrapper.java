package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link IvldGlCostCenterLocalService}.
 *
 * @author
 * @see IvldGlCostCenterLocalService
 * @generated
 */
public class IvldGlCostCenterLocalServiceWrapper
    implements IvldGlCostCenterLocalService,
        ServiceWrapper<IvldGlCostCenterLocalService> {
    private IvldGlCostCenterLocalService _ivldGlCostCenterLocalService;

    public IvldGlCostCenterLocalServiceWrapper(
        IvldGlCostCenterLocalService ivldGlCostCenterLocalService) {
        _ivldGlCostCenterLocalService = ivldGlCostCenterLocalService;
    }

    /**
    * Adds the ivld gl cost center to the database. Also notifies the appropriate model listeners.
    *
    * @param ivldGlCostCenter the ivld gl cost center
    * @return the ivld gl cost center that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldGlCostCenter addIvldGlCostCenter(
        com.stpl.app.model.IvldGlCostCenter ivldGlCostCenter)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldGlCostCenterLocalService.addIvldGlCostCenter(ivldGlCostCenter);
    }

    /**
    * Creates a new ivld gl cost center with the primary key. Does not add the ivld gl cost center to the database.
    *
    * @param ivldGlCostCenterSid the primary key for the new ivld gl cost center
    * @return the new ivld gl cost center
    */
    @Override
    public com.stpl.app.model.IvldGlCostCenter createIvldGlCostCenter(
        int ivldGlCostCenterSid) {
        return _ivldGlCostCenterLocalService.createIvldGlCostCenter(ivldGlCostCenterSid);
    }

    /**
    * Deletes the ivld gl cost center with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldGlCostCenterSid the primary key of the ivld gl cost center
    * @return the ivld gl cost center that was removed
    * @throws PortalException if a ivld gl cost center with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldGlCostCenter deleteIvldGlCostCenter(
        int ivldGlCostCenterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldGlCostCenterLocalService.deleteIvldGlCostCenter(ivldGlCostCenterSid);
    }

    /**
    * Deletes the ivld gl cost center from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldGlCostCenter the ivld gl cost center
    * @return the ivld gl cost center that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldGlCostCenter deleteIvldGlCostCenter(
        com.stpl.app.model.IvldGlCostCenter ivldGlCostCenter)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldGlCostCenterLocalService.deleteIvldGlCostCenter(ivldGlCostCenter);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _ivldGlCostCenterLocalService.dynamicQuery();
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
        return _ivldGlCostCenterLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldGlCostCenterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ivldGlCostCenterLocalService.dynamicQuery(dynamicQuery, start,
            end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldGlCostCenterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ivldGlCostCenterLocalService.dynamicQuery(dynamicQuery, start,
            end, orderByComparator);
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
        return _ivldGlCostCenterLocalService.dynamicQueryCount(dynamicQuery);
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
        return _ivldGlCostCenterLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.IvldGlCostCenter fetchIvldGlCostCenter(
        int ivldGlCostCenterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldGlCostCenterLocalService.fetchIvldGlCostCenter(ivldGlCostCenterSid);
    }

    /**
    * Returns the ivld gl cost center with the primary key.
    *
    * @param ivldGlCostCenterSid the primary key of the ivld gl cost center
    * @return the ivld gl cost center
    * @throws PortalException if a ivld gl cost center with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldGlCostCenter getIvldGlCostCenter(
        int ivldGlCostCenterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldGlCostCenterLocalService.getIvldGlCostCenter(ivldGlCostCenterSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldGlCostCenterLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the ivld gl cost centers.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldGlCostCenterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld gl cost centers
    * @param end the upper bound of the range of ivld gl cost centers (not inclusive)
    * @return the range of ivld gl cost centers
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.IvldGlCostCenter> getIvldGlCostCenters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldGlCostCenterLocalService.getIvldGlCostCenters(start, end);
    }

    /**
    * Returns the number of ivld gl cost centers.
    *
    * @return the number of ivld gl cost centers
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getIvldGlCostCentersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldGlCostCenterLocalService.getIvldGlCostCentersCount();
    }

    /**
    * Updates the ivld gl cost center in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param ivldGlCostCenter the ivld gl cost center
    * @return the ivld gl cost center that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldGlCostCenter updateIvldGlCostCenter(
        com.stpl.app.model.IvldGlCostCenter ivldGlCostCenter)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldGlCostCenterLocalService.updateIvldGlCostCenter(ivldGlCostCenter);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _ivldGlCostCenterLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _ivldGlCostCenterLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _ivldGlCostCenterLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public IvldGlCostCenterLocalService getWrappedIvldGlCostCenterLocalService() {
        return _ivldGlCostCenterLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedIvldGlCostCenterLocalService(
        IvldGlCostCenterLocalService ivldGlCostCenterLocalService) {
        _ivldGlCostCenterLocalService = ivldGlCostCenterLocalService;
    }

    @Override
    public IvldGlCostCenterLocalService getWrappedService() {
        return _ivldGlCostCenterLocalService;
    }

    @Override
    public void setWrappedService(
        IvldGlCostCenterLocalService ivldGlCostCenterLocalService) {
        _ivldGlCostCenterLocalService = ivldGlCostCenterLocalService;
    }
}
