package com.stpl.app.parttwo.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AcBrMethodologyDetailsLocalService}.
 *
 * @author
 * @see AcBrMethodologyDetailsLocalService
 * @generated
 */
public class AcBrMethodologyDetailsLocalServiceWrapper
    implements AcBrMethodologyDetailsLocalService,
        ServiceWrapper<AcBrMethodologyDetailsLocalService> {
    private AcBrMethodologyDetailsLocalService _acBrMethodologyDetailsLocalService;

    public AcBrMethodologyDetailsLocalServiceWrapper(
        AcBrMethodologyDetailsLocalService acBrMethodologyDetailsLocalService) {
        _acBrMethodologyDetailsLocalService = acBrMethodologyDetailsLocalService;
    }

    /**
    * Adds the ac br methodology details to the database. Also notifies the appropriate model listeners.
    *
    * @param acBrMethodologyDetails the ac br methodology details
    * @return the ac br methodology details that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.AcBrMethodologyDetails addAcBrMethodologyDetails(
        com.stpl.app.parttwo.model.AcBrMethodologyDetails acBrMethodologyDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _acBrMethodologyDetailsLocalService.addAcBrMethodologyDetails(acBrMethodologyDetails);
    }

    /**
    * Creates a new ac br methodology details with the primary key. Does not add the ac br methodology details to the database.
    *
    * @param acBrMethodologyDetailsSid the primary key for the new ac br methodology details
    * @return the new ac br methodology details
    */
    @Override
    public com.stpl.app.parttwo.model.AcBrMethodologyDetails createAcBrMethodologyDetails(
        int acBrMethodologyDetailsSid) {
        return _acBrMethodologyDetailsLocalService.createAcBrMethodologyDetails(acBrMethodologyDetailsSid);
    }

    /**
    * Deletes the ac br methodology details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param acBrMethodologyDetailsSid the primary key of the ac br methodology details
    * @return the ac br methodology details that was removed
    * @throws PortalException if a ac br methodology details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.AcBrMethodologyDetails deleteAcBrMethodologyDetails(
        int acBrMethodologyDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _acBrMethodologyDetailsLocalService.deleteAcBrMethodologyDetails(acBrMethodologyDetailsSid);
    }

    /**
    * Deletes the ac br methodology details from the database. Also notifies the appropriate model listeners.
    *
    * @param acBrMethodologyDetails the ac br methodology details
    * @return the ac br methodology details that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.AcBrMethodologyDetails deleteAcBrMethodologyDetails(
        com.stpl.app.parttwo.model.AcBrMethodologyDetails acBrMethodologyDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _acBrMethodologyDetailsLocalService.deleteAcBrMethodologyDetails(acBrMethodologyDetails);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _acBrMethodologyDetailsLocalService.dynamicQuery();
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
        return _acBrMethodologyDetailsLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AcBrMethodologyDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _acBrMethodologyDetailsLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AcBrMethodologyDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _acBrMethodologyDetailsLocalService.dynamicQuery(dynamicQuery,
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
        return _acBrMethodologyDetailsLocalService.dynamicQueryCount(dynamicQuery);
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
        return _acBrMethodologyDetailsLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.parttwo.model.AcBrMethodologyDetails fetchAcBrMethodologyDetails(
        int acBrMethodologyDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _acBrMethodologyDetailsLocalService.fetchAcBrMethodologyDetails(acBrMethodologyDetailsSid);
    }

    /**
    * Returns the ac br methodology details with the primary key.
    *
    * @param acBrMethodologyDetailsSid the primary key of the ac br methodology details
    * @return the ac br methodology details
    * @throws PortalException if a ac br methodology details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.AcBrMethodologyDetails getAcBrMethodologyDetails(
        int acBrMethodologyDetailsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _acBrMethodologyDetailsLocalService.getAcBrMethodologyDetails(acBrMethodologyDetailsSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _acBrMethodologyDetailsLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the ac br methodology detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AcBrMethodologyDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ac br methodology detailses
    * @param end the upper bound of the range of ac br methodology detailses (not inclusive)
    * @return the range of ac br methodology detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.parttwo.model.AcBrMethodologyDetails> getAcBrMethodologyDetailses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _acBrMethodologyDetailsLocalService.getAcBrMethodologyDetailses(start,
            end);
    }

    /**
    * Returns the number of ac br methodology detailses.
    *
    * @return the number of ac br methodology detailses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getAcBrMethodologyDetailsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _acBrMethodologyDetailsLocalService.getAcBrMethodologyDetailsesCount();
    }

    /**
    * Updates the ac br methodology details in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param acBrMethodologyDetails the ac br methodology details
    * @return the ac br methodology details that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.AcBrMethodologyDetails updateAcBrMethodologyDetails(
        com.stpl.app.parttwo.model.AcBrMethodologyDetails acBrMethodologyDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _acBrMethodologyDetailsLocalService.updateAcBrMethodologyDetails(acBrMethodologyDetails);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _acBrMethodologyDetailsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _acBrMethodologyDetailsLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _acBrMethodologyDetailsLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public AcBrMethodologyDetailsLocalService getWrappedAcBrMethodologyDetailsLocalService() {
        return _acBrMethodologyDetailsLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedAcBrMethodologyDetailsLocalService(
        AcBrMethodologyDetailsLocalService acBrMethodologyDetailsLocalService) {
        _acBrMethodologyDetailsLocalService = acBrMethodologyDetailsLocalService;
    }

    @Override
    public AcBrMethodologyDetailsLocalService getWrappedService() {
        return _acBrMethodologyDetailsLocalService;
    }

    @Override
    public void setWrappedService(
        AcBrMethodologyDetailsLocalService acBrMethodologyDetailsLocalService) {
        _acBrMethodologyDetailsLocalService = acBrMethodologyDetailsLocalService;
    }
}
