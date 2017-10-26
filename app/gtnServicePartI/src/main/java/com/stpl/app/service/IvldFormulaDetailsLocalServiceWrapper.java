package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link IvldFormulaDetailsLocalService}.
 *
 * @author
 * @see IvldFormulaDetailsLocalService
 * @generated
 */
public class IvldFormulaDetailsLocalServiceWrapper
    implements IvldFormulaDetailsLocalService,
        ServiceWrapper<IvldFormulaDetailsLocalService> {
    private IvldFormulaDetailsLocalService _ivldFormulaDetailsLocalService;

    public IvldFormulaDetailsLocalServiceWrapper(
        IvldFormulaDetailsLocalService ivldFormulaDetailsLocalService) {
        _ivldFormulaDetailsLocalService = ivldFormulaDetailsLocalService;
    }

    /**
    * Adds the ivld formula details to the database. Also notifies the appropriate model listeners.
    *
    * @param ivldFormulaDetails the ivld formula details
    * @return the ivld formula details that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldFormulaDetails addIvldFormulaDetails(
        com.stpl.app.model.IvldFormulaDetails ivldFormulaDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldFormulaDetailsLocalService.addIvldFormulaDetails(ivldFormulaDetails);
    }

    /**
    * Creates a new ivld formula details with the primary key. Does not add the ivld formula details to the database.
    *
    * @param ivldFormulaDetailsSid the primary key for the new ivld formula details
    * @return the new ivld formula details
    */
    @Override
    public com.stpl.app.model.IvldFormulaDetails createIvldFormulaDetails(
        int ivldFormulaDetailsSid) {
        return _ivldFormulaDetailsLocalService.createIvldFormulaDetails(ivldFormulaDetailsSid);
    }

    /**
    * Deletes the ivld formula details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldFormulaDetailsSid the primary key of the ivld formula details
    * @return the ivld formula details that was removed
    * @throws PortalException if a ivld formula details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldFormulaDetails deleteIvldFormulaDetails(
        int ivldFormulaDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldFormulaDetailsLocalService.deleteIvldFormulaDetails(ivldFormulaDetailsSid);
    }

    /**
    * Deletes the ivld formula details from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldFormulaDetails the ivld formula details
    * @return the ivld formula details that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldFormulaDetails deleteIvldFormulaDetails(
        com.stpl.app.model.IvldFormulaDetails ivldFormulaDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldFormulaDetailsLocalService.deleteIvldFormulaDetails(ivldFormulaDetails);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _ivldFormulaDetailsLocalService.dynamicQuery();
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
        return _ivldFormulaDetailsLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldFormulaDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ivldFormulaDetailsLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldFormulaDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ivldFormulaDetailsLocalService.dynamicQuery(dynamicQuery,
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
        return _ivldFormulaDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
        return _ivldFormulaDetailsLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.IvldFormulaDetails fetchIvldFormulaDetails(
        int ivldFormulaDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldFormulaDetailsLocalService.fetchIvldFormulaDetails(ivldFormulaDetailsSid);
    }

    /**
    * Returns the ivld formula details with the primary key.
    *
    * @param ivldFormulaDetailsSid the primary key of the ivld formula details
    * @return the ivld formula details
    * @throws PortalException if a ivld formula details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldFormulaDetails getIvldFormulaDetails(
        int ivldFormulaDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldFormulaDetailsLocalService.getIvldFormulaDetails(ivldFormulaDetailsSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ivldFormulaDetailsLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the ivld formula detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldFormulaDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld formula detailses
    * @param end the upper bound of the range of ivld formula detailses (not inclusive)
    * @return the range of ivld formula detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.IvldFormulaDetails> getIvldFormulaDetailses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldFormulaDetailsLocalService.getIvldFormulaDetailses(start,
            end);
    }

    /**
    * Returns the number of ivld formula detailses.
    *
    * @return the number of ivld formula detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getIvldFormulaDetailsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldFormulaDetailsLocalService.getIvldFormulaDetailsesCount();
    }

    /**
    * Updates the ivld formula details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param ivldFormulaDetails the ivld formula details
    * @return the ivld formula details that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IvldFormulaDetails updateIvldFormulaDetails(
        com.stpl.app.model.IvldFormulaDetails ivldFormulaDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ivldFormulaDetailsLocalService.updateIvldFormulaDetails(ivldFormulaDetails);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _ivldFormulaDetailsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _ivldFormulaDetailsLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _ivldFormulaDetailsLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public IvldFormulaDetailsLocalService getWrappedIvldFormulaDetailsLocalService() {
        return _ivldFormulaDetailsLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedIvldFormulaDetailsLocalService(
        IvldFormulaDetailsLocalService ivldFormulaDetailsLocalService) {
        _ivldFormulaDetailsLocalService = ivldFormulaDetailsLocalService;
    }

    @Override
    public IvldFormulaDetailsLocalService getWrappedService() {
        return _ivldFormulaDetailsLocalService;
    }

    @Override
    public void setWrappedService(
        IvldFormulaDetailsLocalService ivldFormulaDetailsLocalService) {
        _ivldFormulaDetailsLocalService = ivldFormulaDetailsLocalService;
    }
}
