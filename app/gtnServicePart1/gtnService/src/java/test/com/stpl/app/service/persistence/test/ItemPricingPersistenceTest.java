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
import com.liferay.portal.kernel.test.AssertUtils;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.portal.kernel.util.OrderByComparatorFactoryUtil;
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.exception.NoSuchItemPricingException;
import com.stpl.app.model.ItemPricing;
import com.stpl.app.service.ItemPricingLocalServiceUtil;
import com.stpl.app.service.persistence.ItemPricingPersistence;
import com.stpl.app.service.persistence.ItemPricingUtil;

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
public class ItemPricingPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = ItemPricingUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<ItemPricing> iterator = _itemPricings.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ItemPricing itemPricing = _persistence.create(pk);

		Assert.assertNotNull(itemPricing);

		Assert.assertEquals(itemPricing.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		ItemPricing newItemPricing = addItemPricing();

		_persistence.remove(newItemPricing);

		ItemPricing existingItemPricing = _persistence.fetchByPrimaryKey(newItemPricing.getPrimaryKey());

		Assert.assertNull(existingItemPricing);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addItemPricing();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ItemPricing newItemPricing = _persistence.create(pk);

		newItemPricing.setItemMasterSid(RandomTestUtil.nextInt());

		newItemPricing.setItemPricingQualifierSid(RandomTestUtil.nextInt());

		newItemPricing.setItemPrice(RandomTestUtil.nextDouble());

		newItemPricing.setEndDate(RandomTestUtil.nextDate());

		newItemPricing.setModifiedDate(RandomTestUtil.nextDate());

		newItemPricing.setEntityCode(RandomTestUtil.randomString());

		newItemPricing.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newItemPricing.setStartDate(RandomTestUtil.nextDate());

		newItemPricing.setCreatedDate(RandomTestUtil.nextDate());

		newItemPricing.setCreatedBy(RandomTestUtil.nextInt());

		newItemPricing.setSource(RandomTestUtil.randomString());

		newItemPricing.setBatchId(RandomTestUtil.randomString());

		newItemPricing.setItemUom(RandomTestUtil.nextInt());

		newItemPricing.setModifiedBy(RandomTestUtil.nextInt());

		newItemPricing.setInboundStatus(RandomTestUtil.randomString());

		newItemPricing.setPricingCodeStatus(RandomTestUtil.nextInt());

		newItemPricing.setItemPricePrecision(RandomTestUtil.nextInt());

		_itemPricings.add(_persistence.update(newItemPricing));

		ItemPricing existingItemPricing = _persistence.findByPrimaryKey(newItemPricing.getPrimaryKey());

		Assert.assertEquals(existingItemPricing.getItemMasterSid(),
			newItemPricing.getItemMasterSid());
		Assert.assertEquals(existingItemPricing.getItemPricingQualifierSid(),
			newItemPricing.getItemPricingQualifierSid());
		AssertUtils.assertEquals(existingItemPricing.getItemPrice(),
			newItemPricing.getItemPrice());
		Assert.assertEquals(Time.getShortTimestamp(
				existingItemPricing.getEndDate()),
			Time.getShortTimestamp(newItemPricing.getEndDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingItemPricing.getModifiedDate()),
			Time.getShortTimestamp(newItemPricing.getModifiedDate()));
		Assert.assertEquals(existingItemPricing.getEntityCode(),
			newItemPricing.getEntityCode());
		Assert.assertEquals(existingItemPricing.getRecordLockStatus(),
			newItemPricing.getRecordLockStatus());
		Assert.assertEquals(Time.getShortTimestamp(
				existingItemPricing.getStartDate()),
			Time.getShortTimestamp(newItemPricing.getStartDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingItemPricing.getCreatedDate()),
			Time.getShortTimestamp(newItemPricing.getCreatedDate()));
		Assert.assertEquals(existingItemPricing.getCreatedBy(),
			newItemPricing.getCreatedBy());
		Assert.assertEquals(existingItemPricing.getSource(),
			newItemPricing.getSource());
		Assert.assertEquals(existingItemPricing.getBatchId(),
			newItemPricing.getBatchId());
		Assert.assertEquals(existingItemPricing.getItemUom(),
			newItemPricing.getItemUom());
		Assert.assertEquals(existingItemPricing.getModifiedBy(),
			newItemPricing.getModifiedBy());
		Assert.assertEquals(existingItemPricing.getInboundStatus(),
			newItemPricing.getInboundStatus());
		Assert.assertEquals(existingItemPricing.getItemPricingSid(),
			newItemPricing.getItemPricingSid());
		Assert.assertEquals(existingItemPricing.getPricingCodeStatus(),
			newItemPricing.getPricingCodeStatus());
		Assert.assertEquals(existingItemPricing.getItemPricePrecision(),
			newItemPricing.getItemPricePrecision());
	}

	@Test
	public void testCountByItemPricing() throws Exception {
		_persistence.countByItemPricing(RandomTestUtil.nextInt(),
			RandomTestUtil.nextInt(), RandomTestUtil.nextInt(),
			RandomTestUtil.nextInt(), RandomTestUtil.nextDate());

		_persistence.countByItemPricing(0, 0, 0, 0, RandomTestUtil.nextDate());
	}

	@Test
	public void testCountByItemPricingDetails() throws Exception {
		_persistence.countByItemPricingDetails(RandomTestUtil.nextInt());

		_persistence.countByItemPricingDetails(0);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		ItemPricing newItemPricing = addItemPricing();

		ItemPricing existingItemPricing = _persistence.findByPrimaryKey(newItemPricing.getPrimaryKey());

		Assert.assertEquals(existingItemPricing, newItemPricing);
	}

	@Test(expected = NoSuchItemPricingException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<ItemPricing> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("ITEM_PRICING",
			"itemMasterSid", true, "itemPricingQualifierSid", true,
			"itemPrice", true, "endDate", true, "modifiedDate", true,
			"entityCode", true, "recordLockStatus", true, "startDate", true,
			"createdDate", true, "createdBy", true, "source", true, "batchId",
			true, "itemUom", true, "modifiedBy", true, "inboundStatus", true,
			"itemPricingSid", true, "pricingCodeStatus", true,
			"itemPricePrecision", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		ItemPricing newItemPricing = addItemPricing();

		ItemPricing existingItemPricing = _persistence.fetchByPrimaryKey(newItemPricing.getPrimaryKey());

		Assert.assertEquals(existingItemPricing, newItemPricing);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ItemPricing missingItemPricing = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingItemPricing);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		ItemPricing newItemPricing1 = addItemPricing();
		ItemPricing newItemPricing2 = addItemPricing();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newItemPricing1.getPrimaryKey());
		primaryKeys.add(newItemPricing2.getPrimaryKey());

		Map<Serializable, ItemPricing> itemPricings = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, itemPricings.size());
		Assert.assertEquals(newItemPricing1,
			itemPricings.get(newItemPricing1.getPrimaryKey()));
		Assert.assertEquals(newItemPricing2,
			itemPricings.get(newItemPricing2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, ItemPricing> itemPricings = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(itemPricings.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		ItemPricing newItemPricing = addItemPricing();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newItemPricing.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, ItemPricing> itemPricings = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, itemPricings.size());
		Assert.assertEquals(newItemPricing,
			itemPricings.get(newItemPricing.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, ItemPricing> itemPricings = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(itemPricings.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		ItemPricing newItemPricing = addItemPricing();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newItemPricing.getPrimaryKey());

		Map<Serializable, ItemPricing> itemPricings = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, itemPricings.size());
		Assert.assertEquals(newItemPricing,
			itemPricings.get(newItemPricing.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = ItemPricingLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ItemPricing>() {
				@Override
				public void performAction(ItemPricing itemPricing) {
					Assert.assertNotNull(itemPricing);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		ItemPricing newItemPricing = addItemPricing();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ItemPricing.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("itemPricingSid",
				newItemPricing.getItemPricingSid()));

		List<ItemPricing> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		ItemPricing existingItemPricing = result.get(0);

		Assert.assertEquals(existingItemPricing, newItemPricing);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ItemPricing.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("itemPricingSid",
				RandomTestUtil.nextInt()));

		List<ItemPricing> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		ItemPricing newItemPricing = addItemPricing();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ItemPricing.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"itemPricingSid"));

		Object newItemPricingSid = newItemPricing.getItemPricingSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("itemPricingSid",
				new Object[] { newItemPricingSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingItemPricingSid = result.get(0);

		Assert.assertEquals(existingItemPricingSid, newItemPricingSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ItemPricing.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"itemPricingSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("itemPricingSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected ItemPricing addItemPricing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ItemPricing itemPricing = _persistence.create(pk);

		itemPricing.setItemMasterSid(RandomTestUtil.nextInt());

		itemPricing.setItemPricingQualifierSid(RandomTestUtil.nextInt());

		itemPricing.setItemPrice(RandomTestUtil.nextDouble());

		itemPricing.setEndDate(RandomTestUtil.nextDate());

		itemPricing.setModifiedDate(RandomTestUtil.nextDate());

		itemPricing.setEntityCode(RandomTestUtil.randomString());

		itemPricing.setRecordLockStatus(RandomTestUtil.randomBoolean());

		itemPricing.setStartDate(RandomTestUtil.nextDate());

		itemPricing.setCreatedDate(RandomTestUtil.nextDate());

		itemPricing.setCreatedBy(RandomTestUtil.nextInt());

		itemPricing.setSource(RandomTestUtil.randomString());

		itemPricing.setBatchId(RandomTestUtil.randomString());

		itemPricing.setItemUom(RandomTestUtil.nextInt());

		itemPricing.setModifiedBy(RandomTestUtil.nextInt());

		itemPricing.setInboundStatus(RandomTestUtil.randomString());

		itemPricing.setPricingCodeStatus(RandomTestUtil.nextInt());

		itemPricing.setItemPricePrecision(RandomTestUtil.nextInt());

		_itemPricings.add(_persistence.update(itemPricing));

		return itemPricing;
	}

	private List<ItemPricing> _itemPricings = new ArrayList<ItemPricing>();
	private ItemPricingPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}