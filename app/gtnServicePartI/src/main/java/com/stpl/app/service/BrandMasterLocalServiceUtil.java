package com.stpl.app.service;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.InvokableLocalService;
import org.jboss.logging.Logger;

/**
 * Provides the local service utility for BrandMaster. This utility wraps
 * {@link com.stpl.app.service.impl.BrandMasterLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see BrandMasterLocalService
 * @see com.stpl.app.service.base.BrandMasterLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.BrandMasterLocalServiceImpl
 * @generated
 */
public class BrandMasterLocalServiceUtil {
    private static BrandMasterLocalService _service;
    private static final Logger LOGGER = Logger.getLogger(BrandMasterLocalServiceUtil.class);

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.BrandMasterLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the brand master to the database. Also notifies the appropriate model listeners.
    *
    * @param brandMaster the brand master
    * @return the brand master that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BrandMaster addBrandMaster(
        com.stpl.app.model.BrandMaster brandMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().addBrandMaster(brandMaster);
    }

    /**
    * Creates a new brand master with the primary key. Does not add the brand master to the database.
    *
    * @param brandMasterSid the primary key for the new brand master
    * @return the new brand master
    */
    public static com.stpl.app.model.BrandMaster createBrandMaster(
        int brandMasterSid) {
        return getService().createBrandMaster(brandMasterSid);
    }

    /**
    * Deletes the brand master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param brandMasterSid the primary key of the brand master
    * @return the brand master that was removed
    * @throws PortalException if a brand master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BrandMaster deleteBrandMaster(
        int brandMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteBrandMaster(brandMasterSid);
    }

    /**
    * Deletes the brand master from the database. Also notifies the appropriate model listeners.
    *
    * @param brandMaster the brand master
    * @return the brand master that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BrandMaster deleteBrandMaster(
        com.stpl.app.model.BrandMaster brandMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteBrandMaster(brandMaster);
    }

    public static com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
        return getService().dynamicQuery();
    }

    /**
    * Performs a dynamic query on the database and returns the matching rows.
    *
    * @param dynamicQuery the dynamic query
    * @return the matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().dynamicQuery(dynamicQuery);
    }

    /**
    * Performs a dynamic query on the database and returns a range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BrandMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @return the range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException {
        return getService().dynamicQuery(dynamicQuery, start, end);
    }

    /**
    * Performs a dynamic query on the database and returns an ordered range of the matching rows.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BrandMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param dynamicQuery the dynamic query
    * @param start the lower bound of the range of model instances
    * @param end the upper bound of the range of model instances (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of matching rows
    * @throws SystemException if a system exception occurred
    */
    @SuppressWarnings("rawtypes")
    public static java.util.List dynamicQuery(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService()
                   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public static long dynamicQueryCount(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().dynamicQueryCount(dynamicQuery);
    }

    /**
    * Returns the number of rows that match the dynamic query.
    *
    * @param dynamicQuery the dynamic query
    * @param projection the projection to apply to the query
    * @return the number of rows that match the dynamic query
    * @throws SystemException if a system exception occurred
    */
    public static long dynamicQueryCount(
        com.stpl.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
        com.stpl.portal.kernel.dao.orm.Projection projection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().dynamicQueryCount(dynamicQuery, projection);
    }

    public static com.stpl.app.model.BrandMaster fetchBrandMaster(
        int brandMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().fetchBrandMaster(brandMasterSid);
    }

    /**
    * Returns the brand master with the primary key.
    *
    * @param brandMasterSid the primary key of the brand master
    * @return the brand master
    * @throws PortalException if a brand master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BrandMaster getBrandMaster(
        int brandMasterSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getBrandMaster(brandMasterSid);
    }

    public static com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the brand masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.BrandMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of brand masters
    * @param end the upper bound of the range of brand masters (not inclusive)
    * @return the range of brand masters
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.BrandMaster> getBrandMasters(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getBrandMasters(start, end);
    }

    /**
    * Returns the number of brand masters.
    *
    * @return the number of brand masters
    * @throws SystemException if a system exception occurred
    */
    public static int getBrandMastersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getBrandMastersCount();
    }

    /**
    * Updates the brand master in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param brandMaster the brand master
    * @return the brand master that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.BrandMaster updateBrandMaster(
        com.stpl.app.model.BrandMaster brandMaster)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().updateBrandMaster(brandMaster);
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public static java.lang.String getBeanIdentifier() {
        return getService().getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public static void setBeanIdentifier(java.lang.String beanIdentifier) {
        getService().setBeanIdentifier(beanIdentifier);
    }

    public static java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return getService().invokeMethod(name, parameterTypes, arguments);
    }

    public static java.lang.Object executeSelectQuery(java.lang.String query,
        java.lang.Object udc1, java.lang.Object udc2) {
        LOGGER.debug(query);
        return getService().executeSelectQuery(query, udc1, udc2);
    }

    public static java.lang.Object executeBulkUpdateQuery(
        java.lang.String query, java.lang.Object udc1, java.lang.Object udc2) {
        LOGGER.debug(query);
        return getService().executeBulkUpdateQuery(query, udc1, udc2);
    }

    public static java.lang.Object executeUpdateQuery(
        java.util.List<?> nmSalesList, java.lang.Object udc1,
        java.lang.Object udc2, java.lang.Object udc3) {
        LOGGER.debug(nmSalesList.toString());
        return getService().executeUpdateQuery(nmSalesList, udc1, udc2, udc3);
    }

    public static void clearService() {
        _service = null;
    }

    public static BrandMasterLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    BrandMasterLocalService.class.getName());

            if (invokableLocalService instanceof BrandMasterLocalService) {
                _service = (BrandMasterLocalService) invokableLocalService;
            } else {
                _service = new BrandMasterLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(BrandMasterLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(BrandMasterLocalService service) {
    }
}
