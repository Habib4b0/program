package com.stpl.gtn.gtn2o.hierarchyroutebuilder.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkEntityMasterBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkRouteBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkSelectColumnRelationBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:GtnFrameworkHierarchyRouteBuilder-test.xml" })
@Ignore
public class GtnFrameworkHierarchyServiceImplTest {

	Map<String, String> queryMap = new HashMap<>();

	@Autowired
	private GtnFrameworkHierarchyService hierarchyService;
	@Autowired
	private GtnFrameworkEntityMasterBean entityMasterBean;

	@Test
	public void creatQueryForMultiLevelHierarchy() {
	}

	@Before
	public void inintMap() {
		queryMap.put("customerQuery",
				"SELECT  DISTINCT COMPANY_MASTER.COMPANY_NAME as company_mastercompany_name,COMPANY_MASTER.COMPANY_MASTER_SID as company_mastercompany_master_sid FROM COMPANY_MASTER AS COMPANY_MASTER JOIN GL_COST_CENTER_MASTER GL_COST_CENTER_MASTER ON COMPANY_MASTER.COMPANY_NO = GL_COST_CENTER_MASTER.COMPANY_CODE JOIN ITEM_MASTER ITEM_MASTER ON GL_COST_CENTER_MASTER.NDC8 = ITEM_MASTER.NDC8 JOIN ITEM_IDENTIFIER ITEM_IDENTIFIER ON ITEM_MASTER.ITEM_MASTER_SID = ITEM_IDENTIFIER.ITEM_MASTER_SID JOIN BRAND_MASTER BRAND_MASTER ON ITEM_MASTER.BRAND_MASTER_SID = BRAND_MASTER.BRAND_MASTER_SID WHERE CONTRACT_MASTER.CONTRACT_MASTER_SID in (?) AND COMPANY_MASTER.COMPANY_TYPE in (?) AND GL_COST_CENTER_MASTER.INBOUND_STATUS <> 'D' AND ITEM_MASTER.INBOUND_STATUS <> 'D' AND ITEM_IDENTIFIER.INBOUND_STATUS <> 'D' AND BRAND_MASTER.INBOUND_STATUS <> 'D' AND COMPANY_MASTER.INBOUND_STATUS <> 'D'\"");
		queryMap.put("customerQuery1",
				"SELECT  DISTINCT COMPANY_MASTER.COMPANY_NAME as company_mastercompany_name,COMPANY_MASTER.COMPANY_MASTER_SID as company_mastercompany_master_sid FROM COMPANY_MASTER AS COMPANY_MASTER JOIN GL_COST_CENTER_MASTER GL_COST_CENTER_MASTER ON COMPANY_MASTER.COMPANY_NO = GL_COST_CENTER_MASTER.COMPANY_CODE JOIN ITEM_MASTER ITEM_MASTER ON GL_COST_CENTER_MASTER.NDC8 = ITEM_MASTER.NDC8 JOIN ITEM_IDENTIFIER ITEM_IDENTIFIER ON ITEM_MASTER.ITEM_MASTER_SID = ITEM_IDENTIFIER.ITEM_MASTER_SID JOIN BRAND_MASTER BRAND_MASTER ON ITEM_MASTER.BRAND_MASTER_SID = BRAND_MASTER.BRAND_MASTER_SID WHERE CONTRACT_MASTER.CONTRACT_MASTER_SID in (?) AND COMPANY_MASTER.COMPANY_TYPE in (?) AND GL_COST_CENTER_MASTER.INBOUND_STATUS <> 'D' AND ITEM_MASTER.INBOUND_STATUS <> 'D' AND ITEM_IDENTIFIER.INBOUND_STATUS <> 'D' AND BRAND_MASTER.INBOUND_STATUS <> 'D' AND COMPANY_MASTER.INBOUND_STATUS <> 'D'");
		queryMap.put("productQuery",
				"SELECT  DISTINCT COMPANY_MASTER.COMPANY_NAME as company_mastercompany_name,COMPANY_MASTER.COMPANY_MASTER_SID as company_mastercompany_master_sid FROM COMPANY_MASTER AS COMPANY_MASTER JOIN CFP_CONTRACT CFP_CONTRACT ON CONTRACT_MASTER.CONTRACT_MASTER_SID = CFP_CONTRACT.CONTRACT_MASTER_SID JOIN CFP_CONTRACT_DETAILS CFP_CONTRACT_DETAILS ON CFP_CONTRACT.CFP_CONTRACT_SID = CFP_CONTRACT_DETAILS.CFP_CONTRACT_SID JOIN COMPANY_MASTER COMPANY_MASTER ON CFP_CONTRACT_DETAILS.COMPANY_MASTER_SID = COMPANY_MASTER.COMPANY_MASTER_SID JOIN COMPANY_IDENTIFIER COMPANY_IDENTIFIER ON COMPANY_MASTER.COMPANY_MASTER_SID = COMPANY_IDENTIFIER.COMPANY_MASTER_SID WHERE CONTRACT_MASTER.CONTRACT_MASTER_SID in (?) AND COMPANY_MASTER.COMPANY_TYPE in (?) AND CFP_CONTRACT.INBOUND_STATUS <> 'D' AND CFP_CONTRACT_DETAILS.INBOUND_STATUS <> 'D' AND COMPANY_MASTER.INBOUND_STATUS <> 'D' AND COMPANY_IDENTIFIER.INBOUND_STATUS <> 'D' AND COMPANY_MASTER.INBOUND_STATUS <> 'D'");
		queryMap.put("deductionQuery",
				"SELECT  DISTINCT COMPANY_MASTER.COMPANY_NAME as company_mastercompany_name,COMPANY_MASTER.COMPANY_MASTER_SID as company_mastercompany_master_sid FROM RS_CONTRACT AS RS_CONTRACT JOIN UDCS UDCS ON RS_CONTRACT.RS_CONTRACT_SID = UDCS.MASTER_SID JOIN RS_CONTRACT_DETAILS RS_CONTRACT_DETAILS ON RS_CONTRACT.RS_CONTRACT_SID = RS_CONTRACT_DETAILS.RS_CONTRACT_SID WHERE UDCS.MASTER_TYPE = 'RS_CONTRACT' AND CONTRACT_MASTER.CONTRACT_MASTER_SID in (?) AND COMPANY_MASTER.COMPANY_TYPE in (?) AND RS_CONTRACT_DETAILS.INBOUND_STATUS <> 'D' AND RS_CONTRACT.INBOUND_STATUS <> 'D'");
		queryMap.put("createQuery1",
				"SELECT  DISTINCT  FROM COMPANY_MASTER AS COMPANY_MASTER JOIN CFP_CONTRACT_DETAILS CFP_CONTRACT_DETAILS ON CFP_CONTRACT_DETAILS.COMPANY_MASTER_SID = COMPANY_MASTER.COMPANY_MASTER_SID JOIN CFP_CONTRACT CFP_CONTRACT ON CFP_CONTRACT.CFP_CONTRACT_SID = CFP_CONTRACT_DETAILS.CFP_CONTRACT_SID JOIN CONTRACT_MASTER CONTRACT_MASTER ON CONTRACT_MASTER.CONTRACT_MASTER_SID = CFP_CONTRACT.CONTRACT_MASTER_SID");
		queryMap.put("createQuery2",
				"SELECT  DISTINCT  FROM COMPANY_MASTER AS COMPANY_MASTER JOIN CFP_CONTRACT_DETAILS CFP_CONTRACT_DETAILS ON CFP_CONTRACT_DETAILS.COMPANY_MASTER_SID = COMPANY_MASTER.COMPANY_MASTER_SID JOIN CFP_CONTRACT CFP_CONTRACT ON CFP_CONTRACT.CFP_CONTRACT_SID = CFP_CONTRACT_DETAILS.CFP_CONTRACT_SID JOIN CONTRACT_MASTER CONTRACT_MASTER ON CONTRACT_MASTER.CONTRACT_MASTER_SID = CFP_CONTRACT.CONTRACT_MASTER_SID JOIN COMPANY_IDENTIFIER COMPANY_IDENTIFIER ON COMPANY_MASTER.COMPANY_MASTER_SID = COMPANY_IDENTIFIER.COMPANY_MASTER_SID");
		queryMap.put("createQuery3",
				"SELECT  DISTINCT  FROM COMPANY_MASTER AS COMPANY_MASTER JOIN CFP_CONTRACT_DETAILS CFP_CONTRACT_DETAILS ON CFP_CONTRACT_DETAILS.COMPANY_MASTER_SID = COMPANY_MASTER.COMPANY_MASTER_SID JOIN CFP_CONTRACT CFP_CONTRACT ON CFP_CONTRACT.CFP_CONTRACT_SID = CFP_CONTRACT_DETAILS.CFP_CONTRACT_SID JOIN CONTRACT_MASTER CONTRACT_MASTER ON CONTRACT_MASTER.CONTRACT_MASTER_SID = CFP_CONTRACT.CONTRACT_MASTER_SID JOIN COMPANY_IDENTIFIER COMPANY_IDENTIFIER ON COMPANY_MASTER.COMPANY_MASTER_SID = COMPANY_IDENTIFIER.COMPANY_MASTER_SID JOIN COMPANY_PARENT_DETAILS COMPANY_PARENT_DETAILS ON COMPANY_MASTER.COMPANY_MASTER_SID = COMPANY_PARENT_DETAILS.COMPANY_MASTER_SID");
		queryMap.put("createQuery4",
				"SELECT  DISTINCT  FROM COMPANY_MASTER AS COMPANY_MASTER JOIN CFP_CONTRACT_DETAILS CFP_CONTRACT_DETAILS ON CFP_CONTRACT_DETAILS.COMPANY_MASTER_SID = COMPANY_MASTER.COMPANY_MASTER_SID JOIN CFP_CONTRACT CFP_CONTRACT ON CFP_CONTRACT.CFP_CONTRACT_SID = CFP_CONTRACT_DETAILS.CFP_CONTRACT_SID JOIN CONTRACT_MASTER CONTRACT_MASTER ON CONTRACT_MASTER.CONTRACT_MASTER_SID = CFP_CONTRACT.CONTRACT_MASTER_SID JOIN COMPANY_IDENTIFIER COMPANY_IDENTIFIER ON COMPANY_MASTER.COMPANY_MASTER_SID = COMPANY_IDENTIFIER.COMPANY_MASTER_SID JOIN COMPANY_PARENT_DETAILS COMPANY_PARENT_DETAILS ON COMPANY_MASTER.COMPANY_MASTER_SID = COMPANY_PARENT_DETAILS.COMPANY_MASTER_SID JOIN COMPANY_TRADE_CLASS COMPANY_TRADE_CLASS ON COMPANY_MASTER.COMPANY_MASTER_SID = COMPANY_TRADE_CLASS.COMPANY_MASTER_SID");
		queryMap.put("createQuery5",
				"SELECT  DISTINCT  FROM CONTRACT_MASTER AS CONTRACT_MASTER JOIN CFP_CONTRACT CFP_CONTRACT ON CONTRACT_MASTER.CONTRACT_MASTER_SID = CFP_CONTRACT.CONTRACT_MASTER_SID");
		queryMap.put("createQuery6",
				"SELECT  DISTINCT  FROM CONTRACT_MASTER AS CONTRACT_MASTER JOIN CFP_CONTRACT CFP_CONTRACT ON CONTRACT_MASTER.CONTRACT_MASTER_SID = CFP_CONTRACT.CONTRACT_MASTER_SID JOIN CFP_CONTRACT_DETAILS CFP_CONTRACT_DETAILS ON CFP_CONTRACT.CFP_CONTRACT_SID = CFP_CONTRACT_DETAILS.CFP_CONTRACT_SID JOIN COMPANY_MASTER COMPANY_MASTER ON CFP_CONTRACT_DETAILS.COMPANY_MASTER_SID = COMPANY_MASTER.COMPANY_MASTER_SID JOIN COMPANY_IDENTIFIER COMPANY_IDENTIFIER ON COMPANY_MASTER.COMPANY_MASTER_SID = COMPANY_IDENTIFIER.COMPANY_MASTER_SID");
		queryMap.put("createQuery7",
				"SELECT  DISTINCT  FROM CONTRACT_MASTER AS CONTRACT_MASTER JOIN CFP_CONTRACT CFP_CONTRACT ON CONTRACT_MASTER.CONTRACT_MASTER_SID = CFP_CONTRACT.CONTRACT_MASTER_SID JOIN CFP_CONTRACT_DETAILS CFP_CONTRACT_DETAILS ON CFP_CONTRACT.CFP_CONTRACT_SID = CFP_CONTRACT_DETAILS.CFP_CONTRACT_SID JOIN COMPANY_MASTER COMPANY_MASTER ON CFP_CONTRACT_DETAILS.COMPANY_MASTER_SID = COMPANY_MASTER.COMPANY_MASTER_SID JOIN COMPANY_IDENTIFIER COMPANY_IDENTIFIER ON COMPANY_MASTER.COMPANY_MASTER_SID = COMPANY_IDENTIFIER.COMPANY_MASTER_SID JOIN COMPANY_PARENT_DETAILS COMPANY_PARENT_DETAILS ON COMPANY_MASTER.COMPANY_MASTER_SID = COMPANY_PARENT_DETAILS.COMPANY_MASTER_SID");
		queryMap.put("createQuery8",
				"SELECT  DISTINCT  FROM CONTRACT_MASTER AS CONTRACT_MASTER JOIN CFP_CONTRACT CFP_CONTRACT ON CONTRACT_MASTER.CONTRACT_MASTER_SID = CFP_CONTRACT.CONTRACT_MASTER_SID JOIN CFP_CONTRACT_DETAILS CFP_CONTRACT_DETAILS ON CFP_CONTRACT.CFP_CONTRACT_SID = CFP_CONTRACT_DETAILS.CFP_CONTRACT_SID JOIN COMPANY_MASTER COMPANY_MASTER ON CFP_CONTRACT_DETAILS.COMPANY_MASTER_SID = COMPANY_MASTER.COMPANY_MASTER_SID JOIN COMPANY_IDENTIFIER COMPANY_IDENTIFIER ON COMPANY_MASTER.COMPANY_MASTER_SID = COMPANY_IDENTIFIER.COMPANY_MASTER_SID JOIN COMPANY_PARENT_DETAILS COMPANY_PARENT_DETAILS ON COMPANY_MASTER.COMPANY_MASTER_SID = COMPANY_PARENT_DETAILS.COMPANY_MASTER_SID JOIN COMPANY_TRADE_CLASS COMPANY_TRADE_CLASS ON COMPANY_MASTER.COMPANY_MASTER_SID = COMPANY_TRADE_CLASS.COMPANY_MASTER_SID");
		queryMap.put("createQuery9",
				"SELECT  DISTINCT  FROM COMPANY_IDENTIFIER AS COMPANY_IDENTIFIER JOIN COMPANY_MASTER COMPANY_MASTER ON COMPANY_MASTER.COMPANY_MASTER_SID = COMPANY_IDENTIFIER.COMPANY_MASTER_SID JOIN COMPANY_PARENT_DETAILS COMPANY_PARENT_DETAILS ON COMPANY_MASTER.COMPANY_MASTER_SID = COMPANY_PARENT_DETAILS.COMPANY_MASTER_SID");
		queryMap.put("createQuery10",
				"SELECT  DISTINCT  FROM COMPANY_IDENTIFIER AS COMPANY_IDENTIFIER JOIN COMPANY_MASTER COMPANY_MASTER ON COMPANY_MASTER.COMPANY_MASTER_SID = COMPANY_IDENTIFIER.COMPANY_MASTER_SID JOIN COMPANY_PARENT_DETAILS COMPANY_PARENT_DETAILS ON COMPANY_MASTER.COMPANY_MASTER_SID = COMPANY_PARENT_DETAILS.COMPANY_MASTER_SID JOIN COMPANY_TRADE_CLASS COMPANY_TRADE_CLASS ON COMPANY_MASTER.COMPANY_MASTER_SID = COMPANY_TRADE_CLASS.COMPANY_MASTER_SID");
		queryMap.put("createQuery11",
				"SELECT  DISTINCT  FROM COMPANY_PARENT_DETAILS AS COMPANY_PARENT_DETAILS JOIN COMPANY_MASTER COMPANY_MASTER ON COMPANY_MASTER.COMPANY_MASTER_SID = COMPANY_PARENT_DETAILS.COMPANY_MASTER_SID JOIN COMPANY_TRADE_CLASS COMPANY_TRADE_CLASS ON COMPANY_MASTER.COMPANY_MASTER_SID = COMPANY_TRADE_CLASS.COMPANY_MASTER_SID");
		queryMap.put("createQuery12",
				"SELECT  DISTINCT  FROM ITEM_MASTER AS ITEM_MASTER JOIN GL_COST_CENTER_MASTER GL_COST_CENTER_MASTER ON GL_COST_CENTER_MASTER.NDC8 = ITEM_MASTER.NDC8 JOIN COMPANY_MASTER COMPANY_MASTER ON COMPANY_MASTER.COMPANY_NO = GL_COST_CENTER_MASTER.COMPANY_CODE");
		queryMap.put("createQuery13",
				"SELECT  DISTINCT  FROM ITEM_MASTER AS ITEM_MASTER JOIN GL_COST_CENTER_MASTER GL_COST_CENTER_MASTER ON GL_COST_CENTER_MASTER.NDC8 = ITEM_MASTER.NDC8 JOIN COMPANY_MASTER COMPANY_MASTER ON COMPANY_MASTER.COMPANY_NO = GL_COST_CENTER_MASTER.COMPANY_CODE JOIN ITEM_IDENTIFIER ITEM_IDENTIFIER ON ITEM_MASTER.ITEM_MASTER_SID = ITEM_IDENTIFIER.ITEM_MASTER_SID");
		queryMap.put("createQuery14",
				"SELECT  DISTINCT  FROM ITEM_MASTER AS ITEM_MASTER JOIN GL_COST_CENTER_MASTER GL_COST_CENTER_MASTER ON GL_COST_CENTER_MASTER.NDC8 = ITEM_MASTER.NDC8 JOIN COMPANY_MASTER COMPANY_MASTER ON COMPANY_MASTER.COMPANY_NO = GL_COST_CENTER_MASTER.COMPANY_CODE JOIN ITEM_IDENTIFIER ITEM_IDENTIFIER ON ITEM_MASTER.ITEM_MASTER_SID = ITEM_IDENTIFIER.ITEM_MASTER_SID JOIN BRAND_MASTER BRAND_MASTER ON ITEM_MASTER.BRAND_MASTER_SID = BRAND_MASTER.BRAND_MASTER_SID");
		queryMap.put("createQuery15",
				"SELECT  DISTINCT  FROM COMPANY_MASTER AS COMPANY_MASTER JOIN GL_COST_CENTER_MASTER GL_COST_CENTER_MASTER ON COMPANY_MASTER.COMPANY_NO = GL_COST_CENTER_MASTER.COMPANY_CODE JOIN ITEM_MASTER ITEM_MASTER ON GL_COST_CENTER_MASTER.NDC8 = ITEM_MASTER.NDC8 JOIN ITEM_IDENTIFIER ITEM_IDENTIFIER ON ITEM_MASTER.ITEM_MASTER_SID = ITEM_IDENTIFIER.ITEM_MASTER_SID");
		queryMap.put("createQuery16",
				"SELECT  DISTINCT  FROM COMPANY_MASTER AS COMPANY_MASTER JOIN GL_COST_CENTER_MASTER GL_COST_CENTER_MASTER ON COMPANY_MASTER.COMPANY_NO = GL_COST_CENTER_MASTER.COMPANY_CODE JOIN ITEM_MASTER ITEM_MASTER ON GL_COST_CENTER_MASTER.NDC8 = ITEM_MASTER.NDC8 JOIN ITEM_IDENTIFIER ITEM_IDENTIFIER ON ITEM_MASTER.ITEM_MASTER_SID = ITEM_IDENTIFIER.ITEM_MASTER_SID JOIN BRAND_MASTER BRAND_MASTER ON ITEM_MASTER.BRAND_MASTER_SID = BRAND_MASTER.BRAND_MASTER_SID");
		queryMap.put("createQuery17",
				"SELECT  DISTINCT  FROM ITEM_IDENTIFIER AS ITEM_IDENTIFIER JOIN ITEM_MASTER ITEM_MASTER ON ITEM_MASTER.ITEM_MASTER_SID = ITEM_IDENTIFIER.ITEM_MASTER_SID JOIN BRAND_MASTER BRAND_MASTER ON ITEM_MASTER.BRAND_MASTER_SID = BRAND_MASTER.BRAND_MASTER_SID");
		queryMap.put("createQuery18",
				"SELECT  DISTINCT  FROM RS_CONTRACT AS RS_CONTRACT JOIN UDCS UDCS ON RS_CONTRACT.RS_CONTRACT_SID = UDCS.MASTER_SID WHERE UDCS.MASTER_TYPE = 'RS_CONTRACT'");
		queryMap.put("createQuery19",
				"SELECT  DISTINCT  FROM RS_CONTRACT AS RS_CONTRACT JOIN UDCS UDCS ON RS_CONTRACT.RS_CONTRACT_SID = UDCS.MASTER_SID JOIN RS_CONTRACT_DETAILS RS_CONTRACT_DETAILS ON RS_CONTRACT.RS_CONTRACT_SID = RS_CONTRACT_DETAILS.RS_CONTRACT_SID WHERE UDCS.MASTER_TYPE = 'RS_CONTRACT'");
		queryMap.put("createQuery20",
				"SELECT  DISTINCT  FROM RS_CONTRACT AS RS_CONTRACT JOIN UDCS UDCS ON RS_CONTRACT.RS_CONTRACT_SID = UDCS.MASTER_SID JOIN RS_CONTRACT_DETAILS RS_CONTRACT_DETAILS ON RS_CONTRACT.RS_CONTRACT_SID = RS_CONTRACT_DETAILS.RS_CONTRACT_SID WHERE UDCS.MASTER_TYPE = 'RS_CONTRACT'");
		queryMap.put("createQuery21",
				"SELECT  DISTINCT  FROM UDCS AS UDCS JOIN RS_CONTRACT RS_CONTRACT ON RS_CONTRACT.RS_CONTRACT_SID = UDCS.MASTER_SID WHERE UDCS.MASTER_TYPE = 'RS_CONTRACT'");
		queryMap.put("createQuery22",
				"SELECT  DISTINCT  FROM RS_CONTRACT_DETAILS AS RS_CONTRACT_DETAILS JOIN RS_CONTRACT RS_CONTRACT ON RS_CONTRACT.RS_CONTRACT_SID = RS_CONTRACT_DETAILS.RS_CONTRACT_SID");
		queryMap.put("createQuery23",
				"SELECT  DISTINCT  FROM COMPANY_MASTER AS COMPANY_MASTER JOIN COMPANY_IDENTIFIER COMPANY_IDENTIFIER ON COMPANY_MASTER.COMPANY_MASTER_SID = COMPANY_IDENTIFIER.COMPANY_MASTER_SID JOIN COMPANY_PARENT_DETAILS COMPANY_PARENT_DETAILS ON COMPANY_MASTER.COMPANY_MASTER_SID = COMPANY_PARENT_DETAILS.COMPANY_MASTER_SID JOIN COMPANY_TRADE_CLASS COMPANY_TRADE_CLASS ON COMPANY_MASTER.COMPANY_MASTER_SID = COMPANY_TRADE_CLASS.COMPANY_MASTER_SID JOIN CFP_CONTRACT_DETAILS CFP_CONTRACT_DETAILS ON CFP_CONTRACT_DETAILS.COMPANY_MASTER_SID = COMPANY_MASTER.COMPANY_MASTER_SID");
		queryMap.put("createQuery24",
				"SELECT  DISTINCT  FROM COMPANY_IDENTIFIER AS COMPANY_IDENTIFIER JOIN COMPANY_MASTER COMPANY_MASTER ON COMPANY_MASTER.COMPANY_MASTER_SID = COMPANY_IDENTIFIER.COMPANY_MASTER_SID");
		queryMap.put("createQuery25",
				"SELECT  DISTINCT  FROM COMPANY_PARENT_DETAILS AS COMPANY_PARENT_DETAILS JOIN COMPANY_MASTER COMPANY_MASTER ON COMPANY_MASTER.COMPANY_MASTER_SID = COMPANY_PARENT_DETAILS.COMPANY_MASTER_SID");
		queryMap.put("createQuery26",
				"SELECT  DISTINCT  FROM COMPANY_TRADE_CLASS AS COMPANY_TRADE_CLASS JOIN COMPANY_MASTER COMPANY_MASTER ON COMPANY_MASTER.COMPANY_MASTER_SID = COMPANY_TRADE_CLASS.COMPANY_MASTER_SID");
		queryMap.put("createQuery27",
				"SELECT  DISTINCT  FROM COMPANY_MASTER AS COMPANY_MASTER JOIN GL_COST_CENTER_MASTER GL_COST_CENTER_MASTER ON COMPANY_MASTER.COMPANY_NO = GL_COST_CENTER_MASTER.COMPANY_CODE");
		queryMap.put("createQuery28",
				"SELECT  DISTINCT  FROM ITEM_MASTER AS ITEM_MASTER JOIN ITEM_IDENTIFIER ITEM_IDENTIFIER ON ITEM_MASTER.ITEM_MASTER_SID = ITEM_IDENTIFIER.ITEM_MASTER_SID JOIN BRAND_MASTER BRAND_MASTER ON ITEM_MASTER.BRAND_MASTER_SID = BRAND_MASTER.BRAND_MASTER_SID JOIN GL_COST_CENTER_MASTER GL_COST_CENTER_MASTER ON GL_COST_CENTER_MASTER.NDC8 = ITEM_MASTER.NDC8");
		queryMap.put("createQuery29",
				"SELECT  DISTINCT  FROM ITEM_IDENTIFIER AS ITEM_IDENTIFIER JOIN ITEM_MASTER ITEM_MASTER ON ITEM_MASTER.ITEM_MASTER_SID = ITEM_IDENTIFIER.ITEM_MASTER_SID");
		queryMap.put("createQuery30",
				"SELECT  DISTINCT  FROM BRAND_MASTER AS BRAND_MASTER JOIN ITEM_MASTER ITEM_MASTER ON ITEM_MASTER.BRAND_MASTER_SID = BRAND_MASTER.BRAND_MASTER_SID");
	}

