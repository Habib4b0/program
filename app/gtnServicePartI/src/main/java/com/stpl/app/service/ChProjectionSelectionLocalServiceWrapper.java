package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ChProjectionSelectionLocalService}.
 *
 * @author
 * @see ChProjectionSelectionLocalService
 * @generated
 */
public class ChProjectionSelectionLocalServiceWrapper
    implements ChProjectionSelectionLocalService,
        ServiceWrapper<ChProjectionSelectionLocalService> {
    private ChProjectionSelectionLocalService _chProjectionSelectionLocalService;

    public ChProjectionSelectionLocalServiceWrapper(
        ChProjectionSelectionLocalService chProjectionSelectionLocalService) {
        _chProjectionSelectionLocalService = chProjectionSelectionLocalService;
    }

    /**
    * Adds the ch projection selection to the database. Also notifies the appropriate model listeners.
    *
    * @param chProjectionSelection the ch projection selection
    * @return the ch projection selection that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ChProjectionSelection addChProjectionSelection(
        com.stpl.app.model.ChProjectionSelection chProjectionSelection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _chProjectionSelectionLocalService.addChProjectionSelection(chProjectionSelection);
    }

    /**
    * Creates a new ch projection selection with the primary key. Does not add the ch projection selection to the database.
    *
    * @param chProjectionSelectionSid the primary key for the new ch projection selection
    * @return the new ch projection selection
    */
    @Override
    public com.stpl.app.model.ChProjectionSelection createChProjectionSelection(
        int chProjectionSelectionSid) {
        return _chProjectionSelectionLocalService.createChProjectionSelection(chProjectionSelectionSid);
    }

    /**
    * Deletes the ch projection selection with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param chProjectionSelectionSid the primary key of the ch projection selection
    * @return the ch projection selection that was removed
    * @throws PortalException if a ch projection selection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ChProjectionSelection deleteChProjectionSelection(
        int chProjectionSelectionSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _chProjectionSelectionLocalService.deleteChProjectionSelection(chProjectionSelectionSid);
    }

    /**
    * Deletes the ch projection selection from the database. Also notifies the appropriate model listeners.
    *
    * @param chProjectionSelection the ch projection selection
    * @return the ch projection selection that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ChProjectionSelection deleteChProjectionSelection(
        com.stpl.app.model.ChProjectionSelection chProjectionSelection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _chProjectionSelectionLocalService.deleteChProjectionSelection(chProjectionSelection);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _chProjectionSelectionLocalService.dynamicQuery();
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
        return _chProjectionSelectionLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _chProjectionSelectionLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _chProjectionSelectionLocalService.dynamicQuery(dynamicQuery,
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
        return _chProjectionSelectionLocalService.dynamicQueryCount(dynamicQuery);
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
        return _chProjectionSelectionLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.ChProjectionSelection fetchChProjectionSelection(
        int chProjectionSelectionSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _chProjectionSelectionLocalService.fetchChProjectionSelection(chProjectionSelectionSid);
    }

    /**
    * Returns the ch projection selection with the primary key.
    *
    * @param chProjectionSelectionSid the primary key of the ch projection selection
    * @return the ch projection selection
    * @throws PortalException if a ch projection selection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ChProjectionSelection getChProjectionSelection(
        int chProjectionSelectionSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _chProjectionSelectionLocalService.getChProjectionSelection(chProjectionSelectionSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _chProjectionSelectionLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the ch projection selections.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ch projection selections
    * @param end the upper bound of the range of ch projection selections (not inclusive)
    * @return the range of ch projection selections
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.ChProjectionSelection> getChProjectionSelections(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _chProjectionSelectionLocalService.getChProjectionSelections(start,
            end);
    }

    /**
    * Returns the number of ch projection selections.
    *
    * @return the number of ch projection selections
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getChProjectionSelectionsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _chProjectionSelectionLocalService.getChProjectionSelectionsCount();
    }

    /**
    * Updates the ch projection selection in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param chProjectionSelection the ch projection selection
    * @return the ch projection selection that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ChProjectionSelection updateChProjectionSelection(
        com.stpl.app.model.ChProjectionSelection chProjectionSelection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _chProjectionSelectionLocalService.updateChProjectionSelection(chProjectionSelection);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _chProjectionSelectionLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _chProjectionSelectionLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _chProjectionSelectionLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ChProjectionSelectionLocalService getWrappedChProjectionSelectionLocalService() {
        return _chProjectionSelectionLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedChProjectionSelectionLocalService(
        ChProjectionSelectionLocalService chProjectionSelectionLocalService) {
        _chProjectionSelectionLocalService = chProjectionSelectionLocalService;
    }

    @Override
    public ChProjectionSelectionLocalService getWrappedService() {
        return _chProjectionSelectionLocalService;
    }

    @Override
    public void setWrappedService(
        ChProjectionSelectionLocalService chProjectionSelectionLocalService) {
        _chProjectionSelectionLocalService = chProjectionSelectionLocalService;
    }
}
