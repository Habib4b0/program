package com.stpl.app.service.persistence;

import com.stpl.app.model.ImtdSalesBasisDetails;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the imtd sales basis details service. This utility wraps {@link ImtdSalesBasisDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ImtdSalesBasisDetailsPersistence
 * @see ImtdSalesBasisDetailsPersistenceImpl
 * @generated
 */
public class ImtdSalesBasisDetailsUtil {
    private static ImtdSalesBasisDetailsPersistence _persistence;

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
    public static void clearCache(ImtdSalesBasisDetails imtdSalesBasisDetails) {
        getPersistence().clearCache(imtdSalesBasisDetails);
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
    public static List<ImtdSalesBasisDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ImtdSalesBasisDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ImtdSalesBasisDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static ImtdSalesBasisDetails update(
        ImtdSalesBasisDetails imtdSalesBasisDetails) throws SystemException {
        return getPersistence().update(imtdSalesBasisDetails);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static ImtdSalesBasisDetails update(
        ImtdSalesBasisDetails imtdSalesBasisDetails,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(imtdSalesBasisDetails, serviceContext);
    }

    /**
    * Caches the imtd sales basis details in the entity cache if it is enabled.
    *
    * @param imtdSalesBasisDetails the imtd sales basis details
    */
    public static void cacheResult(
        com.stpl.app.model.ImtdSalesBasisDetails imtdSalesBasisDetails) {
        getPersistence().cacheResult(imtdSalesBasisDetails);
    }

    /**
    * Caches the imtd sales basis detailses in the entity cache if it is enabled.
    *
    * @param imtdSalesBasisDetailses the imtd sales basis detailses
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.ImtdSalesBasisDetails> imtdSalesBasisDetailses) {
        getPersistence().cacheResult(imtdSalesBasisDetailses);
    }

    /**
    * Creates a new imtd sales basis details with the primary key. Does not add the imtd sales basis details to the database.
    *
    * @param imtdSalesBasisDetailsSid the primary key for the new imtd sales basis details
    * @return the new imtd sales basis details
    */
    public static com.stpl.app.model.ImtdSalesBasisDetails create(
        int imtdSalesBasisDetailsSid) {
        return getPersistence().create(imtdSalesBasisDetailsSid);
    }

    /**
    * Removes the imtd sales basis details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param imtdSalesBasisDetailsSid the primary key of the imtd sales basis details
    * @return the imtd sales basis details that was removed
    * @throws com.stpl.app.NoSuchImtdSalesBasisDetailsException if a imtd sales basis details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdSalesBasisDetails remove(
        int imtdSalesBasisDetailsSid)
        throws com.stpl.app.NoSuchImtdSalesBasisDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(imtdSalesBasisDetailsSid);
    }

    public static com.stpl.app.model.ImtdSalesBasisDetails updateImpl(
        com.stpl.app.model.ImtdSalesBasisDetails imtdSalesBasisDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(imtdSalesBasisDetails);
    }

    /**
    * Returns the imtd sales basis details with the primary key or throws a {@link com.stpl.app.NoSuchImtdSalesBasisDetailsException} if it could not be found.
    *
    * @param imtdSalesBasisDetailsSid the primary key of the imtd sales basis details
    * @return the imtd sales basis details
    * @throws com.stpl.app.NoSuchImtdSalesBasisDetailsException if a imtd sales basis details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdSalesBasisDetails findByPrimaryKey(
        int imtdSalesBasisDetailsSid)
        throws com.stpl.app.NoSuchImtdSalesBasisDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(imtdSalesBasisDetailsSid);
    }

    /**
    * Returns the imtd sales basis details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param imtdSalesBasisDetailsSid the primary key of the imtd sales basis details
    * @return the imtd sales basis details, or <code>null</code> if a imtd sales basis details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdSalesBasisDetails fetchByPrimaryKey(
        int imtdSalesBasisDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(imtdSalesBasisDetailsSid);
    }

    /**
    * Returns all the imtd sales basis detailses.
    *
    * @return the imtd sales basis detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ImtdSalesBasisDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the imtd sales basis detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdSalesBasisDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of imtd sales basis detailses
    * @param end the upper bound of the range of imtd sales basis detailses (not inclusive)
    * @return the range of imtd sales basis detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ImtdSalesBasisDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the imtd sales basis detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdSalesBasisDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of imtd sales basis detailses
    * @param end the upper bound of the range of imtd sales basis detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of imtd sales basis detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ImtdSalesBasisDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the imtd sales basis detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of imtd sales basis detailses.
    *
    * @return the number of imtd sales basis detailses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ImtdSalesBasisDetailsPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ImtdSalesBasisDetailsPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    ImtdSalesBasisDetailsPersistence.class.getName());

            ReferenceRegistry.registerReference(ImtdSalesBasisDetailsUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(ImtdSalesBasisDetailsPersistence persistence) {
    }
}
