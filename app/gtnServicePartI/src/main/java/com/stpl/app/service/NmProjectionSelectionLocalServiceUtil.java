package com.stpl.app.service;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for NmProjectionSelection. This utility wraps
 * {@link com.stpl.app.service.impl.NmProjectionSelectionLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see NmProjectionSelectionLocalService
 * @see com.stpl.app.service.base.NmProjectionSelectionLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.NmProjectionSelectionLocalServiceImpl
 * @generated
 */
public class NmProjectionSelectionLocalServiceUtil {
    private static NmProjectionSelectionLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.NmProjectionSelectionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the nm projection selection to the database. Also notifies the appropriate model listeners.
    *
    * @param nmProjectionSelection the nm projection selection
    * @return the nm projection selection that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.NmProjectionSelection addNmProjectionSelection(
        com.stpl.app.model.NmProjectionSelection nmProjectionSelection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().addNmProjectionSelection(nmProjectionSelection);
    }

    /**
    * Creates a new nm projection selection with the primary key. Does not add the nm projection selection to the database.
    *
    * @param nmProjectionSelectionSid the primary key for the new nm projection selection
    * @return the new nm projection selection
    */
    public static com.stpl.app.model.NmProjectionSelection createNmProjectionSelection(
        int nmProjectionSelectionSid) {
        return getService().createNmProjectionSelection(nmProjectionSelectionSid);
    }

    /**
    * Deletes the nm projection selection with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param nmProjectionSelectionSid the primary key of the nm projection selection
    * @return the nm projection selection that was removed
    * @throws PortalException if a nm projection selection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.NmProjectionSelection deleteNmProjectionSelection(
        int nmProjectionSelectionSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteNmProjectionSelection(nmProjectionSelectionSid);
    }

    /**
    * Deletes the nm projection selection from the database. Also notifies the appropriate model listeners.
    *
    * @param nmProjectionSelection the nm projection selection
    * @return the nm projection selection that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.NmProjectionSelection deleteNmProjectionSelection(
        com.stpl.app.model.NmProjectionSelection nmProjectionSelection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteNmProjectionSelection(nmProjectionSelection);
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

    public static com.stpl.app.model.NmProjectionSelection fetchNmProjectionSelection(
        int nmProjectionSelectionSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().fetchNmProjectionSelection(nmProjectionSelectionSid);
    }

    /**
    * Returns the nm projection selection with the primary key.
    *
    * @param nmProjectionSelectionSid the primary key of the nm projection selection
    * @return the nm projection selection
    * @throws PortalException if a nm projection selection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.NmProjectionSelection getNmProjectionSelection(
        int nmProjectionSelectionSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getNmProjectionSelection(nmProjectionSelectionSid);
    }

    public static com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
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
    public static java.util.List<com.stpl.app.model.NmProjectionSelection> getNmProjectionSelections(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getNmProjectionSelections(start, end);
    }

    /**
    * Returns the number of nm projection selections.
    *
    * @return the number of nm projection selections
    * @throws SystemException if a system exception occurred
    */
    public static int getNmProjectionSelectionsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getNmProjectionSelectionsCount();
    }

    /**
    * Updates the nm projection selection in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param nmProjectionSelection the nm projection selection
    * @return the nm projection selection that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.NmProjectionSelection updateNmProjectionSelection(
        com.stpl.app.model.NmProjectionSelection nmProjectionSelection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().updateNmProjectionSelection(nmProjectionSelection);
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

    public static void clearService() {
        _service = null;
    }

    public static NmProjectionSelectionLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    NmProjectionSelectionLocalService.class.getName());

            if (invokableLocalService instanceof NmProjectionSelectionLocalService) {
                _service = (NmProjectionSelectionLocalService) invokableLocalService;
            } else {
                _service = new NmProjectionSelectionLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(NmProjectionSelectionLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(NmProjectionSelectionLocalService service) {
    }
}
