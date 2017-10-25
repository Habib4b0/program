package com.stpl.app.service.persistence;

import com.stpl.app.model.CcpMap;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the ccp map service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CcpMapPersistenceImpl
 * @see CcpMapUtil
 * @generated
 */
public interface CcpMapPersistence extends BasePersistence<CcpMap> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link CcpMapUtil} to access the ccp map persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the ccp map in the entity cache if it is enabled.
    *
    * @param ccpMap the ccp map
    */
    public void cacheResult(com.stpl.app.model.CcpMap ccpMap);

    /**
    * Caches the ccp maps in the entity cache if it is enabled.
    *
    * @param ccpMaps the ccp maps
    */
    public void cacheResult(java.util.List<com.stpl.app.model.CcpMap> ccpMaps);

    /**
    * Creates a new ccp map with the primary key. Does not add the ccp map to the database.
    *
    * @param ccpMapSid the primary key for the new ccp map
    * @return the new ccp map
    */
    public com.stpl.app.model.CcpMap create(int ccpMapSid);

    /**
    * Removes the ccp map with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ccpMapSid the primary key of the ccp map
    * @return the ccp map that was removed
    * @throws com.stpl.app.NoSuchCcpMapException if a ccp map with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CcpMap remove(int ccpMapSid)
        throws com.stpl.app.NoSuchCcpMapException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.CcpMap updateImpl(
        com.stpl.app.model.CcpMap ccpMap)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ccp map with the primary key or throws a {@link com.stpl.app.NoSuchCcpMapException} if it could not be found.
    *
    * @param ccpMapSid the primary key of the ccp map
    * @return the ccp map
    * @throws com.stpl.app.NoSuchCcpMapException if a ccp map with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CcpMap findByPrimaryKey(int ccpMapSid)
        throws com.stpl.app.NoSuchCcpMapException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the ccp map with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ccpMapSid the primary key of the ccp map
    * @return the ccp map, or <code>null</code> if a ccp map with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CcpMap fetchByPrimaryKey(int ccpMapSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the ccp maps.
    *
    * @return the ccp maps
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CcpMap> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the ccp maps.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CcpMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ccp maps
    * @param end the upper bound of the range of ccp maps (not inclusive)
    * @return the range of ccp maps
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CcpMap> findAll(int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the ccp maps.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CcpMapModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ccp maps
    * @param end the upper bound of the range of ccp maps (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ccp maps
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CcpMap> findAll(int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the ccp maps from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of ccp maps.
    *
    * @return the number of ccp maps
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
