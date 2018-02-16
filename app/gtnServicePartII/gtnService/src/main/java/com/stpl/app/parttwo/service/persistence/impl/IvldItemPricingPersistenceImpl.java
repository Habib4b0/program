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

import com.stpl.app.parttwo.exception.NoSuchIvldItemPricingException;
import com.stpl.app.parttwo.model.IvldItemPricing;
import com.stpl.app.parttwo.model.impl.IvldItemPricingImpl;
import com.stpl.app.parttwo.model.impl.IvldItemPricingModelImpl;
import com.stpl.app.parttwo.service.persistence.IvldItemPricingPersistence;

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
 * The persistence implementation for the ivld item pricing service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldItemPricingPersistence
 * @see com.stpl.app.parttwo.service.persistence.IvldItemPricingUtil
 * @generated
 */
@ProviderType
public class IvldItemPricingPersistenceImpl extends BasePersistenceImpl<IvldItemPricing>
	implements IvldItemPricingPersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link IvldItemPricingUtil} to access the ivld item pricing persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = IvldItemPricingImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IvldItemPricingModelImpl.ENTITY_CACHE_ENABLED,
			IvldItemPricingModelImpl.FINDER_CACHE_ENABLED,
			IvldItemPricingImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IvldItemPricingModelImpl.ENTITY_CACHE_ENABLED,
			IvldItemPricingModelImpl.FINDER_CACHE_ENABLED,
			IvldItemPricingImpl.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IvldItemPricingModelImpl.ENTITY_CACHE_ENABLED,
			IvldItemPricingModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public IvldItemPricingPersistenceImpl() {
		setModelClass(IvldItemPricing.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("itemNo", "ITEM_NO");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("pricingCodeQualifierName",
				"PRICING_CODE_QUALIFIER_NAME");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("endDate", "END_DATE");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("itemName", "ITEM_NAME");
			dbColumnNames.put("errorCode", "ERROR_CODE");
			dbColumnNames.put("reprocessedFlag", "REPROCESSED_FLAG");
			dbColumnNames.put("itemPricingIntfid", "ITEM_PRICING_INTFID");
			dbColumnNames.put("ivldItemPricingSid", "IVLD_ITEM_PRICING_SID");
			dbColumnNames.put("pricingCodeStatus", "PRICING_CODE_STATUS");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("itemId", "ITEM_ID");
			dbColumnNames.put("errorField", "ERROR_FIELD");
			dbColumnNames.put("startDate", "START_DATE");
			dbColumnNames.put("itemUom", "ITEM_UOM");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("reasonForFailure", "REASON_FOR_FAILURE");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("pricingCodeQualifier", "PRICING_CODE_QUALIFIER");
			dbColumnNames.put("addChgDelIndicator", "ADD_CHG_DEL_INDICATOR");
			dbColumnNames.put("entityCode", "ENTITY_CODE");
			dbColumnNames.put("itemPrice", "ITEM_PRICE");
			dbColumnNames.put("intfInsertedDate", "INTF_INSERTED_DATE");
			dbColumnNames.put("checkRecord", "CHECK_RECORD");
			dbColumnNames.put("itemPriceprecision", "ITEM_PRICE_PRECISION");

			field.set(this, dbColumnNames);
		}
		catch (Exception e) {
			if (_log.isDebugEnabled()) {
				_log.debug(e, e);
			}
		}
	}

	/**
	 * Caches the ivld item pricing in the entity cache if it is enabled.
	 *
	 * @param ivldItemPricing the ivld item pricing
	 */
	@Override
	public void cacheResult(IvldItemPricing ivldItemPricing) {
		entityCache.putResult(IvldItemPricingModelImpl.ENTITY_CACHE_ENABLED,
			IvldItemPricingImpl.class, ivldItemPricing.getPrimaryKey(),
			ivldItemPricing);

		ivldItemPricing.resetOriginalValues();
	}

	/**
	 * Caches the ivld item pricings in the entity cache if it is enabled.
	 *
	 * @param ivldItemPricings the ivld item pricings
	 */
	@Override
	public void cacheResult(List<IvldItemPricing> ivldItemPricings) {
		for (IvldItemPricing ivldItemPricing : ivldItemPricings) {
			if (entityCache.getResult(
						IvldItemPricingModelImpl.ENTITY_CACHE_ENABLED,
						IvldItemPricingImpl.class,
						ivldItemPricing.getPrimaryKey()) == null) {
				cacheResult(ivldItemPricing);
			}
			else {
				ivldItemPricing.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ivld item pricings.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(IvldItemPricingImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ivld item pricing.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(IvldItemPricing ivldItemPricing) {
		entityCache.removeResult(IvldItemPricingModelImpl.ENTITY_CACHE_ENABLED,
			IvldItemPricingImpl.class, ivldItemPricing.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<IvldItemPricing> ivldItemPricings) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (IvldItemPricing ivldItemPricing : ivldItemPricings) {
			entityCache.removeResult(IvldItemPricingModelImpl.ENTITY_CACHE_ENABLED,
				IvldItemPricingImpl.class, ivldItemPricing.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ivld item pricing with the primary key. Does not add the ivld item pricing to the database.
	 *
	 * @param ivldItemPricingSid the primary key for the new ivld item pricing
	 * @return the new ivld item pricing
	 */
	@Override
	public IvldItemPricing create(int ivldItemPricingSid) {
		IvldItemPricing ivldItemPricing = new IvldItemPricingImpl();

		ivldItemPricing.setNew(true);
		ivldItemPricing.setPrimaryKey(ivldItemPricingSid);

		return ivldItemPricing;
	}

	/**
	 * Removes the ivld item pricing with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ivldItemPricingSid the primary key of the ivld item pricing
	 * @return the ivld item pricing that was removed
	 * @throws NoSuchIvldItemPricingException if a ivld item pricing with the primary key could not be found
	 */
	@Override
	public IvldItemPricing remove(int ivldItemPricingSid)
		throws NoSuchIvldItemPricingException {
		return remove((Serializable)ivldItemPricingSid);
	}

	/**
	 * Removes the ivld item pricing with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ivld item pricing
	 * @return the ivld item pricing that was removed
	 * @throws NoSuchIvldItemPricingException if a ivld item pricing with the primary key could not be found
	 */
	@Override
	public IvldItemPricing remove(Serializable primaryKey)
		throws NoSuchIvldItemPricingException {
		Session session = null;

		try {
			session = openSession();

			IvldItemPricing ivldItemPricing = (IvldItemPricing)session.get(IvldItemPricingImpl.class,
					primaryKey);

			if (ivldItemPricing == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchIvldItemPricingException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ivldItemPricing);
		}
		catch (NoSuchIvldItemPricingException nsee) {
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
	protected IvldItemPricing removeImpl(IvldItemPricing ivldItemPricing) {
		ivldItemPricing = toUnwrappedModel(ivldItemPricing);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ivldItemPricing)) {
				ivldItemPricing = (IvldItemPricing)session.get(IvldItemPricingImpl.class,
						ivldItemPricing.getPrimaryKeyObj());
			}

			if (ivldItemPricing != null) {
				session.delete(ivldItemPricing);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (ivldItemPricing != null) {
			clearCache(ivldItemPricing);
		}

		return ivldItemPricing;
	}

	@Override
	public IvldItemPricing updateImpl(IvldItemPricing ivldItemPricing) {
		ivldItemPricing = toUnwrappedModel(ivldItemPricing);

		boolean isNew = ivldItemPricing.isNew();

		Session session = null;

		try {
			session = openSession();

			if (ivldItemPricing.isNew()) {
				session.save(ivldItemPricing);

				ivldItemPricing.setNew(false);
			}
			else {
				ivldItemPricing = (IvldItemPricing)session.merge(ivldItemPricing);
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

		entityCache.putResult(IvldItemPricingModelImpl.ENTITY_CACHE_ENABLED,
			IvldItemPricingImpl.class, ivldItemPricing.getPrimaryKey(),
			ivldItemPricing, false);

		ivldItemPricing.resetOriginalValues();

		return ivldItemPricing;
	}

	protected IvldItemPricing toUnwrappedModel(IvldItemPricing ivldItemPricing) {
		if (ivldItemPricing instanceof IvldItemPricingImpl) {
			return ivldItemPricing;
		}

		IvldItemPricingImpl ivldItemPricingImpl = new IvldItemPricingImpl();

		ivldItemPricingImpl.setNew(ivldItemPricing.isNew());
		ivldItemPricingImpl.setPrimaryKey(ivldItemPricing.getPrimaryKey());

		ivldItemPricingImpl.setItemNo(ivldItemPricing.getItemNo());
		ivldItemPricingImpl.setModifiedBy(ivldItemPricing.getModifiedBy());
		ivldItemPricingImpl.setPricingCodeQualifierName(ivldItemPricing.getPricingCodeQualifierName());
		ivldItemPricingImpl.setCreatedDate(ivldItemPricing.getCreatedDate());
		ivldItemPricingImpl.setEndDate(ivldItemPricing.getEndDate());
		ivldItemPricingImpl.setBatchId(ivldItemPricing.getBatchId());
		ivldItemPricingImpl.setItemName(ivldItemPricing.getItemName());
		ivldItemPricingImpl.setErrorCode(ivldItemPricing.getErrorCode());
		ivldItemPricingImpl.setReprocessedFlag(ivldItemPricing.getReprocessedFlag());
		ivldItemPricingImpl.setItemPricingIntfid(ivldItemPricing.getItemPricingIntfid());
		ivldItemPricingImpl.setIvldItemPricingSid(ivldItemPricing.getIvldItemPricingSid());
		ivldItemPricingImpl.setPricingCodeStatus(ivldItemPricing.getPricingCodeStatus());
		ivldItemPricingImpl.setCreatedBy(ivldItemPricing.getCreatedBy());
		ivldItemPricingImpl.setItemId(ivldItemPricing.getItemId());
		ivldItemPricingImpl.setErrorField(ivldItemPricing.getErrorField());
		ivldItemPricingImpl.setStartDate(ivldItemPricing.getStartDate());
		ivldItemPricingImpl.setItemUom(ivldItemPricing.getItemUom());
		ivldItemPricingImpl.setModifiedDate(ivldItemPricing.getModifiedDate());
		ivldItemPricingImpl.setReasonForFailure(ivldItemPricing.getReasonForFailure());
		ivldItemPricingImpl.setSource(ivldItemPricing.getSource());
		ivldItemPricingImpl.setPricingCodeQualifier(ivldItemPricing.getPricingCodeQualifier());
		ivldItemPricingImpl.setAddChgDelIndicator(ivldItemPricing.getAddChgDelIndicator());
		ivldItemPricingImpl.setEntityCode(ivldItemPricing.getEntityCode());
		ivldItemPricingImpl.setItemPrice(ivldItemPricing.getItemPrice());
		ivldItemPricingImpl.setIntfInsertedDate(ivldItemPricing.getIntfInsertedDate());
		ivldItemPricingImpl.setCheckRecord(ivldItemPricing.isCheckRecord());
		ivldItemPricingImpl.setItemPriceprecision(ivldItemPricing.getItemPriceprecision());

		return ivldItemPricingImpl;
	}

	/**
	 * Returns the ivld item pricing with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivld item pricing
	 * @return the ivld item pricing
	 * @throws NoSuchIvldItemPricingException if a ivld item pricing with the primary key could not be found
	 */
	@Override
	public IvldItemPricing findByPrimaryKey(Serializable primaryKey)
		throws NoSuchIvldItemPricingException {
		IvldItemPricing ivldItemPricing = fetchByPrimaryKey(primaryKey);

		if (ivldItemPricing == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchIvldItemPricingException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return ivldItemPricing;
	}

	/**
	 * Returns the ivld item pricing with the primary key or throws a {@link NoSuchIvldItemPricingException} if it could not be found.
	 *
	 * @param ivldItemPricingSid the primary key of the ivld item pricing
	 * @return the ivld item pricing
	 * @throws NoSuchIvldItemPricingException if a ivld item pricing with the primary key could not be found
	 */
	@Override
	public IvldItemPricing findByPrimaryKey(int ivldItemPricingSid)
		throws NoSuchIvldItemPricingException {
		return findByPrimaryKey((Serializable)ivldItemPricingSid);
	}

	/**
	 * Returns the ivld item pricing with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivld item pricing
	 * @return the ivld item pricing, or <code>null</code> if a ivld item pricing with the primary key could not be found
	 */
	@Override
	public IvldItemPricing fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(IvldItemPricingModelImpl.ENTITY_CACHE_ENABLED,
				IvldItemPricingImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		IvldItemPricing ivldItemPricing = (IvldItemPricing)serializable;

		if (ivldItemPricing == null) {
			Session session = null;

			try {
				session = openSession();

				ivldItemPricing = (IvldItemPricing)session.get(IvldItemPricingImpl.class,
						primaryKey);

				if (ivldItemPricing != null) {
					cacheResult(ivldItemPricing);
				}
				else {
					entityCache.putResult(IvldItemPricingModelImpl.ENTITY_CACHE_ENABLED,
						IvldItemPricingImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(IvldItemPricingModelImpl.ENTITY_CACHE_ENABLED,
					IvldItemPricingImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return ivldItemPricing;
	}

	/**
	 * Returns the ivld item pricing with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ivldItemPricingSid the primary key of the ivld item pricing
	 * @return the ivld item pricing, or <code>null</code> if a ivld item pricing with the primary key could not be found
	 */
	@Override
	public IvldItemPricing fetchByPrimaryKey(int ivldItemPricingSid) {
		return fetchByPrimaryKey((Serializable)ivldItemPricingSid);
	}

	@Override
	public Map<Serializable, IvldItemPricing> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, IvldItemPricing> map = new HashMap<Serializable, IvldItemPricing>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			IvldItemPricing ivldItemPricing = fetchByPrimaryKey(primaryKey);

			if (ivldItemPricing != null) {
				map.put(primaryKey, ivldItemPricing);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(IvldItemPricingModelImpl.ENTITY_CACHE_ENABLED,
					IvldItemPricingImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (IvldItemPricing)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_IVLDITEMPRICING_WHERE_PKS_IN);

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

			for (IvldItemPricing ivldItemPricing : (List<IvldItemPricing>)q.list()) {
				map.put(ivldItemPricing.getPrimaryKeyObj(), ivldItemPricing);

				cacheResult(ivldItemPricing);

				uncachedPrimaryKeys.remove(ivldItemPricing.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(IvldItemPricingModelImpl.ENTITY_CACHE_ENABLED,
					IvldItemPricingImpl.class, primaryKey, nullModel);
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
	 * Returns all the ivld item pricings.
	 *
	 * @return the ivld item pricings
	 */
	@Override
	public List<IvldItemPricing> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ivld item pricings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld item pricings
	 * @param end the upper bound of the range of ivld item pricings (not inclusive)
	 * @return the range of ivld item pricings
	 */
	@Override
	public List<IvldItemPricing> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ivld item pricings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld item pricings
	 * @param end the upper bound of the range of ivld item pricings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ivld item pricings
	 */
	@Override
	public List<IvldItemPricing> findAll(int start, int end,
		OrderByComparator<IvldItemPricing> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ivld item pricings.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldItemPricingModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld item pricings
	 * @param end the upper bound of the range of ivld item pricings (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ivld item pricings
	 */
	@Override
	public List<IvldItemPricing> findAll(int start, int end,
		OrderByComparator<IvldItemPricing> orderByComparator,
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

		List<IvldItemPricing> list = null;

		if (retrieveFromCache) {
			list = (List<IvldItemPricing>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_IVLDITEMPRICING);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_IVLDITEMPRICING;

				if (pagination) {
					sql = sql.concat(IvldItemPricingModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<IvldItemPricing>)QueryUtil.list(q,
							getDialect(), start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<IvldItemPricing>)QueryUtil.list(q,
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
	 * Removes all the ivld item pricings from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (IvldItemPricing ivldItemPricing : findAll()) {
			remove(ivldItemPricing);
		}
	}

	/**
	 * Returns the number of ivld item pricings.
	 *
	 * @return the number of ivld item pricings
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_IVLDITEMPRICING);

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
		return IvldItemPricingModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ivld item pricing persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(IvldItemPricingImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_IVLDITEMPRICING = "SELECT ivldItemPricing FROM IvldItemPricing ivldItemPricing";
	private static final String _SQL_SELECT_IVLDITEMPRICING_WHERE_PKS_IN = "SELECT ivldItemPricing FROM IvldItemPricing ivldItemPricing WHERE IVLD_ITEM_PRICING_SID IN (";
	private static final String _SQL_COUNT_IVLDITEMPRICING = "SELECT COUNT(ivldItemPricing) FROM IvldItemPricing ivldItemPricing";
	private static final String _ORDER_BY_ENTITY_ALIAS = "ivldItemPricing.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IvldItemPricing exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(IvldItemPricingPersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"itemNo", "modifiedBy", "pricingCodeQualifierName",
				"createdDate", "endDate", "batchId", "itemName", "errorCode",
				"reprocessedFlag", "itemPricingIntfid", "ivldItemPricingSid",
				"pricingCodeStatus", "createdBy", "itemId", "errorField",
				"startDate", "itemUom", "modifiedDate", "reasonForFailure",
				"source", "pricingCodeQualifier", "addChgDelIndicator",
				"entityCode", "itemPrice", "intfInsertedDate", "checkRecord",
				"itemPriceprecision"
			});
}