package com.stpl.app.service.persistence;

import com.stpl.app.model.StMSupplementalDiscActuals;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the st m supplemental disc actuals service. This utility wraps {@link StMSupplementalDiscActualsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StMSupplementalDiscActualsPersistence
 * @see StMSupplementalDiscActualsPersistenceImpl
 * @generated
 */
public class StMSupplementalDiscActualsUtil {
    private static StMSupplementalDiscActualsPersistence _persistence;

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
        StMSupplementalDiscActuals stMSupplementalDiscActuals) {
        getPersistence().clearCache(stMSupplementalDiscActuals);
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
    public static List<StMSupplementalDiscActuals> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<StMSupplementalDiscActuals> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<StMSupplementalDiscActuals> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static StMSupplementalDiscActuals update(
        StMSupplementalDiscActuals stMSupplementalDiscActuals)
        throws SystemException {
        return getPersistence().update(stMSupplementalDiscActuals);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static StMSupplementalDiscActuals update(
        StMSupplementalDiscActuals stMSupplementalDiscActuals,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence()
                   .update(stMSupplementalDiscActuals, serviceContext);
    }

    /**
    * Caches the st m supplemental disc actuals in the entity cache if it is enabled.
    *
    * @param stMSupplementalDiscActuals the st m supplemental disc actuals
    */
    public static void cacheResult(
        com.stpl.app.model.StMSupplementalDiscActuals stMSupplementalDiscActuals) {
        getPersistence().cacheResult(stMSupplementalDiscActuals);
    }

    /**
    * Caches the st m supplemental disc actualses in the entity cache if it is enabled.
    *
    * @param stMSupplementalDiscActualses the st m supplemental disc actualses
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.StMSupplementalDiscActuals> stMSupplementalDiscActualses) {
        getPersistence().cacheResult(stMSupplementalDiscActualses);
    }

    /**
    * Creates a new st m supplemental disc actuals with the primary key. Does not add the st m supplemental disc actuals to the database.
    *
    * @param stMSupplementalDiscActualsPK the primary key for the new st m supplemental disc actuals
    * @return the new st m supplemental disc actuals
    */
    public static com.stpl.app.model.StMSupplementalDiscActuals create(
        StMSupplementalDiscActualsPK stMSupplementalDiscActualsPK) {
        return getPersistence().create(stMSupplementalDiscActualsPK);
    }

    /**
    * Removes the st m supplemental disc actuals with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stMSupplementalDiscActualsPK the primary key of the st m supplemental disc actuals
    * @return the st m supplemental disc actuals that was removed
    * @throws com.stpl.app.NoSuchStMSupplementalDiscActualsException if a st m supplemental disc actuals with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StMSupplementalDiscActuals remove(
        StMSupplementalDiscActualsPK stMSupplementalDiscActualsPK)
        throws com.stpl.app.NoSuchStMSupplementalDiscActualsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(stMSupplementalDiscActualsPK);
    }

    public static com.stpl.app.model.StMSupplementalDiscActuals updateImpl(
        com.stpl.app.model.StMSupplementalDiscActuals stMSupplementalDiscActuals)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(stMSupplementalDiscActuals);
    }

    /**
    * Returns the st m supplemental disc actuals with the primary key or throws a {@link com.stpl.app.NoSuchStMSupplementalDiscActualsException} if it could not be found.
    *
    * @param stMSupplementalDiscActualsPK the primary key of the st m supplemental disc actuals
    * @return the st m supplemental disc actuals
    * @throws com.stpl.app.NoSuchStMSupplementalDiscActualsException if a st m supplemental disc actuals with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StMSupplementalDiscActuals findByPrimaryKey(
        StMSupplementalDiscActualsPK stMSupplementalDiscActualsPK)
        throws com.stpl.app.NoSuchStMSupplementalDiscActualsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(stMSupplementalDiscActualsPK);
    }

    /**
    * Returns the st m supplemental disc actuals with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param stMSupplementalDiscActualsPK the primary key of the st m supplemental disc actuals
    * @return the st m supplemental disc actuals, or <code>null</code> if a st m supplemental disc actuals with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StMSupplementalDiscActuals fetchByPrimaryKey(
        StMSupplementalDiscActualsPK stMSupplementalDiscActualsPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(stMSupplementalDiscActualsPK);
    }

    /**
    * Returns all the st m supplemental disc actualses.
    *
    * @return the st m supplemental disc actualses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StMSupplementalDiscActuals> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the st m supplemental disc actualses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StMSupplementalDiscActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st m supplemental disc actualses
    * @param end the upper bound of the range of st m supplemental disc actualses (not inclusive)
    * @return the range of st m supplemental disc actualses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StMSupplementalDiscActuals> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the st m supplemental disc actualses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StMSupplementalDiscActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st m supplemental disc actualses
    * @param end the upper bound of the range of st m supplemental disc actualses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of st m supplemental disc actualses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StMSupplementalDiscActuals> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the st m supplemental disc actualses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of st m supplemental disc actualses.
    *
    * @return the number of st m supplemental disc actualses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static StMSupplementalDiscActualsPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (StMSupplementalDiscActualsPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    StMSupplementalDiscActualsPersistence.class.getName());

            ReferenceRegistry.registerReference(StMSupplementalDiscActualsUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(
        StMSupplementalDiscActualsPersistence persistence) {
    }
}
