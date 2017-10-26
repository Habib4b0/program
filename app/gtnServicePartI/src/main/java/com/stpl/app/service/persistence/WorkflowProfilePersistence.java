package com.stpl.app.service.persistence;

import com.stpl.app.model.WorkflowProfile;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the workflow profile service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see WorkflowProfilePersistenceImpl
 * @see WorkflowProfileUtil
 * @generated
 */
public interface WorkflowProfilePersistence extends BasePersistence<WorkflowProfile> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link WorkflowProfileUtil} to access the workflow profile persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the workflow profile in the entity cache if it is enabled.
    *
    * @param workflowProfile the workflow profile
    */
    public void cacheResult(com.stpl.app.model.WorkflowProfile workflowProfile);

    /**
    * Caches the workflow profiles in the entity cache if it is enabled.
    *
    * @param workflowProfiles the workflow profiles
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.WorkflowProfile> workflowProfiles);

    /**
    * Creates a new workflow profile with the primary key. Does not add the workflow profile to the database.
    *
    * @param processSid the primary key for the new workflow profile
    * @return the new workflow profile
    */
    public com.stpl.app.model.WorkflowProfile create(int processSid);

    /**
    * Removes the workflow profile with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param processSid the primary key of the workflow profile
    * @return the workflow profile that was removed
    * @throws com.stpl.app.NoSuchWorkflowProfileException if a workflow profile with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.WorkflowProfile remove(int processSid)
        throws com.stpl.app.NoSuchWorkflowProfileException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.WorkflowProfile updateImpl(
        com.stpl.app.model.WorkflowProfile workflowProfile)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the workflow profile with the primary key or throws a {@link com.stpl.app.NoSuchWorkflowProfileException} if it could not be found.
    *
    * @param processSid the primary key of the workflow profile
    * @return the workflow profile
    * @throws com.stpl.app.NoSuchWorkflowProfileException if a workflow profile with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.WorkflowProfile findByPrimaryKey(int processSid)
        throws com.stpl.app.NoSuchWorkflowProfileException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the workflow profile with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param processSid the primary key of the workflow profile
    * @return the workflow profile, or <code>null</code> if a workflow profile with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.WorkflowProfile fetchByPrimaryKey(int processSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the workflow profiles.
    *
    * @return the workflow profiles
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.WorkflowProfile> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the workflow profiles.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.WorkflowProfileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of workflow profiles
    * @param end the upper bound of the range of workflow profiles (not inclusive)
    * @return the range of workflow profiles
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.WorkflowProfile> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the workflow profiles.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.WorkflowProfileModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of workflow profiles
    * @param end the upper bound of the range of workflow profiles (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of workflow profiles
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.WorkflowProfile> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the workflow profiles from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of workflow profiles.
    *
    * @return the number of workflow profiles
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
