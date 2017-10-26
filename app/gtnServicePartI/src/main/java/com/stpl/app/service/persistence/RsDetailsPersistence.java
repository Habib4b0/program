package com.stpl.app.service.persistence;

import com.stpl.app.model.RsDetails;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the rs details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see RsDetailsPersistenceImpl
 * @see RsDetailsUtil
 * @generated
 */
public interface RsDetailsPersistence extends BasePersistence<RsDetails> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link RsDetailsUtil} to access the rs details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the rs details in the entity cache if it is enabled.
    *
    * @param rsDetails the rs details
    */
    public void cacheResult(com.stpl.app.model.RsDetails rsDetails);

    /**
    * Caches the rs detailses in the entity cache if it is enabled.
    *
    * @param rsDetailses the rs detailses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.RsDetails> rsDetailses);

    /**
    * Creates a new rs details with the primary key. Does not add the rs details to the database.
    *
    * @param rsDetailsSid the primary key for the new rs details
    * @return the new rs details
    */
    public com.stpl.app.model.RsDetails create(int rsDetailsSid);

    /**
    * Removes the rs details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param rsDetailsSid the primary key of the rs details
    * @return the rs details that was removed
    * @throws com.stpl.app.NoSuchRsDetailsException if a rs details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RsDetails remove(int rsDetailsSid)
        throws com.stpl.app.NoSuchRsDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.RsDetails updateImpl(
        com.stpl.app.model.RsDetails rsDetails)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the rs details with the primary key or throws a {@link com.stpl.app.NoSuchRsDetailsException} if it could not be found.
    *
    * @param rsDetailsSid the primary key of the rs details
    * @return the rs details
    * @throws com.stpl.app.NoSuchRsDetailsException if a rs details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RsDetails findByPrimaryKey(int rsDetailsSid)
        throws com.stpl.app.NoSuchRsDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the rs details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param rsDetailsSid the primary key of the rs details
    * @return the rs details, or <code>null</code> if a rs details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RsDetails fetchByPrimaryKey(int rsDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the rs detailses.
    *
    * @return the rs detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RsDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the rs detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of rs detailses
    * @param end the upper bound of the range of rs detailses (not inclusive)
    * @return the range of rs detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RsDetails> findAll(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the rs detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of rs detailses
    * @param end the upper bound of the range of rs detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of rs detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RsDetails> findAll(int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the rs detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of rs detailses.
    *
    * @return the number of rs detailses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
