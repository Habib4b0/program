package com.stpl.app.parttwo.service.persistence;

import com.stpl.app.parttwo.model.AcFdAdjustmentSelection;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the ac fd adjustment selection service. This utility wraps {@link AcFdAdjustmentSelectionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see AcFdAdjustmentSelectionPersistence
 * @see AcFdAdjustmentSelectionPersistenceImpl
 * @generated
 */
public class AcFdAdjustmentSelectionUtil {
    private static AcFdAdjustmentSelectionPersistence _persistence;

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
        AcFdAdjustmentSelection acFdAdjustmentSelection) {
        getPersistence().clearCache(acFdAdjustmentSelection);
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
    public static List<AcFdAdjustmentSelection> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<AcFdAdjustmentSelection> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<AcFdAdjustmentSelection> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static AcFdAdjustmentSelection update(
        AcFdAdjustmentSelection acFdAdjustmentSelection)
        throws SystemException {
        return getPersistence().update(acFdAdjustmentSelection);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static AcFdAdjustmentSelection update(
        AcFdAdjustmentSelection acFdAdjustmentSelection,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(acFdAdjustmentSelection, serviceContext);
    }

    /**
    * Caches the ac fd adjustment selection in the entity cache if it is enabled.
    *
    * @param acFdAdjustmentSelection the ac fd adjustment selection
    */
    public static void cacheResult(
        com.stpl.app.parttwo.model.AcFdAdjustmentSelection acFdAdjustmentSelection) {
        getPersistence().cacheResult(acFdAdjustmentSelection);
    }

    /**
    * Caches the ac fd adjustment selections in the entity cache if it is enabled.
    *
    * @param acFdAdjustmentSelections the ac fd adjustment selections
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.parttwo.model.AcFdAdjustmentSelection> acFdAdjustmentSelections) {
        getPersistence().cacheResult(acFdAdjustmentSelections);
    }

    /**
    * Creates a new ac fd adjustment selection with the primary key. Does not add the ac fd adjustment selection to the database.
    *
    * @param accClosureMasterSid the primary key for the new ac fd adjustment selection
    * @return the new ac fd adjustment selection
    */
    public static com.stpl.app.parttwo.model.AcFdAdjustmentSelection create(
        int accClosureMasterSid) {
        return getPersistence().create(accClosureMasterSid);
    }

    /**
    * Removes the ac fd adjustment selection with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param accClosureMasterSid the primary key of the ac fd adjustment selection
    * @return the ac fd adjustment selection that was removed
    * @throws com.stpl.app.parttwo.NoSuchAcFdAdjustmentSelectionException if a ac fd adjustment selection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.AcFdAdjustmentSelection remove(
        int accClosureMasterSid)
        throws com.stpl.app.parttwo.NoSuchAcFdAdjustmentSelectionException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(accClosureMasterSid);
    }

    public static com.stpl.app.parttwo.model.AcFdAdjustmentSelection updateImpl(
        com.stpl.app.parttwo.model.AcFdAdjustmentSelection acFdAdjustmentSelection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(acFdAdjustmentSelection);
    }

    /**
    * Returns the ac fd adjustment selection with the primary key or throws a {@link com.stpl.app.parttwo.NoSuchAcFdAdjustmentSelectionException} if it could not be found.
    *
    * @param accClosureMasterSid the primary key of the ac fd adjustment selection
    * @return the ac fd adjustment selection
    * @throws com.stpl.app.parttwo.NoSuchAcFdAdjustmentSelectionException if a ac fd adjustment selection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.AcFdAdjustmentSelection findByPrimaryKey(
        int accClosureMasterSid)
        throws com.stpl.app.parttwo.NoSuchAcFdAdjustmentSelectionException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(accClosureMasterSid);
    }

    /**
    * Returns the ac fd adjustment selection with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param accClosureMasterSid the primary key of the ac fd adjustment selection
    * @return the ac fd adjustment selection, or <code>null</code> if a ac fd adjustment selection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.parttwo.model.AcFdAdjustmentSelection fetchByPrimaryKey(
        int accClosureMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(accClosureMasterSid);
    }

    /**
    * Returns all the ac fd adjustment selections.
    *
    * @return the ac fd adjustment selections
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.AcFdAdjustmentSelection> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the ac fd adjustment selections.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AcFdAdjustmentSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ac fd adjustment selections
    * @param end the upper bound of the range of ac fd adjustment selections (not inclusive)
    * @return the range of ac fd adjustment selections
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.AcFdAdjustmentSelection> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the ac fd adjustment selections.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.parttwo.model.impl.AcFdAdjustmentSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of ac fd adjustment selections
    * @param end the upper bound of the range of ac fd adjustment selections (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of ac fd adjustment selections
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.parttwo.model.AcFdAdjustmentSelection> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the ac fd adjustment selections from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of ac fd adjustment selections.
    *
    * @return the number of ac fd adjustment selections
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static AcFdAdjustmentSelectionPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (AcFdAdjustmentSelectionPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.parttwo.service.ClpSerializer.getServletContextName(),
                    AcFdAdjustmentSelectionPersistence.class.getName());

            ReferenceRegistry.registerReference(AcFdAdjustmentSelectionUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(AcFdAdjustmentSelectionPersistence persistence) {
    }
}
