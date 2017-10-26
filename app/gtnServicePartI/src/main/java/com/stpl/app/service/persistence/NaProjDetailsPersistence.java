package com.stpl.app.service.persistence;

import com.stpl.app.model.NaProjDetails;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the na proj details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NaProjDetailsPersistenceImpl
 * @see NaProjDetailsUtil
 * @generated
 */
public interface NaProjDetailsPersistence extends BasePersistence<NaProjDetails> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link NaProjDetailsUtil} to access the na proj details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the na proj details in the entity cache if it is enabled.
    *
    * @param naProjDetails the na proj details
    */
    public void cacheResult(com.stpl.app.model.NaProjDetails naProjDetails);

    /**
    * Caches the na proj detailses in the entity cache if it is enabled.
    *
    * @param naProjDetailses the na proj detailses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.NaProjDetails> naProjDetailses);

    /**
    * Creates a new na proj details with the primary key. Does not add the na proj details to the database.
    *
    * @param naProjDetailsSid the primary key for the new na proj details
    * @return the new na proj details
    */
    public com.stpl.app.model.NaProjDetails create(int naProjDetailsSid);

    /**
    * Removes the na proj details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param naProjDetailsSid the primary key of the na proj details
    * @return the na proj details that was removed
    * @throws com.stpl.app.NoSuchNaProjDetailsException if a na proj details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.NaProjDetails remove(int naProjDetailsSid)
        throws com.stpl.app.NoSuchNaProjDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.NaProjDetails updateImpl(
        com.stpl.app.model.NaProjDetails naProjDetails)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the na proj details with the primary key or throws a {@link com.stpl.app.NoSuchNaProjDetailsException} if it could not be found.
    *
    * @param naProjDetailsSid the primary key of the na proj details
    * @return the na proj details
    * @throws com.stpl.app.NoSuchNaProjDetailsException if a na proj details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.NaProjDetails findByPrimaryKey(
        int naProjDetailsSid)
        throws com.stpl.app.NoSuchNaProjDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the na proj details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param naProjDetailsSid the primary key of the na proj details
    * @return the na proj details, or <code>null</code> if a na proj details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.NaProjDetails fetchByPrimaryKey(
        int naProjDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the na proj detailses.
    *
    * @return the na proj detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.NaProjDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the na proj detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NaProjDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of na proj detailses
    * @param end the upper bound of the range of na proj detailses (not inclusive)
    * @return the range of na proj detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.NaProjDetails> findAll(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the na proj detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NaProjDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of na proj detailses
    * @param end the upper bound of the range of na proj detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of na proj detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.NaProjDetails> findAll(int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the na proj detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of na proj detailses.
    *
    * @return the number of na proj detailses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
