package com.stpl.app.service.persistence;

import com.stpl.app.model.Udcs;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the udcs service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see UdcsPersistenceImpl
 * @see UdcsUtil
 * @generated
 */
public interface UdcsPersistence extends BasePersistence<Udcs> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link UdcsUtil} to access the udcs persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the udcs in the entity cache if it is enabled.
    *
    * @param udcs the udcs
    */
    public void cacheResult(com.stpl.app.model.Udcs udcs);

    /**
    * Caches the udcses in the entity cache if it is enabled.
    *
    * @param udcses the udcses
    */
    public void cacheResult(java.util.List<com.stpl.app.model.Udcs> udcses);

    /**
    * Creates a new udcs with the primary key. Does not add the udcs to the database.
    *
    * @param udcsSid the primary key for the new udcs
    * @return the new udcs
    */
    public com.stpl.app.model.Udcs create(int udcsSid);

    /**
    * Removes the udcs with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param udcsSid the primary key of the udcs
    * @return the udcs that was removed
    * @throws com.stpl.app.NoSuchUdcsException if a udcs with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.Udcs remove(int udcsSid)
        throws com.stpl.app.NoSuchUdcsException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.Udcs updateImpl(com.stpl.app.model.Udcs udcs)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the udcs with the primary key or throws a {@link com.stpl.app.NoSuchUdcsException} if it could not be found.
    *
    * @param udcsSid the primary key of the udcs
    * @return the udcs
    * @throws com.stpl.app.NoSuchUdcsException if a udcs with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.Udcs findByPrimaryKey(int udcsSid)
        throws com.stpl.app.NoSuchUdcsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the udcs with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param udcsSid the primary key of the udcs
    * @return the udcs, or <code>null</code> if a udcs with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.Udcs fetchByPrimaryKey(int udcsSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the udcses.
    *
    * @return the udcses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.Udcs> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the udcses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.UdcsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of udcses
    * @param end the upper bound of the range of udcses (not inclusive)
    * @return the range of udcses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.Udcs> findAll(int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the udcses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.UdcsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of udcses
    * @param end the upper bound of the range of udcses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of udcses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.Udcs> findAll(int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the udcses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of udcses.
    *
    * @return the number of udcses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
