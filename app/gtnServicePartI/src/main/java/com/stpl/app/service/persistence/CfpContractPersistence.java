package com.stpl.app.service.persistence;

import com.stpl.app.model.CfpContract;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the cfp contract service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CfpContractPersistenceImpl
 * @see CfpContractUtil
 * @generated
 */
public interface CfpContractPersistence extends BasePersistence<CfpContract> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link CfpContractUtil} to access the cfp contract persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the cfp contract in the entity cache if it is enabled.
    *
    * @param cfpContract the cfp contract
    */
    public void cacheResult(com.stpl.app.model.CfpContract cfpContract);

    /**
    * Caches the cfp contracts in the entity cache if it is enabled.
    *
    * @param cfpContracts the cfp contracts
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.CfpContract> cfpContracts);

    /**
    * Creates a new cfp contract with the primary key. Does not add the cfp contract to the database.
    *
    * @param cfpContractSid the primary key for the new cfp contract
    * @return the new cfp contract
    */
    public com.stpl.app.model.CfpContract create(int cfpContractSid);

    /**
    * Removes the cfp contract with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param cfpContractSid the primary key of the cfp contract
    * @return the cfp contract that was removed
    * @throws com.stpl.app.NoSuchCfpContractException if a cfp contract with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CfpContract remove(int cfpContractSid)
        throws com.stpl.app.NoSuchCfpContractException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.CfpContract updateImpl(
        com.stpl.app.model.CfpContract cfpContract)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the cfp contract with the primary key or throws a {@link com.stpl.app.NoSuchCfpContractException} if it could not be found.
    *
    * @param cfpContractSid the primary key of the cfp contract
    * @return the cfp contract
    * @throws com.stpl.app.NoSuchCfpContractException if a cfp contract with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CfpContract findByPrimaryKey(int cfpContractSid)
        throws com.stpl.app.NoSuchCfpContractException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the cfp contract with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param cfpContractSid the primary key of the cfp contract
    * @return the cfp contract, or <code>null</code> if a cfp contract with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CfpContract fetchByPrimaryKey(int cfpContractSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the cfp contracts.
    *
    * @return the cfp contracts
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CfpContract> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the cfp contracts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cfp contracts
    * @param end the upper bound of the range of cfp contracts (not inclusive)
    * @return the range of cfp contracts
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CfpContract> findAll(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the cfp contracts.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CfpContractModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cfp contracts
    * @param end the upper bound of the range of cfp contracts (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of cfp contracts
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CfpContract> findAll(int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the cfp contracts from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of cfp contracts.
    *
    * @return the number of cfp contracts
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
