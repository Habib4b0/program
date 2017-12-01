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

import com.stpl.app.exception.NoSuchItemPricingQualifierException;
import com.stpl.app.model.ItemPricingQualifier;
import com.stpl.app.service.ItemPricingQualifierLocalServiceUtil;
import com.stpl.app.service.persistence.ItemPricingQualifierPersistence;
import com.stpl.app.service.persistence.ItemPricingQualifierUtil;

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
public class ItemPricingQualifierPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = ItemPricingQualifierUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<ItemPricingQualifier> iterator = _itemPricingQualifiers.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ItemPricingQualifier itemPricingQualifier = _persistence.create(pk);

		Assert.assertNotNull(itemPricingQualifier);

		Assert.assertEquals(itemPricingQualifier.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		ItemPricingQualifier newItemPricingQualifier = addItemPricingQualifier();

		_persistence.remove(newItemPricingQualifier);

		ItemPricingQualifier existingItemPricingQualifier = _persistence.fetchByPrimaryKey(newItemPricingQualifier.getPrimaryKey());

		Assert.assertNull(existingItemPricingQualifier);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addItemPricingQualifier();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ItemPricingQualifier newItemPricingQualifier = _persistence.create(pk);

		newItemPricingQualifier.setCreatedBy(RandomTestUtil.nextInt());

		newItemPricingQualifier.setSpecificEntityCode(RandomTestUtil.randomString());

		newItemPricingQualifier.setModifiedBy(RandomTestUtil.nextInt());

		newItemPricingQualifier.setCreatedDate(RandomTestUtil.nextDate());

		newItemPricingQualifier.setBatchId(RandomTestUtil.randomString());

		newItemPricingQualifier.setModifiedDate(RandomTestUtil.nextDate());

		newItemPricingQualifier.setEffectiveDates(RandomTestUtil.randomString());

		newItemPricingQualifier.setNotes(RandomTestUtil.randomString());

		newItemPricingQualifier.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newItemPricingQualifier.setSource(RandomTestUtil.randomString());

		newItemPricingQualifier.setPricingQualifier(RandomTestUtil.randomString());

		newItemPricingQualifier.setItemPricingQualifierName(RandomTestUtil.randomString());

		newItemPricingQualifier.setInboundStatus(RandomTestUtil.randomString());

		_itemPricingQualifiers.add(_persistence.update(newItemPricingQualifier));

		ItemPricingQualifier existingItemPricingQualifier = _persistence.findByPrimaryKey(newItemPricingQualifier.getPrimaryKey());

		Assert.assertEquals(existingItemPricingQualifier.getCreatedBy(),
			newItemPricingQualifier.getCreatedBy());
		Assert.assertEquals(existingItemPricingQualifier.getItemPricingQualifierSid(),
			newItemPricingQualifier.getItemPricingQualifierSid());
		Assert.assertEquals(existingItemPricingQualifier.getSpecificEntityCode(),
			newItemPricingQualifier.getSpecificEntityCode());
		Assert.assertEquals(existingItemPricingQualifier.getModifiedBy(),
			newItemPricingQualifier.getModifiedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingItemPricingQualifier.getCreatedDate()),
			Time.getShortTimestamp(newItemPricingQualifier.getCreatedDate()));
		Assert.assertEquals(existingItemPricingQualifier.getBatchId(),
			newItemPricingQualifier.getBatchId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingItemPricingQualifier.getModifiedDate()),
			Time.getShortTimestamp(newItemPricingQualifier.getModifiedDate()));
		Assert.assertEquals(existingItemPricingQualifier.getEffectiveDates(),
			newItemPricingQualifier.getEffectiveDates());
		Assert.assertEquals(existingItemPricingQualifier.getNotes(),
			newItemPricingQualifier.getNotes());
		Assert.assertEquals(existingItemPricingQualifier.getRecordLockStatus(),
			newItemPricingQualifier.getRecordLockStatus());
		Assert.assertEquals(existingItemPricingQualifier.getSource(),
			newItemPricingQualifier.getSource());
		Assert.assertEquals(existingItemPricingQualifier.getPricingQualifier(),
			newItemPricingQualifier.getPricingQualifier());
		Assert.assertEquals(existingItemPricingQualifier.getItemPricingQualifierName(),
			newItemPricingQualifier.getItemPricingQualifierName());
		Assert.assertEquals(existingItemPricingQualifier.getInboundStatus(),
			newItemPricingQualifier.getInboundStatus());
	}

	@Test
	public void testCountByitemPricingCodeQualifierByName()
		throws Exception {
		_persistence.countByitemPricingCodeQualifierByName(StringPool.BLANK);

		_persistence.countByitemPricingCodeQualifierByName(StringPool.NULL);

		_persistence.countByitemPricingCodeQualifierByName((String)null);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		ItemPricingQualifier newItemPricingQualifier = addItemPricingQualifier();

		ItemPricingQualifier existingItemPricingQualifier = _persistence.findByPrimaryKey(newItemPricingQualifier.getPrimaryKey());

		Assert.assertEquals(existingItemPricingQualifier,
			newItemPricingQualifier);
	}

	@Test(expected = NoSuchItemPricingQualifierException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<ItemPricingQualifier> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("ITEM_PRICING_QUALIFIER",
			"createdBy", true, "itemPricingQualifierSid", true,
			"specificEntityCode", true, "modifiedBy", true, "createdDate",
			true, "batchId", true, "modifiedDate", true, "effectiveDates",
			true, "notes", true, "recordLockStatus", true, "source", true,
			"pricingQualifier", true, "itemPricingQualifierName", true,
			"inboundStatus", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		ItemPricingQualifier newItemPricingQualifier = addItemPricingQualifier();

		ItemPricingQualifier existingItemPricingQualifier = _persistence.fetchByPrimaryKey(newItemPricingQualifier.getPrimaryKey());

		Assert.assertEquals(existingItemPricingQualifier,
			newItemPricingQualifier);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ItemPricingQualifier missingItemPricingQualifier = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingItemPricingQualifier);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		ItemPricingQualifier newItemPricingQualifier1 = addItemPricingQualifier();
		ItemPricingQualifier newItemPricingQualifier2 = addItemPricingQualifier();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newItemPricingQualifier1.getPrimaryKey());
		primaryKeys.add(newItemPricingQualifier2.getPrimaryKey());

		Map<Serializable, ItemPricingQualifier> itemPricingQualifiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, itemPricingQualifiers.size());
		Assert.assertEquals(newItemPricingQualifier1,
			itemPricingQualifiers.get(newItemPricingQualifier1.getPrimaryKey()));
		Assert.assertEquals(newItemPricingQualifier2,
			itemPricingQualifiers.get(newItemPricingQualifier2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, ItemPricingQualifier> itemPricingQualifiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(itemPricingQualifiers.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		ItemPricingQualifier newItemPricingQualifier = addItemPricingQualifier();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newItemPricingQualifier.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, ItemPricingQualifier> itemPricingQualifiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, itemPricingQualifiers.size());
		Assert.assertEquals(newItemPricingQualifier,
			itemPricingQualifiers.get(newItemPricingQualifier.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, ItemPricingQualifier> itemPricingQualifiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(itemPricingQualifiers.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		ItemPricingQualifier newItemPricingQualifier = addItemPricingQualifier();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newItemPricingQualifier.getPrimaryKey());

		Map<Serializable, ItemPricingQualifier> itemPricingQualifiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, itemPricingQualifiers.size());
		Assert.assertEquals(newItemPricingQualifier,
			itemPricingQualifiers.get(newItemPricingQualifier.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = ItemPricingQualifierLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ItemPricingQualifier>() {
				@Override
				public void performAction(
					ItemPricingQualifier itemPricingQualifier) {
					Assert.assertNotNull(itemPricingQualifier);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		ItemPricingQualifier newItemPricingQualifier = addItemPricingQualifier();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ItemPricingQualifier.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("itemPricingQualifierSid",
				newItemPricingQualifier.getItemPricingQualifierSid()));

		List<ItemPricingQualifier> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		ItemPricingQualifier existingItemPricingQualifier = result.get(0);

		Assert.assertEquals(existingItemPricingQualifier,
			newItemPricingQualifier);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ItemPricingQualifier.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("itemPricingQualifierSid",
				RandomTestUtil.nextInt()));

		List<ItemPricingQualifier> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		ItemPricingQualifier newItemPricingQualifier = addItemPricingQualifier();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ItemPricingQualifier.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"itemPricingQualifierSid"));

		Object newItemPricingQualifierSid = newItemPricingQualifier.getItemPricingQualifierSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("itemPricingQualifierSid",
				new Object[] { newItemPricingQualifierSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingItemPricingQualifierSid = result.get(0);

		Assert.assertEquals(existingItemPricingQualifierSid,
			newItemPricingQualifierSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ItemPricingQualifier.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"itemPricingQualifierSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("itemPricingQualifierSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testResetOriginalValues() throws Exception {
		ItemPricingQualifier newItemPricingQualifier = addItemPricingQualifier();

		_persistence.clearCache();

		ItemPricingQualifier existingItemPricingQualifier = _persistence.findByPrimaryKey(newItemPricingQualifier.getPrimaryKey());

		Assert.assertTrue(Objects.equals(
				existingItemPricingQualifier.getItemPricingQualifierName(),
				ReflectionTestUtil.invoke(existingItemPricingQualifier,
					"getOriginalItemPricingQualifierName", new Class<?>[0])));
	}

	protected ItemPricingQualifier addItemPricingQualifier()
		throws Exception {
		int pk = RandomTestUtil.nextInt();

		ItemPricingQualifier itemPricingQualifier = _persistence.create(pk);

		itemPricingQualifier.setCreatedBy(RandomTestUtil.nextInt());

		itemPricingQualifier.setSpecificEntityCode(RandomTestUtil.randomString());

		itemPricingQualifier.setModifiedBy(RandomTestUtil.nextInt());

		itemPricingQualifier.setCreatedDate(RandomTestUtil.nextDate());

		itemPricingQualifier.setBatchId(RandomTestUtil.randomString());

		itemPricingQualifier.setModifiedDate(RandomTestUtil.nextDate());

		itemPricingQualifier.setEffectiveDates(RandomTestUtil.randomString());

		itemPricingQualifier.setNotes(RandomTestUtil.randomString());

		itemPricingQualifier.setRecordLockStatus(RandomTestUtil.randomBoolean());

		itemPricingQualifier.setSource(RandomTestUtil.randomString());

		itemPricingQualifier.setPricingQualifier(RandomTestUtil.randomString());

		itemPricingQualifier.setItemPricingQualifierName(RandomTestUtil.randomString());

		itemPricingQualifier.setInboundStatus(RandomTestUtil.randomString());

		_itemPricingQualifiers.add(_persistence.update(itemPricingQualifier));

		return itemPricingQualifier;
	}

	private List<ItemPricingQualifier> _itemPricingQualifiers = new ArrayList<ItemPricingQualifier>();
	private ItemPricingQualifierPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}