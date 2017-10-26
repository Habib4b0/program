package com.stpl.app.parttwo.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AccClosureDetailsLocalService}.
 *
 * @author
 * @see AccClosureDetailsLocalService
 * @generated
 */
public class AccClosureDetailsLocalServiceWrapper
    implements AccClosureDetailsLocalService,
        ServiceWrapper<AccClosureDetailsLocalService> {
    private AccClosureDetailsLocalService _accClosureDetailsLocalService;

    public AccClosureDetailsLocalServiceWrapper(
        AccClosureDetailsLocalService accClosureDetailsLocalService) {
        _accClosureDetailsLocalService = accClosureDetailsLocalService;
    }

    /**
    * Adds the acc closure details to the database. Also notifies the appropriate model listeners.
    *
    * @param accClosureDetails the acc closure details
    * @return the acc closure details that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.AccClosureDetails addAccClosureDetails(
        com.stpl.app.parttwo.model.AccClosureDetails accClosureDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _accClosureDetailsLocalService.addAccClosureDetails(accClosureDetails);
    }

    /**
    * Creates a new acc closure details with the primary key. Does not add the acc closure details to the database.
    *
    * @param accClosureDetailsSid the primary key for the new acc closure details
    * @return the new acc closure details
    */
    @Override
    public com.stpl.app.parttwo.model.AccClosureDetails createAccClosureDetails(
        int accClosureDetailsSid) {
        return _accClosureDetailsLocalService.createAccClosureDetails(accClosureDetailsSid);
    }

    /**
    * Deletes the acc closure details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param accClosureDetailsSid the primary key of the acc closure details
    * @return the acc closure details that was removed
    * @throws PortalException if a acc closure details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.AccClosureDetails deleteAccClosureDetails(
        int accClosureDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _accClosureDetailsLocalService.deleteAccClosureDetails(accClosureDetailsSid);
    }

    /**
    * Deletes the acc closure details from the database. Also notifies the appropriate model listeners.
    *
    * @param accClosureDetails the acc closure details
    * @return the acc closure details that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.AccClosureDetails deleteAccClosureDetails(
        com.stpl.app.parttwo.model.AccClosureDetails accClosureDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _accClosureDetailsLocalService.deleteAccClosureDetails(accClosureDetails);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _accClosureDetailsLocalService.dynamicQuery();
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
        return _accClosureDetailsLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AccClosureDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _accClosureDetailsLocalService.dynamicQuery(dynamicQuery, start,
            end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AccClosureDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _accClosureDetailsLocalService.dynamicQuery(dynamicQuery, start,
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
        return _accClosureDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
        return _accClosureDetailsLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.parttwo.model.AccClosureDetails fetchAccClosureDetails(
        int accClosureDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _accClosureDetailsLocalService.fetchAccClosureDetails(accClosureDetailsSid);
    }

    /**
    * Returns the acc closure details with the primary key.
    *
    * @param accClosureDetailsSid the primary key of the acc closure details
    * @return the acc closure details
    * @throws PortalException if a acc closure details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.AccClosureDetails getAccClosureDetails(
        int accClosureDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _accClosureDetailsLocalService.getAccClosureDetails(accClosureDetailsSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _accClosureDetailsLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the acc closure detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AccClosureDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of acc closure detailses
    * @param end the upper bound of the range of acc closure detailses (not inclusive)
    * @return the range of acc closure detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.parttwo.model.AccClosureDetails> getAccClosureDetailses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _accClosureDetailsLocalService.getAccClosureDetailses(start, end);
    }

    /**
    * Returns the number of acc closure detailses.
    *
    * @return the number of acc closure detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getAccClosureDetailsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _accClosureDetailsLocalService.getAccClosureDetailsesCount();
    }

    /**
    * Updates the acc closure details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param accClosureDetails the acc closure details
    * @return the acc closure details that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.AccClosureDetails updateAccClosureDetails(
        com.stpl.app.parttwo.model.AccClosureDetails accClosureDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _accClosureDetailsLocalService.updateAccClosureDetails(accClosureDetails);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _accClosureDetailsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _accClosureDetailsLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _accClosureDetailsLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public AccClosureDetailsLocalService getWrappedAccClosureDetailsLocalService() {
        return _accClosureDetailsLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedAccClosureDetailsLocalService(
        AccClosureDetailsLocalService accClosureDetailsLocalService) {
        _accClosureDetailsLocalService = accClosureDetailsLocalService;
    }

    @Override
    public AccClosureDetailsLocalService getWrappedService() {
        return _accClosureDetailsLocalService;
    }

    @Override
    public void setWrappedService(
        AccClosureDetailsLocalService accClosureDetailsLocalService) {
        _accClosureDetailsLocalService = accClosureDetailsLocalService;
    }
}
