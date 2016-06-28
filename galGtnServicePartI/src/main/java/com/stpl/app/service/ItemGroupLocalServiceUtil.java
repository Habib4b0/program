package com.stpl.app.service;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for ItemGroup. This utility wraps
 * {@link com.stpl.app.service.impl.ItemGroupLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see ItemGroupLocalService
 * @see com.stpl.app.service.base.ItemGroupLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.ItemGroupLocalServiceImpl
 * @generated
 */
public class ItemGroupLocalServiceUtil {
    private static ItemGroupLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.ItemGroupLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the item group to the database. Also notifies the appropriate model listeners.
    *
    * @param itemGroup the item group
    * @return the item group that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ItemGroup addItemGroup(
        com.stpl.app.model.ItemGroup itemGroup)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().addItemGroup(itemGroup);
    }

    /**
    * Creates a new item group with the primary key. Does not add the item group to the database.
    *
    * @param itemGroupSid the primary key for the new item group
    * @return the new item group
    */
    public static com.stpl.app.model.ItemGroup createItemGroup(int itemGroupSid) {
        return getService().createItemGroup(itemGroupSid);
    }

    /**
    * Deletes the item group with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param itemGroupSid the primary key of the item group
    * @return the item group that was removed
    * @throws PortalException if a item group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ItemGroup deleteItemGroup(int itemGroupSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteItemGroup(itemGroupSid);
    }

    /**
    * Deletes the item group from the database. Also notifies the appropriate model listeners.
    *
    * @param itemGroup the item group
    * @return the item group that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ItemGroup deleteItemGroup(
        com.stpl.app.model.ItemGroup itemGroup)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteItemGroup(itemGroup);
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

    public static com.stpl.app.model.ItemGroup fetchItemGroup(int itemGroupSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().fetchItemGroup(itemGroupSid);
    }

    /**
    * Returns the item group with the primary key.
    *
    * @param itemGroupSid the primary key of the item group
    * @return the item group
    * @throws PortalException if a item group with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ItemGroup getItemGroup(int itemGroupSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getItemGroup(itemGroupSid);
    }

    public static com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the item groups.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.ItemGroupModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of item groups
    * @param end the upper bound of the range of item groups (not inclusive)
    * @return the range of item groups
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.ItemGroup> getItemGroups(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getItemGroups(start, end);
    }

    /**
    * Returns the number of item groups.
    *
    * @return the number of item groups
    * @throws SystemException if a system exception occurred
    */
    public static int getItemGroupsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getItemGroupsCount();
    }

    /**
    * Updates the item group in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param itemGroup the item group
    * @return the item group that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.ItemGroup updateItemGroup(
        com.stpl.app.model.ItemGroup itemGroup)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().updateItemGroup(itemGroup);
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

    public static java.util.List getItemGroupMaster(
        java.lang.String itemGroupName) {
        return getService().getItemGroupMaster(itemGroupName);
    }

    public static java.util.List getItemGroupDetails(
        java.lang.String itemType, java.lang.String itemDesc,
        java.lang.String brand, java.lang.String strength,
        java.lang.String itemNoCriteria, java.lang.String therapeuticCriteria,
        java.lang.String formCriteria) {
        return getService()
                   .getItemGroupDetails(itemType, itemDesc, brand, strength,
            itemNoCriteria, therapeuticCriteria, formCriteria);
    }

    public static java.util.List getAvailableSearchResults(
        java.lang.String finalCriteria) {
        return getService().getAvailableSearchResults(finalCriteria);
    }

    public static void clearService() {
        _service = null;
    }

    public static ItemGroupLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ItemGroupLocalService.class.getName());

            if (invokableLocalService instanceof ItemGroupLocalService) {
                _service = (ItemGroupLocalService) invokableLocalService;
            } else {
                _service = new ItemGroupLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(ItemGroupLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(ItemGroupLocalService service) {
    }
}
