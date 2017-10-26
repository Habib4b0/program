package com.stpl.app.service.persistence;

import com.stpl.app.model.CcpDetails;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the ccp details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CcpDetailsPersistenceImpl
 * @see CcpDetailsUtil
 * @generated
 */
public interface CcpDetailsPersistence extends BasePersistence<CcpDetails> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link CcpDetailsUtil} to access the ccp details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the ccp details in the entity cache if it is enabled.
    *
    * @param ccpDetails the ccp details
    */
    public void cacheResult(com.stpl.app.model.CcpDetails ccpDetails);

    /**
    * Caches the ccp detailses in the entity cache if it is enabled.
    *
    * @param ccpDetailses the ccp detailses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.CcpDetails> ccpDetailses);

    /**
    * Creates a new ccp details with the primary key. Does not add the ccp details to the database.
    *
    * @param ccpDetailsSid the primary key for the new ccp details
    * @return the new ccp details
    */
    public com.stpl.app.model.CcpDetails create(int ccpDetailsSid);

    /**
    * Removes the ccp details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ccpDetailsSid the primary key of the ccp details
    * @return the ccp details that was removed
    * @throws com.stpl.app.NoSuchCcpDetailsException if a ccp details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CcpDetails remove(int ccpDetailsSid)
        throws com.stpl.app.NoSuchCcpDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.CcpDetails updateImpl(
        com.stpl.app.model.CcpDetails ccpDetails)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ccp details with the primary key or throws a {@link com.stpl.app.NoSuchCcpDetailsException} if it could not be found.
    *
    * @param ccpDetailsSid the primary key of the ccp details
    * @return the ccp details
    * @throws com.stpl.app.NoSuchCcpDetailsException if a ccp details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CcpDetails findByPrimaryKey(int ccpDetailsSid)
        throws com.stpl.app.NoSuchCcpDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ccp details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ccpDetailsSid the primary key of the ccp details
    * @return the ccp details, or <code>null</code> if a ccp details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CcpDetails fetchByPrimaryKey(int ccpDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the ccp detailses.
    *
    * @return the ccp detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CcpDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ccp detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CcpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ccp detailses
    * @param end the upper bound of the range of ccp detailses (not inclusive)
    * @return the range of ccp detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CcpDetails> findAll(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ccp detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CcpDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ccp detailses
    * @param end the upper bound of the range of ccp detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ccp detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CcpDetails> findAll(int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the ccp detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ccp detailses.
    *
    * @return the number of ccp detailses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
