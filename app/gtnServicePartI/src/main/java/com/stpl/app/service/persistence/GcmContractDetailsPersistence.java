package com.stpl.app.service.persistence;

import com.stpl.app.model.GcmContractDetails;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the gcm contract details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see GcmContractDetailsPersistenceImpl
 * @see GcmContractDetailsUtil
 * @generated
 */
public interface GcmContractDetailsPersistence extends BasePersistence<GcmContractDetails> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link GcmContractDetailsUtil} to access the gcm contract details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the gcm contract details in the entity cache if it is enabled.
    *
    * @param gcmContractDetails the gcm contract details
    */
    public void cacheResult(
        com.stpl.app.model.GcmContractDetails gcmContractDetails);

    /**
    * Caches the gcm contract detailses in the entity cache if it is enabled.
    *
    * @param gcmContractDetailses the gcm contract detailses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.GcmContractDetails> gcmContractDetailses);

    /**
    * Creates a new gcm contract details with the primary key. Does not add the gcm contract details to the database.
    *
    * @param gcmContractDetailsSid the primary key for the new gcm contract details
    * @return the new gcm contract details
    */
    public com.stpl.app.model.GcmContractDetails create(
        int gcmContractDetailsSid);

    /**
    * Removes the gcm contract details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param gcmContractDetailsSid the primary key of the gcm contract details
    * @return the gcm contract details that was removed
    * @throws com.stpl.app.NoSuchGcmContractDetailsException if a gcm contract details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GcmContractDetails remove(
        int gcmContractDetailsSid)
        throws com.stpl.app.NoSuchGcmContractDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.GcmContractDetails updateImpl(
        com.stpl.app.model.GcmContractDetails gcmContractDetails)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the gcm contract details with the primary key or throws a {@link com.stpl.app.NoSuchGcmContractDetailsException} if it could not be found.
    *
    * @param gcmContractDetailsSid the primary key of the gcm contract details
    * @return the gcm contract details
    * @throws com.stpl.app.NoSuchGcmContractDetailsException if a gcm contract details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GcmContractDetails findByPrimaryKey(
        int gcmContractDetailsSid)
        throws com.stpl.app.NoSuchGcmContractDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the gcm contract details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param gcmContractDetailsSid the primary key of the gcm contract details
    * @return the gcm contract details, or <code>null</code> if a gcm contract details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GcmContractDetails fetchByPrimaryKey(
        int gcmContractDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the gcm contract detailses.
    *
    * @return the gcm contract detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.GcmContractDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the gcm contract detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GcmContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of gcm contract detailses
    * @param end the upper bound of the range of gcm contract detailses (not inclusive)
    * @return the range of gcm contract detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.GcmContractDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the gcm contract detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GcmContractDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of gcm contract detailses
    * @param end the upper bound of the range of gcm contract detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of gcm contract detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.GcmContractDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the gcm contract detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of gcm contract detailses.
    *
    * @return the number of gcm contract detailses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
