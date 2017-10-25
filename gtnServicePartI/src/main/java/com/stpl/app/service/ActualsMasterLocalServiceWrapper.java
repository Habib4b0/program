package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ActualsMasterLocalService}.
 *
 * @author
 * @see ActualsMasterLocalService
 * @generated
 */
public class ActualsMasterLocalServiceWrapper
    implements ActualsMasterLocalService,
        ServiceWrapper<ActualsMasterLocalService> {
    private ActualsMasterLocalService _actualsMasterLocalService;

    public ActualsMasterLocalServiceWrapper(
        ActualsMasterLocalService actualsMasterLocalService) {
        _actualsMasterLocalService = actualsMasterLocalService;
    }

    /**
    * Adds the actuals master to the database. Also notifies the appropriate model listeners.
    *
    * @param actualsMaster the actuals master
    * @return the actuals master that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ActualsMaster addActualsMaster(
        com.stpl.app.model.ActualsMaster actualsMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _actualsMasterLocalService.addActualsMaster(actualsMaster);
    }

    /**
    * Creates a new actuals master with the primary key. Does not add the actuals master to the database.
    *
    * @param actualsMasterSid the primary key for the new actuals master
    * @return the new actuals master
    */
    @Override
    public com.stpl.app.model.ActualsMaster createActualsMaster(
        int actualsMasterSid) {
        return _actualsMasterLocalService.createActualsMaster(actualsMasterSid);
    }

    /**
    * Deletes the actuals master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param actualsMasterSid the primary key of the actuals master
    * @return the actuals master that was removed
    * @throws PortalException if a actuals master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ActualsMaster deleteActualsMaster(
        int actualsMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _actualsMasterLocalService.deleteActualsMaster(actualsMasterSid);
    }

    /**
    * Deletes the actuals master from the database. Also notifies the appropriate model listeners.
    *
    * @param actualsMaster the actuals master
    * @return the actuals master that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ActualsMaster deleteActualsMaster(
        com.stpl.app.model.ActualsMaster actualsMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _actualsMasterLocalService.deleteActualsMaster(actualsMaster);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _actualsMasterLocalService.dynamicQuery();
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
        return _actualsMasterLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _actualsMasterLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _actualsMasterLocalService.dynamicQuery(dynamicQuery, start,
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
        return _actualsMasterLocalService.dynamicQueryCount(dynamicQuery);
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
        return _actualsMasterLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.ActualsMaster fetchActualsMaster(
        int actualsMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _actualsMasterLocalService.fetchActualsMaster(actualsMasterSid);
    }

    /**
    * Returns the actuals master with the primary key.
    *
    * @param actualsMasterSid the primary key of the actuals master
    * @return the actuals master
    * @throws PortalException if a actuals master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ActualsMaster getActualsMaster(
        int actualsMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _actualsMasterLocalService.getActualsMaster(actualsMasterSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _actualsMasterLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the actuals masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ActualsMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of actuals masters
    * @param end the upper bound of the range of actuals masters (not inclusive)
    * @return the range of actuals masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.ActualsMaster> getActualsMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _actualsMasterLocalService.getActualsMasters(start, end);
    }

    /**
    * Returns the number of actuals masters.
    *
    * @return the number of actuals masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getActualsMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _actualsMasterLocalService.getActualsMastersCount();
    }

    /**
    * Updates the actuals master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param actualsMaster the actuals master
    * @return the actuals master that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ActualsMaster updateActualsMaster(
        com.stpl.app.model.ActualsMaster actualsMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _actualsMasterLocalService.updateActualsMaster(actualsMaster);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _actualsMasterLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _actualsMasterLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _actualsMasterLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ActualsMasterLocalService getWrappedActualsMasterLocalService() {
        return _actualsMasterLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedActualsMasterLocalService(
        ActualsMasterLocalService actualsMasterLocalService) {
        _actualsMasterLocalService = actualsMasterLocalService;
    }

    @Override
    public ActualsMasterLocalService getWrappedService() {
        return _actualsMasterLocalService;
    }

    @Override
    public void setWrappedService(
        ActualsMasterLocalService actualsMasterLocalService) {
        _actualsMasterLocalService = actualsMasterLocalService;
    }
}
