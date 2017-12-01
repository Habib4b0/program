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

package com.stpl.app.service.persistence.test;

import com.liferay.arquillian.extension.junit.bridge.junit.Arquillian;

import com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.exception.NoSuchCpiIndexMasterException;
import com.stpl.app.model.CpiIndexMaster;
import com.stpl.app.service.CpiIndexMasterLocalServiceUtil;
import com.stpl.app.service.persistence.CpiIndexMasterPersistence;
import com.stpl.app.service.persistence.CpiIndexMasterUtil;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import org.junit.runner.RunWith;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class CpiIndexMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = CpiIndexMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CpiIndexMaster> iterator = _cpiIndexMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CpiIndexMaster cpiIndexMaster = _persistence.create(pk);

		Assert.assertNotNull(cpiIndexMaster);

		Assert.assertEquals(cpiIndexMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CpiIndexMaster newCpiIndexMaster = addCpiIndexMaster();

		_persistence.remove(newCpiIndexMaster);

		CpiIndexMaster existingCpiIndexMaster = _persistence.fetchByPrimaryKey(newCpiIndexMaster.getPrimaryKey());

		Assert.assertNull(existingCpiIndexMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCpiIndexMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CpiIndexMaster newCpiIndexMaster = _persistence.create(pk);

		newCpiIndexMaster.setEffectiveDate(RandomTestUtil.nextDate());

		newCpiIndexMaster.setCreatedBy(RandomTestUtil.nextInt());

		newCpiIndexMaster.setModifiedBy(RandomTestUtil.nextInt());

		newCpiIndexMaster.setCreatedDate(RandomTestUtil.nextDate());

		newCpiIndexMaster.setBatchId(RandomTestUtil.randomString());

		newCpiIndexMaster.setModifiedDate(RandomTestUtil.nextDate());

		newCpiIndexMaster.setStatus(RandomTestUtil.randomString());

		newCpiIndexMaster.setIndexType(RandomTestUtil.randomString());

		newCpiIndexMaster.setIndexId(RandomTestUtil.randomString());

		newCpiIndexMaster.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newCpiIndexMaster.setIndexValue(RandomTestUtil.randomString());

		newCpiIndexMaster.setSource(RandomTestUtil.randomString());

		newCpiIndexMaster.setInboundStatus(RandomTestUtil.randomString());

		_cpiIndexMasters.add(_persistence.update(newCpiIndexMaster));

		CpiIndexMaster existingCpiIndexMaster = _persistence.findByPrimaryKey(newCpiIndexMaster.getPrimaryKey());

		Assert.assertEquals(Time.getShortTimestamp(
				existingCpiIndexMaster.getEffectiveDate()),
			Time.getShortTimestamp(newCpiIndexMaster.getEffectiveDate()));
		Assert.assertEquals(existingCpiIndexMaster.getCreatedBy(),
			newCpiIndexMaster.getCreatedBy());
		Assert.assertEquals(existingCpiIndexMaster.getModifiedBy(),
			newCpiIndexMaster.getModifiedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCpiIndexMaster.getCreatedDate()),
			Time.getShortTimestamp(newCpiIndexMaster.getCreatedDate()));
		Assert.assertEquals(existingCpiIndexMaster.getCpiIndexMasterSid(),
			newCpiIndexMaster.getCpiIndexMasterSid());
		Assert.assertEquals(existingCpiIndexMaster.getBatchId(),
			newCpiIndexMaster.getBatchId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCpiIndexMaster.getModifiedDate()),
			Time.getShortTimestamp(newCpiIndexMaster.getModifiedDate()));
		Assert.assertEquals(existingCpiIndexMaster.getStatus(),
			newCpiIndexMaster.getStatus());
		Assert.assertEquals(existingCpiIndexMaster.getIndexType(),
			newCpiIndexMaster.getIndexType());
		Assert.assertEquals(existingCpiIndexMaster.getIndexId(),
			newCpiIndexMaster.getIndexId());
		Assert.assertEquals(existingCpiIndexMaster.getRecordLockStatus(),
			newCpiIndexMaster.getRecordLockStatus());
		Assert.assertEquals(existingCpiIndexMaster.getIndexValue(),
			newCpiIndexMaster.getIndexValue());
		Assert.assertEquals(existingCpiIndexMaster.getSource(),
			newCpiIndexMaster.getSource());
		Assert.assertEquals(existingCpiIndexMaster.getInboundStatus(),
			newCpiIndexMaster.getInboundStatus());
	}

	@Test
	public void testCountByStatus() throws Exception {
		_persistence.countByStatus(StringPool.BLANK);

		_persistence.countByStatus(StringPool.NULL);

		_persistence.countByStatus((String)null);
	}

	@Test
	public void testCountByIndexId() throws Exception {
		_persistence.countByIndexId(StringPool.BLANK);

		_persistence.countByIndexId(StringPool.NULL);

		_persistence.countByIndexId((String)null);
	}

	@Test
	public void testCountByIndexValue() throws Exception {
		_persistence.countByIndexValue(StringPool.BLANK);

		_persistence.countByIndexValue(StringPool.NULL);

		_persistence.countByIndexValue((String)null);
	}

	@Test
	public void testCountByIndexType() throws Exception {
		_persistence.countByIndexType(StringPool.BLANK);

		_persistence.countByIndexType(StringPool.NULL);

		_persistence.countByIndexType((String)null);
	}

	@Test
	public void testCountByEffectiveDate() throws Exception {
		_persistence.countByEffectiveDate(RandomTestUtil.nextDate());

		_persistence.countByEffectiveDate(RandomTestUtil.nextDate());
	}

	@Test
	public void testCountByCpiIndexUnique() throws Exception {
		_persistence.countByCpiIndexUnique(StringPool.BLANK, StringPool.BLANK,
			StringPool.BLANK, RandomTestUtil.nextDate());

		_persistence.countByCpiIndexUnique(StringPool.NULL, StringPool.NULL,
			StringPool.NULL, RandomTestUtil.nextDate());

		_persistence.countByCpiIndexUnique((String)null, (String)null,
			(String)null, RandomTestUtil.nextDate());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CpiIndexMaster newCpiIndexMaster = addCpiIndexMaster();

		CpiIndexMaster existingCpiIndexMaster = _persistence.findByPrimaryKey(newCpiIndexMaster.getPrimaryKey());

		Assert.assertEquals(existingCpiIndexMaster, newCpiIndexMaster);
	}

	@Test(expected = NoSuchCpiIndexMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<CpiIndexMaster> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("CPI_INDEX_MASTER",
			"effectiveDate", true, "createdBy", true, "modifiedBy", true,
			"createdDate", true, "cpiIndexMasterSid", true, "batchId", true,
			"modifiedDate", true, "status", true, "indexType", true, "indexId",
			true, "recordLockStatus", true, "indexValue", true, "source", true,
			"inboundStatus", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CpiIndexMaster newCpiIndexMaster = addCpiIndexMaster();

		CpiIndexMaster existingCpiIndexMaster = _persistence.fetchByPrimaryKey(newCpiIndexMaster.getPrimaryKey());

		Assert.assertEquals(existingCpiIndexMaster, newCpiIndexMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CpiIndexMaster missingCpiIndexMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCpiIndexMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		CpiIndexMaster newCpiIndexMaster1 = addCpiIndexMaster();
		CpiIndexMaster newCpiIndexMaster2 = addCpiIndexMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCpiIndexMaster1.getPrimaryKey());
		primaryKeys.add(newCpiIndexMaster2.getPrimaryKey());

		Map<Serializable, CpiIndexMaster> cpiIndexMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, cpiIndexMasters.size());
		Assert.assertEquals(newCpiIndexMaster1,
			cpiIndexMasters.get(newCpiIndexMaster1.getPrimaryKey()));
		Assert.assertEquals(newCpiIndexMaster2,
			cpiIndexMasters.get(newCpiIndexMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CpiIndexMaster> cpiIndexMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(cpiIndexMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		CpiIndexMaster newCpiIndexMaster = addCpiIndexMaster();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCpiIndexMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CpiIndexMaster> cpiIndexMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, cpiIndexMasters.size());
		Assert.assertEquals(newCpiIndexMaster,
			cpiIndexMasters.get(newCpiIndexMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CpiIndexMaster> cpiIndexMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(cpiIndexMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		CpiIndexMaster newCpiIndexMaster = addCpiIndexMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCpiIndexMaster.getPrimaryKey());

		Map<Serializable, CpiIndexMaster> cpiIndexMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, cpiIndexMasters.size());
		Assert.assertEquals(newCpiIndexMaster,
			cpiIndexMasters.get(newCpiIndexMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = CpiIndexMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<CpiIndexMaster>() {
				@Override
				public void performAction(CpiIndexMaster cpiIndexMaster) {
					Assert.assertNotNull(cpiIndexMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		CpiIndexMaster newCpiIndexMaster = addCpiIndexMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CpiIndexMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("cpiIndexMasterSid",
				newCpiIndexMaster.getCpiIndexMasterSid()));

		List<CpiIndexMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CpiIndexMaster existingCpiIndexMaster = result.get(0);

		Assert.assertEquals(existingCpiIndexMaster, newCpiIndexMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CpiIndexMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("cpiIndexMasterSid",
				RandomTestUtil.nextInt()));

		List<CpiIndexMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		CpiIndexMaster newCpiIndexMaster = addCpiIndexMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CpiIndexMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"cpiIndexMasterSid"));

		Object newCpiIndexMasterSid = newCpiIndexMaster.getCpiIndexMasterSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("cpiIndexMasterSid",
				new Object[] { newCpiIndexMasterSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCpiIndexMasterSid = result.get(0);

		Assert.assertEquals(existingCpiIndexMasterSid, newCpiIndexMasterSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CpiIndexMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"cpiIndexMasterSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("cpiIndexMasterSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected CpiIndexMaster addCpiIndexMaster() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CpiIndexMaster cpiIndexMaster = _persistence.create(pk);

		cpiIndexMaster.setEffectiveDate(RandomTestUtil.nextDate());

		cpiIndexMaster.setCreatedBy(RandomTestUtil.nextInt());

		cpiIndexMaster.setModifiedBy(RandomTestUtil.nextInt());

		cpiIndexMaster.setCreatedDate(RandomTestUtil.nextDate());

		cpiIndexMaster.setBatchId(RandomTestUtil.randomString());

		cpiIndexMaster.setModifiedDate(RandomTestUtil.nextDate());

		cpiIndexMaster.setStatus(RandomTestUtil.randomString());

		cpiIndexMaster.setIndexType(RandomTestUtil.randomString());

		cpiIndexMaster.setIndexId(RandomTestUtil.randomString());

		cpiIndexMaster.setRecordLockStatus(RandomTestUtil.randomBoolean());

		cpiIndexMaster.setIndexValue(RandomTestUtil.randomString());

		cpiIndexMaster.setSource(RandomTestUtil.randomString());

		cpiIndexMaster.setInboundStatus(RandomTestUtil.randomString());

		_cpiIndexMasters.add(_persistence.update(cpiIndexMaster));

		return cpiIndexMaster;
	}

	private List<CpiIndexMaster> _cpiIndexMasters = new ArrayList<CpiIndexMaster>();
	private CpiIndexMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}