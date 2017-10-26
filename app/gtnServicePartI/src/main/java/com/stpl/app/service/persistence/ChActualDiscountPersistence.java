package com.stpl.app.service.persistence;

import com.stpl.app.model.ChActualDiscount;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the ch actual discount service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ChActualDiscountPersistenceImpl
 * @see ChActualDiscountUtil
 * @generated
 */
public interface ChActualDiscountPersistence extends BasePersistence<ChActualDiscount> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ChActualDiscountUtil} to access the ch actual discount persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the ch actual discount in the entity cache if it is enabled.
    *
    * @param chActualDiscount the ch actual discount
    */
    public void cacheResult(
        com.stpl.app.model.ChActualDiscount chActualDiscount);

    /**
    * Caches the ch actual discounts in the entity cache if it is enabled.
    *
    * @param chActualDiscounts the ch actual discounts
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.ChActualDiscount> chActualDiscounts);

    /**
    * Creates a new ch actual discount with the primary key. Does not add the ch actual discount to the database.
    *
    * @param chActualDiscountPK the primary key for the new ch actual discount
    * @return the new ch actual discount
    */
    public com.stpl.app.model.ChActualDiscount create(
        ChActualDiscountPK chActualDiscountPK);

    /**
    * Removes the ch actual discount with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param chActualDiscountPK the primary key of the ch actual discount
    * @return the ch actual discount that was removed
    * @throws com.stpl.app.NoSuchChActualDiscountException if a ch actual discount with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ChActualDiscount remove(
        ChActualDiscountPK chActualDiscountPK)
        throws com.stpl.app.NoSuchChActualDiscountException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.ChActualDiscount updateImpl(
        com.stpl.app.model.ChActualDiscount chActualDiscount)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ch actual discount with the primary key or throws a {@link com.stpl.app.NoSuchChActualDiscountException} if it could not be found.
    *
    * @param chActualDiscountPK the primary key of the ch actual discount
    * @return the ch actual discount
    * @throws com.stpl.app.NoSuchChActualDiscountException if a ch actual discount with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ChActualDiscount findByPrimaryKey(
        ChActualDiscountPK chActualDiscountPK)
        throws com.stpl.app.NoSuchChActualDiscountException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ch actual discount with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param chActualDiscountPK the primary key of the ch actual discount
    * @return the ch actual discount, or <code>null</code> if a ch actual discount with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ChActualDiscount fetchByPrimaryKey(
        ChActualDiscountPK chActualDiscountPK)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the ch actual discounts.
    *
    * @return the ch actual discounts
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ChActualDiscount> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ch actual discounts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ch actual discounts
    * @param end the upper bound of the range of ch actual discounts (not inclusive)
    * @return the range of ch actual discounts
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ChActualDiscount> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ch actual discounts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ch actual discounts
    * @param end the upper bound of the range of ch actual discounts (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ch actual discounts
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ChActualDiscount> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the ch actual discounts from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ch actual discounts.
    *
    * @return the number of ch actual discounts
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
