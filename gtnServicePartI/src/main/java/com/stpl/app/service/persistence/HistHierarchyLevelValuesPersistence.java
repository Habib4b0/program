package com.stpl.app.service.persistence;

import com.stpl.app.model.HistHierarchyLevelValues;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the hist hierarchy level values service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HistHierarchyLevelValuesPersistenceImpl
 * @see HistHierarchyLevelValuesUtil
 * @generated
 */
public interface HistHierarchyLevelValuesPersistence extends BasePersistence<HistHierarchyLevelValues> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link HistHierarchyLevelValuesUtil} to access the hist hierarchy level values persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the hist hierarchy level values in the entity cache if it is enabled.
    *
    * @param histHierarchyLevelValues the hist hierarchy level values
    */
    public void cacheResult(
        com.stpl.app.model.HistHierarchyLevelValues histHierarchyLevelValues);

    /**
    * Caches the hist hierarchy level valueses in the entity cache if it is enabled.
    *
    * @param histHierarchyLevelValueses the hist hierarchy level valueses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.HistHierarchyLevelValues> histHierarchyLevelValueses);

    /**
    * Creates a new hist hierarchy level values with the primary key. Does not add the hist hierarchy level values to the database.
    *
    * @param histHierarchyLevelValuesPK the primary key for the new hist hierarchy level values
    * @return the new hist hierarchy level values
    */
    public com.stpl.app.model.HistHierarchyLevelValues create(
        HistHierarchyLevelValuesPK histHierarchyLevelValuesPK);

    /**
    * Removes the hist hierarchy level values with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param histHierarchyLevelValuesPK the primary key of the hist hierarchy level values
    * @return the hist hierarchy level values that was removed
    * @throws com.stpl.app.NoSuchHistHierarchyLevelValuesException if a hist hierarchy level values with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HistHierarchyLevelValues remove(
        HistHierarchyLevelValuesPK histHierarchyLevelValuesPK)
        throws com.stpl.app.NoSuchHistHierarchyLevelValuesException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.HistHierarchyLevelValues updateImpl(
        com.stpl.app.model.HistHierarchyLevelValues histHierarchyLevelValues)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the hist hierarchy level values with the primary key or throws a {@link com.stpl.app.NoSuchHistHierarchyLevelValuesException} if it could not be found.
    *
    * @param histHierarchyLevelValuesPK the primary key of the hist hierarchy level values
    * @return the hist hierarchy level values
    * @throws com.stpl.app.NoSuchHistHierarchyLevelValuesException if a hist hierarchy level values with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HistHierarchyLevelValues findByPrimaryKey(
        HistHierarchyLevelValuesPK histHierarchyLevelValuesPK)
        throws com.stpl.app.NoSuchHistHierarchyLevelValuesException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the hist hierarchy level values with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param histHierarchyLevelValuesPK the primary key of the hist hierarchy level values
    * @return the hist hierarchy level values, or <code>null</code> if a hist hierarchy level values with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HistHierarchyLevelValues fetchByPrimaryKey(
        HistHierarchyLevelValuesPK histHierarchyLevelValuesPK)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the hist hierarchy level valueses.
    *
    * @return the hist hierarchy level valueses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.HistHierarchyLevelValues> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the hist hierarchy level valueses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistHierarchyLevelValuesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hist hierarchy level valueses
    * @param end the upper bound of the range of hist hierarchy level valueses (not inclusive)
    * @return the range of hist hierarchy level valueses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.HistHierarchyLevelValues> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the hist hierarchy level valueses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistHierarchyLevelValuesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hist hierarchy level valueses
    * @param end the upper bound of the range of hist hierarchy level valueses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of hist hierarchy level valueses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.HistHierarchyLevelValues> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the hist hierarchy level valueses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of hist hierarchy level valueses.
    *
    * @return the number of hist hierarchy level valueses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
