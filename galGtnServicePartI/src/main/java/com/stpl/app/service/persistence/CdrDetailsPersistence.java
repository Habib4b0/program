package com.stpl.app.service.persistence;

import com.stpl.app.model.CdrDetails;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the cdr details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CdrDetailsPersistenceImpl
 * @see CdrDetailsUtil
 * @generated
 */
public interface CdrDetailsPersistence extends BasePersistence<CdrDetails> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link CdrDetailsUtil} to access the cdr details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the cdr details in the entity cache if it is enabled.
    *
    * @param cdrDetails the cdr details
    */
    public void cacheResult(com.stpl.app.model.CdrDetails cdrDetails);

    /**
    * Caches the cdr detailses in the entity cache if it is enabled.
    *
    * @param cdrDetailses the cdr detailses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.CdrDetails> cdrDetailses);

    /**
    * Creates a new cdr details with the primary key. Does not add the cdr details to the database.
    *
    * @param cdrDetailsSid the primary key for the new cdr details
    * @return the new cdr details
    */
    public com.stpl.app.model.CdrDetails create(int cdrDetailsSid);

    /**
    * Removes the cdr details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param cdrDetailsSid the primary key of the cdr details
    * @return the cdr details that was removed
    * @throws com.stpl.app.NoSuchCdrDetailsException if a cdr details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CdrDetails remove(int cdrDetailsSid)
        throws com.stpl.app.NoSuchCdrDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.CdrDetails updateImpl(
        com.stpl.app.model.CdrDetails cdrDetails)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the cdr details with the primary key or throws a {@link com.stpl.app.NoSuchCdrDetailsException} if it could not be found.
    *
    * @param cdrDetailsSid the primary key of the cdr details
    * @return the cdr details
    * @throws com.stpl.app.NoSuchCdrDetailsException if a cdr details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CdrDetails findByPrimaryKey(int cdrDetailsSid)
        throws com.stpl.app.NoSuchCdrDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the cdr details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param cdrDetailsSid the primary key of the cdr details
    * @return the cdr details, or <code>null</code> if a cdr details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CdrDetails fetchByPrimaryKey(int cdrDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the cdr detailses.
    *
    * @return the cdr detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CdrDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the cdr detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CdrDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cdr detailses
    * @param end the upper bound of the range of cdr detailses (not inclusive)
    * @return the range of cdr detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CdrDetails> findAll(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the cdr detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CdrDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cdr detailses
    * @param end the upper bound of the range of cdr detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of cdr detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CdrDetails> findAll(int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the cdr detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of cdr detailses.
    *
    * @return the number of cdr detailses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
