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

import com.stpl.app.exception.NoSuchImtdRsContractDetailsFrException;
import com.stpl.app.model.ImtdRsContractDetailsFr;
import com.stpl.app.model.impl.ImtdRsContractDetailsFrImpl;
import com.stpl.app.model.impl.ImtdRsContractDetailsFrModelImpl;
import com.stpl.app.service.persistence.ImtdRsContractDetailsFrPersistence;

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
 * The persistence implementation for the imtd rs contract details fr service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ImtdRsContractDetailsFrPersistence
 * @see com.stpl.app.service.persistence.ImtdRsContractDetailsFrUtil
 * @generated
 */
@ProviderType
public class ImtdRsContractDetailsFrPersistenceImpl extends BasePersistenceImpl<ImtdRsContractDetailsFr>
	implements ImtdRsContractDetailsFrPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ImtdRsContractDetailsFrUtil} to access the imtd rs contract details fr persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ImtdRsContractDetailsFrImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ImtdRsContractDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
			ImtdRsContractDetailsFrModelImpl.FINDER_CACHE_ENABLED,
			ImtdRsContractDetailsFrImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ImtdRsContractDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
			ImtdRsContractDetailsFrModelImpl.FINDER_CACHE_ENABLED,
			ImtdRsContractDetailsFrImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ImtdRsContractDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
			ImtdRsContractDetailsFrModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public ImtdRsContractDetailsFrPersistenceImpl() {
		setModelClass(ImtdRsContractDetailsFr.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("imtdRsContractDetailsFrSid",
				"IMTD_RS_CONTRACT_DETAILS_FR_SID");
			dbColumnNames.put("formulaMethodId", "FORMULA_METHOD_ID");
			dbColumnNames.put("itemMasterSid", "ITEM_MASTER_SID");
			dbColumnNames.put("rsContractDetailsFrSid",
				"RS_CONTRACT_DETAILS_FR_SID");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("rsContractDetailsSid", "RS_CONTRACT_DETAILS_SID");
			dbColumnNames.put("imtdItemPriceRebateDetailsSid",
				"IMTD_ITEM_PRICE_REBATE_DETAILS_SID");
			dbColumnNames.put("recordLockStatus", "RECORD_LOCK_STATUS");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("imtdCreatedDate", "IMTD_CREATED_DATE");
			dbColumnNames.put("sessionId", "SESSION_ID");
			dbColumnNames.put("usersId", "USERS_ID");
			dbColumnNames.put("operation", "OPERATION");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
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
	 * Caches the imtd rs contract details fr in the entity cache if it is enabled.
	 *
	 * @param imtdRsContractDetailsFr the imtd rs contract details fr
	 */
	@Override
	public void cacheResult(ImtdRsContractDetailsFr imtdRsContractDetailsFr) {
		entityCache.putResult(ImtdRsContractDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
			ImtdRsContractDetailsFrImpl.class,
			imtdRsContractDetailsFr.getPrimaryKey(), imtdRsContractDetailsFr);

		imtdRsContractDetailsFr.resetOriginalValues();
	}

	/**
	 * Caches the imtd rs contract details frs in the entity cache if it is enabled.
	 *
	 * @param imtdRsContractDetailsFrs the imtd rs contract details frs
	 */
	@Override
	public void cacheResult(
		List<ImtdRsContractDetailsFr> imtdRsContractDetailsFrs) {
		for (ImtdRsContractDetailsFr imtdRsContractDetailsFr : imtdRsContractDetailsFrs) {
			if (entityCache.getResult(
						ImtdRsContractDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
						ImtdRsContractDetailsFrImpl.class,
						imtdRsContractDetailsFr.getPrimaryKey()) == null) {
				cacheResult(imtdRsContractDetailsFr);
			}
			else {
				imtdRsContractDetailsFr.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all imtd rs contract details frs.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ImtdRsContractDetailsFrImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the imtd rs contract details fr.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ImtdRsContractDetailsFr imtdRsContractDetailsFr) {
		entityCache.removeResult(ImtdRsContractDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
			ImtdRsContractDetailsFrImpl.class,
			imtdRsContractDetailsFr.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(
		List<ImtdRsContractDetailsFr> imtdRsContractDetailsFrs) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ImtdRsContractDetailsFr imtdRsContractDetailsFr : imtdRsContractDetailsFrs) {
			entityCache.removeResult(ImtdRsContractDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
				ImtdRsContractDetailsFrImpl.class,
				imtdRsContractDetailsFr.getPrimaryKey());
		}
	}

	/**
	 * Creates a new imtd rs contract details fr with the primary key. Does not add the imtd rs contract details fr to the database.
	 *
	 * @param imtdRsContractDetailsFrSid the primary key for the new imtd rs contract details fr
	 * @return the new imtd rs contract details fr
	 */
	@Override
	public ImtdRsContractDetailsFr create(int imtdRsContractDetailsFrSid) {
		ImtdRsContractDetailsFr imtdRsContractDetailsFr = new ImtdRsContractDetailsFrImpl();

		imtdRsContractDetailsFr.setNew(true);
		imtdRsContractDetailsFr.setPrimaryKey(imtdRsContractDetailsFrSid);

		return imtdRsContractDetailsFr;
	}

	/**
	 * Removes the imtd rs contract details fr with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param imtdRsContractDetailsFrSid the primary key of the imtd rs contract details fr
	 * @return the imtd rs contract details fr that was removed
	 * @throws NoSuchImtdRsContractDetailsFrException if a imtd rs contract details fr with the primary key could not be found
	 */
	@Override
	public ImtdRsContractDetailsFr remove(int imtdRsContractDetailsFrSid)
		throws NoSuchImtdRsContractDetailsFrException {
		return remove((Serializable)imtdRsContractDetailsFrSid);
	}

	/**
	 * Removes the imtd rs contract details fr with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the imtd rs contract details fr
	 * @return the imtd rs contract details fr that was removed
	 * @throws NoSuchImtdRsContractDetailsFrException if a imtd rs contract details fr with the primary key could not be found
	 */
	@Override
	public ImtdRsContractDetailsFr remove(Serializable primaryKey)
		throws NoSuchImtdRsContractDetailsFrException {
		Session session = null;

		try {
			session = openSession();

			ImtdRsContractDetailsFr imtdRsContractDetailsFr = (ImtdRsContractDetailsFr)session.get(ImtdRsContractDetailsFrImpl.class,
					primaryKey);

			if (imtdRsContractDetailsFr == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchImtdRsContractDetailsFrException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(imtdRsContractDetailsFr);
		}
		catch (NoSuchImtdRsContractDetailsFrException nsee) {
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
	protected ImtdRsContractDetailsFr removeImpl(
		ImtdRsContractDetailsFr imtdRsContractDetailsFr) {
		imtdRsContractDetailsFr = toUnwrappedModel(imtdRsContractDetailsFr);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(imtdRsContractDetailsFr)) {
				imtdRsContractDetailsFr = (ImtdRsContractDetailsFr)session.get(ImtdRsContractDetailsFrImpl.class,
						imtdRsContractDetailsFr.getPrimaryKeyObj());
			}

			if (imtdRsContractDetailsFr != null) {
				session.delete(imtdRsContractDetailsFr);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (imtdRsContractDetailsFr != null) {
			clearCache(imtdRsContractDetailsFr);
		}

		return imtdRsContractDetailsFr;
	}

	@Override
	public ImtdRsContractDetailsFr updateImpl(
		ImtdRsContractDetailsFr imtdRsContractDetailsFr) {
		imtdRsContractDetailsFr = toUnwrappedModel(imtdRsContractDetailsFr);

		boolean isNew = imtdRsContractDetailsFr.isNew();

		Session session = null;

		try {
			session = openSession();

			if (imtdRsContractDetailsFr.isNew()) {
				session.save(imtdRsContractDetailsFr);

				imtdRsContractDetailsFr.setNew(false);
			}
			else {
				imtdRsContractDetailsFr = (ImtdRsContractDetailsFr)session.merge(imtdRsContractDetailsFr);
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

		entityCache.putResult(ImtdRsContractDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
			ImtdRsContractDetailsFrImpl.class,
			imtdRsContractDetailsFr.getPrimaryKey(), imtdRsContractDetailsFr,
			false);

		imtdRsContractDetailsFr.resetOriginalValues();

		return imtdRsContractDetailsFr;
	}

	protected ImtdRsContractDetailsFr toUnwrappedModel(
		ImtdRsContractDetailsFr imtdRsContractDetailsFr) {
		if (imtdRsContractDetailsFr instanceof ImtdRsContractDetailsFrImpl) {
			return imtdRsContractDetailsFr;
		}

		ImtdRsContractDetailsFrImpl imtdRsContractDetailsFrImpl = new ImtdRsContractDetailsFrImpl();

		imtdRsContractDetailsFrImpl.setNew(imtdRsContractDetailsFr.isNew());
		imtdRsContractDetailsFrImpl.setPrimaryKey(imtdRsContractDetailsFr.getPrimaryKey());

		imtdRsContractDetailsFrImpl.setImtdRsContractDetailsFrSid(imtdRsContractDetailsFr.getImtdRsContractDetailsFrSid());
		imtdRsContractDetailsFrImpl.setFormulaMethodId(imtdRsContractDetailsFr.getFormulaMethodId());
		imtdRsContractDetailsFrImpl.setItemMasterSid(imtdRsContractDetailsFr.getItemMasterSid());
		imtdRsContractDetailsFrImpl.setRsContractDetailsFrSid(imtdRsContractDetailsFr.getRsContractDetailsFrSid());
		imtdRsContractDetailsFrImpl.setModifiedDate(imtdRsContractDetailsFr.getModifiedDate());
		imtdRsContractDetailsFrImpl.setRsContractDetailsSid(imtdRsContractDetailsFr.getRsContractDetailsSid());
		imtdRsContractDetailsFrImpl.setImtdItemPriceRebateDetailsSid(imtdRsContractDetailsFr.getImtdItemPriceRebateDetailsSid());
		imtdRsContractDetailsFrImpl.setRecordLockStatus(imtdRsContractDetailsFr.isRecordLockStatus());
		imtdRsContractDetailsFrImpl.setCreatedDate(imtdRsContractDetailsFr.getCreatedDate());
		imtdRsContractDetailsFrImpl.setCreatedBy(imtdRsContractDetailsFr.getCreatedBy());
		imtdRsContractDetailsFrImpl.setSource(imtdRsContractDetailsFr.getSource());
		imtdRsContractDetailsFrImpl.setBatchId(imtdRsContractDetailsFr.getBatchId());
		imtdRsContractDetailsFrImpl.setImtdCreatedDate(imtdRsContractDetailsFr.getImtdCreatedDate());
		imtdRsContractDetailsFrImpl.setSessionId(imtdRsContractDetailsFr.getSessionId());
		imtdRsContractDetailsFrImpl.setUsersId(imtdRsContractDetailsFr.getUsersId());
		imtdRsContractDetailsFrImpl.setOperation(imtdRsContractDetailsFr.getOperation());
		imtdRsContractDetailsFrImpl.setModifiedBy(imtdRsContractDetailsFr.getModifiedBy());
		imtdRsContractDetailsFrImpl.setFormulaId(imtdRsContractDetailsFr.getFormulaId());
		imtdRsContractDetailsFrImpl.setInboundStatus(imtdRsContractDetailsFr.getInboundStatus());

		return imtdRsContractDetailsFrImpl;
	}

	/**
	 * Returns the imtd rs contract details fr with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the imtd rs contract details fr
	 * @return the imtd rs contract details fr
	 * @throws NoSuchImtdRsContractDetailsFrException if a imtd rs contract details fr with the primary key could not be found
	 */
	@Override
	public ImtdRsContractDetailsFr findByPrimaryKey(Serializable primaryKey)
		throws NoSuchImtdRsContractDetailsFrException {
		ImtdRsContractDetailsFr imtdRsContractDetailsFr = fetchByPrimaryKey(primaryKey);

		if (imtdRsContractDetailsFr == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchImtdRsContractDetailsFrException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return imtdRsContractDetailsFr;
	}

	/**
	 * Returns the imtd rs contract details fr with the primary key or throws a {@link NoSuchImtdRsContractDetailsFrException} if it could not be found.
	 *
	 * @param imtdRsContractDetailsFrSid the primary key of the imtd rs contract details fr
	 * @return the imtd rs contract details fr
	 * @throws NoSuchImtdRsContractDetailsFrException if a imtd rs contract details fr with the primary key could not be found
	 */
	@Override
	public ImtdRsContractDetailsFr findByPrimaryKey(
		int imtdRsContractDetailsFrSid)
		throws NoSuchImtdRsContractDetailsFrException {
		return findByPrimaryKey((Serializable)imtdRsContractDetailsFrSid);
	}

	/**
	 * Returns the imtd rs contract details fr with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the imtd rs contract details fr
	 * @return the imtd rs contract details fr, or <code>null</code> if a imtd rs contract details fr with the primary key could not be found
	 */
	@Override
	public ImtdRsContractDetailsFr fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ImtdRsContractDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
				ImtdRsContractDetailsFrImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ImtdRsContractDetailsFr imtdRsContractDetailsFr = (ImtdRsContractDetailsFr)serializable;

		if (imtdRsContractDetailsFr == null) {
			Session session = null;

			try {
				session = openSession();

				imtdRsContractDetailsFr = (ImtdRsContractDetailsFr)session.get(ImtdRsContractDetailsFrImpl.class,
						primaryKey);

				if (imtdRsContractDetailsFr != null) {
					cacheResult(imtdRsContractDetailsFr);
				}
				else {
					entityCache.putResult(ImtdRsContractDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
						ImtdRsContractDetailsFrImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ImtdRsContractDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
					ImtdRsContractDetailsFrImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return imtdRsContractDetailsFr;
	}

	/**
	 * Returns the imtd rs contract details fr with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param imtdRsContractDetailsFrSid the primary key of the imtd rs contract details fr
	 * @return the imtd rs contract details fr, or <code>null</code> if a imtd rs contract details fr with the primary key could not be found
	 */
	@Override
	public ImtdRsContractDetailsFr fetchByPrimaryKey(
		int imtdRsContractDetailsFrSid) {
		return fetchByPrimaryKey((Serializable)imtdRsContractDetailsFrSid);
	}

	@Override
	public Map<Serializable, ImtdRsContractDetailsFr> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ImtdRsContractDetailsFr> map = new HashMap<Serializable, ImtdRsContractDetailsFr>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ImtdRsContractDetailsFr imtdRsContractDetailsFr = fetchByPrimaryKey(primaryKey);

			if (imtdRsContractDetailsFr != null) {
				map.put(primaryKey, imtdRsContractDetailsFr);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ImtdRsContractDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
					ImtdRsContractDetailsFrImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ImtdRsContractDetailsFr)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_IMTDRSCONTRACTDETAILSFR_WHERE_PKS_IN);

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

			for (ImtdRsContractDetailsFr imtdRsContractDetailsFr : (List<ImtdRsContractDetailsFr>)q.list()) {
				map.put(imtdRsContractDetailsFr.getPrimaryKeyObj(),
					imtdRsContractDetailsFr);

				cacheResult(imtdRsContractDetailsFr);

				uncachedPrimaryKeys.remove(imtdRsContractDetailsFr.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ImtdRsContractDetailsFrModelImpl.ENTITY_CACHE_ENABLED,
					ImtdRsContractDetailsFrImpl.class, primaryKey, nullModel);
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
	 * Returns all the imtd rs contract details frs.
	 *
	 * @return the imtd rs contract details frs
	 */
	@Override
	public List<ImtdRsContractDetailsFr> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the imtd rs contract details frs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdRsContractDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of imtd rs contract details frs
	 * @param end the upper bound of the range of imtd rs contract details frs (not inclusive)
	 * @return the range of imtd rs contract details frs
	 */
	@Override
	public List<ImtdRsContractDetailsFr> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the imtd rs contract details frs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdRsContractDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of imtd rs contract details frs
	 * @param end the upper bound of the range of imtd rs contract details frs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of imtd rs contract details frs
	 */
	@Override
	public List<ImtdRsContractDetailsFr> findAll(int start, int end,
		OrderByComparator<ImtdRsContractDetailsFr> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the imtd rs contract details frs.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdRsContractDetailsFrModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of imtd rs contract details frs
	 * @param end the upper bound of the range of imtd rs contract details frs (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of imtd rs contract details frs
	 */
	@Override
	public List<ImtdRsContractDetailsFr> findAll(int start, int end,
		OrderByComparator<ImtdRsContractDetailsFr> orderByComparator,
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

		List<ImtdRsContractDetailsFr> list = null;

		if (retrieveFromCache) {
			list = (List<ImtdRsContractDetailsFr>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_IMTDRSCONTRACTDETAILSFR);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_IMTDRSCONTRACTDETAILSFR;

				if (pagination) {
					sql = sql.concat(ImtdRsContractDetailsFrModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ImtdRsContractDetailsFr>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ImtdRsContractDetailsFr>)QueryUtil.list(q,
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
	 * Removes all the imtd rs contract details frs from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ImtdRsContractDetailsFr imtdRsContractDetailsFr : findAll()) {
			remove(imtdRsContractDetailsFr);
		}
	}

	/**
	 * Returns the number of imtd rs contract details frs.
	 *
	 * @return the number of imtd rs contract details frs
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_IMTDRSCONTRACTDETAILSFR);

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
		return ImtdRsContractDetailsFrModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the imtd rs contract details fr persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ImtdRsContractDetailsFrImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_IMTDRSCONTRACTDETAILSFR = "SELECT imtdRsContractDetailsFr FROM ImtdRsContractDetailsFr imtdRsContractDetailsFr";
	private static final String _SQL_SELECT_IMTDRSCONTRACTDETAILSFR_WHERE_PKS_IN =
		"SELECT imtdRsContractDetailsFr FROM ImtdRsContractDetailsFr imtdRsContractDetailsFr WHERE IMTD_RS_CONTRACT_DETAILS_FR_SID IN (";
	private static final String _SQL_COUNT_IMTDRSCONTRACTDETAILSFR = "SELECT COUNT(imtdRsContractDetailsFr) FROM ImtdRsContractDetailsFr imtdRsContractDetailsFr";
	private static final String _ORDER_BY_ENTITY_ALIAS = "imtdRsContractDetailsFr.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ImtdRsContractDetailsFr exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(ImtdRsContractDetailsFrPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"imtdRsContractDetailsFrSid", "formulaMethodId", "itemMasterSid",
				"rsContractDetailsFrSid", "modifiedDate", "rsContractDetailsSid",
				"imtdItemPriceRebateDetailsSid", "recordLockStatus",
				"createdDate", "createdBy", "source", "batchId",
				"imtdCreatedDate", "sessionId", "usersId", "operation",
				"modifiedBy", "formulaId", "inboundStatus"
			});
}