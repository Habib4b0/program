package com.stpl.app.service.persistence;

import com.stpl.app.model.MedicaidUraActuals;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the medicaid ura actuals service. This utility wraps {@link MedicaidUraActualsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see MedicaidUraActualsPersistence
 * @see MedicaidUraActualsPersistenceImpl
 * @generated
 */
public class MedicaidUraActualsUtil {
    private static MedicaidUraActualsPersistence _persistence;

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
    public static void clearCache(MedicaidUraActuals medicaidUraActuals) {
        getPersistence().clearCache(medicaidUraActuals);
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
    public static List<MedicaidUraActuals> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<MedicaidUraActuals> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<MedicaidUraActuals> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static MedicaidUraActuals update(
        MedicaidUraActuals medicaidUraActuals) throws SystemException {
        return getPersistence().update(medicaidUraActuals);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static MedicaidUraActuals update(
        MedicaidUraActuals medicaidUraActuals, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(medicaidUraActuals, serviceContext);
    }

    /**
    * Caches the medicaid ura actuals in the entity cache if it is enabled.
    *
    * @param medicaidUraActuals the medicaid ura actuals
    */
    public static void cacheResult(
        com.stpl.app.model.MedicaidUraActuals medicaidUraActuals) {
        getPersistence().cacheResult(medicaidUraActuals);
    }

    /**
    * Caches the medicaid ura actualses in the entity cache if it is enabled.
    *
    * @param medicaidUraActualses the medicaid ura actualses
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.MedicaidUraActuals> medicaidUraActualses) {
        getPersistence().cacheResult(medicaidUraActualses);
    }

    /**
    * Creates a new medicaid ura actuals with the primary key. Does not add the medicaid ura actuals to the database.
    *
    * @param medicaidUraActualsPK the primary key for the new medicaid ura actuals
    * @return the new medicaid ura actuals
    */
    public static com.stpl.app.model.MedicaidUraActuals create(
        MedicaidUraActualsPK medicaidUraActualsPK) {
        return getPersistence().create(medicaidUraActualsPK);
    }

    /**
    * Removes the medicaid ura actuals with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param medicaidUraActualsPK the primary key of the medicaid ura actuals
    * @return the medicaid ura actuals that was removed
    * @throws com.stpl.app.NoSuchMedicaidUraActualsException if a medicaid ura actuals with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MedicaidUraActuals remove(
        MedicaidUraActualsPK medicaidUraActualsPK)
        throws com.stpl.app.NoSuchMedicaidUraActualsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(medicaidUraActualsPK);
    }

    public static com.stpl.app.model.MedicaidUraActuals updateImpl(
        com.stpl.app.model.MedicaidUraActuals medicaidUraActuals)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(medicaidUraActuals);
    }

    /**
    * Returns the medicaid ura actuals with the primary key or throws a {@link com.stpl.app.NoSuchMedicaidUraActualsException} if it could not be found.
    *
    * @param medicaidUraActualsPK the primary key of the medicaid ura actuals
    * @return the medicaid ura actuals
    * @throws com.stpl.app.NoSuchMedicaidUraActualsException if a medicaid ura actuals with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MedicaidUraActuals findByPrimaryKey(
        MedicaidUraActualsPK medicaidUraActualsPK)
        throws com.stpl.app.NoSuchMedicaidUraActualsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(medicaidUraActualsPK);
    }

    /**
    * Returns the medicaid ura actuals with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param medicaidUraActualsPK the primary key of the medicaid ura actuals
    * @return the medicaid ura actuals, or <code>null</code> if a medicaid ura actuals with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MedicaidUraActuals fetchByPrimaryKey(
        MedicaidUraActualsPK medicaidUraActualsPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(medicaidUraActualsPK);
    }

    /**
    * Returns all the medicaid ura actualses.
    *
    * @return the medicaid ura actualses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.MedicaidUraActuals> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the medicaid ura actualses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MedicaidUraActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of medicaid ura actualses
    * @param end the upper bound of the range of medicaid ura actualses (not inclusive)
    * @return the range of medicaid ura actualses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.MedicaidUraActuals> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the medicaid ura actualses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MedicaidUraActualsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of medicaid ura actualses
    * @param end the upper bound of the range of medicaid ura actualses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of medicaid ura actualses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.MedicaidUraActuals> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the medicaid ura actualses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of medicaid ura actualses.
    *
    * @return the number of medicaid ura actualses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static MedicaidUraActualsPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (MedicaidUraActualsPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    MedicaidUraActualsPersistence.class.getName());

            ReferenceRegistry.registerReference(MedicaidUraActualsUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(MedicaidUraActualsPersistence persistence) {
    }
}
