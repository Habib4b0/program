package com.stpl.app.service.persistence;

import com.stpl.app.model.NationalAssumptionsProj;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the national assumptions proj service. This utility wraps {@link NationalAssumptionsProjPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NationalAssumptionsProjPersistence
 * @see NationalAssumptionsProjPersistenceImpl
 * @generated
 */
public class NationalAssumptionsProjUtil {
    private static NationalAssumptionsProjPersistence _persistence;

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
        NationalAssumptionsProj nationalAssumptionsProj) {
        getPersistence().clearCache(nationalAssumptionsProj);
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
    public static List<NationalAssumptionsProj> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<NationalAssumptionsProj> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<NationalAssumptionsProj> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static NationalAssumptionsProj update(
        NationalAssumptionsProj nationalAssumptionsProj)
        throws SystemException {
        return getPersistence().update(nationalAssumptionsProj);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static NationalAssumptionsProj update(
        NationalAssumptionsProj nationalAssumptionsProj,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(nationalAssumptionsProj, serviceContext);
    }

    /**
    * Caches the national assumptions proj in the entity cache if it is enabled.
    *
    * @param nationalAssumptionsProj the national assumptions proj
    */
    public static void cacheResult(
        com.stpl.app.model.NationalAssumptionsProj nationalAssumptionsProj) {
        getPersistence().cacheResult(nationalAssumptionsProj);
    }

    /**
    * Caches the national assumptions projs in the entity cache if it is enabled.
    *
    * @param nationalAssumptionsProjs the national assumptions projs
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.NationalAssumptionsProj> nationalAssumptionsProjs) {
        getPersistence().cacheResult(nationalAssumptionsProjs);
    }

    /**
    * Creates a new national assumptions proj with the primary key. Does not add the national assumptions proj to the database.
    *
    * @param nationalAssumptionsProjPK the primary key for the new national assumptions proj
    * @return the new national assumptions proj
    */
    public static com.stpl.app.model.NationalAssumptionsProj create(
        NationalAssumptionsProjPK nationalAssumptionsProjPK) {
        return getPersistence().create(nationalAssumptionsProjPK);
    }

    /**
    * Removes the national assumptions proj with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param nationalAssumptionsProjPK the primary key of the national assumptions proj
    * @return the national assumptions proj that was removed
    * @throws com.stpl.app.NoSuchNationalAssumptionsProjException if a national assumptions proj with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.NationalAssumptionsProj remove(
        NationalAssumptionsProjPK nationalAssumptionsProjPK)
        throws com.stpl.app.NoSuchNationalAssumptionsProjException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(nationalAssumptionsProjPK);
    }

    public static com.stpl.app.model.NationalAssumptionsProj updateImpl(
        com.stpl.app.model.NationalAssumptionsProj nationalAssumptionsProj)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(nationalAssumptionsProj);
    }

    /**
    * Returns the national assumptions proj with the primary key or throws a {@link com.stpl.app.NoSuchNationalAssumptionsProjException} if it could not be found.
    *
    * @param nationalAssumptionsProjPK the primary key of the national assumptions proj
    * @return the national assumptions proj
    * @throws com.stpl.app.NoSuchNationalAssumptionsProjException if a national assumptions proj with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.NationalAssumptionsProj findByPrimaryKey(
        NationalAssumptionsProjPK nationalAssumptionsProjPK)
        throws com.stpl.app.NoSuchNationalAssumptionsProjException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(nationalAssumptionsProjPK);
    }

    /**
    * Returns the national assumptions proj with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param nationalAssumptionsProjPK the primary key of the national assumptions proj
    * @return the national assumptions proj, or <code>null</code> if a national assumptions proj with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.NationalAssumptionsProj fetchByPrimaryKey(
        NationalAssumptionsProjPK nationalAssumptionsProjPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(nationalAssumptionsProjPK);
    }

    /**
    * Returns all the national assumptions projs.
    *
    * @return the national assumptions projs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.NationalAssumptionsProj> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the national assumptions projs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NationalAssumptionsProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of national assumptions projs
    * @param end the upper bound of the range of national assumptions projs (not inclusive)
    * @return the range of national assumptions projs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.NationalAssumptionsProj> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the national assumptions projs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NationalAssumptionsProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of national assumptions projs
    * @param end the upper bound of the range of national assumptions projs (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of national assumptions projs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.NationalAssumptionsProj> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the national assumptions projs from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of national assumptions projs.
    *
    * @return the number of national assumptions projs
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static NationalAssumptionsProjPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (NationalAssumptionsProjPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    NationalAssumptionsProjPersistence.class.getName());

            ReferenceRegistry.registerReference(NationalAssumptionsProjUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(NationalAssumptionsProjPersistence persistence) {
    }
}
