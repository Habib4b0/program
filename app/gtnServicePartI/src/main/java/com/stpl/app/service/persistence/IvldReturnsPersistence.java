package com.stpl.app.service.persistence;

import com.stpl.app.model.IvldReturns;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the ivld returns service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldReturnsPersistenceImpl
 * @see IvldReturnsUtil
 * @generated
 */
public interface IvldReturnsPersistence extends BasePersistence<IvldReturns> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link IvldReturnsUtil} to access the ivld returns persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the ivld returns in the entity cache if it is enabled.
    *
    * @param ivldReturns the ivld returns
    */
    public void cacheResult(com.stpl.app.model.IvldReturns ivldReturns);

    /**
    * Caches the ivld returnses in the entity cache if it is enabled.
    *
    * @param ivldReturnses the ivld returnses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.IvldReturns> ivldReturnses);

    /**
    * Creates a new ivld returns with the primary key. Does not add the ivld returns to the database.
    *
    * @param ivldReturnsSid the primary key for the new ivld returns
    * @return the new ivld returns
    */
    public com.stpl.app.model.IvldReturns create(int ivldReturnsSid);

    /**
    * Removes the ivld returns with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldReturnsSid the primary key of the ivld returns
    * @return the ivld returns that was removed
    * @throws com.stpl.app.NoSuchIvldReturnsException if a ivld returns with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IvldReturns remove(int ivldReturnsSid)
        throws com.stpl.app.NoSuchIvldReturnsException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.IvldReturns updateImpl(
        com.stpl.app.model.IvldReturns ivldReturns)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ivld returns with the primary key or throws a {@link com.stpl.app.NoSuchIvldReturnsException} if it could not be found.
    *
    * @param ivldReturnsSid the primary key of the ivld returns
    * @return the ivld returns
    * @throws com.stpl.app.NoSuchIvldReturnsException if a ivld returns with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IvldReturns findByPrimaryKey(int ivldReturnsSid)
        throws com.stpl.app.NoSuchIvldReturnsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ivld returns with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ivldReturnsSid the primary key of the ivld returns
    * @return the ivld returns, or <code>null</code> if a ivld returns with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IvldReturns fetchByPrimaryKey(int ivldReturnsSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the ivld returnses.
    *
    * @return the ivld returnses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IvldReturns> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ivld returnses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldReturnsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld returnses
    * @param end the upper bound of the range of ivld returnses (not inclusive)
    * @return the range of ivld returnses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IvldReturns> findAll(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ivld returnses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldReturnsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld returnses
    * @param end the upper bound of the range of ivld returnses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ivld returnses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IvldReturns> findAll(int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the ivld returnses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ivld returnses.
    *
    * @return the number of ivld returnses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
