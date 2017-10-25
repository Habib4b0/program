package com.stpl.app.parttwo.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link AcFdAdjustmentSelectionLocalService}.
 *
 * @author
 * @see AcFdAdjustmentSelectionLocalService
 * @generated
 */
public class AcFdAdjustmentSelectionLocalServiceWrapper
    implements AcFdAdjustmentSelectionLocalService,
        ServiceWrapper<AcFdAdjustmentSelectionLocalService> {
    private AcFdAdjustmentSelectionLocalService _acFdAdjustmentSelectionLocalService;

    public AcFdAdjustmentSelectionLocalServiceWrapper(
        AcFdAdjustmentSelectionLocalService acFdAdjustmentSelectionLocalService) {
        _acFdAdjustmentSelectionLocalService = acFdAdjustmentSelectionLocalService;
    }

    /**
    * Adds the ac fd adjustment selection to the database. Also notifies the appropriate model listeners.
    *
    * @param acFdAdjustmentSelection the ac fd adjustment selection
    * @return the ac fd adjustment selection that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.AcFdAdjustmentSelection addAcFdAdjustmentSelection(
        com.stpl.app.parttwo.model.AcFdAdjustmentSelection acFdAdjustmentSelection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _acFdAdjustmentSelectionLocalService.addAcFdAdjustmentSelection(acFdAdjustmentSelection);
    }

    /**
    * Creates a new ac fd adjustment selection with the primary key. Does not add the ac fd adjustment selection to the database.
    *
    * @param accClosureMasterSid the primary key for the new ac fd adjustment selection
    * @return the new ac fd adjustment selection
    */
    @Override
    public com.stpl.app.parttwo.model.AcFdAdjustmentSelection createAcFdAdjustmentSelection(
        int accClosureMasterSid) {
        return _acFdAdjustmentSelectionLocalService.createAcFdAdjustmentSelection(accClosureMasterSid);
    }

    /**
    * Deletes the ac fd adjustment selection with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param accClosureMasterSid the primary key of the ac fd adjustment selection
    * @return the ac fd adjustment selection that was removed
    * @throws PortalException if a ac fd adjustment selection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.AcFdAdjustmentSelection deleteAcFdAdjustmentSelection(
        int accClosureMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _acFdAdjustmentSelectionLocalService.deleteAcFdAdjustmentSelection(accClosureMasterSid);
    }

    /**
    * Deletes the ac fd adjustment selection from the database. Also notifies the appropriate model listeners.
    *
    * @param acFdAdjustmentSelection the ac fd adjustment selection
    * @return the ac fd adjustment selection that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.AcFdAdjustmentSelection deleteAcFdAdjustmentSelection(
        com.stpl.app.parttwo.model.AcFdAdjustmentSelection acFdAdjustmentSelection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _acFdAdjustmentSelectionLocalService.deleteAcFdAdjustmentSelection(acFdAdjustmentSelection);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _acFdAdjustmentSelectionLocalService.dynamicQuery();
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
        return _acFdAdjustmentSelectionLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AcFdAdjustmentSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _acFdAdjustmentSelectionLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AcFdAdjustmentSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _acFdAdjustmentSelectionLocalService.dynamicQuery(dynamicQuery,
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
        return _acFdAdjustmentSelectionLocalService.dynamicQueryCount(dynamicQuery);
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
        return _acFdAdjustmentSelectionLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.parttwo.model.AcFdAdjustmentSelection fetchAcFdAdjustmentSelection(
        int accClosureMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _acFdAdjustmentSelectionLocalService.fetchAcFdAdjustmentSelection(accClosureMasterSid);
    }

    /**
    * Returns the ac fd adjustment selection with the primary key.
    *
    * @param accClosureMasterSid the primary key of the ac fd adjustment selection
    * @return the ac fd adjustment selection
    * @throws PortalException if a ac fd adjustment selection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.AcFdAdjustmentSelection getAcFdAdjustmentSelection(
        int accClosureMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _acFdAdjustmentSelectionLocalService.getAcFdAdjustmentSelection(accClosureMasterSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _acFdAdjustmentSelectionLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the ac fd adjustment selections.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AcFdAdjustmentSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ac fd adjustment selections
    * @param end the upper bound of the range of ac fd adjustment selections (not inclusive)
    * @return the range of ac fd adjustment selections
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.parttwo.model.AcFdAdjustmentSelection> getAcFdAdjustmentSelections(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _acFdAdjustmentSelectionLocalService.getAcFdAdjustmentSelections(start,
            end);
    }

    /**
    * Returns the number of ac fd adjustment selections.
    *
    * @return the number of ac fd adjustment selections
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getAcFdAdjustmentSelectionsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _acFdAdjustmentSelectionLocalService.getAcFdAdjustmentSelectionsCount();
    }

    /**
    * Updates the ac fd adjustment selection in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param acFdAdjustmentSelection the ac fd adjustment selection
    * @return the ac fd adjustment selection that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.parttwo.model.AcFdAdjustmentSelection updateAcFdAdjustmentSelection(
        com.stpl.app.parttwo.model.AcFdAdjustmentSelection acFdAdjustmentSelection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _acFdAdjustmentSelectionLocalService.updateAcFdAdjustmentSelection(acFdAdjustmentSelection);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _acFdAdjustmentSelectionLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _acFdAdjustmentSelectionLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _acFdAdjustmentSelectionLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public AcFdAdjustmentSelectionLocalService getWrappedAcFdAdjustmentSelectionLocalService() {
        return _acFdAdjustmentSelectionLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedAcFdAdjustmentSelectionLocalService(
        AcFdAdjustmentSelectionLocalService acFdAdjustmentSelectionLocalService) {
        _acFdAdjustmentSelectionLocalService = acFdAdjustmentSelectionLocalService;
    }

    @Override
    public AcFdAdjustmentSelectionLocalService getWrappedService() {
        return _acFdAdjustmentSelectionLocalService;
    }

    @Override
    public void setWrappedService(
        AcFdAdjustmentSelectionLocalService acFdAdjustmentSelectionLocalService) {
        _acFdAdjustmentSelectionLocalService = acFdAdjustmentSelectionLocalService;
    }
}
