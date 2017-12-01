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
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.util.IntegerWrapper;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.exception.NoSuchChDiscountProjMasterException;
import com.stpl.app.model.ChDiscountProjMaster;
import com.stpl.app.service.ChDiscountProjMasterLocalServiceUtil;
import com.stpl.app.service.persistence.ChDiscountProjMasterPK;
import com.stpl.app.service.persistence.ChDiscountProjMasterPersistence;
import com.stpl.app.service.persistence.ChDiscountProjMasterUtil;

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
public class ChDiscountProjMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = ChDiscountProjMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<ChDiscountProjMaster> iterator = _chDiscountProjMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		ChDiscountProjMasterPK pk = new ChDiscountProjMasterPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		ChDiscountProjMaster chDiscountProjMaster = _persistence.create(pk);

		Assert.assertNotNull(chDiscountProjMaster);

		Assert.assertEquals(chDiscountProjMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		ChDiscountProjMaster newChDiscountProjMaster = addChDiscountProjMaster();

		_persistence.remove(newChDiscountProjMaster);

		ChDiscountProjMaster existingChDiscountProjMaster = _persistence.fetchByPrimaryKey(newChDiscountProjMaster.getPrimaryKey());

		Assert.assertNull(existingChDiscountProjMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addChDiscountProjMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		ChDiscountProjMasterPK pk = new ChDiscountProjMasterPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		ChDiscountProjMaster newChDiscountProjMaster = _persistence.create(pk);

		newChDiscountProjMaster.setSelectedPeriods(RandomTestUtil.randomString());

		newChDiscountProjMaster.setCheckRecord(RandomTestUtil.randomBoolean());

		newChDiscountProjMaster.setPriceGroupType(RandomTestUtil.randomString());

		newChDiscountProjMaster.setBaselinePeriods(RandomTestUtil.randomString());

		newChDiscountProjMaster.setNetFlag(RandomTestUtil.randomString());

		newChDiscountProjMaster.setMethodology(RandomTestUtil.randomString());

		newChDiscountProjMaster.setDiscountType(RandomTestUtil.randomString());

		newChDiscountProjMaster.setProjectedType(RandomTestUtil.randomString());

		_chDiscountProjMasters.add(_persistence.update(newChDiscountProjMaster));

		ChDiscountProjMaster existingChDiscountProjMaster = _persistence.findByPrimaryKey(newChDiscountProjMaster.getPrimaryKey());

		Assert.assertEquals(existingChDiscountProjMaster.getSelectedPeriods(),
			newChDiscountProjMaster.getSelectedPeriods());
		Assert.assertEquals(existingChDiscountProjMaster.getCheckRecord(),
			newChDiscountProjMaster.getCheckRecord());
		Assert.assertEquals(existingChDiscountProjMaster.getPriceGroupType(),
			newChDiscountProjMaster.getPriceGroupType());
		Assert.assertEquals(existingChDiscountProjMaster.getProjectionDetailsSid(),
			newChDiscountProjMaster.getProjectionDetailsSid());
		Assert.assertEquals(existingChDiscountProjMaster.getBaselinePeriods(),
			newChDiscountProjMaster.getBaselinePeriods());
		Assert.assertEquals(existingChDiscountProjMaster.getNetFlag(),
			newChDiscountProjMaster.getNetFlag());
		Assert.assertEquals(existingChDiscountProjMaster.getMethodology(),
			newChDiscountProjMaster.getMethodology());
		Assert.assertEquals(existingChDiscountProjMaster.getRsModelSid(),
			newChDiscountProjMaster.getRsModelSid());
		Assert.assertEquals(existingChDiscountProjMaster.getDiscountType(),
			newChDiscountProjMaster.getDiscountType());
		Assert.assertEquals(existingChDiscountProjMaster.getProjectedType(),
			newChDiscountProjMaster.getProjectedType());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		ChDiscountProjMaster newChDiscountProjMaster = addChDiscountProjMaster();

		ChDiscountProjMaster existingChDiscountProjMaster = _persistence.findByPrimaryKey(newChDiscountProjMaster.getPrimaryKey());

		Assert.assertEquals(existingChDiscountProjMaster,
			newChDiscountProjMaster);
	}

	@Test(expected = NoSuchChDiscountProjMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		ChDiscountProjMasterPK pk = new ChDiscountProjMasterPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		ChDiscountProjMaster newChDiscountProjMaster = addChDiscountProjMaster();

		ChDiscountProjMaster existingChDiscountProjMaster = _persistence.fetchByPrimaryKey(newChDiscountProjMaster.getPrimaryKey());

		Assert.assertEquals(existingChDiscountProjMaster,
			newChDiscountProjMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		ChDiscountProjMasterPK pk = new ChDiscountProjMasterPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		ChDiscountProjMaster missingChDiscountProjMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingChDiscountProjMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		ChDiscountProjMaster newChDiscountProjMaster1 = addChDiscountProjMaster();
		ChDiscountProjMaster newChDiscountProjMaster2 = addChDiscountProjMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newChDiscountProjMaster1.getPrimaryKey());
		primaryKeys.add(newChDiscountProjMaster2.getPrimaryKey());

		Map<Serializable, ChDiscountProjMaster> chDiscountProjMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, chDiscountProjMasters.size());
		Assert.assertEquals(newChDiscountProjMaster1,
			chDiscountProjMasters.get(newChDiscountProjMaster1.getPrimaryKey()));
		Assert.assertEquals(newChDiscountProjMaster2,
			chDiscountProjMasters.get(newChDiscountProjMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		ChDiscountProjMasterPK pk1 = new ChDiscountProjMasterPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		ChDiscountProjMasterPK pk2 = new ChDiscountProjMasterPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, ChDiscountProjMaster> chDiscountProjMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(chDiscountProjMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		ChDiscountProjMaster newChDiscountProjMaster = addChDiscountProjMaster();

		ChDiscountProjMasterPK pk = new ChDiscountProjMasterPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newChDiscountProjMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, ChDiscountProjMaster> chDiscountProjMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, chDiscountProjMasters.size());
		Assert.assertEquals(newChDiscountProjMaster,
			chDiscountProjMasters.get(newChDiscountProjMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, ChDiscountProjMaster> chDiscountProjMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(chDiscountProjMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		ChDiscountProjMaster newChDiscountProjMaster = addChDiscountProjMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newChDiscountProjMaster.getPrimaryKey());

		Map<Serializable, ChDiscountProjMaster> chDiscountProjMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, chDiscountProjMasters.size());
		Assert.assertEquals(newChDiscountProjMaster,
			chDiscountProjMasters.get(newChDiscountProjMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = ChDiscountProjMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ChDiscountProjMaster>() {
				@Override
				public void performAction(
					ChDiscountProjMaster chDiscountProjMaster) {
					Assert.assertNotNull(chDiscountProjMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		ChDiscountProjMaster newChDiscountProjMaster = addChDiscountProjMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ChDiscountProjMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionDetailsSid",
				newChDiscountProjMaster.getProjectionDetailsSid()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.rsModelSid",
				newChDiscountProjMaster.getRsModelSid()));

		List<ChDiscountProjMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		ChDiscountProjMaster existingChDiscountProjMaster = result.get(0);

		Assert.assertEquals(existingChDiscountProjMaster,
			newChDiscountProjMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ChDiscountProjMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.projectionDetailsSid",
				RandomTestUtil.nextInt()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.rsModelSid",
				RandomTestUtil.nextInt()));

		List<ChDiscountProjMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		ChDiscountProjMaster newChDiscountProjMaster = addChDiscountProjMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ChDiscountProjMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.projectionDetailsSid"));

		Object newProjectionDetailsSid = newChDiscountProjMaster.getProjectionDetailsSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.projectionDetailsSid",
				new Object[] { newProjectionDetailsSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingProjectionDetailsSid = result.get(0);

		Assert.assertEquals(existingProjectionDetailsSid,
			newProjectionDetailsSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ChDiscountProjMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.projectionDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.projectionDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected ChDiscountProjMaster addChDiscountProjMaster()
		throws Exception {
		ChDiscountProjMasterPK pk = new ChDiscountProjMasterPK(RandomTestUtil.nextInt(),
				RandomTestUtil.nextInt());

		ChDiscountProjMaster chDiscountProjMaster = _persistence.create(pk);

		chDiscountProjMaster.setSelectedPeriods(RandomTestUtil.randomString());

		chDiscountProjMaster.setCheckRecord(RandomTestUtil.randomBoolean());

		chDiscountProjMaster.setPriceGroupType(RandomTestUtil.randomString());

		chDiscountProjMaster.setBaselinePeriods(RandomTestUtil.randomString());

		chDiscountProjMaster.setNetFlag(RandomTestUtil.randomString());

		chDiscountProjMaster.setMethodology(RandomTestUtil.randomString());

		chDiscountProjMaster.setDiscountType(RandomTestUtil.randomString());

		chDiscountProjMaster.setProjectedType(RandomTestUtil.randomString());

		_chDiscountProjMasters.add(_persistence.update(chDiscountProjMaster));

		return chDiscountProjMaster;
	}

	private List<ChDiscountProjMaster> _chDiscountProjMasters = new ArrayList<ChDiscountProjMaster>();
	private ChDiscountProjMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}