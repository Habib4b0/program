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

import com.stpl.app.exception.NoSuchItemGroupException;
import com.stpl.app.model.ItemGroup;
import com.stpl.app.service.ItemGroupLocalServiceUtil;
import com.stpl.app.service.persistence.ItemGroupPersistence;
import com.stpl.app.service.persistence.ItemGroupUtil;

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
public class ItemGroupPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = ItemGroupUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<ItemGroup> iterator = _itemGroups.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ItemGroup itemGroup = _persistence.create(pk);

		Assert.assertNotNull(itemGroup);

		Assert.assertEquals(itemGroup.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		ItemGroup newItemGroup = addItemGroup();

		_persistence.remove(newItemGroup);

		ItemGroup existingItemGroup = _persistence.fetchByPrimaryKey(newItemGroup.getPrimaryKey());

		Assert.assertNull(existingItemGroup);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addItemGroup();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ItemGroup newItemGroup = _persistence.create(pk);

		newItemGroup.setCreatedDate(RandomTestUtil.nextDate());

		newItemGroup.setCreatedBy(RandomTestUtil.nextInt());

		newItemGroup.setItemGroupNo(RandomTestUtil.randomString());

		newItemGroup.setVersionNo(RandomTestUtil.nextInt());

		newItemGroup.setItemFilter(RandomTestUtil.randomString());

		newItemGroup.setModifiedBy(RandomTestUtil.nextInt());

		newItemGroup.setItemGroupDescription(RandomTestUtil.randomString());

		newItemGroup.setModifiedDate(RandomTestUtil.nextDate());

		newItemGroup.setItemGroupName(RandomTestUtil.randomString());

		newItemGroup.setCompanyMasterSid(RandomTestUtil.nextInt());

		_itemGroups.add(_persistence.update(newItemGroup));

		ItemGroup existingItemGroup = _persistence.findByPrimaryKey(newItemGroup.getPrimaryKey());

		Assert.assertEquals(Time.getShortTimestamp(
				existingItemGroup.getCreatedDate()),
			Time.getShortTimestamp(newItemGroup.getCreatedDate()));
		Assert.assertEquals(existingItemGroup.getCreatedBy(),
			newItemGroup.getCreatedBy());
		Assert.assertEquals(existingItemGroup.getItemGroupNo(),
			newItemGroup.getItemGroupNo());
		Assert.assertEquals(existingItemGroup.getVersionNo(),
			newItemGroup.getVersionNo());
		Assert.assertEquals(existingItemGroup.getItemFilter(),
			newItemGroup.getItemFilter());
		Assert.assertEquals(existingItemGroup.getModifiedBy(),
			newItemGroup.getModifiedBy());
		Assert.assertEquals(existingItemGroup.getItemGroupDescription(),
			newItemGroup.getItemGroupDescription());
		Assert.assertEquals(Time.getShortTimestamp(
				existingItemGroup.getModifiedDate()),
			Time.getShortTimestamp(newItemGroup.getModifiedDate()));
		Assert.assertEquals(existingItemGroup.getItemGroupName(),
			newItemGroup.getItemGroupName());
		Assert.assertEquals(existingItemGroup.getItemGroupSid(),
			newItemGroup.getItemGroupSid());
		Assert.assertEquals(existingItemGroup.getCompanyMasterSid(),
			newItemGroup.getCompanyMasterSid());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		ItemGroup newItemGroup = addItemGroup();

		ItemGroup existingItemGroup = _persistence.findByPrimaryKey(newItemGroup.getPrimaryKey());

		Assert.assertEquals(existingItemGroup, newItemGroup);
	}

	@Test(expected = NoSuchItemGroupException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<ItemGroup> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("ITEM_GROUP", "createdDate",
			true, "createdBy", true, "itemGroupNo", true, "versionNo", true,
			"itemFilter", true, "modifiedBy", true, "itemGroupDescription",
			true, "modifiedDate", true, "itemGroupName", true, "itemGroupSid",
			true, "companyMasterSid", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		ItemGroup newItemGroup = addItemGroup();

		ItemGroup existingItemGroup = _persistence.fetchByPrimaryKey(newItemGroup.getPrimaryKey());

		Assert.assertEquals(existingItemGroup, newItemGroup);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ItemGroup missingItemGroup = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingItemGroup);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		ItemGroup newItemGroup1 = addItemGroup();
		ItemGroup newItemGroup2 = addItemGroup();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newItemGroup1.getPrimaryKey());
		primaryKeys.add(newItemGroup2.getPrimaryKey());

		Map<Serializable, ItemGroup> itemGroups = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, itemGroups.size());
		Assert.assertEquals(newItemGroup1,
			itemGroups.get(newItemGroup1.getPrimaryKey()));
		Assert.assertEquals(newItemGroup2,
			itemGroups.get(newItemGroup2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, ItemGroup> itemGroups = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(itemGroups.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		ItemGroup newItemGroup = addItemGroup();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newItemGroup.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, ItemGroup> itemGroups = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, itemGroups.size());
		Assert.assertEquals(newItemGroup,
			itemGroups.get(newItemGroup.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, ItemGroup> itemGroups = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(itemGroups.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		ItemGroup newItemGroup = addItemGroup();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newItemGroup.getPrimaryKey());

		Map<Serializable, ItemGroup> itemGroups = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, itemGroups.size());
		Assert.assertEquals(newItemGroup,
			itemGroups.get(newItemGroup.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = ItemGroupLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ItemGroup>() {
				@Override
				public void performAction(ItemGroup itemGroup) {
					Assert.assertNotNull(itemGroup);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		ItemGroup newItemGroup = addItemGroup();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ItemGroup.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("itemGroupSid",
				newItemGroup.getItemGroupSid()));

		List<ItemGroup> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		ItemGroup existingItemGroup = result.get(0);

		Assert.assertEquals(existingItemGroup, newItemGroup);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ItemGroup.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("itemGroupSid",
				RandomTestUtil.nextInt()));

		List<ItemGroup> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		ItemGroup newItemGroup = addItemGroup();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ItemGroup.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"itemGroupSid"));

		Object newItemGroupSid = newItemGroup.getItemGroupSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("itemGroupSid",
				new Object[] { newItemGroupSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingItemGroupSid = result.get(0);

		Assert.assertEquals(existingItemGroupSid, newItemGroupSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ItemGroup.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"itemGroupSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("itemGroupSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected ItemGroup addItemGroup() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ItemGroup itemGroup = _persistence.create(pk);

		itemGroup.setCreatedDate(RandomTestUtil.nextDate());

		itemGroup.setCreatedBy(RandomTestUtil.nextInt());

		itemGroup.setItemGroupNo(RandomTestUtil.randomString());

		itemGroup.setVersionNo(RandomTestUtil.nextInt());

		itemGroup.setItemFilter(RandomTestUtil.randomString());

		itemGroup.setModifiedBy(RandomTestUtil.nextInt());

		itemGroup.setItemGroupDescription(RandomTestUtil.randomString());

		itemGroup.setModifiedDate(RandomTestUtil.nextDate());

		itemGroup.setItemGroupName(RandomTestUtil.randomString());

		itemGroup.setCompanyMasterSid(RandomTestUtil.nextInt());

		_itemGroups.add(_persistence.update(itemGroup));

		return itemGroup;
	}

	private List<ItemGroup> _itemGroups = new ArrayList<ItemGroup>();
	private ItemGroupPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}