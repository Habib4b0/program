package com.stpl.app.service.persistence;

import com.stpl.app.model.NmProjectionSelection;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the nm projection selection service. This utility wraps {@link NmProjectionSelectionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NmProjectionSelectionPersistence
 * @see NmProjectionSelectionPersistenceImpl
 * @generated
 */
public class NmProjectionSelectionUtil {
    private static NmProjectionSelectionPersistence _persistence;

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
    public static void clearCache(NmProjectionSelection nmProjectionSelection) {
        getPersistence().clearCache(nmProjectionSelection);
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
    public static List<NmProjectionSelection> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<NmProjectionSelection> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<NmProjectionSelection> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static NmProjectionSelection update(
        NmProjectionSelection nmProjectionSelection) throws SystemException {
        return getPersistence().update(nmProjectionSelection);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static NmProjectionSelection update(
        NmProjectionSelection nmProjectionSelection,
        ServiceContext serviceContext) throws SystemException {
        return getPersistence().update(nmProjectionSelection, serviceContext);
    }

    /**
    * Caches the nm projection selection in the entity cache if it is enabled.
    *
    * @param nmProjectionSelection the nm projection selection
    */
    public static void cacheResult(
        com.stpl.app.model.NmProjectionSelection nmProjectionSelection) {
        getPersistence().cacheResult(nmProjectionSelection);
    }

    /**
    * Caches the nm projection selections in the entity cache if it is enabled.
    *
    * @param nmProjectionSelections the nm projection selections
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.NmProjectionSelection> nmProjectionSelections) {
        getPersistence().cacheResult(nmProjectionSelections);
    }

    /**
    * Creates a new nm projection selection with the primary key. Does not add the nm projection selection to the database.
    *
    * @param nmProjectionSelectionSid the primary key for the new nm projection selection
    * @return the new nm projection selection
    */
    public static com.stpl.app.model.NmProjectionSelection create(
        int nmProjectionSelectionSid) {
        return getPersistence().create(nmProjectionSelectionSid);
    }

    /**
    * Removes the nm projection selection with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param nmProjectionSelectionSid the primary key of the nm projection selection
    * @return the nm projection selection that was removed
    * @throws com.stpl.app.NoSuchNmProjectionSelectionException if a nm projection selection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.NmProjectionSelection remove(
        int nmProjectionSelectionSid)
        throws com.stpl.app.NoSuchNmProjectionSelectionException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(nmProjectionSelectionSid);
    }

    public static com.stpl.app.model.NmProjectionSelection updateImpl(
        com.stpl.app.model.NmProjectionSelection nmProjectionSelection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(nmProjectionSelection);
    }

    /**
    * Returns the nm projection selection with the primary key or throws a {@link com.stpl.app.NoSuchNmProjectionSelectionException} if it could not be found.
    *
    * @param nmProjectionSelectionSid the primary key of the nm projection selection
    * @return the nm projection selection
    * @throws com.stpl.app.NoSuchNmProjectionSelectionException if a nm projection selection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.NmProjectionSelection findByPrimaryKey(
        int nmProjectionSelectionSid)
        throws com.stpl.app.NoSuchNmProjectionSelectionException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(nmProjectionSelectionSid);
    }

    /**
    * Returns the nm projection selection with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param nmProjectionSelectionSid the primary key of the nm projection selection
    * @return the nm projection selection, or <code>null</code> if a nm projection selection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.NmProjectionSelection fetchByPrimaryKey(
        int nmProjectionSelectionSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(nmProjectionSelectionSid);
    }

    /**
    * Returns all the nm projection selections.
    *
    * @return the nm projection selections
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.NmProjectionSelection> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the nm projection selections.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of nm projection selections
    * @param end the upper bound of the range of nm projection selections (not inclusive)
    * @return the range of nm projection selections
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.NmProjectionSelection> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the nm projection selections.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of nm projection selections
    * @param end the upper bound of the range of nm projection selections (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of nm projection selections
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.NmProjectionSelection> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the nm projection selections from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of nm projection selections.
    *
    * @return the number of nm projection selections
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static NmProjectionSelectionPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (NmProjectionSelectionPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    NmProjectionSelectionPersistence.class.getName());

            ReferenceRegistry.registerReference(NmProjectionSelectionUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(NmProjectionSelectionPersistence persistence) {
    }
}
