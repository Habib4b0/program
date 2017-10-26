package com.stpl.app.service.persistence;

import com.stpl.app.model.StChActualDiscount;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the st ch actual discount service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StChActualDiscountPersistenceImpl
 * @see StChActualDiscountUtil
 * @generated
 */
public interface StChActualDiscountPersistence extends BasePersistence<StChActualDiscount> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link StChActualDiscountUtil} to access the st ch actual discount persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the st ch actual discount in the entity cache if it is enabled.
    *
    * @param stChActualDiscount the st ch actual discount
    */
    public void cacheResult(
        com.stpl.app.model.StChActualDiscount stChActualDiscount);

    /**
    * Caches the st ch actual discounts in the entity cache if it is enabled.
    *
    * @param stChActualDiscounts the st ch actual discounts
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.StChActualDiscount> stChActualDiscounts);

    /**
    * Creates a new st ch actual discount with the primary key. Does not add the st ch actual discount to the database.
    *
    * @param stChActualDiscountPK the primary key for the new st ch actual discount
    * @return the new st ch actual discount
    */
    public com.stpl.app.model.StChActualDiscount create(
        StChActualDiscountPK stChActualDiscountPK);

    /**
    * Removes the st ch actual discount with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stChActualDiscountPK the primary key of the st ch actual discount
    * @return the st ch actual discount that was removed
    * @throws com.stpl.app.NoSuchStChActualDiscountException if a st ch actual discount with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.StChActualDiscount remove(
        StChActualDiscountPK stChActualDiscountPK)
        throws com.stpl.app.NoSuchStChActualDiscountException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.StChActualDiscount updateImpl(
        com.stpl.app.model.StChActualDiscount stChActualDiscount)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the st ch actual discount with the primary key or throws a {@link com.stpl.app.NoSuchStChActualDiscountException} if it could not be found.
    *
    * @param stChActualDiscountPK the primary key of the st ch actual discount
    * @return the st ch actual discount
    * @throws com.stpl.app.NoSuchStChActualDiscountException if a st ch actual discount with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.StChActualDiscount findByPrimaryKey(
        StChActualDiscountPK stChActualDiscountPK)
        throws com.stpl.app.NoSuchStChActualDiscountException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the st ch actual discount with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param stChActualDiscountPK the primary key of the st ch actual discount
    * @return the st ch actual discount, or <code>null</code> if a st ch actual discount with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.StChActualDiscount fetchByPrimaryKey(
        StChActualDiscountPK stChActualDiscountPK)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the st ch actual discounts.
    *
    * @return the st ch actual discounts
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.StChActualDiscount> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the st ch actual discounts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StChActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st ch actual discounts
    * @param end the upper bound of the range of st ch actual discounts (not inclusive)
    * @return the range of st ch actual discounts
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.StChActualDiscount> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the st ch actual discounts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StChActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st ch actual discounts
    * @param end the upper bound of the range of st ch actual discounts (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of st ch actual discounts
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.StChActualDiscount> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the st ch actual discounts from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of st ch actual discounts.
    *
    * @return the number of st ch actual discounts
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
