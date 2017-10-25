package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.VwIvldAdjDemandForeActual;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the vw ivld adj demand fore actual service. This utility wraps {@link VwIvldAdjDemandForeActualPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwIvldAdjDemandForeActualPersistence
 * @see VwIvldAdjDemandForeActualPersistenceImpl
 * @generated
 */
public class VwIvldAdjDemandForeActualUtil {
    private static VwIvldAdjDemandForeActualPersistence _persistence;

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
        VwIvldAdjDemandForeActual vwIvldAdjDemandForeActual) {
        getPersistence().clearCache(vwIvldAdjDemandForeActual);
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
    public static List<VwIvldAdjDemandForeActual> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<VwIvldAdjDemandForeActual> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<VwIvldAdjDemandForeActual> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static VwIvldAdjDemandForeActual update(
        VwIvldAdjDemandForeActual vwIvldAdjDemandForeActual)
        throws SystemException {
        return getPersistence().update(vwIvldAdjDemandForeActual);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static VwIvldAdjDemandForeActual update(
        VwIvldAdjDemandForeActual vwIvldAdjDemandForeActual,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(vwIvldAdjDemandForeActual, serviceContext);
    }

    /**
    * Caches the vw ivld adj demand fore actual in the entity cache if it is enabled.
    *
    * @param vwIvldAdjDemandForeActual the vw ivld adj demand fore actual
    */
    public static void cacheResult(
        com.stpl.app.parttwo.model.VwIvldAdjDemandForeActual vwIvldAdjDemandForeActual) {
        getPersistence().cacheResult(vwIvldAdjDemandForeActual);
    }

    /**
    * Caches the vw ivld adj demand fore actuals in the entity cache if it is enabled.
    *
    * @param vwIvldAdjDemandForeActuals the vw ivld adj demand fore actuals
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.VwIvldAdjDemandForeActual> vwIvldAdjDemandForeActuals) {
        getPersistence().cacheResult(vwIvldAdjDemandForeActuals);
    }

    /**
    * Creates a new vw ivld adj demand fore actual with the primary key. Does not add the vw ivld adj demand fore actual to the database.
    *
    * @param ivldAdjustedDemandForecastSid the primary key for the new vw ivld adj demand fore actual
    * @return the new vw ivld adj demand fore actual
    */
    public static com.stpl.app.parttwo.model.VwIvldAdjDemandForeActual create(
        int ivldAdjustedDemandForecastSid) {
        return getPersistence().create(ivldAdjustedDemandForecastSid);
    }

    /**
    * Removes the vw ivld adj demand fore actual with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param ivldAdjustedDemandForecastSid the primary key of the vw ivld adj demand fore actual
    * @return the vw ivld adj demand fore actual that was removed
    * @throws com.stpl.app.parttwo.NoSuchVwIvldAdjDemandForeActualException if a vw ivld adj demand fore actual with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.VwIvldAdjDemandForeActual remove(
        int ivldAdjustedDemandForecastSid)
        throws com.stpl.app.parttwo.NoSuchVwIvldAdjDemandForeActualException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(ivldAdjustedDemandForecastSid);
    }

    public static com.stpl.app.parttwo.model.VwIvldAdjDemandForeActual updateImpl(
        com.stpl.app.parttwo.model.VwIvldAdjDemandForeActual vwIvldAdjDemandForeActual)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(vwIvldAdjDemandForeActual);
    }

    /**
    * Returns the vw ivld adj demand fore actual with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchVwIvldAdjDemandForeActualException} if it could not be found.
    *
    * @param ivldAdjustedDemandForecastSid the primary key of the vw ivld adj demand fore actual
    * @return the vw ivld adj demand fore actual
    * @throws com.stpl.app.parttwo.NoSuchVwIvldAdjDemandForeActualException if a vw ivld adj demand fore actual with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.VwIvldAdjDemandForeActual findByPrimaryKey(
        int ivldAdjustedDemandForecastSid)
        throws com.stpl.app.parttwo.NoSuchVwIvldAdjDemandForeActualException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(ivldAdjustedDemandForecastSid);
    }

    /**
    * Returns the vw ivld adj demand fore actual with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param ivldAdjustedDemandForecastSid the primary key of the vw ivld adj demand fore actual
    * @return the vw ivld adj demand fore actual, or <code>null</code> if a vw ivld adj demand fore actual with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.VwIvldAdjDemandForeActual fetchByPrimaryKey(
        int ivldAdjustedDemandForecastSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(ivldAdjustedDemandForecastSid);
    }

    /**
    * Returns all the vw ivld adj demand fore actuals.
    *
    * @return the vw ivld adj demand fore actuals
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.VwIvldAdjDemandForeActual> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the vw ivld adj demand fore actuals.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwIvldAdjDemandForeActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw ivld adj demand fore actuals
    * @param end the upper bound of the range of vw ivld adj demand fore actuals (not inclusive)
    * @return the range of vw ivld adj demand fore actuals
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.VwIvldAdjDemandForeActual> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the vw ivld adj demand fore actuals.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.VwIvldAdjDemandForeActualModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of vw ivld adj demand fore actuals
    * @param end the upper bound of the range of vw ivld adj demand fore actuals (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of vw ivld adj demand fore actuals
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.VwIvldAdjDemandForeActual> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the vw ivld adj demand fore actuals from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of vw ivld adj demand fore actuals.
    *
    * @return the number of vw ivld adj demand fore actuals
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static VwIvldAdjDemandForeActualPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (VwIvldAdjDemandForeActualPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.parttwo.service.ClpSerializer.getServletContextName(),
                    VwIvldAdjDemandForeActualPersistence.class.getName());

            ReferenceRegistry.registerReference(VwIvldAdjDemandForeActualUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(VwIvldAdjDemandForeActualPersistence persistence) {
    }
}
