package com.stpl.app.service.persistence;

import com.stpl.app.model.HistWorkflowMaster;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the hist workflow master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HistWorkflowMasterPersistenceImpl
 * @see HistWorkflowMasterUtil
 * @generated
 */
public interface HistWorkflowMasterPersistence extends BasePersistence<HistWorkflowMaster> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link HistWorkflowMasterUtil} to access the hist workflow master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the hist workflow master in the entity cache if it is enabled.
    *
    * @param histWorkflowMaster the hist workflow master
    */
    public void cacheResult(
        com.stpl.app.model.HistWorkflowMaster histWorkflowMaster);

    /**
    * Caches the hist workflow masters in the entity cache if it is enabled.
    *
    * @param histWorkflowMasters the hist workflow masters
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.HistWorkflowMaster> histWorkflowMasters);

    /**
    * Creates a new hist workflow master with the primary key. Does not add the hist workflow master to the database.
    *
    * @param workflowMasterSid the primary key for the new hist workflow master
    * @return the new hist workflow master
    */
    public com.stpl.app.model.HistWorkflowMaster create(int workflowMasterSid);

    /**
    * Removes the hist workflow master with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param workflowMasterSid the primary key of the hist workflow master
    * @return the hist workflow master that was removed
    * @throws com.stpl.app.NoSuchHistWorkflowMasterException if a hist workflow master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HistWorkflowMaster remove(int workflowMasterSid)
        throws com.stpl.app.NoSuchHistWorkflowMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.HistWorkflowMaster updateImpl(
        com.stpl.app.model.HistWorkflowMaster histWorkflowMaster)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the hist workflow master with the primary key or throws a {@link com.stpl.app.NoSuchHistWorkflowMasterException} if it could not be found.
    *
    * @param workflowMasterSid the primary key of the hist workflow master
    * @return the hist workflow master
    * @throws com.stpl.app.NoSuchHistWorkflowMasterException if a hist workflow master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HistWorkflowMaster findByPrimaryKey(
        int workflowMasterSid)
        throws com.stpl.app.NoSuchHistWorkflowMasterException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the hist workflow master with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param workflowMasterSid the primary key of the hist workflow master
    * @return the hist workflow master, or <code>null</code> if a hist workflow master with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.HistWorkflowMaster fetchByPrimaryKey(
        int workflowMasterSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the hist workflow masters.
    *
    * @return the hist workflow masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.HistWorkflowMaster> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the hist workflow masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistWorkflowMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hist workflow masters
    * @param end the upper bound of the range of hist workflow masters (not inclusive)
    * @return the range of hist workflow masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.HistWorkflowMaster> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the hist workflow masters.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.HistWorkflowMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of hist workflow masters
    * @param end the upper bound of the range of hist workflow masters (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of hist workflow masters
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.HistWorkflowMaster> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the hist workflow masters from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of hist workflow masters.
    *
    * @return the number of hist workflow masters
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
