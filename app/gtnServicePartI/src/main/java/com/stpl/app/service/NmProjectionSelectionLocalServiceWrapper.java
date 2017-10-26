package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link NmProjectionSelectionLocalService}.
 *
 * @author
 * @see NmProjectionSelectionLocalService
 * @generated
 */
public class NmProjectionSelectionLocalServiceWrapper
    implements NmProjectionSelectionLocalService,
        ServiceWrapper<NmProjectionSelectionLocalService> {
    private NmProjectionSelectionLocalService _nmProjectionSelectionLocalService;

    public NmProjectionSelectionLocalServiceWrapper(
        NmProjectionSelectionLocalService nmProjectionSelectionLocalService) {
        _nmProjectionSelectionLocalService = nmProjectionSelectionLocalService;
    }

    /**
    * Adds the nm projection selection to the database. Also notifies the appropriate model listeners.
    *
    * @param nmProjectionSelection the nm projection selection
    * @return the nm projection selection that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NmProjectionSelection addNmProjectionSelection(
        com.stpl.app.model.NmProjectionSelection nmProjectionSelection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmProjectionSelectionLocalService.addNmProjectionSelection(nmProjectionSelection);
    }

    /**
    * Creates a new nm projection selection with the primary key. Does not add the nm projection selection to the database.
    *
    * @param nmProjectionSelectionSid the primary key for the new nm projection selection
    * @return the new nm projection selection
    */
    @Override
    public com.stpl.app.model.NmProjectionSelection createNmProjectionSelection(
        int nmProjectionSelectionSid) {
        return _nmProjectionSelectionLocalService.createNmProjectionSelection(nmProjectionSelectionSid);
    }

    /**
    * Deletes the nm projection selection with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param nmProjectionSelectionSid the primary key of the nm projection selection
    * @return the nm projection selection that was removed
    * @throws PortalException if a nm projection selection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NmProjectionSelection deleteNmProjectionSelection(
        int nmProjectionSelectionSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _nmProjectionSelectionLocalService.deleteNmProjectionSelection(nmProjectionSelectionSid);
    }

    /**
    * Deletes the nm projection selection from the database. Also notifies the appropriate model listeners.
    *
    * @param nmProjectionSelection the nm projection selection
    * @return the nm projection selection that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NmProjectionSelection deleteNmProjectionSelection(
        com.stpl.app.model.NmProjectionSelection nmProjectionSelection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmProjectionSelectionLocalService.deleteNmProjectionSelection(nmProjectionSelection);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _nmProjectionSelectionLocalService.dynamicQuery();
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
        return _nmProjectionSelectionLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _nmProjectionSelectionLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _nmProjectionSelectionLocalService.dynamicQuery(dynamicQuery,
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
        return _nmProjectionSelectionLocalService.dynamicQueryCount(dynamicQuery);
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
        return _nmProjectionSelectionLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.NmProjectionSelection fetchNmProjectionSelection(
        int nmProjectionSelectionSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmProjectionSelectionLocalService.fetchNmProjectionSelection(nmProjectionSelectionSid);
    }

    /**
    * Returns the nm projection selection with the primary key.
    *
    * @param nmProjectionSelectionSid the primary key of the nm projection selection
    * @return the nm projection selection
    * @throws PortalException if a nm projection selection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NmProjectionSelection getNmProjectionSelection(
        int nmProjectionSelectionSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _nmProjectionSelectionLocalService.getNmProjectionSelection(nmProjectionSelectionSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _nmProjectionSelectionLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the nm projection selections.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of nm projection selections
    * @param end the upper bound of the range of nm projection selections (not inclusive)
    * @return the range of nm projection selections
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.NmProjectionSelection> getNmProjectionSelections(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmProjectionSelectionLocalService.getNmProjectionSelections(start,
            end);
    }

    /**
    * Returns the number of nm projection selections.
    *
    * @return the number of nm projection selections
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getNmProjectionSelectionsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmProjectionSelectionLocalService.getNmProjectionSelectionsCount();
    }

    /**
    * Updates the nm projection selection in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param nmProjectionSelection the nm projection selection
    * @return the nm projection selection that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NmProjectionSelection updateNmProjectionSelection(
        com.stpl.app.model.NmProjectionSelection nmProjectionSelection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _nmProjectionSelectionLocalService.updateNmProjectionSelection(nmProjectionSelection);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _nmProjectionSelectionLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _nmProjectionSelectionLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _nmProjectionSelectionLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public NmProjectionSelectionLocalService getWrappedNmProjectionSelectionLocalService() {
        return _nmProjectionSelectionLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedNmProjectionSelectionLocalService(
        NmProjectionSelectionLocalService nmProjectionSelectionLocalService) {
        _nmProjectionSelectionLocalService = nmProjectionSelectionLocalService;
    }

    @Override
    public NmProjectionSelectionLocalService getWrappedService() {
        return _nmProjectionSelectionLocalService;
    }

    @Override
    public void setWrappedService(
        NmProjectionSelectionLocalService nmProjectionSelectionLocalService) {
        _nmProjectionSelectionLocalService = nmProjectionSelectionLocalService;
    }
}
