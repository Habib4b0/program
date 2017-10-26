package com.stpl.app.service;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for ItemQualifier. This utility wraps
 * {@link com.stpl.app.service.impl.ItemQualifierLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see ItemQualifierLocalService
 * @see com.stpl.app.service.base.ItemQualifierLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.ItemQualifierLocalServiceImpl
 * @generated
 */
public class ItemQualifierLocalServiceUtil {
    private static ItemQualifierLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.ItemQualifierLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the item qualifier to the database. Also notifies the appropriate model listeners.
    *
    * @param itemQualifier the item qualifier
    * @return the item qualifier that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ItemQualifier addItemQualifier(
        com.stpl.app.model.ItemQualifier itemQualifier)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().addItemQualifier(itemQualifier);
    }

    /**
    * Creates a new item qualifier with the primary key. Does not add the item qualifier to the database.
    *
    * @param itemQualifierSid the primary key for the new item qualifier
    * @return the new item qualifier
    */
    public static com.stpl.app.model.ItemQualifier createItemQualifier(
        int itemQualifierSid) {
        return getService().createItemQualifier(itemQualifierSid);
    }

    /**
    * Deletes the item qualifier with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param itemQualifierSid the primary key of the item qualifier
    * @return the item qualifier that was removed
    * @throws PortalException if a item qualifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ItemQualifier deleteItemQualifier(
        int itemQualifierSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteItemQualifier(itemQualifierSid);
    }

    /**
    * Deletes the item qualifier from the database. Also notifies the appropriate model listeners.
    *
    * @param itemQualifier the item qualifier
    * @return the item qualifier that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ItemQualifier deleteItemQualifier(
        com.stpl.app.model.ItemQualifier itemQualifier)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteItemQualifier(itemQualifier);
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

    public static com.stpl.app.model.ItemQualifier fetchItemQualifier(
        int itemQualifierSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().fetchItemQualifier(itemQualifierSid);
    }

    /**
    * Returns the item qualifier with the primary key.
    *
    * @param itemQualifierSid the primary key of the item qualifier
    * @return the item qualifier
    * @throws PortalException if a item qualifier with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ItemQualifier getItemQualifier(
        int itemQualifierSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getItemQualifier(itemQualifierSid);
    }

    public static com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the item qualifiers.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemQualifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of item qualifiers
    * @param end the upper bound of the range of item qualifiers (not inclusive)
    * @return the range of item qualifiers
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ItemQualifier> getItemQualifiers(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getItemQualifiers(start, end);
    }

    /**
    * Returns the number of item qualifiers.
    *
    * @return the number of item qualifiers
    * @throws SystemException if a system exception occurred
    */
    public static int getItemQualifiersCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getItemQualifiersCount();
    }

    /**
    * Updates the item qualifier in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param itemQualifier the item qualifier
    * @return the item qualifier that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ItemQualifier updateItemQualifier(
        com.stpl.app.model.ItemQualifier itemQualifier)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().updateItemQualifier(itemQualifier);
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

    public static com.stpl.app.model.ItemQualifier findByItemIrtQualifierByName(
        java.lang.String itemQualifierValue)
        throws com.stpl.app.NoSuchItemQualifierException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().findByItemIrtQualifierByName(itemQualifierValue);
    }

    public static void clearService() {
        _service = null;
    }

    public static ItemQualifierLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ItemQualifierLocalService.class.getName());

            if (invokableLocalService instanceof ItemQualifierLocalService) {
                _service = (ItemQualifierLocalService) invokableLocalService;
            } else {
                _service = new ItemQualifierLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(ItemQualifierLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(ItemQualifierLocalService service) {
    }
}
