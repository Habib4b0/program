package com.stpl.app.service.persistence;

import com.stpl.app.model.DocDetails;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the doc details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see DocDetailsPersistenceImpl
 * @see DocDetailsUtil
 * @generated
 */
public interface DocDetailsPersistence extends BasePersistence<DocDetails> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link DocDetailsUtil} to access the doc details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the doc details in the entity cache if it is enabled.
    *
    * @param docDetails the doc details
    */
    public void cacheResult(com.stpl.app.model.DocDetails docDetails);

    /**
    * Caches the doc detailses in the entity cache if it is enabled.
    *
    * @param docDetailses the doc detailses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.DocDetails> docDetailses);

    /**
    * Creates a new doc details with the primary key. Does not add the doc details to the database.
    *
    * @param docDetailsId the primary key for the new doc details
    * @return the new doc details
    */
    public com.stpl.app.model.DocDetails create(int docDetailsId);

    /**
    * Removes the doc details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param docDetailsId the primary key of the doc details
    * @return the doc details that was removed
    * @throws com.stpl.app.NoSuchDocDetailsException if a doc details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.DocDetails remove(int docDetailsId)
        throws com.stpl.app.NoSuchDocDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.DocDetails updateImpl(
        com.stpl.app.model.DocDetails docDetails)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the doc details with the primary key or throws a {@link com.stpl.app.NoSuchDocDetailsException} if it could not be found.
    *
    * @param docDetailsId the primary key of the doc details
    * @return the doc details
    * @throws com.stpl.app.NoSuchDocDetailsException if a doc details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.DocDetails findByPrimaryKey(int docDetailsId)
        throws com.stpl.app.NoSuchDocDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the doc details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param docDetailsId the primary key of the doc details
    * @return the doc details, or <code>null</code> if a doc details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.DocDetails fetchByPrimaryKey(int docDetailsId)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the doc detailses.
    *
    * @return the doc detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.DocDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the doc detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DocDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of doc detailses
    * @param end the upper bound of the range of doc detailses (not inclusive)
    * @return the range of doc detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.DocDetails> findAll(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the doc detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DocDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of doc detailses
    * @param end the upper bound of the range of doc detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of doc detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.DocDetails> findAll(int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the doc detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of doc detailses.
    *
    * @return the number of doc detailses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
