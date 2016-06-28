package com.stpl.app.service.persistence;

import com.stpl.app.model.ChProjectionDiscount;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the ch projection discount service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ChProjectionDiscountPersistenceImpl
 * @see ChProjectionDiscountUtil
 * @generated
 */
public interface ChProjectionDiscountPersistence extends BasePersistence<ChProjectionDiscount> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ChProjectionDiscountUtil} to access the ch projection discount persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the ch projection discount in the entity cache if it is enabled.
    *
    * @param chProjectionDiscount the ch projection discount
    */
    public void cacheResult(
        com.stpl.app.model.ChProjectionDiscount chProjectionDiscount);

    /**
    * Caches the ch projection discounts in the entity cache if it is enabled.
    *
    * @param chProjectionDiscounts the ch projection discounts
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.ChProjectionDiscount> chProjectionDiscounts);

    /**
    * Creates a new ch projection discount with the primary key. Does not add the ch projection discount to the database.
    *
    * @param chProjectionDiscountPK the primary key for the new ch projection discount
    * @return the new ch projection discount
    */
    public com.stpl.app.model.ChProjectionDiscount create(
        ChProjectionDiscountPK chProjectionDiscountPK);

    /**
    * Removes the ch projection discount with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param chProjectionDiscountPK the primary key of the ch projection discount
    * @return the ch projection discount that was removed
    * @throws com.stpl.app.NoSuchChProjectionDiscountException if a ch projection discount with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ChProjectionDiscount remove(
        ChProjectionDiscountPK chProjectionDiscountPK)
        throws com.stpl.app.NoSuchChProjectionDiscountException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.ChProjectionDiscount updateImpl(
        com.stpl.app.model.ChProjectionDiscount chProjectionDiscount)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ch projection discount with the primary key or throws a {@link com.stpl.app.NoSuchChProjectionDiscountException} if it could not be found.
    *
    * @param chProjectionDiscountPK the primary key of the ch projection discount
    * @return the ch projection discount
    * @throws com.stpl.app.NoSuchChProjectionDiscountException if a ch projection discount with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ChProjectionDiscount findByPrimaryKey(
        ChProjectionDiscountPK chProjectionDiscountPK)
        throws com.stpl.app.NoSuchChProjectionDiscountException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ch projection discount with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param chProjectionDiscountPK the primary key of the ch projection discount
    * @return the ch projection discount, or <code>null</code> if a ch projection discount with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ChProjectionDiscount fetchByPrimaryKey(
        ChProjectionDiscountPK chProjectionDiscountPK)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the ch projection discounts.
    *
    * @return the ch projection discounts
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ChProjectionDiscount> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ch projection discounts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChProjectionDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ch projection discounts
    * @param end the upper bound of the range of ch projection discounts (not inclusive)
    * @return the range of ch projection discounts
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ChProjectionDiscount> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ch projection discounts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChProjectionDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ch projection discounts
    * @param end the upper bound of the range of ch projection discounts (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ch projection discounts
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ChProjectionDiscount> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the ch projection discounts from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ch projection discounts.
    *
    * @return the number of ch projection discounts
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
