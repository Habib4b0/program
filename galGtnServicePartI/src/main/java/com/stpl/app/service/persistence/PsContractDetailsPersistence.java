package com.stpl.app.service.persistence;

import com.stpl.app.model.PsContractDetails;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the ps contract details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see PsContractDetailsPersistenceImpl
 * @see PsContractDetailsUtil
 * @generated
 */
public interface PsContractDetailsPersistence extends BasePersistence<PsContractDetails> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link PsContractDetailsUtil} to access the ps contract details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the ps contract details in the entity cache if it is enabled.
    *
    * @param psContractDetails the ps contract details
    */
    public void cacheResult(
        com.stpl.app.model.PsContractDetails psContractDetails);

    /**
    * Caches the ps contract detailses in the entity cache if it is enabled.
    *
    * @param psContractDetailses the ps contract detailses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.PsContractDetails> psContractDetailses);

    /**
    * Creates a new ps contract details with the primary key. Does not add the ps contract details to the database.
    *
    * @param psContractDetailsSid the primary key for the new ps contract details
    * @return the new ps contract details
    */
    public com.stpl.app.model.PsContractDetails create(int psContractDetailsSid);

    /**
    * Removes the ps contract details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param psContractDetailsSid the primary key of the ps contract details
    * @return the ps contract details that was removed
    * @throws com.stpl.app.NoSuchPsContractDetailsException if a ps contract details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.PsContractDetails remove(int psContractDetailsSid)
        throws com.stpl.app.NoSuchPsContractDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.PsContractDetails updateImpl(
        com.stpl.app.model.PsContractDetails psContractDetails)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ps contract details with the primary key or throws a {@link com.stpl.app.NoSuchPsContractDetailsException} if it could not be found.
    *
    * @param psContractDetailsSid the primary key of the ps contract details
    * @return the ps contract details
    * @throws com.stpl.app.NoSuchPsContractDetailsException if a ps contract details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.PsContractDetails findByPrimaryKey(
        int psContractDetailsSid)
        throws com.stpl.app.NoSuchPsContractDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ps contract details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param psContractDetailsSid the primary key of the ps contract details
    * @return the ps contract details, or <code>null</code> if a ps contract details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.PsContractDetails fetchByPrimaryKey(
        int psContractDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the ps contract detailses.
    *
    * @return the ps contract detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.PsContractDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ps contract detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ps contract detailses
    * @param end the upper bound of the range of ps contract detailses (not inclusive)
    * @return the range of ps contract detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.PsContractDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ps contract detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.PsContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ps contract detailses
    * @param end the upper bound of the range of ps contract detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ps contract detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.PsContractDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the ps contract detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ps contract detailses.
    *
    * @return the number of ps contract detailses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
