package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.StAccClosureDetails;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the st acc closure details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StAccClosureDetailsPersistenceImpl
 * @see StAccClosureDetailsUtil
 * @generated
 */
public interface StAccClosureDetailsPersistence extends BasePersistence<StAccClosureDetails> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link StAccClosureDetailsUtil} to access the st acc closure details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the st acc closure details in the entity cache if it is enabled.
    *
    * @param stAccClosureDetails the st acc closure details
    */
    public void cacheResult(
        com.stpl.app.parttwo.model.StAccClosureDetails stAccClosureDetails);

    /**
    * Caches the st acc closure detailses in the entity cache if it is enabled.
    *
    * @param stAccClosureDetailses the st acc closure detailses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.StAccClosureDetails> stAccClosureDetailses);

    /**
    * Creates a new st acc closure details with the primary key. Does not add the st acc closure details to the database.
    *
    * @param accClosureMasterSid the primary key for the new st acc closure details
    * @return the new st acc closure details
    */
    public com.stpl.app.parttwo.model.StAccClosureDetails create(
        int accClosureMasterSid);

    /**
    * Removes the st acc closure details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param accClosureMasterSid the primary key of the st acc closure details
    * @return the st acc closure details that was removed
    * @throws com.stpl.app.parttwo.NoSuchStAccClosureDetailsException if a st acc closure details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.StAccClosureDetails remove(
        int accClosureMasterSid)
        throws com.stpl.app.parttwo.NoSuchStAccClosureDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.parttwo.model.StAccClosureDetails updateImpl(
        com.stpl.app.parttwo.model.StAccClosureDetails stAccClosureDetails)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the st acc closure details with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchStAccClosureDetailsException} if it could not be found.
    *
    * @param accClosureMasterSid the primary key of the st acc closure details
    * @return the st acc closure details
    * @throws com.stpl.app.parttwo.NoSuchStAccClosureDetailsException if a st acc closure details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.StAccClosureDetails findByPrimaryKey(
        int accClosureMasterSid)
        throws com.stpl.app.parttwo.NoSuchStAccClosureDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the st acc closure details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param accClosureMasterSid the primary key of the st acc closure details
    * @return the st acc closure details, or <code>null</code> if a st acc closure details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.parttwo.model.StAccClosureDetails fetchByPrimaryKey(
        int accClosureMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the st acc closure detailses.
    *
    * @return the st acc closure detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.StAccClosureDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the st acc closure detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.StAccClosureDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st acc closure detailses
    * @param end the upper bound of the range of st acc closure detailses (not inclusive)
    * @return the range of st acc closure detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.StAccClosureDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the st acc closure detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.StAccClosureDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st acc closure detailses
    * @param end the upper bound of the range of st acc closure detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of st acc closure detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.parttwo.model.StAccClosureDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the st acc closure detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of st acc closure detailses.
    *
    * @return the number of st acc closure detailses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
