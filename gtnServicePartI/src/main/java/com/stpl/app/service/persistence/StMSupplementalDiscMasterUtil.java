package com.stpl.app.service.persistence;

import com.stpl.app.model.StMSupplementalDiscMaster;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the st m supplemental disc master service. This utility wraps {@link StMSupplementalDiscMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StMSupplementalDiscMasterPersistence
 * @see StMSupplementalDiscMasterPersistenceImpl
 * @generated
 */
public class StMSupplementalDiscMasterUtil {
    private static StMSupplementalDiscMasterPersistence _persistence;

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
    public static void clearCache(
        StMSupplementalDiscMaster stMSupplementalDiscMaster) {
        getPersistence().clearCache(stMSupplementalDiscMaster);
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
    public static List<StMSupplementalDiscMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<StMSupplementalDiscMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<StMSupplementalDiscMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static StMSupplementalDiscMaster update(
        StMSupplementalDiscMaster stMSupplementalDiscMaster)
        throws SystemException {
        return getPersistence().update(stMSupplementalDiscMaster);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static StMSupplementalDiscMaster update(
        StMSupplementalDiscMaster stMSupplementalDiscMaster,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(stMSupplementalDiscMaster, serviceContext);
    }

    /**
    * Caches the st m supplemental disc master in the entity cache if it is enabled.
    *
    * @param stMSupplementalDiscMaster the st m supplemental disc master
    */
    public static void cacheResult(
        com.stpl.app.model.StMSupplementalDiscMaster stMSupplementalDiscMaster) {
        getPersistence().cacheResult(stMSupplementalDiscMaster);
    }

    /**
    * Caches the st m supplemental disc masters in the entity cache if it is enabled.
    *
    * @param stMSupplementalDiscMasters the st m supplemental disc masters
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.StMSupplementalDiscMaster> stMSupplementalDiscMasters) {
        getPersistence().cacheResult(stMSupplementalDiscMasters);
    }

    /**
    * Creates a new st m supplemental disc master with the primary key. Does not add the st m supplemental disc master to the database.
    *
    * @param stMSupplementalDiscMasterPK the primary key for the new st m supplemental disc master
    * @return the new st m supplemental disc master
    */
    public static com.stpl.app.model.StMSupplementalDiscMaster create(
        StMSupplementalDiscMasterPK stMSupplementalDiscMasterPK) {
        return getPersistence().create(stMSupplementalDiscMasterPK);
    }

    /**
    * Removes the st m supplemental disc master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stMSupplementalDiscMasterPK the primary key of the st m supplemental disc master
    * @return the st m supplemental disc master that was removed
    * @throws com.stpl.app.NoSuchStMSupplementalDiscMasterException if a st m supplemental disc master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StMSupplementalDiscMaster remove(
        StMSupplementalDiscMasterPK stMSupplementalDiscMasterPK)
        throws com.stpl.app.NoSuchStMSupplementalDiscMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(stMSupplementalDiscMasterPK);
    }

    public static com.stpl.app.model.StMSupplementalDiscMaster updateImpl(
        com.stpl.app.model.StMSupplementalDiscMaster stMSupplementalDiscMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(stMSupplementalDiscMaster);
    }

    /**
    * Returns the st m supplemental disc master with the primary key or throws a {@link com.stpl.app.NoSuchStMSupplementalDiscMasterException} if it could not be found.
    *
    * @param stMSupplementalDiscMasterPK the primary key of the st m supplemental disc master
    * @return the st m supplemental disc master
    * @throws com.stpl.app.NoSuchStMSupplementalDiscMasterException if a st m supplemental disc master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StMSupplementalDiscMaster findByPrimaryKey(
        StMSupplementalDiscMasterPK stMSupplementalDiscMasterPK)
        throws com.stpl.app.NoSuchStMSupplementalDiscMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(stMSupplementalDiscMasterPK);
    }

    /**
    * Returns the st m supplemental disc master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param stMSupplementalDiscMasterPK the primary key of the st m supplemental disc master
    * @return the st m supplemental disc master, or <code>null</code> if a st m supplemental disc master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StMSupplementalDiscMaster fetchByPrimaryKey(
        StMSupplementalDiscMasterPK stMSupplementalDiscMasterPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(stMSupplementalDiscMasterPK);
    }

    /**
    * Returns all the st m supplemental disc masters.
    *
    * @return the st m supplemental disc masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StMSupplementalDiscMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the st m supplemental disc masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StMSupplementalDiscMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st m supplemental disc masters
    * @param end the upper bound of the range of st m supplemental disc masters (not inclusive)
    * @return the range of st m supplemental disc masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StMSupplementalDiscMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the st m supplemental disc masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StMSupplementalDiscMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st m supplemental disc masters
    * @param end the upper bound of the range of st m supplemental disc masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of st m supplemental disc masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StMSupplementalDiscMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the st m supplemental disc masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of st m supplemental disc masters.
    *
    * @return the number of st m supplemental disc masters
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static StMSupplementalDiscMasterPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (StMSupplementalDiscMasterPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    StMSupplementalDiscMasterPersistence.class.getName());

            ReferenceRegistry.registerReference(StMSupplementalDiscMasterUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(StMSupplementalDiscMasterPersistence persistence) {
    }
}
