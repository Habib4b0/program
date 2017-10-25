package com.stpl.app.service.persistence;

import com.stpl.app.model.NationalAssumptionsActuals;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the national assumptions actuals service. This utility wraps {@link NationalAssumptionsActualsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NationalAssumptionsActualsPersistence
 * @see NationalAssumptionsActualsPersistenceImpl
 * @generated
 */
public class NationalAssumptionsActualsUtil {
    private static NationalAssumptionsActualsPersistence _persistence;

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
        NationalAssumptionsActuals nationalAssumptionsActuals) {
        getPersistence().clearCache(nationalAssumptionsActuals);
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
    public static List<NationalAssumptionsActuals> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<NationalAssumptionsActuals> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<NationalAssumptionsActuals> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static NationalAssumptionsActuals update(
        NationalAssumptionsActuals nationalAssumptionsActuals)
        throws SystemException {
        return getPersistence().update(nationalAssumptionsActuals);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static NationalAssumptionsActuals update(
        NationalAssumptionsActuals nationalAssumptionsActuals,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence()
                   .update(nationalAssumptionsActuals, serviceContext);
    }

    /**
    * Caches the national assumptions actuals in the entity cache if it is enabled.
    *
    * @param nationalAssumptionsActuals the national assumptions actuals
    */
    public static void cacheResult(
        com.stpl.app.model.NationalAssumptionsActuals nationalAssumptionsActuals) {
        getPersistence().cacheResult(nationalAssumptionsActuals);
    }

    /**
    * Caches the national assumptions actualses in the entity cache if it is enabled.
    *
    * @param nationalAssumptionsActualses the national assumptions actualses
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.NationalAssumptionsActuals> nationalAssumptionsActualses) {
        getPersistence().cacheResult(nationalAssumptionsActualses);
    }

    /**
    * Creates a new national assumptions actuals with the primary key. Does not add the national assumptions actuals to the database.
    *
    * @param nationalAssumptionsActualsPK the primary key for the new national assumptions actuals
    * @return the new national assumptions actuals
    */
    public static com.stpl.app.model.NationalAssumptionsActuals create(
        NationalAssumptionsActualsPK nationalAssumptionsActualsPK) {
        return getPersistence().create(nationalAssumptionsActualsPK);
    }

    /**
    * Removes the national assumptions actuals with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param nationalAssumptionsActualsPK the primary key of the national assumptions actuals
    * @return the national assumptions actuals that was removed
    * @throws com.stpl.app.NoSuchNationalAssumptionsActualsException if a national assumptions actuals with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.NationalAssumptionsActuals remove(
        NationalAssumptionsActualsPK nationalAssumptionsActualsPK)
        throws com.stpl.app.NoSuchNationalAssumptionsActualsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(nationalAssumptionsActualsPK);
    }

    public static com.stpl.app.model.NationalAssumptionsActuals updateImpl(
        com.stpl.app.model.NationalAssumptionsActuals nationalAssumptionsActuals)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(nationalAssumptionsActuals);
    }

    /**
    * Returns the national assumptions actuals with the primary key or throws a {@link com.stpl.app.NoSuchNationalAssumptionsActualsException} if it could not be found.
    *
    * @param nationalAssumptionsActualsPK the primary key of the national assumptions actuals
    * @return the national assumptions actuals
    * @throws com.stpl.app.NoSuchNationalAssumptionsActualsException if a national assumptions actuals with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.NationalAssumptionsActuals findByPrimaryKey(
        NationalAssumptionsActualsPK nationalAssumptionsActualsPK)
        throws com.stpl.app.NoSuchNationalAssumptionsActualsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(nationalAssumptionsActualsPK);
    }

    /**
    * Returns the national assumptions actuals with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param nationalAssumptionsActualsPK the primary key of the national assumptions actuals
    * @return the national assumptions actuals, or <code>null</code> if a national assumptions actuals with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.NationalAssumptionsActuals fetchByPrimaryKey(
        NationalAssumptionsActualsPK nationalAssumptionsActualsPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(nationalAssumptionsActualsPK);
    }

    /**
    * Returns all the national assumptions actualses.
    *
    * @return the national assumptions actualses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.NationalAssumptionsActuals> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the national assumptions actualses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NationalAssumptionsActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of national assumptions actualses
    * @param end the upper bound of the range of national assumptions actualses (not inclusive)
    * @return the range of national assumptions actualses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.NationalAssumptionsActuals> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the national assumptions actualses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NationalAssumptionsActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of national assumptions actualses
    * @param end the upper bound of the range of national assumptions actualses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of national assumptions actualses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.NationalAssumptionsActuals> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the national assumptions actualses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of national assumptions actualses.
    *
    * @return the number of national assumptions actualses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static NationalAssumptionsActualsPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (NationalAssumptionsActualsPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    NationalAssumptionsActualsPersistence.class.getName());

            ReferenceRegistry.registerReference(NationalAssumptionsActualsUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(
        NationalAssumptionsActualsPersistence persistence) {
    }
}
