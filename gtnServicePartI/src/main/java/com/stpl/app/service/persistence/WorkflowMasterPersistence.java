package com.stpl.app.service.persistence;

import com.stpl.app.model.WorkflowMaster;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the workflow master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see WorkflowMasterPersistenceImpl
 * @see WorkflowMasterUtil
 * @generated
 */
public interface WorkflowMasterPersistence extends BasePersistence<WorkflowMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link WorkflowMasterUtil} to access the workflow master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the workflow master in the entity cache if it is enabled.
    *
    * @param workflowMaster the workflow master
    */
    public void cacheResult(com.stpl.app.model.WorkflowMaster workflowMaster);

    /**
    * Caches the workflow masters in the entity cache if it is enabled.
    *
    * @param workflowMasters the workflow masters
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.WorkflowMaster> workflowMasters);

    /**
    * Creates a new workflow master with the primary key. Does not add the workflow master to the database.
    *
    * @param workflowMasterSid the primary key for the new workflow master
    * @return the new workflow master
    */
    public com.stpl.app.model.WorkflowMaster create(int workflowMasterSid);

    /**
    * Removes the workflow master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param workflowMasterSid the primary key of the workflow master
    * @return the workflow master that was removed
    * @throws com.stpl.app.NoSuchWorkflowMasterException if a workflow master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.WorkflowMaster remove(int workflowMasterSid)
        throws com.stpl.app.NoSuchWorkflowMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.WorkflowMaster updateImpl(
        com.stpl.app.model.WorkflowMaster workflowMaster)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the workflow master with the primary key or throws a {@link com.stpl.app.NoSuchWorkflowMasterException} if it could not be found.
    *
    * @param workflowMasterSid the primary key of the workflow master
    * @return the workflow master
    * @throws com.stpl.app.NoSuchWorkflowMasterException if a workflow master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.WorkflowMaster findByPrimaryKey(
        int workflowMasterSid)
        throws com.stpl.app.NoSuchWorkflowMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the workflow master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param workflowMasterSid the primary key of the workflow master
    * @return the workflow master, or <code>null</code> if a workflow master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.WorkflowMaster fetchByPrimaryKey(
        int workflowMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the workflow masters.
    *
    * @return the workflow masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.WorkflowMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the workflow masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.WorkflowMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of workflow masters
    * @param end the upper bound of the range of workflow masters (not inclusive)
    * @return the range of workflow masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.WorkflowMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the workflow masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.WorkflowMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of workflow masters
    * @param end the upper bound of the range of workflow masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of workflow masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.WorkflowMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the workflow masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of workflow masters.
    *
    * @return the number of workflow masters
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
