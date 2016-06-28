package com.stpl.app.service.persistence;

import com.stpl.app.model.HistItemGroupDetails;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the hist item group details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HistItemGroupDetailsPersistenceImpl
 * @see HistItemGroupDetailsUtil
 * @generated
 */
public interface HistItemGroupDetailsPersistence extends BasePersistence<HistItemGroupDetails> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link HistItemGroupDetailsUtil} to access the hist item group details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the hist item group details in the entity cache if it is enabled.
    *
    * @param histItemGroupDetails the hist item group details
    */
    public void cacheResult(
        com.stpl.app.model.HistItemGroupDetails histItemGroupDetails);

    /**
    * Caches the hist item group detailses in the entity cache if it is enabled.
    *
    * @param histItemGroupDetailses the hist item group detailses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.HistItemGroupDetails> histItemGroupDetailses);

    /**
    * Creates a new hist item group details with the primary key. Does not add the hist item group details to the database.
    *
    * @param histItemGroupDetailsPK the primary key for the new hist item group details
    * @return the new hist item group details
    */
    public com.stpl.app.model.HistItemGroupDetails create(
        HistItemGroupDetailsPK histItemGroupDetailsPK);

    /**
    * Removes the hist item group details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param histItemGroupDetailsPK the primary key of the hist item group details
    * @return the hist item group details that was removed
    * @throws com.stpl.app.NoSuchHistItemGroupDetailsException if a hist item group details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HistItemGroupDetails remove(
        HistItemGroupDetailsPK histItemGroupDetailsPK)
        throws com.stpl.app.NoSuchHistItemGroupDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.HistItemGroupDetails updateImpl(
        com.stpl.app.model.HistItemGroupDetails histItemGroupDetails)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the hist item group details with the primary key or throws a {@link com.stpl.app.NoSuchHistItemGroupDetailsException} if it could not be found.
    *
    * @param histItemGroupDetailsPK the primary key of the hist item group details
    * @return the hist item group details
    * @throws com.stpl.app.NoSuchHistItemGroupDetailsException if a hist item group details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HistItemGroupDetails findByPrimaryKey(
        HistItemGroupDetailsPK histItemGroupDetailsPK)
        throws com.stpl.app.NoSuchHistItemGroupDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the hist item group details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param histItemGroupDetailsPK the primary key of the hist item group details
    * @return the hist item group details, or <code>null</code> if a hist item group details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HistItemGroupDetails fetchByPrimaryKey(
        HistItemGroupDetailsPK histItemGroupDetailsPK)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the hist item group detailses.
    *
    * @return the hist item group detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.HistItemGroupDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the hist item group detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistItemGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hist item group detailses
    * @param end the upper bound of the range of hist item group detailses (not inclusive)
    * @return the range of hist item group detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.HistItemGroupDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the hist item group detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistItemGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hist item group detailses
    * @param end the upper bound of the range of hist item group detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of hist item group detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.HistItemGroupDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the hist item group detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of hist item group detailses.
    *
    * @return the number of hist item group detailses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
