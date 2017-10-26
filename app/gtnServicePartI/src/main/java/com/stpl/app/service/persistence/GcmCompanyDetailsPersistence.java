package com.stpl.app.service.persistence;

import com.stpl.app.model.GcmCompanyDetails;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the gcm company details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see GcmCompanyDetailsPersistenceImpl
 * @see GcmCompanyDetailsUtil
 * @generated
 */
public interface GcmCompanyDetailsPersistence extends BasePersistence<GcmCompanyDetails> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link GcmCompanyDetailsUtil} to access the gcm company details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the gcm company details in the entity cache if it is enabled.
    *
    * @param gcmCompanyDetails the gcm company details
    */
    public void cacheResult(
        com.stpl.app.model.GcmCompanyDetails gcmCompanyDetails);

    /**
    * Caches the gcm company detailses in the entity cache if it is enabled.
    *
    * @param gcmCompanyDetailses the gcm company detailses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.GcmCompanyDetails> gcmCompanyDetailses);

    /**
    * Creates a new gcm company details with the primary key. Does not add the gcm company details to the database.
    *
    * @param gcmCompanyDetailsSid the primary key for the new gcm company details
    * @return the new gcm company details
    */
    public com.stpl.app.model.GcmCompanyDetails create(int gcmCompanyDetailsSid);

    /**
    * Removes the gcm company details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param gcmCompanyDetailsSid the primary key of the gcm company details
    * @return the gcm company details that was removed
    * @throws com.stpl.app.NoSuchGcmCompanyDetailsException if a gcm company details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GcmCompanyDetails remove(int gcmCompanyDetailsSid)
        throws com.stpl.app.NoSuchGcmCompanyDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.GcmCompanyDetails updateImpl(
        com.stpl.app.model.GcmCompanyDetails gcmCompanyDetails)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the gcm company details with the primary key or throws a {@link com.stpl.app.NoSuchGcmCompanyDetailsException} if it could not be found.
    *
    * @param gcmCompanyDetailsSid the primary key of the gcm company details
    * @return the gcm company details
    * @throws com.stpl.app.NoSuchGcmCompanyDetailsException if a gcm company details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GcmCompanyDetails findByPrimaryKey(
        int gcmCompanyDetailsSid)
        throws com.stpl.app.NoSuchGcmCompanyDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the gcm company details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param gcmCompanyDetailsSid the primary key of the gcm company details
    * @return the gcm company details, or <code>null</code> if a gcm company details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GcmCompanyDetails fetchByPrimaryKey(
        int gcmCompanyDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the gcm company detailses.
    *
    * @return the gcm company detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.GcmCompanyDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the gcm company detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GcmCompanyDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of gcm company detailses
    * @param end the upper bound of the range of gcm company detailses (not inclusive)
    * @return the range of gcm company detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.GcmCompanyDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the gcm company detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GcmCompanyDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of gcm company detailses
    * @param end the upper bound of the range of gcm company detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of gcm company detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.GcmCompanyDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the gcm company detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of gcm company detailses.
    *
    * @return the number of gcm company detailses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
