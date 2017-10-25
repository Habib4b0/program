package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link MSupplementalDiscActualsLocalService}.
 *
 * @author
 * @see MSupplementalDiscActualsLocalService
 * @generated
 */
public class MSupplementalDiscActualsLocalServiceWrapper
    implements MSupplementalDiscActualsLocalService,
        ServiceWrapper<MSupplementalDiscActualsLocalService> {
    private MSupplementalDiscActualsLocalService _mSupplementalDiscActualsLocalService;

    public MSupplementalDiscActualsLocalServiceWrapper(
        MSupplementalDiscActualsLocalService mSupplementalDiscActualsLocalService) {
        _mSupplementalDiscActualsLocalService = mSupplementalDiscActualsLocalService;
    }

    /**
    * Adds the m supplemental disc actuals to the database. Also notifies the appropriate model listeners.
    *
    * @param mSupplementalDiscActuals the m supplemental disc actuals
    * @return the m supplemental disc actuals that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.MSupplementalDiscActuals addMSupplementalDiscActuals(
        com.stpl.app.model.MSupplementalDiscActuals mSupplementalDiscActuals)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _mSupplementalDiscActualsLocalService.addMSupplementalDiscActuals(mSupplementalDiscActuals);
    }

    /**
    * Creates a new m supplemental disc actuals with the primary key. Does not add the m supplemental disc actuals to the database.
    *
    * @param mSupplementalDiscActualsPK the primary key for the new m supplemental disc actuals
    * @return the new m supplemental disc actuals
    */
    @Override
    public com.stpl.app.model.MSupplementalDiscActuals createMSupplementalDiscActuals(
        com.stpl.app.service.persistence.MSupplementalDiscActualsPK mSupplementalDiscActualsPK) {
        return _mSupplementalDiscActualsLocalService.createMSupplementalDiscActuals(mSupplementalDiscActualsPK);
    }

    /**
    * Deletes the m supplemental disc actuals with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param mSupplementalDiscActualsPK the primary key of the m supplemental disc actuals
    * @return the m supplemental disc actuals that was removed
    * @throws PortalException if a m supplemental disc actuals with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.MSupplementalDiscActuals deleteMSupplementalDiscActuals(
        com.stpl.app.service.persistence.MSupplementalDiscActualsPK mSupplementalDiscActualsPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _mSupplementalDiscActualsLocalService.deleteMSupplementalDiscActuals(mSupplementalDiscActualsPK);
    }

    /**
    * Deletes the m supplemental disc actuals from the database. Also notifies the appropriate model listeners.
    *
    * @param mSupplementalDiscActuals the m supplemental disc actuals
    * @return the m supplemental disc actuals that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.MSupplementalDiscActuals deleteMSupplementalDiscActuals(
        com.stpl.app.model.MSupplementalDiscActuals mSupplementalDiscActuals)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _mSupplementalDiscActualsLocalService.deleteMSupplementalDiscActuals(mSupplementalDiscActuals);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _mSupplementalDiscActualsLocalService.dynamicQuery();
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
        return _mSupplementalDiscActualsLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MSupplementalDiscActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _mSupplementalDiscActualsLocalService.dynamicQuery(dynamicQuery,
            start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MSupplementalDiscActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _mSupplementalDiscActualsLocalService.dynamicQuery(dynamicQuery,
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
        return _mSupplementalDiscActualsLocalService.dynamicQueryCount(dynamicQuery);
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
        return _mSupplementalDiscActualsLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.MSupplementalDiscActuals fetchMSupplementalDiscActuals(
        com.stpl.app.service.persistence.MSupplementalDiscActualsPK mSupplementalDiscActualsPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _mSupplementalDiscActualsLocalService.fetchMSupplementalDiscActuals(mSupplementalDiscActualsPK);
    }

    /**
    * Returns the m supplemental disc actuals with the primary key.
    *
    * @param mSupplementalDiscActualsPK the primary key of the m supplemental disc actuals
    * @return the m supplemental disc actuals
    * @throws PortalException if a m supplemental disc actuals with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.MSupplementalDiscActuals getMSupplementalDiscActuals(
        com.stpl.app.service.persistence.MSupplementalDiscActualsPK mSupplementalDiscActualsPK)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _mSupplementalDiscActualsLocalService.getMSupplementalDiscActuals(mSupplementalDiscActualsPK);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _mSupplementalDiscActualsLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the m supplemental disc actualses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MSupplementalDiscActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of m supplemental disc actualses
    * @param end the upper bound of the range of m supplemental disc actualses (not inclusive)
    * @return the range of m supplemental disc actualses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.MSupplementalDiscActuals> getMSupplementalDiscActualses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _mSupplementalDiscActualsLocalService.getMSupplementalDiscActualses(start,
            end);
    }

    /**
    * Returns the number of m supplemental disc actualses.
    *
    * @return the number of m supplemental disc actualses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getMSupplementalDiscActualsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _mSupplementalDiscActualsLocalService.getMSupplementalDiscActualsesCount();
    }

    /**
    * Updates the m supplemental disc actuals in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param mSupplementalDiscActuals the m supplemental disc actuals
    * @return the m supplemental disc actuals that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.MSupplementalDiscActuals updateMSupplementalDiscActuals(
        com.stpl.app.model.MSupplementalDiscActuals mSupplementalDiscActuals)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _mSupplementalDiscActualsLocalService.updateMSupplementalDiscActuals(mSupplementalDiscActuals);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _mSupplementalDiscActualsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _mSupplementalDiscActualsLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _mSupplementalDiscActualsLocalService.invokeMethod(name,
            parameterTypes, arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public MSupplementalDiscActualsLocalService getWrappedMSupplementalDiscActualsLocalService() {
        return _mSupplementalDiscActualsLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedMSupplementalDiscActualsLocalService(
        MSupplementalDiscActualsLocalService mSupplementalDiscActualsLocalService) {
        _mSupplementalDiscActualsLocalService = mSupplementalDiscActualsLocalService;
    }

    @Override
    public MSupplementalDiscActualsLocalService getWrappedService() {
        return _mSupplementalDiscActualsLocalService;
    }

    @Override
    public void setWrappedService(
        MSupplementalDiscActualsLocalService mSupplementalDiscActualsLocalService) {
        _mSupplementalDiscActualsLocalService = mSupplementalDiscActualsLocalService;
    }
}
