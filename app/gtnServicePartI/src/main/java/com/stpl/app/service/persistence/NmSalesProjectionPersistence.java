package com.stpl.app.service.persistence;

import com.stpl.app.model.NmSalesProjection;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the nm sales projection service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NmSalesProjectionPersistenceImpl
 * @see NmSalesProjectionUtil
 * @generated
 */
public interface NmSalesProjectionPersistence extends BasePersistence<NmSalesProjection> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link NmSalesProjectionUtil} to access the nm sales projection persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the nm sales projection in the entity cache if it is enabled.
    *
    * @param nmSalesProjection the nm sales projection
    */
    public void cacheResult(
        com.stpl.app.model.NmSalesProjection nmSalesProjection);

    /**
    * Caches the nm sales projections in the entity cache if it is enabled.
    *
    * @param nmSalesProjections the nm sales projections
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.NmSalesProjection> nmSalesProjections);

    /**
    * Creates a new nm sales projection with the primary key. Does not add the nm sales projection to the database.
    *
    * @param nmSalesProjectionPK the primary key for the new nm sales projection
    * @return the new nm sales projection
    */
    public com.stpl.app.model.NmSalesProjection create(
        NmSalesProjectionPK nmSalesProjectionPK);

    /**
    * Removes the nm sales projection with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param nmSalesProjectionPK the primary key of the nm sales projection
    * @return the nm sales projection that was removed
    * @throws com.stpl.app.NoSuchNmSalesProjectionException if a nm sales projection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.NmSalesProjection remove(
        NmSalesProjectionPK nmSalesProjectionPK)
        throws com.stpl.app.NoSuchNmSalesProjectionException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.NmSalesProjection updateImpl(
        com.stpl.app.model.NmSalesProjection nmSalesProjection)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the nm sales projection with the primary key or throws a {@link com.stpl.app.NoSuchNmSalesProjectionException} if it could not be found.
    *
    * @param nmSalesProjectionPK the primary key of the nm sales projection
    * @return the nm sales projection
    * @throws com.stpl.app.NoSuchNmSalesProjectionException if a nm sales projection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.NmSalesProjection findByPrimaryKey(
        NmSalesProjectionPK nmSalesProjectionPK)
        throws com.stpl.app.NoSuchNmSalesProjectionException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the nm sales projection with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param nmSalesProjectionPK the primary key of the nm sales projection
    * @return the nm sales projection, or <code>null</code> if a nm sales projection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.NmSalesProjection fetchByPrimaryKey(
        NmSalesProjectionPK nmSalesProjectionPK)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the nm sales projections.
    *
    * @return the nm sales projections
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.NmSalesProjection> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the nm sales projections.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmSalesProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of nm sales projections
    * @param end the upper bound of the range of nm sales projections (not inclusive)
    * @return the range of nm sales projections
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.NmSalesProjection> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the nm sales projections.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmSalesProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of nm sales projections
    * @param end the upper bound of the range of nm sales projections (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of nm sales projections
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.NmSalesProjection> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the nm sales projections from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of nm sales projections.
    *
    * @return the number of nm sales projections
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
