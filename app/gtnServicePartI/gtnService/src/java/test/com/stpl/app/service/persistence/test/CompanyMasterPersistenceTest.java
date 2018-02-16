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

import com.stpl.app.exception.NoSuchCompanyMasterException;
import com.stpl.app.model.CompanyMaster;
import com.stpl.app.service.CompanyMasterLocalServiceUtil;
import com.stpl.app.service.persistence.CompanyMasterPersistence;
import com.stpl.app.service.persistence.CompanyMasterUtil;

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
public class CompanyMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = CompanyMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<CompanyMaster> iterator = _companyMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CompanyMaster companyMaster = _persistence.create(pk);

		Assert.assertNotNull(companyMaster);

		Assert.assertEquals(companyMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		CompanyMaster newCompanyMaster = addCompanyMaster();

		_persistence.remove(newCompanyMaster);

		CompanyMaster existingCompanyMaster = _persistence.fetchByPrimaryKey(newCompanyMaster.getPrimaryKey());

		Assert.assertNull(existingCompanyMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addCompanyMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CompanyMaster newCompanyMaster = _persistence.create(pk);

		newCompanyMaster.setState(RandomTestUtil.nextInt());

		newCompanyMaster.setFinancialSystem(RandomTestUtil.randomString());

		newCompanyMaster.setCompanyGroup(RandomTestUtil.randomString());

		newCompanyMaster.setCompanyName(RandomTestUtil.randomString());

		newCompanyMaster.setCompanyCategory(RandomTestUtil.nextInt());

		newCompanyMaster.setLastUpdatedDate(RandomTestUtil.nextDate());

		newCompanyMaster.setModifiedDate(RandomTestUtil.nextDate());

		newCompanyMaster.setLives(RandomTestUtil.nextInt());

		newCompanyMaster.setAddress2(RandomTestUtil.randomString());

		newCompanyMaster.setCreatedDate(RandomTestUtil.nextDate());

		newCompanyMaster.setCreatedBy(RandomTestUtil.nextInt());

		newCompanyMaster.setSource(RandomTestUtil.randomString());

		newCompanyMaster.setAddress1(RandomTestUtil.randomString());

		newCompanyMaster.setModifiedBy(RandomTestUtil.nextInt());

		newCompanyMaster.setInboundStatus(RandomTestUtil.randomString());

		newCompanyMaster.setZipCode(RandomTestUtil.randomString());

		newCompanyMaster.setCompanyStringId(RandomTestUtil.randomString());

		newCompanyMaster.setCountry(RandomTestUtil.nextInt());

		newCompanyMaster.setInternalNotes(RandomTestUtil.randomString());

		newCompanyMaster.setOrgKey(RandomTestUtil.randomString());

		newCompanyMaster.setCompanyType(RandomTestUtil.nextInt());

		newCompanyMaster.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newCompanyMaster.setCompanyStartDate(RandomTestUtil.nextDate());

		newCompanyMaster.setCompanyNo(RandomTestUtil.randomString());

		newCompanyMaster.setBatchId(RandomTestUtil.randomString());

		newCompanyMaster.setCompanyStatus(RandomTestUtil.nextInt());

		newCompanyMaster.setCompanyEndDate(RandomTestUtil.nextDate());

		newCompanyMaster.setCity(RandomTestUtil.randomString());

		newCompanyMaster.setRegionCode(RandomTestUtil.randomString());

		_companyMasters.add(_persistence.update(newCompanyMaster));

		CompanyMaster existingCompanyMaster = _persistence.findByPrimaryKey(newCompanyMaster.getPrimaryKey());

		Assert.assertEquals(existingCompanyMaster.getState(),
			newCompanyMaster.getState());
		Assert.assertEquals(existingCompanyMaster.getFinancialSystem(),
			newCompanyMaster.getFinancialSystem());
		Assert.assertEquals(existingCompanyMaster.getCompanyGroup(),
			newCompanyMaster.getCompanyGroup());
		Assert.assertEquals(existingCompanyMaster.getCompanyName(),
			newCompanyMaster.getCompanyName());
		Assert.assertEquals(existingCompanyMaster.getCompanyCategory(),
			newCompanyMaster.getCompanyCategory());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCompanyMaster.getLastUpdatedDate()),
			Time.getShortTimestamp(newCompanyMaster.getLastUpdatedDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingCompanyMaster.getModifiedDate()),
			Time.getShortTimestamp(newCompanyMaster.getModifiedDate()));
		Assert.assertEquals(existingCompanyMaster.getLives(),
			newCompanyMaster.getLives());
		Assert.assertEquals(existingCompanyMaster.getAddress2(),
			newCompanyMaster.getAddress2());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCompanyMaster.getCreatedDate()),
			Time.getShortTimestamp(newCompanyMaster.getCreatedDate()));
		Assert.assertEquals(existingCompanyMaster.getCreatedBy(),
			newCompanyMaster.getCreatedBy());
		Assert.assertEquals(existingCompanyMaster.getSource(),
			newCompanyMaster.getSource());
		Assert.assertEquals(existingCompanyMaster.getAddress1(),
			newCompanyMaster.getAddress1());
		Assert.assertEquals(existingCompanyMaster.getModifiedBy(),
			newCompanyMaster.getModifiedBy());
		Assert.assertEquals(existingCompanyMaster.getInboundStatus(),
			newCompanyMaster.getInboundStatus());
		Assert.assertEquals(existingCompanyMaster.getCompanyMasterSid(),
			newCompanyMaster.getCompanyMasterSid());
		Assert.assertEquals(existingCompanyMaster.getZipCode(),
			newCompanyMaster.getZipCode());
		Assert.assertEquals(existingCompanyMaster.getCompanyStringId(),
			newCompanyMaster.getCompanyStringId());
		Assert.assertEquals(existingCompanyMaster.getCountry(),
			newCompanyMaster.getCountry());
		Assert.assertEquals(existingCompanyMaster.getInternalNotes(),
			newCompanyMaster.getInternalNotes());
		Assert.assertEquals(existingCompanyMaster.getOrgKey(),
			newCompanyMaster.getOrgKey());
		Assert.assertEquals(existingCompanyMaster.getCompanyType(),
			newCompanyMaster.getCompanyType());
		Assert.assertEquals(existingCompanyMaster.getRecordLockStatus(),
			newCompanyMaster.getRecordLockStatus());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCompanyMaster.getCompanyStartDate()),
			Time.getShortTimestamp(newCompanyMaster.getCompanyStartDate()));
		Assert.assertEquals(existingCompanyMaster.getCompanyNo(),
			newCompanyMaster.getCompanyNo());
		Assert.assertEquals(existingCompanyMaster.getBatchId(),
			newCompanyMaster.getBatchId());
		Assert.assertEquals(existingCompanyMaster.getCompanyStatus(),
			newCompanyMaster.getCompanyStatus());
		Assert.assertEquals(Time.getShortTimestamp(
				existingCompanyMaster.getCompanyEndDate()),
			Time.getShortTimestamp(newCompanyMaster.getCompanyEndDate()));
		Assert.assertEquals(existingCompanyMaster.getCity(),
			newCompanyMaster.getCity());
		Assert.assertEquals(existingCompanyMaster.getRegionCode(),
			newCompanyMaster.getRegionCode());
	}

	@Test
	public void testCountByCompanyNo() throws Exception {
		_persistence.countByCompanyNo(StringPool.BLANK);

		_persistence.countByCompanyNo(StringPool.NULL);

		_persistence.countByCompanyNo((String)null);
	}

	@Test
	public void testCountByCompanyId() throws Exception {
		_persistence.countByCompanyId(StringPool.BLANK);

		_persistence.countByCompanyId(StringPool.NULL);

		_persistence.countByCompanyId((String)null);
	}

	@Test
	public void testCountByCompanyName() throws Exception {
		_persistence.countByCompanyName(StringPool.BLANK);

		_persistence.countByCompanyName(StringPool.NULL);

		_persistence.countByCompanyName((String)null);
	}

	@Test
	public void testCountByCompanyType() throws Exception {
		_persistence.countByCompanyType(RandomTestUtil.nextInt());

		_persistence.countByCompanyType(0);
	}

	@Test
	public void testCountByCompanyStatus() throws Exception {
		_persistence.countByCompanyStatus(RandomTestUtil.nextInt());

		_persistence.countByCompanyStatus(0);
	}

	@Test
	public void testCountByCompanyUnique() throws Exception {
		_persistence.countByCompanyUnique(StringPool.BLANK);

		_persistence.countByCompanyUnique(StringPool.NULL);

		_persistence.countByCompanyUnique((String)null);
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		CompanyMaster newCompanyMaster = addCompanyMaster();

		CompanyMaster existingCompanyMaster = _persistence.findByPrimaryKey(newCompanyMaster.getPrimaryKey());

		Assert.assertEquals(existingCompanyMaster, newCompanyMaster);
	}

	@Test(expected = NoSuchCompanyMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<CompanyMaster> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("COMPANY_MASTER", "state",
			true, "financialSystem", true, "companyGroup", true, "companyName",
			true, "companyCategory", true, "lastUpdatedDate", true,
			"modifiedDate", true, "lives", true, "address2", true,
			"createdDate", true, "createdBy", true, "source", true, "address1",
			true, "modifiedBy", true, "inboundStatus", true,
			"companyMasterSid", true, "zipCode", true, "companyStringId", true,
			"country", true, "internalNotes", true, "orgKey", true,
			"companyType", true, "recordLockStatus", true, "companyStartDate",
			true, "companyNo", true, "batchId", true, "companyStatus", true,
			"companyEndDate", true, "city", true, "regionCode", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		CompanyMaster newCompanyMaster = addCompanyMaster();

		CompanyMaster existingCompanyMaster = _persistence.fetchByPrimaryKey(newCompanyMaster.getPrimaryKey());

		Assert.assertEquals(existingCompanyMaster, newCompanyMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CompanyMaster missingCompanyMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingCompanyMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		CompanyMaster newCompanyMaster1 = addCompanyMaster();
		CompanyMaster newCompanyMaster2 = addCompanyMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCompanyMaster1.getPrimaryKey());
		primaryKeys.add(newCompanyMaster2.getPrimaryKey());

		Map<Serializable, CompanyMaster> companyMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, companyMasters.size());
		Assert.assertEquals(newCompanyMaster1,
			companyMasters.get(newCompanyMaster1.getPrimaryKey()));
		Assert.assertEquals(newCompanyMaster2,
			companyMasters.get(newCompanyMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, CompanyMaster> companyMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(companyMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		CompanyMaster newCompanyMaster = addCompanyMaster();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCompanyMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, CompanyMaster> companyMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, companyMasters.size());
		Assert.assertEquals(newCompanyMaster,
			companyMasters.get(newCompanyMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, CompanyMaster> companyMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(companyMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		CompanyMaster newCompanyMaster = addCompanyMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newCompanyMaster.getPrimaryKey());

		Map<Serializable, CompanyMaster> companyMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, companyMasters.size());
		Assert.assertEquals(newCompanyMaster,
			companyMasters.get(newCompanyMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = CompanyMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<CompanyMaster>() {
				@Override
				public void performAction(CompanyMaster companyMaster) {
					Assert.assertNotNull(companyMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		CompanyMaster newCompanyMaster = addCompanyMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("companyMasterSid",
				newCompanyMaster.getCompanyMasterSid()));

		List<CompanyMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		CompanyMaster existingCompanyMaster = result.get(0);

		Assert.assertEquals(existingCompanyMaster, newCompanyMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("companyMasterSid",
				RandomTestUtil.nextInt()));

		List<CompanyMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		CompanyMaster newCompanyMaster = addCompanyMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"companyMasterSid"));

		Object newCompanyMasterSid = newCompanyMaster.getCompanyMasterSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("companyMasterSid",
				new Object[] { newCompanyMasterSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingCompanyMasterSid = result.get(0);

		Assert.assertEquals(existingCompanyMasterSid, newCompanyMasterSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(CompanyMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"companyMasterSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("companyMasterSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected CompanyMaster addCompanyMaster() throws Exception {
		int pk = RandomTestUtil.nextInt();

		CompanyMaster companyMaster = _persistence.create(pk);

		companyMaster.setState(RandomTestUtil.nextInt());

		companyMaster.setFinancialSystem(RandomTestUtil.randomString());

		companyMaster.setCompanyGroup(RandomTestUtil.randomString());

		companyMaster.setCompanyName(RandomTestUtil.randomString());

		companyMaster.setCompanyCategory(RandomTestUtil.nextInt());

		companyMaster.setLastUpdatedDate(RandomTestUtil.nextDate());

		companyMaster.setModifiedDate(RandomTestUtil.nextDate());

		companyMaster.setLives(RandomTestUtil.nextInt());

		companyMaster.setAddress2(RandomTestUtil.randomString());

		companyMaster.setCreatedDate(RandomTestUtil.nextDate());

		companyMaster.setCreatedBy(RandomTestUtil.nextInt());

		companyMaster.setSource(RandomTestUtil.randomString());

		companyMaster.setAddress1(RandomTestUtil.randomString());

		companyMaster.setModifiedBy(RandomTestUtil.nextInt());

		companyMaster.setInboundStatus(RandomTestUtil.randomString());

		companyMaster.setZipCode(RandomTestUtil.randomString());

		companyMaster.setCompanyStringId(RandomTestUtil.randomString());

		companyMaster.setCountry(RandomTestUtil.nextInt());

		companyMaster.setInternalNotes(RandomTestUtil.randomString());

		companyMaster.setOrgKey(RandomTestUtil.randomString());

		companyMaster.setCompanyType(RandomTestUtil.nextInt());

		companyMaster.setRecordLockStatus(RandomTestUtil.randomBoolean());

		companyMaster.setCompanyStartDate(RandomTestUtil.nextDate());

		companyMaster.setCompanyNo(RandomTestUtil.randomString());

		companyMaster.setBatchId(RandomTestUtil.randomString());

		companyMaster.setCompanyStatus(RandomTestUtil.nextInt());

		companyMaster.setCompanyEndDate(RandomTestUtil.nextDate());

		companyMaster.setCity(RandomTestUtil.randomString());

		companyMaster.setRegionCode(RandomTestUtil.randomString());

		_companyMasters.add(_persistence.update(companyMaster));

		return companyMaster;
	}

	private List<CompanyMaster> _companyMasters = new ArrayList<CompanyMaster>();
	private CompanyMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}