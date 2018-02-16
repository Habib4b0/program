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

import com.stpl.app.exception.NoSuchImtdRsDetailsFrException;
import com.stpl.app.model.ImtdRsDetailsFr;
import com.stpl.app.model.impl.ImtdRsDetailsFrImpl;
import com.stpl.app.model.impl.ImtdRsDetailsFrModelImpl;
import com.stpl.app.service.persistence.ImtdRsDetailsFrPersistence;

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
 * The persistence implementation for the imtd rs details fr service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ImtdRsDetailsFrPersistence
 * @see com.stpl.app.service.persistence.ImtdRsDetailsFrUtil
 * @generated
 */
@ProviderType
public class ImtdRsDetailsFrPersistenceImpl extends BasePersistenceImpl<ImtdRsDetailsFr>
	implements ImtdRsDetailsFrPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ImtdRsDetailsFrUtil} to access the imtd rs details fr persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ImtdRsDetailsFrImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ImtdRsDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
			ImtdRsDetailsFrModelImpl.FINDER_CACHE_ENABLED,
			ImtdRsDetailsFrImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ImtdRsDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
			ImtdRsDetailsFrModelImpl.FINDER_CACHE_ENABLED,
			ImtdRsDetailsFrImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ImtdRsDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
			ImtdRsDetailsFrModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public ImtdRsDetailsFrPersistenceImpl() {
		setModelClass(ImtdRsDetailsFr.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("formulaMethodId", "FORMULA_METHOD_ID");
			dbColumnNames.put("itemMasterSid", "ITEM_MASTER_SID");
			dbColumnNames.put("imtdRsDetailsSid", "IMTD_RS_DETAILS_SID");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("rsDetailsFrSid", "RS_DETAILS_FR_SID");
			dbColumnNames.put("recordLockStatus", "RECORD_LOCK_STATUS");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("imtdRsDetailsFrSid", "IMTD_RS_DETAILS_FR_SID");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("imtdCreatedDate", "IMTD_CREATED_DATE");
			dbColumnNames.put("sessionId", "SESSION_ID");
			dbColumnNames.put("usersId", "USERS_ID");
			dbColumnNames.put("operation", "OPERATION");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("rsDetailsSid", "RS_DETAILS_SID");
			dbColumnNames.put("formulaId", "FORMULA_ID");
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
	 * Caches the imtd rs details fr in the entity cache if it is enabled.
	 *
	 * @param imtdRsDetailsFr the imtd rs details fr
	 */
	@Override
	public void cacheResult(ImtdRsDetailsFr imtdRsDetailsFr) {
		entityCache.putResult(ImtdRsDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
			ImtdRsDetailsFrImpl.class, imtdRsDetailsFr.getPrimaryKey(),
			imtdRsDetailsFr);

		imtdRsDetailsFr.resetOriginalValues();
	}

	/**
	 * Caches the imtd rs details frs in the entity cache if it is enabled.
	 *
	 * @param imtdRsDetailsFrs the imtd rs details frs
	 */
	@Override
	public void cacheResult(List<ImtdRsDetailsFr> imtdRsDetailsFrs) {
		for (ImtdRsDetailsFr imtdRsDetailsFr : imtdRsDetailsFrs) {
			if (entityCache.getResult(
						ImtdRsDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
						ImtdRsDetailsFrImpl.class,
						imtdRsDetailsFr.getPrimaryKey()) == null) {
				cacheResult(imtdRsDetailsFr);
			}
			else {
				imtdRsDetailsFr.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all imtd rs details frs.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ImtdRsDetailsFrImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the imtd rs details fr.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ImtdRsDetailsFr imtdRsDetailsFr) {
		entityCache.removeResult(ImtdRsDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
			ImtdRsDetailsFrImpl.class, imtdRsDetailsFr.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ImtdRsDetailsFr> imtdRsDetailsFrs) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ImtdRsDetailsFr imtdRsDetailsFr : imtdRsDetailsFrs) {
			entityCache.removeResult(ImtdRsDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
				ImtdRsDetailsFrImpl.class, imtdRsDetailsFr.getPrimaryKey());
		}
	}

	/**
	 * Creates a new imtd rs details fr with the primary key. Does not add the imtd rs details fr to the database.
	 *
	 * @param imtdRsDetailsFrSid the primary key for the new imtd rs details fr
	 * @return the new imtd rs details fr
	 */
	@Override
	public ImtdRsDetailsFr create(int imtdRsDetailsFrSid) {
		ImtdRsDetailsFr imtdRsDetailsFr = new ImtdRsDetailsFrImpl();

		imtdRsDetailsFr.setNew(true);
		imtdRsDetailsFr.setPrimaryKey(imtdRsDetailsFrSid);

		return imtdRsDetailsFr;
	}

	/**
	 * Removes the imtd rs details fr with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param imtdRsDetailsFrSid the primary key of the imtd rs details fr
	 * @return the imtd rs details fr that was removed
	 * @throws NoSuchImtdRsDetailsFrException if a imtd rs details fr with the primary key could not be found
	 */
	@Override
	public ImtdRsDetailsFr remove(int imtdRsDetailsFrSid)
		throws NoSuchImtdRsDetailsFrException {
		return remove((Serializable)imtdRsDetailsFrSid);
	}

	/**
	 * Removes the imtd rs details fr with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the imtd rs details fr
	 * @return the imtd rs details fr that was removed
	 * @throws NoSuchImtdRsDetailsFrException if a imtd rs details fr with the primary key could not be found
	 */
	@Override
	public ImtdRsDetailsFr remove(Serializable primaryKey)
		throws NoSuchImtdRsDetailsFrException {
		Session session = null;

		try {
			session = openSession();

			ImtdRsDetailsFr imtdRsDetailsFr = (ImtdRsDetailsFr)session.get(ImtdRsDetailsFrImpl.class,
					primaryKey);

			if (imtdRsDetailsFr == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchImtdRsDetailsFrException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(imtdRsDetailsFr);
		}
		catch (NoSuchImtdRsDetailsFrException nsee) {
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
	protected ImtdRsDetailsFr removeImpl(ImtdRsDetailsFr imtdRsDetailsFr) {
		imtdRsDetailsFr = toUnwrappedModel(imtdRsDetailsFr);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(imtdRsDetailsFr)) {
				imtdRsDetailsFr = (ImtdRsDetailsFr)session.get(ImtdRsDetailsFrImpl.class,
						imtdRsDetailsFr.getPrimaryKeyObj());
			}

			if (imtdRsDetailsFr != null) {
				session.delete(imtdRsDetailsFr);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (imtdRsDetailsFr != null) {
			clearCache(imtdRsDetailsFr);
		}

		return imtdRsDetailsFr;
	}

	@Override
	public ImtdRsDetailsFr updateImpl(ImtdRsDetailsFr imtdRsDetailsFr) {
		imtdRsDetailsFr = toUnwrappedModel(imtdRsDetailsFr);

		boolean isNew = imtdRsDetailsFr.isNew();

		Session session = null;

		try {
			session = openSession();

			if (imtdRsDetailsFr.isNew()) {
				session.save(imtdRsDetailsFr);

				imtdRsDetailsFr.setNew(false);
			}
			else {
				imtdRsDetailsFr = (ImtdRsDetailsFr)session.merge(imtdRsDetailsFr);
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

		entityCache.putResult(ImtdRsDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
			ImtdRsDetailsFrImpl.class, imtdRsDetailsFr.getPrimaryKey(),
			imtdRsDetailsFr, false);

		imtdRsDetailsFr.resetOriginalValues();

		return imtdRsDetailsFr;
	}

	protected ImtdRsDetailsFr toUnwrappedModel(ImtdRsDetailsFr imtdRsDetailsFr) {
		if (imtdRsDetailsFr instanceof ImtdRsDetailsFrImpl) {
			return imtdRsDetailsFr;
		}

		ImtdRsDetailsFrImpl imtdRsDetailsFrImpl = new ImtdRsDetailsFrImpl();

		imtdRsDetailsFrImpl.setNew(imtdRsDetailsFr.isNew());
		imtdRsDetailsFrImpl.setPrimaryKey(imtdRsDetailsFr.getPrimaryKey());

		imtdRsDetailsFrImpl.setFormulaMethodId(imtdRsDetailsFr.getFormulaMethodId());
		imtdRsDetailsFrImpl.setItemMasterSid(imtdRsDetailsFr.getItemMasterSid());
		imtdRsDetailsFrImpl.setImtdRsDetailsSid(imtdRsDetailsFr.getImtdRsDetailsSid());
		imtdRsDetailsFrImpl.setModifiedDate(imtdRsDetailsFr.getModifiedDate());
		imtdRsDetailsFrImpl.setRsDetailsFrSid(imtdRsDetailsFr.getRsDetailsFrSid());
		imtdRsDetailsFrImpl.setRecordLockStatus(imtdRsDetailsFr.isRecordLockStatus());
		imtdRsDetailsFrImpl.setCreatedDate(imtdRsDetailsFr.getCreatedDate());
		imtdRsDetailsFrImpl.setCreatedBy(imtdRsDetailsFr.getCreatedBy());
		imtdRsDetailsFrImpl.setSource(imtdRsDetailsFr.getSource());
		imtdRsDetailsFrImpl.setImtdRsDetailsFrSid(imtdRsDetailsFr.getImtdRsDetailsFrSid());
		imtdRsDetailsFrImpl.setBatchId(imtdRsDetailsFr.getBatchId());
		imtdRsDetailsFrImpl.setImtdCreatedDate(imtdRsDetailsFr.getImtdCreatedDate());
		imtdRsDetailsFrImpl.setSessionId(imtdRsDetailsFr.getSessionId());
		imtdRsDetailsFrImpl.setUsersId(imtdRsDetailsFr.getUsersId());
		imtdRsDetailsFrImpl.setOperation(imtdRsDetailsFr.getOperation());
		imtdRsDetailsFrImpl.setModifiedBy(imtdRsDetailsFr.getModifiedBy());
		imtdRsDetailsFrImpl.setRsDetailsSid(imtdRsDetailsFr.getRsDetailsSid());
		imtdRsDetailsFrImpl.setFormulaId(imtdRsDetailsFr.getFormulaId());
		imtdRsDetailsFrImpl.setInboundStatus(imtdRsDetailsFr.getInboundStatus());

		return imtdRsDetailsFrImpl;
	}

	/**
	 * Returns the imtd rs details fr with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the imtd rs details fr
	 * @return the imtd rs details fr
	 * @throws NoSuchImtdRsDetailsFrException if a imtd rs details fr with the primary key could not be found
	 */
	@Override
	public ImtdRsDetailsFr findByPrimaryKey(Serializable primaryKey)
		throws NoSuchImtdRsDetailsFrException {
		ImtdRsDetailsFr imtdRsDetailsFr = fetchByPrimaryKey(primaryKey);

		if (imtdRsDetailsFr == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchImtdRsDetailsFrException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return imtdRsDetailsFr;
	}

	/**
	 * Returns the imtd rs details fr with the primary key or throws a {@link NoSuchImtdRsDetailsFrException} if it could not be found.
	 *
	 * @param imtdRsDetailsFrSid the primary key of the imtd rs details fr
	 * @return the imtd rs details fr
	 * @throws NoSuchImtdRsDetailsFrException if a imtd rs details fr with the primary key could not be found
	 */
	@Override
	public ImtdRsDetailsFr findByPrimaryKey(int imtdRsDetailsFrSid)
		throws NoSuchImtdRsDetailsFrException {
		return findByPrimaryKey((Serializable)imtdRsDetailsFrSid);
	}

	/**
	 * Returns the imtd rs details fr with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the imtd rs details fr
	 * @return the imtd rs details fr, or <code>null</code> if a imtd rs details fr with the primary key could not be found
	 */
	@Override
	public ImtdRsDetailsFr fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ImtdRsDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
				ImtdRsDetailsFrImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ImtdRsDetailsFr imtdRsDetailsFr = (ImtdRsDetailsFr)serializable;

		if (imtdRsDetailsFr == null) {
			Session session = null;

			try {
				session = openSession();

				imtdRsDetailsFr = (ImtdRsDetailsFr)session.get(ImtdRsDetailsFrImpl.class,
						primaryKey);

				if (imtdRsDetailsFr != null) {
					cacheResult(imtdRsDetailsFr);
				}
				else {
					entityCache.putResult(ImtdRsDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
						ImtdRsDetailsFrImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ImtdRsDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
					ImtdRsDetailsFrImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return imtdRsDetailsFr;
	}

	/**
	 * Returns the imtd rs details fr with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param imtdRsDetailsFrSid the primary key of the imtd rs details fr
	 * @return the imtd rs details fr, or <code>null</code> if a imtd rs details fr with the primary key could not be found
	 */
	@Override
	public ImtdRsDetailsFr fetchByPrimaryKey(int imtdRsDetailsFrSid) {
		return fetchByPrimaryKey((Serializable)imtdRsDetailsFrSid);
	}

	@Override
	public Map<Serializable, ImtdRsDetailsFr> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ImtdRsDetailsFr> map = new HashMap<Serializable, ImtdRsDetailsFr>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ImtdRsDetailsFr imtdRsDetailsFr = fetchByPrimaryKey(primaryKey);

			if (imtdRsDetailsFr != null) {
				map.put(primaryKey, imtdRsDetailsFr);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ImtdRsDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
					ImtdRsDetailsFrImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ImtdRsDetailsFr)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_IMTDRSDETAILSFR_WHERE_PKS_IN);

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

			for (ImtdRsDetailsFr imtdRsDetailsFr : (List<ImtdRsDetailsFr>)q.list()) {
				map.put(imtdRsDetailsFr.getPrimaryKeyObj(), imtdRsDetailsFr);

				cacheResult(imtdRsDetailsFr);

				uncachedPrimaryKeys.remove(imtdRsDetailsFr.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ImtdRsDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
					ImtdRsDetailsFrImpl.class, primaryKey, nullModel);
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
	 * Returns all the imtd rs details frs.
	 *
	 * @return the imtd rs details frs
	 */
	@Override
	public List<ImtdRsDetailsFr> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the imtd rs details frs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdRsDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of imtd rs details frs
	 * @param end the upper bound of the range of imtd rs details frs (not inclusive)
	 * @return the range of imtd rs details frs
	 */
	@Override
	public List<ImtdRsDetailsFr> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the imtd rs details frs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdRsDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of imtd rs details frs
	 * @param end the upper bound of the range of imtd rs details frs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of imtd rs details frs
	 */
	@Override
	public List<ImtdRsDetailsFr> findAll(int start, int end,
		OrderByComparator<ImtdRsDetailsFr> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the imtd rs details frs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdRsDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of imtd rs details frs
	 * @param end the upper bound of the range of imtd rs details frs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of imtd rs details frs
	 */
	@Override
	public List<ImtdRsDetailsFr> findAll(int start, int end,
		OrderByComparator<ImtdRsDetailsFr> orderByComparator,
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

		List<ImtdRsDetailsFr> list = null;

		if (retrieveFromCache) {
			list = (List<ImtdRsDetailsFr>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_IMTDRSDETAILSFR);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_IMTDRSDETAILSFR;

				if (pagination) {
					sql = sql.concat(ImtdRsDetailsFrModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ImtdRsDetailsFr>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ImtdRsDetailsFr>)QueryUtil.list(q,
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
	 * Removes all the imtd rs details frs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ImtdRsDetailsFr imtdRsDetailsFr : findAll()) {
			remove(imtdRsDetailsFr);
		}
	}

	/**
	 * Returns the number of imtd rs details frs.
	 *
	 * @return the number of imtd rs details frs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_IMTDRSDETAILSFR);

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
		return ImtdRsDetailsFrModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the imtd rs details fr persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ImtdRsDetailsFrImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_IMTDRSDETAILSFR = "SELECT imtdRsDetailsFr FROM ImtdRsDetailsFr imtdRsDetailsFr";
	private static final String _SQL_SELECT_IMTDRSDETAILSFR_WHERE_PKS_IN = "SELECT imtdRsDetailsFr FROM ImtdRsDetailsFr imtdRsDetailsFr WHERE IMTD_RS_DETAILS_FR_SID IN (";
	private static final String _SQL_COUNT_IMTDRSDETAILSFR = "SELECT COUNT(imtdRsDetailsFr) FROM ImtdRsDetailsFr imtdRsDetailsFr";
	private static final String _ORDER_BY_ENTITY_ALIAS = "imtdRsDetailsFr.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ImtdRsDetailsFr exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(ImtdRsDetailsFrPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"formulaMethodId", "itemMasterSid", "imtdRsDetailsSid",
				"modifiedDate", "rsDetailsFrSid", "recordLockStatus",
				"createdDate", "createdBy", "source", "imtdRsDetailsFrSid",
				"batchId", "imtdCreatedDate", "sessionId", "usersId",
				"operation", "modifiedBy", "rsDetailsSid", "formulaId",
				"inboundStatus"
			});
}