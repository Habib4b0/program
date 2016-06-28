package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.CffDocDetails;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the cff doc details service. This utility wraps {@link CffDocDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CffDocDetailsPersistence
 * @see CffDocDetailsPersistenceImpl
 * @generated
 */
public class CffDocDetailsUtil {
    private static CffDocDetailsPersistence _persistence;

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
    public static void clearCache(CffDocDetails cffDocDetails) {
        getPersistence().clearCache(cffDocDetails);
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
    public static List<CffDocDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<CffDocDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<CffDocDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static CffDocDetails update(CffDocDetails cffDocDetails)
        throws SystemException {
        return getPersistence().update(cffDocDetails);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static CffDocDetails update(CffDocDetails cffDocDetails,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(cffDocDetails, serviceContext);
    }

    /**
    * Caches the cff doc details in the entity cache if it is enabled.
    *
    * @param cffDocDetails the cff doc details
    */
    public static void cacheResult(
        com.stpl.app.parttwo.model.CffDocDetails cffDocDetails) {
        getPersistence().cacheResult(cffDocDetails);
    }

    /**
    * Caches the cff doc detailses in the entity cache if it is enabled.
    *
    * @param cffDocDetailses the cff doc detailses
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.CffDocDetails> cffDocDetailses) {
        getPersistence().cacheResult(cffDocDetailses);
    }

    /**
    * Creates a new cff doc details with the primary key. Does not add the cff doc details to the database.
    *
    * @param cffDocDetailsSid the primary key for the new cff doc details
    * @return the new cff doc details
    */
    public static com.stpl.app.parttwo.model.CffDocDetails create(
        int cffDocDetailsSid) {
        return getPersistence().create(cffDocDetailsSid);
    }

    /**
    * Removes the cff doc details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param cffDocDetailsSid the primary key of the cff doc details
    * @return the cff doc details that was removed
    * @throws com.stpl.app.parttwo.NoSuchCffDocDetailsException if a cff doc details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.CffDocDetails remove(
        int cffDocDetailsSid)
        throws com.stpl.app.parttwo.NoSuchCffDocDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(cffDocDetailsSid);
    }

    public static com.stpl.app.parttwo.model.CffDocDetails updateImpl(
        com.stpl.app.parttwo.model.CffDocDetails cffDocDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(cffDocDetails);
    }

    /**
    * Returns the cff doc details with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchCffDocDetailsException} if it could not be found.
    *
    * @param cffDocDetailsSid the primary key of the cff doc details
    * @return the cff doc details
    * @throws com.stpl.app.parttwo.NoSuchCffDocDetailsException if a cff doc details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.CffDocDetails findByPrimaryKey(
        int cffDocDetailsSid)
        throws com.stpl.app.parttwo.NoSuchCffDocDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(cffDocDetailsSid);
    }

    /**
    * Returns the cff doc details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param cffDocDetailsSid the primary key of the cff doc details
    * @return the cff doc details, or <code>null</code> if a cff doc details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.CffDocDetails fetchByPrimaryKey(
        int cffDocDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(cffDocDetailsSid);
    }

    /**
    * Returns all the cff doc detailses.
    *
    * @return the cff doc detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.CffDocDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the cff doc detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffDocDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cff doc detailses
    * @param end the upper bound of the range of cff doc detailses (not inclusive)
    * @return the range of cff doc detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.CffDocDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the cff doc detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.CffDocDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cff doc detailses
    * @param end the upper bound of the range of cff doc detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of cff doc detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.CffDocDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the cff doc detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of cff doc detailses.
    *
    * @return the number of cff doc detailses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static CffDocDetailsPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (CffDocDetailsPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.parttwo.service.ClpSerializer.getServletContextName(),
                    CffDocDetailsPersistence.class.getName());

            ReferenceRegistry.registerReference(CffDocDetailsUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(CffDocDetailsPersistence persistence) {
    }
}
