package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link NaProjectionSelectionLocalService}.
 *
 * @author
 * @see NaProjectionSelectionLocalService
 * @generated
 */
public class NaProjectionSelectionLocalServiceWrapper
    implements NaProjectionSelectionLocalService,
        ServiceWrapper<NaProjectionSelectionLocalService> {
    private NaProjectionSelectionLocalService _naProjectionSelectionLocalService;

    public NaProjectionSelectionLocalServiceWrapper(
        NaProjectionSelectionLocalService naProjectionSelectionLocalService) {
        _naProjectionSelectionLocalService = naProjectionSelectionLocalService;
    }

    /**
    * Adds the na projection selection to the database. Also notifies the appropriate model listeners.
    *
    * @param naProjectionSelection the na projection selection
    * @return the na projection selection that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NaProjectionSelection addNaProjectionSelection(
        com.stpl.app.model.NaProjectionSelection naProjectionSelection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _naProjectionSelectionLocalService.addNaProjectionSelection(naProjectionSelection);
    }

    /**
    * Creates a new na projection selection with the primary key. Does not add the na projection selection to the database.
    *
    * @param naProjectionSelectionSid the primary key for the new na projection selection
    * @return the new na projection selection
    */
    @Override
    public com.stpl.app.model.NaProjectionSelection createNaProjectionSelection(
        int naProjectionSelectionSid) {
        return _naProjectionSelectionLocalService.createNaProjectionSelection(naProjectionSelectionSid);
    }

    /**
    * Deletes the na projection selection with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param naProjectionSelectionSid the primary key of the na projection selection
    * @return the na projection selection that was removed
    * @throws PortalException if a na projection selection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NaProjectionSelection deleteNaProjectionSelection(
        int naProjectionSelectionSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _naProjectionSelectionLocalService.deleteNaProjectionSelection(naProjectionSelectionSid);
    }

    /**
    * Deletes the na projection selection from the database. Also notifies the appropriate model listeners.
    *
    * @param naProjectionSelection the na projection selection
    * @return the na projection selection that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NaProjectionSelection deleteNaProjectionSelection(
        com.stpl.app.model.NaProjectionSelection naProjectionSelection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _naProjectionSelectionLocalService.deleteNaProjectionSelection(naProjectionSelection);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _naProjectionSelectionLocalService.dynamicQuery();
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
        return _naProjectionSelectionLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NaProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _naProjectionSelectionLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NaProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _naProjectionSelectionLocalService.dynamicQuery(dynamicQuery,
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
        return _naProjectionSelectionLocalService.dynamicQueryCount(dynamicQuery);
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
        return _naProjectionSelectionLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.NaProjectionSelection fetchNaProjectionSelection(
        int naProjectionSelectionSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _naProjectionSelectionLocalService.fetchNaProjectionSelection(naProjectionSelectionSid);
    }

    /**
    * Returns the na projection selection with the primary key.
    *
    * @param naProjectionSelectionSid the primary key of the na projection selection
    * @return the na projection selection
    * @throws PortalException if a na projection selection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NaProjectionSelection getNaProjectionSelection(
        int naProjectionSelectionSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _naProjectionSelectionLocalService.getNaProjectionSelection(naProjectionSelectionSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _naProjectionSelectionLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the na projection selections.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NaProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of na projection selections
    * @param end the upper bound of the range of na projection selections (not inclusive)
    * @return the range of na projection selections
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.NaProjectionSelection> getNaProjectionSelections(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _naProjectionSelectionLocalService.getNaProjectionSelections(start,
            end);
    }

    /**
    * Returns the number of na projection selections.
    *
    * @return the number of na projection selections
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getNaProjectionSelectionsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _naProjectionSelectionLocalService.getNaProjectionSelectionsCount();
    }

    /**
    * Updates the na projection selection in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param naProjectionSelection the na projection selection
    * @return the na projection selection that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.NaProjectionSelection updateNaProjectionSelection(
        com.stpl.app.model.NaProjectionSelection naProjectionSelection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _naProjectionSelectionLocalService.updateNaProjectionSelection(naProjectionSelection);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _naProjectionSelectionLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _naProjectionSelectionLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _naProjectionSelectionLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public NaProjectionSelectionLocalService getWrappedNaProjectionSelectionLocalService() {
        return _naProjectionSelectionLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedNaProjectionSelectionLocalService(
        NaProjectionSelectionLocalService naProjectionSelectionLocalService) {
        _naProjectionSelectionLocalService = naProjectionSelectionLocalService;
    }

    @Override
    public NaProjectionSelectionLocalService getWrappedService() {
        return _naProjectionSelectionLocalService;
    }

    @Override
    public void setWrappedService(
        NaProjectionSelectionLocalService naProjectionSelectionLocalService) {
        _naProjectionSelectionLocalService = naProjectionSelectionLocalService;
    }
}
