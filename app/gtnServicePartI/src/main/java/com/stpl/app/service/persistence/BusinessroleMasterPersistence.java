package com.stpl.app.service.persistence;

import com.stpl.app.model.BusinessroleMaster;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the businessrole master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see BusinessroleMasterPersistenceImpl
 * @see BusinessroleMasterUtil
 * @generated
 */
public interface BusinessroleMasterPersistence extends BasePersistence<BusinessroleMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link BusinessroleMasterUtil} to access the businessrole master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Returns all the businessrole masters where businessroleName = &#63;.
    *
    * @param businessroleName the businessrole name
    * @return the matching businessrole masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.BusinessroleMaster> findByBusinessroleName(
        java.lang.String businessroleName)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the businessrole masters where businessroleName = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BusinessroleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param businessroleName the businessrole name
    * @param start the lower bound of the range of businessrole masters
    * @param end the upper bound of the range of businessrole masters (not inclusive)
    * @return the range of matching businessrole masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.BusinessroleMaster> findByBusinessroleName(
        java.lang.String businessroleName, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the businessrole masters where businessroleName = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BusinessroleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param businessroleName the businessrole name
    * @param start the lower bound of the range of businessrole masters
    * @param end the upper bound of the range of businessrole masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching businessrole masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.BusinessroleMaster> findByBusinessroleName(
        java.lang.String businessroleName, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first businessrole master in the ordered set where businessroleName = &#63;.
    *
    * @param businessroleName the businessrole name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching businessrole master
    * @throws com.stpl.app.NoSuchBusinessroleMasterException if a matching businessrole master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.BusinessroleMaster findByBusinessroleName_First(
        java.lang.String businessroleName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchBusinessroleMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first businessrole master in the ordered set where businessroleName = &#63;.
    *
    * @param businessroleName the businessrole name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching businessrole master, or <code>null</code> if a matching businessrole master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.BusinessroleMaster fetchByBusinessroleName_First(
        java.lang.String businessroleName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last businessrole master in the ordered set where businessroleName = &#63;.
    *
    * @param businessroleName the businessrole name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching businessrole master
    * @throws com.stpl.app.NoSuchBusinessroleMasterException if a matching businessrole master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.BusinessroleMaster findByBusinessroleName_Last(
        java.lang.String businessroleName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchBusinessroleMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last businessrole master in the ordered set where businessroleName = &#63;.
    *
    * @param businessroleName the businessrole name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching businessrole master, or <code>null</code> if a matching businessrole master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.BusinessroleMaster fetchByBusinessroleName_Last(
        java.lang.String businessroleName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the businessrole masters before and after the current businessrole master in the ordered set where businessroleName = &#63;.
    *
    * @param businessroleMasterSid the primary key of the current businessrole master
    * @param businessroleName the businessrole name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next businessrole master
    * @throws com.stpl.app.NoSuchBusinessroleMasterException if a businessrole master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.BusinessroleMaster[] findByBusinessroleName_PrevAndNext(
        int businessroleMasterSid, java.lang.String businessroleName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchBusinessroleMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the businessrole masters where businessroleName = &#63; from the database.
    *
    * @param businessroleName the businessrole name
    * @throws SystemException if a system exception occurred
    */
    public void removeByBusinessroleName(java.lang.String businessroleName)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of businessrole masters where businessroleName = &#63;.
    *
    * @param businessroleName the businessrole name
    * @return the number of matching businessrole masters
    * @throws SystemException if a system exception occurred
    */
    public int countByBusinessroleName(java.lang.String businessroleName)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the businessrole masters where businessroleName = &#63;.
    *
    * @param businessroleName the businessrole name
    * @return the matching businessrole masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.BusinessroleMaster> findByBusinessRoleUnique(
        java.lang.String businessroleName)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the businessrole masters where businessroleName = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BusinessroleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param businessroleName the businessrole name
    * @param start the lower bound of the range of businessrole masters
    * @param end the upper bound of the range of businessrole masters (not inclusive)
    * @return the range of matching businessrole masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.BusinessroleMaster> findByBusinessRoleUnique(
        java.lang.String businessroleName, int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the businessrole masters where businessroleName = &#63;.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BusinessroleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param businessroleName the businessrole name
    * @param start the lower bound of the range of businessrole masters
    * @param end the upper bound of the range of businessrole masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching businessrole masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.BusinessroleMaster> findByBusinessRoleUnique(
        java.lang.String businessroleName, int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first businessrole master in the ordered set where businessroleName = &#63;.
    *
    * @param businessroleName the businessrole name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching businessrole master
    * @throws com.stpl.app.NoSuchBusinessroleMasterException if a matching businessrole master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.BusinessroleMaster findByBusinessRoleUnique_First(
        java.lang.String businessroleName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchBusinessroleMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the first businessrole master in the ordered set where businessroleName = &#63;.
    *
    * @param businessroleName the businessrole name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the first matching businessrole master, or <code>null</code> if a matching businessrole master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.BusinessroleMaster fetchByBusinessRoleUnique_First(
        java.lang.String businessroleName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last businessrole master in the ordered set where businessroleName = &#63;.
    *
    * @param businessroleName the businessrole name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching businessrole master
    * @throws com.stpl.app.NoSuchBusinessroleMasterException if a matching businessrole master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.BusinessroleMaster findByBusinessRoleUnique_Last(
        java.lang.String businessroleName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchBusinessroleMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the last businessrole master in the ordered set where businessroleName = &#63;.
    *
    * @param businessroleName the businessrole name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the last matching businessrole master, or <code>null</code> if a matching businessrole master could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.BusinessroleMaster fetchByBusinessRoleUnique_Last(
        java.lang.String businessroleName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the businessrole masters before and after the current businessrole master in the ordered set where businessroleName = &#63;.
    *
    * @param businessroleMasterSid the primary key of the current businessrole master
    * @param businessroleName the businessrole name
    * @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
    * @return the previous, current, and next businessrole master
    * @throws com.stpl.app.NoSuchBusinessroleMasterException if a businessrole master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.BusinessroleMaster[] findByBusinessRoleUnique_PrevAndNext(
        int businessroleMasterSid, java.lang.String businessroleName,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.app.NoSuchBusinessroleMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the businessrole masters where businessroleName = &#63; from the database.
    *
    * @param businessroleName the businessrole name
    * @throws SystemException if a system exception occurred
    */
    public void removeByBusinessRoleUnique(java.lang.String businessroleName)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of businessrole masters where businessroleName = &#63;.
    *
    * @param businessroleName the businessrole name
    * @return the number of matching businessrole masters
    * @throws SystemException if a system exception occurred
    */
    public int countByBusinessRoleUnique(java.lang.String businessroleName)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Caches the businessrole master in the entity cache if it is enabled.
    *
    * @param businessroleMaster the businessrole master
    */
    public void cacheResult(
        com.stpl.app.model.BusinessroleMaster businessroleMaster);

    /**
    * Caches the businessrole masters in the entity cache if it is enabled.
    *
    * @param businessroleMasters the businessrole masters
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.BusinessroleMaster> businessroleMasters);

    /**
    * Creates a new businessrole master with the primary key. Does not add the businessrole master to the database.
    *
    * @param businessroleMasterSid the primary key for the new businessrole master
    * @return the new businessrole master
    */
    public com.stpl.app.model.BusinessroleMaster create(
        int businessroleMasterSid);

    /**
    * Removes the businessrole master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param businessroleMasterSid the primary key of the businessrole master
    * @return the businessrole master that was removed
    * @throws com.stpl.app.NoSuchBusinessroleMasterException if a businessrole master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.BusinessroleMaster remove(
        int businessroleMasterSid)
        throws com.stpl.app.NoSuchBusinessroleMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.BusinessroleMaster updateImpl(
        com.stpl.app.model.BusinessroleMaster businessroleMaster)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the businessrole master with the primary key or throws a {@link com.stpl.app.NoSuchBusinessroleMasterException} if it could not be found.
    *
    * @param businessroleMasterSid the primary key of the businessrole master
    * @return the businessrole master
    * @throws com.stpl.app.NoSuchBusinessroleMasterException if a businessrole master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.BusinessroleMaster findByPrimaryKey(
        int businessroleMasterSid)
        throws com.stpl.app.NoSuchBusinessroleMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the businessrole master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param businessroleMasterSid the primary key of the businessrole master
    * @return the businessrole master, or <code>null</code> if a businessrole master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.BusinessroleMaster fetchByPrimaryKey(
        int businessroleMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the businessrole masters.
    *
    * @return the businessrole masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.BusinessroleMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the businessrole masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BusinessroleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of businessrole masters
    * @param end the upper bound of the range of businessrole masters (not inclusive)
    * @return the range of businessrole masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.BusinessroleMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the businessrole masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BusinessroleMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of businessrole masters
    * @param end the upper bound of the range of businessrole masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of businessrole masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.BusinessroleMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the businessrole masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of businessrole masters.
    *
    * @return the number of businessrole masters
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
