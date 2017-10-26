package com.stpl.app.service.persistence;

import com.stpl.app.model.RsDetailsFr;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the rs details fr service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see RsDetailsFrPersistenceImpl
 * @see RsDetailsFrUtil
 * @generated
 */
public interface RsDetailsFrPersistence extends BasePersistence<RsDetailsFr> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link RsDetailsFrUtil} to access the rs details fr persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the rs details fr in the entity cache if it is enabled.
    *
    * @param rsDetailsFr the rs details fr
    */
    public void cacheResult(com.stpl.app.model.RsDetailsFr rsDetailsFr);

    /**
    * Caches the rs details frs in the entity cache if it is enabled.
    *
    * @param rsDetailsFrs the rs details frs
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.RsDetailsFr> rsDetailsFrs);

    /**
    * Creates a new rs details fr with the primary key. Does not add the rs details fr to the database.
    *
    * @param rsDetailsFrSid the primary key for the new rs details fr
    * @return the new rs details fr
    */
    public com.stpl.app.model.RsDetailsFr create(int rsDetailsFrSid);

    /**
    * Removes the rs details fr with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param rsDetailsFrSid the primary key of the rs details fr
    * @return the rs details fr that was removed
    * @throws com.stpl.app.NoSuchRsDetailsFrException if a rs details fr with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RsDetailsFr remove(int rsDetailsFrSid)
        throws com.stpl.app.NoSuchRsDetailsFrException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.RsDetailsFr updateImpl(
        com.stpl.app.model.RsDetailsFr rsDetailsFr)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the rs details fr with the primary key or throws a {@link com.stpl.app.NoSuchRsDetailsFrException} if it could not be found.
    *
    * @param rsDetailsFrSid the primary key of the rs details fr
    * @return the rs details fr
    * @throws com.stpl.app.NoSuchRsDetailsFrException if a rs details fr with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RsDetailsFr findByPrimaryKey(int rsDetailsFrSid)
        throws com.stpl.app.NoSuchRsDetailsFrException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the rs details fr with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param rsDetailsFrSid the primary key of the rs details fr
    * @return the rs details fr, or <code>null</code> if a rs details fr with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RsDetailsFr fetchByPrimaryKey(int rsDetailsFrSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the rs details frs.
    *
    * @return the rs details frs
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RsDetailsFr> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the rs details frs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of rs details frs
    * @param end the upper bound of the range of rs details frs (not inclusive)
    * @return the range of rs details frs
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RsDetailsFr> findAll(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the rs details frs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RsDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of rs details frs
    * @param end the upper bound of the range of rs details frs (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of rs details frs
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RsDetailsFr> findAll(int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the rs details frs from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of rs details frs.
    *
    * @return the number of rs details frs
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
