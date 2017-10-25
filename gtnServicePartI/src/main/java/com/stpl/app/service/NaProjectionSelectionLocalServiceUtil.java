package com.stpl.app.service;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for NaProjectionSelection. This utility wraps
 * {@link com.stpl.app.service.impl.NaProjectionSelectionLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see NaProjectionSelectionLocalService
 * @see com.stpl.app.service.base.NaProjectionSelectionLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.NaProjectionSelectionLocalServiceImpl
 * @generated
 */
public class NaProjectionSelectionLocalServiceUtil {
    private static NaProjectionSelectionLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.NaProjectionSelectionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the na projection selection to the database. Also notifies the appropriate model listeners.
    *
    * @param naProjectionSelection the na projection selection
    * @return the na projection selection that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.NaProjectionSelection addNaProjectionSelection(
        com.stpl.app.model.NaProjectionSelection naProjectionSelection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().addNaProjectionSelection(naProjectionSelection);
    }

    /**
    * Creates a new na projection selection with the primary key. Does not add the na projection selection to the database.
    *
    * @param naProjectionSelectionSid the primary key for the new na projection selection
    * @return the new na projection selection
    */
    public static com.stpl.app.model.NaProjectionSelection createNaProjectionSelection(
        int naProjectionSelectionSid) {
        return getService().createNaProjectionSelection(naProjectionSelectionSid);
    }

    /**
    * Deletes the na projection selection with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param naProjectionSelectionSid the primary key of the na projection selection
    * @return the na projection selection that was removed
    * @throws PortalException if a na projection selection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.NaProjectionSelection deleteNaProjectionSelection(
        int naProjectionSelectionSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteNaProjectionSelection(naProjectionSelectionSid);
    }

    /**
    * Deletes the na projection selection from the database. Also notifies the appropriate model listeners.
    *
    * @param naProjectionSelection the na projection selection
    * @return the na projection selection that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.NaProjectionSelection deleteNaProjectionSelection(
        com.stpl.app.model.NaProjectionSelection naProjectionSelection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteNaProjectionSelection(naProjectionSelection);
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NaProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NaProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

    public static com.stpl.app.model.NaProjectionSelection fetchNaProjectionSelection(
        int naProjectionSelectionSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().fetchNaProjectionSelection(naProjectionSelectionSid);
    }

    /**
    * Returns the na projection selection with the primary key.
    *
    * @param naProjectionSelectionSid the primary key of the na projection selection
    * @return the na projection selection
    * @throws PortalException if a na projection selection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.NaProjectionSelection getNaProjectionSelection(
        int naProjectionSelectionSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getNaProjectionSelection(naProjectionSelectionSid);
    }

    public static com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
    }

    /**
    * Returns a range of all the na projection selections.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NaProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of na projection selections
    * @param end the upper bound of the range of na projection selections (not inclusive)
    * @return the range of na projection selections
    * @throws SystemException if a system exception occurred
    */
    public static java.util.List<com.stpl.app.model.NaProjectionSelection> getNaProjectionSelections(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getNaProjectionSelections(start, end);
    }

    /**
    * Returns the number of na projection selections.
    *
    * @return the number of na projection selections
    * @throws SystemException if a system exception occurred
    */
    public static int getNaProjectionSelectionsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getNaProjectionSelectionsCount();
    }

    /**
    * Updates the na projection selection in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param naProjectionSelection the na projection selection
    * @return the na projection selection that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.NaProjectionSelection updateNaProjectionSelection(
        com.stpl.app.model.NaProjectionSelection naProjectionSelection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().updateNaProjectionSelection(naProjectionSelection);
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

    public static NaProjectionSelectionLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    NaProjectionSelectionLocalService.class.getName());

            if (invokableLocalService instanceof NaProjectionSelectionLocalService) {
                _service = (NaProjectionSelectionLocalService) invokableLocalService;
            } else {
                _service = new NaProjectionSelectionLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(NaProjectionSelectionLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(NaProjectionSelectionLocalService service) {
    }
}
