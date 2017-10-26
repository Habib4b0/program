package com.stpl.app.service.persistence;

import com.stpl.app.model.ChAssumptions;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the ch assumptions service. This utility wraps {@link ChAssumptionsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ChAssumptionsPersistence
 * @see ChAssumptionsPersistenceImpl
 * @generated
 */
public class ChAssumptionsUtil {
    private static ChAssumptionsPersistence _persistence;

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
    public static void clearCache(ChAssumptions chAssumptions) {
        getPersistence().clearCache(chAssumptions);
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
    public static List<ChAssumptions> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ChAssumptions> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ChAssumptions> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static ChAssumptions update(ChAssumptions chAssumptions)
        throws SystemException {
        return getPersistence().update(chAssumptions);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static ChAssumptions update(ChAssumptions chAssumptions,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(chAssumptions, serviceContext);
    }

    /**
    * Caches the ch assumptions in the entity cache if it is enabled.
    *
    * @param chAssumptions the ch assumptions
    */
    public static void cacheResult(
        com.stpl.app.model.ChAssumptions chAssumptions) {
        getPersistence().cacheResult(chAssumptions);
    }

    /**
    * Caches the ch assumptionses in the entity cache if it is enabled.
    *
    * @param chAssumptionses the ch assumptionses
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.ChAssumptions> chAssumptionses) {
        getPersistence().cacheResult(chAssumptionses);
    }

    /**
    * Creates a new ch assumptions with the primary key. Does not add the ch assumptions to the database.
    *
    * @param chAssumptionsSid the primary key for the new ch assumptions
    * @return the new ch assumptions
    */
    public static com.stpl.app.model.ChAssumptions create(int chAssumptionsSid) {
        return getPersistence().create(chAssumptionsSid);
    }

    /**
    * Removes the ch assumptions with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param chAssumptionsSid the primary key of the ch assumptions
    * @return the ch assumptions that was removed
    * @throws com.stpl.app.NoSuchChAssumptionsException if a ch assumptions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ChAssumptions remove(int chAssumptionsSid)
        throws com.stpl.app.NoSuchChAssumptionsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(chAssumptionsSid);
    }

    public static com.stpl.app.model.ChAssumptions updateImpl(
        com.stpl.app.model.ChAssumptions chAssumptions)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(chAssumptions);
    }

    /**
    * Returns the ch assumptions with the primary key or throws a {@link com.stpl.app.NoSuchChAssumptionsException} if it could not be found.
    *
    * @param chAssumptionsSid the primary key of the ch assumptions
    * @return the ch assumptions
    * @throws com.stpl.app.NoSuchChAssumptionsException if a ch assumptions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ChAssumptions findByPrimaryKey(
        int chAssumptionsSid)
        throws com.stpl.app.NoSuchChAssumptionsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(chAssumptionsSid);
    }

    /**
    * Returns the ch assumptions with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param chAssumptionsSid the primary key of the ch assumptions
    * @return the ch assumptions, or <code>null</code> if a ch assumptions with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ChAssumptions fetchByPrimaryKey(
        int chAssumptionsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(chAssumptionsSid);
    }

    /**
    * Returns all the ch assumptionses.
    *
    * @return the ch assumptionses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ChAssumptions> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the ch assumptionses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ch assumptionses
    * @param end the upper bound of the range of ch assumptionses (not inclusive)
    * @return the range of ch assumptionses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ChAssumptions> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the ch assumptionses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ChAssumptionsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ch assumptionses
    * @param end the upper bound of the range of ch assumptionses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ch assumptionses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ChAssumptions> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the ch assumptionses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of ch assumptionses.
    *
    * @return the number of ch assumptionses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ChAssumptionsPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ChAssumptionsPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    ChAssumptionsPersistence.class.getName());

            ReferenceRegistry.registerReference(ChAssumptionsUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(ChAssumptionsPersistence persistence) {
    }
}
