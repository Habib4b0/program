package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link PsContractDetailsLocalService}.
 *
 * @author
 * @see PsContractDetailsLocalService
 * @generated
 */
public class PsContractDetailsLocalServiceWrapper
    implements PsContractDetailsLocalService,
        ServiceWrapper<PsContractDetailsLocalService> {
    private PsContractDetailsLocalService _psContractDetailsLocalService;

    public PsContractDetailsLocalServiceWrapper(
        PsContractDetailsLocalService psContractDetailsLocalService) {
        _psContractDetailsLocalService = psContractDetailsLocalService;
    }

    /**
    * Adds the ps contract details to the database. Also notifies the appropriate model listeners.
    *
    * @param psContractDetails the ps contract details
    * @return the ps contract details that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.PsContractDetails addPsContractDetails(
        com.stpl.app.model.PsContractDetails psContractDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _psContractDetailsLocalService.addPsContractDetails(psContractDetails);
    }

    /**
    * Creates a new ps contract details with the primary key. Does not add the ps contract details to the database.
    *
    * @param psContractDetailsSid the primary key for the new ps contract details
    * @return the new ps contract details
    */
    @Override
    public com.stpl.app.model.PsContractDetails createPsContractDetails(
        int psContractDetailsSid) {
        return _psContractDetailsLocalService.createPsContractDetails(psContractDetailsSid);
    }

    /**
    * Deletes the ps contract details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param psContractDetailsSid the primary key of the ps contract details
    * @return the ps contract details that was removed
    * @throws PortalException if a ps contract details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.PsContractDetails deletePsContractDetails(
        int psContractDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _psContractDetailsLocalService.deletePsContractDetails(psContractDetailsSid);
    }

    /**
    * Deletes the ps contract details from the database. Also notifies the appropriate model listeners.
    *
    * @param psContractDetails the ps contract details
    * @return the ps contract details that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.PsContractDetails deletePsContractDetails(
        com.stpl.app.model.PsContractDetails psContractDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _psContractDetailsLocalService.deletePsContractDetails(psContractDetails);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _psContractDetailsLocalService.dynamicQuery();
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
        return _psContractDetailsLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _psContractDetailsLocalService.dynamicQuery(dynamicQuery, start,
            end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _psContractDetailsLocalService.dynamicQuery(dynamicQuery, start,
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
        return _psContractDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
        return _psContractDetailsLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.PsContractDetails fetchPsContractDetails(
        int psContractDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _psContractDetailsLocalService.fetchPsContractDetails(psContractDetailsSid);
    }

    /**
    * Returns the ps contract details with the primary key.
    *
    * @param psContractDetailsSid the primary key of the ps contract details
    * @return the ps contract details
    * @throws PortalException if a ps contract details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.PsContractDetails getPsContractDetails(
        int psContractDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _psContractDetailsLocalService.getPsContractDetails(psContractDetailsSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _psContractDetailsLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the ps contract detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ps contract detailses
    * @param end the upper bound of the range of ps contract detailses (not inclusive)
    * @return the range of ps contract detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.PsContractDetails> getPsContractDetailses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _psContractDetailsLocalService.getPsContractDetailses(start, end);
    }

    /**
    * Returns the number of ps contract detailses.
    *
    * @return the number of ps contract detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getPsContractDetailsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _psContractDetailsLocalService.getPsContractDetailsesCount();
    }

    /**
    * Updates the ps contract details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param psContractDetails the ps contract details
    * @return the ps contract details that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.PsContractDetails updatePsContractDetails(
        com.stpl.app.model.PsContractDetails psContractDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _psContractDetailsLocalService.updatePsContractDetails(psContractDetails);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _psContractDetailsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _psContractDetailsLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _psContractDetailsLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    @Override
    public java.lang.Boolean savePsDetailsAttached(
        java.util.List<java.lang.Object> input, java.lang.Object future) {
        return _psContractDetailsLocalService.savePsDetailsAttached(input,
            future);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public PsContractDetailsLocalService getWrappedPsContractDetailsLocalService() {
        return _psContractDetailsLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedPsContractDetailsLocalService(
        PsContractDetailsLocalService psContractDetailsLocalService) {
        _psContractDetailsLocalService = psContractDetailsLocalService;
    }

    @Override
    public PsContractDetailsLocalService getWrappedService() {
        return _psContractDetailsLocalService;
    }

    @Override
    public void setWrappedService(
        PsContractDetailsLocalService psContractDetailsLocalService) {
        _psContractDetailsLocalService = psContractDetailsLocalService;
    }
}
