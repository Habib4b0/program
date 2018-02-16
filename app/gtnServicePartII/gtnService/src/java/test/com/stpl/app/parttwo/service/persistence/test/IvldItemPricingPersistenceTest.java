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

import com.stpl.app.parttwo.exception.NoSuchIvldItemPricingException;
import com.stpl.app.parttwo.model.IvldItemPricing;
import com.stpl.app.parttwo.service.IvldItemPricingLocalServiceUtil;
import com.stpl.app.parttwo.service.persistence.IvldItemPricingPersistence;
import com.stpl.app.parttwo.service.persistence.IvldItemPricingUtil;

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
public class IvldItemPricingPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.parttwo.service"));

	@Before
	public void setUp() {
		_persistence = IvldItemPricingUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<IvldItemPricing> iterator = _ivldItemPricings.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldItemPricing ivldItemPricing = _persistence.create(pk);

		Assert.assertNotNull(ivldItemPricing);

		Assert.assertEquals(ivldItemPricing.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		IvldItemPricing newIvldItemPricing = addIvldItemPricing();

		_persistence.remove(newIvldItemPricing);

		IvldItemPricing existingIvldItemPricing = _persistence.fetchByPrimaryKey(newIvldItemPricing.getPrimaryKey());

		Assert.assertNull(existingIvldItemPricing);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addIvldItemPricing();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldItemPricing newIvldItemPricing = _persistence.create(pk);

		newIvldItemPricing.setItemNo(RandomTestUtil.randomString());

		newIvldItemPricing.setModifiedBy(RandomTestUtil.randomString());

		newIvldItemPricing.setPricingCodeQualifierName(RandomTestUtil.randomString());

		newIvldItemPricing.setCreatedDate(RandomTestUtil.nextDate());

		newIvldItemPricing.setEndDate(RandomTestUtil.randomString());

		newIvldItemPricing.setBatchId(RandomTestUtil.randomString());

		newIvldItemPricing.setItemName(RandomTestUtil.randomString());

		newIvldItemPricing.setErrorCode(RandomTestUtil.randomString());

		newIvldItemPricing.setReprocessedFlag(RandomTestUtil.randomString());

		newIvldItemPricing.setItemPricingIntfid(RandomTestUtil.randomString());

		newIvldItemPricing.setPricingCodeStatus(RandomTestUtil.randomString());

		newIvldItemPricing.setCreatedBy(RandomTestUtil.randomString());

		newIvldItemPricing.setItemId(RandomTestUtil.randomString());

		newIvldItemPricing.setErrorField(RandomTestUtil.randomString());

		newIvldItemPricing.setStartDate(RandomTestUtil.randomString());

		newIvldItemPricing.setItemUom(RandomTestUtil.randomString());

		newIvldItemPricing.setModifiedDate(RandomTestUtil.nextDate());

		newIvldItemPricing.setReasonForFailure(RandomTestUtil.randomString());

		newIvldItemPricing.setSource(RandomTestUtil.randomString());

		newIvldItemPricing.setPricingCodeQualifier(RandomTestUtil.randomString());

		newIvldItemPricing.setAddChgDelIndicator(RandomTestUtil.randomString());

		newIvldItemPricing.setEntityCode(RandomTestUtil.randomString());

		newIvldItemPricing.setItemPrice(RandomTestUtil.randomString());

		newIvldItemPricing.setIntfInsertedDate(RandomTestUtil.nextDate());

		newIvldItemPricing.setCheckRecord(RandomTestUtil.randomBoolean());

		newIvldItemPricing.setItemPriceprecision(RandomTestUtil.randomString());

		_ivldItemPricings.add(_persistence.update(newIvldItemPricing));

		IvldItemPricing existingIvldItemPricing = _persistence.findByPrimaryKey(newIvldItemPricing.getPrimaryKey());

		Assert.assertEquals(existingIvldItemPricing.getItemNo(),
			newIvldItemPricing.getItemNo());
		Assert.assertEquals(existingIvldItemPricing.getModifiedBy(),
			newIvldItemPricing.getModifiedBy());
		Assert.assertEquals(existingIvldItemPricing.getPricingCodeQualifierName(),
			newIvldItemPricing.getPricingCodeQualifierName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldItemPricing.getCreatedDate()),
			Time.getShortTimestamp(newIvldItemPricing.getCreatedDate()));
		Assert.assertEquals(existingIvldItemPricing.getEndDate(),
			newIvldItemPricing.getEndDate());
		Assert.assertEquals(existingIvldItemPricing.getBatchId(),
			newIvldItemPricing.getBatchId());
		Assert.assertEquals(existingIvldItemPricing.getItemName(),
			newIvldItemPricing.getItemName());
		Assert.assertEquals(existingIvldItemPricing.getErrorCode(),
			newIvldItemPricing.getErrorCode());
		Assert.assertEquals(existingIvldItemPricing.getReprocessedFlag(),
			newIvldItemPricing.getReprocessedFlag());
		Assert.assertEquals(existingIvldItemPricing.getItemPricingIntfid(),
			newIvldItemPricing.getItemPricingIntfid());
		Assert.assertEquals(existingIvldItemPricing.getIvldItemPricingSid(),
			newIvldItemPricing.getIvldItemPricingSid());
		Assert.assertEquals(existingIvldItemPricing.getPricingCodeStatus(),
			newIvldItemPricing.getPricingCodeStatus());
		Assert.assertEquals(existingIvldItemPricing.getCreatedBy(),
			newIvldItemPricing.getCreatedBy());
		Assert.assertEquals(existingIvldItemPricing.getItemId(),
			newIvldItemPricing.getItemId());
		Assert.assertEquals(existingIvldItemPricing.getErrorField(),
			newIvldItemPricing.getErrorField());
		Assert.assertEquals(existingIvldItemPricing.getStartDate(),
			newIvldItemPricing.getStartDate());
		Assert.assertEquals(existingIvldItemPricing.getItemUom(),
			newIvldItemPricing.getItemUom());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldItemPricing.getModifiedDate()),
			Time.getShortTimestamp(newIvldItemPricing.getModifiedDate()));
		Assert.assertEquals(existingIvldItemPricing.getReasonForFailure(),
			newIvldItemPricing.getReasonForFailure());
		Assert.assertEquals(existingIvldItemPricing.getSource(),
			newIvldItemPricing.getSource());
		Assert.assertEquals(existingIvldItemPricing.getPricingCodeQualifier(),
			newIvldItemPricing.getPricingCodeQualifier());
		Assert.assertEquals(existingIvldItemPricing.getAddChgDelIndicator(),
			newIvldItemPricing.getAddChgDelIndicator());
		Assert.assertEquals(existingIvldItemPricing.getEntityCode(),
			newIvldItemPricing.getEntityCode());
		Assert.assertEquals(existingIvldItemPricing.getItemPrice(),
			newIvldItemPricing.getItemPrice());
		Assert.assertEquals(Time.getShortTimestamp(
				existingIvldItemPricing.getIntfInsertedDate()),
			Time.getShortTimestamp(newIvldItemPricing.getIntfInsertedDate()));
		Assert.assertEquals(existingIvldItemPricing.getCheckRecord(),
			newIvldItemPricing.getCheckRecord());
		Assert.assertEquals(existingIvldItemPricing.getItemPriceprecision(),
			newIvldItemPricing.getItemPriceprecision());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		IvldItemPricing newIvldItemPricing = addIvldItemPricing();

		IvldItemPricing existingIvldItemPricing = _persistence.findByPrimaryKey(newIvldItemPricing.getPrimaryKey());

		Assert.assertEquals(existingIvldItemPricing, newIvldItemPricing);
	}

	@Test(expected = NoSuchIvldItemPricingException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<IvldItemPricing> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("IVLD_ITEM_PRICING",
			"itemNo", true, "modifiedBy", true, "pricingCodeQualifierName",
			true, "createdDate", true, "endDate", true, "batchId", true,
			"itemName", true, "errorCode", true, "reprocessedFlag", true,
			"itemPricingIntfid", true, "ivldItemPricingSid", true,
			"pricingCodeStatus", true, "createdBy", true, "itemId", true,
			"errorField", true, "startDate", true, "itemUom", true,
			"modifiedDate", true, "reasonForFailure", true, "source", true,
			"pricingCodeQualifier", true, "addChgDelIndicator", true,
			"entityCode", true, "itemPrice", true, "intfInsertedDate", true,
			"checkRecord", true, "itemPriceprecision", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		IvldItemPricing newIvldItemPricing = addIvldItemPricing();

		IvldItemPricing existingIvldItemPricing = _persistence.fetchByPrimaryKey(newIvldItemPricing.getPrimaryKey());

		Assert.assertEquals(existingIvldItemPricing, newIvldItemPricing);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldItemPricing missingIvldItemPricing = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingIvldItemPricing);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		IvldItemPricing newIvldItemPricing1 = addIvldItemPricing();
		IvldItemPricing newIvldItemPricing2 = addIvldItemPricing();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldItemPricing1.getPrimaryKey());
		primaryKeys.add(newIvldItemPricing2.getPrimaryKey());

		Map<Serializable, IvldItemPricing> ivldItemPricings = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, ivldItemPricings.size());
		Assert.assertEquals(newIvldItemPricing1,
			ivldItemPricings.get(newIvldItemPricing1.getPrimaryKey()));
		Assert.assertEquals(newIvldItemPricing2,
			ivldItemPricings.get(newIvldItemPricing2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, IvldItemPricing> ivldItemPricings = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldItemPricings.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		IvldItemPricing newIvldItemPricing = addIvldItemPricing();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldItemPricing.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, IvldItemPricing> ivldItemPricings = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldItemPricings.size());
		Assert.assertEquals(newIvldItemPricing,
			ivldItemPricings.get(newIvldItemPricing.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, IvldItemPricing> ivldItemPricings = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(ivldItemPricings.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		IvldItemPricing newIvldItemPricing = addIvldItemPricing();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newIvldItemPricing.getPrimaryKey());

		Map<Serializable, IvldItemPricing> ivldItemPricings = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, ivldItemPricings.size());
		Assert.assertEquals(newIvldItemPricing,
			ivldItemPricings.get(newIvldItemPricing.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = IvldItemPricingLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<IvldItemPricing>() {
				@Override
				public void performAction(IvldItemPricing ivldItemPricing) {
					Assert.assertNotNull(ivldItemPricing);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		IvldItemPricing newIvldItemPricing = addIvldItemPricing();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldItemPricing.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ivldItemPricingSid",
				newIvldItemPricing.getIvldItemPricingSid()));

		List<IvldItemPricing> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		IvldItemPricing existingIvldItemPricing = result.get(0);

		Assert.assertEquals(existingIvldItemPricing, newIvldItemPricing);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldItemPricing.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("ivldItemPricingSid",
				RandomTestUtil.nextInt()));

		List<IvldItemPricing> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		IvldItemPricing newIvldItemPricing = addIvldItemPricing();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldItemPricing.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldItemPricingSid"));

		Object newIvldItemPricingSid = newIvldItemPricing.getIvldItemPricingSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("ivldItemPricingSid",
				new Object[] { newIvldItemPricingSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingIvldItemPricingSid = result.get(0);

		Assert.assertEquals(existingIvldItemPricingSid, newIvldItemPricingSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(IvldItemPricing.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"ivldItemPricingSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("ivldItemPricingSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected IvldItemPricing addIvldItemPricing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		IvldItemPricing ivldItemPricing = _persistence.create(pk);

		ivldItemPricing.setItemNo(RandomTestUtil.randomString());

		ivldItemPricing.setModifiedBy(RandomTestUtil.randomString());

		ivldItemPricing.setPricingCodeQualifierName(RandomTestUtil.randomString());

		ivldItemPricing.setCreatedDate(RandomTestUtil.nextDate());

		ivldItemPricing.setEndDate(RandomTestUtil.randomString());

		ivldItemPricing.setBatchId(RandomTestUtil.randomString());

		ivldItemPricing.setItemName(RandomTestUtil.randomString());

		ivldItemPricing.setErrorCode(RandomTestUtil.randomString());

		ivldItemPricing.setReprocessedFlag(RandomTestUtil.randomString());

		ivldItemPricing.setItemPricingIntfid(RandomTestUtil.randomString());

		ivldItemPricing.setPricingCodeStatus(RandomTestUtil.randomString());

		ivldItemPricing.setCreatedBy(RandomTestUtil.randomString());

		ivldItemPricing.setItemId(RandomTestUtil.randomString());

		ivldItemPricing.setErrorField(RandomTestUtil.randomString());

		ivldItemPricing.setStartDate(RandomTestUtil.randomString());

		ivldItemPricing.setItemUom(RandomTestUtil.randomString());

		ivldItemPricing.setModifiedDate(RandomTestUtil.nextDate());

		ivldItemPricing.setReasonForFailure(RandomTestUtil.randomString());

		ivldItemPricing.setSource(RandomTestUtil.randomString());

		ivldItemPricing.setPricingCodeQualifier(RandomTestUtil.randomString());

		ivldItemPricing.setAddChgDelIndicator(RandomTestUtil.randomString());

		ivldItemPricing.setEntityCode(RandomTestUtil.randomString());

		ivldItemPricing.setItemPrice(RandomTestUtil.randomString());

		ivldItemPricing.setIntfInsertedDate(RandomTestUtil.nextDate());

		ivldItemPricing.setCheckRecord(RandomTestUtil.randomBoolean());

		ivldItemPricing.setItemPriceprecision(RandomTestUtil.randomString());

		_ivldItemPricings.add(_persistence.update(ivldItemPricing));

		return ivldItemPricing;
	}

	private List<IvldItemPricing> _ivldItemPricings = new ArrayList<IvldItemPricing>();
	private IvldItemPricingPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}