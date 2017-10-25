package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.SlaCalendarDetails;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the sla calendar details service. This utility wraps {@link SlaCalendarDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see SlaCalendarDetailsPersistence
 * @see SlaCalendarDetailsPersistenceImpl
 * @generated
 */
public class SlaCalendarDetailsUtil {
    private static SlaCalendarDetailsPersistence _persistence;

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
    public static void clearCache(SlaCalendarDetails slaCalendarDetails) {
        getPersistence().clearCache(slaCalendarDetails);
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
    public static List<SlaCalendarDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<SlaCalendarDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<SlaCalendarDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static SlaCalendarDetails update(
        SlaCalendarDetails slaCalendarDetails) throws SystemException {
        return getPersistence().update(slaCalendarDetails);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static SlaCalendarDetails update(
        SlaCalendarDetails slaCalendarDetails, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(slaCalendarDetails, serviceContext);
    }

    /**
    * Caches the sla calendar details in the entity cache if it is enabled.
    *
    * @param slaCalendarDetails the sla calendar details
    */
    public static void cacheResult(
        com.stpl.app.parttwo.model.SlaCalendarDetails slaCalendarDetails) {
        getPersistence().cacheResult(slaCalendarDetails);
    }

    /**
    * Caches the sla calendar detailses in the entity cache if it is enabled.
    *
    * @param slaCalendarDetailses the sla calendar detailses
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.SlaCalendarDetails> slaCalendarDetailses) {
        getPersistence().cacheResult(slaCalendarDetailses);
    }

    /**
    * Creates a new sla calendar details with the primary key. Does not add the sla calendar details to the database.
    *
    * @param slaCalendarDetailsSid the primary key for the new sla calendar details
    * @return the new sla calendar details
    */
    public static com.stpl.app.parttwo.model.SlaCalendarDetails create(
        int slaCalendarDetailsSid) {
        return getPersistence().create(slaCalendarDetailsSid);
    }

    /**
    * Removes the sla calendar details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param slaCalendarDetailsSid the primary key of the sla calendar details
    * @return the sla calendar details that was removed
    * @throws com.stpl.app.parttwo.NoSuchSlaCalendarDetailsException if a sla calendar details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.SlaCalendarDetails remove(
        int slaCalendarDetailsSid)
        throws com.stpl.app.parttwo.NoSuchSlaCalendarDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(slaCalendarDetailsSid);
    }

    public static com.stpl.app.parttwo.model.SlaCalendarDetails updateImpl(
        com.stpl.app.parttwo.model.SlaCalendarDetails slaCalendarDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(slaCalendarDetails);
    }

    /**
    * Returns the sla calendar details with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchSlaCalendarDetailsException} if it could not be found.
    *
    * @param slaCalendarDetailsSid the primary key of the sla calendar details
    * @return the sla calendar details
    * @throws com.stpl.app.parttwo.NoSuchSlaCalendarDetailsException if a sla calendar details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.SlaCalendarDetails findByPrimaryKey(
        int slaCalendarDetailsSid)
        throws com.stpl.app.parttwo.NoSuchSlaCalendarDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(slaCalendarDetailsSid);
    }

    /**
    * Returns the sla calendar details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param slaCalendarDetailsSid the primary key of the sla calendar details
    * @return the sla calendar details, or <code>null</code> if a sla calendar details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.SlaCalendarDetails fetchByPrimaryKey(
        int slaCalendarDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(slaCalendarDetailsSid);
    }

    /**
    * Returns all the sla calendar detailses.
    *
    * @return the sla calendar detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.SlaCalendarDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the sla calendar detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.SlaCalendarDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of sla calendar detailses
    * @param end the upper bound of the range of sla calendar detailses (not inclusive)
    * @return the range of sla calendar detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.SlaCalendarDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the sla calendar detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.SlaCalendarDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of sla calendar detailses
    * @param end the upper bound of the range of sla calendar detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of sla calendar detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.SlaCalendarDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the sla calendar detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of sla calendar detailses.
    *
    * @return the number of sla calendar detailses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static SlaCalendarDetailsPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (SlaCalendarDetailsPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.parttwo.service.ClpSerializer.getServletContextName(),
                    SlaCalendarDetailsPersistence.class.getName());

            ReferenceRegistry.registerReference(SlaCalendarDetailsUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(SlaCalendarDetailsPersistence persistence) {
    }
}
