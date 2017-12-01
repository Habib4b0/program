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

import com.stpl.app.exception.NoSuchIvldFormulaDetailsException;
import com.stpl.app.model.IvldFormulaDetails;
import com.stpl.app.model.impl.IvldFormulaDetailsImpl;
import com.stpl.app.model.impl.IvldFormulaDetailsModelImpl;
import com.stpl.app.service.persistence.IvldFormulaDetailsPersistence;

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
 * The persistence implementation for the ivld formula details service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldFormulaDetailsPersistence
 * @see com.stpl.app.service.persistence.IvldFormulaDetailsUtil
 * @generated
 */
@ProviderType
public class IvldFormulaDetailsPersistenceImpl extends BasePersistenceImpl<IvldFormulaDetails>
	implements IvldFormulaDetailsPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link IvldFormulaDetailsUtil} to access the ivld formula details persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = IvldFormulaDetailsImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IvldFormulaDetailsModelImpl.ENTITY_CACHE_ENABLED,
			IvldFormulaDetailsModelImpl.FINDER_CACHE_ENABLED,
			IvldFormulaDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITH_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IvldFormulaDetailsModelImpl.ENTITY_CACHE_ENABLED,
			IvldFormulaDetailsModelImpl.FINDER_CACHE_ENABLED,
			IvldFormulaDetailsImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IvldFormulaDetailsModelImpl.ENTITY_CACHE_ENABLED,
			IvldFormulaDetailsModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public IvldFormulaDetailsPersistenceImpl() {
		setModelClass(IvldFormulaDetails.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("endDate", "END_DATE");
			dbColumnNames.put("rebatePercent1", "REBATE_PERCENT_1");
			dbColumnNames.put("itemId", "ITEM_ID");
			dbColumnNames.put("rebatePercent2", "REBATE_PERCENT_2");
			dbColumnNames.put("formulaDesc", "FORMULA_DESC");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("rebatePercent3", "REBATE_PERCENT_3");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("addChgDelIndicator", "ADD_CHG_DEL_INDICATOR");
			dbColumnNames.put("errorCode", "ERROR_CODE");
			dbColumnNames.put("formulaId", "FORMULA_ID");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("intfInsertedDate", "INTF_INSERTED_DATE");
			dbColumnNames.put("reprocessedFlag", "REPROCESSED_FLAG");
			dbColumnNames.put("formulaDetailsIntfid", "FORMULA_DETAILS_INTFID");
			dbColumnNames.put("reasonForFailure", "REASON_FOR_FAILURE");
			dbColumnNames.put("contractPrice1", "CONTRACT_PRICE_1");
			dbColumnNames.put("companyStringId", "COMPANY_ID");
			dbColumnNames.put("contractPrice2", "CONTRACT_PRICE_2");
			dbColumnNames.put("formulaNo", "FORMULA_NO");
			dbColumnNames.put("startDate", "START_DATE");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("errorField", "ERROR_FIELD");
			dbColumnNames.put("contractPrice3", "CONTRACT_PRICE_3");
			dbColumnNames.put("ivldFormulaDetailsSid",
				"IVLD_FORMULA_DETAILS_SID");
			dbColumnNames.put("checkRecord", "CHECK_RECORD");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the ivld formula details in the entity cache if it is enabled.
	 *
	 * @param ivldFormulaDetails the ivld formula details
	 */
	@Override
	public void cacheResult(IvldFormulaDetails ivldFormulaDetails) {
		entityCache.putResult(IvldFormulaDetailsModelImpl.ENTITY_CACHE_ENABLED,
			IvldFormulaDetailsImpl.class, ivldFormulaDetails.getPrimaryKey(),
			ivldFormulaDetails);

		ivldFormulaDetails.resetOriginalValues();
	}

	/**
	 * Caches the ivld formula detailses in the entity cache if it is enabled.
	 *
	 * @param ivldFormulaDetailses the ivld formula detailses
	 */
	@Override
	public void cacheResult(List<IvldFormulaDetails> ivldFormulaDetailses) {
		for (IvldFormulaDetails ivldFormulaDetails : ivldFormulaDetailses) {
			if (entityCache.getResult(
						IvldFormulaDetailsModelImpl.ENTITY_CACHE_ENABLED,
						IvldFormulaDetailsImpl.class,
						ivldFormulaDetails.getPrimaryKey()) == null) {
				cacheResult(ivldFormulaDetails);
			}
			else {
				ivldFormulaDetails.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ivld formula detailses.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(IvldFormulaDetailsImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ivld formula details.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(IvldFormulaDetails ivldFormulaDetails) {
		entityCache.removeResult(IvldFormulaDetailsModelImpl.ENTITY_CACHE_ENABLED,
			IvldFormulaDetailsImpl.class, ivldFormulaDetails.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<IvldFormulaDetails> ivldFormulaDetailses) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (IvldFormulaDetails ivldFormulaDetails : ivldFormulaDetailses) {
			entityCache.removeResult(IvldFormulaDetailsModelImpl.ENTITY_CACHE_ENABLED,
				IvldFormulaDetailsImpl.class, ivldFormulaDetails.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ivld formula details with the primary key. Does not add the ivld formula details to the database.
	 *
	 * @param ivldFormulaDetailsSid the primary key for the new ivld formula details
	 * @return the new ivld formula details
	 */
	@Override
	public IvldFormulaDetails create(int ivldFormulaDetailsSid) {
		IvldFormulaDetails ivldFormulaDetails = new IvldFormulaDetailsImpl();

		ivldFormulaDetails.setNew(true);
		ivldFormulaDetails.setPrimaryKey(ivldFormulaDetailsSid);

		return ivldFormulaDetails;
	}

	/**
	 * Removes the ivld formula details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ivldFormulaDetailsSid the primary key of the ivld formula details
	 * @return the ivld formula details that was removed
	 * @throws NoSuchIvldFormulaDetailsException if a ivld formula details with the primary key could not be found
	 */
	@Override
	public IvldFormulaDetails remove(int ivldFormulaDetailsSid)
		throws NoSuchIvldFormulaDetailsException {
		return remove((Serializable)ivldFormulaDetailsSid);
	}

	/**
	 * Removes the ivld formula details with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ivld formula details
	 * @return the ivld formula details that was removed
	 * @throws NoSuchIvldFormulaDetailsException if a ivld formula details with the primary key could not be found
	 */
	@Override
	public IvldFormulaDetails remove(Serializable primaryKey)
		throws NoSuchIvldFormulaDetailsException {
		Session session = null;

		try {
			session = openSession();

			IvldFormulaDetails ivldFormulaDetails = (IvldFormulaDetails)session.get(IvldFormulaDetailsImpl.class,
					primaryKey);

			if (ivldFormulaDetails == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchIvldFormulaDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ivldFormulaDetails);
		}
		catch (NoSuchIvldFormulaDetailsException nsee) {
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
	protected IvldFormulaDetails removeImpl(
		IvldFormulaDetails ivldFormulaDetails) {
		ivldFormulaDetails = toUnwrappedModel(ivldFormulaDetails);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ivldFormulaDetails)) {
				ivldFormulaDetails = (IvldFormulaDetails)session.get(IvldFormulaDetailsImpl.class,
						ivldFormulaDetails.getPrimaryKeyObj());
			}

			if (ivldFormulaDetails != null) {
				session.delete(ivldFormulaDetails);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (ivldFormulaDetails != null) {
			clearCache(ivldFormulaDetails);
		}

		return ivldFormulaDetails;
	}

	@Override
	public IvldFormulaDetails updateImpl(IvldFormulaDetails ivldFormulaDetails) {
		ivldFormulaDetails = toUnwrappedModel(ivldFormulaDetails);

		boolean isNew = ivldFormulaDetails.isNew();

		Session session = null;

		try {
			session = openSession();

			if (ivldFormulaDetails.isNew()) {
				session.save(ivldFormulaDetails);

				ivldFormulaDetails.setNew(false);
			}
			else {
				ivldFormulaDetails = (IvldFormulaDetails)session.merge(ivldFormulaDetails);
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

		entityCache.putResult(IvldFormulaDetailsModelImpl.ENTITY_CACHE_ENABLED,
			IvldFormulaDetailsImpl.class, ivldFormulaDetails.getPrimaryKey(),
			ivldFormulaDetails, false);

		ivldFormulaDetails.resetOriginalValues();

		return ivldFormulaDetails;
	}

	protected IvldFormulaDetails toUnwrappedModel(
		IvldFormulaDetails ivldFormulaDetails) {
		if (ivldFormulaDetails instanceof IvldFormulaDetailsImpl) {
			return ivldFormulaDetails;
		}

		IvldFormulaDetailsImpl ivldFormulaDetailsImpl = new IvldFormulaDetailsImpl();

		ivldFormulaDetailsImpl.setNew(ivldFormulaDetails.isNew());
		ivldFormulaDetailsImpl.setPrimaryKey(ivldFormulaDetails.getPrimaryKey());

		ivldFormulaDetailsImpl.setEndDate(ivldFormulaDetails.getEndDate());
		ivldFormulaDetailsImpl.setRebatePercent1(ivldFormulaDetails.getRebatePercent1());
		ivldFormulaDetailsImpl.setItemId(ivldFormulaDetails.getItemId());
		ivldFormulaDetailsImpl.setRebatePercent2(ivldFormulaDetails.getRebatePercent2());
		ivldFormulaDetailsImpl.setFormulaDesc(ivldFormulaDetails.getFormulaDesc());
		ivldFormulaDetailsImpl.setModifiedDate(ivldFormulaDetails.getModifiedDate());
		ivldFormulaDetailsImpl.setRebatePercent3(ivldFormulaDetails.getRebatePercent3());
		ivldFormulaDetailsImpl.setCreatedBy(ivldFormulaDetails.getCreatedBy());
		ivldFormulaDetailsImpl.setCreatedDate(ivldFormulaDetails.getCreatedDate());
		ivldFormulaDetailsImpl.setSource(ivldFormulaDetails.getSource());
		ivldFormulaDetailsImpl.setAddChgDelIndicator(ivldFormulaDetails.getAddChgDelIndicator());
		ivldFormulaDetailsImpl.setErrorCode(ivldFormulaDetails.getErrorCode());
		ivldFormulaDetailsImpl.setFormulaId(ivldFormulaDetails.getFormulaId());
		ivldFormulaDetailsImpl.setModifiedBy(ivldFormulaDetails.getModifiedBy());
		ivldFormulaDetailsImpl.setIntfInsertedDate(ivldFormulaDetails.getIntfInsertedDate());
		ivldFormulaDetailsImpl.setReprocessedFlag(ivldFormulaDetails.getReprocessedFlag());
		ivldFormulaDetailsImpl.setFormulaDetailsIntfid(ivldFormulaDetails.getFormulaDetailsIntfid());
		ivldFormulaDetailsImpl.setReasonForFailure(ivldFormulaDetails.getReasonForFailure());
		ivldFormulaDetailsImpl.setContractPrice1(ivldFormulaDetails.getContractPrice1());
		ivldFormulaDetailsImpl.setCompanyStringId(ivldFormulaDetails.getCompanyStringId());
		ivldFormulaDetailsImpl.setContractPrice2(ivldFormulaDetails.getContractPrice2());
		ivldFormulaDetailsImpl.setFormulaNo(ivldFormulaDetails.getFormulaNo());
		ivldFormulaDetailsImpl.setStartDate(ivldFormulaDetails.getStartDate());
		ivldFormulaDetailsImpl.setBatchId(ivldFormulaDetails.getBatchId());
		ivldFormulaDetailsImpl.setErrorField(ivldFormulaDetails.getErrorField());
		ivldFormulaDetailsImpl.setContractPrice3(ivldFormulaDetails.getContractPrice3());
		ivldFormulaDetailsImpl.setIvldFormulaDetailsSid(ivldFormulaDetails.getIvldFormulaDetailsSid());
		ivldFormulaDetailsImpl.setCheckRecord(ivldFormulaDetails.isCheckRecord());

		return ivldFormulaDetailsImpl;
	}

	/**
	 * Returns the ivld formula details with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivld formula details
	 * @return the ivld formula details
	 * @throws NoSuchIvldFormulaDetailsException if a ivld formula details with the primary key could not be found
	 */
	@Override
	public IvldFormulaDetails findByPrimaryKey(Serializable primaryKey)
		throws NoSuchIvldFormulaDetailsException {
		IvldFormulaDetails ivldFormulaDetails = fetchByPrimaryKey(primaryKey);

		if (ivldFormulaDetails == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchIvldFormulaDetailsException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return ivldFormulaDetails;
	}

	/**
	 * Returns the ivld formula details with the primary key or throws a {@link NoSuchIvldFormulaDetailsException} if it could not be found.
	 *
	 * @param ivldFormulaDetailsSid the primary key of the ivld formula details
	 * @return the ivld formula details
	 * @throws NoSuchIvldFormulaDetailsException if a ivld formula details with the primary key could not be found
	 */
	@Override
	public IvldFormulaDetails findByPrimaryKey(int ivldFormulaDetailsSid)
		throws NoSuchIvldFormulaDetailsException {
		return findByPrimaryKey((Serializable)ivldFormulaDetailsSid);
	}

	/**
	 * Returns the ivld formula details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivld formula details
	 * @return the ivld formula details, or <code>null</code> if a ivld formula details with the primary key could not be found
	 */
	@Override
	public IvldFormulaDetails fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(IvldFormulaDetailsModelImpl.ENTITY_CACHE_ENABLED,
				IvldFormulaDetailsImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		IvldFormulaDetails ivldFormulaDetails = (IvldFormulaDetails)serializable;

		if (ivldFormulaDetails == null) {
			Session session = null;

			try {
				session = openSession();

				ivldFormulaDetails = (IvldFormulaDetails)session.get(IvldFormulaDetailsImpl.class,
						primaryKey);

				if (ivldFormulaDetails != null) {
					cacheResult(ivldFormulaDetails);
				}
				else {
					entityCache.putResult(IvldFormulaDetailsModelImpl.ENTITY_CACHE_ENABLED,
						IvldFormulaDetailsImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(IvldFormulaDetailsModelImpl.ENTITY_CACHE_ENABLED,
					IvldFormulaDetailsImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return ivldFormulaDetails;
	}

	/**
	 * Returns the ivld formula details with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ivldFormulaDetailsSid the primary key of the ivld formula details
	 * @return the ivld formula details, or <code>null</code> if a ivld formula details with the primary key could not be found
	 */
	@Override
	public IvldFormulaDetails fetchByPrimaryKey(int ivldFormulaDetailsSid) {
		return fetchByPrimaryKey((Serializable)ivldFormulaDetailsSid);
	}

	@Override
	public Map<Serializable, IvldFormulaDetails> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, IvldFormulaDetails> map = new HashMap<Serializable, IvldFormulaDetails>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			IvldFormulaDetails ivldFormulaDetails = fetchByPrimaryKey(primaryKey);

			if (ivldFormulaDetails != null) {
				map.put(primaryKey, ivldFormulaDetails);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(IvldFormulaDetailsModelImpl.ENTITY_CACHE_ENABLED,
					IvldFormulaDetailsImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (IvldFormulaDetails)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_IVLDFORMULADETAILS_WHERE_PKS_IN);

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

			for (IvldFormulaDetails ivldFormulaDetails : (List<IvldFormulaDetails>)q.list()) {
				map.put(ivldFormulaDetails.getPrimaryKeyObj(),
					ivldFormulaDetails);

				cacheResult(ivldFormulaDetails);

				uncachedPrimaryKeys.remove(ivldFormulaDetails.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(IvldFormulaDetailsModelImpl.ENTITY_CACHE_ENABLED,
					IvldFormulaDetailsImpl.class, primaryKey, nullModel);
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
	 * Returns all the ivld formula detailses.
	 *
	 * @return the ivld formula detailses
	 */
	@Override
	public List<IvldFormulaDetails> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ivld formula detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldFormulaDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld formula detailses
	 * @param end the upper bound of the range of ivld formula detailses (not inclusive)
	 * @return the range of ivld formula detailses
	 */
	@Override
	public List<IvldFormulaDetails> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ivld formula detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldFormulaDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld formula detailses
	 * @param end the upper bound of the range of ivld formula detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ivld formula detailses
	 */
	@Override
	public List<IvldFormulaDetails> findAll(int start, int end,
		OrderByComparator<IvldFormulaDetails> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ivld formula detailses.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldFormulaDetailsModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld formula detailses
	 * @param end the upper bound of the range of ivld formula detailses (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ivld formula detailses
	 */
	@Override
	public List<IvldFormulaDetails> findAll(int start, int end,
		OrderByComparator<IvldFormulaDetails> orderByComparator,
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

		List<IvldFormulaDetails> list = null;

		if (retrieveFromCache) {
			list = (List<IvldFormulaDetails>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_IVLDFORMULADETAILS);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_IVLDFORMULADETAILS;

				if (pagination) {
					sql = sql.concat(IvldFormulaDetailsModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<IvldFormulaDetails>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<IvldFormulaDetails>)QueryUtil.list(q,
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
	 * Removes all the ivld formula detailses from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (IvldFormulaDetails ivldFormulaDetails : findAll()) {
			remove(ivldFormulaDetails);
		}
	}

	/**
	 * Returns the number of ivld formula detailses.
	 *
	 * @return the number of ivld formula detailses
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_IVLDFORMULADETAILS);

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
		return IvldFormulaDetailsModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ivld formula details persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(IvldFormulaDetailsImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_IVLDFORMULADETAILS = "SELECT ivldFormulaDetails FROM IvldFormulaDetails ivldFormulaDetails";
	private static final String _SQL_SELECT_IVLDFORMULADETAILS_WHERE_PKS_IN = "SELECT ivldFormulaDetails FROM IvldFormulaDetails ivldFormulaDetails WHERE IVLD_FORMULA_DETAILS_SID IN (";
	private static final String _SQL_COUNT_IVLDFORMULADETAILS = "SELECT COUNT(ivldFormulaDetails) FROM IvldFormulaDetails ivldFormulaDetails";
	private static final String _ORDER_BY_ENTITY_ALIAS = "ivldFormulaDetails.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IvldFormulaDetails exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(IvldFormulaDetailsPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"endDate", "rebatePercent1", "itemId", "rebatePercent2",
				"formulaDesc", "modifiedDate", "rebatePercent3", "createdBy",
				"createdDate", "source", "addChgDelIndicator", "errorCode",
				"formulaId", "modifiedBy", "intfInsertedDate", "reprocessedFlag",
				"formulaDetailsIntfid", "reasonForFailure", "contractPrice1",
				"companyStringId", "contractPrice2", "formulaNo", "startDate",
				"batchId", "errorField", "contractPrice3",
				"ivldFormulaDetailsSid", "checkRecord"
			});
}