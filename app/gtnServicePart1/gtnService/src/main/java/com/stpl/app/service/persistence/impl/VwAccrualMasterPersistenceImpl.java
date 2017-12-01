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

import com.stpl.app.exception.NoSuchVwAccrualMasterException;
import com.stpl.app.model.VwAccrualMaster;
import com.stpl.app.model.impl.VwAccrualMasterImpl;
import com.stpl.app.model.impl.VwAccrualMasterModelImpl;
import com.stpl.app.service.persistence.VwAccrualMasterPersistence;

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
 * The persistence implementation for the vw accrual master service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see VwAccrualMasterPersistence
 * @see com.stpl.app.service.persistence.VwAccrualMasterUtil
 * @generated
 */
@ProviderType
public class VwAccrualMasterPersistenceImpl extends BasePersistenceImpl<VwAccrualMaster>
	implements VwAccrualMasterPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link VwAccrualMasterUtil} to access the vw accrual master persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = VwAccrualMasterImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(VwAccrualMasterModelImpl.ENTITY_CACHE_ENABLED,
			VwAccrualMasterModelImpl.FINDER_CACHE_ENABLED,
			VwAccrualMasterImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(VwAccrualMasterModelImpl.ENTITY_CACHE_ENABLED,
			VwAccrualMasterModelImpl.FINDER_CACHE_ENABLED,
			VwAccrualMasterImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(VwAccrualMasterModelImpl.ENTITY_CACHE_ENABLED,
			VwAccrualMasterModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public VwAccrualMasterPersistenceImpl() {
		setModelClass(VwAccrualMaster.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("itemQualifier", "ITEM_QUALIFIER");
			dbColumnNames.put("businessUnitQualifier", "BUSINESS_UNIT_QUALIFIER");
			dbColumnNames.put("itemNo", "ITEM_NO");
			dbColumnNames.put("postingIndicator", "POSTING_INDICATOR");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("costCenter", "COST_CENTER");
			dbColumnNames.put("subLedger", "SUB_LEDGER");
			dbColumnNames.put("accrualPeriodEd", "ACCRUAL_PERIOD_ED");
			dbColumnNames.put("accrualId", "ACCRUAL_ID");
			dbColumnNames.put("companyQualifier", "COMPANY_QUALIFIER");
			dbColumnNames.put("contractNo", "CONTRACT_NO");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("itemName", "ITEM_NAME");
			dbColumnNames.put("brandId", "BRAND_ID");
			dbColumnNames.put("postingDate", "POSTING_DATE");
			dbColumnNames.put("businessUnitName", "BUSINESS_UNIT_NAME");
			dbColumnNames.put("salesId", "SALES_ID");
			dbColumnNames.put("amount", "AMOUNT");
			dbColumnNames.put("businessUnitNo", "BUSINESS_UNIT_NO");
			dbColumnNames.put("subLedgerType", "SUB_LEDGER_TYPE");
			dbColumnNames.put("documentType", "DOCUMENT_TYPE");
			dbColumnNames.put("accuralType", "ACCURAL_TYPE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("programNo", "PROGRAM_NO");
			dbColumnNames.put("customerId", "CUSTOMER_ID");
			dbColumnNames.put("itemId", "ITEM_ID");
			dbColumnNames.put("accrualMasterSid", "ACCRUAL_MASTER_SID");
			dbColumnNames.put("contractId", "CONTRACT_ID");
			dbColumnNames.put("contractName", "CONTRACT_NAME");
			dbColumnNames.put("glAccount", "GL_ACCOUNT");
			dbColumnNames.put("glDate", "GL_DATE");
			dbColumnNames.put("businessUnitId", "BUSINESS_UNIT_ID");
			dbColumnNames.put("programType", "PROGRAM_TYPE");
			dbColumnNames.put("customerName", "CUSTOMER_NAME");
			dbColumnNames.put("customerNo", "CUSTOMER_NO");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("accrualPeriodSd", "ACCRUAL_PERIOD_SD");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the vw accrual master in the entity cache if it is enabled.
	 *
	 * @param vwAccrualMaster the vw accrual master
	 */
	@Override
	public void cacheResult(VwAccrualMaster vwAccrualMaster) {
		entityCache.putResult(VwAccrualMasterModelImpl.ENTITY_CACHE_ENABLED,
			VwAccrualMasterImpl.class, vwAccrualMaster.getPrimaryKey(),
			vwAccrualMaster);

		vwAccrualMaster.resetOriginalValues();
	}

	/**
	 * Caches the vw accrual masters in the entity cache if it is enabled.
	 *
	 * @param vwAccrualMasters the vw accrual masters
	 */
	@Override
	public void cacheResult(List<VwAccrualMaster> vwAccrualMasters) {
		for (VwAccrualMaster vwAccrualMaster : vwAccrualMasters) {
			if (entityCache.getResult(
						VwAccrualMasterModelImpl.ENTITY_CACHE_ENABLED,
						VwAccrualMasterImpl.class,
						vwAccrualMaster.getPrimaryKey()) == null) {
				cacheResult(vwAccrualMaster);
			}
			else {
				vwAccrualMaster.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all vw accrual masters.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(VwAccrualMasterImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the vw accrual master.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(VwAccrualMaster vwAccrualMaster) {
		entityCache.removeResult(VwAccrualMasterModelImpl.ENTITY_CACHE_ENABLED,
			VwAccrualMasterImpl.class, vwAccrualMaster.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<VwAccrualMaster> vwAccrualMasters) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (VwAccrualMaster vwAccrualMaster : vwAccrualMasters) {
			entityCache.removeResult(VwAccrualMasterModelImpl.ENTITY_CACHE_ENABLED,
				VwAccrualMasterImpl.class, vwAccrualMaster.getPrimaryKey());
		}
	}

	/**
	 * Creates a new vw accrual master with the primary key. Does not add the vw accrual master to the database.
	 *
	 * @param accrualMasterSid the primary key for the new vw accrual master
	 * @return the new vw accrual master
	 */
	@Override
	public VwAccrualMaster create(int accrualMasterSid) {
		VwAccrualMaster vwAccrualMaster = new VwAccrualMasterImpl();

		vwAccrualMaster.setNew(true);
		vwAccrualMaster.setPrimaryKey(accrualMasterSid);

		return vwAccrualMaster;
	}

	/**
	 * Removes the vw accrual master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param accrualMasterSid the primary key of the vw accrual master
	 * @return the vw accrual master that was removed
	 * @throws NoSuchVwAccrualMasterException if a vw accrual master with the primary key could not be found
	 */
	@Override
	public VwAccrualMaster remove(int accrualMasterSid)
		throws NoSuchVwAccrualMasterException {
		return remove((Serializable)accrualMasterSid);
	}

	/**
	 * Removes the vw accrual master with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the vw accrual master
	 * @return the vw accrual master that was removed
	 * @throws NoSuchVwAccrualMasterException if a vw accrual master with the primary key could not be found
	 */
	@Override
	public VwAccrualMaster remove(Serializable primaryKey)
		throws NoSuchVwAccrualMasterException {
		Session session = null;

		try {
			session = openSession();

			VwAccrualMaster vwAccrualMaster = (VwAccrualMaster)session.get(VwAccrualMasterImpl.class,
					primaryKey);

			if (vwAccrualMaster == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchVwAccrualMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(vwAccrualMaster);
		}
		catch (NoSuchVwAccrualMasterException nsee) {
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
	protected VwAccrualMaster removeImpl(VwAccrualMaster vwAccrualMaster) {
		vwAccrualMaster = toUnwrappedModel(vwAccrualMaster);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(vwAccrualMaster)) {
				vwAccrualMaster = (VwAccrualMaster)session.get(VwAccrualMasterImpl.class,
						vwAccrualMaster.getPrimaryKeyObj());
			}

			if (vwAccrualMaster != null) {
				session.delete(vwAccrualMaster);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (vwAccrualMaster != null) {
			clearCache(vwAccrualMaster);
		}

		return vwAccrualMaster;
	}

	@Override
	public VwAccrualMaster updateImpl(VwAccrualMaster vwAccrualMaster) {
		vwAccrualMaster = toUnwrappedModel(vwAccrualMaster);

		boolean isNew = vwAccrualMaster.isNew();

		Session session = null;

		try {
			session = openSession();

			if (vwAccrualMaster.isNew()) {
				session.save(vwAccrualMaster);

				vwAccrualMaster.setNew(false);
			}
			else {
				vwAccrualMaster = (VwAccrualMaster)session.merge(vwAccrualMaster);
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

		entityCache.putResult(VwAccrualMasterModelImpl.ENTITY_CACHE_ENABLED,
			VwAccrualMasterImpl.class, vwAccrualMaster.getPrimaryKey(),
			vwAccrualMaster, false);

		vwAccrualMaster.resetOriginalValues();

		return vwAccrualMaster;
	}

	protected VwAccrualMaster toUnwrappedModel(VwAccrualMaster vwAccrualMaster) {
		if (vwAccrualMaster instanceof VwAccrualMasterImpl) {
			return vwAccrualMaster;
		}

		VwAccrualMasterImpl vwAccrualMasterImpl = new VwAccrualMasterImpl();

		vwAccrualMasterImpl.setNew(vwAccrualMaster.isNew());
		vwAccrualMasterImpl.setPrimaryKey(vwAccrualMaster.getPrimaryKey());

		vwAccrualMasterImpl.setItemQualifier(vwAccrualMaster.getItemQualifier());
		vwAccrualMasterImpl.setBusinessUnitQualifier(vwAccrualMaster.getBusinessUnitQualifier());
		vwAccrualMasterImpl.setItemNo(vwAccrualMaster.getItemNo());
		vwAccrualMasterImpl.setPostingIndicator(vwAccrualMaster.getPostingIndicator());
		vwAccrualMasterImpl.setCreatedDate(vwAccrualMaster.getCreatedDate());
		vwAccrualMasterImpl.setCostCenter(vwAccrualMaster.getCostCenter());
		vwAccrualMasterImpl.setSubLedger(vwAccrualMaster.getSubLedger());
		vwAccrualMasterImpl.setAccrualPeriodEd(vwAccrualMaster.getAccrualPeriodEd());
		vwAccrualMasterImpl.setAccrualId(vwAccrualMaster.getAccrualId());
		vwAccrualMasterImpl.setCompanyQualifier(vwAccrualMaster.getCompanyQualifier());
		vwAccrualMasterImpl.setContractNo(vwAccrualMaster.getContractNo());
		vwAccrualMasterImpl.setBatchId(vwAccrualMaster.getBatchId());
		vwAccrualMasterImpl.setItemName(vwAccrualMaster.getItemName());
		vwAccrualMasterImpl.setBrandId(vwAccrualMaster.getBrandId());
		vwAccrualMasterImpl.setPostingDate(vwAccrualMaster.getPostingDate());
		vwAccrualMasterImpl.setBusinessUnitName(vwAccrualMaster.getBusinessUnitName());
		vwAccrualMasterImpl.setSalesId(vwAccrualMaster.getSalesId());
		vwAccrualMasterImpl.setAmount(vwAccrualMaster.getAmount());
		vwAccrualMasterImpl.setBusinessUnitNo(vwAccrualMaster.getBusinessUnitNo());
		vwAccrualMasterImpl.setSubLedgerType(vwAccrualMaster.getSubLedgerType());
		vwAccrualMasterImpl.setDocumentType(vwAccrualMaster.getDocumentType());
		vwAccrualMasterImpl.setAccuralType(vwAccrualMaster.getAccuralType());
		vwAccrualMasterImpl.setCreatedBy(vwAccrualMaster.getCreatedBy());
		vwAccrualMasterImpl.setProgramNo(vwAccrualMaster.getProgramNo());
		vwAccrualMasterImpl.setCustomerId(vwAccrualMaster.getCustomerId());
		vwAccrualMasterImpl.setItemId(vwAccrualMaster.getItemId());
		vwAccrualMasterImpl.setAccrualMasterSid(vwAccrualMaster.getAccrualMasterSid());
		vwAccrualMasterImpl.setContractId(vwAccrualMaster.getContractId());
		vwAccrualMasterImpl.setContractName(vwAccrualMaster.getContractName());
		vwAccrualMasterImpl.setGlAccount(vwAccrualMaster.getGlAccount());
		vwAccrualMasterImpl.setGlDate(vwAccrualMaster.getGlDate());
		vwAccrualMasterImpl.setBusinessUnitId(vwAccrualMaster.getBusinessUnitId());
		vwAccrualMasterImpl.setProgramType(vwAccrualMaster.getProgramType());
		vwAccrualMasterImpl.setCustomerName(vwAccrualMaster.getCustomerName());
		vwAccrualMasterImpl.setCustomerNo(vwAccrualMaster.getCustomerNo());
		vwAccrualMasterImpl.setSource(vwAccrualMaster.getSource());
		vwAccrualMasterImpl.setAccrualPeriodSd(vwAccrualMaster.getAccrualPeriodSd());

		return vwAccrualMasterImpl;
	}

	/**
	 * Returns the vw accrual master with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the vw accrual master
	 * @return the vw accrual master
	 * @throws NoSuchVwAccrualMasterException if a vw accrual master with the primary key could not be found
	 */
	@Override
	public VwAccrualMaster findByPrimaryKey(Serializable primaryKey)
		throws NoSuchVwAccrualMasterException {
		VwAccrualMaster vwAccrualMaster = fetchByPrimaryKey(primaryKey);

		if (vwAccrualMaster == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchVwAccrualMasterException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return vwAccrualMaster;
	}

	/**
	 * Returns the vw accrual master with the primary key or throws a {@link NoSuchVwAccrualMasterException} if it could not be found.
	 *
	 * @param accrualMasterSid the primary key of the vw accrual master
	 * @return the vw accrual master
	 * @throws NoSuchVwAccrualMasterException if a vw accrual master with the primary key could not be found
	 */
	@Override
	public VwAccrualMaster findByPrimaryKey(int accrualMasterSid)
		throws NoSuchVwAccrualMasterException {
		return findByPrimaryKey((Serializable)accrualMasterSid);
	}

	/**
	 * Returns the vw accrual master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the vw accrual master
	 * @return the vw accrual master, or <code>null</code> if a vw accrual master with the primary key could not be found
	 */
	@Override
	public VwAccrualMaster fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(VwAccrualMasterModelImpl.ENTITY_CACHE_ENABLED,
				VwAccrualMasterImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		VwAccrualMaster vwAccrualMaster = (VwAccrualMaster)serializable;

		if (vwAccrualMaster == null) {
			Session session = null;

			try {
				session = openSession();

				vwAccrualMaster = (VwAccrualMaster)session.get(VwAccrualMasterImpl.class,
						primaryKey);

				if (vwAccrualMaster != null) {
					cacheResult(vwAccrualMaster);
				}
				else {
					entityCache.putResult(VwAccrualMasterModelImpl.ENTITY_CACHE_ENABLED,
						VwAccrualMasterImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(VwAccrualMasterModelImpl.ENTITY_CACHE_ENABLED,
					VwAccrualMasterImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return vwAccrualMaster;
	}

	/**
	 * Returns the vw accrual master with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param accrualMasterSid the primary key of the vw accrual master
	 * @return the vw accrual master, or <code>null</code> if a vw accrual master with the primary key could not be found
	 */
	@Override
	public VwAccrualMaster fetchByPrimaryKey(int accrualMasterSid) {
		return fetchByPrimaryKey((Serializable)accrualMasterSid);
	}

	@Override
	public Map<Serializable, VwAccrualMaster> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, VwAccrualMaster> map = new HashMap<Serializable, VwAccrualMaster>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			VwAccrualMaster vwAccrualMaster = fetchByPrimaryKey(primaryKey);

			if (vwAccrualMaster != null) {
				map.put(primaryKey, vwAccrualMaster);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(VwAccrualMasterModelImpl.ENTITY_CACHE_ENABLED,
					VwAccrualMasterImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (VwAccrualMaster)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_VWACCRUALMASTER_WHERE_PKS_IN);

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

			for (VwAccrualMaster vwAccrualMaster : (List<VwAccrualMaster>)q.list()) {
				map.put(vwAccrualMaster.getPrimaryKeyObj(), vwAccrualMaster);

				cacheResult(vwAccrualMaster);

				uncachedPrimaryKeys.remove(vwAccrualMaster.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(VwAccrualMasterModelImpl.ENTITY_CACHE_ENABLED,
					VwAccrualMasterImpl.class, primaryKey, nullModel);
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
	 * Returns all the vw accrual masters.
	 *
	 * @return the vw accrual masters
	 */
	@Override
	public List<VwAccrualMaster> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the vw accrual masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwAccrualMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of vw accrual masters
	 * @param end the upper bound of the range of vw accrual masters (not inclusive)
	 * @return the range of vw accrual masters
	 */
	@Override
	public List<VwAccrualMaster> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the vw accrual masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwAccrualMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of vw accrual masters
	 * @param end the upper bound of the range of vw accrual masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of vw accrual masters
	 */
	@Override
	public List<VwAccrualMaster> findAll(int start, int end,
		OrderByComparator<VwAccrualMaster> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the vw accrual masters.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link VwAccrualMasterModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of vw accrual masters
	 * @param end the upper bound of the range of vw accrual masters (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of vw accrual masters
	 */
	@Override
	public List<VwAccrualMaster> findAll(int start, int end,
		OrderByComparator<VwAccrualMaster> orderByComparator,
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

		List<VwAccrualMaster> list = null;

		if (retrieveFromCache) {
			list = (List<VwAccrualMaster>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_VWACCRUALMASTER);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_VWACCRUALMASTER;

				if (pagination) {
					sql = sql.concat(VwAccrualMasterModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<VwAccrualMaster>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<VwAccrualMaster>)QueryUtil.list(q,
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
	 * Removes all the vw accrual masters from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (VwAccrualMaster vwAccrualMaster : findAll()) {
			remove(vwAccrualMaster);
		}
	}

	/**
	 * Returns the number of vw accrual masters.
	 *
	 * @return the number of vw accrual masters
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_VWACCRUALMASTER);

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
		return VwAccrualMasterModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the vw accrual master persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(VwAccrualMasterImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_VWACCRUALMASTER = "SELECT vwAccrualMaster FROM VwAccrualMaster vwAccrualMaster";
	private static final String _SQL_SELECT_VWACCRUALMASTER_WHERE_PKS_IN = "SELECT vwAccrualMaster FROM VwAccrualMaster vwAccrualMaster WHERE ACCRUAL_MASTER_SID IN (";
	private static final String _SQL_COUNT_VWACCRUALMASTER = "SELECT COUNT(vwAccrualMaster) FROM VwAccrualMaster vwAccrualMaster";
	private static final String _ORDER_BY_ENTITY_ALIAS = "vwAccrualMaster.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No VwAccrualMaster exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(VwAccrualMasterPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"itemQualifier", "businessUnitQualifier", "itemNo",
				"postingIndicator", "createdDate", "costCenter", "subLedger",
				"accrualPeriodEd", "accrualId", "companyQualifier", "contractNo",
				"batchId", "itemName", "brandId", "postingDate",
				"businessUnitName", "salesId", "amount", "businessUnitNo",
				"subLedgerType", "documentType", "accuralType", "createdBy",
				"programNo", "customerId", "itemId", "accrualMasterSid",
				"contractId", "contractName", "glAccount", "glDate",
				"businessUnitId", "programType", "customerName", "customerNo",
				"source", "accrualPeriodSd"
			});
}