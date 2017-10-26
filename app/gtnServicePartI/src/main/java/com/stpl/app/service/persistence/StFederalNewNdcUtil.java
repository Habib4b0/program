package com.stpl.app.service.persistence;

import com.stpl.app.model.StFederalNewNdc;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the st federal new ndc service. This utility wraps {@link StFederalNewNdcPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StFederalNewNdcPersistence
 * @see StFederalNewNdcPersistenceImpl
 * @generated
 */
public class StFederalNewNdcUtil {
    private static StFederalNewNdcPersistence _persistence;

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
    public static void clearCache(StFederalNewNdc stFederalNewNdc) {
        getPersistence().clearCache(stFederalNewNdc);
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
    public static List<StFederalNewNdc> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<StFederalNewNdc> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<StFederalNewNdc> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static StFederalNewNdc update(StFederalNewNdc stFederalNewNdc)
        throws SystemException {
        return getPersistence().update(stFederalNewNdc);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static StFederalNewNdc update(StFederalNewNdc stFederalNewNdc,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(stFederalNewNdc, serviceContext);
    }

    /**
    * Caches the st federal new ndc in the entity cache if it is enabled.
    *
    * @param stFederalNewNdc the st federal new ndc
    */
    public static void cacheResult(
        com.stpl.app.model.StFederalNewNdc stFederalNewNdc) {
        getPersistence().cacheResult(stFederalNewNdc);
    }

    /**
    * Caches the st federal new ndcs in the entity cache if it is enabled.
    *
    * @param stFederalNewNdcs the st federal new ndcs
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.StFederalNewNdc> stFederalNewNdcs) {
        getPersistence().cacheResult(stFederalNewNdcs);
    }

    /**
    * Creates a new st federal new ndc with the primary key. Does not add the st federal new ndc to the database.
    *
    * @param stFederalNewNdcPK the primary key for the new st federal new ndc
    * @return the new st federal new ndc
    */
    public static com.stpl.app.model.StFederalNewNdc create(
        StFederalNewNdcPK stFederalNewNdcPK) {
        return getPersistence().create(stFederalNewNdcPK);
    }

    /**
    * Removes the st federal new ndc with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stFederalNewNdcPK the primary key of the st federal new ndc
    * @return the st federal new ndc that was removed
    * @throws com.stpl.app.NoSuchStFederalNewNdcException if a st federal new ndc with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StFederalNewNdc remove(
        StFederalNewNdcPK stFederalNewNdcPK)
        throws com.stpl.app.NoSuchStFederalNewNdcException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(stFederalNewNdcPK);
    }

    public static com.stpl.app.model.StFederalNewNdc updateImpl(
        com.stpl.app.model.StFederalNewNdc stFederalNewNdc)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(stFederalNewNdc);
    }

    /**
    * Returns the st federal new ndc with the primary key or throws a {@link com.stpl.app.NoSuchStFederalNewNdcException} if it could not be found.
    *
    * @param stFederalNewNdcPK the primary key of the st federal new ndc
    * @return the st federal new ndc
    * @throws com.stpl.app.NoSuchStFederalNewNdcException if a st federal new ndc with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StFederalNewNdc findByPrimaryKey(
        StFederalNewNdcPK stFederalNewNdcPK)
        throws com.stpl.app.NoSuchStFederalNewNdcException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(stFederalNewNdcPK);
    }

    /**
    * Returns the st federal new ndc with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param stFederalNewNdcPK the primary key of the st federal new ndc
    * @return the st federal new ndc, or <code>null</code> if a st federal new ndc with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StFederalNewNdc fetchByPrimaryKey(
        StFederalNewNdcPK stFederalNewNdcPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(stFederalNewNdcPK);
    }

    /**
    * Returns all the st federal new ndcs.
    *
    * @return the st federal new ndcs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StFederalNewNdc> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the st federal new ndcs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StFederalNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st federal new ndcs
    * @param end the upper bound of the range of st federal new ndcs (not inclusive)
    * @return the range of st federal new ndcs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StFederalNewNdc> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the st federal new ndcs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StFederalNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st federal new ndcs
    * @param end the upper bound of the range of st federal new ndcs (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of st federal new ndcs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StFederalNewNdc> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the st federal new ndcs from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of st federal new ndcs.
    *
    * @return the number of st federal new ndcs
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static StFederalNewNdcPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (StFederalNewNdcPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    StFederalNewNdcPersistence.class.getName());

            ReferenceRegistry.registerReference(StFederalNewNdcUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(StFederalNewNdcPersistence persistence) {
    }
}
