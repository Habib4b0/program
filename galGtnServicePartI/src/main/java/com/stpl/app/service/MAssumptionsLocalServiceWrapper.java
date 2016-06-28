package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MAssumptionsLocalService}.
 *
 * @author
 * @see MAssumptionsLocalService
 * @generated
 */
public class MAssumptionsLocalServiceWrapper implements MAssumptionsLocalService,
    ServiceWrapper<MAssumptionsLocalService> {
    private MAssumptionsLocalService _mAssumptionsLocalService;

    public MAssumptionsLocalServiceWrapper(
        MAssumptionsLocalService mAssumptionsLocalService) {
        _mAssumptionsLocalService = mAssumptionsLocalService;
    }

    /**
    * Adds the m assumptions to the database. Also notifies the appropriate model listeners.
    *
    * @param mAssumptions the m assumptions
    * @return the m assumptions that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.MAssumptions addMAssumptions(
        com.stpl.app.model.MAssumptions mAssumptions)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _mAssumptionsLocalService.addMAssumptions(mAssumptions);
    }

    /**
    * Creates a new m assumptions with the primary key. Does not add the m assumptions to the database.
    *
    * @param mAssumptionsSid the primary key for the new m assumptions
    * @return the new m assumptions
    */
    @Override
    public com.stpl.app.model.MAssumptions createMAssumptions(
        int mAssumptionsSid) {
        return _mAssumptionsLocalService.createMAssumptions(mAssumptionsSid);
    }

    /**
    * Deletes the m assumptions with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param mAssumptionsSid the primary key of the m assumptions
    * @return the m assumptions that was removed
    * @throws PortalException if a m assumptions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.MAssumptions deleteMAssumptions(
        int mAssumptionsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _mAssumptionsLocalService.deleteMAssumptions(mAssumptionsSid);
    }

    /**
    * Deletes the m assumptions from the database. Also notifies the appropriate model listeners.
    *
    * @param mAssumptions the m assumptions
    * @return the m assumptions that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.MAssumptions deleteMAssumptions(
        com.stpl.app.model.MAssumptions mAssumptions)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _mAssumptionsLocalService.deleteMAssumptions(mAssumptions);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _mAssumptionsLocalService.dynamicQuery();
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
        return _mAssumptionsLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _mAssumptionsLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _mAssumptionsLocalService.dynamicQuery(dynamicQuery, start, end,
            orderByComparator);
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
        return _mAssumptionsLocalService.dynamicQueryCount(dynamicQuery);
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
        return _mAssumptionsLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.MAssumptions fetchMAssumptions(
        int mAssumptionsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _mAssumptionsLocalService.fetchMAssumptions(mAssumptionsSid);
    }

    /**
    * Returns the m assumptions with the primary key.
    *
    * @param mAssumptionsSid the primary key of the m assumptions
    * @return the m assumptions
    * @throws PortalException if a m assumptions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.MAssumptions getMAssumptions(int mAssumptionsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _mAssumptionsLocalService.getMAssumptions(mAssumptionsSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _mAssumptionsLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the m assumptionses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of m assumptionses
    * @param end the upper bound of the range of m assumptionses (not inclusive)
    * @return the range of m assumptionses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.MAssumptions> getMAssumptionses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _mAssumptionsLocalService.getMAssumptionses(start, end);
    }

    /**
    * Returns the number of m assumptionses.
    *
    * @return the number of m assumptionses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getMAssumptionsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _mAssumptionsLocalService.getMAssumptionsesCount();
    }

    /**
    * Updates the m assumptions in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param mAssumptions the m assumptions
    * @return the m assumptions that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.MAssumptions updateMAssumptions(
        com.stpl.app.model.MAssumptions mAssumptions)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _mAssumptionsLocalService.updateMAssumptions(mAssumptions);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _mAssumptionsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _mAssumptionsLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _mAssumptionsLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public MAssumptionsLocalService getWrappedMAssumptionsLocalService() {
        return _mAssumptionsLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedMAssumptionsLocalService(
        MAssumptionsLocalService mAssumptionsLocalService) {
        _mAssumptionsLocalService = mAssumptionsLocalService;
    }

    @Override
    public MAssumptionsLocalService getWrappedService() {
        return _mAssumptionsLocalService;
    }

    @Override
    public void setWrappedService(
        MAssumptionsLocalService mAssumptionsLocalService) {
        _mAssumptionsLocalService = mAssumptionsLocalService;
    }
}
