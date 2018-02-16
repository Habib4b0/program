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

import com.stpl.app.exception.NoSuchBrandMasterException;
import com.stpl.app.model.BrandMaster;
import com.stpl.app.service.BrandMasterLocalServiceUtil;
import com.stpl.app.service.persistence.BrandMasterPersistence;
import com.stpl.app.service.persistence.BrandMasterUtil;

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
public class BrandMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = BrandMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<BrandMaster> iterator = _brandMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		BrandMaster brandMaster = _persistence.create(pk);

		Assert.assertNotNull(brandMaster);

		Assert.assertEquals(brandMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		BrandMaster newBrandMaster = addBrandMaster();

		_persistence.remove(newBrandMaster);

		BrandMaster existingBrandMaster = _persistence.fetchByPrimaryKey(newBrandMaster.getPrimaryKey());

		Assert.assertNull(existingBrandMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addBrandMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		BrandMaster newBrandMaster = _persistence.create(pk);

		newBrandMaster.setCreatedBy(RandomTestUtil.nextInt());

		newBrandMaster.setModifiedBy(RandomTestUtil.nextInt());

		newBrandMaster.setCreatedDate(RandomTestUtil.nextDate());

		newBrandMaster.setBatchId(RandomTestUtil.randomString());

		newBrandMaster.setModifiedDate(RandomTestUtil.nextDate());

		newBrandMaster.setBrandId(RandomTestUtil.randomString());

		newBrandMaster.setDisplayBrand(RandomTestUtil.randomString());

		newBrandMaster.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newBrandMaster.setBrandName(RandomTestUtil.randomString());

		newBrandMaster.setBrandDesc(RandomTestUtil.randomString());

		newBrandMaster.setSource(RandomTestUtil.randomString());

		newBrandMaster.setInboundStatus(RandomTestUtil.randomString());

		_brandMasters.add(_persistence.update(newBrandMaster));

		BrandMaster existingBrandMaster = _persistence.findByPrimaryKey(newBrandMaster.getPrimaryKey());

		Assert.assertEquals(existingBrandMaster.getCreatedBy(),
			newBrandMaster.getCreatedBy());
		Assert.assertEquals(existingBrandMaster.getModifiedBy(),
			newBrandMaster.getModifiedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingBrandMaster.getCreatedDate()),
			Time.getShortTimestamp(newBrandMaster.getCreatedDate()));
		Assert.assertEquals(existingBrandMaster.getBrandMasterSid(),
			newBrandMaster.getBrandMasterSid());
		Assert.assertEquals(existingBrandMaster.getBatchId(),
			newBrandMaster.getBatchId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingBrandMaster.getModifiedDate()),
			Time.getShortTimestamp(newBrandMaster.getModifiedDate()));
		Assert.assertEquals(existingBrandMaster.getBrandId(),
			newBrandMaster.getBrandId());
		Assert.assertEquals(existingBrandMaster.getDisplayBrand(),
			newBrandMaster.getDisplayBrand());
		Assert.assertEquals(existingBrandMaster.getRecordLockStatus(),
			newBrandMaster.getRecordLockStatus());
		Assert.assertEquals(existingBrandMaster.getBrandName(),
			newBrandMaster.getBrandName());
		Assert.assertEquals(existingBrandMaster.getBrandDesc(),
			newBrandMaster.getBrandDesc());
		Assert.assertEquals(existingBrandMaster.getSource(),
			newBrandMaster.getSource());
		Assert.assertEquals(existingBrandMaster.getInboundStatus(),
			newBrandMaster.getInboundStatus());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		BrandMaster newBrandMaster = addBrandMaster();

		BrandMaster existingBrandMaster = _persistence.findByPrimaryKey(newBrandMaster.getPrimaryKey());

		Assert.assertEquals(existingBrandMaster, newBrandMaster);
	}

	@Test(expected = NoSuchBrandMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<BrandMaster> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("BRAND_MASTER", "createdBy",
			true, "modifiedBy", true, "createdDate", true, "brandMasterSid",
			true, "batchId", true, "modifiedDate", true, "brandId", true,
			"displayBrand", true, "recordLockStatus", true, "brandName", true,
			"brandDesc", true, "source", true, "inboundStatus", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		BrandMaster newBrandMaster = addBrandMaster();

		BrandMaster existingBrandMaster = _persistence.fetchByPrimaryKey(newBrandMaster.getPrimaryKey());

		Assert.assertEquals(existingBrandMaster, newBrandMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		BrandMaster missingBrandMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingBrandMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		BrandMaster newBrandMaster1 = addBrandMaster();
		BrandMaster newBrandMaster2 = addBrandMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newBrandMaster1.getPrimaryKey());
		primaryKeys.add(newBrandMaster2.getPrimaryKey());

		Map<Serializable, BrandMaster> brandMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, brandMasters.size());
		Assert.assertEquals(newBrandMaster1,
			brandMasters.get(newBrandMaster1.getPrimaryKey()));
		Assert.assertEquals(newBrandMaster2,
			brandMasters.get(newBrandMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, BrandMaster> brandMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(brandMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		BrandMaster newBrandMaster = addBrandMaster();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newBrandMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, BrandMaster> brandMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, brandMasters.size());
		Assert.assertEquals(newBrandMaster,
			brandMasters.get(newBrandMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, BrandMaster> brandMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(brandMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		BrandMaster newBrandMaster = addBrandMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newBrandMaster.getPrimaryKey());

		Map<Serializable, BrandMaster> brandMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, brandMasters.size());
		Assert.assertEquals(newBrandMaster,
			brandMasters.get(newBrandMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = BrandMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<BrandMaster>() {
				@Override
				public void performAction(BrandMaster brandMaster) {
					Assert.assertNotNull(brandMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		BrandMaster newBrandMaster = addBrandMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(BrandMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("brandMasterSid",
				newBrandMaster.getBrandMasterSid()));

		List<BrandMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		BrandMaster existingBrandMaster = result.get(0);

		Assert.assertEquals(existingBrandMaster, newBrandMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(BrandMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("brandMasterSid",
				RandomTestUtil.nextInt()));

		List<BrandMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		BrandMaster newBrandMaster = addBrandMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(BrandMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"brandMasterSid"));

		Object newBrandMasterSid = newBrandMaster.getBrandMasterSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("brandMasterSid",
				new Object[] { newBrandMasterSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingBrandMasterSid = result.get(0);

		Assert.assertEquals(existingBrandMasterSid, newBrandMasterSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(BrandMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"brandMasterSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("brandMasterSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected BrandMaster addBrandMaster() throws Exception {
		int pk = RandomTestUtil.nextInt();

		BrandMaster brandMaster = _persistence.create(pk);

		brandMaster.setCreatedBy(RandomTestUtil.nextInt());

		brandMaster.setModifiedBy(RandomTestUtil.nextInt());

		brandMaster.setCreatedDate(RandomTestUtil.nextDate());

		brandMaster.setBatchId(RandomTestUtil.randomString());

		brandMaster.setModifiedDate(RandomTestUtil.nextDate());

		brandMaster.setBrandId(RandomTestUtil.randomString());

		brandMaster.setDisplayBrand(RandomTestUtil.randomString());

		brandMaster.setRecordLockStatus(RandomTestUtil.randomBoolean());

		brandMaster.setBrandName(RandomTestUtil.randomString());

		brandMaster.setBrandDesc(RandomTestUtil.randomString());

		brandMaster.setSource(RandomTestUtil.randomString());

		brandMaster.setInboundStatus(RandomTestUtil.randomString());

		_brandMasters.add(_persistence.update(brandMaster));

		return brandMaster;
	}

	private List<BrandMaster> _brandMasters = new ArrayList<BrandMaster>();
	private BrandMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}