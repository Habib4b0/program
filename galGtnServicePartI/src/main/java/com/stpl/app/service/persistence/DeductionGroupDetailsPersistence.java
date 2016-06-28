package com.stpl.app.service.persistence;

import com.stpl.app.model.DeductionGroupDetails;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the deduction group details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see DeductionGroupDetailsPersistenceImpl
 * @see DeductionGroupDetailsUtil
 * @generated
 */
public interface DeductionGroupDetailsPersistence extends BasePersistence<DeductionGroupDetails> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link DeductionGroupDetailsUtil} to access the deduction group details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the deduction group details in the entity cache if it is enabled.
    *
    * @param deductionGroupDetails the deduction group details
    */
    public void cacheResult(
        com.stpl.app.model.DeductionGroupDetails deductionGroupDetails);

    /**
    * Caches the deduction group detailses in the entity cache if it is enabled.
    *
    * @param deductionGroupDetailses the deduction group detailses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.DeductionGroupDetails> deductionGroupDetailses);

    /**
    * Creates a new deduction group details with the primary key. Does not add the deduction group details to the database.
    *
    * @param deductionGroupDetailsSid the primary key for the new deduction group details
    * @return the new deduction group details
    */
    public com.stpl.app.model.DeductionGroupDetails create(
        int deductionGroupDetailsSid);

    /**
    * Removes the deduction group details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param deductionGroupDetailsSid the primary key of the deduction group details
    * @return the deduction group details that was removed
    * @throws com.stpl.app.NoSuchDeductionGroupDetailsException if a deduction group details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.DeductionGroupDetails remove(
        int deductionGroupDetailsSid)
        throws com.stpl.app.NoSuchDeductionGroupDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.DeductionGroupDetails updateImpl(
        com.stpl.app.model.DeductionGroupDetails deductionGroupDetails)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the deduction group details with the primary key or throws a {@link com.stpl.app.NoSuchDeductionGroupDetailsException} if it could not be found.
    *
    * @param deductionGroupDetailsSid the primary key of the deduction group details
    * @return the deduction group details
    * @throws com.stpl.app.NoSuchDeductionGroupDetailsException if a deduction group details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.DeductionGroupDetails findByPrimaryKey(
        int deductionGroupDetailsSid)
        throws com.stpl.app.NoSuchDeductionGroupDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the deduction group details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param deductionGroupDetailsSid the primary key of the deduction group details
    * @return the deduction group details, or <code>null</code> if a deduction group details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.DeductionGroupDetails fetchByPrimaryKey(
        int deductionGroupDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the deduction group detailses.
    *
    * @return the deduction group detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.DeductionGroupDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the deduction group detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DeductionGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of deduction group detailses
    * @param end the upper bound of the range of deduction group detailses (not inclusive)
    * @return the range of deduction group detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.DeductionGroupDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the deduction group detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DeductionGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of deduction group detailses
    * @param end the upper bound of the range of deduction group detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of deduction group detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.DeductionGroupDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the deduction group detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of deduction group detailses.
    *
    * @return the number of deduction group detailses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
