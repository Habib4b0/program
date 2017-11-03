package com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GtnFrameworkEntityMasterMainBean {
	private static GtnFrameworkEntityMasterMainBean entityMasterBean = new GtnFrameworkEntityMasterMainBean();

	private final List<GtnFrameworkEntityBean> entityList = new ArrayList<>();
	private final List<GtnFramworkTableRelationBean> entityRelationList = new ArrayList<>();
	private final List<GtnFramworkTableBean> entityTableList = new ArrayList<>();
	private final List<GtnFrameworkRouteBean> entityRouteList = new ArrayList<>();
	private final List<GtnFrameworkSingleColumnRelationBean> singleColumnRelationList = new ArrayList<>();
	private final List<GtnFrameworkEntityHierarchyRelationBean> entityHirarchyRelationList = new ArrayList<>();

	private GtnFrameworkEntityMasterMainBean() {
		initSetup();
	}

	public void initSetup() {
		insertToEntityRelationList();
		insertToEntityList();
		insertToTableList();
		insertToKeyRelationList();
		insertToHierarchyRelationList();
	}

	public static GtnFrameworkEntityMasterMainBean getInstance() {
		return entityMasterBean;
	}

	public List<GtnFrameworkEntityBean> getEntityList() {
		return Collections.unmodifiableList(entityList);
	}

	public void addEntityList(GtnFrameworkEntityBean entityItem) {
		entityList.add(entityItem);
	}

	public List<GtnFramworkTableBean> getEntityTableList() {
		return Collections.unmodifiableList(entityTableList);
	}

	public void addEntityTableList(GtnFramworkTableBean entityTableItem) {
		entityTableList.add(entityTableItem);
	}

	public List<GtnFramworkTableRelationBean> getEntityRelationList() {
		return Collections.unmodifiableList(entityRelationList);
	}

	public void addEntityRelationList(GtnFramworkTableRelationBean entityRelationItem) {
		entityRelationList.add(entityRelationItem);
	}

	public void addKeyRelationList(GtnFrameworkSingleColumnRelationBean keyRelationItem) {
		singleColumnRelationList.add(keyRelationItem);
	}

	public void addEntityHierarchyRelationList(GtnFrameworkEntityHierarchyRelationBean entityHierarchyRelationItem) {
		entityHirarchyRelationList.add(entityHierarchyRelationItem);
	}

	public List<GtnFrameworkRouteBean> getEntityRouteList() {
		return Collections.unmodifiableList(entityRouteList);
	}

	public void addEntityRouteList(GtnFrameworkRouteBean entityRouteItem) {
		entityRouteList.add(entityRouteItem);
	}

	public GtnFrameworkEntityBean getEntityBean(int sourceEntityId) {
		for (GtnFrameworkEntityBean gtnFrameworkEntityBean : entityList) {
			int tableId = gtnFrameworkEntityBean.getEntityMasterSid();
			if (tableId == sourceEntityId)
				return gtnFrameworkEntityBean;

		}
		return null;
	}

	public GtnFramworkTableBean getEntityBeanByTableName(String tableName) {
		for (GtnFramworkTableBean gtnFrameworkTableBean : entityTableList) {
			String tableId = gtnFrameworkTableBean.getTablename();
			if (tableId.equals(tableName))
				return gtnFrameworkTableBean;

		}
		return null;
	}

	public List<GtnFramworkTableRelationBean> getListOfLeftRelatedBeans(int tableId, int prevId) {
		List<GtnFramworkTableRelationBean> tableRelationList = new ArrayList<>();
		for (GtnFramworkTableRelationBean gtnFramworkEntityRelationBean : entityRelationList) {
			int rightKey = gtnFramworkEntityRelationBean.getRightTableMasterSid();
			int lefttKey = gtnFramworkEntityRelationBean.getLefTableMasterSid();
			if (lefttKey == tableId && rightKey != prevId)
				tableRelationList.add(gtnFramworkEntityRelationBean);
		}
		return tableRelationList;
	}

	public List<GtnFramworkTableRelationBean> getListOfRightRelatedBeans(int tableId, int prevId) {
		List<GtnFramworkTableRelationBean> tableRelationList = new ArrayList<>();
		for (GtnFramworkTableRelationBean gtnFramworkEntityRelationBean : entityRelationList) {
			int rightKey = gtnFramworkEntityRelationBean.getRightTableMasterSid();
			int lefttKey = gtnFramworkEntityRelationBean.getLefTableMasterSid();
			if (rightKey == tableId && lefttKey != prevId)
				tableRelationList.add(gtnFramworkEntityRelationBean);
		}
		return tableRelationList;
	}

	public GtnFramworkTableBean getTableBeanUsingTableId(int tableId) {
		for (GtnFramworkTableBean gtnFramworkTableMasterBean : entityTableList) {
			if (gtnFramworkTableMasterBean.getTableId() == tableId) {
				return gtnFramworkTableMasterBean;
			}
		}
		return null;
	}

	public GtnFramworkTableRelationBean getRelationBeanUsingTableId(int leftTableId, int rightTableId) {
		for (GtnFramworkTableRelationBean gtnFramworkEntityRelationBean : entityRelationList) {
			int rightKey = gtnFramworkEntityRelationBean.getRightTableMasterSid();
			int lefttKey = gtnFramworkEntityRelationBean.getLefTableMasterSid();
			if (lefttKey == leftTableId && rightKey == rightTableId) {
				return gtnFramworkEntityRelationBean;
			}
			if (lefttKey == rightTableId && rightKey == leftTableId) {
				return gtnFramworkEntityRelationBean;
			}
		}
		return null;
	}

	public List<GtnFramworkTableRelationBean> getListOfAllRelatedBeans(int sourceTableId) {
		List<GtnFramworkTableRelationBean> outputList = new ArrayList<>();

		for (GtnFramworkTableRelationBean currentTableRelationBean : entityRelationList) {

			if (currentTableRelationBean.getLefTableMasterSid() == sourceTableId) {
				outputList.add(currentTableRelationBean);
			}

			if (currentTableRelationBean.getRightTableMasterSid() == sourceTableId) {
				outputList.add(currentTableRelationBean);
			}

		}
		return outputList;
	}

	public GtnFrameworkSingleColumnRelationBean getKeyRelationBeanUsingTableIdAndColumnName(String tableName,
			String columnName) {
		for (GtnFrameworkSingleColumnRelationBean gtnFrameworkKeyListBean : singleColumnRelationList) {
			String actualTableName = gtnFrameworkKeyListBean.getActualTtableName();
			String actualColumnName = gtnFrameworkKeyListBean.getActualColumnName();
			if (actualTableName.equals(tableName) && actualColumnName.equals(columnName)) {
				return gtnFrameworkKeyListBean;
			}
		}
		return null;
	}

	private void insertToEntityRelationList() {
		addEntityRelationList(new GtnFramworkTableRelationBean(201, 202, "CONTRACT_MASTER_SID", "CONTRACT_MASTER_SID"));
		addEntityRelationList(new GtnFramworkTableRelationBean(202, 203, "CFP_CONTRACT_SID", "CFP_CONTRACT_SID"));
		addEntityRelationList(new GtnFramworkTableRelationBean(203, 204, "COMPANY_MASTER_SID", "COMPANY_MASTER_SID"));
		addEntityRelationList(new GtnFramworkTableRelationBean(204, 205, "COMPANY_MASTER_SID", "COMPANY_MASTER_SID"));
		addEntityRelationList(new GtnFramworkTableRelationBean(204, 206, "COMPANY_MASTER_SID", "COMPANY_MASTER_SID"));
		addEntityRelationList(new GtnFramworkTableRelationBean(204, 207, "COMPANY_MASTER_SID", "COMPANY_MASTER_SID"));
		addEntityRelationList(new GtnFramworkTableRelationBean(208, 209, "COMPANY_NO", "COMPANY_CODE"));
		addEntityRelationList(new GtnFramworkTableRelationBean(209, 212, "NDC8", "NDC8"));
		addEntityRelationList(new GtnFramworkTableRelationBean(212, 210, "ITEM_MASTER_SID", "ITEM_MASTER_SID"));
		addEntityRelationList(new GtnFramworkTableRelationBean(212, 211, "BRAND_MASTER_SID", "BRAND_MASTER_SID"));

	}

	private void insertToEntityList() {
		addEntityList(new GtnFrameworkEntityBean(101, "Company", 204));
		addEntityList(new GtnFrameworkEntityBean(102, "Contract", 201));
		addEntityList(new GtnFrameworkEntityBean(103, "Company_Identifier", 205));
		addEntityList(new GtnFrameworkEntityBean(104, "Company_parent", 206));
		addEntityList(new GtnFrameworkEntityBean(105, "Company_trade_class", 207));
		addEntityList(new GtnFrameworkEntityBean(106, "ITEM", 212));
		addEntityList(new GtnFrameworkEntityBean(107, "COMPANY", 208));
		addEntityList(new GtnFrameworkEntityBean(108, "ITEM_IDENTIFIER", 210));
		addEntityList(new GtnFrameworkEntityBean(109, "BRAND_MASTERR", 211));

	}

	private void insertToTableList() {
		addEntityTableList(new GtnFramworkTableBean(201, "CONTRACT_MASTER"));
		addEntityTableList(new GtnFramworkTableBean(202, "CFP_CONTRACT"));
		addEntityTableList(new GtnFramworkTableBean(203, "CFP_CONTRACT_DETAILS"));
		addEntityTableList(new GtnFramworkTableBean(204, "COMPANY_MASTER"));
		addEntityTableList(new GtnFramworkTableBean(205, "COMPANY_IDENTIFIER"));
		addEntityTableList(new GtnFramworkTableBean(206, "COMPANY_PARENT_DETAILS"));
		addEntityTableList(new GtnFramworkTableBean(207, "COMPANY_TRADE_CLASS"));
		addEntityTableList(new GtnFramworkTableBean(208, "COMPANY_MASTER"));
		addEntityTableList(new GtnFramworkTableBean(209, "GL_COST_CENTER_MASTER"));
		addEntityTableList(new GtnFramworkTableBean(210, "ITEM_IDENTIFIER"));
		addEntityTableList(new GtnFramworkTableBean(211, "BRAND_MASTER"));
		addEntityTableList(new GtnFramworkTableBean(212, "ITEM_MASTER"));

	}

	private void insertToKeyRelationList() {
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(1, "CONTRACT_MASTER", "ADVANCE_NOTICE_DAYS",
				"CONTRACT_MASTER", "", "", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(2, "CONTRACT_MASTER", "AFFILIATED_CONTRACT_INFO",
				"CONTRACT_MASTER", "", "", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(3, "CONTRACT_MASTER", "AWARD_STATUS",
				"HELPER_TABLE", "HELPER_TABLE_SID", "DESCRIPTION", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(4, "CONTRACT_MASTER", "BATCH_ID", "CONTRACT_MASTER",
				"", "", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(5, "CONTRACT_MASTER", "BUNIT_COMPANY_MASTER_SID",
				"COMPANY_MASTER", "COMPANY_MASTER_SID", "COMPANY_NO", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(6, "CONTRACT_MASTER", "CANCELLATION_CLAUSE",
				"CONTRACT_MASTER", "", "", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(7, "CONTRACT_MASTER", "CATEGORY", "CONTRACT_MASTER",
				"", "", "CONTRACT_MASTER_SID"));
		addKeyRelationList(
				new GtnFrameworkSingleColumnRelationBean(8, "CONTRACT_MASTER", "CONT_HOLD_COMPANY_MASTER_SID",
						"COMPANY_MASTER", "COMPANY_MASTER_SID", "COMPANY_NO", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(9, "CONTRACT_MASTER", "CONTRACT_ID",
				"CONTRACT_MASTER", "", "", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(10, "CONTRACT_MASTER", "CONTRACT_MASTER_SID",
				"CONTRACT_MASTER", "", "", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(11, "CONTRACT_MASTER", "CONTRACT_NAME",
				"CONTRACT_MASTER", "", "", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(12, "CONTRACT_MASTER", "CONTRACT_NO",
				"CONTRACT_MASTER", "", "", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(13, "CONTRACT_MASTER", "CONTRACT_STATUS",
				"HELPER_TABLE", "HELPER_TABLE_SID", "DESCRIPTION", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(14, "CONTRACT_MASTER", "CONTRACT_TRADE_CLASS",
				"HELPER_TABLE", "HELPER_TABLE_SID", "DESCRIPTION", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(15, "CONTRACT_MASTER", "CONTRACT_TYPE",
				"HELPER_TABLE", "HELPER_TABLE_SID", "DESCRIPTION", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(16, "CONTRACT_MASTER", "CREATED_BY",
				"CONTRACT_MASTER", "", "", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(17, "CONTRACT_MASTER", "CREATED_DATE",
				"CONTRACT_MASTER", "", "", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(18, "CONTRACT_MASTER", "CURRENCY",
				"CONTRACT_MASTER", "", "", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(19, "CONTRACT_MASTER", "DOCUMENT_CLASS",
				"HELPER_TABLE", "HELPER_TABLE_SID", "DESCRIPTION", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(20, "CONTRACT_MASTER", "DOCUMENT_TYPE",
				"HELPER_TABLE", "HELPER_TABLE_SID", "DESCRIPTION", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(21, "CONTRACT_MASTER", "END_DATE",
				"CONTRACT_MASTER", "", "", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(22, "CONTRACT_MASTER", "EXEMPT_FROM_LOW_PRICE",
				"CONTRACT_MASTER", "", "", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(23, "CONTRACT_MASTER", "INBOUND_STATUS",
				"CONTRACT_MASTER", "", "", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(24, "CONTRACT_MASTER", "INSIDE_ADDITIONAL",
				"CONTRACT_MASTER", "", "", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(25, "CONTRACT_MASTER", "INSIDE_ADDITIONAL_NAME",
				"CONTRACT_MASTER", "", "", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(26, "CONTRACT_MASTER", "INSIDE_ADDITIONAL_PHONE",
				"CONTRACT_MASTER", "", "", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(27, "CONTRACT_MASTER", "INSIDE_AUTHOR",
				"CONTRACT_MASTER", "", "", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(28, "CONTRACT_MASTER", "INSIDE_OWNER",
				"CONTRACT_MASTER", "", "", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(29, "CONTRACT_MASTER", "INSIDE_PHONE",
				"CONTRACT_MASTER", "", "", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(30, "CONTRACT_MASTER", "INTERNAL_NOTES",
				"CONTRACT_MASTER", "", "", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(31, "CONTRACT_MASTER", "LAST_UPDATED_DATE",
				"CONTRACT_MASTER", "", "", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(32, "CONTRACT_MASTER", "MANF_COMPANY_MASTER_SID",
				"COMPANY_MASTER", "COMPANY_MASTER_SID", "COMPANY_NO", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(33, "CONTRACT_MASTER", "MINIMUM_ORDER",
				"CONTRACT_MASTER", "", "", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(34, "CONTRACT_MASTER", "MODIFIED_BY",
				"CONTRACT_MASTER", "", "", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(35, "CONTRACT_MASTER", "MODIFIED_DATE",
				"CONTRACT_MASTER", "", "", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(36, "CONTRACT_MASTER", "MOST_FAVORED_NATION",
				"CONTRACT_MASTER", "", "", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(37, "CONTRACT_MASTER", "ORGANIZATION_KEY",
				"CONTRACT_MASTER", "", "", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(38, "CONTRACT_MASTER", "ORIGINAL_END_DATE",
				"CONTRACT_MASTER", "", "", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(39, "CONTRACT_MASTER", "ORIGINAL_START_DATE",
				"CONTRACT_MASTER", "", "", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(40, "CONTRACT_MASTER", "OUTSIDE_ADDITIONAL",
				"CONTRACT_MASTER", "", "", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(41, "CONTRACT_MASTER", "OUTSIDE_ADDITIONAL_NAME",
				"CONTRACT_MASTER", "", "", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(42, "CONTRACT_MASTER", "OUTSIDE_ADDITIONAL_PHONE",
				"CONTRACT_MASTER", "", "", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(43, "CONTRACT_MASTER", "OUTSIDE_AUTHOR",
				"CONTRACT_MASTER", "", "", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(44, "CONTRACT_MASTER", "OUTSIDE_OWNER",
				"CONTRACT_MASTER", "", "", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(45, "CONTRACT_MASTER", "OUTSIDE_PHONE",
				"CONTRACT_MASTER", "", "", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(46, "CONTRACT_MASTER", "PAYMENT_TERMS",
				"HELPER_TABLE", "HELPER_TABLE_SID", "DESCRIPTION", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(47, "CONTRACT_MASTER", "PRICE_ESCALATION_CLAUSE",
				"CONTRACT_MASTER", "", "", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(48, "CONTRACT_MASTER", "PRICE_RESET_INDICATOR",
				"CONTRACT_MASTER", "", "", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(49, "CONTRACT_MASTER", "PRICEPROTECTION_END_DATE",
				"CONTRACT_MASTER", "", "", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(50, "CONTRACT_MASTER", "PRICEPROTECTION_START_DATE",
				"CONTRACT_MASTER", "", "", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(51, "CONTRACT_MASTER", "PROCESS_STATUS",
				"CONTRACT_MASTER", "", "", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(52, "CONTRACT_MASTER", "PROPOSAL_END_DATE",
				"CONTRACT_MASTER", "", "", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(53, "CONTRACT_MASTER", "PROPOSAL_START_DATE",
				"CONTRACT_MASTER", "", "", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(54, "CONTRACT_MASTER", "RECORD_LOCK_STATUS",
				"CONTRACT_MASTER", "", "", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(55, "CONTRACT_MASTER", "RENEGOTIATION_END_DATE",
				"CONTRACT_MASTER", "", "", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(56, "CONTRACT_MASTER", "RENEGOTIATION_START_DATE",
				"CONTRACT_MASTER", "", "", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(57, "CONTRACT_MASTER", "SHIPPING_TERMS",
				"CONTRACT_MASTER", "", "", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(58, "CONTRACT_MASTER", "SOURCE", "CONTRACT_MASTER",
				"", "", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(59, "CONTRACT_MASTER", "START_DATE",
				"CONTRACT_MASTER", "", "", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(60, "CONTRACT_MASTER", "TERM", "CONTRACT_MASTER",
				"", "", "CONTRACT_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(61, "COMPANY_MASTER", "ADDRESS1", "COMPANY_MASTER",
				"", "", "COMPANY_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(62, "COMPANY_MASTER", "ADDRESS2", "COMPANY_MASTER",
				"", "", "COMPANY_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(63, "COMPANY_MASTER", "BATCH_ID", "COMPANY_MASTER",
				"", "", "COMPANY_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(64, "COMPANY_MASTER", "CITY", "COMPANY_MASTER", "",
				"", "COMPANY_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(65, "COMPANY_MASTER", "COMPANY_CATEGORY",
				"HELPER_TABLE", "HELPER_TABLE_SID", "DESCRIPTION", "COMPANY_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(66, "COMPANY_MASTER", "COMPANY_END_DATE",
				"COMPANY_MASTER", "", "", "COMPANY_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(67, "COMPANY_MASTER", "COMPANY_GROUP",
				"HELPER_TABLE", "HELPER_TABLE_SID", "DESCRIPTION", "COMPANY_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(68, "COMPANY_MASTER", "COMPANY_ID",
				"COMPANY_MASTER", "", "", "COMPANY_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(69, "COMPANY_MASTER", "COMPANY_MASTER_SID",
				"COMPANY_MASTER", "", "", "COMPANY_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(70, "COMPANY_MASTER", "COMPANY_NAME",
				"COMPANY_MASTER", "", "", "COMPANY_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(71, "COMPANY_MASTER", "COMPANY_NO",
				"COMPANY_MASTER", "", "", "COMPANY_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(72, "COMPANY_MASTER", "COMPANY_START_DATE",
				"COMPANY_MASTER", "", "", "COMPANY_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(73, "COMPANY_MASTER", "COMPANY_STATUS",
				"HELPER_TABLE", "HELPER_TABLE_SID", "DESCRIPTION", "COMPANY_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(74, "COMPANY_MASTER", "COMPANY_TYPE",
				"HELPER_TABLE", "HELPER_TABLE_SID", "DESCRIPTION", "COMPANY_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(75, "COMPANY_MASTER", "COUNTRY", "HELPER_TABLE",
				"HELPER_TABLE_SID", "DESCRIPTION", "COMPANY_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(76, "COMPANY_MASTER", "CREATED_BY",
				"COMPANY_MASTER", "", "", "COMPANY_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(77, "COMPANY_MASTER", "CREATED_DATE",
				"COMPANY_MASTER", "", "", "COMPANY_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(78, "COMPANY_MASTER", "FINANCIAL_SYSTEM",
				"COMPANY_MASTER", "", "", "COMPANY_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(79, "COMPANY_MASTER", "INBOUND_STATUS",
				"COMPANY_MASTER", "", "", "COMPANY_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(80, "COMPANY_MASTER", "INTERNAL_NOTES",
				"COMPANY_MASTER", "", "", "COMPANY_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(81, "COMPANY_MASTER", "LAST_UPDATED_DATE",
				"COMPANY_MASTER", "", "", "COMPANY_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(82, "COMPANY_MASTER", "LIVES", "COMPANY_MASTER", "",
				"", "COMPANY_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(83, "COMPANY_MASTER", "MODIFIED_BY",
				"COMPANY_MASTER", "", "", "COMPANY_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(84, "COMPANY_MASTER", "MODIFIED_DATE",
				"COMPANY_MASTER", "", "", "COMPANY_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(85, "COMPANY_MASTER", "ORGANIZATION_KEY",
				"HELPER_TABLE", "HELPER_TABLE_SID", "DESCRIPTION", "COMPANY_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(86, "COMPANY_MASTER", "RECORD_LOCK_STATUS",
				"COMPANY_MASTER", "", "", "COMPANY_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(87, "COMPANY_MASTER", "REGION_CODE",
				"COMPANY_MASTER", "", "", "COMPANY_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(88, "COMPANY_MASTER", "SOURCE", "COMPANY_MASTER",
				"", "", "COMPANY_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(89, "COMPANY_MASTER", "STATE", "HELPER_TABLE",
				"HELPER_TABLE_SID", "DESCRIPTION", "COMPANY_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(90, "COMPANY_MASTER", "ZIP_CODE", "COMPANY_MASTER",
				"", "", "COMPANY_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(91, "ITEM_MASTER", "ACQUIRED_AMP", "ITEM_MASTER",
				"", "", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(92, "ITEM_MASTER", "ACQUIRED_BAMP", "ITEM_MASTER",
				"", "", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(93, "ITEM_MASTER", "ACQUISITION_DATE",
				"ITEM_MASTER", "", "", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(94, "ITEM_MASTER", "ALT_BASE_CPI", "ITEM_MASTER",
				"", "", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(95, "ITEM_MASTER", "ALT_BASELINE_AMP",
				"ITEM_MASTER", "", "", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(96, "ITEM_MASTER", "AUTHORIZED_GENERIC",
				"ITEM_MASTER", "", "", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(97, "ITEM_MASTER", "AUTHORIZED_GENERIC_END_DATE",
				"ITEM_MASTER", "", "", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(98, "ITEM_MASTER", "AUTHORIZED_GENERIC_START_DATE",
				"ITEM_MASTER", "", "", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(99, "ITEM_MASTER", "BASE_CPI", "ITEM_MASTER", "",
				"", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(100, "ITEM_MASTER", "BASE_CPI_PERIOD",
				"ITEM_MASTER", "", "", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(101, "ITEM_MASTER", "BASE_CPI_PRECISION",
				"ITEM_MASTER", "", "", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(102, "ITEM_MASTER", "BASELINE_AMP", "ITEM_MASTER",
				"", "", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(103, "ITEM_MASTER", "BASELINE_AMP_PRECISION",
				"ITEM_MASTER", "", "", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(104, "ITEM_MASTER", "BATCH_ID", "ITEM_MASTER", "",
				"", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(105, "ITEM_MASTER", "BRAND_MASTER_SID",
				"BRAND_MASTER", "BRAND_MASTER_SID", "BRAND_NAME", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(106, "ITEM_MASTER", "CLOTTING_FACTOR_END_DATE",
				"ITEM_MASTER", "", "", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(107, "ITEM_MASTER", "CLOTTING_FACTOR_INDICATOR",
				"ITEM_MASTER", "", "", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(108, "ITEM_MASTER", "CLOTTING_FACTOR_START_DATE",
				"ITEM_MASTER", "", "", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(109, "ITEM_MASTER", "CREATED_BY", "ITEM_MASTER", "",
				"", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(110, "ITEM_MASTER", "CREATED_DATE", "ITEM_MASTER",
				"", "", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(111, "ITEM_MASTER", "DISCONTINUATION_DATE",
				"ITEM_MASTER", "", "", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(112, "ITEM_MASTER", "DIVESTITURE_DATE",
				"ITEM_MASTER", "", "", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(113, "ITEM_MASTER", "DOSES_PER_UNIT", "ITEM_MASTER",
				"", "", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(114, "ITEM_MASTER", "DRA", "ITEM_MASTER", "", "",
				"ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(115, "ITEM_MASTER", "DUAL_PRICING_INDICATOR",
				"ITEM_MASTER", "", "", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(116, "ITEM_MASTER", "FIRST_SALE_DATE",
				"ITEM_MASTER", "", "", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(117, "ITEM_MASTER", "FORM", "HELPER_TABLE",
				"HELPER_TABLE_SID", "", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(118, "ITEM_MASTER", "INBOUND_STATUS", "ITEM_MASTER",
				"", "", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(119, "ITEM_MASTER", "INTERNAL_NOTES", "ITEM_MASTER",
				"", "", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(120, "ITEM_MASTER", "ITEM_CATEGORY", "HELPER_TABLE",
				"HELPER_TABLE_SID", "", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(121, "ITEM_MASTER", "ITEM_CLASS", "HELPER_TABLE",
				"HELPER_TABLE_SID", "", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(122, "ITEM_MASTER", "ITEM_CODE", "ITEM_MASTER", "",
				"", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(123, "ITEM_MASTER", "ITEM_DESC", "ITEM_MASTER", "",
				"", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(124, "ITEM_MASTER", "ITEM_END_DATE", "ITEM_MASTER",
				"", "", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(125, "ITEM_MASTER", "ITEM_FAMILY_ID", "ITEM_MASTER",
				"", "", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(126, "ITEM_MASTER", "ITEM_ID", "ITEM_MASTER", "",
				"", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(127, "ITEM_MASTER", "ITEM_MASTER_SID",
				"ITEM_MASTER", "", "", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(128, "ITEM_MASTER", "ITEM_NAME", "ITEM_MASTER", "",
				"", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(129, "ITEM_MASTER", "ITEM_NO", "ITEM_MASTER", "",
				"", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(130, "ITEM_MASTER", "ITEM_START_DATE",
				"ITEM_MASTER", "", "", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(131, "ITEM_MASTER", "ITEM_STATUS", "HELPER_TABLE",
				"HELPER_TABLE_SID", "", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(132, "ITEM_MASTER", "ITEM_TYPE", "HELPER_TABLE",
				"HELPER_TABLE_SID", "", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(133, "ITEM_MASTER", "ITEM_TYPE_INDICATION",
				"HELPER_TABLE", "HELPER_TABLE_SID", "", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(134, "ITEM_MASTER", "LABELER_CODE", "ITEM_MASTER",
				"", "", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(135, "ITEM_MASTER", "LAST_LOT_EXPIRATION_DATE",
				"ITEM_MASTER", "", "", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(136, "ITEM_MASTER", "MANUFACTURER_ID",
				"ITEM_MASTER", "", "", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(137, "ITEM_MASTER", "MARKET_TERMINATION_DATE",
				"ITEM_MASTER", "", "", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(138, "ITEM_MASTER", "MODIFIED_BY", "ITEM_MASTER",
				"", "", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(139, "ITEM_MASTER", "MODIFIED_DATE", "ITEM_MASTER",
				"", "", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(140, "ITEM_MASTER", "NDC8", "ITEM_MASTER", "", "",
				"ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(141, "ITEM_MASTER", "NDC9", "ITEM_MASTER", "", "",
				"ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(142, "ITEM_MASTER", "NEW_FORMULATION",
				"ITEM_MASTER", "", "", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(143, "ITEM_MASTER", "NEW_FORMULATION_END_DATE",
				"ITEM_MASTER", "", "", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(144, "ITEM_MASTER", "NEW_FORMULATION_INDICATOR",
				"ITEM_MASTER", "", "", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(145, "ITEM_MASTER", "NEW_FORMULATION_START_DATE",
				"ITEM_MASTER", "", "", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(146, "ITEM_MASTER", "NON_FEDERAL_EXPIRATION_DATE",
				"ITEM_MASTER", "", "", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(147, "ITEM_MASTER", "OBRA_BAMP", "ITEM_MASTER", "",
				"", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(148, "ITEM_MASTER", "ORGANIZATION_KEY",
				"COMPANY_MASTER", "COMPANY_MASTER_SID", "COMPANY_NO", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(149, "ITEM_MASTER", "PACKAGE_SIZE", "HELPER_TABLE",
				"HELPER_TABLE_SID", "", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(150, "ITEM_MASTER", "PACKAGE_SIZE_CODE",
				"ITEM_MASTER", "", "", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(151, "ITEM_MASTER", "PACKAGE_SIZE_INTRO_DATE",
				"ITEM_MASTER", "", "", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(152, "ITEM_MASTER", "PEDIATRIC_EXCLUSIVE_END_DATE",
				"ITEM_MASTER", "", "", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(153, "ITEM_MASTER", "PEDIATRIC_EXCLUSIVE_INDICATOR",
				"ITEM_MASTER", "", "", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(154, "ITEM_MASTER",
				"PEDIATRIC_EXCLUSIVE_START_DATE", "ITEM_MASTER", "", "", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(155, "ITEM_MASTER", "PRIMARY_UOM", "HELPER_TABLE",
				"HELPER_TABLE_SID", "", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(156, "ITEM_MASTER", "RECORD_LOCK_STATUS",
				"ITEM_MASTER", "", "", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(157, "ITEM_MASTER", "SECONDARY_UOM", "HELPER_TABLE",
				"HELPER_TABLE_SID", "", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(158, "ITEM_MASTER", "SHELF_LIFE", "ITEM_MASTER", "",
				"", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(159, "ITEM_MASTER", "SHELF_LIFE_TYPE",
				"HELPER_TABLE", "HELPER_TABLE_SID", "", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(160, "ITEM_MASTER", "SOURCE", "ITEM_MASTER", "", "",
				"ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(161, "ITEM_MASTER", "STRENGTH", "HELPER_TABLE",
				"HELPER_TABLE_SID", "", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(162, "ITEM_MASTER", "THERAPEUTIC_CLASS",
				"HELPER_TABLE", "HELPER_TABLE_SID", "", "ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(163, "ITEM_MASTER", "UPPS", "ITEM_MASTER", "", "",
				"ITEM_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(164, "ITEM_IDENTIFIER", "BATCH_ID",
				"ITEM_IDENTIFIER", "", "", "ITEM_IDENTIFIER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(165, "ITEM_IDENTIFIER", "CREATED_BY",
				"ITEM_IDENTIFIER", "", "", "ITEM_IDENTIFIER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(166, "ITEM_IDENTIFIER", "CREATED_DATE",
				"ITEM_IDENTIFIER", "", "", "ITEM_IDENTIFIER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(167, "ITEM_IDENTIFIER", "END_DATE",
				"ITEM_IDENTIFIER", "", "", "ITEM_IDENTIFIER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(168, "ITEM_IDENTIFIER", "ENTITY_CODE",
				"ITEM_IDENTIFIER", "", "", "ITEM_IDENTIFIER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(169, "ITEM_IDENTIFIER", "IDENTIFIER_STATUS",
				"HELPER_TABLE", "HELPER_TABLE_SID", "", "ITEM_IDENTIFIER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(170, "ITEM_IDENTIFIER", "INBOUND_STATUS",
				"ITEM_IDENTIFIER", "", "", "ITEM_IDENTIFIER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(171, "ITEM_IDENTIFIER", "ITEM_IDENTIFIER_SID",
				"ITEM_IDENTIFIER", "", "", "ITEM_IDENTIFIER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(172, "ITEM_IDENTIFIER", "ITEM_IDENTIFIER_VALUE",
				"ITEM_IDENTIFIER", "", "", "ITEM_IDENTIFIER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(173, "ITEM_IDENTIFIER", "ITEM_MASTER_SID",
				"ITEM_MASTER", "ITEM_MASTER_SID", "ITEM_NO", "ITEM_IDENTIFIER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(174, "ITEM_IDENTIFIER", "ITEM_QUALIFIER_SID",
				"ITEM_QUALIFIER", "ITEM_QUALIFIER_SID", "ITEM_QUALIFIER_NAME", "ITEM_IDENTIFIER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(175, "ITEM_IDENTIFIER", "MODIFIED_BY",
				"ITEM_IDENTIFIER", "", "", "ITEM_IDENTIFIER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(176, "ITEM_IDENTIFIER", "MODIFIED_DATE",
				"ITEM_IDENTIFIER", "", "", "ITEM_IDENTIFIER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(177, "ITEM_IDENTIFIER", "RECORD_LOCK_STATUS",
				"ITEM_IDENTIFIER", "", "", "ITEM_IDENTIFIER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(178, "ITEM_IDENTIFIER", "SOURCE", "ITEM_IDENTIFIER",
				"", "", "ITEM_IDENTIFIER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(179, "ITEM_IDENTIFIER", "START_DATE",
				"ITEM_IDENTIFIER", "", "", "ITEM_IDENTIFIER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(180, "BRAND_MASTER", "BATCH_ID", "BRAND_MASTER", "",
				"", "BRAND_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(181, "BRAND_MASTER", "BRAND_DESC", "BRAND_MASTER",
				"", "", "BRAND_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(182, "BRAND_MASTER", "BRAND_ID", "BRAND_MASTER", "",
				"", "BRAND_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(183, "BRAND_MASTER", "BRAND_MASTER_SID",
				"BRAND_MASTER", "", "", "BRAND_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(184, "BRAND_MASTER", "BRAND_NAME", "BRAND_MASTER",
				"", "", "BRAND_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(185, "BRAND_MASTER", "CREATED_BY", "BRAND_MASTER",
				"", "", "BRAND_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(186, "BRAND_MASTER", "CREATED_DATE", "BRAND_MASTER",
				"", "", "BRAND_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(187, "BRAND_MASTER", "DISPLAY_BRAND",
				"BRAND_MASTER", "", "", "BRAND_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(188, "BRAND_MASTER", "INBOUND_STATUS",
				"BRAND_MASTER", "", "", "BRAND_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(189, "BRAND_MASTER", "MODIFIED_BY", "BRAND_MASTER",
				"", "", "BRAND_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(190, "BRAND_MASTER", "MODIFIED_DATE",
				"BRAND_MASTER", "", "", "BRAND_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(191, "BRAND_MASTER", "RECORD_LOCK_STATUS",
				"BRAND_MASTER", "", "", "BRAND_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(192, "BRAND_MASTER", "SOURCE", "BRAND_MASTER", "",
				"", "BRAND_MASTER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(193, "COMPANY_IDENTIFIER", "BATCH_ID",
				"COMPANY_IDENTIFIER", "", "", "COMPANY_IDENTIFIER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(194, "COMPANY_IDENTIFIER", "COMPANY_IDENTIFIER_SID",
				"COMPANY_IDENTIFIER", "", "", "COMPANY_IDENTIFIER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(195, "COMPANY_IDENTIFIER",
				"COMPANY_IDENTIFIER_VALUE", "COMPANY_IDENTIFIER", "", "", "COMPANY_IDENTIFIER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(196, "COMPANY_IDENTIFIER", "COMPANY_MASTER_SID",
				"COMPANY_MASTER", "COMPANY_MASTER_SID", "COMPANY_NO", "COMPANY_IDENTIFIER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(197, "COMPANY_IDENTIFIER", "COMPANY_QUALIFIER_SID",
				"COMPANY_QUALIFIER", "COMPANY_QUALIFIER_SID", "COMPANY_QUALIFIER_NAME", "COMPANY_IDENTIFIER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(198, "COMPANY_IDENTIFIER", "CREATED_BY",
				"COMPANY_IDENTIFIER", "", "", "COMPANY_IDENTIFIER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(199, "COMPANY_IDENTIFIER", "CREATED_DATE",
				"COMPANY_IDENTIFIER", "", "", "COMPANY_IDENTIFIER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(200, "COMPANY_IDENTIFIER", "END_DATE",
				"COMPANY_IDENTIFIER", "", "", "COMPANY_IDENTIFIER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(201, "COMPANY_IDENTIFIER", "ENTITY_CODE",
				"COMPANY_IDENTIFIER", "", "", "COMPANY_IDENTIFIER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(202, "COMPANY_IDENTIFIER", "IDENTIFIER_STATUS",
				"HELPER_TABLE", "HELPER_TABLE_SID", "", "COMPANY_IDENTIFIER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(203, "COMPANY_IDENTIFIER", "INBOUND_STATUS",
				"COMPANY_IDENTIFIER", "", "", "COMPANY_IDENTIFIER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(204, "COMPANY_IDENTIFIER", "MODIFIED_BY",
				"COMPANY_IDENTIFIER", "", "", "COMPANY_IDENTIFIER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(205, "COMPANY_IDENTIFIER", "MODIFIED_DATE",
				"COMPANY_IDENTIFIER", "", "", "COMPANY_IDENTIFIER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(206, "COMPANY_IDENTIFIER", "RECORD_LOCK_STATUS",
				"COMPANY_IDENTIFIER", "", "", "COMPANY_IDENTIFIER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(207, "COMPANY_IDENTIFIER", "SOURCE",
				"COMPANY_IDENTIFIER", "", "", "COMPANY_IDENTIFIER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(208, "COMPANY_IDENTIFIER", "START_DATE",
				"COMPANY_IDENTIFIER", "", "", "COMPANY_IDENTIFIER_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(209, "COMPANY_PARENT_DETAILS", "BATCH_ID",
				"COMPANY_PARENT_DETAILS", "", "", "COMPANY_PARENT_DETAILS_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(210, "COMPANY_PARENT_DETAILS", "COMPANY_MASTER_SID",
				"COMPANY_MASTER", "COMPANY_MASTER_SID", "COMPANY_NO", "COMPANY_PARENT_DETAILS_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(211, "COMPANY_PARENT_DETAILS",
				"COMPANY_PARENT_DETAILS_SID", "COMPANY_PARENT_DETAILS", "", "", "COMPANY_PARENT_DETAILS_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(212, "COMPANY_PARENT_DETAILS", "CREATED_BY",
				"COMPANY_PARENT_DETAILS", "", "", "COMPANY_PARENT_DETAILS_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(213, "COMPANY_PARENT_DETAILS", "CREATED_DATE",
				"COMPANY_PARENT_DETAILS", "", "", "COMPANY_PARENT_DETAILS_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(214, "COMPANY_PARENT_DETAILS", "INBOUND_STATUS",
				"COMPANY_PARENT_DETAILS", "", "", "COMPANY_PARENT_DETAILS_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(215, "COMPANY_PARENT_DETAILS", "LAST_UPDATED_DATE",
				"COMPANY_PARENT_DETAILS", "", "", "COMPANY_PARENT_DETAILS_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(216, "COMPANY_PARENT_DETAILS", "MODIFIED_BY",
				"COMPANY_PARENT_DETAILS", "", "", "COMPANY_PARENT_DETAILS_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(217, "COMPANY_PARENT_DETAILS", "MODIFIED_DATE",
				"COMPANY_PARENT_DETAILS", "", "", "COMPANY_PARENT_DETAILS_SID"));
		addKeyRelationList(
				new GtnFrameworkSingleColumnRelationBean(218, "COMPANY_PARENT_DETAILS", "PARENT_COMPANY_MASTER_SID",
						"COMPANY_MASTER", "COMPANY_MASTER_SID", "COMPANY_NO", "COMPANY_PARENT_DETAILS_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(219, "COMPANY_PARENT_DETAILS", "PARENT_END_DATE",
				"COMPANY_PARENT_DETAILS", "", "", "COMPANY_PARENT_DETAILS_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(220, "COMPANY_PARENT_DETAILS", "PARENT_START_DATE",
				"COMPANY_PARENT_DETAILS", "", "", "COMPANY_PARENT_DETAILS_SID"));
		addKeyRelationList(
				new GtnFrameworkSingleColumnRelationBean(221, "COMPANY_PARENT_DETAILS", "PRIOR_PARENT_CMPY_MASTER_SID",
						"COMPANY_MASTER", "COMPANY_MASTER_SID", "COMPANY_NO", "COMPANY_PARENT_DETAILS_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(222, "COMPANY_PARENT_DETAILS",
				"PRIOR_PARENT_START_DATE", "COMPANY_PARENT_DETAILS", "", "", "COMPANY_PARENT_DETAILS_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(223, "COMPANY_PARENT_DETAILS", "RECORD_LOCK_STATUS",
				"COMPANY_PARENT_DETAILS", "", "", "COMPANY_PARENT_DETAILS_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(224, "COMPANY_PARENT_DETAILS", "SOURCE",
				"COMPANY_PARENT_DETAILS", "", "", "COMPANY_PARENT_DETAILS_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(225, "COMPANY_TRADE_CLASS", "BATCH_ID",
				"COMPANY_TRADE_CLASS", "", "", "COMPANY_TRADE_CLASS_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(226, "COMPANY_TRADE_CLASS", "COMPANY_MASTER_SID",
				"COMPANY_MASTER", "COMPANY_MASTER_SID", "COMPANY_NO", "COMPANY_TRADE_CLASS_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(227, "COMPANY_TRADE_CLASS", "COMPANY_TRADE_CLASS",
				"HELPER_TABLE", "HELPER_TABLE_SID", "", "COMPANY_TRADE_CLASS_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(228, "COMPANY_TRADE_CLASS",
				"COMPANY_TRADE_CLASS_SID", "COMPANY_TRADE_CLASS", "", "", "COMPANY_TRADE_CLASS_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(229, "COMPANY_TRADE_CLASS", "CREATED_BY",
				"COMPANY_TRADE_CLASS", "", "", "COMPANY_TRADE_CLASS_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(230, "COMPANY_TRADE_CLASS", "CREATED_DATE",
				"COMPANY_TRADE_CLASS", "", "", "COMPANY_TRADE_CLASS_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(231, "COMPANY_TRADE_CLASS", "INBOUND_STATUS",
				"COMPANY_TRADE_CLASS", "", "", "COMPANY_TRADE_CLASS_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(232, "COMPANY_TRADE_CLASS", "LAST_UPDATED_DATE",
				"COMPANY_TRADE_CLASS", "", "", "COMPANY_TRADE_CLASS_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(233, "COMPANY_TRADE_CLASS", "MODIFIED_BY",
				"COMPANY_TRADE_CLASS", "", "", "COMPANY_TRADE_CLASS_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(234, "COMPANY_TRADE_CLASS", "MODIFIED_DATE",
				"COMPANY_TRADE_CLASS", "", "", "COMPANY_TRADE_CLASS_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(235, "COMPANY_TRADE_CLASS", "PRIOR_TRADE_CLASS",
				"HELPER_TABLE", "HELPER_TABLE_SID", "", "COMPANY_TRADE_CLASS_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(236, "COMPANY_TRADE_CLASS",
				"PRIOR_TRADE_CLASS_START_DATE", "COMPANY_TRADE_CLASS", "", "", "COMPANY_TRADE_CLASS_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(237, "COMPANY_TRADE_CLASS", "RECORD_LOCK_STATUS",
				"COMPANY_TRADE_CLASS", "", "", "COMPANY_TRADE_CLASS_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(238, "COMPANY_TRADE_CLASS", "SOURCE",
				"COMPANY_TRADE_CLASS", "", "", "COMPANY_TRADE_CLASS_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(239, "COMPANY_TRADE_CLASS", "TRADE_CLASS_END_DATE",
				"COMPANY_TRADE_CLASS", "", "", "COMPANY_TRADE_CLASS_SID"));
		addKeyRelationList(new GtnFrameworkSingleColumnRelationBean(240, "COMPANY_TRADE_CLASS",
				"TRADE_CLASS_START_DATE", "COMPANY_TRADE_CLASS", "", "", "COMPANY_TRADE_CLASS_SID"));
	}

	private void insertToHierarchyRelationList() {
		addEntityHierarchyRelationList(
				new GtnFrameworkEntityHierarchyRelationBean(101, "COMPANY_MASTER", "CUSTOMER HIERARCHY"));
		addEntityHierarchyRelationList(
				new GtnFrameworkEntityHierarchyRelationBean(102, "CONTRACT_MASTER", "CUSTOMER HIERARCHY"));
		addEntityHierarchyRelationList(
				new GtnFrameworkEntityHierarchyRelationBean(103, "COMPANY_IDENTIFIER", "CUSTOMER HIERARCHY"));
		addEntityHierarchyRelationList(
				new GtnFrameworkEntityHierarchyRelationBean(104, "COMPANY_PARENT_DETAILS", "CUSTOMER HIERARCHY"));
		addEntityHierarchyRelationList(
				new GtnFrameworkEntityHierarchyRelationBean(105, "COMPANY_TRADE_CLASS", "CUSTOMER HIERARCHY"));
		addEntityHierarchyRelationList(
				new GtnFrameworkEntityHierarchyRelationBean(106, "ITEM_MASTER", "PRODUCT HIERARCHY "));
		addEntityHierarchyRelationList(
				new GtnFrameworkEntityHierarchyRelationBean(107, "COMPANY_MASTER", "PRODUCT HIERARCHY "));
		addEntityHierarchyRelationList(
				new GtnFrameworkEntityHierarchyRelationBean(108, "ITEM_IDENTIFIER", "PRODUCT HIERARCHY "));
		addEntityHierarchyRelationList(
				new GtnFrameworkEntityHierarchyRelationBean(109, "BRAND_MASTER", "PRODUCT HIERARCHY "));
	}

	public GtnFrameworkEntityHierarchyRelationBean getEntityHirarchyRelationBean(String tableName,
			String hierarchyType) {
		for (GtnFrameworkEntityHierarchyRelationBean entityHirarchyRelationBean : entityHirarchyRelationList) {
			if (entityHirarchyRelationBean.getTableName().equalsIgnoreCase(tableName)
					&& entityHirarchyRelationBean.getHierarchyType().equalsIgnoreCase(hierarchyType)) {
				return entityHirarchyRelationBean;
			}
		}
		return null;
	}

}
