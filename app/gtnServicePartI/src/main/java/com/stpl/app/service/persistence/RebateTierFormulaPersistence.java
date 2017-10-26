package com.stpl.app.service.persistence;

import com.stpl.app.model.RebateTierFormula;

import com.stpl.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the rebate tier formula service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see RebateTierFormulaPersistenceImpl
 * @see RebateTierFormulaUtil
 * @generated
 */
public interface RebateTierFormulaPersistence extends BasePersistence<RebateTierFormula> {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link RebateTierFormulaUtil} to access the rebate tier formula persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
     */

    /**
    * Caches the rebate tier formula in the entity cache if it is enabled.
    *
    * @param rebateTierFormula the rebate tier formula
    */
    public void cacheResult(
        com.stpl.app.model.RebateTierFormula rebateTierFormula);

    /**
    * Caches the rebate tier formulas in the entity cache if it is enabled.
    *
    * @param rebateTierFormulas the rebate tier formulas
    */
    public void cacheResult(
        java.util.List<com.stpl.app.model.RebateTierFormula> rebateTierFormulas);

    /**
    * Creates a new rebate tier formula with the primary key. Does not add the rebate tier formula to the database.
    *
    * @param rebateTierFormulaSid the primary key for the new rebate tier formula
    * @return the new rebate tier formula
    */
    public com.stpl.app.model.RebateTierFormula create(int rebateTierFormulaSid);

    /**
    * Removes the rebate tier formula with the primary key from the database. Also notifies the appropriate model listeners.
    *
    * @param rebateTierFormulaSid the primary key of the rebate tier formula
    * @return the rebate tier formula that was removed
    * @throws com.stpl.app.NoSuchRebateTierFormulaException if a rebate tier formula with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RebateTierFormula remove(int rebateTierFormulaSid)
        throws com.stpl.app.NoSuchRebateTierFormulaException,
            com.stpl.portal.kernel.exception.SystemException;

    public com.stpl.app.model.RebateTierFormula updateImpl(
        com.stpl.app.model.RebateTierFormula rebateTierFormula)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the rebate tier formula with the primary key or throws a {@link com.stpl.app.NoSuchRebateTierFormulaException} if it could not be found.
    *
    * @param rebateTierFormulaSid the primary key of the rebate tier formula
    * @return the rebate tier formula
    * @throws com.stpl.app.NoSuchRebateTierFormulaException if a rebate tier formula with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RebateTierFormula findByPrimaryKey(
        int rebateTierFormulaSid)
        throws com.stpl.app.NoSuchRebateTierFormulaException,
            com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the rebate tier formula with the primary key or returns <code>null</code> if it could not be found.
    *
    * @param rebateTierFormulaSid the primary key of the rebate tier formula
    * @return the rebate tier formula, or <code>null</code> if a rebate tier formula with the primary key could not be found
    * @throws SystemException if a system exception occurred
    */
    public com.stpl.app.model.RebateTierFormula fetchByPrimaryKey(
        int rebateTierFormulaSid)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns all the rebate tier formulas.
    *
    * @return the rebate tier formulas
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RebateTierFormula> findAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns a range of all the rebate tier formulas.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebateTierFormulaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of rebate tier formulas
    * @param end the upper bound of the range of rebate tier formulas (not inclusive)
    * @return the range of rebate tier formulas
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RebateTierFormula> findAll(
        int start, int end)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns an ordered range of all the rebate tier formulas.
    *
    * <p>
    * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link com.stpl.portal.kernel.dao.orm.QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link com.stpl.app.model.impl.RebateTierFormulaModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
    * </p>
    *
    * @param start the lower bound of the range of rebate tier formulas
    * @param end the upper bound of the range of rebate tier formulas (not inclusive)
    * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
    * @return the ordered range of rebate tier formulas
    * @throws SystemException if a system exception occurred
    */
    public java.util.List<com.stpl.app.model.RebateTierFormula> findAll(
        int start, int end,
        com.stpl.portal.kernel.util.OrderByComparator orderByComparator)
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Removes all the rebate tier formulas from the database.
    *
    * @throws SystemException if a system exception occurred
    */
    public void removeAll()
        throws com.stpl.portal.kernel.exception.SystemException;

    /**
    * Returns the number of rebate tier formulas.
    *
    * @return the number of rebate tier formulas
    * @throws SystemException if a system exception occurred
    */
    public int countAll()
        throws com.stpl.portal.kernel.exception.SystemException;
}
