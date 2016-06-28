package com.stpl.app.service.persistence;

import com.stpl.app.model.CustomViewMaster;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the custom view master service. This utility wraps {@link CustomViewMasterPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CustomViewMasterPersistence
 * @see CustomViewMasterPersistenceImpl
 * @generated
 */
public class CustomViewMasterUtil {
    private static CustomViewMasterPersistence _persistence;

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
    public static void clearCache(CustomViewMaster customViewMaster) {
        getPersistence().clearCache(customViewMaster);
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
    public static List<CustomViewMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<CustomViewMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<CustomViewMaster> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static CustomViewMaster update(CustomViewMaster customViewMaster)
        throws SystemException {
        return getPersistence().update(customViewMaster);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static CustomViewMaster update(CustomViewMaster customViewMaster,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(customViewMaster, serviceContext);
    }

    /**
    * Caches the custom view master in the entity cache if it is enabled.
    *
    * @param customViewMaster the custom view master
    */
    public static void cacheResult(
        com.stpl.app.model.CustomViewMaster customViewMaster) {
        getPersistence().cacheResult(customViewMaster);
    }

    /**
    * Caches the custom view masters in the entity cache if it is enabled.
    *
    * @param customViewMasters the custom view masters
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.CustomViewMaster> customViewMasters) {
        getPersistence().cacheResult(customViewMasters);
    }

    /**
    * Creates a new custom view master with the primary key. Does not add the custom view master to the database.
    *
    * @param customViewMasterSid the primary key for the new custom view master
    * @return the new custom view master
    */
    public static com.stpl.app.model.CustomViewMaster create(
        int customViewMasterSid) {
        return getPersistence().create(customViewMasterSid);
    }

    /**
    * Removes the custom view master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param customViewMasterSid the primary key of the custom view master
    * @return the custom view master that was removed
    * @throws com.stpl.app.NoSuchCustomViewMasterException if a custom view master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CustomViewMaster remove(
        int customViewMasterSid)
        throws com.stpl.app.NoSuchCustomViewMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(customViewMasterSid);
    }

    public static com.stpl.app.model.CustomViewMaster updateImpl(
        com.stpl.app.model.CustomViewMaster customViewMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(customViewMaster);
    }

    /**
    * Returns the custom view master with the primary key or throws a {@link com.stpl.app.NoSuchCustomViewMasterException} if it could not be found.
    *
    * @param customViewMasterSid the primary key of the custom view master
    * @return the custom view master
    * @throws com.stpl.app.NoSuchCustomViewMasterException if a custom view master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CustomViewMaster findByPrimaryKey(
        int customViewMasterSid)
        throws com.stpl.app.NoSuchCustomViewMasterException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(customViewMasterSid);
    }

    /**
    * Returns the custom view master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param customViewMasterSid the primary key of the custom view master
    * @return the custom view master, or <code>null</code> if a custom view master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.CustomViewMaster fetchByPrimaryKey(
        int customViewMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(customViewMasterSid);
    }

    /**
    * Returns all the custom view masters.
    *
    * @return the custom view masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CustomViewMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the custom view masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CustomViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of custom view masters
    * @param end the upper bound of the range of custom view masters (not inclusive)
    * @return the range of custom view masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CustomViewMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the custom view masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CustomViewMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of custom view masters
    * @param end the upper bound of the range of custom view masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of custom view masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.CustomViewMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the custom view masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of custom view masters.
    *
    * @return the number of custom view masters
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static CustomViewMasterPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (CustomViewMasterPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    CustomViewMasterPersistence.class.getName());

            ReferenceRegistry.registerReference(CustomViewMasterUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(CustomViewMasterPersistence persistence) {
    }
}
