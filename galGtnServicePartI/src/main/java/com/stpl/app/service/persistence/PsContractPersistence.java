package com.stpl.app.service.persistence;

import com.stpl.app.model.PsContract;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the ps contract service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see PsContractPersistenceImpl
 * @see PsContractUtil
 * @generated
 */
public interface PsContractPersistence extends BasePersistence<PsContract> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link PsContractUtil} to access the ps contract persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the ps contract in the entity cache if it is enabled.
    *
    * @param psContract the ps contract
    */
    public void cacheResult(com.stpl.app.model.PsContract psContract);

    /**
    * Caches the ps contracts in the entity cache if it is enabled.
    *
    * @param psContracts the ps contracts
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.PsContract> psContracts);

    /**
    * Creates a new ps contract with the primary key. Does not add the ps contract to the database.
    *
    * @param psContractSid the primary key for the new ps contract
    * @return the new ps contract
    */
    public com.stpl.app.model.PsContract create(int psContractSid);

    /**
    * Removes the ps contract with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param psContractSid the primary key of the ps contract
    * @return the ps contract that was removed
    * @throws com.stpl.app.NoSuchPsContractException if a ps contract with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.PsContract remove(int psContractSid)
        throws com.stpl.app.NoSuchPsContractException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.PsContract updateImpl(
        com.stpl.app.model.PsContract psContract)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ps contract with the primary key or throws a {@link com.stpl.app.NoSuchPsContractException} if it could not be found.
    *
    * @param psContractSid the primary key of the ps contract
    * @return the ps contract
    * @throws com.stpl.app.NoSuchPsContractException if a ps contract with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.PsContract findByPrimaryKey(int psContractSid)
        throws com.stpl.app.NoSuchPsContractException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ps contract with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param psContractSid the primary key of the ps contract
    * @return the ps contract, or <code>null</code> if a ps contract with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.PsContract fetchByPrimaryKey(int psContractSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the ps contracts.
    *
    * @return the ps contracts
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.PsContract> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ps contracts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ps contracts
    * @param end the upper bound of the range of ps contracts (not inclusive)
    * @return the range of ps contracts
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.PsContract> findAll(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ps contracts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ps contracts
    * @param end the upper bound of the range of ps contracts (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ps contracts
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.PsContract> findAll(int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the ps contracts from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ps contracts.
    *
    * @return the number of ps contracts
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
