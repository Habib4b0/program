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

package com.stpl.app.parttwo.service.persistence.impl;

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

import com.stpl.app.parttwo.exception.NoSuchVwItemIdentifierException;
import com.stpl.app.parttwo.model.VwItemIdentifier;
import com.stpl.app.parttwo.model.impl.VwItemIdentifierImpl;
import com.stpl.app.parttwo.model.impl.VwItemIdentifierModelImpl;
import com.stpl.app.parttwo.service.persistence.VwItemIdentifierPersistence;

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
 * The persistence implementation for the vw item identifier service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwItemIdentifierPersistence
 * @see com.stpl.app.parttwo.service.persistence.VwItemIdentifierUtil
 * @generated
 */
@ProviderType
public class VwItemIdentifierPersistenceImpl extends BasePersistenceImpl<VwItemIdentifier>
	implements VwItemIdentifierPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VwItemIdentifierUtil} to access the vw item identifier persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = VwItemIdentifierImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(VwItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
			VwItemIdentifierModelImpl.FINDER_CACHE_ENABLED,
			VwItemIdentifierImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(VwItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
			VwItemIdentifierModelImpl.FINDER_CACHE_ENABLED,
			VwItemIdentifierImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(VwItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
			VwItemIdentifierModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public VwItemIdentifierPersistenceImpl() {
		setModelClass(VwItemIdentifier.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("itemStatus", "ITEM_STATUS");
			dbColumnNames.put("itemIdentifierSid", "ITEM_IDENTIFIER_SID");
			dbColumnNames.put("endDate", "END_DATE");
			dbColumnNames.put("itemId", "ITEM_ID");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("entityCode", "ENTITY_CODE");
			dbColumnNames.put("startDate", "START_DATE");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("addChgDelIndicator", "ADD_CHG_DEL_INDICATOR");
			dbColumnNames.put("itemName", "ITEM_NAME");
			dbColumnNames.put("itemIdentifier", "ITEM_IDENTIFIER");
			dbColumnNames.put("identifierCodeQualifierName",
				"IDENTIFIER_CODE_QUALIFIER_NAME");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("itemNo", "ITEM_NO");
			dbColumnNames.put("identifierCodeQualifier",
				"IDENTIFIER_CODE_QUALIFIER");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the vw item identifier in the entity cache if it is enabled.
	 *
	 * @param vwItemIdentifier the vw item identifier
	 */
	@Override
	public void cacheResult(VwItemIdentifier vwItemIdentifier) {
		entityCache.putResult(VwItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
			VwItemIdentifierImpl.class, vwItemIdentifier.getPrimaryKey(),
			vwItemIdentifier);

		vwItemIdentifier.resetOriginalValues();
	}

	/**
	 * Caches the vw item identifiers in the entity cache if it is enabled.
	 *
	 * @param vwItemIdentifiers the vw item identifiers
	 */
	@Override
	public void cacheResult(List<VwItemIdentifier> vwItemIdentifiers) {
		for (VwItemIdentifier vwItemIdentifier : vwItemIdentifiers) {
			if (entityCache.getResult(
						VwItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
						VwItemIdentifierImpl.class,
						vwItemIdentifier.getPrimaryKey()) == null) {
				cacheResult(vwItemIdentifier);
			}
			else {
				vwItemIdentifier.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all vw item identifiers.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(VwItemIdentifierImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the vw item identifier.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(VwItemIdentifier vwItemIdentifier) {
		entityCache.removeResult(VwItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
			VwItemIdentifierImpl.class, vwItemIdentifier.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<VwItemIdentifier> vwItemIdentifiers) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (VwItemIdentifier vwItemIdentifier : vwItemIdentifiers) {
			entityCache.removeResult(VwItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
				VwItemIdentifierImpl.class, vwItemIdentifier.getPrimaryKey());
		}
	}

	/**
	 * Creates a new vw item identifier with the primary key. Does not add the vw item identifier to the database.
	 *
	 * @param itemIdentifierSid the primary key for the new vw item identifier
	 * @return the new vw item identifier
	 */
	@Override
	public VwItemIdentifier create(int itemIdentifierSid) {
		VwItemIdentifier vwItemIdentifier = new VwItemIdentifierImpl();

		vwItemIdentifier.setNew(true);
		vwItemIdentifier.setPrimaryKey(itemIdentifierSid);

		return vwItemIdentifier;
	}

	/**
	 * Removes the vw item identifier with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param itemIdentifierSid the primary key of the vw item identifier
	 * @return the vw item identifier that was removed
	 * @throws NoSuchVwItemIdentifierException if a vw item identifier with the primary key could not be found
	 */
	@Override
	public VwItemIdentifier remove(int itemIdentifierSid)
		throws NoSuchVwItemIdentifierException {
		return remove((Serializable)itemIdentifierSid);
	}

	/**
	 * Removes the vw item identifier with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vw item identifier
	 * @return the vw item identifier that was removed
	 * @throws NoSuchVwItemIdentifierException if a vw item identifier with the primary key could not be found
	 */
	@Override
	public VwItemIdentifier remove(Serializable primaryKey)
		throws NoSuchVwItemIdentifierException {
		Session session = null;

		try {
			session = openSession();

			VwItemIdentifier vwItemIdentifier = (VwItemIdentifier)session.get(VwItemIdentifierImpl.class,
					primaryKey);

			if (vwItemIdentifier == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVwItemIdentifierException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(vwItemIdentifier);
		}
		catch (NoSuchVwItemIdentifierException nsee) {
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
	protected VwItemIdentifier removeImpl(VwItemIdentifier vwItemIdentifier) {
		vwItemIdentifier = toUnwrappedModel(vwItemIdentifier);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(vwItemIdentifier)) {
				vwItemIdentifier = (VwItemIdentifier)session.get(VwItemIdentifierImpl.class,
						vwItemIdentifier.getPrimaryKeyObj());
			}

			if (vwItemIdentifier != null) {
				session.delete(vwItemIdentifier);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (vwItemIdentifier != null) {
			clearCache(vwItemIdentifier);
		}

		return vwItemIdentifier;
	}

	@Override
	public VwItemIdentifier updateImpl(VwItemIdentifier vwItemIdentifier) {
		vwItemIdentifier = toUnwrappedModel(vwItemIdentifier);

		boolean isNew = vwItemIdentifier.isNew();

		Session session = null;

		try {
			session = openSession();

			if (vwItemIdentifier.isNew()) {
				session.save(vwItemIdentifier);

				vwItemIdentifier.setNew(false);
			}
			else {
				vwItemIdentifier = (VwItemIdentifier)session.merge(vwItemIdentifier);
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

		entityCache.putResult(VwItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
			VwItemIdentifierImpl.class, vwItemIdentifier.getPrimaryKey(),
			vwItemIdentifier, false);

		vwItemIdentifier.resetOriginalValues();

		return vwItemIdentifier;
	}

	protected VwItemIdentifier toUnwrappedModel(
		VwItemIdentifier vwItemIdentifier) {
		if (vwItemIdentifier instanceof VwItemIdentifierImpl) {
			return vwItemIdentifier;
		}

		VwItemIdentifierImpl vwItemIdentifierImpl = new VwItemIdentifierImpl();

		vwItemIdentifierImpl.setNew(vwItemIdentifier.isNew());
		vwItemIdentifierImpl.setPrimaryKey(vwItemIdentifier.getPrimaryKey());

		vwItemIdentifierImpl.setItemStatus(vwItemIdentifier.getItemStatus());
		vwItemIdentifierImpl.setItemIdentifierSid(vwItemIdentifier.getItemIdentifierSid());
		vwItemIdentifierImpl.setEndDate(vwItemIdentifier.getEndDate());
		vwItemIdentifierImpl.setItemId(vwItemIdentifier.getItemId());
		vwItemIdentifierImpl.setModifiedDate(vwItemIdentifier.getModifiedDate());
		vwItemIdentifierImpl.setEntityCode(vwItemIdentifier.getEntityCode());
		vwItemIdentifierImpl.setStartDate(vwItemIdentifier.getStartDate());
		vwItemIdentifierImpl.setCreatedDate(vwItemIdentifier.getCreatedDate());
		vwItemIdentifierImpl.setCreatedBy(vwItemIdentifier.getCreatedBy());
		vwItemIdentifierImpl.setSource(vwItemIdentifier.getSource());
		vwItemIdentifierImpl.setBatchId(vwItemIdentifier.getBatchId());
		vwItemIdentifierImpl.setAddChgDelIndicator(vwItemIdentifier.getAddChgDelIndicator());
		vwItemIdentifierImpl.setItemName(vwItemIdentifier.getItemName());
		vwItemIdentifierImpl.setItemIdentifier(vwItemIdentifier.getItemIdentifier());
		vwItemIdentifierImpl.setIdentifierCodeQualifierName(vwItemIdentifier.getIdentifierCodeQualifierName());
		vwItemIdentifierImpl.setModifiedBy(vwItemIdentifier.getModifiedBy());
		vwItemIdentifierImpl.setItemNo(vwItemIdentifier.getItemNo());
		vwItemIdentifierImpl.setIdentifierCodeQualifier(vwItemIdentifier.getIdentifierCodeQualifier());

		return vwItemIdentifierImpl;
	}

	/**
	 * Returns the vw item identifier with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the vw item identifier
	 * @return the vw item identifier
	 * @throws NoSuchVwItemIdentifierException if a vw item identifier with the primary key could not be found
	 */
	@Override
	public VwItemIdentifier findByPrimaryKey(Serializable primaryKey)
		throws NoSuchVwItemIdentifierException {
		VwItemIdentifier vwItemIdentifier = fetchByPrimaryKey(primaryKey);

		if (vwItemIdentifier == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchVwItemIdentifierException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return vwItemIdentifier;
	}

	/**
	 * Returns the vw item identifier with the primary key or throws a {@link NoSuchVwItemIdentifierException} if it could not be found.
	 *
	 * @param itemIdentifierSid the primary key of the vw item identifier
	 * @return the vw item identifier
	 * @throws NoSuchVwItemIdentifierException if a vw item identifier with the primary key could not be found
	 */
	@Override
	public VwItemIdentifier findByPrimaryKey(int itemIdentifierSid)
		throws NoSuchVwItemIdentifierException {
		return findByPrimaryKey((Serializable)itemIdentifierSid);
	}

	/**
	 * Returns the vw item identifier with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vw item identifier
	 * @return the vw item identifier, or <code>null</code> if a vw item identifier with the primary key could not be found
	 */
	@Override
	public VwItemIdentifier fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(VwItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
				VwItemIdentifierImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		VwItemIdentifier vwItemIdentifier = (VwItemIdentifier)serializable;

		if (vwItemIdentifier == null) {
			Session session = null;

			try {
				session = openSession();

				vwItemIdentifier = (VwItemIdentifier)session.get(VwItemIdentifierImpl.class,
						primaryKey);

				if (vwItemIdentifier != null) {
					cacheResult(vwItemIdentifier);
				}
				else {
					entityCache.putResult(VwItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
						VwItemIdentifierImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(VwItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
					VwItemIdentifierImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return vwItemIdentifier;
	}

	/**
	 * Returns the vw item identifier with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param itemIdentifierSid the primary key of the vw item identifier
	 * @return the vw item identifier, or <code>null</code> if a vw item identifier with the primary key could not be found
	 */
	@Override
	public VwItemIdentifier fetchByPrimaryKey(int itemIdentifierSid) {
		return fetchByPrimaryKey((Serializable)itemIdentifierSid);
	}

	@Override
	public Map<Serializable, VwItemIdentifier> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, VwItemIdentifier> map = new HashMap<Serializable, VwItemIdentifier>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			VwItemIdentifier vwItemIdentifier = fetchByPrimaryKey(primaryKey);

			if (vwItemIdentifier != null) {
				map.put(primaryKey, vwItemIdentifier);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(VwItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
					VwItemIdentifierImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (VwItemIdentifier)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_VWITEMIDENTIFIER_WHERE_PKS_IN);

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

			for (VwItemIdentifier vwItemIdentifier : (List<VwItemIdentifier>)q.list()) {
				map.put(vwItemIdentifier.getPrimaryKeyObj(), vwItemIdentifier);

				cacheResult(vwItemIdentifier);

				uncachedPrimaryKeys.remove(vwItemIdentifier.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(VwItemIdentifierModelImpl.ENTITY_CACHE_ENABLED,
					VwItemIdentifierImpl.class, primaryKey, nullModel);
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
	 * Returns all the vw item identifiers.
	 *
	 * @return the vw item identifiers
	 */
	@Override
	public List<VwItemIdentifier> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vw item identifiers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of vw item identifiers
	 * @param end the upper bound of the range of vw item identifiers (not inclusive)
	 * @return the range of vw item identifiers
	 */
	@Override
	public List<VwItemIdentifier> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the vw item identifiers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of vw item identifiers
	 * @param end the upper bound of the range of vw item identifiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vw item identifiers
	 */
	@Override
	public List<VwItemIdentifier> findAll(int start, int end,
		OrderByComparator<VwItemIdentifier> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the vw item identifiers.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwItemIdentifierModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of vw item identifiers
	 * @param end the upper bound of the range of vw item identifiers (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of vw item identifiers
	 */
	@Override
	public List<VwItemIdentifier> findAll(int start, int end,
		OrderByComparator<VwItemIdentifier> orderByComparator,
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

		List<VwItemIdentifier> list = null;

		if (retrieveFromCache) {
			list = (List<VwItemIdentifier>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_VWITEMIDENTIFIER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VWITEMIDENTIFIER;

				if (pagination) {
					sql = sql.concat(VwItemIdentifierModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<VwItemIdentifier>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<VwItemIdentifier>)QueryUtil.list(q,
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
	 * Removes all the vw item identifiers from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (VwItemIdentifier vwItemIdentifier : findAll()) {
			remove(vwItemIdentifier);
		}
	}

	/**
	 * Returns the number of vw item identifiers.
	 *
	 * @return the number of vw item identifiers
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_VWITEMIDENTIFIER);

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
		return VwItemIdentifierModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the vw item identifier persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(VwItemIdentifierImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_VWITEMIDENTIFIER = "SELECT vwItemIdentifier FROM VwItemIdentifier vwItemIdentifier";
	private static final String _SQL_SELECT_VWITEMIDENTIFIER_WHERE_PKS_IN = "SELECT vwItemIdentifier FROM VwItemIdentifier vwItemIdentifier WHERE ITEM_IDENTIFIER_SID IN (";
	private static final String _SQL_COUNT_VWITEMIDENTIFIER = "SELECT COUNT(vwItemIdentifier) FROM VwItemIdentifier vwItemIdentifier";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vwItemIdentifier.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VwItemIdentifier exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(VwItemIdentifierPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"itemStatus", "itemIdentifierSid", "endDate", "itemId",
				"modifiedDate", "entityCode", "startDate", "createdDate",
				"createdBy", "source", "batchId", "addChgDelIndicator",
				"itemName", "itemIdentifier", "identifierCodeQualifierName",
				"modifiedBy", "itemNo", "identifierCodeQualifier"
			});
}