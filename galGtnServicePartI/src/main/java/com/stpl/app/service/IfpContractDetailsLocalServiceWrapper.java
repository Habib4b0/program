package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link IfpContractDetailsLocalService}.
 *
 * @author
 * @see IfpContractDetailsLocalService
 * @generated
 */
public class IfpContractDetailsLocalServiceWrapper
    implements IfpContractDetailsLocalService,
        ServiceWrapper<IfpContractDetailsLocalService> {
    private IfpContractDetailsLocalService _ifpContractDetailsLocalService;

    public IfpContractDetailsLocalServiceWrapper(
        IfpContractDetailsLocalService ifpContractDetailsLocalService) {
        _ifpContractDetailsLocalService = ifpContractDetailsLocalService;
    }

    /**
    * Adds the ifp contract details to the database. Also notifies the appropriate model listeners.
    *
    * @param ifpContractDetails the ifp contract details
    * @return the ifp contract details that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IfpContractDetails addIfpContractDetails(
        com.stpl.app.model.IfpContractDetails ifpContractDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ifpContractDetailsLocalService.addIfpContractDetails(ifpContractDetails);
    }

    /**
    * Creates a new ifp contract details with the primary key. Does not add the ifp contract details to the database.
    *
    * @param ifpContractDetailsSid the primary key for the new ifp contract details
    * @return the new ifp contract details
    */
    @Override
    public com.stpl.app.model.IfpContractDetails createIfpContractDetails(
        int ifpContractDetailsSid) {
        return _ifpContractDetailsLocalService.createIfpContractDetails(ifpContractDetailsSid);
    }

    /**
    * Deletes the ifp contract details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ifpContractDetailsSid the primary key of the ifp contract details
    * @return the ifp contract details that was removed
    * @throws PortalException if a ifp contract details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IfpContractDetails deleteIfpContractDetails(
        int ifpContractDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ifpContractDetailsLocalService.deleteIfpContractDetails(ifpContractDetailsSid);
    }

    /**
    * Deletes the ifp contract details from the database. Also notifies the appropriate model listeners.
    *
    * @param ifpContractDetails the ifp contract details
    * @return the ifp contract details that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IfpContractDetails deleteIfpContractDetails(
        com.stpl.app.model.IfpContractDetails ifpContractDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ifpContractDetailsLocalService.deleteIfpContractDetails(ifpContractDetails);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _ifpContractDetailsLocalService.dynamicQuery();
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
        return _ifpContractDetailsLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ifpContractDetailsLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _ifpContractDetailsLocalService.dynamicQuery(dynamicQuery,
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
        return _ifpContractDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
        return _ifpContractDetailsLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.IfpContractDetails fetchIfpContractDetails(
        int ifpContractDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ifpContractDetailsLocalService.fetchIfpContractDetails(ifpContractDetailsSid);
    }

    /**
    * Returns the ifp contract details with the primary key.
    *
    * @param ifpContractDetailsSid the primary key of the ifp contract details
    * @return the ifp contract details
    * @throws PortalException if a ifp contract details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IfpContractDetails getIfpContractDetails(
        int ifpContractDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ifpContractDetailsLocalService.getIfpContractDetails(ifpContractDetailsSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _ifpContractDetailsLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the ifp contract detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ifp contract detailses
    * @param end the upper bound of the range of ifp contract detailses (not inclusive)
    * @return the range of ifp contract detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.IfpContractDetails> getIfpContractDetailses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ifpContractDetailsLocalService.getIfpContractDetailses(start,
            end);
    }

    /**
    * Returns the number of ifp contract detailses.
    *
    * @return the number of ifp contract detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getIfpContractDetailsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ifpContractDetailsLocalService.getIfpContractDetailsesCount();
    }

    /**
    * Updates the ifp contract details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param ifpContractDetails the ifp contract details
    * @return the ifp contract details that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.IfpContractDetails updateIfpContractDetails(
        com.stpl.app.model.IfpContractDetails ifpContractDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _ifpContractDetailsLocalService.updateIfpContractDetails(ifpContractDetails);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _ifpContractDetailsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _ifpContractDetailsLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _ifpContractDetailsLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    @Override
    public java.lang.Boolean saveIfpDetailsAttached(
        java.util.List<java.lang.Object> input, java.lang.Object future) {
        return _ifpContractDetailsLocalService.saveIfpDetailsAttached(input,
            future);
    }

    @Override
    public java.util.List findIFP(java.lang.Object field,
        java.lang.Object value, java.util.List<java.lang.Integer> future,
        java.util.Map<java.lang.String, java.lang.Object> filterMap, int start,
        int end, java.lang.String column, java.lang.String orderBy,
        java.lang.Object future1) {
        return _ifpContractDetailsLocalService.findIFP(field, value, future,
            filterMap, start, end, column, orderBy, future1);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public IfpContractDetailsLocalService getWrappedIfpContractDetailsLocalService() {
        return _ifpContractDetailsLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedIfpContractDetailsLocalService(
        IfpContractDetailsLocalService ifpContractDetailsLocalService) {
        _ifpContractDetailsLocalService = ifpContractDetailsLocalService;
    }

    @Override
    public IfpContractDetailsLocalService getWrappedService() {
        return _ifpContractDetailsLocalService;
    }

    @Override
    public void setWrappedService(
        IfpContractDetailsLocalService ifpContractDetailsLocalService) {
        _ifpContractDetailsLocalService = ifpContractDetailsLocalService;
    }
}
