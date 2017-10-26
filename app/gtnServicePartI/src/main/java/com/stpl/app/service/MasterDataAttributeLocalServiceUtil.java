package com.stpl.app.service;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for MasterDataAttribute. This utility wraps
 * {@link com.stpl.app.service.impl.MasterDataAttributeLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see MasterDataAttributeLocalService
 * @see com.stpl.app.service.base.MasterDataAttributeLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.MasterDataAttributeLocalServiceImpl
 * @generated
 */
public class MasterDataAttributeLocalServiceUtil {
    private static MasterDataAttributeLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.MasterDataAttributeLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the master data attribute to the database. Also notifies the appropriate model listeners.
    *
    * @param masterDataAttribute the master data attribute
    * @return the master data attribute that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MasterDataAttribute addMasterDataAttribute(
        com.stpl.app.model.MasterDataAttribute masterDataAttribute)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().addMasterDataAttribute(masterDataAttribute);
    }

    /**
    * Creates a new master data attribute with the primary key. Does not add the master data attribute to the database.
    *
    * @param masterDataAttributeSid the primary key for the new master data attribute
    * @return the new master data attribute
    */
    public static com.stpl.app.model.MasterDataAttribute createMasterDataAttribute(
        int masterDataAttributeSid) {
        return getService().createMasterDataAttribute(masterDataAttributeSid);
    }

    /**
    * Deletes the master data attribute with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param masterDataAttributeSid the primary key of the master data attribute
    * @return the master data attribute that was removed
    * @throws PortalException if a master data attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MasterDataAttribute deleteMasterDataAttribute(
        int masterDataAttributeSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteMasterDataAttribute(masterDataAttributeSid);
    }

    /**
    * Deletes the master data attribute from the database. Also notifies the appropriate model listeners.
    *
    * @param masterDataAttribute the master data attribute
    * @return the master data attribute that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MasterDataAttribute deleteMasterDataAttribute(
        com.stpl.app.model.MasterDataAttribute masterDataAttribute)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteMasterDataAttribute(masterDataAttribute);
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

    public static com.stpl.app.model.MasterDataAttribute fetchMasterDataAttribute(
        int masterDataAttributeSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().fetchMasterDataAttribute(masterDataAttributeSid);
    }

    /**
    * Returns the master data attribute with the primary key.
    *
    * @param masterDataAttributeSid the primary key of the master data attribute
    * @return the master data attribute
    * @throws PortalException if a master data attribute with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MasterDataAttribute getMasterDataAttribute(
        int masterDataAttributeSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getMasterDataAttribute(masterDataAttributeSid);
    }

    public static com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the master data attributes.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MasterDataAttributeModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of master data attributes
    * @param end the upper bound of the range of master data attributes (not inclusive)
    * @return the range of master data attributes
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.MasterDataAttribute> getMasterDataAttributes(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getMasterDataAttributes(start, end);
    }

    /**
    * Returns the number of master data attributes.
    *
    * @return the number of master data attributes
    * @throws SystemException if a system exception occurred
    */
    public static int getMasterDataAttributesCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getMasterDataAttributesCount();
    }

    /**
    * Updates the master data attribute in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param masterDataAttribute the master data attribute
    * @return the master data attribute that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MasterDataAttribute updateMasterDataAttribute(
        com.stpl.app.model.MasterDataAttribute masterDataAttribute)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().updateMasterDataAttribute(masterDataAttribute);
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

    public static java.util.List getTotalLives(java.lang.Object[] inputs) {
        return getService().getTotalLives(inputs);
    }

    public static java.util.List getLives(java.lang.Object[] inputs) {
        return getService().getLives(inputs);
    }

    public static void clearService() {
        _service = null;
    }

    public static MasterDataAttributeLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    MasterDataAttributeLocalService.class.getName());

            if (invokableLocalService instanceof MasterDataAttributeLocalService) {
                _service = (MasterDataAttributeLocalService) invokableLocalService;
            } else {
                _service = new MasterDataAttributeLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(MasterDataAttributeLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(MasterDataAttributeLocalService service) {
    }
}
