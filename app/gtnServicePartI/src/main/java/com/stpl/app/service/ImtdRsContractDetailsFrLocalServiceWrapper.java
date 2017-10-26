package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ImtdRsContractDetailsFrLocalService}.
 *
 * @author
 * @see ImtdRsContractDetailsFrLocalService
 * @generated
 */
public class ImtdRsContractDetailsFrLocalServiceWrapper
    implements ImtdRsContractDetailsFrLocalService,
        ServiceWrapper<ImtdRsContractDetailsFrLocalService> {
    private ImtdRsContractDetailsFrLocalService _imtdRsContractDetailsFrLocalService;

    public ImtdRsContractDetailsFrLocalServiceWrapper(
        ImtdRsContractDetailsFrLocalService imtdRsContractDetailsFrLocalService) {
        _imtdRsContractDetailsFrLocalService = imtdRsContractDetailsFrLocalService;
    }

    /**
    * Adds the imtd rs contract details fr to the database. Also notifies the appropriate model listeners.
    *
    * @param imtdRsContractDetailsFr the imtd rs contract details fr
    * @return the imtd rs contract details fr that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ImtdRsContractDetailsFr addImtdRsContractDetailsFr(
        com.stpl.app.model.ImtdRsContractDetailsFr imtdRsContractDetailsFr)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _imtdRsContractDetailsFrLocalService.addImtdRsContractDetailsFr(imtdRsContractDetailsFr);
    }

    /**
    * Creates a new imtd rs contract details fr with the primary key. Does not add the imtd rs contract details fr to the database.
    *
    * @param imtdRsContractDetailsFrSid the primary key for the new imtd rs contract details fr
    * @return the new imtd rs contract details fr
    */
    @Override
    public com.stpl.app.model.ImtdRsContractDetailsFr createImtdRsContractDetailsFr(
        int imtdRsContractDetailsFrSid) {
        return _imtdRsContractDetailsFrLocalService.createImtdRsContractDetailsFr(imtdRsContractDetailsFrSid);
    }

    /**
    * Deletes the imtd rs contract details fr with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param imtdRsContractDetailsFrSid the primary key of the imtd rs contract details fr
    * @return the imtd rs contract details fr that was removed
    * @throws PortalException if a imtd rs contract details fr with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ImtdRsContractDetailsFr deleteImtdRsContractDetailsFr(
        int imtdRsContractDetailsFrSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _imtdRsContractDetailsFrLocalService.deleteImtdRsContractDetailsFr(imtdRsContractDetailsFrSid);
    }

    /**
    * Deletes the imtd rs contract details fr from the database. Also notifies the appropriate model listeners.
    *
    * @param imtdRsContractDetailsFr the imtd rs contract details fr
    * @return the imtd rs contract details fr that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ImtdRsContractDetailsFr deleteImtdRsContractDetailsFr(
        com.stpl.app.model.ImtdRsContractDetailsFr imtdRsContractDetailsFr)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _imtdRsContractDetailsFrLocalService.deleteImtdRsContractDetailsFr(imtdRsContractDetailsFr);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _imtdRsContractDetailsFrLocalService.dynamicQuery();
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
        return _imtdRsContractDetailsFrLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdRsContractDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _imtdRsContractDetailsFrLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdRsContractDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _imtdRsContractDetailsFrLocalService.dynamicQuery(dynamicQuery,
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
        return _imtdRsContractDetailsFrLocalService.dynamicQueryCount(dynamicQuery);
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
        return _imtdRsContractDetailsFrLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.ImtdRsContractDetailsFr fetchImtdRsContractDetailsFr(
        int imtdRsContractDetailsFrSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _imtdRsContractDetailsFrLocalService.fetchImtdRsContractDetailsFr(imtdRsContractDetailsFrSid);
    }

    /**
    * Returns the imtd rs contract details fr with the primary key.
    *
    * @param imtdRsContractDetailsFrSid the primary key of the imtd rs contract details fr
    * @return the imtd rs contract details fr
    * @throws PortalException if a imtd rs contract details fr with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ImtdRsContractDetailsFr getImtdRsContractDetailsFr(
        int imtdRsContractDetailsFrSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _imtdRsContractDetailsFrLocalService.getImtdRsContractDetailsFr(imtdRsContractDetailsFrSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _imtdRsContractDetailsFrLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the imtd rs contract details frs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdRsContractDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of imtd rs contract details frs
    * @param end the upper bound of the range of imtd rs contract details frs (not inclusive)
    * @return the range of imtd rs contract details frs
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.ImtdRsContractDetailsFr> getImtdRsContractDetailsFrs(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _imtdRsContractDetailsFrLocalService.getImtdRsContractDetailsFrs(start,
            end);
    }

    /**
    * Returns the number of imtd rs contract details frs.
    *
    * @return the number of imtd rs contract details frs
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getImtdRsContractDetailsFrsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _imtdRsContractDetailsFrLocalService.getImtdRsContractDetailsFrsCount();
    }

    /**
    * Updates the imtd rs contract details fr in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param imtdRsContractDetailsFr the imtd rs contract details fr
    * @return the imtd rs contract details fr that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ImtdRsContractDetailsFr updateImtdRsContractDetailsFr(
        com.stpl.app.model.ImtdRsContractDetailsFr imtdRsContractDetailsFr)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _imtdRsContractDetailsFrLocalService.updateImtdRsContractDetailsFr(imtdRsContractDetailsFr);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _imtdRsContractDetailsFrLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _imtdRsContractDetailsFrLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _imtdRsContractDetailsFrLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ImtdRsContractDetailsFrLocalService getWrappedImtdRsContractDetailsFrLocalService() {
        return _imtdRsContractDetailsFrLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedImtdRsContractDetailsFrLocalService(
        ImtdRsContractDetailsFrLocalService imtdRsContractDetailsFrLocalService) {
        _imtdRsContractDetailsFrLocalService = imtdRsContractDetailsFrLocalService;
    }

    @Override
    public ImtdRsContractDetailsFrLocalService getWrappedService() {
        return _imtdRsContractDetailsFrLocalService;
    }

    @Override
    public void setWrappedService(
        ImtdRsContractDetailsFrLocalService imtdRsContractDetailsFrLocalService) {
        _imtdRsContractDetailsFrLocalService = imtdRsContractDetailsFrLocalService;
    }
}
