package com.stpl.app.service.persistence;

import com.stpl.app.model.StMedicaidNewNdc;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the st medicaid new ndc service. This utility wraps {@link StMedicaidNewNdcPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see StMedicaidNewNdcPersistence
 * @see StMedicaidNewNdcPersistenceImpl
 * @generated
 */
public class StMedicaidNewNdcUtil {
    private static StMedicaidNewNdcPersistence _persistence;

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
    public static void clearCache(StMedicaidNewNdc stMedicaidNewNdc) {
        getPersistence().clearCache(stMedicaidNewNdc);
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
    public static List<StMedicaidNewNdc> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<StMedicaidNewNdc> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<StMedicaidNewNdc> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static StMedicaidNewNdc update(StMedicaidNewNdc stMedicaidNewNdc)
        throws SystemException {
        return getPersistence().update(stMedicaidNewNdc);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static StMedicaidNewNdc update(StMedicaidNewNdc stMedicaidNewNdc,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(stMedicaidNewNdc, serviceContext);
    }

    /**
    * Caches the st medicaid new ndc in the entity cache if it is enabled.
    *
    * @param stMedicaidNewNdc the st medicaid new ndc
    */
    public static void cacheResult(
        com.stpl.app.model.StMedicaidNewNdc stMedicaidNewNdc) {
        getPersistence().cacheResult(stMedicaidNewNdc);
    }

    /**
    * Caches the st medicaid new ndcs in the entity cache if it is enabled.
    *
    * @param stMedicaidNewNdcs the st medicaid new ndcs
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.StMedicaidNewNdc> stMedicaidNewNdcs) {
        getPersistence().cacheResult(stMedicaidNewNdcs);
    }

    /**
    * Creates a new st medicaid new ndc with the primary key. Does not add the st medicaid new ndc to the database.
    *
    * @param stMedicaidNewNdcPK the primary key for the new st medicaid new ndc
    * @return the new st medicaid new ndc
    */
    public static com.stpl.app.model.StMedicaidNewNdc create(
        StMedicaidNewNdcPK stMedicaidNewNdcPK) {
        return getPersistence().create(stMedicaidNewNdcPK);
    }

    /**
    * Removes the st medicaid new ndc with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param stMedicaidNewNdcPK the primary key of the st medicaid new ndc
    * @return the st medicaid new ndc that was removed
    * @throws com.stpl.app.NoSuchStMedicaidNewNdcException if a st medicaid new ndc with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StMedicaidNewNdc remove(
        StMedicaidNewNdcPK stMedicaidNewNdcPK)
        throws com.stpl.app.NoSuchStMedicaidNewNdcException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(stMedicaidNewNdcPK);
    }

    public static com.stpl.app.model.StMedicaidNewNdc updateImpl(
        com.stpl.app.model.StMedicaidNewNdc stMedicaidNewNdc)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(stMedicaidNewNdc);
    }

    /**
    * Returns the st medicaid new ndc with the primary key or throws a {@link com.stpl.app.NoSuchStMedicaidNewNdcException} if it could not be found.
    *
    * @param stMedicaidNewNdcPK the primary key of the st medicaid new ndc
    * @return the st medicaid new ndc
    * @throws com.stpl.app.NoSuchStMedicaidNewNdcException if a st medicaid new ndc with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StMedicaidNewNdc findByPrimaryKey(
        StMedicaidNewNdcPK stMedicaidNewNdcPK)
        throws com.stpl.app.NoSuchStMedicaidNewNdcException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(stMedicaidNewNdcPK);
    }

    /**
    * Returns the st medicaid new ndc with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param stMedicaidNewNdcPK the primary key of the st medicaid new ndc
    * @return the st medicaid new ndc, or <code>null</code> if a st medicaid new ndc with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.StMedicaidNewNdc fetchByPrimaryKey(
        StMedicaidNewNdcPK stMedicaidNewNdcPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(stMedicaidNewNdcPK);
    }

    /**
    * Returns all the st medicaid new ndcs.
    *
    * @return the st medicaid new ndcs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StMedicaidNewNdc> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the st medicaid new ndcs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StMedicaidNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st medicaid new ndcs
    * @param end the upper bound of the range of st medicaid new ndcs (not inclusive)
    * @return the range of st medicaid new ndcs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StMedicaidNewNdc> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the st medicaid new ndcs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.StMedicaidNewNdcModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of st medicaid new ndcs
    * @param end the upper bound of the range of st medicaid new ndcs (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of st medicaid new ndcs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.StMedicaidNewNdc> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the st medicaid new ndcs from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of st medicaid new ndcs.
    *
    * @return the number of st medicaid new ndcs
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static StMedicaidNewNdcPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (StMedicaidNewNdcPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    StMedicaidNewNdcPersistence.class.getName());

            ReferenceRegistry.registerReference(StMedicaidNewNdcUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(StMedicaidNewNdcPersistence persistence) {
    }
}
