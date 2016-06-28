package com.stpl.app.service.persistence;

import com.stpl.app.model.HistCompanyGroup;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the hist company group service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HistCompanyGroupPersistenceImpl
 * @see HistCompanyGroupUtil
 * @generated
 */
public interface HistCompanyGroupPersistence extends BasePersistence<HistCompanyGroup> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link HistCompanyGroupUtil} to access the hist company group persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the hist company group in the entity cache if it is enabled.
    *
    * @param histCompanyGroup the hist company group
    */
    public void cacheResult(
        com.stpl.app.model.HistCompanyGroup histCompanyGroup);

    /**
    * Caches the hist company groups in the entity cache if it is enabled.
    *
    * @param histCompanyGroups the hist company groups
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.HistCompanyGroup> histCompanyGroups);

    /**
    * Creates a new hist company group with the primary key. Does not add the hist company group to the database.
    *
    * @param histCompanyGroupPK the primary key for the new hist company group
    * @return the new hist company group
    */
    public com.stpl.app.model.HistCompanyGroup create(
        HistCompanyGroupPK histCompanyGroupPK);

    /**
    * Removes the hist company group with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param histCompanyGroupPK the primary key of the hist company group
    * @return the hist company group that was removed
    * @throws com.stpl.app.NoSuchHistCompanyGroupException if a hist company group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HistCompanyGroup remove(
        HistCompanyGroupPK histCompanyGroupPK)
        throws com.stpl.app.NoSuchHistCompanyGroupException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.HistCompanyGroup updateImpl(
        com.stpl.app.model.HistCompanyGroup histCompanyGroup)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the hist company group with the primary key or throws a {@link com.stpl.app.NoSuchHistCompanyGroupException} if it could not be found.
    *
    * @param histCompanyGroupPK the primary key of the hist company group
    * @return the hist company group
    * @throws com.stpl.app.NoSuchHistCompanyGroupException if a hist company group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HistCompanyGroup findByPrimaryKey(
        HistCompanyGroupPK histCompanyGroupPK)
        throws com.stpl.app.NoSuchHistCompanyGroupException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the hist company group with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param histCompanyGroupPK the primary key of the hist company group
    * @return the hist company group, or <code>null</code> if a hist company group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HistCompanyGroup fetchByPrimaryKey(
        HistCompanyGroupPK histCompanyGroupPK)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the hist company groups.
    *
    * @return the hist company groups
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.HistCompanyGroup> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the hist company groups.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistCompanyGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hist company groups
    * @param end the upper bound of the range of hist company groups (not inclusive)
    * @return the range of hist company groups
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.HistCompanyGroup> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the hist company groups.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistCompanyGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hist company groups
    * @param end the upper bound of the range of hist company groups (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of hist company groups
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.HistCompanyGroup> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the hist company groups from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of hist company groups.
    *
    * @return the number of hist company groups
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
