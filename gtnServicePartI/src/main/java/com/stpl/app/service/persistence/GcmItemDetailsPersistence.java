package com.stpl.app.service.persistence;

import com.stpl.app.model.GcmItemDetails;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the gcm item details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see GcmItemDetailsPersistenceImpl
 * @see GcmItemDetailsUtil
 * @generated
 */
public interface GcmItemDetailsPersistence extends BasePersistence<GcmItemDetails> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link GcmItemDetailsUtil} to access the gcm item details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the gcm item details in the entity cache if it is enabled.
    *
    * @param gcmItemDetails the gcm item details
    */
    public void cacheResult(com.stpl.app.model.GcmItemDetails gcmItemDetails);

    /**
    * Caches the gcm item detailses in the entity cache if it is enabled.
    *
    * @param gcmItemDetailses the gcm item detailses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.GcmItemDetails> gcmItemDetailses);

    /**
    * Creates a new gcm item details with the primary key. Does not add the gcm item details to the database.
    *
    * @param gcmItemDetailsSid the primary key for the new gcm item details
    * @return the new gcm item details
    */
    public com.stpl.app.model.GcmItemDetails create(int gcmItemDetailsSid);

    /**
    * Removes the gcm item details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param gcmItemDetailsSid the primary key of the gcm item details
    * @return the gcm item details that was removed
    * @throws com.stpl.app.NoSuchGcmItemDetailsException if a gcm item details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GcmItemDetails remove(int gcmItemDetailsSid)
        throws com.stpl.app.NoSuchGcmItemDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.GcmItemDetails updateImpl(
        com.stpl.app.model.GcmItemDetails gcmItemDetails)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the gcm item details with the primary key or throws a {@link com.stpl.app.NoSuchGcmItemDetailsException} if it could not be found.
    *
    * @param gcmItemDetailsSid the primary key of the gcm item details
    * @return the gcm item details
    * @throws com.stpl.app.NoSuchGcmItemDetailsException if a gcm item details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GcmItemDetails findByPrimaryKey(
        int gcmItemDetailsSid)
        throws com.stpl.app.NoSuchGcmItemDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the gcm item details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param gcmItemDetailsSid the primary key of the gcm item details
    * @return the gcm item details, or <code>null</code> if a gcm item details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.GcmItemDetails fetchByPrimaryKey(
        int gcmItemDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the gcm item detailses.
    *
    * @return the gcm item detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.GcmItemDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the gcm item detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GcmItemDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of gcm item detailses
    * @param end the upper bound of the range of gcm item detailses (not inclusive)
    * @return the range of gcm item detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.GcmItemDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the gcm item detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.GcmItemDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of gcm item detailses
    * @param end the upper bound of the range of gcm item detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of gcm item detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.GcmItemDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the gcm item detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of gcm item detailses.
    *
    * @return the number of gcm item detailses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
