package com.stpl.app.parttwo.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AccClosureViewMasterLocalService}.
 *
 * @author
 * @see AccClosureViewMasterLocalService
 * @generated
 */
public class AccClosureViewMasterLocalServiceWrapper
    implements AccClosureViewMasterLocalService,
        ServiceWrapper<AccClosureViewMasterLocalService> {
    private AccClosureViewMasterLocalService _accClosureViewMasterLocalService;

    public AccClosureViewMasterLocalServiceWrapper(
        AccClosureViewMasterLocalService accClosureViewMasterLocalService) {
        _accClosureViewMasterLocalService = accClosureViewMasterLocalService;
    }

    /**
    * Adds the acc closure view master to the database. Also notifies the appropriate model listeners.
    *
    * @param accClosureViewMaster the acc closure view master
    * @return the acc closure view master that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.AccClosureViewMaster addAccClosureViewMaster(
        com.stpl.app.parttwo.model.AccClosureViewMaster accClosureViewMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _accClosureViewMasterLocalService.addAccClosureViewMaster(accClosureViewMaster);
    }

    /**
    * Creates a new acc closure view master with the primary key. Does not add the acc closure view master to the database.
    *
    * @param accClosureViewMasterSid the primary key for the new acc closure view master
    * @return the new acc closure view master
    */
    @Override
    public com.stpl.app.parttwo.model.AccClosureViewMaster createAccClosureViewMaster(
        int accClosureViewMasterSid) {
        return _accClosureViewMasterLocalService.createAccClosureViewMaster(accClosureViewMasterSid);
    }

    /**
    * Deletes the acc closure view master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param accClosureViewMasterSid the primary key of the acc closure view master
    * @return the acc closure view master that was removed
    * @throws PortalException if a acc closure view master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.AccClosureViewMaster deleteAccClosureViewMaster(
        int accClosureViewMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _accClosureViewMasterLocalService.deleteAccClosureViewMaster(accClosureViewMasterSid);
    }

    /**
    * Deletes the acc closure view master from the database. Also notifies the appropriate model listeners.
    *
    * @param accClosureViewMaster the acc closure view master
    * @return the acc closure view master that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.AccClosureViewMaster deleteAccClosureViewMaster(
        com.stpl.app.parttwo.model.AccClosureViewMaster accClosureViewMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _accClosureViewMasterLocalService.deleteAccClosureViewMaster(accClosureViewMaster);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _accClosureViewMasterLocalService.dynamicQuery();
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
        return _accClosureViewMasterLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AccClosureViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _accClosureViewMasterLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AccClosureViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _accClosureViewMasterLocalService.dynamicQuery(dynamicQuery,
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
        return _accClosureViewMasterLocalService.dynamicQueryCount(dynamicQuery);
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
        return _accClosureViewMasterLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.parttwo.model.AccClosureViewMaster fetchAccClosureViewMaster(
        int accClosureViewMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _accClosureViewMasterLocalService.fetchAccClosureViewMaster(accClosureViewMasterSid);
    }

    /**
    * Returns the acc closure view master with the primary key.
    *
    * @param accClosureViewMasterSid the primary key of the acc closure view master
    * @return the acc closure view master
    * @throws PortalException if a acc closure view master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.AccClosureViewMaster getAccClosureViewMaster(
        int accClosureViewMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _accClosureViewMasterLocalService.getAccClosureViewMaster(accClosureViewMasterSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _accClosureViewMasterLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the acc closure view masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AccClosureViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of acc closure view masters
    * @param end the upper bound of the range of acc closure view masters (not inclusive)
    * @return the range of acc closure view masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.parttwo.model.AccClosureViewMaster> getAccClosureViewMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _accClosureViewMasterLocalService.getAccClosureViewMasters(start,
            end);
    }

    /**
    * Returns the number of acc closure view masters.
    *
    * @return the number of acc closure view masters
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getAccClosureViewMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _accClosureViewMasterLocalService.getAccClosureViewMastersCount();
    }

    /**
    * Updates the acc closure view master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param accClosureViewMaster the acc closure view master
    * @return the acc closure view master that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.AccClosureViewMaster updateAccClosureViewMaster(
        com.stpl.app.parttwo.model.AccClosureViewMaster accClosureViewMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _accClosureViewMasterLocalService.updateAccClosureViewMaster(accClosureViewMaster);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _accClosureViewMasterLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _accClosureViewMasterLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _accClosureViewMasterLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public AccClosureViewMasterLocalService getWrappedAccClosureViewMasterLocalService() {
        return _accClosureViewMasterLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedAccClosureViewMasterLocalService(
        AccClosureViewMasterLocalService accClosureViewMasterLocalService) {
        _accClosureViewMasterLocalService = accClosureViewMasterLocalService;
    }

    @Override
    public AccClosureViewMasterLocalService getWrappedService() {
        return _accClosureViewMasterLocalService;
    }

    @Override
    public void setWrappedService(
        AccClosureViewMasterLocalService accClosureViewMasterLocalService) {
        _accClosureViewMasterLocalService = accClosureViewMasterLocalService;
    }
}
