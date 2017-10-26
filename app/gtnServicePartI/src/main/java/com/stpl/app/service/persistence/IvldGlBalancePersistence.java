package com.stpl.app.service.persistence;

import com.stpl.app.model.IvldGlBalance;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the ivld gl balance service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldGlBalancePersistenceImpl
 * @see IvldGlBalanceUtil
 * @generated
 */
public interface IvldGlBalancePersistence extends BasePersistence<IvldGlBalance> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link IvldGlBalanceUtil} to access the ivld gl balance persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the ivld gl balance in the entity cache if it is enabled.
    *
    * @param ivldGlBalance the ivld gl balance
    */
    public void cacheResult(com.stpl.app.model.IvldGlBalance ivldGlBalance);

    /**
    * Caches the ivld gl balances in the entity cache if it is enabled.
    *
    * @param ivldGlBalances the ivld gl balances
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.IvldGlBalance> ivldGlBalances);

    /**
    * Creates a new ivld gl balance with the primary key. Does not add the ivld gl balance to the database.
    *
    * @param ivldGlBalanceSid the primary key for the new ivld gl balance
    * @return the new ivld gl balance
    */
    public com.stpl.app.model.IvldGlBalance create(int ivldGlBalanceSid);

    /**
    * Removes the ivld gl balance with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldGlBalanceSid the primary key of the ivld gl balance
    * @return the ivld gl balance that was removed
    * @throws com.stpl.app.NoSuchIvldGlBalanceException if a ivld gl balance with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IvldGlBalance remove(int ivldGlBalanceSid)
        throws com.stpl.app.NoSuchIvldGlBalanceException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.IvldGlBalance updateImpl(
        com.stpl.app.model.IvldGlBalance ivldGlBalance)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ivld gl balance with the primary key or throws a {@link com.stpl.app.NoSuchIvldGlBalanceException} if it could not be found.
    *
    * @param ivldGlBalanceSid the primary key of the ivld gl balance
    * @return the ivld gl balance
    * @throws com.stpl.app.NoSuchIvldGlBalanceException if a ivld gl balance with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IvldGlBalance findByPrimaryKey(
        int ivldGlBalanceSid)
        throws com.stpl.app.NoSuchIvldGlBalanceException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ivld gl balance with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ivldGlBalanceSid the primary key of the ivld gl balance
    * @return the ivld gl balance, or <code>null</code> if a ivld gl balance with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IvldGlBalance fetchByPrimaryKey(
        int ivldGlBalanceSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the ivld gl balances.
    *
    * @return the ivld gl balances
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IvldGlBalance> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ivld gl balances.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldGlBalanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld gl balances
    * @param end the upper bound of the range of ivld gl balances (not inclusive)
    * @return the range of ivld gl balances
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IvldGlBalance> findAll(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ivld gl balances.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldGlBalanceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld gl balances
    * @param end the upper bound of the range of ivld gl balances (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ivld gl balances
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IvldGlBalance> findAll(int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the ivld gl balances from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ivld gl balances.
    *
    * @return the number of ivld gl balances
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
