package com.stpl.app.service.persistence;

import com.stpl.app.model.ImtdPsDetails;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the imtd ps details service. This utility wraps {@link ImtdPsDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ImtdPsDetailsPersistence
 * @see ImtdPsDetailsPersistenceImpl
 * @generated
 */
public class ImtdPsDetailsUtil {
    private static ImtdPsDetailsPersistence _persistence;

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
    public static void clearCache(ImtdPsDetails imtdPsDetails) {
        getPersistence().clearCache(imtdPsDetails);
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
    public static List<ImtdPsDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ImtdPsDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ImtdPsDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static ImtdPsDetails update(ImtdPsDetails imtdPsDetails)
        throws SystemException {
        return getPersistence().update(imtdPsDetails);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static ImtdPsDetails update(ImtdPsDetails imtdPsDetails,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(imtdPsDetails, serviceContext);
    }

    /**
    * Caches the imtd ps details in the entity cache if it is enabled.
    *
    * @param imtdPsDetails the imtd ps details
    */
    public static void cacheResult(
        com.stpl.app.model.ImtdPsDetails imtdPsDetails) {
        getPersistence().cacheResult(imtdPsDetails);
    }

    /**
    * Caches the imtd ps detailses in the entity cache if it is enabled.
    *
    * @param imtdPsDetailses the imtd ps detailses
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.ImtdPsDetails> imtdPsDetailses) {
        getPersistence().cacheResult(imtdPsDetailses);
    }

    /**
    * Creates a new imtd ps details with the primary key. Does not add the imtd ps details to the database.
    *
    * @param imtdPsDetailsSid the primary key for the new imtd ps details
    * @return the new imtd ps details
    */
    public static com.stpl.app.model.ImtdPsDetails create(int imtdPsDetailsSid) {
        return getPersistence().create(imtdPsDetailsSid);
    }

    /**
    * Removes the imtd ps details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param imtdPsDetailsSid the primary key of the imtd ps details
    * @return the imtd ps details that was removed
    * @throws com.stpl.app.NoSuchImtdPsDetailsException if a imtd ps details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdPsDetails remove(int imtdPsDetailsSid)
        throws com.stpl.app.NoSuchImtdPsDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(imtdPsDetailsSid);
    }

    public static com.stpl.app.model.ImtdPsDetails updateImpl(
        com.stpl.app.model.ImtdPsDetails imtdPsDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(imtdPsDetails);
    }

    /**
    * Returns the imtd ps details with the primary key or throws a {@link com.stpl.app.NoSuchImtdPsDetailsException} if it could not be found.
    *
    * @param imtdPsDetailsSid the primary key of the imtd ps details
    * @return the imtd ps details
    * @throws com.stpl.app.NoSuchImtdPsDetailsException if a imtd ps details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdPsDetails findByPrimaryKey(
        int imtdPsDetailsSid)
        throws com.stpl.app.NoSuchImtdPsDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(imtdPsDetailsSid);
    }

    /**
    * Returns the imtd ps details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param imtdPsDetailsSid the primary key of the imtd ps details
    * @return the imtd ps details, or <code>null</code> if a imtd ps details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdPsDetails fetchByPrimaryKey(
        int imtdPsDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(imtdPsDetailsSid);
    }

    /**
    * Returns all the imtd ps detailses.
    *
    * @return the imtd ps detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ImtdPsDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the imtd ps detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdPsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of imtd ps detailses
    * @param end the upper bound of the range of imtd ps detailses (not inclusive)
    * @return the range of imtd ps detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ImtdPsDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the imtd ps detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdPsDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of imtd ps detailses
    * @param end the upper bound of the range of imtd ps detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of imtd ps detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ImtdPsDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the imtd ps detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of imtd ps detailses.
    *
    * @return the number of imtd ps detailses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ImtdPsDetailsPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ImtdPsDetailsPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    ImtdPsDetailsPersistence.class.getName());

            ReferenceRegistry.registerReference(ImtdPsDetailsUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(ImtdPsDetailsPersistence persistence) {
    }
}
