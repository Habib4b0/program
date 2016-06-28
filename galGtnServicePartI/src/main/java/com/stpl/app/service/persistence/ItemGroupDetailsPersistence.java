package com.stpl.app.service.persistence;

import com.stpl.app.model.ItemGroupDetails;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the item group details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ItemGroupDetailsPersistenceImpl
 * @see ItemGroupDetailsUtil
 * @generated
 */
public interface ItemGroupDetailsPersistence extends BasePersistence<ItemGroupDetails> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ItemGroupDetailsUtil} to access the item group details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the item group details in the entity cache if it is enabled.
    *
    * @param itemGroupDetails the item group details
    */
    public void cacheResult(
        com.stpl.app.model.ItemGroupDetails itemGroupDetails);

    /**
    * Caches the item group detailses in the entity cache if it is enabled.
    *
    * @param itemGroupDetailses the item group detailses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.ItemGroupDetails> itemGroupDetailses);

    /**
    * Creates a new item group details with the primary key. Does not add the item group details to the database.
    *
    * @param itemGroupDetailsSid the primary key for the new item group details
    * @return the new item group details
    */
    public com.stpl.app.model.ItemGroupDetails create(int itemGroupDetailsSid);

    /**
    * Removes the item group details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param itemGroupDetailsSid the primary key of the item group details
    * @return the item group details that was removed
    * @throws com.stpl.app.NoSuchItemGroupDetailsException if a item group details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemGroupDetails remove(int itemGroupDetailsSid)
        throws com.stpl.app.NoSuchItemGroupDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.ItemGroupDetails updateImpl(
        com.stpl.app.model.ItemGroupDetails itemGroupDetails)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the item group details with the primary key or throws a {@link com.stpl.app.NoSuchItemGroupDetailsException} if it could not be found.
    *
    * @param itemGroupDetailsSid the primary key of the item group details
    * @return the item group details
    * @throws com.stpl.app.NoSuchItemGroupDetailsException if a item group details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemGroupDetails findByPrimaryKey(
        int itemGroupDetailsSid)
        throws com.stpl.app.NoSuchItemGroupDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the item group details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param itemGroupDetailsSid the primary key of the item group details
    * @return the item group details, or <code>null</code> if a item group details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.ItemGroupDetails fetchByPrimaryKey(
        int itemGroupDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the item group detailses.
    *
    * @return the item group detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemGroupDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the item group detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of item group detailses
    * @param end the upper bound of the range of item group detailses (not inclusive)
    * @return the range of item group detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemGroupDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the item group detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of item group detailses
    * @param end the upper bound of the range of item group detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of item group detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.ItemGroupDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the item group detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of item group detailses.
    *
    * @return the number of item group detailses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
