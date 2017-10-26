package com.stpl.app.service.persistence;

import com.stpl.app.model.ImtdItemPriceRebateDetails;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the imtd item price rebate details service. This utility wraps {@link ImtdItemPriceRebateDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ImtdItemPriceRebateDetailsPersistence
 * @see ImtdItemPriceRebateDetailsPersistenceImpl
 * @generated
 */
public class ImtdItemPriceRebateDetailsUtil {
    private static ImtdItemPriceRebateDetailsPersistence _persistence;

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
        ImtdItemPriceRebateDetails imtdItemPriceRebateDetails) {
        getPersistence().clearCache(imtdItemPriceRebateDetails);
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
    public static List<ImtdItemPriceRebateDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ImtdItemPriceRebateDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ImtdItemPriceRebateDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static ImtdItemPriceRebateDetails update(
        ImtdItemPriceRebateDetails imtdItemPriceRebateDetails)
        throws SystemException {
        return getPersistence().update(imtdItemPriceRebateDetails);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static ImtdItemPriceRebateDetails update(
        ImtdItemPriceRebateDetails imtdItemPriceRebateDetails,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence()
                   .update(imtdItemPriceRebateDetails, serviceContext);
    }

    /**
    * Caches the imtd item price rebate details in the entity cache if it is enabled.
    *
    * @param imtdItemPriceRebateDetails the imtd item price rebate details
    */
    public static void cacheResult(
        com.stpl.app.model.ImtdItemPriceRebateDetails imtdItemPriceRebateDetails) {
        getPersistence().cacheResult(imtdItemPriceRebateDetails);
    }

    /**
    * Caches the imtd item price rebate detailses in the entity cache if it is enabled.
    *
    * @param imtdItemPriceRebateDetailses the imtd item price rebate detailses
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.ImtdItemPriceRebateDetails> imtdItemPriceRebateDetailses) {
        getPersistence().cacheResult(imtdItemPriceRebateDetailses);
    }

    /**
    * Creates a new imtd item price rebate details with the primary key. Does not add the imtd item price rebate details to the database.
    *
    * @param imtdItemPriceRebateSid the primary key for the new imtd item price rebate details
    * @return the new imtd item price rebate details
    */
    public static com.stpl.app.model.ImtdItemPriceRebateDetails create(
        int imtdItemPriceRebateSid) {
        return getPersistence().create(imtdItemPriceRebateSid);
    }

    /**
    * Removes the imtd item price rebate details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param imtdItemPriceRebateSid the primary key of the imtd item price rebate details
    * @return the imtd item price rebate details that was removed
    * @throws com.stpl.app.NoSuchImtdItemPriceRebateDetailsException if a imtd item price rebate details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdItemPriceRebateDetails remove(
        int imtdItemPriceRebateSid)
        throws com.stpl.app.NoSuchImtdItemPriceRebateDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(imtdItemPriceRebateSid);
    }

    public static com.stpl.app.model.ImtdItemPriceRebateDetails updateImpl(
        com.stpl.app.model.ImtdItemPriceRebateDetails imtdItemPriceRebateDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(imtdItemPriceRebateDetails);
    }

    /**
    * Returns the imtd item price rebate details with the primary key or throws a {@link com.stpl.app.NoSuchImtdItemPriceRebateDetailsException} if it could not be found.
    *
    * @param imtdItemPriceRebateSid the primary key of the imtd item price rebate details
    * @return the imtd item price rebate details
    * @throws com.stpl.app.NoSuchImtdItemPriceRebateDetailsException if a imtd item price rebate details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdItemPriceRebateDetails findByPrimaryKey(
        int imtdItemPriceRebateSid)
        throws com.stpl.app.NoSuchImtdItemPriceRebateDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(imtdItemPriceRebateSid);
    }

    /**
    * Returns the imtd item price rebate details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param imtdItemPriceRebateSid the primary key of the imtd item price rebate details
    * @return the imtd item price rebate details, or <code>null</code> if a imtd item price rebate details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdItemPriceRebateDetails fetchByPrimaryKey(
        int imtdItemPriceRebateSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(imtdItemPriceRebateSid);
    }

    /**
    * Returns all the imtd item price rebate detailses.
    *
    * @return the imtd item price rebate detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ImtdItemPriceRebateDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the imtd item price rebate detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdItemPriceRebateDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of imtd item price rebate detailses
    * @param end the upper bound of the range of imtd item price rebate detailses (not inclusive)
    * @return the range of imtd item price rebate detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ImtdItemPriceRebateDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the imtd item price rebate detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdItemPriceRebateDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of imtd item price rebate detailses
    * @param end the upper bound of the range of imtd item price rebate detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of imtd item price rebate detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ImtdItemPriceRebateDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the imtd item price rebate detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of imtd item price rebate detailses.
    *
    * @return the number of imtd item price rebate detailses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ImtdItemPriceRebateDetailsPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ImtdItemPriceRebateDetailsPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    ImtdItemPriceRebateDetailsPersistence.class.getName());

            ReferenceRegistry.registerReference(ImtdItemPriceRebateDetailsUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(
        ImtdItemPriceRebateDetailsPersistence persistence) {
    }
}
