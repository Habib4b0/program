package com.stpl.app.service.persistence;

import com.stpl.app.model.FederalNewNdc;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the federal new ndc service. This utility wraps {@link FederalNewNdcPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see FederalNewNdcPersistence
 * @see FederalNewNdcPersistenceImpl
 * @generated
 */
public class FederalNewNdcUtil {
    private static FederalNewNdcPersistence _persistence;

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
    public static void clearCache(FederalNewNdc federalNewNdc) {
        getPersistence().clearCache(federalNewNdc);
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
    public static List<FederalNewNdc> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<FederalNewNdc> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<FederalNewNdc> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static FederalNewNdc update(FederalNewNdc federalNewNdc)
        throws SystemException {
        return getPersistence().update(federalNewNdc);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static FederalNewNdc update(FederalNewNdc federalNewNdc,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(federalNewNdc, serviceContext);
    }

    /**
    * Caches the federal new ndc in the entity cache if it is enabled.
    *
    * @param federalNewNdc the federal new ndc
    */
    public static void cacheResult(
        com.stpl.app.model.FederalNewNdc federalNewNdc) {
        getPersistence().cacheResult(federalNewNdc);
    }

    /**
    * Caches the federal new ndcs in the entity cache if it is enabled.
    *
    * @param federalNewNdcs the federal new ndcs
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.FederalNewNdc> federalNewNdcs) {
        getPersistence().cacheResult(federalNewNdcs);
    }

    /**
    * Creates a new federal new ndc with the primary key. Does not add the federal new ndc to the database.
    *
    * @param itemMasterSid the primary key for the new federal new ndc
    * @return the new federal new ndc
    */
    public static com.stpl.app.model.FederalNewNdc create(int itemMasterSid) {
        return getPersistence().create(itemMasterSid);
    }

    /**
    * Removes the federal new ndc with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param itemMasterSid the primary key of the federal new ndc
    * @return the federal new ndc that was removed
    * @throws com.stpl.app.NoSuchFederalNewNdcException if a federal new ndc with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.FederalNewNdc remove(int itemMasterSid)
        throws com.stpl.app.NoSuchFederalNewNdcException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(itemMasterSid);
    }

    public static com.stpl.app.model.FederalNewNdc updateImpl(
        com.stpl.app.model.FederalNewNdc federalNewNdc)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(federalNewNdc);
    }

    /**
    * Returns the federal new ndc with the primary key or throws a {@link com.stpl.app.NoSuchFederalNewNdcException} if it could not be found.
    *
    * @param itemMasterSid the primary key of the federal new ndc
    * @return the federal new ndc
    * @throws com.stpl.app.NoSuchFederalNewNdcException if a federal new ndc with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.FederalNewNdc findByPrimaryKey(
        int itemMasterSid)
        throws com.stpl.app.NoSuchFederalNewNdcException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(itemMasterSid);
    }

    /**
    * Returns the federal new ndc with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param itemMasterSid the primary key of the federal new ndc
    * @return the federal new ndc, or <code>null</code> if a federal new ndc with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.FederalNewNdc fetchByPrimaryKey(
        int itemMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(itemMasterSid);
    }

    /**
    * Returns all the federal new ndcs.
    *
    * @return the federal new ndcs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.FederalNewNdc> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the federal new ndcs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.FederalNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of federal new ndcs
    * @param end the upper bound of the range of federal new ndcs (not inclusive)
    * @return the range of federal new ndcs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.FederalNewNdc> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the federal new ndcs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.FederalNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of federal new ndcs
    * @param end the upper bound of the range of federal new ndcs (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of federal new ndcs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.FederalNewNdc> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the federal new ndcs from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of federal new ndcs.
    *
    * @return the number of federal new ndcs
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static FederalNewNdcPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (FederalNewNdcPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    FederalNewNdcPersistence.class.getName());

            ReferenceRegistry.registerReference(FederalNewNdcUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(FederalNewNdcPersistence persistence) {
    }
}
