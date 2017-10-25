package com.stpl.app.service.persistence;

import com.stpl.app.model.CdrModel;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the cdr model service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see CdrModelPersistenceImpl
 * @see CdrModelUtil
 * @generated
 */
public interface CdrModelPersistence extends BasePersistence<CdrModel> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link CdrModelUtil} to access the cdr model persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the cdr model in the entity cache if it is enabled.
    *
    * @param cdrModel the cdr model
    */
    public void cacheResult(com.stpl.app.model.CdrModel cdrModel);

    /**
    * Caches the cdr models in the entity cache if it is enabled.
    *
    * @param cdrModels the cdr models
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.CdrModel> cdrModels);

    /**
    * Creates a new cdr model with the primary key. Does not add the cdr model to the database.
    *
    * @param cdrModelSid the primary key for the new cdr model
    * @return the new cdr model
    */
    public com.stpl.app.model.CdrModel create(int cdrModelSid);

    /**
    * Removes the cdr model with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param cdrModelSid the primary key of the cdr model
    * @return the cdr model that was removed
    * @throws com.stpl.app.NoSuchCdrModelException if a cdr model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CdrModel remove(int cdrModelSid)
        throws com.stpl.app.NoSuchCdrModelException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.CdrModel updateImpl(
        com.stpl.app.model.CdrModel cdrModel)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the cdr model with the primary key or throws a {@link com.stpl.app.NoSuchCdrModelException} if it could not be found.
    *
    * @param cdrModelSid the primary key of the cdr model
    * @return the cdr model
    * @throws com.stpl.app.NoSuchCdrModelException if a cdr model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CdrModel findByPrimaryKey(int cdrModelSid)
        throws com.stpl.app.NoSuchCdrModelException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the cdr model with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param cdrModelSid the primary key of the cdr model
    * @return the cdr model, or <code>null</code> if a cdr model with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.CdrModel fetchByPrimaryKey(int cdrModelSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the cdr models.
    *
    * @return the cdr models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CdrModel> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the cdr models.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CdrModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cdr models
    * @param end the upper bound of the range of cdr models (not inclusive)
    * @return the range of cdr models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CdrModel> findAll(int start,
        int end) throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the cdr models.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.CdrModelModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of cdr models
    * @param end the upper bound of the range of cdr models (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of cdr models
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.CdrModel> findAll(int start,
        int end, com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the cdr models from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of cdr models.
    *
    * @return the number of cdr models
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
