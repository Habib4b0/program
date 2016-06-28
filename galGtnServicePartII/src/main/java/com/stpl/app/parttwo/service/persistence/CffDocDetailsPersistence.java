package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.CffDocDetails;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the cff doc details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CffDocDetailsPersistenceImpl
 * @see CffDocDetailsUtil
 * @generated
 */
public interface CffDocDetailsPersistence extends BasePersistence<CffDocDetails> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link CffDocDetailsUtil} to access the cff doc details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the cff doc details in the entity cache if it is enabled.
    *
    * @param cffDocDetails the cff doc details
    */
    public void cacheResult(
        com.stpl.app.parttwo.model.CffDocDetails cffDocDetails);

    /**
    * Caches the cff doc detailses in the entity cache if it is enabled.
    *
    * @param cffDocDetailses the cff doc detailses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.CffDocDetails> cffDocDetailses);

    /**
    * Creates a new cff doc details with the primary key. Does not add the cff doc details to the database.
    *
    * @param cffDocDetailsSid the primary key for the new cff doc details
    * @return the new cff doc details
    */
    public com.stpl.app.parttwo.model.CffDocDetails create(int cffDocDetailsSid);

    /**
    * Removes the cff doc details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param cffDocDetailsSid the primary key of the cff doc details
    * @return the cff doc details that was removed
    * @throws com.stpl.app.parttwo.NoSuchCffDocDetailsException if a cff doc details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.CffDocDetails remove(int cffDocDetailsSid)
        throws com.stpl.app.parttwo.NoSuchCffDocDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.parttwo.model.CffDocDetails updateImpl(
        com.stpl.app.parttwo.model.CffDocDetails cffDocDetails)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the cff doc details with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchCffDocDetailsException} if it could not be found.
    *
    * @param cffDocDetailsSid the primary key of the cff doc details
    * @return the cff doc details
    * @throws com.stpl.app.parttwo.NoSuchCffDocDetailsException if a cff doc details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.CffDocDetails findByPrimaryKey(
        int cffDocDetailsSid)
        throws com.stpl.app.parttwo.NoSuchCffDocDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the cff doc details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param cffDocDetailsSid the primary key of the cff doc details
    * @return the cff doc details, or <code>null</code> if a cff doc details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.CffDocDetails fetchByPrimaryKey(
        int cffDocDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the cff doc detailses.
    *
    * @return the cff doc detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.CffDocDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the cff doc detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffDocDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cff doc detailses
    * @param end the upper bound of the range of cff doc detailses (not inclusive)
    * @return the range of cff doc detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.CffDocDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the cff doc detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffDocDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cff doc detailses
    * @param end the upper bound of the range of cff doc detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of cff doc detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.CffDocDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the cff doc detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of cff doc detailses.
    *
    * @return the number of cff doc detailses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
