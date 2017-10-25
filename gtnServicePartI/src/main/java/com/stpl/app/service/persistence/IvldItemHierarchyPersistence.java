package com.stpl.app.service.persistence;

import com.stpl.app.model.IvldItemHierarchy;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the ivld item hierarchy service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldItemHierarchyPersistenceImpl
 * @see IvldItemHierarchyUtil
 * @generated
 */
public interface IvldItemHierarchyPersistence extends BasePersistence<IvldItemHierarchy> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link IvldItemHierarchyUtil} to access the ivld item hierarchy persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the ivld item hierarchy in the entity cache if it is enabled.
    *
    * @param ivldItemHierarchy the ivld item hierarchy
    */
    public void cacheResult(
        com.stpl.app.model.IvldItemHierarchy ivldItemHierarchy);

    /**
    * Caches the ivld item hierarchies in the entity cache if it is enabled.
    *
    * @param ivldItemHierarchies the ivld item hierarchies
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.IvldItemHierarchy> ivldItemHierarchies);

    /**
    * Creates a new ivld item hierarchy with the primary key. Does not add the ivld item hierarchy to the database.
    *
    * @param ivldItemHierarchySid the primary key for the new ivld item hierarchy
    * @return the new ivld item hierarchy
    */
    public com.stpl.app.model.IvldItemHierarchy create(int ivldItemHierarchySid);

    /**
    * Removes the ivld item hierarchy with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldItemHierarchySid the primary key of the ivld item hierarchy
    * @return the ivld item hierarchy that was removed
    * @throws com.stpl.app.NoSuchIvldItemHierarchyException if a ivld item hierarchy with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IvldItemHierarchy remove(int ivldItemHierarchySid)
        throws com.stpl.app.NoSuchIvldItemHierarchyException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.IvldItemHierarchy updateImpl(
        com.stpl.app.model.IvldItemHierarchy ivldItemHierarchy)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ivld item hierarchy with the primary key or throws a {@link com.stpl.app.NoSuchIvldItemHierarchyException} if it could not be found.
    *
    * @param ivldItemHierarchySid the primary key of the ivld item hierarchy
    * @return the ivld item hierarchy
    * @throws com.stpl.app.NoSuchIvldItemHierarchyException if a ivld item hierarchy with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IvldItemHierarchy findByPrimaryKey(
        int ivldItemHierarchySid)
        throws com.stpl.app.NoSuchIvldItemHierarchyException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ivld item hierarchy with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ivldItemHierarchySid the primary key of the ivld item hierarchy
    * @return the ivld item hierarchy, or <code>null</code> if a ivld item hierarchy with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.IvldItemHierarchy fetchByPrimaryKey(
        int ivldItemHierarchySid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the ivld item hierarchies.
    *
    * @return the ivld item hierarchies
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IvldItemHierarchy> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ivld item hierarchies.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldItemHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld item hierarchies
    * @param end the upper bound of the range of ivld item hierarchies (not inclusive)
    * @return the range of ivld item hierarchies
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IvldItemHierarchy> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ivld item hierarchies.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.IvldItemHierarchyModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ivld item hierarchies
    * @param end the upper bound of the range of ivld item hierarchies (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ivld item hierarchies
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.IvldItemHierarchy> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the ivld item hierarchies from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ivld item hierarchies.
    *
    * @return the number of ivld item hierarchies
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
