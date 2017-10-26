package com.stpl.app.service.persistence;

import com.stpl.app.model.HierarchyLevelValues;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the hierarchy level values service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HierarchyLevelValuesPersistenceImpl
 * @see HierarchyLevelValuesUtil
 * @generated
 */
public interface HierarchyLevelValuesPersistence extends BasePersistence<HierarchyLevelValues> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link HierarchyLevelValuesUtil} to access the hierarchy level values persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the hierarchy level values in the entity cache if it is enabled.
    *
    * @param hierarchyLevelValues the hierarchy level values
    */
    public void cacheResult(
        com.stpl.app.model.HierarchyLevelValues hierarchyLevelValues);

    /**
    * Caches the hierarchy level valueses in the entity cache if it is enabled.
    *
    * @param hierarchyLevelValueses the hierarchy level valueses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.HierarchyLevelValues> hierarchyLevelValueses);

    /**
    * Creates a new hierarchy level values with the primary key. Does not add the hierarchy level values to the database.
    *
    * @param hierarchyLevelValuesSid the primary key for the new hierarchy level values
    * @return the new hierarchy level values
    */
    public com.stpl.app.model.HierarchyLevelValues create(
        int hierarchyLevelValuesSid);

    /**
    * Removes the hierarchy level values with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param hierarchyLevelValuesSid the primary key of the hierarchy level values
    * @return the hierarchy level values that was removed
    * @throws com.stpl.app.NoSuchHierarchyLevelValuesException if a hierarchy level values with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HierarchyLevelValues remove(
        int hierarchyLevelValuesSid)
        throws com.stpl.app.NoSuchHierarchyLevelValuesException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.HierarchyLevelValues updateImpl(
        com.stpl.app.model.HierarchyLevelValues hierarchyLevelValues)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the hierarchy level values with the primary key or throws a {@link com.stpl.app.NoSuchHierarchyLevelValuesException} if it could not be found.
    *
    * @param hierarchyLevelValuesSid the primary key of the hierarchy level values
    * @return the hierarchy level values
    * @throws com.stpl.app.NoSuchHierarchyLevelValuesException if a hierarchy level values with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HierarchyLevelValues findByPrimaryKey(
        int hierarchyLevelValuesSid)
        throws com.stpl.app.NoSuchHierarchyLevelValuesException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the hierarchy level values with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param hierarchyLevelValuesSid the primary key of the hierarchy level values
    * @return the hierarchy level values, or <code>null</code> if a hierarchy level values with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HierarchyLevelValues fetchByPrimaryKey(
        int hierarchyLevelValuesSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the hierarchy level valueses.
    *
    * @return the hierarchy level valueses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.HierarchyLevelValues> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the hierarchy level valueses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HierarchyLevelValuesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hierarchy level valueses
    * @param end the upper bound of the range of hierarchy level valueses (not inclusive)
    * @return the range of hierarchy level valueses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.HierarchyLevelValues> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the hierarchy level valueses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HierarchyLevelValuesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hierarchy level valueses
    * @param end the upper bound of the range of hierarchy level valueses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of hierarchy level valueses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.HierarchyLevelValues> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the hierarchy level valueses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of hierarchy level valueses.
    *
    * @return the number of hierarchy level valueses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
