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

import com.stpl.app.exception.NoSuchItemHierarchyMasterException;
import com.stpl.app.model.ItemHierarchyMaster;
import com.stpl.app.service.ItemHierarchyMasterLocalServiceUtil;
import com.stpl.app.service.persistence.ItemHierarchyMasterPersistence;
import com.stpl.app.service.persistence.ItemHierarchyMasterUtil;

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
public class ItemHierarchyMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = ItemHierarchyMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<ItemHierarchyMaster> iterator = _itemHierarchyMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ItemHierarchyMaster itemHierarchyMaster = _persistence.create(pk);

		Assert.assertNotNull(itemHierarchyMaster);

		Assert.assertEquals(itemHierarchyMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		ItemHierarchyMaster newItemHierarchyMaster = addItemHierarchyMaster();

		_persistence.remove(newItemHierarchyMaster);

		ItemHierarchyMaster existingItemHierarchyMaster = _persistence.fetchByPrimaryKey(newItemHierarchyMaster.getPrimaryKey());

		Assert.assertNull(existingItemHierarchyMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addItemHierarchyMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ItemHierarchyMaster newItemHierarchyMaster = _persistence.create(pk);

		newItemHierarchyMaster.setLevel1Alias(RandomTestUtil.randomString());

		newItemHierarchyMaster.setLevel11Alias(RandomTestUtil.randomString());

		newItemHierarchyMaster.setCreatedDate(RandomTestUtil.nextDate());

		newItemHierarchyMaster.setLevel8Alias(RandomTestUtil.randomString());

		newItemHierarchyMaster.setLevel14Alias(RandomTestUtil.randomString());

		newItemHierarchyMaster.setLevel5Alias(RandomTestUtil.randomString());

		newItemHierarchyMaster.setCreatedBy(RandomTestUtil.nextInt());

		newItemHierarchyMaster.setLevel10Alias(RandomTestUtil.randomString());

		newItemHierarchyMaster.setLevel17Alias(RandomTestUtil.randomString());

		newItemHierarchyMaster.setLevel9Alias(RandomTestUtil.randomString());

		newItemHierarchyMaster.setLevel0Alias(RandomTestUtil.randomString());

		newItemHierarchyMaster.setModifiedDate(RandomTestUtil.nextDate());

		newItemHierarchyMaster.setLevel13Alias(RandomTestUtil.randomString());

		newItemHierarchyMaster.setSource(RandomTestUtil.randomString());

		newItemHierarchyMaster.setLevel6Alias(RandomTestUtil.randomString());

		newItemHierarchyMaster.setGtnBrand(RandomTestUtil.randomString());

		newItemHierarchyMaster.setModifiedBy(RandomTestUtil.nextInt());

		newItemHierarchyMaster.setLevel3Alias(RandomTestUtil.randomString());

		newItemHierarchyMaster.setLevel16Alias(RandomTestUtil.randomString());

		newItemHierarchyMaster.setBatchId(RandomTestUtil.randomString());

		newItemHierarchyMaster.setLevel19Alias(RandomTestUtil.randomString());

		newItemHierarchyMaster.setLevel20(RandomTestUtil.randomString());

		newItemHierarchyMaster.setLevel2Alias(RandomTestUtil.randomString());

		newItemHierarchyMaster.setLevel20Alias(RandomTestUtil.randomString());

		newItemHierarchyMaster.setGtnTherapeuticClass(RandomTestUtil.randomString());

		newItemHierarchyMaster.setLevel7Alias(RandomTestUtil.randomString());

		newItemHierarchyMaster.setLevel0(RandomTestUtil.randomString());

		newItemHierarchyMaster.setLevel1(RandomTestUtil.randomString());

		newItemHierarchyMaster.setLevel2(RandomTestUtil.randomString());

		newItemHierarchyMaster.setLevel3(RandomTestUtil.randomString());

		newItemHierarchyMaster.setLevel12Alias(RandomTestUtil.randomString());

		newItemHierarchyMaster.setLevel8(RandomTestUtil.randomString());

		newItemHierarchyMaster.setLevel11(RandomTestUtil.randomString());

		newItemHierarchyMaster.setLevel4Alias(RandomTestUtil.randomString());

		newItemHierarchyMaster.setLevel9(RandomTestUtil.randomString());

		newItemHierarchyMaster.setLevel12(RandomTestUtil.randomString());

		newItemHierarchyMaster.setLevel13(RandomTestUtil.randomString());

		newItemHierarchyMaster.setLevel14(RandomTestUtil.randomString());

		newItemHierarchyMaster.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newItemHierarchyMaster.setLevel0Tag(RandomTestUtil.randomString());

		newItemHierarchyMaster.setLevel4(RandomTestUtil.randomString());

		newItemHierarchyMaster.setLevel5(RandomTestUtil.randomString());

		newItemHierarchyMaster.setLevel6(RandomTestUtil.randomString());

		newItemHierarchyMaster.setLevel15Alias(RandomTestUtil.randomString());

		newItemHierarchyMaster.setLevel7(RandomTestUtil.randomString());

		newItemHierarchyMaster.setLevel10(RandomTestUtil.randomString());

		newItemHierarchyMaster.setLevel19(RandomTestUtil.randomString());

		newItemHierarchyMaster.setLevel15(RandomTestUtil.randomString());

		newItemHierarchyMaster.setLevel16(RandomTestUtil.randomString());

		newItemHierarchyMaster.setGtnCompany(RandomTestUtil.randomString());

		newItemHierarchyMaster.setLevel17(RandomTestUtil.randomString());

		newItemHierarchyMaster.setLevel18Alias(RandomTestUtil.randomString());

		newItemHierarchyMaster.setLevel18(RandomTestUtil.randomString());

		newItemHierarchyMaster.setInboundStatus(RandomTestUtil.randomString());

		_itemHierarchyMasters.add(_persistence.update(newItemHierarchyMaster));

		ItemHierarchyMaster existingItemHierarchyMaster = _persistence.findByPrimaryKey(newItemHierarchyMaster.getPrimaryKey());

		Assert.assertEquals(existingItemHierarchyMaster.getLevel1Alias(),
			newItemHierarchyMaster.getLevel1Alias());
		Assert.assertEquals(existingItemHierarchyMaster.getLevel11Alias(),
			newItemHierarchyMaster.getLevel11Alias());
		Assert.assertEquals(Time.getShortTimestamp(
				existingItemHierarchyMaster.getCreatedDate()),
			Time.getShortTimestamp(newItemHierarchyMaster.getCreatedDate()));
		Assert.assertEquals(existingItemHierarchyMaster.getLevel8Alias(),
			newItemHierarchyMaster.getLevel8Alias());
		Assert.assertEquals(existingItemHierarchyMaster.getLevel14Alias(),
			newItemHierarchyMaster.getLevel14Alias());
		Assert.assertEquals(existingItemHierarchyMaster.getLevel5Alias(),
			newItemHierarchyMaster.getLevel5Alias());
		Assert.assertEquals(existingItemHierarchyMaster.getCreatedBy(),
			newItemHierarchyMaster.getCreatedBy());
		Assert.assertEquals(existingItemHierarchyMaster.getLevel10Alias(),
			newItemHierarchyMaster.getLevel10Alias());
		Assert.assertEquals(existingItemHierarchyMaster.getItemHierarchyMasterSid(),
			newItemHierarchyMaster.getItemHierarchyMasterSid());
		Assert.assertEquals(existingItemHierarchyMaster.getLevel17Alias(),
			newItemHierarchyMaster.getLevel17Alias());
		Assert.assertEquals(existingItemHierarchyMaster.getLevel9Alias(),
			newItemHierarchyMaster.getLevel9Alias());
		Assert.assertEquals(existingItemHierarchyMaster.getLevel0Alias(),
			newItemHierarchyMaster.getLevel0Alias());
		Assert.assertEquals(Time.getShortTimestamp(
				existingItemHierarchyMaster.getModifiedDate()),
			Time.getShortTimestamp(newItemHierarchyMaster.getModifiedDate()));
		Assert.assertEquals(existingItemHierarchyMaster.getLevel13Alias(),
			newItemHierarchyMaster.getLevel13Alias());
		Assert.assertEquals(existingItemHierarchyMaster.getSource(),
			newItemHierarchyMaster.getSource());
		Assert.assertEquals(existingItemHierarchyMaster.getLevel6Alias(),
			newItemHierarchyMaster.getLevel6Alias());
		Assert.assertEquals(existingItemHierarchyMaster.getGtnBrand(),
			newItemHierarchyMaster.getGtnBrand());
		Assert.assertEquals(existingItemHierarchyMaster.getModifiedBy(),
			newItemHierarchyMaster.getModifiedBy());
		Assert.assertEquals(existingItemHierarchyMaster.getLevel3Alias(),
			newItemHierarchyMaster.getLevel3Alias());
		Assert.assertEquals(existingItemHierarchyMaster.getLevel16Alias(),
			newItemHierarchyMaster.getLevel16Alias());
		Assert.assertEquals(existingItemHierarchyMaster.getBatchId(),
			newItemHierarchyMaster.getBatchId());
		Assert.assertEquals(existingItemHierarchyMaster.getLevel19Alias(),
			newItemHierarchyMaster.getLevel19Alias());
		Assert.assertEquals(existingItemHierarchyMaster.getLevel20(),
			newItemHierarchyMaster.getLevel20());
		Assert.assertEquals(existingItemHierarchyMaster.getLevel2Alias(),
			newItemHierarchyMaster.getLevel2Alias());
		Assert.assertEquals(existingItemHierarchyMaster.getLevel20Alias(),
			newItemHierarchyMaster.getLevel20Alias());
		Assert.assertEquals(existingItemHierarchyMaster.getGtnTherapeuticClass(),
			newItemHierarchyMaster.getGtnTherapeuticClass());
		Assert.assertEquals(existingItemHierarchyMaster.getLevel7Alias(),
			newItemHierarchyMaster.getLevel7Alias());
		Assert.assertEquals(existingItemHierarchyMaster.getLevel0(),
			newItemHierarchyMaster.getLevel0());
		Assert.assertEquals(existingItemHierarchyMaster.getLevel1(),
			newItemHierarchyMaster.getLevel1());
		Assert.assertEquals(existingItemHierarchyMaster.getLevel2(),
			newItemHierarchyMaster.getLevel2());
		Assert.assertEquals(existingItemHierarchyMaster.getLevel3(),
			newItemHierarchyMaster.getLevel3());
		Assert.assertEquals(existingItemHierarchyMaster.getLevel12Alias(),
			newItemHierarchyMaster.getLevel12Alias());
		Assert.assertEquals(existingItemHierarchyMaster.getLevel8(),
			newItemHierarchyMaster.getLevel8());
		Assert.assertEquals(existingItemHierarchyMaster.getLevel11(),
			newItemHierarchyMaster.getLevel11());
		Assert.assertEquals(existingItemHierarchyMaster.getLevel4Alias(),
			newItemHierarchyMaster.getLevel4Alias());
		Assert.assertEquals(existingItemHierarchyMaster.getLevel9(),
			newItemHierarchyMaster.getLevel9());
		Assert.assertEquals(existingItemHierarchyMaster.getLevel12(),
			newItemHierarchyMaster.getLevel12());
		Assert.assertEquals(existingItemHierarchyMaster.getLevel13(),
			newItemHierarchyMaster.getLevel13());
		Assert.assertEquals(existingItemHierarchyMaster.getLevel14(),
			newItemHierarchyMaster.getLevel14());
		Assert.assertEquals(existingItemHierarchyMaster.getRecordLockStatus(),
			newItemHierarchyMaster.getRecordLockStatus());
		Assert.assertEquals(existingItemHierarchyMaster.getLevel0Tag(),
			newItemHierarchyMaster.getLevel0Tag());
		Assert.assertEquals(existingItemHierarchyMaster.getLevel4(),
			newItemHierarchyMaster.getLevel4());
		Assert.assertEquals(existingItemHierarchyMaster.getLevel5(),
			newItemHierarchyMaster.getLevel5());
		Assert.assertEquals(existingItemHierarchyMaster.getLevel6(),
			newItemHierarchyMaster.getLevel6());
		Assert.assertEquals(existingItemHierarchyMaster.getLevel15Alias(),
			newItemHierarchyMaster.getLevel15Alias());
		Assert.assertEquals(existingItemHierarchyMaster.getLevel7(),
			newItemHierarchyMaster.getLevel7());
		Assert.assertEquals(existingItemHierarchyMaster.getLevel10(),
			newItemHierarchyMaster.getLevel10());
		Assert.assertEquals(existingItemHierarchyMaster.getLevel19(),
			newItemHierarchyMaster.getLevel19());
		Assert.assertEquals(existingItemHierarchyMaster.getLevel15(),
			newItemHierarchyMaster.getLevel15());
		Assert.assertEquals(existingItemHierarchyMaster.getLevel16(),
			newItemHierarchyMaster.getLevel16());
		Assert.assertEquals(existingItemHierarchyMaster.getGtnCompany(),
			newItemHierarchyMaster.getGtnCompany());
		Assert.assertEquals(existingItemHierarchyMaster.getLevel17(),
			newItemHierarchyMaster.getLevel17());
		Assert.assertEquals(existingItemHierarchyMaster.getLevel18Alias(),
			newItemHierarchyMaster.getLevel18Alias());
		Assert.assertEquals(existingItemHierarchyMaster.getLevel18(),
			newItemHierarchyMaster.getLevel18());
		Assert.assertEquals(existingItemHierarchyMaster.getInboundStatus(),
			newItemHierarchyMaster.getInboundStatus());
	}

	@Test
	public void testCountByLevel0() throws Exception {
		_persistence.countByLevel0(StringPool.BLANK);

		_persistence.countByLevel0(StringPool.NULL);

		_persistence.countByLevel0((String)null);
	}

	@Test
	public void testCountByLevel0Alias() throws Exception {
		_persistence.countByLevel0Alias(StringPool.BLANK);

		_persistence.countByLevel0Alias(StringPool.NULL);

		_persistence.countByLevel0Alias((String)null);
	}

	@Test
	public void testCountByLevel0Tag() throws Exception {
		_persistence.countByLevel0Tag(StringPool.BLANK);

		_persistence.countByLevel0Tag(StringPool.NULL);

		_persistence.countByLevel0Tag((String)null);
	}

	@Test
	public void testCountByItemHierarchyUnique() throws Exception {
		_persistence.countByItemHierarchyUnique(StringPool.BLANK);

		_persistence.countByItemHierarchyUnique(StringPool.NULL);

		_persistence.countByItemHierarchyUnique((String)null);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		ItemHierarchyMaster newItemHierarchyMaster = addItemHierarchyMaster();

		ItemHierarchyMaster existingItemHierarchyMaster = _persistence.findByPrimaryKey(newItemHierarchyMaster.getPrimaryKey());

		Assert.assertEquals(existingItemHierarchyMaster, newItemHierarchyMaster);
	}

	@Test(expected = NoSuchItemHierarchyMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<ItemHierarchyMaster> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("ITEM_HIERARCHY_MASTER",
			"level1Alias", true, "level11Alias", true, "createdDate", true,
			"level8Alias", true, "level14Alias", true, "level5Alias", true,
			"createdBy", true, "level10Alias", true, "itemHierarchyMasterSid",
			true, "level17Alias", true, "level9Alias", true, "level0Alias",
			true, "modifiedDate", true, "level13Alias", true, "source", true,
			"level6Alias", true, "gtnBrand", true, "modifiedBy", true,
			"level3Alias", true, "level16Alias", true, "batchId", true,
			"level19Alias", true, "level20", true, "level2Alias", true,
			"level20Alias", true, "gtnTherapeuticClass", true, "level7Alias",
			true, "level0", true, "level1", true, "level2", true, "level3",
			true, "level12Alias", true, "level8", true, "level11", true,
			"level4Alias", true, "level9", true, "level12", true, "level13",
			true, "level14", true, "recordLockStatus", true, "level0Tag", true,
			"level4", true, "level5", true, "level6", true, "level15Alias",
			true, "level7", true, "level10", true, "level19", true, "level15",
			true, "level16", true, "gtnCompany", true, "level17", true,
			"level18Alias", true, "level18", true, "inboundStatus", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		ItemHierarchyMaster newItemHierarchyMaster = addItemHierarchyMaster();

		ItemHierarchyMaster existingItemHierarchyMaster = _persistence.fetchByPrimaryKey(newItemHierarchyMaster.getPrimaryKey());

		Assert.assertEquals(existingItemHierarchyMaster, newItemHierarchyMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ItemHierarchyMaster missingItemHierarchyMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingItemHierarchyMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		ItemHierarchyMaster newItemHierarchyMaster1 = addItemHierarchyMaster();
		ItemHierarchyMaster newItemHierarchyMaster2 = addItemHierarchyMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newItemHierarchyMaster1.getPrimaryKey());
		primaryKeys.add(newItemHierarchyMaster2.getPrimaryKey());

		Map<Serializable, ItemHierarchyMaster> itemHierarchyMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, itemHierarchyMasters.size());
		Assert.assertEquals(newItemHierarchyMaster1,
			itemHierarchyMasters.get(newItemHierarchyMaster1.getPrimaryKey()));
		Assert.assertEquals(newItemHierarchyMaster2,
			itemHierarchyMasters.get(newItemHierarchyMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, ItemHierarchyMaster> itemHierarchyMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(itemHierarchyMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		ItemHierarchyMaster newItemHierarchyMaster = addItemHierarchyMaster();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newItemHierarchyMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, ItemHierarchyMaster> itemHierarchyMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, itemHierarchyMasters.size());
		Assert.assertEquals(newItemHierarchyMaster,
			itemHierarchyMasters.get(newItemHierarchyMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, ItemHierarchyMaster> itemHierarchyMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(itemHierarchyMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		ItemHierarchyMaster newItemHierarchyMaster = addItemHierarchyMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newItemHierarchyMaster.getPrimaryKey());

		Map<Serializable, ItemHierarchyMaster> itemHierarchyMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, itemHierarchyMasters.size());
		Assert.assertEquals(newItemHierarchyMaster,
			itemHierarchyMasters.get(newItemHierarchyMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = ItemHierarchyMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ItemHierarchyMaster>() {
				@Override
				public void performAction(
					ItemHierarchyMaster itemHierarchyMaster) {
					Assert.assertNotNull(itemHierarchyMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		ItemHierarchyMaster newItemHierarchyMaster = addItemHierarchyMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ItemHierarchyMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("itemHierarchyMasterSid",
				newItemHierarchyMaster.getItemHierarchyMasterSid()));

		List<ItemHierarchyMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		ItemHierarchyMaster existingItemHierarchyMaster = result.get(0);

		Assert.assertEquals(existingItemHierarchyMaster, newItemHierarchyMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ItemHierarchyMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("itemHierarchyMasterSid",
				RandomTestUtil.nextInt()));

		List<ItemHierarchyMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		ItemHierarchyMaster newItemHierarchyMaster = addItemHierarchyMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ItemHierarchyMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"itemHierarchyMasterSid"));

		Object newItemHierarchyMasterSid = newItemHierarchyMaster.getItemHierarchyMasterSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("itemHierarchyMasterSid",
				new Object[] { newItemHierarchyMasterSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingItemHierarchyMasterSid = result.get(0);

		Assert.assertEquals(existingItemHierarchyMasterSid,
			newItemHierarchyMasterSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ItemHierarchyMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"itemHierarchyMasterSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("itemHierarchyMasterSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected ItemHierarchyMaster addItemHierarchyMaster()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		ItemHierarchyMaster itemHierarchyMaster = _persistence.create(pk);

		itemHierarchyMaster.setLevel1Alias(RandomTestUtil.randomString());

		itemHierarchyMaster.setLevel11Alias(RandomTestUtil.randomString());

		itemHierarchyMaster.setCreatedDate(RandomTestUtil.nextDate());

		itemHierarchyMaster.setLevel8Alias(RandomTestUtil.randomString());

		itemHierarchyMaster.setLevel14Alias(RandomTestUtil.randomString());

		itemHierarchyMaster.setLevel5Alias(RandomTestUtil.randomString());

		itemHierarchyMaster.setCreatedBy(RandomTestUtil.nextInt());

		itemHierarchyMaster.setLevel10Alias(RandomTestUtil.randomString());

		itemHierarchyMaster.setLevel17Alias(RandomTestUtil.randomString());

		itemHierarchyMaster.setLevel9Alias(RandomTestUtil.randomString());

		itemHierarchyMaster.setLevel0Alias(RandomTestUtil.randomString());

		itemHierarchyMaster.setModifiedDate(RandomTestUtil.nextDate());

		itemHierarchyMaster.setLevel13Alias(RandomTestUtil.randomString());

		itemHierarchyMaster.setSource(RandomTestUtil.randomString());

		itemHierarchyMaster.setLevel6Alias(RandomTestUtil.randomString());

		itemHierarchyMaster.setGtnBrand(RandomTestUtil.randomString());

		itemHierarchyMaster.setModifiedBy(RandomTestUtil.nextInt());

		itemHierarchyMaster.setLevel3Alias(RandomTestUtil.randomString());

		itemHierarchyMaster.setLevel16Alias(RandomTestUtil.randomString());

		itemHierarchyMaster.setBatchId(RandomTestUtil.randomString());

		itemHierarchyMaster.setLevel19Alias(RandomTestUtil.randomString());

		itemHierarchyMaster.setLevel20(RandomTestUtil.randomString());

		itemHierarchyMaster.setLevel2Alias(RandomTestUtil.randomString());

		itemHierarchyMaster.setLevel20Alias(RandomTestUtil.randomString());

		itemHierarchyMaster.setGtnTherapeuticClass(RandomTestUtil.randomString());

		itemHierarchyMaster.setLevel7Alias(RandomTestUtil.randomString());

		itemHierarchyMaster.setLevel0(RandomTestUtil.randomString());

		itemHierarchyMaster.setLevel1(RandomTestUtil.randomString());

		itemHierarchyMaster.setLevel2(RandomTestUtil.randomString());

		itemHierarchyMaster.setLevel3(RandomTestUtil.randomString());

		itemHierarchyMaster.setLevel12Alias(RandomTestUtil.randomString());

		itemHierarchyMaster.setLevel8(RandomTestUtil.randomString());

		itemHierarchyMaster.setLevel11(RandomTestUtil.randomString());

		itemHierarchyMaster.setLevel4Alias(RandomTestUtil.randomString());

		itemHierarchyMaster.setLevel9(RandomTestUtil.randomString());

		itemHierarchyMaster.setLevel12(RandomTestUtil.randomString());

		itemHierarchyMaster.setLevel13(RandomTestUtil.randomString());

		itemHierarchyMaster.setLevel14(RandomTestUtil.randomString());

		itemHierarchyMaster.setRecordLockStatus(RandomTestUtil.randomBoolean());

		itemHierarchyMaster.setLevel0Tag(RandomTestUtil.randomString());

		itemHierarchyMaster.setLevel4(RandomTestUtil.randomString());

		itemHierarchyMaster.setLevel5(RandomTestUtil.randomString());

		itemHierarchyMaster.setLevel6(RandomTestUtil.randomString());

		itemHierarchyMaster.setLevel15Alias(RandomTestUtil.randomString());

		itemHierarchyMaster.setLevel7(RandomTestUtil.randomString());

		itemHierarchyMaster.setLevel10(RandomTestUtil.randomString());

		itemHierarchyMaster.setLevel19(RandomTestUtil.randomString());

		itemHierarchyMaster.setLevel15(RandomTestUtil.randomString());

		itemHierarchyMaster.setLevel16(RandomTestUtil.randomString());

		itemHierarchyMaster.setGtnCompany(RandomTestUtil.randomString());

		itemHierarchyMaster.setLevel17(RandomTestUtil.randomString());

		itemHierarchyMaster.setLevel18Alias(RandomTestUtil.randomString());

		itemHierarchyMaster.setLevel18(RandomTestUtil.randomString());

		itemHierarchyMaster.setInboundStatus(RandomTestUtil.randomString());

		_itemHierarchyMasters.add(_persistence.update(itemHierarchyMaster));

		return itemHierarchyMaster;
	}

	private List<ItemHierarchyMaster> _itemHierarchyMasters = new ArrayList<ItemHierarchyMaster>();
	private ItemHierarchyMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}