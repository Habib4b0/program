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

import com.stpl.app.exception.NoSuchImtdDeductionDetailsException;
import com.stpl.app.model.ImtdDeductionDetails;
import com.stpl.app.model.impl.ImtdDeductionDetailsImpl;
import com.stpl.app.model.impl.ImtdDeductionDetailsModelImpl;
import com.stpl.app.service.persistence.ImtdDeductionDetailsPersistence;

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
 * The persistence implementation for the imtd deduction details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see ImtdDeductionDetailsPersistence
 * @see com.stpl.app.service.persistence.ImtdDeductionDetailsUtil
 * @generated
 */
@ProviderType
public class ImtdDeductionDetailsPersistenceImpl extends BasePersistenceImpl<ImtdDeductionDetails>
	implements ImtdDeductionDetailsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link ImtdDeductionDetailsUtil} to access the imtd deduction details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = ImtdDeductionDetailsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(ImtdDeductionDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ImtdDeductionDetailsModelImpl.FINDER_CACHE_ENABLED,
			ImtdDeductionDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(ImtdDeductionDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ImtdDeductionDetailsModelImpl.FINDER_CACHE_ENABLED,
			ImtdDeductionDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(ImtdDeductionDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ImtdDeductionDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public ImtdDeductionDetailsPersistenceImpl() {
		setModelClass(ImtdDeductionDetails.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("imtdDeductionDetailsSid",
				"IMTD_DEDUCTION_DETAILS_SID");
			dbColumnNames.put("deductionName", "DEDUCTION_NAME");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("imtdCreatedDate", "IMTD_CREATED_DATE");
			dbColumnNames.put("deductionDetailsSid", "DEDUCTION_DETAILS_SID");
			dbColumnNames.put("indicator", "INDICATOR");
			dbColumnNames.put("contractNo", "CONTRACT_NO");
			dbColumnNames.put("checkRecord", "CHECK_RECORD");
			dbColumnNames.put("deductionSubType", "DEDUCTION_SUB_TYPE");
			dbColumnNames.put("cdrModelSid", "CDR_MODEL_SID");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("deductionNo", "DEDUCTION_NO");
			dbColumnNames.put("netSalesFormulaMasterSid",
				"NET_SALES_FORMULA_MASTER_SID");
			dbColumnNames.put("usersSid", "USERS_SID");
			dbColumnNames.put("contractMasterSid", "CONTRACT_MASTER_SID");
			dbColumnNames.put("contractName", "CONTRACT_NAME");
			dbColumnNames.put("deductionCategory", "DEDUCTION_CATEGORY");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("deductionType", "DEDUCTION_TYPE");
			dbColumnNames.put("recordLockStatus", "RECORD_LOCK_STATUS");
			dbColumnNames.put("operation", "OPERATION");
			dbColumnNames.put("sessionId", "SESSION_ID");
			dbColumnNames.put("rsContractSid", "RS_CONTRACT_SID");
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
	 * Caches the imtd deduction details in the entity cache if it is enabled.
	 *
	 * @param imtdDeductionDetails the imtd deduction details
	 */
	@Override
	public void cacheResult(ImtdDeductionDetails imtdDeductionDetails) {
		entityCache.putResult(ImtdDeductionDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ImtdDeductionDetailsImpl.class,
			imtdDeductionDetails.getPrimaryKey(), imtdDeductionDetails);

		imtdDeductionDetails.resetOriginalValues();
	}

	/**
	 * Caches the imtd deduction detailses in the entity cache if it is enabled.
	 *
	 * @param imtdDeductionDetailses the imtd deduction detailses
	 */
	@Override
	public void cacheResult(List<ImtdDeductionDetails> imtdDeductionDetailses) {
		for (ImtdDeductionDetails imtdDeductionDetails : imtdDeductionDetailses) {
			if (entityCache.getResult(
						ImtdDeductionDetailsModelImpl.ENTITY_CACHE_ENABLED,
						ImtdDeductionDetailsImpl.class,
						imtdDeductionDetails.getPrimaryKey()) == null) {
				cacheResult(imtdDeductionDetails);
			}
			else {
				imtdDeductionDetails.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all imtd deduction detailses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(ImtdDeductionDetailsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the imtd deduction details.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(ImtdDeductionDetails imtdDeductionDetails) {
		entityCache.removeResult(ImtdDeductionDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ImtdDeductionDetailsImpl.class, imtdDeductionDetails.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<ImtdDeductionDetails> imtdDeductionDetailses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (ImtdDeductionDetails imtdDeductionDetails : imtdDeductionDetailses) {
			entityCache.removeResult(ImtdDeductionDetailsModelImpl.ENTITY_CACHE_ENABLED,
				ImtdDeductionDetailsImpl.class,
				imtdDeductionDetails.getPrimaryKey());
		}
	}

	/**
	 * Creates a new imtd deduction details with the primary key. Does not add the imtd deduction details to the database.
	 *
	 * @param imtdDeductionDetailsSid the primary key for the new imtd deduction details
	 * @return the new imtd deduction details
	 */
	@Override
	public ImtdDeductionDetails create(int imtdDeductionDetailsSid) {
		ImtdDeductionDetails imtdDeductionDetails = new ImtdDeductionDetailsImpl();

		imtdDeductionDetails.setNew(true);
		imtdDeductionDetails.setPrimaryKey(imtdDeductionDetailsSid);

		return imtdDeductionDetails;
	}

	/**
	 * Removes the imtd deduction details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param imtdDeductionDetailsSid the primary key of the imtd deduction details
	 * @return the imtd deduction details that was removed
	 * @throws NoSuchImtdDeductionDetailsException if a imtd deduction details with the primary key could not be found
	 */
	@Override
	public ImtdDeductionDetails remove(int imtdDeductionDetailsSid)
		throws NoSuchImtdDeductionDetailsException {
		return remove((Serializable)imtdDeductionDetailsSid);
	}

	/**
	 * Removes the imtd deduction details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the imtd deduction details
	 * @return the imtd deduction details that was removed
	 * @throws NoSuchImtdDeductionDetailsException if a imtd deduction details with the primary key could not be found
	 */
	@Override
	public ImtdDeductionDetails remove(Serializable primaryKey)
		throws NoSuchImtdDeductionDetailsException {
		Session session = null;

		try {
			session = openSession();

			ImtdDeductionDetails imtdDeductionDetails = (ImtdDeductionDetails)session.get(ImtdDeductionDetailsImpl.class,
					primaryKey);

			if (imtdDeductionDetails == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchImtdDeductionDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(imtdDeductionDetails);
		}
		catch (NoSuchImtdDeductionDetailsException nsee) {
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
	protected ImtdDeductionDetails removeImpl(
		ImtdDeductionDetails imtdDeductionDetails) {
		imtdDeductionDetails = toUnwrappedModel(imtdDeductionDetails);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(imtdDeductionDetails)) {
				imtdDeductionDetails = (ImtdDeductionDetails)session.get(ImtdDeductionDetailsImpl.class,
						imtdDeductionDetails.getPrimaryKeyObj());
			}

			if (imtdDeductionDetails != null) {
				session.delete(imtdDeductionDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (imtdDeductionDetails != null) {
			clearCache(imtdDeductionDetails);
		}

		return imtdDeductionDetails;
	}

	@Override
	public ImtdDeductionDetails updateImpl(
		ImtdDeductionDetails imtdDeductionDetails) {
		imtdDeductionDetails = toUnwrappedModel(imtdDeductionDetails);

		boolean isNew = imtdDeductionDetails.isNew();

		Session session = null;

		try {
			session = openSession();

			if (imtdDeductionDetails.isNew()) {
				session.save(imtdDeductionDetails);

				imtdDeductionDetails.setNew(false);
			}
			else {
				imtdDeductionDetails = (ImtdDeductionDetails)session.merge(imtdDeductionDetails);
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

		entityCache.putResult(ImtdDeductionDetailsModelImpl.ENTITY_CACHE_ENABLED,
			ImtdDeductionDetailsImpl.class,
			imtdDeductionDetails.getPrimaryKey(), imtdDeductionDetails, false);

		imtdDeductionDetails.resetOriginalValues();

		return imtdDeductionDetails;
	}

	protected ImtdDeductionDetails toUnwrappedModel(
		ImtdDeductionDetails imtdDeductionDetails) {
		if (imtdDeductionDetails instanceof ImtdDeductionDetailsImpl) {
			return imtdDeductionDetails;
		}

		ImtdDeductionDetailsImpl imtdDeductionDetailsImpl = new ImtdDeductionDetailsImpl();

		imtdDeductionDetailsImpl.setNew(imtdDeductionDetails.isNew());
		imtdDeductionDetailsImpl.setPrimaryKey(imtdDeductionDetails.getPrimaryKey());

		imtdDeductionDetailsImpl.setImtdDeductionDetailsSid(imtdDeductionDetails.getImtdDeductionDetailsSid());
		imtdDeductionDetailsImpl.setDeductionName(imtdDeductionDetails.getDeductionName());
		imtdDeductionDetailsImpl.setModifiedBy(imtdDeductionDetails.getModifiedBy());
		imtdDeductionDetailsImpl.setCreatedDate(imtdDeductionDetails.getCreatedDate());
		imtdDeductionDetailsImpl.setImtdCreatedDate(imtdDeductionDetails.getImtdCreatedDate());
		imtdDeductionDetailsImpl.setDeductionDetailsSid(imtdDeductionDetails.getDeductionDetailsSid());
		imtdDeductionDetailsImpl.setIndicator(imtdDeductionDetails.getIndicator());
		imtdDeductionDetailsImpl.setContractNo(imtdDeductionDetails.getContractNo());
		imtdDeductionDetailsImpl.setCheckRecord(imtdDeductionDetails.isCheckRecord());
		imtdDeductionDetailsImpl.setDeductionSubType(imtdDeductionDetails.getDeductionSubType());
		imtdDeductionDetailsImpl.setCdrModelSid(imtdDeductionDetails.getCdrModelSid());
		imtdDeductionDetailsImpl.setCreatedBy(imtdDeductionDetails.getCreatedBy());
		imtdDeductionDetailsImpl.setDeductionNo(imtdDeductionDetails.getDeductionNo());
		imtdDeductionDetailsImpl.setNetSalesFormulaMasterSid(imtdDeductionDetails.getNetSalesFormulaMasterSid());
		imtdDeductionDetailsImpl.setUsersSid(imtdDeductionDetails.getUsersSid());
		imtdDeductionDetailsImpl.setContractMasterSid(imtdDeductionDetails.getContractMasterSid());
		imtdDeductionDetailsImpl.setContractName(imtdDeductionDetails.getContractName());
		imtdDeductionDetailsImpl.setDeductionCategory(imtdDeductionDetails.getDeductionCategory());
		imtdDeductionDetailsImpl.setModifiedDate(imtdDeductionDetails.getModifiedDate());
		imtdDeductionDetailsImpl.setDeductionType(imtdDeductionDetails.getDeductionType());
		imtdDeductionDetailsImpl.setRecordLockStatus(imtdDeductionDetails.isRecordLockStatus());
		imtdDeductionDetailsImpl.setOperation(imtdDeductionDetails.getOperation());
		imtdDeductionDetailsImpl.setSessionId(imtdDeductionDetails.getSessionId());
		imtdDeductionDetailsImpl.setRsContractSid(imtdDeductionDetails.getRsContractSid());
		imtdDeductionDetailsImpl.setInboundStatus(imtdDeductionDetails.getInboundStatus());

		return imtdDeductionDetailsImpl;
	}

	/**
	 * Returns the imtd deduction details with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the imtd deduction details
	 * @return the imtd deduction details
	 * @throws NoSuchImtdDeductionDetailsException if a imtd deduction details with the primary key could not be found
	 */
	@Override
	public ImtdDeductionDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchImtdDeductionDetailsException {
		ImtdDeductionDetails imtdDeductionDetails = fetchByPrimaryKey(primaryKey);

		if (imtdDeductionDetails == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchImtdDeductionDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return imtdDeductionDetails;
	}

	/**
	 * Returns the imtd deduction details with the primary key or throws a {@link NoSuchImtdDeductionDetailsException} if it could not be found.
	 *
	 * @param imtdDeductionDetailsSid the primary key of the imtd deduction details
	 * @return the imtd deduction details
	 * @throws NoSuchImtdDeductionDetailsException if a imtd deduction details with the primary key could not be found
	 */
	@Override
	public ImtdDeductionDetails findByPrimaryKey(int imtdDeductionDetailsSid)
		throws NoSuchImtdDeductionDetailsException {
		return findByPrimaryKey((Serializable)imtdDeductionDetailsSid);
	}

	/**
	 * Returns the imtd deduction details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the imtd deduction details
	 * @return the imtd deduction details, or <code>null</code> if a imtd deduction details with the primary key could not be found
	 */
	@Override
	public ImtdDeductionDetails fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(ImtdDeductionDetailsModelImpl.ENTITY_CACHE_ENABLED,
				ImtdDeductionDetailsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		ImtdDeductionDetails imtdDeductionDetails = (ImtdDeductionDetails)serializable;

		if (imtdDeductionDetails == null) {
			Session session = null;

			try {
				session = openSession();

				imtdDeductionDetails = (ImtdDeductionDetails)session.get(ImtdDeductionDetailsImpl.class,
						primaryKey);

				if (imtdDeductionDetails != null) {
					cacheResult(imtdDeductionDetails);
				}
				else {
					entityCache.putResult(ImtdDeductionDetailsModelImpl.ENTITY_CACHE_ENABLED,
						ImtdDeductionDetailsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(ImtdDeductionDetailsModelImpl.ENTITY_CACHE_ENABLED,
					ImtdDeductionDetailsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return imtdDeductionDetails;
	}

	/**
	 * Returns the imtd deduction details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param imtdDeductionDetailsSid the primary key of the imtd deduction details
	 * @return the imtd deduction details, or <code>null</code> if a imtd deduction details with the primary key could not be found
	 */
	@Override
	public ImtdDeductionDetails fetchByPrimaryKey(int imtdDeductionDetailsSid) {
		return fetchByPrimaryKey((Serializable)imtdDeductionDetailsSid);
	}

	@Override
	public Map<Serializable, ImtdDeductionDetails> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, ImtdDeductionDetails> map = new HashMap<Serializable, ImtdDeductionDetails>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			ImtdDeductionDetails imtdDeductionDetails = fetchByPrimaryKey(primaryKey);

			if (imtdDeductionDetails != null) {
				map.put(primaryKey, imtdDeductionDetails);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(ImtdDeductionDetailsModelImpl.ENTITY_CACHE_ENABLED,
					ImtdDeductionDetailsImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (ImtdDeductionDetails)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_IMTDDEDUCTIONDETAILS_WHERE_PKS_IN);

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

			for (ImtdDeductionDetails imtdDeductionDetails : (List<ImtdDeductionDetails>)q.list()) {
				map.put(imtdDeductionDetails.getPrimaryKeyObj(),
					imtdDeductionDetails);

				cacheResult(imtdDeductionDetails);

				uncachedPrimaryKeys.remove(imtdDeductionDetails.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(ImtdDeductionDetailsModelImpl.ENTITY_CACHE_ENABLED,
					ImtdDeductionDetailsImpl.class, primaryKey, nullModel);
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
	 * Returns all the imtd deduction detailses.
	 *
	 * @return the imtd deduction detailses
	 */
	@Override
	public List<ImtdDeductionDetails> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the imtd deduction detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdDeductionDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of imtd deduction detailses
	 * @param end the upper bound of the range of imtd deduction detailses (not inclusive)
	 * @return the range of imtd deduction detailses
	 */
	@Override
	public List<ImtdDeductionDetails> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the imtd deduction detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdDeductionDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of imtd deduction detailses
	 * @param end the upper bound of the range of imtd deduction detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of imtd deduction detailses
	 */
	@Override
	public List<ImtdDeductionDetails> findAll(int start, int end,
		OrderByComparator<ImtdDeductionDetails> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the imtd deduction detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link ImtdDeductionDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of imtd deduction detailses
	 * @param end the upper bound of the range of imtd deduction detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of imtd deduction detailses
	 */
	@Override
	public List<ImtdDeductionDetails> findAll(int start, int end,
		OrderByComparator<ImtdDeductionDetails> orderByComparator,
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

		List<ImtdDeductionDetails> list = null;

		if (retrieveFromCache) {
			list = (List<ImtdDeductionDetails>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_IMTDDEDUCTIONDETAILS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_IMTDDEDUCTIONDETAILS;

				if (pagination) {
					sql = sql.concat(ImtdDeductionDetailsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<ImtdDeductionDetails>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<ImtdDeductionDetails>)QueryUtil.list(q,
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
	 * Removes all the imtd deduction detailses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (ImtdDeductionDetails imtdDeductionDetails : findAll()) {
			remove(imtdDeductionDetails);
		}
	}

	/**
	 * Returns the number of imtd deduction detailses.
	 *
	 * @return the number of imtd deduction detailses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_IMTDDEDUCTIONDETAILS);

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
		return ImtdDeductionDetailsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the imtd deduction details persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(ImtdDeductionDetailsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_IMTDDEDUCTIONDETAILS = "SELECT imtdDeductionDetails FROM ImtdDeductionDetails imtdDeductionDetails";
	private static final String _SQL_SELECT_IMTDDEDUCTIONDETAILS_WHERE_PKS_IN = "SELECT imtdDeductionDetails FROM ImtdDeductionDetails imtdDeductionDetails WHERE IMTD_DEDUCTION_DETAILS_SID IN (";
	private static final String _SQL_COUNT_IMTDDEDUCTIONDETAILS = "SELECT COUNT(imtdDeductionDetails) FROM ImtdDeductionDetails imtdDeductionDetails";
	private static final String _ORDER_BY_ENTITY_ALIAS = "imtdDeductionDetails.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No ImtdDeductionDetails exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(ImtdDeductionDetailsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"imtdDeductionDetailsSid", "deductionName", "modifiedBy",
				"createdDate", "imtdCreatedDate", "deductionDetailsSid",
				"indicator", "contractNo", "checkRecord", "deductionSubType",
				"cdrModelSid", "createdBy", "deductionNo",
				"netSalesFormulaMasterSid", "usersSid", "contractMasterSid",
				"contractName", "deductionCategory", "modifiedDate",
				"deductionType", "recordLockStatus", "operation", "sessionId",
				"rsContractSid", "inboundStatus"
			});
}