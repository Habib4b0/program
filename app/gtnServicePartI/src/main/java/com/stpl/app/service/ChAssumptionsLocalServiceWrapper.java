package com.stpl.app.service;

import com.stpl.portal.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ChAssumptionsLocalService}.
 *
 * @author
 * @see ChAssumptionsLocalService
 * @generated
 */
public class ChAssumptionsLocalServiceWrapper
    implements ChAssumptionsLocalService,
        ServiceWrapper<ChAssumptionsLocalService> {
    private ChAssumptionsLocalService _chAssumptionsLocalService;

    public ChAssumptionsLocalServiceWrapper(
        ChAssumptionsLocalService chAssumptionsLocalService) {
        _chAssumptionsLocalService = chAssumptionsLocalService;
    }

    /**
    * Adds the ch assumptions to the database. Also notifies the appropriate model listeners.
    *
    * @param chAssumptions the ch assumptions
    * @return the ch assumptions that was added
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ChAssumptions addChAssumptions(
        com.stpl.app.model.ChAssumptions chAssumptions)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _chAssumptionsLocalService.addChAssumptions(chAssumptions);
    }

    /**
    * Creates a new ch assumptions with the primary key. Does not add the ch assumptions to the database.
    *
    * @param chAssumptionsSid the primary key for the new ch assumptions
    * @return the new ch assumptions
    */
    @Override
    public com.stpl.app.model.ChAssumptions createChAssumptions(
        int chAssumptionsSid) {
        return _chAssumptionsLocalService.createChAssumptions(chAssumptionsSid);
    }

    /**
    * Deletes the ch assumptions with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param chAssumptionsSid the primary key of the ch assumptions
    * @return the ch assumptions that was removed
    * @throws PortalException if a ch assumptions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ChAssumptions deleteChAssumptions(
        int chAssumptionsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _chAssumptionsLocalService.deleteChAssumptions(chAssumptionsSid);
    }

    /**
    * Deletes the ch assumptions from the database. Also notifies the appropriate model listeners.
    *
    * @param chAssumptions the ch assumptions
    * @return the ch assumptions that was removed
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ChAssumptions deleteChAssumptions(
        com.stpl.app.model.ChAssumptions chAssumptions)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _chAssumptionsLocalService.deleteChAssumptions(chAssumptions);
    }

    @Override
    public com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return _chAssumptionsLocalService.dynamicQuery();
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
        return _chAssumptionsLocalService.dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _chAssumptionsLocalService.dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
        return _chAssumptionsLocalService.dynamicQuery(dynamicQuery, start,
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
        return _chAssumptionsLocalService.dynamicQueryCount(dynamicQuery);
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
        return _chAssumptionsLocalService.dynamicQueryCount(dynamicQuery,
            projection);
    }

    @Override
    public com.stpl.app.model.ChAssumptions fetchChAssumptions(
        int chAssumptionsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _chAssumptionsLocalService.fetchChAssumptions(chAssumptionsSid);
    }

    /**
    * Returns the ch assumptions with the primary key.
    *
    * @param chAssumptionsSid the primary key of the ch assumptions
    * @return the ch assumptions
    * @throws PortalException if a ch assumptions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ChAssumptions getChAssumptions(
        int chAssumptionsSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _chAssumptionsLocalService.getChAssumptions(chAssumptionsSid);
    }

    @Override
    public com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return _chAssumptionsLocalService.getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the ch assumptionses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ch assumptionses
    * @param end the upper bound of the range of ch assumptionses (not inclusive)
    * @return the range of ch assumptionses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public java.util.List<com.stpl.app.model.ChAssumptions> getChAssumptionses(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _chAssumptionsLocalService.getChAssumptionses(start, end);
    }

    /**
    * Returns the number of ch assumptionses.
    *
    * @return the number of ch assumptionses
    * @throws SystemException if a system exception occurred
    */
    @Override
    public int getChAssumptionsesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return _chAssumptionsLocalService.getChAssumptionsesCount();
    }

    /**
    * Updates the ch assumptions in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param chAssumptions the ch assumptions
    * @return the ch assumptions that was updated
    * @throws SystemException if a system exception occurred
    */
    @Override
    public com.stpl.app.model.ChAssumptions updateChAssumptions(
        com.stpl.app.model.ChAssumptions chAssumptions)
        throws com.stpl.portal.kernel.exception.SystemException {
        return _chAssumptionsLocalService.updateChAssumptions(chAssumptions);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    @Override
    public java.lang.String getBeanIdentifier() {
        return _chAssumptionsLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    @Override
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _chAssumptionsLocalService.setBeanIdentifier(beanIdentifier);
    }

    @Override
    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _chAssumptionsLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #getWrappedService}
     */
    public ChAssumptionsLocalService getWrappedChAssumptionsLocalService() {
        return _chAssumptionsLocalService;
    }

    /**
     * @deprecated As of 6.1.0, replaced by {@link #setWrappedService}
     */
    public void setWrappedChAssumptionsLocalService(
        ChAssumptionsLocalService chAssumptionsLocalService) {
        _chAssumptionsLocalService = chAssumptionsLocalService;
    }

    @Override
    public ChAssumptionsLocalService getWrappedService() {
        return _chAssumptionsLocalService;
    }

    @Override
    public void setWrappedService(
        ChAssumptionsLocalService chAssumptionsLocalService) {
        _chAssumptionsLocalService = chAssumptionsLocalService;
    }
}
