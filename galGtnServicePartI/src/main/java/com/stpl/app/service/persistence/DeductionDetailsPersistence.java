package com.stpl.app.service.persistence;

import com.stpl.app.model.DeductionDetails;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the deduction details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see DeductionDetailsPersistenceImpl
 * @see DeductionDetailsUtil
 * @generated
 */
public interface DeductionDetailsPersistence extends BasePersistence<DeductionDetails> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link DeductionDetailsUtil} to access the deduction details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the deduction details in the entity cache if it is enabled.
    *
    * @param deductionDetails the deduction details
    */
    public void cacheResult(
        com.stpl.app.model.DeductionDetails deductionDetails);

    /**
    * Caches the deduction detailses in the entity cache if it is enabled.
    *
    * @param deductionDetailses the deduction detailses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.DeductionDetails> deductionDetailses);

    /**
    * Creates a new deduction details with the primary key. Does not add the deduction details to the database.
    *
    * @param deductionDetailsSid the primary key for the new deduction details
    * @return the new deduction details
    */
    public com.stpl.app.model.DeductionDetails create(int deductionDetailsSid);

    /**
    * Removes the deduction details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param deductionDetailsSid the primary key of the deduction details
    * @return the deduction details that was removed
    * @throws com.stpl.app.NoSuchDeductionDetailsException if a deduction details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.DeductionDetails remove(int deductionDetailsSid)
        throws com.stpl.app.NoSuchDeductionDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.DeductionDetails updateImpl(
        com.stpl.app.model.DeductionDetails deductionDetails)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the deduction details with the primary key or throws a {@link com.stpl.app.NoSuchDeductionDetailsException} if it could not be found.
    *
    * @param deductionDetailsSid the primary key of the deduction details
    * @return the deduction details
    * @throws com.stpl.app.NoSuchDeductionDetailsException if a deduction details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.DeductionDetails findByPrimaryKey(
        int deductionDetailsSid)
        throws com.stpl.app.NoSuchDeductionDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the deduction details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param deductionDetailsSid the primary key of the deduction details
    * @return the deduction details, or <code>null</code> if a deduction details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.DeductionDetails fetchByPrimaryKey(
        int deductionDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the deduction detailses.
    *
    * @return the deduction detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.DeductionDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the deduction detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DeductionDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of deduction detailses
    * @param end the upper bound of the range of deduction detailses (not inclusive)
    * @return the range of deduction detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.DeductionDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the deduction detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DeductionDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of deduction detailses
    * @param end the upper bound of the range of deduction detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of deduction detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.DeductionDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the deduction detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of deduction detailses.
    *
    * @return the number of deduction detailses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
