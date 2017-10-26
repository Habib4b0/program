package com.stpl.app.service.persistence;

import com.stpl.app.model.ImtdRsDetailsFr;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the imtd rs details fr service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ImtdRsDetailsFrPersistenceImpl
 * @see ImtdRsDetailsFrUtil
 * @generated
 */
public interface ImtdRsDetailsFrPersistence extends BasePersistence<ImtdRsDetailsFr> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ImtdRsDetailsFrUtil} to access the imtd rs details fr persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the imtd rs details fr in the entity cache if it is enabled.
    *
    * @param imtdRsDetailsFr the imtd rs details fr
    */
    public void cacheResult(com.stpl.app.model.ImtdRsDetailsFr imtdRsDetailsFr);

    /**
    * Caches the imtd rs details frs in the entity cache if it is enabled.
    *
    * @param imtdRsDetailsFrs the imtd rs details frs
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.ImtdRsDetailsFr> imtdRsDetailsFrs);

    /**
    * Creates a new imtd rs details fr with the primary key. Does not add the imtd rs details fr to the database.
    *
    * @param imtdRsDetailsFrSid the primary key for the new imtd rs details fr
    * @return the new imtd rs details fr
    */
    public com.stpl.app.model.ImtdRsDetailsFr create(int imtdRsDetailsFrSid);

    /**
    * Removes the imtd rs details fr with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param imtdRsDetailsFrSid the primary key of the imtd rs details fr
    * @return the imtd rs details fr that was removed
    * @throws com.stpl.app.NoSuchImtdRsDetailsFrException if a imtd rs details fr with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ImtdRsDetailsFr remove(int imtdRsDetailsFrSid)
        throws com.stpl.app.NoSuchImtdRsDetailsFrException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.ImtdRsDetailsFr updateImpl(
        com.stpl.app.model.ImtdRsDetailsFr imtdRsDetailsFr)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the imtd rs details fr with the primary key or throws a {@link com.stpl.app.NoSuchImtdRsDetailsFrException} if it could not be found.
    *
    * @param imtdRsDetailsFrSid the primary key of the imtd rs details fr
    * @return the imtd rs details fr
    * @throws com.stpl.app.NoSuchImtdRsDetailsFrException if a imtd rs details fr with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ImtdRsDetailsFr findByPrimaryKey(
        int imtdRsDetailsFrSid)
        throws com.stpl.app.NoSuchImtdRsDetailsFrException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the imtd rs details fr with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param imtdRsDetailsFrSid the primary key of the imtd rs details fr
    * @return the imtd rs details fr, or <code>null</code> if a imtd rs details fr with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ImtdRsDetailsFr fetchByPrimaryKey(
        int imtdRsDetailsFrSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the imtd rs details frs.
    *
    * @return the imtd rs details frs
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ImtdRsDetailsFr> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the imtd rs details frs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdRsDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of imtd rs details frs
    * @param end the upper bound of the range of imtd rs details frs (not inclusive)
    * @return the range of imtd rs details frs
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ImtdRsDetailsFr> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the imtd rs details frs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdRsDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of imtd rs details frs
    * @param end the upper bound of the range of imtd rs details frs (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of imtd rs details frs
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ImtdRsDetailsFr> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the imtd rs details frs from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of imtd rs details frs.
    *
    * @return the number of imtd rs details frs
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
