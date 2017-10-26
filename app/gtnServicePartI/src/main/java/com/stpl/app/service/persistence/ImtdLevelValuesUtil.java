package com.stpl.app.service.persistence;

import com.stpl.app.model.ImtdLevelValues;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the imtd level values service. This utility wraps {@link ImtdLevelValuesPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ImtdLevelValuesPersistence
 * @see ImtdLevelValuesPersistenceImpl
 * @generated
 */
public class ImtdLevelValuesUtil {
    private static ImtdLevelValuesPersistence _persistence;

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
    public static void clearCache(ImtdLevelValues imtdLevelValues) {
        getPersistence().clearCache(imtdLevelValues);
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
    public static List<ImtdLevelValues> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ImtdLevelValues> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ImtdLevelValues> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static ImtdLevelValues update(ImtdLevelValues imtdLevelValues)
        throws SystemException {
        return getPersistence().update(imtdLevelValues);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static ImtdLevelValues update(ImtdLevelValues imtdLevelValues,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(imtdLevelValues, serviceContext);
    }

    /**
    * Caches the imtd level values in the entity cache if it is enabled.
    *
    * @param imtdLevelValues the imtd level values
    */
    public static void cacheResult(
        com.stpl.app.model.ImtdLevelValues imtdLevelValues) {
        getPersistence().cacheResult(imtdLevelValues);
    }

    /**
    * Caches the imtd level valueses in the entity cache if it is enabled.
    *
    * @param imtdLevelValueses the imtd level valueses
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.ImtdLevelValues> imtdLevelValueses) {
        getPersistence().cacheResult(imtdLevelValueses);
    }

    /**
    * Creates a new imtd level values with the primary key. Does not add the imtd level values to the database.
    *
    * @param imtdLevelValuesSid the primary key for the new imtd level values
    * @return the new imtd level values
    */
    public static com.stpl.app.model.ImtdLevelValues create(
        int imtdLevelValuesSid) {
        return getPersistence().create(imtdLevelValuesSid);
    }

    /**
    * Removes the imtd level values with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param imtdLevelValuesSid the primary key of the imtd level values
    * @return the imtd level values that was removed
    * @throws com.stpl.app.NoSuchImtdLevelValuesException if a imtd level values with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdLevelValues remove(
        int imtdLevelValuesSid)
        throws com.stpl.app.NoSuchImtdLevelValuesException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(imtdLevelValuesSid);
    }

    public static com.stpl.app.model.ImtdLevelValues updateImpl(
        com.stpl.app.model.ImtdLevelValues imtdLevelValues)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(imtdLevelValues);
    }

    /**
    * Returns the imtd level values with the primary key or throws a {@link com.stpl.app.NoSuchImtdLevelValuesException} if it could not be found.
    *
    * @param imtdLevelValuesSid the primary key of the imtd level values
    * @return the imtd level values
    * @throws com.stpl.app.NoSuchImtdLevelValuesException if a imtd level values with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdLevelValues findByPrimaryKey(
        int imtdLevelValuesSid)
        throws com.stpl.app.NoSuchImtdLevelValuesException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(imtdLevelValuesSid);
    }

    /**
    * Returns the imtd level values with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param imtdLevelValuesSid the primary key of the imtd level values
    * @return the imtd level values, or <code>null</code> if a imtd level values with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdLevelValues fetchByPrimaryKey(
        int imtdLevelValuesSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(imtdLevelValuesSid);
    }

    /**
    * Returns all the imtd level valueses.
    *
    * @return the imtd level valueses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ImtdLevelValues> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the imtd level valueses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdLevelValuesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of imtd level valueses
    * @param end the upper bound of the range of imtd level valueses (not inclusive)
    * @return the range of imtd level valueses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ImtdLevelValues> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the imtd level valueses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdLevelValuesModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of imtd level valueses
    * @param end the upper bound of the range of imtd level valueses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of imtd level valueses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ImtdLevelValues> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the imtd level valueses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of imtd level valueses.
    *
    * @return the number of imtd level valueses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ImtdLevelValuesPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ImtdLevelValuesPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    ImtdLevelValuesPersistence.class.getName());

            ReferenceRegistry.registerReference(ImtdLevelValuesUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(ImtdLevelValuesPersistence persistence) {
    }
}
