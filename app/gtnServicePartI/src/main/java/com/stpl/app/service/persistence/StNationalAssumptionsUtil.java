package com.stpl.app.service.persistence;

import com.stpl.app.model.StNationalAssumptions;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the st national assumptions service. This utility wraps {@link StNationalAssumptionsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StNationalAssumptionsPersistence
 * @see StNationalAssumptionsPersistenceImpl
 * @generated
 */
public class StNationalAssumptionsUtil {
    private static StNationalAssumptionsPersistence _persistence;

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
    public static void clearCache(StNationalAssumptions stNationalAssumptions) {
        getPersistence().clearCache(stNationalAssumptions);
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
    public static List<StNationalAssumptions> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<StNationalAssumptions> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<StNationalAssumptions> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static StNationalAssumptions update(
        StNationalAssumptions stNationalAssumptions) throws SystemException {
        return getPersistence().update(stNationalAssumptions);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static StNationalAssumptions update(
        StNationalAssumptions stNationalAssumptions,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(stNationalAssumptions, serviceContext);
    }

    /**
    * Caches the st national assumptions in the entity cache if it is enabled.
    *
    * @param stNationalAssumptions the st national assumptions
    */
    public static void cacheResult(
        com.stpl.app.model.StNationalAssumptions stNationalAssumptions) {
        getPersistence().cacheResult(stNationalAssumptions);
    }

    /**
    * Caches the st national assumptionses in the entity cache if it is enabled.
    *
    * @param stNationalAssumptionses the st national assumptionses
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.StNationalAssumptions> stNationalAssumptionses) {
        getPersistence().cacheResult(stNationalAssumptionses);
    }

    /**
    * Creates a new st national assumptions with the primary key. Does not add the st national assumptions to the database.
    *
    * @param stNationalAssumptionsPK the primary key for the new st national assumptions
    * @return the new st national assumptions
    */
    public static com.stpl.app.model.StNationalAssumptions create(
        StNationalAssumptionsPK stNationalAssumptionsPK) {
        return getPersistence().create(stNationalAssumptionsPK);
    }

    /**
    * Removes the st national assumptions with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stNationalAssumptionsPK the primary key of the st national assumptions
    * @return the st national assumptions that was removed
    * @throws com.stpl.app.NoSuchStNationalAssumptionsException if a st national assumptions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StNationalAssumptions remove(
        StNationalAssumptionsPK stNationalAssumptionsPK)
        throws com.stpl.app.NoSuchStNationalAssumptionsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(stNationalAssumptionsPK);
    }

    public static com.stpl.app.model.StNationalAssumptions updateImpl(
        com.stpl.app.model.StNationalAssumptions stNationalAssumptions)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(stNationalAssumptions);
    }

    /**
    * Returns the st national assumptions with the primary key or throws a {@link com.stpl.app.NoSuchStNationalAssumptionsException} if it could not be found.
    *
    * @param stNationalAssumptionsPK the primary key of the st national assumptions
    * @return the st national assumptions
    * @throws com.stpl.app.NoSuchStNationalAssumptionsException if a st national assumptions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StNationalAssumptions findByPrimaryKey(
        StNationalAssumptionsPK stNationalAssumptionsPK)
        throws com.stpl.app.NoSuchStNationalAssumptionsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(stNationalAssumptionsPK);
    }

    /**
    * Returns the st national assumptions with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param stNationalAssumptionsPK the primary key of the st national assumptions
    * @return the st national assumptions, or <code>null</code> if a st national assumptions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StNationalAssumptions fetchByPrimaryKey(
        StNationalAssumptionsPK stNationalAssumptionsPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(stNationalAssumptionsPK);
    }

    /**
    * Returns all the st national assumptionses.
    *
    * @return the st national assumptionses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StNationalAssumptions> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the st national assumptionses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNationalAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st national assumptionses
    * @param end the upper bound of the range of st national assumptionses (not inclusive)
    * @return the range of st national assumptionses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StNationalAssumptions> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the st national assumptionses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNationalAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st national assumptionses
    * @param end the upper bound of the range of st national assumptionses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of st national assumptionses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StNationalAssumptions> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the st national assumptionses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of st national assumptionses.
    *
    * @return the number of st national assumptionses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static StNationalAssumptionsPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (StNationalAssumptionsPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    StNationalAssumptionsPersistence.class.getName());

            ReferenceRegistry.registerReference(StNationalAssumptionsUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(StNationalAssumptionsPersistence persistence) {
    }
}
