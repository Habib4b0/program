package com.stpl.app.service.persistence;

import com.stpl.app.model.StNewNdc;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the st new ndc service. This utility wraps {@link StNewNdcPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StNewNdcPersistence
 * @see StNewNdcPersistenceImpl
 * @generated
 */
public class StNewNdcUtil {
    private static StNewNdcPersistence _persistence;

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
    public static void clearCache(StNewNdc stNewNdc) {
        getPersistence().clearCache(stNewNdc);
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
    public static List<StNewNdc> findWithDynamicQuery(DynamicQuery dynamicQuery)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<StNewNdc> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<StNewNdc> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static StNewNdc update(StNewNdc stNewNdc) throws SystemException {
        return getPersistence().update(stNewNdc);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static StNewNdc update(StNewNdc stNewNdc,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(stNewNdc, serviceContext);
    }

    /**
    * Caches the st new ndc in the entity cache if it is enabled.
    *
    * @param stNewNdc the st new ndc
    */
    public static void cacheResult(com.stpl.app.model.StNewNdc stNewNdc) {
        getPersistence().cacheResult(stNewNdc);
    }

    /**
    * Caches the st new ndcs in the entity cache if it is enabled.
    *
    * @param stNewNdcs the st new ndcs
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.StNewNdc> stNewNdcs) {
        getPersistence().cacheResult(stNewNdcs);
    }

    /**
    * Creates a new st new ndc with the primary key. Does not add the st new ndc to the database.
    *
    * @param stNewNdcPK the primary key for the new st new ndc
    * @return the new st new ndc
    */
    public static com.stpl.app.model.StNewNdc create(StNewNdcPK stNewNdcPK) {
        return getPersistence().create(stNewNdcPK);
    }

    /**
    * Removes the st new ndc with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stNewNdcPK the primary key of the st new ndc
    * @return the st new ndc that was removed
    * @throws com.stpl.app.NoSuchStNewNdcException if a st new ndc with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StNewNdc remove(StNewNdcPK stNewNdcPK)
        throws com.stpl.app.NoSuchStNewNdcException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(stNewNdcPK);
    }

    public static com.stpl.app.model.StNewNdc updateImpl(
        com.stpl.app.model.StNewNdc stNewNdc)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(stNewNdc);
    }

    /**
    * Returns the st new ndc with the primary key or throws a {@link com.stpl.app.NoSuchStNewNdcException} if it could not be found.
    *
    * @param stNewNdcPK the primary key of the st new ndc
    * @return the st new ndc
    * @throws com.stpl.app.NoSuchStNewNdcException if a st new ndc with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StNewNdc findByPrimaryKey(
        StNewNdcPK stNewNdcPK)
        throws com.stpl.app.NoSuchStNewNdcException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(stNewNdcPK);
    }

    /**
    * Returns the st new ndc with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param stNewNdcPK the primary key of the st new ndc
    * @return the st new ndc, or <code>null</code> if a st new ndc with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StNewNdc fetchByPrimaryKey(
        StNewNdcPK stNewNdcPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(stNewNdcPK);
    }

    /**
    * Returns all the st new ndcs.
    *
    * @return the st new ndcs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StNewNdc> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the st new ndcs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st new ndcs
    * @param end the upper bound of the range of st new ndcs (not inclusive)
    * @return the range of st new ndcs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StNewNdc> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the st new ndcs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st new ndcs
    * @param end the upper bound of the range of st new ndcs (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of st new ndcs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StNewNdc> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the st new ndcs from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of st new ndcs.
    *
    * @return the number of st new ndcs
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static StNewNdcPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (StNewNdcPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    StNewNdcPersistence.class.getName());

            ReferenceRegistry.registerReference(StNewNdcUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(StNewNdcPersistence persistence) {
    }
}
