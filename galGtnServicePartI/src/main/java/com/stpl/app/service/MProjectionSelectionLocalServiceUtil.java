package com.stpl.app.service;

import com.stpl.portal.kernel.bean.PortletBeanLocatorUtil;
import com.stpl.portal.kernel.util.ReferenceRegistry;
import com.stpl.portal.service.InvokableLocalService;

/**
 * Provides the local service utility for MProjectionSelection. This utility wraps
 * {@link com.stpl.app.service.impl.MProjectionSelectionLocalServiceImpl} and is the
 * primary access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author
 * @see MProjectionSelectionLocalService
 * @see com.stpl.app.service.base.MProjectionSelectionLocalServiceBaseImpl
 * @see com.stpl.app.service.impl.MProjectionSelectionLocalServiceImpl
 * @generated
 */
public class MProjectionSelectionLocalServiceUtil {
    private static MProjectionSelectionLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link com.stpl.app.service.impl.MProjectionSelectionLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Adds the m projection selection to the database. Also notifies the appropriate model listeners.
    *
    * @param mProjectionSelection the m projection selection
    * @return the m projection selection that was added
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MProjectionSelection addMProjectionSelection(
        com.stpl.app.model.MProjectionSelection mProjectionSelection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().addMProjectionSelection(mProjectionSelection);
    }

    /**
    * Creates a new m projection selection with the primary key. Does not add the m projection selection to the database.
    *
    * @param mProjectionSelectionSid the primary key for the new m projection selection
    * @return the new m projection selection
    */
    public static com.stpl.app.model.MProjectionSelection createMProjectionSelection(
        int mProjectionSelectionSid) {
        return getService().createMProjectionSelection(mProjectionSelectionSid);
    }

    /**
    * Deletes the m projection selection with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param mProjectionSelectionSid the primary key of the m projection selection
    * @return the m projection selection that was removed
    * @throws PortalException if a m projection selection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MProjectionSelection deleteMProjectionSelection(
        int mProjectionSelectionSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteMProjectionSelection(mProjectionSelectionSid);
    }

    /**
    * Deletes the m projection selection from the database. Also notifies the appropriate model listeners.
    *
    * @param mProjectionSelection the m projection selection
    * @return the m projection selection that was removed
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MProjectionSelection deleteMProjectionSelection(
        com.stpl.app.model.MProjectionSelection mProjectionSelection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().deleteMProjectionSelection(mProjectionSelection);
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.MProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
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

    public static com.stpl.app.model.MProjectionSelection fetchMProjectionSelection(
        int mProjectionSelectionSid)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().fetchMProjectionSelection(mProjectionSelectionSid);
    }

    /**
    * Returns the m projection selection with the primary key.
    *
    * @param mProjectionSelectionSid the primary key of the m projection selection
    * @return the m projection selection
    * @throws PortalException if a m projection selection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MProjectionSelection getMProjectionSelection(
        int mProjectionSelectionSid)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getMProjectionSelection(mProjectionSelectionSid);
    }

    public static com.stpl.portal.model.PersistedModel getPersistedModel(
        java.io.Serializable primaryKeyObj)
        throws com.stpl.portal.kernel.exception.PortalException,
            com.stpl.portal.kernel.exception.SystemException {
        return getService().getPersistedModel(primaryKeyObj);
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
    public static java.util.List<com.stpl.app.model.MProjectionSelection> getMProjectionSelections(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getMProjectionSelections(start, end);
    }

    /**
    * Returns the number of m projection selections.
    *
    * @return the number of m projection selections
    * @throws SystemException if a system exception occurred
    */
    public static int getMProjectionSelectionsCount()
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().getMProjectionSelectionsCount();
    }

    /**
    * Updates the m projection selection in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
    *
    * @param mProjectionSelection the m projection selection
    * @return the m projection selection that was updated
    * @throws SystemException if a system exception occurred
    */
    public static com.stpl.app.model.MProjectionSelection updateMProjectionSelection(
        com.stpl.app.model.MProjectionSelection mProjectionSelection)
        throws com.stpl.portal.kernel.exception.SystemException {
        return getService().updateMProjectionSelection(mProjectionSelection);
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

    public static MProjectionSelectionLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    MProjectionSelectionLocalService.class.getName());

            if (invokableLocalService instanceof MProjectionSelectionLocalService) {
                _service = (MProjectionSelectionLocalService) invokableLocalService;
            } else {
                _service = new MProjectionSelectionLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(MProjectionSelectionLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated As of 6.2.0
     */
    public void setService(MProjectionSelectionLocalService service) {
    }
}
