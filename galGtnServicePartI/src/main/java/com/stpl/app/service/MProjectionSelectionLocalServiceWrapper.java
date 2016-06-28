package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MProjectionSelectionLocalService}.
 *
 * @author
 * @see MProjectionSelectionLocalService
 * @generated
 */
public class MProjectionSelectionLocalServiceWrapper
    implements MProjectionSelectionLocalService,
        ServiceWrapper<MProjectionSelectionLocalService> {
    private MProjectionSelectionLocalService _mProjectionSelectionLocalService;

    public MProjectionSelectionLocalServiceWrapper(
        MProjectionSelectionLocalService mProjectionSelectionLocalService) {
        _mProjectionSelectionLocalService = mProjectionSelectionLocalService;
    }

    /**
    * Adds the m projection selection to the database. Also notifies the appropriate model listeners.
    *
    * @param mProjectionSelection the m projection selection
    * @return the m projection selection that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.MProjectionSelection addMProjectionSelection(
        com.stpl.app.model.MProjectionSelection mProjectionSelection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _mProjectionSelectionLocalService.addMProjectionSelection(mProjectionSelection);
    }

    /**
    * Creates a new m projection selection with the primary key. Does not add the m projection selection to the database.
    *
    * @param mProjectionSelectionSid the primary key for the new m projection selection
    * @return the new m projection selection
    */
    @Override
    public com.stpl.app.model.MProjectionSelection createMProjectionSelection(
        int mProjectionSelectionSid) {
        return _mProjectionSelectionLocalService.createMProjectionSelection(mProjectionSelectionSid);
    }

    /**
    * Deletes the m projection selection with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param mProjectionSelectionSid the primary key of the m projection selection
    * @return the m projection selection that was removed
    * @throws PortalException if a m projection selection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.MProjectionSelection deleteMProjectionSelection(
        int mProjectionSelectionSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _mProjectionSelectionLocalService.deleteMProjectionSelection(mProjectionSelectionSid);
    }

    /**
    * Deletes the m projection selection from the database. Also notifies the appropriate model listeners.
    *
    * @param mProjectionSelection the m projection selection
    * @return the m projection selection that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.MProjectionSelection deleteMProjectionSelection(
        com.stpl.app.model.MProjectionSelection mProjectionSelection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _mProjectionSelectionLocalService.deleteMProjectionSelection(mProjectionSelection);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _mProjectionSelectionLocalService.dynamicQuery();
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
        return _mProjectionSelectionLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _mProjectionSelectionLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _mProjectionSelectionLocalService.dynamicQuery(dynamicQuery,
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
        return _mProjectionSelectionLocalService.dynamicQueryCount(dynamicQuery);
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
        return _mProjectionSelectionLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.MProjectionSelection fetchMProjectionSelection(
        int mProjectionSelectionSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _mProjectionSelectionLocalService.fetchMProjectionSelection(mProjectionSelectionSid);
    }

    /**
    * Returns the m projection selection with the primary key.
    *
    * @param mProjectionSelectionSid the primary key of the m projection selection
    * @return the m projection selection
    * @throws PortalException if a m projection selection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.MProjectionSelection getMProjectionSelection(
        int mProjectionSelectionSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _mProjectionSelectionLocalService.getMProjectionSelection(mProjectionSelectionSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _mProjectionSelectionLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the m projection selections.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of m projection selections
    * @param end the upper bound of the range of m projection selections (not inclusive)
    * @return the range of m projection selections
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.MProjectionSelection> getMProjectionSelections(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _mProjectionSelectionLocalService.getMProjectionSelections(start,
            end);
    }

    /**
    * Returns the number of m projection selections.
    *
    * @return the number of m projection selections
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getMProjectionSelectionsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _mProjectionSelectionLocalService.getMProjectionSelectionsCount();
    }

    /**
    * Updates the m projection selection in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param mProjectionSelection the m projection selection
    * @return the m projection selection that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.MProjectionSelection updateMProjectionSelection(
        com.stpl.app.model.MProjectionSelection mProjectionSelection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _mProjectionSelectionLocalService.updateMProjectionSelection(mProjectionSelection);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _mProjectionSelectionLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _mProjectionSelectionLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _mProjectionSelectionLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public MProjectionSelectionLocalService getWrappedMProjectionSelectionLocalService() {
        return _mProjectionSelectionLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedMProjectionSelectionLocalService(
        MProjectionSelectionLocalService mProjectionSelectionLocalService) {
        _mProjectionSelectionLocalService = mProjectionSelectionLocalService;
    }

    @Override
    public MProjectionSelectionLocalService getWrappedService() {
        return _mProjectionSelectionLocalService;
    }

    @Override
    public void setWrappedService(
        MProjectionSelectionLocalService mProjectionSelectionLocalService) {
        _mProjectionSelectionLocalService = mProjectionSelectionLocalService;
    }
}
