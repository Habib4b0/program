package com.stpl.app.parttwo.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link IvldCustomerGtsActualLocalService}.
 *
 * @author
 * @see IvldCustomerGtsActualLocalService
 * @generated
 */
public class IvldCustomerGtsActualLocalServiceWrapper
    implements IvldCustomerGtsActualLocalService,
        ServiceWrapper<IvldCustomerGtsActualLocalService> {
    private IvldCustomerGtsActualLocalService _ivldCustomerGtsActualLocalService;

    public IvldCustomerGtsActualLocalServiceWrapper(
        IvldCustomerGtsActualLocalService ivldCustomerGtsActualLocalService) {
        _ivldCustomerGtsActualLocalService = ivldCustomerGtsActualLocalService;
    }

    /**
    * Adds the ivld customer gts actual to the database. Also notifies the appropriate model listeners.
    *
    * @param ivldCustomerGtsActual the ivld customer gts actual
    * @return the ivld customer gts actual that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.IvldCustomerGtsActual addIvldCustomerGtsActual(
        com.stpl.app.parttwo.model.IvldCustomerGtsActual ivldCustomerGtsActual)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldCustomerGtsActualLocalService.addIvldCustomerGtsActual(ivldCustomerGtsActual);
    }

    /**
    * Creates a new ivld customer gts actual with the primary key. Does not add the ivld customer gts actual to the database.
    *
    * @param ivldCustomerGtsActualSid the primary key for the new ivld customer gts actual
    * @return the new ivld customer gts actual
    */
    @Override
    public com.stpl.app.parttwo.model.IvldCustomerGtsActual createIvldCustomerGtsActual(
        int ivldCustomerGtsActualSid) {
        return _ivldCustomerGtsActualLocalService.createIvldCustomerGtsActual(ivldCustomerGtsActualSid);
    }

    /**
    * Deletes the ivld customer gts actual with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldCustomerGtsActualSid the primary key of the ivld customer gts actual
    * @return the ivld customer gts actual that was removed
    * @throws PortalException if a ivld customer gts actual with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.IvldCustomerGtsActual deleteIvldCustomerGtsActual(
        int ivldCustomerGtsActualSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldCustomerGtsActualLocalService.deleteIvldCustomerGtsActual(ivldCustomerGtsActualSid);
    }

    /**
    * Deletes the ivld customer gts actual from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldCustomerGtsActual the ivld customer gts actual
    * @return the ivld customer gts actual that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.IvldCustomerGtsActual deleteIvldCustomerGtsActual(
        com.stpl.app.parttwo.model.IvldCustomerGtsActual ivldCustomerGtsActual)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldCustomerGtsActualLocalService.deleteIvldCustomerGtsActual(ivldCustomerGtsActual);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _ivldCustomerGtsActualLocalService.dynamicQuery();
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
        return _ivldCustomerGtsActualLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCustomerGtsActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ivldCustomerGtsActualLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCustomerGtsActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ivldCustomerGtsActualLocalService.dynamicQuery(dynamicQuery,
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
        return _ivldCustomerGtsActualLocalService.dynamicQueryCount(dynamicQuery);
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
        return _ivldCustomerGtsActualLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.parttwo.model.IvldCustomerGtsActual fetchIvldCustomerGtsActual(
        int ivldCustomerGtsActualSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldCustomerGtsActualLocalService.fetchIvldCustomerGtsActual(ivldCustomerGtsActualSid);
    }

    /**
    * Returns the ivld customer gts actual with the primary key.
    *
    * @param ivldCustomerGtsActualSid the primary key of the ivld customer gts actual
    * @return the ivld customer gts actual
    * @throws PortalException if a ivld customer gts actual with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.IvldCustomerGtsActual getIvldCustomerGtsActual(
        int ivldCustomerGtsActualSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldCustomerGtsActualLocalService.getIvldCustomerGtsActual(ivldCustomerGtsActualSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldCustomerGtsActualLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the ivld customer gts actuals.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.IvldCustomerGtsActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld customer gts actuals
    * @param end the upper bound of the range of ivld customer gts actuals (not inclusive)
    * @return the range of ivld customer gts actuals
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.parttwo.model.IvldCustomerGtsActual> getIvldCustomerGtsActuals(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldCustomerGtsActualLocalService.getIvldCustomerGtsActuals(start,
            end);
    }

    /**
    * Returns the number of ivld customer gts actuals.
    *
    * @return the number of ivld customer gts actuals
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getIvldCustomerGtsActualsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldCustomerGtsActualLocalService.getIvldCustomerGtsActualsCount();
    }

    /**
    * Updates the ivld customer gts actual in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param ivldCustomerGtsActual the ivld customer gts actual
    * @return the ivld customer gts actual that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.IvldCustomerGtsActual updateIvldCustomerGtsActual(
        com.stpl.app.parttwo.model.IvldCustomerGtsActual ivldCustomerGtsActual)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldCustomerGtsActualLocalService.updateIvldCustomerGtsActual(ivldCustomerGtsActual);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _ivldCustomerGtsActualLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _ivldCustomerGtsActualLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _ivldCustomerGtsActualLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public IvldCustomerGtsActualLocalService getWrappedIvldCustomerGtsActualLocalService() {
        return _ivldCustomerGtsActualLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedIvldCustomerGtsActualLocalService(
        IvldCustomerGtsActualLocalService ivldCustomerGtsActualLocalService) {
        _ivldCustomerGtsActualLocalService = ivldCustomerGtsActualLocalService;
    }

    @Override
    public IvldCustomerGtsActualLocalService getWrappedService() {
        return _ivldCustomerGtsActualLocalService;
    }

    @Override
    public void setWrappedService(
        IvldCustomerGtsActualLocalService ivldCustomerGtsActualLocalService) {
        _ivldCustomerGtsActualLocalService = ivldCustomerGtsActualLocalService;
    }
}
