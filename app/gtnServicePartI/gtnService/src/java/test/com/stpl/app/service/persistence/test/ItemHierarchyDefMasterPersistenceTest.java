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

import com.stpl.app.exception.NoSuchItemHierarchyDefMasterException;
import com.stpl.app.model.ItemHierarchyDefMaster;
import com.stpl.app.service.ItemHierarchyDefMasterLocalServiceUtil;
import com.stpl.app.service.persistence.ItemHierarchyDefMasterPersistence;
import com.stpl.app.service.persistence.ItemHierarchyDefMasterUtil;

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
public class ItemHierarchyDefMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = ItemHierarchyDefMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<ItemHierarchyDefMaster> iterator = _itemHierarchyDefMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ItemHierarchyDefMaster itemHierarchyDefMaster = _persistence.create(pk);

		Assert.assertNotNull(itemHierarchyDefMaster);

		Assert.assertEquals(itemHierarchyDefMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		ItemHierarchyDefMaster newItemHierarchyDefMaster = addItemHierarchyDefMaster();

		_persistence.remove(newItemHierarchyDefMaster);

		ItemHierarchyDefMaster existingItemHierarchyDefMaster = _persistence.fetchByPrimaryKey(newItemHierarchyDefMaster.getPrimaryKey());

		Assert.assertNull(existingItemHierarchyDefMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addItemHierarchyDefMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ItemHierarchyDefMaster newItemHierarchyDefMaster = _persistence.create(pk);

		newItemHierarchyDefMaster.setCreatedBy(RandomTestUtil.nextInt());

		newItemHierarchyDefMaster.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newItemHierarchyDefMaster.setModifiedBy(RandomTestUtil.nextInt());

		newItemHierarchyDefMaster.setCreatedDate(RandomTestUtil.nextDate());

		newItemHierarchyDefMaster.setAlias(RandomTestUtil.randomString());

		newItemHierarchyDefMaster.setSource(RandomTestUtil.randomString());

		newItemHierarchyDefMaster.setBatchId(RandomTestUtil.randomString());

		newItemHierarchyDefMaster.setBpiLvl(RandomTestUtil.randomString());

		newItemHierarchyDefMaster.setModifiedDate(RandomTestUtil.nextDate());

		newItemHierarchyDefMaster.setMember(RandomTestUtil.randomString());

		newItemHierarchyDefMaster.setInboundStatus(RandomTestUtil.randomString());

		_itemHierarchyDefMasters.add(_persistence.update(
				newItemHierarchyDefMaster));

		ItemHierarchyDefMaster existingItemHierarchyDefMaster = _persistence.findByPrimaryKey(newItemHierarchyDefMaster.getPrimaryKey());

		Assert.assertEquals(existingItemHierarchyDefMaster.getItemHierarchyDefMasterSid(),
			newItemHierarchyDefMaster.getItemHierarchyDefMasterSid());
		Assert.assertEquals(existingItemHierarchyDefMaster.getCreatedBy(),
			newItemHierarchyDefMaster.getCreatedBy());
		Assert.assertEquals(existingItemHierarchyDefMaster.getRecordLockStatus(),
			newItemHierarchyDefMaster.getRecordLockStatus());
		Assert.assertEquals(existingItemHierarchyDefMaster.getModifiedBy(),
			newItemHierarchyDefMaster.getModifiedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingItemHierarchyDefMaster.getCreatedDate()),
			Time.getShortTimestamp(newItemHierarchyDefMaster.getCreatedDate()));
		Assert.assertEquals(existingItemHierarchyDefMaster.getAlias(),
			newItemHierarchyDefMaster.getAlias());
		Assert.assertEquals(existingItemHierarchyDefMaster.getSource(),
			newItemHierarchyDefMaster.getSource());
		Assert.assertEquals(existingItemHierarchyDefMaster.getBatchId(),
			newItemHierarchyDefMaster.getBatchId());
		Assert.assertEquals(existingItemHierarchyDefMaster.getBpiLvl(),
			newItemHierarchyDefMaster.getBpiLvl());
		Assert.assertEquals(Time.getShortTimestamp(
				existingItemHierarchyDefMaster.getModifiedDate()),
			Time.getShortTimestamp(newItemHierarchyDefMaster.getModifiedDate()));
		Assert.assertEquals(existingItemHierarchyDefMaster.getMember(),
			newItemHierarchyDefMaster.getMember());
		Assert.assertEquals(existingItemHierarchyDefMaster.getInboundStatus(),
			newItemHierarchyDefMaster.getInboundStatus());
	}

	@Test
	public void testCountByMember() throws Exception {
		_persistence.countByMember(StringPool.BLANK);

		_persistence.countByMember(StringPool.NULL);

		_persistence.countByMember((String)null);
	}

	@Test
	public void testCountByAlias() throws Exception {
		_persistence.countByAlias(StringPool.BLANK);

		_persistence.countByAlias(StringPool.NULL);

		_persistence.countByAlias((String)null);
	}

	@Test
	public void testCountByBpiLvl() throws Exception {
		_persistence.countByBpiLvl(StringPool.BLANK);

		_persistence.countByBpiLvl(StringPool.NULL);

		_persistence.countByBpiLvl((String)null);
	}

	@Test
	public void testCountByItemHierarchyDefUnique() throws Exception {
		_persistence.countByItemHierarchyDefUnique(StringPool.BLANK,
			StringPool.BLANK);

		_persistence.countByItemHierarchyDefUnique(StringPool.NULL,
			StringPool.NULL);

		_persistence.countByItemHierarchyDefUnique((String)null, (String)null);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		ItemHierarchyDefMaster newItemHierarchyDefMaster = addItemHierarchyDefMaster();

		ItemHierarchyDefMaster existingItemHierarchyDefMaster = _persistence.findByPrimaryKey(newItemHierarchyDefMaster.getPrimaryKey());

		Assert.assertEquals(existingItemHierarchyDefMaster,
			newItemHierarchyDefMaster);
	}

	@Test(expected = NoSuchItemHierarchyDefMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<ItemHierarchyDefMaster> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("ITEM_HIERARCHY_DEF_MASTER",
			"itemHierarchyDefMasterSid", true, "createdBy", true,
			"recordLockStatus", true, "modifiedBy", true, "createdDate", true,
			"alias", true, "source", true, "batchId", true, "bpiLvl", true,
			"modifiedDate", true, "member", true, "inboundStatus", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		ItemHierarchyDefMaster newItemHierarchyDefMaster = addItemHierarchyDefMaster();

		ItemHierarchyDefMaster existingItemHierarchyDefMaster = _persistence.fetchByPrimaryKey(newItemHierarchyDefMaster.getPrimaryKey());

		Assert.assertEquals(existingItemHierarchyDefMaster,
			newItemHierarchyDefMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ItemHierarchyDefMaster missingItemHierarchyDefMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingItemHierarchyDefMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		ItemHierarchyDefMaster newItemHierarchyDefMaster1 = addItemHierarchyDefMaster();
		ItemHierarchyDefMaster newItemHierarchyDefMaster2 = addItemHierarchyDefMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newItemHierarchyDefMaster1.getPrimaryKey());
		primaryKeys.add(newItemHierarchyDefMaster2.getPrimaryKey());

		Map<Serializable, ItemHierarchyDefMaster> itemHierarchyDefMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, itemHierarchyDefMasters.size());
		Assert.assertEquals(newItemHierarchyDefMaster1,
			itemHierarchyDefMasters.get(
				newItemHierarchyDefMaster1.getPrimaryKey()));
		Assert.assertEquals(newItemHierarchyDefMaster2,
			itemHierarchyDefMasters.get(
				newItemHierarchyDefMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, ItemHierarchyDefMaster> itemHierarchyDefMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(itemHierarchyDefMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		ItemHierarchyDefMaster newItemHierarchyDefMaster = addItemHierarchyDefMaster();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newItemHierarchyDefMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, ItemHierarchyDefMaster> itemHierarchyDefMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, itemHierarchyDefMasters.size());
		Assert.assertEquals(newItemHierarchyDefMaster,
			itemHierarchyDefMasters.get(
				newItemHierarchyDefMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, ItemHierarchyDefMaster> itemHierarchyDefMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(itemHierarchyDefMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		ItemHierarchyDefMaster newItemHierarchyDefMaster = addItemHierarchyDefMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newItemHierarchyDefMaster.getPrimaryKey());

		Map<Serializable, ItemHierarchyDefMaster> itemHierarchyDefMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, itemHierarchyDefMasters.size());
		Assert.assertEquals(newItemHierarchyDefMaster,
			itemHierarchyDefMasters.get(
				newItemHierarchyDefMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = ItemHierarchyDefMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ItemHierarchyDefMaster>() {
				@Override
				public void performAction(
					ItemHierarchyDefMaster itemHierarchyDefMaster) {
					Assert.assertNotNull(itemHierarchyDefMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		ItemHierarchyDefMaster newItemHierarchyDefMaster = addItemHierarchyDefMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ItemHierarchyDefMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"itemHierarchyDefMasterSid",
				newItemHierarchyDefMaster.getItemHierarchyDefMasterSid()));

		List<ItemHierarchyDefMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		ItemHierarchyDefMaster existingItemHierarchyDefMaster = result.get(0);

		Assert.assertEquals(existingItemHierarchyDefMaster,
			newItemHierarchyDefMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ItemHierarchyDefMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq(
				"itemHierarchyDefMasterSid", RandomTestUtil.nextInt()));

		List<ItemHierarchyDefMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		ItemHierarchyDefMaster newItemHierarchyDefMaster = addItemHierarchyDefMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ItemHierarchyDefMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"itemHierarchyDefMasterSid"));

		Object newItemHierarchyDefMasterSid = newItemHierarchyDefMaster.getItemHierarchyDefMasterSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"itemHierarchyDefMasterSid",
				new Object[] { newItemHierarchyDefMasterSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingItemHierarchyDefMasterSid = result.get(0);

		Assert.assertEquals(existingItemHierarchyDefMasterSid,
			newItemHierarchyDefMasterSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ItemHierarchyDefMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"itemHierarchyDefMasterSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in(
				"itemHierarchyDefMasterSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected ItemHierarchyDefMaster addItemHierarchyDefMaster()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		ItemHierarchyDefMaster itemHierarchyDefMaster = _persistence.create(pk);

		itemHierarchyDefMaster.setCreatedBy(RandomTestUtil.nextInt());

		itemHierarchyDefMaster.setRecordLockStatus(RandomTestUtil.randomBoolean());

		itemHierarchyDefMaster.setModifiedBy(RandomTestUtil.nextInt());

		itemHierarchyDefMaster.setCreatedDate(RandomTestUtil.nextDate());

		itemHierarchyDefMaster.setAlias(RandomTestUtil.randomString());

		itemHierarchyDefMaster.setSource(RandomTestUtil.randomString());

		itemHierarchyDefMaster.setBatchId(RandomTestUtil.randomString());

		itemHierarchyDefMaster.setBpiLvl(RandomTestUtil.randomString());

		itemHierarchyDefMaster.setModifiedDate(RandomTestUtil.nextDate());

		itemHierarchyDefMaster.setMember(RandomTestUtil.randomString());

		itemHierarchyDefMaster.setInboundStatus(RandomTestUtil.randomString());

		_itemHierarchyDefMasters.add(_persistence.update(itemHierarchyDefMaster));

		return itemHierarchyDefMaster;
	}

	private List<ItemHierarchyDefMaster> _itemHierarchyDefMasters = new ArrayList<ItemHierarchyDefMaster>();
	private ItemHierarchyDefMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}