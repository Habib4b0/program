package com.stpl.app.service.persistence;

import com.stpl.app.model.RebateTierFormula;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the rebate tier formula service. This utility wraps {@link RebateTierFormulaPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see RebateTierFormulaPersistence
 * @see RebateTierFormulaPersistenceImpl
 * @generated
 */
public class RebateTierFormulaUtil {
    private static RebateTierFormulaPersistence _persistence;

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
    public static void clearCache(RebateTierFormula rebateTierFormula) {
        getPersistence().clearCache(rebateTierFormula);
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
    public static List<RebateTierFormula> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<RebateTierFormula> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<RebateTierFormula> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static RebateTierFormula update(RebateTierFormula rebateTierFormula)
        throws SystemException {
        return getPersistence().update(rebateTierFormula);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static RebateTierFormula update(
        RebateTierFormula rebateTierFormula, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(rebateTierFormula, serviceContext);
    }

    /**
    * Caches the rebate tier formula in the entity cache if it is enabled.
    *
    * @param rebateTierFormula the rebate tier formula
    */
    public static void cacheResult(
        com.stpl.app.model.RebateTierFormula rebateTierFormula) {
        getPersistence().cacheResult(rebateTierFormula);
    }

    /**
    * Caches the rebate tier formulas in the entity cache if it is enabled.
    *
    * @param rebateTierFormulas the rebate tier formulas
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.RebateTierFormula> rebateTierFormulas) {
        getPersistence().cacheResult(rebateTierFormulas);
    }

    /**
    * Creates a new rebate tier formula with the primary key. Does not add the rebate tier formula to the database.
    *
    * @param rebateTierFormulaSid the primary key for the new rebate tier formula
    * @return the new rebate tier formula
    */
    public static com.stpl.app.model.RebateTierFormula create(
        int rebateTierFormulaSid) {
        return getPersistence().create(rebateTierFormulaSid);
    }

    /**
    * Removes the rebate tier formula with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param rebateTierFormulaSid the primary key of the rebate tier formula
    * @return the rebate tier formula that was removed
    * @throws com.stpl.app.NoSuchRebateTierFormulaException if a rebate tier formula with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RebateTierFormula remove(
        int rebateTierFormulaSid)
        throws com.stpl.app.NoSuchRebateTierFormulaException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(rebateTierFormulaSid);
    }

    public static com.stpl.app.model.RebateTierFormula updateImpl(
        com.stpl.app.model.RebateTierFormula rebateTierFormula)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(rebateTierFormula);
    }

    /**
    * Returns the rebate tier formula with the primary key or throws a {@link com.stpl.app.NoSuchRebateTierFormulaException} if it could not be found.
    *
    * @param rebateTierFormulaSid the primary key of the rebate tier formula
    * @return the rebate tier formula
    * @throws com.stpl.app.NoSuchRebateTierFormulaException if a rebate tier formula with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RebateTierFormula findByPrimaryKey(
        int rebateTierFormulaSid)
        throws com.stpl.app.NoSuchRebateTierFormulaException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(rebateTierFormulaSid);
    }

    /**
    * Returns the rebate tier formula with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param rebateTierFormulaSid the primary key of the rebate tier formula
    * @return the rebate tier formula, or <code>null</code> if a rebate tier formula with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.RebateTierFormula fetchByPrimaryKey(
        int rebateTierFormulaSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(rebateTierFormulaSid);
    }

    /**
    * Returns all the rebate tier formulas.
    *
    * @return the rebate tier formulas
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RebateTierFormula> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the rebate tier formulas.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebateTierFormulaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of rebate tier formulas
    * @param end the upper bound of the range of rebate tier formulas (not inclusive)
    * @return the range of rebate tier formulas
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RebateTierFormula> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the rebate tier formulas.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebateTierFormulaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of rebate tier formulas
    * @param end the upper bound of the range of rebate tier formulas (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of rebate tier formulas
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.RebateTierFormula> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the rebate tier formulas from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of rebate tier formulas.
    *
    * @return the number of rebate tier formulas
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static RebateTierFormulaPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (RebateTierFormulaPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    RebateTierFormulaPersistence.class.getName());

            ReferenceRegistry.registerReference(RebateTierFormulaUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(RebateTierFormulaPersistence persistence) {
    }
}
