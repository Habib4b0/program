package com.stpl.app.service.persistence;

import com.stpl.app.model.StNmDiscountProjection;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the st nm discount projection service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StNmDiscountProjectionPersistenceImpl
 * @see StNmDiscountProjectionUtil
 * @generated
 */
public interface StNmDiscountProjectionPersistence extends BasePersistence<StNmDiscountProjection> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link StNmDiscountProjectionUtil} to access the st nm discount projection persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the st nm discount projection in the entity cache if it is enabled.
    *
    * @param stNmDiscountProjection the st nm discount projection
    */
    public void cacheResult(
        com.stpl.app.model.StNmDiscountProjection stNmDiscountProjection);

    /**
    * Caches the st nm discount projections in the entity cache if it is enabled.
    *
    * @param stNmDiscountProjections the st nm discount projections
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.StNmDiscountProjection> stNmDiscountProjections);

    /**
    * Creates a new st nm discount projection with the primary key. Does not add the st nm discount projection to the database.
    *
    * @param stNmDiscountProjectionPK the primary key for the new st nm discount projection
    * @return the new st nm discount projection
    */
    public com.stpl.app.model.StNmDiscountProjection create(
        StNmDiscountProjectionPK stNmDiscountProjectionPK);

    /**
    * Removes the st nm discount projection with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stNmDiscountProjectionPK the primary key of the st nm discount projection
    * @return the st nm discount projection that was removed
    * @throws com.stpl.app.NoSuchStNmDiscountProjectionException if a st nm discount projection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.StNmDiscountProjection remove(
        StNmDiscountProjectionPK stNmDiscountProjectionPK)
        throws com.stpl.app.NoSuchStNmDiscountProjectionException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.StNmDiscountProjection updateImpl(
        com.stpl.app.model.StNmDiscountProjection stNmDiscountProjection)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the st nm discount projection with the primary key or throws a {@link com.stpl.app.NoSuchStNmDiscountProjectionException} if it could not be found.
    *
    * @param stNmDiscountProjectionPK the primary key of the st nm discount projection
    * @return the st nm discount projection
    * @throws com.stpl.app.NoSuchStNmDiscountProjectionException if a st nm discount projection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.StNmDiscountProjection findByPrimaryKey(
        StNmDiscountProjectionPK stNmDiscountProjectionPK)
        throws com.stpl.app.NoSuchStNmDiscountProjectionException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the st nm discount projection with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param stNmDiscountProjectionPK the primary key of the st nm discount projection
    * @return the st nm discount projection, or <code>null</code> if a st nm discount projection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.StNmDiscountProjection fetchByPrimaryKey(
        StNmDiscountProjectionPK stNmDiscountProjectionPK)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the st nm discount projections.
    *
    * @return the st nm discount projections
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.StNmDiscountProjection> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the st nm discount projections.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmDiscountProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st nm discount projections
    * @param end the upper bound of the range of st nm discount projections (not inclusive)
    * @return the range of st nm discount projections
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.StNmDiscountProjection> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the st nm discount projections.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmDiscountProjectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st nm discount projections
    * @param end the upper bound of the range of st nm discount projections (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of st nm discount projections
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.StNmDiscountProjection> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the st nm discount projections from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of st nm discount projections.
    *
    * @return the number of st nm discount projections
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
