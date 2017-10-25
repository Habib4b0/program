package com.stpl.app.service.persistence;

import com.stpl.app.model.IfpContract;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the ifp contract service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IfpContractPersistenceImpl
 * @see IfpContractUtil
 * @generated
 */
public interface IfpContractPersistence extends BasePersistence<IfpContract> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link IfpContractUtil} to access the ifp contract persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the ifp contract in the entity cache if it is enabled.
    *
    * @param ifpContract the ifp contract
    */
    public void cacheResult(com.stpl.app.model.IfpContract ifpContract);

    /**
    * Caches the ifp contracts in the entity cache if it is enabled.
    *
    * @param ifpContracts the ifp contracts
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.IfpContract> ifpContracts);

    /**
    * Creates a new ifp contract with the primary key. Does not add the ifp contract to the database.
    *
    * @param ifpContractSid the primary key for the new ifp contract
    * @return the new ifp contract
    */
    public com.stpl.app.model.IfpContract create(int ifpContractSid);

    /**
    * Removes the ifp contract with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ifpContractSid the primary key of the ifp contract
    * @return the ifp contract that was removed
    * @throws com.stpl.app.NoSuchIfpContractException if a ifp contract with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IfpContract remove(int ifpContractSid)
        throws com.stpl.app.NoSuchIfpContractException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.IfpContract updateImpl(
        com.stpl.app.model.IfpContract ifpContract)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ifp contract with the primary key or throws a {@link com.stpl.app.NoSuchIfpContractException} if it could not be found.
    *
    * @param ifpContractSid the primary key of the ifp contract
    * @return the ifp contract
    * @throws com.stpl.app.NoSuchIfpContractException if a ifp contract with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IfpContract findByPrimaryKey(int ifpContractSid)
        throws com.stpl.app.NoSuchIfpContractException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ifp contract with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ifpContractSid the primary key of the ifp contract
    * @return the ifp contract, or <code>null</code> if a ifp contract with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IfpContract fetchByPrimaryKey(int ifpContractSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the ifp contracts.
    *
    * @return the ifp contracts
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IfpContract> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ifp contracts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ifp contracts
    * @param end the upper bound of the range of ifp contracts (not inclusive)
    * @return the range of ifp contracts
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IfpContract> findAll(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ifp contracts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IfpContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ifp contracts
    * @param end the upper bound of the range of ifp contracts (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ifp contracts
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IfpContract> findAll(int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the ifp contracts from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ifp contracts.
    *
    * @return the number of ifp contracts
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
