package com.stpl.app.service.persistence;

import com.stpl.app.model.ImtdDeductionDetails;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the imtd deduction details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ImtdDeductionDetailsPersistenceImpl
 * @see ImtdDeductionDetailsUtil
 * @generated
 */
public interface ImtdDeductionDetailsPersistence extends BasePersistence<ImtdDeductionDetails> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ImtdDeductionDetailsUtil} to access the imtd deduction details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the imtd deduction details in the entity cache if it is enabled.
    *
    * @param imtdDeductionDetails the imtd deduction details
    */
    public void cacheResult(
        com.stpl.app.model.ImtdDeductionDetails imtdDeductionDetails);

    /**
    * Caches the imtd deduction detailses in the entity cache if it is enabled.
    *
    * @param imtdDeductionDetailses the imtd deduction detailses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.ImtdDeductionDetails> imtdDeductionDetailses);

    /**
    * Creates a new imtd deduction details with the primary key. Does not add the imtd deduction details to the database.
    *
    * @param imtdDeductionDetailsSid the primary key for the new imtd deduction details
    * @return the new imtd deduction details
    */
    public com.stpl.app.model.ImtdDeductionDetails create(
        int imtdDeductionDetailsSid);

    /**
    * Removes the imtd deduction details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param imtdDeductionDetailsSid the primary key of the imtd deduction details
    * @return the imtd deduction details that was removed
    * @throws com.stpl.app.NoSuchImtdDeductionDetailsException if a imtd deduction details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ImtdDeductionDetails remove(
        int imtdDeductionDetailsSid)
        throws com.stpl.app.NoSuchImtdDeductionDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.ImtdDeductionDetails updateImpl(
        com.stpl.app.model.ImtdDeductionDetails imtdDeductionDetails)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the imtd deduction details with the primary key or throws a {@link com.stpl.app.NoSuchImtdDeductionDetailsException} if it could not be found.
    *
    * @param imtdDeductionDetailsSid the primary key of the imtd deduction details
    * @return the imtd deduction details
    * @throws com.stpl.app.NoSuchImtdDeductionDetailsException if a imtd deduction details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ImtdDeductionDetails findByPrimaryKey(
        int imtdDeductionDetailsSid)
        throws com.stpl.app.NoSuchImtdDeductionDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the imtd deduction details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param imtdDeductionDetailsSid the primary key of the imtd deduction details
    * @return the imtd deduction details, or <code>null</code> if a imtd deduction details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ImtdDeductionDetails fetchByPrimaryKey(
        int imtdDeductionDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the imtd deduction detailses.
    *
    * @return the imtd deduction detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ImtdDeductionDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the imtd deduction detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdDeductionDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of imtd deduction detailses
    * @param end the upper bound of the range of imtd deduction detailses (not inclusive)
    * @return the range of imtd deduction detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ImtdDeductionDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the imtd deduction detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdDeductionDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of imtd deduction detailses
    * @param end the upper bound of the range of imtd deduction detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of imtd deduction detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ImtdDeductionDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the imtd deduction detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of imtd deduction detailses.
    *
    * @return the number of imtd deduction detailses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
