package com.stpl.app.service.persistence;

import com.stpl.app.model.ImtdPsDetails;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the imtd ps details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ImtdPsDetailsPersistenceImpl
 * @see ImtdPsDetailsUtil
 * @generated
 */
public interface ImtdPsDetailsPersistence extends BasePersistence<ImtdPsDetails> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ImtdPsDetailsUtil} to access the imtd ps details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the imtd ps details in the entity cache if it is enabled.
    *
    * @param imtdPsDetails the imtd ps details
    */
    public void cacheResult(com.stpl.app.model.ImtdPsDetails imtdPsDetails);

    /**
    * Caches the imtd ps detailses in the entity cache if it is enabled.
    *
    * @param imtdPsDetailses the imtd ps detailses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.ImtdPsDetails> imtdPsDetailses);

    /**
    * Creates a new imtd ps details with the primary key. Does not add the imtd ps details to the database.
    *
    * @param imtdPsDetailsSid the primary key for the new imtd ps details
    * @return the new imtd ps details
    */
    public com.stpl.app.model.ImtdPsDetails create(int imtdPsDetailsSid);

    /**
    * Removes the imtd ps details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param imtdPsDetailsSid the primary key of the imtd ps details
    * @return the imtd ps details that was removed
    * @throws com.stpl.app.NoSuchImtdPsDetailsException if a imtd ps details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ImtdPsDetails remove(int imtdPsDetailsSid)
        throws com.stpl.app.NoSuchImtdPsDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.ImtdPsDetails updateImpl(
        com.stpl.app.model.ImtdPsDetails imtdPsDetails)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the imtd ps details with the primary key or throws a {@link com.stpl.app.NoSuchImtdPsDetailsException} if it could not be found.
    *
    * @param imtdPsDetailsSid the primary key of the imtd ps details
    * @return the imtd ps details
    * @throws com.stpl.app.NoSuchImtdPsDetailsException if a imtd ps details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ImtdPsDetails findByPrimaryKey(
        int imtdPsDetailsSid)
        throws com.stpl.app.NoSuchImtdPsDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the imtd ps details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param imtdPsDetailsSid the primary key of the imtd ps details
    * @return the imtd ps details, or <code>null</code> if a imtd ps details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ImtdPsDetails fetchByPrimaryKey(
        int imtdPsDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the imtd ps detailses.
    *
    * @return the imtd ps detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ImtdPsDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the imtd ps detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdPsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of imtd ps detailses
    * @param end the upper bound of the range of imtd ps detailses (not inclusive)
    * @return the range of imtd ps detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ImtdPsDetails> findAll(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the imtd ps detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdPsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of imtd ps detailses
    * @param end the upper bound of the range of imtd ps detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of imtd ps detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ImtdPsDetails> findAll(int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the imtd ps detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of imtd ps detailses.
    *
    * @return the number of imtd ps detailses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
