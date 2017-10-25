package com.stpl.app.service.persistence;

import com.stpl.app.model.HistItemGroup;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the hist item group service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HistItemGroupPersistenceImpl
 * @see HistItemGroupUtil
 * @generated
 */
public interface HistItemGroupPersistence extends BasePersistence<HistItemGroup> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link HistItemGroupUtil} to access the hist item group persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the hist item group in the entity cache if it is enabled.
    *
    * @param histItemGroup the hist item group
    */
    public void cacheResult(com.stpl.app.model.HistItemGroup histItemGroup);

    /**
    * Caches the hist item groups in the entity cache if it is enabled.
    *
    * @param histItemGroups the hist item groups
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.HistItemGroup> histItemGroups);

    /**
    * Creates a new hist item group with the primary key. Does not add the hist item group to the database.
    *
    * @param histItemGroupPK the primary key for the new hist item group
    * @return the new hist item group
    */
    public com.stpl.app.model.HistItemGroup create(
        HistItemGroupPK histItemGroupPK);

    /**
    * Removes the hist item group with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param histItemGroupPK the primary key of the hist item group
    * @return the hist item group that was removed
    * @throws com.stpl.app.NoSuchHistItemGroupException if a hist item group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HistItemGroup remove(
        HistItemGroupPK histItemGroupPK)
        throws com.stpl.app.NoSuchHistItemGroupException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.HistItemGroup updateImpl(
        com.stpl.app.model.HistItemGroup histItemGroup)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the hist item group with the primary key or throws a {@link com.stpl.app.NoSuchHistItemGroupException} if it could not be found.
    *
    * @param histItemGroupPK the primary key of the hist item group
    * @return the hist item group
    * @throws com.stpl.app.NoSuchHistItemGroupException if a hist item group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HistItemGroup findByPrimaryKey(
        HistItemGroupPK histItemGroupPK)
        throws com.stpl.app.NoSuchHistItemGroupException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the hist item group with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param histItemGroupPK the primary key of the hist item group
    * @return the hist item group, or <code>null</code> if a hist item group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HistItemGroup fetchByPrimaryKey(
        HistItemGroupPK histItemGroupPK)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the hist item groups.
    *
    * @return the hist item groups
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.HistItemGroup> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the hist item groups.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hist item groups
    * @param end the upper bound of the range of hist item groups (not inclusive)
    * @return the range of hist item groups
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.HistItemGroup> findAll(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the hist item groups.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hist item groups
    * @param end the upper bound of the range of hist item groups (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of hist item groups
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.HistItemGroup> findAll(int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the hist item groups from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of hist item groups.
    *
    * @return the number of hist item groups
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
