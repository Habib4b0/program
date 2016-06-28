package com.stpl.app.service.persistence;

import com.stpl.app.model.StNmAssumptions;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the st nm assumptions service. This utility wraps {@link StNmAssumptionsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StNmAssumptionsPersistence
 * @see StNmAssumptionsPersistenceImpl
 * @generated
 */
public class StNmAssumptionsUtil {
    private static StNmAssumptionsPersistence _persistence;

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
    public static void clearCache(StNmAssumptions stNmAssumptions) {
        getPersistence().clearCache(stNmAssumptions);
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
    public static List<StNmAssumptions> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<StNmAssumptions> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<StNmAssumptions> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static StNmAssumptions update(StNmAssumptions stNmAssumptions)
        throws SystemException {
        return getPersistence().update(stNmAssumptions);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static StNmAssumptions update(StNmAssumptions stNmAssumptions,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(stNmAssumptions, serviceContext);
    }

    /**
    * Caches the st nm assumptions in the entity cache if it is enabled.
    *
    * @param stNmAssumptions the st nm assumptions
    */
    public static void cacheResult(
        com.stpl.app.model.StNmAssumptions stNmAssumptions) {
        getPersistence().cacheResult(stNmAssumptions);
    }

    /**
    * Caches the st nm assumptionses in the entity cache if it is enabled.
    *
    * @param stNmAssumptionses the st nm assumptionses
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.StNmAssumptions> stNmAssumptionses) {
        getPersistence().cacheResult(stNmAssumptionses);
    }

    /**
    * Creates a new st nm assumptions with the primary key. Does not add the st nm assumptions to the database.
    *
    * @param stNmAssumptionsPK the primary key for the new st nm assumptions
    * @return the new st nm assumptions
    */
    public static com.stpl.app.model.StNmAssumptions create(
        StNmAssumptionsPK stNmAssumptionsPK) {
        return getPersistence().create(stNmAssumptionsPK);
    }

    /**
    * Removes the st nm assumptions with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stNmAssumptionsPK the primary key of the st nm assumptions
    * @return the st nm assumptions that was removed
    * @throws com.stpl.app.NoSuchStNmAssumptionsException if a st nm assumptions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StNmAssumptions remove(
        StNmAssumptionsPK stNmAssumptionsPK)
        throws com.stpl.app.NoSuchStNmAssumptionsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(stNmAssumptionsPK);
    }

    public static com.stpl.app.model.StNmAssumptions updateImpl(
        com.stpl.app.model.StNmAssumptions stNmAssumptions)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(stNmAssumptions);
    }

    /**
    * Returns the st nm assumptions with the primary key or throws a {@link com.stpl.app.NoSuchStNmAssumptionsException} if it could not be found.
    *
    * @param stNmAssumptionsPK the primary key of the st nm assumptions
    * @return the st nm assumptions
    * @throws com.stpl.app.NoSuchStNmAssumptionsException if a st nm assumptions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StNmAssumptions findByPrimaryKey(
        StNmAssumptionsPK stNmAssumptionsPK)
        throws com.stpl.app.NoSuchStNmAssumptionsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(stNmAssumptionsPK);
    }

    /**
    * Returns the st nm assumptions with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param stNmAssumptionsPK the primary key of the st nm assumptions
    * @return the st nm assumptions, or <code>null</code> if a st nm assumptions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StNmAssumptions fetchByPrimaryKey(
        StNmAssumptionsPK stNmAssumptionsPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(stNmAssumptionsPK);
    }

    /**
    * Returns all the st nm assumptionses.
    *
    * @return the st nm assumptionses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StNmAssumptions> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the st nm assumptionses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st nm assumptionses
    * @param end the upper bound of the range of st nm assumptionses (not inclusive)
    * @return the range of st nm assumptionses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StNmAssumptions> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the st nm assumptionses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNmAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st nm assumptionses
    * @param end the upper bound of the range of st nm assumptionses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of st nm assumptionses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StNmAssumptions> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the st nm assumptionses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of st nm assumptionses.
    *
    * @return the number of st nm assumptionses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static StNmAssumptionsPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (StNmAssumptionsPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    StNmAssumptionsPersistence.class.getName());

            ReferenceRegistry.registerReference(StNmAssumptionsUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(StNmAssumptionsPersistence persistence) {
    }
}
