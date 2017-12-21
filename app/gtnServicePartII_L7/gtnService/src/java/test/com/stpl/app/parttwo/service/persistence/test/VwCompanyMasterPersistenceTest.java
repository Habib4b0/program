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

import com.stpl.app.parttwo.exception.NoSuchVwCompanyMasterException;
import com.stpl.app.parttwo.model.VwCompanyMaster;
import com.stpl.app.parttwo.service.VwCompanyMasterLocalServiceUtil;
import com.stpl.app.parttwo.service.persistence.VwCompanyMasterPersistence;
import com.stpl.app.parttwo.service.persistence.VwCompanyMasterUtil;

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
public class VwCompanyMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.parttwo.service"));

	@Before
	public void setUp() {
		_persistence = VwCompanyMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<VwCompanyMaster> iterator = _vwCompanyMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwCompanyMaster vwCompanyMaster = _persistence.create(pk);

		Assert.assertNotNull(vwCompanyMaster);

		Assert.assertEquals(vwCompanyMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		VwCompanyMaster newVwCompanyMaster = addVwCompanyMaster();

		_persistence.remove(newVwCompanyMaster);

		VwCompanyMaster existingVwCompanyMaster = _persistence.fetchByPrimaryKey(newVwCompanyMaster.getPrimaryKey());

		Assert.assertNull(existingVwCompanyMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addVwCompanyMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwCompanyMaster newVwCompanyMaster = _persistence.create(pk);

		newVwCompanyMaster.setState(RandomTestUtil.randomString());

		newVwCompanyMaster.setFinancialSystem(RandomTestUtil.randomString());

		newVwCompanyMaster.setCompanyGroup(RandomTestUtil.randomString());

		newVwCompanyMaster.setCompanyName(RandomTestUtil.randomString());

		newVwCompanyMaster.setLastUpdatedDate(RandomTestUtil.nextDate());

		newVwCompanyMaster.setCompanyCategory(RandomTestUtil.randomString());

		newVwCompanyMaster.setModifiedDate(RandomTestUtil.nextDate());

		newVwCompanyMaster.setLives(RandomTestUtil.nextInt());

		newVwCompanyMaster.setOrganizationKey(RandomTestUtil.randomString());

		newVwCompanyMaster.setAddress2(RandomTestUtil.randomString());

		newVwCompanyMaster.setCreatedDate(RandomTestUtil.nextDate());

		newVwCompanyMaster.setCreatedBy(RandomTestUtil.randomString());

		newVwCompanyMaster.setSource(RandomTestUtil.randomString());

		newVwCompanyMaster.setAddress1(RandomTestUtil.randomString());

		newVwCompanyMaster.setAddChgDelIndicator(RandomTestUtil.randomString());

		newVwCompanyMaster.setModifiedBy(RandomTestUtil.randomString());

		newVwCompanyMaster.setUdc6(RandomTestUtil.randomString());

		newVwCompanyMaster.setUdc5(RandomTestUtil.randomString());

		newVwCompanyMaster.setUdc4(RandomTestUtil.randomString());

		newVwCompanyMaster.setUdc1(RandomTestUtil.randomString());

		newVwCompanyMaster.setZipCode(RandomTestUtil.randomString());

		newVwCompanyMaster.setUdc2(RandomTestUtil.randomString());

		newVwCompanyMaster.setUdc3(RandomTestUtil.randomString());

		newVwCompanyMaster.setCompanyId(RandomTestUtil.randomString());

		newVwCompanyMaster.setCountry(RandomTestUtil.randomString());

		newVwCompanyMaster.setCompanyType(RandomTestUtil.randomString());

		newVwCompanyMaster.setCompanyStartDate(RandomTestUtil.nextDate());

		newVwCompanyMaster.setCompanyNo(RandomTestUtil.randomString());

		newVwCompanyMaster.setBatchId(RandomTestUtil.randomString());

		newVwCompanyMaster.setCompanyStatus(RandomTestUtil.randomString());

		newVwCompanyMaster.setCompanyEndDate(RandomTestUtil.nextDate());

		newVwCompanyMaster.setCity(RandomTestUtil.randomString());

		newVwCompanyMaster.setRegionCode(RandomTestUtil.randomString());

		_vwCompanyMasters.add(_persistence.update(newVwCompanyMaster));

		VwCompanyMaster existingVwCompanyMaster = _persistence.findByPrimaryKey(newVwCompanyMaster.getPrimaryKey());

		Assert.assertEquals(existingVwCompanyMaster.getState(),
			newVwCompanyMaster.getState());
		Assert.assertEquals(existingVwCompanyMaster.getFinancialSystem(),
			newVwCompanyMaster.getFinancialSystem());
		Assert.assertEquals(existingVwCompanyMaster.getCompanyGroup(),
			newVwCompanyMaster.getCompanyGroup());
		Assert.assertEquals(existingVwCompanyMaster.getCompanyName(),
			newVwCompanyMaster.getCompanyName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwCompanyMaster.getLastUpdatedDate()),
			Time.getShortTimestamp(newVwCompanyMaster.getLastUpdatedDate()));
		Assert.assertEquals(existingVwCompanyMaster.getCompanyCategory(),
			newVwCompanyMaster.getCompanyCategory());
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwCompanyMaster.getModifiedDate()),
			Time.getShortTimestamp(newVwCompanyMaster.getModifiedDate()));
		Assert.assertEquals(existingVwCompanyMaster.getLives(),
			newVwCompanyMaster.getLives());
		Assert.assertEquals(existingVwCompanyMaster.getOrganizationKey(),
			newVwCompanyMaster.getOrganizationKey());
		Assert.assertEquals(existingVwCompanyMaster.getAddress2(),
			newVwCompanyMaster.getAddress2());
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwCompanyMaster.getCreatedDate()),
			Time.getShortTimestamp(newVwCompanyMaster.getCreatedDate()));
		Assert.assertEquals(existingVwCompanyMaster.getCreatedBy(),
			newVwCompanyMaster.getCreatedBy());
		Assert.assertEquals(existingVwCompanyMaster.getSource(),
			newVwCompanyMaster.getSource());
		Assert.assertEquals(existingVwCompanyMaster.getAddress1(),
			newVwCompanyMaster.getAddress1());
		Assert.assertEquals(existingVwCompanyMaster.getAddChgDelIndicator(),
			newVwCompanyMaster.getAddChgDelIndicator());
		Assert.assertEquals(existingVwCompanyMaster.getModifiedBy(),
			newVwCompanyMaster.getModifiedBy());
		Assert.assertEquals(existingVwCompanyMaster.getUdc6(),
			newVwCompanyMaster.getUdc6());
		Assert.assertEquals(existingVwCompanyMaster.getUdc5(),
			newVwCompanyMaster.getUdc5());
		Assert.assertEquals(existingVwCompanyMaster.getCompanyMasterSid(),
			newVwCompanyMaster.getCompanyMasterSid());
		Assert.assertEquals(existingVwCompanyMaster.getUdc4(),
			newVwCompanyMaster.getUdc4());
		Assert.assertEquals(existingVwCompanyMaster.getUdc1(),
			newVwCompanyMaster.getUdc1());
		Assert.assertEquals(existingVwCompanyMaster.getZipCode(),
			newVwCompanyMaster.getZipCode());
		Assert.assertEquals(existingVwCompanyMaster.getUdc2(),
			newVwCompanyMaster.getUdc2());
		Assert.assertEquals(existingVwCompanyMaster.getUdc3(),
			newVwCompanyMaster.getUdc3());
		Assert.assertEquals(existingVwCompanyMaster.getCompanyId(),
			newVwCompanyMaster.getCompanyId());
		Assert.assertEquals(existingVwCompanyMaster.getCountry(),
			newVwCompanyMaster.getCountry());
		Assert.assertEquals(existingVwCompanyMaster.getCompanyType(),
			newVwCompanyMaster.getCompanyType());
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwCompanyMaster.getCompanyStartDate()),
			Time.getShortTimestamp(newVwCompanyMaster.getCompanyStartDate()));
		Assert.assertEquals(existingVwCompanyMaster.getCompanyNo(),
			newVwCompanyMaster.getCompanyNo());
		Assert.assertEquals(existingVwCompanyMaster.getBatchId(),
			newVwCompanyMaster.getBatchId());
		Assert.assertEquals(existingVwCompanyMaster.getCompanyStatus(),
			newVwCompanyMaster.getCompanyStatus());
		Assert.assertEquals(Time.getShortTimestamp(
				existingVwCompanyMaster.getCompanyEndDate()),
			Time.getShortTimestamp(newVwCompanyMaster.getCompanyEndDate()));
		Assert.assertEquals(existingVwCompanyMaster.getCity(),
			newVwCompanyMaster.getCity());
		Assert.assertEquals(existingVwCompanyMaster.getRegionCode(),
			newVwCompanyMaster.getRegionCode());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		VwCompanyMaster newVwCompanyMaster = addVwCompanyMaster();

		VwCompanyMaster existingVwCompanyMaster = _persistence.findByPrimaryKey(newVwCompanyMaster.getPrimaryKey());

		Assert.assertEquals(existingVwCompanyMaster, newVwCompanyMaster);
	}

	@Test(expected = NoSuchVwCompanyMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<VwCompanyMaster> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("VW_COMPANY_MASTER",
			"state", true, "financialSystem", true, "companyGroup", true,
			"companyName", true, "lastUpdatedDate", true, "companyCategory",
			true, "modifiedDate", true, "lives", true, "organizationKey", true,
			"address2", true, "createdDate", true, "createdBy", true, "source",
			true, "address1", true, "addChgDelIndicator", true, "modifiedBy",
			true, "udc6", true, "udc5", true, "companyMasterSid", true, "udc4",
			true, "udc1", true, "zipCode", true, "udc2", true, "udc3", true,
			"companyId", true, "country", true, "companyType", true,
			"companyStartDate", true, "companyNo", true, "batchId", true,
			"companyStatus", true, "companyEndDate", true, "city", true,
			"regionCode", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		VwCompanyMaster newVwCompanyMaster = addVwCompanyMaster();

		VwCompanyMaster existingVwCompanyMaster = _persistence.fetchByPrimaryKey(newVwCompanyMaster.getPrimaryKey());

		Assert.assertEquals(existingVwCompanyMaster, newVwCompanyMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwCompanyMaster missingVwCompanyMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingVwCompanyMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		VwCompanyMaster newVwCompanyMaster1 = addVwCompanyMaster();
		VwCompanyMaster newVwCompanyMaster2 = addVwCompanyMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newVwCompanyMaster1.getPrimaryKey());
		primaryKeys.add(newVwCompanyMaster2.getPrimaryKey());

		Map<Serializable, VwCompanyMaster> vwCompanyMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, vwCompanyMasters.size());
		Assert.assertEquals(newVwCompanyMaster1,
			vwCompanyMasters.get(newVwCompanyMaster1.getPrimaryKey()));
		Assert.assertEquals(newVwCompanyMaster2,
			vwCompanyMasters.get(newVwCompanyMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, VwCompanyMaster> vwCompanyMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(vwCompanyMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		VwCompanyMaster newVwCompanyMaster = addVwCompanyMaster();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newVwCompanyMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, VwCompanyMaster> vwCompanyMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, vwCompanyMasters.size());
		Assert.assertEquals(newVwCompanyMaster,
			vwCompanyMasters.get(newVwCompanyMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, VwCompanyMaster> vwCompanyMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(vwCompanyMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		VwCompanyMaster newVwCompanyMaster = addVwCompanyMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newVwCompanyMaster.getPrimaryKey());

		Map<Serializable, VwCompanyMaster> vwCompanyMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, vwCompanyMasters.size());
		Assert.assertEquals(newVwCompanyMaster,
			vwCompanyMasters.get(newVwCompanyMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = VwCompanyMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<VwCompanyMaster>() {
				@Override
				public void performAction(VwCompanyMaster vwCompanyMaster) {
					Assert.assertNotNull(vwCompanyMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		VwCompanyMaster newVwCompanyMaster = addVwCompanyMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwCompanyMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("companyMasterSid",
				newVwCompanyMaster.getCompanyMasterSid()));

		List<VwCompanyMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		VwCompanyMaster existingVwCompanyMaster = result.get(0);

		Assert.assertEquals(existingVwCompanyMaster, newVwCompanyMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwCompanyMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("companyMasterSid",
				RandomTestUtil.nextInt()));

		List<VwCompanyMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		VwCompanyMaster newVwCompanyMaster = addVwCompanyMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwCompanyMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"companyMasterSid"));

		Object newCompanyMasterSid = newVwCompanyMaster.getCompanyMasterSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("companyMasterSid",
				new Object[] { newCompanyMasterSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCompanyMasterSid = result.get(0);

		Assert.assertEquals(existingCompanyMasterSid, newCompanyMasterSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(VwCompanyMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"companyMasterSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("companyMasterSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected VwCompanyMaster addVwCompanyMaster() throws Exception {
		int pk = RandomTestUtil.nextInt();

		VwCompanyMaster vwCompanyMaster = _persistence.create(pk);

		vwCompanyMaster.setState(RandomTestUtil.randomString());

		vwCompanyMaster.setFinancialSystem(RandomTestUtil.randomString());

		vwCompanyMaster.setCompanyGroup(RandomTestUtil.randomString());

		vwCompanyMaster.setCompanyName(RandomTestUtil.randomString());

		vwCompanyMaster.setLastUpdatedDate(RandomTestUtil.nextDate());

		vwCompanyMaster.setCompanyCategory(RandomTestUtil.randomString());

		vwCompanyMaster.setModifiedDate(RandomTestUtil.nextDate());

		vwCompanyMaster.setLives(RandomTestUtil.nextInt());

		vwCompanyMaster.setOrganizationKey(RandomTestUtil.randomString());

		vwCompanyMaster.setAddress2(RandomTestUtil.randomString());

		vwCompanyMaster.setCreatedDate(RandomTestUtil.nextDate());

		vwCompanyMaster.setCreatedBy(RandomTestUtil.randomString());

		vwCompanyMaster.setSource(RandomTestUtil.randomString());

		vwCompanyMaster.setAddress1(RandomTestUtil.randomString());

		vwCompanyMaster.setAddChgDelIndicator(RandomTestUtil.randomString());

		vwCompanyMaster.setModifiedBy(RandomTestUtil.randomString());

		vwCompanyMaster.setUdc6(RandomTestUtil.randomString());

		vwCompanyMaster.setUdc5(RandomTestUtil.randomString());

		vwCompanyMaster.setUdc4(RandomTestUtil.randomString());

		vwCompanyMaster.setUdc1(RandomTestUtil.randomString());

		vwCompanyMaster.setZipCode(RandomTestUtil.randomString());

		vwCompanyMaster.setUdc2(RandomTestUtil.randomString());

		vwCompanyMaster.setUdc3(RandomTestUtil.randomString());

		vwCompanyMaster.setCompanyId(RandomTestUtil.randomString());

		vwCompanyMaster.setCountry(RandomTestUtil.randomString());

		vwCompanyMaster.setCompanyType(RandomTestUtil.randomString());

		vwCompanyMaster.setCompanyStartDate(RandomTestUtil.nextDate());

		vwCompanyMaster.setCompanyNo(RandomTestUtil.randomString());

		vwCompanyMaster.setBatchId(RandomTestUtil.randomString());

		vwCompanyMaster.setCompanyStatus(RandomTestUtil.randomString());

		vwCompanyMaster.setCompanyEndDate(RandomTestUtil.nextDate());

		vwCompanyMaster.setCity(RandomTestUtil.randomString());

		vwCompanyMaster.setRegionCode(RandomTestUtil.randomString());

		_vwCompanyMasters.add(_persistence.update(vwCompanyMaster));

		return vwCompanyMaster;
	}

	private List<VwCompanyMaster> _vwCompanyMasters = new ArrayList<VwCompanyMaster>();
	private VwCompanyMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}