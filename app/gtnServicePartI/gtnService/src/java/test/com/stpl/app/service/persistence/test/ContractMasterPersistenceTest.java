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
import com.liferay.portal.kernel.test.AssertUtils;
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

import com.stpl.app.exception.NoSuchContractMasterException;
import com.stpl.app.model.ContractMaster;
import com.stpl.app.service.ContractMasterLocalServiceUtil;
import com.stpl.app.service.persistence.ContractMasterPersistence;
import com.stpl.app.service.persistence.ContractMasterUtil;

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
public class ContractMasterPersistenceTest {
	@ClassRule
	@Rule
	public static final AggregateTestRule aggregateTestRule = new AggregateTestRule(new LiferayIntegrationTestRule(),
			PersistenceTestRule.INSTANCE,
			new TransactionalTestRule(Propagation.REQUIRED,
				"com.stpl.app.service"));

	@Before
	public void setUp() {
		_persistence = ContractMasterUtil.getPersistence();

		Class<?> clazz = _persistence.getClass();

		_dynamicQueryClassLoader = clazz.getClassLoader();
	}

	@After
	public void tearDown() throws Exception {
		Iterator<ContractMaster> iterator = _contractMasters.iterator();

		while (iterator.hasNext()) {
			_persistence.remove(iterator.next());

			iterator.remove();
		}
	}

	@Test
	public void testCreate() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ContractMaster contractMaster = _persistence.create(pk);

		Assert.assertNotNull(contractMaster);

