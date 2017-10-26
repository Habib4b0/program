package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.CffDetails;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the cff details service. This utility wraps {@link CffDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CffDetailsPersistence
 * @see CffDetailsPersistenceImpl
 * @generated
 */
public class CffDetailsUtil {
    private static CffDetailsPersistence _persistence;

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
    public static void clearCache(CffDetails cffDetails) {
        getPersistence().clearCache(cffDetails);
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
    public static List<CffDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<CffDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<CffDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static CffDetails update(CffDetails cffDetails)
        throws SystemException {
        return getPersistence().update(cffDetails);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static CffDetails update(CffDetails cffDetails,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(cffDetails, serviceContext);
    }

    /**
    * Caches the cff details in the entity cache if it is enabled.
    *
    * @param cffDetails the cff details
    */
    public static void cacheResult(
        com.stpl.app.parttwo.model.CffDetails cffDetails) {
        getPersistence().cacheResult(cffDetails);
    }

    /**
    * Caches the cff detailses in the entity cache if it is enabled.
    *
    * @param cffDetailses the cff detailses
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.CffDetails> cffDetailses) {
        getPersistence().cacheResult(cffDetailses);
    }

    /**
    * Creates a new cff details with the primary key. Does not add the cff details to the database.
    *
    * @param cffDetailsSid the primary key for the new cff details
    * @return the new cff details
    */
    public static com.stpl.app.parttwo.model.CffDetails create(
        int cffDetailsSid) {
        return getPersistence().create(cffDetailsSid);
    }

    /**
    * Removes the cff details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param cffDetailsSid the primary key of the cff details
    * @return the cff details that was removed
    * @throws com.stpl.app.parttwo.NoSuchCffDetailsException if a cff details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.CffDetails remove(
        int cffDetailsSid)
        throws com.stpl.app.parttwo.NoSuchCffDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(cffDetailsSid);
    }

    public static com.stpl.app.parttwo.model.CffDetails updateImpl(
        com.stpl.app.parttwo.model.CffDetails cffDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(cffDetails);
    }

    /**
    * Returns the cff details with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchCffDetailsException} if it could not be found.
    *
    * @param cffDetailsSid the primary key of the cff details
    * @return the cff details
    * @throws com.stpl.app.parttwo.NoSuchCffDetailsException if a cff details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.CffDetails findByPrimaryKey(
        int cffDetailsSid)
        throws com.stpl.app.parttwo.NoSuchCffDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(cffDetailsSid);
    }

    /**
    * Returns the cff details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param cffDetailsSid the primary key of the cff details
    * @return the cff details, or <code>null</code> if a cff details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.CffDetails fetchByPrimaryKey(
        int cffDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(cffDetailsSid);
    }

    /**
    * Returns all the cff detailses.
    *
    * @return the cff detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.CffDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the cff detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cff detailses
    * @param end the upper bound of the range of cff detailses (not inclusive)
    * @return the range of cff detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.CffDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the cff detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cff detailses
    * @param end the upper bound of the range of cff detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of cff detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.CffDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the cff detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of cff detailses.
    *
    * @return the number of cff detailses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static CffDetailsPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (CffDetailsPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.parttwo.service.ClpSerializer.getServletContextName(),
                    CffDetailsPersistence.class.getName());

            ReferenceRegistry.registerReference(CffDetailsUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(CffDetailsPersistence persistence) {
    }
}