	@Test
	public void creatQueryForMultiLevelHierarchyInvalid() throws GtnFrameworkGeneralException {
		try {
			GtnFrameworkQueryGeneratorBean invalidQueryBean = new GtnFrameworkQueryGeneratorBean();
			List<String> invalidntityList = Arrays.asList("BRAND_MASTER", "BRAND_MASTER", "ITEM_MASTER");
			hierarchyService.getQueryByTableNameAndHierarchyTypeForMultiLevel(invalidntityList, "PRODUCT HIERARCHY",
					invalidQueryBean);
			GtnFrameworkSelectColumnRelationBean keyRelationBean = entityMasterBean
					.getKeyRelationBeanUsingTableIdAndColumnName("COMPANY_MASTER", "COMPANY_STATUS");
			List<GtnFrameworkSelectColumnRelationBean> whereKeyListBean = getKeyRelationBeanList();
			hierarchyService.getSelectColumnsForRelationShipBuilder(keyRelationBean, invalidQueryBean);
			hierarchyService.addTableJoin(keyRelationBean, invalidQueryBean);
			hierarchyService.getWhereQuery(whereKeyListBean, invalidQueryBean);
			hierarchyService.getInboundRestrictionQueryForAutoUpdate(invalidQueryBean);
			String invalidFinalQuery = invalidQueryBean.generateQuery();
			System.out.println(invalidFinalQuery);
			String invalidFormedQuery = queryMap.get("customerQuery");
			Assert.assertEquals(invalidFinalQuery, invalidFormedQuery);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private List<GtnFrameworkSelectColumnRelationBean> getKeyRelationBeanList() {
		GtnFrameworkSelectColumnRelationBean whereKeyRelationBean = entityMasterBean
				.getKeyRelationBeanUsingTableIdAndColumnName("CONTRACT_MASTER", "CONTRACT_NAME");
		GtnFrameworkSelectColumnRelationBean whereKeyRelationBean1 = entityMasterBean
				.getKeyRelationBeanUsingTableIdAndColumnName("COMPANY_MASTER", "COMPANY_TYPE");
		List<GtnFrameworkSelectColumnRelationBean> whereKeyListBean = new ArrayList<>();
		whereKeyListBean.add(whereKeyRelationBean);
		whereKeyListBean.add(whereKeyRelationBean1);
		return whereKeyListBean;
	}

	@Test
	public void creatQueryForMultiLevelHierarchyProduct() throws GtnFrameworkGeneralException {
		GtnFrameworkQueryGeneratorBean itemQueryBean = new GtnFrameworkQueryGeneratorBean();
		List<String> itemntityList = Arrays.asList("COMPANY_MASTER", "ITEM_IDENTIFIER", "BRAND_MASTER", "ITEM_MASTER");
		hierarchyService.getQueryByTableNameAndHierarchyTypeForMultiLevel(itemntityList, "PRODUCT HIERARCHY",
				itemQueryBean);
		GtnFrameworkSelectColumnRelationBean keyRelationBean = entityMasterBean
				.getKeyRelationBeanUsingTableIdAndColumnName("COMPANY_MASTER", "COMPANY_NAME");
		List<GtnFrameworkSelectColumnRelationBean> whereKeyListBean = getKeyRelationBeanList();
		hierarchyService.getSelectColumnsForRelationShipBuilder(keyRelationBean, itemQueryBean);
		hierarchyService.getWhereQuery(whereKeyListBean, itemQueryBean);
		hierarchyService.getInboundRestrictionQueryForAutoUpdate(itemQueryBean);
		String itemFinalQuery = itemQueryBean.generateQuery();
		System.out.println(itemFinalQuery);
		String itemFormedQuery = queryMap.get("productQuery");
		Assert.assertEquals(itemFinalQuery, itemFormedQuery);
	}

	@Test
	public void creatQueryForMultiLevelHierarchyCustomer() throws GtnFrameworkGeneralException {
		GtnFrameworkQueryGeneratorBean customerQueryBean = new GtnFrameworkQueryGeneratorBean();
		List<String> customerntityList = Arrays.asList("CONTRACT_MASTER", "COMPANY_MASTER", "COMPANY_IDENTIFIER");
		hierarchyService.getQueryByTableNameAndHierarchyTypeForMultiLevel(customerntityList, "CUSTOMER HIERARCHY",
				customerQueryBean);
		GtnFrameworkSelectColumnRelationBean keyRelationBean = entityMasterBean
				.getKeyRelationBeanUsingTableIdAndColumnName("COMPANY_MASTER", "COMPANY_NAME");
		List<GtnFrameworkSelectColumnRelationBean> whereKeyListBean = getKeyRelationBeanList();
		hierarchyService.getSelectColumnsForRelationShipBuilder(keyRelationBean, customerQueryBean);
		hierarchyService.getWhereQuery(whereKeyListBean, customerQueryBean);
		hierarchyService.getInboundRestrictionQueryForAutoUpdate(customerQueryBean);
		String customerFinalQuery = customerQueryBean.generateQuery();
		System.out.println(customerFinalQuery);
		String customerFormedQuery = queryMap.get("customerQuery1");
		Assert.assertEquals(customerFinalQuery, customerFormedQuery);
	}

	@Test
	public void creatQueryForMultiLevelHierarchyDeduction() throws GtnFrameworkGeneralException {
		GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
		List<String> entityList = Arrays.asList("RS_CONTRACT", "UDCS", "RS_CONTRACT_DETAILS");
		hierarchyService.getQueryByTableNameAndHierarchyTypeForMultiLevel(entityList, "Deduction Hierarchy", queryBean);
		GtnFrameworkSelectColumnRelationBean keyRelationBean = entityMasterBean
				.getKeyRelationBeanUsingTableIdAndColumnName("COMPANY_MASTER", "COMPANY_NAME");
		List<GtnFrameworkSelectColumnRelationBean> whereKeyListBean = getKeyRelationBeanList();
		hierarchyService.getSelectColumnsForRelationShipBuilder(keyRelationBean, queryBean);
		hierarchyService.getWhereQuery(whereKeyListBean, queryBean);
		hierarchyService.getInboundRestrictionQueryForAutoUpdate(queryBean);
		String finalQuery = queryBean.generateQuery();
		System.out.println(finalQuery);
		String formedQuery = queryMap.get("deductionQuery");
		Assert.assertEquals(finalQuery, formedQuery);
	}

	@Test
	public void createQuery1() throws GtnFrameworkGeneralException {
		GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
		List<String> entityList = Arrays.asList("COMPANY_MASTER", "CONTRACT_MASTER");
		hierarchyService.getQueryByTableNameAndHierarchyTypeForMultiLevel(entityList, "CUSTOMER HIERARCHY", queryBean);
		String finalQuery = queryBean.generateQuery();
		System.out.println(finalQuery);
		String formedQuery = queryMap.get("createQuery1");
		Assert.assertEquals(finalQuery, formedQuery);
	}

	@Test
	public void createQuery2() throws GtnFrameworkGeneralException {
		GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
		List<String> entityList = Arrays.asList("COMPANY_MASTER", "CONTRACT_MASTER", "COMPANY_IDENTIFIER");
		hierarchyService.getQueryByTableNameAndHierarchyTypeForMultiLevel(entityList, "CUSTOMER HIERARCHY", queryBean);
		String finalQuery = queryBean.generateQuery();
		System.out.println(finalQuery);
		String formedQuery = queryMap.get("createQuery2");
		Assert.assertEquals(finalQuery, formedQuery);
	}

	@Test
	public void createQuery3() throws GtnFrameworkGeneralException {
		GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
		List<String> entityList = Arrays.asList("COMPANY_MASTER", "CONTRACT_MASTER", "COMPANY_IDENTIFIER",
				"COMPANY_PARENT_DETAILS");
		hierarchyService.getQueryByTableNameAndHierarchyTypeForMultiLevel(entityList, "CUSTOMER HIERARCHY", queryBean);
		String finalQuery = queryBean.generateQuery();
		System.out.println(finalQuery);
		String formedQuery = queryMap.get("createQuery3");
		Assert.assertEquals(finalQuery, formedQuery);
	}

	@Test
	public void createQuery4() throws GtnFrameworkGeneralException {
		GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
		List<String> entityList = Arrays.asList("COMPANY_MASTER", "CONTRACT_MASTER", "COMPANY_IDENTIFIER",
				"COMPANY_PARENT_DETAILS", "COMPANY_TRADE_CLASS");
		hierarchyService.getQueryByTableNameAndHierarchyTypeForMultiLevel(entityList, "CUSTOMER HIERARCHY", queryBean);
		String finalQuery = queryBean.generateQuery();
		System.out.println(finalQuery);
		String formedQuery = queryMap.get("createQuery4");
		Assert.assertEquals(finalQuery, formedQuery);
	}

	@Test
	public void createQuery5() throws GtnFrameworkGeneralException {
		GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
		List<String> entityList = Arrays.asList("CONTRACT_MASTER");
		hierarchyService.getQueryByTableNameAndHierarchyTypeForMultiLevel(entityList, "CUSTOMER HIERARCHY", queryBean);
		String finalQuery = queryBean.generateQuery();
		System.out.println(finalQuery);
		String formedQuery = queryMap.get("createQuery5");
		Assert.assertEquals(finalQuery, formedQuery);
	}

	@Test
	public void createQuery6() throws GtnFrameworkGeneralException {
		GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
		List<String> entityList = Arrays.asList("CONTRACT_MASTER", "COMPANY_IDENTIFIER");
		hierarchyService.getQueryByTableNameAndHierarchyTypeForMultiLevel(entityList, "CUSTOMER HIERARCHY", queryBean);
		String finalQuery = queryBean.generateQuery();
		System.out.println(finalQuery);
		String formedQuery = queryMap.get("createQuery6");
		Assert.assertEquals(finalQuery, formedQuery);
	}

	@Test
	public void createQuery7() throws GtnFrameworkGeneralException {
		GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
		List<String> entityList = Arrays.asList("CONTRACT_MASTER", "COMPANY_IDENTIFIER", "COMPANY_PARENT_DETAILS");
		hierarchyService.getQueryByTableNameAndHierarchyTypeForMultiLevel(entityList, "CUSTOMER HIERARCHY", queryBean);
		String finalQuery = queryBean.generateQuery();
		System.out.println(finalQuery);
		String formedQuery = queryMap.get("createQuery7");
		Assert.assertEquals(finalQuery, formedQuery);
	}

	// @Test
	public void createQuery8() throws GtnFrameworkGeneralException {
		GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
		List<String> entityList = Arrays.asList("CONTRACT_MASTER", "COMPANY_IDENTIFIER", "COMPANY_PARENT_DETAILS",
				"COMPANY_TRADE_CLASS");
		hierarchyService.getQueryByTableNameAndHierarchyTypeForMultiLevel(entityList, "CUSTOMER HIERARCHY", queryBean);
		String finalQuery = queryBean.generateQuery();
		System.out.println(finalQuery);
		String formedQuery = queryMap.get("createQuery8");
		Assert.assertEquals(finalQuery, formedQuery);
	}

	@Test
	public void createQuery9() throws GtnFrameworkGeneralException {
		GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
		List<String> entityList = Arrays.asList("COMPANY_IDENTIFIER", "COMPANY_PARENT_DETAILS");
		hierarchyService.getQueryByTableNameAndHierarchyTypeForMultiLevel(entityList, "CUSTOMER HIERARCHY", queryBean);
		String finalQuery = queryBean.generateQuery();
		System.out.println(finalQuery);
		String formedQuery = queryMap.get("createQuery9");
		Assert.assertEquals(finalQuery, formedQuery);
	}

	@Test
	public void createQuery10() throws GtnFrameworkGeneralException {
		GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
		List<String> entityList = Arrays.asList("COMPANY_IDENTIFIER", "COMPANY_PARENT_DETAILS", "COMPANY_TRADE_CLASS");
		hierarchyService.getQueryByTableNameAndHierarchyTypeForMultiLevel(entityList, "CUSTOMER HIERARCHY", queryBean);
		String finalQuery = queryBean.generateQuery();
		System.out.println(finalQuery);
		String formedQuery = queryMap.get("createQuery10");
		Assert.assertEquals(finalQuery, formedQuery);
	}

	@Test
	public void createQuery11() throws GtnFrameworkGeneralException {
		GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
		List<String> entityList = Arrays.asList("COMPANY_PARENT_DETAILS", "COMPANY_TRADE_CLASS");
		hierarchyService.getQueryByTableNameAndHierarchyTypeForMultiLevel(entityList, "CUSTOMER HIERARCHY", queryBean);
		String finalQuery = queryBean.generateQuery();
		System.out.println(finalQuery);
		String formedQuery = queryMap.get("createQuery11");
		Assert.assertEquals(finalQuery, formedQuery);
	}

	@Test
	public void createQuery12() throws GtnFrameworkGeneralException {
		GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
		List<String> entityList = Arrays.asList("ITEM_MASTER", "COMPANY_MASTER");
		hierarchyService.getQueryByTableNameAndHierarchyTypeForMultiLevel(entityList, "PRODUCT HIERARCHY", queryBean);
		String finalQuery = queryBean.generateQuery();
		System.out.println(finalQuery);
		String formedQuery = queryMap.get("createQuery12");
		Assert.assertEquals(finalQuery, formedQuery);
	}

	@Test
	public void createQuery13() throws GtnFrameworkGeneralException {
		GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
		List<String> entityList = Arrays.asList("ITEM_MASTER", "COMPANY_MASTER", "ITEM_IDENTIFIER");
		hierarchyService.getQueryByTableNameAndHierarchyTypeForMultiLevel(entityList, "PRODUCT HIERARCHY", queryBean);
		String finalQuery = queryBean.generateQuery();
		System.out.println(finalQuery);
		String formedQuery = queryMap.get("createQuery13");
		Assert.assertEquals(finalQuery, formedQuery);
	}

	@Test
	public void createQuery14() throws GtnFrameworkGeneralException {
		GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
		List<String> entityList = Arrays.asList("ITEM_MASTER", "COMPANY_MASTER", "ITEM_IDENTIFIER", "BRAND_MASTER");
		hierarchyService.getQueryByTableNameAndHierarchyTypeForMultiLevel(entityList, "PRODUCT HIERARCHY", queryBean);
		String finalQuery = queryBean.generateQuery();
		System.out.println(finalQuery);
		String formedQuery = queryMap.get("createQuery14");
		Assert.assertEquals(finalQuery, formedQuery);
	}

	@Test
	public void createQuery15() throws GtnFrameworkGeneralException {
		GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
		List<String> entityList = Arrays.asList("COMPANY_MASTER", "ITEM_IDENTIFIER");
		hierarchyService.getQueryByTableNameAndHierarchyTypeForMultiLevel(entityList, "PRODUCT HIERARCHY", queryBean);
		String finalQuery = queryBean.generateQuery();
		System.out.println(finalQuery);
		String formedQuery = queryMap.get("createQuery15");
		Assert.assertEquals(finalQuery, formedQuery);
	}

	@Test
	public void createQuery16() throws GtnFrameworkGeneralException {
		GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
		List<String> entityList = Arrays.asList("COMPANY_MASTER", "ITEM_IDENTIFIER", "BRAND_MASTER");
		hierarchyService.getQueryByTableNameAndHierarchyTypeForMultiLevel(entityList, "PRODUCT HIERARCHY", queryBean);
		String finalQuery = queryBean.generateQuery();
		System.out.println(finalQuery);
		String formedQuery = queryMap.get("createQuery16");
		Assert.assertEquals(finalQuery, formedQuery);
	}

	@Test
	public void createQuery17() throws GtnFrameworkGeneralException {
		GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
		List<String> entityList = Arrays.asList("ITEM_IDENTIFIER", "BRAND_MASTER");
		hierarchyService.getQueryByTableNameAndHierarchyTypeForMultiLevel(entityList, "PRODUCT HIERARCHY", queryBean);
		String finalQuery = queryBean.generateQuery();
		System.out.println(finalQuery);
		String formedQuery = queryMap.get("createQuery17");
		Assert.assertEquals(finalQuery, formedQuery);
	}

	@Test
	public void createQuery18() throws GtnFrameworkGeneralException {
		GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
		List<String> entityList = Arrays.asList("RS_CONTRACT", "UDCS");
		hierarchyService.getQueryByTableNameAndHierarchyTypeForMultiLevel(entityList, "Deduction Hierarchy", queryBean);
		String finalQuery = queryBean.generateQuery();
		System.out.println(finalQuery);
		String formedQuery = queryMap.get("createQuery18");
		Assert.assertEquals(finalQuery, formedQuery);
	}

	@Test
	public void createQuery19() throws GtnFrameworkGeneralException {
		GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
		List<String> entityList = Arrays.asList("RS_CONTRACT", "UDCS", "RS_CONTRACT_DETAILS");
		hierarchyService.getQueryByTableNameAndHierarchyTypeForMultiLevel(entityList, "Deduction Hierarchy", queryBean);
		String finalQuery = queryBean.generateQuery();
		System.out.println(finalQuery);
		String formedQuery = queryMap.get("createQuery19");
		Assert.assertEquals(finalQuery, formedQuery);
	}

	@Test
	public void createQuery20() throws GtnFrameworkGeneralException {
		GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
		List<String> entityList = Arrays.asList("RS_CONTRACT");
		hierarchyService.getQueryByTableNameAndHierarchyTypeForMultiLevel(entityList, "Deduction Hierarchy", queryBean);
		String finalQuery = queryBean.generateQuery();
		System.out.println(finalQuery);
		String formedQuery = queryMap.get("createQuery20");
		Assert.assertEquals(finalQuery, formedQuery);
	}

	@Test
	public void createQuery21() throws GtnFrameworkGeneralException {
		GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
		List<String> entityList = Arrays.asList("UDCS");
		hierarchyService.getQueryByTableNameAndHierarchyTypeForMultiLevel(entityList, "Deduction Hierarchy", queryBean);
		String finalQuery = queryBean.generateQuery();
		System.out.println(finalQuery);
		String formedQuery = queryMap.get("createQuery21");
		Assert.assertEquals(finalQuery, formedQuery);
	}

	@Test
	public void createQuery22() throws GtnFrameworkGeneralException {
		GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
		List<String> entityList = Arrays.asList("RS_CONTRACT_DETAILS");
		hierarchyService.getQueryByTableNameAndHierarchyTypeForMultiLevel(entityList, "Deduction Hierarchy", queryBean);
		String finalQuery = queryBean.generateQuery();
		System.out.println(finalQuery);
		String formedQuery = queryMap.get("createQuery22");
		Assert.assertEquals(finalQuery, formedQuery);
	}

	@Test
	public void createQuery23() throws GtnFrameworkGeneralException {
		GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
		List<String> entityList = Arrays.asList("COMPANY_MASTER");
		hierarchyService.getQueryByTableNameAndHierarchyTypeForMultiLevel(entityList, "CUSTOMER HIERARCHY", queryBean);
		String finalQuery = queryBean.generateQuery();
		System.out.println(finalQuery);
		String formedQuery = queryMap.get("createQuery23");
		Assert.assertEquals(finalQuery, formedQuery);
	}

	@Test
	public void createQuery24() throws GtnFrameworkGeneralException {
		GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
		List<String> entityList = Arrays.asList("COMPANY_IDENTIFIER");
		hierarchyService.getQueryByTableNameAndHierarchyTypeForMultiLevel(entityList, "CUSTOMER HIERARCHY", queryBean);
		String finalQuery = queryBean.generateQuery();
		System.out.println(finalQuery);
		String formedQuery = queryMap.get("createQuery24");
		Assert.assertEquals(finalQuery, formedQuery);
	}

	@Test
	public void createQuery25() throws GtnFrameworkGeneralException {
		GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
		List<String> entityList = Arrays.asList("COMPANY_PARENT_DETAILS");
		hierarchyService.getQueryByTableNameAndHierarchyTypeForMultiLevel(entityList, "CUSTOMER HIERARCHY", queryBean);
		String finalQuery = queryBean.generateQuery();
		System.out.println(finalQuery);
		String formedQuery = queryMap.get("createQuery25");
		Assert.assertEquals(finalQuery, formedQuery);
	}

	@Test
	public void createQuery26() throws GtnFrameworkGeneralException {
		GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
		List<String> entityList = Arrays.asList("COMPANY_TRADE_CLASS");
		hierarchyService.getQueryByTableNameAndHierarchyTypeForMultiLevel(entityList, "CUSTOMER HIERARCHY", queryBean);
		String finalQuery = queryBean.generateQuery();
		System.out.println(finalQuery);
		String formedQuery = queryMap.get("createQuery26");
		Assert.assertEquals(finalQuery, formedQuery);
	}

	@Test
	public void createQuery27() throws GtnFrameworkGeneralException {
		GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
		List<String> entityList = Arrays.asList("COMPANY_MASTER");
		hierarchyService.getQueryByTableNameAndHierarchyTypeForMultiLevel(entityList, "PRODUCT HIERARCHY", queryBean);
		String finalQuery = queryBean.generateQuery();
		System.out.println(finalQuery);
		String formedQuery = queryMap.get("createQuery27");
		Assert.assertEquals(finalQuery, formedQuery);
	}

	@Test
	public void createQuery28() throws GtnFrameworkGeneralException {
		GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
		List<String> entityList = Arrays.asList("ITEM_MASTER");
		hierarchyService.getQueryByTableNameAndHierarchyTypeForMultiLevel(entityList, "PRODUCT HIERARCHY", queryBean);
		String finalQuery = queryBean.generateQuery();
		System.out.println(finalQuery);
		String formedQuery = queryMap.get("createQuery28");
		Assert.assertEquals(finalQuery, formedQuery);
	}

	@Test
	public void createQuery29() throws GtnFrameworkGeneralException {
		GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
		List<String> entityList = Arrays.asList("ITEM_IDENTIFIER");
		hierarchyService.getQueryByTableNameAndHierarchyTypeForMultiLevel(entityList, "PRODUCT HIERARCHY", queryBean);
		String finalQuery = queryBean.generateQuery();
		System.out.println(finalQuery);
		String formedQuery = queryMap.get("createQuery29");
		Assert.assertEquals(finalQuery, formedQuery);
	}

	@Test
	public void createQuery30() throws GtnFrameworkGeneralException {
		GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
		List<String> entityList = Arrays.asList("BRAND_MASTER");
		hierarchyService.getQueryByTableNameAndHierarchyTypeForMultiLevel(entityList, "PRODUCT HIERARCHY", queryBean);
		String finalQuery = queryBean.generateQuery();
		System.out.println(finalQuery);
		String formedQuery = queryMap.get("createQuery30");
		Assert.assertEquals(finalQuery, formedQuery);
	}

	@Test
	public void getRoutePathInvalid() {
		GtnFrameworkRouteBean invalidRoutePath = hierarchyService.getRoutePath(1, 10);
		Object[] invalidPath = {};
		System.out.println(invalidRoutePath.getPathList());
		Assert.assertArrayEquals(invalidRoutePath.getPathList().toArray(), invalidPath);
	}

	@Test
	public void getRoutePath1() {
		GtnFrameworkRouteBean companyItemRoutePath = hierarchyService.getRoutePath(1, 2);
		Object[] companyItemPath = { 4, 3, 2, 1 };
		System.out.println(companyItemRoutePath.getPathList());
		Assert.assertArrayEquals(companyItemRoutePath.getPathList().toArray(), companyItemPath);
	}

	@Test
	public void getRoutePath2() {
		GtnFrameworkRouteBean companyItemRoutePath = hierarchyService.getRoutePath(1, 3);
		Object[] companyItemPath = { 4, 5 };
		System.out.println(companyItemRoutePath.getPathList());
		Assert.assertArrayEquals(companyItemRoutePath.getPathList().toArray(), companyItemPath);
	}

	@Test
	public void getRoutePath3() {
		GtnFrameworkRouteBean companyItemRoutePath = hierarchyService.getRoutePath(1, 4);
		Object[] companyItemPath = { 4, 6 };
		System.out.println(companyItemRoutePath.getPathList());
		Assert.assertArrayEquals(companyItemRoutePath.getPathList().toArray(), companyItemPath);
	}

	@Test
	public void getRoutePath4() {
		GtnFrameworkRouteBean companyItemRoutePath = hierarchyService.getRoutePath(1, 5);
		Object[] companyItemPath = { 4, 7 };
		System.out.println(companyItemRoutePath.getPathList());
		Assert.assertArrayEquals(companyItemRoutePath.getPathList().toArray(), companyItemPath);
	}

	@Test
	public void getRoutePath5() {
		GtnFrameworkRouteBean companyItemRoutePath = hierarchyService.getRoutePath(2, 3);
		Object[] companyItemPath = { 1, 2, 3, 4, 5 };
		System.out.println(companyItemRoutePath.getPathList());
		Assert.assertArrayEquals(companyItemRoutePath.getPathList().toArray(), companyItemPath);
	}

	@Test
	public void getRoutePath6() {
		GtnFrameworkRouteBean companyItemRoutePath = hierarchyService.getRoutePath(2, 4);
		Object[] companyItemPath = { 1, 2, 3, 4, 6 };
		System.out.println(companyItemRoutePath.getPathList());
		Assert.assertArrayEquals(companyItemRoutePath.getPathList().toArray(), companyItemPath);
	}

	// @Test
	public void getRoutePath7() {
		GtnFrameworkRouteBean companyItemRoutePath = hierarchyService.getRoutePath(2, 5);
		Object[] companyItemPath = { 1, 2, 3, 4, 7 };
		System.out.println(companyItemRoutePath.getPathList());
		Assert.assertArrayEquals(companyItemRoutePath.getPathList().toArray(), companyItemPath);
	}

	@Test
	public void getRoutePath8() {
		GtnFrameworkRouteBean companyItemRoutePath = hierarchyService.getRoutePath(3, 4);
		Object[] companyItemPath = { 5, 4, 6 };
		System.out.println(companyItemRoutePath.getPathList());
		Assert.assertArrayEquals(companyItemRoutePath.getPathList().toArray(), companyItemPath);
	}

	@Test
	public void getRoutePath9() {
		GtnFrameworkRouteBean companyItemRoutePath = hierarchyService.getRoutePath(3, 5);
		Object[] companyItemPath = { 5, 4, 7 };
		System.out.println(companyItemRoutePath.getPathList());
		Assert.assertArrayEquals(companyItemRoutePath.getPathList().toArray(), companyItemPath);
	}

	@Test
	public void getRoutePath10() {
		GtnFrameworkRouteBean companyItemRoutePath = hierarchyService.getRoutePath(4, 5);
		Object[] companyItemPath = { 6, 4, 7 };
		System.out.println(companyItemRoutePath.getPathList());
		Assert.assertArrayEquals(companyItemRoutePath.getPathList().toArray(), companyItemPath);
	}

	@Test
	public void getRoutePathProduct() {
		GtnFrameworkRouteBean customerContractRoutePath = hierarchyService.getRoutePath(6, 7);
		Object[] customerContractPath = { 12, 9, 8 };
		System.out.println(customerContractRoutePath.getPathList());
		Assert.assertArrayEquals(customerContractRoutePath.getPathList().toArray(), customerContractPath);
	}

	@Test
	public void getRoutePathProduct1() {
		GtnFrameworkRouteBean customerContractRoutePath = hierarchyService.getRoutePath(6, 8);
		Object[] customerContractPath = { 12, 10 };
		System.out.println(customerContractRoutePath.getPathList());
		Assert.assertArrayEquals(customerContractRoutePath.getPathList().toArray(), customerContractPath);
	}

	@Test
	public void getRoutePathProduct2() {
		GtnFrameworkRouteBean customerContractRoutePath = hierarchyService.getRoutePath(6, 9);
		Object[] customerContractPath = { 12, 11 };
		System.out.println(customerContractRoutePath.getPathList());
		Assert.assertArrayEquals(customerContractRoutePath.getPathList().toArray(), customerContractPath);
	}

	@Test
	public void getRoutePathProduct3() {
		GtnFrameworkRouteBean customerContractRoutePath = hierarchyService.getRoutePath(7, 8);
		Object[] customerContractPath = { 8, 9, 12, 10 };
		System.out.println(customerContractRoutePath.getPathList());
		Assert.assertArrayEquals(customerContractRoutePath.getPathList().toArray(), customerContractPath);
	}

	@Test
	public void getRoutePathProduct4() {
		GtnFrameworkRouteBean customerContractRoutePath = hierarchyService.getRoutePath(7, 9);
		Object[] customerContractPath = { 8, 9, 12, 11 };
		System.out.println(customerContractRoutePath.getPathList());
		Assert.assertArrayEquals(customerContractRoutePath.getPathList().toArray(), customerContractPath);
	}

	@Test
	public void getRoutePathProduct5() {
		GtnFrameworkRouteBean customerContractRoutePath = hierarchyService.getRoutePath(8, 9);
		Object[] customerContractPath = { 10, 12, 11 };
		System.out.println(customerContractRoutePath.getPathList());
		Assert.assertArrayEquals(customerContractRoutePath.getPathList().toArray(), customerContractPath);
	}

	@Test
	public void getRoutePathDeduction() {
		GtnFrameworkRouteBean deductionRoutePath = hierarchyService.getRoutePath(10, 11);
		Object[] deductionPath = { 13, 14 };
		System.out.println(deductionRoutePath.getPathList());
		Assert.assertArrayEquals(deductionRoutePath.getPathList().toArray(), deductionPath);
	}

	@Test
	public void getRoutePathDeduction1() {
		GtnFrameworkRouteBean deductionRoutePath = hierarchyService.getRoutePath(10, 12);
		Object[] deductionPath = { 13, 15 };
		System.out.println(deductionRoutePath.getPathList());
		Assert.assertArrayEquals(deductionRoutePath.getPathList().toArray(), deductionPath);
	}

	@Test
	public void getRoutePathDeduction2() {
		GtnFrameworkRouteBean deductionRoutePath = hierarchyService.getRoutePath(11, 12);
		Object[] deductionPath = { 14, 13, 15 };
		System.out.println(deductionRoutePath.getPathList());
		Assert.assertArrayEquals(deductionRoutePath.getPathList().toArray(), deductionPath);
	}

}