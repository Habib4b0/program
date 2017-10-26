package com.stpl.app.service.persistence;

import com.stpl.app.model.MedicaidUraProj;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the medicaid ura proj service. This utility wraps {@link MedicaidUraProjPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see MedicaidUraProjPersistence
 * @see MedicaidUraProjPersistenceImpl
 * @generated
 */
public class MedicaidUraProjUtil {
    private static MedicaidUraProjPersistence _persistence;

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
    public static void clearCache(MedicaidUraProj medicaidUraProj) {
        getPersistence().clearCache(medicaidUraProj);
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
    public static List<MedicaidUraProj> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<MedicaidUraProj> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<MedicaidUraProj> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static MedicaidUraProj update(MedicaidUraProj medicaidUraProj)
        throws SystemException {
        return getPersistence().update(medicaidUraProj);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static MedicaidUraProj update(MedicaidUraProj medicaidUraProj,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(medicaidUraProj, serviceContext);
    }

    /**
    * Caches the medicaid ura proj in the entity cache if it is enabled.
    *
    * @param medicaidUraProj the medicaid ura proj
    */
    public static void cacheResult(
        com.stpl.app.model.MedicaidUraProj medicaidUraProj) {
        getPersistence().cacheResult(medicaidUraProj);
    }

    /**
    * Caches the medicaid ura projs in the entity cache if it is enabled.
    *
    * @param medicaidUraProjs the medicaid ura projs
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.MedicaidUraProj> medicaidUraProjs) {
        getPersistence().cacheResult(medicaidUraProjs);
    }

    /**
    * Creates a new medicaid ura proj with the primary key. Does not add the medicaid ura proj to the database.
    *
    * @param medicaidUraProjPK the primary key for the new medicaid ura proj
    * @return the new medicaid ura proj
    */
    public static com.stpl.app.model.MedicaidUraProj create(
        MedicaidUraProjPK medicaidUraProjPK) {
        return getPersistence().create(medicaidUraProjPK);
    }

    /**
    * Removes the medicaid ura proj with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param medicaidUraProjPK the primary key of the medicaid ura proj
    * @return the medicaid ura proj that was removed
    * @throws com.stpl.app.NoSuchMedicaidUraProjException if a medicaid ura proj with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MedicaidUraProj remove(
        MedicaidUraProjPK medicaidUraProjPK)
        throws com.stpl.app.NoSuchMedicaidUraProjException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(medicaidUraProjPK);
    }

    public static com.stpl.app.model.MedicaidUraProj updateImpl(
        com.stpl.app.model.MedicaidUraProj medicaidUraProj)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(medicaidUraProj);
    }

    /**
    * Returns the medicaid ura proj with the primary key or throws a {@link com.stpl.app.NoSuchMedicaidUraProjException} if it could not be found.
    *
    * @param medicaidUraProjPK the primary key of the medicaid ura proj
    * @return the medicaid ura proj
    * @throws com.stpl.app.NoSuchMedicaidUraProjException if a medicaid ura proj with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MedicaidUraProj findByPrimaryKey(
        MedicaidUraProjPK medicaidUraProjPK)
        throws com.stpl.app.NoSuchMedicaidUraProjException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(medicaidUraProjPK);
    }

    /**
    * Returns the medicaid ura proj with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param medicaidUraProjPK the primary key of the medicaid ura proj
    * @return the medicaid ura proj, or <code>null</code> if a medicaid ura proj with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MedicaidUraProj fetchByPrimaryKey(
        MedicaidUraProjPK medicaidUraProjPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(medicaidUraProjPK);
    }

    /**
    * Returns all the medicaid ura projs.
    *
    * @return the medicaid ura projs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.MedicaidUraProj> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the medicaid ura projs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MedicaidUraProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of medicaid ura projs
    * @param end the upper bound of the range of medicaid ura projs (not inclusive)
    * @return the range of medicaid ura projs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.MedicaidUraProj> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the medicaid ura projs.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MedicaidUraProjModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of medicaid ura projs
    * @param end the upper bound of the range of medicaid ura projs (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of medicaid ura projs
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.MedicaidUraProj> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the medicaid ura projs from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of medicaid ura projs.
    *
    * @return the number of medicaid ura projs
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static MedicaidUraProjPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (MedicaidUraProjPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    MedicaidUraProjPersistence.class.getName());

            ReferenceRegistry.registerReference(MedicaidUraProjUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(MedicaidUraProjPersistence persistence) {
    }
}
