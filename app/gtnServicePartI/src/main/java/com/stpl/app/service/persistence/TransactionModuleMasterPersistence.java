package com.stpl.app.service.persistence;

import com.stpl.app.model.TransactionModuleMaster;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the transaction module master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see TransactionModuleMasterPersistenceImpl
 * @see TransactionModuleMasterUtil
 * @generated
 */
public interface TransactionModuleMasterPersistence extends BasePersistence<TransactionModuleMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link TransactionModuleMasterUtil} to access the transaction module master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the transaction module master in the entity cache if it is enabled.
    *
    * @param transactionModuleMaster the transaction module master
    */
    public void cacheResult(
        com.stpl.app.model.TransactionModuleMaster transactionModuleMaster);

    /**
    * Caches the transaction module masters in the entity cache if it is enabled.
    *
    * @param transactionModuleMasters the transaction module masters
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.TransactionModuleMaster> transactionModuleMasters);

    /**
    * Creates a new transaction module master with the primary key. Does not add the transaction module master to the database.
    *
    * @param transactionModuleMasterSid the primary key for the new transaction module master
    * @return the new transaction module master
    */
    public com.stpl.app.model.TransactionModuleMaster create(
        int transactionModuleMasterSid);

    /**
    * Removes the transaction module master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param transactionModuleMasterSid the primary key of the transaction module master
    * @return the transaction module master that was removed
    * @throws com.stpl.app.NoSuchTransactionModuleMasterException if a transaction module master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.TransactionModuleMaster remove(
        int transactionModuleMasterSid)
        throws com.stpl.app.NoSuchTransactionModuleMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.TransactionModuleMaster updateImpl(
        com.stpl.app.model.TransactionModuleMaster transactionModuleMaster)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the transaction module master with the primary key or throws a {@link com.stpl.app.NoSuchTransactionModuleMasterException} if it could not be found.
    *
    * @param transactionModuleMasterSid the primary key of the transaction module master
    * @return the transaction module master
    * @throws com.stpl.app.NoSuchTransactionModuleMasterException if a transaction module master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.TransactionModuleMaster findByPrimaryKey(
        int transactionModuleMasterSid)
        throws com.stpl.app.NoSuchTransactionModuleMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the transaction module master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param transactionModuleMasterSid the primary key of the transaction module master
    * @return the transaction module master, or <code>null</code> if a transaction module master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.TransactionModuleMaster fetchByPrimaryKey(
        int transactionModuleMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the transaction module masters.
    *
    * @return the transaction module masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.TransactionModuleMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the transaction module masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.TransactionModuleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of transaction module masters
    * @param end the upper bound of the range of transaction module masters (not inclusive)
    * @return the range of transaction module masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.TransactionModuleMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the transaction module masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.TransactionModuleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of transaction module masters
    * @param end the upper bound of the range of transaction module masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of transaction module masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.TransactionModuleMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the transaction module masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of transaction module masters.
    *
    * @return the number of transaction module masters
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
