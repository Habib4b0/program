/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.stpl.app.service.persistence.impl;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.dao.orm.EntityCache;
import com.liferay.portal.kernel.dao.orm.FinderCache;
import com.liferay.portal.kernel.dao.orm.FinderPath;
import com.liferay.portal.kernel.dao.orm.Query;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.kernel.util.SetUtil;
import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.spring.extender.service.ServiceReference;

import com.stpl.app.exception.NoSuchSalesBasisDetailsException;
import com.stpl.app.model.SalesBasisDetails;
import com.stpl.app.model.impl.SalesBasisDetailsImpl;
import com.stpl.app.model.impl.SalesBasisDetailsModelImpl;
import com.stpl.app.service.persistence.SalesBasisDetailsPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the sales basis details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see SalesBasisDetailsPersistence
 * @see com.stpl.app.service.persistence.SalesBasisDetailsUtil
 * @generated
 */
@ProviderType
public class SalesBasisDetailsPersistenceImpl extends BasePersistenceImpl<SalesBasisDetails>
	implements SalesBasisDetailsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link SalesBasisDetailsUtil} to access the sales basis details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = SalesBasisDetailsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(SalesBasisDetailsModelImpl.ENTITY_CACHE_ENABLED,
			SalesBasisDetailsModelImpl.FINDER_CACHE_ENABLED,
			SalesBasisDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(SalesBasisDetailsModelImpl.ENTITY_CACHE_ENABLED,
			SalesBasisDetailsModelImpl.FINDER_CACHE_ENABLED,
			SalesBasisDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(SalesBasisDetailsModelImpl.ENTITY_CACHE_ENABLED,
			SalesBasisDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public SalesBasisDetailsPersistenceImpl() {
		setModelClass(SalesBasisDetails.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("netSalesFormulaMasterSid",
				"NET_SALES_FORMULA_MASTER_SID");
			dbColumnNames.put("recordLockStatus", "RECORD_LOCK_STATUS");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("contractMasterSid", "CONTRACT_MASTER_SID");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("cdrModelSid", "CDR_MODEL_SID");
			dbColumnNames.put("salesBasisDetailsSid", "SALES_BASIS_DETAILS_SID");
			dbColumnNames.put("cfpContractDetailsSid",
				"CFP_CONTRACT_DETAILS_SID");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("inboundStatus", "INBOUND_STATUS");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the sales basis details in the entity cache if it is enabled.
	 *
	 * @param salesBasisDetails the sales basis details
	 */
	@Override
	public void cacheResult(SalesBasisDetails salesBasisDetails) {
		entityCache.putResult(SalesBasisDetailsModelImpl.ENTITY_CACHE_ENABLED,
			SalesBasisDetailsImpl.class, salesBasisDetails.getPrimaryKey(),
			salesBasisDetails);

		salesBasisDetails.resetOriginalValues();
	}

	/**
	 * Caches the sales basis detailses in the entity cache if it is enabled.
	 *
	 * @param salesBasisDetailses the sales basis detailses
	 */
	@Override
	public void cacheResult(List<SalesBasisDetails> salesBasisDetailses) {
		for (SalesBasisDetails salesBasisDetails : salesBasisDetailses) {
			if (entityCache.getResult(
						SalesBasisDetailsModelImpl.ENTITY_CACHE_ENABLED,
						SalesBasisDetailsImpl.class,
						salesBasisDetails.getPrimaryKey()) == null) {
				cacheResult(salesBasisDetails);
			}
			else {
				salesBasisDetails.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all sales basis detailses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(SalesBasisDetailsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the sales basis details.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(SalesBasisDetails salesBasisDetails) {
		entityCache.removeResult(SalesBasisDetailsModelImpl.ENTITY_CACHE_ENABLED,
			SalesBasisDetailsImpl.class, salesBasisDetails.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<SalesBasisDetails> salesBasisDetailses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (SalesBasisDetails salesBasisDetails : salesBasisDetailses) {
			entityCache.removeResult(SalesBasisDetailsModelImpl.ENTITY_CACHE_ENABLED,
				SalesBasisDetailsImpl.class, salesBasisDetails.getPrimaryKey());
		}
	}

	/**
	 * Creates a new sales basis details with the primary key. Does not add the sales basis details to the database.
	 *
	 * @param salesBasisDetailsSid the primary key for the new sales basis details
	 * @return the new sales basis details
	 */
	@Override
	public SalesBasisDetails create(int salesBasisDetailsSid) {
		SalesBasisDetails salesBasisDetails = new SalesBasisDetailsImpl();

		salesBasisDetails.setNew(true);
		salesBasisDetails.setPrimaryKey(salesBasisDetailsSid);

		return salesBasisDetails;
	}

	/**
	 * Removes the sales basis details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param salesBasisDetailsSid the primary key of the sales basis details
	 * @return the sales basis details that was removed
	 * @throws NoSuchSalesBasisDetailsException if a sales basis details with the primary key could not be found
	 */
	@Override
	public SalesBasisDetails remove(int salesBasisDetailsSid)
		throws NoSuchSalesBasisDetailsException {
		return remove((Serializable)salesBasisDetailsSid);
	}

	/**
	 * Removes the sales basis details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the sales basis details
	 * @return the sales basis details that was removed
	 * @throws NoSuchSalesBasisDetailsException if a sales basis details with the primary key could not be found
	 */
	@Override
	public SalesBasisDetails remove(Serializable primaryKey)
		throws NoSuchSalesBasisDetailsException {
		Session session = null;

		try {
			session = openSession();

			SalesBasisDetails salesBasisDetails = (SalesBasisDetails)session.get(SalesBasisDetailsImpl.class,
					primaryKey);

			if (salesBasisDetails == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchSalesBasisDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(salesBasisDetails);
		}
		catch (NoSuchSalesBasisDetailsException nsee) {
			throw nsee;
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}
	}

	@Override
	protected SalesBasisDetails removeImpl(SalesBasisDetails salesBasisDetails) {
		salesBasisDetails = toUnwrappedModel(salesBasisDetails);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(salesBasisDetails)) {
				salesBasisDetails = (SalesBasisDetails)session.get(SalesBasisDetailsImpl.class,
						salesBasisDetails.getPrimaryKeyObj());
			}

			if (salesBasisDetails != null) {
				session.delete(salesBasisDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (salesBasisDetails != null) {
			clearCache(salesBasisDetails);
		}

		return salesBasisDetails;
	}

	@Override
	public SalesBasisDetails updateImpl(SalesBasisDetails salesBasisDetails) {
		salesBasisDetails = toUnwrappedModel(salesBasisDetails);

		boolean isNew = salesBasisDetails.isNew();

		Session session = null;

		try {
			session = openSession();

			if (salesBasisDetails.isNew()) {
				session.save(salesBasisDetails);

				salesBasisDetails.setNew(false);
			}
			else {
				salesBasisDetails = (SalesBasisDetails)session.merge(salesBasisDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);

		if (isNew) {
			finderCache.removeResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY);
			finderCache.removeResult(FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL,
				FINDER_ARGS_EMPTY);
		}

		entityCache.putResult(SalesBasisDetailsModelImpl.ENTITY_CACHE_ENABLED,
			SalesBasisDetailsImpl.class, salesBasisDetails.getPrimaryKey(),
			salesBasisDetails, false);

		salesBasisDetails.resetOriginalValues();

		return salesBasisDetails;
	}

	protected SalesBasisDetails toUnwrappedModel(
		SalesBasisDetails salesBasisDetails) {
		if (salesBasisDetails instanceof SalesBasisDetailsImpl) {
			return salesBasisDetails;
		}

		SalesBasisDetailsImpl salesBasisDetailsImpl = new SalesBasisDetailsImpl();

		salesBasisDetailsImpl.setNew(salesBasisDetails.isNew());
		salesBasisDetailsImpl.setPrimaryKey(salesBasisDetails.getPrimaryKey());

		salesBasisDetailsImpl.setCreatedBy(salesBasisDetails.getCreatedBy());
		salesBasisDetailsImpl.setNetSalesFormulaMasterSid(salesBasisDetails.getNetSalesFormulaMasterSid());
		salesBasisDetailsImpl.setRecordLockStatus(salesBasisDetails.isRecordLockStatus());
		salesBasisDetailsImpl.setModifiedBy(salesBasisDetails.getModifiedBy());
		salesBasisDetailsImpl.setCreatedDate(salesBasisDetails.getCreatedDate());
		salesBasisDetailsImpl.setContractMasterSid(salesBasisDetails.getContractMasterSid());
		salesBasisDetailsImpl.setSource(salesBasisDetails.getSource());
		salesBasisDetailsImpl.setCdrModelSid(salesBasisDetails.getCdrModelSid());
		salesBasisDetailsImpl.setSalesBasisDetailsSid(salesBasisDetails.getSalesBasisDetailsSid());
		salesBasisDetailsImpl.setCfpContractDetailsSid(salesBasisDetails.getCfpContractDetailsSid());
		salesBasisDetailsImpl.setModifiedDate(salesBasisDetails.getModifiedDate());
		salesBasisDetailsImpl.setInboundStatus(salesBasisDetails.getInboundStatus());

		return salesBasisDetailsImpl;
	}

	/**
	 * Returns the sales basis details with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the sales basis details
	 * @return the sales basis details
	 * @throws NoSuchSalesBasisDetailsException if a sales basis details with the primary key could not be found
	 */
	@Override
	public SalesBasisDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchSalesBasisDetailsException {
		SalesBasisDetails salesBasisDetails = fetchByPrimaryKey(primaryKey);

		if (salesBasisDetails == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchSalesBasisDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return salesBasisDetails;
	}

	/**
	 * Returns the sales basis details with the primary key or throws a {@link NoSuchSalesBasisDetailsException} if it could not be found.
	 *
	 * @param salesBasisDetailsSid the primary key of the sales basis details
	 * @return the sales basis details
	 * @throws NoSuchSalesBasisDetailsException if a sales basis details with the primary key could not be found
	 */
	@Override
	public SalesBasisDetails findByPrimaryKey(int salesBasisDetailsSid)
		throws NoSuchSalesBasisDetailsException {
		return findByPrimaryKey((Serializable)salesBasisDetailsSid);
	}

	/**
	 * Returns the sales basis details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the sales basis details
	 * @return the sales basis details, or <code>null</code> if a sales basis details with the primary key could not be found
	 */
	@Override
	public SalesBasisDetails fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(SalesBasisDetailsModelImpl.ENTITY_CACHE_ENABLED,
				SalesBasisDetailsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		SalesBasisDetails salesBasisDetails = (SalesBasisDetails)serializable;

		if (salesBasisDetails == null) {
			Session session = null;

			try {
				session = openSession();

				salesBasisDetails = (SalesBasisDetails)session.get(SalesBasisDetailsImpl.class,
						primaryKey);

				if (salesBasisDetails != null) {
					cacheResult(salesBasisDetails);
				}
				else {
					entityCache.putResult(SalesBasisDetailsModelImpl.ENTITY_CACHE_ENABLED,
						SalesBasisDetailsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(SalesBasisDetailsModelImpl.ENTITY_CACHE_ENABLED,
					SalesBasisDetailsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return salesBasisDetails;
	}

	/**
	 * Returns the sales basis details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param salesBasisDetailsSid the primary key of the sales basis details
	 * @return the sales basis details, or <code>null</code> if a sales basis details with the primary key could not be found
	 */
	@Override
	public SalesBasisDetails fetchByPrimaryKey(int salesBasisDetailsSid) {
		return fetchByPrimaryKey((Serializable)salesBasisDetailsSid);
	}

	@Override
	public Map<Serializable, SalesBasisDetails> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, SalesBasisDetails> map = new HashMap<Serializable, SalesBasisDetails>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			SalesBasisDetails salesBasisDetails = fetchByPrimaryKey(primaryKey);

			if (salesBasisDetails != null) {
				map.put(primaryKey, salesBasisDetails);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(SalesBasisDetailsModelImpl.ENTITY_CACHE_ENABLED,
					SalesBasisDetailsImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (SalesBasisDetails)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_SALESBASISDETAILS_WHERE_PKS_IN);

		for (Serializable primaryKey : uncachedPrimaryKeys) {
			query.append((int)primaryKey);

			query.append(StringPool.COMMA);
		}

		query.setIndex(query.index() - 1);

		query.append(StringPool.CLOSE_PARENTHESIS);

		String sql = query.toString();

		Session session = null;

		try {
			session = openSession();

			Query q = session.createQuery(sql);

			for (SalesBasisDetails salesBasisDetails : (List<SalesBasisDetails>)q.list()) {
				map.put(salesBasisDetails.getPrimaryKeyObj(), salesBasisDetails);

				cacheResult(salesBasisDetails);

				uncachedPrimaryKeys.remove(salesBasisDetails.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(SalesBasisDetailsModelImpl.ENTITY_CACHE_ENABLED,
					SalesBasisDetailsImpl.class, primaryKey, nullModel);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		return map;
	}

	/**
	 * Returns all the sales basis detailses.
	 *
	 * @return the sales basis detailses
	 */
	@Override
	public List<SalesBasisDetails> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the sales basis detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesBasisDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of sales basis detailses
	 * @param end the upper bound of the range of sales basis detailses (not inclusive)
	 * @return the range of sales basis detailses
	 */
	@Override
	public List<SalesBasisDetails> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the sales basis detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesBasisDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of sales basis detailses
	 * @param end the upper bound of the range of sales basis detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of sales basis detailses
	 */
	@Override
	public List<SalesBasisDetails> findAll(int start, int end,
		OrderByComparator<SalesBasisDetails> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the sales basis detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link SalesBasisDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of sales basis detailses
	 * @param end the upper bound of the range of sales basis detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of sales basis detailses
	 */
	@Override
	public List<SalesBasisDetails> findAll(int start, int end,
		OrderByComparator<SalesBasisDetails> orderByComparator,
		boolean retrieveFromCache) {
		boolean pagination = true;
		FinderPath finderPath = null;
		Object[] finderArgs = null;

		if ((start == QueryUtil.ALL_POS) && (end == QueryUtil.ALL_POS) &&
				(orderByComparator == null)) {
			pagination = false;
			finderPath = FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL;
			finderArgs = FINDER_ARGS_EMPTY;
		}
		else {
			finderPath = FINDER_PATH_WITH_PAGINATION_FIND_ALL;
			finderArgs = new Object[] { start, end, orderByComparator };
		}

		List<SalesBasisDetails> list = null;

		if (retrieveFromCache) {
			list = (List<SalesBasisDetails>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_SALESBASISDETAILS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_SALESBASISDETAILS;

				if (pagination) {
					sql = sql.concat(SalesBasisDetailsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<SalesBasisDetails>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<SalesBasisDetails>)QueryUtil.list(q,
							getDialect(), start, end);
				}

				cacheResult(list);

				finderCache.putResult(finderPath, finderArgs, list);
			}
			catch (Exception e) {
				finderCache.removeResult(finderPath, finderArgs);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return list;
	}

	/**
	 * Removes all the sales basis detailses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (SalesBasisDetails salesBasisDetails : findAll()) {
			remove(salesBasisDetails);
		}
	}

	/**
	 * Returns the number of sales basis detailses.
	 *
	 * @return the number of sales basis detailses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_SALESBASISDETAILS);

				count = (Long)q.uniqueResult();

				finderCache.putResult(FINDER_PATH_COUNT_ALL, FINDER_ARGS_EMPTY,
					count);
			}
			catch (Exception e) {
				finderCache.removeResult(FINDER_PATH_COUNT_ALL,
					FINDER_ARGS_EMPTY);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return count.intValue();
	}

	@Override
	public Set<String> getBadColumnNames() {
		return _badColumnNames;
	}

	@Override
	protected Map<String, Integer> getTableColumnsMap() {
		return SalesBasisDetailsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the sales basis details persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(SalesBasisDetailsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_SALESBASISDETAILS = "SELECT salesBasisDetails FROM SalesBasisDetails salesBasisDetails";
	private static final String _SQL_SELECT_SALESBASISDETAILS_WHERE_PKS_IN = "SELECT salesBasisDetails FROM SalesBasisDetails salesBasisDetails WHERE SALES_BASIS_DETAILS_SID IN (";
	private static final String _SQL_COUNT_SALESBASISDETAILS = "SELECT COUNT(salesBasisDetails) FROM SalesBasisDetails salesBasisDetails";
	private static final String _ORDER_BY_ENTITY_ALIAS = "salesBasisDetails.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No SalesBasisDetails exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(SalesBasisDetailsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"createdBy", "netSalesFormulaMasterSid", "recordLockStatus",
				"modifiedBy", "createdDate", "contractMasterSid", "source",
				"cdrModelSid", "salesBasisDetailsSid", "cfpContractDetailsSid",
				"modifiedDate", "inboundStatus"
			});
}