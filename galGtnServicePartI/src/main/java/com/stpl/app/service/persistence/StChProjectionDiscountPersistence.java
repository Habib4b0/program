package com.stpl.app.service.persistence;

import com.stpl.app.model.StChProjectionDiscount;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the st ch projection discount service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StChProjectionDiscountPersistenceImpl
 * @see StChProjectionDiscountUtil
 * @generated
 */
public interface StChProjectionDiscountPersistence extends BasePersistence<StChProjectionDiscount> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link StChProjectionDiscountUtil} to access the st ch projection discount persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the st ch projection discount in the entity cache if it is enabled.
    *
    * @param stChProjectionDiscount the st ch projection discount
    */
    public void cacheResult(
        com.stpl.app.model.StChProjectionDiscount stChProjectionDiscount);

    /**
    * Caches the st ch projection discounts in the entity cache if it is enabled.
    *
    * @param stChProjectionDiscounts the st ch projection discounts
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.StChProjectionDiscount> stChProjectionDiscounts);

    /**
    * Creates a new st ch projection discount with the primary key. Does not add the st ch projection discount to the database.
    *
    * @param stChProjectionDiscountPK the primary key for the new st ch projection discount
    * @return the new st ch projection discount
    */
    public com.stpl.app.model.StChProjectionDiscount create(
        StChProjectionDiscountPK stChProjectionDiscountPK);

    /**
    * Removes the st ch projection discount with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stChProjectionDiscountPK the primary key of the st ch projection discount
    * @return the st ch projection discount that was removed
    * @throws com.stpl.app.NoSuchStChProjectionDiscountException if a st ch projection discount with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.StChProjectionDiscount remove(
        StChProjectionDiscountPK stChProjectionDiscountPK)
        throws com.stpl.app.NoSuchStChProjectionDiscountException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.StChProjectionDiscount updateImpl(
        com.stpl.app.model.StChProjectionDiscount stChProjectionDiscount)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the st ch projection discount with the primary key or throws a {@link com.stpl.app.NoSuchStChProjectionDiscountException} if it could not be found.
    *
    * @param stChProjectionDiscountPK the primary key of the st ch projection discount
    * @return the st ch projection discount
    * @throws com.stpl.app.NoSuchStChProjectionDiscountException if a st ch projection discount with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.StChProjectionDiscount findByPrimaryKey(
        StChProjectionDiscountPK stChProjectionDiscountPK)
        throws com.stpl.app.NoSuchStChProjectionDiscountException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the st ch projection discount with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param stChProjectionDiscountPK the primary key of the st ch projection discount
    * @return the st ch projection discount, or <code>null</code> if a st ch projection discount with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.StChProjectionDiscount fetchByPrimaryKey(
        StChProjectionDiscountPK stChProjectionDiscountPK)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the st ch projection discounts.
    *
    * @return the st ch projection discounts
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.StChProjectionDiscount> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the st ch projection discounts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StChProjectionDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st ch projection discounts
    * @param end the upper bound of the range of st ch projection discounts (not inclusive)
    * @return the range of st ch projection discounts
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.StChProjectionDiscount> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the st ch projection discounts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StChProjectionDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st ch projection discounts
    * @param end the upper bound of the range of st ch projection discounts (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of st ch projection discounts
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.StChProjectionDiscount> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the st ch projection discounts from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of st ch projection discounts.
    *
    * @return the number of st ch projection discounts
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
