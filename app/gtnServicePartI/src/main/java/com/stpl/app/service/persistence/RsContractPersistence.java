package com.stpl.app.service.persistence;

import com.stpl.app.model.RsContract;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the rs contract service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see RsContractPersistenceImpl
 * @see RsContractUtil
 * @generated
 */
public interface RsContractPersistence extends BasePersistence<RsContract> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link RsContractUtil} to access the rs contract persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the rs contract in the entity cache if it is enabled.
    *
    * @param rsContract the rs contract
    */
    public void cacheResult(com.stpl.app.model.RsContract rsContract);

    /**
    * Caches the rs contracts in the entity cache if it is enabled.
    *
    * @param rsContracts the rs contracts
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.RsContract> rsContracts);

    /**
    * Creates a new rs contract with the primary key. Does not add the rs contract to the database.
    *
    * @param rsContractSid the primary key for the new rs contract
    * @return the new rs contract
    */
    public com.stpl.app.model.RsContract create(int rsContractSid);

    /**
    * Removes the rs contract with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param rsContractSid the primary key of the rs contract
    * @return the rs contract that was removed
    * @throws com.stpl.app.NoSuchRsContractException if a rs contract with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RsContract remove(int rsContractSid)
        throws com.stpl.app.NoSuchRsContractException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.RsContract updateImpl(
        com.stpl.app.model.RsContract rsContract)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the rs contract with the primary key or throws a {@link com.stpl.app.NoSuchRsContractException} if it could not be found.
    *
    * @param rsContractSid the primary key of the rs contract
    * @return the rs contract
    * @throws com.stpl.app.NoSuchRsContractException if a rs contract with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RsContract findByPrimaryKey(int rsContractSid)
        throws com.stpl.app.NoSuchRsContractException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the rs contract with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param rsContractSid the primary key of the rs contract
    * @return the rs contract, or <code>null</code> if a rs contract with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RsContract fetchByPrimaryKey(int rsContractSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the rs contracts.
    *
    * @return the rs contracts
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RsContract> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the rs contracts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of rs contracts
    * @param end the upper bound of the range of rs contracts (not inclusive)
    * @return the range of rs contracts
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RsContract> findAll(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the rs contracts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of rs contracts
    * @param end the upper bound of the range of rs contracts (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of rs contracts
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RsContract> findAll(int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the rs contracts from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of rs contracts.
    *
    * @return the number of rs contracts
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
