package com.stpl.app.service.persistence;

import com.stpl.app.model.MProjectionSelection;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.exception.SystemException;
import com.stpl.portal.kernel.util.OrderByComparator;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.ServiceContext;

import java.util.List;

/**
 * The persistence utility for the m projection selection service. This utility wraps {@link MProjectionSelectionPersistenceImpl} and provides direct access to the database for CRUD operations. This utility should only be used by the service layer, as it must operate within a transaction. Never access this utility in a JSP, controller, model, or other front-end class.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see MProjectionSelectionPersistence
 * @see MProjectionSelectionPersistenceImpl
 * @generated
 */
public class MProjectionSelectionUtil {
    private static MProjectionSelectionPersistence _persistence;

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
    public static void clearCache(MProjectionSelection mProjectionSelection) {
        getPersistence().clearCache(mProjectionSelection);
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
    public static List<MProjectionSelection> findWithDynamicQuery(
        DynamicQuery dynamicQuery) throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int)
     */
    public static List<MProjectionSelection> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end)
        throws SystemException {
        return getPersistence().findWithDynamicQuery(dynamicQuery, start, end);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#findWithDynamicQuery(DynamicQuery, int, int, OrderByComparator)
     */
    public static List<MProjectionSelection> findWithDynamicQuery(
        DynamicQuery dynamicQuery, int start, int end,
        OrderByComparator orderByComparator) throws SystemException {
        return getPersistence()
                   .findWithDynamicQuery(dynamicQuery, start, end,
            orderByComparator);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel)
     */
    public static MProjectionSelection update(
        MProjectionSelection mProjectionSelection) throws SystemException {
        return getPersistence().update(mProjectionSelection);
    }

    /**
     * @see com.stpl.portal.service.persistence.BasePersistence#update(com.stpl.portal.model.BaseModel, ServiceContext)
     */
    public static MProjectionSelection update(
        MProjectionSelection mProjectionSelection, ServiceContext serviceContext)
        throws SystemException {
        return getPersistence().update(mProjectionSelection, serviceContext);
    }

    /**
    * Caches the m projection selection in the entity cache if it is enabled.
    *
    * @param mProjectionSelection the m projection selection
    */
    public static void cacheResult(
        com.stpl.app.model.MProjectionSelection mProjectionSelection) {
        getPersistence().cacheResult(mProjectionSelection);
    }

    /**
    * Caches the m projection selections in the entity cache if it is enabled.
    *
    * @param mProjectionSelections the m projection selections
    */
    public static void cacheResult(
        java.util.List<com.stpl.app.model.MProjectionSelection> mProjectionSelections) {
        getPersistence().cacheResult(mProjectionSelections);
    }

    /**
    * Creates a new m projection selection with the primary key. Does not add the m projection selection to the database.
    *
    * @param mProjectionSelectionSid the primary key for the new m projection selection
    * @return the new m projection selection
    */
    public static com.stpl.app.model.MProjectionSelection create(
        int mProjectionSelectionSid) {
        return getPersistence().create(mProjectionSelectionSid);
    }

    /**
    * Removes the m projection selection with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param mProjectionSelectionSid the primary key of the m projection selection
    * @return the m projection selection that was removed
    * @throws com.stpl.app.NoSuchMProjectionSelectionException if a m projection selection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MProjectionSelection remove(
        int mProjectionSelectionSid)
        throws com.stpl.app.NoSuchMProjectionSelectionException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().remove(mProjectionSelectionSid);
    }

    public static com.stpl.app.model.MProjectionSelection updateImpl(
        com.stpl.app.model.MProjectionSelection mProjectionSelection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().updateImpl(mProjectionSelection);
    }

    /**
    * Returns the m projection selection with the primary key or throws a {@link com.stpl.app.NoSuchMProjectionSelectionException} if it could not be found.
    *
    * @param mProjectionSelectionSid the primary key of the m projection selection
    * @return the m projection selection
    * @throws com.stpl.app.NoSuchMProjectionSelectionException if a m projection selection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MProjectionSelection findByPrimaryKey(
        int mProjectionSelectionSid)
        throws com.stpl.app.NoSuchMProjectionSelectionException,
            com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findByPrimaryKey(mProjectionSelectionSid);
    }

    /**
    * Returns the m projection selection with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param mProjectionSelectionSid the primary key of the m projection selection
    * @return the m projection selection, or <code>null</code> if a m projection selection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MProjectionSelection fetchByPrimaryKey(
        int mProjectionSelectionSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().fetchByPrimaryKey(mProjectionSelectionSid);
    }

    /**
    * Returns all the m projection selections.
    *
    * @return the m projection selections
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.MProjectionSelection> findAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll();
    }

    /**
    * Returns a range of all the m projection selections.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of m projection selections
    * @param end the upper bound of the range of m projection selections (not inclusive)
    * @return the range of m projection selections
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.MProjectionSelection> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end);
    }

    /**
    * Returns an ordered range of all the m projection selections.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of m projection selections
    * @param end the upper bound of the range of m projection selections (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of m projection selections
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.MProjectionSelection> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().findAll(start, end, orderByComparator);
    }

    /**
    * Removes all the m projection selections from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public static void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        getPersistence().removeAll();
    }

    /**
    * Returns the number of m projection selections.
    *
    * @return the number of m projection selections
    * @throws SystemException if a system exception occurred
    */
    public static int countAll()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getPersistence().countAll();
    }

    public static MProjectionSelectionPersistence getPersistence() {
        if (_persistence == null) {
            _persistence = (MProjectionSelectionPersistence) PortletBeanLocatorUtil.locate(com.stpl.app.service.ClpSerializer.getServletContextName(),
                    MProjectionSelectionPersistence.class.getName());

            ReferenceRegistry.registerReference(MProjectionSelectionUtil.class,
                "_persistence");
        }

        return _persistence;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setPersistence(MProjectionSelectionPersistence persistence) {
    }
}
