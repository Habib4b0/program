package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.CffApprovalDetails;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the cff approval details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CffApprovalDetailsPersistenceImpl
 * @see CffApprovalDetailsUtil
 * @generated
 */
public interface CffApprovalDetailsPersistence extends BasePersistence<CffApprovalDetails> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link CffApprovalDetailsUtil} to access the cff approval details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the cff approval details in the entity cache if it is enabled.
    *
    * @param cffApprovalDetails the cff approval details
    */
    public void cacheResult(
        com.stpl.app.parttwo.model.CffApprovalDetails cffApprovalDetails);

    /**
    * Caches the cff approval detailses in the entity cache if it is enabled.
    *
    * @param cffApprovalDetailses the cff approval detailses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.CffApprovalDetails> cffApprovalDetailses);

    /**
    * Creates a new cff approval details with the primary key. Does not add the cff approval details to the database.
    *
    * @param cffApprovalDetailsSid the primary key for the new cff approval details
    * @return the new cff approval details
    */
    public com.stpl.app.parttwo.model.CffApprovalDetails create(
        int cffApprovalDetailsSid);

    /**
    * Removes the cff approval details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param cffApprovalDetailsSid the primary key of the cff approval details
    * @return the cff approval details that was removed
    * @throws com.stpl.app.parttwo.NoSuchCffApprovalDetailsException if a cff approval details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.CffApprovalDetails remove(
        int cffApprovalDetailsSid)
        throws com.stpl.app.parttwo.NoSuchCffApprovalDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.parttwo.model.CffApprovalDetails updateImpl(
        com.stpl.app.parttwo.model.CffApprovalDetails cffApprovalDetails)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the cff approval details with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchCffApprovalDetailsException} if it could not be found.
    *
    * @param cffApprovalDetailsSid the primary key of the cff approval details
    * @return the cff approval details
    * @throws com.stpl.app.parttwo.NoSuchCffApprovalDetailsException if a cff approval details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.CffApprovalDetails findByPrimaryKey(
        int cffApprovalDetailsSid)
        throws com.stpl.app.parttwo.NoSuchCffApprovalDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the cff approval details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param cffApprovalDetailsSid the primary key of the cff approval details
    * @return the cff approval details, or <code>null</code> if a cff approval details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.CffApprovalDetails fetchByPrimaryKey(
        int cffApprovalDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the cff approval detailses.
    *
    * @return the cff approval detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.CffApprovalDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the cff approval detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffApprovalDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cff approval detailses
    * @param end the upper bound of the range of cff approval detailses (not inclusive)
    * @return the range of cff approval detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.CffApprovalDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the cff approval detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffApprovalDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cff approval detailses
    * @param end the upper bound of the range of cff approval detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of cff approval detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.CffApprovalDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the cff approval detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of cff approval detailses.
    *
    * @return the number of cff approval detailses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
