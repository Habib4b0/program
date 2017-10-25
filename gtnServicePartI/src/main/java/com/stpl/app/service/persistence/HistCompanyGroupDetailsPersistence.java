package com.stpl.app.service.persistence;

import com.stpl.app.model.HistCompanyGroupDetails;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the hist company group details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HistCompanyGroupDetailsPersistenceImpl
 * @see HistCompanyGroupDetailsUtil
 * @generated
 */
public interface HistCompanyGroupDetailsPersistence extends BasePersistence<HistCompanyGroupDetails> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link HistCompanyGroupDetailsUtil} to access the hist company group details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the hist company group details in the entity cache if it is enabled.
    *
    * @param histCompanyGroupDetails the hist company group details
    */
    public void cacheResult(
        com.stpl.app.model.HistCompanyGroupDetails histCompanyGroupDetails);

    /**
    * Caches the hist company group detailses in the entity cache if it is enabled.
    *
    * @param histCompanyGroupDetailses the hist company group detailses
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.HistCompanyGroupDetails> histCompanyGroupDetailses);

    /**
    * Creates a new hist company group details with the primary key. Does not add the hist company group details to the database.
    *
    * @param histCompanyGroupDetailsPK the primary key for the new hist company group details
    * @return the new hist company group details
    */
    public com.stpl.app.model.HistCompanyGroupDetails create(
        HistCompanyGroupDetailsPK histCompanyGroupDetailsPK);

    /**
    * Removes the hist company group details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param histCompanyGroupDetailsPK the primary key of the hist company group details
    * @return the hist company group details that was removed
    * @throws com.stpl.app.NoSuchHistCompanyGroupDetailsException if a hist company group details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HistCompanyGroupDetails remove(
        HistCompanyGroupDetailsPK histCompanyGroupDetailsPK)
        throws com.stpl.app.NoSuchHistCompanyGroupDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.HistCompanyGroupDetails updateImpl(
        com.stpl.app.model.HistCompanyGroupDetails histCompanyGroupDetails)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the hist company group details with the primary key or throws a {@link com.stpl.app.NoSuchHistCompanyGroupDetailsException} if it could not be found.
    *
    * @param histCompanyGroupDetailsPK the primary key of the hist company group details
    * @return the hist company group details
    * @throws com.stpl.app.NoSuchHistCompanyGroupDetailsException if a hist company group details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HistCompanyGroupDetails findByPrimaryKey(
        HistCompanyGroupDetailsPK histCompanyGroupDetailsPK)
        throws com.stpl.app.NoSuchHistCompanyGroupDetailsException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the hist company group details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param histCompanyGroupDetailsPK the primary key of the hist company group details
    * @return the hist company group details, or <code>null</code> if a hist company group details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HistCompanyGroupDetails fetchByPrimaryKey(
        HistCompanyGroupDetailsPK histCompanyGroupDetailsPK)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the hist company group detailses.
    *
    * @return the hist company group detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.HistCompanyGroupDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the hist company group detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistCompanyGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hist company group detailses
    * @param end the upper bound of the range of hist company group detailses (not inclusive)
    * @return the range of hist company group detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.HistCompanyGroupDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the hist company group detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistCompanyGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hist company group detailses
    * @param end the upper bound of the range of hist company group detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of hist company group detailses
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.HistCompanyGroupDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the hist company group detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of hist company group detailses.
    *
    * @return the number of hist company group detailses
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
