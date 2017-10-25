package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link IvldDemandActualLocalService}.
 *
 * @author
 * @see IvldDemandActualLocalService
 * @generated
 */
public class IvldDemandActualLocalServiceWrapper
    implements IvldDemandActualLocalService,
        ServiceWrapper<IvldDemandActualLocalService> {
    private IvldDemandActualLocalService _ivldDemandActualLocalService;

    public IvldDemandActualLocalServiceWrapper(
        IvldDemandActualLocalService ivldDemandActualLocalService) {
        _ivldDemandActualLocalService = ivldDemandActualLocalService;
    }

    /**
    * Adds the ivld demand actual to the database. Also notifies the appropriate model listeners.
    *
    * @param ivldDemandActual the ivld demand actual
    * @return the ivld demand actual that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldDemandActual addIvldDemandActual(
        com.stpl.app.model.IvldDemandActual ivldDemandActual)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldDemandActualLocalService.addIvldDemandActual(ivldDemandActual);
    }

    /**
    * Creates a new ivld demand actual with the primary key. Does not add the ivld demand actual to the database.
    *
    * @param ivldDemandActualSid the primary key for the new ivld demand actual
    * @return the new ivld demand actual
    */
    @Override
    public com.stpl.app.model.IvldDemandActual createIvldDemandActual(
        int ivldDemandActualSid) {
        return _ivldDemandActualLocalService.createIvldDemandActual(ivldDemandActualSid);
    }

    /**
    * Deletes the ivld demand actual with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldDemandActualSid the primary key of the ivld demand actual
    * @return the ivld demand actual that was removed
    * @throws PortalException if a ivld demand actual with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldDemandActual deleteIvldDemandActual(
        int ivldDemandActualSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldDemandActualLocalService.deleteIvldDemandActual(ivldDemandActualSid);
    }

    /**
    * Deletes the ivld demand actual from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldDemandActual the ivld demand actual
    * @return the ivld demand actual that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldDemandActual deleteIvldDemandActual(
        com.stpl.app.model.IvldDemandActual ivldDemandActual)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldDemandActualLocalService.deleteIvldDemandActual(ivldDemandActual);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _ivldDemandActualLocalService.dynamicQuery();
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
        return _ivldDemandActualLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldDemandActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ivldDemandActualLocalService.dynamicQuery(dynamicQuery, start,
            end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldDemandActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ivldDemandActualLocalService.dynamicQuery(dynamicQuery, start,
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
        return _ivldDemandActualLocalService.dynamicQueryCount(dynamicQuery);
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
        return _ivldDemandActualLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.IvldDemandActual fetchIvldDemandActual(
        int ivldDemandActualSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldDemandActualLocalService.fetchIvldDemandActual(ivldDemandActualSid);
    }

    /**
    * Returns the ivld demand actual with the primary key.
    *
    * @param ivldDemandActualSid the primary key of the ivld demand actual
    * @return the ivld demand actual
    * @throws PortalException if a ivld demand actual with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldDemandActual getIvldDemandActual(
        int ivldDemandActualSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldDemandActualLocalService.getIvldDemandActual(ivldDemandActualSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldDemandActualLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the ivld demand actuals.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldDemandActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld demand actuals
    * @param end the upper bound of the range of ivld demand actuals (not inclusive)
    * @return the range of ivld demand actuals
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.IvldDemandActual> getIvldDemandActuals(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldDemandActualLocalService.getIvldDemandActuals(start, end);
    }

    /**
    * Returns the number of ivld demand actuals.
    *
    * @return the number of ivld demand actuals
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getIvldDemandActualsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldDemandActualLocalService.getIvldDemandActualsCount();
    }

    /**
    * Updates the ivld demand actual in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param ivldDemandActual the ivld demand actual
    * @return the ivld demand actual that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldDemandActual updateIvldDemandActual(
        com.stpl.app.model.IvldDemandActual ivldDemandActual)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldDemandActualLocalService.updateIvldDemandActual(ivldDemandActual);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _ivldDemandActualLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _ivldDemandActualLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _ivldDemandActualLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public IvldDemandActualLocalService getWrappedIvldDemandActualLocalService() {
        return _ivldDemandActualLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedIvldDemandActualLocalService(
        IvldDemandActualLocalService ivldDemandActualLocalService) {
        _ivldDemandActualLocalService = ivldDemandActualLocalService;
    }

    @Override
    public IvldDemandActualLocalService getWrappedService() {
        return _ivldDemandActualLocalService;
    }

    @Override
    public void setWrappedService(
        IvldDemandActualLocalService ivldDemandActualLocalService) {
        _ivldDemandActualLocalService = ivldDemandActualLocalService;
    }
}
