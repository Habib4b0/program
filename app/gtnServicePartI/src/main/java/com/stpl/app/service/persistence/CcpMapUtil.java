package com.stpl.app.service.persistence;

import com.stpl.app.model.CcpMap;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the ccp map service. This utility wraps {@link CcpMapPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CcpMapPersistence
 * @see CcpMapPersistenceImpl
 * @generated
 */
public class CcpMapUtil {
    private static CcpMapPersistence _persistence;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
     */

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#clearCache()
     */
    public static void clearCache() {
        getPersistence().clearCache();
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#clearCache(com.stpl.portal.model.BaseModel)
     */
    public static void clearCache(CcpMap ccpMap) {
        getPersistence().clearCache(ccpMap);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#countWithDynamicQuery(DynamicQuery)
     */
    public static long countWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().countWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery)
     */
    public static List<CcpMap> findWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<CcpMap> findWithDynamicQuery(DynamicQuery dynamicQuery,
        int start, int end) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<CcpMap> findWithDynamicQuery(DynamicQuery dynamicQuery,
        int start, int end, OrderByComparator orderByComparator)
        throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static CcpMap update(CcpMap ccpMap) throws SystemException {
        return getPersistence().update(ccpMap);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static CcpMap update(CcpMap ccpMap, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(ccpMap, serviceContext);
    }

    /**
    * Caches the ccp map in the entity cache if it is enabled.
    *
    * @param ccpMap the ccp map
    */
    public static void cacheResult(com.stpl.app.model.CcpMap ccpMap) {
        getPersistence().cacheResult(ccpMap);
    }

    /**
    * Caches the ccp maps in the entity cache if it is enabled.
    *
    * @param ccpMaps the ccp maps
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.CcpMap> ccpMaps) {
        getPersistence().cacheResult(ccpMaps);
    }

    /**
    * Creates a new ccp map with the primary key. Does not add the ccp map to the database.
    *
    * @param ccpMapSid the primary key for the new ccp map
    * @return the new ccp map
    */
    public static com.stpl.app.model.CcpMap create(int ccpMapSid) {
        return getPersistence().create(ccpMapSid);
    }

    /**
    * Removes the ccp map with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ccpMapSid the primary key of the ccp map
    * @return the ccp map that was removed
    * @throws com.stpl.app.NoSuchCcpMapException if a ccp map with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CcpMap remove(int ccpMapSid)
        throws com.stpl.app.NoSuchCcpMapException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(ccpMapSid);
    }

    public static com.stpl.app.model.CcpMap updateImpl(
        com.stpl.app.model.CcpMap ccpMap)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(ccpMap);
    }

    /**
    * Returns the ccp map with the primary key or throws a {@link com.stpl.app.NoSuchCcpMapException} if it could not be found.
    *
    * @param ccpMapSid the primary key of the ccp map
    * @return the ccp map
    * @throws com.stpl.app.NoSuchCcpMapException if a ccp map with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CcpMap findByPrimaryKey(int ccpMapSid)
        throws com.stpl.app.NoSuchCcpMapException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(ccpMapSid);
    }

    /**
    * Returns the ccp map with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ccpMapSid the primary key of the ccp map
    * @return the ccp map, or <code>null</code> if a ccp map with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CcpMap fetchByPrimaryKey(int ccpMapSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(ccpMapSid);
    }

    /**
    * Returns all the ccp maps.
    *
    * @return the ccp maps
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CcpMap> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

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
    public static java.util.List<com.stpl.app.model.CcpMap> findAll(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

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
    public static java.util.List<com.stpl.app.model.CcpMap> findAll(int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the ccp maps from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of ccp maps.
    *
    * @return the number of ccp maps
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static CcpMapPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (CcpMapPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    CcpMapPersistence.class.getName());

            ReferenceRegistry.registerReference(CcpMapUtil.class, "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(CcpMapPersistence persistence) {
    }
}
