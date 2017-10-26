package com.stpl.app.service.persistence;

import com.stpl.app.model.DeductionCalendarDetails;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the deduction calendar details service. This utility wraps {@link DeductionCalendarDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see DeductionCalendarDetailsPersistence
 * @see DeductionCalendarDetailsPersistenceImpl
 * @generated
 */
public class DeductionCalendarDetailsUtil {
    private static DeductionCalendarDetailsPersistence _persistence;

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
        DeductionCalendarDetails deductionCalendarDetails) {
        getPersistence().clearCache(deductionCalendarDetails);
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
    public static List<DeductionCalendarDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<DeductionCalendarDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<DeductionCalendarDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static DeductionCalendarDetails update(
        DeductionCalendarDetails deductionCalendarDetails)
        throws SystemException {
        return getPersistence().update(deductionCalendarDetails);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static DeductionCalendarDetails update(
        DeductionCalendarDetails deductionCalendarDetails,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(deductionCalendarDetails, serviceContext);
    }

    /**
    * Caches the deduction calendar details in the entity cache if it is enabled.
    *
    * @param deductionCalendarDetails the deduction calendar details
    */
    public static void cacheResult(
        com.stpl.app.model.DeductionCalendarDetails deductionCalendarDetails) {
        getPersistence().cacheResult(deductionCalendarDetails);
    }

    /**
    * Caches the deduction calendar detailses in the entity cache if it is enabled.
    *
    * @param deductionCalendarDetailses the deduction calendar detailses
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.DeductionCalendarDetails> deductionCalendarDetailses) {
        getPersistence().cacheResult(deductionCalendarDetailses);
    }

    /**
    * Creates a new deduction calendar details with the primary key. Does not add the deduction calendar details to the database.
    *
    * @param deductionCalendarDetailsPK the primary key for the new deduction calendar details
    * @return the new deduction calendar details
    */
    public static com.stpl.app.model.DeductionCalendarDetails create(
        DeductionCalendarDetailsPK deductionCalendarDetailsPK) {
        return getPersistence().create(deductionCalendarDetailsPK);
    }

    /**
    * Removes the deduction calendar details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param deductionCalendarDetailsPK the primary key of the deduction calendar details
    * @return the deduction calendar details that was removed
    * @throws com.stpl.app.NoSuchDeductionCalendarDetailsException if a deduction calendar details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.DeductionCalendarDetails remove(
        DeductionCalendarDetailsPK deductionCalendarDetailsPK)
        throws com.stpl.app.NoSuchDeductionCalendarDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(deductionCalendarDetailsPK);
    }

    public static com.stpl.app.model.DeductionCalendarDetails updateImpl(
        com.stpl.app.model.DeductionCalendarDetails deductionCalendarDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(deductionCalendarDetails);
    }

    /**
    * Returns the deduction calendar details with the primary key or throws a {@link com.stpl.app.NoSuchDeductionCalendarDetailsException} if it could not be found.
    *
    * @param deductionCalendarDetailsPK the primary key of the deduction calendar details
    * @return the deduction calendar details
    * @throws com.stpl.app.NoSuchDeductionCalendarDetailsException if a deduction calendar details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.DeductionCalendarDetails findByPrimaryKey(
        DeductionCalendarDetailsPK deductionCalendarDetailsPK)
        throws com.stpl.app.NoSuchDeductionCalendarDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(deductionCalendarDetailsPK);
    }

    /**
    * Returns the deduction calendar details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param deductionCalendarDetailsPK the primary key of the deduction calendar details
    * @return the deduction calendar details, or <code>null</code> if a deduction calendar details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.DeductionCalendarDetails fetchByPrimaryKey(
        DeductionCalendarDetailsPK deductionCalendarDetailsPK)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(deductionCalendarDetailsPK);
    }

    /**
    * Returns all the deduction calendar detailses.
    *
    * @return the deduction calendar detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.DeductionCalendarDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the deduction calendar detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DeductionCalendarDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of deduction calendar detailses
    * @param end the upper bound of the range of deduction calendar detailses (not inclusive)
    * @return the range of deduction calendar detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.DeductionCalendarDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the deduction calendar detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.DeductionCalendarDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of deduction calendar detailses
    * @param end the upper bound of the range of deduction calendar detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of deduction calendar detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.DeductionCalendarDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the deduction calendar detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of deduction calendar detailses.
    *
    * @return the number of deduction calendar detailses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static DeductionCalendarDetailsPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (DeductionCalendarDetailsPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    DeductionCalendarDetailsPersistence.class.getName());

            ReferenceRegistry.registerReference(DeductionCalendarDetailsUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(DeductionCalendarDetailsPersistence persistence) {
    }
}
