package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.AcBrMethodologyDetails;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the ac br methodology details service. This utility wraps {@link AcBrMethodologyDetailsPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see AcBrMethodologyDetailsPersistence
 * @see AcBrMethodologyDetailsPersistenceImpl
 * @generated
 */
public class AcBrMethodologyDetailsUtil {
    private static AcBrMethodologyDetailsPersistence _persistence;

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
    public static void clearCache(AcBrMethodologyDetails acBrMethodologyDetails) {
        getPersistence().clearCache(acBrMethodologyDetails);
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
    public static List<AcBrMethodologyDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<AcBrMethodologyDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<AcBrMethodologyDetails> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static AcBrMethodologyDetails update(
        AcBrMethodologyDetails acBrMethodologyDetails)
        throws SystemException {
        return getPersistence().update(acBrMethodologyDetails);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static AcBrMethodologyDetails update(
        AcBrMethodologyDetails acBrMethodologyDetails,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(acBrMethodologyDetails, serviceContext);
    }

    /**
    * Caches the ac br methodology details in the entity cache if it is enabled.
    *
    * @param acBrMethodologyDetails the ac br methodology details
    */
    public static void cacheResult(
        com.stpl.app.parttwo.model.AcBrMethodologyDetails acBrMethodologyDetails) {
        getPersistence().cacheResult(acBrMethodologyDetails);
    }

    /**
    * Caches the ac br methodology detailses in the entity cache if it is enabled.
    *
    * @param acBrMethodologyDetailses the ac br methodology detailses
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.AcBrMethodologyDetails> acBrMethodologyDetailses) {
        getPersistence().cacheResult(acBrMethodologyDetailses);
    }

    /**
    * Creates a new ac br methodology details with the primary key. Does not add the ac br methodology details to the database.
    *
    * @param acBrMethodologyDetailsSid the primary key for the new ac br methodology details
    * @return the new ac br methodology details
    */
    public static com.stpl.app.parttwo.model.AcBrMethodologyDetails create(
        int acBrMethodologyDetailsSid) {
        return getPersistence().create(acBrMethodologyDetailsSid);
    }

    /**
    * Removes the ac br methodology details with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param acBrMethodologyDetailsSid the primary key of the ac br methodology details
    * @return the ac br methodology details that was removed
    * @throws com.stpl.app.parttwo.NoSuchAcBrMethodologyDetailsException if a ac br methodology details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.AcBrMethodologyDetails remove(
        int acBrMethodologyDetailsSid)
        throws com.stpl.app.parttwo.NoSuchAcBrMethodologyDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(acBrMethodologyDetailsSid);
    }

    public static com.stpl.app.parttwo.model.AcBrMethodologyDetails updateImpl(
        com.stpl.app.parttwo.model.AcBrMethodologyDetails acBrMethodologyDetails)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(acBrMethodologyDetails);
    }

    /**
    * Returns the ac br methodology details with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchAcBrMethodologyDetailsException} if it could not be found.
    *
    * @param acBrMethodologyDetailsSid the primary key of the ac br methodology details
    * @return the ac br methodology details
    * @throws com.stpl.app.parttwo.NoSuchAcBrMethodologyDetailsException if a ac br methodology details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.AcBrMethodologyDetails findByPrimaryKey(
        int acBrMethodologyDetailsSid)
        throws com.stpl.app.parttwo.NoSuchAcBrMethodologyDetailsException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(acBrMethodologyDetailsSid);
    }

    /**
    * Returns the ac br methodology details with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param acBrMethodologyDetailsSid the primary key of the ac br methodology details
    * @return the ac br methodology details, or <code>null</code> if a ac br methodology details with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.AcBrMethodologyDetails fetchByPrimaryKey(
        int acBrMethodologyDetailsSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(acBrMethodologyDetailsSid);
    }

    /**
    * Returns all the ac br methodology detailses.
    *
    * @return the ac br methodology detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.AcBrMethodologyDetails> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the ac br methodology detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AcBrMethodologyDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ac br methodology detailses
    * @param end the upper bound of the range of ac br methodology detailses (not inclusive)
    * @return the range of ac br methodology detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.AcBrMethodologyDetails> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the ac br methodology detailses.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AcBrMethodologyDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ac br methodology detailses
    * @param end the upper bound of the range of ac br methodology detailses (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ac br methodology detailses
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.AcBrMethodologyDetails> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the ac br methodology detailses from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of ac br methodology detailses.
    *
    * @return the number of ac br methodology detailses
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static AcBrMethodologyDetailsPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (AcBrMethodologyDetailsPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.parttwo.service.ClpSerializer.getServletContextName(),
                    AcBrMethodologyDetailsPersistence.class.getName());

            ReferenceRegistry.registerReference(AcBrMethodologyDetailsUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(AcBrMethodologyDetailsPersistence persistence) {
    }
}
