package com.stpl.app.service.persistence;

import com.stpl.app.model.NmActualDiscount;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the nm actual discount service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NmActualDiscountPersistenceImpl
 * @see NmActualDiscountUtil
 * @generated
 */
public interface NmActualDiscountPersistence extends BasePersistence<NmActualDiscount> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link NmActualDiscountUtil} to access the nm actual discount persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the nm actual discount in the entity cache if it is enabled.
    *
    * @param nmActualDiscount the nm actual discount
    */
    public void cacheResult(
        com.stpl.app.model.NmActualDiscount nmActualDiscount);

    /**
    * Caches the nm actual discounts in the entity cache if it is enabled.
    *
    * @param nmActualDiscounts the nm actual discounts
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.NmActualDiscount> nmActualDiscounts);

    /**
    * Creates a new nm actual discount with the primary key. Does not add the nm actual discount to the database.
    *
    * @param nmActualDiscountPK the primary key for the new nm actual discount
    * @return the new nm actual discount
    */
    public com.stpl.app.model.NmActualDiscount create(
        NmActualDiscountPK nmActualDiscountPK);

    /**
    * Removes the nm actual discount with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param nmActualDiscountPK the primary key of the nm actual discount
    * @return the nm actual discount that was removed
    * @throws com.stpl.app.NoSuchNmActualDiscountException if a nm actual discount with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.NmActualDiscount remove(
        NmActualDiscountPK nmActualDiscountPK)
        throws com.stpl.app.NoSuchNmActualDiscountException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.NmActualDiscount updateImpl(
        com.stpl.app.model.NmActualDiscount nmActualDiscount)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the nm actual discount with the primary key or throws a {@link com.stpl.app.NoSuchNmActualDiscountException} if it could not be found.
    *
    * @param nmActualDiscountPK the primary key of the nm actual discount
    * @return the nm actual discount
    * @throws com.stpl.app.NoSuchNmActualDiscountException if a nm actual discount with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.NmActualDiscount findByPrimaryKey(
        NmActualDiscountPK nmActualDiscountPK)
        throws com.stpl.app.NoSuchNmActualDiscountException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the nm actual discount with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param nmActualDiscountPK the primary key of the nm actual discount
    * @return the nm actual discount, or <code>null</code> if a nm actual discount with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.NmActualDiscount fetchByPrimaryKey(
        NmActualDiscountPK nmActualDiscountPK)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the nm actual discounts.
    *
    * @return the nm actual discounts
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.NmActualDiscount> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the nm actual discounts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of nm actual discounts
    * @param end the upper bound of the range of nm actual discounts (not inclusive)
    * @return the range of nm actual discounts
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.NmActualDiscount> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the nm actual discounts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmActualDiscountModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of nm actual discounts
    * @param end the upper bound of the range of nm actual discounts (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of nm actual discounts
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.NmActualDiscount> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the nm actual discounts from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of nm actual discounts.
    *
    * @return the number of nm actual discounts
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
