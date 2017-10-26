package com.stpl.app.service.persistence;

import com.stpl.app.model.ContractAliasMaster;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the contract alias master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ContractAliasMasterPersistenceImpl
 * @see ContractAliasMasterUtil
 * @generated
 */
public interface ContractAliasMasterPersistence extends BasePersistence<ContractAliasMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ContractAliasMasterUtil} to access the contract alias master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the contract alias masters where contractMasterSid = &#63;.
    *
    * @param contractMasterSid the contract master sid
    * @return the matching contract alias masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ContractAliasMaster> findByContractSystemId(
        int contractMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the contract alias masters where contractMasterSid = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ContractAliasMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contractMasterSid the contract master sid
    * @param start the lower bound of the range of contract alias masters
    * @param end the upper bound of the range of contract alias masters (not inclusive)
    * @return the range of matching contract alias masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ContractAliasMaster> findByContractSystemId(
        int contractMasterSid, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the contract alias masters where contractMasterSid = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ContractAliasMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param contractMasterSid the contract master sid
    * @param start the lower bound of the range of contract alias masters
    * @param end the upper bound of the range of contract alias masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching contract alias masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ContractAliasMaster> findByContractSystemId(
        int contractMasterSid, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first contract alias master in the ordered set where contractMasterSid = &#63;.
    *
    * @param contractMasterSid the contract master sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contract alias master
    * @throws com.stpl.app.NoSuchContractAliasMasterException if a matching contract alias master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ContractAliasMaster findByContractSystemId_First(
        int contractMasterSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchContractAliasMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first contract alias master in the ordered set where contractMasterSid = &#63;.
    *
    * @param contractMasterSid the contract master sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching contract alias master, or <code>null</code> if a matching contract alias master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ContractAliasMaster fetchByContractSystemId_First(
        int contractMasterSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last contract alias master in the ordered set where contractMasterSid = &#63;.
    *
    * @param contractMasterSid the contract master sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contract alias master
    * @throws com.stpl.app.NoSuchContractAliasMasterException if a matching contract alias master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ContractAliasMaster findByContractSystemId_Last(
        int contractMasterSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchContractAliasMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last contract alias master in the ordered set where contractMasterSid = &#63;.
    *
    * @param contractMasterSid the contract master sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching contract alias master, or <code>null</code> if a matching contract alias master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ContractAliasMaster fetchByContractSystemId_Last(
        int contractMasterSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the contract alias masters before and after the current contract alias master in the ordered set where contractMasterSid = &#63;.
    *
    * @param contractAliasMasterSid the primary key of the current contract alias master
    * @param contractMasterSid the contract master sid
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next contract alias master
    * @throws com.stpl.app.NoSuchContractAliasMasterException if a contract alias master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ContractAliasMaster[] findByContractSystemId_PrevAndNext(
        int contractAliasMasterSid, int contractMasterSid,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchContractAliasMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the contract alias masters where contractMasterSid = &#63; from the database.
    *
    * @param contractMasterSid the contract master sid
    * @throws SystemException if a system exception occurred
    */
    public void removeByContractSystemId(int contractMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of contract alias masters where contractMasterSid = &#63;.
    *
    * @param contractMasterSid the contract master sid
    * @return the number of matching contract alias masters
    * @throws SystemException if a system exception occurred
    */
    public int countByContractSystemId(int contractMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Caches the contract alias master in the entity cache if it is enabled.
    *
    * @param contractAliasMaster the contract alias master
    */
    public void cacheResult(
        com.stpl.app.model.ContractAliasMaster contractAliasMaster);

    /**
    * Caches the contract alias masters in the entity cache if it is enabled.
    *
    * @param contractAliasMasters the contract alias masters
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.ContractAliasMaster> contractAliasMasters);

    /**
    * Creates a new contract alias master with the primary key. Does not add the contract alias master to the database.
    *
    * @param contractAliasMasterSid the primary key for the new contract alias master
    * @return the new contract alias master
    */
    public com.stpl.app.model.ContractAliasMaster create(
        int contractAliasMasterSid);

    /**
    * Removes the contract alias master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param contractAliasMasterSid the primary key of the contract alias master
    * @return the contract alias master that was removed
    * @throws com.stpl.app.NoSuchContractAliasMasterException if a contract alias master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ContractAliasMaster remove(
        int contractAliasMasterSid)
        throws com.stpl.app.NoSuchContractAliasMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.ContractAliasMaster updateImpl(
        com.stpl.app.model.ContractAliasMaster contractAliasMaster)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the contract alias master with the primary key or throws a {@link com.stpl.app.NoSuchContractAliasMasterException} if it could not be found.
    *
    * @param contractAliasMasterSid the primary key of the contract alias master
    * @return the contract alias master
    * @throws com.stpl.app.NoSuchContractAliasMasterException if a contract alias master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ContractAliasMaster findByPrimaryKey(
        int contractAliasMasterSid)
        throws com.stpl.app.NoSuchContractAliasMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the contract alias master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param contractAliasMasterSid the primary key of the contract alias master
    * @return the contract alias master, or <code>null</code> if a contract alias master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ContractAliasMaster fetchByPrimaryKey(
        int contractAliasMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the contract alias masters.
    *
    * @return the contract alias masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ContractAliasMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the contract alias masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ContractAliasMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of contract alias masters
    * @param end the upper bound of the range of contract alias masters (not inclusive)
    * @return the range of contract alias masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ContractAliasMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the contract alias masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ContractAliasMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of contract alias masters
    * @param end the upper bound of the range of contract alias masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of contract alias masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ContractAliasMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the contract alias masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of contract alias masters.
    *
    * @return the number of contract alias masters
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
