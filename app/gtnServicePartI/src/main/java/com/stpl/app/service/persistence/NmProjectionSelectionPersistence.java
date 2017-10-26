package com.stpl.app.service.persistence;

import com.stpl.app.model.NmProjectionSelection;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the nm projection selection service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see NmProjectionSelectionPersistenceImpl
 * @see NmProjectionSelectionUtil
 * @generated
 */
public interface NmProjectionSelectionPersistence extends BasePersistence<NmProjectionSelection> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link NmProjectionSelectionUtil} to access the nm projection selection persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the nm projection selection in the entity cache if it is enabled.
    *
    * @param nmProjectionSelection the nm projection selection
    */
    public void cacheResult(
        com.stpl.app.model.NmProjectionSelection nmProjectionSelection);

    /**
    * Caches the nm projection selections in the entity cache if it is enabled.
    *
    * @param nmProjectionSelections the nm projection selections
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.NmProjectionSelection> nmProjectionSelections);

    /**
    * Creates a new nm projection selection with the primary key. Does not add the nm projection selection to the database.
    *
    * @param nmProjectionSelectionSid the primary key for the new nm projection selection
    * @return the new nm projection selection
    */
    public com.stpl.app.model.NmProjectionSelection create(
        int nmProjectionSelectionSid);

    /**
    * Removes the nm projection selection with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param nmProjectionSelectionSid the primary key of the nm projection selection
    * @return the nm projection selection that was removed
    * @throws com.stpl.app.NoSuchNmProjectionSelectionException if a nm projection selection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.NmProjectionSelection remove(
        int nmProjectionSelectionSid)
        throws com.stpl.app.NoSuchNmProjectionSelectionException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.NmProjectionSelection updateImpl(
        com.stpl.app.model.NmProjectionSelection nmProjectionSelection)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the nm projection selection with the primary key or throws a {@link com.stpl.app.NoSuchNmProjectionSelectionException} if it could not be found.
    *
    * @param nmProjectionSelectionSid the primary key of the nm projection selection
    * @return the nm projection selection
    * @throws com.stpl.app.NoSuchNmProjectionSelectionException if a nm projection selection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.NmProjectionSelection findByPrimaryKey(
        int nmProjectionSelectionSid)
        throws com.stpl.app.NoSuchNmProjectionSelectionException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the nm projection selection with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param nmProjectionSelectionSid the primary key of the nm projection selection
    * @return the nm projection selection, or <code>null</code> if a nm projection selection with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.NmProjectionSelection fetchByPrimaryKey(
        int nmProjectionSelectionSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the nm projection selections.
    *
    * @return the nm projection selections
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.NmProjectionSelection> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

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
    public java.util.List<com.stpl.app.model.NmProjectionSelection> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the nm projection selections.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.NmProjectionSelectionModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of nm projection selections
    * @param end the upper bound of the range of nm projection selections (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of nm projection selections
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.NmProjectionSelection> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the nm projection selections from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of nm projection selections.
    *
    * @return the number of nm projection selections
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
