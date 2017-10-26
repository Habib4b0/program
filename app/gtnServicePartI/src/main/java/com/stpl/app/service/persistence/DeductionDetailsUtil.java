package com.stpl.app.service.persistence;

import com.stpl.app.model.DeductionDetails;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the deduction details service. This utility wraps {@link DeductionDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see DeductionDetailsPersistence
 * @see DeductionDetailsPersistenceImpl
 * @generated
 */
public class DeductionDetailsUtil {
    private static DeductionDetailsPersistence _persistence;

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
    public static void clearCache(DeductionDetails deductionDetails) {
        getPersistence().clearCache(deductionDetails);
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
    public static List<DeductionDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<DeductionDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<DeductionDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static DeductionDetails update(DeductionDetails deductionDetails)
        throws SystemException {
        return getPersistence().update(deductionDetails);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static DeductionDetails update(DeductionDetails deductionDetails,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(deductionDetails, serviceContext);
    }

    /**
    * Caches the deduction details in the entity cache if it is enabled.
    *
    * @param deductionDetails the deduction details
    */
    public static void cacheResult(
        com.stpl.app.model.DeductionDetails deductionDetails) {
        getPersistence().cacheResult(deductionDetails);
    }

    /**
    * Caches the deduction detailses in the entity cache if it is enabled.
    *
    * @param deductionDetailses the deduction detailses
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.DeductionDetails> deductionDetailses) {
        getPersistence().cacheResult(deductionDetailses);
    }

    /**
    * Creates a new deduction details with the primary key. Does not add the deduction details to the database.
    *
    * @param deductionDetailsSid the primary key for the new deduction details
    * @return the new deduction details
    */
    public static com.stpl.app.model.DeductionDetails create(
        int deductionDetailsSid) {
        return getPersistence().create(deductionDetailsSid);
    }

    /**
    * Removes the deduction details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param deductionDetailsSid the primary key of the deduction details
    * @return the deduction details that was removed
    * @throws com.stpl.app.NoSuchDeductionDetailsException if a deduction details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.DeductionDetails remove(
        int deductionDetailsSid)
        throws com.stpl.app.NoSuchDeductionDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(deductionDetailsSid);
    }

    public static com.stpl.app.model.DeductionDetails updateImpl(
        com.stpl.app.model.DeductionDetails deductionDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(deductionDetails);
    }

    /**
    * Returns the deduction details with the primary key or throws a {@link com.stpl.app.NoSuchDeductionDetailsException} if it could not be found.
    *
    * @param deductionDetailsSid the primary key of the deduction details
    * @return the deduction details
    * @throws com.stpl.app.NoSuchDeductionDetailsException if a deduction details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.DeductionDetails findByPrimaryKey(
        int deductionDetailsSid)
        throws com.stpl.app.NoSuchDeductionDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(deductionDetailsSid);
    }

    /**
    * Returns the deduction details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param deductionDetailsSid the primary key of the deduction details
    * @return the deduction details, or <code>null</code> if a deduction details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.DeductionDetails fetchByPrimaryKey(
        int deductionDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(deductionDetailsSid);
    }

    /**
    * Returns all the deduction detailses.
    *
    * @return the deduction detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.DeductionDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the deduction detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DeductionDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of deduction detailses
    * @param end the upper bound of the range of deduction detailses (not inclusive)
    * @return the range of deduction detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.DeductionDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the deduction detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DeductionDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of deduction detailses
    * @param end the upper bound of the range of deduction detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of deduction detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.DeductionDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the deduction detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of deduction detailses.
    *
    * @return the number of deduction detailses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static DeductionDetailsPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (DeductionDetailsPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    DeductionDetailsPersistence.class.getName());

            ReferenceRegistry.registerReference(DeductionDetailsUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(DeductionDetailsPersistence persistence) {
    }
}
