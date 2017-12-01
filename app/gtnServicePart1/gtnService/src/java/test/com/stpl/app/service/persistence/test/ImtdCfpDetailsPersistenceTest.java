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

import com.stpl.app.exception.NoSuchImtdCfpDetailsException;
import com.stpl.app.model.ImtdCfpDetails;
import com.stpl.app.service.ImtdCfpDetailsLocalServiceUtil;
import com.stpl.app.service.persistence.ImtdCfpDetailsPersistence;
import com.stpl.app.service.persistence.ImtdCfpDetailsUtil;

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
public class ImtdCfpDetailsPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = ImtdCfpDetailsUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<ImtdCfpDetails> iterator = _imtdCfpDetailses.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ImtdCfpDetails imtdCfpDetails = _persistence.create(pk);

		Assert.assertNotNull(imtdCfpDetails);

		Assert.assertEquals(imtdCfpDetails.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		ImtdCfpDetails newImtdCfpDetails = addImtdCfpDetails();

		_persistence.remove(newImtdCfpDetails);

		ImtdCfpDetails existingImtdCfpDetails = _persistence.fetchByPrimaryKey(newImtdCfpDetails.getPrimaryKey());

		Assert.assertNull(existingImtdCfpDetails);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addImtdCfpDetails();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ImtdCfpDetails newImtdCfpDetails = _persistence.create(pk);

		newImtdCfpDetails.setCompanyNo(RandomTestUtil.randomString());

		newImtdCfpDetails.setCfpDetailsStartDate(RandomTestUtil.nextDate());

		newImtdCfpDetails.setCompanyType(RandomTestUtil.randomString());

		newImtdCfpDetails.setCfpDetailsTcStartDate(RandomTestUtil.nextDate());

		newImtdCfpDetails.setModifiedBy(RandomTestUtil.nextInt());

		newImtdCfpDetails.setCreatedDate(RandomTestUtil.nextDate());

		newImtdCfpDetails.setCfpDetailsTcEndDate(RandomTestUtil.nextDate());

		newImtdCfpDetails.setCfpDetailsCreatedDate(RandomTestUtil.nextDate());

		newImtdCfpDetails.setImtdCreatedDate(RandomTestUtil.nextDate());

		newImtdCfpDetails.setCfpDetailsModifiedDate(RandomTestUtil.nextDate());

		newImtdCfpDetails.setCfpDetailsAttachedStatus(RandomTestUtil.nextInt());

		newImtdCfpDetails.setCheckRecord(RandomTestUtil.randomBoolean());

		newImtdCfpDetails.setCfpDetailsAttachedDate(RandomTestUtil.nextDate());

		newImtdCfpDetails.setCfpDetailsEndDate(RandomTestUtil.nextDate());

		newImtdCfpDetails.setCompanyStringId(RandomTestUtil.randomString());

		newImtdCfpDetails.setCfpDetailsTradeClass(RandomTestUtil.randomString());

		newImtdCfpDetails.setTradingPartnerContractNo(RandomTestUtil.randomString());

		newImtdCfpDetails.setCreatedBy(RandomTestUtil.nextInt());

		newImtdCfpDetails.setUsersSid(RandomTestUtil.randomString());

		newImtdCfpDetails.setCompanyStartDate(RandomTestUtil.nextDate());

		newImtdCfpDetails.setCfpDetailsModifiedBy(RandomTestUtil.randomString());

		newImtdCfpDetails.setCompanyEndDate(RandomTestUtil.nextDate());

		newImtdCfpDetails.setCompanyMasterSid(RandomTestUtil.nextInt());

		newImtdCfpDetails.setCfpModelSid(RandomTestUtil.nextInt());

		newImtdCfpDetails.setCfpDetailsSid(RandomTestUtil.nextInt());

		newImtdCfpDetails.setCompanyStatus(RandomTestUtil.nextInt());

		newImtdCfpDetails.setModifiedDate(RandomTestUtil.nextDate());

		newImtdCfpDetails.setCompanyName(RandomTestUtil.randomString());

		newImtdCfpDetails.setOperation(RandomTestUtil.randomString());

		newImtdCfpDetails.setCfpDetailsCreatedBy(RandomTestUtil.randomString());

		newImtdCfpDetails.setSessionId(RandomTestUtil.randomString());

		_imtdCfpDetailses.add(_persistence.update(newImtdCfpDetails));

		ImtdCfpDetails existingImtdCfpDetails = _persistence.findByPrimaryKey(newImtdCfpDetails.getPrimaryKey());

		Assert.assertEquals(existingImtdCfpDetails.getCompanyNo(),
			newImtdCfpDetails.getCompanyNo());
		Assert.assertEquals(existingImtdCfpDetails.getImtdCfpDetailsSid(),
			newImtdCfpDetails.getImtdCfpDetailsSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdCfpDetails.getCfpDetailsStartDate()),
			Time.getShortTimestamp(newImtdCfpDetails.getCfpDetailsStartDate()));
		Assert.assertEquals(existingImtdCfpDetails.getCompanyType(),
			newImtdCfpDetails.getCompanyType());
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdCfpDetails.getCfpDetailsTcStartDate()),
			Time.getShortTimestamp(newImtdCfpDetails.getCfpDetailsTcStartDate()));
		Assert.assertEquals(existingImtdCfpDetails.getModifiedBy(),
			newImtdCfpDetails.getModifiedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdCfpDetails.getCreatedDate()),
			Time.getShortTimestamp(newImtdCfpDetails.getCreatedDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdCfpDetails.getCfpDetailsTcEndDate()),
			Time.getShortTimestamp(newImtdCfpDetails.getCfpDetailsTcEndDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdCfpDetails.getCfpDetailsCreatedDate()),
			Time.getShortTimestamp(newImtdCfpDetails.getCfpDetailsCreatedDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdCfpDetails.getImtdCreatedDate()),
			Time.getShortTimestamp(newImtdCfpDetails.getImtdCreatedDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdCfpDetails.getCfpDetailsModifiedDate()),
			Time.getShortTimestamp(
				newImtdCfpDetails.getCfpDetailsModifiedDate()));
		Assert.assertEquals(existingImtdCfpDetails.getCfpDetailsAttachedStatus(),
			newImtdCfpDetails.getCfpDetailsAttachedStatus());
		Assert.assertEquals(existingImtdCfpDetails.getCheckRecord(),
			newImtdCfpDetails.getCheckRecord());
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdCfpDetails.getCfpDetailsAttachedDate()),
			Time.getShortTimestamp(
				newImtdCfpDetails.getCfpDetailsAttachedDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdCfpDetails.getCfpDetailsEndDate()),
			Time.getShortTimestamp(newImtdCfpDetails.getCfpDetailsEndDate()));
		Assert.assertEquals(existingImtdCfpDetails.getCompanyStringId(),
			newImtdCfpDetails.getCompanyStringId());
		Assert.assertEquals(existingImtdCfpDetails.getCfpDetailsTradeClass(),
			newImtdCfpDetails.getCfpDetailsTradeClass());
		Assert.assertEquals(existingImtdCfpDetails.getTradingPartnerContractNo(),
			newImtdCfpDetails.getTradingPartnerContractNo());
		Assert.assertEquals(existingImtdCfpDetails.getCreatedBy(),
			newImtdCfpDetails.getCreatedBy());
		Assert.assertEquals(existingImtdCfpDetails.getUsersSid(),
			newImtdCfpDetails.getUsersSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdCfpDetails.getCompanyStartDate()),
			Time.getShortTimestamp(newImtdCfpDetails.getCompanyStartDate()));
		Assert.assertEquals(existingImtdCfpDetails.getCfpDetailsModifiedBy(),
			newImtdCfpDetails.getCfpDetailsModifiedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdCfpDetails.getCompanyEndDate()),
			Time.getShortTimestamp(newImtdCfpDetails.getCompanyEndDate()));
		Assert.assertEquals(existingImtdCfpDetails.getCompanyMasterSid(),
			newImtdCfpDetails.getCompanyMasterSid());
		Assert.assertEquals(existingImtdCfpDetails.getCfpModelSid(),
			newImtdCfpDetails.getCfpModelSid());
		Assert.assertEquals(existingImtdCfpDetails.getCfpDetailsSid(),
			newImtdCfpDetails.getCfpDetailsSid());
		Assert.assertEquals(existingImtdCfpDetails.getCompanyStatus(),
			newImtdCfpDetails.getCompanyStatus());
		Assert.assertEquals(Time.getShortTimestamp(
				existingImtdCfpDetails.getModifiedDate()),
			Time.getShortTimestamp(newImtdCfpDetails.getModifiedDate()));
		Assert.assertEquals(existingImtdCfpDetails.getCompanyName(),
			newImtdCfpDetails.getCompanyName());
		Assert.assertEquals(existingImtdCfpDetails.getOperation(),
			newImtdCfpDetails.getOperation());
		Assert.assertEquals(existingImtdCfpDetails.getCfpDetailsCreatedBy(),
			newImtdCfpDetails.getCfpDetailsCreatedBy());
		Assert.assertEquals(existingImtdCfpDetails.getSessionId(),
			newImtdCfpDetails.getSessionId());
	}

	@Test
	public void testCountByImtdCfpSearch() throws Exception {
		_persistence.countByImtdCfpSearch(StringPool.BLANK, StringPool.BLANK,
			RandomTestUtil.nextDate());

		_persistence.countByImtdCfpSearch(StringPool.NULL, StringPool.NULL,
			RandomTestUtil.nextDate());

		_persistence.countByImtdCfpSearch((String)null, (String)null,
			RandomTestUtil.nextDate());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		ImtdCfpDetails newImtdCfpDetails = addImtdCfpDetails();

		ImtdCfpDetails existingImtdCfpDetails = _persistence.findByPrimaryKey(newImtdCfpDetails.getPrimaryKey());

		Assert.assertEquals(existingImtdCfpDetails, newImtdCfpDetails);
	}

	@Test(expected = NoSuchImtdCfpDetailsException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<ImtdCfpDetails> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("IMTD_CFP_DETAILS",
			"companyNo", true, "imtdCfpDetailsSid", true,
			"cfpDetailsStartDate", true, "companyType", true,
			"cfpDetailsTcStartDate", true, "modifiedBy", true, "createdDate",
			true, "cfpDetailsTcEndDate", true, "cfpDetailsCreatedDate", true,
			"imtdCreatedDate", true, "cfpDetailsModifiedDate", true,
			"cfpDetailsAttachedStatus", true, "checkRecord", true,
			"cfpDetailsAttachedDate", true, "cfpDetailsEndDate", true,
			"companyStringId", true, "cfpDetailsTradeClass", true,
			"tradingPartnerContractNo", true, "createdBy", true, "usersSid",
			true, "companyStartDate", true, "cfpDetailsModifiedBy", true,
			"companyEndDate", true, "companyMasterSid", true, "cfpModelSid",
			true, "cfpDetailsSid", true, "companyStatus", true, "modifiedDate",
			true, "companyName", true, "operation", true,
			"cfpDetailsCreatedBy", true, "sessionId", true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		ImtdCfpDetails newImtdCfpDetails = addImtdCfpDetails();

		ImtdCfpDetails existingImtdCfpDetails = _persistence.fetchByPrimaryKey(newImtdCfpDetails.getPrimaryKey());

		Assert.assertEquals(existingImtdCfpDetails, newImtdCfpDetails);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ImtdCfpDetails missingImtdCfpDetails = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingImtdCfpDetails);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		ImtdCfpDetails newImtdCfpDetails1 = addImtdCfpDetails();
		ImtdCfpDetails newImtdCfpDetails2 = addImtdCfpDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newImtdCfpDetails1.getPrimaryKey());
		primaryKeys.add(newImtdCfpDetails2.getPrimaryKey());

		Map<Serializable, ImtdCfpDetails> imtdCfpDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, imtdCfpDetailses.size());
		Assert.assertEquals(newImtdCfpDetails1,
			imtdCfpDetailses.get(newImtdCfpDetails1.getPrimaryKey()));
		Assert.assertEquals(newImtdCfpDetails2,
			imtdCfpDetailses.get(newImtdCfpDetails2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, ImtdCfpDetails> imtdCfpDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(imtdCfpDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		ImtdCfpDetails newImtdCfpDetails = addImtdCfpDetails();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newImtdCfpDetails.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, ImtdCfpDetails> imtdCfpDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, imtdCfpDetailses.size());
		Assert.assertEquals(newImtdCfpDetails,
			imtdCfpDetailses.get(newImtdCfpDetails.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, ImtdCfpDetails> imtdCfpDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(imtdCfpDetailses.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		ImtdCfpDetails newImtdCfpDetails = addImtdCfpDetails();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newImtdCfpDetails.getPrimaryKey());

		Map<Serializable, ImtdCfpDetails> imtdCfpDetailses = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, imtdCfpDetailses.size());
		Assert.assertEquals(newImtdCfpDetails,
			imtdCfpDetailses.get(newImtdCfpDetails.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = ImtdCfpDetailsLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ImtdCfpDetails>() {
				@Override
				public void performAction(ImtdCfpDetails imtdCfpDetails) {
					Assert.assertNotNull(imtdCfpDetails);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		ImtdCfpDetails newImtdCfpDetails = addImtdCfpDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdCfpDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("imtdCfpDetailsSid",
				newImtdCfpDetails.getImtdCfpDetailsSid()));

		List<ImtdCfpDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		ImtdCfpDetails existingImtdCfpDetails = result.get(0);

		Assert.assertEquals(existingImtdCfpDetails, newImtdCfpDetails);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdCfpDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("imtdCfpDetailsSid",
				RandomTestUtil.nextInt()));

		List<ImtdCfpDetails> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		ImtdCfpDetails newImtdCfpDetails = addImtdCfpDetails();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdCfpDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"imtdCfpDetailsSid"));

		Object newImtdCfpDetailsSid = newImtdCfpDetails.getImtdCfpDetailsSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("imtdCfpDetailsSid",
				new Object[] { newImtdCfpDetailsSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingImtdCfpDetailsSid = result.get(0);

		Assert.assertEquals(existingImtdCfpDetailsSid, newImtdCfpDetailsSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ImtdCfpDetails.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"imtdCfpDetailsSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("imtdCfpDetailsSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected ImtdCfpDetails addImtdCfpDetails() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ImtdCfpDetails imtdCfpDetails = _persistence.create(pk);

		imtdCfpDetails.setCompanyNo(RandomTestUtil.randomString());

		imtdCfpDetails.setCfpDetailsStartDate(RandomTestUtil.nextDate());

		imtdCfpDetails.setCompanyType(RandomTestUtil.randomString());

		imtdCfpDetails.setCfpDetailsTcStartDate(RandomTestUtil.nextDate());

		imtdCfpDetails.setModifiedBy(RandomTestUtil.nextInt());

		imtdCfpDetails.setCreatedDate(RandomTestUtil.nextDate());

		imtdCfpDetails.setCfpDetailsTcEndDate(RandomTestUtil.nextDate());

		imtdCfpDetails.setCfpDetailsCreatedDate(RandomTestUtil.nextDate());

		imtdCfpDetails.setImtdCreatedDate(RandomTestUtil.nextDate());

		imtdCfpDetails.setCfpDetailsModifiedDate(RandomTestUtil.nextDate());

		imtdCfpDetails.setCfpDetailsAttachedStatus(RandomTestUtil.nextInt());

		imtdCfpDetails.setCheckRecord(RandomTestUtil.randomBoolean());

		imtdCfpDetails.setCfpDetailsAttachedDate(RandomTestUtil.nextDate());

		imtdCfpDetails.setCfpDetailsEndDate(RandomTestUtil.nextDate());

		imtdCfpDetails.setCompanyStringId(RandomTestUtil.randomString());

		imtdCfpDetails.setCfpDetailsTradeClass(RandomTestUtil.randomString());

		imtdCfpDetails.setTradingPartnerContractNo(RandomTestUtil.randomString());

		imtdCfpDetails.setCreatedBy(RandomTestUtil.nextInt());

		imtdCfpDetails.setUsersSid(RandomTestUtil.randomString());

		imtdCfpDetails.setCompanyStartDate(RandomTestUtil.nextDate());

		imtdCfpDetails.setCfpDetailsModifiedBy(RandomTestUtil.randomString());

		imtdCfpDetails.setCompanyEndDate(RandomTestUtil.nextDate());

		imtdCfpDetails.setCompanyMasterSid(RandomTestUtil.nextInt());

		imtdCfpDetails.setCfpModelSid(RandomTestUtil.nextInt());

		imtdCfpDetails.setCfpDetailsSid(RandomTestUtil.nextInt());

		imtdCfpDetails.setCompanyStatus(RandomTestUtil.nextInt());

		imtdCfpDetails.setModifiedDate(RandomTestUtil.nextDate());

		imtdCfpDetails.setCompanyName(RandomTestUtil.randomString());

		imtdCfpDetails.setOperation(RandomTestUtil.randomString());

		imtdCfpDetails.setCfpDetailsCreatedBy(RandomTestUtil.randomString());

		imtdCfpDetails.setSessionId(RandomTestUtil.randomString());

		_imtdCfpDetailses.add(_persistence.update(imtdCfpDetails));

		return imtdCfpDetails;
	}

	private List<ImtdCfpDetails> _imtdCfpDetailses = new ArrayList<ImtdCfpDetails>();
	private ImtdCfpDetailsPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}