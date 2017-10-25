package com.stpl.app.service.persistence;

import com.stpl.app.model.StMAssumptions;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the st m assumptions service. This utility wraps {@link StMAssumptionsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StMAssumptionsPersistence
 * @see StMAssumptionsPersistenceImpl
 * @generated
 */
public class StMAssumptionsUtil {
    private static StMAssumptionsPersistence _persistence;

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
    public static void clearCache(StMAssumptions stMAssumptions) {
        getPersistence().clearCache(stMAssumptions);
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
    public static List<StMAssumptions> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<StMAssumptions> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<StMAssumptions> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static StMAssumptions update(StMAssumptions stMAssumptions)
        throws SystemException {
        return getPersistence().update(stMAssumptions);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static StMAssumptions update(StMAssumptions stMAssumptions,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(stMAssumptions, serviceContext);
    }

    /**
    * Caches the st m assumptions in the entity cache if it is enabled.
    *
    * @param stMAssumptions the st m assumptions
    */
    public static void cacheResult(
        com.stpl.app.model.StMAssumptions stMAssumptions) {
        getPersistence().cacheResult(stMAssumptions);
    }

    /**
    * Caches the st m assumptionses in the entity cache if it is enabled.
    *
    * @param stMAssumptionses the st m assumptionses
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.StMAssumptions> stMAssumptionses) {
        getPersistence().cacheResult(stMAssumptionses);
    }

    /**
    * Creates a new st m assumptions with the primary key. Does not add the st m assumptions to the database.
    *
    * @param stMAssumptionsPK the primary key for the new st m assumptions
    * @return the new st m assumptions
    */
    public static com.stpl.app.model.StMAssumptions create(
        StMAssumptionsPK stMAssumptionsPK) {
        return getPersistence().create(stMAssumptionsPK);
    }

    /**
    * Removes the st m assumptions with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stMAssumptionsPK the primary key of the st m assumptions
    * @return the st m assumptions that was removed
    * @throws com.stpl.app.NoSuchStMAssumptionsException if a st m assumptions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StMAssumptions remove(
        StMAssumptionsPK stMAssumptionsPK)
        throws com.stpl.app.NoSuchStMAssumptionsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(stMAssumptionsPK);
    }

    public static com.stpl.app.model.StMAssumptions updateImpl(
        com.stpl.app.model.StMAssumptions stMAssumptions)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(stMAssumptions);
    }

    /**
    * Returns the st m assumptions with the primary key or throws a {@link com.stpl.app.NoSuchStMAssumptionsException} if it could not be found.
    *
    * @param stMAssumptionsPK the primary key of the st m assumptions
    * @return the st m assumptions
    * @throws com.stpl.app.NoSuchStMAssumptionsException if a st m assumptions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StMAssumptions findByPrimaryKey(
        StMAssumptionsPK stMAssumptionsPK)
        throws com.stpl.app.NoSuchStMAssumptionsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(stMAssumptionsPK);
    }

    /**
    * Returns the st m assumptions with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param stMAssumptionsPK the primary key of the st m assumptions
    * @return the st m assumptions, or <code>null</code> if a st m assumptions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StMAssumptions fetchByPrimaryKey(
        StMAssumptionsPK stMAssumptionsPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(stMAssumptionsPK);
    }

    /**
    * Returns all the st m assumptionses.
    *
    * @return the st m assumptionses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StMAssumptions> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the st m assumptionses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StMAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st m assumptionses
    * @param end the upper bound of the range of st m assumptionses (not inclusive)
    * @return the range of st m assumptionses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StMAssumptions> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the st m assumptionses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StMAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st m assumptionses
    * @param end the upper bound of the range of st m assumptionses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of st m assumptionses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StMAssumptions> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the st m assumptionses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of st m assumptionses.
    *
    * @return the number of st m assumptionses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static StMAssumptionsPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (StMAssumptionsPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    StMAssumptionsPersistence.class.getName());

            ReferenceRegistry.registerReference(StMAssumptionsUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(StMAssumptionsPersistence persistence) {
    }
}
