package com.stpl.app.service.persistence;

import com.stpl.app.model.NationalAssumptions;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the national assumptions service. This utility wraps {@link NationalAssumptionsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NationalAssumptionsPersistence
 * @see NationalAssumptionsPersistenceImpl
 * @generated
 */
public class NationalAssumptionsUtil {
    private static NationalAssumptionsPersistence _persistence;

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
    public static void clearCache(NationalAssumptions nationalAssumptions) {
        getPersistence().clearCache(nationalAssumptions);
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
    public static List<NationalAssumptions> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<NationalAssumptions> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<NationalAssumptions> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static NationalAssumptions update(
        NationalAssumptions nationalAssumptions) throws SystemException {
        return getPersistence().update(nationalAssumptions);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static NationalAssumptions update(
        NationalAssumptions nationalAssumptions, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(nationalAssumptions, serviceContext);
    }

    /**
    * Caches the national assumptions in the entity cache if it is enabled.
    *
    * @param nationalAssumptions the national assumptions
    */
    public static void cacheResult(
        com.stpl.app.model.NationalAssumptions nationalAssumptions) {
        getPersistence().cacheResult(nationalAssumptions);
    }

    /**
    * Caches the national assumptionses in the entity cache if it is enabled.
    *
    * @param nationalAssumptionses the national assumptionses
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.NationalAssumptions> nationalAssumptionses) {
        getPersistence().cacheResult(nationalAssumptionses);
    }

    /**
    * Creates a new national assumptions with the primary key. Does not add the national assumptions to the database.
    *
    * @param nationalAssumptionsPK the primary key for the new national assumptions
    * @return the new national assumptions
    */
    public static com.stpl.app.model.NationalAssumptions create(
        NationalAssumptionsPK nationalAssumptionsPK) {
        return getPersistence().create(nationalAssumptionsPK);
    }

    /**
    * Removes the national assumptions with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param nationalAssumptionsPK the primary key of the national assumptions
    * @return the national assumptions that was removed
    * @throws com.stpl.app.NoSuchNationalAssumptionsException if a national assumptions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.NationalAssumptions remove(
        NationalAssumptionsPK nationalAssumptionsPK)
        throws com.stpl.app.NoSuchNationalAssumptionsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(nationalAssumptionsPK);
    }

    public static com.stpl.app.model.NationalAssumptions updateImpl(
        com.stpl.app.model.NationalAssumptions nationalAssumptions)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(nationalAssumptions);
    }

    /**
    * Returns the national assumptions with the primary key or throws a {@link com.stpl.app.NoSuchNationalAssumptionsException} if it could not be found.
    *
    * @param nationalAssumptionsPK the primary key of the national assumptions
    * @return the national assumptions
    * @throws com.stpl.app.NoSuchNationalAssumptionsException if a national assumptions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.NationalAssumptions findByPrimaryKey(
        NationalAssumptionsPK nationalAssumptionsPK)
        throws com.stpl.app.NoSuchNationalAssumptionsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(nationalAssumptionsPK);
    }

    /**
    * Returns the national assumptions with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param nationalAssumptionsPK the primary key of the national assumptions
    * @return the national assumptions, or <code>null</code> if a national assumptions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.NationalAssumptions fetchByPrimaryKey(
        NationalAssumptionsPK nationalAssumptionsPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(nationalAssumptionsPK);
    }

    /**
    * Returns all the national assumptionses.
    *
    * @return the national assumptionses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.NationalAssumptions> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the national assumptionses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NationalAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of national assumptionses
    * @param end the upper bound of the range of national assumptionses (not inclusive)
    * @return the range of national assumptionses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.NationalAssumptions> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the national assumptionses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NationalAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of national assumptionses
    * @param end the upper bound of the range of national assumptionses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of national assumptionses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.NationalAssumptions> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the national assumptionses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of national assumptionses.
    *
    * @return the number of national assumptionses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static NationalAssumptionsPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (NationalAssumptionsPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    NationalAssumptionsPersistence.class.getName());

            ReferenceRegistry.registerReference(NationalAssumptionsUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(NationalAssumptionsPersistence persistence) {
    }
}
