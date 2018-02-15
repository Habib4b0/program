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
import com.liferay.portal.spring.extender.service.ServiceReference;

import com.stpl.app.exception.NoSuchHistItemGroupDetailsException;
import com.stpl.app.model.HistItemGroupDetails;
import com.stpl.app.model.impl.HistItemGroupDetailsImpl;
import com.stpl.app.model.impl.HistItemGroupDetailsModelImpl;
import com.stpl.app.service.persistence.HistItemGroupDetailsPK;
import com.stpl.app.service.persistence.HistItemGroupDetailsPersistence;

import java.io.Serializable;

import java.lang.reflect.Field;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * The persistence implementation for the hist item group details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see HistItemGroupDetailsPersistence
 * @see com.stpl.app.service.persistence.HistItemGroupDetailsUtil
 * @generated
 */
@ProviderType
public class HistItemGroupDetailsPersistenceImpl extends BasePersistenceImpl<HistItemGroupDetails>
	implements HistItemGroupDetailsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link HistItemGroupDetailsUtil} to access the hist item group details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = HistItemGroupDetailsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(HistItemGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
			HistItemGroupDetailsModelImpl.FINDER_CACHE_ENABLED,
			HistItemGroupDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(HistItemGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
			HistItemGroupDetailsModelImpl.FINDER_CACHE_ENABLED,
			HistItemGroupDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(HistItemGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
			HistItemGroupDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public HistItemGroupDetailsPersistenceImpl() {
		setModelClass(HistItemGroupDetails.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("itemGroupDetailsSid", "ITEM_GROUP_DETAILS_SID");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("actionDate", "ACTION_DATE");
			dbColumnNames.put("itemMasterSid", "ITEM_MASTER_SID");
			dbColumnNames.put("actionFlag", "ACTION_FLAG");
			dbColumnNames.put("versionNo", "VERSION_NO");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("itemGroupSid", "ITEM_GROUP_SID");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the hist item group details in the entity cache if it is enabled.
	 *
	 * @param histItemGroupDetails the hist item group details
	 */
	@Override
	public void cacheResult(HistItemGroupDetails histItemGroupDetails) {
		entityCache.putResult(HistItemGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
			HistItemGroupDetailsImpl.class,
			histItemGroupDetails.getPrimaryKey(), histItemGroupDetails);

		histItemGroupDetails.resetOriginalValues();
	}

	/**
	 * Caches the hist item group detailses in the entity cache if it is enabled.
	 *
	 * @param histItemGroupDetailses the hist item group detailses
	 */
	@Override
	public void cacheResult(List<HistItemGroupDetails> histItemGroupDetailses) {
		for (HistItemGroupDetails histItemGroupDetails : histItemGroupDetailses) {
			if (entityCache.getResult(
						HistItemGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
						HistItemGroupDetailsImpl.class,
						histItemGroupDetails.getPrimaryKey()) == null) {
				cacheResult(histItemGroupDetails);
			}
			else {
				histItemGroupDetails.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all hist item group detailses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(HistItemGroupDetailsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the hist item group details.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(HistItemGroupDetails histItemGroupDetails) {
		entityCache.removeResult(HistItemGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
			HistItemGroupDetailsImpl.class, histItemGroupDetails.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<HistItemGroupDetails> histItemGroupDetailses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (HistItemGroupDetails histItemGroupDetails : histItemGroupDetailses) {
			entityCache.removeResult(HistItemGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
				HistItemGroupDetailsImpl.class,
				histItemGroupDetails.getPrimaryKey());
		}
	}

	/**
	 * Creates a new hist item group details with the primary key. Does not add the hist item group details to the database.
	 *
	 * @param histItemGroupDetailsPK the primary key for the new hist item group details
	 * @return the new hist item group details
	 */
	@Override
	public HistItemGroupDetails create(
		HistItemGroupDetailsPK histItemGroupDetailsPK) {
		HistItemGroupDetails histItemGroupDetails = new HistItemGroupDetailsImpl();

		histItemGroupDetails.setNew(true);
		histItemGroupDetails.setPrimaryKey(histItemGroupDetailsPK);

		return histItemGroupDetails;
	}

	/**
	 * Removes the hist item group details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param histItemGroupDetailsPK the primary key of the hist item group details
	 * @return the hist item group details that was removed
	 * @throws NoSuchHistItemGroupDetailsException if a hist item group details with the primary key could not be found
	 */
	@Override
	public HistItemGroupDetails remove(
		HistItemGroupDetailsPK histItemGroupDetailsPK)
		throws NoSuchHistItemGroupDetailsException {
		return remove((Serializable)histItemGroupDetailsPK);
	}

	/**
	 * Removes the hist item group details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the hist item group details
	 * @return the hist item group details that was removed
	 * @throws NoSuchHistItemGroupDetailsException if a hist item group details with the primary key could not be found
	 */
	@Override
	public HistItemGroupDetails remove(Serializable primaryKey)
		throws NoSuchHistItemGroupDetailsException {
		Session session = null;

		try {
			session = openSession();

			HistItemGroupDetails histItemGroupDetails = (HistItemGroupDetails)session.get(HistItemGroupDetailsImpl.class,
					primaryKey);

			if (histItemGroupDetails == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchHistItemGroupDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(histItemGroupDetails);
		}
		catch (NoSuchHistItemGroupDetailsException nsee) {
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
	protected HistItemGroupDetails removeImpl(
		HistItemGroupDetails histItemGroupDetails) {
		histItemGroupDetails = toUnwrappedModel(histItemGroupDetails);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(histItemGroupDetails)) {
				histItemGroupDetails = (HistItemGroupDetails)session.get(HistItemGroupDetailsImpl.class,
						histItemGroupDetails.getPrimaryKeyObj());
			}

			if (histItemGroupDetails != null) {
				session.delete(histItemGroupDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (histItemGroupDetails != null) {
			clearCache(histItemGroupDetails);
		}

		return histItemGroupDetails;
	}

	@Override
	public HistItemGroupDetails updateImpl(
		HistItemGroupDetails histItemGroupDetails) {
		histItemGroupDetails = toUnwrappedModel(histItemGroupDetails);

		boolean isNew = histItemGroupDetails.isNew();

		Session session = null;

		try {
			session = openSession();

			if (histItemGroupDetails.isNew()) {
				session.save(histItemGroupDetails);

				histItemGroupDetails.setNew(false);
			}
			else {
				histItemGroupDetails = (HistItemGroupDetails)session.merge(histItemGroupDetails);
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

		entityCache.putResult(HistItemGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
			HistItemGroupDetailsImpl.class,
			histItemGroupDetails.getPrimaryKey(), histItemGroupDetails, false);

		histItemGroupDetails.resetOriginalValues();

		return histItemGroupDetails;
	}

	protected HistItemGroupDetails toUnwrappedModel(
		HistItemGroupDetails histItemGroupDetails) {
		if (histItemGroupDetails instanceof HistItemGroupDetailsImpl) {
			return histItemGroupDetails;
		}

		HistItemGroupDetailsImpl histItemGroupDetailsImpl = new HistItemGroupDetailsImpl();

		histItemGroupDetailsImpl.setNew(histItemGroupDetails.isNew());
		histItemGroupDetailsImpl.setPrimaryKey(histItemGroupDetails.getPrimaryKey());

		histItemGroupDetailsImpl.setItemGroupDetailsSid(histItemGroupDetails.getItemGroupDetailsSid());
		histItemGroupDetailsImpl.setCreatedDate(histItemGroupDetails.getCreatedDate());
		histItemGroupDetailsImpl.setCreatedBy(histItemGroupDetails.getCreatedBy());
		histItemGroupDetailsImpl.setActionDate(histItemGroupDetails.getActionDate());
		histItemGroupDetailsImpl.setItemMasterSid(histItemGroupDetails.getItemMasterSid());
		histItemGroupDetailsImpl.setActionFlag(histItemGroupDetails.getActionFlag());
		histItemGroupDetailsImpl.setVersionNo(histItemGroupDetails.getVersionNo());
		histItemGroupDetailsImpl.setModifiedBy(histItemGroupDetails.getModifiedBy());
		histItemGroupDetailsImpl.setModifiedDate(histItemGroupDetails.getModifiedDate());
		histItemGroupDetailsImpl.setItemGroupSid(histItemGroupDetails.getItemGroupSid());

		return histItemGroupDetailsImpl;
	}

	/**
	 * Returns the hist item group details with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the hist item group details
	 * @return the hist item group details
	 * @throws NoSuchHistItemGroupDetailsException if a hist item group details with the primary key could not be found
	 */
	@Override
	public HistItemGroupDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchHistItemGroupDetailsException {
		HistItemGroupDetails histItemGroupDetails = fetchByPrimaryKey(primaryKey);

		if (histItemGroupDetails == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchHistItemGroupDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return histItemGroupDetails;
	}

	/**
	 * Returns the hist item group details with the primary key or throws a {@link NoSuchHistItemGroupDetailsException} if it could not be found.
	 *
	 * @param histItemGroupDetailsPK the primary key of the hist item group details
	 * @return the hist item group details
	 * @throws NoSuchHistItemGroupDetailsException if a hist item group details with the primary key could not be found
	 */
	@Override
	public HistItemGroupDetails findByPrimaryKey(
		HistItemGroupDetailsPK histItemGroupDetailsPK)
		throws NoSuchHistItemGroupDetailsException {
		return findByPrimaryKey((Serializable)histItemGroupDetailsPK);
	}

	/**
	 * Returns the hist item group details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the hist item group details
	 * @return the hist item group details, or <code>null</code> if a hist item group details with the primary key could not be found
	 */
	@Override
	public HistItemGroupDetails fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(HistItemGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
				HistItemGroupDetailsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		HistItemGroupDetails histItemGroupDetails = (HistItemGroupDetails)serializable;

		if (histItemGroupDetails == null) {
			Session session = null;

			try {
				session = openSession();

				histItemGroupDetails = (HistItemGroupDetails)session.get(HistItemGroupDetailsImpl.class,
						primaryKey);

				if (histItemGroupDetails != null) {
					cacheResult(histItemGroupDetails);
				}
				else {
					entityCache.putResult(HistItemGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
						HistItemGroupDetailsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(HistItemGroupDetailsModelImpl.ENTITY_CACHE_ENABLED,
					HistItemGroupDetailsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return histItemGroupDetails;
	}

	/**
	 * Returns the hist item group details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param histItemGroupDetailsPK the primary key of the hist item group details
	 * @return the hist item group details, or <code>null</code> if a hist item group details with the primary key could not be found
	 */
	@Override
	public HistItemGroupDetails fetchByPrimaryKey(
		HistItemGroupDetailsPK histItemGroupDetailsPK) {
		return fetchByPrimaryKey((Serializable)histItemGroupDetailsPK);
	}

	@Override
	public Map<Serializable, HistItemGroupDetails> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, HistItemGroupDetails> map = new HashMap<Serializable, HistItemGroupDetails>();

		for (Serializable primaryKey : primaryKeys) {
			HistItemGroupDetails histItemGroupDetails = fetchByPrimaryKey(primaryKey);

			if (histItemGroupDetails != null) {
				map.put(primaryKey, histItemGroupDetails);
			}
		}

		return map;
	}

	/**
	 * Returns all the hist item group detailses.
	 *
	 * @return the hist item group detailses
	 */
	@Override
	public List<HistItemGroupDetails> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the hist item group detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistItemGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of hist item group detailses
	 * @param end the upper bound of the range of hist item group detailses (not inclusive)
	 * @return the range of hist item group detailses
	 */
	@Override
	public List<HistItemGroupDetails> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the hist item group detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistItemGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of hist item group detailses
	 * @param end the upper bound of the range of hist item group detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of hist item group detailses
	 */
	@Override
	public List<HistItemGroupDetails> findAll(int start, int end,
		OrderByComparator<HistItemGroupDetails> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the hist item group detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link HistItemGroupDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of hist item group detailses
	 * @param end the upper bound of the range of hist item group detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of hist item group detailses
	 */
	@Override
	public List<HistItemGroupDetails> findAll(int start, int end,
		OrderByComparator<HistItemGroupDetails> orderByComparator,
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

		List<HistItemGroupDetails> list = null;

		if (retrieveFromCache) {
			list = (List<HistItemGroupDetails>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_HISTITEMGROUPDETAILS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_HISTITEMGROUPDETAILS;

				if (pagination) {
					sql = sql.concat(HistItemGroupDetailsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<HistItemGroupDetails>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<HistItemGroupDetails>)QueryUtil.list(q,
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
	 * Removes all the hist item group detailses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (HistItemGroupDetails histItemGroupDetails : findAll()) {
			remove(histItemGroupDetails);
		}
	}

	/**
	 * Returns the number of hist item group detailses.
	 *
	 * @return the number of hist item group detailses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_HISTITEMGROUPDETAILS);

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
		return HistItemGroupDetailsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the hist item group details persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(HistItemGroupDetailsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_HISTITEMGROUPDETAILS = "SELECT histItemGroupDetails FROM HistItemGroupDetails histItemGroupDetails";
	private static final String _SQL_COUNT_HISTITEMGROUPDETAILS = "SELECT COUNT(histItemGroupDetails) FROM HistItemGroupDetails histItemGroupDetails";
	private static final String _ORDER_BY_ENTITY_ALIAS = "histItemGroupDetails.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No HistItemGroupDetails exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(HistItemGroupDetailsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"itemGroupDetailsSid", "createdDate", "createdBy", "actionDate",
				"itemMasterSid", "actionFlag", "versionNo", "modifiedBy",
				"modifiedDate", "itemGroupSid"
			});
}