package com.stpl.app.service.persistence;

import com.stpl.app.model.NmDiscountProjection;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the nm discount projection service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NmDiscountProjectionPersistenceImpl
 * @see NmDiscountProjectionUtil
 * @generated
 */
public interface NmDiscountProjectionPersistence extends BasePersistence<NmDiscountProjection> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link NmDiscountProjectionUtil} to access the nm discount projection persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the nm discount projection in the entity cache if it is enabled.
    *
    * @param nmDiscountProjection the nm discount projection
    */
    public void cacheResult(
        com.stpl.app.model.NmDiscountProjection nmDiscountProjection);

    /**
    * Caches the nm discount projections in the entity cache if it is enabled.
    *
    * @param nmDiscountProjections the nm discount projections
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.NmDiscountProjection> nmDiscountProjections);

    /**
    * Creates a new nm discount projection with the primary key. Does not add the nm discount projection to the database.
    *
    * @param nmDiscountProjectionPK the primary key for the new nm discount projection
    * @return the new nm discount projection
    */
    public com.stpl.app.model.NmDiscountProjection create(
        NmDiscountProjectionPK nmDiscountProjectionPK);

    /**
    * Removes the nm discount projection with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param nmDiscountProjectionPK the primary key of the nm discount projection
    * @return the nm discount projection that was removed
    * @throws com.stpl.app.NoSuchNmDiscountProjectionException if a nm discount projection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.NmDiscountProjection remove(
        NmDiscountProjectionPK nmDiscountProjectionPK)
        throws com.stpl.app.NoSuchNmDiscountProjectionException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.NmDiscountProjection updateImpl(
        com.stpl.app.model.NmDiscountProjection nmDiscountProjection)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the nm discount projection with the primary key or throws a {@link com.stpl.app.NoSuchNmDiscountProjectionException} if it could not be found.
    *
    * @param nmDiscountProjectionPK the primary key of the nm discount projection
    * @return the nm discount projection
    * @throws com.stpl.app.NoSuchNmDiscountProjectionException if a nm discount projection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.NmDiscountProjection findByPrimaryKey(
        NmDiscountProjectionPK nmDiscountProjectionPK)
        throws com.stpl.app.NoSuchNmDiscountProjectionException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the nm discount projection with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param nmDiscountProjectionPK the primary key of the nm discount projection
    * @return the nm discount projection, or <code>null</code> if a nm discount projection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.NmDiscountProjection fetchByPrimaryKey(
        NmDiscountProjectionPK nmDiscountProjectionPK)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the nm discount projections.
    *
    * @return the nm discount projections
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.NmDiscountProjection> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the nm discount projections.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmDiscountProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of nm discount projections
    * @param end the upper bound of the range of nm discount projections (not inclusive)
    * @return the range of nm discount projections
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.NmDiscountProjection> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the nm discount projections.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmDiscountProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of nm discount projections
    * @param end the upper bound of the range of nm discount projections (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of nm discount projections
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.NmDiscountProjection> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the nm discount projections from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of nm discount projections.
    *
    * @return the number of nm discount projections
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
