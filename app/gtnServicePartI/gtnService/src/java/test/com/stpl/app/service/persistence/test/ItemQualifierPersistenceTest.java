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
import com.liferay.portal.kernel.test.ReflectionTestUtil;
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

import com.stpl.app.exception.NoSuchItemQualifierException;
import com.stpl.app.model.ItemQualifier;
import com.stpl.app.service.ItemQualifierLocalServiceUtil;
import com.stpl.app.service.persistence.ItemQualifierPersistence;
import com.stpl.app.service.persistence.ItemQualifierUtil;

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
import java.util.Objects;
import java.util.Set;

/**
 * @generated
 */
@RunWith(Arquillian.class)
public class ItemQualifierPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = ItemQualifierUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<ItemQualifier> iterator = _itemQualifiers.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ItemQualifier itemQualifier = _persistence.create(pk);

		Assert.assertNotNull(itemQualifier);

		Assert.assertEquals(itemQualifier.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		ItemQualifier newItemQualifier = addItemQualifier();

		_persistence.remove(newItemQualifier);

		ItemQualifier existingItemQualifier = _persistence.fetchByPrimaryKey(newItemQualifier.getPrimaryKey());

		Assert.assertNull(existingItemQualifier);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addItemQualifier();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ItemQualifier newItemQualifier = _persistence.create(pk);

		newItemQualifier.setCreatedBy(RandomTestUtil.nextInt());

		newItemQualifier.setSpecificEntityCode(RandomTestUtil.randomString());

		newItemQualifier.setItemQualifierName(RandomTestUtil.randomString());

		newItemQualifier.setModifiedBy(RandomTestUtil.nextInt());

		newItemQualifier.setCreatedDate(RandomTestUtil.nextDate());

		newItemQualifier.setBatchId(RandomTestUtil.randomString());

		newItemQualifier.setModifiedDate(RandomTestUtil.nextDate());

		newItemQualifier.setEffectiveDates(RandomTestUtil.randomString());

		newItemQualifier.setNotes(RandomTestUtil.randomString());

		newItemQualifier.setItemQualifierValue(RandomTestUtil.randomString());

		newItemQualifier.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newItemQualifier.setSource(RandomTestUtil.randomString());

		newItemQualifier.setInboundStatus(RandomTestUtil.randomString());

		_itemQualifiers.add(_persistence.update(newItemQualifier));

		ItemQualifier existingItemQualifier = _persistence.findByPrimaryKey(newItemQualifier.getPrimaryKey());

		Assert.assertEquals(existingItemQualifier.getCreatedBy(),
			newItemQualifier.getCreatedBy());
		Assert.assertEquals(existingItemQualifier.getItemQualifierSid(),
			newItemQualifier.getItemQualifierSid());
		Assert.assertEquals(existingItemQualifier.getSpecificEntityCode(),
			newItemQualifier.getSpecificEntityCode());
		Assert.assertEquals(existingItemQualifier.getItemQualifierName(),
			newItemQualifier.getItemQualifierName());
		Assert.assertEquals(existingItemQualifier.getModifiedBy(),
			newItemQualifier.getModifiedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingItemQualifier.getCreatedDate()),
			Time.getShortTimestamp(newItemQualifier.getCreatedDate()));
		Assert.assertEquals(existingItemQualifier.getBatchId(),
			newItemQualifier.getBatchId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingItemQualifier.getModifiedDate()),
			Time.getShortTimestamp(newItemQualifier.getModifiedDate()));
		Assert.assertEquals(existingItemQualifier.getEffectiveDates(),
			newItemQualifier.getEffectiveDates());
		Assert.assertEquals(existingItemQualifier.getNotes(),
			newItemQualifier.getNotes());
		Assert.assertEquals(existingItemQualifier.getItemQualifierValue(),
			newItemQualifier.getItemQualifierValue());
		Assert.assertEquals(existingItemQualifier.getRecordLockStatus(),
			newItemQualifier.getRecordLockStatus());
		Assert.assertEquals(existingItemQualifier.getSource(),
			newItemQualifier.getSource());
		Assert.assertEquals(existingItemQualifier.getInboundStatus(),
			newItemQualifier.getInboundStatus());
	}

	@Test
	public void testCountByItemIrtQualifierName() throws Exception {
		_persistence.countByItemIrtQualifierName(StringPool.BLANK);

		_persistence.countByItemIrtQualifierName(StringPool.NULL);

		_persistence.countByItemIrtQualifierName((String)null);
	}

	@Test
	public void testCountByItemIrtQualifierByName() throws Exception {
		_persistence.countByItemIrtQualifierByName(StringPool.BLANK);

		_persistence.countByItemIrtQualifierByName(StringPool.NULL);

		_persistence.countByItemIrtQualifierByName((String)null);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		ItemQualifier newItemQualifier = addItemQualifier();

		ItemQualifier existingItemQualifier = _persistence.findByPrimaryKey(newItemQualifier.getPrimaryKey());

		Assert.assertEquals(existingItemQualifier, newItemQualifier);
	}

	@Test(expected = NoSuchItemQualifierException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<ItemQualifier> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("ITEM_QUALIFIER",
			"createdBy", true, "itemQualifierSid", true, "specificEntityCode",
			true, "itemQualifierName", true, "modifiedBy", true, "createdDate",
			true, "batchId", true, "modifiedDate", true, "effectiveDates",
			true, "notes", true, "itemQualifierValue", true,
			"recordLockStatus", true, "source", true, "inboundStatus", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		ItemQualifier newItemQualifier = addItemQualifier();

		ItemQualifier existingItemQualifier = _persistence.fetchByPrimaryKey(newItemQualifier.getPrimaryKey());

		Assert.assertEquals(existingItemQualifier, newItemQualifier);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ItemQualifier missingItemQualifier = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingItemQualifier);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		ItemQualifier newItemQualifier1 = addItemQualifier();
		ItemQualifier newItemQualifier2 = addItemQualifier();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newItemQualifier1.getPrimaryKey());
		primaryKeys.add(newItemQualifier2.getPrimaryKey());

		Map<Serializable, ItemQualifier> itemQualifiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, itemQualifiers.size());
		Assert.assertEquals(newItemQualifier1,
			itemQualifiers.get(newItemQualifier1.getPrimaryKey()));
		Assert.assertEquals(newItemQualifier2,
			itemQualifiers.get(newItemQualifier2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, ItemQualifier> itemQualifiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(itemQualifiers.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		ItemQualifier newItemQualifier = addItemQualifier();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newItemQualifier.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, ItemQualifier> itemQualifiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, itemQualifiers.size());
		Assert.assertEquals(newItemQualifier,
			itemQualifiers.get(newItemQualifier.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, ItemQualifier> itemQualifiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(itemQualifiers.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		ItemQualifier newItemQualifier = addItemQualifier();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newItemQualifier.getPrimaryKey());

		Map<Serializable, ItemQualifier> itemQualifiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, itemQualifiers.size());
		Assert.assertEquals(newItemQualifier,
			itemQualifiers.get(newItemQualifier.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = ItemQualifierLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ItemQualifier>() {
				@Override
				public void performAction(ItemQualifier itemQualifier) {
					Assert.assertNotNull(itemQualifier);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		ItemQualifier newItemQualifier = addItemQualifier();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ItemQualifier.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("itemQualifierSid",
				newItemQualifier.getItemQualifierSid()));

		List<ItemQualifier> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		ItemQualifier existingItemQualifier = result.get(0);

		Assert.assertEquals(existingItemQualifier, newItemQualifier);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ItemQualifier.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("itemQualifierSid",
				RandomTestUtil.nextInt()));

		List<ItemQualifier> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		ItemQualifier newItemQualifier = addItemQualifier();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ItemQualifier.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"itemQualifierSid"));

		Object newItemQualifierSid = newItemQualifier.getItemQualifierSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("itemQualifierSid",
				new Object[] { newItemQualifierSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingItemQualifierSid = result.get(0);

		Assert.assertEquals(existingItemQualifierSid, newItemQualifierSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ItemQualifier.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"itemQualifierSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("itemQualifierSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		ItemQualifier newItemQualifier = addItemQualifier();

		_persistence.clearCache();

		ItemQualifier existingItemQualifier = _persistence.findByPrimaryKey(newItemQualifier.getPrimaryKey());

		Assert.assertTrue(Objects.equals(
				existingItemQualifier.getItemQualifierName(),
				ReflectionTestUtil.invoke(existingItemQualifier,
					"getOriginalItemQualifierName", new Class<?>[0])));
	}

	protected ItemQualifier addItemQualifier() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ItemQualifier itemQualifier = _persistence.create(pk);

		itemQualifier.setCreatedBy(RandomTestUtil.nextInt());

		itemQualifier.setSpecificEntityCode(RandomTestUtil.randomString());

		itemQualifier.setItemQualifierName(RandomTestUtil.randomString());

		itemQualifier.setModifiedBy(RandomTestUtil.nextInt());

		itemQualifier.setCreatedDate(RandomTestUtil.nextDate());

		itemQualifier.setBatchId(RandomTestUtil.randomString());

		itemQualifier.setModifiedDate(RandomTestUtil.nextDate());

		itemQualifier.setEffectiveDates(RandomTestUtil.randomString());

		itemQualifier.setNotes(RandomTestUtil.randomString());

		itemQualifier.setItemQualifierValue(RandomTestUtil.randomString());

		itemQualifier.setRecordLockStatus(RandomTestUtil.randomBoolean());

		itemQualifier.setSource(RandomTestUtil.randomString());

		itemQualifier.setInboundStatus(RandomTestUtil.randomString());

		_itemQualifiers.add(_persistence.update(itemQualifier));

		return itemQualifier;
	}

	private List<ItemQualifier> _itemQualifiers = new ArrayList<ItemQualifier>();
	private ItemQualifierPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}