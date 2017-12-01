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

import com.stpl.app.exception.NoSuchItemIdentifierException;
import com.stpl.app.model.ItemIdentifier;
import com.stpl.app.service.ItemIdentifierLocalServiceUtil;
import com.stpl.app.service.persistence.ItemIdentifierPersistence;
import com.stpl.app.service.persistence.ItemIdentifierUtil;

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
public class ItemIdentifierPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = ItemIdentifierUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<ItemIdentifier> iterator = _itemIdentifiers.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ItemIdentifier itemIdentifier = _persistence.create(pk);

		Assert.assertNotNull(itemIdentifier);

		Assert.assertEquals(itemIdentifier.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		ItemIdentifier newItemIdentifier = addItemIdentifier();

		_persistence.remove(newItemIdentifier);

		ItemIdentifier existingItemIdentifier = _persistence.fetchByPrimaryKey(newItemIdentifier.getPrimaryKey());

		Assert.assertNull(existingItemIdentifier);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addItemIdentifier();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ItemIdentifier newItemIdentifier = _persistence.create(pk);

		newItemIdentifier.setItemMasterSid(RandomTestUtil.nextInt());

		newItemIdentifier.setEndDate(RandomTestUtil.nextDate());

		newItemIdentifier.setModifiedDate(RandomTestUtil.nextDate());

		newItemIdentifier.setIdentifierStatus(RandomTestUtil.nextInt());

		newItemIdentifier.setEntityCode(RandomTestUtil.randomString());

		newItemIdentifier.setItemIdentifierValue(RandomTestUtil.randomString());

		newItemIdentifier.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newItemIdentifier.setItemQualifierSid(RandomTestUtil.nextInt());

		newItemIdentifier.setStartDate(RandomTestUtil.nextDate());

		newItemIdentifier.setCreatedDate(RandomTestUtil.nextDate());

		newItemIdentifier.setSource(RandomTestUtil.randomString());

		newItemIdentifier.setCreatedBy(RandomTestUtil.nextInt());

		newItemIdentifier.setBatchId(RandomTestUtil.randomString());

		newItemIdentifier.setModifiedBy(RandomTestUtil.nextInt());

		newItemIdentifier.setInboundStatus(RandomTestUtil.randomString());

		_itemIdentifiers.add(_persistence.update(newItemIdentifier));

		ItemIdentifier existingItemIdentifier = _persistence.findByPrimaryKey(newItemIdentifier.getPrimaryKey());

		Assert.assertEquals(existingItemIdentifier.getItemIdentifierSid(),
			newItemIdentifier.getItemIdentifierSid());
		Assert.assertEquals(existingItemIdentifier.getItemMasterSid(),
			newItemIdentifier.getItemMasterSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingItemIdentifier.getEndDate()),
			Time.getShortTimestamp(newItemIdentifier.getEndDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingItemIdentifier.getModifiedDate()),
			Time.getShortTimestamp(newItemIdentifier.getModifiedDate()));
		Assert.assertEquals(existingItemIdentifier.getIdentifierStatus(),
			newItemIdentifier.getIdentifierStatus());
		Assert.assertEquals(existingItemIdentifier.getEntityCode(),
			newItemIdentifier.getEntityCode());
		Assert.assertEquals(existingItemIdentifier.getItemIdentifierValue(),
			newItemIdentifier.getItemIdentifierValue());
		Assert.assertEquals(existingItemIdentifier.getRecordLockStatus(),
			newItemIdentifier.getRecordLockStatus());
		Assert.assertEquals(existingItemIdentifier.getItemQualifierSid(),
			newItemIdentifier.getItemQualifierSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingItemIdentifier.getStartDate()),
			Time.getShortTimestamp(newItemIdentifier.getStartDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingItemIdentifier.getCreatedDate()),
			Time.getShortTimestamp(newItemIdentifier.getCreatedDate()));
		Assert.assertEquals(existingItemIdentifier.getSource(),
			newItemIdentifier.getSource());
		Assert.assertEquals(existingItemIdentifier.getCreatedBy(),
			newItemIdentifier.getCreatedBy());
		Assert.assertEquals(existingItemIdentifier.getBatchId(),
			newItemIdentifier.getBatchId());
		Assert.assertEquals(existingItemIdentifier.getModifiedBy(),
			newItemIdentifier.getModifiedBy());
		Assert.assertEquals(existingItemIdentifier.getInboundStatus(),
			newItemIdentifier.getInboundStatus());
	}

	@Test
	public void testCountByItemIrtIdentifier() throws Exception {
		_persistence.countByItemIrtIdentifier(StringPool.BLANK,
			RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
			RandomTestUtil.nextDate());

		_persistence.countByItemIrtIdentifier(StringPool.NULL, 0, 0,
			RandomTestUtil.nextDate());

		_persistence.countByItemIrtIdentifier((String)null, 0, 0,
			RandomTestUtil.nextDate());
	}

	@Test
	public void testCountByItemIrtIdentifierDetails() throws Exception {
		_persistence.countByItemIrtIdentifierDetails(RandomTestUtil.nextInt());

		_persistence.countByItemIrtIdentifierDetails(0);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		ItemIdentifier newItemIdentifier = addItemIdentifier();

		ItemIdentifier existingItemIdentifier = _persistence.findByPrimaryKey(newItemIdentifier.getPrimaryKey());

		Assert.assertEquals(existingItemIdentifier, newItemIdentifier);
	}

	@Test(expected = NoSuchItemIdentifierException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<ItemIdentifier> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("ITEM_IDENTIFIER",
			"itemIdentifierSid", true, "itemMasterSid", true, "endDate", true,
			"modifiedDate", true, "identifierStatus", true, "entityCode", true,
			"itemIdentifierValue", true, "recordLockStatus", true,
			"itemQualifierSid", true, "startDate", true, "createdDate", true,
			"source", true, "createdBy", true, "batchId", true, "modifiedBy",
			true, "inboundStatus", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		ItemIdentifier newItemIdentifier = addItemIdentifier();

		ItemIdentifier existingItemIdentifier = _persistence.fetchByPrimaryKey(newItemIdentifier.getPrimaryKey());

		Assert.assertEquals(existingItemIdentifier, newItemIdentifier);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ItemIdentifier missingItemIdentifier = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingItemIdentifier);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		ItemIdentifier newItemIdentifier1 = addItemIdentifier();
		ItemIdentifier newItemIdentifier2 = addItemIdentifier();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newItemIdentifier1.getPrimaryKey());
		primaryKeys.add(newItemIdentifier2.getPrimaryKey());

		Map<Serializable, ItemIdentifier> itemIdentifiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, itemIdentifiers.size());
		Assert.assertEquals(newItemIdentifier1,
			itemIdentifiers.get(newItemIdentifier1.getPrimaryKey()));
		Assert.assertEquals(newItemIdentifier2,
			itemIdentifiers.get(newItemIdentifier2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, ItemIdentifier> itemIdentifiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(itemIdentifiers.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		ItemIdentifier newItemIdentifier = addItemIdentifier();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newItemIdentifier.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, ItemIdentifier> itemIdentifiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, itemIdentifiers.size());
		Assert.assertEquals(newItemIdentifier,
			itemIdentifiers.get(newItemIdentifier.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, ItemIdentifier> itemIdentifiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(itemIdentifiers.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		ItemIdentifier newItemIdentifier = addItemIdentifier();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newItemIdentifier.getPrimaryKey());

		Map<Serializable, ItemIdentifier> itemIdentifiers = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, itemIdentifiers.size());
		Assert.assertEquals(newItemIdentifier,
			itemIdentifiers.get(newItemIdentifier.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = ItemIdentifierLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ItemIdentifier>() {
				@Override
				public void performAction(ItemIdentifier itemIdentifier) {
					Assert.assertNotNull(itemIdentifier);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		ItemIdentifier newItemIdentifier = addItemIdentifier();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ItemIdentifier.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("itemIdentifierSid",
				newItemIdentifier.getItemIdentifierSid()));

		List<ItemIdentifier> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		ItemIdentifier existingItemIdentifier = result.get(0);

		Assert.assertEquals(existingItemIdentifier, newItemIdentifier);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ItemIdentifier.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("itemIdentifierSid",
				RandomTestUtil.nextInt()));

		List<ItemIdentifier> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		ItemIdentifier newItemIdentifier = addItemIdentifier();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ItemIdentifier.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"itemIdentifierSid"));

		Object newItemIdentifierSid = newItemIdentifier.getItemIdentifierSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("itemIdentifierSid",
				new Object[] { newItemIdentifierSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingItemIdentifierSid = result.get(0);

		Assert.assertEquals(existingItemIdentifierSid, newItemIdentifierSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ItemIdentifier.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"itemIdentifierSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("itemIdentifierSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected ItemIdentifier addItemIdentifier() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ItemIdentifier itemIdentifier = _persistence.create(pk);

		itemIdentifier.setItemMasterSid(RandomTestUtil.nextInt());

		itemIdentifier.setEndDate(RandomTestUtil.nextDate());

		itemIdentifier.setModifiedDate(RandomTestUtil.nextDate());

		itemIdentifier.setIdentifierStatus(RandomTestUtil.nextInt());

		itemIdentifier.setEntityCode(RandomTestUtil.randomString());

		itemIdentifier.setItemIdentifierValue(RandomTestUtil.randomString());

		itemIdentifier.setRecordLockStatus(RandomTestUtil.randomBoolean());

		itemIdentifier.setItemQualifierSid(RandomTestUtil.nextInt());

		itemIdentifier.setStartDate(RandomTestUtil.nextDate());

		itemIdentifier.setCreatedDate(RandomTestUtil.nextDate());

		itemIdentifier.setSource(RandomTestUtil.randomString());

		itemIdentifier.setCreatedBy(RandomTestUtil.nextInt());

		itemIdentifier.setBatchId(RandomTestUtil.randomString());

		itemIdentifier.setModifiedBy(RandomTestUtil.nextInt());

		itemIdentifier.setInboundStatus(RandomTestUtil.randomString());

		_itemIdentifiers.add(_persistence.update(itemIdentifier));

		return itemIdentifier;
	}

	private List<ItemIdentifier> _itemIdentifiers = new ArrayList<ItemIdentifier>();
	private ItemIdentifierPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}