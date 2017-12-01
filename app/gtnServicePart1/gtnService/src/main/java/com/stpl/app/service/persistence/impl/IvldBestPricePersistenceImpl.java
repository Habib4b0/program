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

import com.stpl.app.exception.NoSuchIvldBestPriceException;
import com.stpl.app.model.IvldBestPrice;
import com.stpl.app.model.impl.IvldBestPriceImpl;
import com.stpl.app.model.impl.IvldBestPriceModelImpl;
import com.stpl.app.service.persistence.IvldBestPricePersistence;

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
 * The persistence implementation for the ivld best price service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author
 * @see IvldBestPricePersistence
 * @see com.stpl.app.service.persistence.IvldBestPriceUtil
 * @generated
 */
@ProviderType
public class IvldBestPricePersistenceImpl extends BasePersistenceImpl<IvldBestPrice>
	implements IvldBestPricePersistence {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this class directly. Always use {@link IvldBestPriceUtil} to access the ivld best price persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this class.
	 */
	public static final String FINDER_CLASS_NAME_ENTITY = IvldBestPriceImpl.class.getName();
	public static final String FINDER_CLASS_NAME_LIST_WITH_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List1";
	public static final String FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION = FINDER_CLASS_NAME_ENTITY +
		".List2";
	public static final FinderPath FINDER_PATH_WITH_PAGINATION_FIND_ALL = new FinderPath(IvldBestPriceModelImpl.ENTITY_CACHE_ENABLED,
			IvldBestPriceModelImpl.FINDER_CACHE_ENABLED,
			IvldBestPriceImpl.class, FINDER_CLASS_NAME_LIST_WITH_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_WITHOUT_PAGINATION_FIND_ALL = new FinderPath(IvldBestPriceModelImpl.ENTITY_CACHE_ENABLED,
			IvldBestPriceModelImpl.FINDER_CACHE_ENABLED,
			IvldBestPriceImpl.class, FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION,
			"findAll", new String[0]);
	public static final FinderPath FINDER_PATH_COUNT_ALL = new FinderPath(IvldBestPriceModelImpl.ENTITY_CACHE_ENABLED,
			IvldBestPriceModelImpl.FINDER_CACHE_ENABLED, Long.class,
			FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION, "countAll", new String[0]);

	public IvldBestPricePersistenceImpl() {
		setModelClass(IvldBestPrice.class);

		try {
			Field field = ReflectionUtil.getDeclaredField(BasePersistenceImpl.class,
					"_dbColumnNames");

			Map<String, String> dbColumnNames = new HashMap<String, String>();

			dbColumnNames.put("itemDesc", "ITEM_DESC");
			dbColumnNames.put("bestPriceIntfid", "BEST_PRICE_INTFID");
			dbColumnNames.put("accountId", "ACCOUNT_ID");
			dbColumnNames.put("sellingPrice", "SELLING_PRICE");
			dbColumnNames.put("period", "PERIOD");
			dbColumnNames.put("itemId", "ITEM_ID");
			dbColumnNames.put("modifiedDate", "MODIFIED_DATE");
			dbColumnNames.put("createdBy", "CREATED_BY");
			dbColumnNames.put("createdDate", "CREATED_DATE");
			dbColumnNames.put("source", "SOURCE");
			dbColumnNames.put("addChgDelIndicator", "ADD_CHG_DEL_INDICATOR");
			dbColumnNames.put("initialDiscount", "INITIAL_DISCOUNT");
			dbColumnNames.put("errorCode", "ERROR_CODE");
			dbColumnNames.put("modifiedBy", "MODIFIED_BY");
			dbColumnNames.put("intfInsertedDate", "INTF_INSERTED_DATE");
			dbColumnNames.put("itemNo", "ITEM_NO");
			dbColumnNames.put("reprocessedFlag", "REPROCESSED_FLAG");
			dbColumnNames.put("contractEndDate", "CONTRACT_END_DATE");
			dbColumnNames.put("ivldBestPriceSid", "IVLD_BEST_PRICE_SID");
			dbColumnNames.put("contractId", "CONTRACT_ID");
			dbColumnNames.put("beginningWacPackage", "BEGINNING_WAC_PACKAGE");
			dbColumnNames.put("reasonForFailure", "REASON_FOR_FAILURE");
			dbColumnNames.put("contractStartDate", "CONTRACT_START_DATE");
			dbColumnNames.put("batchId", "BATCH_ID");
			dbColumnNames.put("errorField", "ERROR_FIELD");
			dbColumnNames.put("initialBestPrice", "INITIAL_BEST_PRICE");
			dbColumnNames.put("contractNo", "CONTRACT_NO");
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
	 * Caches the ivld best price in the entity cache if it is enabled.
	 *
	 * @param ivldBestPrice the ivld best price
	 */
	@Override
	public void cacheResult(IvldBestPrice ivldBestPrice) {
		entityCache.putResult(IvldBestPriceModelImpl.ENTITY_CACHE_ENABLED,
			IvldBestPriceImpl.class, ivldBestPrice.getPrimaryKey(),
			ivldBestPrice);

		ivldBestPrice.resetOriginalValues();
	}

	/**
	 * Caches the ivld best prices in the entity cache if it is enabled.
	 *
	 * @param ivldBestPrices the ivld best prices
	 */
	@Override
	public void cacheResult(List<IvldBestPrice> ivldBestPrices) {
		for (IvldBestPrice ivldBestPrice : ivldBestPrices) {
			if (entityCache.getResult(
						IvldBestPriceModelImpl.ENTITY_CACHE_ENABLED,
						IvldBestPriceImpl.class, ivldBestPrice.getPrimaryKey()) == null) {
				cacheResult(ivldBestPrice);
			}
			else {
				ivldBestPrice.resetOriginalValues();
			}
		}
	}

	/**
	 * Clears the cache for all ivld best prices.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache() {
		entityCache.clearCache(IvldBestPriceImpl.class);

		finderCache.clearCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	/**
	 * Clears the cache for the ivld best price.
	 *
	 * <p>
	 * The {@link EntityCache} and {@link FinderCache} are both cleared by this method.
	 * </p>
	 */
	@Override
	public void clearCache(IvldBestPrice ivldBestPrice) {
		entityCache.removeResult(IvldBestPriceModelImpl.ENTITY_CACHE_ENABLED,
			IvldBestPriceImpl.class, ivldBestPrice.getPrimaryKey());

		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@Override
	public void clearCache(List<IvldBestPrice> ivldBestPrices) {
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.clearCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);

		for (IvldBestPrice ivldBestPrice : ivldBestPrices) {
			entityCache.removeResult(IvldBestPriceModelImpl.ENTITY_CACHE_ENABLED,
				IvldBestPriceImpl.class, ivldBestPrice.getPrimaryKey());
		}
	}

	/**
	 * Creates a new ivld best price with the primary key. Does not add the ivld best price to the database.
	 *
	 * @param ivldBestPriceSid the primary key for the new ivld best price
	 * @return the new ivld best price
	 */
	@Override
	public IvldBestPrice create(int ivldBestPriceSid) {
		IvldBestPrice ivldBestPrice = new IvldBestPriceImpl();

		ivldBestPrice.setNew(true);
		ivldBestPrice.setPrimaryKey(ivldBestPriceSid);

		return ivldBestPrice;
	}

	/**
	 * Removes the ivld best price with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param ivldBestPriceSid the primary key of the ivld best price
	 * @return the ivld best price that was removed
	 * @throws NoSuchIvldBestPriceException if a ivld best price with the primary key could not be found
	 */
	@Override
	public IvldBestPrice remove(int ivldBestPriceSid)
		throws NoSuchIvldBestPriceException {
		return remove((Serializable)ivldBestPriceSid);
	}

	/**
	 * Removes the ivld best price with the primary key from the database. Also notifies the appropriate model listeners.
	 *
	 * @param primaryKey the primary key of the ivld best price
	 * @return the ivld best price that was removed
	 * @throws NoSuchIvldBestPriceException if a ivld best price with the primary key could not be found
	 */
	@Override
	public IvldBestPrice remove(Serializable primaryKey)
		throws NoSuchIvldBestPriceException {
		Session session = null;

		try {
			session = openSession();

			IvldBestPrice ivldBestPrice = (IvldBestPrice)session.get(IvldBestPriceImpl.class,
					primaryKey);

			if (ivldBestPrice == null) {
				if (_log.isDebugEnabled()) {
					_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
				}

				throw new NoSuchIvldBestPriceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
					primaryKey);
			}

			return remove(ivldBestPrice);
		}
		catch (NoSuchIvldBestPriceException nsee) {
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
	protected IvldBestPrice removeImpl(IvldBestPrice ivldBestPrice) {
		ivldBestPrice = toUnwrappedModel(ivldBestPrice);

		Session session = null;

		try {
			session = openSession();

			if (!session.contains(ivldBestPrice)) {
				ivldBestPrice = (IvldBestPrice)session.get(IvldBestPriceImpl.class,
						ivldBestPrice.getPrimaryKeyObj());
			}

			if (ivldBestPrice != null) {
				session.delete(ivldBestPrice);
			}
		}
		catch (Exception e) {
			throw processException(e);
		}
		finally {
			closeSession(session);
		}

		if (ivldBestPrice != null) {
			clearCache(ivldBestPrice);
		}

		return ivldBestPrice;
	}

	@Override
	public IvldBestPrice updateImpl(IvldBestPrice ivldBestPrice) {
		ivldBestPrice = toUnwrappedModel(ivldBestPrice);

		boolean isNew = ivldBestPrice.isNew();

		Session session = null;

		try {
			session = openSession();

			if (ivldBestPrice.isNew()) {
				session.save(ivldBestPrice);

				ivldBestPrice.setNew(false);
			}
			else {
				ivldBestPrice = (IvldBestPrice)session.merge(ivldBestPrice);
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

		entityCache.putResult(IvldBestPriceModelImpl.ENTITY_CACHE_ENABLED,
			IvldBestPriceImpl.class, ivldBestPrice.getPrimaryKey(),
			ivldBestPrice, false);

		ivldBestPrice.resetOriginalValues();

		return ivldBestPrice;
	}

	protected IvldBestPrice toUnwrappedModel(IvldBestPrice ivldBestPrice) {
		if (ivldBestPrice instanceof IvldBestPriceImpl) {
			return ivldBestPrice;
		}

		IvldBestPriceImpl ivldBestPriceImpl = new IvldBestPriceImpl();

		ivldBestPriceImpl.setNew(ivldBestPrice.isNew());
		ivldBestPriceImpl.setPrimaryKey(ivldBestPrice.getPrimaryKey());

		ivldBestPriceImpl.setItemDesc(ivldBestPrice.getItemDesc());
		ivldBestPriceImpl.setBestPriceIntfid(ivldBestPrice.getBestPriceIntfid());
		ivldBestPriceImpl.setAccountId(ivldBestPrice.getAccountId());
		ivldBestPriceImpl.setSellingPrice(ivldBestPrice.getSellingPrice());
		ivldBestPriceImpl.setPeriod(ivldBestPrice.getPeriod());
		ivldBestPriceImpl.setItemId(ivldBestPrice.getItemId());
		ivldBestPriceImpl.setModifiedDate(ivldBestPrice.getModifiedDate());
		ivldBestPriceImpl.setCreatedBy(ivldBestPrice.getCreatedBy());
		ivldBestPriceImpl.setCreatedDate(ivldBestPrice.getCreatedDate());
		ivldBestPriceImpl.setSource(ivldBestPrice.getSource());
		ivldBestPriceImpl.setAddChgDelIndicator(ivldBestPrice.getAddChgDelIndicator());
		ivldBestPriceImpl.setInitialDiscount(ivldBestPrice.getInitialDiscount());
		ivldBestPriceImpl.setErrorCode(ivldBestPrice.getErrorCode());
		ivldBestPriceImpl.setModifiedBy(ivldBestPrice.getModifiedBy());
		ivldBestPriceImpl.setIntfInsertedDate(ivldBestPrice.getIntfInsertedDate());
		ivldBestPriceImpl.setItemNo(ivldBestPrice.getItemNo());
		ivldBestPriceImpl.setReprocessedFlag(ivldBestPrice.getReprocessedFlag());
		ivldBestPriceImpl.setContractEndDate(ivldBestPrice.getContractEndDate());
		ivldBestPriceImpl.setIvldBestPriceSid(ivldBestPrice.getIvldBestPriceSid());
		ivldBestPriceImpl.setContractId(ivldBestPrice.getContractId());
		ivldBestPriceImpl.setBeginningWacPackage(ivldBestPrice.getBeginningWacPackage());
		ivldBestPriceImpl.setReasonForFailure(ivldBestPrice.getReasonForFailure());
		ivldBestPriceImpl.setContractStartDate(ivldBestPrice.getContractStartDate());
		ivldBestPriceImpl.setBatchId(ivldBestPrice.getBatchId());
		ivldBestPriceImpl.setErrorField(ivldBestPrice.getErrorField());
		ivldBestPriceImpl.setInitialBestPrice(ivldBestPrice.getInitialBestPrice());
		ivldBestPriceImpl.setContractNo(ivldBestPrice.getContractNo());
		ivldBestPriceImpl.setCheckRecord(ivldBestPrice.isCheckRecord());

		return ivldBestPriceImpl;
	}

	/**
	 * Returns the ivld best price with the primary key or throws a {@link com.liferay.portal.kernel.exception.NoSuchModelException} if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivld best price
	 * @return the ivld best price
	 * @throws NoSuchIvldBestPriceException if a ivld best price with the primary key could not be found
	 */
	@Override
	public IvldBestPrice findByPrimaryKey(Serializable primaryKey)
		throws NoSuchIvldBestPriceException {
		IvldBestPrice ivldBestPrice = fetchByPrimaryKey(primaryKey);

		if (ivldBestPrice == null) {
			if (_log.isDebugEnabled()) {
				_log.debug(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY + primaryKey);
			}

			throw new NoSuchIvldBestPriceException(_NO_SUCH_ENTITY_WITH_PRIMARY_KEY +
				primaryKey);
		}

		return ivldBestPrice;
	}

	/**
	 * Returns the ivld best price with the primary key or throws a {@link NoSuchIvldBestPriceException} if it could not be found.
	 *
	 * @param ivldBestPriceSid the primary key of the ivld best price
	 * @return the ivld best price
	 * @throws NoSuchIvldBestPriceException if a ivld best price with the primary key could not be found
	 */
	@Override
	public IvldBestPrice findByPrimaryKey(int ivldBestPriceSid)
		throws NoSuchIvldBestPriceException {
		return findByPrimaryKey((Serializable)ivldBestPriceSid);
	}

	/**
	 * Returns the ivld best price with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param primaryKey the primary key of the ivld best price
	 * @return the ivld best price, or <code>null</code> if a ivld best price with the primary key could not be found
	 */
	@Override
	public IvldBestPrice fetchByPrimaryKey(Serializable primaryKey) {
		Serializable serializable = entityCache.getResult(IvldBestPriceModelImpl.ENTITY_CACHE_ENABLED,
				IvldBestPriceImpl.class, primaryKey);

		if (serializable == nullModel) {
			return null;
		}

		IvldBestPrice ivldBestPrice = (IvldBestPrice)serializable;

		if (ivldBestPrice == null) {
			Session session = null;

			try {
				session = openSession();

				ivldBestPrice = (IvldBestPrice)session.get(IvldBestPriceImpl.class,
						primaryKey);

				if (ivldBestPrice != null) {
					cacheResult(ivldBestPrice);
				}
				else {
					entityCache.putResult(IvldBestPriceModelImpl.ENTITY_CACHE_ENABLED,
						IvldBestPriceImpl.class, primaryKey, nullModel);
				}
			}
			catch (Exception e) {
				entityCache.removeResult(IvldBestPriceModelImpl.ENTITY_CACHE_ENABLED,
					IvldBestPriceImpl.class, primaryKey);

				throw processException(e);
			}
			finally {
				closeSession(session);
			}
		}

		return ivldBestPrice;
	}

	/**
	 * Returns the ivld best price with the primary key or returns <code>null</code> if it could not be found.
	 *
	 * @param ivldBestPriceSid the primary key of the ivld best price
	 * @return the ivld best price, or <code>null</code> if a ivld best price with the primary key could not be found
	 */
	@Override
	public IvldBestPrice fetchByPrimaryKey(int ivldBestPriceSid) {
		return fetchByPrimaryKey((Serializable)ivldBestPriceSid);
	}

	@Override
	public Map<Serializable, IvldBestPrice> fetchByPrimaryKeys(
		Set<Serializable> primaryKeys) {
		if (primaryKeys.isEmpty()) {
			return Collections.emptyMap();
		}

		Map<Serializable, IvldBestPrice> map = new HashMap<Serializable, IvldBestPrice>();

		if (primaryKeys.size() == 1) {
			Iterator<Serializable> iterator = primaryKeys.iterator();

			Serializable primaryKey = iterator.next();

			IvldBestPrice ivldBestPrice = fetchByPrimaryKey(primaryKey);

			if (ivldBestPrice != null) {
				map.put(primaryKey, ivldBestPrice);
			}

			return map;
		}

		Set<Serializable> uncachedPrimaryKeys = null;

		for (Serializable primaryKey : primaryKeys) {
			Serializable serializable = entityCache.getResult(IvldBestPriceModelImpl.ENTITY_CACHE_ENABLED,
					IvldBestPriceImpl.class, primaryKey);

			if (serializable != nullModel) {
				if (serializable == null) {
					if (uncachedPrimaryKeys == null) {
						uncachedPrimaryKeys = new HashSet<Serializable>();
					}

					uncachedPrimaryKeys.add(primaryKey);
				}
				else {
					map.put(primaryKey, (IvldBestPrice)serializable);
				}
			}
		}

		if (uncachedPrimaryKeys == null) {
			return map;
		}

		StringBundler query = new StringBundler((uncachedPrimaryKeys.size() * 2) +
				1);

		query.append(_SQL_SELECT_IVLDBESTPRICE_WHERE_PKS_IN);

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

			for (IvldBestPrice ivldBestPrice : (List<IvldBestPrice>)q.list()) {
				map.put(ivldBestPrice.getPrimaryKeyObj(), ivldBestPrice);

				cacheResult(ivldBestPrice);

				uncachedPrimaryKeys.remove(ivldBestPrice.getPrimaryKeyObj());
			}

			for (Serializable primaryKey : uncachedPrimaryKeys) {
				entityCache.putResult(IvldBestPriceModelImpl.ENTITY_CACHE_ENABLED,
					IvldBestPriceImpl.class, primaryKey, nullModel);
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
	 * Returns all the ivld best prices.
	 *
	 * @return the ivld best prices
	 */
	@Override
	public List<IvldBestPrice> findAll() {
		return findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS, null);
	}

	/**
	 * Returns a range of all the ivld best prices.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldBestPriceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld best prices
	 * @param end the upper bound of the range of ivld best prices (not inclusive)
	 * @return the range of ivld best prices
	 */
	@Override
	public List<IvldBestPrice> findAll(int start, int end) {
		return findAll(start, end, null);
	}

	/**
	 * Returns an ordered range of all the ivld best prices.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldBestPriceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld best prices
	 * @param end the upper bound of the range of ivld best prices (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @return the ordered range of ivld best prices
	 */
	@Override
	public List<IvldBestPrice> findAll(int start, int end,
		OrderByComparator<IvldBestPrice> orderByComparator) {
		return findAll(start, end, orderByComparator, true);
	}

	/**
	 * Returns an ordered range of all the ivld best prices.
	 *
	 * <p>
	 * Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link IvldBestPriceModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	 * </p>
	 *
	 * @param start the lower bound of the range of ivld best prices
	 * @param end the upper bound of the range of ivld best prices (not inclusive)
	 * @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	 * @param retrieveFromCache whether to retrieve from the finder cache
	 * @return the ordered range of ivld best prices
	 */
	@Override
	public List<IvldBestPrice> findAll(int start, int end,
		OrderByComparator<IvldBestPrice> orderByComparator,
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

		List<IvldBestPrice> list = null;

		if (retrieveFromCache) {
			list = (List<IvldBestPrice>)finderCache.getResult(finderPath,
					finderArgs, this);
		}

		if (list == null) {
			StringBundler query = null;
			String sql = null;

			if (orderByComparator != null) {
				query = new StringBundler(2 +
						(orderByComparator.getOrderByFields().length * 2));

				query.append(_SQL_SELECT_IVLDBESTPRICE);

				appendOrderByComparator(query, _ORDER_BY_ENTITY_ALIAS,
					orderByComparator);

				sql = query.toString();
			}
			else {
				sql = _SQL_SELECT_IVLDBESTPRICE;

				if (pagination) {
					sql = sql.concat(IvldBestPriceModelImpl.ORDER_BY_JPQL);
				}
			}

			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(sql);

				if (!pagination) {
					list = (List<IvldBestPrice>)QueryUtil.list(q, getDialect(),
							start, end, false);

					Collections.sort(list);

					list = Collections.unmodifiableList(list);
				}
				else {
					list = (List<IvldBestPrice>)QueryUtil.list(q, getDialect(),
							start, end);
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
	 * Removes all the ivld best prices from the database.
	 *
	 */
	@Override
	public void removeAll() {
		for (IvldBestPrice ivldBestPrice : findAll()) {
			remove(ivldBestPrice);
		}
	}

	/**
	 * Returns the number of ivld best prices.
	 *
	 * @return the number of ivld best prices
	 */
	@Override
	public int countAll() {
		Long count = (Long)finderCache.getResult(FINDER_PATH_COUNT_ALL,
				FINDER_ARGS_EMPTY, this);

		if (count == null) {
			Session session = null;

			try {
				session = openSession();

				Query q = session.createQuery(_SQL_COUNT_IVLDBESTPRICE);

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
		return IvldBestPriceModelImpl.TABLE_COLUMNS_MAP;
	}

	/**
	 * Initializes the ivld best price persistence.
	 */
	public void afterPropertiesSet() {
	}

	public void destroy() {
		entityCache.removeCache(IvldBestPriceImpl.class.getName());
		finderCache.removeCache(FINDER_CLASS_NAME_ENTITY);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITH_PAGINATION);
		finderCache.removeCache(FINDER_CLASS_NAME_LIST_WITHOUT_PAGINATION);
	}

	@ServiceReference(type = EntityCache.class)
	protected EntityCache entityCache;
	@ServiceReference(type = FinderCache.class)
	protected FinderCache finderCache;
	private static final String _SQL_SELECT_IVLDBESTPRICE = "SELECT ivldBestPrice FROM IvldBestPrice ivldBestPrice";
	private static final String _SQL_SELECT_IVLDBESTPRICE_WHERE_PKS_IN = "SELECT ivldBestPrice FROM IvldBestPrice ivldBestPrice WHERE IVLD_BEST_PRICE_SID IN (";
	private static final String _SQL_COUNT_IVLDBESTPRICE = "SELECT COUNT(ivldBestPrice) FROM IvldBestPrice ivldBestPrice";
	private static final String _ORDER_BY_ENTITY_ALIAS = "ivldBestPrice.";
	private static final String _NO_SUCH_ENTITY_WITH_PRIMARY_KEY = "No IvldBestPrice exists with the primary key ";
	private static final Log _log = LogFactoryUtil.getLog(IvldBestPricePersistenceImpl.class);
	private static final Set<String> _badColumnNames = SetUtil.fromArray(new String[] {
				"itemDesc", "bestPriceIntfid", "accountId", "sellingPrice",
				"period", "itemId", "modifiedDate", "createdBy", "createdDate",
				"source", "addChgDelIndicator", "initialDiscount", "errorCode",
				"modifiedBy", "intfInsertedDate", "itemNo", "reprocessedFlag",
				"contractEndDate", "ivldBestPriceSid", "contractId",
				"beginningWacPackage", "reasonForFailure", "contractStartDate",
				"batchId", "errorField", "initialBestPrice", "contractNo",
				"checkRecord"
			});
}