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

package com.stpl.app.parttwo.service.persistence.test;

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

import com.stpl.app.parttwo.exception.NoSuchVwItemPricingException;
import com.stpl.app.parttwo.model.VwItemPricing;
import com.stpl.app.parttwo.service.VwItemPricingLocalServiceUtil;
import com.stpl.app.parttwo.service.persistence.VwItemPricingPersistence;
import com.stpl.app.parttwo.service.persistence.VwItemPricingUtil;

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
public class VwItemPricingPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.parttwo.service"));

	@Before
	public void setUp() {
		_persistence = VwItemPricingUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<VwItemPricing> iterator = _vwItemPricings.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwItemPricing vwItemPricing = _persistence.create(pk);

		Assert.assertNotNull(vwItemPricing);

		Assert.assertEquals(vwItemPricing.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		VwItemPricing newVwItemPricing = addVwItemPricing();

		_persistence.remove(newVwItemPricing);

		VwItemPricing existingVwItemPricing = _persistence.fetchByPrimaryKey(newVwItemPricing.getPrimaryKey());

		Assert.assertNull(existingVwItemPricing);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addVwItemPricing();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwItemPricing newVwItemPricing = _persistence.create(pk);

		newVwItemPricing.setPricingCodeQualifier(RandomTestUtil.randomString());

		newVwItemPricing.setItemPrice(RandomTestUtil.randomString());

		newVwItemPricing.setEndDate(RandomTestUtil.nextDate());

		newVwItemPricing.setItemId(RandomTestUtil.randomString());

		newVwItemPricing.setModifiedDate(RandomTestUtil.nextDate());

		newVwItemPricing.setEntityCode(RandomTestUtil.randomString());

		newVwItemPricing.setStartDate(RandomTestUtil.nextDate());

		newVwItemPricing.setCreatedDate(RandomTestUtil.nextDate());

		newVwItemPricing.setCreatedBy(RandomTestUtil.randomString());

		newVwItemPricing.setSource(RandomTestUtil.randomString());

		newVwItemPricing.setBatchId(RandomTestUtil.randomString());

		newVwItemPricing.setAddChgDelIndicator(RandomTestUtil.randomString());

		newVwItemPricing.setItemName(RandomTestUtil.randomString());

		newVwItemPricing.setItemUom(RandomTestUtil.randomString());

		newVwItemPricing.setModifiedBy(RandomTestUtil.randomString());

		newVwItemPricing.setItemNo(RandomTestUtil.randomString());

		newVwItemPricing.setPricingCodeStatus(RandomTestUtil.randomString());

		newVwItemPricing.setPricingCodeQualifierName(RandomTestUtil.randomString());

		newVwItemPricing.setItemPriceprecision(RandomTestUtil.nextInt());

		_vwItemPricings.add(_persistence.update(newVwItemPricing));

		VwItemPricing existingVwItemPricing = _persistence.findByPrimaryKey(newVwItemPricing.getPrimaryKey());

		Assert.assertEquals(existingVwItemPricing.getPricingCodeQualifier(),
			newVwItemPricing.getPricingCodeQualifier());
		Assert.assertEquals(existingVwItemPricing.getItemPrice(),
			newVwItemPricing.getItemPrice());
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwItemPricing.getEndDate()),
			Time.getShortTimestamp(newVwItemPricing.getEndDate()));
		Assert.assertEquals(existingVwItemPricing.getItemId(),
			newVwItemPricing.getItemId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwItemPricing.getModifiedDate()),
			Time.getShortTimestamp(newVwItemPricing.getModifiedDate()));
		Assert.assertEquals(existingVwItemPricing.getEntityCode(),
			newVwItemPricing.getEntityCode());
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwItemPricing.getStartDate()),
			Time.getShortTimestamp(newVwItemPricing.getStartDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwItemPricing.getCreatedDate()),
			Time.getShortTimestamp(newVwItemPricing.getCreatedDate()));
		Assert.assertEquals(existingVwItemPricing.getCreatedBy(),
			newVwItemPricing.getCreatedBy());
		Assert.assertEquals(existingVwItemPricing.getSource(),
			newVwItemPricing.getSource());
		Assert.assertEquals(existingVwItemPricing.getBatchId(),
			newVwItemPricing.getBatchId());
		Assert.assertEquals(existingVwItemPricing.getAddChgDelIndicator(),
			newVwItemPricing.getAddChgDelIndicator());
		Assert.assertEquals(existingVwItemPricing.getItemName(),
			newVwItemPricing.getItemName());
		Assert.assertEquals(existingVwItemPricing.getItemUom(),
			newVwItemPricing.getItemUom());
		Assert.assertEquals(existingVwItemPricing.getModifiedBy(),
			newVwItemPricing.getModifiedBy());
		Assert.assertEquals(existingVwItemPricing.getItemNo(),
			newVwItemPricing.getItemNo());
		Assert.assertEquals(existingVwItemPricing.getItemPricingSid(),
			newVwItemPricing.getItemPricingSid());
		Assert.assertEquals(existingVwItemPricing.getPricingCodeStatus(),
			newVwItemPricing.getPricingCodeStatus());
		Assert.assertEquals(existingVwItemPricing.getPricingCodeQualifierName(),
			newVwItemPricing.getPricingCodeQualifierName());
		Assert.assertEquals(existingVwItemPricing.getItemPriceprecision(),
			newVwItemPricing.getItemPriceprecision());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		VwItemPricing newVwItemPricing = addVwItemPricing();

		VwItemPricing existingVwItemPricing = _persistence.findByPrimaryKey(newVwItemPricing.getPrimaryKey());

		Assert.assertEquals(existingVwItemPricing, newVwItemPricing);
	}

	@Test(expected = NoSuchVwItemPricingException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<VwItemPricing> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("VW_ITEM_PRICING",
			"pricingCodeQualifier", true, "itemPrice", true, "endDate", true,
			"itemId", true, "modifiedDate", true, "entityCode", true,
			"startDate", true, "createdDate", true, "createdBy", true,
			"source", true, "batchId", true, "addChgDelIndicator", true,
			"itemName", true, "itemUom", true, "modifiedBy", true, "itemNo",
			true, "itemPricingSid", true, "pricingCodeStatus", true,
			"pricingCodeQualifierName", true, "itemPriceprecision", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		VwItemPricing newVwItemPricing = addVwItemPricing();

		VwItemPricing existingVwItemPricing = _persistence.fetchByPrimaryKey(newVwItemPricing.getPrimaryKey());

		Assert.assertEquals(existingVwItemPricing, newVwItemPricing);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwItemPricing missingVwItemPricing = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingVwItemPricing);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		VwItemPricing newVwItemPricing1 = addVwItemPricing();
		VwItemPricing newVwItemPricing2 = addVwItemPricing();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newVwItemPricing1.getPrimaryKey());
		primaryKeys.add(newVwItemPricing2.getPrimaryKey());

		Map<Serializable, VwItemPricing> vwItemPricings = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, vwItemPricings.size());
		Assert.assertEquals(newVwItemPricing1,
			vwItemPricings.get(newVwItemPricing1.getPrimaryKey()));
		Assert.assertEquals(newVwItemPricing2,
			vwItemPricings.get(newVwItemPricing2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, VwItemPricing> vwItemPricings = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(vwItemPricings.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		VwItemPricing newVwItemPricing = addVwItemPricing();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newVwItemPricing.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, VwItemPricing> vwItemPricings = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, vwItemPricings.size());
		Assert.assertEquals(newVwItemPricing,
			vwItemPricings.get(newVwItemPricing.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, VwItemPricing> vwItemPricings = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(vwItemPricings.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		VwItemPricing newVwItemPricing = addVwItemPricing();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newVwItemPricing.getPrimaryKey());

		Map<Serializable, VwItemPricing> vwItemPricings = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, vwItemPricings.size());
		Assert.assertEquals(newVwItemPricing,
			vwItemPricings.get(newVwItemPricing.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = VwItemPricingLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<VwItemPricing>() {
				@Override
				public void performAction(VwItemPricing vwItemPricing) {
					Assert.assertNotNull(vwItemPricing);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		VwItemPricing newVwItemPricing = addVwItemPricing();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwItemPricing.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("itemPricingSid",
				newVwItemPricing.getItemPricingSid()));

		List<VwItemPricing> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		VwItemPricing existingVwItemPricing = result.get(0);

		Assert.assertEquals(existingVwItemPricing, newVwItemPricing);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwItemPricing.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("itemPricingSid",
				RandomTestUtil.nextInt()));

		List<VwItemPricing> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		VwItemPricing newVwItemPricing = addVwItemPricing();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwItemPricing.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"itemPricingSid"));

		Object newItemPricingSid = newVwItemPricing.getItemPricingSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("itemPricingSid",
				new Object[] { newItemPricingSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingItemPricingSid = result.get(0);

		Assert.assertEquals(existingItemPricingSid, newItemPricingSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwItemPricing.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"itemPricingSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("itemPricingSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected VwItemPricing addVwItemPricing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwItemPricing vwItemPricing = _persistence.create(pk);

		vwItemPricing.setPricingCodeQualifier(RandomTestUtil.randomString());

		vwItemPricing.setItemPrice(RandomTestUtil.randomString());

		vwItemPricing.setEndDate(RandomTestUtil.nextDate());

		vwItemPricing.setItemId(RandomTestUtil.randomString());

		vwItemPricing.setModifiedDate(RandomTestUtil.nextDate());

		vwItemPricing.setEntityCode(RandomTestUtil.randomString());

		vwItemPricing.setStartDate(RandomTestUtil.nextDate());

		vwItemPricing.setCreatedDate(RandomTestUtil.nextDate());

		vwItemPricing.setCreatedBy(RandomTestUtil.randomString());

		vwItemPricing.setSource(RandomTestUtil.randomString());

		vwItemPricing.setBatchId(RandomTestUtil.randomString());

		vwItemPricing.setAddChgDelIndicator(RandomTestUtil.randomString());

		vwItemPricing.setItemName(RandomTestUtil.randomString());

		vwItemPricing.setItemUom(RandomTestUtil.randomString());

		vwItemPricing.setModifiedBy(RandomTestUtil.randomString());

		vwItemPricing.setItemNo(RandomTestUtil.randomString());

		vwItemPricing.setPricingCodeStatus(RandomTestUtil.randomString());

		vwItemPricing.setPricingCodeQualifierName(RandomTestUtil.randomString());

		vwItemPricing.setItemPriceprecision(RandomTestUtil.nextInt());

		_vwItemPricings.add(_persistence.update(vwItemPricing));

		return vwItemPricing;
	}

	private List<VwItemPricing> _vwItemPricings = new ArrayList<VwItemPricing>();
	private VwItemPricingPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}