		Assert.assertEquals(contractMaster.getPrimaryKey(), pk);
	}

	@Test
	public void testRemove() throws Exception {
		ContractMaster newContractMaster = addContractMaster();

		_persistence.remove(newContractMaster);

		ContractMaster existingContractMaster = _persistence.fetchByPrimaryKey(newContractMaster.getPrimaryKey());

		Assert.assertNull(existingContractMaster);
	}

	@Test
	public void testUpdateNew() throws Exception {
		addContractMaster();
	}

	@Test
	public void testUpdateExisting() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ContractMaster newContractMaster = _persistence.create(pk);

		newContractMaster.setProposalEndDate(RandomTestUtil.nextDate());

		newContractMaster.setCreatedDate(RandomTestUtil.nextDate());

		newContractMaster.setRenegotiationEndDate(RandomTestUtil.nextDate());

		newContractMaster.setOutsideAdditionalName(RandomTestUtil.randomString());

		newContractMaster.setEndDate(RandomTestUtil.nextDate());

		newContractMaster.setManfCompanyMasterSid(RandomTestUtil.randomString());

		newContractMaster.setRenegotiationStartDate(RandomTestUtil.nextDate());

		newContractMaster.setInsideAuthor(RandomTestUtil.randomString());

		newContractMaster.setAdvanceNoticeDays(RandomTestUtil.nextDouble());

		newContractMaster.setOutsideOwner(RandomTestUtil.randomString());

		newContractMaster.setMostFavoredNation(RandomTestUtil.randomString());

		newContractMaster.setInsideAdditionalPhone(RandomTestUtil.randomString());

		newContractMaster.setOriginalStartDate(RandomTestUtil.nextDate());

		newContractMaster.setCreatedBy(RandomTestUtil.nextInt());

		newContractMaster.setProposalStartDate(RandomTestUtil.nextDate());

		newContractMaster.setContractTradeClass(RandomTestUtil.nextInt());

		newContractMaster.setOutsideAdditional(RandomTestUtil.randomString());

		newContractMaster.setProcessStatus(RandomTestUtil.randomBoolean());

		newContractMaster.setInsideAdditionalName(RandomTestUtil.randomString());

		newContractMaster.setContractStatus(RandomTestUtil.nextInt());

		newContractMaster.setContractId(RandomTestUtil.randomString());

		newContractMaster.setModifiedDate(RandomTestUtil.nextDate());

		newContractMaster.setContractType(RandomTestUtil.nextInt());

		newContractMaster.setAwardStatus(RandomTestUtil.nextInt());

		newContractMaster.setInsideOwner(RandomTestUtil.randomString());

		newContractMaster.setSource(RandomTestUtil.randomString());

		newContractMaster.setShippingTerms(RandomTestUtil.randomString());

		newContractMaster.setPriceEscalationClause(RandomTestUtil.randomString());

		newContractMaster.setModifiedBy(RandomTestUtil.nextInt());

		newContractMaster.setOutsideAdditionalPhone(RandomTestUtil.randomString());

		newContractMaster.setTerm(RandomTestUtil.nextInt());

		newContractMaster.setContractNo(RandomTestUtil.randomString());

		newContractMaster.setBatchId(RandomTestUtil.randomString());

		newContractMaster.setDocumentClass(RandomTestUtil.nextInt());

		newContractMaster.setOriginalEndDate(RandomTestUtil.nextDate());

		newContractMaster.setPaymentTerms(RandomTestUtil.nextInt());

		newContractMaster.setInsideAdditional(RandomTestUtil.randomString());

		newContractMaster.setAffiliatedContractInfo(RandomTestUtil.randomString());

		newContractMaster.setCategory(RandomTestUtil.randomString());

		newContractMaster.setOutsidePhone(RandomTestUtil.randomString());

		newContractMaster.setPriceprotectionStartDate(RandomTestUtil.nextDate());

		newContractMaster.setPriceprotectionEndDate(RandomTestUtil.nextDate());

		newContractMaster.setDocumentType(RandomTestUtil.nextInt());

		newContractMaster.setExemptFromLowPrice(RandomTestUtil.randomString());

		newContractMaster.setOrganizationKey(RandomTestUtil.randomString());

		newContractMaster.setCurrency(RandomTestUtil.randomString());

		newContractMaster.setInsidePhone(RandomTestUtil.randomString());

		newContractMaster.setBunitCompanyMasterSid(RandomTestUtil.randomString());

		newContractMaster.setOutsideAuthor(RandomTestUtil.randomString());

		newContractMaster.setContHoldCompanyMasterSid(RandomTestUtil.randomString());

		newContractMaster.setStartDate(RandomTestUtil.nextDate());

		newContractMaster.setContractName(RandomTestUtil.randomString());

		newContractMaster.setLastUpdatedDate(RandomTestUtil.nextDate());

		newContractMaster.setRecordLockStatus(RandomTestUtil.randomBoolean());

		newContractMaster.setPriceResetIndicator(RandomTestUtil.randomString());

		newContractMaster.setMinimumOrder(RandomTestUtil.randomString());

		newContractMaster.setCancellationClause(RandomTestUtil.randomString());

		newContractMaster.setInboundStatus(RandomTestUtil.randomString());

		newContractMaster.setInternalNotes(RandomTestUtil.randomString());

		_contractMasters.add(_persistence.update(newContractMaster));

		ContractMaster existingContractMaster = _persistence.findByPrimaryKey(newContractMaster.getPrimaryKey());

		Assert.assertEquals(Time.getShortTimestamp(
				existingContractMaster.getProposalEndDate()),
			Time.getShortTimestamp(newContractMaster.getProposalEndDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingContractMaster.getCreatedDate()),
			Time.getShortTimestamp(newContractMaster.getCreatedDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingContractMaster.getRenegotiationEndDate()),
			Time.getShortTimestamp(newContractMaster.getRenegotiationEndDate()));
		Assert.assertEquals(existingContractMaster.getOutsideAdditionalName(),
			newContractMaster.getOutsideAdditionalName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingContractMaster.getEndDate()),
			Time.getShortTimestamp(newContractMaster.getEndDate()));
		Assert.assertEquals(existingContractMaster.getManfCompanyMasterSid(),
			newContractMaster.getManfCompanyMasterSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingContractMaster.getRenegotiationStartDate()),
			Time.getShortTimestamp(
				newContractMaster.getRenegotiationStartDate()));
		Assert.assertEquals(existingContractMaster.getInsideAuthor(),
			newContractMaster.getInsideAuthor());
		AssertUtils.assertEquals(existingContractMaster.getAdvanceNoticeDays(),
			newContractMaster.getAdvanceNoticeDays());
		Assert.assertEquals(existingContractMaster.getOutsideOwner(),
			newContractMaster.getOutsideOwner());
		Assert.assertEquals(existingContractMaster.getMostFavoredNation(),
			newContractMaster.getMostFavoredNation());
		Assert.assertEquals(existingContractMaster.getInsideAdditionalPhone(),
			newContractMaster.getInsideAdditionalPhone());
		Assert.assertEquals(Time.getShortTimestamp(
				existingContractMaster.getOriginalStartDate()),
			Time.getShortTimestamp(newContractMaster.getOriginalStartDate()));
		Assert.assertEquals(existingContractMaster.getCreatedBy(),
			newContractMaster.getCreatedBy());
		Assert.assertEquals(Time.getShortTimestamp(
				existingContractMaster.getProposalStartDate()),
			Time.getShortTimestamp(newContractMaster.getProposalStartDate()));
		Assert.assertEquals(existingContractMaster.getContractTradeClass(),
			newContractMaster.getContractTradeClass());
		Assert.assertEquals(existingContractMaster.getOutsideAdditional(),
			newContractMaster.getOutsideAdditional());
		Assert.assertEquals(existingContractMaster.getProcessStatus(),
			newContractMaster.getProcessStatus());
		Assert.assertEquals(existingContractMaster.getInsideAdditionalName(),
			newContractMaster.getInsideAdditionalName());
		Assert.assertEquals(existingContractMaster.getContractMasterSid(),
			newContractMaster.getContractMasterSid());
		Assert.assertEquals(existingContractMaster.getContractStatus(),
			newContractMaster.getContractStatus());
		Assert.assertEquals(existingContractMaster.getContractId(),
			newContractMaster.getContractId());
		Assert.assertEquals(Time.getShortTimestamp(
				existingContractMaster.getModifiedDate()),
			Time.getShortTimestamp(newContractMaster.getModifiedDate()));
		Assert.assertEquals(existingContractMaster.getContractType(),
			newContractMaster.getContractType());
		Assert.assertEquals(existingContractMaster.getAwardStatus(),
			newContractMaster.getAwardStatus());
		Assert.assertEquals(existingContractMaster.getInsideOwner(),
			newContractMaster.getInsideOwner());
		Assert.assertEquals(existingContractMaster.getSource(),
			newContractMaster.getSource());
		Assert.assertEquals(existingContractMaster.getShippingTerms(),
			newContractMaster.getShippingTerms());
		Assert.assertEquals(existingContractMaster.getPriceEscalationClause(),
			newContractMaster.getPriceEscalationClause());
		Assert.assertEquals(existingContractMaster.getModifiedBy(),
			newContractMaster.getModifiedBy());
		Assert.assertEquals(existingContractMaster.getOutsideAdditionalPhone(),
			newContractMaster.getOutsideAdditionalPhone());
		Assert.assertEquals(existingContractMaster.getTerm(),
			newContractMaster.getTerm());
		Assert.assertEquals(existingContractMaster.getContractNo(),
			newContractMaster.getContractNo());
		Assert.assertEquals(existingContractMaster.getBatchId(),
			newContractMaster.getBatchId());
		Assert.assertEquals(existingContractMaster.getDocumentClass(),
			newContractMaster.getDocumentClass());
		Assert.assertEquals(Time.getShortTimestamp(
				existingContractMaster.getOriginalEndDate()),
			Time.getShortTimestamp(newContractMaster.getOriginalEndDate()));
		Assert.assertEquals(existingContractMaster.getPaymentTerms(),
			newContractMaster.getPaymentTerms());
		Assert.assertEquals(existingContractMaster.getInsideAdditional(),
			newContractMaster.getInsideAdditional());
		Assert.assertEquals(existingContractMaster.getAffiliatedContractInfo(),
			newContractMaster.getAffiliatedContractInfo());
		Assert.assertEquals(existingContractMaster.getCategory(),
			newContractMaster.getCategory());
		Assert.assertEquals(existingContractMaster.getOutsidePhone(),
			newContractMaster.getOutsidePhone());
		Assert.assertEquals(Time.getShortTimestamp(
				existingContractMaster.getPriceprotectionStartDate()),
			Time.getShortTimestamp(
				newContractMaster.getPriceprotectionStartDate()));
		Assert.assertEquals(Time.getShortTimestamp(
				existingContractMaster.getPriceprotectionEndDate()),
			Time.getShortTimestamp(
				newContractMaster.getPriceprotectionEndDate()));
		Assert.assertEquals(existingContractMaster.getDocumentType(),
			newContractMaster.getDocumentType());
		Assert.assertEquals(existingContractMaster.getExemptFromLowPrice(),
			newContractMaster.getExemptFromLowPrice());
		Assert.assertEquals(existingContractMaster.getOrganizationKey(),
			newContractMaster.getOrganizationKey());
		Assert.assertEquals(existingContractMaster.getCurrency(),
			newContractMaster.getCurrency());
		Assert.assertEquals(existingContractMaster.getInsidePhone(),
			newContractMaster.getInsidePhone());
		Assert.assertEquals(existingContractMaster.getBunitCompanyMasterSid(),
			newContractMaster.getBunitCompanyMasterSid());
		Assert.assertEquals(existingContractMaster.getOutsideAuthor(),
			newContractMaster.getOutsideAuthor());
		Assert.assertEquals(existingContractMaster.getContHoldCompanyMasterSid(),
			newContractMaster.getContHoldCompanyMasterSid());
		Assert.assertEquals(Time.getShortTimestamp(
				existingContractMaster.getStartDate()),
			Time.getShortTimestamp(newContractMaster.getStartDate()));
		Assert.assertEquals(existingContractMaster.getContractName(),
			newContractMaster.getContractName());
		Assert.assertEquals(Time.getShortTimestamp(
				existingContractMaster.getLastUpdatedDate()),
			Time.getShortTimestamp(newContractMaster.getLastUpdatedDate()));
		Assert.assertEquals(existingContractMaster.getRecordLockStatus(),
			newContractMaster.getRecordLockStatus());
		Assert.assertEquals(existingContractMaster.getPriceResetIndicator(),
			newContractMaster.getPriceResetIndicator());
		Assert.assertEquals(existingContractMaster.getMinimumOrder(),
			newContractMaster.getMinimumOrder());
		Assert.assertEquals(existingContractMaster.getCancellationClause(),
			newContractMaster.getCancellationClause());
		Assert.assertEquals(existingContractMaster.getInboundStatus(),
			newContractMaster.getInboundStatus());
		Assert.assertEquals(existingContractMaster.getInternalNotes(),
			newContractMaster.getInternalNotes());
	}

	@Test
	public void testFindByPrimaryKeyExisting() throws Exception {
		ContractMaster newContractMaster = addContractMaster();

		ContractMaster existingContractMaster = _persistence.findByPrimaryKey(newContractMaster.getPrimaryKey());

		Assert.assertEquals(existingContractMaster, newContractMaster);
	}

	@Test(expected = NoSuchContractMasterException.class)
	public void testFindByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		_persistence.findByPrimaryKey(pk);
	}

	@Test
	public void testFindAll() throws Exception {
		_persistence.findAll(QueryUtil.ALL_POS, QueryUtil.ALL_POS,
			getOrderByComparator());
	}

	protected OrderByComparator<ContractMaster> getOrderByComparator() {
		return OrderByComparatorFactoryUtil.create("CONTRACT_MASTER",
			"proposalEndDate", true, "createdDate", true,
			"renegotiationEndDate", true, "outsideAdditionalName", true,
			"endDate", true, "manfCompanyMasterSid", true,
			"renegotiationStartDate", true, "insideAuthor", true,
			"advanceNoticeDays", true, "outsideOwner", true,
			"mostFavoredNation", true, "insideAdditionalPhone", true,
			"originalStartDate", true, "createdBy", true, "proposalStartDate",
			true, "contractTradeClass", true, "outsideAdditional", true,
			"processStatus", true, "insideAdditionalName", true,
			"contractMasterSid", true, "contractStatus", true, "contractId",
			true, "modifiedDate", true, "contractType", true, "awardStatus",
			true, "insideOwner", true, "source", true, "shippingTerms", true,
			"priceEscalationClause", true, "modifiedBy", true,
			"outsideAdditionalPhone", true, "term", true, "contractNo", true,
			"batchId", true, "documentClass", true, "originalEndDate", true,
			"paymentTerms", true, "insideAdditional", true,
			"affiliatedContractInfo", true, "category", true, "outsidePhone",
			true, "priceprotectionStartDate", true, "priceprotectionEndDate",
			true, "documentType", true, "exemptFromLowPrice", true,
			"organizationKey", true, "currency", true, "insidePhone", true,
			"bunitCompanyMasterSid", true, "outsideAuthor", true,
			"contHoldCompanyMasterSid", true, "startDate", true,
			"contractName", true, "lastUpdatedDate", true, "recordLockStatus",
			true, "priceResetIndicator", true, "minimumOrder", true,
			"cancellationClause", true, "inboundStatus", true, "internalNotes",
			true);
	}

	@Test
	public void testFetchByPrimaryKeyExisting() throws Exception {
		ContractMaster newContractMaster = addContractMaster();

		ContractMaster existingContractMaster = _persistence.fetchByPrimaryKey(newContractMaster.getPrimaryKey());

		Assert.assertEquals(existingContractMaster, newContractMaster);
	}

	@Test
	public void testFetchByPrimaryKeyMissing() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ContractMaster missingContractMaster = _persistence.fetchByPrimaryKey(pk);

		Assert.assertNull(missingContractMaster);
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereAllPrimaryKeysExist()
		throws Exception {
		ContractMaster newContractMaster1 = addContractMaster();
		ContractMaster newContractMaster2 = addContractMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newContractMaster1.getPrimaryKey());
		primaryKeys.add(newContractMaster2.getPrimaryKey());

		Map<Serializable, ContractMaster> contractMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(2, contractMasters.size());
		Assert.assertEquals(newContractMaster1,
			contractMasters.get(newContractMaster1.getPrimaryKey()));
		Assert.assertEquals(newContractMaster2,
			contractMasters.get(newContractMaster2.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereNoPrimaryKeysExist()
		throws Exception {
		int pk1 = RandomTestUtil.nextInt();

		int pk2 = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(pk1);
		primaryKeys.add(pk2);

		Map<Serializable, ContractMaster> contractMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(contractMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithMultiplePrimaryKeysWhereSomePrimaryKeysExist()
		throws Exception {
		ContractMaster newContractMaster = addContractMaster();

		int pk = RandomTestUtil.nextInt();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newContractMaster.getPrimaryKey());
		primaryKeys.add(pk);

		Map<Serializable, ContractMaster> contractMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, contractMasters.size());
		Assert.assertEquals(newContractMaster,
			contractMasters.get(newContractMaster.getPrimaryKey()));
	}

	@Test
	public void testFetchByPrimaryKeysWithNoPrimaryKeys()
		throws Exception {
		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		Map<Serializable, ContractMaster> contractMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertTrue(contractMasters.isEmpty());
	}

	@Test
	public void testFetchByPrimaryKeysWithOnePrimaryKey()
		throws Exception {
		ContractMaster newContractMaster = addContractMaster();

		Set<Serializable> primaryKeys = new HashSet<Serializable>();

		primaryKeys.add(newContractMaster.getPrimaryKey());

		Map<Serializable, ContractMaster> contractMasters = _persistence.fetchByPrimaryKeys(primaryKeys);

		Assert.assertEquals(1, contractMasters.size());
		Assert.assertEquals(newContractMaster,
			contractMasters.get(newContractMaster.getPrimaryKey()));
	}

	@Test
	public void testActionableDynamicQuery() throws Exception {
		final IntegerWrapper count = new IntegerWrapper();

		ActionableDynamicQuery actionableDynamicQuery = ContractMasterLocalServiceUtil.getActionableDynamicQuery();

		actionableDynamicQuery.setPerformActionMethod(new ActionableDynamicQuery.PerformActionMethod<ContractMaster>() {
				@Override
				public void performAction(ContractMaster contractMaster) {
					Assert.assertNotNull(contractMaster);

					count.increment();
				}
			});

		actionableDynamicQuery.performActions();

		Assert.assertEquals(count.getValue(), _persistence.countAll());
	}

	@Test
	public void testDynamicQueryByPrimaryKeyExisting()
		throws Exception {
		ContractMaster newContractMaster = addContractMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ContractMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("contractMasterSid",
				newContractMaster.getContractMasterSid()));

		List<ContractMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		ContractMaster existingContractMaster = result.get(0);

		Assert.assertEquals(existingContractMaster, newContractMaster);
	}

	@Test
	public void testDynamicQueryByPrimaryKeyMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ContractMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.add(RestrictionsFactoryUtil.eq("contractMasterSid",
				RandomTestUtil.nextInt()));

		List<ContractMaster> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	@Test
	public void testDynamicQueryByProjectionExisting()
		throws Exception {
		ContractMaster newContractMaster = addContractMaster();

		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ContractMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"contractMasterSid"));

		Object newContractMasterSid = newContractMaster.getContractMasterSid();

		dynamicQuery.add(RestrictionsFactoryUtil.in("contractMasterSid",
				new Object[] { newContractMasterSid }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(1, result.size());

		Object existingContractMasterSid = result.get(0);

		Assert.assertEquals(existingContractMasterSid, newContractMasterSid);
	}

	@Test
	public void testDynamicQueryByProjectionMissing() throws Exception {
		DynamicQuery dynamicQuery = DynamicQueryFactoryUtil.forClass(ContractMaster.class,
				_dynamicQueryClassLoader);

		dynamicQuery.setProjection(ProjectionFactoryUtil.property(
				"contractMasterSid"));

		dynamicQuery.add(RestrictionsFactoryUtil.in("contractMasterSid",
				new Object[] { RandomTestUtil.nextInt() }));

		List<Object> result = _persistence.findWithDynamicQuery(dynamicQuery);

		Assert.assertEquals(0, result.size());
	}

	protected ContractMaster addContractMaster() throws Exception {
		int pk = RandomTestUtil.nextInt();

		ContractMaster contractMaster = _persistence.create(pk);

		contractMaster.setProposalEndDate(RandomTestUtil.nextDate());

		contractMaster.setCreatedDate(RandomTestUtil.nextDate());

		contractMaster.setRenegotiationEndDate(RandomTestUtil.nextDate());

		contractMaster.setOutsideAdditionalName(RandomTestUtil.randomString());

		contractMaster.setEndDate(RandomTestUtil.nextDate());

		contractMaster.setManfCompanyMasterSid(RandomTestUtil.randomString());

		contractMaster.setRenegotiationStartDate(RandomTestUtil.nextDate());

		contractMaster.setInsideAuthor(RandomTestUtil.randomString());

		contractMaster.setAdvanceNoticeDays(RandomTestUtil.nextDouble());

		contractMaster.setOutsideOwner(RandomTestUtil.randomString());

		contractMaster.setMostFavoredNation(RandomTestUtil.randomString());

		contractMaster.setInsideAdditionalPhone(RandomTestUtil.randomString());

		contractMaster.setOriginalStartDate(RandomTestUtil.nextDate());

		contractMaster.setCreatedBy(RandomTestUtil.nextInt());

		contractMaster.setProposalStartDate(RandomTestUtil.nextDate());

		contractMaster.setContractTradeClass(RandomTestUtil.nextInt());

		contractMaster.setOutsideAdditional(RandomTestUtil.randomString());

		contractMaster.setProcessStatus(RandomTestUtil.randomBoolean());

		contractMaster.setInsideAdditionalName(RandomTestUtil.randomString());

		contractMaster.setContractStatus(RandomTestUtil.nextInt());

		contractMaster.setContractId(RandomTestUtil.randomString());

		contractMaster.setModifiedDate(RandomTestUtil.nextDate());

		contractMaster.setContractType(RandomTestUtil.nextInt());

		contractMaster.setAwardStatus(RandomTestUtil.nextInt());

		contractMaster.setInsideOwner(RandomTestUtil.randomString());

		contractMaster.setSource(RandomTestUtil.randomString());

		contractMaster.setShippingTerms(RandomTestUtil.randomString());

		contractMaster.setPriceEscalationClause(RandomTestUtil.randomString());

		contractMaster.setModifiedBy(RandomTestUtil.nextInt());

		contractMaster.setOutsideAdditionalPhone(RandomTestUtil.randomString());

		contractMaster.setTerm(RandomTestUtil.nextInt());

		contractMaster.setContractNo(RandomTestUtil.randomString());

		contractMaster.setBatchId(RandomTestUtil.randomString());

		contractMaster.setDocumentClass(RandomTestUtil.nextInt());

		contractMaster.setOriginalEndDate(RandomTestUtil.nextDate());

		contractMaster.setPaymentTerms(RandomTestUtil.nextInt());

		contractMaster.setInsideAdditional(RandomTestUtil.randomString());

		contractMaster.setAffiliatedContractInfo(RandomTestUtil.randomString());

		contractMaster.setCategory(RandomTestUtil.randomString());

		contractMaster.setOutsidePhone(RandomTestUtil.randomString());

		contractMaster.setPriceprotectionStartDate(RandomTestUtil.nextDate());

		contractMaster.setPriceprotectionEndDate(RandomTestUtil.nextDate());

		contractMaster.setDocumentType(RandomTestUtil.nextInt());

		contractMaster.setExemptFromLowPrice(RandomTestUtil.randomString());

		contractMaster.setOrganizationKey(RandomTestUtil.randomString());

		contractMaster.setCurrency(RandomTestUtil.randomString());

		contractMaster.setInsidePhone(RandomTestUtil.randomString());

		contractMaster.setBunitCompanyMasterSid(RandomTestUtil.randomString());

		contractMaster.setOutsideAuthor(RandomTestUtil.randomString());

		contractMaster.setContHoldCompanyMasterSid(RandomTestUtil.randomString());

		contractMaster.setStartDate(RandomTestUtil.nextDate());

		contractMaster.setContractName(RandomTestUtil.randomString());

		contractMaster.setLastUpdatedDate(RandomTestUtil.nextDate());

		contractMaster.setRecordLockStatus(RandomTestUtil.randomBoolean());

		contractMaster.setPriceResetIndicator(RandomTestUtil.randomString());

		contractMaster.setMinimumOrder(RandomTestUtil.randomString());

		contractMaster.setCancellationClause(RandomTestUtil.randomString());

		contractMaster.setInboundStatus(RandomTestUtil.randomString());

		contractMaster.setInternalNotes(RandomTestUtil.randomString());

		_contractMasters.add(_persistence.update(contractMaster));

		return contractMaster;
	}

	private List<ContractMaster> _contractMasters = new ArrayList<ContractMaster>();
	private ContractMasterPersistence _persistence;
	private ClassLoader _dynamicQueryClassLoader;
}