package com.stpl.app.service.persistence;

import com.stpl.app.model.ImtdDeductionDetails;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the imtd deduction details service. This utility wraps {@link ImtdDeductionDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ImtdDeductionDetailsPersistence
 * @see ImtdDeductionDetailsPersistenceImpl
 * @generated
 */
public class ImtdDeductionDetailsUtil {
    private static ImtdDeductionDetailsPersistence _persistence;

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
    public static void clearCache(ImtdDeductionDetails imtdDeductionDetails) {
        getPersistence().clearCache(imtdDeductionDetails);
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
    public static List<ImtdDeductionDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<ImtdDeductionDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<ImtdDeductionDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static ImtdDeductionDetails update(
        ImtdDeductionDetails imtdDeductionDetails) throws SystemException {
        return getPersistence().update(imtdDeductionDetails);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static ImtdDeductionDetails update(
        ImtdDeductionDetails imtdDeductionDetails, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(imtdDeductionDetails, serviceContext);
    }

    /**
    * Caches the imtd deduction details in the entity cache if it is enabled.
    *
    * @param imtdDeductionDetails the imtd deduction details
    */
    public static void cacheResult(
        com.stpl.app.model.ImtdDeductionDetails imtdDeductionDetails) {
        getPersistence().cacheResult(imtdDeductionDetails);
    }

    /**
    * Caches the imtd deduction detailses in the entity cache if it is enabled.
    *
    * @param imtdDeductionDetailses the imtd deduction detailses
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.ImtdDeductionDetails> imtdDeductionDetailses) {
        getPersistence().cacheResult(imtdDeductionDetailses);
    }

    /**
    * Creates a new imtd deduction details with the primary key. Does not add the imtd deduction details to the database.
    *
    * @param imtdDeductionDetailsSid the primary key for the new imtd deduction details
    * @return the new imtd deduction details
    */
    public static com.stpl.app.model.ImtdDeductionDetails create(
        int imtdDeductionDetailsSid) {
        return getPersistence().create(imtdDeductionDetailsSid);
    }

    /**
    * Removes the imtd deduction details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param imtdDeductionDetailsSid the primary key of the imtd deduction details
    * @return the imtd deduction details that was removed
    * @throws com.stpl.app.NoSuchImtdDeductionDetailsException if a imtd deduction details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdDeductionDetails remove(
        int imtdDeductionDetailsSid)
        throws com.stpl.app.NoSuchImtdDeductionDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(imtdDeductionDetailsSid);
    }

    public static com.stpl.app.model.ImtdDeductionDetails updateImpl(
        com.stpl.app.model.ImtdDeductionDetails imtdDeductionDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(imtdDeductionDetails);
    }

    /**
    * Returns the imtd deduction details with the primary key or throws a {@link com.stpl.app.NoSuchImtdDeductionDetailsException} if it could not be found.
    *
    * @param imtdDeductionDetailsSid the primary key of the imtd deduction details
    * @return the imtd deduction details
    * @throws com.stpl.app.NoSuchImtdDeductionDetailsException if a imtd deduction details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdDeductionDetails findByPrimaryKey(
        int imtdDeductionDetailsSid)
        throws com.stpl.app.NoSuchImtdDeductionDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(imtdDeductionDetailsSid);
    }

    /**
    * Returns the imtd deduction details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param imtdDeductionDetailsSid the primary key of the imtd deduction details
    * @return the imtd deduction details, or <code>null</code> if a imtd deduction details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ImtdDeductionDetails fetchByPrimaryKey(
        int imtdDeductionDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(imtdDeductionDetailsSid);
    }

    /**
    * Returns all the imtd deduction detailses.
    *
    * @return the imtd deduction detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ImtdDeductionDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the imtd deduction detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdDeductionDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of imtd deduction detailses
    * @param end the upper bound of the range of imtd deduction detailses (not inclusive)
    * @return the range of imtd deduction detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ImtdDeductionDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the imtd deduction detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ImtdDeductionDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of imtd deduction detailses
    * @param end the upper bound of the range of imtd deduction detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of imtd deduction detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ImtdDeductionDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the imtd deduction detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of imtd deduction detailses.
    *
    * @return the number of imtd deduction detailses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static ImtdDeductionDetailsPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (ImtdDeductionDetailsPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    ImtdDeductionDetailsPersistence.class.getName());

            ReferenceRegistry.registerReference(ImtdDeductionDetailsUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(ImtdDeductionDetailsPersistence persistence) {
    }
}
