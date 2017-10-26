package com.stpl.app.service.persistence;

import com.stpl.app.model.MAssumptions;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the m assumptions service. This utility wraps {@link MAssumptionsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see MAssumptionsPersistence
 * @see MAssumptionsPersistenceImpl
 * @generated
 */
public class MAssumptionsUtil {
    private static MAssumptionsPersistence _persistence;

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
    public static void clearCache(MAssumptions mAssumptions) {
        getPersistence().clearCache(mAssumptions);
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
    public static List<MAssumptions> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<MAssumptions> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<MAssumptions> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static MAssumptions update(MAssumptions mAssumptions)
        throws SystemException {
        return getPersistence().update(mAssumptions);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static MAssumptions update(MAssumptions mAssumptions,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(mAssumptions, serviceContext);
    }

    /**
    * Caches the m assumptions in the entity cache if it is enabled.
    *
    * @param mAssumptions the m assumptions
    */
    public static void cacheResult(com.stpl.app.model.MAssumptions mAssumptions) {
        getPersistence().cacheResult(mAssumptions);
    }

    /**
    * Caches the m assumptionses in the entity cache if it is enabled.
    *
    * @param mAssumptionses the m assumptionses
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.MAssumptions> mAssumptionses) {
        getPersistence().cacheResult(mAssumptionses);
    }

    /**
    * Creates a new m assumptions with the primary key. Does not add the m assumptions to the database.
    *
    * @param mAssumptionsSid the primary key for the new m assumptions
    * @return the new m assumptions
    */
    public static com.stpl.app.model.MAssumptions create(int mAssumptionsSid) {
        return getPersistence().create(mAssumptionsSid);
    }

    /**
    * Removes the m assumptions with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param mAssumptionsSid the primary key of the m assumptions
    * @return the m assumptions that was removed
    * @throws com.stpl.app.NoSuchMAssumptionsException if a m assumptions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MAssumptions remove(int mAssumptionsSid)
        throws com.stpl.app.NoSuchMAssumptionsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(mAssumptionsSid);
    }

    public static com.stpl.app.model.MAssumptions updateImpl(
        com.stpl.app.model.MAssumptions mAssumptions)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(mAssumptions);
    }

    /**
    * Returns the m assumptions with the primary key or throws a {@link com.stpl.app.NoSuchMAssumptionsException} if it could not be found.
    *
    * @param mAssumptionsSid the primary key of the m assumptions
    * @return the m assumptions
    * @throws com.stpl.app.NoSuchMAssumptionsException if a m assumptions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MAssumptions findByPrimaryKey(
        int mAssumptionsSid)
        throws com.stpl.app.NoSuchMAssumptionsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(mAssumptionsSid);
    }

    /**
    * Returns the m assumptions with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param mAssumptionsSid the primary key of the m assumptions
    * @return the m assumptions, or <code>null</code> if a m assumptions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MAssumptions fetchByPrimaryKey(
        int mAssumptionsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(mAssumptionsSid);
    }

    /**
    * Returns all the m assumptionses.
    *
    * @return the m assumptionses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.MAssumptions> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the m assumptionses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of m assumptionses
    * @param end the upper bound of the range of m assumptionses (not inclusive)
    * @return the range of m assumptionses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.MAssumptions> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the m assumptionses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of m assumptionses
    * @param end the upper bound of the range of m assumptionses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of m assumptionses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.MAssumptions> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the m assumptionses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of m assumptionses.
    *
    * @return the number of m assumptionses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static MAssumptionsPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (MAssumptionsPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    MAssumptionsPersistence.class.getName());

            ReferenceRegistry.registerReference(MAssumptionsUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(MAssumptionsPersistence persistence) {
    }
}
