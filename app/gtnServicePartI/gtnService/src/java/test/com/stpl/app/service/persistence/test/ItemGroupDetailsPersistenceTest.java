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
import com.liferay.portal.kernel.util.Time;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.exception.NoSuchItemGroupDetailsException;
import com.stpl.app.model.ItemGroupDetails;
import com.stpl.app.service.ItemGroupDetailsLocalServiceUtil;
import com.stpl.app.service.persistence.ItemGroupDetailsPersistence;
import com.stpl.app.service.persistence.ItemGroupDetailsUtil;

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
public class ItemGroupDetailsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = ItemGroupDetailsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<ItemGroupDetails> iterator = _itemGroupDetailses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ItemGroupDetails itemGroupDetails = _persistence.create(pk);

		Assert.assertNotNull(itemGroupDetails);

		Assert.assertEquals(itemGroupDetails.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		ItemGroupDetails newItemGroupDetails = addItemGroupDetails();

		_persistence.remove(newItemGroupDetails);

		ItemGroupDetails existingItemGroupDetails = _persistence.fetchByPrimaryKey(newItemGroupDetails.getPrimaryKey());

		Assert.assertNull(existingItemGroupDetails);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addItemGroupDetails();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ItemGroupDetails newItemGroupDetails = _persistence.create(pk);

		newItemGroupDetails.setCreatedDate(RandomTestUtil.nextDate());

		newItemGroupDetails.setCreatedBy(RandomTestUtil.nextInt());

		newItemGroupDetails.setItemMasterSid(RandomTestUtil.nextInt());

		newItemGroupDetails.setVersionNo(RandomTestUtil.nextInt());

		newItemGroupDetails.setModifiedBy(RandomTestUtil.nextInt());

		newItemGroupDetails.setModifiedDate(RandomTestUtil.nextDate());

		newItemGroupDetails.setItemGroupSid(RandomTestUtil.nextInt());

		_itemGroupDetailses.add(_persistence.update(newItemGroupDetails));

		ItemGroupDetails existingItemGroupDetails = _persistence.findByPrimaryKey(newItemGroupDetails.getPrimaryKey());

		Assert.assertEquals(existingItemGroupDetails.getItemGroupDetailsSid(),
			newItemGroupDetails.getItemGroupDetailsSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingItemGroupDetails.getCreatedDate()),
			Time.getShortTimestamp(newItemGroupDetails.getCreatedDate()));
		Assert.assertEquals(existingItemGroupDetails.getCreatedBy(),
			newItemGroupDetails.getCreatedBy());
		Assert.assertEquals(existingItemGroupDetails.getItemMasterSid(),
			newItemGroupDetails.getItemMasterSid());
		Assert.assertEquals(existingItemGroupDetails.getVersionNo(),
			newItemGroupDetails.getVersionNo());
		Assert.assertEquals(existingItemGroupDetails.getModifiedBy(),
			newItemGroupDetails.getModifiedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingItemGroupDetails.getModifiedDate()),
			Time.getShortTimestamp(newItemGroupDetails.getModifiedDate()));
		Assert.assertEquals(existingItemGroupDetails.getItemGroupSid(),
			newItemGroupDetails.getItemGroupSid());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		ItemGroupDetails newItemGroupDetails = addItemGroupDetails();

		ItemGroupDetails existingItemGroupDetails = _persistence.findByPrimaryKey(newItemGroupDetails.getPrimaryKey());

		Assert.assertEquals(existingItemGroupDetails, newItemGroupDetails);
	}

	@Test(expected = NoSuchItemGroupDetailsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<ItemGroupDetails> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("ITEM_GROUP_DETAILS",
			"itemGroupDetailsSid", true, "createdDate", true, "createdBy",
			true, "itemMasterSid", true, "versionNo", true, "modifiedBy", true,
			"modifiedDate", true, "itemGroupSid", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		ItemGroupDetails newItemGroupDetails = addItemGroupDetails();

		ItemGroupDetails existingItemGroupDetails = _persistence.fetchByPrimaryKey(newItemGroupDetails.getPrimaryKey());

		Assert.assertEquals(existingItemGroupDetails, newItemGroupDetails);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ItemGroupDetails missingItemGroupDetails = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingItemGroupDetails);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		ItemGroupDetails newItemGroupDetails1 = addItemGroupDetails();
		ItemGroupDetails newItemGroupDetails2 = addItemGroupDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newItemGroupDetails1.getPrimaryKey());
		primaryKeys.add(newItemGroupDetails2.getPrimaryKey());

		Map<Serializable, ItemGroupDetails> itemGroupDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, itemGroupDetailses.size());
		Assert.assertEquals(newItemGroupDetails1,
			itemGroupDetailses.get(newItemGroupDetails1.getPrimaryKey()));
		Assert.assertEquals(newItemGroupDetails2,
			itemGroupDetailses.get(newItemGroupDetails2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, ItemGroupDetails> itemGroupDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(itemGroupDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		ItemGroupDetails newItemGroupDetails = addItemGroupDetails();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newItemGroupDetails.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, ItemGroupDetails> itemGroupDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, itemGroupDetailses.size());
		Assert.assertEquals(newItemGroupDetails,
			itemGroupDetailses.get(newItemGroupDetails.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, ItemGroupDetails> itemGroupDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(itemGroupDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		ItemGroupDetails newItemGroupDetails = addItemGroupDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newItemGroupDetails.getPrimaryKey());

		Map<Serializable, ItemGroupDetails> itemGroupDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, itemGroupDetailses.size());
		Assert.assertEquals(newItemGroupDetails,
			itemGroupDetailses.get(newItemGroupDetails.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = ItemGroupDetailsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ItemGroupDetails>() {
				@Override
				public void performAction(ItemGroupDetails itemGroupDetails) {
					Assert.assertNotNull(itemGroupDetails);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		ItemGroupDetails newItemGroupDetails = addItemGroupDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ItemGroupDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("itemGroupDetailsSid",
				newItemGroupDetails.getItemGroupDetailsSid()));

		List<ItemGroupDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		ItemGroupDetails existingItemGroupDetails = result.get(0);

		Assert.assertEquals(existingItemGroupDetails, newItemGroupDetails);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ItemGroupDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("itemGroupDetailsSid",
				RandomTestUtil.nextInt()));

		List<ItemGroupDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		ItemGroupDetails newItemGroupDetails = addItemGroupDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ItemGroupDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"itemGroupDetailsSid"));

		Object newItemGroupDetailsSid = newItemGroupDetails.getItemGroupDetailsSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("itemGroupDetailsSid",
				new Object[] { newItemGroupDetailsSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingItemGroupDetailsSid = result.get(0);

		Assert.assertEquals(existingItemGroupDetailsSid, newItemGroupDetailsSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ItemGroupDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"itemGroupDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("itemGroupDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected ItemGroupDetails addItemGroupDetails() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ItemGroupDetails itemGroupDetails = _persistence.create(pk);

		itemGroupDetails.setCreatedDate(RandomTestUtil.nextDate());

		itemGroupDetails.setCreatedBy(RandomTestUtil.nextInt());

		itemGroupDetails.setItemMasterSid(RandomTestUtil.nextInt());

		itemGroupDetails.setVersionNo(RandomTestUtil.nextInt());

		itemGroupDetails.setModifiedBy(RandomTestUtil.nextInt());

		itemGroupDetails.setModifiedDate(RandomTestUtil.nextDate());

		itemGroupDetails.setItemGroupSid(RandomTestUtil.nextInt());

		_itemGroupDetailses.add(_persistence.update(itemGroupDetails));

		return itemGroupDetails;
	}

	private List<ItemGroupDetails> _itemGroupDetailses = new ArrayList<ItemGroupDetails>();
	private ItemGroupDetailsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}