package com.stpl.app.service.persistence;

import com.stpl.app.model.ImtdRsContractDetailsFr;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the imtd rs contract details fr service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ImtdRsContractDetailsFrPersistenceImpl
 * @see ImtdRsContractDetailsFrUtil
 * @generated
 */
public interface ImtdRsContractDetailsFrPersistence extends BasePersistence<ImtdRsContractDetailsFr> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ImtdRsContractDetailsFrUtil} to access the imtd rs contract details fr persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the imtd rs contract details fr in the entity cache if it is enabled.
    *
    * @param imtdRsContractDetailsFr the imtd rs contract details fr
    */
    public void cacheResult(
        com.stpl.app.model.ImtdRsContractDetailsFr imtdRsContractDetailsFr);

    /**
    * Caches the imtd rs contract details frs in the entity cache if it is enabled.
    *
    * @param imtdRsContractDetailsFrs the imtd rs contract details frs
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.ImtdRsContractDetailsFr> imtdRsContractDetailsFrs);

    /**
    * Creates a new imtd rs contract details fr with the primary key. Does not add the imtd rs contract details fr to the database.
    *
    * @param imtdRsContractDetailsFrSid the primary key for the new imtd rs contract details fr
    * @return the new imtd rs contract details fr
    */
    public com.stpl.app.model.ImtdRsContractDetailsFr create(
        int imtdRsContractDetailsFrSid);

    /**
    * Removes the imtd rs contract details fr with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param imtdRsContractDetailsFrSid the primary key of the imtd rs contract details fr
    * @return the imtd rs contract details fr that was removed
    * @throws com.stpl.app.NoSuchImtdRsContractDetailsFrException if a imtd rs contract details fr with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ImtdRsContractDetailsFr remove(
        int imtdRsContractDetailsFrSid)
        throws com.stpl.app.NoSuchImtdRsContractDetailsFrException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.ImtdRsContractDetailsFr updateImpl(
        com.stpl.app.model.ImtdRsContractDetailsFr imtdRsContractDetailsFr)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the imtd rs contract details fr with the primary key or throws a {@link com.stpl.app.NoSuchImtdRsContractDetailsFrException} if it could not be found.
    *
    * @param imtdRsContractDetailsFrSid the primary key of the imtd rs contract details fr
    * @return the imtd rs contract details fr
    * @throws com.stpl.app.NoSuchImtdRsContractDetailsFrException if a imtd rs contract details fr with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ImtdRsContractDetailsFr findByPrimaryKey(
        int imtdRsContractDetailsFrSid)
        throws com.stpl.app.NoSuchImtdRsContractDetailsFrException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the imtd rs contract details fr with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param imtdRsContractDetailsFrSid the primary key of the imtd rs contract details fr
    * @return the imtd rs contract details fr, or <code>null</code> if a imtd rs contract details fr with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ImtdRsContractDetailsFr fetchByPrimaryKey(
        int imtdRsContractDetailsFrSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the imtd rs contract details frs.
    *
    * @return the imtd rs contract details frs
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ImtdRsContractDetailsFr> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the imtd rs contract details frs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdRsContractDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of imtd rs contract details frs
    * @param end the upper bound of the range of imtd rs contract details frs (not inclusive)
    * @return the range of imtd rs contract details frs
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ImtdRsContractDetailsFr> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the imtd rs contract details frs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdRsContractDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of imtd rs contract details frs
    * @param end the upper bound of the range of imtd rs contract details frs (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of imtd rs contract details frs
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ImtdRsContractDetailsFr> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the imtd rs contract details frs from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of imtd rs contract details frs.
    *
    * @return the number of imtd rs contract details frs
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
