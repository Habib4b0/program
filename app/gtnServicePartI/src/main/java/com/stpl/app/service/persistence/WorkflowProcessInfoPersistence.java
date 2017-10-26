package com.stpl.app.service.persistence;

import com.stpl.app.model.WorkflowProcessInfo;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the workflow process info service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see WorkflowProcessInfoPersistenceImpl
 * @see WorkflowProcessInfoUtil
 * @generated
 */
public interface WorkflowProcessInfoPersistence extends BasePersistence<WorkflowProcessInfo> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link WorkflowProcessInfoUtil} to access the workflow process info persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the workflow process info in the entity cache if it is enabled.
    *
    * @param workflowProcessInfo the workflow process info
    */
    public void cacheResult(
        com.stpl.app.model.WorkflowProcessInfo workflowProcessInfo);

    /**
    * Caches the workflow process infos in the entity cache if it is enabled.
    *
    * @param workflowProcessInfos the workflow process infos
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.WorkflowProcessInfo> workflowProcessInfos);

    /**
    * Creates a new workflow process info with the primary key. Does not add the workflow process info to the database.
    *
    * @param workflowProcessInfoSid the primary key for the new workflow process info
    * @return the new workflow process info
    */
    public com.stpl.app.model.WorkflowProcessInfo create(
        int workflowProcessInfoSid);

    /**
    * Removes the workflow process info with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param workflowProcessInfoSid the primary key of the workflow process info
    * @return the workflow process info that was removed
    * @throws com.stpl.app.NoSuchWorkflowProcessInfoException if a workflow process info with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.WorkflowProcessInfo remove(
        int workflowProcessInfoSid)
        throws com.stpl.app.NoSuchWorkflowProcessInfoException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.WorkflowProcessInfo updateImpl(
        com.stpl.app.model.WorkflowProcessInfo workflowProcessInfo)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the workflow process info with the primary key or throws a {@link com.stpl.app.NoSuchWorkflowProcessInfoException} if it could not be found.
    *
    * @param workflowProcessInfoSid the primary key of the workflow process info
    * @return the workflow process info
    * @throws com.stpl.app.NoSuchWorkflowProcessInfoException if a workflow process info with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.WorkflowProcessInfo findByPrimaryKey(
        int workflowProcessInfoSid)
        throws com.stpl.app.NoSuchWorkflowProcessInfoException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the workflow process info with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param workflowProcessInfoSid the primary key of the workflow process info
    * @return the workflow process info, or <code>null</code> if a workflow process info with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.WorkflowProcessInfo fetchByPrimaryKey(
        int workflowProcessInfoSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the workflow process infos.
    *
    * @return the workflow process infos
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.WorkflowProcessInfo> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the workflow process infos.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.WorkflowProcessInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of workflow process infos
    * @param end the upper bound of the range of workflow process infos (not inclusive)
    * @return the range of workflow process infos
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.WorkflowProcessInfo> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the workflow process infos.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.WorkflowProcessInfoModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of workflow process infos
    * @param end the upper bound of the range of workflow process infos (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of workflow process infos
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.WorkflowProcessInfo> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the workflow process infos from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of workflow process infos.
    *
    * @return the number of workflow process infos
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
