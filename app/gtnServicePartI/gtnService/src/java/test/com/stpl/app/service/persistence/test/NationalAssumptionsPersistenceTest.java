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

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.ProjectionFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.test.AssertUtils;
import com.liferay.portal.kernel.test.rule.AggregateTestRule;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.test.rule.PersistenceTestRule;
import com.liferay.portal.test.rule.TransactionalTestRule;

import com.stpl.app.exception.NoSuchNationalAssumptionsException;
import com.stpl.app.model.NationalAssumptions;
import com.stpl.app.service.persistence.NationalAssumptionsPK;
import com.stpl.app.service.persistence.NationalAssumptionsPersistence;
import com.stpl.app.service.persistence.NationalAssumptionsUtil;

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
public class NationalAssumptionsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = NationalAssumptionsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<NationalAssumptions> iterator = _nationalAssumptionses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		NationalAssumptionsPK pk = new NationalAssumptionsPK(RandomTestUtil.randomString(),
				RandomTestUtil.randomString(), RandomTestUtil.randomString(),
				RandomTestUtil.nextInt());

		NationalAssumptions nationalAssumptions = _persistence.create(pk);

		Assert.assertNotNull(nationalAssumptions);

		Assert.assertEquals(nationalAssumptions.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		NationalAssumptions newNationalAssumptions = addNationalAssumptions();

		_persistence.remove(newNationalAssumptions);

		NationalAssumptions existingNationalAssumptions = _persistence.fetchByPrimaryKey(newNationalAssumptions.getPrimaryKey());

		Assert.assertNull(existingNationalAssumptions);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addNationalAssumptions();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		NationalAssumptionsPK pk = new NationalAssumptionsPK(RandomTestUtil.randomString(),
				RandomTestUtil.randomString(), RandomTestUtil.randomString(),
				RandomTestUtil.nextInt());

		NationalAssumptions newNationalAssumptions = _persistence.create(pk);

		newNationalAssumptions.setBaselinePeriod(RandomTestUtil.randomString());

		newNationalAssumptions.setFrequency(RandomTestUtil.randomString());

		newNationalAssumptions.setForecastMethodology(RandomTestUtil.randomString());

		newNationalAssumptions.setPriceBasis(RandomTestUtil.randomString());

		newNationalAssumptions.setRollingPeriod(RandomTestUtil.randomString());

		newNationalAssumptions.setBaselineMethodology(RandomTestUtil.randomString());

		newNationalAssumptions.setGrowthRate(RandomTestUtil.nextDouble());

		_nationalAssumptionses.add(_persistence.update(newNationalAssumptions));

		NationalAssumptions existingNationalAssumptions = _persistence.findByPrimaryKey(newNationalAssumptions.getPrimaryKey());

		Assert.assertEquals(existingNationalAssumptions.getBaselinePeriod(),
			newNationalAssumptions.getBaselinePeriod());
		Assert.assertEquals(existingNationalAssumptions.getFrequency(),
			newNationalAssumptions.getFrequency());
		Assert.assertEquals(existingNationalAssumptions.getStartPeriod(),
			newNationalAssumptions.getStartPeriod());
		Assert.assertEquals(existingNationalAssumptions.getForecastMethodology(),
			newNationalAssumptions.getForecastMethodology());
		Assert.assertEquals(existingNationalAssumptions.getPriceType(),
			newNationalAssumptions.getPriceType());
		Assert.assertEquals(existingNationalAssumptions.getEndPeriod(),
			newNationalAssumptions.getEndPeriod());
		Assert.assertEquals(existingNationalAssumptions.getPriceBasis(),
			newNationalAssumptions.getPriceBasis());
		Assert.assertEquals(existingNationalAssumptions.getNaProjMasterSid(),
			newNationalAssumptions.getNaProjMasterSid());
		Assert.assertEquals(existingNationalAssumptions.getRollingPeriod(),
			newNationalAssumptions.getRollingPeriod());
		Assert.assertEquals(existingNationalAssumptions.getBaselineMethodology(),
			newNationalAssumptions.getBaselineMethodology());
		AssertUtils.assertEquals(existingNationalAssumptions.getGrowthRate(),
			newNationalAssumptions.getGrowthRate());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		NationalAssumptions newNationalAssumptions = addNationalAssumptions();

		NationalAssumptions existingNationalAssumptions = _persistence.findByPrimaryKey(newNationalAssumptions.getPrimaryKey());

		Assert.assertEquals(existingNationalAssumptions, newNationalAssumptions);
	}

	@Test(expected = NoSuchNationalAssumptionsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		NationalAssumptionsPK pk = new NationalAssumptionsPK(RandomTestUtil.randomString(),
				RandomTestUtil.randomString(), RandomTestUtil.randomString(),
				RandomTestUtil.nextInt());

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		NationalAssumptions newNationalAssumptions = addNationalAssumptions();

		NationalAssumptions existingNationalAssumptions = _persistence.fetchByPrimaryKey(newNationalAssumptions.getPrimaryKey());

		Assert.assertEquals(existingNationalAssumptions, newNationalAssumptions);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		NationalAssumptionsPK pk = new NationalAssumptionsPK(RandomTestUtil.randomString(),
				RandomTestUtil.randomString(), RandomTestUtil.randomString(),
				RandomTestUtil.nextInt());

		NationalAssumptions missingNationalAssumptions = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingNationalAssumptions);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		NationalAssumptions newNationalAssumptions1 = addNationalAssumptions();
		NationalAssumptions newNationalAssumptions2 = addNationalAssumptions();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newNationalAssumptions1.getPrimaryKey());
		primaryKeys.add(newNationalAssumptions2.getPrimaryKey());

		Map<Serializable, NationalAssumptions> nationalAssumptionses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, nationalAssumptionses.size());
		Assert.assertEquals(newNationalAssumptions1,
			nationalAssumptionses.get(newNationalAssumptions1.getPrimaryKey()));
		Assert.assertEquals(newNationalAssumptions2,
			nationalAssumptionses.get(newNationalAssumptions2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		NationalAssumptionsPK pk1 = new NationalAssumptionsPK(RandomTestUtil.randomString(),
				RandomTestUtil.randomString(), RandomTestUtil.randomString(),
				RandomTestUtil.nextInt());

		NationalAssumptionsPK pk2 = new NationalAssumptionsPK(RandomTestUtil.randomString(),
				RandomTestUtil.randomString(), RandomTestUtil.randomString(),
				RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, NationalAssumptions> nationalAssumptionses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(nationalAssumptionses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		NationalAssumptions newNationalAssumptions = addNationalAssumptions();

		NationalAssumptionsPK pk = new NationalAssumptionsPK(RandomTestUtil.randomString(),
				RandomTestUtil.randomString(), RandomTestUtil.randomString(),
				RandomTestUtil.nextInt());

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newNationalAssumptions.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, NationalAssumptions> nationalAssumptionses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, nationalAssumptionses.size());
		Assert.assertEquals(newNationalAssumptions,
			nationalAssumptionses.get(newNationalAssumptions.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, NationalAssumptions> nationalAssumptionses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(nationalAssumptionses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		NationalAssumptions newNationalAssumptions = addNationalAssumptions();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newNationalAssumptions.getPrimaryKey());

		Map<Serializable, NationalAssumptions> nationalAssumptionses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, nationalAssumptionses.size());
		Assert.assertEquals(newNationalAssumptions,
			nationalAssumptionses.get(newNationalAssumptions.getPrimaryKey()));
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		NationalAssumptions newNationalAssumptions = addNationalAssumptions();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NationalAssumptions.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.startPeriod",
				newNationalAssumptions.getStartPeriod()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.priceType",
				newNationalAssumptions.getPriceType()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.endPeriod",
				newNationalAssumptions.getEndPeriod()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.naProjMasterSid",
				newNationalAssumptions.getNaProjMasterSid()));

		List<NationalAssumptions> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		NationalAssumptions existingNationalAssumptions = result.get(0);

		Assert.assertEquals(existingNationalAssumptions, newNationalAssumptions);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NationalAssumptions.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.startPeriod",
				RandomTestUtil.randomString()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.priceType",
				RandomTestUtil.randomString()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.endPeriod",
				RandomTestUtil.randomString()));
		dynamicQuery.add(RestrictionsFactoryUtil.eq("id.naProjMasterSid",
				RandomTestUtil.nextInt()));

		List<NationalAssumptions> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		NationalAssumptions newNationalAssumptions = addNationalAssumptions();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NationalAssumptions.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.startPeriod"));

		Object newStartPeriod = newNationalAssumptions.getStartPeriod();

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.startPeriod",
				new Object[] { newStartPeriod }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingStartPeriod = result.get(0);

		Assert.assertEquals(existingStartPeriod, newStartPeriod);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(NationalAssumptions.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"id.startPeriod"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("id.startPeriod",
				new Object[] { RandomTestUtil.randomString() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected NationalAssumptions addNationalAssumptions()
		throws Exception {
		NationalAssumptionsPK pk = new NationalAssumptionsPK(RandomTestUtil.randomString(),
				RandomTestUtil.randomString(), RandomTestUtil.randomString(),
				RandomTestUtil.nextInt());

		NationalAssumptions nationalAssumptions = _persistence.create(pk);

		nationalAssumptions.setBaselinePeriod(RandomTestUtil.randomString());

		nationalAssumptions.setFrequency(RandomTestUtil.randomString());

		nationalAssumptions.setForecastMethodology(RandomTestUtil.randomString());

		nationalAssumptions.setPriceBasis(RandomTestUtil.randomString());

		nationalAssumptions.setRollingPeriod(RandomTestUtil.randomString());

		nationalAssumptions.setBaselineMethodology(RandomTestUtil.randomString());

		nationalAssumptions.setGrowthRate(RandomTestUtil.nextDouble());

		_nationalAssumptionses.add(_persistence.update(nationalAssumptions));

		return nationalAssumptions;
	}

	private List<NationalAssumptions> _nationalAssumptionses = new ArrayList<NationalAssumptions>();
	private NationalAssumptionsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}